package com.jeelp.protal.modules.admin.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title: Topic
* @Description: TODO 栏目管理管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
public class Topic extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 本系统中的用户ID，格式：UUID
	*/
	private String topicId;

	/**
	* 栏目名称
	*/
	private String topicName;

	/**
	* 栏目描述
	*/
	private String topicDesc;

	/**
	* 栏目路径
	*/
	private String topicPath;

    /**
     *是否为导航菜单：0-否，1-是
     */
    private String navigateMenu;

	/**
	* 是否首页展示：0-否，1-是
	*/
	private String homeDisplay;

	/**
	* 首页展示条数
	*/
	private Integer homeInfoSize;

	/**
	* 首页展示位置
	*/
	private Integer homeIndex;

	/**
	* 首页展示模板
	*/
	private String homeTemp;

	/**
	* 二级页模板
	*/
	private String secondaryTemp;

	/**
	* 信息页模板
	*/
	private String infoTemp;

    /**
     * 信息类型
     */
	private String infoType;

	/**
	* 排序
	*/
	private Integer sort;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.topicId=this.id;
	}

    public String getId(){
        return this.topicId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicPath() {
        return topicPath;
    }

    public void setTopicPath(String topicPath) {
        this.topicPath = topicPath;
    }

    public String getHomeDisplay() {
        return homeDisplay;
    }

    public void setHomeDisplay(String homeDisplay) {
        this.homeDisplay = homeDisplay;
    }

    public Integer getHomeInfoSize() {
        return homeInfoSize;
    }

    public void setHomeInfoSize(Integer homeInfoSize) {
        this.homeInfoSize = homeInfoSize;
    }

    public Integer getHomeIndex() {
        return homeIndex;
    }

    public void setHomeIndex(Integer homeIndex) {
        this.homeIndex = homeIndex;
    }

    public String getHomeTemp() {
        return homeTemp;
    }

    public void setHomeTemp(String homeTemp) {
        this.homeTemp = homeTemp;
    }

    public String getSecondaryTemp() {
        return secondaryTemp;
    }

    public void setSecondaryTemp(String secondaryTemp) {
        this.secondaryTemp = secondaryTemp;
    }

    public String getInfoTemp() {
        return infoTemp;
    }

    public void setInfoTemp(String infoTemp) {
        this.infoTemp = infoTemp;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNavigateMenu() {
        return navigateMenu;
    }

    public void setNavigateMenu(String navigateMenu) {
        this.navigateMenu = navigateMenu;
    }
}