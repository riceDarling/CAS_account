
$("#requisitionForm_table_add_tr").click(function(){
	var tr_num = $("#requisitionForm_table tbody tr").length + 1;
	var html="";
	html+='<tr>'
		+'<td>'+tr_num+'</td>'
		+'<td style=""></td><td>'
		+'<select  tabindex="1" class="materialname_select form-control">'
		+'<option value=""></option>';
		for (i in materials) {
			var material = materials[i];
			html+='<option value="'+material.materialName+'" materialnum="'+material.materialNum+'" materialsize="'+material.size+'">'+material.materialName+'</opption>';
		}
		html+='</select>'
		+'</td>'
		+'<td></td><td>'
		+'<input type="text" class="input-small required form-control" name="">'
		+'</td><td>'
		+'<select  tabindex="1" class="unit_selected form-control">'
		+'<option value=""></option>';
		for (i in units) {
			var unit = units[i];
			html+='<option value="'+unit.id+'">'+unit.name+'</option>';
		}
		html+='</select>'
		+'</td><td>'
		+'<input type="text" class="input-small required form-control" name="">'
		+'</td><td>'
		+'<i class="fa fa-minus-circle requisitionForm_table_del_tr"></i>'
		+'</td>'
		+'</tr>';
	$("#signup_form table tbody").append(html);
	tr_num++;
	$(".materialname_select").select2();
	$(".unit_selected").select2();
});

$(function(){
	
});
$("#signup_form table tbody").on("click",".requisitionForm_table_del_tr",function(){
	$(this).parent().parent().remove();
	var num_xk=1;
	$("#requisitionForm_table tbody tr").each(function(){
		$(this).children("td").eq(0).text(num_xk);
		num_xk++;
	});
});

$("#signup_form table tbody").on("change",".materialname_select",function(){
	//alert($(this).val());
	//alert($(this).children("option:selected").attr("materialnum"));
	materialnum=$(this).children("option:selected").attr("materialnum");
	materialsize=$(this).children("option:selected").attr("materialsize");
	
	$(this).parent("td").siblings("td").eq(1).text(materialnum);
	$(this).parent("td").siblings("td").eq(2).text(materialsize);
});
$("#signup_form").on("click","#requisitionFormSubmit",function(){
	/*var listjson = {};
	listjson["title"]=$("#title").val();
	listjson["receivedate"]=$("#receivedate").val();
	listjson["reason"]=$("#reason").val();
	listjson["checker"]=$("#requisitionForm_checker").val();
	var listjsonlist = new Array();
	
	$("#requisitionForm_table tbody tr").each(function(){
		var json={};
		json["materialcode"]=$(this).children("td").eq(1).text();
		json["quantitiy"]=$(this).children("td").eq(4).find("input").val();
		json["units"]=$(this).children("td").eq(5).find("select").val();
		json["remarks"]=$(this).children("td").eq(6).find("input").val();
		//alert(json["materialcode"]+json["quantitiy"]+json["units"]);
		listjsonlist.push(json);
	});
	listjson["accountRequisitionDetailList"]=listjsonlist;
	console.log(listjson);
	
	$.ajax("http://"+IP_config+"/account/accountRequisition/save", {
		dataType: "json",
		contentType:"application/json;charset=UTF-8",
		data:JSON.stringify(listjson),
		xhrFields: {
			withCredentials: true
		},
		type: "POST",
		success: function(data) {
			//console.log(data);
			if(data.success){
				//提交成功
				layer.alert(data.msg);
				$("#index_content").load("requisitionList.html");
			}else{
				//提交失败提示
				layer.alert(data.msg);
			}
		}
	});*/
});