package com.jeelp.protal.modules.admin.service.impl;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.protal.modules.admin.entity.InfoCate;
import com.jeelp.protal.modules.admin.mapper.InfoCateMapper;
import com.jeelp.protal.modules.admin.service.InfoCateService;
import org.springframework.stereotype.Service;

/**
* @Title: InfoCateServiceImpl
* @Description: TODO 信息分类管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@Service
public class InfoCateServiceImpl extends BaseServiceImpl<InfoCate> implements InfoCateService {

	private final InfoCateMapper mapper;

    public InfoCateServiceImpl(InfoCateMapper mapper) {
        this.mapper = mapper;
    }

    @Override
	public BaseMapper<InfoCate> getMapper(){
		return mapper;
	}

}
