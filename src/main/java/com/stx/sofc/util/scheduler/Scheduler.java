package com.stx.sofc.util.scheduler;


import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stx.sofc.dashboard.service.DashBoardCityService;
import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.util.mail.MailMessageDef;
import com.stx.sofc.util.mail.MailSendUtil;

@Component
public class Scheduler {
	
	@Inject
	private DashBoardCityService service;
	
	@Autowired
	private MailSendUtil mailSendUtil;
	

	// * 을 입력할경우 모두(항상)으로 설정함.
	//                 초  분  시  일  월  요일
	@Scheduled(cron = "0 0/1 * * * *")
	public void autoUpdate() throws Exception {
		//10초에 한번씩 호출
		
		List<DashboardVO> list = service.eventAlarmList();

		if(list.size() >= 1) {
			
			List<EmailVO> emailList = service.emailList();
			
			for(int i = 0 ; i < list.size(); i++) {
				
				String sMessage = "";
				
				DashboardVO systemPath = service.eventSystemPath(list.get(i).getDeviceID());
				int alarmTodayCount = service.eventAlarmToday(list.get(i));

				if(systemPath != null) {
//					sMessage += "설치장소 : 전국 - " + systemPath.getsCityName() + " - " + systemPath.getsAreaName() + " - " + systemPath.getsSiteName();
					sMessage += "도시명 : " + systemPath.getsCityName();
					sMessage += "\n";
					sMessage += "지역명 : " + systemPath.getsAreaName();
					sMessage += "\n";
					sMessage += "설치 사이트 : " + systemPath.getsSiteName();
					sMessage += "\n";
					sMessage += "장비명 : " + systemPath.getsSystemName();
					sMessage += "\n";
					sMessage += "에러 발생 시간 : " + list.get(i).getEventDate();
					sMessage += "\n";
					if(Integer.parseInt(list.get(i).getErrNum()) < 20) {
						sMessage += "에러 발생 번호 : " + list.get(i).getErrNum();
					}else {
						sMessage += "에러 발생 번호 : ";
						
						switch (Integer.parseInt(list.get(i).getErrNum())) {
							case 21:
								sMessage += "1-1 (수동)";
								break;
							case 22:
								sMessage += "1-2 (수동)";
								break;
							case 23:
								sMessage += "1-3 (자동)";
								break;
							case 24:
								sMessage += "1-4 (자동)";
								break;
							case 25:
								sMessage += "1-5 (자동)";
								break;
							case 26:
								sMessage += "1-6 (자동)";
								break;
							case 27:
								sMessage += "1-7 (자동)";
								break;
							case 28:
								sMessage += "1-8 (자동)";
								break;
							case 29:
								sMessage += "1-9 (수동)";
								break;
							case 30:
								sMessage += "1-10 (수동)";
								break;
							case 31:
								sMessage += "1-11 (수동)";
								break;
							case 32:
								sMessage += "1-12 (자동)";
								break;
							case 33:
								sMessage += "1-13 (자동)";
								break;
							case 34:
								sMessage += "1-14 (자동)";
								break;
							case 35:
								sMessage += "1-15 (자동)";
								break;
							case 36:
								sMessage += "1-16 (자동)";
								break;
							case 37:
								sMessage += "2-1 (자동)";
								break;
							case 38:
								sMessage += "2-2 (자동)";
								break;
							case 39:
								sMessage += "2-3 (자동)";
								break;
							case 40:
								sMessage += "2-4 (자동)";
								break;
							case 41:
								sMessage += "2-5 (자동)";
								break;
							case 42:
								sMessage += "2-6 (수동)";
								break;
							case 43:
								sMessage += "2-7 (수동)";
								break;
							case 44:
								sMessage += "2-8 (수동)";
								break;
							default:
								break;
							
						}
						
					}
					
					sMessage += "\n";
					sMessage += "에러 내용 : ";
					
					switch (Integer.parseInt(list.get(i).getErrNum())) {
						case 1:
							sMessage += MailMessageDef.message1;
							break;
						case 2:
							sMessage += MailMessageDef.message2;
							break;
						case 3:
							sMessage += MailMessageDef.message3;
							break;
						case 4:
							sMessage += MailMessageDef.message4;
							break;
						case 5:
							sMessage += MailMessageDef.message5;
							break;
						case 6:
							sMessage += MailMessageDef.message6;
							break;
						case 7:
							sMessage += MailMessageDef.message7;
							break;
						case 8:
							sMessage += MailMessageDef.message8;
							break;
						case 9:
							sMessage += MailMessageDef.message9;
							break;
						case 10:
							sMessage += MailMessageDef.message10;
							break;
						case 11:
							sMessage += MailMessageDef.message11;
							break;
						case 12:
							sMessage += MailMessageDef.message12;
							break;
						case 13:
							sMessage += MailMessageDef.message13;
							break;
						case 14:
							sMessage += MailMessageDef.message14;
							break;
						case 15:
							sMessage += MailMessageDef.message15;
							break;
						case 16:
							sMessage += MailMessageDef.message16;
							break;
						case 18:
							sMessage += MailMessageDef.message18;
							break;
						case 21:
							sMessage += MailMessageDef.message21;
							break;
						case 22:
							sMessage += MailMessageDef.message22;
							break;
						case 23:
							sMessage += MailMessageDef.message23;
							break;
						case 24:
							sMessage += MailMessageDef.message24;
							break;
						case 25:
							sMessage += MailMessageDef.message25;
							break;
						case 26:
							sMessage += MailMessageDef.message26;
							break;
						case 27:
							sMessage += MailMessageDef.message27;
							break;
						case 28:
							sMessage += MailMessageDef.message28;
							break;
						case 29:
							sMessage += MailMessageDef.message29;
							break;
						case 30:
							sMessage += MailMessageDef.message30;
							break;
						case 31:
							sMessage += MailMessageDef.message31;
							break;
						case 32:
							sMessage += MailMessageDef.message32;
							break;
						case 33:
							sMessage += MailMessageDef.message33;
							break;
						case 34:
							sMessage += MailMessageDef.message34;
							break;
						case 35:
							sMessage += MailMessageDef.message35;
							break;
						case 36:
							sMessage += MailMessageDef.message36;
							break;
						case 37:
							sMessage += MailMessageDef.message37;
							break;
						case 38:
							sMessage += MailMessageDef.message38;
							break;
						case 39:
							sMessage += MailMessageDef.message39;
							break;
						case 40:
							sMessage += MailMessageDef.message40;
							break;
						case 41:
							sMessage += MailMessageDef.message41;
							break;
						case 42:
							sMessage += MailMessageDef.message42;
							break;
						case 43:
							sMessage += MailMessageDef.message43;
							break;
						case 44:
							sMessage += MailMessageDef.message44;
							break;
						default:
							break;
					}

					if(alarmTodayCount > 0) {
						for (int j = 0; j < emailList.size(); j++) {

							mailSendUtil.sendMail(emailList.get(j).getsEmail(), emailList.get(j).getsEmail(), "SOFC 에러발생 알림 ( " + systemPath.getsCityName() + " / " + systemPath.getsAreaName() + " / " + systemPath.getsSiteName() + " / " + systemPath.getsSystemName() + " ) " , sMessage);

							Thread.sleep(5000);
							//waiting 3초정도 줘야 함
							//안하면 서버쪽 이슈로 전송 안됨
						}
					}
					service.updateEventAlarm(list.get(i));
				}
			}
		}
	}
}
