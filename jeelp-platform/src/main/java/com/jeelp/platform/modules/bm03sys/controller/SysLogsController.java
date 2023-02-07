package com.jeelp.platform.modules.bm03sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeelp.platform.common.logging.annotation.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm03sys.entity.SysLogs;
import com.jeelp.platform.modules.bm03sys.service.SysLogsService;

/**
* @Title:               SysLogsController.java
* @Description: TODO   	系统日志管理
* @author               
* @date                 2021-12-14
* @version              V1.0
*/
@RestController
@RequestMapping("/bm03sys/sysLogs")
public class SysLogsController {

	private final SysLogsService service;

    public SysLogsController(SysLogsService service) {
        this.service = service;
    }

//    @Log("查询【系统日志】")
    @PostMapping(value="page")
	public ResponseEntity<TabPage<SysLogs>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}
    
    @PostMapping(value="userlogs")
	public ResponseEntity<TabPage<SysLogs>> userlogs(@RequestBody Map<String, Object> param){
    	param.put("username",UserinfoUtils.getUserName());
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}


    @Log("删除【系统日志】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("清空【错误日志】")
    @DeleteMapping(value="delError")
    public ResponseEntity<Object> delError(){
        Map<String,Object> params = new HashMap<>();
        params.put("logType","ERROR");
        service.deleteByParams(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("清空【系统日志】")
    @DeleteMapping(value="delInfo")
    public ResponseEntity<Object> delInfo(){
        Map<String,Object> params = new HashMap<>();
        params.put("logType","INFO");
        service.deleteByParams(params);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("批量保存【系统日志】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<SysLogs>> batchSave(@RequestBody SaveModel<SysLogs> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【系统日志】")
	@PostMapping(value="save")
    public ResponseEntity<SysLogs> save(@RequestBody SysLogs entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【系统日志】详情")
    @GetMapping(value="load")
    public ResponseEntity<SysLogs> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
