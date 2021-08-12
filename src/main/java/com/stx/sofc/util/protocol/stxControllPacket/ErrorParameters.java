package com.stx.sofc.util.protocol.stxControllPacket;

public class ErrorParameters {
	ErrorStruct error02_1;
	ErrorStruct error02_2;
	ErrorStruct error02_3;
	ErrorStruct error15_1;
	
	public ErrorStruct getError02_1() {
		return error02_1;
	}
	public void setError02_1(ErrorStruct error02_1) {
		this.error02_1 = error02_1;
	}
	public ErrorStruct getError02_2() {
		return error02_2;
	}
	public void setError02_2(ErrorStruct error02_2) {
		this.error02_2 = error02_2;
	}
	public ErrorStruct getError02_3() {
		return error02_3;
	}
	public void setError02_3(ErrorStruct error02_3) {
		this.error02_3 = error02_3;
	}
	public ErrorStruct getError15_1() {
		return error15_1;
	}
	public void setError15_1(ErrorStruct error15_1) {
		this.error15_1 = error15_1;
	}
	@Override
	public String toString() {
		return "ErrorParameters [error02_1=" + error02_1 + ", error02_2=" + error02_2 + ", error02_3=" + error02_3
				+ ", error15_1=" + error15_1 + "]";
	}
	
	
}
