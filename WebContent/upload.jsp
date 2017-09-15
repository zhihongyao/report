<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>Word To Excel</title>
	<link rel="icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
	
	<link type="text/css" href="<%=request.getContextPath()%>/css/buttons.css" rel="stylesheet" />
	<link type="text/css" href="<%=request.getContextPath()%>/css/upload/upload.css" rel="stylesheet" />
	<script type="text/javascript">
		var _contextPath = '<%=request.getContextPath()%>';
	</script>
</head>
<body>
	<div class="g-upload-container">
		<!-- accept="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-zip-compressed,application/x-rar-compressed,application/octet-stream" -->
		<form action="<%=request.getContextPath()%>/report/upload.do"
			method="post" id="uploadForm" enctype="multipart/form-data">
			<h3>选择Word文档/压缩包</h3>
			<div class="m-upload-wrap">
				<a href="javascript:;" class="u-select-file">
				   <input type="file" name="wordFile" multiple="multiple" class="button button-pill button-tiny">点击这里选择文件
				</a>
				<div class="m-file-container">
					<h4>已选文件</h4>
					<span class="file-empty">没有选择文件</span>
					<ul class="m-file-list hidden">
					</ul>
				</div>
				<a href="javascript:;" class="u-uplaod-file">
					<input type="button" id="submit" value="开始分析" class="button button-primary button-pill button-small" />
				</a>
			</div>
		</form>
		<form action="<%=request.getContextPath()%>/report/download.do" method="post" id="downloadForm" target="downloadFileFrame">
			 <input type="hidden" name="filePath" value="" />
			 <input type="hidden" name="fileName" value="" />
		</form>
		<div class="m-remark">
			浏览文件时可以选取一个或多个Word文档（如果Word文档过多，避免浏览器卡顿，请将所有文档打成.zip或者.rar压缩包，直接上传压缩包）。
		</div>
	</div>
	<iframe src="javascript:void(0);" id="downloadFileFrame" name="downloadFileFrame" style="display:none;" frameborder="0"></iframe>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/blockUI/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/spin/spin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/upload/upload.js"></script>
</body>
</html>