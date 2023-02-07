/**
 * @Title 验证码工具类
 * @<Description  JEELP验证码工具类
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.platform.modules.bm01login.utils;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
public class CaptchaUtils {

	// 默认宽度
	public static final int  DEFAULT_WIDTH = 111;

	// 默认高度
	public static final int  DEFAULT_HEIGHT = 36;

	// 默认字符长度
	public static final int  DEFAULT_LENTH = 5;

	// 计算式验证码
	public static final String ARITHMETIC = "arithmetic"; 

	// 中文验证码
	public static final String CHINESE = "chinese";
	
	 // 中文闪图验证码
	public static final String CHINESE_GIF = "chinese_gif";
	
	 // 闪图验证码
	public static final String GIF = "gif";

	// SPEC验证码
	public static final String SPEC = "spec";
		
	/**
     * 选择验证码类型
     */
    private static Captcha switchCaptcha(String codeType) {
        Captcha captcha;
        switch (codeType) {
            case "arithmetic":
                captcha = new ArithmeticCaptcha();
                break;
            case "chinese":
                captcha = new ChineseCaptcha();
                break;
            case "chinese_gif":
                captcha = new ChineseGifCaptcha();
                break;
            case "gif":
                captcha = new GifCaptcha();
                break;
            case "spec":
                captcha = new SpecCaptcha();
                break;
            default:
            	captcha = new SpecCaptcha();
        }
        return captcha;
    }
	
	public static Captcha getCaptchaCode(String codeType,int width,int height,int length){
		Captcha captcha =  switchCaptcha(codeType);
		captcha.setWidth(width);
		captcha.setHeight(height);
		captcha.setLen(length);
		return captcha;
	}
	
}
