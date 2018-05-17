/**
 * Created by Tianshan on 18-5-16.
 */
//全局变量
    //当前tab
    var activeTab="1";
    //tab验证结果：0-未验证，1-验证通过，-1-验证失败，
    var verificationTab1=0;
    var verificationTab2=0;
    var verificationTab3=0;
    var verificationTab4=0;
    var verificationTab5=0;
    var verificationTab6=0;

$(document).ready( function() {
    //获取host
    $("#base_url").val(window.location.host);
    $('#form_1').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'scientificname': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            },
            'authorstr': {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 80
                    },
                }
            },
            'epithet': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            }
        }
    });
    $('#form_2').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'description1': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            },
        }
    });
    $('#form_3').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'molecular1': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            },
        }
    });
    $('#form_4').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'commonname1': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            },
        }
    });
    $('#form_5').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'occurrence2': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            },
        }
    });
    $('#form_6').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'multimedia1': {
                validators: {
                    notEmpty: {
                    },
                    stringLength: {
                        min: 2,
                        max: 80
                    },
                }
            },
        }
    });
} );



//验证状态变更
function changeVerificationTab(tabNum,status) {
    this['verificationTab'+tabNum]=status;
}

//验证基本信息
function formValidator_1() {
    $('#form_1').data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_1').data('bootstrapValidator').isValid()){
        changeVerificationTab(1,-1);
        return false;//没有通过校验
    } else {
        changeVerificationTab(1,1);
        return true;//通过校验
    }
}

//验证基本信息
function formValidator_2() {
    $('#form_2').data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_2').data('bootstrapValidator').isValid()){
        changeVerificationTab(2,-1);
        return false;//没有通过校验
    } else {
        changeVerificationTab(2,1);
        return true;//通过校验
    }
}

//验证基本信息
function formValidator_3() {
    $('#form_3').data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_3').data('bootstrapValidator').isValid()){
        changeVerificationTab(3,-1);
        return false;//没有通过校验
    } else {
        changeVerificationTab(3,1);
        return true;//通过校验
    }
}

//验证基本信息
function formValidator_4() {
    $('#form_4').data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_4').data('bootstrapValidator').isValid()){
        changeVerificationTab(4,-1);
        return false;//没有通过校验
    } else {
        changeVerificationTab(4,1);
        return true;//通过校验
    }
}

//验证基本信息
function formValidator_5() {
    $('#form_5').data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_5').data('bootstrapValidator').isValid()){
        changeVerificationTab(5,-1);
        return false;//没有通过校验
    } else {
        changeVerificationTab(5,1);
        return true;//通过校验
    }
}

//验证基本信息
function formValidator_6() {
    $('#form_6').data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_6').data('bootstrapValidator').isValid()){
        changeVerificationTab(6,-1);
        return false;//没有通过校验
    } else {
        changeVerificationTab(6,1);
        return true;//通过校验
    }
}

//点击标签页的操作
$('#addSteps a').click(function (e) {
    var idNum=this.hash;
    idNum=idNum.substr(idNum.lastIndexOf("_")+1);
    //alert("当前："+activeTab+" | 去往："+idNum);
    if(verifierStep(idNum)){
        e.preventDefault();
        $(this).unbind('show.bs.tab');
        completeStep(idNum-1);
        activeStep(idNum);
        //changeVerificationTab(idNum,0);
        $(this).tab('show');
        activeTab=idNum;
    }
    else{
        $(this).on('show.bs.tab', function(e) {
             e.preventDefault();
        });
        layer.msg("请先完成当前步骤的填写", function(){
        });
    }
})

//验证完善程度
function verifierStep(stepNum){
    if(Number(stepNum)<Number(activeTab)){
        return true;
    }
    switch(stepNum)
    {
        case "2":
            changeVerificationTab(1,0);
            return formValidator_1();
            break;
        case "3":
            return (formValidator_1() && formValidator_2());
            break;
        case "4":
            return (formValidator_1() && formValidator_2() && formValidator_3());
            break;
        case "5":
            return (formValidator_1() && formValidator_2() && formValidator_3() && formValidator_4());
            break;
        case "6":
            return (formValidator_1() && formValidator_2() && formValidator_3() && formValidator_4() && formValidator_5());
            break;
        case "7":
            return (formValidator_1() && formValidator_2() && formValidator_3() && formValidator_4() && formValidator_5() && formValidator_6());
            break;
        default:
            return true;
    }
};

//步骤跳转
function toStep(stepNum){
    var lable=$("#label_"+stepNum);
    lable.click();
    //activeTab(stepNum);
};

//添加当前步骤样式
function activeStep(stepNum){
    var labelTitle=$("#label_title_"+stepNum);
    labelTitle.removeClass();
    labelTitle.addClass("badge label label-primary");
    var labelIcon=$("#lable_icon_"+stepNum);
    labelIcon.removeClass();
    labelIcon.addClass("fa fa-chevron-right");
    labelIcon.css("color","#3c8dbc");

};

//配置已完成步骤样式
function completeStep(stepNum){
    var labelTitle=$("#label_title_"+stepNum);
    labelTitle.addClass("badge label label-success");
    var labelIcon=$("#lable_icon_"+stepNum);
    labelIcon.removeClass();
    labelIcon.addClass("fa fa-chevron-right");
    labelIcon.css("color","#00a65a");

};

//删除其他步骤样式
function unactiveStep(){
    $("span[id^='label_title_']").removeClass();
    //$("#label_title_1,#label_title_2,#label_title_3,#label_title_4,#label_title_5,#label_title_6").removeClass();
};

//模拟tab点击
function activeTab(stepNum){
    var lable=$("#label_"+stepNum);
    lable.click();
};