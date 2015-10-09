$(document).ready(function() {
	// alert("follow");
	/**
	 * 关注用户
	 */
	$("#followButton").click(function() {
		var obj = $(this);
		var userid = $(".title").attr("id");
		var title = $(this).attr("title");
		var style;
		var message;
		var url;
		if (title == "关注该创作者") {
			url = "json-authorhome-follow.action";
			title = "取消关注该创作者";
			style = "color: #FF8C69; font-size: 19px";
			message="关注成功!";
		} else {
			url = "json-authorhome-unfollow.action";
			title = "关注该创作者";
			style = "color: #D4D4D4; font-size: 19px";
			message="取消关注成功!";
		}
		$.post(url, {
			userid : userid
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

})