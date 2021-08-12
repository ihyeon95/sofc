package com.stx.sofc.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.stx.sofc.login.vo.LoginVO;

@Service 
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private SqlSession sqlSession;

	private static String namespace = "com.stx.sofc.mappers.login";
	
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlSession = sqlsession;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			//System.out.println("start!!!");
			
			// Password 체크 로직이 필요함
			String USER_ID = (String) authentication.getPrincipal();
			String USER_PW = (String) authentication.getCredentials();
			String reqip = "0.0.0.0";

			// Request 방식으로 가져오기 Web.xml 에 Listner
			// org.springframework.web.context.request.RequestContextListener 가 있어야 사용가능함
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			HttpSession session = request.getSession(true);
			
			LoginVO vo = new LoginVO();
			
			vo.setsUserId(USER_ID);
			vo.setsUserPassword(USER_PW);
			
			int intLoginCnt = sqlSession.selectOne(namespace + ".authInfoCnt", vo);
			
			if (intLoginCnt == 0) {
				
//				System.out.println("가입된 회원을 찾을 수 없습니다. 서비스 가입을 먼저 진행하여 주십시오.");
				throw new BadCredentialsException("가입된 회원을 찾을 수 없습니다. 서비스 가입을 먼저 진행하여 주십시오.");
			}

			int intLoginPwdCnt = sqlSession.selectOne(namespace + ".authInfoPwdCnt", vo);
			
			if (intLoginPwdCnt == 0) {
				
//				System.out.println("비밀번호가 일치하지 않습니다. 비밀번호 확인 후 다시 시도해 주십시오.");
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다. 비밀번호 확인 후 다시 시도해 주십시오.");
			}
			
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			
			vo = sqlSession.selectOne(namespace + ".loginProcess", vo);
			
			if(vo.getbUserAuth().equals("1") ) {
				
				//System.out.println("role_su get");
				
				roles.add(new SimpleGrantedAuthority("ROLE_SU"));
			}else if(vo.getbUserAuth().equals("0")) {
				
				//System.out.println("role_user get");
				
				roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			}
			
			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(USER_ID, USER_PW, roles);
			
			return result;
			
		} catch (DataAccessException e) {
			throw new BadCredentialsException(" DB 연결오류");
		} catch (Exception ex) {
			throw new BadCredentialsException(ex.toString());

		}
		
		
//		return authentication;
	}

	
}

