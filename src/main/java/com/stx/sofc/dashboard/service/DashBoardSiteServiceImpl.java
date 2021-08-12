package com.stx.sofc.dashboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.stx.sofc.dashboard.dao.DashBoardSiteDAO;
import com.stx.sofc.dashboard.vo.DashboardVO;

@Service
public class DashBoardSiteServiceImpl implements DashBoardSiteService{
	@Inject
	private DashBoardSiteDAO dao;
	
	@Override
	public List<DashboardVO> siteEffiGraph(DashboardVO vo) throws Exception {
		return dao.siteEffiGraph(vo);
	}
	
	@Override
	public DashboardVO siteMeasure(String getiRtuNum) throws Exception {

		return dao.siteMeasure(getiRtuNum);
	}
	
	@Override
	public List<DashboardVO> siteList(DashboardVO vo) throws Exception {
		return dao.siteList(vo);
	}
	
	@Override
	public List<DashboardVO> siteNameList(DashboardVO vo) throws Exception {
		return dao.siteNameList(vo);
	}
	
	@Override
	public List<DashboardVO> siteMeasureList(DashboardVO vo) throws Exception {

		return dao.siteMeasureList(vo); 
	}

	@Override
	public List<DashboardVO> siteMeasureHourList(DashboardVO vo) throws Exception {

		return dao.siteMeasureHourList(vo); 
	}	
	
	@Override
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception {

		return dao.measureStatusList(vo); 
	}		
	
	@Override
	public int insertSiteInfo(DashboardVO vo) throws Exception {
		return dao.insertSiteInfo(vo);
	}
	
	@Override
	public int deleteSiteInfo(DashboardVO vo) throws Exception {
		return dao.deleteSiteInfo(vo);
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
}
