package com.jeelp.platform.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 编码生成工具
 * @author zhangfujiang
 */
public class GenCodeUtil {

	/**
	 * 根据前缀及父级编码生成新编码
	 * @param prefix		编码前缀
	 * @param parentCode	父级编码
	 * @param maxCode		当前父级编码下最大子编码
	 * @param len			每一级编码长度
	 * @return
	 */
	public static String genCode(String prefix,String parentCode,String maxCode,Integer len){
    	String pcode = parentCode.replaceAll("(.)\\1+", "");
    	String mCode = "0";
    	if(maxCode != null){
    		mCode = maxCode.replace(pcode,"").replace(prefix, "").replaceAll("(.)\\1+", "");
    		if(StringUtils.isAllBlank(mCode)){
    			mCode = "0";
    		}
    	}
    	Integer mCodeNum = Integer.valueOf(mCode)+1; 
    	return StringUtils.rightPad(pcode+StringUtils.leftPad(""+mCodeNum,len,"0"), parentCode.length(),"0");
    }
	
	/**
	 * 生成自增编码
	 * @param prefix	编码前缀
	 * @param maxCode	当前编码
	 * @param len		编码长度
	 * @return
	 */
	public static String genCode(String prefix,String maxCode,Integer len){
    	String mCode = "0";
    	if(maxCode != null){
    		mCode = maxCode.replace(prefix, "").replaceAll("(.)\\1+", "");
    		if(StringUtils.isAllBlank(mCode)){
    			mCode = "0";
    		}
    	}
    	Integer mCodeNum = Integer.valueOf(mCode)+1; 
    	return prefix+StringUtils.leftPad(""+mCodeNum,len,"0");
    }

}
