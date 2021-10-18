<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!--  <title>PMIS Report - Pending Issues</title> -->
    <title>Pending Issues Reports - PMIS</title>
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
                            <h6>Pending Issues Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col m8 s12 offset-m2 l7 offset-l3">
                            	<form id="reportForm" name="reportForm" method="post">
	                                <div class="row no-mar">
	                                	<div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">HOD</p>
	                                        <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" onchange="addInQueHOD(this.value);getWorksListInIssuesReport(this.value);getIssueReport();">
	                                            <option value="">Select </option>
	                                              <c:forEach var="obj" items="${hodsList }">
                                                    <option value="${obj.hod_user_id_fk }"> ${obj.designation }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getContractsListInIssuesReport(this.value);getIssueReport();">
	                                            <option value="">Select </option>
	                                             <c:forEach var="obj" items="${worksList }">
                                                    <option  value="${obj.work_id_fk }"> <c:if test="${ empty obj.work_short_name}"> ${obj.work_id_fk } </c:if>${obj.work_short_name }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="work_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Contract</p>
	                                        <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="addInQueContract(this.value);getIssueReport();">
	                                            <option value="">Select </option>
	                                            <c:forEach var="obj" items="${contractsList }">
                                                    <option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }"><c:if test="${ empty obj.contract_short_name}"> ${obj.contract_id_fk } </c:if> ${obj.contract_short_name }</option>
                                                </c:forEach>
	                                        </select>
	                                        <span id="contract_id_fkError" class="error-msg" ></span>
	                                    </div>
	                                    
	                                </div>    
	                                <div class="row">	                                	
	                                    <div class="col s5 m4 l3 input-field offset-l1">
	                                        <button class="btn bg-s waves-effect waves-light t-c" type="button"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="clearFilter()">Clear Filter</button>
	                                    </div>
	                                    <div class="col s7 m4 l3 input-field">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generatePendingIssuesReport()">Generate Report</button>
	                                    </div>
	                                    <!--<div class="col s12 m4 l3 input-field mob-center">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="generateIssuesSummaryReport()">Issues Summary</button>
	                                    </div>		-->
	                                    
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
            var filters = window.localStorage.getItem("issueReportFilters");
            
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
    		        	  }else if($.trim(temp2[0]) == 'hod_user_id_fk'){
    		        		  getHODListInIssuesReport(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            getIssueReport();
       
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
        
        function getIssueReport(){
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("issueReportFilters", filters);
    			});
        }
        
        function getWorksListInIssuesReport(work) {
        	$(".page-loader").show();
           	$("#work_id_fk option:not(:first)").remove();
           	$("#contract_id_fk option:not(:first)").remove();
           	var hod_user_id_fk = $("#hod_user_id_fk").val();           	
           	var myParams = {hod_user_id_fk : hod_user_id_fk, status_fk : 'Closed'}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getWorksListInIssuesReport",
                   data: myParams, cache: false,
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
        
        
        function getContractsListInIssuesReport(contract){
        	$(".page-loader").show();
        	var work_id_fk = $("#work_id_fk").val();
           	$("#contract_id_fk option:not(:first)").remove();
           	var myParams = {work_id_fk : work_id_fk,status_fk : 'Closed'}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getContractsListInIssuesReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var contractShortName = '';
                               if ($.trim(val.contract_short_name) != '') { contractShortName = $.trim(val.contract_short_name) }
                               if ($.trim(val.contract_short_name) == '') { contractShortName = $.trim(val.contract_id_fk) }
                               var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
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
           	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk,status_fk : 'Closed'}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getHODListInIssuesReport",
                   data: myParams, cache: false,
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
        
        function generatePendingIssuesReport() {
        	//$(".page-loader").show();
        	$('#reportForm').attr('action', '<%=request.getContextPath() %>/generate-pending-issues-report').submit();
		}
        
        function generateIssuesSummaryReport() {
        	//$(".page-loader").show();
        	$('#reportForm').attr('action', '<%=request.getContextPath() %>/generate-issues-summary-report').submit();
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
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "work_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"contract_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"hod_user_id_fk": {
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
		$('.searchable').select2();
		window.localStorage.setItem("issueReportFilters",'');
		window.location.href= "<%=request.getContextPath()%>/issues-report"
	}
    </script>

</body>

</html>