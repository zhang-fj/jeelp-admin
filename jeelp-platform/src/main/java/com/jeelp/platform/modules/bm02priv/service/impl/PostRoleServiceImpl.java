package com.jeelp.platform.modules.bm02priv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm02priv.entity.PostRoleEntity;
import com.jeelp.platform.modules.bm02priv.mapper.PostRoleMapper;
import com.jeelp.platform.modules.bm02priv.service.PostRoleService;

/**
* @Title:              PostRoleServiceImpl
* @Description: TODO   岗位角色管理
* @author              
* @date                2021-10-08
* @version             V1.0
*/
@Service
public class PostRoleServiceImpl extends BaseServiceImpl<PostRoleEntity> implements PostRoleService{

	private final PostRoleMapper mapper;

	public PostRoleServiceImpl(PostRoleMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<PostRoleEntity> getMapper(){
		return mapper;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public List<PostRoleEntity> batchSaveOrUpdate(List<PostRoleEntity> entitys) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(entitys!=null){
			for(PostRoleEntity entity:entitys){
				params.put("postId", entity.getPostId());
				params.put("roleId", entity.getRoleId());
				if(mapper.selectCount(params) == 0){
					mapper.insert(entity);
				}
			}
		}
		return entitys;
	}

}
