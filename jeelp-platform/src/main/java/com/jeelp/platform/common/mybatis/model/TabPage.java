package com.jeelp.platform.common.mybatis.model;

import java.util.List;

/**
 *	分页模型
 */
public class TabPage<T> {

    /**
     * 总数
     */
    protected long total = 0;

    /**
     * 当前页面数据
     */
    protected List<T> data;

    /**
     * 提示信息
     */
    protected String msg;
    
    protected String code = "0";

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
