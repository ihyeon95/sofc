package com.stx.sofc.dashboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.stx.sofc.dashboard.dao.DashBoardCityDAO;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.login.vo.LoginVO;

@Service
public class DashBoardCityServiceImpl implements DashBoardCityService{
	@Inject
	private DashBoardCityDAO dao;

	@Override
	public DashboardVO mainSwitch(LoginVO vo) throws Exception {
		return dao.mainSwitch(vo);
	}
	
	
	@Override
	public List<DashboardVO> cityEffiGraph(DashboardVO vo) throws Exception {
		return dao.cityEffiGraph(vo);
	}
	
	@Override
	public DashboardVO cityMeasure(DashboardVO vo) throws Exception {
		return dao.cityMeasure(vo);
	}
	
	@Override
	public List<DashboardVO> cityList(DashboardVO vo) throws Exception {
		return dao.cityList(vo);
	}
	
	@Override
	public List<DashboardVO> cityNameList(DashboardVO vo) throws Exception {
		return dao.cityNameList(vo);
	}
	
	@Override
	public List<DashboardVO> cityMeasureList(DashboardVO vo) throws Exception {
		return dao.cityMeasureList(vo); 
	}	
	
	@Override
	public List<DashboardVO> cityMeasureHourList(DashboardVO vo) throws Exception {

		return dao.cityMeasureHourList(vo); 
	}
	
	@Override
	public int insertCityInfo(DashboardVO vo) throws Exception {
		return dao.insertCityInfo(vo);
	}
	
	@Override
	public int deleteCityInfo(DashboardVO vo) throws Exception {
		return dao.deleteCityInfo(vo);
	}
		
	@Override
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception {

		return dao.measureStatusList(vo); 
	}		
	
	@Override
	public int systemEffi(DashboardVO vo) throws Exception {
		return dao.systemEffi(vo);
	}
			
	
	@Override
	public int systemCnt(DashboardVO vo) throws Exception {
		return dao.systemCnt(vo);
	}

	@Override
	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception {
		return dao.systemRtuIdList(vo);
	}
	
	@Override
	public DashboardVO tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception {
		return dao.tdAccumulateWattProduceAvg(vo); 
	}

	@Override
	public String tdFLAvg(DashboardVO vo) throws Exception {
		return dao.tdFLAvg(vo);
	}
	
	@Override
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception {
		return dao.adAccumulateWattProduceAvg(vo); 
	}
	
	@Override
	public String adFLAvg(DashboardVO vo) throws Exception {
		return dao.adFLAvg(vo);
	}
	

	@Override
	public List<DashboardVO> eventList(DashboardVO vo) throws Exception {

		return dao.eventList(vo);
	}
	
	@Override
	public int eventListCnt(DashboardVO vo) throws Exception {
		return dao.eventListCnt(vo);
	}
		
	@Override
	public List<DashboardVO> eventAlarmList() throws Exception {

		return dao.eventAlarmList();
	}	
	
	@Override
	public int updateEventAlarm(DashboardVO vo) throws Exception {
		return dao.updateEventAlarm(vo);
	}
	
	@Override
	public List<EmailVO> emailList() throws Exception {

		return dao.emailList();
	}
	
	@Override
	public DashboardVO eventSystemPath(String iRtuNum) throws Exception {

		return dao.eventSystemPath(iRtuNum);
	}
}
