package com.jeelp.platform.modules.bm02priv.service;

import java.util.Map;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm02priv.entity.UserEntity;
import com.jeelp.platform.modules.bm02priv.viewobject.EditPassword;

/**
* @Title:              UserService
* @Description: TODO   用户管理管理
* @author                
* @date                2021-09-22
* @version             V1.0
*/
public interface UserService extends BaseService<UserEntity>{

	UserEntity findByUserName(String username);

	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	Map<String,Object> getUserinfo(String id);

	/**
	 * 修改用户密集
	 * @param editPassword
	 * @return
	 */
	void editPassword(EditPassword editPassword);
	
}