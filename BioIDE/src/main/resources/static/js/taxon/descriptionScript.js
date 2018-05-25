/**
 * Created by Tianshan on 18-5-22.
 */

//选择关系
function selectRelation (descriptionNum,relationId,relationText) {
    $("#relationId_"+descriptionNum).val(relationId);
    $("#relationBtn_"+descriptionNum).text(relationText);
}
//删除一个新描述
function removeDescription (descriptionNum) {
    $("#descriptionForm_"+descriptionNum).remove();
}
//提交一个描述
function submitDescription(descriptionNum) {
    descriptionFormValidator(descriptionNum);
    if($('#descriptionForm_'+descriptionNum).data('bootstrapValidator').isValid()){
        //处理ajax提交
        //
        //
        //
        layer.msg('提交成功，请继续填写其他内容',
            {
                time: 1500, //1.5s后自动关闭
            },
            function(){
                if($('#descriptionCollapse_'+descriptionNum).hasClass('in')){
                    $('#descriptionCollapseTitle_'+descriptionNum).trigger("click");
                }
                $('#descriptionForm_'+descriptionNum).removeClass("panel-default");
                $('#descriptionForm_'+descriptionNum).removeClass("panel-danger");
                $('#descriptionForm_'+descriptionNum).addClass("panel-success");
                $('#descriptionStatus_'+descriptionNum).removeClass("hidden");
                return true;
            });
        return true;
    }
    else{
        if(!$('#descriptionCollapse_'+descriptionNum).hasClass('in')){
            $('#descriptionCollapseTitle_'+descriptionNum).trigger("click");
        }
        $('#descriptionForm_'+descriptionNum).removeClass("panel-success");
        $('#descriptionForm_'+descriptionNum).addClass("panel-danger");
        $('#descriptionStatus_'+descriptionNum).addClass("hidden");
        layer.msg("请完成此文本描述的填写", function(){
        });
        return false;
    }
}

//提交所有description
function submitAllDescription() {
    var submitResult=true;
    $("form[id^='descriptionForm_']").each(function () {
        if(submitDescription($(this).attr("id").substr($(this).attr("id").lastIndexOf("_")+1))){
        }
        else{
            submitResult= false;
        }
    });
    return submitResult;
}
//添加一个新描述
function addDescription () {

    var countDescription=parseInt($('#countDescription').val());

    var thisDescriptionNum = {num: countDescription+1};

    $('#descriptionForm').tmpl(thisDescriptionNum).appendTo('#newDescription');

    $("#destypeid_"+(countDescription+1)).select2({
        placeholder: "请选择描述类型"
    });
	buildSelect2("destypeid_" + (countDescription+1), "console/descriptiontype/rest/select");
    $("#licenseid_"+(countDescription+1)).select2({
        placeholder: "请选择共享协议"
    });
	buildSelect2("licenseid_" + (countDescription+1), "console/license/rest/select");
    $("#language_"+(countDescription+1)).select2({
        placeholder: "请选择描述语言"
    });
    $("#descriptionsourcesid_"+(countDescription+1)).select2({
        placeholder: "请选择数据来源"
    });
	buildSelect2("descriptionsourcesid_" + (countDescription+1), "console/datasource/rest/select");
    $("#relationDes_"+(countDescription+1)).select2({
        placeholder: "请选择描述"
    });

    $('#countDescription').val(countDescription+1);

    addDescriptionValidator(countDescription + 1);

    //title变化后处理的函数
    $("#destitle_"+(countDescription+1)).on("change", function(){
        $("#descriptionCollapseTitle_"+(countDescription+1)).text($("#destitle_"+(countDescription+1)).val());
    });

}
//拼接option
function formatRepo (repo) {
    if (repo.loading) return repo.text;
    var markup = repo.full_name;
    return markup;
}
//拼接option
function formatRepoSelection (repo) {
    return repo.full_name || repo.text;
}
//构造select2的方法
function buildSelect2 (select_id,url) {
    $("#"+select_id).select2({
        language : "zh-CN",
        ajax: {
            url: url,
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    find: params.term,
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data.items,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 0,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });
}