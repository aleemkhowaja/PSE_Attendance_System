//call this function after cliccking the submit button
$(document).on('submit', '#table-form', function(event) {
	$.ajax({
		url :$(this).attr('action'),
		type : $(this).attr('method'),
		data : $(this).serialize(),
		async:false,
		success : function(data) {
			if(data.status == true) {
				$.notify(data.message,{position: 'right bottom',className:"success"});
				$("#centercontent").load(data.url);
			}else {
				$.notify(data.message,{ position: 'right bottom',className:"error"} );
				$("#loginmsg").html(data.message);			
			}
		}
	});
	return false;
});

//table screen crud function
function tableCrud(id,mode,url){
	
	if(mode =='Add'){
		$("#centercontent").load("jsp/table-detail.jsp",function(){					
			$("#tableHeader").html("Add Table");
			$("#mode").val(mode);
			validateTextRange();
			validateIntegerValues();
			validateIntegerRange();
			validateFloatingValues();
			$("#cancelButton").attr("onclick",'setContent("jsp/search-tables.jsp")');
		});//end of callback function					
	}
	else if (mode == 'Update') {///start of update mode if
		showTableEditForm(id,url,mode);
	} else if (mode == 'Delete') {///start of Delete mode if
			$.post(url, {
				tableId : id,
			}, function(result) {
				$("#centercontent").load("jsp/search-tables.jsp");
			});//end of $.post() function
	}///End of Delete mode if
}

//show edit form for update values
function showTableEditForm(id,url,mode) {
	
	$.ajax({
		url:url,
		async:false,
		type:"POST",
		data:{		
			mode:mode,
			tableId:id,
		},
		success:function(result) {
			$("#centercontent").load("jsp/table-detail.jsp",function(){
				$("#cancelButton").attr("onclick",'setContent("jsp/search-tables.jsp")');
				$("#tableHeader").html("Update Table");
				var data = result.model;
				$("#tableName").val(data.tableName);
				$("#noOfColumns").val(data.noOfColumns);
				$("#size").val(data.size);
				$("#mode").val("Update");
				$("#tableId").val(id);
			});
		}
	});
}