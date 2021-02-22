<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Training Report</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/training.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child,
        td:last-of-type {
            white-space: inherit;
        }

        .last-column a+a {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .tabs .tab a:not(.active) {
            color: #999999;
        }

        .tabs .tab a:focus.active {
            background-color: #e5e5e5;
        }

        .tabs .tab a.active {
            color: #444;
            background-color: #dadada;
            border-bottom: 1px solid #444;
        }

        .tabs .indicator {
            background-color: #888;
        }

        .btn.disabled,
        .btn.disabled * {
            color: #999 !important;
        }

        @media only screen and (max-width: 700px) {
            .mt-md-54 {
                margin-top: inherit;
            }
        }
       
        td {
        	word-break: break-word;
    		white-space: initial;
		}
		.fw-120{
        	width:120px !important;
        	max-width:120px;
        }
		.fw-200{
        	width:200px;
        	max-width:200px;
        }
        .fw-250{
        	width:250px;
        	max-width:250px;
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
		.label-for-report{
			text-align:left ;
			line-height: 50px;
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
                            <h6>Training Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m6 s12">
                            	<form action="<%=request.getContextPath() %>/generate-scheduled-training-report" id="scheduledTrainingReportForm" name="scheduledTrainingReportForm" method="post" target="_blank">
	                                <div class="row">
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
	
	                                    <div class="col s12 m6 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px;width: 100%; font-weight: 600;"
	                                            onclick="generateScheduledTrainingReport()"> Scheduled Training</button>
	                                    </div>
	                                </div>                                
                                </form>
                            </div>
                            <div class="col m3 hide-on-small-only"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m6 s12">
                            	<form action="<%=request.getContextPath() %>/generate-employee-training-report" id="employeeTrainingReportForm" name="employeeTrainingReportForm" method="post" target="_blank">
	                                <div class="row">
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
	
	                                    <div class="col s12 m6 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px;width: 100%; font-weight: 600;"
	                                            onclick="generateEmployeeTrainingReport()"> Employee Training</button>
	                                    </div>
	                                </div>                                
                                </form>
                            </div>
                            <div class="col m3 hide-on-small-only"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m6 s12">
                            	<form action="<%=request.getContextPath() %>/generate-completed-training-report" id="completedTrainingReportForm" name="completedTrainingReportForm" method="post" target="_blank">
	                                <div class="row">
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
	
	                                    <div class="col s12 m6 input-field">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px;width: 100%; font-weight: 600;"
	                                            onclick="generateCompletedTrainingReport()"> Completed Training </button>
	                                    </div>
	                                </div>                                
                                </form>
                            </div>
                            <div class="col m3 hide-on-small-only"></div>
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