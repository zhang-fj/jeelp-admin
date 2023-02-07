package com.jeelp.platform.modules.bm02priv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.common.utils.SequenceUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm02priv.entity.PostEntity;
import com.jeelp.platform.modules.bm02priv.entity.RoleEntity;
import com.jeelp.platform.modules.bm02priv.entity.RoleFuncEntity;
import com.jeelp.platform.modules.bm02priv.mapper.RoleMapper;
import com.jeelp.platform.modules.bm02priv.service.RoleService;

/**
* @Title:              RoleServiceImpl
* @Description: TODO   角色管理管理
* @author              
* @date                2021-10-07
* @version             V1.0
*/
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity> implements RoleService{

	private final RoleMapper mapper;
	
	public RoleServiceImpl(RoleMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<RoleEntity> getMapper(){
		return mapper;
	}
	
	@Override
	public Integer insert(RoleEntity entity) {
		entity.setWhoForInsert(UserinfoUtils.getUserId());
		entity.setRoleCode(SequenceUtil.getNextSequence("0004",4));
		Integer result = mapper.insert(entity);
		return result;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void auth(Map<String, Object> params) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", params.get("roleId"));
		mapper.deleteRoleFuncs(param);
		List<String> list = (List<String>) params.get("funcIds");
		if(list.size()>0){
			List<RoleFuncEntity> roleFuncs = list.stream().map(funcId-> new RoleFuncEntity((String)params.get("roleId"),funcId)).collect(Collectors.toList());
			mapper.insertRoleFuncs(roleFuncs);
		}
	}

	@Override
	public List<String> getAuthFuncIds(String roleId) {
		return mapper.getAuthFuncIds(roleId);
	}

}
