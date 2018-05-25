/**
 * Created by Tianshan on 18-5-16.
 */
//初始化所有select组件
$(document).ready(function(){
    //构造select2
	$("#rank").select2({
		 placeholder: "请选择分类等级(分类阶元或分类单元)"
	});
	buildSelect2("rank", "console/rank/rest/select");
    $("#sourcesid").select2({
        placeholder: "请选择数据来源"
    });
    buildSelect2("sourcesid", "console/datasource/rest/select");
   
    $('#taxaset').select2({
        placeholder: "请选择存放的分类单元集"
    });
    buildSelect2("taxaset", "console/taxaset/rest/select");
    
    $('#taxaset').select2({
    	placeholder: "请选择存放的分类单元集"
    });
    buildSelect2("taxaset", "console/taxaset/rest/select");

    //左右多选
    var refjson = $('#refjson').bootstrapDualListbox({
        nonSelectedListLabel: '所有文献列表',
        selectedListLabel: '已加入文献列表',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
    });

    buildMultiple();

});
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

//构造参考文献列表的方法
function buildMultiple () {
    $.ajax({
        url:"/console/rank/rest/select",
        type:"get",
        async:true,
        success:function(returnData){
            $.each(returnData, function(key, val) {
                var o = document.createElement("option")
                o.value = val.id;
                o.text = val.permissionName;
                if("undefined" != typeof (selectedDataStr) && selectedDataStr != ""){
                    var selectedDataArray = selectedDataStr.split(',');
                    $.each(selectedDataArray, function (i, val){
                        if(o.value = val){
                            o.selected = "selected";
                            return false;
                        }
                    });
                }
                $("select[multiple*='multiple']")[0].options.add(o);
            });

            $('#refjson').bootstrapDualListbox({
                nonSelectedListLabel : "Non-selected",
                selectedListLabel : "selected",
                preserveSelectionOnMove : "moved",
            });
        },
        error: function(e){
            alert(e.msg)
        }
    });
}
