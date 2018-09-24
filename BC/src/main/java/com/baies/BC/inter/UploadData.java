package com.baies.BC.inter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/UploadData")
public class UploadData extends HttpServlet {

	private static final long serialVersionUID = -8032216803782538143L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException(
					"Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}

		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		PrintWriter writer = response.getWriter();

		String newFileName = "";

//		System.out.println(new File(request.getServletContext().getRealPath("/") + "uploadedData/"));

		try {
			List<FileItem> items = uploadHandler.parseRequest(request);

			for (FileItem item : items) {
				if (!item.isFormField()) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
					Date date = new Date();
					StringTokenizer st = new StringTokenizer(item.getName());
					String pureName = st.nextToken(".");
					String format = st.nextToken(".");

//					newFileName = pureName + dateFormat.format(date) + "." + format;
					newFileName = pureName + "." + format;

					File file = new File(request.getServletContext().getRealPath("/") + "uploadedData/", newFileName);
					item.write(file);

					System.out.println("uploaded");
				}
			}
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

			writer.close();
		}

		// update new nodes information into the web
		write(response, newFileName);
	}

	private void write(HttpServletResponse response, String bn) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		response.getWriter().write(bn);
	}
}