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