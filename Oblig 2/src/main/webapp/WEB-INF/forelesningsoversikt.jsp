<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <title>Oversikt</title>
    <link rel="stylesheet" href="simple.css">
</head>
<body>
    <h2>Fagene dine:</h2>
    <table>
        <tr>
            <th>Emnekode</th>
			<th>Forelesningsnr</th>
        </tr>	
        <c:forEach var="e" items="${emner}">
            <tr>
                <td onclick="window.location.href="${pageContext.request.contextPath}/';" style="cursor: pointer;">${e.emnekode}</td>
                <td>${e.navn}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
<!--Ble ikke ferdig her-->