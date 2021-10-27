<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update Zonal Railway - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}">Add Zonal Railway - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/zonal.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style>
        .row.fixed-width {
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        #zonal_railway_table thead tr th,
        #zonal_railway_table {
            border: 1px solid #ddd;
            vertical-align: middle;
            text-align: center;
        }

        #zonal_railway_table td input[type="month"] {
            border: 0;
            border-bottom: 1px solid #999;
            width: 135px !important;
            background-color: transparent;
        }
        .month::-webkit-calendar-picker-indicator{        	
        	margin:0;
        	padding:0;
        }
        
  		.error-msg label,.error{color:red!important;}
  		
        .input-field .searchable_label {
            font-size: .85rem;
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
        
         input[type="number"]~.units {
           position: absolute;
           right: 20px;
           top: 25px;
           border: 0;
           opacity: 0.7;
       }
       .fw-110{
	       	width:110px !important;
	       	max-width:110px;
	       	white-space:break-spaces;
       }
       .hideCol{
       		display:none !important;
       }
       .pt-5{
			padding-top:5px !important;
		}
		.fs11px{font-size: 11px !important;}
		.lh14{line-height: 1.4;}
		.d-none{display: none;}
		.w7em{width: 7em;}
		.mdl-data-table th:first-of-type{padding-left: 15px;}
       @media only screen and (max-width: 768px){       
			.input-field p.searchable_label {
			    margin-bottom: 0;
			}
			.sm-width-redused label{
				width: calc(100% - 1.5rem) !important;
			}
			#zonal_railway_table td input[type="month"] {
			    width: 90% !important;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
			    vertical-align: middle;
			}
			input[type="month"]{
				box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
    			height: 40px;
    			padding-left: 10px;
			}
			.mobile_responsible_table>tbody > tr:not(.datepicker-row) >td >input[type="number"]~.units{
				position:relative;
				top:0;
				right:23px;
			}
			.hideCol{
				display:block !important;
       			width:100% !important;
       		}
       		.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td.hideCol::before {
			    text-align:center !important;
			    font-weight:bold;
			}
			.pt-5{
				padding-top: 0 !important;
    			margin-top: 10px !important;
			}
       }
	 @media only screen and (max-width: 576px){  
		#zonal_railway_table td input[type="month"] {
		    width: 100% !important;
		}
	 }
		#zonal_railway_table tbody td{
			vertical-align:baseline;
		}

    </style>

</head>

<body>

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
						 		 <c:if test="${action eq 'edit'}">Update Zonal Railway (${zonalRailwayDetails.contract_id })</c:if>
								 <c:if test="${action eq 'add'}">Add Zonal Railway</c:if>                                
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <c:if test="${action eq 'edit'}">				                
		                	<form action="<%=request.getContextPath() %>/update-zonal-railway" id="zonalRailwayForm" name="zonalRailwayForm" method="post" class="form-horizontal" role="form">
                      </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-zonal-railway" id="zonalRailwayForm" name="zonalRailwayForm" method="post" class="form-horizontal" role="form" >
					  </c:if>
                        <%-- <input type="hidden" name="contract_id" value="${zonalRailwayDetails.contract_id }" /> --%>
                        <div class="container container-no-margin">
                            <div class="row">
                             <c:if test="${action eq 'add'}">	
	                                <div class="col s6 m4 l6 input-field offset-m2">
	                                    <p class="searchable_label"> Project <span class="required">*</span></p>
	                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="getWorksList(this.value);">
	                                        <option value="">Select</option>
	                                         <c:forEach var="obj" items="${projectsList }">
	                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
	                                         </c:forEach>
	                                    </select>
	                                    <span id="project_id_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 l6 input-field">
	                                    <p class="searchable_label"> Work <span class="required">*</span></p>
	                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="resetProjectsDropdowns(this.value);" > 
	                                        <option value="" >Select</option>
	                                        <c:forEach var="obj" items="${worksList }">
		                                      	   <option value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
		                                    </c:forEach>
	                                    </select>
	                                    <span id="work_id_fkError" class="error-msg" ></span>
	                                </div>
                                 </c:if>
                                 <c:if test="${action eq 'edit'}">	
		                              <!-- <div class="row" id="center" style="text-align:left;"> -->			                            
			                       		  <div class="col s6 m4 l6 input-field offset-m2">
			                                    <input type="text" value="${zonalRailwayDetails.project_id_fk} - ${zonalRailwayDetails.project_name}" readonly id="project_value" />
												<label for="project_value"> Project <span class="required">*</span></label>
										  </div> 
										  <div class="col s6 m4 l6 input-field"> 
			                                    <input type="text"  value="${zonalRailwayDetails.work_id_fk} - ${zonalRailwayDetails.work_short_name}" readonly id="work_value"/>
											    <label for="work_value"> Work <span class="required">*</span></label>
			                                    <input type="hidden" name="work_id_fk" id="work_id_fk" value="${zonalRailwayDetails.work_id_fk}" readonly />
			                              </div>
		                             <!--  </div>  -->
                                 </c:if>
                            </div>
                            <c:if test="${action eq 'add'}">	
     						<div class="row">
                                <div class="col s12 m8 l12 input-field offset-m2">
                                	<textarea  name="sub_work" id="sub_work" class='pmis-textarea'></textarea>
                                	<label for="sub_work">Sub Work</label>
                                </div>	
                            </div>
							</c:if>
							<c:if test="${action eq 'edit'}">	
     						<div class="row">
                                <div class="col s12 m8 l12 input-field offset-m2">
                                	<textarea placeholder="Sub Work" name="sub_work" id="sub_work" class='pmis-textarea'>${zonalRailwayDetails.sub_work}</textarea>
	                                <label for="sub_work">Sub Work</label>
                                </div>	
                            </div>
							</c:if>
                            <div class="row">
                             <c:if test="${action eq 'add'}">
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label fs-sm-8rem">Executing Agency <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="execution_agency_railway_fk" name="execution_agency_railway_fk">
                                        <option value="" >Select</option>
                                         <c:forEach var="obj" items="${railwayList }">
		                                      	   <option value= "${obj.execution_agency_railway_fk}"<c:if test="${zonalRailwayDetails.execution_agency_railway_fk eq obj.execution_agency_railway_fk}">selected</c:if>>${obj.execution_agency_railway_fk}<c:if test="${not empty obj.railway_name}"> - </c:if> ${obj.railway_name }</option>
		                                 </c:forEach>
                                    </select>
                                    <span id="execution_agency_railway_fkError" class="error-msg" ></span>
                                 </div>
                                 <!-- <div class="col m4 hide-on-small-only"></div> -->
                               <!--   <div class="col s6 m4 input-field">
                                    <input type="text" id="contract_id" name="contract_id"   />
                                     <label for="contract_id">Sub Work ID <span class="required">*</span>:</label>
                                     <span id="contract_idError" class="error-msg"></span>
                                </div> -->
                                </c:if>	
                                 <c:if test="${action eq 'edit'}">
                                 <div class="col s6 m4 l4 input-field offset-m2"> 
                                    <input type="text"  value="${zonalRailwayDetails.execution_agency_railway_fk} - ${zonalRailwayDetails.railway_name}" readonly id="execution_agency"/>
								     <label for="execution_agency" class="fs-sm-8rem">Executing Agency <span class="required">*</span>:</label>
			                     </div>
			                     
                                </c:if>	                                
                            
                               <!--  <div class="col s12 m8 input-field"> 
                                    <div class="row">-->
                                        <div class="col s6 m4 l4 input-field offset-m2">
                                            <p class="searchable_label">Source of Funds </p>
                                            <select class="searchable" id="source_of_funds" name="source_of_funds">
                                                <option value="" >Select</option>
                                               <c:forEach var="obj" items="${sourceOfFundList }">
		                                      	   <option value= "${obj.source_of_funds}"<c:if test="${zonalRailwayDetails.source_of_funds eq obj.source_of_funds}">selected</c:if>>${obj.source_of_funds}</option>
		                                        </c:forEach>
                                            </select>
                                        </div>
                                         <div class="col s12 m4 l4 input-field">
                                            <p class="searchable_label fs-sm-8rem">Nodal Officer in MRVC </p>
                                            <select class="searchable" id="responsible_person_user_fk" name="responsible_person_user_fk">
                                                <option value="" >Select</option>
                                                 <c:forEach var="obj" items="${usersList }"> 
			                                    	  <option value="${obj.responsible_person_user_fk }" <c:if test="${zonalRailwayDetails.responsible_person_user_fk eq obj.responsible_person_user_fk}">selected</c:if>> ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                     </c:forEach> 
                                            </select>
                                        </div>
                                      
                                   <!--  </div>
                                </div> -->
                            </div>
						<div class="row">
							<div class="col s12 m4 l4 input-field offset-m2">
								<p class="searchable_label mb-lg-5px">Status</p>
								<select class="searchable" id="status_fk" name="status_fk">
									<option value="">Select</option>
									<c:forEach var="obj" items="${statusList }">
										<option value="${obj.status_fk}"
											<c:if test="${zonalRailwayDetails.status_fk eq obj.status_fk}">selected</c:if>>${obj.status_fk}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col s8 m3 l3 input-field offset-m2">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="sanction_cost" name="sanction_cost" type="number" class="validate" min="0.01" step="0.01" value="${zonalRailwayDetails.sanction_cost }">
                                    <label for="sanction_cost">Sanction Cost</label>
                                    <span id="sanction_costError" class="error-msg"></span>
                                </div>
                                <div class="col s4 m1 l1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="sanction_cost_units" name="sanction_cost_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${zonalRailwayDetails.sanction_cost_units eq obj.value }">selected</c:if> <c:if test="${empty zonalRailwayDetails.sanction_cost_units}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="sanction_cost_unitsError" class="error-msg" ></span>
                               	</div>
                               	<div class="col s8 m3 l3 input-field">
                                     <i class="material-icons prefix center-align">₹</i>
                                    <input id="latest_revised_cost" name="latest_revised_cost" type="number" min="0.01" step="0.01"  value="${zonalRailwayDetails.latest_revised_cost }"
                                        class="validate">
                                    <label for="latest_revised_cost" class="fs-sm-8rem">Latest Revised Cost</label>
                                    <span id="latest_revised_costError" class="error-msg"></span>
                                </div>
                                <div class="col s4 m1 l1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="latest_revised_cost_units" name="latest_revised_cost_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${zonalRailwayDetails.latest_revised_cost_units eq obj.value }">selected</c:if><c:if test="${empty zonalRailwayDetails.latest_revised_cost_units}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="latest_revised_cost_unitsError" class="error-msg" ></span>
                               	</div>
							
						</div>

						

                            <div class="row">
                                <div class="col s9 m3 l3 input-field sm-width-redused">
                                     <i class="material-icons prefix center-align">₹</i>	
                                    <input id="cumilative_expenditure" name="cumulative_expenditure_upto_last_finacial_year" type="number" min="0.01" step="0.01" value="${zonalRailwayDetails.cumulative_expenditure_upto_last_finacial_year }"
                                        class="validate">
                                    <label for="cumilative_expenditure" class="fs11px lh14">Cumulative Expenditure upto Last Financial Year </label>
                                    <span id="cumulative_expenditure_upto_last_finacial_yearError" class="error-msg"></span>
                                </div>
                                <div class="col s3 m1 l1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="cumilative_expenditure_units" name="cumilative_expenditure_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${zonalRailwayDetails.cumilative_expenditure_units eq obj.value }">selected</c:if><c:if test="${empty zonalRailwayDetails.cumilative_expenditure_units}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="cumilative_expenditure_unitsError" class="error-msg" ></span>
                               	</div>
                               	 <div class="col s12 m3 l3 input-field ">
                                     <i class="material-icons prefix center-align">₹</i>	
                                    <input id="cum_actual_expenditure" name="cum_actual_expenditure" type="number" min="0.01" step="0.01" value="${zonalRailwayDetails.cum_actual_expenditure }" 	
                                        class="validate">
                                    <label for="cum_actual_expenditure" class="fs11px lh14">Cumulative Actual Expenditure</label>
                                    <span id="cum_actual_expenditure_crError" class="error-msg"></span>
                                </div>
                                <div class="col s4 m1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="cum_actual_expenditure_units" name="cum_actual_expenditure_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${zonalRailwayDetails.cum_actual_expenditure_units eq obj.value }">selected</c:if><c:if test="${empty zonalRailwayDetails.cum_actual_expenditure_units}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="cumilative_actual_expenditure_unitsError" class="error-msg" ></span>
                               	</div>
                                <div class="col s8 m3 l3 input-field offset-m2">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="completion_cost" name="completion_cost" type="number" min="0.01" step="0.01" value="${zonalRailwayDetails.completion_cost }"
                                        class="validate">
                                    <label for="completion_cost">Completion Cost</label>
                                    <span id="completion_costError" class="error-msg"></span>
                                </div>
                                 <div class="col s4 m1 l1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="completion_cost_units" name="completion_cost_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${zonalRailwayDetails.completion_cost_units eq obj.value }">selected</c:if><c:if test="${empty zonalRailwayDetails.completion_cost_units}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="completion_cost_unitsError" class="error-msg" ></span>
                               	</div>                                
                            </div>

							<div class="row">
								 
                               
                              <%--     --%>  
                               	</div>
                            <div class="row">
                                <div class="col s12 m8 l12 input-field offset-m2">
                                    <div class="row">
                                        <div class="col s6 m4 l4 input-field">
                                            <input id="actual_start" name="actual_start" type="text" value="${zonalRailwayDetails.actual_start }"
                                                class="validate datepicker">
                                            <label for="actual_start">Actual Start</label>
                                            <button type="button" id="actual_start_icon"
                                                class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                        </div> 
                                        <div class="col s6 m4 l4 input-field">
                                            <input id="expected_finish" name="expected_finish" type="text"  value="${zonalRailwayDetails.expected_finish }"
                                                class="validate datepicker">
                                            <label for="expected_finish" class="fs-sm-67rem">Target For Completion</label>
                                            <button type="button" id="expected_finish_icon"
                                                class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col s6 m4 l4 input-field">
                                            <input id="actual_finish" name="actual_finish" type="text"  value="${zonalRailwayDetails.actual_finish }"
                                                class="validate datepicker">
                                            <label for="actual_finish">Actual Finish</label>
                                            <button type="button" id="actual_finish_icon"
                                                class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col s6 m4 l4 input-field">
								<input id="asOnDate" type="text" class="validate datepicker"
									value="${zonalRailwayDetails.as_on_date }" name="as_on_date">
								<label for="asOnDate">As on Date</label>
								<button type="button" id="asOnDate_icon" class="datepicker-button">
									<i class="fa fa-calendar"></i>
								</button>
							</div>  
                            <div class="col s6 m4 l4 input-field d-none">
                                    <input type="hidden" id="contract_id" name="contract_id"   value="${zonalRailwayDetails.contract_id }" readonly />
                                     <!-- <label for="contract_id">Sub Work ID <span class="required">*</span>:</label> -->
                                </div>
                                    </div>
                                </div>
                            </div>
                           
                        </div>

                        <div class="row fixed-width" style="margin-bottom: 10px;">
                            <h5 class="center-align">Progress details</h5>
                            <div class="table-inside">
                                <table id="zonal_railway_table" class="mdl-data-table mobile_responsible_table">
                                    <thead>
                                        <tr>
                                            <th rowspan="2" class="fw-125">Month</th>
                                            <th colspan="2">Financial Progress </th>
                                            <th colspan="2">Physical Progress</th>
                                            <th rowspan="2">Progress</th>
                                            <th rowspan="2">Issue</th>
                                            <th rowspan="2">Assistance <br>Required</th>
                                            <th rowspan="2">Action</th>
                                        </tr>
                                        <tr>
                                            <!-- <th>Cum Actual <br>Current FY (in Cr)</th> -->
                                            <th class="fw-110">planned %</th>
                                           <!--  <th>Cum Actual Expenditure (in Cr)</th> -->
                                            <th class="fw-110">Actual %</th>
                                            <th class="fw-110">planned %</th>
                                            <th class="fw-110">Actual %</th>
                                        </tr>
                                    </thead>
                                    <tbody id="progressTableBody">
                                      <c:choose>
		                                      	 <c:when test="${not empty zonalRailwayDetails.zonalRailway && fn:length(zonalRailwayDetails.zonalRailway) gt 0 }">
		                                       	 <c:forEach var="pObj" items="${zonalRailwayDetails.zonalRailway }" varStatus="index"> 
                                       	 
                                        <tr id="progressRow${index.count }">
                                            <td data-head="Month" class="input-field"><input type="hidden" name= "progress_ids" id="progress_ids${index.count }" value="${pObj.progress_id }"/>
                                                <input id="months${index.count }" name="months"  class="month" type="month" class="validate" value="${pObj.month }"
                                                    placeholder="Month">
                                            </td>
                                            <td class="hideCol" data-head="Expenditure"></td>
                                          <!--   <td data-head="Cum Actual Current FY (in Cr)" class="input-field">
                                                <input id="cum_actual_expenditure_fy_crs${index.count }" name="cum_actual_expenditure_fy_crs" value="${pObj.cum_actual_expenditure_fy_cr }"
                                                    type="number" class="validate" min="0" 
                                                    placeholder="Amount">
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td> -->
                                            <td data-head="Cum planned %" class="input-field">
                                                <input id="cum_planned_expenditure_pers${index.count }" name="cum_planned_expenditure_pers" value="${pObj.cum_planned_expenditure_per }"
                                                    type="number" class="validate" 
                                                    placeholder="Cum Planned %">
                                                    <span class="units">%</span>
                                            </td>
                                           <!--  <td data-head="Cum Actual (in Cr)" class="input-field">
                                                <input id="cum_actual_expenditure_crs${index.count }" name="cum_actual_expenditure_crs" type="number" class="validate" value="${pObj.cum_actual_expenditure }"
                                                    min="0"  placeholder="cum Actual">
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td> -->
                                                                                       
                                            <td data-head="Cum Actual %" class="input-field">
                                                <input id="cum_actual_expenditure_pers${index.count }" name="cum_actual_expenditure_pers" type="number" value="${pObj.cum_actual_expenditure_per }"
                                                    class="validate"  placeholder="cum Actual %">
                                                    <span class="units">%</span>
                                            </td>
                                            <td class="hideCol" data-head="Physical Progress"></td>
                                            <td data-head="Cum planned %" class="input-field">
                                                <input id="cum_planned_physical_progress_pers${index.count }"
                                                    name="cum_planned_physical_progress_pers" type="number" class="validate" value="${pObj.cum_planned_physical_progress_per }"
                                                     placeholder="Cum Planned %">
                                                    <span class="units">%</span>
                                            </td>
                                            <td data-head="Cum Actual %" class="input-field">
                                                <input id="cum_actual_physical_progress_pers${index.count }"
                                                    name="cum_actual_physical_progress_pers" type="number" class="validate" value="${pObj.cum_actual_physical_progress_per }"
                                                     placeholder="cum Actual %">
                                                    <span class="units">%</span>
                                            </td>
                                            <td class="hideCol" data-head=" "></td>
                                            <td data-head="Progress" class="input-field">
                                                <input id="progresss${index.count }" name="progresss" type="text" class="validate" value="${pObj.progress }"
                                                    placeholder="Progress">
                                            </td>
                                            <td data-head="Issue" class="input-field">
                                                <input id="issues${index.count }" name="issues" type="text" class="validate" value="${pObj.issue }"
                                                    placeholder="Issue">
                                            </td>
                                            <td data-head="Assistance Required" class="input-field">
                                                 <input id="assistance_requireds${index.count }" name="assistance_requireds" type="text" class="validate" value="${pObj.assistance_required }"
                                                    placeholder="Assistance Requireds">
                                            </td>
                                            <td class="mobile_btn_close">
                                                <a onclick="removeProgress('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                         
                                      </c:forEach>
                               	    </c:when>
                                	<c:otherwise>
                                         <tr id="progressRow0">
                                            <td data-head="Month" class="input-field"><input type="hidden" name= "progress_ids" id="progress_ids0" />
                                                <input id="months0" name="months" type="month" class="validate" class="month"
                                                    placeholder="Month">
                                            </td>
                                            <!-- <td data-head="Cum Actual Current FY (in Cr)" class="input-field">
                                                <input id="cum_actual_expenditure_fy_crs0" name="cum_actual_expenditure_fy_crs" value=""
                                                    type="number" class="validate" min="0" 
                                                    placeholder="Amount">
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td> -->
                                            <td data-head="Cum planned %" class="input-field">
                                                <input id="cum_planned_expenditure_pers0" name="cum_planned_expenditure_pers"  value=""
                                                    type="number" class="validate" min="0" step="0.01"
                                                    placeholder="Cum Planned %">
                                                    <span class="units">%</span>
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td>
                                            <!-- <td data-head="Cum Actual (in Cr)" class="input-field">
                                                <input id="cum_actual_expenditure_crs0" name="cum_actual_expenditure_crs" type="number" class="validate"  value=""
                                                    min="0" placeholder="cum Actual">
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td> -->
                                            <td data-head="Cum Actual %" class="input-field">
                                                <input id="cum_actual_expenditure_pers0" name="cum_actual_expenditure_pers" type="number"   value=""
                                                    class="validate" min="0.01" step="0.01" placeholder="cum Actual %">
                                                    <span class="units">%</span>
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td>
                                            <td data-head="Cum planned %" class="input-field">
                                                <input id="cum_planned_physical_progress_pers0"
                                                    name="cum_planned_physical_progress_pers" type="number" class="validate"  value=""
                                                    min="0.01" step="0.01" placeholder="Cum Planned %">
                                                    <span class="units">%</span>
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td>
                                            <td data-head="Cum Actual %" class="input-field">
                                                <input id="cum_actual_physical_progress_pers0"
                                                    name="cum_actual_physical_progress_pers" type="number" class="validate"  value=""
                                                    min="0.01" step="0.01" placeholder="cum Actual %">
                                                    <span class="units">%</span>
                                                    <span id="erroerArea" class="error-msg"></span>
                                            </td>
                                            <td data-head="Progress" class="input-field">
                                                <input id="progresss0" name="progresss" type="text" class="validate" 
                                                    placeholder="Progress">
                                            </td>
                                            <td data-head="Issue" class="input-field">
                                                <input id="issues0" name="issues" type="text" class="validate" 
                                                    placeholder="Issue">
                                            </td>
                                            <td data-head="Assistance Required" class="input-field"> <input id="assistance_requireds0" name="assistance_requireds" type="text" class="validate"
                                                    placeholder="Assistance Requireds">
                                            </td>
                                            <td class="mobile_btn_close">
                                                <a onclick="removeProgress('0');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
 									</c:otherwise>
                                   </c:choose>
                                 </tbody>
                               </table>
							   <table class="mdl-data-table">
                                   <tbody id="safetyBody">                                          
	                                  <tr>
											 <td colspan="5" > <a type="button" class="btn waves-effect waves-light bg-m t-c "  onclick="addProgressRow()"> <i
	                                                          class="fa fa-plus"></i></a>
	                                  </tr>
                                </tbody>
                               </table>
                                <c:choose>
                                    <c:when test="${not empty zonalRailwayDetails.zonalRailway && fn:length(zonalRailwayDetails.zonalRailway) gt 0 }">
                                		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(zonalRailwayDetails.zonalRailway) }" />
                                	</c:when>
                                 	<c:otherwise>
                                 		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                 	</c:otherwise>
                                 </c:choose> 
                            </div>
                        </div>

                        <!-- Modal Structure -->
                        <div id="modal1" class="modal">
                            <div class="modal-content center-align">
                                <h6 class="headbg bg-s p-2">Upload File</h6>
                                <div class="container">
                                    <div class="row">
                                        <div class="col m12 s12">
                                            <p>Select File to Upload</p>
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
                                        <div class="col s6 m6 center-align">
                                            <div class=" m-1">
                                                <button  class="btn waves-effect waves-light bg-m ">Submit</button>
                                            </div>
                                        </div>
                                        <div class="col s6 m6 center-align">
                                            <div class=" m-1">
                                                <button  class="btn waves-effect waves-light bg-s modal-close">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr offset-m2 center-align">
                                     <div class=" m-1">
                                         <c:if test="${action eq 'edit'}">
                                           <button type="button" onclick="updateZonalRailway();" class="btn waves-effect waves-light bg-m w7em">Update</button>
                                         </c:if>
										 <c:if test="${action eq 'add'}"> 
					                       <button type="button" onclick="addZonalRailway();" class="btn waves-effect waves-light bg-m w7em">Add</button>
										 </c:if>
                                    </div>
                                </div>                              
                                <div class="col s6 m4 l6 mt-brdr center-align">
                                    <div class=" m-1">
                                            <a href="<%=request.getContextPath()%>/zonal-railway" class="btn waves-effect waves-light bg-s w7em">Cancel</a>
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
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

    <script>
    /*     $(document).on('focus', '.datepicker', function () {
            $(this).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            })
        });      */
  /*       let date_pickers = document.querySelectorAll('.datepicker');
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
        $(document).on('focus', '.datepicker-btn', function () {
            var dateId = $(this).attr('id').split("__")[0];
            $('#' + dateId).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            });
            $('#' + dateId).datepicker('open');
        }); */

        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });

        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForZonalRailwayForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${zonalRailwayDetails.work_id_fk}";
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
            }else{
            	$(".page-loader").hide();
            }
        }
        
     <%--    function resetRailways(workId){
        	$(".page-loader").show();
            $("#execution_agency_railway_fk option:not(:first)").remove();
            if ($.trim(workId) != "") {
                var myParams = { work_id_fk: workId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getRailwayListForZonalRailwayForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var railway_name = '';
                                if ($.trim(val.railway_name) != '') { railway_name = ' - ' + $.trim(val.railway_name) }
                              
                                    $("#execution_agency_railway_fk").append('<option value="' + val.execution_agency_railway_fk + '">' + $.trim(val.execution_agency_railway_fk) + $.trim(railway_name) + '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        	
        } --%>
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		    $("#project_id_fk").valid();
       		}
       		
        } 
        
        function addProgressRow() {
       	    var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="progressRow'+rNo+'"> <td data-head="Month" class="input-field"><input type="hidden" name= "progress_ids" id="progress_ids' + rNo + '" /> <input id="months' + rNo + '" name="months" type="month" class="validate" placeholder="Month"> </td>' +
                '<td class="hideCol" data-head="Expenditure"></td>' +
                '<td data-head="Cum planned %" class="input-field"> <input id="cum_planned_expenditure_pers' + rNo + '" name="cum_planned_expenditure_pers" type="number" value="" class="validate" placeholder="Cum Planned %"> <span class="units">%</span>' +
                '</td><td data-head="Cum Actual %" class="input-field"> <input id="cum_actual_expenditure_pers' + rNo + '" name="cum_actual_expenditure_pers" type="number" class="validate"  placeholder="cum Actual %"><span class="units">%</span> </td>' +
                '<td class="hideCol" data-head="Physical Progress"></td><td data-head="Cum planned %" class="input-field"> <input id="cum_planned_physical_progress_pers" name="cum_planned_physical_progress_pers" type="number" class="validate"  placeholder="Cum Planned %"><span class="units">%</span>' +
                '</td> <td data-head="Cum Actual %" class="input-field"> <input id="cum_actual_physical_progress_pers' + rNo + '" name="cum_actual_physical_progress_pers" type="number" class="validate"  placeholder="cum Actual %"> <span class="units">%</span>' +
                '</td> <td class="hideCol" data-head=" "></td><td data-head="Progress" class="input-field"> <input id="progresss' + rNo + '" name="progresss" type="text" class="validate" placeholder="Progress"> </td> <td data-head="Issue" class="input-field">' +
                '<input id="issues' + rNo + '" name="issues" type="text" class="validate" placeholder="Issue"> </td> <td data-head="Assistance Required" class="input-field">' +
                '<input id="assistance_requireds' + rNo + '" name="assistance_requireds" type="text" class="validate" placeholder="Assistance Requireds"> </td>' +
                '<td class="mobile_btn_close"> <a onclick="removeProgress(' + rNo + ');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
            $('#progressTableBody').append(html);
			$("#rowNo").val(rNo);
			
        }
       
        function removeProgress(rowNo){
			$("#progressRow"+rowNo).remove();
		}
        

        function addZonalRailway(){
        	 if(validator.form()){ // validation perform
	        	$(".page-loader").show();
	        	var sanction_cost = $('#sanction_cost').val();
	        	var latest_revised_cost = $('#latest_revised_cost').val();
	        	var cumulative_expenditure = $('#cumilative_expenditure').val();
	        	var completion_cost = $('#completion_cost').val();
	  			if(sanction_cost == ""){
	  				$('#sanction_cost_units').val("");
	  			}
	  			if(latest_revised_cost == ""){
	  				$('#latest_revised_cost_units').val("");
	  			}
	  			if(cumulative_expenditure == ""){
	  				$('#cumilative_expenditure_units').val("");
	  			}
	  			if(completion_cost == ""){
	  				$('#completion_cost_units').val("");
	  			}
	        	$('form input[name=months]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_fy_crs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_planned_expenditure_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_crs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_planned_physical_progress_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_physical_progress_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=progresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=issues]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=assistance_requireds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });

	  			document.getElementById("zonalRailwayForm").submit();			
   	 	 }
        }
        function updateZonalRailway(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	        	var sanction_cost = $('#sanction_cost').val();
	        	var latest_revised_cost = $('#latest_revised_cost').val();
	        	var cumulative_expenditure = $('#cumilative_expenditure').val();
	        	var completion_cost = $('#completion_cost').val();
	  			if(sanction_cost == ""){
	  				$('#sanction_cost_units').val("");
	  			}
	  			if(latest_revised_cost == ""){
	  				$('#latest_revised_cost_units').val("");
	  			}
	  			if(cumulative_expenditure == ""){
	  				$('#cumilative_expenditure_units').val("");
	  			}
	  			if(completion_cost == ""){
	  				$('#completion_cost_units').val("");
	  			}
	        	$('form input[name=months]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_fy_crs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_planned_expenditure_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_crs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_planned_physical_progress_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_physical_progress_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=progresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=issues]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=assistance_requireds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });

	   			document.getElementById("zonalRailwayForm").submit();	
        	}
        }
        
        var validator =	$('#zonalRailwayForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "project_id_fk": {
	  			 		  required: true
	  			 	  },"work_id_fk": {
	  			 		  required: true
	  			 	  },"execution_agency_railway_fk": {
	  			 		  required: true
	  			 	  },"sanction_cost": {
	  			 		  required: false
	  			 	  }	,"latest_revised_cost": {
	  			 		  required: false
	  			 	  }	,"cumulative_expenditure_upto_last_finacial_year": {
	  			 		  required: false
	  			 	  }	,"completion_cost": {
	  			 		  required: false
	  			 	  }	,"contract_id": {
	  			 		  required: true
	  			 	  },"sanction_cost_units":{
        		 		 required: function(element){
        		             return $("#sanction_cost").val()!="";
        		         }
        		 	  },"latest_revised_cost_units":{
        		 		 required: function(element){
        		             return $("#latest_revised_cost").val()!="";
        		         }
        		 	  },"cumilative_expenditure_units":{
        		 		 required: function(element){
        		 			 return $("#cumilative_expenditure").val()!="";
        		         }
        		 	  },"completion_cost_units":{
        		 		 required: function(element){
        		             return $("#completion_cost").val()!="";
        		         }
        		 	  }		
	  		 	},
	  		    messages: {
	  		 		   "project_id_fk": {
	  			 		  required: 'Required'								
	  			 	  },"work_id_fk": {
	  			 		  required: 'Required'
	  			 	  }	,"execution_agency_railway_fk": {
	  			 		  required: 'Required'
	  			 	  },"sanction_cost": {
	  			 		  required: 'Required'
	  			 	  },"latest_revised_cost": {
	  			 		  required: 'Required'
	  			 	  },"cumulative_expenditure_upto_last_finacial_year": {
	  			 		  required: 'Required'
	  			 	  },"completion_cost": {
	  			 		  required: 'Required'
	  			 	  },"contract_id": {
	  			 		  required: 'Required'
	  			 	  },"sanction_cost_units": {
	  			 		  required: 'Required'
	  			 	  },"latest_revised_cost_units": {
	  			 		  required: 'Required'
	  			 	  },"cumilative_expenditure_units": {
	  			 		  required: 'Required'
	  			 	  },"completion_cost_units": {
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
					 }else if(element.attr("id") == "execution_agency_railway_fk" ){
					     document.getElementById("execution_agency_railway_fkError").innerHTML="";
				 	     error.appendTo('#execution_agency_railway_fkError');
					 }else if(element.attr("id") == "sanction_cost" ){
					     document.getElementById("sanction_costError").innerHTML="";
				 	     error.appendTo('#sanction_costError');
					 }else if(element.attr("id") == "latest_revised_cost" ){
					     document.getElementById("latest_revised_costError").innerHTML="";
				 	     error.appendTo('#latest_revised_costError');
					 }else if(element.attr("id") == "cumilative_expenditure" ){
					     document.getElementById("cumulative_expenditure_upto_last_finacial_yearError").innerHTML="";
				 	     error.appendTo('#cumulative_expenditure_upto_last_finacial_yearError');
					 }else if(element.attr("id") == "completion_cost" ){
					     document.getElementById("completion_costError").innerHTML="";
				 	     error.appendTo('#completion_costError');
					 }else if(element.attr("id") == "contract_id" ){
					     document.getElementById("contract_idError").innerHTML="";
				 	     error.appendTo('#contract_idError');
					 }else if(element.attr("id") == "sanction_cost_units" ){
					     document.getElementById("sanction_cost_unitsError").innerHTML="";
					     error.appendTo('#sanction_cost_unitsError');
					 }else if(element.attr("id") == "latest_revised_cost_units" ){
					     document.getElementById("latest_revised_cost_unitsError").innerHTML="";
					     error.appendTo('#latest_revised_cost_unitsError');
					 }else if(element.attr("id") == "cumilative_expenditure_units" ){
					     document.getElementById("cumilative_expenditure_unitsError").innerHTML="";
					     error.appendTo('#cumilative_expenditure_unitsError');
					 }else if(element.attr("id") == "completion_cost_units" ){
					     document.getElementById("completion_cost_unitsError").innerHTML="";
					     error.appendTo('#completion_cost_unitsError');
					 }else{
	 					 error.appendTo('#'+element.attr('id')+'+.error-msg');	 					 
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