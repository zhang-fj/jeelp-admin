package com.jeelp.platform.modules.bm03sys.service;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm03sys.entity.SysParam;

/**
* @Title:                SysParamService
* @Description: TODO   系统参数管理
* @author                
* @date                2021-12-14
* @version            V1.0
*/
public interface SysParamService extends BaseService<SysParam>{

	/**
	 * 缓存系统参数
	 */
	void reloadRedis();

	
	/**
	 * 获取系统参数
	 * @param paramId
	 * @return
	 */
	String getParamValue(String paramId);

}