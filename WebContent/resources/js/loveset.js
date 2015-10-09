$(document).ready(function() {
	//alert("loveset");
	/**
	 * 取消喜爱
	 */
	$(".unlikeButton").click(function() {
		var url = "json-loveset-unlike.action";
		var obj = $(this).parent().parent();
		$.post(url, {
			goodsid : $(this).attr("id")
		}, function(result) {
			if (result.statu == true) {
				obj.remove();
				$("#modaltitle").text("消息");
				$("#modaltext").text("成功取消喜爱！");
				$("#modalButton").click();
			}
		}, 'json');

	});

})