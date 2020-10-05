<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Edit Contract</title>
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
                                <h6>Update Contract</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-contract" id="contractForm" name="contractForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">

                            <div class="row">

                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                    onchange="getWorksList(this.value);">
                                        <option value="" >Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id }" <c:if test="${contractDeatils.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
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
                                    <select name="department_fk" id="department_fk">
                                        <option value="" selected>Select</option>
                                          <c:forEach var="obj" items="${departmentList }">
                                      	    <option value= "${ obj.department_fk}" <c:if test="${contractDeatils.department_fk eq obj.department_fk}">selected</c:if>>${ obj.department_name}</option>
                                          </c:forEach>
                                    </select>
                                    <label>Department</label>
                              <span id="department_fkError" class="error-msg" ></span>
                                    
                                </div>
                                <div class="col s12 m4 input-field">
                                    <label class="primary-text-bold">Contract ID : <input id="contract_id" name="contract_id" type="text" value="${contractDeatils.contract_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                               
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input name="contract_name" id="contract_name" type="text" class="validate"  value="${contractDeatils.contract_name }"  >
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
		                                     <option value="${obj.contract_type_fk }" <c:if test="${contractDeatils.contract_type_fk eq obj.contract_type_fk}">selected</c:if>>${obj.contract_type_fk }</option>
		                                   </c:forEach>
                                    </select>
                                    <label>Contract Type</label>
                                     <span id="contract_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select name="contractor_id_fk" id="contractor_id_fk" class="validate-dropdown">
                                        <option value="" selected>Select</option>
                                       	    <c:forEach var="obj" items="${contractor }">
		                                      <option value="${obj.contractor_id_fk }" <c:if test="${contractDeatils.contractor_id_fk eq obj.contractor_id_fk}">selected</c:if>>${obj.contractor_name }</option>
		                                    </c:forEach>
                                    </select>
                                    <label>Contractor Name</label>
                            		<span id="contractor_id_fkError" class="error-msg" ></span>
                                    
                                </div>                             
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m8 input-field">
                                    <input id="scope_of_contract" name="scope_of_contract" type="text" class="validate" value="${contractDeatils.scope_of_contract }">
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
                                             <select name="designation" id="designation" class="validate-dropdown"> 
                                     		  <option value="" selected>Select</option> 
                                                 <c:forEach var="obj" items="${hodList }"> 
		                                    	  <option value="${obj.designation }" <c:if test="${contractDeatils.designation eq obj.designation}">selected</c:if> > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
		                                        </c:forEach> 
                                            </select> 
                                            <label>HOD</label>
                                            <span id="hod_user_id_fkError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m6 input-field">
                                           <input name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" type="text" class="validate" value="${contractDeatils.dy_hod_user_id_fk }">
                                    		<label for="dy_hod_user_id_fk">Dy HOD</label>
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
                                    <input name="doc" id="doc" type="text" class="validate datepicker" value="${contractDeatils.doc }" >
                                    <label for="doc">Planned DOC</label>
                                     <span id="docError" class="error-msg" ></span>
                                     <button type="button" id="doc_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>
                                    <input id="awarded_cost" name="awarded_cost" type="text" class="validate" value="${contractDeatils.awarded_cost }" />
                                    <label for="awarded_cost">Awarded cost</label>
                                    <span id="awarded_costError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                                <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="start_date" name="date_of_start" type="text" class="validate datepicker" value="${contractDeatils.date_of_start }">
                                    <label for="start_date">Date of Start</label>
                                     <span id="date_of_startError" class="error-msg" ></span>
                                    <button type="button" id="start_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="estimated_cost" name="estimated_cost" type="text" class="validate" value="${contractDeatils.estimated_cost }">
                                    <label for="estimated_cost">estimated cost</label>
                                    <span id="estimated_costError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="loa_letter_number" name="loa_letter_number" type="text" class="validate" value="${contractDeatils.loa_letter_number }">
                                    <label for="loa_letter_number">LOA Letter No</label>
                                    <span id="loa_letter_numberError" class="error-msg" ></span>
                                   
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="loa_date" name="loa_date" type="text" class="validate datepicker" value="${contractDeatils.loa_date }">
                                    <label for="loa_date">LOA Date</label>
                                    <span id="loa_dateError" class="error-msg" ></span>
                                    <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="ca_no" name="ca_no" type="text" class="validate" value="${contractDeatils.ca_no }">
                                    <label for="ca_no">CA No</label>
                                     <span id="ca_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="ca_date" name="ca_date" type="text" class="validate datepicker" value="${contractDeatils.ca_date }">
                                    <label for="ca_date">CA Date</label>
                                     <span id="ca_dateError" class="error-msg" ></span>
                                    <button type="button" id="ca_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker" value="${contractDeatils.actual_completion_date }">
                                    <label for="actual_completion_date">Actual Completed Date</label>
                                    <span id="actual_completion_dateError" class="error-msg" ></span>
                                    <button type="button" id="co_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                	<i class="material-icons prefix center-align">₹</i>
                                    <input id="completed_cost" name="completed_cost" type="text" class="validate" value="${contractDeatils.completed_cost }">
                                    <label for="completed_cost">Completed Cost</label>
                                     <span id="completed_costError" class="error-msg" ></span>
                                </div>                              
                            </div> 
      						<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="contract_closure_date" name="contract_closure_date" type="text" class="validate datepicker" value="${contractDeatils.contract_closure_date }">
                                    <label for="contract_closure_date">Contract Closure</label>
                                    <button type="button" id="contract_closure_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="contract_closure_dateError" class="error-msg" ></span>
                                    
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="completion_certificate_date" name="completion_certificate_release" type="text" class="validate datepicker" value="${contractDeatils.completion_certificate_release }">
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
                                    <input id="final_takeover_client" name="final_takeover" type="text" class="validate datepicker" value="${contractDeatils.final_takeover }">
                                    <label for="final_takeover_client">Final Taking over by Client</label>
                                    <span id="final_takeover_clientError" class="error-msg" ></span>
                                    <button type="button" id="final_takeover_client_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_bill_release" name="final_bill_release" type="text" class="validate" value="${contractDeatils.final_bill_release }">
                                    <label for="final_bill_release">Release of Final bill</label>
                                    <span id="final_bill_releaseError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="defect_liability_period" name="defect_liability_period" type="text" class="validate" value="${contractDeatils.defect_liability_period }">
                                    <label for="defect_liability_period">Defect Liability Period</label>
                                    <span id="defect_liability_periodError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="final_retention_release" name="retention_money_release" type="text" class="validate" value="${contractDeatils.retention_money_release }">
                                    <label for="final_retention_release"> Release of Final Retention amount/BG</label>
                                    <span id="final_retention_releaseError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="release_of_pbg" name="pbg_release" type="text" class="validate" value="${contractDeatils.pbg_release }">
                                    <label for="release_of_pbg">Release of PBG</label>
                                    <span id="release_of_pbgError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="contract_closure" name="contract_closure" type="text" class="validate" value="${contractDeatils.contract_closure }">
                                    <label for="contract_closure"> Contract Closure Comment</label>
                                     <span id="contract_closureError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select name = "contract_status_fk" id="contract_status_fk" class="validate-dropdown">
                                        <option value="" selected>Select</option>
                                           <c:forEach var="obj" items="${contract_Statustype }">
		                                    	<option value="${obj.contract_status_fk }" <c:if test="${contractDeatils.contract_status_fk eq obj.contract_status_fk}">selected</c:if>>${obj.contract_status_fk }</option>
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
                                    <p>Bank Guarantee Required</p>							<%-- ${contractDeatils.bg_required } --%>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio"  value="yes" <c:if test="${contractDeatils.bg_required eq 'yes'}">checked
                                            </c:if> />
                                               
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio" value="no" <c:if test="${contractDeatils.bg_required eq 'no'}">checked
                                            </c:if> />
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
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.bankGauranree && fn:length(contractDeatils.bankGauranree) gt 0 }">
                                          
                                		  <c:forEach var="bankObj" items="${contractDeatils.bankGauranree }" varStatus="index">                                        	
                                        
                                            <tr id="bankRow${index.count }">
                                                <td> <select id="bg_type_fks${index.count }" name="bg_type_fks">
                                                        <option value="" selected>Select
                                                        </option>
                                                         <c:forEach var="obj" items="${bankGuaranteeTYpe }">
		                                    			   <option value="${obj.bg_type_fk }" <c:if test="${bankObj.bg_type_fk eq obj.bg_type_fk}">selected</c:if>>${obj.bg_type_fk }</option>
		                                     			  </c:forEach>
                                                    </select>
                                                </td> 
                                                <!-- <td> <input id="bg_type" type="text" class="validate"
                                                        placeholder="BG Type">
                                                </td> -->
                                                <td>
                                                    <input id="issuing_banks${index.count }" name="issuing_banks"  type="text" class="validate" value="${bankObj.issuing_bank }"
                                                        placeholder="Issuing Bank">
                                                </td>
                                                <td>
                                                    <input id="bank_addresss${index.count }" name ="bank_addresss" type="text" class="validate" value="${bankObj.bank_address }"
                                                        placeholder="Bank Address">
                                                </td>
                                                <td>
                                                    <input id="bg_numbers${index.count }" name="bg_numbers" type="text" class="validate" value="${bankObj.bg_number}"
                                                        placeholder="BG Number">
                                                </td>
                                                <td>
                                                    <input id="bg_values${index.count }" name="bg_values" type="text" class="validate" value="${bankObj.bg_value }"
                                                        placeholder="BG Value">
                                                </td>
                                                <td>
                                                    <input id="bg_valid_uptos${index.count }" name="bg_valid_uptos" type="text" class="validate datepicker" value="${bankObj.bg_valid_upto }"
                                                        placeholder="Valid Upto">
                                                    <button type="button" id="bg_upto_icon"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarkss${index.count }" name ="remarkss" type="text" class="validate" value="${bankObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a onclick="removeBank('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>                                               
                                               
                                            </tr>
                                             <script type="text/javascript">
	                                               /*  $("#bg_valid_uptos${index.count }").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 }); */
                                                </script>
                                          </c:forEach>
                                           </c:when>
                                             <c:otherwise>
                                             <tr id="bankRow0">
                                                <td> <select id="bg_type_fks0" name="bg_type_fks">
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
                                                    <input id="issuing_banks0" name="issuing_banks"  type="text" class="validate"
                                                        placeholder="Issuing Bank">
                                                </td>
                                                <td>
                                                    <input id="bank_addresss0" name ="bank_addresss" type="text" class="validate"
                                                        placeholder="Bank Address">
                                                </td>
                                                <td>
                                                    <input id="bg_numbers0" name="bg_numbers" type="text" class="validate"
                                                        placeholder="BG Number">
                                                </td>
                                                <td>
                                                    <input id="bg_values0" name="bg_values" type="text" class="validate"
                                                        placeholder="BG Value">
                                                </td>
                                                <td>
                                                    <input id="bg_valid_uptos0" name="bg_valid_uptos" type="text" class="validate datepicker"
                                                        placeholder="Valid Upto">
                                                    <button type="button" id="bg_upto_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="remarkss0" name ="remarkss" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a onclick="removeBank('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                                
                                                
                                            </tr>
                                            	<script type="text/javascript">
	                                                $("#bg_valid_uptos0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
                                                </script>
                                              </c:otherwise>
                                            </c:choose>
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
                                    <c:choose>
                                        <c:when test="${not empty contractDeatils.bankGauranree && fn:length(contractDeatils.bankGauranree) gt 0 }">
                                    		<input type="hidden" id="bankRowNo"  name="bankRowNo" value="${fn:length(contractDeatils.bankGauranree) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="bankRowNo"  name="bankRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>   
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 input-field center-align no-float-small">
                                    <p>Insurance Required</p>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="insurance_required" type="radio"  value="yes" <c:if test="${contractDeatils.insurance_required eq 'yes'}">checked
                                            </c:if> />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="insurance_required" type="radio"  value="no" <c:if test="${contractDeatils.insurance_required eq 'no'}">checked
                                            </c:if> />
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
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                          <c:forEach var="insurenceObj" items="${contractDeatils.insurence }" varStatus="index">  
                                            <tr id="insurenceRow${index.count }">
                                                <td>
                                                    <select id="insurance_type_fks${index.count }" name="insurance_type_fks">
                                                        <option value="" selected>Select</option>
                                                          <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" <c:if test="${insurenceObj.insurance_type_fk eq obj.insurance_type}">selected</c:if>>${ obj.insurance_type}</option>
                                        				  </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="issuing_agencys${index.count }" name="issuing_agencys" type="text" class="validate"  value="${insurenceObj.issuing_agency }"
                                                        placeholder="Issuing Agency">
                                                </td>
                                                <td>
                                                    <input id="agency_addresss${index.count }" name="agency_addresss" type="text" class="validate" value="${insurenceObj.agency_address }"
                                                        placeholder="Agency Address">
                                                </td>

                                                <td>
                                                    <input id="insurance_numbers${index.count }" name="insurance_numbers" type="text" class="validate" value="${insurenceObj.insurance_number }"
                                                        placeholder="Insurance Number">
                                                </td>
                                                <td>
                                                    <input id="insurance_values${index.count }" name="insurance_values" type="text" class="validate" value="${insurenceObj.insurance_value }"
                                                        placeholder="Insurance Value">
                                                </td>
                                                <td>
                                                    <input id="insurence_valid_uptos${index.count }" name="insurence_valid_uptos" type="text" value="${insurenceObj.insurence_valid_upto }"
                                                        class="validate datepicker" placeholder="Valid Upto">
                                                    <button type="button" id="insurance_upto_icon${index.count }"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="insurence_remarks${index.count }" name="insurence_remarks"  type="text" class="validate" value="${insurenceObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a onclick="removeInsurence('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                             <script type="text/javascript">
	                                               /*  $("#insurence_valid_uptos${index.count }").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 }); */
                                                </script>
                                             </c:forEach>
                                             </c:when>
                                             <c:otherwise>
                                             
                                              <tr id="insurenceRow0">
                                                <td>
                                                    <select id="insurance_type_fks0" name="insurance_type_fks">
                                                        <option value="" selected>Select</option>
                                                          <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" >${ obj.insurance_type}</option>
                                        				  </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="issuing_agencys0" name="issuing_agencys" type="text" class="validate" 
                                                        placeholder="Issuing Agency">
                                                </td>
                                                <td>
                                                    <input id="agency_addresss0" name="agency_addresss" type="text" class="validate" 
                                                        placeholder="Agency Address">
                                                </td>

                                                <td>
                                                    <input id="insurance_numbers0" name="insurance_numbers" type="text" class="validate" 
                                                        placeholder="Insurance Number">
                                                </td>
                                                <td>
                                                    <input id="insurance_values0" name="insurance_values" type="text" class="validate" 
                                                        placeholder="Insurance Value">
                                                </td>
                                                <td>
                                                    <input id="insurence_valid_uptos0" name="insurence_valid_uptos" type="text" 
                                                        class="validate datepicker" placeholder="Valid Upto">
                                                    <button type="button" id="insurance_upto_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="insurence_remarks0" name="insurence_remarks"  type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a onclick="removeInsurence('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <script type="text/javascript">
	                                                $("#insurence_valid_uptos0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
                                                </script>
                                           
                                             </c:otherwise>
                                            </c:choose>  
                                        </tbody>
                                    </table>
                                     <table  class="mdl-data-table">
                                        <tbody id="insurenceTableBody">                                          
                                            <tr>
                                   <td colspan="8" style="text-align: right;" ><a   class="btn waves-effect waves-light bg-m t-c "  onclick="addInsurenceRow()"> <i class="fa fa-plus"></i></a></td>
                                             </tr>
                                        </tbody>
                                    </table>
									<c:choose>
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                    		<input type="hidden" id="insurenceRowNo"  name="insurenceRowNo" value="${fn:length(contractDeatils.insurence) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="insurenceRowNo"  name="insurenceRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
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
                                        </thead >
                                        <tbody id="milestoneTableBody" >
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.milestones  && fn:length(contractDeatils.milestones ) gt 0 }">
                                          
                                          <c:forEach var="milestonesObj" items="${contractDeatils.milestones }" varStatus="index">  
                                        
                                            <tr id="mileRow${index.count }">                                    
                                                <td>
                                                    <input id="milestone_names${index.count }" name="milestone_names" type="text" class="validate" value="${milestonesObj.milestone_name }"
                                                        placeholder="Milestone Name ">
                                                </td>
                                                <td>
                                                    <input id="milestone_dates${index.count }" name="milestone_dates" type="text" class="validate datepicker" value="${milestonesObj.milestone_date }"
                                                        placeholder="Milestone Date">
                                                    <button type="button" id="milestone_date_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="actual_dates${index.count }" name="actual_dates" type="text" class="validate datepicker" value="${milestonesObj.actual_date }"
                                                        placeholder="Actual Date">
                                                    <button type="button" id="actual_date_icon${index.count }"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="revisions${index.count }" name="revisions" type="text" class="validate" value="${milestonesObj.revision }"
                                                        placeholder="Revision">
                                                </td>
                                                <td>
                                                    <input id="mile_remarks${index.count }" name="mile_remarks" type="text" class="validate" value="${milestonesObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td><a onclick="removeMilestone('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                              <script type="text/javascript">
	                                               /*  $("#milestone_dates${index.count },#actual_dates${index.count }").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 }); */
                                                </script>
                                             </c:forEach>
                                              </c:when>
                                             <c:otherwise>
                                              <tr id="mileRow0">                                    
                                                <td>
                                                    <input id="milestone_names0" name="milestone_names" type="text" class="validate" 
                                                        placeholder="Milestone Name ">
                                                </td>
                                                <td>
                                                    <input id="milestone_dates0" name="milestone_dates" type="text" class="validate datepicker" 
                                                        placeholder="Milestone Date">
                                                    <button type="button" id="milestone_date_icon${index.count }"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="actual_dates0" name="actual_dates" type="text" class="validate datepicker" 
                                                        placeholder="Actual Date">
                                                    <button type="button" id="actual_date_icon${index.count }"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="revisions0" name="revisions" type="text" class="validate " 
                                                        placeholder="Revision">
                                                </td>
                                                <td>
                                                    <input id="mile_remarks0" name="mile_remarks" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td><a onclick="removeMilestone('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                              <script type="text/javascript">
	                                                $("#milestone_dates0,#actual_dates0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
                                                </script>
                                            </c:otherwise>
                                            </c:choose>  
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
                                    
                                      <c:choose>
                                        <c:when test="${not empty contractDeatils.milestones && fn:length(contractDeatils.milestones) gt 0 }">
                                    		<input type="hidden" id="mileRowNo"  name="mileRowNo" value="${fn:length(contractDeatils.milestones) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="mileRowNo"  name="mileRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
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
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.contract_revision  && fn:length(contractDeatils.contract_revision ) gt 0 }">
                                          
                                         <c:forEach var="revObj" items="${contractDeatils.contract_revision }" varStatus="index">  
                                            <tr id="revRow${index.count }">
                                                <td> <input id="revision_numbers${index.count }" name="revision_numbers" type="text" class="validate" value="${revObj.revision_number }"
                                                        placeholder="Revision Number">
                                                </td>
                                                <td><i class="material-icons prefix center-align">₹</i>
                                                    <input id="revised_amounts${index.count }" name="revised_amounts" type="text" class="validate" value="${revObj.revised_amount }"
                                                        placeholder="Revised Amount">
                                                </td>
                                                <td>
                                                    <input id="revised_docs${index.count }" name="revised_docs" type="text" class="validate datepicker" value="${revObj.revised_doc }"
                                                        placeholder="Revised DOC">
                                                    <button type="button" id="revised_doc_icon${index.count }"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td> 
                                                    <input id="revision_remarks${index.count }" name="revision_remarks" type="text" class="validate" value="${revObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td><a onclick="removeRev('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                             <script type="text/javascript">
	                                               /*  $("#revised_docs${index.count }").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 }); */
                                                </script>
                                          </c:forEach>
                                           </c:when>
                                             <c:otherwise>
                                             <tr id="revRow0">
                                                <td> <input id="revision_numbers0" name="revision_numbers" type="text" class="validate" 
                                                        placeholder="Revision Number">
                                                </td>
                                                <td>
                                                    <input id="revised_amounts0" name="revised_amounts" type="text" class="validate"
                                                        placeholder="Revised Amount">
                                                </td>
                                                <td>
                                                    <input id="revised_docs0" name="revised_docs" type="text" class="validate datepicker" 
                                                        placeholder="Revised DOC">
                                                    <button type="button" id="revised_doc_icon"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td> 
                                                    <input id="revision_remarks0" name="revision_remarks" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td><a onclick="removeRev('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                             <script type="text/javascript">
	                                                $("#revised_docs0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
                                                </script>
                                             </c:otherwise>
                                            </c:choose> 
                                        </tbody>
                                    </table>
                                     <table class="mdl-data-table">
                                        <tbody id="revTableBody">                                          
                                            <tr>
									<td colspan="5" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addRevRow()"> <i
                                                            class="fa fa-plus"></i></a></td>
                                              </tr>
                                        </tbody>
                                    </table>
                                   	 <c:choose>
                                        <c:when test="${not empty contractDeatils.contract_revision && fn:length(contractDeatils.contract_revision) gt 0 }">
                                    		<input type="hidden" id="revRowNo"  name="revRowNo" value="${fn:length(contractDeatils.contract_revision) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="revRowNo"  name="revRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
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
                                    <textarea id="remarks" name ="remarks" class="materialize-textarea" data-length="1000">${contractDeatils.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg"></span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="button" style="width: 100%;" onclick="updateContract();" class="btn waves-effect waves-light bg-m">Update</button>
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
    $(document).on('focus', '.datepicker',function(){
        $(this).datepicker({
        	format:'dd-mm-yyyy',
   	    	onSelect: function () {
   	    	   $('.confirmation-btns .datepicker-done').click();
   	    	}
        })
    });
    
        $(document).ready(function () {
            $('select').formSelect();
            $('#remarks').characterCounter();
            // $(".datepicker").datepicker();
           /*  $("#loa_date").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
   	    	     $('.confirmation-btns .datepicker-done').click();
   	    	  } }); */
            $('#loa_date_icon').click(function () {
                event.stopPropagation();
                $('#loa_date').click();
            });
           /*  $("#doc").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
   	    	     $('.confirmation-btns .datepicker-done').click();
   	    	  } }); */
            $('#doc_icon').click(function () {
                event.stopPropagation();
                $('#doc').click();
            });
           /*  $("#ca_date").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           }); */
            $('#ca_date_icon').click(function () {
                event.stopPropagation();
                $('#ca_date').click();
            });
          
           /*  $("#actual_completion_date").datepicker({
            	 maxDate: new Date(),
	           	 format:'dd-mm-yyyy',
	             onSelect: function () {
		    	     $('.confirmation-btns .datepicker-done').click();
		    	  } }); */
     /* 
            $("#bg_valid_uptos").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  } }); */
            
            $('#bg_upto_valid_icon').click(function () {
                event.stopPropagation();
                $('#bg_upto_valid').click();
            });
          /*  
            $("#insurence_valid_uptos").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            }); */
            $('#insurance_upto_valid_icon').click(function () {
                event.stopPropagation();
                $('#insurance_upto_valid').click();
            });
           /*  $("#milestone_dates").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            }); */

            $('#milestone_date_icon').click(function () {
                event.stopPropagation();
                $('#milestone_date').click();
            });
           /*  $("#actual_dates").datepicker({
            	
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            }); */
            $('#actual_date_icon').click(function () {
                event.stopPropagation();
                $('#actual_date').click();
            });
           /*  $("#revised_docs").datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
     	    	     $('.confirmation-btns .datepicker-done').click();
     	    	  }
            }); */
            $('#revised_doc_icon').click(function () {
                event.stopPropagation();
                $('#revised_doc').click();
            });
          

           
           
          
           
          
         

            $('#insurance_upto_icon').click(function () {
                event.stopPropagation();
                $('#insurance_upto').click();
            });

          /*   $('#contract_closure_date').datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           }); */
            $('#contract_closure_date_icon').click(function () {
                event.stopPropagation();
                $('#contract_closure_date').click();
            });

           /*  $('#completion_certificate_date').datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           }); */
            $('#completion_certificate_date_icon').click(function () {
                event.stopPropagation();
                $('#completion_certificate_date').click();
            });
          /*   $('#start_date').datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           }); */
            $('#start_date_icon').click(function () {
                event.stopPropagation();
                $('#start_date').click();
            });
           
          /*   $('#final_takeover_client').datepicker({
            	 format:'dd-mm-yyyy',
                onSelect: function () {
    	    	     $('.confirmation-btns .datepicker-done').click();
    	    	  }
           }); */
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
       
            var projectId = "${contractDeatils.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
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
                                var workId = "${contractDeatils.work_id_fk}";
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
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
        
        function updateContract(){
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
	        	 			 error.appendTo("docError");
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
  		   +'<select  name="bg_type_fks" id="bg_type_fks'+rNo+'" >'	   			
  		   +'<option value="" >select</option>'
  		 	<c:forEach var="obj" items="${bankGuaranteeTYpe }">
		  +'<option value="${obj.bg_type_fk }">${obj.bg_type_fk }</option>'
			</c:forEach>
  		   +'</select></div></td>'
		   +'<td> <input id="issuing_banks'+rNo+'" name="issuing_banks"  type="text" class="validate"  placeholder="Issuing Bank"></td>'
		   +'<td><input id="bank_addresss'+rNo+'" name ="bank_addresss" type="text" class="validate"  placeholder="Bank Address"></td>'
		   +'<td><input id="bg_numbers'+rNo+'" name="bg_numbers" type="text" class="validate"  placeholder="BG Number"></td>'
		   +'<td><input id="bg_values'+rNo+'" name="bg_values" type="text" class="validate"  placeholder="BG Value"></td>'
		   +'<td><input id="bg_valid_uptos'+rNo+'" name="bg_valid_uptos" type="text" class="validate"  placeholder="Valid Upto"><button type="button" id="bg_upto_icon"><i class="fa fa-calendar"></i></button></td>'
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
    console.log(rNo)
    var html = '<tr id="insurenceRow'+rNo+'"><td> <div>'
	   +'<select  name="insurance_type_fks" id="insurance_type_fks'+rNo+'"  >'	   			
	   +'<option value="" >select</option>'
	   <c:forEach var="obj" items="${insurance_type }">
		  +' <option value= "${ obj.insurance_type}">${ obj.insurance_type}</option>'
	  </c:forEach>
	   +'</select></div></td>'
	   +'<td> <input id="issuing_agencys'+rNo+'" name="issuing_agencys" type="text" class="validate"  placeholder="Issuing Agency"></td>'
	   +'<td><input id="agency_addresss'+rNo+'" name="agency_addresss" type="text" class="validate" placeholder="Agency Address"></td>'
	   +'<td><input id="insurance_numbers'+rNo+'" name="insurance_numbers" type="text" class="validate"  placeholder="Insurance Number"></td>'
	   +'<td><input id="insurance_values'+rNo+'" name="insurance_values" type="text" class="validate" placeholder="Insurance Value"></td>'
	   +'<td><input id="insurence_valid_uptos'+rNo+'" name="insurence_valid_uptos" type="text" class="validate" placeholder="Valid Upto"> <button type="button" id="insurance_upto_icon"><i class="fa fa-calendar"></i></button></td>'
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
	console.log("#insurenceRow"+rowNo)
$("#insurenceRow"+rowNo).remove();
}

function addMilestoneRow(){
	
    var rowNo = $("#mileRowNo").val();
    var rNo = Number(rowNo)+1;
    var total = 0;
    var html = '<tr id="mileRow'+rNo+'">'
	   +'<td><input id="milestone_names'+rNo+'" name="milestone_names" type="text" class="validate"  placeholder="Milestone Name "></td>'
	   +'<td><input id="milestone_dates'+rNo+'" name="milestone_dates" type="text" class="validate"  placeholder="Milestone Date"><button type="button" id="milestone_date_icon"><i class="fa fa-calendar"></i></button></td>'
	   +'<td><input id="actual_dates'+rNo+'" name="actual_dates" type="text" class="validate"   placeholder="Actual Date">  <button type="button" id="actual_date_icon"><i  class="fa fa-calendar"></i></button></td>'
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
	   +'<td><i class="material-icons prefix center-align">₹</i><input id="revised_amounts'+rNo+'" name="revised_amounts" type="text" class="validate"  placeholder="Revised Amount"></td>'
	   +'<td><input id="revised_docs'+rNo+'" name="revised_docs" type="text" class="validate"  placeholder="Revised DOC">'
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