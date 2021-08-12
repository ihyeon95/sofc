package com.stx.sofc.util.protocol.stxControllPacket;

public class KalmanParameters {
	KalmanStruct tc1;
	KalmanStruct tc2;
	KalmanStruct tc3;
	KalmanStruct tc4;
	KalmanStruct tc5;
	KalmanStruct tc6;
	KalmanStruct tc7;
	KalmanStruct tc8;
	KalmanStruct tc9;
	KalmanStruct tc10;
	KalmanStruct tc11;
	
	public KalmanParameters() {}
	public KalmanParameters(byte[] buff) {

		tc1 = new KalmanStruct();
		tc2 = new KalmanStruct();
		tc3 = new KalmanStruct();
		tc4 = new KalmanStruct();
		tc5 = new KalmanStruct();
		tc6 = new KalmanStruct();
		tc7 = new KalmanStruct();
		tc8 = new KalmanStruct();
		tc9 = new KalmanStruct();
		tc10 = new KalmanStruct();
		tc11 = new KalmanStruct();
		
		int i = 9;
		tc1.setSelect(buff[i]);
		tc1.setOnoff(buff[++i]);

		tc2.setSelect(buff[++i]);
		tc2.setOnoff(buff[++i]);

		tc3.setSelect(buff[++i]);
		tc3.setOnoff(buff[++i]);

		tc4.setSelect(buff[++i]);
		tc4.setOnoff(buff[++i]);

		tc5.setSelect(buff[++i]);
		tc5.setOnoff(buff[++i]);

		tc6.setSelect(buff[++i]);
		tc6.setOnoff(buff[++i]);

		tc7.setSelect(buff[++i]);
		tc7.setOnoff(buff[++i]);

		tc8.setSelect(buff[++i]);
		tc8.setOnoff(buff[++i]);

		tc9.setSelect(buff[++i]);
		tc9.setOnoff(buff[++i]);

		tc10.setSelect(buff[++i]);
		tc10.setOnoff(buff[++i]);

		tc11.setSelect(buff[++i]);
		tc11.setOnoff(buff[++i]);
		
		
	}
	
	public KalmanStruct getTc1() {
		return tc1;
	}
	public void setTc1(KalmanStruct tc1) {
		this.tc1 = tc1;
	}
	public KalmanStruct getTc2() {
		return tc2;
	}
	public void setTc2(KalmanStruct tc2) {
		this.tc2 = tc2;
	}
	public KalmanStruct getTc3() {
		return tc3;
	}
	public void setTc3(KalmanStruct tc3) {
		this.tc3 = tc3;
	}
	public KalmanStruct getTc4() {
		return tc4;
	}
	public void setTc4(KalmanStruct tc4) {
		this.tc4 = tc4;
	}
	public KalmanStruct getTc5() {
		return tc5;
	}
	public void setTc5(KalmanStruct tc5) {
		this.tc5 = tc5;
	}
	public KalmanStruct getTc6() {
		return tc6;
	}
	public void setTc6(KalmanStruct tc6) {
		this.tc6 = tc6;
	}
	public KalmanStruct getTc7() {
		return tc7;
	}
	public void setTc7(KalmanStruct tc7) {
		this.tc7 = tc7;
	}
	public KalmanStruct getTc8() {
		return tc8;
	}
	public void setTc8(KalmanStruct tc8) {
		this.tc8 = tc8;
	}
	public KalmanStruct getTc9() {
		return tc9;
	}
	public void setTc9(KalmanStruct tc9) {
		this.tc9 = tc9;
	}
	public KalmanStruct getTc10() {
		return tc10;
	}
	public void setTc10(KalmanStruct tc10) {
		this.tc10 = tc10;
	}
	public KalmanStruct getTc11() {
		return tc11;
	}
	public void setTc11(KalmanStruct tc11) {
		this.tc11 = tc11;
	}
	
}
