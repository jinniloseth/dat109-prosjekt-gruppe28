<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Emneoversikt</title>
    <link rel="stylesheet" href="/css/css.css" />
</head>
<body>
    <h1>Dine emner</h1>
    <div class="modul-container">
        <c:forEach var="emne" items="${emner}">
            <a href="forelesninger?emnekode=${emne.emnekode}&semester=${emne.semester}" style="text-decoration: none;">
                <div class="modul">
                    <div class="modul-header"></div>
                    <div class="modul-innhold">
                        <div class="fagnavn">${emne.navn}</div>
                        <div class="kode">${emne.emnekode}</div>
                        <div class="semester">${emne.semester}</div>
                        <div class="ikoner">
                            <div class="ikon"></div>
                            <div class="ikon"></div>
                            <div class="ikon"></div>
                        </div>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</body>
</html>
