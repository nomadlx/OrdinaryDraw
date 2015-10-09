$(document).ready(function() {
	/**
	 * 取消关注用户
	 */
	$("#searchButton").click(function() {
		var url = "json-common-gettagid.action";
		$.post(url, {
			tname : $("#tagInput").val()
		}, function(result) {
			if (result.statu == true) {
				window.location = 'searchtag-index?tagid=' + result.tagid;
			} else {
				$("#modaltitle").text("消息");
				$("#modaltext").text("对不起,暂无该标签相关作品！");
				$("#modalButton").click();
			}
		}, 'json');

	});

})