package com.jeelp.platform.modules.bm02priv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeelp.platform.common.logging.annotation.Log;
import org.apache.commons.lang3.StringUtils;
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
import com.jeelp.platform.common.utils.GenCodeUtil;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm02priv.entity.FuncEntity;
import com.jeelp.platform.modules.bm02priv.service.FuncService;
import com.jeelp.platform.modules.bm02priv.viewobject.MenuVo;

/**
* @Title:               FuncController.java
* @Description: TODO   	菜单管理
* @author               
* @date                 2021-09-22
* @version              V1.0
*/
@RestController
@RequestMapping("/bm02priv/func")
public class FuncController {

	private final FuncService service;

    public FuncController(FuncService service) {
        this.service = service;
    }

    @Log("查询菜单")
    @PostMapping(value="page")
	public ResponseEntity<TabPage<FuncEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}


    @PostMapping(value="list")
    public ResponseEntity<List<FuncEntity>> list(@RequestBody Map<String, Object> param){
        return new ResponseEntity<>(service.findAll(param), HttpStatus.OK);
    }

    @Log("删除菜单")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存菜单")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<FuncEntity>> batchSave(@RequestBody SaveModel<FuncEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存菜单")
	@PostMapping(value="save")
    public ResponseEntity<FuncEntity> save(@RequestBody FuncEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看菜单详情")
    @GetMapping(value="load")
    public ResponseEntity<FuncEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }

    @GetMapping(value="menus")
    public ResponseEntity<List<MenuVo>> getMenus(String pid){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("pid", StringUtils.isNotBlank(pid)?pid:"-1");
    	if(!UserinfoUtils.getUserName().equals("ADMIN")){
    		params.put("userId",UserinfoUtils.getUserId());
    	}
    	return new ResponseEntity<>(service.getMenus(params), HttpStatus.OK);
    }

    @GetMapping(value="funcTree")
    public ResponseEntity<List<FuncEntity>> funcTree(String pid){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("pid", StringUtils.isNotBlank(pid)?pid:"-1");
    	return new ResponseEntity<>(service.tree(params), HttpStatus.OK);
    }
    
}
