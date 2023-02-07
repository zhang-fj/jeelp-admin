package com.jeelp.platform.modules.bm01login.viewobject;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 在线用户
 */
public class OnlineUser implements Serializable{

	private static final long serialVersionUID = 1L;

    public OnlineUser() {}

    public OnlineUser(String username, String empName, String browser, String ip, String address, String key, Date loginTime) {
        this.userName=username;
        this.nickName = empName;
        this.browser = browser;
        this.ip = ip;
        this.address = address;
        this.key = key;
        this.loginTime = loginTime;
    }

	/**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date loginTime;

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBrowser() {
        return browser;
    }

    public String getIp() {
        return ip;
    }

    public String getAddress() {
        return address;
    }

    public String getKey() {
        return key;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}
