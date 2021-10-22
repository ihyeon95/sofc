package com.stx.sofc.dashboard.service;

import com.stx.sofc.dashboard.dao.DashBoardAdminDAO;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.dashboard.vo.excelVo;
import com.stx.sofc.dashboard.vo.guestVo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
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

	@Override
	public int deleteEmail(List<Integer> checkedEmailList) throws Exception {

		return dao.deleteEmail(checkedEmailList);
	}

	@Override
	public List<guestVo> guestList() throws Exception{
		return dao.guestList();
	}

	@Override
	public int insertGuest(guestVo vo) throws Exception{
		return dao.insertGuest(vo);
	}

	@Override
	public int insertExcel(guestVo vo) throws Exception{
		return dao.insertExcel(vo);
	}

	@Override
	public int deleteGuest(List<String> checkedGuestList) throws Exception{
		return dao.deleteGuest(checkedGuestList);
	}

	@Override
	public List<excelVo> excelList(String sUserId) throws Exception{
		return dao.excelList(sUserId);
	}

	@Override
	public int updateExcel(excelVo vo) throws Exception{
		return dao.updateExcel(vo);
	}

	@Override
	public List<DashboardVO> cityNameList() throws Exception{
		return dao.cityNameList();
	}

	@Override
	public List<DashboardVO> areaNameList(String iCityNum) throws Exception{
		return dao.areaNameList(iCityNum);
	}

	@Override
	public List<DashboardVO> siteNameList(DashboardVO vo) throws Exception{
		return dao.siteNameList(vo);
	}
}
