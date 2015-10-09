<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>我的作品</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/myhome.js"></script>

<style type="text/css">
body {
	background-color: #EBEBEB;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-10 col-md-offset-1"
		style="margin-top: 70px">
		<div class="panel-body">
			<div class="page-header">
				<div class="row">
					<a href="myhome-index"> <img
						class="col-md-2 col-md-offset-5 img-circle" data-toggle="tooltip"
						data-placement="bottom" title="" src="${myInfo.img }" alt="...">
					</a>

				</div>
				<br>
				<div class="row text-center">
					<p>${myInfo.uname }</p>
				</div>
				<div class="row text-center">
					<p>
						<small>${myInfo.addr}</small>
					</p>
					<p>
						<small>${myInfo.udesc }</small>
					</p>
				</div>
			</div>

			<div class="row">
				<a type="button" class="btn btn-success col-md-4 col-md-offset-4"
					href="workedit-index">创建新作品</a>
			</div>

			<br>
			<div class="row">
				<c:forEach var="mywork" items="${myWork }">
					<c:if test="${mywork.statu != 2}">
						<div class="col-xs-6 col-md-3">

							<div class="thumbnail col-md-12">
								<c:forEach items="${mywork.goodsimgs}" begin="0" end="0"
									step="1" var="goodsimg">
									<img data-toggle="tooltip" data-placement="bottom"
										title="￥${mywork.price}" src="${goodsimg.path}" alt="...">

								</c:forEach>
								<div class="caption inline">
									<h5>
										<a href="workshow-index?goodsid=${ mywork.goodsid}">《${mywork.gname}》</a><span
											class="badge label-primary">${ fn:split(mywork.updatetime,' ')[0]}</span>
									</h5>
									<p>
										<c:if test="${mywork.statu == 0}">
											<a id="editButton${ mywork.goodsid}"
												href="workedit-index?goodsid=${mywork.goodsid }"
												class="btn btn-warning editButton" role="button">编辑</a>
											<button id="${mywork.goodsid }"
												class="btn btn-link shelveButton" role="button">下架</button>
										</c:if>
										<c:if test="${mywork.statu == 1}">
											<a style="display: none;" id="editButton${ mywork.goodsid}"
												href="workedit-index?goodsid=${mywork.goodsid }"
												class="btn btn-warning " role="button">编辑</a>
											<button id="${mywork.goodsid }"
												class="btn btn-success shelveButton" role="button">上架</button>
										</c:if>


									</p>
								</div>
							</div>

						</div>
					</c:if>
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