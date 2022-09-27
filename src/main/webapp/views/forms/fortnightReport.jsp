<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fortnight Report - PMIS</title>
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
                            <h6>Fortnight Report </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-fortnight-report" id="FortnightReportForm" name="FortnightReportForm" method="post" target="_blank">	                              
                       		 <div class="col s12 m12 l7 offset-l2 offset-m1">
								<div class="col s6 m3 l4 input-field"> 
									<p class="searchable_label">Project</p>
									<select class="searchable validate-dropdown" id="project_id" name="project_id" onchange="addInQueProject(this.value);getWorksList('');getContractsList('');resetFilterDropDowns();">
										<option value="">Select</option>										
									</select> 
									<span id="project_idError" class="error-msg"></span>
								</div>							
								<div class="col s6 m3 l4 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getContractsList('');resetFilterDropDowns();">
										<option value="">Select</option>
										<c:forEach var="obj" items="${worksList }">
	                                      	   <option  value= "${obj.work_id}" >${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                    </c:forEach>
									</select> 
									<span id="work_idError" class="error-msg"></span>
								</div>	
								<div class="col s12 m3 l4 input-field">
									<p class="searchable_label">Contract</p>
									<select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="addInQueContract(this.value);resetFilterDropDowns();">
										<option value="">Select</option>	
										<c:forEach var="obj" items="${contarctsList }">
	                                      	   <option workId="${obj.work_id}" value= "${obj.contract_id}" >${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
	                                    </c:forEach>
									</select> 
									<span id="contract_idError" class="error-msg"></span>
								</div>
                            </div>
                            <div class="row">	                                	
                                <div class="col s7 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button type="submit" class="btn bg-m waves-effe ct waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px%; font-weight: 600;" onclick="getFortnightReport();">Generate Report</button>
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
            var filters = window.localStorage.getItem("FortnightReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'project_id' ){
    		        		  getProjectsList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id'){
    		        		  getWorksList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'contract_id'){
    		        		  getContractsList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            resetFilterDropDowns(); 
		});    
        function addInQueWork(work_id){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id) != ''){
            	filtersMap["work_id_fk"] = work_id;
          	}
        } 
        function addInQueProject(project_id){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('project_id')) delete filtersMap[key];
       		});
        	if($.trim(project_id) != ''){
       	    	filtersMap["project_id"] = project_id;
        	}
        }
        
        function addInQueContract(contract_id){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('contract_id')) delete filtersMap[key];
       		});
        	if($.trim(contract_id) != ''){
       	    	filtersMap["contract_id_fk"] = contract_id;
        	}
        }

        function resetFilterDropDowns(){        	
        	getProjectsList("");
            
            var project_id = $("#project_id").val();
        	var work_id = $("#work_id_fk").val();
        	var contract_id = $("#contract_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("progressReportFilters", filters);
    			});
            
            //$('#to_date_holder').hide();
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
        function getProjectsList(project) {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id_fk").val();
        	var contract_id = $("#contract_id_fk").val();

            if ($.trim(project_id) == "") {
            	$("#project_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInProgressReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	var selectedFlag = (project == val.project_id)?'selected':'';
	                            $("#project_id").append('<option value="' + val.project_id + '"'+selectedFlag+'>' + $.trim(val.project_id) +'-'+ $.trim(val.project_name) + '</option>');
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
        
        function getWorksList(work) {
        	
        	$("#work_id_fk").not(":first").empty();
        	$("#work_id_fk").val(""); 
        	$("#contract_id_fk").val("");
 
        	
        	
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id_fk").val();
        	var contract_id = $("#contract_id_fk").val();

        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(work_id) == "") 
            {
            	$("#work_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInProgressReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                            var workName = '';
	                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
	                            var selectedFlag = (work == val.work_id)?'selected':'';
	                            $("#work_id_fk").append('<option value="' + val.work_id + '"'+selectedFlag+'>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
            else
            {
	        	  $(".page-loader").hide();
	        }
        }

        //geting contracts list    
        function getContractsList(contarct) 
        {
        	$("#contract_id_fk").not(":first").empty();
        	$("#contract_id_fk").val("");         	
        	
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id_fk").val();
        	var contract_id = $("#contract_id_fk").val();

            if ($.trim(contract_id) == "") {
            	$("#contract_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInProgressReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	var contract_name = '';
	                            if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
	                            var selectedFlag = (contarct == val.contract_id)?'selected':'';
	                            $("#contract_id_fk").append('<option value="' + val.contract_id + '"'+selectedFlag+'>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
     
        function getFortnightReport(){
        	 getWorksList('');
        	 getProjectsList('');
             getContractsList('');
             
        	 var contract_id_fk = $("#contract_id_fk").val();
             var work_id_fk = $("#work_id_fk").val();
             var project_id_fk = $("#project_id_fk").val();
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("FortnightReportFilters", filters);
     			});
        }

        function clearFilter(){
    		$('#work_id_fk').val('');
    		$('#project_id_fk').val('');
    		$('#contract_id_fk').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("FortnightReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/fortnight-report";
    	}
    </script>

</body>

</html>