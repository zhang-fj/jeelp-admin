package com.jeelp.protal.modules.admin.service.impl;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.protal.modules.admin.entity.InfoCate;
import com.jeelp.protal.modules.admin.entity.OnlineMsg;
import com.jeelp.protal.modules.admin.mapper.OnlineMsgMapper;
import com.jeelp.protal.modules.admin.service.OnlineMsgService;
import org.springframework.stereotype.Service;

/**
* @Title: OnlineMsgServiceImpl
* @Description: TODO 在线留言管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@Service
public class OnlineMsgServiceImpl extends BaseServiceImpl<OnlineMsg> implements OnlineMsgService{

	private final OnlineMsgMapper mapper;

    public OnlineMsgServiceImpl(OnlineMsgMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Integer insert(OnlineMsg entity) {
        entity.setWhoForInsert("");
        return mapper.insert(entity);
    }

    @Override
	public BaseMapper<OnlineMsg> getMapper(){
		return mapper;
	}

}
