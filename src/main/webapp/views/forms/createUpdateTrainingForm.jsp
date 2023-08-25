<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
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
    
    
     <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/project.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    
    
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
   
    <style>
        #session-table .datepicker~button {
            top: 26px;
        }
         #session-table {
            padding-left: 0px;;
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
			    padding:0 !important;  
			}
			.modal:not(.datepicker-modal){
	        	max-height:80%;
	        	width:85%;
	        }
	        .modal.datepicker-modal {
	        	width:76%;
	        	max-width:78%;
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
			   
			   
			    margin-bottom: 50px;
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
		@media only screen and (max-width:450px){
			.timepicker-digital-display{
				min-height:150px;
			}
			.date-holder{
			    font-size: 2rem;
			    margin-bottom:3rem;
			}
			.date-holder >.date-text{
			    font-size: 2.5rem;     
			}
		}
		
		div[class^=autocomplete]{
			position: absolute;
		    z-index: 2;
		    background-color: #fff;
		    width: min(200%,180px);
		    border:1px solid #eee;
		    padding:.1rem;
		    overflow:auto;
		    max-height: 200px;
		    box-shadow:-2px -2px 5px #efefef;
		}
		.autoList{
			padding:.1rem .3rem;
			cursor:pointer;
			border-bottom:1px solid #ececec;
		}
		.autoList:hover{
			background-color:rgba(0,0,0,.05);
		}
		
		
/* Base styles for the row */

  /* Media query for smaller screens */
 @media screen and (max-width: 768px) {
  #trainingRow0 td {
    display: block;
    width: 100%;
    box-sizing: border-box;
    padding: 5px 30px 5px 150px;
    text-align : center;
  }
  #trainingRow0 td[data-head]::before {
    content: attr(data-head);
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
  }
   .file-path.validate {
    width: 100%;
  }
  
  /* Add space between input elements */
  #trainingRow0 input[type="text"].validate {
    margin-bottom: 50px; /* Adjust the margin value as needed */
  }
}


@media screen and (max-width: 768px) {
  #trainingRow td {
    display: block;
    width: 100%;
    box-sizing: border-box;
    padding: 5px 30px 5px 150px;
    text-align : center;
  }
  #trainingRow td[data-head]::before {
    content: attr(data-head);
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
  }
  
  .RemarksCell {
  padding-top: 100px;
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
									<c:if test="${action eq 'edit'}">Update Training (${trainingDetails.training_id })</c:if>
									<c:if test="${action eq 'add'}"> Add Training</c:if>
								</h6>
							</div>
						</span>
					</div>
					<!-- form start-->
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
								<%-- <div class="col s6 offset-m2 m4 input-field">								
									<p class="primary-text-bold">Training ID : <span>${trainingDetails.training_id }</span></p>								
								</div> --%>
							</c:if>
							<div class="col s6 m4 l4 input-field <c:if test="${action eq 'add'}"> </c:if>">
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
							<div class="col s6 m4 l4 input-field">
								<p class="searchable_label">Category <span class="required">*</span></p>
								<select class="searchable validate-dropdown"
									name="training_category_fk" id="training_category_fk">
									<option value="">Select Category </option>
									<c:forEach var="obj" items="${categoriesList}">
										<option value="${obj.training_category_fk}"
											<c:if test="${trainingDetails.training_category_fk eq obj.training_category_fk }">selected</c:if>>${obj.training_category_fk }</option>
									</c:forEach>
								</select> 
								<span id="training_category_fkError" class="error-msg"></span>
							</div>
							
							<div class="col s6 m4 l4 input-field">
 							 <p class="searchable_label">Periodicity <span class="required">*</span></p>
												  <select class="searchable validate-dropdown" 
												  name="period_fk" id="period_fk">
												    <option value="">Select Periodicity</option>
												    <c:forEach var="obj" items="${periodicityList}">
														<option value="${obj.period_fk}"
															<c:if test="${trainingDetails.period_fk eq obj.period_fk }">selected</c:if>>${obj.period_fk }</option>
													</c:forEach>
												  </select> 
												  <span id="period_fkError" class="error-msg"></span>
												</div>				


					<!-- card  	<div class="col s12 m4 l4 input-field">
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
							</div> -->
							 
						</div>


						<div class="row">
							 
							
							 
						</div>
						<input type="hidden" name="training_id" value="${trainingDetails.training_id }"  id="training_id"/>
						<div class="row">
							 
							<div class="col s12 m12 l12 input-field">
								<textarea id="title" name="title" class="pmis-textarea">${trainingDetails.title }</textarea>
								<label for="title">Title <span class="required">*</span></label>
								<span id="titleError" class="error-msg"></span>
							</div>
							 
						</div>
						<div class="row">
							 
							<div class="col s12 m8 l8 input-field">
								<textarea id="description" name="description" class="pmis-textarea">${trainingDetails.description }</textarea>
								<label for="description">Description <span class="required">*</span></label>
								<span id="descriptionError" class="error-msg"></span>
								</div>
								
								
								<div class="col s12 m4 l4 input-field">
 							 <p class="searchable_label">Conducted By : <span class="required">*</span></p>
												  <select class="searchable validate-dropdown" 
												  name="conduct_by_fk" id="conduct_by_fk">
												    <option value="">Conducted By</option>
												    <c:forEach var="obj" items="${training_ConductedList}">
													<option value="${obj.conduct_by_fk}"
													<c:if test="${trainingDetails.conduct_by_fk  eq obj.conduct_by_fk}">selected</c:if>>${obj.conduct_by_fk }</option>
													</c:forEach>
												   
												  </select> 
												  <span id="conduct_by_fkError" class="error-msg"></span>
								</div>
							 
						</div>

						<div class="row">
							<div class="col s6 m4 l4 input-field ">
								<input id="faculty_name" name="faculty_name" type="text" class="validate" value="${trainingDetails.faculty_name }">
								<label for="faculty_name">Faculty <span class="required">*</span></label>
								<span id="faculty_nameError" class="error-msg"></span>
							</div>
							<div class="col s6 m4 l4 input-field ">
								<input id="designation" type="text" name="designation" class="validate" value="${trainingDetails.designation }"> 
								<label for="designation">Designation</label>
								<span id="designationError" class="error-msg"></span>
							</div>
							<div class="col s12 m4 l4 input-field">
								<!-- <input id="training_center" type="text" class="validate" style="margin-top: 10px;">
                                    <label for="training_center"> Training Center </label> -->
								<textarea id="training_center" name="training_center" rows="2"
									class="pmis-textarea-h pmis-textarea">${trainingDetails.training_center }</textarea>
								<label for="training_center">Location</label>
							</div>
							 
						</div>
									<div class="col s6 m4 l4 input-field">
								    <p class="searchable_label">Provided To : <span class="required">*</span></p>
								    <select class="searchable validate-dropdown"
								        name="provide_to_fk" id="provide_to_fk">
								        <option value="">Provided To</option>
								        <c:forEach var="obj" items="${providedList}">
									<option value="${obj.provide_to_fk}"
									<c:if test="${trainingDetails.provide_to_fk  eq obj.provide_to_fk }">selected</c:if>>${obj.provide_to_fk }</option>
									</c:forEach>
								    </select> 
								       <span id="provide_to_fkError" class="error-msg"></span>
								</div>
								
								<div class="col s6 m4 l4 input-field" id="contractors-dropdown" style="display:none;">
								    <p class="searchable_label">Contractor Name: </p>
								    <select class="searchable validate-dropdown"
								        name="contract_short_name_fk" id="contract_short_name_fk">
								        <option value="">Select Option</option>
								        <c:forEach var="obj" items="${contract_short_nameList}">
													<option value="${obj.contract_short_name_fk }"
													<c:if test="${trainingDetails.contract_short_name_fk  eq obj.contract_short_name_fk}">selected</c:if>>${obj.contract_short_name_fk }</option>
													</c:forEach>
								    </select> 
								    <span id="contractor_optionError" class="error-msg"></span>
								</div>
								<script>
							    $(document).ready(function() {
							         // Check initial value of provide_to_fk
							        if ($("#provide_to_fk").val() == "Contractors") {
							            $("#contractors-dropdown").show();
							        }
							    	$("#provide_to_fk").change(function() {
							            if ($(this).val() == "Contractors") {
							                $("#contractors-dropdown").show();
							            } else {
							                $("#contractors-dropdown").hide();
							            }
							        });
							    });
								</script>
								
			
						
						<!-- <div class="col s6 m4 l4 input-field">
								<p class="searchable_label">Contract Name : <span class="required">*</span></p>
								<select class="searchable validate-dropdown"
									name="contract_short_name_fk" id="contract_short_name_fk" type="hidden" >
									<option value="">Select Contract </option>
									<c:forEach var="obj" items="${contract_short_nameList}">
													<option value="${obj.contract_short_name_fk }"
													<c:if test="${trainingDetails.contract_short_name_fk  eq obj.contract_short_name_fk}">selected</c:if>>${obj.contract_short_name_fk }</option>
													</c:forEach>
								</select> 
								<span id="training_category_fkError" class="error-msg"></span>
							</div>  -->	
					<!--  -->	
					
				<div class="row">
    				<div class="col m12 s12">        
						<div class="row fixed-width">
							<h5 class="center-align">Sessions</h5>
							<div class="col s12 m12 l12 table-inside" style=" width: 130%; margin-left:-150px; ">
								<table id="session-table" class="mdl-data-table mobile_responsible_table"style="text-align:center;">
									<thead>
										<tr>
											<th class="fw-110">Session No<span class="required">*</span></th>	
							<!--  		    <th>Date <span class="required">*</span></th> -->
											<th>Start Time<span class="required">*</span></th>
											<th>End Time<span class="required">*</span></th>
											<th>No.of Participants<span class="required">*</span></th>
											<th>No.of Absentees<span class="required">*</span></th>
											<c:if test="${action eq 'edit'}"><th>Dld Image</th></c:if>
											<th>Upload Photo</th>
											
											<th>Remarks</th>
										    <th>Delete</th>
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
													
														<td data-head="Session No" class="input-field">
														
														<input type="hidden" name="training_session_ids" id="training_session_ids${index.count }" value="${tObj.training_session_id}" />
														
														 <input id="session_nos${index.count }" name="session_nos" type="text" class="validate" value="${tObj.session_no }"
															placeholder="Session No"> </td>
															
							<!-- 	  					    <td data-head="Data" class="input-field">
														<input id="created_dates${index.count }" name="created_dates" type="text" class="validate datepicker" value="${tObj.date }" style="width: 110px;">
										                <button type="button" id="created_date${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
										                 <span id="dateError" class="error-msg" ></span> </td> -->	
														
														<td data-head="Start Time"  class="input-field"><div class="pos-rel">
														<input id="start_times${index.count }"  name="start_times" type="text" class="validate timepicker" value="${tObj.start_time}"placeholder="Start Time" style="width: 200px;">
															<button type="button" id="start_time_icon${index.count }"><i class="fa fa-clock-o"></i></button></div></td>
															
														<td data-head="End Time"  class="input-field"><div class="pos-rel">
														<input id="end_times${index.count }" name="end_times" type="text" class="validate timepicker" value="${tObj.end_time}" placeholder="End Time" style="width: 200px;">
															<button type="button" id="end_time_icon${index.count }"><i class="fa fa-clock-o"></i></button></div></td>
															
															
															
														<td data-head="No. of Participants" class="input-field">
            											 <input id="num_participants${index.count }" name="num_participants"  type="text" class="validate" value="${tObj.no_of_Participants}" placeholder="No.of Participants" ></td>
                        									
                        									<td data-head="No. of Absentees" class="input-field">
                           									 <input  id="num_absentees${index.count}"  name="num_absentees" type="text" class="validate"  value="${tObj.no_of_Absentees}"   placeholder="No.of Absentees"  onblur="validateAbsentees(${index.count})">
                        									<script>
															function validateAbsentees(index) {
															  var num_participants = parseInt(document.getElementById(`num_participants${index}`).value);
															  var num_absentees = parseInt(document.getElementById(`num_absentees${index}`).value);
															  
															  if (num_absentees >= num_participants) {
																alert("The number of absentees should be less than number of participants.");
															    document.getElementById(`num_absentees${index}`).value = 0;
															  }
															}
															</script>
                        									</td> 
                        									<td data-head="Dld Image" >
															    <a href="/pmis/TRAINING_GALLERY/${tObj.training_id}/${tObj.file_name}" download="" title="Click to Download Old Image">
															        <div class="btn bg-m t-c">
															            <i class="material-icons">file_download</i>
															            
															        </div>
															    </a>
															</td>
															<td data-head="Attach Image" class="cell-disp-inb input-field file-field">
									                                        <div class="btn bg-m t-c">
									                                            <span>Attach Image</span>
									                                            <input type="file" id="projectGalleryFiles${index.count }" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" onchange="validateImage('${index.count }')" value="${tObj.projectGalleryFiles}">
									                                        </div>
									                                        	 <div class="file-path-wrapper">
									                                            <input class="file-path validate" type="text" id="projectGalleryFileNames${index.count }" name="projectGalleryFileNames" value="${tObj.file_name }">
									                                      </div>    
									                                           
			                                                      	</td>
			                                                        
			                                                   
			                                                      	
			                                                      	<td data-head="Remarks" class="input-field">
                           									 <input type="text" name="remarkss" id="remarkss${index.count }" class="validate" value="${tObj.remarks}" placeholder="Remarks">
                        										</td> 
															
														<!-- put edit in this column below when u want to use this input  below as "${action eq 'edit'}" -->		
														  									
															
															
														<td class="mobile_btn_close center"><a onclick="removeTraining('${index.count }');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
													</tr>
													
													<script>
                                          				dateTimesInits++;                                         
                                          			 </script>
												</c:forEach>
											</c:when>
											<c:otherwise>
											<tr id="trainingRow0">
													<td data-head="Session No"  class="input-field" ><input type="hidden" name= "training_session_ids" id="training_session_ids0"  value="${tObj.training_session_id}"/><span id="session_nosError" class="error-msg">
													<input id="session_nos0" name="session_nos" type="text" class="validate" placeholder="Session No"></span></td>
														
											<!--    <td data-head="Date" class="input-field">
												 	<input id="created_date0" name="created_dates" type="text" class="validate datepicker" placeholder="Date">
									                <button type="button" id="created_date0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
									                <span id="dateError" class="error-msg" ></span></td>	-->	
													
													<td data-head="Start Time"  class="input-field"><div class="pos-rel"><input id="start_times0" name="start_times" type="text" class="validate timepicker" value="${tObj.start_times}" placeholder="Start Time">
													<button type="button" id="start_time_icon0"><i class="fa fa-clock-o"></i></button></div>
													<span id="start_timesError" class="error-msg"  ></span></td>
														
													<td data-head="End Time"  class="input-field"><div class="pos-rel"><input id="end_times0" name="end_times" type="text" class="validate timepicker"value="${tObj.end_times}" placeholder="End Time">
													<span id="end_timesError" class="error-msg" ></span>
													<button type="button" id="end_time_icon0"><i class="fa fa-clock-o"></i></button></div>
													</td>
														
													<td data-head="No. of Participants" class="input-field">
             										<input id="num_participants${index.count }" name="num_participants"  type="text" class="validate" value="${tObj.num_participants}" placeholder="No.of Participants">
             										<span id="num_participantsError" class="error-msg" ></span></td>
                        									
                        							<td data-head="No. of Absentees" class="input-field">
                           							<input id="num_absentees${index.count}" name="num_absentees" type="text" class="validate" value="${tObj.num_absentees}" placeholder="No.of Absentees" onblur="validateAbsentees(${index.count})">
                        							<span id="num_absenteesError" class="error-msg" ></span></td>		
                        									<script>
															function validateAbsentees(index) {
															  var num_participants = parseInt(document.getElementById(`num_participants${index}`).value);
															  var num_absentees = parseInt(document.getElementById(`num_absentees${index}`).value);
															  
															  if (num_absentees >= num_participants) {
																alert("The number of absentees should be less than number of participants.");
															    document.getElementById(`num_absentees${index}`).value = 0;
															  }
															}
															</script>
															
                        									</td>	
                        									
                        										<td data-head="Attach Image" class="cell-disp-inb input-field file-field">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach Image</span>
								                                            <input type="file" id="projectGalleryFiles0" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg"  >
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="projectGalleryFileNames0" name="projectGalleryFileNames">
								                                        </div>
		                                                  		</td>
			                                                
			                                                 	
			                                                 	<td data-head="Remarks" class="input-field">
                           									 <input type="text" name="remarkss" id="remarkss${index.count }" class="validate" value="${tObj.remarkss}" placeholder="Remarks">
                        									</td> 
			                                                 	
																					
													<td class="mobile_btn_close center"><a onclick="removeTraining('0');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>

								</table>
								<table id="mdl-data-table" class="mdl-data-table mobile_responsible_table" style="width:100%;text-align:center;">
									<tbody id="trainingBody">
										<tr>
											<td colspan="4"><a type="button" class="btn waves-effect waves-light bg-m t-c "
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
							
							<div class="col s12 m2 table-inside"></div>
						</div>
								
										
				    </div>
				</div>
					<br><br>
							<div class="row">
							 
							<div class="col s6 m6 l6 mt-brdr center-align">
								<div class=" m-1">
									<c:if test="${action eq 'edit'}">
										<button type="button" onclick="updateTraining();" class="btn waves-effect waves-light bg-m">Update</button>
									</c:if>
									<c:if test="${action eq 'add'}"> 
										<button type="button" onclick="addTraining();" class="btn waves-effect waves-light bg-m" style="min-width: 90px; ">Add</button>
									</c:if>
								</div>
							</div>
							<div class="col s6 m6 l6 mt-brdr center-align">
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
	
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	
	<script src="/pmis/resources/js/datetimepicker.js"></script>
	
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	
	<script>
	$(document).ready(function() {
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
    });
	 $("[data-length]").each(function(i,val){
     	$('#'+val.id).characterCounter();;
     });
	   function validateImage(RowNum) 
       {
           var formData = new FormData();
           var file = document.getElementById("projectGalleryFiles"+RowNum).files[0];
           formData.append("Filedata", file);
           var t = file.type.split('/').pop().toLowerCase();
           
           if (t != "jpeg" && t != "jpg" && t != "png" && t != "bmp" && t != "gif") {
               alert('Please select a valid image file');
               document.getElementById("projectGalleryFiles"+RowNum).value = '';
               return false;
           }
           return true;
       }
	    	
	    function removeImage(rowNo){
	    	$("#imageRow"+rowNo).remove();
	    }
	
	
	function autocomplete(inp, arr) {
		  /*the autocomplete function takes two arguments,
		  the text field element and an array of possible autocompleted values:*/
		  var currentFocus;
		  /*execute a function when someone writes in the text field:*/
		  inp.addEventListener("input", function(e) {
		      var a, b, i, val = this.value;
		      /*close any already open lists of autocompleted values*/
		      closeAllLists();
		      if (!val) { return false;}
		      currentFocus = -1;
		      /*create a DIV element that will contain the items (values):*/
		      a = document.createElement("DIV");
		      a.setAttribute("id", this.id + "autocomplete-list");
		      a.setAttribute("class", "autocomplete-items");
		      /*append the DIV element as a child of the autocomplete container:*/
		      this.parentNode.appendChild(a);
		      /*for each item in the array...*/
		      for (i = 0; i < arr.length; i++) {
		        /*check if the item starts with the same letters as the text field value:*/
		        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
		          /*create a DIV element for each matching element:*/
		          b = document.createElement("DIV");
		          b.classList.add('autoList');
		          /*make the matching letters bold:*/
		          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
		          b.innerHTML += arr[i].substr(val.length);
		          /*insert a input field that will hold the current array item's value:*/
		          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
		          /*execute a function when someone clicks on the item value (DIV element):*/
		          b.addEventListener("click", function(e) {
		              /*insert the value for the autocomplete text field:*/
		              inp.value = this.getElementsByTagName("input")[0].value;
		              /*close the list of autocompleted values,
		              (or any other open lists of autocompleted values:*/
		              closeAllLists();
		          });
		          a.appendChild(b);
		        }
		      }
		  });
		  /*execute a function presses a key on the keyboard:*/
		  inp.addEventListener("keydown", function(e) {
		      var x = document.getElementById(this.id + "autocomplete-list");
		      if (x) x = x.getElementsByTagName("div");
		      if (e.keyCode == 40) {
		        /*If the arrow DOWN key is pressed,
		        increase the currentFocus variable:*/
		        currentFocus++;
		        /*and and make the current item more visible:*/
		        addActive(x);
		      } else if (e.keyCode == 38) { //up
		        /*If the arrow UP key is pressed,
		        decrease the currentFocus variable:*/
		        currentFocus--;
		        /*and and make the current item more visible:*/
		        addActive(x);
		      } else if (e.keyCode == 13) {
		        /*If the ENTER key is pressed, prevent the form from being submitted,*/
		        e.preventDefault();
		        if (currentFocus > -1) {
		          /*and simulate a click on the "active" item:*/
		          if (x) x[currentFocus].click();
		        }
		      }
		  });
		  function addActive(x) {
		    /*a function to classify an item as "active":*/
		    if (!x) return false;
		    /*start by removing the "active" class on all items:*/
		    removeActive(x);
		    if (currentFocus >= x.length) currentFocus = 0;
		    if (currentFocus < 0) currentFocus = (x.length - 1);
		    /*add class "autocomplete-active":*/
		    x[currentFocus].classList.add("autocomplete-active");
		  }
		  function removeActive(x) {
		    /*a function to remove the "active" class from all autocomplete items:*/
		    for (var i = 0; i < x.length; i++) {
		      x[i].classList.remove("autocomplete-active");
		    }
		  }
		  function closeAllLists(elmnt) {
		    /*close all autocomplete lists in the document,
		    except the one passed as an argument:*/
		    var x = document.getElementsByClassName("autocomplete-items");
		    for (var i = 0; i < x.length; i++) {
		      if (elmnt != x[i] && elmnt != inp) {
		        x[i].parentNode.removeChild(x[i]);
		      }
		    }
		  }
		  /*execute a function when someone clicks in the document:*/
		  document.addEventListener("click", function (e) {
		      closeAllLists(e.target);
		  });
		}

	
	
	
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
		 var attendees = $("#attendees"+idNo).val();
     	 var dept = $("#attendees"+idNo).find('option:selected').attr("name");
     	 $('#department_fks'+idNo).val(dept);
     	 $('#department_fks'+idNo).select2();
     	 if($.trim(attendees) == ""){getAttendeesList(idNo)}
     	 
     	 	if(selectedAttendeesArray.indexOf($("#attendees"+idNo).val())=="-1")
     		 {
     	 		selectedAttendeesArray.push($("#attendees"+idNo).val());
     		 }
     	 
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
                                  $("#attendees"+count).append('<option name="' + val.department_fk + '" value="' + val.user_id + '" >'  +  $.trim(designation) + $.trim(userName) + '</option>');
                              } else {
                                  $("#attendees"+count).append('<option name="' + val.department_fk + '" value="' + val.user_id + '" >'  +  $.trim(designation) + $.trim(userName) +'</option>');
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
		    MaterialDateTimePicker.create($("#start_times0"), { format: 'HH:mm' });
		    MaterialDateTimePicker.create($("#end_times0"), { format: 'HH:mm' });

		    $('#start_time_icon0').click(function () {
		        MaterialDateTimePicker.create($("#start_times0"), { format: 'HH:mm' });
		    });
		    $('#end_time_icon0').click(function () {
		        MaterialDateTimePicker.create($("#end_times0"), { format: 'HH:mm' });
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
		    callTimePicker(dateTimesInits);
		});

		function callTimePicker() {
		    for ( var i = 1; i <= dateTimesInits; i++ ) {
		        MaterialDateTimePicker.create($("#start_times"+i), { format: 'HH:mm' });
		        MaterialDateTimePicker.create($("#end_times"+i), { format: 'HH:mm' });
		        $('#start_time_icon'+i).click(function () {
		            MaterialDateTimePicker.create($("#start_times"+i), { format: 'HH:mm' });
		        });
		        $('#end_time_icon'+i).click(function () {
		            MaterialDateTimePicker.create($("#end_times"+i), { format: 'HH:mm' });
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
  	var attendeesArray=new Array();
  	var selectedAttendeesArray=new Array();
  	var attendeesNamesArray=new Array();

        var rowNumber = null;
        function showNo(a){
        	attendeesArray=[];
        	attendeesNamesArray=[];
			
        	rowNumber = a.href.split("#")[1].split("-")[2].split('modal')[1]; 
			var k=0;
			
			
			var attendeesSelected = $('#trainingRow'+rowNumber+' select[name="attendees"]').map(function() {
    			if(selectedAttendeesArray.indexOf($(this).val())=="-1")
    			{
    				selectedAttendeesArray.push($(this).val());
    			}
            return $(this).val();
        }).get().join(",");			
       	
        	var attendees = $('#training-update-table'+rowNumber+' select[name="attendees"]').map(function() {
        			var id="";
        			var option="";
        			
       				if(k==0)
       				{
               			id=$(this).attr("id");
               			k++;
               			$("#"+id+" option").each(function()
               					{
		                			if(attendeesArray.indexOf($(this).val())=="-1")
		                			{              				
				    	            		attendeesArray.push($(this).val());
				    	            		attendeesNamesArray.push($('#'+id+' option[value="'+$(this).val()+'"]').text());
		                			}
               					});              			
       				}
        				
	            return $(this).val();
	        }).get().join(",");
        	
        	
       	    var m=0;
       	    var rowInput="";
        	
        	var getRowId = $('#training-new-update-table'+rowNumber+' input[name="attendees"]').map(function() {
    			var id="";
   				if(m==0)
   				{

   					rowInput=$(this).attr("id");
   					m++;
           			             			
   				}
   				
            return rowInput;
        }); 	

        	
        	
        	
        	$('#session-update-modal'+rowNumber).removeAttr('tabindex');
        	
    		autocomplete(document.getElementById(rowInput), attendeesNamesArray);	
    		
 		
       	
        	
        }
        
     
        var selectedvalueArray=new Array();
        
        function addSelectedRow(t) 
        	{
     	
        		var id=$(t).attr('id');
        		if(id!="")
       			{
        		  	  var rowNo = $("#rowNo").val();
        	          var rNo = Number(rowNo)+1;
        	          
            		addTrainingUpdateRow(rowNumber,rNo);
            		var concat="";
            		
            		
            		$('#training-update-table'+rowNumber+' select[name="attendees"]').map(function() {
            			var id=id=$(this).attr("id");
            			concat=id;
            				
    	            return $(this).val();
    	        })   
  
        			if(selectedvalueArray.indexOf($("#"+concat).val())=="-1")
        			{
        				selectedvalueArray.push($("#"+concat).val());
                		$("#"+concat+" option:contains('"+$(t).val()+"')").prop('selected', true); 
                		$("#"+concat+" option:contains('"+$(t).val()+"')").trigger('change'); 
                		$("#msg-text").html("<b>If name of attendee is not in list please add below</b>");
        			}   
        			else
       				{
        				$("#msg-text").html($("#msg-text").html()+'-----<span style="color:red;">Already selected Attendee</span>');
        				 var table = document.getElementById('training-update-table'+rowNumber);
        				    table.deleteRow(-1);
        				    selectedvalueArray.pop();
       				}
            		
            		$("#"+id).val("");
            		
            		
       			}
        	
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
							'<option name="${obj.department_fk }" value="${obj.user_id }">${obj.designation }<c:if test="${not empty obj.designation }"> - </c:if>${obj.attendee }</option>'+
						</c:forEach> 
					'</select></td><input type="hidden" name="hod_user_id_fks"/><input type="hidden"  name="emails" class="no-reset" /><input type="hidden" name="trainee_designations"/><input type="hidden" name="mobile_nos"/>' +	                
	                '<td data-head="Nominated" class="input-field"><p class="disp-init"><label><input type="hidden" name="required_fks" id="required_fk'+ rNo +tNo+'" value="No" class="req"/><input type="checkbox" id="required_fks'+ rNo +tNo+'" class="required_fks"/><span></span></label></p></td>' +
	                '<td data-head="Participated" class="input-field"><p class="disp-init"><label><input type="hidden" name="participated_fks" id="participated_fk'+ rNo +tNo+'" value="No" class="part"/><input type="checkbox" id="participated_fks'+ rNo +tNo+'" class="participated_fks" /><span></span></label></p></td>' +
	                '<td class="mobile_btn_close"><a onclick="removeTrainingAttendees('+rNo+','+tNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
	                
	                
             $('#attendeesTableBody'+ index).append(html);
             
             
             $('#attendees'+rNo +tNo+' option').each(function() {
             	if(selectedAttendeesArray.indexOf($(this).val())!="-1")
         		{
             		$('#attendees'+rNo +tNo+' option[value="'+$(this).val()+'"]').remove();
         		}
             });
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
            '<td data-head="Attendee" class="input-field"> <input id="new_attendees'+ rNo +tNo+'" name="new_attendees" type="text" class="validate attendees autocomplete" placeholder="Name"><span id="new_attendeesError'+ rNo +tNo+'" class="error-msg"></span>  </td>' +
            '<td data-head="Department" class="input-field">'+
		 	   '<input type="hidden" name= "training_session_id_fks" id="new_training_session_id_fks'+rNo+tNo+'" value="'+trainingSessionId+'" />'+
			   '<input type="hidden" name="training_attendees_ids" id="new_training_attendees_ids'+ rNo +tNo+'" />'+
            '<select id="new_department_fks'+ rNo +tNo+'" name="department_fks" class="searchable validate-dropdown department_fks" onchange="getHODsList('+ rNo +tNo+');">'+
            '<option value="" >Select Department</option>'+
	                <c:forEach var="obj" items="${departmentsList}">
	             	  '<option value="${obj.department_fk }">${obj.department_name}</option>' +
	                </c:forEach>
      	    '</select> <span id="new_department_fksError'+ rNo +tNo+'" class="error-msg"></span> </td><input type="hidden"  name="is_new_users" value="Yes"/>'+
             '<td data-head="HOD" class="input-field"> <select class="searchable hod_user_id_fks" name="hod_user_id_fks" id="new_hod_user_id_fks'+ rNo +tNo+'" onchange="setHODDeptList('+ rNo +tNo+');">'+
             '<option value="" >Select HOD</option>'+
             	<c:forEach var="obj" items="${usersList}">
					'<option name="${obj.department_fk }" value="${obj.hod_user_id_fk }">${obj.designation }</option>'+
				</c:forEach> 
             '</select><span id="new_hod_user_id_fkError'+ rNo +tNo+'" class="error-msg"></span></td>'+
             '<td data-head="Designation" class="input-field"> <input type="text" placeholder="Designation" class="trainee_designations" id="new_trainee_designations'+ rNo +tNo+'" name="trainee_designations" ><span id="new_trainee_designationsError'+ rNo +tNo+'" class="error-msg"></span></td><td data-head="Email" class="input-field"> <input class="email" type="text" placeholder="Email" id="email'+ rNo +tNo+'" name="emails" ><span id="emailError'+ rNo +tNo+'" class="error-msg"></span></td>'+
             '<td data-head="Mobile" class="input-field"><input id="new_mobile_nos'+ rNo +tNo+'" name="mobile_nos" type="number" class="validate new_mobile_nos" placeholder="Mobile"><span id="mobile_nosError'+ rNo +tNo+'" class="error-msg"></span> </td>' +
             '<td data-head="Nominated" class="input-field"><p class="disp-init"><label><input type="hidden" name="required_fks" id="new_required_fk'+ rNo +tNo+'" value="No" class="req"/><input type="checkbox" id="new_required_fks'+ rNo +tNo+'" class="required_fks"/><span></span></label></p><span id="new_required_fkError'+ rNo +tNo+'" class="error-msg"></span></td>' +
             '<td data-head="Participated" class="input-field"><p class="disp-init"><label><input type="hidden" name="participated_fks" id="new_participated_fk'+ rNo +tNo+'" value="No" class="part"/><input type="checkbox" id="new_participated_fks'+ rNo +tNo+'" class="participated_fks" /><span></span></label></p><span id="new_participated_fkError'+ rNo +tNo+'" class="error-msg"></span></td>' +
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
           	 $('.attendees').keyup(function(key, element){
      			$(".attendees").each(function(){
      				var idNo = (this.id).replace('new_attendees',''); 
            		if($.trim(this.value) != ""){ 
            			$('#new_attendeesError'+idNo).text('');
      			}
                });
      		});
           $('.department_fks').change(function(key, element){
     			$(".department_fks").each(function(){
     				var idNo = (this.id).replace('new_department_fks',''); 
           		if($.trim(this.value) != ""){ 
           			$('#new_department_fksError'+idNo).text('');
     			}
               });
     		});
           $('.required_fks').change(function(key, element){
     			$(".required_fks").each(function(){
     				var idNo = (this.id).replace('new_required_fks',''); 
     				var new_required_fks = $("#new_required_fks"+idNo+":checked");
          			if($("#new_required_fks"+idNo).is(":checked")){
            			$('#new_required_fkError'+idNo).text('');
     			}
                });
     		}); 
         /*   $('.participated_fks').change(function(key, element){
     			$(".participated_fks").each(function(){
     				var idNo = (this.id).replace('new_participated_fks',''); 
     				var participatedCheck = $("#new_participated_fks"+idNo+":checked");
          			if($("#new_participated_fks"+idNo).is(":checked")){
             			$('#new_participated_fkError'+idNo).text('');
     				}
                 });
     		});   */
           $('.new_mobile_nos').keyup(function(key, element){
     			$("input[name=mobile_nos]").each(function(){
     				var idNo = (this.id).replace('new_mobile_nos',''); 
           		if($.trim(this.value) != "" ){ 
           			$('#mobile_nosError'+idNo).text('');
     			}
               });
     			
     		});
           $('.email').keyup(function(key, element){
     			$("input[name=emails]").each(function(){
     				var idNo = (this.id).replace('email',''); 
           		if($.trim(this.value) != "" ){ 
            			$('#emailError'+idNo).text('');
     			}
                }); 
     		});
           $('.trainee_designations').keyup(function(key, element){
     			$("input[name=trainee_designations]").each(function(){
     				var idNo = (this.id).replace('new_trainee_designations',''); 
             		if($.trim(this.value) != "" ){ 
             			$('#new_trainee_designationsError'+idNo).text('');
     				}
                 });
     		});
           $('.hod_user_id_fks').change(function(key, element){
      			$(".hod_user_id_fks").each(function(){
      				var idNo = (this.id).replace('new_hod_user_id_fks',''); 
              		if($.trim(this.value) != "" ){ 
              			$('#new_hod_user_id_fkError'+idNo).text('');
      					$('#new_department_fksError'+idNo).text('');
      				}
                  });
      		});
           
           
           
           attendeesArray=[];
       	   attendeesNamesArray=[];
			
			var k=0;
      	
       	var attendees = $('#training-update-table'+rNo+' select[name="attendees"]').map(function() {
       			var id="";
       			var option="";
       			
      				if(k==0)
      				{
              			id=$(this).attr("id");
              			k++;
              			$("#"+id+" option").each(function()
              					{
		                			if(attendeesArray.indexOf($(this).val())=="-1")
		                			{              				
				    	            		attendeesArray.push($(this).val()); 
				    	            		attendeesNamesArray.push($('#'+id+' option[value="'+$(this).val()+'"]').text());
		                			}
              					});              			
      				}
       				
	            return $(this).val();
	        }).get().join(",");
       	
    	var concat="new_attendees"+rNo +tNo;
   	
   		autocomplete(document.getElementById(concat), attendeesNamesArray);	   
           
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

    	  var html = '<tr id="trainingRow'+rNo+'">' +
          '<td data-head="Session No" class="input-field"><input type="hidden" name= "training_session_ids" id="training_session_ids'+rNo+'"  />'+
          ' <input id="session_nos'+ rNo +'" name="session_nos" type="text" class="validate" placeholder="Session No"> </td>' +
          
              
           '<td data-head="Start Time"  class="input-field"><div class="pos-rel"><input id="start_times'+ rNo +'" name="start_times" type="text" class="validate timepicker"  placeholder="Start Time">' +
         	  '<button type="button" id="start_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></div> </td>' +
         	 
          '<td data-head="End Time" class="input-field"><div class="pos-rel"><input id="end_times'+ rNo +'" name="end_times" type="text" class="validate timepicker"placeholder="End Time">' +
          	  '<button type="button" id="end_times_icon'+ rNo +'"><i class="fa fa-clock-o"></i></button></div></td>'+
          	  
          	  
          	 '<td data-head="No. of Participants" class="input-field">'+
             ' <input id="num_participants'+ rNo +'" name="num_participants" type="text" class="validate"placeholder="No.of Participants"></td> ' +
             
             '<td data-head="No. of Absentees" class="input-field">'+
             ' <input id="num_absentees"'+ rNo +'" name="num_absentees" type="text" class="validate"placeholder="No. of Absentees"></td> ' +
             
             <c:if test="${action eq 'edit'}">
             '<td data-head="Download Photo" >'+
			 '<div class="btn-small bg-m t-c" style="margin-right: 5px;">'+
		        '<a href="/pmis/TRAINING_GALLERY/${tObj.file_name}" download="" class="template-btn" title="Click to Download Old Image">'+
		           ' <i class="material-icons">file_download</i>'+
		        '</a> </div></td>'+ </c:if>      
                        
              
             '<td data-head="Attach Image" class="cell-disp-inb input-field file-field"> '	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Image</span>'	
			   +'<input type="file" id="projectGalleryFiles'+rNo+'" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" onchange="validateImage('+rNo+')">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectGalleryFileNames'+rNo+'" name="projectGalleryFileNames">'	
			   +'</div></td>'
			   +
             
             
             '<td data-head="Remarks" class="input-field">'+
                ' <input id="remarkss'+ rNo +'" name="remarkss" type="text" class="validate"placeholder="Remarks"></td> ';
             
      
	                          
	                          html +='<td class="mobile_btn_close "> <a onclick="removeTraining('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';       	  
          
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
       	 $('.attendees').keyup(function(key, element){
  			$(".attendees").each(function(){
  				var idNo = (this.id).replace('new_attendees',''); 
        		if($.trim(this.value) != ""){ 
        			$('#new_attendeesError'+idNo).text('');
  			}
            });
  		});
       $('.department_fks').change(function(key, element){
 			$(".department_fks").each(function(){
 				var idNo = (this.id).replace('new_department_fks',''); 
       		if($.trim(this.value) != ""){ 
       			$('#new_department_fksError'+idNo).text('');
 			}
           });
 		});
       $('.required_fks').change(function(key, element){
 			$(".required_fks").each(function(){
 				var idNo = (this.id).replace('new_required_fks',''); 
 				var new_required_fks = $("#new_required_fks"+idNo+":checked");
      			if($("#new_required_fks"+idNo).is(":checked")){
        			$('#new_required_fkError'+idNo).text('');
 			}
            });
 		}); 
    /*    $('.participated_fks').change(function(key, element){
 			$(".participated_fks").each(function(){
 				var idNo = (this.id).replace('new_participated_fks',''); 
 				var participatedCheck = $("#new_participated_fks"+idNo+":checked");
      			if($("#new_participated_fks"+idNo).is(":checked")){
         			$('#new_participated_fkError'+idNo).text('');
 				}
             });
 		});   */
       $('.new_mobile_nos').keyup(function(key, element){
 			$("input[name=mobile_nos]").each(function(){
 				var idNo = (this.id).replace('new_mobile_nos',''); 
       		if($.trim(this.value) != "" ){ 
       			$('#mobile_nosError'+idNo).text('');
 			}
           });
 			
 		});
       $('.email').keyup(function(key, element){
 			$("input[name=emails]").each(function(){
 				var idNo = (this.id).replace('email',''); 
       		if($.trim(this.value) != "" ){ 
        			$('#emailError'+idNo).text('');
 			}
            }); 
 		});
       $('.trainee_designations').keyup(function(key, element){
 			$("input[name=trainee_designations]").each(function(){
 				var idNo = (this.id).replace('new_trainee_designations',''); 
         		if($.trim(this.value) != "" ){ 
         			$('#new_trainee_designationsError'+idNo).text('');
 				}
             });
 		});
       $('.hod_user_id_fks').change(function(key, element){
  			$(".hod_user_id_fks").each(function(){
  				var idNo = (this.id).replace('new_hod_user_id_fks',''); 
          		if($.trim(this.value) != ""){ 
          			$('#new_hod_user_id_fkError'+idNo).text('');
  					$('#new_department_fksError'+idNo).text('');
  				}
              });
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
        	 $("#rowsCounts"+rNo).val(value);
        	 
       	 
		var indexVal=selectedAttendeesArray.indexOf($("#attendees"+rNo).val()); 
		attendeesArray.splice(indexVal, 1);
		
		var indexText=attendeesNamesArray.indexOf($("#attendees"+rNo+" option:selected").text()); 
		attendeesNamesArray.splice(indexText, 1);
		
		
        	 
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
			$('form input[name=num_participants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=num_absentees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=attendees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=trainee_designations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=mobile_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=required_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=participated_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=hod_user_id_fk]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=is_new_user]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			var flag = validateAttendees(rowNumber);
			if(flag){
				document.getElementById("trainingForm").submit();	
			}else{
	        	$(".page-loader").hide();
	 		}
    	}
     }
     
     function addTraining(){
    	if(validator.form()){ // validation perform
			$(".page-loader").show();
			$('form input[name=session_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			
			$('form input[name=start_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=end_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=num_participants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=num_absentees]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
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
  			 	  },"period_fk": {
  		 		    required: true
  			 	  },"title": {
  		 		    required: true
  			 	  },"description": {
  		 		    required: true
  			 	  },"conduct_by_fk": {
  		 		    required: true
  			 	  },"designation": {
  		 		    required: false
  			 	  },"faculty_name":{
  		 		    required: true
  			 	  },"provide_to_fk":{
  			 		required: true
  			 	  },"session_nos":{
  			 		required: true
  			 	  },"start_times":{
  			 		required: true
  			 	  },"end_times":{
  			 		required: true
  			 	  },"num_participants":{
  			 		required: true
  			 	  },"num_absentees":{
  			 		required: true
  			 	  },"remarkss":{
  			 		required: false
  			 	  },"session_nos":{
  			 		required: true
  			 	  }
  			 	  
  		 	},
  		    messages: {
  		 		 "training_type_fk": {
  				 	required: 'This field is required',
  			 	  },"training_category_fk": {
  			 		required: ' This field is required'
  			 	  },"period_fk": {
  			 		required: ' This field is required'
  			 	  },"status_fk": {
  		 			required: ' This field is required'
  		 	  	 },"title": {
  		 			required: ' This field is required'
  		 	  	 },"description": {
  		 			required: ' This field is required'
  		 	     },"conduct_by_fk": {
		 			required: ' This field is required'
  		 	  	 },"designation": {
  		 			required: ' This field is required'
  		 	  	 },"faculty_name": {
  		 			required: ' This field is required'
  		 	  	 },"provide_to_fk": {
  		 			required: ' This field is required'
  		 	  	 },"session_nos": {
  		 			required: ' Required'
  		 	  	 },"start_times": {
  		 			required: ' This field is required'
  		 	  	 },"end_times": {
  		 			required: ' This field is required'
  		 	  	 },"num_participants": {
  		 			required: ' This field is required'
  		 	  	 },"num_absentees": {
  		 			required: ' This field is required'
  		 	  	 }
	   		},
	   		
	   		errorPlacement:function(error, element){
	   		 	if (element.attr("id") == "training_type_fk" ){
					 document.getElementById("training_typeError").innerHTML="";
			 		 error.appendTo('#training_typeError');
			    }else if(element.attr("id") == "training_category_fk" ){
					   document.getElementById("training_category_fkError").innerHTML="";
				 	   error.appendTo('#training_category_fkError');
			    }else if(element.attr("id") == "period_fk" ){
					   document.getElementById("period_fkError").innerHTML="";
				 	   error.appendTo('#period_fkError');
			    }else if(element.attr("id") == "status_fk" ){
						document.getElementById("status_fkError").innerHTML="";
					 	error.appendTo('#status_fkError');
			    }else if(element.attr("id") == "title" ){
			 		 document.getElementById("titleError").innerHTML="";
	 				 error.appendTo('#titleError');
				}else if(element.attr("id") == "description" ){
			 		 document.getElementById("descriptionError").innerHTML="";
	 				 error.appendTo('#descriptionError');
				}else if(element.attr("id") == "conduct_by_fk" ){
			 		 document.getElementById("conduct_by_fkError").innerHTML="";
	 				 error.appendTo('#conduct_by_fkError');
				}else if(element.attr("id") == "designation" ){
			 		 document.getElementById("designationError").innerHTML="";
	 				 error.appendTo('#designationError');
				}else if(element.attr("id") == "faculty_name" ){
			 		 document.getElementById("faculty_nameError").innerHTML="";
	 				 error.appendTo('#faculty_nameError');
				}else if(element.attr("id") == "provide_to_fk" ){
			 		 document.getElementById("provide_to_fkError").innerHTML="";
	 				 error.appendTo('#provide_to_fkError');
				}else if(element.attr("id") == "session_nos" ){
			 		 document.getElementById("session_nosError").innerHTML="";
	 				 error.appendTo('#session_nosError');
				}else if(element.attr("id") == "start_times" ){
			 		 document.getElementById("start_timesError").innerHTML="";
	 				 error.appendTo('#start_timesError');
				}else if(element.attr("id") == "num_participants" ){
			 		 document.getElementById("num_participantsError").innerHTML="";
	 				 error.appendTo('#num_participantsError');
				}else if(element.attr("id") == "num_absentees" ){
			 		 document.getElementById("num_absenteesError").innerHTML="";
	 				 error.appendTo('#num_absenteesError');
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
		         }validateAttendees();
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
         var rowNumber = 1;
         function validateAttendees(rowNo){
        	 rowNumber = rowNo;
     		var flag = true;
     		$(".department_fks").each(function(){
     			var idNo = (this.id).replace('new_department_fks','');
     			var new_department_fks = $("#new_department_fks"+idNo).val();
     			if($.trim(new_department_fks) == "" ){
 					$('#new_department_fksError'+idNo).text('Requried');
 					$('#new_department_fksError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		
     		$(".hod_user_id_fks").each(function(){
     			var idNo = (this.id).replace('new_hod_user_id_fks','');
     			var new_hod_user_id_fks = $("#new_department_fks"+idNo).val();
     			if($.trim(new_hod_user_id_fks) == "" ){
 					$('#new_hod_user_id_fkError'+idNo).text('Requried');
 					$('#new_hod_user_id_fkError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".attendees").each(function(){
     			var idNo = (this.id).replace('new_attendees','');
     			var new_attendees = $("#new_attendees"+idNo).val();
     			if($.trim(new_attendees) == "" ){
 					$('#new_attendeesError'+idNo).text('Requried');
 					$('#new_attendeesError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".trainee_designations").each(function(){
     			var idNo = (this.id).replace('new_trainee_designations','');
     			var new_trainee_designations = $("#new_trainee_designations"+idNo).val();
     			if($.trim(new_trainee_designations) == "" ){
 					$('#new_trainee_designationsError'+idNo).text('Requried');
 					$('#new_trainee_designationsError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".email").each(function(){
     			var idNo = (this.id).replace('email','');
     			var email = $("#email"+idNo).val();
     			if($.trim(email) == "" ){
 					$('#emailError'+idNo).text('Requried');
 					$('#emailError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".new_mobile_nos").each(function(){
     			var idNo = (this.id).replace('new_mobile_nos','');
     			var new_mobile_nos = $("#new_mobile_nos"+idNo).val();
     			if($.trim(new_mobile_nos) == "" ){
 					$('#mobile_nosError'+idNo).text('Requried');
 					$('#mobile_nosError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     		$(".required_fks").each(function(){
     			var idNo = (this.id).replace('new_required_fks','');
     			var reqCheck =  $("#new_required_fks"+idNo+":checked")
     			if(reqCheck.length > 0){
 					$('#new_required_fkError'+idNo).text('');
 				}else{
 					$('#new_required_fkError'+idNo).text('Requried');
 					$('#new_required_fkError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
 				}
     		});
     	/* 	$(".participated_fks").each(function(){
     			var idNo = (this.id).replace('new_participated_fks','');
     			var participatedCheck = $("#new_participated_fks"+idNo+":checked");
     			if(participatedCheck.length > 0){
 					$('#new_participated_fkError'+idNo).text('');
     			}else{
     				$('#new_participated_fkError'+idNo).text('Requried');
 					$('#new_participated_fkError'+idNo).slideDown(100,function(){
 						$(this).focus();
 					});
 					flag = false;
     			}
 				}); */
     			var len = $('#newAttendeesTableBody'+rowNo+' tr').length;
     			$(".department_fks").each(function(){
     				
         			var idNo = (this.id).replace('new_department_fks','');
         			var new_department_fks = $("#new_department_fks"+idNo).val();
         			var new_hod_user_id_fks = $("#new_department_fks"+idNo).val();
         			var new_attendees = $("#new_attendees"+idNo).val();
         			var new_trainee_designations = $("#new_trainee_designations"+idNo).val();
         			var new_mobile_nos = $("#new_mobile_nos"+idNo).val();
         			var reqCheck =  $("#new_required_fks"+idNo+":checked");
         			//var participatedCheck = $("#new_participated_fks"+idNo+":checked");
         			var email = $("#email"+idNo).val();
         			if($.trim(new_department_fks) == "" && $.trim(new_hod_user_id_fks) == "" && $.trim(new_attendees) == "" && $.trim(new_trainee_designations) == "" 
         				&& $.trim(new_mobile_nos) == "" && $.trim(email) == "" && (reqCheck.length == 0)){
     					$('#new_department_fksError'+idNo).text('');
     					$('#new_hod_user_id_fkError'+idNo).text('');
     					$('#new_attendeesError'+idNo).text('');
     					$('#new_trainee_designationsError'+idNo).text('');
     					$('#emailError'+idNo).text('');
     					$('#mobile_nosError'+idNo).text('');
     					$('#new_required_fkError'+idNo).text('');
     					//$('#new_participated_fkError'+idNo).text('');
     				}
         		});
     			//$('#newAttendeesTableBody'+rowNo+' .error-msg').each(function(index,value){
     				a = [];
     				$('#newAttendeesTableBody'+rowNo+' .error-msg').each(function(){a.push(this.innerHTML)});
     				console.log(a)
     				var found = a.includes('Requried');
     				if (!found){
     					flag = true;
     				}
     			//})
     			if(!flag){
     				 //$('#session-update-modal'+rowNo).modal();
     				//$('#session-update-modal'+rowNo).modal({dismissible:false});
     				$('#session-update-modal'+rowNo).modal('open');
     			}else{
     				$('#session-update-modal'+rowNo).modal('close');
     			}
     			return flag;
     		};
            
         $('.attendees').keyup(function(key, element){
 			$(".attendees").each(function(){
 				var idNo = (this.id).replace('new_attendees',''); 
       		if($.trim(this.value) != ""){ 
       			$('#new_attendeesError'+idNo).text('');
 			}
           });
 		});
      $('.department_fks').change(function(key, element){
			$(".department_fks").each(function(){
				var idNo = (this.id).replace('new_department_fks',''); 
      		if($.trim(this.value) != ""){ 
      			$('#new_department_fksError'+idNo).text('');
			}
          });
		});
      $('.required_fks').change(function(key, element){
			$(".required_fks").each(function(){
				var idNo = (this.id).replace('new_required_fks',''); 
				var new_required_fks = $("#new_required_fks"+idNo+":checked");
     			if($("#new_required_fks"+idNo).is(":checked")){
       			$('#new_required_fkError'+idNo).text('');
			}
           });
		}); 
     /*  $('.participated_fks').change(function(key, element){
			$(".participated_fks").each(function(){
				var idNo = (this.id).replace('new_participated_fks',''); 
				var participatedCheck = $("#new_participated_fks"+idNo+":checked");
     			if($("#new_participated_fks"+idNo).is(":checked")){
        			$('#new_participated_fkError'+idNo).text('');
				}
            });
		});   */
      $('.new_mobile_nos').keyup(function(key, element){
			$("input[name=mobile_nos]").each(function(){
				var idNo = (this.id).replace('new_mobile_nos',''); 
      		if($.trim(this.value) != "" ){ 
      			$('#mobile_nosError'+idNo).text('');
			}
          });
			
		});
      $('.email').keyup(function(key, element){
			$("input[name=emails]").each(function(){
				var idNo = (this.id).replace('email',''); 
      		if($.trim(this.value) != "" ){ 
       			$('#emailError'+idNo).text('');
			}
           }); 
		});
      $('.trainee_designations').keyup(function(key, element){
			$("input[name=trainee_designations]").each(function(){
				var idNo = (this.id).replace('new_trainee_designations',''); 
        		if($.trim(this.value) != "" ){ 
        			$('#new_trainee_designationsError'+idNo).text('');
				}
            });
		});
      $('.hod_user_id_fks').change(function(key, element){
 			$(".hod_user_id_fks").each(function(){
 				var idNo = (this.id).replace('new_hod_user_id_fks',''); 
         		if($.trim(this.value) != ""){ 
         			$('#new_hod_user_id_fkError'+idNo).text('');
 					$('#new_department_fksError'+idNo).text('');
 				}
             });
 		});
      
      document.addEventListener('DOMContentLoaded', function() {
    	    var elems = document.querySelectorAll('.datepicker');
    	    var options = {
    	        format: 'yyyy-mm-dd',
    	        autoClose: true
    	    };
    	    var instances = M.Datepicker.init(elems, options);
    	});
     
      function loadContracts() {
    	    var provideTo = document.getElementById("provide_to_fk").value;
    	    var url = "loadContracts.jsp?provide_to=" + provideTo;
    	    var xhttp = new XMLHttpRequest();
    	    xhttp.onreadystatechange = function() {
    	        if (this.readyState == 4 && this.status == 200) {
    	            document.getElementById("contract_short_name_fk").innerHTML = this.responseText;
    	        }
    	    };
    	    xhttp.open("GET", url, true);
    	    xhttp.send();
    	}

    


 </script>
</body>

