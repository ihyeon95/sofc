package com.stx.sofc.util.protocol.stxControllPacket;

public class SimulationActiveParameters {

	byte error_no;
	
	short pt1;
	short tc02;
	
	public byte getError_no() {
		return error_no;
	}
	public void setError_no(byte error_no) {
		this.error_no = error_no;
	}
	public short getPt1() {
		return pt1;
	}
	public void setPt1(short pt1) {
		this.pt1 = pt1;
	}
	public short getTc02() {
		return tc02;
	}
	public void setTc02(short tc02) {
		this.tc02 = tc02;
	}
	@Override
	public String toString() {
		return "SimulationModeParameters [error_no=" + error_no + ", pt1=" + pt1 + ", tc02=" + tc02 + "]";
	}
	
	
}
