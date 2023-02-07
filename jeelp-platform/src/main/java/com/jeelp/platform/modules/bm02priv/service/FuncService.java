package com.jeelp.platform.modules.bm02priv.service;

import java.util.List;
import java.util.Map;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm02priv.entity.FuncEntity;
import com.jeelp.platform.modules.bm02priv.viewobject.MenuVo;

/**
* @Title:                FuncService
* @Description: TODO   菜单管理
* @author                
* @date                2021-09-22
* @version            V1.0
*/
public interface FuncService extends BaseService<FuncEntity>{

	/**
	 * 获取功能菜单树
	 * @param params
	 * @return
	 */
	List<FuncEntity> tree(Map<String, Object> params);

	/**
	 * 获取首页菜单
	 * @param params
	 * @return
	 */
	List<MenuVo> getMenus(Map<String, Object> params);

}