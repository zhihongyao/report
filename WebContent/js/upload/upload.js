var opts = {
		  lines: 12 // The number of lines to draw
		, length: 7 // The length of each line
		, width: 5 // The line thickness
		, radius: 10 // The radius of the inner circle
		, scale: 1.0 // Scales overall size of the spinner
		, corners: 1 // Corner roundness (0..1)
		, color: '#ffffff' // #rgb or #rrggbb or array of colors
		, opacity: 0.25 // Opacity of the lines
		, rotate: 0 // The rotation offset
		, direction: 1 // 1: clockwise, -1: counterclockwise
		, speed: 1 // Rounds per second
		, trail: 100 // Afterglow percentage
		, fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
		, zIndex: 2e9 // The z-index (defaults to 2000000000)
		, className: 'spinner' // The CSS class to assign to the spinner
		, top: '50%' // Top position relative to parent
		, left: '50%' // Left position relative to parent
		, shadow: false // Whether to render a shadow
		, hwaccel: false // Whether to use hardware acceleration
		, position: 'absolute' // Element positioning
},
optsSmart = {
		  lines: 8 // The number of lines to draw
		, length: 6 // The length of each line
		, width: 3 // The line thickness
		, radius: 5 // The radius of the inner circle
		, scale: 1.0 // Scales overall size of the spinner
		, corners: 1 // Corner roundness (0..1)
		, color: '#ffffff' // #rgb or #rrggbb or array of colors
		, opacity: 0.25 // Opacity of the lines
		, rotate: 0 // The rotation offset
		, direction: 1 // 1: clockwise, -1: counterclockwise
		, speed: 1 // Rounds per second
		, trail: 100 // Afterglow percentage
		, fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
		, zIndex: 2e9 // The z-index (defaults to 2000000000)
		, className: 'spinner' // The CSS class to assign to the spinner
		, top: '50%' // Top position relative to parent
		, left: '50%' // Left position relative to parent
		, shadow: false // Whether to render a shadow
		, hwaccel: false // Whether to use hardware acceleration
		, position: 'absolute' // Element positioning
},
_layer = window.layer,
spinner;
(function(window, document, $, undef) {
	if (_layer != undefined) {
		_layer.config({
			extend : [ 'skin/moon/style.css' ]
		});
	}
	var target = $(document.body)[0];
	var ajaxSubmitForm = function() {
		var value = $("input[name=wordFile]").val();
		if (value == null || value == undefined || value == '') {
			layer.alert('<font size="3" color="black">请选择文件</font>', {
				icon : 0,
				skin : 'layer-ext-moon'
			}, function(index) {
				layer.close(index);
			});
			return false;
		}
		if (!value.match(/.doc|.docx|.rar|.zip/i)) {
			layer.alert('<font size="3" color="black">文件格式错误</font>', {
				icon : 2,
				skin : 'layer-ext-moon'
			}, function(index) {
				layer.close(index);
			});
			return false;
		}
		spinner = new Spinner(opts).spin(target);
		$.blockUI({message:''});
		var option = {
			url : _contextPath + '/report/upload.do',
			type : 'POST',
			dataType : 'json',
			headers : {
				"ClientCallMode" : "ajax"
			}, // 添加请求头部
			success : function(data) {
				if (data.status == 'success') {
					downloadFileByForm(data.object.filePath,
							data.object.fileName);
				} else {
					layer.alert('<font size="3" color="black">文件上传失败，请稍后重试。</font>', {
						icon : 2,
						skin : 'layer-ext-moon'
					}, function(index) {
						layer.close(index);
					});
				}
			},
			error : function(data) {
				layer.alert('<font size="3" color="black">文件上传失败，请稍后重试。</font>', {
					icon : 2,
					skin : 'layer-ext-moon'
				}, function(index) {
					layer.close(index);
				});
			},
			complete:function(){
				spinner.stop();
				$.unblockUI();
			}
		};
		$("#uploadForm").ajaxSubmit(option);
		return false; // 最好返回false，因为如果按钮类型是submit,则表单自己又会提交一次;返回false阻止表单再次提交
	}

	$('#submit').off('click').on('click', function() {
		ajaxSubmitForm();
	});

	function downloadFileByForm(filePath, fileName) {
		$('#downloadForm').find('input[name=filePath]').val(filePath);
		$('#downloadForm').find('input[name=fileName]').val(fileName);
		$('#downloadForm').submit();
	}
	$('input[name=wordFile]').off('change').on('change', function(e) {// 上传
		$('.m-file-list').empty();
		var fileNames = $(this)[0].files;
		if(fileNames == undef){
			var file = $('input[name=wordFile]');
			file.after(file.clone());
			file.remove();
			layer.alert('<font size="3" color="black">浏览器版本太低或者不支持，请换用其他浏览器。</font>', {
				icon : 0,
				skin : 'layer-ext-moon'
			}, function(index) {
				layer.close(index);
				window.location.href = window.location.href;
			});
			return false;
		}
		if(fileNames.length > 0){
			$('.file-empty').addClass('hidden');
			$('.m-file-list').removeClass('hidden');
		}else{
			$('.file-empty').removeClass('hidden');
			$('.m-file-list').addClass('hidden');
		}
		for(var i = 0; i < fileNames.length; i++){
			var fileName = fileNames[i].name;
			$('.m-file-list').append('<li>'+fileName+'</li>');
		}
	});
})(window, document, jQuery, void 0);
var downloadError = function(msg) {
	layer.alert('<font size="3" color="black">' + msg + '</font>', {
		icon : 0,
		skin : 'layer-ext-moon'
	}, function(index) {
		layer.close(index);
	});
}