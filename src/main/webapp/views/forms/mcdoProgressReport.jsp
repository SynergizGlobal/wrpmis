<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MCDO Progress report - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
	<style>
		.input-field .searchable_label {
			font-size:0.9rem;
		}
		.error-msg label{
			color:red!important;
		}	
		.input-field .center-align.m-1 button.bg-m, 
		.input-field .center-align.m-1 button.bg-s{
			width:inherit !important;
		}
	</style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m m-b-2">
								<h6 id="rptName">MCDO Progress report</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<form action="<%=request.getContextPath() %>/generate-mcdo-progress-report" id="mcdoProgressReportForm" name="mcdoProgressReportForm" method="post" target="_blank">
						<div class="row">
						<div class="col s12 m12 l7 offset-l2">
							<div class="row no-mar" style="margin-bottom:0;">
								<div class="col s6 m3 l4 input-field"> 
									<p class="searchable_label">Project</p>
									<select class="searchable validate-dropdown" id="project_id" name="project_id" onchange="addInQueProject(this.value);getWorksListForMCDOProgressReport();" multiple>
										<option value="">Select</option>		
								
									</select> 
									<span id="project_idError" class="error-msg"></span>
								</div>							
								<div class="col s6 m3 l4 input-field">
									<p class="searchable_label">Work<span class="required">*</span></p>
									<select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);" multiple>
										<option value="">Select</option>
	
									</select> 
									<span id="work_id_fkError" class="error-msg"></span>
								</div>	
							</div>									
							<div class="row">
								<div class="col s6 m3 l4 input-field">
									<input id="from_date" name="from_date" type="text" class="validate datepicker"> <label for="from_date"> From Date <span class="required">*</span></label>
									<button type="button" id="from_date_icon"><i class="fa fa-calendar"></i></button>
									<span id="from_dateError" class="error-msg"></span>
								</div>
								<div class="col s6 m3 l4 input-field">
									<input id="to_date" name="to_date" type="text" class="validate datepicker"> <label for="to_date"> To Date<span class="required">*</span></label>
									<button type="button" id="to_date_icon" ><i class="fa fa-calendar"></i></button>
									<span id="to_dateError" class="error-msg"></span>
								</div>							
							</div>							
							<div class="row">	
								<div class="col s7 m6 input-field" style="text-align:right;">
									<button type="submit" class="btn waves-effect waves-light bg-m t-c"><strong>Generate Report </strong></button>
								</div>
								<div class="col s4 m6 input-field" style="text-align:left;">
									<button type="button" class="btn waves-effect waves-light bg-s t-c" onClick="clearFilters();" >Reset</button>
								</div>								
							</div>

						</div>
						</div>
					</form>
					<!-- form ends  -->
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<p>reports goes here </p>
				</div>
			</div>
		</div>
	</div> -->



	<!-- Page Loader -->
	<div class="page-loader" style="display: none;">
		<div class="preloader-wrapper big active">
			<div class="spinner-layer spinner-blue-only">
				<div class="circle-clipper left">
					<div class="circle"></div>
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script>

	 var filtersMap = new Object();
        $(document).ready(function () {
        	
       	 	getWorksList('');
       	 	getProjectsList('');      	
    
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
			var filters = window.localStorage.getItem("mcdoProgressReportFilters");
			            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'project_id' ){
    		        		  getProjectsList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getWorksList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            getMCDOProgressList();  
           
        });
        
        
        
        $('#from_date').datepicker({                   
	    	  maxDate: new Date(),
	    	  format:'dd-mm-yyyy',
	    	  //perform click event on done button
	    	  onSelect: function () {
	    	     $('.confirmation-btns .datepicker-done').click();
	    	  }
	        });
      
      $('#to_date').datepicker({                   
	    	  maxDate: new Date(),
	    	  format:'dd-mm-yyyy',
	    	  //perform click event on done button
	    	  onSelect: function () {
	    	     $('.confirmation-btns .datepicker-done').click();
	    	  }
	        });       
      
        function clearFilters(){
       
        	$("#project_id").val('');
        	$("#work_id_fk").val('');
        	$("#contract_id_fk").val('');
        	window.localStorage.setItem("mcdoProgressReportFilters",'');
        	window.location.href= "<%=request.getContextPath()%>/mcdo-progress-report";
        }
        function addInQueWork(work_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
          	}
        } 
        function addInQueProject(project_id){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('project_id')) delete filtersMap[key];
       		});
        	if($.trim(project_id) != ''){
       	    	filtersMap["project_id"] = project_id;
        	}
        }
        
      
        function getMCDOProgressList(){
       	 	getWorksList('');
       	 	getProjectsList('');
            
       	 	
            var work_id_fk = $("#work_id_fk").val();
            var project_id_fk = $("#project_id_fk").val();
          	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("mcdoProgressReportFilters", filters);
    			});
       }
        function getProjectsList(project) {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id_fk = $("#work_id_fk").val();
        	
        	//if(contract_id_fk == ""){$("#structure_type_fk_div").hide();}
            if ($.trim(project_id) == "") {
            	$("#project_id option:not(:first)").remove();
            	var myParams = {};
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectListForActivitiesExportReportForm",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (project == val.project_id)?'selected':'';
	                            $("#project_id").append('<option value="' + val.project_id + '"'+selectedFlag+'>' + $.trim(val.project_id) +'-'+ $.trim(val.project_name) + '</option>');
	                        });
	                    }
	                    $('.searchable').select2();
	                    $(".page-loader").hide();
	                },error: function (jqXHR, exception) {
	 	   			    $(".page-loader").hide();
		   	          	getErrorMessage(jqXHR, exception);
		   	     	}
	            });
            }else{
	        	  $(".page-loader").hide();
	        }
        }
        
        function getWorksList(work) {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id_fk = $("#work_id_fk").val();
        	 
          if ($.trim(work_id_fk) == "") {
           	$("#work_id_fk option:not(:first)").remove();
           	var myParams = {project_id : project_id,work_id_fk : work_id_fk};
               $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksListForActivitiesExportReportForm",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                            var selectedFlag = (work == val.work_id_fk)?'selected':'';
                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			    $(".page-loader").hide();
	   	          	getErrorMessage(jqXHR, exception);
	   	     	}
            });
          }else{
        	  $(".page-loader").hide();
        }
        }
        
        function getWorksListForMCDOProgressReport() {
        	//$(".page-loader").show();
        	var project_id = $("#project_id").val();
           	var myParams = {project_id:encodeURIComponent(project_id)};
        	$("#work_id_fk option:not(:first)").remove();
               $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksListForMCDOProgressReport",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			    $(".page-loader").hide();
	   	          	getErrorMessage(jqXHR, exception);
	   	     	}
            });
          
        }       
        
        
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = ''
        		var workId = $("#work_id_fk").val();
            	var projectId = workId.substring(0, 3);    
       			$("#project_id").val(projectId);
       			$("#project_id").select2();
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		
       		$(".page-loader").hide();
        }
        
        function resetProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
       		var work_id_fk = $("#work_id_fk").val();
       		if($.trim(work_id_fk) != ''){  
            	projectId = work_id_fk.substring(0, 3);    
       			$("#project_id").val(projectId);
       			$("#project_id").select2();
       		}
       		$(".page-loader").hide();
        	
        }
    
      	//This function is used to get error message for all ajax calls
        function getErrorMessage(jqXHR, exception) {
        	    var msg = '';
        	    if (jqXHR.status === 0) {
        	        msg = 'Not connect.\n Verify Network.';
        	    } else if (jqXHR.status == 404) {
        	        msg = 'Requested page not found. [404]';
        	    } else if (jqXHR.status == 500) {
        	        msg = 'Internal Server Error [500].';
        	    } else if (exception === 'parsererror') {
        	        msg = 'Requested JSON parse failed.';
        	    } else if (exception === 'timeout') {
        	        msg = 'Time out error.';
        	    } else if (exception === 'abort') {
        	        msg = 'Ajax request aborted.';
        	    } else {
        	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        	    }
        	    console.log(msg);
         }
        
        var validator = $('#mcdoProgressReportForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "work_id_fk": {
				 		required: true
				 	  },"from_date": {
				 		required: true
				 	  }, "to_date": {
				 		required: true
				 	  },
			 	},
			   messages: {
				     "work_id_fk": {
			 			required: 'Required'
			 	  	 }, "from_date": {
			 			required: 'Required'
			 	  	 }, "to_date": {
			 			required: 'Required'
			 	  	 }
			 				      
		    },errorPlacement:function(error, element){
  		 	         if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "from_date" ){
			 	    	 document.getElementById("from_dateError").innerHTML="";
			 			 error.appendTo('#from_dateError');
			 	    }else if (element.attr("id") == "to_date" ){
   			 	    	 document.getElementById("to_dateError").innerHTML="";
			 			 error.appendTo('to_dateError');
		 	    	}
	    	 },
			 invalidHandler: function (form, validator) {
                 var errors = validator.numberOfInvalids();
                 if (errors) {
                     var position = validator.errorList[0].element;
                     jQuery('html, body').animate({
                         scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                     }, 1000);
                 }
             },submitHandler: function(form) {
			    // do other things for a valid form
			    form.submit();
			    //return true;
			  }
		});
 
        $('select').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
        
        $('input').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
    </script>
</body>
</html>