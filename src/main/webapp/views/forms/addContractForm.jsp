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
    <title>Add Contract - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">         
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style>

        #ravTable .datepicker~button,
        #insurenceTable .datepicker~button,
        #bankTable .datepicker~button,
        #mileTable .datepicker~button {
            top: 26px;
        }
		.my-error {
   			 color:red;
   			 font-size: 12px
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
            margin-left:auto !important;
            margin-right:auto !important;
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
	   /* cost unit dropdown , lable and input styling starts here  */
		.pt-5{
			padding-top:5px !important;
		}
		.cost_dropdown{
			min-width:95px !important;
		}
		.input-field .prefix.cost ~ input,
		.input-field .prefix.cost ~ label,
		.input-field .prefix.cost ~ .validate ~ label {
		    margin-left: 2rem;
		    width: 92%;
		    width: calc(100% - 2rem);
		}
		.input-field.col .prefix.cost ~ label,
		.input-field.col .prefix.cost ~ .validate ~ label {
		    width: calc(100% - 2rem - 1.5rem);
		}
		/* cost unit dropdown , lable and input styling ends here  */
		.min-200{
			min-width:200px;
		}
		@media only screen and (max-width: 768px){
			.mobile_responsible_table>tbody> tr:not(.datepicker-row):not(.mobile_hide_row) {
			    border-bottom: 3px solid #2A9D8F;
			}
			.h-auto{
				height:auto !important;
			}
			.input-field p.searchable_label {
			    margin-top: -24px !important;
			}
			 .filevalue {
			    width: 200%;
			    white-space: break-spaces;
			}
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
	                                <div class="col s6 m4 offset-m2 input-field">
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
	                                <div class="col s6 m4 input-field">
	                                <p class="searchable_label">Work <span class="required">*</span></p>
	                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" 
	                                    	onchange="resetProjectsDropdowns(this.value);">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${worksList }">
	                                      	   <option workShortName="${obj.work_short_name }" value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                         </c:forEach>
	                                    </select>
	                                     <span id="work_id_fkError" class="error-msg" ></span>
	                                </div>
	                                <input type="hidden" id="work_short_name" name="work_short_name"/>
	                            </div>
								   <input type="hidden" id="contract_id_code" name="contract_id_code"/>  
	                                    <div class="row">
	  										<div class="col s6 m4 input-field offset-m2">
	  										 	<p class="searchable_label">HOD<span class="required">*</span></p>
	                                            <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown searchable" onchange="getDyHodList();"  <c:if test="${sessionScope.USER_TYPE eq 'HOD'  || sessionScope.USER_TYPE eq 'DyHOD'}"> disabled  </c:if>> 
	                                     		  <option value="">Select</option> 
	                                                 <%-- <c:forEach var="obj" items="${hodList }"> 
			                                    	  <option value="${obj.user_id }" <c:if test="${sessionScope.USER_ID eq obj.user_id}">selected</c:if> > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                        </c:forEach>  --%>  
	                                            </select> 
												<input type="hidden"  name="department_fk" id="department_fk"/>
												<!-- <label for="hod_user_id_fk">HOD</label>  -->
	                                            <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                        <div class="col s6 m4 input-field">
	                                        	<p class="searchable_label">Dy HOD<span class="required">*</span></p>
	                                            <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown searchable" onchange="getHodList();" <c:if test="${sessionScope.USER_TYPE eq 'DyHOD'}"> disabled  </c:if>>
	                                                <option value="">Select</option>
	                                                 <%--  <c:forEach var="obj" items="${dyHodList }"> 
			                                    	  <option value="${obj.user_id }" > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                        </c:forEach>   --%> 
	                                            </select>
												<!-- <input name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" type="text" class="validate" style="margin-top:10px">
	                               		     	<label for="dy_hod_user_id_fk">Dy HOD</label> -->
	                                            <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                    </div>
	              
	                            <div class="row"> 
	                            	<div class="col m8 offset-m2 s12">
										<div class="row fixed-width">
									        <h5 class="center-align">Department Details</h5>
									        <div class="table-inside">
									            <table id="departmentTable" class="mdl-data-table mobile_responsible_table" >
									                <thead>
									                    <tr>
									                        <th style="width:22%">Department<span class="required"> *</span></th>
															<th>Select Executives</th>
															<th style="width:8%">Action</th>
									                    </tr>
									                </thead>
									                <tbody id="departmentTableBody">
									                    <tr id="departmentRow0">
									                        <td data-head="Department" class="input-field">
									                             <select class="searchable validate-dropdown" name="department_fks" class="searchable"
									                                id="department_fk0" onchange="getExecutivesList('0');">
									                                	<option value="" >Select</option>  
																          <c:forEach var="obj" items="${departmentList }">
								                                      	    <option value= "${ obj.department_fk}" >${ obj.department_name}</option>
								                                          </c:forEach>
									                              </select>
									                               <span id="deptError0" class="my-error"></span>
									                        </td>
									                        <td data-head="Select Executives" class="input-field h-auto">
									                            <select class="searchable validate-dropdown" name="responsible_people_id_fks" onchange="fileCount('0');"
									                                id="responsible_people_id_fk0" multiple="multiple">
									                                <option value="" >Select</option>
									                             
									                            </select>
									                            <span id="personError0" class="my-error"></span>
									                            <input type="hidden" id="filecounts0" name="filecounts" value="0">
									                        </td>
									                        <td class="mobile_btn_close">
									                            <a onclick="removeDepartment('0');"
									                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
									                        </td>
									                    </tr>
									                 
									                </tbody>
									            </table>
									            <table  class="mdl-data-table" style="margin-bottom: 20px">
			                                        <tbody>                                          
			                                            <tr>
			                                   				<td colspan="3" style="text-align: right;" ><a   class="btn waves-effect waves-light bg-m t-c "  onclick="addDepartmentRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table>
			                                    <input type="hidden" id="deptRowNo"  name="deptRowNo" value="0" />
									        </div>
									    </div>
									</div>
								</div>
	                            <div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <textarea name="contract_name" id="contract_name" type="text" class="validate materialize-textarea" ></textarea>
	                                    <label for="contract_name">Contract Name <span class="required">*</span></label>
	                                    <span id="contract_nameError" class="error-msg" ></span>
	                                </div>	                              
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <input name="contract_short_name" id="contract_short_name" type="text" class="validate" >
	                                    <label for="contract_short_name">Contract Short Name</label>
	                                      <span id="contract_short_nameError" class="error-msg" ></span>
	                                </div>
	                            </div>
	
	                            <div class="row">	
	                                <div class="col s6 m4 input-field offset-m2">
	                                 <p class="searchable_label">Contract Type <span class="required">*</span></p>
	                                    <select name="contract_type_fk" id="contract_type_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                       	   <c:forEach var="obj" items="${contract_type }">
			                                     <option value="${obj.contract_type_fk }" >${obj.contract_type_fk }</option>
			                                   </c:forEach>
	                                    </select>                                   
	                                     <span id="contract_type_fkError" class="error-msg" ></span>	                                    
	                                </div>	                                
	                                <div class="col s6 m4 input-field">
	                                 <p class="searchable_label">Contractor Name <span class="required">*</span></p>
	                                    <select name="contractor_id_fk" id="contractor_id_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                       	    <c:forEach var="obj" items="${contractors }">
			                                      <option value="${obj.contractor_id_fk }" >${obj.contractor_name }</option>
			                                    </c:forEach>
	                                    </select>
	                                    <span id="contractor_id_fkError" class="error-msg" ></span>
	                                </div>                             
	                            </div>
	                            <div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <textarea id="scope_of_contract" name="scope_of_contract" class="materialize-textarea validate" data-length="1000">${contractDeatils.scope_of_contract }</textarea>
	                                    <label for="scope_of_contract">Scope of Contract</label>
	                                 <span id="scope_of_contractError" class="error-msg" ></span>                                    
	                                </div>
	                           
	                                <div class="col s6 m4 input-field offset-m2">
	                                    <input id="loa_letter_number" name="loa_letter_number" type="text" class="validate">
	                                    <label for="loa_letter_number">LOA Letter No</label>
	                                    <span id="loa_letter_numberError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 input-field">
	                                    <input id="loa_date" name="loa_date" type="text" class="validate datepicker">
	                                    <label for="loa_date">LOA Date</label>
	                                     <span id="loa_dateError" class="error-msg" ></span>
	                                    <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col s6 m4 input-field offset-m2">
	                                    <input id="ca_no" name="ca_no" type="text" class="validate">
	                                    <label for="ca_no">CA No</label>
	                                    <span id="ca_noError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 input-field">
	                                    <input id="ca_date" name="ca_date" type="text" class="validate datepicker">
	                                    <label for="ca_date">CA Date</label>
	                                    <span id="ca_dateError" class="error-msg" ></span>
	                                    <button type="button" id="ca_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <!-- <div class="col s12 m4 input-field">
	                                    <input id="date_of_start" name="date_of_start" type="text" class="validate datepicker">
	                                    <label for="date_of_start">Date of Start</label>
	                                     <span id="date_of_startError" class="error-msg" ></span>
	                                    <button type="button" id="date_of_start_icon"><i class="fa fa-calendar"></i></button>
	                                </div> -->
	                                	<div class="col s9 m3 input-field offset-m2">
	                                    <i class="material-icons prefix cost">₹</i>
	                                    <input id="estimated_cost" name="estimated_cost" type="number" min="0.01" step="0.01" class="validate">
	                                    <label for="estimated_cost">Detailed Estimated cost</label>
	                                    <span id="estimated_costError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s3 m1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units validate-dropdown" id="estimated_cost_units" name="estimated_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }">${obj.unit }</option>
                                   		    </c:forEach>
	                                	</select>
	                                	<span id="estimated_cost_unitsError" class="error-msg" ></span>
                                	</div>                                	
	                                <div class="col s9 m3 input-field">
	                                	<i class="material-icons prefix cost">₹</i>
	                                    <input id="awarded_cost" name="awarded_cost" type="number" min="0.01" step="0.01" class="validate">
	                                    <label for="awarded_cost">Awarded cost</label>
	                                    <span id="awarded_costError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s3 m1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units validate-dropdown" id="awarded_cost_units" name="awarded_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }">${obj.unit }</option>
                                   		    </c:forEach>
	                                	</select>
	                                	<span id="awarded_cost_unitsError" class="error-msg" ></span>
                                	</div>
                                	
	                            </div>
	                            <div class="row">
	                                <div class="col s6 m4 input-field offset-m2">
	                                    <input name="doc" id="doc" type="text" class="validate datepicker">
	                                    <label for="doc">Original DOC</label>
	                                    <button type="button" id="doc_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="docError" class="error-msg" ></span>
	                                </div>		                                
	                                <div class="col s6 m4 input-field">
	                                 <p class="searchable_label"> <label>Status of Contract</label> </p>
	                                    <select class="validate-dropdown searchable" id="contract_status_fk" name="contract_status_fk">
	                                         <option value="" selected>Select</option>
                                       		 <c:forEach var="obj" items="${contract_Statustype }">
		                                    	 <option value="${obj.contract_status_fk }" <c:if test="${obj.contract_status_fk eq 'Not Started' }">selected</c:if>>${obj.contract_status_fk }</option>
		                                     </c:forEach>
	                                    </select>
	                                    <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div>         	                                
	                            </div>	   
	                         
	                            <div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000"></textarea>
	                                    <label for="remarks">Remarks</label>
	                                      <span id="remarksError" class="error-msg"></span>
	                                </div>
	                            </div>                        
	                        
 							<div class="container container-no-margin">
	                           
	
	                            <div class=" col m8 offset-m2 s12" style="margin-bottom:30px; padding:0;">
	                                <div class="row fixed-width">
	                                    <h5 class="center-align">Documents</h5>
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table mobile_responsible_table">
	                                            <thead>
	                                                <tr>
	                                                	<th>File Type </th>
	                                                    <th class="min-200">Name </th>
	                                                    <th style="text-align:center">Attachment</th>
	                                                    <th> </th>
	                                                    <th style="width:8%">Action</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="contractDocumentTableBody" >
	                                                <tr id="contractDocumentRow0">
	                                                	<td data-head="File Type" class="input-field">
															<select  name="contract_file_types"  id="contract_file_types0"  class="validate-dropdown searchable">
			                                   					 <option value="" >--Select--</option>
			                                         			  <c:forEach var="obj" items="${contractFileTypeList}">
			                    					  				 <option value="${obj.contract_file_type }">${obj.contract_file_type}</option>
			                                          			  </c:forEach>
			                               					  </select>
													    </td>
	                                                    <td data-head="Name" class="input-field"> <input id="contractDocumentNames0" name="contractDocumentNames" type="text" class="validate"
	                                                            placeholder="Name">
	                                                    </td>
	                                                    <td data-head="Attachment" class="input-field">
	                                                        <span class="normal-btn">
	                                                            <input type="file" id="contractDocumentFiles0" name="contractDocumentFiles"
	                                                                style="display:none" onchange="getFileName('0')"/>
	                                                            <label for="contractDocumentFiles0" class="btn bg-m"><i
	                                                                    class="fa fa-paperclip"></i></label>
	                                                            <span id="contractDocumentFileName0" class="filevalue"></span>
	                                                        </span>
	                                                    </td>
	                                                    <td>
	                                                       
	                                                    </td>
	                                                    <td class="mobile_btn_close">
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
						

                            <div class="row">
                                <div class="col s6 m4 mt-brdr center-align offset-m2">
                                    <div class="m-1">
                                        <button type="button" onclick="addContract();" class="btn waves-effect waves-light bg-m" style="min-width:90px;">Add</button>
                                    </div>
                                </div>
                                <div class="col s6 m4 mt-brdr center-align">
                                    <div class="m-1">
                                        <a href="<%=request.getContextPath()%>/contract"class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
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
	    	var options = {format: 'dd-mm-yyyy',autoClose:true};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    });
		var loggedin_user_id = '${sessionScope.USER_ID}';
        $(document).ready(function () {
        	 $('select:not(.searchable):not(.units)').formSelect();
             $('.searchable').select2();
             $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
             $('#remarks').characterCounter();

             getHodList();
             getDyHodList();
        });
     
        function getExecutivesList(num) {
        	$(".page-loader").show();
        	var count = Number(num);
        	var department_fk = $('#department_fk'+count).val();
        	/* var id =  $("#departmentTable tbody tr:first-of-type >td:first-of-type").find('.searchable').attr("id");  
        	var deptFirst = $('#'+id).val(); */
        	
        	$("#responsible_people_id_fk"+count+" option:not(:first)").attr("selected",false);
        	$("#responsible_people_id_fk"+count+" option:not(:first)").remove();
            if ($.trim(department_fk) != "") {
            	
                var myParams = { department_fk: department_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getExecutivesListForContractForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var userName = '';
	                        	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
                                 var designation = '';
                                 if ($.trim(val.designation) != '') { designation = $.trim(val.designation) }
                                
                                if ($.trim(hod_user_id_fk) != '') {
                                     $("#responsible_people_id_fk"+count).append('<option  value="' + val.hod_user_id_fk + '" >'  +  $.trim(designation) + $.trim(userName) + '</option>');
                                 } else {
                                     $("#responsible_people_id_fk"+count).append('<option  value="' + val.hod_user_id_fk + '" >'  +  $.trim(designation) + $.trim(userName) +'</option>');
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
        
        function fileCount(Rno){
        	var count = $('#responsible_people_id_fk'+Rno+' option:selected').length;
        	$('#filecounts'+Rno).val(count)
        }
  
        function addDepartmentRow(){
        	 var rowNo = $("#deptRowNo").val();
    		 var rNo = Number(rowNo)+1;
    		 var no = 0;
    		 var html = '<tr id="departmentRow'+rNo+'">'
    			   +'<td data-head="Department" class="input-field">'
    			   +'<select class="searchable validate-dropdown" name="department_fks" class="searchable" id="department_fk'+rNo+'" onchange="getExecutivesList('+rNo+');">'
    			   			+'<option value="" >Select</option> '
    			   			<c:forEach var="obj" items="${departmentList }">
    			   				+'<option value= "${ obj.department_fk}" >${ obj.department_name}</option>'
    			   			</c:forEach>
    			   +' </select><span id="deptError'+rNo+'" class="my-error"><input id="filecounts'+rNo+'"  name="filecounts"  type="hidden"></td>'
    			   +'<td data-head="Select Executives" class="input-field h-auto">'
    			   		+'<select class="searchable validate-dropdown" name="responsible_people_id_fks" id="responsible_people_id_fk'+rNo+'" onchange="fileCount('+rNo+')"  multiple="multiple">'
    			   			+'<option value="" >Select</option>'
    			   
    			   +'</select><span id="personError'+rNo+'" class="my-error"></span></td>'
    			   +'<td class="mobile_btn_close"> <a onclick="removeDepartment('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    			   +'</tr>';
    		
    			 $('#departmentTableBody').append(html);
    			 $("#deptRowNo").val(rNo);
    			 $('.searchable').select2();
        }
        
        function removeDepartment(rowNo){
        	$("#departmentRow"+rowNo).remove();
        	var id =  $("#departmentTable tbody tr:first-of-type >td:first-of-type").find('.searchable').attr("id");  
        	var deptFirst = $('#'+id).val();
        	$('#department_fk').val(deptFirst);
        }
        
        function getHodList() {
        	$(".page-loader").show();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
        	var reporting_to_id_srfk = $("#dy_hod_user_id_fk").find('option:selected').attr("name");
        	if ($.trim(hod_user_id_fk) == "") {
            	$("#hod_user_id_fk option:not(:first)").attr("selected",false);
            	//var myParams = { hod_user_id_fk: hod_user_id_fk, dy_hod_user_id_fk: reporting_to_id_srfk };
            	var myParams = {};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getHodList",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var userName = '';
 	                        	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
 	                        	   var deptCode =  val.contract_id_code;
	                        	   $("#contract_id_code").val(deptCode);
	                        	   $('#department_fk').val(val.department_fk);
      	                          
                              	   if(val.hod_user_id_fk == loggedin_user_id){
                              	   		$("#hod_user_id_fk").append('<option value="' + val.hod_user_id_fk + '" selected>' + $.trim(val.designation) + userName + '</option>');
                              	   }else{
                              	   		$("#hod_user_id_fk").append('<option value="' + val.hod_user_id_fk + '">' + $.trim(val.designation) + userName + '</option>');
                              	   }
                            });
                            if($.trim(reporting_to_id_srfk) != ''){
                            	$("#hod_user_id_fk").val(reporting_to_id_srfk);      
                            }
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
            	if($.trim(reporting_to_id_srfk) != ''){
                   $("#hod_user_id_fk").val(reporting_to_id_srfk);      
                }
            }
        }
        
        function getDyHodList() {
        	$(".page-loader").show();
        	$("#dy_hod_user_id_fk option:not(:first)").remove();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
            //var myParams = { hod_user_id_fk: hod_user_id_fk, dy_hod_user_id_fk: dy_hod_user_id_fk };
           	var myParams = { hod_user_id_fk: hod_user_id_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getDyHodList",
                data: myParams, cache: false,async:false,
                success: function (data) {
                	var flag = false;
                    if (data.length > 0) {                    	
                        $.each(data, function (i, val) {
                       	   var userName = '';
                    	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
                           if(val.dy_hod_user_id_fk == loggedin_user_id){
                        	    flag = true;
                                $("#dy_hod_user_id_fk").append('<option name="'+val.reporting_to_id_srfk+'" value="' + val.dy_hod_user_id_fk + '" selected>' + $.trim(val.designation) + userName + '</option>');
                           }else{
                                $("#dy_hod_user_id_fk").append('<option name="'+val.reporting_to_id_srfk+'" value="' + val.dy_hod_user_id_fk + '">' + $.trim(val.designation) + userName + '</option>');
                           }
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                    if(flag){
                    	getHodList();
                    }
                },error: function (jqXHR, exception) {
 	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
            });
        }
        
     
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
                                $("#work_id_fk").append('<option workShortName="' + val.work_short_name + '" value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
        	var rowCount = $('#departmentTableBody tr').length;
        	var c = $('[name=responsible_people_id_fks]').length;
  			for(var i=0; i<= (rowCount-1); i++){ 
  				var resp_Person = $("#responsible_people_id_fk"+i).val();
  				if(resp_Person == ""){
  					$("#responsible_people_id_fk"+i).val("");
  					var v = $("#responsible_people_id_fk"+i).val();
  					$("#filecounts"+i).val(1);
  					$('#responsible_people_id_fk' + i + ' option[value=""]').hide();
  				}
  			}
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	
	  			
	  			var work_short_name = $("#work_id_fk").find('option:selected').attr("workShortName");
	  			$("#work_short_name").val(work_short_name);
	  			var estimated_cost = $('#estimated_cost').val();
	  			var awarded_cost = $('#awarded_cost').val();
	  			if(estimated_cost == ""){
	  				$('#estimated_cost_units').val("");
	  			}
	  			if(awarded_cost == ""){
	  				$('#awarded_cost_units').val("");
	  			}
	  			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });		
	  			$('form input[name=responsible_people_id_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			
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
		   		 		required: true
		   		 	  },"dy_hod_user_id_fk": {
		   	 		    required: true
		   	 	   	  },"doc": {
		   		 		required: false
   				 		//dateBefore1:"#date_of_start"
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
				 	  },"estimated_cost_units":{
        		 		 required: function(element){
        		             return $("#estimated_cost").val()!="";
        		         }
        		 	  },"awarded_cost_units":{
        		 		 required: function(element){
        		             return $("#awarded_cost").val()!="";
        		         }
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
				 	  },"estimated_cost_units":{
        	 	  		required: 'Units required'
        		 	  },"awarded_cost_units":{
        	 	  		required: 'Units required'
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
		   	 	    }else if (element.attr("id") == "estimated_cost_units" ){
       	 		     document.getElementById("estimated_cost_unitsError").innerHTML="";
    	 			 error.appendTo('#estimated_cost_unitsError');
        	 	    }else if (element.attr("id") == "awarded_cost_units" ){
    	 		     document.getElementById("awarded_cost_unitsError").innerHTML="";
    	 			 error.appendTo('#awarded_cost_unitsError');
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
		   +'<td class="input-field"><i class="material-icons prefix cost">₹</i><input id="bg_values'+rNo+'" name="bg_values" type="number" min="0.01" step="0.01" class="validate"  placeholder="Amount"></td>'
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
		   +'<td class="input-field"><i class="material-icons prefix cost">₹</i><input id="insurance_values'+rNo+'" name="insurance_values" type="number" min="0.01" step="0.01" class="validate" placeholder="Insurance Value"></td>'
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
			   +'<td class="input-field"><i class="material-icons prefix cost">₹</i><input id="revised_amounts'+rNo+'" name="revised_amounts" min="0.01" step="0.01" type="number" class="validate"  placeholder="Revised Amount"></td>'
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
			  $("#revision_status"+rNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                	// $(".part").prop('disabled', true);
                     $("#revision_statuss"+rNo).val('Yes');
                 } else{
                  	 
                	  $("#revision_status"+rNo).prop('checked',false);
                	  $("#revision_statuss"+rNo).val('No')
                  }
       	    }); 
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
					 +'<td data-head="File Type" class="input-field">'
						+'<select  name="contract_file_types"  id="contract_file_types'+rNo+'"  class="validate-dropdown searchable">'
							+ '<option value="" >--Select--</option>'
						  <c:forEach var="obj" items="${contractFileTypeList}">
				  				+ '<option value="${obj.contract_file_type }">${obj.contract_file_type}</option>'
						  </c:forEach>
					 + '</select></td>'
					 +'<td data-head="Name" class="input-field"> <input id="contractDocumentNames'+rNo+'" name="contractDocumentNames" type="text" class="validate" placeholder="Name"> </td>'
					 +'<td data-head="Attachment" class="input-field">'
					 +'<span class="normal-btn">'
					 +'<input type="file" id="contractDocumentFiles'+rNo+'" name="contractDocumentFiles" style="display:none" onchange="getFileName('+rNo+')" />'
					 +'<label for="contractDocumentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
					 +'<span id="contractDocumentFileName'+rNo+'" class="filevalue"></span>'
					 +'</span>'
					 +'</td>'
					 +'<td></td>'
					 +'<td class="mobile_btn_close">'
					 +'<a href="javascript:void(0);" onclick="removeContractDocument('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
					 +'</td>'
			   		 +'</tr>';
		
			 $('#contractDocumentTableBody').append(html);
			 $("#documentRowNo").val(rNo);
			 $('.searchable').select2();
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
						//$(".hidden_check").val('No');
						
						$("#revision_status"+a).prop('checked',true);
						$("#revision_statuss"+a).val('Yes');
						if(i != a){
							if($("#revision_status"+i).prop('checked') == true 	&& $('#revised_amounts'+i).val() !="" && $('#revised_docs'+a).val()==""){
								$("#revision_statuss"+i).val('No');
								$("#revision_status"+i).prop('checked',false);							
												
							}
							if($("#revision_status"+i).prop('checked') == true 	&& $('#revised_docs'+i).val()!="" && $('#revised_amounts'+a).val()==""){
								$("#revision_statuss"+i).val('No');
								$("#revision_status"+i).prop('checked',false);							
												 
							}
							if($("#revision_status"+i).prop('checked') == false && $('#revised_docs'+i).val()!="" && $('#revised_amounts'+a).val()!=""){
								$("#revision_statuss"+i).val('No');
												 
							}
							if($("#revision_status"+i).prop('checked') == false){
								$("#revision_statuss"+i).val('No');
												 
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
						$("#revision_statuss"+a).val('Yes');
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