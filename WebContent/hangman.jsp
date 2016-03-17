<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="HangModelBean" class="hangman.HangmanModel"
	scope="session" />
<jsp:useBean id="HangControlBean" class="hangman.HangmanController"
	scope="session" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/HangmanStyle.css">
<link rel="icon" type="image/png"	href="./images/SiteImages/favicon.ico">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hangman Game</title>
</head>
<body>
	<%@include file="header.jsp"%>



	<c:if test="${sessionScope.game =='0' || sessionScope.game == null}">
		<%@include file="hangmanstart.jsp"%>
	</c:if>

	<c:if test="${sessionScope.game =='1'}">
		<div class=Hangcontainer>
			<c:if
				test="${sessionScope.username == null || sessionScope.username == ''}">
 To play, please<a href="login.jsp"> log in.</a>
				<br>
			</c:if>

			<c:if test="${sessionScope.username != null}">
				<div id="hangNotLoggedIn">

					<br>

					<div id=heading>
						<h1>Hang the Man Game</h1>
					</div>
					<div class=content>
						Incorrect Guesses:
						<c:out value="${sessionScope.hangman}" />
						Correct Guesses:
						<c:out value="${sessionScope.correctCount}" />
						<br>
						<c:if test="${sessionScope.hangman == null}">
							<img src="./images/Hangman/mtPole.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='0'}">
							<img src="./images/Hangman/mtPole.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='1'}">
							<img src="./images/Hangman/head.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='2'}">
							<img src="./images/Hangman/body.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='3'}">
							<img src="./images/Hangman/rightArm.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='4'}">
							<img src="./images/Hangman/2Arm.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='5'}">
							<img src="./images/Hangman/1leg.png" id="hangPic">
						</c:if>
						<c:if test="${sessionScope.hangman =='6'}">
							<img src="./images/Hangman/dead.gif" id="hangPic">
						</c:if>
						<br>

						<c:if test="${sessionScope.quit == '1'}">
							<div class=contentFoot>
								Word:
								<c:out value="${sessionScope.letterList}" />
								<br> <br>
								<form action="HangmanServlet" method="POST">
									<input type=text name="guess" placeholder="Enter Guess"
										maxlength="1" REQUIRED>
									<button type="submit" class="logBtn" id="submit">Enter
										Guess</button>

								</form>
								<br>Guesses:<br>
								<c:out value="${sessionScope.guessList}" />
							</div>
							<br>
							<br>
							<a href="index.jsp">Quit?</a>
							<a href="hangmanstart.jsp">New Word</a>
							<br>
							<br>
						</c:if>


						<c:if test="${sessionScope.quit =='3'}">
				GAME OVER.<br>
							<br>
				The Word was: <c:out value="${sessionScope.wordPlay}" />
							<br>
				Would you like to play again?<br>
							<br>
							<a href="hangmanstart.jsp">Yes</a>
							<a href="index.jsp">No</a>
						</c:if>

						<c:if test="${sessionScope.quit =='2'}">
				GAME OVER.<br>
				Congratulations you guess the word and save a man from hanging.
				Would you like to play again?<br>
							<a href="hangmanstart.jsp">Yes</a>
							<a href="index.jsp">No</a>
						</c:if>
						<br>
					</div>
			</c:if>
		</div>

	</c:if>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>