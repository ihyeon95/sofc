package com.stx.sofc.login.vo;

public class LoginVO {

	private String iUserNum;
	private String sUserName;
	private String sUserId;
	private String sUserPassword;
	private String bUserAuth;
	
	public String getiUserNum() {
		return iUserNum;
	}
	public void setiUserNum(String iUserNum) {
		this.iUserNum = iUserNum;
	}
	public String getsUserName() {
		return sUserName;
	}
	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}
	public String getsUserId() {
		return sUserId;
	}
	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	public String getsUserPassword() {
		return sUserPassword;
	}
	public void setsUserPassword(String sUserPassword) {
		this.sUserPassword = sUserPassword;
	}
	public String getbUserAuth() {
		return bUserAuth;
	}
	public void setbUserAuth(String bUserAuth) {
		this.bUserAuth = bUserAuth;
	}
	@Override
	public String toString() {
		return "LoginVO [iUserNum=" + iUserNum + ", sUserName=" + sUserName + ", sUserId=" + sUserId
				+ ", sUserPassword=" + sUserPassword + ", bUserAuth=" + bUserAuth + "]";
	}
	
	
	
}
