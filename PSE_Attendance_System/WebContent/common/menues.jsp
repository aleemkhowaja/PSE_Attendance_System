<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li style="margin-left: 90%;">
	<a href="/PSE_Attendance_System/logout" >Logout</a></li>
</li>
<div class="menu-wrap">
    <nav class="menu">
        <ul class="clearfix">
            <li>
                <a href="#">Attendance <span class="arrow">&#9660;</span></a>
                <ul class="sub-menu">
                    <li><a href="/PSE_Attendance_System/attendance/insert-attendance.htm">Insert Attendance</a></li>
                </ul>
            </li>
            <c:if test="${employee.role == 'admin'}">
            	<li>
	                <a href="#">User <span class="arrow">&#9660;</span></a>
	                <ul class="sub-menu">
	                    <li><a href="/PSE_Attendance_System/attendance/search-employees.htm">Users</a></li>
	                </ul>
            	</li>
            	<li>
	                <a href="#">Holidays <span class="arrow">&#9660;</span></a>
	                <ul class="sub-menu">
	                    <li><a href="/PSE_Attendance_System/holidays/search-holiday.htm">Holidays</a></li>
	                </ul>
            	</li>
            	<li>
	                <a href="#">Reports <span class="arrow">&#9660;</span></a>
	                <ul class="sub-menu">
	                    <li><a href="/PSE_Attendance_System/attendance/aggregrate-monthly-attendance-report.htm">Attendance Report</a></li>
	                </ul>
            	</li>
            </c:if>
        </ul>
    </nav>
    
</div>
