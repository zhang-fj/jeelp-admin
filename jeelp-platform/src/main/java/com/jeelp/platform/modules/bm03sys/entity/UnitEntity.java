package com.jeelp.platform.modules.bm03sys.entity;

import java.math.BigDecimal;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:                Unti
* @Description: TODO   机构代码管理
* @author                
* @date                2021-10-12
* @version            V1.0
*/
public class UnitEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 机构代码
	*/
	private String code;

	/**
	* 机构(日常）名称
	*/
	private String name;

	/**
	* 拼音缩写
	*/
	private String spell;

	/**
	* 机构全称
	*/
	private String fullName;

	/**
	* 机构简称
	*/
	private String shortName;

	/**
	* 邮政编码
	*/
	private String postCode;

	/**
	* 地址
	*/
	private String address;

	/**
	* 联系电话
	*/
	private String telphone;

	/**
	* 电子邮箱
	*/
	private String email;

	/**
	* 上级机构代码
	*/
	private String pcode;

	/**
	* 机构描述
	*/
	private String descs;

	/**
	* 排序编号，数字，越大越排后
	*/
	private BigDecimal unitOrder;
	
	/**
	 * 子机构数量
	 */
	private Integer subUnitCount;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.code=this.id;
	}
	
	public String getId(){
		return this.code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public BigDecimal getUnitOrder() {
		return unitOrder;
	}

	public void setUnitOrder(BigDecimal unitOrder) {
		this.unitOrder = unitOrder;
	}

	public Integer getSubUnitCount() {
		return subUnitCount;
	}

	public void setSubUnitCount(Integer subUnitCount) {
		this.subUnitCount = subUnitCount;
	}
	
}