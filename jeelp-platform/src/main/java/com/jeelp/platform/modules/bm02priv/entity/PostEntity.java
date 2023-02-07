package com.jeelp.platform.modules.bm02priv.entity;

import java.math.BigDecimal;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:              Post
* @Description: TODO   岗位管理管理
* @author                
* @date                2021-10-07
* @version             V1.0
*/
public class PostEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 岗位唯一主键
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
	* 岗位描述
	*/
	private String postDesc;

	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.postId=this.id;
	}
	
	public String getId(){
		return this.postId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}

	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}

}