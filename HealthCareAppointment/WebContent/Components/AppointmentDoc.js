/**
 * 
 */

$(document).ready(function(){
	
	if ($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	
});

// save **********************************************
$(document).on("click", ".btnSave", function(event){
	
	// Clear alerts--------------------- 
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide(); 
	
	//Form validation ******************
	var status = validateAppointmentDocForm();
	
	if (status != true){
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
	}
	
	//If valid ******************
	//$("#AppointmentDocForm").submit(); 
	$("#alertSuccess").text("Saved successfully.");
	$("#alertSuccess").show();
	//$("#AppointmentDocForm")[0].reset();
});


//Update ************************************************
$(document).on("click", ".btnUpdate", function(event){
	
	$("#appDocId").val($(this).closest("tr").find('#appDocId').val());
	$("#specialization").val($(this).closest("tr").find('td:eq(0)').text());
	$("#docName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#hospital").val($(this).closest("tr").find('td:eq(2)').text());
	$("#date").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#patientNIC").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#patientName").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#patientAge").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#patientEmail").val($(this).closest("tr").find('td:eq(7)').text()); 
	$("#patientContact").val($(this).closest("tr").find('td:eq(8)').text()); 
	
	
});


// Client - model ******************************************

function validateAppointmentDocForm(){
	
	//specialization
	if ($("#specialization").val().trim() == ""){
		return "Enter Specialization";
	}
	
	//docName
	if ($("#docName").val().trim() == ""){
		return "Enter Doctor Name";
	}
	
	//hospital
	if ($("#hospital").val().trim() == ""){
		return "Enter Hospital ";
	}
	
	// date
	if ($("#date").val().trim() == ""){
		return "Enter Date";
	}
	
	// patientNIC
	if ($("#patientNIC").val().trim() == ""){
		return "Enter Patient NIC";
	}
	
	// patientName
	if ($("#patientName").val().trim() == ""){
		return "Enter Patient Name";
	}
	
	// patientAge
	if ($("#patientAge").val().trim() == ""){
		return "Enter Patient Age";
	}
	
	//is age no numerical value
	var tmpAge = $("#patientAge").val().trim(); 
	if (!$.isNumeric(tmpAge)){
		return "Enter a numerical value for Patient Age"; 
	}
	
	//patientEmail
	if ($("#patientEmail").val().trim() == ""){
		return "Enter Patient Email";
	}
	
	//patientContact
	if ($("#patientContact").val().trim() == ""){
		return "Enter Patient Contact";
	}
	
	//is contact no numerical value
	var tmpContact = $("#patientContact").val().trim(); 
	if (!$.isNumeric(tmpContact)){
		return "Enter a numerical value for Contact Nummber"; 
	}
	
	
	return true;
}



$(document).on("click", "#btnSave", function(event){
	
	
var status = validateAppointmentDocForm();
	
	if (status != true){
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
	}
	
	//$("#AppointmentDocForm")[0].reset();
	
	var type = ($("#appDocId").val() == "") ? "POST" : "PUT"; 
	
	

$.ajax( 
{
	url : "AppointmentAPI",
	type : type, 
	data : $("#AppointmentDocForm").serialize(), 
	dataType : "text",
	complete : function(response, status){
		onAppointmentSaveComplete(response.responseText, status); 
	}
	});	
});

function onAppointmentSaveComplete(response, status){
	if (status == "success"){
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show();
			
			$("#divItemsGrid").html(resultSet.data);
			
		}else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		}
	}else if (status == "error"){
		
		$("#alertError").text("Error while saving."); 
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#appDocId").val("");
	//$("#AppointmentDocForm").submit(); 
	$("#AppointmentDocForm")[0].reset();
}



$(document).on("click", ".btnRemove", function(event){
	//event.preventDefault()
	$.ajax(
	{
		url : "AppointmentAPI",
		type : "DELETE",
		data : "appDocId=" + $(this).data("appdocid"),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentDeleteComplete(response.responseText, status); 
		}
	});
});

function onAppointmentDeleteComplete(response, status){
	
	if (status == "success"){
		
		var resultSet = JSON.parse(response); 
		
		if (resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show(); 
			 
			$("#divItemsGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);   
			$("#alertError").show(); 
		} else if (status == "error") {
			$("#alertError").text("Error while deleting.");  
			$("#alertError").show();
		}else{
			$("#alertError").text("Unknown error while deleting.."); 
			$("#alertError").show();
		}
	}
}





		