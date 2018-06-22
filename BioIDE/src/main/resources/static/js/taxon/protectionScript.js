/**
 * Created by Tianshan on 18-5-22.
 */

//删除一个新保护数据
function removeProtection(protectionNum) {
	var r = confirm("是否删除?");
	if (r == true) {
		$.post("/console/protection/rest/delete",
		{
	        "_csrf":$('input[name="_csrf"]').val(),
	        "protectionId":$("#protectionId_" + protectionNum).val()
	    },
		function(status) {
			if (status) {
				layer.msg('删除成功', {time : 500}, 
				function() {
					 $("#protectionForm_" + protectionNum).remove();
				})
			}else {
				layer.msg('操作失败', function(){})
			}
		})
	}else {
		layer.msg(
			'操作取消', 
			{time : 500}
		)
	}
}
//提交一个保护数据
function submitProtection(protectionNum) {
    protectionFormValidator(protectionNum);
    if ($('#protectionForm_' + protectionNum).data('bootstrapValidator').isValid() &&
        ($("tr[id^='"+'protectionReferencesForm_'+protectionNum+"_']").length<=0 || referencesValidator('newProtectionReferences_'+protectionNum,3))) {
        //处理ajax提交
    	var obj = $('#protectionForm_' + protectionNum).serialize();
    	var postSuccess=false;
    	$.ajax({
          type: "POST",
          url: "/console/protection/rest/add",
          data: obj,	// 要提交的表单
          dataType: "json",
          contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
          success: function (msg) {
        	if (msg.result == true) {
        		layer.msg(
        		    '提交成功，请继续填写其他内容',
        		    {time: 1500},
        		    function () {
        		        if ($('#protectionCollapse_' + protectionNum).hasClass('in')) {
        		            $('#protectionCollapseTitle_' + protectionNum).trigger("click");
        		        }
        		        $('#protectionForm_' + protectionNum).removeClass("panel-default");
        		        $('#protectionForm_' + protectionNum).removeClass("panel-danger");
        		        $('#protectionForm_' + protectionNum).addClass("panel-success");
        		        $('#protectionStatus_' + protectionNum).removeClass("hidden");
        		        postSuccess = true;
        		    });
			}else{
				layer.msg("添加失败！", {time: 1000});
				postSuccess = false;
			}
          },
          error: function() {
        	  postSuccess = false;
          }
        });
    	if (!$('#protectionCollapse_' + protectionNum).hasClass('in')) {
            $('#protectionCollapseTitle_' + protectionNum).trigger("click");
        }
        $('#protectionForm_' + protectionNum).removeClass("panel-success");
        $('#protectionForm_' + protectionNum).addClass("panel-danger");
        $('#protectionStatus_' + protectionNum).addClass("hidden");
        layer.msg("添加失败", function () {
        });
        return postSuccess;
    }
    else {
        if (!$('#protectionCollapse_' + protectionNum).hasClass('in')) {
            $('#protectionCollapseTitle_' + protectionNum).trigger("click");
        }
        $('#protectionForm_' + protectionNum).removeClass("panel-success");
        $('#protectionForm_' + protectionNum).addClass("panel-danger");
        $('#protectionStatus_' + protectionNum).addClass("hidden");
        $('#tab-protection').addClass("alert alert-danger");
        layer.msg("请完成此文本描述的填写", function () {
        });
        return false;
    }
}

//提交所有保护数据
function submitAllProtection() {
    var submitResult = true;
    $('#tab-protection').removeClass("alert-danger");
    $('#tab-protection').removeClass("alert");
    $("form[id^='protectionForm_']").each(function () {
        if (submitProtection($(this).attr("id").substr($(this).attr("id").lastIndexOf("_") + 1))) {
        }
        else {
            submitResult = false;
        }
    });
    return submitResult;
}
//添加一个新保护数据
function addProtection() {

    var countProtection = parseInt($('#countProtection').val());

    var thisProtectionNum = {num: countProtection + 1};

    $('#protectionForm').tmpl(thisProtectionNum).appendTo('#newProtection');
    // 保护标准下拉选
    buildSelect("standardname_" + (countProtection + 1), "console/protectstandard/rest/selectStandard", "请选择保护标准");

    $('#version_'  + (countProtection + 1)).select2({placeholder: '请选择标准版本'});
    $('#protlevel_'  + (countProtection + 1)).select2({placeholder: '请选择保护级别'});
    $('#countProtection').val(countProtection + 1);

    addProtectionValidator(countProtection + 1);

    // 唯一标识UUID
    $.get("/console/taxon/rest/uuid", function(id){
    	$("#protectionId_" + (countProtection + 1)).val(id);
    });
    
    function buildProtectionTitle(countProtection) {
        var titleText = "";
        if ($("#standardname_" + (countProtection + 1)).select2('data')[0]) {
            titleText = $("#standardname_" + (countProtection + 1)).select2('data')[0].full_name
        }
        if ($("#version_" + (countProtection + 1)).select2('data')[0]) {
            titleText = titleText
                + " ("
                + $("#version_" + (countProtection + 1)).select2('data')[0].full_name
                + ") "
        }
        if ($("#protlevel_" + (countProtection + 1)).select2('data')[0]) {
            titleText = titleText
                + $("#protlevel_" + (countProtection + 1)).select2('data')[0].full_name
        }
        return titleText;
    }


    //保护标准变化后处理的函数
    $("#standardname_" + (countProtection + 1)).on("change", function () {
        $("#protectionCollapseTitle_" + (countProtection + 1)).text(
            buildProtectionTitle(countProtection)
        );
    });
    //保护标准版本变化后处理的函数
    $("#version_" + (countProtection + 1)).on("change", function () {
        $("#protectionCollapseTitle_" + (countProtection + 1)).text(
            buildProtectionTitle(countProtection)
        );
    });
    //保护级别变化后处理的函数
    $("#protlevel_" + (countProtection + 1)).on("change", function () {
        $("#protectionCollapseTitle_" + (countProtection + 1)).text(
            buildProtectionTitle(countProtection)
        );
    });

}
//根据standardname是否被选中给出不同的结果
function getStandardname(num){
	$("#version").click(function () { 
		var standardname = $("#standardname_" + num).select2("val");
		if (null == standardname) {
			layer.msg("请选择保护标准", {time:1500});
		}else{
			$.get("/console/protectstandard/rest/selectVersion",{"standardname":standardname});
			// 保护标准版本下拉选
		    buildSelect("version_" + num, "console/protectstandard/rest/selectVersion", "请选择标准版本");
		}
	});
}
//根据version是否被选中给出不同的结果
function getVersion(num){
	$("#protlevel").click(function () { 
		var version = $("#version_" + num).select2("val");
		if (null == version) {
			layer.msg("请选择标准版本", {time:1500});
		}else{
			$.get("/console/protectstandard/rest/selectProtlevel",{"version":version});
			// 保护级别下拉选
		    buildSelect("protlevel_" + num, "console/protectstandard/rest/selectProtlevel", "请选择保护级别");
		}
	});
}
//删除一个新参考文献
function removeProtectionReferences(protectionNum, referencesNum) {
    $("#protectionReferencesForm_" + protectionNum + "_" + referencesNum).remove();
}
//添加一个新参考文献
function addProtectionReferences(protectionNum) {

    var countProtectionReferences = parseInt($('#countProtectionReferences_' + protectionNum).val());

    var thisReferencesNum = {pnum: protectionNum, num: countProtectionReferences + 1};

    $('#protectionReferencesForm').tmpl(thisReferencesNum).appendTo('#newProtectionReferences_' + protectionNum);

    buildSelect2("protectionReferences_" + protectionNum + "_" + (countProtectionReferences + 1), "console/ref/rest/select", "请选择参考文献");

    //参考文献验证规则
    addReferencesValidator(
        "newProtectionReferences_"+protectionNum,
        (countProtectionReferences+1),
        "protectionReferences_"+protectionNum+"_",
        "protectionReferencesPageS_"+protectionNum+"_",
        "protectionReferencesPageE_"+protectionNum+"_"
    );

    $('#countProtectionReferences_' + protectionNum).val(countProtectionReferences + 1);

}
