<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Land Aquisition</title>
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
                                <h6>Add / Edit Land Aquisition</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row" style="margin-bottom: 20px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <!-- <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select> -->
                                    <label class="primary-text-bold"> Land Aquisition ID :</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="survey_no" name="survey_no" type="text" class="validate mt-10">
                                    <label for="survey_no">Survey Number </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Village ID </p>
                                    <select class="searchable" id="village_id" name="village_id">
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
                                    <p class="searchable_label"> Type of Land </p>
                                    <select id="type_of_land" class="searchable" name="type_of_land">
                                        <option value="0" selected>Select</option>
                                        <option value="Govt">Govt </option>
                                        <option value="Forest">Forest</option>
                                        <option value="Private">Private</option>
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
                                    <input id="village" name="village" type="text" class="validate">
                                    <label for="village"> Village </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="taluka" name="taluka" type="text" class="validate">
                                    <label for="taluka"> Taluka </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="dy_slr" name="dy_slr" type="text" class="validate">
                                    <label for="dy-slr">Dy SLR </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="sdo" name="sdo" type="text" class="validate">
                                    <label for="sdo"> SDO</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="collector" name="collector" type="text" class="validate">
                                    <label for="collector">Collector </label>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="submission_date" name="submission_date" type="text"
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
                                    <input id="area_of_plot" name="area_of_plot" type="text" class="validate">
                                    <label for="area_of_plot">Area of Plot </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="jm_free_amount" name="jm_free_amount" type="text" class="validate">
                                    <label for="jm_free_amount"> JM Fee Amount </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="chainage_from" name="chainage_from" type="text" class="validate">
                                    <label for="chainage_from">Chainage From</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="chainage_to" name="chainage_to" type="text" class="validate">
                                    <label for="chainage_to"> Chainage To </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="fee_received_date" name="fee_received_date" type="text"
                                        class="validate datepicker">
                                    <label for="fee_received_date"> JM Fee Letter received Date </label>
                                    <button type="button" id="fee_received_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="fee_paid_date" name="fee_paid_date" type="text"
                                        class="validate datepicker">
                                    <label for="fee_paid_date">JM Fee Paid Date </label>
                                    <button type="button" id="fee_paid_date__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="jm_start" type="text" name="jm_start" class="validate datepicker">
                                    <label for="jm_start">JM Start Date </label>
                                    <button type="button" id="jm_start__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="jm_end" name="jm_end" type="text" class="validate datepicker">
                                    <label for="jm_end"> JM Completion Date</label>
                                    <button type="button" id="jm_end__icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="jm_to_sdo" name="jm_to_sdo" type="text" class="validate datepicker">
                                    <label for="jm_to_sdo">JM Sheet Date to SDO </label>
                                    <button type="button" id="jm_to_sdo__icon" class="datepicker-button"><i
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
                                                    <input class="with-gap" name="jmApproval" type="radio"
                                                        value="accept" />
                                                    <span>Accept</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="jmApproval" type="radio"
                                                        value="reject" />
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
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="govt_survey_no" type="text" class="validate">
                                        <label for="govt_survey_no">Survey Number </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label>Sub Category of Land </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label> Village ID </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="govt_survey_no" type="text" class="validate">
                                        <label for="govt_survey_no"> Govt Land Classification </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="govt_area_acquired" name="govt_area_acquired" type="text"
                                            class="validate">
                                        <label for="govt_area_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_proposal_submission_date" type="text"
                                            name="govt_proposal_submission_date" class="validate datepicker">
                                        <label for="govt_proposal_submission_date">Proposal submission</label>
                                        <button type="button" id="govt_proposal_submission_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> Proposal Submission Status </p>
                                        <select class="searchable" id="govt_proposal_submission_status"
                                            name="govt_proposal_submission_status">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_valuation_date" type="text"
                                            name="govt_valuation_date" class="validate datepicker mt-10">
                                        <label for="govt_valuation_date">Valuation Date</label>
                                        <button type="button" id="govt_valuation_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_letter_for_payment" type="text" class="validate datepicker"
                                            name="govt_letter_for_payment">
                                        <label for="govt_letter_for_payment">Letter for Payment</label>
                                        <button type="button" id="govt_letter_for_payment__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="govt_amount_demanded" name="govt_amount_demanded" type="text"
                                            class="validate">
                                        <label for="govt_amount_demanded">Amount Demanded</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> LFP Status </p>
                                        <select class="searchable" id="govt_lfp_status" name="govt_lfp_status">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_payment_approval_date" type="text"
                                            name="govt_payment_approval_date" class="validate datepicker mt-10">
                                        <label for="govt_payment_approval_date">Approval for Payment </label>
                                        <button type="button" id="govt_payment_approval_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_payment_date" name="govt_payment_date" type="text"
                                            class="validate datepicker">
                                        <label for="govt_payment_date">Payment date </label>
                                        <button type="button" id="govt_payment_date__icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="govt_amount_paid" name="govt_amount_paid" type="text"
                                            class="validate">
                                        <label for="govt_amount_paid"> Amount Paid</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> Payment Status </p>
                                        <select class="searchable" id="govt_payment_status" name="govt_payment_status">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_possession_date" name="govt_possession_date" type="text"
                                            class="validate datepicker mt-10">
                                        <label for="govt_possession_date">Possession Date </label>
                                        <button type="button" id="govt_possession_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="govt_possession_status"
                                            name="govt_possession_status">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="govt_special_feature" name="govt_special_feature" type="text"
                                            class="validate mt-10">
                                        <label for="govt_special_feature"> Special Feature</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                            </div>

                            <!-- if selected forest this div shown  -->
                            <div id="forest_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Forest Land Details </h6>
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_survey_no" type="text" class="validate">
                                        <label for="forest_survey_no">Survey Number </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label>Sub Category of Land </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label> Village ID </label>
                                    </div> -->
                                    <div class="col s12 m8 input-field">
                                        <input id="forest_area_acquired" name="forest_area_acquired" type="text"
                                            class="validate">
                                        <label for="forest_area_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_online_submission" name="forest_online_submission" type="text"
                                            class="validate datepicker">
                                        <label for="forest_online_submission"> On line Submission </label>
                                        <button type="button" id="forest_online_submission__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_dycfo" name="forest_submission_dycfo" type="text"
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
                                        <input id="forest_submission_ccf" name="forest_submission_ccf" type="text"
                                            class="validate datepicker">
                                        <label for="forest_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="forest_submission_ccf__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_nodal_officer"
                                            name="forest_submission_nodal_officer" type="text"
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
                                        <input id="forest_submission_revenue_sec" name="forest_submission_revenue_sec"
                                            type="text" class="validate datepicker">
                                        <label for="forest_submission_revenue_sec"> Submission Date to Revenue Secretary
                                            Mantralaya </label>
                                        <button type="button" id="forest_submission_revenue_sec__icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_submission_regional_office"
                                            name="forest_submission_regional_office" type="text"
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
                                            name="forest_approval_regional_office" type="text"
                                            class="validate datepicker">
                                        <label for="forest_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="forest_approval_regional_office__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_valuation_dycfo" name="forest_valuation_dycfo" type="text"
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
                                        <input id="forest_demanded_amount" name="forest_demanded_amount" type="number"
                                            class="validate">
                                        <label for="forest_demanded_amount">Demanded Amount </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="forest_payment_amount" name="forest_payment_amount" type="number"
                                            class="validate">
                                        <label for="forest_payment_amount"> Payment Amount </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_payment_approval_date" name="forest_payment_approval_date"
                                            type="text" class="validate datepicker">
                                        <label for="forest_payment_approval_date"> Approval for Payment</label>
                                        <button type="button" id="forest_payment_approval_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_payment_date" name="forest_payment_date" type="text"
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
                                        <input id="forest_possession_date" name="forest_possession_date" type="text"
                                            class="validate datepicker mt-10">
                                        <label for="forest_possession_date"> Possession Date</label>
                                        <button type="button" id="forest_possession_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="forest_possession_status"
                                            name="forest_possession_status">
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
                                        <p class="searchable_label">Payment Status </p>
                                        <select class="searchable" id="forest_searchable_label"
                                            name="forest_searchable_label">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="forest_special_feature" name="forest_special_feature" type="text"
                                            class="validate mt-10">
                                        <label for="forest_special_feature"> Special Feature </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                            </div>

                            <!-- if selected private this div shown  -->
                            <div id="private_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Private Land Details
                                </h6>
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_owner_name" type="text" class="validate">
                                        <label for="private_owner_name"> Name of Owner </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label>Type of Land</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label>Land Class</label>
                                    </div> -->
                                    <div class="col s12 m4 input-field">
                                        <input id="private_area_acquired" name="private_area_acquired" type="text"
                                            class="validate">
                                        <label for="private_area_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <label> Area to be Acquired </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_name_of_owner" name="private_name_of_owner" type="text"
                                            class="validate">
                                        <label for="private_name_of_owner">Name of Owner</label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_basic_rate" name="private_basic_rate" type="text"
                                            class="validate">
                                        <label for="private_basic_rate">Basic Rate </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_attachment_no" name="private_attachment_no" type="text"
                                            class="validate">
                                        <label for="private_attachment_no">Attachment Number </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_solatium" type="text" class="validate">
                                        <label for="private_solatium">100% Solatium</label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_extra" type="text" class="validate">
                                        <label for="private_extra">Extra 25% </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_total_rate" type="text" class="validate">
                                        <label for="private_total_rate"> Total Rate</label>
                                        <span class="units">m<sup>2</sup></span>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_land_compensation" type="text" class="validate">
                                        <label for="private_land_compensation">Land Compensation </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    <div class="col s12 m4 input-field">
                                        <input id="private_agri_trees" name="private_agri_trees" type="text"
                                            class="validate">
                                        <label for="private_agri_trees"> Agriculture tree nos</label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_agri_tree_rate" name="private_agri_tree_rate" type="text"
                                            class="validate">
                                        <label for="private_agri_tree_rate"> Agriculture tree rate </label>
                                        </label>
                                    </div>
                                    <!-- <div class="col s12 m4 input-field">
                                                <input id="private_agri_tree_compensation" type="text" class="validate">
                                                <label for="private_agri_tree_compensation"> Agriculture tree
                                                    compensation</label>
                                            </div> -->
                                    <!-- </div>
                                    </div> -->
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    <div class="col s12 m4 input-field">
                                        <input id="private_forest_trees" name="private_forest_trees" type="text"
                                            class="validate">
                                        <label for="private_forest_trees">Forest tree nos </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <i class="material-icons prefix center-align">₹</i>
                                        <input id="private_forest_tree_rate" name="private_forest_tree_rate" type="text"
                                            class="validate">
                                        <label for="private_forest_tree_rate"> Forest tree rate </label>
                                        </label>
                                    </div>
                                    <!-- <div class="col s12 m4 input-field">
                                                <input id="private_forest_tree_compensation" type="text"
                                                    class="validate">
                                                <label for="private_forest_tree_compensation"> Forest tree
                                                    compensation</label>
                                            </div> -->
                                    <!-- </div>
                                    </div> -->
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m8 input-field">
                                        <div class="row">
                                            <div class="col s12 m4 input-field">
                                                <input id="private_structure_compensation" type="text" class="validate">
                                                <label for="private_structure_compensation">Structure
                                                    compensation</label>
                                            </div>
                                            <div class="col s12 m4 input-field">
                                                <input id="private_borewell_compensation" type="text" class="validate">
                                                <label for="private_borewell_compensation"> Borewell compensation
                                                </label>
                                            </div>
                                            <div class="col s12 m4 input-field">
                                                <input id="private_total_compensation" type="text" class="validate">
                                                <label for="private_total_compensation"> Total Compensation</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_owner_consent" name="private_owner_consent" type="text"
                                            class="validate datepicker">
                                        <label for="private_owner_consent">Consent from Owner </label>
                                        <button type="button" id="private_owner_consent__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_leagal_search_report" name="private_leagal_search_report"
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
                                        <input id="private_registartion_date" name="private_registartion_date"
                                            type="text" class="validate datepicker">
                                        <label for="private_registartion_date">Date of Registration </label>
                                        <button type="button" id="private_registartion_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_possession_date" name="private_possession_date" type="text"
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
                                        <select class="searchable" id="private_possession_status"
                                            name="private_possession_status">
                                            <option value="0" selected>Select</option>
                                            <option value="yes">Yes</option>
                                            <option value="no">No</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field ">
                                        <input id="private_special_feature" name="private_special_feature" type="text"
                                            class="validate mt-10">
                                        <label for="private_special_feature"> Special Feature </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>
                                <!-- <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_survey_no" type="text" class="validate">
                                        <label for="private_survey_no">Survey Number </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label>Sub Category of Land </label>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div> -->
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <!-- <div class="col s12 m4 input-field">
                                        <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                        <label> Village ID </label>
                                    </div> -->
                                    <div class="col s12 m4 input-field">
                                        <input id="private_forest_tree_survey" name="private_forest_tree_survey"
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
                                        <input id="private_forest_tree_valuation" name="private_forest_tree_valuation"
                                            type="text" class="validate datepicker mt-10">
                                        <label for="private_forest_tree_valuation"> Forest Tree Valuation </label>
                                        <button type="button" id="private_forest_tree_valuation__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> Forest Tree Valuation Status </p>
                                        <select class="searchable" id="private_forest_tree_valuation_status"
                                            name="private_forest_tree_valuation_status">
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
                                        <input id="private_horiculture_tree_survey"
                                            name="private_horiculture_tree_survey" type="text"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_survey"> Horticulture Tree Survey </label>
                                        <button type="button" id="private_horiculture_tree_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_horiculture_tree_valuation"
                                            name="private_horiculture_tree_valuation" type="text"
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
                                        <input id="private_structure_survey" name="private_structure_survey" type="text"
                                            class="validate datepicker">
                                        <label for="private_structure_survey"> Structure Survey </label>
                                        <button type="button" id="private_structure_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_structure_valuation" name="private_structure_valuation"
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
                                        <input id="private_borewell_survey" name="private_borewell_survey" type="text"
                                            class="validate datepicker">
                                        <label for="private_borewell_survey"> Borewell Survey </label>
                                        <button type="button" id="private_borewell_survey__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_borewell_valuation" name="private_borewell_valuation"
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
                                            name="private_horticulture_tree_valuation_status">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> Structure Valuation Status </p>
                                        <select class="searchable" id="private_structure_valuation_status"
                                            name="private_structure_valuation_status">
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
                                        <p class="searchable_label">Borewell Valuation Status </p>
                                        <select class="searchable" id="private_borewell_valuation_status"
                                            name="private_borewell_valuation_status">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label"> RFP to ADTP status </p>
                                        <select class="searchable" id="private_rfp_to_adtp_status"
                                            name="private_rfp_to_adtp_status">
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
                                        <input id="private_rfp_adtp" name="private_rfp_adtp" type="text"
                                            class="validate datepicker">
                                        <label for="private_rfp_adtp">Date of RFP to ADTP </label>
                                        <button type="button" id="private_rfp_adtp__icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_rate_fixation_date" name="private_rate_fixation_date"
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
                                            name="private_sdo_payment_demand_date" type="text"
                                            class="validate datepicker">
                                        <label for="private_sdo_payment_demand_date">SDO demand for payment </label>
                                        <button type="button" id="private_sdo_payment_demand_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_payment_approval_date" name="private_payment_approval_date"
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
                                        <input id="private_payment_amount" name="private_payment_amount" type="text"
                                            class="validate">
                                        <label for="private_payment_amount">Payment Amount </label>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <input id="private_payment_date" name="private_payment_date" type="text"
                                            class="validate datepicker">
                                        <label for="private_payment_date"> Payment Date </label>
                                        <button type="button" id="private_payment_date__icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
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
                                                    <input class="file-path validate" type="text">
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
                                            <div class="col s12 m6 input-field" style="padding-top: 14px;">
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
                                        <button style="width: 100%;" class="btn waves-effect waves-light bg-m">Add /
                                            Edit</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</button>
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
            $('#' + dateId).datepicker();
            $('#' + dateId).datepicker('open');
        });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#textarea1,#textarea2,#issueDesc,#jmremarks').characterCounter();
            // commented code placed next script tag from here

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
            $('input[name=jmApproval]').change(function () {
                forestGovernmentPrivateDivShowOrHide();
            });
            // call show/hide div function on type of land change event
            $('#type_of_land').change(function () {
                forestGovernmentPrivateDivShowOrHide();
            });
            // based on type of land show/hide div
            function forestGovernmentPrivateDivShowOrHide() {
                var jmapproval = $('input[name=jmApproval]:checked').val();
                if (jmapproval == 'accept') {
                    var landtype = $('#type_of_land').val();
                    if (landtype == 'Govt') {
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
                    else {
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#private_div').css("display", "none");
                    }
                }
                else if (jmapproval == 'reject') {
                    $('#govt_div').css("display", "none");
                    $('#forest_div').css("display", "none");
                    $('#private_div').css("display", "none");
                }
            }
        });
    </script>
   
</body>

</html>