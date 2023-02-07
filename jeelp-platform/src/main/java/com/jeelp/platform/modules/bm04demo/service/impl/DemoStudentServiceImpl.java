package com.jeelp.platform.modules.bm04demo.service.impl;

import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm04demo.entity.DemoStudent;
import com.jeelp.platform.modules.bm04demo.mapper.DemoStudentMapper;
import com.jeelp.platform.modules.bm04demo.service.DemoStudentService;

/**
* @Title:              DemoStudentServiceImpl
* @Description: TODO   学生演示管理
* @author              
* @date                2022-01-25
* @version             V1.0
*/
@Service
public class DemoStudentServiceImpl extends BaseServiceImpl<DemoStudent> implements DemoStudentService{

	private final DemoStudentMapper mapper;
	
	public DemoStudentServiceImpl(DemoStudentMapper mapper){
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<DemoStudent> getMapper(){
		return mapper;
	}

}
