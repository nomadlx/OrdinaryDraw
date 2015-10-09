<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>作品发布页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/workedit.js"></script>
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
	<%@ include file="header.jsp"%>
	<div
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-10 col-md-offset-1"
		style="margin-top: 70px">
		<div class="panel-body">
			<div class="page-header">
				<h3 id="pagetitle">${title }</h3>
			</div>

			<div class="row" style="margin-left: 100px; margin-right: 100px;">
				<div class="form-group" style="display: none">
					<input style="width: 500px;" type="text" class="form-control"
						id="goodsidInput" placeholder="goodsid"
						value="${goodsInfo.goodsid }">
				</div>
				<div class="form-group">
					<label for="gnameInput">作品名</label> <input style="width: 60%;"
						type="text" class="form-control" id="gnameInput"
						placeholder="goodsname" value="${goodsInfo.gname }">
				</div>
				<div class="form-group">
					<label for="priceInput">价格</label> <input style="width: 40%;"
						type="text" class="form-control" id="priceInput"
						placeholder="price" value="${goodsInfo.price }">
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1">图片</label>
					<p>
					<div>
						<div id="imgList" class="col-md-11">
							<c:forEach var="goodsimg" items="${goodsInfo.goodsimgs }">
								<a href='#' class='thumbnail col-md-2'>
									<button data-toggle='tooltip' data-placement='bottom'
										id="${goodsimg.seq }" title='移除图片' type='button'
										class='close delimgButton' aria-label='Close'>
										<span aria-hidden='true'>&times;</span>
									</button> <img class='img-rounded imgs' id='imgcontent'
									data-toggle='tooltip' data-placement='bottom'
									title="${goodsimg.path }" src="${goodsimg.path }" alt="">
								</a>
							</c:forEach>
						</div>
						<div>
							<form id="imgForm" action="json-common-showimg" method="post"
								enctype="multipart/form-data">
								<input type="file" id="imgInput" name="upImg" accept=" image/*">
							</form>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="tagInput">标签</label>
					<textarea id="tagsInput" style="width: 60%;" class="form-control"
						rows="3"><c:forEach var="goodstag"
							items="${goodsInfo.goodstags }">${goodstag.tag.tname} </c:forEach></textarea>
				</div>
				<div class="form-group">
					<label for="gdescInput">作品描述</label>
					<textarea id="gdescInput" style="width: 60%;"
						class="form-control" rows="3">${goodsInfo.gdesc }</textarea>
				</div>
				<div class="col-md-12 text-right">
					<button id="publishButton" class="btn btn-success btn-lg">发布作品</button>
				</div>
			</div>

			<br>
			<div class="modal fade bs-example-modal-lg" tabindex="-1"
				role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<img class="img-rounded "
							src=""
							alt="...">
					</div>
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