package com.tech.blog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.DAO.LikesDAO;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class DisLikeServlet
 */
public class DisLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisLikeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		int pid = Integer.parseInt(request.getParameter("pid"));
		int uid = Integer.parseInt(request.getParameter("uid"));

//		if (operation.equals("liked")) {
//			LikesDAO ldao = new LikesDAO(ConnectionProvider.getConnection());
//			boolean result = ldao.isLikedByUser(pid, uid);
//
//			if (result) {
//				LikesDAO rdao = new LikesDAO(ConnectionProvider.getConnection());
//				boolean rs = rdao.deleteLike(pid, uid);
//				if(rs) {
//					response.getWriter().write("success");
//				}else {
//					response.getWriter().write("error");
//				}
//			} 
//		}
	}

}
