/**
 * Created by Tianshan on 18-5-22.
 */
//删除一个新引证
function removeCitation(citationNum) {
	var r = confirm("是否删除?");
	if (r == true) {
		$.post("/console/citation/rest/delete", 
		{
			"_csrf":$('input[name="_csrf"]').val(),
			"citationId":$("#citationId_" + citationNum).val()
		}, 
		function(status) {
			if (status) {
				layer.msg('删除成功', {time : 500}, function() {
					 $("#citationForm_" + citationNum).remove();
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
//提交一个引证
function submitCitation(citationNum) {
    citationFormValidator(citationNum);
    if ($('#citationForm_' + citationNum).data('bootstrapValidator').isValid() &&
        ($("tr[id^='"+'citationReferencesForm_'+citationNum+"_']").length<=0 || referencesValidator('newCitationReferences_'+citationNum,2))) {
    	// 处理Ajax提交
        var obj = $('#citationForm_' + citationNum).serialize();
        var postSuccess=false;
        return $.ajax({
            type: "POST",
            url: "/console/citation/rest/add",
            data: obj,	// 要提交的表单
            dataType: "json",
            contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (msg) {
	          	if (msg.result == true) {
                    layer.msg('提交成功，请继续填写其他内容', {time: 1500}, function () {
                            if ($('#citationCollapse_' + citationNum).hasClass('in')) {
                                $('#citationCollapseTitle_' + citationNum).trigger("click");
                            }
                            $('#citationForm_' + citationNum).removeClass("panel-default");
                            $('#citationForm_' + citationNum).removeClass("panel-danger");
                            $('#citationForm_' + citationNum).addClass("panel-success");
                            $('#citationStatus_' + citationNum).removeClass("hidden");
                            postSuccess = true;
                        });
				}else{
                    layer.msg("添加失败", function () {
                    });
                    postSuccess = false;
				}
            },
            error: function() {
                postSuccess = false;
            },
        });
        if (!$('#citationCollapse_' + citationNum).hasClass('in')) {
            $('#citationCollapseTitle_' + citationNum).trigger("click");
        }
        $('#citationForm_' + citationNum).removeClass("panel-success");
        $('#citationForm_' + citationNum).addClass("panel-danger");
        $('#citationStatus_' + citationNum).addClass("hidden");
        layer.msg("添加失败", function () {
        });
        return postSuccess;
    }
    else {
        if (!$('#citationCollapse_' + citationNum).hasClass('in')) {
            $('#citationCollapseTitle_' + citationNum).trigger("click");
        }
        $('#citationForm_' + citationNum).removeClass("panel-success");
        $('#citationForm_' + citationNum).addClass("panel-danger");
        $('#citationStatus_' + citationNum).addClass("hidden");
        layer.msg("请完成此引证信息的填写", function () {
        });
        return false;
    }
}

//提交所有citation
function submitAllCitation() {
    var submitResult = true;
    $("form[id^='citationForm_']").each(function () {
        if (submitCitation($(this).attr("id").substr($(this).attr("id").lastIndexOf("_") + 1))) {
        }
        else {
            submitResult = false;
        }
    });
    return submitResult;
}

//添加一个新引证
function addCitation() {

    var countCitation = parseInt($('#countCitation').val());

    var thisCitationNum = {num: countCitation + 1};

    $('#citationForm').tmpl(thisCitationNum).appendTo('#newCitation');

    $('#countCitation').val(countCitation + 1);


    buildSelect2("citationSourcesid_" + (countCitation + 1), "console/datasource/rest/select", "请选择数据来源");


    $("#nametype_" + (countCitation + 1)).select2({
        placeholder: "请选择名称类型",
    });

    addCitationValidator(countCitation + 1);

    //名称变化后处理的函数
    $("#sciname_" + (countCitation + 1)).on("change", function () {
        $("#citationName_" + (countCitation + 1)).text($("#sciname_" + (countCitation + 1)).val());
    });
    $("#authorship_" + (countCitation + 1)).on("change", function () {
        $("#citationAuthor_" + (countCitation + 1)).text(" " + $("#authorship_" + (countCitation + 1)).val());
    });
    // 唯一标识UUID
    $.get("/console/taxon/rest/uuid", function(id){
    	$("#citationId_" + (countCitation + 1)).val(id);
    });

}
//删除一个新参考文献
function removeCitationReferences(citationNum, referencesNum) {
    $("#citationReferencesForm_" + citationNum + "_" + referencesNum).remove();
}
//添加一个新参考文献
function addCitationReferences(citationNum) {

    var countCitationReferences = parseInt($('#countCitationReferences_' + citationNum).val());

    var thisReferencesNum = {cnum: citationNum, num: countCitationReferences + 1};

    $('#citationReferencesForm').tmpl(thisReferencesNum).appendTo('#newCitationReferences_' + citationNum);

    buildSelect2("citationReferences_" + citationNum + "_" + (countCitationReferences + 1), "console/ref/rest/select", "请选择参考文献");

    $('#countCitationReferences_' + citationNum).val(countCitationReferences + 1);

    //参考文献验证规则
    addReferencesValidator(
        "newCitationReferences_"+citationNum,
        (countCitationReferences+1),
        "citationReferences_"+citationNum+"_",
        "citationReferencesPageS_"+citationNum+"_",
        "citationReferencesPageE_"+citationNum+"_"
    );

}