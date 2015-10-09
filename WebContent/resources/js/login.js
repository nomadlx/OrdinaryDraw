$(document).ready(function() {
	/**
	 * 清楚输入错误提示
	 */
	$.post("json-login-clear.action", {

	}, function(result) {

	}, 'json');
	/**
	 * 根据用户输入的邮箱，动态加载头像
	 */
	$("#emailInput").change(function() {
		var url = "json-login-getimg.action";
		$.post(url, {
			email : $("#emailInput").val()
		}, function(result) {
			$("#faceImg").attr("src", result.img);
		}, 'json');
	});

})