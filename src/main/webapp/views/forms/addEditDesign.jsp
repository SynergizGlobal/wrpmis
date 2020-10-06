<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Design & Drawing</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">

       <style>
        #revTable .datepicker~button,
        #example4 .datepicker~button {
            top: 30px;
            right:2px;
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

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }
        td{
        position:relative}

        /* change radio colors  */
        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::after {
            background-color: #2E58AD !important;
        }

        [type="radio"]:checked+span::after,
        [type="radio"].with-gap:checked+span::before,
        [type="radio"].with-gap:checked+span::after {
            border: 2px solid #2E58AD !important;
        }
        #revTable .select2-container{
        max-width:80px;
        text-align:left;
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
                                <h6>
                               	    <c:if test="${action eq 'edit'}">Update Design & Drawing</c:if>
									<c:if test="${action eq 'add'}"> Add Design & Drawing</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-design" id="designForm" name="designForm" method="post" class="form-horizontal" role="form">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-design" id="designForm" name="designForm" method="post" class="form-horizontal" role="form">
						  </c:if>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Project ID </label></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                    onchange="getWorksList(this.value);">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Work ID </label></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" selected>Select</option>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label>Contract ID </label></p>
                                     <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Department </label></p>
                                     <select name="department_id_fk" id="department_id_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                          <c:forEach var="obj" items="${departmentList }">
                                      	    <option value= "${ obj.department_fk}" <c:if test="${designDetails.department_id_fk eq obj.department_fk}">selected</c:if>>${ obj.department_fk}</option>
                                          </c:forEach>
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label>Consultant Contract ID </label></p>
                                    <select name="consultant_contract_id_fk" id="consultant_contract_id_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                       <c:forEach var="obj" items="${contractList }">
                                      	   <option value= "${ obj.contract_id_fk}" <c:if test="${designDetails.contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p> <label>Proof Consultant Contract ID </label></p>
                                    <select id="proof_consultant_contract_id_fk" name="proof_consultant_contract_id_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                          <option value="" selected>Select</option>
                                      	   <c:forEach var="obj" items="${contractList }">
                                      	    <option value= "${ obj.contract_id_fk}" <c:if test="${designDetails.contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                           </c:forEach>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="hod" type="text" class="validate" name="hod">
                                    <label for="hod">HOD </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="dy_hod" type="text" class="validate" name="dy_hod">
                                    <label for="dy_hod">Dy HOD </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p> <label>Prepared By </label></p>
                                    <select class="searchable" name="prepared_by_id_fk" id="prepared_by_id_fk">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                                <p> <label>Structure </label></p>
                                    <select id="structure_type_fk" name="structure_type_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                       			<c:forEach var="obj" items="${structureTypeList}">
	                       						   <option value="${obj.structure_type_fk }" <c:if test="${designDetails.structure_type_fk eq obj.structure_type_fk }">selected</c:if>>${obj.structure_type_fk}</option>
	                                            </c:forEach>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                        <p><label>Component </label></p>
                                    <select class="searchable" name="component" id="component">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p> <label>Drawing Type </label></p>
                                    <select id="drawing_type_fk" name="drawing_type_fk" class="searchable">
                                        <option value="" selected>Select</option>
                                       			 <c:forEach var="obj" items="${drawingTypeList}">
	                       						    <option value="${obj.drawing_type_fk }" <c:if test="${designDetails.drawing_type_fk eq obj.drawing_type_fk }">selected</c:if>>${obj.drawing_type_fk}</option>
	                                             </c:forEach>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="contractor_drawing_no" name="contractor_drawing_no" type="text" class="validate">
                                    <label for="contractor_drawing_no"> Contractor Drawing No </label>


                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="mrvc_drawing_no" name="mrvc_drawing_no" type="text" class="validate">
                                    <label for="mrvc_drawing_no"> MRVC Drawing No </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="division_drawing_no" name="division_drawing_no" type="text" class="validate">
                                    <label for="division_drawing_no"> Division Drawing No</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="hq_drawing_no" name="hq_drawing_no" type="text" class="validate">
                                    <label for="hq_drawing_no"> HQ Drawing No </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="drawing_title" name="drawing_title" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="drawing_title">Drawing Title</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="planned_start" name="planned_start" type="text" class="validate datepicker">
                                    <label for="planned_start">Planned Start </label>
                                    <button type="button" id="planned_start_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="planned_finish" name="planned_finish" type="text" class="validate datepicker">
                                    <label for="planned_finish">Planned Finish </label>
                                    <button type="button" id="planned_finish_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="revision" name="revision" type="text" class="validate">
                                    <label for="revision">Revision</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="consultant_submission" name="consultant_submission" type="text" class="validate datepicker">
                                    <label for="consultant_submission">Consultant Submission </label>
                                    <button type="button" id="consultant_submission_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="mrvc_reviewed_date" name="mrvc_reviewed" type="text" class="validate datepicker">
                                    <label for="mrvc_reviewed_date">MRVC Reviewed </label>
                                    <button type="button" id="mrvc_reviewed_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="divisional_approval_date" name="divisional_approval" type="text" class="validate datepicker">
                                    <label for="divisional_approval_date">Divisional Approval </label>
                                    <button type="button" id="divisional_approval_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="hq_approval_date" name="hq_approval" type="text" class="validate datepicker">
                                    <label for="hq_approval_date">HQ Approval </label>
                                    <button type="button" id="hq_approval_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="gfc_released" name="gfc_released" type="text" class="validate datepicker">
                                    <label for="gfc_released">GFC Released </label>
                                    <button type="button" id="gfc_released_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p> <label for="rivision_no">As Built Drawing Status</label></p>
                                    <select name="as_built_status" id="as_built_status" class="searchable">
                                        <option value="" selected>Select</option>
                                        	 <c:forEach var="obj" items="${revisionStatuses }">
		                                    	<option value="${obj.as_built_status }" <c:if test="${designDetails.as_built_status eq obj.as_built_status}">selected</c:if>>${obj.as_built_status }</option>
		                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="as_built_date" name="as_built_date" type="text" class="validate datepicker" style="margin-top:10px">
                                    <label for="as_built_date">As Built Drawing Date </label>
                                    <button type="button" id="as_built_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                            </div>

                            <!-- insurance show hide div  -->
                            <div class="row fixed-width">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">

                                    <table id="revTable" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Revision </th>
                                                <th>Consultant Submission </th>
                                                <th>MRVC Reviewed </th>
                                                <th>Divisional Approval </th>
                                                <th>HQ approval </th>
                                                <th> Revision Status</th>
                                                <th class="no-sort">Remarks</th>
                                                <th class="no-sort">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="revisionsTableBody">
                                        	<c:forEach var="revObj" items="${designDetails.designRevisions }" varStatus="index">  
                                             <tr id="revisionRow${index.count }">                                                
                                                <td>
                                                    <input id="revisions${index.count }" name="revisions" type="text" class="validate"
                                                        placeholder="Revision">
                                                </td>
                                                <td>
                                                    <input id="consultant_submissions${index.count }" name="consultant_submissions" type="text" class="validate datepicker"
                                                        placeholder="Consultant Submission">
                                                    <button type="button" id="consultant_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="mrvc_revieweds${index.count }" name="mrvc_revieweds" type="text" class="validate datepicker"
                                                        placeholder="MRVC Reviewed">
                                                    <button type="button" id="mrvc_reviewed_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="divisional_approvals${index.count }" name="divisional_approvals" type="text"
                                                        class="validate datepicker" placeholder="Divisional Approval">
                                                    <button type="button" id="divisional_approval_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="hq_approvals${index.count }" name="hq_approvals" type="text" class="validate datepicker"
                                                        placeholder="HQ approval">
                                                    <button type="button" id="hq_approval_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <select class="searchable" id="revision_status_fks${index.count }" name="revision_status_fks">
                                                        <option value="" selected>Select </option>
                                                          <c:forEach var="obj" items="${revisionStatuses }">
		                                    				<option value="${obj.as_built_status }" <c:if test="${designDetails.as_built_status eq obj.as_built_status}">selected</c:if>>${obj.as_built_status }</option>
		                                  				  </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="remarkss${index.count }" name="remarkss" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <a  class="btn waves-effect waves-light red t-c " onclick="removeRevision('${index.count }');"> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                           </c:forEach>
                                           
                                        </tbody>
                                    </table>
 									<table class="mdl-data-table">
                                        <tbody id="revTableBody">                                          
			                                    <tr>
			  										 <td colspan="9" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-s t-c " onclick="addRevisionRow()"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>                       

                            <div class="row" style="margin-top: 20px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row">
                                        <div class="col m6 s12">
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
                                        <div class="col m6 s12">
                                            <div class="row">
                                                <div class="col s6 m6 input-field center-align">
                                                    <p style="margin-top: 8px;">Any Issue ?</p>
                                                </div>
                                                <div class="col s6 m6 input-field">
                                                    <p class="radiogroup"
                                                        style="padding-bottom: 10px;padding-top: 10px;">
                                                        <label>
                                                            <input class="with-gap" name="issue" type="radio"
                                                                value="yes" />
                                                            <span>Yes</span>
                                                        </label> &nbsp; <label>
                                                            <input class="with-gap" name="issue" type="radio"
                                                                value="no" />
                                                            <span>No</span>
                                                        </label>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="issue_yes" style="display: none;">
                                <div class="row">
                                    <h6 class="center-align" style="color:#2E58AD;font-weight:600">Issue Details </h6>
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col m8 s12">
                                        <div class="row">
                                            <div class="col s12 m6 input-field" style="margin-top: 40px;">
                                                <p> <label>Issue Category</label></p>
                                                <select class="searchable">
                                                    <option value="0" selected>Select</option>
                                                    <option value="1">Category 1</option>
                                                    <option value="2">Category 2</option>
                                                    <option value="3">Category 3</option>
                                                </select>
                                            </div>
                                            <div class="col s12 m6 input-field" style="padding-top: 14px;">
                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="priority" type="radio"
                                                            value="high" />
                                                        <span>High</span>
                                                    </label>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m8 input-field">
                                        <textarea id="issueDesc" class="materialize-textarea"
                                            data-length="500"></textarea>
                                        <label for="issueDesc">Issue Description</label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateDesign();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addDesign();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
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



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

	
<!-- 	<script src="/pmis/resources/js/dataTables.material.min.js"></script> -->


    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
         $('.searchable').select2();
            $('#remarks,#textarea3,#issueDesc').characterCounter();
            $("#planned_start,#as_built_date,#hq_approval,#divisional_approval,#mrvc_reviewed,#consultant,#gfc_released,#hq_approval_date,#planned_finish,#divisional_approval_date,#consultant_submission,#mrvc_reviewed_date").datepicker();

            $('#planned_start_icon').click(function () {
                event.stopPropagation();
                $('#planned_start').click();
            });
            $('#consultant_icon').click(function () {
                event.stopPropagation();
                $('#consultant').click();
            });
            $('#mrvc_reviewed_icon').click(function () {
                event.stopPropagation();
                $('#mrvc_reviewed').click();
            });
            $('#planned_finish_icon').click(function () {
                event.stopPropagation();
                $('#planned_finish').click();
            });
            $('#consultant_submission_icon').click(function () {
                event.stopPropagation();
                $('#consultant_submission').click();
            });
            $('#mrvc_reviewed_date_icon').click(function () {
                event.stopPropagation();
                $('#mrvc_reviewed_date').click();
            });
            $('#divisional_approval_date_icon').click(function () {
                event.stopPropagation();
                $('#divisional_approval_date').click();
            });
            $('#divisional_approval_icon').click(function () {
                event.stopPropagation();
                $('#divisional_approval').click();
            });
            $('#hq_approval_date_icon').click(function () {
                event.stopPropagation();
                $('#hq_approval_date').click();
            });
            $('#hq_approval_icon').click(function () {
                event.stopPropagation();
                $('#hq_approval').click();
            });
            $('#gfc_released_icon').click(function () {
                event.stopPropagation();
                $('#gfc_released').click();
            });
            $('#as_built_date_icon').click(function () {
                event.stopPropagation();
                $('#as_built_date').click();
            });

            $('input[name=issue]').change(function () {
                var radioval = $('input[name=issue]:checked').val();
                if (radioval == 'yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#issue_yes').css("display", "none");
                }
            });
            var projectId = "${designDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            var work_id_fk = "${designDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
        });

      
    
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
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                var workId = "${designDetails.work_id_fk}";
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('select').formSelect();
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
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContract",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${designDetails.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                }
                            });
                        }
                        $('select').formSelect();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        function addDesign(){
    			$(".page-loader").show();
    			document.getElementById("designForm").submit();			
    	 	
    	}
    
    function updateDesign(){
		if(validator.form()){ // validation perform
			$(".page-loader").show();
			document.getElementById("designForm").submit();			
	 	}
	}
    

	  function addRevisionRow(){
		
      var rowNo = $("#rowNo").val();
      var rNo = Number(rowNo)+1;
      
      var html ='<tr id="revisionRow'+rNo+'"> '
		      +'<td> <input id="revisions'+rNo+'" name="revisions" type="text" class="validate" placeholder="Revision"></td>'
		      +'<td><input id="consultant_submissions'+rNo+'" name="consultant_submissions" type="text" class="validate datepicker" placeholder="Consultant Submission"><button type="button" id="consultant_icon" class="white"><i class="fa fa-calendar"></i></button> </td>'
		      +'<td><input id="mrvc_revieweds${index.count }" name="mrvc_revieweds" type="text" class="validate datepicker" placeholder="MRVC Reviewed"><button type="button" id="mrvc_reviewed_icon" class="white"><i class="fa fa-calendar"></i></button></td>'
		      +'<td><input id="divisional_approvals'+rNo+'" name="divisional_approvals" type="text" class="validate datepicker" placeholder="Divisional Approval"> <button type="button" id="divisional_approval_icon" class="white"><i class="fa fa-calendar"></i></button></td>'
			  +'<td><input id="hq_approvals'+rNo+'" name="hq_approvals" type="text" class="validate datepicker" placeholder="HQ approval"> <button type="button" id="hq_approval_icon" class="white"><i class="fa fa-calendar"></i></button> </td>'
			  +'<td>'
			  +'<select class="searchable" id="revision_status_fks'+rNo+'" name="revision_status_fks">'
		      +'<option value="" selected>Select </option>'
		        <c:forEach var="obj" items="${revisionStatuses }">
			  	  +'<option value="${obj.as_built_status }">${obj.as_built_status }</option>'
				</c:forEach>
			  +'</select></td>'
			  +'<td> <input id="remarkss'+rNo+'" name="remarkss" type="text" class="validate" placeholder="Remarks"></td>'
			  +'<td><a class="btn waves-effect waves-light red t-c " onclick="removeRevision('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
				
			  $('#revisionsTableBody').append(html);
				 $("#rowNo").val(rNo);
				// $('select').formSelect();
				 $('.searchable').select2();
	  }
	  
	  function removeRevision(rowNo){
	    	//alert("#revisionRow"+rowNo);
	    	$("#revisionRow"+rowNo).remove();
	    }
    
    
    </script>

</body>
</html>