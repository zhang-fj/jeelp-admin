/**
 * @Title 用户登录授权管理控制器类
 * @<Description  JEELP用户登录授权管理控制器类，完成登录、登出、用户信息获取等业务
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.platform.modules.bm01login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeelp.platform.common.exception.BadRequestException;
import com.jeelp.platform.common.redis.RedisUtils;
import com.jeelp.platform.modules.bm01login.config.bean.SecurityProperties;
import com.jeelp.platform.modules.bm01login.security.TokenProvider;
import com.jeelp.platform.modules.bm01login.service.OnlineUserService;
import com.jeelp.platform.modules.bm01login.utils.CaptchaUtils;
import com.jeelp.platform.modules.bm01login.viewobject.AuthUser;
import com.jeelp.platform.modules.bm02priv.entity.UserEntity;
import com.jeelp.platform.modules.bm02priv.service.UserService;
import com.jeelp.platform.modules.bm03sys.service.SysParamService;
import com.wf.captcha.base.Captcha;

import cn.hutool.core.util.IdUtil;

/**
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final SysParamService paramService;

    public AuthorizationController(SecurityProperties properties, RedisUtils redisUtils, OnlineUserService onlineUserService, TokenProvider tokenProvider,UserService userService,SysParamService paramService) {
        this.properties = properties;
        this.redisUtils = redisUtils;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.paramService = paramService;
    }

//    @Log("登录系统")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) throws Exception {
        // 获取密码
        String password = authUser.getPassword();
        // 查询验证码
        String captcha = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(captcha)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCaptcha()) || !authUser.getCaptcha().equalsIgnoreCase(captcha)) {
            throw new BadRequestException("验证码错误");
        }
        
        // 获取登录用户
        UserEntity user = userService.findByUserName(authUser.getUsername());
        
        if( user== null || !StringUtils.isNotBlank(password) || !user.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
        	throw new BadRequestException("用户名或密码不正确！");
        }

        Map<String,Object> userinfo = new HashMap<String,Object>(){
            {
                put("userid",user.getUserId());
                put("username",user.getUserName());
                put("roles",user.getRoles());
            }
        };

        // 生成令牌
        String token = tokenProvider.createToken(user.getUserName(),userinfo);
        // 保存在线信息
        onlineUserService.save(user, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("userinfo",userinfo);
            put("roles",user.getRoles());
        }};
        
        return ResponseEntity.ok(authInfo);
    }

    @GetMapping(value = "/info")
    public ResponseEntity<Map<String,Object>> info() {
        return ResponseEntity.ok(tokenProvider.getUserinfo());
    }

    //生成验证码，并把UUID和对应图片的数值放到redis中，比较的时候根据UUID和captchaValue判断是否输入正确
    @GetMapping(value = "/captcha")
    public ResponseEntity<Object> captcha() {
        //从系统参数表里取宽、高、长
        String param0001=paramService.getParamValue("0001");
        String param0002=paramService.getParamValue("0002");
        String param0003=paramService.getParamValue("0003");

        //判断取出的值是不是整数，如果不是，设置为默认值
        int captcheWidth=CaptchaUtils.DEFAULT_WIDTH;
        if (StringUtils.isNumeric(param0001))
            captcheWidth=Integer.valueOf(param0001);

        int captcheHeight=CaptchaUtils.DEFAULT_HEIGHT;
        if (StringUtils.isNumeric(param0002))
            captcheHeight=Integer.valueOf(param0002);

        int captcheLenth=CaptchaUtils.DEFAULT_LENTH;
        if (StringUtils.isNumeric(param0003))
        	captcheLenth=Integer.valueOf(param0003);

        // 获取运算的结果
        Captcha captcha = CaptchaUtils.getCaptchaCode(
        		CaptchaUtils.SPEC,captcheWidth, captcheHeight,captcheLenth);
        
        // 设置redis缓存验证码key值
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        
        // 获取验证码真实值
        String captchaValue = captcha.text();
        
        // 缓存redis
        redisUtils.set(uuid, captchaValue,Long.valueOf(paramService.getParamValue("0004")), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

//    @Log("退出系统")
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout() {
        onlineUserService.logout(tokenProvider.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
