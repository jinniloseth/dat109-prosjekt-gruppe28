<!DOCTYPE html>
<html lang="no">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="simple.css">
<title>Påmeldingsbekreftelse</title>
</head>
<body>
	<fieldset style="width: 50px">
		<c:if test="${not empty username}">
			<p>Innlogget bruker: ${person.fornavn}&nbsp;${person.etternavn} / ${username}</p>
		</c:if>

		<h2>Påmeldingsbekreftelse</h2>
		<p>Påmeldingen er mottatt for</p>
		<p>
			&nbsp;&nbsp;${person.fornavn}<br />
			&nbsp;&nbsp;&nbsp;${person.etternavn}<br />
			&nbsp;&nbsp;&nbsp;${person.mobil}<br />
			&nbsp;&nbsp;&nbsp;${person.kjonn}<br />
		</p>

		<a href="deltagerliste">Gå til deltagerliste</a>
	</fieldset>
</body>
</html>
