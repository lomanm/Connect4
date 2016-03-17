<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Register</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="css/HangmanStyle.css">

</head>
<body>
<body>

  <%@include file="header.jsp"%> 
  
  
   <div class="regForm">


    <!-- Put your page content here! -->
 
        <form action="RegisterServlet" method="post" id="regText">
    Name:<br> <input type="text" name="username"  id="regNameIn" pattern="[^'\x22]+" title="Invalid input" required ><br>
    Email:<br>  <input type="email" name="email" id="regEmailIn" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required><br>
    Password:<br> <input type="password" name="password1" id="regPass1In" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required> <br>
    Re-enter Password:<br> <input type="password" name="password2" id="regPass2In"pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
        
<!--         user in Db -->
      <% if(session.getAttribute("user") == "userInDb") { %>
	 <div id="fail"> Username Unavailable </div>
<!-- 	 user to short -->
	 <% } else if(session.getAttribute("user")  == "userTooSmall") { %>
	 	<div id="fail"> Username less then 4 Characters </div>
<!-- 	 	password to short -->
	  <% } else if(session.getAttribute("user")  == "passTooSmall") { %>
	 	<div id="fail"> Password less then 2 Characters </div>
<!-- 	 password didn't match -->
	 <% } else if(session.getAttribute("user")  == "passfail") { %>
	 <div id="fail"> Password mismatched or not entered </div> 
<!-- 	 nothing entered -->
	 <% } else if(session.getAttribute("user")  == "nothingEntered") { %>
	 <div id="fail">You didn't enter anything. <br></div> 
	 <!-- 	 error -->
	 <% } else if(session.getAttribute("user")  == "error") { %>
	 <div id="fail">Something went wrong, contact Admin. <br></div> 
<!-- 	 user registered -->
	 <% } else if(session.getAttribute("user")  == "registered") { %>
	 <div id="fail"> User Registered<a href="login.jsp"> Login In </a> </div> <% } %>
	 <br>
            <input type="submit" name="submit" value="Sign-up" id="regBtn">
        </form>
        
    </div>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>
</body>
</html>