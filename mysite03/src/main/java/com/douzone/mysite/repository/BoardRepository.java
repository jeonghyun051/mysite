package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BoardVo vo) {
		vo.setGroupNo(vo.getGroupNo()+1);
		int count = sqlSession.insert("board.insert",vo);
		return count == 1;
	}

	public Boolean insert2(BoardVo vo) {
		int count = sqlSession.insert("board.insert",vo);
		return count == 1;
	}

	public int findMaxGroupNo() {
		BoardVo vo = sqlSession.selectOne("board.findMaxGroupNo"); 
		if(vo == null) {
			return 0;
		}
		return vo.getGroupNo();
	}
		
	public int count() {
		int count = sqlSession.selectOne("board.count");
		if(count == 0) {
			return -1;
		}
		return count;
	}
	
	public int countByKwd(int page, String kwd) {
		page = 0;
		Map<String,Object> map = new HashMap<>();
		map.put("p", page);
		map.put("k", kwd);
		Integer count = sqlSession.selectOne("board.countByKwd", map);
		return count;
	}

	public List<BoardVo> findAll(int page) {
		page = page * 5;
		return sqlSession.selectList("board.findAll",page);
	}
	
	public List<BoardVo> findByKwd(int page, String kwd) {
		Map<String,Object> map = new HashMap<>();
		page = page * 5;
		map.put("page", page);
		map.put("kwd", kwd);
		return sqlSession.selectList("board.findByKwd",map);
	}

	public BoardVo findById(Long no) {
		return sqlSession.selectOne("board.findById",no);
	}

	public int findByIdTopOrderNo(int no) {		
		return sqlSession.selectOne("board.findByIdTopOrderNo",no);
	}

	public Boolean deleteById(Long no) {
		int count = sqlSession.delete("board.deleteById",no);
		return count == 1;
	}

	public int update(int no) {
		return sqlSession.update("board.update",no);
	}


	public int boardupdate(BoardVo vo) {
		return sqlSession.update("board.boardupdate",vo);
	}
	
	
	public int update2(int no, int orderNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("no", no);
		map.put("orderNo", orderNo);
		return sqlSession.update("board.update2",map);
	}

	public int updateHit(Long no) {
		return sqlSession.update("board.updateHit",no);
	}
}