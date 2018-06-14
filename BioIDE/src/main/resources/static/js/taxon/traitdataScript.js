/**
 * Created by Tianshan on 18-5-22.
 */

//删除一个新特征数据
function removeTraitdata(traitdataNum) {
    $("#traitdataForm_" + traitdataNum).remove();
}
//提交一个特征数据
function submitTraitdata(traitdataNum) {
    traitdataFormValidator(traitdataNum);
    if (
        $('#traitdataForm_' + traitdataNum).data('bootstrapValidator').isValid() &&
        ($("tr[id^='"+'traitdataValueForm_'+traitdataNum+"_']").length>0 && traitdataValidator('newTraitdataValue_'+traitdataNum,3)) &&
        ($("tr[id^='"+'traitdataReferencesForm_'+traitdataNum+"_']").length<=0 || referencesValidator('newTraitdataReferences_'+traitdataNum,3))) {
        //处理ajax提交
        layer.msg('提交成功，请继续填写其他内容',
            {
                time: 1500, //1.5s后自动关闭
            },
            function () {
                if ($('#traitdataCollapse_' + traitdataNum).hasClass('in')) {
                    $('#traitdataCollapseTitle_' + traitdataNum).trigger("click");
                }
                $('#traitdataForm_' + traitdataNum).removeClass("panel-default");
                $('#traitdataForm_' + traitdataNum).removeClass("panel-danger");
                $('#traitdataForm_' + traitdataNum).addClass("panel-success");
                $('#traitdataStatus_' + traitdataNum).removeClass("hidden");
                return true;
            });
        return true;
    }
    else {
        if (!$('#traitdataCollapse_' + traitdataNum).hasClass('in')) {
            $('#traitdataCollapseTitle_' + traitdataNum).trigger("click");
        }
        $('#traitdataForm_' + traitdataNum).removeClass("panel-success");
        $('#traitdataForm_' + traitdataNum).addClass("panel-danger");
        $('#traitdataStatus_' + traitdataNum).addClass("hidden");
        $('#tab-traitdata').addClass("alert alert-danger");
        layer.msg("请完成此文本描述的填写", function () {
        });
        return false;
    }
}

//提交所有特征数据
function submitAllTraitdata() {
    var submitResult = true;
    $('#tab-traitdata').removeClass("alert-danger");
    $('#tab-traitdata').removeClass("alert");
    $("form[id^='traitdataForm_']").each(function () {
        if (submitTraitdata($(this).attr("id").substr($(this).attr("id").lastIndexOf("_") + 1))) {
        }
        else {
            submitResult = false;
        }
    });
    return submitResult;
}
//添加一个新特征数据
function addTraitdata() {

    var countTraitdata = parseInt($('#countTraitdata').val());

    var thisTraitdataNum = {num: countTraitdata + 1};

    $('#traitdataForm').tmpl(thisTraitdataNum).appendTo('#newTraitdata');
    // 术语集下拉选
    buildSelect2("trainsetid_" + (countTraitdata + 1), "console/datasource/rest/select", "请选择特征所属的大类");
    // 源数据下拉选
    buildSelect2("trainSourcesid_" + (countTraitdata + 1), "console/datasource/rest/select", "请选择数据来源");
    // 描述来源下拉选
    buildSelect2("desid_" + (countTraitdata + 1), "console/datasource/rest/select", "请选择特征来源的描述信息");

    $('#countTraitdata').val(countTraitdata + 1);

    addTraitdataValidator(countTraitdata + 1);

    function buildTraitdataTitle(countTraitdata) {
        var titleText = "";
        if ($("#trainsetid_" + (countTraitdata + 1)).select2('data')[0]) {
            titleText = $("#trainsetid_" + (countTraitdata + 1)).select2('data')[0].full_name
        }
        if ($("#trainSourcesid_" + (countTraitdata + 1)).select2('data')[0]) {
            titleText = titleText
                + " ("
                + $("#desid_" + (countTraitdata + 1)).select2('data')[0].full_name
                + ") "
        }
        return titleText;
    }


    //术语集变化后处理的函数
    $("#trainsetid_" + (countTraitdata + 1)).on("change", function () {
        $("#traitdataCollapseTitle_" + (countTraitdata + 1)).text(
            buildTraitdataTitle(countTraitdata)
        );
    });
    //源数据变化后处理的函数
    $("#desid_" + (countTraitdata + 1)).on("change", function () {
        $("#traitdataCollapseTitle_" + (countTraitdata + 1)).text(
            buildTraitdataTitle(countTraitdata)
        );
    });

}
//删除一个特征详细描述
function removeTraitdataValue(traitdataNum, referencesNum) {
    $("#traitdataValueForm_" + traitdataNum + "_" + referencesNum).remove();
}
//添加一个特征详细描述
function addTraitdataValue(traitdataNum) {

    var countTraitdataValue = parseInt($('#countTraitdataValue_' + traitdataNum).val());

    var thisValueNum = {tnum: traitdataNum, num: countTraitdataValue + 1};

    $('#traitdataValueForm').tmpl(thisValueNum).appendTo('#newTraitdataValue_' + traitdataNum);

    buildSelect2("traitontology_" + traitdataNum + "_" + (countTraitdataValue + 1), "console/ref/rest/select", "请选择术语(特征)");

    //特征详细描述验证规则
    addTraitdataValueValidator(
        "newTraitdataValue_"+traitdataNum,
        (countTraitdataValue+1),
        "traitontology_"+traitdataNum+"_",
        "traitdataValue_"+traitdataNum+"_"
    );

    $('#countTraitdataValue_' + traitdataNum).val(countTraitdataValue + 1);

}
//删除一个新参考文献
function removeTraitdataReferences(traitdataNum, referencesNum) {
    $("#traitdataReferencesForm_" + traitdataNum + "_" + referencesNum).remove();
}
//添加一个新参考文献
function addTraitdataReferences(traitdataNum) {

    var countTraitdataReferences = parseInt($('#countTraitdataReferences_' + traitdataNum).val());

    var thisReferencesNum = {tnum: traitdataNum, num: countTraitdataReferences + 1};

    $('#traitdataReferencesForm').tmpl(thisReferencesNum).appendTo('#newTraitdataReferences_' + traitdataNum);

    buildSelect2("traitdataReferences_" + traitdataNum + "_" + (countTraitdataReferences + 1), "console/ref/rest/select", "请选择参考文献");

    //参考文献验证规则
    addReferencesValidator(
        "newTraitdataReferences_"+traitdataNum,
        (countTraitdataReferences+1),
        "traitdataReferences_"+traitdataNum+"_",
        "traitdataReferencesPageS_"+traitdataNum+"_",
        "traitdataReferencesPageE_"+traitdataNum+"_"
    );

    $('#countTraitdataReferences_' + traitdataNum).val(countTraitdataReferences + 1);

}
