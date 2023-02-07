package com.jeelp.platform.modules.bm03sys.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeelp.platform.common.logging.annotation.Log;
import com.jeelp.platform.modules.bm03sys.entity.AttachmentCate;
import com.jeelp.platform.modules.bm03sys.entity.AttachmentFile;
import com.jeelp.platform.modules.bm03sys.service.AttachmentCateService;
import com.jeelp.platform.modules.bm03sys.service.AttachmentFileService;
import com.jeelp.platform.modules.bm03sys.service.SysParamService;

/**
* @Title:               AttachmentController.java
* @Description: TODO   	附件管理
* @author               
* @date                 2021-10-06
* @version              V1.0
*/
@RestController
@RequestMapping("/sys/attachment")
public class AttachmentController {
	
	private final AttachmentCateService service;
	
	private final AttachmentFileService fileService;
	
	private final SysParamService paramService;
	
	public AttachmentController(AttachmentCateService service,AttachmentFileService fileService,SysParamService paramService) {
		super();
		this.service = service;
		this.fileService = fileService;
		this.paramService = paramService;
	}

	/**
	 * 附件上传
	 * @param request
	 * @return
	 */
	@PostMapping(value="upload")
	public ResponseEntity<Object> upload(MultipartHttpServletRequest request) throws IOException {
    	
		// 获取 附件关联的业务ID
		String srcId = request.getParameter("srcId");
		// 获取 附件分类
		String attachmentType = request.getParameter("category");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cateCode", attachmentType);
		AttachmentCate cate = service.selectByPropertys(params);
		
		// 获取附件存储根路径 ，如果附件分类上为设置路径，则从系统参数中获取
		String catePath = paramService.getParamValue("0005");
		if(cate != null && StringUtils.isNotBlank(cate.getCatePath())){
		    catePath = cate.getCatePath();
		}
		if(catePath.lastIndexOf(File.separator)<0){
            catePath = catePath + File.separator;
        }
		// 获取附件
    	MultipartFile file = request.getFile("file");
    	// 获取附件名称
    	String fileName = file.getOriginalFilename();
    	String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
    	String storeFileName = UUID.randomUUID().toString();
    	String storePath = catePath+attachmentType;
    	// 获取附件大小
    	File filePath = new File(storePath, storeFileName);
    	if(!filePath.getParentFile().exists()){
    		filePath.getParentFile().mkdirs();
    	}
    	
    	AttachmentFile attachmentFile = new AttachmentFile();
        file.transferTo(filePath);

        attachmentFile.setContentType(file.getContentType());
        attachmentFile.setExt(fileExtName);
        attachmentFile.setOriFileName(fileName);
        attachmentFile.setStoreFileName(storeFileName);
        attachmentFile.setSrcId(srcId);
        attachmentFile.setType(attachmentType);
        attachmentFile.setStorePath(storePath);

        fileService.insert(attachmentFile);

		return new ResponseEntity<>(new HashMap<String, String>(1) {{
            put("fileId",attachmentFile.getFileid());
        }},HttpStatus.OK);
	}
	
	@GetMapping(value="download")
	public String download( String srcId ,String type,String fileId,String isImage,String view, HttpServletRequest request, HttpServletResponse response){
		
		response.setContentType("text/html;charset=utf-8");
		AttachmentFile file = null;
		BufferedInputStream bis = null;
		
		// 如果fileId存在根据fileId 附件信息
		if(StringUtils.isNotBlank(fileId)){
			file = fileService.selectByPK(fileId);
		}else{
			// 否则根据附件类型及业务id获取最新上传的附件
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("srcId", srcId);
			params.put("type",type);
			file = fileService.selectByPropertys(params);
		}
		
		// 当获取不到附件时，如果附件为图片类型，则获取默认图片
		if(file==null&&"true".equals(isImage)){
			
			file = new AttachmentFile();
			file.setContentType("image/png");
			file.setStoreFileName("avatar.png");
			file.setOriFileName("avatar.png");
			
			try {
				bis = new BufferedInputStream(new ClassPathResource("asset/head-image/avatar.png").getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(file != null){
			
			BufferedOutputStream bos = null;
			
			String downLoadPath = file.getStorePath()+File.separator+file.getStoreFileName();  //注意不同系统的分隔符
			
			try {
				bis = bis==null ? new BufferedInputStream(new FileInputStream(downLoadPath)) : bis;

				response.setContentType(file.getContentType());
				
				if("1".equals(view)){
					response.setHeader("Content-disposition", "inline; filename=" + new String(file.getOriFileName().getBytes("utf-8"), "ISO8859-1"));
				}else{
					response.setHeader("Content-disposition", "attachment; filename=" + new String(file.getOriFileName().getBytes("utf-8"), "ISO8859-1"));
				}
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
	
	
    
}
