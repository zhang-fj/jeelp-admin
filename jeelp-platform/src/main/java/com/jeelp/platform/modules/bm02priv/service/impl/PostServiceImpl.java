package com.jeelp.platform.modules.bm02priv.service.impl;

import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.common.utils.SequenceUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm02priv.entity.PostEntity;
import com.jeelp.platform.modules.bm02priv.mapper.PostMapper;
import com.jeelp.platform.modules.bm02priv.service.PostService;
import com.jeelp.platform.modules.bm03sys.entity.SysSequence;

/**
* @Title:              PostServiceImpl
* @Description: TODO   岗位管理管理
* @author              
* @date                2021-10-07
* @version             V1.0
*/
@Service
public class PostServiceImpl extends BaseServiceImpl<PostEntity> implements PostService{

	private final PostMapper mapper;

	public PostServiceImpl(PostMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<PostEntity> getMapper(){
		return mapper;
	}

	@Override
	public Integer insert(PostEntity entity) {
		entity.setWhoForInsert(UserinfoUtils.getUserId());
		entity.setPostCode(SequenceUtil.getNextSequence("0002",4));
		Integer result = mapper.insert(entity);
		return result;
	}

}
