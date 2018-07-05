<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<jsp:include page="../common/header.jsp" />
	<body>
		<!-------------------- crud urls  ---------------------------->
		<c:url var="returnAllHolidaysForGrid" value="/holiday/returnAllHolidaysForGrid/" ></c:url>
		<c:url var="addHoliday" value="/holiday/save.htm/" ></c:url>
		<c:url var="updateHoliday" value="/holiday/updateHoliday/" ></c:url>
		<c:url var="deleteHoliday" value="/holiday/deleteHoliday/" ></c:url>
		<c:url var="clearHolidayForm" value="/clear" ></c:url>
		<!-- ------------------------------------------------------ -->
		
		<script  src="<c:url value="/resources/theme/js/holiday.js" />" > </script>
		<div class="body">
		    
			<jsp:include page="../common/menues.jsp" />
			<br /> <br /><h2>Holidays</h2>
			<div class="advance_search">
				<div class="advance_search_heading">
					<p>Search</p>
				</div>
				<div class="advance_search_input">
					<form action="" method="post" class="ajaxForm stdform" id="table-form">
						<div class="adv_ser_row">
							<span> 
								<input class="smallinput datepicker" type="text" name="	 " id="searchHolidayDate" placeholder="Date" />
							</span>
							<input class="search_images search-button" type="submit" id="search-holiday" value="Search" />
						</div>
					</form>
				</div>
			</div>
			<!-- <div id="centercontent" class="content"> -->
				<div id="holiday-detail-grid" class="grid-container"></div>
				<div style="clear: both;"></div>

				<script type="text/javascript">
				$(document).ready(function(){
			        $('#holiday-detail-grid').jtable({
			                title :'Holidays List',
			                paging : true,
							pageSize : 25,
							sorting : true,
			                actions :{
			                        listAction:'${returnAllHolidaysForGrid}',
			                },
							recordsLoaded: function(event, data) {
							        $('.jtable-data-row').click(function() {
							        	holiday_Crud($(this).find("#holidayId").val(),'Update','');
							        });
							 },
			                fields :{
			                	holidayId: {
			                        key: true,
			                        create: false,
			                        edit: false,
			                        list: false
			                        
			                    },
			                    holiday:{
			                    	title :'Holiday',
			                        width :'30%',
			                        display : function(data) {
										return  data.record.holiday+'<input type="hidden"  id="holidayId" value="'+data.record.holidayId+'" > ';
									} 
			                   },
			                   description :{
			                   		title :'Description',
			                    	width :'20%',
			                   }
			                },
			                
			              //Initialize validation logic when a form is created
			                formCreated: function (event, data) {
			                    data.form.find('select[name="holiday"]').addClass('validate[required]');
			                    data.form.find('select[name="description"]').addClass('validate[required]');
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
				    $('#search-holiday').click(function (e) {
				    	e.preventDefault();
				        $('#holiday-detail-grid').jtable('load', {
				        	holiday: $('#searchHolidayDate').val()
				        });
				  	});

			      $('#holiday-detail-grid').jtable('load');
			});

				</script>
				
				
				
				<jsp:include page="holidays-detail.jsp" />
		    <jsp:include page="../common/footer.jsp" />
	</body>
</html>
