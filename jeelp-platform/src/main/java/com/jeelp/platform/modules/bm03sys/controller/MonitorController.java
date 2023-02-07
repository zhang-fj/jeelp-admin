package com.jeelp.platform.modules.bm03sys.controller;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.modules.bm03sys.service.MonitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/monitor")
public class MonitorController {

    private final MonitorService serverService;

    public MonitorController(MonitorService serverService) {
        this.serverService = serverService;
    }

    @GetMapping(value="query")
    public ResponseEntity<Object> query() {
        return new ResponseEntity<>(serverService.getServers(), HttpStatus.OK);
    }
}
