package com.stx.sofc.dashboard.controller;

import com.stx.sofc.dashboard.service.*;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
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
	@RequestMapping(value = "/adminPage", method = RequestMethod.POST)
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


			mv.setViewName("admin/adminPage");
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
}
