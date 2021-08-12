package com.stx.sofc.dashboard.service;

import java.util.List;

import com.stx.sofc.dashboard.vo.DashboardVO;

public interface DashBoardSystemService {
	
	public DashboardVO systemMeasure(String getiRtuNum) throws Exception;
	
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemList(DashboardVO vo) throws Exception;
	
	public int systemListCnt(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemMeasureList(DashboardVO vo) throws Exception;
	
	public int systemEffi(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemMeasureHourList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> audiInfo(DashboardVO vo) throws Exception;

	public int insertAudiInfo(DashboardVO vo) throws Exception;
	
	public int deleteAudiInfo(DashboardVO vo) throws Exception;
	
	public int insertSystemInfo(DashboardVO vo) throws Exception;
	
	public int deleteSystemInfo(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception;

	public String tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String tdFLAvg(DashboardVO vo) throws Exception;
	
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String adFLAvg(DashboardVO vo) throws Exception;
	
}
