<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R & R Report - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" >
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
                            <h6>R & R Report </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-rr-report" id="RRReportForm" name="RRReportForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                       		     <div class="col s6 m4 l2 input-field offset-l2 pt-md-3">
                                <input id="work_name" name="work_short_name" type="hidden"/>
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="addInQueProject(this.value);getRRReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id}">${obj.project_id} - ${obj.project_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Location</p>
                                    <select class="searchable validate-dropdown" id="location_name" name="location_name" onchange="addInQueLocation(this.value);getRRReport();">
                                        <option value="">Select </option>
                                        <c:forEach var="obj" items="${location }">
                                      	   <option  value= "${obj.location_name}">${obj.location_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
                                </div>
                                   	<div class="col s6 m4 l2 input-field ">
                                    <p class="searchable_label" style="text-align:left">Type of Use </p>
                                    <select class="searchable validate-dropdown" id="type_of_use" name="type_of_use" onchange="addInQueTypeOfUse(this.value);getRRReport();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${structure }">
                                      	   <option value= "${obj.type_of_use}">${obj.type_of_use}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="hodError" class="error-msg" ></span>
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

    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <!-- <script src="/wrpmis/resources/js/datepickerDepedency.js"></script> -->
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/wrpmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
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
            var filters = window.localStorage.getItem("RRReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'type_of_use' ){
    		        		  getTypeOfUseList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'location_name'){
    		        		  getLocationsList(temp2[1]);
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
        function addInQueWork(work_id){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id) != ''){
            	filtersMap["work_id"] = work_id;
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
        
        function addInQueLocation(location_name){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('location_name')) delete filtersMap[key];
       		});
        	if($.trim(location_name) != ''){
       	    	filtersMap["location_name"] = location_name;
        	}
        }

        function testingExistingData() {
        	<%-- if(validator.form())
        	{
        		// validation perform
	        	$(".page-loader").show();
	            var type_of_use = $("#type_of_use").val();
	            var work_id = $("#work_id").val();
	            var location_name = $("#location_name").val();
	            var user = $("#user").val();
	            var from_date = $("#from_date").val();
	            var to_date = $("#to_date").val();
	            	if($("#to_date").val()=="")
	            	{
	            		to_date=$("#from_date").val();
	            	}
	                var myParams = { type_of_use: type_of_use, user : user,location_name : location_name, work_id: work_id,from_date : from_date,to_date : to_date };
	                $.ajax({
	                    url: "<%=request.getContextPath()%>/ajax/getRRReportFormData",
	                    data: myParams, cache: false,
	                    success: function (data) {
	                        if(data.length > 0) {
	                	        	$(".page-loader").show();
	                	        	
	            	            	if($("#to_date").val()=="")
	            	            	{
	            	            		$("#to_date").val($("#from_date").val());
	            	            	}	                	        	 --%>
	                	        	document.getElementById("RRReportForm").submit();
	                	        	//$("#to_date").val("");
	                       /*  }else{
	                        	showNoDataMessage()
	                        }
	                        $('.searchable').select2();
	                        $(".page-loader").hide();
	                    }
	                });
	        } */
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
            var type_of_use = $("#type_of_use").val();
            var work_id = $("#work_id").val();
            var location_name = $("#location_name").val();
            var user = $("#user").val();
            if ($.trim(project_id_fk) == "") { 
           	    $("#project_id_fk option:not(:first)").remove();
                var myParams = { type_of_use: type_of_use, work_id : work_id,location_name : location_name,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectListForRRReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (pVal == val.project_id)?'selected':'';
                                $("#project_id_fk").append('<option value="' + val.project_id + '"'+selectedFlag+'>' + $.trim(val.project_id) + " - "+$.trim(val.project_name) + '</option>');
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
        

        
        function getTypeOfUseList(module) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
            var type_of_use = $("#type_of_use").val();
            var work_id = $("#work_id").val();
            var location_name = $("#location_name").val();
            var user = $("#user").val();
            if ($.trim(type_of_use) == "" ) {
            	$("#type_of_use option:not(:first)").remove();
               
                var myParams = { type_of_use: type_of_use, work_id : work_id,location_name : location_name,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getTypeOfUsesListForRRReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (module == val.type_of_use)?'selected':'';
                                $("#type_of_use").append('<option value="' + val.type_of_use + '"'+selectedFlag+'>' + $.trim(val.type_of_use) + '</option>');
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

        function getLocationsList(contarctVal) {
        	$(".page-loader").show();
        	
            var type_of_use = $("#type_of_use").val();
            var project_id_fk = $("#project_id_fk").val();
            var location_name = $("#location_name").val();
            var work_id = $("#work_id").val();
            var user = $("#user").val();
            if ($.trim(location_name) == "" ) {
            	$("#location_name option:not(:first)").remove();
                var myParams = { type_of_use: type_of_use, work_id : work_id,location_name : location_name,project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getLocationsListForRRReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (contarctVal == val.location_name)?'selected':'';
                                $("#location_name").append('<option value="' + val.location_name + '"'+selectedFlag+'>' + $.trim(val.location_name) + '</option>');
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
     
        function getRRReport(){
             getLocationsList('');
             getProjectList('');
             getTypeOfUseList('');
             
        	 var type_of_use = $("#type_of_use").val();
             var work_id = $("#work_id").val();
             var location_name = $("#location_name").val();
             var project_id_fk = $("#project_id_fk").val();
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("RRReportFilters", filters);
     			});
        }

        function clearFilter(){
    		$('#type_of_use').val('');
    		$('#work_id').val('');
    		$('#project_id_fk').val('');
    		$('#location_name').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("RRReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/rr-report";
    	}
    </script>

</body>

</html>