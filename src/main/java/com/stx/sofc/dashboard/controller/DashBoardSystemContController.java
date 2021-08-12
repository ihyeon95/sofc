package com.stx.sofc.dashboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stx.sofc.dashboard.service.DashBoardSystemContService;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.SystemContVO;

@Controller
@RequestMapping("/dashboard/systemCont/*")
public class DashBoardSystemContController {
	
	@Inject
	private DashBoardSystemContService service;
	
	/**
     * <pre>
     * 연료전지 제어 화면
     * </pre>
     * @author kct
     * @since 2020. 2. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/mng", method = RequestMethod.POST)
	public ModelAndView systemContMng(DashboardVO vo, ModelAndView mv) {
		
		mv.addObject("iCityNum", vo.getiCityNum());
		mv.addObject("sCityName", vo.getsCityName());
		mv.addObject("iAreaNum", vo.getiAreaNum());
		mv.addObject("sAreaName", vo.getsAreaName());
		mv.addObject("iSiteNum", vo.getiSiteNum());								
		mv.addObject("sSiteName", vo.getsSiteName());
		mv.addObject("iSysNum", vo.getiSysNum());								
		mv.addObject("sSystemName", vo.getsSystemName());
		mv.addObject("iRtuNum", vo.getiRtuNum());
		mv.addObject("iBdNum", vo.getiBdNum());
		
		SystemContVO vo1 = new SystemContVO();
		
		vo1.setRtuId(vo.getiRtuNum());
		
		try {
			mv.addObject("systemCont", service.systemContMeasure(vo1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("dashboard/systemContMng");

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
	@RequestMapping(value="/measure", method=RequestMethod.POST)
	public HashMap<String, Object> systemContMeasure(SystemContVO vo) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//차트용 데이터 
		try {
			result.put("systemCont", service.systemContMeasure(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
				
	}
		
    /**
     * <pre>
     * 엑셀 다운로드
     * </pre>
     * @author kct
     * @since 2020. 3. 31.
     * @param SystemContVO
     * @return String
     */
    @RequestMapping(value = "/downloadMeasureExcelFile", method = RequestMethod.POST)
    public String downloadExcelFile(Model model, SystemContVO vo) {
    	
    	try {
    		List<SystemContVO> list = service.systemMeasureExcelDownload(vo);
            
            SXSSFWorkbook workbook = service.excelFileDownloadProcess(list, vo.getsSystemNameExcel());
            
            model.addAttribute("locale", Locale.KOREA);
            model.addAttribute("workbook", workbook);
            model.addAttribute("workbookName", vo.getsSystemNameExcel());
            model.addAttribute("startDt", vo.getSearchRequstDeBgn().replaceAll("-", ""));
            model.addAttribute("endDt", vo.getSearchRequstDeEnd().replaceAll("-", ""));
            
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return "excelDownloadView";
    }
}
