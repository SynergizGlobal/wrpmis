<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Training - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Training - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
   
    <style>
        #session-table .datepicker~button {
            top: 26px;
        }

        #session-table input[type="text"]::-webkit-input-placeholder,
        #session-table input[type="text"]:-ms-input-placeholder,
        #session-table input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }
  
        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }
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
        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0;
        }

        .radiogroup {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        /* font size increase  */
        .input-field .searchable_label {
            font-size: 0.9rem;
        }
		input[type="text"].timepicker{
			font-size:1rem;
		}		
        .timepicker~button {
            position: absolute;
            /* right: 15px; */
            right: 0;
            /* top: 32px; */
            top: 12px;
            border: 0;
            opacity: 0.7;
            cursor: pointer;
            background-color: transparent;
        }
        
        .attendees-column{     
        	padding: 0.43rem .65rem !important;
        }
        .attendees-column >.btn{
        	padding:0 10px;
        }

        .timepicker~button .fa {
            font-size: 1.3rem;
            color: #333;
        }

        /* modal header styling  */
        .modal-header {
            text-align: center;
            background-color: #007a7a;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .table-inside tbody td p {
            margin-bottom: 0;
            margin-top: 10px;
        }

        input[type="checkbox"]+label:before {
            border: 2px solid indigo;
            background: transparent;
        }

        input[type="checkbox"]:checked+label:before {
            border: 2px solid transparent;
            border-bottom: 2px solid indigo;
            border-right: 2px solid indigo;
            background: transparent;
        }

        .materialize-textarea::placeholder {
            color: #777;
        }
        .modal .select2-container {
        	width:110px !important;
    		max-width: 160px;
    		min-width:160px; 
        }
        .modal .attendee-dropdown >.select2-container{
        	min-width: 250px;
   			max-width: 300px;
        }
        .select2-container--default .select2-selection--single{
        	background-color:transparent;
        }
        .modal{
        	max-height:90%;
        	width:68%;
        }
        .modal-content .row.fixed-width,.row.fixed-width{
        	margin:0;
        }
        
        .select2-container.select2-container--default.select2-container--open{
        	z-index:1034;
        }
        .row.no-mar{
        	margin-bottom:0;
        }
     
        .fw-110{
            max-width: 110px;
		    width: 110px;
		    min-width: 110px;
    	}
    	.fw-400{
        	width: 400px;
    		max-width: 400px;
    	}
     	.filevalue {
            display: block;
            margin-top: 5px;
            /* max-width:250px; */
        }
        .error-msg label{color:red!important;}   
       
        .input-field>textarea+label:not(.label-icon).active{
        	margin-top:0;
        }
        .input-field>label:not(.label-icon).active{
        	margin-top:5px;
        }
        .modal-content .mdl-data-table thead tr:hover{
        	background-color:#007a7a;
        }
        .mt-brdr{
			margin-top: 20px;
		    border-top: 1px solid #777;
		    border-bottom: 1px solid #777;
		}
		.center-align.m-1 button.bg-m.waves-light, 
		.center-align.m-1 button.bg-s.waves-light{
			width:inherit;
		}
		
		.pos-rel{
			position:relative;
		}
		.disp-init{
			display:initial;
		}
		.fw-220{
			width:220px ;
			min-width:220px;
		}
		@media only screen and (min-width: 993px){	
			div[id^=session-update-modal].modal.open{
				width:90%;
			}			
		}		
		.date-holder{
		    color: white;
		    margin-top: 2rem;
		    font-size: 2rem;
		}
		.date-holder >.date-text{
		    font-size: 2.5rem;
		    margin-top: 2rem;
		}
		@media only screen and (max-width: 768px){
			.date-holder{
			    margin-top: 1rem;
			    font-size: 1rem;
			    margin-bottom:2rem;
			    text-align:center;
			}
			.date-holder >.date-text{
			    font-size: 1.5rem;
			    margin-top: 0;		
			    margin-left:1rem;        
			}
			.date-holder >.date-text,
			.date-holder >.year-text{
			    display:inline-block; 
			}
			
			.modal.datepicker-modal > .modal-content,
			.modal.timepicker-modal > .modal-content{
			    padding:0;  
			}
			.modal:not(.datepicker-modal){
	        	max-height:80%;
	        	width:85%;
	        }
	        .pos-rel{
	        	width:100%;	        
	        }
	        .modal .select2-container{
	        	max-width:inherit;
	        }	        
	        .modal:not(.datepicker-modal).modal-header{
	        	margin: -24px -12px 20px !important;
    			font-size: 1.4rem;
	        }
	        .modal:not(.datepicker-modal) .modal-content{
	        	padding:24px 12px;
	        }
	        .modal .attendee-dropdown >.select2-container{
	        	min-width: auto;
	   			max-width: inherit;
	        }
	        .mobile_responsible_table .input-field input.timepicker{
	        	width:100% ;
	        }
	        .timepicker~button{
	        	right:-10px;
	        }
			td.cell-disp-inb .file-path-wrapper {
			    visibility: hidden;
			    width: 2px;
			    margin-bottom: 0;
			}
			.pos-rel{
				white-space:inherit !important;
			}
		}
			@media only screen and (max-width: 769px) and (min-width: 500px){ 
			input.timepicker~button{
	        	right:25px;
	        }	
	        .mobile_responsible_table .input-field input.timepicker{
	        	width:90% ;
	        }	 
		}
		.mw-150{
			width: 150px !important;
		    max-width: 150px !important;
		}
		@media only screen and (min-width:769px){
			.py-0{
				padding:auto 0;
			}
		}
		#msg-text{
			text-align: left;
			padding: 4px;
		}
    </style>
</head>

<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<script>
		var dateTimesInits=0;
	</script>
	<!-- card  -->
	<div class="row">
		<div class="col s12 m12">
			<div class="card ">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m m-b-2">
								<h6>
									<c:if test="${action eq 'edit'}">Update Training</c:if>
									<c:if test="${action eq 'add'}"> Add Training</c:if>
								</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
						<c:if test="${action eq 'edit'}">
							<form action="<%=request.getContextPath() %>/update-training" id="trainingForm" name="trainingForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						</c:if>
						<c:if test="${action eq 'add'}">
							<form action="<%=request.getContextPath() %>/add-training" id="trainingForm" name="trainingForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						</c:if>
					<div class="container container-no-margin">
						<div class="row">	
							<c:if test="${action eq 'edit'}">						 
								<div class="col s6 offset-m2 m4 input-field">								
									<p class="primary-text-bold">Training ID : <span>${trainingDetails.training_id }</span></p>								
								</div>
							</c:if>
							<div class="col s6 m4 input-field <c:if test="${action eq 'add'}">offset-m2 </c:if>">
								<p class="searchable_label">Training Type <span class="required">*</span></p>
								<select class="searchable validate-dropdown"
									name="training_type_fk" id="training_type_fk">
									<option value="">Select Training Type</option>
									<c:forEach var="obj" items="${trainingTypesList}">
										<option value="${obj.training_type_fk }"
											<c:if test="${trainingDetails.training_type_fk eq obj.training_type_fk }">selected</c:if>>${obj.training_type_fk }</option>
									</c:forEach>
								</select> 
								<span id="training_typeError" class="error-msg"></span>
							</div>
							 
						</div>

						<div class="row">
							 
							<div class="col s6 offset-m2 m4 input-field">
								<p class="searchable_label">Category <span class="required">*</span></p>
								<select class="searchable validate-dropdown"
									name="training_category_fk" id="training_category_fk">
									<option value="">Select Category </option>
									<c:forEach var="obj" items="${categoriesList}">
										<option value="${obj.training_category_fk }"
											<c:if test="${trainingDetails.training_category_fk eq obj.training_category_fk }">selected</c:if>>${obj.training_category_fk }</option>
									</c:forEach>
								</select> 
								<span id="training_category_fkError" class="error-msg"></span>
							</div>
							<div class="col s6 m4 input-field">
								<p class="searchable_label">Status <span class="required">*</span></p>
								<select class="searchable validate-dropdown" name="status_fk"
									id="status_fk">
									<option value="">Select Status</option>
									<c:forEach var="obj" items="${statusList}">
										<option value="${obj.status_fk }"
											<c:if test="${trainingDetails.status_fk eq obj.status_fk }">selected</c:if>>${obj.status_fk }</option>
									</c:forEach>
								</select> 
								<span id="status_fkError" class="error-msg"></span>
							</div>
							 
						</div>

						<div class="row">
							 
							<div class="col s6 offset-m2 m4 input-field ">
								<input id="faculty_name" name="faculty_name" type="text" class="validate" value="${trainingDetails.faculty_name }">
								<label for="faculty_name">Faculty <span class="required">*</span></label>
								<span id="faculty_nameError" class="error-msg"></span>
							</div>
							<div class="col s6 m4 input-field ">
								<input id="designation" type="text" name="designation" class="validate" value="${trainingDetails.designation }"> 
								<label for="designation">Designation</label>
								<span id="designationError" class="error-msg"></span>
							</div>
							 
						</div>
						<input type="hidden" name="training_id" value="${trainingDetails.training_id }"  id="training_id"/>
						<div class="row">
							 
							<div class="col s12 m8 input-field offset-m2">
								<textarea id="title" name="title" class="pmis-textarea">${trainingDetails.title }</textarea>
								<label for="title">Title <span class="required">*</span></label>
								<span id="titleError" class="error-msg"></span>
							</div>
							 
						</div>
						<div class="row">
							 
							<div class="col s12 m8 input-field offset-m2">
								<textarea id="description" name="description" class="pmis-textarea">${trainingDetails.description }</textarea>
								<label for="description">Description <span class="required">*</span></label>
								<span id="descriptionError" class="error-msg"></span>
							</div>
							 
						</div>

						<div class="row">
							 
							<div class="col s12 m8 input-field offset-m2">
								<!-- <input id="training_center" type="text" class="validate" style="margin-top: 10px;">
                                    <label for="training_center"> Training Center </label> -->
								<textarea id="training_center" name="training_center"
									class="pmis-textarea">${trainingDetails.training_center }</textarea>
								<label for="training_center">Training Center</label>
							</div>
							 
						</div>
					</div>
					
				<div class="row">
    				<div class="col m12 s12">        
						<div class="row fixed-width">
							<h5 class="center-align">Sessions</h5>
							<div class="table-inside">
								<table id="session-table" class="mdl-data-table mobile_responsible_table">
									<thead>
										<tr>
											<th class="fw-110">Session No</th>	
											<th>Start Time</th>
											<th>End Time</th>
											<th>Attendees</th>
											<th class="fw-400">Remarks</th>
											<th>Attachment</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="trainingTableBody">
										<c:choose>
											<c:when
												test="${not empty trainingDetails.trainingSessions && fn:length(trainingDetails.trainingSessions) gt 0 }">
												<c:forEach var="tObj"
													items="${trainingDetails.trainingSessions }"
													varStatus="index">
													<tr id="trainingRow${index.count }">
														<td data-head="Session No" class="input-field"><input type="hidden" name="training_session_ids" id="training_session_ids${index.count }" value="${tObj.training_session_id}" />
														 <input id="session_nos${index.count }" name="session_nos" type="text" class="validate" value="${tObj.session_no }"
															placeholder="Session No"></td>
														<td data-head="Start Time"  class="input-field"><div class="pos-rel">
															<input id="start_times${index.count }"  name="start_times" type="text" class="validate timepicker" value="${tObj.start_time }"
															placeholder="Start Time">
															<button type="button" id="start_time_icon${index.count }"><i class="fa fa-clock-o"></i></button></div></td>
														<td data-head="End Time"  class="input-field"><div class="pos-rel"><input id="end_times${index.count }" name="end_times" type="text" class="validate timepicker" value="${tObj.end_time }" placeholder="End Time">
															<button type="button" id="end_time_icon${index.count }"><i class="fa fa-clock-o"></i></button></div></td>
														<td data-head="Attendees" class="attendees-column"><a href="#session-update-modal${index.count }" class="btn waves-effect waves-light bg-m t-c modal-trigger"
															onclick="showNo(this);"> Update </a>
															<div id="session-update-modal${index.count }" class="modal">
																<div class="modal-content">
																	<h5 class="modal-header">Trainee Updation Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>

																	<div class="row"> 
																	<div class="s12 m8 offset-m2 col">
																	<div class="row fixed-width" >
																		<div class="table-inside">
																			<table id="training-update-table${index.count }"
																				class="mdl-data-table val mobile_responsible_table">
																				<thead>
																					<tr>
																						<th>Department</th>																						
																						<th> &nbsp; Attendee &nbsp; </th>																						
																						<th class="py-0">Nominated</th>
																						<th>Participated</th>																						
																						<th>Action</th>
																					</tr>
																				</thead>
																				<tbody id="attendeesTableBody${index.count }">
																					<c:choose>
																						<c:when
																							test="${not empty tObj.trainingAttendees && fn:length(tObj.trainingAttendees) gt 0 }">
																							<c:forEach var="dObj" items="${tObj.trainingAttendees }" varStatus="indexx">
																								<tr id="attendeesRow0${indexx.count }${index.count }">
																									<td data-head="Department" class="input-field">
																									<input type="hidden" name="training_session_id_fks" id="training_session_id_fks0${indexx.count }${index.count }${indexx.count }"
																										 value="${dObj.training_session_id_fk}"  class="no-reset"/>
																										<input type="hidden" name="training_attendees_ids" class="no-reset" id="training_attendees_ids0${indexx.count }${index.count }${indexx.count }"
																										value="${dObj.training_attendees_id }" /> 
																										<select class="searchable no-reset validate-dropdown" name="department_fks" id="department_fks0${indexx.count }${index.count }${indexx.count }" onchange="getAttendeesList('0${indexx.count }${index.count }${indexx.count }');">
																											<option value="">Select Department</option>
																											<c:forEach var="obj"
																												items="${departmentsList}">
																												<option value="${obj.department_fk }"
																													<c:if test="${dObj.department_name eq obj.department_name }">selected</c:if>>${obj.department_name }</option>
																											</c:forEach>
																									</select> <span id="training_category_fkError" class="error-msg"></span></td>
																																															
																									<input type="hidden" name="is_new_users"/>
																									<td data-head="Attendee" class="input-field attendee-dropdown">
																										<select class="searchable no-reset validate-dropdown" name="attendees" id="attendees0${indexx.count }${index.count }${indexx.count }" onchange="setDepartment('0${indexx.count }${index.count }${indexx.count }');">
																											<option value="">Select Attendee</option>
																											<c:forEach var="obj" items="${dObj.attendeesList}">
																												<option name="${obj.department_fk }" value="${obj.attendee }" <c:if test="${dObj.attendee eq obj.attendee }">selected</c:if>>${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>
																											</c:forEach>
																										 </select>
																									</td>
																									<input type="hidden" name="hod_user_id_fks" class="no-reset" value="${dObj.hod_user_id_fk}" />
																									<input type="hidden" name="trainee_designations" class="no-reset" value="${dObj.designation}" />
																									<input type="hidden" name="mobile_nos" class="no-reset" value="${dObj.mobile_no}"/>
																									<input type="hidden"  name="emails" class="no-reset" value="${dObj.email}" >
																									<td data-head="Nominated" class="input-field">
																										<p class="disp-init">
																											<label><input type="hidden" name="required_fks" class="no-reset" value ="${dObj.required_fk}" id="required_fk0${indexx.count }${index.count }${indexx.count }" />
																											  
																											   <input type="checkbox" id="required_fks0${indexx.count }${index.count }${indexx.count }"  class="required_fks no-reset" onChange="checkBox('0${indexx.count }${index.count }${indexx.count }')"
																												 <c:if test="${dObj.required_fk eq 'Yes'}">  checked</c:if> />
																												<span></span>
																											</label>
																										</p>
																									</td>
																									<td data-head="Participated" class="input-field">
																										<p class="disp-init">
																											<label> <input type="hidden" name="participated_fks" class="no-reset" value ="${dObj.participated_fk}" id="participated_fk0${indexx.count }${index.count }${indexx.count }" /> 
																											  
																											   <input type="checkbox" id="participated_fks0${indexx.count }${index.count }${indexx.count }"  class="participated_fks no-reset" onChange="checkBoxs('0${indexx.count }${index.count }${indexx.count }')"
																												 <c:if test="${dObj.participated_fk eq 'Yes'}">    checked</c:if> />
																												<span></span>
																											</label>
																										</p>
																									</td>
																									<td class="mobile_btn_close"><a onclick="removeTrainingAttendees('0${indexx.count }${index.count }','${index.count }');"
																										class="btn waves-effect waves-light red t-c ">
																											<i class="fa fa-close"></i>
																									</a></td>
																								</tr>
																					
																							</c:forEach>
																						 </c:when>
																	 					<c:otherwise>
																							<tr id="attendeesRow0${index.count }" class="same"> 
																								<td data-head="Department" class="input-field">
																								<input type="hidden" name="training_session_id_fks" id="training_session_id_fks0${index.count }"
																									 value="${tObj.training_session_id}" />
																									 <input type="hidden" name="training_attendees_ids" id="training_attendees_ids0${index.count }" />
																									  <select class="searchable validate-dropdown" name="department_fks" id="department_fks0${index.count }"  onchange="getAttendeesList('0${index.count }');">
																											<option value="">Select Department</option>
																											<c:forEach var="obj" items="${departmentsList}">
																												<option value="${obj.department_fk }">${obj.department_name }</option>
																											</c:forEach>
																									  </select> <span id="training_category_fkError" class="error-msg"></span></td>
																								<input type="hidden"  name="is_new_users"/>
																								<td data-head="Attendee" class="input-field attendee-dropdown">
																									<select class="searchable validate-dropdown" name="attendees" id="attendees0${index.count }" onchange="setDepartment('0${index.count }');">
																											<option value="">Select Attendee</option>
																										 	<c:forEach var="obj" items="${attendeesList}">
																												<option name="${obj.department_fk }" value="${obj.attendee }">${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>
																											</c:forEach> 
																								    </select> 
																								</td>	
																								<input type="hidden" name="hod_user_id_fks"/>
																								<input type="hidden" name="trainee_designations"/>
																								<input type="hidden" name="mobile_nos"/>
																								<input type="hidden"  name="emails" class="no-reset" >
																								<td data-head="Nominated" class="input-field">
																									<p class="disp-init">
																										<label> <input type="hidden" id="required_fk0${index.count }" name="required_fks" value="No" class="req" />
																											<input type="checkbox" id="required_fks0${index.count }" /> <span></span>
																										</label>
																									</p>
																								</td>
																								<td data-head="Participated" class="input-field">
																									<p class="disp-init">
																										<label> <input type="hidden" id="participated_fk0${index.count }" name="participated_fks" value="No" class="part" /> 
																											<input type="checkbox" id="participated_fks0${index.count }"  /> <span></span>
																										</label>
																									</p>
																								</td>
																								<td class="mobile_btn_close"><a onclick="removeTrainingAttendees('0${index.count }','${index.count }');"	class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>
																							</tr>
																							<script>
																									
																			                       	 $('#required_fks0${index.count }').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             {
																			                                 $('#required_fk0${index.count }').val('Yes');
																			                             }else{
																			                              	  $("#required_fk0${index.count }").val('No')
																			                            	  $("#required_fk0${index.count }").prop('checked',false).removeAttr('checked');
																			                              }
																			                   	    });
																			                    	 $('#participated_fks0${index.count }').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             {
																			                                 $('#participated_fk0${index.count }').val('Yes');
																			                             } else{
																			                              	  $("#participated_fk0${index.count }").val('No')
																			                            	  $("#participated_fk0${index.count }").prop('checked',false).removeAttr('checked');;
																			                              }
																			                   	    });
																			                    	 $(document).ready(function(){
																				                    	 $('#department_fks0${index.count }').select2();
																										// $('#attendees0${index.count }').select2();																			                    		 
																			                    	 });
												                            
												                            				</script>
																						</c:otherwise> 
																					</c:choose>
																				</tbody>
																			</table>

																			<table class="mdl-data-table">
																				<tbody id="trainingUpdateBody">
																					<tr>
																						<td colspan="5" ><a type="button" class="btn waves-effect waves-light bg-m t-c "
																							onclick="addTrainingUpdateRow('${tObj.training_session_id}','${index.count }')"> <i class="fa fa-plus"></i></a>
																					</tr>																					
																				</tbody>
																			</table>
																			<c:choose>
																				<c:when
																					test="${not empty (tObj.trainingAttendees) && fn:length(tObj.trainingAttendees) gt 0 }">
																					<input type="hidden" id="trainNo" name="trainNo" value="${fn:length(tObj.trainingAttendees) }" />
																				</c:when>
																				<c:otherwise>
																					<input type="hidden" id="trainNo" name="trainNo" value="0" />
																				</c:otherwise>
																			</c:choose>
																		</div>
																	</div> 
																	</div> 
																	</div>
																	<p id="msg-text"><b>If name of attendee is not in list please add below</b></p> 
																	<div class="row fixed-width">
																		<div class="table-inside">
																			<table id="training-new-update-table${index.count }"
																				class="mdl-data-table val mobile_responsible_table">
																				<thead>
																					<tr>
																						<th>Department</th>
																						<th>HOD</th>
																						<th>Attendee</th>
																						<th>Designation</th>
																						<th class="fw-220">Email </th>
																						<th class="mw-150"> Mobile </th>
																						<th class="py-0">Nominated</th>
																						<th>Participated</th>																						
																						<th>Action</th>
																					</tr>
																				</thead>
																				<tbody id="newAttendeesTableBody${index.count }">
																				<input type="hidden" id="rowsCounts${index.count }" name="rowsCounts"/>	
																					<c:choose>
																						<c:when
																							test="${not empty tObj.trainingNewList && fn:length(tObj.trainingNewList) gt 0 }">
																							<c:forEach var="dObj" items="${tObj.trainingNewList }" varStatus="indexx">
																								<tr id="newAttendeesRow0${indexx.count }${index.count }">
																								
																									<td data-head="Department" class="input-field">
																									<input type="hidden" class="no-reset" name="training_session_id_fks" id="new_training_session_id_fks0${indexx.count }${index.count }${indexx.count }"
																										 value="${dObj.training_session_id_fk}" />
																										<input type="hidden" class="no-reset" name="training_attendees_ids" id="new_training_attendees_ids0${indexx.count }${index.count }${indexx.count }"
																										value="${dObj.training_attendees_id }" /> 
																										<select class="searchable no-reset validate-dropdown" name="department_fks" id="new_department_fks0${indexx.count }${index.count }${indexx.count }" onchange="getHODsList('0${indexx.count }${index.count }${indexx.count }');">
																											<option value="">Select Department</option>
																											<c:forEach var="obj"
																												items="${departmentsList}">
																												<option value="${obj.department_fk }"
																													<c:if test="${dObj.department_name eq obj.department_name }">selected</c:if>>${obj.department_name }</option>
																											</c:forEach>
																									</select> <span id="new_training_category_fkError" class="error-msg"></span></td>																									
																									<td data-head="HOD" class="input-field">																											
																                                        <select class="searchable no-reset" name="hod_user_id_fks" id="new_hod_user_id_fks0${indexx.count }${index.count }${indexx.count }" onchange="setHODDeptList('0${indexx.count }${index.count }${indexx.count }');">
																                                            <option value="" >Select HOD</option>  
																                                            <c:forEach var="obj" items="${dObj.HODsList}">
																												<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }"<c:if test="${dObj.hod_user_id_fk eq obj.hod_user_id_fk }">selected</c:if>>${obj.designation }</option>
																											</c:forEach>                                         
																                                        </select>                                   
																									</td>
																									<input type="hidden"  name="is_new_users" class="no-reset" value="Yes"/>
																									<td data-head="Attendee" class="input-field">
																										 <input id="new_attendees0${indexx.count }${index.count }${indexx.count }" name="attendees" type="text" class="validate no-reset" placeholder="Name" value="${dObj.attendee}">
																									</td>
																									<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" id="trainee_designations0${indexx.count }${index.count }${indexx.count }" name="trainee_designations" value="${dObj.trainee_designation}" class="no-reset"></td>		
																									<td data-head="Email" class="input-field"> <input type="text" placeholder="Email" id="email0${indexx.count }${index.count }${indexx.count }" name="emails" value="${dObj.email}" class="no-reset"></td>		
																									<td data-head="Mobile" class="input-field"><input id="new_mobile_nos0${indexx.count }${index.count }${indexx.count }" name="mobile_nos" class="no-reset" type="number" class="validate" placeholder="Mobile"
																										value="${dObj.mobile_no }"></td>
																									<td data-head="Nominated" class="input-field">
																										<p class="disp-init">
																											<label><input type="hidden" class="no-reset" name="required_fks" value ="${dObj.required_fk}" id="new_required_fk0${indexx.count }${index.count }${indexx.count }" />
																											  
																											   <input type="checkbox" id="new_required_fks0${indexx.count }${index.count }${indexx.count }"  class="required_fks no-reset" onChange="checkBox('0${indexx.count }${index.count }${indexx.count }')"
																												 <c:if test="${dObj.required_fk eq 'Yes'}">  checked</c:if> />
																												<span></span>
																											</label>
																										</p>
																									</td>
																									<td data-head="Participated" class="input-field">
																										<p class="disp-init">
																											<label> <input type="hidden" name="participated_fks" class="no-reset" value ="${dObj.participated_fk}" id="new_participated_fk0${indexx.count }${index.count }${indexx.count }" /> 
																											  
																											   <input type="checkbox" id="new_participated_fks0${indexx.count }${index.count }${indexx.count }"  class="participated_fks no-reset" onChange="checkBoxs('0${indexx.count }${index.count }${indexx.count }')"
																												 <c:if test="${dObj.participated_fk eq 'Yes'}">    checked</c:if> />
																												<span></span>
																											</label>
																										</p>
																									</td>
																									<td class="mobile_btn_close"><a onclick="removeNewTrainingAttendees('0${indexx.count }${index.count }','${index.count }');"
																										class="btn waves-effect waves-light red t-c ">
																											<i class="fa fa-close"></i>
																									</a></td>
																								</tr>
																							 <script>
																									 var len = $("#newAttendeesTableBody${index.count } tr").length + $("#attendeesTableBody${index.count }  tr").length
																									 $("#rowsCounts${index.count }").val(len);
																									 $('#new_required_fks0${indexx.count }${index.count }${indexx.count }').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             { 
																			                            	 //$(".req").prop('disabled', true);
																			                                 $('#new_required_fk0${indexx.count }${index.count }${indexx.count }').val('Yes');
																			                             }else{
																			                              	  $("#new_required_fk0${indexx.count }${index.count }${indexx.count }").val('No')
																			                            	  $("#new_required_fk0${indexx.count }${index.count }${indexx.count }").prop('checked',false).removeAttr('checked');
																			                              }
																			                   	    });
																			                    	 $('#new_participated_fks0${indexx.count }${index.count }${indexx.count }').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             {
																			                            	// $(".part").prop('disabled', true);
																			                                $("#new_participated_fk0${indexx.count }${index.count }${indexx.count }").val('Yes');
																			                             } else{
																			                              	  $("#new_participated_fk0${indexx.count }${index.count }${indexx.count }").val('No')
																			                            	  $("#new_participated_fk0${indexx.count }${index.count }${indexx.count }").prop('checked',false).removeAttr('checked');;
																			                              }
																			                   	    });
																			                    	 $(document).ready(function(){
																				                    	 $('#new_department_fks0${indexx.count }${index.count }${indexx.count }').select2();
																										 $('#new_hod_user_id_fks0${indexx.count }${index.count }${indexx.count }').select2();																			                    		 
																			                    	 });
																								</script> 
																							</c:forEach>
																						 </c:when>
																	 					<c:otherwise>
																							<tr id="newAttendeesRow0${index.count }">
																								<td data-head="Department" class="input-field">
																								<input type="hidden" name="training_session_id_fks" id="new_training_session_id_fks0${index.count }"
																									 value="${tObj.training_session_id}" />
																									 <input type="hidden" name="training_attendees_ids" id="new_training_attendees_ids0${index.count }" />
																									  <select class="searchable validate-dropdown" name="department_fks" id="new_department_fks0${index.count }" onchange="getHODsList('0${index.count }');">
																											<option value="">Select Department</option>
																											<c:forEach var="obj" items="${departmentsList}">
																												<option value="${obj.department_fk }">${obj.department_name }</option>
																											</c:forEach>
																									  </select> <span id="new_training_category_fkError" class="error-msg"></span></td>
																								<td data-head="HOD" class="input-field">																											
																                                        <select class="searchable" name="hod_user_id_fks" id="new_hod_user_id_fks0${index.count }" onchange="setHODDeptList('0${index.count }');">
																                                            <option value="" >Select HOD</option>  
																                                        <c:forEach var="obj" items="${usersList}">
																												<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }">${obj.designation }</option>
																											</c:forEach>                                            
																                                        </select>                                   
																								</td>
																								<%-- <input type="hidden" id="rowsCounts${index.count }" name="rowsCounts"/>	 --%>
																								<input type="hidden"  name="is_new_users" value="Yes"/>
																								<td data-head="Attendee" class="input-field">																									
																									<input id="new_attendees0${index.count }" name="attendees" type="text" class="validate" placeholder="Name">																								    
																								</td>	
																								<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" id="new_trainee_designations0${index.count }" name="trainee_designations" ></td>																							
																								<td data-head="Email" class="input-field"> <input type="text" placeholder="Email" id="email0${index.count }" name="emails" ></td>																							
																								<td data-head="Mobile" class="input-field"><input id="new_mobile_nos0${index.count }" name="mobile_nos" type="number" class="validate" placeholder="Mobile">
																								</td>
																								<td data-head="Nominated" class="input-field">
																									<p class="disp-init">
																										<label> <input type="hidden" id="new_required_fk0${index.count }" name="required_fks" value="No" class="req" />
																											<input type="checkbox" id="new_required_fks0${index.count }" /> <span></span>
																										</label>
																									</p>
																								</td>
																								<td data-head="Participated" class="input-field">
																									<p class="disp-init">
																										<label> <input type="hidden" id="new_participated_fk0${index.count }" name="participated_fks" value="No" class="part" /> 
																											<input type="checkbox" id="new_participated_fks0${index.count }"  /> <span></span>
																										</label>
																									</p>
																								</td>
																								<td class="mobile_btn_close"><a onclick="removeNewTrainingAttendees('0${index.count }','${index.count }');"	class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>
																							</tr>
																							<script>
																									 var len = $("#newAttendeesTableBody${index.count } tr").length + $("#attendeesTableBody${index.count }  tr").length
																									 $("#rowsCounts${index.count }").val(len);
																			                       	 $('#new_required_fks0${index.count }').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             { 
																			                            	 //$(".req").prop('disabled', true);
																			                                 $('#new_required_fk0${index.count }').val('Yes');
																			                             }else{
																			                              	  $("#new_required_fk0${index.count }").val('No')
																			                            	  $("#new_required_fk0${index.count }").prop('checked',false).removeAttr('checked');
																			                              }
																			                   	    });
																			                    	 $('#new_participated_fks0${index.count }').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             {
																			                            	// $(".part").prop('disabled', true);
																			                                 $('#new_participated_fk0${index.count }').val('Yes');
																			                             } else{
																			                              	  $("#new_participated_fk0${index.count }").val('No')
																			                            	  $("#new_participated_fk0${index.count }").prop('checked',false).removeAttr('checked');;
																			                              }
																			                   	    });
																			                    	 $(document).ready(function(){
																				                    	 $('#new_department_fks0${index.count }').select2();
																										 //$('#new_attendees0${index.count }').select2();																			                    		 
																			                    	 });																									 
																									
												                            				</script>
																						</c:otherwise> 
																					</c:choose>
																				</tbody>
																			</table>

																			<table class="mdl-data-table" style="margin-bottom:20px;">
																				<tbody id="trainingUpdateBody">
																					<tr>
																						<td colspan="7" ><a type="button" class="btn waves-effect waves-light bg-m t-c "
																							onclick="addNewTrainingUpdateRow('${tObj.training_session_id}','${index.count }')"> <i class="fa fa-plus"></i></a>
																					</tr>
																					<!-- <tr>
																						<td colspan="7" ><a type="button" class="btn waves-effect waves-light bg-m t-c modal-action modal-close">Update</a>
																					</tr> -->
																				</tbody>
																			</table>
																			<c:choose>
																				<c:when
																					test="${not empty (tObj.trainingNewList) && fn:length(tObj.trainingNewList) gt 0 }">
																					<input type="hidden" id="newTrainNo" name="newTrainNo" value="${fn:length(tObj.trainingNewList) }" />
																				</c:when>
																				<c:otherwise>
																					<input type="hidden" id="newTrainNo" name="newTrainNo" value="0" />
																				</c:otherwise>
																			</c:choose>
																			<div class="row" style="margin:0;">    
																				<div class="col s6 m4 offset-m2 center-align">        
																					<a type="button" class="btn waves-effect waves-light modal-close bg-m t-c"> Update</a>    
																				</div>   
																				<div class="col s6 m4 center-align">    
																				    <button type="button" class="btn waves-effect waves-light modal-close t-c bg-s" onclick="resetModal(this)">Cancel</button>     
																				</div>     
																			</div>
																	</div> 
																	
																</div>
															</div></td>
														<td data-head="Remarks" class="input-field"><textarea id="remarkss${index.count }" name="remarkss" class="materialize-textarea" placeholder="Remarks">${tObj.remarks }</textarea></td>
														  <td data-head="Attachment" class="input-field cell-disp-inb">
                                                            <!-- <div class="">
                                                                <input type="file" name="myfile" id="myFile0"
                                                                    onchange="getFileName(0)" style="display:none" />
                                                                <label for="myFile0" class="btn bg-m"><i
                                                                        class="fa fa-paperclip"></i></label>
                                                                <span id="fileVal0" class="filevalue"></span>
                                                            </div> -->
                                                            
                                                            
                                                            	<c:set var="existingTrainingFilesLength" value="${fn:length(tObj.trainingFilesList )}"></c:set>
													<c:if test="${fn:length(tObj.trainingFilesList ) gt 0}">
														<c:set var="existingTrainingFilesLength" value="${fn:length(tObj.trainingFilesList )+1}"></c:set>
													</c:if>
													<div id="selectedFilesInput${index.count }">
				                                    	<div class="file-field input-field" id="trainingSessionFilesDiv${index.count }${fn:length(tObj.trainingFilesList) }" >
					                                        <div class="btn bg-m t-c"> <span>Attach Files</span>
					                                        	<c:if test="${ empty tObj.trainingFilesList }">
					                                        			<input type="hidden" id="trainingSessionFileNames${index.count }" name="trainingSessionFileNames" value="">
												           		 </c:if>
					                                           		<input type="hidden" id="len${index.count }"  value="${fn:length(tObj.trainingFilesList) }">
					                                           
					                                            <input type="file" id="trainingSessionFiles${index.count }${fn:length(tObj.trainingFilesList) }" name="trainingSessionFiles"  onchange="selectFileUpdate('${index.count }${fn:length(tObj.trainingFilesList) }','${index.count }')">
					                                        </div>
					                                        
					                                        <div class="file-path-wrapper" > 
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    <div id="selectedFiles${index.count }" class="disp-in">
				                                    	<c:forEach var="obj" items="${tObj.trainingFilesList }" varStatus="indexx">
															 <div id="trainingSessionFilesNames${index.count }${indexx.count }">
																<a href="<%=CommonConstants.TRAINING_SESSIONS %>${obj.attachment }" class="filevalue" download>${obj.attachment } 
																<span onclick="removeFileUpdate('${index.count }${indexx.count }','${index.count }')" class="attachment-remove-btn">X</span> </a>
																<input type="hidden" id="trainingSessionFileNames${index.count }${indexx.count }" name="trainingSessionFileNames" value="${obj.attachment }">
														     </div>
														     <div style="clear:both" class="hide" id="hide${index.count }${indexx.count }"><input type="hidden" id="filecounts${index.count }${indexx.count }" name="filecounts" value="${indexx.count }"></div>
														     <script>
														     var count = ('${index.count }${indexx.count }') - 1;
																var lastNo = $('#selectedFiles${index.count }${indexx.count }  input').last().val('${indexx.count }');
																var s = $('#hide'+count+' input').val();
																if(s != null){
																	$('#hide'+count+' input').removeAttr('name');
																	
																}
															</script>
														</c:forEach>
														<div id="hideVal${index.count }">
														<c:if test="${ empty tObj.trainingFilesList }">
														<input type="hidden" id="filecounts${index.count }" name="filecounts" value="0">
												            </c:if></div>
													</div>
													
													<%-- 
                                                            <div class="">
			                                                   	<input type="file" name="trainingSessionFiles" id="trainingSessionFiles${index.count }"  onchange="getFileName('${index.count }')"  style="display:none"  />
			                                                   	<label for="trainingSessionFiles${index.count }" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
																<a id="fileVal${index.count }" class="filevalue" href="<%=CommonConstants.TRAINING_SESSIONS %>${tObj.attachment }" download>${tObj.attachment }</a> 
															 </div>                                              
													         <input type="hidden" id="trainingSessionFileNames${index.count }" name="trainingSessionFileNames" value="${tObj.attachment }">
                                                         --%></td>
														<td class="mobile_btn_close right"><a onclick="removeTraining('${index.count }');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
													</tr>
													<script>
                                          				dateTimesInits++;                                         
                                          			 </script>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr id="trainingRow0">
													<td data-head="Session No"  class="input-field" ><input type="hidden" name= "training_session_ids" id="training_session_ids0"  value="${tObj.training_session_id}"/>
														<input id="session_nos0" name="session_nos" type="text" class="validate" placeholder="Session No"></td>
													<td data-head="Start Time"  class="input-field"><div class="pos-rel"><input id="start_times0" name="start_times" type="text" class="validate timepicker" placeholder="Start Time">
														<button type="button" id="start_time_icon0"><i class="fa fa-clock-o"></i></button></div></td>
													<td data-head="End Time"  class="input-field"><div class="pos-rel"><input id="end_times0" name="end_times" type="text" class="validate timepicker" placeholder="End Time">
														<button type="button" id="end_time_icon0"><i class="fa fa-clock-o"></i></button></div></td>
													<td data-head="Attendees" class="input-field attendees-column" ><a href="#session-update-modal0" class="btn waves-effect waves-light bg-m t-c modal-trigger" onclick="showNo(this)"> Update </a>
														<div id="session-update-modal0" class="modal">
															<div class="modal-content">
																<h4 class="modal-header">Trainee Updation Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h4>
																<div class="row">
																    <div class="s12 m8 offset-m2 col">
																        <div class="row fixed-width">
																            <div class="table-inside">
																                <table id="training-update-table0" class="mdl-data-table val mobile_responsible_table">
																                    <thead>
																                        <tr>
																                            <th>Department</th>
																                            <th>Attendee</th>
																                            <th class="py-0">Nominated</th>
																                            <th>Participated</th>
																                            <th>Action</th>
																                        </tr>
																                    </thead>
																                    <tbody id="attendeesTableBody0">
																                        <tr id="attendeesRow0">
																                            <td data-head="Department" class="input-field">
																                              
																                                    <input type="hidden" name="training_session_id_fks" id="training_session_id_fks0"> 
																                                    <input type="hidden"name="training_attendees_ids" id="training_attendees_ids0"> 
																                                    <select class="searchable validate-dropdown " name="department_fks" id="department_fks0" onchange="getAttendeesList('0');">
																                                   		<option value="" >Select Department</option> 
																                                   		<c:forEach var="obj" items="${departmentsList}">
																											<option value="${obj.department_fk }">${obj.department_name }</option>
																										</c:forEach>
																                                    </select>
																                            </td><input type="hidden"  name="is_new_users"/>
																                            <td data-head="Attendee" class="input-field attendee-dropdown">
																                                <select class="searchable validate-dropdown " name="attendees"
																                                    id="attendees0" onchange="setDepartment(0);">   
																                                    <option value="" >Select Attendee</option>
																                                   <c:forEach var="obj" items="${attendeesList}">
																										'<option name="${obj.department_fk }" value="${obj.attendee }">${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>'+
																									</c:forEach>                                  
																                                </select>
																                                </td>
																                                <input type="hidden" name="hod_user_id_fks"/>
																								<input type="hidden" name="trainee_designations"/>
																								<input type="hidden" name="mobile_nos"/>
																								<input type="hidden"  name="emails" class="no-reset" >
																                            <td data-head="Nominated" class="input-field">
																                                <p class="disp-init"> <label><input type="hidden" name="required_fks"
																                                            id="required_fk0"> <input type="checkbox" id="required_fks0"
																                                            class="required_fks" onchange="checkBox(0)"><span></span></label></p>
																                            </td>
																                            <td data-head="Participated" class="input-field">
																                                <p class="disp-init"><label> <input type="hidden" name="participated_fks"
																                                            id="participated_fk0"><input type="checkbox" id="participated_fks0"
																                                            class="participated_fks" onchange="checkBoxs(0)"><span></span></label></p>
																                            </td>
																                            <td class="mobile_btn_close"><a onclick="removeTrainingAttendees(0,0);"
																                                    class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
																                        </tr>
																                        
																                    </tbody>
																                </table> <input type="hidden" id="trainNo" name="trainNo" value="1">
																                <table class="mdl-data-table">
																                    <tbody id="trainingUpdateBody">
																                        <tr>
																                            <td colspan="7"> <a type="button" class="btn waves-effect waves-light bg-m t-c "
																                                    onclick="addTrainingUpdateRow('','0')"> <i class="fa fa-plus"></i></a> </td>
																                        </tr>
																                    </tbody>
																                </table>
																            </div>
																        </div>
																    </div>
																</div>
																<p id="msg-text"><b>If name of attendee is not in list please add below</b></p>
															   	<div class="row fixed-width">																
																	<div class="table-inside"> 
																		<table id="training-new-update-table0"	class="mdl-data-table mobile_responsible_table">
																			<thead>
																				<tr>
																					<th>Department</th>
																					<th>HOD</th>
																					<th>Attendee</th>
																					<th>Designation</th>
																					<th class='fw-220'>Email </th>
																					<th class="mw-150"> Mobile</th>
																					<th class="py-0">Nominated</th>
																					<th>Participated</th>
																					<th>Action</th>
																				</tr>
																			</thead>
																			<tbody id="newAttendeesTableBody00">
																			<input type="hidden" id="rowsCounts0" name="rowsCounts" value="2"/>
																				<tr id="newAttendeesRow00">
																					<td data-head="Department" class="input-field">
																					<input type="hidden" name="training_attendees_ids" id="training_attendees_ids00" value="${tObj.training_session_id_fk}"/> 
																					<select class="searchable validate-dropdown" name="department_fks" id="new_department_fks00" onchange="getHODsList('00');">
																							<option value="">Select Department</option>
																							<c:forEach var="obj" items="${departmentsList}">
																								<option value="${obj.department_fk }">${obj.department_name }</option>
																							</c:forEach>
																					</select> <!-- //pattern="[6-7-9]{1}[0-9]{9}" --> 
																					<td data-head="HOD" class="input-field"> <select class="searchable" name="hod_user_id_fks" id="new_hod_user_id_fks00" onchange="setHODDeptList('00');">
																					<option value="" >Select HOD</option>
																					 <c:forEach var="obj" items="${usersList}">
																							<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }">${obj.designation }</option>
																					</c:forEach>   
																					</select>    
																					</td>
																					<input type="hidden"  name="is_new_users" value="Yes"/>
																					<td data-head="Attendee" class="input-field">																						
																						 <input id="new_attendees00" name="attendees" type="text" class="validate" placeholder="Name">
																					</td>
																					<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" id="new_trainee_designations0" name="trainee_designations" ></td>
																					<td data-head="Email" class="input-field"> <input type="text" placeholder="Email" id="email0" name="emails" ></td>
																					<td data-head="Mobile" class="input-field"><input id="new_mobile_nos00" name="mobile_nos" type="number" class="validate num" placeholder="Mobile">
																					<br><span id="new_mobile_nosError" class="error-msg"></span></td>
																					<td data-head="Nominated" class="input-field">
																						<p class="disp-init">
																							<label> 
																								<input type="hidden" id="new_required_fk00" name="required_fks" value="No" class="req" /> 
																								<input type="checkbox" id="new_required_fks0" /> <span></span>
																							</label>
																						</p>
																					</td>
																					<td data-head="Participated" class="input-field">
																						<p class="disp-init">
																							<label> <input type="hidden" id="new_participated_fk00" name="participated_fks" value="No" class="part" />
																								<input type="checkbox" id="new_participated_fks00"  /> <span></span>
																							</label>
																						</p>
																					</td>
																					
																					<td class="mobile_btn_close"><a onclick="removeNewTrainingAttendees('00','0');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i>
																					</a></td>
																				</tr>

																			</tbody>
																		</table>
																		<script>
															                       	 $('#new_required_fks0').on('change', function(e){
															                             if($(this).prop('checked'))
															                             {
															                            	 //$(".req").prop('disabled', true);
															                                 $('#new_required_fk00').val('Yes');
															                             } else{
															                              	  $("#new_required_fk00").val('No')
															                            	  $("#new_required_fk00").prop('checked',false).removeAttr('checked');
															                              }
															                   	    });
															                    	 $('#new_participated_fks00').on('change', function(e){
															                             if($(this).prop('checked'))
															                             {
															                            	// $(".part").prop('disabled', true);
															                                 $('#new_participated_fk00').val('Yes');
															                             } else{
															                              	  $("#new_participated_fk00").val('No')
															                            	  $("#new_participated_fk00").prop('checked',false).removeAttr('checked');
															                              }
															                   	    });
												                            
												                            </script>
																		<input type="hidden" id="newTrainNo" name="newTainNo"
																			value="0" />
																		<table class="mdl-data-table" style="margin-bottom:20px;">
																			<tbody id="trainingUpdateBody">
																				<tr>
																					<td colspan="7"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																						onclick="addNewTrainingUpdateRow('00','00')"> <i class="fa fa-plus"></i></a>
																				</tr>
																			</tbody>
																		</table>
																		<div class="row" style="margin:0;">    
																			<div class="col s6 m4 offset-m2 center-align">        
																				<a type="button" class="btn waves-effect waves-light modal-close bg-m t-c"> Update</a>    
																			</div>    
																			<div class="col s6 m4 center-align">        
																				<button type="button" class="btn waves-effect waves-light modal-close t-c bg-s" onclick="resetModal(this)">Cancel</button>  
																			</div>     
																		</div>
																</div>
															</div>
														</div></td>
													<td data-head="Remarks" class="input-field"><textarea id="remarkss0" name="remarkss" class="materialize-textarea" placeholder="Remarks"></textarea>
													</td>
													  <td data-head="Attachment" class="input-field cell-disp-inb">
                                                            <!-- <div class="">
                                                                <input type="file" name="myfile" id="myFile0"
                                                                    onchange="getFileName(0)" style="display:none" />
                                                                <label for="myFile0" class="btn bg-m"><i
                                                                        class="fa fa-paperclip"></i></label>
                                                                <span id="fileVal0" class="filevalue"></span>
                                                            </div> -->
                                                              <div id="selectedFilesInput">
				                                    	<div class="file-field input-field" id="trainingSessionFilesDiv1" >
				                                    		 <div class="btn bg-m t-c">
				                                            <span>Attach Files</span>
					                                            <input type="file" id="trainingSessionFiles1" name="trainingSessionFiles"   onchange="selectFile('1')">
					                                            </div> 
					                                            <!-- <label for="trainingFiles1" class="btn bg-m"><i class="fa fa-paperclip"></i></label> -->
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    <div id="selectedFiles">
				                                    	<input type="hidden" id="trainingSessionFileNames0" name="trainingSessionFileNames" value="">
				                                    	<div id="hideVal0">
															<input type="hidden" id="filecounts0" name="filecounts" value="0">
												        </div>
													</div>
													<!-- 
                                                            <div class="">
			                                                   	<input type="file" name="trainingSessionFiles" id="trainingSessionFiles0"  onchange="getFileName('0')"  style="display:none"  />
			                                                   	<label for="trainingSessionFiles0" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
			                                                   	<span id="fileVal0" class="filevalue"></span>
															 </div>         
															 <input type="hidden" id="trainingSessionFileNames0" name="trainingSessionFileNames" >  -->
                                                        </td>
													<td class="mobile_btn_close right"><a onclick="removeTraining('0');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<table class="mdl-data-table">
									<tbody id="trainingBody">
										<tr>
											<td colspan="7" ><a type="button" class="btn waves-effect waves-light bg-m t-c "
												onclick="addSessionRow('${tObj.training_session_id}')"> <i class="fa fa-plus"></i></a>
										</tr>
									</tbody>
								</table>
								<c:choose>
									<c:when
										test="${not empty (trainingDetails.trainingSessions) && fn:length(trainingDetails.trainingSessions) gt 0 }">
										<input type="hidden" id="rowNo" name="rowNo" value="${fn:length(trainingDetails.trainingSessions) }" />
									</c:when>
									<c:otherwise>
										<input type="hidden" id="rowNo" name="rowNo" value="0" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
										
				    </div>
				</div>
					
						<div class="container container-no-margin">						
							<div class="row" style="margin-top: 20px;">
								 
								<div class="col m8 s12 offset-m2">
									<div class="row">
										<div class="col s5 m6 input-field">
											<p style="margin-top: 18px;">Any Issue ?</p>
										</div>
										<div class="col s7 m6 input-field">
											<p class="radiogroup"
												style="padding-bottom: 10px; padding-top: 10px;">
												<label> 
													<input class="with-gap" name="is_there_issue" type="radio" value="yes" /> <span>Yes</span>
												</label>
												 &nbsp; 
												 <label> 
												 	<input class="with-gap" name="is_there_issue" type="radio" value="no" /> <span>No</span>
												</label>
											</p>
										</div>
									</div>
								</div>
							</div>

						<div id="issue_yes" style="display: none;">
							<div class="row">
								<h6 class="center-align"
									style="color: #282130; font-weight: 600">Issue Details</h6>
								 
								<div class="col m8 s12 offset-m2">
									<div class="row">
										<div class="col s12 m4 l6 input-field" style="margin-top: 33px;">
											<p class="searchable_label">Issue Category</p>
											<select class="searchable validate-dropdown"
												id="issue_category_id" name="issue_category_id">
												<option value="">Select</option>
												<c:forEach var="obj" items="${issueCatogoriesList}">
													<option value="${obj.category }">${obj.category }</option>
												</c:forEach>
											</select> <span id="status_fkError" class="error-msg"></span>
										</div>
										<div class="col s12 m8 l6 input-field" style="padding-top: 4px;">
											<p class="prio">Priority</p>
											<p class="radiogroup">
												<label>
												 <input class="with-gap" name="issue_priority_id" type="radio" value="low" /> <span>Low</span>
												</label> 
												&nbsp; 
												<label>
												 <input class="with-gap" name="issue_priority_id" type="radio" value="medium" /> <span>Medium</span>
												</label>
												 &nbsp;
												<label>
												  <input class="with-gap" name="issue_priority_id" type="radio" value="high" /> <span>High</span>
												</label>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								 
								<div class="col s12 m8 input-field offset-m2"><textarea id="issue_description" name="issue_description" class="pmis-textarea" data-length="500"></textarea>
									<label for="issueDesc">Issue Description</label>
								</div>
							</div>
						</div>

						<div class="row">
							<!-- row 10 -->
							 
							<div class="col s12 m8 input-field offset-m2">
								<textarea id="remarks" class="pmis-textarea" name="remarks" id="remarks" data-length="1000">${trainingDetails.remarks }</textarea>
								<label for="remarks">Remarks</label>
							</div>
						</div>

						<div class="row">
							 
							<div class="col s6 m4 mt-brdr offset-m2 center-align">
								<div class=" m-1">
									<c:if test="${action eq 'edit'}">
										<button type="button" onclick="updateTraining();" class="btn waves-effect waves-light bg-m">Update</button>
									</c:if>
									<c:if test="${action eq 'add'}"> 
										<button type="button" onclick="addTraining();" class="btn waves-effect waves-light bg-m" style="min-width: 90px; ">Add</button>
									</c:if>
								</div>
							</div>
							<div class="col s6 m4 mt-brdr center-align">
								<div class=" m-1">
									<a href="<%=request.getContextPath()%>/training"
										class="btn waves-effect waves-light bg-s" >Cancel</a>
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

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/datetimepicker.js"></script>
	
	<script>
	
	 function selectFile(no){
		    files = $("#trainingSessionFiles"+no)[0].files;
		    var html = "";
			var count = no - 1;
				
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=trainingSessionFilesNames'+no+'>'
				 + '<a href="#" class="filevalue"> '+$(this).get(0).files[i].name+'<span onclick="removeFile('+no+','+count+')" class="attachment-remove-btn">X</span></a>'
				 + '</div>'
				 + '<div style="clear:both;" id="hide'+no+'"><input id="fileCounts'+no+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles").append(html);
		   
		    $('#trainingSessionFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			var lastfieldsid = $('#hide'+no+' input').last().val(no);
			var s = $('#hide'+count+' input').val();
			 $('#hideVal'+count+' input').removeAttr('name');
			if(s != null){
				$('#hide'+count+' input').removeAttr('name');
				
			}
			
			moreFiles(fileIndex);
		}
	 
	 function setDepartment(idNo){
		 var attendees = $("#attendees"+idNo).val();;
     	 var dept = $("#attendees"+idNo).find('option:selected').attr("name");
     	 $('#department_fks'+idNo).val(dept);
     	 $('#department_fks'+idNo).select2();
     	 if($.trim(attendees) == ""){getAttendeesList(idNo)}
	 }
	 
	 function setHODDeptList(idNo){
		 var new_hod_user_id_fks = $("#new_hod_user_id_fks"+idNo).val();;
     	 var dept = $("#new_hod_user_id_fks"+idNo).find('option:selected').attr("name");
     	 $('#new_department_fks'+idNo).val(dept);
     	 $('#new_department_fks'+idNo).select2();
     	if($.trim(new_hod_user_id_fks) == ""){getHODsList(idNo)}
	 }
	 function getAttendeesList(count){
     	var department_fk = $('#department_fks'+count).val();
     
     	$("#attendees"+count+" option:not(:first)").attr("selected",false);
       //  if ($.trim(department_fk) != "") {
          	$("#attendees"+count+" option:not(:first)").remove();
             var myParams = { department_fk: department_fk };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getAttendeesListForTrainingForm",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                         	 var userName = '';
	                        	   if($.trim(val.attendee) != ''){userName =  $.trim(val.attendee) }
                              var designation = '';
                              if ($.trim(val.designation) != '') { designation = $.trim(val.designation)+" - " }
                             
                             if ($.trim(val.attendee) != '') {
                                  $("#attendees"+count).append('<option name="' + val.department_fk + '" value="' + val.attendee + '" >'  +  $.trim(designation) + $.trim(userName) + '</option>');
                              } else {
                                  $("#attendees"+count).append('<option name="' + val.department_fk + '" value="' + val.attendee + '" >'  +  $.trim(designation) + $.trim(userName) +'</option>');
                              }
                         });
                     }
                 }
             });
         //}
	 }
	 
	 function getHODsList(count){
     	var department_fk = $('#new_department_fks'+count).val();
     	$("#new_hod_user_id_fks"+count+" option:not(:first)").attr("selected",false);
     	$("#new_hod_user_id_fks"+count+" option:not(:first)").remove();
         //if ($.trim(department_fk) != "") {
             var myParams = { department_fk: department_fk };
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getHODsListForTrainingForm",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                         	 var userName = '';
	                        	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
                              var designation = '';
                              if ($.trim(val.designation) != '') { designation = $.trim(val.designation) }
                             
                             if ($.trim(val.hod_user_id_fk) != '') {
                                  $("#new_hod_user_id_fks"+count).append('<option name="' + val.department_fk + '" value="' + val.hod_user_id_fk + '" >'  +  $.trim(designation)  + '</option>');
                              } else {
                                  $("#new_hod_user_id_fks"+count).append('<option name="' + val.department_fk + '" value="' + val.hod_user_id_fk + '" >'  +  $.trim(designation)  +'</option>');
                              }
                         });
                     }
                 }
             });
         //}
	 }
	 
	  function selectFileUpdate(no,bNo){
		    files = $("#trainingSessionFiles"+no)[0].files;
		    var html = "";
		    var s = null;
			var count = no - 1;
		    var fileIndex = Number(no)+1;
			var id = (fileIndex/10);
		    var str=id.toString();
		    var splt = str.split('.')[1];
		    var spli1 = str.split('.')[0];
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=trainingSessionFilesNames'+fileIndex+'>'
				 + '<a href="#" class="filevalue"> '+$(this).get(0).files[i].name+' <span onclick="removeFileUpdate('+fileIndex+','+bNo+')" class="attachment-remove-btn">X</span></a>'
				
				 + '<input type="hidden" id="trainingSessionFileNames'+fileIndex+'" name="trainingSessionFileNames" value="'+$(this).get(0).files[i].name+'" >'
				 + '</div>'
				 + '<div style="clear:both;" class="hide" id="hide'+fileIndex+'"><input id="fileCounts'+fileIndex+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles"+bNo).append(html);
		   
		    $('#trainingSessionFilesDiv'+no).hide();
		    var num = (splt-spli1);
		    var posNum = (num < 0) ? num * -1 : num; // if num is negative multiple by negative one ... 
		    var attr = $('#hide'+no+' input').attr('name');
		    
		    if (typeof attr == 'undefined' || attr == false) {
		      	   s = 1;
				    $('#hide'+fileIndex+' input').last().val(s);

		    }else{
		    	  s = $('#hide'+no+' input').val();
				    if(s == null){
				    	s = 0;
				    }
				    var d = $("#hide"+no+" input").attr("id");
				    if(d != null){
				    	 var v = $("#"+d).val();
						 var splt2 = d.split('s')[1];
				    }else{
				    	v = 0;
				    }
				    var lastfieldsid = $('#hide'+fileIndex+' input').last().val(Number(v)+1);

		    }
		    $('#hide'+splt2+' input').removeAttr('name');
		    $('#hideVal'+bNo+' input').removeAttr('name');
			
			//$('#trainingSessionFileNames'+bNo).removeAttr('name');
			if(s != null){
				$('#hide'+count+' input').removeAttr('name');
				
			}
			
			moreFilesUpdate(no,bNo);
		}
		
		function moreFilesUpdate(no,bNo){
			var html = "";
			var count = no;
			 var fileIndex = Number(no)+1;
			/* if(no >1 ){
				var rNo=(no-1)
				$("#fileCounts"+rNo).removeAttr('value');
			} */
			html =  html + '<div class="file-field input-field" id="trainingSessionFilesDiv'+fileIndex+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="trainingSessionFiles'+fileIndex+'" name="trainingSessionFiles"  onchange="selectFileUpdate('+fileIndex+','+bNo+')">'
			

			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput"+bNo).append(html);
		}
		function moreFiles(no){
			var html = "";
			var count = no;
			/* if(no >1 ){
				var rNo=(no-1)
				$("#fileCounts"+rNo).removeAttr('value');
			} */
			html =  html + '<div class="file-field input-field" id="trainingSessionFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="trainingSessionFiles'+no+'" name="trainingSessionFiles"  onchange="selectFile('+no+')">'
			

			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFileUpdate(no,bNo){			
	   	//$('#trainingSessionFilesDiv'+no).remove();
	   	$('#trainingSessionFilesNames'+no).remove();
	   	var id = Number(no)-1;
	   	$('#hide'+id+' input').attr('name', 'filecounts');
	   	var bId = $('#hide'+id+' input').val();
	   	if(bId == null){
			var html = '<input id="fileCounts'+bNo+'"  name="filecounts" value="0" type="hidden">'
	   		$('#hideVal'+bNo).append(html);
	   	}
	   	$('#hide'+no+' input').removeAttr('name');
	   	
	  } 
		function removeFile(no,bNo){			
		   	$('#trainingSessionFilesDiv'+no).remove();
		   	$('#trainingSessionFilesNames'+no).remove();
		   	var id = Number(no)-1;
		   	$('#hide'+id+' input').attr('name', 'filecounts');
		   	var bId = $('#hide'+id+' input').val();
		   	if(bId == null){
		   		$('#hideVal'+id+' input').attr('name', 'filecounts');
		   	}
		   	$('#hide'+no+' input').removeAttr('name');
		   	
		  } 
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal();
            $('#remarks').characterCounter();
            MaterialDateTimePicker.create($("#start_times0"));
            MaterialDateTimePicker.create($("#end_times0"));

            $('#start_time_icon0').click(function () {
                // event.stopPropagation();
                // $('#star_time').click();
                MaterialDateTimePicker.create($("#start_times0"));
            });
            $('#end_time_icon0').click(function () {
                // event.stopPropagation();
                // $('#end_time').click();
                MaterialDateTimePicker.create($("#end_times0"));
            });
            $('input[name=is_there_issue]').change(function () {
                var radioval = $('input[name=is_there_issue]:checked').val();
                if (radioval == 'yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#issue_yes').css("display", "none");
                }
            });
            callDateTimePicker(dateTimesInits);
        });
        //initializing datetime pickers 
        
        function callDateTimePicker(){
        	
        	for ( var i = 1; i <= dateTimesInits; i++ ) {
        		//cons(dateTimesInits)
        		 MaterialDateTimePicker.create($("#start_times"+i));
        		 MaterialDateTimePicker.create($("#end_times"+i));
        		 $('#start_time_icon'+i).click(function () {
                     MaterialDateTimePicker.create($("#start_times"+i));
                 });
                 $('#end_time_icon'+i).click(function () {
                     MaterialDateTimePicker.create($("#end_times"+i));
                 });
        	}
        }

      function checkBox(rowCount){
    	  if ($('#required_fks'+ rowCount).is(':checked')) {
        	  //$('#required_fk'+ rowCount).prop('disabled', true);
        	  $('#required_fk'+ rowCount).val('Yes');
          }else{
        	  $('#required_fk'+ rowCount).val('No');
        	  $('#required_fks'+ rowCount).prop('checked',false).removeAttr('checked');;
          }
      }
      
      function checkBoxs(rowCount){
          if ($('#participated_fks'+ rowCount).is(':checked')) {
        	  //$('#participated_fk'+ rowCount).prop('disabled', true);
        	  $('#participated_fk'+ rowCount).val('Yes');
          }else{
        	  $('#participated_fk'+ rowCount).val('No')
        	  $('#participated_fks'+ rowCount).prop('checked',false).removeAttr('checked');
          }
      }
      
        var rowNumber = null;
        function showNo(a){
        	rowNumber = a.href.split("#")[1].split("-")[2].split('modal')[1];        	
        //	console.log($('#trainingRow'+rowNumber));
        	$('#session-update-modal'+rowNumber).removeAttr('tabindex');
        }
      /*   function prevRow(tNo){
        	 var id = $('#attendeesTableBody'+tNo+' tr .hide:last').attr('id');
             var splt = id.split('s')[1];
             var c = $('#attendeesTableBody'+tNo+' tr').length;
             if(splt > 0){
             	var lastIndex = splt;
           	    var lastRow = $('#attendeesTableBody'+tNo+' #rowCounts'+lastIndex).removeAttr("disabled");
           	 $('#attendeesTableBody'+tNo+' #rowCounts'+lastIndex+':last').val(c)
             }else{
            	 $("#rowCounts0").removeAttr("disabled");
             }
        	
        } */
        function addTrainingUpdateRow(trainingSessionId,tNo) {
        	var index = rowNumber;
        	var tNo1 = tNo;
        	if(index == null){
        		index = 0;
        	}
        	if(tNo == 0){
        		tNo1 = tNo+"0"
        	}
        	var len = $('#newAttendeesTableBody'+tNo1+' tr').length + $('#attendeesTableBody'+tNo+'  tr').length;
        	var values = len + 1;
        	$("#rowsCounts"+tNo).val(values);
        	if(trainingSessionId === undefined){trainingSessionId = "";}
        	var trainNo = $("#trainNo").val();
            var rNo = Number(trainNo)+1;
        
            var html = '<tr id="attendeesRow'+rNo+'">' +
            	   '<td data-head="Department" class="input-field">'+
			 	   '<input type="hidden" name= "training_session_id_fks" id="training_session_id_fks'+rNo+tNo+'" value="'+trainingSessionId+'" />'+
				   '<input type="hidden" name="training_attendees_ids" id="training_attendees_ids'+ rNo +tNo+'" />'+
	               '<select id="department_fks'+ rNo +tNo+'" name="department_fks" class="searchable validate-dropdown " onchange="getAttendeesList('+ rNo +tNo+');">'+
	               '<option value="" >Select Department</option>'+
		                <c:forEach var="obj" items="${departmentsList}">
		             	  '<option value="${obj.department_fk }">${obj.department_name}</option>' +
		                </c:forEach>
	         	    '</select></td><input type="hidden"  name="is_new_users"/>'+
	                '<td data-head="Attendee" class="input-field attendee-dropdown"><select class="searchable validate-dropdown" name="attendees" id="attendees'+ rNo +tNo+'" onchange="setDepartment('+ rNo +tNo+');">'+
					'<option value="">Select Attendee</option>'+
						 <c:forEach var="obj" items="${attendeesList}">
							'<option name="${obj.department_fk }" value="${obj.attendee }">${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>'+
						</c:forEach> 
					'</select></td><input type="hidden" name="hod_user_id_fks"/><input type="hidden"  name="emails" class="no-reset" /><input type="hidden" name="trainee_designations"/><input type="hidden" name="mobile_nos"/>' +	                
	                '<td data-head="Nominated" class="input-field"><p class="disp-init"><label><input type="hidden" name="required_fks" id="required_fk'+ rNo +tNo+'" value="No" class="req"/><input type="checkbox" id="required_fks'+ rNo +tNo+'" class="required_fks"/><span></span></label></p></td>' +
	                '<td data-head="Participated" class="input-field"><p class="disp-init"><label><input type="hidden" name="participated_fks" id="participated_fk'+ rNo +tNo+'" value="No" class="part"/><input type="checkbox" id="participated_fks'+ rNo +tNo+'" class="participated_fks" /><span></span></label></p></td>' +
	                '<td class="mobile_btn_close"><a onclick="removeTrainingAttendees('+rNo+','+tNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
             $('#attendeesTableBody'+ index).append(html);
             $("#trainNo").val(rNo );
       	     $('#department_fks' + rNo+tNo ).select2();
       	     $('#attendees' + rNo+tNo ).select2();
       	  	 $('#hod_user_id_fks' + rNo+tNo ).select2();
       		 $('#required_fks'+ rNo+tNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                	// $(".req").prop('disabled', true);
                     $('#required_fk'+ rNo+tNo).val('Yes');
                 } else{
                    	  $("#required_fk"+ rNo+tNo).val('No')
                   	  $("#required_fk"+ rNo+tNo).prop('checked',false).removeAttr('checked');;
                     }
       	     });
           	 $("#participated_fks"+ rNo+tNo).on('change', function(e){
                if($(this).prop('checked'))
                {
                	//$(".part").prop('disabled', true);
                    $("#participated_fk"+ rNo+tNo).val('Yes');
                } else{
                  	  $("#participated_fk"+ rNo+tNo).val('No')
               	  $("#participated_fk"+ rNo+tNo).prop('checked',false).removeAttr('checked');;
                 }
            });
      
        }
        
        function addNewTrainingUpdateRow(trainingSessionId,tNo) {
        	var tNo1 = tNo;
        	if(tNo == "00"){
        		tNo = "0";
        	}
        	var len = $('#newAttendeesTableBody'+tNo1+' tr').length + $('#attendeesTableBody'+tNo+'  tr').length;
        	var values = len + 1;
        	$("#rowsCounts"+tNo).val(values);
        	if(trainingSessionId === undefined){trainingSessionId = "";}
        	
        	var trainNo = $("#newTrainNo").val();
            var rNo = Number(trainNo)+1;
      
            var html = '<tr id="newAttendeesRow'+rNo+'">' +
            '<td data-head="Department" class="input-field">'+
		 	   '<input type="hidden" name= "training_session_id_fks" id="new_training_session_id_fks'+rNo+tNo+'" value="'+trainingSessionId+'" />'+
			   '<input type="hidden" name="training_attendees_ids" id="new_training_attendees_ids'+ rNo +tNo+'" />'+
            '<select id="new_department_fks'+ rNo +tNo+'" name="department_fks" class="searchable validate-dropdown " onchange="getHODsList('+ rNo +tNo+');">'+
            '<option value="" >Select Department</option>'+
	                <c:forEach var="obj" items="${departmentsList}">
	             	  '<option value="${obj.department_fk }">${obj.department_name}</option>' +
	                </c:forEach>
      	    '</select></td><input type="hidden"  name="is_new_users" value="Yes"/>'+
             '<td data-head="HOD" class="input-field"> <select class="searchable" name="hod_user_id_fks" id="new_hod_user_id_fks'+ rNo +tNo+'" onchange="setHODDeptList('+ rNo +tNo+');">'+
             '<option value="" >Select HOD</option>'+
             	<c:forEach var="obj" items="${usersList}">
					'<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }">${obj.designation }</option>'+
				</c:forEach> 
             '</select></td>'+
             '<td data-head="Attendee" class="input-field"> <input id="new_attendees'+ rNo +tNo+'" name="attendees" type="text" class="validate" placeholder="Name"></td>' +
             '<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" id="new_trainee_designations'+ rNo +tNo+'" name="trainee_designations" ></td><td data-head="Email" class="input-field"> <input type="text" placeholder="Email" id="email'+ rNo +tNo+'" name="emails" ></td>'+
             '<td data-head="Mobile" class="input-field"><input id="new_mobile_nos'+ rNo +tNo+'" name="mobile_nos" type="number" class="validate" placeholder="Mobile"> </td>' +
             '<td data-head="Nominated" class="input-field"><p class="disp-init"><label><input type="hidden" name="required_fks" id="new_required_fk'+ rNo +tNo+'" value="No" class="req"/><input type="checkbox" id="new_required_fks'+ rNo +tNo+'" class="required_fks"/><span></span></label></p></td>' +
             '<td data-head="Participated" class="input-field"><p class="disp-init"><label><input type="hidden" name="participated_fks" id="new_participated_fk'+ rNo +tNo+'" value="No" class="part"/><input type="checkbox" id="new_participated_fks'+ rNo +tNo+'" class="participated_fks" /><span></span></label></p></td>' +
             '<td class="mobile_btn_close"><a onclick="removeNewTrainingAttendees('+rNo+','+tNo+'); " class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
             $('#newAttendeesTableBody'+ tNo1).append(html);
             $("#newTrainNo").val(rNo );
       	     $('#new_department_fks' + rNo+tNo ).select2();
       	  	 $('#new_hod_user_id_fks' + rNo+tNo ).select2();
       		 $('#new_required_fks'+ rNo+tNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                     $('#new_required_fk'+ rNo+tNo).val('Yes');
                 } else{
                    	  $("#new_required_fk"+ rNo+tNo).val('No')
                   	  $("#new_required_fk"+ rNo+tNo).prop('checked',false).removeAttr('checked');;
                     }
       	     });
           	 $("#new_participated_fks"+ rNo+tNo).on('change', function(e){
                if($(this).prop('checked'))
                {
                	//$(".part").prop('disabled', true);
                    $("#new_participated_fk"+ rNo+tNo).val('Yes');
                } else{
                  	  $("#new_participated_fk"+ rNo+tNo).val('No')
               	  $("#new_participated_fk"+ rNo+tNo).prop('checked',false).removeAttr('checked');;
                 }
            });
          
        }
        
        function addSessionRow(sessionId) {
       	
     	
      	  var rowNo = $("#rowNo").val();
          var rNo = Number(rowNo)+1;
          var trainNo = $("#trainNo").val();
          var tNo = Number(trainNo)+1;
          var len = $('#newAttendeesTableBody'+rNo+' tr').length + $('#attendeesTableBody'+rNo+'  tr').length;
      	  var values = len + 1;
      	  $("#rowsCounts"+rNo).val(values);
          var i = 13;
          
          
          if($("#training_id").val()>0)
        	  {
          
        	  
          
          var html = '<tr id="trainingRow'+rNo+'">' +
          '<td data-head="Session No" class="input-field"><input type="hidden" name= "training_session_ids" id="training_session_ids'+rNo+'"  />'+
          ' <input id="session_nos'+ rNo +'" name="session_nos" type="text" class="validate" placeholder="Session No"> </td>' +
          '<td data-head="Start Time"  class="input-field"><div class="pos-rel"><input id="start_times'+ rNo +'" name="start_times" type="text" class="validate timepicker"  placeholder="Start Time">' +
         	  '<button type="button" id="start_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></div> </td>' +
          '<td data-head="End Time" class="input-field"><div class="pos-rel"><input id="end_times'+ rNo +'" name="end_times" type="text" class="validate timepicker"placeholder="End Time">' +
          	  '<button type="button" id="end_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></div></td>' +
          '<td data-head="Attendees" class="input-field attendees-column"><a href="#session-update-modal'+ rNo +'" class="btn waves-effect waves-light bg-m t-c modal-trigger" onclick="showNo(this)"> Update </a> ' +
			  '<div id="session-update-modal'+ rNo +'" class="modal"><div class="modal-content">'+
				 '<h4 class="modal-header">Trainee Updation Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h4> <div class="row">'+
				 '<div class="s12 m8 offset-m2 col"><div class="row fixed-width" > <div class="table-inside"><table id="training-update-table'+ rNo +'" class="mdl-data-table val mobile_responsible_table">'+
		         '<thead><tr><th>Department</th><th>Attendee</th><th class="py-0">Nominated</th><th>Participated</th><th>Action</th></tr></thead> <tbody id="attendeesTableBody'+ rNo +'">'+
		         <c:forEach var="dObj" items="${trainingDetails.trainingSessions[0].trainingAttendees}" varStatus="indexx">
		         
		         
		         
		         '<tr id="attendeesRow0${indexx.count }${index.count }">'+
		         '<td data-head="Department" class="input-field">'+
		         '<input type="hidden" name="training_session_id_fks" id="training_session_id_fks'+ rNo+i+'" /> <input type="hidden" name="training_attendees_ids" id="training_attendees_ids'+ rNo+i+'" /> '+
		         '<select class="searchable no-reset validate-dropdown" name="department_fks" id="department_fks0${indexx.count }${index.count }${indexx.count }"> <option value="">Select Department</option>'+
		         <c:forEach var="obj" items="${departmentsList}">
						'<option value="${obj.department_fk }" <c:if test="${dObj.department_name eq obj.department_name }">selected</c:if>>${obj.department_name }</option>'+
					</c:forEach>
		    	 '</select></td><input type="hidden"  name="is_new_users"/>'+
		    	 '<td data-head="Attendee" class="input-field attendee-dropdown"><select class="searchable no-reset validate-dropdown" name="attendees" id="attendees0${indexx.count }${index.count }${indexx.count }" onchange="setDepartment('+ rNo+i+');"><option value="">Select Attendee</option>'+
		    	 <c:forEach var="obj" items="${dObj.attendeesList}">
					'<option name="${obj.department_fk }" value="${obj.attendee }" <c:if test="${dObj.attendee eq obj.attendee }">selected</c:if>>${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>'+
				</c:forEach>
		         '</select></td><input type="hidden" name="hod_user_id_fks" class="no-reset" value="${dObj.hod_user_id_fk}" /><input type="hidden" name="trainee_designations" class="no-reset" value="${dObj.designation}" /><input type="hidden" name="mobile_nos" class="no-reset" value="${dObj.mobile_no}"/><input type="hidden"  name="emails" class="no-reset" value="${dObj.email}" ><td data-head="Nominated" class="input-field"><p class="disp-init"> <label><input type="hidden" name="required_fks" id="required_fk'+ rNo+i+'" /> '+
		         '<input type="checkbox" id="required_fks'+ rNo+i+'"  class="required_fks" onChange="checkBox('+ rNo+i+')" <c:if test="${dObj.required_fk eq 'Yes'}">  checked</c:if> />'+
		         '<span></span></label></p></td><td data-head="Participated" class="input-field"><p class="disp-init"><label> <input type="hidden" name="participated_fks" id="participated_fk'+ rNo+i+'" />'+
		         '<input type="checkbox" id="participated_fks'+ rNo+i+'"  class="participated_fks" onChange="checkBoxs('+ rNo+i+')" <c:if test="${dObj.participated_fk eq 'Yes'}">    checked</c:if> /><span></span>'+
		         '</label></p></td><td class="mobile_btn_close"><a onclick="removeTrainingAttendees(\''+0+0+rNo+1+'\',\''+ rNo +'\');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'+
		         '</tr>'+
		         
		         
		         </c:forEach>
		         '</tbody></table> <input type="hidden" id="trainNo"  name="trainNo" value="0" /><table class="mdl-data-table"><tbody id="trainingUpdateBody">'+                                          
                 '<tr><td colspan="7" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addTrainingUpdateRow(\''+sessionId+'\',\''+ rNo +'\')"> <i class="fa fa-plus"></i></a> </tr>'+
                 '</tbody></table></div></div></div></div><p id="msg-text"><b>If name of attendee is not in list please add below</b></p>'+
		         '<div class="row fixed-width"><div class="table-inside"> <table id="training-new-update-table'+ rNo +'" class="mdl-data-table mobile_responsible_table">'+
					  '<thead><tr><th>Department</th><th>HOD</th><th>Attendee</th><th>Designation</th><th class="fw-220">Email</th><th class="mw-150"> Mobile</th><th class="py-0">Nominated</th><th>Participated</th><th>Action</th></tr></thead>'+
						'<tbody id="newAttendeesTableBody'+ rNo +'" ><input type="hidden" id="rowsCounts'+ rNo +'"  name="rowsCounts" value="2"/><tr id="newAttendeesRow'+0+0+rNo+1+'"><td data-head="Department" class="input-field">'+
						    '<input type="hidden" name= "training_session_id_fks" id="new_training_session_id_fks'+rNo+'"  value="'+sessionId+'" />'+
						    '<input type="hidden" name="training_attendees_ids" id="new_training_attendees_ids'+ rNo+i+'" />'+
						    '<select class="searchable validate-dropdown" name="department_fks" id="new_department_fks'+ rNo+i+'" onchange="getHODsList('+ rNo+i+');">'+
						      ' <option value="" >Select Department </option>'+
					             <c:forEach var="obj" items="${departmentsList}">
						            '<option value="${obj.department_fk }" >${obj.department_name }</option>'+
						          </c:forEach>
						     '</select></td>'+
							'<td data-head="HOD" class="input-field"> <select class="searchable" name="hod_user_id_fks" id="new_hod_user_id_fks'+ rNo +i+'" onchange="setHODDeptList('+ rNo +i+');">'+
							'<option value="" >Select HOD</option>'+
			                <c:forEach var="obj" items="${usersList}">
								'<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }">${obj.designation }</option>'+
							</c:forEach> 
			                '</select> </td><input type="hidden"  name="is_new_users" value="Yes"/>'+
			               // '</select></td>'+
							'<td data-head="Attendee" class="input-field"> <input id="new_attendees'+ rNo+i+'" name="attendees" type="text" class="validate" placeholder="Name"></td>' +
			                '<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" id="new_trainee_designations'+ rNo+i+'" name="trainee_designations" ></td>'+
			                '<td data-head="Email" class="input-field"> <input type="text" placeholder="Email" id="email'+ rNo+i+'" name="emails" ></td>'+
							'<td data-head="Mobile" class="input-field"><input id="new_mobile_nos'+ rNo+i+'" name="mobile_nos" type="number" class="validate" placeholder="Mobile" ></td>'+
			                '<td data-head="Nominated" class="input-field"><p class="disp-init"><label><input type="hidden" name="required_fks" id="new_required_fk'+ rNo+i+'" value="No" class="req"/><input type="checkbox" id="new_required_fks'+ rNo+i+'" class="required_fks"/><span></span></label></p></td>' +
			                '<td data-head="Participated" class="input-field"><p class="disp-init"><label><input type="hidden" name="participated_fks" id="new_participated_fk'+ rNo+i+'" value="No" class="part"/><input type="checkbox" id="new_participated_fks'+ rNo+i+'" class="participated_fks" /><span></span></label></p></td>' +
			                '<td class="mobile_btn_close"><a onclick="removeNewTrainingAttendees(\''+0+0+rNo+1+'\',\''+ rNo +'\');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr></tbody></table>'+
							'<input type="hidden" id="newTrainNo"  name="newTrainNo" value="0" /> ' +                    
	                  		    '<table class="mdl-data-table"><tbody id="newTrainingUpdateBody">'+                                          
	                            '<tr><td colspan="7" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addNewTrainingUpdateRow(\''+sessionId+'\',\''+ rNo +'\')"> <i class="fa fa-plus"></i></a> </tr>'+
	                          '</tbody></table></div></div></div> '+
	                          '<div class="row"> <div class="col s6 m4 offset-m2 center-align"> <a type="button" class="btn waves-effect waves-light modal-close bg-m t-c"> Update</a> </div>'+
	                          '<div class="col s6 m4 center-align"> <button type="button" class="btn waves-effect waves-light modal-close t-c bg-s" onclick="resetModal(this)">Cancel</a></div> </div></td>'+
	                         // '<a type="button" style="margin-bottom:20px;" class="btn waves-effect waves-light modal-close bg-m t-c"> Update</a></div> </td>'+
          '<td data-head="Remarks" class="input-field"> <textarea id="remarkss'+ rNo +'" name="remarkss" class="materialize-textarea" placeholder="Remarks"></textarea> </td>' +
          
          '<td data-head="Attachment" class="input-field cell-disp-inb">  <div id="selectedFilesInput'+rNo+'" ><div class="file-field input-field" id="trainingSessionFilesDivs'+rNo+1+'" >' 
          + '<div class="btn bg-m t-c"> <span>Attach Files</span>'
              +'<input type="hidden"  name="trainingSessionFileNames" value=""><input type="file" id="trainingSessionFiles'+rNo+1+'" name="trainingSessionFiles"   onchange="selectFiles('+rNo+1+','+rNo+')"></div>'
              +' <div class="file-path-wrapper">'
          +' <input class="file-path validate" type="text">'
          +' </div></div></div><div id="selectedFiles'+rNo+'" class="disp-in"><div class="hide" id="hideVal'+rNo+'"> <input id="fileCounts'+rNo+'"  name="filecounts"  type="hidden" value="0"></div></div></td>'+
         
          
          '<td class="mobile_btn_close right"> <a onclick="removeTraining('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
          
          
        	  }
          else
        	  {
        	  var html = '<tr id="trainingRow'+rNo+'">' +
              '<td data-head="Session No" class="input-field"><input type="hidden" name= "training_session_ids" id="training_session_ids'+rNo+'"  />'+
              ' <input id="session_nos'+ rNo +'" name="session_nos" type="text" class="validate" placeholder="Session No"> </td>' +
              '<td data-head="Start Time"  class="input-field"><div class="pos-rel"><input id="start_times'+ rNo +'" name="start_times" type="text" class="validate timepicker"  placeholder="Start Time">' +
             	  '<button type="button" id="start_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></div> </td>' +
              '<td data-head="End Time" class="input-field"><div class="pos-rel"><input id="end_times'+ rNo +'" name="end_times" type="text" class="validate timepicker"placeholder="End Time">' +
              	  '<button type="button" id="end_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></div></td>' +
              '<td data-head="Attendees" class="input-field attendees-column"><a href="#session-update-modal'+ rNo +'" class="btn waves-effect waves-light bg-m t-c modal-trigger" onclick="showNo(this)"> Update </a> ' +
    			  '<div id="session-update-modal'+ rNo +'" class="modal"><div class="modal-content">'+
    				 '<h4 class="modal-header">Trainee Updation Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h4> <div class="row">'+
    				 '<div class="s12 m8 offset-m2 col"><div class="row fixed-width" > <div class="table-inside"><table id="training-update-table'+ rNo +'" class="mdl-data-table val mobile_responsible_table">'+
    		         '<thead><tr><th>Department</th><th>Attendee</th><th class="py-0">Nominated</th><th>Participated</th><th>Action</th></tr></thead> <tbody id="attendeesTableBody'+ rNo +'"> <tr id="attendeesRow'+0+0+rNo+1+'">'+
    		         '<td data-head="Department" class="input-field">'+
    		         '<input type="hidden" name="training_session_id_fks" id="training_session_id_fks'+ rNo+i+'" /> <input type="hidden" name="training_attendees_ids" id="training_attendees_ids'+ rNo+i+'" /> '+
    		         '<select class="searchable validate-dropdown" name="department_fks" id="department_fks'+ rNo+i+'" onchange="getAttendeesList('+ rNo+i+');"> <option value="">Select Department</option>'+
    	             <c:forEach var="obj" items="${departmentsList}"> 
    	             	'<option value="${obj.department_fk }">${obj.department_name }</option>'+
    	        	 </c:forEach>
    		    	 '</select></td><input type="hidden"  name="is_new_users"/>'+
    		    	 '<td data-head="Attendee" class="input-field attendee-dropdown"><select class="searchable validate-dropdown" name="attendees" id="attendees'+ rNo+i+'" onchange="setDepartment('+ rNo+i+');"><option value="">Select Attendee</option>'+
    		         <c:forEach var="obj" items="${attendeesList}">
    		             '<option name="${obj.department_fk }" value="${obj.attendee }">${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>'+
    		         </c:forEach>
    		         '</select></td><input type="hidden" name="hod_user_id_fks"/><input type="hidden"  name="emails" class="no-reset" /><input type="hidden" name="trainee_designations"/><input type="hidden" name="mobile_nos"/><td data-head="Nominated" class="input-field"><p class="disp-init"> <label><input type="hidden" name="required_fks" id="required_fk'+ rNo+i+'" /> '+
    		         '<input type="checkbox" id="required_fks'+ rNo+i+'"  class="required_fks" onChange="checkBox('+ rNo+i+')" <c:if test="${dObj.required_fk eq 'Yes'}">  checked</c:if> />'+
    		         '<span></span></label></p></td><td data-head="Participated" class="input-field"><p class="disp-init"><label> <input type="hidden" name="participated_fks" id="participated_fk'+ rNo+i+'" />'+
    		         '<input type="checkbox" id="participated_fks'+ rNo+i+'"  class="participated_fks" onChange="checkBoxs('+ rNo+i+')" <c:if test="${dObj.participated_fk eq 'Yes'}">    checked</c:if> /><span></span>'+
    		         '</label></p></td><td class="mobile_btn_close"><a onclick="removeTrainingAttendees(\''+0+0+rNo+1+'\',\''+ rNo +'\');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'+
    		         '</tr></tbody></table> <input type="hidden" id="trainNo"  name="trainNo" value="0" /><table class="mdl-data-table"><tbody id="trainingUpdateBody">'+                                          
                     '<tr><td colspan="7" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addTrainingUpdateRow(\''+sessionId+'\',\''+ rNo +'\')"> <i class="fa fa-plus"></i></a> </tr>'+
                     '</tbody></table></div></div></div></div><p id="msg-text"><b>If name of attendee is not in list please add below</b></p>'+
    		         '<div class="row fixed-width"><div class="table-inside"> <table id="training-new-update-table'+ rNo +'" class="mdl-data-table mobile_responsible_table">'+
    					  '<thead><tr><th>Department</th><th>HOD</th><th>Attendee</th><th>Designation</th><th class="fw-220">Email</th><th class="mw-150"> Mobile</th><th class="py-0">Nominated</th><th>Participated</th><th>Action</th></tr></thead>'+
    						'<tbody id="newAttendeesTableBody'+ rNo +'" ><input type="hidden" id="rowsCounts'+ rNo +'"  name="rowsCounts" value="2"/><tr id="newAttendeesRow'+0+0+rNo+1+'"><td data-head="Department" class="input-field">'+
    						    '<input type="hidden" name= "training_session_id_fks" id="new_training_session_id_fks'+rNo+'"  value="'+sessionId+'" />'+
    						    '<input type="hidden" name="training_attendees_ids" id="new_training_attendees_ids'+ rNo+i+'" />'+
    						    '<select class="searchable validate-dropdown" name="department_fks" id="new_department_fks'+ rNo+i+'" onchange="getHODsList('+ rNo+i+');">'+
    						      ' <option value="" >Select Department </option>'+
    					             <c:forEach var="obj" items="${departmentsList}">
    						            '<option value="${obj.department_fk }" >${obj.department_name }</option>'+
    						          </c:forEach>
    						     '</select></td>'+
    							'<td data-head="HOD" class="input-field"> <select class="searchable" name="hod_user_id_fks" id="new_hod_user_id_fks'+ rNo +i+'" onchange="setHODDeptList('+ rNo +i+');">'+
    							'<option value="" >Select HOD</option>'+
    			                <c:forEach var="obj" items="${usersList}">
    								'<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }">${obj.designation }</option>'+
    							</c:forEach> 
    			                '</select> </td><input type="hidden"  name="is_new_users" value="Yes"/>'+
    			               // '</select></td>'+
    							'<td data-head="Attendee" class="input-field"> <input id="new_attendees'+ rNo+i+'" name="attendees" type="text" class="validate" placeholder="Name"></td>' +
    			                '<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" id="new_trainee_designations'+ rNo+i+'" name="trainee_designations" ></td>'+
    			                '<td data-head="Email" class="input-field"> <input type="text" placeholder="Email" id="email'+ rNo+i+'" name="emails" ></td>'+
    							'<td data-head="Mobile" class="input-field"><input id="new_mobile_nos'+ rNo+i+'" name="mobile_nos" type="number" class="validate" placeholder="Mobile" ></td>'+
    			                '<td data-head="Nominated" class="input-field"><p class="disp-init"><label><input type="hidden" name="required_fks" id="new_required_fk'+ rNo+i+'" value="No" class="req"/><input type="checkbox" id="new_required_fks'+ rNo+i+'" class="required_fks"/><span></span></label></p></td>' +
    			                '<td data-head="Participated" class="input-field"><p class="disp-init"><label><input type="hidden" name="participated_fks" id="new_participated_fk'+ rNo+i+'" value="No" class="part"/><input type="checkbox" id="new_participated_fks'+ rNo+i+'" class="participated_fks" /><span></span></label></p></td>' +
    			                '<td class="mobile_btn_close"><a onclick="removeNewTrainingAttendees(\''+0+0+rNo+1+'\',\''+ rNo +'\');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr></tbody></table>'+
    							'<input type="hidden" id="newTrainNo"  name="newTrainNo" value="0" /> ' +                    
    	                  		    '<table class="mdl-data-table"><tbody id="newTrainingUpdateBody">'+                                          
    	                            '<tr><td colspan="7" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addNewTrainingUpdateRow(\''+sessionId+'\',\''+ rNo +'\')"> <i class="fa fa-plus"></i></a> </tr>'+
    	                          '</tbody></table></div></div></div> '+
    	                          '<div class="row"> <div class="col s6 m4 offset-m2 center-align"> <a type="button" class="btn waves-effect waves-light modal-close bg-m t-c"> Update</a> </div>'+
    	                          '<div class="col s6 m4 center-align"> <button type="button" class="btn waves-effect waves-light modal-close t-c bg-s" onclick="resetModal(this)">Cancel</a></div> </div></td>'+
    	                         // '<a type="button" style="margin-bottom:20px;" class="btn waves-effect waves-light modal-close bg-m t-c"> Update</a></div> </td>'+
              '<td data-head="Remarks" class="input-field"> <textarea id="remarkss'+ rNo +'" name="remarkss" class="materialize-textarea" placeholder="Remarks"></textarea> </td>' +
              
              '<td data-head="Attachment" class="input-field cell-disp-inb">  <div id="selectedFilesInput'+rNo+'" ><div class="file-field input-field" id="trainingSessionFilesDivs'+rNo+1+'" >' 
              + '<div class="btn bg-m t-c"> <span>Attach Files</span>'
                  +'<input type="hidden"  name="trainingSessionFileNames" value=""><input type="file" id="trainingSessionFiles'+rNo+1+'" name="trainingSessionFiles"   onchange="selectFiles('+rNo+1+','+rNo+')"></div>'
                  +' <div class="file-path-wrapper">'
              +' <input class="file-path validate" type="text">'
              +' </div></div></div><div id="selectedFiles'+rNo+'" class="disp-in"><div class="hide" id="hideVal'+rNo+'"> <input id="fileCounts'+rNo+'"  name="filecounts"  type="hidden" value="0"></div></div></td>'+
             
              
              '<td class="mobile_btn_close right"> <a onclick="removeTraining('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';       	  
        	  }
          
          $('#trainingTableBody').append(html);
           
          $('#session-update-modal'+rNo).modal();
          $('#department_fks' + rNo+i).select2();
          $('#attendees' + rNo+i).select2();
          $('#hod_user_id_fks' + rNo+i).select2();
          $('.searchable').select2();
          $("#rowNo").val(rNo);
          MaterialDateTimePicker.create($("#start_times"+ rNo));
          MaterialDateTimePicker.create($("#end_times"+ rNo));

          $('#start_time_icon'+ rNo +'').click(function () {
             MaterialDateTimePicker.create($("#start_times" + rNo));
          });
          $('#end_time_icon'+ rNo +'').click(function () {
             MaterialDateTimePicker.create($("#end_times" + rNo));
          });
          $('#required_fks'+ rNo+i).on('change', function(e){
             if($(this).prop('checked'))
             {
            	// $(".req").prop('disabled', true);
                 $('#required_fk'+ rNo+i).val('Yes');
             } else{
                	  $("#required_fk"+ rNo+i).val('No')
               	  $("#required_fk"+ rNo+i).prop('checked',false).removeAttr('checked');;
                 }
   	     });
          $('#new_required_fks'+ rNo+i).on('change', function(e){
              if($(this).prop('checked'))
              {
             	// $(".req").prop('disabled', true);
                  $('#new_required_fk'+ rNo+i).val('Yes');
              } else{
                 	  $("#new_required_fk"+ rNo+i).val('No')
                	  $("#new_required_fk"+ rNo+i).prop('checked',false).removeAttr('checked');;  
                  }
    	     });
          $("#new_participated_fks"+ rNo+i).on('change', function(e){
              if($(this).prop('checked'))
              {
                  $("#new_participated_fk"+ rNo+i).val('Yes');
              } else{
                	  $("#new_participated_fk"+ rNo+i).val('No')
             	      $("#new_participated_fk"+ rNo+i).prop('checked',false).removeAttr('checked');;
               }
          });
       	 $("#participated_fks"+ rNo+i).on('change', function(e){
            if($(this).prop('checked'))
            {
                $("#participated_fk"+ rNo+i).val('Yes');
            } else{
              	  $("#participated_fk"+ rNo+i).val('No')
           	  	  $("#participated_fk"+ rNo+i).prop('checked',false).removeAttr('checked');;
             }
        });
    }
        function selectFiles(no,rNo){
		    files = $("#trainingSessionFiles"+no)[0].files;
		    var id = (no/10);
		    var str=id.toString();
		    var splt = str.split('.')[1];
		    var count = splt;
			var fNo = no - 1
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=trainingSessionFilesNames'+no+'>'
				 + '<a href="#" class="filevalue" >'+$(this).get(0).files[i].name+' <span onclick="removeFiles('+no+','+rNo+')" class="attachment-remove-btn">X</span></a>'		
				 + '<input type="hidden" id="trainingSessionFileNames'+no+'" name="trainingSessionFileNames" value="'+$(this).get(0).files[i].name+'" >'
				 + '</div>'
				 + '<div style="clear:both;" class="hide" id="hide'+no+'"><input id="fileCounts'+no+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles"+rNo).append(html);
		    
		    $('#trainingSessionFilesDivs'+no).hide();
		    
			var fileIndex = Number(no)+1;
			var lastfieldsid = $('#hide'+no+' input').last().val(count);
			$('#hideVal'+rNo+' input').removeAttr('name');
			//$('#trainingSessionFiles'+fNo).removeAttr('name');
			var s = $('#hide'+fNo+' input').val();
			if(s != null){
				$('#hide'+fNo+' input').removeAttr('name');
				 
			}
			moreFiles1(fileIndex,rNo);
		}
		
		function moreFiles1(no,rNo){
			var html = "";
			html =  html + '<div class="file-field input-field" id="trainingSessionFilesDivs'+no+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="trainingSessionFiles'+no+'" name="trainingSessionFiles"  onchange="selectFiles('+no+','+rNo+')"></div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput"+rNo).append(html);
		}
		
		function removeFiles(no,rNo){			
	   	$('#trainingSessionFilesDiv'+no).remove();
	   	$('#trainingSessionFilesNames'+no).remove();
		var id = Number(no)-1;
	    $('#hide'+id+' input').attr('name', 'filecounts');
		var bId = $('#hide'+id+' input').val();
	   	if(bId == null){
	   		var html = '<input id="fileCounts'+rNo+'"  name="filecounts" value="0" type="hidden">'
	   		$('#hideVal'+rNo).append(html);
	   	}
		
	   	$('#hide'+no+' input').removeAttr('name');
	   	
	   } 
     
     function removeTraining(rowNo){
     	//alert("#trainingRow"+rowNo);
     	$("#trainingRow"+rowNo).remove();
     }
     function getFileName(rowNo) {
         var filename = $('#trainingSessionFiles' + rowNo)[0].files[0].name;
         $('#fileVal' + rowNo).html(filename);
     }
     function removeTrainingAttendees(rowCount,rNo){
    	$("#attendeesRow"+rowCount).remove();
    		 var count = $("#rowsCounts"+rNo).val();
        	 var value = (count - 1);
        	 $("#rowsCounts"+rNo).val(value)
     }
     function removeNewTrainingAttendees(rowCount,rNo){
    	 $("#newAttendeesRow"+rowCount).remove();
    	 var count = $("#rowsCounts"+rNo).val();
    	 var value = (count - 1);
    	 $("#rowsCounts"+rNo).val(value)
    	
     }
     
     function updateTraining(){
    	 if(validator.form()){ // validation perform
			$(".page-loader").show();
			$('form input[name=session_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=start_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=end_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=attendees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=trainee_designations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=mobile_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=required_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=participated_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=hod_user_id_fk]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=is_new_user]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			document.getElementById("trainingForm").submit();	
    	}
     }
     
     function addTraining(){
    	if(validator.form()){ // validation perform
			$(".page-loader").show();
			$('form input[name=session_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=start_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=end_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=attendees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=trainee_designations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=mobile_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=required_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=participated_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=hod_user_id_fk]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=is_new_user]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			document.getElementById("trainingForm").submit();
    	}
 }
   
     var validator = $('#trainingForm').validate({
		 ignore: ":hidden:not(.validate-dropdown)",
  		    rules: {
  		 		  "training_type_fk": {
  			 		required: true
  			 	  },"training_category_fk": {
  			 		required: true
  			 	  },"status_fk": {
  		 		    required: true
  			 	  },"title": {
  		 		    required: true
  			 	  },"description": {
  		 		    required: true
  			 	  },"designation": {
  		 		    required: false
  			 	  },"faculty_name": {
  		 		    required: true
  			 	  },"mobile_nos": {
  			 		  required: false,
	  				  number: true,
	  				  minlength: 10,
	  				  maxlength: 10
  			 	  }
  			 	  
  		 	},
  		    messages: {
  		 		 "training_type_fk": {
  				 	required: 'This field is required',
  			 	  },"training_category_fk": {
  			 		required: ' This field is required'
  			 	  },"status_fk": {
  		 			required: ' This field is required'
  		 	  	 },"title": {
  		 			required: ' This field is required'
  		 	  	 },"description": {
  		 			required: ' This field is required'
  		 	  	 },"designation": {
  		 			required: ' This field is required'
  		 	  	 },"faculty_name": {
  		 			required: ' This field is required'
  		 	  	 },"mobile_nos": {
  		 			required: "Enter your mobile no",
  		 			minlength : "please enter valid number",
  		 			minlength : "please enter valid number"
  		 	  	 }
	   		},
	   		errorPlacement:function(error, element){
	   		 	if (element.attr("id") == "training_type_fk" ){
					 document.getElementById("training_typeError").innerHTML="";
			 		 error.appendTo('#training_typeError');
			    }else if(element.attr("id") == "training_category_fk" ){
					   document.getElementById("training_category_fkError").innerHTML="";
				 	   error.appendTo('#training_category_fkError');
			    }else if(element.attr("id") == "status_fk" ){
						document.getElementById("status_fkError").innerHTML="";
					 	error.appendTo('#status_fkError');
			    }else if(element.attr("id") == "title" ){
			 		 document.getElementById("titleError").innerHTML="";
	 				 error.appendTo('#titleError');
				}else if(element.attr("id") == "description" ){
			 		 document.getElementById("descriptionError").innerHTML="";
	 				 error.appendTo('#descriptionError');
				}else if(element.attr("id") == "designation" ){
			 		 document.getElementById("designationError").innerHTML="";
	 				 error.appendTo('#designationError');
				}else if(element.attr("id") == "faculty_name" ){
			 		 document.getElementById("faculty_nameError").innerHTML="";
	 				 error.appendTo('#faculty_nameError');
				}else if(element.attr("name") == "mobile_nos" ){
			 		 document.getElementById("mobile_nosError").innerHTML="";
	 				 error.appendTo('#mobile_nosError');
				}else{
	 					error.insertAfter(element);
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
		    	form.submit();
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

         function resetModal(a){
        	 var modalId=$(a).closest('.modal.open').attr('id');
        	 $('#'+modalId+' input[type="text"]:not(".no-reset")').val('');
        	 $('#'+modalId+' input[type="number"]:not(".no-reset")').val('');
        	 $('#'+modalId+' input[name="required_fks"]:not(".no-reset")').val('No');
        	 $('#'+modalId+' input[name="participated_fks"]:not(".no-reset")').val('No');
        	 $('#'+modalId+' input[type="checkbox"]:not(".no-reset")').prop('checked',false);
        	 $('#'+modalId+' select:not(".no-reset")').val('');
        	 $('.searchable').select2();
         }
                         
 </script>
</body>

