/**
 * Created by Tianshan on 18-5-22.
 */

//删除一个新检索表
function removeTaxkey (taxkeyNum) {
    $("#taxkeyForm_"+taxkeyNum).remove();
}
//提交一个检索表
function submitTaxkey(taxkeyNum) {
    taxkeyFormValidator(taxkeyNum);
    if($('#taxkeyForm_'+taxkeyNum).data('bootstrapValidator').isValid()){
        //处理ajax提交
        //
        //
        //
        layer.msg('提交成功，请继续填写其他内容',
            {
                time: 1500, //1.5s后自动关闭
            },
            function(){
                if($('#taxkeyCollapse_'+taxkeyNum).hasClass('in')){
                    $('#taxkeyCollapseTitle_'+taxkeyNum).trigger("click");
                }
                $('#taxkeyForm_'+taxkeyNum).removeClass("panel-default");
                $('#taxkeyForm_'+taxkeyNum).removeClass("panel-danger");
                $('#taxkeyForm_'+taxkeyNum).addClass("panel-success");
                $('#taxkeyStatus_'+taxkeyNum).removeClass("hidden");
                return true;
            });
        return true;
    }
    else{
        if(!$('#taxkeyCollapse_'+taxkeyNum).hasClass('in')){
            $('#taxkeyCollapseTitle_'+taxkeyNum).trigger("click");
        }
        $('#taxkeyForm_'+taxkeyNum).removeClass("panel-success");
        $('#taxkeyForm_'+taxkeyNum).addClass("panel-danger");
        $('#taxkeyStatus_'+taxkeyNum).addClass("hidden");
        $('#tab-taxkey').addClass("alert alert-danger");
        layer.msg("请完成此文本描述的填写", function(){
        });
        return false;
    }
}

//提交所有检索表
function submitAllTaxkey() {
    var submitResult=true;
    $('#tab-taxkey').removeClass("alert-danger");
    $('#tab-taxkey').removeClass("alert");
    $("form[id^='taxkeyForm_']").each(function () {
        if(submitTaxkey($(this).attr("id").substr($(this).attr("id").lastIndexOf("_")+1))){
        }
        else{
            submitResult= false;
        }
    });
    return submitResult;
}
//添加一个新检索表
function addTaxkey () {

    var countTaxkey=parseInt($('#countTaxkey').val());

    var thisTaxkeyNum = {num: countTaxkey+1};

    $('#taxkeyForm').tmpl(thisTaxkeyNum).appendTo('#newTaxkey');
    // 指向分类单元下拉选
    buildSelect2("taxonid_" + (countTaxkey+1), "console/datasource/rest/select", "请选择指向分类单元");

    $('#countTaxkey').val(countTaxkey+1);

    //addTaxkeyValidator(countTaxkey + 1);
    buildKeytable(countTaxkey + 1);

    //title变化后处理的函数
    $("#keytitle_"+(countTaxkey+1)).on("change", function(){
        $("#taxkeyCollapseTitle_"+(countTaxkey+1)).text($("#keytitle_"+(countTaxkey+1)).val());
    });

}

//构造检索表预览
function buildKeytable(taxkeyNum) {
    var $table = $('#keytable_'+taxkeyNum);

    $(function () {
        var data = [
            {
                "orderid": "1.",
                "item": "植物体无根、茎、叶的分化，无胚",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "2",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "",
                "item": "植物体有根、茎、叶的分化，有胚",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "4",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "2.",
                "item": "由藻类和菌类所组成的共生体",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "地衣植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "",
                "item": "非藻类和菌类所组成的共生体",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "菌类植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "3.",
                "item": "植物体有叶绿素或其他色素，为自养生活方式",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "藻类植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "",
                "item": "植物体无叶绿素或其他色素，为异养生活方式",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "菌类植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "4.",
                "item": "植物体有茎、叶，而无真根",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "苔藓植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "",
                "item": "植物体有茎、叶，而有真根",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "5",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "5.",
                "item": "产生孢子",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "蕨类植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            },
            {
                "orderid": "",
                "item": "产生种子",
                "ellipsis": "………………………………………………………………………………………",
                "branchid": "种子植物门",
                "images": "<button type='button' class='btn btn-default btn-xs' onclick='showImages('1');'>特征图片</button>",
                "edit": "<button type='button' class='btn btn-default btn-xs' onclick='editKeyitem('1');'>编辑</button>",
                "remove": "<button type='button' class='btn btn-default btn-xs' onclick='removeKeyitem('1');'>删除</button>"
            }
        ];
        $table.bootstrapTable({data: data});
    });
}
