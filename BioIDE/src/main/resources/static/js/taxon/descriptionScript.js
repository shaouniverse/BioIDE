/**
 * Created by Tianshan on 18-5-22.
 */

//选择关系
function selectRelation(descriptionNum, relationId, relationText) {
    $("#relationId_" + descriptionNum).val(relationId);
    $("#relationBtn_" + descriptionNum).text(relationText);
}
//删除一个新描述
function removeDescription(descriptionNum) {
	var obj = $("#descriptionId_" + descriptionNum).val();
		var r = confirm("是否删除?");
		if (r == true) {
			$.post("/console/description/rest/delete", {descriptionId:obj}, 
				function(status) {
					if (status) {
						layer.msg('删除成功', {time : 500}, 
						function() {
							$("#descriptionForm_" + descriptionNum).remove();
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
function submitDescription(descriptionNum) {
    descriptionFormValidator(descriptionNum);
    if ($('#descriptionForm_' + descriptionNum).data('bootstrapValidator').isValid() &&
        ($("tr[id^='"+'descriptionReferencesForm_'+descriptionNum+"_']").length<=0 || referencesValidator('newDescriptionReferences_'+descriptionNum,3))) {
        //处理ajax提交
    	var obj = $('#descriptionForm_' + descriptionNum).serialize();
    	var postSuccess=false;
    	$.ajax({
            type: "POST",
            url: "/console/description/rest/add",
            data: obj,	// 要提交的表单
            dataType: "json",
            contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (msg) {
              if (msg.result == true) {
                  layer.msg(
                          '提交成功，请继续填写其他内容',
                          {time: 1500},
                          function () {
                              if ($('#descriptionCollapse_' + descriptionNum).hasClass('in')) {
                                  $('#descriptionCollapseTitle_' + descriptionNum).trigger("click");
                              }
                              $('#descriptionForm_' + descriptionNum).removeClass("panel-default");
                              $('#descriptionForm_' + descriptionNum).removeClass("panel-danger");
                              $('#descriptionForm_' + descriptionNum).addClass("panel-success");
                              $('#descriptionStatus_' + descriptionNum).removeClass("hidden");
                              postSuccess = true;
                          });
              }else{
                  layer.msg("添加失败！", {time: 1000});
                  postSuccess = false;
              }
            },
            error: function() {
          	  postSuccess = false;
            }
         });
    	if (!$('#descriptionCollapse_' + descriptionNum).hasClass('in')) {
            $('#descriptionCollapseTitle_' + descriptionNum).trigger("click");
        }
        $('#descriptionForm_' + descriptionNum).removeClass("panel-success");
        $('#descriptionForm_' + descriptionNum).addClass("panel-danger");
        $('#descriptionStatus_' + descriptionNum).addClass("hidden");
        layer.msg("添加失败", function () {
        });
        return postSuccess;
    }
    else {
        if (!$('#descriptionCollapse_' + descriptionNum).hasClass('in')) {
            $('#descriptionCollapseTitle_' + descriptionNum).trigger("click");
        }
        $('#descriptionForm_' + descriptionNum).removeClass("panel-success");
        $('#descriptionForm_' + descriptionNum).addClass("panel-danger");
        $('#descriptionStatus_' + descriptionNum).addClass("hidden");
        $('#tab-description').addClass("alert alert-danger");
        layer.msg("请完成此文本描述的填写", function () {
        });
        return false;
    }
}

//提交所有description
function submitAllDescription() {
    var submitResult = true;
    $('#tab-description').removeClass("alert-danger");
    $('#tab-description').removeClass("alert");
    $("form[id^='descriptionForm_']").each(function () {
        if (submitDescription($(this).attr("id").substr($(this).attr("id").lastIndexOf("_") + 1))) {
        }
        else {
            submitResult = false;
        }
    });
    return submitResult;
}
//添加一个新描述
function addDescription() {

    var countDescription = parseInt($('#countDescription').val());

    var thisDescriptionNum = {num: countDescription + 1};

    $('#descriptionForm').tmpl(thisDescriptionNum).appendTo('#newDescription');
    // 描述类型下拉选
    buildSelect2("destypeid_" + (countDescription + 1), "console/descriptiontype/rest/select", "请选择描述类型");
    // 共享协议下拉选
    buildSelect2("licenseid_" + (countDescription + 1), "console/license/rest/select", "请选择共享协议");
    $("#language_" + (countDescription + 1)).select2({
        placeholder: "请选择描述语言"
    });
    // 数据源下拉选
    buildSelect2("descriptionsourcesid_" + (countDescription + 1), "console/datasource/rest/select", "请选择数据来源");
    $("#relationDes_" + (countDescription + 1)).select2({
        placeholder: "请选择描述"
    });

    $('#countDescription').val(countDescription + 1);

    addDescriptionValidator(countDescription + 1);

    //title变化后处理的函数
    $("#destitle_" + (countDescription + 1)).on("change", function () {
        $("#descriptionCollapseTitle_" + (countDescription + 1)).text($("#destitle_" + (countDescription + 1)).val());
    });
    
    // 唯一标识UUID
    $.get("/console/taxon/rest/uuid", function(id){
    	$("#descriptionId_" + (countDescription + 1)).val(id);
    });
}

//选择参考文献类型
function selectDescriptionReferences(descriptionNum, referencesNum, referencesId, referencesText) {
    $("#descriptionReferencesType_" + descriptionNum + "_" + referencesNum).val(referencesId);
    $("#descriptionReferencesBtn_" + descriptionNum + "_" + referencesNum).text(referencesText);
}
//删除一个新参考文献
function removeDescriptionReferences(descriptionNum, referencesNum) {
    $("#descriptionReferencesForm_" + descriptionNum + "_" + referencesNum).remove();
}
//添加一个新参考文献
function addDescriptionReferences(descriptionNum) {

    var countDescriptionReferences = parseInt($('#countDescriptionReferences_' + descriptionNum).val());

    var thisReferencesNum = {dnum: descriptionNum, num: countDescriptionReferences + 1};

    $('#descriptionReferencesForm').tmpl(thisReferencesNum).appendTo('#newDescriptionReferences_' + descriptionNum);

    buildSelect2("descriptionReferences_" + descriptionNum + "_" + (countDescriptionReferences + 1), "console/ref/rest/select", "请选择参考文献");

    //参考文献验证规则
    addReferencesValidator(
        "newDescriptionReferences_"+descriptionNum,
        (countDescriptionReferences+1),
        "descriptionReferences_"+descriptionNum+"_",
        "descriptionReferencesPageS_"+descriptionNum+"_",
        "descriptionReferencesPageE_"+descriptionNum+"_"
    );

    $('#countDescriptionReferences_' + descriptionNum).val(countDescriptionReferences + 1);

}
