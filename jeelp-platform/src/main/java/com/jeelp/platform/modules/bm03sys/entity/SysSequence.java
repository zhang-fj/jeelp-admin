package com.jeelp.platform.modules.bm03sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:               SysSequence
* @Description: TODO    序列号管理
* @author                
* @date                2022-01-25
* @version             V1.0
*/
public class SysSequence extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 同规则序列的组号
	*/
	private String groupId;

	/**
	* 业务前缀字母
	*/
	private String bizPrefix;

	/**
	* 当前值
	*/
	private BigDecimal seqval;

	/**
	* 备注
	*/
	private String remark;
	
	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;


	@Override
	public void setId(String id) {
		super.setId(id);
	    this.groupId=this.id;
	}
	
	public String getId() {
		return this.groupId;
	}



	public String getGroupId() {
		return groupId;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public String getBizPrefix() {
		return bizPrefix;
	}


	public void setBizPrefix(String bizPrefix) {
		this.bizPrefix = bizPrefix;
	}


	public BigDecimal getSeqval() {
		return seqval;
	}


	public void setSeqval(BigDecimal seqval) {
		this.seqval = seqval;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public BigDecimal getDelMark() {
		return delMark;
	}


	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}
	
		

}