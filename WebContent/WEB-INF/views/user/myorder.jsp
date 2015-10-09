<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>我的订单</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/myorder.js"></script>
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
			<ul id="myTabs" class="nav nav-tabs " role="tablist">
				<li role="presentation" class="active"><a href="#home"
					id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="false">所有订单</a></li>
				<li role="presentation" class=""><a href="#profile" role="tab"
					id="profile-tab" data-toggle="tab" aria-controls="profile"
					aria-expanded="false">已购作品</a></li>
				<li role="presentation" class="dropdown "><a href="#"
					id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown"
					aria-controls="myTabDrop1-contents" aria-expanded="false">状态 <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" aria-labelledby="myTabDrop1"
						id="myTabDrop1-contents">
						<li class=""><a href="#dropdown1" role="tab"
							id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1"
							aria-expanded="false">未付款</a></li>
						<li class=""><a href="#dropdown2" role="tab"
							id="dropdown2-tab" data-toggle="tab" aria-controls="dropdown2"
							aria-expanded="true">已取消</a></li>
						<li class=""><a href="#dropdown3" role="tab"
							id="dropdown3-tab" data-toggle="tab" aria-controls="dropdown3"
							aria-expanded="true">已完成</a></li>
					</ul></li>
			</ul>
			<div id="myTabContent" class="tab-content ">
				<br>
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="home-tab">
					<div class="list-group">
						<div class="list-group-item">
							<table style="width: 100%;">
								<thead>
									<th style="width: 70%">商品</th>
									<th style="width: 10%">数量</th>
									<th style="width: 10%">总价格</th>
									<th style="width: 10%">状态</th>
								</thead>
							</table>
						</div>
						<c:forEach var="order" items="${allOrder }">
							<div class="list-group-item">
								<p>
									<label>订单编号：</label>${order.orderid }<label
										style="margin-left: 10px">日期：</label>${order.gertdate }
								</p>
								<table style="width: 100%;">
									<tr>
										<td style="width: 70%"><c:forEach
												items="${order.orderitems}" var="orderitems">
												<a
													href="workshow-index?goodsid=${orderitems.goods.goodsid }">
													<c:forEach items="${orderitems.goods.goodsimgs}" begin="0"
														end="0" step="1" var="goodsimg">
														<img style="width: 60px; height: 60px"
															data-toggle="tooltip" data-placement="bottom"
															title="《${orderitems.goods.gname }》 ￥${orderitems.goods.price}"
															src="${goodsimg.path }" alt="...">
													</c:forEach>

												</a>
											</c:forEach></td>
										<td style="width: 10%">${order.qty }</td>
										<td style="width: 10%">￥${order.price }</td>
										<td style="width: 10%"><c:if test="${order.statu=='0' }">
												<button id="${order.orderid}" type="button"
													class="btn btn-success payorderButton" style="">付款</button>
												<button id="${order.orderid}" type="button"
													class="btn btn-link cancelorderButton" style="">取消</button>
											</c:if> <c:if test="${order.statu=='1' }">
												<button type="button" class="btn btn-success "
													disabled="disabled" style="">已完成</button>
											</c:if> <c:if test="${order.statu=='2' }">
												<button type="button" class="btn btn-default"
													disabled="disabled" style="">已取消</button>
											</c:if></td>
									</tr>
								</table>
							</div>
						</c:forEach>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="profile"
					aria-labelledby="profile-tab">
					<div class="list-group">
						<div class="list-group-item">
							<table style="width: 100%;">
								<thead>
									<th style="width: 60%">作品</th>
									<th style="width: 10%">作品名</th>
									<th style="width: 10%">创作者名</th>
									<th style="width: 10%">价格</th>
									<th style="width: 10%">操作</th>
								</thead>
							</table>
						</div>
						<c:forEach var="salegoods" items="${saleGoods }">
							<div class="list-group-item">

								<table style="width: 100%;">
									<tr>
										<td style="width: 60%"><c:forEach
												items="${salegoods.goods.goodsimgs}" var="goodsimg">
												<a href="#"> <img style="witdh: 60px; height: 60px"
													data-toggle="tooltip" data-placement="bottom" title=""
													src="${ goodsimg.path}" alt="...">
												</a>
											</c:forEach></td>
										<td style="width: 10%"><a
											href="workshow-index?goodsid=${salegoods.goods.goodsid }">${salegoods.goods.gname }</a></td>
										<td style="width: 10%"><a
											href="authorhome-index?userid=${salegoods.goods.user.userid }">${salegoods.goods.user.uname }</a></td>
										<td style="width: 10%">￥${salegoods.goods.price }</td>
										<td style="width: 10%">
											<a  href="myorder-download?goodsid=${salegoods.goods.goodsid}" type="button" class="btn btn-success" style="">下载</a>
										</td>
									</tr>
								</table>

							</div>
						</c:forEach>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="dropdown1"
					aria-labelledby="dropdown1-tab">
					<div class="list-group">
						<div class="list-group-item">
							<table style="width: 100%;">
								<thead>
									<th style="width: 70%">商品</th>
									<th style="width: 10%">数量</th>
									<th style="width: 10%">总价格</th>
									<th style="width: 10%">状态</th>
								</thead>
							</table>
						</div>
						<c:forEach var="nopayorder" items="${nopayOrder }">
							<div class="list-group-item">
								<p>
									<label>订单编号：</label>${nopayorder.orderid }<label
										style="margin-left: 10px">日期：</label>${nopayorder.gertdate }
								</p>
								<table style="width: 100%;">
									<tr>
										<td style="width: 70%"><c:forEach
												items="${nopayorder.orderitems}" var="orderitems">
												<a
													href="workshow-index?goodsid=${orderitems.goods.goodsid }">
													<c:forEach items="${orderitems.goods.goodsimgs}" begin="0"
														end="0" step="1" var="goodsimg">
														<img style="width: 60px; height: 60px"
															data-toggle="tooltip" data-placement="bottom"
															title="《${orderitems.goods.gname }》 ￥${orderitems.goods.price}"
															src="${goodsimg.path }" alt="...">
													</c:forEach>

												</a>
											</c:forEach></td>
										<td style="width: 10%">${nopayorder.qty }</td>
										<td style="width: 10%">￥${nopayorder.price }</td>
										<td style="width: 10%">
											<button id="${nopayorder.orderid}" type="button"
												class="btn btn-success payorderButton" style="">付款</button>
											<button id="${nopayorder.orderid}" type="button"
												class="btn btn-link cancelorderButton" style="">取消</button>
										</td>
									</tr>
								</table>
							</div>
						</c:forEach>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="dropdown2"
					aria-labelledby="dropdown2-tab">
					<div class="list-group">
						<div class="list-group-item">
							<table style="width: 100%;">
								<thead>
									<th style="width: 70%">商品</th>
									<th style="width: 10%">数量</th>
									<th style="width: 10%">总价格</th>
									<th style="width: 10%">状态</th>
								</thead>
							</table>
						</div>
						<c:forEach var="cancelorder" items="${cancelOrder }">
							<div class="list-group-item">
								<p>
									<label>订单编号：</label>${cancelorder.orderid }<label
										style="margin-left: 10px">日期：</label>${cancelorder.gertdate }
								</p>
								<table style="width: 100%;">
									<tr>
										<td style="width: 70%"><c:forEach
												items="${cancelorder.orderitems}" var="orderitems">
												<a
													href="workshow-index?goodsid=${orderitems.goods.goodsid }">
													<c:forEach items="${orderitems.goods.goodsimgs}" begin="0"
														end="0" step="1" var="goodsimg">
														<img style="width: 60px; height: 60px"
															data-toggle="tooltip" data-placement="bottom"
															title="《${orderitems.goods.gname }》 ￥${orderitems.goods.price}"
															src="${goodsimg.path }" alt="...">
													</c:forEach>

												</a>
											</c:forEach></td>
										<td style="width: 10%">${cancelorder.qty }</td>
										<td style="width: 10%">￥${cancelorder.price }</td>
										<td style="width: 10%">
											<button type="button" class="btn btn-default"
												disabled="disabled" style="">已取消</button>
										</td>
									</tr>
								</table>
							</div>
						</c:forEach>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="dropdown3"
					aria-labelledby="dropdown3-tab">
					<div class="list-group">
						<div class="list-group-item">
							<table style="width: 100%;">
								<thead>
									<th style="width: 70%">商品</th>
									<th style="width: 10%">数量</th>
									<th style="width: 10%">总价格</th>
									<th style="width: 10%">状态</th>
								</thead>
							</table>
						</div>
						<c:forEach var="doneorder" items="${doneOrder }">
							<div class="list-group-item">
								<p>
									<label>订单编号：</label>${doneorder.orderid }<label
										style="margin-left: 10px">日期：</label>${doneorder.gertdate }
								</p>
								<table style="width: 100%;">
									<tr>
										<td style="width: 70%"><c:forEach
												items="${doneorder.orderitems}" var="orderitems">
												<a
													href="workshow-index?goodsid=${orderitems.goods.goodsid }">
													<c:forEach items="${orderitems.goods.goodsimgs}" begin="0"
														end="0" step="1" var="goodsimg">
														<img style="width: 60px; height: 60px"
															data-toggle="tooltip" data-placement="bottom"
															title="《${orderitems.goods.gname }》 ￥${orderitems.goods.price}"
															src="${goodsimg.path }" alt="...">
													</c:forEach>

												</a>
											</c:forEach></td>
										<td style="width: 10%">${doneorder.qty }</td>
										<td style="width: 10%">￥${doneorder.price }</td>
										<td style="width: 10%">
											<button type="button" class="btn btn-success"
												disabled="disabled" style="">已完成</button>
										</td>
									</tr>
								</table>
							</div>
						</c:forEach>
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