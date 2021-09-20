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
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"	rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
<link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
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
    <%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
 
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
			            <form action="<%=request.getContextPath() %>/generate-contract-resource-report" id="reportForm" name="reportForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                                <div class="col s6 m4 l2 input-field offset-l3">
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="addInQueProject(this.value);getWorksList();getContractsList();getHODList();getResourceReport();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id_fk}">${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="projectError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);resetProjectsDropdowns();getHODList();getContractsList();getResourceReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">HOD</p>
                                    <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" onchange="addInQueHOD(this.value);resetWorksAndProjectsDropdowns();getContractsList();getResourceReport();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${HODsList }">
                                      	   <option name="${obj.work_id_fk }" value= "${obj.hod_user_id_fk}">${obj.designation}<%-- <c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name } --%></option>
                                         </c:forEach>
                                    </select>
                                    <span id="hodError" class="error-msg" ></span>
                                </div>
                                </div>
                           <div class="row">
                           		<div class="col s6 m4 l2 input-field offset-l3 pt-md-5">
                                    <p class="searchable_label" style="text-align:left">Contract</p>
                                    <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="addInQueContract(this.value);resetWorksAndProjectsDropdowns();setHODList();getResourceReport();">
                                        <option value="">Select </option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" name="${obj.hod_user_id_fk }" value= "${obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field ">                                    
                                    <input id="from_date" type="text" name="from_date" class="validate datepicker">
                                    <label for="from_date" class="fs-sm-8rem">Deployment From Date <span class="required">*</span></label>
                                    <button type="button" id="from_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="from_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">                                    
                                    <input id="to_date" type="text" name="to_date" class="validate datepicker">
                                    <label for="to_date" class="fs-sm-8rem">Deployment To Date <span class="required">*</span></label>
                                    <button type="button" id="to_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="to_dateError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">	                                	
                                <div class="col s12 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button class="btn bg-s waves-effect waves-light t-c" type="button"
                                        style="margin-top: 6px; font-weight: 600; min-width:160px"
                                        onclick="clearFilter()">Clear Filter</button>
                                </div>
                                <div class="col s12 m4 l3 input-field center-align">
                                    <button type="submit" class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px%; font-weight: 600;">Generate Report</button>
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
  <%--   <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

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
    let date_pickers = document.querySelectorAll('.datepicker');
    $.each(date_pickers, function(){
    	var dt = this.value.split(/[^0-9]/);
    	this.value = ""; 
    	var options = {format: 'dd-mm-yyyy',autoClose:true};
    	if(dt.length > 1){
    		options.setDefaultDate = true,
    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
    	}
    	M.Datepicker.init(this, options);
    });
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
            var filters = window.localStorage.getItem("contrctResourceReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
    		        		  getProjectList(temp2[1]);
    		        		  getContractsList("");
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getWorksList(temp2[1]);
    		        		  resetProjectsDropdowns();
    		        		  getContractsList("");
    		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
    		        		  getContractsList(temp2[1]);
    		        		  getHODList("");
    		        		  setHODList();
    		        		  resetWorksAndProjectsDropdowns();
    		        	  }else if($.trim(temp2[0]) == 'hod_user_id_fk'){
    		        		  getHODList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
		});    
        function addInQueWork(work_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
          	}
        } 
        function addInQueProject(project_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('project_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(project_id_fk) != ''){
       	    	filtersMap["project_id_fk"] = project_id_fk;
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
        function addInQueHOD(hod_user_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('hod_user_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(hod_user_id_fk) != ''){
            	filtersMap["hod_user_id_fk"] = hod_user_id_fk;
          	}
        }
        function getWorksList(work) {
        	$(".page-loader").show();
          
            var project_id_fk = $("#project_id_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var hod_user_id_fk = $("#hod_user_id_fk").val();
            if ($.trim(work_id_fk) == "") {
           	    $("#work_id_fk option:not(:first)").remove();
                $("#hod_user_id_fk option:not(:first)").remove();
                $("#contract_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: project_id_fk, hod_user_id_fk : hod_user_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForContractResourceReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var selectedFlag = (work == val.work_id_fk)?'selected':'';
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function getProjectList(project) {
        	$(".page-loader").show();
        
            var project_id_fk = $("#project_id_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var hod_user_id_fk = $("#hod_user_id_fk").val();
            if ($.trim(project_id_fk) == "" ) {
            	$("#project_id_fk option:not(:first)").remove();
                $("#work_id_fk option:not(:first)").remove();
                $("#contract_id_fk option:not(:first)").remove();
                var myParams = { work_id_fk: work_id_fk, hod_user_id_fk : hod_user_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectsListForContractResourceReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var project_name = '';
                                if ($.trim(val.project_name) != '') { project_name = ' - ' + $.trim(val.project_name) }
                                var selectedFlag = (project == val.project_id_fk)?'selected':'';
                                $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>' + $.trim(val.project_id_fk) + $.trim(project_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        function getHODList(hod){
			$(".page-loader").show();
        	
            var project_id_fk = $("#project_id_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var hod_user_id_fk = $("#hod_user_id_fk").val();
            if ($.trim(hod_user_id_fk) == "" ) {
                $("#hod_user_id_fk option:not(:first)").remove();
                var myParams = { work_id_fk: work_id_fk, project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getHODSListForContractResourceReportForm", 
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                               var selectedFlag = (hod == val.hod_user_id_fk)?'selected':'';
                               $("#hod_user_id_fk").append('<option name="'+val.work_id_fk +'"  value="' + val.hod_user_id_fk + '"'+selectedFlag+'>' + $.trim(val.designation)  + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        function getContractsList(contarct) {
        	$(".page-loader").show();
        	
            var project_id_fk = $("#project_id_fk").val();
            var contract_id_fk = $("#contract_id_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var hod_user_id_fk = $("#hod_user_id_fk").val();
            if ($.trim(contract_id_fk) == "" ) {
            	$("#contract_id_fk option:not(:first)").remove();
                var myParams = { work_id_fk: work_id_fk, hod_user_id_fk : hod_user_id_fk,project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForContractResourceReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                var selectedFlag = (contarct == val.contract_id_fk)?'selected':'';
                                $("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" name="'+val.hod_user_id_fk +'" value="' + val.contract_id_fk + '"'+selectedFlag+'>' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        function setHODList(){
        	$(".page-loader").show();        	
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){  
            	var hod_user_id_fk = $("#contract_id_fk").find('option:selected').attr("name");
       			$("#hod_user_id_fk").val(hod_user_id_fk);
       			$("#hod_user_id_fk").select2();
       		}
       		$(".page-loader").hide();
        }
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	if($.trim(hod_user_id_fk) != ''){
        		contract_id_fk = "";
        	}
       		if($.trim(contract_id_fk) != ''){  
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
            	projectId = workId.substring(0, 3);    
       			//workId = workId.substring(3, work_id.length); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		} else{
       			workId = $("#hod_user_id_fk").find('option:selected').attr("name"); 
       			//contract_id_fk = $("#hod_user_id_fk").find('option:selected').attr("name"); 
       			projectId = workId.substring(0, 3);    
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		} 
       		$(".page-loader").hide();
        }
        
        function resetProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
       		var work_id_fk = $("#work_id_fk").val();
       		if($.trim(work_id_fk) != ''){  
            	projectId = work_id_fk.substring(0, 3);    
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		$(".page-loader").hide();
        	
        }
        function getResourceReport(){
        	  getProjectList("");
           
        	   var project_id_fk = $("#project_id_fk").val();
               var work_id_fk = $("#work_id_fk").val();
               var hod_user_id_fk = $("#hod_user_id_fk").val();
               var contract_id_fk = $("#contract_id_fk").val();
               
           	  
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("contrctResourceReportFilters", filters);
     			});
        }
        
        <%-- function getResourceReport(sub_work) {
        	$(".page-loader").show();
        	var work_id = $("#report_work_id").val();
           	$("#from_date option:not(:first)").remove();
           	var myParams = {work_id : work_id,sub_work : sub_work}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getResourceReport",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
								$("#from_date").append('<option value="' + $.trim(val.assessment_date) + '">' + $.trim(val.assessment_date)+'</option>');
                           });
                       }
                       $('.searchable').select2();
                       $(".page-loader").hide();
                   },error: function (jqXHR, exception) {
    	   			  $(".page-loader").hide();
   	   	          	  getErrorMessage(jqXHR, exception);
   	   	     	  }
            });
        } --%>
        
        var validator =	$('#reportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "from_date": {
	  			 		required: true
	  			 	  }	,"to_date": {
	  			 		required: true,
	  			 		greaterThan: "#from_date",	  			 		
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "from_date": {
	  			 		required: ' This field is required'
	  			 	  },"to_date": {
	  			 		required: ' This field is required',	  			 		
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if(element.attr("id") == "project" ){
						   document.getElementById("projectError").innerHTML="";
					 	   error.appendTo('#projectError');
					} else if(element.attr("id") == "sub_work" ){
						   document.getElementById("sub_workError").innerHTML="";
					 	   error.appendTo('#sub_workError');
					} else if(element.attr("id") == "hod" ){
						   document.getElementById("hodError").innerHTML="";
					 	   error.appendTo('#hodError');
					} else if(element.attr("id") == "contract" ){
						   document.getElementById("contractError").innerHTML="";
					 	   error.appendTo('#contractError');
					} else if(element.attr("id") == "from_date" ){
						   document.getElementById("from_dateError").innerHTML="";
					 	   error.appendTo('#from_dateError');
					} else if(element.attr("id") == "to_date" ){
						   document.getElementById("to_dateError").innerHTML="";
					 	   error.appendTo('#to_dateError');
					} else{
	 					error.insertAfter(element);
			       }
                }
		   		,submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        
    	$.validator.addMethod("greaterThan", function(value, element) {
            var fromDateString = $('#from_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
         
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	//return Date.parse(fromDate) <= Date.parse(toDate);
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
        }, "To date must be after From Date");
    	
    	
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
    		$('#project_id_fk').val('');
    		$('#work_id_fk').val('');
    		$('#hod_user_id_fk').val('');
    		$('#contract_id_fk').val('');
    		$('#from_date').val('');
    		$('#to_date').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("contrctResourceReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/contract-resource-report";
    	}
    </script>

</body>

</html>