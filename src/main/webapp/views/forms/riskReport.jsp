<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk Analysis Report - Reports - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
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
                            <h6>Risk Analysis Report </h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row no-mar">
                            <div class="col l9 m10 s12 offset-m2 offset-l3">
                            	<form action="<%=request.getContextPath() %>/generate-risk-analysis-report" id="reportForm" name="reportForm" method="post">
	                                <div class="row">
	                                    <div class="col s6 m4 l3 input-field offset-l1">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="sub_work" name="sub_work" onchange="addInQueWork(this.value);getAssessmentDateListInRiskReport('');getRiskReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="sub_workError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s6 m4 l3 input-field">
	                                        <p class="searchable_label">Assessment Date</p>
	                                        <select class="searchable validate-dropdown" id="report_assessment_date" name="assessment_date" onchange="addInQueDate(this.value);getRiskReport();">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="report_assessment_dateError" class="error-msg" ></span>
	                                    </div>
	                                </div>
	                                <div class="row">	                                	
	                                    <div class="col s5 m4 l3 input-field center-align offset-l1">
	                                        <button class="btn bg-s waves-effect waves-light t-c" type="button"
	                                            style="margin-top: 6px; font-weight: 600;"
	                                            onclick="clearFilter()">Clear Filter</button>
	                                    </div>
	                                    <div class="col s7 m4 l3 input-field center-align">
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
			var filters = window.localStorage.getItem("riskReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'sub_work' ){
    		        		  getSubWorksListInRiskReport(temp2[1]);
    		        		  getAssessmentDateListInRiskReport("");
    		        	  }else if($.trim(temp2[0]) == 'report_assessment_date'){
    		        		  getAssessmentDateListInRiskReport(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            getRiskReport();  			
        });
        
        function addInQueWork(sub_work){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('sub_work')) delete filtersMap[key];
       	   	});
          	if($.trim(sub_work) != ''){
            	filtersMap["sub_work"] = sub_work;
          	}
        } 
        function addInQueDate(report_assessment_date){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('report_assessment_date')) delete filtersMap[key];
       		});
        	if($.trim(report_assessment_date) != ''){
       	    	filtersMap["report_assessment_date"] = report_assessment_date;
        	}
        }
        
       function getRiskReport(){
    	   
    	  	var sub_work = $("#sub_work").val();
    	  	if(sub_work == "") { getSubWorksListInRiskReport("");}
        	var report_assessment_date = $("#report_assessment_date").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("riskReportFilters", filters);
    			});
    	   
       }
        
        function getSubWorksListInRiskReport(work){
        	$(".page-loader").show();
           	$("#sub_work option:not(:first)").remove();
           	var myParams = {}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getSubWorksListInRiskReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	    var selectedFlag = (work == val.sub_work)?'selected':'';
								$("#sub_work").append('<option value="' + $.trim(val.sub_work) + '"'+selectedFlag+'>' + $.trim(val.sub_work)+'</option>');
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
        
        
        function getAssessmentDateListInRiskReport(date) {
        	$(".page-loader").show();
        	var sub_work = $("#sub_work").val();
           	$("#report_assessment_date option:not(:first)").remove();
           	var myParams = {sub_work : sub_work}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getAssessmentDateListInRiskReport",
                   data: myParams, cache: false,async:false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	    var selectedFlag = (date == val.assessment_date)?'selected':'';
								$("#report_assessment_date").append('<option value="' + $.trim(val.assessment_date) + '"'+selectedFlag+'>' + $.trim(val.assessment_date)+'</option>');
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
						   document.getElementById("sub_workError").innerHTML="";
					 	   error.appendTo('#sub_workError');
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
    		$('#sub_work').val('');
    		$('#report_assessment_date').val('');
    		$('.searchable').select2();
    		window.localStorage.setItem("riskReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/risk-analysis-report"
    	}
    </script>

</body>

</html>