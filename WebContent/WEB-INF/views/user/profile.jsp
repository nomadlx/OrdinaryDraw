<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>个人设置</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/profile.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-color: #EBEBEB;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-8 col-md-offset-2"
		style="margin-top: 70px">
		<div class="panel-body">
			<div>

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#home"
						aria-controls="home" role="tab" data-toggle="tab">基本信息</a></li>
					<li role="presentation"><a href="#profile"
						aria-controls="profile" role="tab" data-toggle="tab">安全设置</a></li>

				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<br>
					<div role="tabpanel" class="tab-pane active" id="home">
						<div class="container">
							<div class="form-group">
								<label for="imgcontent">个人头像</label>
								<p>
									<img class="img-rounded" id="userImg"
										style="width: 70px; height: 70px" src="${myProfile.img }"
										alt="...">
								</p>
								<form id="imgForm" action="json-common-showimg"
									method="post" enctype="multipart/form-data">
									<input type="file" id="fileInput" name="upImg" accept="image/*">
								</form>
							</div>
							<div class="form-group">
								<label for="unameInput">用户名</label> <input style="width: 500px;"
									type="text" class="form-control" id="unameInput"
									placeholder="username" value="${myProfile.uname }">
							</div>
							<div class="form-group">
								<label for="emailInput">邮箱</label> <input disabled style="width: 500px;"
									type="email" class="form-control" id="emailInput"
									placeholder="email" value="${myProfile.email }">
							</div>
							<div class="form-group">
								<label for="addrInput">居住地址</label> <select id="addrInput"
									class="form-control" style="width: 200px;" value="云南">
								</select>
							</div>
							<div class="form-group">
								<label for="udescInput">个人说明</label>
								<textarea id="udescInput" style="width: 500px;"
									class="form-control" rows="3">${myProfile.udesc }</textarea>
							</div>

							<button id="updateButton" type="submit" class="btn btn-success">保存修改</button>
						</div>

					</div>
					<div role="tabpanel" class="tab-pane" id="profile">
						<div class="container">
							

								<div class="form-group">
									<label for="oldpwdInput">旧密码</label> <input
										style="width: 500px;" type="password" class="form-control"
										id="oldpwdInput" placeholder="old password">
								</div>
								<div class="form-group">
									<label for="newpwdInput">新密码</label> <input
										style="width: 500px;" type="password" class="form-control"
										id="newpwdInput" placeholder="new password">
								</div>
								<div class="form-group">
									<label for="renewpwdInput">重复新密码</label> <input
										style="width: 500px;" type="password" class="form-control"
										id="renewpwdInput" placeholder="repeat new password">
								</div>

								<button id="updatepwdButton" class="btn btn-danger">更新密码</button>
								<button type="submit" class="btn btn-link">忘记密码？</button>
							
						</div>
					</div>
					<br>
				</div>

			</div>
		</div>
	</div>
		<button style=" display:  none;" id="modalButton" type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">Launch demo modal
	</button>
	<div class="modal bs-example-modal-sm" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>