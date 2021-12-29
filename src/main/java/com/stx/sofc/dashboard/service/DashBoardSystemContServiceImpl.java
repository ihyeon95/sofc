package com.stx.sofc.dashboard.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.inject.Inject;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.excelVo;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.stx.sofc.dashboard.dao.DashBoardSystemContDAO;
import com.stx.sofc.dashboard.vo.SystemContVO;

@Service
public class DashBoardSystemContServiceImpl implements DashBoardSystemContService{
	@Inject
	private DashBoardSystemContDAO dao;
	
	@Override
	public SystemContVO systemContMeasure(SystemContVO vo) throws Exception {
		return dao.systemContMeasure(vo);
	}

	@Override
	public int selectIRemoteStatus(DashboardVO vo) throws Exception {
		return dao.selectIRemoteStatus(vo);
	}
	
	@Override
	public List<SystemContVO> systemMeasureExcelDownload(SystemContVO vo) throws Exception {
		return dao.systemMeasureExcelDownload(vo);
	}

	@Override
	public List<Map<String,BigDecimal>> selectPreAccumulateWattProduce(SystemContVO vo) throws Exception {
		return dao.selectPreAccumulateWattProduce(vo);
	}

	
	/**
     * 생성한 엑셀 워크북을 컨트롤레에서 받게해줄 메소
     * @param list
     * @return
     */
	@Override
    public SXSSFWorkbook excelFileDownloadProcess(List<SystemContVO> list,Map<String, BigDecimal> preAccumulateWattProduceMap, String systemName) throws ParseException {
        return this.makeSystemMeasureExcelWorkbook(list, preAccumulateWattProduceMap, systemName);
    }

	@Override
	public SXSSFWorkbook guestExcelFileDownloadProcess(List<SystemContVO> list, Map<String, BigDecimal> preAccumulateWattProduceMap, String systemName, List<excelVo> excelList) throws ParseException{
		return this.makeSystemMeasureExcelWorkbook(list, preAccumulateWattProduceMap, systemName, excelList);
	}

	/**
	 * 리스트를 간단한 엑셀 워크북 객체로 생성
	 * @param list
	 * @return 생성된 워크북
	 */
	public SXSSFWorkbook makeSystemMeasureExcelWorkbook(List<SystemContVO> list, Map<String, BigDecimal> preAccumulateWattProduceMap, String systemName, List<excelVo> excelList) throws ParseException {
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// 시트 생성
		SXSSFSheet sheet = workbook.createSheet(systemName + " 데이터");

		//시트 열 너비 설정

		for(int i = 0; i <= 50 ; i++){
			sheet.setColumnWidth(i, 5000);
		}

		CellStyle headerStyle= workbook.createCellStyle();

		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setBorderBottom(BorderStyle.THIN); // 아랫쪽에 얇은 실선 적용.
		headerStyle.setBorderLeft(BorderStyle.THIN);	// 셀 좌측에 얇은 실선 적용.
		headerStyle.setBorderRight(BorderStyle.THIN);	// 셀 우측에 얇은 실선 적용.
		headerStyle.setBorderTop(BorderStyle.THIN);	// 셀 윗쪽에 얇은 실선 적용.

		CellStyle contentsNumberStyle= workbook.createCellStyle();

		contentsNumberStyle.setAlignment(HorizontalAlignment.RIGHT);
		contentsNumberStyle.setBorderBottom(BorderStyle.THIN); // 아랫쪽에 얇은 실선 적용.
		contentsNumberStyle.setBorderLeft(BorderStyle.THIN);	// 셀 좌측에 얇은 실선 적용.
		contentsNumberStyle.setBorderRight(BorderStyle.THIN);	// 셀 우측에 얇은 실선 적용.
		contentsNumberStyle.setBorderTop(BorderStyle.THIN);	// 셀 윗쪽에 얇은 실선 적용.

		CellStyle contentsTextStyle= workbook.createCellStyle();

		contentsTextStyle.setAlignment(HorizontalAlignment.CENTER);
		contentsTextStyle.setBorderBottom(BorderStyle.THIN); // 아랫쪽에 얇은 실선 적용.
		contentsTextStyle.setBorderLeft(BorderStyle.THIN);	// 셀 좌측에 얇은 실선 적용.
		contentsTextStyle.setBorderRight(BorderStyle.THIN);	// 셀 우측에 얇은 실선 적용.
		contentsTextStyle.setBorderTop(BorderStyle.THIN);	// 셀 윗쪽에 얇은 실선 적용.

		// 헤더 행 생
		Row headerRow = sheet.createRow(0);

		Cell headerCell;

		Map<String, Integer> excelColumnMap = new LinkedHashMap<>();
		if(excelList.size() ==1) {
			excelColumnMap.put("생성일자", excelList.get(0).getColumn01());
			excelColumnMap.put("개질연료 유량계", excelList.get(0).getColumn02());
			excelColumnMap.put("버너연료 유량계", excelList.get(0).getColumn03());
			excelColumnMap.put("공기 유량계", excelList.get(0).getColumn04());
			excelColumnMap.put("물펌프 속도", excelList.get(0).getColumn05());
			excelColumnMap.put("스택 온도계", excelList.get(0).getColumn06());
			excelColumnMap.put("개질기 온도계", excelList.get(0).getColumn07());
			excelColumnMap.put("버너 온도계", excelList.get(0).getColumn08());
			excelColumnMap.put("셀상부 온도계", excelList.get(0).getColumn09());
			excelColumnMap.put("HX HOT(IN) 온도계", excelList.get(0).getColumn10());
			excelColumnMap.put("HX HOT(OUT) 온도계", excelList.get(0).getColumn11());
			excelColumnMap.put("HX COLD(OUT) 온도계", excelList.get(0).getColumn12());
			excelColumnMap.put("폐열회수 온도계", excelList.get(0).getColumn13());
			excelColumnMap.put("배기가스 온도계", excelList.get(0).getColumn14());
			excelColumnMap.put("스택예비 온도계", excelList.get(0).getColumn15());
			excelColumnMap.put("저탕조 온도계", excelList.get(0).getColumn16());
			excelColumnMap.put("개질연료 압력계", excelList.get(0).getColumn17());
			excelColumnMap.put("배기가스 압력계", excelList.get(0).getColumn18());
			excelColumnMap.put("공정상태", excelList.get(0).getColumn19());
			excelColumnMap.put("인버터에러", excelList.get(0).getColumn20());
			excelColumnMap.put("DC 전류", excelList.get(0).getColumn21());
			excelColumnMap.put("DC 전압", excelList.get(0).getColumn22());
			excelColumnMap.put("DC 발전량", excelList.get(0).getColumn23());
			excelColumnMap.put("AC 전류", excelList.get(0).getColumn24());
			excelColumnMap.put("AC 전압", excelList.get(0).getColumn25());
			excelColumnMap.put("AC 발전량", excelList.get(0).getColumn26());
			excelColumnMap.put("당일누적발전량", excelList.get(0).getColumn27());
			excelColumnMap.put("전체누적발전량", excelList.get(0).getColumn28());
			excelColumnMap.put("열생산량", excelList.get(0).getColumn29());
			excelColumnMap.put("누적 열생산량", excelList.get(0).getColumn30());
			excelColumnMap.put("누적 열소비량", excelList.get(0).getColumn31());
			excelColumnMap.put("전일 누적 열 생산량", excelList.get(0).getColumn32());
		}


		int count = 0;
		for (Map.Entry<String, Integer> entrySet : excelColumnMap.entrySet()) {
			if(entrySet.getValue() == 1) {
				headerCell = headerRow.createCell(count);
				headerCell.setCellValue(entrySet.getKey());
				headerCell.setCellStyle(headerStyle);
				count++;
			}
		}



		// 내용 행 및 셀 생성
		Row bodyRow = null;
		Cell bodyCell = null;

		for(int i=0; i<list.size(); i++) {
			SystemContVO systemCont = list.get(i);
			count = 0;
			// 행 생성
			bodyRow = sheet.createRow(i+1);

			Date timestampToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(systemCont.getTimestamp());
			String timestampToString = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestampToDate);
			String timestampToString2 = new SimpleDateFormat("yyyy-MM-dd").format(timestampToDate);

			if(excelList.get(0).getColumn01() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(timestampToString);
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn02() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getFL1().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn03() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getFL2().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn04() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getFL3().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn05() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getFL5().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn06() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC1().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn07() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC2().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn08() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC3().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn09() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC4().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn10() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC5().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn11() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC6().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn12() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC7().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn13() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC8().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn14() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC9().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn15() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC10().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn16() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getTC11().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn17() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getPT1().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn18() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(Math.floor(systemCont.getPT2().doubleValue()));
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn19() == 1) {
				bodyCell = bodyRow.createCell(count);
				if(systemCont.getStatusOperationStatus().equals("3")) {
					bodyCell.setCellValue("착화 공정");
				}else if(systemCont.getStatusOperationStatus().equals("4")) {
					bodyCell.setCellValue("승온 공정");
				}else if(systemCont.getStatusOperationStatus().equals("5")) {
					bodyCell.setCellValue("운전 공정");
				}else if(systemCont.getStatusOperationStatus().equals("6")) {
					bodyCell.setCellValue("종료 공정");
				}else if(systemCont.getStatusOperationStatus().equals("7")) {
	//	        	bodyCell.setCellValue("에러 공정");
					//20210531 상세 코드 보여지도록 수정
					bodyCell.setCellValue("에러 공정 - " + systemCont.getErrorErrorCode());
				}else {
					bodyCell.setCellValue("- - -");
				}
				bodyCell.setCellStyle(contentsTextStyle);
				count++;
			}
			if(excelList.get(0).getColumn20() == 1) {
				bodyCell = bodyRow.createCell(count);
				if(systemCont.getErrorInverterErrorCode().equals("0")) {
					bodyCell.setCellValue("정상");
				}else {
					bodyCell.setCellValue(systemCont.getErrorInverterErrorCode());
				}
				bodyCell.setCellStyle(contentsTextStyle);
				count++;
			}
			if(excelList.get(0).getColumn21() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getDCAmpare().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn22() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getDCVoltage().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn23() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getDCWatt().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn24() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getRearACAmpare().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn25() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getRearACVoltage().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn26() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getRearACWatt().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			BigDecimal tdAccumulateWattProduce = systemCont.getAccumulateWattProduce();
			BigDecimal pdAccumulateWattProduce = systemCont.getPreAccumulateWattProduce();

			if(excelList.get(0).getColumn27() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(tdAccumulateWattProduce.subtract(preAccumulateWattProduceMap.get(timestampToString2)).doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn28() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getAccumulateWattProduce().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn29() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getHeatProduce().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn30() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getAccumulateHeatProduce().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn31() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getAccumulateHeatConsume().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
			if(excelList.get(0).getColumn32() == 1) {
				bodyCell = bodyRow.createCell(count);
				bodyCell.setCellValue(systemCont.getPreDayAccumulateHeatProduce().doubleValue());
				bodyCell.setCellStyle(contentsNumberStyle);
				count++;
			}
		}

		return workbook;
	}
	
	/**
     * 리스트를 간단한 엑셀 워크북 객체로 생성
     * @param list
     * @return 생성된 워크북
     */
	public SXSSFWorkbook makeSystemMeasureExcelWorkbook(List<SystemContVO> list, Map<String, BigDecimal> preAccumulateWattProduceMap, String systemName) throws ParseException {
	    SXSSFWorkbook workbook = new SXSSFWorkbook();

	    
	    // 시트 생성
	    SXSSFSheet sheet = workbook.createSheet(systemName + " 데이터");
	    
	    //시트 열 너비 설정
	    
	    for(int i = 0; i <= 50 ; i++){
	        sheet.setColumnWidth(i, 5000);
	    }
	    
	    CellStyle headerStyle= workbook.createCellStyle();
	    
	    headerStyle.setAlignment(HorizontalAlignment.CENTER);
	    headerStyle.setBorderBottom(BorderStyle.THIN); // 아랫쪽에 얇은 실선 적용.
	    headerStyle.setBorderLeft(BorderStyle.THIN);	// 셀 좌측에 얇은 실선 적용.
	    headerStyle.setBorderRight(BorderStyle.THIN);	// 셀 우측에 얇은 실선 적용.
	    headerStyle.setBorderTop(BorderStyle.THIN);	// 셀 윗쪽에 얇은 실선 적용.
	    
	    CellStyle contentsNumberStyle= workbook.createCellStyle();
	    
	    contentsNumberStyle.setAlignment(HorizontalAlignment.RIGHT);
	    contentsNumberStyle.setBorderBottom(BorderStyle.THIN); // 아랫쪽에 얇은 실선 적용.
	    contentsNumberStyle.setBorderLeft(BorderStyle.THIN);	// 셀 좌측에 얇은 실선 적용.
	    contentsNumberStyle.setBorderRight(BorderStyle.THIN);	// 셀 우측에 얇은 실선 적용.
	    contentsNumberStyle.setBorderTop(BorderStyle.THIN);	// 셀 윗쪽에 얇은 실선 적용.
	    
	    CellStyle contentsTextStyle= workbook.createCellStyle();
	    
	    contentsTextStyle.setAlignment(HorizontalAlignment.CENTER);
	    contentsTextStyle.setBorderBottom(BorderStyle.THIN); // 아랫쪽에 얇은 실선 적용.
	    contentsTextStyle.setBorderLeft(BorderStyle.THIN);	// 셀 좌측에 얇은 실선 적용.
	    contentsTextStyle.setBorderRight(BorderStyle.THIN);	// 셀 우측에 얇은 실선 적용.
	    contentsTextStyle.setBorderTop(BorderStyle.THIN);	// 셀 윗쪽에 얇은 실선 적용.
	    
	    // 헤더 행 생
	    Row headerRow = sheet.createRow(0);
	    
	    Cell headerCell = headerRow.createCell(0);
	    headerCell.setCellValue("생성일자");
	    headerCell.setCellStyle(headerStyle);
	    
	    headerCell = headerRow.createCell(1);
//	    headerCell.setCellValue("PUMP1");
	    headerCell.setCellValue("개질연료 펌프");
	    headerCell.setCellStyle(headerStyle);
	    
	    headerCell = headerRow.createCell(2);
//	    headerCell.setCellValue("PUMP2");
	    headerCell.setCellValue("버너연료 펌프");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(3);
//	    headerCell.setCellValue("PUMP3");
	    headerCell.setCellValue("공기 펌프");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(4);
//	    headerCell.setCellValue("PUMP4");
	    headerCell.setCellValue("개질워터 펌프");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(5);
//	    headerCell.setCellValue("FL1");
	    headerCell.setCellValue("개질연료 유량계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(6);
//	    headerCell.setCellValue("FL2");
	    headerCell.setCellValue("버너연료 유량계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(7);
//	    headerCell.setCellValue("FL3");
	    headerCell.setCellValue("공기 유량계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(8);
//	    headerCell.setCellValue("FL5");
	    headerCell.setCellValue("물펌프 속도");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(9);
//	    headerCell.setCellValue("SOL1");
	    headerCell.setCellValue("개질연료 밸브");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(10);
//	    headerCell.setCellValue("SOL2");
	    headerCell.setCellValue("버너연료 밸브");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(11);
//	    headerCell.setCellValue("SOL4");
	    headerCell.setCellValue("안전 밸브");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(12);
//	    headerCell.setCellValue("SOL5");
	    headerCell.setCellValue("개질물 밸브");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(13);
//	    headerCell.setCellValue("SOL6");
	    headerCell.setCellValue("저탕조 온수배출 밸브");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(14);
//	    headerCell.setCellValue("TC1");
	    headerCell.setCellValue("스택 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(15);
//	    headerCell.setCellValue("TC2");
	    headerCell.setCellValue("개질기 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(16);
//	    headerCell.setCellValue("TC3");
	    headerCell.setCellValue("버너 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(17);
//	    headerCell.setCellValue("TC4");
	    headerCell.setCellValue("셀상부 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(18);
//	    headerCell.setCellValue("TC5");
	    headerCell.setCellValue("HX HOT(IN) 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(19);
//	    headerCell.setCellValue("TC6");
	    headerCell.setCellValue("HX HOT(OUT) 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(20);
//	    headerCell.setCellValue("TC7");
	    headerCell.setCellValue("HX COLD(OUT) 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(21);
//	    headerCell.setCellValue("TC8");
	    headerCell.setCellValue("폐열회수 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(22);
//	    headerCell.setCellValue("TC9");
	    headerCell.setCellValue("배기가스 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(23);
//	    headerCell.setCellValue("TC10");
	    headerCell.setCellValue("스택예비 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(24);
//	    headerCell.setCellValue("TC11");
	    headerCell.setCellValue("저탕조 온도계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(25);
//	    headerCell.setCellValue("SNH");
	    headerCell.setCellValue("점화기");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(26);
//	    headerCell.setCellValue("Three_Way_Valve");
	    headerCell.setCellValue("삼방밸브");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(27);
//	    headerCell.setCellValue("Water_Level_Sensor");
	    headerCell.setCellValue("저수위센서");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(28);
//	    headerCell.setCellValue("PT1");
	    headerCell.setCellValue("개질연료 압력계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(29);
//	    headerCell.setCellValue("PT2");
	    headerCell.setCellValue("배기가스 압력계");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(30);
//	    headerCell.setCellValue("Operation_Status");
	    headerCell.setCellValue("공정상태");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(31);
//	    headerCell.setCellValue("Error_Inverter_Error_Code");
	    headerCell.setCellValue("인버터에러");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(32);
//	    headerCell.setCellValue("DC_Ampare");
	    headerCell.setCellValue("DC 전류");
	    headerCell.setCellStyle(headerStyle);
	    
	    headerCell = headerRow.createCell(33);
//	    headerCell.setCellValue("DC_Voltage");
	    headerCell.setCellValue("DC 전압");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(34);
//	    headerCell.setCellValue("DC_Watt");
	    headerCell.setCellValue("DC 발전량");
	    headerCell.setCellStyle(headerStyle);
	    
	    headerCell = headerRow.createCell(35);
//	    headerCell.setCellValue("AC_Ampare");
	    headerCell.setCellValue("AC 전류");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(36);
//	    headerCell.setCellValue("AC_Voltage");
	    headerCell.setCellValue("AC 전압");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(37);
//	    headerCell.setCellValue("AC_Watt");
	    headerCell.setCellValue("AC 발전량");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(38);
	    headerCell.setCellValue("당일누적발전량");
	    headerCell.setCellStyle(headerStyle);
	    
	    headerCell = headerRow.createCell(39);
	    headerCell.setCellValue("전체누적발전량");
	    headerCell.setCellStyle(headerStyle);
//
//	    headerCell = headerRow.createCell(40);
//	    headerCell.setCellValue("Rear_AC_Watt");

	    headerCell = headerRow.createCell(40);
//	    headerCell.setCellValue("Heat_Produce");
	    headerCell.setCellValue("열생산량");
	    headerCell.setCellStyle(headerStyle);

//	    headerCell = headerRow.createCell(41);
////	    headerCell.setCellValue("Status_Op_ONOFF");
//	    headerCell.setCellValue("고장여부");
//	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(41);
//	    headerCell.setCellValue("Accumulate_Heat_Produce");
	    headerCell.setCellValue("누적 열생산량");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(42);
//	    headerCell.setCellValue("Accumulate_Heat_Consume");
	    headerCell.setCellValue("누적 열소비량");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(43);
//	    headerCell.setCellValue("Pre_Day_Accumulate_Heat_Produce");
	    headerCell.setCellValue("전일 누적 열 생산량");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(44);
//	    headerCell.setCellValue("Inlet_Temp");
	    headerCell.setCellValue("입수 온도");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(45);
//	    headerCell.setCellValue("Outlet_Temp");
	    headerCell.setCellValue("출수 온도");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(46);
//	    headerCell.setCellValue("Power_Factor");
	    headerCell.setCellValue("역률");
	    headerCell.setCellStyle(headerStyle);

	    headerCell = headerRow.createCell(47);
//	    headerCell.setCellValue("Freq");
	    headerCell.setCellValue("주파수");
	    headerCell.setCellStyle(headerStyle);

	    // 내용 행 및 셀 생성
	    Row bodyRow = null;
	    Cell bodyCell = null;
	    for(int i=0; i<list.size(); i++) {
	        SystemContVO systemCont = list.get(i);
	        
	        // 행 생성
	        bodyRow = sheet.createRow(i+1);


			Date timestampToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(systemCont.getTimestamp());
			String timestampToString = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestampToDate);
			String timestampToString2 = new SimpleDateFormat("yyyy-MM-dd").format(timestampToDate);

	        bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue(timestampToString);
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(systemCont.getPUMP1().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue(systemCont.getPUMP2().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(Math.floor(systemCont.getPUMP3().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue(systemCont.getPUMP4().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(systemCont.getFL1().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(6);
	        bodyCell.setCellValue(systemCont.getFL2().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(7);
	        bodyCell.setCellValue(Math.floor(systemCont.getFL3().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(8);
	        bodyCell.setCellValue(systemCont.getFL5().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);

			BigDecimal compareZero = BigDecimal.valueOf(0);

	        bodyCell = bodyRow.createCell(9);
	        bodyCell.setCellValue(systemCont.getStatusSOL1().equals(compareZero) ? "OFF" : "ON" );
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        bodyCell = bodyRow.createCell(10);
	        bodyCell.setCellValue(systemCont.getStatusSOL2().equals(compareZero) ? "OFF" : "ON" );
	        bodyCell.setCellStyle(contentsTextStyle);

	        bodyCell = bodyRow.createCell(11);
	        bodyCell.setCellValue(systemCont.getStatusSOL4().equals(compareZero) ? "OFF" : "ON" );
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        bodyCell = bodyRow.createCell(12);
	        bodyCell.setCellValue(systemCont.getStatusSOL5().equals(compareZero) ? "OFF" : "ON" );
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        bodyCell = bodyRow.createCell(13);
	        bodyCell.setCellValue(systemCont.getStatusSOL6().equals(compareZero) ? "OFF" : "ON" );
	        bodyCell.setCellStyle(contentsTextStyle);

	        bodyCell = bodyRow.createCell(14);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC1().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(15);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC2().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(16);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC3().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(17);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC4().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(18);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC5().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(19);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC6().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(20);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC7().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(21);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC8().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(22);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC9().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(23);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC10().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);

	        bodyCell = bodyRow.createCell(24);
	        bodyCell.setCellValue(Math.floor(systemCont.getTC11().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(25);
	        bodyCell.setCellValue(systemCont.getStatusSNH());
	        bodyCell.setCellStyle(contentsTextStyle);

	        bodyCell = bodyRow.createCell(26);
	        bodyCell.setCellValue(systemCont.getStatusThreeWayValve());
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        bodyCell = bodyRow.createCell(27);
	        bodyCell.setCellValue(systemCont.getStatusWaterLevelSensor());
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        bodyCell = bodyRow.createCell(28);
	        bodyCell.setCellValue(Math.floor(systemCont.getPT1().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(29);
	        bodyCell.setCellValue(Math.floor(systemCont.getPT2().doubleValue()));
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(30);
	        if(systemCont.getStatusOperationStatus().equals("3")) {
	        	bodyCell.setCellValue("착화 공정");
	        }else if(systemCont.getStatusOperationStatus().equals("4")) {
	        	bodyCell.setCellValue("승온 공정");
	        }else if(systemCont.getStatusOperationStatus().equals("5")) {
	        	bodyCell.setCellValue("운전 공정");
	        }else if(systemCont.getStatusOperationStatus().equals("6")) {
	        	bodyCell.setCellValue("종료 공정");
	        }else if(systemCont.getStatusOperationStatus().equals("7")) {
//	        	bodyCell.setCellValue("에러 공정");
	        	//20210531 상세 코드 보여지도록 수정
	        	bodyCell.setCellValue("에러 공정 - " + systemCont.getErrorErrorCode());
	        }else {
	        	bodyCell.setCellValue("- - -");
	        }
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        bodyCell = bodyRow.createCell(31);
	        if(systemCont.getErrorInverterErrorCode().equals("0")) {
	        	bodyCell.setCellValue("정상");
	        }else {
	        	bodyCell.setCellValue(systemCont.getErrorInverterErrorCode());
	        }
	        bodyCell.setCellStyle(contentsTextStyle);
	        
	        
	        bodyCell = bodyRow.createCell(32);
	        bodyCell.setCellValue(systemCont.getDCAmpare().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(33);
	        bodyCell.setCellValue(systemCont.getDCVoltage().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(34);
	        bodyCell.setCellValue(systemCont.getDCWatt().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(35);
	        bodyCell.setCellValue(systemCont.getRearACAmpare().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(36);
	        bodyCell.setCellValue(systemCont.getRearACVoltage().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(37);
	        bodyCell.setCellValue(systemCont.getRearACWatt().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
//	        float tdAccumulateWattProduce = systemCont.getAccumulateWattProduce().floatValue();
//	        float pdAccumulateWattProduce = systemCont.getPreAccumulateWattProduce().floatValue();
	        
	        BigDecimal tdAccumulateWattProduce = systemCont.getAccumulateWattProduce();
	        BigDecimal pdAccumulateWattProduce = systemCont.getPreAccumulateWattProduce();

	        bodyCell = bodyRow.createCell(38);
	        bodyCell.setCellValue(tdAccumulateWattProduce.subtract(preAccumulateWattProduceMap.get(timestampToString2)).doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(39);
	        bodyCell.setCellValue(systemCont.getAccumulateWattProduce().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
//	        bodyCell = bodyRow.createCell(40);
//	        bodyCell.setCellValue(systemCont.getRearACWatt());
	        
	        bodyCell = bodyRow.createCell(40);
	        bodyCell.setCellValue(systemCont.getHeatProduce().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
//	        bodyCell = bodyRow.createCell(41);
//	        bodyCell.setCellValue(systemCont.getStatusOpONOFF());
//	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(41);
	        bodyCell.setCellValue(systemCont.getAccumulateHeatProduce().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(42);
	        bodyCell.setCellValue(systemCont.getAccumulateHeatConsume().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(43);
	        bodyCell.setCellValue(systemCont.getPreDayAccumulateHeatProduce().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(44);
	        bodyCell.setCellValue(systemCont.getInletTemp().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(45);
	        bodyCell.setCellValue(systemCont.getOutletTemp().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(46);
	        bodyCell.setCellValue(systemCont.getPowerFactor().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	        bodyCell = bodyRow.createCell(47);
	        bodyCell.setCellValue(systemCont.getFreq().doubleValue());
	        bodyCell.setCellStyle(contentsNumberStyle);
	        
	    }
	    
	    return workbook;
	}
}
