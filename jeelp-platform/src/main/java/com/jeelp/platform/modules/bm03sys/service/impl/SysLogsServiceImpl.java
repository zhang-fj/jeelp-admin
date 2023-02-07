package com.jeelp.platform.modules.bm03sys.service.impl;

import org.springframework.stereotype.Service;

import com.jeelp.platform.common.logging.entity.LogInfo;
import com.jeelp.platform.common.logging.service.SaveLogService;
import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm03sys.entity.SysLogs;
import com.jeelp.platform.modules.bm03sys.mapper.SysLogsMapper;
import com.jeelp.platform.modules.bm03sys.service.SysLogsService;

/**
* @Title:              SysLogsServiceImpl
* @Description: TODO   系统日志管理
* @author              
* @date                2021-12-14
* @version             V1.0
*/
@Service
public class SysLogsServiceImpl extends BaseServiceImpl<SysLogs> implements SysLogsService, SaveLogService {

	private final SysLogsMapper mapper;

    public SysLogsServiceImpl(SysLogsMapper mapper) {
        this.mapper = mapper;
    }

	@Override
	public BaseMapper<SysLogs> getMapper(){
		return mapper;
	}

    @Override
    public void save(LogInfo log) {
        SysLogs syslog=new SysLogs();
        syslog.setAddress(log.getAddress());
        syslog.setBrowser(log.getBrowser());
        syslog.setDescription(log.getDescription());
        syslog.setExceptionDetail(log.getExceptionDetail());
        syslog.setLogType(log.getLogType());
        syslog.setMethod(log.getMethod());
        syslog.setParams(log.getParams());
        syslog.setRequestIp(log.getRequestIp());
        syslog.setTime(log.getTime());
        syslog.setUsername(log.getUsername());
        insert(syslog);
    }
}
