package com.PAFProject.HealthCare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class AppointmentDocRepository {

Connection con = null;
	
	public AppointmentDocRepository(){
		
		String url = "jdbc:mysql://localhost:3306/appointmentdb?serverTimezone=UTC";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public String getDocAppointments(){
		
		String output = "";
		
		//List<AppointmentDoc> appointDocList = new ArrayList<>();
		
		// Prepare the html table to be displayed 
		output = "<table border='1' table width ='100%'><tr>"
				
				+ "<th>Specialization</th>"
				+ "<th>Doctor Name</th>"   
				+ "<th>Hospital</th>"
				+ "<th>Date</th>"
				+ "<th>Patient NIC</th>"
				+ "<th>Patient Name</th>"
				+ "<th>Patient Age</th>"
				+ "<th>Patient Email</th>"
				+ "<th>Patient Contact Number</th>"
				+ "<th>Update</th>"
				+ "<th>Remove</th></tr>"; 
		
		
		String sql = "select * from appointmentdoc";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				
				//AppointmentDoc appDoc = new AppointmentDoc();
				String appDocId = Integer.toString(rs.getInt("appDocId"));
				String specialization = rs.getString("specialization");
				String docName = rs.getString("docName");
				String hospital = rs.getString("hospital");
				String date = rs.getString("date");
				String patientNIC = rs.getString("patientNIC");
				String patientName = rs.getString("patientName");
				String patientAge = Integer.toString(rs.getInt("patientAge"));
				String patientEmail = rs.getString("patientEmail");
				String patientContact = Integer.toString(rs.getInt("patientContact"));
				
				//appointDocList.add(appDoc);
				
				
				// Add into the html table 
				output += "<tr><td><input id='appDocId'  name='appDocId'   type='hidden' value='" + appDocId    + "'>"+
				specialization + "</td>";  
				output += "<td>" + docName + "</td>";
				output += "<td>" + hospital + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + patientNIC+ "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + patientAge + "</td>";
				output += "<td>" + patientEmail + "</td>";
				output += "<td>" + patientContact + "</td>";
				
				
				// buttons 
				output += "<td><input name='btnUpdate'     type='button' value='Update'   class='btnUpdate btn btn-secondary'></td>" 
				+ "<td><input name='btnRemove' type='button' value='Remove'   class='btnRemove btn btn-danger'    data-appdocid='"  
						+ appDocId + "'>" + "</td></tr>";
			}
			
			// Complete the html table
			output += "</table>"; 
			
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		//return appointDocList;
		return output;
		
	}
	
	
//	public AppointmentDoc getDocAppointment(int appDocId) {
//		
//		String sql = "select * from appointmentdoc where appDocId="+appDocId;
//		AppointmentDoc appDoc = new AppointmentDoc();
//		
//		try {
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			if(rs.next()) {
//				
//				
//				appDoc.setAppDocId(rs.getInt(1));
//				appDoc.setSpecialization(rs.getString(2));
//				appDoc.setDocName(rs.getString(3));
//				appDoc.setHospital(rs.getString(4));
//				appDoc.setDate(rs.getString(5));
//				appDoc.setPatientNIC(rs.getString(6));
//				appDoc.setPatientName(rs.getString(7));
//				appDoc.setPatientAge(rs.getInt(8));
//				appDoc.setPatientEmail(rs.getString(9));
//				appDoc.setPatientContact(rs.getInt(10));
//				
//				
//			}
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		return appDoc;
//		
//	}
	
	
	public String createDocAppointment(String specialization,String docName,String hospital,String date,String patientNIC,String patientName,String patientAge,String patientEmail,String patientContact ) {
		
		String output = "";
		String sql = "insert into appointmentdoc values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,0);
			st.setString(2,specialization);
			st.setString(3,docName);
			st.setString(4,hospital);
			st.setString(5,date);
			st.setString(6,patientNIC);
			st.setString(7,patientName);
			st.setInt(8, Integer.parseInt(patientAge));
			st.setString(9,patientEmail);
			st.setInt(10, Integer.parseInt(patientContact));
			
			
			st.executeUpdate();
			
			String newDocAppointment = getDocAppointments();	
			
			output = "{\"status\":\"success\", \"data\": \"" + 
					newDocAppointment+ "\"}";
			
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\":"
					+ "         \"Error while inserting the item.\"}";
			System.out.println(e);
		}
		
		return output;
		
	}
	
	
	public String updateDocAppointment(String specialization,String docName,String hospital,String date,String patientNIC,String patientName,String patientAge,String patientEmail,String patientContact,String appDocId) {
		
		String output = "";
		
		String sql = "update appointmentdoc set specialization=?, docName=?, hospital=?, date=?, patientNIC=?, patientName=?, patientAge=?, patientEmail=?, patientContact=? where appDocId=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, specialization.toString());
			st.setString(2, docName.toString());
			st.setString(3, hospital.toString());
			st.setString(4, date.toString());
			st.setString(5, patientNIC.toString());
			st.setString(6, patientName.toString());
			st.setInt(7, Integer.parseInt(patientAge));
			st.setString(8, patientEmail.toString());
			st.setInt(9, Integer.parseInt(patientContact));
			st.setInt(10, Integer.parseInt(appDocId));
			
			
			st.executeUpdate();
			
			String newDocAppointment = getDocAppointments();	
			
			output = "{\"status\":\"success\", \"data\": \"" + 
					newDocAppointment+ "\"}";
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\": "
					+ "        \"Error while updating the item.\"}";
			System.out.println(e);
		}
		
		return output; 
		
	}

	public String deleteDocAppointment(String appDocId) {
		
		String output = "";
		
		String sql = "delete from appointmentdoc where appDocId=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,Integer.parseInt(appDocId));
			
			
			st.executeUpdate();
			
			String newDocAppointment = getDocAppointments();	
			
			output = "{\"status\":\"success\", \"data\": \"" + 
					newDocAppointment+ "\"}";
			
		}catch(Exception e) {
			
			   output = "{\"status\":\"error\", \"data\":      "
			   		+ "   \"Error while deleting the item.\"}"; 
			System.out.println(e);
		}
		
		
		return output;
	}
}
