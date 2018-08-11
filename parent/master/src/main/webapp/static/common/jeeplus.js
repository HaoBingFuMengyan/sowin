/*!
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 * 
 * 通用公共方法
 * @author jeeplus
 * @version 2014-4-29
 */
$(document).ready(function() {
	try{
		// 链接去掉虚框
		$("a").bind("focus",function() {
			if(this.blur) {this.blur()};
		});
		//所有下拉框使用select2
		$("select").select2();
	}catch(e){
		// blank
	}
});
function _nav(url,para,frmId){
	var s="";
	
	//alert(s);
	if(frmId){
		var _url=$("#"+frmId).attr("action");
		if(_url&&_url.length>1)
			url=_url;
		else
			url=window.location.href;
	}
	if(url.indexOf('?')<0)
		url+="?x_x=1";
	url+=s; 

	var pams = [];
	var d;
	if(frmId){
	d= $("#"+frmId).serializeArray();
		for ( var i in d) {
			pams.push($('<input>', {
				name : d[i].name,
				value : d[i].value
			}));
		}
	}
	
	if(para){
		d = para;
		for ( var i in d) {
			pams.push($('<input>', {
				name : i,
				value : d[i]
			}));
		}
	}
	var f = $('<form>', {
		action : url
	}).append(pams);
	var div=$('<div>');
	div.css('display','none');
	div.append(f);
	$('body').append(div); 
	f.submit();
	
}
// 引入js和css文件
function include(id, path, file){
	if (document.getElementById(id)==null){
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++){
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + path + name + "'";
            document.write("<" + tag + (i==0?" id="+id:"") + attr + link + "></" + tag + ">");
        }
	}
}

// 获取URL地址参数
function getQueryString(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == ""){
	    url = window.location.search;
    }else{	
    	url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null) return unescape(r[2]); return null;
}

//获取字典标签
function getDictLabel(data, value, defaultValue){
	for (var i=0; i<data.length; i++){
		var row = data[i];
		if (row.value == value){
			return row.label;
		}
	}
	return defaultValue;
}

// 打开一个窗体
function windowOpen(url, name, width, height){
	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	window.open(url ,name , options);
}

// 恢复提示框显示
function resetTip(){
	top.$.jBox.tip.mess = null;
}

// 关闭提示框
function closeTip(){
	top.$.jBox.closeTip();
}

//显示提示框
function showTip(mess, type, timeout, lazytime){
	resetTip();
	setTimeout(function(){
		top.$.jBox.tip(mess, (type == undefined || type == '' ? 'info' : type), {opacity:0, 
			timeout:  timeout == undefined ? 2000 : timeout});
	}, lazytime == undefined ? 500 : lazytime);
}

// 显示加载框
function loading(mess){
	if (mess == undefined || mess == ""){
		mess = "正在提交，请稍等...";
	}
	resetTip();
	top.$.jBox.tip(mess,'loading',{opacity:0});
}

// 警告对话框
function alertx(mess, closed){
	top.$.jBox.info(mess, '提示', {closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
	top.$('.jbox-body .jbox-icon').css('top','55px');
}

// 确认对话框
function confirmx(mess, href, closed){
	
	top.layer.confirm(mess, {icon: 3, title:'系统提示'}, function(index){
	    //do something
		if (typeof href == 'function') {
			href();
		}else{
			resetTip(); //loading();
			location = href;
		}
	    top.layer.close(index);
	});
	
	return false;
}

// 提示输入对话框
function promptx(title,  href){

	 var index = top.layer.prompt({title: title, formType: 2}, function(text){
		 if (typeof href == 'function') {
				href();
			}else{
				resetTip(); //loading();
				location = href + encodeURIComponent(text);
			}
		 
		 top.layer.close(index);
		  });
	return false;
}


// cookie操作
function cookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
}

// 数值前补零
function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}

// 转换为日期
function strToDate(date){
	return new Date(date.replace(/-/g,"/"));
}

// 日期加减
function addDate(date, dadd){  
	date = date.valueOf();
	date = date + dadd * 24 * 60 * 60 * 1000;
	return new Date(date);  
}

//截取字符串，区别汉字和英文
function abbr(name, maxLength){  
 if(!maxLength){  
     maxLength = 20;  
 }  
 if(name==null||name.length<1){  
     return "";  
 }  
 var w = 0;//字符串长度，一个汉字长度为2   
 var s = 0;//汉字个数   
 var p = false;//判断字符串当前循环的前一个字符是否为汉字   
 var b = false;//判断字符串当前循环的字符是否为汉字   
 var nameSub;  
 for (var i=0; i<name.length; i++) {  
    if(i>1 && b==false){  
         p = false;  
    }  
    if(i>1 && b==true){  
         p = true;  
    }  
    var c = name.charCodeAt(i);  
    //单字节加1   
    if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {  
         w++;  
         b = false;  
    }else {  
         w+=2;  
         s++;  
         b = true;  
    }  
    if(w>maxLength && i<=name.length-1){  
         if(b==true && p==true){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         if(b==false && p==false){  
             nameSub = name.substring(0,i-3)+"...";  
         }  
         if(b==true && p==false){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         if(p==true){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         break;  
    }  
 }  
 if(w<=maxLength){  
     return name;  
 }  
 return nameSub;  
}
function openHref(url){
	openHref(url,false);
}
//打开链接
function openHref(url,nosubmit){
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	var dlg={
		    type: 2,  
		    area: ['auto', 'auto'],
		    title: "",
	        maxmin: false, //开启最大化最小化按钮
		    content: nosubmit?(url+"&view=true"):url ,
			cancel: function(index){}
		};
	if(nosubmit){
		dlg["btn"]=['关闭'];
	}
	else{
		dlg["btn"]=['确定', '关闭'];
	
		dlg["btn1"]=function(index, layero){
		
			var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			var $=iframeWin.contentWindow.$;
			var doc=$(iframeWin.contentWindow.document);
			
			doc.find('form').first().submit() 
	        
			
		  };
	}
	var index=layer.open(dlg);
	layer.full(index);  

}

//打开链接
function openHrefView(url){
	openHref(url,true)
}

//打开对话框(添加修改)
function openDialog(title,url,width,height,target){
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	
	var index=layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			var $=iframeWin.contentWindow.$;
			var doc=$(iframeWin.contentWindow.document);
			doc.find('form').first().submit() 
	        
		  },
		  cancel: function(index){}
	}); 	
	layer.full(index);  
	
}
//打开对话框(添加修改)   弹窗
function openlog(title,url,width,height,target){
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	
	var index=layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			var $=iframeWin.contentWindow.$;
			var doc=$(iframeWin.contentWindow.document);
			doc.find('form').first().submit() 
	        
		  },
		  cancel: function(index){}
	}); 	
}
//打开对话框(查看)
function openDialogView(title,url,width,height){
	
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	var index=layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 
	layer.full(index);  
}
//打开对话框(查看) 弹窗
function openlogView(title,url,width,height){
	
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
		
	}
	var index=layer.open({
		type: 2,  
		area: [width, height],
		title: title,
		maxmin: true, //开启最大化最小化按钮
		content: url ,
		btn: ['关闭'],
		cancel: function(index){ 
		}
	}); 
}

//查询，页码清零
function search(){
	$("#pageNo").val(0);
	$('input').val(function(_, value) { return $.trim(value); });  //全局去空格 
	$("#searchForm").submit();
	return false;
}

function reset(){//重置，页码清零
	$("#pageNo").val(0);
	$("#searchForm div.form-group input").val("");
	$("#searchForm div.form-group select").val("");
	$("#searchForm").submit();
		return false;
	 }
function sortOrRefresh(){//刷新或者排序，页码不清零
	$("#searchForm").submit();
	return false;
}
function page(n,s){//翻页
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	$("span.page-size").text(s);
	return false;
}

//全局回车提交
document.onkeydown=function(event){ 
    e = event ? event :(window.event ? window.event : null); 
    if(e.keyCode==13){
    	if(! function(){try{return doSubmit(),true}catch(e){}}() ){
    		search();
    		return false;
    	}
    	doSubmit(); 
    } 
}

/**
 * 异步删除
 * 如果删除一条数据就要传一条id，如果删多条第三个参数ids可以不用传,给每一条checkbox设置value（要删除的数据的id）即可
 */
function deleteSome(){
	var message=arguments[0];   //参数1，提示语
	var url=arguments[1];   //参数2 ， 链接
	var ids=arguments[2]; 	//参数3，要删除数据的id
	var flag = true;
	var checkid=new Array();
	if(ids == undefined || ids == ""){
		$("tbody input.i-checks:checked").each(function(i){
			checkid[i]=$(this).val();
		});
		if(checkid.length <= 0){
			 $.jBox.tip("至少选择一条数据！", 'error');
			 flag = false;
		}
    }else{
    	checkid[0]=ids ;
    }
	if(flag){
		top.layer.confirm(message, {icon: 3, title:'系统提示'},function(index){
			$.ajax({
			    url:url,
			    data:{ids:checkid},
			    traditional:true,
			    success:function(data,textStatus,jqXHR){
			    	if(data.success){
						 $.jBox.tip('操作成功！', 'info');
						 for (var i=0;i<checkid.length;i++){
							 $("#"+checkid[i]).hide(1000,function(){
								 $("#"+checkid[i]).remove();
							 });
						 }
					 }else{
                        if(data.msg)
                            $.jBox.tip(data.msg, 'error');
                        else
                            $.jBox.tip("未知错误,返回消息格式不正确!需要异步提交", 'error');
				     }
			    },
			})
			top.layer.close(index);
		});
	}
}


/**
 * 关闭openDiaLog
 * @returns
 */
function closeDia(){
	 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
     parent.layer.close(index);
}

/**
 * 如果字符串为空 就返回true
 * @param str
 * @returns
 */
function isnull(str){
	return (str == undefined || str == null || str.trim()=='');
}
