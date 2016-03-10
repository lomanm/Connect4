<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="gameBean" class="c4model.C4Game" scope="session"/>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="connect4.css">
<title>Connect 4</title>


<c:if test="${c4Active==null}">
<script type="text/javascript">
window.location = "connect4.html"
</script>
</c:if>


<c:if test="${!gameBean.isHumansTurn() && !gameBean.isGameOver()}">
	<script type="text/javascript">
	function initialize() {
        setTimeout('submitForm()', 750);
    }
    function submitForm() {
        document.forms["c4form"].submit();
    }
</script>
</c:if>


</head>

<body onload="initialize()">
<FORM name="c4form" method="post" action="c4play">
<TABLE id="connect4Outer">
<TR><TD colspan="7">
<c:choose>
	<c:when test="${gameBean.isGameOver() && gameBean.getWinner() == 1}">
		<h2>GAME OVER - YOU WIN!</h2>
	</c:when>
	<c:when test="${gameBean.isGameOver() && gameBean.getWinner() == 2}">
		<h2>GAME OVER - YOU LOSE!</h2>
	</c:when>
	<c:when test="${gameBean.isGameOver() && gameBean.getWinner() == 3}">
		<h2>GAME OVER - DRAW!</h2>
	</c:when>
	<c:when test="${gameBean.isHumansTurn()}">
		<h2>YOUR TURN</h2>
	</c:when>
	<c:otherwise>
		<h2>COMPUTER'S TURN: thinking...</h2>
	</c:otherwise>
</c:choose>
</TD></TR>
<TR><TD colspan="7">


<TABLE id="connect4">

<c:forEach begin="0" end="5" var="i">
	<c:set var="row" value="${5-i}"/>
	<TR>
	<c:forEach begin="0" end="6" var="col">
		<TD>
		<c:choose>
			<c:when test="${gameBean.getBoard().getSlot(row,col) eq ' ' && (!gameBean.isGameOver() || gameBean.getWinner()==3)}">
       			<img src="images/white.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.getBoard().getSlot(row,col) eq 'R' && (!gameBean.isGameOver() || gameBean.getWinner()==3)}">
       			<img src="images/red.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.getBoard().getSlot(row,col) eq 'Y' && (!gameBean.isGameOver() || gameBean.getWinner()==3)}">
       			<img src="images/yellow.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.isGameOver() && gameBean.getBoard().getSlot(row,col) eq ' '}">
       			<img src="images/white2.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.isGameOver() && gameBean.getBoard().getSlot(row,col) eq 'R' && !gameBean.getBoard().isWinningSlot(row,col)}">
       			<img src="images/red2.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.isGameOver() && gameBean.getBoard().getSlot(row,col) eq 'Y' && !gameBean.getBoard().isWinningSlot(row,col)}">
       			<img src="images/yellow2.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.isGameOver() && gameBean.getBoard().getSlot(row,col) eq 'R' && gameBean.getBoard().isWinningSlot(row,col)}">
       			<img src="images/red3.png" width="65" height="65">
    		</c:when>
    		<c:when test="${gameBean.isGameOver() && gameBean.getBoard().getSlot(row,col) eq 'Y' && gameBean.getBoard().isWinningSlot(row,col)}">
       			<img src="images/yellow3.png" width="65" height="65">
    		</c:when>
		</c:choose>
    	</TD>
	</c:forEach> 
	</TR>
</c:forEach>

</TABLE>
</TD></TR>
<TR><TD>

<TR>


<c:choose>
	<c:when test="${gameBean.isGameOver()}">
		<TD height="32" colspan="7">
		<a href="connect4.html" class="c4LinkButton">Play Again</a>
		<a href="connect4.html" class="c4LinkButton">Claim Games Main Menu</a>
		</TD>
	</c:when>
	<c:when test="${gameBean.isHumansTurn()}">
		<TD><input type="submit" name="submitted" value="1" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,0) ne ' '}">disabled</c:if>></TD>
		<TD><input type="submit" name="submitted" value="2" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,1) ne ' '}">disabled</c:if>></TD>
		<TD><input type="submit" name="submitted" value="3" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,2) ne ' '}">disabled</c:if>></TD>
		<TD><input type="submit" name="submitted" value="4" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,3) ne ' '}">disabled</c:if>></TD>
		<TD><input type="submit" name="submitted" value="5" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,4) ne ' '}">disabled</c:if>></TD>
		<TD><input type="submit" name="submitted" value="6" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,5) ne ' '}">disabled</c:if>></TD>
		<TD><input type="submit" name="submitted" value="7" class="c4ColumnButton" <c:if test="${gameBean.getBoard().getSlot(5,6) ne ' '}">disabled</c:if>></TD>
	</c:when>
	<c:otherwise>
		<TD colspan="7">
		<input type="submit" name="submitted" value="thinking..." class="c4ComputerButton">
		</TD>
	</c:otherwise>
</c:choose>
</TR>
</TABLE>
</FORM>
</body>
</html>