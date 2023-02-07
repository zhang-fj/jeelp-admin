package com.jeelp.protal.modules.portal.rest;

import com.jeelp.platform.common.mybatis.model.TabPage;
import com.jeelp.platform.modules.bm03sys.entity.AttachmentFile;
import com.jeelp.platform.modules.bm03sys.service.AttachmentFileService;
import com.jeelp.protal.modules.admin.entity.InfoCate;
import com.jeelp.protal.modules.admin.entity.OnlineMsg;
import com.jeelp.protal.modules.admin.entity.Topic;
import com.jeelp.protal.modules.admin.entity.TopicInfo;
import com.jeelp.protal.modules.admin.service.InfoCateService;
import com.jeelp.protal.modules.admin.service.OnlineMsgService;
import com.jeelp.protal.modules.admin.service.TopicInfoService;
import com.jeelp.protal.modules.admin.service.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProtalController.java
 * @Description TODO
 * @createTime 2022年03月19日 21:51:00
 */
@RestController
@RequestMapping("/protal")
public class ProtalController {

    private final InfoCateService infoCateService;

    private final TopicService topicService;

    private final AttachmentFileService attachmentFileService;

    private final TopicInfoService topicInfoService;

    private final OnlineMsgService onlineMsgService;

    public ProtalController(InfoCateService infoCateService, TopicService topicService, TopicInfoService topicInfoService, OnlineMsgService onlineMsgService,AttachmentFileService attachmentFileService) {
        this.infoCateService = infoCateService;
        this.topicService = topicService;
        this.topicInfoService = topicInfoService;
        this.onlineMsgService = onlineMsgService;
        this.attachmentFileService = attachmentFileService;
    }

    /**
     * 获取导航栏目
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="nav/topics")
    public ResponseEntity<List<Topic>> navTopics(@RequestBody Map<String, Object> param) throws Exception{
        param.put("navigateMenu",1);
        return new ResponseEntity(topicService.findAll(param), HttpStatus.OK);
    }

    /**
     * 获取首页栏目
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="home/topics")
    public ResponseEntity<List<Topic>> homeTopics(@RequestBody Map<String, Object> param) throws Exception{
        param.put("homeDisplay",1);
        param.put("sortName","HOME_INDEX");
        return new ResponseEntity(topicService.findAll(param), HttpStatus.OK);
    }

    /**
     * 根据栏目Id 获取栏目
     * @param id
     * @return
     */
    @GetMapping(value="topic")
    public ResponseEntity<Topic> load(String id){
        return new ResponseEntity(topicService.selectByPK(id), HttpStatus.OK);
    }

    /**
     * 根据栏目id获取信息分类
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="cates")
    public ResponseEntity<List<InfoCate>> cates(@RequestBody Map<String, Object> param) throws Exception{
        return new ResponseEntity(infoCateService.findAll(param), HttpStatus.OK);
    }

    /**
     *  获取根据栏目，分类获取栏目信息
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value="infos")
    public ResponseEntity<TabPage<TopicInfo>> infos(@RequestBody Map<String, Object> param) throws Exception{
        param.put("status","1");
        return new ResponseEntity(topicInfoService.selectForPage(param), HttpStatus.OK);
    }

    /**
     * 根据信息id获取信息
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value="info/{id}")
    public ResponseEntity<TopicInfo> info(@PathVariable("id") String id) throws Exception{
        return new ResponseEntity(topicInfoService.selectByPK(id), HttpStatus.OK);
    }

    /**
     * 获取附件列表
     * @param param
     * @return
     */
    @PostMapping(value="files")
    public ResponseEntity<TabPage<AttachmentFile>> files(@RequestBody Map<String, Object> param){
        return new ResponseEntity<>(attachmentFileService.selectForPage(param), HttpStatus.OK);
    }

    /**
     * 文件下载
     * @param fileId
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value="download")
    public String download(String fileId, HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=utf-8");
        AttachmentFile file = attachmentFileService.selectByPK(fileId);;
        BufferedInputStream bis = null;

        if(file != null){

            BufferedOutputStream bos = null;

            String downLoadPath = file.getStorePath()+ File.separator+file.getStoreFileName();  //注意不同系统的分隔符

            try {
                bis = bis==null ? new BufferedInputStream(new FileInputStream(downLoadPath)) : bis;
                response.setContentType(file.getContentType());
                response.setHeader("Content-Length", String.valueOf(bis.available()));
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null)
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (bos != null)
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return null;
    }

    /**
     * 在线留言
     * @param entity
     * @return
     * @throws Exception
     */
    @PostMapping(value="online/msg")
    public ResponseEntity<OnlineMsg> save(@RequestBody OnlineMsg entity) throws Exception{
        return new ResponseEntity(onlineMsgService.saveOrUpdate(entity), HttpStatus.OK);
    }

}
