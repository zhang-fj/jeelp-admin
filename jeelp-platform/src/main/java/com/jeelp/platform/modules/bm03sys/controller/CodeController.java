package com.jeelp.platform.modules.bm03sys.controller;

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
import com.jeelp.platform.modules.bm03sys.entity.CodeEntity;
import com.jeelp.platform.modules.bm03sys.service.CodeService;

/**
* @Title:               CodeController.java
* @Description: TODO   	字典明细管理
* @author               
* @date                 2021-10-06
* @version              V1.0
*/
@RestController
@RequestMapping("/sys/code")
public class CodeController {

	private final CodeService service;

	public CodeController(CodeService service) {
		this.service = service;
	}

    @Log("查询【字典】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<CodeEntity>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

	@PostMapping(value="list")
	public ResponseEntity<List<CodeEntity>> list(@RequestBody Map<String, Object> param){
		
		String likeCode = (String) param.get("likeCode");
		
		// 区县字典表
		if("T_CODE_CITY".equals(param.get("sysCode"))){
			if(StringUtils.isNotBlank(likeCode)){
				param.put("likeCode", likeCode.substring(0, 2));
			}
		// 城市字典表
		}else if("T_CODE_DISTRICT".equals(param.get("sysCode"))){
			if(StringUtils.isNotBlank(likeCode)){
				param.put("likeCode", likeCode.substring(0, 4));
			}
		} 
		
		return new ResponseEntity<>(service.findAll(param), HttpStatus.OK);
	}

    @Log("删除【字典】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody Map<String,Object> params){
		service.deleteByParams(params);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Log("批量保存【字典】")
	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<CodeEntity>> batchSave(@RequestBody SaveModel<CodeEntity> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("保存【字典】")
	@PostMapping(value="save")
    public ResponseEntity<CodeEntity> save(@RequestBody CodeEntity entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @Log("查看字典详情")
    @GetMapping(value="load")
    public ResponseEntity<CodeEntity> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
        
}
