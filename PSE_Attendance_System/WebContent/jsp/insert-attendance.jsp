 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="saveAttendance" value="/attendance/save.htm" ></c:url>
<c:url var="home" value="/" scope="request" />

<link rel="stylesheet" href="<c:url value="/resources/theme/css/bootstrap.min.css"/>" />
<script src="<c:url value="/resources/theme/js/bootstrap.min.js" /> "></script>
<script src="<c:url value="/resources/theme/js/attendance1.js" />"></script>
<link href="<c:url value="/resources/theme/css/attendance.css" />" rel="stylesheet" />
<style>
	select {
	    color: black;
	}
</style>
 <form:form  servletRelativeAction="${saveAttendance}" cssClass="form-horizontal validate-form" method="post" commandName="attendanceData" id="attendanceSubmitForm" resolveContext="true" resolveMapping="true">
		<fieldset>
			<h3><legend><span class="number"></span>Insert Your Attendance</legend></h3>

			<c:if test="${employee.role == 'admin'}">
				<label>Employee :</label>
				<form:select cssClass="form-control" id="attendance_employee" path="employee">
		 			<form:option value="" label="Select Employee" />
		 			<form:options items="${employeeList}" itemValue="employeeId" itemLabel="employeeName"   />
		 		</form:select>
		 		
		 		<label>Date :</label>
		 		<form:input cssClass="form-control smallinput datepicker" path="attendanceDate" id="employeeAttendanceDate" placeholder="Attendance Date" />
		 		
		 		<label>Leave/ Absent :</label>
		 		<form:radiobutton path="onLeave" value="Present" id="over_13" cssClass="validate-required" onclick="isOnLeave();"   />  Present 
    			<form:radiobutton path="onLeave" value="Leave" id="over_13"  cssClass="validate-required" onclick="isOnLeave();"   />  Leave 
				<form:radiobutton path="onLeave" value="Absent" id="over_13" cssClass="validate-required" onclick="isOnLeave();"    />  Absent 
		 		<%-- <form:checkbox id="onLeaveCheckBox" path="onLeave" onchange="isOnLeave();" /> --%>
		 		
		 		<form:label path="placeAt"> Present Location :</form:label>
				<form:select cssClass="afterLeave form-control" id="place" path="placeAt">
		 			<form:option value="" label="Select Place" />
		 			<form:option value="onsite" label="On Site" />
		 			<form:option value="office" label="Office"  />
		 		</form:select>
		 		
		 		<label>In Time</label>
		 		<form:input cssClass="afterLeave form-control smallinput" path="inTime" id="inTime" placeholder="In Time (09:00 am)" />
		 		
		 		<label>Out Time</label>
		 		<form:input cssClass="afterLeave form-control smallinput" path="outTime" id="outTime" placeholder="Out Time (06:30 pm)" />
	
			</c:if>
		    <c:if test="${employee.role == 'employee'}">
		    	<div class="form-group">
    		 		<form:label cssClass="control-label col-xs-2" style="margin-top: -9px;" id="time-label"  path="timeStatus"> Time :</form:label>
    				<div class="col-xs-10">
    					<form:radiobutton path="timeStatus" value="in" id="over_13"  cssClass="validate-required"  />  In Time 
					 	<form:radiobutton path="timeStatus" value="out" id="over_13" cssClass="validate-required"  />  Out Time 
    				</div>
    			</div>		    
		    </c:if>

    		<form:label path="inTimeDescription"> Remarks :</form:label>
			<form:textarea cssClass="form-control" path="remarks" id="employeeAttendance_remarks"  />	
<%-- 			
			 <div class="form-group">
    		 	<form:label cssClass="control-label col-xs-2"  path="workPlace"> Work Place :</form:label>
    			<div class="col-xs-10">
        			<form:select cssClass="form-control" id="workPlace" path="workPlace" >
	 					<form:option value="" label="Select Work Place" />
	 					<form:option value="home" label="Home" />
	 					<form:option value="onSite" label="On Site" />
	 					<form:option value="office" label="Office" />
	 				</form:select>
 				</div>
 			</div> --%>
 	
			<!-- <button value="Submit" id="insert-attendance" class="btn btn-primary btn-lg" > Search</button> -->
			<!-- <input style="width: 20%; margin-left: 80%;" type="submit" value="Submit" class="button" id="insert-attendance"  /> -->
			<button id="attendance-save-btn" type="submit" class="btn" onclick="return attendance_Crud(event);" >Save</button>
    	</fieldset>
    	<form:hidden path="attendanceDetailId" id="attendance_id" />
</form:form>