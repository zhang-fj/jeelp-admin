package com.jeelp.platform.modules.bm03sys.service;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm03sys.entity.SysSequence;

/**
* @Title:                SysSequenceService
* @Description: TODO    序列号管理
* @author                
* @date                2022-01-25
* @version            V1.0
*/
public interface SysSequenceService extends BaseService<SysSequence>{

	/**
	 * 根据序列号同规则序列的组号生成序列号	
	 * @param groupId 	同规则序列的组号
	 * @param length 	序列号长度
	 * @param year 	 	年度
	 * @param month		月份
	 * @param space		间隔符
	 * @return
	 */
	String getNextSequence(String groupId,int length, String year, String month, String space);

}