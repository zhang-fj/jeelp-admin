package com.jeelp.protal.modules.admin.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title: InfoCate
* @Description: TODO 信息分类管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
public class InfoCate extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 本系统中的用户ID，格式：UUID
	*/
	private String cateId;

	/**
	* 栏目ID
	*/
	private String topicId;

	/**
	* 栏目编码
	*/
	private String topicCode;

	/**
	* 分类名称
	*/
	private String cateName;

	/**
	* 分类编码
	*/
	private String cateCode;

	/**
	* 排序字段
	*/
	private Integer sort;

	/**
	* 分类状态：0-未发布。1-已发布，2-已回收
	*/
	private Integer status;

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

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}