<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>H+ 后台主题UI框架 - 主页</title>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/><![endif]-->

    <link rel="shortcut icon" href="${ctxStatic}/bootstrap-table-master/docs/favicon.ico">

    <%@ include file="/include/head.jsp"%>
    <script src="${ctxStatic}/common/inspinia.js"></script>
    <script src="${ctxStatic}/common/contabs.js"></script>
    <script src="${ctxStatic}/jquery/jquery.cookie.js"></script>

    <!-- 即时聊天插件  开始-->
    <link href="${ctxStatic}/layer-v2.3/layim/layui/css/layui.css" type="text/css" rel="stylesheet"/>


    <script type="text/javascript">
        $(document).ready(function() {
            // 默认主题
            $("body").removeClass("skin-2");
            $("body").removeClass("skin-3");
            $("body").removeClass("skin-1");

            var lasturl= $.cookie('last_frame');
            if(lasturl){
                $('.J_menuItem').each(function(){
                    if($(this).attr('href')==lasturl){
                        $(this).trigger('click');
                    }

                });
            }

        });
    </script>
</head>

<body class=" fixed-sidebar full-height-layout gray-bg"  style="">


<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element" style="text-align: center;">

                        <div style="font-size: 15px;font-weight: bold;color:white;">
                            企业微信点餐系统后台管理
                        </div>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs">欢迎您，<strong class="font-bold">admin</strong></span>
                                </span>
                        </a>
                        <a class="changepw" href="javascript:openlog('修改密码','/sys/operator/operatorSpassword.shtml','550px','330px')">[修改密码]</a>
                    </div>
                    <div class="logo-element">微信点餐
                    </div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-home"></i> <span class="nav-label">主页</span> <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="#">主页示例一</a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a href="#"><i class="fa fa-table"></i>
                        <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${ctx}/sys/menu/index.shtml">菜单管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${ctx}/role/index.shtml">DataTables</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="table_jqgrid.html">jqGrid</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="table_foo_table.html">Foo Tables</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="table_bootstrap.html">Bootstrap Table
                                <span class="label label-danger pull-right">推荐</span></a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row content-tabs">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
            <button class="roll-nav roll-left J_tabLeft" style="left:50px;"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs" style="margin-left:90px;width:auto;">
                <div class="page-tabs-content" style="line-height:31px;">
                    <a href="javascript:;" class="active J_menuTab" data-id="/home.shtml">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose"  data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="${ctx}/logout.shtml" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main" style="background-color: lightgrey;">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/main.shtml" frameborder="0" data-id="${ctx}/main.shtml" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->


</div>

</body>
<!-- 语言切换插件，为国际化功能预留插件 -->
<script type="text/javascript">

    $(document).ready(function(){

        $("a.lang-select").click(function(){
            $(".lang-selected").find(".lang-flag").attr("src",$(this).find(".lang-flag").attr("src"));
            $(".lang-selected").find(".lang-flag").attr("alt",$(this).find(".lang-flag").attr("alt"));
            $(".lang-selected").find(".lang-id").text($(this).find(".lang-id").text());
            $(".lang-selected").find(".lang-name").text($(this).find(".lang-name").text());

        });


    });

    function changeStyle(){
        $.get('${pageContext.request.contextPath}/theme/ace?url='+window.top.location.href,function(result){   window.location.reload();});
    }

</script>
<style>
    /*签名样式*/
    .layim-sign-box{
        width:95%
    }
    .layim-sign-hide{
        border:none;background-color:#F5F5F5;
    }
</style>

</html>
