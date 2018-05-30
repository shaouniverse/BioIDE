/**
 * Created by BINZI on 18-5-25.
 */
  var width=$(window).width();
  var height=$(window).height();
  var layer_width="90%";
  var layer_height="90%";
  if(width>760){
  	layer_width="500px";
      layer_height="500px";
  }

//dataset变化监测
$("select[id='sourcesid']").on("change", function(){
	if($("#sourcesid").val()=="addNew"){
		$("#sourcesid").empty();
		layer.open({
			  type: 2,
			  title:'<h3 class="modal-title">添加数据源</h3>',
			  fixed: false, //不固定
			  area: [layer_width, layer_height],
			  skin: 'layui-layer-rim', //加上边框
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
			  title:'<h3 class="modal-title">添加分类单元集</h3>',
			  fixed: false, //不固定
			  area: [layer_width, layer_height],
			  skin: 'layui-layer-rim', //加上边框
			  content: '/console/taxaset/addNew'
		});
	}
});