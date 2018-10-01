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

		<select name="selectBox1">
			<option value="default" selected="selected" class="msg">大陸名を選択してください</option>
			<option value ="opt1" class="opt1">Asia</option>
			<option value ="opt2" class="opt2">Africa</option>
			<option value ="opt3" class="opt3">Europe</option>
			<option value ="opt4" class="opt4">North America</option>
			<option value ="opt5" class="opt5">South America</option>
			<option value ="opt6" class="opt6">Oceania</option>
		</select>
		&nbsp;&nbsp;
		<select name="selectBox2">
			<option value="default" selected="selected" class="msg">国名を選択してください</option>
			<% for (String c1 : asia) {	%>
				<option value =c1 class="opt1"><%=c1 %></option>
			<% } %>
			<% for (String c2 : africa) {	%>
				<option value =c2 class="opt2"><%=c2 %></option>
			<% } %>
			<% for (String c3 : europe) {	%>
				<option value =c3 class="opt3"><%=c3 %></option>
			<% } %>
			<% for (String c4 : n_america) {	%>
				<option value =c4 class="opt4"><%=c4 %></option>
			<% } %>
			<% for (String c5 : s_america) {	%>
				<option value =c5 class="opt5"><%=c5 %></option>
			<% } %>
			<% for (String c6 : oceania) {	%>
				<option value =c6 class="opt6"><%=c6 %></option>
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
		$('select[name="selectBox1"]').change(function() {
			// 選択されている大陸のクラス名を取得
			var selectBox1Name = $('select[name="selectBox1"] option:selected').attr("class");
			console.log(selectBox1Name);
			// 国名の要素数を取得
			var count = $('select[name="selectBox2"]').children().length;
			// 国名の要素数分、for文で回す
			for (var i=0; i<count; i++) {
				var selectBox2 = $('select[name="selectBox2"] option:eq(' + i + ')');
				if(selectBox2.attr("class") === selectBox1Name) {
					// 選択した大陸と同じクラス名だった場合
					selectBox2.show();
				}else {
					// 選択した大陸とクラス名が違った場合
					if(selectBox2.attr("class") === "msg") {
						// 「国名を選択して下さい」という要素だった場合
							selectBox2.show();  //「国名を選択して下さい」を表示させる
							selectBox2.prop('selected',true);  //「国名を選択して下さい」を強制的に選択されている状態にする
					} else {
						// 「国名を選択して下さい」という要素でなかった場合
						selectBox2.hide();
					}
				}
			}
		})
	})
	</script>
</body>
</html>