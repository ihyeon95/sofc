package com.stx.sofc.dashboard.service;

import com.stx.sofc.dashboard.vo.EmailVO;

import java.util.List;

public interface DashBoardAdminService {
	public List<EmailVO> emailList() throws Exception;

	public int insertEmail(EmailVO vo) throws Exception;
}
