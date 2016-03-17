package register;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GetInfo reg = new GetInfo();

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		
		if(username == null || password1 == null || password2 == null){
			session.setAttribute("user", "nothingEntered");
			response.sendRedirect("register.jsp");
			return;
		}
		
		
		int value = reg.newRegister(password1, password2, username, email);

		switch(value){

		// user name is in DB,
		case 1  :
			session.setAttribute("user", "userInDb");
			response.sendRedirect("register.jsp");
			return;
			
		// user name not in DB, user name less then 4 characters
		case 2:
			session.setAttribute("user", "userTooSmall");
			response.sendRedirect("register.jsp");
			return;
		
		// user password to small
		case 3:
			session.setAttribute("user", "passTooSmall");
			response.sendRedirect("register.jsp");
			return;
			
		// user name not in DB, longer then 4, passwords match
		case 4:
			session.setAttribute("user", "passfail");
			response.sendRedirect("register.jsp");
			return;

		// all good, user registered
		case 5:
			session.setAttribute("user", "registered");
			response.sendRedirect("login.jsp");
			return;
			
		default:
			session.setAttribute("user", "error");
			response.sendRedirect("register.jsp");
			return;
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		doGet(request, response);
	}

}
