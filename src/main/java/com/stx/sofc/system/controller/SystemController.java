package com.stx.sofc.system.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stx.sofc.util.protocol.Packet;
import com.stx.sofc.util.protocol.PacketDef;
import com.stx.sofc.util.protocol.stxControllPacket.EndWaterParameters;
import com.stx.sofc.util.protocol.stxControllPacket.EndWaterStruct;
import com.stx.sofc.util.protocol.stxControllPacket.ErrorParameters;
import com.stx.sofc.util.protocol.stxControllPacket.ErrorStruct;
import com.stx.sofc.util.protocol.stxControllPacket.GainParameters;
import com.stx.sofc.util.protocol.stxControllPacket.GainStruct;
import com.stx.sofc.util.protocol.stxControllPacket.GeneratorParameters;
import com.stx.sofc.util.protocol.stxControllPacket.HeatingParameters;
import com.stx.sofc.util.protocol.stxControllPacket.HeatingStruct;
import com.stx.sofc.util.protocol.stxControllPacket.IgniteParameters;
import com.stx.sofc.util.protocol.stxControllPacket.IgniteStruct;
import com.stx.sofc.util.protocol.stxControllPacket.KalmanParameters;
import com.stx.sofc.util.protocol.stxControllPacket.KalmanStruct;
import com.stx.sofc.util.protocol.stxControllPacket.OffsetParameters;
import com.stx.sofc.util.protocol.stxControllPacket.OffsetStruct;
import com.stx.sofc.util.protocol.stxControllPacket.OperationParameters;
import com.stx.sofc.util.protocol.stxControllPacket.OperationStruct;
import com.stx.sofc.util.protocol.stxControllPacket.Parameter32Struct;
import com.stx.sofc.util.protocol.stxControllPacket.Parameter8Struct;
import com.stx.sofc.util.protocol.stxControllPacket.ParameterParameters;
import com.stx.sofc.util.protocol.stxControllPacket.SimulationActiveParameters;
import com.stx.sofc.util.protocol.stxControllPacket.SimulationModeParameters;

@Controller
@RequestMapping("/systemCont/*")
public class SystemController {
	
//	private String socketIp = "58.72.255.154";
//	private String socketIp = "192.168.1.158";
	private String socketIp = "127.0.0.1";
	private int socketPort = 5201;

	private int SOCKET_TIME_OUT = 1000;
//	private int socketPort = 5555;
	
	/**
     * <pre>
     * 1. ???????????? : insertIgnite
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : kct
     * 4. ?????? : ???????????? modfiy
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param condition1
     * @param condition3
     * @param targetValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertIgnite", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertIgnite(Model model, String rtuId, String iBdNum, BigDecimal[] condition1, BigDecimal[] condition3, BigDecimal[] targetValue) throws Exception {
		
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_IGNITE_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.IGNITE_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.IGNITE_MOD);
    	    
    	    // ????????? ?????? 
    	    IgniteStruct struct = new IgniteStruct();
    	    
    	    BigDecimal multiply = new BigDecimal("100");
    	    
    	    IgniteParameters param = new IgniteParameters();
    	    
    	    int array = 0;
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump3(struct);
    	    
    	    array++;
    	    
    	    struct = new IgniteStruct();
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump2(struct);
    	    
    	    array++;
    	    
    	    struct = new IgniteStruct();
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setSnh(struct);
    	    
    	    System.out.println("Parameters : " + param.toString());
    	    
    	    packet.setData(PacketDef.IGNITE, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[9];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			if(j == 1) {
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				j = 0;
        			}
        			j++;	
        		}
        	}
        	
        	System.out.println();
        	
        	IgniteStruct struct1 = new IgniteStruct();
    	    IgniteParameters param1 = new IgniteParameters();
    	    
    	    struct1.setCondition1(iTempArray[0]);
    	    struct1.setCondition3(iTempArray[1]);
    	    struct1.setTargetValue(iTempArray[2]);
    	    param1.setPump3(struct1);
    	    
    	    struct1 = new IgniteStruct();
    	    
    	    struct1.setCondition1(iTempArray[3]);
    	    struct1.setCondition3(iTempArray[4]);
    	    struct1.setTargetValue(iTempArray[5]);
    	    param1.setPump2(struct1);
    	    
    	    struct1 = new IgniteStruct();
    	    
    	    struct1.setCondition1(iTempArray[6]);
    	    struct1.setCondition3(iTempArray[7]);
    	    struct1.setTargetValue(iTempArray[8]);
    	    param1.setSnh(struct1);
    	    
    	    System.out.println("Parameters : " + param1.toString());
        	
    	    hashmap.put("res", param1);
    	    
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }		
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    	
    }
    	
	
    /**
     * <pre>
     * 1. ???????????? : insertIgnite
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : kct
     * 4. ?????? : ?????????????????? ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/igniteInfo", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> igniteInfo(Model model, String rtuId, String iBdNum) throws Exception {
 
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_IGNITE_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.IGNITE);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.IGNITE);
    	    
    	    // ????????? ?????? 
    	    IgniteStruct struct = new IgniteStruct();
    	    IgniteParameters param = new IgniteParameters();
    	    param.setPump3(struct);
    	    
    	    struct = new IgniteStruct();
    	    param.setPump2(struct);
    	    
    	    struct = new IgniteStruct();
    	    param.setSnh(struct);
    	    
    	    packet.setData(PacketDef.IGNITE, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[9];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			if(j == 1) {
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				j = 0;
        			}
        			j++;	
        		}
        	}
        	
        	System.out.println();
        	
        	IgniteStruct struct1 = new IgniteStruct();
    	    IgniteParameters param1 = new IgniteParameters();
    	    
    	    struct1.setCondition1(iTempArray[0]);
    	    struct1.setCondition3(iTempArray[1]);
    	    struct1.setTargetValue(iTempArray[2]);
    	    param1.setPump3(struct1);
    	    
    	    struct1 = new IgniteStruct();
    	    
    	    struct1.setCondition1(iTempArray[3]);
    	    struct1.setCondition3(iTempArray[4]);
    	    struct1.setTargetValue(iTempArray[5]);
    	    param1.setPump2(struct1);
    	    
    	    struct1 = new IgniteStruct();
    	    
    	    struct1.setCondition1(iTempArray[6]);
    	    struct1.setCondition3(iTempArray[7]);
    	    struct1.setTargetValue(iTempArray[8]);
    	    param1.setSnh(struct1);
    	    
    	    System.out.println("Parameters : " + param1.toString());
        	
    	    hashmap.put("res", param1);
    	    
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }		
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

    /**
     * <pre>
     * 1. ???????????? : insertHeating
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : jsw
     * 4. ?????? : ???????????? modfiy
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param condition2
     * @param speedValue
     * @param targetValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertHeating", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertHeating(Model model, String rtuId, String iBdNum, BigDecimal[] condition2, BigDecimal[] speedValue, BigDecimal[] targetValue) throws Exception {
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_HEATING_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.HEATING_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.HEATING_MOD);
    	    
    	    // ????????? ?????? 
    	    HeatingParameters param = new HeatingParameters();
    	    
    	    BigDecimal multiply = new BigDecimal("100");
    	    
    	    int array = 0;
    	    HeatingStruct struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump3_1(struct);
    	    System.out.println("struct1 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump1_1(struct);
    	    System.out.println("struct2 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump2_1(struct);
    	    System.out.println("struct3 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump4_1(struct);
    	    System.out.println("struct4 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump1_2(struct);
    	    System.out.println("struct5 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump4_2(struct);
    	    System.out.println("struct6 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump2_2(struct);
    	    System.out.println("struct7 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump3_2(struct);
    	    System.out.println("struct8 : " + struct.toString());

    	    array++;
    	    struct = new HeatingStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    System.out.println("struct9 : " + struct.toString());
    	    param.setPcs(struct);
    	    
    	    packet.setData(PacketDef.HEATING_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[9*3];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}
        	
        	System.out.println();
        	
        	HeatingStruct struct1 = new HeatingStruct();
        	HeatingParameters param1 = new HeatingParameters();
    	    
    	    int i = 0;
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump2_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump2_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPcs(struct1);

        	hashmap.put("res", param1);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
	@RequestMapping(value = "/insertHeatingNew", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertHeatingNew(Model model, String rtuId, String iBdNum, BigDecimal[] condition2New, BigDecimal[] speedValueNew, BigDecimal[] targetValueNew) throws Exception {

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);

		socket.setSoTimeout(SOCKET_TIME_OUT);

		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// test
		BufferedInputStream bis = null;

		try {
			// ????????????2 ??????
			ByteBuffer sendByteBuffer = null;

			sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_HEATING_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
			sendByteBuffer.order(ByteOrder.BIG_ENDIAN);

			/* IGNITE test */
			// packet.java class test
			Packet packet = new Packet(PacketDef.HEATING2_MOD);
			packet.setRTU_ID(Short.parseShort(rtuId));
			packet.setBD_ID(Short.parseShort(iBdNum));
			packet.setHeader(PacketDef.HEATING2_MOD);

			// ????????? ??????
			HeatingParameters param = new HeatingParameters();

			BigDecimal multiply = new BigDecimal("100");

			int array = 0;
			HeatingStruct struct = new HeatingStruct();
			struct.setCondition2(condition2New[array].multiply(multiply).intValue());
			struct.setSpeedValue(speedValueNew[array].multiply(multiply).intValue());
			struct.setTargetValue(targetValueNew[array].multiply(multiply).intValue());
			System.out.println("struct10 : " + struct.toString());
			param.setPump4_3(struct);

			packet.setData(PacketDef.HEATING2_MOD, param);

			// ????????? ????????? ????????????...
			byte[] header = packet.getHeader();
			byte[] body = packet.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();


			// send
			sendByteBuffer.put(sendData);

			os.write(sendByteBuffer.array());
			os.flush();

			/* ?????? ??????... */
//			byte[] reciveData = null;
//			byte[] headerBuffer = new byte[231];
			//dis.read(headerBuffer);	// ??????


			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);
			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}
			System.out.println("?????? ??? >>>>>>>>>>>>");
			byte[] tempArray = new byte[4];
			int j = 0;
			int k = 0;
			int[] iTempArray = new int[1*3];

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));

				if(i >= header.length && i < header.length + body.length - 1 ) {

					if(j == 1) {

						System.arraycopy(buff, i - 1, tempArray, 0, 3);
						iTempArray[k] = getBigEndian(tempArray);

						k++;
					}else if(j == 4) {

						j = 0;

					}

					j++;

				}
			}

			System.out.println();

			HeatingStruct struct1 = new HeatingStruct();
			HeatingParameters param1 = new HeatingParameters();

			int i = 0;
			struct1.setCondition2(iTempArray[i++]);
			struct1.setSpeedValue(iTempArray[i++]);
			struct1.setTargetValue(iTempArray[i++]);
			param1.setPump4_3(struct1);

			// ?????? + ????????? ???????????? return
			hashmap.put("res2", param1);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;
	}
    

    /**
     * <pre>
     * 1. ???????????? : getHeating
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : jsw
     * 4. ?????? : ?????????????????? ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getHeating", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getHeating(Model model, String rtuId, String iBdNum) throws Exception {
		System.out.println("???????????? ??????");
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
		System.out.println("Socket open");
        socket.setSoTimeout(SOCKET_TIME_OUT);
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
    	try {
    		
            // test
            BufferedInputStream bis = null;
    		
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_HEATING_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.HEATING);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.HEATING);
    	    
    	    // ????????? ?????? 
    	    HeatingParameters param = new HeatingParameters();
    	    
    	    param.setPump3_1(new HeatingStruct());
    	    param.setPump1_1(new HeatingStruct());
    	    param.setPump2_1(new HeatingStruct());
    	    param.setPump4_1(new HeatingStruct());
    	    param.setPump1_2(new HeatingStruct());
    	    param.setPump4_2(new HeatingStruct());
    	    param.setPump2_2(new HeatingStruct());
    	    param.setPump3_2(new HeatingStruct());
    	    param.setPcs(new HeatingStruct());

			System.out.println("setData HEATING ??????");
    	    packet.setData(PacketDef.HEATING, param);
			System.out.println("setData HEATING ??????");

    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
			System.out.println("sendData put!");
    	    
            os.write(sendByteBuffer.array());
			System.out.println("os write!");
            os.flush();
			System.out.println("os flush!");
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[9*3];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}
        	
        	System.out.println();
        	
        	HeatingStruct struct1 = new HeatingStruct();
        	HeatingParameters param1 = new HeatingParameters();
    	    
    	    int i = 0;
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump2_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_1(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump2_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_2(struct1);
    		
    		struct1 = new HeatingStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPcs(struct1);

        	hashmap.put("res", param1);
        	
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

	@RequestMapping(value = "/getHeatingNew", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getHeatingNew(Model model, String rtuId, String iBdNum) throws Exception {
		System.out.println("???????????? ??????");
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
		System.out.println("Socket open");
		socket.setSoTimeout(SOCKET_TIME_OUT);
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		try {

			// ????????????2 ??????
			BufferedInputStream bis = null;

			ByteBuffer sendByteBuffer = null;

			System.out.println("????????????2 ??????!!!!!!!!!!!!");
			sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_HEATING_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
			sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
			System.out.println("sendByteBuffer ??????");

			/* IGNITE test */
			// packet.java class test
			Packet packet = new Packet(PacketDef.HEATING2);
			packet.setRTU_ID(Short.parseShort(rtuId));
			packet.setBD_ID(Short.parseShort(iBdNum));
			packet.setHeader(PacketDef.HEATING2);
			System.out.println("packet set ??????");

			// ????????? ??????
			HeatingParameters param = new HeatingParameters();

			param.setPump4_3(new HeatingStruct());

			System.out.println("setData HEATING2 ??????");
			packet.setData(PacketDef.HEATING2, param);
			System.out.println("setData HEATING2 ??????");

			// ????????? ????????? ????????????...
			byte[] header = packet.getHeader();
			byte[] body = packet.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();


			// send
			sendByteBuffer.put(sendData);

			os.write(sendByteBuffer.array());
			os.flush();

			/* ?????? ??????... */
//			byte[] reciveData = null;
//			byte[] headerBuffer = new byte[231];
			//dis.read(headerBuffer);	// ??????


			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);
			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}
			System.out.println("?????? ??? >>>>>>>>>>>>");
			byte[] tempArray = new byte[4];
			int j = 0;
			int k = 0;
			int[] iTempArray = new int[1*3];

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));
				if(i >= header.length && i < header.length + body.length - 1 ) {
					if(j == 1) {
						System.arraycopy(buff, i - 1, tempArray, 0, 3);
						iTempArray[k] = getBigEndian(tempArray);
						k++;
					}else if(j == 4) {
						j = 0;
					}
					j++;
				}
			}

			System.out.println();

			HeatingStruct struct1 = new HeatingStruct();
			HeatingParameters param1 = new HeatingParameters();

			int i = 0;
			struct1.setCondition2(iTempArray[i++]);
			struct1.setSpeedValue(iTempArray[i++]);
			struct1.setTargetValue(iTempArray[i++]);
			param1.setPump4_3(struct1);


			hashmap.put("res2", param1);


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;
	}
    
    /**
     * <pre>
     * 1. ???????????? : insertEndWater
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : jsw
     * 4. ?????? : ??????/????????? modfiy
     * </pre>
     * @param model
     * @param condition2
     * @param speedValue
     * @param targetValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertEndWater", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertEndWater(Model model, String rtuId, String iBdNum, BigDecimal[] condition2, BigDecimal[] speedValue, BigDecimal[] targetValue) throws Exception {
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ENDWATER_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.ENDWATER_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.ENDWATER_MOD);
    	    
    	    // ????????? ?????? 
    	    EndWaterParameters param = new EndWaterParameters();
    	    
    	    BigDecimal multiply = new BigDecimal("100");
    	    
    	    int array = 0;
    	    EndWaterStruct struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setPcs(struct);
    	    System.out.println("struct1 : " + struct.toString());
    	    
    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump1_1(struct);
    	    System.out.println("struct2 : " + struct.toString());
    	    
    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    	    param.setPump4_1(struct);
    	    System.out.println("struct3 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setPump3_1(struct);
    	    System.out.println("struct4 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setPump4_2(struct);
    	    System.out.println("struct5 : " + struct.toString());
    	    
    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setPump4_3(struct);
    	    System.out.println("struct6 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setPump1_2(struct);
    	    System.out.println("struct7 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setSol1(struct);
    	    System.out.println("struct8 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setPump3_2(struct);
    	    System.out.println("struct9 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setStart(struct);
    	    System.out.println("struct10 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setSol6_1(struct);
    	    System.out.println("struct11 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setSol6_2(struct);
    	    System.out.println("struct12 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setThreeway1_1(struct);
    	    System.out.println("struct13 : " + struct.toString());

    	    array++;
    	    struct = new EndWaterStruct();
    	    struct.setCondition2(condition2[array].multiply(multiply).intValue());
    	    struct.setSpeedValue(speedValue[array].multiply(multiply).intValue());
    	    struct.setTargetValue(targetValue[array].multiply(multiply).intValue());
    		param.setThreeway1_2(struct);
    	    System.out.println("struct14 : " + struct.toString());
    	    
    	    packet.setData(PacketDef.ENDWATER_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[14*3];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}
        	
        	System.out.println();
        	
        	EndWaterStruct struct1 = new EndWaterStruct();
    	    EndWaterParameters param1 = new EndWaterParameters();
    	    
    	    int i = 0;
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPcs(struct1);
    		
    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_3(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setSol1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setStart(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setSol6_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setSol6_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setThreeway1_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setThreeway1_2(struct1);

        	hashmap.put("res", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : getEndWater
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : jsw
     * 4. ?????? : ??????/????????? ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEndWater", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getEndWater(Model model, String rtuId, String iBdNum) throws Exception {
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ENDWATER_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.ENDWATER);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.ENDWATER);
    	    
    	    // ????????? ?????? 
    	    EndWaterParameters param = new EndWaterParameters();
    	    
    		param.setPcs(new EndWaterStruct());
    	    param.setPump1_1(new EndWaterStruct());
    	    param.setPump4_1(new EndWaterStruct());
    		param.setPump3_1(new EndWaterStruct());
    		param.setPump4_2(new EndWaterStruct());
    		param.setPump4_3(new EndWaterStruct());
    		param.setPump1_2(new EndWaterStruct());
    		param.setSol1(new EndWaterStruct());
    		param.setPump3_2(new EndWaterStruct());
    		param.setStart(new EndWaterStruct());
    		param.setSol6_1(new EndWaterStruct());
    		param.setSol6_2(new EndWaterStruct());
    		param.setThreeway1_1(new EndWaterStruct());
    		param.setThreeway1_2(new EndWaterStruct());
    	    
    	    packet.setData(PacketDef.ENDWATER, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[14*3];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}
        	
        	System.out.println();
        	
        	EndWaterStruct struct1 = new EndWaterStruct();
    	    EndWaterParameters param1 = new EndWaterParameters();
    	    
    	    int i = 0;
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPcs(struct1);
    		
    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump4_3(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump1_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setSol1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setPump3_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setStart(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setSol6_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setSol6_2(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setThreeway1_1(struct1);

    		struct1 = new EndWaterStruct();
    	    struct1.setCondition2(iTempArray[i++]);
    	    struct1.setSpeedValue(iTempArray[i++]);
    	    struct1.setTargetValue(iTempArray[i++]);
    		param1.setThreeway1_2(struct1);

        	hashmap.put("res", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : insertSimulatorMode
     * 2. ????????? : 2020. 4. 9. ?????? 1:20:58
     * 3. ????????? : jsw
     * 4. ?????? : ????????????????????? ??????/??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param mode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertSimulatorMode", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertSimulatorMode(Model model, String rtuId, String iBdNum, byte mode) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ENDWATER_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.SIMULATION_MODE);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.SIMULATION_MODE);
    	    
    	    // ????????? ?????? 
    	    SimulationModeParameters param = new SimulationModeParameters();
    	    
    	    param.setMode(mode);
    	    
    	    packet.setData(PacketDef.SIMULATION_MODE, param);
    	    System.out.println(param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            // ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[29*4];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}
        	
        	System.out.println();
        	
        	SimulationModeParameters param1 = new SimulationModeParameters();
        	param1.setMode(buff[9]);
    	    hashmap.put("res", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	hashmap.put("result", "success");
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : insertGain
     * 2. ????????? : 2020. 4. 10. ?????? 11:04:32
     * 3. ????????? : jsw
     * 4. ?????? : ??????????????? - gain
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param gain
     * @param gainValue
     * @param offset
     * @param offsetValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertGain", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertGain(Model model, String rtuId, String iBdNum, BigDecimal[] gain, BigDecimal[] gainValue, BigDecimal[] offset, BigDecimal[] offsetValue) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;
        BigDecimal multiply = new BigDecimal("100");

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_GAIN_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.GAIN_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.GAIN_MOD);
    	    
    	    // ????????? ?????? 
    	    GainParameters param = new GainParameters();
    	    GainStruct struct = new GainStruct();
    	    
    	    int array = 0;
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setPump1(struct);
    	    System.out.println("struct1 : " + struct.toString());
    	    
    	    array++;
    	    struct = new GainStruct();
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setPump2(struct);
    	    System.out.println("struct2 : " + struct.toString());
    	    
    	    array++;
    	    struct = new GainStruct();
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setPump3(struct);
    	    System.out.println("struct3 : " + struct.toString());
    	    
    	    array++;
    	    struct = new GainStruct();
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setPump4(struct);
    	    System.out.println("struct4 : " + struct.toString());
    	    
    	    array++;
    	    struct = new GainStruct();
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setFl1(struct);
    	    System.out.println("struct5 : " + struct.toString());

    	    array++;
    	    struct = new GainStruct();
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setFl2(struct);
    	    System.out.println("struct6 : " + struct.toString());

    	    array++;
    	    struct = new GainStruct();
    	    struct.setSelect(multiply.multiply(gain[array]).byteValue());
    	    struct.setValue(multiply.multiply(gainValue[array]).intValue());
    	    param.setFl3(struct);
    	    System.out.println("struct7 : " + struct.toString());
    	    
    	    packet.setData(PacketDef.GAIN_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        	}
        	
        	System.out.println();
        	GainParameters param1 = new GainParameters(buff);
        	
    	    hashmap.put("res1", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : insertOffset
     * 2. ????????? : 2020. 4. 10. ?????? 11:04:32
     * 3. ????????? : jsw
     * 4. ?????? : ??????????????? - offset ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param gain
     * @param gainValue
     * @param offset
     * @param offsetValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertOffset", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertOffset(Model model, String rtuId, String iBdNum, BigDecimal[] gain, BigDecimal[] gainValue, BigDecimal[] offset, BigDecimal[] offsetValue) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
        BigDecimal multiply = new BigDecimal("100");
    	
    	// OFFSET
    	Socket socket1 = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket1.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os1 = socket1.getOutputStream();
        DataOutputStream dos1 = new DataOutputStream(os1);
        
        InputStream is1 = socket1.getInputStream();
        DataInputStream dis1 = new DataInputStream(is1);
        
        // test
        BufferedInputStream bis1 = null;
        
    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_OFFSET_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.OFFSET_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.OFFSET_MOD);
    	    
    	    // ????????? ?????? 
    	    OffsetParameters param = new OffsetParameters();
    	    OffsetStruct struct = new OffsetStruct();
    	    
    	    int array = 0;
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setPump1(struct);
    	    System.out.println("struct1 : " + struct.toString());
    	    
    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setPump2(struct);
    	    System.out.println("struct2 : " + struct.toString());
    	    
    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setPump3(struct);
    	    System.out.println("struct3 : " + struct.toString());
    	    
    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setPump4(struct);
    	    System.out.println("struct4 : " + struct.toString());
    	    
    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setFl1(struct);
    	    System.out.println("struct5 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setFl2(struct);
    	    System.out.println("struct6 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setFl3(struct);
    	    System.out.println("struct7 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc1(struct);
    	    System.out.println("struct8 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc2(struct);
    	    System.out.println("struct9 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc3(struct);
    	    System.out.println("struct10 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc4(struct);
    	    System.out.println("struct11 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc5(struct);
    	    System.out.println("struct12 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc6(struct);
    	    System.out.println("struct13 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc7(struct);
    	    System.out.println("struct14 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc8(struct);
    	    System.out.println("struct15 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc9(struct);
    	    System.out.println("struct16 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc10(struct);
    	    System.out.println("struct17 : " + struct.toString());

    	    array++;
    	    struct = new OffsetStruct();
    	    struct.setSelect(multiply.multiply(offset[array]).byteValue());
    	    struct.setSign(offsetValue[array].doubleValue()<0.0d?(byte)200:(byte)100);
    	    struct.setValue(Math.abs(multiply.multiply(offsetValue[array]).intValue()));
    	    param.setTc11(struct);
    	    System.out.println("struct18 : " + struct.toString());
    	    
    	    
    	    packet.setData(PacketDef.OFFSET_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
//    	    int j = 0;
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
//    			if(i == PacketDef.HEADER_SIZE) {
//	    		System.out.println();
//	    	}else if(PacketDef.HEADER_SIZE < i  && i < PacketDef.DATA_OFFSET_SIZE ){
//	    		
//	    		j++;
//	    		
//	    		if(j % 6 == 0) {
//	    			System.out.println();
//	    			j = 0;
//	    		}
//	    	}
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os1.write(sendByteBuffer.array());
            os1.flush();

        	// ?????? ?????????
        	bis1 = new BufferedInputStream(socket1.getInputStream());
        	byte[] buff1 = new byte[sendData.length];
        	int read2 = bis1.read(buff1, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff1.length; i++) {
        		System.out.print(String.format("%02x ", buff1[i]));
        	}
        	
        	System.out.println();
        	OffsetParameters param2 = new OffsetParameters(buff1);
        	
    	    hashmap.put("res2", param2);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos1.close();
        	dis1.close();
        	socket1.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    
    /**
     * <pre>
     * 1. ???????????? : getGain
     * 2. ????????? : 2020. 4. 10. ?????? 2:12:12
     * 3. ????????? : jsw
     * 4. ?????? : ??????????????? gain
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGain", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getGain(Model model, String rtuId, String iBdNum) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;
//        BigDecimal multiply = new BigDecimal("100");

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_GAIN_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.GAIN);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.GAIN);
    	    
    	    // ????????? ?????? 
    	    GainParameters param = new GainParameters();
    	    param.setPump1(new GainStruct());
    		param.setPump2(new GainStruct());
    		param.setPump3(new GainStruct());
    		param.setPump4(new GainStruct());
    		param.setFl1(new GainStruct());
    		param.setFl2(new GainStruct());
    		param.setFl3(new GainStruct());
    	    
    	    packet.setData(PacketDef.GAIN, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        	}
        	
        	System.out.println();
        	GainParameters param1 = new GainParameters(buff);
        	
    	    hashmap.put("res1", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : getOffset
     * 2. ????????? : 2020. 4. 10. ?????? 2:12:12
     * 3. ????????? : jsw
     * 4. ?????? : ??????????????? offset
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getOffset", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getOffset(Model model, String rtuId, String iBdNum) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		
    	// OFFSET
    	Socket socket1 = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket1.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os1 = socket1.getOutputStream();
        DataOutputStream dos1 = new DataOutputStream(os1);
        
        InputStream is1 = socket1.getInputStream();
        DataInputStream dis1 = new DataInputStream(is1);
        
        // test
        BufferedInputStream bis1 = null;
    	
    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_OFFSET_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.OFFSET);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.OFFSET);
    	    
    	    // ????????? ?????? 
    	    OffsetParameters param = new OffsetParameters();
    	    
    	    param.setPump1(new OffsetStruct());
    		param.setPump2(new OffsetStruct());
    		param.setPump3(new OffsetStruct());
    		param.setPump4(new OffsetStruct());
    		param.setFl1(new OffsetStruct());
    		param.setFl2(new OffsetStruct());
    		param.setFl3(new OffsetStruct());
    		param.setTc1(new OffsetStruct());
    		param.setTc2(new OffsetStruct());
    		param.setTc3(new OffsetStruct());
    		param.setTc4(new OffsetStruct());
    		param.setTc5(new OffsetStruct());
    		param.setTc6(new OffsetStruct());
    		param.setTc7(new OffsetStruct());
    		param.setTc8(new OffsetStruct());
    		param.setTc9(new OffsetStruct());
    		param.setTc10(new OffsetStruct());
    		param.setTc11(new OffsetStruct());
    	    
    	    packet.setData(PacketDef.OFFSET, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os1.write(sendByteBuffer.array());
            os1.flush();

        	// ?????? ?????????
        	bis1 = new BufferedInputStream(socket1.getInputStream());
        	byte[] buff1 = new byte[sendData.length];
        	int read2 = bis1.read(buff1, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff1.length; i++) {
        		System.out.print(String.format("%02x ", buff1[i]));
        	}
        	
        	System.out.println();
        	OffsetParameters param2 = new OffsetParameters(buff1);
        	
    	    hashmap.put("res2", param2);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos1.close();
        	dis1.close();
        	socket1.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : insertKalman
     * 2. ????????? : 2020. 4. 10. ?????? 11:05:30
     * 3. ????????? : jsw
     * 4. ?????? : ????????? ??????-TC?????? ??????(?????????????????????)
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param tc
     * @param tcValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertKalman", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertKalman(Model model, String rtuId, String iBdNum, byte[] tc, byte[] tcValue) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_KALMAN_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.KALMAN_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.KALMAN_MOD);
    	    
    	    // ????????? ?????? 
    	    KalmanParameters param = new KalmanParameters();
    	    KalmanStruct struct = new KalmanStruct();
    	    
    	    int array = 0;
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc1(struct);
    	    System.out.println("struct1 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc2(struct);
    	    System.out.println("struct2 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc3(struct);
    	    System.out.println("struct3 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc4(struct);
    	    System.out.println("struct4 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc5(struct);
    	    System.out.println("struct5 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc6(struct);
    	    System.out.println("struct6 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc7(struct);
    	    System.out.println("struct7 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc8(struct);
    	    System.out.println("struct8 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc9(struct);
    	    System.out.println("struct9 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc10(struct);
    	    System.out.println("struct10 : " + struct.toString());
    	    
    	    array++;
    	    struct = new KalmanStruct();
    	    struct.setSelect(tc[array]);
    	    struct.setOnoff(tcValue[array]);
    	    param.setTc11(struct);
    	    System.out.println("struct11 : " + struct.toString());
    	    
    	    packet.setData(PacketDef.KALMAN_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>> OFFSET");
        	
        	System.out.println(" OFFSET S");
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        	}
        	System.out.println(" OFFSET E");
        	
        	System.out.println();

        	KalmanParameters param1 = new KalmanParameters(buff);
        	
    	    hashmap.put("res", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : getKalman
     * 2. ????????? : 2020. 4. 10. ?????? 2:26:31
     * 3. ????????? : jsw
     * 4. ?????? : ????????????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getKalman", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getKalman(Model model, String rtuId, String iBdNum) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_KALMAN_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.KALMAN);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.KALMAN);
    	    
    	    // ????????? ?????? 
    	    KalmanParameters param = new KalmanParameters();
    	    param.setTc1(new KalmanStruct());
    		param.setTc2(new KalmanStruct());
    		param.setTc3(new KalmanStruct());
    		param.setTc4(new KalmanStruct());
    		param.setTc5(new KalmanStruct());
    		param.setTc6(new KalmanStruct());
    		param.setTc7(new KalmanStruct());
    		param.setTc8(new KalmanStruct());
    		param.setTc9(new KalmanStruct());
    		param.setTc10(new KalmanStruct());
    		param.setTc11(new KalmanStruct());
    	    
    	    packet.setData(PacketDef.KALMAN, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        	}
        	
        	System.out.println();

        	KalmanParameters param1 = new KalmanParameters(buff);
        	
    	    hashmap.put("res", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : insertSimulatorActive
     * 2. ????????? : 2020. 4. 9. ?????? 1:20:58
     * 3. ????????? : jsw
     * 4. ?????? : ????????????????????? ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param errorNo
     * @param pt1
     * @param tc02
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertSimulatorActive", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertSimulatorActive(Model model, String rtuId, String iBdNum, byte errorNo, short pt1, short tc02) throws Exception {
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_SIMULATION_ACTIVE_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.SIMULATION_ACTIVE);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.SIMULATION_ACTIVE);
    	    
    	    // ????????? ?????? 
    	    SimulationActiveParameters param = new SimulationActiveParameters();
    	    
    	    param.setError_no(errorNo);
    	    param.setPt1(pt1);
    	    param.setTc02(tc02);
    	    
    	    packet.setData(PacketDef.SIMULATION_ACTIVE, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        	}
        	
        	System.out.println();
        	short newValue = 0;
        	SimulationActiveParameters param1 = new SimulationActiveParameters();
        	param1.setError_no(buff[9]);
        	
        	newValue = 0;
            newValue |= (((short)buff[11])<<8)&0xFF00;
            newValue |= (((short)buff[10]))&0xFF;
        	param1.setPt1(newValue);
        	
        	newValue = 0;
            newValue |= (((short)buff[13])<<8)&0xFF00;
            newValue |= (((short)buff[12]))&0xFF;
        	param1.setTc02(newValue);
        	
    	    hashmap.put("res", param1);
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

    /**
     * <pre>
     * 1. ???????????? : insertError
     * 2. ????????? : 2020. 4. 8. ?????? 3:52:52
     * 3. ????????? : jsw
     * 4. ?????? : ???????????? modify
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param condition1
     * @param condition3
     * @param sec
     * @param tc3
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertError", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertError(Model model, String rtuId, String iBdNum, BigDecimal[] condition1, BigDecimal[] condition3, BigDecimal[] sec, BigDecimal[] tc3) throws Exception {

    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ERROR_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.ERROR_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.ERROR_MOD);
    	    
    	    // ????????? ?????? 
    	    ErrorParameters param = new ErrorParameters();
    	    
    	    BigDecimal multiply = new BigDecimal("100");
    	    
    	    int array = 0;
    	    ErrorStruct struct = new ErrorStruct();
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setSec(sec[array].multiply(multiply).intValue());
    	    struct.setTc3(tc3[array].multiply(multiply).intValue());
    		param.setError02_1(struct);
    	    System.out.println("struct1 : " + struct.toString());

    	    array++;
    	    struct = new ErrorStruct();
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setSec(sec[array].multiply(multiply).intValue());
    	    struct.setTc3(tc3[array].multiply(multiply).intValue());
    		param.setError02_2(struct);
    	    System.out.println("struct2 : " + struct.toString());

    	    array++;
    	    struct = new ErrorStruct();
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setSec(sec[array].multiply(multiply).intValue());
    	    struct.setTc3(tc3[array].multiply(multiply).intValue());
    		param.setError02_3(struct);
    	    System.out.println("struct3 : " + struct.toString());

    	    array++;
    	    struct = new ErrorStruct();
    	    struct.setCondition1(condition1[array].multiply(multiply).intValue());
    	    struct.setCondition3(condition3[array].multiply(multiply).intValue());
    	    struct.setSec(sec[array].multiply(multiply).intValue());
    	    struct.setTc3(tc3[array].multiply(multiply).intValue());
    		param.setError15_1(struct);
    	    System.out.println("struct4 : " + struct.toString());
    	    
    	    
    	    packet.setData(PacketDef.ERROR_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            // ??????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[16];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			if(j == 1) {
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				j = 0;
        			}
        			j++;	
        		}
        	}
        	
//        	for(int i = 0 ; i < iTempArray.length; i++) {
//        		System.out.println(iTempArray[i] + "-> iTempArray");
//        		
//        	}
        	
        	System.out.println();
        	
        	ErrorStruct struct1 = new ErrorStruct();
        	ErrorParameters param1 = new ErrorParameters();
    	    
        	struct1.setCondition1(iTempArray[0]);
        	struct1.setCondition3(iTempArray[1]);
        	struct1.setSec(iTempArray[2]);
        	struct1.setTc3(iTempArray[3]);
        	param1.setError02_1(struct1);

			struct1 = new ErrorStruct();
        	struct1.setCondition1(iTempArray[4]);
        	struct1.setCondition3(iTempArray[5]);
        	struct1.setSec(iTempArray[6]);
        	struct1.setTc3(iTempArray[7]);
        	param1.setError02_2(struct1);

			struct1 = new ErrorStruct();
        	struct1.setCondition1(iTempArray[8]);
        	struct1.setCondition3(iTempArray[9]);
        	struct1.setSec(iTempArray[10]);
        	struct1.setTc3(iTempArray[11]);
        	param1.setError02_3(struct1);

			struct1 = new ErrorStruct();
        	struct1.setCondition1(iTempArray[12]);
        	struct1.setCondition3(iTempArray[13]);
        	struct1.setSec(iTempArray[14]);
        	struct1.setTc3(iTempArray[15]);
        	param1.setError15_1(struct1);
        	
    	    hashmap.put("res", param1);
    	    
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

	@RequestMapping(value = "/insertErrorNew", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertErrorNew(Model model, String rtuId, String iBdNum, BigDecimal[] condition1New, BigDecimal[] condition3New, BigDecimal[] secNew, BigDecimal[] tc3New) throws Exception {

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);

		socket.setSoTimeout(SOCKET_TIME_OUT);

		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// test
		BufferedInputStream bis = null;

		try {

			//????????????2 ??????
			ByteBuffer sendByteBuffer2 = null;

			sendByteBuffer2 = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ERROR_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);

			sendByteBuffer2.order(ByteOrder.BIG_ENDIAN);

			Packet packet2 = new Packet(PacketDef.ERROR2_MOD);
			packet2.setRTU_ID(Short.parseShort(rtuId));
			packet2.setBD_ID(Short.parseShort(iBdNum));
			packet2.setHeader(PacketDef.ERROR2_MOD);

			// ????????? ??????
			ErrorParameters param2 = new ErrorParameters();

			BigDecimal multiply = new BigDecimal("100");

			int array = 0;
			ErrorStruct struct = new ErrorStruct();
			struct.setCondition1(condition1New[array].multiply(multiply).intValue());
			struct.setCondition3(condition3New[array].multiply(multiply).intValue());
			struct.setSec(secNew[array].multiply(multiply).intValue());
			struct.setTc3(tc3New[array].multiply(multiply).intValue());
			param2.setError08_1(struct);
			System.out.println("struct5 : " + struct.toString());

			array++;
			struct = new ErrorStruct();
			struct.setCondition1(condition1New[array].multiply(multiply).intValue());
			struct.setCondition3(condition3New[array].multiply(multiply).intValue());
			struct.setSec(secNew[array].multiply(multiply).intValue());
			struct.setTc3(tc3New[array].multiply(multiply).intValue());
			param2.setError08_2(struct);
			System.out.println("struct6 : " + struct.toString());


			packet2.setData(PacketDef.ERROR2_MOD, param2);

			// ????????? ????????? ????????????...
			byte[] header = packet2.getHeader();
			byte[] body = packet2.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet2.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();


			// send
			sendByteBuffer2.put(sendData);

			os.write(sendByteBuffer2.array());
			os.flush();

			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);
			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}
			System.out.println("?????? ??? >>>>>>>>>>>>");

			byte[] tempArray = new byte[4];
			int j = 0;
			int k = 0;
			int[] iTempArray = new int[4*2];

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));

				if(i >= header.length && i < header.length + body.length - 1 ) {
					if(j == 1) {
						System.arraycopy(buff, i - 1, tempArray, 0, 3);
						iTempArray[k] = getBigEndian(tempArray);

						k++;
					}else if(j == 4) {
						j = 0;
					}
					j++;
				}
			}

			System.out.println();

			ErrorStruct struct1 = new ErrorStruct();
			ErrorParameters param1 = new ErrorParameters();

			struct1.setCondition1(iTempArray[0]);
			struct1.setCondition3(iTempArray[1]);
			struct1.setSec(iTempArray[2]);
			struct1.setTc3(iTempArray[3]);
			param1.setError08_1(struct1);

			struct1 = new ErrorStruct();
			struct1.setCondition1(iTempArray[4]);
			struct1.setCondition3(iTempArray[5]);
			struct1.setSec(iTempArray[6]);
			struct1.setTc3(iTempArray[7]);
			param1.setError08_2(struct1);


			System.out.println("Parameters : " + param1.toString());

			hashmap.put("res2", param1);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;
	}
    
    /**
     * <pre>
     * 1. ???????????? : getError
     * 2. ????????? : 2020. 4. 8. ?????? 3:52:52
     * 3. ????????? : jsw
     * 4. ?????? : ???????????? modify
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getError", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getError(Model model, String rtuId, String iBdNum) throws Exception {

    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ERROR_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.ERROR);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.ERROR);
    	    
    	    // ????????? ?????? 
    	    ErrorParameters param = new ErrorParameters();
    	    
    	    BigDecimal multiply = new BigDecimal("100");
    	    
    	    int array = 0;
    	    ErrorStruct struct = new ErrorStruct();
    		param.setError02_1(struct);
    	    System.out.println("struct1 : " + struct.toString());

    	    array++;
    	    struct = new ErrorStruct();
    		param.setError02_2(struct);
    	    System.out.println("struct2 : " + struct.toString());

    	    array++;
    	    struct = new ErrorStruct();
    		param.setError02_3(struct);
    	    System.out.println("struct3 : " + struct.toString());

    	    array++;
    	    struct = new ErrorStruct();
    		param.setError15_1(struct);
    	    System.out.println("struct4 : " + struct.toString());
    	    
    	    
    	    packet.setData(PacketDef.ERROR, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[16];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			if(j == 1) {
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				j = 0;
        			}
        			j++;	
        		}
        	}
        	
//        	for(int i = 0 ; i < iTempArray.length; i++) {
//        		System.out.println(iTempArray[i] + "-> iTempArray");
//        		
//        	}
        	
        	System.out.println();
        	
        	ErrorStruct struct1 = new ErrorStruct();
        	ErrorParameters param1 = new ErrorParameters();
    	    
        	struct1.setCondition1(iTempArray[0]);
        	struct1.setCondition3(iTempArray[1]);
        	struct1.setSec(iTempArray[2]);
        	struct1.setTc3(iTempArray[3]);
        	param1.setError02_1(struct1);

			struct1 = new ErrorStruct();
        	struct1.setCondition1(iTempArray[4]);
        	struct1.setCondition3(iTempArray[5]);
        	struct1.setSec(iTempArray[6]);
        	struct1.setTc3(iTempArray[7]);
        	param1.setError02_2(struct1);

			struct1 = new ErrorStruct();
        	struct1.setCondition1(iTempArray[8]);
        	struct1.setCondition3(iTempArray[9]);
        	struct1.setSec(iTempArray[10]);
        	struct1.setTc3(iTempArray[11]);
        	param1.setError02_3(struct1);

			struct1 = new ErrorStruct();
        	struct1.setCondition1(iTempArray[12]);
        	struct1.setCondition3(iTempArray[13]);
        	struct1.setSec(iTempArray[14]);
        	struct1.setTc3(iTempArray[15]);
        	param1.setError15_1(struct1);
        	
    	    System.out.println("Parameters : " + param1.toString());
        	
    	    hashmap.put("res", param1);
    	    
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

	@RequestMapping(value = "/getErrorNew", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getErrorNew(Model model, String rtuId, String iBdNum) throws Exception {

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);

		socket.setSoTimeout(SOCKET_TIME_OUT);

		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// test
		BufferedInputStream bis = null;

		try {
			// ????????????2 ??????
			ByteBuffer sendByteBuffer = null;

//    		sendByteBuffer = ByteBuffer.allocate(500);
			sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_ERROR_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);

			sendByteBuffer.order(ByteOrder.BIG_ENDIAN);

			/* IGNITE test */
			// packet.java class test
			Packet packet = new Packet(PacketDef.ERROR2);
			packet.setRTU_ID(Short.parseShort(rtuId));
			packet.setBD_ID(Short.parseShort(iBdNum));
			packet.setHeader(PacketDef.ERROR2);

			// ????????? ??????
			ErrorParameters param = new ErrorParameters();

			BigDecimal multiply = new BigDecimal("100");

			int array = 0;
			ErrorStruct struct = new ErrorStruct();
			param.setError08_1(struct);
			System.out.println("struct5 : " + struct.toString());

			array++;
			struct = new ErrorStruct();
			param.setError08_2(struct);
			System.out.println("struct6 : " + struct.toString());

			packet.setData(PacketDef.ERROR2, param);

			// ????????? ????????? ????????????...
			byte[] header = packet.getHeader();
			byte[] body = packet.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();


			// send
			sendByteBuffer.put(sendData);

			os.write(sendByteBuffer.array());
			os.flush();


			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);
			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}
			System.out.println("?????? ??? >>>>>>>>>>>>");

			byte[] tempArray = new byte[4];
			int j = 0;
			int k = 0;
			int[] iTempArray = new int[2*4];

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));

				if(i >= header.length && i < header.length + body.length - 1 ) {
					if(j == 1) {
						System.arraycopy(buff, i - 1, tempArray, 0, 3);
						iTempArray[k] = getBigEndian(tempArray);

						k++;
					}else if(j == 4) {
						j = 0;
					}
					j++;
				}
			}

			System.out.println();

			ErrorStruct struct1 = new ErrorStruct();
			ErrorParameters param1 = new ErrorParameters();

			struct1.setCondition1(iTempArray[0]);
			struct1.setCondition3(iTempArray[1]);
			struct1.setSec(iTempArray[2]);
			struct1.setTc3(iTempArray[3]);
			param1.setError08_1(struct1);

			struct1 = new ErrorStruct();
			struct1.setCondition1(iTempArray[4]);
			struct1.setCondition3(iTempArray[5]);
			struct1.setSec(iTempArray[6]);
			struct1.setTc3(iTempArray[7]);
			param1.setError08_2(struct1);

			System.out.println("Parameters : " + param1.toString());


			hashmap.put("res2", param1);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			os.close();
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;
	}

    /**
     * <pre>
     * 1. ???????????? : insertControll
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : jeongeun
     * 4. ?????? : ???????????? modfiy
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param editValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertControll", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertControll(Model model, String rtuId, String iBdNum, BigDecimal[] editValue) throws Exception {
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
			System.out.println("???????????? ??????!!!!!??????");
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_OPERATION_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.OPERATION_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.OPERATION_MOD);
			System.out.println("???????????? ?????? packet set");

    	    // ????????? ?????? 
    	    
    	    BigDecimal multplyDate = new BigDecimal("100");
    	    int ediDateI = 0;
			
			OperationStruct struct = new OperationStruct();
			OperationParameters param = new OperationParameters();
			struct.setCondition1(0);
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue()); 
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPump1_1(struct);
			System.out.println("Parameters1 pump1_1: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump1_2(struct);
			System.out.println("Parameters2 pump1_2: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump1_3(struct);
			System.out.println("Parameters3 pump1_3: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump1_4(struct);
			System.out.println("Parameters4 pump1_4 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump1_5(struct);
			System.out.println("Parameters5 pump1_5 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump1_6(struct);
			System.out.println("Parameters6 pump1_6 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump1_7(struct);
			System.out.println("Parameters7 pump1_7 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump1_8(struct);
			System.out.println("Parameters8 pump1_8 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			param.setPump1_9(struct);
			System.out.println("Parameters9 pump1_9: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump1_10(struct);
			System.out.println("Parameters10 pump1_10 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump1_11(struct);
			System.out.println("Parameters11 pump1_11 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump1_12(struct);
			System.out.println("Parameters12 pump1_12 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setCondition3(0);
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump1_13(struct);
			System.out.println("Parameters13 pump1_13 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(0);
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump3_1(struct);
			System.out.println("Parameters14 pump3_1: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump3_2(struct);                                                    
			System.out.println("Parameters15 pump3_2: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump3_3(struct);
			System.out.println("Parameters16 pump3_3: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump3_4(struct);
			System.out.println("Parameters17 pump3_4 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump3_5(struct);
			System.out.println("Parameters18 pump3_5 : " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setCondition3(0);
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump3_6(struct);
			System.out.println("Parameters19 pump3_6 : " + param.toString());

			struct = new OperationStruct();
			struct.setCondition1(0);
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());  
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue()); 
			param.setPump4_1(struct);
			System.out.println("Parameters20 pump4_1: " + param.toString());

			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPump4_3(struct);
			System.out.println("Parameters21 pump4_3: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setCondition3(0);
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());  
			param.setPump4_4(struct);
			System.out.println("Parameters22 pump4_4: " + param.toString());

			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
    	    struct.setCondition3(0);  
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
    	    struct.setTargetValue(0); 
			param.setPcs1_1(struct);
			System.out.println("Parameters23 Pcs1_1: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(0);  
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());
    	    struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPcs1_2(struct);
			System.out.println("Parameters24 Pcs1_2: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(0);  
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPcs1_3(struct);
			System.out.println("Parameters25 Pcs1_3: " + param.toString());

			struct = new OperationStruct();
			struct.setCondition1(0);
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());    
    	    struct.setSpeedValue(0);
    	    struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());   
			param.setPump2_1(struct);
			System.out.println("Parameters26 pump2_1: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(0);
    	    struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());    
    	    struct.setSpeedValue(0);
    	    struct.setTargetValue(0);
			param.setPump2_2(struct);
			System.out.println("Parameters27 pump2_2: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(0);
			struct.setCondition3(0);
			struct.setSpeedValue(0);
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPump2_3(struct);
			System.out.println("Parameters28 pump2_3: " + param.toString());
			
			struct = new OperationStruct();
			struct.setCondition1(0);
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());    
			struct.setSpeedValue(0);
			struct.setTargetValue(0);
			param.setPump2_4(struct);
			System.out.println("Parameters29 pump2_4 : " + param.toString());
			
			packet.setData(PacketDef.OPERATION_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[29*4];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}

			OperationStruct struct1 = new OperationStruct();
			OperationParameters param1 = new OperationParameters();
			
			ediDateI = 0;
			
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump1_1(struct1);
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);  
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);  
    	    struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_2(struct1);
			System.out.println("Parameters2 pump1_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_3(struct1);
			System.out.println("Parameters3 pump1_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_4(struct1);
			System.out.println("Parameters4 pump1_4 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_5(struct1);
			System.out.println("Parameters5 pump1_5 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_6(struct1);
			System.out.println("Parameters6 pump1_6 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_7(struct1);
			System.out.println("Parameters7 pump1_7 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_8(struct1);
			System.out.println("Parameters8 pump1_8 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]);   
			param1.setPump1_9(struct1);
			System.out.println("Parameters9 pump1_9: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_10(struct1);
			System.out.println("Parameters10 pump1_10 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_11(struct1);
			System.out.println("Parameters11 pump1_11 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_12(struct1);
			System.out.println("Parameters12 pump1_12 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);  
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_13(struct1);
			System.out.println("Parameters13 pump1_13 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);   
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);   
    	    struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_1(struct1);
			System.out.println("Parameters14 pump3_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);   
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);   
    	    struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_2(struct1);                                                    
			System.out.println("Parameters15 pump3_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump3_3(struct1);
			System.out.println("Parameters16 pump3_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump3_4(struct1);
			System.out.println("Parameters17 pump3_4 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_5(struct1);
			System.out.println("Parameters18 pump3_5 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);   
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_6(struct1);
			System.out.println("Parameters19 pump3_6 : " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);  
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);  
    	    struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump4_1(struct1);
			System.out.println("Parameters20 pump4_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);   
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);   
    	    struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump4_3(struct1);
			System.out.println("Parameters21 pump4_3: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump4_4(struct1);
			System.out.println("Parameters22 pump4_4: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);  
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPcs1_1(struct1);
			System.out.println("Parameters23 Pcs1_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);  
    	    struct1.setCondition3(iTempArray[ediDateI++]);
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPcs1_2(struct1);
			System.out.println("Parameters24 Pcs1_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);  
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPcs1_3(struct1);
			System.out.println("Parameters25 Pcs1_3: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);    
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);   
			param1.setPump2_1(struct1);
			System.out.println("Parameters26 pump2_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);    
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump2_2(struct1);
			System.out.println("Parameters27 pump2_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);   
			param1.setPump2_3(struct1);
			System.out.println("Parameters28 pump2_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);    
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump2_4(struct1);
			System.out.println("Parameters29 pump2_4 : " + param1.toString());

			hashmap.put("res", param1);
			
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

	@RequestMapping(value = "/insertControllNew", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertControllNew(Model model, String rtuId, String iBdNum, BigDecimal[] editValue) throws Exception {

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);

		socket.setSoTimeout(SOCKET_TIME_OUT);

		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// test
		BufferedInputStream bis = null;

		try {
			// ????????????2 ??????
			ByteBuffer sendByteBuffer = null;

			sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_OPERATION_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);

			sendByteBuffer.order(ByteOrder.BIG_ENDIAN);

			/* IGNITE test */
			// packet.java class test
			Packet packet = new Packet(PacketDef.OPERATION2_MOD);
			packet.setRTU_ID(Short.parseShort(rtuId));
			packet.setBD_ID(Short.parseShort(iBdNum));
			packet.setHeader(PacketDef.OPERATION2_MOD);

			// ????????? ??????

			BigDecimal multplyDate = new BigDecimal("100");
			int ediDateI = 0;

			OperationStruct struct = new OperationStruct();
			OperationParameters param = new OperationParameters();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(0);
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPump1_14(struct);
			System.out.println("Parameters1 pump1_14: " + param.toString());

			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(0);
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPump1_15(struct);
			System.out.println("Parameters2 pump1_15: " + param.toString());

			struct = new OperationStruct();
			struct.setCondition1(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setCondition3(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setSpeedValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			struct.setTargetValue(editValue[ediDateI++].multiply(multplyDate).intValue());
			param.setPump4_2(struct);
			System.out.println("Parameters3 pump4_2: " + param.toString());

			packet.setData(PacketDef.OPERATION2_MOD, param);

			// ????????? ????????? ????????????...
			byte[] header = packet.getHeader();
			byte[] body = packet.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();

			// send
			sendByteBuffer.put(sendData);

			os.write(sendByteBuffer.array());
			os.flush();

			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);

			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}

			System.out.println("?????? ??? >>>>>>>>>>>>");
			byte[] tempArray = new byte[4];
			int j = 0;
			int k = 0;
			int[] iTempArray = new int[10*4];

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));

				if(i >= header.length && i < header.length + body.length - 1 ) {

					if(j == 1) {

						System.arraycopy(buff, i - 1, tempArray, 0, 3);
						iTempArray[k] = getBigEndian(tempArray);

						k++;
					}else if(j == 4) {

						j = 0;

					}

					j++;

				}
			}

			OperationStruct struct1 = new OperationStruct();
			OperationParameters param1 = new OperationParameters();

			ediDateI = 0;

			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump1_14(struct1);
			System.out.println("Parameters2 pump1_14: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump1_15(struct1);
			System.out.println("Parameters2 pump1_15: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump4_2(struct1);
			System.out.println("Parameters3 pump4_2: " + param1.toString());


			hashmap.put("res2", param1);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;
	}
    
    /**
     * <pre>
     * 1. ???????????? : getControll
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : jeongeun
     * 4. ?????? : ???????????? ????????? ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getControll", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getControll(Model model, String rtuId, String iBdNum) throws Exception {
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
			System.out.println("???????????? ??????");
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_OPERATION_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.OPERATION);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.OPERATION);
			System.out.println("???????????? packet set");
			
    	    // ????????? ?????? 
			OperationParameters param = new OperationParameters();
    	    
			param.setPump1_1(new OperationStruct());
			param.setPump1_2(new OperationStruct());
			param.setPump1_3(new OperationStruct());
			param.setPump1_4(new OperationStruct());
			param.setPump1_5(new OperationStruct());
			param.setPump1_6(new OperationStruct());
			param.setPump1_7(new OperationStruct());
			param.setPump1_8(new OperationStruct());
			param.setPump1_9(new OperationStruct());
			param.setPump1_10(new OperationStruct());
			param.setPump1_11(new OperationStruct());
			param.setPump1_12(new OperationStruct());
			param.setPump1_13(new OperationStruct());
			
			param.setPump3_1(new OperationStruct());
			param.setPump3_2(new OperationStruct());
			param.setPump3_3(new OperationStruct());
			param.setPump3_4(new OperationStruct());
			param.setPump3_5(new OperationStruct());
			param.setPump3_6(new OperationStruct());
			
			param.setPump4_1(new OperationStruct());
			param.setPump4_3(new OperationStruct());
			param.setPump4_4(new OperationStruct());
			
			param.setPcs1_1(new OperationStruct());
			param.setPcs1_2(new OperationStruct());
			param.setPcs1_3(new OperationStruct());

			param.setPump2_1(new OperationStruct());
			param.setPump2_2(new OperationStruct());
			param.setPump2_3(new OperationStruct());
			param.setPump2_4(new OperationStruct());

			System.out.println("???????????? sendData ??????");
    	    packet.setData(PacketDef.OPERATION, param);
			System.out.println("???????????? sendData ??????");
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
        	
    	    // send
        	sendByteBuffer.put(sendData);

            os.write(sendByteBuffer.array());
			os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
			System.out.println("??????????????? ??????");
        	bis = new BufferedInputStream(socket.getInputStream());
			System.out.println("BufferedInputStream");
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	byte[] tempArray = new byte[4];
        	int j = 0;
        	int k = 0;
        	int[] iTempArray = new int[29*4];
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        		
        		if(i >= header.length && i < header.length + body.length - 1 ) {
        			
        			if(j == 1) {
        				
        				System.arraycopy(buff, i - 1, tempArray, 0, 3);
        				iTempArray[k] = getBigEndian(tempArray);
        				
        				k++;
        			}else if(j == 4) {
        				
        				j = 0;
        				
        			}
        			
        			j++;
        			
        		}
        	}
        	
        	System.out.println();
        	
        	OperationStruct struct1 = new OperationStruct();
			OperationParameters param1 = new OperationParameters();
			
			int ediDateI = 0;
			
			
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump1_1(struct1);
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);  
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);  
    	    struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_2(struct1);
			System.out.println("Parameters2 pump1_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_3(struct1);
			System.out.println("Parameters3 pump1_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_4(struct1);
			System.out.println("Parameters4 pump1_4 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_5(struct1);
			System.out.println("Parameters5 pump1_5 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_6(struct1);
			System.out.println("Parameters6 pump1_6 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_7(struct1);
			System.out.println("Parameters7 pump1_7 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_8(struct1);
			System.out.println("Parameters8 pump1_8 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]);   
			param1.setPump1_9(struct1);
			System.out.println("Parameters9 pump1_9: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_10(struct1);
			System.out.println("Parameters10 pump1_10 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump1_11(struct1);
			System.out.println("Parameters11 pump1_11 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_12(struct1);
			System.out.println("Parameters12 pump1_12 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);  
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump1_13(struct1);
			System.out.println("Parameters13 pump1_13 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);   
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);   
    	    struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_1(struct1);
			System.out.println("Parameters14 pump3_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);   
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);   
    	    struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_2(struct1);                                                    
			System.out.println("Parameters15 pump3_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump3_3(struct1);
			System.out.println("Parameters16 pump3_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);  
			struct1.setSpeedValue(iTempArray[ediDateI++]);  
			struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump3_4(struct1);
			System.out.println("Parameters17 pump3_4 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);   
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_5(struct1);
			System.out.println("Parameters18 pump3_5 : " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);   
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump3_6(struct1);
			System.out.println("Parameters19 pump3_6 : " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);  
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);  
    	    struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPump4_1(struct1);
			System.out.println("Parameters20 pump4_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);   
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);   
    	    struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump4_3(struct1);
			System.out.println("Parameters21 pump4_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);   
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);   
			struct1.setTargetValue(iTempArray[ediDateI++]);  
			param1.setPump4_4(struct1);
			System.out.println("Parameters22 pump4_4: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);  
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]); 
			param1.setPcs1_1(struct1);
			System.out.println("Parameters23 Pcs1_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);  
    	    struct1.setCondition3(iTempArray[ediDateI++]);
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPcs1_2(struct1);
			System.out.println("Parameters24 Pcs1_2: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);  
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPcs1_3(struct1);
			System.out.println("Parameters25 Pcs1_3: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);    
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);   
			param1.setPump2_1(struct1);
			System.out.println("Parameters26 pump2_1: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
    	    struct1.setCondition3(iTempArray[ediDateI++]);    
    	    struct1.setSpeedValue(iTempArray[ediDateI++]);
    	    struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump2_2(struct1);
			System.out.println("Parameters27 pump2_2: " + param1.toString());             
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);   
			param1.setPump2_3(struct1);
			System.out.println("Parameters28 pump2_3: " + param1.toString());
			
			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);    
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump2_4(struct1);
			System.out.println("Parameters29 pump2_4 : " + param1.toString());

			hashmap.put("res", param1);
        	
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			os.close();
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }

	@RequestMapping(value = "/getControllNew", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getControllNew(Model model, String rtuId, String iBdNum) throws Exception {

		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);

		socket.setSoTimeout(SOCKET_TIME_OUT);

		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// test
		BufferedInputStream bis = null;

		try {

			// ????????????2 ??????
			ByteBuffer sendByteBuffer = null;

			sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_OPERATION_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);

			sendByteBuffer.order(ByteOrder.BIG_ENDIAN);

			/* IGNITE test */
			// packet.java class test
			Packet packet = new Packet(PacketDef.OPERATION2);
			packet.setRTU_ID(Short.parseShort(rtuId));
			packet.setBD_ID(Short.parseShort(iBdNum));
			packet.setHeader(PacketDef.OPERATION2);
			System.out.println("???????????? set packet");

			// ????????? ??????
			OperationParameters param = new OperationParameters();

			param.setPump1_14(new OperationStruct());
			param.setPump1_15(new OperationStruct());
			param.setPump4_2(new OperationStruct());

			System.out.println("???????????? setData OPERATION2 packet");
			packet.setData(PacketDef.OPERATION2, param);
			System.out.println("???????????? setData OPERATION2 packet");

			// ????????? ????????? ????????????...
			byte[] header = packet.getHeader();
			byte[] body = packet.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();


			// send
			sendByteBuffer.put(sendData);

			os.write(sendByteBuffer.array());
			os.flush();


			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);
			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}
			System.out.println("?????? ??? >>>>>>>>>>>>");
			byte[] tempArray = new byte[4];
			int j = 0;
			int k = 0;
			int[] iTempArray = new int[3*4];

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));

				if(i >= header.length && i < header.length + body.length - 1 ) {

					if(j == 1) {

						System.arraycopy(buff, i - 1, tempArray, 0, 3);
						iTempArray[k] = getBigEndian(tempArray);

						k++;
					}else if(j == 4) {

						j = 0;

					}

					j++;

				}
			}

			System.out.println();

			int ediDateI = 0;

			OperationStruct struct1 = new OperationStruct();
			OperationParameters param1 = new OperationParameters();

			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump1_14(struct1);
			System.out.println("Parameters2 pump1_14: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump1_15(struct1);
			System.out.println("Parameters2 pump1_15: " + param1.toString());

			struct1 = new OperationStruct();
			struct1.setCondition1(iTempArray[ediDateI++]);
			struct1.setCondition3(iTempArray[ediDateI++]);
			struct1.setSpeedValue(iTempArray[ediDateI++]);
			struct1.setTargetValue(iTempArray[ediDateI++]);
			param1.setPump4_2(struct1);
			System.out.println("Parameters3 pump4_2: " + param1.toString());



			hashmap.put("res2", param1);


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;
	}

    /**
     * <pre>
     * 1. ???????????? : insertParameters
     * 2. ????????? : 2020. 4. 9
     * 3. ????????? : jeongeun
     * 4. ?????? : ???????????? modfiy
     * </pre>
     * @param model
     * @param parameterValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertParameter", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> insertParameters(Model model, @RequestBody HashMap<String, BigDecimal> parameterValue) throws Exception {
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.DATA_PARAMETER_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.PARAMETER);
    	    packet.setRTU_ID(parameterValue.get("rtuId").shortValue());
    	    packet.setBD_ID(parameterValue.get("iBdNum").shortValue());
    	    packet.setHeader(PacketDef.PARAMETER);
    	    
    	    // ????????? ?????? 
    	    BigDecimal multplyDate = new BigDecimal("100");

    	    Parameter8Struct struct8 = new Parameter8Struct();
    	    Parameter32Struct struct32 = new Parameter32Struct();
			ParameterParameters param = new ParameterParameters();
			
			struct32.setSelect(parameterValue.get("pump1Select").multiply(multplyDate).byteValue());
			struct32.setValue(parameterValue.get("pump1Value").multiply(multplyDate).intValue());
			param.setPump1(struct32);
			System.out.println("Parameters1 pump1: " + param.toString());
			
			struct32 = new Parameter32Struct();
			
			struct32.setSelect(parameterValue.get("pump2Select").multiply(multplyDate).byteValue());
			struct32.setValue(parameterValue.get("pump2Value").multiply(multplyDate).intValue());
			param.setPump2(struct32);
			System.out.println("Parameters1 pump2: " + param.toString());
			
			struct32 = new Parameter32Struct();
			
			struct32.setSelect(parameterValue.get("pump3Select").multiply(multplyDate).byteValue());
			struct32.setValue(parameterValue.get("pump3Value").multiply(multplyDate).intValue());
			param.setPump3(struct32);
			System.out.println("Parameters1 pump3: " + param.toString());
			
			struct32 = new Parameter32Struct();
			
			struct32.setSelect(parameterValue.get("pump4Select").multiply(multplyDate).byteValue());
			struct32.setValue(parameterValue.get("pump4Value").multiply(multplyDate).intValue());
			param.setPump4(struct32);
			System.out.println("Parameters1 pump4: " + param.toString());
			
			struct32 = new Parameter32Struct();

			struct32.setSelect(parameterValue.get("pcsSelect").multiply(multplyDate).byteValue());
			struct32.setValue(parameterValue.get("pcsValue").multiply(multplyDate).intValue());
			param.setPcs(struct32);
			System.out.println("Parameters1 pcs: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("snhSelect").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("snhValue").multiply(multplyDate).byteValue());
			param.setSnh(struct8);
			System.out.println("Parameters1 snh: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("sol1Select").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("sol1Value").multiply(multplyDate).byteValue());
			param.setSol1(struct8);
			System.out.println("Parameters1 sol1: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("sol2Select").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("sol2Value").multiply(multplyDate).byteValue());
			param.setSol2(struct8);
			System.out.println("Parameters1 sol2: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("sol4Select").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("sol4Value").multiply(multplyDate).byteValue());
			param.setSol4(struct8);
			System.out.println("Parameters1 sol4: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("sol5Select").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("sol5Value").multiply(multplyDate).byteValue());
			param.setSol5(struct8);
			System.out.println("Parameters1 sol5: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("sol6Select").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("sol6Value").multiply(multplyDate).byteValue());
			param.setSol6(struct8);
			System.out.println("Parameters1 sol6: " + param.toString());
			
			struct8 = new Parameter8Struct();
			
			struct8.setSelect(parameterValue.get("threeway1Select").multiply(multplyDate).byteValue());
			struct8.setValue(parameterValue.get("threeway1Value").multiply(multplyDate).byteValue());
			param.setThreeway1(struct8);
			System.out.println("Parameters1 threeway1: " + param.toString());
			
			param.setU8(parameterValue.get("u8").multiply(multplyDate).byteValue());
			System.out.println("Parameters1 u8: " + param.toString());
			
			packet.setData(PacketDef.PARAMETER, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }

    		// send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// ??????
        	
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	ParameterParameters param1 = new ParameterParameters(buff);
        	
			hashmap.put("res", param1);
			
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    }
    
    /**
     * <pre>
     * 1. ???????????? : insertGeneratorMode
     * 2. ????????? : 2020. 4. 8. ?????? 3:34:19
     * 3. ????????? : kct
     * 4. ?????? : ?????? ??????
     * </pre>
     * @param model
     * @param rtuId
     * @param iBdNum
     * @param start
     * @param stop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertGeneratorMode", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> startGenerator(Model model, String rtuId, String iBdNum, byte start, byte stop) throws Exception {
		
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	
    	Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);
        
        socket.setSoTimeout(SOCKET_TIME_OUT);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
//    		sendByteBuffer = ByteBuffer.allocate(500);
    		sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.GENERATOR_MOD_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);
    		
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.GENERATOR_MOD);
    	    packet.setRTU_ID(Short.parseShort(rtuId));
    	    packet.setBD_ID(Short.parseShort(iBdNum));
    	    packet.setHeader(PacketDef.GENERATOR_MOD);
    	    
    	    // ????????? ?????? 
    	    
    	    BigDecimal multiply = new BigDecimal("100");
    	    GeneratorParameters param = new GeneratorParameters();
    	    
    	    param.setStart(start);
    	    param.setStop(stop);
    	    
    	    System.out.println("Parameters : " + param.toString());
    	    
    	    packet.setData(PacketDef.GENERATOR_MOD, param);
    	    
    	    // ????????? ????????? ????????????...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // ?????????
    	    System.out.println("Header Size : " + header.length);
    	    System.out.println("Body Size : " + body.length);
    	    System.out.print("Header : ");
    		for(int i = 0; i < header.length; i++) {
    	    	System.out.print(String.format("%02x ", header[i]));
    	    }
    		System.out.println();
    		System.out.print("Body : ");
    		for(int i = 0; i < body.length; i++) {
    	    	System.out.print(String.format("%02x ", body[i]));
    	    }
    	    
    	    // header + body
    		System.out.print("\nData : ");
    	    byte[] data = new byte[header.length + body.length];
    	    System.arraycopy(header, 0, data, 0, header.length);
    	    System.arraycopy(body, 0, data, header.length, body.length);
    	    
    	    // ?????????
    	    for(int i = 0; i < data.length; i++) {
    	    	System.out.print(String.format("%02x ", data[i]));
    	    }
    	    
    	    // checksum
    	    byte checksum = packet.getChecksum(data);
    	    System.out.println("\nChecksum : " + String.format("%02x ", checksum));
    	    
    	    // final packet data
    	    byte[] sendData = new byte[data.length + 1];
    	    System.arraycopy(data, 0, sendData, 0, data.length);
    	    sendData[data.length] = checksum;
    		System.out.print("\nSendData : ");
    		for(int i = 0; i < sendData.length; i++) {
    	    	System.out.print(String.format("%02x ", sendData[i]));
    	    }
    		System.out.println();
    	    
    	    // send
        	sendByteBuffer.put(sendData);
    	    
            os.write(sendByteBuffer.array());
            os.flush();

            /* ?????? ??????... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	
        	// ?????? ?????????
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("?????? ??? >>>>>>>>>>>>");
        	
        	for(int i = 0; i < buff.length; i++) {
        		System.out.print(String.format("%02x ", buff[i]));
        	}
        	
        	System.out.println();
        	
        	GeneratorParameters param1 = new GeneratorParameters();
    	    
        	param1.setStart(buff[9]);
        	param1.setStop(buff[10]);
    	    
    	    hashmap.put("res", param1);
    	    
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n?????? ?????? ??????");
        }		
    	
    	hashmap.put("result", "success");
    	
    	return hashmap;
    	
    }


	@RequestMapping(value = "/insertErrorStopMode", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> stopError(Model model, String rtuId, String iBdNum, byte stop) throws Exception {

		HashMap<String, Object> hashmap = new HashMap<String, Object>();

		Socket socket = new Socket( InetAddress.getByName(socketIp) , socketPort);

		socket.setSoTimeout(SOCKET_TIME_OUT);

		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// test
		BufferedInputStream bis = null;

		try {
			ByteBuffer sendByteBuffer = null;

//    		sendByteBuffer = ByteBuffer.allocate(500);
			sendByteBuffer = ByteBuffer.allocate(PacketDef.HEADER_SIZE + PacketDef.GENERATOR_MOD_SIZE + PacketDef.ETX_SIZE + PacketDef.CHECKSUM_SIZE);

			sendByteBuffer.order(ByteOrder.BIG_ENDIAN);

			/* IGNITE test */
			// packet.java class test
			Packet packet = new Packet(PacketDef.ERROR_STOP_MOD);
			packet.setRTU_ID(Short.parseShort(rtuId));
			packet.setBD_ID(Short.parseShort(iBdNum));
			packet.setHeader(PacketDef.ERROR_STOP_MOD);

			// ????????? ??????
			ErrorParameters param = new ErrorParameters();

			param.setStop(stop);

			System.out.println("Parameters : " + param.toString());

			packet.setData(PacketDef.ERROR_STOP_MOD, param);

			// ????????? ????????? ????????????...
			byte[] header = packet.getHeader();
			byte[] body = packet.getBody();

			// ?????????
			System.out.println("Header Size : " + header.length);
			System.out.println("Body Size : " + body.length);
			System.out.print("Header : ");
			for(int i = 0; i < header.length; i++) {
				System.out.print(String.format("%02x ", header[i]));
			}
			System.out.println();
			System.out.print("Body : ");
			for(int i = 0; i < body.length; i++) {
				System.out.print(String.format("%02x ", body[i]));
			}

			// header + body
			System.out.print("\nData : ");
			byte[] data = new byte[header.length + body.length];
			System.arraycopy(header, 0, data, 0, header.length);
			System.arraycopy(body, 0, data, header.length, body.length);

			// ?????????
			for(int i = 0; i < data.length; i++) {
				System.out.print(String.format("%02x ", data[i]));
			}

			// checksum
			byte checksum = packet.getChecksum(data);
			System.out.println("\nChecksum : " + String.format("%02x ", checksum));

			// final packet data
			byte[] sendData = new byte[data.length + 1];
			System.arraycopy(data, 0, sendData, 0, data.length);
			sendData[data.length] = checksum;
			System.out.print("\nSendData : ");
			for(int i = 0; i < sendData.length; i++) {
				System.out.print(String.format("%02x ", sendData[i]));
			}
			System.out.println();

			// send
			sendByteBuffer.put(sendData);

			os.write(sendByteBuffer.array());
			os.flush();


			// ?????? ?????????
			bis = new BufferedInputStream(socket.getInputStream());
			byte[] buff = new byte[sendData.length];
			int read2 = bis.read(buff, 0, sendData.length);
			if(read2 < 0) {
				System.out.println("read2 Error : " + read2);
			}
			System.out.println("?????? ??? >>>>>>>>>>>>");

			for(int i = 0; i < buff.length; i++) {
				System.out.print(String.format("%02x ", buff[i]));
			}

			System.out.println();

			ErrorParameters param1 = new ErrorParameters();
//
//			param1.setStart(buff[9]);
			param1.setStop(buff[8]);

			hashmap.put("res", param1);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos.close();
			dis.close();
			socket.close();
			System.out.println("\n?????? ?????? ??????");
		}

		hashmap.put("result", "success");

		return hashmap;

	}
    
    
	public static int getBigEndian(byte[] v) {
		
	    int[] array = new int[4];
	    for (int i = 0; i < 4; i++) array[i] = (int) (v[3 - i] & 0xFF);
	    return ((array[0] << 24) + (array[1] << 16) + (array[2] << 8) + (array[3] << 0));
	}
}
