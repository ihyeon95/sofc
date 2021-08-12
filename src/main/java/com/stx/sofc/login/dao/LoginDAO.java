package com.stx.sofc.login.dao;

import com.stx.sofc.login.vo.LoginVO;



public interface LoginDAO {

	public int authInfoCnt(LoginVO vo) throws Exception;
	
	public int authInfoPwdCnt(LoginVO vo) throws Exception;
	
	public LoginVO loginProcess(LoginVO vo) throws Exception;
	
}
