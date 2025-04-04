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
				<c:forEach var="i" begin="1" end="5">
				    <td>
				        <label>
				            <input type="radio" name="vurdering" value="${i}" style="display:none;" onchange="this.form.submit()">
				        	<img src="bilder/${i}.png" alt="Vurdering ${i}" style="cursor:pointer;">
				    	</label>
					</td>
				</c:forEach>
            </tr>
        </table>
    </form>
</body>
</html>