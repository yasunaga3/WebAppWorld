<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	List<Country> countries = (List<Country>)request.getAttribute("COUNTRIES");
	int countries_size = countries.size();
%>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>index.jsp</title>
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body onLoad="functionName()">
	<form name="formName" method="post" action="/index">
		<!--選択肢その1-->
		<select name = "selectName1" onChange="functionName()">
			<option value = "果物">くだもの</option>
			<option value = "野菜">やさい</option>
			<option value = "肉類">にくるい</option>
		</select>
		<!--選択肢その2（選択肢その1の項目によって変化）-->
		<select name = "selectName2">
		</select>
		<br />
		size=<%=countries_size %>

	</form>
	<p><a href="<c:url value='/index' />">index</a></p>
	 <script src="<c:url value='/css/sp1.js' />"></script>
</body>
</html>