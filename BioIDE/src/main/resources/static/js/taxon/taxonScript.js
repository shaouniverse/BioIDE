/**
 * Created by Tianshan on 18-5-22.
 */

//选择参考文献类型
function selectReferences(referencesNum, referencesId, referencesText) {
    $("#referencesType_" + referencesNum).val(referencesId);
    $("#referencesBtn_" + referencesNum).text(referencesText);
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
    // 参考文献下拉选
    buildSelect2("references_" + (countReferences + 1), "console/ref/rest/select", "请选择参考文献");
    $('#countReferences').val(countReferences+1);

    //参考文献验证规则
    addReferencesValidator("newReferences",countReferences+1,"references_","referencesPageS_","referencesPageE_");
    //添加Reference弹出层
    addRefLayer();
}


function addRefLayer(){
	var num = $('#countReferences').val();
	var width = $(window).width();
	var height = $(window).height();
	var layer_width = "90%";
	var layer_height = "90%";
	if (width > 760) {
		layer_width = "500px";
		layer_height = "500px";
	}
	//References -- Form1变化监测
	var refId = "references_" + num;
	$("select[id=" + refId + "]").on("change", function() {
		if ($("#" + refId).val() == "addNew") {
			$("#" + refId).empty();
			layer.open({
				type : 2,
				title : '<h4>添加参考文献</h4>',
				fixed : false, // 不固定
				area : [ layer_width, layer_height ],
				content : '/console/ref/addNew?refId = ' + refId
			});
		}
	});
}

// Form1下的Datasource & Taxset变化检测
var width=$(window).width();
var height=$(window).height();
var layer_width="90%";
var layer_height="90%";
if(width>760){
	layer_width="500px";
	layer_height="500px";
}

//datasource -- Form1变化监测
$("select[id='sourcesid']").on("change", function(){
	if($("#sourcesid").val()=="addNew"){
		$("#sourcesid").empty();
		layer.open({
			type: 2,
			title:'<h4>添加数据源</h4>',
			fixed: false, //不固定
			area: [layer_width, layer_height],
			content: '/console/datasource/addNew'
		});
	}
});

//taxaset变化监测
$("select[id='taxaset']").on("change", function(){
	if($("#taxaset").val() == "addNew"){
		$("#taxaset").empty();
		layer.open({
			type: 2,
			title:'<h4>添加分类单元集</h4>',
			fixed: false, //不固定
			area: [layer_width, layer_height],
			content: '/console/taxaset/addNew'
		});
	}
});
