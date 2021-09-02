<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Issue</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">	
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">		 
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
	<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css"> -->
	 <link rel="stylesheet" href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLInputElement/setSelectionRange">
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }        
        .input-field .searchable_label{
			font-size:0.85rem;			
		}
		.input-field>.datepicker ~ label:not(.label-icon).active {
		    -webkit-transform: translateY(-11px) scale(0.8);
		    transform: translateY(-11px) scale(.8);		
		}
		.error-msg label{color:red!important;}
		.datepicker1~button {
		    position: absolute;
		    right: 15px;
		    top: 15px;
		    border: 0;
		    opacity: 0.7;
		    cursor: pointer;
		    background-color: transparent;
		}
		.bg-s *, a.bg-s {
		    color: #fff !important;
		}
		.input-field {
		    margin-top: .35rem;
		    margin-bottom: .35rem;
		}
		.input-field>textarea+label:not(.label-icon).active{
			margin-top: 8px;
		}
		.mar{
			margin-bottom:10px !important;
		}
		@media only screen and (max-width: 769px) {
			.mobile_responsible_table>tbody >tr:not(.datepicker-row)> td> div.btn{
				float:none;
				position:relative;
			}
			.mar{
				margin-bottom:0 !important;
			}
			td.cell-disp-inb .file-path-wrapper {
			    visibility: visible;
			    width: 200%;
			    display: block !important;
			}
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
                                <h6>Update Issue (Issue Id : ${issue.issue_id })</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container no-mar">
                        <form action="<%=request.getContextPath() %>/update-issue" id="issueForm" name="issueForm" method="post" enctype="multipart/form-data">
                        	<input id="existing_status_fk" name="existing_status_fk" type="hidden" value="${issue.existing_status_fk }"/>
                        	<input id="existing_responsible_person" name="existing_responsible_person" type="hidden" value="${issue.responsible_person }"/>
                        	<input id="existing_escalated_to" name="existing_escalated_to" type="hidden" value="${issue.escalated_to }"/>
                        	<input id="issue_id" name="issue_id" type="hidden" value="${issue.issue_id }" />
                        	<input id="hod_user_id_fk" name="hod_user_id_fk" type="hidden" value="${issue.hod_user_id_fk }" />
                        	<input id="dy_hod_user_id_fk" name="dy_hod_user_id_fk" type="hidden" value="${issue.dy_hod_user_id_fk }" />
                        	<div class="row">
                                <div class="col s6 m4 input-field offset-m2">     
                                    <input type="text" class="" value="${issue.project_name }" readonly>
                                    <label for="project_id_fk"> Project</label>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m4 input-field">
                                    <input type="text" class="" value="${issue.work_short_name }" readonly>
                                    <label for="work_id_fk"> Work</label>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row ">
                                <div class="col s12 m8 input-field offset-m2">           
                                    <input id="contract_id_fk" name="contract_id_fk" type="text" class="" value="${issue.contract_short_name }" readonly>
                                    <label for="contract_id_fk"> Contract <span class="required">*</span></label>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>                                
                                <%-- <div class="col s12 m4 input-field">
                                    <label for="">Issue ID : <input id="issue_id" name="issue_id" type="text" value="${issue.issue_id }" readonly  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                </div> --%>
                                 
                            </div>

                            
                            <div class="row">
                                 
                                <div class="col s6 m4 input-field offset-m2">
                               		<!-- <p class="searchable_label">Issue Category <span class="required">*</span></p>                                 
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${issuesCategoryList }">
                                            <option value="${obj.category }" <c:if test="${issue.category_fk eq obj.category}">selected</c:if>>${obj.category}</option>
                                        </c:forEach>
                                    </select> -->
                                     <input id="category_fk" name="category_fk" type="text" class="" value="${issue.category_fk }" readonly>
                                    <label for="category_fk"> Issue Category <span class="required">*</span></label>
                                    <span id="category_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                <p class="searchable_label">Issue Priority <span class="required">*</span></p> 
                                   <select class="searchable validate-dropdown" id="priority_fk" name="priority_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${issuesPriorityList }">
                                            <option value="${obj.priority }" <c:if test="${issue.priority_fk eq obj.priority}">selected</c:if>>${obj.priority}</option>
                                        </c:forEach>
                                    </select>  
                                    <%-- <input id="priority_fk" name="priority_fk" type="text" class="" value="${issue.priority_fk }" readonly>
                                    <label for="priority_fk"> Issue Priority <span class="required">*</span></label>   --%>                               
                                    <span id="priority_fkError" class="error-msg" ></span>
                                </div>
                                 
                            </div>
                            <div class="row">                                 
                                <div class="col s12 m8 input-field offset-m2">
                                    <textarea id="title" name="title" class="materialize-textarea" data-length="1000" readonly>${issue.title }</textarea>
                                    <label for="title">Short Description <span class="required">*</span></label>
                                    <span id="titleError" class="error-msg" ></span>
                                </div>                                 
                            </div>
                            
                            <div class="row">                                 
                                <div class="col s12 m8 input-field offset-m2">
                                    <textarea id="corrective_measure" name="corrective_measure"   class="pmis-textarea"  data-length="1000">${issue.corrective_measure }</textarea>
                                    <label for="corrective_measure">Action Taken<span class="required">*</span></label>
                                    <span id="corrective_measureError" class="error-msg" ></span>
                                    <input type="hidden" name="comment" id="comment" />
                                    <input type="hidden"  id="value_old"  value="${issue.corrective_measure }"/>
                                </div>
                            </div>    
                            
                            <div class="row ">                                 
                                <div class="col s6 m4 input-field offset-m2">
                                    <input id="date" name="date" type="text" class="datepicker1" value="${issue.date }" readonly>
                                    <label for="date">Issue pending since <span class="required">*</span></label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="location" name="location" type="text" class="" value="${issue.location }" readonly>
                                    <label for="location">Location/Station/KM<span class="required">*</span></label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                                 
                            </div>

                            <%-- <div class="row">
                                <!-- row 6 -->
                                 
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${issue.latitude }">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${issue.longitude }">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                 
                            </div> --%>
                          
                            <div class="row mar">
                                 
                                <div class="col s12 offset-m2 m8 input-field">
                                   <p class="searchable_label"> Responsible Organization (Pending with)<span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="zonal_railway_fk" name="zonal_railway_fk" onchange="getResponsiblePersons('')">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${railwayList }">
                                            <option name="${obj.railway_name}" value="${obj.railway_id }" <c:if test="${obj.railway_id eq issue.zonal_railway_fk }">selected</c:if>>${obj.railway_name}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="zonal_railway_fkError" class="error-msg" ></span>
                                </div>
                             </div> 
							 <div class="row mar" id="other_organization_holder" style="display:none;">
	                                <div class="col s12 m4 offset-m2 input-field" >
	                                    <%-- <input id="other_organization" name="other_organization"  type="text" class="validate" value="${issue.other_organization}">
	                                    <label for="other_organization">Organization Name (Pending with)<span class="required">*</span></label> --%>
	                                    <p class="searchable_label">Organization Name (Pending with)<span class="required">*</span></p> 
	                                    <select class="searchable browser-default" id="other_organization" name="other_organization">
	                                        <option value="" selected>Select</option>
	                                        <c:forEach var="obj" items="${otherOrganizations }">
	                                            <option value="${obj.other_organization }" <c:if test="${obj.other_organization eq issue.other_organization }">selected</c:if>>${obj.other_organization}</option>
	                                        </c:forEach>
	                                    </select>
	                                    <span id="other_organizationError" class="error-msg" ></span>
	                                </div>
                               </div>
                               <div class="row mar" id="department_holder" style="display:none;">
                                 <div class="col s12 m4 input-field offset-m2" >
                                  <p class="searchable_label"> Department Responsible (Pending with)<span class="required">*</span></p> 
                                    <select class="searchable browser-default" id="other_organizations" name="other_organization" onchange="getResponsiblePersons(this.value)">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${departmentList }">          
                                         	<c:set var = "string0" value = "${issue.other_organization}" />                               	
      										<c:set var = "string1" value = "${issue.zonal_railway_fk} ${obj.department_name}" />
      										<c:set var = "string2" value = "${issue.zonal_railway_fk} - ${obj.department_name}" />
      										<c:set var = "string3" value = "${issue.zonal_railway_fk} -${obj.department_name}" />
      										<c:set var = "string4" value = "${issue.zonal_railway_fk}- ${obj.department_name}" />
      										<c:set var = "string4" value = "${issue.zonal_railway_fk}-${obj.department_name}" />
                                            <option value="${obj.department_name }" <c:if test="${(string0 eq string1) or (string0 eq string2) or (string0 eq string3) or (string0 eq string4) }">selected</c:if>>${obj.department_name}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="other_organizationsError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row mar" id="other_organization_responsibles_holder" style="display:none;">
                                 
                                <div class="col s12 m4 input-field offset-m2">
                                    <input id="other_org_resposible_person_name" name="other_org_resposible_person_name" type="text" class="validate" value="${issue.other_org_resposible_person_name }">
                                    <label for="other_org_resposible_person_name">Responsible Person Name </label>
                                    <span id="other_org_resposible_person_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="other_org_resposible_person_designation" name="other_org_resposible_person_designation" type="text" class="validate" value="${issue.other_org_resposible_person_designation }">
                                    <label for="other_org_resposible_person_designation" class="fs-sm-8rem">Responsible Person Designation</label>
                                    <span id="other_org_resposible_person_designationError" class="error-msg" ></span>
                                </div>
                                 
                            </div>
                            
                            <div class="row">
							  <div class="col s6 offset-m2 m4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="" value="${issue.reported_by }" readonly>
                                    <label for="reported_by">Reported by </label>
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
				
							  <div class="col s6 m4 input-field ">
					             <p class="searchable_label">Issue Status <span class="required">*</span></p> 
					             <select class="searchable validate-dropdown" id="status_fk" name="status_fk" onchange="getDetailsByStatus(this.value);">
					                 <!-- <option value="">Select</option> -->						                 
					             </select>                                    
					             <span id="status_fkError" class="error-msg" ></span>
					          </div>
							</div>

                            <div class="row mar" id="assignDateDiv" style="display:none">
                                <!-- row 2 -->
                                                                 
                                <div class="col s12 m4 input-field offset-m2">
						             <input id="assigned_date" name="assigned_date" type="text" class="validate datepicker" value="${issue.assigned_date }" >
	                                    <label for="assigned_date""> Assigned Date<span class="required">*</span></label>
	                                    <button type="button" id="assigned_date_icon"><i
	                                            class="fa fa-calendar"></i></button>
	                                    <span id="assigned_dateError" class="error-msg" ></span>
						         </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label" >Person Responsible In MRVC (Assigned to)<span class="required">*</span></p> 
                                    <select class="searchable browser-default" id="responsible_person" name="responsible_person">
                                        <option value="">Select</option>
                                        <%-- <c:forEach var="obj" items="${responsiblePersonList }">
                                            <option value="${obj.responsible_person_user_id }" <c:if test="${issue.responsible_person eq obj.responsible_person_user_id }">selected</c:if>>${obj.responsible_person_designation}</option>
                                        </c:forEach> --%>
                                    </select>
                                    <span id="responsible_personError" class="error-msg" ></span>
                                </div>
                                 
                            </div>
                                                  
                                                   
                            <div>
	                            <div class="row mar" id="escalatedDiv" style="display: none;">
	                                 
	                                <div class="col s6 offset-m2 m4 input-field">
	                                    <%-- <input id="escalated_to" name="escalated_to" type="text" class="validate" value="${issue.escalated_to }">
	                                    <label for="escalated_to">Escalated To </label> --%>
	                                    <p class="searchable_label" style="margin-bottom:8px">Escalated To<span class="required">*</span></p> 
	                                    <select class="searchable browser-default" id="escalated_to" name="escalated_to">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${escalatedToList }">
	                                            <option value="${obj.escalated_to_user_id }" <c:if test="${issue.escalated_to eq obj.escalated_to_user_id}">selected</c:if>>${obj.escalated_to_designation}</option>
	                                        </c:forEach>
	                                    </select>
	                                    <span id="escalated_toError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 input-field">
	                                    <input id="escalation_date" name="escalation_date" type="text" class="validate datepicker" value="${issue.escalation_date}">
	                                    <label for="escalation_date"> Escalated Date<span class="required">*</span></label>
	                                    <button type="button" id="escalation_date_icon"><i
	                                            class="fa fa-calendar"></i></button>
	                                    <span id="escalation_dateError" class="error-msg" ></span>
	                                </div>
	                                 
	                            </div>
                            	<div class="row mar" id="escalatedRemarksDiv" style="display: none;">
	                                 
	                                <div class="col s12 m8 input-field offset-m2" >
	                                <div id="test" data-message="${issue.remarks }" style="display: none;"></div>
	                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${issue.remarks }</textarea>
	                                    <label for="remarks">Status After Escalation</label>
	                                    <span id="remarksError" class="error-msg" ></span>
	                                    <input type="hidden"  name="remarks_new" id="remarks_new"/>
	                                    <input type="hidden"  name="remarks_old" id="remarks_old" value="${issue.remarks}"/>
	                                </div>
	                            </div> 
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m4 input-field offset-m2" style="margin-top:15px; display:none;" id="resolvedDiv">
                                    <input id="resolved_date" name="resolved_date" type="text" class="validate datepicker" value="${issue.resolved_date }">
                                    <label for="resolved_date"> Resolved Date<span class="required">*</span></label>
                                    <button type="button" id="resolved_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="resolved_dateError" class="error-msg" ></span>
                                </div>                                 
                            </div>
                            <input type="hidden" name="existingAssignedPerson" id="existingAssignedPerson" />
                            <div class="row">
								 
								<div class="col m8 s12 offset-m2">
									<div class="row fixed-width"
										style="margin-bottom: 20px; margin-top: 20px">
										<div class="table-inside">
											<table class="mdl-data-table update-table mobile_responsible_table">
												<thead>
													<tr>
														<th style="width: 30%;text-align: left;">File Type</th>
														<th style="width: 52%;text-align: left;">Attach File</th>
														<th style="display:none;"></th>
														<th style="width: 8%;text-align: left;">Action</th>
													</tr>
												</thead>
												<tbody id="issueFilesBody">
													<c:choose>
														<c:when	test="${not empty issue.issueFilesList && fn:length(issue.issueFilesList) gt 0 }">
															<c:forEach var="iObj" items="${issue.issueFilesList }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td data-head="File Type" class="input-field">
																			<select  name="issue_file_types"  id="issue_file_types${index.count }"  class="validate-dropdown searchable">
							                                   					 <option value="" >Select</option>
							                                         			  <c:forEach var="obj" items="${issueFileTypes}">
							                    					  				 <option value="${obj.issue_file_type }" <c:if test="${iObj.issue_file_type_fk eq obj.issue_file_type}">selected</c:if>>${obj.issue_file_type}</option>
							                                          			  </c:forEach>
							                               					  </select>
																	</td>
																	<td data-head="Attach File" class="input-field file-field cell-disp-inb">
									                                        <div class="btn bg-m t-c">
									                                            <span>Attach File</span>
									                                            <input type="file" id="issueFiles${index.count }" name="issueFiles">
									                                        </div>
									                                        <div class="file-path-wrapper">
									                                            <input class="file-path validate" type="text" id="issueFileNames${index.count }" name="issueFileNames" value="${iObj.file_name }">
									                                        </div>                             
			                                                      	</td>
			                                                      	<td style="display:none;">
			                                                      		<input type="hidden" id="issue_file_ids${index.count }" name="issue_file_ids" value="${iObj.issue_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.ISSUE_FILES%>${iObj.issue_id }/${iObj.file_name } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                      	</td>
																	<td class="mobile_btn_close">
																		<a onclick="removeActions('${index.count }');" class="btn red"> 
																			<i class="fa fa-close"></i></a>
																	</td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow0">
																<td data-head="File Type" class="input-field">
																		<select  name="issue_file_types"  id="issue_file_types0"  class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${issueFileTypes}">
						                    					  				 <option value="${obj.issue_file_type }">${obj.issue_file_type}</option>
						                                          			  </c:forEach>
						                               					  </select>
																</td>
																<td data-head="Attach File" class="input-field file-field cell-disp-inb">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach File</span>
								                                            <input type="file" id="issueFiles0" name="issueFiles">
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="issueFileNames0" name="issueFileNames">
								                                        </div>                                       
		                                                      	</td>
		                                                      	<td style="display:none;"><input type="hidden" id="issue_file_ids0" name="issue_file_ids"/></td>
																<td class="mobile_btn_close">
																	<a onclick="removeActions('0');" class="btn red"> 
																		<i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
													
												</tbody>
											</table>
											<table class="mdl-data-table">
												<tbody>
													<tr >
														<td colspan="5"><a type="button"
															class="btn waves-effect waves-light bg-m t-c " onclick="addIssueFileRow()"> <i class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											
											<c:choose>
												<c:when test="${not empty (issue.issueFilesList) && fn:length(issue.issueFilesList) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo" value="${fn:length(issue.issueFilesList) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>

                            <div class="row no-mar">
                                 
                                <div class="col s6 center-align offset-m2 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <c:if test="${issue.status_fk ne 'Closed' and issue.readonlyForm eq false }">
                                        	<a  onclick="updateIssue();" class="btn waves-effect waves-light bg-m" >Update </a>
                                        </c:if>
                                        
                                        <c:if test="${issue.readonlyForm eq true }">
                                        	<a style="color:red;line-height:36px">Not Authorized to Edit</a>
                                        </c:if>
                                        <c:if test="${issue.status_fk eq 'Closed' }">
                                        	<a style="color:red;line-height:36px">Issue Resolved</a>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col s6 center-align m4 mt-brdr">
                                    <div class=" m-1">
                                        <a href="<%=request.getContextPath() %>/issues" class="btn waves-effect waves-light bg-s " >Cancel</a>
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
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" crossorigin="anonymous"></script> -->
	<script>
	
		/*$(document).on('focus', '.datepicker',function(){
				$(this).datepicker({
		        	format:'dd-mm-yyyy',
		        	autoClose:true,
		        	setDefaultDate:false
		        });	       
		    });
		 */
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
		var issueStatusFk = "";
        $(document).ready(function () {
       	  	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#corrective_measure').characterCounter();
            $('#remarks').characterCounter();  
            
            $('#corrective_measure').css('height',function(){
            	this.style.height = (this.scrollHeight < 50) ? '50px' : this.scrollHeight + 'px';
            });
            
           	/* var txt = $("textarea#corrective_measure");
           	txt.val( txt.val() + "\n");*/
          
           	var readOnlyLength = $('#corrective_measure').val().length;
           	var val_old = $('#value_old').val();   /*  .css("color", "#00ffff"); */
           	/*var  input = document.getElementById('corrective_measure');
            	var alpha = val_old;
           	var res = "", cls = "";
           	var t = $("#corrective_measure").text();

           	for (i=0; i<t.length; i++) {
           	        if (t == alpha) {cls = "red";}
            	    res += "<span class='"+cls+"'>"+t[i]+"</span>";
           	    cls="";
           	}
           	$("#corrective_measure").val(res); */
           /* 	var i, l;
           	input.focus();
           	input.value = input.value.trim();
            i = input.value .indexOf( val_old );
            l = ( val_old ).length;
            input.setSelectionRange( i, l + i ); */
            
            
           /* 	if(text.indexOf(val_old) != -1){
           		text = text.replace(/\[val_old\]/g, '[<font color="blue">'+val_old+'</font>]');
           		$("#corrective_measure").val(text);
           	}
           	
            $("#corrective_measure:contains('"+val_old+"')").each(function () {
            	 $(this).html($(this).html().replace(""+val_old+"", "<span class='red'>"+val_old+"</span>"));
            }); */
            
           	$('#corrective_measure').on('keypress, keydown', function(event) {
           		var $field = $(this)
           		 if ((event.which != 37 && (event.which != 39)) &&
					    ((this.selectionStart < readOnlyLength) ||
					      ((this.selectionStart == readOnlyLength) && (event.which == 8)))) {
           			var text = $("textarea#corrective_measure").val();  
           			
           			var concatDescription=$('#corrective_measure').val()+"\n";
	   	        	$('#corrective_measure').val(concatDescription);
	   	        	console.log($('#corrective_measure').val())
	   	        	var len=$('#corrective_measure').val().len();
	   		  		$('#corrective_measure').prop('selectionEnd', len);
	   		  		$('#corrective_measure').prop('selectionStart', len-1);
				    return false;
				  }
           	});
           	
            $('#corrective_measure').focus(function(){
			  var that = this;
			  setTimeout(function(){
				 // that.selectionStart = that.selectionEnd = 10000;
				  var len=$(that).val().length;
	   		  		$(that).prop('selectionEnd',len);
	   		  		$(that).prop('selectionStart', len-1);
			  }, 0);
			});
            
            $('#date_icon').click(function (event) {
                event.stopPropagation();
                $('#date').click();
            });
           	$('#assigned_date_icon').click(function () {
                event.stopPropagation();
                $('#assigned_date').click();
            });
               
            $('#resolved_date_icon').click(function (event) {
                event.stopPropagation();
                $('#resolved_date').click();
            });
            $('#escalation_date_icon').click(function () {
                event.stopPropagation();
                $('#escalation_date').click();
            });
                      
            /* var project_id_fk = "${issue.project_id_fk}";
            if ($.trim(project_id_fk) != '') {
                getWorksList(project_id_fk);
            }
            
            var work_id_fk = "${issue.work_id_fk}";
            if ($.trim(work_id_fk) != '') {
            	getContractsList(work_id_fk);
            } */
	        
            var responsible_person = $("#responsible_person").val();
            var dy_hod_user_id_fk = "${issue.dy_hod_user_id_fk}";
            var hod_user_id_fk = "${issue.hod_user_id_fk}";
            
            if($.trim(responsible_person) == ''){
            	responsible_person = "${issue.responsible_person}";
            }
            
			var escalated_to = "${issue.escalated_to}";
            var existingAssignedPerson = "";
            if(escalated_to != ""){
            	existingAssignedPerson = escalated_to;
            }else if(responsible_person != ""){
            	existingAssignedPerson = responsible_person;
            }else{
            	existingAssignedPerson = dy_hod_user_id_fk;
            }
            $('#existingAssignedPerson').val(existingAssignedPerson);
            getIssueStatusList();
            
            if('${issue.readonlyForm}' == 'true'){
	            $("#issueForm :input").attr("disabled", true);
	            $("#issueForm :textarea").attr("disabled", true);	            
	            $("#issueForm select").prop("disabled", true);	            
            }
            
            //getDetailsByStatus(status_fk);
            
            issueStatusFk = "${issue.status_fk}";
            
            if($.trim(issueStatusFk) == 'Closed'){
            	$("#assignDateDiv").show();
        		$("#resolvedDiv").show();        		
        		var escalated_to = '${issue.escalated_to}';
        		if($.trim(escalated_to) != ''){
        			$("#escalatedDiv").show();
        			$("#escalatedRemarksDiv").show();
        			$("#escalated_to").attr('disabled', true);
        			$("#escalation_date").attr('disabled', true);
        			$("#remarks").attr('readonly', true);
        		}else{
        			$("#escalatedDiv").hide();
        			$("#escalatedRemarksDiv").hide();
        			$("#escalated_to").attr('disabled', false);
        			$("#escalation_date").attr('disabled', false);
        			$("#remarks").attr('readonly', false);
        			
        			$("#escalated_to").val('');
            		$("#escalation_date").val('');
            		$("#remarks").val('');
        		}
        		
        		$("#corrective_measure").attr('readonly', true);
        		$("#zonal_railway_fk").attr('disabled', true);
        		$("#other_organization").attr('disabled', true);
        		//$("#other_organization").attr('readonly', true);
        		$("#other_organizations").attr('disabled', true);
        		
        		$("#assigned_date").attr('disabled', true);
        		$("#responsible_person").attr('disabled', true);
        	}else if($.trim(issueStatusFk) == 'Escalated'){
        		$("#assignDateDiv").show();
        		$("#escalatedDiv").show();
        		$("#escalatedRemarksDiv").show();
        		$("#resolvedDiv").hide();
        		
        		$("#corrective_measure").attr('readonly', true);
        		$("#zonal_railway_fk").attr('disabled', true);
        		$("#other_organization").attr('disabled', true);
        		//$("#other_organization").attr('readonly', true);
        		$("#other_organizations").attr('disabled', true);
        		
        		$("#assigned_date").attr('disabled', true);
        		$("#responsible_person").attr('disabled', true);     
        		
        		$("#escalated_to").val('${issue.escalated_to}');
        		$("#escalation_date").val('${issue.escalation_date}');
        		
        		var div = $('#test').data('message');
        		$("textarea#remarks").val(div.replace("\\n","\n"));
        		$("textarea#remarks").val( div + "\n");
        	 	var readOnlyLength = $('#remarks').val().length;
               	
               	$('#remarks').on('keypress, keydown', function(event) {
               		var $field = $(this)
               		 if ((event.which != 37 && (event.which != 39)) &&
    					    ((this.selectionStart < readOnlyLength) ||
    					      ((this.selectionStart == readOnlyLength) && (event.which == 8)))) {
               			var text = $("textarea#remarks").val();  
    				    return false;
    				  }
               	})
               	
               	var year = '',month = '',day = '';                   	
                var escalation_date = '${issue.escalation_date}';                    
                if($.trim(escalation_date) != '' ){
                	var sDate = escalation_date.split("-");
                	year = sDate[2];
                	month = sDate[1];
                	day = sDate[0];
                }                    
                var minDate = new Date(year,month-1,day); 
                
                let date_pickers = document.querySelectorAll('#escalation_date');
        	    $.each(date_pickers, function(){
        	    	var dt = this.value.split(/[^0-9]/);
        	    	this.value = ""; 
        	    	var options = {minDate : minDate,maxDate: new Date(),format: 'dd-mm-yyyy',autoClose:true};
        	    	if(dt.length > 1){
        	    		options.setDefaultDate = true,
        	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
        	    	}
        	    	M.Datepicker.init(this, options);
        	    });
        		
        	}else if($.trim(issueStatusFk) == 'Assigned'){
        		$("#assignDateDiv").show();
        		$("#escalatedDiv").hide();
        		$("#escalatedRemarksDiv").hide();
            	$("#resolvedDiv").hide();
            	
            	$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', false);
        		$("#other_organization").attr('disabled', false);
        		//$("#other_organization").attr('readonly', false);
        		$("#other_organizations").attr('disabled', false);
        		
        		$("#assigned_date").attr('disabled', false);
        		$("#responsible_person").attr('disabled', false);     
        		
        		$("#escalated_to").val('');
        		$("#escalation_date").val('');
        		$("#remarks").val('');
        		
        		var year = '',month = '',day = '';                   	
                var assigned_date = '${issue.assigned_date}';                    
                if($.trim(assigned_date) != '' ){
                	var sDate = assigned_date.split("-");
                	year = sDate[2];
                	month = sDate[1];
                	day = sDate[0];
                }                    
                var minDate = new Date(year,month-1,day); 
        		
        		let date_pickers = document.querySelectorAll('#assigned_date');
        	    $.each(date_pickers, function(){
        	    	var dt = this.value.split(/[^0-9]/);
        	    	this.value = ""; 
        	    	var options = {minDate : minDate,maxDate: new Date(),format: 'dd-mm-yyyy',autoClose:true};
        	    	if(dt.length > 1){
        	    		options.setDefaultDate = true,
        	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
        	    	}
        	    	M.Datepicker.init(this, options);
        	    });
        	    
        	}else if($.trim(issueStatusFk) == 'Raised'){
        		$("#assignDateDiv").hide();
        		$("#escalatedDiv").hide();
        		$("#escalatedRemarksDiv").hide();
        		$("#resolvedDiv").hide();
        		
        		$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', false);
        		$("#other_organization").attr('disabled', false);
        		//$("#other_organization").attr('readonly', false);
        		$("#other_organizations").attr('disabled', false); 
        		
        		$("#assigned_date").attr('disabled', false);
        		$("#responsible_person").attr('disabled', false);     
        		
        		$("#escalated_to").val('');
        		$("#escalation_date").val('');
        		$("#remarks").val('');
        	}
            
			var department = $("#other_organizations").val();
        	getResponsiblePersons(department);
        });
        
        function getDetailsByStatus(issueStatus){
        	if($.trim(issueStatus) == 'Closed'){
            	$("#assignDateDiv").show();
        		$("#resolvedDiv").show();        		
        		var escalated_to = '${issue.escalated_to}';
        		if($.trim(escalated_to) == '' || $.trim(escalated_to) == 'undefined'){
        			escalated_to = $("#escalated_to").val();
        		}
        		if($.trim(escalated_to) != ''){
        			$("#escalatedDiv").show();
        			$("#escalatedRemarksDiv").show();
        			$("#escalated_to").attr('disabled', true);
        			$("#escalation_date").attr('disabled', true);
        			$("#remarks").attr('readonly', false);
        		}else{
        			$("#escalatedDiv").hide();
        			$("#escalatedRemarksDiv").hide();
        			$("#escalated_to").attr('disabled', false);
        			$("#escalation_date").attr('disabled', false);
        			$("#remarks").attr('readonly', false);
        			
        			$("#escalated_to").val('');
            		$("#escalation_date").val('');
            		$("#remarks").val('');
            		
            		$("#escalated_to").select2();
        		}
        		
        		$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', true);
        		$("#other_organization").attr('disabled', true);
        		//$("#other_organization").attr('readonly', true);
        		$("#other_organizations").attr('disabled', true);
        		
        		$("#assigned_date").attr('disabled', true);
        		$("#responsible_person").attr('disabled', true);   
        		
        		if($.trim('${issue.resolved_date}') != ''){
        			$("#resolved_date").val('${issue.resolved_date}');
        		}else{
        			$('#resolved_date').datepicker({
    					maxDate: new Date(),
    		        	format:'dd-mm-yyyy',
    		        	autoClose:true
    		        }).datepicker("setDate", new Date());
        		}
        		if($.trim('${issue.assigned_date}') != ''){
        			$("#assigned_date").val('${issue.assigned_date}');
        		}else{
        			$('#assigned_date').datepicker({
    					maxDate: new Date(),
    		        	format:'dd-mm-yyyy',
    		        	autoClose:true
    		        }).datepicker("setDate", new Date());
        		}
        		
        	}else if($.trim(issueStatus) == 'Escalated'){
        		$("#assignDateDiv").show();
        		$("#escalatedDiv").show();
        		$("#escalatedRemarksDiv").hide();
        		$("#resolvedDiv").hide();
        		
        		$("#escalated_to").attr('disabled', false);
    			$("#escalation_date").attr('disabled', false);
    			$("#remarks").attr('readonly', false);
        		
        		$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', true);
        		$("#other_organization").attr('disabled', true);
        		//$("#other_organization").attr('readonly', true);
        		$("#other_organizations").attr('disabled', true);
        		
        		$("#assigned_date").attr('disabled', true);
        		$("#responsible_person").attr('disabled', true);     
        		
        		$("#escalated_to").val('${issue.escalated_to}');
        		$("#escalation_date").val('${issue.escalation_date}');
        		var div = $('#test').data('message');
        		$("textarea#remarks").val(div.replace("\\n","\n"));
        		$("textarea#remarks").val( div + "\n");
			    var readOnlyLength = $('#remarks').val().length;
               	
               	$('#remarks').on('keypress, keydown', function(event) {
               		var $field = $(this)
               		 if ((event.which != 37 && (event.which != 39)) &&
    					    ((this.selectionStart < readOnlyLength) ||
    					      ((this.selectionStart == readOnlyLength) && (event.which == 8)))) {
               			var text = $("textarea#remarks").val();  
    				    return false;
    				  }
               	})
        		if($.trim('${issue.escalation_date}') != ''){
        			var year = '',month = '',day = '';                   	
                    var escalation_date = '${issue.escalation_date}';                    
                    if($.trim(escalation_date) != '' ){
                    	var sDate = escalation_date.split("-");
                    	year = sDate[2];
                    	month = sDate[1];
                    	day = sDate[0];
                    }                    
                    var minDate = new Date(year,month-1,day);    
                    let date_pickers = document.querySelectorAll('#escalation_date');
            	    $.each(date_pickers, function(){
            	    	var dt = this.value.split(/[^0-9]/);
            	    	this.value = ""; 
            	    	var options = {minDate : minDate,maxDate: new Date(),format: 'dd-mm-yyyy',autoClose:true};
            	    	if(dt.length > 1){
            	    		options.setDefaultDate = true,
            	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
            	    	}
            	    	M.Datepicker.init(this, options);
            	    });
    		        
        			$("#escalation_date").val('${issue.escalation_date}');
        		}else{
        			$('#escalation_date').datepicker({
    					maxDate: new Date(),
    		        	format:'dd-mm-yyyy',
    		        	autoClose:true
    		        }).datepicker("setDate", new Date());
        		}
        		
        		$("#resolved_date").val('');
        		
        		$("#escalated_to").select2();
        		
        	}else if($.trim(issueStatus) == 'Assigned'){
        		$("#assignDateDiv").show();
        		$("#escalatedDiv").hide();
        		$("#escalatedRemarksDiv").hide();
            	$("#resolvedDiv").hide();
            	
            	$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', false);
        		$("#other_organization").attr('disabled', false);
        		//$("#other_organization").attr('readonly', false);
        		$("#other_organizations").attr('disabled', false);
        		
        		$("#assigned_date").attr('disabled', false);
        		$("#responsible_person").attr('disabled', false);     
        		
        		$("#escalated_to").val('');
        		$("#escalation_date").val('');
        		$("#remarks").val('');
        		
        		$("#resolved_date").val('');
                
        		
        		if($.trim('${issue.assigned_date}') != ''){        			
        			var year = '',month = '',day = '';                   	
                    var assigned_date = '${issue.assigned_date}';                    
                    if($.trim(assigned_date) != '' ){
                    	var sDate = assigned_date.split("-");
                    	year = sDate[2];
                    	month = sDate[1];
                    	day = sDate[0];
                    }                    
                    var minDate = new Date(year,month-1,day);                    
                    let date_pickers = document.querySelectorAll('#assigned_date');
            	    $.each(date_pickers, function(){
            	    	var dt = this.value.split(/[^0-9]/);
            	    	this.value = ""; 
            	    	var options = {minDate : minDate,maxDate: new Date(),format: 'dd-mm-yyyy',autoClose:true};
            	    	if(dt.length > 1){
            	    		options.setDefaultDate = true,
            	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
            	    	}
            	    	M.Datepicker.init(this, options);
            	    });
    		        
        			$("#assigned_date").val('${issue.assigned_date}');
        		}else{
        			$('#assigned_date').datepicker({
    					maxDate: new Date(),
    		        	format:'dd-mm-yyyy',
    		        	autoClose:true
    		        }).datepicker("setDate", new Date());
        		}
        		
        		$("#escalated_to").select2();
        	}else if($.trim(issueStatus) == 'Raised'){
        		$("#assignDateDiv").hide();
        		$("#escalatedDiv").hide();
        		$("#escalatedRemarksDiv").hide();
        		$("#resolvedDiv").hide();
        		
        		$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', false);
        		$("#other_organization").attr('disabled', false);
        		//$("#other_organization").attr('readonly', false);
        		$("#other_organizations").attr('disabled', false); 
        		
        		$("#assigned_date").attr('disabled', false);
        		$("#responsible_person").attr('disabled', false);     
        		
        		$("#escalated_to").val('');
        		$("#escalation_date").val('');
        		$("#remarks").val('');
        		
        		$("#resolved_date").val('');
        		$("#assigned_date").val('');
        		
        		$("#escalated_to").select2();
        	}
        	
        	if(issueStatusFk == 'Escalated'){
        		$("#corrective_measure").attr('readonly', true);  
    		}
        }
        
        //geting works list from database    
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = $.trim(val.work_name) }
                                if ($.trim(val.work_short_name) == '') { workName = $.trim(val.work_id_fk) }
                                var work_id_fk = "${issue.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(workName) + '</option>');
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

        //geting contracts list    
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContracts",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = $.trim(val.contract_name) }
                                if ($.trim(val.contract_short_name) == '') { contract_name = $.trim(val.contract_id_fk) }
                                var contract_id_fk = "${issue.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option contract_type="'+val.contract_type_fk+'" hod="'+val.hod_user_id_fk+'" dyhod="'+val.dy_hod_user_id_fk+'" value="' + val.contract_id + '" selected>' + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option contract_type="'+val.contract_type_fk+'" hod="'+val.hod_user_id_fk+'" dyhod="'+val.dy_hod_user_id_fk+'" value="' + val.contract_id + '">' + $.trim(contract_name) + '</option>');
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
        
        function getIssueStatusList() {
        	$(".page-loader").show();
            $("#status_fk option:not(:first)").remove();
            
            var contract_id_fk = $("#contract_id_fk").val();
            var responsible_person = $("#responsible_person").val();
            var dy_hod_user_id_fk = "${issue.dy_hod_user_id_fk}";
            var hod_user_id_fk = "${issue.hod_user_id_fk}";
            
            if($.trim(responsible_person) == ''){
            	responsible_person = "${issue.responsible_person}";
            }
            
			var escalated_to = "${issue.escalated_to}";
          
            var status_fk = "${issue.status_fk}";
            var logged_id_user_id = "${sessionScope.USER_ID}";
            var logged_id_user_role_code = "${sessionScope.USER_ROLE_CODE}";
            var user_role_it_admin = '<%=CommonConstants.ROLE_CODE_IT_ADMIN%>';
            
            /* var hod_user_id_fk = $("#contract_id_fk").find('option:selected').attr("hod");
            var dy_hod_user_id_fk = $("#contract_id_fk").find('option:selected').attr("dyhod"); */
            
           
            
            
            var myParams = {};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getIssueStatusListForIssuesForm",
                data: myParams, cache: false,async:false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            
                            var selectedFlag = "";
                            if (val.status == $.trim(status_fk)) {
                            	selectedFlag = 'selected';
                            }
                            if($.trim(status_fk) != '' && $.trim(status_fk) == 'Raised'){
                            	if ((val.status == 'Assigned') && ((logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                             	}else 
                                if ((val.status == 'Closed') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }else  
                                if ((val.status == 'Escalated') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == responsible_person ) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }else 
                                if ((val.status == 'Raised')){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                             	}
                            }else if($.trim(status_fk) != '' && $.trim(status_fk) == 'Assigned'){                            	
                            	if ((val.status == 'Assigned') && ((logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == responsible_person ) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                             	}else 
                                if ((val.status == 'Closed') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }else  
                                if ((val.status == 'Escalated') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == responsible_person ) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }
                            }else if($.trim(status_fk) != '' && $.trim(status_fk) == 'Escalated'){
                            	if ((val.status == 'Closed') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }else  
                                if ((val.status == 'Escalated') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == responsible_person ) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk) || (logged_id_user_id == escalated_to))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }
                            }else if($.trim(status_fk) != '' && $.trim(status_fk) == 'Closed'){
                            	if ((val.status == 'Closed') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }
                            }/* else{
                            	if ((val.status == 'Assigned') && ((logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                             	}else 
                                if ((val.status == 'Closed') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }else  
                                if ((val.status == 'Escalated') && ((status_fk == val.status ) || (logged_id_user_role_code == user_role_it_admin) || (logged_id_user_id == responsible_person ) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                                }else 
                                if ((val.status == 'Raised')){
                                	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                             	}
                            } */
                            
                            /* if (val.status == $.trim(status_fk)) {
                                $("#status_fk").append('<option value="' + val.status+'" selected>' + $.trim(val.status) + '</option>');
                            } else {
                                $("#status_fk").append('<option value="' + val.status + '">' + $.trim(val.status) + '</option>');
                            } */
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                }
            });
        }
        
        
        function getResponsiblePersons(department){
        	$(".page-loader").show();
            $("#responsible_person option:not(:first)").remove();
            var myParams = { department_name: department };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getResponsiblePersonsInIssue",
                data: myParams, cache: false,async:true,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var responsible_person = "${issue.responsible_person }";
                            if ($.trim(responsible_person) != '' && val.responsible_person_user_id == $.trim(responsible_person)) {
                            	$("#responsible_person").append('<option value="' + val.responsible_person_user_id + '" selected>' + $.trim(val.responsible_person_designation) + '</option>');
                            } else {
                            	$("#responsible_person").append('<option value="' + val.responsible_person_user_id + '">' + $.trim(val.responsible_person_designation) + '</option>');
                            }
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                }
            });
        }
        
        
        
        function updateIssue(){
        	var text = $("textarea#corrective_measure").val();  
       		var val_old = $('#value_old').val();
       		var remarks = $("textarea#remarks").val();  
       		var remarks_old = $('#remarks_old').val();
       		
       		
       		var remarks_new = remarks.replace(remarks_old,'');
       		$('#remarks_new').val(remarks_new)
       		
       		var val_new = text.replace(val_old,'');
       		$('#comment').val(val_new)
    		if(validator.form()){ // validation perform
    			$(".page-loader").show();
    		
    			$("#corrective_measure").attr('readonly', false);
        		$("#zonal_railway_fk").attr('disabled', false);
        		$("#other_organization").attr('disabled', false);
        		//$("#other_organization").attr('readonly', false);
        		$("#other_organizations").attr('disabled', false);
        		
        		$("#assigned_date").attr('disabled', false);
        		$("#responsible_person").attr('disabled', false);
        		
        		$("#escalated_to").attr('disabled', false);
        		$("#escalation_date").attr('disabled', false);
        		$("#remarks").attr('disabled', false);
        		
    			document.getElementById("issueForm").submit();			
    	 	}
    	}
    	
    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	    var validator = $('#issueForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id_fk": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  },"activity": {
    				 		required: false
    				 	  },"category_fk": {
    				 		required: true
    				 	  },"priority_fk": {
    				 		required: true
    				 	  },"status_fk": {
    			 		    required: true,
    			 	   	  },"title": {
    				 		required: true
    				 	  },"description": {
    			 		    required: false
    			 	   	  },"date": {
    				 		required: true,
       				 	 	dateBeforeToday1:"#date"
    				 	  },"assigned_date":{
    				 		 required: true,
        				 	 dateBeforeToday2:"#assigned_date",
        				 	 dateBefore1:"#date"
    				 	  },"location": {
    				 		required: true
    				 	  },"latitude": {
    				 		required: false
    				 	  },"longitude": {
    				 		required: false
    				 	  },"reported_by": {
    				 		required: false
    				 	  },"responsible_person":{
    				 		 required: true
    				 	  },"corrective_measure": {
    			 		    required: true,
    			 	   	  },"resolved_date": {
    				 		required: true,
       				 	 	dateBeforeToday4:"#resolved_date",
       				 	 	dateBefore3:"#escalation_date",
    				 		statusCheck1: true
    				 	  },"escalated_to": {
    			 		    required: true
    			 	   	  },"escalation_date": {
    			 	   		required: true,
       				 	 	dateBeforeToday3:"#escalation_date",
       				 	 	dateBefore2:"#assigned_date"
	   			 	   	  },"zonal_railway_fk": {
    				 		required: true
    				 	  },"remarks":{
    				 		 required: false
    				 	  },"other_organization":{
    				 		 required: true
    				 	  }
    				 				
    			 	},
    			   messages: {
	    				 "project_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Required'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Required'
    			 	  	 },"activity": {
    			 			required: 'Required'
    			 	  	 },"category_fk": {
    			 			required: 'Required'
    			 	  	 },"priority_fk": {
    			 			required: 'Required'
    			 	  	 },"status_fk": {
    			 			required: 'Required'
    			 	  	 },"title": {
    			 			required: 'Required'
    			 	  	 },"description": {
    			 			required: 'Required'
    			 	  	 },"date": {
    			 			required: 'Required'
    			 	  	 },"assigned_date":{
    			 	  		required: 'Required'
   				 	  	  },"location": {
     				 		required: 'Required'
	   				 	  },"latitude": {
	   				 		required: 'Required'
	   				 	  },"longitude": {
	   				 		required: 'Required'
	   				 	  },"reported_by": {
	   				 		required: 'Required'
	   				 	  },"responsible_person":{
	   				 		 required: 'Required'
	   				 	  },"corrective_measure": {
	   			 		    required: 'Required',
	   			 	   	  },"resolved_date": {
	   				 		required: 'Required'
	   				 	  },"escalated_to": {
	   			 		    required: 'Required'
	   			 	   	  },"escalation_date": {
	   			 		    required: 'Required'
	   			 	   	  },"zonal_railway_fk": {
	   				 		required: 'Required'
	   				 	  },"remarks":{
    			 	  		required: 'Required'
    				 	  },"other_organization":{
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
    			 	    }else if (element.attr("id") == "activity" ){
    			 		     document.getElementById("activityError").innerHTML="";
    			 			 error.appendTo('#activityError');
    			 	    }else if (element.attr("id") == "category_fk" ){
    			 		     document.getElementById("category_fkError").innerHTML="";
    			 			 error.appendTo('#category_fkError');
    			 	    }else if (element.attr("id") == "priority_fk" ){
    			 		     document.getElementById("priority_fkError").innerHTML="";
    			 			 error.appendTo('#priority_fkError');
    			 	    }else if (element.attr("id") == "status_fk" ){
    			 		     document.getElementById("status_fkError").innerHTML="";
    			 			 error.appendTo('#status_fkError');
    			 	    }else if (element.attr("id") == "title" ){
    			 		     document.getElementById("titleError").innerHTML="";
    			 			 error.appendTo('#titleError');
    			 	    }else if (element.attr("name") == "description" ){
    			 		     document.getElementById("descriptionError").innerHTML="";
    			 			 error.appendTo('#descriptionError');
    			 	    }else if (element.attr("id") == "date" ){
    			 		     document.getElementById("dateError").innerHTML="";
    			 			 error.appendTo('#dateError');
    			 	    }else if (element.attr("id") == "assigned_date" ){
	   			 		     document.getElementById("assigned_dateError").innerHTML="";
				 			 error.appendTo('#assigned_dateError');
				 	    }else if (element.attr("id") == "location" ){
  			 	    	     document.getElementById("locationError").innerHTML="";
  			 			     error.appendTo('#locationError');
	  			 	    }else if (element.attr("id") == "latitude" ){
	  			 		     document.getElementById("latitudeError").innerHTML="";
	  			 			 error.appendTo('#latitudeError');
	  			 	    }else if (element.attr("id") == "longitude" ){
	  			 		     document.getElementById("longitudeError").innerHTML="";
	  			 			 error.appendTo('#longitudeError');
	  			 	    }else if (element.attr("id") == "reported_by" ){
	  			 		     document.getElementById("reported_byError").innerHTML="";
	  			 			 error.appendTo('#reported_byError');
	  			 	    }else if (element.attr("id") == "responsible_person" ){
	  			 		     document.getElementById("responsible_personError").innerHTML="";
	  			 			 error.appendTo('#responsible_personError');
	  			 	    }else if (element.attr("id") == "corrective_measure" ){
	  			 		     document.getElementById("corrective_measureError").innerHTML="";
	  			 			 error.appendTo('#corrective_measureError');
	  			 	    }else if (element.attr("id") == "resolved_date" ){
	  			 		     document.getElementById("resolved_dateError").innerHTML="";
	  			 			 error.appendTo('#resolved_dateError');
	  			 	    }else if (element.attr("name") == "escalated_to" ){
	  			 		     document.getElementById("escalated_toError").innerHTML="";
	  			 			 error.appendTo('#escalated_toError');
	  			 	    }else if (element.attr("id") == "escalation_date" ){
	  			 		     document.getElementById("escalation_dateError").innerHTML="";
	  			 			 error.appendTo('#escalation_dateError');
	  			 	    }else if (element.attr("id") == "zonal_railway_fk" ){
	  			 		     document.getElementById("zonal_railway_fkError").innerHTML="";
	  			 			 error.appendTo('#zonal_railway_fkError');
	  			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
    			 	    }else if (element.attr("id") == "other_organization" ){
	   			 		     document.getElementById("other_organizationError").innerHTML="";
				 			 error.appendTo('#other_organizationError');
				 	    }else if (element.attr("id") == "other_organizations" ){
	   			 		     document.getElementById("other_organizationsError").innerHTML="";
				 			 error.appendTo('#other_organizationsError');
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
            
    	    $.validator.addMethod("dateBefore1", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            	//return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	            
	        }, "Assigned Date must be after Issue pending since");
    	    
	    	$.validator.addMethod("dateBefore2", function(value, element) {
	            var fromDateString = $('#assigned_date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Escalated Date date must be after Assigned Date");
	    	
	    	$.validator.addMethod("dateBefore3", function(value, element) {
	    		var fromDateString = $('#assigned_date').val(); 
	    		/* var fromDateString = $('#escalation_date').val(); //
	            var status = $('#status_fk').val();
	            if($.trim(status) != '' && $.trim(status) == 'Escalated'){
	            	fromDateString = $('#escalation_date').val();
	            }else{
	            	fromDateString = $('#assigned_date').val();
	            } */
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Resolved Date must be after Assigned Date");
	    	
	    	
	    	
	    	$.validator.addMethod("dateBeforeToday1", function(value, element) {
	    		var fromDateParts = value.split("-");
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDate = new Date();
	            if($.trim(value) != ''){
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else{
	            	return true;
	            }
	            
	        }, "Issue pending since must be On or Before Today");
    	    
	    	$.validator.addMethod("dateBeforeToday2", function(value, element) {
	    		var fromDateParts = value.split("-");
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDate = new Date();
	            if($.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else{
	            	return true;
	            }
	        }, "Assigned Date date must be On or Before Today");
	    	
	    	$.validator.addMethod("dateBeforeToday3", function(value, element) {
	    		var fromDateParts = value.split("-");
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDate = new Date();
	            if($.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else{
	            	return true;
	            }
	        }, "Escalated Date must be  On or Before Today");
	    	
	    	$.validator.addMethod("dateBeforeToday4", function(value, element) {
	    		var fromDateParts = value.split("-");
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDate = new Date();
	            if($.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else{
	            	return true;
	            }
	        }, "Resolved Date must be On or Before Today");
	    	
    	    
    	    $.validator.addMethod("statusCheck1", function(value, element) {
    	    	var status = $("#status_fk").val();
    	    	if(($.trim(status) == '' || status != 'Closed') && $.trim(value) != ''){
	            	$("#resolved_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	          
	        }, "Status is not closed, So you cannot select this field");
            
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
        	
        	$("#zonal_railway_fk").change(function () {    
            	var val = $('#zonal_railway_fk').val();
            	var name = $("#zonal_railway_fk").find('option:selected').attr("name");
            	
            	$('#other_organizations').removeAttr('name');
            	$('#other_organization').removeAttr('name');
            	
            	$('#other_org_resposible_person_name').removeAttr('name');
            	$('#other_org_resposible_person_designation').removeAttr('name');
            	
            	$('#other_organization_holder').hide();
            	$('#other_organization_responsibles_holder').hide();
            	$('#department_holder').hide();
            	
                if($.trim(val) == 'Others'){
                	$('#department_holder').hide();                	
                	$('#other_organization').attr('name', 'other_organization');
                	$('#other_org_resposible_person_name').attr('name','other_org_resposible_person_name');
                	$('#other_org_resposible_person_designation').attr('name','other_org_resposible_person_designation');
                	
                	$('#other_organization_holder').show();  
                	$('#other_organization_responsibles_holder').show();    
                	$('#other_organization').val('').focus();                	
                } else if($.trim(val) == 'MRVC'){          
                	$('#other_organizations').attr('name', 'other_organization'); 
                	$('#department_holder').show();
                	$('#other_organizations').val('');
                } else if($.trim(val) != ''){ 
                	$('#other_organization').attr('name', 'other_organization');
                	$('#other_organization').val(name);
                	
                	$('#other_org_resposible_person_name').attr('name','other_org_resposible_person_name');
                	$('#other_org_resposible_person_designation').attr('name','other_org_resposible_person_designation');
                	$('#other_organization_responsibles_holder').show();
                }
            });
        	
        	$(document).ready(function(){				
        		var responsibleOrganization = '${issue.zonal_railway_fk}';
        		var otherOrganization = '${issue.zonal_railway_fk}';
        		
        		var name = $("#zonal_railway_fk").find('option:selected').attr("name");
        		
        		$('#other_organizations').removeAttr('name');
            	$('#other_organization').removeAttr('name');
            	
            	$('#other_org_resposible_person_name').removeAttr('name');
            	$('#other_org_resposible_person_designation').removeAttr('name');
            	
            	if($.trim(responsibleOrganization) == 'Others'){
            		$('#other_organization').attr('name', 'other_organization');
            		$('#other_org_resposible_person_name').attr('name','other_org_resposible_person_name');
                	$('#other_org_resposible_person_designation').attr('name','other_org_resposible_person_designation');
                	
                	$('#other_organization_holder').show();  
                	$('#other_organization_responsibles_holder').show();
            	}else if(responsibleOrganization == 'MRVC'){
            		$('#other_organizations').attr('name', 'other_organization');
            		$('#department_holder').show();
            	}else if($.trim(responsibleOrganization) != ''){
            		$('#other_organization').attr('name', 'other_organization');
            		$('#other_organization').val(name);
            		
            		$('#other_org_resposible_person_name').attr('name','other_org_resposible_person_name');
                	$('#other_org_resposible_person_designation').attr('name','other_org_resposible_person_designation');
                	$('#other_organization_responsibles_holder').show();
            	}  
        	});
        	function removeMedia(link,id){
        	   	  $('#'+id).val('');
        	   	  $(link).prev().text('');
        	   	  $(link).css('display','none');
        	}  
        	
            
           /*  function removeFile(link,id,issueFileNames){         	
	          	 var text=$('#'+id).val('');
	          	 var text1=$('#'+issueFileNames).val();
	          	 text1= text1.replace($(link).prev().text(),'') ;
	          	 text1 = text1.replace(/,\s*$/, "");
	          	 $('#'+issueFileNames).val(text1)
	          	 $(link).prev().text(''); 
	          	 $(link).css('display','none');
	          	 $('#'+id).remove()
             } */ 
            
            
            /**************************************************************************************************************/
            
            function selectFile(no){
			    files = $("#issueFiles"+no)[0].files;
			    var html = "";
			    for (var i = 0; i < files.length ; i++) {
			    	html =  html + '<div id=issueFileName'+no+'>'
					 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
					 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
					 + '</div>'
					 + '<div style="clear:both;"></div>';
			    }
			    $("#selectedFiles").append(html);
			    
			    $('#issueFilesDiv'+no).hide();
			    
				var fileIndex = Number(no)+1;
				moreFiles(fileIndex);
			}
			
			function moreFiles(no){
				var html = "";
				html =  html + '<div class="file-field input-field" id="issueFilesDiv'+no+'" >'
				+ '<div class="btn bg-m t-c">'
				+ '<span>Attach Files</span>'
				+ '<input type="file" id="issueFiles'+no+'" name="issueFiles" onchange="selectFile('+no+')">'
				+ '</div>'
				+ '<div class="file-path-wrapper">'
				+ '<input class="file-path validate" type="text">'
				+ '</div>'                          
				+ '</div>'
				$("#selectedFilesInput").append(html);
			}
			
			function removeFile(no){			
	         	$('#issueFilesDiv'+no).remove();
	         	$('#issueFileName'+no).remove();
	        }
			
			/**************************************************************************************************************/
            
			function addIssueFileRow(){
				var rowNo = $("#rowNo").val();
	            var rNo = Number(rowNo)+1;
	            var html = '<tr id="actionRow' + rNo + '">'
	               +'<td data-head="File Type" class="input-field">'
	               +'<select name="issue_file_types" id="issue_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
		   		   +'<option value="" >Select</option>'
				     <c:forEach var="obj" items="${issueFileTypes}">
		     	      +'<option value="${obj.issue_file_type }">${obj.issue_file_type}</option>'
				     </c:forEach>
		   		   +'</select></div></td>'	   		  			
				   
		   		   +'<td data-head="Attach File" class="file-field input-field cell-disp-inb">'	
				   +'<div class="btn bg-m t-c">'	
				   +'<span>Attach File</span>'	
				   +'<input type="file" id="issueFiles'+rNo+'" name="issueFiles">'	
				   +'</div>'	
				   +'<div class="file-path-wrapper">'	
				   +'<input class="file-path validate" type="text" id="issueFileNames'+rNo+'" name="issueFileNames">'	
				   +'</div></td>'
				   +'<td style="display:none;"><input type="hidden" id="issue_file_ids'+rNo+'" name="issue_file_ids"/></td>'
				   +'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
				   +'</tr>';
			
				$('#issueFilesBody').append(html);
	            $("#rowNo").val(rNo);          	
	            
	            $('select:not(.searchable)').formSelect();
	            $('.searchable').select2();
	        }
	        
	        function removeActions(rowNo){
	        	$("#actionRow"+rowNo).remove();
	        }
        
        /****************************************************************************************************/
    </script>
</body>
</html>