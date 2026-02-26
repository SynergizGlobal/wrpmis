<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Alerts Report - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/wrpmis/resources/images/favicon.png">
	<link rel="stylesheet"	href="/wrpmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet"	href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
	<!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
	<link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
	<link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
	<link rel="stylesheet"	href="/wrpmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" >
	<style>
		.input-field .searchable_label {
			font-size:0.9rem;
		}
		.error-msg label{
			color:red!important;
		}	
		.input-field .center-align.m-1 button.bg-m, 
		.input-field .center-align.m-1 button.bg-s{
			width:inherit !important;
		}
		@media(max-width: 575px){
			.s-center-align{
				text-align: center;
			}
		}
	</style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m m-b-2">
								<h6>Alerts Report</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<form action="<%=request.getContextPath() %>/generate-contracts-alert-report" id="alertsReportForm" name="alertsReportForm" method="post" target="_blank">
						<div class="row">
						<div class="col s12 m12 l8 offset-l2">
							<div class="row no-mar" style="margin-bottom:0;">
								<div class="col s6 m3 l3 input-field">
									<p class="searchable_label">HOD</p>
									<select class="searchable validate-dropdown" id="hod" name="hod" onchange="addInQueHOD(this.value);resetDropDowns();">
										<option value="">Select</option>
										 <c:forEach var="obj" items="${hodsList }">
                                                    <option  value="${obj.hod }"> ${obj.hod }</option>
                                         </c:forEach>
									</select> 
								</div>							

								<div class="col s6 m3 l3 input-field">
									<p class="searchable_label">Alert level</p>
									<select class="searchable validate-dropdown" id="alert_level" name="alert_level" onchange="addInQueAlertLevel(this.value);resetDropDowns();">
										<option value="">Select</option>
										<c:forEach var="obj" items="${alertLevel }">
                                                    <option  value="${obj.alert_level }"> ${obj.alert_level }</option>
                                         </c:forEach>	
									</select> 
								</div>	
								<div class="col s6 m3 l3 input-field">
									<p class="searchable_label">Alert Type</p>
									<select class="searchable validate-dropdown" id="alert_type_fk" name="alert_type_fk" onchange="addInQueAlertType(this.value);resetDropDowns();">
										<option value="">Select</option>	
										<c:forEach var="obj" items="${alertType }">
                                                    <option  value="${obj.alert_type_fk }"> ${obj.alert_type_fk }</option>
                                         </c:forEach>	
									</select> 
								</div>
							</div>									
							
							<div class="row">	
								<div class="col s12 m5 input-field right-align s-center-align">
									<button type="button" class="btn waves-effect waves-light bg-s t-c" onclick="clearFilters();">Clear Filters</button>
								</div>
								<div class="col s12 m7 input-field s-center-align">
									<button type="submit" class="btn waves-effect waves-light bg-m t-c"><strong>Generate Contracts Alert Report </strong></button>
								</div>
							</div>

						</div>
						</div>
					</form>
					<!-- form ends  -->
				</div>
			</div>
		</div>
	</div>

	<!-- Page Loader -->
	<div class="page-loader" style="display: none;">
		<div class="preloader-wrapper big active">
			<div class="spinner-layer spinner-blue-only">
				<div class="circle-clipper left">
					<div class="circle"></div>
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	  <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script>
	var filtersMap = new Object();
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
 			var filters = window.localStorage.getItem("alertReportFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'hod'){
    		        		  getHodList(temp2[1]);
    		        		  getAlertLevelsList("");
    		        	  }else if($.trim(temp2[0]) == 'alert_level'){
    		        		  getAlertLevelsList(temp2[1]);
    		        		  getAlertTypesList("");
    		        	  }else if($.trim(temp2[0]) == 'alert_type_fk'){
    		        		  getAlertTypesList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            resetDropDowns();           
        });
        
        function clearFilters(){
        	$("#hod option:not(:first)").remove();
        	$("#work_id_fk option:not(:first)").remove();
        	$("#alert_level option:not(:first)").remove();
        	$("#alert_type_fk option:not(:first)").remove();
        	
        	$("#hod").val('');
        	$("#work_id_fk").val('');
        	$("#alert_level").val('');
        	$("#alert_type_fk").val('');
        	$('.searchable').select2();
        	window.localStorage.setItem("alertReportFilters",'');
    		window.location.href= "<%=request.getContextPath()%>/alerts-report"
        }
        function addInQueHOD(hod){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('hod')) delete filtersMap[key];
       		});
        	if($.trim(hod) != ''){
       	    	filtersMap["hod"] = hod;
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
        function addInQueAlertLevel(alert_level){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('alert_level')) delete filtersMap[key];
       		});
        	if($.trim(alert_level) != ''){
       	    	filtersMap["alert_level"] = alert_level;
        	}
        }
        function addInQueAlertType(alert_type_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('alert_type_fk')) delete filtersMap[key];
       		});
        	if($.trim(alert_type_fk) != ''){
       	    	filtersMap["alert_type_fk"] = alert_type_fk;
        	}
        }
        
        function resetDropDowns(){
        	getHodList("");
        	getAlertLevelsList("");
        	getAlertTypesList("");
        	var hod = $("#hod").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var alert_level = $("#alert_level").val();
        	var alert_type_fk = $("#alert_type_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("alertReportFilters", filters);
    			});
        }
        
        function getHodList(hodVal) {
        	$(".page-loader").show();

        	var hod = $("#hod").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var alert_level = $("#alert_level").val();
        	var alert_type_fk = $("#alert_type_fk").val();
            if ($.trim(hod) == ""){
            	$("#hod option:not(:first)").remove();
            	var myParams = {hod : hod, work_id_fk : work_id_fk, alert_level : alert_level,alert_type_fk : alert_type_fk };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getHodListInAlertsReport",
	                data: myParams, cache: false,async:false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                           var selectedFlag = (hodVal == val.hod)?'selected':'';
	                           $("#hod").append('<option value="' + val.hod + '"'+selectedFlag+'>' + $.trim(val.hod)+ '</option>');
	                        });
	                    }
	                    $('.searchable').select2();
	                    $(".page-loader").hide();
	                },error: function (jqXHR, exception) {
	 	   			    $(".page-loader").hide();
		   	          	getErrorMessage(jqXHR, exception);
		   	     	}
	            });
            } else {
	        	  $(".page-loader").hide();
	        }
        }

  
        function getAlertLevelsList(level) {
        	$(".page-loader").show(); 
        	
        	var hod = $("#hod").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var alert_level = $("#alert_level").val();
        	var alert_type_fk = $("#alert_type_fk").val();
        	
            if ($.trim(alert_level) == "") {
            	$("#alert_level option:not(:first)").remove();
            	var myParams = {hod : hod, work_id_fk : work_id_fk, alert_level : alert_level,alert_type_fk : alert_type_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAlertLevelsListInAlertsReport",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFlag = (level == val.alert_level)?'selected':'';
                                $("#alert_level").append('<option value="' + val.alert_level + '"'+selectedFlag+'>' + $.trim(val.alert_level) +'</option>');
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
        
        function getAlertTypesList(type){
        	$(".page-loader").show(); 
        	
        	var hod = $("#hod").val();
        	var work_id_fk = $("#work_id_fk").val();
        	var alert_level = $("#alert_level").val();
        	var alert_type_fk = $("#alert_type_fk").val();
        	
            if ($.trim(alert_type_fk) == "") {
            	$("#alert_type_fk option:not(:first)").remove();
            	var myParams = {hod : hod, work_id_fk : work_id_fk, alert_level : alert_level,alert_type_fk : alert_type_fk};
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getAlertTypesListInAlertsReport",
	                data: myParams, cache: false,async:false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val){
	                        	var selectedFlag = (type == val.alert_type_fk)?'selected':'';
	                        	$("#alert_type_fk").append('<option value="' + val.alert_type_fk + '"'+selectedFlag+'>' + $.trim(val.alert_type_fk) + '</option>');
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
        
      	//This function is used to get error message for all ajax calls
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
    </script>
</body>
</html>