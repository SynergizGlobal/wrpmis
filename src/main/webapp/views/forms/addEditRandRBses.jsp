<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
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
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" />
	
    <style>
       	.cf .character-counter{
       		position: relative;
    		right: 1.5em;
       	}
       	.cf2 .character-counter{
       		position: relative;
    		right: 1.5em;
    		top: 4em;
       	}
       	.cf3 .character-counter{
       		position: relative;
    		right: 1.5em;
    		top: 0em;
       	}
        .mdl-data-table{
        	border:1px solid #ccc;	
        }
  		.error-msg label{color:red!important;}   
		
		span.required {
		    font-size: inherit;
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
      
        .error-msg label{color:red!important;}
        /*table with fixed header & height start */
		.max-h{
			max-height:400px;
			height:auto;
			overflow:auto;			
		}	
		.max-h tr{
			position:relative;
		}
		.max-h thead th{
			position:sticky;
			top:0;
			z-index:1;
		}
		/*table with fixed header & height ends */
        #userPermissionsTableBody .select2-container{
        	max-width:290px;
        	text-align:left;
        }
        .mdl-data-table {
        	border:1px solid #ccc;
        }
          .input-field .searchable_label{
        	font-size:0.85rem;
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
.input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
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

        .fw-8p {
            width: 8%;
        }

        #dashboard_form_table td>.btn {
            padding: 0 12px;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }
        .input-field>label{
        	font-size: .9rem !important;
        	line-height: 16px;
        }
        .w20em{width: 20em;}
        .w60{
            width: 60px;
        }
        .w7em{width: 7em;}
        .bd-none{border:none !important;background: transparent}
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.3em;
   					 margin-left: 9%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:17%;}
    	}
    	@media(max-width: 912px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	.per-box-list li {
			    width: 49.5%;
			    margin: 10px 0;
			}
		.mobile_responsible_table>tbody tr td.mobile_btn_close a {
		    float: right;
		    margin-right: -25px;
		    margin-top: 1em;
		}
		.no-bd{
			border: 0 !important;
		}
		.mdl-data-table td, .mdl-data-table th {
		    text-align: center;
		}
		.mobile_responsible_table tbody tr td .select2-container{
			width: 90% !important;
		}
    	}
		 @media only screen and (max-width:820px) {          
			.input-field input[type="email"]{
				box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
				width: -webkit-fill-available;
			    background-color: transparent;
			    padding-left: 10px;
			}  			
		} 
		 @media only screen and (max-width:601px) {     
			.col.s12.max-h{
				width: 110%;
			}
		}
		
		 .select2-container--default .select2-selection--multiple .select2-selection__choice__display{
			white-space: pre-wrap;
			word-break: break-word;
		}
		.select2-container--default .select2-selection--multiple .select2-selection__choice{
			display: inherit;
		}
		/*.select2-selection__rendered li{
			display: block;
			float: left;
		} */
			#app_com_table {
		counter-reset: serial-number;  /* Set the serial number counter to 0 */
		}
		#app_com_table td:first-child:before {
		counter-increment: serial-number;  /* Increment the serial number counter */
		content: counter(serial-number);  /* Display the counter */
		}
		#app_com_table .datepicker-table td:first-child:before {
		    content: none !important;
		}
		@media(max-width: 575px){
			.per-box-list li {
			    width: 100%;
			    margin: 10px 0;
			}
			.mobile_responsible_table tbody tr td .select2-container{
				width: 100% !important;
			}
		}
    </style>
</head>
<body>
 <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
                  
       <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>R&R Agency</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">	
			                	<form action="<%=request.getContextPath() %>/update-rr-bses" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-rr-bses" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
						  
                        <div class="container container-no-margin">
                            <div class="row">  
                            <input type="hidden" name="rr_id" value="${rrDetails.rrbses_id }"/>
                                <h5 class="center-align" style="margin-bottom: 40px;">BSES Consultant Details</h5>
                                <div class="col s12 m4 input-field">
                                <c:if test="${action eq 'add'}">	

                                    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select id="work_id_fk" class="searchable validate-dropdown" name="work_id_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id_fk}" <c:if test="${rrDetails.work_id_fk eq obj.work_id_fk}">selected</c:if>>${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> 
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
	                           </c:if>
				              <c:if test="${action eq 'edit'}">				                
	                                     <input type="text" class="validate w80 pdr4em" value="${rrDetails.work_short_name } - ${rrDetails.work_id_fk}" readonly>
							  </c:if>
						  
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> MRVC HOD <span class="required">*</span></p>
                                    <select id="hod" class="searchable validate-dropdown" name="hod" onchange="getResponsible(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${hodList }">
                                      	   <option value= "${ obj.hod}" <c:if test="${rrDetails.hod eq obj.hod}">selected</c:if>>${obj.designation}<c:if test="${not empty obj.user_name}"> - </c:if> ${obj.user_name }</option>
                                         </c:forEach> 
                                    </select>
                                     <span id="hodError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> MRVC Responsible Person</p>
                                    <select id="mrvc_responsible_person" class="searchable validate-dropdown
                                    " name="mrvc_responsible_person">
                                        <option value="">Select</option>
                                    </select>
                                     <span id="mrvc_responsible_personError" class="error-msg" ></span>
                                </div>  
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                     <input id="bses_agency_name" maxlength="100" data-length="100" name="bses_agency_name" type="text" class="validate w80 pdr4em" value="${rrDetails.bses_agency_name }">
                                     <label for="bses_agency_name">BSES Agency Name<span class="required">*</span></label>
                                     <span id="bses_agency_nameError" class="error-msg" ></span>
                                 </div> 
                                 <div class="col s6 m4 l4 input-field">
                                     <input id="res_person_agency" maxlength="50" data-length="50" name="agency_responsible_person" type="text" class="validate w80 pdr4em" value="${rrDetails.agency_responsible_person }">
                                     <label for="res_person_agency">Responsible Person From Agency</label>
                                     <span id="res_person_agencyError" class="error-msg" ></span>
                                 </div> 
                                
                                <div class="col s12 m4 input-field">
                                    <input id="contact_number" maxlength="10" data-length="10" name="contact_number" type="number" value="${rrDetails.contact_number }" class="validate num w80 pdr4em">
                                    <label for="contact_number">Contact Number </label>
                                    <span id="contact_numberError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                     <input id="bses_email" maxlength="100" data-length="100" name="bses_email" type="email" class="validate w80 pdr4em" value="${rrDetails.bses_email }">
                                     <label for="bses_email">Email ID</label>
                                     <span id="bses_emailError" class="error-msg" ></span>
                                 </div> 
                                 <div class="col s6 m4 input-field">
                                     <input id="submission_date_report_ca" name="submission_date_report_ca" type="text" class="validate datepicker" value="${rrDetails.submission_date_report_ca }">
                                     <button type="button" id="submission_date_report_ca_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                     <label for="submission_date_report_ca">Submission date of report as per CA <!-- <span class="required" id="verification_date_req"></span> --></label>
                                     <span id="submission_date_report_caError" class="error-msg" ></span>
                                </div> 
                                
                                <div class="col s6 m4 input-field">
                                     <input id="actual_date_report_mrvc" name="actual_submission_date_bses_report_to_mrvc" type="text" class="validate datepicker" value="${rrDetails.actual_submission_date_bses_report_to_mrvc }">
                                     <button type="button" id="actual_date_report_mrvc_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                     <label for="actual_date_report_mrvc">Actual Submission date of BSES report to MRVC <span class="required">*</span></label>
                                     <span id="actual_date_report_mrvcError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <h5 class="center-align">MRVC Verification</h5>
                                 <div style='margin-top: 20px;'>
                                    <div class="row">
                                        <div class="col s6 m4 l4 input-field">
                                             <input id="submission_date_report_mrvc" name="report_submission_date_to_mrvc" type="text" class="validate datepicker" value="${rrDetails.report_submission_date_to_mrvc }">
                                             <button type="button" id="submission_date_report_mrvc_icon" class="datepicker-button"><i
                                                    class="fa fa-calendar"></i></button>
                                             <label for="submission_date_report_mrvc">Report Submission Date to MRVC <span class="required">*</span></label>
                                             <span id="submission_date_report_mrvcError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s6 m4 l4 input-field">
											<div data-head="Attachment" class="input-field center-align">
												<span class="normal-btn">
													<input type="file" id="rragencyFiles" name="rragencyFiles" style="display:none" onchange="getFileName()">
														<label for="rragencyFiles" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
                                               <c:choose>
		                                        <c:when test="${not empty rrDetails}">														
														<a href="<%=CommonConstants.RR_AGENCY_FILES %>${rrDetails.attachment_file}" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
												 </c:when>
											 </c:choose>														<input type="hidden" id="rrlaDocumentFileNames" name="rrlaDocumentFileNames">
														<span id="rrlaDocumentFileName" class="filevalue"></span></span><span id="laFiles1Error" class="error-msg"></span></div>

											
                                        </div>
		                                <div class="col s6 m4 l4 input-field" id='show-me' style='margin-top: 20px;'>
		                                             <input id="approval_date_mrvc" name="approval_date_by_mrvc" type="text" class="validate datepicker" value="${rrDetails.approval_date_by_mrvc }">
		                                             <button type="button" id="approval_date_mrvc_icon" class="datepicker-button"><i
		                                                    class="fa fa-calendar"></i></button>
		                                             <label for="approval_date_mrvc">Approval Date by MRVC <span class="required">*</span></label>
		                                             <span id="approval_date_mrvcError" class="error-msg" ></span>
		                                </div>                                         
                                    </div>
                                </div>                               
                            </div>
                            </div>

                            <div class="container container-no-margin">
                             <div class="row exe-box w100" style="margin-bottom: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <h5 class="center-align marob">Appointment of Committee</h5>
                                        <div class="table-inside">
                                            <table id="app_com_table" class="mdl-data-table mobile_responsible_table auto-index">
                                                <thead>
                                                    <tr>
                                                        <th class="w60">S No </th>
                                                        <th class="w20em">Date of Appointment </th>
                                                        <th class="w20em">Committee Name </th>
                                                        <th class="w20em">Name of Representative </th>
                                                        <th class="w20em">Phone No </th>
                                                        <th class="w20em">Email ID </th>
                                                        <th class="w60">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
                                               
                                               <c:choose>
		                                        <c:when test="${not empty rrDetails.rrBSESLIst && fn:length(rrDetails.rrBSESLIst) gt 0 }">
		                                         <input type="hidden" id="sNo" value="${fn:length(rDetails.rrBSESLIst) }">
		                                		  <c:forEach var="rrObj" items="${rrDetails.rrBSESLIst }" varStatus="index">   
                                                       <tr id="actionStRow${index.count }">
                                                        <td>&nbsp;</td>
                                                        <td data-head="Date of Appointment" class="input-field">
                                                            <input id="appointment_date_committee${index.count }" placeholder="Date" name="date_of_appointments" type="text" class="validate datepicker" value="${rrObj.date_of_appointment }">
                                                                 <button type="button" id="appointment_date_committee_icon" class="datepicker-button"><i
                                                                        class="fa fa-calendar"></i></button>
                                                                 <span id="appointment_date_committee${index.count }Error" class="error-msg" ></span>
                                                        </td>
                                                        <td data-head="Committee Name${index.count }" class="input-field">
                                                        <input id="committee_name" maxlength="50" placeholder="Committee Name" data-length="50" name="committee_names" type="text" class="validate w70 pdr4em" value="${rrObj.committee_name }">
                                                         <span id="committee_name${index.count }Error" class="error-msg" ></span>
                                                        </td>                                                        
                                                        <td data-head="Name of Representative${index.count }" class="input-field">
                                                        <input id="name_representative" maxlength="50" placeholder="Name" data-length="50" name="name_of_representatives" type="text" class="validate w70 pdr4em" value="${rrObj.name_of_representative }">
                                                         <span id="name_representative${index.count }Error" class="error-msg" ></span>
                                                        </td>
                                                        <td data-head="Contact Number" class="input-field">
                                                            <input id="contact_number_rep${index.count }" placeholder="Number" maxlength="10" data-length="10" name="phone_nos" type="number" value="${rrObj.phone_no }" class="validate num w70 pdr4em">
                                                            <span id="contact_number_rep${index.count }Error" class="error-msg"></span>
                                                        </td>
                                                        <td data-head="Email" class="input-field">
                                                        <input id="rep_email${index.count }" maxlength="50" placeholder="Email" data-length="50" name="email_ids" type="email" class="validate w70 pdr4em" value="${rrObj.email_id }">
                                                         <span id="rep_email${index.count }Error" class="error-msg" ></span>
                                                        </td>
                                                        <td class="input-field mobile_btn_close">
                                                            <a onclick="removeStActions('${index.count }');" class="btn waves-effect waves-light red t-c remove">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
	                                             </c:forEach>
	                                           </c:when>
                                             <c:otherwise>
                                              <input type="hidden" id="sNo" value="1">
                                              <tr id="actionStRow0">
                                                        <td>&nbsp;</td>
                                                        <td data-head="Date of Appointment" class="input-field">
                                                            <input id="appointment_date_committee0" name="date_of_appointments" type="text" placeholder="Date" class="validate datepicker" value="">
                                                                 <button type="button" id="appointment_date_committee_icon" class="datepicker-button"><i
                                                                        class="fa fa-calendar"></i></button>
                                                                 <span id="appointment_date_committeeError" class="error-msg" ></span>
                                                        </td>
                                                        <td data-head="Committee Name0" class="input-field">
                                                        <input id="committee_name" maxlength="50" placeholder="Committee Name" data-length="50" name="committee_names" type="text" class="validate w70 pdr4em" value="">
                                                         <span id="committee_nameError" class="error-msg" ></span>
                                                        </td>                                                            
                                                        <td data-head="Name of Representative0" class="input-field">
                                                        <input id="name_representative" placeholder="Name" maxlength="50" data-length="50" name="name_of_representatives" type="text" class="validate w70 pdr4em" value="">
                                                         <span id="name_representativeError" class="error-msg" ></span>
                                                        </td>
                                                        <td data-head="Contact Number" class="input-field">
                                                            <input id="contact_number_rep0" placeholder="Number" maxlength="10" data-length="10" name="phone_nos" type="number" value="" class="validate num w70 pdr4em">
                                                            <span id="contact_number_repError" class="error-msg"></span>
                                                        </td>
                                                        <td data-head="Email" class="input-field">
                                                        <input id="rep_email0" placeholder="Email" maxlength="50" data-length="50" name="email_ids" type="email" class="validate w70 pdr4em" value="">
                                                         <span id="rep_emailError" class="error-msg" ></span>
                                                        </td>
                                                        <td class="input-field mobile_btn_close">
                                                            <a onclick="removeStActions('0');" class="btn waves-effect waves-light red t-c remove">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                             </c:otherwise>
                                            </c:choose>
                                                </tbody>
                                            </table>
                                            <table class="mdl-data-table table-add bd-none">
                                                <tbody class="bd-none">
                                                    <tr class="bd-none">
                                                        <td colspan="3" class="bd-none"><a
                                                            type="button"
                                                            class="btn waves-effect waves-light bg-m t-c add-align"
                                                            onclick="addStRow()"> <i
                                                                class="fa fa-plus"></i>
                                                        </a>
                                                    </tr>
                                                </tbody>
                                            </table>
                                      <c:choose>
                                        <c:when test="${not empty rDetails.rrBSESLIst && fn:length(rDetails.rrBSESLIst) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(rDetails.rrBSESLIst) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>
                                            

                                        </div>
                                    </div>
                                </div>
                             </div>
                      
                    </div>
                            <!-- </div> -->
                            <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s12 m6 l6 mt-brdr ">
                                      <c:if test="${action eq 'edit'}">
		                                      <div class="center-align m-1">
		                                          <button type="button" onclick="updateRR();" class="btn waves-effect waves-light bg-m">Update</button>
		                                    </div>	
		                                       
		                                    </c:if>
		                                    <c:if test="${action eq 'add'}">
			                                    <div class="center-align m-1">
			                                          <button type="button" id="disabled" onclick="addRR();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
			                                    </div>
		                                        
		                                    </c:if>
                                </div>
                                <div class="col s12 m6 l6 mt-brdr ">
                                    <div class="center-align m-1">
                                          <a href="<%=request.getContextPath() %>/rr-bses" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </form>
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
   $(document).ready(function() {
	   $("[data-length]").each(function(i,val){
	    	$('#'+val.id).characterCounter();
	    });
       $(".num").keypress(function() {
           if ($(this).val().length == $(this).attr("maxlength")) {
               return false;
           }
       });
       

       getResponsible('${rrDetails.hod}');
   });
	 
	 
   $(document).ready(function () {
       $('select:not(.searchable)').formSelect();
       $( ".searchable" ).each(function( index,val ) {
$( this ).select2({                placeholder:      $( this ).attr('placeholder')       });
});
      // $('').select2({                placeholder            });
   });


function getFileName(){
	var filename = $('#rragencyFiles')[0].files[0].name;
    $('#rrlaDocumentFileName').html(filename);
    $('#rrlaDocumentFileNames').val(filename);
}

function getResponsible(hod){
   	$(".page-loader").show();
    $("#mrvc_responsible_person option:not(:first)").remove();
    if ($.trim(hod) != "") {
        var myParams = { hod: hod };
        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getResponsibleListInRRBSES",
            data: myParams, cache: false,
            success: function (data) {
                if (data.length > 0) {
                    $.each(data, function (i, val) {
                        var workName = '';
                        if ($.trim(val.user_name) != '') { workName = ' - ' + $.trim(val.user_name) }
                        var user_id = "${rrDetails.mrvc_responsible_person}";
                        if ($.trim(user_id) != '' && val.user_id == $.trim(user_id)) {
                            $("#mrvc_responsible_person").append('<option  value="' + val.user_id + '" selected>' + $.trim(val.designation) + $.trim(workName) + '</option>');
                        } else {
                            $("#mrvc_responsible_person").append('<option  value="' + val.user_id + '">' + $.trim(val.designation) + $.trim(workName) + '</option>');
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
  function addStRow(){
       var rowNo = $("#rowNo").val();
       var rowNo1 = $("#sNo").val();
       var rNo = Number(rowNo)+1;
       var sNo = Number(rowNo1)+1;
       var html = '<tr id="actionStRow' + rNo + '"><td></td>'

          +'<td data-head="Date of Appointment" class="input-field">'
          +'<input id="appointment_date_committee' + rNo + '" type="text" placeholder="Date" class="validate datepicker" name="date_of_appointments" value="">'              
          +'<button type="button" id="appointment_date_committee_icon' + rNo + '" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
          +'<span id="appointment_date_committee' + rNo + 'Error" class="error-msg"></span></td>'
          
          +'<td data-head="Committee Name" class="input-field">'
          +'<input type="text" placeholder="Committee Name"  maxlength="50" data-length="50" id="committee_name' + rNo + '" class="validate w70 pdr4em"  name="committee_names" value="">' 
          +'<span id="committee_name' + rNo + 'Error" class="error-msg"></span> </td>'          

          +'<td data-head="Name Of Representative" class="input-field">'
          +'<input type="text" placeholder="Name"  maxlength="50" data-length="50" id="name_representative' + rNo + '" class="validate w70 pdr4em"  name="name_of_representatives" value="">' 
          +'<span id="name_of_representative' + rNo + 'Error" class="error-msg"></span> </td>'

          +'<td data-head="Contact Number" class="input-field">'
          +'<input type="number" placeholder="Number" maxlength="10" data-length="10" id="contact_number_rep' + rNo + '" class="validate w70 pdr4em num"  name="phone_nos" value="">' 
          +'<span id="contact_number_rep' + rNo + 'Error" class="error-msg"></span> </td>'

          +'<td data-head="Email" class="input-field">'
          +'<input type="email" placeholder="Email"  maxlength="50" data-length="50" id="rep_email' + rNo + '" class="validate w70 pdr4em"  name="email_ids" value="">' 
          +'<span id="rep_email' + rNo + 'Error" class="error-msg"></span> </td>'

          +'<td class="input-field mobile_btn_close"><a onclick="removeStActions(' + rNo + ');" class="btn waves-effect waves-light red t-c remove"><i class="fa fa-close"></i></a></td>'
          +'</tr>';
   
       $('#stBody').append(html);
       $("#rowNo").val(rNo);   
       var rowCount = $("#stBody tr").length;
       $("#sNo").val(rowCount);    
       $('#name_representative'+rNo).characterCounter();
		 $('#contact_number_rep'+rNo).characterCounter();
		 $('#rep_email'+rNo).characterCounter();
		 $('#contact_number_rep'+rNo).keypress(function() {
	           if ($(this).val().length == $(this).attr("maxlength")) {
	               return false;
	           }
	       });
       $('.searchable').select2();
   }
	$(function(){
		
		$(document).on('click', '.remove', function() {
			var trIndex = $(this).closest("tr").index();
				if(trIndex>0) {
				$(this).closest("tr").remove();
			} else {
				
			}
		});
	}); 
   

	   function addRR(){
	   	 if(validator.form()){ // validation perform
	       	    $('form input[name=date_of_appointments]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	 			$('form input[name=name_of_representatives]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	 			$('form input[name=phone_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	 			$('form input[name=email_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	 			var rowLr=document.getElementById("app_com_table").rows.length;
	 			for(var k=0;k<rowLr-1;k++)
	 			{
	 				if($("#contact_number_rep"+k).val()!="")
	 				{
			 				if($("#contact_number_rep"+k).val().length<10)
			 				{
			 					alert("Phone number length should be 10");
			 					$("#contact_number_rep"+k+"Error").html("Phone number length should be 10");
			 					return false;
			 				}
			 				else
			 				{
			 					$("#contact_number_rep"+k+"Error").html("");
			 				}
			 		}
	 			}
	 			$(".page-loader").show();
	  			document.getElementById("RandRForm").submit();			
		 	 }
	   }
	   function updateRR(){
	   	if(validator.form()){ // validation perform
	       	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	        $('form input[name=date_of_appointments]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=name_of_representatives]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=phone_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			$('form input[name=email_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 			var rowLr=document.getElementById("app_com_table").rows.length;
 			for(var k=0;k<rowLr-1;k++)
 			{
 				if($("#contact_number_rep"+k).val()!="")
 				{
		 				if($("#contact_number_rep"+k).val().length<10)
		 				{
		 					alert("Phone number length should be 10");
		 					$("#contact_number_rep"+k+"Error").html("Phone number length should be 10");
		 					return false;
		 				}
		 				else
		 				{
		 					$("#contact_number_rep"+k+"Error").html("");
		 				}
		 		}
 			}
	       	$(".page-loader").show();	
	  		document.getElementById("RandRForm").submit();	
	   	}
	   }
     

	   var validator =	$('#RandRForm').validate({
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	 		    rules: {
	 		 		   "work_id_fk": {
	 			 		  required: true
	 			 	  },"hod": {
	 			 		  required: true
	 			 	  },"bses_agency_name"	:{
	 			 		  required:true
	 			 	  },"actual_submission_date_bses_report_to_mrvc": {
	 			 		  required: true
	 			 	  },"report_submission_date_to_mrvc": {
	 			 		  required: true
	 			 	  }	,"approval_date_by_mrvc"	:{
	 			 		  required:true
	 			 	  }
	 		 	},
	 		    messages: {
	 		 		   "work_id_fk": {
	 			 		  required: 'Required'
	 			 	  },"hod": {
	 			 		  required: 'Required'
	 			 	  },"bses_agency_name"	:{
	 			 		  required:'Required'
	 			 	  },"actual_submission_date_bses_report_to_mrvc": {
	 			 		  required: 'Required'
	 			 	  },"report_submission_date_to_mrvc": {
	 			 		  required: 'Required'
	 			 	  }	,"approval_date_by_mrvc"	:{
	 			 		  required:'Required'
	 			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "work_id_fk" ){
					     document.getElementById("work_id_fkError").innerHTML="";
				 	     error.appendTo('#work_id_fkError');
					 }else if(element.attr("id") == "hod" ){
					     document.getElementById("hodError").innerHTML="";
				 	     error.appendTo('#hodError');
					 }else if(element.attr("id") == "bses_agency_name" ){
					     document.getElementById("bses_agency_nameError").innerHTML="";
				 	     error.appendTo('#bses_agency_nameError');
					 }else if(element.attr("id") == "actual_submission_date_bses_report_to_mrvc" ){
					     document.getElementById("actual_date_report_mrvcError").innerHTML="";
				 	     error.appendTo('#actual_date_report_mrvcError');
					 }else if(element.attr("id") == "report_submission_date_to_mrvc" ){
					     document.getElementById("submission_date_report_mrvcError").innerHTML="";
				 	     error.appendTo('#submission_date_report_mrvcError');
					 }else if(element.attr("id") == "approval_date_by_mrvc" ){
					     document.getElementById("approval_date_mrvcError").innerHTML="";
				 	     error.appendTo('#approval_date_mrvcError');
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
   </script>

</body>
</html>