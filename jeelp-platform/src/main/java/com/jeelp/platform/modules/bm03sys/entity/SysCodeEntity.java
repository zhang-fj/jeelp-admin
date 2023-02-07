package com.jeelp.platform.modules.bm03sys.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:                SysCode
* @Description: TODO   字典索引管理
* @author                
* @date                2021-10-06
* @version            V1.0
*/
public class SysCodeEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 代码表名
	*/
	private String codeId;

	/**
	* 代码表描述
	*/
	private String codeDesc;

	/**
	* 备注字段
	*/
	private String remark;

	/**
	* 代码表排序字段
	*/
	private String orderCol;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.codeId=this.id;
	}
	
	public String getId(){
		return this.codeId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

}