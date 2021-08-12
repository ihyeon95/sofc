package com.stx.sofc.dashboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.stx.sofc.dashboard.dao.DashBoardAreaDAO;
import com.stx.sofc.dashboard.vo.DashboardVO;

@Service
public class DashBoardAreaServiceImpl implements DashBoardAreaService{
	@Inject
	private DashBoardAreaDAO dao;

	@Override
	public List<DashboardVO> areaEffiGraph(DashboardVO vo) throws Exception {
		return dao.areaEffiGraph(vo);
	}
	
	@Override
	public DashboardVO areaMeasure(DashboardVO vo) throws Exception {
		return dao.areaMeasure(vo); 
	}
	
	@Override
	public List<DashboardVO> areaList(DashboardVO vo) throws Exception {
		return dao.areaList(vo);
	}
	
	@Override
	public List<DashboardVO> areaNameList(DashboardVO vo) throws Exception {
		return dao.areaNameList(vo);
	}
	
	@Override
	public List<DashboardVO> areaMeasureList(DashboardVO vo) throws Exception {
		return dao.areaMeasureList(vo); 
	}
	
	@Override
	public List<DashboardVO> areaMeasureHourList(DashboardVO vo) throws Exception {

		return dao.areaMeasureHourList(vo); 
	}	
	
	@Override
	public int insertAreaInfo(DashboardVO vo) throws Exception {
		return dao.insertAreaInfo(vo);
	}
	
	@Override
	public int deleteAreaInfo(DashboardVO vo) throws Exception {
		return dao.deleteAreaInfo(vo);
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
}
