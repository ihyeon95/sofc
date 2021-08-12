package com.stx.sofc.util.protocol.stxControllPacket;

public class OffsetStruct {
	byte select;
	byte sign;
	int value;
	
	public byte getSelect() {
		return select;
	}
	public void setSelect(byte select) {
		this.select = select;
	}
	public byte getSign() {
		return sign;
	}
	public void setSign(byte sign) {
		this.sign = sign;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "OffsetStruct [select=" + select + ", sign=" + sign + ", value=" + value + "]";
	}
	
}
