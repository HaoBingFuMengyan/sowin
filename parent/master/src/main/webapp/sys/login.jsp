<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
</head>
<body>
	<form action="/login.shtml" method="post">
		<span>用户名</span>
			<input type="text" name="susername" ><br>
		<span>密码&nbsp;&nbsp;&nbsp;</span>
			<input type="text" name="spassword"/>
		<button type="submit" value="提交">提交</button>
	</form>

</body>
</html>