<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Training Report - Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css">
      <style>              
		.mb-20{
			margin-bottom:20px;
		}	          
		.error-msg label{color:red!important;}

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
                            <h6>Training Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m8 l5 s12 offset-m2 offset-l4">
                            	<form action="<%=request.getContextPath() %>/generate-scheduled-training-report" id="scheduledTrainingReportForm" name="scheduledTrainingReportForm" method="post" target="_blank">
	                                <div class="row no-mar">
	                                    <div class="col s12 m6 input-field">
	                                        <p class="searchable_label" style="text-align:left">Training Title</p>
	                                        <select class="searchable validate-dropdown" name="title">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${scheduledTrainingTitles }">
	                                            	<option value="${obj.title }">${obj.title } </option>
	                                            </c:forEach>
	                                        </select>
	                                        <!-- <span id="titleError" class="error-msg" ></span> -->
	                                    </div>
	
	                                    <div class="col s12 m6 input-field mob-center">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateScheduledTrainingReport()"> Scheduled Training</button>
	                                    </div>
	                                </div>                                
                                </form>
                                <div class="hide-on-med-and-up grey divider mb-20"></div>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m8 l5 s12 offset-m2 offset-l4">
                            	<form action="<%=request.getContextPath() %>/generate-employee-training-report" id="employeeTrainingReportForm" name="employeeTrainingReportForm" method="post" target="_blank">
	                                <div class="row no-mar">
	                                    <div class="col s12 m6 input-field">
	                                        <p class="searchable_label" style="text-align:left">Employee</p>
	                                        <select class="searchable validate-dropdown" id="attendee" name="attendee">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${employees }">
	                                            	<option value="${obj.attendee }">${obj.attendee } </option>
	                                            </c:forEach>
	                                        </select>
	                                        <span id="attendeeError" class="error-msg" ></span>
	                                    </div>
	
	                                    <div class="col s12 m6 input-field mob-center">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px;font-weight: 600;"
	                                            onclick="generateEmployeeTrainingReport()"> Employee Training</button>
	                                    </div>
	                                </div>                                
                                </form>
                                <div class="hide-on-med-and-up grey divider mb-20"></div>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m8 l5 s12 offset-m2 offset-l4">
                            	<form action="<%=request.getContextPath() %>/generate-completed-training-report" id="completedTrainingReportForm" name="completedTrainingReportForm" method="post" target="_blank">
	                                <div class="row no-mar">
	                                    <div class="col s12 m6 input-field">
	                                        <p class="searchable_label" style="text-align:left">Training Title</p>
	                                        <select class="searchable validate-dropdown" name="title">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${completedTrainingTitles }">
	                                            	<option value="${obj.title }">${obj.title } </option>
	                                            </c:forEach>
	                                        </select>
	                                        <!-- <span id="attendeeError" class="error-msg" ></span> -->
	                                    </div>
	
	                                    <div class="col s12 m6 input-field mob-center">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateCompletedTrainingReport()"> Completed Training </button>
	                                    </div>
	                                </div>                                
                                </form>
                            </div>
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
        $(document).ready(function(){
        	$('.searchable').select2();
        });
        
        function generateScheduledTrainingReport() {
        	//$(".page-loader").show();
        	$("#scheduledTrainingReportForm").submit();
		}
        
        function generateEmployeeTrainingReport() {
        	//$(".page-loader").show();
        	$("#employeeTrainingReportForm").submit();
		}
        
        function generateCompletedTrainingReport() {
        	//$(".page-loader").show();
        	$("#completedTrainingReportForm").submit();
		}
        
        
        var validator =	$('#employeeTrainingReportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "attendee": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "attendee": {
	  				 	required: 'This field is required',
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "attendee" ){
						 document.getElementById("attendeeError").innerHTML="";
				 		 error.appendTo('#attendeeError');
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