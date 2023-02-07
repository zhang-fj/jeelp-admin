package com.jeelp.platform.common.mybatis.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.jeelp.platform.common.mybatis.domain.Entity;
import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;

/**
 *	Service公共接口，公共业务的标准化方法
 */
public interface BaseService<T extends Entity> {

    /**
     * 使用钩子方法 获取对应的mapper
     * @return BaseMapper<T>
     */
    public BaseMapper<T> getMapper();

    /**
     * 根据查询查询参数获取分页列表
     * @param params
     * @return TabPage<T>
     */
    public TabPage<T> selectForPage(Map<String, Object> params);

    /**
     * 根据主键id 获取对象信息
     * @param id
     * @return T
     */
    public T selectByPK(Object id);

    /**
     * 根据参数查询单条数据
     * @param params
     * @return T
     */
    public T selectByPropertys(Map<String, Object> params);


    /**
     * 插入一条信息
     * @param entity
     * @return Integer
     */
    public Integer insert(T entity);

    /**
     * 修改一条信息
     *
     * @throws Exception
     * @param entity
     * @return Integer
     */
    public Integer update(T entity);


    /**
     * 批量保存或更新信息
     * @param entitys
     * @return List<T>
     */
    public List<T> batchSaveOrUpdate(List<T> entitys);

    /**
     * @Description TODO 保存或更新
     * @param entity
     * @return T
     */
    public T saveOrUpdate(T entity);

    /**
     * 批量保存或更新信息
     * @param datas
     * @return SaveModel<T>
     */
    public SaveModel<T> batchSaveOrUpdate(SaveModel<T> datas);

    /**
     * 根据对象数组删除数据
     * @param entitys
     * @return: void
     */
    public void delete(List<T> entitys);


    /**
     * 根据对象删除数据
     * @param entity
     * @return: void
     */
    public Integer delete(T entity);

    /**
     * 根据参数删除数据
     * @param params
     * @return: Integer
     */
    public Integer deleteByParams(Map<String, Object> params);

    /**
     * 根据ids数组删除数据
     * @param ids
     * @return: Integer
     */
    public Integer deleteByIds(List<Object> ids);

    /**
     * 查询根据所有参数查询信息
     * @param params
     * @return: Integer
     */
    public List<T> findAll(Map<String, Object> params);

}
