package com.stx.sofc.util.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.stx.sofc.HomeController;

/**
* <pre>
* 1. 패키지명 : com.stx.sofc.util.mail
* 2. 타입명 : MailSendUtil.java
* 3. 작성일 : 2020. 4. 3
* 4. 작성자 : jeongeun
* 5. 설명 : (공통기능) 메일전송기능 컴포넌트 
* </pre>
*/
@Component
public class MailSendUtil {

	@Autowired 
	private JavaMailSenderImpl mailSender;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	* <pre>
	* 1. 메소드명 : sendMail
	* 2. 작성일 : 2020. 4. 3
	* 3. 작성자 : jeongeun
	* 4. 설명 : 단일 메일 전송 기능
	* </pre>
	* @param from, to, subject, text
	* @return Map<String, Object>
	*/
	public Map<String, Object> sendMail(String from, String to, String subject, String text) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			
			// 1. 메일 정보 세팅(발신자, 수신자, 제목, 내용)
			message.setFrom(new InternetAddress(String.valueOf(from)));
			message.setRecipient(RecipientType.TO, new InternetAddress(String.valueOf(to)));
			message.setSubject(subject); 
			message.setText(text); 		
			
			// 2. 메일 발송
			mailSender.send(message);
			
			// 3. 결과 세팅(성공)
			returnMap.put("returnCode", "ok");
		} catch (Exception e) {
			logger.error(e.toString());
			// 결과 세팅(실패)
			returnMap.put("returnCode", "fail");
		}
		
		return returnMap;
    }
	

	/**
	* <pre>
	* 1. 메소드명 : sendMails
	* 2. 작성일 : 2020. 4. 3
	* 3. 작성자 : jeongeun
	* 4. 설명 : 다중 메일 전송 기능
	* </pre>
	* @param from, toArray, subject, text
	* @return Map<String, Object>
	*/
	public Map<String, Object> sendMails(String from, InternetAddress[] toArray, String subject, String text) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			
			// 1. 메일 정보 세팅(발신자, 수신자 리스트, 제목, 내용)
			message.setFrom(new InternetAddress(String.valueOf(from)));
			message.setRecipients(RecipientType.TO, toArray);
			message.setSubject(subject); 
			message.setText(text); 		
			
			// 2. 메일 발송
			mailSender.send(message);
			
			// 3. 결과 세팅(성공)
			returnMap.put("returnCode", "ok");
		} catch (Exception e) {
			logger.error(e.toString());
			// 결과 세팅(실패)
			returnMap.put("returnCode", "fail");
		}
		
		return returnMap;
    }
}
