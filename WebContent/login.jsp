<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>登录</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<style type="text/css">
body {
	background-color: #EBEBEB;
}

.pd {
	padding: 10px;
}
</style>
</head>
<body>
	<%@ include file="WEB-INF/views/user/headerlogout.jsp"%>
	<div
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-10 col-md-offset-1"
		style="margin-top: 70px; padding-bottom: 70px">
		<div class="panel-body">
			<div class="page-header">
				<div class="row">
					<img id="faceImg" class="col-md-2 col-md-offset-5 img-circle"
						src="../images/ordinarydraw/workimg/temp/square/img_default_face.jpeg">
				</div>
				<br>
			</div>
			<form id="loginForm" class="form-horizontal " action="login-login"
				method="post">
				<div class="form-group">
					<label for="emailInput" class="col-sm-3 control-label">邮箱</label>
					<div class="col-sm-6">
						<input type="email" class="form-control input" id="emailInput"
							name="email" placeholder="Email">
					</div>
				</div>
				<div class="form-group">
					<label for="pwdInput" class="col-sm-3 control-label">密码</label>
					<div class="col-sm-6">
						<input type="password" class="form-control input" id="pwdInput"
							name="pwd" placeholder="Password">
					</div>
				</div>
				<c:if test="${statu }">
					<div class="form-group">
						<label class="col-sm-3 control-label"></label>
						<div class="col-sm-6">
							<p style="color: #FF6347">邮箱或者密码错误，请重新输入！</p>
						</div>
					</div>
				</c:if>

				<div style="display: none;" class="form-group">
					<div class="col-sm-offset-3 col-sm-10">
						<div class="checkbox">
							<label> <input type="checkbox"> 记住密码
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-10">
						<button id="loginButton" type="submit" class="btn btn-success ">登录</button>
						<button class="btn btn-link ">忘记密码？</button>
					</div>
				</div>
			</form>

		</div>
	</div>
	<div class="col-md-12">
		<%@ include file="WEB-INF/views/user/footer.jsp"%>
	</div>
</body>
</html>