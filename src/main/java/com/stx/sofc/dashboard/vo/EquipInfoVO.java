package com.stx.sofc.dashboard.vo;

public class EquipInfoVO {
	
	private String iRtuNum;

	private String iInverterCapa;
	private String sInverterMFR;
	private String sInverterName;

	private String sAsDate;
	private String sAsReason;
	
	public String getiRtuNum() {
		return iRtuNum;
	}
	public void setiRtuNum(String iRtuNum) {
		this.iRtuNum = iRtuNum;
	}
	public String getiInverterCapa() {
		return iInverterCapa;
	}
	public void setiInverterCapa(String iInverterCapa) {
		this.iInverterCapa = iInverterCapa;
	}
	public String getsInverterMFR() {
		return sInverterMFR;
	}
	public void setsInverterMFR(String sInverterMFR) {
		this.sInverterMFR = sInverterMFR;
	}
	public String getsInverterName() {
		return sInverterName;
	}
	public void setsInverterName(String sInverterName) {
		this.sInverterName = sInverterName;
	}
	public String getsAsDate() {
		return sAsDate;
	}
	public void setsAsDate(String sAsDate) {
		this.sAsDate = sAsDate;
	}
	public String getsAsReason() {
		return sAsReason;
	}
	public void setsAsReason(String sAsReason) {
		this.sAsReason = sAsReason;
	}
	
	
}
