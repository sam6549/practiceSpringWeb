package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
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

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.AttachFileDTO;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list");
//		model.addAttribute("list", service.getList());
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list: "+ cri);
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri,123));
		
		int total = service.getTotal(cri);
		
		log.info("total: "+total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("============================");
		log.info("register: "+ board);
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
			
		}
		log.info("============================");
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register page ");

	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno,  @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or /modify");
		model.addAttribute("board", service.get(bno));
	}
	
//	@PostMapping("/modify")
//	public String modify(BoardVO board, RedirectAttributes rttr) {
//		log.info("modify: "+board);
//		
//		if(service.modify(board)) {
//			rttr.addFlashAttribute("result", "success");
//			
//		}
//		
//		return "redirect:/board/list";
//	}
	
	//
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify: "+board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		//추가
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
		
		//자바스크립트 안될 경우에 추가할 수 있음
		//return "redirect:/board/list" + cri.getListLink();
	}
	
//	@PostMapping("/remove")
//	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
//		log.info("remove... bno["+bno+"]");
//		
//		if(service.remove(bno)) {
//			rttr.addAttribute("result", "success");
//			
//		}
//		return "redirect:/board/list";
//	}
	
//	@PostMapping("/remove")
//	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
//		log.info("remove... bno["+bno+"]");
//		
//		if(service.remove(bno)) {
//			rttr.addAttribute("result", "success");
//			
//		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		//추가
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
//		
//		return "redirect:/board/list";
//		
//		//자바스크립트 안될 경우에 추가할 수 있음
//		//return "redirect:/board/list" + cri.getListLink();
//	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove : " + bno);
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		if(service.remove(bno)) {
			
			//delete Attach Files
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result","success");
		}
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		//make folder ------4
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		//make yyyy/MM/dd folder
		
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				//check image type file
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					
					thumbnail.close();
				}
				
				//add to List
				list.add(attachDTO);
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		
		log.info("deleteFile: "+fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				
				log.info("largeFileName: "+ largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName: " + fileName);
		
		File file = new File("C:\\upload\\" + fileName);
		
		log.info("file: " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping(value="/getAttachList", produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		log.info("getAttachList: " + bno);
		
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-agent") String userAgent, String fileName){
		
		log.info("download file: " + fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		log.info("resource: " + resource);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		String resourceName = resource.getFilename();
		
		//remove UUID
		String resourceOriginalName= resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			
			if(userAgent.contains("trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("//+",  " ");
				
			}else if(userAgent.contains("edge")) {
				log.info("Edge browser");
				
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
				log.info("edge name: " + downloadName);
			}else {
				
				log.info("Chrome borwser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			log.info("downloadName: "+ downloadName);
			
			//uuid가 빠진채로 다운로드 준비함
			headers.add("Content-Disposition", "attachment; filename=" + downloadName); 
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			log.info("delete attach Files............................");
			log.info(attachList);
			
			attachList.forEach(attach->{
				try {
					Path file = Paths.get("C:\\upload\\"+ attach.getUploadPath());
					Files.deleteIfExists(file);
					if(Files.probeContentType(file).startsWith("image")) {
						Path thumbnail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
						Files.delete(thumbnail);
						
					}
				}catch (Exception e) {
					log.error("delete file error" + e.getMessage());
				}//end catch
			
			});//end for each
			
		}
	}
}
