package com.jeelp.platform.modules.bm03sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeelp.platform.common.mybatis.domain.Entity;

import java.util.Date;

/**
* @Title:                SysLogs
* @Description: TODO   系统日志管理
* @author                
* @date                2021-12-14
* @version            V1.0
*/
public class SysLogs extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* ID
	*/
	private String logId;

	/**
	* 
	*/
	private String description;

	/**
	* 
	*/
	private String logType;

	/**
	* 
	*/
	private String method;

	/**
	* 
	*/
	private String params;

	/**
	* 
	*/
	private String requestIp;

	/**
	* 
	*/
	private Long time;

	/**
	* 
	*/
	private String username;

	/**
	* 
	*/
	private String address;

	/**
	* 
	*/
	private String browser;

	/**
	* 
	*/
	private String exceptionDetail;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.logId=this.id;
	}

    public String getLogId() {
        return logId;
    }

    public String getDescription() {
        return description;
    }

    public String getLogType() {
        return logType;
    }

    public String getMethod() {
        return method;
    }

    public String getParams() {
        return params;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public Long getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getBrowser() {
        return browser;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
    
}