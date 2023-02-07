package com.jeelp.platform.common.logging.service;


import com.jeelp.platform.common.logging.entity.LogInfo;

/**
 * @Title 日志信息服务接口
 * @<Description 	提供日志操作的基础服务接口
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
public interface SaveLogService {

	/**
	 * 保存日志接口
	 * @param log
	 */
	void save(LogInfo log);

}
