/**
 * Created by BINZI on 18-5-25.
 */

//dataset变化监测
$("select[id='sourcesid']").on("change", function(){
	if($("#sourcesid").val()=="addNew"){
		$("#modal-addDataset").modal();
	}
});

//taxaset变化监测
$("select[id='taxaset']").on("change", function(){
	if($("#taxaset").val() == "addNew"){
		$("#modal-addTaxaset").modal();
	}
});

//添加dataset
function addDataset() {
	if (entityNameValidator($('input[name="dsname"]').val()) && entityInfoValidator($('textarea[name="dsabstract"]').val())) {
		layer.load(2);
		$.post("console/dataset/rest/new", 
		{
			"_csrf" : $('input[name="_csrf"]').val(),
			"dsname" : $('input[name="dsname"]').val(),
			"dsabstract" : $('textarea[name="dsabstract"]').val()
		}, 
		function(rsl) {
			alert(rsl.result);
			if (rsl.result == true) {
				layer.closeAll('loading');
				$("#sourcesid").val(result.newId);
				$("#sourcesid").prepend("<option value='"+rsl.newId+"' selected='selected'>" + rsl.newDsname + "</option>"); // 填充新增实体名称
				$('#form_1').data('bootstrapValidator').updateStatus('thisTaxon.sourcesid', 'NOT_VALIDATED', null).validateField('thisTaxon.sourcesid');
				$("#close-addModel").click();
			} else {
				layer.closeAll('loading');
				layer.msg("error", function() {
				});
			}
		});
	} else {
		layer.msg(warning_Illegal, function() {
		});
	}
};

//验证dsname
function entityNameValidator(entityName) {
	var pattern = /^[\da-zA-Z_\u4e00-\u9f5a【】]{1,30}$/;
	return pattern.test(dsname);
}
//验证dsabstraction
function entityInfoValidator(entityInfo) {
	var pattern = /^[\da-zA-Z_\u4e00-\u9f5a【】]{0,100}$/;
	return pattern.test(dsabstraction);
}