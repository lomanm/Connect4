package c4controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import c4businesslogic.C4Logic;
import c4model.C4Game;

/**
 * Servlet implementation class C4ServletController
 */
@WebServlet("/C4ServletController")
public class C4ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C4ServletController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GRAB THE SESSION GAME BEAN AND ASSIGN IT TO A NEW GAME OBJECT
		C4Game game = (C4Game) request.getSession().getAttribute("c4GameBean");

		// READ FORM INPUT IF THE HUMAN JUST PLAYED
		int columnChoice = 0;				// 1-7=human's move, 0=computer's move
		if (game.isHumansTurn()) {
			columnChoice = Integer.parseInt((String)request.getParameter("submitted"));
		}

		// PASS THE GAME STATE INTO THE BUSINESS LOGIC WHICH WILL PRODUCE AN UPDATED GAME STATE
		C4Logic logic = new C4Logic();
		game = logic.takeTurn(game,columnChoice);

		// UPDATE THE SESSION JAVA BEAN WITH THE NEW LOGIC THAT JUST TOOK PLACE
		request.getSession().setAttribute("c4GameBean",game);
		
		// PAINT THE UPDATED BOARD
		response.sendRedirect("connect4.jsp");
	}
}
