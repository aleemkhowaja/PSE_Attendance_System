<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<jsp:include page="../common/header.jsp" />
	<body>
		<!-------------------- crud urls  ---------------------------->
		<c:url var="returnAllDepartmentsForCombo" value="/returnAllDepartments/" ></c:url>
		<c:url var="returnAllLocationsForCombo" value="/returnAllLocations/" ></c:url>
		<c:url var="returnAllEmployeesForGrid" value="/employee/returnAllEmployeesForGrid/" ></c:url>
		<c:url var="addEmployee" value="/employee/addEmployee/" ></c:url>
		<c:url var="updateEmployee" value="/employee/updateEmployee/" ></c:url>
		<c:url var="deleteEmployee" value="/employee/deleteEmployee/" ></c:url>
		<c:url var="clearEmployeeForm" value="/clear" ></c:url>
		<!-- ------------------------------------------------------ -->
		<div class="body">
		    
			<jsp:include page="../common/menues.jsp" />
			<br /> <br /><h2>Users</h2>
		    <div class="advance_search">
				<div class="advance_search_heading">
					<p>Search</p>
				</div>
				<div class="advance_search_input">
					<form action="" method="post" class="ajaxForm stdform" id="table-form">
						<!-- Start Left Column -->
						<div class="adv_ser_row">
							<span> 
								<input class="smallinput" type="text" name="employeeName" id="employeeName" placeholder="Employee Name" />
							</span>
						</div>
						<div class="adv_ser_row_right">
							<input class="search_images search-button" type="submit" id="search-employee" value="Search" />
						</div>
					</form>
				</div>
			</div>
			
			<!-- <div id="centercontent" class="content"> -->
				<div id="employee-detail-grid" class="grid-container"></div>
				<div style="clear: both;"></div>

				<script type="text/javascript">
				$(document).ready(function(){
			        $('#employee-detail-grid').jtable({
			                title :'Students List',
			                paging : true,
							pageSize : 25,
							sorting : true,
			                actions :{
			                        listAction:'${returnAllEmployeesForGrid}'
			                       /*  updateAction:'${updateEmployee}',
			                        deleteAction:'${deleteEmployee}' */
			                },
			                fields :{
			                	employeeId: {
			                        key: true,
			                        create: false,
			                        edit: false,
			                        list: false
			                    },
			                	employeeName:{
			                    	title :'Employee Name',
			                        width :'30%',
			                   },
			                   fathersName :{
			                   		title :'Father Name',
			                    	width :'20%',
			                   },
			                   mobileNo :{
			                        title :'Mobile No',
			                        width :'30%',
			                   },
			                   isActive :{
			                        title :'Is Active',
			                        width :'30%',
			                   },
			                },
			                recordsLoaded: function(event, data) {
			                    $('.jtable-data-row').click(function() {
			                        var row_id = $(this).attr('data-record-key');
			                        employee_DBClick(row_id);
			                    });
			                },
			              //Initialize validation logic when a form is created
			                formCreated: function (event, data) {
			                    data.form.find('select[name="location"]').addClass('validate[required]');
			                    data.form.find('select[name="department"]').addClass('validate[required]');
			                    data.form.find('input[name="employeeName"]').addClass('validate[required]');
			                    data.form.find('input[name="username"]').addClass('validate[required]');
			                    data.form.find('input[name="password"]').addClass('validate[required]');
			                    
			                    data.form.validationEngine();
			                },
			                //Validate form when it is being submitted
			                formSubmitting: function (event, data) {
			                    return data.form.validationEngine('validate');
			                },
			                //Dispose validation logic when form is closed
			                formClosed: function (event, data) {
			                    data.form.validationEngine('hide');
			                    data.form.validationEngine('detach');
			                }
			        });
			        
			      //Re-load records when user click 'load records' button.
				    $('#search-employee').click(function (e) {
				    	e.preventDefault();
				        $('#employee-detail-grid').jtable('load', {
				        	employeeName: $('#employeeName').val()
				        });
				  	});

			      $('#employee-detail-grid').jtable('load');
			});

				</script>
				
				
				
				<jsp:include page="employee-detail.jsp" />
		    <jsp:include page="../common/footer.jsp" />
	</body>
</html>
