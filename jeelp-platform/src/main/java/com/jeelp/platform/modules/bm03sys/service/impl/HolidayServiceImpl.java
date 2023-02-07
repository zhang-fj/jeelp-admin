package com.jeelp.platform.modules.bm03sys.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.common.utils.DateUtils;
import com.jeelp.platform.modules.bm03sys.entity.Holiday;
import com.jeelp.platform.modules.bm03sys.mapper.HolidayMapper;
import com.jeelp.platform.modules.bm03sys.service.HolidayService;
import com.jeelp.platform.modules.bm03sys.viewobject.HolidayModel;

/**
* @Title:              HolidayServiceImpl
* @Description: TODO   假期规则管理
* @author              
* @date                2022-01-15
* @version             V1.0
*/
@Service
public class HolidayServiceImpl extends BaseServiceImpl<Holiday> implements HolidayService{

	private final HolidayMapper mapper;
	
	public HolidayServiceImpl(HolidayMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<Holiday> getMapper(){
		return mapper;
	}

	@Override
	public String initHoliday(String startYear, String endYear) {
		
		
		// 获取开始时间
		Date startDate = DateUtils.parseDate(startYear+"-01-01",5);
		// 获取结束时间
		Date endDate = DateUtils.parseDate(endYear+"-12-31",5);
		// 清除假期规则
		
		Map<String, Object> param= new HashMap<>();
		param.put("startDate", startYear+"-01-01");
		param.put("endDate", endYear+"-12-31");
		mapper.deleteByParams(param);
		
		Holiday holiday = new Holiday();
		while (startDate.compareTo(endDate)<=0) {
			holiday.setSort(null);
			// 获取日历对象
			Calendar cal = Calendar.getInstance();
			// 设置开始时间
			cal.setTime(startDate);
			
			// 判断当前日期是否为周末
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				System.out.println(DateUtils.toDateStrByFormat(cal, "yyyy-MM-dd"));
				holiday.setId(null);
				holiday.setExtInfo1("休");
				holiday.setDaysets(startDate);
				holiday.setSort("0");
			}
			
			// 
			if(cal.get(Calendar.DAY_OF_MONTH) ==1){ //判断节假日类型
	           switch (cal.get(Calendar.MONTH)){
	               case 0:
	                   holiday.setDaysets(startDate);
	                   holiday.setExtInfo1("元旦");
	                   holiday.setSort("0");
	                   break;
	               case 4:
	            	   holiday.setDaysets(startDate);
	                   holiday.setExtInfo1("劳动节");
	                   holiday.setSort("0");
	                   break;
	               case 9:
	            	   holiday.setDaysets(startDate);
	                   holiday.setExtInfo1("国庆节");
	                   holiday.setSort("0");
	                   break;
	           }
	           
	        }
			if(StringUtils.isNotBlank(holiday.getSort())){
				insert(holiday);
			}
			//当前日期加1
			cal.add(Calendar.DATE,1);
			startDate=cal.getTime();
		}
		return "初始化成功";
	}
	
	@Override
	public String setHoliday(String startDate, String endDate,String sort,String holidayType) {
		
		
		// 获取开始时间
		Date startDt = DateUtils.parseDate(startDate,5);
		// 获取结束时间
		Date endDt = DateUtils.parseDate(endDate,5);
		// 清除假期规则
		
		Map<String, Object> param= new HashMap<>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		mapper.deleteByParams(param);
		if(holidayType != null ){
			Holiday holiday = new Holiday();
			while (startDt.compareTo(endDt)<=0) {
				holiday.setSort(null);
				// 获取日历对象
				Calendar cal = Calendar.getInstance();
				// 设置开始时间
				cal.setTime(startDt);
				
				holiday.setId(null);
				holiday.setExtInfo1(holidayType);
				holiday.setDaysets(startDt);
				holiday.setSort(sort);

				if(StringUtils.isNotBlank(holiday.getSort())){
					insert(holiday);
				}
				//当前日期加1
				cal.add(Calendar.DATE,1);
				startDt=cal.getTime();
			}
		}
		return "设置成功";
	}

	@Override
	public List<HolidayModel> getMonthHoliday(String year, String month) {
		List<HolidayModel> hms=new ArrayList<>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("year", year);
		params.put("month",month);
		List<Holiday> holidays=mapper.findAll(params);
		if(holidays!=null){
			for(Holiday h:holidays){
				hms.add(
					new HolidayModel("0".equals(h.getSort())?"休":"班", 
							DateUtils.toDateStrByFormat(h.getDaysets(), "yyyy-MM-dd"), 
							DateUtils.toDateStrByFormat(h.getDaysets(), "yyyy-MM-dd"), 
							"0".equals(h.getSort())?"#f7656b":"#82848a")
				);
			}
		}
		return hms;
	}
	
	@Override
	public Map<String,Object> computeWorkday(String startDate, String endDate) {
		
		Map<String,Object> result = new HashMap<>();
		Date startDt = DateUtils.parseDate(startDate, 5);
		Date endDt = DateUtils.parseDate(endDate, 5);
		
		// 获取总天数
		int days = DateUtils.calcDays(endDt,startDt)+1;
		result.put("days", days);
		
		// 获取假期天数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int holidays = mapper.getHolidays(params);
		result.put("holidays", holidays);
		
		// 计算工作天数
		result.put("workdays", days-holidays);
		
		
		return result;
	}
	
}
