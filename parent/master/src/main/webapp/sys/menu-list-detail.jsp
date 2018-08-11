<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/taglib.jsp" %>
<html>
<head>
    <title>菜单管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/include/head.jsp"%>
    <script type="text/javascript">
        var validateForm;
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if (validateForm.form()) {
                $("#inputForm").submit();
                return true;
            }
            return false;
        }
        $(document).ready(function () {
//            focusmenu('菜单管理');
            validateForm = $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });

    </script>
</head>
<body class="  gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-tools">
            <a class="collapse-link">
                <i class="fa fa-chevron-up"></i>
            </a>
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-wrench"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#">选项1</a>
                </li>
                <li><a href="#">选项2</a>
                </li>
            </ul>
            <a class="close-link">
                <i class="fa fa-times"></i>
            </a>
        </div>
        <div class="ibox-content">
            <div class="row">
                <form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save.shtml" method="post"
                           class="form-horizontal">
                    <form:hidden path="id"/>
                    <form:hidden path="smenuno" value="${menu.smenuno}"/>
                    <form:hidden path="bisleaf" value="${menu.bisleaf}"/>
                    <form:hidden path="bisroot" value="${menu.bisroot}"/>
                    <%--<sys:message content="${message}"/>--%>
                    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
                        <tbody>
                        <tr>
                            <td class="width-15 active"><label class="pull-right">上级菜单:</label></td>
                            <td class="width-35"><sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}"
                                                                 labelName="parent.sname"
                                                                 labelValue="${menu.parent.sname}"
                                                                 title="菜单" url="/sys/menu/treeData.shtml"
                                                                 extId="${menu.id}"
                                                                 cssClass="form-control required"/></td>
                            <td class="width-15 active"><label class="pull-right"><font color="red">*</font> 名称:</label>
                            </td>
                            <td class="width-35"><form:input path="sname" htmlEscape="false" maxlength="50"
                                                             class="required form-control "/></td>
                        </tr>
                        <tr>
                            <td class="width-15 active"><label class="pull-right">图标:</label></td>
                            <td class="width-35"><sys:iconselect id="icon" name="simagepath"
                                                                 value="${menu.simagepath}"/></td>
                            <td class="width-15 active"><label class="pull-right">菜单链接:</label></td>
                            <td class="width-35"><form:input path="smenupath" htmlEscape="false" maxlength="2000"
                                                             class="form-control "/>
                                <span class="help-inline">点击菜单跳转的页面</span></td>
                        </tr>

                        <tr>
                            <td class="width-15 active"><label class="pull-right">描述:</label></td>
                            <td class="width-35"><form:textarea path="sremark" htmlEscape="false" rows="3"
                                                                maxlength="200" class="form-control "/></td>
                            <td class="width-15 active"><label class="pull-right">排序:</label></td>
                            <td class="width-35"><input name="isort" type="number" value="${menu.isort}"
                                                        class="form-control" autocomplete="off"/></td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>