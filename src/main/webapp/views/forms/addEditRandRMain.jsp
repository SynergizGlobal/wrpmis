<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R & R - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
	
    <style>
       
        .mdl-data-table{
        	border:1px solid #ccc;	
        }
  		.error-msg label{color:red!important;}   
		
		span.required {
		    font-size: inherit;
		}
       
		.input-field .searchable_label.mb-8 {
		    margin-bottom: 5px !important;
    		margin-top: -11px !important;
    	}
        .fixed-width {
            width: 100%;
			margin: auto !important;            
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td{
        	position:relative
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        .fs-9{
        	font-size:.9rem;
        }
        p.prio{
       	    margin-top: -10px !important;       	    
        }
        p.priokind{
        	font-weight:600;
        	margin-top: 10px !important;     
        }
        p.priokind >i{
        	margin-left:10px;
        	vertical-align:inherit;
        }
        #revTable .select2-container{
	        max-width:150px;
	        width: 150px !important;
	        text-align:left;
	        margin-top:10px;
        }        
         
        .my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		td.center-align{
			text-align:center !important;
		}
		
		/* new css code starts from here */ 
		.col.issue-mar,
		.issue-mar{
			margin-top:40px;
		 }
		 .mobile-prio,
		 .col.mobile-prio{
		 	margin-top:30px;
		 }
		.mb-md-20{
			margin-bottom:20px !important;
		}
		.input-field p.mt-md-0{
			margin-top:-4px !important;
		}
		.lh{
			line-height:inherit;
		}
		.input-field.min4{
			min-height:4rem ;
		}
		.fs11px{font-size: 11px !important;}
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			#revisionsTableBody tr .input-field .datepicker~button ,
			 #statusTable tr .input-field .datepicker~button{
			    position: relative;
			    top: 0;
			    right: 26px;
			}			
			#revTable .select2-container{
				width:-webkit-fill-available !important;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
			    vertical-align: middle;
			}
			.mobile_responsible_table tbody {
			    overflow-x:hidden;
			}
			.mt-brdr{
				margin-top: auto !important;
    			border: none !important;
			}
			.mt-brdr .btn{
   				width: 100% !important;
			}
			.mobile-prio{
				margin-top:14px;
				margin-bottom:14px;
			}
			.mobile-prio >.prio{
				text-align:center;
				margin-bottom:5px;
			}
			.col.issue-mar,
			.issue-mar{
				margin-top:25px;
				text-align:center;
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
			    -webkit-transform: translateY(-18px) scale(0.95);
			    transform: translateY(-18px) scale(0.95);
			}
			.input-field>textarea ~ label:not(.label-icon).active{		
				-webkit-transform: translateY(-26px) scale(0.95);
			    transform: translateY(-26px) scale(0.95);
			}
			.mb-md-20	{
				margin-bottom:0 !important;
			}				
			.fs-sm-8rem{
				font-size:.8rem !important;
			}	
			.input-field>.datepicker~button{
				top:10px;
				right:6px;
			}
			.lh{
				line-height:1.1rem;
			}	
			.mt-md-0{
				margin-top: -14px !important;
			} 
		}
		@media only screen and (max-width: 767px){
			.input-field.min4{
				min-height:1px ;
			}
		}	
			.input-field .select2-container--default {
			    margin-bottom: 0.5rem;
			}
			@media only screen and (min-width: 770px){
				.mdl-data-table	tr>td.input-field .select2-container--default {
				    margin-bottom: 0;
	    			margin-top: 8px;
				}
			}
			
		@media only screen and (max-width: 769px) and (min-width: 500px){
			  #revTable .select2-container{
		          max-width: inherit !important;
    			  width: 95% !important;
		      }
		}
		@media(max-width: 575px){
			.row .col{margin: 10px auto}
			p.priokind{margin-top: 0 !important;}
			.t-48{
			top:37px;}
		}
		.mobile_btn_close > .waves-effect.btn.red,
		.waves-effect.bg-m.t-c{
			z-index:0;
		}
		
		.filevalue {
            display: block;
            margin-top: 10px;
			font-size: .9rem;
        }
        .max-200{
        	max-width: 200px;
        	width:200px !important;
        }
        .right.mob-center{
			position:absolute;
			right:3rem;
		}
		@media only screen and (max-width: 769px){
			.right.mob-center{
		    	position:relative;
				right:inherit;
				float: none !important;
				display:block;
				margin-left:auto;
				margin-right:auto;
				margin-top:5px;
			}
			.filevalue {
			    width: 200%;
			    white-space: break-spaces;
			}
			td[data-head="Status"]>p{
				display:inline-block;
			}
			.input-field .searchable_label.mb-8 {
				margin-top: -20px !important;
	    		margin-bottom: -4px !important;
    		}
		}
		.pos-rel{
			position:relative;
		}
		h6{
			margin:1rem 0;
			font-weight:600;
		}
		thead th{
			text-transform: capitalize;
		}
		input[type=number]::-webkit-inner-spin-button, 
		input[type=number]::-webkit-outer-spin-button { 
		  -webkit-appearance: none; 
		  margin: 0; 
		}
		#percapita_per_month{
			margin-top:.5rem;
		}
		@media only screen and (min-width: 769px){
			.max-80{
				max-width:80px;
				width:80px;
			}
		}
    </style>
</head>
<body>
 <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
                  
       <div class="row">
        <div class="col s12 m12" style="margin-bottom:4rem;">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                               	    <c:if test="${action eq 'edit'}">Update R & R (${rrDetails.rr_id })</c:if>
									<c:if test="${action eq 'add'}"> Add R & R</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-rr" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-rr" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
						  
						  
						  
						    <div class="container container-no-margin">
                            <div class="section ">
                            <div class="row">
						    <c:if test="${action eq 'add'}">	
                                <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label mb-8"> Project <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  onchange="getWorksList(this.value);" >
                                         <option value="" >Select</option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id_fk}" <c:if test="${rrDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> 
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label mb-8"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id" name="work_id" onchange="resetProjectsDropdowns(this.value);">
                                        <option value="" >Select</option>
                                         <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> 
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             <div class="col s12 m4 l4 input-field">
                             		<input type="text" id="rr_id" name="rr_id" class="validate" onkeyup="checkRRId()"/>
								    <label for="rr_id">R & R Id <span class="required">*</span></label>
								    <span id="rr_idError" class="error-msg" style="font-size:13px"; ></span>
                             </div>
                            </c:if>
 							<c:if test="${action eq 'edit'}">		
 							 <input type="hidden" id="rr_id" name="rr_id" value="${rrDetails.rr_id }">                             
	                                <div class="col s6 m4 l4 input-field ">
								    	<label for="project_id_fk">Project </label>
								    	<input type="hidden" name="project_id_fk" id="project_id_fk" value="${rrDetails.project_id_fk }"  />
								    	<input type="text"  value="${rrDetails.project_id_fk } -  ${rrDetails.project_name }" readonly />
								    </div> 
	                                <div class="col s12 m8 l8 input-field"> 
	                                	<label for="work_id">Work </label>
	                                	<input type="hidden" name="work_id" id="work_id" value="${rrDetails.work_id }"  />
	                                	<input type="text" value="${rrDetails.work_id } - ${rrDetails.work_short_name }" readonly />
	                                </div>
	                           
                              </c:if>
							</div>
							<div class="row">
                                 <div class="col s6 m2 input-field">
                                     <input id="id_no" name="identification_no" type="text" class="validate" value="${rrDetails.identification_no }">
		                             <label for="id_no">Id No </label>
		                             <span id="id_noError" class="error-msg" ></span>
                                 </div>
                                 <div class="col s6 m2 input-field">
                                     <input id="map_no" name="map_sr_no" type="text" class="validate" value="${rrDetails.map_sr_no }">
		                             <label for="map_no" >Map S.No </label>
		                             <span id="map_noError" class="error-msg" ></span>
                                 </div>
                                 <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label mb-8"> Phase</p>
                                    <select class="searchable validate-dropdown" id="phase" name="phase"  >
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${phase }">
                                      	   <option value= "${ obj.phase}" <c:if test="${rrDetails.phase eq obj.phase}">selected</c:if>> ${obj.phase }</option>
                                         </c:forEach> 
                                    </select>
                                    <span id="phaseError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label mb-8"> Structure</p>
                                    <select class="searchable validate-dropdown" id="structure" name="structure_id"  >
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${structure }">
                                      	   <option value= "${ obj.structure_id}" <c:if test="${rrDetails.structure_id eq obj.structure_id}">selected</c:if>> ${obj.structure_id }</option>
                                         </c:forEach> 
                                    </select>
                                    <span id="structureError" class="error-msg" ></span>
                                </div>
                             </div>
                             <div class="row">                       
                                 <div class="col s12 m4 l4 input-field ">
                                 	<p class="searchable_label">Location <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="location_name" id="location_name">
                                        <option value="" selected>Select</option>
                                          <c:forEach var="obj" items="${location }">
                                      	   <option value= "${ obj.location_name}" <c:if test="${rrDetails.location_name eq obj.location_name}">selected</c:if>> ${obj.location_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="locationError" class="error-msg" ></span>                                     
                                </div>
                                 <div class="col s6 m4 l4 input-field ">
                                     <p class="searchable_label">Sub Location <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="sub_location_name" id="sub_location_name">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${subLocation }">
                                      	   <option value= "${ obj.sub_location_name}" <c:if test="${fn:trim(rrDetails.sub_location_name) eq fn:trim(obj.sub_location_name)}">selected</c:if>> ${obj.sub_location_name }</option>
                                         </c:forEach>
                                    </select>                                    
                                    <span id="sub_locationError" class="error-msg" ></span>                                     
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Type of Use <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="type_of_use" id="type_of_use">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${typeofUse }">
                                      	   <option value= "${ obj.type_of_use}" <c:if test="${rrDetails.type_of_use eq obj.type_of_use}">selected</c:if>> ${obj.type_of_use }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="type_of_useError" class="error-msg" ></span>
                                </div>
                             </div>
                            
                             <div class="row">
                                 <div class="col s6 m2 input-field">
                                    <input id="carpet_area" name="carpet_area" type="text" class="validate" value="${rrDetails.carpet_area }">
                                    <label for="carpet_area" class="fs-sm-8rem fs-9">Carpet Area (sft)</label>
                                    <span id="carpet_areaError" class="error-msg" ></span>
	                             </div>
                                <div class="col s6 m2 input-field">
	                                <input id="year_of_construction" name="year_of_construction" type="text" class="validate datepicker" value="${rrDetails.year_of_construction }">
                                     <button type="button" id="verification_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="year_of_construction">Year of Construction </label>
	                                <span id="construction_yearError" class="error-msg" ></span>
                                 </div>		                         		
                                <div class="col s6 m4 input-field">
                                    <input id="owner_name" name="name_of_the_owner" type="text" class="validate " value="${rrDetails.name_of_the_owner }">
                                    <label for="owner_name" class="fs-sm-8rem fs-9">Owner Name </label>
                                    <span id="owner_nameError" class="error-msg" ></span>
                                </div>  
                                <div class="col s6 m4 input-field">
                                    <input id="occupier_name" name="occupier_name_during_verification" type="text" class="validate " value="${rrDetails.occupier_name_during_verification }">
                                    <label for="occupier_name" class="fs-sm-8rem fs-9">Occupier Name </label>
                                    <span id="occupier_nameError" class="error-msg" ></span>
                                </div>  
                            </div>
						</div>
                            <div class="row ">
                            	<h6 class="center-align">Identification Details</h6>
                                <div class="col s6 m2 input-field">
                                    <p class="searchable_label mb-8">Document Type <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="document_type" id="doc_type">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${docType }">
                                      	   <option value= "${ obj.document_type}" <c:if test="${rrDetails.document_type eq obj.document_type}">selected</c:if>> ${obj.document_type }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="doc_typeError" class="error-msg" ></span>
                                </div>                               
                                <div class="col s6 m2 input-field">
                                     <input id="document_no" name="document_no" type="text" class="validate" value="${rrDetails.document_no }">
		                             <label for="document_no" >Document No </label>
		                             <span id="document_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                     <input id="verification_date" name="physical_verification" type="text" class="validate datepicker" value="${rrDetails.physical_verification }">
                                     <button type="button" id="verification_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="verification_date">Physical Verification Date</label>
		                             <span id="verification_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                     <p class="searchable_label mb-8">Verification By <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="verification_by" id="verification_by">
                                        <option value="" selected>Select</option>
                                           <c:forEach var="obj" items="${verificationBy }">
                                      	   <option value= "${ obj.verification_by}" <c:if test="${rrDetails.verification_by eq obj.verification_by}">selected</c:if>> ${obj.verification_by }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="verification_byError" class="error-msg" ></span>
                                </div>
                               
                            </div> 
                            <div class="row">
                            	 <div class="col s6 m4 l3 input-field">
                                     <input id="approval_by_committee" name="approval_by_committee" type="text" class="validate datepicker" value="${rrDetails.approval_by_committee }">
                                     <button type="button" id="approval_by_committee_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="approval_by_committee">Approval By Committee </label>
		                             <span id="approval_by_committeeError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l3 input-field">
                                     <input id="approval_by_mrvc" name="rr_approval_status_by_mrvc" type="text" class="validate datepicker" value="${rrDetails.rr_approval_status_by_mrvc }">
                                     <button type="button" id="approval_by_mrvc_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="approval_by_mrvc">Approval by MRVC</label>
		                             <span id="approval_by_mrvcError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l3 input-field">
                                     <input id="estimate_approval" name="estimate_approval_date" type="text" class="validate datepicker" value="${rrDetails.estimate_approval_date }">
                                     <button type="button" id="estimate_approval_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="estimate_approval">Estimate Approval</label>
		                             <span id="estimate_approvalError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l3 input-field amount-dropdown">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="estimation_amount" name="estimation_amount" type="number" class="validate" value="${rrDetails.estimation_amount }" min="0" step="0.00001">
                                    <label for="estimation_amount">Estimation Amount</label>
                                    <span id="estimation_amountError" class="error-msg"></span>
                                	<span id="estimation_amount_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="estimation_amount_units" name="estimation_amount_units">
                                		 <c:forEach var="obj" items="${units }">
                                      	   <option value= "${ obj.value}" <c:if test="${rrDetails.estimation_amount_units eq obj.value}">selected</c:if>> ${obj.unit }</option>
                                         </c:forEach>
                                	</select>
                                </div> 
                            </div>
                            <div class="row">
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="letter_to_mmrda" name="letter_to_mmrda" type="text" class="validate datepicker" value="${rrDetails.letter_to_mmrda }">
                                     <button type="button" id="letter_to_mmrda_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="letter_to_mmrda">Letter to MMRDA</label>
		                             <span id="letter_to_mmrdaError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field amount-dropdown">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="estimated_by_mmrda_amount" name="estimates_by_mmrda" type="number" class="validate" value="${rrDetails.estimates_by_mmrda }" min="0" step="0.00001">
                                    <label for="estimated_by_mmrda_amount">Estimate by MMRDA</label>
                                    <span id="estimated_by_mmrda_amountError" class="error-msg"></span>
                                	<span id="estimated_by_mmrda_amount_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="estimated_by_mmrda_amount_units" name="estimated_by_mmrda_amount_units">
                                    	 <c:forEach var="obj" items="${units }">
                                      	   <option value= "${ obj.value}" <c:if test="${rrDetails.estimated_by_mmrda_amount_units eq obj.value}">selected</c:if>> ${obj.unit }</option>
                                         </c:forEach>
                                	</select>
                                </div> 
                                <div class="col s6 m4 l4 input-field">
                                     <input id="payment_to_mmrda" name="payment_to_mmrda" type="text" class="validate datepicker" value="${rrDetails.payment_to_mmrda }">
                                     <button type="button" id="payment_to_mmrda_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="payment_to_mmrda">Payment to MMRDA</label>
		                             <span id="payment_to_mmrdaError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row"> 
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="alternate_housing_allotment" name="alternate_housing_allotment" type="text" class="validate datepicker" value="${rrDetails.alternate_housing_allotment }">
                                     <button type="button" id="alternate_housing_allotment_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="alternate_housing_allotment">Alternate Housing Allotment</label>
		                             <span id="alternate_housing_allotmentError" class="error-msg" ></span>
                                </div>
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="relocation" name="relocation" type="text" class="validate datepicker" value="${rrDetails.relocation }">
                                     <button type="button" id="relocation_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="relocation">Relocation</label>
		                             <span id="relocationError" class="error-msg" ></span>
                                </div>
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="encroachment_removal" name="encroachment_removal" type="text" class="validate datepicker" value="${rrDetails.encroachment_removal }">
                                     <button type="button" id="encroachment_removal_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="encroachment_removal">Encroachment Removal</label>
		                             <span id="encroachment_removalError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label mb-8">Boundary Wall Status <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="boundary_wall_status" id="boundary_wall_status">
                                        <option value="" selected>Select</option>
                                        <c:forEach var="obj" items="${status }">
                                      	   <option value= "${ obj.boundary_wall_status}" <c:if test="${rrDetails.boundary_wall_status eq obj.boundary_wall_status}">selected</c:if>> ${obj.boundary_wall_status }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="boundary_wall_statusError" class="error-msg" ></span>
                                </div>
                            	<div class="col s12 m4 input-field">
                                     <input id="boundary_wall_doc" name="boundary_wall_doc" type="text" class="validate datepicker" value="${rrDetails.boundary_wall_doc }">
                                     <button type="button" id="boundary_wall_doc_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="boundary_wall_doc">Boundary Wall Doc</label>
		                             <span id="boundary_wall_docError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                  <%--    <p class="center-align" style="margin-top:1rem;">
									      <label>
									      	<input type="hidden" name="handed_over_to_execution" class="no-reset" value ="${rrDetails.handed_over_to_execution}"
									      		 id="handover_to_executions" />
									        <input type="checkbox" class="filled-in" id="handover_to_execution" <c:if test="${rrDetails.handed_over_to_execution eq 'Yes'}">  checked</c:if> />
									        <span>Handover to Execution</span>
									      </label>
								    </p> --%>
								     <input id="handover_to_execution" name="handed_over_to_execution" type="text" class="validate datepicker" value="${rrDetails.handed_over_to_execution }">
                                     <button type="button" id="handover_to_execution_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="handover_to_execution">Handover to Execution</label>
		                             <span id="handover_to_executionError" class="error-msg" ></span>
                                </div>
                                                          
                            </div>
                         </div>
                            <div class="row" id="residential_details" style="display:none;">
                            	<div class="container container-no-margin">                            		
                            		<div class="row">
                            			<h6 class="center-align">Residential Details</h6>	
                            			<div class="col s6 m4 l3 input-field">
                            				<p class="searchable_label mb-8">Occupancy Status</p>
                                            <select id="occupancy_status" name="occupancy_status" class="searchable">
                                                <option value="">Select</option> 
                                                <c:forEach var="obj" items="${occupancyStatus }">
		                                      	   <option value= "${ obj.occupancy_status}" <c:if test="${rrDetails.occupancy_status eq obj.occupancy_status}">selected</c:if>> ${obj.occupancy_status }</option>
		                                         </c:forEach>                                                       
                                            </select>
                                            <span id="occupancy_statusError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l3 input-field">
                            				<p class="searchable_label mb-8">Gender</p>
                                            <select id="gender" name="gender" class="searchable">
                                                <option value="">Select</option>
                                                <c:forEach var="obj" items="${gender }">
		                                      	   <option value= "${ obj.gender}" <c:if test="${rrDetails.gender eq obj.gender}">selected</c:if>> ${obj.gender }</option>
		                                        </c:forEach>                                                           
                                            </select>
                                            <span id="genderError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l3 input-field">
		                                    <p class="searchable_label mb-8">Tenure Status </p>
		                                    <select class="searchable validate-dropdown" name="tenure_status" id="tenure_status">
		                                        <option value="" >Select</option>
		                                        <c:forEach var="obj" items="${tenureStatus }">
		                                      	   <option value= "${ obj.tenure_status}" <c:if test="${rrDetails.tenure_status eq obj.tenure_status}">selected</c:if>> ${obj.tenure_status }</option>
		                                        </c:forEach> 
		                                    </select>
		                                    <span id="tenure_statusError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s12 m4 l3 input-field">
		                                     <p class="center-align" style="margin-top:1rem;">
											      <label>
												    <input type="hidden" class="no-reset" name="vulnerable_category" value ="${rrDetails.vulnerable_category}" 
												    id="vulnerable_categorys" />
											        <input type="checkbox" class="filled-in"  <c:if test="${rrDetails.vulnerable_category eq 'Yes'}">  checked</c:if> id="vulnerable_category" />
											        <span>Vulnerable Category</span>
											      </label>
											   
										    </p>
		                                </div>
                            		</div>
                            		<div class="row">
                            			<div class="col s6 m4 l4 input-field">
                            				<p class="searchable_label">Caste</p>
                                            <select id="caste" name="caste" class="searchable">
                                                <option value="">Select</option>   
                                                 <c:forEach var="obj" items="${caste }">
		                                      	   <option value= "${ obj.caste}" <c:if test="${rrDetails.caste eq obj.caste}">selected</c:if>> ${obj.caste }</option>
		                                        </c:forEach>                                                      
                                            </select>
                                            <span id="casteError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l4 input-field">
                            				<p class="searchable_label ">Mother Tongue</p>
                                            <select id="mother_tongue" name="mother_tongue" class="searchable">
                                                <option value="">Select</option>      
                                                <c:forEach var="obj" items="${motherTongue }">
		                                      	   <option value= "${ obj.mother_tongue}" <c:if test="${rrDetails.mother_tongue eq obj.mother_tongue}">selected</c:if>> ${obj.mother_tongue }</option>
		                                        </c:forEach>                                                 
                                            </select>
                                            <span id="mother_tongueError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l4 input-field">
		                                    <p class="searchable_label">Type of Family </p>
		                                    <select class="searchable validate-dropdown" name="type_of_family" id="type_of_family">
		                                        <option value="" >Select</option>
		                                        <c:forEach var="obj" items="${typeofFamily }">
		                                      	   <option value= "${ obj.type_of_family}" <c:if test="${rrDetails.type_of_family eq obj.type_of_family}">selected</c:if>> ${obj.type_of_family }</option>
		                                        </c:forEach> 
		                                    </select>
		                                    <span id="type_of_familyError" class="error-msg" ></span>
		                                </div>		                                
                            		</div>
                            		<div class="row">
                            			 <div class="col s6 m4 l3 input-field">
	                                       <input id="family_size" name="family_size" type="number" class="validate" value="${rrDetails.family_size }">
			                               <label for="family_size">Family Size </label>
			                               <span id="family_sizeError" class="error-msg" ></span>
	                                    </div>
                            			 <div class="col s6 m4 l3 input-field">
	                                       <input id="number_of_married_couple" name="number_of_married_couple" type="number" class="validate" value="${rrDetails.number_of_married_couple }">
			                               <label for="number_of_married_couple">No of Married Couple </label>
			                               <span id="number_of_married_coupleError" class="error-msg" ></span>
	                                    </div>
		                                <div class="col s6 m4 l3 input-field amount-dropdown">
		                                    <i class="material-icons amount-symbol center-align">₹</i>
		                                    <input id="family_income_amount" name="family_income_amount" type="number" class="validate" value="${rrDetails.family_income_amount }" min="0" step="0.00001">
		                                    <label for="family_income_amount">Family Income</label>
		                                    <span id="family_income_amountError" class="error-msg"></span>
		                                	<span id="family_income_amount_unitsError" class="error-msg right" ></span>
		                                    <select class="validate-dropdown" id="family_income_amount_units" name="family_income_amount_units">
		                                     <c:forEach var="obj" items="${units }">
	                                      	   <option value= "${ obj.value}" <c:if test="${rrDetails.family_income_amount_units eq obj.value}">selected</c:if>> ${obj.unit }</option>
	                                         </c:forEach>
		                                	</select>
		                                </div>
		                                <div class="col s6 m4 l3 input-field">
	                                       <p class="searchable_label">Monthly Per Capita Income</p>
	                                       <p id="percapita_per_month"></p>
	                                    </div>
                            		</div>
                            	</div>
                            	<div class="table-inside">
                            		<h6 class="center-align">Family Details</h6>                            		
                                    <table id="residentDetails" class="mdl-data-table mobile_responsible_table another">
                                        <thead>
                                            <tr>                                            
                                                <th>name</th>
												<th>relation with head</th>
												<th class="max-80">age</th>
												<th>gender</th>
												<th>maritual status</th>
												<th>education</th>
												<th>employment</th>
												<th>monthly salary (in rupees)</th>
                                                <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">
                                                	<th>Action</th>
                                                </c:if>
                                            </tr>
                                        </thead>
                                        <tbody id="residentDetailsBody">
                                        <c:choose>
                                        <c:when test="${not empty rrDetails.residentialList && fn:length(rrDetails.residentialList) gt 0 }">
                                          <c:forEach var="resObj" items="${rrDetails.residentialList }" varStatus="index">  
                                            <tr id="residentDetailRow${index.count }" class="">
                                                <td data-head="Name" class="input-field">
                                                    <input id="residential_name${index.count }" name="residential_names" type="text" class="validate"  value="${resObj.residential_name }"
                                                        placeholder="Name">
                                                </td>
                                                <td data-head="Relation with Head" class="input-field">
                                                    <input id="residential_relation_with_head${index.count }" name="residential_relation_with_heads" type="text" class="validate"  value="${resObj.residential_relation_with_head }"
                                                        placeholder="Relation with Head">
                                                </td>
                                                <td data-head="Age" class="input-field">
                                                    <input id="residential_age${index.count }" name="residential_ages" type="number" class="validate"  value="${resObj.residential_age }"
                                                        placeholder="Age">
                                                </td>    
                                                <td data-head="Gender" class="input-field">
                                                    <select id="residential_gender${index.count }" name="residential_genders" class="searchable">
                                                        <option value="">Select</option>
                                                       <c:forEach var="obj" items="${gender }">
				                                      	   <option value= "${ obj.gender}" <c:if test="${resObj.residential_gender eq obj.gender}">selected</c:if>> ${obj.gender }</option>
				                                        </c:forEach>                                                          
                                                    </select>
                                                    <span id="residential_gender${index.count }Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Maritual Status" class="input-field center-align"> 
                                                	<select id="residential_maritual_status${index.count }" name="residential_maritual_statuss" class="searchable">
                                                        <option value="" >Select</option>
                                                         <c:forEach var="obj" items="${maritualStatus }">
				                                      	   <option value= "${ obj.maritua_status}" <c:if test="${resObj.residential_maritual_status eq obj.maritua_status}">selected</c:if>> ${obj.maritua_status }</option>
				                                        </c:forEach>  
                                                    </select>
                                                    <span id="residential_maritual_status${index.count }Error" class="my-error"></span>
                                            	</td>     
                                            	<td data-head="Education" class="input-field">
                                                    <input id="residential_education${index.count }" name="residential_educations" type="text" class="validate"  value="${resObj.residential_education }"
                                                        placeholder="Education">
                                                </td>  
                                                <td data-head="Employment" class="input-field">
                                                    <input id="residential_employment${index.count }" name="residential_employments" type="text" class="validate"  value="${resObj.residential_employment }"
                                                        placeholder="Employment">
                                                </td>                                                                                     
                                                <td data-head="Monthly Salary" class="input-field amount-dropdown">
                                               		<i class="material-icons amount-symbol cost left-align">₹</i>
                                                   	<input id="residential_salary${index.count }" name="residential_salarys" min="0.01" step="0.01" type="number" class="validate" value="${resObj.residential_salary }"
                                                       placeholder="Salary">
                                                       <select class="validate-dropdown" id="residential_salary_units${index.count }" name="residential_salary_unitss">
				                                		 <c:forEach var="obj" items="${units }">
				                                      	   <option value= "${ obj.value}" <c:if test="${resObj.residential_salary_units eq obj.value}">selected</c:if>> ${obj.unit }</option>
				                                         </c:forEach>
				                                	</select>
				                                	<span id="residential_salary_units${index.count }Error" class="my-error my-error"></span>
                                                 </td> 
                                                                                             
	                                            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">    
	                                                <td class="mobile_btn_close">
	                                                    <a onclick="removeResidentRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
                                                </c:if>
                                            </tr>
                                            
                                             </c:forEach>
                                             </c:when>
                                             <c:otherwise>
                                             
                                              <tr id="residentDetailRow0" class="">
                                                <td data-head="Name" class="input-field">
                                                    <input id="residential_name0" name="residential_names" type="text" class="validate"  value=""
                                                        placeholder="Name">
                                                </td>
                                                <td data-head="Relation with Head" class="input-field">
                                                    <input id="residential_relation_with_head0" name="residential_relation_with_heads" type="text" class="validate"  value=""
                                                        placeholder="Relation with Head">
                                                </td>
                                                <td data-head="Age" class="input-field">
                                                    <input id="residential_age0" name="residential_ages" type="number" class="validate"  value=""
                                                        placeholder="Age">
                                                </td>    
                                                <td data-head="Gender" class="input-field">
                                                    <select id="residential_gender0" name="residential_genders" class="searchable">
                                                        <option value="">Select</option>
                                                       <c:forEach var="obj" items="${gender }">
				                                      	   <option value= "${ obj.gender}" > ${obj.gender }</option>
				                                        </c:forEach> 
                                                    </select>
                                                    <span id="residential_gender0Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Maritual Status" class="input-field center-align"> 
                                                	<select id="residential_maritual_status0" name="residential_maritual_statuss" class="searchable">
                                                        <option value="" >Select</option>
                                                         <c:forEach var="obj" items="${maritualStatus }">
				                                      	   <option value= "${ obj.maritua_status}" > ${obj.maritua_status }</option>
				                                        </c:forEach> 
                                                    </select>
                                                    <span id="residential_maritual_status0Error" class="my-error"></span>
                                            	</td>     
                                            	<td data-head="Education" class="input-field">
                                                    <input id="residential_education0" name="residential_educations" type="text" class="validate"  value=""
                                                        placeholder="Education">
                                                </td>  
                                                <td data-head="Employment" class="input-field">
                                                    <input id="residential_employment0" name="residential_employments" type="text" class="validate"  value=""
                                                        placeholder="Employment">
                                                </td>                                                                                     
                                                <td data-head="Monthly Salary" class="input-field amount-dropdown">
                                               		<i class="material-icons amount-symbol cost left-align">₹</i>
                                                   	<input id="residential_salary0" name="residential_salarys" min="0.01" step="0.01" type="number" class="validate" value=""
                                                       placeholder="Salary">
                                                       <select class="validate-dropdown" id="residential_salary_units0" name="residential_salary_unitss">
				                                		 <c:forEach var="obj" items="${units }">
				                                      	   <option value= "${ obj.value}" > ${obj.unit }</option>
				                                         </c:forEach>
				                                	</select>
				                                	<span id="residential_salary_units0Error" class="my-error my-error"></span>
                                                 </td> 
                                                                                             
	                                            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">    
	                                                <td class="mobile_btn_close">
	                                                    <a onclick="removeResidentRow('0');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
                                                </c:if>
                                            </tr>
                                           
                                             </c:otherwise>
                                            </c:choose>  
                                        </tbody>
                                    </table>
                                     <table  class="mdl-data-table">
                                        <tbody id="residentDetailsBody">                                          
                                            <tr>
                                   <td colspan="8"  ><a   class="btn waves-effect waves-light bg-m t-c "  onclick="addResidentDetailRow()"> <i class="fa fa-plus"></i></a></td>
                                             </tr>
                                        </tbody>
                                    </table>
									<c:choose>
                                        <c:when test="${not empty rrDetails.residentialList && fn:length(rrDetails.residentialList) gt 0 }">
                                    		<input type="hidden" id="residentDetailRowNo"  name="residentDetailRowNo" value="${fn:length(rrDetails.residentialList) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="residentDetailRowNo"  name="residentDetailRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
                                </div>
                            </div>
                             
                            <div class="row" id="commercial_details" style="display:none;">
                            	<div class="container container-no-margin">                            		
                            		<div class="row">
                            			<h6 class="center-align">Commercial Details</h6>	
                            			 <div class="col s6 m4 l2 input-field">
		                                     <input id="name_of_activity" name="name_of_activity" type="text" class="validate" value="${rrDetails.name_of_activity }">
				                             <label for="name_of_activity" >Name of Activity </label>
				                             <span id="name_of_activityError" class="error-msg" ></span>
		                                 </div>
		                                 <div class="col s6 m4 l2 input-field">
		                                    <%-- <p class="searchable_label mb-8">Year of Establishment</p>
		                                    <select class="searchable validate-dropdown" name="year_of_establishment" id="year_of_establishment">
		                                        <option value="" selected>Select</option>
		                                         <c:forEach var="obj" items="${preparedBy }">
		                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${rrDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
		                                          </c:forEach>
		                                    </select>
		                                    <span id="year_of_establishmentError" class="error-msg" ></span> --%>
		                                    <input id="year_of_establishment" name="year_of_establishment" type="number" class="validate" value="${rrDetails.year_of_establishment }">
			                                <label for="year_of_establishment">Year of Establishment </label>
			                                <span id="year_of_establishmentError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m4 l2 input-field">
	                                       <input id="com_carpet_area" name="com_carpet_area" type="number" class="validate" value="${rrDetails.com_carpet_area }">
			                               <label for="com_carpet_area">Carpet Area (in sqft) </label>
			                               <span id="com_carpet_areaError" class="error-msg" ></span>
	                                    </div>
		                                <div class="col s6 m4 l3 input-field amount-dropdown">
		                                    <i class="material-icons amount-symbol center-align">₹</i>
		                                    <input id="monthly_turnover_amount" name="monthly_turnover_amount" type="number" class="validate" value="${rrDetails.monthly_turnover_amount }" min="0" step="0.00001">
		                                    <label for="monthly_turnover_amount">Monthly Turnover</label>
		                                    <span id="monthly_turnover_amountError" class="error-msg"></span>
		                                	<span id="monthly_turnover_amount_unitsError" class="error-msg right" ></span>
		                                    <select class="validate-dropdown" id="monthly_turnover_amount_units" name="monthly_turnover_amount_units">
		                                    	 <c:forEach var="obj" items="${units }">
		                                      	   <option value= "${ obj.value}" <c:if test="${rrDetails.monthly_turnover_amount_units eq obj.value}">selected</c:if>> ${obj.unit }</option>
		                                         </c:forEach>
		                                	</select>
		                                </div>
	                                   <div class="col s6 m4 l3 input-field">
	                                      <input id="number_of_employees" name="number_of_employees" type="number" class="validate" value="${rrDetails.number_of_employees }">
			                              <label for="number_of_employees">Number of Employees </label>
			                              <span id="number_of_employeesError" class="error-msg" ></span>
	                                   </div>
                            		</div>                            		 
                            		<div class="row">
                            			<div class="col s12 m12 input-field">
		                                    <textarea id="remarks" name="com_remarks" class="pmis-textarea" data-length="1000">${rrDetails.com_remarks }</textarea>
		                                    <label for="remarks">Remarks</label>
		                                </div>	
                            		</div>
                            	</div>
                            	<div class="table-inside">  
                            	    <h6 class="center-align">Employee Details</h6>                     		
                                    <table id="employeeDetails" class="mdl-data-table mobile_responsible_table another">
                                        <thead>
                                            <tr>
                                                <th>name</th>
												<th class="max-80">age</th>
												<th>gender</th>
												<th>literate</th>
												<th>class attended</th>
												<th>travel time <br>from residence <br>(in minutes)</th>
												<th>monthly salary (in rupees)</th>
												<th>nature of <br>work</th>
                                                <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">
                                                	<th>Action</th>
                                                </c:if>
                                            </tr>
                                        </thead>
                                        <tbody id="employeeDetailsBody">
                                        <c:choose>
                                        <c:when test="${not empty rrDetails.commercialList && fn:length(rrDetails.commercialList) gt 0 }">
                                          <c:forEach var="comObj" items="${rrDetails.commercialList }" varStatus="index">  
                                            <tr id="employeeDetailRow${index.count }" class="">
                                                <td data-head="Name" class="input-field">
                                                    <input id="employee_name${index.count }" name="employee_names" type="text" class="validate"  value="${comObj.employee_name}"
                                                        placeholder="Name">
                                                </td>
                                                <td data-head="Age" class="input-field">
                                                    <input id="employee_age${index.count }" name="employee_ages" type="number" class="validate"  value="${comObj.employee_age}"
                                                        placeholder="Age">
                                                </td>    
                                                <td data-head="Gender" class="input-field">
                                                    <select id="employee_gender${index.count }" name="employee_genders" class="searchable">
                                                        <option value="">Select</option>
                                                       <c:forEach var="obj" items="${gender }">
				                                      	   <option value= "${ obj.gender}" <c:if test="${comObj.employee_gender eq obj.gender}">selected</c:if>> ${obj.gender }</option>
				                                        </c:forEach>
                                                    </select>
                                                    <span id="employee_gender${index.count }Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Literate" class="input-field center-align"> 
                                                	<p>
                                                		<label> 
                                                			<input type="hidden" id="employee_literacys${index.count }" name="employee_literacys" value="${comObj.employee_literacy}" />
                                                			<input type="checkbox" id="employee_literacy${index.count }" <c:if test="${comObj.employee_literacy eq 'Yes'}">  checked</c:if> > <span></span> 
                                                		</label>	
                                                	</p>
                                            	</td>     
                                            	<td data-head="Class Attended" class="input-field">
                                                    <input id="employee_attended${index.count }" name="employee_attendeds" type="text" class="validate"  value="${comObj.employee_attended}"
                                                        placeholder="Class Attended">
                                                </td>  
                                                <td data-head="Travel Time" class="input-field">
                                                    <input id="employee_travel_time${index.count }" name="employee_travel_times" type="number" class="validate"  value="${comObj.employee_travel_time}"
                                                        placeholder="Travel Time">
                                                </td>                                                                                     
                                                <td data-head="Monthly Salary" class="input-field amount-dropdown">
                                               		<i class="material-icons amount-symbol cost left-align">₹</i>
                                                   	<input id="employee_salary${index.count }" name="employee_salarys" min="0.01" step="0.01" type="number" class="validate" value="${comObj.employee_salary}"
                                                       placeholder="Salary">
                                                       <select class="validate-dropdown" id="employee_salary_units${index.count }" name="employee_salary_unitss">
				                                		 <c:forEach var="obj" items="${units }">
				                                      	   <option value= "${ obj.value}" <c:if test="${comObj.employee_salary_units eq obj.value}">selected</c:if>> ${obj.unit }</option>
				                                         </c:forEach>
				                                	</select>
				                                	<span id="employee_salary_units${index.count }Error" class="my-error my-error"></span>
                                                 </td> 
                                                 <td data-head="Nature of Work" class="input-field">
                                                    <input id="employee_nature_of_work${index.count }" name="employee_nature_of_works" type="text" class="validate"  value="${comObj.employee_nature_of_work}"
                                                        placeholder="Nature of Work">
                                                 </td>
                                            
	                                            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">    
	                                                <td class="mobile_btn_close">
	                                                    <a onclick="removeEmployeeRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
                                                </c:if>
                                            </tr>
                                            <script>
	                                            $('#employee_literacy${index.count }').on('change', function(e){
	                              	              if($(this).prop('checked'))
	                              	              {
	                              	                  $('#employee_literacys${index.count }').val('Yes');
	                              	              }else{
	                              	               	  $('#employee_literacys${index.count }').val('No')
	                              	             	  $('#employee_literacy${index.count }').prop('checked',false).removeAttr('checked');
	                              	               }
	                              	    	    }); 
                                            
                                            </script>
                                             </c:forEach>
                                             </c:when>
                                             <c:otherwise>
                                             
                                              <tr id="employeeDetailRow0">
                                                <td data-head="Name" class="input-field">
                                                    <input id="employee_name0" name="employee_names" type="text" class="validate"  value=""
                                                        placeholder="Name">
                                                </td>
                                                <td data-head="Age" class="input-field">
                                                    <input id="employee_age0" name="employee_ages" type="number" class="validate"  value=""
                                                        placeholder="Age">
                                                </td>    
                                                <td data-head="Gender" class="input-field">
                                                    <select id="employee_gender0" name="employee_genders" class="searchable">
                                                        <option value="">Select</option>
                                                       <c:forEach var="obj" items="${gender }">
				                                      	   <option value= "${ obj.gender}" > ${obj.gender }</option>
				                                        </c:forEach>
                                                    </select>
                                                    <span id="employee_gender0Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Literate" class="input-field center-align"> 
                                                	<p>
                                                		<label> 
                                                			<input type="hidden" id="employee_literacys0" name="employee_literacys" value="" />
                                                			<input type="checkbox" id="employee_literacy0" > <span></span> 
                                                		</label>	
                                                	</p>
                                            	</td>     
                                            	<td data-head="Class Attended" class="input-field">
                                                    <input id="employee_attended0" name="employee_attendeds" type="text" class="validate"  value=""
                                                        placeholder="Class Attended">
                                                </td>  
                                                <td data-head="Travel Time" class="input-field">
                                                    <input id="employee_travel_time${index.count }" name="employee_travel_times" type="number" class="validate"  value=""
                                                        placeholder="Travel Time">
                                                </td>                                                                                     
                                                <td data-head="Monthly Salary" class="input-field amount-dropdown">
                                               		<i class="material-icons amount-symbol cost left-align">₹</i>
                                                   	<input id="employee_salary0" name="employee_salarys" min="0.01" step="0.01" type="number" class="validate" value=""
                                                       placeholder="Salary">
                                                       <select class="validate-dropdown" id="employee_salary_units0" name="employee_salary_unitss">
				                                		 <c:forEach var="obj" items="${units }">
				                                      	   <option value= "${ obj.value}" > ${obj.unit }</option>
				                                         </c:forEach>
				                                	</select>
				                                	<span id="employee_salary_units0Error" class="my-error my-error"></span>
                                                 </td> 
                                                 <td data-head="Nature of Work" class="input-field">
                                                    <input id="employee_nature_of_work0" name="employee_nature_of_works" type="text" class="validate"  value=""
                                                        placeholder="Nature of Work">
                                                 </td>
                                                 
                                                <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">          
	                                                <td class="mobile_btn_close">
	                                                    <a onclick="removeEmployeeRow('0');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
                                                </c:if>
                                            </tr>
                                           
                                             </c:otherwise>
                                            </c:choose>  
                                        </tbody>
                                    </table>
                                     <table  class="mdl-data-table">
                                        <tbody id="employeeDetailsBody">                                          
                                            <tr>
                                   <td colspan="8"  ><a   class="btn waves-effect waves-light bg-m t-c "  onclick="addEmployeeDetailRow()"> <i class="fa fa-plus"></i></a></td>
                                             </tr>
                                        </tbody>
                                    </table>
									<c:choose>
                                        <c:when test="${not empty rrDetails.commercialList && fn:length(rrDetails.commercialList) gt 0 }">
                                    		<input type="hidden" id="employeeDetailRowNo"  name="employeeDetailRowNo" value="${fn:length(rrDetails.commercialList) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="employeeDetailRowNo"  name="employeeDetailRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
                                </div>
                            </div>
                                                   
 						<div class="container container-no-margin">
                           
                            <div class="row">
                                <div class="col s12 m12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${rrDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom:20px;">
                                <div class="col s6 offset-m2 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateRR();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" id="disabled" onclick="addRR();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                                </div> 
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/randr-main" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                            </div>
                           
                        </form>
                    
                    <!-- form ends  -->
                </div>
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



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

   <script>
   $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
    	$('.searchable').select2();
    	$(".datepicker").datepicker();
    	
    	$('#type_of_use').change(function(){
    		if($('#type_of_use').val()=='Residential'){
    			$('#residential_details').show();
    			$('#commercial_details').hide();    			
    		}
    		else if($('#type_of_use').val()=='Commercial'){
    			$('#commercial_details').show();
    			$('#residential_details').hide();    			
    		}else{
    			$('#commercial_details').hide();
    			$('#residential_details').hide(); 
    		}
    	});
    	if($('#type_of_use').val()=='Residential'){
			$('#residential_details').show();
			$('#commercial_details').hide();    			
		}
		else if($('#type_of_use').val()=='Commercial'){
			$('#commercial_details').show();
			$('#residential_details').hide();    			
		}else{
			$('#commercial_details').hide();
			$('#residential_details').hide(); 
		}
    	  $('#handover_to_execution').on('change', function(e){
              if($(this).prop('checked'))
              {
                  $('#handover_to_executions').val('Yes');
              }else{
               	  $("#handover_to_executions").val('No')
             	  $("#handover_to_execution").prop('checked',false).removeAttr('checked');
               }
    	    });    
    	  
    	   
	      	 $('#vulnerable_category').on('change', function(e){
              if($(this).prop('checked'))
              { 
                  $('#vulnerable_categorys').val('Yes');
              }else{
               	  $("#vulnerable_categorys").val('No')
             	  $("#vulnerable_category").prop('checked',false).removeAttr('checked');
               }
    	    });
	      	 $('#employee_literacy0').on('change', function(e){
	              if($(this).prop('checked'))
	              {
	                  $('#employee_literacys0').val('Yes');
	              }else{
	               	  $('#employee_literacys0').val('No')
	             	  $('#employee_literacy0').prop('checked',false).removeAttr('checked');
	               }
	    	    }); 
    	$('#family_size,#family_income_amount,#family_income_amount_units').change(function(){
    		var family_size = Number($('#family_size').val());
    		var family_income = Number($('#family_income_amount').val());
    		var income_units = $('#family_income_amount_units option:selected').text();
    		if(family_size != "" && family_income != "" && income_units != ""){
    			var percapita_income = Math.round((family_income / family_size) * 100)/100
        		$('#percapita_per_month').text(	percapita_income+' '+income_units	);
    		}
    	});
    	var family_size = Number($('#family_size').val());
		var family_income = Number($('#family_income_amount').val());
		var income_units = $('#family_income_amount_units option:selected').text();
		if(family_size != "" && family_income != "" && income_units != ""){
			var percapita_income = Math.round((family_income / family_size) * 100)/100
			$('#percapita_per_month').text(	percapita_income+' '+income_units	);
		}
	
   });
   
   function checkRRId(){
	   
	   var arr = '${rrId}';
	   var rrId = $('#rr_id').val();
	   if ($.trim(rrId) != "") {
           var myParams = {};
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getRRIdListForRRForm",
               data: myParams, cache: false,
               success: function (data) {
                   if (data.length > 0) {
                       $.each(data, function (i, val) {
                           if ($.trim(rr_id) != '' && val.rr_id == $.trim(rrId)) {
                        	   $("#rr_idError").text(rrId +" Already Exixts!").addClass('my-error-class');
                        	   $('#disabled').prop('disabled', true);
                        	   return false;
                           } else {
                        	   $("#rr_idError").text("");
                        	   $('#disabled').prop('disabled', false);
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
   function addEmployeeDetailRow(){
	    var rowNo = $("#employeeDetailRowNo").val();
	    var rNo = Number(rowNo)+1;
	    var html = '<tr id="employeeDetailRow'+rNo+'"> <td data-head="Name" class="input-field"> <input id="employee_name'+rNo+'" name="employee_names" type="text" class="validate"'
	    		  +' value="" placeholder="Name"> </td> <td data-head="Age" class="input-field"> <input id="employee_age'+rNo+'" name="employee_ages" type="number" class="validate" '
	    		  +' value="" placeholder="Age"> </td> <td data-head="Gender" class="input-field"> <select id="employee_gender'+rNo+'" name="employee_genders" class="searchable">'
	    		  +' <option value="">Select</option> '
	    		  	<c:forEach var="obj" items="${gender }">
	    		  		+' <option value= "${ obj.gender}"> ${obj.gender }</option>'
               		</c:forEach>
        		  +'</select> <span id="employee_gender'+rNo+'Error" class="my-error"></span> </td>'
    			  +'<td data-head="Literate" class="input-field center-align"><p><label> <input type="hidden" id="employee_literacys'+rNo+'" name="employee_literacys" value="" />'
    			  +'<input type="checkbox" id="employee_literacy'+rNo+'" > <span></span> </label> </p></td> '
    			  +'<td data-head="Class Attended" class="input-field"> <input id="employee_attended'+rNo+'" name="employee_attendeds" type="text" class="validate"  value="" placeholder="Class Attended"> </td>'
    			  +'<td data-head="Travel Time" class="input-field"> <input id="employee_travel_time'+rNo+'" name="employee_travel_times" type="number" class="validate"  value="" placeholder="Travel Time"> </td>'
    			  +'<td data-head="Monthly Salary" class="input-field amount-dropdown"> <i class="material-icons amount-symbol cost left-align">₹</i> <input id="employee_salary'+rNo+'" name="employee_salarys" min="0.01"'
    			  +' step="0.01" type="number" class="validate" value="" placeholder="Salary"> <select class="validate-dropdown" id="employee_salary_units'+rNo+'" name="employee_salary_unitss">'
		    		 <c:forEach var="obj" items="${units }">
		    		  +' <option value= "${ obj.value}"> ${obj.unit }</option>'
                     </c:forEach>
    			  +'</select> <span id="employee_salary_units'+rNo+'Error" class="my-error my-error"></span>     </td> '
    			  +'<td data-head="Nature of Work" class="input-field"> <input id="employee_nature_of_work'+rNo+'" name="employee_nature_of_works" type="text" class="validate"  value="" placeholder="Nature of Work"> </td>'
    			  <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">          
        		  +'<td class="mobile_btn_close"> <a onclick="removeEmployeeRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    			  </c:if>
				  +'</tr>';		   
		 $('#employeeDetailsBody').append(html);
		 $("#employeeDetailRowNo").val(rNo);
		 $('.searchable').select2();
		 $('select:not(.searchable):not(.units)').formSelect();
		 $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
		  $('#employee_literacy'+rNo).on('change', function(e){
              if($(this).prop('checked'))
              {
                  $('#employee_literacys'+rNo).val('Yes');
              }else{
               	  $('#employee_literacys'+rNo).val('No')
             	  $('#employee_literacy'+rNo).prop('checked',false).removeAttr('checked');
               }
    	    });  
   }
   
   function removeEmployeeRow(rowNo){
		$("#employeeDetailRow"+rowNo).remove();
	}
   
   function addResidentDetailRow(){
	    var rowNo = $("#residentDetailRowNo").val();
	    var rNo = Number(rowNo)+1;
	    var html = '<tr id="residentDetailRow'+rNo+'" > <td data-head="Name" class="input-field"> <input id="residential_name'+rNo+'" name="residential_names" type="text" class="validate"  value="" placeholder="Name"> </td>'
	    		   +'<td data-head="Relation with Head" class="input-field"> <input id="residential_relation_with_head'+rNo+'" name="residential_relation_with_heads" type="text" class="validate"  value="" placeholder="Relation with Head"> </td>'
	    		   +'<td data-head="Age" class="input-field"> <input id="residential_age'+rNo+'" name="residential_ages" type="number" class="validate"  value="" placeholder="Age"> </td> '
	    		   +'<td data-head="Gender" class="input-field"> <select id="residential_gender'+rNo+'" name="residential_genders" class="searchable"> <option value="">Select</option> '
	    		   		<c:forEach var="obj" items="${gender }">
		   		  		+' <option value= "${ obj.gender}"> ${obj.gender }</option>'
		          		</c:forEach>	    		   +'<option value="other">Other</option> </select> <span id="residential_gender'+rNo+'Error" class="my-error"></span> </td>'
	    		   +'<td data-head="Maritual Status" class="input-field center-align"> <select id="residential_maritual_status'+rNo+'" name="residential_maritual_statuss" class="searchable"> <option value="" >Select</option>'
	    		   		<c:forEach var="obj" items="${maritualStatus }">
		   		  		+' <option value= "${ obj.maritua_status}"> ${obj.maritua_status }</option>'
		          		</c:forEach>
        		  +'</select> <span id="residential_maritual_status'+rNo+'Error" class="my-error"></span> </td>     '
        		  +'<td data-head="Education" class="input-field"> <input id="residential_education'+rNo+'" name="residential_educations" type="text" class="validate"  value="" placeholder="Education"> </td>  '
        		  +'<td data-head="Employment" class="input-field"> <input id="residential_employment'+rNo+'" name="residential_employments" type="text" class="validate"  value="" placeholder="Employment"> </td>  '
        		  +'<td data-head="Monthly Salary" class="input-field amount-dropdown">	<i class="material-icons amount-symbol cost left-align">₹</i> <input id="residential_salary'+rNo+'" name="residential_salarys" min="0.01" step="0.01" type="number" '
        		  +'class="validate" value="" placeholder="Salary"> <select class="validate-dropdown" id="residential_salary_units'+rNo+'" name="residential_salary_unitss">'
	    		 	<c:forEach var="obj" items="${units }">
		    		  +' <option value= "${ obj.value}"> ${obj.unit }</option>'
                     </c:forEach>
    			+'</select>	<span id="residential_salary_units'+rNo+'Error" class="my-error my-error"></span></td> '                                                 
			    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">    
			        +'<td class="mobile_btn_close"> <a onclick="removeResidentRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td>'
			    </c:if>
				+'</tr>';		   
		 $('#residentDetailsBody').append(html);
		 $("#residentDetailRowNo").val(rNo);
		 $('.searchable').select2();
		 $('select:not(.searchable):not(.units)').formSelect();
		 $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
  }
   
   function removeResidentRow(rowNo){
		$("#employeeDetailRow"+rowNo).remove();
	}
   
   function getWorksList(projectId) {
   	$(".page-loader").show();
       $("#work_id option:not(:first)").remove();

       if ($.trim(projectId) != "") {
           var myParams = { project_id_fk: projectId };
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInRR",
               data: myParams, cache: false,
               success: function (data) {
                   if (data.length > 0) {
                       $.each(data, function (i, val) {
                           var workName = '';
                           if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                           var workId = "${rrDetails.work_id}";
                           if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                               $("#work_id").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                           } else {
                               $("#work_id").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
  			$("#project_id_fkError").text("");
  		}
  		
   }
   

   function addRR(){
   	 if(validator.form()){ // validation perform
       	$(".page-loader").show();	
       	$('form input[name=employee_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_ages]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_genders]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_literacys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_attendeds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_travel_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_salarys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_salary_unitss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=employee_nature_of_works]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			
 			$('form input[name=residential_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_relation_with_heads]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_ages]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_genders]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_maritual_statuss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_educations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_employments]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_salarys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=residential_salary_unitss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			document.getElementById("RandRForm").submit();			
	 	 }
   }
   function updateRR(){
   	if(validator.form()){ // validation perform
       	$(".page-loader").show();	
       	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		$('form input[name=employee_ages]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_genders]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_literacys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_attendeds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_travel_times]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_salarys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_salary_unitss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=employee_nature_of_works]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			
			$('form input[name=residential_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_relation_with_heads]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_ages]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_genders]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_maritual_statuss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_educations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_employments]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_salarys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
			$('form input[name=residential_salary_unitss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			document.getElementById("RandRForm").submit();	
   	}
   }
   
 
   var validator =	$('#RandRForm').validate({
		 errorClass: "my-error-class",
		 validClass: "my-valid-class",
		 ignore: ":hidden:not(.validate-dropdown)",
 		    rules: {
 		 		   "project_id_fk": {
 			 		  required: true
 			 	  },"work_id": {
 			 		  required: true
 			 	  }	,"rr_id"	:{
 			 		  required:true
 			 	  }
 		 	},
 		    messages: {
 		 		   "project_id_fk": {
 			 		  required: 'Required'
 			 	  },"work_id": {
 			 		  required: 'Required'
 			 	  }	,"rr_id"	:{
 			 		  required:'Required'
 			 	  }
	   		},
	   		errorPlacement:function(error, element){
	   		 	  if(element.attr("id") == "project_id_fk" ){
				     document.getElementById("project_id_fkError").innerHTML="";
			 	     error.appendTo('#project_id_fkError');
				 }else if(element.attr("id") == "work_id" ){
				     document.getElementById("work_id_fkError").innerHTML="";
			 	     error.appendTo('#work_id_fkError');
				 }else if(element.attr("id") == "rr_id" ){
				     document.getElementById("rr_idError").innerHTML="";
			 	     error.appendTo('#rr_idError');
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
            },
	   		submitHandler:function(form){
		    	//form.submit();
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