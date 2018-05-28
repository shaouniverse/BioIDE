/**
 * Created by Tianshan on 18-5-22.
 */

//选择参考文献类型
function selectReferences (referencesNum,referencesId,referencesText) {
    $("#referencesId_"+referencesNum).val(referencesId);
    $("#referencesType_"+referencesNum).text(referencesText);
}
//删除一个新参考文献
function removeReferences (referencesNum) {
    $("#referencesForm_"+referencesNum).remove();
}
//添加一个新参考文献
function addReferences () {

    var countReferences=parseInt($('#countReferences').val());

    var thisReferencesNum = {num: countReferences+1};

    $('#referencesForm').tmpl(thisReferencesNum).appendTo('#newReferences');


    $("#references_" + (countReferences + 1)).select2({
        placeholder: "请选择参考文献"
    });

    $('#countReferences').val(countReferences+1);

}