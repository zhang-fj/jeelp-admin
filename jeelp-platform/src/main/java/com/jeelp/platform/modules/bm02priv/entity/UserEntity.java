/**
 * @Title JEELP轻量级开发平台-系统用户实体类
 * @<Description  JEELP轻量级开发平台-系统用户实体类，处理数据库映射
 * @<Copyright  JEELP  Copyright (c) 2020
 * @author JEELP
 * @version 01.00.00
 * @since 2020.05.01
 */
package com.jeelp.platform.modules.bm02priv.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jeelp.platform.common.mybatis.domain.Entity;

public class UserEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

    /**
	* 唯一主键
	*/
	private String userId;

	/**
	* 员工编号
	*/
	private String empCode;

	/**
	* 姓名
	*/
	private String empName;

	/**
	* 系统登录用户名
	*/
	private String userName;

	/**
	* 员工所属单位
	*/
	private String empUnitcode;

	/**
	* 系统登录密码
	*/
	private String userPassword;

	/**
	* 员工职务
	*/
	private String empBusiness;

	/**
	* 默认业务首页
	*/
	private String indexPage;

	/**
	* 是否锁定  1锁定  0正常
	*/
	private String isLock;

	/**
	* 用户登录客户端IP地址
	*/
	private String ip;

	/**
	* 员工职员类型
	*/
	private String empType;

	/**
	* 手机号码
	*/
	private String empPhone;

	/**
	* 排序编号，数字，越大越排后
	*/
	private BigDecimal empOrder;

	/**
	* 邮件地址
	*/
	private String empEmail;
	
	/**
	* 删除标识 0不删除 1删除
	*/
	private BigDecimal delMark;

    /**
     * 角色数组
     */
	private List<String> roles = new ArrayList<>();

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.userId=this.id;
	}
	
	@Override
	public String getId(){
		return this.userId;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmpUnitcode() {
		return empUnitcode;
	}

	public void setEmpUnitcode(String empUnitcode) {
		this.empUnitcode = empUnitcode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmpBusiness() {
		return empBusiness;
	}

	public void setEmpBusiness(String empBusiness) {
		this.empBusiness = empBusiness;
	}

	public String getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public BigDecimal getEmpOrder() {
		return empOrder;
	}

	public void setEmpOrder(BigDecimal empOrder) {
		this.empOrder = empOrder;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}

	public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}