<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>作品页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/workshow.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
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
	<div id="${goodsInfo.goodsid }"
		class="panel panel-default col-xs-10 col-xs-offset-1 col-md-10 col-md-offset-1 title"
		style="margin-top: 70px">
		<div class="panel-body">
			<c:if test="${goodsInfo.statu=='2' }">
				<h1>该作品已删除</h1>
			</c:if>
			<c:if test="${goodsInfo.statu!='2' }">

				<div class="page-header">
					<div class="page-header">
						<div class="row">
							<div class="col-md-5 col-md-offset-1 " style="padding: 40px">
								<c:forEach items="${goodsInfo.goodsimgs}" begin="0" end="0"
									step="1" var="goodsimg">
									<img class="img-rounded" id="imgcontent"
										style="width: 300px; height: 300px;" data-toggle="tooltip"
										data-placement="bottom" src="${goodsimg.path}" alt="...">
								</c:forEach>
							</div>
							<div class="col-md-6">
								<div class="col-md-12 pd">
									<h1>
										《${goodsInfo.gname }》
										<c:if test="${goodsInfo.user.userid!=nowUser.userid  }">

											<c:if test="${liked }">
												<a href="#"><small><span id="likeButton"
														style="color: #FF8C69; font-size: 19px"
														data-toggle="tooltip" data-placement="bottom"
														title="取消喜爱该作品" class="glyphicon glyphicon-heart"
														aria-hidden="true"></span></small></a>
											</c:if>
											<c:if test="${liked==false }">
												<a href="#"><small><span id="likeButton"
														style="color: #D4D4D4; font-size: 19px"
														data-toggle="tooltip" data-placement="bottom"
														title="喜爱该作品" class="glyphicon glyphicon-heart"
														aria-hidden="true"></span></small></a>
											</c:if>
										</c:if>
									</h1>
								</div>
								<div class="col-md-12 pd">
									创作者：<a href="authorhome-index?userid=${goodsInfo.user.userid }">${goodsInfo.user.uname }</a>
								</div>
								<div class="col-md-12 pd">${goodsInfo.gdesc }</div>
								<div class="col-md-12 pd">
									<p style="font-size: 14px">
										<c:forEach var="goodstag" items="${goodsInfo.goodstags }">
											<a href="searchtag-index?tagid=${goodstag.tag.tagid }"
												style="color: #8B8878">#${goodstag.tag.tname } </a>
										</c:forEach>
									</p>
								</div>
								<div class="col-md-12 pd">
									价格<strong style="font-size: 25px">￥${goodsInfo.price }</strong>

								</div>

								<div class="col-md-12 pd">
									<c:if test="${goodsInfo.user.userid!=nowUser.userid  }">
										<c:if test="${goodsInfo.statu=='0' }">
											<button id="${goodsInfo.goodsid }"
												class="btn btn-success btn-lg addcartButton">加入购物车</button>
										</c:if>
										<c:if test="${goodsInfo.statu=='1' }">
											<button id="${goodsInfo.goodsid }" disabled="disabled"
												class="btn btn-default btn-lg addcartButton">该作品已下架</button>
										</c:if>
									</c:if>
									<c:if test="${goodsInfo.user.userid==nowUser.userid  }">
										<button id="${goodsInfo.goodsid }" disabled="disabled"
											class="btn btn-success btn-lg addcartButton">加入购物车</button>
									</c:if>
									<a href="#reportModal" type="button" class="btn btn-link"
										style="" data-toggle="modal" data-target="#reportModal">举报</a>

									<script>
										window._bd_share_config = {
											"common" : {
												"bdSnsKey" : {},
												"bdText" : "",
												"bdMini" : "1",
												"bdMiniList" : false,
												"bdPic" : "",
												"bdStyle" : "2",
												"bdSize" : "16"
											},
											"share" : {}
										};
										with (document)
											0[(getElementsByTagName('head')[0] || body)
													.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
													+ ~(-new Date() / 36e5)];
									</script>
								</div>
								<div class="col-md-12 pd">
									<div class="bdsharebuttonbox">
										<a href="#" class="bds_more" data-cmd="more"></a><a href="#"
											class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a
											href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a
											href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a
											href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a
											href="#" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a>
									</div>
									<script>
										window._bd_share_config = {
											"common" : {
												"bdSnsKey" : {},
												"bdText" : "我在 凡画---自由的原创性画作交易平台 发现一个好看的作品",
												"bdMini" : "1",
												"bdMiniList" : false,
												"bdPic" : "",
												"bdStyle" : "1",
												"bdSize" : "16"
											},
											"share" : {}
										};
										with (document)
											0[(getElementsByTagName('head')[0] || body)
													.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
													+ ~(-new Date() / 36e5)];
									</script>
								</div>
							</div>
						</div>
					</div>

					<div class="row" style="margin-left: 100px; margin-right: 100px;">
						<c:forEach items="${goodsInfo.goodsimgs}" var="goodsimg">
							<a class="thumbnail col-md-2"> <img class="img-rounded "
								id="imgcontent" data-toggle="modal"
								data-target="#imgModal-${goodsimg.seq }" src="${goodsimg.path }"
								alt="...">
							</a>
							<div id="imgModal-${goodsimg.seq }" class="modal fade imgModal"
								tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
								style="text-align: center;"
								ondragstart="window.event.returnValue=false"
								oncontextmenu="window.event.returnValue=false"
								onselectstart="event.returnValue=false">
								<!-- <div class="modal-dialog modal-lg">
								<div class="modal-content"> -->
								<img id="img-${goodsimg.seq }" class="img-rounded "
									style="text-align: center; width: 80%;" src=""
									data-toggle="tooltip" data-placement="bottom"
									title="${goodsInfo.user.uname}《${goodsInfo.gname}》">
								<!-- 	</div>
							</div> -->
							</div>
						</c:forEach>
					</div>
				</div>

				<div class="col-md-10 col-md-offset-1">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#home"
							aria-controls="home" role="tab" data-toggle="tab">评论</a></li>
						<li role="presentation"><a href="#profile"
							aria-controls="profile" role="tab" data-toggle="tab">销售记录</a></li>

					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<br>
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="row">
								<form class="col-md-12">

									<div class="form-group">
										<label for="commentInput">评论</label>
										<textarea id="commentInput" style="width: 100%;"
											class="form-control" rows="3"></textarea>
									</div>
									<div class="text-right">
										<button id="commentButton" type="button"
											class="btn btn-danger">提交评论</button>
									</div>

								</form>
							</div>
							<br>
							<div id="commentDiv" class="list-group">

								<c:forEach var="goodscomment" items="${goodsComment }">
									<div class="list-group-item">
										<a id="ttt" href="#"> <img
											style="width: 30px; height: 30px" data-toggle="tooltip"
											data-placement="bottom" title="${goodscomment.user.uname }"
											src="${goodscomment.user.img }" alt="...">
										</a> <a
											href="authorhome-index?userid=${goodscomment.user.userid }">${goodscomment.user.uname }</a>：${goodscomment.content }
										<small>${goodscomment.publishtime }</small>


									</div>
								</c:forEach>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<div class="list-group">

								<c:forEach var="goodssalehistorie" items="${goodsSaleHistorie }">
									<div class="list-group-item">
										<a
											href="authorhome-index?userid=${ goodssalehistorie.user.userid}">
											<img style="width: 30px; height: 30px" data-toggle="tooltip"
											data-placement="bottom"
											title="${goodssalehistorie.user.uname }"
											src="${goodssalehistorie.user.img} " alt="...">
										</a> <a
											href="authorhome-index?userid=${ goodssalehistorie.user.userid}">${goodssalehistorie.user.uname }
										</a> 在 ${goodssalehistorie.buytime } 购买了这个作品


									</div>
								</c:forEach>
							</div>
						</div>
						<br>
					</div>

				</div>
			</c:if>
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
	<div class="modal " id="reportModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">举报理由</h4>
				</div>
				<div class="modal-body">
					<input style="width: 100%;" type="text" class="form-control"
						id="reasonInput">
				</div>
				<div class="modal-footer">
					<button id="reportButton" type="button" class="btn btn-danger"
						data-dismiss="modal">提交举报</button>
				</div>
			</div>

		</div>
	</div>
	<div class="col-md-12">
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>