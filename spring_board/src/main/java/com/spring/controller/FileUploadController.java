package com.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.BoardAttachVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Controller
public class FileUploadController {
	
//	private static final Logger logger=
//			LoggerFactory.getLogger(FileUploadController.class);
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax 폼 요청");
	}
	
	@PostMapping(value="/uploadAjax",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("파일 업로드 요청");
		
		String uploadPath="d:\\upload";	
		
		//d:\\upload 안에  년/월/일 폴더 생성하기 
		String uploadFolderPath=getFolder();
		File uploadFolder = new File(uploadPath,uploadFolderPath);
		
		if(!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		
		String uploadFileName=null;
		
		//첨부파일 목록 담아줄 리스트 생성
		List<BoardAttachVO> attachList=new ArrayList<BoardAttachVO>();
		
		for(MultipartFile multipartFile:uploadFile) {
		
		//IE 브라우저가 파일 경로까지 같이 가져오는 부분 잘라내기
		String uploadOriFileName=multipartFile.getOriginalFilename();
		uploadFileName=uploadOriFileName.substring(uploadOriFileName.lastIndexOf("\\")+1);
					
		UUID uuid=UUID.randomUUID();
		uploadFileName=uuid.toString()+"_"+uploadFileName;	
		
		BoardAttachVO attach=new BoardAttachVO();
		attach.setFileName(uploadOriFileName);
		attach.setUuid(uuid.toString());
		attach.setUploadPath(uploadFolderPath);				
		
			try {
				//서버에 파일 저장
				File saveFile=new File(uploadFolder,uploadFileName);
				
				if(checkImageType(saveFile)) {
					attach.setFileType(true);
					//썸네일 작업하기
					FileOutputStream thumbnail=
							new FileOutputStream(new File(uploadFolder,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),
									thumbnail,100,100);
					thumbnail.close();
				}				
				attachList.add(attach);
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {			
				e.printStackTrace();
			} catch (IOException e) {			
				e.printStackTrace();
			}		}
		
		//ResponseEntity : Http상태코드 + 보내고 싶은 값
		return new ResponseEntity<List<BoardAttachVO>>(attachList,HttpStatus.OK);		
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName "+fileName);
		
		File file=new File("d:\\upload\\"+fileName);
		ResponseEntity<byte[]> entity=null;
		
		try {
			HttpHeaders header=new HttpHeaders();
			header.add("content-Type", Files.probeContentType(file.toPath()));
			entity=new ResponseEntity<byte[]>
			(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return entity;
	}
	
	@GetMapping(value="/download",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(String fileName,
			@RequestHeader("User-Agent") String userAgent){
		log.info("fileName : "+fileName);
		
		Resource resource=new FileSystemResource("d:\\upload\\"+fileName);
		
		
		//파일 다운로드 시 uuid 값 제거 후 보여주기
		String resourceUidName=resource.getFilename();
		String resourceName=
				resourceUidName.substring(resourceUidName.indexOf("_")+1);
		//------
		
		HttpHeaders header=new HttpHeaders();
		
		String downloadName=null;
		try {
		
			if(userAgent.contains("Trident")) { //MS 11			
				downloadName=URLEncoder.encode(resourceName,"utf-8")
							.replaceAll("\\+", " ");			 
			}else if(userAgent.contains("Edge")) {
				downloadName=URLEncoder.encode(resourceName,"utf-8");
			}else { //Chrome 계열
				downloadName=new String(resourceName.getBytes("utf-8"),
						"ISO-8859-1");
			}
		
			header.add("Content-Disposition","attachment;fileName="+downloadName);
			
		}catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}	
		
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
	}
	
	
	@GetMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName,String type){
		log.info(" fileName : "+fileName+" type : "+type);
		
		File file=null;
		
		try {
			file=new File("d:\\upload\\"+URLDecoder.decode(fileName,"utf-8"));
			//일반파일 삭제/썸네일 삭제
			file.delete();
			
			if(type.equals("image")) {
				String largeName=file.getAbsolutePath().replace("s_", "");
				file=new File(largeName);
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
		
	}
	
	private boolean checkImageType(File file) {		
		try {//이미지 파일인지 확인
			String contentType = Files.probeContentType(file.toPath());	
			return contentType.startsWith("image");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	//폴더 생성시 필요한 폴더명
	private String getFolder() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String str=sdf.format(date);
		return str.replace("-", File.separator);
	}
}










