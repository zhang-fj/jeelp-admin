package com.jeelp.protal.modules.admin.rest;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.protal.modules.admin.entity.Topic;
import com.jeelp.protal.modules.admin.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* @Title: TopicController.java
* @Description: TODO 栏目管理管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@RestController
@RequestMapping("/admin/topic")
public class TopicController {

	private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @Log("查询【栏目管理】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<Topic>> page(@RequestBody Map<String, Object> param) throws Exception{
		return new ResponseEntity(service.selectForPage(param), HttpStatus.OK);
	}
	
	@Log("保存【栏目管理】")
	@PostMapping(value="save")
	public ResponseEntity<Topic> save(@RequestBody Topic entity) throws Exception{
		return new ResponseEntity(service.saveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("批量保存【栏目管理】")
	@PostMapping(value="batchSave")
	public ResponseEntity<SaveModel<Topic>> batchSave(@RequestBody SaveModel<Topic> entity){
	    return new ResponseEntity(service.batchSaveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("删除【栏目管理】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@Log("加载【栏目管理】")
	@GetMapping(value="load")
	public ResponseEntity<Topic> load(String id){
		return new ResponseEntity(service.selectByPK(id), HttpStatus.OK);
	}

}