package com.stx.sofc.dashboard.service;

import java.util.List;

import com.stx.sofc.dashboard.vo.DashboardVO;

public interface DashBoardSiteService {
	
	public List<DashboardVO> siteEffiGraph(DashboardVO vo) throws Exception;
	
	public DashboardVO siteMeasure(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> siteList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> siteNameList(DashboardVO vo) throws Exception;
		
	public List<DashboardVO> siteMeasureList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> siteMeasureHourList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception;
	
	public int insertSiteInfo(DashboardVO vo) throws Exception;
	
	public int deleteSiteInfo(DashboardVO vo) throws Exception;
	
	public int systemEffi(DashboardVO vo) throws Exception;
	
    public int systemCnt(DashboardVO vo) throws Exception;
    
    public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception;

	public DashboardVO tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String tdFLAvg(DashboardVO vo) throws Exception;
	
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String adFLAvg(DashboardVO vo) throws Exception;
}
