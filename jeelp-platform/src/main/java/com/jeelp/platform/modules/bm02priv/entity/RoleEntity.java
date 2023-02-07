package com.jeelp.platform.modules.bm02priv.entity;

import java.math.BigDecimal;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:              Role
* @Description: TODO   角色管理管理
* @author                
* @date                2021-10-07
* @version             V1.0
*/
public class RoleEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 唯一主键
	*/
	private String roleId;
	
	/**
	 * 角色编码
	 */
	private String roleCode;

	/**
	* 角色名称
	*/
	private String roleName;

	/**
	* 角色描述
	*/
	private String roleDesc;

	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.roleId=this.id;
	}
	
	public String getId(){
		return this.roleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}
	
}