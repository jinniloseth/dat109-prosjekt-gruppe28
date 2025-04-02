<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <title>Ny Forelesning</title>
    <link rel="stylesheet" href="simple.css">
</head>
<body>
    Oprett ny forelesning
    <form>
        <label>Emnekode</label><br />
        <input type="text" name="emnekode" /><br />
        <label>Dato</labl><br />
        <input type="text" name="dato"/> <br />
        <input type="submit" onClick="opprettForelesning(emnekode, dato)"/>
        </from>
        
       
        
    
    
    </body>
    
    
    
    
    