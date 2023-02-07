package com.jeelp.platform.modules.bm02priv.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:                PostUser
* @Description: TODO   人员岗位管理
* @author                
* @date                2021-10-08
* @version            V1.0
*/
public class PostUserEntity extends Entity {

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
	* T_SYS_USER表唯一主键
	*/
	private String userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 员工编码
	 */
	private String empCode;
	
	/**
	 * 员工姓名
	 */
	private String empName;

	@Override
	public void setId(String id) {
		super.setId(id);
	}

	public String getId(){
		return this.postId+"-"+this.userId;
	}
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}