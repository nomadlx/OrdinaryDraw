<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>注册</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/register.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>

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
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-8 col-md-offset-2"
		style="margin-top: 70px;">
		<div class="panel-body">
			<div class="page-header">
				<h3>用户注册</h3>
			</div>

			<div class="form-group">
				<label for="emailInput">邮箱</label> <input style="width: 500px;"
					type="email" class="form-control" id="emailInput"
					placeholder="email">
			</div>
			<div class="form-group">
				<label for="pwdInput">密码</label> <input style="width: 500px;"
					type="password" class="form-control" id="pwdInput"
					placeholder="password:more than 6 char">
			</div>
			<div class="form-group">
				<label for="repwdInput">重复密码</label> <input style="width: 500px;"
					type="password" class="form-control" id="repwdInput"
					placeholder="repeat password:consistent with password">
			</div>

			<div class="form-group">
				<label for="imgcontent">个人头像</label>
				<p>
					<img class="img-rounded" id="userImg"
						style="width: 70px; height: 70px" data-toggle="tooltip"
						data-placement="bottom" title="头像"
						src="../images/ordinarydraw/workimg/temp/square/img_default_face.jpeg"
						alt="...">
				</p>
				<form id="imgForm" action="json-common-showimg" method="post"
					enctype="multipart/form-data">
					<input type="file" id="fileInput" name="upImg" accept="image/*">
				</form>
				<p class="help-block">Example block-level help text here.</p>
			</div>
			<div class="form-group">
				<label for="unameInput">用户名</label> <input style="width: 500px;"
					type="email" class="form-control" id="unameInput"
					placeholder="username">
			</div>

			<div class="form-group">
				<label for="addrInput">居住地址</label> <select id="addrInput"
					class="form-control" style="width: 200px;">
				</select>
			</div>
			<div class="form-group">
				<label for="udescInput">个人说明</label>
				<textarea id="udescInput" style="width: 500px;" class="form-control"
					rows="3" placeholder="your personal description"></textarea>
			</div>

			<button id="registerButton" class="btn btn-success">注册</button>

			<br>
		</div>
	</div>
	<button id="modalButton" type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">Launch demo modal
	</button>
	<div class="modal bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modaltitle"></h4>
				</div>
				<div id="modaltext" class="modal-body"></div>
				<div class="modal-footer">
					<button id="closeButton" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-12">
		<%@ include file="WEB-INF/views/user/footer.jsp"%>
	</div>
</body>
</html>