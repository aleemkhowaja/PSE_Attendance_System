<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	
	<body>
		<!-------------------- crud urls  ---------------------------->
		<c:url var="returnAttendanceList" value="/attendance/attendanceList.htm/" ></c:url>
		<c:url var="updateAttendanceDetail" value="/attendance/updateAttendanceDetail/" ></c:url>
		<c:url var="deleteAttendanceDetail" value="/attendance/delete.htm/" ></c:url>
		<c:url var="clearAttendanceDetailForm" value="/clear" ></c:url>
		
		
		<!-- ------------------------------------------------------ -->
		<div class="body">
			<jsp:include page="../common/header.jsp" />
			<jsp:include page="../common/menues.jsp" />
		    <br /> <br /> <h2>Attendance</h2>
			<c:if test="${employee.role == 'admin'}">
				<div class="advance_search">
		    		<form action="" method="post" class="ajaxForm stdform" id="table-form">
						<!-- Start Left Column -->
						<div class="adv_ser_row">
							<span> 
								<input class="smallinput datepicker" type="text" name="	 " id="attendanceDate" placeholder="Attendance Date" />
							</span>
							<input class="search_images search-button" type="submit" id="search-attendance" value="Search" />
						</div>
						<div class="adv_ser_row_right">
							
						</div>
					</form>
				</div>
			</c:if>

			<!-- <div id="centercontent" class="content"> -->
				<div id="attendance-detail-grid" class="grid-container"></div>
				<input type="hidden" id="attendance_role" value="${employee.role}" />
				<jsp:include page="insert-attendance.jsp" />
					
			</div>
				<script type="text/javascript">
				$(document).ready(function(){
			        $('#attendance-detail-grid').jtable({
			                title :'Attendance',
			                paging : true,
							pageSize : 25,
							sorting : true,
			                actions :{
			                        listAction:'${returnAttendanceList}'
			                },
							recordsLoaded: function(event, data) {
							        $('.jtable-data-row').click(function() {
							        	var role = $("#attendance_role").val();
							        	attendance_DBClick($(this).find("#attendanceDetailId").val(),'Update','', role);
							        });
							 },
			                fields :{
			                	attendanceDetailId: {
			                        key: true,
			                        list: false
			                    },
			                    department:{
			                    	title :'Department Name',
			                        width :'30%'
			                    },
			                    emplayeeName:{
			                    	title :'Employee Name',
			                        width :'30%',
			                        display : function(data) {
										return  data.record.emplayeeName+'<input type="hidden"  id="attendanceDetailId" value="'+data.record.attendanceDetailId+'" > ';
									} 
			                    },
			                    attendanceDateString:{
			                		title: 'Attendance Date',
			                        width: '20%'
			                	},
			                	inTime:{
			                		title: 'In Time',
			                        width: '10%'		                        
			                	},
			                	outTime:{
			                    	title :'Out Time',
			                        width :'30%'
			                    },
			                    onLeave:{
			                    	title :'Present',
			                        width :'30%'
			                    },
			                    outTimeDescription:{
									type : 'textarea',			                    	
			                    	title :'Remarks',
			                        width :'30%',
			                        list: false
			                    }
			                },
			              //Initialize validation logic when a form is created
			                formCreated: function (event, data) {
			                    data.form.find('input[name="inTime"]').addClass('validate[required]');
			                    data.form.find('input[name="outTime"]').addClass('validate[required]');
			                    data.form.find('input[name="inTime"]').addClass('timepicker');
			                    data.form.find('input[name="outTime"]').addClass('timepicker');
			                    $('.timepicker').timepicker({
			        	            timeFormat: 'h:mm p',
			        	            interval: 60,
			        	            minTime: '5',
			        	            maxTime: '6:00pm',
			        	            startTime: '10:00',
			        	            dynamic: false,
			        	            dropdown: false,
			        	            scrollbar: true
			        	        });
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
			       $('#search-attendance').click(function (e) {
			           e.preventDefault();
			           $('#attendance-detail-grid').jtable('load', {
			               attendanceDate: $('#attendanceDate').val()
			           });
			       });
			      
			      //Load all records when page is first shown
			       $('#attendance-detail-grid').jtable('load');
			        			        
			});
				
							
				</script>
				
				
				
				<%-- <jsp:include page="employee-detail.jsp" /> --%>
		    <jsp:include page="../common/footer.jsp" />
	</body>
</html>
