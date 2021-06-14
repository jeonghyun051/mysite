<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form:form modelAttribute="boardVo" class="board-form" method="post"
					action="${pageContext.request.contextPath }/board/write/${boardNo }">
					<input type="hidden" name="userNo" value="${authUser.no }">
					<input type="hidden" name="groupNo" value="${groupNo }">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${boardVo.title }">
								<p style="color: #f00; text-align: left; padding-left: 0">
									<spring:hasBindErrors name="boardVo">
										<c:if test="${errors.hasFieldErrors('title') }">
											<spring:message
												code="${errors.getFieldError('title').codes[0] }" />
										</c:if>
									</spring:hasBindErrors>
								</p></td>
							<td></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="contents">${boardVo.contents }</textarea>
								<p style="color: #f00; text-align: left; padding-left: 0">
									<spring:hasBindErrors name="boardVo">
										<c:if test="${errors.hasFieldErrors('contents') }">
											<spring:message
												code="${errors.getFieldError('contents').codes[0] }" />
										</c:if>
									</spring:hasBindErrors>
								</p></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="/board/0">취소</a> <input type="submit" value="등록">
					</div>
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>