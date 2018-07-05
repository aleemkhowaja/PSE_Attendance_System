$(document).on('submit', '#employeeSubmitForm', function(event) 
{
	var employeeId = $("#employeeId").val();
	url ='/PSE_Attendance_System/employee/save.htm';
	var employeeData = {}
	if(employeeId != "" && employeeId != undefined)
	{
		employeeData['employeeId'] = employeeId;
	}
	var isActive = $('input[name="isActive"]:checked').val();
	if(isActive == undefined)
	{
		isActive = false;
	}
	
	employeeData['employeeName'] = $("#empName").val();
	employeeData['fathersName'] = $("#fatherName").val();
	employeeData['mobileNo'] = $("#mobileNo").val();
	employeeData['registrationNo'] = $("#registrationNo").val();
	employeeData['address'] = $("#address").val();
	employeeData['username'] = $("#username").val();
	employeeData['password'] = $("#password").val();
	employeeData['department'] = $("#dapartment").val();
	employeeData['location'] = $("#location").val();
	employeeData['isActive'] = isActive;
	
	event.preventDefault();
	$.ajax({
		url :url,
		 type: "POST",
         contentType: "application/json",
		 data: JSON.stringify(employeeData),
		 
         beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
         },
		 async:false,
		 success : function(data) {
			if(data == 'Employee Saved Successfuly') {
				$('#employee-detail-grid').jtable('load');
				iziToast.success({
				    title: 'OK',
				    message: 'Employee Saved Succesfully',
				});
				// invoke common function for clear fields
				clearFields("employeeSubmitForm");
			}
			else
				if(data == 'Duplicate Employee Username' || data == 'Duplicate Employee Registration No')
				{
					iziToast.warning({
					    title: 'Caution',
					    message: data,
					});
				}
			else {
				iziToast.error({
				    title: 'Error',
				    message: 'Illegal operation',
				});			
			}
		}
	});
	event.preventDefault();
});


$(document).on('submit', '#employeeRegisterSubmitForm', function(event) {
	
	var screenName = $("#employeeRegistrationId").val();
	url ='/PSE_Attendance_System/employee/registerEmployee.htm';
	var employeeData = {}
	employeeData['employeeName'] = $("#empName").val();
	employeeData['fathersName'] = $("#fatherName").val();
	employeeData['mobileNo'] = $("#mobileNo").val();
	employeeData['registrationNo'] = $("#registrationNo").val();
	employeeData['address'] = $("#address").val();
	employeeData['username'] = $("#username").val();
	employeeData['password'] = $("#password").val();
	employeeData['department'] = $("#dapartment").val();
	employeeData['location'] = $("#location").val();
	
	event.preventDefault();
	$.ajax({
		url :url,
		 type: "POST",
         contentType: "application/json",
		 data: JSON.stringify(employeeData),
		 
         beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
         },
		 async:false,
		 success : function(data) {
			if(data == 'Employee Saved Successfuly') {
				iziToast.success({
				    title: 'OK',
				    message: 'Employee Saved Succesfully',
				});
				employee_form_clear();
			}
			else
				if(data == 'Duplicate Employee Username' || data == 'Duplicate Employee Registration No')
				{
					iziToast.warning({
					    title: 'Caution',
					    message: data,
					});
				}
			else {
				iziToast.error({
				    title: 'Error',
				    message: 'Illegal operation',
				});			
			}
		}
	});
	event.preventDefault();
});

function employee_DBClick(employeeId)
{
	url ='/PSE_Attendance_System/employee/getEmployeeById';
	$.ajax({
		url :url,
		 type: "POST",
		 async:false,
		 data:{employeeId : employeeId},
		 /*beforeSend: function(xhr) 
         {
			 xhr.setRequestHeader(header, token);
         },*/
		 success : function(data) {
			 employee_Set_FormData(data);
			 var myElem = document.getElementById('employee-delete-btn');
			 if (myElem == null)
			 {
				 $("#employee-save-btn").after("<input type='button' id='employee-delete-btn' style='margin-left: 1%;' class='btn' value='Delete' onclick='employee_DeleteEmployee() ;'/>");
			 }
		 }
	});
}

function employee_DeleteEmployee()
{
	
	var result = confirm("Do you want to Delete?");
	if(result)
	{
		url ='/PSE_Attendance_System/employee/deleteEmployee';
		var employeeId = $("#employeeId").val();
		//event.preventDefault();
		$.ajax({
			url :url,
			 type: "POST",
	         data:{employeeId : employeeId},
			 async:false,
			 success : function(data) {
				if(data != undefined) {
					
					$('#employee-detail-grid').jtable('load');
					iziToast.success({
					    title: 'OK',
					    message: 'Employee Deleted Succesfully',
					});
					employee_form_clear();
				}
				else {
				}
			}
		});
		//event.preventDefault();
	}
}

function employee_Set_FormData(data)
{
	$("#employeeId").val(data.employeeId);
	$("#empName").val(data.employeeName);
	$("#fatherName").val(data.fathersName);
	$("#mobileNo").val(data.mobileNo);
	$("#registrationNo").val(data.registrationNo);
	$("#address").val(data.address);
	$("#username").val(data.username);
	$("#dapartment").val(data.department);
	$("#location").val(data.location);
	if(data.isActive)
	{
		$('#isActive').prop('checked', true);
	}
	else
	{
		$('#isActive').prop('checked', false);
	}
	pageAniamateScroll();
}


function employee_form_clear()
{
	$("#attendance_employee").val("");
	$("#employeeAttendanceDate").val("");
	$("#mobileNo").val("");
	$("#registrationNo").val("");
	$("#address").val("");
	$("#username").val("");
	$("#password").val("");
	$("#dapartment").val("");
	$("#location").val("");
	$('#isActive').prop('checked', false);
}