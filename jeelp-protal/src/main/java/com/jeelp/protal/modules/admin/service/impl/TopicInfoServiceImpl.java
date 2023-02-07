package com.jeelp.protal.modules.admin.service.impl;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.protal.modules.admin.entity.TopicInfo;
import com.jeelp.protal.modules.admin.mapper.TopicInfoMapper;
import com.jeelp.protal.modules.admin.service.TopicInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @Title: TopicInfoServiceImpl
* @Description: TODO 栏目信息管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@Service
public class TopicInfoServiceImpl extends BaseServiceImpl<TopicInfo> implements TopicInfoService{

	private final TopicInfoMapper mapper;

    public TopicInfoServiceImpl(TopicInfoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
	public BaseMapper<TopicInfo> getMapper(){
		return mapper;
	}

    @Override
    public TopicInfo saveOrUpdate(TopicInfo entity) {
        if(this.update(entity)==0){
            this.insert(entity);
        }
        return entity;
    }

    @Override
    public void release(List<String> ids) {
        if(ids != null){
            for(String id:ids){
                TopicInfo topicInfo = new TopicInfo();
                topicInfo.setId(id);
                topicInfo.setReleaseUser(UserinfoUtils.getUserName());
                topicInfo.setReleaseTime(new Date());
                topicInfo.setStatus("1");
                this.saveOrUpdate(topicInfo);
            }
        }
    }

    @Override
    public void back(List<String> ids) {
        if(ids != null){
            for(String id:ids){
                TopicInfo topicInfo = new TopicInfo();
                topicInfo.setId(id);
                topicInfo.setReleaseUser(UserinfoUtils.getUserName());
                topicInfo.setReleaseTime(new Date());
                topicInfo.setStatus("2");
                this.saveOrUpdate(topicInfo);
            }
        }
    }
}
