<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
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
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/mobile-form-template.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" href="/pmis/resources/css/mobile-responsive-table.css">
       <style>
        #revTable .datepicker~button,
        #example4 .datepicker~button {
            top: 30px;
            right:2px;
        }
        .mdl-data-table{
        	border:1px solid #ccc;	
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
            color: #777;
        }

        .fixed-width {
            width: 100%;
			margin: auto !important;            
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
          .input-field .searchable_label {
            font-size: 0.9rem;
        }
        p.prio{
        	margin-top: -10px !important;
        	text-align:center;
        }
        #revTable .select2-container{
	     /*    max-width:80px; */
	        text-align:left;
	        margin-top:10px;
        }
        
        .my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		.datepicker~button {
		     top: 21px;
		}
		 .file-path-wrapper input[type='text'].file-path {
	    	box-shadow:none !important;
	    }
	    #revisionsTableBody tr td{
	    	white-space:inherit;
	    }
	    @media only screen and (max-width: 768px){
			.mobile_responsible_table>tbody tr:not(.datepicker-row) td::before {			
			    vertical-align: middle;
			}
		}
		/* mobile related styling starts here  */
		 .input-field>.datelike{
	  		margin-top:10px !important;
	  	}
	  	.input-field>.datelike1{
	  		margin-top:13px !important;
	  	}
	  	 .input-field>textarea.datelike{
	  		margin-top:12px !important;
	  	}
    	.input-field>textarea ~ label,
    	.input-field>.datelike ~ label
    	.input-field>.datelike1 ~ label{
	        padding-left:10px;
	    }
	    .input-field>textarea ~ label:not(.label-icon).active,
	    .input-field>.datelike ~ label:not(.label-icon).active,
	    .input-field>.datelike1 ~ label:not(.label-icon).active{
	        background-color:transparent;
	        padding-left:0;
	        font-size:.9rem;
	    }
	    .input-field>.datelike ~ label{
        	line-height:44px;
        	padding-left:10px;
	    }
	     .input-field>textarea.datelike ~ label,
	     .input-field>.datelike1 ~ label{
        	line-height:42px;
        	padding-left:10px;
	    }
	    textarea.datelike{
	    	height:40px !important;
	    }
	    .input-field>.datelike ~ label:not(.label-icon).active,
	    .input-field>input[type='text'] ~ label:not(.label-icon).active{
	        line-height:20px;
	        -webkit-transform: translateY(-11px) scale(0.95) ;
		    transform: translateY(-11px) scale(0.95);
	    }
	    .datepicker~button {
		    top: 21px;
		    right:10px;
		}
		
		#revisionsTableBody tr td.input-field .datepicker~button{
			position: relative;
		    top: 0;
		    right: 26px;
		}
	    /* mobile related styling ends here  */
    </style>
</head>
<body>
               
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
									Update / Add Design & Drawing
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-design" id="designForm" name="designForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-design" id="designForm" name="designForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
						  
						   <input type="hidden" id="design_id" name="design_id" value="${designDetails.design_id }">
						    <div class="container container-no-margin">
						    <c:if test="${action eq 'add'}">	
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Project </p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                    onchange="getWorksList(this.value);">
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            </c:if>
 							<c:if test="${action eq 'edit'}">	
	                             <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>	                               
	                                <div class="col s6 m4 input-field">
										<p class="searchable_label">Project</p>
	                                         	 	<input type="text" name="project_id_fk" id="project_id_fk" value="${designDetails.project_id_fk}- ${designDetails.project_name}" readonly />
								    </div> 
	                                <div class="col s6 m4 input-field"> 
									    <p class="searchable_label"> Work </p>
	                                         	 	<input type="text" name="work_id_fk" id="work_id_fk" value="${designDetails.work_id_fk}- ${designDetails.work_short_name}" readonly />
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
                            </c:if>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <c:if test="${action eq 'add'}">
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Contract </p>
                                     <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" 
                                     	onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" value= "${ obj.contract_id}">${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                </c:if>
                                <c:if test="${action eq 'edit'}">
                                <div class="col s6 m4 input-field"> 
									    <p class="searchable_label"> Contract </p>
	                                         	 	<input type="text" name="contract_id_fk" id="contract_id_fk" value="${designDetails.contract_id_fk}- ${designDetails.contract_short_name}" readonly />
	                                </div>
                                </c:if> 
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Department </p>
                                     <select name="department_id_fk" id="department_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                          <c:forEach var="obj" items="${departmentList }">
                                      	    <option value= "${ obj.department_fk}" <c:if test="${designDetails.department_id_fk eq obj.department_fk}">selected</c:if>>${ obj.department_name}</option>
                                          </c:forEach>
                                    </select>
                                     <span id="department_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Consultant Contract </p>
                                    <select name="consultant_contract_id_fk" id="consultant_contract_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${contractList }">
                                      	   <option value= "${ obj.contract_id_fk}" <c:if test="${designDetails.consultant_contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label" style="font-size:.74rem">Proof Consultant Contract </p>
                                    <select id="proof_consultant_contract_id_fk" name="proof_consultant_contract_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                          <option value="" selected>Select</option>
                                      	   <c:forEach var="obj" items="${contractList }">
                                      	    <option value= "${ obj.contract_id_fk}" <c:if test="${designDetails.proof_consultant_contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                           </c:forEach>
                                    </select>
                                    <span id="proof_consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <input id="hod" type="text" class="validate datelike" name="hod" value="${designDetails.hod }">
                                    <label for="hod">HOD </label>
                                    <span id="hodError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="dy_hod" type="text" class="validate datelike" name="dy_hod" value="${designDetails.dy_hod }">
                                    <label for="dy_hod">Dy HOD </label>
                                    <span id="dy_hodError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Prepared By </p>
                                    <select class="searchable validate-dropdown" name="prepared_by_id_fk" id="prepared_by_id_fk">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="prepared_by_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                                <p class="searchable_label">Structure </p>
                                    <select id="structure_type_fk" name="structure_type_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                                 		<c:forEach var="obj" items="${structureTypeList}">
                  						   <option value="${obj.structure_type_fk }" <c:if test="${designDetails.structure_type_fk eq obj.structure_type_fk }">selected</c:if>>${obj.structure_type_fk}</option>
                                       </c:forEach>
                                    </select>
                                    <span id="structure_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field" style="padding-top:9px">
                                    <input id="component" type="text" class="validate datelike" name="component" value="${designDetails.component }">
                                    <label for="component">Component </label>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label" style="margin-top:-4px !important">Drawing Type </p>
                                    <select id="drawing_type_fk" name="drawing_type_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                               			<c:forEach var="obj" items="${drawingTypeList}">
                						    <option value="${obj.drawing_type_fk }" <c:if test="${designDetails.drawing_type_fk eq obj.drawing_type_fk }">selected</c:if>>${obj.drawing_type_fk}</option>
                                      	</c:forEach>
                                    </select>
                                    <span id="drawing_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="drawing_title" name="drawing_title" class="materialize-textarea" data-length="1000">${designDetails.drawing_title }</textarea>
                                    <label for="drawing_title">Drawing Title</label>
                                     <span id="drawing_titleError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <input id="planned_start" name="planned_start" type="text" class="validate datepicker" value="${designDetails.planned_start }">
                                    <label for="planned_start">Planned Start </label>
                                    <button type="button" id="planned_start_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="planned_startError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="planned_finish" name="planned_finish" type="text" class="validate datepicker" value="${designDetails.planned_finish }">
                                    <label for="planned_finish">Planned Finish </label>
                                    <button type="button" id="planned_finish_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="planned_finishError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                             <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <input id="contractor_drawing_no" name="contractor_drawing_no" type="text" class="validate datelike" value="${designDetails.contractor_drawing_no }">
                                    <label for="contractor_drawing_no" style="font-size:0.85rem"> Contractor Drawing No </label>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="consultant_submission" name="consultant_submission" type="text" class="validate datepicker" value="${designDetails.consultant_submission }">
                                    <label for="consultant_submission" style="font-size:0.85rem">Consultant Submission </label>
                                    <button type="button" id="consultant_submission_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="consultant_submissionError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                          
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <input id="mrvc_drawing_no" name="mrvc_drawing_no" type="text" class="validate datelike" value="${designDetails.mrvc_drawing_no }">
                                    <label for="mrvc_drawing_no">MRVC Drawing No </label>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="mrvc_reviewed" name="mrvc_reviewed" type="text" class="validate datepicker" value="${designDetails.mrvc_reviewed }">
                                    <label for="mrvc_reviewed">MRVC Reviewed </label>
                                    <button type="button" id="mrvc_reviewed_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="mrvc_reviewedError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <div class="col m4 hide-on-small-only"></div>                                
                                <div class="col s12 m4 input-field">
                                    <p class="prio">Divisional submission ?</p>
                                    <p class="radiogroup">
                                        <label>
                                            <input class="with-gap" name="divisional_submission_fk" type="radio"
                                                value="Yes"  <c:if test="${designDetails.divisional_submission_fk eq 'Yes'}">checked</c:if>/>
                                            <span>Yes</span>
                                        </label>
                                        &nbsp;
                                        <label>
                                            <input class="with-gap" name="divisional_submission_fk" type="radio"
                                                value="No" <c:if test="${designDetails.divisional_submission_fk eq 'No'}">checked</c:if> />
                                            <span>No</span>
                                        </label>
                                    </p>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                             </div>
                              <div class="row divisional_submission_fk" style="display:none;">
                                <div class="col m2 hide-on-small-only"></div>      
                                <div class="col s12 m8 ">
                                	<div class="row">                                
		                                <div class="col s6 m4 input-field" >
		                                    <input id="division_drawing_no" name="division_drawing_no" type="text" class="validate datelike" value="${designDetails.division_drawing_no }">
		                                    <label for="division_drawing_no" style="font-size:0.9rem"> Divisional Drawing No</label>
		                                </div>
		                                <div class="col s6 m4 input-field" >
		                                    <input id="submitted_to_division" name="submitted_to_division" type="text" class="validate datepicker" value="${designDetails.submitted_to_division }">
		                                    <label for="submitted_to_division" style="font-size:0.85rem">Submitted to Division </label>
		                                    <button type="button" id="submitted_to_division_icon"><i
		                                            class="fa fa-calendar"></i></button>
		                                    <span id="submitted_to_divisionError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m4 input-field" >
		                                    <input id="divisional_approval" name="divisional_approval" type="text" class="validate datepicker" value="${designDetails.divisional_approval }">
		                                    <label for="divisional_approval">Divisional Approval </label>
		                                    <button type="button" id="divisional_approval_icon"><i
		                                            class="fa fa-calendar"></i></button>
		                                    <span id="divisional_approvalError" class="error-msg" ></span>
		                                </div>
		                             </div>
                           		</div>
                                <div class="col m2 hide-on-small-only"></div>
							</div>
							
                            <div class="row">
                                <div class="col m4 hide-on-small-only"></div>                                
                                <div class="col s12 m4 input-field">
                                    <p class="prio">HQ submission ?</p>
                                    <p class="radiogroup">
                                        <label>
                                            <input class="with-gap" name="hq_submission_fk" type="radio"
                                                value="Yes" <c:if test="${designDetails.hq_submission_fk eq 'Yes'}">checked</c:if> />
                                            <span>Yes</span>
                                        </label>
                                        &nbsp;
                                        <label>
                                            <input class="with-gap" name="hq_submission_fk" type="radio"
                                                value="No"  <c:if test="${designDetails.hq_submission_fk eq 'No'}">checked</c:if>/>
                                            <span>No</span>
                                        </label>
                                    </p>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>  
                            </div>           
                                 
                            <div class="row hq_submission_fk" style="display:none;">
                                <div class="col m2 hide-on-small-only"></div>   
                                <div class="col s12 m8 ">
                                	<div class="row">           
		                                <div class="col s6 m4 input-field " >
		                                    <input id="hq_drawing_no" name="hq_drawing_no" type="text" class="validate datelike" value="${designDetails.hq_drawing_no }">
		                                    <label for="hq_drawing_no"> HQ Drawing No </label>
		                                </div>
		                                <div class="col s6 m4 input-field " >
		                                    <input id="hq_submission" name="submitted_to_hq" type="text" class="validate datepicker" value="${designDetails.submitted_to_hq }">
		                                    <label for="hq_submission">Submitted to HQ</label>
		                                    <button type="button" id="hq_submission_icon"><i
		                                            class="fa fa-calendar"></i></button>
		                                    <span id="hq_submissionError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m4 input-field ">
		                                    <input id="hq_approval" name="hq_approval" type="text" class="validate datepicker" value="${designDetails.hq_approval }">
		                                    <label for="hq_approval">HQ Approval </label>
		                                    <button type="button" id="hq_approval_icon"><i
		                                            class="fa fa-calendar"></i></button>
		                                    <span id="hq_approvalError" class="error-msg" ></span>
		                                </div>
		                             </div>
		                        </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <input id="revision" name="revision" type="text" class="validate datelike" value="${designDetails.revision }">
                                    <label for="revision">Revision</label>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="gfc_released" name="gfc_released" type="text" class="validate datepicker" value="${designDetails.gfc_released }">
                                    <label for="gfc_released">GFC Released </label>
                                    <button type="button" id="gfc_released_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="gfc_releasedError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label" style="font-size:0.8rem">As Built Drawing Status</p>
                                    <select name="as_built_status" id="as_built_status" class="searchable">
                                        <option value="" selected>Select</option>
                                        	 <c:forEach var="obj" items="${asBuiltStatuses }">
		                                    	<option value="${obj.as_built_status }" <c:if test="${designDetails.as_built_status eq obj.as_built_status}">selected</c:if>>${obj.as_built_status }</option>
		                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input id="as_built_date" name="as_built_date" type="text" class="validate datepicker" value="${designDetails.as_built_date }">
                                    <label for="as_built_date" style="font-size:0.9rem">As Built Drawing Date </label>
                                    <button type="button" id="as_built_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="as_built_dateError" class="error-msg" ></span>
                                </div>
                            </div>
</div>
                            <!-- insurance show hide div  -->
                            <div class="row fixed-width">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">
                                    <table id="revTable" class="mdl-data-table  mobile_responsible_table">
                                        <thead>
                                            <tr>
                                                <th>Revision </th>
                                                <th>Consultant Submission </th>
                                                <th>MRVC Reviewed </th>
                                                <th>Divisional Approval </th>
                                                <th>HQ approval</th>
                                                <th>Revision Status</th>
                                                <th class="no-sort">Remarks</th>
                                                <th class="no-sort">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="revisionsTableBody">
                                        
                                        <c:choose>
	                                        <c:when test="${not empty designDetails.designRevisions && fn:length(designDetails.designRevisions) gt 0 }">
	                                        	<c:forEach var="revObj" items="${designDetails.designRevisions }" varStatus="index">  
		                                            <tr id="revisionRow${index.count }">                                                
		                                                <td data-head="Revision" class="input-field">
		                                                    <input id="revisions${index.count }" name="revisions" type="text" class="validate"
		                                                        placeholder="Revision" value="${revObj.revision }">                                                        
		                                                </td>
		                                                <td data-head="Consultant Submission" class="input-field">
		                                                    <input id="consultant_submissions${index.count }" name="consultant_submissions" type="text" class="validate datepicker"
		                                                        placeholder="Consultant Submission" value="${revObj.consultant_submission }">
		                                                    <button type="button" id="consultant_icon${index.count }" class="white"><i
		                                                            class="fa fa-calendar"></i></button>
		                                                </td>
		                                                <td data-head="MRVC Reviewed" class="input-field">
		                                                    <input id="mrvc_revieweds${index.count }" name="mrvc_revieweds" type="text" class="validate datepicker"
		                                                        placeholder="MRVC Reviewed" value="${revObj.mrvc_reviewed }">
		                                                    <button type="button" id="mrvc_reviewed_icon${index.count }" class="white"><i
		                                                            class="fa fa-calendar"></i></button>
		                                                </td>
		                                                <td data-head="Divisional Approval" class="input-field">
		                                                    <input id="divisional_approvals${index.count }" name="divisional_approvals" type="text"
		                                                        class="validate datepicker" placeholder="Divisional Approval" value="${revObj.divisional_approval }">
		                                                    <button type="button" id="divisional_approval_icon${index.count }" class="white"><i
		                                                            class="fa fa-calendar"></i></button>
		                                                </td>
		                                                <td data-head="HQ approval" class="input-field">
		                                                    <input id="hq_approvals${index.count }" name="hq_approvals" type="text" class="validate datepicker"
		                                                        placeholder="HQ approval" value="${revObj.hq_approval }">
		                                                    <button type="button" id="hq_approval_icon${index.count }" class="white"><i
		                                                            class="fa fa-calendar"></i></button>
		                                                </td>
		                                                <td data-head=" Revision Status" class="input-field">
		                                                    <select class="searchable" id="revision_status_fks${index.count }" name="revision_status_fks">
		                                                        <option value="" selected>Select </option>
		                                                          <c:forEach var="obj" items="${revisionStatuses }">
				                                    				<option value="${obj.revision_status }" <c:if test="${revObj.revision_status_fk eq obj.revision_status}">selected</c:if>>${obj.revision_status }</option>
				                                  				  </c:forEach>
		                                                    </select>
		                                                </td>
		                                                <td data-head="Remarks" class="input-field">
		                                                    <input id="remarkss${index.count }" name="remarkss" type="text" class="validate"
		                                                        placeholder="Remarks" value="${revObj.remarks }">
		                                                </td>
		                                                <td class="mobile_btn_close">
		                                                    <a  class="btn waves-effect waves-light red t-c " onclick="removeRevision('${index.count }');"> <i
		                                                            class="fa fa-close"></i></a>
		                                                </td>
		                                           </tr>
	                                           </c:forEach> 
                                           </c:when>
	                                       <c:otherwise>
	                                        	<tr id="revisionRow0">                                                
	                                                <td data-head="Revision" class="input-field">
	                                                    <input id="revisions0" name="revisions" type="text" class="validate"
	                                                        placeholder="Revision">                                                        
	                                                </td>
	                                                <td data-head="Consultant Submission" class="input-field">
	                                                    <input id="consultant_submissions0" name="consultant_submissions" type="text" class="validate datepicker"
	                                                        placeholder="Consultant Submission">
	                                                    <button type="button" id="consultant_icon0" class="white"><i
	                                                            class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="MRVC Reviewed" class="input-field">
	                                                    <input id="mrvc_revieweds0" name="mrvc_revieweds" type="text" class="validate datepicker"
	                                                        placeholder="MRVC Reviewed">
	                                                    <button type="button" id="mrvc_reviewed_icon0" class="white"><i
	                                                            class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="Divisional Approval" class="input-field">
	                                                    <input id="divisional_approvals0" name="divisional_approvals" type="text"
	                                                        class="validate datepicker" placeholder="Divisional Approval">
	                                                    <button type="button" id="divisional_approval_icon0" class="white"><i
	                                                            class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="HQ approval" class="input-field">
	                                                    <input id="hq_approvals0" name="hq_approvals" type="text" class="validate datepicker"
	                                                        placeholder="HQ approval">
	                                                    <button type="button" id="hq_approval_icon0" class="white"><i
	                                                            class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head=" Revision Status" class="input-field">
	                                                    <select class="searchable" id="revision_status_fks0" name="revision_status_fks">
	                                                        <option value="" selected>Select </option>
	                                                          <c:forEach var="obj" items="${revisionStatuses }">
			                                    				<option value="${obj.revision_status }">${obj.revision_status }</option>
			                                  				  </c:forEach>
	                                                    </select>
	                                                </td>
	                                                <td data-head="Remarks" class="input-field">
	                                                    <input id="remarkss0" name="remarkss" type="text" class="validate"
	                                                        placeholder="Remarks">
	                                                </td>
	                                                <td class="mobile_btn_close">
	                                                    <a  class="btn waves-effect waves-light red t-c " onclick="removeRevision('0');"> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
	                                            </tr>
	                                       </c:otherwise>
                                        </c:choose>                                          
                                        </tbody>
                                    </table>
 									<table class="mdl-data-table">
                                        <tbody id="revTableBody">                                          
			                                    <tr>
			  										 <td colspan="9" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addRevisionRow()"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
                                    
                                    <c:choose>
                                        <c:when test="${not empty designDetails.designRevisions && fn:length(designDetails.designRevisions) gt 0 }">
                                            <input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(designDetails.designRevisions)}" />
                                        </c:when>
                                        <c:otherwise>
                                        	<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                        </c:otherwise>
                                    </c:choose> 
                                </div>
                            </div>   
                                                
 						<div class="container container-no-margin">
                            <div class="row" style="margin-top: 20px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row">
                                        <div class="col m6 s12">
                                            <div class="file-field input-field">
                                                <div class="btn bg-m">
                                                    <span>Attachment</span>
                                                    <input type="file" id="designFile" name="designFile">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text" name="attachment" value="${designDetails.attachment }">
                                                </div>
                                            </div>
                                            
                                            <c:if test="${not empty designDetails.attachment }">
		                                       	<a href="<%=CommonConstants2.DESIGN_FILES %>${designDetails.attachment }" class="filevalue" download>${designDetails.attachment }</a>
		                                   	</c:if>
		                                   	
                                        </div>
                                        <div class="col m6 s12">
                                            <div class="row">
                                                <div class="col s4 m6 input-field center-align">
                                                    <p style="margin-top: 10px;">Any Issue ?</p>
                                                </div>
                                                <div class="col s8 m6 input-field">
                                                    <p class="radiogroup"
                                                        style="padding-bottom: 10px;padding-top: 10px;">
                                                        <label>
                                                            <input class="with-gap" name="is_there_issue" type="radio"
                                                                value="yes" />
                                                            <span>Yes</span>
                                                        </label> &nbsp; <label>
                                                            <input class="with-gap" name="is_there_issue" type="radio"
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
                                <div class="row" style="margin-bottom:20px;">
                                    <h6 class="center-align" style="color:#2E58AD;font-weight:600">Issue Details </h6>
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col m8 s12">
                                        <div class="row">
                                            <div class="col s12 m6 input-field" style="margin-top: 20px;">
                                                <p class="searchable_label">Issue Category</p>
                                                <select id="issue_category_id" name="issue_category_id" class="searchable">
                                                    <option value="">Select</option>
                                                    <c:forEach var="obj" items="${issueCategoryList }">
                                                    	<option value="${obj.category }">${obj.category }</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col s12 m6 input-field" style="padding-top: 14px;">
                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="Low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="Medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="High" />
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
                                        <textarea id="issue_description" name="issue_description" class="materialize-textarea"
                                            data-length="500"></textarea>
                                        <label for="issue_description">Issue Description</label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${designDetails.remarks }</textarea>
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
	                                   <%--  <c:if test="${action eq 'add'}"> --%>
	                                        <button type="button" onclick="addDesign();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
	                                    <%-- </c:if> --%>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/design" class="btn waves-effect waves-light bg-s"
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

 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

	
<!-- 	<script src="/pmis/resources/js/dataTables.material.min.js"></script> -->


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
            $('#remarks,#issueDesc').characterCounter();
            

            $('input[name=is_there_issue]').change(function () {
                console.log( $('input[name=is_there_issue]:checked'));
                var radioval = $('input[name=is_there_issue]:checked').val();
                if (radioval == 'yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#issue_yes').css("display", "none");
                }
            });
            
            $('input[name=divisional_submission_fk]').change(function () {
                var radioval = $('input[name=divisional_submission_fk]:checked').val();
                if (radioval == 'Yes') {
                    $('.divisional_submission_fk').css("display", "block");
                }
                else if (radioval == 'No') {
                    $('.divisional_submission_fk').css("display", "none");
                }
            });
            
            $('input[name=hq_submission_fk]').change(function () {
                var radioval = $('input[name=hq_submission_fk]:checked').val();
                if (radioval == 'Yes') {
                    $('.hq_submission_fk').css("display", "block");
                }
                else if (radioval == 'No') {
                    $('.hq_submission_fk').css("display", "none");
                }
            });
            
            var divisional_submission_fk = "${designDetails.divisional_submission_fk}";
            if($.trim(divisional_submission_fk) == 'Yes' ){
            	$('.divisional_submission_fk').css("display", "block");
            }
            
            var hq_submission_fk = "${designDetails.hq_submission_fk}";
            if($.trim(hq_submission_fk) == 'Yes' ){
            	$('.hq_submission_fk').css("display", "block");
            }
            
            
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
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForDesignForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${designDetails.work_id_fk}";
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
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForDesignForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                var contract_id_fk = "${designDetails.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForDesignForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
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
                $('.searchable').select2();
            }
       		
        }
        
        function addDesign(){
        	if(validator.form()){ // validation perform
	   			$(".page-loader").show();
	   			$('form input[name=revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=consultant_submissions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=mrvc_revieweds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=divisional_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=hq_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revision_status_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("designForm").submit();	
        	}
    	}
    
    	function updateDesign(){		
    		if(validator.form()){ // validation perform
				$(".page-loader").show();
				$('form input[name=revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=consultant_submissions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=mrvc_revieweds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=divisional_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=hq_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revision_status_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("designForm").submit();		
    		}
		}
    	   
    	var validator = $('#designForm').validate({
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
    				 	  },"department_id_fk": {
    				 		required: true
    				 	  },"consultant_contract_id_fk": {
    				 		required: false
    				 	  },"proof_consultant_contract_id_fk": {
    				 		required: false
    				 	  },"consultant_submission": {
       				 		required: false
       				 	  },"mrvc_reviewed": {
    				 		required: false,
       				 		dateBefore1:"#consultant_submission"
    				 	  },"divisional_approval": {
    				 		required: false,
       				 		dateBefore2:"#mrvc_reviewed"
    				 	  },"hq_approval": {
    				 		required: false,
       				 		dateBefore3:"#divisional_approval"
    				 	  },"gfc_released": {
    				 		required: false,
       				 		dateBefore4:"#hq_approval"
    				 	  },"as_built_date": {
    				 		required: false,
       				 		dateBefore5:"#gfc_released"
    				 	  },"hod": {
       				 		required: true
       				 	  },"dy_hod": {
    				 		required: true
    				 	  },"prepared_by_id_fk": {
    				 		required: true
    				 	  },"structure_type_fk": {
    				 		required: true
    				 	  },"drawing_type_fk": {
    				 		required: true
    				 	  },"drawing_title": {
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
	   			 	  	 },"department_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"consultant_contract_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"proof_consultant_contract_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"hod": {
	   			 	  		required: 'Required'
	    				 },"dy_hod": {
	    					required: 'Required'
	   				 	 },"prepared_by_id_fk": {
	   				 		required: 'Required'
	   				 	 },"structure_type_fk": {
	   				 		required: 'Required'
	   				 	 },"drawing_type_fk": {
	   				 		required: 'Required'
	   				 	 },"drawing_title": {
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
    			 	    }else if (element.attr("id") == "department_id_fk" ){
    			 		     document.getElementById("department_id_fkError").innerHTML="";
    			 			 error.appendTo('#department_id_fkError');
    			 	    }else if (element.attr("id") == "consultant_contract_id_fk" ){
    			 		     document.getElementById("consultant_contract_id_fkError").innerHTML="";
    			 			 error.appendTo('#consultant_contract_id_fkError');
    			 	    }else if (element.attr("id") == "proof_consultant_contract_id_fk" ){
    			 		     document.getElementById("proof_consultant_contract_id_fkError").innerHTML="";
    			 			 error.appendTo('#proof_consultant_contract_id_fkError');
    			 	    }else if (element.attr("id") == "consultant_submission" ){
     			 		     document.getElementById("consultant_submissionError").innerHTML="";
     			 			 error.appendTo('#consultant_submissionError');
     			 	    }else if (element.attr("id") == "mrvc_reviewed" ){
    			 		     document.getElementById("mrvc_reviewedError").innerHTML="";
    			 			 error.appendTo('#mrvc_reviewedError');
    			 	    }else if (element.attr("id") == "divisional_approval" ){
    			 	    	 document.getElementById("divisional_approvalError").innerHTML="";
    			 			 error.appendTo('#divisional_approvalError');
    			 	    }else if (element.attr("id") == "hq_approval" ){
    			 		     document.getElementById("hq_approvalError").innerHTML="";
    			 			 error.appendTo('#hq_approvalError');
    			 	    }else if (element.attr("id") == "gfc_released" ){
    			 		     document.getElementById("gfc_releasedError").innerHTML="";
    			 			 error.appendTo('#gfc_releasedError');
    			 	    }else if (element.attr("id") == "as_built_date" ){
    			 		     document.getElementById("as_built_dateError").innerHTML="";
    			 			 error.appendTo('#as_built_dateError');
    			 	    }else  if (element.attr("id") == "hod" ){
     			 		     document.getElementById("hodError").innerHTML="";
     			 			 error.appendTo('#hodError');
     			 	    }else if (element.attr("id") == "dy_hod" ){
    			 		     document.getElementById("dy_hodError").innerHTML="";
    			 			 error.appendTo('#dy_hodError');
    			 	    }else if (element.attr("id") == "prepared_by_id_fk" ){
    			 	    	 document.getElementById("prepared_by_id_fkError").innerHTML="";
    			 			 error.appendTo('#prepared_by_id_fkError');
    			 	    }else if (element.attr("id") == "structure_type_fk" ){
    			 		     document.getElementById("structure_type_fkError").innerHTML="";
    			 			 error.appendTo('#structure_type_fkError');
    			 	    }else if (element.attr("id") == "drawing_type_fk" ){
    			 		     document.getElementById("drawing_type_fkError").innerHTML="";
    			 			 error.appendTo('#drawing_type_fkError');
    			 	    }else if (element.attr("id") == "drawing_title" ){
    			 		     document.getElementById("drawing_titleError").innerHTML="";
    			 			 error.appendTo('#drawing_titleError');
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
    	
    	
	    	$.validator.addMethod("dateBefore1", function(value, element) {
	            var fromDateString = $('#consultant_submission').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	            
	        }, "MRVC reviewed date must be after Consultant submission date");
	    	
	    	$.validator.addMethod("dateBefore2", function(value, element) {
	            var fromDateString = $('#mrvc_reviewed').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Divisional approval date must be after MRVC reviewed date");
	    	
	    	$.validator.addMethod("dateBefore3", function(value, element) {
	            var fromDateString = $('#divisional_approval').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "HQ approval date must be after Divisional approval date");
	    	
	    	var field = "";
	    	var messager = function() {
                return field;
            };
	            
	    	$.validator.addMethod("dateBefore4", function(value, element) {
	    		var fromDateString = "";
	    		var hq_approval = $('#hq_approval').val(); //
	            var mrvc_reviewed = $('#mrvc_reviewed').val();
	            var divisional_approval = $('#divisional_approval').val();
	            
	            if($.trim(mrvc_reviewed) != ''){
	            	field = "GFC released date must be after MRVC reviewed date";
	            	fromDateString = mrvc_reviewed;
	            }
	            
	            if($.trim(divisional_approval) != ''){
	            	field = "GFC released date must be after Divisional approval date";
	            	fromDateString = divisional_approval;
	            }
	            
	            if($.trim(hq_approval) != ''){
	            	field = "GFC released date must be after HQ approval date ";
	            	fromDateString = hq_approval;
	            }
				
				
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        },  messager); 
	            
	    	/* $.validator.addMethod("dateBefore4", function(value, element) {
	    		var fromDateString = $('#hq_approval').val();				
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        },  "GFC released date must be after HQ approval date"); */
	    	
	    	$.validator.addMethod("dateBefore5", function(value, element) {
	            var fromDateString = $('#gfc_released').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) < Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "As built date date must be after GFC released date");
	    	
    	
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
        	   //     $(this).valid();
        	    }
        	});
            
    

	  function addRevisionRow(){
		
      var rowNo = $("#rowNo").val();
      var rNo = Number(rowNo)+1;
	//console.log("rowno= "+rowNo+" rno= "+rNo);
      var html ='<tr id="revisionRow'+rNo+'"> '
		      +'<td data-head="Revision" class="input-field"> <input id="revisions'+rNo+'" name="revisions" type="text" class="validate" placeholder="Revision"></td>'
		      +'<td data-head="Consultant Submission" class="input-field"><input id="consultant_submissions'+rNo+'" name="consultant_submissions" type="text" class="validate datepicker" placeholder="Consultant Submission"><button type="button" id="consultant_submissions_icon'+rNo+'" class="white"><i class="fa fa-calendar"></i></button> </td>'
		      +'<td data-head="MRVC Reviewed" class="input-field"><input id="mrvc_revieweds'+rNo+'" name="mrvc_revieweds" type="text" class="validate datepicker" placeholder="MRVC Reviewed"><button type="button" id="mrvc_reviewed_icon'+rNo+'" class="white"><i class="fa fa-calendar"></i></button></td>'
		      +'<td data-head="Divisional Approval" class="input-field"><input id="divisional_approvals'+rNo+'" name="divisional_approvals" type="text" class="validate datepicker" placeholder="Divisional Approval"> <button type="button" id="divisional_approval_icon'+rNo+'" class="white"><i class="fa fa-calendar"></i></button></td>'
			  +'<td data-head="HQ approval" class="input-field"><input id="hq_approvals'+rNo+'" name="hq_approvals" type="text" class="validate datepicker" placeholder="HQ approval"> <button type="button" id="hq_approval_icon'+rNo+'" class="white"><i class="fa fa-calendar"></i></button> </td>'
			  +'<td data-head="Revision Status" class="input-field">'
			  +'<select class="searchable" id="revision_status_fks'+rNo+'" name="revision_status_fks">'
		      +'<option value="" selected>Select </option>'
		        <c:forEach var="obj" items="${revisionStatuses }">
			  	  +'<option value="${obj.revision_status }">${obj.revision_status }</option>'
				</c:forEach>
			  +'</select></td>'
			  +'<td data-head="Remarks" class="input-field"> <input id="remarkss'+rNo+'" name="remarkss" type="text" class="validate" placeholder="Remarks"></td>'
			  +'<td class="mobile_btn_close"><a class="btn waves-effect waves-light red t-c " onclick="removeRevision('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
				
			  $('#revisionsTableBody').append(html);
			  $("#rowNo").val(rNo);
			  // $('select').formSelect();
		      $('.searchable').select2();
		      

	            $(document).on('focus', '.datepicker',function(){
			        $(this).datepicker({
			        	format:'dd-mm-yyyy',
			   	    	onSelect: function () {
			   	    	   $('.confirmation-btns .datepicker-done').click();
			   	    	}
			        })
			    });
	  }
	  
	  function removeRevision(rowNo){
	    	$("#revisionRow"+rowNo).remove();
	    }
    
    
    </script>

</body>
</html>