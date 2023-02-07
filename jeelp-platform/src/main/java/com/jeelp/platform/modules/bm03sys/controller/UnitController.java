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
import com.jeelp.platform.modules.bm03sys.entity.UnitEntity;
import com.jeelp.platform.modules.bm03sys.service.UnitService;

/**
* @Title:               UntiController.java
* @Description: TODO   	机构代码管理
* @author               
* @date                 2021-10-12
* @version              V1.0
*/
@RestController
@RequestMapping("/bm03sys/unit")
public class UnitController {

	private final UnitService service;

	public UnitController(UnitService service) {
		this.service = service;
	}

    @Log("查询【机构代码】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<UnitEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}
	
	@PostMapping(value="list")
	public ResponseEntity<List<UnitEntity>> list(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.findAll(param), HttpStatus.OK);
	}

    @Log("删除【机构代码】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【机构代码】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<UnitEntity>> batchSave(@RequestBody SaveModel<UnitEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【机构代码】")
	@PostMapping(value="save")
    public ResponseEntity<UnitEntity> save(@RequestBody UnitEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【机构代码】详情")
    @GetMapping(value="load")
    public ResponseEntity<UnitEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
