<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>
     	 <c:if test="${action eq 'edit'}">Update Risk </c:if>
		 <c:if test="${action eq 'add'}"> Add Risk </c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
        <style>
        textarea::placeholder {
            color: #444;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td {
            position: relative;
        }

        #riskReview {
            border: 1px solid #ddd;
        }

        #riskReview .datepicker~button {
            right:5px;
        } 

        #riskReview td .select2-container {
            width: 120px;
            max-width: 120px;
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

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
          .modal-header {
            text-align: center;
            background-color: #999999;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
            font-size: 24px;
        }

        .update-table {
            border: 1px solid #ddd;
        }

        table.update-table tbody td .datepicker~button {
            top: 15px !important;
            right: 1px;
        }

        table.update-table tbody td .input-field {
            margin-top: 0;
            margin-bottom: 0;
        }
         table.update-table tbody td .modal.datepicker-modal.open {
            width: 85% !important;
        }
        
        .fw-60 {
            max-width: 60px;
            width: 60px !important;
        }
        .fw-110{
        	 max-width: 100px;
            width: 100px !important;
        }        
        .fw-150 {
            max-width: 150px;
            width: 150px !important;
        }
        .fw-180 {
            max-width: 180px;
            width: 180px !important;
        }
       tbody .select2-container--default .select2-selection--single {
        	background-color:transparent;
        }
         .modal:not(.datepicker-modal){
        	max-height:90%;
        	width:62%;
        	min-height:75%;
        }
       
        .datepicker-table th, 
        .datepicker-table td,
        .mdl-data-table tbody td .datepicker-table th,
        .mdl-data-table tbody td .datepicker-table .datepicker-row td{
        	padding:0 !important;
        	height:44px !important;
        }
        .mdl-data-table tbody td .datepicker-table .datepicker-row{
        	bottom-border:none;
        }
        
	    .error-msg label{color:red!important;}   
		
    </style>
    
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
  <!-- card  -->
      <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                               <h6>
                                 	<c:if test="${action eq 'edit'}">Update Risk </c:if>
		 							<c:if test="${action eq 'add'}"> Add Risk </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                  		 <c:if test="${action eq 'edit'}">
							<form action="<%=request.getContextPath() %>/update-risk" id="riskForm" name="riskForm" method="post" class="form-horizontal" role="form">
						</c:if>
						<c:if test="${action eq 'add'}">
							<form action="<%=request.getContextPath() %>/add-risk" id="riskForm" name="riskForm" method="post" class="form-horizontal" role="form">
						</c:if>
                        <div class="container container-no-margin">
                            <c:if test="${action eq 'add'}">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m4 s12 input-field">
                                    <p class="searchable_label">Project</p>
                                       <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  onchange="getWorksList(this.value);">
                                 	     <option value="" >Select</option>
                                   		 <c:forEach var="obj" items="${projectsList }">
                                      		<option value="${obj.project_id_fk }" <c:if test="${riskDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                   		 </c:forEach>
                                     </select>
                            		 <span id="project_idError" class="error-msg" ></span>
                                </div>
                                <div class="col m4 s12 input-field">
                                    <p class="searchable_label">Work</p>
                                     <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"  >
                              		  <option value="">Select</option>
                           		 	</select>
                            		 <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            </c:if>
                            <c:if test="${action eq 'edit'}">	
                             <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                               
                                <div class="col s12 m4 input-field">
									<p class="searchable_label">Project</p>
                                         	 	<input type="text" name="project_id_fk" id="project_id_fk" value="${riskDetails.project_id_fk}- ${riskDetails.project_name}" readonly />
							    </div> 
                                <div class="col s12 m4 input-field"> 
								    <p class="searchable_label"> Work </p>
                                         	 	<input type="text" name="work_id_fk" id="work_id_fk" value="${riskDetails.work_id_fk}- ${riskDetails.work_name}" readonly />
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                             </c:if>
                            <c:if test="${action eq 'add'}">
                            <div class="row">
                            <input  type="hidden"   name="risk_id_pk" value="${riskDetails.risk_id_pk }" />
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="risk_id" name="risk_id" type="text" class="validate" value="${riskDetails.risk_id }">
                                    <label for="riskid">Risk ID </label>
                                    <span id="risk_idError" class="error-msg" ></span>
                                </div>
                                 <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Area</p>
                                    <select class="searchable validate-dropdown" id="area" name="area"  onchange="getSubAreasList();">
                                 	     <option value="" >Select</option>
                                   	
                                     </select>
                            		 <span id="areaError" class="error-msg" ></span>
                                </div>
                               <!--  <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="date_of_identification" name="date_of_identification" value="${riskDetails.date_of_identification }">
                                    <label for="date_of_identification">Date of Identification</label>
                                    <button id="date_of_identification_icon"><i class="fa fa-calendar"></i></button>
                                </div> -->
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            </c:if>
                            <c:if test="${action eq 'edit'}">
                            <div class="row">
                            <input  type="hidden"   name="risk_id_pk" value="${riskDetails.risk_id_pk }" />
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="risk_id" name="risk_id" type="text" class="validate" value="${riskDetails.risk_id }" readonly>
                                    <label for="riskid">Risk ID </label>
                                    <span id="risk_idError" class="error-msg" ></span>
                                </div>
                                 <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Area</p>
                                      <input id="area" name="area" type="text" class="validate" value="${riskDetails.area }" readonly>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            </c:if>
                            
                            
 							<c:if test="${action eq 'edit'}">
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                               
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Sub Area</p>
                                   	<input id="sub_area_fk" name="sub_area_fk" type="text" class="validate" value="${riskDetails.sub_area_fk }" readonly>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            </c:if>
                            <c:if test="${action eq 'add'}">
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                               
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Sub Area</p>
                                    <select class="searchable validate-dropdown" id="sub_area_fk" name="sub_area_fk" onchange="getAreasList();" >
                              		  <option value="" >Select</option>
                                   		 
                           		 	</select>
                            		<span id="sub_area_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            </c:if>
                        </div>
                        <div class="row fixed-width" style="margin-bottom: 40px;">
                            <h5 class="center-align">Risk Review</h5>
                            <div class="table-inside">
                                <table id="riskReview" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="fw-180">Assessment Date</th>
                                            <th>Owner</th>
                                            <th>Responsible <br> Person</th>
                                            <th>Priority</th>
                                            <th>Probability</th>
                                            <th>Impact</th>
                                            <th>Mitigation <br> Plan</th>
                                            <th>Update Action</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                     <tbody id="riskRevisionBody">
                                      <c:choose>
                                      	 <c:when test="${not empty riskDetails.risks && fn:length(riskDetails.risks) gt 0 }">
                                       	 <c:forEach var="rObj" items="${riskDetails.risks }" varStatus="index"> 
                                         
                                           <tr id="riskReviewRow${index.count }">
                                            <td >
                                            <div class="input-field">
                                             <input type="hidden" name="risk_revision_ids" id="risk_revision_ids${index.count }"
																										 value="${rObj.risk_revision_id}" />
                                                <input id="dates${index.count }" name="dates" type="text" class="validate datepicker" value="${rObj.date }"
                                                    placeholder="Date">
                                                <button type="button" id="reveiw_date_icon${index.count }"><i
                                                        class="fa fa-calendar"></i></button>
                                             </div>
                                            </td>
                                            <td>
                                                <input id="owners${index.count }" type="text"  name="owners" class="validate" placeholder="Owner" value="${rObj.owner }">
                                            </td>
                                            <td>
                                                <input id="responsible_persons${index.count }" name="responsible_persons" type="text" class="validate" value="${rObj.responsible_person }"
                                                    placeholder="Responsible Person">
                                            </td>
                                            <td>
                                                <select id="prioritys${index.count }" name="prioritys" class="searchable validate-dropdown fw-110">
                                                    <option value="" >Priority</option>
                                                    <c:forEach var="obj" items="${prioritiesList }">
                                      					<option value="${obj.priority }" <c:if test="${rObj.priority_fk eq obj.priority}">selected</c:if>>${obj.priority}</option>
                                   					 </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select class="searchable fw-60" id="probabilitys${index.count }" name="probabilitys">
                                                        <option value="">Probability</option>
                                                    <option value="1" <c:if test="${rObj.probability eq 1}">selected</c:if>>1</option>
                                                    <option value="3" <c:if test="${rObj.probability eq 3}">selected</c:if>>3</option>
                                                    <option value="5" <c:if test="${rObj.probability eq 5}">selected</c:if>>5</option>
                                                    
                                                </select>
                                            </td>

                                            <td>
                                                <select class="searchable fw-60" id="impacts${index.count }" name="impacts">
                                                    <option value="" >Impact</option>
                                                   <option value="1" <c:if test="${rObj.impact eq 1}">selected</c:if>>1</option>
                                                    <option value="3" <c:if test="${rObj.impact eq 3}">selected</c:if>>3</option>
                                                    <option value="5" <c:if test="${rObj.impact eq 5}">selected</c:if>>5</option>
                                                </select>
                                            </td>
                                            <td>
                                                <textarea id="mitigation_plans${index.count }" name="mitigation_plans" class="materialize-textarea"
                                                    placeholder="Mitigation Plan">${rObj.mitigation_plan }</textarea>
                                            </td>
                                            <td>
                                                <a class="waves-effect waves-light btn modal-trigger bg-m t-c" onclick="showNo(this);"
                                                    href="#update_action${index.count }">Update</a>
                                                <div id="update_action${index.count }" class="modal">
                                                    <div class="modal-content">
                                                       <h4 class="modal-header">Action Taken <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h4>
                                                        <h6>Assessment Date : ${rObj.date } </h6> <br>

                                                        <div class="row fixed-width">
                                                            <div class="table-inside">
                                                                <table id="update-action-table${index.count }"
                                                                    class="mdl-data-table update-table">
                                                                    <thead>
                                                                        <tr>
                                                                            <th class="fw-150">ATR Date</th>
                                                                            <th>Action Taken</th>
                                                                            <th class="fw-60">Action</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody id="actionTableBody${index.count }">
                                                                    <c:choose>
																	 <c:when
																		test="${not empty rObj.riskActions && fn:length(rObj.riskActions) gt 0 }">
																		<c:forEach var="aObj" items="${rObj.riskActions }" varStatus="indexx">
                                                                        <tr id="actionRow${indexx.count }${index.count }">
                                                                            <td><input type="hidden" id="rowCounts${indexx.count }${index.count }" name="rowCounts" class="hide" />
                                                                           <input type="hidden" name="risk_action_ids" id="risk_action_ids${indexx.count }${index.count }"
																										 value="${aObj.risk_action_id}" />
                                                                                <div class="input-field">
                                                                                    <input id="atr_dates${indexx.count }${index.count }" name="atr_dates" type="text" class="validate datepicker" 
                                                                                     placeholder="ATR  Date" value="${ aObj.atr_date}">
                                                                                    <button type="button" id="atr_date_icon${indexx.count }${index.count }"><i  class="fa fa-calendar"></i></button>
                                                                                </div>

                                                                            </td>
                                                                            <td>
                                                                                <textarea id="action_takens${indexx.count }${index.count }" name="action_takens" class="materialize-textarea" 
                                                                                placeholder="Action Taken" style="height: 44px;">${ aObj.action_taken}</textarea>
                                                                            </td>
                                                                            <td>
                                                                                <a onclick="removeactions('${indexx.count }${index.count }');prevRow('${index.count }')" class="btn waves-effect waves-light red t-c ">
                                                                                    <i class="fa fa-close"></i></a>
                                                                            </td>
                                                                        </tr>
                                                                	    <script>
																			 var w = $('#actionTableBody${index.count } tr:last').attr("id");
																			 var value = ${indexx.count }
																			 if(value > 1){
																	            	var lastIndex = value -1;
																	          	    var lastRow = $('#actionTableBody${index.count } #rowCounts${indexx.count -1}${index.count}').prop('disabled', true);
																	            } 																								
																			 $('#actionTableBody${index.count }  #rowCounts${indexx.count }${index.count }:last').val(value);
																		</script> 
																</c:forEach>
															 </c:when>
										 					<c:otherwise>
										 					 <tr id="actionRow00${index.count }">
                                                                            <td><input type="hidden" id="rowCounts00${index.count }" name="rowCounts" value="1" class="hide" />
                                                                                <div class="input-field">
                                                                                    <input id="atr_dates00${index.count }" name="atr_dates" type="text" class="validate datepicker"  placeholder="ATR  Date">
                                                                                    <button type="button" id="atr_date_icon00${index.count }"><i  class="fa fa-calendar"></i></button>
                                                                                </div>

                                                                            </td>
                                                                            <td>
                                                                                <textarea id="action_takens00${index.count }" name="action_takens" class="materialize-textarea" placeholder="Action Taken" style="height: 44px;"></textarea>
                                                                            </td>
                                                                            <td>
                                                                                <a onclick="removeactions('00${index.count }');" class="btn waves-effect waves-light red t-c ">
                                                                                    <i class="fa fa-close"></i></a>
                                                                            </td>
                                                                        </tr>
																		</c:otherwise> 
																	</c:choose>
                                                                    </tbody>
                                                                </table>
																<table class="mdl-data-table">
																	<tbody id="actionBody">
																		<tr>
																			<td colspan="6" style="text-align: right;"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																				onclick="addRiskActionRow('${rObj.risk_revision_id}','${index.count }')"> <i class="fa fa-plus"></i></a>
																		</tr>
																	</tbody>
																</table>
																<c:choose>
																	<c:when
																		test="${not empty (rObj.riskActions) && fn:length(rObj.riskActions) gt 0 }">
																		<input type="hidden" id="trainNo" name="trainNo" value="${fn:length(rObj.riskActions) }" />
																	</c:when>
																	<c:otherwise>
																		<input type="hidden" id="trainNo" name="trainNo" value="0" />
																	</c:otherwise>
																</c:choose>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a  onclick="removeRiskRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                         
                                           </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                        <tr id="riskReviewRow0">
                                            <td>
                                                <div class ="input-field" ><input id="dates0" name="dates" type="text" class="validate datepicker"
                                                    placeholder="Assesment Date">
                                                <button type="button" id="reveiw_date_icon0"><i
                                                        class="fa fa-calendar"></i></button></div>
                                            </td>
                                            <td>
                                                <input id="owners0"  name="owners" type="text" class="validate" placeholder="Owner">
                                            </td>
                                            <td>
                                                <input id="responsible_persons0"  name="responsible_persons" type="text" class="validate"
                                                    placeholder="Responsible Person">
                                            </td>
                                            <td>
                                                <select id="prioritys0" name="prioritys" class="searchable">
                                                    <option value="">Priority</option>
                                                     <c:forEach var="obj" items="${prioritiesList }">
                                      					<option value="${obj.priority}">${obj.priority}</option>
                                   					 </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select class="searchable" id="probabilitys0"  name="probabilitys">
                                                      <option value="" >Probability</option>
                                                    <option value="1">1</option>
                                                    <option value="3">3</option>
                                                    <option value="5">5</option>
                                                </select>
                                            </td>

                                            <td>
                                                <select class="searchable" id="impacts0" name="impacts">
                                                    <option value="" >Impact</option>
                                                    <option value="1">1</option>
                                                    <option value="3">3</option>
                                                    <option value="5">5</option>
                                                </select>
                                            </td>
                                            <td>
                                                <textarea id="mitigation_plans0" name="mitigation_plans" class="materialize-textarea"
                                                    placeholder="Mitigation Plan"></textarea>
                                            </td>
                                             <td>
                                                <a class="waves-effect waves-light btn modal-trigger bg-m t-c" href="#update_action0" onclick="showNo(this);">Update</a>
                                                <div id="update_action0" class="modal">
                                                    <div class="modal-content">
                                                       <h4 class="modal-header">Action Taken <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h4>
                                                       <!--  <h6>Assessment Date (12-04-2020) </h6> --> <br>

                                                        <div class="row fixed-width">
                                                            <div class="table-inside">
                                                                <table id="update-action-table0"
                                                                    class="mdl-data-table update-table">
                                                                    <thead>
                                                                        <tr>
                                                                            <th class="fw-150">ATR Date</th>
                                                                            <th>Action Taken</th>
                                                                            <th class="fw-60">Action</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody id="actionTableBody0" >
                                                                        <tr id="actionRow0">
                                                                            <td><input type="hidden" id="rowCounts0" name="rowCounts" value="1" class="hide" />
                                                                            
                                                                                <div class="input-field">
                                                                                    <input id="atr_dates0" name="atr_dates" type="text"  class="validate datepicker" placeholder="ATR  Date">
                                                                                    <button type="button" id="atr_date0_icon"><i class="fa fa-calendar"></i></button>
                                                                                </div>

                                                                            </td>
                                                                            <td>
                                                                                <textarea id="action_takens0"  name="action_takens" class="materialize-textarea"  placeholder="Action Taken"
                                                                                    style="height: 44px;"></textarea>
                                                                            </td>
                                                                            <td>
                                                                                <a onclick="removeactions('0');" class="btn waves-effect waves-light red t-c ">
                                                                                    <i class="fa fa-close"></i></a>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
																<table class="mdl-data-table">
																	<tbody id="actionBody">
																		<tr>
																			<td colspan="6" style="text-align: right;"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																				onclick="addRiskActionRow('${rObj.risk_revision_id}','0')"> <i class="fa fa-plus"></i></a>
																		</tr>
																	</tbody>
																</table>
																<input type="hidden" id="trainNo" name="trainNo" value="0" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a onclick="removeRiskRow('0');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                         </c:otherwise>
                                     	</c:choose>
                                        
                                    </tbody>
                                </table>
                                 <table class="mdl-data-table">
                                        <tbody id="documentBody">                                          
			                                    <tr>
			  										 <td colspan="9" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addRiskRow('${rObj.risk_revision_id}')"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
  									<c:choose>
                                        <c:when test="${not empty (riskDetails.risks) && fn:length(riskDetails.risks) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(riskDetails.risks) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose> 
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <!-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <p class="searchable_label">Category</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">probability 1</option>
                                        <option value="2">probability 2</option>
                                        <option value="3">probability 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">

                                    <input id="priority" type="number" class="validate" style="margin-top: 5px;">
                                    <label for="priority">Priority</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                   <p class="searchable_label">Responsible Person </p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Status 1</option>
                                        <option value="2">Status 2</option>
                                        <option value="3">Status 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">

                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea3" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea3">Action Taken / Remarks</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="target_date"
                                        placeholder="Target Date of Mitigation">
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="mitigated_on" placeholder="Mitigated On">
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field file-field">

                                    <div class="btn bg-m">
                                        <span>Attach Risk Analysis Report</span>
                                        <input type="file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text">
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> -->


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                   <div class="center-align m-1">
									<c:if test="${action eq 'edit'}">
										<button type="button" onclick="updateRisk();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
									</c:if>
									<c:if test="${action eq 'add'}"> 
										<button type="button" onclick="addRisk();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
									</c:if>
								</div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/risk" class="btn waves-effect waves-light bg-s black-text"
                                            style="width:100%">Cancel</a>
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
    <!-- Page Loader -->
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
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
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $("#dates0,#date_of_identification").datepicker();
            $('#date_icon0').click(function () {
                event.stopPropagation();
                $('#dates0').click();
            });
            $("#atr_date0").datepicker();
            $('#atr_date_icon0').click(function () {
                event.stopPropagation();
                $('#atr_dates0').click();
            });
            $('.modal').modal();
            $('#date_of_identification_icon').click(function () {
                event.stopPropagation();
                $('#date_of_identification').click();
            });
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
            $('#textarea3').characterCounter();
            var projectId = "${riskDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            getSubAreasList();
            getAreasList();
           
        });
        function getAreasList() {
        	$(".page-loader").show();
        	var area = $("#area").val();
        	var sub_area_fk = $("#sub_area_fk").val();
            if ($.trim(area) == "") {
            	$("#area option:not(:first)").remove();
            	var myParams = { sub_area_fk: sub_area_fk, area: area };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getAreasList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                              	 var area = "${riskDetails.area}";
                              	 
      	                           if ($.trim(area) != '' && val.area == $.trim(area)) {
                                         $("#area").append('<option value="' + val.area + '" selected>' + $.trim(val.area) + '</option>');
                                     } else {
                                         $("#area").append('<option value="' + val.area + '">' + $.trim(val.area) + '</option>');
                                     }
                            });
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
            }
        }
     
        function getSubAreasList() {
        	$(".page-loader").show();
        	var area = $("#area").val();
        	var sub_area_fk = $("#sub_area_fk").val();
            if ($.trim(sub_area_fk) == "") {
            	$("#sub_area_fk option:not(:first)").remove();
            	var myParams = { sub_area_fk: sub_area_fk, area: area };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubAreasList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	
                            	 var subArea = "${riskDetails.sub_area_fk}";
                            	 
    	                           if ($.trim(subArea) != '' && val.sub_area_fk == $.trim(subArea)) {
                                       $("#sub_area_fk").append('<option value="' + val.sub_area_fk + '" selected>' + $.trim(val.sub_area_fk) + '</option>');
                                   } else {
                                       $("#sub_area_fk").append('<option value="' + val.sub_area_fk + '">' + $.trim(val.sub_area_fk) + '</option>');
                                   }
                            });
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
            }
        }
     
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
                                    var workId = "${riskDetails.work_id}";
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
            }else{
            	$(".page-loader").hide();
            }
        }
    	//This function is used to get error message for all ajax calls
        function getErrorMessage(jqXHR, exception) {
        	    var msg = '';
        	    if (jqXHR.status === 0) {
        	        msg = 'Not connect.\n Verify Network.';
        	    } else if (jqXHR.status == 404) {
        	        msg = 'Requested page not found. [404]';
        	    } else if (jqXHR.status == 500) {
        	        msg = 'Internal Server Error [500].';
        	    } else if (exception === 'parsererror') {
        	        msg = 'Requested JSON parse failed.';
        	    } else if (exception === 'timeout') {
        	        msg = 'Time out error.';
        	    } else if (exception === 'abort') {
        	        msg = 'Ajax request aborted.';
        	    } else {
        	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        	    }
        	    console.log(msg);
         }
        
        
        
        function addRiskRow(revisionId) {
        	
            var rowNo = $("#rowNo").val();
            var riskNo = Number(rowNo)+1;
            var trainNo = $("#trainNo").val();
            var tNo = Number(trainNo)+1;
           
			var html = '<tr id="riskReviewRow' + riskNo + '">'+
						'<td><div class="input-field"><input id="dates' + riskNo + '" name="dates" type="text" class="validate datepicker" placeholder="Assessment Date">' +
						'<button type="button" id="reveiw_date_icon' + riskNo + '"><i class="fa fa-calendar"></i></button> </div></td>' +
						'<td> <input id="owners' + riskNo + '" name="owners" type="text" class="validate" placeholder="Owner"></td>' +
						'<td><input id="responsible_persons' + riskNo + '" name="responsible_persons" type="text" class="validate" placeholder="Responsible Person"></td>' +
						'<td><select class="validate-dropdown searchable" id="prioritys' + riskNo + '" name="prioritys">'+
						'<option value="" >Priority</option>'+
						<c:forEach var="obj" items="${prioritiesList }">
						'<option value="${obj.priority }">${obj.priority}</option>'+
						</c:forEach>
						'</select> </td>' +
						'<td><select class="validate-dropdown searchable" id="probabilitys' + riskNo + '" name="probabilitys">'+
						'<option value="" >Probability</option>'+
                         '<option value="1">1</option><option value="3">3</option><option value="5">5</option>'+
						'</select></td>' +
						'<td><select class="validate-dropdown searchable" id="impacts' + riskNo + '" name="impacts">'+
						'<option value="" >Impact</option>'+
                         '<option value="1">1</option><option value="3">3</option><option value="5">5</option>'+
						'</select></td>' +
						'<td><textarea id="mitigation_plans' + riskNo + '" name="mitigation_plans" class="materialize-textarea" placeholder="Mitigation Plan"></textarea></td>' +
						
						'<td><a class="waves-effect waves-light btn modal-trigger bg-m t-c" href="#update_action' + riskNo + '" onclick="showNo(this);">Update</a>'+
						
						'<div id="update_action' + riskNo + '" class="modal"><div class="modal-content"><h4 class="modal-header">Action Taken <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h4>'+
						'<!--  <h6>Assessment Date (12-04-2020) </h6> --> <br> <div class="row fixed-width">'+
						'<div class="table-inside"> <table id="update-action-table' + riskNo + '"class="mdl-data-table update-table">'+
						'<thead> <tr><th class="fw-150">ATR Date</th><th>Action Taken</th><th class="fw-60">Action</th></tr></thead>'+
						'<tbody id="actionTableBody' + riskNo + '" ><tr id="actionRow' + riskNo +tNo+ '">'+
						'<td><input type="hidden" id="rowCounts' + riskNo + '" name="rowCounts" value="1" class="hide" />'+
						'<div class="input-field"><input id="atr_dates' + riskNo +tNo+ '" name="atr_dates" type="text"  class="validate datepicker" placeholder="ATR  Date">'+
						'	<button type="button" id="atr_date_icon' + riskNo + tNo+'"><i class="fa fa-calendar"></i></button></div></td>'+
						'<td><textarea id="action_takens' + riskNo +tNo+ '"  name="action_takens" class="materialize-textarea"  placeholder="Action Taken"style="height: 44px;"></textarea></td>'+
						'<td><a onclick="removeactions(' + riskNo +tNo+ ');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td></tr>'+
						'</tbody></table>'+
						'<input type="hidden" id="trainNo"  name="trainNo" value="0" /> ' +                    
              		    '<table class="mdl-data-table"><tbody id="trainingUpdateBody">'+                                          
                        '<tr><td colspan="6" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addRiskActionRow(\''+revisionId+'\',\''+ riskNo +'\')"> <i class="fa fa-plus"></i></a> </tr>'+
                      '</tbody></table></div></div></div></div> </td>'+
						
						'<td> <a onclick="removeRiskRow(' + riskNo +');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </tr>';
			$('#riskRevisionBody').append(html);
            $("#rowNo").val(riskNo);
            $('#update_action'+riskNo).modal();
            $("#dates" + riskNo).datepicker();
            $('#reveiw_date_icon' + riskNo).click(function () {
                event.stopPropagation();
                $('#dates' + riskNo).click();
            });
            $("#atr_dates" + riskNo+tNo).datepicker();
            $('#atr_date_icon' + riskNo+tNo).click(function () {
                event.stopPropagation();
                $('#atr_dates' + riskNo+tNo).click();
            });
            $('.searchable').select2();
		

        }
        
        function removeRiskRow(rowNo){
        	//alert("#revisionRow"+rowNo);
        	$("#riskReviewRow"+rowNo).remove();
        }
        function removeactions(rowNo){
        	//alert("#revisionRow"+rowNo);
        	$("#actionRow"+rowNo).remove();
        }
        var rowNumber = null;
        function showNo(a){
        	rowNumber = a.href.split("#")[1].split("_")[1].split('action')[1];        	
        	console.log($('#riskReviewRow'+rowNumber));
        }
        
        function prevRow(tNo){
       	 var id = $('#actionTableBody'+tNo+' tr .hide:last').attr('id');
       	 if(id == null){
         	id='s0';
         }
            var splt = id.split('s')[1];
            var c = $('#actionTableBody'+tNo+' tr').length;
            if(splt > 0){
            	var lastIndex = splt;
          	    var lastRow = $('#actionTableBody'+tNo+' #rowCounts'+lastIndex).removeAttr("disabled");
          	 $('#actionTableBody'+tNo+' #rowCounts'+lastIndex+':last').val(c)
            }else{
           	 $("#rowCounts0").removeAttr("disabled");
            }
       	
       }
        
        function addRiskActionRow(revisionId,tNo) {
        	var index = rowNumber;
        	if(index == null){
        		index = 0;
        	}
        	$("#rowCounts0").prop('disabled', true);
        	$('#actionTableBody'+tNo+' #rowCounts'+tNo).prop('disabled', true);
        	if(revisionId === undefined){
        		revisionId = "";
        	}
        	var trainNo = $("#trainNo").val();
            var riskNo = Number(trainNo)+1;
            var id = $('#actionTableBody'+tNo+' > tr .hide:last').attr('id');
            if(id == null){
            	id='s0';
            }
            var splt = id.split('s')[1];
            var d =$('#actionTableBody'+tNo+' >tr').length;
            var c = $('#actionTableBody'+tNo+' >tr').length + 1;
            if(splt > 0){
            	var lastIndex = splt;
          	    var lastRow = $('#actionTableBody'+tNo+' #rowCounts'+lastIndex).prop('disabled', true);
            } 
            var html = '<tr id="actionRow' + riskNo + '">'+
						'<td>'+
						'<div class="input-field"><input id="atr_dates' + riskNo + riskNo +'" name="atr_dates" type="text"  class="validate datepicker" placeholder="ATR  Date">'+
						'	<button type="button" id="atr_date_icon' + riskNo +riskNo + '"><i class="fa fa-calendar"></i></button></div></td>'+
						'<td><input type="hidden" id="rowCounts' + riskNo + '" name="rowCounts" class="hide" /><textarea id="action_takens' + riskNo + riskNo +'"  name="action_takens" class="materialize-textarea"  placeholder="Action Taken"style="height: 44px;"></textarea></td>'+
						'<td><a onclick="removeactions(' + riskNo + '); prevRow('+tNo+')" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td></tr>';
				$('#actionTableBody'+ index).append(html);
	            $("#trainNo").val(riskNo );
	            $('#actionTableBody'+tNo+' #rowCounts'+riskNo+':last').val(c)
	            $("#atr_dates" + riskNo+riskNo ).datepicker();
	            $('#atr_date_icon' + riskNo +riskNo ).click(function () {
	                event.stopPropagation();
	                $('#atr_dates' +  riskNo+riskNo ).click();
	            });
        }
        
        function updateRisk(){
       	 if(validator.form()){ // validation perform
   			$(".page-loader").show();
   			$('form input[name=atr_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=action_takens]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=owners]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=responsible_persons]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=prioritys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=probabilitys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=impacts]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			$('form input[name=mitigation_plans]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
   			document.getElementById("riskForm").submit();	
      	}
        }
        
        function addRisk(){
          	 if(validator.form()){ // validation perform
      			$(".page-loader").show();
      			$('form input[name=atr_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=action_takens]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=owners]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=responsible_persons]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=prioritys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=probabilitys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=impacts]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			$('form input[name=mitigation_plans]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
      			document.getElementById("riskForm").submit();	
          	}
           }
        
        var validator =	$('#riskForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"risk_id": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"risk_id": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						 document.getElementById("project_idError").innerHTML="";
				 		 error.appendTo('#project_idError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "risk_id" ){
						   document.getElementById("risk_idError").innerHTML="";
					 	   error.appendTo('#risk_idError');
					}else{
 					error.insertAfter(element);
			        } 
		   		},submitHandler:function(form){
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