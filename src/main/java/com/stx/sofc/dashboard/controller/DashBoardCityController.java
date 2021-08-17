package com.stx.sofc.dashboard.controller;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stx.sofc.dashboard.service.DashBoardAreaService;
import com.stx.sofc.dashboard.service.DashBoardCityService;
import com.stx.sofc.dashboard.service.DashBoardSiteService;
import com.stx.sofc.dashboard.service.DashBoardSystemDetailService;
import com.stx.sofc.dashboard.service.DashBoardSystemService;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.util.mail.MailMessageDef;

@Controller
@RequestMapping("/dashboard/city/*")
public class DashBoardCityController {
	
	@Inject
	private DashBoardCityService cityService;
	
	@Inject
	private DashBoardAreaService areaService;
	
	@Inject
	private DashBoardSiteService siteService;
	
	@Inject
	private DashBoardSystemService systemService;
	
	@Inject
	private DashBoardSystemDetailService systemDetailService;
	
	/**
     * <pre>
     * 메인화면 Switch
     * </pre>
     * @author kct
     * @since 2020. 3. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/mainSwitch", method = RequestMethod.GET)
	public String mainSwitch(){
		
		String sPath ="";
		String sAuth = "";
		
		try {
			
			//세션에 있는 로그인 정보 조회
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
			Iterator<? extends GrantedAuthority> iter = authorities.iterator(); 

			while (iter.hasNext()) { 
				GrantedAuthority auth = iter.next();
				sAuth = auth.getAuthority();
			}
			
			if(sAuth == "ROLE_SU") {
				sPath = "redirect:/dashboard/city/mng";
			}else {
				sPath = "redirect:/dashboard/system/mngSub";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		return sPath;


	}
	
	/**
     * <pre>
     * 메인화면
     * </pre>
     * @author kct
     * @since 2020. 3. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/mng", method = RequestMethod.GET)
	public ModelAndView cityMng(ModelAndView mv) {
	  	
		mv.setViewName("dashboard/cityMng");
		
		return mv;
	}
	
	/**
     * <pre>
     * 메인화면 발전기 리스트
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param DashboardVO
     * @return ModelAndView
     */
	@RequestMapping(value = "/list")
	public ModelAndView cityList(DashboardVO vo, ModelAndView mv) {
	
		try {
			String sAuth = "";
			
			//세션에 있는 로그인 정보 조회
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
			Iterator<? extends GrantedAuthority> iter = authorities.iterator(); 

			while (iter.hasNext()) { 
				GrantedAuthority auth = iter.next();
				sAuth = auth.getAuthority();
			}
			
			mv.addObject("sAuth", sAuth);
			
			//조회 하려는 페이지
		    int startPage = (vo.getStartPage()!=null?Integer.parseInt(vo.getStartPage().toString()):1);
		    //한페이지에 보여줄 리스트 수
		    int visiblePages = (vo.getVisiblePages()!=null?Integer.parseInt(vo.getVisiblePages().toString()):6);
		    //일단 전체 건수를 가져온다.
		    int totalCnt = cityService.cityNameList(vo).size();
		    
		    //1.하단 페이지 네비게이션에서 보여줄 리스트 수를 구한다.
		    BigDecimal decimal1 = new BigDecimal(totalCnt);
		    BigDecimal decimal2 = new BigDecimal(visiblePages);
		    BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);
		
		    int startLimitPage = 0;
		    //2.mssql limit 범위를 구하기 위해 계산
		    if(startPage == 1){
		        startLimitPage = 0;
		    }else{
		        startLimitPage = (startPage - 1) * visiblePages;
		    }
		    
		    if(startPage > (totalCnt / visiblePages) + 1 ) {
		    	double dTemp = Math.floor(totalCnt / visiblePages);
		    	
		    	vo.setStart(Integer.toString(totalCnt - visiblePages));
		    	vo.setEnd(Integer.toString(totalCnt));
		    	mv.addObject("startPage", (int)dTemp + 1);							//현재 페이지      
		    }else {
		    	vo.setStart(Integer.toString(startLimitPage + 1));
		        vo.setEnd(Integer.toString(startLimitPage + visiblePages));
		        mv.addObject("startPage", startPage);								//현재 페이지      
		    }
		    
		    mv.addObject("visiblePages", visiblePages);								//화면에 보여줄 갯수
		    mv.addObject("totalPage", totalPage);									//페이지 네비게이션에 보여줄 리스트 수
			List<DashboardVO> cityList = cityService.cityList(vo);
			cityList = cityList.stream().sorted(Comparator.comparing(DashboardVO::getsCityName)).collect(Collectors.toList());
		    mv.addObject("cityList", cityList);
		    mv.setViewName("dashboard/cityList");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	    return mv;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/measureList", method=RequestMethod.POST)
	public HashMap<String, Object> systemMeasureList(DashboardVO vo) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		int iNormal = 0;
		int iWait = 0;
		int iBreakDown = 0;
		int iPreparing = 0;
		int iBoardStatusTot = 0;
		
		JSONArray measureObjArray = new JSONArray();
		JSONArray measureAmtArray = new JSONArray();
		
		JSONArray wattProduceHourArray = new JSONArray();
		
		JSONArray wattSubProduceArray = new JSONArray();
		JSONArray wattSubProduceArray1 = new JSONArray();
		JSONObject wattSubProduceInfo = new JSONObject();
		
		BigDecimal tdCumulativeWatt = BigDecimal.valueOf(0);
		BigDecimal tdEfficiency = BigDecimal.valueOf(0);
		BigDecimal tdAccumulateHeatConsume = BigDecimal.valueOf(0);
		BigDecimal pdCumulativeWatt = BigDecimal.valueOf(0);
		BigDecimal cumulativeEfficiency = BigDecimal.valueOf(0);
		
		BigDecimal tdReductionCo2Watt = BigDecimal.valueOf(0);
		BigDecimal fCapa = BigDecimal.valueOf(0);
		BigDecimal cumulativeWatt = BigDecimal.valueOf(0);
		
		BigDecimal tdAccumulateHeatProduce = BigDecimal.valueOf(0);
		BigDecimal tdReductionCo2Heat = BigDecimal.valueOf(0);
		BigDecimal fCapaSolar = BigDecimal.valueOf(0);
		BigDecimal accumulateHeatProduce = BigDecimal.valueOf(0);
		
		BigDecimal[] txtCumulativeWattHour = {
												BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
												BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
												BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
												BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
												BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
											};
		
		String timestamp = "";
		
		try {
			
			List<DashboardVO> cityRtuIdList = cityService.systemRtuIdList(vo);

			// 병렬로 쿼리 실행
			List<DashboardVO> threadMeasureList = new ArrayList<>();
			threadMeasureList = threadMeasure(vo, threadMeasureList, cityRtuIdList);

//			Comparator<DashboardVO> compare1 = Comparator
//					.comparing(DashboardVO::getsSystemName)
//					.thenComparing(DashboardVO::getsSystemName);
//
//			threadMeasureList = threadMeasureList.stream()
//					.sorted(compare1)
//					.collect(Collectors.toList());


			for(DashboardVO dbvo : threadMeasureList){

				tdCumulativeWatt = tdCumulativeWatt.add(dbvo.getTdCumulativeWatt());
				tdEfficiency = tdEfficiency.add(dbvo.getTdEfficiency());
				tdAccumulateHeatConsume = tdAccumulateHeatConsume.add(dbvo.getTdAccumulateHeatConsume());
				pdCumulativeWatt = pdCumulativeWatt.add(dbvo.getPdCumulativeWatt());
				cumulativeEfficiency = cumulativeEfficiency.add(dbvo.getCumulativeEfficiency());

				tdReductionCo2Watt = tdReductionCo2Watt.add(dbvo.getTdReductionCo2Watt());
				fCapa = fCapa.add(dbvo.getfCapa());
				cumulativeWatt = cumulativeWatt.add(dbvo.getCumulativeWatt());

				tdAccumulateHeatProduce = tdAccumulateHeatProduce.add(dbvo.getTdAccumulateHeatProduce());
				tdReductionCo2Heat = tdReductionCo2Heat.add(dbvo.getTdReductionCo2Heat());
				fCapaSolar = fCapaSolar.add(dbvo.getfCapaSolar());
				accumulateHeatProduce = accumulateHeatProduce.add(dbvo.getAccumulateHeatProduce());

				timestamp = dbvo.getTimestamp();

				String sTxtCumulativeWattHour = dbvo.getTxtCumulativeWattHour().replaceAll("[\\{\\}]", "");
				String sTemp[] = sTxtCumulativeWattHour.split(",");

				if(sTemp.length == 24) {
					for(int i = 0 ; i < sTemp.length ; i++) {

						txtCumulativeWattHour[i] = txtCumulativeWattHour[i].add(BigDecimal.valueOf(Double.parseDouble(sTemp[i])));

					}
				}

				//운영현황
				if(dbvo.getTdStatusBoardState().equals("0")) {
					iNormal += 1;
				}else if(dbvo.getTdStatusBoardState().equals("1")) {
					iWait += 1;
				}else if(dbvo.getTdStatusBoardState().equals("2")) {
					iBreakDown += 1;
				}else if(dbvo.getTdStatusBoardState().equals("3")) {
					iPreparing += 1;
				}
				iBoardStatusTot += 1;

				result.put("iNormal", iNormal);
				result.put("iWait", iWait);
				result.put("iBreakDown", iBreakDown);
				result.put("iPreparing", iPreparing);
				result.put("iBoardStatusTot", iBoardStatusTot);
			}


			for(int i = 0 ; i < 24 ; i++) {
				wattProduceHourArray.add(i);
			}

			BigDecimal bigTemp1 = BigDecimal.valueOf(1000);

			for(int i = 0 ; i < txtCumulativeWattHour.length ; i++) {

				BigDecimal bigTemp;

				wattSubProduceInfo.put("name", "CITY");

				bigTemp = txtCumulativeWattHour[i];
				bigTemp = bigTemp.divide(bigTemp1, 2, BigDecimal.ROUND_HALF_UP);

				wattSubProduceArray1.add(bigTemp);
				wattSubProduceInfo.put("data", wattSubProduceArray1);

				wattSubProduceArray.add(wattSubProduceInfo);
			}
			
			List<DashboardVO> cityEffiGraph = cityService.cityEffiGraph(vo);

			cityEffiGraph = cityEffiGraph.stream().sorted(Comparator.comparing(DashboardVO::getsCityName)).collect(Collectors.toList());
			
			for (DashboardVO effiGraph : cityEffiGraph) {
				//발전 효율 차트용 데이터
				measureObjArray.add(effiGraph.getsCityName());
				measureAmtArray.add(Math.round(effiGraph.getTdEfficiency().doubleValue()));
			}
			
			DashboardVO measure = new DashboardVO();
			
			BigDecimal bSize = BigDecimal.valueOf(cityRtuIdList.size());
			
			measure.setTdCumulativeWatt(tdCumulativeWatt);
			measure.setTdEfficiency(tdEfficiency.divide(bSize, 0, BigDecimal.ROUND_HALF_UP));
			measure.setTdAccumulateHeatConsume(tdAccumulateHeatConsume);
			measure.setPdCumulativeWatt(pdCumulativeWatt);
			measure.setCumulativeEfficiency(cumulativeEfficiency.divide(bSize, 0, BigDecimal.ROUND_HALF_UP));
			
			measure.setTdReductionCo2Watt(tdReductionCo2Watt);
			measure.setfCapa(fCapa);
			measure.setCumulativeWatt(cumulativeWatt);
			
			measure.setTdAccumulateHeatProduce(tdAccumulateHeatProduce);
			measure.setTdReductionCo2Heat(tdReductionCo2Heat);
			measure.setfCapaSolar(fCapaSolar);
			measure.setAccumulateHeatProduce(accumulateHeatProduce);
			
			measure.setTimestamp(timestamp);
			
			
			result.put("measure", measure);
			
			result.put("effiChartObjJson", measureObjArray);
			result.put("effiChartAmtJson", measureAmtArray);
			
			result.put("wattSubProduceArray", wattSubProduceArray);
			result.put("wattProduceHourArray", wattProduceHourArray);
			
			result.put("systemCnt", cityRtuIdList.size());
			
			result.put("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return result;
	}

	public List<DashboardVO> threadMeasure(DashboardVO vo, List<DashboardVO> threadMeasureList, List<DashboardVO> cityRtuIdList) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(cityRtuIdList.size());

		for (int i = 0; i < cityRtuIdList.size(); i++) {
			final int jobId = i;

			// ThreadPoolExecutor에 Task를 예약하면, 여유가 있을 때 Task를 수행합니다.
			executor.execute(() -> {
				try {
//					TimeUnit.MILLISECONDS.sleep(100);
					String threadName = Thread.currentThread().getName();
					DashboardVO tmpVO = cityRtuIdList.get(jobId);
					String getiRtuNum = tmpVO.getiRtuNum();
//					System.out.println("getiRtuNum : "+getiRtuNum);
					vo.setiRtuNum(tmpVO.getiRtuNum());
					DashboardVO dbvo = cityService.cityMeasure(getiRtuNum);

					synchronized (threadMeasureList) {
						threadMeasureList.add(dbvo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		executor.shutdown();

		if (executor.awaitTermination(20, TimeUnit.SECONDS)) {
			System.out.println(LocalTime.now() + " All jobs are terminated");
		} else {
			System.out.println(LocalTime.now() + " some jobs are not terminated");

			// 모든 Task를 강제 종료합니다.
			executor.shutdownNow();
		}

		return threadMeasureList;
	}

	/**
     * <pre>
     * 메인화면 추가 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param DashboardVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/insertCityInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertCityInfo(DashboardVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		cityService.insertCityInfo(vo);
        	
        	hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}
    	
    	return hashmap;
    }
	
    /**
     * <pre>
     * 메인화면 삭제 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param DashboardVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/deleteCityInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> deleteCityInfo(DashboardVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		systemDetailService.deleteInstallInfo(vo);
    		systemDetailService.deleteEquipInfo(vo);
    		
    		systemService.deleteSystemInfo(vo);
    		systemService.deleteAudiInfo(vo);
    		
    		siteService.deleteSiteInfo(vo);
    		
    		areaService.deleteAreaInfo(vo);
    		
    		cityService.deleteCityInfo(vo);
        	
        	hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}
    	
    	return hashmap;
    }

  /**
   * <pre>
   * 이벤트 리스트
   * </pre>
   * @author kct
   * @since 2020. 3. 31.
   * @param DashboardVO
   * @return ModelAndView
   */
	@RequestMapping(value = "/eventList")
	public ModelAndView eventList(DashboardVO vo, ModelAndView mv) {

		try {
	    	//조회 하려는 페이지
	        int startPage = (vo.getStartPage()!=null?Integer.parseInt(vo.getStartPage().toString()):1);
	        //한페이지에 보여줄 리스트 수
	        int visiblePages = (vo.getVisiblePages()!=null?Integer.parseInt(vo.getVisiblePages().toString()):5);
	        //일단 전체 건수를 가져온다.
	        int totalCnt = cityService.eventListCnt(vo);
	        
	        //1.하단 페이지 네비게이션에서 보여줄 리스트 수를 구한다.
	        BigDecimal decimal1 = new BigDecimal(totalCnt);
	        BigDecimal decimal2 = new BigDecimal(visiblePages);
	        BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);

	        int startLimitPage = 0;
	        //2.mssql limit 범위를 구하기 위해 계산
	        if(startPage == 1){
	            startLimitPage = 0;
	        }else{
	            startLimitPage = (startPage - 1) * visiblePages;
	        }
	        
	        if(startPage > (totalCnt / visiblePages) + 1 ) {
	        	double dTemp = Math.floor(totalCnt / visiblePages);
	        	
	        	vo.setStart(Integer.toString(totalCnt - visiblePages));
	        	vo.setEnd(Integer.toString(totalCnt));
	        	mv.addObject("startPage1", (int)dTemp + 1);							//현재 페이지      
	        }else {
	        	vo.setStart(Integer.toString(startLimitPage + 1));
	            vo.setEnd(Integer.toString(startLimitPage + visiblePages));
	            mv.addObject("startPage1", startPage);								//현재 페이지      
	        }
	        
	        List<DashboardVO> list = cityService.eventList(vo);
	        
	        for(int i = 0 ; i < list.size(); i++) {
				
				switch (Integer.parseInt(list.get(i).getErrNum())) {
				case 1:
					list.get(i).setsMessage(MailMessageDef.message1); 
					break;
				case 2:
					list.get(i).setsMessage(MailMessageDef.message2);
					break;
				case 3:
					list.get(i).setsMessage(MailMessageDef.message3);
					break;
				case 4:
					list.get(i).setsMessage(MailMessageDef.message4);
					break;
				case 5:
					list.get(i).setsMessage(MailMessageDef.message5);
					break;
				case 6:
					list.get(i).setsMessage(MailMessageDef.message6);
					break;
				case 7:
					list.get(i).setsMessage(MailMessageDef.message7);
					break;
				case 8:
					list.get(i).setsMessage(MailMessageDef.message8);
					break;
				case 9:
					list.get(i).setsMessage(MailMessageDef.message9);
					break;
				case 10:
					list.get(i).setsMessage(MailMessageDef.message10);
					break;
				case 12:
					list.get(i).setsMessage(MailMessageDef.message12);
					break;
				case 13:
					list.get(i).setsMessage(MailMessageDef.message13);
					break;
				case 14:
					list.get(i).setsMessage(MailMessageDef.message14);
					break;
				case 15:
					list.get(i).setsMessage(MailMessageDef.message15);
					break;
				case 16:
					list.get(i).setsMessage(MailMessageDef.message16);
					break;
				case 18:
					list.get(i).setsMessage(MailMessageDef.message18);
					break;
				case 21:
					list.get(i).setsMessage(MailMessageDef.message21);
					break;
				case 22:
					list.get(i).setsMessage(MailMessageDef.message22);
					break;
				case 23:
					list.get(i).setsMessage(MailMessageDef.message23);
					break;
				case 24:
					list.get(i).setsMessage(MailMessageDef.message24);
					break;
				case 25:
					list.get(i).setsMessage(MailMessageDef.message25);
					break;
				case 26:
					list.get(i).setsMessage(MailMessageDef.message26);
					break;
				case 27:
					list.get(i).setsMessage(MailMessageDef.message27);
					break;
				case 28:
					list.get(i).setsMessage(MailMessageDef.message28);
					break;
				case 29:
					list.get(i).setsMessage(MailMessageDef.message29);
					break;
				case 30:
					list.get(i).setsMessage(MailMessageDef.message30);
					break;
				case 31:
					list.get(i).setsMessage(MailMessageDef.message31);
					break;
				case 32:
					list.get(i).setsMessage(MailMessageDef.message32);
					break;
				case 33:
					list.get(i).setsMessage(MailMessageDef.message33);
					break;
				case 34:
					list.get(i).setsMessage(MailMessageDef.message34);
					break;
				case 35:
					list.get(i).setsMessage(MailMessageDef.message35);
					break;
				case 36:
					list.get(i).setsMessage(MailMessageDef.message36);
					break;
				case 37:
					list.get(i).setsMessage(MailMessageDef.message37);
					break;
				case 38:
					list.get(i).setsMessage(MailMessageDef.message38);
					break;
				case 39:
					list.get(i).setsMessage(MailMessageDef.message39);
					break;
				case 40:
					list.get(i).setsMessage(MailMessageDef.message40);
					break;
				case 41:
					list.get(i).setsMessage(MailMessageDef.message41);
					break;
				case 42:
					list.get(i).setsMessage(MailMessageDef.message42);
					break;
				case 43:
					list.get(i).setsMessage(MailMessageDef.message43);
					break;
				case 44:
					list.get(i).setsMessage(MailMessageDef.message44);
					break;
				default:
					break;
				}
			}	
	        
	        mv.addObject("visiblePages1", visiblePages);								//화면에 보여줄 갯수
	        mv.addObject("totalPage1", totalPage);									//페이지 네비게이션에 보여줄 리스트 수
	        mv.addObject("eventList", list);
	        mv.setViewName("dashboard/eventList");
		} catch (Exception e) {
			e.printStackTrace();
		}

      return mv;
  }
}
