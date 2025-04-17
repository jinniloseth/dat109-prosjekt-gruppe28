<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Emneoversikt</title>
    <link rel="stylesheet" href="/css/css.css" />
</head>
<body>
    <h1>Dine emner - ${lektor.brukernavn}</h1>
    <div class="modul-container">
		<c:forEach var="emne" items="${emner}">
		            <h2>${emne.navn} ( ${emne.emnekode} - ${emne.semester} )</h2>

		            <table border="1" style="margin-bottom: 20px;">
		                <thead>
		                    <tr>
		                        <th>Forelesningsnr</th>
		                        <th>Dato</th>
		                        <th>Tittel</th>
		                        <th>Resultat</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <c:forEach var="f" items="${emne.forelesninger}">
		                        <tr>
		                            <td>${f.forelesningnr}</td>
		                            <td>${f.dato}</td>
		                            <td>${f.tittel}</td>
		                            <td>${f.resultat}</td>
		                        </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </c:forEach>
    </div>
</body>
</html>
