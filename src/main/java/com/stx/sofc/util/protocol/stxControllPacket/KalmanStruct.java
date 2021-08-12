package com.stx.sofc.util.protocol.stxControllPacket;

public class KalmanStruct {
	byte select;
	byte onoff;
	
	public byte getSelect() {
		return select;
	}
	public void setSelect(byte select) {
		this.select = select;
	}
	public byte getOnoff() {
		return onoff;
	}
	public void setOnoff(byte onoff) {
		this.onoff = onoff;
	}
	@Override
	public String toString() {
		return "KalmanStruct [select=" + select + ", onoff=" + onoff + "]";
	}
	
}
