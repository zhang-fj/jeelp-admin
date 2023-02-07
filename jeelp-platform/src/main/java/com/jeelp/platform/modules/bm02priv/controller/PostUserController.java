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

import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.platform.modules.bm02priv.entity.PostUserEntity;
import com.jeelp.platform.modules.bm02priv.service.PostUserService;

/**
* @Title:               PostUserController.java
* @Description: TODO   	人员岗位管理
* @author               
* @date                 2021-10-08
* @version              V1.0
*/
@RestController
@RequestMapping("/bm02priv/postUser")
public class PostUserController {

	private final PostUserService service;
	
	public PostUserController(PostUserService service) {
		super();
		this.service = service;
	}

    @Log("查询【人员-岗位】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<PostUserEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除【人员-岗位】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("授权【人员-岗位】")
	@PostMapping(value="batchSave")
    public ResponseEntity<List<PostUserEntity>> batchSave(@RequestBody List<PostUserEntity> entitys){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entitys), HttpStatus.OK);
    }

    @Log("保存【人员-岗位】")
	@PostMapping(value="save")
    public ResponseEntity<PostUserEntity> save(@RequestBody PostUserEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【人员-岗位】详情")
    @GetMapping(value="load")
    public ResponseEntity<PostUserEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
