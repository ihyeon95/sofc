package com.stx.sofc.dashboard.dao;

import com.stx.sofc.dashboard.vo.EmailVO;

import java.util.ArrayList;
import java.util.List;

public interface DashBoardAdminDAO {
	public List<EmailVO> emailList() throws Exception;

	public int insertEmail(EmailVO vo) throws Exception;

	public int deleteEmail(List<Integer> checkedEmailList) throws Exception;
}
