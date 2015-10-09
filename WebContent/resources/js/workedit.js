$(document)
		.ready(
				function() {
					// alert("workedit");
					var gnamebool = false;
					var pricebool = false;
					var checkNum = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
					if ($("#gnameInput").val().length < 1) {
						gnamebool = false;
					} else {
						gnamebool = true;
					}
					if (checkNum.test($("#priceInput").val())
							&& $("#priceInput").val().length > 0) {
						pricebool = true;
					} else {
						pricebool = false;
					}
					/**
					 * 图片的添加删除处理
					 */
					$("#imgInput")
							.change(
									function() {
										$('#imgForm')
												.ajaxSubmit(
														function(result) {
															$("#imgList")
																	.append(
																			"<a href='#' class='thumbnail col-md-2'> <button data-toggle='tooltip' data-placement='bottom' title='移除图片' type='button' class='close delimgButton' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> <img class='img-rounded imgs' id='imgcontent' data-toggle='tooltip' data-placement='bottom' title='"
																					+ result.path
																					+ "' src='"
																					+ result.path
																					+ "' alt='...'> </a>");
															// 新增的按钮绑定事件
															$(".delimgButton")
																	.bind(
																			"click",
																			function() {
																				$(
																						this)
																						.parent()
																						.remove();
																			});
														});
									});
					/**
					 * 保存作品
					 */
					$("#publishButton").click(function() {
						if (check()) {
							var isnew = true;
							if ($("#pagetitle").text() == "编辑作品") {
								isnew = false;
							}

							var imgs = "";
							$(".imgs").each(function() {
								imgs = imgs + $(this).attr("src") + " ";
							});
							var url = "json-workedit-publish";
							$.post(url, {
								goodsid : $("#goodsidInput").val(),
								gname : $("#gnameInput").val(),
								imgs : imgs,
								price : $("#priceInput").val(),
								tags : $("#tagsInput").val(),
								gdesc : $("#gdescInput").val(),
								isnew : isnew
							}, function(result) {
								if (result.statu) {
									window.location = 'myhome-index.action';
								}
							})
						} else {
							$("#modaltitle").text("警告");
							$("#modaltext").text("请填写齐全作品的信息，再提交！");
							$("#modalButton").click();
						}

					});
					/**
					 * 修改图片id
					 */
					/*
					 * function setimgId(){ var i=0;
					 * $(".delimgButton").each(function(){ $(this).attr("id",i);
					 * i++; }); }
					 */
					/**
					 * 
					 */
					$(".delimgButton").click(function() {
						$(this).parent().remove();
					});
					/**
					 * 名字不为空
					 */
					$("#gnameInput").change(
							function() {
								if ($(this).val().length < 1) {
									$(this).parent().attr("class",
											"form-group has-error");
									gnamebool = false;
								} else {
									$(this).parent().attr("class",
											"form-group has-success");
									gnamebool = true;
								}
							});
					/**
					 * 价格校验
					 */
					$("#priceInput").change(
							function() {
								var checkNum = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
								if (checkNum.test($(this).val())&&$("#priceInput").val().length>0) {
									$(this).parent().attr("class",
											"form-group has-success");
									pricebool = true;
								} else {
									$(this).parent().attr("class",
											"form-group has-error");
									pricebool = false;
								}
							});
					/**
					 * 提交前校验
					 */
					function check() {
						if (!gnamebool) {
							$("#gnameInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						if (!pricebool) {
							$("#priceInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						var count = 0;
						$(".imgs").each(function() {
							count++;
						});
						if (count == 0) {
							return false;
						}
						return true;
					}
					;
				})