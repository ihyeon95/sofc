package com.stx.sofc.dashboard.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.SystemContVO;



public interface DashBoardSystemContDAO {

	public SystemContVO systemContMeasure(SystemContVO vo) throws Exception;

	public int selectIRemoteStatus(DashboardVO vo) throws Exception;
	
	public List<SystemContVO> systemMeasureExcelDownload(SystemContVO vo) throws Exception;

	public List<Map<String, BigDecimal>> selectPreAccumulateWattProduce(SystemContVO vo) throws Exception;
	
}
