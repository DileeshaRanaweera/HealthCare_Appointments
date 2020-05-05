
<%@page import="com.PAFProject.HealthCare.AppointmentDocRepository"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   

    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="Components/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Components/AppointmentDoc.js"></script> 
<link rel="stylesheet" href="Views/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>

	<h1><center>Doctor Appointments</center></h1>
	<form id="AppointmentDocForm" name="AppointDocForm" method="post" action="AppointmentDoc.jsp">
	<div class="container">
  	<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-4">
	
		Specialization:
		<input id="specialization" name="specialization" type="text" class="form-control form-control-sm"><br> 
 	
		Doctor Name:
		<input id="docName" name="docName" type="text" class="form-control form-control-sm"><br> 
 
		Hospital:
		<input id="hospital" name="hospital" type="text" class="form-control form-control-sm"><br> 
 
		Date:
		<input id="date" name="date" type="date" class="form-control form-control-sm"><br>
	
	</div>
  <div class="col-sm-4">
 
		
		Patient NIC:
		<input id="patientNIC" name="patientNIC" type="text" class="form-control form-control-sm"><br>
		
		Patient Name:
		<input id="patientName" name="patientName" type="text" class="form-control form-control-sm"><br>
		
		Patient Age:
		<input id="patientAge" name="patientAge" type="text" class="form-control form-control-sm"><br>
		
		Patient Email:
		<input id="patientEmail" name="patientEmail" type="text" class="form-control form-control-sm"><br>
		
		Patient Contact Number:
		<input id="patientContact" name="patientContact" type="text" class="form-control form-control-sm"><br>
 
		
		
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="appDocId" name="appDocId" value="" styles ="margine-bottom: 50px;">
		<br><br>
	
	</div>
	<div class="col-sm-2"></div>
	</div>
	</div>
 
 </form>
 
	    <div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		
		<br>
		<div class="table-responsive">
		<div id="divItemsGrid"  class="table table-striped w-auto">
			<%
				AppointmentDocRepository appDocRepo = new AppointmentDocRepository();
				out.print(appDocRepo.getDocAppointments());
			%>
			</div>
			</div>
 
 

</body>
</html>