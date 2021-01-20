<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Land Acquisition</c:if>
		 <c:if test="${action eq 'add'}"> Add Land Acquisition</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        /* change radio colors  */
        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }

        .primary-text-bold {
            color: #2E58AD !important;
            font-weight: 600
        }

        .input-field .prefix {
            color: #999;
        }

        .input-field .prefix.active {
            color: #2e58ad;
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
                            <div class="center-align p-2 bg-m">
                                <h6>
                                	 <c:if test="${action eq 'edit'}">Update Land Acquisition</c:if>
									 <c:if test="${action eq 'add'}"> Add Land Acquisition</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row" >
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m2 input-field">
                                    <!-- <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select> -->
                                    
                                    <label for="la_id"> Land Acquisition ID :</label>
                                </div>
                                 <div class="col s12 m6 input-field">
                                  <input id="la_id" name="la_id" type="text" class="validate mt-10" value="${LADetails.la_id }" readonly>
                                 </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="survey_number" name="survey_number" type="text" class="validate mt-10" value="${LADetails.survey_number }">
                                    <label for="survey_number">Survey Number </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="village_id" name="village_id" type="text" class="validate mt-10" value="${LADetails.village_id }">
                                    <label for="village_id">Village ID </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Type of Land </p>
                                    <select id="type_of_land" class="searchable" name="type_of_land">
                                        <option value="" >Select</option>
                                        <option value="Government"<c:if test="${LADetails.type_of_land eq 'Government'}">selected</c:if>>Government </option>
                                        <option value="Forest" <c:if test="${LADetails.type_of_land eq 'Forest'}">selected</c:if>>Forest</option>
                                        <option value="Private" <c:if test="${LADetails.type_of_land eq 'Private'}">selected</c:if>>Private</option>
                                        <option value="Railway" <c:if test="${LADetails.type_of_land eq 'Railway'}">selected</c:if>>Railway</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Sub Category of Land</p>
                                    <select class="searchable" name="sub_category_of_land">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="village" name="village" type="text" class="validate" value="${LADetails.village }">
                                    <label for="village"> Village </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="taluka" name="taluka" type="text" class="validate" value="${LADetails.taluka }">
                                    <label for="taluka"> Taluka </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="dy_slr" name="dy_slr" type="text" class="validate" value="${LADetails.dy_slr }">
                                    <label for="dy_slr">Dy SLR </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="sdo" name="sdo" type="text" class="validate" value="${LADetails.sdo }">
                                    <label for="sdo"> SDO</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="collector" name="collector" type="text" class="validate" value="${LADetails.collector }">
                                    <label for="collector">Collector </label>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="submission_date" name="proposal_submission_date_to_collector" type="text" value="${LADetails.proposal_submission_date_to_collector }"
                                        class="validate datepicker">
                                    <label for="submission_date">Proposal submission Date to collector</label>
                                    <button type="button" id="submission_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="area_of_plot" name="area_of_plot" type="text" class="validate" value="${LADetails.area_of_plot }">
                                    <label for="area_of_plot">Area of Plot </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="jm_fee_amount" name="jm_fee_amount" type="text" class="validate" value="${LADetails.jm_fee_amount }">
                                    <label for="jm_fee_amount"> JM Fee Amount </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="chainage_from" name="chainage_from" type="text" class="validate" value="${LADetails.chainage_from }">
                                    <label for="chainage_from">Chainage From</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="chainage_to" name="chainage_to" type="text" class="validate" value="${LADetails.chainage_to }">
                                    <label for="chainage_to"> Chainage To </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="jm_fee_letter_received_date" name="jm_fee_letter_received_date" type="text" value="${LADetails.jm_fee_letter_received_date }"
                                        class="validate datepicker">
                                    <label for="jm_fee_letter_received_date"> JM Fee Letter received Date </label>
                                    <button type="button" id="jm_fee_letter_received_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="jm_fee_paid_date" name="jm_fee_paid_date" type="text" value="${LADetails.jm_fee_paid_date }"
                                        class="validate datepicker">
                                    <label for="jm_fee_paid_date">JM Fee Paid Date </label>
                                    <button type="button" id="jm_fee_paid_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="jm_start_date" type="text" name="jm_start_date" class="validate datepicker" value="${LADetails.jm_start_date }">
                                    <label for="jm_start_date">JM Start Date </label>
                                    <button type="button" id="jm_start_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="jm_completion_date" name="jm_completion_date" type="text" class="validate datepicker" value="${LADetails.jm_completion_date }">
                                    <label for="jm_completion_date"> JM Completion Date</label>
                                    <button type="button" id="jm_completion_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="jm_sheet_date_to_sdo" name="jm_sheet_date_to_sdo" type="text" class="validate datepicker" value="${LADetails.jm_sheet_date_to_sdo }">
                                    <label for="jm_sheet_date_to_sdo">JM Sheet Date to SDO </label>
                                    <button type="button" id="jm_sheet_date_to_sdo__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field center-align">
                                    <div class="row">
                                        <div class="col s4 m4 input-field ">
                                            <p style="margin-top: 8px;">JM Approval</p>
                                        </div>
                                        <div class="col s8 m8 input-field">
                                            <p class="" style="padding-bottom: 10px;padding-top: 10px;">
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
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="jm_remarks" name="jm_remarks" class="materialize-textarea"
                                        data-length="500"></textarea>
                                    <label for="jm_remarks"> JM Remarks</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <!-- if selected govt this div shown  -->
                            <div id="govt_div" style="display: none; ">
                                <h6 class="center-align primary-text-bold">Government Land Details </h6>
                               
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="govt_area_to_be_acquired" name="area_to_be_acquired" type="number" min="0.01" step="0.01" value="${LADetails.area_to_be_acquired }"
                                            class="validate">
                                        <label for="govt_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                    </div>      
                                    <div class="col s12 m4 input-field">
                                        <input id="govt_area_acquired" name="area_acquired" type="number" min="0.01" step="0.01" value="${LADetails.area_acquired }"
                                            class="validate">
                                        <label for="govt_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                    </div>   
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col s12 m4 input-field ">
                                        <input id="proposal_submission" type="text" value="${LADetails.proposal_submission }"
                                            name="proposal_submission" class="validate datepicker">
                                        <label for="proposal_submission">Proposal submission</label>
                                        <button type="button" id="proposal_submission__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="letter_for_payment" type="text" class="validate datepicker" value="${LADetails.letter_for_payment }"
                                            name="letter_for_payment">
                                        <label for="letter_for_payment">Letter for Payment</label>
                                        <button type="button" id="letter_for_payment__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="amount_demanded" name="amount_demanded" type="number" value="${LADetails.amount_demanded }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="amount_demanded">Amount Demanded</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> LFP Status </p>
                                        <select class="searchable" id="lfp_status_fk" name="lfp_status_fk">
                                            <option value="" selected>Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.lfp_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="approval_for_payment" type="text" value="${LADetails.approval_for_payment }"
                                            name="approval_for_payment" class="validate datepicker mt-10">
                                        <label for="approval_for_payment">Approval for Payment </label>
                                        <button type="button" id="approval_for_payment__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="payment_date" name="payment_date" type="text" value="${LADetails.payment_date }"
                                            class="validate datepicker">
                                        <label for="payment_date">Payment date </label>
                                        <button type="button" id="payment_date__icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="govt_amount_paid" name="amount_paid" type="number" value="${LADetails.amount_paid }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="govt_amount_paid"> Amount Paid</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> Payment Status </p>
                                        <select class="searchable" id="payment_status_fk" name="payment_status_fk">
                                            <option value="" selected>Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.payment_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                   <div class="col m2 hide-on-small-only"></div>
                                   <div class="col s12 m4 input-field ">
                                        <input id="possession_date" name="possession_date" type="text" value="${LADetails.possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="possession_date">Possession Date </label>
                                        <button type="button" id="possession_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s12 m4 input-field ">
                                        <input id="valuation_date" type="text" value="${LADetails.valuation_date }"
                                            name="valuation_date" class="validate datepicker mt-10">
                                        <label for="valuation_date">Valuation Date</label>
                                        <button type="button" id="valuation_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                   <div class="col m2 hide-on-small-only"></div>
                                   <div class="col s12 m4 input-field ">
                                       <input id="govt_special_feature" name="special_feature" type="text" value="${LADetails.special_feature }"
                                           class="validate mt-10">
                                       <label for="govt_special_feature"> Special Feature</label>
                                   </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                            </div>

        <!-- if selected forest this div shown  -->
                            
                            <div id="forest_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Forest Land Details </h6>
                               <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_area_to_be_acquired" name="forest_area_to_be_acquired" type="number" min="0.01" step="0.01" value="${LADetails.forest_area_to_be_acquired }"
                                            class="validate">
                                        <label for="forest_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                     <div class="col s12 m4 input-field">
                                        <input id="forest_area_acquired" name="forest_area_acquired" type="number" min="0.01" step="0.01" value="${LADetails.forest_area_acquired }"
                                            class="validate">
                                        <label for="forest_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_online_submission" name="forest_online_submission" type="text" value="${LADetails.forest_online_submission }"
                                            class="validate datepicker">
                                        <label for="forest_online_submission"> On line Submission </label>
                                        <button type="button" id="forest_online_submission__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_dycfo" name="forest_submission_date_to_dycfo" type="text" value="${LADetails.forest_submission_date_to_dycfo }"
                                            class="validate datepicker">
                                        <label for="forest_submission_dycfo">Submission Date to DyCFO </label>
                                        <button type="button" id="forest_submission_dycfo__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_ccf" name="forest_submission_date_to_ccf_thane" type="text" value="${LADetails.forest_submission_date_to_ccf_thane }"
                                            class="validate datepicker">
                                        <label for="forest_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="forest_submission_ccf__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_nodal_officer"
                                            name="forest_submission_date_to_nodal_officer" type="text" value="${LADetails.forest_submission_date_to_nodal_officer }"
                                            class="validate datepicker">
                                        <label for="forest_submission_nodal_officer"> Submission Date to Nodal
                                            Officer</label>
                                        <button type="button" id="forest_submission_nodal_officer__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_revenue_sec" name="forest_submission_date_to_revenue_secretary_mantralaya" value="${LADetails.forest_submission_date_to_revenue_secretary_mantralaya }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_submission_revenue_sec"> Submission Date to Revenue Secretary
                                            Mantralaya </label>
                                        <button type="button" id="forest_submission_revenue_sec__icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_regional_office"
                                            name="forest_submission_date_to_regional_office_nagpur" type="text" value="${LADetails.forest_submission_date_to_regional_office_nagpur }"
                                            class="validate datepicker">
                                        <label for="forest_submission_regional_office"> Submission Date to Regional
                                            Office Nagpur
                                        </label>
                                        <button type="button" id="forest_submission_regional_office__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_approval_regional_office"
                                            name="forest_date_of_approval_by_regional_office_nagpur" type="text" value="${LADetails.forest_date_of_approval_by_regional_office_nagpur }"
                                            class="validate datepicker">
                                        <label for="forest_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="forest_approval_regional_office__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_valuation_dycfo" name="forest_valuation_by_dycfo" type="text" value="${LADetails.forest_valuation_by_dycfo }"
                                            class="validate datepicker">
                                        <label for="forest_valuation_dycfo">Valuation by DyCFO </label>
                                        <button type="button" id="forest_valuation_dycfo__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="forest_demanded_amount" name="forest_demanded_amount" type="number" value="${LADetails.forest_demanded_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="forest_demanded_amount">Demanded Amount </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="forest_payment_amount" name="forest_payment_amount" type="number" value="${LADetails.forest_payment_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="forest_payment_amount"> Payment Amount </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_payment_approval_date" name="forest_approval_for_payment" value="${LADetails.forest_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_payment_approval_date"> Approval for Payment</label>
                                        <button type="button" id="forest_payment_approval_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_payment_date" name="forest_payment_date" type="text" value="${LADetails.forest_payment_date }"
                                            class="validate datepicker">
                                        <label for="forest_payment_date"> Payment Date </label>
                                        <button type="button" id="forest_payment_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field"> 
                                        <input id="forest_possession_date" name="forest_possession_date" type="text" value="${LADetails.forest_possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="forest_possession_date"> Possession Date</label>
                                        <button type="button" id="forest_possession_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_special_feature" name="forest_special_feature" type="text" value="${LADetails.forest_special_feature }"
                                            class="validate mt-10">
                                        <label for="forest_special_feature"> Special Feature </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                            </div>

        <!-- if selected private this div shown  -->
                            
                            <div id="private_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Private Land Details                                </h6>   
                                 <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>                            
                                    <div class="col s12 m4 input-field">
                                        <input id="private_area_to_be_acquired" name="private_area_to_be_acquired" type="text" value="${LADetails.private_area_to_be_acquired }"
                                            class="validate">
                                        <label for="private_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                     <div class="col s12 m4 input-field">
                                        <input id="private_area_acquired" name="private_area_acquired" type="text" value="${LADetails.private_area_acquired }"
                                            class="validate">
                                        <label for="private_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                    </div>                                   
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>    
                                 	<div class="col s12 m8 input-field">
                                        <input id="private_name_of_owner" name="name_of_the_owner" type="text" value="${LADetails.name_of_the_owner }"
                                            class="validate">
                                        <label for="private_name_of_owner">Name of Owner</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_basic_rate" name=basic_rate type="text" value="${LADetails.basic_rate }"
                                            class="validate">
                                        <label for="private_basic_rate">Basic Rate </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_attachment_no" name="private_attachment_no" type="text" value="${LADetails.private_attachment_no }"
                                            class="validate">
                                        <label for="private_attachment_no">Attachment Number </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                              
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    <div class="col s12 m4 input-field">
                                        <input id="private_agri_trees" name="private_agri_trees" type="text" value="${LADetails.special_feature }"
                                            class="validate">
                                        <label for="private_agri_trees"> Agriculture tree nos</label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_agri_tree_rate" name="agriculture_tree_rate" type="number" min="0.01" step="0.01" value="${LADetails.agriculture_tree_rate }"
                                            class="validate">
                                        <label for="private_agri_tree_rate"> Agriculture tree rate </label>
                                        </label>
                                    </div>
                                   
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    <div class="col s12 m4 input-field">
                                        <input id="private_forest_trees" name="forest_tree_nos" type="number" value="${LADetails.forest_tree_nos }"
                                            class="validate">
                                        <label for="private_forest_trees">Forest tree nos </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_forest_tree_rate" name="forest_tree_rate" type="number" min="0.01" step="0.01" value="${LADetails.forest_tree_rate }"
                                            class="validate">
                                        <label for="private_forest_tree_rate"> Forest tree rate </label>
                                        </label>
                                    </div>
                                   
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                               
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_owner_consent" name="consent_from_owner" type="text" value="${LADetails.consent_from_owner }"
                                            class="validate datepicker">
                                        <label for="private_owner_consent">Consent from Owner </label>
                                        <button type="button" id="private_owner_consent__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_leagal_search_report" name="legal_search_report" value="${LADetails.legal_search_report }"
                                            type="text" class="validate datepicker">
                                        <label for="private_leagal_search_report"> Legal Search Report</label>
                                        <button type="button" id="private_leagal_search_rport__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_registartion_date" name="date_of_registration" value="${LADetails.date_of_registration }"
                                            type="text" class="validate datepicker">
                                        <label for="private_registartion_date">Date of Registration </label>
                                        <button type="button" id="private_registartion_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_possession_date" name="date_of_possession" type="text" value="${LADetails.date_of_possession }"
                                            class="validate datepicker">
                                        <label for="private_possession_date">Date of Possession</label>
                                        <button type="button" id="private_possession_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label">Possession Status</p>
                                        <select class="searchable" id="possession_status_fk"
                                            name="private_possession_status">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_special_feature" name="private_special_feature" type="text" value="${LADetails.private_special_feature }"
                                            class="validate mt-10">
                                        <label for="private_special_feature"> Special Feature </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                               
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                   
                                    <div class="col s12 m4 input-field"> 
                                        <input id="private_forest_tree_survey" name="forest_tree_survey" value="${LADetails.forest_tree_survey }"
                                            type="text" class="validate datepicker">
                                        <label for="private_forest_tree_survey"> Forest Tree Survey </label>
                                        <button type="button" id="private_forest_tree_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_forest_tree_valuation" name="forest_tree_valuation" value="${LADetails.forest_tree_valuation }"
                                            type="text" class="validate datepicker mt-10">
                                        <label for="private_forest_tree_valuation"> Forest Tree Valuation </label>
                                        <button type="button" id="private_forest_tree_valuation__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> Forest Tree Valuation Status </p>
                                        <select class="searchable" id="private_forest_tree_valuation_status"
                                            name="forest_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_horiculture_tree_survey"
                                            name="horticulture_tree_survey" type="text" value="${LADetails.horticulture_tree_survey }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_survey"> Horticulture Tree Survey </label>
                                        <button type="button" id="private_horiculture_tree_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_horiculture_tree_valuation"
                                            name="horticulture_tree_valuation" type="text" value="${LADetails.horticulture_tree_valuation }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_valuation"> Horticulture Tree Valuation
                                        </label>
                                        <button type="button" id="private_horiculture_tree_valuation__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_structure_survey" name="structure_survey" type="text" value="${LADetails.structure_survey }"
                                            class="validate datepicker">
                                        <label for="private_structure_survey"> Structure Survey </label>
                                        <button type="button" id="private_structure_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_structure_valuation" name="structure_valuation" value="${LADetails.structure_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_structure_valuation"> Structure Valuation </label>
                                        <button type="button" id="private_structure_valuation__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_borewell_survey" name="borewell_survey" type="text" value="${LADetails.borewell_survey }"
                                            class="validate datepicker">
                                        <label for="private_borewell_survey"> Borewell Survey </label>
                                        <button type="button" id="private_borewell_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_borewell_valuation" name="borewell_valuation" value="${LADetails.borewell_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_borewell_valuation"> Borewell Valuation </label>
                                        <button type="button" id="private_borewell_valuation__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_rfp_adtp" name="date_of_rfp_to_adtp" type="text" value="${LADetails.date_of_rfp_to_adtp }"
                                            class="validate datepicker">
                                        <label for="private_rfp_adtp">Date of RFP to ADTP </label>
                                        <button type="button" id="private_rfp_adtp__icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_rate_fixation_date" name="date_of_rate_fixation_of_land" value="${LADetails.date_of_rate_fixation_of_land }"
                                            type="text" class="validate datepicker">
                                        <label for="private_rate_fixation_date"> Date of Rate Fixation of Land </label>
                                        <button type="button" id="private_rate_fixation_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_sdo_payment_demand_date"
                                            name="sdo_demand_for_payment" type="text" value="${LADetails.sdo_demand_for_payment }"
                                            class="validate datepicker">
                                        <label for="private_sdo_payment_demand_date">SDO demand for payment </label>
                                        <button type="button" id="private_sdo_payment_demand_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_payment_approval_date" name="date_of_approval_for_payment" value="${LADetails.date_of_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="private_payment_approval_date"> Date of Approval for Payment
                                        </label>
                                        <button type="button" id="private_payment_approval_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_payment_amount" name="payment_amount" type="text" value="${LADetails.payment_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="private_payment_amount">Payment Amount </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_payment_date" name="private_payment_date" type="text" value="${LADetails.private_payment_date }"
                                            class="validate datepicker">
                                        <label for="private_payment_date"> Payment Date </label>
                                        <button type="button" id="private_payment_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                            </div>
             <!-- //*********************************************************   -->             
 							<div id="railway_div" style="display: none; ">
                                <h6 class="center-align primary-text-bold">Railway Land Details </h6>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_area_to_be_acquired" name="railway_area_to_be_acquired" value="${LADetails.railway_area_to_be_acquired }"
                                            type="number" min="0.01" step="0.01" class="validate">
                                        <label for="railway_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_area_acquired" name="railway_area_acquired" type="number" min="0.01" step="0.01" value="${LADetails.railway_area_acquired }"
                                            class="validate">
                                        <label for="railway_area_acquired"> Area Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    
                                    <div class="col s12 m4 input-field ">
                                        <input id="railway_online_submission" type="text" value="${LADetails.railway_online_submission }"
                                            name="railway_online_submission" class="validate datepicker">
                                        <label for="railway_online_submission">Online Submission</label>
                                        <button type="button" id="railway_online_submission__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="railway_submission_dycfo" type="text" name="railway_submission_date_to_DyCFO" value="${LADetails.railway_submission_date_to_DyCFO }"
                                            class="validate datepicker">
                                        <label for="railway_submission_dycfo">Submission to DyCFO</label>
                                        <button type="button" id="railway_submission_dycfo__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_submission_ccf" name="railway_submission_date_to_CCF_Thane" type="text" value="${LADetails.railway_submission_date_to_CCF_Thane }"
                                            class="validate datepicker">
                                        <label for="railway_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="railway_submission_ccf__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_submission_nodal_officer"
                                            name="railway_submission_date_to_nodal_officer_CCF_Nagpur" type="text" value="${LADetails.railway_submission_date_to_nodal_officer_CCF_Nagpur }"
                                            class="validate datepicker">
                                        <label for="railway_submission_nodal_officer"> Submission Date to Nodal
                                            Officer / CCF Nagpur </label>
                                        <button type="button" id="railway_submission_nodal_officer__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_submission_revenue_sec" name="railway_submission_date_to_revenue_secretary_mantralaya" value="${LADetails.railway_submission_date_to_revenue_secretary_mantralaya }"
                                            type="text" class="validate datepicker">
                                        <label for="railway_submission_revenue_sec"> Submission Date to Revenue
                                            Secretary
                                            Mantralaya </label>
                                        <button type="button" id="railway_submission_revenue_sec__icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_submission_regional_office" value="${LADetails.railway_submission_date_to_regional_office_nagpur }"
                                            name="railway_submission_date_to_regional_office_nagpur" type="text"
                                            class="validate datepicker">
                                        <label for="railway_submission_regional_office"> Submission Date to Regional
                                            Office Nagpur
                                        </label>
                                        <button type="button" id="railway_submission_regional_office__icon" 
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_approval_regional_office"
                                            name="railway_date_of_approval_by_Rregional_Office_agpur" type="text" value="${LADetails.railway_date_of_approval_by_Rregional_Office_agpur }"
                                            class="validate datepicker">
                                        <label for="railway_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="railway_approval_regional_office__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_valuation_dycfo" name="railway_valuation_by_DyCFO" type="text" value="${LADetails.railway_valuation_by_DyCFO }"
                                            class="validate datepicker">
                                        <label for="railway_valuation_dycfo">Valuation by DyCFO </label>
                                        <button type="button" id="railway_valuation_dycfo__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="railway_demanded_amount" name="railway_demanded_amount" type="number" value="${LADetails.railway_demanded_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="railway_demanded_amount">Demanded Amount </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="railway_payment_amount" name="railway_payment_amount" type="number" value="${LADetails.railway_payment_amount }" min="0.01" step="0.01"
                                            class="validate">
                                        <label for="railway_payment_amount"> Payment Amount </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_payment_approval_date" name="railway_approval_for_payment" value="${LADetails.railway_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="railway_payment_approval_date"> Approval for Payment</label>
                                        <button type="button" id="railway_payment_approval_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_payment_date" name="railway_payment_date" type="text" value="${LADetails.railway_payment_date }"
                                            class="validate datepicker">
                                        <label for="railway_payment_date"> Payment Date </label>
                                        <button type="button" id="railway_payment_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_possession_date" name="railway_possession_date" type="text" value="${LADetails.railway_possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="railway_possession_date"> Possession Date</label>
                                        <button type="button" id="railway_possession_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
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
                                    <div class="col s12 m4 input-field">
                                        <input id="railway_special_feature" name="railway_special_feature" type="text" value="${LADetails.railway_special_feature }"
                                            class="validate mt-10">
                                        <label for="railway_special_feature"> Special Feature </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row">
                                        <div class="col m6 s12">
                                            <div class="file-field input-field">
                                                <div class="btn bg-m">
                                                    <span>Attachment</span>
                                                    <input type="file">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text" >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col m6 s12">
                                            <div class="row">
                                                <!-- row 7 -->
                                                <div class="col s6 m6 input-field center-align">
                                                    <p style="margin-top: 8px;">Any Issue ?</p>
                                                </div>
                                                <div class="col s6 m6 input-field">
                                                    <p class="radiogroup"
                                                        style="padding-bottom: 10px;padding-top: 10px;">
                                                        <label>
                                                            <input class="with-gap" name="issue" type="radio"
                                                                value="yes" />
                                                            <span>Yes</span>
                                                        </label> &nbsp; <label>
                                                            <input class="with-gap" name="issue" type="radio"
                                                                value="no" />
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
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col m8 s12">
                                        <div class="row">
                                            <div class="col s12 m6 input-field" style="margin-top: 35px;">
                                                <p class="searchable_label">Issue Category</p>
                                                <select class="searchable">
                                                    <option value="0" selected>Select</option>
                                                    <option value="1">Category 1</option>
                                                    <option value="2">Category 2</option>
                                                    <option value="3">Category 3</option>
                                                </select>
                                            </div>
                                            <div class="col s12 m6 input-field" style="padding-top: 4px;">
                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="high" />
                                                        <span>High</span>
                                                    </label>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m8 input-field">
                                        <textarea id="issueDesc" class="materialize-textarea"
                                            data-length="500"></textarea>
                                        <label for="issueDesc">Issue Description</label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea"
                                        data-length="1000"></textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                              
                                <div class="col s12 m4">
                                   <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateLA();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addLA();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                            <a href="<%=request.getContextPath()%>/land-acquisition" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
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

    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>

    <script>
        $(document).on('focus', '.datepicker', function () {
            $(this).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            })
        });
        $(document).on('focus', '.datepicker-button', function () {
            var dateId = $(this).attr('id').split("__")[0];
            $('#' + dateId).datepicker({
            	 format: 'dd-mm-yyyy',
                 onSelect: function () {
                     $('.confirmation-btns .datepicker-done').click();
                 }
            });
            $('#' + dateId).datepicker('open');
            
        });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#textarea1,#textarea2,#issueDesc,#jmremarks').characterCounter();
            // commented code placed next script tag from here
			forestGovernmentPrivateDivShowOrHide();
            $('input[name=issue]').change(function () {
                var radioval = $('input[name=issue]:checked').val();
                if (radioval == 'yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#issue_yes').css("display", "none");
                }
            });
            // call show/hide div function on approval change event
            $('input[name=jm_approval]').change(function () {
                forestGovernmentPrivateDivShowOrHide();
            });
            // call show/hide div function on type of land change event
            $('#type_of_land').change(function () {
                forestGovernmentPrivateDivShowOrHide();
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
                    }
                    else if (landtype == 'Forest') {
                        $('#forest_div').css("display", "block");
                        $('#govt_div').css("display", "none");
                        $('#private_div').css("display", "none");
                    }
                    else if (landtype == 'Private') {
                        $('#private_div').css("display", "block");
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
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
    </script>
   
</body>

</html>