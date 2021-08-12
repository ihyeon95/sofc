package com.stx.sofc.util.protocol.stxControllPacket;

import java.util.Arrays;

public class IgniteParameters {
	IgniteStruct pump3;
	IgniteStruct pump2;
	IgniteStruct snh;
	
	public IgniteStruct getPump3() {
		return pump3;
	}
	public void setPump3(IgniteStruct pump3) {
		this.pump3 = pump3;
	}
	public IgniteStruct getPump2() {
		return pump2;
	}
	public void setPump2(IgniteStruct pump2) {
		this.pump2 = pump2;
	}
	public IgniteStruct getSnh() {
		return snh;
	}
	public void setSnh(IgniteStruct snh) {
		this.snh = snh;
	}
	@Override
	public String toString() {
		return "IgniteParameters [pump3=" + pump3 + ", pump2=" + pump2 + ", snh=" + snh + "]";
	}
	
	
	
	
}
