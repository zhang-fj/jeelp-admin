package com.jeelp.platform.modules.bm02priv.controller;

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
import com.jeelp.platform.modules.bm02priv.entity.PostEntity;
import com.jeelp.platform.modules.bm02priv.service.PostService;

/**
* @Title:               PostController.java
* @Description: TODO   	岗位管理管理
* @author               
* @date                 2021-10-07
* @version              V1.0
*/
@RestController
@RequestMapping("/bm02priv/post")
public class PostController {

	private final PostService service;
	
	public PostController(PostService service) {
		this.service = service;
	}

    @Log("查询岗位")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<PostEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除岗位")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存岗位")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<PostEntity>> batchSave(@RequestBody SaveModel<PostEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存岗位")
	@PostMapping(value="save")
    public ResponseEntity<PostEntity> save(@RequestBody PostEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看岗位详情")
    @GetMapping(value="load")
    public ResponseEntity<PostEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
