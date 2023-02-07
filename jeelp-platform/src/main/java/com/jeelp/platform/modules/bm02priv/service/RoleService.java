package com.jeelp.platform.modules.bm02priv.service;

import java.util.List;
import java.util.Map;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm02priv.entity.RoleEntity;

/**
* @Title:                RoleService
* @Description: TODO   角色管理管理
* @author                
* @date                2021-10-07
* @version            V1.0
*/
public interface RoleService extends BaseService<RoleEntity>{
	
	/**
	 * 角色功能授权
	 * @param params
	 */
	void auth(Map<String, Object> params);

	
	/**
	 * 获取角色授权功能id数组
	 * @param roleId
	 * @return
	 */
	List<String> getAuthFuncIds(String roleId);

}