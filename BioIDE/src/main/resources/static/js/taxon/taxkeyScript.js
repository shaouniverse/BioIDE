/**
 * Created by Tianshan on 18-5-22.
 */

//删除一个新检索表
function removeTaxkey(taxkeyNum) {
	var r = confirm("是否删除?");
	if (r == true) {
		$.post("/console/taxkey/rest/delete",
		{
	        "_csrf":$('input[name="_csrf"]').val(),
	        "taxkeyId":$("#taxkeyId_" + taxkeyNum).val()
	    },
		function(status) {
			if (status) {
				layer.msg('删除成功', {time : 500}, 
				function() {
					$("#taxkeyForm_" + taxkeyNum).remove();
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
//提交一个检索表
function submitTaxkey(taxkeyNum) {
    taxkeyFormValidator(taxkeyNum);
    if ($('#taxkeyForm_' + taxkeyNum).data('bootstrapValidator').isValid()) {
        //处理ajax提交
    	var obj = $('#taxkeyForm_' + taxkeyNum).serialize();
    	var postSuccess=false;
    	return $.ajax({
          type: "POST",
          url: "/console/taxkey/rest/add",
          data: obj,	// 要提交的表单
          dataType: "json",
          contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
          success: function (msg) {
        	if (msg.result == true) {
        		layer.msg('提交成功，请继续填写其他内容', {time: 1500},
                function () {
                    if ($('#taxkeyCollapse_' + taxkeyNum).hasClass('in')) {
                        $('#taxkeyCollapseTitle_' + taxkeyNum).trigger("click");
                    }
                    $('#taxkeyForm_' + taxkeyNum).removeClass("panel-default");
                    $('#taxkeyForm_' + taxkeyNum).removeClass("panel-danger");
                    $('#taxkeyForm_' + taxkeyNum).addClass("panel-success");
                    $('#taxkeyStatus_' + taxkeyNum).removeClass("hidden");
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
        if (!$('#taxkeyCollapse_' + taxkeyNum).hasClass('in')) {
            $('#taxkeyCollapseTitle_' + taxkeyNum).trigger("click");
        }
        $('#taxkeyForm_' + taxkeyNum).removeClass("panel-danger");
        $('#taxkeyForm_' + taxkeyNum).addClass("panel-success");
        $('#taxkeyStatus_' + taxkeyNum).removeClass("hidden");
        layer.msg("添加失败", function () {
        });
        return postSuccess;
    } else {
        if (!$('#taxkeyCollapse_' + taxkeyNum).hasClass('in')) {
            $('#taxkeyCollapseTitle_' + taxkeyNum).trigger("click");
        }
        $('#taxkeyForm_' + taxkeyNum).removeClass("panel-success");
        $('#taxkeyForm_' + taxkeyNum).addClass("panel-danger");
        $('#taxkeyStatus_' + taxkeyNum).addClass("hidden");
        $('#tab-taxkey').addClass("alert alert-danger");
        layer.msg("请完成此文本描述的填写", function () {
        });
        return false;
    }
}

//提交所有检索表
function submitAllTaxkey() {
    var submitResult = true;
    $('#tab-taxkey').removeClass("alert-danger");
    $('#tab-taxkey').removeClass("alert");
    $("form[id^='taxkeyForm_']").each(function () {
        if (submitTaxkey($(this).attr("id").substr($(this).attr("id").lastIndexOf("_") + 1))) {
        } else {
            submitResult = false;
        }
    });
    return submitResult;
}
//添加一个新检索表
function addTaxkey() {

    var countTaxkey = parseInt($('#countTaxkey').val());

    var thisTaxkeyNum = {
        num: countTaxkey + 1
    };

    $('#taxkeyForm').tmpl(thisTaxkeyNum).appendTo('#newTaxkey');
    // 指向分类单元下拉选
    buildSelect2("taxonid_" + (countTaxkey + 1), "console/taxon/rest/select", "请选择指向分类单元");

    $('#countTaxkey').val(countTaxkey + 1);

    addTaxkeyValidator(countTaxkey + 1);
    //buildKeytable(countTaxkey + 1);

    //title变化后处理的函数
    $("#keytitle_" + (countTaxkey + 1)).on("change",
        function () {
            $("#taxkeyCollapseTitle_" + (countTaxkey + 1)).text($("#keytitle_" + (countTaxkey + 1)).val());
        });
    
 // 唯一标识UUID
    $.get("/console/taxon/rest/uuid", function(id){
    	$("#taxkeyId_" + (countTaxkey + 1)).val(id);
    });
    
    // 唯一标识UUID
    $.get("/console/taxon/rest/uuid", function(id){
    	$("#keyitemId_" + (countTaxkey + 1)).val(id);
    });
}

//切换检索表类型
function changetaxkeyType(taxkeyNum, taxkeyType) {
    if($("#taxkeyType"+taxkeyType+"_"+taxkeyNum).hasClass("active")){
    	
    }else{
        var r = confirm("切换检索表类型会删除您当前编辑的检索表内容，是否继续？");
        if (r == true) {
            switch (taxkeyType) {
                case 1:
                    $("#taxkeyType1_"+taxkeyNum).addClass("active");
                    $("#taxkeyType2_"+taxkeyNum).removeClass("active");
                    $("#keytype_"+taxkeyNum).val(1);
                    layer.msg('切换至【双项式】检索表', {
                            time: 500,
                            //1s后自动关闭
                        },
                        function () {
                            $("#inputinnerorder_"+taxkeyNum).show();
                            // var taxkeyData = [
                            //     {
                            //         "orderid": "1.",
                            //         "item": "植物体无根、茎、叶的分化，无胚",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "2",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "",
                            //         "item": "植物体有根、茎、叶的分化，有胚",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "4",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "2.",
                            //         "item": "由藻类和菌类所组成的共生体",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "地衣植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "",
                            //         "item": "非藻类和菌类所组成的共生体",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "3",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "3.",
                            //         "item": "植物体有叶绿素或其他色素，为自养生活方式",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "藻类植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "",
                            //         "item": "植物体无叶绿素或其他色素，为异养生活方式",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "菌类植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "4.",
                            //         "item": "植物体有茎、叶，而无真根",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "苔藓植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "",
                            //         "item": "植物体有茎、叶，而有真根",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "5",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "5.",
                            //         "item": "产生孢子",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "蕨类植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "",
                            //         "item": "产生种子",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "种子植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     }
                            //     ];
                            var taxkeyData = [
                                {
                                    "orderid": "",
                                    "item": "",
                                    "ellipsis": "",
                                    "branchid": "",
                                    "images": "",
                                    "edit": "",
                                    "remove": ""
                                }
                            ];
                            loadKeytable(taxkeyNum, taxkeyData);
                        });
                    break;
                case 2:
                    $("#taxkeyType2_"+taxkeyNum).addClass("active");
                    $("#taxkeyType1_"+taxkeyNum).removeClass("active");
                    $("#keytype_"+taxkeyNum).val(2);
                    layer.msg('切换至【单项式】检索表', {
                            time: 500,
                            //1s后自动关闭
                        },
                        function () {
                            $("#inputinnerorder_"+taxkeyNum).hide();
                            // var taxkeyData = [
                            //     {
                            //         "orderid": "1(6)",
                            //         "item": "植物体无根、茎、叶的分化，无胚",
                            //         "ellipsis": "",
                            //         "branchid": "",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "2(5)",
                            //         "item": "非藻类和菌类所组成的共生体",
                            //         "ellipsis": "",
                            //         "branchid": "",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "3(4)",
                            //         "item": "植物体有叶绿素或其他色素，为自养生活方式",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "藻类植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "4(3)",
                            //         "item": "植物体无叶绿素或其他色素，为异养生活方式",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "菌类植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "5(2)",
                            //         "item": "由藻类和菌类所组成的共生体",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "地衣植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "6(1)",
                            //         "item": "植物体有根、茎、叶的分化，有胚",
                            //         "ellipsis": "",
                            //         "branchid": "",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "7(8)",
                            //         "item": "植物体有茎、叶，而无真根",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "苔藓植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "8(7)",
                            //         "item": "植物体有茎、叶，而有真根",
                            //         "ellipsis": "",
                            //         "branchid": "",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "9(10)",
                            //         "item": "产生孢子",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "蕨类植物门",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     },
                            //     {
                            //         "orderid": "10(9)",
                            //         "item": "产生种子",
                            //         "ellipsis": "……………………………………………………………………………………",
                            //         "branchid": "种子植物门	",
                            //         "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                            //         "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                            //         "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
                            //     }
                            //     ];
                            var taxkeyData = [
                                {
                                    "orderid": "",
                                    "item": "",
                                    "ellipsis": "",
                                    "branchid": "",
                                    "images": "",
                                    "edit": "",
                                    "remove": ""
                                }
                            ];
                            loadKeytable(taxkeyNum, taxkeyData);
                        });
                    break;
                case 3:
                    layer.msg('目前不支持该格式', {
                            time: 1000,
                            //1s后自动关闭
                        },
                        function () {

                        });
                    break;
                default:
                    return true;
            }
        }
        else{
            layer.msg('操作取消', {
                time: 500,
            })
        }
    }
}

//构造检索表预览
function buildKeytable(taxkeyNum) {
    var $table = $('#keytable_' + taxkeyNum);
    var taxkeyDefaultData = [
        {
            "orderid": "1.",
            "item": "植物体无根、茎、叶的分化，无胚",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "2",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "",
            "item": "植物体有根、茎、叶的分化，有胚",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "4",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "2.",
            "item": "由藻类和菌类所组成的共生体",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "地衣植物门",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "",
            "item": "非藻类和菌类所组成的共生体",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "3",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "3.",
            "item": "植物体有叶绿素或其他色素，为自养生活方式",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "藻类植物门",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "",
            "item": "植物体无叶绿素或其他色素，为异养生活方式",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "菌类植物门",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "4.",
            "item": "植物体有茎、叶，而无真根",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "苔藓植物门",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "",
            "item": "植物体有茎、叶，而有真根",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "5",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "5.",
            "item": "产生孢子",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "蕨类植物门",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        },
        {
            "orderid": "",
            "item": "产生种子",
            "ellipsis": "……………………………………………………………………………………",
            "branchid": "种子植物门",
            "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
            "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
            "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
        }];
    $(function () {
        $table.bootstrapTable({
            data: taxkeyDefaultData
        });
    });
}

//重载检索表预览
function loadKeytable(taxkeyNum, data) {
    var $table = $('#keytable_' + taxkeyNum);
    $(function () {
        $table.bootstrapTable('load', data);
    });
}