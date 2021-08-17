package com.stx.sofc.dashboard.controller;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stx.sofc.dashboard.service.DashBoardSiteService;
import com.stx.sofc.dashboard.service.DashBoardSystemDetailService;
import com.stx.sofc.dashboard.service.DashBoardSystemService;
import com.stx.sofc.dashboard.vo.DashboardVO;

@Controller
@RequestMapping("/dashboard/site/*")
public class DashBoardSiteController {
	
	@Inject
	private DashBoardSiteService siteService;
	
	@Inject
	private DashBoardSystemService systemService;
	
	@Inject
	private DashBoardSystemDetailService systemDetailService;
	
	/**
     * <pre>
     * 지역화면
     * </pre>
     * @author kct
     * @since 2020. 2. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/mng", method = RequestMethod.POST)
	public ModelAndView siteMng(DashboardVO vo, ModelAndView mv) {
		
		mv.addObject("iCityNum", vo.getiCityNum());
		mv.addObject("sCityName", vo.getsCityName());
		mv.addObject("iAreaNum", vo.getiAreaNum());
		mv.addObject("sAreaName", vo.getsAreaName());
		        
		mv.setViewName("dashboard/siteMng");
		
		return mv;
	}
	
	/**
     * <pre>
     * 지역화면 공장화면 발전기 리스트
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param DashboardVO
     * @return ModelAndView
     */
	@RequestMapping(value = "/list")
	public ModelAndView siteList(DashboardVO vo, ModelAndView mv) {
	
		try {
			//조회 하려는 페이지
		    int startPage = (vo.getStartPage()!=null?Integer.parseInt(vo.getStartPage().toString()):1);
		    //한페이지에 보여줄 리스트 수
		    int visiblePages = (vo.getVisiblePages()!=null?Integer.parseInt(vo.getVisiblePages().toString()):6);
		    //일단 전체 건수를 가져온다.
		    int totalCnt = siteService.siteNameList(vo).size();
		    
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
			List<DashboardVO> siteList = siteService.siteList(vo);
			siteList = siteList.stream().sorted(Comparator.comparing(DashboardVO::getsSiteName)).collect(Collectors.toList());
			mv.addObject("siteList", siteList);
		    mv.setViewName("dashboard/siteList");
			
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
			
			List<DashboardVO> siteRtuIdList = siteService.systemRtuIdList(vo);

			// 병렬로 쿼리 실행
			List<DashboardVO> threadMeasureList = new ArrayList<>();
			threadMeasureList = threadMeasure(vo, threadMeasureList, siteRtuIdList);

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
				
				wattSubProduceInfo.put("name", "SITE");
				
				bigTemp = txtCumulativeWattHour[i];
				bigTemp = bigTemp.divide(bigTemp1, 2, BigDecimal.ROUND_HALF_UP);
				
				wattSubProduceArray1.add(bigTemp);
				wattSubProduceInfo.put("data", wattSubProduceArray1);
				
				wattSubProduceArray.add(wattSubProduceInfo);
			}
			
			List<DashboardVO> siteEffiGraph = siteService.siteEffiGraph(vo);

			siteEffiGraph = siteEffiGraph.stream().sorted(Comparator.comparing(DashboardVO::getsSiteName)).collect(Collectors.toList());
			
			for (DashboardVO effiGraph : siteEffiGraph) {
				//발전 효율 차트용 데이터
				measureObjArray.add(effiGraph.getsSiteName());
				measureAmtArray.add(Math.round(effiGraph.getTdEfficiency().doubleValue()));
			}
			
			DashboardVO measure = new DashboardVO();
			
			BigDecimal bSize = BigDecimal.valueOf(siteRtuIdList.size());
			
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
			
			result.put("systemCnt", siteRtuIdList.size());
			
			result.put("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return result;
	}

	public List<DashboardVO> threadMeasure(DashboardVO vo, List<DashboardVO> threadMeasureList, List<DashboardVO> siteRtuIdList) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(siteRtuIdList.size());

		for (int i = 0; i < siteRtuIdList.size(); i++) {
			final int jobId = i;

			// ThreadPoolExecutor에 Task를 예약하면, 여유가 있을 때 Task를 수행합니다.
			executor.execute(() -> {
				try {
//					TimeUnit.MILLISECONDS.sleep(100);
					String threadName = Thread.currentThread().getName();
					DashboardVO tmpVO = siteRtuIdList.get(jobId);
					String getiRtuNum = tmpVO.getiRtuNum();
//					System.out.println("getiRtuNum : "+getiRtuNum);
					vo.setiRtuNum(tmpVO.getiRtuNum());
					DashboardVO dbvo = siteService.siteMeasure(getiRtuNum);

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
     * 시스템 추가 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param DashboardVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/insertSiteInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertSiteInfo(DashboardVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		siteService.insertSiteInfo(vo);
        	
        	hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}
    	
    	return hashmap;
    }
	
    /**
     * <pre>
     * 시스템 삭제 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param DashboardVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/deleteSiteInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> deleteSiteInfo(DashboardVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		systemDetailService.deleteInstallInfo(vo);
    		systemDetailService.deleteEquipInfo(vo);
    		
    		systemService.deleteSystemInfo(vo);
    		systemService.deleteAudiInfo(vo);
    		
    		siteService.deleteSiteInfo(vo);
        	
        	hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}
    	
    	return hashmap;
    }
}
