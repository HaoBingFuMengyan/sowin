<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>--%>
<%@ include file="/include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="0">
	<title>后台管理</title>
	<link rel="icon" href="${ctxStatic}/layui-admin/favicon.ico">
	<link href="${ctxStatic}/css/login.css" rel="stylesheet" type="text/css" />

	<%--引入jquery插件--%>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function () {
			$("#login").click(function () {
				$("#inputForm").submit();
            });
        });

        <%--function refreshCaptcha() {--%>
            <%--$('#captchaImg').hide().attr('src','${ctx}/captcha.html?a=' + Math.floor(Math.random()*100)).fadeIn();--%>
        <%--}--%>
	</script>
</head>

<body>
<div class="login_box">
	<div class="login_l_img"><img src="${ctxStatic}/images/login-img.png" /></div>
	<div class="login">
		<div class="login_logo"><a href="#"><img src="${ctxStatic}/images/login_logo.png" /></a></div>
		<div class="login_name">
			<p>后台管理系统</p>
		</div>
		<form id="inputForm" action="${ctx}/login.html" method="post">
			<input name="susername" type="text"  value="用户名" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名'}">
			<input name="spassword" type="password" id="password" placeholder="密码" onfocus="this.placeholder=''" onblur="if(this.placeholder==''){this.placeholder='密码'}"/>
			<input name="checkcode" type="text" id="checkcode" placeholder="验证码" maxlength="4" onfocus="this.placeholder=''" onblur="if(this.placeholder==''){this.placeholder='验证码'}"/>
			<a onclick="refreshCaptcha()">
				<img class="captcha" id="captchaImg" src="${ctx}/captcha.html" />
			</a>
			<input id="login" value="登录" style="width:100%;" type="submit">
		</form>
	</div>
	<div class="copyright">HaoSHI有限公司 版权所有©2018-2118 技术支持电话：021-00000000</div>
</div>
<div style="text-align:center;">
	<%--<p>更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>--%>
</div>
</body>
</html>
