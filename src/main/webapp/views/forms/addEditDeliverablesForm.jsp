<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		  <c:if test="${action eq 'edit'}">Update Deliverable - Update Forms - PMIS</c:if>
		  <c:if test="${action eq 'add'}">Add Deliverable - Update Forms - PMIS</c:if>
	</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
     <style>
        .input-field .searchable_label {
            font-size: 0.9rem;
        }      
		.my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		.col.input-field>textarea.materialize-textarea{
		   margin-bottom: 2px;
		}
		.file-field input[type=file]{
			box-shadow:none;
		}
		
		td>.character-counter {
		    top: 75%;
		}
		td>.datepicker~button {
		    right: 5px;
		    top: 23px;
		}
		
		td>.btn, td>.btn-large, td>.btn-small, td>.btn-flat {
		    height: 28px!important;
		    line-height: 30px!important;
		    padding: 0px 9px!important;
		}
		
		.mdl-data-table td, .mdl-data-table th {
		    padding: 0 10px 10px;
		    text-align: right;
		}
		
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                               		 <c:if test="${action eq 'edit'}">Update Deliverable </c:if>
		  							 <c:if test="${action eq 'add'}"> Add Deliverable </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-deliverables" id="deliverablesForm" name="deliverablesForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                 </c:if>
				     <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-deliverables" id="deliverablesForm" name="deliverablesForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
				     </c:if>
                     <div class="container container-no-margin" style="width: 90%!important;">
                         <input type="hidden" name="deliverable_id" id="deliverable_id"  value="${deliverablesDetails.deliverable_id }"/>
                         <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Project <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                               	  		 onchange="getWorksList(this.value);">
                                    		 <option value="" >Select</option>
                                      		 <c:forEach var="obj" items="${projectsList }">
                                         			 <option value="${obj.project_id_fk }" <c:if test="${deliverablesDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                      		 </c:forEach>
		                             </select>
                               		 <span id="project_idError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work <span class="required">*</span></p>
                                     <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                   		  onchange="getContractsList(this.value);">
                                   		  <option value="">Select</option>
                                   		  <c:forEach var="obj" items="${worksList }">
                                     	   		<option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                              </c:forEach>
	                                  </select>
                                   		   
                                  <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Contract <span class="required">*</span></p>
                                   <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();getContractMilestonesList(this.value);">
                                       	<option value="">Select</option>
                                       	 <c:forEach var="obj" items="${contractsList }">
                                     	  			 <option workId="${obj.work_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                       	 </c:forEach>
                                  	</select>
                                   	<span id="contract_id_fkError" class="error-msg" ></span>
                                 </div>
                            </div>
                              
                            <div class="row">
                            	<div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Milestone <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" name="milestone_id" id="milestone_id">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${milestonesList }">
		                                     <option value="${obj.milestone_id }" <c:if test="${(not empty deliverablesDetails.milestone_id) and (deliverablesDetails.milestone_id eq obj.milestone_id)}">selected</c:if>>
		                                     ${obj.milestone_id}
		                                     <c:if test="${obj.milestone_id ne null and obj.milestone_name ne null}">
		                                      => 
		                                     </c:if>
		                                     ${obj.milestone_name}
		                                     </option>
		                                </c:forEach>
                                    </select>
                                    <span id="milestone_idError" class="error-msg" ></span>
                                </div>
                            	<div class="col s6 m4 l4 input-field">
                                    <%-- <p class="searchable_label">Deliverable Type <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" name="deliverable_type_fk" id="deliverable_type_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${deliverablesTypeList }">
		                                     <option value="${obj.deliverable_type_fk }" <c:if test="${deliverablesDetails.deliverable_type_fk eq obj.deliverable_type_fk}">selected</c:if>>${obj.deliverable_type_fk}</option>
		                                </c:forEach>
                                    </select> --%>
                                    <input id="deliverable_type" name="deliverable_type" type="text" class="validate" value="${deliverablesDetails.deliverable_type }">
                                    <label for="deliverable_type">Deliverable type <span class="required">*</span></label>
                                    <span id="deliverable_typeError" class="error-msg" ></span>
                                    
                                </div>
                                
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Status </p>
                                    <select class="searchable validate-dropdown" name="status_fk" id="status_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${statusList }">
		                                     <option value="${obj.status_fk }" <c:if test="${deliverablesDetails.status_fk eq obj.status_fk}">selected</c:if>>${obj.status_fk}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                                    <textarea id="deliverable_description" name="deliverable_description" class="pmis-textarea"
                                        data-length="200">${deliverablesDetails.deliverable_description }</textarea>
                                    <label for="deliverable_description">Deliverable Description</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="milestone_payment" name="milestone_payment" type="number" class="validate" value="${deliverablesDetails.milestone_payment }">
                                    <label for="milestone_payment">Milestone Payment (%) <span class="required">*</span></label>
                                    <span id="milestone_paymentError" class="error-msg" ></span>
                                </div>
                            </div>
							<div class="row">
	                                <div class="row fixed-width" >
	                                    <div class="table-inside" style="margin-bottom: 20px">
	                                        <table class="mdl-data-table mobile_responsible_table" >
	                                            <thead>
	                                                <tr>
	                                                	<th>Id</th>
	                                                	<th>Document</th>
	                                                    <th>Original Due Date</th>
	                                                    <th>Revised Due Date</th>
	                                                    <th>Submission Date</th>
	                                                    <th>Approval Date</th>
	                                                    <th>Payment(%)</th>
	                                                    <th class="fw-300">Remarks</th>
	                                                    <th>Attach File</th>
	                                                    <th></th>
	                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
	                                                    	<th></th>
	                                                    </c:if>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="attachmentsTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty deliverablesDetails.deliverableDocuments  && fn:length(deliverablesDetails.deliverableDocuments ) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${deliverablesDetails.deliverableDocuments }" varStatus="index">  
			                                                <tr id="attachmentRow${index.count }">
			                                                    <td data-head="Document Id " class="input-field"> 
			                                                    	<input id="deliverable_document_ids${index.count }" name="deliverable_document_ids" type="hidden" value="${docObj.deliverable_document_id }"/>
			                                                            ${docObj.deliverable_document_id }
			                                                    </td>
			                                                	<td data-head="Document name" class="input-field"> 
			                                                		<input id="deliverable_document_names${index.count }" maxlength="50" name="deliverable_document_names" type="text" class="validate"
			                                                            placeholder="Document Name" value="${docObj.deliverable_document_name }">
			                                                    </td>
			                                                    <td data-head="Original due date" class="input-field">
										                              <input id="original_due_dates${index.count }" name="original_due_dates" type="text" class="validate datepicker" placeholder="Original due date" value="${docObj.original_due_date }">
									                                  <button type="button" id="original_due_dates${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="original_due_dates${index.count}Error" class="error-msg" ></span>
										                        </td>
			                                                    <td data-head="Revised due date" class="input-field">
										                              <input id="revised_due_dates${index.count }" name="revised_due_dates" type="text" class="validate datepicker" placeholder="Revised due date" value="${docObj.revised_due_date }">
									                                  <button type="button" id="revised_due_dates${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="revised_due_dates${index.count}Error" class="error-msg" ></span>
										                        </td>
			                                                    <td data-head="Submission date" class="input-field">
										                              <input id="submission_dates${index.count }" name="submission_dates" type="text" class="validate datepicker" placeholder="Submission due date" value="${docObj.submission_date }">
									                                  <button type="button" id="submission_dates${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="submission_dates${index.count}Error" class="error-msg" ></span>
										                        </td>
										                        <td data-head="Approval date" class="input-field">
										                              <input id="approval_dates${index.count }" name="approval_dates" type="text" class="validate datepicker" placeholder="Approval due date" value="${docObj.approval_date }">
									                                  <button type="button" id="approval_dates${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="approval_dates${index.count}Error" class="error-msg" ></span>
										                        </td>										                        
			                                                    <td data-head="Payment " class="input-field"> <input id="payments${index.count }" maxlength="10" data-length="50" name="payments" type="number" class="validate"
			                                                            placeholder="Payment" value="${docObj.payment }">
			                                                    </td>
			                                                    <td data-head="Remarks" class="input-field" > 			                                                    
			                                                     <textarea id="remarkss${index.count }" name="remarkss" class="pmis-textarea"
                                        								data-length="200">${docObj.remarks }</textarea>
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="deliverableDocumentFiles${index.count }" name="deliverableDocumentFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="deliverableDocumentFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="deliverable_document_file_names${index.count }" name="deliverable_document_file_names" value="${docObj.deliverable_document_file_name }">
			                                                             <span id="file_names${index.count }" class="filevalue">
				                                                             <c:if test="${not empty docObj.deliverable_document_file_name }"> 
				                                                             	<a href="<%=CommonConstants.DELIVERABLES_FILES%>${docObj.deliverable_document_file_name } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
				                                                             </c:if>
			                                                             </span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
		                                                     		
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
	                                             				<td data-head="Document Id " class="input-field"> 
			                                                    	<input id="deliverable_document_ids0" name="deliverable_document_ids" type="hidden" value=""/>
			                                                    </td>
			                                                	<td data-head="Document name" class="input-field"> 
			                                                		<input id="deliverable_document_names0" maxlength="50" name="deliverable_document_names" type="text" class="validate"
			                                                            placeholder="Document Name">
			                                                    </td>
			                                                    <td data-head="Original due date" class="input-field">
										                              <input id="original_due_dates0" name="original_due_dates" type="text" class="validate datepicker" placeholder="Original due date">
									                                  <button type="button" id="original_due_dates0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="original_due_dates0Error" class="error-msg" ></span>
										                        </td>
			                                                    <td data-head="Revised due date" class="input-field">
										                              <input id="revised_due_dates0" name="revised_due_dates" type="text" class="validate datepicker" placeholder="Revised due date">
									                                  <button type="button" id="revised_due_dates0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="revised_due_dates0Error" class="error-msg" ></span>
										                        </td>
			                                                    <td data-head="Submission date" class="input-field">
										                              <input id="submission_dates0" name="submission_dates" type="text" class="validate datepicker" placeholder="Submission due date" >
									                                  <button type="button" id="submission_dates0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="submission_dates0Error" class="error-msg" ></span>
										                        </td>
										                        <td data-head="Approval date" class="input-field">
										                              <input id="approval_dates0" name="approval_dates" type="text" class="validate datepicker" placeholder="Approval due date">
									                                  <button type="button" id="approval_dates0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
									                                  <span id="approval_dates0Error" class="error-msg" ></span>
										                        </td>										                        
			                                                    <td data-head="Payment " class="input-field"> <input id="payments0" maxlength="50" data-length="50" name="payments" type="text" class="validate"
			                                                            placeholder="Payment">
			                                                    </td>
			                                                    <td data-head="Remarks" class="input-field"> <textarea id="remarkss0" name="remarkss" class="pmis-textarea"
                                        								data-length="200"></textarea>
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="deliverableDocumentFiles0" name="deliverableDocumentFiles"
			                                                                style="display:none" onchange="getFileName('0')"/>
			                                                            <label for="deliverableDocumentFiles0" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="deliverable_document_file_names0" name="deliverable_document_file_names" >
			                                                             <span id="file_names0" class="filevalue"></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>		                                                     		
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
														<td colspan="11" > <a type="button"  class="btn waves-effect waves-light bg-m t-c"  onclick="addAttachmentRow()"> 
														<i class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty deliverablesDetails.deliverableDocuments && fn:length(deliverablesDetails.deliverableDocuments) gt 0 }">
		                                    		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="${fn:length(deliverablesDetails.deliverableDocuments) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose> 
	                                    </div>
	                                </div>
	                            </div>
	                            
	                            <div class="row">
	                                <div class="col s6 m6 mt-brdr">
	                                    <div class="center-align m-1">
		                                         <c:if test="${action eq 'edit'}">
		                                           <button type="button" onclick="updateDeliverablesFrom();" class="btn waves-effect waves-light bg-m">Update</button>
		                                         </c:if>
												 <c:if test="${action eq 'add'}"> 
							                       <button type="button" onclick="addDeliverablesFrom();" class="btn waves-effect waves-light bg-m" style="min-width:90px;">Add</button>
												 </c:if>
	                                    </div>
	                                </div>
	                                <div class="col s6 m6 mt-brdr">
	                                    <div class="center-align m-1">
	                                          <a href="<%=request.getContextPath()%>/deliverables" class="btn waves-effect waves-light bg-s white-text">Cancel</a>
	                                    </div>
	                                </div>
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
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script type="text/javascript">
	      function addAttachmentRow(){		
	 		 var rowNo = $("#attachmentRowNo").val();
	 		 var rNo = Number(rowNo)+1;
	 		 var total = 0;
	 		 var html = '<tr id="attachmentRow'+rNo+'">'
	 					 +'<td data-head="Document Id " class="input-field">' 
	 					+'<input id="deliverable_document_ids'+rNo+'" name="deliverable_document_ids" type="hidden" value=""/>'
	 					+'</td>'
	 					+'<td data-head="Document name" class="input-field"> '
	 					+'<input id="deliverable_document_names'+rNo+'" maxlength="50" name="deliverable_document_names" type="text" class="validate"'
	 					+'placeholder="Document Name">'
	 					+'</td>'
	 					+'<td data-head="Original due date" class="input-field">'
	 					+'<input id="original_due_dates'+rNo+'" name="original_due_dates" type="text" class="validate datepicker" placeholder="Original due date">'
	 					+'<button type="button" id="original_due_dates'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> '											                              
	 					+'<span id="original_due_dates'+rNo+'Error" class="error-msg" ></span>'
	 					+'</td>'
	 					+'<td data-head="Revised due date" class="input-field">'
	 					+'<input id="revised_due_dates'+rNo+'" name="revised_due_dates" type="text" class="validate datepicker" placeholder="Revised due date">'
	 					+'<button type="button" id="revised_due_dates'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>'									                              
	 					+'<span id="revised_due_dates'+rNo+'Error" class="error-msg" ></span>'
	 					+'</td>'
	 					+'<td data-head="Submission date" class="input-field">'
	 					+'<input id="submission_dates'+rNo+'" name="submission_dates" type="text" class="validate datepicker" placeholder="Submission due date" >'
	 					+'<button type="button" id="submission_dates'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>'										                              
	 					+'<span id="submission_dates'+rNo+'Error" class="error-msg" ></span>'
	 					+'</td>'
	 					+'<td data-head="Approval date" class="input-field">'
	 					+'<input id="approval_dates'+rNo+'" name="approval_dates" type="text" class="validate datepicker" placeholder="Approval due date">'
	 					+'<button type="button" id="approval_dates'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>' 											                              
	 					+'<span id="approval_dates'+rNo+'Error" class="error-msg" ></span>'
	 					+'</td>	' 
	 					+'<td data-head="Payment " class="input-field"> <input id="payments'+rNo+'" maxlength="50" data-length="50" name="payments" type="text" class="validate"'
	 					+'placeholder="Payment">'
	 					+'</td>'
	 					+'<td data-head="Remarks" class="input-field"> <textarea id="remarkss'+rNo+'" name="remarkss" class="pmis-textarea"'
						+'data-length="200"></textarea>'
	 					+'</td>'
	 					+'<td data-head="Attachment" class="input-field">'
	 					+'<span class="normal-btn">'
	 					+'<input type="file" id="deliverableDocumentFiles'+rNo+'" name="deliverableDocumentFiles"'
	 					+'style="display:none" onchange="getFileName('+rNo+')"/>'
	 					+'<label for="deliverableDocumentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
	 					+'<input type="hidden" id="deliverable_document_file_names'+rNo+'" name="deliverable_document_file_names" >'
	 					+'<span id="deliverable_document_file_names'+rNo+'" class="filevalue"></span>'
	 					+'</span>'
	 					+'</td>'
	 					+'<td>'                                                     		
	 					+'</td>';
	 					 
	 					 var user_role_name = '${sessionScope.USER_ROLE_NAME}';
	 					 if(user_role_name == 'IT Admin'){
	 						 html = html +'<td class="mobile_btn_close">'
	 						 +'<a href="javascript:void(0);" onclick="removeAttachmentRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
	 						 +'</td>';
	 					 }
	 					 html = html +'</tr>';
	 		
	 			 $('#attachmentsTableBody').append(html);
	 			 $("#attachmentRowNo").val(rNo);
	 			 $('.searchable').select2();
	 			 $('#remarkss'+rNo).characterCounter();
	 	} 
	 	
	 	function removeAttachmentRow(rowNo){
	 		$("#attachmentRow"+rowNo).remove();
	 	}
	
	 	function getFileName(rowNo){
	 		var filename = $('#deliverableDocumentFiles'+rowNo)[0].files[0].name;
	 		$('#file_names'+rowNo).html(filename);
	 	    $('#deliverable_document_file_names'+rowNo).val(filename);
	 	}

        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();    
            
            $("[data-length]").each(function(i,val){
            	$('#'+val.id).characterCounter();
            });
            
            var project_id_fk = "${deliverablesDetails.project_id_fk}";
            if($.trim(project_id_fk) != ''){
            	getWorksList(project_id_fk);
            }
            var work_id_fk = "${deliverablesDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
            var contract_id_fk = "${deliverablesDetails.contract_id_fk}";
            if($.trim(contract_id_fk) != ''){
            	getContractMilestonesList(contract_id_fk);
            }
        });
        
      //geting works list from database 
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();
            $("#contract_id_fk option:not(:first)").remove();
            $("#milestone_id option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForDeliverablesForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workShortName = '';
                                if ($.trim(val.work_short_name) != '') { workShortName = '  - ' + $.trim(val.work_short_name) }
                                
                                var work_id_fk_exists = "${deliverablesDetails.work_id_fk}";
                                var selected = '';
                                if(work_id_fk_exists == val.work_id_fk){
                                	selected = 'selected';
                                }
                                
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '" '+selected+'>' + $.trim(val.work_id_fk) + $.trim(workShortName) + '</option>');
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
            $("#milestone_id option:not(:first)").remove();
            
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForDeliverablesForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_short_name = '  - ' + $.trim(val.contract_short_name) }
                                
                                var contract_id_fk_exists = "${deliverablesDetails.contract_id_fk}";
                                var selected = '';
                                if(contract_id_fk_exists == val.contract_id_fk){
                                	selected = 'selected';
                                }
                                
                                $("#contract_id_fk").append('<option  workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '" '+selected+'>' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
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
        function getContractMilestonesList(contract_id_fk) {
        	$(".page-loader").show();
            $("#milestone_id option:not(:first)").remove();
            
            if ($.trim(contract_id_fk) != "") {
                var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractMilestonesListForDeliverablesForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var milestone = '';
                                if ($.trim(val.milestone_id) != '' && $.trim(val.milestone_name) != '') { 
                                	milestone = $.trim(val.milestone_id) +' => ' + $.trim(val.milestone_name); 
                                }else if ($.trim(val.milestone_id) == '' && $.trim(val.milestone_name) != '') { 
                                	milestone = $.trim(val.milestone_name); 
                                }else if ($.trim(val.milestone_id) != '' && $.trim(val.milestone_name) == '') { 
                                	milestone = $.trim(val.milestone_id) 
                                }
                                
                                var milestone_id_exists = "${deliverablesDetails.milestone_id}";
                                var selected = '';
                                if($.trim(milestone_id_exists) != '' && milestone_id_exists == val.milestone_id){
                                	selected = 'selected';
                                }
                                
                            	$("#milestone_id").append('<option value="' + val.milestone_id + '" '+selected+'>' + milestone + '</option>');
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
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForDeliverablesForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = '  - ' + $.trim(val.work_name) }
                                if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
        
        function addDeliverablesFrom(){
        	if(validator.form()){ // validation perform
   	        	$(".page-loader").show();	    		
   	  			document.getElementById("deliverablesForm").submit();	
        	}
        }
        
        function updateDeliverablesFrom(){
         	if(validator.form()){ // validation perform
    	        	$(".page-loader").show();	    		
    	  			document.getElementById("deliverablesForm").submit();	
         	}
         }
        
        
        var validator =	$('#deliverablesForm').validate({	
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
	  			 	  },"deliverable_type": {
	  		 		    required: true
	  			 	  },"milestone_id": {
	  		 		    required: true
	  			 	  },"status_fk": {
	  		 		    required: false
	  			 	  },"milestone_payment": {
	  		 		    required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"contract_id_fk": {
	  		 			required: ' This field is required'
	  		 	  	  },"deliverable_type": {
	  		 			required: ' This field is required'
	  		 	  	  },"milestone_id": {
	  		 			required: ' This field is required'
	  		 	  	  },"status_fk": {
	  		 			required: ' This field is required'
	  		 	  	  },"milestone_payment": {
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
					}else if(element.attr("id") == "contract_id_fk" ){
						document.getElementById("contract_id_fkError").innerHTML="";
					 	error.appendTo('#contract_id_fkError');
					}else if(element.attr("id") == "deliverable_type" ){
						document.getElementById("deliverable_typeError").innerHTML="";
					 	error.appendTo('#deliverable_typeError');
					}else if(element.attr("id") == "milestone_id" ){
						document.getElementById("milestone_idError").innerHTML="";
					 	error.appendTo('#milestone_idError');
					}else if(element.attr("id") == "status_fk" ){
						document.getElementById("status_fkError").innerHTML="";
					 	error.appendTo('#status_fkError');
					}else if(element.attr("id") == "milestone_payment" ){
						document.getElementById("milestone_paymentError").innerHTML="";
					 	error.appendTo('#milestone_paymentError');
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