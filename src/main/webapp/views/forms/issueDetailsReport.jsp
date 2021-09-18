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
	                                        <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getContractsListInIssuesReport(this.value);getStatusListInIssuesReport();getLocationsListInIssuesReport();getCategoriesListInIssuesReport();getTitlesListInIssuesReport();getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${worksList }">
                                                    <option  value="${obj.work_id_fk }"> <c:if test="${ empty obj.work_short_name}"> ${obj.work_id_fk } </c:if>${obj.work_short_name }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="work_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contract</p>
	                                        <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="addInQueContract(this.value);getHODListInIssuesReport(this.value);getStatusListInIssuesReport();getLocationsListInIssuesReport();getCategoriesListInIssuesReport();getTitlesListInIssuesReport();getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                             <c:forEach var="obj" items="${contractsList }">
                                                    <option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }"><c:if test="${ empty obj.contract_short_name}"> ${obj.contract_id_fk } </c:if> ${obj.contract_short_name }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="contract_id_fkError" class="error-msg" ></span>
	                                    </div>
										<div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">HOD</p>
	                                        <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" onchange="addInQueHOD(this.value);getStatusListInIssuesReport();getLocationsListInIssuesReport();getCategoriesListInIssuesReport();getTitlesListInIssuesReport();getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${hodsList }">
                                                    <option value="${obj.hod_user_id_fk }"> ${obj.designation }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    </div>
	                                    <div class="row">
	                                     <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Status</p>
	                                        <select class="searchable validate-dropdown" id="status_fk" name="status_fk" onchange="addInQueStatus(this.value);getLocationsListInIssuesReport();getCategoriesListInIssuesReport();getTitlesListInIssuesReport();getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${statusList }">
                                                    <option  value="${obj.status_fk }"> ${obj.status_fk } </option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="status_fkError" class="error-msg" ></span>
	                                    </div> 
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Location</p>
	                                        <select class="searchable validate-dropdown" id="location" name="location" onchange="addInQueLocations(this.value);getCategoriesListInIssuesReport();getTitlesListInIssuesReport();getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                           <c:forEach var="obj" items="${locationList }">
                                                    <option  value="${obj.location }"> ${obj.location }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="locationError" class="error-msg" ></span>
	                                    </div> 
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Category</p>
	                                        <select class="searchable validate-dropdown" id="category_fk" name="category_fk" onchange="addInQueCategories(this.value);getTitlesListInIssuesReport();getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                             <c:forEach var="obj" items="${categoryList }">
                                                    <option  value="${obj.category_fk }"> ${obj.category_fk }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="category_fkError" class="error-msg" ></span>
	                                    </div> 
	                                     <div class="col s6 m9 l9 input-field">
	                                        <p class="searchable_label" style="text-align:left">Description <span class="required">*</span></p>
	                                        <select class="searchable validate-dropdown" id="issue_id" name="issue_id" onchange="addInQueTitles(this.value);getIssueDetailsReport();">
	                                            <option value="">Select </option>
	                                             <c:forEach var="obj" items="${titlesList }">
                                                    <option work_id_fk="${obj.work_id_fk}" contract_id_fk="${obj.contract_id_fk}" status_fk="${obj.status_fk}"
                                                     hod_user_id_fk="${obj.hod_user_id_fk}" value="${obj.issue_id }">${obj.issue_id} - ${obj.location} - ${obj.title }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="issue_idError" class="error-msg" ></span>
	                                    </div> 
	                                    
	                                </div>    
	                                <div class="row">	                                	
	                                    <div class="col s5 m4 l3 input-field offset-l2">
	                                        <button class="btn bg-s waves-effect waves-light t-c" type="button"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="clearFilter()">Clear Filter</button>
	                                    </div>
	                                   
	                                     <div class="col s7 m4 l3 input-field mob-center">
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
      var filtersMap = new Object();
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
		    var filters = window.localStorage.getItem("issueDetailReportFilters");
             
             if($.trim(filters) != '' && $.trim(filters) != null){
           	  var temp = filters.split('^'); 
           	  for(var i=0;i< temp.length;i++){
     	        	  if($.trim(temp[i]) != '' ){
     	        		  var temp2 = temp[i].split('=');
     		        	  if($.trim(temp2[0]) == 'work_id_fk' ){
     		        		 getWorksListInIssuesReport(temp2[1]);
     		        		getContractsListInIssuesReport("");
     		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
     		        		 getContractsListInIssuesReport(temp2[1]);
     		        		getHODListInIssuesReport("");
     		        		getStatusListInIssuesReport("");
     		        		getLocationsListInIssuesReport("");
     		        		getCategoriesListInIssuesReport("");
     		        		getTitlesListInIssuesReport("");
     		        	  }else if($.trim(temp2[0]) == 'hod_user_id_fk'){
     		        		 getHODListInIssuesReport(temp2[1]);
     		        	  }else if($.trim(temp2[0]) == 'status_fk'){
     		        		 getStatusListInIssuesReport(temp2[1]);
     		        	  }else if($.trim(temp2[0]) == 'location'){
     		        		 getLocationsListInIssuesReport(temp2[1]);
     		        	  }else if($.trim(temp2[0]) == 'category_fk'){
     		        		 getCategoriesListInIssuesReport(temp2[1]);
     		        	  }else if($.trim(temp2[0]) == 'issue_id'){
     		        		 getTitlesListInIssuesReport(temp2[1]);
     		        	  }
     	        	  }
     	          }
               }
             getIssueDetailsReport();
        	
        });
        function addInQueHOD(hod_user_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('hod_user_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(hod_user_id_fk) != ''){
       	    	filtersMap["hod_user_id_fk"] = hod_user_id_fk;
        	}
        }
        
        function addInQueWork(work_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
          	}
        } 
        function addInQueContract(contract_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('contract_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(contract_id_fk) != ''){
       	    	filtersMap["contract_id_fk"] = contract_id_fk;
        	}
        }
        
        function addInQueStatus(status_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('status_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(status_fk) != ''){
            	filtersMap["status_fk"] = status_fk;
          	}
        }
        function addInQueLocations(location){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('location')) delete filtersMap[key];
       		});
        	if($.trim(location) != ''){
       	    	filtersMap["location"] = location;
        	}
        }
        
        function addInQueCategories(category_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('category_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(category_fk) != ''){
            	filtersMap["category_fk"] = category_fk;
          	}
        } 
        function addInQueTitles(issue_id){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('issue_id')) delete filtersMap[key];
       		});
        	if($.trim(issue_id) != ''){
       	    	filtersMap["issue_id"] = issue_id;
        	}
        }
   
        function getIssueDetailsReport(){
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	var status_fk = $("#status_fk").val();
        	var location = $("#location").val();
        	var category_fk = $("#category_fk").val();
        	var issue_id = $("#issue_id").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("issueDetailReportFilters", filters);
    			});
        	
        }
        
        
        function getWorksListInIssuesReport(work) {
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
                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
   	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + workShortName +'</option>');
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
        
        
        function getContractsListInIssuesReport(contarct){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
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
                               var selectedFlag = (contarct == val.contract_id_fk)?'selected':'';
							   $("#contract_id_fk").append('<option value="' + $.trim(val.contract_id_fk) + '"'+selectedFlag+'>' + contractShortName +'</option>');
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
        
        function getHODListInIssuesReport(hod){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
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
                        	   var selectedFlag = (hod == val.hod_user_id_fk)?'selected':'';
                        	   $("#hod_user_id_fk").append('<option value="' + $.trim(val.hod_user_id_fk) + '"'+selectedFlag+'>' + $.trim(val.designation) +'</option>');
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
        
        function getStatusListInIssuesReport(status){
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
                        	   var selectedFlag = (status == val.status_fk)?'selected':'';
                        	   $("#status_fk").append('<option value="' + $.trim(val.status_fk) + '"'+selectedFlag+'>' + $.trim(val.status_fk) +'</option>');
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
        
        function getLocationsListInIssuesReport(location){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	var status_fk = $("#status_fk").val();
           	$("#location option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk,hod_user_id_fk : hod_user_id_fk, status_fk : status_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getLocationsListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var selectedFlag = (location == val.location)?'selected':'';
                        	   $("#location").append('<option value="' + $.trim(val.location) + '"'+selectedFlag+'>' + $.trim(val.location) +'</option>');
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
        
        function getCategoriesListInIssuesReport(category){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	var status_fk = $("#status_fk").val();
        	var location = $("#location").val();
           	$("#category_fk option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk,hod_user_id_fk : hod_user_id_fk, status_fk : status_fk, location : location}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getCategoriesListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var selectedFlag = (category == val.category_fk)?'selected':'';
                        	   $("#category_fk").append('<option value="' + $.trim(val.category_fk) + '"'+selectedFlag+'>' + $.trim(val.category_fk) +'</option>');
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
        
        function getTitlesListInIssuesReport(title){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	var status_fk = $("#status_fk").val();
        	var location = $("#location").val();
        	var category_fk = $("#category_fk").val();
           	$("#issue_id option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk,hod_user_id_fk : hod_user_id_fk, status_fk : status_fk,location : location,category_fk : category_fk}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getTitlesListInIssuesReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var selectedFlag = (title == val.issue_id)?'selected':'';
                        	   $("#issue_id").append('<option work_id_fk="'+$.trim(val.work_id_fk)+'" contract_id_fk="'+$.trim(val.contract_id_fk)+'" status_fk="'+$.trim(val.status_fk)+'" hod_user_id_fk="'+$.trim(val.hod_user_id_fk)+'" value="' + $.trim(val.issue_id) + '"'+selectedFlag+'>' + $.trim(val.issue_id) +' - '+ $.trim(val.location) +' - '+ $.trim(val.title) +'</option>');
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
	  			 	  },"location":{
	  			 		required: false
	  			 	  },"category_fk":{
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
	  			 	  },"location":{
	  			 		required: ' This field is required'
	  			 	  },"category_fk":{
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
					} else if(element.attr("id") == "location" ){
						   document.getElementById("locationError").innerHTML="";
					 	   error.appendTo('#locationError');
					} else if(element.attr("id") == "category_fk" ){
						   document.getElementById("category_fkError").innerHTML="";
					 	   error.appendTo('#category_fkError');
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
				$('#location').val('');
				$('#category_fk').val('');
				$('#title').val('');
				$('.searchable').select2();
				window.localStorage.setItem("issueDetailReportFilters",'');
				window.location.href= "<%=request.getContextPath()%>/issue-details-report"
			}
    </script>

</body>

</html>