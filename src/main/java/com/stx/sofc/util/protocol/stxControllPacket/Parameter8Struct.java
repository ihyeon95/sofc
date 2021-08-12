package com.stx.sofc.util.protocol.stxControllPacket;

public class Parameter8Struct {
	byte select;
	byte value;		// on/off (1/0)
	
	public byte getSelect() {
		return select;
	}
	public void setSelect(byte select) {
		this.select = select;
	}
	public byte getValue() {
		return value;
	}
	public void setValue(byte value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Parameter8Struct [select=" + select + ", value=" + value + "]";
	}
}
