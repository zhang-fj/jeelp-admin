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
import com.jeelp.platform.modules.bm02priv.entity.UserEntity;
import com.jeelp.platform.modules.bm02priv.service.FuncService;
import com.jeelp.platform.modules.bm02priv.service.UserService;
import com.jeelp.platform.modules.bm02priv.viewobject.EditPassword;

/**
* @Title:               UserController.java
* @Description: TODO   	用户管理管理
* @author               
* @date                 2021-09-22
* @version              V1.0
*/
@RestController
@RequestMapping("/bm02priv/user")
public class UserController {

	private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Log("查询【用户】")
    @PostMapping(value="page")
	public ResponseEntity<TabPage<UserEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除【用户】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【用户】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<UserEntity>> batchSave(@RequestBody SaveModel<UserEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【用户】")
	@PostMapping(value="save")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【用户】详情")
    @GetMapping(value="load")
    public ResponseEntity<UserEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
    @Log("获取用户基本信息")
    @GetMapping(value="getUserinfo")
    public ResponseEntity<Map<String,Object>> getUserinfo(String id){
    	return new ResponseEntity<>(service.getUserinfo(id), HttpStatus.OK);
    }
    
    @Log("修改【用户密码】")
	@PostMapping(value="editPassword")
    public ResponseEntity<Object> editPassword(@RequestBody EditPassword editPassword){
    	service.editPassword(editPassword);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
