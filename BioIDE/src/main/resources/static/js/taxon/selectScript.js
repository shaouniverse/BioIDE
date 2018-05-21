/**
 * Created by Tianshan on 18-5-16.
 */
//初始化所有select组件
$(document).ready(function(){
    //构造select2
    $("#rank").select2({
        placeholder: "请选择分类等级(分类阶元或分类单元)"
    });
    $("#sourcesid").select2({
        placeholder: "请选择数据来源"
    });
    $("#taxaset").select2({
        placeholder: "请选择存放的分类单元集"
    });
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
        language : select2Language,
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