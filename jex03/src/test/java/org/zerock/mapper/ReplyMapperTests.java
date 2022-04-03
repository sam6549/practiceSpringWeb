package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성하는 글");
//		board.setContent("새로 작성하는 내용");
//		board.setWriter("newble");
//		
//		mapper.insert(board);
//		
//		log.info(board);
//	}
	
//	@Test
//	public void testInsertSelectKey() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성하는 글 select key");
//		board.setContent("새로 작성하는 내용 select key");
//		board.setWriter("newbie");
//		
//		mapper.insertSelectKey(board);
//		
//		log.info(board);
//	}
	
//	@Test
//	public void testRead() {
//		
//		//존재하는 게시물 번호로 테스트
//		BoardVO board = mapper.read(5L);
//		log.info(board);
//	}
	
//	@Test
//	public void testDelete() {
//		log.info("DELETE COUNT: " + mapper.delete(3L));
//	}
	
//	@Test
//	public void testUpdate() {
//		BoardVO board = new BoardVO();
//		
//		//실행전 존재하는 번호인지 확인할 것
//		board.setBno(5L);
//		board.setTitle("수정된 제목");
//		board.setContent("수정된 내용");
//		board.setWriter("user00");
//		
//		int count = mapper.update(board);
//		log.info("UPDATE COUNT: "+ count);
//	}
	
//	@Test
//	public void testPaging() {
//		Criteria cri = new Criteria();
//		List<BoardVO> list = mapper.getListWithPaging(cri);
//		list.forEach(board -> log.info(board));
//	}
	
//	@Test
//	public void testSearch() {
//		Criteria cri = new Criteria();
//		cri.setKeyword("새로");
//		cri.setType("TC");
//		
//		List<BoardVO> list = mapper.getListWithPaging(cri);
//		
//		list.forEach(board -> log.info(board));
//	}
//	
//	@Test
//	public void testSearchCount() {
//		Criteria cri = new Criteria();
//		cri.setKeyword("새로");
//		cri.setType("TC");
//		
//		int count = mapper.getTotalCount(cri);
//		
//		log.info(" COUNT: "+ count);
//		
//	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		//3145745L
		//List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 50L);
		
		replies.forEach(reply -> log.info(reply));
	}
	
//	@Test
//	public void testUpdate() {
//		Long targetRno = 10L;
//		ReplyVO vo = mapper.read(targetRno);
//		vo.setReply("upupupupupupup");
//		int count = mapper.update(vo);
//		
//		log.info("update Count: "+count);;
//	}
	
}
