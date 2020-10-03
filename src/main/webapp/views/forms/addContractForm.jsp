<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Contract</title>
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
        /* #bank_guarantee_div,
        #insurance_div {
            display: none;
        } */

        #ravTable .datepicker~button,
        #insurenceTable .datepicker~button,
        #bankTable .datepicker~button,
        #mileTable .datepicker~button {
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
        .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;}
	    @media only screen and (max-width: 600px) {
          .no-float-small {
              float: none !important;
          }
     	 }
     	 .my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		#newButton{
			position: relative;
			float: right;
			right: 24px;
			top: 5px;
		}
		#newButton2{
			position: relative;
			float: right;
			right: -34px;
			top: 5px;
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
                                <h6>Add Contract</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="add-Contract" id="contractForm" name="contractForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                    onchange="getWorksList(this.value);">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" >${ obj.project_id}</option>
                                         </c:forEach>
                                    </select>
                                    <label>Project ID</label>
                                     <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk">
                                        <option value="" selected>Select</option>
                                    </select>
                                    <label>Work Name</label>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select name="department_fk" id="department_fk" class="validate-dropdown">
                                        <option value="">Select</option>
                                          <c:forEach var="obj" items="${departmentList }">
                                      	    <option value= "${ obj.department_fk}" >${ obj.department_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <label>Department</label>
                                      <span id="department_fkError" class="error-msg" ></span>
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
                                    <input name="contract_name" id="contract_name" type="text" class="validate" >
                                    <label for="contract_name">Contract Name</label>
                                      <span id="contract_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                    <select name="contract_type_fk" id="contract_type_fk" class="validate-dropdown">
                                        <option value="" selected>Select</option>
                                       	   <c:forEach var="obj" items="${contract_type }">
		                                     <option value="${obj.contract_type_fk }" >${obj.contract_type_fk }</option>
		                                   </c:forEach>
                                    </select>
                                    <label>Contract Type</label>
                                     <span id="contract_type_fkError" class="error-msg" ></span>
                                    
                                </div>
                                
                                <div class="col s12 m4 input-field">
                                    <select name="contractor_id_fk" id="contractor_id_fk" class="validate-dropdown">
                                        <option value="" selected>Select</option>
                                       	    <c:forEach var="obj" items="${contractor }">
		                                      <option value="${obj.contractor_id_fk }" >${obj.contractor_id_fk }</option>
		                                    </c:forEach>
                                    </select>
                                    <label>Contractor ID</label>
                                    <span id="contractor_id_fkError" class="error-msg" ></span>
                                </div>                             
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m8 input-field">
                                    <input id="scope_of_contract" name="scope_of_contract" type="text" class="validate">
                                    <label for="scope_of_contract">Scope of Contract</label>
                                 <span id="scope_of_contractError" class="error-msg" ></span>
                                    
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <div class="row">
                                <!-- //row 9 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 ">
                                    <div class="row">

                                        <div class="col s12 m6 input-field">
                                            <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown">
                                      		  <option value="" selected>Select</option>
                                                <c:forEach var="obj" items="${hodList }">
		                                    	  <option value="${obj.user_id }" >${obj.user_id }</option>
		                                        </c:forEach>
                                            </select>
                                            <label>HOD</label>
                                            <span id="hod_user_id_fkError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m6 input-field">
                                            <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown">
                                                <option value="" selected>Select</option>
                                                <c:forEach var="obj" items="${hodList }">
		                                    	  <option value="${obj.user_id }" >${obj.user_id }</option>
		                                     	 </c:forEach>
                                            </select>
                                            <label>Dy HOD</label>
                                            <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- //row 7 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input name="doc" id="doc" type="text" class="validate datepicker">
                                    <label for="doc">Planned DOC</label>
                                    <span id="docError" class="error-msg" ></span>
                                     <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>
                                    <input id="awarded_cost" name="awarded_cost" type="text" class="validate">
                                    <label for="awarded_cost">Awarded cost</label>
                                    <span id="awarded_costError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                                <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="start_date" name="date_of_start" type="text" class="validate datepicker">
                                    <label for="start_date">Date of Start</label>
                                     <span id="date_of_startError" class="error-msg" ></span>
                                    <button type="button" id="start_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="estimated_cost" name="estimated_cost" type="text" class="validate">
                                    <label for="estimated_cost">estimated cost</label>
                                     <span id="estimated_costError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="loa_letter_number" name="loa_letter_number" type="text" class="validate">
                                    <label for="loa_letter_number">LOA Letter No</label>
                                    <span id="loa_letter_numberError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="loa_date" name="loa_date" type="text" class="validate datepicker">
                                    <label for="loa_date">LOA Date</label>
                                     <span id="loa_dateError" class="error-msg" ></span>
                                    <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="ca_no" name="ca_no" type="text" class="validate">
                                    <label for="ca_no">CA No</label>
                                    <span id="ca_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="ca_date" name="ca_date" type="text" class="validate datepicker">
                                    <label for="ca_date">CA Date</label>
                                    <span id="ca_dateError" class="error-msg" ></span>
                                    <button type="button" id="ca_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker">
                                    <label for="actual_completion_date">Actual Completed Date</label>
                                     <span id="actual_completion_dateError" class="error-msg" ></span>
                                    <button type="button" id="co_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>
                                    <input id="completed_cost" name="completed_cost" type="text" class="validate">
                                    <label for="completed_cost">Completed Cost</label>
                                      <span id="completed_costError" class="error-msg" ></span>
                                </div>                              
                            </div> 
      						<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field"> 
                                    <input id="contract_closure_date" name="contract_closure_date" type="text" class="validate datepicker">
                                    <label for="contract_closure_date">Contract Closure</label>
                                    <span id="contract_closure_dateError" class="error-msg" ></span>
                                    <button type="button" id="contract_closure_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="completion_certificate_date" name="completion_certificate_release" type="text" class="validate datepicker">
                                    <label for="completion_certificate_date">Release of Completion Certificate</label>
                                     <span id="completion_certificate_dateError" class="error-msg" ></span>
                                    <button type="button" id="completion_certificate_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_takeover_client" name="final_takeover" type="text" class="validate datepicker">
                                    <label for="final_takeover_client">Final Taking over by Client</label>
                                    <span id="final_takeover_clientError" class="error-msg" ></span>
                                    <button type="button" id="final_takeover_client_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_bill_release" name="final_bill_release" type="text" class="validate">
                                    <label for="final_bill_release">Release of Final bill</label>
                                    <span id="final_bill_releaseError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="defect_liability_period" name="defect_liability_period" type="text" class="validate">
                                    <label for="defect_liability_period">Defect Liability Period</label>
                                    <span id="defect_liability_periodError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_retention_release" name="retention_money_release" type="text" class="validate">
                                    <label for="final_retention_release"> Release of Final Retention amount/BG</label>
                                     <span id="final_retention_releaseError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="release_of_pbg" name="pbg_release" type="text" class="validate">
                                    <label for="release_of_pbg">Release of PBG</label>
                                    <span id="release_of_pbgError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="contract_closure" name="contract_closure" type="text" class="validate">
                                    <label for="contract_closure"> Contract Closure Comment</label>
                                    <span id="ontract_closureError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="validate-dropdown" id="contract_status_fk" name="contract_status_fk">
                                        <option value="" selected>Select</option>
                                       		 <c:forEach var="obj" items="${contract_Statustype }">
		                                    	 <option value="${obj.contract_status_fk }" >${obj.contract_status_fk }</option>
		                                      </c:forEach>
                                    </select>
                                    <label>Status of Contract</label>
                                    <span id="contract_status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 input-field center-align no-float-small">
                                    <p>Bank Guarantee Required</p>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio" checked
                                                value="yes" />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio" value="no" />
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
                                    <table id="bankTable" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <!-- <th>Contract ID </th> -->
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
                                        <tbody id="bankTableBody">
                                            <tr id="bankRow${index.count }">
                                                <td> <select id="bg_type_fks${index.count }" name="bg_type_fks" >
                                                        <option value="" selected>Select </option>
                                                         <c:forEach var="obj" items="${bankGuaranteeTYpe }">
		                                    			   <option value="${obj.bg_type_fk }" >${obj.bg_type_fk }</option>
		                                     			  </c:forEach>
                                                    </select>
                                                </td> 
                                                <!-- <td> <input id="bg_type" type="text" class="validate"
                                                        placeholder="BG Type">
                                                </td> -->
                                                <td>
                                                    <input id="issuing_banks${index.count }" name="issuing_banks"  type="text" class="validate"
                                                        placeholder="Issuing Bank">
                                                </td>
                                                <td>
                                                    <input id="bank_addresss${index.count }" name ="bank_addresss" type="text" class="validate"
                                                        placeholder="Bank Address">
                                                </td>
                                                <td>
                                                    <input id="bg_numbers${index.count }" name="bg_numbers" type="text" class="validate"
                                                        placeholder="BG Number">
                                                </td>
                                                <td>
                                                    <input id="bg_values${index.count }" name="bg_values" type="text" class="validate"
                                                        placeholder="BG Value">
                                                </td>
                                                <td>
                                                    <input id="bg_valid_uptos${index.count }" name="bg_valid_uptos" type="text" class="validate datepicker"
                                                        placeholder="Valid Upto">
                                                    <button type="button" id="bg_upto_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarkss${index.count }" name ="remarkss" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a onclick="removeBank('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                      <table class="mdl-data-table">
                                        <tbody id="bankTableBody">                                          
                                            <tr>
									 <td colspan="9" style="text-align: right;"> <a   class="btn waves-effect waves-light bg-m t-c "  onclick="addBankRow()"> <i
                                                            class="fa fa-plus"></i></a></td>
                                         </tr>
                                        </tbody>
                                    </table>
													<input type="hidden" id="bankRowNo"  name="bankRowNo" value="0" />                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 input-field center-align no-float-small">
                                    <p>Insurance Required</p>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="insurance_required" type="radio" checked value="yes" />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="insurance_required" type="radio" value="no" />
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
                                    <table id="insurenceTable" class="mdl-data-table">
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
                                         <tbody id="insurenceTableBody">
                                        
                                            <tr id="insurenceRow${index.count }">
                                                <td>
                                                    <select id="insurance_type_fks${index.count }" name="insurance_type_fks">
                                                        <option value="" selected>Select</option>
                                                          <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" >${ obj.insurance_type}</option>
                                        				  </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="issuing_agencys${index.count }" name="issuing_agencys" type="text" class="validate" 
                                                        placeholder="Issuing Agency">
                                                </td>
                                                <td>
                                                    <input id="agency_addresss${index.count }" name="agency_addresss" type="text" class="validate" 
                                                        placeholder="Agency Address">
                                                </td>

                                                <td>
                                                    <input id="insurance_numbers${index.count }" name="insurance_numbers" type="text" class="validate" 
                                                        placeholder="Insurance Number">
                                                </td>
                                                <td>
                                                    <input id="insurance_values${index.count }" name="insurance_values" type="text" class="validate" 
                                                        placeholder="Insurance Value">
                                                </td>
                                                <td>
                                                    <input id="insurence_valid_uptos${index.count }" name="insurence_valid_uptos" type="text" 
                                                        class="validate datepicker" placeholder="Valid Upto">
                                                    <button type="button" id="insurance_upto_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="insurence_remarks${index.count }" name="insurence_remarks"  type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a onclick="removeInsurence('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                           
                                        </tbody>
                                    </table>
                                     <table  class="mdl-data-table">
                                        <tbody id="insurenceTableBody">                                          
                                            <tr>
                                   <td colspan="8" style="text-align: right;" ><a   class="btn waves-effect waves-light bg-m t-c "  onclick="addInsurenceRow()"> <i class="fa fa-plus"></i></a></td>
                                             </tr>
                                        </tbody>
                                    </table>
                                    <input type="hidden" id="insurenceRowNo"  name="insurenceRowNo" value="0" />

                                </div>
                            </div>

                            <div class="row fixed-width">
                                <h5 class="center-align">Milestone Details</h5>
                                <div class="table-inside">
                                    <table id="mileTable" class="mdl-data-table">
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
                                         <tbody id="milestoneTableBody" >
                                        
                                            <tr id="mileRow${index.count }">                                    
                                                <td>
                                                    <input id="milestone_names${index.count }" name="milestone_names" type="text" class="validate" 
                                                        placeholder="Milestone Name ">
                                                </td>
                                                <td>
                                                    <input id="milestone_dates${index.count }" name="milestone_dates" type="text" class="validate datepicker" 
                                                        placeholder="Milestone Date">
                                                    <button type="button" id="milestone_date_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="actual_dates${index.count }" name="actual_dates" type="text" class="validate datepicker" 
                                                        placeholder="Actual Date">
                                                    <button type="button" id="actual_date_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="revisions${index.count }" name="revisions" type="text" class="validate" 
                                                        placeholder="Revision">
                                                </td>
                                                <td>
                                                    <input id="mile_remarks${index.count }" name="mile_remarks" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td><a onclick="removeMilestone('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
  												<table class="mdl-data-table">
                                        <tbody id="milestoneTableBody">                                          
                                            <tr>
  										<td colspan="6" style="text-align: right;" ><a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addMilestoneRow()"> <i
                                                            class="fa fa-plus"></i></a></td> 
                                             </tr>
                                        </tbody>
                                    </table>
                                    <input type="hidden" id="mileRowNo"  name="mileRowNo" value="0" />
                                </div>
                            </div>
                            <div class="row fixed-width">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">

                                    <table id="ravTable" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Revision Number </th>
                                                <th>Revised Amount </th>
                                                <th>Revised DOC </th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                         <tbody  id="revTableBody" >
                                            <tr id="revRow${index.count }">
                                                <td> <input id="revision_numbers${index.count }" name="revision_numbers" type="text" class="validate" 
                                                        placeholder="Revision Number">
                                                </td>
                                                <td>
                                                    <input id="revised_amounts${index.count }" name="revised_amounts" type="text" class="validate"
                                                        placeholder="Revised Amount">
                                                </td>
                                                <td>
                                                    <input id="revised_docs${index.count }" name="revised_docs" type="text" class="validate datepicker" 
                                                        placeholder="Revised DOC">
                                                    <button type="button" id="revised_doc_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td> 
                                                    <input id="revision_remarks${index.count }" name="revision_remarks" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td><a onclick="removeRev('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <table class="mdl-data-table">
                                        <tbody id="revTableBody">                                          
                                            <tr>
												<td colspan="5" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addRevRow()"> <i
                                                            class="fa fa-plus"></i></a></td>
                                              </tr>
                                        </tbody></table>
                                   	 <input type="hidden" id="revRowNo"  name="revRowNo" value="0" />
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
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="remarks">Remarks</label>
                                      <span id="remarksError" class="error-msg"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="button" style="width: 100%;" onclick="addContract();" class="btn waves-effect waves-light bg-m">Add</button>
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
            $('#remarks').characterCounter();
            // $(".datepicker").datepicker();
            $("#loa_date").datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
   	    	     $('.confirmation-btns .datepicker-done').click();
   	    	  } });
            $("#doc").datepicker({
           	 format:'dd-mm-yyyy',
               onSelect: function () {
  	    	     $('.confirmation-btns .datepicker-done').click();
  	    	  } });
            
            $("#actual_completion_date").datepicker({
           	 format:'dd-mm-yyyy',
               onSelect: function () {
   	    	     $('.confirmation-btns .datepicker-done').click();
   	    	  }
          });
            $("#ca_date").datepicker({
           	 format:'dd-mm-yyyy',
               onSelect: function () {
   	    	     $('.confirmation-btns .datepicker-done').click();
   	    	  }
          });
            $("#insurence_valid_uptos").datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            });
            $("#bg_valid_uptos").datepicker({
           	 format:'dd-mm-yyyy',
               onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           });
            $("#milestone_dates").datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            });
            $("#actual_dates").datepicker({
           	 format:'dd-mm-yyyy',
               onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           });
            $("#revised_docs").datepicker({
           	 format:'dd-mm-yyyy',
               onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
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

            $('#contract_closure_date,#completion_certificate_date,#final_takeover_client,#start_date').datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           });
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
            $('input[name="bg_required"]').change(function () {
                var radioval = $('input[name="bg_required"]:checked').val();
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

            $('input[name="insurance_required"]').change(function () {
                var radioval = $('input[name="insurance_required"]:checked').val();
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
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                var globalWorkId = "${sessionScope.globalWorkId}";
                                if ($.trim(globalWorkId) != '' && val.work_id == $.trim(globalWorkId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('select').formSelect();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function addContract(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	    		
    			document.getElementById("contractForm").submit();			
    	 	}
    	}
        var validator = $('#contractForm').validate({
        	
   		 errorClass: "my-error-class",
   		   validClass: "my-valid-class",
   	ignore: ":hidden:not(.validate-dropdown)",
   	   rules: {
   			  "project_id_fk": {
   			 	required: true
   			  },"work_id_fk": {
   		 		required: true
   		 	  },"department_fk": {
   		 		required: true
   		 	  },"contract_name": {
   		 		required: true
   		 	  },"contract_type_fk": {
   		 		required: true
   		 	  },"contractor_id_fk": {
   	 		    required: true,
   	 	   	  },"scope_of_contract": {
   	 		    required: true,
   	 	   	  },"hod_user_id_fk": {
   		 		required: true
   		 	  },"dy_hod_user_id_fk": {
   	 		    required: true
   	 	   	  },"doc": {
   		 		required: true
   		 	  },"awarded_cost": {
   		 		required: true
   		 	  },"date_of_start": {
   		 		required: true
   		 	  },"estimated_cost": {
   		 		required: true
   		 	  },"loa_letter_number": {
   		 		required: true
   		 	  },"loa_date":{
   		 		 required: true
   		 	  },"ca_no": {
   	 		    required: true,
   	 	   	  },"ca_date": {
   		 		required: true
   		 	  },"actual_completion_date": {
   	 		    required: true
   	 	   	  },"completed_cost": {
   		 		required: true
   		 	  },"contract_closure_date": {
   		 		required: true
   		 	  },"completion_certificate_release":{
   		 		 required: true
   		 	  },"final_takeover": {
   	 		    required: true,
   	 	   	  },"final_bill_release": {
   		 		required: true
   		 	  },"defect_liability_period": {
   	 		    required: true
   	 	   	  },"retention_money_release": {
   		 		required: true
   		 	  },"pbg_release":{
   		 		 required: true
   		 	  },"contract_closure":{
   		 		 required: true
   		 	  },"contract_status_fk":{
   		 		 required: true
   		 	  },"remarks":{
 		 		 required: true
		 	  }
   		 				
   	 	},
   	   messages: {
   			 "project_id_fk": {
   		 			required: 'Required'
   		 	  	 },"work_id_fk": {
   	 			required: 'Required'
   	 	  	 },"department_fk": {
   	 			required: 'Required'
   	 	  	 },"contract_name": {
   	 			required: 'Required'
   	 	  	 },"contract_type_fk": {
   	 			required: 'Required'
   	 	  	 },"contractor_id_fk": {
   	 			required: 'Required'
   	 	  	 },"scope_of_contract": {
   	 	  		required: 'Required'
   	 	   	  	 },"hod_user_id_fk": {
   	 			required: 'Required'
   	 	  	 },"dy_hod_user_id_fk": {
   	 			required: 'Required'
   	 	  	 },"doc": {
   	 			required: 'Required'
   	 	  	 },"awarded_cost": {
		 		required: 'Required'
		 	  },"date_of_start": {
		 		required: 'Required'
		 	  },"estimated_cost": {
		 		required: 'Required'
		 	  },"loa_letter_number": {
		 		required: 'Required'
		 	  },"loa_date":{
		 		 required: 'Required'
		 	  },"ca_no": {
	 		    required: 'Required',
	 	   	  },"ca_date": {
		 		required: 'Required'
		 	  },"actual_completion_date": {
	 		    required: 'Required'
	 	   	  },"completed_cost": {
		 		required: 'Required'
		 	  },"contract_closure_date": {
		 		required: 'Required'
   		 	  },"completion_certificate_release":{
   		 		 required: 'Required'
   		 	  },"final_takeover": {
   		 		 required: 'Required'
   	 	   	  },"final_bill_release": {
   	 	   		required: 'Required'
   		 	  },"defect_liability_period": {
   		 		 required: 'Required'
   	 	   	  },"retention_money_release": {
   	 	   		required: 'Required'
   		 	  },"pbg_release":{
   		 		 required: 'Required'
   		 	  },"contract_closure":{
   	 	  		required: 'Required'
   		 	  },"contract_status_fk":{
   	 	  		required: 'Required'
   		 	  },"remarks":{
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
   	 	    }else if (element.attr("id") == "department_fk" ){
   	 		     document.getElementById("department_fkError").innerHTML="";
   	 			 error.appendTo('#department_fkError');
   	 	    }else if (element.attr("id") == "contract_name" ){
   	 		     document.getElementById("contract_nameError").innerHTML="";
   	 			 error.appendTo('#contract_nameError');
   	 	    }else if (element.attr("id") == "contract_type_fk" ){
   	 		     document.getElementById("contract_type_fkError").innerHTML="";
   	 			 error.appendTo('#contract_type_fkError');
   	 	    }else if (element.attr("id") == "contractor_id_fk" ){
   	 		     document.getElementById("contractor_id_fkError").innerHTML="";
   	 			 error.appendTo('#contractor_id_fkError');
   	 	    }else if (element.attr("id") == "scope_of_contract" ){
   	 		     document.getElementById("scope_of_contractError").innerHTML="";
   	 			 error.appendTo('#scope_of_contractError');
   	 	    }else if (element.attr("id") == "hod_user_id_fk" ){
   	 		     document.getElementById("hod_user_id_fkError").innerHTML="";
   	 			 error.appendTo('#hod_user_id_fkError');
   	 	    }else if (element.attr("id") == "dy_hod_user_id_fk" ){
   	 		     document.getElementById("dy_hod_user_id_fkError").innerHTML="";
   	 			 error.appendTo('#dy_hod_user_id_fkError');
   	 	    }else if (element.attr("name") == "doc" ){
   	 		     document.getElementById("docError").innerHTML="";
   	 			 error.appendTo('#docError');
   	 	    }else if (element.attr("id") == "awarded_cost" ){
   	 		     document.getElementById("awarded_costError").innerHTML="";
   	 			 error.appendTo('#awarded_costError');
   	 	    }else if (element.attr("id") == "start_date" ){
   	 	    	     document.getElementById("date_of_startError").innerHTML="";
   	 			     error.appendTo('#date_of_startError');
	 	    }else if (element.attr("id") == "estimated_cost" ){
	 		     document.getElementById("estimated_costError").innerHTML="";
	 			 error.appendTo('#estimated_costError');
	 	    }else if (element.attr("id") == "loa_letter_number" ){
	 		     document.getElementById("loa_letter_numberError").innerHTML="";
	 			 error.appendTo('#loa_letter_numberError');
	 	    }else if (element.attr("id") == "loa_date" ){
	 		     document.getElementById("loa_dateError").innerHTML="";
	 			 error.appendTo('#loa_dateError');
	 	    }else if (element.attr("id") == "ca_no" ){
	 		     document.getElementById("ca_noError").innerHTML="";
	 			 error.appendTo('#ca_noError');
	 	    }else if (element.attr("id") == "ca_date" ){
	 		     document.getElementById("ca_dateError").innerHTML="";
	 			 error.appendTo('#ca_dateError');
	 	    }else if (element.attr("id") == "actual_completion_date" ){
	 		     document.getElementById("actual_completion_dateError").innerHTML="";
	 			 error.appendTo('#actual_completion_dateError');
	 	    }else if (element.attr("id") == "completed_cost" ){
	 		     document.getElementById("completed_costError").innerHTML="";
	 			 error.appendTo('#completed_costError');
	 	    }else if (element.attr("id") == "contract_closure_date" ){
	 		     document.getElementById("contract_closure_dateError").innerHTML="";
	 			 error.appendTo('#contract_closure_dateError');
	 	    }else if (element.attr("id") == "completion_certificate_date" ){
	 		     document.getElementById("completion_certificate_dateError").innerHTML="";
	 			 error.appendTo('#completion_certificate_dateError');
	 	    }else if (element.attr("id") == "final_takeover_client" ){
	 		     document.getElementById("final_takeover_clientError").innerHTML="";
	 			 error.appendTo('#final_takeover_clientError');
	 	    }else if (element.attr("id") == "final_bill_release" ){
	 		     document.getElementById("final_bill_releaseError").innerHTML="";
	 			 error.appendTo('#final_bill_releaseError');
	 	    }else if (element.attr("id") == "defect_liability_period" ){
	 		     document.getElementById("defect_liability_periodError").innerHTML="";
	 			 error.appendTo('#defect_liability_periodError');
	 	    }else if (element.attr("id") == "final_retention_release" ){
	 		     document.getElementById("final_retention_releaseError").innerHTML="";
	 			 error.appendTo('#final_retention_releaseError');
	 	    }else if (element.attr("name") == "pbg_release" ){
	 		     document.getElementById("release_of_pbgError").innerHTML="";
	 			 error.appendTo('#release_of_pbgError');
	 	    }else if (element.attr("id") == "contract_closure" ){
	 		     document.getElementById("ontract_closureError").innerHTML="";
	 			 error.appendTo('#ontract_closureError');
	 	    }else if (element.attr("id") == "contract_status_fk" ){
 		     document.getElementById("contract_status_fkError").innerHTML="";
 			 error.appendTo('#contract_status_fkError');
   	 	    }else if (element.attr("id") == "remarks" ){
  	 		     document.getElementById("remarksError").innerHTML="";
	 			 error.appendTo('#remarksError');}
   	 },submitHandler: function(form) {
   	    // do other things for a valid form
   	    //form.submit();
   	    //return true;
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
   function addBankRow(){
		
       var rowNo = $("#bankRowNo").val();
       var rNo = Number(rowNo)+1;
       var total = 0;
       var html = '<tr id="bankRow'+rNo+'"><td> <div>'
		   +'<select  name="bg_type_fks" id="bg_type_fks'+rNo+'"  >'	   			
		   +'<option value="" >select</option>'
		 	<c:forEach var="obj" items="${bankGuaranteeTYpe }">
		  +'<option value="${obj.bg_type_fk }">${obj.bg_type_fk }</option>'
			</c:forEach>
		   +'</select></div></td>'
		   +'<td> <input id="issuing_banks'+rNo+'" name="issuing_banks"  type="text" class="validate"  placeholder="Issuing Bank"></td>'
		   +'<td><input id="bank_addresss'+rNo+'" name ="bank_addresss" type="text" class="validate"  placeholder="Bank Address"></td>'
		   +'<td><input id="bg_numbers'+rNo+'" name="bg_numbers" type="text" class="validate"  placeholder="BG Number"></td>'
		   +'<td><input id="bg_values'+rNo+'" name="bg_values" type="text" class="validate"  placeholder="BG Value"></td>'
		   +'<td><input id="bg_valid_uptos'+rNo+'" name="bg_valid_uptos" type="text" class="validate datepicker"  placeholder="Valid Upto"><button type="button" id="bg_upto_icon"><i class="fa fa-calendar"></i></button></td>'
		   +'<td><input id="remarkss'+rNo+'" name ="remarkss" type="text" class="validate" value="${bankObj.remarks }" placeholder="Remarks"></td>'
	   	   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeBank('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
	 
		 $('#bankTableBody').append(html);
		 $("#bankRowNo").val(rNo);
		 $('select').formSelect();
		 
		 $("#bg_valid_uptos"+rNo).datepicker({
      	 format:'dd-mm-yyyy',
          onSelect: function () {
	    	     $('.confirmation-btns .datepicker-done').click();
	    	  }
      });

} 


function removeBank(rowNo){
$("#bankRow"+rowNo).remove();
}

function addInsurenceRow(){
	
 var rowNo = $("#insurenceRowNo").val();
 var rNo = Number(rowNo)+1;
 var total = 0;
 var html = '<tr id="insurenceRow'+rNo+'"><td> <div>'
	   +'<select  name="insurance_type_fks" id="insurance_type_fks'+rNo+'" >'	   			
	   +'<option value="" >select</option>'
	   <c:forEach var="obj" items="${insurance_type }">
		  +' <option value= "${ obj.insurance_type}">${ obj.insurance_type}</option>'
	  </c:forEach>
	   +'</select></div></td>'
	   +'<td> <input id="issuing_agencys'+rNo+'" name="issuing_agencys" type="text" class="validate"  placeholder="Issuing Agency"></td>'
	   +'<td><input id="agency_addresss'+rNo+'" name="agency_addresss" type="text" class="validate" placeholder="Agency Address"></td>'
	   +'<td><input id="insurance_numbers'+rNo+'" name="insurance_numbers" type="text" class="validate"  placeholder="Insurance Number"></td>'
	   +'<td><input id="insurance_values'+rNo+'" name="insurance_values" type="text" class="validate" placeholder="Insurance Value"></td>'
	   +'<td><input id="insurence_valid_uptos'+rNo+'" name="insurence_valid_uptos" type="text" class="validate datepicker" placeholder="Valid Upto"> <button type="button" id="insurance_upto_icon"><i class="fa fa-calendar"></i></button></td>'
	   +'<td><input id="insurence_remarks'+rNo+'" name="insurence_remarks"  type="text" class="validate"  placeholder="Remarks"></td>'
	   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeInsurence('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';

	 $('#insurenceTableBody').append(html);
	 $("#insurenceRowNo").val(rNo);
	 $('select').formSelect();
	 $("#insurence_valid_uptos"+rNo).datepicker({
	      	 format:'dd-mm-yyyy',
	          onSelect: function () {
		    	     $('.confirmation-btns .datepicker-done').click();
		    	  }
	      });
} 


function removeInsurence(rowNo){
$("#insurenceRow"+rowNo).remove();
}

function addMilestoneRow(){
	
 var rowNo = $("#mileRowNo").val();
 var rNo = Number(rowNo)+1;
 var total = 0;
 var html = '<tr id="mileRow'+rNo+'">'
	   +'<td><input id="milestone_names'+rNo+'" name="milestone_names" type="text" class="validate"  placeholder="Milestone Name "></td>'
	   +'<td><input id="milestone_dates'+rNo+'" name="milestone_dates" type="text" class="validate datepicker"  placeholder="Milestone Date"><button type="button" id="milestone_date_icon"><i class="fa fa-calendar"></i></button></td>'
	   +'<td><input id="actual_dates'+rNo+'" name="actual_dates" type="text" class="validate datepicker"   placeholder="Actual Date">  <button type="button" id="actual_date_icon"><i  class="fa fa-calendar"></i></button></td>'
	   +'<td><input id="revisions'+rNo+'" name="revisions" type="text" class="validate" placeholder="Revision"></td>'
	   +'<td>  <input id="mile_remarks'+rNo+'" name="mile_remarks" type="text" class="validate" placeholder="Remarks"></td>'
	   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeMilestone('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
	   +'</tr>';

	 $('#milestoneTableBody').append(html);
	 $("#mileRowNo").val(rNo);
	 $('select').formSelect();
	 $("#milestone_dates"+rNo).datepicker({
	      	 format:'dd-mm-yyyy',
	          onSelect: function () {
		    	     $('.confirmation-btns .datepicker-done').click();
		    	  }
	      });
	 $("#actual_dates"+rNo).datepicker({
	      	 format:'dd-mm-yyyy',
	          onSelect: function () {
		    	     $('.confirmation-btns .datepicker-done').click();
		    	  }
	      });
} 


function removeMilestone(rowNo){
	$("#mileRow"+rowNo).remove();
	}
function addRevRow(){
	
 var rowNo = $("#revRowNo").val();
 var rNo = Number(rowNo)+1;
 var total = 0;
 var html = '<tr id="revRow'+rNo+'">'
	   +'<td><input id="revision_numbers'+rNo+'" name="revision_numbers" type="text" class="validate"  placeholder="Revision Number"</td>'
	   +'<td><input id="revised_amounts'+rNo+'" name="revised_amounts" type="text" class="validate"  placeholder="Revised Amount"></td>'
	   +'<td><input id="revised_docs'+rNo+'" name="revised_docs" type="text" class="validate datepicker"  placeholder="Revised DOC">'
	   +'<button type="button" id="revised_doc_icon"><i class="fa fa-calendar"></i></button></td>'
	   +'<td> <input id="revision_remarks'+rNo+'" name="revision_remarks" type="text" class="validate"  placeholder="Remarks"></td>'
	   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeRev('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
	   +'</tr>';

	 $('#revTableBody').append(html);
	 $("#revRowNo").val(rNo);
	 $('select').formSelect();
	 $("#revised_docs"+rNo).datepicker({
	      	 format:'dd-mm-yyyy',
	          onSelect: function () {
		    	     $('.confirmation-btns .datepicker-done').click();
		    	  }
	      });
} 


function removeRev(rowNo){
$("#revRow"+rowNo).remove();
}



    </script>

</body>

</html>