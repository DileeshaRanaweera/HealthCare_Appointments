package com.PAFProject.HealthCare;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class AppointmentAPI
 */
@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AppointmentDocRepository appDocRepo = new AppointmentDocRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output =  appDocRepo.createDocAppointment(request.getParameter("specialization"),
				request.getParameter("docName"),
				request.getParameter("hospital"),
				request.getParameter("date"),
				request.getParameter("patientNIC"),
				request.getParameter("patientName"),
				request.getParameter("patientAge"),
				request.getParameter("patientEmail"),
				request.getParameter("patientContact"));
		
		response.getWriter().write(output);
		
		//doGet(request, response);
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close(); 		
			
			String[] params = queryString.split("&"); 
			for(String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}catch(Exception e) {
			
		}
		return map;
	}
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = appDocRepo.updateDocAppointment(paras.get("specialization").toString(),paras.get("docName").toString(),paras.get("hospital").toString(),paras.get("date").toString(),paras.get("patientNIC").toString(),paras.get("patientName").toString(),paras.get("patientAge").toString(),paras.get("patientEmail").toString(),
				paras.get("patientContact").toString() ,paras.get("appDocId").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 Map paras = getParasMap(request);
		 
		 String output = appDocRepo.deleteDocAppointment(paras.get("appDocId").toString());
		 
		 response.getWriter().write(output);
		 
	 
	}

}
