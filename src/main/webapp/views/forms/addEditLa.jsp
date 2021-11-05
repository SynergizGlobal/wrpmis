<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Land Acquisition - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Land Acquisition - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    <style>
        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        /* change radio colors  */
       /*  [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }
 */
        .primary-text-bold {
            color: #007A7A !important;
            font-weight: 600
        }

        .input-field .prefix {
            color: #999;
        }

        .input-field .prefix.active {
            color: #007A7A;
        }
		input[type="number"]~.units {
		    position: absolute;
		    right: 15px;
		    top: 15px;
		    border: 0;
		    opacity: 0.7;
		}
        h6.primary-text-bold.center-align {
            margin: 20px auto;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
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
        .error-msg label{color:red!important;}
        
        .pt-5 {
		    padding-top: .3rem !important;
		}
		.wd-wrap{white-space: pre-line;
    				overflow: hidden;
    				text-overflow: ellipsis;
    				display: inline-block;
    				font-size: 11px !important;
    				line-height: normal;}
        @media only screen and (max-width: 768px){
			.mt-sm-n1rem{
				margin-top:-1rem !important;
			}
			.pt-5 {
			    padding-top: 0 !important;
			}
		}
		
		 @media only screen and (max-width: 568px){
			.container{
				width:100%;
			}
		}
		@media(max-width: 575px){
			.row .col{margin: 10px auto;}
			.mn6tbpx{margin: -6px auto;}
		}
		
    </style>
</head>

<body>

    <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                	 <c:if test="${action eq 'edit'}">Update Land Acquisition</c:if>
									 <c:if test="${action eq 'add'}"> Add Land Acquisition</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-land-acquisition" id="landAcquisitionForm" name="landAcquisitionForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                 </c:if>
				     <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-land-acquisition" id="landAcquisitionForm" name="landAcquisitionForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
				     </c:if>
				     <c:if test="${action eq 'add'}">
                        <div class="row">
                                <div class="col s6 m4 l6 input-field offset-m2">
                                    <p class="searchable_label"> Project <span class="required">*</span></p>
                                    <select id="project_id_fk" name="project_id_fk"  class="searchable validate-dropdown"  onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l6 input-field">
                                    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="resetProjectsDropdowns(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${worksList }">
	                                      	   <option value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                     </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>
							</c:if>
 							<c:if test="${action eq 'edit'}">	
                              <div class="row" >
	                       		  <div class="col s6 m4 l6 input-field offset-m2">
										<p class="searchable_label"> Project <span class="required">*</span></p>
	                                    <input type="text" value="${LADetails.project_id_fk} - ${LADetails.project_name}" readonly />
								  </div> 
								  <div class="col s6 m4 l6 input-field"> 
									    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    	<input type="text"  value="${LADetails.work_id_fk} - ${LADetails.work_short_name}" readonly />
                                    	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${LADetails.work_id_fk}"  />
	                              </div>
                              </div> 
                             </c:if>
                             <c:if test="${action eq 'add'}">
	                            <div class="row" >
	                                <div class="col s12 m2 l2 input-field offset-m2 mt-sm-n1rem">
	                                    <!-- <select class="searchable">
	                                        <option value="0" selected>Select</option>
	                                        <option value="1">Agency 1</option>
	                                        <option value="2">Agency 2</option>
	                                        <option value="3">Agency 3</option>
	                                    </select> -->	                                    
	                                    <label for="la_id" class="mn6tbpx"> Land Acquisition ID <span class="required">*</span>:</label>
	                                </div>
	                                 <div class="col s12 m6 l10 input-field">
	                                  <input id="la_id" name="la_id" type="text" class="validate mt-10" value="${LADetails.la_id }" >
	                                  <span id="la_idError" class="error-msg" ></span>
	                                 </div>
	                            </div>
							</c:if>
							<c:if test="${action eq 'edit'}">
	                            <div class="row" >	                                 
	                                <div class="col s12 m2 l2 input-field offset-m2 mt-sm-n1rem">
	                                    <!-- <select class="searchable">
	                                        <option value="0" selected>Select</option>
	                                        <option value="1">Agency 1</option>
	                                        <option value="2">Agency 2</option>
	                                        <option value="3">Agency 3</option>
	                                    </select> -->
	                                    
	                                    <label for="la_id"> Land Acquisition ID <span class="required">*</span>:</label>
	                                </div>
	                                 <div class="col s12 m6 l10 input-field">
	                                  <input id="la_id" name="la_id" type="text" class="validate mt-10" value="${LADetails.la_id }" readonly>
	                                 </div>	                                 
	                            </div>
							</c:if>
							
                            <div class="row">                                 
                                <div class="col s6 m4 l6 input-field offset-m2">
                                    <input id="survey_number" name="survey_number" type="text" class="validate mt-10" value="${LADetails.survey_number }">
                                    <label for="survey_number">Survey Number </label>
									<span id="survey_numberError" class="error-msg" ></span>                                    
                                </div>
                                <div class="col s6 m4 l6 input-field ">
                                    <input id="village_id" name="village_id" type="text" class="validate mt-10" value="${LADetails.village_id }">
                                    <label for="village_id">Village ID </label>
                                    <span id="village_idError" class="error-msg" ></span>
                                </div>                                 
                            </div>
							<c:if test="${action eq 'add'}">
                            <div class="row">                                 
                                <div class="col s6 m4 l6 input-field offset-m2">
                                    <p class="searchable_label"> Type of Land <span class="required">*</span></p>
                                    <select id="type_of_land" class="searchable validate-dropdown" name="type_of_land" onchange="getSubCategorysList();">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${landsList }">
	                                      	   <option value= "${obj.type_of_land}" <c:if test="${LADetails.type_of_land eq obj.type_of_land}">selected</c:if>>${obj.type_of_land}</option>
	                                     </c:forEach>
                                    </select>
                                    <span id="type_of_landError" class="error-msg" ></span>                                    
                                </div>
                                <div class="col s6 m4 l6 input-field">
                                    <p class="searchable_label fs-sm-67rem"> Sub Category of Land <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="sub_category_of_land" name="id" onchange="getLandsList();">
                                        <option value="" selected>Select</option>
                                       <c:forEach var="obj" items="${subCategorysList }">
	                                      	   <option value= "${obj.id}" <c:if test="${LADetails.sub_category_of_land eq obj.sub_category_of_land}">selected</c:if>>${obj.sub_category_of_land}</option>
	                                     </c:forEach>
                                    </select>
                                    <span id="sub_category_of_landError" class="error-msg" ></span>
                                </div>                                 
                            </div>
							</c:if>
							<c:if test="${action eq 'edit'}">
								<div class="row">	                                 
	                                <div class="col s6 m4 l6 input-field offset-m2">
	                                    <input type="text" id="type_of_land" name="type_of_land"  value="${LADetails.type_of_land }" readonly />
	                                    <label for="type_of_land"> Type of Land <span class="required">*</span></label>	                                    
	                                </div>
	                                <div class="col s6 m4 l6 input-field">
	                                    <input type="text"  id="sub_category_of_land" value="${LADetails.sub_category_of_land }" readonly/>
	                                    <label for="sub_category_of_land" class="fs-sm-8rem"> Sub Category of Land <span class="required">*</span></label>
	                                </div>	                                 
	                            </div>
							
							</c:if>
                            <div class="row">                                 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="village" name="village" type="text" class="validate" value="${LADetails.village }">
                                    <label for="village"> Village </label>
                                    <span id="villageError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input id="taluka" name="taluka" type="text" class="validate" value="${LADetails.taluka }">
                                    <label for="taluka"> Taluka </label>
                                    <span id="talukaError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="dy_slr" name="dy_slr" type="text" class="validate" value="${LADetails.dy_slr }">
                                    <label for="dy_slr">Dy SLR </label>
                                    <span id="dy_slrError" class="error-msg" ></span>
                                </div>                                
                            
                            <div class="col s6 m4 l4 input-field">
                                    <input id="sdo" name="sdo" type="text" class="validate" value="${LADetails.sdo }">
                                    <label for="sdo"> SDO</label>
                                    <span id="sdoError" class="error-msg" ></span>
                                </div>                                 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="collector" name="collector" type="text" class="validate" value="${LADetails.collector }">
                                    <label for="collector">Collector </label>
                                    <span id="collectorError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l4 input-field ">
                                    <input id="area_of_plot" name="area_of_plot" type="text" class="validate" value="${LADetails.area_of_plot }">
                                    <label for="area_of_plot">Area of Plot </label>
                                </div>                                                         
                            </div>

                            <div class="row">      
                               <div class="col s12 m4 l4 input-field offset-m2">
                                    <input id="submission_date" name="proposal_submission_date_to_collector" type="text" value="${LADetails.proposal_submission_date_to_collector }"
                                        class="validate datepicker">
                                    <label for="submission_date">Proposal submission Date to collector</label>
                                    <button type="button" id="submission_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="submission_dateError" class="error-msg" ></span>
                                </div>        
                                <div class="col s12 m4 l4 input-field amount-dropdown">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="jm_fee_amount" name="jm_fee_amount" type="number" class="validate" value="${LADetails.jm_fee_amount }" min="0.01" step="0.01">
                                    <label for="jm_fee_amount"> JM Fee Amount </label>
                                    <span id="jm_fee_amountError" class="error-msg"></span>
                                	<span id="jm_fee_amount_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="jm_fee_amount_units" name="jm_fee_amount_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                </div>   
                            <%--     <div class="col s4 m1 l1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="jm_fee_amount_units" name="jm_fee_amount_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="jm_fee_amount_unitsError" class="error-msg" ></span>
                               	</div> --%>
                               	<!-- <div class="col m2 hide-on-small-only"></div> -->
                               	<div class="col s6 m4 l4 input-field">
                                    <input id="chainage_from" name="chainage_from" type="text" class="validate" value="${LADetails.chainage_from }">
                                    <label for="chainage_from">Chainage From</label>
                                </div>                               
                             
                            	<div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="chainage_to" name="chainage_to" type="text" class="validate" value="${LADetails.chainage_to }">
                                    <label for="chainage_to"> Chainage To </label>
                                </div>                                
                                <div class="col s12 m4 l4 input-field ">
                                    <input id="jm_fee_letter_received_date" name="jm_fee_letter_received_date" type="text" value="${LADetails.jm_fee_letter_received_date }"
                                        class="validate datepicker">
                                    <label for="jm_fee_letter_received_date"> JM Fee Letter received Date </label>
                                    <button type="button" id="jm_fee_letter_received_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="jm_fee_paid_date" name="jm_fee_paid_date" type="text" value="${LADetails.jm_fee_paid_date }"
                                        class="validate datepicker">
                                    <label for="jm_fee_paid_date">JM Fee Paid Date </label>
                                    <button type="button" id="jm_fee_paid_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>                                 
                                                    
                                <div class="col s6 m4 l4 input-field ">
                                    <input id="jm_start_date" type="text" name="jm_start_date" class="validate datepicker" value="${LADetails.jm_start_date }">
                                    <label for="jm_start_date">JM Start Date </label>
                                    <button type="button" id="jm_start_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="jm_completion_date" name="jm_completion_date" type="text" class="validate datepicker" value="${LADetails.jm_completion_date }">
                                    <label for="jm_completion_date" class="fs-sm-8rem"> JM Completion Date</label>
                                    <button type="button" id="jm_completion_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>  
                                 <div class="col s6 m4 l4 input-field ">
                                    <input id="jm_sheet_date_to_sdo" name="jm_sheet_date_to_sdo" type="text" class="validate datepicker" value="${LADetails.jm_sheet_date_to_sdo }">
                                    <label for="jm_sheet_date_to_sdo" class="fs-sm-8rem">JM Sheet Date to SDO </label>
                                    <button type="button" id="jm_sheet_date_to_sdo_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>                               
                            </div>
                            
                            <div class="row">             
                                <div class="col s12 m8 l12 center-align offset-m2">
                                    <div class="row">
                                        <div class="col s4 m4 l6 input-field ">
                                            <p>JM Approval</p>
                                        </div>
                                        <div class="col s8 m8 l6 input-field">
                                            <p class="">
                                                <label>
                                                    <input class="with-gap" name="jm_approval" type="radio"
                                                        value="Done" <c:if test="${LADetails.jm_approval eq 'Done'}">checked</c:if> />
                                                    <span>Accept</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="jm_approval" type="radio"
                                                        value="" />
                                                    <span>Reject</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>                                 
                            </div>

                            <div class="row">                                 
                                <div class="col s12 m8 l12 input-field offset-m2">
                                    <textarea id="jm_remarks" name="jm_remarks" class="pmis-textarea"
                                        data-length="500">${LADetails.jm_remarks }</textarea>
                                    <label for="jm_remarks"> JM Remarks</label>
                                </div>                                 
                            </div>

                            <!-- if selected govt this div shown  -->
                            <div id="govt_div" style="display: none; ">
                                <h6 class="center-align primary-text-bold">Government Land Details </h6>                               
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="govt_area_to_be_acquired" name="area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.area_to_be_acquired }"
                                            class="validate">
                                        <label for="govt_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="govt_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>      
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="govt_area_acquired" name="area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.area_acquired }"
                                            class="validate">
                                        <label for="govt_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                        <span id="govt_area_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label"> Proposal Submission Status </p>
                                        <select class="searchable" id="proposal_submission_status_fk"
                                            name="proposal_submission_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.proposal_submission_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                        
                                </div>
                                
                                <div class="row">
                                	<div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="proposal_submission" type="text" value="${LADetails.proposal_submission }" 
                                            name="proposal_submission" class="validate datepicker">
                                        <label for="proposal_submission">Proposal submission</label>
                                        <button type="button" id="proposal_submission_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                    <div class="col s6 m4 l4 input-field ">
                                        <input id="letter_for_payment" type="text" class="validate datepicker" value="${LADetails.letter_for_payment }"
                                            name="letter_for_payment">
                                        <label for="letter_for_payment">Letter for Payment</label>
                                        <button type="button" id="letter_for_payment_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field amount-dropdown offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="amount_demanded" name="amount_demanded" type="number" value="${LADetails.amount_demanded }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="amount_demanded" class="fs-sm-67rem">Amount Demanded</label>
                                        <span id="amount_demandedError" class="error-msg"></span>
	                                	<span id="amount_demanded_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="amount_demanded_units" name="amount_demanded_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }"<c:if test="${LADetails.amount_demanded_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="amount_demanded_units" name="amount_demanded_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }"<c:if test="${LADetails.amount_demanded_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="amount_demanded_unitsError" class="error-msg" ></span>
                               		</div> --%>                                      
                                </div>
                                <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <p class="searchable_label"> LFP Status </p>
                                        <select class="searchable" id="lfp_status_fk" name="lfp_status_fk">
                                            <option value="" selected>Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.lfp_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s6 m4 l4 input-field ">
                                        <input id="approval_for_payment" type="text" value="${LADetails.approval_for_payment }"
                                            name="approval_for_payment" class="validate datepicker mt-10">
                                        <label for="approval_for_payment" class="fs-sm-8rem">Approval for Payment </label>
                                        <button type="button" id="approval_for_payment_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="payment_date" name="payment_date" type="text" value="${LADetails.payment_date }"
                                            class="validate datepicker">
                                        <label for="payment_date">Payment date </label>
                                        <button type="button" id="payment_date_icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                
                                <div class="row">
                                	<div class="col s12 m4 l4 input-field amount-dropdown offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="govt_amount_paid" name="amount_paid" type="number" value="${LADetails.amount_paid }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="govt_amount_paid"> Amount Paid</label>
                                        <span id="govt_amount_paidError" class="error-msg"></span>
	                                	<span id="amount_paid_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="amount_paid_units" name="amount_paid_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.amount_paid_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>   
                                    <%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="amount_paid_units" name="amount_paid_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.amount_paid_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="amount_paid_unitsError" class="error-msg" ></span>
                               		</div>       --%>                               
                                    <div class="col s6 m4 l4 input-field ">
                                        <p class="searchable_label"> Payment Status </p>
                                        <select class="searchable" id="payment_status_fk" name="payment_status_fk">
                                            <option value="" selected>Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.payment_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="possession_status_fk"
                                            name="possession_status_fk">
                                            <option value="" selected>Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>       
                                </div>
                                <div class="row">                                    
                                   <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="possession_date" name="possession_date" type="text" value="${LADetails.possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="possession_date">Possession Date </label>
                                        <button type="button" id="possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s6 m4 l4 input-field ">
                                        <input id="valuation_date" type="text" value="${LADetails.valuation_date }"
                                            name="valuation_date" class="validate datepicker mt-10">
                                        <label for="valuation_date">Valuation Date</label>
                                        <button type="button" id="valuation_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                       <input id="govt_special_feature" name="special_feature" type="text" value="${LADetails.special_feature }"
                                           class="validate mt-10">
                                       <label for="govt_special_feature"> Special Feature</label>
                                   </div>                                      
                                </div>
                              
                            </div>

        <!-- if selected forest this div shown  -->
                            
                            <div id="forest_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Forest Land Details </h6>
                               <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_area_to_be_acquired" name="forest_area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.forest_area_to_be_acquired }"
                                            class="validate">
                                        <label for="forest_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="forest_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                     <div class="col s6 m4 l4 input-field">
                                        <input id="forest_area_acquired" name="forest_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.forest_area_acquired }"
                                            class="validate">
                                        <label for="forest_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                        <span id="forest_area_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_online_submission" name="forest_online_submission" type="text" value="${LADetails.forest_online_submission }"
                                            class="validate datepicker">
                                        <label for="forest_online_submission"> On line Submission </label>
                                        <button type="button" id="forest_online_submission_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                 
                                <div class="col s6 m4 l4 input-field">
                                        <input id="forest_submission_dycfo" name="forest_submission_date_to_dycfo" type="text" value="${LADetails.forest_submission_date_to_dycfo }"
                                            class="validate datepicker">
                                        <label for="forest_submission_dycfo" class="fs-sm-67rem">Submission Date to DyCFO </label>
                                        <button type="button" id="forest_submission_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="forest_submission_ccf" name="forest_submission_date_to_ccf_thane" type="text" value="${LADetails.forest_submission_date_to_ccf_thane }"
                                            class="validate datepicker">
                                        <label for="forest_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="forest_submission_ccf_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="forest_submission_nodal_officer"
                                            name="forest_submission_date_to_nodal_officer" type="text" value="${LADetails.forest_submission_date_to_nodal_officer }"
                                            class="validate datepicker">
                                        <label for="forest_submission_nodal_officer"> Submission Date to Nodal
                                            Officer</label>
                                        <button type="button" id="forest_submission_nodal_officer_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="forest_submission_revenue_sec" name="forest_submission_date_to_revenue_secretary_mantralaya" value="${LADetails.forest_submission_date_to_revenue_secretary_mantralaya }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_submission_revenue_sec" class="fs-sm-8rem wd-wrap"> Submission Date to Revenue Secretary
                                            Mantralaya </label>
                                        <button type="button" id="forest_submission_revenue_sec_icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="forest_submission_regional_office"
                                            name="forest_submission_date_to_regional_office_nagpur" type="text" value="${LADetails.forest_submission_date_to_regional_office_nagpur }"
                                            class="validate datepicker">
                                        <label for="forest_submission_regional_office" class="fs-sm-8rem wd-wrap"> Submission Date to Regional
                                            Office Nagpur
                                        </label>
                                        <button type="button" id="forest_submission_regional_office_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="forest_approval_regional_office"
                                            name="forest_date_of_approval_by_regional_office_nagpur" type="text" value="${LADetails.forest_date_of_approval_by_regional_office_nagpur }"
                                            class="validate datepicker">
                                        <label for="forest_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="forest_approval_regional_office_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    
                                                                        
                                </div>
                                <div class="row">
                                	<div class="col s12 m4 l4 offset-m2 input-field">
                                        <input id="forest_valuation_dycfo" name="forest_valuation_by_dycfo" type="text" value="${LADetails.forest_valuation_by_dycfo }"
                                            class="validate datepicker">
                                        <label for="forest_valuation_dycfo">Valuation by DyCFO </label>
                                        <button type="button" id="forest_valuation_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                      
                                    <div class="col s12 m4 l4 input-field amount-dropdown">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="forest_demanded_amount" name="forest_demanded_amount" type="number" value="${LADetails.forest_demanded_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="forest_demanded_amount" class="fs-sm-8rem">Demanded Amount </label>
                                        <span id="forest_demanded_amountError" class="error-msg"></span>
	                                	<span id="forest_demanded_amount_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="demanded_amount_units_forest" name="demanded_amount_units_forest">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <%--  <div class="col s4 m1 l1 input-field pt-5">
	                               	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="demanded_amount_units_forest" name="demanded_amount_units_forest">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_demanded_amount_unitsError" class="error-msg" ></span>
                               		</div>    --%>
                                    <div class="col s12 m4 l4 input-field amount-dropdown">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="forest_payment_amount" name="forest_payment_amount" type="number" value="${LADetails.forest_payment_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="forest_payment_amount" class="fs-sm-8rem"> Payment Amount </label>
                                        <span id="forest_payment_amountError" class="error-msg"></span>
	                                	<span id="forest_payment_amount_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="payment_amount_units_forest" name="payment_amount_units_forest">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>  
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units_forest" name="payment_amount_units_forest">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_payment_amount_unitsError" class="error-msg" ></span>
                               		</div>   --%>                                    
                                </div>
                                <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_payment_approval_date" name="forest_approval_for_payment" value="${LADetails.forest_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_payment_approval_date" class="fs-sm-8rem"> Approval for Payment</label>
                                        <button type="button" id="forest_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="forest_payment_date" name="forest_payment_date" type="text" value="${LADetails.forest_payment_date }"
                                            class="validate datepicker">
                                        <label for="forest_payment_date"> Payment Date </label>
                                        <button type="button" id="forest_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2"> 
                                        <input id="forest_possession_date" name="forest_possession_date" type="text" value="${LADetails.forest_possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="forest_possession_date"> Possession Date</label>
                                        <button type="button" id="forest_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                       
                                <div class="row">  
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="forest_possession_status_fk"
                                            name="forest_possession_status_fk">
                                            <option value="" selected>Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                   
                                    <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label">Payment Status </p>
                                        <select class="searchable" id="forest_payment_status_fk"
                                            name="forest_payment_status_fk">
                                            <option value="" selected>Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_payment_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_special_feature" name="forest_special_feature" type="text" value="${LADetails.forest_special_feature }"
                                            class="validate mt-10">
                                        <label for="forest_special_feature"> Special Feature </label>
                                    </div>
                                     
                                </div>
                                <div class="row">                                     
                                  	<div class="col s12 m4 l8 input-field offset-m2">
                                        <input id="forest_attachment_no" name="forest_attachment_No" type="text" value="${LADetails.forest_attachment_No }"
                                            class="validate">
                                        <label for="forest_attachment_no">Attachment Number </label>
                                    </div>
                                     
                                 </div>
                            </div>

        <!-- if selected private this div shown  -->
                            
                            <div id="private_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Private Land Details </h6>   
                                 <div class="row">
                                                                 
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_area_to_be_acquired" name="private_area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.private_area_to_be_acquired }"
                                            class="validate">
                                        <label for="private_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="private_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                     <div class="col s12 m4 l4 input-field">
                                        <input id="private_area_acquired" name="private_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.private_area_acquired }"
                                            class="validate">
                                        <label for="private_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                        <span id="private_area_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_name_of_owner" name="name_of_the_owner" type="text" value="${LADetails.name_of_the_owner }"
                                            class="validate">
                                        <label for="private_name_of_owner">Name of Owner</label>
                                    </div>                                   
                                     
                                </div>
                                <div class="row">                                         
                                 	                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_basic_rate" name="basic_rate" type="number" min="0.01" step="0.01" value="${LADetails.basic_rate }"
                                            class="validate">
                                        <label for="private_basic_rate">Basic Rate </label>
                                        <span id="private_basic_rateError" class="error-msg"></span>     
	                                	<span id="basic_rate_unitsError" class="error-msg right" ></span>                                 
                                        <select class="validate-dropdown" id="basic_rate_units" name="basic_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.basic_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                 	<%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="basic_rate_units" name="basic_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.basic_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="basic_rate_unitsError" class="error-msg" ></span>
                               		</div>  --%>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_attachment_no" name="private_attachment_no" type="text" value="${LADetails.private_attachment_no }"
                                            class="validate">
                                        <label for="private_attachment_no" class="fs-sm-8rem">Attachment Number </label>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_agri_trees" name="agriculture_tree_nos" type="text" value="${LADetails.agriculture_tree_nos }"
                                            class="validate">
                                        <label for="private_agri_trees"> Agriculture tree nos</label>
                                       
                                    </div>                                     
                                </div>                              
                                <div class="row">                                     
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    
                                         
                                </div>
                                <div class="row">                                     
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_agri_tree_rate" name="agriculture_tree_rate" type="number" min="0.01" step="0.01" value="${LADetails.agriculture_tree_rate }"
                                            class="validate">
                                        <label for="private_agri_tree_rate" class="fs-sm-8rem"> Agriculture tree rate </label>
										<span id="private_agri_tree_rateError" class="error-msg"></span>
	                                	<span id="agriculture_tree_rate_unitsError" class="error-msg right" ></span>
										<select class="validate-dropdown" id="agriculture_tree_rate_units" name="agriculture_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.agriculture_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>  
                                    <%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="agriculture_tree_rate_units" name="agriculture_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.agriculture_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="agriculture_tree_rate_unitsError" class="error-msg" ></span>
                               		</div> --%>
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="private_forest_trees" name="forest_tree_nos" type="number" value="${LADetails.forest_tree_nos }"
                                            class="validate">
                                        <label for="private_forest_trees">Forest tree nos </label>
                                    </div>
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_forest_tree_rate" name="forest_tree_rate" type="number" min="0.01" step="0.01" value="${LADetails.forest_tree_rate }"
                                            class="validate">
                                        <label for="private_forest_tree_rate" class="fs-sm-8rem"> Forest tree rate </label>
                                         <span id="private_forest_tree_rateError" class="error-msg"></span>
	                                	<span id="forest_tree_rate_unitsError" class="error-msg right" ></span>
                                         <select class="validate-dropdown" id="forest_tree_rate_units" name="forest_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.forest_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                  <%--   <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="forest_tree_rate_units" name="forest_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.forest_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_tree_rate_unitsError" class="error-msg" ></span>
                               		</div> --%>  
                                </div>                               
                                <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_owner_consent" name="consent_from_owner" type="text" value="${LADetails.consent_from_owner }"
                                            class="validate datepicker">
                                        <label for="private_owner_consent">Consent from Owner </label>
                                        <button type="button" id="private_owner_consent_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field ">
                                        <input id="private_leagal_search_report" name="legal_search_report" value="${LADetails.legal_search_report }"
                                            type="text" class="validate datepicker">
                                        <label for="private_leagal_search_report"> Legal Search Report</label>
                                        <button type="button" id="private_leagal_search_rport_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_registartion_date" name="date_of_registration" value="${LADetails.date_of_registration }"
                                            type="text" class="validate datepicker">
                                        <label for="private_registartion_date">Date of Registration </label>
                                        <button type="button" id="private_registartion_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row"> 
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_possession_date" name="date_of_possession" type="text" value="${LADetails.date_of_possession }"
                                            class="validate datepicker">
                                        <label for="private_possession_date">Date of Possession</label>
                                        <button type="button" id="private_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label">Possession Status</p>
                                        <select class="searchable" id="private_possession_status"
                                            name="private_possession_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s7 m4 l4 input-field">
                                        <input id="private_special_feature" name="private_special_feature" type="text" value="${LADetails.private_special_feature }"
                                            class="validate mt-10">
                                        <label for="private_special_feature"> Special Feature </label>
                                    </div>                                     
                                </div>                               
                                <div class="row">                                   
                                                                         
                                </div>
                                <div class="row">
                                	<div class="col s12 m4 l4 input-field offset-m2"> 
                                        <input id="private_forest_tree_survey" name="forest_tree_survey" value="${LADetails.forest_tree_survey }"
                                            type="text" class="validate datepicker">
                                        <label for="private_forest_tree_survey"> Forest Tree Survey </label>
                                        <button type="button" id="private_forest_tree_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="private_forest_tree_valuation" name="forest_tree_valuation" value="${LADetails.forest_tree_valuation }"
                                            type="text" class="validate datepicker mt-10">
                                        <label for="private_forest_tree_valuation"> Forest Tree Valuation </label>
                                        <button type="button" id="private_forest_tree_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <p class="searchable_label fs-sm-67rem"> Forest Tree Valuation Status </p>
                                        <select class="searchable" id="private_forest_tree_valuation_status"
                                            name="forest_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_horiculture_tree_survey"
                                            name="horticulture_tree_survey" type="text" value="${LADetails.horticulture_tree_survey }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_survey" class="fs-sm-8rem"> Horticulture Tree Survey </label>
                                        <button type="button" id="private_horiculture_tree_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_horiculture_tree_valuation"
                                            name="horticulture_tree_valuation" type="text" value="${LADetails.horticulture_tree_valuation }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_valuation"> Horticulture Tree Valuation
                                        </label>
                                        <button type="button" id="private_horiculture_tree_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_structure_survey" name="structure_survey" type="text" value="${LADetails.structure_survey }"
                                            class="validate datepicker">
                                        <label for="private_structure_survey"> Structure Survey </label>
                                        <button type="button" id="private_structure_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row"> 
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_structure_valuation" name="structure_valuation" value="${LADetails.structure_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_structure_valuation"> Structure Valuation </label>
                                        <button type="button" id="private_structure_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="private_borewell_survey" name="borewell_survey" type="text" value="${LADetails.borewell_survey }"
                                            class="validate datepicker">
                                        <label for="private_borewell_survey"> Borewell Survey </label>
                                        <button type="button" id="private_borewell_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_borewell_valuation" name="borewell_valuation" value="${LADetails.borewell_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_borewell_valuation"> Borewell Valuation </label>
                                        <button type="button" id="private_borewell_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Horticulture Tree Valuation Status </p>
                                        <select class="searchable" id="private_horticulture_tree_valuation_status"
                                            name="horticulture_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.horticulture_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <p class="searchable_label"> Structure Valuation Status </p>
                                        <select class="searchable" id="private_structure_valuation_status"
                                            name="structure_valuation_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.structure_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Borewell Valuation Status </p>
                                        <select class="searchable" id="private_borewell_valuation_status"
                                            name="borewell_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.borewell_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                     
                                </div>
                              
                                <div class="row"> 
                                	<div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label"> RFP to ADTP status </p>
                                        <select class="searchable" id="private_rfp_to_adtp_status"
                                            name="rfp_to_adtp_status_fk">
                                            <option value="" >Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.rfp_to_adtp_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                    
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="private_rfp_adtp" name="date_of_rfp_to_adtp" type="text" value="${LADetails.date_of_rfp_to_adtp }"
                                            class="validate datepicker">
                                        <label for="private_rfp_adtp">Date of RFP to ADTP </label>
                                        <button type="button" id="private_rfp_adtp_icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_rate_fixation_date" name="date_of_rate_fixation_of_land" value="${LADetails.date_of_rate_fixation_of_land }"
                                            type="text" class="validate datepicker">
                                        <label for="private_rate_fixation_date"> Date of Rate Fixation of Land </label>
                                        <button type="button" id="private_rate_fixation_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_sdo_payment_demand_date"
                                            name="sdo_demand_for_payment" type="text" value="${LADetails.sdo_demand_for_payment }"
                                            class="validate datepicker">
                                        <label for="private_sdo_payment_demand_date">SDO demand for payment </label>
                                        <button type="button" id="private_sdo_payment_demand_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_payment_approval_date" name="date_of_approval_for_payment" value="${LADetails.date_of_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="private_payment_approval_date"> Date of Approval for Payment
                                        </label>
                                        <button type="button" id="private_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_payment_amount" name="payment_amount" type="number" value="${LADetails.payment_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="private_payment_amount" class="fs-sm-8rem">Payment Amount </label>
                                        <span id="private_payment_amountError" class="error-msg"></span>
	                                	<span id="payment_amount_unitsError" class="error-msg right" ></span>                                        
                                        <select class="validate-dropdown" id="payment_amount_units" name="payment_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units" name="payment_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="payment_amount_unitsError" class="error-msg" ></span>
                               		</div> --%>                                     
                                </div>
                                 
                                <div class="row">
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_payment_date" name="private_payment_date" type="text" value="${LADetails.private_payment_date }"
                                            class="validate datepicker">
                                        <label for="private_payment_date"> Payment Date </label>
                                        <button type="button" id="private_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                     <div class="col s6 m4 l4 input-field ">
                                        <input id="hundred_percent_Solatium" name="hundred_percent_Solatium" type="number" value="${LADetails.hundred_percent_Solatium }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="hundred_percent_Solatium">100 Percent Solatium </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="extra_25_percent" name="extra_25_percent" type="number" value="${LADetails.extra_25_percent }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="extra_25_percent">Extra 25 Percent </label>
                                    </div>                                     
                                </div>
                                 <div class="row">                                     
                                     <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="total_rate_divide_m2" name="total_rate_divide_m2" type="number" value="${LADetails.total_rate_divide_m2 }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="total_rate_divide_m2">Total Rate Divide M2 </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="land_compensation" name="land_compensation" type="number" value="${LADetails.land_compensation }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="land_compensation">Land Compensation </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="agriculture_tree_compensation" name="agriculture_tree_compensation" type="number" value="${LADetails.agriculture_tree_compensation }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="agriculture_tree_compensation" class="fs-sm-67rem">Agriculture Tree Compensation </label>
                                    </div>                                     
                                </div>
                                   
                                <div class="row"> 
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_tree_compensation" name="forest_tree_compensation" type="number" value="${LADetails.forest_tree_compensation }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="forest_tree_compensation" class="fs-sm-67rem">Forest Tree Compensation </label>
                                    </div>                                    
                                     <div class="col s6 m4 l4 input-field ">
                                        <input id="structure_compensation" name="structure_compensation" type="number" value="${LADetails.structure_compensation }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="structure_compensation" class="fs-sm-8rem">Structure Compensation </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="borewell_compensation" name="borewell_compensation" type="number" value="${LADetails.borewell_compensation }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="borewell_compensation" class="fs-sm-8rem">Borewell Compensation </label>
                                    </div>
                                     
                                </div>
                                  <div class="row">
                                     
                                     <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="total_compensation" name="total_compensation" type="number" value="${LADetails.total_compensation }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="total_compensation">Total Compensation </label>
                                    </div>                                   
                                     
                                </div>
                                
                            </div>
             <!-- //*********************************************************   -->             
 							<div id="railway_div" style="display: none; ">
                                <h6 class="center-align primary-text-bold">Railway Land Details </h6>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="railway_area_to_be_acquired" name="railway_area_to_be_acquired" value="${LADetails.railway_area_to_be_acquired }"
                                            type="number" min="0.0001" step="0.0001" class="validate">
                                        <label for="railway_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="railway_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="railway_area_acquired" name="railway_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.railway_area_acquired }"
                                            class="validate">
                                        <label for="railway_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                        <span id="railway_area_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="railway_online_submission" type="text" value="${LADetails.railway_online_submission }"
                                            name="railway_online_submission" class="validate datepicker">
                                        <label for="railway_online_submission">Online Submission</label>
                                        <button type="button" id="railway_online_submission_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                
                                	<div class="col s6 m4 l4 input-field ">
                                        <input id="railway_submission_dycfo" type="text" name="railway_submission_date_to_DyCFO" value="${LADetails.railway_submission_date_to_DyCFO }"
                                            class="validate datepicker">
                                        <label for="railway_submission_dycfo" class="fs-sm-8rem">Submission to DyCFO</label>
                                        <button type="button" id="railway_submission_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="railway_submission_ccf" name="railway_submission_date_to_CCF_Thane" type="text" value="${LADetails.railway_submission_date_to_CCF_Thane }"
                                            class="validate datepicker">
                                        <label for="railway_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="railway_submission_ccf_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="railway_submission_nodal_officer"
                                            name="railway_submission_date_to_nodal_officer_CCF_Nagpur" type="text" value="${LADetails.railway_submission_date_to_nodal_officer_CCF_Nagpur }"
                                            class="validate datepicker">
                                        <label for="railway_submission_nodal_officer" class="fs-sm-8rem wd-wrap"> Submission Date to Nodal
                                            Officer / CCF Nagpur </label>
                                        <button type="button" id="railway_submission_nodal_officer_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="railway_submission_revenue_sec" name="railway_submission_date_to_revenue_secretary_mantralaya" value="${LADetails.railway_submission_date_to_revenue_secretary_mantralaya }"
                                            type="text" class="validate datepicker">
                                        <label for="railway_submission_revenue_sec" class="fs-sm-8rem wd-wrap"> Submission Date to Revenue Secretary
                                            Mantralaya </label>
                                        <button type="button" id="railway_submission_revenue_sec_icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="railway_submission_regional_office" value="${LADetails.railway_submission_date_to_regional_office_nagpur }"
                                            name="railway_submission_date_to_regional_office_nagpur" type="text"
                                            class="validate datepicker">
                                        <label for="railway_submission_regional_office" class="fs-sm-8rem wd-wrap"> Submission Date to Regional
                                            Office Nagpur
                                        </label>
                                        <button type="button" id="railway_submission_regional_office_icon" 
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="railway_approval_regional_office"
                                            name="railway_date_of_approval_by_Rregional_Office_agpur" type="text" value="${LADetails.railway_date_of_approval_by_Rregional_Office_agpur }"
                                            class="validate datepicker">
                                        <label for="railway_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="railway_approval_regional_office_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row">
                                	<div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="railway_valuation_dycfo" name="railway_valuation_by_DyCFO" type="text" value="${LADetails.railway_valuation_by_DyCFO }"
                                            class="validate datepicker">
                                        <label for="railway_valuation_dycfo">Valuation by DyCFO </label>
                                        <button type="button" id="railway_valuation_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="railway_demanded_amount" name="railway_demanded_amount" type="number" value="${LADetails.railway_demanded_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="railway_demanded_amount" class="fs-sm-8rem">Demanded Amount </label>
                                        <span id="railway_demanded_amountError" class="error-msg"></span>
	                                	<span id="railway_demanded_amount_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="demanded_amount_units" name="demanded_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="demanded_amount_units" name="demanded_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="railway_demanded_amount_unitsError" class="error-msg" ></span>
                               		</div> --%>
                                    <div class="col s12 m4 l4 amount-dropdown input-field ">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="railway_payment_amount" name="railway_payment_amount" type="number" value="${LADetails.railway_payment_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="railway_payment_amount" class="fs-sm-8rem"> Payment Amount </label>
                                        <span id="railway_payment_amountError" class="error-msg"></span>
	                                	<span id="railway_payment_amount_unitsError" class="error-msg right" ></span>
                                        <select class=" validate-dropdown" id="payment_amount_units_railway" name="payment_amount_units_railway">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_railway eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>    
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units_railway" name="payment_amount_units_railway">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_railway eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="railway_payment_amount_unitsError" class="error-msg" ></span>
                               		</div>  --%>                                
                                </div>
                                <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="railway_payment_approval_date" name="railway_approval_for_payment" value="${LADetails.railway_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="railway_payment_approval_date" class="fs-sm-8rem"> Approval for Payment</label>
                                        <button type="button" id="railway_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="railway_payment_date" name="railway_payment_date" type="text" value="${LADetails.railway_payment_date }"
                                            class="validate datepicker">
                                        <label for="railway_payment_date"> Payment Date </label>
                                        <button type="button" id="railway_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="railway_possession_date" name="railway_possession_date" type="text" value="${LADetails.railway_possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="railway_possession_date"> Possession Date</label>
                                        <button type="button" id="railway_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                               
                             <div class="row">
                             		<div class="col s6 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="railway_possession_status"
                                            name="railway_possession_status">
                                            <option value="" >Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.railway_possession_status eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                     
                                    <div class="col s6 m4 l4 input-field ">
                                        <p class="searchable_label">Payment Status </p>
                                        <select class="searchable" id="railway_payment_status"
                                            name="railway_payment_status">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.railway_payment_status eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="railway_special_feature" name="railway_special_feature" type="text" value="${LADetails.railway_special_feature }"
                                            class="validate mt-10">
                                        <label for="railway_special_feature"> Special Feature </label>
                                    </div>                                     
                              <!--   </div>
                                <div class="row">     -->                                 
                                  	<div class="col s12 m4 input-field">
                                        <input id="railway_attachment_no" name="railway_attachment_no" type="text" value="${LADetails.railway_attachment_no }"
                                            class="validate">
                                        <label for="railway_attachment_no">Attachment Number </label>
                                    </div>                                     
                                 </div>
                              </div>
                            
                            <div class="row">                                 
                                <div class="col m8 s12 offset-m2 l12">
                                    <div class="row">
                                        <div class="col l12 m12 s12">
                                        	<c:if test="${action eq 'add'}">
			                            <div id="selectedFilesInput">
			                                    	<div class="file-field input-field" id="laFilesDiv1" >
				                                        <div class="btn bg-m t-c">
				                                            <span>Attach Files</span>
				                                            <input type="file" id="laFiles1" name="laFiles"   onchange="selectFile('1')">
				                                        </div>
				                                        <div class="file-path-wrapper">
				                                            <input class="file-path validate" type="text">
				                                        </div>                                       
				                                    </div>
												</div>
			                                    <div id="selectedFiles">
												</div>
									  </c:if>	
									  <c:if test="${action eq 'edit'}">
													<c:set var="existingDeliverableFilesLength" value="${fn:length(LADetails.laFilesList )}"></c:set>
													<c:if test="${fn:length(LADetails.laFilesList ) gt 0}">
														<c:set var="existingDeliverableFilesLength" value="${fn:length(LADetails.laFilesList )+1}"></c:set>
													</c:if>
													<div id="selectedFilesInput">
				                                    	<div class="file-field input-field" id="laFilesDiv${existingDeliverableFilesLength }" >
					                                        <div class="btn bg-m t-c">
					                                            <span>Attach Files</span>
					                                            <input type="file" id="laFiles${existingDeliverableFilesLength }" name="laFiles"  onchange="selectFile('${existingDeliverableFilesLength }')">
					                                        </div>
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    
				                                    <div id="selectedFiles">
				                                    	<c:forEach var="obj" items="${LADetails.laFilesList }" varStatus="index">
															 <div id="laFileNames${index.count }">
																<a href="<%=CommonConstants.LAND_ACQUISITION_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
																<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
																<input type="hidden" name="laFileNames" value="${obj.attachment }">
														     </div>
														     <div style="clear:both" ></div>
														</c:forEach>
													</div>
				                             </c:if>	
                                            
                                            </div>
                                        <div class="col m12 s12">
                                            <div class="row">
                                                <!-- row 7 -->
                                                <div class="col s5 m6 l6 input-field center-align">
                                                    <p style="margin-top: 8px;">Any Issue ?</p>
                                                </div>
                                                <div class="col s7 m6 l6 input-field">
                                                    <p class="radiogroup"
                                                        style="padding-bottom: 10px;padding-top: 10px;">
                                                        <label>
                                                            <input class="with-gap" name="is_there_issue" type="radio"
                                                                value="Yes" />
                                                            <span>Yes</span>
                                                        </label> &nbsp; <label>
                                                            <input class="with-gap" name="is_there_issue" type="radio"
                                                                value="No" />
                                                            <span>No</span>
                                                        </label>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="issue_yes" style="display: none;">
                                <div class="row">
                                    <h6 class="center-align primary-text-bold">Issue
                                        Details </h6>
                                     
                                    <div class="col l12 m8 s12 offset-m2">
                                        <div class="row">
                                            <div class="col s12 m4 l6 input-field" style="margin-top: 32px;">
                                                <p class="searchable_label">Issue Category</p>
                                                <select class="searchable" id="issue_category_id" name="issue_category_id">
                                                    <option value="" selected>Select</option>
                                                    <c:forEach var="obj" items="${issueCatogoriesList}">
													<option value="${obj.category }">${obj.category }</option>
												</c:forEach>
                                                </select>
                                            </div>
                                            <div class="col s12 m8 l6 input-field" style="padding-top: 4px;">
                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="high" />
                                                        <span>High</span>
                                                    </label>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                     
                                    <div class="col s12 m8 l12 input-field offset-m2">
                                        <textarea  class="pmis-textarea" id="issue_description" name="issue_description"
                                            data-length="500"></textarea>
                                        <label for="issueDesc">Issue Description</label>
                                    </div>
                                </div>
                            </div>
 					<c:if test="${action eq 'add'}">
	 					 <div class="row">
	                                <!-- row 10 -->
	                                 
		                                <div class="col s12 m8 l12 input-field offset-m2">
		                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
		                                        data-length="1000"> </textarea>
		                                    <label for="remarks">Remarks</label>
		                                </div>
	                      </div>				                
				     </c:if>
				     <c:if test="${action eq 'edit'}">
                            <div class="row">
                                <!-- row 10 -->
                                 
                                 <c:if test="${LADetails.type_of_land == 'Government'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.gov_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                                 <c:if test="${LADetails.type_of_land == 'Forest'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.forest_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                                 <c:if test="${LADetails.type_of_land == 'Private'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.private_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                                 <c:if test="${LADetails.type_of_land == 'Railway'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.railway_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                            </div>
 							</c:if>
                            <div class="row">                                
                              
                                <div class="col s6 m4 l6 mt-brdr center-align offset-m2">
	                                <div class="m-1">
	                                     <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateLAFrom();" class="btn waves-effect waves-light bg-m">Update</button>
	                                     </c:if>
										 <c:if test="${action eq 'add'}"> 
					                       <button type="button" onclick="addLAForm();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
										 </c:if>                                
	                                </div>
                                </div>
                                
                                <div class="col s6 m4 l6 mt-brdr center-align">
                                	<div class="m-1">
                                     	<a href="<%=request.getContextPath()%>/land-acquisition" class="btn waves-effect waves-light bg-s">Cancel</a>
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

    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/datepickerDepedency.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/datetimepicker.js"></script>
    <script>
      /*   $(document).on('focus', '.datepicker', function () {
            $(this).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            })
        }); */
        function selectFile(no){
		    files = $("#laFiles"+no)[0].files;
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=laFileNames'+no+'>'
				 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
				 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
				 + '</div>'
				 + '<div style="clear:both;"></div>';
		    }
		    $("#selectedFiles").append(html);
		    
		    $('#laFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			moreFiles(fileIndex);
		}
		
		function moreFiles(no){
			var html = "";
			html =  html + '<div class="file-field input-field" id="laFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c">'
			+ '<span>Attach Files</span>'
			+ '<input type="file" id="laFiles'+no+'" name="laFiles"  onchange="selectFile('+no+')">'
			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFile(no){			
	     	$('#laFilesDiv'+no).remove();
	     	$('#laFileNames'+no).remove();
	    } 
      /*   let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy',autoClose:true};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    }); */

      /*   $(document).on('focus', '.datepicker-button', function () {
            var dateId = $(this).attr('id').split("__")[0];
            $('#' + dateId).datepicker({
            	 format: 'dd-mm-yyyy',
                 onSelect: function () {
                     $('.confirmation-btns .datepicker-done').click();
                 }
            });
            $('#' + dateId).datepicker('open');            
        }); */
	    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#issue_description,#jm_remarks,#remarks').characterCounter();
            // commented code placed next script tag from here
			forestGovernmentPrivateDivShowOrHide();
            $('input[name=is_there_issue]').change(function () {
                var radioval = $('input[name=is_there_issue]:checked').val();
                if (radioval == 'Yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'No') {
                    $('#issue_yes').css("display", "none");
                }
            });
            // call show/hide div function on approval change event
            $('input[name=jm_approval]').change(function () {
                forestGovernmentPrivateDivShowOrHide();
                $('select:not(.searchable)').formSelect();
            });
            // call show/hide div function on type of land change event
            $('#type_of_land').change(function () {
                forestGovernmentPrivateDivShowOrHide();
                $('select:not(.searchable)').formSelect();
            });
            // based on type of land show/hide div
            function forestGovernmentPrivateDivShowOrHide() {
                var jmapproval = $('input[name=jm_approval]:checked').val();
                if (jmapproval == 'Done') {
                    var landtype = $('#type_of_land').val();
                    if (landtype == 'Government') {
                        $('#govt_div').css("display", "block");
                        $('#forest_div').css("display", "none");
                        $('#private_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                    else if (landtype == 'Forest') {
                        $('#forest_div').css("display", "block");
                        $('#govt_div').css("display", "none");
                        $('#private_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                    else if (landtype == 'Private') {
                        $('#private_div').css("display", "block");
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                    else if (landtype == 'Railway') {
                        $('#private_div').css("display", "none");
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#railway_div').css("display", "block");
                    }
                    else {
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#private_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                }
                else if (jmapproval == '') {
                    $('#govt_div').css("display", "none");
                    $('#forest_div').css("display", "none");
                    $('#private_div').css("display", "none");
                    $('#railway_div').css("display", "none");
                }
            }
          
        });
        
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForLAForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${LADetails.work_id_fk}";
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
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
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
        }
        function getLandsList() {
        	$(".page-loader").show();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(type_of_land) == "") {
            	$("#type_of_land option:not(:first)").remove();
            	var myParams = { sub_category_of_land: sub_category_of_land, type_of_land: type_of_land };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getLandsList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                              	 var type_of_land = "${LADetails.type_of_land}";
                              	 
      	                           if ($.trim(type_of_land) != '' && val.type_of_land == $.trim(type_of_land)) {
                                         $("#type_of_land").append('<option value="' + val.type_of_land + '" selected>' + $.trim(val.type_of_land) + '</option>');
                                     } else {
                                         $("#type_of_land").append('<option value="' + val.type_of_land + '">' + $.trim(val.type_of_land) + '</option>');
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
        
        function getSubCategorysList() {
        	$(".page-loader").show();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(sub_category_of_land) == "") {
            	$("#sub_category_of_land option:not(:first)").remove();
            	var myParams = { sub_category_of_land: sub_category_of_land, type_of_land: type_of_land };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubCategorysList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	
                            	 var sub_category_of_land = "${LADetails.sub_category_of_land}";
                            	 
    	                           if ($.trim(sub_category_of_land) != '' && val.sub_category_of_land == $.trim(sub_category_of_land)) {
                                       $("#sub_category_of_land").append('<option value="' + val.id + '" selected>' + $.trim(val.sub_category_of_land) + '</option>');
                                   } else {
                                       $("#sub_category_of_land").append('<option value="' + val.id + '">' + $.trim(val.sub_category_of_land) + '</option>');
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
        
        function addLAForm(){
        	if(validator.form()){ // validation perform
   	        	$(".page-loader").show();	   
   	        	var amount_demanded = $('#amount_demanded').val();
   	        	var govt_amount_paid = $('#govt_amount_paid').val();
   	        	var forest_demanded_amount = $('#forest_demanded_amount').val();
   	        	var forest_payment_amount = $('#forest_payment_amount').val();
   	        	var private_basic_rate = $('#private_basic_rate').val();
   	        	var private_agri_tree_rate = $('#private_agri_tree_rate').val();
   	        	var private_forest_tree_rate = $('#private_forest_tree_rate').val();
   	        	var private_payment_amount = $('#private_payment_amount').val();
   	        	var railway_demanded_amount = $('#railway_demanded_amount').val();
   	        	var railway_payment_amount = $('#railway_payment_amount').val();
   	        	var jm_fee_amount = $('#jm_fee_amount').val();
   	        	
	  			if(jm_fee_amount == ""){$('#jm_fee_amount_units').val("");}
	  			if(amount_demanded == ""){$('#amount_demanded_units').val("");}
	  			if(govt_amount_paid == ""){$('#amount_paid_units').val("");}
	  			if(forest_demanded_amount == ""){$('#demanded_amount_units_forest').val("");}
	  			if(forest_payment_amount == ""){$('#payment_amount_units_forest').val("");}
	  			if(private_basic_rate == ""){$('#basic_rate_units').val("");}
	  			if(private_agri_tree_rate == ""){$('#agriculture_tree_rate_units').val("");}
	  			if(private_forest_tree_rate == ""){$('#forest_tree_rate_units').val("");}
	  			if(private_payment_amount == ""){$('#payment_amount_units').val("");}
	  			if(railway_demanded_amount == ""){$('#demanded_amount_units').val("");}
	  			if(railway_payment_amount == ""){$('#payment_amount_units_railway').val("");}
	  			
   	  			document.getElementById("landAcquisitionForm").submit();	
        	}
        }
        
        function updateLAFrom(){
         	if(validator.form()){ // validation perform
    	        	$(".page-loader").show();	
    	        	var amount_demanded = $('#amount_demanded').val();
       	        	var govt_amount_paid = $('#govt_amount_paid').val();
       	        	var forest_demanded_amount = $('#forest_demanded_amount').val();
       	        	var forest_payment_amount = $('#forest_payment_amount').val();
       	        	var private_basic_rate = $('#private_basic_rate').val();
       	        	var private_agri_tree_rate = $('#private_agri_tree_rate').val();
       	        	var private_forest_tree_rate = $('#private_forest_tree_rate').val();
       	        	var private_payment_amount = $('#private_payment_amount').val();
       	        	var railway_demanded_amount = $('#railway_demanded_amount').val();
       	        	var railway_payment_amount = $('#railway_payment_amount').val();
       	        	var jm_fee_amount = $('#jm_fee_amount').val();
       	        	
    	  			if(jm_fee_amount == ""){$('#jm_fee_amount_units').val("");}
    	  			if(amount_demanded == ""){$('#amount_demanded_units').val("");}
    	  			if(govt_amount_paid == ""){$('#amount_paid_units').val("");}
    	  			if(forest_demanded_amount == ""){$('#demanded_amount_units_forest').val("");}
    	  			if(forest_payment_amount == ""){$('#payment_amount_units_forest').val("");}
    	  			if(private_basic_rate == ""){$('#basic_rate_units').val("");}
    	  			if(private_agri_tree_rate == ""){$('#agriculture_tree_rate_units').val("");}
    	  			if(private_forest_tree_rate == ""){$('#forest_tree_rate_units').val("");}
    	  			if(private_payment_amount == ""){$('#payment_amount_units').val("");}
    	  			if(railway_demanded_amount == ""){$('#demanded_amount_units').val("");}
    	  			if(railway_payment_amount == ""){$('#payment_amount_units_railway').val("");}
    	  			
    	  			document.getElementById("landAcquisitionForm").submit();	
         	}
         }
        
        var validator =	$('#landAcquisitionForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"la_id": {
	  			 		required: true
	  			 	  },"type_of_land": {
	  			 		required: true
	  			 	  },"id": {
	  			 		required: true
	  			 	  },"jm_fee_amount":{
		  			 	required: false
	  			 	  },"area_acquired":{
		  			 	required: false
	  			 	  },"govt_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"forest_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"forest_area_acquired":{
		  			 	required: false
	  			 	  },"private_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"private_area_acquired":{
		  			 	required: false
	  			 	  },"railway_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"railway_area_acquired":{
		  			 	required: false
	  			 	  },"railway_demanded_amount":{
		  			 	required: false
	  			 	  },"railway_payment_amount":{
		  			 	required: false
	  			 	  },"private_basic_rate":{
		  			 	required: false
	  			 	  },"private_agri_tree_rate":{
		  			 	required: false
	  			 	  },"private_forest_tree_rate":{
		  			 	required: false
	  			 	  },"private_payment_amount":{
		  			 	required: false
	  			 	  },"railway_demanded_amount":{
		  			 	required: false
	  			 	  },"railway_payment_amount":{
		  			 	required: false
	  			 	  },"forest_demanded_amount":{
		  			 	required: false
	  			 	  },"forest_payment_amount":{
		  			 	required: false
	  			 	  },"amount_demanded":{
		  			 	required: false
	  			 	  },"govt_amount_paid":{
		  			 	required: false
	  			 	  },"jm_fee_amount_units":{
        		 		 required: function(element){
        		             return $("#jm_fee_amount").val()!="";
        		         }
        		 	  },"amount_demanded_units":{
        		 		 required: function(element){
        		             return $("#amount_demanded").val()!="";
        		         }
        		 	  },"amount_paid_units":{
        		 		 required: function(element){
        		             return $("#govt_amount_paid ").val()!="";
        		         }
        		 	  },"basic_rate_units":{
        		 		 required: function(element){
        		             return $("#private_basic_rate").val()!="";
        		         }
        		 	  },"agriculture_tree_rate_units":{
        		 		 required: function(element){
        		             return $("#private_agri_tree_rate").val()!="";
        		         }
        		 	  },"forest_tree_rate_units":{
        		 		 required: function(element){
        		             return $("#private_forest_tree_rate").val()!="";
        		         }
        		 	  },"demanded_amount_units_forest":{
        		 		 required: function(element){
        		             return $("#forest_demanded_amount").val()!="";
        		         }
        		 	  },"payment_amount_units_forest":{
        		 		 required: function(element){
        		             return $("#forest_payment_amount").val()!="";
        		         }
        		 	  },"payment_amount_units_railway":{
        		 		 required: function(element){
        		             return $("#railway_payment_amount").val()!="";
        		         }
        		 	  },"demanded_amount_units":{
        		 		 required: function(element){
        		             return $("#railway_demanded_amount").val()!="";
        		         }
        		 	  },"payment_amount_units":{
        		 		 required: function(element){
        		             return $("#private_payment_amount").val()!="";
        		         }
        		 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"la_id": {
	  			 		required: ' This field is required'
	  			 	  },"type_of_land": {
	  			 		required: ' This field is required'
	  			 	  },"id": {
	  			 		required: ' This field is required'
	  			 	  },"jm_fee_amount_units": {
	  			 		required: ' Rrequired'
	  			 	  },"demanded_amount_units": {
	  			 		required: ' Rrequired'
	  			 	  },"payment_amount_units": {
	  			 		required: ' Rrequired'
	  			 	  },"amount_demanded_units": {
	  			 		required: ' Rrequired'
	  			 	  },"amount_paid_units": {
	  			 		required: ' Rrequired'
	  			 	  },"payment_amount_units_forest": {
	  			 		required: ' Rrequired'
	  			 	  },"basic_rate_units": {
	  			 		required: ' Rrequired'
	  			 	  },"agriculture_tree_rate_units": {
	  			 		required: ' Rrequired'
	  			 	  },"forest_tree_rate_units": {
	  			 		required: ' Rrequired'
	  			 	  },"demanded_amount_units_forest": {
	  			 		required: ' Rrequired'
	  			 	  },"payment_amount_units_railway": {
	  			 		required: ' Rrequired'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						 document.getElementById("project_id_fkError").innerHTML="";
				 		 error.appendTo('#project_id_fkError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "la_id" ){
						   document.getElementById("la_idError").innerHTML="";
					 	   error.appendTo('#la_idError');
					}else if(element.attr("id") == "type_of_land" ){
						   document.getElementById("type_of_landError").innerHTML="";
					 	   error.appendTo('#type_of_landError');
					}else if(element.attr("id") == "sub_category_of_land" ){
						   document.getElementById("sub_category_of_landError").innerHTML="";
					 	   error.appendTo('#sub_category_of_landError');
					}else if(element.attr("id") == "govt_area_acquired" ){
						   document.getElementById("govt_area_acquiredError").innerHTML="";
					 	   error.appendTo('#govt_area_acquiredError');
				    }else if(element.attr("id") == "govt_area_to_be_acquired" ){
						   document.getElementById("govt_area_to_be_acquiredError").innerHTML="";
					 	   error.appendTo('#govt_area_to_be_acquiredError');
				   }else if(element.attr("id") == "forest_area_to_be_acquired" ){
					   document.getElementById("forest_area_to_be_acquiredError").innerHTML="";
				 	   error.appendTo('#forest_area_to_be_acquiredError');
			   	   }else if(element.attr("id") == "forest_area_acquired" ){
					   document.getElementById("forest_area_acquiredError").innerHTML="";
				 	   error.appendTo('#forest_area_acquiredError');
			       }else if(element.attr("id") == "private_area_to_be_acquired" ){
					   document.getElementById("private_area_to_be_acquiredError").innerHTML="";
				 	   error.appendTo('#private_area_to_be_acquiredError');
			   	   }else if(element.attr("id") == "private_area_acquired" ){
					   document.getElementById("private_area_acquiredError").innerHTML="";
				 	   error.appendTo('#private_area_acquiredError');
			       }else if(element.attr("id") == "railway_area_to_be_acquired" ){
					   document.getElementById("railway_area_to_be_acquiredError").innerHTML="";
				 	   error.appendTo('#railway_area_to_be_acquiredError');
			   	   }else if(element.attr("id") == "railway_area_acquired" ){
					   document.getElementById("railway_area_acquiredError").innerHTML="";
				 	   error.appendTo('#railway_area_acquiredError');
			       }else if(element.attr("id") == "railway_demanded_amount" ){
					   document.getElementById("railway_demanded_amountError").innerHTML="";
				 	   error.appendTo('#railway_demanded_amountError');
			   	   }else if(element.attr("id") == "railway_payment_amount" ){
					   document.getElementById("railway_payment_amountError").innerHTML="";
				 	   error.appendTo('#railway_payment_amountError');
			       }else if(element.attr("id") == "private_basic_rate" ){
					   document.getElementById("private_basic_rateError").innerHTML="";
				 	   error.appendTo('#private_basic_rateError');
			       }else if(element.attr("id") == "private_agri_tree_rate" ){
					   document.getElementById("private_agri_tree_rateError").innerHTML="";
				 	   error.appendTo('#private_agri_tree_rateError');
			       }else if(element.attr("id") == "private_forest_tree_rate" ){
					   document.getElementById("private_forest_tree_rateError").innerHTML="";
				 	   error.appendTo('#private_forest_tree_rateError');
			       }else if(element.attr("id") == "private_payment_amount" ){
					   document.getElementById("private_payment_amountError").innerHTML="";
				 	   error.appendTo('#private_payment_amountError');
			       }else if(element.attr("id") == "forest_demanded_amount" ){
					   document.getElementById("forest_demanded_amountError").innerHTML="";
				 	   error.appendTo('#forest_demanded_amountError');
			       }else if(element.attr("id") == "forest_payment_amount" ){
					   document.getElementById("forest_payment_amountError").innerHTML="";
				 	   error.appendTo('#forest_payment_amountError');
			       }else if(element.attr("id") == "amount_demanded" ){
					   document.getElementById("amount_demandedError").innerHTML="";
				 	   error.appendTo('#amount_demandedError');
			       }else if(element.attr("id") == "govt_amount_paid" ){
					   document.getElementById("govt_amount_paidError").innerHTML="";
				 	   error.appendTo('#govt_amount_paidError');
			       }else if(element.attr("id") == "jm_fee_amount" ){
					   document.getElementById("jm_fee_amountError").innerHTML="";
				 	   error.appendTo('#jm_fee_amountError');
			       } else if (element.attr("id") == "jm_fee_amount_units") {
			           document.getElementById("jm_fee_amount_unitsError").innerHTML = "";
			           error.appendTo('#jm_fee_amount_unitsError');
			       } else if (element.attr("id") == "basic_rate_units") {
			           document.getElementById("basic_rate_unitsError").innerHTML = "";
			           error.appendTo('#basic_rate_unitsError');
			       }else if (element.attr("id") == "agriculture_tree_rate_units") {
			           document.getElementById("agriculture_tree_rate_unitsError").innerHTML = "";
			           error.appendTo('#agriculture_tree_rate_unitsError');
			       }else if (element.attr("id") == "forest_tree_rate_units") {
			           document.getElementById("forest_tree_rate_unitsError").innerHTML = "";
			           error.appendTo('#forest_tree_rate_unitsError');
			       }else if (element.attr("id") == "payment_amount_units") {
			           document.getElementById("payment_amount_unitsError").innerHTML = "";
			           error.appendTo('#payment_amount_unitsError');
			       }else if (element.attr("id") == "demanded_amount_units_forest") {
			           document.getElementById("forest_demanded_amount_unitsError").innerHTML = "";
			           error.appendTo('#forest_demanded_amount_unitsError');
			       }else if (element.attr("id") == "payment_amount_units_forest") {
			           document.getElementById("forest_payment_amount_unitsError").innerHTML = "";
			           error.appendTo('#forest_payment_amount_unitsError');
			       }else if (element.attr("id") == "amount_demanded_units") {
			           document.getElementById("amount_demanded_unitsError").innerHTML = "";
			           error.appendTo('#amount_demanded_unitsError');
			       }else if (element.attr("id") == "amount_paid_units") {
			           document.getElementById("amount_paid_unitsError").innerHTML = "";
			           error.appendTo('#amount_paid_unitsError');
			       }else if (element.attr("id") == "demanded_amount_units") {
			           document.getElementById("railway_demanded_amount_unitsError").innerHTML = "";
			           error.appendTo('#railway_demanded_amount_unitsError');
			       }else if (element.attr("id") == "payment_amount_units_railway") {
			           document.getElementById("railway_payment_amount_unitsError").innerHTML = "";
			           error.appendTo('#railway_payment_amount_unitsError');
			       } else{
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
	            
	            $('select').keyup(function(){
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