<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>关注</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/follow.js"></script>
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
					<li role="presentation" class="active"><a
						href="#followcontent" aria-controls="home" role="tab"
						data-toggle="tab">关注推送</a></li>
					<li role="presentation"><a href="#followmanager"
						aria-controls="profile" role="tab" data-toggle="tab">关注管理</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="followcontent">
						<br>
						<div class="row">
							<c:forEach var="followpush" items="${followPush }">
								<div class="col-md-2 ">
									<div class="col-md-10 col-md-offset-1">
										<a href="authorhome-index?userid=${ followpush.user.userid}">
											<img class="img-rounded" style="width: 70px; height: 70px"
											data-toggle="tooltip" data-placement="bottom"
											title="${followpush.user.uname }"
											src="${followpush.user.img }" alt="...">
										</a>
									</div>
								</div>
								<div class="col-md-9">
									<div class="thumbnail">
										<div class="caption">

											<p>
												<a href="authorhome-index?userid=${ followpush.user.userid}">${followpush.user.uname }</a>
												的<strong>《${followpush.gname }》</strong> <a
													href="workshow-index?goodsid=${followpush.goodsid }"><small>查看</small></a>
											</p>
										</div>
										<c:forEach items="${followpush.goodsimgs}" begin="0" end="0"
											step="1" var="goodsimg">
											<img src="${goodsimg.path}" alt="...">

										</c:forEach>

										<!-- <div class="caption">
											<div class="col-md-7"></div>
											<div class="col-md-5">
												<button type="button" class="btn btn-default btn-xs">喜爱</button>
												<button type="button" class="btn btn-default btn-xs">分享</button>
												<button type="button" class="btn btn-default btn-xs">加入购物车</button>
											</div>
										</div> -->
									</div>


								</div>
							</c:forEach>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="followmanager">
						<br>
						<div class="row">
							<form id="searchForm" action="searchuser-index" method="post">
								<div class="col-md-12" style="margin-bottom: 10px">
									<div class="col-lg-6">
										<div class="input-group">

											<input id="keyInput" type="text" name="key"
												class="form-control" placeholder="Search for..."> <span
												class="input-group-btn"> <a id="searchuserButton"
												class="btn btn-default" type="button">觅</a>
											</span>


										</div>
										<!-- /input-group -->
									</div>
									<!-- /.col-lg-6 -->
								</div>
							</form>
							<br>
							<div class="col-md-12 ">

								<ul class="list-inline">
									<c:forEach var="followuser" items="${followUser }" begin="0"
										end="6" step="1">
										<li class="list-group-item col-md-6 col-md-offset-0"><a
											href="authorhome-index?userid=${ followuser.userid}"> <img
												class="col-md-3" data-toggle="tooltip"
												data-placement="bottom" title="${followuser.uname }"
												src="${followuser.img }" alt="...">
										</a>
											<div class="col-md-7">
												<p>
													<a href="authorhome-index?userid=${ followuser.userid}">${followuser.uname}</a>
												</p>
												<p>
													<small>${followuser.udesc }</small>
												</p>
											</div>
											<div class="col-md-2">
												<button data-toggle="tooltip" data-placement="bottom"
													id="${ followuser.userid}" title="取消关注" type="button"
													class="close unfollowButton" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div></li>
									</c:forEach>
								</ul>
							</div>

						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
	<button style="display: none;" id="modalButton" type="button"
		class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">Launch demo modal</button>
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
					<button id="closeButton" type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>