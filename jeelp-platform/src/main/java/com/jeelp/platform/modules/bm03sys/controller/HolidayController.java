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
import com.jeelp.platform.modules.bm03sys.entity.Holiday;
import com.jeelp.platform.modules.bm03sys.service.HolidayService;
import com.jeelp.platform.modules.bm03sys.viewobject.HolidayModel;

/**
* @Title:               HolidayController.java
* @Description: TODO   	假期规则管理
* @author               
* @date                 2022-01-15
* @version              V1.0
*/
@RestController
@RequestMapping("/bm03sys/holiday")
public class HolidayController {

	private final HolidayService service;
	
	public HolidayController(HolidayService service) {
		super();
		this.service = service;
	}

	@PostMapping(value="page")
	public ResponseEntity<TabPage<Holiday>> page(@RequestBody Map<String, Object> param){
		return new ResponseEntity<>(service.selectForPage(param), HttpStatus.OK);
	}

	@DeleteMapping(value="del")
	public ResponseEntity<Object> del(@RequestBody List<Object> ids){
		service.deleteByIds(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value="batchSave")
    public ResponseEntity<SaveModel<Holiday>> batchSave(@RequestBody SaveModel<Holiday> entity){
    	return new ResponseEntity<>(service.batchSaveOrUpdate(entity), HttpStatus.OK);
    }

	@PostMapping(value="save")
    public ResponseEntity<Holiday> save(@RequestBody Holiday entity){
    	return new ResponseEntity<>(service.saveOrUpdate(entity), HttpStatus.OK);
    }

    @GetMapping(value="load")
    public ResponseEntity<Holiday> load(String id){
    	return new ResponseEntity<>(service.selectByPK(id), HttpStatus.OK);
    }
    
    /**
     * 初始化假期规则
     * @param params {startYear:2010,endYear:2022}
     * @return
     */
    @PostMapping(value="initHoliday")
    public ResponseEntity<String> initHoliday(@RequestBody Map<String,Object> params){
    	return new ResponseEntity<>(service.initHoliday((String)params.get("startYear"),(String)params.get("endYear")), HttpStatus.OK);
    }
    
    /**
     * 设置假期规则
     * @return
     */
    @PostMapping(value="setHoliday")
    public ResponseEntity<String> setHoliday(@RequestBody Map<String,Object> params){
    	return new ResponseEntity<>(service.setHoliday(
    			(String)params.get("startDate"),
    			(String)params.get("endDate"),
    			(String)params.get("sort"),
    			(String)params.get("holidayType")
    		), HttpStatus.OK);
    }
    
    /**
     * 获取某月份假期规则
     * @param params {startYear:2010,endYear:2022}
     * @return
     */
    @PostMapping(value="getMonthHoliday")
    public ResponseEntity<List<HolidayModel>> getMonthHoliday(@RequestBody Map<String,Object> params){
    	return new ResponseEntity<>(service.getMonthHoliday((String)params.get("year"),(String)params.get("month")), HttpStatus.OK);
    }
    
    /**
     * 获取某月份假期规则
     * @param params {startYear:2010,endYear:2022}
     * @return
     */
    @PostMapping(value="computeWorkday")
    public ResponseEntity<Map<String,Object>> computeWorkday(@RequestBody Map<String,Object> params){
    	return new ResponseEntity<>(service.computeWorkday((String)params.get("startDate"),(String)params.get("endDate")), HttpStatus.OK);
    }
    
    
}
