package com.jeelp.platform.modules.bm03sys.controller;

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
import com.jeelp.platform.modules.bm03sys.entity.SysCodeEntity;
import com.jeelp.platform.modules.bm03sys.service.SysCodeService;

/**
* @Title:               SysCodeController.java
* @Description: TODO   	字典索引管理
* @author               
* @date                 2021-10-06
* @version              V1.0
*/
@RestController
@RequestMapping("/sys/sysCode")
public class SysCodeController {

	private final SysCodeService service;

	public SysCodeController(SysCodeService service) {
		this.service = service;
	}

    @Log("查询【字典索引】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<SysCodeEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除【字典索引】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【字典索引】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<SysCodeEntity>> batchSave(@RequestBody SaveModel<SysCodeEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【字典索引】")
	@PostMapping(value="save")
    public ResponseEntity<SysCodeEntity> save(@RequestBody SysCodeEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【字典索引】详情")
    @GetMapping(value="load")
    public ResponseEntity<SysCodeEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
