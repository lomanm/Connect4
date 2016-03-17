<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="HangModelBean" class="hangman.HangmanModel" scope="session" />
<jsp:useBean id="HangControlBean" class="hangman.HangmanController" scope="session" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/HangmanStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signed Out</title>
</head>
<body>
	<%@include file="header.jsp"%>


		<div class=gamesContainer>

<%
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        %>

				<h5>You have been Signed Out</h5>
				<br>
				<h6>
					<a href="login.jsp">Login</a>
				</h6>

	</div>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>