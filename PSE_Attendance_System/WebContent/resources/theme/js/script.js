//submit login form 
$(document).on('submit', 'form.ajax-form-login', function() {
	
	// Hide previously shown notify.js notifications - Trick (notify.js automatically hides notifications when clicked)
	//$(".notifyjs-wrapper").click();
	$.ajax({
		url : $(this).attr('action'),
		type : $(this).attr('method'),
		data : $(this).serialize(),
		success : function(data) {
			$(".loginBtn").attr("disabled","true");
			if(data.status == true) {
				$("#body").load(data.url);
				//$.notify(data.message,{position: 'right bottom',className:"success"});
				//$("#loginmsg").html(data.message);
				//$("#logout-div").css("display", "block");
			}else if(data.status == false) {
				$(".loginBtn").removeAttr("disabled");
				$("#error-login-msg").text(data.message);
				//$.notify(data.message,{ position: 'right bottom',className:"error"} );
				//$("#loginmsg").html(data.message);			
			}
		}
	});
	return false;
});

function logout()
{
	$.ajax({
		url : '/PSE_Attendance_System/logout',
		type : 'GET',
		success : function(data) {
			if(data.status == true) {
				$("#body").load(data.url);
			}
		}
	});
}


function pageAniamateScroll(){
	$('body,html').animate({ scrollTop: 400}, 800);
}


/**
 * validate required fields from form
 * @returns {Boolean}
 */
function validateGenericAllRequired()
{

	var flag=true;
	$(".validate-form .validate-required-error").remove();
	
	$(".validate-required").each(function(i){
		
		console.log("---"+$(this).attr('type'));
		if($(this).attr('type') == "radio")
		{
			if($(this).is(':checked') == false)
			{
				$(this).css("border-bottom-color", "red");
				/*$(this).before($("#validate-required-error").clone());
				var $validateRequiredError = $(this).prev() ;
				$validateRequiredError.removeAttr("id");
				$validateRequiredError.attr("class","validate-required-error non-readonly msgerror");
				var $validateRequiredMessage = $(this).attr("validate-required-message");
				if($validateRequiredMessage != undefined){			
					//var $defaultErrorMessage = $validateRequiredError.find(".msgerror");
					$validateRequiredError.html($validateRequiredMessage);
				}	*/	
			
				if(flag){
					flag=false;
				}
				//return false;
			}
			else
			{
				$(this).removeAttr('style');
				flag = true;
				return false;
			}
		}
		else
		{
			if($.trim( $(this).val())=="")
			{
				$(this).css("border-bottom-color", "red");
				$(this).before($("#validate-required-error").clone());
				var $validateRequiredError = $(this).prev() ;
				$validateRequiredError.removeAttr("id");
				$validateRequiredError.attr("class","validate-required-error non-readonly msgerror");
				var $validateRequiredMessage = $(this).attr("validate-required-message");
				if($validateRequiredMessage != undefined){			
					//var $defaultErrorMessage = $validateRequiredError.find(".msgerror");
					$validateRequiredError.html($validateRequiredMessage);
				}		
			
				if(flag){
					flag=false;
				}
			}
			else
			{
				$(this).removeAttr('style');
				//$("div.validate-form").removeAttr('style');

			}
		}
	});
	$(".validate-form .validate-required-error").show();
	return flag;
}

/**
 * 
 * @returns {Boolean}
 */
function validateForm()
{
	var flag = true;
	if( !validateGenericAllRequired())
	{
		if(flag)
		{
			flag=false;
		}
	}
	if (!flag)
		alert('Invalid Data filled. See Error Messages for more Information.');
	return flag;
}

function clearFields(formId)
{
	$("#"+formId+" input:text,#"+formId+" select,#"+formId+" textarea, #"+formId+" input:hidden, #"+formId+" input:password").each(function(){
		 $("#"+$(this).attr('id')).val("");
	});
	
	$("#"+formId+" :radio").each(function () {
		$(this).removeAttr('checked');
		
		$('input[type="radio"]').attr('checked', false);
	});
}

/**
 * remove child elements of html
 */

function remove_child_Elements(id) 
{
    var elem = document.getElementById(id);
    if(elem != null)
    {
    	elem.parentNode.removeChild(elem);
        return false;
    }
}