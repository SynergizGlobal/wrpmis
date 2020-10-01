<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Contract</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <style>
      
        #example3 .datepicker~button,
        #example6 .datepicker~button,
        #example5 .datepicker~button,
        #example4 .datepicker~button {
            top: 26px;
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

        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
    </style>
</head>

<body>

    <!-- header  starts-->
             <jsp:include page="../layout/header.jsp"></jsp:include>

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Edit Contract</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">

                            <div class="row">

                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Project ID</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Work Name</label>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">

                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Department</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <label class="primary-text-bold">Contract ID :</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="work_name" type="text" class="validate">
                                    <label for="work_name">Contract Name</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Contract Type</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Contractor ID</label>
                                </div>
                               <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m8 input-field">
                                    <input id="scope_contract" type="text" class="validate">
                                    <label for="scope_contract">Scope of Contract</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 ">
                                    <div class="row">
                                        <div class="col s12 m6 input-field">
                                            <select>
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                            <label>HOD</label>
                                        </div>
                                        <div class="col s12 m6 input-field">
                                            <select>
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                            <label>Dy HOD</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                                 <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input name="doc" id="doc" type="text" class="validate datepicker">
                                    <label for="doc">Planned DOC</label>
                                     <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="awarded_cost" name="awarded_cost" type="text" class="validate">
                                    <label for="awarded_cost">Awarded cost</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                               <div class="col m2 hide-on-small-only"></div>
                               <div class="col s12 m4 input-field">
                                   <input id="start_date" type="text" class="validate datepicker">
                                   <label for="start_date">Date of Start</label>
                                   <button type="button" id="start_date_icon"><i class="fa fa-calendar"></i></button>
                               </div>
                               <div class="col s12 m4 input-field">
                                   <i class="material-icons prefix center-align">₹</i>
                                   <input id="estimated_cost" type="text" class="validate">
                                   <label for="estimated_cost">estimated cost</label>
                               </div>
                               <div class="col m2 hide-on-small-only"></div>
                           </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="loa_no" type="text" class="validate">
                                    <label for="loa_no">LOA Letter No</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="loa_date" type="text" class="validate datepicker">
                                    <label for="loa_date">LOA Date</label>
                                    <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="ca_no" type="text" class="validate">
                                    <label for="ca_no">CA No</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="ca_date" type="text" class="validate datepicker">
                                    <label for="ca_date">CA Date</label>
                                    <button type="button" id="ca_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="co_date" type="text" class="validate datepicker">
                                    <label for="co_date">Actual Completed Date</label>
                                    <button type="button" id="co_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                <i class="material-icons prefix center-align">₹</i>
                                    <input id="completed_cost" name="completed_cost" type="text" class="validate">
                                    <label for="completed_cost">Completed Cost</label>
                                </div>                              
                            </div>
                             <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="contract_closure_date" type="text" class="validate datepicker">
                                    <label for="contract_closure_date">Contract Closure</label>
                                    <button type="button" id="contract_closure_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="completion_certificate_date" type="text" class="validate datepicker">
                                    <label for="completion_certificate_date">Release of Completion Certificate</label>
                                    <button type="button" id="completion_certificate_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_takeover_client" type="text" class="validate datepicker">
                                    <label for="final_takeover_client">Final Taking over by Client</label>
                                    <button type="button" id="final_takeover_client_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_bill_release" type="text" class="validate">
                                    <label for="final_bill_release">Release of Final bill</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="defect_liability_period" type="text" class="validate">
                                    <label for="defect_liability_period">Defect Liability Period</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_retention_release" type="text" class="validate">
                                    <label for="final_retention_release"> Release of Final Retention amount/BG</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="release_of_pbg" type="text" class="validate">
                                    <label for="release_of_pbg">Release of PBG</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="contract_closure" type="text" class="validate">
                                    <label for="contract_closure"> Contract Closure</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Status of Contract</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 input-field center-align no-float-small">
                                    <p>Bank Guarantee Required</p>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="bank_guarantee" type="radio" checked
                                                value="yes" />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="bank_guarantee" type="radio" value="no" />
                                            <span>No</span>
                                        </label>
                                    </p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <!-- bg show hide div  -->
                            <div class="row fixed-width" id="bank_guarantee_div">
                                <h5 class="center-align">Bank Guarantee Details</h5>
                                <div class="table-inside">
                                    <table id="example5" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>BG Type </th>
                                                <th>Issuing Bank </th>
                                                <th>Bank Address </th>
                                                <th>BG Number </th>
                                                <th>BG Value </th>
                                                <th>Valid Upto </th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>                                               
                                                <td> <input id="bg_type" type="text" class="validate"
                                                        placeholder="BG Type">
                                                </td>
                                                <td>
                                                    <input id="issuing_bank" type="text" class="validate"
                                                        placeholder="Issuing Bank">
                                                </td>
                                                <td>
                                                    <input id="bank_address" type="text" class="validate"
                                                        placeholder="Bank Address">
                                                </td>
                                                <td>
                                                    <input id="bg_no" type="text" class="validate"
                                                        placeholder="BG Number">
                                                </td>
                                                <td>
                                                    <input id="bg_val" type="text" class="validate"
                                                        placeholder="BG Value">
                                                </td>
                                                <td>
                                                    <input id="bg_upto_valid" type="text" class="validate datepicker"
                                                        placeholder="Valid Upto">
                                                    <button type="button" id="bg_upto_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarks_bg" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-m t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 input-field center-align no-float-small">
                                    <p>Insurance Required</p>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="insurance" type="radio" checked value="yes" />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="insurance" type="radio" value="no" />
                                            <span>No</span>
                                        </label>
                                    </p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <!-- insurance show hide div  -->
                            <div class="row fixed-width" id="insurance_div">
                                <h5 class="center-align">Insurance Details</h5>
                                <div class="table-inside">
                                    <table id="example6" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Insurance Type </th>
                                                <th>Issuing Agency </th>
                                                <th>Agency Address </th>
                                                <th>Insurance Number </th>
                                                <th>Insurance Value </th>
                                                <th>Valid Upto </th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <select>
                                                        <option value="0" selected>Select
                                                        </option>
                                                        <option value="1">Agency 1</option>
                                                        <option value="2">Agency 2</option>
                                                        <option value="3">Agency 3</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="issuing_agency" type="text" class="validate"
                                                        placeholder="Issuing Agency">
                                                </td>
                                                <td>
                                                    <input id="agency_address" type="text" class="validate"
                                                        placeholder="Agency Address                                                        ">
                                                </td>

                                                <td>
                                                    <input id="insurance_no" type="text" class="validate"
                                                        placeholder="Insurance Number">
                                                </td>
                                                <td>
                                                    <input id="insurance_val" type="text" class="validate"
                                                        placeholder="Insurance Value">
                                                </td>
                                                <td>
                                                    <input id="insurance_upto_valid" type="text"
                                                        class="validate datepicker" placeholder="Valid Upto">
                                                    <button type="button" id="insurance_upto_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarks_insurance" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-m t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>

                            <div class="row fixed-width">
                                <h5 class="center-align">Milestone Details</h5>
                                <div class="table-inside">
                                    <table id="example4" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Milestone Name </th>
                                                <th>Milestone Date </th>
                                                <th>Actual Date </th>
                                                <th>Revision</th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input id="milestone_name" type="text" class="validate"
                                                        placeholder="Milestone Name ">
                                                </td>
                                                <td>
                                                    <input id="milestone_date" type="text" class="validate datepicker"
                                                        placeholder="Milestone Date">
                                                    <button type="button" id="milestone_date_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="actual_date" type="text" class="validate datepicker"
                                                        placeholder="Actual Date">
                                                    <button type="button" id="actual_date_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="revision" type="text" class="validate"
                                                        placeholder="Revision">
                                                </td>
                                                <td>
                                                    <input id="completed_cost" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-m t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <div class="row fixed-width">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">

                                    <table id="example3" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Revision Number </th>
                                                <th>Revised Amount </th>
                                                <th>Revised DOC </th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td> <input id="revision_no" type="text" class="validate"
                                                        placeholder="Revision Number">
                                                </td>
                                                <td>
                                                    <input id="revised_amount" type="text" class="validate"
                                                        placeholder="Revised Amount">
                                                </td>
                                                <td>
                                                    <input id="revised_doc" type="text" class="validate datepicker"
                                                        placeholder="Revised DOC">
                                                    <button type="button" id="revised_doc_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="completed_cost" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-m t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col m8 s12">
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
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea1">Remarks</label>
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
                                        <a href="<%=request.getContextPath()%>/contract"class="btn waves-effect waves-light bg-s"
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
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('.sidenav').sidenav();
            $('.modal').modal();
            $('#textarea1,#textarea2,#textarea3').characterCounter();
            // $(".datepicker").datepicker();
            $("#loa_date").datepicker();
            $("#ca_date,#co_date").datepicker();
            $("#bg_upto_valid,#insurance_upto_valid").datepicker();
            $("#milestone_date,#actual_date,#revised_doc").datepicker();

            $('#sactioned_year').datepicker({
                format: 'yyyy',
                selectYears: true,
                selectMonths: true,
                selectDays: false,
                onSelect: function (arg) {
                    var selectedYear = parseInt(arg.getFullYear());
                    var selectedMonth = parseInt(arg.getMonth() + 1);
                    var year = (selectedMonth >= 4) ? selectedYear + '-' + (selectedYear + 1) : (selectedYear - 1) + '-' + selectedYear;
                    console.log('sactioned_year : ' + year);

                }
            });
            $('#loa_date_icon').click(function () {
                event.stopPropagation();
                $('#loa_date').click();
            });
            $('#ca_date_icon').click(function () {
                event.stopPropagation();
                $('#ca_date').click();
            });
            $('#co_date_icon').click(function () {
                event.stopPropagation();
                $('#co_date').click();
            });
            $('#bg_upto_valid_icon').click(function () {
                event.stopPropagation();
                $('#bg_upto_valid').click();
            });
            $('#insurance_upto_valid_icon').click(function () {
                event.stopPropagation();
                $('#insurance_upto_valid').click();
            });
            $('#milestone_date_icon').click(function () {
                event.stopPropagation();
                $('#milestone_date').click();
            });
            $('#actual_date_icon').click(function () {
                event.stopPropagation();
                $('#actual_date').click();
            });
            $('#revised_doc_icon').click(function () {
                event.stopPropagation();
                $('#revised_doc').click();
            });
            $('#bg_upto_icon').click(function () {
                event.stopPropagation();
                $('#bg_upto').click();
            });

            $('#insurance_upto_icon').click(function () {
                event.stopPropagation();
                $('#insurance_upto').click();
            });

            $('#contract_closure_date,#completion_certificate_date,#final_takeover_client,#start_date').datepicker();
            $('#start_date_icon').click(function () {
                event.stopPropagation();
                $('#start_date').click();
            });
            $('#contract_closure_date_icon').click(function () {
                event.stopPropagation();
                $('#contract_closure_date').click();
            });
            $('#completion_certificate_date_icon').click(function () {
                event.stopPropagation();
                $('#completion_certificate_date').click();
            });
            $('#final_takeover_client_icon').click(function () {
                event.stopPropagation();
                $('#final_takeover_client').click();
            });
           
            // show or hide based on bg 
            $('input[name="bank_guarantee"]').change(function () {
                var radioval = $('input[name="bank_guarantee"]:checked').val();
                if (radioval == 'yes') {
                    $('#bank_guarantee_div .btn').removeClass('disabled');
                    $('#bank_guarantee_div input').prop("disabled", false);
                    // .css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#bank_guarantee_div .btn').addClass('disabled');
                    $('#bank_guarantee_div input').prop("disabled", true);
                }
            });
            // show or hide based on insurance 

            $('input[name="insurance"]').change(function () {
                var radioval = $('input[name="insurance"]:checked').val();
                if (radioval == 'yes') {
                    $('#insurance_div .btn').removeClass('disabled');
                    $('#insurance_div input').prop("disabled", false);
                }
                else if (radioval == 'no') {
                    $('#insurance_div .btn').addClass('disabled');
                    $('#insurance_div input').prop("disabled", true);
                }
            });
        });
    </script>

</body>

</html>