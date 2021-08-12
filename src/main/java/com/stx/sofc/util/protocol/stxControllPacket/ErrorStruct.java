package com.stx.sofc.util.protocol.stxControllPacket;

public class ErrorStruct {
	int condition1;
	int condition3;
	int sec;
	int tc3;
	
	public int getCondition1() {
		return condition1;
	}
	public void setCondition1(int condition1) {
		this.condition1 = condition1;
	}
	public int getCondition3() {
		return condition3;
	}
	public void setCondition3(int condition3) {
		this.condition3 = condition3;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public int getTc3() {
		return tc3;
	}
	public void setTc3(int tc3) {
		this.tc3 = tc3;
	}
	@Override
	public String toString() {
		return "ErrorStruct [condition1=" + condition1 + ", condition3=" + condition3 + ", sec=" + sec + ", tc3=" + tc3
				+ "]";
	}
	
}
