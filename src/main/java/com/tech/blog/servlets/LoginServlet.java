package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.DAO.UserDAO;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.Usermaster;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {

			String userEmail = req.getParameter("email");
			String userPassword = req.getParameter("password");
			UserDAO dao = new UserDAO(ConnectionProvider.getConnection());
			Usermaster u = dao.getUserByEmailAndPassword(userEmail, userPassword);

			if (u == null) {
				Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
				HttpSession s = req.getSession();
				s.setAttribute("msg", msg);
				resp.sendRedirect("login.jsp");
			} else {
				HttpSession s = req.getSession();
				s.setAttribute("currentUser", u);

				resp.sendRedirect("profile.jsp");
			}

			out.println("<html>" + "<head>" + "<title>" + "Login Here" + "</title>" + "</head>" + "<body>" + "</body>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
