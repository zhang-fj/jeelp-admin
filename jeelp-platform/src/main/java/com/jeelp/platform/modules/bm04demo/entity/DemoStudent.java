package com.jeelp.platform.modules.bm04demo.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

import java.math.BigDecimal;

/**
* @Title:              DemoStudent
* @Description: TODO   学生演示管理
* @author                
* @date                2022-01-25
* @version             V1.0
*/
public class DemoStudent extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 学生的ID，格式：UUID
	*/
	private String studId;

	/**
	* 学生姓名，必填
	*/
	private String studName;

	/**
	* 学生的身份证号，18位，要加验证，必填
	*/
	private String studIdcard;

	/**
	* 学生的性别， 从t_code_gender中下拉选择，必填
	*/
	private String studGender;

	/**
	* 学生所属班级，如2021级8班，必填
	*/
	private String studClass;

	/**
	* 学生的学号，这个是学校发的，手工录入
	*/
	private String studN0;

	/**
	* 学生手机号码
	*/
	private String studPhone;

	/**
	* 学生邮件地址
	*/
	private String studEmail;

	/**
	* 学生照片，对应附件表中的UUID
	*/
	private String studPhoto;

	/**
	* 学生简历，用一个文本框录入
	*/
	private String studResume;
	
	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.studId=this.id;
	}

	public String getStudId() {
		return studId;
	}
	
	public String getId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudIdcard() {
		return studIdcard;
	}

	public void setStudIdcard(String studIdcard) {
		this.studIdcard = studIdcard;
	}

	public String getStudGender() {
		return studGender;
	}

	public void setStudGender(String studGender) {
		this.studGender = studGender;
	}

	public String getStudClass() {
		return studClass;
	}

	public void setStudClass(String studClass) {
		this.studClass = studClass;
	}

	public String getStudN0() {
		return studN0;
	}

	public void setStudN0(String studN0) {
		this.studN0 = studN0;
	}

	public String getStudPhone() {
		return studPhone;
	}

	public void setStudPhone(String studPhone) {
		this.studPhone = studPhone;
	}

	public String getStudEmail() {
		return studEmail;
	}

	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}

	public String getStudPhoto() {
		return studPhoto;
	}

	public void setStudPhoto(String studPhoto) {
		this.studPhoto = studPhoto;
	}

	public String getStudResume() {
		return studResume;
	}

	public void setStudResume(String studResume) {
		this.studResume = studResume;
	}

	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}

}