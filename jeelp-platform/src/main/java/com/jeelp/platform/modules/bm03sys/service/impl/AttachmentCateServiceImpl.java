package com.jeelp.platform.modules.bm03sys.service.impl;

import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm03sys.entity.AttachmentCate;
import com.jeelp.platform.modules.bm03sys.mapper.AttachmentCateMapper;
import com.jeelp.platform.modules.bm03sys.service.AttachmentCateService;

/**
* @Title:              AttachmentCateServiceImpl
* @Description: TODO   附件分类管理
* @author              
* @date                2022-01-22
* @version             V1.0
*/
@Service
public class AttachmentCateServiceImpl extends BaseServiceImpl<AttachmentCate> implements AttachmentCateService{

	private final AttachmentCateMapper mapper;
	
	public AttachmentCateServiceImpl(AttachmentCateMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<AttachmentCate> getMapper(){
		return mapper;
	}

}
