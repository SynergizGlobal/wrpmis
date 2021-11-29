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
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contract.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
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
        .w7em{width: 7em;}
        .bd-none{border:none !important;}
        .mt20px{margin-top: 15px !important;}
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.8em;
   					 margin-left: 26%;}
   		.bd-none{border: none;}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:36%;}
    	}
    	@media(max-width: 800px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
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

		h5{
			margin-top:0;
		}
		@media(max-width: 575px){
		.row .col{margin: 15px auto;}
		}	
		
		/* searchable dropdown code start here  */
		.input-field .select2-container--default{
			margin-bottom:8px;
		}
		@media only screen and (min-width:769px){
			span.select2 .selection .select2-selection--single{
				height:3.05rem;
				padding-top:.75rem;
			}
			span.select2-container--default .select2-selection--single .select2-selection__arrow{
				top:.75rem;
			}
			label.selected {
		      	-webkit-transform: translateY(-14px) scale(0.8);
			    transform: translateY(-14px) scale(0.8);
			    -webkit-transform-origin: 0 0;
			    transform-origin: 0 0;
			}
		}		
		@media only screen and (max-width: 768px){
			label.selected {
		      	-webkit-transform: translateY(-18px) scale(0.95);
			    transform: translateY(-18px) scale(0.95);
			    -webkit-transform-origin: 0 0;
			    transform-origin: 0 0;
			}
		}
		/* searchable dropdown code ends here  */
		
		.pad-top{
			padding-top:1rem;
		}
		@media only screen and (max-width: 768px){
			.pad-top{
				padding-top:0;
			}
		}
		@media only screen and (max-width: 575px){
			.pad-top{
				padding-bottom:1rem;
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
	                                <div class="col s6 m4 l4 input-field">
	                                <!-- <p class="searchable_label">Project <span class="required">*</span></p> -->
	                                <label for="project_id_fk" class="selected">Project <span class="required">*</span></label>
	                                <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${obj.project_id}" >${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                     </select>                                    
                                     <span id="project_id_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 l4 input-field">
		                               <!--  <p class="searchable_label">Work <span class="required">*</span></p> -->
		                                <label for="work_id_fk" class="selected">Work <span class="required">*</span></label>
		                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" 
		                                    	onchange="resetProjectsDropdowns(this.value);">
		                                        <option value="">Select</option>
		                                        <c:forEach var="obj" items="${worksList }">
		                                      	   <option workShortName="${obj.work_short_name }" value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
		                                         </c:forEach>
		                                    </select>
		                                     <span id="work_id_fkError" class="error-msg" ></span>
		                                
		                                <input type="hidden" id="work_short_name" name="work_short_name"/>
		                            </div>
								   	<input type="hidden" id="contract_id_code" name="contract_id_code"/>  
	                                 
	                                <div class="col s12 m4 l4 input-field">
		                                <p class="priokind pad-top" style="text-align: center;"> <span style="margin-right:.5rem;font-weight:600;">Contract Awarded? <span class="required">*</span></span>
                                           	<span class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label style="padding-right: 10px;">
                                                    <input class="with-gap" name="contract_status" type="radio" value="Yes" onclick="getStatusLIst();hideContractDetails();">
                                                    <span style="padding-left: 23px;">Yes</span>
                                                </label>
                                                <label>
                                                    <input class="with-gap" name="contract_status" type="radio" value="No" onclick="getStatusLIst();hideContractDetails();">
                                                    <span style="padding-left: 23px;">No</span>
                                                </label>
                                            </span>
                                           </p>	
                                           <p id="contract_statusError" class="error-msg" style="margin-top: 10px;padding-left: 30px;"></p>
                                     </div>
	                             </div>
	                                 
	                             <div class="row">	                                    
                                    <c:choose>
								         <c:when test = "${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">
								         <div class="col s6 m4 l4 input-field ">
								            <!-- <p class="searchable_label">HOD<span class="required">*</span></p> -->
								            <label class="selected" for="hod_user_id_fk">HOD<span class="required">*</span></label>
	                                            <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown searchable"> 
	                                     		  <option value="">Select</option> 
	                                     		  	<c:forEach var="obj" items="${hodList }"> 
			                                    	  <option  deptCode="${obj.contract_id_code }" value="${obj.user_id }"> ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                        </c:forEach> 
	                                     	     </select> 
	                                     	<span id="hod_user_id_fkError" class="error-msg" ></span>
	                                     	</div>
	                                     	 <div class="col s6 m4 l4 input-field">
	                                        	<!-- <p class="searchable_label">Dy HOD<span class="required">*</span></p> -->
	                                        	<label class="selected" for="dy_hod_user_id_fk">Dy HOD<span class="required">*</span></label>
	                                            <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown searchable" >
	                                                <option value="">Select</option>
	                                                 <c:forEach var="obj" items="${dyHodList }"> 
			                                    	  <option value="${obj.user_id }" > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                         </c:forEach>   
	                                            </select>
												<!-- <input name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" type="text" class="validate" style="margin-top:10px">
	                               		     	<label for="dy_hod_user_id_fk">Dy HOD</label> -->
	                                            <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                        
	                                 
								         </c:when>
								         <c:otherwise>
								           	<div class="col s6 m4 l4 input-field ">
	  										 	<!-- <p class="searchable_label">HOD<span class="required">*</span></p> -->
	  										 	<label class="selected" for="hod_user_id_fk">HOD<span class="required">*</span></label>
	                                            <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown searchable" onchange="getDyHodList();"  <c:if test="${sessionScope.USER_TYPE eq 'HOD'  || sessionScope.USER_TYPE eq 'DyHOD'}"> disabled  </c:if>> 
	                                     		  <option value="">Select</option> 
	                                              
	                                            </select> 
												<input type="hidden"  name="department_fk" id="department_fk"/>
												<!-- <label for="hod_user_id_fk">HOD</label>  -->
	                                            <span id="hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                        <c:if test="${sessionScope.USER_TYPE eq 'HOD'  || sessionScope.USER_TYPE eq 'DyHOD'}">  <input type="hidden" id="hodVal" name="hod_user_id_fk" />  </c:if>
	                                       
	                                        <div class="col s6 m4 l4 input-field">
	                                        	<!-- <p class="searchable_label">Dy HOD<span class="required">*</span></p> -->
	                                        	<label class="selected" for="dy_hod_user_id_fk">Dy HOD<span class="required">*</span></label>
	                                            <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown searchable" onchange="getHodList();" <c:if test="${sessionScope.USER_TYPE eq 'DyHOD'}"> disabled  </c:if>>
	                                                <option value="">Select</option>
	                                                 
	                                            </select>
												<!-- <input name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" type="text" class="validate" style="margin-top:10px">
	                               		     	<label for="dy_hod_user_id_fk">Dy HOD</label> -->
	                                            <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
	                                         <c:if test="${ sessionScope.USER_TYPE eq 'DyHOD'}">  <input type="hidden" id="dyHodVal" name="dy_hod_user_id_fk" />  </c:if>
	                                         <div class="col s6 m4 l4 input-field">
			                                  <!--  <p class="searchable_label">Status of Work <span class="required">*</span></p> -->
			                                   	<label class="selected" for="contract_status_fk">Status of Work <span class="required">*</span></label>	                                                  
			                                    <select name = "contract_status_fk" id="contract_status_fk" class="validate-dropdown searchable">
			                                        <option value="" selected>Select</option>
			                                           <c:forEach var="obj" items="${contract_Statustype }">
			                                           		<c:if test="${obj.contract_status_fk ne 'Completed'}">
					                                    		<option status="${obj.contract_status }" value="${obj.contract_status_fk }" <c:if test="${obj.contract_status_fk eq 'Not Started'}">selected</c:if>>${obj.contract_status_fk }</option>
					                                    	</c:if>
					                                    </c:forEach>
			                                    </select>
			                                     <span id="contract_status_fkError" class="error-msg" ></span>
			                                </div>
								         </c:otherwise>
								      </c:choose>
                                </div>
                                
                                <div class="row">                            
	                                <div class="col s12 m8 l12 input-field mt20px offset-m2">
	                                    <input name="contract_short_name" id="contract_short_name" type="text" class="validate" >
	                                    <label for="contract_short_name">Contract Short Name <span class="required">*</span></label>
	                                      <span id="contract_short_nameError" class="error-msg" ></span>
	                                </div>
	                            </div>
	              
	                            <div class="row"> 
	                            	<div class="col m8 l12 offset-m2 s12">
										<div class="row fixed-width">
									        <h5 class="center-align">Executives</h5>
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
									            <table  class="mdl-data-table table-add bd-none" style="margin-bottom: 20px">
			                                        <tbody>                                          
			                                            <tr class="bd-none">
			                                   				<td colspan="3" class="bd-none"><a   class="btn waves-effect waves-light bg-m t-c add-align"  onclick="addDepartmentRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table>
			                                    <input type="hidden" id="deptRowNo"  name="deptRowNo" value="0" />
									        </div>
									    </div>
									</div>
								</div>
	                            <div class="row">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea name="contract_name" id="contract_name" type="text" class="validate pmis-textarea" ></textarea>
	                                    <label for="contract_name">Contract Name <span class="required">*</span></label>
	                                    <span id="contract_nameError" class="error-msg" ></span>
	                                </div>	                              
	                                <!-- <div class="col s12 m8 l12 input-field offset-m2">
	                                    <input name="contract_short_name" id="contract_short_name" type="text" class="validate" >
	                                    <label for="contract_short_name">Contract Short Name <span class="required">*</span></label>
	                                      <span id="contract_short_nameError" class="error-msg" ></span>
	                                </div> -->
	                            </div>
	
	                            <div class="row" id="contract_type_fk_div">	
	                                <div class="col s6 m4 l12 input-field offset-m2">
	                                 <!-- <p class="searchable_label">Contract Type <span class="required">*</span></p> -->
	                                 <label class="selected" for="contract_type_fk">Contract Type <span class="required">*</span></label>
	                                    <select name="contract_type_fk" id="contract_type_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                       	   <c:forEach var="obj" items="${contract_type }">
			                                     <option value="${obj.contract_type_fk }" >${obj.contract_type_fk }</option>
			                                   </c:forEach>
	                                    </select>                                   
	                                     <span id="contract_type_fkError" class="error-msg" ></span>	                                    
	                                </div>	                         
	                            </div>
	                            <div class="row">
	                                <div class="col s12 m8 l12 input-field offset-m2" id="scope_of_contract_div">
	                                    <textarea id="scope_of_contract" name="scope_of_contract" class="pmis-textarea validate" data-length="1000">${contractDeatils.scope_of_contract }</textarea>
	                                    <label for="scope_of_contract">Scope of Contract</label>
	                                 <span id="scope_of_contractError" class="error-msg" ></span>                                    
	                                </div>
	                           
	                                <div class="col s6 m4 l6 input-field offset-m2" id="loa_letter_number_div">
	                                    <input id="loa_letter_number" name="loa_letter_number" type="text" class="validate">
	                                    <label for="loa_letter_number">LOA Letter No</label>
	                                    <span id="loa_letter_numberError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 l6 input-field" id="loa_date_div">
	                                    <input id="loa_date" name="loa_date" type="text" class="validate datepicker">
	                                    <label for="loa_date">LOA Date</label>
	                                     <span id="loa_dateError" class="error-msg" ></span>
	                                    <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col s12 m4 l6 input-field offset-m2 amount-dropdown" id="estimated_cost_div">
	                                    <i class="material-icons amount-symbol cost">₹</i>
	                                    <input id="estimated_cost" name="estimated_cost" type="number" min="0.01" step="0.01" class="validate">
	                                    <label for="estimated_cost">Detailed Estimated cost</label>
	                                    <span id="estimated_costError" class="error-msg" ></span>
	                                	<span id="estimated_cost_unitsError" class="error-msg right" ></span>
	                                    <select class=" validate-dropdown" id="estimated_cost_units" name="estimated_cost_units">
	                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }">${obj.unit }</option>
                                   		    </c:forEach>
	                                	</select>
	                                </div>
	                                 <div class="col s6 m4 l6 input-field" id="contract_status_fk_div">
		                                   <!-- <p class="searchable_label">Status of Work <span class="required">*</span></p> -->			                                   
		                                   <label class="selected" for="contract_status_fk">Status of Work <span class="required">*</span></label>			                                   
		                                    <select name = "contract_status_fk" id="contract_status_fk" class="validate-dropdown searchable" data-placeholder="Select">
		                                        <option value="" selected>Select</option>
		                                            <c:forEach var="obj" items="${contract_Statustype }">
		                                           		<c:if test="${obj.contract_status_fk ne 'Completed'}">
				                                    		<option status="${obj.contract_status }" value="${obj.contract_status_fk }" <c:if test="${obj.contract_status_fk eq 'Not Started'}">selected</c:if>>${obj.contract_status_fk }</option>
				                                    	</c:if>
				                                    </c:forEach>
		                                    </select>
		                                     <span id="contract_status_fkError" class="error-msg" ></span>
		                                </div>                  
	                        
 							<div class="table-div">
	
	                    <div class="">
	                    	<div class=" col m10 s12 l12" style="margin-bottom:30px; padding:0;">
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
	                                        
	                                        <table class="mdl-data-table table-add bd-none">
		                                        <tbody id="revTableBody" class="bd-none">                                          
		                                            <tr class="bd-none">
														<td colspan="3" class="bd-none">	<a type="button"  class="btn waves-effect waves-light bg-m t-c add-align"  onclick="addContractDocumentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <input type="hidden" id="documentRowNo"  name="documentRowNo" value="0" />
	                                    </div>
	                                </div>
	                            </div>        
	                    </div>
						</div>

                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr center-align offset-m2">
                                    <div class="m-1">
                                        <button type="button" onclick="addContract();" class="btn waves-effect waves-light bg-m" style="min-width:90px;">Add</button>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr center-align">
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
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>


    <script>
	   /*  let date_pickers = document.querySelectorAll('.datepicker');
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
		var loggedin_user_id = '${sessionScope.USER_ID}';
		var user_role = '${sessionScope.USER_ROLE_NAME}';   
        $(document).ready(function () {
        	 $('select:not(.searchable):not(.units)').formSelect();
             $('.searchable').select2(); 
             $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
             $('#remarks').characterCounter();
			 if(user_role != 'IT Admin'){
	             getHodList();
			 }
			 getDyHodList();
			 getStatusLIst();	
			 
			 hideContractDetails();
			 
        });
        
        function getStatusLIst(){
		  	$(".page-loader").show();
		  	//var contract_status = $('#contract_status').val();
		  	var contract_status = $('input[name="contract_status"]:checked').val();
            $("#contract_status_fk option:not(:first)").remove();
            if ($.trim(contract_status) != "") {
                var myParams = { contract_status: contract_status };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractStatusLIstFormContractFom",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	if(val.contract_status != 'Completed'){
                                	$("#contract_status_fk").append('<option status="'+val.contract_status +'" value="' + val.contract_status_fk + '">' + $.trim(val.contract_status_fk) +  '</option>');
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
        
        function hideContractDetails(){
        	//var contract_status = $('#contract_status').val();
        	var contract_status = $('input[name="contract_status"]:checked').val();
        	if($.trim(contract_status) == 'No' || $.trim(contract_status) == ''){
        		$('#contract_type_fk').val('');
        		$('#scope_of_contract').val('');
            	$('#loa_letter_number').val('').focus();
            	$('#loa_date').val('').focus();
            	$('#ca_no').val('').focus();
            	$('#ca_date').val('').focus();
            	$('#estimated_cost').val('').focus();
            	$('#contract_status_fk').val('');
            	$('#estimated_cost_units').val('1').focus();
            	
            	$('.searchable').select2();
            	
            	$("#contract_type_fk_div").hide();
				$('#scope_of_contract_div').hide();
	        	$('#loa_letter_number_div').hide();
	        	$('#loa_date_div').hide();
	        	$('#ca_no_div').hide();
	        	$('#ca_date_div').hide();
	        	$('#estimated_cost_div').hide();
	        	$('#estimated_cost_units_div').hide();
	        	$('#contract_status_fk_div').hide();
	        	
	        	$('#contract_type_fk').rules('remove',  'required');
	        	$('#contract_status_fk').rules('remove',  'required');
        	}else{
        		$("#contract_type_fk_div").show();
				$('#scope_of_contract_div').show();
	        	$('#loa_letter_number_div').show();
	        	$('#loa_date_div').show();
	        	$('#ca_no_div').show();
	        	$('#ca_date_div').show();
	        	$('#estimated_cost_div').show();
	        	$('#estimated_cost_units_div').show();
	        	$('#contract_status_fk_div').show();
	        	
	        	$('#contract_type_fk').rules('add',  { required: true });
	        	$('#contract_status_fk').rules('add',  { required: true });
        	}
        }
     
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
                              	   		$("#hod_user_id_fk").append('<option deptCode="' + val.contract_id_code +'" value="' + val.hod_user_id_fk + '" selected>' + $.trim(val.designation) + userName + '</option>');
                              	   	    $("#hodVal").val(val.hod_user_id_fk);
                              	   }else{
                              	   		$("#hod_user_id_fk").append('<option deptCode="' + val.contract_id_code +'" value="' + val.hod_user_id_fk + '">' + $.trim(val.designation) + userName + '</option>');
                              	   }
                            });
                            if($.trim(reporting_to_id_srfk) != ''){
                            	$("#hod_user_id_fk").val(reporting_to_id_srfk);
                            	$("#hodVal").val(reporting_to_id_srfk);
                            	deptCode = $("#hod_user_id_fk").find('option:selected').attr("deptCode");
                            	$("#contract_id_code").val(deptCode);
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
                                $("#dyHodVal").val(val.dy_hod_user_id_fk);
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
	  			
	  			var deptCode = $("#hod_user_id_fk").find('option:selected').attr("deptCode");
	  			if(deptCode != ""){
		  			$("#contract_id_code").val(deptCode);
	  			}
	  			var estimated_cost = $('#estimated_cost').val();
	  			var awarded_cost = $('#awarded_cost').val();
	  			if(estimated_cost == ""){
	  				$('#estimated_cost_units').val("");
	  			}
	  			$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });		
	  			$('form input[name=responsible_people_id_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			
	  			$('form input[name=contractDocumentNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractDocumentFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			
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
		   		 	  },"contract_name": {
		   		 		required: true
		   		 	  },"contract_short_name":{
        		 		 required: true,
        		 		 maxlength: 100
        		 	  },"contract_type_fk": {
		   		 		required: true
		   		 	  },"scope_of_contract": {
		   	 		    required: false,
		   	 	   	  },"hod_user_id_fk": {
		   		 		required: true
		   		 	  },"dy_hod_user_id_fk": {
		   	 		    required: true
		   	 	   	  },"estimated_cost": {
			   		 	required: function(element){
	   		             	return $("#estimated_cost").val()!="";
	   		         	}
		   		 	  },"estimated_cost_units":{
	        		 		 required: function(element){
	        		             return $("#estimated_cost").val()!="";
	        		         }
	        		  },"loa_letter_number": {
		   		 		required: false
		   		 	  },"loa_date":{
		   		 		 required: false
		   		 	  },"contract_status_fk":{
		   	 	  		required: true
		   		 	  },"contract_status":{
		   	 	  		required: true
		   		 	  }		
		   	 	},
		   	   messages: {
		   			 "project_id_fk": {
	   		 			required: 'Required'
	   		 	  	 },"work_id_fk": {
		   	 			required: 'Required'
		   	 	  	 },"contract_name": {
		   	 			required: 'Required'
		   	 	  	 },"contract_name": {
		   	 			required: 'Required'
		   	 	  	 },"contract_short_name": {
		   	 			required: 'Required',
		   	 			maxlength : 'Contract short name must be less than or equal to 100 characters'
		   	 	  	 },"contract_type_fk": {
		   	 	  		required: 'Required'
		   		 	 },"scope_of_contract": {
		   	 	  		required: 'Required'
		   	 	   	 },"hod_user_id_fk": {
		   	 			required: 'Required'
		   	 	  	 },"dy_hod_user_id_fk": {
		   	 			required: 'Required'
		   	 	  	 },"estimated_cost": {
				 		required: 'Required'
				 	  },"estimated_cost_units":{
        	 	  		required: 'Required'
        		 	  },"loa_letter_number": {
				 		required: 'Required'
				 	  },"loa_date":{
				 		 required: 'Required'
				 	  },"contract_status_fk":{
		   	 	  		required: 'Required'
		   		 	  },"contract_status":{
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
			   	 	    }else if (element.attr("id") == "contract_name" ){
			   	 		     document.getElementById("contract_nameError").innerHTML="";
			   	 			 error.appendTo('#contract_nameError');
			   	 	    }else if (element.attr("id") == "contract_short_name" ){
			   	 		     document.getElementById("contract_short_nameError").innerHTML="";
			   	 			 error.appendTo('#contract_short_nameError');
			   	 	    }else if (element.attr("id") == "contract_type_fk" ){
			   	 		     document.getElementById("contract_type_fkError").innerHTML="";
			   	 			 error.appendTo('#contract_type_fkError');
			   	 	    }else if (element.attr("id") == "scope_of_contract" ){
			   	 		     document.getElementById("scope_of_contractError").innerHTML="";
			   	 			 error.appendTo('#scope_of_contractError');
			   	 	    }else if (element.attr("id") == "hod_user_id_fk" ){
			   	 		     document.getElementById("hod_user_id_fkError").innerHTML="";
			   	 			 error.appendTo('#hod_user_id_fkError');
			   	 	    }else if (element.attr("id") == "dy_hod_user_id_fk" ){
			   	 		     document.getElementById("dy_hod_user_id_fkError").innerHTML="";
			   	 			 error.appendTo('#dy_hod_user_id_fkError');
			   	 	    }else if (element.attr("id") == "estimated_cost" ){
				 		     document.getElementById("estimated_costError").innerHTML="";
				 			 error.appendTo('#estimated_costError');
				 	    }else if (element.attr("id") == "estimated_cost_units" ){
		       	 		     document.getElementById("estimated_cost_unitsError").innerHTML="";
		    	 			 error.appendTo('#estimated_cost_unitsError');
	        	 	    }else if (element.attr("id") == "loa_letter_number" ){
				 		     document.getElementById("loa_letter_numberError").innerHTML="";
				 			 error.appendTo('#loa_letter_numberError');
				 	    }else if (element.attr("id") == "loa_date" ){
				 		     document.getElementById("loa_dateError").innerHTML="";
				 			 error.appendTo('#loa_dateError');
				 	    }else if (element.attr("id") == "contract_status_fk" ){
				 		     document.getElementById("contract_status_fkError").innerHTML="";
				 			 error.appendTo('#contract_status_fkError');
			   	 	    }else if (element.attr("name") == "contract_status" ){
			  	 		     document.getElementById("contract_statusError").innerHTML="";
				 			 error.appendTo('#contract_statusError');
				 		}
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
		

    </script>

</body>

</html>