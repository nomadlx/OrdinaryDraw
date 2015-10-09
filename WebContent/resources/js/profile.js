$(document).ready(
		function() {
			// alert("register");
			/**
			 * 获取省份列表
			 */
			var pwdbool=false;
			var repwdbool=false;
			var url = "json-profile-getaddrs.action";
			$.post(url, {

			}, function(result) {
				for (var i = 0; i < result.count; i++) {
					if (i == result.index) {
						$("#addrInput").append(
								"<option value='" + i + "' selected>"
										+ result.addr[i] + "</option>");
					} else {
						$("#addrInput").append(
								"<option value='" + i + "'>" + result.addr[i]
										+ "</option>");
					}

				}
			}, 'json');
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
			 * 修改基本信息
			 */
			$("#updateButton").click(function() {
				if (check()) {
					var url = "json-profile-update"
					$.post(url, {
						img : $("#userImg").attr("src"),
						email : $("#emailInput").val(),
						uname : $("#unameInput").val(),
						addrindex : $("#addrInput").val(),
						udesc : $("#udescInput").val()
					}, function(result) {
						if (result.statu) {
							$("#modaltitle").text("消息");
							$("#modaltext").text("保存个人信息成功！");
							$("#modalButton").click();
						} else {
							$("#modaltitle").text("警告");
							$("#modaltext").text("保存个人信息失败！");
							$("#modalButton").click();
						}
					}, 'json');
				} else {
					$("#modaltitle").text("警告");
					$("#modaltext").text("用户名不能为空！");
					$("#modalButton").click();
				}

			});
			/**
			 * 更新密码
			 */
			$("#updatepwdButton").click(function() {
				if(pwdbool&&repwdbool){
					var url = "json-profile-updatepwd"
						$.post(url, {
							oldpwd : $("#oldpwdInput").val(),
							newpwd : $("#newpwdInput").val()
						}, function(result) {
							if (result.statu) {
								$("#modaltitle").text("消息");
								$("#modaltext").text("修改密码成功！");
								$("#modalButton").click();
							} else {
								$("#modaltitle").text("警告");
								$("#modaltext").text("旧密码输入错误！");
								$("#modalButton").click();
							}
							$("#oldpwdInput").val("");
							$("#newpwdInput").val("");
							$("#renewpwdInput").val("");
							pwdbool=false;
							repwdbool=false;
						}, 'json');
				}else{
					$("#modaltitle").text("警告");
					$("#modaltext").text("输入的密码不合法,请重新输入！");
					$("#modalButton").click();
				}
				
			});
			/**
			 * 用户名不能为空
			 */
			$("#unameInput").change(function() {
				if ($(this).val().length < 1) {
					$(this).parent().attr("class", "form-group has-error");
					gnamebool = false;
				} else {
					$(this).parent().attr("class", "form-group has-success");
					gnamebool = true;
				}
			});
			/**
			 * 密码长度要求
			 */
			$("#newpwdInput").change(function() {
				$(this).parent().attr("class", "form-group has-error");
				var checkNum = /^.{6,}$/;
				if (checkNum.test($(this).val())) {
					$(this).parent().attr("class", "form-group has-success");
					pwdbool = true;
				} else {
					$(this).parent().attr("class", "form-group has-error");
					pwdbool = false;
				}
			});
			/**
			 * 密码一致
			 */
			$("#renewpwdInput").change(
					function() {
						var checkNum = /^.{6,}$/;
						if (checkNum.test($(this).val())
								&& $(this).val() == $("#newpwdInput").val()) {
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
			 * 修改个人信息校验
			 */
			function check() {
				if ($("#unameInput").val().length < 1) {
					$("#unameInput").parent().attr("class",
							"form-group has-error");
					return false;
				}
				return true;
			}
		})