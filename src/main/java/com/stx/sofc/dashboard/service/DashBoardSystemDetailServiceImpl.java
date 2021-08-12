package com.stx.sofc.dashboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.stx.sofc.dashboard.dao.DashBoardSystemDetailDAO;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EquipInfoVO;
import com.stx.sofc.dashboard.vo.InstallInfoVO;

@Service
public class DashBoardSystemDetailServiceImpl implements DashBoardSystemDetailService{
	@Inject
	private DashBoardSystemDetailDAO dao;

//	@Override
//	public String systemDetailEffi(DashboardVO vo) throws Exception {
//		return dao.systemDetailEffi(vo);
//	}
	
	
	@Override
	public DashboardVO systemDetailMeasure(DashboardVO vo) throws Exception {

		return dao.systemDetailMeasure(vo); 
	}
	
	@Override
	public List<DashboardVO> systemDetailMeasureList(DashboardVO vo) throws Exception {

		return dao.systemDetailMeasureList(vo); 
	}
	
	@Override
	public List<DashboardVO> systemDetailMeasureHourList(DashboardVO vo) throws Exception {

		return dao.systemDetailMeasureHourList(vo); 
	}	
		
	@Override
	public List<InstallInfoVO> installInfo(InstallInfoVO vo) throws Exception {
		return dao.installInfo(vo);
	}
	
	@Override
	public int insertInstallInfo(InstallInfoVO vo) throws Exception {
		return dao.insertInstallInfo(vo);
	}

	@Override
	public List<EquipInfoVO> equipInfo(EquipInfoVO vo) throws Exception {
		return dao.equipInfo(vo);
	}
	
	@Override
	public int insertEquipInfo(EquipInfoVO vo) throws Exception {
		return dao.insertEquipInfo(vo);
	}
	
	@Override
	public int deleteInstallInfo(DashboardVO vo) throws Exception {
		return dao.deleteInstallInfo(vo);
	}
	
	@Override
	public int deleteEquipInfo(DashboardVO vo) throws Exception {
		return dao.deleteEquipInfo(vo);
	}
	
	@Override
	public List<DashboardVO> systemInfo(DashboardVO vo) throws Exception {
		return dao.systemInfo(vo);
	}
	
	@Override
	public int updateSystemInfo(DashboardVO vo) throws Exception {
		return dao.updateSystemInfo(vo);
	}
	
	@Override
	public int systemCnt(DashboardVO vo) throws Exception {
		return dao.systemCnt(vo);
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
