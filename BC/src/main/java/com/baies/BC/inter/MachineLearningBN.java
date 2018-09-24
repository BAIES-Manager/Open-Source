package com.baies.BC.inter;
 
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.baies.BC.learner.SBL_parameter_learner;

 
/**
 * Servlet implementation class UpdateUsername
 */
@WebServlet("/MachineLearningBN")
public class MachineLearningBN extends HttpServlet { 
          
	private static final long serialVersionUID = -296828517902171389L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MachineLearningBN() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}
	 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		//get json data from the web
		String data = request.getParameter("BNinfoAndData");
		 
		if (data == null || data.isEmpty())
			return;
		
		JSONObject obj = new JSONObject(data);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"); 
		String date = dateFormat.format(new Date());
		
		String script = java.net.URLDecoder.decode(obj.getString("script"), "UTF-8");
		String uploaded_file_name = java.net.URLDecoder.decode(obj.getString("uploaded_file_name"), "UTF-8");
		String dataPath = request.getServletContext().getRealPath("/") + "uploadedData/";
		String learnedSBN = new SBL_parameter_learner().run("myNet", script, dataPath + uploaded_file_name); 
		 
//		System.out.println(learnedSBN);
		 
		write(response, learnedSBN);
	}
	  
	private void write(HttpServletResponse response, String bn) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain"); 
		response.getWriter().write(bn); 
	}  
}
