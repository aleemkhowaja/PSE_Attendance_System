$(document).ready(function() {
    $('.abs-pre').css('display','none');
    $("#studentsImg").hide();
    $("#classImg").hide();
$("#detaiImg").hide();
$("#pr").hide();
$("#searchImg").hide();
 $("#showImg").hide();
 $("#courseImg").hide();
 $("#partImg").hide();
 $("#semesterImg").hide();
 $("#teacherImg").hide();
 $("#courseTitleImg").hide();
 $("#programImg").hide();
 $("#insertProc").hide();
 $("#procText").hide();
  $("#done").hide(); 
 $('#courseTitle').prop('disabled', true);
	showSelective();
    settingDate()
});

function showSelective(){
	$('#sel').change(function() {
        if($(this).is(":checked")) {
			
			 $('.abs-pre').css('display','block');
                           $("#students *").attr('disabled',true);
			 $('#msg').css('display','block')
		}
		else{
                         $("#students *").attr('disabled',false);
			 $('.abs-pre').css('display','none');
			 $('#msg').css('display','none')
			}
			});
	}
function settingDate(){
	$( "#datepicker" ).datepicker({
	
	inline: true,
	
	
	 altField: "#date",
	 
    // The format you want
    dateFormat: "yy-mm-dd",
    // The format the user actually sees
    altFormat: "dd/mm/yy",
	firstDay:1,
	//maxDate: "0d",
	//minDate:"-1d"
	beforeShowDay: $.datepicker.noWeekends,
	onSelect: function(date) {	
	var dateText = $.datepicker.formatDate("dd/mm/yy", $(this).datepicker("getDate"));    
		 var shiftId = $("input:radio[name='shift']:checked").val();
		 var departmentId = $("#departments").val();
		 var date = $("#date").val();
		 if(shiftId=="" || departmentId=="" || date=="")return
		 else{
              $("#teacherImg").show();
               $("#classImg").show();
			  $.ajax({url:"showClasses.php",data:{shiftId: shiftId,departmentId: departmentId,date:date.toString()}}) 
            .done(function(data){
               $("#classes").html(data);
                $("#classImg").hide();
             classId= 0;
             groupId = 0;
             sectionId = 0;
                 $("#studentsImg").show();
               $.ajax({url:"studentListShow.php",data:{date:date.toString(),classId: classId,group:groupId,section:sectionId}}) 
                         .done(function(data){
                        $("#students").html(data);
                          $("#studentsImg").hide();
                        }).fail(function(){
                          $("#studentsImg").hide();
                        alert("not connected");}); 
                    
                $.ajax({url:"showCourses.php",data:{date:date.toString(),classId: classId,groupId:groupId,sectionId:sectionId}}) 
                          .done(function(data){
                          $("#course").html(data);
                          $("#courseImg").hide();
                          }).fail(function(){
                            $("#courseImg").hide();
                           alert("not connected");});
                       
			    $.ajax({url:"showTeacher.php",data:{departmentId: departmentId}}) 
            .done(function(data){
               $("#teacher").html(data);
                $("#teacherImg").hide();
            }).fail(function(){
                 $("#teacherImg").hide();
                $("#classImg").hide();
                alert("not connected");});   

            }).fail(function(){
             $("#teacherImg").hide();
             $("#classImg").hide();
        alert("not connected");});   

		}
        
    
	}
	
});
	}