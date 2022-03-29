package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	public void register(ReplyVO board);
	
	public ReplyVO get(Long rno);
	
	public boolean modify(ReplyVO board);
	
	public boolean remove(Long rno);
	
	//public List<ReplyVO> getList();
	
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
}
