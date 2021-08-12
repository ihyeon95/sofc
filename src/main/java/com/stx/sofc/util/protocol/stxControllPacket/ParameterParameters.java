package com.stx.sofc.util.protocol.stxControllPacket;

public class ParameterParameters {
	Parameter32Struct pump1;
	Parameter32Struct pump2;
	Parameter32Struct pump3;
	Parameter32Struct pump4;
	Parameter32Struct pcs;
	Parameter8Struct snh;
	Parameter8Struct sol1;
	Parameter8Struct sol2;
	Parameter8Struct sol4;
	Parameter8Struct sol5;
	Parameter8Struct sol6;
	Parameter8Struct threeway1;
	byte u8;	// 정상값 복귀(값 미정)
	
	public ParameterParameters() {}
	public ParameterParameters(byte[] buff) {
		
		pump1 = new Parameter32Struct(); 
		pump2 = new Parameter32Struct();
		pump3 = new Parameter32Struct();
		pump4 = new Parameter32Struct();
		pcs = new Parameter32Struct();
		snh = new Parameter8Struct();
		sol1 = new Parameter8Struct();
		sol2 = new Parameter8Struct();
		sol4 = new Parameter8Struct();
		sol5 = new Parameter8Struct();
		sol6 = new Parameter8Struct();
		threeway1 = new Parameter8Struct();

		
		int value = 0;
		
		value |= (((short)buff[13])<<8)&0xFF00;
		value |= (((short)buff[12])<<8)&0xFF00;
		value |= (((short)buff[11])<<8)&0xFF00;
	    value |= (((short)buff[10]))&0xFF;
	    pump1.setSelect(buff[9]);
	    pump1.setValue(value);

	    value = 0;
		value |= (((short)buff[18])<<8)&0xFF00;
		value |= (((short)buff[17])<<8)&0xFF00;
		value |= (((short)buff[16])<<8)&0xFF00;
	    value |= (((short)buff[15]))&0xFF;
	    pump2.setSelect(buff[14]);
	    pump2.setValue(value);

	    value = 0;
		value |= (((short)buff[23])<<8)&0xFF00;
		value |= (((short)buff[22])<<8)&0xFF00;
		value |= (((short)buff[21])<<8)&0xFF00;
	    value |= (((short)buff[20]))&0xFF;
	    pump3.setSelect(buff[19]);
	    pump3.setValue(value);
	    
	    value = 0;
		value |= (((short)buff[28])<<8)&0xFF00;
		value |= (((short)buff[27])<<8)&0xFF00;
		value |= (((short)buff[26])<<8)&0xFF00;
	    value |= (((short)buff[25]))&0xFF;
	    pump4.setSelect(buff[24]);
	    pump4.setValue(value);
	    
	    value = 0;
		value |= (((short)buff[33])<<8)&0xFF00;
		value |= (((short)buff[32])<<8)&0xFF00;
		value |= (((short)buff[31])<<8)&0xFF00;
	    value |= (((short)buff[30]))&0xFF;
	    pcs.setSelect(buff[29]);
	    pcs.setValue(value);
	    
	    snh.setSelect(buff[34]);
	    snh.setValue(buff[35]);
	    
	    sol1.setSelect(buff[36]);
	    sol1.setValue(buff[37]);
	    
	    sol2.setSelect(buff[38]);
	    sol2.setValue(buff[39]);
	    
	    sol4.setSelect(buff[40]);
	    sol4.setValue(buff[41]);
	    
	    sol5.setSelect(buff[42]);
	    sol5.setValue(buff[43]);
	    
	    sol6.setSelect(buff[44]);
	    sol6.setValue(buff[45]);
	    
	    threeway1.setSelect(buff[46]);
	    threeway1.setValue(buff[47]);
	    
	    u8 = buff[48];
	    
	}
	
	public Parameter32Struct getPump1() {
		return pump1;
	}
	public void setPump1(Parameter32Struct pump1) {
		this.pump1 = pump1;
	}
	public Parameter32Struct getPump2() {
		return pump2;
	}
	public void setPump2(Parameter32Struct pump2) {
		this.pump2 = pump2;
	}
	public Parameter32Struct getPump3() {
		return pump3;
	}
	public void setPump3(Parameter32Struct pump3) {
		this.pump3 = pump3;
	}
	public Parameter32Struct getPump4() {
		return pump4;
	}
	public void setPump4(Parameter32Struct pump4) {
		this.pump4 = pump4;
	}
	public Parameter32Struct getPcs() {
		return pcs;
	}
	public void setPcs(Parameter32Struct pcs) {
		this.pcs = pcs;
	}
	public Parameter8Struct getSnh() {
		return snh;
	}
	public void setSnh(Parameter8Struct snh) {
		this.snh = snh;
	}
	public Parameter8Struct getSol1() {
		return sol1;
	}
	public void setSol1(Parameter8Struct sol1) {
		this.sol1 = sol1;
	}
	public Parameter8Struct getSol2() {
		return sol2;
	}
	public void setSol2(Parameter8Struct sol2) {
		this.sol2 = sol2;
	}
	public Parameter8Struct getSol4() {
		return sol4;
	}
	public void setSol4(Parameter8Struct sol4) {
		this.sol4 = sol4;
	}
	public Parameter8Struct getSol5() {
		return sol5;
	}
	public void setSol5(Parameter8Struct sol5) {
		this.sol5 = sol5;
	}
	public Parameter8Struct getSol6() {
		return sol6;
	}
	public void setSol6(Parameter8Struct sol6) {
		this.sol6 = sol6;
	}
	public Parameter8Struct getThreeway1() {
		return threeway1;
	}
	public void setThreeway1(Parameter8Struct threeway1) {
		this.threeway1 = threeway1;
	}
	public byte getU8() {
		return u8;
	}
	public void setU8(byte u8) {
		this.u8 = u8;
	}
	@Override
	public String toString() {
		return "ParameterParameters [pump1=" + pump1 + ", pump2=" + pump2 + ", pump3=" + pump3 + ", pump4=" + pump4
				+ ", pcs=" + pcs + ", snh=" + snh + ", sol1=" + sol1 + ", sol2=" + sol2 + ", sol4=" + sol4 + ", sol5="
				+ sol5 + ", sol6=" + sol6 + ", threeway1=" + threeway1 + ", u8=" + u8 + "]";
	}
}
