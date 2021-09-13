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
	                                        <select class="searchable validate-dropdown" id="title" name="title" onchange="addInQueScheduled(this.value);getTrainingReport();">
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
	                                        <select class="searchable validate-dropdown" id="attendee" name="attendee" onchange="addInQueEmployee(this.value);getTrainingReport();">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${employees }">
	                                            	<option value="${obj.user_id }">${obj.user_name } </option>
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
	                                        <select class="searchable validate-dropdown" id="completedTitle" name="title" onchange="addInQueCompleted(this.value);getTrainingReport();">
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
    var filtersMap = new Object();
    
        $(document).ready(function(){
        	$('.searchable').select2();
        	
        	var filters = window.localStorage.getItem("trainingReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'title' ){
    		        		getScheduledListInTrainingReport(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'attendee'){
    		        		getEmployeesListInTrainingReport(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'completedTitle'){
    		        		getCompletedListInTrainingReport(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            //getTrainingReport();
        });
        
        function addInQueScheduled(title){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('title')) delete filtersMap[key];
       		});
        	if($.trim(title) != ''){
       	    	filtersMap["title"] = title;
        	}
        }
        
        function addInQueEmployee(attendee){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('attendee')) delete filtersMap[key];
       	   	});
          	if($.trim(attendee) != ''){
            	filtersMap["attendee"] = attendee;
          	}
        } 
        
        function addInQueCompleted(completedTitle){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('completedTitle')) delete filtersMap[key];
       	   	});
          	if($.trim(completedTitle) != ''){
            	filtersMap["completedTitle"] = completedTitle;
          	}
        } 
        
        function getTrainingReport(){
        	
        	getScheduledListInTrainingReport('');
        	getEmployeesListInTrainingReport('');
        	getCompletedListInTrainingReport('');
        	var title = $('#title').val()
        	var attendee = $('#attendee').val();
        	var completedTitle = $('#completedTitle').val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("trainingReportFilters", filters);
    			});
        }
        
        function getScheduledListInTrainingReport(titleVal) {
        	$(".page-loader").show();
        	var title = $('#title').val();
        	if ($.trim(title) == "") {
	        	$("#title option:not(:first)").remove();
	           	var myParams = {}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getScheduledListInTrainingReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                             var selectedFlag = (titleVal == val.title)?'selected':'';
	   	                         $("#title").append('<option value="' + val.title + '"'+selectedFlag+'>' + $.trim(val.title)  +'</option>');
	   	                     
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
        
        function getEmployeesListInTrainingReport(attendeeVal) {
        	$(".page-loader").show();
        	var attendee = $('#attendee').val();
        	if ($.trim(attendee) == "") {
        		$("#attendee option:not(:first)").remove();
	           	var myParams = {}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getEmployeesListInTrainingReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                             var selectedFlag = (attendeeVal == val.user_id)?'selected':'';
	   	                         $("#attendee").append('<option value="' + val.user_id + '"'+selectedFlag+'>' + $.trim(val.user_name)  +'</option>');
	   	                    
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
        function getCompletedListInTrainingReport(completedTitle) {
        	$(".page-loader").show();
        	var title = $('#completedTitle').val();
        	if ($.trim(title) == "") {
        		$("#completedTitle option:not(:first)").remove();
	           	var myParams = {}
	           	$.ajax({
	                   url: "<%=request.getContextPath()%>/ajax/getCompletedListInTrainingReport",
	                   data: myParams, cache: false,async: false,
	                   success: function (data) {
	                       if (data.length > 0) {
	                           $.each(data, function (i, val) {
	                             var selectedFlag = (completedTitle == val.title)?'selected':'';
	   	                         $("#completedTitle").append('<option value="' + val.title + '"'+selectedFlag+'>' + $.trim(val.title)  +'</option>');
		   	                   
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