package com.jeelp.platform.modules.bm03sys.service;

import java.util.List;
import java.util.Map;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm03sys.entity.Holiday;
import com.jeelp.platform.modules.bm03sys.viewobject.HolidayModel;

/**
* @Title:                HolidayService
* @Description: TODO   假期规则管理
* @author                
* @date                2022-01-15
* @version            V1.0
*/
public interface HolidayService extends BaseService<Holiday>{

	/**
	 * 初始化假期
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	String initHoliday(String startYear, String endYear);

	/**
	 * 获取某月份假期规则
	 * @param year
	 * @param month
	 * @return
	 */
	List<HolidayModel> getMonthHoliday(String year, String month);

	/**
	 * 设置假期
	 * @param startDate
	 * @param endDate
	 * @param sort
	 * @param holidayType
	 * @return
	 */
	String setHoliday(String startDate, String endDate,String sort,String holidayType);

	/**
	 * 工作日计算
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String, Object> computeWorkday(String startDate, String endDate);

}