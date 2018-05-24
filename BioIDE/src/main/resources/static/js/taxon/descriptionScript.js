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