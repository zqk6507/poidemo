package com.zqk.poidemo.pojo;

/**
 * @author zhengqiku
 * @date 2021/9/7
 */

public class FundWeeklyInfo {

    private String code;
    private String showNamePre;
    private String showName;
    private String showNameSuf;

    private String estinateReturn;

    public FundWeeklyInfo(String code, String showNamePre, String showName, String showNameSuf, String estinateReturn) {
        this.code = code;
        this.showNamePre = showNamePre;
        this.showName = showName;
        this.showNameSuf = showNameSuf;
        this.estinateReturn = estinateReturn;
    }

    public FundWeeklyInfo() {
    }

    @Override
    public String toString() {
        return "showName:" + showNamePre + showName + showNameSuf;
    }

    public String getShowNamePre() {
        return showNamePre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShowNamePre(String showNamePre) {
        this.showNamePre = showNamePre;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowNameSuf() {
        return showNameSuf;
    }

    public void setShowNameSuf(String showNameSuf) {
        this.showNameSuf = showNameSuf;
    }

    public String getEstinateReturn() {
        return estinateReturn;
    }

    public void setEstinateReturn(String estinateReturn) {
        this.estinateReturn = estinateReturn;
    }
}