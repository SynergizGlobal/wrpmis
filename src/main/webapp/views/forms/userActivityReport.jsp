<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Activity Report - PMIS</title>
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
                            <h6>User Activity Report </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/generate-user-activity-report" id="userActivityReportForm" name="userActivityReportForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                                <div class="col s6 m4 l2 input-field offset-l3">
                                    <p class="searchable_label" style="text-align:left">User</p>
                                    <select class="searchable validate-dropdown" id="user" name="user" onchange="addInQueUser(this.value);getUserActivityReport();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${UsersList }">
                                      	   <option  value= "${obj.user}">${obj.user}<%-- <c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name } --%></option>
                                         </c:forEach>
                                    </select>                                    
                                    <span id="projectError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Work</p>
                                    <select class="searchable validate-dropdown" id="work" name="work" onchange="addInQueWork(this.value);getUserActivityReport();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work}">${obj.work}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="sub_workError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Contract</p>
                                    <select class="searchable validate-dropdown" id="contract" name="contract" onchange="addInQueContract(this.value);getUserActivityReport();">
                                        <option value="">Select </option>
                                        <c:forEach var="obj" items="${contarctsList }">
                                      	   <option  value= "${obj.contract}">${obj.contract}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractError" class="error-msg" ></span>
                                </div>
                                </div>
                           <div class="row">
                           	
                                  
                                   	<div class="col s6 m4 l2 input-field offset-l3 pt-md-5">
                                    <p class="searchable_label" style="text-align:left">Update Form</p>
                                    <select class="searchable validate-dropdown" id="module_name" name="module_name" onchange="addInQueModule(this.value);getUserActivityReport();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${modulelist }">
                                      	   <option value= "${obj.module_name}">${obj.module_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="hodError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field ">                                    
                                    <input id="from_date" type="text" name="from_date" class="validate datepicker">
                                    <label for="from_date" class="fs-sm-8rem">Activity From Date <span class="required">*</span></label>
                                    <button type="button" id="from_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="from_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">                                    
                                    <input id="to_date" type="text" name="to_date" class="validate datepicker">
                                    <label for="to_date" class="fs-sm-8rem">Activity To Date 
                                    <!-- <span class="required">*</span> -->
                                    </label>
                                    <button type="button" id="to_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="to_dateError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">	                                	
                                <div class="col s12 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button type="button" class="btn bg-m waves-effe ct waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px%; font-weight: 600;" onclick="testingExistingData();">Generate Report</button>
                                </div>
                                <div class="col s12 m4 l3 input-field left-align ">
                                    <button class="btn bg-s waves-effect waves-light t-c" type="button"
                                        style="margin-top: 6px; font-weight: 600; min-width:160px"
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
    var datePickerSelectAddClass = function () {
        var self = this;
        setTimeout(function () {
            var selector = self.el;
            if (!selector) {
                selector = ".datepicker"
            }
            $(selector).siblings(".datepicker-modal")
                .find(".select-dropdown.dropdown-trigger")
                .each((index, item) => {
                    var dateDropdownID = $(item).attr("data-target");
                    var dropdownUL = $('#' + dateDropdownID);
                    dropdownUL.children("li").on("click", () => {
                        datePickerSelectAddClass();
                    });
                    dropdownUL.addClass("datepicker-dropdown-year-month")
                });
        }, 500);
    };
    
    let date_pickers = document.querySelectorAll('.datepicker');
    $.each(date_pickers, function(){
    	var dt = this.value.split(/[^0-9]/);
    	this.value = ""; 
      	var options = {format: 'dd-mm-yyyy',autoClose:true,onOpen: datePickerSelectAddClass,maxDate: new Date()};
    	if(dt.length > 1){
    		options.setDefaultDate = true,
    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
    	}
    	M.Datepicker.init(this, options);
    }); 
    $(document).on('focus', '.datepicker-button', function () {
        var dateId = $(this).attr('id').split("_i")[0];
        $('#' + dateId).datepicker('open');
    });
    //let date_pickers = document.querySelectorAll('#to_date');
   
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
            var filters = window.localStorage.getItem("userActivityReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'module_name' ){
    		        		  getModuleList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work'){
    		        		  getWorksList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'contract'){
    		        		  getContractsList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'user'){
    		        		  getUserList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
		});    
        function addInQueWork(work){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work')) delete filtersMap[key];
       	   	});
          	if($.trim(work) != ''){
            	filtersMap["work"] = work;
          	}
        } 
        function addInQueModule(module_name){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('module_name')) delete filtersMap[key];
       		});
        	if($.trim(module_name) != ''){
       	    	filtersMap["module_name"] = module_name;
        	}
        }
        
        function addInQueContract(contract){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('contract')) delete filtersMap[key];
       		});
        	if($.trim(contract) != ''){
       	    	filtersMap["contract"] = contract;
        	}
        }
        function addInQueUser(user){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('user')) delete filtersMap[key];
       	   	});
          	if($.trim(user) != ''){
            	filtersMap["user"] = user;
          	}
        }
        
        function testingExistingData() {
        	if(validator.form())
        	{
        		// validation perform
	        	$(".page-loader").show();
	            var module_name = $("#module_name").val();
	            var work = $("#work").val();
	            var contract = $("#contract").val();
	            var user = $("#user").val();
	            var from_date = $("#from_date").val();
	            var to_date = $("#to_date").val();
	            	if($("#to_date").val()=="")
	            	{
	            		to_date=$("#from_date").val();
	            	}
	                var myParams = { module_name: module_name, user : user,contract : contract, work: work,from_date : from_date,to_date : to_date };
	                $.ajax({
	                    url: "<%=request.getContextPath()%>/ajax/getUserActivityReportFormData",
	                    data: myParams, cache: false,
	                    success: function (data) {
	                        if(data.length > 0) {
	                	        	$(".page-loader").show();
	                	        	
	            	            	if($("#to_date").val()=="")
	            	            	{
	            	            		$("#to_date").val($("#from_date").val());
	            	            	}	                	        	
	                	        	document.getElementById("userActivityReportForm").submit();
	                	        	//$("#to_date").val("");
	                        }else{
	                        	showNoDataMessage()
	                        }
	                        $('.searchable').select2();
	                        $(".page-loader").hide();
	                    }
	                });
	        }
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
        function getWorksList(workVal) {
        	$(".page-loader").show();
          
            var module_name = $("#module_name").val();
            var work = $("#work").val();
            var contract = $("#contract").val();
            var user = $("#user").val();
            if ($.trim(work) == "") { 
           	    $("#work option:not(:first)").remove();
                var myParams = { module_name: module_name, user : user,contract : contract };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListForUserActivityReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (workVal == val.work)?'selected':'';
                                $("#work").append('<option value="' + val.work + '"'+selectedFlag+'>' + $.trim(val.work) + '</option>');
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
        
        function getModuleList(module) {
        	$(".page-loader").show();
        
            var module_name = $("#module_name").val();
            var work = $("#work").val();
            var contract = $("#contract").val();
            var user = $("#user").val();
            if ($.trim(module_name) == "" ) {
            	$("#module_name option:not(:first)").remove();
               
                var myParams = { work: work, user : user,contract : contract };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getModulesListForUserActivityReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (module == val.module_name)?'selected':'';
                                $("#module_name").append('<option value="' + val.module_name + '"'+selectedFlag+'>' + $.trim(val.module_name) + '</option>');
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
        function getUserList(userVal){
			$(".page-loader").show();
        	
            var module_name = $("#module_name").val();
            var work = $("#work").val();
            var contract = $("#contract").val();
            var user = $("#user").val();
            if ($.trim(user) == "" ) {
                $("#user option:not(:first)").remove();
                var myParams = { work: work, module_name : module_name ,contract :contract};
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getUsersListForUserActivityReportForm", 
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                               var selectedFlag = (userVal == val.user)?'selected':'';
                               $("#user").append('<option  value="' + val.user + '"'+selectedFlag+'>' + $.trim(val.user)  + '</option>');
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
        function getContractsList(contarctVal) {
        	$(".page-loader").show();
        	
            var module_name = $("#module_name").val();
            var contract = $("#contract").val();
            var work = $("#work").val();
            var user = $("#user").val();
            if ($.trim(contract) == "" ) {
            	$("#contract option:not(:first)").remove();
                var myParams = { work: work, user : user,module_name : module_name };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForUserActivityReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (contarctVal == val.contract)?'selected':'';
                                $("#contract").append('<option value="' + val.contract + '"'+selectedFlag+'>' + $.trim(val.contract) + '</option>');
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
     
        function getUserActivityReport(){
        	 getWorksList('');
             getContractsList('');
             getUserList('');
             getModuleList('');
             
        	 var module_name = $("#module_name").val();
             var work = $("#work").val();
             var user = $("#user").val();
             var contract = $("#contract").val();
              
           	var filters = '';
         	Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("userActivityReportFilters", filters);
     			});
        }
        
        <%-- function getUserActivityReport(sub_work) {
        	$(".page-loader").show();
        	var work_id = $("#report_work_id").val();
           	$("#from_date option:not(:first)").remove();
           	var myParams = {work_id : work_id,sub_work : sub_work}
           	$.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getUserActivityReport",
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
        
        var validator =	$('#userActivityReportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "from_date": {
	  			 		required: true
	  			 	  }	,"to_date": {
	  			 		//required: true,
	  			 		greaterThan: "#from_date",	  			 		
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		 "from_date": {
	  			 		required: ' This field is required'
	  			 	  }
/* 	  		 			,"to_date": {
	  			 		required: ' This field is required',	  			 		
	  			 	  } */
		   		},
		   		errorPlacement:function(error, element){
		   		 	if(element.attr("id") == "project" ){
						   document.getElementById("projectError").innerHTML="";
					 	   error.appendTo('#projectError');
					} else if(element.attr("id") == "sub_work" ){
						   document.getElementById("sub_workError").innerHTML="";
					 	   error.appendTo('#sub_workError');
					} else if(element.attr("id") == "User" ){
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
    		$('#module_name').val('');
    		$('#work').val('');
    		$('#user').val('');
    		$('#contract').val('');
    		$('#from_date').val('');
    		$('#to_date').val('');
    		$('.searchable').select2();
    		 window.localStorage.setItem("userActivityReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/user-activity-report";
    	}
    </script>

</body>

</html>