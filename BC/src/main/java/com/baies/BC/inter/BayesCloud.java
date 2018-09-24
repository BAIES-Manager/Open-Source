package com.baies.BC.inter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
@WebServlet("/BayesCloud")
public class BayesCloud extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map <String, Object> map = new HashMap<String, Object>();
		boolean isValid = false;
		String username = request.getParameter("username");
		if(username != null && username.trim().length() != 0){
			map.put("username", username);
			isValid = true;
		}
		
		map.put("isValid", isValid);
		  
		response.setContentType("text/html");
		if (request.getParameter("name").equals("java")){
			response.getWriter().println(
						"<topic>" +
						"<name> Java Lang </name>" +
						"<tutorial> Java Lang </tutorial>" +
						"</topic>"
						);
			
		} else 	if (request.getParameter("name").equals("oodp")){
			response.getWriter().println(
					"<topic>" +
					"<name> oodp Lang </name>" +
					"<tutorial> oodp Lang </tutorial>" +
					"</topic>"
					);
		} else {
			response.getWriter().println(
					"<topic>" +
					"<name> else Lang </name>" +
					"<tutorial> else Lang </tutorial>" +
					"<tutorial> oelseodp Lang </tutorial>" +
					"<tutorial> else Lang </tutorial>" +
					"<tutorial> else Lang </tutorial>" +
					"</topic>"
					);
			
		}
		
		//doGet(request, response);
	}

}
