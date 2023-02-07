package com.jeelp.protal.modules.admin.rest;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.protal.modules.admin.entity.OnlineMsg;
import com.jeelp.protal.modules.admin.service.OnlineMsgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* @Title: OnlineMsgController.java
* @Description: TODO 在线留言管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@RestController
@RequestMapping("/admin/onlinemsg")
public class OnlineMsgController {

	private final OnlineMsgService service;

    public OnlineMsgController(OnlineMsgService service) {
        this.service = service;
    }

    @Log("查询【在线留言管理】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<OnlineMsg>> page(@RequestBody Map<String, Object> param) throws Exception{
		return new ResponseEntity(service.selectForPage(param), HttpStatus.OK);
	}
	
	@Log("保存【在线留言管理】")
	@PostMapping(value="save")
	public ResponseEntity<OnlineMsg> save(@RequestBody OnlineMsg entity) throws Exception{
		return new ResponseEntity(service.saveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("批量保存【在线留言管理】")
	@PostMapping(value="batchSave")
	public ResponseEntity<SaveModel<OnlineMsg>> batchSave(@RequestBody SaveModel<OnlineMsg> entity){
	    return new ResponseEntity(service.batchSaveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("删除【在线留言管理】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@Log("加载【在线留言管理】")
	@GetMapping(value="load")
	public ResponseEntity<OnlineMsg> load(String id){
		return new ResponseEntity(service.selectByPK(id), HttpStatus.OK);
	}

}