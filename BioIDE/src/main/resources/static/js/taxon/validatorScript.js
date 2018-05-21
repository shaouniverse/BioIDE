/**
 * Created by Tianshan on 18-5-16.
 */
$(document).ready( function() {
    //获取host
    $("#base_url").val(window.location.host);
    $('#form_1').bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
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
                    stringLength: {
                        min: 0,
                        max: 80
                    },
                }
            },
            'nomencode': {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 50
                    },
                }
            },
            'rank.id': {
                validators: {
                    // notEmpty: {
                    // }
                }
            },
            'sourcesid': {
                validators: {
                    // notEmpty: {
                    // }
                }
            },
            'tci': {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 100
                    },
                }
            },
            'taxaset.id': {
                validators: {
                    // notEmpty: {
                    // }
                }
            },
            'remark': {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 1000
                    },
                }
            },
        }
    });
    $('#form_2').bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
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
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
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
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
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
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
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
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
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

//统一验证
function formValidator(formNum) {
    $('#form_'+formNum).data('bootstrapValidator').validate();
    //是否通过校验
    if(!$('#form_'+formNum).data('bootstrapValidator').isValid()){
        changeVerificationTab(formNum,-1);
        incompleteStep(formNum);
        return false;//没有通过校验
    } else {
        changeVerificationTab(formNum,1);
        completeStep(formNum);
        return true;//通过校验
    }
}
