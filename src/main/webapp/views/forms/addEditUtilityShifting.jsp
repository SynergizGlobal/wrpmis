<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utility Shifting - Update Forms - PMIS</title>
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
		h6{
			margin:1rem 0;
			font-weight:600;
		}
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
		.filevalue {
            display: block;
            margin-top: 10px;
        }
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			 .filevalue {
			    width: 200%;
			    white-space: break-spaces;
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
				.normal-btn{
			        display: block;
			        text-align: center;
			    }  
			    td[data-head="Attachment"] {
				    max-width: 30%;
				    width: 30% !important;
				}
				td[data-head="Progress Date"] {
				    max-width: 24%;
				    width: 24% !important;
				}
				.mdl-data-table .datepicker{
					margin-bottom:0 !important;
				}
				.mdl-data-table .datepicker~button{
					bottom:1rem;
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
		@media(max-width: 575px){
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
	  		  vertical-align: top;
			}
		}
		@media only screen and (max-width: 768px){
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
			    vertical-align: top;
			}
		}
		.w65{
			width: 62% !important;
		}
    </style>
</head>
<body>
 <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
                  
       <div class="row">
        <div class="col s12 m12" style="margin-bottom:3rem;">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                               	    <c:if test="${action eq 'edit'}">Update Utility Shifting (${utilityShifting.utility_shifting_id })</c:if>
									<c:if test="${action eq 'add'}"> Add Utility Shifting</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
                        <c:if test="${action eq 'edit'}">	
                       		 <c:if test="${sessionScope.USER_ID eq utilityShifting.executive_user_id_fk or sessionScope.USER_ROLE_NAME eq 'IT Admin'}">
			                	<form action="<%=request.getContextPath() %>/updateUtilityShifting" id="utilityshiftingform" name="utilityshiftingform" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                            </c:if>				                
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/addUtilityShifting" id="utilityshiftingform" name="utilityshiftingform" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
						  						  
						    <div class="container container-no-margin">
                            <div class="row" style="margin-top:1rem;">
                                <div class="col s12 m3 l3 input-field">
                                <p class="searchable_label"> Project </p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id_fk }" <c:if test="${obj.project_id_fk eq utilityShifting.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>                                   
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                 <input type="hidden" id= "work_code" name= "work_code"/>
                                <div class="col s12 m3 l3 input-field">
                                <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getImpactedContractsList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option name="${ obj.work_code}" value= "${ obj.work_id_fk}" <c:if test="${obj.work_id_fk eq utilityShifting.work_id_fk}">selected</c:if>>${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <%-- <div class="col s12 m4 l4 input-field">
                                	<p class="searchable_label"> Contract </p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option  workId="${obj.work_id_fk }" hod_user_id="${obj.hod_user_id_fk }" value= "${ obj.contract_id_fk}" <c:if test="${obj.contract_id_fk eq utilityShifting.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col s12 m3 l3 input-field">
                                    <p class="searchable_label"> Execution Agency <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="execution_agency_fk" name="execution_agency_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${utilityExecutionAgencyList }">
                                      	   <option value= "${ obj.execution_agency_fk}" <c:if test="${obj.execution_agency_fk eq utilityShifting.execution_agency_fk }">selected</c:if>>${obj.execution_agency_fk}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="execution_agency_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                    <p class="searchable_label"> HOD <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="hod_user_id_fk" name="hod_user_id_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${utilityHODList }">
                                      	   <option value= "${ obj.hod_user_id_fk}" <c:if test="${obj.hod_user_id_fk eq utilityShifting.hod_user_id_fk }">selected</c:if>>${obj.hod_user_id_fk} - ${obj.user_name}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="hod_user_id_fkError" class="error-msg" ></span>
                                </div>
							</div>
							<input type="hidden" name="utility_shifting_id" id="utility_shifting_id" value="${utilityShifting.utility_shifting_id }"  />
							  <input type="hidden" name="id" id="id" value="${utilityShifting.id }"  />
							
							<div class="row">
								 
                                <div class="col s12 m3 l3 input-field ">
                                    <p class="searchable_label"> Utility Type <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="utility_type_fk" name="utility_type_fk">
                                         <option value="" >Select</option>
                                        <c:forEach var="obj" items="${utilityTypeList }">
                                      	   <option value= "${ obj.utility_type_fk}" <c:if test="${obj.utility_type_fk eq utilityShifting.utility_type_fk }">selected</c:if>>${obj.utility_type_fk}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="utility_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                     <input id="utility_description" maxlength="50" data-length="50" name="utility_description" type="text" class="validate w85 pdr3em" value="${utilityShifting.utility_description }">                                     
                                     <label for="utility_description">Utility Description<span class="required">*</span></label>
	                                 <span id="utility_descriptionError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                     <input id="location_name" maxlength="50" data-length="50" name="location_name" type="text" class="validate w85 pdr3em" value="${utilityShifting.location_name }">                                     
                                     <label for="location_name">Location Name</label>
	                                 <span id="location_nameError" class="error-msg" ></span>
                                </div>
                               
                                <div class="col s12 m3 l3 input-field">
                                    <input id="custodian" maxlength="100" data-length="100" name="custodian" type="text" class="validate w85 pdr3em" value="${utilityShifting.custodian }">                                     
                                     <label for="custodian">Custodian </label>
	                                 <span id="custodianError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
								<div class="col s12 m3 l3 input-field">
                                     <input id="identification" name="identification" type="text" class="validate datepicker" value="${utilityShifting.identification }">                                     
                                     <label for="identification">Identification Date</label>
	                                 <button type="button" id="identification_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="identificationError" class="error-msg" ></span>
                                </div>
                                 <div class="col s12 m3 l3 input-field">
                                    <input id="reference_number" maxlength="25" data-length="25" name="reference_number" type="text" class="validate w85 pdr3em" value="${utilityShifting.reference_number }">                                     
                                     <label for="reference_number">Reference Number</label>
	                                 <span id="reference_numberError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                    <input id="executed_by" maxlength="100" data-length="100" name="executed_by" type="text" class="validate w85 pdr3em" value="${utilityShifting.executed_by }">                                     
                                     <label for="executed_by">Executed by </label>
	                                 <span id="executed_byError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
								 <div class="col s12 m3 l3 input-field">
                                     <input id="chainage" maxlength="100" data-length="100" name="chainage" type="text" class="validate w85 pdr3em" value="${utilityShifting.chainage }" onKeyup="getRRCoordinates();">                                     
                                     <label for="chainage">Chainage</label>
	                                 <span id="chainageError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
								     <input id="latitude" name="latitude" type="text" class="validate w75 pdr4em" value="${utilityShifting.latitude }">
		                             <label for="latitude" id="idLatitude">Latitude</label>
		                             <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
								     <input id="longitude" name="longitude" type="text" class="validate w75 pdr4em" value="${utilityShifting.longitude }">
		                             <label for="longitude" id="idLongitude">Longitude</label>
		                             <span id="longitudeError" class="error-msg" ></span>
                                </div>
							</div>							
							<div class="row">
								<%-- <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label"> Utility Type <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="utility_type_fk" name="utility_type_fk">
                                         <option value="" >Select</option>
                                        <c:forEach var="obj" items="${utilityTypeList }">
                                      	   <option value= "${ obj.utility_type_fk}" <c:if test="${obj.utility_type_fk eq utilityShifting.utility_type_fk }">selected</c:if>>${obj.utility_type_fk}</option>
                                         </c:forEach>
                                    </select>
                                    <span id="utility_type_fkError" class="error-msg" ></span>
                                </div> --%>
                                <%-- <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Category <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="utility_category_fk" name="utility_category_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${utilityCategoryList }">
                                      	   <option value= "${ obj.utility_category_fk}" <c:if test="${obj.utility_category_fk eq utilityShifting.utility_category_fk }">selected</c:if>>${obj.utility_category_fk}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="utility_category_fkError" class="error-msg" ></span> 
                                </div> --%>
							</div>
							<div class="row">
								<div class="col s12 m3 l3 input-field ">
                                    <p class="searchable_label mb-8"> Impacted Contract <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="impacted_contract_id_fk" name="impacted_contract_id_fk" 
                                    onchange="getRequirementStageList(this.value);" >
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${impactedContractsList }">
                                      	   <option  value= "${ obj.contract_id_fk}" <c:if test="${obj.contract_id_fk eq utilityShifting.impacted_contract_id_fk }">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="impacted_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                    <p class="searchable_label mb-8"> Requirement stage <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="requirement_stage_fk" name="requirement_stage_fk" 
                                    onchange="getImpactedElementList(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${reqStageList }">
                                      	   <option value= "${ obj.requirement_stage_fk}" <c:if test="${obj.requirement_stage_fk eq utilityShifting.requirement_stage_fk }">selected</c:if>>${obj.requirement_stage_fk}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="requirement_stage_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                    <p class="searchable_label mb-8"> Impacted Element</p>
                                    <select class="searchable validate-dropdown" id="impacted_element" name="impacted_element" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${impactedElementList }">
                                      	   <option value= "${ obj.impacted_element}" <c:if test="${obj.impacted_element eq utilityShifting.impacted_element }">selected</c:if>>${obj.impacted_element}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="impacted_elementError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m3 l3 input-field">
                                     <input id="affected_structures" maxlength="100" data-length="100" name="affected_structures" type="text" class="validate w85 pdr3em" value="${utilityShifting.affected_structures }" >                                     
                                     <label for="affected_structures">Affected Structures</label>
	                                 <span id="affected_structuresError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
                                <div class="col s12 m3 l3 input-field">
                                     <input id="planned_completion_date" name="planned_completion_date" type="text" class="validate datepicker" value="${utilityShifting.planned_completion_date }">                                     
                                     <label for="planned_completion_date">Target Date</label>
	                                 <button type="button" id="planned_completion_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="planned_completion_dateError" class="error-msg" ></span> 
                                </div>
                                <div class="col s12 m2 l3 input-field">
                                     <input id="scope" maxlength="25" data-length="25" name="scope" type="number" class="validate w85 pdr3em num" value="${utilityShifting.scope }" onkeypress='return restrictAlphabets(event)' onkeyup='scopeValidation();'>                                     
                                     <label for="scope">Scope <span class="required"></span></label>
	                                 <span id="scopeError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m2 l3 input-field">
                                     <input id="completed" maxlength="25" data-length="25" name="completed" type="number" class="validate w100 pdr2em num" value="${utilityShifting.completed }" onkeypress='return restrictAlphabets(event)' onkeyup='scopeValidation();'>                                     
                                     <label for="completed">Completed <span class="required"></span></label>
	                                 <span id="completedError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m2 l3 input-field">
                                    <p class="searchable_label mb-8"> Unit <span class="required" id="unit"></span></p>
                                    <select class="searchable validate-dropdown" id="unit_fk" name="unit_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${unitList }">
                                      	   <option value= "${ obj.unit_fk}" <c:if test="${obj.unit_fk eq utilityShifting.unit_fk }">selected</c:if>>${obj.unit_fk}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="unit_fkError" class="error-msg" ></span>
                                </div>
							</div>
							<%-- <div class="row">
								
                             	<div class="col s12 m3 l3 input-field">
                                     <input id="planned_completion_date" name="planned_completion_date" type="text" class="validate datepicker" value="${utilityShifting.planned_completion_date }">                                     
                                     <label for="planned_completion_date">Planned Completion <span class="required">*</span></label>
	                                 <button type="button" id="planned_completion_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="planned_completion_dateError" class="error-msg" ></span> 
                                </div>
                             	<div class="col s12 m3 l3 input-field">
                                     <input id="start_date" name="start_date" type="text" class="validate datepicker" value="${utilityShifting.start_date }">                                     
                                     <label for="start_date">Start Date <span class="required"></span></label>
	                                 <button type="button" id="start_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="start_dateError" class="error-msg" ></span>
                                </div>
							</div> --%>
							<div class="row">
								<%-- <div class="col s6 m2 l2 input-field">
                                     <input id="scope" maxlength="25" data-length="25" name="scope" type="number" class="validate w65 pdr3em num" value="${utilityShifting.scope }" onkeypress='return restrictAlphabets(event)' onkeyup='scopeValidation();'>                                     
                                     <label for="scope">Scope <span class="required"></span></label>
	                                 <span id="scopeError" class="error-msg" ></span>
                                </div> --%>
                                <%-- <div class="col s6 m2 l2 input-field">
                                     <input id="completed" maxlength="25" data-length="25" name="completed" type="number" class="validate w65 pdr3em num" value="${utilityShifting.completed }" onkeypress='return restrictAlphabets(event)' onkeyup='scopeValidation();'>                                     
                                     <label for="completed">Completed <span class="required"></span></label>
	                                 <span id="completedError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m2 l2 input-field">
                                    <p class="searchable_label mb-8"> Unit <span class="required" id="unit"></span></p>
                                    <select class="searchable validate-dropdown" id="unit_fk" name="unit_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${unitList }">
                                      	   <option value= "${ obj.unit_fk}" <c:if test="${obj.unit_fk eq utilityShifting.unit_fk }">selected</c:if>>${obj.unit_fk}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="unit_fkError" class="error-msg" ></span>
                                </div> --%>
                                <div class="col s12 m3 l3 input-field">
                                     <input id="start_date" name="start_date" type="text" class="validate datepicker" value="${utilityShifting.start_date }">                                     
                                     <label for="start_date">Start Date <span class="required"></span></label>
	                                 <button type="button" id="start_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="start_dateError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m3 l3 input-field">
                                    <p class="searchable_label mb-8"> Status <span class="required"></span></p>
                                    <select class="searchable validate-dropdown" id="shifting_status_fk" name="shifting_status_fk" >
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${statusList }">
                                      	   <option value= "${ obj.shifting_status_fk}" <c:if test="${obj.shifting_status_fk eq utilityShifting.shifting_status_fk }">selected</c:if>>${obj.shifting_status_fk}</option>
                                         </c:forEach>
                                    </select>
                                      <span id="shifting_status_fkError" class="error-msg" ></span>
                                </div>
                                
                             	<div class="col s12 m3 l3 input-field">
                                     <input id="shifting_completion_date" name="shifting_completion_date" type="text" class="validate datepicker" value="${utilityShifting.shifting_completion_date }">                                     
                                     <label for="shifting_completion_date">Completion Date <span class="required"></span></label>
	                                 <button type="button" id="shifting_completion_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="shifting_completion_dateError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="col s12">
										<div class="row fixed-width">
									       <h6 class="center-align">Progress Details</span></h6>
									        <div class="table-inside">
									            <table id="progressDetailsTable" class="mdl-data-table mobile_responsible_table" >
									                <thead>
									                    <tr>
									                        <th>Progress Date </th>
															<th>Progress of Work </th>
															<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th style="width:8%">Action</th></c:if>
									                    </tr>
									                </thead>
									                <tbody id="progressDetailsTableBody">
									                <c:choose>
				                                        <c:when test="${not empty utilityShifting.utilityShiftingProgressDetailsList }" >				                                          
				                                		  <c:forEach var="pObj" items="${utilityShifting.utilityShiftingProgressDetailsList }" varStatus="index"> 				                                		      
											                  <tr id="progressDetailsRow${index.count }">
											                        <td data-head="Progress Date" class="input-field">
											                              <input id="progress_date${index.count }" name="progress_dates" type="text" class="validate datepicker" placeholder="Progress Date" value="${pObj.progress_date }">
										                                  <button type="button" id="progress_date${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
										                                  <span id="progress_date${index.count}Error" class="error-msg" ></span>
											                        </td>
											                        <td data-head="Progress of Work" class="input-field">
											                            <textarea id="progress_of_work${index.count }" maxlength="1000" data-length="1000" name ="progress_of_works" class="pmis-textarea pdr5em" placeholder="Progress of Work">${pObj.progress_of_work }</textarea>
									                                    <span id="progress_of_work${index.count }Error" class="error-msg"></span>
											                        </td>
											                        <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
												                        <td class="mobile_btn_close">
												                            <a onclick="removeProgressDetailsRow('${index.count }');"
												                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
												                        </td>
											                        </c:if>
											                    </tr>											                  
									                	</c:forEach>
                                           			</c:when>
                                             		<c:otherwise>
									                    <tr id="progressDetailsRow0">
									                        <td data-head="Progress Date" class="input-field">
									                              <input id="progress_date0" name="progress_dates" type="text" class="validate datepicker" placeholder="Progress Date">
								                                  <button type="button" id="progress_date0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
								                                  <span id="progress_date0Error" class="error-msg" ></span>
									                        </td>									                       
									                        <td data-head="Progress of Work" class="input-field">
									                            <textarea id="progress_of_work0" maxlength="1000" data-length="1000" name ="progress_of_works" class="pmis-textarea pdr5em" placeholder="Progress of Work"></textarea>
							                                    <span id="progress_of_work0Error" class="error-msg"></span>
									                        </td>
									                        <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
										                        <td class="mobile_btn_close">
										                            <a onclick="removeProgressDetailsRow('0');"
										                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
										                        </td>
									                        </c:if>
									                    </tr>
									              </c:otherwise>
                                            	</c:choose>
									                </tbody>
									            </table>
									            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
									            <table  class="mdl-data-table" style="margin-bottom: 20px">
			                                        <tbody>                                          
			                                            <tr>
			                                   				<td colspan="3"  ><a class="btn waves-effect waves-light bg-m t-c "  onclick="addProgressDetailsRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table> 
			                                    </c:if>
			                                    <c:choose>
				                                    <c:when test="${not empty utilityShifting.utilityShiftingProgressDetailsList && fn:length(utilityShifting.utilityShiftingProgressDetailsList) gt 0 }">
				                                		<input type="hidden" id="progressDetailsRowNo"  name="progressDetailsRowNo" value="${fn:length(utilityShifting.utilityShiftingProgressDetailsList) }" />
				                                	</c:when>
				                                 	<c:otherwise>
				                                 		<input type="hidden" id="progressDetailsRowNo"  name="progressDetailsRowNo" value="0" />
				                                 	</c:otherwise>
				                                 </c:choose>
							                                    
									        </div>
									    </div>
									</div>
							
							
	                            <div class="col s12">
	                                <div class="row fixed-width" >
	                                    <!-- <h5 class="center-align"><span class="div-header">Attachments</span></h5>  -->
	                                    <h6 class="center-align">Attachments</span></h6> 
	                                    <div class="table-inside" style="margin-bottom: 20px">
	                                        <table class="mdl-data-table mobile_responsible_table" >
	                                            <thead>
	                                                <tr>
	                                                	<th>File Type </th>
	                                                    <th>Name </th>
	                                                    <th style="text-align:center;">Attachment</th>
	                                                    <th> </th>
	                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
	                                                    	<th style="width:8%">Action</th>
	                                                    </c:if>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="attachmentsTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty utilityShifting.utilityShiftingFilesList  && fn:length(utilityShifting.utilityShiftingFilesList ) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${utilityShifting.utilityShiftingFilesList }" varStatus="index">  
			                                                <tr id="attachmentRow${index.count }">
			                                                	<td data-head="File Type " class="input-field">
																	<select  name="attachment_file_types"  id="attachment_file_types${index.count }"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${utilityshiftingfiletypeList}">
					                    					  				 <option value="${obj.utility_shifting_file_type }" <c:if test="${docObj.utility_shifting_file_type eq obj.utility_shifting_file_type}">selected</c:if>>${obj.utility_shifting_file_type}</option>
					                                          			  </c:forEach>
					                               					  </select>
															    </td>
			                                                    <td data-head="Name " class="input-field"> <input id="attachmentNames${index.count }" maxlength="50" data-length="50" name="attachmentNames" type="text" class="validate w85 pdr4em"
			                                                            placeholder="Name" value="${docObj.name }">
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="utilityShiftingFiles${index.count }" name="utilityShiftingFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="utilityShiftingFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="attachmentFileNames${index.count }" name="attachmentFileNames" value="${docObj.attachment }">
			                                                             <span id="attachmentFileName${index.count }" class="filevalue"></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
			                                                     		<input type="hidden" id="attach_file_ids${index.count }" name="attach_file_ids" value="${docObj.utility_shifting_id }"/>
			                                                      		<a href="<%=CommonConstants2.UTILITY_SHIFTING_FILES%>${docObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                        
			                                                    </td>
			                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
				                                                    <td class="mobile_btn_close">
				                                                        <a href="javascript:void(0);" onclick="removeAttachmentRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
				                                                                class="fa fa-close"></i></a>
				                                                    </td>
			                                                    </c:if>
			                                                </tr> 
	                                                	</c:forEach>
	                                           		</c:when>
	                                             	<c:otherwise>
	                                             		<tr id="attachmentRow0">
	                                             			<td data-head="File Type " class="input-field">																		
																<select  name="attachment_file_types"  id="attachment_file_types0"  class="validate-dropdown searchable">
				                                   					 <option value="" >Select</option>
				                                         			  <c:forEach var="obj" items="${utilityshiftingfiletypeList}">
				                    					  				 <option value="${obj.utility_shifting_file_type }">${obj.utility_shifting_file_type}</option>
				                                          			  </c:forEach>
				                               					  </select>
															    </td>
		                                                    <td data-head="Name " class="input-field"> <input id="attachmentNames0" maxlength="50" data-length="50" name="attachmentNames" type="text" class="validate w85 pdr4em"
		                                                            placeholder="Name">
		                                                    </td>
		                                                    <td data-head="Attachment" class="input-field">
		                                                        <span class="normal-btn">
		                                                            <input type="file" id="utilityShiftingFiles0" name="utilityShiftingFiles"
		                                                                style="display:none" onchange="getFileName('0')"/>
		                                                            <label for="utilityShiftingFiles0" class="btn bg-m"><i
		                                                                    class="fa fa-paperclip"></i></label>
		                                                            <input type="hidden" id="attachmentFileNames0" name="attachmentFileNames" value="">
		                                                            <span id="attachmentFileName0" class="filevalue"></span>
		                                                        </span>
		                                                    </td>
		                                                    <td><input type="hidden" id="attach_file_ids0" name="attach_file_ids" value= ""/>
		                                                    </td>
		                                                    
		                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
			                                                    <td class="mobile_btn_close">
			                                                        <a href="javascript:void(0);" onclick="removeAttachmentRow('0');" class="btn waves-effect waves-light red t-c "> <i
			                                                                class="fa fa-close"></i></a>
			                                                    </td>
		                                                    </c:if>
		                                                </tr>
	                                             	</c:otherwise>
                                            	</c:choose> 
	                                            </tbody>
	                                        </table>
	                                        
	                                        <table class="mdl-data-table">
		                                        <tbody>                                          
		                                            <tr>
														<td colspan="3" > <a type="button"  class="btn waves-effect waves-light bg-m t-c"  onclick="addAttachmentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty utilityShifting.utilityShiftingFilesList && fn:length(utilityShifting.utilityShiftingFilesList) gt 0 }">
		                                    		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="${fn:length(utilityShifting.utilityShiftingFilesList) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose> 
	                                    </div>
	                                </div>
	                            </div>                         
                                                   
                            <div class="row">
                                <div class="col s12 m12 input-field">
                                    <textarea id="remarks" maxlength="1000" data-length="1000" name="remarks" class="pmis-textarea pdr5em" data-length="1000">${utilityShifting.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom:20px;">

                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" class="btn waves-effect waves-light bg-m" onclick="addUtilityShifting();">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" class="btn waves-effect waves-light bg-m" style="min-width:90px" onclick="addUtilityShifting();">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                  <div class="col s6  m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/utilityshifting" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                              
                            </div>                           
                </div>
                    <!-- form ends  -->
                        </form>
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
       $(".num").keypress(function() {
           if ($(this).val().length == $(this).attr("maxlength")) {
               return false;
           }
       });
   });
	 $("[data-length]").each(function(i,val){
    	$('#'+val.id).characterCounter();;
    });
	 
	 
	 
	   function getRRCoordinates()
	   {
	       $('#latitude').val();
	       $('#longitude').val();	
	       
		 if($("#work_id_fk").val()!="" || "${utilityShifting.work_id_fk}"!="")
			 {
		       var r1=$('#chainage').val();
		       var work_id_fk=$("#work_id_fk").val();
		       	   if($("#work_id_fk").val()=="")
		    	   {
		       		work_id_fk="${utilityShifting.work_id_fk}";
		    	   }
		       if(r1!="")
		       	{
		    	   var c1=r1; 
		    	   
			        	var myParams = { chainage: r1,work_id_fk:work_id_fk };
			            $.ajax({
			                url: "<%=request.getContextPath()%>/ajax/getRRCoordinates",
			                data: myParams, cache: false,
			                success: function (data) {
			                    if (data.length > 0) 
			                    {
									if(data[0]["chainage"]!="")
										{
					                    	var splitChainage=data[0]["chainage"];
					                    	splitChainage=splitChainage.toString();
					                    	splitChainage=splitChainage.split(",");
					
					                    	var splitLatitude=data[0]["latitude"];
					                    	splitLatitude=splitLatitude.toString();
					                    	splitLatitude=splitLatitude.split(",");
					                    	
					                    	var splitLongitude=data[0]["longitude"];
					                    	splitLongitude=splitLongitude.toString();
					                    	splitLongitude=splitLongitude.split(",");                    	
					                    	
					                    	
					                        var a1= splitChainage[0];    var x1=splitLatitude[0]; var y1=splitLongitude[0];
					                        var b1=splitChainage[1];	 var x2=splitLatitude[1]; var y2=splitLongitude[1];
					
					                        var x3=0;   var y3=0;
					
					                        x3=parseFloat(x2)+parseFloat((((c1-b1)/(b1-a1))*(x2-x1)));
					                        y3=parseFloat(y2)+parseFloat((((c1-b1)/(b1-a1))*(y2-y1)));
					                        
					                        $('#idLatitude').html("");
					                        $('#idLongitude').html("");		                        
				                        
					                        $('#latitude').val(x3);
					                        $('#longitude').val(y3);									
										}
									    else 
									    {
										
					                    	var splitLatitude=data[0]["latitude"];
					                    	var splitLongitude=data[0]["longitude"];
					                    	
					                        $('#idLatitude').html("");
					                        $('#idLongitude').html("");		                    	
				                       
					                        $('#latitude').val(splitLatitude);
					                        $('#longitude').val(splitLongitude);
										}
								 								
									
			                        
			                    }
			                }
			            });
		       	}
			 }
	   } 	 
	 
	 
	 
	 
	 
	 
   
   $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
    	$('.searchable').select2();
    	$(".datepicker").datepicker();
    	var status = $("#shifting_status_fk").val();
    	if(status == null || status == ''){
    		 $("#shifting_status_fk option[value='Not Started']").attr("selected", "selected");
    		 $('.searchable').select2();
    	}
    	
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
         
    	$('#shifting_status_fk').change(function(){
    		status = $("#shifting_status_fk").val();
    		if(status == null || status == ''){
    			 $("#shifting_status_fk").val('Not Started'); 
	    	}
    		if($('#shifting_status_fk').val()=='Completed'){
    			$('#shifting_completion_date').rules('add',  { required: true });
    			$('#start_date').rules('add',  { required: true });
    			$('#completed').rules('add',  { required: true });
    			$('#scope').rules('add',  { required: true });
    			$('#unit_fk').rules('add',  { required: true });

    			//$('label[for="shifting_completion_date"] .required').text('*');   
    			//$('label[for="start_date"] .required').text('*');   
    			//$('label[for="completed"] .required').text('*');   
    			//$('label[for="scope"] .required').text('*');
    			$('#unit').text('*');   

    		}else if($('#shifting_status_fk').val()=='In Progress'){
    			$('#shifting_completion_date').rules('add',  { required: false });
    			$('#start_date').rules('add',  { required: true });
    			$('#completed').rules('add',  { required: true });
    			$('#scope').rules('add',  { required: true });
    			$('#unit_fk').rules('add',  { required: true });

    			//$('label[for="shifting_completion_date"] .required').text('');   
    			//$('label[for="start_date"] .required').text('*');   
    			//$('label[for="completed"] .required').text('*');   
    			//$('label[for="scope"] .required').text('*');   
    			$('#unit').text('*');   


    		}else if($('#shifting_status_fk').val()=='Under Progress'){
    			$('#shifting_completion_date').rules('add',  { required: false });
    			$('#start_date').rules('add',  { required: true });
    			$('#completed').rules('add',  { required: true });
    			$('#scope').rules('add',  { required: true });
    			$('#unit_fk').rules('add',  { required: true });

    			//$('label[for="shifting_completion_date"] .required').text('');   
    			//$('label[for="start_date"] .required').text('*');   
    			//$('label[for="completed"] .required').text('*');   
    			//$('label[for="scope"] .required').text('*');   
    			$('#unit').text('*');   


    		}
    		else{
    			$('#shifting_completion_date').rules('add',  { required: false });
    			$('#start_date').rules('add',  { required: false });
    			$('#completed').rules('add',  { required: false });
    			$('#scope').rules('add',  { required: false });
    			$('#unit_fk').rules('add',  { required: false });

    			//$('label[for="shifting_completion_date"] .required').text('');   
    			//$('label[for="start_date"] .required').text('');   
    			//$('label[for="completed"] .required').text('');   
    			//$('label[for="scope"] .required').text('');   
    			$('#unit').text('');   


    		}
    		
        });
    	
    	$('#shifting_status_fk').change(function(){
    		if($('#shifting_status_fk').val()=='Not Started'){
    			$('#shifting_completion_date').rules('add',  { required: false });
    			$('#start_date').rules('add',  { required: false });
    			$('#completed').rules('add',  { required: false });
    			$('#scope').rules('add',  { required: false });
    			$('#unit').rules('add',  { required: false });

				$('#shifting_completion_dateError').text('');   
				$('#start_dateError').text('');   
				$('#completedError').text('');   
				$('#scopeError').text('');   
				$('#unit_fkError').text('');   
 

    		}
    		if($('#shifting_status_fk').val()=='In Progress'){
    			$('#shifting_completion_date').rules('add',  { required: false });
    			
				$('#shifting_completion_dateError').text('');   
				
    		}
    		if($('#shifting_status_fk').val()=='Under Progress'){
    			$('#shifting_completion_date').rules('add',  { required: false });
    			

				$('#shifting_completion_dateError').text('');   
				 

    		}
    	});

       
    	
    	if($('#shifting_status_fk').val()=='Completed'){
			$('label[for="shifting_completion_date"] .required').text('*');
    	}
		    ///$('label[for="start_date"] .required').text('');	    	  
		/* }else if($('#shifting_status_fk').val()=='In Progress'){
		    $('label[for="start_date"] .required').text('*');	    	  
			$('label[for="shifting_completion_date"] .required').text('');
        } */
    	       	
        if($('#shifting_status_fk').val()=='Completed'){
			$('label[for="scope"] .required').text('*');
			$('label[for="completed"] .required').text('*');
			$('label[for="start_date"] .required').text('*');
			$('#unit').text('*');   

    	} 
        if($('#shifting_status_fk').val()=='In Progress'){
			$('label[for="scope"] .required').text('*');
			$('label[for="completed"] .required').text('*');
			$('label[for="start_date"] .required').text('*');
			$('#unit').text('*');   

    	} 
         if($('#shifting_status_fk').val()=='Under Progress'){
			$('label[for="scope"] .required').text('*');
			$('label[for="completed"] .required').text('*');
			$('label[for="start_date"] .required').text('*');
			$('#unit').text('*');   

    	} 
         
        var project_id_fk = "${utilityShifting.project_id_fk}";
        var work_id_fk = "${utilityShifting.work_id_fk}";
        var impacted_contract_id_fk = "${utilityShifting.impacted_contract_id_fk}";
        var requirement_stage_fk = "${utilityShifting.requirement_stage_fk}";
        var impacted_element = "${utilityShifting.impacted_element}";
        
        if($.trim(project_id_fk) != '' ){
        	getWorksList(project_id_fk);
        }
        if($.trim(work_id_fk) != '' ){
        	getImpactedContractsList(work_id_fk);
        }
        if($.trim(impacted_contract_id_fk) != '' ){
        	getRequirementStageList(impacted_contract_id_fk);
        }
        if($.trim(requirement_stage_fk) != '' ){
        	getImpactedElementList(requirement_stage_fk);
        }
        
   });
   
   function restrictAlphabets(e) {
       var x = e.which || e.keycode;
       if (x == 46) {
           //Check if the text already contains the . character
           if (x.value.indexOf('.') === -1) {
             return true;
           } else {
             return false;
           }
         } else {
           if (x > 31 &&
             (x < 48 || x > 57))
             return false;
         }
         return true;
   }
   function scopeValidation()
   {
		 var s1=parseFloat(document.getElementById("scope").value);
		 var s2=parseFloat(document.getElementById("completed").value);
    	if(parseFloat(s1)<parseFloat(s2))
    	{
	    	 	alert("Scope Should be greater than or equal to Completed.");
	    	 	document.getElementById("completed").value="";
	    	 	return false;
    	} 	   
   }
   
   
	function addAttachmentRow(){		
		 var rowNo = $("#attachmentRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var total = 0;
		 var html = '<tr id="attachmentRow'+rNo+'">'
					 +'<td data-head="File Type " class="input-field">'
							+'<select  name="attachment_file_types"  id="attachment_file_types'+rNo+'"  class="validate-dropdown searchable">'
		    					+ '<option value="" >Select</option>'
		          			   <c:forEach var="obj" items="${utilityshiftingfiletypeList}">
					  				+ '<option value="${obj.utility_shifting_file_type }">${obj.utility_shifting_file_type}</option>'
		           			  </c:forEach> 
						+ '</select></td>'
					 +'<td data-head="Name " class="input-field"> <input id="attachmentNames'+rNo+'" maxlength="50" data-length="50" name="attachmentNames" type="text" class="validate w85 pdr4em" placeholder="Name"> </td>'
					 +'<td data-head="Attachment" class="input-field">'
					 +'<span class="normal-btn">'
					 +'<input type="file" id="utilityShiftingFiles'+rNo+'" name="utilityShiftingFiles" style="display:none" onchange="getFileName('+rNo+')" />'
					 +'<label for="utilityShiftingFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
					 +'<input type="hidden" id="attachmentFileNames'+rNo+'" name="attachmentFileNames">'
					 +'<span id="attachmentFileName'+rNo+'" class="filevalue"></span>'
					 +'</span>'
					 +'</td>'
					 +'<td><input type="hidden" id="attach_file_ids'+rNo+'" name="attach_file_ids"/></td>';
					 
					 var user_role_name = '${sessionScope.USER_ROLE_NAME}';
					 if(user_role_name == 'IT Admin'){
						 html = html +'<td class="mobile_btn_close">'
						 +'<a href="javascript:void(0);" onclick="removeAttachmentRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
						 +'</td>';
					 }
					 html = html +'</tr>';
		
			 $('#attachmentsTableBody').append(html);
			 $("#attachmentRowNo").val(rNo);
			 $('#attachmentNames'+rNo).characterCounter();;
			 $('.searchable').select2();
	         $("#attach_file_ids0").val('');
	} 
	
	function removeAttachmentRow(rowNo){
		$("#attachmentRow"+rowNo).remove();
	}

	function getFileName(rowNo){
		var filename = $('#utilityShiftingFiles'+rowNo)[0].files[0].name;
	    $('#attachmentFileName'+rowNo).html(filename);
	    $('#attachmentNames'+rowNo).val(filename);
	}
	
	
	 function addProgressDetailsRow(){
    	 var rowNo = $("#progressDetailsRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var no = 0;
		 var html = '<tr id="progressDetailsRow'+rNo+'"> '
		 			+'<td data-head="Progress Date" class="input-field"> <input id="progress_date'+rNo+'" name="progress_dates" type="text" class="validate datepicker" '
		 			+'placeholder="Progress Date"> <button type="button" id="progress_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> '											                              
        			+'<span id="progress_date'+rNo+'Error" class="error-msg" ></span>   </td>'
   					+'<td data-head="Progress of Work" class="input-field"> <textarea id="progress_of_work'+rNo+'" maxlength="1000" data-length="1000" name ="progress_of_works" class="pmis-textarea pdr4em" placeholder="Progress of Work"></textarea>'
   					+'<span id="progress_of_work'+rNo+'Error" class="error-msg"></span>  </td>'
   				<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
      				+'<td class="mobile_btn_close"> <a onclick="removeProgressDetailsRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td>'
   				</c:if>
					+'</tr>';
		
			 $('#progressDetailsTableBody').append(html); 
			 $('#progress_of_work'+rNo).characterCounter();;
			 $("#progressDetailsRowNo").val(rNo);
			 $('.searchable').select2();
    }
    
    function removeProgressDetailsRow(rowNo){
    	$("#progressDetailsRow"+rowNo).remove();
    }   
    
    
    function getWorksList(projectId) {
    	$(".page-loader").show();
        $("#work_id_fk option:not(:first)").remove();
        $("#contract_id_fk option:not(:first)").remove();

        if ($.trim(projectId) != "") {
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorkListForUtilityForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workShortName = '';
                            if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                            var work_id_fk_exists = "${utilityShifting.work_id_fk}";
                            var selected = '';
                            if(work_id_fk_exists == val.work_id_fk){
                            	selected = 'selected';
                            }
                            $("#work_id_fk").append('<option name = "' + val.work_code + '" value="' + val.work_id_fk + '" '+selected+'>' + $.trim(val.work_id_fk) + $.trim(workShortName) + '</option>');
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

    //geting contracts list    
    function getContractsList(work_id_fk) {
    	var work_code =   $('#work_id_fk option:selected').attr('name');
		$("#work_code").val(work_code);
    	$(".page-loader").show();
        $("#contract_id_fk option:not(:first)").remove();
        
        if ($.trim(work_id_fk) != "") {
            var myParams = { work_id_fk: work_id_fk };
            $.ajax({
            	url: "<%=request.getContextPath()%>/ajax/getContractsListForSafetyForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var contract_short_name = '';
                            if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                            $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" hod_user_id="'+val.hod_user_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
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
    
  //geting contracts list    
    function getImpactedContractsList(work_id_fk) {
    	var work_code =   $('#work_id_fk option:selected').attr('name');
		$("#work_code").val(work_code);
    	$(".page-loader").show();
        $("#impacted_contract_id_fk option:not(:first)").remove();
        $("#requirement_stage_fk option:not(:first)").remove();
        $("#impacted_element option:not(:first)").remove();
        
        if ($.trim(work_id_fk) != "") {
            var myParams = { work_id_fk: work_id_fk };
            $.ajax({
            	url: "<%=request.getContextPath()%>/ajax/getImpactedContractsListForUSForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var contract_short_name = '';
                            if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                            var impacted_contract_id_fk_exists = "${utilityShifting.impacted_contract_id_fk}";
                            var selected = '';
                            if(impacted_contract_id_fk_exists == val.contract_id_fk){
                            	selected = 'selected';
                            }
                            $("#impacted_contract_id_fk").append('<option  workId="'+val.work_id_fk +'" hod_user_id="'+val.hod_user_id_fk +'" value="' + val.contract_id_fk + '" '+selected+'>' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
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
   		var contract_id_fk = $("#impacted_contract_id_fk").val();
   		if($.trim(contract_id_fk) != ''){        			
   			
        	var workId = $("#impacted_contract_id_fk").find('option:selected').attr("workId");
        	projectId = workId.substring(0, 3);    
   			//workId = workId.substring(3, work_id.length);
   			$("#project_id_fk").val(projectId);
   			$("#project_id_fk").select2();
   			
   		}
   		
   		if ($.trim(projectId) != "") {
   			$("#work_id_fk option:not(:first)").remove();
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorkListForSafetyForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                            if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                $("#work_id_fk").append('<option name = "' + val.work_code + '" value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                            } else {
                                $("#work_id_fk").append('<option name = "' + val.work_code + '" value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
    
    
    
    function getRequirementStageList(impacted_contract_id_fk) {
    	$(".page-loader").show();
        $("#requirement_stage_fk option:not(:first)").remove();
        $("#impacted_element option:not(:first)").remove();
        
        if ($.trim(impacted_contract_id_fk) != "") {
            var myParams = { impacted_contract_id_fk: impacted_contract_id_fk };
            $.ajax({
            	url: "<%=request.getContextPath()%>/ajax/getReqStageListForUSForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var requirement_stage_fk_exists = "${utilityShifting.requirement_stage_fk}";
                            var selected = '';
                            if(requirement_stage_fk_exists == val.requirement_stage_fk){
                            	selected = 'selected';
                            }
                        	$("#requirement_stage_fk").append('<option value="' + val.requirement_stage_fk + '" '+selected+'>' + $.trim(val.requirement_stage_fk) + '</option>');
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
    
    function getImpactedElementList(requirement_stage_fk) {
    	$(".page-loader").show();
        $("#impacted_element option:not(:first)").remove();
        var impacted_contract_id_fk = $("#impacted_contract_id_fk").val();
        
        if ($.trim(requirement_stage_fk) != "") {
            var myParams = { impacted_contract_id_fk: impacted_contract_id_fk,requirement_stage_fk: requirement_stage_fk };
            $.ajax({
            	url: "<%=request.getContextPath()%>/ajax/getImpactedElementListForUSForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var impacted_element_exists = "${utilityShifting.impacted_element}";
                            var selected = '';
                            if(impacted_element_exists == val.impacted_element){
                            	selected = 'selected';
                            }
                        	$("#impacted_element").append('<option value="' + val.impacted_element + '" '+selected+'>' + $.trim(val.impacted_element) + '</option>');
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
    
    
    $('form').on('reset', function () {
        $(".searchable").trigger("change");
    });
    
    function addUtilityShifting(){
		if(validator.form()){ // validation perform
			$(".page-loader").show();
			var compensation = $('#compensation').val();
  			if(compensation == ""){
  				$('#compensation_units').val("");
  			}
			document.getElementById("utilityshiftingform").submit();			
	 	}
	}
	
	//Wait for the DOM to be ready
	
	// to validate apartment form inputs thruogh jquery.
	   
	    var validator = $('#utilityshiftingform').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
    				  "project_id_fk": {
   				 		required: false
   				 	  },"work_id_fk": {
				 		required: true
				 	  },"contract_id_fk": {
				 		required: false
				 	  },"identification": {
				 		required: false
				 	  },"location_name": {
				 		required: false
				 	  },"reference_number": {
				 		required: false
				 	  },"latitude": {
				 		required: false
				 	  },"utility_description": {
			 		    required: true,
			 	   	  },"owner_name": {
			 		    required: true,
			 	   	  },"utility_type_fk": {
				 		required: true
				 	  },"utility_category_fk": {
			 		    required: true
			 	   	  },"execution_agency_fk": {
				 		required: true
				 	  },"impacted_contract_id_fk": {
				 		required: true
				 	  },"requirement_stage_fk": {
				 		required: true
				 	  },"planned_completion_date": {
				 		required: false
				 	  },"shifting_completion_date": {
				 		 required: function(element) {
				 	        return $('#shifting_status_fk').val()=='Completed'
				 	      }
				 	  },"start_date":{	
				 		 required:false
				 	  },"scope": {
				 		 required:false
			 	   	  },"completed": {
			 	   		required:false
				 	  },"unit_fk": {
				 		 required:false
			 	   	  },"shifting_status_fk": {
				 		required: false
				 	  },
				 	  "hod_user_id_fk":{	
				 		 required: true
				 	  },"custodian": {
				 		 required: false
			 	   	  },"executed_by": {
			 	   		required: false
				 	  },"impacted_element": {
				 		 required: false
			 	   	  },"affected_structures": {
				 		required: false
				 	  },"chainage":{
				 		 required: false
				 	  }
				 				
			 	},
			   messages: {
       				  "project_id_fk": {
     				 	required: 'Required'
     				  },"work_id_fk": {
  				 		required: 'Required'
  				 	  },"contract_id_fk": {
  				 		required: 'Required'
  				 	  },"identification": {
  				 		required: 'Required'
  				 	  },"location_name": {
  				 		required: 'Required'
  				 	  },"reference_number": {
  				 		required: 'Required'
  				 	  },"latitude": {
  				 		required: 'Required'
  				 	  },"utility_description": {
  			 		    required: 'Required',
  			 	   	  },"owner_name": {
  			 		    required: 'Required',
  			 	   	  },"utility_type_fk": {
  				 		required: 'Required'
  				 	  },"utility_category_fk": {
  			 		    required: 'Required'
  			 	   	  },"execution_agency_fk": {
  				 		required: 'Required'
  				 	  },"impacted_contract_id_fk": {
  				 		required: 'Required'
  				 	  },"requirement_stage_fk": {
  				 		required: 'Required'
  				 	  },"planned_completion_date": {
  				 		required: 'Required'
  				 	  },"shifting_completion_date": {
  				 		required: 'Required'
  				 	  },"start_date":{	
  				 		 required: 'Required'
  				 	  },"scope": {
  			 		    required: 'Required',
  			 	   	  },"completed": {
  				 		required: 'Required'
  				 	  },"unit_fk": {
  			 		    required: 'Required'
  			 	   	  },"shifting_status_fk": {
  				 		required: 'Required'
  				 	  },
				 	  "hod_user_id_fk":{	
				 		 required: 'Required'
				 	  },"custodian": {
				 		 required: 'Required'
			 	   	  },"executed_by": {
			 	   		required: 'Required'
				 	  },"impacted_element": {
				 		 required: 'Required'
			 	   	  },"affected_structures": {
			 	   		required: 'Required'
				 	  },"chainage":{
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
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "identification" ){
   			 	    	 document.getElementById("identificationError").innerHTML="";
			 			 error.appendTo('#identificationError');
			 	    }else if (element.attr("id") == "location_name" ){
			 		     document.getElementById("location_nameError").innerHTML="";
			 			 error.appendTo('#location_nameError');
			 	    }else if (element.attr("id") == "reference_number" ){
			 		     document.getElementById("reference_numberError").innerHTML="";
			 			 error.appendTo('#reference_numberError');
			 	    }else if (element.attr("id") == "latitude" ){
			 		     document.getElementById("latitudeError").innerHTML="";
			 			 error.appendTo('#latitudeError');
			 	    }else if (element.attr("id") == "utility_description" ){
			 		     document.getElementById("utility_descriptionError").innerHTML="";
			 			 error.appendTo('#utility_descriptionError');
			 	    }else if (element.attr("id") == "owner_name" ){
   			 		     document.getElementById("owner_nameError").innerHTML="";
			 			 error.appendTo('#owner_nameError');
			 	    }else if (element.attr("id") == "title" ){
			 		     document.getElementById("titleError").innerHTML="";
			 			 error.appendTo('#titleError');
			 	    }else if (element.attr("name") == "utility_type_fk" ){
			 		     document.getElementById("utility_type_fkError").innerHTML="";
			 			 error.appendTo('#utility_type_fkError');
			 	    }else if (element.attr("id") == "utility_category_fk" ){
			 		     document.getElementById("utility_category_fkError").innerHTML="";
			 			 error.appendTo('#utility_category_fkError');
			 	    }else if (element.attr("id") == "execution_agency_fk" ){
			 	    	     document.getElementById("execution_agency_fkError").innerHTML="";
			 			     error.appendTo('#execution_agency_fkError');
  			 	    }else if (element.attr("id") == "impacted_contract_id_fk" ){
  			 		     document.getElementById("impacted_contract_id_fkError").innerHTML="";
  			 			 error.appendTo('#impacted_contract_id_fkError');
  			 	    }else if (element.attr("id") == "requirement_stage_fk" ){
  			 		     document.getElementById("requirement_stage_fkError").innerHTML="";
  			 			 error.appendTo('#requirement_stage_fkError');
  			 	    }else if (element.attr("id") == "planned_completion_date" ){
  			 		     document.getElementById("planned_completion_dateError").innerHTML="";
  			 			 error.appendTo('#planned_completion_dateError');
  			 	    }else if (element.attr("id") == "shifting_completion_date" ){
  			 		     document.getElementById("shifting_completion_dateError").innerHTML="";
  			 			 error.appendTo('#shifting_completion_dateError');
  			 	    }else if (element.attr("id") == "start_date" ){
  			 		     document.getElementById("start_dateError").innerHTML="";
  			 			 error.appendTo('#start_dateError');
  			 	    }else if (element.attr("id") == "scope" ){
  			 		     document.getElementById("scopeError").innerHTML="";
  			 			 error.appendTo('#scopeError');
  			 	    }else if (element.attr("name") == "completed" ){
  			 		     document.getElementById("completedError").innerHTML="";
  			 			 error.appendTo('#completedError');
  			 	    }else if (element.attr("id") == "unit_fk" ){
  			 		     document.getElementById("unit_fkError").innerHTML="";
  			 			 error.appendTo('#unit_fkError');
  			 	    }else if (element.attr("id") == "shifting_status_fk" ){
  			 		     document.getElementById("shifting_status_fkError").innerHTML="";
  			 			 error.appendTo('#shifting_status_fkError');
  			 	    }
  			 	    else if (element.attr("id") == "hod_user_id_fk" ){
			 		     document.getElementById("hod_user_id_fkError").innerHTML="";
			 			 error.appendTo('#hod_user_id_fkError');
			 	    }else if (element.attr("id") == "custodian" ){
			 		     document.getElementById("custodianError").innerHTML="";
			 			 error.appendTo('#custodianError');
			 	    }else if (element.attr("id") == "executed_by" ){
			 		     document.getElementById("executed_byError").innerHTML="";
			 			 error.appendTo('#executed_byError');
			 	    }else if (element.attr("name") == "impacted_element" ){
			 		     document.getElementById("impacted_elementError").innerHTML="";
			 			 error.appendTo('#impacted_elementError');
			 	    }else if (element.attr("id") == "affected_structures" ){
			 		     document.getElementById("affected_structuresError").innerHTML="";
			 			 error.appendTo('#affected_structuresError');
			 	    }else if (element.attr("id") == "chainage" ){
			 		     document.getElementById("chainageError").innerHTML="";
			 			 error.appendTo('#chainageError');
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

   </script>

</body>
</html>