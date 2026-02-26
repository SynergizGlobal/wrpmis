<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Project - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Project - Update Forms - PMIS</c:if>    
    </title>
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/project.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-responsive-table.css" >
    
	<style>
		.my-error-class {
			color: red;
		}
		
		.my-valid-class {
			color: green;
		}
		.images-show{
			display: flex;
		    flex-wrap: wrap;
		    justify-content: flex-start;
		}
		.images-show img,.images-show video{
			width:100px;
			height:100px;
			margin:5px;
		}
		
		.input-field {
		    margin-top: .35rem;
		    margin-bottom: .35rem;
		}
		.input-field>textarea+label:not(.label-icon).active{
			margin-top: 8px;
		}         
		.images-show img:first-of-type{			
			margin-left:0;
		}
		.images-show img:last-of-type{
			margin-right:0;
		}
		.select2-container--default .select2-selection--single{
			background-color:transparent;
		}
		
		.w7em{width: 7em;}
		.row{
			margin-bottom: 10px;
		}
		@media(max-width: 2200px){
		.table-add{position: relative;}
		
   		.bd-none{border: none;}
   		 }
    	
    	@media(max-width: 820px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	}
		@media only screen and (max-width: 600px) {
		  .images-show{
		    justify-content: space-evenly;
		  }
		}
		.col.input-field>textarea+label:not(.label-icon).active{
		    margin-top:0;
		}
		.input-field p.searchable_label{
			margin-top: -10px !important;
			color :#8585ad;
		}
		.datepicker-table th,
        .datepicker-table td {
            padding: 0 !important;
        }
     
		.datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom: none !important;
        }
        .mobile_responsible_table .datepicker~button {
            top: 26px;
        }        
        
        /* new css code starts from here */ 
		.col.issue-mar,
		.issue-mar{
			margin-top:40px;
		 }
		 .mobile-prio,
		 .col.mobile-prio{
		 	margin-top:30px;
		 }
		.mb-md-20{
			margin-bottom:20px !important;
		}
		.mt-md-0{
			margin-top:0 !important;
		}
		.lh{
			line-height:inherit;
		}
		@media  only screen and (min-width: 769px)  {
			/*  #created_date0_icon{
			 	top:30px;
			 	right:20px;
			 } */
			 .md-pt-2px{
			 	padding-top:2px !important;
			 }
			 .md-mt-0{
			 	margin-top:0 !important;
			 }
		}
		
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			.mobile_responsible_table tbody tr .input-field .datepicker~button {
			    position: relative;
			    top: 0;
			    right: 26px;
			}			
			.mobile_responsible_table tbody tr td .select2-container {
			    width: 100% !important;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
			    vertical-align: middle;
			}
			.mobile_responsible_table tbody {
			    overflow-x:hidden;
			}
			.mt-brdr{
				margin-top: auto !important;
    			border: none !important;
			}
			.mt-brdr .btn{
   				width: 100% !important;
			}
			.mobile-prio{
				margin-top:14px;
				margin-bottom:14px;
			}
			.mobile-prio >.prio{
				text-align:center;
				margin-bottom:5px;
			}
			.col.issue-mar,
			.issue-mar{
				margin-top:25px;
				text-align:center;
			}
			/* input fields styles for mobile version  */
			div.input-field {
			    margin-top: 1rem;
			    margin-bottom: 1rem;
			}
			.input-field p.searchable_label {
			    margin-top: -20px !important;
   				margin-bottom: -4px;
			}
			.input-field>.datelike ~ label:not(.label-icon).active, 
			.input-field>input[type='text']:not(.datepicker) ~ label:not(.label-icon).active,
			.input-field>label:not(.label-icon).active {
			    -webkit-transform: translateY(-18px) scale(0.95);
			    transform: translateY(-18px) scale(0.95);
			}			
			.mb-md-20	{
				margin-bottom:0 !important;
			}				
			.fs-sm-8rem{
				font-size:.8rem !important;
			}	
			.input-field>.datepicker~button{
				top:10px;
				right:6px;
			}			
			.mt-md-0{
				margin-top: -14px !important;
			} 
			.mobile_responsible_table>tbody tr:not(.datepicker-row):not(.mobile_hide_row) {
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row)> td> div.btn{
				float:none;
				position:relative;
			}
			td.cell-disp-inb .file-path-wrapper {
			    visibility: visible;
			    width: 200%;
			    display: block !important;
			}
		}
			.input-field>input[type='text'].datepicker ~ label:not(.label-icon).active{
				-webkit-transform: translateY(-20px) scale(0.95);
			    transform: translateY(-20px) scale(0.95);
			}	
		@media(max-width: 575px){
		.row .col{margin: 6px auto !important;}
		}
		 #compensation_unitsError{
	   		float:right;	
	    }
	   .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
		.pdr3em{
			padding-right: 3em !important;
		} 
		.pdr4em{
			padding-right: 4em !important;
		}
		.pdr5em{
			padding-right: 5em !important;
		}
		.w85{
			width: 85% !important;
		}
		.w80{
			width: 80% !important;
		}
		.w75{
			width: 75% !important;
		}
		.w70{
			width: 70% !important;
		}
		
		    .page-title-bar {
        background: #f4f4f4;
        padding: 20px 30px;
        border-bottom: 1px solid #ccc;
        margin-bottom: 10px;
    }

    .page-title-bar h5 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
        display: inline-block;
    }

    .action-buttons {
        float: right;
        margin-top: -35px;
    }

    .action-buttons .btn {
        margin-left: 10px;
    }

    .data-table-wrapper {
        background: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
    }

    .success, .error {
        margin: 10px 0;
        padding: 10px;
        border-radius: 4px;
        text-align: center;
        font-weight: bold;
    }

    .success {
        background-color: #d4edda;
        color: #155724;
    }

    .error {
        background-color: #f8d7da;
        color: #721c24;
    }

    table.mdl-data-table {
        width: 100%;
        border-collapse: collapse;
    }

    table.mdl-data-table th,
    table.mdl-data-table td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table.mdl-data-table th {
        background: #e56717;
        color: white;
    }

    table.mdl-data-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .last-column {
        text-align: center;
    }

    .mob-btn i {
        font-size: 16px;
    }

    .container-padding {
        padding: 30px;
    }
	table.mdl-data-table.table-add td{
		text-align: center;
	}
	textarea{
		border: 0;
		border-bottom: 1px solid #9e9e9e;
		outline: 0;
		margin: 15px 0;
	}
	</style>
</head>

<body>

    <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>

    <!-- header ends  -->

    <!-- card  -->
<div class="container-padding">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <c:if test="${action eq 'add'}">	
                               		<h6>Add Project</h6>
                               	 </c:if>
                               	 <c:if test="${action eq 'edit'}">	
                               		<h6>Edit Project (${projectDetails.project_id })</h6>
                               	 </c:if>
                            </div>
                        </span>
                    </div>
		            <div class="row clearfix" style="margin-bottom:0;">
		                <div class="col s12 m12">
		                	<c:if test="${not empty success }">
						        <div class="center-align m-1 close-message">	
								   ${success}
								</div>
							</c:if>
							<c:if test="${not empty error }">
								<div class="center-align m-1 close-message">
								   ${error}
								</div>
							</c:if>
						</div>
					</div>
                    <!-- form start-->
<div class="container container-no-margin">
    <c:if test="${action eq 'edit'}">				                
        <form action="update-project" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
    </c:if>
    <c:if test="${action eq 'add'}">				                
        <form action="add-project" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
    </c:if> 

    <div class="row">
        <div class="col s6 m4 l4 input-field">
            <input id="project_name" maxlength="100" type="text" class="validate w80 pdr4em" value="${projectDetails.project_name}" name="project_name">
            <label for="project_name">Project Name <span class="required">*</span></label>
            <span id="project_nameError"></span>
            <input type="hidden" name="project_id" id="project_id" value="${projectDetails.project_id}">
        </div>

        <div class="col s6 m4 l4 input-field">
            <p class="searchable_label">Project Status <span class="required">*</span></p>
            <select class="validate-dropdown searchable" name="project_status" id="project_status">
                <option value="">Select</option>
                <option value="Open" <c:if test="${projectDetails.project_status == 'Open'}">selected</c:if>>Open</option>
                <option value="Closed" <c:if test="${projectDetails.project_status == 'Closed'}">selected</c:if>>Closed</option>
            </select> 
            <span id="project_statusError"></span>
        </div>

        <div class="col s6 m4 l4 input-field">
            <p class="searchable_label">Project Type</p>
            <select class="validate-dropdown searchable" name="project_type_id" id="project_type_id">
                <option value="">Select</option>
                <c:forEach var="obj" items="${projectTypes}">
                    <option value="${obj.project_type_id}" <c:if test="${projectDetails.project_type_id == obj.project_type_id}">selected</c:if>>${obj.project_type_name}</option>
                </c:forEach>
            </select>
            <span id="project_type_nameError"></span>
        </div>
    </div>

    <div class="row">
        <div class="col s6 m4 l4 input-field">
            <p class="searchable_label">Railway Zone</p>
            <select class="validate-dropdown searchable" name="railway_zone" id="railway_zone">
                <option value="">Select</option>
                <c:forEach var="z" items="${railwayZones}">
                    <option value="${z.railway_id}" <c:if test="${projectDetails.railway_zone == z.railway_id}">selected</c:if>>${z.railway_name}</option>
                </c:forEach>
            </select>
            <span id="railway_zoneError"></span>
        </div>

        <div class="col s6 m4 l4 input-field">
            <input id="plan_head_number" maxlength="15" type="text" class="validate w80 pdr4em" value="${projectDetails.plan_head_number}" name="plan_head_number">
            <label for="plan_head_number">Plan Head Number <span class="required">*</span></label>
            <span id="plan_head_numberError"></span>
        </div>

        <div class="col s6 m4 l4 input-field">
            <p class="searchable_label">Sanctioned Year</p>
			<select  name="financial_years"  id="financial_years"  class="validate-dropdown searchable">
             					 	<option value="" >Select</option>
                   			 	<c:forEach var="obj" items="${yearList}">
			  				 	<option value="${obj.financial_year }"<c:if test="${projectDetails.financial_year_fk eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
                    			  	</c:forEach>
         					  	</select>
            <span id="sanctioned_yearError"></span>
        </div>
    </div>

    <div class="row">
        <div class="col s6 m4 l4 input-field">
            <input id="sanctioned_amount" type="text" class="validate w80 pdr4em" value="${projectDetails.sanctioned_amount}" name="sanctioned_amount">
            <label for="sanctioned_amount">Sanctioned Amount</label>
            <span id="sanctioned_amountError"></span>
        </div>

        <div class="col s6 m4 l4 input-field">
            <input id="sanctioned_commissioning_date" type="date" class="validate w80 pdr4em" value="${projectDetails.sanctioned_commissioning_date}" name="sanctioned_commissioning_date">
            <label for="sanctioned_commissioning_date">Sanctioned Commissioning Date</label>
        </div>

        <div class="col s6 m4 l4 input-field">
            <p class="searchable_label">Division</p>
            <select class="validate-dropdown searchable" name="division_id" id="division_id">
                <option value="">Select</option>
                <c:forEach var="d" items="${divisions}">
                    <option value="${d.division_id}" <c:if test="${projectDetails.division_id == d.division_id}">selected</c:if>>${d.division_name}</option>
                </c:forEach>
            </select>
            <span id="divisionError"></span>
        </div>
    </div>

    <div class="row">
        <div class="col s6 m4 l4 input-field">
            <p class="searchable_label">Section</p>
            <select class="validate-dropdown searchable" name="section_id" id="section_id">
                <option value="">Select</option>
                <c:forEach var="d" items="${sections}">
                    <option value="${d.section_id}" <c:if test="${projectDetails.section_id == d.section_id}">selected</c:if>>${d.section_name}</option>
                </c:forEach>
            </select>
            <span id="divisionError"></span>
        </div>

        <div class="col s6 m4 l4 input-field">
            <input id="pink_book_item_numbers" type="text" class="validate w80 pdr4em" value="${projectDetails.pb_item_no}" name="pink_book_item_numbers">
            <label for="pink_book_item_numbers">PB Item No</label>
        </div>

        <div class="col s6 m4 l4 input-field">
            <input id="actual_completion_cost" type="text" class="validate w80 pdr4em" value="${projectDetails.actual_completion_cost}" name="actual_completion_cost">
            <label for="actual_completion_cost">Actual Completion Cost</label>
        </div>
    </div>

    <div class="row">
        <div class="col s6 m4 l4 input-field">
            <input id="actual_completion_date" type="date" class="validate w80 pdr4em" value="${projectDetails.actual_completion_date}" name="actual_completion_date">
            <label for="actual_completion_date">Actual Completion Date</label>
        </div>
		<div class="col s6 m4 l4 input-field">
 <input 
            id="proposed_length" 
            type="text" 
            class="validate w80 pdr4em" 
            name="proposed_length" 
            placeholder="Enter proposed length"
            oninput="validateProposedLength(this)"
            value="${projectDetails.proposed_length == null 
                    ? '' 
                    : (projectDetails.proposed_length + 0 == 0 
                        ? '0' 
                        : projectDetails.proposed_length + 0)}"
        >

		    <label for="proposed_length">Proposed Length (km)</label>
		</div>       
    </div>

    <div class="row">
        <div class="col s6 m6 l6 input-field">
            <textarea id="benefits" name="benefits" class="pmis-textarea pdr4em w85" data-length="1000" maxlength="1000">${projectDetails.benefits}</textarea>
            <label for="benefits">Benefits</label>
            <span id="benefitsError"></span>
        </div>

        <div class="col s6 m6 l6 input-field">
            <textarea id="remarks" name="remarks" class="pmis-textarea pdr4em w85" data-length="1000" maxlength="1000">${projectDetails.remarks}</textarea>
            <label for="remarks">Remarks</label>
            <span id="remarksError"></span>
        </div>
    </div>

<div class="row">
    <div class="col l12 m12 s12">
        <div class="row fixed-width">
            <div class="table-inside">
                <h6>Commissioned Length</h6>
                <table class="mdl-data-table update-table mobile_responsible_table">
                    <thead>
                        <tr>
                            <th>S. No.</th>
                            <th>From Chainage (m)</th>
                            <th>To Chainage (m)</th>
                            <th>Completed Length (m)</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="commissionedLengthBody">
                        <c:choose>
                            <c:when test="${not empty projectDetails.projectCommissionedLengthList && fn:length(projectDetails.projectCommissionedLengthList) gt 0}">
                                <c:forEach var="cObj" items="${projectDetails.projectCommissionedLengthList}" varStatus="index">
                                    <tr id="commissionedRow${index.count}">
                                        <td>${index.index + 1}</td>

										<td>
										    <input type="text" name="commission_from_chainage" id="from_chainage_${index.count}" 
										           class="validate w100" 
										           value="<fmt:formatNumber value='${cObj.commission_fromchainage}' maxFractionDigits='10' groupingUsed='false' />"
										           oninput="validateNumber(this)" />
										</td>
										<td>
										    <input type="text" name="commission_to_chainage" id="to_chainage_${index.count}" 
										           class="validate w100" 
										           value="<fmt:formatNumber value='${cObj.commission_tochainage}' maxFractionDigits='10' groupingUsed='false' />"
										           oninput="validateNumber(this)" />
										</td>
										<td>
										    <input type="text" name="commission_completed_length" id="completed_length_${index.count}" 
										           class="validate w100" 
										           value="<fmt:formatNumber value='${cObj.commission_completedlength}' maxFractionDigits='10' groupingUsed='false' />"
										           oninput="validateNumber(this)" />
										</td>

                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <!-- Default row for add mode or empty edit -->
                                <tr id="commissionedRow0">
                                    <td>1</td>
                                    <td><input type="text" name="commission_from_chainage" id="from_chainage_0" class="validate w100" oninput="validateNumber(this)" /></td>
                                    <td><input type="text" name="commission_to_chainage" id="to_chainage_0" class="validate w100" oninput="validateNumber(this)" /></td>
                                    <td><input type="text" name="commission_completed_length" id="completed_length_0" class="validate w100" oninput="validateNumber(this)" /></td>
                                    <td class="mobile_btn_close">
                                        <a onclick="removeCommissionedRow(0);" class="btn red"><i class="fa fa-close"></i></a>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>

                <!-- Add Row Button -->
                <table class="mdl-data-table table-add">
                    <tbody>
                        <tr class="bd-none">
                            <td colspan="5">
                                <a type="button" class="btn waves-effect waves-light bg-m t-c" onclick="addCommissionedRow()">
                                    <i class="fa fa-plus"></i>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <!-- Hidden input to track row count -->
                <input type="hidden" id="commissionedRowNo" name="commissionedRowNo" 
                       value="${not empty projectDetails.projectCommissionedLengthList ? fn:length(projectDetails.projectCommissionedLengthList) : 1}" />
            </div>
        </div>
    </div>
</div>


							
<div class="row">
    <div class="col l12 m12 s12">
        <div class="row fixed-width">
            <div class="table-inside">
                <table class="mdl-data-table update-table mobile_responsible_table">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Estimated Completion Cost</th>
                            <th>Revised Completion Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="completionCostBody">
                        <tr id="costRow0">
                            <td><input type="date" name="completion_dates" class="validate w100" /></td>
                            <td><input type="text" name="estimated_completion_costs" class="validate w100" /></td>
                            <td><input type="date" name="revised_completion_dates" class="validate w100" /></td>
                            <td class="mobile_btn_close">
                                <a onclick="removeCostRow('0');" class="btn red"><i class="fa fa-close"></i></a>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="mdl-data-table table-add">
                    <tbody>
                        <tr class="bd-none">
                            <td colspan="4">
                                <a type="button" class="btn waves-effect waves-light bg-m t-c" onclick="addCostRow()">
                                    <i class="fa fa-plus"></i>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <input type="hidden" id="costRowNo" name="costRowNo" value="1" />
            </div>
        </div>
    </div>
</div>


<div class="row">
    <h5 class="center-align">KMZ file / Chainage-wise Coordinates Upload</h5>
    
    <div class="col s12 m12 center-align exportButton">
        <div class="m-n1">
            <!-- Download Button with Icon -->
            <a href="/wrpmis/1_Chainage_wise_Coordinates_TemplateP46.xlsx" download
               class="btn waves-effect waves-light bg-s t-c"
               title="Download Chainage-wise Coordinates Template">
                <i class="fa fa-arrow-circle-down left"></i>Download
            </a>

            <!-- Upload Button -->
            <a href="javascript:void(0);" onclick="openUploadChainageModal();"
               class="btn waves-effect waves-light bg-s t-c">
                <i class="fa fa-arrow-circle-up left"></i> Upload
            </a>
        </div>
    </div>
</div>

	

							
		
							<c:if test="${action eq 'edit'}">
							<div class="row">
								<div class="col l12 m12 s12 ">
									<div class="row fixed-width">
										<div class="table-inside">
											<table id="project-table" class="mdl-data-table update-table mobile_responsible_table">
												<thead>
													<tr>
													<!-- 	<th  style=" width: 30%;">Financial Year</th>
														<th style="width:30%">Railway</th> 
														<th>WR%</th>
														<th>CR</th>
														<th style=" width: 130px;">PB Item No</th>
														<th style="width:8%">Action</th>
														 -->
														<th>Financial Year</th>
														<th>Railway</th> 												
														<th >PB Item No</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="pinkBookBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectPinkBooks && fn:length(projectDetails.projectPinkBooks) gt 0 }">
															<c:forEach var="pObj" items="${projectDetails.projectPinkBooks }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td data-head="Financial Year" class="input-field">
																		<select  name="financial_years"  id="financial_years${index.count }"  class="select searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${yearList}">
						                    					  				 <option value="${obj.financial_year }" <c:if test="${pObj.financial_year_fk eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</td>
																	<td data-head="Railway" class="input-field">
																		<select  name="railways"  id="railways${index.count }"  class="select searchable">
						                                   					 <option value="" >Select</option>							                                         			  
						                    					  			 <option value="WR" <c:if test="${pObj.railway eq 'WR'}">selected</c:if>>WR</option>
						                    					  			 <option value="CR" <c:if test="${pObj.railway eq 'CR'}">selected</c:if>>CR</option>							                                       
							                               				</select>
																	</td>
																	<td data-head="PB Item No" class="input-field">
																		<input id="pink_book_item_numbers${index.count }" maxlength="15" data-length="15" name="pink_book_item_numbers" type="text" class="validate w85 pdr4em" maxlength="15"  
	                                                        				placeholder="PB Item No" value="${pObj.pb_item_no }" autocomplete="off">
	                                                        		</td>
																	<td class="mobile_btn_close">
																		<a onclick="removeActions('${index.count }');" class="btn red"> 
																			<i class="fa fa-close"></i></a>
																	</td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow0">
																<td data-head="Financial Year" class="input-field">
																	<select  name="financial_years" id="financial_years0" class="select searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${yearList}">
					                    					  				 <option value="${obj.financial_year }">${obj.financial_year}</option>
					                                          			  </c:forEach>
					                               					  </select>
																</td>
																<td data-head="Railway" class="input-field">
																	<select  name="railways"  id="railways0"  class="select searchable">
				                                   					    <option value="">Select</option>							                                         			 
				                    					  				<option value="WR">WR</option>
				                    					  				<option value="CR">CR</option>							                                          		
						                               				</select>
																</td>																	
																<td data-head="PB Item No" class="input-field">
																	<input id="pink_book_item_numbers0" name="pink_book_item_numbers" type="text" class="validate"  maxlength="15" data-length="15"
	                                                        				placeholder="PB Item No" autocomplete="off">
																</td>
																<td class="mobile_btn_close">
																	<a onclick="removeActions('0');" class="btn red"> <i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
											<table class="mdl-data-table table-add">
												<tbody>
							                        <tr class="bd-none">
							                            <td colspan="4">
							                                <a type="button" class="btn waves-effect waves-light bg-m t-c" onclick="addPinkBookRow()">
							                                    <i class="fa fa-plus"></i>
							                                </a>
							                            </td>
							                        </tr>													
												</tbody>
											</table>
											<c:choose>
												<c:when
													test="${not empty (projectDetails.projectPinkBooks) && fn:length(projectDetails.projectPinkBooks) gt 0 }">
													<input type="hidden" id="PBRowNo" name="rowNo"
														value="${fn:length(projectDetails.projectPinkBooks) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="PBRowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							</c:if>

                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr center-align">
                                    <div class=" m-1">
                                     <c:if test="${action eq 'edit'}">
 											<button type="button" onclick="updateProject();" class="btn waves-effect waves-light bg-m w7em">Update</button>    
 									 </c:if>
                                         <c:if test="${action eq 'add'}">
 											<button type="button" onclick="addProject();" class="btn waves-effect waves-light bg-m w7em">Add</button>    
 									 </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr center-align">
                                    <div class=" m-1">
                                        <a href="<%=request.getContextPath()%>/project" class="btn waves-effect waves-light bg-s w7em">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
    


		<div id="upload_template" class="modal">
		<div class="modal-content">
			<div class="center-align p-2 bg-m modal-title headbg">
				<h6>Upload Chainages </h6>
			</div>
			<!-- form start-->
			<div class="container">
				<form action="<%=request.getContextPath()%>/upload-chainages"
					method="post" enctype="multipart/form-data">
					<div class="row no-mar">
						<div class="col s12 m12 input-field center-align">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m">
											<span>Attachment</span> <input type="file" id="ProjectChainagesFile"
												name="ProjectChainagesFile" required="required">
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text">
										</div>
									</div>
								</div>
								<div class="col m2 hide-on-small-only"></div>
							</div>
						</div>
					</div>
					            <input type="hidden" name="project_id_fk" id="project_id_fk" value="${projectDetails.project_id}">
					
					<div class="row no-mar">
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="submit" class="btn waves-effect waves-light bg-m"
									style="width: 100%;">Upload</button>
							</div>
						</div>
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="button" class="btn waves-effect waves-light bg-s"
									style="width: 100%;" onclick="closeUploadLAModal();">Cancel</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>

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


  	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/datepickerDepedency.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
	<script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
 

    <script type="text/javascript">
    $(document).ready(function() {
    	$('.modal').modal();
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
    });
	 $("[data-length]").each(function(i,val){
     	$('#'+val.id).characterCounter();;
     });
	 
	 function validateNumber(input) {
		    // Allow only digits and one decimal point
		    input.value = input.value.replace(/[^0-9.]/g, '');
		    const parts = input.value.split('.');
		    if (parts.length > 2) {
		        input.value = parts[0] + '.' + parts[1];
		    }
		}	 
	 
	 function validateProposedLength(input) {
		    // Allow only digits and a single decimal point
		    input.value = input.value.replace(/[^0-9.]/g, '');

		    // Ensure only one decimal point
		    const parts = input.value.split('.');
		    if (parts.length > 2) {
		        input.value = parts[0] + '.' + parts[1];
		    }
		}	
	 
	 
	 function addCommissionedRow() {
		    const tbody = document.getElementById('commissionedLengthBody');
		    const rowNoInput = document.getElementById('commissionedRowNo');
		    let rowNo = parseInt(rowNoInput.value);

		    const tr = document.createElement('tr');
		    tr.id = `commissionedRow${rowNo}`;
		    tr.innerHTML = `
		        <td class="sno"></td>
		        <td><input type="text" name="commission_from_chainage" id="from_chainage_${rowNo}" class="validate w100" oninput="validateNumber(this)" /></td>
		        <td><input type="text" name="commission_to_chainage" id="to_chainage_${rowNo}" class="validate w100" oninput="validateNumber(this)" /></td>
		        <td><input type="text" name="commission_completed_length" id="completed_length_${rowNo}" class="validate w100" oninput="validateNumber(this)" /></td>
		        <td class="mobile_btn_close">
		            <a onclick="removeCommissionedRow(${rowNo});" class="btn red"><i class="fa fa-close"></i></a>
		        </td>
		    `;
		    tbody.appendChild(tr);
		    rowNoInput.value = rowNo + 1;

		    updateSNo(); // update S. No. for all rows
		}

		function removeCommissionedRow(rowNo) {
		    const tr = document.getElementById(`commissionedRow${rowNo}`);
		    if (tr) tr.remove();
		    updateSNo();
		}

		function updateSNo() {
		    const rows = document.querySelectorAll('#commissionedLengthBody tr');
		    rows.forEach((row, index) => {
		        const snoTd = row.querySelector('.sno');
		        if (snoTd) snoTd.innerText = index + 1;
		    });
		}
	/****************************************************************************************************/
/*     let date_pickers = document.querySelectorAll('.datepicker');
        $.each(date_pickers, function(){
        	var dt = this.value.split(/[^0-9]/);
        	this.value = ""; 
        	var options = {maxDate: new Date(),format: 'dd-mm-yyyy',autoClose:true};
        	if(dt.length > 1){
        		options.setDefaultDate = true,
        		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
        	}
        	M.Datepicker.init(this, options);
        });
         */
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
        
        
     function addImageFileRow(){
			var rowNo = $("#imageRowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="imageRow' + rNo + '">'	
	        +' <td data-head="Data" class="input-field">'
				+'<input id="created_date'+rNo+'" name="created_dates" type="text" class="validate datepicker" placeholder="date">'
				+'<button type="button" id="created_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
	            +'<span id="dateError" class="error-msg" ></span></td>'
	   		   +'<td data-head="Attach Image" class="cell-disp-inb input-field file-field"> '	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Image</span>'	
			   +'<input type="file" id="projectGalleryFiles'+rNo+'" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" onchange="validateImage('+rNo+')">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectGalleryFileNames'+rNo+'" name="projectGalleryFileNames">'	
			   +'</div></td>'
			   +'<td class=" input-field"></td>'
			   +'<td class="mobile_btn_close"><a onclick="removeImage(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			  
			$('#projectImageFilesBody').append(html);
	        $("#imageRowNo").val(rNo); 
	       
	    	 	var id = 'created_date'+rNo
				$('#'+id).datepicker({
					maxDate: new Date(),
		        	format:'dd-mm-yyyy',
		        	onOpen: datePickerSelectAddClass,
		        	autoClose: true		   	    	
		        }).datepicker("setDate", new Date());
				
	    	
	    }
    	
	    function removeImage(rowNo){
	    	$("#imageRow"+rowNo).remove();
	    }
    	
    /**************************************************************************************************************/
    
		function addFileRow(){
			var rowNo = $("#rowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="actionRow' + rNo + '">'
	           +'<td data-head="File Type" class="input-field" > '
	           +'<select name="project_file_types" id="project_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${projectFileTypes}">
	     	      +'<option value="${obj.project_file_type }">${obj.project_file_type}</option>'
			     </c:forEach>
	   		   +'</select></td>'	
	   		   +'<td data-head="Attach File" class="file-field input-field cell-disp-inb">'	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Files</span>'	
			   +'<input type="file" id="projectFiles'+rNo+'" name="projectFiles">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectFileNames'+rNo+'" name="projectFileNames">'	
			   +'</div></td>'
			   +'<td><input type="hidden" id="project_file_ids'+rNo+'" name="project_file_ids"/></td>'
			   +'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			$('#projectFilesBody').append(html);
	        $("#rowNo").val(rNo);          	
	        
	        $('select:not(.searchable)').formSelect();
	        $('.searchable').select2();
	    }
	    
	    function removeActions(rowNo){
	    	$("#actionRow"+rowNo).remove();
	    }


	/****************************************************************************************************/

        $(document).ready(function () {
        	var date='';
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks,#benefits').characterCounter(); 
           	var dateVal=  date;
           	if(dateVal == ''){
           	}
          
        });
        
        function addProject(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	
	  			$('form input[name=financial_years]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=railways]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=pink_book_item_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$("#projectForm").submit();		
    	 	}
    	}
  
        function updateProject(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	   
	  			$('form input[name=financial_years]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=railways]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=pink_book_item_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
    			$("#projectForm").submit();			
    	 	}
    	}
        
        
        function addPinkBookRow() {        	
            var rowNo = $("#PBRowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="actionRow' + rNo + '">'
            +'<td data-head="Financial Year" class="input-field">'
            +'<select name="financial_years" id="financial_years'+rNo+'"  class="select searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${yearList}">
	     	      +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
			     </c:forEach>
	   		   +'</select></div></td>'
	   		   +'<td data-head="Railway" class="input-field">'
	   		   +'<select name="railways"  id="railways'+rNo+'"  class="select searchable">'
   					+'<option >Select</option>'         			
		  				+' <option value="WR">WR</option>'     
		  				+' <option value="CR">CR</option>' 
					+'  </select>	</td>'				
			   +'<td data-head="PB Item No" class="input-field"><input  class="validate w85 pdr4em" id="pink_book_item_numbers'+rNo+'" maxlength="15" data-length="15" name="pink_book_item_numbers" type="text" placeholder="PB Item No""></td>'
			+'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td></tr>';
		
			$('#pinkBookBody').append(html);
            $("#PBRowNo").val(rNo);          	
            $('#pink_book_item_numbers'+rNo).characterCounter();;
            //$('select:not(.searchable)').formSelect();
            $('.searchable').select2();


        }
        
        function removeActions(rowNo){
        	$("#actionRow"+rowNo).remove();
        }
	            
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
   
        var validator =	$('#projectForm').validate({
				 errorClass: "my-error-class",
				   validClass: "my-valid-class",
				 ignore: ":hidden:not(.validate-dropdown)",
		  		    rules: {
		  		 		  "project_name": {
		  			 		required: true
		  			 	  },"project_status":{
		  			 		required: true
		  			 	  },"plan_head_number": {
		  			 		required: true
		  			 	  },"remarks": {
		  			 		required: false
		  			 	  }		
		  		 	},
		  		    messages: {
		  		 		 "project_name": {
		  				 	required: 'This field is required',
		  			 	  },"project_status":{
		  			 		required: ' This field is required'
		  			 	  },"plan_head_number": {
		  			 		required: ' This field is required'
		  			 	  },"remarks": {
		  		 			required: ' This field is required'
		  		 	  	 }
			   		},
			   		errorPlacement:function(error, element){
			   		 	if (element.attr("id") == "project_name" ){
							 document.getElementById("project_nameError").innerHTML="";
					 		 error.appendTo('#project_nameError');
						}else if(element.attr("id") == "project_status" ){
							   document.getElementById("project_statusError").innerHTML="";
						 	   error.appendTo('#project_statusError');
						}else if(element.attr("id") == "plan_head_number" ){
							   document.getElementById("plan_head_numberError").innerHTML="";
						 	   error.appendTo('#plan_head_numberError');
						}else if(element.attr("id") == "remarks" ){
						 		 document.getElementById("remarksError").innerHTML="";
				 				 error.appendTo('#remarksError');
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

    </script>
    
 <script>
function handleFileTypeChange(index) {
    const select = document.getElementById('project_file_types'+index);
    const fileInput = document.getElementById('projectFiles'+index);

    if (select && fileInput) {
        if (select.value.trim().toLowerCase() === 'kmz / chainage-wise coordinates') {
            fileInput.setAttribute("accept", ".kmz");
        } else {
            fileInput.removeAttribute("accept");
        }
    }
}

function addCostRow() {
    var rowNo = parseInt($("#costRowNo").val());
    var newRow = `
        <tr id="costRow${rowNo}">
            <td><input type="date" name="completion_dates" class="validate w100" /></td>
            <td><input type="text" name="estimated_completion_costs" class="validate w100" /></td>
            <td><input type="date" name="revised_completion_dates" class="validate w100" /></td>
            <td class="mobile_btn_close">
                <a onclick="removeCostRow('${rowNo}');" class="btn red"><i class="fa fa-close"></i></a>
            </td>
        </tr>`;
    $("#completionCostBody").append(newRow);
    $("#costRowNo").val(rowNo + 1);
}

function removeCostRow(rowId) {
    $("#costRow" + rowId).remove();
}

function openUploadChainageModal() {
	$("#ProjectChainagesFile").val('');
	$("#upload_template").modal('open');
}  

function closeUploadLAModal() {
	$("#ProjectChainagesFile").val('');
	$("#upload_template").modal('close');
}   	

</script>   
</body>

</html>