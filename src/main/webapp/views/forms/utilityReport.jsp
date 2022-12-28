<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utility Shifting Report - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
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
    <jsp:include page="../layout/header.jsp"></jsp:include>
 
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Utility Shifting Report </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-utility-report" id="UtilityShiftingReportForm" name="UtilityShiftingReportForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                       		     <div class="col s6 m4 l2 input-field offset-l3 pt-md-3">
                                <input id="work_name" name="work_short_name" type="hidden"/>
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="addInQueProject(this.value);getUtilityShiftingReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id_fk}">${obj.project_id_fk} - ${obj.project_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                <input id="work_name" name="work_short_name" type="hidden"/>
                                    <p class="searchable_label" style="text-align:left">Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getUtilityShiftingReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}">${obj.work_id_fk} - ${obj.work_short_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Execution Agency</p>
                                    <select class="searchable validate-dropdown" id="execution_agency_fk" name="execution_agency_fk" onchange="addInQueAgency(this.value);getUtilityShiftingReport();">
                                      <option value="">Select </option>
                                        <c:forEach var="obj" items="${category }">
                                      	   <option  value= "${obj.execution_agency_fk}">${obj.execution_agency_fk}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
                                </div>
                           </div>
                           <div class="row no-mar">
 								<div class="col s6 m4 l2 input-field offset-l3 pt-md-3">
                                    <p class="searchable_label mb-8"> Impacted Contract </p>
                                    <select class="searchable validate-dropdown" id="impacted_contract_id_fk" name="impacted_contract_id_fk" 
                                    onchange="getRequirementStageList(this.value);" >
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${impactedContractsList }">
                                      	   <option  value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="impacted_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                
                                 <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label"> HOD </p>
                                    <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${utilityHODList }">
                                      	   <option value= "${ obj.hod_user_id_fk}" <c:if test="${obj.hod_user_id_fk eq utilityShifting.hod_user_id_fk }">selected</c:if>>${obj.hod_user_id_fk} - ${obj.user_name}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="hod_user_id_fkError" class="error-msg" ></span>
                                </div>                                                              
                     
                            </div>
                            <div class="row">	                                	
                                <div class="col s7 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button type="button" class="btn bg-m waves-effe ct waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px%; font-weight: 600;" onclick="testingExistingData();">Generate Report</button>
                                </div>
                                <div class="col s5 m4 l3 input-field left-align ">
                                    <button class="btn bg-s waves-effect waves-light t-c" type="button"
                                        style="margin-top: 6px; font-weight: 600; min-width:120px"
                                        onclick="clearFilter()">Reset</button>
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
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <!-- <script src="/pmis/resources/js/datepickerDepedency.js"></script> -->
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
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
            var filters = window.localStorage.getItem("UtilityShiftingReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	   if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getWorksList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'execution_agency_fk'){
    		        		  getAgencyList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'project_id_fk'){
    		        		  getProjectList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
		});    
        function addInQueProject(project_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('project_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(project_id_fk) != ''){
            	filtersMap["project_id_fk"] = project_id_fk;
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
        function addInQueTypeOfUse(type_of_use){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('type_of_use')) delete filtersMap[key];
       		});
        	if($.trim(type_of_use) != ''){
       	    	filtersMap["type_of_use"] = type_of_use;
        	}
        }
        
        function addInQueAgency(execution_agency_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('execution_agency_fk')) delete filtersMap[key];
       		});
        	if($.trim(execution_agency_fk) != ''){
       	    	filtersMap["execution_agency_fk"] = execution_agency_fk;
        	}
        }

        function testingExistingData() {

        	document.getElementById("UtilityShiftingReportForm").submit();
	       
        }
        function showNoDataMessage() {
          	swal({
      	            title: "Status",
      	            text: "No updates in this period!",
      	            type: "info",
      	            confirmButtonColor: "#DD6B55",
      	            cancelButtonText: "ok",
      	            closeOnCancel: true
      	        })
      	    }
        function getProjectList(pVal) {
        	$(".page-loader").show();
          
            var project_id_fk = $("#project_id_fk").val();
           
            var work_id_fk = $("#work_id_fk").val();
            var execution_agency_fk = $("#execution_agency_fk").val();
            var user = $("#user").val();
            if ($.trim(project_id_fk) == "") { 
           	    $("#project_id_fk option:not(:first)").remove();
                var myParams = {  work_id_fk : work_id_fk,execution_agency_fk : execution_agency_fk,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectListForUtilityShiftingReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (pVal == val.project_id_fk)?'selected':'';
                                $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>' + $.trim(val.project_id_fk) + " - "+$.trim(val.project_name) + '</option>');
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
        
        function getWorksList(workVal) {
        	$(".page-loader").show();
            var project_id_fk = $("#project_id_fk").val();
           
            var work_id_fk = $("#work_id_fk").val();
            var execution_agency_fk = $("#execution_agency_fk").val();
            var user = $("#user").val();
            if ($.trim(work_id_fk) == "") { 
           	    $("#work_id_fk option:not(:first)").remove();
                var myParams = {  work_id_fk : work_id_fk,execution_agency_fk : execution_agency_fk,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListForUtilityShiftingReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (workVal == val.work_id_fk)?'selected':'';
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + " - "+$.trim(val.work_short_name) + '</option>');
                                $("work_name").val(val.work_short_name);
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
     
        function getAgencyList(contarctVal) {
        	$(".page-loader").show();
        	
           
            var project_id_fk = $("#project_id_fk").val();
            var execution_agency_fk = $("#execution_agency_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var user = $("#user").val();
            if ($.trim(execution_agency_fk) == "" ) {
            	$("#execution_agency_fk option:not(:first)").remove();
                var myParams = {  work_id_fk : work_id_fk,execution_agency_fk : execution_agency_fk,project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getAgencyListForUtilityShiftingReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (contarctVal == val.execution_agency_fk)?'selected':'';
                                $("#execution_agency_fk").append('<option value="' + val.execution_agency_fk + '"'+selectedFlag+'>' + $.trim(val.execution_agency_fk) + '</option>');
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
     
        function getUtilityShiftingReport(){
        	 getWorksList('');
             getAgencyList('');
             getProjectList('');
             getTypeOfUseList('');
             
        	
             var work_id_fk = $("#work_id_fk").val();
             var execution_agency_fk = $("#execution_agency_fk").val();
             var project_id_fk = $("#project_id_fk").val();
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("UtilityShiftingReportFilters", filters);
     			});
        }

        function clearFilter(){
    		
    		$('#work_id_fk').val('');
    		$('#project_id_fk').val('');
    		$('#execution_agency_fk').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("UtilityShiftingReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/utility-report";
    	}
    </script>

</body>

</html>