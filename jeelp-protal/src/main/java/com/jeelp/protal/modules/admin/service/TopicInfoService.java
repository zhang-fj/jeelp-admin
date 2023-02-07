package com.jeelp.protal.modules.admin.service;

import com.jeelp.platform.common.mybatis.service.BaseService;
import com.jeelp.protal.modules.admin.entity.TopicInfo;

import java.util.List;

/**
* @Title: TopicInfoService
* @Description: TODO 栏目信息管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
public interface TopicInfoService extends BaseService<TopicInfo> {

    void release(List<String> ids);

    void back(List<String> ids);
}