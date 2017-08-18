<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/report/upload.do" method="post" enctype="multipart/form-data">
	选择Word文档：<input type="file" id="input" accept="application/msword" name="wordFile" multiple="multiple" />
	<input type="submit" />
	</form>
	<p>浏览文件时可以选取一个或多个Word文档。</p>
</body>
</html>