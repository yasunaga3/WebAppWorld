<!-- https://yuu.1000quu.com/jquery_hierselect -->
<!-- http://onocom.net/blog/jquery-table-search-simple/ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%
	// 大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を取得する
	CountryTable[] asia = (CountryTable[])session.getAttribute("ASIA");
	CountryTable[] africa = (CountryTable[])session.getAttribute("AFRICA");
	CountryTable[] europe = (CountryTable[])session.getAttribute("EUROPE");
	CountryTable[] n_america = (CountryTable[])session.getAttribute("N_AMERICA");
	CountryTable[] s_america = (CountryTable[])session.getAttribute("S_AMERICA");
	CountryTable[] oceania = (CountryTable[])session.getAttribute("OCEANIA");
%>

<%
	// 大陸名、国名、都市リストの取得(2回目以降)
	String countryName = (String)request.getAttribute("COUNTRY");
	String continentName =  (String)request.getAttribute("CONTINENT");
	List<City> cities = (List<City>)request.getAttribute("CITIES");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>cities_list.jsp</title>
	<!-- jQueryをCDNを使って読み込む -->
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>

<body>
<main class="container">
	<h2>国ごとの都市一覧表</h2>
	<form id="fm" action="<c:url value='/index' />" method="post">
		<%------------------大陸選択用selectBox ---------------%>
		<div id="search">
			<select name="selectBox1" id="continent-select">
				<option value="default" selected="selected" class="msg">大陸名を選択してください</option>
				<option value ="Asia" class="opt1">Asia</option>
				<option value ="Africa" class="opt2">Africa</option>
				<option value ="Europe" class="opt3">Europe</option>
				<option value ="North America" class="opt4">North America</option>
				<option value ="South America" class="opt5">South America</option>
				<option value ="Oceania" class="opt6">Oceania</option>
			</select>
			&nbsp;&nbsp;
			<%------------------国選択用selectBox ---------------%>
			<select name="selectBox2" id="country-select">
				<option value="default" selected="selected" class="msg">国名を選択してください</option>
				<% for (CountryTable c1 : asia) {	%>
					<option value =<%=c1.getCountry().getCode() %> class="opt1"><%=c1.getCountry().getName() %></option>
				<% } %>
				<% for (CountryTable c2 : africa) {	%>
					<option value =<%=c2.getCountry().getCode() %> class="opt2"><%=c2.getCountry().getName() %></option>
				<% } %>
				<% for (CountryTable c3 : europe) {	%>
					<option value =<%=c3.getCountry().getCode() %> class="opt3"><%=c3.getCountry().getName() %></option>
				<% } %>
				<% for (CountryTable c4 : n_america) {	%>
					<option value =<%=c4.getCountry().getCode() %> class="opt4"><%=c4.getCountry().getName() %></option>
				<% } %>
				<% for (CountryTable c5 : s_america) {	%>
					<option value =<%=c5.getCountry().getCode() %> class="opt5"><%=c5.getCountry().getName() %></option>
				<% } %>
				<% for (CountryTable c6 : oceania) {	%>
					<option value =<%=c6.getCountry().getCode() %> class="opt6"><%=c6.getCountry().getName() %></option>
				<% } %>
			</select>
		</div>
	</form>
	<br>

	<%
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		if(countryName == null || countryName.equals("")) { b1 = true; }
		if(continentName == null || continentName.equals("")) { b2 = true; }
		if(cities == null || cities.size() == 0) { b3 = true; }
	%>
		<% if(!b1 && !b2 && !b3) { %>
			<%-- 国別都市情報表示用table --%>
			<c:import url="_city_table.jsp" />
		<%}%>

	<br />
	<p><a href="<c:url value='/top_page.html' />">index</a></p>
	</main>
	<script src="<c:url value='/css/script2.js' />"></script>
</body>
</html>