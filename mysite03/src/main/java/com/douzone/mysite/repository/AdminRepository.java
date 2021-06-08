package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int update(SiteVo vo) {
		int result = sqlSession.update("site.update",vo);
		System.out.println("result:" + result);
		return result;
	}

	public SiteVo findOne() {
		return sqlSession.selectOne("site.selectOne");
	}
}