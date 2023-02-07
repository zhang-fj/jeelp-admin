package com.jeelp.platform.modules.bm03sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.common.utils.GenCodeUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm03sys.entity.UnitEntity;
import com.jeelp.platform.modules.bm03sys.mapper.UnitMapper;
import com.jeelp.platform.modules.bm03sys.service.UnitService;

/**
* @Title:              UntiServiceImpl
* @Description: TODO   机构代码管理
* @author              
* @date                2021-10-12
* @version             V1.0
*/
@Service
public class UnitServiceImpl extends BaseServiceImpl<UnitEntity> implements UnitService{

	private final UnitMapper mapper;

	public UnitServiceImpl(UnitMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<UnitEntity> getMapper(){
		return mapper;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public Integer insert(UnitEntity entity) {
		entity.setWhoForInsert(UserinfoUtils.getUserId());
		entity.setId(GenCodeUtil.genCode("GU", entity.getPcode(), mapper.getSubMaxCode(entity.getPcode()), 2));
		return mapper.insert(entity);
	}
	
	

}
