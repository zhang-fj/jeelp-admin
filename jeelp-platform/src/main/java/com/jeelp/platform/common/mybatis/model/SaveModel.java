package com.jeelp.platform.common.mybatis.model;

import java.util.List;

/**
 *	批量保存模型
 */
public class SaveModel<T> {

    /**
     * 新增数据列表
     */
    private List<T> adds;
    
    /**
     * 修改数据列表
     */
    private List<T> edits;
    
    /**
     * 删除数据列表
     */
    private List<T> dels;

    public List<T> getAdds() {
        return adds;
    }

    public void setAdds(List<T> adds) {
        this.adds = adds;
    }

    public List<T> getEdits() {
        return edits;
    }

    public void setEdits(List<T> edits) {
        this.edits = edits;
    }

    public List<T> getDels() {
        return dels;
    }

    public void setDels(List<T> dels) {
        this.dels = dels;
    }
}
