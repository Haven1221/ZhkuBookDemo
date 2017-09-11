<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admination Manager</title>
	<style type="text/css">@IMPORT url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
	<div id="global">
	<fieldset>
		<legend>后台管理</legend>
		<p>
			<a href="<c:url value="/admin/userInfo"/>">User Manager</a>
		</p>
		<p>
			<a href="<c:url value="/admin/tempStoreInfo"/>">TempStore Manager</a>
		</p>
		<p>
			<a href="<c:url value="/admin/storeInfo"/>">Store Manager</a>
		</p>
	</fieldset>
	</div>
</body>
</html>