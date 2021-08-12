package com.stx.sofc.util.protocol.stxControllPacket;

public class GeneratorParameters {
	byte start;
	byte stop;
	
	public byte getStart() {
		return start;
	}
	public void setStart(byte start) {
		this.start = start;
	}
	public byte getStop() {
		return stop;
	}
	public void setStop(byte stop) {
		this.stop = stop;
	}
	@Override
	public String toString() {
		return "GeneratorParameters [start=" + start + ", stop=" + stop + "]";
	}
	
	
}
