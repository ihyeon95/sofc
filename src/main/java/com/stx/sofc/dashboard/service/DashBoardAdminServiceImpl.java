package com.stx.sofc.dashboard.service;

import com.stx.sofc.dashboard.dao.DashBoardAdminDAO;
import com.stx.sofc.dashboard.vo.EmailVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class DashBoardAdminServiceImpl implements DashBoardAdminService{
	@Inject
	private DashBoardAdminDAO dao;

	@Override
	public List<EmailVO> emailList() throws Exception {

		return dao.emailList();
	}

	@Override
	public int insertEmail(EmailVO vo) throws Exception {

		return dao.insertEmail(vo);
	}
}
