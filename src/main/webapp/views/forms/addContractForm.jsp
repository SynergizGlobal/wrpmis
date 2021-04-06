<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Contract</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
         
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
     <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <style>

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
            padding: 0 !important;
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
				
		#insurenceTableBody .select2-container{
			max-width:134px
		}
		#bankTableBody .select2-container{
			max-width:140px;
			width:130px;
		}
		#bankTableBody th.fs-100,
		#bankTableBody td.fs-100{
			max-width:100px;
			width:90px;
		}
		#insurenceTableBody td.input-field .prefix,
		#revTableBody td.input-field .prefix,
		#bankTableBody td.input-field .prefix {
    		top: 1.5rem;
		}
		
		    .filevalue {
            display: block;
            margin-top: 10px;
        }
        #insurance_div,#bank_guarantee_div{
        	margin:0
        }
        .table-inside td .btn{
        	padding:6px 12px;
        	line-height:20px;
        	height:auto;
        }
         .table-inside td .btn .fa{
         	font-size:1rem;
         }
         
         .table-inside .normal-btn .btn{
         	padding:0 16px;
        	line-height:36px;
        	height:36px;
         }
         .table-inside .normal-btn .btn .fa{
         	font-size:1.3rem;
         }
         .select2-container--default .select2-selection--single {
	    	background-color: transparent;
	     }
	     #bankTable thead th, 
	     #bankTable tbody td,	      
	     #insurenceTable thead th, 
	     #insurenceTable tbody td{
	     	padding:12px;
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
		.input-field .searchable_label{
			font-size:.9rem;
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Add Contract</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
<!--                     <div class="container container-no-margin"> -->
                        <form action="add-contract" id="contractForm" name="contractForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                            <div class="container container-no-margin">
	                            <div class="row">
	                                <!-- row 1  -->
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                <p class="searchable_label">Project <span class="required">*</span></p>
	                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
	                                    onchange="getWorksList(this.value);">
	                                        <option value="">Select</option>
	                                         <c:forEach var="obj" items="${projectsList }">
	                                      	   <option value= "${obj.project_id}" >${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
	                                         </c:forEach>
	                                    </select>                                    
	                                     <span id="project_id_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                <p class="searchable_label">Work <span class="required">*</span></p>
	                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" 
	                                    	onchange="resetProjectsDropdowns(this.value);">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${worksList }">
	                                      	   <option value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                         </c:forEach>
	                                    </select>
	                                     <span id="work_id_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <!-- //row 9 -->
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m8 ">
	                                    <div class="row">
	  										<div class="col s12 m6 input-field">
	  										 	<p class="searchable_label">HOD</p>
	                                            <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown searchable" onchange="getDepartmentsList(this.value);"> 
	                                     		  <option value="">Select</option> 
	                                                 <c:forEach var="obj" items="${hodList }"> 
			                                    	  <option value="${obj.user_id }" > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                        </c:forEach> 
	                                            </select> 
												<!-- <input name="hod_user_id_fk" id="hod_user_id_fk" type="text" class="validate">
												<label for="hod_user_id_fk">HOD</label> -->
	                                            <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                        <div class="col s12 m6 input-field">
	                                        	<p class="searchable_label">Dy HOD</p>
	                                            <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown searchable">
	                                                <option value="">Select</option>
	                                                <c:forEach var="obj" items="${dyHodList }"> 
			                                    	  <option value="${obj.user_id }" > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                        </c:forEach> 
	                                            </select>
												<!-- <input name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" type="text" class="validate" style="margin-top:10px">
	                               		     	<label for="dy_hod_user_id_fk">Dy HOD</label> -->
	                                            <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                    </div>
	                                </div>
	
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            
	                            <div class="row">
	                                <!-- row 1  -->
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                <p class="searchable_label">Department <span class="required">*</span></p>
	                                    <select name="department_fk" id="department_fk" class="searchable validate-dropdown">
	                                        <option value="">Select</option>
	                                           <c:forEach var="obj" items="${departmentList }">
	                                      	    <option value= "${ obj.department_fk}" >${ obj.department_name}</option>
	                                          </c:forEach>
	                                    </select>
	                                      <span id="department_fkError" class="error-msg" ></span>
	                                </div>
	                                <!-- <div class="col s12 m4 input-field">
	                                    <label class="primary-text-bold" style="margin-top:10px">Contract ID :</label>
	                                </div> -->
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	
	                            <div class="row">
	                                <!-- row 4 -->
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m8 input-field">
	                                    <textarea name="contract_name" id="contract_name" type="text" class="validate materialize-textarea" ></textarea>
	                                    <label for="contract_name">Contract Name <span class="required">*</span></label>
	                                    <span id="contract_nameError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            
	                            <div class="row">
	                                <!-- row 4 -->
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m8 input-field">
	                                    <input name="contract_short_name" id="contract_short_name" type="text" class="validate" >
	                                    <label for="contract_short_name">Contract Short Name</label>
	                                      <span id="contract_short_nameError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	
	                            <div class="row">
	                                <!-- row 6 -->
	                                <div class="col m2 hide-on-small-only"></div>
	
	                                <div class="col s12 m4 input-field">
	                                 <p class="searchable_label">Contract Type <span class="required">*</span></p>
	                                    <select name="contract_type_fk" id="contract_type_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                       	   <c:forEach var="obj" items="${contract_type }">
			                                     <option value="${obj.contract_type_fk }" >${obj.contract_type_fk }</option>
			                                   </c:forEach>
	                                    </select>                                   
	                                     <span id="contract_type_fkError" class="error-msg" ></span>
	                                    
	                                </div>
	                                
	                                <div class="col s12 m4 input-field">
	                                 <p class="searchable_label">Contractor Name <span class="required">*</span></p>
	                                    <select name="contractor_id_fk" id="contractor_id_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                       	    <c:forEach var="obj" items="${contractors }">
			                                      <option value="${obj.contractor_id_fk }" >${obj.contractor_name }</option>
			                                    </c:forEach>
	                                    </select>
	                                    <span id="contractor_id_fkError" class="error-msg" ></span>
	                                </div>                             
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m8 input-field">
	                                    <textarea id="scope_of_contract" name="scope_of_contract" class="materialize-textarea validate" data-length="1000">${contractDeatils.scope_of_contract }</textarea>
	                                    <label for="scope_of_contract">Scope of Contract</label>
	                                 <span id="scope_of_contractError" class="error-msg" ></span>                                    
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
	                                <!-- //row 7 -->
	                                <div class="col m2 hide-on-small-only"></div>	                               
	                                <div class="col s12 m4 input-field">
	                                    <input id="date_of_start" name="date_of_start" type="text" class="validate datepicker">
	                                    <label for="date_of_start">Date of Start</label>
	                                     <span id="date_of_startError" class="error-msg" ></span>
	                                    <button type="button" id="date_of_start_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                	<i class="material-icons prefix center-align">₹</i>
	                                    <input id="awarded_cost" name="awarded_cost" type="number" min="0.01" step="0.01" class="validate">
	                                    <label for="awarded_cost">Awarded cost</label>
	                                    <span id="awarded_costError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input name="doc" id="doc" type="text" class="validate datepicker">
	                                    <label for="doc">Contractual DOC</label>
	                                    <button type="button" id="doc_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="docError" class="error-msg" ></span>
	                                </div>	                                
	                                <div class="col s12 m4 input-field">
	                                    <i class="material-icons prefix center-align">₹</i>
	                                    <input id="estimated_cost" name="estimated_cost" type="number" min="0.01" step="0.01" class="validate">
	                                    <label for="estimated_cost">Estimated cost</label>
	                                     <span id="estimated_costError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>	   
	                            
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>	                                
	                                <div class="col s12 m4 input-field">
	                                 <p class="searchable_label"> <label>Status of Contract</label> </p>
	                                    <select class="validate-dropdown searchable" id="contract_status_fk" name="contract_status_fk">
	                                        <option value="" selected>Select</option>
	                                       		 <c:forEach var="obj" items="${contract_Statustype }">
			                                    	 <option value="${obj.contract_status_fk }" >${obj.contract_status_fk }</option>
			                                      </c:forEach>
	                                    </select>
	                                    <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m8 input-field">
	                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000"></textarea>
	                                    <label for="remarks">Remarks</label>
	                                      <span id="remarksError" class="error-msg"></span>
	                                </div>
	                            </div>                        
	                           
	                            <%-- <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                 <p>   <label>Status of Contract</p>
	                                    <select class="validate-dropdown searchable" id="contract_status_fk" name="contract_status_fk">
	                                        <option value="" selected>Select</option>
	                                       		 <c:forEach var="obj" items="${contract_Statustype }">
			                                    	 <option value="${obj.contract_status_fk }" >${obj.contract_status_fk }</option>
			                                      </c:forEach>
	                                    </select>
	                                    <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div> --%>
	                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 input-field center-align no-float-small">
                                    <p>Bank Guarantee Required</p>
                                    <p>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio" value="Yes" />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio" value="No" />
                                            <span>No</span>
                                        </label>
                                    </p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
							</div>
                            <!-- bg show hide div  -->
                            <div class="row fixed-width" id="bank_guarantee_div">
                                <h5 class="center-align">Bank Guarantee Details</h5>
                                <div class="table-inside">
                                    <table id="bankTable" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th class="fs-100">Code </th>
                                                <th>BG Type </th>
                                                <th>Issuing Bank </th>
                                               <!--  <th>Bank Address </th> -->
                                                <th>BG / FDR <br>Number </th>
                                                <th>Amount </th>
                                                <th>BG / FDR <br> Date </th>
                                                <th>Expiry Date </th>
                                              <!--   <th>Remarks </th> -->
                                                <th>Release Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="bankTableBody">
                                            <tr id="bankRow0">
                                            <td class="fs-100"> <input id="codes0" type="text" class="validate" name="codes"
                                                        placeholder="Code">
                                                </td>
                                                <td> <select id="bg_type_fks0" name="bg_type_fks" class="searchable">
                                                        <option value="" selected>Select </option>
                                                         <c:forEach var="obj" items="${bankGuaranteeTYpe }">
		                                    			   <option value="${obj.bg_type_fk }" >${obj.bg_type_fk }</option>
		                                     			  </c:forEach>
                                                    </select>
                                                </td> 
                                                  
                                                <td>
                                                    <input id="issuing_banks0" name="issuing_banks"  type="text" class="validate"
                                                        placeholder="Issuing Bank">
                                                </td>
                                               <!--  <td>
                                                    <input id="bank_addresss0" name ="bank_addresss" type="text" class="validate"
                                                        placeholder="Bank Address">
                                                </td> -->
                                                <td>
                                                    <input id="bg_numbers0" name="bg_numbers" type="text" class="validate"
                                                        placeholder="BG / FDR Number">
                                                </td>
                                                <td class="input-field"><i class="material-icons prefix center-align">₹</i>
                                                    <input id="bg_values0" name="bg_values" type="number" min="0.01" step="0.01" class="validate"
                                                        placeholder="Amount">
                                                </td>
                                                <td>
                                                    <input id="bg_dates0" name="bg_dates" type="text" class="validate datepicker"
                                                        placeholder="BG /FDR Date">
                                                         <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="bg_valid_uptos0" name="bg_valid_uptos" type="text" class="validate datepicker"
                                                        placeholder="Expiry Date">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                               <!--  <td>
                                                    <input id="remarkss0" name ="remarkss" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td> -->
												<td>	
													<!-- <label> <input type="checkbox" id="status0"/> <span></span> </label>	 -->
													<input id="release_dates0" name="release_dates" type="text" class="validate datepicker"
                                                        placeholder="Release Date">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
												</td>                                                
                                                <td>
                                                    <a onclick="removeBank('0');" class="btn waves-effect waves-light red t-c "> <i
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
									<input type="hidden" id="bankRowNo"  name="bankRowNo" value="0" />                                
								</div>
                            </div>
 							
 							<div class="container container-no-margin">
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col m8 input-field center-align no-float-small">
	                                    <p>Insurance Required</p>
	                                    <p>
	                                        <label>
	                                            <input class="with-gap" name="insurance_required" type="radio" value="Yes" />
	                                            <span>Yes</span>
	                                        </label>
	                                        <label>
	                                            <input class="with-gap" name="insurance_required" type="radio" value="No" />
	                                            <span>No</span>
	                                        </label>
	                                    </p>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
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
                                                <th>Revision </th>
                                                <th>Valid Upto </th>
                                                <th>Remarks </th>
                                                <th>Release</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                         <tbody id="insurenceTableBody">
                                        
                                            <tr id="insurenceRow0">
                                                <td>
                                                    <select id="insurance_type_fks0" name="insurance_type_fks" class="searchable">
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
                                                <td class="input-field"><i class="material-icons prefix center-align">₹</i>
                                                    <input id="insurance_values0" name="insurance_values" type="number" class="validate"  min="0.01" step="0.01" 
                                                        placeholder="Insurance Value">
                                                </td>
                                                <td>
                                                    <input id="insurance_revisions0" name="insurance_revisions" type="text" class="validate" 
                                                        placeholder="Revision">
                                                </td>
												
											<td><input id="insurence_valid_uptos0"
												name="insurence_valid_uptos" type="text"
												class="validate datepicker" placeholder="Valid Upto">
												<button type="button">
													<i class="fa fa-calendar"></i>
												</button></td>
											<td>
                                                    <input id="insurence_remarks0" name="insurence_remarks"  type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                 <td><label><input type="hidden" id="insuranceStatuss0" name="insuranceStatus" value="No" />
                                                 <input type="checkbox" id="insuranceStatus0" /> <span></span> </label>	</td>       
                                                
                                                <td>
                                                    <a onclick="removeInsurence('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                             <script type="text/javascript">
	                                              
	                                                $('#insuranceStatus0').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#insuranceStatuss0').val('Yes');
							                             } else{
							                              	 
							                            	  $("#insuranceStatuss0").prop('checked',false).removeAttr('checked');
							                            	  $("#insuranceStatuss0").val('No')
							                              }
							                   	    });
                                                </script>
                                           
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

 							<div class="container container-no-margin">
	                            <div class="row fixed-width">
	                                <h5 class="center-align">Milestone Details</h5>
	                                <div class="table-inside">
	                                    <table id="mileTable" class="mdl-data-table">
	                                        <thead>
	                                            <tr>
	                                            	<th>Milestone ID </th>
	                                                <th>Milestone Name </th>
	                                                <th>Milestone Date </th>
	                                                <th>Actual Date </th>
	                                                <th>Revision</th>
	                                                <th>Remarks </th>
	                                                <th>Action</th>
	                                            </tr>
	                                        </thead>
	                                         <tbody id="milestoneTableBody" >
	                                        
	                                            <tr id="mileRow0">  
	                                            	<td>
	                                                    <input id="milestone_ids0" name="milestone_ids" type="text" class="validate" 
	                                                        placeholder="Milestone ID">
	                                                </td>                                  
	                                                <td>
	                                                    <input id="milestone_names0" name="milestone_names" type="text" class="validate" 
	                                                        placeholder="Milestone Name ">
	                                                </td>
	                                                <td>
	                                                    <input id="milestone_dates0" name="milestone_dates" type="text" class="validate datepicker" 
	                                                        placeholder="Milestone Date">
	                                                    <button type="button"><i class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td>
	                                                    <input id="actual_dates0" name="actual_dates" type="text" class="validate datepicker" 
	                                                        placeholder="Actual Date">
	                                                   <button type="button"><i class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td>
	                                                    <input id="revisions0" name="revisions" type="text" class="validate" 
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
                                                <th>Current </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                         <tbody  id="revTableBody" >
                                            <tr id="revRow0">
                                                <td> <input id="revision_numbers0" name="revision_numbers" type="text" class="validate" 
                                                        placeholder="Revision Number">
                                                </td>
                                                <td class="input-field">
                                                   <i class="material-icons prefix center-align">₹</i> <input id="revised_amounts0" name="revised_amounts" min="0.01" step="0.01" type="number" class="validate"
                                                        placeholder="Revised Amount">
                                                </td>
                                                <td>
                                                    <input id="revised_docs0" name="revised_docs" type="text" class="validate datepicker" 
                                                        placeholder="Revised DOC">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td> 
                                                    <input id="revision_remarks0" name="revision_remarks" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                 <td><label><input type="hidden" class="hidden_check" id="revision_statuss0" name="revision_statuss" value="No" />
                                                 <input type="checkbox" id="revision_status0" onchange="revisionChecks('0')" class="revision_status_checking"/> <span></span> </label>	</td>       
                                                
                                                <td><a onclick="removeRev('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                           <!--  <script type="text/javascript">
	                                              
	                                                $('#revision_status0').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#revision_statuss0').val('Yes');
							                             } else{
							                              	 
							                            	  $("#revision_statuss0").prop('checked',false).removeAttr('checked');
							                            	  $("#revision_statuss0").val('No')
							                              }
							                   	    });
                                                </script> -->
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
                            </div>
                              <!-- new code  starts-->
                            <div class="container container-no-margin">
	                            <div class="container">
	                                <div class="row fixed-width">
	                                    <h5 class="center-align">Key Personnel</h5>
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table">
	                                            <thead>
	                                                <tr>
	                                                    <th>Name </th>
	                                                    <th>Designation </th>
	                                                    <th>Mobile No</th>
	                                                    <th>Email ID </th>
	                                                    <th>Action</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="keyPersonnelTableBody">
	                                                <tr id="keyPersonnelRow0">
	                                                    <td> 
	                                                    	<input id="contractKeyPersonnelNames0" name="contractKeyPersonnelNames" type="text" class="validate" placeholder="Name">
	                                                    </td>
	                                                    <td> 
	                                                    	<input id="contractKeyPersonnelDesignations0" name="contractKeyPersonnelDesignations" type="text" class="validate" placeholder="Designation">
	                                                    </td>
	                                                    <td>
	                                                        <input id="contractKeyPersonnelMobileNos0" name="contractKeyPersonnelMobileNos" type="number" class="validate" placeholder="Mobile No">
	                                                    </td>
	                                                    <td>
	                                                        <input id="contractKeyPersonnelEmailIds0" name="contractKeyPersonnelEmailIds" type="text" class="validate" placeholder="Email">
	                                                    </td>
	                                                    <td>
	                                                        <a href="javascript:void(0);" onclick="removeKeyPersonnel('0');"  class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
	                                                    </td>
	                                                </tr>
	                                            </tbody>
	                                        </table>
	                                        
	                                        <table class="mdl-data-table">
		                                        <tbody id="revTableBody">                                          
		                                            <tr>
														<td colspan="4" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addKeyPersonnelRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <input type="hidden" id="keyRowNo"  name="keyRowNo" value="0" />
                                   	 
	                                    </div>
	                                </div>
	                            </div>
	
	                            <div class="container" style="margin-bottom:30px">
	                                <div class="row fixed-width">
	                                    <h5 class="center-align">Documents</h5>
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table">
	                                            <thead>
	                                                <tr>
	                                                    <th>Name </th>
	                                                    <th style="text-align:center">Attachment</th>
	                                                    <th style="width:8%">Action</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="contractDocumentTableBody" >
	                                                <tr id="contractDocumentRow0">
	                                                    <td> <input id="contractDocumentNames0" name="contractDocumentNames" type="text" class="validate"
	                                                            placeholder="Name">
	                                                    </td>
	                                                    <td>
	                                                        <div class="normal-btn">
	                                                            <input type="file" id="contractDocumentFiles0" name="contractDocumentFiles"
	                                                                style="display:none" onchange="getFileName('0')"/>
	                                                            <label for="contractDocumentFiles0" class="btn bg-m"><i
	                                                                    class="fa fa-paperclip"></i></label>
	                                                            <span id="contractDocumentFileName0" class="filevalue"></span>
	                                                        </div>
	                                                    </td>
	                                                    <td>
	                                                        <a href="javascript:void(0);" onclick="removeContractDocument('0');" class="btn waves-effect waves-light red t-c "> <i
	                                                                class="fa fa-close"></i></a>
	                                                    </td>
	                                                </tr>
	                                            </tbody>
	                                        </table>
	                                        
	                                        <table class="mdl-data-table">
		                                        <tbody id="revTableBody">                                          
		                                            <tr>
														<td colspan="3" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addContractDocumentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <input type="hidden" id="documentRowNo"  name="documentRowNo" value="0" />
	                                    </div>
	                                </div>
	                            </div>
							</div>
                            <!-- new code  ends-->
                            
<!--                             <div class="row"> -->
<!--                                 <div class="col m2 hide-on-small-only"></div> -->
<!--                                 <div class="col m8 s12"> -->
<!--                                     <div class="file-field input-field"> -->
<!--                                         <div class="btn bg-m"> -->
<!--                                             <span>Attachment</span> -->
<!--                                             <input type="file"> -->
<!--                                         </div> -->
<!--                                         <div class="file-path-wrapper"> -->
<!--                                             <input class="file-path validate" type="text"> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <div class="col m2 hide-on-small-only"></div> -->
<!--                             </div> -->
                         <div class="container container-no-margin">    
                         	<div class="row">
                         		<h5 class="center-align">Contract Closure Details</h5>
                         	</div>
                          		<div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker">
	                                    <label for="actual_completion_date">Actual Completed Date</label>
	                                     <span id="actual_completion_dateError" class="error-msg" ></span>
	                                    <button type="button" id="actual_completion_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                	<i class="material-icons prefix center-align">₹</i>
	                                    <input id="completed_cost" name="completed_cost" type="number" min="0.01" step="0.01" class="validate">
	                                    <label for="completed_cost">Completed Cost</label>
	                                      <span id="completed_costError" class="error-msg" ></span>
	                                </div>                              
	                            </div> 
	      						<div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="final_takeover" name="final_takeover" type="text" class="validate datepicker">
	                                    <label for="final_takeover">Final Taking over by Client</label>
	                                    <button type="button" id="final_takeover_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="final_takeoverError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="completion_certificate_release" name="completion_certificate_release" type="text" class="validate datepicker">
	                                    <label for="completion_certificate_release">Release of Completion Certificate</label>
	                                     <span id="completion_certificate_releaseError" class="error-msg" ></span>
	                                    <button type="button" id="completion_certificate_release_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>	                                
	                                <div class="col s12 m4 input-field"> 
	                                    <input id="contract_closure_date" name="contract_closure_date" type="text" class="validate datepicker">
	                                    <label for="contract_closure_date">Contract Closure</label>
	                                    <button type="button" id="contract_closure_date_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="contract_closure_dateError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="final_bill_release" name="final_bill_release" type="text" class="validate datepicker">
	                                    <label for="final_bill_release">Release of Final bill</label>
	                                    <button type="button" id="final_bill_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="final_bill_releaseError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="defect_liability_period" name="defect_liability_period" type="text" class="validate datepicker">
	                                    <label for="defect_liability_period">Defect Liability Period</label>
	                                    <button type="button" id="defect_liability_period_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="defect_liability_periodError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="final_retention_release" name="retention_money_release" type="text" class="validate datepicker">
	                                    <label for="final_retention_release"> Release of Final Retention amount/BG</label>
	                                    <button type="button" id="final_retention_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="final_retention_releaseError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="pbg_release" name="pbg_release" type="text" class="validate datepicker">
	                                    <label for="pbg_release">Release of PBG</label>
	                                    <button type="button" id="pbg_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="pbg_releaseError" class="error-msg" ></span>
	                                </div>
	                                <!-- <div class="col s12 m4 input-field">
	                                    <input id="contract_closure" name="contract_closure" type="text" class="validate">
	                                    <label for="contract_closure"> Contract Closure Comment</label>
	                                    <span id="ontract_closureError" class="error-msg" ></span>
	                                </div> -->
	                                <%-- <div class="col s12 m4 input-field">
	                                 <p class="searchable_label"> <label>Status of Contract</label> </p>
	                                    <select class="validate-dropdown searchable" id="contract_status_fk" name="contract_status_fk">
	                                        <option value="" selected>Select</option>
	                                       		 <c:forEach var="obj" items="${contract_Statustype }">
			                                    	 <option value="${obj.contract_status_fk }" >${obj.contract_status_fk }</option>
			                                      </c:forEach>
	                                    </select>
	                                    <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div> --%>
	                                <div class="col m2 hide-on-small-only"></div>
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
                       </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
<!--     </div> -->



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
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>


    <script>
	   /*  $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); */
	    let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy'};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    });

        $(document).ready(function () {
        	 $('select:not(.searchable)').formSelect();
             $('.searchable').select2();
             $('#remarks').characterCounter();
        });
        
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForContractForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                            });
                        }
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
         
        function getDepartmentsList(userId) {
        	$(".page-loader").show();
        	 $("#department_fk option:not(:first)").attr("selected",false);

            if ($.trim(userId) != "") {
                var myParams = { hod_user_id_fk: userId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getDepartmentsListForContractForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var department_name = '';
                                 if ($.trim(val.department_name) != '') { department_name = $.trim(val.department_name) }
                                
                                if ($.trim(val.department_fk) != '') {
                                	 document.querySelectorAll('#department_fk > option').forEach((option) => {
                                	     if ((option.value) == ($.trim(val.department_fk))){
                                	    	 $('select[name="department_fk"]').find('option[value="' + val.department_fk + '"]').attr("selected",true);
                                	    	 $("#department_fk").select2();
                                	     }
                                	    	 
                                	 })
                                } else {
                                    $("#department_fk").append('<option value="' + val.department_fk + '">' +  $.trim(department_name) + '</option>');
                                }
                            });
                        }
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
        
        function addContract(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	
	  		
	  			$('form input[name=bg_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });		
	  			$('form input[name=issuing_banks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bank_addresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bg_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bg_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bank_revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=release_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=bg_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=codes]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=insuranceStatus]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=bg_valid_uptos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=issuing_agencys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=agency_addresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=insurance_revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurence_valid_uptos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurence_remarks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contract_milestones_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=milestone_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=milestone_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=milestone_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=actual_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=mile_remarks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			
	  			$('form input[name=contract_revision_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revision_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revision_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revised_amounts]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revised_docs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revision_remarks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractDocumentNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractDocumentFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractKeyPersonnelNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractKeyPersonnelDesignations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=contractKeyPersonnelMobileNos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=contractKeyPersonnelEmailIds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
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
		   		 	  },"contract_short_name":{
        		 		 required: false,
        		 		 maxlength: 100
        		 	  },"contract_type_fk": {
		   		 		required: true
		   		 	  },"contractor_id_fk": {
		   	 		    required: true,
		   	 	   	  },"scope_of_contract": {
		   	 		    required: false,
		   	 	   	  },"hod_user_id_fk": {
		   		 		required: false
		   		 	  },"dy_hod_user_id_fk": {
		   	 		    required: false
		   	 	   	  },"doc": {
		   		 		required: false,
   				 		dateBefore1:"#date_of_start"
		   		 	  },"awarded_cost": {
		   		 		required: false
		   		 	  },"date_of_start": {
		   		 		required: false
		   		 	  },"estimated_cost": {
		   		 		required: false
		   		 	  },"loa_letter_number": {
		   		 		required: false
		   		 	  },"loa_date":{
		   		 		 required: false
		   		 	  },"ca_no": {
		   	 		    required: false,
		   	 	   	  },"ca_date": {
		   		 		required: false
		   		 	  },"actual_completion_date": {
		   	 		    required: false
		   	 	   	  },"completed_cost": {
		   		 		required: false
		   		 	  },"contract_closure_date": {
		   		 		required: false,
	   				 	dateBefore3:"#completion_certificate_release"
		   		 	  },"completion_certificate_release":{
		   		 		 required: false,
	   				 	 dateBefore2:"#final_takeover"
		   		 	  },"final_takeover": {
		   	 		    required: false,
		   	 	   	  },"final_bill_release": {
		   		 		required: false
		   		 	  },"defect_liability_period": {
		   	 		    required: false
		   	 	   	  },"retention_money_release": {
		   		 		required: false
		   		 	  },"pbg_release":{
		   		 		 required: false
		   		 	  },"contract_closure":{
		   		 		 required: false
		   		 	  },"contract_status_fk":{
		   		 		 required: false
		   		 	  },"remarks":{
		 		 		 required: false
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
		   	 	  	 },"contract_name": {
		   	 			required: 'Required'
		   	 	  	 },"contract_short_name": {
		   	 			required: 'Required',
		   	 			maxlength : 'Contract short name must be less than or equal to 100 characters'
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
		   	 	    }else if (element.attr("id") == "contract_short_name" ){
		   	 		     document.getElementById("contract_short_nameError").innerHTML="";
		   	 			 error.appendTo('#contract_short_nameError');
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
		   	 	    }else if (element.attr("id") == "doc" ){
		   	 		     document.getElementById("docError").innerHTML="";
		   	 			 error.appendTo('#docError');
		   	 	    }else if (element.attr("id") == "awarded_cost" ){
		   	 		     document.getElementById("awarded_costError").innerHTML="";
		   	 			 error.appendTo('#awarded_costError');
		   	 	    }else if (element.attr("id") == "date_of_start" ){
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
			 	    }else if (element.attr("id") == "completion_certificate_release" ){
			 		     document.getElementById("completion_certificate_releaseError").innerHTML="";
			 			 error.appendTo('#completion_certificate_releaseError');
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
			 		     document.getElementById("pbg_releaseError").innerHTML="";
			 			 error.appendTo('#pbg_releaseError');
			 	    }else if (element.attr("id") == "contract_closure" ){
			 		     document.getElementById("ontract_closureError").innerHTML="";
			 			 error.appendTo('#ontract_closureError');
			 	    }else if (element.attr("id") == "contract_status_fk" ){
		 		     document.getElementById("contract_status_fkError").innerHTML="";
		 			 error.appendTo('#contract_status_fkError');
		   	 	    }else if (element.attr("id") == "remarks" ){
		  	 		     document.getElementById("remarksError").innerHTML="";
			 			 error.appendTo('#remarksError');}
		   	 },invalidHandler: function (form, validator) {
		         var errors = validator.numberOfInvalids();
		         if (errors) {
		             var position = validator.errorList[0].element;
		             jQuery('html, body').animate({
		                 scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
		             }, 1000);
		         }
		     },submitHandler: function(form) {
		   	    // do other things for a valid form
		   	    //form.submit();
		   	    //return true;
		   	  }
		});
        
        
        $.validator.addMethod("dateBefore1", function(value, element) {
            var fromDateString = $('#date_of_start').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Planned Doc date must be after Date of start");
    	
    	$.validator.addMethod("dateBefore2", function(value, element) {
            var fromDateString = $('#final_takeover').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
         
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
        }, "Release of Completion Certificate date must be after Final takeover by client date");
    	
    	$.validator.addMethod("dateBefore3", function(value, element) {
            var fromDateString = $('#completion_certificate_release').val(); 
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
         
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
        }, "Contract closure date date must be after Release of Completion Certificate date");
        
        
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
       var html = '<tr id="bankRow'+rNo+'">  <td> <input id="codes'+rNo+'" type="text" class="validate" name="codes" placeholder="Code">       </td><td> <div>'
		   +'<select  name="bg_type_fks" id="bg_type_fks'+rNo+'"  class="searchable">'	   			
		   +'<option value="" >Select</option>'
		 	<c:forEach var="obj" items="${bankGuaranteeTYpe }">
		  +'<option value="${obj.bg_type_fk }">${obj.bg_type_fk }</option>'
			</c:forEach>
		   +'</select></div></td>'
		   +'<td> <input id="issuing_banks'+rNo+'" name="issuing_banks"  type="text" class="validate"  placeholder="Issuing Bank"></td>'
		   //+'<td><input id="bank_addresss'+rNo+'" name ="bank_addresss" type="text" class="validate"  placeholder="Bank Address"></td>'
		   +'<td><input id="bg_numbers'+rNo+'" name="bg_numbers" type="text" class="validate"  placeholder="BG / FDR Number"></td>'
		   +'<td class="input-field"><i class="material-icons prefix center-align">₹</i><input id="bg_values'+rNo+'" name="bg_values" type="number" min="0.01" step="0.01" class="validate"  placeholder="Amount"></td>'
		   //+'<td><input id="bank_revisions'+rNo+'" name="bank_revisions" type="text" class="validate"  placeholder="Revision"></td>'
		   +'<td><input id="bg_dates'+rNo+'" name="bg_dates" type="text" class="validate datepicker" placeholder="BG /FDR Date"> <button type="button"><i class="fa fa-calendar"></i></button>'
		   +'<td><input id="bg_valid_uptos'+rNo+'" name="bg_valid_uptos" type="text" class="validate datepicker"  placeholder="Valid Upto"><button type="button"><i class="fa fa-calendar"></i></button></td>'
		   //+'<td><input id="remarks'+rNo+'" name ="remarkss" type="text" class="validate" value="${bankObj.remarks }" placeholder="Remarks"></td>'
		   +'<td><input id="release_dates'+rNo+'" name="release_dates" type="text" class="validate datepicker" placeholder="Release Date"> <button type="button"><i class="fa fa-calendar"></i></button></td>'
		   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeBank('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
	 
		 $('#bankTableBody').append(html);
		 $("#bankRowNo").val(rNo);
		 $('.searchable').select2();
		 
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
		   +'<select  name="insurance_type_fks" id="insurance_type_fks'+rNo+'" class="searchable" >'	   			
		   +'<option value="" >Select</option>'
		   <c:forEach var="obj" items="${insurance_type }">
			  +' <option value= "${ obj.insurance_type}">${ obj.insurance_type}</option>'
		  </c:forEach>
		   +'</select></div></td>'
		   +'<td> <input id="issuing_agencys'+rNo+'" name="issuing_agencys" type="text" class="validate"  placeholder="Issuing Agency"></td>'
		   +'<td><input id="agency_addresss'+rNo+'" name="agency_addresss" type="text" class="validate" placeholder="Agency Address"></td>'
		   +'<td><input id="insurance_numbers'+rNo+'" name="insurance_numbers" type="text" class="validate"  placeholder="Insurance Number"></td>'
		   +'<td class="input-field"><i class="material-icons prefix center-align">₹</i><input id="insurance_values'+rNo+'" name="insurance_values" type="number" min="0.01" step="0.01" class="validate" placeholder="Insurance Value"></td>'
		   +'<td><input id="insurance_revisions'+rNo+'" name="insurance_revisions" type="text" class="validate" placeholder="Revision"></td>'
		   +'<td><input id="insurence_valid_uptos'+rNo+'" name="insurence_valid_uptos" type="text" class="validate datepicker" placeholder="Valid Upto"> <button type="button" ><i class="fa fa-calendar"></i></button></td>'
		   +'<td><input id="insurence_remarks'+rNo+'" name="insurence_remarks"  type="text" class="validate"  placeholder="Remarks"></td>'
		   +'<td><label> <input type="hidden" id="insuranceStatus'+rNo+'" name="insuranceStatus" value="No" /><input type="checkbox" id="insuranceStatuss'+rNo+'" /> <span></span> </label></td>'
		   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeInsurence('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
	
		 $('#insurenceTableBody').append(html);
		 $("#insurenceRowNo").val(rNo);
		 $('.searchable').select2();
		 $("#insurence_valid_uptos"+rNo).datepicker({
		      	 format:'dd-mm-yyyy',
		          onSelect: function () {
			    	     $('.confirmation-btns .datepicker-done').click();
			    	  }
		 });
		 $("#insuranceStatuss"+rNo).on('change', function(e){
             if($(this).prop('checked'))
             {
            	// $(".part").prop('disabled', true);
                 $("#insuranceStatus"+rNo).val('Yes');
             } else{
              	 
            	  $("#insuranceStatus"+rNo).prop('checked',false).removeAttr('checked');
            	  $("#insuranceStatus"+rNo).val('No')
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
	 	   +'<td><input id="milestone_ids'+rNo+'" name="milestone_ids" type="text" class="validate" placeholder="Milestone ID"></td>'
		   +'<td><input id="milestone_names'+rNo+'" name="milestone_names" type="text" class="validate"  placeholder="Milestone Name "></td>'
		   +'<td><input id="milestone_dates'+rNo+'" name="milestone_dates" type="text" class="validate datepicker"  placeholder="Milestone Date"><button type="button"><i class="fa fa-calendar"></i></button></td>'
		   +'<td><input id="actual_dates'+rNo+'" name="actual_dates" type="text" class="validate datepicker"   placeholder="Actual Date">  <button type="button"><i  class="fa fa-calendar"></i></button></td>'
		   +'<td><input id="revisions'+rNo+'" name="revisions" type="text" class="validate" placeholder="Revision"></td>'
		   +'<td>  <input id="mile_remarks'+rNo+'" name="mile_remarks" type="text" class="validate" placeholder="Remarks"></td>'
		   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeMilestone('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
		   +'</tr>';
	
		 $('#milestoneTableBody').append(html);
		 $("#mileRowNo").val(rNo);
		 $('.searchable').select2();
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
			   +'<td class="input-field"><i class="material-icons prefix center-align">₹</i><input id="revised_amounts'+rNo+'" name="revised_amounts" min="0.01" step="0.01" type="number" class="validate"  placeholder="Revised Amount"></td>'
			   +'<td><input id="revised_docs'+rNo+'" name="revised_docs" type="text" class="validate datepicker"  placeholder="Revised DOC">'
			   +'<button type="button"><i class="fa fa-calendar"></i></button></td>'
			   +'<td> <input id="revision_remarks'+rNo+'" name="revision_remarks" type="text" class="validate"  placeholder="Remarks"></td>'
			   +'<td><label> <input type="hidden" id="revision_statuss'+rNo+'" name="revision_statuss" class="hidden_check" value="No" /><input type="checkbox" id="revision_status'+rNo+'" onchange="revisionChecks('+rNo+')" class="revision_status_checking"/> <span></span> </label></td>'
			   +'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeRev('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
			   +'</tr>';
		
			 $('#revTableBody').append(html);
			 $("#revRowNo").val(rNo);
			 $('.searchable').select2();
			 $("#revised_docs"+rNo).datepicker({
		      	 format:'dd-mm-yyyy',
		          onSelect: function () {
			    	     $('.confirmation-btns .datepicker-done').click();
			    	  }
		      });
			/*  $("#revision_status"+rNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                	// $(".part").prop('disabled', true);
                     $("#revision_statuss"+rNo).val('Yes');
                 } else{
                  	 
                	  $("#revision_status"+rNo).prop('checked',false);
                	  $("#revision_statuss"+rNo).val('No')
                  }
       	    }); */
	} 	


	function removeRev(rowNo){
		$("#revRow"+rowNo).remove();
	}
	
	
	function addKeyPersonnelRow(){		
		 var rowNo = $("#keyRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var total = 0;
		 var html = '<tr id="keyPersonnelRow'+rNo+'">'
					 +'<td> <input id="contractKeyPersonnelNames'+rNo+'" name="contractKeyPersonnelNames" type="text" class="validate" placeholder="Name">'
					 +'</td>'
					 +'<td> <input id="contractKeyPersonnelDesignations'+rNo+'" name="contractKeyPersonnelDesignations" type="text" class="validate" placeholder="Name">'
					 +'</td>'
					 +'<td>'
					 +'<input id="contractKeyPersonnelMobileNos'+rNo+'" name="contractKeyPersonnelMobileNos" type="number" class="validate" placeholder="Mobile No">'
					 +'</td>'
					 +'<td>'
					 +'<input id="contractKeyPersonnelEmailIds'+rNo+'" name="contractKeyPersonnelEmailIds" type="text" class="validate" placeholder="Email">'
					 +'</td>'
					 +'<td>'
					 +'<a href="javascript:void(0);" onclick="removeKeyPersonnel('+rNo+');"  class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
					 +'</td>'
			   		 +'</tr>';
		
			 $('#keyPersonnelTableBody').append(html);
			 $("#keyRowNo").val(rNo);
	}
	
	function removeKeyPersonnel(rowNo){
		$("#keyPersonnelRow"+rowNo).remove();
	}

	function addContractDocumentRow(){		
		 var rowNo = $("#documentRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var total = 0;
		 var html = '<tr id="contractDocumentRow'+rNo+'">'
					 +'<td> <input id="contractDocumentNames'+rNo+'" name="contractDocumentNames" type="text" class="validate" placeholder="Name"> </td>'
					 +'<td>'
					 +'<div class="normal-btn">'
					 +'<input type="file" id="contractDocumentFiles'+rNo+'" name="contractDocumentFiles" style="display:none" onchange="getFileName('+rNo+')" />'
					 +'<label for="contractDocumentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
					 +'<span id="contractDocumentFileName'+rNo+'" class="filevalue"></span>'
					 +'</div>'
					 +'</td>'
					 +'<td>'
					 +'<a href="javascript:void(0);" onclick="removeContractDocument('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
					 +'</td>'
			   		 +'</tr>';
		
			 $('#contractDocumentTableBody').append(html);
			 $("#documentRowNo").val(rNo);
	} 
	function removeContractDocument(rowNo){
		$("#contractDocumentRow"+rowNo).remove();
	}
	
	
	function getFileName(rowNo){
		var filename = $('#contractDocumentFiles'+rowNo)[0].files[0].name;
	    $('#contractDocumentFileName'+rowNo).html(filename);
	}
	

	function revisionChecks(a){
		//alert('hai');
		/* $('input[name=revision_statuss]').each(function(j,item){
			$("#"+item.id).val('No');
		}); */
		$('.revision_status_checking').each(function(i,val){
			//$(".revision_status_checking").prop('checked',false);
				if($('#revised_amounts'+i).val()=="" && $('#revised_docs'+i).val()==""){
					$("#revision_status"+i).prop('checked',false);
			    }
				
				if(($('#revised_amounts'+a).val()=="" && $('#revised_docs'+a).val()!="") || ($('#revised_amounts'+a).val()!="" && $('#revised_docs'+a).val()=="")){
					//$("#revision_status"+i).prop('checked',true);
					if($("#revision_status"+i).prop('checked')){
						if($('#revised_amounts'+i).val()!="" && $('#revised_docs'+i).val()!=""){
							$(".revision_status_checking").prop('checked',false);
						}
						$(".hidden_check").val('No');
						
						$("#revision_status"+a).prop('checked',true);
						$("#revision_statuss"+a).val('yes');
						if(i != a){
							if($("#revision_status"+i).prop('checked') == true 	&& $('#revised_amounts'+i).val() !="" && $('#revised_docs'+a).val()==""){
								
								$("#revision_status"+i).prop('checked',false);							
												
							}
							if($("#revision_status"+i).prop('checked') == true 	&& $('#revised_docs'+i).val()!="" && $('#revised_amounts'+a).val()==""){
								
								$("#revision_status"+i).prop('checked',false);							
												 
							}
						}
						if(i == a ){
							$("#revision_status"+i).prop('checked',true);	
						}
					}
			    }
				
				if($('#revised_amounts'+a).val()!="" && $('#revised_docs'+a).val()!=""){
			    	//$("#revision_status"+i).prop('checked',true);
					if($("#revision_status"+i).prop('checked')){
						$(".revision_status_checking").prop('checked',false);
						$(".hidden_check").val('No');
						$("#revision_status"+a).prop('checked',true);
						$("#revision_statuss"+a).val('yes');
						//$("#revision_status"+i).prop('checked',true);
						//if($('#revised_amounts'+a).val()!="" && $('#revised_docs'+a).val()!=""){$(".revision_status_checking").prop('checked',false);$("#revision_status"+a).prop('checked',true);}
					}
			    	
			    }
				
		});		
		
		/* 
		$('.revision_status_checking').each(function(i,val){
			if($(this).prop('checked')){
				if($('#revised_amounts'+i).val()!="" && $('#revised_amounts'+i).val()!=undefined){	
					console.log(i)
					$("#revision_status"+i).prop('checked',true);
				}
			}
			
		}); */

	}

    </script>

</body>

</html>