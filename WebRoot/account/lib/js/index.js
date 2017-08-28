$(function(){
	//获取当前登录用户
	$.ajax(IP_config+"admin/getLoginAdmin", {
		dataType: "json",
		xhrFields: {
			withCredentials: true
		},
		type: "GET",
		success: function(data) {
			//console.log(data);
			if(data.success){
				//获取用户成功
				$("#index_username").text(data.obj.userrole);
				
				$.cookie("login_userid",data.obj.id);
				$.cookie("menu_0",data.obj.menu_0);
				$.cookie("menu_1",data.obj.menu_1);
				$.cookie("menu_2",data.obj.menu_2);
				$.cookie("menu_3",data.obj.menu_3);
			}else{
				//获取用户失败，跳转到登录页
				location.href=IP_config + "index.html";
			}
		}
	});
	//菜单点击load页面
	$(".menu").click(function(){
		//alert($(this).attr("data-original-title"));
		var html=$(this).attr("data-original-title");
		$("#index_content").load( html + ".html" );
	});
	$("#admin_logout").click(function(){
		$( 'body' ).RemindWokenSelect({
			title : '确定退出？',
			istrue : function () {
				
				$.ajax({
					url:IP_config+"admin/exit",
					dataType:"json",
					type:"post",
					timeout:1000,
					success:function(data){
						if (data.success) {
							location.href=IP_config + "index.html";
						} else {
							$( 'body' ).RemindWoken( data.msg );
						
						}
					}
				});
				
			}
		});
		
	});
});