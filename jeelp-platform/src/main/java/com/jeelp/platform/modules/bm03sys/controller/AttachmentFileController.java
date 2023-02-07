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
import com.jeelp.platform.modules.bm03sys.entity.AttachmentFile;
import com.jeelp.platform.modules.bm03sys.service.AttachmentFileService;

/**
* @Title:               AttachmentFileController.java
* @Description: TODO   	附件上传管理
* @author               
* @date                 2022-01-22
* @version              V1.0
*/
@RestController
@RequestMapping("/bm03sys/attachmentFile")
public class AttachmentFileController {

	private final AttachmentFileService service;

	public AttachmentFileController(AttachmentFileService service) {
		super();
		this.service = service;
	}

	@PostMapping(value="page")
	public ResponseEntity<TabPage<AttachmentFile>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<AttachmentFile>> batchSave(@RequestBody SaveModel<AttachmentFile> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

	@PostMapping(value="save")
    public ResponseEntity<AttachmentFile> save(@RequestBody AttachmentFile entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @GetMapping(value="load")
    public ResponseEntity<AttachmentFile> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
}
