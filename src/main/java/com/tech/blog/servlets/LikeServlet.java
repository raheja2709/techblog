package com.tech.blog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.DAO.LikesDAO;
import com.tech.blog.helper.ConnectionProvider;

public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LikeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		if (operation.equals("like")) {
			LikesDAO ldao = new LikesDAO(ConnectionProvider.getConnection());
			boolean result = ldao.insertLike(uid, pid);

			if (result) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write("error");
			}
		} else if (operation.equals("removeLike")) {
			LikesDAO ldao = new LikesDAO(ConnectionProvider.getConnection());
			boolean result = ldao.deleteLike(pid, uid);
			if (result) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write("error");
			}
		}
	}
}
