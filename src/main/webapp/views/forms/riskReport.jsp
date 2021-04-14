<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risk Report</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
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
                            <div class="col m4 hide-on-small-only"></div>
                            <div class="col m8 s12">
                            	<form action="<%=request.getContextPath() %>/generate-risk-analysis-report" id="reportForm" name="reportForm" method="post">
	                                <div class="row">
	                                    <!-- <div class="col s12 m3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="report_work_id" name="work_id" onchange="getSubWorksListInRiskReport(this.value);">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="report_work_idError" class="error-msg" ></span>
	                                    </div> -->
	                                    <div class="col s12 m3 input-field">
	                                        <p class="searchable_label" style="text-align:left">Work</p>
	                                        <select class="searchable validate-dropdown" id="sub_work" name="sub_work" onchange="getAssessmentDateListInRiskReport(this.value);">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="sub_workError" class="error-msg" ></span>
	                                    </div>
	                                    <div class="col s12 m3 input-field">
	                                        <p class="searchable_label">Assessment Date</p>
	                                        <select class="searchable validate-dropdown" id="report_assessment_date" name="assessment_date">
	                                            <option value="">Select </option>
	                                        </select>
	                                        <span id="report_assessment_dateError" class="error-msg" ></span>
	                                    </div>
	
	                                </div>
	                                <div class="row">	                                	
	                                    <div class="col s12 m3 input-field center-align">
	                                        <button class="btn bg-s waves-effect waves-light t-c" type="button"
	                                            style="margin-top: 6px; font-weight: 600; min-width:160px"
	                                            onclick="clearFilter()">Clear Filter</button>
	                                    </div>
	                                    <div class="col s12 m3 input-field center-align">
	                                        <button class="btn bg-s waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 6px;min-width:160px%; font-weight: 600;"
	                                            onclick="generateReport()">Generate Report</button>
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
        	//getWorksListInRiskReport();
        	getSubWorksListInRiskReport();
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
        
        
        function getSubWorksListInRiskReport(){
        	$(".page-loader").show();
           	$("#sub_work option:not(:first)").remove();
           	var myParams = {}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getSubWorksListInRiskReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
								$("#sub_work").append('<option value="' + $.trim(val.sub_work) + '">' + $.trim(val.sub_work)+'</option>');
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
        
        
        function getAssessmentDateListInRiskReport(sub_work) {
        	$(".page-loader").show();
        	var work_id = $("#report_work_id").val();
           	$("#report_assessment_date option:not(:first)").remove();
           	var myParams = {work_id : work_id,sub_work : sub_work}
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
	  			 	  },"sub_work": {
	  			 		required: true
	  			 	  }	,"assessment_date": {
	  			 		required: true
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "work_id": {
	  				 	required: 'This field is required',
	  			 	  },"sub_work": {
	  			 		required: ' This field is required'
	  			 	  },"assessment_date": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "report_work_id" ){
						 document.getElementById("report_work_idError").innerHTML="";
				 		 error.appendTo('#report_work_idError');
					} else if(element.attr("id") == "sub_work" ){
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
    	}
    </script>

</body>

</html>