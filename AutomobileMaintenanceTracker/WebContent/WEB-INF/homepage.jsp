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
		
		
			//create initialise function
			function initialise(){
				
				//Delete customer using post request to DeleteServlet with ajax
			    $('.deleteForm').on('submit', function (e) {
			        $.ajax({
			            type: 'POST',
			            url: 'DeleteServlet',
			            data: $(this).serialize(),
			            success: function (data) {
			                if(data){
								//delete animation
			                     $("#"+data).slideUp(300,function() {
			     					$("#"+data).remove();
		    					 });    
			                 }
			            },
			            error: function(jqXHR, textStatus, errorThrown){
			                  alert("An error occured. Please try again.");
			            },
			            beforeSend: function(jqXHR, settings){
			                //intercept the request by adding dummy data to disable 
			                //submit button until response from the server is received. 
			                settings.data += "&dummyData=whatever";
			                $('.sendDeleteForm').attr("disabled", true);
			            },
			            complete: function(jqXHR, textStatus){
			            	//enable submit button
			                $('.sendDeleteForm').attr("disabled", false);
			            }
			        });
			        e.preventDefault();
			    });
			    
			  	//Add customer using post request to FormServlet with ajax
			    $('#addForm').on('submit', function (e) {
			        $.ajax({
			            type: 'POST',
			            url: 'FormServlet',
			            dataType: 'json',
			            data: $(this).serialize(),
			            success: function (data) {
			                 if(data){
			                	 
			                	 //empty the 'Add A Customer' form, remove popup and return to main screen
			                	 document.getElementById("addForm").reset();
			     				 document.getElementById('pop-up-fade').style.display = "none";
			    				 document.getElementById('pop-up-form').style.display = "none";
			                	 
			    				 //color code vehicle type
			                	 var color = '';
			                	 if(data.type == 'Gas'){
			                		 color = '#ffcc00';
			                	 } else if(data.type == 'Diesel'){
			                		 color = '#7a7aff';
			                	 } else if(data.type == 'Electric'){
			                		 color = '#42c07c';
			                	 }
								
			                	 //on success add submitted data to list
			                	 $('.table-data > tbody> tr:first').after(
				               	        '<tr id="'+data.id+'" class="table-row-data">' +
				          	            '<td><a href="tasks?code='+data.id+'"></a>'+data.customer+'</td>' +
				          				'<td>'+data.make+'</td>' +
				          	            '<td>'+data.model+'</td>' +
				          	            '<td>'+data.year+'</td>' +
				          	            '<td class="odometer-field">'+data.odometer+' km</td>' +
				          	            '<td><span style="color:'+color+'">'+data.type+'</span></td>' +
				          	            '<td>' +
				          				'<form action="DeleteServlet" class="deleteForm" method="post" onclick="return confirm(\'Are you sure you want to delete this item?\')");">' +
				          					'<input type="hidden" name="id" value="'+data.id+'">' +
				          					'<button class="sendDeleteForm" type="submit">' +
				          						'<i class="fa fa-trash" aria-hidden="true"></i>' +
				          					'</button>' +
				          				'</form>' +
				          	            '</td>' +
				          	         '</tr>'
				                );

			                 } else {
			                	 alert("An error occured. Please try again.");
			                 }
			            },
			            error: function(jqXHR, textStatus, errorThrown){
			            	alert("An error occured. Please try again.");
			            },
			            beforeSend: function(jqXHR, settings){
			                //intercept the request by adding dummy data to disable 
			                //submit button until response from the server is received. 
			                settings.data += "&dummyData=whatever";
			                $('#sendAddForm').attr("disabled", true);
			            },
			            complete: function(jqXHR, textStatus){
			            	//enable submit button
			                $('#sendAddForm').attr("disabled", false);
			            }
			        });
			        e.preventDefault();
			    });
			    

			    //link each table row item to taskpage.jsp except for the last 'tr' which contains delete function
			    //reference parent 'tr' and find close 'a' to get link
				$('table tr').each(function(i,e){
					$(e).children('td:not(:last)').click(function(){
				         var href = $(this).closest("tr").find("a").attr("href");
				         if(href){
				            window.location = href;
				         }              
					});
				});
				
				//display odometer amounts with commas
				$('.odometer-field').each(function(){
					var number = $(this).text().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					$(this).html(number);
				});
				
				//fill year drowndown list in 'Add A Customer' popup form
				//new vehicles can be up to a year ahead
				//place vehicles in order of newest to oldest
				var select = document.getElementById("year"); 
				var newestYear = (new Date().getFullYear()) + 2;
				var options = []; 
				
				var x = 1950;

				while (x < newestYear) {
					options.push(x);
				 	x++;
				}
				
				options.reverse();
				

				for(var i = 0; i < options.length; i++) {
				    var opt = options[i];
				    var el = document.createElement("option");
				    el.textContent = opt;
				    el.value = opt;
				    select.appendChild(el);
				}


				
			};
			
			
			//run initialise function at document ready
			$(function () {
				initialise();
			});
			
			//run initialise function whenever Ajax request is completed
			$(document).ajaxComplete(function () {
			    initialise();
			});


			
			//function to display 'Add A Customer' popup
			function div_show() {
				document.getElementById('pop-up-fade').style.display = "block";
				document.getElementById('pop-up-form').style.display = "block";
			}
			
			//function to display 'Add A Customer' popup
			function div_hide(){
				document.getElementById('pop-up-fade').style.display = "none";
				document.getElementById('pop-up-form').style.display = "none";
			}
			


			
    	</script>
	</head>
	<body>
	
		<!-- Popup Faded Background -->
		<div id="pop-up-fade" onclick ="div_hide()"></div>
		
		<!-- Popup Form -->
		<div id="pop-up-form">
			<div class="pop-up-form-header"><div class="pop-up-form-header-text">Add A Customer</div></div>
			<div class="pop-up-form-fields">
				<form action="FormServlet" id="addForm" method="post">
					<div class="add-form-fields">
						<label for="customer" class="add-form-label">Customer Name:</label>
						<input id="customer" class="add-form-input" type="text" name="customer" placeholder="Aaron Hills..." required><br>
					</div>
					<div class="add-form-fields">
						<label for="make" class="add-form-label">Vehicle Make:</label>
						<input id="make" class="add-form-input" type="text" name="make" placeholder="BMW..." required><br>
					</div>
					<div class="add-form-fields">
						<label for="model" class="add-form-label">Vehicle Model:</label>
						<input id="model" class="add-form-input" type="text" name="model" placeholder="X4..." required><br>
					</div>
					<div class="add-form-fields">
						<label for="year" class="add-form-label">Vehicle Year: </label>
						<select id="year" class="add-form-select" name="year" required>
						</select><br>
					</div>
					<div class="add-form-fields">
						<label for="odometer" class="add-form-label">Vehicle Odometer:</label>
						<input id="odometer" class="add-form-input" type="number" name="odometer" placeholder="123456..." required><br>
					</div>
					<div class="add-form-fields">
						<label for="type" class="add-form-label">Vehicle Type: </label>
						<select id="type" class="add-form-select" name="type" required>
						  <option value="Electric">Electric</option>
						  <option value="Gas">Gas</option>
						  <option value="Diesel">Diesel</option>
						</select><br>
					</div>
					<input id="sendAddForm" type="submit" value="Submit">
				</form>
			</div>
		</div>

		<!-- Header -->
		<div class="header-bar">
			<div class="header-bar-title"><i class="fa fa-wrench"></i> Auto Maintenance Tracker</div>
			<div id="popup" onclick="div_show()" class="btn-add-vehicle"><div class="btn-add-vehicle-plus">+</div></div>
		</div>
	 	
	 	<!-- Customer Table -->
	    <table class="table-data">
	       <tr class="table-row-header">
	          <th>Customer</th>
	          <th>Make</th>
	          <th>Model</th>
	          <th>Year</th>
	          <th>Odometer</th>
	          <th>Type</th>
	          <th>Delete${productList}</th>
	       </tr>
	       <c:forEach items="${list}" var="job" >
	          <tr id="${job.id}" class="table-row-data">
	            <td><a href='tasks?code=${job.id}'></a>${job.customer}</td>
				<td>${job.make}</td>
	            <td>${job.model}</td>
	            <td>${job.year}</td>
	            <td class="odometer-field">${job.odometer} km</td>
	            <td>
	            	<c:choose>
					    <c:when test="${job.type=='Gas'}">
							<span style="color:#ffcc00">
					    </c:when>
					    <c:when test="${job.type=='Diesel'}">
							<span style="color:#7a7aff">
					    </c:when>
					    <c:when test="${job.type=='Electric'}">
							<span style="color:#42c07c">
					    </c:when>
				    </c:choose>
				    ${job.type}
				    </span>
				</td>
	            <td>
				<form action="DeleteServlet" class="deleteForm" method="post" onclick="return confirm('Are you sure you want to delete this item?');">
					<input type="hidden" name="id" value="${job.id}">
					<button class="sendDeleteForm" type="submit">
						<i class="fa fa-trash" aria-hidden="true"></i>
					</button>
				</form>
	            </td>
	          </tr>
	       </c:forEach>
	    </table>
	    
	</body>
</html>

