<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css.css">
<title>Vurderings Skjema</title>
</head>

<body>

    <h2>Vurder forelesningen!</h2>

    <form action="/vurderingskjema" method="post">
        <table>
            <tr>
                <td><img src="bilder/1.png" alt="Dårlig forelesning"></td>
                <td><img src="bilder/2.png" alt="Middels dårlig forelesning"></td>
                <td><img src="bilder/3.png" alt="Mid forelesning no cap"></td>
                <td><img src="bilder/4.png" alt="Middels bra forelesning"></td>
                <td><img src="bilder/5.png" alt="Veldig bra forelesning"></td>
            </tr>
        </table>
    </form>
</body>
</html>