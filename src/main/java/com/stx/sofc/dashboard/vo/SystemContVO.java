package com.stx.sofc.dashboard.vo;

import java.math.BigDecimal;

public class SystemContVO {

	private String rtuId;
	private String iBdNum;
	
	private String timestamp;
	private BigDecimal PUMP1;
	private BigDecimal PUMP2;
	private BigDecimal PUMP3;
	private BigDecimal PUMP4;
	private BigDecimal FL1;
	private BigDecimal FL2;
	private BigDecimal FL3;
	private BigDecimal FL5;
	
	private BigDecimal StatusSOL1;
	private BigDecimal StatusSOL2;
	private BigDecimal StatusSOL4;
	private BigDecimal StatusSOL5;
	private BigDecimal StatusSOL6;
		
	private BigDecimal TC1;
	private BigDecimal TC2;
	private BigDecimal TC3;
	private BigDecimal TC4;
	private BigDecimal TC5;
	private BigDecimal TC6;
	private BigDecimal TC7;
	private BigDecimal TC8;
	private BigDecimal TC9;
	private BigDecimal TC10;
	private BigDecimal TC11;
	private String StatusSNH;
	private String StatusThreeWayValve;
	private String StatusWaterLevelSensor;
	private BigDecimal PT1;
	private BigDecimal PT2;
	
	private String StatusOperationStatus;
	private String ErrorInverterErrorCode;
	
	private BigDecimal ACAmpare;
	private BigDecimal ACVoltage;
	private BigDecimal ACWatt;
	private BigDecimal DCVoltage;
	private BigDecimal DCAmpare;
	private BigDecimal DCWatt;
	private BigDecimal RearACVoltage;
	private BigDecimal RearACAmpare;
	private BigDecimal RearACWatt;
	private BigDecimal HeatProduce;
	private String StatusOpONOFF;
	private BigDecimal AccumulateHeatProduce;
	private BigDecimal AccumulateHeatConsume;
	private BigDecimal PreDayAccumulateHeatProduce;
	private BigDecimal InletTemp;
	private BigDecimal OutletTemp;
	private BigDecimal PowerFactor;
	private BigDecimal Freq;
	private BigDecimal AccumulateWattProduce;
	private BigDecimal preAccumulateWattProduce;
	
	private BigDecimal errorErrorCode;
	
	private String sSystemNameExcel;
	private String searchRequstDeBgn;
	private String searchRequstDeEnd;
	public String getRtuId() {
		return rtuId;
	}
	public void setRtuId(String rtuId) {
		this.rtuId = rtuId;
	}
	public String getiBdNum() {
		return iBdNum;
	}
	public void setiBdNum(String iBdNum) {
		this.iBdNum = iBdNum;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public BigDecimal getPUMP1() {
		return PUMP1;
	}
	public void setPUMP1(BigDecimal pUMP1) {
		PUMP1 = pUMP1;
	}
	public BigDecimal getPUMP2() {
		return PUMP2;
	}
	public void setPUMP2(BigDecimal pUMP2) {
		PUMP2 = pUMP2;
	}
	public BigDecimal getPUMP3() {
		return PUMP3;
	}
	public void setPUMP3(BigDecimal pUMP3) {
		PUMP3 = pUMP3;
	}
	public BigDecimal getPUMP4() {
		return PUMP4;
	}
	public void setPUMP4(BigDecimal pUMP4) {
		PUMP4 = pUMP4;
	}
	public BigDecimal getFL1() {
		return FL1;
	}
	public void setFL1(BigDecimal fL1) {
		FL1 = fL1;
	}
	public BigDecimal getFL2() {
		return FL2;
	}
	public void setFL2(BigDecimal fL2) {
		FL2 = fL2;
	}
	public BigDecimal getFL3() {
		return FL3;
	}
	public void setFL3(BigDecimal fL3) {
		FL3 = fL3;
	}
	public BigDecimal getFL5() {
		return FL5;
	}
	public void setFL5(BigDecimal fL5) {
		FL5 = fL5;
	}
	public BigDecimal getStatusSOL1() {
		return StatusSOL1;
	}
	public void setStatusSOL1(BigDecimal statusSOL1) {
		StatusSOL1 = statusSOL1;
	}
	public BigDecimal getStatusSOL2() {
		return StatusSOL2;
	}
	public void setStatusSOL2(BigDecimal statusSOL2) {
		StatusSOL2 = statusSOL2;
	}
	public BigDecimal getStatusSOL4() {
		return StatusSOL4;
	}
	public void setStatusSOL4(BigDecimal statusSOL4) {
		StatusSOL4 = statusSOL4;
	}
	public BigDecimal getStatusSOL5() {
		return StatusSOL5;
	}
	public void setStatusSOL5(BigDecimal statusSOL5) {
		StatusSOL5 = statusSOL5;
	}
	public BigDecimal getStatusSOL6() {
		return StatusSOL6;
	}
	public void setStatusSOL6(BigDecimal statusSOL6) {
		StatusSOL6 = statusSOL6;
	}
	public BigDecimal getTC1() {
		return TC1;
	}
	public void setTC1(BigDecimal tC1) {
		TC1 = tC1;
	}
	public BigDecimal getTC2() {
		return TC2;
	}
	public void setTC2(BigDecimal tC2) {
		TC2 = tC2;
	}
	public BigDecimal getTC3() {
		return TC3;
	}
	public void setTC3(BigDecimal tC3) {
		TC3 = tC3;
	}
	public BigDecimal getTC4() {
		return TC4;
	}
	public void setTC4(BigDecimal tC4) {
		TC4 = tC4;
	}
	public BigDecimal getTC5() {
		return TC5;
	}
	public void setTC5(BigDecimal tC5) {
		TC5 = tC5;
	}
	public BigDecimal getTC6() {
		return TC6;
	}
	public void setTC6(BigDecimal tC6) {
		TC6 = tC6;
	}
	public BigDecimal getTC7() {
		return TC7;
	}
	public void setTC7(BigDecimal tC7) {
		TC7 = tC7;
	}
	public BigDecimal getTC8() {
		return TC8;
	}
	public void setTC8(BigDecimal tC8) {
		TC8 = tC8;
	}
	public BigDecimal getTC9() {
		return TC9;
	}
	public void setTC9(BigDecimal tC9) {
		TC9 = tC9;
	}
	public BigDecimal getTC10() {
		return TC10;
	}
	public void setTC10(BigDecimal tC10) {
		TC10 = tC10;
	}
	public BigDecimal getTC11() {
		return TC11;
	}
	public void setTC11(BigDecimal tC11) {
		TC11 = tC11;
	}
	public String getStatusSNH() {
		return StatusSNH;
	}
	public void setStatusSNH(String statusSNH) {
		StatusSNH = statusSNH;
	}
	public String getStatusThreeWayValve() {
		return StatusThreeWayValve;
	}
	public void setStatusThreeWayValve(String statusThreeWayValve) {
		StatusThreeWayValve = statusThreeWayValve;
	}
	public String getStatusWaterLevelSensor() {
		return StatusWaterLevelSensor;
	}
	public void setStatusWaterLevelSensor(String statusWaterLevelSensor) {
		StatusWaterLevelSensor = statusWaterLevelSensor;
	}
	public BigDecimal getPT1() {
		return PT1;
	}
	public void setPT1(BigDecimal pT1) {
		PT1 = pT1;
	}
	public BigDecimal getPT2() {
		return PT2;
	}
	public void setPT2(BigDecimal pT2) {
		PT2 = pT2;
	}
	public String getStatusOperationStatus() {
		return StatusOperationStatus;
	}
	public void setStatusOperationStatus(String statusOperationStatus) {
		StatusOperationStatus = statusOperationStatus;
	}
	public String getErrorInverterErrorCode() {
		return ErrorInverterErrorCode;
	}
	public void setErrorInverterErrorCode(String errorInverterErrorCode) {
		ErrorInverterErrorCode = errorInverterErrorCode;
	}
	public BigDecimal getACAmpare() {
		return ACAmpare;
	}
	public void setACAmpare(BigDecimal aCAmpare) {
		ACAmpare = aCAmpare;
	}
	public BigDecimal getACVoltage() {
		return ACVoltage;
	}
	public void setACVoltage(BigDecimal aCVoltage) {
		ACVoltage = aCVoltage;
	}
	public BigDecimal getACWatt() {
		return ACWatt;
	}
	public void setACWatt(BigDecimal aCWatt) {
		ACWatt = aCWatt;
	}
	public BigDecimal getDCVoltage() {
		return DCVoltage;
	}
	public void setDCVoltage(BigDecimal dCVoltage) {
		DCVoltage = dCVoltage;
	}
	public BigDecimal getDCAmpare() {
		return DCAmpare;
	}
	public void setDCAmpare(BigDecimal dCAmpare) {
		DCAmpare = dCAmpare;
	}
	public BigDecimal getDCWatt() {
		return DCWatt;
	}
	public void setDCWatt(BigDecimal dCWatt) {
		DCWatt = dCWatt;
	}
	public BigDecimal getRearACVoltage() {
		return RearACVoltage;
	}
	public void setRearACVoltage(BigDecimal rearACVoltage) {
		RearACVoltage = rearACVoltage;
	}
	public BigDecimal getRearACAmpare() {
		return RearACAmpare;
	}
	public void setRearACAmpare(BigDecimal rearACAmpare) {
		RearACAmpare = rearACAmpare;
	}
	public BigDecimal getRearACWatt() {
		return RearACWatt;
	}
	public void setRearACWatt(BigDecimal rearACWatt) {
		RearACWatt = rearACWatt;
	}
	public BigDecimal getHeatProduce() {
		return HeatProduce;
	}
	public void setHeatProduce(BigDecimal heatProduce) {
		HeatProduce = heatProduce;
	}
	public String getStatusOpONOFF() {
		return StatusOpONOFF;
	}
	public void setStatusOpONOFF(String statusOpONOFF) {
		StatusOpONOFF = statusOpONOFF;
	}
	public BigDecimal getAccumulateHeatProduce() {
		return AccumulateHeatProduce;
	}
	public void setAccumulateHeatProduce(BigDecimal accumulateHeatProduce) {
		AccumulateHeatProduce = accumulateHeatProduce;
	}
	public BigDecimal getAccumulateHeatConsume() {
		return AccumulateHeatConsume;
	}
	public void setAccumulateHeatConsume(BigDecimal accumulateHeatConsume) {
		AccumulateHeatConsume = accumulateHeatConsume;
	}
	public BigDecimal getPreDayAccumulateHeatProduce() {
		return PreDayAccumulateHeatProduce;
	}
	public void setPreDayAccumulateHeatProduce(BigDecimal preDayAccumulateHeatProduce) {
		PreDayAccumulateHeatProduce = preDayAccumulateHeatProduce;
	}
	public BigDecimal getInletTemp() {
		return InletTemp;
	}
	public void setInletTemp(BigDecimal inletTemp) {
		InletTemp = inletTemp;
	}
	public BigDecimal getOutletTemp() {
		return OutletTemp;
	}
	public void setOutletTemp(BigDecimal outletTemp) {
		OutletTemp = outletTemp;
	}
	public BigDecimal getPowerFactor() {
		return PowerFactor;
	}
	public void setPowerFactor(BigDecimal powerFactor) {
		PowerFactor = powerFactor;
	}
	public BigDecimal getFreq() {
		return Freq;
	}
	public void setFreq(BigDecimal freq) {
		Freq = freq;
	}
	public BigDecimal getAccumulateWattProduce() {
		return AccumulateWattProduce;
	}
	public void setAccumulateWattProduce(BigDecimal accumulateWattProduce) {
		AccumulateWattProduce = accumulateWattProduce;
	}
	public BigDecimal getPreAccumulateWattProduce() {
		return preAccumulateWattProduce;
	}
	public void setPreAccumulateWattProduce(BigDecimal preAccumulateWattProduce) {
		this.preAccumulateWattProduce = preAccumulateWattProduce;
	}
	public BigDecimal getErrorErrorCode() {
		return errorErrorCode;
	}
	public void setErrorErrorCode(BigDecimal errorErrorCode) {
		this.errorErrorCode = errorErrorCode;
	}
	public String getsSystemNameExcel() {
		return sSystemNameExcel;
	}
	public void setsSystemNameExcel(String sSystemNameExcel) {
		this.sSystemNameExcel = sSystemNameExcel;
	}
	public String getSearchRequstDeBgn() {
		return searchRequstDeBgn;
	}
	public void setSearchRequstDeBgn(String searchRequstDeBgn) {
		this.searchRequstDeBgn = searchRequstDeBgn;
	}
	public String getSearchRequstDeEnd() {
		return searchRequstDeEnd;
	}
	public void setSearchRequstDeEnd(String searchRequstDeEnd) {
		this.searchRequstDeEnd = searchRequstDeEnd;
	}
	@Override
	public String toString() {
		return "SystemContVO [rtuId=" + rtuId + ", iBdNum=" + iBdNum + ", timestamp=" + timestamp + ", PUMP1=" + PUMP1
				+ ", PUMP2=" + PUMP2 + ", PUMP3=" + PUMP3 + ", PUMP4=" + PUMP4 + ", FL1=" + FL1 + ", FL2=" + FL2
				+ ", FL3=" + FL3 + ", FL5=" + FL5 + ", StatusSOL1=" + StatusSOL1 + ", StatusSOL2=" + StatusSOL2
				+ ", StatusSOL4=" + StatusSOL4 + ", StatusSOL5=" + StatusSOL5 + ", StatusSOL6=" + StatusSOL6 + ", TC1="
				+ TC1 + ", TC2=" + TC2 + ", TC3=" + TC3 + ", TC4=" + TC4 + ", TC5=" + TC5 + ", TC6=" + TC6 + ", TC7="
				+ TC7 + ", TC8=" + TC8 + ", TC9=" + TC9 + ", TC10=" + TC10 + ", TC11=" + TC11 + ", StatusSNH="
				+ StatusSNH + ", StatusThreeWayValve=" + StatusThreeWayValve + ", StatusWaterLevelSensor="
				+ StatusWaterLevelSensor + ", PT1=" + PT1 + ", PT2=" + PT2 + ", StatusOperationStatus="
				+ StatusOperationStatus + ", ErrorInverterErrorCode=" + ErrorInverterErrorCode + ", ACAmpare="
				+ ACAmpare + ", ACVoltage=" + ACVoltage + ", ACWatt=" + ACWatt + ", DCVoltage=" + DCVoltage
				+ ", DCAmpare=" + DCAmpare + ", DCWatt=" + DCWatt + ", RearACVoltage=" + RearACVoltage
				+ ", RearACAmpare=" + RearACAmpare + ", RearACWatt=" + RearACWatt + ", HeatProduce=" + HeatProduce
				+ ", StatusOpONOFF=" + StatusOpONOFF + ", AccumulateHeatProduce=" + AccumulateHeatProduce
				+ ", AccumulateHeatConsume=" + AccumulateHeatConsume + ", PreDayAccumulateHeatProduce="
				+ PreDayAccumulateHeatProduce + ", InletTemp=" + InletTemp + ", OutletTemp=" + OutletTemp
				+ ", PowerFactor=" + PowerFactor + ", Freq=" + Freq + ", AccumulateWattProduce=" + AccumulateWattProduce
				+ ", preAccumulateWattProduce=" + preAccumulateWattProduce + ", errorErrorCode=" + errorErrorCode
				+ ", sSystemNameExcel=" + sSystemNameExcel + ", searchRequstDeBgn=" + searchRequstDeBgn
				+ ", searchRequstDeEnd=" + searchRequstDeEnd + "]";
	}
	
}
