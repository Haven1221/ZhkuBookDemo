<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Order Detail</title>
	<style type="text/css">@IMPORT url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
	<div id="global">
	<fieldset>
		<legend>订单详情</legend>
		<h1>Order Product</h1>
		<table>
			<tr>
				<th>编号</th>
				<th>商品名称</th>
				<th>单价</th>
				<th>数量</th>
				<th>路径</th>
			</tr>
			<c:forEach var="product" items="${userOrderProduct }" varStatus="vs">
			<tr>
				<td>${vs.count }</td>
				<td>${product.productName }</td>
				<td>${product.productPrice }</td>
				<td>${product.productCount }</td>
				<td>${product.productImage }</td>
			</tr>
			</c:forEach>
		</table>
		<h1>Order Address</h1>
		<p>省市区：${userOrderAddress.addrProvince }</p>
		<p>街道办：${userOrderAddress.addrDetail }</p>
		<p>收货电话：${userOrderAddress.addrPhone }</p>
		<p>收货人：${userOrderAddress.addrConsinee }</p>
	</fieldset>
	</div>
</body>
</html>