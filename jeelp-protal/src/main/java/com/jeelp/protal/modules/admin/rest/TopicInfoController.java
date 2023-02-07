package com.jeelp.protal.modules.admin.rest;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.protal.modules.admin.entity.TopicInfo;
import com.jeelp.protal.modules.admin.service.TopicInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* @Title: TopicInfoController.java
* @Description: TODO 栏目信息管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@RestController
@RequestMapping("/admin/topicInfo")
public class TopicInfoController {

	private final TopicInfoService service;

    public TopicInfoController(TopicInfoService service) {
        this.service = service;
    }

    @Log("查询【栏目信息】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<TopicInfo>> page(@RequestBody Map<String, Object> param) throws Exception{
		return new ResponseEntity(service.selectForPage(param), HttpStatus.OK);
	}
	
	@Log("保存【栏目信息】")
	@PostMapping(value="save")
	public ResponseEntity<TopicInfo> save(@RequestBody TopicInfo entity) throws Exception{
		return new ResponseEntity(service.saveOrUpdate(entity), HttpStatus.OK);
	}

	@Log("批量保存【栏目信息】")
	@PostMapping(value="batchSave")
	public ResponseEntity<SaveModel<TopicInfo>> batchSave(@RequestBody SaveModel<TopicInfo> entity){
	    return new ResponseEntity(service.batchSaveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("删除【栏目信息】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
	    return new ResponseEntity(HttpStatus.OK);
	}

    @Log("发布【栏目信息】")
    @PostMapping(value="release")
    public ResponseEntity<Object> release(@RequestBody List<String> ids){
        service.release(ids);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("撤销发布【栏目信息】")
    @PostMapping(value="back")
    public ResponseEntity<Object> back(@RequestBody List<String> ids){
        service.back(ids);
        return new ResponseEntity(HttpStatus.OK);
    }
	
	@Log("加载【栏目信息】")
	@GetMapping(value="load")
	public ResponseEntity<TopicInfo> load(String id){
		return new ResponseEntity(service.selectByPK(id), HttpStatus.OK);
	}

}