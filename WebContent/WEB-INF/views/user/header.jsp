<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">凡画</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">


			<ul class="nav navbar-nav navbar-right">
				<li><a href="home-index">首页</a></li>
				<li><a href="discovery-index">发现</a></li>
				<li><a href="follow-index">关注</a></li>
				<li><a href="loveset-index">喜爱集</a></li>
				<li><a href="myhome-index">我的作品</a></li>
				<li>
					<form class="navbar-form">
						<div class="col-lg-9">
							<div class="input-group">
								<input id="tagInput" type="text" class="form-control" style=""
									placeholder="Search for..."> <span
									class="input-group-btn">
									<button id="searchButton" class="btn btn-default" type="button">寻</button>
								</span>
							</div>
						</div>
					</form>
				</li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">${nowUser.uname } <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="shopcart-index">购物车</a></li>
						<li><a href="myorder-index">我的订单</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="profile-index">个人设置</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="login-logout">退出</a></li>
					</ul></li>
			</ul>

		</div>

	</div>
</nav>
