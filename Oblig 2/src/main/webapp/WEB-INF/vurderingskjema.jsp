<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="simple.css">
<title>Vurderings Skjema</title>
</head>
<body>

<!--  
	<c:if test="${not empty username}">
		<c:forEach var="d" items="${deltagerListe}">
			<c:if test="${d.mobil == username}">
				<p>Innlogget bruker: ${d.fornavn}&nbsp;${d.etternavn} /
					${username}</p>
			</c:if>
		</c:forEach>
	</c:if>

	<h2>Deltagerliste</h2>
	<table>
		<tr>
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>

		<c:forEach var="d" items="${deltagerListe}">
			<tr style="${d.mobil == username ? 'background-color: lightgreen;' : ''}">
				<td align="left">${d.kjonn}</td>
				<td align="left">${d.fornavn}&nbsp;${d.etternavn}</td>
				<td>${d.mobil}</td>
			</tr>
		</c:forEach>


	</table>
-->

	<form action="/vurderingskjema" method="get">
		Emnekode: ${emnekode} <br>
		Forelesning nr: ${forelesningsnr}
	</form>
	
	
</body>
</html>