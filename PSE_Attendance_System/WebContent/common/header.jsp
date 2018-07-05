<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<head>
	
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <c:url var="home" value="/" scope="request" />
				
		<link href="<c:url value="/resources/theme/css/head-foot.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/theme/css/metro/green/jtable.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/theme/css/jtable_metro_base.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/theme/css/jquery-ui.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/theme/css/jquery.timepicker.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/theme/css/validationEngine.jquery.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/theme/css/menu.css" />" rel="stylesheet">
		<link href="<c:url value="/http://fonts.googleapis.com/css?family=Ek+Mukta" />" rel="stylesheet">
		<link href="<c:url value="/resources/theme/css/toast/iziToast.min.css" />" rel="stylesheet">
		
		<script src="<c:url value="/resources/theme/js/jquery-1.10.2.js" />"></script>
		<script src="<c:url value="/resources/theme/js/jquery-ui.js" />"></script>
		<script src="<c:url value="/resources/theme/js/style.js" />"></script>
		<script src="<c:url value="/resources/theme/js/jquery.timepicker.min.js" />"></script>
		<script src="<c:url value="/resources/theme/js/toast/iziToast.min.js" />"></script>
		
		<script  src="<c:url value="/resources/theme/js/jquery.jtable.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/underscore.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/jquery.flot.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/notify.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/d3.v2.min.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/d3.tip.v0.6.3.js" />" > </script>
		
		<script  src="<c:url value="/resources/theme/js/employee.js" />" > </script>
		<script type="text/javascript" src="<c:url value="/resources/theme/js/script.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/jquery.validationEngine.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/jquery.validationEngine-en.js" />" > </script>
		<script  src="<c:url value="/resources/theme/js/date.js" />" > </script>
		
		<link href="<c:url value="/resources/theme/css/style.css" />" rel="stylesheet" />
		
		<script>
			$( function() {
	    		$('.datepicker').datepicker({
	    		    dateFormat: 'dd-MM-yy',
	    		    showButtonPanel: true,
	    	        changeMonth: true,
	    		});
	  		} );
  		</script>
		
	</head>
	
	<div class="header">
		<div class="logo">
	    	<img src="/PSE_Attendance_System/resources/theme/images/path1.png" height="100" width="100" style="width: 54%;" />
		</div>
		<div class="logo">
			<h2>Employee Attendance System</h2>
		    <p><strong>Path Solution</strong></p>
		</div>
		
		<label id="validate-required-error" style="display: none;" class="non-readonly" >Field is required.</label>
		<label id="validate-regex-error" style="display: none;" class="non-readonly" >Please Enter Valid Value.</label>
  	</div>