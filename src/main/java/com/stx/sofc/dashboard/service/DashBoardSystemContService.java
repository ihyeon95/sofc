package com.stx.sofc.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.excelVo;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.stx.sofc.dashboard.vo.SystemContVO;

public interface DashBoardSystemContService {
	
	public SystemContVO systemContMeasure(SystemContVO vo) throws Exception;
	public int selectIRemoteStatus(DashboardVO vo) throws Exception;
	
	public List<SystemContVO> systemMeasureExcelDownload(SystemContVO vo) throws Exception;
	
	/**
     * 생성한 엑셀 워크북을 컨트롤레에서 받게해줄 메소
     * @param list
     * @return
     */
    public SXSSFWorkbook excelFileDownloadProcess(List<SystemContVO> list, String systemName) throws Exception;
	public SXSSFWorkbook guestExcelFileDownloadProcess(List<SystemContVO> list, String systemName, List<excelVo> excelList) throws Exception;
    
}
