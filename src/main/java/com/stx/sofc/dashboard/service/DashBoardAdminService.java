package com.stx.sofc.dashboard.service;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.dashboard.vo.excelVo;
import com.stx.sofc.dashboard.vo.guestVo;

import java.util.ArrayList;
import java.util.List;

public interface DashBoardAdminService {
	public List<EmailVO> emailList() throws Exception;

	public int insertEmail(EmailVO vo) throws Exception;

	public int deleteEmail(List<Integer> checkedEmailList) throws Exception;

	public List<guestVo> guestList() throws Exception;
	public int insertGuest(guestVo vo) throws Exception;
	public int deleteGuest(List<String> checkedGuestList) throws Exception;

	public List<excelVo> excelList() throws Exception;
	public List<DashboardVO> cityNameList() throws Exception;
	public List<DashboardVO> areaNameList(String iCityNum) throws Exception;
	public List<DashboardVO> siteNameList(DashboardVO vo) throws Exception;
}
