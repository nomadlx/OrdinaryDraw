$(document)
		.ready(
				function() {
					// alert("workshow");
					/**
					 * 加入购物车
					 */
					$(".addcartButton").click(function() {
						var goodsid = $(this).attr("id");
						var url = "json-workshow-addtocart";
						$.post(url, {
							goodsid : goodsid
						}, function(result) {
							if (result.statu) {
								$("#modaltitle").text("消息");
								$("#modaltext").text("添加成功!");
								$("#modalButton").click();
							} else {
								$("#modaltitle").text("消息");
								$("#modaltext").text("商品已经在购物车里!");
								$("#modalButton").click();
							}

						}, 'json');
					});
					/**
					 * 喜爱取消喜爱该作品
					 */
					$("#likeButton").click(function() {
						var obj = $(this);
						var goodsid = $(".title").attr("id");
						var title = $(this).attr("title");
						var style;
						var message;
						var url;
						if (title == "喜爱该作品") {
							url = "json-workshow-like.action";
							title = "取消喜爱该作品";
							style = "color: #FF8C69; font-size: 19px";
							message = "喜爱成功!";
						} else {
							url = "json-workshow-unlike.action";
							title = "喜爱该作品";
							style = "color: #D4D4D4; font-size: 19px";
							message = "取消喜爱成功!";
						}
						$.post(url, {
							goodsid : goodsid
						}, function(result) {
							if (result.statu == true) {
								obj.attr("title", title);
								obj.attr("style", style);
								$("#modaltitle").text("消息");
								$("#modaltext").text(message);
								$("#modalButton").click();

							} else {
								$("#modaltitle").text("消息");
								$("#modaltext").text("操作失败！");
								$("#modalButton").click();
							}
						}, 'json');
					});
					$("#commentButton").click(function() {
						var url = "json-workshow-comment.action";
						$.post(url, {
							goodsid : $(".title").attr("id"),
							content : $("#commentInput").val()
						}, function(result) {
							if (result.statu == true) {
								$("#modaltitle").text("消息");
								$("#modaltext").text("评论成功!");
								$("#modalButton").click();
								loadcomment()

							} else {
								$("#modaltitle").text("消息");
								$("#modaltext").text("评论失败！");
								$("#modalButton").click();
							}
						}, 'json');
					});
					/**
					 * 举报作品
					 */
					$("#reportButton").click(function() {
						var url = "json-workshow-report.action";
						var reason =$("#reasonInput").val();
						$.post(url, {
							goodsid : $(".title").attr("id"),
							reason : reason
						}, function(result) {
							if (result.statu == true) {
								$("#modaltitle").text("消息");
								$("#modaltext").text("举报成功!");
								$("#modalButton").click();

							} else {
								$("#modaltitle").text("消息");
								$("#modaltext").text("举报失败！");
								$("#modalButton").click();
							}
						}, 'json');
					});
					function loadcomment() {
						var url = "json-workshow-loadcomment.action";
						$
								.post(
										url,
										{
											goodsid : $(".title").attr("id")
										},
										function(result) {
											var str = "";
											for (var i = 0; i < result.count; i++) {
												var goodscomment = result.goodsComment[i];

												str = str
														+ "<div class='list-group-item'> <a href='#'> <img style='width: 30px; height: 30px' data-toggle='tooltip' data-placement='bottom' title='"
														+ goodscomment.user.uname
														+ "' src='"
														+ goodscomment.user.img
														+ "' alt='...'> </a> <a href='authorhome-index?userid="
														+ goodscomment.user.userid
														+ "'>"
														+ goodscomment.user.uname
														+ "</a>："
														+ goodscomment.content
														+ "<small>  "
														+ goodscomment.publishtime
														+ "</small> </div>";
											}
											$("#commentDiv").html(str);
										}, 'json');
					}
					/**
					 * 当模式框被调用时,先去服务器加载对应图片
					 */
					$('.imgModal').on('show.bs.modal', function() {
						var seq = $(this).attr("id").split("-")[1];
						var url = "json-workshow-getimg.action";
						$.post(url, {
							goodsid : $(".title").attr("id"),
							seq : seq
						}, function(result) {
							$("#img-" + seq).attr("src", result.path);
						}, 'json');
					});
				})