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
    $("#descriptionForm_" + descriptionNum).remove();
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
                $('#descriptionCollapseTitle_'+descriptionNum).trigger("click");
                $('#descriptionForm_'+descriptionNum).removeClass("panel-default");
                $('#descriptionForm_'+descriptionNum).addClass("panel-success");
                $('#descriptionStatus_'+descriptionNum).removeClass("hidden");
                return true;
            });
        return true;
    }
    else{
        $('#descriptionForm_'+descriptionNum).removeClass("panel-success");
        $('#descriptionForm_'+descriptionNum).addClass("panel-default");
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
function addDescription() {

    var countDescription = parseInt($('#countDescription').val());

    var thisDescriptionNum = {
        num: countDescription + 1
    };

    $('#descriptionForm').tmpl(thisDescriptionNum).appendTo('#newDescription');

    $("#destypeid_" + (countDescription + 1)).select2({
        placeholder: "请选择描述类型"
    });
    $("#licenseid_" + (countDescription + 1)).select2({
        placeholder: "请选择共享协议"
    });
    $("#language_" + (countDescription + 1)).select2({
        placeholder: "请选择描述语言"
    });
    $("#descriptionsourcesid_" + (countDescription + 1)).select2({
        placeholder: "请选择数据来源"
    });
    $("#relationDes_" + (countDescription + 1)).select2({
        placeholder: "请选择描述"
    });

    $('#countDescription').val(countDescription + 1);

    addDescriptionValidator(countDescription + 1);

    //title变化后处理的函数
    $("#destitle_" + (countDescription + 1)).on("change",
        function() {
            $("#descriptionCollapseTitle_" + (countDescription + 1)).text($("#destitle_" + (countDescription + 1)).val());
        });

}