package com.jeelp.platform.modules.bm01login.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeelp.platform.common.utils.SpringContextHolder;
import com.jeelp.platform.modules.bm01login.security.TokenProvider;

public class UserinfoUtils {
	
	public static final String USER_ID_KEY = "userid";
	
	public static final String USER_NAME_KEY = "username";
	
	public static final String ROLES_KEY = "roles";
	
	public static Map<String,Object> getUserinfo(){
	    try {
            TokenProvider tokenProvider = SpringContextHolder.getBean(TokenProvider.class);
            return tokenProvider.getUserinfo();
        }catch (Exception e){

        }
		return new HashMap<>();
	}
	
	public static String getUserId(){
		return (String) getUserinfo().get(USER_ID_KEY);
	}
	
	public static String getUserName(){
		return (String) getUserinfo().get(USER_NAME_KEY);
	}
	
	
	public static List<String> getRoles(){
		return (List<String>) getUserinfo().get(ROLES_KEY);
	}

}
