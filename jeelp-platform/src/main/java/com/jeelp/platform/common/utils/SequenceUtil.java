package com.jeelp.platform.common.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.jeelp.platform.modules.bm03sys.service.SysSequenceService;

/**
 * 序列号生成工具类
 * @author zhangfujiang
 */
public class SequenceUtil {
	
	
	
	/**
	 * 根据序列号同规则序列的组号生成序列号	
	 * @param groupId 	同规则序列的组号
	 * @param length 	序列号长度
	 * @param year 	 	年度
	 * @param month		月份
	 * @param space		间隔符
	 * @return
	 */
	public static String getNextSequence(String groupId,int length, String year, String month, String space){
		SysSequenceService service = SpringContextHolder.getBean(SysSequenceService.class);
		return service.getNextSequence(groupId, length, year, month, space);
	}

	/**
	 * 根据序列号同规则序列的组号生成无间隔符的年度月份序列号
	 * @param groupId
	 * @param length
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getNextSequence(String groupId,int length, String year, String month){
		String y = year;
		if(!StringUtils.isNotBlank(year)){
			y = DateUtils.toDateStrByFormat(new Date(), "yyyy");
		}
		String m = month;
		if(!StringUtils.isNotBlank(month)){
			m = DateUtils.toDateStrByFormat(new Date(), "MM");
		}
		return getNextSequence(groupId, length, y, m, null);
	}
	
	/**
	 * 根据序列号同规则序列的组号生成带有(-)间隔符的年度月份序列号
	 * @param groupId
	 * @param length
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getNextSpaceSequence(String groupId,int length, String year, String month){
		String y = year;
		if(!StringUtils.isNotBlank(year)){
			y = DateUtils.toDateStrByFormat(new Date(), "yyyy");
		}
		String m = month;
		if(!StringUtils.isNotBlank(month)){
			m = DateUtils.toDateStrByFormat(new Date(), "MM");
		}
		return getNextSequence(groupId, length, y, m, "-");
	}
	
	/**
	 * 根据序列号同规则序列的组号生成无间隔符的年度序列号
	 * @param groupId
	 * @param length
	 * @param year
	 * @return
	 */
	public static String getNextSequence(String groupId,int length, String year){
		String y = year;
		if(!StringUtils.isNotBlank(year)){
			y = DateUtils.toDateStrByFormat(new Date(), "yyyy");
		}
		return getNextSequence(groupId, length, y, null, null);
	}
	
	/**
	 * 根据序列号同规则序列的组号生成生成带有(-)间隔符的年度序列号
	 * @param groupId
	 * @param length
	 * @param year
	 * @return
	 */
	public static String getNextSpaceSequence(String groupId,int length, String year){
		String y = year;
		if(!StringUtils.isNotBlank(year)){
			y = DateUtils.toDateStrByFormat(new Date(), "yyyy");
		}
		return getNextSequence(groupId, length, y, null, "-");
	}
	
	/**
	 * 根据序列号同规则序列的组号生成无间隔符的序列号
	 * @param groupId
	 * @param length
	 * @return
	 */
	public static String getNextSequence(String groupId,int length){
		return getNextSequence(groupId, length, null, null, null);
	}
	
	/**
	 * 根据序列号同规则序列的组号生成带有(-)间隔符的序列号
	 * @param groupId
	 * @param length
	 * @return
	 */
	public static String getNextSpaceSequence(String groupId,int length){
		return getNextSequence(groupId, length, null, null, null);
	}
}
