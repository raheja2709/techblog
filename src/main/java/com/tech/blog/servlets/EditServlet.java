package com.tech.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tech.blog.DAO.UserDAO;
import com.tech.blog.entities.Usermaster;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class EditServlet
 */
@MultipartConfig
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Edit Servlet</title>");
			out.println("</head>");
			out.println("<body>");

			// fetch all data
			String userEmail = request.getParameter("user_email");
			String userName = request.getParameter("user_name");
			String userPassword = request.getParameter("user_password");
			String userAbout = request.getParameter("user_about");
			Part part = request.getPart("image");
			String imageName = part.getSubmittedFileName();

			// get the user from the session
			HttpSession s = request.getSession();
			Usermaster user = (Usermaster) s.getAttribute("currentUser");
			user.setEmail(userEmail);
			user.setName(userName);
			user.setPassword(userPassword);
			user.setAbout(userAbout);
			user.setProfile(imageName);

			UserDAO userdao = new UserDAO(ConnectionProvider.getConnection());
			boolean ans = userdao.updateUser(user);
			System.out.println("ans  = " + ans);

			if (ans) {
				out.println("updated to db");

				String path = "D:/TechBlog/images";
				File directory = new File(path);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				String uniqueFileName = imageName;
				String filePath = path + File.separator + uniqueFileName;
				part.write(filePath);

			} else {
				out.println("not updated.....");
			}
			out.println("</body>");
			out.println("</html>");

		}
	}

}
