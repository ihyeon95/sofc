package com.stx.sofc.dashboard.controller;

import com.stx.sofc.dashboard.service.*;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.dashboard.vo.excelVo;
import com.stx.sofc.dashboard.vo.guestVo;
import com.stx.sofc.login.vo.LoginVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/*")
public class DashBoardAdminController {
	
	@Inject
	private DashBoardAdminService adminService;
	
	/**
     * <pre>
     * 도시화면
     * </pre>
     * @author kct
     * @since 2020. 2. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/emailAdminPage", method = RequestMethod.POST)
	public ModelAndView adminPage(DashboardVO vo, ModelAndView mv){

		try {
//			mv.addObject("iCityNum", vo.getiCityNum());
//			mv.addObject("sCityName", vo.getsCityName());
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

			mv.addObject("emailList", adminService.emailList());


			mv.setViewName("admin/emailAdminPage");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return mv;
	}

	@RequestMapping(value = "/insertEmail", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertEmail(EmailVO vo, ModelAndView mv){

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		try {
			adminService.insertEmail(vo);
			hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}


		return hashmap;
	}

	@RequestMapping(value = "/deleteEmail", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> deleteEmail(@RequestParam(value="checkedEmailList[]") List<Integer> checkedEmailList){

		System.out.println("checkedEmail : "+checkedEmailList);

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		try {
			adminService.deleteEmail(checkedEmailList);
			hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}


		return hashmap;
	}


	/* 게스트 관리 */
	@RequestMapping(value = "/guestAdminPage", method = RequestMethod.POST)
	public ModelAndView guestAdminPage(DashboardVO vo, ModelAndView mv){

		try {
//			mv.addObject("iCityNum", vo.getiCityNum());
//			mv.addObject("sCityName", vo.getsCityName());
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
			mv.addObject("sUserId", authentication.getName());


			mv.addObject("guestList", adminService.guestList());
			mv.addObject("cityNameList", adminService.cityNameList());
//			mv.addObject("excelList", adminService.excelList());


			mv.setViewName("admin/guestAdminPage");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(value = "/areaSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<DashboardVO> areaSelect(String iCityNum){

		List<DashboardVO> areaNameList = new ArrayList<>();

		try {
			areaNameList = adminService.areaNameList(iCityNum);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return areaNameList;
	}

	@RequestMapping(value = "/siteSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<DashboardVO> siteSelect(DashboardVO vo){

		List<DashboardVO> siteNameList = new ArrayList<>();

		try {
			siteNameList = adminService.siteNameList(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return siteNameList;
	}

	@RequestMapping(value = "/insertGuestInfo", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertGuestInfo(guestVo vo, ModelAndView mv){

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		try {
			adminService.insertGuest(vo);
			adminService.insertExcel(vo);
			hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}


		return hashmap;
	}

	@RequestMapping(value = "/deleteGuest", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> deleteGuest(@RequestParam(value="checkedGuestList[]") List<String> checkedGuestList){

		System.out.println("checkedGuest : "+checkedGuestList);

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		try {
			adminService.deleteGuest(checkedGuestList);
			adminService.deleteExcel(checkedGuestList);
			hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}


		return hashmap;
	}



	@RequestMapping(value = "/excelAdminPage", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView excelAdminPage(excelVo vo, ModelAndView mv){
		try {

			mv.addObject("excelList", adminService.excelList(vo.getsUserId()));
			mv.addObject("sUserId", vo.getsUserId());


			mv.setViewName("admin/excelAdminPage");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/updateExcelInfo", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateExcelInfo(excelVo vo, ModelAndView mv){

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		try {
			adminService.updateExcel(vo);
			hashmap.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			hashmap.put("result", "fail");
		}


		return hashmap;
	}
}
