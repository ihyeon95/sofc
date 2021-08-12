package com.stx.sofc.util.protocol.stxControllPacket;

public class SimulationModeParameters {
	byte mode;

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "SimulationModeParameters [mode=" + mode + "]";
	}
	
}
