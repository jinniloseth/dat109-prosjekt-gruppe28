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
		
		<p>emnenr: ${emne.emnenr}</p>
		<p>forelesningsnr: ${forelesning.forelesningnr}</p>

		
	    <input type="hidden" name="student" value="${student.brukernavn}" />
		<input type="hidden" name="emne" value="${emne.emnenr}" />
	    <input type="hidden" name="forelesning" value="${forelesning.forelesningnr}" />

	    <table>
	        <tr>
				<c:forEach var="i" begin="1" end="5">
				    <td style="text-align: center;">
				        <label>
				            <img src="/bilder/${i}.png" alt="Vurdering ${i}" class="vurderingsbilde"><br/>
				            <input type="radio" name="vurdering" value="${i}" ${i == 1 ? "required" : ""}>
				        </label>
				    </td>
				</c:forEach>
	        </tr>
	    </table>
	    <br/>
	    <button type="submit">Send vurdering</button>
	</form>

</body>
</html>