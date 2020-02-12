package com.example.demo.model;

/**
 * Created by zhengb
 */
public class LoginEvent extends Event {

    public final static String MOBILE = "mobile";

    public final static String OPERATEIP = "operateIp";

    private String mobile;

    private String operateIp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

}
