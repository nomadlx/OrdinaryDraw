$(document).ready(function() {
	// alert("shopcart");
	/**
	 * 一进购物车就要计算对应的价格和全选框状态
	 */
	setPrice();
	setCount();
	setAllCheckbox();
	var balanced=false;
	/**
	 * 从购物车中移除商品
	 */
	$(".deleteButton").click(function() {
		var url = "json-shopcart-remove.action";
		var obj = $(this).parent().parent().parent().parent().parent();
		$.post(url, {
			goodsid : $(this).attr("id")
		}, function(result) {
			if (result.statu == true) {
				$("#modaltitle").text("消息");
				$("#modaltext").text("移除商品成功!");
				$("#modalButton").click();
				obj.remove();
				setPrice();
				setCount();
				setAllCheckbox();
			}
		}, 'json');
	});
	/**
	 * 全选框选中和去除动态效果
	 */
	$("#allselectCheckbox").click(function() {
		var statu = $("#allselectCheckbox").prop("checked");
		$(".goodsCheckbox").prop("checked", statu);
		setPrice();
		setCount();
	});
	/**
	 * 单件商品的选中状态发生改变，对应价格发生变化
	 */
	$(".goodsCheckbox").change(function() {
		setPrice();
		setCount();
		setAllCheckbox();
	});
	/**
	 * 购物车结算提交
	 */
	$("#balanceButton").click(function() {
		var str = "";
		$(".goodsCheckbox").each(function() {
			if ($(this).prop("checked")) {
				str = str + $(this).attr("name")+",";
			}
		});
		var url="json-shopcart-balance.action";
		$.post(url, {
			data : str
		}, function(result) {
			if (result.statu) {
				balanced=true;
				$("#modaltitle").text("消息");
				$("#modaltext").text("生成订单成功!");
				$("#modalButton").click();
				
			}
		}, 'json');
	});
	/**
	 * 动态设置购物车价格
	 */
	function setPrice() {
		var value = 0;
		$(".goodsCheckbox").each(function() {
			if ($(this).prop("checked")) {
				value = value + parseFloat($(this).val());
			}
		});
		$("#allPrice").text(value);
	}
	/**
	 * 动态设置已经选中商品数量
	 */
	function setCount() {
		var count = 0;
		$(".goodsCheckbox").each(function() {
			if ($(this).prop("checked")) {
				count++;
			}
		});
		$("#allCount").text(count);
	}
	/**
	 * 动态设置全选框
	 */
	function setAllCheckbox() {
		var bool = true;
		$(".goodsCheckbox").each(function() {
			if (!$(this).prop("checked")) {
				bool = false;
			}
		});
		$("#allselectCheckbox").prop("checked", bool);
	}
	/**
	 * 当模式框被关闭时
	 */
	$('#myModal').on('hidden.bs.modal', function() {
		if(balanced){
			window.location = 'myorder-index.action';
		}
	});
})