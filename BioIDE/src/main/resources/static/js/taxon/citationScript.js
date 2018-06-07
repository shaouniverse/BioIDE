/**
 * Created by Tianshan on 18-5-22.
 */
//删除一个新引证
function removeCitation(citationNum) {
    $("#citationForm_" + citationNum).remove();
}
//提交一个引证
function submitCitation(citationNum) {
    citationFormValidator(citationNum);
    if ($('#citationForm_' + citationNum).data('bootstrapValidator').isValid()) {
        //处理ajax提交
        //
        //
        //
        layer.msg('提交成功，请继续填写其他内容',
            {
                time: 1500, //1.5s后自动关闭
            },
            function () {
                if ($('#citationCollapse_' + citationNum).hasClass('in')) {
                    $('#citationCollapseTitle_' + citationNum).trigger("click");
                }
                $('#citationForm_' + citationNum).removeClass("panel-default");
                $('#citationForm_' + citationNum).removeClass("panel-danger");
                $('#citationForm_' + citationNum).addClass("panel-success");
                $('#citationStatus_' + citationNum).removeClass("hidden");
                return true;
            });
        return true;
    }
    else {
        if (!$('#citationCollapse_' + citationNum).hasClass('in')) {
            $('#citationCollapseTitle_' + citationNum).trigger("click");
        }
        $('#citationForm_' + citationNum).removeClass("panel-success");
        $('#citationForm_' + citationNum).addClass("panel-danger");
        $('#citationStatus_' + citationNum).addClass("hidden");
        layer.msg("请完成此文本描述的填写", function () {
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

    buildSelect2("sourcesid_" + (countCitation + 1), "console/datasource/rest/select");

    // $("#sourcesid_" + (countCitation+1)).select2({
    //     placeholder: "请选择数据源",
    // });

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

    $("#citationReferences_" + citationNum + "_" + (countCitationReferences + 1)).select2({
        placeholder: "请选择参考文献"
    });

    $('#countCitationReferences_' + citationNum).val(countCitationReferences + 1);

}

/*var width=$(window).width();
var height=$(window).height();
var layer_width="90%";
var layer_height="90%";
if(width>760){
	layer_width="500px";
	layer_height="500px";
}

//datasource -- Form1变化监测
$("select[id='sourcesid_' + (countCitation + 1)]").on("change", function(){
	if($("#sourcesid_" + (countCitation + 1)).val()=="addNew"){
		$("#sourcesid" + (countCitation + 1)).empty();
		layer.open({
			type: 2,
			title:'<h4>添加数据源</h4>',
			fixed: false, //不固定
			area: [layer_width, layer_height],
			content: '/console/datasource/addNew'
		});
	}
});*/
