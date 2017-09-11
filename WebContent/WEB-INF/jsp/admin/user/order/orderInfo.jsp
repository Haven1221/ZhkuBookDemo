<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Order Information</title>
	<style type="text/css">@IMPORT url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
	<div id="global">
	<fieldset>
		<legend>${userName }的订单列表</legend>
		<h1>Order List</h1>
		<c:choose>
			<c:when test="${userOrderList == null }">该用户还没有消费记录呢</c:when>
			<c:otherwise>
			<table>
				<tr>
					<th>订单号</th>
					<th>店铺名</th>
					<th>下单时间</th>
					<th>总价</th>
					<th>订单状态</th>
					<th>订单详情</th>
				<tr>
				<c:forEach var="order" items="${userOrderList }">
				<tr>
					<td>${order.orderId }</td>
					<td>${order.storeName }</td>
					<td>${order.orderDate }</td>
					<td>${order.orderPrice }</td>
					<td>${order.orderState }</td>
					<td>
						<a href="<c:url value="/admin/userOrderDetail?orderId=${order.orderId }&addrId=${order.addrId }"/>">查看</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<p align="center">
				每页${userOrderPage.pageNumber }行 共${userOrderPage.totalNumber }行
				页数${userOrderPage.currentPage }/${userOrderPage.totalPage }<br>
				<c:choose>
					<c:when test="${userOrderPage.currentPage == 1 }">首页 上一页</c:when>
					<c:otherwise>
						<a href="<c:url value="/admin/userOrderInfo?currentPage=1&userName=${userName }"/>">首页</a>
						<a href="<c:url value="/admin/userOrderInfo?currentPage=${userOrderPage.currentPage-1 }&userName=${userName }"/>">上一页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${userOrderPage.currentPage == userOrderPage.totalPage }">下一页 尾页</c:when>
					<c:otherwise>
						<a href="<c:url value="/admin/userOrderInfo?currentPage=${userOrderPage.currentPage+1 }&userName=${userName }"/>">下一页</a>
						<a href="<c:url value="/admin/userOrderInfo?currentPage=${userOrderPage.totalPage }&userName=${userName }"/>">尾页</a>
					</c:otherwise>
				</c:choose>
			</p>
			</c:otherwise>
		</c:choose>
	</fieldset>
	</div>
</body>
</html>