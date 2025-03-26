<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="simple.css">
<title>Logg inn</title>
</head>
<body>
	<h2>Logg inn</h2>

	<fieldset style="width: 50px">
		<c:if test="${not empty mismatchError}">
			<p style="color: red;">${mismatchError}</p>
		</c:if>


		<form action="/innlogging" method="post" id="deltager">
			<c:if test="${not empty loggetUt}">
				<p style="color: red;">${loggetUt}</p>
			</c:if>
		
			Mobil<br> <input type="text" name="mobil" pattern="^\d{8}$"
				required title="Mobilnummeret må bestå av 8 siffre"><br>

			Passord <input type="password" name="passord" id="passord" required>
			<br> <br> <input type="submit" value="Logg inn" />

		</form>

		
	</fieldset>
</body>
</html>