package com.stx.sofc.dashboard.dao;

import java.util.List;

import com.stx.sofc.dashboard.vo.DashboardVO;

public interface DashBoardAreaDAO {

	public List<DashboardVO> areaEffiGraph(DashboardVO vo) throws Exception;
	
	public DashboardVO areaMeasure(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> areaList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> areaNameList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> areaMeasureList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> areaMeasureHourList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception;
	
	public int insertAreaInfo(DashboardVO vo) throws Exception;
	
	public int deleteAreaInfo(DashboardVO vo) throws Exception;
	
	public int systemEffi(DashboardVO vo) throws Exception;
	
	public int systemCnt(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception;
	
	public DashboardVO tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String tdFLAvg(DashboardVO vo) throws Exception;
	
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String adFLAvg(DashboardVO vo) throws Exception;
	
}
