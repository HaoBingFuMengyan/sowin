<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/taglib.jsp" %>
<html>
<head>
    <title>菜单管理</title>
    <%--暂时没有装饰器--%>
    <%--<meta name="decorator" content="default"/>--%>
    <%@include file="/include/head.jsp"%>
    <script type="text/javascript">
        $(document).ready(function () {

        });

    </script>
</head>
<body class="">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>菜单列表 </h5>
        </div>
        <div class="ibox-content">
            <%--<sys:message content="${message}"/>--%>

            <!-- 查询条件 -->
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" action="${ctx}/sys/menu/index.shtml" method="post" class="form-inline">
                    </form:form>
                    <br/>
                </div>
            </div>

            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
                        <a onclick="openlog('新增菜单','${ctx}/sys/menu/form.shtml','1000px','500px');" class="btn btn-white btn-sm"><i class="fa fa-plus"></i>添加</a>
                        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left"
                                onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新
                        </button>
                    </div>
                </div>
            </div>

            <%-- 表格Content --%>
            <table id="treeTable"
                   class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th><input type="checkbox" class="i-checks"></th>
                    <th>名称</th>
                    <th>链接</th>
                    <th style="text-align:center;">排序</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="menu">
                    <tr id="${menu.id}" pId="${menu.sparentid ne '0'?menu.sparentid:'0'}">
                        <td> <input type="checkbox" id="${menu.id}" value="${menu.id}" class="i-checks"></td>
                        <td nowrap><i class="icon-menu.icon"></i><a onclick="openlogView('查看菜单', '${ctx}/sys/menu/form.shtml?id=${menu.id}','1000px', '500px')">${menu.sname}</a></td>
                        <td >${menu.smenupath}</td>
                        <td style="text-align:center;">
                                ${menu.isort}
                        </td>
                        <td nowrap>

                            <a onclick="openlogView('查看菜单','${ctx}/sys/menu/form.shtml?id=${menu.id}','1000px','500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
                            <a onclick="openlog('菜单管理','${ctx}/sys/menu/form.shtml?id=${menu.id}','1000px','500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
                            <a href="${ctx}/sys/menu/delete.shtml?id=${menu.id}" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)" class="btn btn-danger btn-xs" ><i class="fa fa-trash"></i> 删除</a>
                            <a onclick="openlog('菜单管理','${ctx}/sys/menu/form.shtml?sparentid=${menu.id}','1000px','500px')" class="btn btn-primary btn-xs" ><i class="fa fa-plus"></i> 添加下级菜单</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>