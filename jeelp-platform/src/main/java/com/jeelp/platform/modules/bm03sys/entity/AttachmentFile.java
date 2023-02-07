package com.jeelp.platform.modules.bm03sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeelp.platform.common.mybatis.domain.Entity;

/**
* @Title:              AttachmentFile
* @Description: TODO   附件上传管理
* @author                
* @date                2022-01-22
* @version             V1.0
*/
public class AttachmentFile extends Entity {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 362013224742003121L;

	/**
	* 文件附件ID
	*/
	private String fileid;

	/**
	* 业务ID
	*/
	private String srcId;

	/**
	* 类型，表示由哪类业务产生
	*/
	private String type;
	
	/**
	 * 业务类型名称
	 */
	private String typeName;

	/**
	* 文件扩展名
	*/
	private String ext;

	/**
	* 原始文件名
	*/
	private String oriFileName;
	
	/**
	 * contentType 类型
	 */
	private String contentType;

	/**
	* 存储文件名
	*/
	private String storeFileName;

	/**
	* 存储路径,因为可能存在不同的节点机器上,这个路径很有可能是个URL
	*/
	private String storePath;

	/**
	* 删除标志 1删除 0正常（使用）
	*/
	private BigDecimal delMark;

	@Override
	public void setId(String id) {
		super.setId(id);
	    this.fileid=this.id;
	}

	public String getId(){
		return this.fileid;
	}
	
	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getOriFileName() {
		return oriFileName;
	}

	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}

	public String getStoreFileName() {
		return storeFileName;
	}

	public void setStoreFileName(String storeFileName) {
		this.storeFileName = storeFileName;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public BigDecimal getDelMark() {
		return delMark;
	}

	public void setDelMark(BigDecimal delMark) {
		this.delMark = delMark;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
}