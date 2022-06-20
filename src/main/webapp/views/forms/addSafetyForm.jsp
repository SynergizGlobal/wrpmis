<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Safety Incident - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }
         .hidden{
        	display:none;
        }
         .input-field .searchable_label{
        	font-size:0.85rem;
        } 
        .mti-5 p{
        	margin-top:5px;
        }
        .pb-10{
        	padding-bottom:10px;
        }
        .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
        @media only screen and (max-width:364px){
			.fs-sm-67rem {
			    font-size: .656rem !important;
			}
		}
		.error-msg label{color:red!important;}
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
		.center-align.m-1 button.bg-m.waves-light, 
		.center-align.m-1 button.bg-s.waves-light{
			width:inherit;
		}
		.input-field >textarea.materialize-textarea{
			margin-bottom:2px;
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
                                <h6>Add Safety Incident</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/add-safety" id="safetyForm" name="safetyForm" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Project <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id_fk }" >${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>                                   
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field ">
                                	<p class="searchable_label"> Contract <span class="required">*</span></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();getResponsiblePersonsList();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" hod_user_id="${obj.hod_user_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                            
							<input type="hidden" id="status_fk" name="status_fk" value="Open"/>
                                                           
                                
                                                              
                                 
                           
							
                           <%--  <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                <p class="searchable_label"> Department <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="department_fk" name="department_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departmentList }">
                                        	<c:if test="${obj.department_fk eq 'Elec' or obj.department_fk eq 'Engg' or obj.department_fk eq 'S&T'}">
                                            	<option value="${obj.department_fk }" >${obj.department_name}</option>
                                        	</c:if>
                                        </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                   <div class="col s12 m4 input-field">
                                	<p class="searchable_label"> HOD <span class="required">*</span></p>
                                    <select id="hod_user_id_fk" name="hod_user_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${hodList }">
                                      	   <option value= "${ obj.hod_user_id_fk}">${obj.designation}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="hod_user_id_fkError" class="error-msg" ></span>
                                </div>
                                
                               
                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>
                           
                            	<div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Category <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk" onchange="setTitle(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyCategoryList }">
                                            <option name="${obj.short_description}" value="${obj.category }" >${obj.category}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="category_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Impact </p>
                                    <select class="searchable validate-dropdown" id="impact_fk" name="impact_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyImpactList }">
                                            <option value="${obj.impact }" >${obj.impact}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="impact_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Root Cause <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="root_cause_fk" name="root_cause_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyRootCauseList }">
                                            <option value="${obj.root_cause }" >${obj.root_cause}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="root_cause_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                               <%--  <div class="col s12 m4 input-field">
                                <p class="searchable_label"> Status <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyStatusList }">
                                            <option value="${obj.status }" >${obj.status}</option>
                                        </c:forEach>
                                    </select>                                    
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div> --%>
                                 <!-- <div class="col s6 m4 input-field mti-5">
	                                 <p>
									      <label>
									        <input type="checkbox" id="committee_required" name="committee_required"/>
									        <span>Committee Required</span>
									      </label>
									      <input type="hidden" id="committee_required_fk" name="committee_required_fk" value="No"/>
								    </p>
							    </div> -->
							    <div class="col s6 m4 hidden input-field mti-5" id="committee_formed_div" >
	                                 <p>
									      <label>
									        <input type="checkbox" id="committee_formed" name="committee_formed" />
									        <span>Committee Formed</span>
									      </label>
								    </p>
								    <input type="hidden" id="committee_formed_fk" name="committee_formed_fk" value="No"/>
							    </div>
                               <!--  <div class="col s12 m4 input-field">
                                <p class="searchable_label"> Committee formed </p>
                                    <select id="committee_formed_fk" name="committee_formed_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                        <option value="Yes">Yes</option>
                                        <option value="No">No</option>
                                    </select>
                                    <span id="committee_formed_fkError" class="error-msg" ></span>
                                </div> -->
                            </div>
                            <div class="row">
                                 <div class="col s12 m12 l12 input-field">
                                     <textarea id="title" name="title" class="pmis-textarea validate" data-length="100" maxlength="100"></textarea>
	                                 <label for="title">Short Description <span class="required">*</span></label>
	                                 <span id="titleError" class="error-msg" ></span>
                                 </div>
                            </div>
                            <div class="row">
                                  <div class="col s12 m12 l12 input-field">
                                      <textarea id="description" name="description" class="pmis-textarea validate" data-length="1000" maxlength="1000"></textarea>
                                   <label for="description">Full Description<span class="required">*</span></label>
                                   <span id="descriptionError" class="error-msg" ></span>
                                  </div>                                    
                            </div>

                            <div class="row ">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="date" name="date" type="text" class="validate datepicker">
                                    <label for="date"> Date of Incident <span class="required">*</span></label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4  l4 input-field">
                                    <input id="location" name="location" type="text" class="validate">
                                    <label for="location" class="fs-sm-8rem">Location/Station/KM<span class="required">*</span></label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate"  maxlength="15" data-length="15">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate"  maxlength="15" data-length="15">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                         <div class="col s12 m4 l4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="validate" value="${sessionScope.USER_NAME }">
                                    <label for="reported_by">Reported By</label>
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                            	<div class="col s12 m12 l12 input-field">
									<c:set var="existingsafetyFilesLength" value="${fn:length(safety.safetyFilesList )}"></c:set>
									<c:if test="${fn:length(safety.safetyFilesList ) gt 0}">
										<c:set var="existingSafetyFilesLength" value="${fn:length(safety.safetyFilesList )+1}"></c:set>
									</c:if>
									<div id="selectedFilesInput">
                                    	<div class="file-field input-field" id="safetyFilesDiv${existingSafetyFilesLength }" >
	                                        <div class="btn bg-m t-c">
	                                            <span>Attach Files</span>
	                                            <input type="file" id="safetyFiles${existingSafetyFilesLength }" name="safetyFiles" onchange="selectFile('${existingSafetyFilesLength }')">
	                                        </div>
	                                        <div class="file-path-wrapper">
	                                            <input class="file-path validate" type="text">
	                                        </div>                                       
	                                    </div>
									</div>
                                    
                                    <div id="selectedFiles">
                                    	<c:forEach var="obj" items="${safety.safetyFilesList }" varStatus="index">
											 <div id="safetyFileName${index.count }">
												<a href="<%=CommonConstants2.SAFETY_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
												<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
												<input type="hidden" name="safetyFileNames" value="${obj.attachment }">
										     </div>
										     <div style="clear:both" ></div>
										</c:forEach>
									</div>
									
                                </div>                              
                            </div>
                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <button type="button" onclick="addSafety()"
                                            class="btn waves-effect waves-light bg-m" style="min-width:90px">Add </button>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <!-- <button type="reset" class="btn waves-effect waves-light bg-s black-text"
                                            style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath() %>/safety" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script>
    function selectFile(no){
	    files = $("#safetyFiles"+no)[0].files;
	    var html = "";
	    for (var i = 0; i < files.length ; i++) {
	    	html =  html + '<div id=safetyFileName'+no+'>'
			 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
			 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
			 + '</div>'
			 + '<div style="clear:both;"></div>';
	    }
	    $("#selectedFiles").append(html);
	    
	    $('#safetyFilesDiv'+no).hide();
	    
		var fileIndex = Number(no)+1;
		moreFiles(fileIndex);
	}
	
	function moreFiles(no){
		var html = "";
		html =  html + '<div class="file-field input-field" id="safetyFilesDiv'+no+'" >'
		+ '<div class="btn bg-m t-c">'
		+ '<span>Attach Files</span>'
		+ '<input type="file" id="safetyFiles'+no+'" name="safetyFiles" onchange="selectFile('+no+')">'
		+ '</div>'
		+ '<div class="file-path-wrapper">'
		+ '<input class="file-path validate" type="text">'
		+ '</div>'                          
		+ '</div>'
		$("#selectedFilesInput").append(html);
	}
		
	
	function removeFile(no){			
     	$('#safetyFilesDiv'+no).remove();
     	$('#safetyFileName'+no).remove();
    }

        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
              
            var today = new Date();
	        var dd = today.getDate();
	        if (dd < 10) {
	            dd = '0' + dd;
	        }
	        var mm = today.getMonth() + 1;  
	        if (mm < 10) {
	            mm = '0' + mm;
	        }
	        var yyyy = today.getFullYear();
	        var today =  yyyy+'-'+ mm +'-'+dd ;  
	        
            $('#date').datepicker({ 
	   	    	format:'dd-mm-yyyy',
	   	    	defaultDate: new Date(today),
	   	     	setDefaultDate: true,
	   	    	maxDate: new Date(today),
	   	    	autoClose:true
            });
   	        //}).datepicker("setDate", new Date());
            
            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
            
	        $("[data-length]").each(function(i,val){
	        	$('#'+val.id).characterCounter();;
	        });           
            
          /*   $('#closure_date_icon').click(function () {
                event.stopPropagation();
                $('#closure_date').click();
            });
            
            $('#closure_date').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            $('#investigation_completed_icon').click(function () {
                event.stopPropagation();
                $('#investigation_completed').click();
            });
            
            $('#investigation_completed').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
            
            $('#payment_date_icon').click(function () {
                event.stopPropagation();
                $('#payment_date').click();
            });
            
            $('#payment_date').datepicker({ 
  	    	    format:'dd-mm-yyyy',
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        }); */
            
         /*    $('#committee_required').change(function(){
                if(this.checked){
                    $('#committee_formed_div').removeClass('hidden');
                }else{
                    $('#committee_formed_div').addClass('hidden');
                    $("#committee_formed_fk").val("No");
                    $('#committee_formed').prop('checked', false);
                }
            }); 
            
            
            $("#committee_required").change(function(){
	            if($(this).prop("checked") == true){
	            	$("#committee_required_fk").val("Yes");
	            }else{
	            	$("#committee_required_fk").val("No");
	            }
	        });
	        
	        $("#committee_formed").change(function(){
	            if($(this).prop("checked") == true){
	            	$("#committee_formed_fk").val("Yes");
	            }else{
	            	$("#committee_formed_fk").val("No");
	            }
	        });
            */
        });
            
        function setTitle(category){
        	var short_description = $("#category_fk").find('option:selected').attr("name");
        	$("#title").val(short_description).focus();
        }
        
      //geting works list from database    
      function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workShortName = '';
                                if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workShortName) + '</option>');
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

        //geting contracts list    
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForSafetyForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                                $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" hod_user_id="'+val.hod_user_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
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
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){        			
       			
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
            	projectId = workId.substring(0, 3);    
       			//workId = workId.substring(3, work_id.length);
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
       		if ($.trim(projectId) != "") {
       			$("#work_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
                $('.searchable').select2();
            }       		
        }        
        
        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });
        
        function addSafety(){
    		if(validator.form()){ // validation perform
    			$(".page-loader").show();
    			var compensation = $('#compensation').val();
	  			if(compensation == ""){
	  				$('#compensation_units').val("");
	  			}
    			document.getElementById("safetyForm").submit();			
    	 	}
    	}
    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	    var validator = $('#safetyForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id_fk": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  },"hod_user_id_fk": {
    				 		required: true
    				 	  },"department_fk": {
    				 		required: true
    				 	  },"category_fk": {
    				 		required: true
    				 	  },"impact_fk": {
    				 		required: false
    				 	  },"status_fk": {
    			 		    required: true,
    			 	   	  },"root_cause_fk": {
    			 		    required: true,
    			 	   	  },"title": {
    				 		required: true
    				 	  },"description": {
    			 		    required: true,
    			 		    maxlength:1000
    			 	   	  },"date": {
    				 		required: true
    				 	  },"location": {
    				 		required: true
    				 	  },"latitude": {
    				 		required: false
    				 	  },"longitude": {
    				 		required: false
    				 	  },"reported_by": {
    				 		required: false
    				 	  },"closure_date": {
    			 		    required: false,
    			 		    dateBefore1: "#date",
    				 		statusCheck1: true
    			 	   	  },"committee_formed_fk": {
    				 		required: false
    				 	  },"lti_hours": {
    			 		    required: false
    			 	   	  },"equipment_impact": {
    				 		required: false
    				 	  },"people_impact": {
    				 		required: false
    				 	  },"work_impact":{
    				 		 required: false
    				 	  },"compensation": {
    			 		    required: false,
    			 	   	  },"investigation_completed": {
    				 		required: false,
    			 		    dateBefore3: "#date",
    				 		statusCheck2: true
    				 	  },"payment_date": {
    			 		    required: false,
    			 		    dateBefore2: "#date",
    				 		statusCheck3: true
    			 	   	  },"corrective_measure_short_term": {
    				 		required: false
    				 	  },"corrective_measure_long_term":{
    				 		 required: false
    				 	  },"remarks":{
    				 		 required: false
    				 	  },"compensation_units":{
   	        		 		 required: function(element){
   	        		             return $("#compensation").val()!="";
   	        		         }
   	        		 	  }	
   				 				
    			 	},
    			   messages: {
	    				 "project_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Required'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Required'
    			 	  	 },"hod_user_id_fk": {
    			 	  		required: 'Required'
   				 	  	  },"department_fk": {
    			 			required: 'Required'
    			 	  	 },"category_fk": {
    			 			required: 'Required'
    			 	  	 },"impact_fk": {
    			 			required: 'Required'
    			 	  	 },"status_fk": {
    			 			required: 'Required'
    			 	  	 },"root_cause_fk": {
    			 	  		required: 'Required'
   			 	   	  	 },"title": {
    			 			required: 'Required'
    			 	  	 },"description": {
    			 			required: 'Required',
    			 			maxlength: 'Should not exceed 1000 characters'
    			 	  	 },"date": {
    			 			required: 'Required'
    			 	  	 },"location": {
     				 		required: 'Required'
	   				 	  },"latitude": {
	   				 		required: 'Required'
	   				 	  },"longitude": {
	   				 		required: 'Required'
	   				 	  },"reported_by": {
	   				 		required: 'Required'
	   				 	  },"closure_date": {
	   			 		    required: 'Required',
	   			 	   	  },"committee_formed_fk": {
	   				 		required: 'Required'
	   				 	  },"lti_hours": {
	   			 		    required: 'Required'
	   			 	   	  },"equipment_impact": {
	   				 		required: 'Required'
	   				 	  },"people_impact": {
	   				 		required: 'Required'
    				 	  },"work_impact":{
    				 		 required: 'Required'
    				 	  },"compensation": {
    				 		 required: 'Required'
    			 	   	  },"investigation_completed": {
    			 	   		required: 'Required'
    				 	  },"payment_date": {
    				 		 required: 'Required'
    			 	   	  },"corrective_measure_short_term": {
    			 	   		required: 'Required'
    				 	  },"corrective_measure_long_term":{
    				 		 required: 'Required'
    				 	  },"remarks":{
    			 	  		required: 'Required'
    				 	  },"compensation_units":{
    			 	  		required: 'Required'
    				 	  }    			 				      
    		    },
    			  errorPlacement:
    			 	function(error, element){
	    				if (element.attr("id") == "project_id_fk" ){
	 			 		     document.getElementById("project_id_fkError").innerHTML="";
	 			 			 error.appendTo('#project_id_fkError');
	 			 	    }else if (element.attr("id") == "work_id_fk" ){
    			 		     document.getElementById("work_id_fkError").innerHTML="";
    			 			 error.appendTo('#work_id_fkError');
    			 	    }else if (element.attr("id") == "contract_id_fk" ){
    			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
    			 			 error.appendTo('#contract_id_fkError');
    			 	    }else if (element.attr("id") == "hod_user_id_fk" ){
	   			 	    	 document.getElementById("hod_user_id_fkError").innerHTML="";
				 			 error.appendTo('#hod_user_id_fkError');
				 	    }else if (element.attr("id") == "department_fk" ){
    			 		     document.getElementById("department_fkError").innerHTML="";
    			 			 error.appendTo('#department_fkError');
    			 	    }else if (element.attr("id") == "category_fk" ){
    			 		     document.getElementById("category_fkError").innerHTML="";
    			 			 error.appendTo('#category_fkError');
    			 	    }else if (element.attr("id") == "impact_fk" ){
    			 		     document.getElementById("impact_fkError").innerHTML="";
    			 			 error.appendTo('#impact_fkError');
    			 	    }else if (element.attr("id") == "status_fk" ){
    			 		     document.getElementById("status_fkError").innerHTML="";
    			 			 error.appendTo('#status_fkError');
    			 	    }else if (element.attr("id") == "root_cause_fk" ){
	   			 		     document.getElementById("root_cause_fkError").innerHTML="";
				 			 error.appendTo('#root_cause_fkError');
				 	    }else if (element.attr("id") == "title" ){
    			 		     document.getElementById("titleError").innerHTML="";
    			 			 error.appendTo('#titleError');
    			 	    }else if (element.attr("name") == "description" ){
    			 		     document.getElementById("descriptionError").innerHTML="";
    			 			 error.appendTo('#descriptionError');
    			 	    }else if (element.attr("id") == "date" ){
    			 		     document.getElementById("dateError").innerHTML="";
    			 			 error.appendTo('#dateError');
    			 	    }else if (element.attr("id") == "location" ){
  			 	    	     document.getElementById("locationError").innerHTML="";
  			 			     error.appendTo('#locationError');
	  			 	    }else if (element.attr("id") == "latitude" ){
	  			 		     document.getElementById("latitudeError").innerHTML="";
	  			 			 error.appendTo('#latitudeError');
	  			 	    }else if (element.attr("id") == "longitude" ){
	  			 		     document.getElementById("longitudeError").innerHTML="";
	  			 			 error.appendTo('#longitudeError');
	  			 	    }else if (element.attr("id") == "reported_by" ){
	  			 		     document.getElementById("reported_byError").innerHTML="";
	  			 			 error.appendTo('#reported_byError');
	  			 	    }else if (element.attr("id") == "closure_date" ){
	  			 		     document.getElementById("closure_dateError").innerHTML="";
	  			 			 error.appendTo('#closure_dateError');
	  			 	    }else if (element.attr("id") == "committee_formed_fk" ){
	  			 		     document.getElementById("committee_formed_fkError").innerHTML="";
	  			 			 error.appendTo('#committee_formed_fkError');
	  			 	    }else if (element.attr("name") == "lti_hours" ){
	  			 		     document.getElementById("lti_hoursError").innerHTML="";
	  			 			 error.appendTo('#lti_hoursError');
	  			 	    }else if (element.attr("id") == "equipment_impact" ){
	  			 		     document.getElementById("equipment_impactError").innerHTML="";
	  			 			 error.appendTo('#equipment_impactError');
	  			 	    }else if (element.attr("id") == "people_impact" ){
	  			 		     document.getElementById("people_impactError").innerHTML="";
	  			 			 error.appendTo('#people_impactError');
	  			 	    }else if (element.attr("id") == "work_impact" ){
	  			 		     document.getElementById("work_impactError").innerHTML="";
	  			 			 error.appendTo('#work_impactError');
	  			 	    }else if (element.attr("id") == "compensation" ){
	  			 		     document.getElementById("compensationError").innerHTML="";
	  			 			 error.appendTo('#compensationError');
	  			 	    }else if (element.attr("id") == "investigation_completed" ){
	  			 		     document.getElementById("investigation_completedError").innerHTML="";
	  			 			 error.appendTo('#investigation_completedError');
	  			 	    }else if (element.attr("id") == "payment_date" ){
	  			 		     document.getElementById("payment_dateError").innerHTML="";
	  			 			 error.appendTo('#payment_dateError');
	  			 	    }else if (element.attr("name") == "corrective_measure_short_term" ){
	  			 		     document.getElementById("corrective_measure_short_termError").innerHTML="";
	  			 			 error.appendTo('#corrective_measure_short_termError');
	  			 	    }else if (element.attr("id") == "corrective_measure_long_term" ){
	  			 		     document.getElementById("corrective_measure_long_termError").innerHTML="";
	  			 			 error.appendTo('#corrective_measure_long_termError');
	  			 	    }else if (element.attr("id") == "compensation_units" ){
	  			 		     document.getElementById("compensation_unitsError").innerHTML="";
	  			 			 error.appendTo('#compensation_unitsError');
	  			 	    }else if (element.attr("id") == "remarks" ){
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
                 },submitHandler: function(form) {
    			    // do other things for a valid form
    			    //form.submit();
    			    //return true;
    			  }
    		});
    	
    	    $.validator.addMethod("dateBefore1", function(value, element) {
	            var fromDateString = $('#date').val(); //
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
	            
	        }, "Closure date must be after Date");
	    	
	    	$.validator.addMethod("dateBefore2", function(value, element) {
	            var fromDateString = $('#date').val(); //
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
	        }, "Payment Date must be after Date");
	    	
	    	$.validator.addMethod("dateBefore3", function(value, element) {
	            var fromDateString = $('#date').val(); //
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
	        }, "Investigation Completion date must be after Date");
	    	
    	
    	    $.validator.addMethod("statusCheck1", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#closure_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    	    $.validator.addMethod("statusCheck2", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#investigation_completed").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    	    $.validator.addMethod("statusCheck3", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#payment_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
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