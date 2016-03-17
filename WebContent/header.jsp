<head>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><img src="./images/SiteImages/claimgamesLogo.png" id="HeaderLogo"></a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Home</a></li>
					<li><a href="members.jsp">View Members</a></li>
					<li><a href="games.jsp">Games</a></li>
					<li><a href="profile.jsp">Profile</a></li>
				</ul>

				
				<%
					if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
				%>
				<form action="LoginServlet" method="post">
					<div class="inputgroup">
						<div id=submitUser>
							<button type="submit" class="btn btn-login" id="submit">Login</button>
						</div>
						<div class="input-group input-group-sm">
							<input type="text" class="form-control" name="username"
								placeholder="Username:" aria-describedby="sizing-addon3">
						</div>

						<div class="input-group input-group-sm">
							<input type="password" class="form-control" name="password"
								placeholder="Password:" aria-describedby="sizing-addon3"
								id="loginbtn"><br> <a href="register.jsp">
								Register Here</a>
						</div>

					</div>

				</form>
				<%
					} else {
				%>

				<div class="inputgroup">
					<div class="userLogin">
						Welcome :
						<%=session.getAttribute("username")%><br> <a
							href="signout.jsp" id=signout>Sign Out</a>
						<%
							}
						%>
					</div>
				</div>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	
	
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>