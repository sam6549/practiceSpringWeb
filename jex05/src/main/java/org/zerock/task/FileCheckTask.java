package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	@Setter(onMethod_ = {@Autowired})
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str .replace("-", File.separator);
	}
	
//	1분마다 실행할 것
//	* * * * *
//	동일 프로세스를 10분마다 실행
//	*/10 * * * *
//	매시 15분마다 실행
//	15 * * * *
//	1시간마다 실행
//	0 * * * *
//	2시간마다 실행
//	0 */2 * * *
//	오전 11시와 오후 4시마다 실행
//	00 11,16 * * *
//	근무시간(9시 ~ 오후 6) 내 매시간 실행
//	-특정 작업이 근무 시간에만 실행되도록 설정하기 위해서는 시간을 09-18과 같은 표현을 사용합니다.
//	00 09-18 * * *
//	주말을 제외하고 근무 시간에만 매시간 실행
//	-특정 작업을 주중 근무일, 근무 시간에만 실행되도록 하기 위해서는 09-18과 같은 시간 표현과 1-5와 같은 요일 표현을 사용합니다.
//	00 09-18 * * 1-5
	
	
	//새벽 2시에 동작
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception{
		log.warn("File check Task run...................");
		
		log.warn(new Date());
		
		//file List in database
		//어제 날짜로 보관되는 모든 첨부파일의 목록을 가져온다.
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		//ready for check file in directory with database file list
		//BoardAttachVO 객체타입 -> java.nio.Paths 목록으로 변환
		List<Path> fileListPaths = fileList.stream()
				.map(vo->Paths.get("C:\\upload", vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName()))
				.collect(Collectors.toList());
		
		//image file has thumnail file
		//썸네일 목록도 필요하기 때문에
		fileList.stream().filter(vo->vo.isFileType() == true)
			.map(vo->Paths.get("C:\\upload", vo.getUploadPath(), "s_" +vo.getUuid()+"_"+vo.getFileName()))
			.forEach(p->fileListPaths.add(p));
		
		log.warn("==============================================");
		
		fileListPaths.forEach(p->log.warn(p));
		
		//file in yesterday directory
		//실제 폴더에 있는 파일들의 목록에서 데이터베이스에 없는 파일들을 찾아서 목록으로 준비한다.
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		File[] removeFiles = targetDir.listFiles(file->fileListPaths.contains(file.toPath()) == false);
		log.warn("----------------------------------------------");
		
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			//최종적으로 대상이 되는 목록을 삭제한다.
			file.delete();
		}
	}
}
