package com.stx.sofc.login.service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.stx.sofc.login.dao.LoginDAO;
import com.stx.sofc.login.vo.LoginVO;
import com.stx.sofc.util.protocol.Packet;
import com.stx.sofc.util.protocol.PacketDef;
import com.stx.sofc.util.protocol.stxControllPacket.IgniteParameters;
import com.stx.sofc.util.protocol.stxControllPacket.IgniteStruct;

@Service
public class LoginServiceImpl implements LoginService{
	@Inject
	private LoginDAO dao;

	@Override
	public int authInfoCnt(LoginVO vo) throws Exception {
		
		return dao.authInfoCnt(vo);
	}
	
	@Override
	public int authInfoPwdCnt(LoginVO vo) throws Exception {
		
		return dao.authInfoPwdCnt(vo);
	}
	
	@Override
	public LoginVO loginProcess(LoginVO vo) throws Exception {
		
		return dao.loginProcess(vo);
	}

	// for test - 황유성
	@Override
	public void sendTcpData() throws Exception {
		System.out.println("==== 황유성 테스트 =====================");
		Socket socket = new Socket( InetAddress.getByName("58.72.255.154") , 5201);
        
        socket.setSoTimeout(5000);
        
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // test
        BufferedInputStream bis = null;

    	try {
    		ByteBuffer sendByteBuffer = null;
    		
    		sendByteBuffer = ByteBuffer.allocate(500);
    	    sendByteBuffer.order(ByteOrder.BIG_ENDIAN);
    	    
    	    /* IGNITE test */
    	    // packet.java class test
    	    Packet packet = new Packet(PacketDef.IGNITE);
    	    packet.setRTU_ID((short) 1);
    	    packet.setBD_ID((short) 1);
    	    packet.setHeader(PacketDef.IGNITE);
    	    
    	    // 데이터 셋팅 
    	    IgniteStruct struct = new IgniteStruct();
    	    IgniteParameters param = new IgniteParameters();
//    	    struct.setCondition1(100);
//    	    struct.setCondition3(0);
    	    struct.setTargetValue(500);
    	    param.setPump3(struct);
    	    System.out.println("Parameters1 : " + param.toString());
    	    
    	    struct = new IgniteStruct();
    	    struct.setCondition1(0);
    	    struct.setCondition3(400);
    	    struct.setTargetValue(0);
    	    param.setPump2(struct);
    	    System.out.println("Parameters2 : " + param.toString());
    	    
    	    struct = new IgniteStruct();
    	    struct.setCondition1(1000);
    	    struct.setCondition3(0);
    	    struct.setTargetValue(300);
    	    param.setSnh(struct);
    	    
    	    System.out.println("Parameters3 : " + param.toString());
    	    
    	    packet.setData(PacketDef.IGNITE, param);
    	    
    	    
    	    /*
    	    // GAIN test
    	    Packet packet = new Packet(PacketDef.GAIN);
    	    packet.setRTU_ID((short) 1);
    	    packet.setBD_ID((short) 1);
    	    packet.setHeader(PacketDef.GAIN);
    	    
    	    // parameter setting
    	    GainStruct[] gain = new GainStruct[7];
    	    for(int i = 0; i < gain.length; i++) {
    	    	gain[i] = new GainStruct();
    	    }
    	    gain[0].setSelect((byte)1); gain[0].setValue(1);
    	    gain[1].setSelect((byte)0);	gain[1].setValue(15);
    	    gain[2].setSelect((byte)1);	gain[2].setValue(47);
    	    gain[3].setSelect((byte)0);	gain[3].setValue(63);
    	    gain[4].setSelect((byte)1);	gain[4].setValue(79);
    	    gain[5].setSelect((byte)0);	gain[5].setValue(100);
    	    gain[6].setSelect((byte)1);	gain[6].setValue(9999);
    	    
    	    packet.setData(PacketDef.GAIN, gain);
    	    */
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    // 아래는 그대로 두면될듯...
    	    byte[] header = packet.getHeader();
    	    byte[] body = packet.getBody();
    	    
    	    // 확인용
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
    	    
    	    // 확인용
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

            /* 읽는 부분... */
        	byte[] reciveData = null;
        	byte[] headerBuffer = new byte[231];
        	//dis.read(headerBuffer);	// 원본
        	
        	
        	// 읽기 테스트
        	bis = new BufferedInputStream(socket.getInputStream());
        	byte[] buff = new byte[sendData.length];
        	int read2 = bis.read(buff, 0, sendData.length);
        	if(read2 < 0) {
        		System.out.println("read2 Error : " + read2);
        	}
        	System.out.println("읽은 후 >>>>>>>>>>>>");
        	
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
        	
//        	for(int i = 0 ; i < iTempArray.length; i++) {
//        		System.out.println(iTempArray[i] + "-> iTempArray");
//        		
//        	}
        	
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

//    	    String sTemp = Integer.toString((buff[header.length + 3] & 0xff) + 0x100, 16).substring(1) + 
//    	    			   Integer.toString((buff[header.length + 2] & 0xff) + 0x100, 16).substring(1) + 
//    	    			   Integer.toString((buff[header.length + 1] & 0xff) + 0x100, 16).substring(1) + 
//    	    			   Integer.toString((buff[header.length] & 0xff) + 0x100, 16).substring(1);
//    	    
//    	    struct1.setCondition1(Integer.parseInt(sTemp, 16));
//    	    
//    	    sTemp = Integer.toString((buff[header.length + 7] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 6] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 5] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 4] & 0xff) + 0x100, 16).substring(1);
//    	    
//    	    struct1.setCondition3(Integer.parseInt(sTemp, 16));
//    	    
//    	    sTemp = Integer.toString((buff[header.length + 11] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 10] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 9] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 8] & 0xff) + 0x100, 16).substring(1);
//    	    
//    	    struct1.setTargetValue(Integer.parseInt(sTemp, 16));
//    	    param1.setPump3(struct1);
//    	    System.out.println("Parameters1 : " + param1.toString());
//    	    
//    	    
//    	    struct1 = new IgniteStruct();
//    	    sTemp = Integer.toString((buff[header.length + 15] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 14] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 13] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 12] & 0xff) + 0x100, 16).substring(1);
//	    
//		    struct1.setCondition1(Integer.parseInt(sTemp, 16));
//		    
//		    sTemp = Integer.toString((buff[header.length + 19] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 18] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 17] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 16] & 0xff) + 0x100, 16).substring(1);
//		    
//		    struct1.setCondition3(Integer.parseInt(sTemp, 16));
//		    
//		    sTemp = Integer.toString((buff[header.length + 23] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 22] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 21] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 20] & 0xff) + 0x100, 16).substring(1);
//		    
//		    struct1.setTargetValue(Integer.parseInt(sTemp, 16));
//    	    param1.setPump2(struct1);
//    	    System.out.println("Parameters2 : " + param1.toString());
//    	    
//    	    struct1 = new IgniteStruct();
//    	    sTemp = Integer.toString((buff[header.length + 27] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 26] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 25] & 0xff) + 0x100, 16).substring(1) + 
//	    			   Integer.toString((buff[header.length + 24] & 0xff) + 0x100, 16).substring(1);
//	    
//		    struct1.setCondition1(Integer.parseInt(sTemp, 16));
//		    
//		    sTemp = Integer.toString((buff[header.length + 31] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 30] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 29] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 28] & 0xff) + 0x100, 16).substring(1);
//		    
//		    struct1.setCondition3(Integer.parseInt(sTemp, 16));
//		    
//		    sTemp = Integer.toString((buff[header.length + 35] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 34] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 33] & 0xff) + 0x100, 16).substring(1) + 
//	 			   Integer.toString((buff[header.length + 32] & 0xff) + 0x100, 16).substring(1);
//    	    param1.setSnh(struct1);
//    	    
//    	    System.out.println("Parameters3 : " + param1.toString());
        	
    	    
    	    
        	
        	
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	dos.close();
        	dis.close();
        	socket.close();
        	System.out.println("\n소켓 연결 종료");
        }		
	}
	
	// utils
	public static int byteArrayToInt(byte[] b, int lengthDiv){
        int byteInt = 0;
        if (lengthDiv==2){
            byteInt = ((b[1] & 0xFF) << 8) | (b[0] & 0xFF);
        }else if (lengthDiv==4){
            byteInt = b[0] & 0xFF |
                     (b[1] & 0xFF) << 8 |
                     (b[2] & 0xFF) << 16 |
                     (b[3] & 0xFF) << 24;
        }
        return byteInt;
    }
	
	public static int getBigEndian(byte[] v) {
		
	    int[] array = new int[4];
	    for (int i = 0; i < 4; i++) array[i] = (int) (v[3 - i] & 0xFF);
	    return ((array[0] << 24) + (array[1] << 16) + (array[2] << 8) + (array[3] << 0));
	}
	
}
