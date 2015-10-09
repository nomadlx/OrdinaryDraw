<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<title>发现</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
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
			<h4>
				热门标签 <small>Hot tags</small>
			</h4>
			<div class="row">
				<c:forEach var="hottag" items="${hotTag }" varStatus="loop">
					<div class="col-xs-6 col-md-3" >
					<c:forEach items="${hotTagWork[loop.index].goodsimgs}" begin="0" end="0"
								step="1" var="goodsimg">
								<a href="searchtag-index?tagid=${hottag.tagid }" class="thumbnail"> <img data-toggle="tooltip"
									data-placement="bottom"
									title="${hottag.tname }"
									src="${goodsimg.path}" alt="...">
								</a>
							</c:forEach>
						
					</div>
				</c:forEach>
			</div>
			<br>
			<div class="row">
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="col-md-6">推荐标签</th>
							<th class="col-md-2">作品数量</th>
							<th class="col-md-2 hidden-xs">热门创作者</th>
							<th class="col-md-2 hidden-xs">最近更新</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="recommendtag" items="${ recommendTag}" varStatus="loop">
							<tr>
								<td><a href="searchtag-index?tagid=${recommendtag.tagid }">${recommendtag.tname}</a></td>
								<td><span class="badge label-primary">${recommendtag.goodscount }</span></td>
								<td class="hidden-xs"><a href="authorhome-index?userid=${recommendTagUser[loop.index].userid }" class="hidden-xs">${recommendTagUser[loop.index].uname }</a></td>
								<td class="hidden-xs"><em class="text-muted hidden-xs">${fn:split(recommendtag.updatetime ,' ')[0]}</em></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>