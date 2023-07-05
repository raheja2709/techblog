package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.DAO.UserDAO;
import com.tech.blog.entities.Usermaster;
import com.tech.blog.helper.ConnectionProvider;

@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {

			String check = request.getParameter("check");
			if (check == null) {
				out.println("box not checked");
			} else {
				String conAbout = "hey ! I am using techblog character varying";
				String name = request.getParameter("user_name");
				String email = request.getParameter("user_email");
				String password = request.getParameter("user_password");
				String gender = request.getParameter("gender");
				String about = request.getParameter("about");
				if (about == "" || about == null || about == " ") {
					about = conAbout;
				}
				// Create User Objects and set all data to that user object..

				Usermaster user = new Usermaster(name, email, password, gender, about);
				// create a UserDAO Object
				UserDAO dao = new UserDAO(ConnectionProvider.getConnection());
				if (dao.saveUser(user)) {
					out.println("done");
				} else {
					out.println("Error!");
				}
			}
		}
	}

}
