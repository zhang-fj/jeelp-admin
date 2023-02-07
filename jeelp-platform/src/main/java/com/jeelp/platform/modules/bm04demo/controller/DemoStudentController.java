package com.jeelp.platform.modules.bm04demo.controller;

import java.util.List;
import java.util.Map;

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
import com.jeelp.platform.modules.bm04demo.entity.DemoStudent;
import com.jeelp.platform.modules.bm04demo.service.DemoStudentService;

/**
* @Title:               DemoStudentController.java
* @Description: TODO   	学生演示管理
* @author               
* @date                 2022-01-25
* @version              V1.0
*/
@RestController
@RequestMapping("/bm04demo/demoStudent")
public class DemoStudentController {

	private final DemoStudentService service;

	public DemoStudentController(DemoStudentService service){
		this.service = service;
	}
	
	@PostMapping(value="page")
	public ResponseEntity<TabPage<DemoStudent>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<DemoStudent>> batchSave(@RequestBody SaveModel<DemoStudent> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

	@PostMapping(value="save")
    public ResponseEntity<DemoStudent> save(@RequestBody DemoStudent entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @GetMapping(value="load")
    public ResponseEntity<DemoStudent> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
