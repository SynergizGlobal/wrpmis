<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Training</c:if>
		 <c:if test="${action eq 'add'}"> Add Training</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
     
     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/training.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
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

        /* change radio colors  */
        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #282130 !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #282130 !important;
        }

        .timepicker~button {
            position: absolute;
            right: 15px;
            top: 25px;
            border: 0;
            opacity: 0.7;
            cursor: pointer;
            background-color: transparent;
        }

        .timepicker~button .fa {
            font-size: 1.3rem;
            color: #333;
        }

        /* modal header styling  */
        .modal-header {
            text-align: center;
            background-color: #282130;
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
        	min-width: 150px;
        	width:150px !important;
    		max-width: 160px;
        }
        .select2-container--default .select2-selection--single{
        	background-color:transparent;
        }
        tbody thead tr:hover{
        	    background-color: #282130 !important;
        }
        .modal{
        	max-height:90%;
        }
        .modal-content .row.fixed-width{
        	margin:0;
        }
        
        .select2-container.select2-container--default.select2-container--open{
        	z-index:1034;
        }
      /*   input[name^=start_times],input[name^=end_times]{
        	font-size:0.95rem !important;
        } */
        .fw-110{
            max-width: 110px;
		    width: 110px;
		    min-width: 110px;
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
							<div class="center-align p-2 bg-m">
								<h6>
									<c:if test="${action eq 'edit'}">Update Training</c:if>
									<c:if test="${action eq 'add'}"> Add Training</c:if>
								</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
					<div class="container container-no-margin">
						<c:if test="${action eq 'edit'}">
							<form action="<%=request.getContextPath() %>/update-training" id="trainingForm" name="trainingForm" method="post" class="form-horizontal" role="form">
						</c:if>
						<c:if test="${action eq 'add'}">
							<form action="<%=request.getContextPath() %>/add-training" id="trainingForm" name="trainingForm" method="post" class="form-horizontal" role="form">
						</c:if>
						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m4 input-field">
								<label class="primary-text-bold"></label>
								<c:if test="${action eq 'edit'}">
									<label class="primary-text-bold">Training ID : <span>${trainingDetails.training_id }</span></label>
								</c:if>
							</div>
							<div class="col s12 m4 input-field">
								<p class="searchable_label">Training Type</p>
								<select class="searchable validate-dropdown"
									name="training_type_fk" id="training_type_fk">
									<option value="">Select Financial Year</option>
									<c:forEach var="obj" items="${trainingTypesList}">
										<option value="${obj.training_type_fk }"
											<c:if test="${trainingDetails.training_type_fk eq obj.training_type_fk }">selected</c:if>>${obj.training_type_fk }</option>
									</c:forEach>
								</select> <span id="financial_year_fkError" class="error-msg"></span>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>

						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m4 input-field">
								<p class="searchable_label">Category</p>
								<select class="searchable validate-dropdown"
									name="training_category_fk" id="training_category_fk">
									<option value="">Select Financial Year</option>
									<c:forEach var="obj" items="${categoriesList}">
										<option value="${obj.training_category_fk }"
											<c:if test="${trainingDetails.training_category_fk eq obj.training_category_fk }">selected</c:if>>${obj.training_category_fk }</option>
									</c:forEach>
								</select> <span id="training_category_fkError" class="error-msg"></span>
							</div>
							<div class="col s12 m4 input-field">
								<p class="searchable_label">Status</p>
								<select class="searchable validate-dropdown" name="status_fk"
									id="status_fk">
									<option value="">Select Financial Year</option>
									<c:forEach var="obj" items="${statusList}">
										<option value="${obj.status_fk }"
											<c:if test="${trainingDetails.status_fk eq obj.status_fk }">selected</c:if>>${obj.status_fk }</option>
									</c:forEach>
								</select> <span id="status_fkError" class="error-msg"></span>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>

						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m4 input-field ">
								<input id="faculty_name" name="faculty_name" type="text"
									class="validate" value="${trainingDetails.faculty_name }">
								<label for="faculty_name">Faculty</label>
							</div>
							<div class="col s12 m4 input-field ">
								<input id="designation" type="text" class="validate"
									value="${trainingDetails.faculty_name }"> <label
									for="designation">Designation</label>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>
						<input type="hidden" name="training_id"
							value="${trainingDetails.training_id }" />
						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m8 input-field">
								<textarea id="training_title" name="title"
									class="materialize-textarea">${trainingDetails.title }</textarea>
								<label for="training_title">Title</label>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>
						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m8 input-field">
								<textarea id="training_description" name="description"
									class="materialize-textarea">${trainingDetails.description }</textarea>
								<label for="training_description">Description</label>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>

						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m8 input-field">
								<!-- <input id="training_center" type="text" class="validate" style="margin-top: 10px;">
                                    <label for="training_center"> Training Center </label> -->
								<textarea id="training_center" name="training_center"
									class="materialize-textarea">${trainingDetails.training_center }</textarea>
								<label for="training_center">Training Center</label>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>

						<div class="row fixed-width">
							<h5 class="center-align">Sessions</h5>
							<div class="table-inside">
								<table id="session-table" class="mdl-data-table">
									<thead>
										<tr>
											<th class="fw-110">Session No</th>
											<th>Start Time</th>
											<th>End Time</th>
											<th>Attendees</th>
											<th>Remaks</th>
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
														<td><input type="hidden" name="training_session_ids" id="training_session_ids${index.count }" value="${tObj.training_session_id}" />
														 <input id="session_nos${index.count }" name="session_nos" type="text" class="validate" value="${tObj.session_no }"
															placeholder="Session No"></td>
														<td><input type="hidden" name="start_times" value="${tObj.start_time }"/>
															<input id="start_times${index.count }" type="text" class="validate timepicker" value="${tObj.start_time }"
															placeholder="Start Time">
															<button type="button" id="start_time_icon${index.count }"><i class="fa fa-clock-o"></i></button></td>
														<td><input id="end_times${index.count }" name="end_times" type="text" class="validate timepicker" value="${tObj.end_time }" placeholder="End Time">
															<button type="button" id="end_time_icon${index.count }"><i class="fa fa-clock-o"></i></button></td>
														<td><a href="#session-update-modal${index.count }" class="btn waves-effect waves-light bg-m t-c modal-trigger"
															onclick="showNo(this);"> Update </a>
															<div id="session-update-modal${index.count }" class="modal">
																<div class="modal-content">
																	<h4 class="modal-header">Trainee Updation Details <span class="right modal-action modal-close">  <i class="fa fa-close"></i>  	</span></h4>

																	<div class="row fixed-width">
																		<div class="table-inside">
																			<table id="training-update-table${index.count }"
																				class="mdl-data-table val">
																				<thead>
																					<tr>
																						<th>Department</th>
																						<th>Attendee</th>
																						<th>Mobile</th>
																						<th>Required</th>
																						<th>Participated</th>
																						<th>Action</th>
																					</tr>
																				</thead>
																				<tbody id="attendeesTableBody${index.count }">
																					<c:choose>
																						<c:when
																							test="${not empty tObj.trainingAttendees && fn:length(tObj.trainingAttendees) gt 0 }">
																							<c:forEach var="dObj" items="${tObj.trainingAttendees }" varStatus="indexx">
																								<tr id="attendeesRow${indexx.count }">
																									<td><input type="hidden" name="training_session_id_fks" id="training_session_id_fks${indexx.count }${index.count }"
																										 value="${dObj.training_session_id_fk}" />
																										<input type="hidden" name="training_attendees_ids" id="training_attendees_ids${indexx.count }${index.count }"
																										value="${dObj.training_attendees_id }" /> 
																										<select class="searchable validate-dropdown" name="department_fks" id="department_fks${indexx.count }${index.count }">
																											<option value="">Select Department</option>
																											<c:forEach var="obj"
																												items="${departmentsList}">
																												<option value="${obj.department_fk }"
																													<c:if test="${dObj.department_name eq obj.department_name }">selected</c:if>>${obj.department_name }</option>
																											</c:forEach>
																									</select> <span id="training_category_fkError" class="error-msg"></span></td>
																									<td><input
																										id="attendees${indexx.count }${index.count }" name="attendees" type="text" class="validate" placeholder="Attendee"
																										value="${dObj.attendee }"></td>
																									<td><input id="mobile_nos${indexx.count }${index.count }" name="mobile_nos" type="number" class="validate" placeholder="Mobile"
																										value="${dObj.mobile_no }"></td>
																									<td>
																										<p>
																											<label><input type="hidden" name="required_fks" value ="${dObj.required_fk}" id="required_fk${indexx.count }${index.count }" />
																											  
																											   <input type="checkbox" id="required_fks${indexx.count }${index.count }"  class="required_fks" onChange="checkBox('${indexx.count }${index.count }')"
																												 <c:if test="${dObj.required_fk eq 'Yes'}">  checked</c:if> />
																												<span></span>
																											</label>
																										</p>
																									</td>
																									<td>
																										<p>
																											<label> <input type="hidden" name="participated_fks" value ="${dObj.participated_fk}" id="participated_fk${indexx.count }${index.count }" /> 
																											  
																											   <input type="checkbox" id="participated_fks${indexx.count }${index.count }"  class="participated_fks" onChange="checkBoxs('${indexx.count }${index.count }')"
																												 <c:if test="${dObj.participated_fk eq 'Yes'}">    checked</c:if> />
																												<span></span>
																											</label>
																										</p>
																									</td>
																									<td><a onclick="removeTrainingAttendees('${indexx.count }');"
																										class="btn waves-effect waves-light red t-c ">
																											<i class="fa fa-close"></i>
																									</a></td>
																								</tr>
																							</c:forEach>
																						 </c:when>
																						<c:otherwise>
																							<tr id="attendeesRow0">
																								<td><input type="hidden" name="training_session_id_fks" id="training_session_id_fks0"
																									 value="${tObj.training_session_id}" />
																									 <input type="hidden" name="training_attendees_ids" id="training_attendees_ids0" />
																									  <select class="searchable validate-dropdown" name="department_fks" id="department_fks0">
																											<option value="">Select Department</option>
																											<c:forEach var="obj" items="${departmentsList}">
																												<option value="${obj.department_fk }">${obj.department_name }</option>
																											</c:forEach>
																									  </select> <span id="training_category_fkError" class="error-msg"></span></td>
																								<td><input id="attendees0" name="attendees" type="text" class="validate" placeholder="Attendee"></td>
																								<td><input id="mobile_nos0" name="mobile_nos" type="number" class="validate" placeholder="Mobile">
																								</td>
																								<td>
																									<p>
																										<label> <input type="hidden" id="required_fk0" name="required_fks" value="No" class="req" />
																											<input type="checkbox" id="required_fks0" /> <span></span>
																										</label>
																									</p>
																								</td>
																								<td>

																									<p>
																										<label> <input type="hidden" id="participated_fk0" name="participated_fks" value="No" class="part" /> 
																											<input type="checkbox" id="participated_fks0"  /> <span></span>
																										</label>
																									</p>
																								</td>
																								<td><a onclick="removeTrainingAttendees('0');"	class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>
																							</tr>
																							<script>
																			                       	 $('#required_fks0').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             {
																			                            	 //$(".req").prop('disabled', true);
																			                                 $('#required_fk0').val('Yes');
																			                             }else{
																			                              	  $("#required_fk0").val('No')
																			                            	  $("#required_fk0").prop('checked',false).removeAttr('checked');;
																			                              }
																			                   	    });
																			                    	 $('#participated_fks0').on('change', function(e){
																			                             if($(this).prop('checked'))
																			                             {
																			                            	// $(".part").prop('disabled', true);
																			                                 $('#participated_fk0').val('Yes');
																			                             } else{
																			                              	  $("#participated_fk0").val('No')
																			                            	  $("#participated_fk0").prop('checked',false).removeAttr('checked');;
																			                              }
																			                   	    });
												                            
												                            				</script>
																						</c:otherwise>
																					</c:choose>
																				</tbody>
																			</table>

																			<table class="mdl-data-table">
																				<tbody id="trainingBody">
																					<tr>
																						<td colspan="6" style="text-align: right;"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																							onclick="addTrainingUpdateRow('${tObj.training_session_id}')"> <i class="fa fa-plus"></i></a>
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
															</div></td>
														<td><textarea id="remarkss${index.count }" name="remarkss" class="materialize-textarea" placeholder="Remarks">${tObj.remarks }</textarea></td>
														<td><a onclick="removeTraining('${index.count }');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
													</tr>
													<script>
                                          				dateTimesInits++;                                         
                                          			 </script>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr id="trainingRow0">
													<td><input type="hidden" name= "training_session_ids" id="training_session_ids0"  value="${tObj.training_session_id}"/>
														<input id="session_nos0" name="session_nos" type="text" class="validate" placeholder="Session No"></td>
													<td><input id="start_times0" name="start_times" type="text" class="validate timepicker" placeholder="Start Time">
														<button type="button" id="start_time_icon0"><i class="fa fa-clock-o"></i></button></td>
													<td><input id="end_times0" name="end_times" type="text" class="validate timepicker" placeholder="End Time">
														<button type="button" id="end_time_icon0"><i class="fa fa-clock-o"></i></button></td>
													<td><a href="#session-update-modal0" class="btn waves-effect waves-light bg-m t-c modal-trigger" onclick="showNo(this)"> Update </a>
														<div id="session-update-modal0" class="modal">
															<div class="modal-content">
																<h4 class="modal-header">Trainee Updation Details</h4>
															   	<div class="row fixed-width">
																	<div class="table-inside">
																		<table id="training-update-table0"
																			class="mdl-data-table">
																			<thead>
																				<tr>
																					<th>Department</th>
																					<th>Attendee</th>
																					<th>Mobile</th>
																					<th>Required</th>
																					<th>Participated</th>
																					<th>Action</th>
																				</tr>
																			</thead>
																			<tbody id="attendeesTableBody0">
																				<tr id="attendeesRow00">
																					<td><input type="hidden" name="training_attendees_ids" id="training_attendees_ids00" value="${tObj.training_session_id_fk}"/> 
																					<select class="searchable validate-dropdown" name="department_fks" id="department_fks00">
																							<option value="">Select Department</option>
																							<c:forEach var="obj" items="${departmentsList}">
																								<option value="${obj.department_fk }">${obj.department_name }</option>
																							</c:forEach>
																					</select> 
																					<span id="training_category_fkError" class="error-msg"></span></td>
																					<td><input id="attendees00" name="attendees" type="text" class="validate" placeholder="Attendee"></td>
																					<td><input id="mobile_nos00" name="mobile_nos" type="number" class="validate" placeholder="Mobile"></td>
																					<td>
																						<p>
																							<label> 
																								<input type="hidden" id="required_fk00" name="required_fks" value="No" class="req" /> 
																								<input type="checkbox" id="required_fks00" /> <span></span>
																							</label>
																						</p>
																					</td>
																					<td>
																						<p>
																							<label> <input type="hidden" id="participated_fk00" name="participated_fks" value="No" class="part" />
																								<input type="checkbox" id="participated_fks00"  /> <span></span>
																							</label>
																						</p>
																					</td>
																					<td><a onclick="removeTrainingAttendees('00');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i>
																					</a></td>
																				</tr>

																			</tbody>
																		</table>
																		<script>
															                       	 $('#required_fks00').on('change', function(e){
															                             if($(this).prop('checked'))
															                             {
															                            	 //$(".req").prop('disabled', true);
															                                 $('#required_fk00').val('Yes');
															                             } else{
															                              	  $("#required_fk00").val('No')
															                            	  $("#required_fk00").prop('checked',false).removeAttr('checked');;
															                              }
															                   	    });
															                    	 $('#participated_fks00').on('change', function(e){
															                             if($(this).prop('checked'))
															                             {
															                            	// $(".part").prop('disabled', true);
															                                 $('#participated_fk00').val('Yes');
															                             } else{
															                              	  $("#participated_fk00").val('No')
															                            	  $("#participated_fk00").prop('checked',false).removeAttr('checked');;
															                              }
															                   	    });
												                            
												                            </script>
																		<input type="hidden" id="trainNo" name="trainNo"
																			value="0" />
																		<table class="mdl-data-table">
																			<tbody id="trainingBody">
																				<tr>
																					<td colspan="6" style="text-align: right;"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																						onclick="addTrainingUpdateRow('${tObj.training_session_id}')"> <i class="fa fa-plus"></i></a>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>
														</div></td>
													<td><textarea id="remarkss0" name="remarkss" class="materialize-textarea" placeholder="Remarks"></textarea>
													</td>
													<td><a onclick="removeTraining('0');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<table class="mdl-data-table">
									<tbody id="trainingBody">
										<tr>
											<td colspan="6" style="text-align: right;"><a type="button" class="btn waves-effect waves-light bg-m t-c "
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

						<div class="row" style="margin-top: 20px;">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col m8 s12">
								<div class="row">
									<div class="col s6 m6 input-field">
										<p style="margin-top: 18px;">Any Issue ?</p>
									</div>
									<div class="col s6 m6 input-field">
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
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="row">
										<div class="col s12 m6 input-field" style="margin-top: 35px;">
											<p class="searchable_label">Issue Category</p>
											<select class="searchable validate-dropdown"
												id="issue_category_id" name="issue_category_id">
												<option value="">Select</option>
												<c:forEach var="obj" items="${issueCatogoriesList}">
													<option value="${obj.category }">${obj.category }</option>
												</c:forEach>
											</select> <span id="status_fkError" class="error-msg"></span>
										</div>
										<div class="col s12 m6 input-field" style="padding-top: 4px;">
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
								<div class="col m2 hide-on-small-only"></div>
								<div class="col s12 m8 input-field"><textarea id="issue_description" name="issue_description" class="materialize-textarea" data-length="500"></textarea>
									<label for="issueDesc">Issue Description</label>
								</div>
							</div>
						</div>

						<div class="row">
							<!-- row 10 -->
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m8 input-field">
								<textarea id="textarea1" class="materialize-textarea" name="remarks" id="remarks" data-length="1000"></textarea>
								<label for="textarea1">Remarks</label>
							</div>
						</div>

						<div class="row">
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m4">
								<div class="center-align m-1">
									<c:if test="${action eq 'edit'}">
										<button type="button" onclick="updateTraining();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
									</c:if>
									<c:if test="${action eq 'add'}"> 
										<button type="button" onclick="addTraining();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
									</c:if>
								</div>
							</div>
							<div class="col s12 m4">
								<div class="center-align m-1">
									<button href="<%=request.getContextPath()%>/training"
										class="btn waves-effect waves-light bg-s" style="width: 100%">Cancel</button>
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
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/datetimepicker.js"></script>
	
	<script>
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
        	  $('#participated_fks'+ rowCount).prop('checked',false).removeAttr('checked');;
          }
      }
        var f = null;
        function showNo(a){
        	 f=a.href.split("#")[1].split("-")[2].split('modal')[1];        	
        	console.log($('#trainingRow'+f));
        }
        
        function addTrainingUpdateRow(trainingSessionId) {
        	var index = f;
        	var count = 0;
        	if(index == null){
        		index = 0;
        	}
        	
        	if(trainingSessionId === undefined){
        		trainingSessionId = "";
        	}
        	 var trainNo = $("#trainNo").val();
             var rNo = Number(trainNo)+1;
            var html = '<tr id="attendeesRow'+rNo+'"><input type="hidden" id="rowCounts'+rNo+'" name="rowCounts" value="'+rNo+'"  />' +
            	'<td> '+
			 	 '<input type="hidden" name= "training_session_id_fks" id="training_session_id_fks'+rNo+'" value="'+trainingSessionId+'" />'+
				 '<input type="hidden" name="training_attendees_ids" id="training_attendees_ids'+ rNo +'" />'+
	                '<select id="department_fks'+ rNo +'" name="department_fks" class="searchable validate-dropdown " >'+
	                '<option value="" >Select Department</option>'+
		                <c:forEach var="obj" items="${departmentsList}">
		             	  '<option value="${obj.department_fk }">${obj.department_name}</option>' +
		                </c:forEach>
	         	    '</select></td>'+
	                '<td><input id="attendees'+ rNo +'" name="attendees" type="text" class="validate" placeholder="Attendee"></td>' +
	                '<td><input id="mobile_nos'+ rNo +'" name="mobile_nos" type="number" class="validate" placeholder="Mobile"> </td>' +
	                '<td><p><label><input type="hidden" id="required_fk'+ rNo +'" name="required_fks"  value="No" class="req"/><input type="checkbox" id="required_fks'+ rNo +'" class="required_fks"/><span></span></label></p></td>' +
	                '<td><p><label><input type="hidden" id="participated_fk'+ rNo +'" name="participated_fks"  value="No" class="part"/><input type="checkbox" id="participated_fks'+ rNo +'" class="participated_fks" /><span></span></label></p></td>' +
	                '<td><a onclick="removeTrainingAttendees('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
             $('#attendeesTableBody'+ index).append(html);
             $("#trainNo").val(rNo );
       	     $('#department_fks' + rNo ).select2();
       		  var count = $('table tr').length;
       			
       		  
           	 $('#required_fks'+ rNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                	 //$(".req").prop('disabled', true);
                     $('#required_fk'+ rNo).val('Yes');
                 }else{
                 	  $("#required_fk"+ rNo).val('No')
                	  $("#required_fk"+ rNo).prop('checked',false).removeAttr('checked');;
                  }
       	    });
           	$("#participated_fks"+ rNo).on('change', function(e){
                if($(this).prop('checked'))
                {
                	 //$(".part").prop('disabled', true);
                    $("#participated_fk"+ rNo).val('Yes');
                }else{
              	  $("#participated_fk"+ rNo).val('No')
            	  $("#participated_fks"+ rNo).prop('checked',false).removeAttr('checked');;
              }
           });
      
        }
        
        function addSessionRow(sessionId) {
        
      	  var rowNo = $("#rowNo").val();
          var rNo = Number(rowNo)+1;
          var trainNo = $("#trainNo").val();
          var tNo = Number(trainNo)+1;
          var html = '<tr id="trainingRow'+rNo+'">' +
          '<td><input type="hidden" name= "training_session_ids" id="training_session_ids'+rNo+'"  />'+
          ' <input id="session_nos'+ rNo +'" name="session_nos" type="text" class="validate" placeholder="Session No"> </td>' +
          '<td> <input id="start_times'+ rNo +'" name="start_times" type="text" class="validate timepicker"  placeholder="Start Time">' +
         		 '<button type="button" id="start_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button> </td>' +
          '<td> <input id="end_times'+ rNo +'" name="end_times" type="text" class="validate timepicker"placeholder="End Time">' +
          	 '<button type="button" id="end_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></td>' +
          '<td> <a href="#session-update-modal'+ rNo +'" class="btn waves-effect waves-light bg-m t-c modal-trigger" onclick="showNo(this)"> Update </a> ' +
					'<div id="session-update-modal'+ rNo +'" class="modal"><div class="modal-content">'+
					'<h4 class="modal-header">Trainee Updation Details <span class="right modal-action modal-close">  <i class="fa fa-close"></i>  	</span></h4> <div class="row fixed-width"><div class="table-inside">'+
						'<table id="training-update-table'+ rNo +'" class="mdl-data-table">'+
							' <thead><tr><th>Department</th><th>Attendee</th><th>Mobile</th><th>Required</th><th>Participated</th><th>Action</th></tr></thead>'+
							  ' <tbody id="attendeesTableBody'+ rNo +'" ><tr id="attendeesRow'+rNo+'"><td>'+
							 	  '<input type="hidden" name= "training_session_id_fks" id="training_session_id_fks'+rNo+'"  value="'+sessionId+'" />'+
								  '<input type="hidden" name="training_attendees_ids" id="training_attendees_ids'+ rNo +tNo+'" />'+
									'<select class="searchable validate-dropdown" name="department_fks" id="department_fks'+ rNo +tNo+'">'+
								      ' <option value="" >Select Department </option>'+
							             <c:forEach var="obj" items="${departmentsList}">
								            '<option value="${obj.department_fk }" >${obj.department_name }</option>'+
								          </c:forEach>
									 '</select></td>'+
									'<td><input id="attendees'+ rNo +tNo+'" name="attendees" type="text" class="validate" placeholder="Attendee" ></td>'+
									'<td><input id="mobile_nos'+ rNo +tNo+'" name="mobile_nos" type="number" class="validate" placeholder="Mobile" ></td>'+
					                '<td><p><label><input type="hidden" name="required_fks" id="required_fk'+ rNo +tNo+'" value="No" class="req"/><input type="checkbox" id="required_fks'+ rNo +tNo+'" class="required_fks"/><span></span></label></p></td>' +
					                '<td><p><label><input type="hidden" name="participated_fks" id="participated_fk'+ rNo +tNo+'" value="No" class="part"/><input type="checkbox" id="participated_fks'+ rNo +tNo+'" class="participated_fks" /><span></span></label></p></td>' +
					                '<td><a onclick="removeTrainingAttendees('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr></tbody></table>'+
									'<input type="hidden" id="trainNo"  name="trainNo" value="1" /> ' +                    
	                        		'<table class="mdl-data-table"><tbody id="trainingBody">'+                                          
	                                  '<tr><td colspan="6" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addTrainingUpdateRow('+sessionId+')"> <i class="fa fa-plus"></i></a> </tr>'+
	                                '</tbody></table></div></div></div></div> </td>'+
          '<td> <textarea id="remarkss'+ rNo +'" name="remarkss" class="materialize-textarea" placeholder="Remarks"></textarea> </td>' +
          '<td> <a onclick="removeTraining('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
          $('#trainingTableBody').append(html);
           
          $('#session-update-modal'+rNo).modal();
          $('#department_fks' + rNo+tNo).select2();
           
         $("#rowNo").val(rNo);
         MaterialDateTimePicker.create($("#start_times"+ rNo));
         MaterialDateTimePicker.create($("#end_times"+ rNo));

         $('#start_time_icon'+ rNo +'').click(function () {
             MaterialDateTimePicker.create($("#start_times" + rNo));
         });
         $('#end_time_icon'+ rNo +'').click(function () {
             MaterialDateTimePicker.create($("#end_times" + rNo));
         });
         
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
               	  $("#required_fk"+ rNo+tNo).val('No')
            	  $("#required_fk"+ rNo+tNo).prop('checked',false).removeAttr('checked');;
              }
	       });
    }
     
     function removeTraining(rowNo){
     	//alert("#trainingRow"+rowNo);
     	$("#trainingRow"+rowNo).remove();
     }
     function removeTrainingAttendees(rowNo){
    	 $("#attendeesRow"+rowNo).remove();
     }
     function updateTraining(){
			$(".page-loader").show();
			$('form input[name=session_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=start_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=end_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=attendees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=mobile_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=required_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=participated_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			document.getElementById("trainingForm").submit();		    		
     }
     function addTraining(){
			$(".page-loader").show();
			$('form input[name=session_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=start_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=end_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=attendees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=mobile_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=required_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=participated_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			document.getElementById("trainingForm").submit();		    		
 	
 }
   
 </script>
</body>

