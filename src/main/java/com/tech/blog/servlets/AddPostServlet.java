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

import com.tech.blog.DAO.PostDAO;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.Usermaster;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class AddPostServlet
 */
@MultipartConfig
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			int cid = Integer.parseInt(request.getParameter("cid"));
			String title = request.getParameter("title");
			String pContent = request.getParameter("pContent");
			String pCode = request.getParameter("pCode");
			Part part = request.getPart("pic");

			HttpSession session = request.getSession();

			Usermaster user = (Usermaster) session.getAttribute("currentUser");
			Post p = new Post(title, pContent, pCode, part.getSubmittedFileName(), null, cid, user.getId());
			PostDAO dao = new PostDAO(ConnectionProvider.getConnection());
			String path = null;
			if (dao.savePost(p)) {
				out.println("done");
//				path = "D:/TechBlog/TechBlog/blog_pics/" + part.getSubmittedFileName();
				path = request.getRealPath("/") + "blog_pics" + File.separator + part.getSubmittedFileName();

				File directory = new File(path);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				part.write(path);

			} else {
				out.println("error");
			}
		}
	}

}
