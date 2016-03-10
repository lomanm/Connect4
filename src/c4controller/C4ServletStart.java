package c4controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import c4model.C4Game;

/**
 * Servlet implementation class C4ServletStart
 */
@WebServlet("/C4ServletStart")
public class C4ServletStart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public C4ServletStart() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// READ IN FORM VARIABLES
		String humanColor = (String)request.getParameter("color");
		int humanMove = Integer.parseInt((String)request.getParameter("turn"));
		int difficulty = Integer.parseInt((String)request.getParameter("level"));
			
		// INSTANTIATE A GAME (AND SET GAME OPTIONS THROUGH CONSTRUCTOR)
		C4Game game = new C4Game(humanColor, humanMove, difficulty);
		
		// CREATE A JAVA BEAN FROM OUR GAME OBJECT
		request.getSession().setAttribute("gameBean",game);
		request.getSession().setAttribute("c4Active","YES");
		
		// DISPLAY INITIAL BOARD
		response.sendRedirect("connect4.jsp");
	}

}
