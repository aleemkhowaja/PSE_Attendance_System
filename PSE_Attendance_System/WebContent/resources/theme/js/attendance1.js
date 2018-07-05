/**
 * Submit Form while Save/Update
 */
function attendance_Crud(event)
{ 
	var flag = validateForm();
	if(flag)
	{
		url ='/PSE_Attendance_System/attendance/save.htm';
		event.preventDefault();
		$.ajax({
			url :url,
			type: "POST",
			data : $("#attendanceSubmitForm").serialize(),
			async:false,
			success : function(data) {
				if(data == 'Success') {
					$('#attendance-detail-grid').jtable('load');
					iziToast.success({
					    title: 'OK',
					    message: 'Successfully inserted record!',
					});
					// invoke common function for clear fields
					clearFields("attendanceSubmitForm");
				}
				else 
					if(data == 'duplicate')
					{
						iziToast.warning({
						    title: 'Caution',
						    message: 'Today\'s Attendance Already Exist',
						});
					}
					else 
						if(data == 'inTimeMissing')
						{
							iziToast.warning({
							    title: 'Caution',
							    message: 'In Time is Missing',
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
	}
	event.preventDefault();
	return flag;
}

function isOnLeave()
{
	var isOnLeave = $("input[name='onLeave']:checked"). val();
//	var isOnLeave = $("#onLeaveCheckBox").is(':checked');
	if(isOnLeave == "Present")
	{
		$('.afterLeave').removeAttr('disabled'); //Enable
	}
	else
	{
		$('.afterLeave').attr('disabled', 'disabled'); //Disable
	}
}


function attendance_DBClick(attendanceId, status, id, role)
{
	if(role != "employee")
	{
		url ='/PSE_Attendance_System/attendance/getAttendanceById';
		$.post(url, {
			attendanceId : attendanceId,
		}, function(data) {
				var isOnLeave = data.onLeave;
				if(isOnLeave != "Present")
				{
					$('.afterLeave').attr('disabled', 'disabled'); //Disable
					$("#onLeaveCheckBox").prop('checked', true);
				}
				else
				{
					$('.afterLeave').removeAttr('disabled'); //Enable
					$("#onLeaveCheckBox").prop('checked', false);
				}
				//$("#holiday_delete").show();
				//$("#holiday_save").css("margin-left", "75%");
				var myElem = document.getElementById('attenance-delete-btn');
				if (myElem == null)
				{
					$("#attendance-save-btn").after("<input type='button' id='attenance-delete-btn' style='margin-left: 1%;' class='btn' value='Delete' onclick='attendance_DeleteAttendance() ;'/>");
				}
				attendance_Set_FormData(data);
			}
		);
	}
}

function attendance_Set_FormData(data)
{
	$("#attendance_id").val(data.attendanceDetailId);
	$("#attendance_employee").val(data.employee);
	$("#employeeAttendanceDate").val(data.attendanceDateString);
	$("input[name='onLeave'][value='"+data.onLeave+"']").prop('checked', true);
	$("#place").val(data.placeAt);
	$("#inTime").val(data.inTime);
	$("#outTime").val(data.outTime);
	$("#employeeAttendance_remarks").val(data.remarks);
	pageAniamateScroll();
	isOnLeave();
}

function attendance_DeleteAttendance()
{
	
	var result = confirm("Do you want to Delete?");
	if(result)
	{
		url ='/PSE_Attendance_System/attendance/delete.htm';
		var attendanceDetailId = $("#attendance_id").val();
		//event.preventDefault();
		$.ajax({
			url :url,
			 type: "POST",
	         data:{attendanceDetailId : attendanceDetailId},
			 async:false,
			 success : function(data) {
				if(data != undefined) {
					
					$('#attendance-detail-grid').jtable('load');
					iziToast.success({
					    title: 'OK',
					    message: 'Attendance Deleted Succesfully',
					});
					// invoke common function for clear fields
					clearFields("attendanceSubmitForm");
				}
				else {
					
				}
			}
		});
		//event.preventDefault();
	}
}
//function attendance_DBClick(id,mode,url)
//{
//if (mode == 'Update') {///start of update mode if
//		
//		url ='/PSE_Attendance_System/attendance/getAttendanceById';
//		$.post(url, {
//			attendanceId : id,
//		}, function(data) {
//			$("#attendanceDetailId").val(data.attendanceDetailId);
//			$("#employeeAttendanceDate").val(data.attendanceDateString);
//			$("#attendance_employee").val(data.employee);
//			var isOnLeave = data.onLeave;
//			if(isOnLeave)
//			{
//				$('.afterLeave').attr('disabled', 'disabled'); //Disable
//				$("#onLeaveCheckBox").prop('checked', true);
//			}
//			else
//			{
//				$('.afterLeave').removeAttr('disabled'); //Enable
//				$("#onLeaveCheckBox").prop('checked', false);
//			}
//			//$("#holiday_delete").show();
//			//$("#holiday_save").css("margin-left", "75%");
//			}
//		);
//}
//}
/*function attendance_Crud(id,mode,url){
	
	if (mode == 'Update') {///start of update mode if
		
		url ='/PSE_Attendance_System/attendance/getAttendanceById';
		event.preventDefault();
		$.post(url, {
			attendanceId : id,
		}, function(data) {
			alert(data.employee);
			$("#attendanceDetailId").val(data.attendanceDetailId);
			$("#employeeAttendanceDate").val(data.attendanceDateString);
			$("#attendance_employee").val(data.employee);
			var isOnLeave = data.onLeave;
			if(isOnLeave)
			{
				$('.afterLeave').attr('disabled', 'disabled'); //Disable
				$("#onLeaveCheckBox").prop('checked', true);
			}
			else
			{
				$('.afterLeave').removeAttr('disabled'); //Enable
				$("#onLeaveCheckBox").prop('checked', false);
			}
			//$("#holiday_delete").show();
			//$("#holiday_save").css("margin-left", "75%");
			}
		);
		event.preventDefault();
	} else if (mode == 'Delete') {///start of Delete mode if
		var r = confirm("Do you want to delete ?");
		if (r == true) {//start of if
			var id = $("#holidayId").val();
			url ='/PSE_Attendance_System/holiday/deleteHoliday';
			event.preventDefault();
			$.post(url, {
				holidayId : id,
			}, function(data) {
				if(data == 'Success') {
					$('#holiday-detail-grid').jtable('load');
					iziToast.success({
					    title: 'OK',
					    message: 'Successfully Deleted record!',
					});
					holiday_form_clear();
				}
				else {
					iziToast.error({
					    title: 'Error',
					    message: 'Illegal operation',
					});			
				}
				}
			);
		}
		event.preventDefault();
	}///End of Delete mode if
}*/

function employee_attendance_form_clear()
{
	$("#empName").val("");
	$("#fatherName").val("");
	$("#place").val("");
	$("#inTime").val("");
	$("#outTime").val("");
	$("#employeeAttendance_remarks").val("");
	//remove Delete Button
	remove_child_Elements('attendance-save-btn');
}
