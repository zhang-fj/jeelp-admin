package com.jeelp.platform.modules.bm03sys.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:              Code
* @Description: TODO   字典明细管理
* @author                
* @date                2021-10-06
* @version            V1.0
*/
public class CodeEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 编码
	*/
	private String code;

	/**
	* 名称
	*/
	private String name;

	/**
	* 拼音简写
	*/
	private String spell;
	
	private String sysCode;
	
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

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

}