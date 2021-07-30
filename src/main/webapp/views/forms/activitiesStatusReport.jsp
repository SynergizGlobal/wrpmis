<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Activities Progress Report - Reports - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
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
								<h6 id="rptName">Activities Status Report</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<form action="<%=request.getContextPath() %>/generate-activities-status-report" id="activitiesReportForm" name="activitiesReportForm" method="post" target="_blank">
						<div class="row">
						<div class="col s12 m12 l7 offset-l2">
							<div class="row no-mar" style="margin-bottom:0;">
								<div class="col s6 m3 l4 input-field"> 
									<p class="searchable_label">Project</p>
									<select class="searchable validate-dropdown" id="project_id" name="project_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>										
									</select> 
									<span id="project_idError" class="error-msg"></span>
								</div>							
								<div class="col s6 m3 l4 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id" name="work_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>	
									</select> 
									<span id="work_idError" class="error-msg"></span>
								</div>	
								<div class="col s6 m3 l4 input-field">
									<p class="searchable_label">Contract</p>
									<select class="searchable validate-dropdown" id="contract_id" name="contract_id" onchange="resetFilterDropDowns();">
										<option value="">Select</option>		
										<c:forEach var="obj" items="${contarctsList }">
	                                      	   <option value= "${obj.contract_id}" >${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
	                                    </c:forEach>
									</select> 
									<span id="contract_idError" class="error-msg"></span>
								</div>	 
							
							    <div class="col s6 m3 l4 input-field" id="fob_id_fk_div" style="display: none;">
									<p class="searchable_label">Structure</p>
									<select class="searchable validate-dropdown" id="fob_id_fk" name="fob_id_fks" multiple="multiple">
										<option value="">Select</option>	
									</select> 
									<span id="fob_id_fkError" class="error-msg"></span>
								</div>			
								<div class="col s6 m3 l4 input-field" id="fmRow" style="display: none;">
									
								</div>
								<div class="col s6 m3 l4 input-field" id="to_date_holder" style="display:none;">
									
								</div>	
							</div>									
							
							<div class="row">	
								<div class="col s6 m6 input-field" style="text-align:right;">
									<button type="submit" class="btn waves-effect waves-light bg-m t-c"><strong>Generate Report </strong></button>
								</div>
								<div class="col s6 m6 input-field" style="text-align:left;">
									<button type="button" class="btn waves-effect waves-light bg-s t-c" onClick="window.location.reload();" >Reset</button>
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


        $(document).ready(function () {
    
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            resetFilterDropDowns();           
        });
      
        function clearFilters(){
       
        	$("#project_id").val('');
        	$("#work_id").val('');
        	$("#contract_id").val('');
        	$("#fob_id_fk").val('');
        	
        	resetFilterDropDowns();
        }
        
        
        function resetFilterDropDowns(){   
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	getProjectsList();
        	getWorksList();
            getContractsList();
            getFobList();
        }
            
      
        function getProjectsList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        
            if ($.trim(project_id) == "") {
            	$("#project_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk};
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInActivitiesStatusReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                            $("#project_id").append('<option value="' + val.project_id + '">' + $.trim(val.project_id) +'-'+ $.trim(val.project_name) + '</option>');
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
        
        function getWorksList(project_id) {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
            if ($.trim(work_id) == "") 
            {
            	$("#work_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk};
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInActivitiesStatusReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                            var workName = '';
	                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
	                            $("#work_id").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
            else
            {
	        	  $(".page-loader").hide();
	        }
        }

        //geting contracts list    
        function getContractsList() {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
            if ($.trim(contract_id) == "") {
            	$("#contract_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id, fob_id_fk : fob_id_fk};
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInActivitiesStatusReport",
	                data: myParams, cache: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	var contract_short_name = '';
	                            if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
	                            $("#contract_id").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_short_name) + '</option>');
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
        
        function getFobList() {
        	$(".page-loader").show();           
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	$("#fob_id_fk option:not(:first)").remove();
            if ($.trim(contract_id) != "" && $.trim(fob_id_fk) == "") {
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk :fob_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getFobFilterListInActivitiesStatusReport",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var fobName = '';
                                if ($.trim(val.fob_name) != '') { fobName = ' - ' + $.trim(val.fob_name) }
                                $("#fob_id_fk").append('<option value="' + val.fob_id + '">' + $.trim(val.fob_id)  + fobName +'</option>');
                            });
                            $("#fob_id_fk_div").show();
                        }else{
                        	$("#fob_id_fk_div").hide();
                        }                     
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
      	   				$(".page-loader").hide();
    	   	         	getErrorMessage(jqXHR, exception);
    	   	     	}
                });
            }else if ($.trim(contract_id) != "" && $.trim(fob_id_fk) != "") {
            	$(".page-loader").hide();
            }else{
            	$(".page-loader").hide();
            	$("#fob_id_fk_div").hide();
            }
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
        
        var validator = $('#activitiesReportForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "work_id": {
				 		required: false
				 	  },"contract_id": {
				 		required: true
				 	  }
			 	},
			   messages: {
				     "work_id": {
			 			required: 'Required'
			 	  	 },"contract_id": {
			 			required: 'Required'
			 	  	 }
			 				      
		    },
			  errorPlacement:
			 	function(error, element){
				    if (element.attr("id") == "contract_id" ){
			 	    	 document.getElementById("contract_idError").innerHTML="";
			 			 error.appendTo('#contract_idError');
			 	    }
			 },invalidHandler: function (form, validator) {
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