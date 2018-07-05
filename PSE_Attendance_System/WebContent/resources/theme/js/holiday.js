$(document).on('submit', '#holidaySubmitForm', function(event) {
	
	url ='/PSE_Attendance_System/holiday/save.htm';
	var holidayData = {}
	holidayData['holiday'] = $("#holidayDate").val();
	holidayData['description'] = $("#holiday_Description").val();
	holidayData['holidayId'] = $("#holiday_id").val() == "" ? null : $("#holiday_id").val();
	event.preventDefault();
	$.ajax({
		url :url,
		 type: "POST",
         contentType: "application/json",
		 data: JSON.stringify(holidayData),
		 
         beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
         },
		 async:false,
		 success : function(data) {
			if(data == 'Success') {
				$('#holiday-detail-grid').jtable('load');
				iziToast.success({
				    title: 'OK',
				    message: 'Successfully inserted record!',
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
	});
	event.preventDefault();
});

function holiday_Crud(id,mode,url){
	
	if (mode == 'Update') {///start of update mode if
		
		url ='/PSE_Attendance_System/holiday/getHoliday';
		event.preventDefault();
		$.post(url, {
			holidayId : id,
		}, function(data) {
			$("#holidayDate").val(data.holiday);
			$("#holiday_Description").val(data.description);
			$("#holiday_id").val(data.holidayId);
			//$("#holiday-buttons").append("<input type='button' value='Delete' onclick='holiday_Crud('','Delete','');' class='btn btn-primary'></input>");
			$("#holiday_delete").show();
			//$("#holiday_save").css("margin-left", "75%");
			}
		);
		event.preventDefault();
	} else if (mode == 'Delete') {///start of Delete mode if
		var r = confirm("Do you want to delete ?");
		if (r == true) {//start of if
			var id = $("#holiday_id").val();
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
}

function holiday_form_clear()
{
	$("#holidayDate").val("");
	$("#holiday_Description").val("");
	$("#holiday_delete").hide();
	$("#holiday_id").val("");
}