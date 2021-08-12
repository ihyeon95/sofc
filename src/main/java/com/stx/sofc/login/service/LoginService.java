package com.stx.sofc.login.service;

import com.stx.sofc.login.vo.LoginVO;

public interface LoginService {
	
	public int authInfoCnt(LoginVO vo) throws Exception;
	
	public int authInfoPwdCnt(LoginVO vo) throws Exception;

	public LoginVO loginProcess(LoginVO vo) throws Exception;
	
	// temp
	public void sendTcpData() throws Exception;
	
}
