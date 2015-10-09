$(document).ready(function() {
	// alert("searchtag");
	/**
	 * 订阅标签
	 */
	$("#subButton").click(function() {
		var obj = $(this);
		var tagid = $(".title").attr("id");
		var title = $(this).text();
		var classtype;
		var message;
		var url;
		if (title == "已订阅") {
			url = "json-searchtag-unsubscribe.action";
			title = "未订阅";
			classtype = "btn btn-default btn-sm";
			message = "取消订阅成功!";
		} else {
			url = "json-searchtag-subscribe.action";
			title = "已订阅";
			classtype= "btn btn-success btn-sm ";
			message = "订阅成功!";
		}
		$.post(url, {
			tagid : tagid
		}, function(result) {
			if (result.statu == true) {
				obj.text(title);
				obj.attr("class", classtype);
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

})