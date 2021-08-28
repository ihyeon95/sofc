package com.stx.sofc.dashboard.vo;

public class guestVo {
    private String iUserNum;
    private String sUserName;
    private String sUserId;
    private String sUserPassword;
    private String bUserAuth;
    private String sCityName;
    private String sAreaName;
    private String sSiteName;
    private int iCityNum;
    private int iAreaNum;
    private int iSiteNum;

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
    public String getsCityName() {
        return sCityName;
    }

    public void setsCityName(String sCityName) {
        this.sCityName = sCityName;
    }

    public String getsAreaName() {
        return sAreaName;
    }

    public void setsAreaName(String sAreaName) {
        this.sAreaName = sAreaName;
    }

    public String getsSiteName() {
        return sSiteName;
    }

    public void setsSiteName(String sSiteName) {
        this.sSiteName = sSiteName;
    }

    public int getiCityNum() {
        return iCityNum;
    }

    public void setiCityNum(int iCityNum) {
        this.iCityNum = iCityNum;
    }

    public int getiAreaNum() {
        return iAreaNum;
    }

    public void setiAreaNum(int iAreaNum) {
        this.iAreaNum = iAreaNum;
    }

    public int getiSiteNum() {
        return iSiteNum;
    }

    public void setiSiteNum(int iSiteNum) {
        this.iSiteNum = iSiteNum;
    }


    @Override
    public String toString() {
        return "LoginVO [iUserNum=" + iUserNum + ", sUserName=" + sUserName + ", sUserId=" + sUserId
                + ", sUserPassword=" + sUserPassword + ", bUserAuth=" + bUserAuth + ", sCityName=" + sCityName + ", sAreaName=" + sAreaName + ", sSiteName=" + sSiteName + "]";
    }
}
