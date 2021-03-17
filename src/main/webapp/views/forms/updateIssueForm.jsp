<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	 
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	 
	<link rel="stylesheet" href="/pmis/resources/css/issues.css">
	
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
                            <div class="center-align p-2 bg-m">
                                <h6>Update Issue</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container no-mar">
                        <form action="<%=request.getContextPath() %>/update-issue" id="issueForm" name="issueForm" method="post" enctype="multipart/form-data">
                        	<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <!-- <div class="col s12 m4 input-field">
                                 <p class="searchable_label"> Project <span class="required">*</span></p>    
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id_fk }" <c:if test="${issue.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div> -->
                                <div class="col s12 m4 input-field">                                
                                <%--  <p class="searchable_label"> Project <span class="required">*</span></p>    
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id_fk }" <c:if test="${issue.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select> --%>
                                    <input id="project_id_fk" name="project_id_fk" type="text" class="" value="${issue.project_id_fk }<c:if test="${not empty issue.project_name}"> - </c:if> ${issue.project_name }" readonly>
                                    <label for="project_id_fk"> Project <span class="required">*</span></label>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div> 
                                <div class="col s12 m4 input-field">
                                	<!-- <p class="searchable_label"> Work <span class="required">*</span></p> 
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" selected>Select</option>
                                    </select> -->
                                    <input id="work_id_fk" name="work_id_fk" type="text" class="" value="${issue.work_id_fk }<c:if test="${not empty issue.work_short_name}"> - </c:if> ${issue.work_short_name }" readonly>
                                    <label for="work_id_fk"> Work <span class="required">*</span></label>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                 <!--  <p class="searchable_label"> Contract <span class="required">*</span></p> 
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="getIssueStatusList();">
                                        <option value="">Select</option>
                                    </select> -->                                    
                                    <input id="contract_id_fk" name="contract_id_fk" type="text" class="" value="${issue.contract_id_fk }<c:if test="${not empty issue.contract_short_name}"> - </c:if> ${issue.contract_short_name }" readonly>
                                    <label for="contract_id_fk"> Contract <span class="required">*</span></label>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>                                
                                <div class="col s12 m4 input-field">
                                    <!-- <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea> -->
                                    <label for="">Issue ID : <input id="issue_id" name="issue_id" type="text" value="${issue.issue_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                </div>
                            </div>

                            <%-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                
                                <div class="col s12 m4 input-field">
                                	<p class="searchable_label">Department </p> 
                                    <select class="searchable validate-dropdown" id="department_fk" name="department_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departmentList }">
                                            <option value="${obj.department_fk }" <c:if test="${issue.department_fk eq obj.department_fk}">selected</c:if>>${obj.department_name}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                
                                 <div class="col s12 m4 input-field">
                                    <input id="activity" name="activity" type="text" class="validate" value="${issue.activity }">
                                    <label> Activity </label>
                                    <span id="activityError" class="error-msg" ></span>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
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
                                <div class="col s12 m4 input-field">
                                <!-- <p class="searchable_label">Issue Priority <span class="required">*</span></p> 
                                   <select class="searchable validate-dropdown" id="priority_fk" name="priority_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${issuesPriorityList }">
                                            <option value="${obj.priority }" <c:if test="${issue.priority_fk eq obj.priority}">selected</c:if>>${obj.priority}</option>
                                        </c:forEach>
                                    </select>  --> 
                                    <input id="priority_fk" name="priority_fk" type="text" class="" value="${issue.priority_fk }" readonly>
                                    <label for="priority_fk"> Issue Priority <span class="required">*</span></label>                                 
                                    <span id="priority_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                              <!--   <div class="col s12 m4 input-field">
                                	<p class="searchable_label">Department <span class="required">*</span></p> 
                                    <select class="searchable validate-dropdown" id="department_fk" name="department_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departmentList }">
                                            <option value="${obj.department_fk }" <c:if test="${issue.department_fk eq obj.department_fk}">selected</c:if>>${obj.department_name}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div> -->
                                <div class="col s12 m8 input-field">
                                    <input id="title" name="title" type="text" class="" value="${issue.title }" readonly>
                                    <label for="title">Short Description <span class="required">*</span></label>
                                    <span id="titleError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <%-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                     <textarea id="description" name="description" class="materialize-textarea" data-length="1000">${issue.description }</textarea>
                                    <label for="description">Description </label>
                                    <span id="descriptionError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>
                            <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" name="date" type="text" class="datepicker1" value="${issue.date }" readonly>
                                    <label for="date">Date of raising issue <span class="required">*</span></label>
                                    <button type="button" id="date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="location" name="location" type="text" class="" value="${issue.location }" readonly>
                                    <label for="location">Location/Station/KM<span class="required">*</span></label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
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
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="" value="${issue.reported_by }" readonly>
                                    <label for="reported_by">Reported by </label>
                                    <!-- <p class="searchable_label">Reported by</p> 
                                    <select class="searchable validate-dropdown" id="reported_by" name="reported_by">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${reportedByList }">
                                            <option value="${obj.reported_by_user_id }" <c:if test="${issue.reported_by eq obj.reported_by_user_id }">selected</c:if>>${obj.reported_by_designation}</option>
                                        </c:forEach>
                                    </select> -->
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <%-- <input id="responsible_person" name="responsible_person" type="text" class="validate" value="${issue.responsible_person }">
                                    <label for="responsible_person">Person Responsible In MRVC (Assigned to)</label> --%>
                                    <p class="searchable_label" style="margin-bottom:8px">Person Responsible In MRVC (Assigned to)</p> 
                                    <select class="searchable validate-dropdown" id="responsible_person" name="responsible_person" onchange="getIssueStatusList();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${responsiblePersonList }">
                                            <option value="${obj.responsible_person_user_id }" <c:if test="${issue.responsible_person eq obj.responsible_person_user_id }">selected</c:if>>${obj.responsible_person_designation}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="responsible_personError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <p class="searchable_label"> Responsible Organization</p>
                                    <select class="searchable validate-dropdown" id="zonal_railway_fk" name="zonal_railway_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${railwayList }">
                                            <option name="${obj.railway_name}" value="${obj.railway_id }" <c:if test="${obj.railway_id eq issue.zonal_railway_fk }">selected</c:if>>${obj.railway_name}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="zonal_railway_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field" id="other_organization_holder" style="display:none;">
                                    <input id="other_organization" name="other_organization"  type="text" class="validate" value="${issue.other_organization}">
                                    <label for="other_organization">Organization Name </label>
                                    <span id="other_organizationError" class="error-msg" ></span>
                                </div>
                                 <div class="col s12 m4 input-field" id="department_holder" style="display:none;">
                                  <p class="searchable_label"> Department Responsible  </p> 
                                    <select class="searchable validate-dropdown" id="other_organizations" name="other_organization">
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
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="corrective_measure" name="corrective_measure" class="materialize-textarea" data-length="1000">${issue.corrective_measure }</textarea>
                                    <label for="corrective_measure">Issue/Action Taken/Remarks<span class="required">*</span></label>
                                    <span id="corrective_measureError" class="error-msg" ></span>
                                </div>
                            </div>

                            
                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Issue Status <span class="required">*</span></p> 
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk" onchange="getEscalatedDetails(this.value);">
                                        <option value="">Select</option>
                                        <%-- <c:forEach var="obj" items="${issuesStatusList }">
                                            <c:if test="${(obj.status eq 'Escalated') and ((issue.status_fk eq obj.status ) or (sessionScope.USER_ID eq issue.responsible_person ) or (sessionScope.USER_ID eq issue.dy_hod_user_id_fk) or (sessionScope.USER_ID eq issue.hod_user_id_fk))}">
                                            	<option value="${obj.status }" <c:if test="${issue.status_fk eq obj.status}">selected</c:if>>${obj.status}</option>
                                            </c:if>
                                            <c:if test="${(obj.status ne 'Escalated')}">
                                            	<option value="${obj.status }" <c:if test="${issue.status_fk eq obj.status}">selected</c:if>>${obj.status}</option>
                                            </c:if>
                                        </c:forEach> --%>
                                    </select>                                    
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="resolved_date" name="resolved_date" type="text" class="validate datepicker" value="${issue.resolved_date }">
                                    <label for="resolved_date"> Resolved Date</label>
                                    <button type="button" id="resolved_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="resolved_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div id="escalatedDiv" style="display: none;">
	                            <div class="row" >
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <%-- <input id="escalated_to" name="escalated_to" type="text" class="validate" value="${issue.escalated_to }">
	                                    <label for="escalated_to">Escalated To </label> --%>
	                                    <p class="searchable_label">Escalated To</p> 
	                                    <select class="searchable validate-dropdown" id="escalated_to" name="escalated_to">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${escalatedToList }">
	                                            <option value="${obj.escalated_to_user_id }" <c:if test="${issue.escalated_to eq obj.escalated_to_user_id}">selected</c:if>>${obj.escalated_to_designation}</option>
	                                        </c:forEach>
	                                    </select>
	                                    <span id="escalated_toError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="escalation_date" name="escalation_date" type="text" class="validate datepicker" value="${issue.escalation_date}">
	                                    <label for="escalation_date"> Escalated Date</label>
	                                    <button type="button" id="escalation_date_icon"><i
	                                            class="fa fa-calendar"></i></button>
	                                    <span id="escalation_dateError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
                            	<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${issue.remarks }</textarea>
                                    <label for="remarks">Escalation Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div> 
                            </div>
                         
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="issueFile" name="issueFile">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text" name="attachment" value="${issue.attachment }" id="issueFileWrapper">
                                        </div>
                                    </div>
                                    
                                    <c:if test="${not empty issue.attachment }">
                                       	<a href="<%=CommonConstants2.ISSUE_FILES %>${issue.attachment }" class="filevalue" download>${issue.attachment }</a>
										<span onclick="removeMedia(this,'issueFileWrapper')" class="attachment-remove-btn">X</span>											
                                   	</c:if>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>                            
                            

                            <div class="row no-mar">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a  onclick="updateIssue();" class="btn waves-effect waves-light bg-m" >Update </a>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/issues" class="btn waves-effect waves-light bg-s " >Cancel</a>
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
            $('#corrective_measure').characterCounter();
            $('#remarks').characterCounter();                     
            
            $('#date_icon').click(function (event) {
                event.stopPropagation();
                $('#date').click();
            });
               
            $('#resolved_date_icon').click(function (event) {
                event.stopPropagation();
                $('#resolved_date').click();
            });
            $('#escalation_date_icon').click(function () {
                event.stopPropagation();
                $('#escalation_date').click();
            });
            
            $('#escalation_date').datepicker({ 
  	    	    format:'dd-mm-yyyy',
  	    	    //perform click event on done button
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        });
                      
            var project_id_fk = "${issue.project_id_fk}";
            if ($.trim(project_id_fk) != '') {
                getWorksList(project_id_fk);
            }
            
            var work_id_fk = "${issue.work_id_fk}";
            if ($.trim(work_id_fk) != '') {
            	getContractsList(work_id_fk);
            }
	        
	        var status_fk = "${issue.status_fk}";
            if ($.trim(status_fk) == 'Escalated') {
            	$("#escalatedDiv").show();
            }else{
            	$("#escalated_to").val('');
            	$("#escalation_date").val('');
            	$("#escalatedDiv").hide();
            }
            
            getIssueStatusList();
        });
        
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
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                var work_id_fk = "${issue.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id == $.trim(work_id_fk)) {
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
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${issue.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option hod="'+val.hod_user_id_fk+'" dyhod="'+val.dy_hod_user_id_fk+'" value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option hod="'+val.hod_user_id_fk+'" dyhod="'+val.dy_hod_user_id_fk+'" value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
            
            var status_fk = "${issue.status_fk}";
            var logged_id_user_id = "${sessionScope.USER_ID}";
            
            var hod_user_id_fk = $("#contract_id_fk").find('option:selected').attr("hod");
            var dy_hod_user_id_fk = $("#contract_id_fk").find('option:selected').attr("dyhod");
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
                            
                            if ((val.status == 'Escalated') && ((status_fk == val.status ) || (logged_id_user_id == responsible_person ) || (logged_id_user_id == dy_hod_user_id_fk) || (logged_id_user_id == hod_user_id_fk))){
                            	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                            }
                            if ((val.status == 'Closed') && ((status_fk == val.status ) || (logged_id_user_id == hod_user_id_fk))){
                            	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                            } 
                            if ((val.status != 'Escalated') && (val.status != 'Closed')){
                            	$("#status_fk").append('<option value="' + val.status+'" '+selectedFlag+'>' + $.trim(val.status) + '</option>');
                            } 
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
        
        function getEscalatedDetails(issueStatus){
        	$("#escalated_to").val('');
        	$("#escalation_date").val('');
        	if($.trim(issueStatus) == 'Escalated'){
        		$("#escalatedDiv").show();
        	}else{
        		$("#escalatedDiv").hide();
        	}
        }
        
        function updateIssue(){
    		if(validator.form()){ // validation perform
    			$(".page-loader").show();
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
    				 		required: true
    				 	  },"location": {
    				 		required: true
    				 	  },"latitude": {
    				 		required: false
    				 	  },"longitude": {
    				 		required: false
    				 	  },"reported_by": {
    				 		required: false
    				 	  },"responsible_person":{
    				 		 required: false
    				 	  },"corrective_measure": {
    			 		    required: true,
    			 	   	  },"resolved_date": {
    				 		required: false,
       				 		dateBefore1:"#date",
    				 		statusCheck1: true
    				 	  },"escalated_to": {
    			 		    required: false
    			 	   	  },"zonal_railway_fk": {
    				 		required: false
    				 	  },"remarks":{
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
	   			 	   	  },"zonal_railway_fk": {
	   				 		required: 'Required'
	   				 	  },"remarks":{
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
	  			 	    }else if (element.attr("id") == "zonal_railway_fk" ){
	  			 		     document.getElementById("zonal_railway_fkError").innerHTML="";
	  			 			 error.appendTo('#zonal_railway_fkError');
	  			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
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
	            
	        }, "Resolved Date must be after Date of raising issue");
    	    
    	    $.validator.addMethod("statusCheck1", function(value, element) {
    	    	var status = $("#status_fk").val();
    	    	if(($.trim(status) == '' || status != 'Closed') && $.trim(value) != ''){
	            	$("#resolved_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	          
	        }, "Status is not closed, So you cannot select this field");
            
            /* $('select').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	}); */
            
           /*  $('input').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	}); */
        	
        	$("#zonal_railway_fk").change(function () {    
            	var val = $('#zonal_railway_fk').val();
            	var name = $("#zonal_railway_fk").find('option:selected').attr("name");
            	
            	$('#other_organizations').removeAttr('name');
            	$('#other_organization').removeAttr('name');
            	
            	$('#other_organization_holder').hide();
            	$('#department_holder').hide();
            	
                if(val == 'Others'){
                	$('#department_holder').hide();                	
                	$('#other_organization').attr('name', 'other_organization');
                	$('#other_organization_holder').show();      
                	$('#other_organization').val(name).focus();                	
                } else if(val == 'MRVC'){          
                	$('#other_organizations').attr('name', 'other_organization'); 
                	$('#department_holder').show();
                } else { 
                	$('#other_organization').attr('name', 'other_organization');
                	$('#other_organization').val(name);
                }
            });
        	
        	$(document).ready(function(){				
        		var responsibleOrganization = '${issue.zonal_railway_fk}';
        		var otherOrganization = '${issue.zonal_railway_fk}';
        		
        		var name = $("#zonal_railway_fk").find('option:selected').attr("name");
        		
        		$('#other_organizations').removeAttr('name');
            	$('#other_organization').removeAttr('name');
            	
            	if($.trim(responsibleOrganization) == 'Others'){
            		$('#other_organization').attr('name', 'other_organization');
            		$('#other_organization_holder').show();
            	}else if(responsibleOrganization == 'MRVC'){
            		$('#other_organizations').attr('name', 'other_organization');
            		$('#department_holder').show();
            	}else{
            		$('#other_organization').attr('name', 'other_organization');
            		$('#other_organization').val(name);
            	}        		
        	});
        	function removeMedia(link,id){
        	   	  $('#'+id).val('');
        	   	  $(link).prev().text('');
        	   	  $(link).css('display','none');
        	}  
            
    </script>
</body>
</html>