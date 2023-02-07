/**
 * @Title JEELP轻量级开发平台-用户管理类mapper
 * @<Description  JEELP轻量级开发平台-用户管理类mapper
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.platform.modules.bm02priv.mapper;

import java.util.List;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.modules.bm02priv.entity.UserEntity;

public interface UserMapper extends BaseMapper<UserEntity>{

	/**
	 * 根据userId获取用户角色数组
	 * @param userId
	 * @return
	 */
	List<String> getRolesByUserId(String userId);
}