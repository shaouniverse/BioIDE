/**
 * Created by Tianshan on 18-5-22.
 */

//选择关系
function selectRelation(distributiondataNum, relationId, relationText) {
    $("#relationId_" + distributiondataNum).val(relationId);
    $("#relationBtn_" + distributiondataNum).text(relationText);
}
//删除一个新描述
function removeDistributiondata(distributiondataNum) {
    var r = confirm("是否删除?");
	if (r == true) {
		$.post("/console/distributiondata/rest/delete",
		{
	        "_csrf":$('input[name="_csrf"]').val(),
	        "distributiondataId":$("#distributiondataId_" + distributiondataNum).val()
	    },
		function(status) {
			if (status) {
				layer.msg('删除成功', {time : 500}, 
				function() {
					$("#distributiondataForm_" + distributiondataNum).remove();
				})
			}else {
				layer.msg('操作失败', function(){})
			}
		})
	}else {
		layer.msg(
			'操作取消', 
			{time : 500}
		)
	}
    
}
//提交一个描述
function submitDistributiondata(distributiondataNum) {
    distributiondataFormValidator(distributiondataNum);
    if ($('#distributiondataForm_' + distributiondataNum).data('bootstrapValidator').isValid()) {
        //处理ajax提交
    	var obj = $('#distributiondataForm_' + distributiondataNum).serialize();
        return $.ajax({
          type: "POST",
          url: "/console/distributiondata/rest/add",
          data: obj,	// 要提交的表单
          dataType: "json",
          contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
          success: function (msg) {
        	if (msg.result == true) {
        		layer.msg('提交成功，请继续填写其他内容',
        	            {time: 1500},
        	            function () {
        	                if ($('#distributiondataCollapse_' + distributiondataNum).hasClass('in')) {
        	                    $('#distributiondataCollapseTitle_' + distributiondataNum).trigger("click");
        	                }
        	                $('#distributiondataForm_' + distributiondataNum).removeClass("panel-default");
        	                $('#distributiondataForm_' + distributiondataNum).removeClass("panel-danger");
        	                $('#distributiondataForm_' + distributiondataNum).addClass("panel-success");
        	                $('#distributiondataStatus_' + distributiondataNum).removeClass("hidden");
        	            });
        	        return true;
			}else{
				layer.msg("添加失败！", {time: 1000});
                return false;
			}
          }
        });
    }
    else {
        if (!$('#distributiondataCollapse_' + distributiondataNum).hasClass('in')) {
            $('#distributiondataCollapseTitle_' + distributiondataNum).trigger("click");
        }
        $('#distributiondataForm_' + distributiondataNum).removeClass("panel-success");
        $('#distributiondataForm_' + distributiondataNum).addClass("panel-default");
        $('#distributiondataStatus_' + distributiondataNum).addClass("hidden");
        $('#tab-distributiondata').addClass("alert alert-danger");
        layer.msg("请完成此分布数据的填写", function () {
        });
        return false;
    }
}

//提交所有distributiondata
function submitAllDistributiondata() {
    var submitResult = true;
    $('#tab-distributiondata').removeClass("alert-danger");
    $('#tab-distributiondata').removeClass("alert");
    $("form[id^='distributiondataForm_']").each(function () {
        if (submitDistributiondata($(this).attr("id").substr($(this).attr("id").lastIndexOf("_") + 1))) {
        }
        else {
            submitResult = false;
        }
    });
    return submitResult;
}
//添加一个新描述
function addDistributiondata() {

    var countDistributiondata = parseInt($('#countDistributiondata').val());

    var thisDistributiondataNum = {
        num: countDistributiondata + 1
    };

    $('#distributiondataForm').tmpl(thisDistributiondataNum).appendTo('#newDistributiondata');

    // 行政区下拉选
    buildSelect("geojson_" + (countDistributiondata + 1), "console/geoobject/rest/select/administrative", "请选择行政区分布地");
    // $("#licenseid_" + (countDistributiondata + 1)).select2({
    //     placeholder: "请选择共享协议"
    // });
    // $("#language_" + (countDistributiondata + 1)).select2({
    //     placeholder: "请选择描述语言"
    // });
    // $("#distributiondatasourcesid_" + (countDistributiondata + 1)).select2({
    //     placeholder: "请选择数据来源"
    // });
    // $("#relationDes_" + (countDistributiondata + 1)).select2({
    //     placeholder: "请选择描述"
    // });

    // //左右多选
    // var geojson = $('#geojson_'+(countDistributiondata + 1)).bootstrapDualListbox({
    //     nonSelectedListLabel: '所有地理位置',
    //     selectedListLabel: '已记录的地理位置',
    //     preserveSelectionOnMove: 'moved',
    //     moveOnSelect: false,
    // });
    //
    // buildMultiple('#geojson_'+(countDistributiondata + 1));

    $('#countDistributiondata').val(countDistributiondata + 1);

    //addDistributiondataValidator(countDistributiondata + 1);

    //title变化后处理的函数
    $("#destitle_" + (countDistributiondata + 1)).on("change",
        function () {
            $("#distributiondataCollapseTitle_" + (countDistributiondata + 1)).text($("#destitle_" + (countDistributiondata + 1)).val());
        });
    
    // 唯一标识UUID
    $.get("/console/taxon/rest/uuid", function(id){
    	$("#distributiondataId_" + (countDistributiondata + 1)).val(id);
    });
}