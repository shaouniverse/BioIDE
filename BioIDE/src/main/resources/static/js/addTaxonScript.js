/**
 * Created by Tianshan on 18-5-16.
 */

//点击标签页的操作
$('#addSteps a').click(function (e) {
    var idNum=this.hash;
    idNum=idNum.substr(idNum.lastIndexOf("_")+1);
    if(verifierStep(idNum)){
        e.preventDefault();
        $(this).tab('show');
        completeStep(idNum-1);
        activeStep(idNum);
    }
    else{
        alert("不成功！");
    }
})

//验证完善程度
function verifierStep(stepNum){
    if(stepNum==3){
        return false;
    }
    return true;
};

//步骤跳转
function toStep(stepNum){
    activeTab(stepNum);
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