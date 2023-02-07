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
import com.jeelp.platform.modules.bm02priv.entity.PostRoleEntity;
import com.jeelp.platform.modules.bm02priv.service.PostRoleService;

/**
* @Title:               PostRoleController.java
* @Description: TODO   	岗位角色管理
* @author               
* @date                 2021-10-08
* @version              V1.0
*/
@RestController
@RequestMapping("/bm02priv/postRole")
public class PostRoleController {

	private final PostRoleService service;

	public PostRoleController(PostRoleService service) {
		this.service = service;
	}

    @Log("查询【岗位-角色】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<PostRoleEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除【岗位-角色】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【岗位-角色】")
	@PostMapping(value="batchSave")
    public ResponseEntity<List<PostRoleEntity>> batchSave(@RequestBody List<PostRoleEntity> entitys){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entitys), HttpStatus.OK);
    }

    @Log("保存【岗位-角色】")
	@PostMapping(value="save")
    public ResponseEntity<PostRoleEntity> save(@RequestBody PostRoleEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【岗位-角色】详情")
    @GetMapping(value="load")
    public ResponseEntity<PostRoleEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
