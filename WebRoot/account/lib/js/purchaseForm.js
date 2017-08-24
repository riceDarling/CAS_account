$("#accountPurchaseSubmit").click(function(){
	$( 'body' ).RemindWokenSelect({
		title : '确定提交？',
		istrue : function () {
			var data_json={};
			var inquirynum=$("#accountPurchaseForm_contract").val();
			var title=$("#title").val();
			var checker=$("#accountPurchaseForm_checker").val();
			var purchase_id=$("#title").attr("purchase_id");
			data_json["id"]=purchase_id;
			data_json["inquirynum"]=inquirynum;
			data_json["title"]=title;
			data_json["checker"]=checker;
			
			/*if ( inquirynum == '' ) {
				$( 'body' ).RemindWoken( '必须选择合同名称' );
				return;
			}
			
			if ( $("#accountPurchaseForm_checker").val()) {
				$( 'body' ).RemindWoken( '必须选择审核人' );
				return;
			}*/
			
			var listjson = new Array();
			$("#secondPageTbody tr").each(function(){
				var json={};
				json["materialcode"]=$(this).children("td").eq(0).attr("materialcode");
				json["unitprice"]=$(this).children("td").eq(2).text();
				json["quantity"]=$(this).children("td").eq(3).find("input").val();
				json["totlemoney"]=$(this).children("td").eq(4).find("input").val();
				listjson.push(json);
			});
			data_json["accountPurchaseDetailList"]=listjson;
			//console.log(data_json);
			
			
			$.ajax({
				url : IP_config + "accountPurchase/save",
				dataType: "json",
				contentType:"application/json;charset=UTF-8",
				data:JSON.stringify(data_json),
				xhrFields: {
					withCredentials: true
				},
				type: "POST",
				success: function(data) {
					//console.log(data);
					if(data.success){
						//提交成功
						$( 'body' ).RemindWokenSuccess( '操作成功' );
							$.ajax( {
								url : IP_config + "accountPurchase/selectView",
								dataType: "json",
								type: "POST",
								data :  {
									title : $("#purchase_title").val(),
									checker : userid,
									procInsId : $( '#procInsId' ).val(),
									startTime : $( '#startTime' ).val(),
									endTime : $( '#endTime' ).val(),
									pageSize : 30,
									currentPage : 1
								},
								async : false,
								success: function( data1 ) {
									if ( data1.success ) {
										
										var data_total = data1.obj.totalCount;
										var table_data = data1.obj.list;
										
										dataTable( table_data , data_total );
									} else {
										$( 'body' ).RemindWoken( '查询失败' );
									}
								}
							});
							setTimeout(function(){
								$(".account-panel").remove();
							},600);
					}else{
						//提交失败提示
						$( 'body' ).RemindWokenError( '操作失败' );
					}
				},
				error : function (  ) {
					$( 'body' ).RemindWokenError( '操作失败' );
				}
			});
		}
	
	});
});
var accountPurchaseAudit_id = $.cookie("accountPurchaseAudit_id");
//删除
$("#accountPurchaseDanger").click(function(){
	$( 'body' ).RemindWokenSelect({
		title : '确定删除？',
		istrue : function () {
		$.ajax({
			url : IP_config + "accountPurchase/delete",
			dataType: "json",
			data:{
				"accountPurchaseid" : accountPurchaseAudit_id
			},
			xhrFields: {
				withCredentials: true
			},
			type: "POST",
			success: function(data) {
				//console.log(data);
				if(data.success){
					//提交成功
					$( 'body' ).RemindWokenSuccess( '操作成功' );
					$("#index_content").load("accountPurchaseList.html");
					setTimeout(function(){
						$(".account-panel").remove();
					},600);
				}else{
					//提交失败提示
					$( 'body' ).RemindWokenError( '操作失败' );
				}
			},
			error : function (  ) {
				$( 'body' ).RemindWokenError( '操作失败' );
			}
		});
	}
})
});

