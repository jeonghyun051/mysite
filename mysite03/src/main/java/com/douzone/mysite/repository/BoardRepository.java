package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		page = page*5;
		Map<String,Object> map = new HashMap<>();
		map.put("p", page);
		map.put("k", kwd);
		Integer count = sqlSession.selectOne("board.countByKwd", map);
		if(count == null || count == 0) {
			return -1;
		}
		return count;
	}

	public List<BoardVo> findAll(int page) {
		page = page * 5;
		return sqlSession.selectList("board.findAll",page);
	}
	
	public List<BoardVo> findByKwd(int page, String kwd) {

		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select b.no, b.title, b.contents, b.reg_date, b.hit, b.group_no, b.order_no, b.depth, b.user_no, u.name\r\n"
					+ "from board b\r\n"
					+ "join user u on u.no = b.user_no \r\n"
					+ "where b.title like ? or b.contents like ? or u.name like ?\r\n"
					+ "order by group_no desc, order_no asc limit ?,5";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kwd+"%");
			pstmt.setString(2, "%"+kwd+"%");
			pstmt.setString(3, "%"+kwd+"%");
			pstmt.setInt(4, page * 5);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				int hit = rs.getInt(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNO(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(name);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public BoardVo findById(Long no) {

		BoardVo vo = new BoardVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select no, title, contents, group_no, order_no, depth, user_no from board where no = ?";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				Long id = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int groupNo = rs.getInt(4);
				int oderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				Long userNo = rs.getLong(7);

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(content);
				vo.setGroupNo(groupNo);
				vo.setOrderNO(oderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return vo;
	}

	public int findByIdTopOrderNo(int no) {

		int groupNo = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select max(order_no) from board where group_no=?";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				groupNo = rs.getInt(1);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return groupNo;
	}

	public Boolean deleteById(Long no) {
		int count = sqlSession.delete("board.deleteById",no);
		return count == 1;
	}

	public Boolean update(int no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩

			// 2. 연결 하기
			conn = getConnection();

			String sql = "update board set order_no=order_no+1\r\n" + "where group_no = ? and order_no>=1";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setInt(1, no);

			// 5. SQL문을 실행
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return result;
	}


	public Boolean boardupdate(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩

			// 2. 연결 하기
			conn = getConnection();

			String sql = "update board set title = ?, contents= ? where no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			// 5. SQL문을 실행
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return result;
	}
	
	
	public Boolean update2(int no, int orderNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩

			// 2. 연결 하기
			conn = getConnection();

			String sql = "update board set order_no=order_no+1\r\n" + "where group_no = ? and order_no>?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setInt(1, no);
			pstmt.setInt(2, orderNo);

			// 5. SQL문을 실행
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return result;
	}

	public int updateHit(Long no) {
		return sqlSession.update("board.updateHit",no);
	}
	
// 집
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
//		} catch (SQLException e) {
//			System.out.println("error" + e);
//		}
//		return conn;
//	}
	
//	교육
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.254.35:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		return conn;
	}

}
