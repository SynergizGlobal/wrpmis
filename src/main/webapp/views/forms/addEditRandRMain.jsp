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
                               	    <c:if test="${action eq 'edit'}">Update R & R</c:if>
									<c:if test="${action eq 'add'}"> Add R & R</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-randr" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-randr" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
						  
						   <input type="hidden" id="design_id" name="design_id" value="${designDetails.design_id }">
						  
						    <div class="container container-no-margin">
                            <div class="section ">
                            <div class="row">
						    <c:if test="${action eq 'add'}">	
                                <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  >
                                         <option value="" >Select</option>
                                         <%-- <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m8 l8 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             
                            </c:if>
 							<c:if test="${action eq 'edit'}">		                             
	                                <div class="col s6 m4 l4 input-field ">
	                                    <input type="text" value="" readonly />
								    	<label for="project_id_fk">Project <span class="required">*</span></label>
								    	<input type="hidden" name="project_id_fk" id="project_id_fk" value="${designDetails.project_id_fk}" readonly />
								    </div> 
	                                <div class="col s12 m8 l8 input-field"> 
	                                    <input type="text" value="" readonly />
	                                	<label for="work_id_fk">Work <span class="required">*</span></label>
	                                	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${designDetails.work_id_fk}" readonly />
	                                </div>
	                           
                              </c:if>
							</div>
							<div class="row">
                                 <div class="col s6 m2 input-field">
                                     <input id="id_no" name="id_no" type="text" class="validate" value="">
		                             <label for="id_no">Id No </label>
		                             <span id="id_noError" class="error-msg" ></span>
                                 </div>
                                 <div class="col s6 m2 input-field">
                                     <input id="map_no" name="map_no" type="text" class="validate" value="">
		                             <label for="map_no" >Map S.No </label>
		                             <span id="map_noError" class="error-msg" ></span>
                                 </div>
                                 <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label mb-8"> Phase</p>
                                    <select class="searchable validate-dropdown" id="phase" name="phase"  >
                                         <option value="" >Select</option>
                                         <%-- <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                    <span id="phaseError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label mb-8"> Structure</p>
                                    <select class="searchable validate-dropdown" id="structure" name="structure"  >
                                         <option value="" >Select</option>
                                         <%-- <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                    <span id="structureError" class="error-msg" ></span>
                                </div>
                             </div>
                             <div class="row">                       
                                 <div class="col s12 m4 l4 input-field ">
                                 	<p class="searchable_label">Location <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="location" id="location">
                                        <option value="" selected>Select</option>
                                         <%-- <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach> --%>
                                    </select>
                                    <span id="locationError" class="error-msg" ></span>                                     
                                </div>
                                 <div class="col s6 m4 l4 input-field ">
                                     <p class="searchable_label">Sub Location <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="sub_location" id="sub_location">
                                        <option value="" selected>Select</option>
                                         <%-- <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach> --%>
                                    </select>                                    
                                    <span id="sub_locationError" class="error-msg" ></span>                                     
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Type of Use <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="type_of_use" id="type_of_use">
                                        <option value="" >Select</option>
                                        <option value="residential_details" >Residential</option>
                                        <option value="commercial_details" >Commercial</option>
                                         <%-- <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach> --%>
                                    </select>
                                    <span id="type_of_useError" class="error-msg" ></span>
                                </div>
                             </div>
                            
                             <div class="row">
                                 <div class="col s6 m2 input-field">
                                    <input id="required_date" name="required_date" type="text" class="validate" value="">
                                    <label for="required_date" class="fs-sm-8rem fs-9">Carpet Area (sft)</label>
                                    <span id="required_dateError" class="error-msg" ></span>
	                             </div>
                                <div class="col s6 m2 input-field">
                                    <p class="searchable_label mb-8">Year of Construction <!-- <span class="required">*</span> --></p>
                                    <select id="construction_year" name="construction_year" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                               			<%-- <c:forEach var="obj" items="${drawingTypeList}">
                						    <option value="${obj.drawing_type_fk }" <c:if test="${designDetails.drawing_type_fk eq obj.drawing_type_fk }">selected</c:if>>${obj.drawing_type_fk}</option>
                                      	</c:forEach> --%>
                                    </select>
                                    <span id="construction_yearError" class="error-msg" ></span>
                                 </div>		                         		
                                <div class="col s6 m4 input-field">
                                    <input id="owner_name" name="owner_name" type="text" class="validate " value="">
                                    <label for="owner_name" class="fs-sm-8rem fs-9">Owner Name </label>
                                    <span id="owner_nameError" class="error-msg" ></span>
                                </div>  
                                <div class="col s6 m4 input-field">
                                    <input id="occupier_name" name="occupier_name" type="text" class="validate " value="">
                                    <label for="occupier_name" class="fs-sm-8rem fs-9">Occupier Name </label>
                                    <span id="occupier_nameError" class="error-msg" ></span>
                                </div>  
                            </div>
						</div>
                            <div class="row ">
                            	<h6 class="center-align">Identification Details</h6>
                                <div class="col s6 m2 input-field">
                                    <p class="searchable_label mb-8">Document Type <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="doc_type" id="doc_type">
                                        <option value="" selected>Select</option>
                                         <%-- <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach> --%>
                                    </select>
                                    <span id="doc_typeError" class="error-msg" ></span>
                                </div>                               
                                <div class="col s6 m2 input-field">
                                     <input id="document_no" name="document_no" type="text" class="validate" value="">
		                             <label for="document_no" >Document No </label>
		                             <span id="document_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                     <input id="verification_date" name="verification_date" type="text" class="validate datepicker" value="">
                                     <button type="button" id="verification_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="verification_date">Physical Verification Date</label>
		                             <span id="verification_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                     <p class="searchable_label mb-8">Verification By <!-- <span class="required">*</span> --></p>
                                    <select class="searchable validate-dropdown" name="verification_by" id="verification_by">
                                        <option value="" selected>Select</option>
                                         <%-- <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach> --%>
                                    </select>
                                    <span id="verification_byError" class="error-msg" ></span>
                                </div>
                               
                            </div> 
                            <div class="row">
                            	 <div class="col s6 m4 l3 input-field">
                                     <input id="approval_by_committee" name="approval_by_committee" type="text" class="validate datepicker" value="">
                                     <button type="button" id="approval_by_committee_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="approval_by_committee">Approval By Committee </label>
		                             <span id="approval_by_committeeError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l3 input-field">
                                     <input id="approval_by_mrvc" name="approval_by_mrvc" type="text" class="validate datepicker" value="">
                                     <button type="button" id="approval_by_mrvc_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="approval_by_mrvc">Approval by MRVC</label>
		                             <span id="approval_by_mrvcError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l3 input-field">
                                     <input id="estimate_approval" name="estimate_approval" type="text" class="validate datepicker" value="">
                                     <button type="button" id="estimate_approval_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="estimate_approval">Estimate Approval</label>
		                             <span id="estimate_approvalError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l3 input-field amount-dropdown">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="estimation_amount" name="estimation_amount" type="number" class="validate" value="" min="0" step="0.00001">
                                    <label for="estimation_amount">Estimation Amount</label>
                                    <span id="estimation_amountError" class="error-msg"></span>
                                	<span id="estimation_amount_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="estimation_amount_units" name="estimation_amount_units">
                                    	<option>Rs</option>
                                		<%-- <c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach> --%>
                                	</select>
                                </div> 
                            </div>
                            <div class="row">
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="letter_to_mmrda" name="letter_to_mmrda" type="text" class="validate datepicker" value="">
                                     <button type="button" id="letter_to_mmrda_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="letter_to_mmrda">Letter to MMRDA</label>
		                             <span id="letter_to_mmrdaError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field amount-dropdown">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="estimated_by_mmrda_amount" name="estimated_by_mmrda_amount" type="number" class="validate" value="" min="0" step="0.00001">
                                    <label for="estimated_by_mmrda_amount">Estimate by MMRDA</label>
                                    <span id="estimated_by_mmrda_amountError" class="error-msg"></span>
                                	<span id="estimated_by_mmrda_amount_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="estimated_by_mmrda_amount_units" name="estimated_by_mmrda_amount_units">
                                    	<option>Rs</option>
                                		<%-- <c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach> --%>
                                	</select>
                                </div> 
                                <div class="col s6 m4 l4 input-field">
                                     <input id="payment_to_mmrda" name="payment_to_mmrda" type="text" class="validate datepicker" value="">
                                     <button type="button" id="payment_to_mmrda_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="payment_to_mmrda">Payment to MMRDA</label>
		                             <span id="payment_to_mmrdaError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row"> 
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="alternate_housing_allotment" name="alternate_housing_allotment" type="text" class="validate datepicker" value="">
                                     <button type="button" id="alternate_housing_allotment_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="alternate_housing_allotment">Alternate Housing Allotment</label>
		                             <span id="alternate_housing_allotmentError" class="error-msg" ></span>
                                </div>
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="relocation" name="relocation" type="text" class="validate datepicker" value="">
                                     <button type="button" id="relocation_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="relocation">Relocation</label>
		                             <span id="relocationError" class="error-msg" ></span>
                                </div>
                            	<div class="col s6 m4 l4 input-field">
                                     <input id="encroachment_removal" name="encroachment_removal" type="text" class="validate datepicker" value="">
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
                                         <%-- <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach> --%>
                                    </select>
                                    <span id="boundary_wall_statusError" class="error-msg" ></span>
                                </div>
                            	<div class="col s12 m4 input-field">
                                     <input id="boundary_wall_doc" name="boundary_wall_doc" type="text" class="validate datepicker" value="">
                                     <button type="button" id="boundary_wall_doc_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
		                             <label for="boundary_wall_doc">Boundary Wall Doc</label>
		                             <span id="boundary_wall_docError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <p class="center-align" style="margin-top:1rem;">
									      <label>
									        <input type="checkbox" class="filled-in" checked="checked" id="handover_to_execution" name="handover_to_execution"/>
									        <span>Handover to Execution</span>
									      </label>
								    </p>
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
                                            </select>
                                            <span id="occupancy_statusError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l3 input-field">
                            				<p class="searchable_label mb-8">Gender</p>
                                            <select id="gender" name="gender" class="searchable">
                                                <option value="">Select</option>
                                                <option value="male">Male</option>
                                                <option value="female">Female</option>
                                                <option value="other">Other</option>                                                          
                                            </select>
                                            <span id="genderError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l3 input-field">
		                                    <p class="searchable_label mb-8">Tenure Status </p>
		                                    <select class="searchable validate-dropdown" name="tenure_status" id="tenure_status">
		                                        <option value="" >Select</option>
		                                         <%-- <c:forEach var="obj" items="${preparedBy }">
		                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
		                                          </c:forEach> --%>
		                                    </select>
		                                    <span id="tenure_statusError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s12 m4 l3 input-field">
		                                     <p class="center-align" style="margin-top:1rem;">
											      <label>
											        <input type="checkbox" class="filled-in" checked="checked" id="vulnerable_category" name="vulnerable_category"/>
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
                                            </select>
                                            <span id="casteError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l4 input-field">
                            				<p class="searchable_label ">Mother Tongue</p>
                                            <select id="mother_tongue" name="mother_tongue" class="searchable">
                                                <option value="">Select</option>                                                     
                                            </select>
                                            <span id="mother_tongueError" class="my-error"></span>
                            			</div>
                            			<div class="col s6 m4 l4 input-field">
		                                    <p class="searchable_label">Type of Family </p>
		                                    <select class="searchable validate-dropdown" name="type_of_family" id="type_of_family">
		                                        <option value="" >Select</option>
		                                         <%-- <c:forEach var="obj" items="${preparedBy }">
		                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
		                                          </c:forEach> --%>
		                                    </select>
		                                    <span id="type_of_familyError" class="error-msg" ></span>
		                                </div>		                                
                            		</div>
                            		<div class="row">
                            			 <div class="col s6 m4 l3 input-field">
	                                       <input id="family_size" name="family_size" type="number" class="validate" value="">
			                               <label for="family_size">Family Size </label>
			                               <span id="family_sizeError" class="error-msg" ></span>
	                                    </div>
                            			 <div class="col s6 m4 l3 input-field">
	                                       <input id="number_of_married_couple" name="number_of_married_couple" type="number" class="validate" value="">
			                               <label for="number_of_married_couple">No of Married Couple </label>
			                               <span id="number_of_married_coupleError" class="error-msg" ></span>
	                                    </div>
		                                <div class="col s6 m4 l3 input-field amount-dropdown">
		                                    <i class="material-icons amount-symbol center-align">₹</i>
		                                    <input id="family_income_amount" name="family_income_amount" type="number" class="validate" value="" min="0" step="0.00001">
		                                    <label for="family_income_amount">Family Income</label>
		                                    <span id="family_income_amountError" class="error-msg"></span>
		                                	<span id="family_income_amount_unitsError" class="error-msg right" ></span>
		                                    <select class="validate-dropdown" id="family_income_amount_units" name="family_income_amount_units">
		                                    	<option>Rs</option>
		                                    	<option value="thousands">Thousands</option>
		                                    	<option value="lacs">Lacs</option>
		                                    	<option value="crores">Crores</option>
		                                		<%-- <c:forEach var="obj" items="${unitsList }">
			                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
			                                	</c:forEach> --%>
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
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                          <c:forEach var="insurenceObj" items="${contractDeatils.insurence }" varStatus="index">  
                                            <tr id="residentDetailRow${index.count }" class="">
                                                <td data-head="Name" class="input-field">
                                                    <input id="residential_name${index.count }" name="residential_names" type="text" class="validate"  value=""
                                                        placeholder="Name">
                                                </td>
                                                <td data-head="Relation with Head" class="input-field">
                                                    <input id="residential_relation_with_head${index.count }" name="residential_relation_with_heads" type="text" class="validate"  value=""
                                                        placeholder="Relation with Head">
                                                </td>
                                                <td data-head="Age" class="input-field">
                                                    <input id="residential_age${index.count }" name="residential_ages" type="number" class="validate"  value=""
                                                        placeholder="Age">
                                                </td>    
                                                <td data-head="Gender" class="input-field">
                                                    <select id="residential_gender${index.count }" name="residential_genders" class="searchable">
                                                        <option value="">Select</option>
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>                                                          
                                                    </select>
                                                    <span id="residential_gender${index.count }Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Maritual Status" class="input-field center-align"> 
                                                	<select id="residential_maritual_status${index.count }" name="residential_maritual_statuss" class="searchable">
                                                        <option value="" >Select</option>
                                                          <%-- <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" <c:if test="${insurenceObj.insurance_type_fk eq obj.insurance_type}">selected</c:if>>${ obj.insurance_type}</option>
                                        				  </c:forEach> --%>
                                                    </select>
                                                    <span id="residential_maritual_status${index.count }Error" class="my-error"></span>
                                            	</td>     
                                            	<td data-head="Education" class="input-field">
                                                    <input id="residential_education${index.count }" name="residential_educations" type="text" class="validate"  value=""
                                                        placeholder="Education">
                                                </td>  
                                                <td data-head="Employment" class="input-field">
                                                    <input id="residential_employment${index.count }" name="residential_employments" type="text" class="validate"  value=""
                                                        placeholder="Employment">
                                                </td>                                                                                     
                                                <td data-head="Monthly Salary" class="input-field amount-dropdown">
                                               		<i class="material-icons amount-symbol cost left-align">₹</i>
                                                   	<input id="residential_salary${index.count }" name="residential_salarys" min="0.01" step="0.01" type="number" class="validate" value=""
                                                       placeholder="Salary">
                                                       <select class="validate-dropdown" id="residential_salary_units${index.count }" name="residential_salary_unitss">
				                                		<option value="">Rs</option> 
				                                		<%-- <c:forEach var="obj" items="${unitsList }">
	                                    			    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                     			  </c:forEach> --%>
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
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>
                                                    </select>
                                                    <span id="residential_gender0Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Maritual Status" class="input-field center-align"> 
                                                	<select id="residential_maritual_status0" name="residential_maritual_statuss" class="searchable">
                                                        <option value="" >Select</option>
                                                          <%-- <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" <c:if test="${insurenceObj.insurance_type_fk eq obj.insurance_type}">selected</c:if>>${ obj.insurance_type}</option>
                                        				  </c:forEach> --%>
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
				                                		<option value="">Rs</option> 
				                                		<%-- <c:forEach var="obj" items="${unitsList }">
	                                    			    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                     			  </c:forEach> --%>
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
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                    		<input type="hidden" id="residentDetailRowNo"  name="residentDetailRowNo" value="${fn:length(contractDeatils.insurence) }" />
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
		                                     <input id="name_of_activity" name="name_of_activity" type="text" class="validate" value="">
				                             <label for="name_of_activity" >Name of Activity </label>
				                             <span id="name_of_activityError" class="error-msg" ></span>
		                                 </div>
		                                 <div class="col s6 m4 l2 input-field">
		                                    <p class="searchable_label mb-8">Year of Establishment</p>
		                                    <select class="searchable validate-dropdown" name="year_of_establishment" id="year_of_establishment">
		                                        <option value="" selected>Select</option>
		                                         <%-- <c:forEach var="obj" items="${preparedBy }">
		                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
		                                          </c:forEach> --%>
		                                    </select>
		                                    <span id="year_of_establishmentError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m4 l2 input-field">
	                                       <input id="carpet_area" name="carpet_area" type="number" class="validate" value="">
			                               <label for="carpet_area">Carpet Area (in sqft) </label>
			                               <span id="carpet_areaError" class="error-msg" ></span>
	                                    </div>
		                                <div class="col s6 m4 l3 input-field amount-dropdown">
		                                    <i class="material-icons amount-symbol center-align">₹</i>
		                                    <input id="monthly_turnover_amount" name="monthly_turnover_amount" type="number" class="validate" value="" min="0" step="0.00001">
		                                    <label for="monthly_turnover_amount">Monthly Turnover</label>
		                                    <span id="monthly_turnover_amountError" class="error-msg"></span>
		                                	<span id="monthly_turnover_amount_unitsError" class="error-msg right" ></span>
		                                    <select class="validate-dropdown" id="monthly_turnover_amount_units" name="monthly_turnover_amount_units">
		                                    	<option>Rs</option>
		                                		<%-- <c:forEach var="obj" items="${unitsList }">
			                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
			                                	</c:forEach> --%>
		                                	</select>
		                                </div>
	                                   <div class="col s6 m4 l3 input-field">
	                                      <input id="number_of_employees" name="number_of_employees" type="number" class="validate" value="">
			                              <label for="number_of_employees">Number of Employees </label>
			                              <span id="number_of_employeesError" class="error-msg" ></span>
	                                   </div>
                            		</div>                            		 
                            		<div class="row">
                            			<div class="col s12 m12 input-field">
		                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000"></textarea>
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
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                          <c:forEach var="insurenceObj" items="${contractDeatils.insurence }" varStatus="index">  
                                            <tr id="employeeDetailRow${index.count }" class="">
                                                <td data-head="Name" class="input-field">
                                                    <input id="employee_name${index.count }" name="employee_names" type="text" class="validate"  value=""
                                                        placeholder="Name">
                                                </td>
                                                <td data-head="Age" class="input-field">
                                                    <input id="employee_age${index.count }" name="employee_ages" type="number" class="validate"  value=""
                                                        placeholder="Age">
                                                </td>    
                                                <td data-head="Gender" class="input-field">
                                                    <select id="employee_gender${index.count }" name="employee_genders" class="searchable">
                                                        <option value="">Select</option>
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>
                                                    </select>
                                                    <span id="employee_gender${index.count }Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Literate" class="input-field center-align"> 
                                                	<p>
                                                		<label> 
                                                			<input type="hidden" id="employee_literacys${index.count }" name="employee_literacyss" value="" />
                                                			<input type="checkbox" id="employee_literacy${index.count }" > <span></span> 
                                                		</label>	
                                                	</p>
                                            	</td>     
                                            	<td data-head="Class Attended" class="input-field">
                                                    <input id="employee_attended${index.count }" name="employee_attendeds" type="text" class="validate"  value=""
                                                        placeholder="Class Attended">
                                                </td>  
                                                <td data-head="Travel Time" class="input-field">
                                                    <input id="employee_travel_time${index.count }" name="employee_travel_times" type="number" class="validate"  value=""
                                                        placeholder="Travel Time">
                                                </td>                                                                                     
                                                <td data-head="Monthly Salary" class="input-field amount-dropdown">
                                               		<i class="material-icons amount-symbol cost left-align">₹</i>
                                                   	<input id="employee_salary${index.count }" name="employee_salarys" min="0.01" step="0.01" type="number" class="validate" value=""
                                                       placeholder="Salary">
                                                       <select class="validate-dropdown" id="employee_salary_units${index.count }" name="employee_salary_unitss">
				                                		<option value="">Rs</option> 
				                                		<%-- <c:forEach var="obj" items="${unitsList }">
	                                    			    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                     			  </c:forEach> --%>
				                                	</select>
				                                	<span id="employee_salary_units${index.count }Error" class="my-error my-error"></span>
                                                 </td> 
                                                 <td data-head="Nature of Work" class="input-field">
                                                    <input id="employee_nature_of_work${index.count }" name="employee_nature_of_works" type="text" class="validate"  value=""
                                                        placeholder="Nature of Work">
                                                 </td>
                                            
	                                            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">    
	                                                <td class="mobile_btn_close">
	                                                    <a onclick="removeEmployeeRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
                                                </c:if>
                                            </tr>
                                            
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
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>
                                                    </select>
                                                    <span id="employee_gender0Error" class="my-error"></span>
                                                </td>
                                                <td data-head="Literate" class="input-field center-align"> 
                                                	<p>
                                                		<label> 
                                                			<input type="hidden" id="employee_literacys0" name="employee_literacyss" value="" />
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
				                                		<option value="">Rs</option> 
				                                		<%-- <c:forEach var="obj" items="${unitsList }">
	                                    			    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                     			  </c:forEach> --%>
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
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                    		<input type="hidden" id="employeeDetailRowNo"  name="employeeDetailRowNo" value="${fn:length(contractDeatils.insurence) }" />
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
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${designDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom:20px;">
                                <div class="col s6 offset-m2 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateDesign();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addDesign();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/design" class="btn waves-effect waves-light bg-s">Cancel</a>
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
    		if($('#type_of_use').val()=='residential_details'){
    			$('#residential_details').show();
    			$('#commercial_details').hide();    			
    		}
    		if($('#type_of_use').val()=='commercial_details'){
    			$('#commercial_details').show();
    			$('#residential_details').hide();    			
    		}
    	});
    	$('#family_size,#family_income_amount,#family_income_amount_units').change(function(){
    		var family_size = Number($('#family_size').val());
    		var family_income = Number($('#family_income_amount').val());
    		var income_units = $('#family_income_amount_units').val();
    		var percapita_income = Math.round((family_income / family_size) * 100)/100
    		$('#percapita_per_month').text(	percapita_income+' '+income_units	);
    	});
   });
   function addEmployeeDetailRow(){
	    var rowNo = $("#employeeDetailRowNo").val();
	    var rNo = Number(rowNo)+1;
	    var html = '<tr id="employeeDetailRow'+rNo+'"> <td data-head="Name" class="input-field"> <input id="employee_name'+rNo+'" name="employee_names" type="text" class="validate"'
	    		  +' value="" placeholder="Name"> </td> <td data-head="Age" class="input-field"> <input id="employee_age'+rNo+'" name="employee_ages" type="number" class="validate" '
	    		  +' value="" placeholder="Age"> </td> <td data-head="Gender" class="input-field"> <select id="employee_gender'+rNo+'" name="employee_genders" class="searchable">'
	    		  +' <option value="">Select</option> <option value="male">Male</option> <option value="female">Female</option> <option value="other">Other</option>'
        		  +'</select> <span id="employee_gender'+rNo+'Error" class="my-error"></span> </td>'
    			  +'<td data-head="Literate" class="input-field center-align"><p><label> <input type="hidden" id="employee_literacys'+rNo+'" name="employee_literacyss" value="" />'
    			  +'<input type="checkbox" id="employee_literacy'+rNo+'" > <span></span> </label> </p></td> '
    			  +'<td data-head="Class Attended" class="input-field"> <input id="employee_attended'+rNo+'" name="employee_attendeds" type="text" class="validate"  value="" placeholder="Class Attended"> </td>'
    			  +'<td data-head="Travel Time" class="input-field"> <input id="employee_travel_time'+rNo+'" name="employee_travel_times" type="number" class="validate"  value="" placeholder="Travel Time"> </td>'
    			  +'<td data-head="Monthly Salary" class="input-field amount-dropdown"> <i class="material-icons amount-symbol cost left-align">₹</i> <input id="employee_salary'+rNo+'" name="employee_salarys" min="0.01"'
    			  +' step="0.01" type="number" class="validate" value="" placeholder="Salary"> <select class="validate-dropdown" id="employee_salary_units'+rNo+'" name="employee_salary_unitss"> <option value="">Rs</option>'
		    		<%-- <c:forEach var="obj" items="${unitsList }">
				    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
					</c:forEach> --%>
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
	    		   +'<td data-head="Gender" class="input-field"> <select id="residential_gender'+rNo+'" name="residential_genders" class="searchable"> <option value="">Select</option> <option value="male">Male</option> <option value="female">Female</option> '
	    		   +'<option value="other">Other</option> </select> <span id="residential_gender'+rNo+'Error" class="my-error"></span> </td>'
	    		   +'<td data-head="Maritual Status" class="input-field center-align"> <select id="residential_maritual_status'+rNo+'" name="residential_maritual_statuss" class="searchable"> <option value="" >Select</option>'
	              <%-- <c:forEach var="obj" items="${insurance_type }">
					   <option value= "${ obj.insurance_type}" <c:if test="${insurenceObj.insurance_type_fk eq obj.insurance_type}">selected</c:if>>${ obj.insurance_type}</option>
				  </c:forEach> --%>
        		  +'</select> <span id="residential_maritual_status'+rNo+'Error" class="my-error"></span> </td>     '
        		  +'<td data-head="Education" class="input-field"> <input id="residential_education'+rNo+'" name="residential_educations" type="text" class="validate"  value="" placeholder="Education"> </td>  '
        		  +'<td data-head="Employment" class="input-field"> <input id="residential_employment'+rNo+'" name="residential_employments" type="text" class="validate"  value="" placeholder="Employment"> </td>  '
        		  +'<td data-head="Monthly Salary" class="input-field amount-dropdown">	<i class="material-icons amount-symbol cost left-align">₹</i> <input id="residential_salary'+rNo+'" name="residential_salarys" min="0.01" step="0.01" type="number" '
        		  +'class="validate" value="" placeholder="Salary"> <select class="validate-dropdown" id="residential_salary_units'+rNo+'" name="residential_salary_unitss"> <option value="">Rs</option> '
	    		<%-- <c:forEach var="obj" items="${unitsList }">
			    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
				  </c:forEach> --%>
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
   </script>

</body>
</html>