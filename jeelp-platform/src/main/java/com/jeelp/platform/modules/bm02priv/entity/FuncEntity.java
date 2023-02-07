package com.jeelp.platform.modules.bm02priv.entity;

import java.util.List;

import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:                Func
* @Description: TODO   菜单管理
* @author                
* @date                2021-09-22
* @version            V1.0
*/
public class FuncEntity extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 唯一主键
	*/
	private String funcId;

	/**
	* 功能树代码
	*/
	private String funcCode;

	/**
	* 上级功能树代码
	*/
	private String upFuncUuid;

	/**
	* 功能名称
	*/
	private String funcName;

	/**
	* 功能简称
	*/
	private String funcShortName;

	/**
	* 功能地址
	*/
	private String funcAddr;

	/**
	* 是否叶子节点-1 是  0不是（文件夹）
	*/
	private String isLeaf;

	/**
	* 功能显示的图标地址
	*/
	private String icon;

	/**
	* 菜单序号
	*/
	private Integer orderNum;

	/**
	* 打开方式 0tab页打开 1window open打开
	*/
	private Integer openType;

	/**
	* 乐观锁
	*/
	private Integer revision;

    /**
     * 组件
     */
	private String component;

	/**
	* 删除标识 0不删除 1删除
	*/
	private Integer delMark;

    /**
     * 下级菜单数量
     */
	private Integer subFuncCount;
	
	/**
	 * 下级菜单
	 */
	private List<FuncEntity> children;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.funcId=this.id;
	}
	
	public String getId(){
		return this.funcId;
	}

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public String getUpFuncUuid() {
        return upFuncUuid;
    }

    public void setUpFuncUuid(String upFuncUuid) {
        this.upFuncUuid = upFuncUuid;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncShortName() {
        return funcShortName;
    }

    public void setFuncShortName(String funcShortName) {
        this.funcShortName = funcShortName;
    }

    public String getFuncAddr() {
        return funcAddr;
    }

    public void setFuncAddr(String funcAddr) {
        this.funcAddr = funcAddr;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getSubFuncCount() {
        return subFuncCount;
    }

    public void setSubFuncCount(Integer subFuncCount) {
        this.subFuncCount = subFuncCount;
    }

    public List<FuncEntity> getChildren() {
        return children;
    }

    public void setChildren(List<FuncEntity> children) {
        this.children = children;
    }
}