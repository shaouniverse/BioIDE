/**
 * Created by Tianshan on 18-5-16.
 */
$(document).ready( function() {
    //获取host
    $("#base_url").val(window.location.host);
    //基础信息验证规则
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
                    notEmpty: {
                    }
                }
            },
            'sourcesid': {
                validators: {
                    notEmpty: {
                    }
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
                    notEmpty: {
                    }
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
    //引证验证规则
    $('#form_2').bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            // 'description1': {
            //     validators: {
            //         notEmpty: {
            //         },
            //         stringLength: {
            //             min: 2,
            //             max: 80
            //         },
            //     }
            // },
        }
    });
    //描述验证规则
    $('#form_3').bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            // 'description1': {
            //     validators: {
            //         notEmpty: {
            //         },
            //         stringLength: {
            //             min: 2,
            //             max: 80
            //         },
            //     }
            // },
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
    $('#form_5').bootstrapValidator({
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
    $('#form_6').bootstrapValidator({
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
    $('#form_7').bootstrapValidator({
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

//统一验证form
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

//构造一个description验证规则
function addDescriptionValidator(descriptionNum) {
    $("#descriptionForm_" + descriptionNum).bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "destitle_"+descriptionNum, {
        validators: {
            notEmpty: {
            },
            stringLength: {
                min: 1,
                max: 100
            },
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "destypeid_"+descriptionNum, {
        validators: {
            // notEmpty: {
            // }
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "desdate_"+descriptionNum, {
        validators: {
            stringLength: {
                min: 0,
                max: 50
            },
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "describer_"+descriptionNum, {
        validators: {
            stringLength: {
                min: 0,
                max: 50
            },
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "rightsholder_"+descriptionNum, {
        validators: {
            stringLength: {
                min: 0,
                max: 100
            },
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "descontent_"+descriptionNum, {
        validators: {
            notEmpty: {
            },
            stringLength: {
                min: 1,
                // max: 100
            },
        }
    });
    $("#descriptionForm_" + descriptionNum).bootstrapValidator("addField", "descriptionremark_"+descriptionNum, {
        validators: {
            stringLength: {
                min: 0,
                max: 500
            },
        }
    });
}
//统一验证description
function descriptionFormValidator(Num) {
    $('#descriptionForm_'+Num).data('bootstrapValidator').validate();
}

//构造一个citation验证规则
function addCitationValidator(citationNum) {
    $("#citationForm_" + citationNum).bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        }
    });
    $("#citationForm_" + citationNum).bootstrapValidator("addField", "sciname_"+citationNum, {
        validators: {
            notEmpty: {
            },
            stringLength: {
                min: 1,
                max: 100
            },
        }
    });
    $("#citationForm_" + citationNum).bootstrapValidator("addField", "authorship_"+citationNum, {
        validators: {
            notEmpty: {
            },
            stringLength: {
                min: 1,
                max: 100
            },
        }
    });
    $("#citationForm_" + citationNum).bootstrapValidator("addField", "nametype_"+citationNum, {
        validators: {
            notEmpty: {
            }
        }
    });
    $("#citationForm_" + citationNum).bootstrapValidator("addField", "sourcesid_"+citationNum, {
        validators: {
            notEmpty: {
            }
        }
    });
    $("#citationForm_" + citationNum).bootstrapValidator("addField", "citationstr_"+citationNum, {
        validators: {
            notEmpty: {
            },
            stringLength: {
                min: 1,
                max: 500
            },
        }
    });
}
//统一验证citation
function citationFormValidator(Num) {
    $('#citationForm_'+Num).data('bootstrapValidator').validate();
}
//构造一个protection验证规则
function addProtectionValidator(protectionNum) {
    $("#protectionForm_" + protectionNum).bootstrapValidator({
        excluded:[":disabled"],//只对于禁用域不进行验证，其他的表单元素都要验证
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon ',
            invalid: 'glyphicon ',
            validating: 'glyphicon glyphicon-refresh'
        }
    });
    $("#protectionForm_" + protectionNum).bootstrapValidator("addField", "standardname_"+protectionNum, {
        validators: {
            notEmpty: {
            }
        }
    });
    $("#protectionForm_" + protectionNum).bootstrapValidator("addField", "version_"+protectionNum, {
        validators: {
            notEmpty: {
            }
        }
    });
    $("#protectionForm_" + protectionNum).bootstrapValidator("addField", "protlevel_"+protectionNum, {
        validators: {
            notEmpty: {
            }
        }
    });
    $("#protectionForm_" + protectionNum).bootstrapValidator("addField", "proassessment_"+protectionNum, {
        validators: {
            stringLength: {
                min: 0,
                max: 1500
            },
        }
    });
}
//统一验证protection
function protectionFormValidator(Num) {
    $('#protectionForm_'+Num).data('bootstrapValidator').validate();
}

