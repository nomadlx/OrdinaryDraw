<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>搜索——标签</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/searchtag.js"></script>
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
	<div id="${nowTag.tagid }"
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-10 col-md-offset-1 title"
		style="margin-top: 70px">
		<div class="panel-body">
			<div class="page-header">
				<h3>
					<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
					${nowTag.tname}
					<c:if test="${liked }">
						<button id="subButton" class="btn btn-success btn-sm "
							role="button">已订阅</button>
					</c:if>
					<c:if test="${liked==false }">
						<button id="subButton" class="btn btn-default btn-sm "
							role="button">未订阅</button>
					</c:if>

				</h3>
			</div>
			<div class="row">
				<c:forEach var="taggoods" items="${tagGoods }">
					<div class="col-xs-6 col-md-3">

						<div class="thumbnail col-md-12">
							<c:forEach items="${taggoods.goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<img data-toggle="tooltip" data-placement="bottom"
									title="￥${taggoods.price}" src="${goodsimg.path}" alt="...">

							</c:forEach>

							<div class="caption inline">
								<h5>
									<a href="workshow-index?goodsid=${ taggoods.goodsid}">
										《${taggoods.gname }》</a><a
										href="authorhome-index?userid=${taggoods.user.userid }"><small>${taggoods.user.uname }</small></a>
								</h5>
							</div>
						</div>

					</div>
				</c:forEach>
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