<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Innlogging</title>
</head>
<body>
    <h2>Logg inn</h2>

    <form action="innlogging" method="post">
		
		<p>emnenr: ${emnenr}</p>
		<p>forelesningsnr: ${forelesningsnr}</p>
		
		<input type="hidden" name="emnenr" value="${emnenr}" />
		<input type="hidden" name="forelesningnr" value="${forelesningsnr}" />

        <label for="brukernavn">Brukernavn:</label>
        <input type="text" id="brukernavn" name="brukernavn" required /><br><br>
        <button type="submit">Logg inn</button>
    </form>
</body>
</html>

