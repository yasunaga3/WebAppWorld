<!-- https://yuu.1000quu.com/jquery_hierselect -->
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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>countries_list.jsp</title>
	<!-- jQueryをCDNを使って読み込む -->
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<style type="text/css">
		.hidden { visibility: collapse; }
	</style>
</head>

<body>
<main class="container">
	<h2>世界の国一覧表</h2>
	<form action="" method="post">
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
		</div>
	</form>

	<%------------------ 国別情報表示用table ---------------%>
	<div id="country-data">
		<table id="country-table" class="table table-borderd">
			<thead>
				<tr class="active">
					<td class="hidden">Continent</td>
					<td>Name</td>
					<td>Region</td>
					<td>SurfaceArea</td>
					<td>Population</td>
					<td>Capital</td>
				</tr>
			</thead>
			<tbody>
			<%for(CountryTable c1 : asia) { %>
				<tr>
					<td class="hidden"><%=c1.getCountry().getContinent() %></td>
					<td><%=c1.getCountry().getName() %></td>
					<td><%=c1.getCountry().getRegion() %></td>
					<td><%=c1.getCountry().getSurfaceArea() %></td>
					<td><%=c1.getCountry().getPopulation() %></td>
					<td><%=c1.getCapital() %></td>
				</tr>
			<% } %>
			<%for(CountryTable c2 : africa) { %>
				<tr>
					<td class="hidden"><%=c2.getCountry().getContinent() %></td>
					<td><%=c2.getCountry().getName() %></td>
					<td><%=c2.getCountry().getRegion() %></td>
					<td><%=c2.getCountry().getSurfaceArea() %></td>
					<td><%=c2.getCountry().getPopulation() %></td>
					<td><%=c2.getCapital() %></td>
				</tr>
			<% } %>
			<%for(CountryTable c3 : europe) { %>
				<tr>
					<td class="hidden"><%=c3.getCountry().getContinent() %></td>
					<td><%=c3.getCountry().getName() %></td>
					<td><%=c3.getCountry().getRegion() %></td>
					<td><%=c3.getCountry().getSurfaceArea() %></td>
					<td><%=c3.getCountry().getPopulation() %></td>
					<td><%=c3.getCapital() %></td>
				</tr>
			<% } %>
			<%for(CountryTable c4 : n_america) { %>
				<tr>
					<td class="hidden"><%=c4.getCountry().getContinent() %></td>
					<td><%=c4.getCountry().getName() %></td>
					<td><%=c4.getCountry().getRegion() %></td>
					<td><%=c4.getCountry().getSurfaceArea() %></td>
					<td><%=c4.getCountry().getPopulation() %></td>
					<td><%=c4.getCapital() %></td>
				</tr>
			<% } %>
			<%for(CountryTable c5 : s_america) { %>
				<tr>
					<td class="hidden"><%=c5.getCountry().getContinent() %></td>
					<td><%=c5.getCountry().getName() %></td>
					<td><%=c5.getCountry().getRegion() %></td>
					<td><%=c5.getCountry().getSurfaceArea() %></td>
					<td><%=c5.getCountry().getPopulation() %></td>
					<td><%=c5.getCapital() %></td>
				</tr>
			<% } %>
			<%for(CountryTable c6 : oceania) { %>
				<tr>
					<td class="hidden"><%=c6.getCountry().getContinent() %></td>
					<td><%=c6.getCountry().getName() %></td>
					<td><%=c6.getCountry().getRegion() %></td>
					<td><%=c6.getCountry().getSurfaceArea() %></td>
					<td><%=c6.getCountry().getPopulation() %></td>
					<td><%=c6.getCapital() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>

	<br />
	asia.length=<%=asia.length %><br />
	africa.length=<%=africa.length %><br />
	europe.length=<%=europe.length %><br />
	n_america.length=<%=n_america.length %>	<br />
	s_america.length=<%=s_america.length %><br />
	oceania.length=<%=oceania.length %><br />


	&nbsp;&nbsp;
	<p><a href="<c:url value='/top_page.html' />">index</a></p>


<!-- 	<script>
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
		});
	});
	</script> -->
</main>
<script>
	jQuery(function($) {
		// セレクトボックスが変更されたら処理をする
		$('#continent-select').change(function() {
			// 選択した値を取得
			var select_val = $('#continent-select option:selected').val();
			console.log("select_val=" + select_val);
			// h2要素のノードリストを取得
			var obj = document.getElementsByTagName("h2");
			if (select_val == "") {
				obj[0].innerHTML = "地域ごとの国一覧表";
			} else {
				obj[0].innerHTML = select_val + "の国一覧表";
			}

			// tbodyのtr数回 処理をする
			$.each($("#country-table tbody tr"), function(index, element) {
				// 選択した値が空欄だったら、全ての行を表示する為の処理
				if (select_val == "") {
					$(element).css("display", "table-row");
					return true;
				}
				// 1行をテキストとして取り出し、セレクトボックスで選択した値があるかをチェック
				var row_text = $(element).text();
				if (row_text.indexOf(select_val) != -1) {
					// 見つかった場合は表示する
					$(element).css("display", "table-row");
				} else {
					// 見つからなかった場合は非表示に
					$(element).css("display", "none");
				}
			});
		});
	});
</script>

</body>
</html>