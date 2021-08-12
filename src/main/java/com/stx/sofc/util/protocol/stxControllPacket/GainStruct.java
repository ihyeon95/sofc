package com.stx.sofc.util.protocol.stxControllPacket;

public class GainStruct {
	byte select;
	int value;
	
	public byte getSelect() {
		return select;
	}
	public void setSelect(byte select) {
		this.select = select;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "GainStruct [select=" + select + ", value=" + value + "]";
	}
	
}
