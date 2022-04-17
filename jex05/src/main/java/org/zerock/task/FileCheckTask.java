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
		
		return str.replace("-",  File.separator);
	}
	
	/*cron설정
		123456 7
		0*****(*)
		1:seconds(0~59)
		2:minutes(0~59)
		3:hours(0~23)
		4:day(1~31)
		5:months(1~12)
		6:day of week(1~7)
		7:year(optional)
	 * 
	 * */
	
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
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles()throws Exception{
		
		log.warn("file Check Task Run.......");
		log.warn(new Date());
		
		//file list in database
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		//ready for check file in directory with database file list
		List<Path> fileListPaths = fileList.stream()
				.map(vo->Paths.get("C:\\upload", vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName()))
				.collect(Collectors.toList());
		
		//image file has thumnail file
		fileList.stream().filter(vo -> vo.isFileType() == true)
			.map(vo->Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid()+"_"+vo.getFileName()))
			.forEach(p->fileListPaths.add(p));
		
		log.warn("===================================");
		
		fileListPaths.forEach(p->log.warn(p));
		
		//files in yesterday directory
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("------------------------------------");
		
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	}
}
