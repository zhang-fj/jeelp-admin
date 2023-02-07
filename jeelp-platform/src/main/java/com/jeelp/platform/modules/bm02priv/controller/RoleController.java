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
import com.jeelp.platform.modules.bm02priv.entity.FuncEntity;
import com.jeelp.platform.modules.bm02priv.entity.RoleEntity;
import com.jeelp.platform.modules.bm02priv.service.RoleService;

/**
* @Title:               RoleController.java
* @Description: TODO   	角色管理管理
* @author               
* @date                 2021-10-07
* @version              V1.0
*/
@RestController
@RequestMapping("/bm02priv/role")
public class RoleController {

	private final RoleService service;

	public RoleController(RoleService service) {
		super();
		this.service = service;
	}

    @Log("查询【角色】")
    @PostMapping(value="page")
	public ResponseEntity<TabPage<RoleEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

    @Log("删除【角色】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【角色】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<RoleEntity>> batchSave(@RequestBody SaveModel<RoleEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【角色】")
	@PostMapping(value="save")
    public ResponseEntity<RoleEntity> save(@RequestBody RoleEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看【角色】详情")
    @GetMapping(value="load")
    public ResponseEntity<RoleEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }

    @Log("授权【角色-菜单】")
    @PostMapping(value="auth")
    public ResponseEntity<List<FuncEntity>> auth(@RequestBody Map<String,Object> params){
    	service.auth(params);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping(value="getAuthFuncIds")
    public ResponseEntity<List<String>> getAuthFuncIds(String roleId){
    	return new ResponseEntity<>(service.getAuthFuncIds(roleId), HttpStatus.OK);
    }
    
}
