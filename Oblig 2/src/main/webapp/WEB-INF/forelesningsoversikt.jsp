<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Forelesningsoversikt</title>
</head>
<body>
    <h2>Forelesninger i ${emne.navn} (${emne.emnekode})</h2>
    <c:forEach var="f" items="${forelesninger}">
        <div>
            <strong>${f.tittel}</strong><br/>
            Dato: ${f.dato}<br/>
            Forelesningsnr: ${f.forelesningsnr}
        </div>
        <hr/>
    </c:forEach>
    <a href="oversikt?brukernavn=${emne.lektorer[0].brukernavn}">Tilbake</a>
</body>
</html>
