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
	src="${pageContext.request.contextPath}/resources/js/searchuser.js"></script>
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
			<div class="page-header">
				<h3>
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					搜索用户结果
				</h3>
			</div>
			<div class="row">

				<ul class="list-inline">
					<c:forEach var="searchuser" items="${searchUsers }"
						varStatus="loop">
						<li class="list-group-item col-md-6 col-md-offset-0"><a
							href="authorhome-index?userid=${ searchuser.userid}"> <img
								class="col-md-3" data-toggle="tooltip" data-placement="bottom"
								title="${searchuser.uname }" src="${searchuser.img }" alt="...">
						</a>
							<div class="col-md-7">
								<p>
									<a href="authorhome-index?userid=${ searchuser.userid}">${searchuser.uname}</a>
								</p>
								<p>
									<small>${searchuser.udesc }</small>
								</p>
							</div>
							<div class="col-md-2" style="text-align: right;">
								<c:if test="${searchuser.userid!=nowUser.userid }">
									<c:if test="${isLiked[loop.index]==true }">
										<span id="${ searchuser.userid}" data-toggle="tooltip"
											data-placement="bottom" title="取消关注该创作者"
											class="glyphicon glyphicon-heart followButton"
											style="color: #FF8C69; font-size: 19px" aria-hidden="true"></span>
									</c:if>

									<c:if test="${isLiked[loop.index]==false }">
										<span id="${ searchuser.userid}" data-toggle="tooltip"
											data-placement="bottom" title="关注该创作者"
											class="glyphicon glyphicon-heart followButton"
											style="color: #D4D4D4; font-size: 19px" aria-hidden="true"></span>
									</c:if>
								</c:if>
							</div></li>
					</c:forEach>
				</ul>
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
	<br>
	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>