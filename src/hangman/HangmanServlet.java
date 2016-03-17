package hangman;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangmanServlet
 */
@WebServlet("/HangmanServlet")
public class HangmanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HangmanServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HangmanModel model = (HangmanModel) request.getSession().getAttribute("HangModelBean");
		HangmanController control = (HangmanController) request.getSession().getAttribute("HangControlBean");
		
		
		String guess = request.getParameter("guess").toUpperCase();
		
		model.setGuess(guess.charAt(0));
		model.getGuessList().add(guess.charAt(0));
		
		System.out.println(model.getGuess());
		
		//control.game(model);
		control.printLetters(model);
		control.guessChecker(model);
		
		switch(control.gameChecker(model)){
		case 1:
			request.getSession().setAttribute("quit", 1);		// game not over
			break;
		case 2:
			request.getSession().setAttribute("quit", 2);		// game over, word not guessed
			break;
		}
		if(model.getGameCounter() == 6){
			request.getSession().setAttribute("quit", 3);
		}
		
		request.getSession().setAttribute("hangman", model.getGameCounter());
		request.getSession().setAttribute("guessList", model.getGuessList());
		request.getSession().setAttribute("letterList", model.getLetterList());
		request.getSession().setAttribute("correctCount", model.getCorrectCount());
		
		
	
		response.sendRedirect("Hangman.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
