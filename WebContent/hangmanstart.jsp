<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/HangmanStyle.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lets get Started</title>
</head>
<body>
	<%@include file="header.jsp"%>


	<c:if
		test="${sessionScope.username == null || sessionScope.username == ''}">
				<div id=hangStartCon>
			To play, please<a href="login.jsp"> <br><br>log in.</a><br><br><br><br><br><br><br><br><br>
		</div>
		<br>
	</c:if>


	<div id="startpage">


		<c:if test="${sessionScope.username != null}">
			<div id="hangNotLoggedIn">

				<form action="HangmanStartServlet" method="POST" id="startform">
					<select name="wordSelect" id="options">
						<option value="1">Easy (12 letters or more)</option>
						<option value="2">Medium (8-12 letters long)</option>
						<option value="3">Hard (4-8 letters long)</option>
						<option value="4">Extra Hard (4 or less letters long)</option>
						<option value="5">Random (All words in list)</option>
					</select><br>

					<button type="submit" id="options">Play</button>

				</form>
			</div>
		</c:if>
	</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>