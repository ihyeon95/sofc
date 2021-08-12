package com.stx.sofc.util.protocol.stxControllPacket;

public class GainParameters {
	GainStruct pump1;
	GainStruct pump2;
	GainStruct pump3;
	GainStruct pump4;
	GainStruct fl1;
	GainStruct fl2;
	GainStruct fl3;
	
	public GainParameters() {}
	public GainParameters(byte[] buff) {
		
		pump1 = new GainStruct();
		pump2 = new GainStruct();
		pump3 = new GainStruct();
		pump4 = new GainStruct();
		fl1 = new GainStruct();
		fl2 = new GainStruct();
		fl3 = new GainStruct();
		
		int value = 0;
    	
    	value |= (((int)buff[13])<<8)&0xFF00;
    	value |= (((int)buff[12])<<8)&0xFF00;
    	value |= (((int)buff[11])<<8)&0xFF00;
        value |= (((int)buff[10]))&0xFF;
        pump1.setSelect(buff[9]);
        pump1.setValue(value);

        value = 0;
    	value |= (((int)buff[18])<<8)&0xFF00;
    	value |= (((int)buff[17])<<8)&0xFF00;
    	value |= (((int)buff[16])<<8)&0xFF00;
        value |= (((int)buff[15]))&0xFF;
        pump2.setSelect(buff[14]);
        pump2.setValue(value);

        value = 0;
    	value |= (((int)buff[23])<<8)&0xFF00;
    	value |= (((int)buff[22])<<8)&0xFF00;
    	value |= (((int)buff[21])<<8)&0xFF00;
        value |= (((int)buff[20]))&0xFF;
        pump3.setSelect(buff[19]);
        pump3.setValue(value);
        
        value = 0;
    	value |= (((int)buff[28])<<8)&0xFF00;
    	value |= (((int)buff[27])<<8)&0xFF00;
    	value |= (((int)buff[26])<<8)&0xFF00;
        value |= (((int)buff[25]))&0xFF;
        pump4.setSelect(buff[24]);
        pump4.setValue(value);
        
        value = 0;
    	value |= (((int)buff[33])<<8)&0xFF00;
    	value |= (((int)buff[32])<<8)&0xFF00;
    	value |= (((int)buff[31])<<8)&0xFF00;
        value |= (((int)buff[30]))&0xFF;
        fl1.setSelect(buff[29]);
        fl1.setValue(value);
        
        value = 0;
    	value |= (((int)buff[38])<<8)&0xFF00;
    	value |= (((int)buff[37])<<8)&0xFF00;
    	value |= (((int)buff[36])<<8)&0xFF00;
        value |= (((int)buff[35]))&0xFF;
        fl2.setSelect(buff[34]);
        fl2.setValue(value);
        
        value = 0;
    	value |= (((int)buff[43])<<8)&0xFF00;
    	value |= (((int)buff[42])<<8)&0xFF00;
    	value |= (((int)buff[41])<<8)&0xFF00;
        value |= (((int)buff[40]))&0xFF;
        fl3.setSelect(buff[39]);
        fl3.setValue(value);
	}
	
	public GainStruct getPump1() {
		return pump1;
	}
	public void setPump1(GainStruct pump1) {
		this.pump1 = pump1;
	}
	public GainStruct getPump2() {
		return pump2;
	}
	public void setPump2(GainStruct pump2) {
		this.pump2 = pump2;
	}
	public GainStruct getPump3() {
		return pump3;
	}
	public void setPump3(GainStruct pump3) {
		this.pump3 = pump3;
	}
	public GainStruct getPump4() {
		return pump4;
	}
	public void setPump4(GainStruct pump4) {
		this.pump4 = pump4;
	}
	public GainStruct getFl1() {
		return fl1;
	}
	public void setFl1(GainStruct fl1) {
		this.fl1 = fl1;
	}
	public GainStruct getFl2() {
		return fl2;
	}
	public void setFl2(GainStruct fl2) {
		this.fl2 = fl2;
	}
	public GainStruct getFl3() {
		return fl3;
	}
	public void setFl3(GainStruct fl3) {
		this.fl3 = fl3;
	}
	
}
