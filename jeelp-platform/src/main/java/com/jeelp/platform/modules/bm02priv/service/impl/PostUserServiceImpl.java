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
import com.jeelp.platform.modules.bm02priv.entity.PostUserEntity;
import com.jeelp.platform.modules.bm02priv.mapper.PostUserMapper;
import com.jeelp.platform.modules.bm02priv.service.PostUserService;

/**
* @Title:              PostUserServiceImpl
* @Description: TODO   人员岗位管理
* @author              
* @date                2021-10-08
* @version             V1.0
*/
@Service
public class PostUserServiceImpl extends BaseServiceImpl<PostUserEntity> implements PostUserService{


	private final PostUserMapper mapper;

	public PostUserServiceImpl(PostUserMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<PostUserEntity> getMapper(){
		return mapper;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public List<PostUserEntity> batchSaveOrUpdate(List<PostUserEntity> entitys) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(entitys!=null){
			for(PostUserEntity entity:entitys){
				params.put("postId", entity.getPostId());
				params.put("userId", entity.getUserId());
				if(mapper.selectCount(params) == 0){
					mapper.insert(entity);
				}
			}
		}
		return entitys;
	}
	
	

}
