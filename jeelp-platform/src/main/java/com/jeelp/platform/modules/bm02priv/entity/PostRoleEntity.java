package com.jeelp.platform.modules.bm02priv.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:                PostRole
* @Description: TODO   岗位角色管理
* @author                
* @date                2021-10-08
* @version            V1.0
*/
public class PostRoleEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* T_PRIV_POST表唯一主键
	*/
	private String postId;
	
	/**
	 * 岗位编码
	 */
	private String postCode;
	
	/**
	 * 岗位名称
	 */
	private String postName;

	/**
	* T_PRIV_ROLE表唯一主键
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

	@Override
	public void setId(String id) {
		super.setId(id);
	}
	
	public String getId(){
		return this.postId+"-"+this.roleId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
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
	
}