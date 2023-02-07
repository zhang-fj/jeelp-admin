package com.jeelp.platform.common.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @Title 		     错误信息返回实体
 * @<Description  返回错误信息
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
public class Error {

	
    /**
     * 状态
     */
    private Integer status = 400;
    
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    /**
     * 消息
     */
    private String message;

    private Error() {
        timestamp = LocalDateTime.now();
    }

    public static Error error(String message) {
    	Error error = new Error();
    	error.setMessage(message);
        return error;
    }

    public static Error error(Integer status, String message) {
    	Error error = new Error();
    	error.setStatus(status);
    	error.setMessage(message);
        return error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


