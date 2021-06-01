<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite2</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/board?a=search"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach items="${list }" var="list" varStatus="status">
						<tr>
							<td>${count-status.index-param.p*5 }</td>
							<c:if test="${list.depth == 0 }">
								<td style="text-align: left; padding-left: 0px">
							</c:if>

							<c:if test="${list.depth == 1}">
								<td style="text-align: left; padding-left: 20px"><img
									src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
							</c:if>

							<c:if test="${list.depth == 2}">
								<td style="text-align: left; padding-left: 40px"><img
									src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
							</c:if>
							<a href="${pageContext.request.contextPath }/board?a=view&no=${list.no }">${list.title }</a>
							<td>${list.userName }</td>
							<td>${list.hit }</td>
							<td>${list.regDate }</td>
							<td><c:if test="${list.userNo == authUser.no}">
									<a
										href="${pageContext.request.contextPath }/board?a=delete&no=${list.no }"
										class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${param.p == 0 }">
								<li><a href="/mysite02/board?a=list&p=${param.p}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite02/board?a=list&p=${param.p-1}">◀</a></li>
							</c:otherwise>
						</c:choose>

						<!-- <li class="selected"><a>1</a></li> -->
						<c:forEach begin="0" end="${lastPageNo }" var="lastPageNo"
							varStatus="status">
							<c:choose>
								<c:when test="${param.p == status.index }">
									<li class="selected"><a
										href="/mysite02/board?a=list&p=${status.index }">${status.count }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/mysite02/board?a=list&p=${status.index }">${status.count }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${param.p == lastPageNo }">
								<li><a href="/mysite02/board?a=list&p=${param.p}">▶</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite02/board?a=list&p=${param.p+1}">▶</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
				<!-- pager 추가 -->

				<c:if test="${authUser.no != null }">
					<div class="bottom">
						<a
							href="${pageContext.request.contextPath }/board?a=writeform&boardNo=0"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>