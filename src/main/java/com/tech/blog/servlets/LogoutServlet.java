package com.tech.blog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.entities.Message;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		s.removeAttribute("currentUser");
		Message m = new Message("Logout SuccessFully", "success", "alert-success");
		s.setAttribute("msg", m);
		resp.sendRedirect("login.jsp");
	}

}
