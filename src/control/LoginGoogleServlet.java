package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Google;
import model.GoogleUtils;

/**
 * Servlet implementation class LoginGoogleServlet
 */
@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginGoogleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
			dis.forward(request, response);
		} else {
			String accessToken = GoogleUtils.getToken(code);
			Google google = GoogleUtils.getUserInfo(accessToken);
			request.setAttribute("id", google.getId());
			request.setAttribute("name", google.getName());
			request.setAttribute("email", google.getEmail());
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}