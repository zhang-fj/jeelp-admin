package com.jeelp.platform.modules.bm03sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:                Holiday
* @Description: TODO   假期规则管理
* @author                
* @date                2022-01-15
* @version            V1.0
*/
public class Holiday extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 唯一主键
	*/
	private String uuid;

	/**
	* 节假日日期
	*/
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh" , timezone="GMT+8")
	private Date daysets;

	/**
	* 分类：0法定假日 1周末调假上班
	*/
	private String sort;

	/**
	* 假期描述
	*/
	private String extInfo1;

	/**
	* 扩展字段02（备用）
	*/
	private String extInfo2;

	/**
	* 扩展字段03（备用）
	*/
	private String extInfo3;

	/**
	* 扩展字段04（备用）
	*/
	private String extInfo4;

	/**
	* 扩展字段05（备用）
	*/
	private String extInfo5;

	/**
	* 扩展字段06（备用）
	*/
	private String extInfo6;
	
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.uuid=this.id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDaysets() {
		return daysets;
	}

	public void setDaysets(Date daysets) {
		this.daysets = daysets;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getExtInfo1() {
		return extInfo1;
	}

	public void setExtInfo1(String extInfo1) {
		this.extInfo1 = extInfo1;
	}

	public String getExtInfo2() {
		return extInfo2;
	}

	public void setExtInfo2(String extInfo2) {
		this.extInfo2 = extInfo2;
	}

	public String getExtInfo3() {
		return extInfo3;
	}

	public void setExtInfo3(String extInfo3) {
		this.extInfo3 = extInfo3;
	}

	public String getExtInfo4() {
		return extInfo4;
	}

	public void setExtInfo4(String extInfo4) {
		this.extInfo4 = extInfo4;
	}

	public String getExtInfo5() {
		return extInfo5;
	}

	public void setExtInfo5(String extInfo5) {
		this.extInfo5 = extInfo5;
	}

	public String getExtInfo6() {
		return extInfo6;
	}

	public void setExtInfo6(String extInfo6) {
		this.extInfo6 = extInfo6;
	}

	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}

}