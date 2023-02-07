package com.jeelp.platform.modules.bm03sys.entity;

import com.jeelp.platform.common.mybatis.domain.Entity;

import java.math.BigDecimal;

/**
* @Title:                SysParam
* @Description: TODO   系统参数管理
* @author                
* @date                2021-12-14
* @version            V1.0
*/
public class SysParam extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 参数名称
	*/
	private String paramId;

	/**
	* 参数值
	*/
	private String paramValue;

	/**
	* 参数描述
	*/
	private String paramDesc;

	/**
	* 备注
	*/
	private String remark;

	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.paramId=this.id;
	}

    public String getId() {
        return paramId;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getDelMark() {
        return delMark;
    }

    public void setDelMark(BigDecimal delMark) {
        this.delMark = delMark;
    }
}