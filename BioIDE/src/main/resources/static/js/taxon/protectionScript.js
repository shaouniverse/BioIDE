/**
 * Created by Tianshan on 18-5-22.
 */

//删除一个新保护数据
function removeProtection (protectionNum) {
    $("#protectionForm_"+protectionNum).remove();
}
//提交一个保护数据
function submitProtection(protectionNum) {
    protectionFormValidator(protectionNum);
    if($('#protectionForm_'+protectionNum).data('bootstrapValidator').isValid()){
        //处理ajax提交
        //
        //
        //
        layer.msg('提交成功，请继续填写其他内容',
            {
                time: 1500, //1.5s后自动关闭
            },
            function(){
                if($('#protectionCollapse_'+protectionNum).hasClass('in')){
                    $('#protectionCollapseTitle_'+protectionNum).trigger("click");
                }
                $('#protectionForm_'+protectionNum).removeClass("panel-default");
                $('#protectionForm_'+protectionNum).removeClass("panel-danger");
                $('#protectionForm_'+protectionNum).addClass("panel-success");
                $('#protectionStatus_'+protectionNum).removeClass("hidden");
                return true;
            });
        return true;
    }
    else{
        if(!$('#protectionCollapse_'+protectionNum).hasClass('in')){
            $('#protectionCollapseTitle_'+protectionNum).trigger("click");
        }
        $('#protectionForm_'+protectionNum).removeClass("panel-success");
        $('#protectionForm_'+protectionNum).addClass("panel-danger");
        $('#protectionStatus_'+protectionNum).addClass("hidden");
        $('#tab-protection').addClass("alert alert-danger");
        layer.msg("请完成此文本描述的填写", function(){
        });
        return false;
    }
}

//提交所有保护数据
function submitAllProtection() {
    var submitResult=true;
    $('#tab-protection').removeClass("alert-danger");
    $('#tab-protection').removeClass("alert");
    $("form[id^='protectionForm_']").each(function () {
        if(submitProtection($(this).attr("id").substr($(this).attr("id").lastIndexOf("_")+1))){
        }
        else{
            submitResult= false;
        }
    });
    return submitResult;
}
//添加一个新保护数据
function addProtection () {

    var countProtection=parseInt($('#countProtection').val());

    var thisProtectionNum = {num: countProtection+1};

    $('#protectionForm').tmpl(thisProtectionNum).appendTo('#newProtection');
    // 保护标准下拉选
    buildSelect2("standardname_" + (countProtection+1), "console/datasource/rest/select", "请选择保护标准");
    // 保护标准版本下拉选
    buildSelect2("version_" + (countProtection+1), "console/datasource/rest/select", "请选择标准版本");
    // 保护级别下拉选
    buildSelect2("protlevel_" + (countProtection+1), "console/datasource/rest/select", "请选择保护级别");

    $('#countProtection').val(countProtection+1);

    addProtectionValidator(countProtection + 1);

    function buildProtectionTitle (countProtection) {
        var titleText="";
        if($("#standardname_"+(countProtection+1)).select2('data')[0]){
            titleText=$("#standardname_"+(countProtection+1)).select2('data')[0].full_name
        } if($("#version_"+(countProtection+1)).select2('data')[0]){
            titleText=titleText
                +" ("
                +$("#version_"+(countProtection+1)).select2('data')[0].full_name
                +") "
        }
        if($("#protlevel_"+(countProtection+1)).select2('data')[0]){
            titleText=titleText
                +$("#protlevel_"+(countProtection+1)).select2('data')[0].full_name
        }
        return titleText;
    }


    //保护标准变化后处理的函数
    $("#standardname_"+(countProtection+1)).on("change", function(){
        $("#protectionCollapseTitle_"+(countProtection+1)).text(
            buildProtectionTitle(countProtection)
        );
    });
    //保护标准版本变化后处理的函数
    $("#version_"+(countProtection+1)).on("change", function(){
        $("#protectionCollapseTitle_"+(countProtection+1)).text(
            buildProtectionTitle(countProtection)
        );
    });
    //保护级别变化后处理的函数
    $("#protlevel_"+(countProtection+1)).on("change", function(){
        $("#protectionCollapseTitle_"+(countProtection+1)).text(
            buildProtectionTitle(countProtection)
        );
    });

}

//选择参考文献类型
function selectProtectionReferences(protectionNum,referencesNum,referencesId,referencesText) {
    $("#protectionReferencesType_"+protectionNum+"_"+referencesNum).val(referencesId);
    $("#protectionReferencesBtn_"+protectionNum+"_"+referencesNum).text(referencesText);
}
//删除一个新参考文献
function removeProtectionReferences(protectionNum,referencesNum) {
    $("#protectionReferencesForm_"+protectionNum+"_"+referencesNum).remove();
}
//添加一个新参考文献
function addProtectionReferences(protectionNum) {

    var countProtectionReferences=parseInt($('#countProtectionReferences_'+protectionNum).val());

    var thisReferencesNum = {pnum: protectionNum,num: countProtectionReferences+1};

    $('#protectionReferencesForm').tmpl(thisReferencesNum).appendTo('#newProtectionReferences_'+protectionNum);

    $("#protectionReferences_" + protectionNum+"_"+(countProtectionReferences + 1)).select2({
        placeholder: "请选择参考文献"
    });

    $('#countProtectionReferences_'+protectionNum).val(countProtectionReferences+1);

}
