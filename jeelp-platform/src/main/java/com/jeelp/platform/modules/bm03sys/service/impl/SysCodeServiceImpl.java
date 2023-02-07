package com.jeelp.platform.modules.bm03sys.service.impl;

import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm03sys.entity.SysCodeEntity;
import com.jeelp.platform.modules.bm03sys.mapper.SysCodeMapper;
import com.jeelp.platform.modules.bm03sys.service.SysCodeService;

/**
* @Title:              SysCodeServiceImpl
* @Description: TODO   字典索引管理
* @author              
* @date                2021-10-06
* @version             V1.0
*/
@Service
public class SysCodeServiceImpl extends BaseServiceImpl<SysCodeEntity> implements SysCodeService{

	private final SysCodeMapper mapper;
	
	public SysCodeServiceImpl(SysCodeMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<SysCodeEntity> getMapper(){
		return mapper;
	}

}
