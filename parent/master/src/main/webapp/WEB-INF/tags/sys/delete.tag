<!--  异步删除
把要删除的复选框的value设成id
 例子链接（括号以内要全部复制）：（ https://shimo.im/doc/NPWz54CncfUfajO2/「deleteSome()」） -->

<!-- 参数1提示语，参数2是链接 ，在jeeplus.js中定义了deleteSome() -->
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/taglib.jsp"%>
<%@ attribute name="message" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<button class="btn btn-white btn-sm" onclick="deleteSome('${message}','${url}')" data-toggle="tooltip" data-placement="top"><i class="fa fa-trash-o"></i>删除</button>

<script type="text/javascript">

</script>