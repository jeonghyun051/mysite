t<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		btn = $('#btn-check');
		btn.click(function() {
			var email = $("#email").val();
			if (email == "") {
				return;
			}
			$.ajax({
				url : "/mysite03/user/api/checkemail?email=" + email,
				type : "get",
				dataType : "json",
				error : function(xhr, status, e) {
					console.error(status, e);
				},
				success : function(res) {
					console.log(res);
					if (res.result != "success") {
						console.log(res.message);
						return;
					}

					if (res.data) {
						alert("존재하는 이메일 입니다. 다른 이메일을 사용하세요");
						$("#email").val("");
						$("#email").focus;
						return;
					}

					$("#btn-check").hide();
					$("#img-check").show();

				}
			});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="">
					<p style="color: #f00; text-align: left; padding-left: 0">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<strong>${errors.getFieldError( 'name' ).defaultMessage }</strong>
							</c:if>
						</spring:hasBindErrors>
					</p>
					<label class="block-label" for="email">이메일</label> <input
						id="email" name="email" type="text" value=""> <input
						id="btn-check" type="button" value="중복체크"> <img
						id="img-check"
						src="${pageContext.request.contextPath }/assets/images/check.png"
						style="width: 18px; vertical-align: bottom; display: none" /> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">
					<p style="color: #f00; text-align: left; padding-left: 0">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('eamil') }">
								<strong>${errors.getFieldError( 'email' ).defaultMessage }</strong>
							</c:if>
						</spring:hasBindErrors>
					</p>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					<input type="submit" value="가입하기">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>