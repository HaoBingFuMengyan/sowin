<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/taglib.jsp" %>
<html>
<head>
    <title>角色管理</title>
    <%--暂时没有装饰器--%>
    <%--<meta name="decorator" content="default"/>--%>
    <%@include file="/include/head.jsp"%>

    <script type="text/javascript">
        function jumpPage(pageNo) {
            $("#pageNo").val(pageNo);

            $("#searchForm").submit();
        }
    </script>
</head>
<body class="">
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>角色管理</h5>
        </div>
        <div class="ibox-content">
            <%--<sys:message content="${message}"/>--%>

            <!-- 查询条件 -->
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" action="${ctx}/role/index.shtml" method="post" class="form-inline">
                        <input type="hidden" name="pageNo" id="pageNo" value="0"/>
                    </form:form>
                    <br/>
                </div>
            </div>

            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
                        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"
                                onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新${list.totalElements}
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
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list.content}" var="role">
                    <tr>
                        <td> <input type="checkbox" value="${role.id}" class="i-checks"></td>
                        <td>${role.srolename}</td>
                        <td>${role.sremark}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                    <div class="scott">
                        <span class="disabled">上一页</span>
                        <a href="javascript:jumpPage(0)">1</a>
                        <a href="javascript:jumpPage(1)">2</a>
                        <a href="javascript:jumpPage(2)">3</a>
                        <a href="javascript:jumpPage(3)">4</a>
                        <a href="javascript:jumpPage(1)">下一页</a>
                    </div>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>