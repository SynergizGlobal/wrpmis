<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Activities Progress Report - Reports - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet"	href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
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
								<h6 id="rptName">Daily Progress Report</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<form action="<%=request.getContextPath() %>/generate-daily-progress-report" id="activitiesReportForm" name="activitiesReportForm" method="post" target="_blank">
						<div class="row">
						<div class="col s12 m12 l7 offset-l2 offset-m1">
							<div class="row no-mar" style="margin-bottom:0;">
								<div class="col s6 m3 l4 input-field"> 
									<p class="searchable_label">Project</p>
									<select class="searchable validate-dropdown" id="project_id" name="project_id" onchange="addInQueProject(this.value);getWorksList('');getContractsList('');getContractorsList(''); getHodList('');getDyhodList('');resetFilterDropDowns();">
										<option value="">Select</option>										
									</select> 
									<span id="project_idError" class="error-msg"></span>
								</div>							
								<div class="col s6 m3 l4 input-field">
									<p class="searchable_label">Work</p>
									<select class="searchable validate-dropdown" id="work_id" name="work_id" onchange="addInQueWork(this.value);getContractsList('');getContractorsList(''); getHodList('');getDyhodList('');resetFilterDropDowns();">
										<option value="">Select</option>
										<c:forEach var="obj" items="${worksList }">
	                                      	   <option  value= "${obj.work_id}" >${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                    </c:forEach>
									</select> 
									<span id="work_idError" class="error-msg"></span>
								</div>	
								<div class="col s6 m3 l4 input-field">
									<p class="searchable_label">Contract</p>
									<select class="searchable validate-dropdown" id="contract_id" name="contract_id" onchange="addInQueContract(this.value);getFobList('');getContractorsList(''); getHodList('');getDyhodList('');resetFilterDropDowns();">
										<option value="">Select</option>	
										<c:forEach var="obj" items="${contarctsList }">
	                                      	   <option workId="${obj.work_id}" value= "${obj.contract_id}" >${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
	                                    </c:forEach>
									</select> 
									<span id="contract_idError" class="error-msg"></span>
								</div>	
								</div> 
							    <div class="row no-mar" id="nextRow">
									<div class="col s6 m3 l4 input-field">
										<p class="searchable_label">Contractor</p>
										<select class="searchable validate-dropdown" id="contractor_id" name="contractor_id" onchange="addInQueContractor(this.value); getHodList('');getDyhodList('');resetFilterDropDowns();">
											<option value="">Select</option>	
											<c:forEach var="obj" items="${contractorsList }">
	                                      	   <option  value= "${obj.contractor_id}" ><c:if test="${empty obj.contractor_name}"> ${obj.contract_id} </c:if> ${obj.contractor_name }</option>
	                                         </c:forEach>
										</select> 
										<span id="contractor_idError" class="error-msg"></span>
									</div>
									<div class="col s6 m3 l4 input-field">
										<p class="searchable_label">HOD</p>
										<select class="searchable validate-dropdown" id="hod" name="hod" onchange="addInQueHOD(this.value);getDyhodList('');resetFilterDropDowns();">
											<option value="">Select</option>
											<c:forEach var="obj" items="${hodList }">
	                                      	   <option  value= "${obj.user_id}" >${obj.designation} -${obj.user_name }</option>
	                                   	    </c:forEach>	
										</select> 
										<span id="hod_idError" class="error-msg"></span>
									</div>
									<div class="col s6 m3 l4 input-field">
										<p class="searchable_label">Dy HOD</p>
										<select class="searchable validate-dropdown" id="dyhod" name="dyhod" onchange="addInQueDyHOD(this.value);resetFilterDropDowns();">
											<option value="">Select</option>	
											<c:forEach var="obj" items="${dyhodList }">
	                                      	   <option  value= "${obj.user_id}" >${obj.designation} - ${obj.user_name }</option>
	                                   	    </c:forEach>
										</select> 
										<span id="dyhod_idError" class="error-msg"></span>
									</div>
								</div>	
								<div class="row">
							    <div class="col s6 m3 l4 input-field" id="fob_id_fk_div" style="display: none;">
									<p class="searchable_label">Structure</p>
									<select class="searchable validate-dropdown" id="fob_id_fk" name="fob_id_fk" onchange="addInQueFOB(this.value);resetFilterDropDowns();">
										<option value="">Select</option>	
									</select> 
									<span id="fob_id_fkError" class="error-msg"></span>
								</div>			
								<div class="col s6 m3 l4 input-field" id="fmRow">
									<input id="from_date" name="from_date" type="text" class="validate datepicker"> <label for="from_date"> Progress Date <span class="required">*</span></label>
									<button type="button" id="from_date_icon"><i class="fa fa-calendar"></i></button>
									<span id="from_dateError" class="error-msg"></span>
								</div>
							</div>									
							
							<div class="row">	
								<div class="col s7 m6 input-field" style="text-align:right;">
									<button type="submit" class="btn waves-effect waves-light bg-m t-c"><strong>Generate Report </strong></button>
								</div>
								<div class="col s4 m6 input-field" style="text-align:left;">
									<button type="button" class="btn waves-effect waves-light bg-s t-c" onclick="clearFilters();">Reset</button>
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

	<!-- <div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<p>reports goes here </p>
				</div>
			</div>
		</div>
	</div> -->



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

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script>

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
	  var filtersMap = new Object();
	  var today = new Date();
      let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy', endDate: "today", maxDate: today, autoClose:true, onOpen:datePickerSelectAddClass };
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    });

        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#from_date_icon').click(function () {
                event.stopPropagation();
                $('#from_date').click();
            });
			filters = window.localStorage.getItem("activitiesReportFilters");
            
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
    		        		  getFobList("");
    		        		  getContractorsList("");
    		        		  getHodList("");
    		        		  getDyhodList("");
    		        	  }else if($.trim(temp2[0]) == 'contractor_id'){
    		        		  getContractorsList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'hod'){
    		        		  getHodList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'dyhod'){
    		        		  getDyhodList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'fob_id_fk'){
    		        		  getFobList(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
            resetFilterDropDowns();           
        });

        function clearFilters(){
      
        	$("#project_id").val('');
        	$("#work_id").val('');
        	$("#contract_id").val('');
        	$("#fob_id_fk").val('');
        	$("#contractor_id").val('');
        	$("#hod").val('');
        	$("#dyhod").val('');        	

            $("#from_date").val('').focus();
            
            window.localStorage.setItem("activitiesReportFilters",'');
        	window.location.href= "<%=request.getContextPath()%>/daily-progress-report";
        }
        
        
        function resetFilterDropDowns(){        	
        	getProjectsList("");
        	/* getWorksList('');
            getContractsList('');getFobList('');getContractorsList(''); getHodList('');getDyhodList(''); */
            
            var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("activitiesReportFilters", filters);
    			});
            
        }
        function addInQueWork(work_id){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('work_id')) delete filtersMap[key];
       	   	});
          	if($.trim(work_id) != ''){
            	filtersMap["work_id"] = work_id;
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
       	    	filtersMap["contract_id"] = contract_id;
        	}
        }
        
        function addInQueContractor(contractor_id){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('contractor_id')) delete filtersMap[key];
       	   	});
          	if($.trim(contractor_id) != ''){
            	filtersMap["contractor_id"] = contractor_id;
          	}
        }
        
        function addInQueFOB(fob_id_fk){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('fob_id_fk')) delete filtersMap[key];
       	   	});
          	if($.trim(fob_id_fk) != ''){
            	filtersMap["fob_id_fk"] = fob_id_fk;
          	}
        }
        function addInQueHOD(hod){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('hod')) delete filtersMap[key];
       	   	});
          	if($.trim(hod) != ''){
            	filtersMap["hod"] = hod;
          	}
        }
        function addInQueDyHOD(dyhod){
          	Object.keys(filtersMap).forEach(function (key) {
    	   		if(key.match('dyhod')) delete filtersMap[key];
       	   	});
          	if($.trim(dyhod) != ''){
            	filtersMap["dyhod"] = dyhod;
          	}
        }
        
       
        /*   function resetContractorDropDowns(){        	
        	getProjectsList("");
        	//getWorksList();
            //getContractsList();
            getFobList();
            //getContractorsList();
            getHodList();
            getDyhodList();
            $('#to_date_holder').hide();
        } 
        
         function resetprojectDropDowns(){        	
        	//getProjectsList();
        	getWorksList();
            getContractsList();
            getFobList();
            getContractorsList();
            getHodList();
            getDyhodList();
            $('#to_date_holder').hide();
        } 
        
        function resetWorkDropDowns(){        	
        	getProjectsList();
        	//getWorksList();
            getContractsList();
            getFobList();
            getContractorsList();
            getHodList();
            getDyhodList();
            $('#to_date_holder').hide();
        }       
           
        function resetContractsDropDowns(){        	
        	getProjectsList();
        	//getWorksList();
            //getContractsList();
            getFobList();
            getContractorsList();
            getHodList();
            getDyhodList();
            $('#to_date_holder').hide();
        } 
        
        
        function resethodDropDowns(){        	
        	getProjectsList();
        	//getWorksList();
            //getContractsList();
            getFobList();
           // getContractorsList();
            //getHodList();
            getDyhodList();
            $('#to_date_holder').hide();
        }   
        
        function resetdyhodDropDowns()
        {        	
        	getProjectsList();
        	//getWorksList();
            //getContractsList();
            //getFobList();
           // getContractorsList();
            //getHodList();
            //getDyhodList();
            $('#to_date_holder').hide();
        }          
        
        
         */
        
        function getProjectsList(project) {
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(project_id) == "") {
            	$("#project_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInActivitiesReport",
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
        	
        	$("#work_id").not(":first").empty();
        	$("#work_id").val(""); 
        	$("#contract_id").val("");
        	$("#contractor_id").val("");
        	$("#fob_id_fk").val("");
        	
        	$("#hod").val("");  
        	$("#dyhod").val("");  
        	
        	
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(work_id) == "") 
            {
            	$("#work_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInActivitiesReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                            var workName = '';
	                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
	                            var selectedFlag = (work == val.work_id)?'selected':'';
	                            $("#work_id").append('<option value="' + val.work_id + '"'+selectedFlag+'>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
        	$("#contract_id").not(":first").empty();
        	$("#contract_id").val("");         	
        	
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(contract_id) == "") {
            	$("#contract_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id, fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInActivitiesReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	var contract_name = '';
	                            if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
	                            var selectedFlag = (contarct == val.contract_id)?'selected':'';
	                            $("#contract_id").append('<option value="' + val.contract_id + '"'+selectedFlag+'>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
        
        function getFobList(fob) {
        	$(".page-loader").show();           
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
        	//alert(contract_id +" "+ fob_id_fk);
           // if ($.trim(contract_id) != "" && $.trim(fob_id_fk) == "") {
            	$("#fob_id_fk option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk :fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getFobFilterListInActivitiesReport",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var fobName = '';
                                if ($.trim(val.fob_name) != '') { fobName = ' - ' + $.trim(val.fob_name) }
                                var selectedFlag = (fob == val.fob_id)?'selected':'';
                                $("#fob_id_fk").append('<option value="' + val.fob_id + '"'+selectedFlag+'>' + $.trim(val.fob_id)  + fobName +'</option>');
                            });
                            $("#fob_id_fk_div").show();
                        }else{
                        	$("#fob_id_fk_div").hide();
                        }                     
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
      	   				$(".page-loader").hide();
    	   	         	getErrorMessage(jqXHR, exception);
    	   	     	}
                });
            
        }
        
        function getContractorsList(contractor) 
        {
        	$("#contractor_id").not(":first").empty();
        	$("#contractor_id").val("");
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(contractor_id) == "") {
            	$("#contractor_id option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getContractorsFilterListInActivitiesReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) 
	                        {
	                        	if(data.length==1)
                        		{
                        			$("#contractor_id").append('<option value="' + val.contractor_id + '" selected>' + $.trim(val.contractor_name) + '</option>');
                        		}
	                        	else
                        		{
	                        		var selectedFlag = (contractor == val.contractor_id)?'selected':'';
                        			$("#contractor_id").append('<option value="' + val.contractor_id + '"'+selectedFlag+'>' + $.trim(val.contractor_name) + '</option>');
                        		}
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
        
        function getHodList(hodVal) {
        	
        	$("#hod").not(":first").empty();
        	$("#hod").val("");
        	
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(hod) == "") {
            	$("#hod option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getHodFilterListInActivitiesReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) 
	                        {
	                        	if(data.length==1)
                        		{	                        	
	                        		$("#hod").append('<option value="' + val.user_id + '" selected>' + $.trim(val.designation) + ' - ' + $.trim(val.user_name) + '</option>');
                        		}
	                        	else
                        		{
	                        		 var selectedFlag = (hodVal == val.user_id)?'selected':'';
	                        		$("#hod").append('<option value="' + val.user_id + '"'+selectedFlag+'>' + $.trim(val.designation) + ' - ' + $.trim(val.user_name) + '</option>');
                        		}
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
        
        function getDyhodList(dyHodVal){
        	
        	$("#dyhod").not(":first").empty();
        	$("#dyhod").val("");
        	
        	$(".page-loader").show();
        	var project_id = $("#project_id").val();
        	var work_id = $("#work_id").val();
        	var contract_id = $("#contract_id").val();
        	var fob_id_fk = $("#fob_id_fk").val();
        	var contractor_id = $("#contractor_id").val();
        	var hod = $("#hod").val();
        	var dyhod = $("#dyhod").val();
            if ($.trim(dyhod) == "") {
            	$("#dyhod option:not(:first)").remove();
            	var myParams = {project_id : project_id, work_id : work_id, contract_id : contract_id,fob_id_fk : fob_id_fk, contractor_id : contractor_id, hod : hod, dyhod : dyhod };
                $.ajax({
	            	url: "<%=request.getContextPath()%>/ajax/getDyhodFilterListInActivitiesReport",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) 
	                        {
	                        	if(data.length==1)
                        		{	                        	
	                        		$("#dyhod").append('<option value="' + val.user_id + '" selected>' + $.trim(val.designation) + ' - ' + $.trim(val.user_name) + '</option>');
                        		}
	                        	else
                        		{ 
	                        		var selectedFlag = (dyHodVal == val.user_id)?'selected':'';
	                        		$("#dyhod").append('<option value="' + val.user_id + '"'+selectedFlag+'>' + $.trim(val.designation) + ' - ' + $.trim(val.user_name) + '</option>');
                        		}
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
        
        var validator = $('#activitiesReportForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "work_id": {
				 		required: false
				 	  },"contract_id": {
				 		required: false
				 	  },"from_date": {
				 		required: true
			 	  	  }
				 				
			 	},
			   messages: {
				     "work_id": {
			 			required: 'Required'
			 	  	 },"contract_id": {
			 			required: 'Required'
			 	  	 },"from_date": {
  			 			required: 'Required'
  			 	  	 }
			 				      
		    },
			  errorPlacement:
			 	function(error, element){
				    if (element.attr("id") == "from_date" ){
			 		     document.getElementById("from_dateError").innerHTML="";
			 			 error.appendTo('#from_dateError');
			 	    }else if (element.attr("id") == "work_id" ){
			 		     document.getElementById("work_idError").innerHTML="";
			 			 error.appendTo('#work_idError');
			 	    }else if (element.attr("id") == "contract_id" ){
			 	    	 document.getElementById("contract_idError").innerHTML="";
			 			 error.appendTo('#contract_idError');
			 	    }
			 },invalidHandler: function (form, validator) {
                 var errors = validator.numberOfInvalids();
                 if (errors) {
                     var position = validator.errorList[0].element;
                     jQuery('html, body').animate({
                         scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                     }, 1000);
                 }
             },submitHandler: function(form) {
			    // do other things for a valid form
			    form.submit();
			    //return true;
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
    </script>
</body>
</html>