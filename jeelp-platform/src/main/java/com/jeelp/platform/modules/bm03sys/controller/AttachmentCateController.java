package com.jeelp.platform.modules.bm03sys.controller;

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
import com.jeelp.platform.modules.bm03sys.entity.AttachmentCate;
import com.jeelp.platform.modules.bm03sys.service.AttachmentCateService;

/**
* @Title:               AttachmentCateController.java
* @Description: TODO   	附件分类管理
* @author               
* @date                 2022-01-22
* @version              V1.0
*/
@RestController
@RequestMapping("/bm03sys/attachmentCate")
public class AttachmentCateController {

	private final AttachmentCateService service;
	
	public AttachmentCateController(AttachmentCateService service) {
		super();
		this.service = service;
	}

	@PostMapping(value="page")
	public ResponseEntity<TabPage<AttachmentCate>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<AttachmentCate>> batchSave(@RequestBody SaveModel<AttachmentCate> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

	@PostMapping(value="save")
    public ResponseEntity<AttachmentCate> save(@RequestBody AttachmentCate entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @GetMapping(value="load")
    public ResponseEntity<AttachmentCate> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
