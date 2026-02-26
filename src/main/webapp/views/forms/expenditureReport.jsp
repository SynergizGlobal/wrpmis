<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expenditure Report - PMIS</title>
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
                            <h6>Expenditure Report </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-expenditure-report" id="ExpenditureReportForm" name="ExpenditureReportForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                       		     <div class="col s6 m4 l2 input-field offset-l2 pt-md-3">
                                <input id="work_name" name="work_short_name" type="hidden"/>
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="addInQueProject(this.value);getExpenditureReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_name}">${obj.project_id} - ${obj.project_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Voucher Type</p>
                                    <select class="searchable validate-dropdown" id="voucher_type" name="voucher_type" onchange="addInQueTypeOfLand(this.value);getExpenditureReport();">
                                        <option value="">Select </option>
                                        <c:forEach var="obj" items="${voucherTypesFilterList }">
                                      	   <option  value= "${obj.voucher_type}">${obj.voucher_type}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
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
            var filters = window.localStorage.getItem("ExpenditureReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
						  if($.trim(temp2[0]) == 'voucher_type'){
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
        
        function addInQueTypeOfLand(voucher_type){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('voucher_type')) delete filtersMap[key];
       		});
        	if($.trim(voucher_type) != ''){
       	    	filtersMap["voucher_type"] = voucher_type;
        	}
        }

        function testingExistingData() 
        {
	       document.getElementById("ExpenditureReportForm").submit();
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
            var voucher_type = $("#voucher_type").val();
            var user = $("#user").val();
            if ($.trim(project_id_fk) == "") { 
           	    $("#project_id_fk option:not(:first)").remove();
                var myParams = {  work_id_fk : work_id_fk,voucher_type : voucher_type,project_id_fk : project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectListForLandReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (pVal == val.project_name)?'selected':'';
                                $("#project_id_fk").append('<option value="' + val.project_name + '"'+selectedFlag+'>' + $.trim(val.project_id) + " - "+$.trim(val.project_name) + '</option>');
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
        	
            var project_id_fk = $("#project_id_fk").val();
            var voucher_type = $("#voucher_type").val();
            var work_id_fk = $("#work_id_fk").val();
            var user = $("#user").val();
            if ($.trim(voucher_type) == "" ) {
            	$("#voucher_type option:not(:first)").remove();
                var myParams = { work_id_fk : work_id_fk,voucher_type : voucher_type,project_id_fk : project_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getTypeOfLandListForExpenditureReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (categoryVal == val.voucher_type)?'selected':'';
                                $("#voucher_type").append('<option value="' + val.voucher_type + '"'+selectedFlag+'>' + $.trim(val.voucher_type) + '</option>');
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
     
        function getExpenditureReport(){
             getTypeOfLandList('');
             getProjectList('');
             
             var work_id_fk = $("#work_id_fk").val();
             var voucher_type = $("#voucher_type").val();
             var project_id_fk = $("#project_id_fk").val();
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("ExpenditureReportFilters", filters);
     			});
        }

        function clearFilter(){
    		$('#work_id_fk').val('');
    		$('#project_id_fk').val('');
    		$('#voucher_type').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("ExpenditureReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/expenditure-report";
    	}
    </script>

</body>

</html>