<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk Analysis Report</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/mobile-form-template.css">
    <style>
 		.input-field .searchable_label {
            font-size: 0.9rem;
        }
         .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}		
		.preloader-wrapper{top: 45%!important;left:47%!important;}		
		.error-msg label{color:red!important;}
    </style>
</head>

<body>
    <!-- header included -->
    
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 ">
                            <h6>Risk Analysis Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12">
                            	<form action="<%=request.getContextPath() %>/generate-risk-analysis-report" id="reportForm" name="reportForm" method="post">
	                                <div class="row no-mar">
	                                    <div class="col s6 m4 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="report_work_id" name="work_id" onchange="getAssessmentDateListInRiskReport(this.value);">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="report_work_idError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 input-field">
	                                        <p class="searchable_label">Assessment Date</p>
	                                        <select class="searchable validate-dropdown" id="report_assessment_date" name="assessment_date">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="report_assessment_dateError" class="error-msg" ></span>
	                                    </div>
	
	                                    <div class="col s12 m4 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px;width: 100%; font-weight: 600;"
	                                            onclick="generateReport()">Generate
	                                            Report</button>
	                                    </div>
	                                </div>
                                
                                </form>
                            </div>

                            <div class="col m2 hide-on-small-only"></div>
                        </div>
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
        	getWorksListInRiskReport();
        });
        
        function getWorksListInRiskReport() {
        	$(".page-loader").show();
           	$("#report_work_id option:not(:first)").remove();
           	var myParams = {}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getWorksListInRiskReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                           	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
   	                         $("#report_work_id").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
        
        
        function getAssessmentDateListInRiskReport(work_id) {
        	$(".page-loader").show();
           	$("#report_assessment_date option:not(:first)").remove();
           	var myParams = {work_id : work_id}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getAssessmentDateListInRiskReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
								$("#report_assessment_date").append('<option value="' + $.trim(val.assessment_date) + '">' + $.trim(val.assessment_date)+'</option>');
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
        	//$(".page-loader").show();
        	$("#reportForm").submit();
		}
        
        
        var validator =	$('#reportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "work_id": {
	  			 		required: true
	  			 	  },"assessment_date": {
	  			 		required: true
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "work_id": {
	  				 	required: 'This field is required',
	  			 	  },"assessment_date": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "report_work_id" ){
						 document.getElementById("report_work_idError").innerHTML="";
				 		 error.appendTo('#report_work_idError');
					} else if(element.attr("id") == "report_assessment_date" ){
						   document.getElementById("report_assessment_dateError").innerHTML="";
					 	   error.appendTo('#report_assessment_dateError');
					} else{
	 					error.insertAfter(element);
			       }
                }
		   		,submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        

    </script>

</body>

</html>