package com.stx.sofc.dashboard.dao;

import java.util.List;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EquipInfoVO;
import com.stx.sofc.dashboard.vo.InstallInfoVO;

public interface DashBoardSystemDetailDAO {

//	public String systemDetailEffi(DashboardVO vo) throws Exception;
	
	public DashboardVO systemDetailMeasure(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemDetailMeasureList(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemDetailMeasureHourList(DashboardVO vo) throws Exception;
	
	public List<InstallInfoVO> installInfo(InstallInfoVO vo) throws Exception;
	
	public int insertInstallInfo(InstallInfoVO vo) throws Exception;

	public int deleteInstallInfo(DashboardVO vo) throws Exception;
	
	public List<EquipInfoVO> equipInfo(EquipInfoVO vo) throws Exception;
	
	public int insertEquipInfo(EquipInfoVO vo) throws Exception;
	
	public int deleteEquipInfo(DashboardVO vo) throws Exception;
	
	public List<DashboardVO> systemInfo(DashboardVO vo) throws Exception;
	
	public int updateSystemInfo(DashboardVO vo) throws Exception;
	
	public int systemCnt(DashboardVO vo) throws Exception;
	
	public String tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String tdFLAvg(DashboardVO vo) throws Exception;
	
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception;
	
	public String adFLAvg(DashboardVO vo) throws Exception;
	
}
