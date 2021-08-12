package com.stx.sofc.dashboard.service;

import java.util.List;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.login.vo.LoginVO;

public interface DashBoardCityService {

	public DashboardVO mainSwitch(LoginVO vo) throws Exception;
	
	public List<DashboardVO> cityEffiGraph(DashboardVO vo) throws Exception;
	
	public DashboardVO cityMeasure(String getiRtuNum) throws Exception;
	
	public List<DashboardVO> cityList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> cityNameList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> cityMeasureList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> cityMeasureHourList(DashboardVO vo) throws Exception;
	
	public int insertCityInfo(DashboardVO vo) throws Exception;
	
	public int deleteCityInfo(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception;
	
	public int systemEffi(DashboardVO vo) throws Exception;
	
	public int systemCnt(DashboardVO vo) throws Exception;

	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception;

	public DashboardVO tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String tdFLAvg(DashboardVO vo) throws Exception;
	
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String adFLAvg(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> eventList(DashboardVO vo) throws Exception;
	
	public int eventListCnt(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> eventAlarmList() throws Exception;

	public int eventAlarmToday(DashboardVO vo) throws Exception;

	public List<EmailVO> emailList() throws Exception;
	
	public int updateEventAlarm(DashboardVO vo) throws Exception;
	
	public DashboardVO eventSystemPath(String iRtuNum) throws Exception;
	
}
