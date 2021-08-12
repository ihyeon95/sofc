package com.stx.sofc.util.protocol.stxControllPacket;

public class IgniteStruct {
	int condition1;
	int condition3;
	int targetValue;
	
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
	public int getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(int targetValue) {
		this.targetValue = targetValue;
	}
	
	@Override
	public String toString() {
		return "IgniteStruct [condition1=" + condition1 + ", condition3=" + condition3 + ", targetValue=" + targetValue
				+ "]";
	}
	
}
