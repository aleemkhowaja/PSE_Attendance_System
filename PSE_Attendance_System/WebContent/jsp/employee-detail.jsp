<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="<c:url value="/resources/theme/css/bootstrap.min.css"/>" />
<script src="<c:url value="/resources/theme/js/bootstrap.min.js" /> "></script>

<c:url var="saveEmployee" value="/employee/save.htm" ></c:url>
<c:url var="returnAllDepartmentsForCombo" value="/returnAllDepartments/" ></c:url>
<c:url var="returnAllLocationsForCombo" value="/returnAllLocations/" ></c:url>
  		
<style>

select {
    color: black;
}

</style>
<h2>Users</h2>
<form:form servletRelativeAction="${saveEmployee}" cssClass="form-horizontal" method="post" commandName="employeeData" id="employeeSubmitForm"  resolveContext="true" resolveMapping="true">
	<div class="form-group">
		<form:label cssClass="control-label col-xs-2" path="location"> Location :</form:label>
        <div class="col-xs-10"> 						
  			<form:select  cssClass="form-control validate[required]" id="location" path="location">
	 			<form:option value="" label="Select Location" />
	 			<form:options items="${locationList}" itemValue="locationId" itemLabel="locationName"   />
	 		</form:select>
        </div>
    </div>
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2"  path="department"> Department :</form:label>
    	<div class="col-xs-10">
        	<form:select cssClass="form-control validate[required]" id="dapartment" path="department" >
	 			<form:option value="" label="Select Department" />
	 			<form:options items="${departmentList}" itemValue="departmentId" itemLabel="departmentName"   />
	 		</form:select>
 		</div>
 	</div>
 	
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2 validate[required]" path="employeeName"> Name :</form:label>
        <div class="col-xs-10">
        	<form:input cssClass="form-control" id="empName" placeholder="Employee Name" path = "employeeName" />
        </div>
    </div>
    			
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="fathersName"> Father Name :</form:label>
       	<div class="col-xs-10">
       		<form:input cssClass="form-control" id="fatherName" placeholder="Father Name" path = "fathersName" />
       	</div>
    </div>
	
	<div class="form-group">
		<form:label cssClass="control-label col-xs-2" path="mobileNo"> Mobile No :</form:label>
    	<div class="col-xs-10">
    		<form:input cssClass="form-control" id="mobileNo" placeholder="Mobile No" path = "mobileNo" />
        </div>
    </div>
    
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="registrationNo"> Registration No :</form:label>
        <div class="col-xs-10">
        	<form:input cssClass="form-control" id="registrationNo" placeholder="Registration No" path = "registrationNo" />
        </div>
    </div>
    			
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="address">Present Address :</form:label>
        <div class="col-xs-10">
        	<form:input cssClass="form-control" id="address" placeholder="Present Address" path="address" />
        </div>
    </div>
    
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="username">Username :</form:label>
    	<div class="col-xs-10">
        	<form:input cssClass="form-control" id="username" placeholder="Usernames" path="username" />
        </div>
    </div>
    
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="password">Password :</form:label>
        <div class="col-xs-10">
        	<form:password cssClass="form-control" id="password" placeholder="Password" path="password" />
        </div>
    </div>
    
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="isActive">Is Active :</form:label>
        <div class="col-xs-1">
        	<form:checkbox cssClass="form-control" id="isActive" placeholder="isActive" path="isActive" />
        </div>
    </div>
    			
	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<input type="submit" value="Save" class="btn" id="employee-save-btn"></input>
		</div>
	</div>
			    
	<form:hidden path="employeeId"  />
</form:form>