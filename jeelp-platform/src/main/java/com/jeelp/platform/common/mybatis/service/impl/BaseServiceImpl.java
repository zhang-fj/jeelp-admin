package com.jeelp.platform.common.mybatis.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeelp.platform.common.mybatis.domain.Entity;
import com.jeelp.platform.common.mybatis.exception.MessageRuntimeException;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.platform.common.mybatis.page.PageUtils;
import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;


/**
 *  Service 公共实现类，主要为公共业务标准化方法，提供最基本的业务逻辑实现以及数据库事务的控制方案
 *   1、提供公共的分页查询方法
 *   2、提供公共的添加，修改，删除的基本实现方法，并指定事务控制方法
 */

public abstract class BaseServiceImpl<T extends Entity> implements BaseService<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class.getName());

    public TabPage<T> selectForPage(Map<String, Object> params){
        TabPage<T> tabPage = new TabPage<T>();
        try {
            tabPage = PageUtils.selectForPage(params, new PageUtils.SelectCallBack<T>() {
	                @Override
	                public List<T> query(Map<String, Object> params) throws Exception {
	                    return findAll(params);
	                }
	            }
            );
        } catch (Exception e) {
            log.error("查询记录失败", e);
            throw new MessageRuntimeException("查询记录失败", e, params);
        }
        return tabPage;
    }

    public List<T> findAll(Map<String, Object> param){
        return getMapper().findAll(param);
    }

    @Override
    public T selectByPK(Object id){
        return getMapper().selectByPK(id);
    }

    @Override
    public T selectByPropertys(Map<String, Object> params){
        return getMapper().selectByPropertys(params);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public SaveModel<T> batchSaveOrUpdate(SaveModel<T> data) {
        if (data != null) {
            delete(data.getDels());
            batchSaveOrUpdate(data.getEdits());
            batchSaveOrUpdate(data.getAdds());
        }
        return data;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public T saveOrUpdate(T entity)  {
        if (StringUtils.isNotBlank(entity.getId())) {
            update(entity);
        } else {
            insert(entity);
        }
        return entity;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<T> batchSaveOrUpdate(List<T> entitys) {
        if(entitys!=null){
            for (T t : entitys) {
                saveOrUpdate(t);
            }
        }
        return entitys;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Integer insert(T entity) {
        entity.setWhoForInsert(UserinfoUtils.getUserId());
        return getMapper().insert(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Integer update(T entity) {
        entity.setWhoForUpdate(UserinfoUtils.getUserId());
        return getMapper().update(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void delete(List<T> entitys)  {
        if(entitys != null){
            for (T t : entitys) {
                delete(t);
            }
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Integer delete(T entity)  {
        return getMapper().delete(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Integer deleteByIds(List<Object> ids)  {
        return getMapper().deleteByIds(ids);
    }

    @Override
    public Integer deleteByParams(Map<String, Object> param)  {
        return getMapper().deleteByParams(param);
    }

}
