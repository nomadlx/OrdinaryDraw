$(document)
		.ready(
				function() {
					// alert("register");
					/**
					 * 获取省份列表
					 */
					var emailbool = false;
					var pwdbool = false;
					var repwdbool = false;
					var url = "json-register-getaddrs.action";
					$.post(url, {

					}, function(result) {
						for (var i = 0; i < result.count; i++) {
							$("#addrInput").append(
									"<option value='" + i + "'>"
											+ result.addr[i] + "</option>");
						}
					}, 'json');
					/**
					 * 邮箱校验
					 */
					$("#emailInput")
							.change(
									function() {
										var checkNum = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
										if (checkNum.test($(this).val())) {
											$(this).parent().attr("class",
													"form-group has-success");
											emailbool = true;
										} else {
											$(this).parent().attr("class",
													"form-group has-error");
											emailbool = false;
										}

									});
					/**
					 * 密码长度要求
					 */
					$("#pwdInput").change(
							function() {
								$(this).parent().attr("class",
										"form-group has-error");
								var checkNum = /^.{6,}$/;
								if (checkNum.test($(this).val())) {
									$(this).parent().attr("class",
											"form-group has-success");
									pwdbool = true;
								} else {
									$(this).parent().attr("class",
											"form-group has-error");
									pwdbool = false;
								}
							});
					/**
					 * 密码一致
					 */
					$("#repwdInput").change(
							function() {
								var checkNum = /^.{6,}$/;
								if (checkNum.test($(this).val())
										&& $(this).val() == $("#pwdInput")
												.val()) {
									$(this).parent().attr("class",
											"form-group has-success");
									repwdbool = true;
								} else {
									$(this).parent().attr("class",
											"form-group has-error");
									repwdbool = false;
								}
							});
					/**
					 * 上传头像图片
					 */
					$("#fileInput").change(function() {
						// 提交表单且回调函数
						$('#imgForm').ajaxSubmit(function(result) {
							$("#userImg").attr("src", result.path);
						});
					});
					/**
					 * 注册用户
					 */
					$("#registerButton").click(function() {
						if (check()) {
							var url = "json-register-register"
							$.post(url, {
								email : $("#emailInput").val(),
								img : $("#userImg").attr("src"),
								pwd : $("#pwdInput").val(),
								uname : $("#unameInput").val(),
								addrindex : $("#addrInput").val(),
								udesc : $("#udescInput").val()
							}, function(result) {
								if (result.statu) {
									$("#modaltitle").text("消息");
									$("#modaltext").text("注册成功！");
									$("#modalButton").click();

								} else {
									$("#modaltitle").text("警告");
									$("#modaltext").text("注册失败，该邮箱已注册！");
									$("#modalButton").click();
								}

							}, 'json');
						} else {
							$("#modaltitle").text("警告");
							$("#modaltext").text("填写的信息不合法，请重新填写后提交！");
							$("#modalButton").click();
						}

					});
					/**
					 * 注册成功跳转到登录页面
					 */
					$("#closeButton").click(function() {
						if ($("#modaltitle").text() == "消息") {
							window.location = 'login.jsp';
						}
					});
					/**
					 * 校验是否可以提交表单
					 */
					function check() {
						if (!emailbool) {
							$("#emailInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						if (!pwdbool) {
							$("#pwdInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						if (!repwdbool) {
							$("#repwdInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						if ($("#unameInput").val() == "") {
							$("#unameInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						if ($("#udescInput").val() == "") {
							$("#udescInput").parent().attr("class",
									"form-group has-error");
							return false;
						}
						return true;

					}
				})