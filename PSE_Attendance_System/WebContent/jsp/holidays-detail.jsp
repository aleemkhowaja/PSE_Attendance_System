<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="<c:url value="/resources/theme/css/bootstrap.min.css"/>" />
<script src="<c:url value="/resources/theme/js/bootstrap.min.js" /> "></script>

<c:url var="saveHoliday" value="/holidays/save.htm" ></c:url>
<link href="<c:url value="/resources/theme/css/attendance.css" />" rel="stylesheet" />
  		
<style>

select {
    color: black;
}

</style>
<form:form servletRelativeAction="${saveHoliday}" cssClass="form-horizontal" method="post" commandName="holidayData" id="holidaySubmitForm"  resolveContext="true" resolveMapping="true">

	<h3><legend><span class="number"></span>Holidays</legend></h3>

		 		
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2 validate[required]" path="holiday"> Date :</form:label>
        <div class="col-xs-10">
        	<form:input required="true" cssClass="form-control datepicker" path="holiday" id="holidayDate" placeholder="Date" />
        </div>
    </div>
    			
    <div class="form-group">
    	<form:label cssClass="control-label col-xs-2" path="description">Remarks :</form:label>
        <div class="col-xs-10">
        	<form:input required="true"  cssClass="form-control" id="holiday_Description" placeholder="description" path="description" />
        </div>
    </div>
    			
	<form:hidden id="holiday_id" path="holidayId"  />
	
	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<input  type="submit" id="holiday_save" value="Save" class="btn"></input>
			<input style="display:none;" type="button" id="holiday_delete" value="Delete" onclick="holiday_Crud('','Delete','');" class="btn"></input>
		</div>
	</div>
	
	
</form:form>