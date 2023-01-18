<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<title>
 		 <c:if test="${action eq 'edit'}">Update Expenditure - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Expenditure - Update Forms - PMIS</c:if>
 	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">          
    <!-- <link rel="stylesheet" href="/pmis/resources/css/budget.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style>
    	.fs11px{font-size: 11px !important;}
    	.pdtb8px{padding: 8px 0 !important;}
    
	    @media only screen and (min-width: 1200px){
			html {
			    font-size: 14px;
			}
	    }
        p a {
            color: blue
        }
        .input-field .searchable_label {
            font-size: 0.85rem;
        }        
		.error-msg label{color:red!important;}
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
		.input-field>.datepicker ~ label:not(.label-icon).active {
		    -webkit-transform: translateY(-11px) scale(0.8);
		    transform: translateY(-11px) scale(0.8);
		}
	
		/* new css code starts from here */ 
		
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
		
			.mt-brdr{
				margin-top: auto !important;
    			border: none !important;
			}
			.mt-brdr .btn{
   				width: 100% !important;
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
			    -webkit-transform: translateY(-18px) scale(0.95) !important;
			    transform: translateY(-18px) scale(0.95) !important;
			}			
			.input-field>textarea ~ label:not(.label-icon){
				margin-top:0 !important;
			}					
			.fs-sm-8rem{
				font-size:.8rem !important;
			}	
			.input-field>.datepicker~button{
				top:10px;
				right:6px;
			}
			
			.input-field .prefix{
				width: 2rem;
			}
			.input-field .prefix ~ input, 
			.input-field .prefix ~ textarea, 
			.input-field .prefix ~ label, 
			.input-field .prefix ~ .validate ~ label, 
			.input-field .prefix ~ .helper-text, 
			.input-field .prefix ~ .autocomplete-content{
				margin-left: 2rem;
			}
			.input-field .prefix ~ label, 
			.input-field .prefix ~ .validate ~ label {
				margin-left:2.5rem
			}
			.input-field .prefix ~ .validate ~ label.active {
				margin-left:2rem
			}
			.input-field.col .prefix ~ label, .input-field.col .prefix ~ .validate ~ label {
			    width: calc(100% - 3rem );
			}
			
		}		
		.fs-85{
			font-size:.85rem !important;
		}
		
		@media(max-width: 575px){
		.row .col{margin: 10px auto}
		.pmis-textarea{margin-bottom:0;}		
		}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                 <c:if test="${action eq 'edit'}">Update Expenditure</c:if>
								 <c:if test="${action eq 'add'}"> Add Expenditure</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">				                
				                	<form action="<%=request.getContextPath() %>/update-expenditure" id="expenditureForm" name="expenditureForm" method="post" >
	                          </c:if>
				              <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-expenditure" id="expenditureForm" name="expenditureForm" method="post" >
							  </c:if>
							<c:if test="${action eq 'add'}">	
                            <div class="row">
                               <div class="col s6 m4 l4 input-field offset-m2">
                                    <p class="searchable_label">Project <span class="required">*</span></p>
                                     <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                   		 onchange="getWorksList(this.value);">
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                     </select>
                                     <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                            
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <p class="searchable_label">Contract <span class="required">*</span></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option contractorName="${obj.contractor_name }" workId="${obj.work_id_fk }" value= "${ obj.contract_id}">${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Contractor <span class="required">*</span></p>
                                    <select id="contractor_name" name="contractor_name" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractorsList }">
                                      	   <option value= "${ obj.contractor_name}">${obj.contractor_name}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contractor_nameError" class="error-msg" ></span>
                                </div>
                            
                           </c:if>
                           <c:if test="${action eq 'edit'}">	
	                       	 	<div class="row">
		                       		  <div class="col s6 m4 l4 input-field offset-m2">
		                                    <input type="text" value="${expenditureDetails.project_id_fk} - ${expenditureDetails.project_name}" readonly id="project-text"/>
		                                     <input type="hidden" value="${expenditureDetails.project_id_fk}" name="project_id_fk" />
		                                    <label for="project-text">Project <span class="required">*</span></label>
									  </div> 
									  <div class="col s6 m4 l4 input-field"> 
		                                    <input type="text" value="${expenditureDetails.work_id_fk} - ${expenditureDetails.work_name}" readonly id="work-text"/>
		                                    <input type="hidden" value="${expenditureDetails.work_id_fk}" name="work_id_fk"  />
		                                    <label for="work-text">Work <span class="required">*</span></label>		                                    
		                              </div>
	                             
		                       		  <div class="col s6 m4 l4 input-field offset-m2">
	                              			<input type="text" value="${expenditureDetails.contract_id_fk} - ${expenditureDetails.contract_name}" readonly id="Contract-text"/>
	                              			<input type="hidden" value="${expenditureDetails.contract_id_fk}" name="contract_id_fk"  />
	                              			<label for="Contract-text">Contract <span class="required">*</span></label>	                              			
									  </div> 
									  <div class="col s6 m4 l4 input-field"> 
		                                    <input type="text" class="pdtb8px" value="${expenditureDetails.contractor_name}" readonly id="Contractor-text"/>
		                                    <label for="Contractor-text" class="fs-sm-9rem">Contractor name <span class="required">*</span></label>		                                    
		                              </div>
		                                                          
                            </c:if>
                            <%-- <div class="row">
                                <div class="col s12 m8 l8 input-field">
                                    <textarea id="ledger_account" name="ledger_account" class="materialize-textarea">${expenditureDetails.ledger_account }</textarea>
                                    <label for="ledger_account">Ledger Account</label>
                                    <span id="ledger_accountError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>

                           
                                <%-- <div class="col s12 m4 l4 input-field">
                                    <input id="contractor_name" name="contractor_name" type="text" class="validate" value="${expenditureDetails.contractor_name }">
                                    <label for="contractor_name">Contractor Name</label>
                                    <span id="contractor_nameError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col s12 m8 l8 input-field offset-m2">
                                    <textarea id="ledger_account" name="ledger_account" class="pmis-textarea">${expenditureDetails.ledger_account }</textarea>
                                    <label for="ledger_account">Ledger Account</label>
                                    <span id="ledger_accountError" class="error-msg" ></span>
                                </div>
                                         
                             	<div class="col s12 m4 l4 input-field offset-m2">
                                    <input id="date" type="text" name="date" class="validate datepicker" value="${expenditureDetails.date }">
                                    <label for="date">Date <span class="required">*</span></label>
                                    <span id="dateError" class="error-msg" ></span>
                                    <button type="button" id="date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Voucher Type <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="voucher_type" name="voucher_type">
                                         <option value="" >Select Voucher Type</option>
                                         <c:forEach var="obj" items="${voucherList }">
                                      	   <option value= "${ obj.financial_year}" <c:if test="${expenditureDetails.voucher_type eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
                                         </c:forEach>
                                     </select>
                                     <span id="voucher_typeError" class="error-msg" ></span>
                                </div>
                                <input type="hidden" name="expenditure_id" value="${expenditureDetails.expenditure_id }" />
                                <div class="col s12 m8 l4 input-field offset-m2">
                                    <input id="voucher_no" type="number" class="validate" name="voucher_no" value="${expenditureDetails.voucher_no }">
                                    <label for="voucher_no">Voucher No </label>
                                    <span id="voucher_noError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m8 l12 input-field offset-m2">
                                    <textarea id="narration" class="pmis-textarea" name="narration">${expenditureDetails.narration }</textarea>
                                    <label for="narration">Narration</label>
                                    <span id="narrationError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                             <div class="col s12 m4 l3 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="gross_work_done" step="0.01" type="number" class="validate" name="gross_work_done" value="${expenditureDetails.gross_work_done }"> 
                                    <label for="gross_work_done" class=""> Gross Work Done </label>
                                    <span id="gross_work_doneError" class="error-msg" ></span>
                                	<span id="gross_work_done_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="gross_work_done_units" name="gross_work_done_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.gross_work_done_units eq obj.value}">selected</c:if> >${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                              </div>
                               <%--  <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="gross_work_done_units" name="gross_work_done_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.gross_work_done_units eq obj.value}">selected</c:if> >${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="gross_work_done_unitsError" class="error-msg" ></span>
                                 </div> --%>
                                <div class="col s12 m4 l3 amount-dropdown input-field">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="net_paid" step="0.01" type="number" class="validate" name="net_paid" value="${expenditureDetails.net_paid }">
                                    <label for="net_paid"> Net Paid </label>
                                    <span id="net_paidError" class="error-msg" ></span>
                                	<span id="net_paid_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="net_paid_units" name="net_paid_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.net_paid_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                               <%--  <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="net_paid_units" name="net_paid_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.net_paid_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="net_paid_unitsError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col s12 m4 l3 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="sd_payable" min="0.01" step="0.01" type="number" class="validate" name="sd_payable" value="${expenditureDetails.sd_payable }">
                                    <label for="sd_payable">SD Payable</label>
                                    <span id="sd_payableError" class="error-msg" ></span>
                                	<span id="sd_payable_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="sd_payable_units" name="sd_payable_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.sd_payable_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>  
                              <%--   <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="sd_payable_units" name="sd_payable_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.sd_payable_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="sd_payable_unitsError" class="error-msg" ></span>
                                </div>   --%>                          
                           
                                <div class="col s12 m4 l3 amount-dropdown input-field">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="contractor_income_tax" min="0.01" step="0.01" type="number" class="validate" name="contractor_income_tax" value="${expenditureDetails.contractor_income_tax }">
                                    <label for="contractor_income_tax" class="fs11px">Contractor Income Tax</label>
                                    <span id="contractor_income_taxError" class="error-msg" ></span>
                                	<span id="contractor_income_tax_unitsError" class="error-msg right" ></span>
                                    <select class=" validate-dropdown" id="contractor_income_tax_units" name="contractor_income_tax_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.contractor_income_tax_units eq obj.value}">selected</c:if> >${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                                <%--  <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="contractor_income_tax_units" name="contractor_income_tax_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.contractor_income_tax_units eq obj.value}">selected</c:if> >${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="contractor_income_tax_unitsError" class="error-msg" ></span>
                                 </div>  --%>
                            
								<!-- <div class="col s12 m8 offset-m2">
									<div class="row"> -->
										<div class="col s12 m4 l3 amount-dropdown input-field offset-m2">
											<i class="material-icons amount-symbol center-align">₹</i> <input
												id="cgst_tds" min="0.01" step="0.01" type="number" class="validate" name="cgst_tds"
												value="${expenditureDetails.cgst_tds }"> <label
												for="cgst_tds">CGST TDS</label> <span id="cgst_tdsError"
												class="error-msg"></span>
			                                	<span id="cgst_tds_unitsError" class="error-msg right" ></span>
												<select class="validate-dropdown" id="cgst_tds_units" name="cgst_tds_units">
		                                		<!-- <option value="">Select</option> -->
		                                		<c:forEach var="obj" items="${unitsList }">
		                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.cgst_tds_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                  		    </c:forEach>
		                                	</select>
										</div>
									  <%--   <div class="col s4 m1 l1 input-field pt-10">
		                                	<p class="searchable_label">Unit</p>
		                                	<select class="units searchable validate-dropdown" id="cgst_tds_units" name="cgst_tds_units">
		                                		<option value="">Select</option>
		                                		<c:forEach var="obj" items="${unitsList }">
		                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.cgst_tds_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                  		    </c:forEach>
		                                	</select>
		                                	<span id="cgst_tds_unitsError" class="error-msg" ></span>
		                                </div> --%>
										<div class="col s12 m4 l3 amount-dropdown input-field">
											<i class="material-icons amount-symbol center-align">₹</i> <input
												id="sgst_tds" min="0.01" step="0.01" type="number" class="validate" name="sgst_tds"
												value="${expenditureDetails.sgst_tds }"> <label
												for="sgst_tds">SGST TDS</label> <span id="sgst_tdsError"
												class="error-msg"></span>
		                                		<span id="sgst_tds_unitsError" class="error-msg right" ></span>
												<select class="validate-dropdown" id="sgst_tds_units" name="sgst_tds_units">
		                                		<!-- <option value="">Select</option> -->
		                                		<c:forEach var="obj" items="${unitsList }">
		                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.sgst_tds_units eq obj.value}">selected</c:if> >${obj.unit }</option>
		                                  		    </c:forEach>
		                                	</select>
										</div>
									<%-- 	<div class="col s4 m1 l1 input-field pt-10">
		                                	<p class="searchable_label">Unit</p>
		                                	<select class="units searchable validate-dropdown" id="sgst_tds_units" name="sgst_tds_units">
		                                		<option value="">Select</option>
		                                		<c:forEach var="obj" items="${unitsList }">
		                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.sgst_tds_units eq obj.value}">selected</c:if> >${obj.unit }</option>
		                                  		    </c:forEach>
		                                	</select>
		                                	<span id="sgst_tds_unitsError" class="error-msg" ></span>
		                                </div> --%>
		                                <div class="col s12 m4 l3 amount-dropdown input-field offset-m2">
											<i class="material-icons amount-symbol center-align">₹</i> <input
												id="igst_tds" min="0.01" step="0.01" type="number" class="validate" name="igst_tds"
												value="${expenditureDetails.igst_tds }"> <label
												for="igst_tds">IGST TDS</label> <span id="igst_tdsError"
												class="error-msg"></span>
			                                	<span id="igst_tds_unitsError" class="error-msg right" ></span>
												<select class="validate-dropdown" id="igst_tds_units" name="igst_tds_units">
		                                		<!-- <option value="">Select</option> -->
		                                		<c:forEach var="obj" items="${unitsList }">
		                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.igst_tds_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                  		    </c:forEach>
		                                	</select>
										</div>
										<%-- <div class="col s4 m1 l1 input-field pt-10">
		                                	<p class="searchable_label">Unit</p>
		                                	<select class="units searchable validate-dropdown" id="igst_tds_units" name="igst_tds_units">
		                                		<option value="">Select</option>
		                                		<c:forEach var="obj" items="${unitsList }">
		                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.igst_tds_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                  		    </c:forEach>
		                                	</select>
		                                	<span id="igst_tds_unitsError" class="error-msg" ></span>
		                               </div> --%>
		                            <div class="col s12 m4 l3 amount-dropdown input-field ">
	                                    <i class="material-icons amount-symbol center-align">₹</i>
	                                    <input id="mob_advance" min="0.01" step="0.01" type="number" class="validate" name="mob_advance" value="${expenditureDetails.mob_advance }">
	                                    <label for="mob_advance" class="fs11px">Mobilization Advance</label>
	                                    <span id="mob_advanceError" class="error-msg" ></span>
	                                	<span id="mob_advance_unitsError" class="error-msg right" ></span>
	                                    <select class="validate-dropdown" id="mob_advance_units" name="mob_advance_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
	                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.mob_advance_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                  		    </c:forEach>
	                                	</select>
                                </div>
                                <%-- <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="mob_advance_units" name="mob_advance_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }" <c:if test="${expenditureDetails.mob_advance_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="mob_advance_unitsError" class="error-msg" ></span>
                                </div> --%>
		                                </div>
		                                <div class="row">
										
									<!-- </div>
								</div> -->
                           
                                
                                <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="interest_on_mob_adv" min="0.01" step="0.01" type="number" class="validate" name="interest_on_mob_adv" value="${expenditureDetails.interest_on_mob_adv }">
                                    <label for="interest_on_mob_adv" class="fs11px">Interest on Mobilization
                                        Advance</label>
                                    <span id="interest_on_mob_advError" class="error-msg" ></span>
                                	<span id="interest_on_mob_adv_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="interest_on_mob_adv_units" name="interest_on_mob_adv_units" onChange="alert(this.value);">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.interest_on_mob_adv_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                            <%--     <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="interest_on_mob_adv_units" name="interest_on_mob_adv_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.interest_on_mob_adv_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="interest_on_mob_adv_unitsError" class="error-msg" ></span>
                                </div> --%>
                                
                                
                				<%--cess on the building section--%>
                               <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="cess_on_building" min="0.01" step="0.01" type="number" class="validate" name="cess_on_building" value="${expenditureDetails.cess_on_building }">
                                    <label for="interest_on_mob_adv" class="fs11px">Cess on Building
                                        </label>
                                    <span id="interest_on_mob_advError" class="error-msg" ></span>
                                	<span id="interest_on_mob_adv_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="cess_on_building_units" name="cess_on_building_units" onChange="alert(this.value);">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.cess_on_building_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                                
                                 
                				<%--Establishment charges on Cess section--%>
                               <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="est_charges_on_cess" min="0.01" step="0.01" type="number" class="validate" name="est_charges_on_cess" value="${expenditureDetails.est_charges_on_cess }">
                                    <label for="interest_on_mob_adv" class="fs11px">Establishment charges on Cess
                                        </label>
                                    <span id="interest_on_mob_advError" class="error-msg" ></span>
                                	<span id="interest_on_mob_adv_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="est_charges_on_cess_units" name="est_charges_on_cess_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.est_charges_on_cess_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                                
                                <%--CGST (Output) section--%>
                               <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="cgst_output" min="0.01" step="0.01" type="number" class="validate" name="cgst_output" value="${expenditureDetails.cgst_output }">
                                    <label for="interest_on_mob_adv" class="fs11px">CGST (Output)
                                        </label>
                                    <span id="interest_on_mob_advError" class="error-msg" ></span>
                                	<span id="interest_on_mob_adv_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="cgst_output_units" name="cgst_output_units" onChange="alert(this.value);">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.cgst_output_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                                
                                
                                <%--SGST (Output) section--%>
                               <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="sgst_output" min="0.01" step="0.01" type="number" class="validate" name="sgst_output" value="${expenditureDetails.sgst_output }">
                                    <label for="interest_on_mob_adv" class="fs11px">SGST (Output)
                                        </label>
                                    <span id="interest_on_mob_advError" class="error-msg" ></span>
                                	<span id="interest_on_mob_adv_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="sgst_output_units" name="sgst_output_units" onChange="alert(this.value);">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.sgst_output_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                                
                                
                                
                                
                           
                                <div class="col s12 m4 l4 amount-dropdown input-field ">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="vat_wct" min="0.01" step="0.01" type="number" class="validate" name="vat_wct" value="${expenditureDetails.vat_wct }">
                                    <label for="vat_wct">VAT WCT</label>
                                    <span id="vat_wctError" class="error-msg" ></span>
                                	<span id="vat_wct_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="vat_wct_units" name="vat_wct_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.vat_wct_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                              <%--   <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="vat_wct_units" name="vat_wct_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.vat_wct_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="vat_wct_unitsError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="amount_withheld" min="0.01" step="0.01" type="number" class="validate" name="amount_withheld" value="${expenditureDetails.amount_withheld }">
                                    <label for="amount_withheld" class="fs-sm-8rem">Amount WithHeld</label>
                                    <span id="amount_withheldError" class="error-msg" ></span>
                                	<span id="amount_withheld_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="amount_withheld_units" name="amount_withheld_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.amount_withheld_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                </div>
                                <%-- <div class="col s4 m1 l1 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="amount_withheld_units" name="amount_withheld_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                 			   <option value="${obj.value }"  <c:if test="${expenditureDetails.amount_withheld_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                  		    </c:forEach>
                                	</select>
                                	<span id="amount_withheld_unitsError" class="error-msg" ></span>
                                </div> --%>
                            </div>

                            <div class="row">
                                <div class="col s12 m8 l12 input-field offset-m2">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${expenditureDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr offset-m2">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateExpenditure();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addExpenditure();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                             </div>
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <a href="<%=request.getContextPath()%>/expenditure" class="btn waves-effect waves-light bg-s">Cancel</a>
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



    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

    <script>
	  /*   $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); 
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
	    }); */

        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks').characterCounter();
           /*  $("#date").datepicker(); */

          /*   $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            }); */
            var projectId = "${expenditureDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            var work_id_fk = "${expenditureDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
        });
        
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();
            
            $("#contractor_name").attr("readonly", false); 
        	$("#contractor_name").val("");
        	$("#contractor_name").attr("readonly", true);

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForExpenditureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            
            $("#contractor_name").attr("readonly", false); 
        	$("#contractor_name").val("");
        	$("#contractor_name").attr("readonly", true);
        	
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForExpenditureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                $("#contract_id_fk").append('<option contractorName="'+val.contractor_name +'" workId="'+val.work_id_fk +'" value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
        
        function resetWorksAndProjectsDropdowns(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){        			
       			var contractor_name = $("#contract_id_fk").find('option:selected').attr("contractorName");
       			$("#contractor_name").attr("readonly", false); 
            	$("#contractor_name").val(contractor_name).focus();
            	$("#contractor_name").attr("readonly", true);
            	
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
            	projectId = workId.substring(0, 3);    
       			//workId = workId.substring(3, work_id.length);
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
       		if ($.trim(projectId) != "") {
       			$("#work_id_fk option:not(:first)").remove();
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForExpenditureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
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
                $('.searchable').select2();
            }
       		
        }
        
        function addExpenditure(){
        	var net_paid = $('#net_paid').val();
  			var gross_work_done = $('#gross_work_done').val();
  			var sd_payable = $('#sd_payable').val();
  			var contractor_income_tax = $('#contractor_income_tax').val();
  			var cgst_tds = $('#cgst_tds').val();
  			var sgst_tds = $('#sgst_tds').val();
  			var igst_tds = $('#igst_tds').val();
  			var vat_wct = $('#vat_wct').val();
  			var mob_advance = $('#mob_advance').val();
  			var interest_on_mob_adv = $('#interest_on_mob_adv').val();
  			var amount_withheld = $('#amount_withheld').val();
  			if(net_paid == ""){
  				$('#net_paid_units').val("");
  			}
  			if(gross_work_done == ""){
  				$('#gross_work_done_units').val("");
  			}
  			if(sd_payable == ""){
  				$('#sd_payable_units').val("");
  			}
  			if(contractor_income_tax == ""){
  				$('#contractor_income_tax_units').val("");
  			}
  			if(cgst_tds == ""){
  				$('#cgst_tds_units').val("");
  			}
  			if(sgst_tds == ""){
  				$('#sgst_tds_units').val("");
  			}
  			if(igst_tds == ""){
  				$('#igst_tds_units').val("");
  			}
  			if(vat_wct == ""){
  				$('#vat_wct_units').val("");
  			}
  			if(mob_advance == ""){
  				$('#mob_advance_units').val("");
  			}
  			if(interest_on_mob_adv == ""){
  				$('#interest_on_mob_adv_units').val("");
  			}
  			if(amount_withheld == ""){
  				$('#amount_withheld_units').val("");
  			}
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("expenditureForm").submit();			
  	 	 	}
        }
       function updateExpenditure(){
    	   	var net_paid = $('#net_paid').val();
  			var gross_work_done = $('#gross_work_done').val();
  			var sd_payable = $('#sd_payable').val();
  			var contractor_income_tax = $('#contractor_income_tax').val();
  			var cgst_tds = $('#cgst_tds').val();
  			var sgst_tds = $('#sgst_tds').val();
  			var igst_tds = $('#igst_tds').val();
  			var vat_wct = $('#vat_wct').val();
  			var mob_advance = $('#mob_advance').val();
  			var interest_on_mob_adv = $('#interest_on_mob_adv').val();
  			var amount_withheld = $('#amount_withheld').val();
  			if(net_paid == ""){
  				$('#net_paid_units').val("");
  			}
  			if(gross_work_done == ""){
  				$('#gross_work_done_units').val("");
  			}
  			if(sd_payable == ""){
  				$('#sd_payable_units').val("");
  			}
  			if(contractor_income_tax == ""){
  				$('#contractor_income_tax_units').val("");
  			}
  			if(cgst_tds == ""){
  				$('#cgst_tds_units').val("");
  			}
  			if(sgst_tds == ""){
  				$('#sgst_tds_units').val("");
  			}
  			if(igst_tds == ""){
  				$('#igst_tds_units').val("");
  			}
  			if(vat_wct == ""){
  				$('#vat_wct_units').val("");
  			}
  			if(mob_advance == ""){
  				$('#mob_advance_units').val("");
  			}
  			if(interest_on_mob_adv == ""){
  				$('#interest_on_mob_adv_units').val("");
  			}
  			if(amount_withheld == ""){
  				$('#amount_withheld_units').val("");
  			}
    	    if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("expenditureForm").submit();	
       		}
       }	   
       
       
       var validator =	$('#expenditureForm').validate({
			 errorClass: "my-error-class",
			   validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "project_id_fk": {
	  			 		  required: true
	  			 	  },"work_id_fk": {
	  			 		  required: true															
	  			 	  },"contract_id_fk": {
	  			 		  required: true
	  			 	  },"date": {
	  			 		  required: true
	  			 	  },"contractor_name": {
	  			 		  required: true
	  			 	  },"voucher_type": {
	  			 		  required: true
	  			 	  },"voucher_no": {
	  			 		  required: false
	  			 	  },"net_paid": {
	  			 		  required: false
	  			 	  },"ledger_account": {
	  			 		  required: false
	  			 	  },"narration": {
	  			 		  required: false
	  			 	  },"remarks": {
	  			 		  required: false
	  			 	  },"gross_work_done": {
	  			 		  required: false
	  			 	  },"sd_payable": {
	  			 		  required: false
	  			 	  },"contractor_income_tax": {
	  			 		  required: false
	  			 	  },"cgst_tds": {
	  			 		  required: false
	  			 	  },"sgst_tds": {
	  			 		  required: false
	  			 	  },"vat_wct": {
	  			 		  required: false
	  			 	  },"mob_advance": {
	  			 		  required: false
	  			 	  },"interest_on_mob_adv1": {
	  			 		  required: false
	  			 	  },"amount_withheld": {
	  			 		  required: false
	  			 	  },"igst_tds": {
	  			 		  required: false
	  			 	  },"net_paid_units":{
        		 		 required: function(element){
        		             return $("#net_paid").val()!="";
        		         }
        		 	  },"gross_work_done_units":{
        		 		 required: function(element){
        		             return $("#gross_work_done").val()!="";
        		         }
        		 	  },"sd_payable_units":{
        		 		 required: function(element){
        		             return $("#sd_payable").val()!="";
        		         }
        		 	  },"contractor_income_tax_units":{
        		 		 required: function(element){
        		             return $("#contractor_income_tax").val()!="";
        		         }
        		 	  },"cgst_tds_units":{
        		 		 required: function(element){
        		             return $("#cgst_tds").val()!="";
        		         }
        		 	  },"sgst_tds_units":{
        		 		 required: function(element){
        		             return $("#sgst_tds").val()!="";
        		         }
        		 	  },"igst_tds_units":{
        		 		 required: function(element){
        		             return $("#igst_tds").val()!="";
        		         }
        		 	  },"vat_wct_units":{
        		 		 required: function(element){
        		             return $("#vat_wct").val()!="";
        		         }
        		 	  },"mob_advance_units":{
        		 		 required: function(element){
        		             return $("#mob_advance").val()!="";
        		         }
        		 	  },"interest_on_mob_adv_units":{
        		 		 required: function(element){
        		             return $("#interest_on_mob_adv").val()!="";
        		         }
        		 	  },"amount_withheld_units":{
        		 		 required: function(element){
        		             return $("#amount_withheld").val()!="";
        		         }
        		 	  }			
	  		 	},
	  		    messages: {
	  		 		   "project_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"work_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"contract_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"date": {
	  			 		  required: 'Required'
	  			 	  },"contractor_name": {
	  			 		required: 'Required'
	  			 	  },"voucher_type": {
	  			 		required: 'Required'
	  			 	  },"voucher_no": {
	  			 		required: 'Required'
	  			 	  },"ledger_account": {
	  			 		required: 'Required'
	  			 	  },"narration": {
	  			 		required: 'Required'
	  			 	  },"remarks": {
	  			 		required: 'Required'
	  			 	  },"net_paid": {
	  			 		required: 'Required'
	  			 	  },"gross_work_done": {
	  			 		required: 'Required'
	  			 	  },"sd_payable": {
	  			 		required: 'Required'
	  			 	  },"contractor_income_tax": {
	  			 		required: 'Required'
	  			 	  },"cgst_tds": {
	  			 		required: 'Required'
	  			 	  },"igst_tds": {
	  			 		required: 'Required'
	  			 	  },"sgst_tds": {
	  			 		required: 'Required'
	  			 	  },"vat_wct": {
	  			 		required: 'Required'
	  			 	  },"mob_advance": {
	  			 		required: 'Required'
	  			 	  },"interest_on_mob_adv1": {
	  			 		required: 'Required'
	  			 	  },"amount_withheld": {
	  			 		required: 'Required'
	  			 	  },"net_paid_units": {
	  			 		required: 'Required'
	  			 	  },"gross_work_done_units": {
	  			 		required: 'Required'
	  			 	  },"sd_payable_units": {
	  			 		required: 'Required'
	  			 	  },"contractor_income_tax_units": {
	  			 		required: 'Required'
	  			 	  },"cgst_tds_units": {
	  			 		required: 'Required'
	  			 	  },"sgst_tds_units": {
	  			 		required: 'Required'
	  			 	  },"igst_tds_units": {
	  			 		required: 'Required'
	  			 	  },"vat_wct_units": {
	  			 		required: 'Required'
	  			 	  },"mob_advance_units": {
	  			 		required: 'Required'
	  			 	  },"interest_on_mob_adv_units": {
	  			 		required: 'Required'
	  			 	  },"amount_withheld_units": {
	  			 		required: 'Required'
	  			 	  }		
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "project_id_fk" ){
					     document.getElementById("project_id_fkError").innerHTML="";
				 	     error.appendTo('#project_id_fkError');
					 }else if(element.attr("id") == "work_id_fk" ){
					     document.getElementById("work_id_fkError").innerHTML="";
				 	     error.appendTo('#work_id_fkError');
					 }else if(element.attr("id") == "contract_id_fk" ){
					     document.getElementById("contract_id_fkError").innerHTML="";
				 	     error.appendTo('#contract_id_fkError');
					 }else if(element.attr("id") == "ledger_account" ){
					     document.getElementById("ledger_accountError").innerHTML="";
				 	     error.appendTo('#ledger_accountError');
					 }else if(element.attr("id") == "date" ){
					     document.getElementById("dateError").innerHTML="";
				 	     error.appendTo('#dateError');
					 }else if(element.attr("id") == "contractor_name" ){
					     document.getElementById("contractor_nameError").innerHTML="";
				 	     error.appendTo('#contractor_nameError');
					 }else if(element.attr("id") == "voucher_type" ){
					     document.getElementById("voucher_typeError").innerHTML="";
				 	     error.appendTo('#voucher_typeError');
					 }else if(element.attr("id") == "voucher_no" ){
					     document.getElementById("voucher_noError").innerHTML="";
				 	     error.appendTo('#voucher_noError');
					 }else if(element.attr("id") == "net_paid" ){
					     document.getElementById("net_paidError").innerHTML="";
				 	     error.appendTo('#net_paidError');
					 }else if(element.attr("id") == "gross_work_done" ){
					     document.getElementById("gross_work_doneError").innerHTML="";
				 	     error.appendTo('#gross_work_doneError');
					 }else if(element.attr("id") == "sd_payable" ){
					     document.getElementById("sd_payableError").innerHTML="";
				 	     error.appendTo('#sd_payableError');
					 }else if(element.attr("id") == "bank_account" ){
					     document.getElementById("bank_accountError").innerHTML="";
				 	     error.appendTo('#bank_accountError');
					 }else if(element.attr("id") == "contractor_income_tax" ){
					     document.getElementById("contractor_income_taxError").innerHTML="";
				 	     error.appendTo('#contractor_income_taxError');
					 }else if(element.attr("id") == "narration" ){
					     document.getElementById("narrationError").innerHTML="";
				 	     error.appendTo('#narrationError');
					 }else if(element.attr("id") == "remarks" ){
					     document.getElementById("remarksError").innerHTML="";
				 	     error.appendTo('#remarksError');
					 }else if(element.attr("id") == "cgst_tds" ){
					     document.getElementById("cgst_tdsError").innerHTML="";
				 	     error.appendTo('#cgst_tdsError');
					 }else if(element.attr("id") == "sgst_tds" ){
					     document.getElementById("sgst_tdsError").innerHTML="";
				 	     error.appendTo('#sgst_tdsError');
					 }else if(element.attr("id") == "vat_wct" ){
					     document.getElementById("vat_wctError").innerHTML="";
				 	     error.appendTo('#vat_wctError');
					 }else if(element.attr("id") == "igst_tds" ){
					     document.getElementById("igst_tdsError").innerHTML="";
				 	     error.appendTo('#igst_tdsError');
					 }else if(element.attr("id") == "mob_advance" ){
					     document.getElementById("mob_advanceError").innerHTML="";
				 	     error.appendTo('#mob_advanceError');
					 }else if(element.attr("id") == "interest_on_mob_adv" ){
					     document.getElementById("interest_on_mob_advError").innerHTML="";
				 	     error.appendTo('#interest_on_mob_advError');
					 }else if(element.attr("id") == "amount_withheld" ){
					     document.getElementById("amount_withheldError").innerHTML="";
				 	     error.appendTo('#amount_withheldError');
					 }else if(element.attr("id") == "net_paid_units" ){
						    document.getElementById("net_paid_unitsError").innerHTML="";
					     error.appendTo('#net_paid_unitsError');
					}else if(element.attr("id") == "sd_payable_units" ){
					    document.getElementById("sd_payable_unitsError").innerHTML="";
					     error.appendTo('#sd_payable_unitsError');
					}else if(element.attr("id") == "gross_work_done_units" ){
					    document.getElementById("gross_work_done_unitsError").innerHTML="";
					     error.appendTo('#gross_work_done_unitsError');
					}else if(element.attr("id") == "contractor_income_tax_units" ){
					    document.getElementById("contractor_income_tax_unitsError").innerHTML="";
					     error.appendTo('#contractor_income_tax_unitsError');
					}else if(element.attr("id") == "cgst_tds_units" ){
					    document.getElementById("cgst_tds_unitsError").innerHTML="";
					     error.appendTo('#cgst_tds_unitsError');
					}else if(element.attr("id") == "sgst_tds_units" ){
					    document.getElementById("sgst_tds_unitsError").innerHTML="";
					     error.appendTo('#sgst_tds_unitsError');
					}else if(element.attr("id") == "igst_tds_units" ){
					    document.getElementById("igst_tds_unitsError").innerHTML="";
					     error.appendTo('#igst_tds_unitsError');
					}else if(element.attr("id") == "mob_advance_units" ){
					    document.getElementById("mob_advance_unitsError").innerHTML="";
					     error.appendTo('#mob_advance_unitsError');
					}else if(element.attr("id") == "interest_on_mob_adv_units" ){
					    document.getElementById("interest_on_mob_adv_unitsError").innerHTML="";
					     error.appendTo('#interest_on_mob_adv_unitsError');
					}else if(element.attr("id") == "vat_wct_units" ){
					    document.getElementById("vat_wct_unitsError").innerHTML="";
					     error.appendTo('#vat_wct_unitsError');
					}else if(element.attr("id") == "amount_withheld_units" ){
					    document.getElementById("amount_withheld_unitsError").innerHTML="";
					     error.appendTo('#amount_withheld_unitsError');
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
    </script>

</body>

</html>