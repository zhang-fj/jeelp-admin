package com.jeelp.protal.modules.admin.service.impl;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.protal.modules.admin.entity.Topic;
import com.jeelp.protal.modules.admin.mapper.TopicMapper;
import com.jeelp.protal.modules.admin.service.TopicService;
import org.springframework.stereotype.Service;

/**
* @Title: TopicServiceImpl
* @Description: TODO 栏目管理管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@Service
public class TopicServiceImpl extends BaseServiceImpl<Topic> implements TopicService{

	private final TopicMapper mapper;

    public TopicServiceImpl(TopicMapper mapper) {
        this.mapper = mapper;
    }

    @Override
	public BaseMapper<Topic> getMapper(){
		return mapper;
	}

}
