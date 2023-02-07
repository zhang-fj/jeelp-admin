package com.jeelp.platform.modules.bm02priv.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.modules.bm02priv.entity.RoleEntity;
import com.jeelp.platform.modules.bm02priv.entity.RoleFuncEntity;

/**
* @Title:              Role
* @Description: TODO   角色管理管理
* @author                
* @date                2021-10-07
* @version             V1.0
*/
public interface RoleMapper extends BaseMapper<RoleEntity>{
	
	/**
	 * 根据角色ID删除角色功能授权
	 * @param param
	 */
	void deleteRoleFuncs(Map<String, Object> param);

	/**
	 * 批量新增角色功能授权
	 * @param params
	 */
	void insertRoleFuncs(List<RoleFuncEntity> roleFuncs);

	/**
	 * 获取角色授权功能Id数组
	 * @param roleId
	 * @return
	 */
	List<String> getAuthFuncIds(@Param("roleId") String roleId);
	
}