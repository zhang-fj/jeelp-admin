package com.jeelp.platform.common.mybatis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 普通的数据库映射实体公共父类，提供普通业务实体数据库映射的公共字段，及统一设置公共字段的方法
 */
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    protected String id;

    /**
     * 创建人
     */
    protected String inputUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    protected Date inputTime;

    /**
     * 更新人
     */
    protected String updateUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    protected Date updateTime;

    /**
     * 版本标识
     */
    protected Integer version;

    /**
     * 是否启用：是（Y）；否（N）
     */
    private String enabled;

    /**
     * 删除标志 1删除 0正常（使用）
     */
    private BigDecimal delMark;

    /**
     * 插入时，设置创建用户，创建时间，更新用户，更新时间
     */
    public void setWhoForInsert(String userId) {
        this.inputUser = userId;
        this.inputTime = new Date();
        this.updateUser = userId;
        this.updateTime = this.inputTime;
        this.setId(UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * 修改时，设置更新用户，更新时间
     */
    public void setWhoForUpdate(String userId) {
        this.updateUser = userId;
        this.updateTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getDelMark() {
        return delMark;
    }

    public void setDelMark(BigDecimal delMark) {
        this.delMark = delMark;
    }
}
