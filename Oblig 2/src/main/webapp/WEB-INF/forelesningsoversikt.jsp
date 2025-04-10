<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Forelesningsoversikt</title>
</head>
<body>
    <h2>Forelesninger i ${emne.emnekode} ${emne.semester} Resultat: ${resultat}</h2>
    <c:forEach var="f" items="${emne.forelesninger}">
        <div>
            <strong>${f.tittel}</strong><br/>
            Dato: ${f.dato}<br/>
            Forelesningsnr: ${f.forelesningsnr}
			Resultat: ${f.resultat}
        </div>
    </c:forEach>
    <a href="emneoversikt">Tilbake</a>
</body>
</html>
