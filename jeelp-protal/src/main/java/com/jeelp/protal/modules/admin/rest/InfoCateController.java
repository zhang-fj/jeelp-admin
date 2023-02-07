package com.jeelp.protal.modules.admin.rest;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.common.mybatis.model.SaveModel;
import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.protal.modules.admin.entity.InfoCate;
import com.jeelp.protal.modules.admin.service.InfoCateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* @Title: InfoCateController.java
* @Description: TODO 信息分类管理
* @author 
* @date 2022-03-19
* @version V1.0
*/
@RestController
@RequestMapping("/admin/infoCate")
public class InfoCateController {

	private final InfoCateService service;

    public InfoCateController(InfoCateService service) {
        this.service = service;
    }

    @Log("查询【信息分类】")
	@PostMapping(value="page")
	public ResponseEntity<TabPage<InfoCate>> page(@RequestBody Map<String, Object> param) throws Exception{
		return new ResponseEntity(service.selectForPage(param), HttpStatus.OK);
	}
	
	@Log("保存【信息分类】")
	@PostMapping(value="save")
	public ResponseEntity<InfoCate> save(@RequestBody InfoCate entity) throws Exception{
		return new ResponseEntity(service.saveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("批量保存【信息分类】")
	@PostMapping(value="batchSave")
	public ResponseEntity<SaveModel<InfoCate>> batchSave(@RequestBody SaveModel<InfoCate> entity){
	    return new ResponseEntity(service.batchSaveOrUpdate(entity), HttpStatus.OK);
	}
	
	@Log("删除【信息分类】")
	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@Log("加载【信息分类】")
	@GetMapping(value="load")
	public ResponseEntity<InfoCate> load(String id){
		return new ResponseEntity(service.selectByPK(id), HttpStatus.OK);
	}

}