/**
 * Created by wangjianglin on 2017/5/2.
 */
/**
 * 单据设置工作流选择模版
 * @param ibiztype 单据类型： 0:PROXY:代理采购 1:CDZY:仓单质押
 * @param sbizid 单据ID
 * @param sbizno 单据编号
 */
function doSetWorkFlowTemp(ibiztype,sbizid,sbizno){
    var url = ctx+"/wf/workflowtemplate/choicetemplatelist.shtml";
    layer.open({
        type: 2,
        area: ["900px", "450px"],
        title: "工作流模版列表",
        maxmin: true, //开启最大化最小化按钮
        content : url,
        btn: ['确定', '关闭'],
        yes: function(index, layero){
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var result = iframeWin.contentWindow.doSubmit();

            top.openTab(ctx+"/wf/workflow/form.shtml?ibiztype="+ibiztype+"&sbizid="+sbizid+"&sbizno="+sbizno+"&stemplateid="+result,'设置审批流',false);
            setTimeout(function(){layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
        },
        cancel: function(index){}
    });
}