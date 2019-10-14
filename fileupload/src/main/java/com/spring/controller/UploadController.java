package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	private static final Logger logger=LoggerFactory.getLogger(UploadController.class);
	
//	@GetMapping("/upload")
//	public String uploadForm() {
//		logger.info("uploadForm 요청");
//		return "fileupload";
//	}
//	
//	@PostMapping("/upload")
//	public void uploadPost(MultipartFile[] uploadFile) {
//		String uploadPath="d:\\upload\\";
//		for(MultipartFile multipartFile:uploadFile) {
//		logger.info("Upload File Name "+multipartFile.getOriginalFilename());
//		logger.info("Upload File Size "+multipartFile.getSize());
//		
//		UUID uuid=UUID.randomUUID();
//		String uploadFileName=uuid.toString()+"_"+multipartFile.getOriginalFilename();
//		
//		//서버에 파일 저장
//		File saveFile = new File(uploadPath,uploadFileName);
//		try {
//			
//			multipartFile.transferTo(saveFile);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	}
//	
//	
//	@GetMapping(value="/download",
//			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	@ResponseBody
//	public ResponseEntity<Resource> download(String fileName,
//			@RequestHeader("User-Agent") String userAgent){
//		logger.info("fileName : "+fileName);
//		
//		Resource resource=new FileSystemResource("d:\\upload\\"+fileName);
//		String resourceName=resource.getFilename();
//		
//		HttpHeaders header=new HttpHeaders();
//		
//		String downloadName=null;
//		
//		try {
//			if(userAgent.contains("Trident")) {//MS 11
//				downloadName=URLEncoder.encode(resourceName,"utf-8".replaceAll("\\+",", "));
//			}else if(userAgent.contains("Edge")) {
//				downloadName=URLEncoder.encode(resourceName,"utf-8");
//			}else { //Chrome 계열
//				downloadName=new String(resourceName.getBytes("utf-8"),
//						"ISO-8859-1");
//			}
//			header.add("Content-Disposition", "attachment;fileName="+downloadName);
//		}
//		catch (UnsupportedEncodingException e) {			
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
//	}
	

	
}
