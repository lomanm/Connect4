<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/connect4.css">
<title>Connect 4</title>
</head>
<body id="introBody">
<form method="post" action="c4begin">
<TABLE id="c4OptionTable">
<tr><td colspan=3><h1>Connect 4</h1></td></tr>
<tr><td colspan=3><h3>Options:</h3></td></tr>
<TR>
<TD>Choose your color:</TD>  
<TD><input type="radio" name="color" value="R" checked> red</TD>
<TD><input type="radio" name="color" value="Y"> yellow</TD>
</TR>
<TR>
<TD>Choose 1st or 2nd move:</TD>
<TD><input type="radio" name="turn" value="1" checked> 1st</TD>
<TD><input type="radio" name="turn" value="2"> 2nd</TD>
</TR>
<TR>
<TD>Choose difficulty level:</TD>
<TD><input type="radio" name="level" value="1"> easy</TD>
<TD><input type="radio" name="level" value="2" checked> hard</TD>
</TR>
<TR>
<TD>Where do you want to play?</TD>
<TD colspan=2><select name="where" class="c4where">
 <option value="1" selected>at home</option>
 <option value="2">in the park</option>
 <option value="3">at the beach</option>
 </select>
</TD></TR>
<TR><TD colspan=3><input type="submit" value="Play" class="c4ColumnButton"></TD>
</TABLE></form></body></html>