$(document).ready(function() {
	// alert("loveset");
	/**
	 * 上架下架按钮变化以及反馈到数据库
	 */
	$(".shelveButton").click(function() {
		var text = $(this).text();
		var url;
		var obj = $(this);
		var editButton = "#editButton" + $(this).attr("id");
		if (text == "下架") {
			url = "json-myhome-offshelve.action";
			$.post(url, {
				goodsid : $(this).attr("id")
			}, function(result) {
				if (result.statu == true) {
					$("#modaltitle").text("消息");
					$("#modaltext").text("成功下架作品！");
					$("#modalButton").click();
					obj.text("上架");
					$(obj).attr("class", "btn btn-success shelveButton");
					$(editButton).hide();
				}
			}, 'json');
		} else if (text == "上架") {
			url = "json-myhome-shelve.action";
			$.post(url, {
				goodsid : $(this).attr("id")
			}, function(result) {
				if (result.statu == true) {
					$("#modaltitle").text("消息");
					$("#modaltext").text("成功上架作品！");
					$("#modalButton").click();
					obj.text("下架");
					$(obj).attr("class", "btn btn-link shelveButton");
					$(editButton).show();
				}
			}, 'json');
		}

	});

})