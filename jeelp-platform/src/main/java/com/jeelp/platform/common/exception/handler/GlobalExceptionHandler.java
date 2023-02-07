package com.jeelp.platform.common.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jeelp.platform.common.exception.BadRequestException;
import com.jeelp.platform.common.utils.ThrowableUtil;

/**
 * @Title 			spring mvc 统一异常处理
 * @<Description  	统一处理spring mvc 产生的异常信息
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    /**
     * 处理所有不可知的异常
     * @param e	异常信息
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Error> handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(Error.error(e.getMessage()));
    }
    
    /**
     * 处理请求异常
     * @param e	请求异常
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(Error.error(e.getStatus(), e.getMessage()));
    }
    
    /**
     * 统一返回错误信息
     * @param error
     * @return
     */
    private ResponseEntity<Error> buildResponseEntity(Error error) {
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatus()));
    }

    public static Logger getLog() {
        return log;
    }
}
