package com.jeelp.protal.modules.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeelp.platform.common.mybatis.domain.Entity;

import java.util.Date;

/**
* @Title: TopicInfo
* @Description: TODO 栏目信息管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
public class TopicInfo extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 本系统中的用户ID，格式：UUID
	*/
	private String infoId;

	/**
	* 栏目ID
	*/
	private String topicId;

	/**
	* 信息描述
	*/
	private String desc;

	/**
	* 分类ID
	*/
	private String cateId;

	/**
	* 分类编码
	*/
	private String cateCode;

	/**
	* 标题
	*/
	private String title;

	/**
	* 内容
	*/
	private String content;

	/**
	* 主要图片地址
	*/
	private String mainImgId;

	/**
	* 信息状态：0-待发布，1-已发布，2-已回收
	*/
	private String status;

	/**
	* 发布人
	*/
	private String releaseUser;

	/**
	* 发布时间
	*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date releaseTime;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.infoId=this.id;
	}

    public String getId(){
        return this.infoId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMainImgId() {
        return mainImgId;
    }

    public void setMainImgId(String mainImgId) {
        this.mainImgId = mainImgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(String releaseUser) {
        this.releaseUser = releaseUser;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}