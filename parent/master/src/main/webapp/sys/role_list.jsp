<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/include/taglib.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>提单列表 </h5>
        </div>
        <div class="ibox-content">
            <table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th class="sort-column">提货编号</th>
                    <th class="sort-column">提货方式</th>
                    <th class="sort-column">开单日期</th>
                    <th class="sort-column">状态</th>
                    <th class="sort-column">收货人</th>
                    <th class="sort-column">手机号</th>
                    <th class="sort-column">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="obj">
                    <tr>
                        <td>${obj.srolename}</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
        </div>
    </div>
</div>
</body>
</html>
