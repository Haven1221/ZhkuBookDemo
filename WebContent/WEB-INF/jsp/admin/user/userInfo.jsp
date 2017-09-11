<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Information</title>
	<style type="text/css">@IMPORT url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
	<div id="global">
	<fieldset>
		<legend>User Manager</legend>
		<h1>User List</h1>
		<form action="<c:url value="/admin/userInfo"/>" method="post">
			<input type="text" name="userName" value="${keyword }"/>
			<input type="submit" value="查询"/>
		</form>
		<c:if test="${deleteError != null }">${deleteError }</c:if>
		<c:choose>
			<c:when test="${userList == null }">查询结果为空！</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>编号</th>
						<th>用户名</th>
						<th>类型</th>
						<th>操作</th>
					</tr>
					<c:forEach var="user" items="${userList }" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${user.userName }</td>
						<td>${user.userType }</td>
						<td>
							<a href="<c:url value="/admin/userOrderInfo?userName=${user.userName }"/>">消费记录</a>
							<a href="<c:url value="/admin/deleteUser?id=${user.userId }&currentPage=${userPage.currentPage }&userName=${keyword }"/>">删除</a>
						</td>
					</tr>
					</c:forEach>
				</table>
				<p align="center">
				每页${userPage.pageNumber }行 共${userPage.totalNumber }行
				页数${userPage.currentPage }/${userPage.totalPage }<br>
				<c:choose>
					<c:when test="${userPage.currentPage == 1 }">首页 上一页</c:when>
					<c:otherwise>
						<a href="<c:url value="/admin/userInfo?currentPage=1&userName=${keyword }"/>">首页</a>
						<a href="<c:url value="/admin/userInfo?currentPage=${userPage.currentPage-1 }&userName=${keyword }"/>">上一页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${userPage.currentPage == userPage.totalPage }">下一页 尾页</c:when>
					<c:otherwise>
						<a href="<c:url value="/admin/userInfo?currentPage=${userPage.currentPage+1 }&userName=${keyword }"/>">下一页</a>
						<a href="<c:url value="/admin/userInfo?currentPage=${userPage.totalPage }&userName=${keyword }"/>">尾页</a>
					</c:otherwise>
				</c:choose>
			</p>
			</c:otherwise>
		</c:choose>
	</fieldset>
	</div>
</body>
</html>