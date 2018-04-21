<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/include/taglib.jsp" %>
<%@ include file="/include/head.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--引入layui插件--%>
    <link rel="stylesheet" href="${ctxStatic}/layuicms2.0/layui/css/layui.css"  media="all">
    <script src="${ctxStatic}/layuicms2.0/layui/layui.all.js" charset="utf-8"></script>
    <script src="${ctxStatic}/layuicms2.0/layui/lay/modules/form.js" charset="utf-8"></script>

    <script type="text/javascript">
        var form;
        $(document).ready(function () {
            //修饰组件
            layui.use('form', function(){
                form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                //……
                //但是，如果你的HTML是动态生成的，自动渲染就会失效
                //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
                form.render();
            });

            layui.use('laypage', function(){
                var laypage = layui.laypage;

                //执行一个laypage实例
                laypage.render({
                    elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                    ,count: 50 //数据总数，从服务端得到
                    ,jump: function(obj, first){
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数

                        //首次不执行
                        if(!first){
                            //do something
                        }
                    }
                });
            });
            //全选、反选
            form = layui.form;
            form.on('checkbox(allChoose)', function(data){
                var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                child.each(function(index, item){
                    item.checked = data.elem.checked;
                });
                form.render('checkbox');
            });
        });

        function deletess(){
        }
    </script>
</head>
<body>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>角色列表</legend>
    </fieldset>

    <%--查询条件--%>
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">验证手机</label>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">验证邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">QQ</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">验证手机</label>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>

                <button class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-normal">查询</button>
            </div>

        </div>
    </form>
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="20">
                <col width="20">
                <col width="150">
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" lay-skin="primary" lay-filter="allChoose"/>
                    </th>
                    <th>序号</th>
                    <th>角色名称</th>
                    <th>备注</th>
                    <th>操作</th>
                    <th>格言</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="obj" varStatus="objIndex">
                    <tr>
                        <td>
                            <input type="checkbox" lay-skin="primary" />
                        </td>
                        <td>${objIndex.index + 1}</td>
                        <td>${obj.srolename}</td>
                        <td>${obj.sremark}</td>
                        <td>
                            <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="deletess()"><i class="layui-icon">口</i>删除</button>
                        </td>
                        <td>人生似修行</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <input type="checkbox" lay-skin="primary" />
                    </td>
                    <td>2</td>
                    <td>张爱玲</td>
                    <td>汉族</td>
                    <td>1920-09-30</td>
                    <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" lay-skin="primary" />
                    </td>
                    <td>3</td>
                    <td>岳飞</td>
                    <td>汉族</td>
                    <td>1103-北宋崇宁二年</td>
                    <td>教科书再滥改，也抹不去“民族英雄”的事实</td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" lay-skin="primary" />
                    </td>
                    <td>4</td>
                    <td>孟子</td>
                    <td>华夏族（汉族）</td>
                    <td>公元前-372年</td>
                    <td>猿强，则国强。国强，则猿更强！ </td>
                </tr>
            </tbody>
        </table>
        <div id="test1"></div>
    </div>
</body>
</html>