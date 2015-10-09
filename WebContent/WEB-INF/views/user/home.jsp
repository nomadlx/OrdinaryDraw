<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
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
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-10 col-md-offset-1"
		style="margin-top: 70px">

		<div class="panel-body">
			<h4>
				热门作品 <small>Hot works</small>
			</h4>
			<table>
				<tr>
					<td colspan="2" rowspan="2">
						<div class="row col-md-12">
							<c:forEach items="${hotWork[0].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[0].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[0].gname}》 ￥${hotWork[0].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div>
					</td>

					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[1].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[1].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[1].gname}》 ￥${hotWork[1].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
					<td>
						<div class="row col-md-12">
							<c:forEach items="${hotWork[2].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[2].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[2].gname}》 ￥${hotWork[2].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div>
					</td>
					<td>
						<div class="row col-md-12">
							<c:forEach items="${hotWork[3].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[3].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[3].gname}》 ￥${hotWork[3].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div>
					</td>
					<td>
						<div class="row col-md-12">
							<c:forEach items="${hotWork[4].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[4].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[4].gname}》 ￥${hotWork[4].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div>
					</td>
					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[5].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[5].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[5].gname}》 ￥${hotWork[5].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
				</tr>
				<tr>

					<td>
						<div class="row col-md-12">
							<c:forEach items="${hotWork[6].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[6].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[6].gname}》 ￥${hotWork[6].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div>
					</td>
					<td colspan="2" rowspan="2"><div class="row col-md-12">
							<c:forEach items="${hotWork[7].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[7].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[7].gname}》 ￥${hotWork[7].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[8].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[8].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[8].gname}》 ￥${hotWork[8].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>


					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[9].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[9].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[9].gname}》 ￥${hotWork[9].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
				</tr>
				<tr>
					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[10].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[10].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[10].gname}》 ￥${hotWork[10].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[11].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[11].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[11].gname}》 ￥${hotWork[11].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[12].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[12].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[12].gname}》 ￥${hotWork[12].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>


					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[13].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[13].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[13].gname}》 ￥${hotWork[13].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
					<td><div class="row col-md-12">
							<c:forEach items="${hotWork[14].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="workshow-index?goodsid=${ hotWork[14].goodsid}"
									class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="《${hotWork[14].gname}》 ￥${hotWork[14].price}"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						</div></td>
				</tr>
			</table>
		</div>
		<div class="row inline">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<c:forEach var="subtag" items="${subTag }" varStatus="loop">
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#collapse${subtag.tagid }" aria-expanded="true"
									aria-controls="collapseOne"> ${subtag.tname } </a>
							</h4>
						</div>
						<div id="collapse${subtag.tagid }"
							class="panel-collapse collapse in" role="tabpanel"
							aria-labelledby="headingOne">
							<div class="panel-body">
								<c:forEach var="subwork" items="${subWork[loop.index]}">
									<div class="col-xs-6 col-md-2">
										<c:forEach items="${subwork.goodsimgs}" begin="0" end="0"
											step="1" var="goodsimg">
											<a href="workshow-index?goodsid=${ subwork.goodsid}"
												class="thumbnail"> <img data-toggle="tooltip"
												data-placement="bottom"
												title="《${subwork.gname}》 ￥${subwork.price}"
												src="${goodsimg.path}" alt="...">
											</a>
										</c:forEach>

									</div>
								</c:forEach>
								<div class="col-xs-12 col-md-12 text-right">
									<a href="searchtag-index?tagid=${subtag.tagid }"> 发现更多... </a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>

		<h5>
			热门创作者 <small>Hot creators</small>
		</h5>
		<div class="row">
			<c:forEach items="${hotAuthor }" var="hotauthor">
				<div class="col-xs-3 col-md-1">
					<a class="thumbnail"
						href="authorhome-index?userid=${hotauthor.userid }"> <img
						data-toggle="tooltip" data-placement="bottom"
						title="${hotauthor.uname }" src="${hotauthor.img }" alt="...">
					</a>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>