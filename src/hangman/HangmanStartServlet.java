package hangman;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangmanStartServlet
 */
@WebServlet("/HangmanStartServlet")
public class HangmanStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HangmanStartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int wordList = Integer.parseInt(request.getParameter("wordSelect"));
		
		HangmanModel model = new HangmanModel();
		HangmanController control = new HangmanController();
		
		control.getwords(model);							// make word list from file

		switch(wordList){
		case 1:
			model.setList(model.easyWord);			// set to 12 or more chars long
			break;
		case 2:
			model.setList(model.medWord);			// set to 8 - 12 chars long
			break;
		case 3:
			model.setList(model.hardWord);			// set to 4 - 8 chars long
			break;
		case 4:
			model.setList(model.extraHardWord);		// set to less then 4 chars long
			break;
		case 5:
			model.setList(model.wordList);			// get random length
			break;
		}
		
		control.oneWord(model);
		model.setGameCounter(0);
		model.getGuessList().clear();
		control.printLetters(model);
		System.out.println(model.getWordPlay());
		
		request.getSession().setAttribute("HangModelBean", model);
		request.getSession().setAttribute("HangControlBean", control);
		request.getSession().setAttribute("hangman", 0);
		request.getSession().setAttribute("letterList", model.getLetterList());
		request.getSession().setAttribute("guessList", model.getGuessList());
		request.getSession().setAttribute("wordPlay", model.getWordPlay());
		request.getSession().setAttribute("quit", 1);
		request.getSession().setAttribute("game", 1);
		response.sendRedirect("Hangman.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
