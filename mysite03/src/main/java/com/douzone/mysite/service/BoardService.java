package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> findAll(int page) {
		return boardRepository.findAll(page);
	}

	public BoardVo findById(Long no) {
		return boardRepository.findById(no);

	}

	public void updateHit(Long no) {
		boardRepository.updateHit(no);
	}

	public void deleteById(Long no) {
		boardRepository.deleteById(no);

	}

	public int countByKwd(int page, String kwd) {
		return boardRepository.countByKwd(page, kwd);
	}

	public List<BoardVo> findByKwd(int page, String kwd) {
		return boardRepository.findByKwd(page, kwd);
	}

	public void boardUpdate(BoardVo vo) {
		boardRepository.boardupdate(vo);
		
	}

	public int findMaxGroupNo() {
		return boardRepository.findMaxGroupNo();
		
	}

	public void insert(BoardVo vo) {
		boardRepository.insert(vo);
		
	}

	public void update(int groupNo) {
		boardRepository.update(groupNo);
		
	}

	public void insert2(BoardVo vo) {
		boardRepository.insert2(vo);	
		
	}

	public void update2(int groupNo, int orderNO) {
		boardRepository.update2(groupNo, orderNO);
		
	}

	public int count() {
		return boardRepository.count();
	}
}
