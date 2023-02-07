package com.jeelp.platform.modules.bm03sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm03sys.entity.CodeEntity;
import com.jeelp.platform.modules.bm03sys.mapper.CodeMapper;
import com.jeelp.platform.modules.bm03sys.service.CodeService;

/**
* @Title:              CodeServiceImpl
* @Description: TODO   字典明细管理
* @author              
* @date                2021-10-06
* @version             V1.0
*/
@Service
public class CodeServiceImpl extends BaseServiceImpl<CodeEntity> implements CodeService{

	private final CodeMapper mapper;

	public CodeServiceImpl(CodeMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<CodeEntity> getMapper(){
		return mapper;
	}

	@Override
	public CodeEntity saveOrUpdate(CodeEntity entity) {
		Map<String,Object> params = new HashMap<>();
		params.put("code", entity.getCode());
		params.put("sysCode", entity.getSysCode());
		if(mapper.selectCount(params)>0){
			update(entity);
		}else{
			insert(entity);
		}
		return entity;
	}

}
