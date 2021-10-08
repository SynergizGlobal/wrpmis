<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FOB Daily Update - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
     <style>
		/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
        .hiddendiv.common {
            width: 99vw !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }
        .primary-text {
            color: #2E58AD;
            font-weight: 500;
        }
        .table-inside{
        	margin-bottom:25px;
        }

        /* dots related styling  */

        /* horizontal line*/

        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }

        /* selects toggle class */

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        #dotgroup1 .dotgroup-scroll {
            width: 100%;
            max-width: 100%;
            height: 100px;
            padding-top: 30px;
            overflow-x: auto;
            white-space: nowrap;

        }

        #dotgroup1 .horizontal-line {
            border: 1px solid #777;
            position: relative;
            bottom: -19px;
            height: 8px;
            box-shadow: 0 0 3px inset #555;
        }

        #dotgroup1 .dot-container {
            position: relative;
            display: inline-block;
        }
        .dot-container{
			min-width:55px;
		}		  
		 #component_circles .dot-container:first-of-type a{
            margin-left: -10px;
        }  

        #dotgroup1 .dot-line {
            width: inherit;
            min-width:30px;
            border: 2px solid #777;
            position: absolute;
            top: 14px;
            left: -17px;
            z-index: 0;
        }
        
        #dotgroup1 .dot-container:first-of-type >.dot-line{
        	left:4px;
        }

        #dotgroup1 .dot {
            height: 30px;
            width: 30px;
            z-index: 1;
            background-color: #bbb;
            color: #333;
            border-radius: 50%;
            border: 1px solid #bbb;
            display: inline-block;
            position: relative;
            margin: 0 12px;
        }

        .dot.active {
            box-shadow: 0px 0px 14px 6px #444, 0px 0px 25px 1px #777;
            border: 1px solid black !important;
        }

        .dot.active .project {
            font-weight: bold;
        }

        #dotgroup1 .project::before {
            content: none;
        }

        #dotgroup1 a .project.odd {
            position: relative;
            top: 30px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 a .project.even {
            position: relative;
            bottom: 20px;
            /* left: 4px; */
            font-size: 0.75rem;
        }

        #dotgroup1 .dot.not-started {
            background-color: #fff;
        }

        #dotgroup1 .dot.in-progress {
            background-color: #FFFF00;
        }


        #dotgroup1 .dot.completed {
            background-color: #05a705;
        }


        #dotgroup1 .dot.delayed {
            background-color: #f00;
        }
        
        .page-loader-1 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
        
        .page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
       
		.page-loader-3 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}

		.page-loader-4 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
		.page-loader-5 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
        .error-msg label {
            color: red !important;
        }

        .input-field .searchable_label {
            font-size: .85rem;
        }
        .fixed-width {
            width: 100%;
            overflow: auto;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        thead th input[type="checkbox"]+span:not(.lever):before{
            border: 2px solid #fff;
        }
        thead th input[type="checkbox"]:checked+span:not(.lever):before{
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }
        .mobile_responsible_table>tbody > tr:not(.datepicker-row) >td{
        	height:auto;
        }
        
  .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent !important ;
            border-radius: 0;
            border-bottom-width: 0;
        }

        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0 !important;
        }      
 .datepicker~button {
    bottom: 1rem;
}        
        
    </style>
     <style>
       
        .legends {
            padding: 2px;
        }

        .box,
        .description {
            display: inline-block;
            margin-left: 3px;
            margin-right: 3px;
            vertical-align: middle;
        }

        .box {
            width: 20px;
            height: 20px;
            border-radius:50%;
            background-color: #fff;
            border: 1px solid #ccc;
        }

        .box.not-started {
            background-color: #fff;
        }

        .box.in-progress {
            background-color: #FFFF00;
        }

        .box.completed {
            background-color: #05a705;
        }

        .box.delayed {
            background-color: #f00;
        }

        @media only screen and (max-width: 768px) {
           .fixed-width .table-inside {
	    		overflow: hidden;
			}
        }
        @media only screen and (max-width: 700px) {
            .legends .col {
                text-align: left;
            }
        }
       
        
        
    </style>
</head>
<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
   <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>FOB Daily Update</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <c:if test="${not empty success}">
				        <div class="center-align m-1 close-message" style="color:green;" >	
						   ${success}
						</div>
				    </c:if>
				    <c:if test="${not empty error }">
						<div class="center-align m-1 close-message" style="color:red;">
						   ${error}
						</div>
				    </c:if>
                    <form action="<%=request.getContextPath() %>/insert-fob-daily-update" id="FOBDailyUpdateForm" name="FOBDailyUpdateForm" method="post" >
                    <div class="container container-no-margin">
                        <div class="row">                          
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col m6 s6 input-field">
                                            <p class="searchable_label">Project</p>
                                            <select class="searchable validate-dropdown" id="project_id" name="project_id" data-placeholder="Select"
                                                onchange="addInQueProject(this.value);getAcivitiesBulkUpdateWorksList(this.value);onLoadMethod();">
                                               <option value="" ></option> 
                                                <c:forEach var="obj" items="${projectsList }">
                                                    <option value="${obj.project_id }"><%-- ${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> --%> ${obj.project_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="project_idError" class="error-msg" ></span>
                                        </div>
                                        <div class="col m6 s6 input-field">
                                            <p class="searchable_label">Work</p>
                                            <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" data-placeholder="Select"
                                                onchange="addInQueWork(this.value);getAcivitiesBulkUpdateContractsList(this.value);onLoadMethod();">
                                                 <option value=""></option> 
                                                <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id }"><%-- ${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> --%> ${obj.work_short_name }</option>
                                                </c:forEach>
                                            </select>
                                            <span id="work_id_fkError" class="error-msg" ></span>
                                        </div>
                                       <div class="col m12 s12 input-field">
                                            <p class="searchable_label">Contract <span class="required">*</span></p>
                                            <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" data-placeholder="Select"
                                                onchange="addInQueContract(this.value);resetWorksAndProjectsDropdowns(null);onLoadMethod();getAcivitiesBulkUpdateStructures();">
                                                 <option value=""></option> 
                                                <c:forEach var="obj" items="${contractsList }">
                                                	<option name="${obj.work_id_fk }" value="${obj.contract_id }" ><%-- ${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if >--%>${obj.contract_short_name}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="contract_id_fkError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                    <div class="row" id="toggle-selects">
                                        <div class="col m6 s6 input-field" >
                                            <p class="searchable_label">Structure <span class="required">*</span></p>
                                           <select id="strip_chart_structure_id_fk" name="strip_chart_structure_id_fk" data-placeholder="Select"
                                                class="searchable validate-dropdown" onchange="addInQueStructure(this.value);ClearComponents();onLoadMethod();">
                                                <option value=""></option>
                                            </select>
                                            <span id="strip_chart_structure_id_fkError" class="error-msg" ></span>
                                        </div>
                                         <div class="col m6 s6 input-field">
                                             <input id="progress_date" name="progress_date" type="text" class="validate datepicker">
                                             <label for="progress_date">Reporting Date <span class="required">*</span></label>
                                             <button type="button" id="progress_date_icon" class="white"><i class="fa fa-calendar"></i></button>
                                              <span id="progress_dateError" class="error-msg" ></span>
                                        </div>                                       
                                    </div>
									<div class="row">		
                            	 					 
							            <div class="col s12 m8 l12 input-field offset-m2">
		                                    <input id="remarks" name="remarks" type="text" class="validate valid" aria-invalid="false">
		                                    <label for="remarks" class="active">Remarks <span class="required">*</span></label> 
		                                    <span id="remarksError" class="error-msg"></span>
		                                    
		                                </div>
									</div>
							

								<div class="container container-no-margin" >
								<div class="row">
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col s4 m4 left-align">
                                            <div class=" m-1">
                                                <button type="button" onclick="updateProgress();" id="btn" class="btn btn-primary t-c" >Submit</button>
                                            </div>
                                        </div>
                                        <div class="col s4 m4 center-align">
                                            <div class=" m-1">
                                                <button type="reset" onClick="window.location.reload(); clearFilters();" class="btn waves-effect waves-light bg-s t-c">Reset</button>
                                            </div>
                                        </div>
                                        <div class="col s4 m4 right-align">
                                            <div class=" m-1">
                                                <button type="reset" onClick="" class="btn waves-effect waves-light red lighten-2">Report</button>
                                            </div>
                                        </div>                                        
                                    </div>

                                </div>
                                </div>
                        </div>	
                            </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    

    <jsp:include page="../layout/footer.jsp"></jsp:include>    
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
   <!--  <script src="/pmis/resources/js/datepickerDepedency.js"></script> -->
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
	    var structureVal = "";
	    var glb="";
	    var glbID="";
        $(document).ready(function () {
            $('.searchable').select2();
   
            
            var filters = window.localStorage.getItem("BulkFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++)
          	  {
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
    		        		  getAcivitiesBulkUpdateWorksList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getAcivitiesBulkUpdateContractsList(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
    		        		  resetWorksAndProjectsDropdowns(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'strip_chart_structure_id_fk'){
    		        		  getAcivitiesBulkUpdateStructures(temp2[1]);
    		        		  structureVal = temp2[1];
    		        	  }
    		        	  
    	        	  }
    	          }
              }
            
           // $('#progress_date').datepicker();
            $('#progress_date').datepicker({
                maxDate: new Date(),
              //  max: new Date(),
                format: 'dd-mmm-yy',
                autoClose:true,
                onOpen: datePickerSelectAddClass              
            });
            $('#progress_date_icon').click(function () {
                event.stopPropagation();
                $('#progress_date').click();
            });
            $('#remarks').characterCounter();
        
        });
        function onLoadMethod(){
	        $(".page-loader").show();
	       
	       
	       	var filters = '';
	       	Object.keys(filtersMap).forEach(function (key) {
	       		//alert(filtersMap[key]);
	       		filters = filters + key +"="+filtersMap[key] + "^";
	       		window.localStorage.setItem("BulkFilters", filters);
	   			});
	        $(".page-loader").hide();
       }
        
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
        function addInQueContract(contract_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('contract_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(contract_id_fk) != ''){
            	filtersMap["contract_id_fk"] = contract_id_fk;
	      	}
        }
        function addInQueStructure(strip_chart_structure_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('strip_chart_structure_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(strip_chart_structure_id_fk) != ''){
            	filtersMap["strip_chart_structure_id_fk"] = strip_chart_structure_id_fk;
	      	}
        }
  
        
       
        function clearFilters(){
        	window.localStorage.setItem("BulkFilters",'');
        }
        
        function ClearComponents()
        {
        	glb="";
        	glbID="";
        }

	
	function getAcivitiesBulkUpdateWorksList(projectId) { 
		$(".page-loader-1").show();
		$("#contract_id_fk option:not(:first)").remove();    	
	    $("#work_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    $("#strip_chart_section_name option:not(:first)").remove();
		
		$('.searchable').select2();
		
	
	    if ($.trim(projectId) != "") {
	        var myParams = { project_id_fk: projectId };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getAcivitiesBulkUpdateWorksList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	            	var id1 = "";
	                var id2 = "";
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var workName = '';
	                        if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
	                        if ($.trim(id2) != '' && val.work_id == $.trim(id2)) {
	                        	id1 = val.work_id;
	                            $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(workName) + '</option>');
	                        } else {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '">'  + $.trim(workName) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader-1").hide();
	                
	                if ($.trim(id1) != '' && $.trim(id2) != '') {
	                	getAcivitiesBulkUpdateContractsList(id2);
	                }
	            }
	        });
	    }else{
	    	$(".page-loader-1").hide();
	    }
	}
	
	//geting contracts list    
	function getAcivitiesBulkUpdateContractsList(work_id_fk) {
		$(".page-loader").show();
	    $("#contract_id_fk option:not(:first)").remove();
	    
	    $("#strip_chart_structure_id_fk option:not(:first)").remove();
	    $("#strip_chart_line_id_fk option:not(:first)").remove();
	    $("#strip_chart_section_name option:not(:first)").remove();
		$('.searchable').select2();
		
	    if ($.trim(work_id_fk) != "") {
	        var myParams = { work_id_fk: work_id_fk };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getAcivitiesBulkUpdateContractsList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	            	var id1 = "";
	            	var id2 = "";                        
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                        if ($.trim(id2) != '' && val.contract_id == $.trim(id2)) {
	                        	id1 = val.contract_id;
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '" selected>'  + $.trim(contract_name) + '</option>');
	                        } else {
	                            $("#contract_id_fk").append('<option name="'+val.work_id_fk+'" value="' + val.contract_id + '">' + $.trim(contract_name) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader").hide();
	                
	                if ($.trim(id1) != '' && $.trim(id2) != '') {
	                	getAcivitiesBulkUpdateStructures(id2);
	                }
	            }
	        });
	    }else{
	    	$(".page-loader").hide();
	    }
	}
	
	function resetWorksAndProjectsDropdowns(contract){
		$(".page-loader-1").show();
	
		
		var projectId = '';
		var workId = ''
			var strip_chart_structure_id_fk = $("#strip_chart_structure_id_fk").val();
			var contract_id_fk = $("#contract_id_fk").val();
			if(contract_id_fk == ""){
				contract_id_fk = contract;
			}
			if($.trim(contract_id_fk) != ''){        			
				workId = $("#contract_id_fk").find('option:selected').attr("name");
				if(workId == null){
					workId =  contract_id_fk.substring(0, 6); 
				}
				projectId = workId.substring(0, 3);    
				//workId = workId.substring(3, work_id.length);
				$("#project_id").val(projectId);
				$("#contract_id_fk").val(contract_id_fk);
				
			
				$("#project_id").select2();
				$("#contract_id_fk").select2();
			}
			
			if ($.trim(projectId) != "") {
				$("#work_id_fk option:not(:first)").remove();
	        var myParams = { project_id_fk: projectId };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getAcivitiesBulkUpdateWorksList",
	            data: myParams, cache: false,async: false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                        var workName = '';
	                        if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
	                        if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' +  $.trim(workName) + '</option>');
	                            getAcivitiesBulkUpdateStructures(structureVal);
	                        } else {
	                            $("#work_id_fk").append('<option value="' + val.work_id + '">' +  $.trim(workName) + '</option>');
	                        }
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader-1").hide();
	            }
	        });
	        $('.searchable').select2();
	    }
			
	}
	
	  function getAcivitiesBulkUpdateStructures(value) {
      	  $(".page-loader-4").show();
      	  var contract_id_fk = $("#contract_id_fk").val();
      	  var strip_chart_structure_id_fk = value;
          $("#strip_chart_structure_id_fk option:not(:first)").remove();
          $("#strip_chart_component option:not(:first)").remove();
          $("#strip_chart_component_id option:not(:first)").remove();
          if ($.trim(contract_id_fk) != "") {
          	var myParams = { contract_id_fk: contract_id_fk };
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getAcivitiesBulkUpdateInProgressStructures",
                  data: myParams, cache: false,async: false,
                  success: function (data) {
                  	var id1 = "";
                  	var id2 = "";
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
	                            if ($.trim(id2) != '' && val.strip_chart_structure_id_fk == $.trim(id2)) {
	                            	id1 = val.strip_chart_structure_id_fk;
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '" selected>' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            }else if (val.strip_chart_structure_id_fk == value) {
	                            	 var selectedFlag = (strip_chart_structure_id_fk != null)?'selected':'';
	                                 $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '" selected>' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            	 id2 = strip_chart_structure_id_fk;
	                            } else {
	                                $("#strip_chart_structure_id_fk").append('<option value="' + val.strip_chart_structure_id_fk + '">' + $.trim(val.strip_chart_structure_id_fk) + '</option>');
	                            }
                          });
                      }
                      $('.searchable').select2();
                      $(".page-loader-4").hide();
                  }
              });
          }else{
          	$(".page-loader-4").hide();
          }
      }


	




     function updateProgress()
     {
    	 if(validator.form())
    	 {	
		   		document.getElementById("FOBDailyUpdateForm").submit();
    	 }
 			 
     }
  
     function process(date){
    	   var parts = date.split("-");
    	   var date = new Date(parts[1] + "-" + parts[0] + "-" + parts[2]);
    	   return date.getTime();
    	}
     var validator = $('#FOBDailyUpdateForm').validate({
    	 ignore: ":hidden:not(.validate-dropdown)",
    	 rules: {
    		  "project_id": {
			 	required: false
			  },"work_id_fk": {
		 		required: false
		 	  },"progress_date": {
		 		required: true
		 	  },"contract_id_fk": {
		 		required: true
		 	  },"strip_chart_structure_id_fk": {
		 		required: true
		 	  },"remarks": {
			 		required: true
			 	  }		 	  
    	 },
            messages: {
                  "project_id": {
			 		required: 'Select project'
			 	 },"work_id_fk": {
		 			required: 'Select work'
		 	  	 },"contract_id_fk": {
		 			required: 'Select contract'
		 	  	 },"strip_chart_structure_id_fk": {
		 	  		required: 'Select Structure'
			 	 },"progress_date": {
		 			required: 'Select Reporting Date'
		 	  	 },"remarks": {
		 			required: 'Enter Remarks'
		 	  	 }			 	 
    	 },errorPlacement:function(error, element){
  		 	         if (element.attr("id") == "project_id" ){
			 		     document.getElementById("project_idError").innerHTML="";
 			 			 error.appendTo('#project_idError');
 			 	    }else if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "strip_chart_structure_id_fk" ){
   			 	    	 document.getElementById("strip_chart_structure_id_fkError").innerHTML="";
			 			 error.appendTo('#strip_chart_structure_id_fkError');
		 	    	}else if (element.attr("id") == "progress_date" ){
   			 	    	 document.getElementById("progress_dateError").innerHTML="";
			 			 error.appendTo('#progress_dateError');
		 	    	}
		 	    	else if (element.attr("id") == "remarks" ){
  			 	    	 document.getElementById("remarksError").innerHTML="";
			 			 error.appendTo('#remarksError');
		 	    	}  		 	         
	    	 },invalidHandler: function (form, validator) {
	             var errors = validator.numberOfInvalids();
	             if (errors) {
	                 var position = validator.errorList[0].element;
	                 jQuery('html, body').animate({
	                     scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
	                 }, 1000);
	             }
	         },submitHandler:function(form){
		    	//form.submit();
		     }
     });
     $.validator.addMethod("dateFormat",
     	    function(value, element) {
     	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
     	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
     	    	//return dtRegex.test(value);
     	    },
     	    //"Date format (Aug 02,2020)"
     	    "Date format (DD-MM-YYYY)"
     	);
     
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