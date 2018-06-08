$(document).ready(function() {
});
function clearInput() {
	$('#thisForm')[0].reset()
};
function resetFrom() {
	$('#thisForm')[0].reset()
};
function editThisObject(id, type) {
	window.location.href = "/console/" + type + "/edit/" + id
};
function removeThisObject(id, type) {
	var r = confirm("确定删除该" + type + "?");
	if (r == true) {
		$.get("/console/" + type + "/rest/remove/" + id, {}, function(data,
				status) {
			if (status) {
				if (data) {
					layer.msg('删除成功', {
						time : 500,
					}, function() {
						$('[name="refresh"]').click()
					})
				} else {
					layer.msg('操作失败', function() {
					})
				}
			} else {
				layer.msg('操作失败', function() {
				})
			}
		})
	} else {
		layer.msg('操作取消', {
			time : 500,
		})
	}
};
function removeThisMember(teamId, userId) {
	var r = confirm("是否将该成员删除?");
	if (r == true) {
		$.post("/console/team/rest/removeMember", {
			"_csrf" : $('input[name="_csrf"]').val(),
			teamId : teamId,
			userId : userId
		}, function(status) {
			if (status) {
				layer.msg('删除成功', {
					time : 500,
				}, function() {
					$('[name="refresh"]').click()
				})
			} else {
				layer.msg('操作失败', function() {
				})
			}
		})
	} else {
		layer.msg('操作取消', {
			time : 500,
		})
	}
};
function transThisMember(teamId, userId) {
	var r = confirm("确定授权?");
	if (r == true) {
		$.post("/console/team/rest/transMember", {
			"_csrf" : $('input[name="_csrf"]').val(),
			teamId : teamId,
			userId : userId
		}, function(status) {
			if (status) {
				layer.msg('授权成功', {
					time : 500,
				}, function() {
					$('[name="refresh"]').click()
				})
			} else {
				layer.msg('操作失败', function() {
				})
			}
		})
	} else {
		layer.msg('操作取消', {
			time : 500,
		})
	}
};
function editSelectObject(type) {
	var number = 0;
	var checkId = "";
	$("input:checkbox[id^='sel']:checked").each(function(i) {
		number = number + 1;
		checkId = $(this).attr('id');
		checkId = checkId.substring(4)
	});
	if (number == 0) {
		alert("请选择数据")
	} else if (number > 1) {
		alert("您选择了" + number + "条数据，只能选择1条数据进行编辑")
	} else {
		editThisObject(checkId, type)
	}
};
function removeSelectObject(type) {
	var number = 0;
	var checkId = "";
	$("input:checkbox[id^='sel']:checked").each(function(i) {
		number = number + 1
	});
	if (number == 0) {
		alert("请选择数据")
	} else {
		var msg = "您确定要删除这" + number + "条记录吗？";
		var ids = "";
		if (confirm(msg) == true) {
			$("input:checkbox[id^='sel']:checked").each(function(i) {
				checkId = $(this).attr('id');
				checkId = checkId.substring(4);
				if (i == 0) {
					ids = checkId
				} else {
					ids = ids + "￥" + checkId
				}
			});
			$.ajax({
				url : '/console/' + type + '/rest/removeMany/' + ids,
				cache : false,
				success : function() {
					layer.msg('已批量删除' + number + '数据', {
						time : 500,
					}, function() {
						$('[name="refresh"]').click()
					})
				},
				error : function() {
					layer.msg('操作失败')
				}
			})
		} else {
			layer.msg('操作取消', {
				time : 500,
			})
		}
	}
};
function inviteThisObject(id) {
	window.location.href = "/console/message/compose/" + id
};
function inviteObject() {
	var number = 0;
	var checkId = "";
	$("input:checkbox[id^='sel']:checked").each(function(i) {
		number = number + 1;
		checkId = $(this).attr('id');
		checkId = checkId.substring(4)
	});
	if (number == 0) {
		alert("请选择数据")
	} else if (number > 1) {
		alert("您选择了" + number + "条数据，只能选择1条数据进行编辑")
	} else {
		inviteThisObject(checkId)
	}
};