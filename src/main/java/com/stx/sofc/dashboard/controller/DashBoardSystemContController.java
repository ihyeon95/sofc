package com.stx.sofc.dashboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import com.stx.sofc.dashboard.service.DashBoardAdminService;
import com.stx.sofc.dashboard.vo.excelVo;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Inject
	private DashBoardAdminService adminService;
	
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

	@ResponseBody
	@RequestMapping(value="/selectIRemoteStatus", method=RequestMethod.POST)
	public HashMap<String, Object> selectIRemoteStatus(DashboardVO vo) {

		HashMap<String, Object> result = new HashMap<String, Object>();

//		DashboardVO vo2 = new DashboardVO();
//		vo2.setiSysNum(vo.getiSysNum());
//		vo2.setiCityNum(vo.getiCityNum());
//		vo2.setiAreaNum(vo.getiAreaNum());
//		vo2.setiSiteNum(vo.getiSiteNum());
//		vo2.setiRtuNum(vo.getiRtuNum());
//		vo2.setiBdNum(vo.getiBdNum());

		//차트용 데이터
		try {
			result.put("iRemoteStatus", service.selectIRemoteStatus(vo));
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
			System.out.println("list 가져오기 시작");
    		List<SystemContVO> list = service.systemMeasureExcelDownload(vo);
			System.out.println("list 가져오는거 종료");
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

	/**
	 * <pre>
	 * 게스트 엑셀 다운로드
	 * </pre>
	 * @author ihy
	 * @since 2020. 3. 31.
	 * @param SystemContVO
	 * @return String
	 */
	@RequestMapping(value = "/guestDownloadMeasureExcelFile", method = RequestMethod.POST)
	public String guestDownloadMeasureExcelFile(Model model, SystemContVO vo) {

		try {
			//세션에 있는 로그인 정보 조회
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			List<SystemContVO> list = service.systemMeasureExcelDownload(vo);
			List<excelVo> excelList = adminService.excelList(authentication.getName());

			SXSSFWorkbook workbook = service.guestExcelFileDownloadProcess(list, vo.getsSystemNameExcel(), excelList);

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
