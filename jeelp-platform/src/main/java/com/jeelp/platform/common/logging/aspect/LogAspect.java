package com.jeelp.platform.common.logging.aspect;

import cn.hutool.json.JSONUtil;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.common.logging.entity.LogInfo;
import com.jeelp.platform.common.logging.service.SaveLogService;
import com.jeelp.platform.common.utils.IpUtils;
import com.jeelp.platform.common.utils.ThrowableUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title 			日志切面
 * @<Description  	以面向切面编程的方式记录日志，需要配合@Log注解使用
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
@Component
@Aspect
public class LogAspect {

    /**
     * 日志操作服务接口
     */
    private final SaveLogService saveLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(SaveLogService saveLogService) {
        this.saveLogService = saveLogService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.jeelp.platform.common.logging.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        // 获取当前系统时间
        currentTime.set(System.currentTimeMillis());
        // 执行目标方法
        result = joinPoint.proceed();
        // 计算目标方法执行时间
        LogInfo log = new LogInfo("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        // 获取请求信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 保存日志
        save(getUsername(), IpUtils.getBrowser(request), IpUtils.getIp(request), joinPoint, log);
        return result;
    }

    /**
     * 保存日志信息
     * @param username
     * @param browser
     * @param ip
     * @param joinPoint
     * @param log
     */
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, LogInfo log) {
    	
    	// 获取切入点方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = signature.getMethod();
        // 获取切入点上的@Log注解
        Log aopLog = method.getAnnotation(Log.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        // 从@Log注解上获取方法描述描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        assert log != null;
        // 设置ip
        log.setRequestIp(ip);
        // 设置ip地址
        log.setAddress(IpUtils.getCityInfo(log.getRequestIp()));
        // 设置方法名
        log.setMethod(methodName);
        // 设置登录用户
        log.setUsername(username);
        // 设置方法参数
        log.setParams(getParameter(method, joinPoint.getArgs()));
        // 设置浏览器
        log.setBrowser(browser);
        // 保存日志
        saveLogService.save(log);
    }


    /**
     * 根据方法和传入的参数获取请求参数
     * @param method
     * @param args
     * @return
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }

    /**
     * 配置异常通知
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LogInfo log = new LogInfo("ERROR", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        save(getUsername(), IpUtils.getBrowser(request), IpUtils.getIp(request), (ProceedingJoinPoint) joinPoint, log);
    }

    /**
     * 获取当前登录用户名称
     * @return
     */
    public String getUsername() {
        try {
            return UserinfoUtils.getUserName();
        } catch (Exception e) {
            return "";
        }
    }

}
