package com.stx.sofc.util.protocol.stxControllPacket;

public class OffsetParameters {
	
	OffsetStruct pump1;
	OffsetStruct pump2;
	OffsetStruct pump3;
	OffsetStruct pump4;
	OffsetStruct fl1;
	OffsetStruct fl2;
	OffsetStruct fl3;
	OffsetStruct tc1;
	OffsetStruct tc2;
	OffsetStruct tc3;
	OffsetStruct tc4;
	OffsetStruct tc5;
	OffsetStruct tc6;
	OffsetStruct tc7;
	OffsetStruct tc8;
	OffsetStruct tc9;
	OffsetStruct tc10;
	OffsetStruct tc11;
	
	byte[] tempArray = new byte[4];
	
	public OffsetParameters() {}
	public OffsetParameters(byte[] buff) {
		pump1= new OffsetStruct();
		pump2= new OffsetStruct();
		pump3= new OffsetStruct();
		pump4= new OffsetStruct();
		fl1= new OffsetStruct();
		fl2= new OffsetStruct();
		fl3= new OffsetStruct();
		tc1= new OffsetStruct();
		tc2= new OffsetStruct();
		tc3= new OffsetStruct();
		tc4= new OffsetStruct();
		tc5= new OffsetStruct();
		tc6= new OffsetStruct();
		tc7= new OffsetStruct();
		tc8= new OffsetStruct();
		tc9= new OffsetStruct();
		tc10= new OffsetStruct();
		tc11= new OffsetStruct();
		
		int value = 0;
    	value |= (((short)buff[14])<<8)&0xFF00;
    	value |= (((short)buff[13])<<8)&0xFF00;
    	value |= (((short)buff[12])<<8)&0xFF00;
        value |= (((short)buff[11]))&0xFF;
        pump1.setSign(buff[10]);
        pump1.setSelect(buff[9]);
        pump1.setValue(value);

        value = 0;
    	value |= (((short)buff[20])<<8)&0xFF00;
    	value |= (((short)buff[19])<<8)&0xFF00;
    	value |= (((short)buff[18])<<8)&0xFF00;
        value |= (((short)buff[17]))&0xFF;
        pump2.setSign(buff[16]);
        pump2.setSelect(buff[15]);
        pump2.setValue(value);

        value = 0;
    	value |= (((short)buff[26])<<8)&0xFF00;
    	value |= (((short)buff[25])<<8)&0xFF00;
    	value |= (((short)buff[24])<<8)&0xFF00;
        value |= (((short)buff[23]))&0xFF;
        pump3.setSign(buff[22]);
        pump3.setSelect(buff[21]);
        pump3.setValue(value);
        
        value = 0;
    	value |= (((short)buff[32])<<8)&0xFF00;
    	value |= (((short)buff[31])<<8)&0xFF00;
    	value |= (((short)buff[30])<<8)&0xFF00;
        value |= (((short)buff[29]))&0xFF;
        pump4.setSign(buff[28]);
        pump4.setSelect(buff[27]);
        pump4.setValue(value);
        
        value = 0;
    	value |= (((short)buff[38])<<8)&0xFF00;
    	value |= (((short)buff[37])<<8)&0xFF00;
    	value |= (((short)buff[36])<<8)&0xFF00;
        value |= (((short)buff[35]))&0xFF;
        fl1.setSign(buff[34]);
        fl1.setSelect(buff[33]);
        fl1.setValue(value);
        
        value = 0;
    	value |= (((short)buff[44])<<8)&0xFF00;
    	value |= (((short)buff[43])<<8)&0xFF00;
    	value |= (((short)buff[42])<<8)&0xFF00;
        value |= (((short)buff[41]))&0xFF;
        fl2.setSign(buff[40]);
        fl2.setSelect(buff[39]);
        fl2.setValue(value);
        
        value = 0;
    	value |= (((short)buff[50])<<8)&0xFF00;
    	value |= (((short)buff[49])<<8)&0xFF00;
    	value |= (((short)buff[48])<<8)&0xFF00;
        value |= (((short)buff[47]))&0xFF;
        fl3.setSign(buff[46]);
        fl3.setSelect(buff[45]);
        fl3.setValue(value);
        
//        value = 0;
        tempArray[0] = buff[56];
        tempArray[1] = buff[55];
        tempArray[2] = buff[54];
        tempArray[3] = buff[53];
        
        tc1.setSign(buff[52]);
        tc1.setSelect(buff[51]);
        tc1.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[62])<<8)&0xFF00;
//    	value |= (((short)buff[61])<<8)&0xFF00;
//    	value |= (((short)buff[60])<<8)&0xFF00;
//    	value |= (((short)buff[59])<<8)&0xFF00;
    	tempArray[0] = buff[62];
        tempArray[1] = buff[61];
        tempArray[2] = buff[60];
        tempArray[3] = buff[59];
        tc2.setSign(buff[58]);
        tc2.setSelect(buff[57]);
        tc2.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[68])<<8)&0xFF00;
//    	value |= (((short)buff[67])<<8)&0xFF00;
//    	value |= (((short)buff[66])<<8)&0xFF00;
//        value |= (((short)buff[65]))&0xFF;
        tempArray[0] = buff[68];
        tempArray[1] = buff[67];
        tempArray[2] = buff[66];
        tempArray[3] = buff[65];
        tc3.setSign(buff[64]);
        tc3.setSelect(buff[63]);
        tc3.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[74])<<8)&0xFF00;
//    	value |= (((short)buff[73])<<8)&0xFF00;
//    	value |= (((short)buff[72])<<8)&0xFF00;
//        value |= (((short)buff[71]))&0xFF;
        tempArray[0] = buff[74];
        tempArray[1] = buff[73];
        tempArray[2] = buff[72];
        tempArray[3] = buff[71];
        tc4.setSign(buff[70]);
        tc4.setSelect(buff[69]);
        tc4.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[80])<<8)&0xFF00;
//    	value |= (((short)buff[79])<<8)&0xFF00;
//    	value |= (((short)buff[78])<<8)&0xFF00;
//        value |= (((short)buff[77]))&0xFF;
        tempArray[0] = buff[80];
        tempArray[1] = buff[79];
        tempArray[2] = buff[78];
        tempArray[3] = buff[77];
        tc5.setSign(buff[76]);
        tc5.setSelect(buff[75]);
        tc5.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[86])<<8)&0xFF00;
//    	value |= (((short)buff[85])<<8)&0xFF00;
//    	value |= (((short)buff[84])<<8)&0xFF00;
//        value |= (((short)buff[83]))&0xFF;
        tempArray[0] = buff[86];
        tempArray[1] = buff[85];
        tempArray[2] = buff[84];
        tempArray[3] = buff[83];
        tc6.setSign(buff[82]);
        tc6.setSelect(buff[81]);
        tc6.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[92])<<8)&0xFF00;
//    	value |= (((short)buff[91])<<8)&0xFF00;
//    	value |= (((short)buff[90])<<8)&0xFF00;
//        value |= (((short)buff[89]))&0xFF;
        tempArray[0] = buff[92];
        tempArray[1] = buff[91];
        tempArray[2] = buff[90];
        tempArray[3] = buff[89];
        tc7.setSign(buff[88]);
        tc7.setSelect(buff[87]);
        tc7.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[98])<<8)&0xFF00;
//    	value |= (((short)buff[97])<<8)&0xFF00;
//    	value |= (((short)buff[96])<<8)&0xFF00;
//        value |= (((short)buff[95]))&0xFF;
        tempArray[0] = buff[98];
        tempArray[1] = buff[97];
        tempArray[2] = buff[96];
        tempArray[3] = buff[95];
        tc8.setSign(buff[94]);
        tc8.setSelect(buff[93]);
        tc8.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[104])<<8)&0xFF00;
//    	value |= (((short)buff[103])<<8)&0xFF00;
//    	value |= (((short)buff[102])<<8)&0xFF00;
//        value |= (((short)buff[101]))&0xFF;
        tempArray[0] = buff[104];
        tempArray[1] = buff[103];
        tempArray[2] = buff[102];
        tempArray[3] = buff[101];
        tc9.setSign(buff[100]);
        tc9.setSelect(buff[99]);
        tc9.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[110])<<8)&0xFF00;
//    	value |= (((short)buff[109])<<8)&0xFF00;
//    	value |= (((short)buff[108])<<8)&0xFF00;
//        value |= (((short)buff[107]))&0xFF;
        tempArray[0] = buff[110];
        tempArray[1] = buff[109];
        tempArray[2] = buff[108];
        tempArray[3] = buff[107];
        tc10.setSign(buff[106]);
        tc10.setSelect(buff[105]);
        tc10.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
        
//        value = 0;
//    	value |= (((short)buff[116])<<8)&0xFF00;
//    	value |= (((short)buff[115])<<8)&0xFF00;
//    	value |= (((short)buff[114])<<8)&0xFF00;
//        value |= (((short)buff[113]))&0xFF;
        tempArray[0] = buff[116];
        tempArray[1] = buff[115];
        tempArray[2] = buff[114];
        tempArray[3] = buff[113];
        tc11.setSign(buff[112]);
        tc11.setSelect(buff[111]);
        tc11.setValue(Integer.parseInt(byteArrayToHex(tempArray), 16));
	}
	
	public OffsetStruct getPump1() {
		return pump1;
	}
	public void setPump1(OffsetStruct pump1) {
		this.pump1 = pump1;
	}
	public OffsetStruct getPump2() {
		return pump2;
	}
	public void setPump2(OffsetStruct pump2) {
		this.pump2 = pump2;
	}
	public OffsetStruct getPump3() {
		return pump3;
	}
	public void setPump3(OffsetStruct pump3) {
		this.pump3 = pump3;
	}
	public OffsetStruct getPump4() {
		return pump4;
	}
	public void setPump4(OffsetStruct pump4) {
		this.pump4 = pump4;
	}
	public OffsetStruct getFl1() {
		return fl1;
	}
	public void setFl1(OffsetStruct fl1) {
		this.fl1 = fl1;
	}
	public OffsetStruct getFl2() {
		return fl2;
	}
	public void setFl2(OffsetStruct fl2) {
		this.fl2 = fl2;
	}
	public OffsetStruct getFl3() {
		return fl3;
	}
	public void setFl3(OffsetStruct fl3) {
		this.fl3 = fl3;
	}
	public OffsetStruct getTc1() {
		return tc1;
	}
	public void setTc1(OffsetStruct tc1) {
		this.tc1 = tc1;
	}
	public OffsetStruct getTc2() {
		return tc2;
	}
	public void setTc2(OffsetStruct tc2) {
		this.tc2 = tc2;
	}
	public OffsetStruct getTc3() {
		return tc3;
	}
	public void setTc3(OffsetStruct tc3) {
		this.tc3 = tc3;
	}
	public OffsetStruct getTc4() {
		return tc4;
	}
	public void setTc4(OffsetStruct tc4) {
		this.tc4 = tc4;
	}
	public OffsetStruct getTc5() {
		return tc5;
	}
	public void setTc5(OffsetStruct tc5) {
		this.tc5 = tc5;
	}
	public OffsetStruct getTc6() {
		return tc6;
	}
	public void setTc6(OffsetStruct tc6) {
		this.tc6 = tc6;
	}
	public OffsetStruct getTc7() {
		return tc7;
	}
	public void setTc7(OffsetStruct tc7) {
		this.tc7 = tc7;
	}
	public OffsetStruct getTc8() {
		return tc8;
	}
	public void setTc8(OffsetStruct tc8) {
		this.tc8 = tc8;
	}
	public OffsetStruct getTc9() {
		return tc9;
	}
	public void setTc9(OffsetStruct tc9) {
		this.tc9 = tc9;
	}
	public OffsetStruct getTc10() {
		return tc10;
	}
	public void setTc10(OffsetStruct tc10) {
		this.tc10 = tc10;
	}
	public OffsetStruct getTc11() {
		return tc11;
	}
	public void setTc11(OffsetStruct tc11) {
		this.tc11 = tc11;
	}
	@Override
	public String toString() {
		return "OffsetParameters [pump1=" + pump1 + ", pump2=" + pump2 + ", pump3=" + pump3 + ", pump4=" + pump4
				+ ", fl1=" + fl1 + ", fl2=" + fl2 + ", fl3=" + fl3 + ", tc1=" + tc1 + ", tc2=" + tc2 + ", tc3=" + tc3
				+ ", tc4=" + tc4 + ", tc5=" + tc5 + ", tc6=" + tc6 + ", tc7=" + tc7 + ", tc8=" + tc8 + ", tc9=" + tc9
				+ ", tc10=" + tc10 + ", tc11=" + tc11 + "]";
	}
	
	
	String byteArrayToHex(byte[] a) {
	    StringBuilder sb = new StringBuilder();
	    for(final byte b: a)
	        sb.append(String.format("%02x", b&0xff));
	    return sb.toString();
	}
}
