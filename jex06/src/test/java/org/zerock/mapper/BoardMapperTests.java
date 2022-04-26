package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardMapperTests {
	
	//테스트 전에 해당 번호의 게시물이 존재 확인
	private Long[] bnoArr = {3145745L, 3145744L, 3145743L, 3145742L, 3145741L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	@Test
//	public void testCreate() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			ReplyVO vo = new ReplyVO();
//			
//			//게시물의 번호
//			vo.setBno(bnoArr[i % 5]);
//			vo.setReply("댓글 테스트 "+ i);
//			vo.setReplyer("replyer" + i);
//			
//			mapper.insert(vo);
//			
//		});
//	}
//	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	@Test
//	public void testRead() {
//		
//		Long targetRno = 5L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		log.info(vo);
//	}
	
//	@Test
//	public void testDelete() {
//		Long targetRno = 1L;
//		
//		int result = mapper.delete(targetRno);
//		
//		log.info("result :" + result);
//	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("update reply ");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: "+count);
	}
}
