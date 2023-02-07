package com.jeelp.platform.common.mybatis.mapper;

import java.util.List;
import java.util.Map;

/**
 * 基础接口，主要提供基本增删改查标准化方法
 */
public interface BaseMapper<T> {

    /**
     * 根据查询信息获取信息总数量
     * @param param
     */
    public Integer selectCount(Map<String, Object> param);

    /**
     * 根据id获取一条数据
     * @param id
     */
    public T selectByPK(Object id);


    /**
     * 根据参数获取一条数据
     * @param params
     */
    public T selectByPropertys(Map<String, Object> params);

    /**
     * 根据实体删除一条数据
     * @param entity
     */
    public Integer delete(T entity);


    /**
     * 根据参数删除
     * @param param
     */
    public Integer deleteByParams(Map<String, Object> param);

    /**
     * 根据id数组删除
     * @param ids
     */
    public Integer deleteByIds(List<Object> ids);

    /**
     * 添加一条数据
     * @param entity
     */
    public Integer insert(T entity);

    /**
     * 修改一条数据
     * @param entity
     */
    public Integer update(T entity);

    /**
     * 根据参数查询所有数据
     * @param param
     */
    public List<T> findAll(Map<String, Object> param);

}
