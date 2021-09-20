package com.stx.sofc.util.protocol;

/**
* <pre>
* 1. 패키지명 : com.stx.sofc.util.protocol
* 2. 타입명 : PacketDef.java
* 3. 작성일 : 2020. 4. 2. 오후 5:23:26
* 4. 작성자 : yousung
* 5. 설명 : 프로토콜 데이터(send)
* </pre>
*/
public class PacketDef {
	
	// Header size
	public static final int HEADER_SIZE   	= 	9;		// STX(1) + RTU_ID(2) + BD_ID(2) + CMD(1) + KIND(1) + LENGTH(2)
	
	// Tail size
	public static final int ETX_SIZE	  	= 	1;		// ETX(1)
	public static final int CHECKSUM_SIZE 	= 	1;		// Checksum(1)
	
	// Data size
	public static short DATA_IGNITE_SIZE   		 	=  36;		// 착화공정 (12 * 3)
	public static short DATA_HEATING_SIZE   		= 108;		// 승온공정 (12 * 9)
	public static short DATA_OPERATION_SIZE 		= 464;		// 운전공정 (16 * 29)
	public static short DATA_ENDWATER_SIZE  		= 168;		// 종료/저탕조 공정 (12(3*4) * 14)
	public static short DATA_PARAMETER_SIZE		 	=  40;		// 파라메터 변경 
	public static short DATA_ERROR_SIZE     		=  64;		// 에러공정 (16 * 4)
	public static short DATA_GAIN_SIZE      		=  35;		// 정확도 - Gain (5 * 7)
	public static short DATA_OFFSET_SIZE    		= 108;		// 정확도 - Offset (6 * 18)
	public static short DATA_KALMAN_SIZE    		=  22;		// 정확도 - 칼만필터 (2 * 11)
	public static short DATA_SIMULATION_MODE_SIZE	=  1;		// 시뮬레이션모드 진입/해제 
	public static short DATA_SIMULATION_ACTIVE_SIZE	=  5;		// 시뮬레이션모드 적용 
	public static short GENERATOR_MOD_SIZE			=  2;		// 발전 시작/종료()
	
	
	// Header Definition
	public static final byte STX = 0x53;
//	public static final byte KIND = 0x01;		// 요청 : 0x01, 응답 : 0x02, 데이터변경 : 0x03
	public static final byte KIND = 0x00;		// 프로그램에서 보드 혹은 IDC서버로 요청 혹은 응답할 경우 : 0x00, IDC 혹은 보드에서 프로그램쪽으로 요청 혹은 응답 : 0x01

	
	// Tail Definition
	public static final byte ETX = (byte)0x45;
	
	// Command
	public static final byte IGNITE    			= 0x21;			// 착화공정
	public static final byte IGNITE_MOD  		= 0x22;			// 착화공정 수정
	public static final byte HEATING   			= 0x23;			// 승온공정
	public static final byte HEATING_MOD		= 0x24;			// 승온공정 수정
	public static final byte OPERATION 			= 0x25;			// 운전공정
	public static final byte OPERATION_MOD		= 0x26;			// 운전공정 수정
	public static final byte ENDWATER  			= 0x27;			// 종료공정, 저탕조
	public static final byte ENDWATER_MOD		= 0x28;			// 종료공정, 저탕조 수정
	public static final byte PARAMETER 			= (byte)0x4D;	// 파라메터 변경
	public static final byte PARAMETER_MOD 		= (byte)0x4E;	// 파라메터 변경
	public static final byte ERROR 	   			= 0x29;			// 에러조건
	public static final byte ERROR_MOD 	   		= 0x2A;			// 에러조건 수정
	public static final byte GAIN 	   			= 0x31;			// 정확도 - Gain
	public static final byte GAIN_MOD 	   		= 0x32;			// 정확도 - Gain 수정
	public static final byte OFFSET    			= 0x33;			// 정확도 - Offset
	public static final byte OFFSET_MOD			= 0x34;			// 정확도 - Offset 수정 
	public static final byte KALMAN   			= 0x51;			// 정확도 - 칼만필터
	public static final byte KALMAN_MOD 		= 0x52;			// 정확도 - 칼만필터 수정
	public static final byte SIMULATION_MODE	= (byte)0xA1;	// 시뮬레이션모드 진입/해제
	public static final byte SIMULATION_ACTIVE	= (byte)0xA2;	// 시뮬레이션모드 적용
	public static final byte GENERATOR_MOD		= 0x46;			// 발전 시작/종료

	public static final byte HEATING2   		= 0x48;			// 승온공정2
	public static final byte HEATING2_MOD		= 0x49;			// 승온공정2 수정
	
	
	
}
