package com.jeelp.platform.modules.bm03sys.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm03sys.entity.AttachmentFile;
import com.jeelp.platform.modules.bm03sys.mapper.AttachmentFileMapper;
import com.jeelp.platform.modules.bm03sys.service.AttachmentFileService;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;

/**
* @Title:              AttachmentFileServiceImpl
* @Description: TODO   附件上传管理
* @author              
* @date                2022-01-22
* @version             V1.0
*/
@Service
public class AttachmentFileServiceImpl extends BaseServiceImpl<AttachmentFile> implements AttachmentFileService{

	private final AttachmentFileMapper mapper;
	
	public AttachmentFileServiceImpl(AttachmentFileMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<AttachmentFile> getMapper(){
		return mapper;
	}

	@Override
	public Integer deleteByIds(List<Object> ids) {
		
		if(ids != null){
			for(Object id:ids){
				AttachmentFile file = mapper.selectByPK(id);
				if(file != null){
					FileUtil.del(new File(file.getStorePath()+File.separator+file.getStoreFileName()));
					mapper.delete(file);
				}
			}
		}
		return 0;
	}

}
