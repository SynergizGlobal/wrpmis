<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resource Report - Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
      <style>            		
		.error-msg label{color:red!important;} 
		
		@media only screen and (min-width:787px){
			.pt-md-5{
				padding-top:5px !important;
			}
		}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
 
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Resource Report </h6>
                        </div>
                    </span>
                    <div class="">
			            <form action="<%=request.getContextPath() %>/generate-resource_report" id="reportForm" name="reportForm" method="post">	                              
                       		 <div class="row no-mar">
                                <div class="col s6 m4 l2 input-field offset-l3">
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project" name="project" onchange="getResourceReport(this.value);">
                                        <option value="">Select </option>
                                    </select>
                                    <span id="projectError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Work</p>
                                    <select class="searchable validate-dropdown" id="sub_work" name="sub_work" onchange="getResourceReport(this.value);">
                                        <option value="">Select </option>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Contract</p>
                                    <select class="searchable validate-dropdown" id="contract" name="contract" onchange="getResourceReport(this.value);">
                                        <option value="">Select </option>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
                                </div>
                                </div>
                           <div class="row">
                                <div class="col s6 m4 l2 input-field offset-l3">                                    
                                    <input id="deployment_from_date" type="text" name="deployment_from_date" class="validate datepicker">
                                    <label for="deployment_from_date" class="fs-sm-8rem">Deployment From Date</label>
                                    <button type="button" id="deployment_from_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="deployment_from_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">                                    
                                    <input id="deployment_to_date" type="text" name="deployment_to_date" class="validate datepicker">
                                    <label for="deployment_to_date" class="fs-sm-8rem">Deployment To Date</label>
                                    <button type="button" id="deployment_to_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="deployment_to_dateError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">	                                	
                                <div class="col s12 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button class="btn bg-s waves-effect waves-light t-c" type="button"
                                        style="margin-top: 6px; font-weight: 600; min-width:160px"
                                        onclick="clearFilter()">Clear Filter</button>
                                </div>
                                <div class="col s12 m4 l3 input-field center-align">
                                    <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px%; font-weight: 600;"
                                        onclick="generateReport()">Generate Report</button>
                                </div>
                             </div>
                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         
    <div class="page-loader" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
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
                
        $(document).ready(function(){
			$('.searchable').select2();
		});    
        
        function getResourceReport(sub_work) {
        	$(".page-loader").show();
        	var work_id = $("#report_work_id").val();
           	$("#deployment_from_date option:not(:first)").remove();
           	var myParams = {work_id : work_id,sub_work : sub_work}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getResourceReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
								$("#deployment_from_date").append('<option value="' + $.trim(val.assessment_date) + '">' + $.trim(val.assessment_date)+'</option>');
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
        
        function generateReport() {
        	$("#reportForm").submit();
		}
        
        
        var validator =	$('#reportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "sub_work": {
	  			 		required: true
	  			 	  }	,"assessment_date": {
	  			 		required: true
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "sub_work": {
	  			 		required: ' This field is required'
	  			 	  },"assessment_date": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if(element.attr("id") == "sub_work" ){
						   document.getElementById("projectError").innerHTML="";
					 	   error.appendTo('#projectError');
					} else if(element.attr("id") == "deployment_from_date" ){
						   document.getElementById("deployment_from_dateError").innerHTML="";
					 	   error.appendTo('#deployment_from_dateError');
					} else{
	 					error.insertAfter(element);
			       }
                }
		   		,submitHandler:function(form){
			    	form.submit();
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

        function clearFilter(){
    		$('#project').val('');
    		$('#sub_work').val('');
    		$('#contract').val('');
    		$('#deployment_from_date').val('');
    		$('#deployment_to_date').val('');
    		$('.searchable').select2();
    	}
    </script>

</body>

</html>