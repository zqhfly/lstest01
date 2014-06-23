<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>处理成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#main {margin:20px;}
	</style>

  </head>
  
  <body id="main">
  	<br/><div><s:label value="处理成功!请点击下载："></s:label>
  	<a href="DownloadAction.action?fileName=<s:property value="excelName"/>" target="_blank">
  		<s:property value="excelName"/>
  	</a><br/><br/></div>
  	<div><s:if test="errExcelName!=null">
  		<s:label value="部分订单查询出错!请点击下载："></s:label>
  		<a href="DownloadAction.action?fileName=<s:property value="errExcelName"/>" target="_blank">
  		<s:property value="errExcelName"/>
  	</a><br/><br/>
  	</s:if></div>
  	<div><a href="/cost/index.jsp">
		<span class="back">
			&lt;&lt;&nbsp;&nbsp;点击继续处理
		</span>
	</a><br/><br/></div>
	<table border="1" bgColor="#FFFFFF" borderColor="#000000" width="95%" cellpadding="0" cellspacing="0">
  		<tr align="center">
  			<td colspan="10" ><font size="5">快递单费用核对</font></td>
  		</tr>
  		<tr align="center">
  			<td>快递单号</td>
  			<td>快递公司</td>
  			<td>快递费用</td>
  			<td>收货地址</td>
  			<td>店铺名称</td>
  			<td>订单编号</td>
  			<td>用户id</td>
  			<td>下单时间</td>
  			<td>扫描人员</td>
  			<td>扫描时间</td>
  		</tr>
  		<s:iterator value="list" id="array">
  			<tr align="center">
  				<s:iterator value="#array" id="element">
  					<td><s:property value="element"/></td>
  				</s:iterator>
  			</tr>
  		</s:iterator>
  		<tr align="center">
  			<td colspan="2" ><font size="4">费用合计</font></td>
  			<td><s:property value="total"/></td>
  			<td colspan="6"></td>
  		</tr>
  	</table>
  	<br/><br/>
  	<div><s:if test="errExcelName!=null">
  		<s:label value="出错的订单信息如下："></s:label>
  		<br/><br/>
  		<table border="1" bgColor="#FFFFFF" borderColor="#000000" width="20%" cellpadding="0" cellspacing="0">
  		<tr align="center">
  			<td><font size="4">快递单号</font></td>
  		</tr>
  		<s:iterator value="err" id="errArray">
  			<tr align="center">
  				<s:iterator value="#errArray" id="errElement">
  					<td><s:property value="errElement"/></td>
  				</s:iterator>
  			</tr>
  		</s:iterator>
  	</table>
  	<br/><br/>
  	</s:if></div>
  </body>
</html>
