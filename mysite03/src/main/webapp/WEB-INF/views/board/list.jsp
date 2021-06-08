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
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/board/search/0"
					method="GET">
					<input type="text" id="kwd" name="kwd"> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목$</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>

					</tr>

					<c:forEach items="${list }" var="list" varStatus="status">
						<tr>

							<c:choose>
								<c:when test="${empty param.kwd }">
									<td>${count-status.index-page*5 }</td>
								</c:when>
								<c:otherwise>
									<td>${count2-status.index-page*5 }</td>
								</c:otherwise>
							</c:choose>


							<c:if test="${list.depth == 0 }">
								<td style="text-align: left; padding-left: 0px">
							</c:if>

							<c:if test="${list.depth >= 1}">
								<td style="text-align: left; padding-left: ${list.depth *20}px"><img
									src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
							</c:if>
							<a
								href="${pageContext.request.contextPath }/board/view/${list.no}">${list.title }
							</a>
							<td>${list.userName }</td>
							<td>${list.hit }</td>
							<td>${list.regDate }</td>
							<td><c:if test="${list.userNo == authUser.no}">
									<a
										href="${pageContext.request.contextPath }/board/delete/${list.no }/${page }"
										class="del"
										style='background-image:url("${pageContext.request.contextPath }/assets/images/recycle.png")'>삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<c:choose>
					<c:when test="${empty kwd }">
						<div class="pager">
							<ul>
								<c:choose>
									<c:when test="${page == 0 }">
										<li><a href="/mysite03/board/${page }">◀</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/mysite03/board/${page -1}">◀</a></li>
									</c:otherwise>
								</c:choose>

								<!-- <li class="selected"><a>1</a></li> -->
								<c:forEach begin="0" end="${lastPageNo }" var="lastPageNo"
									varStatus="status">
									<c:choose>
										<c:when test="${page == status.index }">
											<li class="selected"><a
												href="${pageContext.request.contextPath }/board/${status.index }">${status.count }</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath }/board/${status.index }">${status.count }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${page == lastPageNo }">
										<li><a
											href="${pageContext.request.contextPath }/board/${page}">▶</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath }/board/${page+1}">▶</a></li>
									</c:otherwise>
								</c:choose>

							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="pager">
							<ul>
								<c:choose>
									<c:when test="${page == 0 }">
										<li><a
											href="${pageContext.request.contextPath }/board/search/${page}?kwd=${kwd }">◀</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath }/board/search/${page-1}?kwd=${kwd }">◀</a></li>
									</c:otherwise>
								</c:choose>

								<!-- <li class="selected"><a>1</a></li> -->
								<c:forEach begin="0" end="${lastPageNo2 }" var="lastPageNo"
									varStatus="status">
									<c:choose>
										<c:when test="${page == status.index }">
											<li class="selected"><a
												href="${pageContext.request.contextPath }/board/search/${status.index }?kwd=${kwd }">${status.count }</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath }/board/search/${status.index }?kwd=${kwd }">${status.count }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${page == lastPageNo2 }">
										<li><a
											href="${pageContext.request.contextPath }/board/search/${page}?kwd=${kwd }">▶</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath }/board/search/${page +1}?kwd=${kwd }">▶</a></li>
									</c:otherwise>
								</c:choose>

							</ul>
						</div>

					</c:otherwise>
				</c:choose>
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write/0/0"
						id="new-book">글쓰기</a>
				</div>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>