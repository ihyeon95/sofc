package com.stx.sofc.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stx.sofc.login.service.LoginService;
import com.stx.sofc.util.mail.MailSendUtil;

@Controller
@RequestMapping("/*")
public class LoginController {
	
	@Autowired
	LoginService service;

	@Autowired
	private MailSendUtil mailSendUtil;
	/**
     * <pre>
     * 로그인 화면
     * </pre>
     * @author kct
     * @since 2020. 3. 19.
     * @param ModelAndView
     * @return ModelAndView
     */
	@RequestMapping(value = "/loginPage")
	public ModelAndView login(ModelAndView mv) throws Exception {
	  		
		 mv.setViewName("login/loginPage");
//		 service.sendTcpData();		// for test
		 
//		 mailSendUtil.sendMail("kct7302@gmail.com", "kct7302@gmail.com", "test", "test입니다.");
		 
		 return mv;
	}
	
	@RequestMapping(value = "/loginFailPage")
    public String loginFailPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	return "/login/loginFailPage";
    }
	
	@RequestMapping(value = "/accessDeniedPage")
    public String accessDeniedPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	return "/login/accessDeniedPage";
    }
	
	@RequestMapping(value = "/logoutPage")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(auth != null) {
        	// 세션정보 요청
    		new SecurityContextLogoutHandler().logout(request, response, auth);
    	}
 
    	return "/login/logoutPage";
    }
}
