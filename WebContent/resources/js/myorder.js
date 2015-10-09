$(document).ready(function() {
	// alert("myorder");
	var success=false;
	/**
	 * 付款
	 */
	$(".payorderButton").click(function() {
		var obj=$(this);
		var url = "json-myorder-pay";
		var id = $(this).attr("id");
		$.post(url, {
			orderid : id
		}, function(result) {
			if (result.statu) {
				$("#modaltitle").text("消息");
				$("#modaltext").text("付款成功!");
				$("#modalButton").click();
				success=true;
				
			} else {
				$("#modaltitle").text("消息");
				$("#modaltext").text("付款失败!");
				$("#modalButton").click();
				
			}
		}, 'json');
	});
	/**
	 * 取消订单
	 */
	$(".cancelorderButton").click(function() {
		var obj=$(this);
		var url = "json-myorder-cancel";
		var id = $(this).attr("id");
		$.post(url, {
			orderid : id
		}, function(result) {
			if (result.statu) {
				$("#modaltitle").text("消息");
				$("#modaltext").text("成功取消订单!");
				$("#modalButton").click();
				success=true;
			} else {
				$("#modaltitle").text("消息");
				$("#modaltext").text("取消订单失败!");
				$("#modalButton").click();
			}
		}, 'json');
	});
	
	/**
	 * 当模式框被关闭时
	 */
	$('#myModal').on('hidden.bs.modal', function() {
		if(success){
			window.location ="myorder-index";
		}
	});
	/**
	 * 付款
	 *//*
	$(".payorderButton2").click(function() {
		var url = "json-myorder-pay";
		var id = $(this).attr("id");
		$.post(url, {
			orderid : id
		}, function(result) {
			if (result.statu) {
				$("#modaltitle").text("消息");
				$("#modaltext").text("付款成功!");
				$("#modalButton").click();
			} else {
				$("#modaltitle").text("消息");
				$("#modaltext").text("付款失败!");
				$("#modalButton").click();
			}
		}, 'json');
	});
	*//**
	 * 取消订单
	 *//*
	$(".cancelorderButton2").click(function() {
		var url = "json-myorder-cancel";
		var id = $(this).attr("id");
		$.post(url, {
			orderid : id
		}, function(result) {
			if (result.statu) {
				$("#modaltitle").text("消息");
				$("#modaltext").text("成功取消订单!");
				$("#modalButton").click();
			} else {
				$("#modaltitle").text("消息");
				$("#modaltext").text("取消订单失败!");
				$("#modalButton").click();
			}
		}, 'json');
	});*/
})