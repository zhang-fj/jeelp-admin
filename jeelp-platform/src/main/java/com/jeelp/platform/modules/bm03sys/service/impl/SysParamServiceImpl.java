package com.jeelp.platform.modules.bm03sys.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.common.redis.RedisUtils;
import com.jeelp.platform.common.utils.SequenceUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm03sys.entity.SysParam;
import com.jeelp.platform.modules.bm03sys.mapper.SysParamMapper;
import com.jeelp.platform.modules.bm03sys.service.SysParamService;

/**
* @Title:              SysParamServiceImpl
* @Description: TODO   系统参数管理
* @author              
* @date                2021-12-14
* @version             V1.0
*/
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService{


	private final String SYS_PARAM_REDIS_KEY="sys::param:";
	private final RedisUtils redisUtils;
	
	private final SysParamMapper mapper;

    public SysParamServiceImpl(SysParamMapper mapper,RedisUtils redisUtils) {
        this.mapper = mapper;
        this.redisUtils = redisUtils;
    }

    @Override
	public BaseMapper<SysParam> getMapper(){
		return mapper;
	}

	@Override
	public Integer insert(SysParam entity) {
		// 设置默认参数
		entity.setWhoForInsert(UserinfoUtils.getUserId());
		// 设置ID
		entity.setId(SequenceUtil.getNextSequence("0005", 4));
		// 保存参数配置
		Integer result = mapper.insert(entity);
		// 刷新redis缓存
		reloadRedis();
		return result;
	}
	
	@Override
	public Integer update(SysParam entity) {
		// 修改参数配置
		Integer result = super.update(entity);
		// 刷新redis缓存
		reloadRedis();
		return result;
	}

	@Override
	public Integer deleteByIds(List<Object> ids) {
		// 删除参数配置
		Integer result = super.deleteByIds(ids);
		// 刷新redis缓存
		reloadRedis();
		return result;
	}

	@Override
	public void reloadRedis(){
		List<SysParam> params = findAll(new HashMap<String,Object>());
		if(params != null){
			for(SysParam param:params){
				redisUtils.set(SYS_PARAM_REDIS_KEY+param.getParamId(), param.getParamValue(),0);
			}
		}
	}
	
	@Override
	public String getParamValue(String paramId){
		// 从缓存中获取系统参数
		String value = (String) redisUtils.get(SYS_PARAM_REDIS_KEY+paramId);
		// 如果系统参数在缓存中不存在
		if(!StringUtils.isNotBlank(value)){
			// 则从数据库中获取并放入缓存中
			SysParam param =  mapper.selectByPK(paramId);
			if(param!=null){
				value = param.getParamValue();
				redisUtils.set(SYS_PARAM_REDIS_KEY+param.getParamId(), param.getParamValue(),0);
			}
		}
		return value;
	}

}
