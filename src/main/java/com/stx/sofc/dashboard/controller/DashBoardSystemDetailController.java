package com.stx.sofc.dashboard.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

import com.stx.sofc.dashboard.service.DashBoardSystemDetailService;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EquipInfoVO;
import com.stx.sofc.dashboard.vo.InstallInfoVO;

@Controller
@RequestMapping("/dashboard/systemDetail/*")
public class DashBoardSystemDetailController {
	
	@Inject
	private DashBoardSystemDetailService systemDetailService;
	
	
	/**
     * <pre>
     * 시스템 화면
     * </pre>
     * @author kct
     * @since 2020. 2. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/mng", method = RequestMethod.POST)
	public ModelAndView systemDetailMng(DashboardVO vo, ModelAndView mv) {
	  	
		String sAuth = "";
		
		//세션에 있는 로그인 정보 조회
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
		Iterator<? extends GrantedAuthority> iter = authorities.iterator(); 
		
		while (iter.hasNext()) { 
			GrantedAuthority auth = iter.next();
			sAuth = auth.getAuthority();
		}
		
		mv.addObject("iCityNum", vo.getiCityNum());
		mv.addObject("sCityName", vo.getsCityName());
		mv.addObject("iAreaNum", vo.getiAreaNum());
		mv.addObject("sAreaName", vo.getsAreaName());
		mv.addObject("iSiteNum", vo.getiSiteNum());								
		mv.addObject("sSiteName", vo.getsSiteName());
		mv.addObject("sAuth", sAuth);
		mv.addObject("iSysNum", vo.getiSysNum());								
		mv.addObject("sSystemName", vo.getsSystemName());
		mv.addObject("iRtuNum", vo.getiRtuNum());
		mv.addObject("iBdNum", vo.getiBdNum());
        
		mv.setViewName("dashboard/systemDetailMng");
		
		return mv;
	}
	
	/**
     * <pre>
     * 시스템화면 발전기 측정값 조회
     * </pre>
     * @author kct
     * @since 2020. 3. 11.
     * @param DashboardVO
     * @return HashMap
	 * @throws IOException 
     */
	@ResponseBody
	@RequestMapping(value="/measureList", method=RequestMethod.POST)
	public HashMap<String, Object> systemDetailMeasureList(DashboardVO vo) {
		
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
		
		try {
			
			DashboardVO dbvo = systemDetailService.systemDetailMeasure(vo);
			
			result.put("measure", dbvo);
			
			//발전 효율 차트용 데이터
			measureObjArray.add(dbvo.getsSystemName());
			measureAmtArray.add(Math.floor(dbvo.getTdEfficiency().doubleValue()));
			
			result.put("effiChartObjJson", measureObjArray);
			result.put("effiChartAmtJson", measureAmtArray);
			
			
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
			
			
			for(int i = 0 ; i < 24 ; i++) {
				wattProduceHourArray.add(i);
			}
			
			String txtCumulativeWattHour = dbvo.getTxtCumulativeWattHour().replaceAll("[\\{\\}]", "");
			String sTemp[] = txtCumulativeWattHour.split(",");
			
			if(sTemp.length == 24) {
				for(int i = 0 ; i < sTemp.length ; i++) {
					
					BigDecimal bigTemp;
					BigDecimal bigTemp1 = BigDecimal.valueOf(1000);
					
					wattSubProduceInfo.put("name", dbvo.getsSystemName());
					
					bigTemp = BigDecimal.valueOf(Double.parseDouble(sTemp[i]));
					bigTemp = bigTemp.divide(bigTemp1, 2, BigDecimal.ROUND_HALF_UP);
					
					wattSubProduceArray1.add(bigTemp);
					wattSubProduceInfo.put("data", wattSubProduceArray1);
					
					wattSubProduceArray.add(wattSubProduceInfo);
				}
			}
			
			result.put("wattSubProduceArray", wattSubProduceArray);
			result.put("wattProduceHourArray", wattProduceHourArray);
			
			result.put("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return result;
	}
	
	/**
     * <pre>
     * 발전원 설치정보 존재 여부 조회
     * </pre>
     * @author kct
     * @since 2020. 3. 11.
     * @param DashboardVO
     * @return HashMap
     */
	@ResponseBody
	@RequestMapping(value="/installInfoConfirm", method=RequestMethod.POST)
	public HashMap<String, Object> installInfoConfirm(InstallInfoVO vo) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {

			List<InstallInfoVO> voList = systemDetailService.installInfo(vo);
			
			result.put("installInfo", voList);
			result.put("installInfoCnt", voList.size());
			result.put("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
     * <pre>
     * 발전원 설치정보 입력 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param InstallInfoVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/insertInstallInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertInstallInfo(InstallInfoVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		systemDetailService.insertInstallInfo(vo);
    		hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}
    	
    	return hashmap;
    }
	
    /**
     * <pre>
     * 발전 설비 제원 존재 여부 조회
     * </pre>
     * @author kct
     * @since 2020. 3. 11.
     * @param DashboardVO
     * @return HashMap
     */
	@ResponseBody
	@RequestMapping(value="/equipInfoConfirm", method=RequestMethod.POST)
	public HashMap<String, Object> equipInfoConfirm(EquipInfoVO vo) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {

			List<EquipInfoVO> voList = systemDetailService.equipInfo(vo);
			
			result.put("equipInfo", voList);
			result.put("equipInfoCnt", voList.size());
			result.put("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
     * <pre>
     * 발전 설비 제원 입력 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param EquipInfoVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/insertEquipInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertEquipInfo(EquipInfoVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		systemDetailService.insertEquipInfo(vo);
    		hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}

    	return hashmap;
    }
	
    /**
     * <pre>
     * 발전 설비 제원 존재 여부 조회
     * </pre>
     * @author kct
     * @since 2020. 3. 11.
     * @param DashboardVO
     * @return HashMap
     */
	@ResponseBody
	@RequestMapping(value="/systemInfoConfirm", method=RequestMethod.POST)
	public HashMap<String, Object> systemInfoConfirm(DashboardVO vo) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {

			List<DashboardVO> voList = systemDetailService.systemInfo(vo);
			
			result.put("systemInfo", voList);
			result.put("systemInfoCnt", voList.size());
			result.put("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
     * <pre>
     * 발전 설비 제원 입력 기능
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param EquipInfoVO
     * @return HashMap<String, Object> 재배치 결과에 대한 응답(success/fail 정보)
     */
    @RequestMapping(value = "/updateSystemInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> updateSystemInfo(DashboardVO vo, Model model) {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	try {
    		systemDetailService.updateSystemInfo(vo);
    		hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}

    	return hashmap;
    }
}
