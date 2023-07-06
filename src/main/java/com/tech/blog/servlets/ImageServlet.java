package com.tech.blog.servlets;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			StringBuffer imageName = request.getRequestURL();
			System.out.println(imageName.substring(38));
			String images = imageName.substring(38);
			File sourceimage = new File("d:\\techblog\\images\\" + images);
			Image image = ImageIO.read(sourceimage);
			out.println(imageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}