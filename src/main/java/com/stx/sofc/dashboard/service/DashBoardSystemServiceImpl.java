package com.stx.sofc.dashboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.stx.sofc.dashboard.dao.DashBoardSystemDAO;
import com.stx.sofc.dashboard.vo.DashboardVO;

@Service
public class DashBoardSystemServiceImpl implements DashBoardSystemService{
	@Inject
	private DashBoardSystemDAO dao;

	
	@Override
	public DashboardVO systemMeasure(DashboardVO vo) throws Exception {

		return dao.systemMeasure(vo); 
	}
	
	@Override
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception {

		return dao.measureStatusList(vo); 
	}		
	
	@Override
	public List<DashboardVO> systemList(DashboardVO vo) throws Exception {

		return dao.systemList(vo);
	}
	
	@Override
	public int systemListCnt(DashboardVO vo) throws Exception {
		return dao.systemListCnt(vo);
	}

	@Override
	public List<DashboardVO> systemMeasureList(DashboardVO vo) throws Exception {

		return dao.systemMeasureList(vo); 
	}
	
	@Override
	public int systemEffi(DashboardVO vo) throws Exception {
		return dao.systemEffi(vo);
	}
	
	@Override
	public List<DashboardVO> systemMeasureHourList(DashboardVO vo) throws Exception {

		return dao.systemMeasureHourList(vo); 
	}	
	
	@Override
	public List<DashboardVO> audiInfo(DashboardVO vo) throws Exception {
		return dao.audiInfo(vo);
	}
	
	@Override
	public int insertAudiInfo(DashboardVO vo) throws Exception {
		return dao.insertAudiInfo(vo);
	}
	
	@Override
	public int deleteAudiInfo(DashboardVO vo) throws Exception {
		return dao.deleteAudiInfo(vo);
	}
	
	@Override
	public int insertSystemInfo(DashboardVO vo) throws Exception {
		return dao.insertSystemInfo(vo);
	}
	
	@Override
	public int deleteSystemInfo(DashboardVO vo) throws Exception {
		return dao.deleteSystemInfo(vo);
	}
	
	@Override
	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception {
		return dao.systemRtuIdList(vo);
	}
	
	@Override
	public String tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception {
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
