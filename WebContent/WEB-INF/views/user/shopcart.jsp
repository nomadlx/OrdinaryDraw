<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>购物车</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/shopcart.js"></script>
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
					购物车 <small>Shopping cart</small>
				</h3>
			</div>
			<div class="list-group">
				<div class="list-group-item">
					<table style="width: 100%;">
						<thead>
							<th style="width: 5%"><div class="checkbox">
									<label> <input id="allselectCheckbox" type="checkbox"  checked="false">
									</label>
								</div></th>
							<th style="width: 45%">图片</th>
							<th style="width: 15%">作品名</th>
							<th style="width: 10%">创作者名</th>
							<th style="width: 10%">价格</th>
							<th style="width: 10%">操作</th>
						</thead>
					</table>
				</div>
				<c:forEach var="cartgoods" items="${cartGoods }">
					<div class="list-group-item">
						<table style="width: 100%;">
							<tr>
								<td style="width: 5%">
									<div class="checkbox">
										<label> <input value="${cartgoods.goods.price }"
											name="${cartgoods.goods.goodsid }" type="checkbox"
											class="goodsCheckbox">
										</label>
									</div>
								</td>
								<td style="width: 45%"><c:forEach var="goodsimgs"
										items="${cartgoods.goods.goodsimgs }">
										<img style="width: 60px; height: 60px"
											src="${ goodsimgs.path}" alt="...">
									</c:forEach></td>
								<td style="width: 15%"><a
									href="workshow-index?goodsid=${cartgoods.goods.goodsid }">《${cartgoods.goods.gname }》</a></td>
								<td style="width: 10%"><a
									href="authorhome-index?userid=${cartgoods.goods.user.userid }">${cartgoods.goods.user.uname }</a></td>
								<td style="width: 10%">￥${cartgoods.goods.price}</td>
								<td style="width: 10%">
									<button id="${cartgoods.goods.goodsid }" type="button"
										class="btn btn-danger deleteButton" style="">删除</button>
								</td>
							</tr>
						</table>
					</div>
				</c:forEach>
				<div class="list-group-item">
					<table style="width: 100%">
						<tr>
							<td style="width: 60%"></td>
							<td style="width: 15%">已选商品<strong id="allCount">0</strong>件
							</td>
							<td style="width: 15%">合计：￥<strong id="allPrice">0</strong></td>
							<td style="width: 10%">
								<button id="balanceButton" type="button" class="btn btn-success"
									style="">结算</button>
							</td>
						</tr>
					</table>
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
						<button id="modalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>