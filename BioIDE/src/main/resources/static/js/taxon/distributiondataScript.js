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
    $("#distributiondataForm_" + distributiondataNum).remove();
}
//提交一个描述
function submitDistributiondata(distributiondataNum) {
    distributiondataFormValidator(distributiondataNum);
    if ($('#distributiondataForm_' + distributiondataNum).data('bootstrapValidator').isValid()) {
        //处理ajax提交
    	var obj = $('#distributiondataForm_' + distributiondataNum).serialize();
        $.ajax({
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

    $("#destypeid_" + (countDistributiondata + 1)).select2({
        placeholder: "请选择描述类型"
    });
    $("#licenseid_" + (countDistributiondata + 1)).select2({
        placeholder: "请选择共享协议"
    });
    $("#language_" + (countDistributiondata + 1)).select2({
        placeholder: "请选择描述语言"
    });
    $("#distributiondatasourcesid_" + (countDistributiondata + 1)).select2({
        placeholder: "请选择数据来源"
    });
    $("#relationDes_" + (countDistributiondata + 1)).select2({
        placeholder: "请选择描述"
    });

    $('#countDistributiondata').val(countDistributiondata + 1);

    addDistributiondataValidator(countDistributiondata + 1);

    //title变化后处理的函数
    $("#destitle_" + (countDistributiondata + 1)).on("change",
        function () {
            $("#distributiondataCollapseTitle_" + (countDistributiondata + 1)).text($("#destitle_" + (countDistributiondata + 1)).val());
        });

}