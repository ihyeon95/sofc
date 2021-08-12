package com.stx.sofc.util.protocol;

import com.stx.sofc.util.protocol.stxControllPacket.EndWaterParameters;
import com.stx.sofc.util.protocol.stxControllPacket.ErrorParameters;
import com.stx.sofc.util.protocol.stxControllPacket.GainParameters;
import com.stx.sofc.util.protocol.stxControllPacket.GeneratorParameters;
import com.stx.sofc.util.protocol.stxControllPacket.HeatingParameters;
import com.stx.sofc.util.protocol.stxControllPacket.IgniteParameters;
import com.stx.sofc.util.protocol.stxControllPacket.KalmanParameters;
import com.stx.sofc.util.protocol.stxControllPacket.OffsetParameters;
import com.stx.sofc.util.protocol.stxControllPacket.OperationParameters;
import com.stx.sofc.util.protocol.stxControllPacket.ParameterParameters;
import com.stx.sofc.util.protocol.stxControllPacket.SimulationActiveParameters;
import com.stx.sofc.util.protocol.stxControllPacket.SimulationModeParameters;

/**
* <pre>
* 1. 패키지명 : com.stx.sofc.util.protocol
* 2. 타입명 : Packet.java
* 3. 작성일 : 2020. 4. 2. 오후 9:16:49
* 4. 작성자 : yousung
* 5. 설명 : 보드 명령용 패킷 데이터 생성 클래스
* </pre>
*/
public class Packet {

	private byte[] header;
	private byte[] data;
	private byte[] length = {0x00, 0x00};	// length of data
	
	private byte[] RTU = {0x00, 0x00};		// RTU_ID
	private byte[] BD = {0x00, 0x00};		// B/D ID
	
	/**
	 * Usage
	 * 1. Make Instance with command parameter
	 * 2. setRTU_ID()
	 * 3. setBD_ID()
	 * 4. setHeader()
	 * 5. make body(data)
	 * 6. add checksum byte using getChecksum()
	 */
	public Packet(byte command) {
		// header
		header = new byte[PacketDef.HEADER_SIZE];
		
		// data ( +1 이 있는 이유 : 마지막 바이트에 ETX 삽입 -> checksum 구한 후 다시 마지막에 checksum byte 추가
		switch(command) {
			case PacketDef.IGNITE :
			case PacketDef.IGNITE_MOD :
				data = new byte[PacketDef.DATA_IGNITE_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_IGNITE_SIZE, length);
				break;
			case PacketDef.HEATING :
			case PacketDef.HEATING_MOD :
				data = new byte[PacketDef.DATA_HEATING_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_HEATING_SIZE, length);
				break;
			case PacketDef.OPERATION :
			case PacketDef.OPERATION_MOD :
				data = new byte[PacketDef.DATA_OPERATION_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_OPERATION_SIZE, length);
				break;
			case PacketDef.ENDWATER :
			case PacketDef.ENDWATER_MOD :
				data = new byte[PacketDef.DATA_ENDWATER_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_ENDWATER_SIZE, length);
				break;
			case PacketDef.PARAMETER :
			case PacketDef.PARAMETER_MOD :
				data = new byte[PacketDef.DATA_PARAMETER_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_PARAMETER_SIZE, length);
				break;
			case PacketDef.ERROR :
			case PacketDef.ERROR_MOD :
				data = new byte[PacketDef.DATA_ERROR_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_ERROR_SIZE, length);
				break;
			case PacketDef.GAIN :
			case PacketDef.GAIN_MOD :
				data = new byte[PacketDef.DATA_GAIN_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_GAIN_SIZE, length);
				break;
			case PacketDef.OFFSET :
			case PacketDef.OFFSET_MOD :
				data = new byte[PacketDef.DATA_OFFSET_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_OFFSET_SIZE, length);
				break;
			case PacketDef.KALMAN : 
			case PacketDef.KALMAN_MOD : 
				data = new byte[PacketDef.DATA_KALMAN_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_KALMAN_SIZE, length);
				break;
			case PacketDef.SIMULATION_ACTIVE : 
				data = new byte[PacketDef.DATA_SIMULATION_ACTIVE_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_SIMULATION_ACTIVE_SIZE, length);
				break;
			case PacketDef.SIMULATION_MODE : 
				data = new byte[PacketDef.DATA_SIMULATION_MODE_SIZE + 1];
				shortToBytes_LE(PacketDef.DATA_SIMULATION_MODE_SIZE, length);
				break;
			case PacketDef.GENERATOR_MOD : 
				data = new byte[PacketDef.GENERATOR_MOD_SIZE + 1];
				shortToBytes_LE(PacketDef.GENERATOR_MOD_SIZE, length);
				break;	
				
			default :
				break;
		}
		
		// ETX
		data[data.length-1] = PacketDef.ETX;
	}
	
	public void setRTU_ID(short rtuId) {
		shortToBytes_LE(rtuId, RTU);
	}
	
	public void setBD_ID(short bdId) {
		shortToBytes_LE(bdId, BD);
	}
	
	public byte[] getHeader() {
		return header;
	}
	
	public byte[] getBody() {
		return data;
	}
	
	public void setHeader(byte command) {
		if(header != null && header.length > 0) {
			header[0] = PacketDef.STX;
			header[1] = RTU[0];
			header[2] = RTU[1];
			header[3] = BD[0];
			header[4] = BD[1];
			header[5] = command;
			header[6] = PacketDef.KIND;
			header[7] = length[0];
			header[8] = length[1];
		}
	}
	
	public void setData(byte command, Object obj) {
		byte[] int4 = new byte[4];
		byte[] short2 = new byte[2];
		
		switch(command) {
			case PacketDef.IGNITE :
				IgniteParameters ignite = (IgniteParameters) obj;

				// pump3
				int4 = intToBytes_LE(ignite.getPump3().getCondition1());
				System.arraycopy(int4, 0, data, 0, 4);
				int4 = intToBytes_LE(ignite.getPump3().getCondition3());
				System.arraycopy(int4, 0, data, 4, 4);
				int4 = intToBytes_LE(ignite.getPump3().getTargetValue());
				System.arraycopy(int4, 0, data, 8, 4);
				
				// pump2
				int4 = intToBytes_LE(ignite.getPump2().getCondition1());
				System.arraycopy(int4, 0, data, 12, 4);
				int4 = intToBytes_LE(ignite.getPump2().getCondition3());
				System.arraycopy(int4, 0, data, 16, 4);
				int4 = intToBytes_LE(ignite.getPump2().getTargetValue());
				System.arraycopy(int4, 0, data, 20, 4);
				
				// snh
				int4 = intToBytes_LE(ignite.getSnh().getCondition1());
				System.arraycopy(int4, 0, data, 24, 4);
				int4 = intToBytes_LE(ignite.getSnh().getCondition3());
				System.arraycopy(int4, 0, data, 28, 4);
				int4 = intToBytes_LE(ignite.getSnh().getTargetValue());
				System.arraycopy(int4, 0, data, 32, 4);
				
				break;
			case PacketDef.HEATING :
			case PacketDef.HEATING_MOD :
				HeatingParameters heating = (HeatingParameters) obj;
				
				// pump3_1
				int4 = intToBytes_LE(heating.getPump3_1().getCondition2());
				System.arraycopy(int4, 0, data, 0, 4);
				int4 = intToBytes_LE(heating.getPump3_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 4, 4);
				int4 = intToBytes_LE(heating.getPump3_1().getTargetValue());
				System.arraycopy(int4, 0, data, 8, 4);
				
				// pump1_1
				int4 = intToBytes_LE(heating.getPump1_1().getCondition2());
				System.arraycopy(int4, 0, data, 12, 4);
				int4 = intToBytes_LE(heating.getPump1_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 16, 4);
				int4 = intToBytes_LE(heating.getPump1_1().getTargetValue());
				System.arraycopy(int4, 0, data, 20, 4);
				
				// pump2_1
				int4 = intToBytes_LE(heating.getPump2_1().getCondition2());
				System.arraycopy(int4, 0, data, 24, 4);
				int4 = intToBytes_LE(heating.getPump2_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 28, 4);
				int4 = intToBytes_LE(heating.getPump2_1().getTargetValue());
				System.arraycopy(int4, 0, data, 32, 4);
				
				// pump4_1
				int4 = intToBytes_LE(heating.getPump4_1().getCondition2());
				System.arraycopy(int4, 0, data, 36, 4);
				int4 = intToBytes_LE(heating.getPump4_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 40, 4);
				int4 = intToBytes_LE(heating.getPump4_1().getTargetValue());
				System.arraycopy(int4, 0, data, 44, 4);
				
				// pump1_2
				int4 = intToBytes_LE(heating.getPump1_2().getCondition2());
				System.arraycopy(int4, 0, data, 48, 4);
				int4 = intToBytes_LE(heating.getPump1_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 52, 4);
				int4 = intToBytes_LE(heating.getPump1_2().getTargetValue());
				System.arraycopy(int4, 0, data, 56, 4);
				
				// pump4_2
				int4 = intToBytes_LE(heating.getPump4_2().getCondition2());
				System.arraycopy(int4, 0, data, 60, 4);
				int4 = intToBytes_LE(heating.getPump4_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 64, 4);
				int4 = intToBytes_LE(heating.getPump4_2().getTargetValue());
				System.arraycopy(int4, 0, data, 68, 4);
				
				// pump2_2
				int4 = intToBytes_LE(heating.getPump2_2().getCondition2());
				System.arraycopy(int4, 0, data, 72, 4);
				int4 = intToBytes_LE(heating.getPump2_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 76, 4);
				int4 = intToBytes_LE(heating.getPump2_2().getTargetValue());
				System.arraycopy(int4, 0, data, 80, 4);
				
				// pump3_2
				int4 = intToBytes_LE(heating.getPump3_2().getCondition2());
				System.arraycopy(int4, 0, data, 84, 4);
				int4 = intToBytes_LE(heating.getPump3_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 88, 4);
				int4 = intToBytes_LE(heating.getPump3_2().getTargetValue());
				System.arraycopy(int4, 0, data, 92, 4);
				
				// pcs
				int4 = intToBytes_LE(heating.getPcs().getCondition2());
				System.arraycopy(int4, 0, data, 96, 4);
				int4 = intToBytes_LE(heating.getPcs().getSpeedValue());
				System.arraycopy(int4, 0, data, 100, 4);
				int4 = intToBytes_LE(heating.getPcs().getTargetValue());
				System.arraycopy(int4, 0, data, 104, 4);
				
				break;
			case PacketDef.OPERATION :
			case PacketDef.OPERATION_MOD :
				OperationParameters operaion = (OperationParameters) obj;
				
				// pump1_1
				int4 = intToBytes_LE(operaion.getPump1_1().getCondition1());
				System.arraycopy(int4, 0, data, 0, 4);
				int4 = intToBytes_LE(operaion.getPump1_1().getCondition3());
				System.arraycopy(int4, 0, data, 4, 4);
				int4 = intToBytes_LE(operaion.getPump1_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 8, 4);
				int4 = intToBytes_LE(operaion.getPump1_1().getTargetValue());
				System.arraycopy(int4, 0, data, 12, 4);
				
				// pump1_2
				int4 = intToBytes_LE(operaion.getPump1_2().getCondition1());
				System.arraycopy(int4, 0, data, 16, 4);
				int4 = intToBytes_LE(operaion.getPump1_2().getCondition3());
				System.arraycopy(int4, 0, data, 20, 4);
				int4 = intToBytes_LE(operaion.getPump1_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 24, 4);
				int4 = intToBytes_LE(operaion.getPump1_2().getTargetValue());
				System.arraycopy(int4, 0, data, 28, 4);
				
				// pump1_3
				int4 = intToBytes_LE(operaion.getPump1_3().getCondition1());
				System.arraycopy(int4, 0, data, 32, 4);
				int4 = intToBytes_LE(operaion.getPump1_3().getCondition3());
				System.arraycopy(int4, 0, data, 36, 4);
				int4 = intToBytes_LE(operaion.getPump1_3().getSpeedValue());
				System.arraycopy(int4, 0, data, 40, 4);
				int4 = intToBytes_LE(operaion.getPump1_3().getTargetValue());
				System.arraycopy(int4, 0, data, 44, 4);
				
				// pump1_4
				int4 = intToBytes_LE(operaion.getPump1_4().getCondition1());
				System.arraycopy(int4, 0, data, 48, 4);
				int4 = intToBytes_LE(operaion.getPump1_4().getCondition3());
				System.arraycopy(int4, 0, data, 52, 4);
				int4 = intToBytes_LE(operaion.getPump1_4().getSpeedValue());
				System.arraycopy(int4, 0, data, 56, 4);
				int4 = intToBytes_LE(operaion.getPump1_4().getTargetValue());
				System.arraycopy(int4, 0, data, 60, 4);
				
				// pump1_5
				int4 = intToBytes_LE(operaion.getPump1_5().getCondition1());
				System.arraycopy(int4, 0, data, 64, 4);
				int4 = intToBytes_LE(operaion.getPump1_5().getCondition3());
				System.arraycopy(int4, 0, data, 68, 4);
				int4 = intToBytes_LE(operaion.getPump1_5().getSpeedValue());
				System.arraycopy(int4, 0, data, 72, 4);
				int4 = intToBytes_LE(operaion.getPump1_5().getTargetValue());
				System.arraycopy(int4, 0, data, 76, 4);
				
				// pump1_6
				int4 = intToBytes_LE(operaion.getPump1_6().getCondition1());
				System.arraycopy(int4, 0, data, 80, 4);
				int4 = intToBytes_LE(operaion.getPump1_6().getCondition3());
				System.arraycopy(int4, 0, data, 84, 4);
				int4 = intToBytes_LE(operaion.getPump1_6().getSpeedValue());
				System.arraycopy(int4, 0, data, 88, 4);
				int4 = intToBytes_LE(operaion.getPump1_6().getTargetValue());
				System.arraycopy(int4, 0, data, 92, 4);
				
				// pump1_7
				int4 = intToBytes_LE(operaion.getPump1_7().getCondition1());
				System.arraycopy(int4, 0, data, 96, 4);
				int4 = intToBytes_LE(operaion.getPump1_7().getCondition3());
				System.arraycopy(int4, 0, data, 100, 4);
				int4 = intToBytes_LE(operaion.getPump1_7().getSpeedValue());
				System.arraycopy(int4, 0, data, 104, 4);
				int4 = intToBytes_LE(operaion.getPump1_7().getTargetValue());
				System.arraycopy(int4, 0, data, 108, 4);
				
				// pump1_8
				int4 = intToBytes_LE(operaion.getPump1_8().getCondition1());
				System.arraycopy(int4, 0, data, 112, 4);
				int4 = intToBytes_LE(operaion.getPump1_8().getCondition3());
				System.arraycopy(int4, 0, data, 116, 4);
				int4 = intToBytes_LE(operaion.getPump1_8().getSpeedValue());
				System.arraycopy(int4, 0, data, 120, 4);
				int4 = intToBytes_LE(operaion.getPump1_8().getTargetValue());
				System.arraycopy(int4, 0, data, 124, 4);
				
				// pump1_9
				int4 = intToBytes_LE(operaion.getPump1_9().getCondition1());
				System.arraycopy(int4, 0, data, 128, 4);
				int4 = intToBytes_LE(operaion.getPump1_9().getCondition3());
				System.arraycopy(int4, 0, data, 132, 4);
				int4 = intToBytes_LE(operaion.getPump1_9().getSpeedValue());
				System.arraycopy(int4, 0, data, 136, 4);
				int4 = intToBytes_LE(operaion.getPump1_9().getTargetValue());
				System.arraycopy(int4, 0, data, 140, 4);
				
				// pump1_10
				int4 = intToBytes_LE(operaion.getPump1_10().getCondition1());
				System.arraycopy(int4, 0, data, 144, 4);
				int4 = intToBytes_LE(operaion.getPump1_10().getCondition3());
				System.arraycopy(int4, 0, data, 148, 4);
				int4 = intToBytes_LE(operaion.getPump1_10().getSpeedValue());
				System.arraycopy(int4, 0, data, 152, 4);
				int4 = intToBytes_LE(operaion.getPump1_10().getTargetValue());
				System.arraycopy(int4, 0, data, 156, 4);
				
				// pump1_11
				int4 = intToBytes_LE(operaion.getPump1_11().getCondition1());
				System.arraycopy(int4, 0, data, 160, 4);
				int4 = intToBytes_LE(operaion.getPump1_11().getCondition3());
				System.arraycopy(int4, 0, data, 164, 4);
				int4 = intToBytes_LE(operaion.getPump1_11().getSpeedValue());
				System.arraycopy(int4, 0, data, 168, 4);
				int4 = intToBytes_LE(operaion.getPump1_11().getTargetValue());
				System.arraycopy(int4, 0, data, 172, 4);
				
				// pump1_12
				int4 = intToBytes_LE(operaion.getPump1_12().getCondition1());
				System.arraycopy(int4, 0, data, 176, 4);
				int4 = intToBytes_LE(operaion.getPump1_12().getCondition3());
				System.arraycopy(int4, 0, data, 180, 4);
				int4 = intToBytes_LE(operaion.getPump1_12().getSpeedValue());
				System.arraycopy(int4, 0, data, 184, 4);
				int4 = intToBytes_LE(operaion.getPump1_12().getTargetValue());
				System.arraycopy(int4, 0, data, 188, 4);
				
				// pump1_13
				int4 = intToBytes_LE(operaion.getPump1_13().getCondition1());
				System.arraycopy(int4, 0, data, 192, 4);
				int4 = intToBytes_LE(operaion.getPump1_13().getCondition3());
				System.arraycopy(int4, 0, data, 196, 4);
				int4 = intToBytes_LE(operaion.getPump1_13().getSpeedValue());
				System.arraycopy(int4, 0, data, 200, 4);
				int4 = intToBytes_LE(operaion.getPump1_13().getTargetValue());
				System.arraycopy(int4, 0, data, 204, 4);
				
				// pump3_1
				int4 = intToBytes_LE(operaion.getPump3_1().getCondition1());
				System.arraycopy(int4, 0, data, 208, 4);
				int4 = intToBytes_LE(operaion.getPump3_1().getCondition3());
				System.arraycopy(int4, 0, data, 212, 4);
				int4 = intToBytes_LE(operaion.getPump3_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 216, 4);
				int4 = intToBytes_LE(operaion.getPump3_1().getTargetValue());
				System.arraycopy(int4, 0, data, 220, 4);
				
				// pump3_2
				int4 = intToBytes_LE(operaion.getPump3_2().getCondition1());
				System.arraycopy(int4, 0, data, 224, 4);
				int4 = intToBytes_LE(operaion.getPump3_2().getCondition3());
				System.arraycopy(int4, 0, data, 228, 4);
				int4 = intToBytes_LE(operaion.getPump3_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 232, 4);
				int4 = intToBytes_LE(operaion.getPump3_2().getTargetValue());
				System.arraycopy(int4, 0, data, 236, 4);
				
				// pump3_3
				int4 = intToBytes_LE(operaion.getPump3_3().getCondition1());
				System.arraycopy(int4, 0, data, 240, 4);
				int4 = intToBytes_LE(operaion.getPump3_3().getCondition3());
				System.arraycopy(int4, 0, data, 244, 4);
				int4 = intToBytes_LE(operaion.getPump3_3().getSpeedValue());
				System.arraycopy(int4, 0, data, 248, 4);
				int4 = intToBytes_LE(operaion.getPump3_3().getTargetValue());
				System.arraycopy(int4, 0, data, 252, 4);
				
				// pump3_4
				int4 = intToBytes_LE(operaion.getPump3_4().getCondition1());
				System.arraycopy(int4, 0, data, 256, 4);
				int4 = intToBytes_LE(operaion.getPump3_4().getCondition3());
				System.arraycopy(int4, 0, data, 260, 4);
				int4 = intToBytes_LE(operaion.getPump3_4().getSpeedValue());
				System.arraycopy(int4, 0, data, 264, 4);
				int4 = intToBytes_LE(operaion.getPump3_4().getTargetValue());
				System.arraycopy(int4, 0, data, 268, 4);
				
				// pump3_5
				int4 = intToBytes_LE(operaion.getPump3_5().getCondition1());
				System.arraycopy(int4, 0, data, 272, 4);
				int4 = intToBytes_LE(operaion.getPump3_5().getCondition3());
				System.arraycopy(int4, 0, data, 276, 4);
				int4 = intToBytes_LE(operaion.getPump3_5().getSpeedValue());
				System.arraycopy(int4, 0, data, 280, 4);
				int4 = intToBytes_LE(operaion.getPump3_5().getTargetValue());
				System.arraycopy(int4, 0, data, 284, 4);
				
				// pump3_6
				int4 = intToBytes_LE(operaion.getPump3_6().getCondition1());
				System.arraycopy(int4, 0, data, 288, 4);
				int4 = intToBytes_LE(operaion.getPump3_6().getCondition3());
				System.arraycopy(int4, 0, data, 292, 4);
				int4 = intToBytes_LE(operaion.getPump3_6().getSpeedValue());
				System.arraycopy(int4, 0, data, 296, 4);
				int4 = intToBytes_LE(operaion.getPump3_6().getTargetValue());
				System.arraycopy(int4, 0, data, 300, 4);
				
				// pump4_1
				int4 = intToBytes_LE(operaion.getPump4_1().getCondition1());
				System.arraycopy(int4, 0, data, 304, 4);
				int4 = intToBytes_LE(operaion.getPump4_1().getCondition3());
				System.arraycopy(int4, 0, data, 308, 4);
				int4 = intToBytes_LE(operaion.getPump4_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 312, 4);
				int4 = intToBytes_LE(operaion.getPump4_1().getTargetValue());
				System.arraycopy(int4, 0, data, 316, 4);
				
				// pump4_2
				int4 = intToBytes_LE(operaion.getPump4_2().getCondition1());
				System.arraycopy(int4, 0, data, 320, 4);
				int4 = intToBytes_LE(operaion.getPump4_2().getCondition3());
				System.arraycopy(int4, 0, data, 324, 4);
				int4 = intToBytes_LE(operaion.getPump4_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 328, 4);
				int4 = intToBytes_LE(operaion.getPump4_2().getTargetValue());
				System.arraycopy(int4, 0, data, 332, 4);
				
				// pump4_3
				int4 = intToBytes_LE(operaion.getPump4_3().getCondition1());
				System.arraycopy(int4, 0, data, 336, 4);
				int4 = intToBytes_LE(operaion.getPump4_3().getCondition3());
				System.arraycopy(int4, 0, data, 340, 4);
				int4 = intToBytes_LE(operaion.getPump4_3().getSpeedValue());
				System.arraycopy(int4, 0, data, 344, 4);
				int4 = intToBytes_LE(operaion.getPump4_3().getTargetValue());
				System.arraycopy(int4, 0, data, 348, 4);
				
				// pcs1_1
				int4 = intToBytes_LE(operaion.getPcs1_1().getCondition1());
				System.arraycopy(int4, 0, data, 352, 4);
				int4 = intToBytes_LE(operaion.getPcs1_1().getCondition3());
				System.arraycopy(int4, 0, data, 356, 4);
				int4 = intToBytes_LE(operaion.getPcs1_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 360, 4);
				int4 = intToBytes_LE(operaion.getPcs1_1().getTargetValue());
				System.arraycopy(int4, 0, data, 364, 4);
				
				// pcs1_2
				int4 = intToBytes_LE(operaion.getPcs1_2().getCondition1());
				System.arraycopy(int4, 0, data, 368, 4);
				int4 = intToBytes_LE(operaion.getPcs1_2().getCondition3());
				System.arraycopy(int4, 0, data, 372, 4);
				int4 = intToBytes_LE(operaion.getPcs1_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 376, 4);
				int4 = intToBytes_LE(operaion.getPcs1_2().getTargetValue());
				System.arraycopy(int4, 0, data, 380, 4);
				
				// pcs1_3
				int4 = intToBytes_LE(operaion.getPcs1_3().getCondition1());
				System.arraycopy(int4, 0, data, 384, 4);
				int4 = intToBytes_LE(operaion.getPcs1_3().getCondition3());
				System.arraycopy(int4, 0, data, 388, 4);
				int4 = intToBytes_LE(operaion.getPcs1_3().getSpeedValue());
				System.arraycopy(int4, 0, data, 392, 4);
				int4 = intToBytes_LE(operaion.getPcs1_3().getTargetValue());
				System.arraycopy(int4, 0, data, 396, 4);
				
				// pump2_1
				int4 = intToBytes_LE(operaion.getPump2_1().getCondition1());
				System.arraycopy(int4, 0, data, 400, 4);
				int4 = intToBytes_LE(operaion.getPump2_1().getCondition3());
				System.arraycopy(int4, 0, data, 404, 4);
				int4 = intToBytes_LE(operaion.getPump2_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 408, 4);
				int4 = intToBytes_LE(operaion.getPump2_1().getTargetValue());
				System.arraycopy(int4, 0, data, 412, 4);
				
				// pump2_2
				int4 = intToBytes_LE(operaion.getPump2_2().getCondition1());
				System.arraycopy(int4, 0, data, 416, 4);
				int4 = intToBytes_LE(operaion.getPump2_2().getCondition3());
				System.arraycopy(int4, 0, data, 420, 4);
				int4 = intToBytes_LE(operaion.getPump2_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 424, 4);
				int4 = intToBytes_LE(operaion.getPump2_2().getTargetValue());
				System.arraycopy(int4, 0, data, 428, 4);
				
				// pump2_3
				int4 = intToBytes_LE(operaion.getPump2_3().getCondition1());
				System.arraycopy(int4, 0, data, 432, 4);
				int4 = intToBytes_LE(operaion.getPump2_3().getCondition3());
				System.arraycopy(int4, 0, data, 436, 4);
				int4 = intToBytes_LE(operaion.getPump2_3().getSpeedValue());
				System.arraycopy(int4, 0, data, 440, 4);
				int4 = intToBytes_LE(operaion.getPump2_3().getTargetValue());
				System.arraycopy(int4, 0, data, 444, 4);
				
				// pump2_4
				int4 = intToBytes_LE(operaion.getPump2_4().getCondition1());
				System.arraycopy(int4, 0, data, 448, 4);
				int4 = intToBytes_LE(operaion.getPump2_4().getCondition3());
				System.arraycopy(int4, 0, data, 452, 4);
				int4 = intToBytes_LE(operaion.getPump2_4().getSpeedValue());
				System.arraycopy(int4, 0, data, 456, 4);
				int4 = intToBytes_LE(operaion.getPump2_4().getTargetValue());
				System.arraycopy(int4, 0, data, 460, 4);
				
				break;
			case PacketDef.ENDWATER :
			case PacketDef.ENDWATER_MOD :
				EndWaterParameters endWater = (EndWaterParameters) obj;
				
				// pcs
				int4 = intToBytes_LE(endWater.getPcs().getCondition2());
				System.arraycopy(int4, 0, data, 0, 4);
				int4 = intToBytes_LE(endWater.getPcs().getSpeedValue());
				System.arraycopy(int4, 0, data, 4, 4);
				int4 = intToBytes_LE(endWater.getPcs().getTargetValue());
				System.arraycopy(int4, 0, data, 8, 4);
				
				// pump1_1
				int4 = intToBytes_LE(endWater.getPump1_1().getCondition2());
				System.arraycopy(int4, 0, data, 12, 4);
				int4 = intToBytes_LE(endWater.getPump1_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 16, 4);
				int4 = intToBytes_LE(endWater.getPump1_1().getTargetValue());
				System.arraycopy(int4, 0, data, 20, 4);
				
				// pump4_1
				int4 = intToBytes_LE(endWater.getPump4_1().getCondition2());
				System.arraycopy(int4, 0, data, 24, 4);
				int4 = intToBytes_LE(endWater.getPump4_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 28, 4);
				int4 = intToBytes_LE(endWater.getPump4_1().getTargetValue());
				System.arraycopy(int4, 0, data, 32, 4);
				
				// pump3_1
				int4 = intToBytes_LE(endWater.getPump3_1().getCondition2());
				System.arraycopy(int4, 0, data, 36, 4);
				int4 = intToBytes_LE(endWater.getPump3_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 40, 4);
				int4 = intToBytes_LE(endWater.getPump3_1().getTargetValue());
				System.arraycopy(int4, 0, data, 44, 4);
				
				// pump4_2
				int4 = intToBytes_LE(endWater.getPump4_2().getCondition2());
				System.arraycopy(int4, 0, data, 48, 4);
				int4 = intToBytes_LE(endWater.getPump4_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 52, 4);
				int4 = intToBytes_LE(endWater.getPump4_2().getTargetValue());
				System.arraycopy(int4, 0, data, 56, 4);
				
				// pump4_3
				int4 = intToBytes_LE(endWater.getPump4_3().getCondition2());
				System.arraycopy(int4, 0, data, 60, 4);
				int4 = intToBytes_LE(endWater.getPump4_3().getSpeedValue());
				System.arraycopy(int4, 0, data, 64, 4);
				int4 = intToBytes_LE(endWater.getPump4_3().getTargetValue());
				System.arraycopy(int4, 0, data, 68, 4);
				
				// pump1_2
				int4 = intToBytes_LE(endWater.getPump1_2().getCondition2());
				System.arraycopy(int4, 0, data, 72, 4);
				int4 = intToBytes_LE(endWater.getPump1_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 76, 4);
				int4 = intToBytes_LE(endWater.getPump1_2().getTargetValue());
				System.arraycopy(int4, 0, data, 80, 4);
				
				// sol1
				int4 = intToBytes_LE(endWater.getSol1().getCondition2());
				System.arraycopy(int4, 0, data, 84, 4);
				int4 = intToBytes_LE(endWater.getSol1().getSpeedValue());
				System.arraycopy(int4, 0, data, 88, 4);
				int4 = intToBytes_LE(endWater.getSol1().getTargetValue());
				System.arraycopy(int4, 0, data, 92, 4);
				
				// pump3_2
				int4 = intToBytes_LE(endWater.getPump3_2().getCondition2());
				System.arraycopy(int4, 0, data, 96, 4);
				int4 = intToBytes_LE(endWater.getPump3_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 100, 4);
				int4 = intToBytes_LE(endWater.getPump3_2().getTargetValue());
				System.arraycopy(int4, 0, data, 104, 4);
				
				// start
				int4 = intToBytes_LE(endWater.getStart().getCondition2());
				System.arraycopy(int4, 0, data, 108, 4);
				int4 = intToBytes_LE(endWater.getStart().getSpeedValue());
				System.arraycopy(int4, 0, data, 112, 4);
				int4 = intToBytes_LE(endWater.getStart().getTargetValue());
				System.arraycopy(int4, 0, data, 116, 4);
				
				// sol6_1
				int4 = intToBytes_LE(endWater.getSol6_1().getCondition2());
				System.arraycopy(int4, 0, data, 120, 4);
				int4 = intToBytes_LE(endWater.getSol6_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 124, 4);
				int4 = intToBytes_LE(endWater.getSol6_1().getTargetValue());
				System.arraycopy(int4, 0, data, 128, 4);
				
				// sol6_2
				int4 = intToBytes_LE(endWater.getSol6_2().getCondition2());
				System.arraycopy(int4, 0, data, 132, 4);
				int4 = intToBytes_LE(endWater.getSol6_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 136, 4);
				int4 = intToBytes_LE(endWater.getSol6_2().getTargetValue());
				System.arraycopy(int4, 0, data, 140, 4);
				
				// threeway1_1
				int4 = intToBytes_LE(endWater.getThreeway1_1().getCondition2());
				System.arraycopy(int4, 0, data, 144, 4);
				int4 = intToBytes_LE(endWater.getThreeway1_1().getSpeedValue());
				System.arraycopy(int4, 0, data, 148, 4);
				int4 = intToBytes_LE(endWater.getThreeway1_1().getTargetValue());
				System.arraycopy(int4, 0, data, 152, 4);
				
				// threeway1_2
				int4 = intToBytes_LE(endWater.getThreeway1_2().getCondition2());
				System.arraycopy(int4, 0, data, 156, 4);
				int4 = intToBytes_LE(endWater.getThreeway1_2().getSpeedValue());
				System.arraycopy(int4, 0, data, 160, 4);
				int4 = intToBytes_LE(endWater.getThreeway1_2().getTargetValue());
				System.arraycopy(int4, 0, data, 164, 4);
				
				break;
			case PacketDef.PARAMETER :
			case PacketDef.PARAMETER_MOD :
				ParameterParameters param = (ParameterParameters) obj;
				
				// pump1
				data[0] = param.getPump1().getSelect();
				int4 = intToBytes_LE(param.getPump1().getValue());
				System.arraycopy(int4, 0, data, 1, 4);
				
				// pump2
				data[5] = param.getPump2().getSelect();
				int4 = intToBytes_LE(param.getPump2().getValue());
				System.arraycopy(int4, 0, data, 6, 4);
				
				// pump3
				data[10] = param.getPump3().getSelect();
				int4 = intToBytes_LE(param.getPump3().getValue());
				System.arraycopy(int4, 0, data, 11, 4);
				
				// pump4
				data[15] = param.getPump4().getSelect();
				int4 = intToBytes_LE(param.getPump4().getValue());
				System.arraycopy(int4, 0, data, 16, 4);
				
				// pcs
				data[20] = param.getPcs().getSelect();
				int4 = intToBytes_LE(param.getPcs().getValue());
				System.arraycopy(int4, 0, data, 21, 4);
				
				// snh
				data[25] = param.getSnh().getSelect();
				data[26] = param.getSnh().getValue();
				
				// sol1
				data[27] = param.getSol1().getSelect();
				data[28] = param.getSol1().getValue();
				
				// sol2
				data[29] = param.getSol2().getSelect();
				data[30] = param.getSol2().getValue();
				
				// sol4
				data[31] = param.getSol4().getSelect();
				data[32] = param.getSol4().getValue();
				
				// sol5
				data[33] = param.getSol5().getSelect();
				data[34] = param.getSol5().getValue();
				
				// sol6
				data[35] = param.getSol6().getSelect();
				data[36] = param.getSol6().getValue();
				
				// threeway
				data[37] = param.getThreeway1().getSelect();
				data[38] = param.getThreeway1().getValue();
				
				// u8
				data[39] = param.getU8();
				
				break;
			case PacketDef.ERROR :
			case PacketDef.ERROR_MOD :
				ErrorParameters error = (ErrorParameters) obj;
				
				// error02_1
				int4 = intToBytes_LE(error.getError02_1().getCondition1());
				System.arraycopy(int4, 0, data, 0, 4);
				int4 = intToBytes_LE(error.getError02_1().getCondition3());
				System.arraycopy(int4, 0, data, 4, 4);
				int4 = intToBytes_LE(error.getError02_1().getSec());
				System.arraycopy(int4, 0, data, 8, 4);
				int4 = intToBytes_LE(error.getError02_1().getTc3());
				System.arraycopy(int4, 0, data, 12, 4);
				
				// error02_2
				int4 = intToBytes_LE(error.getError02_2().getCondition1());
				System.arraycopy(int4, 0, data, 16, 4);
				int4 = intToBytes_LE(error.getError02_2().getCondition3());
				System.arraycopy(int4, 0, data, 20, 4);
				int4 = intToBytes_LE(error.getError02_2().getSec());
				System.arraycopy(int4, 0, data, 24, 4);
				int4 = intToBytes_LE(error.getError02_2().getTc3());
				System.arraycopy(int4, 0, data, 28, 4);
				
				// error02_3
				int4 = intToBytes_LE(error.getError02_3().getCondition1());
				System.arraycopy(int4, 0, data, 32, 4);
				int4 = intToBytes_LE(error.getError02_3().getCondition3());
				System.arraycopy(int4, 0, data, 36, 4);
				int4 = intToBytes_LE(error.getError02_3().getSec());
				System.arraycopy(int4, 0, data, 40, 4);
				int4 = intToBytes_LE(error.getError02_3().getTc3());
				System.arraycopy(int4, 0, data, 44, 4);
				
				// error15_1
				int4 = intToBytes_LE(error.getError15_1().getCondition1());
				System.arraycopy(int4, 0, data, 48, 4);
				int4 = intToBytes_LE(error.getError15_1().getCondition3());
				System.arraycopy(int4, 0, data, 52, 4);
				int4 = intToBytes_LE(error.getError15_1().getSec());
				System.arraycopy(int4, 0, data, 56, 4);
				int4 = intToBytes_LE(error.getError15_1().getTc3());
				System.arraycopy(int4, 0, data, 60, 4);
				
				break;
			case PacketDef.GAIN :
			case PacketDef.GAIN_MOD :
				GainParameters gain = (GainParameters) obj;
				
				// pump1
				data[0] = gain.getPump1().getSelect();
				int4 = intToBytes_LE(gain.getPump1().getValue());
				System.arraycopy(int4, 0, data, 1, 4);
				
				// pump2
				data[5] = gain.getPump2().getSelect();
				int4 = intToBytes_LE(gain.getPump2().getValue());
				System.arraycopy(int4, 0, data, 6, 4);
				
				// pump3
				data[10] = gain.getPump3().getSelect();
				int4 = intToBytes_LE(gain.getPump3().getValue());
				System.arraycopy(int4, 0, data, 11, 4);
				
				// pump4
				data[15] = gain.getPump4().getSelect();
				int4 = intToBytes_LE(gain.getPump4().getValue());
				System.arraycopy(int4, 0, data, 16, 4);
				
				// fl1
				data[20] = gain.getFl1().getSelect();
				int4 = intToBytes_LE(gain.getFl1().getValue());
				System.arraycopy(int4, 0, data, 21, 4);
				
				// fl2
				data[25] = gain.getFl2().getSelect();
				int4 = intToBytes_LE(gain.getFl2().getValue());
				System.arraycopy(int4, 0, data, 26, 4);
				
				// fl3
				data[30] = gain.getFl3().getSelect();
				int4 = intToBytes_LE(gain.getFl3().getValue());
				System.arraycopy(int4, 0, data, 31, 4);

				break;
			case PacketDef.OFFSET :
			case PacketDef.OFFSET_MOD :
				OffsetParameters offset = (OffsetParameters) obj;
				
				// pump1
				data[0] = offset.getPump1().getSelect();
				data[1] = offset.getPump1().getSign();
				int4 = intToBytes_LE(offset.getPump1().getValue());
				System.arraycopy(int4, 0, data, 2, 4);
				
				// pump2
				data[6] = offset.getPump2().getSelect();
				data[7] = offset.getPump2().getSign();
				int4 = intToBytes_LE(offset.getPump2().getValue());
				System.arraycopy(int4, 0, data, 8, 4);
				
				// pump3
				data[12] = offset.getPump3().getSelect();
				data[13] = offset.getPump3().getSign();
				int4 = intToBytes_LE(offset.getPump3().getValue());
				System.arraycopy(int4, 0, data, 14, 4);
				
				// pump4
				data[18] = offset.getPump4().getSelect();
				data[19] = offset.getPump4().getSign();
				int4 = intToBytes_LE(offset.getPump4().getValue());
				System.arraycopy(int4, 0, data, 20, 4);
				
				// fl1
				data[24] = offset.getFl1().getSelect();
				data[25] = offset.getFl1().getSign();
				int4 = intToBytes_LE(offset.getFl1().getValue());
				System.arraycopy(int4, 0, data, 26, 4);
				
				// fl2
				data[30] = offset.getFl2().getSelect();
				data[31] = offset.getFl2().getSign();
				int4 = intToBytes_LE(offset.getFl2().getValue());
				System.arraycopy(int4, 0, data, 32, 4);
				
				// fl3
				data[36] = offset.getFl3().getSelect();
				data[37] = offset.getFl3().getSign();
				int4 = intToBytes_LE(offset.getFl3().getValue());
				System.arraycopy(int4, 0, data, 38, 4);
				
				// tc1
				data[42] = offset.getTc1().getSelect();
				data[43] = offset.getTc1().getSign();
				int4 = intToBytes_LE(offset.getTc1().getValue());
				System.arraycopy(int4, 0, data, 44, 4);
				
				// tc2
				data[48] = offset.getTc2().getSelect();
				data[49] = offset.getTc2().getSign();
				int4 = intToBytes_LE(offset.getTc2().getValue());
				System.arraycopy(int4, 0, data, 50, 4);
				
				// tc3
				data[54] = offset.getTc3().getSelect();
				data[55] = offset.getTc3().getSign();
				int4 = intToBytes_LE(offset.getTc3().getValue());
				System.arraycopy(int4, 0, data, 56, 4);
				
				// tc4
				data[60] = offset.getTc4().getSelect();
				data[61] = offset.getTc4().getSign();
				int4 = intToBytes_LE(offset.getTc4().getValue());
				System.arraycopy(int4, 0, data, 62, 4);
				
				// tc5
				data[66] = offset.getTc5().getSelect();
				data[67] = offset.getTc5().getSign();
				int4 = intToBytes_LE(offset.getTc5().getValue());
				System.arraycopy(int4, 0, data, 68, 4);
				
				// tc6
				data[72] = offset.getTc6().getSelect();
				data[73] = offset.getTc6().getSign();
				int4 = intToBytes_LE(offset.getTc6().getValue());
				System.arraycopy(int4, 0, data, 74, 4);
				
				// tc7
				data[78] = offset.getTc7().getSelect();
				data[79] = offset.getTc7().getSign();
				int4 = intToBytes_LE(offset.getTc7().getValue());
				System.arraycopy(int4, 0, data, 80, 4);
				
				// tc8
				data[84] = offset.getTc8().getSelect();
				data[85] = offset.getTc8().getSign();
				int4 = intToBytes_LE(offset.getTc8().getValue());
				System.arraycopy(int4, 0, data, 86, 4);
				
				// tc9
				data[90] = offset.getTc9().getSelect();
				data[91] = offset.getTc9().getSign();
				int4 = intToBytes_LE(offset.getTc9().getValue());
				System.arraycopy(int4, 0, data, 92, 4);
				
				// tc10
				data[96] = offset.getTc10().getSelect();
				data[97] = offset.getTc10().getSign();
				int4 = intToBytes_LE(offset.getTc10().getValue());
				System.arraycopy(int4, 0, data, 98, 4);
				
				// tc11
				data[102] = offset.getTc11().getSelect();
				data[103] = offset.getTc11().getSign();
				int4 = intToBytes_LE(offset.getTc11().getValue());
				System.arraycopy(int4, 0, data, 104, 4);
				
				break;
			case PacketDef.KALMAN : 
			case PacketDef.KALMAN_MOD : 
				KalmanParameters kalman = (KalmanParameters) obj;
				
				data[0]  = (byte)(kalman.getTc1().getSelect() > 0 ? 0x01 : 0x00);
				data[1]  = (byte)(kalman.getTc1().getOnoff()  > 0 ? 0x01 : 0x00);
				data[2]  = (byte)(kalman.getTc2().getSelect() > 0 ? 0x01 : 0x00);
				data[3]  = (byte)(kalman.getTc2().getOnoff()  > 0 ? 0x01 : 0x00);
				data[4]  = (byte)(kalman.getTc3().getSelect() > 0 ? 0x01 : 0x00);
				data[5]  = (byte)(kalman.getTc3().getOnoff()  > 0 ? 0x01 : 0x00);
				data[6]  = (byte)(kalman.getTc4().getSelect() > 0 ? 0x01 : 0x00);
				data[7]  = (byte)(kalman.getTc4().getOnoff()  > 0 ? 0x01 : 0x00);
				data[8]  = (byte)(kalman.getTc5().getSelect() > 0 ? 0x01 : 0x00);
				data[9]  = (byte)(kalman.getTc5().getOnoff()  > 0 ? 0x01 : 0x00);
				data[10] = (byte)(kalman.getTc6().getSelect() > 0 ? 0x01 : 0x00);
				data[11] = (byte)(kalman.getTc6().getOnoff()  > 0 ? 0x01 : 0x00);
				data[12] = (byte)(kalman.getTc7().getSelect() > 0 ? 0x01 : 0x00);
				data[13] = (byte)(kalman.getTc7().getOnoff()  > 0 ? 0x01 : 0x00);
				data[14] = (byte)(kalman.getTc8().getSelect() > 0 ? 0x01 : 0x00);
				data[15] = (byte)(kalman.getTc8().getOnoff()  > 0 ? 0x01 : 0x00);
				data[16] = (byte)(kalman.getTc9().getSelect() > 0 ? 0x01 : 0x00);
				data[17] = (byte)(kalman.getTc9().getOnoff()  > 0 ? 0x01 : 0x00);
				data[18] = (byte)(kalman.getTc10().getSelect() > 0 ? 0x01 : 0x00);
				data[19] = (byte)(kalman.getTc10().getOnoff()  > 0 ? 0x01 : 0x00);
				data[20] = (byte)(kalman.getTc11().getSelect() > 0 ? 0x01 : 0x00);
				data[21] = (byte)(kalman.getTc11().getOnoff()  > 0 ? 0x01 : 0x00);
				break;
			case PacketDef.SIMULATION_ACTIVE :
				SimulationActiveParameters simMod = (SimulationActiveParameters) obj;
				
				// pump1
				data[0] = (byte)(simMod.getError_no() > 0 ? 0x01 : 0x00);
				short2 = intToBytes_LE(simMod.getPt1());
				System.arraycopy(short2, 0, data, 1, 2);

				short2 = intToBytes_LE(simMod.getTc02());
				System.arraycopy(short2, 0, data, 3, 2);
				break;
			case PacketDef.SIMULATION_MODE :
				SimulationModeParameters simAct = (SimulationModeParameters) obj;
				data[0] = (byte)(simAct.getMode() > 0 ? 0x01 : 0x00);
				break;
			case PacketDef.GENERATOR_MOD :
				GeneratorParameters genAct = (GeneratorParameters) obj;
				data[0] = (byte)(genAct.getStart() > 0 ? 0x01 : 0x00);
				data[1] = (byte)(genAct.getStop() > 0 ? 0x01 : 0x00);
				break;	
				
			default :
				data = null;
				break;
		}
	}
	
	/*******************************************************
	 * Util Methods 
	 ******************************************************/
	// short to bytes
	public void shortToBytes_BE(short input, byte[] output) {
        output[0] = (byte) (input >> 8);
        output[1] = (byte) input;
    }
	
	public void shortToBytes_LE(short input, byte[] output) {
        output[0] = (byte) input;
        output[1] = (byte) (input >> 8);
    }
	
	// integer to bytes
	public void intToBytes_BE(int input, byte[] output) {
        for(int cnt = 0;  cnt < 4; cnt++){
            output[(3-cnt)] = (byte)(input % (0xff + 1));
            input = input >> 8;
        }
    }
	
	public void intToBytes_LE(int input, byte[] output) {
        for(int cnt = 0;  cnt < 4; cnt++){
            output[cnt] = (byte)(input % (0xff + 1));
            input = input >> 8;
        }
    }
	
	public byte[] intToBytes_LE(int input) {
		byte[] output = new byte[4];
        for(int cnt = 0;  cnt < 4; cnt++){
            output[cnt] = (byte)(input % (0xff + 1));
            input = input >> 8;
        }
        
        return output;
    }
	
	// checksum
	public byte getChecksum(byte[] data) {
		byte checksum = (byte)0x00;
		
		for(byte b : data) {
			checksum ^= b;
		}
		
		return checksum;
	}
}
