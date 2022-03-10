<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>
		<c:if test="${action eq 'edit'}">Update Structure Form - Update Forms - PMIS</c:if>
		<c:if test="${action eq 'add'}"> Add Structure Form - Update Forms - PMIS</c:if>
	</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">	
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<!-- <link rel="stylesheet" href="/pmis/resources/css/fob.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
	 <style>
	 	.select2-selection.select2-selection--single{
	 		margin-bottom:5px;
	 	}
	 	td .select2-selection.select2-selection--single{
	 		margin-bottom:0;
	 	}
        .fixed-width {
            width: 100%;
            margin-left :auto !important;
            margin-right :auto !important;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }       
		
		td .btn.red{
			z-index:0;
		}
		.filevalue {
            display: block;
            margin-top: 10px;
        }
		.input-field>label.small{
			font-size: 0.88rem !important;
		}
		/*table with fixed header & height ends */
		.error-msg label{color:red!important;}
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
		.input-field .searchable_label{
			font-size:.85rem;
		}
		.col .center-align.m-1 button.bg-m, .center-align.m-1 button.bg-s{
			width:inherit;
		}	
		/* cost unit dropdown , lable and input styling starts here  */
		.pt-10{
			padding-top:10px !important;
		}
		.cost_dropdown{
			min-width:95px !important;
		}
		/* cost unit dropdown , lable and input styling ends here  */
		
		#gallery_table .datepicker~button {
            top: 25px;
            right:20px;
        }
        .mt9px{margin-top: 9px !important}
        .w7em{width: 7em;}
        .bd-none{border:none !important;background: transparent}
        #structureContractResponsibleTableBody .input-field:not(.h-auto) .select2-container--default{
    		max-width:400px;
    	}
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.table-add.sm{width:80%;}
		.add-align{position: absolute;
   					 margin-top: -5.8em;
   					 margin-left: 26%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:36%;}
    	}
    	@media(max-width: 820px){
    	.add-align{position: relative; margin-top: 0; margin-left:0 !important;}
    	.bd-none{border: 1px solid rgba(0,0,0,0.12) !important;}
    	.table-add{position: relative;}
    	#structureContractResponsibleTableBody .input-field:not(.h-auto) .select2-container--default{
    		max-width:200px;
    		}
    	.table-add.sm {
		    width: 100%;
			}
		.table-add{
		 width: 100%;
			}
    	}
        @media only screen and (max-width: 768px){
        	.mobile_responsible_table>tbody >tr:not(.datepicker-row)> td> div.btn{
				float:none;
				position:relative;
			}
			[name="created_dates"]{
				width:92% !important;
			}
			#gallery_table .datepicker~button{
				top:0;
			}
			.h-auto{
				height:auto !important;
			}
			 .filevalue {
			    width: 200%;
			    white-space: break-spaces;
			}
			td.cell-disp-inb .file-path-wrapper {
			    visibility: visible;
			    width: 200%;
			    display: block !important;
			}
			.select2-container--default .select2-selection--multiple, .select2-container--default.select2-container--focus .select2-selection--multiple {
			    box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
			    background-color: transparent;
			    padding: 5px;
			    white-space: pre-wrap;
			}
        }
       /*  datepicker styling to its defaults */
    .mobile_responsible_table .datepicker-table .datepicker-row td {
	  padding:0
	}

    .mobile_responsible_table>tbody .datepicker-table tr{
        background-color: transparent;
    }
    
    .min-180{
    	min-width: 180px;
    }
    .min-200{
    	min-width: 200px;
    }
    #gallery_table .select2-container--default .select2-selection--single,
    #fobDocumentTableBody .select2-container--default .select2-selection--single {
    	background-color:transparent;
    }
    @media(max-width: 575px){
    	.row .col{margin: 6px auto;}
    	h5{margin: 1.0933333333rem 0 .656rem 0;}
    	.select2-container--default .select2-selection--multiple .select2-selection__choice__display{
    		white-space: pre-wrap;
    	}
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
                        <span class="card-commissioning_date headbg card-title">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                	<c:if test="${action eq 'edit'}">Update Structure Form</c:if>
									<c:if test="${action eq 'add'}"> Add Structure Form</c:if>
								</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                          <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-structure-form" id="structuresForm" name="structuresForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-structures" id="structuresForm" name="structuresForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Project<span class="required">*</span></p>
                                <input type="hidden" name="structure_id" id="structure_id" value="${structuresListDetails.structure_id}">
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);" <c:if test="${not empty structuresListDetails.project_id_fk}">disabled</c:if>>
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id_fk }" <c:if test="${obj.project_id_fk eq structuresListDetails.project_id_fk}">selected</c:if>><%-- ${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> --%> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>                                   
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                	<p class="searchable_label"> Work<span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="resetProject();getContractsList(this.value);" <c:if test="${not empty structuresListDetails.work_id_fk}">disabled</c:if>>
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${obj.work_id_fk}" <c:if test="${obj.work_id_fk eq structuresListDetails.work_id_fk}">selected</c:if>> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                	<p class="searchable_label"> Structure Type<span class="required">*</span></p>
                                 	<select id="structure_type_fk" name="structure_type_fk" class="validate-dropdown searchable" >
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${structuresList }">
	                                  		<option value="${obj.structure_type }" <c:if test="${structuresListDetails.structure_type_fk eq obj.structure_type}">selected</c:if>>${obj.structure_type}</option>
	                                     </c:forEach> 
                                     </select>
                                     <span id="structure_type_fkError" class="error-msg" ></span>
                            	</div>
                            	
                            	                                <div class="col s6 m4 l4 input-field">
                                    <input id="structure_name" name="structure_name" type="text" class="validate" <c:if test="${action eq 'edit'}">readonly</c:if> value="${structuresListDetails.structure_name }" >
                                    <label for="structure_name">Structure Name <span class="required">*</span></label>
                                    <span id="structure_nameError" class="error-msg" ></span>
                                </div>
                           		
                           		<%-- <div style="display:none">
									<c:forEach var="obj" items="${fobIdCheck}" varStatus="index">
										<input type="hidden" id="structure${index.count}" value="${obj.structure }"  class="findLengths"/>
									</c:forEach>
								</div>          --%>                  
                                <div class="col s6 m4 l4 input-field" id="hideForFOB"  >
                                    <input id="structure" name="structure" type="text" class="validate" value="${structuresListDetails.structure }"  <c:if test="${not empty structuresListDetails.structure}">readonly</c:if>>
                                    <label for="structure">Structure ID <span class="required">*</span></label>
                                    <span id="structureError" class="error-msg" ></span>
                                </div>
                                
                                 <div class="col s6 m4 l4 input-field" id="showForFOB" style=" display: none;">
                                    <input id="fob_id" name="structure" type="text" class="validate" value="${structuresListDetails.structure }" onkeyup="doValidate(this.value)" <c:if test="${not empty structuresListDetails.structure}">readonly</c:if>>
                                    <label for="structure">Structure ID <span class="required">*</span></label>
                                    <span id="structureError" class="error-msg" ></span>
                                </div>
                             
                                <div class="col s6 m4 l4 input-field mt9px">
                                    <p class="searchable_label">Work Status <span class="required">*</span></p>
                                    <select id="work_status_fk" name="work_status_fk"  class="searchable validate-dropdown" onchange="openDates(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${executionStatusList }">
                                        	<c:if test="${obj ne 'Closed' and obj ne 'Terminated' and obj ne 'Completed'}">
                                            	<option value="${obj }" <c:if test="${(empty structuresListDetails.work_status_fk and obj eq 'Not Started') or (obj eq structuresListDetails.work_status_fk)}">selected</c:if> >${obj}</option>
                                        	</c:if>
                                        </c:forEach>
                                    </select>
                                    <span id="work_status_fkError" class="error-msg" ></span>
                                </div>
                                <input type="hidden" id="existing_work_status_fk" name="existing_work_status_fk" value="${structuresListDetails.work_status_fk }"/> 
                            </div>
                            <div class="row">

                           </div>
                         
                            
                            
	                         <div class="row " id="structureResponsiblePeopleDetails">
	                            <div class="row"> 
	                            	<div class="col m12 l12 s12">
										<div class="row fixed-width">
									       <h5 class="center-align"><span class="div-header">Contract - Execution Executives</span></h5> 
									        <div class="table-inside">
									            <table id="structureContractResponsibleTableBody" class="mdl-data-table mobile_responsible_table" >
									                <thead>
									                    <tr>
									                        <th style="width:45%">Contract <span class="required">*</span></th>
															<th style="width:45%;text-align : left;">Responsible Executives <span class="required">*</span></th>
															<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th>Action</th></c:if>
									                    </tr>
									                </thead>
									                
												    <tbody id="departmentTableBody">
									                <c:choose>
				                                        <c:when test="${not empty structuresListDetails.executivesList }" >
				                                		  <c:forEach var="contractObj" items="${structuresListDetails.executivesList }" varStatus="index"> 
				                                		  <c:set var="selVal" value="" />
											                  <tr id="departmentRow${index.count }">
											                        <td data-head="Department" class="input-field">
											                             <select class="searchable validate-dropdown" name="contracts_id_fk" id="contract_id_fk${index.count }" onChange="getResponsibleExecutives(${index.count });"
											                              <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}">disabled </c:if>>
											                                	<option value="" >Select</option>  
																		          <c:forEach var="obj" items="${contractsList }">
									 											  <c:set var="selVal" value="${contractObj.contract_id_fk}" />
									 											<option value= "${obj.contract_id_fk}" <c:if test="${contractObj.contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${ obj.contract_short_name}</option>
									 											                                          </c:forEach>
											                              </select> 
											                              <span id="deptError${index.count }" class="my-error"></span>
											                        </td>
											                        <td data-head="Select Executives" class="input-field h-auto">
											                        	<input type="hidden"  id="responsible_people_id_fk${index.count }" name="responsible_people_id_fks" />
											                            <select class="searchable validate-dropdown dept" name="excecutives" id="responsible_people_id_fks${index.count }" onchange="executivesToStringMethod('${index.count }');"
											                             multiple="multiple"
											                             <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}">disabled </c:if>>
											                             <option value="" disabled="disabled">Select</option>
										                                    <c:forEach var="obj" items="${responsiblePeopleList}">
																				<option value="${obj.user_id }" 
																					<c:forEach var="tempobj" items="${contractObj.responsiblePeopleLists}">
																			 			<c:if test="${tempobj.responsible_people_id_fk eq obj.user_id }">selected</c:if>
										                                          	</c:forEach>>${obj.designation }-${obj.user_name}</option>
																			</c:forEach>
											                            </select>
											                             <span id="personError${index.count }" class="my-error" ></span>
											                        </td>
									                				<c:choose>
								                                        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' || sessionScope.USER_TYPE eq 'DyHOD'}" >											                        
															                        <td class="mobile_btn_close">
															                            <a onclick="removeStructureContractResponsible('${index.count }');"
															                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
															                        </td>
															              </c:when>   
											                       </c:choose>
											                    </tr>
											                     <script>
													     			   var exvals =  $('#responsible_people_id_fks${index.count }').val();
													     			   exvals = exvals.join(',');
													            	   $('#responsible_people_id_fk${index.count }').val(exvals);
											                     </script>
									                	</c:forEach>
                                           			</c:when>
                                             		<c:otherwise>
									                    <tr id="departmentRow0">
									                        <td data-head="Department" class="input-field">
									                             <select class="searchable validate-dropdown dept" name="contracts_id_fk" id="contract_id_fk0" onChange="getResponsibleExecutives(0);"
									                             	<c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD'  && sessionScope.USER_TYPE ne 'DyHOD'}">disabled </c:if>> 
									                                	<option value="" >Select</option>  
																          <c:forEach var="obj" items="${contractsList }">
																			<option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }" 
																	 		>${obj.contract_short_name }</option>								                                         
																	 	 </c:forEach>
									                              </select>
									                              <span id="deptError0" class="my-error"></span>
									                        </td>
									                        <td data-head="Select Executives" class="input-field h-auto">
									                        <input type="hidden"  id="responsible_people_id_fk0" name="responsible_people_id_fks" />
									                            <select class="searchable validate-dropdown" name="excecutives"
									                               <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD'   && sessionScope.USER_TYPE ne 'DyHOD'}">disabled </c:if>
									                                id="responsible_people_id_fks0" multiple="multiple" onchange="executivesToStringMethod('0');">
									                                <option value="" >Select</option>
														 			 <c:forEach var="obj" items="${responsiblePeopleList}">
																		<option value="${obj.user_id }" >${obj.designation }-${obj.user_name}</option>
																	</c:forEach>			                             	
									                            </select>
									                            <span id="personError0" class="my-error"></span>
									                        </td>
							                				<c:choose>
						                                        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}" >										                        
											                        <td class="mobile_btn_close">
											                            <a onclick="removeStructureContractResponsible('0');"
											                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
											                        </td>
													            </c:when>   
									                       </c:choose>									                        
									                    </tr>
									              </c:otherwise>
                                            	</c:choose>
									                </tbody>									                

									               
												</table>
									            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
									            <table  class="mdl-data-table table-add bd-none">
			                                        <tbody>                                          
			                                            <tr class="bd-none">
			                                   				<td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c add-align"  onclick="addStructureContractRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table> 
			                                    </c:if>
			                                    <c:choose>
				                                    <c:when test="${not empty structuresListDetails.executivesList && fn:length(structuresListDetails.executivesList) gt 0 }">
				                                		<input type="hidden" id="structureContractRowNo"  name="structureContractRowNo" value="${fn:length(structuresListDetails.executivesList) }" />
				                                	</c:when>
				                                 	<c:otherwise>
				                                 		<input type="hidden" id="structureContractRowNo"  name="structureContractRowNo" value="0" />
				                                 	</c:otherwise>
				                                 </c:choose>
                                                                
                                  
							                                    
									        </div>
									    </div>
									</div>
								</div>
							</div>
                            
                            <br>
                           <div class="row">
	                            <div class="col s12 m6 l6 input-field">
                                    <input id="target_date" name="target_date" type="text" class="validate datepicker" value="${structuresListDetails.target_date }" <c:if test="${not empty structuresListDetails.target_date}">disabled</c:if>>
                                    <label for="target_date">Original Target Date </label>
                                    <button type="button" id="target_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="target_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6 l6 input-field amount-dropdown">
                                	<i class="material-icons amount-symbol cost">₹</i>   
                                    <input id="estimated_cost" name="estimated_cost" type="number" class="validate" value="${structuresListDetails.estimated_cost }" min="0.01" step="0.01" <c:if test="${not empty structuresListDetails.estimated_cost}">readonly</c:if>>
                                    <label for="estimated_cost">Estimated Cost</label>
                                    <span id="estimated_costError" class="error-msg" ></span> 
                                	<span id="estimated_cost_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="estimated_cost_units" name="estimated_cost_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${structuresListDetails.estimated_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                </div>
                          
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000" maxlength="1000">${structuresListDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            <c:if test="${action eq 'edit'}">	
                            <div class="row">
                                <div class="col s6 m6 l6 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${structuresListDetails.latitude }" <c:if test="${not empty structuresListDetails.latitude}">readonly</c:if>>
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m6 l6 input-field ">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${structuresListDetails.longitude }" <c:if test="${not empty structuresListDetails.longitude}">readonly</c:if>>
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                            </div>
                            </c:if>
                            
                            <div class="row">
                                <div class="col s6 m6 l6 input-field " id="construction_start_dateDiv" style=" display: none;">
                                    <input id="construction_start_date" name="construction_start_date" type="text" class="validate datepicker" value="${structuresListDetails.construction_start_date }" <c:if test="${not empty structuresListDetails.construction_start_date}">disabled</c:if>>
                                    <label for="construction_start_date" class="fs-sm-8rem">Construction Start Date </label>
                                    <button type="button" id="construction_start_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="construction_start_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m6 l6 input-field " id="revised_completionDiv" style=" display: none; ">
                                    <input id="revised_completion" name="revised_completion" type="text" class="validate datepicker" value="${structuresListDetails.revised_completion }" <c:if test="${not empty structuresListDetails.revised_completion}">disabled</c:if>>
                                    <label for="revised_completion" class="fs-sm-8rem">Target completion Date </label>
                                    <button type="button" id="revised_completion_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="revised_completionError" class="error-msg" ></span>
                                </div>                                                               
                            </div>
                            
							<c:if test="${action eq 'edit'}"> 
	                            <div class="row">
	                                <h5 class="center-align">Structure Details</h5>
	                                <div class="col s12 m12" id="structureFobDiv" style="display:none;">
	                                    <table id="structureDetailsTable" class="mdl-data-table" style="margin-bottom:20px;">
	                                        <thead>
	                                            <tr>
	                                                <th>Structure Detail</th>
	                                                <th>Value </th>
	                                                <!-- <th>Action</th> -->
	                                            </tr>
	                                        </thead>
	                                        <tbody id="structureDetailsTableBody">
	                                       		<c:forEach var="dObj" items="${structuresListDetails.structureDetailsList }" varStatus="index">                                        	
		                                           <tr id="structureDetailsRow${index.count }">                                            	
		                                               <td>
		                                                    <input id="structure_detail_names${index.count }" name="structure_details" type="text" readonly class="validate" value="${dObj.structure_detail }"
		                                                        placeholder="Detail name">
		                                                </td>
		                                                <td>
			                                                <c:if test="${dObj.structure_detail eq 'Type'}">
			                                                     <select id="structure_detail_values${index.count }" name="structure_values">
			                                                    	<option value="">Select</option>
			                                                    	<c:forEach var="obj" items="${structureDetailsTypes }"> 
			                                                    		<option value="${obj.fob_details_type }" <c:if test="${dObj.structure_value eq obj.fob_details_type }">selected</c:if>>${obj.fob_details_type }</option>
			                                                    	</c:forEach>
			                                                     </select>
			                                                </c:if>
			                                                <c:if test="${dObj.structure_detail eq 'Location of FOB'}">
			                                                     <select id="structure_detail_values${index.count }" name=structure_values>
			                                                    	<option value="">Select</option>
			                                                    	<c:forEach var="obj" items="${structureDetailsLocations }"> 
			                                                    		<option value="${obj.fob_details_location }" <c:if test="${dObj.structure_value eq obj.fob_details_location }">selected</c:if>>${obj.fob_details_location }</option>
			                                                    	</c:forEach>
			                                                     </select>
			                                                </c:if>
			                                                <c:if test="${(dObj.structure_detail ne 'Type') and (dObj.structure_detail ne 'Location of FOB') }">
			                                                    <input id="structure_detail_values${index.count }" name="structure_values" type="text" class="validate" value="${dObj.structure_value }"
			                                                        placeholder="Value">
			                                                </c:if>		                                                 
		                                                </td>
		                                            </tr>
	                                            </c:forEach> 
	                                     </tbody>
	                                   </table>
	                                </div>
	                                <div class="col s12 m12" id="structureNonFobDiv" style="display:none;">
							            <table id="structureDetailsTable" class="mdl-data-table">
							                <thead>
							                    <tr>
							                        <th>Structure Detail </th>
							                        <th>Value </th>
							                        <th>Action</th>
							                    </tr>
							                </thead>
							                <tbody id="structureNonFobDetailsTableBody">
							                <c:choose>
											<c:when test="${not empty structuresListDetails.structureDetailsList1 && fn:length(structuresListDetails.structureDetailsList1) gt 0 }">
											 <c:forEach var="valObj" items="${structuresListDetails.structureDetailsList1 }" varStatus="index"> 
											  	<tr id="structureDetailsRow${index.count }${index.count }">
							                        <td>
							                            <input id="structure_detail_names${index.count }${index.count }" name="structure_details" type="text"
							                                class="validate" placeholder="Detail name" value="${valObj.structure_detail }">
							                        </td>
							                        <td>
							                            <input id="structure_detail_values${index.count }${index.count }" name="structure_values" type="text"
							                                class="validate"  placeholder="Value" value="${valObj.structure_value }">
							                        </td>
							                        <td class="mobile_btn_close">
							                            <a onclick="removeStructureDetail(${index.count }${index.count });"
							                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
							                        </td>
							                    </tr>
											  </c:forEach>
										       </c:when>
												<c:otherwise>
							                    <tr id="structureDetailsRow0">
							                        <td>
							                            <input id="structure_detail_names" name="structure_details" type="text"
							                                class="validate" value="" placeholder="Detail name">
							                        </td>
							                        <td>
							                            <input id="structure_detail_values" name="structure_values" type="text"
							                                class="validate" value="" placeholder="Value">
							                        </td>
							                        <td class="mobile_btn_close">
							                            <a onclick="removeStructureDetail(0);"
							                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
							                        </td>
							                    </tr>
							                  </c:otherwise>
         									 </c:choose>
							                </tbody>
							            </table>
							              <c:choose>
		                                        <c:when test="${not empty sObj.structureDetailsList && fn:length(sObj.structureDetailsList) gt 0 }">
		                                    		<input type="hidden" id="structureDetailsLength${index.count }"   value="${fn:length(sObj.structureDetailsList) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="structureDetailsLength${index.count }"   value="0" />
		                                     	</c:otherwise>
		                                 </c:choose>
							            <table class="mdl-data-table table-add sm bd-none">
							                <tbody>
							                    <tr class="bd-none">
							                        <td colspan="3" class="bd-none"><a class="btn waves-effect waves-light add-align bg-m t-c"
							                                onclick="addstructureDetailRow()"> <i class="fa fa-plus"></i></a></td>
							                    </tr>
							                </tbody>
							            </table>
							        </div>
	                            </div>
                            </c:if>  
                           
                            <div class="row" style="margin-top:35px;">
                                <div class="col s12 m4 input-field" id="commissioning_dateDiv" style="display: none;">
                                    <input id="commissioning_date" name="commissioning_date" type="text" class="validate datepicker" value="${structuresListDetails.commissioning_date }" <c:if test="${not empty structuresListDetails.commissioning_date}">disabled</c:if>>
                                    <label for="commissioning_date">Commissioning Date </label>
                                    <button type="button" id="commissioning_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="commissioning_dateError" class="error-msg" ></span>
                                </div>
                           

	                             <!-- class="col m9 input-field" --><div id="actual_completion_dateDiv" style="display: none;">
	                                <div class="col m4 input-field">
	                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker" value="${structuresListDetails.actual_completion_date }" <c:if test="${not empty structuresListDetails.actual_completion_date}">disabled</c:if>>
	                                    <label for="actual_completion_date">Actual Completion Date </label>
	                                    <button type="button" id="actual_completion_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    <span id="actual_completion_dateError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m4 input-field amount-dropdown">
	                                	<i class="material-icons amount-symbol cost">₹</i>   
	                                    <input id="completion_cost" name="completion_cost" type="number" class="validate" min="0.01" step="0.01" value="${structuresListDetails.completion_cost }" <c:if test="${not empty structuresListDetails.completion_cost}">readonly</c:if>>
	                                    <label for="completion_cost">Actual Completion Cost</label>
	                                    <span id="completion_costError" class="error-msg" ></span>
	                                	<span id="completion_cost_unitsError" class="error-msg right" ></span>
	                                    <select class=" validate-dropdown" id="completion_cost_units" name="completion_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
	                                  			   <option value="${obj.value }" <c:if test="${structuresListDetails.completion_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                   		 </c:forEach>
	                                	</select>
	                                </div>
	                              <%--   <div class="col m4 input-field">
	                                	<p class="searchable_label">Units</p>
	                                	<select class="units validate-dropdown" id="completion_cost_units" name="completion_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
	                                  			   <option value="${obj.value }" <c:if test="${structuresListDetails.completion_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                   		 </c:forEach>
	                                	</select>
	                                	<span id="completion_cost_unitsError" class="error-msg" ></span>
	                                </div> --%>
	                            </div>
                           </div>
                            
                            <div class="row"> 
                                <div class="col m12 s12">
	                           								
                             <div class="row">
								<div class="col m112 l12 s12">
									<div class="row fixed-width">
                             			<h5 class="center-align"><span class="div-header">Documents</span></h5> 
										<!-- <div class="table-inside"> -->
											<table class="mdl-data-table update-table mobile_responsible_table" id="gallery_table">
												<thead>
													<tr>
														<th class="min-180">File Type</th>
														<th>Name</th>
														<!-- <th>Upload Date</th> -->
														<th>Attachment</th>
														<th style="display:none;"></th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="structureFilesBody">
													<c:choose>
														<c:when	test="${not empty structuresListDetails.documentsList && fn:length(structuresListDetails.documentsList) gt 0 }">
															<c:forEach var="fObj" items="${structuresListDetails.documentsList }" varStatus="index">
																<tr id="structureFileRow${index.count }">
																	<td data-head="File Type" class="input-field">
																			<select  name="structure_file_types"  id="structure_file_types${index.count }"  class="validate-dropdown searchable">
							                                   					 <option value="" >Select</option>
							                                         			  <c:forEach var="obj" items="${fileType}">
							                    					  				 <option value="${obj.structure_file_type }" <c:if test="${fObj.structure_file_type_fk eq obj.structure_file_type}">selected</c:if>>${obj.structure_file_type}</option>
							                                          			  </c:forEach>
							                               					  </select>
																	</td>
																	<td data-head="Name" class="input-field"> 
																		<input id="structureDocumentNames${index.count }" name="structureDocumentNames" type="text" class="validate" placeholder="Name"  value="${fObj.name }">
				                                                    </td>
			                                                      	<!-- <td data-head="Photo Date" class="input-field">
		                                                      			<span style='display:inline-block;'><input type="text" id="created_dates${index.count }" name="created_dates" placeholder="Uploaded date" value="${fObj.created_date}" class="validate datepicker" /><button type="button" id="created_dates${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></span>
			                                                      	</td> -->
																	<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">
								                                        <div class="t-c">
								                                            <input type="file" id="structureFiles${index.count }" name="structureFiles" accept="image/*">
								                                            <label for="structureFiles${index.count }" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="structureFileNames${index.count }" name="structureFileNames" value="${fObj.attachment }">
								                                        </div>                             
			                                                      	</td>
			                                                      	<td  style="display:none;">
			                                                      		<input type="hidden" id="structure_file_ids${index.count }" name="structure_file_ids" value="${fObj.structure_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.STRUCTURE_FILES%>${fObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                      	</td>
																	<td class="mobile_btn_close">
																		<a onclick="removeActions('${index.count }');" class="btn red"> 
																			<i class="fa fa-close"></i></a>
																	</td>
																</tr>
																						
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="structureFileRow0">
																<td data-head="File Type" class="input-field">
																		<select  name="structure_file_types"  id="structure_file_types0"  class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${fileType}">
						                    					  				 <option value="${obj.structure_file_type }">${obj.structure_file_type}</option>
						                                          			  </c:forEach>
						                               					  </select>
																</td>
																<td data-head="Name" class="input-field"> 
																	<input id="structureDocumentNames0" name="structureDocumentNames" type="text" class="validate" placeholder="Name">
				                                                </td>
				                                                <!-- <td data-head="Upload Date" class="input-field">
		                                                      		<span style='display:inline-block;'><input type="text" id="created_dates0" name="created_dates" placeholder="Upload date" class="validate datepicker" /><button type="button" id="created_dates0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></span>
																</td> -->
																<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">
							                                        <div class="t-c">
							                                            <input type="file" id="structureFiles0" name="structureFiles" accept="image/*">
							                                            <label for="structureFiles0" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
							                                        </div>
							                                        <div class="file-path-wrapper">
							                                            <input class="file-path validate" type="text" id="structureFileNames0" name="structureFileNames">
							                                        </div>                                       
		                                                      	</td>
		                                                      	
		                                                      	<td  style="display:none;"><input type="hidden" id="structure_file_ids0" name="structure_file_ids"/></td>
																<td class="mobile_btn_close">
																	<a onclick="removeActions('0');" class="btn red"> 
																		<i class="fa fa-close"></i></a>
																</td>
																
															</tr>
															
														</c:otherwise>
													</c:choose>
													
												</tbody>
											</table>
											<table class="mdl-data-table table-add bd-none">
												<tbody>
													<tr class="bd-none">
														<td colspan="5" class="bd-none"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c add-align"
															onclick="addStructureFileRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											
											<c:choose>
												<c:when test="${not empty (structuresListDetails.documentsList) && fn:length(structuresListDetails.documentsList) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo" value="${fn:length(structuresListDetails.documentsList) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										<!-- </div> -->
									</div>
								</div>
							</div>               
								
                            	</div>
								<div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col s6 l6 m6 mt-brdr">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateStructure();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addStructure();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
	                                  
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/structure-form" class="btn waves-effect waves-light bg-s" >Cancel</a>
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
	<script src="/pmis/resources/js/datepickerDepedency.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script type="text/javascript">

    let constructionstartdate = document.querySelectorAll('#construction_start_date');
    $.each(constructionstartdate, function(){
    	var dt = this.value.split(/[^0-9]/);
    	this.value = ""; 
    	var options = {format: 'dd-mm-yyyy',autoClose:true,maxDate:new Date(), onOpen:datePickerSelectAddClass};
    	if(dt.length > 1){
    		options.setDefaultDate = true,
    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
    	}
    	M.Datepicker.init(this, options);
    });
      
	$(document).ready(function () {
				
		$('select:not(.searchable):not(.units)').formSelect();
         $('.searchable:not(.units)').select2();
        $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
        $('#remarks').characterCounter();
                             
        $('#structure_type_fk').trigger('change');
        
        var project_id_fk = "${structuresListDetails.project_id_fk}";
        if ($.trim(project_id_fk) != '') {
            //getWorksList(project_id_fk);
        }
        
        var work_id_fk = "${structuresListDetails.work_id_fk}";
        if ($.trim(work_id_fk) != '') {
        	//getContractsList(work_id_fk);
        	//existedContarcts();
        }
        var structureType = "${structuresListDetails.structure_type_fk}";
        if ($.trim(structureType) != '' && structureType == 'FOB') {
        	$('#structureFobDiv').show();
			$('#structureNonFobDiv').hide();
			$('#structure').attr("disabled",true);
			$('#fob_id').attr("disabled",false);
			$("#structureNonFobDetailsTableBody").find("input,select").attr("disabled", true);
			$("#structureDetailsTableBody").find("input,select").attr("disabled", false);

        }else{
        	$('#structureFobDiv').hide();
			$('#structureNonFobDiv').show();
			$('#structure').attr("disabled",false);
			$('#fob_id').attr("disabled",true);
			$("#structureNonFobDetailsTableBody").find("input,select").attr("disabled", false);
			$("#structureDetailsTableBody").find("input,select").attr("disabled", true);
        }
        var work_status = "${structuresListDetails.work_status_fk}";
        if($.trim(work_status) != ''){
        	if($.trim(work_status) == 'In Progress'){
    			$("#construction_start_dateDiv").show();
    			$("#commissioning_dateDiv").hide();
    			$("#actual_completion_dateDiv").hide();
    			
    			$("#commissioning_date").val('');
    			$("#actual_completion_date").val('');
    			
    		}else if($.trim(work_status) == 'Commissioned'){
    			$("#construction_start_dateDiv").show();
    			$("#commissioning_dateDiv").show();
    			$("#actual_completion_dateDiv").show();
    			
    			
    			//$("#actual_completion_date").val('');
    		}else if($.trim(work_status) == 'Completed'){
    			$("#construction_start_dateDiv").show();
    			$("#commissioning_dateDiv").show();
    			$("#actual_completion_dateDiv").show();
    		}else{
    			$("#construction_start_dateDiv").hide();
    			$("#commissioning_dateDiv").hide();
    			$("#actual_completion_dateDiv").hide();
    			
    			$("#construction_start_date").val('');
    			$("#commissioning_date").val('');
    			$("#actual_completion_date").val('');
    		}
        	
        	if($.trim(work_status) == 'Not Started'){
        		$("#revised_completionDiv").hide();
        		$("#revised_completion").val('');
        	}else{
        		$("#revised_completionDiv").show();
        	}
        	
        	
        	var user_role_code = '${sessionScope.USER_ROLE_CODE}';
        	if(user_role_code == 'IT'){
        		$("#project_id_fk").attr('disabled', false);
      			$("#work_id_fk").attr('disabled', false);
      			$("#contract_id_fk").attr('disabled', false);
      			$("#responsible_people_id_fk").attr('disabled', false);
      			$("#target_date").attr('disabled', false);
      			$("#construction_start_date").attr('disabled', false);
      			$("#revised_completion").attr('disabled', false);
      			$("#commissioning_date").attr('disabled', false);
      			$("#actual_completion_date").attr('disabled', false);
      			
      			$("#structure_name").attr('readonly', false);
      			$("#estimated_cost").attr('readonly', false);
      			$("#latitude").attr('readonly', false);
      			$("#longitude").attr('readonly', false);
      			$("#completion_cost").attr('readonly', false);
        	}
        	
        }
        
        var size = '${fn:length(structuresListDetails.documentsList )}';
        
        /********************************************************************************/
        
    });
	
	var flag = false; 
    function doValidate(value){
 	   var print_value = value;	
 	   var value = value.trim();
 	   value = value.toLowerCase();
 	   var validate = $('.findLengths').length;
 	   if(validate == 0){flag = true;}
 	   var count  = 0;
 	   var ek = $('.findLengths').map((_,el) => el.value).get();
 	   while(count < validate){
 		   var findVal = ek[count];
 		   findVal = findVal.toLowerCase();
 		   if(findVal == value){
 			   $('#structureError').text(print_value+' alreday exists').css('color', 'red');
 			   //$('#bttn').prop('disabled', true);
 			   flag = false;
 			   return false;
 		   }else{
 			   $('#structureError').text('');
 			  // $('#bttn').prop('disabled', false); 
 			   flag = true;
 		   }
 		   
 		   count++;
 	   }
    }
    
    
    function executivesToStringMethod(Rno){
    	var vals =  $('#responsible_people_id_fks'+Rno).val();
  	    vals = vals.join(',');
  	   $('#responsible_people_id_fk'+Rno).val(vals);
    }   
    
    
    function addStructureContractRow(){
   	 var rowNo = $("#structureContractRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var no = 0;
		 
				 
		 var html = '<tr id="departmentRow'+rNo+'">'
			   +'<td data-head="Department" class="input-field">'+
			   <c:choose>
		        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">                                 
		         '<select  class="searchable validate-dropdown" name="contracts_id_fk" id="contract_id_fk'+rNo+'" onChange="getResponsibleExecutives('+rNo+');"> '
         		 +'<option value="">Select</option>'+
                 <c:forEach var="obj" items="${contractsList}">
			 		'<option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }" >${obj.contract_short_name }</option>'+
                 </c:forEach>
         '</select>'+
         </c:when>
         <c:otherwise>
         '<select  class="searchable validate-dropdown" name="contracts_id_fk" id="contract_id_fk'+rNo+'" onChange="getResponsibleExecutives('+rNo+');" disabled >'+
         		 '<option value="">Select</option>'+
                 <c:forEach var="obj" items="${contractsList}">
			 		'<option workId="${obj.work_id_fk }" value="${obj.contract_id_fk }">${obj.contract_short_name }</option>'
                 </c:forEach>
         +'</select>' +                               
         </c:otherwise>
         </c:choose>			   
         '</td>'
			   +'<td data-head="Select Executives" class="input-field h-auto">'+
			   
	
	    <c:choose>
         <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">   
         ' <input type="hidden"  id="responsible_people_id_fk'+rNo+'" name="responsible_people_id_fks" />'+
          '<select  class="searchable validate-dropdown"  name="excecutives" id="responsible_people_id_fks'+rNo+'" onchange="executivesToStringMethod('+rNo+');"  multiple="multiple">'+
          '<option value="" disabled="disabled">Select</option>'+
          <c:forEach var="obj" items="${responsiblePeopleList}">
		  			 '<option value="${obj.user_id }"> ${obj.designation} - ${obj.user_name}</option>'+
          </c:forEach> 
        '</select>'+
         </c:when>
         <c:otherwise>
         ' <input type="hidden"  id="responsible_people_id_fk'+rNo+'" name="responsible_people_id_fks" />'+
		 '<select  class="searchable validate-dropdown"  name="excecutives" id="responsible_people_id_fks'+rNo+'"  onchange="executivesToStringMethod('+rNo+');" multiple="multiple" disabled>'+
                                          
          '<option value="" disabled="disabled">Select</option>'+
           <c:forEach var="obj" items="${responsiblePeopleList}">
		  			 '<option value="${obj.user_id }" > ${obj.designation} - ${obj.user_name}</option>'+
          </c:forEach> 
         '</select>'+  
         
          </c:otherwise>
         </c:choose>   			   
			   
			   		'</td>'
			   +'<td class="mobile_btn_close"> <a onclick="removeStructureContractResponsible('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			 $('#structureContractResponsibleTableBody').append(html); 
			 $("#structureContractRowNo").val(rNo);
			  $('.searchable:not(.units)').select2();
			 getContractsByRowList(rNo);
			 

   }    
    
    
    function removeStructureContractResponsible(rowNo){
    	$("#departmentRow"+rowNo).remove();
    }   
    
	function addstructureDetailRow(){
		 var rowNo = $("#structureDetailsLength").val();
		 var rNo = Number(rowNo)+1;
		// var x = Math.floor(Math.random() * (500 - 900 + 1) + 900)
		// var index = rNo+ind+x;
		 var html = '<tr id="structureDetailsRow'+rNo+'">'
	            +'<td><input id="structure_detail_names'+rNo+'" name="structure_details" type="text" class="validate" placeholder="Detail name"></td> <td>'
	            +'<input id="structure_detail_values'+rNo+'" name="structure_values" type="text" class="validate" value="" placeholder="Value"></td> <td class="mobile_btn_close">'
	            +'<a onclick="removeStructureDetail('+rNo+');"class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
	            
		 $('#structureNonFobDetailsTableBody').append(html); 
		 $("#structureDetailsLength").val(rNo);
		  $('.searchable:not(.units)').select2(); 
		 $('select:not(.searchable)').formSelect();
	}
    
	function removeStructureDetail(no){
		$("#structureDetailsRow"+no).remove();
	}
    function getResponsibleExecutives(num)
    {
        	$(".page-loader").show();
        	var count = Number(num);
        	$('#deptError'+count).hide();
        	var contract_id_fk = $('#contract_id_fk'+count).val();

        	$("#responsible_people_id_fks"+count+" option:not(:first)").attr("selected",false);
            if ($.trim(contract_id_fk) != "") {
            	$("#responsible_people_id_fks"+count+" option:not(:first)").remove();
                var myParams = { contract_id_fk: contract_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getResponsibleExecutives",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var userName = '';
	                        	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
                                 var designation = '';
                                 if ($.trim(val.designation) != '') { designation = $.trim(val.designation) }

                                 $("#responsible_people_id_fks"+count).append('<option  value="' + val.user_id + '" >'  +  $.trim(designation) + $.trim(userName) +'</option>');
                            });
                        }
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
    }
    
    
    function openDates(work_status) {
		if($.trim(work_status) == 'In Progress' || $.trim(work_status) == 'On Hold' || $.trim(work_status) == 'Dropped'){
			$("#construction_start_dateDiv").show();
			$("#commissioning_dateDiv").hide();
			$("#actual_completion_dateDiv").hide();
			
			
			$("#commissioning_date").val('');
			$("#actual_completion_date").val('');
			
		}else if($.trim(work_status) == 'Commissioned'){
			$("#construction_start_dateDiv").show();
			$("#commissioning_dateDiv").show();
			$("#actual_completion_dateDiv").show();
			
			$("#actual_completion_date").val('');
			
		}else if($.trim(work_status) == 'Completed'){
			$("#construction_start_dateDiv").show();
			$("#commissioning_dateDiv").show();
			$("#actual_completion_dateDiv").show();
		}else{
			$("#construction_start_dateDiv").hide();
			$("#commissioning_dateDiv").hide();
			$("#actual_completion_dateDiv").hide();
			
			$("#construction_start_date").val('');
			$("#commissioning_date").val('');
			$("#actual_completion_date").val('');
		}
		
		if($.trim(work_status) == 'Not Started'){
    		$("#revised_completionDiv").hide();
    		$("#revised_completion").val('');
    	}else{
    		$("#revised_completionDiv").show();
    	}
		
	}
    
    function selectFile(no){
	    files = $("#structureFiles"+no)[0].files;
	    var html = "";
	    for (var i = 0; i < files.length ; i++) {
	    	html =  html + '<div id=structureFileNames'+no+'>'
			 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
			 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
			 + '</div>'
			 + '<div style="clear:both;"></div>';
	    }
	    $("#selectedFiles").append(html);
	    
	    $('#structureFilesDiv'+no).hide();
	    
		var fileIndex = Number(no)+1;
		moreFiles(fileIndex);
	}
	
	function moreFiles(no){
		var html = "";
		html =  html + '<div class="file-field input-field" id="structureFilesDiv'+no+'" >'
		+ '<div class="btn bg-m t-c">'
		+ '<span>Attach Photos</span>'
		+ '<input type="file" id="structureFiles'+no+'" name="structureFiles" onchange="selectFile('+no+')">'
		+ '</div>'
		+ '<div class="file-path-wrapper">'
		+ '<input class="file-path validate" type="text">'
		+ '</div>'                          
		+ '</div>'
		$("#selectedFilesInput").append(html);
	}
	
	function removeFile(no){			
     	$('#structureFilesDiv'+no).remove();
     	$('#structureFileNames'+no).remove();
    } 
	
	function addStructureFileRow(){
		var rowNo = $("#rowNo").val();
        var rNo = Number(rowNo)+1;
        var html = '<tr id="structureFileRow' + rNo + '">'
           +'<td data-head="File Type" class="input-field"> '
           +'<select name="structure_file_types" id="structure_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
   		   +'<option value="" >Select</option>'
		     <c:forEach var="obj" items="${fileType}">
     	      +'<option value="${obj.structure_file_type }">${obj.structure_file_type}</option>'
		     </c:forEach>
   		   +'</select></td>'  		  			
		   +'<td data-head="Name" class="input-field">'
		   +'<input id="structureDocumentNames'+rNo+'" name="structureDocumentNames" type="text" class="validate" placeholder="Name">'
		   +'</td>'
		   //+'<td data-head="Photo Date" class="input-field">'
		   //+'<span style="display:inline-block;"><input type="text" id="created_dates'+rNo+'" name="created_dates" placeholder="Upload date" class="validate datepicker" /><button type="button" id="created_dates'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></span>'
		   //+'</td>' 
   		   +'<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">'	
		   +'<div class="t-c">'	
		   +'<label for="structureFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'	
		   +'<input type="file" id="structureFiles'+rNo+'" name="structureFiles" accept="image/*">'	
		   +'</div>'	
		   +'<div class="file-path-wrapper">'	
		   +'<input class="file-path validate" type="text" id="structureFileNames'+rNo+'" name="structureFileNames">'	
		   +'</div></td>'		  
		   +'<td style="display:none;"><a ></a><input type="hidden" id="structure_file_ids'+rNo+'" name="structure_file_ids"/></td>'
		   +'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
		   +'</tr>';
	
		$('#structureFilesBody').append(html);
        $("#rowNo").val(rNo);          	
        
         $('.searchable:not(.units)').select2();
        /* 
        $("#created_dates"+rNo).datepicker({
        	 format:'dd-mm-yyyy',
 	       	 autoClose:true,
 	       	 onOpen: datePickerSelectAddClass
        });   */     
    }
	 function removeActions(rowNo){
     	$("#structureFileRow"+rowNo).remove();
     }

    
    
  //geting works list from database    
    function getWorksList(projectId) {
    	$(".page-loader").show();
        $("#work_id_fk option:not(:first)").remove();
        $("#contract_id_fk option:not(:first)").remove();
        //$("#responsible_people_id_fk option:not(:first)").remove();

        if ($.trim(projectId) != "") {
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksListForStructureForm",
                data: myParams, cache: false,async : false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
                            var work_id_fk = "${structuresListDetails.work_id_fk }";
                            if ($.trim(work_id_fk) != '' && val.work_id_fk == $.trim(work_id_fk)) {
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' +  $.trim(workName) + '</option>');
                            } else {
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' +  $.trim(workName) + '</option>');
                            }
                        });
                    }
                     $('.searchable:not(.units)').select2();
                    $(".page-loader").hide();
                }
            });
            var work_id_fk = $("#work_id_fk").val();
            if ($.trim(work_id_fk) != '') {
            	getContractsList(work_id_fk);
            }
        }else{
        	$(".page-loader").hide();
        }
        
        hitCount = 0;
    }

    //geting contracts list    
    function getContractsList(work_id_fk) {
    	$(".page-loader").show();
        $("#contract_id_fk0 option:not(:first)").remove();
        //$("#responsible_people_id_fk option:not(:first)").remove();
        if($.trim(work_id_fk) != ''){
        	var myParams = { work_id_fk: work_id_fk };
	        $.ajax({
	        	url: "<%=request.getContextPath()%>/ajax/getContractsListForStructureFrom",
	            data: myParams, cache: false,async:false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                    	var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                       /*  var contract_id_fk = "${structuresListDetails.contract_id_fk }";
	                        if ($.trim(contract_id_fk) != '' && val.contract_id_fk == $.trim(contract_id_fk)) {
	                        	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '" selected>' +  $.trim(contract_name) + '</option>');
	                        } else {
	                        	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' +  $.trim(contract_name) + '</option>');
	                        } */
	                        var selectedFlag = ''
	                        <c:forEach var="tempobj" items="${structuresListDetails.executivesList}">	                        	
	                        	if('${tempobj.contract_id_fk}' == val.contract_id_fk){
	                        		selectedFlag = 'selected';
	                        	}
		                   	</c:forEach>
		                   	$("#contract_id_fk0").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '" '+selectedFlag+'>' +  $.trim(contract_name) + '</option>');
	                       
	                    });
	                }
	                 $('.searchable:not(.units)').select2();
	                $(".page-loader").hide();
	                //existedContarcts();
	            }
	        });
	        
        }else{
        	$(".page-loader").hide();
        }
    }
    function resetProject(){
    	$(".page-loader").show();        	
    	var projectId = '';
    	var workId = $("#work_id_fk").val();
    	if($.trim(workId) != ''){  
        	projectId = workId.substring(0, 3);    
   			$("#project_id_fk").val(projectId);
   			$("#project_id_fk").select2();
   		    document.getElementById("project_id_fkError").innerHTML="";
   		}
   		$(".page-loader").hide();
    }
    function existedContarcts(){
    	$('form select[name="contracts_id_fk"]').each(function(){
    			var ids = $(this).attr('id'); 
    			ids = ids.split('fk');
    			getContractsByRowList(ids[1]);
    		});
    }
    function getContractsByRowList(row) {
    	var workid="";
        if($.trim("${structuresListDetails.structure}")!= '')
        {
        	workid="${structuresListDetails.work_id_fk}";
        }
        else
       	{
        	workid=$("#work_id_fk").val();
       	}
        
    	$(".page-loader").show();
    	var contractVal = $("#contract_id_fk"+row).val();
        $("#contract_id_fk"+row+" option:not(:first)").remove();
        if(workid!='')
        {
        	var myParams = { work_id_fk: workid };
	        $.ajax({
	        	url: "<%=request.getContextPath()%>/ajax/getContractsListForStructureFrom",
	            data: myParams, cache: false,async:false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                    	var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                        var selectedFlag = (contractVal == $.trim(val.contract_id_fk))?'selected':'';
		                   	$("#contract_id_fk"+row).append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '"'+selectedFlag+'>' +  $.trim(contract_name) + '</option>');
	                       
	                    });
	                }
	                 $('.searchable:not(.units)').select2();
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
    
    function addStructure(){
    	
		if(validator.form()){ // validation perform
			//var contract_name = $( "#contract_id_fk option:selected" ).text();
		
			var completion_cost = $("#completion_cost").val();
			var estimated_cost = $("#estimated_cost").val();
			if(completion_cost == ""){
  				$('#completion_cost_units').val("");
  			}if(estimated_cost == ""){
  				$('#estimated_cost_units').val("");
  			}
			var contract_name = $("#contract_id_fk option:selected").map(function() {
			    return $(this).text();
			}).get(); 
			$("#contract_name").val(contract_name);
  			if(validator.form()){
  				$('form input[name=structure_detail_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  	  			$('form input[name=structure_detail_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  	  			$(".page-loader").show();
  	  			document.getElementById("structuresForm").submit();	
 			}
	 	}
	}
	
    function updateStructure(){
    	
    	
    	$('form select[name=contracts_id_fk]').each(function(){
    		$("#"+this.id).attr("disabled",false);
    	});	
    	$('[name=structure_details]').each(function(){
    		var idNo = (this.id).replace('structure_detail_names','');
    		if(idNo != '' && idNo == "undefined" && idNo != null){
    			var detail = $("#"+this.id).val();
        		if(detail == ''){
        			('#structure_detail_values'+idNo).val('');
        		}
    		}
    	});	
    	$('form select[name=responsible_people_id_fks]').each(function(){
    		$("#"+this.id).attr("disabled",false);
    	});	   	
  		if(validator.form()){ 
  			
  			var completion_cost = $("#completion_cost").val();
			var estimated_cost = $("#estimated_cost").val();
			if(completion_cost == ""){
  				$('#completion_cost_units').val("");
  			}if(estimated_cost == ""){
  				$('#estimated_cost_units').val("");
  			}
  			var contract_name = $("#contract_id_fk option:selected").map(function() {
			    return $(this).text();
			}).get();
			$("#contract_name").val(contract_name);
			
  			$("#project_id_fk").attr('disabled', false);
  			$("#work_id_fk").attr('disabled', false);
  			$("#contract_id_fk").attr('disabled', false);
  			$("#responsible_people_id_fk").attr('disabled', false);
  			$("#target_date").attr('disabled', false);
  			$("#construction_start_date").attr('disabled', false);
  			$("#revised_completion").attr('disabled', false);
  			$("#commissioning_date").attr('disabled', false);
  			$("#actual_completion_date").attr('disabled', false);
  			
  				    		
  			$('form input[name=structure_detail_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			$('form input[name=structure_detail_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			
  			if($("#work_status_fk").val() == 'Commissioned' && $("#commissioning_date").val()=="")
 				{
 				   $("#commissioning_dateError").html("Commissioning Date is mandatory");
 				   return false;
 				}
  			else
  				{
  					$("#commissioning_dateError").html("");
  				}
  			$(".page-loader").show();
  			document.getElementById("structuresForm").submit();			
	 	}
	}
	
	//Wait for the DOM to be ready
	
	// to validate apartment form inputs thruogh jquery.
	   
	var validator = $('#structuresForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "structure": {
				 		required: true
			 	  	  },"structure_type_fk": {
				 		required: true
			 	  	  },"project_id_fk": {
   				 		required: true
   				 	  },"work_id_fk": {
				 		required: true
				 	  },"contract_id_fk": {
				 		required: true
				 	  },"structure_name": {
				 		required: true
				 	  },"work_status_fk": {
				 		required: true
				 	  },"target_date": {
				 		required: false,
   				 		//dateBefore1:"#construction_start_date"
				 	  },"estimated_cost": {
				 		required: false
				 	  },"last_sanctioned_cost": {
			 		    required: false,
			 	   	  },"construction_start_date": {
			 		    required: false,
			 		    currentDate1:"#construction_start_date",
			 		    //dateBefore4 :"#target_date"
			 	   	  },"commissioning_date": {
				 		required: false,
				 		currentDate2:"#commissioning_date",
   				 		dateBefore2:"#construction_start_date",
				 		/* dateBefore5:"#actual_completion_date" */
				 	  },"actual_completion_date": {
			 		    required: false,
			 		    currentDate3:"#commissioning_date",
   				 		dateBefore3:"#actual_completion_date"
			 	   	  },"completion_cost": {
				 		required: false
				 	  },"latitude": {
				 		required: false
				 	  },"longitude": {
				 		required: false
				 	  },"remarks":{
				 		 required: false
				 	  },"completion_cost_units":{
        		 		 required: function(element){
        		             return $("#completion_cost").val()!="";
        		         }
        		 	  },"estimated_cost_units":{
        		 		 required: function(element){
        		             return $("#estimated_cost").val()!="";
        		         }
        		 	  }
				 				
			 	},
			   messages: {
				     "structure": {
  			 			required: 'Required'
  			 	  	 },"structure_type_fk": {
  			 			required: 'Required'
  			 	  	 },"project_id_fk": {
   			 			required: 'Required'
   			 	  	 },"work_id_fk": {
			 			required: 'Required'
			 	  	 },"contract_id_fk": {
			 			required: 'Required'
			 	  	 },"structure_name": {
			 			required: 'Required'
			 	  	 },"work_status_fk": {
			 			required: 'Required'
			 	  	 },"target_date": {
			 			required: 'Required'
			 	  	 },"estimated_cost": {
			 			required: 'Required'
			 	  	 },"last_sanctioned_cost": {
			 	  		required: 'Required'
			 	   	 },"construction_start_date": {
			 			required: 'Required'
			 	  	 },"commissioning_date": {
			 			required: 'Required'
			 	  	 },"actual_completion_date": {
			 			required: 'Required'
			 	  	 },"completion_cost": {
			 			required: 'Required'
			 	  	 },"latitude": {
 				 		required: 'Required'
   				 	  },"longitude": {
   				 		required: 'Required'
   				 	  },"remarks":{
			 	  		required: 'Required'
				 	  },"completion_cost_units": {
   				 		required: 'Required'
   				 	  },"estimated_cost_units":{
			 	  		required: 'Required'
				 	  }
			 				      
		    },
			  errorPlacement:
			 	function(error, element){
				    if (element.attr("id") == "structure" ){
			 		     document.getElementById("structureError").innerHTML="";
			 			 error.appendTo('#structureError');
			 	    }else if (element.attr("id") == "structure_type_fk" ){
 			 		     document.getElementById("structure_type_fkError").innerHTML="";
 			 			 error.appendTo('#structure_type_fkError');
 			 	    }else if (element.attr("id") == "project_id_fk" ){
 			 		     document.getElementById("project_id_fkError").innerHTML="";
 			 			 error.appendTo('#project_id_fkError');
 			 	    }else if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "structure_name" ){
			 		     document.getElementById("structure_nameError").innerHTML="";
			 			 error.appendTo('#structure_nameError');
			 	    }else if (element.attr("id") == "work_status_fk" ){
			 		     document.getElementById("work_status_fkError").innerHTML="";
			 			 error.appendTo('#work_status_fkError');
			 	    }else if (element.attr("id") == "target_date" ){
			 		     document.getElementById("target_dateError").innerHTML="";
			 			 error.appendTo('#target_dateError');
			 	    }else if (element.attr("id") == "estimated_cost" ){
			 		     document.getElementById("estimated_costError").innerHTML="";
			 			 error.appendTo('#estimated_costError');
			 	    }else if (element.attr("id") == "construction_start_date" ){
			 		     document.getElementById("construction_start_dateError").innerHTML="";
			 			 error.appendTo('#construction_start_dateError');
			 	    }else if (element.attr("id") == "estimated_cost" ){
			 		     document.getElementById("estimated_costError").innerHTML="";
			 			 error.appendTo('#estimated_costError');
			 	    }else if (element.attr("id") == "last_sanctioned_cost" ){
			 		     document.getElementById("last_sanctioned_costError").innerHTML="";
			 			 error.appendTo('#last_sanctioned_costError');
			 	    }else if (element.attr("id") == "commissioning_date" ){
			 		     document.getElementById("commissioning_dateError").innerHTML="";
			 			 error.appendTo('#commissioning_dateError');
			 	    }else if (element.attr("name") == "actual_completion_date" ){
			 		     document.getElementById("actual_completion_dateError").innerHTML="";
			 			 error.appendTo('#actual_completion_dateError');
			 	    }else if (element.attr("id") == "completion_cost" ){
			 		     document.getElementById("completion_costError").innerHTML="";
			 			 error.appendTo('#completion_costError');
			 	    }else if (element.attr("id") == "latitude" ){
			 	    	     document.getElementById("latitudeError").innerHTML="";
			 			     error.appendTo('#latitudeError');
  			 	    }else if (element.attr("id") == "longitude" ){
  			 		     document.getElementById("longitudeError").innerHTML="";
  			 			 error.appendTo('#longitudeError');
  			 	    }else if (element.attr("id") == "completion_cost_units" ){
		 	    	     document.getElementById("completion_cost_unitsError").innerHTML="";
		 			     error.appendTo('#completion_cost_unitsError');
			 	    }else if (element.attr("id") == "estimated_cost_units" ){
			 		     document.getElementById("estimated_cost_unitsError").innerHTML="";
			 			 error.appendTo('#estimated_cost_unitsError');
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
	    
	    $.validator.addMethod("dateBefore1", function(value, element) {
	    	var d = new Date();
	    	var month = d.getUTCMonth() + 1;
	    	var dateVal = ''+d.getUTCDate()+'-'+month+'-'+d.getUTCFullYear()+'';
	    	var newDateParts = dateVal.split("-");
	    	var newDate = new Date(+newDateParts[2], newDateParts[1] - 1, +newDateParts[0]);
            var fromDateString = $('#construction_start_date').val();
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
            
        }, "Target Date must be after Construction Start Date");
	    
	    $.validator.addMethod("dateBefore2", function(value, element) {
	    	var d = new Date();
	    	var month = d.getUTCMonth() + 1;
	    	var dateVal = ''+d.getUTCDate()+'-'+month+'-'+d.getUTCFullYear()+'';
	    	var newDateParts = dateVal.split("-");
	    	var newDate = new Date(+newDateParts[2], newDateParts[1] - 1, +newDateParts[0]);
            var fromDateString = $('#construction_start_date').val(); //
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
            }else if(newDate < fromDate){
            	return false;
            }else{
            	return true;
            }
            
        }, "Commissioning Date must be after Construction Start Date");
	    
	    $.validator.addMethod("currentDate1", function(value, element) {
	    	var d = new Date();
	    	var month = d.getUTCMonth() + 1;
	    	var dateVal = ''+d.getUTCDate()+'-'+month+'-'+d.getUTCFullYear()+'';
	    	var newDateParts = dateVal.split("-");
	    	var newDate = new Date(+newDateParts[2], newDateParts[1] - 1, +newDateParts[0]);
            var fromDateString = $('#construction_start_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

             if(newDate < fromDate){
            	return false;
            }else{
            	return true;
            }
            
        }, "Construction Start Date should not greater than today");
	    
	    $.validator.addMethod("dateBefore4", function(value, element) {
            var fromDateString = $('#target_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) >= Date.parse(toDate);
            	//return Date.parse(fromDate) < Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Construction Start Date must be before Target Date");
	    
	    
	    $.validator.addMethod("currentDate2", function(value, element) {
	    	var d = new Date();
	    	var month = d.getUTCMonth() + 1;
	    	var dateVal = ''+d.getUTCDate()+'-'+month+'-'+d.getUTCFullYear()+'';
	    	var newDateParts = dateVal.split("-");
	    	var newDate = new Date(+newDateParts[2], newDateParts[1] - 1, +newDateParts[0]);
            var fromDateString = $('#commissioning_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

             if(newDate < fromDate){
            	return false;
            }else{
            	return true;
            }
            
        }, "Commissioning Date should not greater than today");
	    
	    $.validator.addMethod("currentDate3", function(value, element) {
	    	var d = new Date();
	    	var month = d.getUTCMonth() + 1;
	    	var dateVal = ''+d.getUTCDate()+'-'+month+'-'+d.getUTCFullYear()+'';
	    	var newDateParts = dateVal.split("-");
	    	var newDate = new Date(+newDateParts[2], newDateParts[1] - 1, +newDateParts[0]);
            var fromDateString = $('#actual_completion_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

             if(newDate < fromDate){
            	return false;
            }else{
            	return true;
            }
            
        }, "Actual Completion Date should not greater than today");
	    
	    $.validator.addMethod("dateBefore5", function(value, element) {
            var fromDateString = $('#actual_completion_date').val(); //
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) >= Date.parse(toDate);
            	//return Date.parse(fromDate) < Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Commissioning Date must be before Actual Completion Date");
	    
	    $.validator.addMethod("dateBefore3", function(value, element) {
            var fromDateString = $('#commissioning_date').val(); //
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
            
        }, "Actual Completion Date must be after Commissioning Date");
        
    /*     
        $('select').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	}); */
        
        $('input').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
        
        
        
         function addStructureImages(){      		
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="fobImagesRow'+rNo+'">'
    		   		  +'<td><input type="hidden" name= "fob_id_fks" id="fob_id_fks' + rNo + '" />'
    		   		+'<div class="row">'
    		   		+'<div class="col m2 hide-on-small-only"></div>'
    		   		+'<div class="col m8 s12">'
    		   		+'<div class="file-field input-field">'
    		   		+'<div class="btn bg-m">'
    		   		+'<span>Attach Image</span>'
    		   		+'<input type="file" id="structureFile'+rNo+'" name="structureFile" accept="image/*" onchange="readURL(this,'+rNo+');">'
    		   		+'</div>'
    		   		+' <div class="file-path-wrapper">'
    		   		+'<input class="file-path validate" type="text" name="attachment" >'
    		   		+'<img style="height: 20%;width: 20%; id="fobImagePreview'+rNo+'"   />'
    		   		+'</div>'
    		   		+'</div>'
    		   		+'</div>'
    		   		+'<div class="col m2 hide-on-small-only"></div></div>'
		            +'</td>'
    			   	+'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeStructureImages('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
    			 
			 $('#fobImagesTable').append(html);
			 $("#rowNo").val(rNo);
        }
        
		 function removeStructureImages(rowNo){
        	$("#fobImagesRow"+rowNo).remove();
         } 
		 
		 function readURL(input,rowNo) {
	            if (input.files && input.files[0]) {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                    $('#fobImagePreview'+rowNo).attr('src', e.target.result)
	                };
	                reader.readAsDataURL(input.files[0]);
	                $('#fobImagePreview'+rowNo).show();
	            }
	     }
		 
 	$('#structure_type_fk').on('change',function(){
			if($('#structure_type_fk').val()=='FOB'){
				$('#structureFobDiv').show();
				$('#structureNonFobDiv').hide();
				$('#hideForFOB').hide();
				$('#showForFOB').show();
				$("#hideForFOB").children().prop('disabled', true);
				$('#structure').attr("disabled",true);
				$('#fob_id').attr("disabled",false);
				$("#structureNonFobDetailsTableBody").find("input,select").attr("disabled", true);
				$("#structureDetailsTableBody").find("input,select").attr("disabled", false);
			}else{
				$('#structureNonFobDiv').show();
				$('#structureFobDiv').hide();
				$('#showForFOB').hide();
				$('#hideForFOB').show();
				$("#hideForFOB").children().prop('disabled', false);
				$('#structure').attr("disabled",false);
				$('#fob_id').attr("disabled",true);
				$("#structureNonFobDetailsTableBody").find("input,select").attr("disabled", false);
				$("#structureDetailsTableBody").find("input,select").attr("disabled", true);
			}
		}); 
	
    </script>
	
</body>
</html>