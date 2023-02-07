package com.jeelp.platform.modules.bm03sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:              AttachmentCate
* @Description: TODO   附件分类管理
* @author                
* @date                2022-01-22
* @version            V1.0
*/
public class AttachmentCate extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 分类ID
	*/
	private String cateId;

	/**
	* 分类编码
	*/
	private String cateCode;

	/**
	* 分类名称
	*/
	private String cateName;

	/**
	* 分类路径：文件储存路径
	*/
	private String catePath;

	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.cateId=this.id;
	}

	public String getId(){
		return this.cateId;
	}
	
	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCatePath() {
		return catePath;
	}

	public void setCatePath(String catePath) {
		this.catePath = catePath;
	}

	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}

}