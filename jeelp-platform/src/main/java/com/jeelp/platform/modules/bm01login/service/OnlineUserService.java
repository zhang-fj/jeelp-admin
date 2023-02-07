package com.jeelp.platform.modules.bm01login.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jeelp.platform.common.redis.PageUtil;
import com.jeelp.platform.common.redis.RedisUtils;
import com.jeelp.platform.common.utils.EncryptUtils;
import com.jeelp.platform.common.utils.IpUtils;
import com.jeelp.platform.modules.bm01login.config.bean.SecurityProperties;
import com.jeelp.platform.modules.bm01login.viewobject.OnlineUser;
import com.jeelp.platform.modules.bm02priv.entity.UserEntity;

/**
 * 在线用户service
 */
@Service
public class OnlineUserService {

    private static final Logger log = LoggerFactory.getLogger(OnlineUserService.class.getName());

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     *
     * @param jwtUser 
     * @param token      
     * @param request    
     */
    public void save(UserEntity userEntity, String token, HttpServletRequest request) {
        String ip = IpUtils.getIp(request);
        String browser = IpUtils.getBrowser(request);
        String address = IpUtils.getCityInfo(ip);
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(userEntity.getUserName(), userEntity.getEmpName(), browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUser, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据
     *
     * @param filter   
     * @param pageable 
     * @return 
     */
    public Map<String, Object> getAll(String filter, Pageable pageable) {
        List<OnlineUser> onlineUsers = getAll(filter);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineUsers),
                onlineUsers.size()
        );
    }

    /**
     * 查询全部数据，不分页
     * @param filter 
     * @return 
     */
    public List<OnlineUser> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUser);
                }
            } else {
                onlineUsers.add(onlineUser);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }
    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }
    
    /**
     * 查询用户
     * @param key 
     * @return 
     */
    public OnlineUser getOne(String key) {
        return (OnlineUser) redisUtils.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName 用户名
     */
//    public void checkLoginOnUser(String userName, String igoreToken) {
//        List<OnlineUser> onlineUsers = getAll(userName);
//        if (onlineUsers == null || onlineUsers.isEmpty()) {
//            return;
//        }
//        for (OnlineUser onlineUser : onlineUsers) {
//            if (onlineUser.getUserName().equals(userName)) {
//                try {
//                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
//                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
//                        this.logout(token);
//                    } else if (StringUtils.isBlank(igoreToken)) {
//                        this.logout(token);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    /**
     * 根据用户名强退用户
     * @param username
     */
    @Async
    public void kickOutForUsername(String username) throws Exception {
        List<OnlineUser> onlineUsers = getAll(username);
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(username)) {
                String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                logout(token);
            }
        }
    }
}
