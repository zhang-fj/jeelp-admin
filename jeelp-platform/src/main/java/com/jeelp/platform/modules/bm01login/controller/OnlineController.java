package com.jeelp.platform.modules.bm01login.controller;

import java.util.Set;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.modules.bm01login.service.OnlineUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeelp.platform.common.utils.EncryptUtils;


@RestController
@RequestMapping("/online")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    public OnlineController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @Log("查询在线用户")
    @GetMapping
    public ResponseEntity<Object> query(String filter, int page,int size) {
        return new ResponseEntity<>(onlineUserService.getAll(filter, PageRequest.of(page, size)), HttpStatus.OK);
    }

    @Log("强退用户")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
        	key = EncryptUtils.desDecrypt(key);
            onlineUserService.logout(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
