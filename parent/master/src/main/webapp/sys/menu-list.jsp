<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="decorator" content="none">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>菜单管理</title>
    <link rel="icon" href="${ctxStatic}/layui-admin/favicon.ico">

    <%--引入jquery插件--%>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-2.1.1.min.js"></script>

</head>

<body>
${list.content}
</body>
</html>
