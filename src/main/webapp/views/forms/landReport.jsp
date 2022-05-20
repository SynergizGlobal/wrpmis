<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Land Acquisition Report - PMIS</title>
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
                            <h6>Land Acquisition Report </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-la-report" id="LAReportForm" name="LAReportForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                       		     <div class="col s6 m4 l2 input-field offset-l2 pt-md-3">
                                <input id="work_name" name="work_short_name" type="hidden"/>
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="addInQueProject(this.value);getLAReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id}">${obj.project_id} - ${obj.project_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                <input id="work_name" name="work_short_name" type="hidden"/>
                                    <p class="searchable_label" style="text-align:left">Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getLAReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}">${obj.work_id_fk} - ${obj.work_short_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Type Of Land</p>
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk" onchange="addInQueTypeOfLand(this.value);getLAReport();">
                                        <option value="">Select </option>
                                        <c:forEach var="obj" items="${category }">
                                      	   <option  value= "${obj.category_fk}">${obj.category_fk}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
                                </div>
                                   	<div class="col s6 m4 l2 input-field ">
                                    <p class="searchable_label" style="text-align:left">Sub Category</p>
                                    <select class="searchable validate-dropdown" id="la_sub_category_fk" name="la_sub_category_fk" onchange="addInQueSubCategoryOfLand(this.value);getLAReport();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${subCategory }">
                                      	   <option value= "${obj.la_sub_category_fk}">${obj.la_sub_category}</option>
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
            var filters = window.localStorage.getItem("LAReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'la_sub_category_fk' ){
    		        		  getSubCategoryOfLandList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getWorksList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'category_fk'){
    		        		  getTypeOfLandList(temp2[1]);
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
        function addInQueSubCategoryOfLand(la_sub_category_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('la_sub_category_fk')) delete filtersMap[key];
       		});
        	if($.trim(la_sub_category_fk) != ''){
       	    	filtersMap["la_sub_category_fk"] = la_sub_category_fk;
        	}
        }
        
        function addInQueTypeOfLand(category_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('category_fk')) delete filtersMap[key];
       		});
        	if($.trim(category_fk) != ''){
       	    	filtersMap["category_fk"] = category_fk;
        	}
        }

        function testingExistingData() {
        	<%-- if(validator.form())
        	{
        		// validation perform
	        	$(".page-loader").show();
	            var la_sub_category_fk = $("#la_sub_category_fk").val();
	            var work_id_fk = $("#work_id_fk").val();
	            var category_fk = $("#category_fk").val();
	            var user = $("#user").val();
	            var from_date = $("#from_date").val();
	            var to_date = $("#to_date").val();
	            	if($("#to_date").val()=="")
	            	{
	            		to_date=$("#from_date").val();
	            	}
	                var myParams = { la_sub_category_fk: la_sub_category_fk, user : user,category_fk : category_fk, work_id_fk: work_id_fk,from_date : from_date,to_date : to_date };
	                $.ajax({
	                    url: "<%=request.getContextPath()%>/ajax/getLAReportFormData",
	                    data: myParams, cache: false,
	                    success: function (data) {
	                        if(data.length > 0) {
	                	        	$(".page-loader").show();
	                	        	
	            	            	if($("#to_date").val()=="")
	            	            	{
	            	            		$("#to_date").val($("#from_date").val());
	            	            	}	                	        	 --%>
	                	        	document.getElementById("LAReportForm").submit();
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
            var la_sub_category_fk = $("#la_sub_category_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var category_fk = $("#category_fk").val();
            var user = $("#user").val();
            if ($.trim(project_id_fk) == "") { 
           	    $("#project_id_fk option:not(:first)").remove();
                var myParams = { la_sub_category_fk: la_sub_category_fk, work_id_fk : work_id_fk,category_fk : category_fk,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectListForLandReportForm",
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
        
        function getWorksList(workVal) {
        	$(".page-loader").show();
            var project_id_fk = $("#project_id_fk").val();
            var la_sub_category_fk = $("#la_sub_category_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var category_fk = $("#category_fk").val();
            var user = $("#user").val();
            if ($.trim(work_id_fk) == "") { 
           	    $("#work_id_fk option:not(:first)").remove();
                var myParams = { la_sub_category_fk: la_sub_category_fk, work_id_fk : work_id_fk,category_fk : category_fk,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListForLandReportForm",
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
        
        function getSubCategoryOfLandList(subCat) {
        	$(".page-loader").show();
        	var project_id_fk = $("#project_id_fk").val();
            var la_sub_category_fk = $("#la_sub_category_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var category_fk = $("#category_fk").val();
            var user = $("#user").val();
            if ($.trim(la_sub_category_fk) == "" ) {
            	$("#la_sub_category_fk option:not(:first)").remove();
               
                var myParams = { la_sub_category_fk: la_sub_category_fk, work_id_fk : work_id_fk,category_fk : category_fk,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubCategoryOfLandsListForLAReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (subCat == val.la_sub_category_fk)?'selected':'';
                                $("#la_sub_category_fk").append('<option value="' + val.la_sub_category_fk + '"'+selectedFlag+'>' + $.trim(val.la_sub_category) + '</option>');
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

        function getTypeOfLandList(categoryVal) {
        	$(".page-loader").show();
        	
            var la_sub_category_fk = $("#la_sub_category_fk").val();
            var project_id_fk = $("#project_id_fk").val();
            var category_fk = $("#category_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            var user = $("#user").val();
            if ($.trim(category_fk) == "" ) {
            	$("#category_fk option:not(:first)").remove();
                var myParams = { la_sub_category_fk: la_sub_category_fk, work_id_fk : work_id_fk,category_fk : category_fk,project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getTypeOfLandListForLAReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (categoryVal == val.category_fk)?'selected':'';
                                $("#category_fk").append('<option value="' + val.category_fk + '"'+selectedFlag+'>' + $.trim(val.category_fk) + '</option>');
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
     
        function getLAReport(){
        	 getWorksList('');
             getTypeOfLandList('');
             getProjectList('');
             getSubCategoryOfLandList('');
             
        	 var la_sub_category_fk = $("#la_sub_category_fk").val();
             var work_id_fk = $("#work_id_fk").val();
             var category_fk = $("#category_fk").val();
             var project_id_fk = $("#project_id_fk").val();
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("LAReportFilters", filters);
     			});
        }

        function clearFilter(){
    		$('#la_sub_category_fk').val('');
    		$('#work_id_fk').val('');
    		$('#project_id_fk').val('');
    		$('#category_fk').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("LAReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/la-report";
    	}
    </script>

</body>

</html>