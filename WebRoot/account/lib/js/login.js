$(function(){
	//登录
	$("#btn_login").click(function(){
		$.ajax(IP_config+"admin/login", {
			dataType: "json",
			data: $("#form_login").serialize(),
			type: "POST",
			success: function(data) {
				//console.log(data);
				if(data.success){
				
					//登录成功跳页
					location.href="account/index.html";
				}else{
					//登录失败提示
					$( 'body' ).RemindWoken( data.msg );
				}
			}
		});
	});
	//键盘回车事件
	$(document).keydown(function(event) {
		if (event.keyCode == 13) {
			$.ajax(IP_config+"admin/login", {
				dataType: "json",
				data: $("#form_login").serialize(),
				xhrFields: {
					withCredentials: true
				},
				type: "POST",
				success: function(data) {
					//console.log(data);
					if(data.success){
						//登录成功跳页
						location.href="account/index.html";
					}else{
						//登录失败提示
						$( 'body' ).RemindWoken( data.msg );
					}
				}
			});
		}
	});
});

