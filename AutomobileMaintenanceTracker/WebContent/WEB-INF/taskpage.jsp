<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Auto Maintenance Tracker</title>
		<link rel="stylesheet" href="/AutomobileMaintenanceTracker/css/stylesheet.css" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=Lato|Oswald|Roboto+Condensed" rel="stylesheet">
		<link rel="stylesheet" href="/AutomobileMaintenanceTracker/css/font-awesome-4.6.3/css/font-awesome.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		<script>
		
			$(function () {
				
				//called variable for number of times ajax has been called
				var called = 0; 
				
				ajaxSubmitter = function(forms){
					
					$.ajax({
						type: "POST",
						url: 'StatusServlet',
						data: forms[called].serialize(),
						success: function(data) {
							
							called++;
							
							if(called < forms.length) {
								//call the ajax function again
								ajaxSubmitter(forms);
							} else {
								//reset called value to 0
								called = 0; 
							}
						 
						},
			            error: function(jqXHR, textStatus, errorThrown){
			                  alert("An error occured. Please try again.");
			            },
			            beforeSend: function(jqXHR, settings){
			                //intercept the request by adding dummy data to disable 
			                //submit button until response from the server is received. 
			                settings.data += "&dummyData=whatever";
			                $('.sendForm').attr("disabled", true);
			            },
			            complete: function(jqXHR, textStatus){
			            	//enable submit button
			                $('.sendForm').attr("disabled", false);
			            }
					});
		
				}
				
				$(document).on('click','.sendForm',function(){
					
					//when saved button is pressed get array of all forms on page and use ajaxSubmitter function
					var x = 0;
					var forms = [];
					
					$('.taskForm').each(function(){
						
						forms[x] = $(this);
						x++;
						
					});
					
					ajaxSubmitter(forms);
					
					//adjust css to show changes in selected radio buttons, set save button to disabled
					$('.selected-radio').removeClass("selected-radio");
					$('.change-radio').addClass("selected-radio");
					
					$('#save-tasks').addClass('save-tasks-disabled');
					$('#save-tasks').prop("disabled", true);
					
					
				});
				
				$('input:radio[name="status"]').change(function(){
			        
					//the input element has been set to display:none, only labels are shown to allow custom css
					//when radio input has changed, adjust css to show selected option.
					//remove change-radio class from current radio option set and re-add change-radio class to most recently selected
					//set saved button to enabled
			        var item = $(this).data("value");
			        
			        $('[data-value="'+item+'"]').prev('label').removeClass("change-radio");
			        $(this).prev('label').addClass("change-radio");
			        
					$('#save-tasks').removeClass('save-tasks-disabled');
					$('#save-tasks').prop("disabled", false);
				    
				});

			});
    	</script>
	</head>
	<body>
	
		<!-- Header -->
		<div class="header-bar">
			<div class="header-bar-title"><i class="fa fa-wrench"></i> Auto Maintenance Tracker</div>
			<a href="/AutomobileMaintenanceTracker">
			<div class="btn-add-vehicle"><div class="btn-add-vehicle-arrow"><i class="fa fa-arrow-left" aria-hidden="true"></i></div></div>
			</a>
		</div>
		
		<!-- Customer Details -->
		<div class="customer-detail">
			<table class="table-customer-info">
				<tr>
					<td><i class="fa fa-user" aria-hidden="true"></i></td>
					<td><c:out value="${currentVehicle.customer}" /></td>
				<tr>
				<tr>
					<td><i class="fa fa-building" aria-hidden="true"></i></td>
					<td><c:out value="${currentVehicle.make}" /></td>
				</tr>
				<tr>
					<td><i class="fa fa-car" aria-hidden="true"></i></td>
					<td><c:out value="${currentVehicle.model}" /></td>
				</tr>
				<tr>
					<td><i class="fa fa-calendar" aria-hidden="true"></i></td>
					<td><c:out value="${currentVehicle.year}" /></td>
				</tr>
				<tr>
					<td><i class="fa fa-tachometer" aria-hidden="true"></i></td>
					<td><c:out value="${currentVehicle.odometer}" /></td>
				</tr>
			</table>
		</div>
		
		<!-- Vehicle Task Table -->
	    <table class="table-data">
	       <tr class="table-row-header">
	       	  <th>Task Item</th>
	          <th>Description</th>
	          <th>Status</th>
	       </tr>
		<c:forEach items="${tasks}" var="task" >
		   <tr class="table-task-row-data">
		   	 <td>${task.item}</td>
		     <td>${task.description}</td>
			 <td>
			 <form action="StatusServlet" class="taskForm" method="post">
			 <input type="hidden" name="id" value="${currentVehicle.id}">
			 <input type="hidden" name="item"class="item-input" value="${task.item}">
			 	
			 	<c:choose>
				  <c:when test="${task.status=='Open'}">
				  	<label for="open${task.item}" class="status-radio selected-radio change-radio">Open</label>
				    <input type="radio" name="status" id="open${task.item}" class="hidden-radio" data-value="${task.item}" value="Open" checked>
				  </c:when>
				  <c:otherwise>
				  	<label for="open${task.item}" class="status-radio">Open</label>
					<input type="radio" name="status" id="open${task.item}" class="hidden-radio" data-value="${task.item}" value="Open">
				  </c:otherwise>
				</c:choose>
			 	
			 	<c:choose>
				  <c:when test="${task.status=='In Progress'}">
				  	<label for="in-progress${task.item}" class="status-radio selected-radio change-radio">In Progress</label>
				    <input type="radio" name="status" id="in-progress${task.item}" class="hidden-radio" data-value="${task.item}" value="In Progress" checked>
				  </c:when>
				  <c:otherwise>
				  	<label for="in-progress${task.item}" class="status-radio">In Progress</label>
					<input type="radio" name="status" id="in-progress${task.item}" class="hidden-radio" data-value="${task.item}" value="In Progress">
				  </c:otherwise>
				</c:choose>
			 	
			 	<c:choose>
				  <c:when test="${task.status=='Closed'}">
				  	<label for="closed${task.item}" class="status-radio selected-radio change-radio">Closed</label>
				    <input type="radio" name="status" id="closed${task.item}" class="hidden-radio" data-value="${task.item}" value="Closed" checked>
				  </c:when>
				  <c:otherwise>
				  	<label for="closed${task.item}" class="status-radio">Closed</label>
					<input type="radio" name="status" id="closed${task.item}" class="hidden-radio" data-value="${task.item}" value="Closed">
				  </c:otherwise>
				</c:choose>
				

			</form>
			</td>
			
		   </tr>
		</c:forEach>
	    </table>
	    <button id="save-tasks" class="sendForm save-tasks-disabled" type="button" disabled>Save</button>
	
	</body>
</html>