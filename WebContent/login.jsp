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
<title>Login</title>
</head>
<body>
	<%@include file="header.jsp"%>


		<div class=gamesContainer>
<form action="LoginServlet" method="post">
		<div class="inputgroup1">
			<div id=submitUser>
				<div class="logForm">
					<div id="logText">
						<div id="inputText">
<%  session.removeAttribute("user");  %>
							
							<%
								if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
							%>
							Username: <br> <input type="text" name="username"
								id="usernameIn" aria-describedby="sizing-addon3"><br>

							Password: <br> <input type="password" name="password"
								id="passIn" aria-describedby="sizing-addon3"> <br>
						</div>
						<!-- 						password fail -->
						<%
							if (session.getAttribute("login") == "passFail") {
						%>
						<div id="fail">Incorrect Password</div>
						<!-- 							user not found -->
						<%
							} else if (session.getAttribute("login") == "noUser") {
						%>
						<div id="fail">User not Found</div>
						<a href="register.jsp"> Register New User </a> <br>
						<%
							}
						%>


						<p>Please log in:</p>
						<button type="submit" class="logBtn" id="submit">Login</button>
						<br> <a href="register.jsp" id="reg">Register Here</a>
						<div id="fail">
							<a href="getpassword.jsp">Forgot password?</a>
						</div>
						<%
							} else {
						%>

						<p>You are logged In:</p>
						<%
							}
						%>
					</div>

				</div>
			</div>
		</div>

	</form>

	</div>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>