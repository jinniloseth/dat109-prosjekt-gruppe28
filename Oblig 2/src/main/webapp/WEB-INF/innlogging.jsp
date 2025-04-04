<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Innlogging</title>
</head>
<body>
    <h2>Logg inn</h2>

    <form action="innlogging" method="post">
        <label for="brukernavn">Brukernavn:</label>
        <input type="text" id="brukernavn" name="brukernavn" required /><br><br>

        <label for="passord">Passord:</label>
        <input type="password" id="passord" name="passord" required /><br><br>

        <button type="submit">Logg inn</button>
    </form>
</body>
</html>
