$(document).ready(function() {
	$('.i-checks').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
	});
	
	$("thead input.i-checks").on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		$("input.i-checks").iCheck('check');
    	});

	$("thead input.i-checks").on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		$("input.i-checks").iCheck('uncheck');
    	});
});