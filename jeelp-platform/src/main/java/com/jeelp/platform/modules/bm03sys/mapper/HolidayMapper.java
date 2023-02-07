package com.jeelp.platform.modules.bm03sys.mapper;

import java.util.Map;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.modules.bm03sys.entity.Holiday;

/**
* @Title:                Holiday
* @Description: TODO   假期规则管理
* @author                
* @date                2022-01-15
* @version            V1.0
*/
public interface HolidayMapper extends BaseMapper<Holiday>{

	/**
	 * 获取某个时间段内的假期天数
	 * @param params
	 * @return
	 */
	int getHolidays(Map<String, Object> params);
}