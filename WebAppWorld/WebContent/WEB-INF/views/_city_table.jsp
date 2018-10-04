<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>

<%
	// 大陸名、国名、都市リストの取得(2回目以降)
	String countryName = (String)request.getAttribute("COUNTRY");
	String continentName =  (String)request.getAttribute("CONTINENT");
	List<City> cities = (List<City>)request.getAttribute("CITIES");
%>

<h1>${ CONTINENT } : &nbsp; ${ COUNTRY }</h1>
cities.size()=<%=cities.size() %><br>

<%------------- 国別都市情報表示用table -----------%>
<div id="city-data">
	<table id="city-table" class="table table-borderd">
		<thead>
		<tr>
			<td>City Name</td>
			<td>District</td>
			<td>Population</td>
		</tr>
	</thead>
	<tbody>
	<%for(City city : cities) { %>
		<tr>
			<td><%=city.getName() %></td>
			<td><%=city.getDistrict() %></td>
			<td><%=city.getPopulation() %></td>
		</tr>
	<% } %>
	</tbody>
	</table>
</div>
