package com.tech.blog.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer ImageURL = request.getRequestURL();
		String imagename = ImageURL.substring(38);
		String path = "D:\\TechBlog\\images\\" + imagename;
		try (InputStream is = new FileInputStream(path); OutputStream os = response.getOutputStream()) {

			byte[] buffer = new byte[is.available()];
			int bytesRead;

			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
