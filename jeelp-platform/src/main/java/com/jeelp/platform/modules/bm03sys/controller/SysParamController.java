package com.jeelp.platform.modules.bm03sys.controller;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.platform.modules.bm03sys.entity.SysParam;
import com.jeelp.platform.modules.bm03sys.service.SysParamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* @Title:               SysParamController.java
* @Description: TODO   	系统参数管理
* @author               
* @date                 2021-12-14
* @version              V1.0
*/
@RestController
@RequestMapping("/bm03sys/sysParam")
public class SysParamController {

	private final SysParamService service;

    public SysParamController(SysParamService service) {
        this.service = service;
    }

    @Log("查询【系统参数】")
    @PostMapping(value="page")
	public ResponseEntity<TabPage<SysParam>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除【系统参数】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【系统参数】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<SysParam>> batchSave(@RequestBody SaveModel<SysParam> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【系统参数】")
	@PostMapping(value="save")
    public ResponseEntity<SysParam> save(@RequestBody SysParam entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【系统参数】详情")
    @GetMapping(value="load")
    public ResponseEntity<SysParam> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
