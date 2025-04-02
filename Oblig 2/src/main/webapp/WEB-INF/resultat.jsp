<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="simple.css">
<title>Resultater</title>


</head>
<body>
	<form action="/resultat" method="get">
		Emne: ${emnekode}<br> 
		Forelesnings nr: ${foreslesningsnr}

		<c:forEach var="r" items="${resultat}">
			<tr>
				<td>${r.key}</td>
				<td>${r.value}</td>
			</tr>
		</c:forEach>

		Takk for din tilbakemelding!
		
	</form>
</body>
</html>