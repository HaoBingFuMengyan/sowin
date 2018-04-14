<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="0">
	<title>后台管理</title>
	<link href="/static/css/login.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="/static/jquery/jquery-2.1.1.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function () {
			$("#login").click(function () {
				$("#inputForm").submit();
            });
        });
	</script>
</head>

<body>
<div class="login_box">
	<div class="login_l_img"><img src="/static/images/login-img.png" /></div>
	<div class="login">
		<div class="login_logo"><a href="#"><img src="/static/images/login_logo.png" /></a></div>
		<div class="login_name">
			<p>后台管理系统</p>
		</div>
		<form id="inputForm" action="/login.html" method="post">
			<input name="susername" type="text"  value="用户名" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名'}">
			<input name="spassword" type="password" id="password" placeholder="密码" />
			<input id="login" value="登录" style="width:100%;" type="submit">
		</form>
	</div>
	<div class="copyright">HaoSHI有限公司 版权所有©2018-2118 技术支持电话：021-00000000</div>
</div>
<div style="text-align:center;">
	<p>更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
</div>
</body>
</html>
