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
			            <form action="<%=request.getContextPath() %>/generate-inactive-users-report" id="userInactiveReportForm" name="userInactiveReportForm" method="post" target="_blank">	                              
                       		 
                           	<div class="row">
                                 <div class="col s6 m4 l2 input-field offset-l3 pt-md-5">
                                    <p class="searchable_label" style="text-align:left">Module</p>
                                    <select class="searchable validate-dropdown" id="module_name_fk" name="module_name_fk" onchange="addInQueModule(this.value);getUserInactiveReportFilters();">
                                        <option value="">Select </option>
                                         <c:forEach var="obj" items="${modulelist }">
                                      	   <option value= "${obj.module_name}">${obj.module_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="hodError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label" style="text-align:left">Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="addInQueWork(this.value);getUserInactiveReportFilters();">
                                        <option value="">Select </option>
                                          <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id}">${obj.work_short_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field ">                                    
                                    <input type="number" id="inactive_since" name="inactive_since" class="validate">
                                    <label for="inactive_since" class="fs-sm-8rem">Inactive since(Days) <span class="required">*</span></label>
                                    <span id="inactive_sinceError" class="error-msg" ></span>
                                </div>
                                
                                <input type="hidden" id="work_short_name" name="work_short_name" />
                            </div>
                            <div class="row">	                                	
                                <div class="col s12 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button type="button" class="btn bg-m waves-effe ct waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px; font-weight: 600;" onclick="checkInactiveUsersExistsOrNot();">Generate Report</button>
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
    <script type="text/javascript">
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
            var filters = window.localStorage.getItem("userInactiveReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'module_name' ){
    		        		  getModuleList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work'){
    		        		  getWorksList(temp2[1]);
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
        
        function checkInactiveUsersExistsOrNot() {
        	if(validator.form()){
        		// validation perform
	        	$(".page-loader").show();
	            var module_name_fk = $("#module_name_fk").val();
	            var work_id_fk = $("#work_id_fk").val();
	            var inactive_since = $("#inactive_since").val();
	            
	            var work_short_name = $('#work_id_fk option:selected').text();
	            $("#work_short_name").val(work_short_name);
	            
                var myParams = { module_name_fk: module_name_fk,work_id_fk: work_id_fk, inactive_since : inactive_since};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/checkInactiveUsersExistsOrNot",
                    data: myParams, cache: false,
                    success: function (data) {
                        if(data > 0) {   
                	        document.getElementById("userInactiveReportForm").submit();
                        }else{
                        	showNoDataMessage();
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
      	            text: "There is no inactive users!",
      	            type: "info",
      	            confirmButtonColor: "#DD6B55",
      	            cancelButtonText: "ok",
      	            closeOnCancel: true
      	        })
      	    }
        function getWorksList(workVal) {
        	$(".page-loader").show();          
            var module_name_fk = $("#module_name_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            if ($.trim(work_id_fk) == "") { 
           	    $("#work_id_fk option:not(:first)").remove();
                var myParams = { module_name_fk: module_name_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListForUserInactiveReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (workVal == val.work_id)?'selected':'';
                                $("#work_id_fk").append('<option value="' + val.work_id + '"'+selectedFlag+'>' + $.trim(val.work_short_name) + '</option>');
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
            var module_name_fk = $("#module_name_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            if ($.trim(module_name_fk) == "" ) {
            	$("#module_name_fk option:not(:first)").remove();
               
                var myParams = { work_id_fk: work_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getModulesListForUserInactiveReportForm",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (module == val.module_name_fk)?'selected':'';
                                $("#module_name_fk").append('<option value="' + val.module_name + '"'+selectedFlag+'>' + $.trim(val.module_name) + '</option>');
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
     
        function getUserInactiveReportFilters(){
        	 getWorksList('');
             getModuleList('');
             
        	 var module_name_fk = $("#module_name_fk").val();
             var work_id_fk = $("#work_id_fk").val();
             var inactive_since = $("#inactive_since").val();
              
           	 var filters = '';
         	 Object.keys(filtersMap).forEach(function (key) {
         		//alert(filtersMap[key]);
         		filters = filters + key +"="+filtersMap[key] + "^";
         		window.localStorage.setItem("userInactiveReportFilters", filters);
     		 });
        }
        
        var validator =	$('#userInactiveReportForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "inactive_since": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "inactive_since": {
	  			 		required: 'This field is required'
	  			 	  },"work_id_fk": {
	  			 		required: 'This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if(element.attr("id") == "inactive_since" ){
						   document.getElementById("inactive_sinceError").innerHTML="";
					 	   error.appendTo('#inactive_sinceError');
					}else if(element.attr("id") == "work_id_fk" ){
						   document.getElementById("work_id_fkError").innerHTML="";
					 	   error.appendTo('#work_id_fkError');
					}else{
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
    		$('#module_name_fk').val('');
    		$('#work_id_fk').val('');
    		$('#inactive_since').val('');
    		$('.searchable').select2();
    		window.localStorage.setItem("userInactiveReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/inactive-users-report";
    	}
    </script>

</body>

</html>