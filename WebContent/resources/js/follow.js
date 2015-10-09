$(document).ready(function() {
	// alert("follow");
	/**
	 * 取消关注用户
	 */
	$(".unfollowButton").click(function() {
		var url = "json-follow-unfollow.action";
		var obj = $(this).parent();
		$.post(url, {
			userid : $(this).attr("id")
		}, function(result) {
			if (result.statu == true) {
				obj.parent().remove();
				$("#modaltitle").text("消息");
				$("#modaltext").text("成功取消关注！");
				$("#modalButton").click();
			}
		}, 'json');

	});
	/**
	 * 查询用户
	 */
	$("#searchuserButton").click(function() {
		$("#searchForm").submit();
	});
})