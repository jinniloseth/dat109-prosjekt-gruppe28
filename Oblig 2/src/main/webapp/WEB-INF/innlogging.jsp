<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!--Kopiert fra et annet prosjekt, så kan inneholde feil-->
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="simple.css">
		<title>Innlogging</title>
	</head>
	<body>
		<h1>Innlogging</h1>
		<div class="input">
		<form action="/innlogging" method="post">
			<label for="type">Hva er du:</label>
			<select id="type" name="type">
				<option selected disabled hbrukernavnden>Ikke valgt</option>
				<option value="Student">Student</option>
				<option value="Lektor">Lektor</option>
			</select>
			<label for="brukernavn">Brukernavn:</label>
			<input type="text" id="brukernavn" name="brukernavn" value="${brukernavn}">
			
<!--			<label for="etternavn">Etternavn:</label>
			<input type="text" id="etternavn" name="etternavn" value="${deltager.etternavn}">
			<c:if test="${not empty bindingResult.getFieldError('etternavn')}">
				<p style="color: red;">${bindingResult.getFieldError('etternavn').defaultMessage}</p>
			</c:if><br> -->
			
			<input type="submit" value="Logg inn">
		</form>
		</div>
		
		<c:if test="${not empty errorMessage}">
			<p style="color: red;">${errorMessage}</p>
		</c:if>

		<br/>
		<a href="${pageContext.request.contextPath}/deltagerliste">Gå til deltagerlisten</a>
	</body>
</html>
