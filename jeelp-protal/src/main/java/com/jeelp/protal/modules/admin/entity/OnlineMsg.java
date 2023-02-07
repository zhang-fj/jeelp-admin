package com.jeelp.protal.modules.admin.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title: OnlineMsg
* @Description: TODO 在线留言
* @author 
* @date 2022-03-19
* @version V1.0
*/
public class OnlineMsg extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 姓名
	*/
	private String name;

	/**
	* 电话
	*/
	private String mobile;

	/**
	* 邮箱
	*/
	private String email;

	/**
	* 留言内容
	*/
	private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}