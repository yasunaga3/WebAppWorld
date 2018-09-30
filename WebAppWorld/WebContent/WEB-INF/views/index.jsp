<!-- https://yuu.1000quu.com/jquery_hierselect -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%
	// 大陸ごとの国名配列の取得
	String[] asia = (String[])request.getAttribute("ASIA");
	String[] africa = (String[])request.getAttribute("AFRICA");
	String[] europe = (String[])request.getAttribute("EUROPE");
	String[] n_america = (String[])request.getAttribute("N_AMERICA");
	String[] s_america = (String[])request.getAttribute("S_AMERICA");
	String[] oceania = (String[])request.getAttribute("OCEANIA");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>index.jsp</title>
	<!-- jQueryをCDNを使って読み込む -->
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
</head>

<body>
	<form action="" method="post">

		<select name="continent">
			<option value="Japan" selected="selected" class="msg">大陸名を選択してください</option>
			<option value ="Asia" class="Asia">Asia</option>
			<option value ="Africa" class="Africa">Africa</option>
			<option value ="Europe" class="Europe">Europe</option>
			<option value ="NorthAmerica" class="NorthAmerica">North America</option>
			<option value ="SouthAmerica" class="SouthAmerica">South America</option>
			<option value ="Oceania" class="Oceania">Oceania</option>
		</select>
		&nbsp;&nbsp;
		<select name="country">
			<option value="Japan" selected="selected" class="msg">国名を選択してください</option>
			<% for (String c1 : asia) {	%>
				<option value =c1 class="Asia"><%=c1 %></option>
			<% } %>

			<% for (String c2 : africa) {	%>
				<option value =c2 class="Africa"><%=c2 %></option>
			<% } %>

			<% for (String c3 : europe) {	%>
				<option value =c3 class="Europe"><%=c3 %></option>
			<% } %>

			<% for (String c4 : n_america) {	%>
				<option value =c4 class="NorthAmerica"><%=c4 %></option>
			<% } %>

			<% for (String c5 : s_america) {	%>
				<option value =c5 class="SouthAmerica"><%=c5 %></option>
			<% } %>

			<% for (String c6 : oceania) {	%>
				<option value =c6 class="Oceania"><%=c6 %></option>
			<% } %>

		</select>

	</form>

	<br />
	asia.length=<%=asia.length %><br />
	africa.length=<%=africa.length %><br />
	europe.length=<%=europe.length %><br />
	n_america.length=<%=n_america.length %>	<br />
	s_america.length=<%=s_america.length %><br />
	oceania.length=<%=oceania.length %><br />

	<p><a href="<c:url value='/index' />">index</a></p>

	<script>
	$(function() {
		// 大陸名が変更されたら発動
		$('select[name="continent"]').change(function() {
			// 選択されている大陸のクラス名を取得
			var continentName = $('select[name="continent"] option:selected').attr("class");
			console.log(continentName);
			// 国名の要素数を取得
			var count = $('select[name="country"]').children().length;
			// 国名の要素数分、for文で回す
			for (var i=0; i<count; i++) {
				var country = $('select[name="country"] option:eq(' + i + ')');
				if(country.attr("class") === continentName) {
					// 選択した大陸と同じクラス名だった場合
					country.show();
				}else {
					// 選択した大陸とクラス名が違った場合
					if(country.attr("class") === "msg") {
						// 「国名を選択して下さい」という要素だった場合
							country.show();  //「国名を選択して下さい」を表示させる
							country.prop('selected',true);  //「国名を選択して下さい」を強制的に選択されている状態にする
					} else {
						// 「国名を選択して下さい」という要素でなかった場合
						country.hide();
					}
				}
			}
		})
	})
	</script>

</body>
</html>