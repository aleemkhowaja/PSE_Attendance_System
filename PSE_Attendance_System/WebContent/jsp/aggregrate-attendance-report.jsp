 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<c:url var="aggregrateMonthlyAttendanceReportPdf" value="/attendance/aggregrateMonthlyAttendanceReportPdf.htm" ></c:url>
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

			<br /> <br /> <h2>Employee Aggregrate Attendance Report</h2>			
			<form:form  action="${aggregrateMonthlyAttendanceReportPdf}" cssClass="form-horizontal" method="post" commandName="attendanceData" id="attendanceSubmitForm" >	
					
					<form:label for="year" path="year">Select Year :</form:label>
					<form:select path="year">
			           	<form:option value="2015" label="2015" />
			           	<form:option value="2016" label="2016" />
			           	<form:option value="2017" label="2017" />
			           	<form:option value="2018" label="2018" />
			           	<form:option value="2019" label="2019" />
			           	<form:option value="2020" label="2020" />
			        </form:select>
			        			
					<form:label for="month" path="month">Select Month :</form:label>
					<form:select path="month">
			           	<form:option value="1" label="January" />
			           	<form:option value="2" label="February" />
			           	<form:option value="3" label="March" />
			           	<form:option value="4" label="April" />
			           	<form:option value="5" label="May" />
			           	<form:option value="6" label="June" />
			           	<form:option value="7" label="July" />
			           	<form:option value="8" label="August" />
			           	<form:option value="9" label="September" />
			           	<form:option value="10" label="October" />
			           	<form:option value="11" label="November" />
			           	<form:option value="12" label="December" />
			        </form:select>
			
					<input type="submit" value="Submit" class="button" id="insert-attendance"  />
			</form:form>

       </div>
    </body>
</html>
 