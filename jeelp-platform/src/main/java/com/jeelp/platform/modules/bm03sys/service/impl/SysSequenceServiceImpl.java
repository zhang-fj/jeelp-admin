package com.jeelp.platform.modules.bm03sys.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.common.utils.GenCodeUtil;
import com.jeelp.platform.common.utils.SequenceUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm03sys.entity.SysParam;
import com.jeelp.platform.modules.bm03sys.entity.SysSequence;
import com.jeelp.platform.modules.bm03sys.mapper.SysSequenceMapper;
import com.jeelp.platform.modules.bm03sys.service.SysSequenceService;

/**
* @Title:              SysSequenceServiceImpl
* @Description: TODO    序列号管理
* @author              
* @date                2022-01-25
* @version             V1.0
*/
@Service
public class SysSequenceServiceImpl extends BaseServiceImpl<SysSequence> implements SysSequenceService{

	private final SysSequenceMapper mapper;
	
	public SysSequenceServiceImpl(SysSequenceMapper mapper){
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<SysSequence> getMapper(){
		return mapper;
	}
	
	@Override
	public Integer insert(SysSequence entity) {
		entity.setWhoForInsert(UserinfoUtils.getUserId());
		entity.setId(SequenceUtil.getNextSequence("0006",4));
		Integer result = mapper.insert(entity);
		return result;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String getNextSequence(String groupId,int length,String year,String month,String space){
		String code  =  "";
		
		SysSequence entity = selectByPK(groupId);
		
		// 如果业务前缀不为空，则拼接业务前缀
		if(StringUtils.isNotBlank(entity.getBizPrefix())){
			code = entity.getBizPrefix();
		}
		// 如果间隔符不为空，则拼接间隔符
		if(StringUtils.isNotBlank(space)){
			code = code + space;
		}
		// 如果年度不为空，则拼接年度
		if(StringUtils.isNotBlank(year)){
			code = code + year;
		}
		// 如果间隔符不为空，则拼接间隔符
		if(StringUtils.isNotBlank(space)){
			code = code + space;
		}
		// 如果月份不为空，则拼接月份
		if(StringUtils.isNotBlank(month)){
			code = code + month;
		}
		// 如果间隔符不为空，则拼接间隔符
		if(StringUtils.isNotBlank(space)){
			code = code + space;
		}
		// 如果间隔符不为空，则拼接间隔符
		if(StringUtils.isNotBlank(space)){
			code = code + space;
		}
		
		// 获取下一个序列号
		BigDecimal sequence = entity.getSeqval().add(new BigDecimal(1));
		
		// 拼接下一个序列号，拼接前，根据长度为序列号补零
		code = code + StringUtils.leftPad(sequence.toString(),length,"0") ;
		
		// 重置当前序号
		entity.setSeqval(sequence);
		update(entity);
		
		return code;
		
	}

}
