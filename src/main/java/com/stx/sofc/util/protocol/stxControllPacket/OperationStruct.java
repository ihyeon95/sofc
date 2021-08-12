package com.stx.sofc.util.protocol.stxControllPacket;

public class OperationStruct {
	int condition1;
	int condition3;
	int speedValue;
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
	public int getSpeedValue() {
		return speedValue;
	}
	public void setSpeedValue(int speedValue) {
		this.speedValue = speedValue;
	}
	public int getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(int targetValue) {
		this.targetValue = targetValue;
	}
	@Override
	public String toString() {
		return "OperationStruct [condition1=" + condition1 + ", condition3=" + condition3 + ", speedValue=" + speedValue
				+ ", targetValue=" + targetValue + "]";
	}
}
