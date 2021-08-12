package com.stx.sofc.dashboard.vo;

import java.math.BigDecimal;

public class DashboardVO {

	private String iCityNum;
	private String sCityName;
	
	private String iAreaNum;
	private String sAreaName;
	
	private String iSiteNum;
	private String sSiteName;
	
	private String iSysNum;
	private String sSystemName;
	private String iRtuNum;
	private String iBdNum;
	
	private String sSysCnt;
	private BigDecimal fCapa;
	private BigDecimal fCapaGeo;
	private BigDecimal fCapaSolar;
	private String sUsePer;
	
	private String fLhv;								// 추가: 사용자에게 받은 LHV
	private String tLastUpdated;						// 추가: 업데이트 시간
	private String fCumulativeEfficiency;				// 추가: 누적 발전 효율, 장비 시작 시점부터 지금까지의 효율 평균값
	private String fTodayEfficiency;					// 추가: 당일 발전 효율(0시 이후 fEfficiency의 평균값)
	private String fCO2;								// 추가: 사용자에게 받은 fCo2
	private String txtCumulativeWattHour; 				// 추가: 금일 시간당 발전량
	
	
	private BigDecimal tdCumulativeWatt;
	private BigDecimal tdEfficiency;
	private BigDecimal tdAccumulateHeatConsume;
	private BigDecimal pdCumulativeWatt;
	private BigDecimal cumulativeEfficiency;
	private BigDecimal tdReductionCo2Watt;
	private BigDecimal cumulativeWatt;
	private BigDecimal tdAccumulateHeatProduce;
	private BigDecimal tdReductionCo2Heat;
	private BigDecimal accumulateHeatProduce;
	private String timestamp;
	
	
	private BigDecimal pdAccumulateWattProduce;
	private BigDecimal pdAccumulateHeatProduce;
	private BigDecimal tdAccumulateWattProduce;
	
	
	private String tdPreDayAccumulateHeatProduce;
	private String tdWattMeter;
	private String tdFL1;
	private String tdFL2;
	private String tdStatusBoardState;
	private String hour;
	private String rtuId;
	private String accumulateWattProduce;
	
	private String efficient;
	
	private String sAudiNum;
	private String sAudiName;
	private String sAudiCall;
	private String sAudiDo;
	private String sAudiCity;
	private String sAudiArea;
	private String sAudiBiz;
	private String sAudiBuilding;
	
	private String sAuth;
	private String rownum;					//글번호(페이징을 위한 변수 )
	private String start;					//시작(페이징을 위한 변수 )
	private String end;						//끝(페이징을 위한 변수 )
	private String startPage;				//조회 하려는 페이지(페이징을 위한 변수 )
	private String visiblePages;			//한페이지에 보여줄 리스트 수(페이징을 위한 변수 )
	
	private String id;						//event의 ID
	private String eventDate;
	private String deviceID;
	private String errNum;
	private String isAlreadySend;
	private String other;
	
	private String sMessage;

	public String getiCityNum() {
		return iCityNum;
	}

	public void setiCityNum(String iCityNum) {
		this.iCityNum = iCityNum;
	}

	public String getsCityName() {
		return sCityName;
	}

	public void setsCityName(String sCityName) {
		this.sCityName = sCityName;
	}

	public String getiAreaNum() {
		return iAreaNum;
	}

	public void setiAreaNum(String iAreaNum) {
		this.iAreaNum = iAreaNum;
	}

	public String getsAreaName() {
		return sAreaName;
	}

	public void setsAreaName(String sAreaName) {
		this.sAreaName = sAreaName;
	}

	public String getiSiteNum() {
		return iSiteNum;
	}

	public void setiSiteNum(String iSiteNum) {
		this.iSiteNum = iSiteNum;
	}

	public String getsSiteName() {
		return sSiteName;
	}

	public void setsSiteName(String sSiteName) {
		this.sSiteName = sSiteName;
	}

	public String getiSysNum() {
		return iSysNum;
	}

	public void setiSysNum(String iSysNum) {
		this.iSysNum = iSysNum;
	}

	public String getsSystemName() {
		return sSystemName;
	}

	public void setsSystemName(String sSystemName) {
		this.sSystemName = sSystemName;
	}

	public String getiRtuNum() {
		return iRtuNum;
	}

	public void setiRtuNum(String iRtuNum) {
		this.iRtuNum = iRtuNum;
	}

	public String getiBdNum() {
		return iBdNum;
	}

	public void setiBdNum(String iBdNum) {
		this.iBdNum = iBdNum;
	}

	public String getsSysCnt() {
		return sSysCnt;
	}

	public void setsSysCnt(String sSysCnt) {
		this.sSysCnt = sSysCnt;
	}

	public BigDecimal getfCapa() {
		return fCapa;
	}

	public void setfCapa(BigDecimal fCapa) {
		this.fCapa = fCapa;
	}

	public BigDecimal getfCapaGeo() {
		return fCapaGeo;
	}

	public void setfCapaGeo(BigDecimal fCapaGeo) {
		this.fCapaGeo = fCapaGeo;
	}

	public BigDecimal getfCapaSolar() {
		return fCapaSolar;
	}

	public void setfCapaSolar(BigDecimal fCapaSolar) {
		this.fCapaSolar = fCapaSolar;
	}

	public String getsUsePer() {
		return sUsePer;
	}

	public void setsUsePer(String sUsePer) {
		this.sUsePer = sUsePer;
	}

	public String getfLhv() {
		return fLhv;
	}

	public void setfLhv(String fLhv) {
		this.fLhv = fLhv;
	}

	public String gettLastUpdated() {
		return tLastUpdated;
	}

	public void settLastUpdated(String tLastUpdated) {
		this.tLastUpdated = tLastUpdated;
	}

	public String getfCumulativeEfficiency() {
		return fCumulativeEfficiency;
	}

	public void setfCumulativeEfficiency(String fCumulativeEfficiency) {
		this.fCumulativeEfficiency = fCumulativeEfficiency;
	}

	public String getfTodayEfficiency() {
		return fTodayEfficiency;
	}

	public void setfTodayEfficiency(String fTodayEfficiency) {
		this.fTodayEfficiency = fTodayEfficiency;
	}

	public String getfCO2() {
		return fCO2;
	}

	public void setfCO2(String fCO2) {
		this.fCO2 = fCO2;
	}

	public String getTxtCumulativeWattHour() {
		return txtCumulativeWattHour;
	}

	public void setTxtCumulativeWattHour(String txtCumulativeWattHour) {
		this.txtCumulativeWattHour = txtCumulativeWattHour;
	}

	public BigDecimal getTdCumulativeWatt() {
		return tdCumulativeWatt;
	}

	public void setTdCumulativeWatt(BigDecimal tdCumulativeWatt) {
		this.tdCumulativeWatt = tdCumulativeWatt;
	}

	public BigDecimal getTdEfficiency() {
		return tdEfficiency;
	}

	public void setTdEfficiency(BigDecimal tdEfficiency) {
		this.tdEfficiency = tdEfficiency;
	}

	public BigDecimal getTdAccumulateHeatConsume() {
		return tdAccumulateHeatConsume;
	}

	public void setTdAccumulateHeatConsume(BigDecimal tdAccumulateHeatConsume) {
		this.tdAccumulateHeatConsume = tdAccumulateHeatConsume;
	}

	public BigDecimal getPdCumulativeWatt() {
		return pdCumulativeWatt;
	}

	public void setPdCumulativeWatt(BigDecimal pdCumulativeWatt) {
		this.pdCumulativeWatt = pdCumulativeWatt;
	}

	public BigDecimal getCumulativeEfficiency() {
		return cumulativeEfficiency;
	}

	public void setCumulativeEfficiency(BigDecimal cumulativeEfficiency) {
		this.cumulativeEfficiency = cumulativeEfficiency;
	}

	public BigDecimal getTdReductionCo2Watt() {
		return tdReductionCo2Watt;
	}

	public void setTdReductionCo2Watt(BigDecimal tdReductionCo2Watt) {
		this.tdReductionCo2Watt = tdReductionCo2Watt;
	}

	public BigDecimal getCumulativeWatt() {
		return cumulativeWatt;
	}

	public void setCumulativeWatt(BigDecimal cumulativeWatt) {
		this.cumulativeWatt = cumulativeWatt;
	}

	public BigDecimal getTdAccumulateHeatProduce() {
		return tdAccumulateHeatProduce;
	}

	public void setTdAccumulateHeatProduce(BigDecimal tdAccumulateHeatProduce) {
		this.tdAccumulateHeatProduce = tdAccumulateHeatProduce;
	}

	public BigDecimal getTdReductionCo2Heat() {
		return tdReductionCo2Heat;
	}

	public void setTdReductionCo2Heat(BigDecimal tdReductionCo2Heat) {
		this.tdReductionCo2Heat = tdReductionCo2Heat;
	}

	public BigDecimal getAccumulateHeatProduce() {
		return accumulateHeatProduce;
	}

	public void setAccumulateHeatProduce(BigDecimal accumulateHeatProduce) {
		this.accumulateHeatProduce = accumulateHeatProduce;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getPdAccumulateWattProduce() {
		return pdAccumulateWattProduce;
	}

	public void setPdAccumulateWattProduce(BigDecimal pdAccumulateWattProduce) {
		this.pdAccumulateWattProduce = pdAccumulateWattProduce;
	}

	public BigDecimal getPdAccumulateHeatProduce() {
		return pdAccumulateHeatProduce;
	}

	public void setPdAccumulateHeatProduce(BigDecimal pdAccumulateHeatProduce) {
		this.pdAccumulateHeatProduce = pdAccumulateHeatProduce;
	}

	public BigDecimal getTdAccumulateWattProduce() {
		return tdAccumulateWattProduce;
	}

	public void setTdAccumulateWattProduce(BigDecimal tdAccumulateWattProduce) {
		this.tdAccumulateWattProduce = tdAccumulateWattProduce;
	}

	public String getTdPreDayAccumulateHeatProduce() {
		return tdPreDayAccumulateHeatProduce;
	}

	public void setTdPreDayAccumulateHeatProduce(String tdPreDayAccumulateHeatProduce) {
		this.tdPreDayAccumulateHeatProduce = tdPreDayAccumulateHeatProduce;
	}

	public String getTdWattMeter() {
		return tdWattMeter;
	}

	public void setTdWattMeter(String tdWattMeter) {
		this.tdWattMeter = tdWattMeter;
	}

	public String getTdFL1() {
		return tdFL1;
	}

	public void setTdFL1(String tdFL1) {
		this.tdFL1 = tdFL1;
	}

	public String getTdFL2() {
		return tdFL2;
	}

	public void setTdFL2(String tdFL2) {
		this.tdFL2 = tdFL2;
	}

	public String getTdStatusBoardState() {
		return tdStatusBoardState;
	}

	public void setTdStatusBoardState(String tdStatusBoardState) {
		this.tdStatusBoardState = tdStatusBoardState;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getRtuId() {
		return rtuId;
	}

	public void setRtuId(String rtuId) {
		this.rtuId = rtuId;
	}

	public String getAccumulateWattProduce() {
		return accumulateWattProduce;
	}

	public void setAccumulateWattProduce(String accumulateWattProduce) {
		this.accumulateWattProduce = accumulateWattProduce;
	}

	public String getEfficient() {
		return efficient;
	}

	public void setEfficient(String efficient) {
		this.efficient = efficient;
	}

	public String getsAudiNum() {
		return sAudiNum;
	}

	public void setsAudiNum(String sAudiNum) {
		this.sAudiNum = sAudiNum;
	}

	public String getsAudiName() {
		return sAudiName;
	}

	public void setsAudiName(String sAudiName) {
		this.sAudiName = sAudiName;
	}

	public String getsAudiCall() {
		return sAudiCall;
	}

	public void setsAudiCall(String sAudiCall) {
		this.sAudiCall = sAudiCall;
	}

	public String getsAudiDo() {
		return sAudiDo;
	}

	public void setsAudiDo(String sAudiDo) {
		this.sAudiDo = sAudiDo;
	}

	public String getsAudiCity() {
		return sAudiCity;
	}

	public void setsAudiCity(String sAudiCity) {
		this.sAudiCity = sAudiCity;
	}

	public String getsAudiArea() {
		return sAudiArea;
	}

	public void setsAudiArea(String sAudiArea) {
		this.sAudiArea = sAudiArea;
	}

	public String getsAudiBiz() {
		return sAudiBiz;
	}

	public void setsAudiBiz(String sAudiBiz) {
		this.sAudiBiz = sAudiBiz;
	}

	public String getsAudiBuilding() {
		return sAudiBuilding;
	}

	public void setsAudiBuilding(String sAudiBuilding) {
		this.sAudiBuilding = sAudiBuilding;
	}

	public String getsAuth() {
		return sAuth;
	}

	public void setsAuth(String sAuth) {
		this.sAuth = sAuth;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStartPage() {
		return startPage;
	}

	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}

	public String getVisiblePages() {
		return visiblePages;
	}

	public void setVisiblePages(String visiblePages) {
		this.visiblePages = visiblePages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getErrNum() {
		return errNum;
	}

	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}

	public String getIsAlreadySend() {
		return isAlreadySend;
	}

	public void setIsAlreadySend(String isAlreadySend) {
		this.isAlreadySend = isAlreadySend;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}
	
	@Override
	public String toString() {
		return "DashboardVO [iCityNum=" + iCityNum + ", sCityName=" + sCityName + ", iAreaNum=" + iAreaNum
				+ ", sAreaName=" + sAreaName + ", iSiteNum=" + iSiteNum + ", sSiteName=" + sSiteName + ", iSysNum="
				+ iSysNum + ", sSystemName=" + sSystemName + ", iRtuNum=" + iRtuNum + ", iBdNum=" + iBdNum
				+ ", sSysCnt=" + sSysCnt + ", fCapa=" + fCapa + ", fCapaGeo=" + fCapaGeo + ", fCapaSolar=" + fCapaSolar
				+ ", sUsePer=" + sUsePer + ", fLhv=" + fLhv + ", tLastUpdated=" + tLastUpdated
				+ ", fCumulativeEfficiency=" + fCumulativeEfficiency + ", fTodayEfficiency=" + fTodayEfficiency
				+ ", fCO2=" + fCO2 + ", txtCumulativeWattHour=" + txtCumulativeWattHour + ", tdCumulativeWatt="
				+ tdCumulativeWatt + ", tdEfficiency=" + tdEfficiency + ", tdAccumulateHeatConsume="
				+ tdAccumulateHeatConsume + ", pdCumulativeWatt=" + pdCumulativeWatt + ", cumulativeEfficiency="
				+ cumulativeEfficiency + ", tdReductionCo2Watt=" + tdReductionCo2Watt + ", cumulativeWatt="
				+ cumulativeWatt + ", tdAccumulateHeatProduce=" + tdAccumulateHeatProduce + ", tdReductionCo2Heat="
				+ tdReductionCo2Heat + ", accumulateHeatProduce=" + accumulateHeatProduce + ", timestamp=" + timestamp
				+ ", pdAccumulateWattProduce=" + pdAccumulateWattProduce + ", pdAccumulateHeatProduce="
				+ pdAccumulateHeatProduce + ", tdAccumulateWattProduce=" + tdAccumulateWattProduce
				+ ", tdPreDayAccumulateHeatProduce=" + tdPreDayAccumulateHeatProduce + ", tdWattMeter=" + tdWattMeter
				+ ", tdFL1=" + tdFL1 + ", tdFL2=" + tdFL2 + ", tdStatusBoardState=" + tdStatusBoardState + ", hour="
				+ hour + ", rtuId=" + rtuId + ", accumulateWattProduce=" + accumulateWattProduce + ", efficient="
				+ efficient + ", sAudiNum=" + sAudiNum + ", sAudiName=" + sAudiName + ", sAudiCall=" + sAudiCall
				+ ", sAudiDo=" + sAudiDo + ", sAudiCity=" + sAudiCity + ", sAudiArea=" + sAudiArea + ", sAudiBiz="
				+ sAudiBiz + ", sAudiBuilding=" + sAudiBuilding + ", sAuth=" + sAuth + ", rownum=" + rownum + ", start="
				+ start + ", end=" + end + ", startPage=" + startPage + ", visiblePages=" + visiblePages + ", id=" + id
				+ ", eventDate=" + eventDate + ", deviceID=" + deviceID + ", errNum=" + errNum + ", isAlreadySend="
				+ isAlreadySend + ", other=" + other + ", sMessage=" + sMessage + "]";
	}
	
}
