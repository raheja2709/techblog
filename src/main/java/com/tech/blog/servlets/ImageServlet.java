package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ImageServlet
 */

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {

			// String path = "D:/TechBlog/images/" + imageName;
			StringBuffer imageName = request.getRequestURL();
			System.out.println(imageName.substring(38));
			System.out.println("imagename = " + imageName);
			out.println(imageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}