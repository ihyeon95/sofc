package com.stx.sofc.util.protocol.stxControllPacket;

public class HeatingStruct {
	int condition2;
	int speedValue;
	int targetValue;
	
	public int getCondition2() {
		return condition2;
	}
	public void setCondition2(int condition2) {
		this.condition2 = condition2;
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
		return "HeatingStruct [condition2=" + condition2 + ", speedValue=" + speedValue + ", targetValue=" + targetValue
				+ "]";
	}
	
}
