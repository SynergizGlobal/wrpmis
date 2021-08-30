<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!--  <title>PMIS Report - Pending Issues</title> -->
    <title>Issue Details Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css">
      <style>              	
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
                            <h6>Issue Details Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m8 s12 offset-m2 l7 offset-l3">
                            	<form id="reportForm" name="reportForm" method="post">
	                                <div class="row no-mar">
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="getContractsListInIssuesReport(this.value);getTitlesListInIssuesReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="work_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contract</p>
	                                        <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="getHODListInIssuesReport(this.value);getTitlesListInIssuesReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="contract_id_fkError" class="error-msg" ></span>
	                                    </div>
										<div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">HOD</p>
	                                        <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" onchange="getStatusListInIssuesReport();getTitlesListInIssuesReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    </div>
	                                    <div class="row">
	                                     <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Status</p>
	                                        <select class="searchable validate-dropdown" id="status_fk" name="status_fk" onchange="getTitlesListInIssuesReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="status_fkError" class="error-msg" ></span>
	                                    </div> 
	                                     <div class="col s6 m4 l6 input-field">
	                                        <p class="searchable_label" style="text-align:left">Description</p>
	                                        <select class="searchable validate-dropdown" id="issue_id" name="issue_id">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="issue_idError" class="error-msg" ></span>
	                                    </div> 
	                                    
	                                </div>    
	                                <div class="row">	                                	
	                                    <div class="col s6 m4 l3 input-field offset-l1">
	                                        <button class="btn bg-s waves-effect waves-light t-c" type="button"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="clearFilter()">Clear Filter</button>
	                                    </div>
	                                   
	                                     <div class="col s12 m4 l3 input-field mob-center">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateAndDownloadIssueDetailsReport()">Generate Report</button>
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
        	getWorksListInIssuesReport();
        	getContractsListInIssuesReport("");
        	getHODListInIssuesReport("");
        	getStatusListInIssuesReport();
        	getTitlesListInIssuesReport();
        });
        
        function getWorksListInIssuesReport() {
        	$(".page-loader").show();
           	$("#work_id_fk option:not(:first)").remove();
           	var myParams = {}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getWorksListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                           	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = $.trim(val.work_short_name) }
                             if ($.trim(val.work_short_name) == '') { workShortName = $.trim(val.work_id_fk) }
   	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + workShortName +'</option>');
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
        
        
        function getContractsListInIssuesReport(work_id_fk){
        	$(".page-loader").show();
           	$("#contract_id_fk option:not(:first)").remove();
           	$("#hod_user_id_fk option:not(:first)").remove();
           	$("#status_fk option:not(:first)").remove();
           	$("#issue_id option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractsListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var contractShortName = '';
                               if ($.trim(val.contract_short_name) != '') { contractShortName = $.trim(val.contract_short_name) }
                               if ($.trim(val.contract_short_name) == '') { contractShortName = $.trim(val.contract_id_fk) }
							   $("#contract_id_fk").append('<option value="' + $.trim(val.contract_id_fk) + '">' + contractShortName +'</option>');
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
        
        function getHODListInIssuesReport(contract_id_fk){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
           	$("#hod_user_id_fk option:not(:first)").remove();
           	$("#status_fk option:not(:first)").remove();
           	$("#issue_id option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getHODListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   $("#hod_user_id_fk").append('<option value="' + $.trim(val.hod_user_id_fk) + '">' + $.trim(val.designation) +'</option>');
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
        
        function getStatusListInIssuesReport(){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
           	$("#status_fk option:not(:first)").remove();
           	$("#issue_id option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk,hod_user_id_fk : hod_user_id_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getStatusListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   $("#status_fk").append('<option value="' + $.trim(val.status_fk) + '">' + $.trim(val.status_fk) +'</option>');
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
        
        function getTitlesListInIssuesReport(){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	var status_fk = $("#status_fk").val();
           	$("#issue_id option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk,hod_user_id_fk : hod_user_id_fk, status_fk : status_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getTitlesListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   $("#issue_id").append('<option work_id_fk="'+$.trim(val.work_id_fk)+'" contract_id_fk="'+$.trim(val.contract_id_fk)+'" status_fk="'+$.trim(val.status_fk)+'" hod_user_id_fk="'+$.trim(val.hod_user_id_fk)+'" value="' + $.trim(val.issue_id) + '">' + $.trim(val.title) +'</option>');
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
        
        function setDropdownValues(){
        	var work_id_fk = $("#issue_id").find('option:selected').attr("work_id_fk");
        	var contract_id_fk = $("#issue_id").find('option:selected').attr("contract_id_fk");
        	var hod_user_id_fk = $("#issue_id").find('option:selected').attr("hod_user_id_fk");
        	var status_fk = $("#issue_id").find('option:selected').attr("status_fk");
        	$('#work_id_fk').val(work_id_fk);
        	$('#contract_id_fk').val(contract_id_fk);
        	$('#hod_user_id_fk').val(hod_user_id_fk);
        	$('#status_fk').val(status_fk);
        	$('.searchable').select2();
        }
        
        function generateAndDownloadIssueDetailsReport() {
        	if(validator.form()){
            	$('#reportForm').attr('action', '<%=request.getContextPath() %>/generate-and-download-issue-details-report').submit();
        	}
		}
        
        
        var validator =	$('#reportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "work_id_fk": {
	  			 		required: false
	  			 	  },"contract_id_fk": {
	  			 		required: false
	  			 	  },"hod_user_id_fk": {
	  			 		required: false
	  			 	  },"status_fk": {
	  			 		required: false
	  			 	  },"issue_id": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "work_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"contract_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"hod_user_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"status_fk": {
	  			 		required: ' This field is required'
	  			 	  },"issue_id": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "work_id_fk" ){
						 document.getElementById("work_id_fkError").innerHTML="";
				 		 error.appendTo('#work_id_fkError');
					} else if(element.attr("id") == "contract_id_fk" ){
						   document.getElementById("contract_id_fkError").innerHTML="";
					 	   error.appendTo('#contract_id_fkError');
					} else if(element.attr("id") == "hod_user_id_fk" ){
						   document.getElementById("hod_user_id_fkError").innerHTML="";
					 	   error.appendTo('#hod_user_id_fkError');
					} else if(element.attr("id") == "status_fk" ){
						   document.getElementById("status_fkError").innerHTML="";
					 	   error.appendTo('#status_fkError');
					} else if(element.attr("id") == "issue_id" ){
						   document.getElementById("issue_idError").innerHTML="";
					 	   error.appendTo('#issue_idError');
					} else{
	 					error.insertAfter(element);
			        }
                }
		   		,submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        
	function clearFilter(){
		$('#work_id_fk').val('');
		$('#contract_id_fk').val('');
		$('#hod_user_id_fk').val('');
		$('#status_fk').val('');
		$('#title').val('');
		$('.searchable').select2();
	}
    </script>

</body>

</html>