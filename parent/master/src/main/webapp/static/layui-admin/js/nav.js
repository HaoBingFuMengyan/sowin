var navs = [{
	"title" : "后台首页",
	"shiro": "main:query",
	"icon" : "icon-computer",
	"href" : "main.shtml",
	"spread" : true
},{
	"title" : "文章列表",
    "shiro": "",
	"icon" : "icon-text",
	"href" : "news/newsList.shtml",
	"spread" : false
},{
	"title" : "404页面",
    "shiro": "",
	"icon" : "&#xe61c;",
	"href" : "page/404.html",
	"spread" : false
},{
	"title" : "系统基本参数",
    "shiro": "",
	"icon" : "&#xe631;",
	"href" : "page/systemParameter/systemParameter.html",
	"spread" : false
},{
	"title" : "二级菜单演示",
    "shiro": "",
	"icon" : "&#xe61c;",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "二级菜单1",
            "shiro": "",
			"icon" : "&#xe631;",
			"href" : "",
			"spread" : false
		}
	]
}]