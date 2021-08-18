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
		<c:if test="${action eq 'edit'}">Update FOB - Update Forms - PMIS</c:if>
		<c:if test="${action eq 'add'}"> Add FOB - Update Forms - PMIS</c:if>
	</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">	
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/fob.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
	 <style>
        .fixed-width {
            width: 100%;
            margin-left :auto !important;
            margin-right :auto !important;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }       
		/*table with fixed header & height start */
		/* .max-h{
			max-height:400px;
			height:auto;
			overflow:auto;	
			padding:0 !important;		
		}	
		.max-h tr{
			position:relative;
		}
		.max-h thead th{
			position:sticky;
			top:0;
			z-index:1;
			background-color:#003049;
		} */
		td .btn.red{
			z-index:0;
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
            top: 32px;
            right:20px;
        }
        
        @media only screen and (max-width: 768px){
        	.mobile_responsible_table>tbody >tr:not(.datepicker-row)> td> div.btn{
				float:none;
				position:relative;
			}
			#gallery_table .datepicker~button{
				top:0;
			}
			.h-auto{
				height:auto !important;
			}
			
			td.cell-disp-inb .file-path-wrapper {
			    visibility: visible;
			    width: 200%;
			    display: block !important;
			}
        }
       /*  datepicker styling to its defaults */
    .mobile_responsible_table .datepicker-table .datepicker-row td {
	  padding:0
	}

    .mobile_responsible_table>tbody .datepicker-table tr{
        background-color: transparent;
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
                                	<c:if test="${action eq 'edit'}">Update FOB</c:if>
									<c:if test="${action eq 'add'}"> Add FOB</c:if>
								</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                          <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-fob" id="fobForm" name="fobForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-fob" id="fobForm" name="fobForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"
                                        onchange="getWorksList(this.value);" <c:if test="${not empty fob.project_id_fk}">disabled</c:if>>
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                            <option value="${obj.project_id }" <c:if test="${obj.project_id eq fob.project_id_fk}">selected</c:if>><%-- ${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> --%> ${obj.project_name }</option>
                                        </c:forEach>
                                    </select>                                   
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                <p class="searchable_label"> Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);" <c:if test="${not empty fob.work_id_fk}">disabled</c:if>>
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}"><%-- ${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> --%> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div style="height:20px;"></div>
                            <div class="row">
                                <div class="col s6 m2 input-field offset-m2">
                                    <input id="fob_name" name="fob_name" type="text" class="validate" <c:if test="${action eq 'edit'}">readonly</c:if> value="${fob.fob_name }" >
                                    <label for="fob_name">FOB Name <span class="required">*</span></label>
                                    <span id="fob_nameError" class="error-msg" ></span>
                                </div>
                           		
                           		<div style="display:none">
									<c:forEach var="obj" items="${fobIdCheck}" varStatus="index">
										<input type="hidden" id="fob_id${index.count}" value="${obj.fob_id }"  class="findLengths"/>
									</c:forEach>
								</div>
                           
                                 <%-- <c:if test="${empty fob.fob_id }"> --%>
	                                <div class="col s6 m2 input-field">
	                                    <input id="fob_id" name="fob_id" type="text" class="validate" value="${fob.fob_id }" onkeyup="doValidate(this.value)" <c:if test="${not empty fob.fob_id}">readonly</c:if>>
	                                    <label for="fob_id">FOB ID <span class="required">*</span></label>
	                                    <span id="fob_idError" class="error-msg" ></span>
	                                </div>
                                <%-- </c:if> --%>
                                <%-- <c:if test="${not empty fob.fob_id }">
	                                <div class="col s12 m2 input-field">
	                                    <label > FOB ID <span class="required">*</span>: <input id="fob_id" name="fob_id" type="text" value="${fob.fob_id }" readonly style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
	                                    <span id="fob_idError" class="error-msg" ></span>
	                                    <input id="fob_id" name="fob_id" type="text" class="validate" value="${fob.fob_id }" readonly>
	                                    <label for="fob_id">FOB ID <span class="required">*</span></label>
	                                    <span id="fob_idError" class="error-msg" ></span>
	                                </div>
                                </c:if> --%>
                                <div class="col s6 m4 input-field ">
                                    <p class="searchable_label">Work Status <span class="required">*</span></p>
                                    <select id="work_status_fk" name="work_status_fk"  class="searchable validate-dropdown" onchange="openDates(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${generalStatusList }">
                                        	<c:if test="${obj ne 'Closed' and obj ne 'Terminated' and obj ne 'Completed'}">
                                            	<option value="${obj }" <c:if test="${(empty fob.work_status_fk and obj eq 'Not Started') or (obj eq fob.work_status_fk)}">selected</c:if> >${obj}</option>
                                        	</c:if>
                                        </c:forEach>
                                    </select>
                                    <span id="work_status_fkError" class="error-msg" ></span>
                                </div>
                                <input type="hidden" id="existing_work_status_fk" name="existing_work_status_fk" value="${fob.work_status_fk }"/> 
                           </div>
                            <%-- <div class="row ">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field offset-m2">
                                <p class="searchable_label"> Contract <span class="required">*</span></p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" 
                                     	onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" value= "${ obj.contract_id}">${obj.contract_id}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                 
                                <div class="col s6 m4 input-field offset-m2">
                                   <p class="searchable_label">Work Status <span class="required">*</span></p>
                                    <select id="work_status_fk" name="work_status_fk"  class="searchable validate-dropdown" onchange="openDates(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${generalStatusList }">
                                        	<c:if test="${obj ne 'Closed' and obj ne 'Terminated' and obj ne 'Completed'}">
                                            	<option value="${obj }" <c:if test="${(empty fob.work_status_fk and obj eq 'Not Started') or (obj eq fob.work_status_fk)}">selected</c:if> >${obj}</option>
                                        	</c:if>
                                        </c:forEach>
                                    </select>
                                    <span id="work_status_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>
                            <br>
                            <div class="row">
                                <div class="col s12 m4 input-field offset-m2">
                                <p class="searchable_label">Contract</p>
									<c:choose>
								        <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">                                 
                                 <select  class="searchable validate-dropdown" name="contract_id_fk" id="contract_id_fk" 
                                 			multiple="multiple" onchange="resetWorksAndProjectsDropdowns();"> 
                                  		 <option value="">Select</option>
                                          <c:forEach var="obj" items="${contractsList}">
									 		<option workId="${obj.work_id_fk }" value="${obj.contract_id }" 
										 		<c:forEach var="tempobj" items="${fob.contractsList}">
										 			<c:if test="${tempobj.contract_id_fk eq obj.contract_id}">selected</c:if>
	                                          	</c:forEach>
									 		>${obj.contract_short_name }</option>
                                          </c:forEach>
                                  </select>
                                  </c:when>
                                  <c:otherwise>
                                  <select  class="searchable validate-dropdown" name="contract_id_fk" id="contract_id_fk" 
                                 			multiple="multiple" onchange="resetWorksAndProjectsDropdowns();" >
                                  		 <option value="" disabled>Select</option>
                                          <c:forEach var="obj" items="${contractsList}">
									 		<option workId="${obj.work_id_fk }" value="${obj.contract_id }" 
										 		<c:forEach var="tempobj" items="${fob.contractsList}">
										 			<c:if test="${tempobj.contract_id_fk eq obj.contract_id}">selected</c:if>
	                                          	</c:forEach>
									 		>${obj.contract_short_name }</option>
                                          </c:forEach>
                                  </select>                                 
                                  </c:otherwise>
                                  </c:choose>
                                  <span id="contract_id_fkError" class="error-msg"></span>
                                </div>
                                
                                <input type="hidden" id="contract_name" name="contract_name" />
                                
                                <div class="col s12 m4 input-field ">
                                <p class="searchable_label">Responsible Executives</p>
                                  <select  class="searchable validate-dropdown" name="responsible_people_id_fk" id="responsible_people_id_fk" 
                                  multiple="multiple" <c:if test="${fn:length(fob.responsiblePeopleList) gt 0}">disabled</c:if>>
                                   <option value="" disabled="disabled">Select</option>
                                   <c:forEach var="obj" items="${responsiblePeopleList}">
           					  			 <option value="${obj.user_id }"            					  			 
           					  			 		<c:forEach var="tempobj" items="${fob.responsiblePeopleList}">
										 			<c:if test="${tempobj.responsible_people_id_fk eq obj.user_id}">selected</c:if>
	                                          	</c:forEach>           					  			 
           					  			 > ${obj.designation} - ${obj.user_name}</option>
                                   </c:forEach>
                                  </select>
                                     <span id="responsible_people_id_fkError" class="error-msg"></span>
                                </div>
                            </div> 
                            <br>
                           <div class="row">
	                            <div class="col s12 m4 input-field offset-m2">
                                    <input id="target_date" name="target_date" type="text" class="validate datepicker" value="${fob.target_date }" <c:if test="${not empty fob.target_date}">disabled</c:if>>
                                    <label for="target_date">Original Target Date </label>
                                    <button type="button" id="target_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="target_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s8 m3 input-field">
                                	<i class="material-icons prefix cost">₹</i>   
                                    <input id="estimated_cost" name="estimated_cost" type="number" class="validate" value="${fob.estimated_cost }" min="0.01" step="0.01" <c:if test="${not empty fob.estimated_cost}">readonly</c:if>>
                                    <label for="estimated_cost">Estimated Cost</label>
                                    <span id="estimated_costError" class="error-msg" ></span> 
                                </div>
                                <div class="col s4 m1 input-field">
                                	<p class="searchable_label">Units</p>
                                	<select class="units validate-dropdown" id="estimated_cost_units" name="estimated_cost_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${fob.estimated_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                	<span id="estimated_cost_unitsError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m8 input-field offset-m2">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000" maxlength="1000">${fob.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            <c:if test="${action eq 'edit'}">	
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${fob.latitude }" <c:if test="${not empty fob.latitude}">readonly</c:if>>
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field ">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${fob.longitude }" <c:if test="${not empty fob.longitude}">readonly</c:if>>
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                            </div>
                            </c:if>
                            
                            <div class="row">
                             	<div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field " id="construction_start_dateDiv" style="display: none;">
                                    <input id="construction_start_date" name="construction_start_date" type="text" class="validate datepicker" value="${fob.construction_start_date }" <c:if test="${not empty fob.construction_start_date}">disabled</c:if>>
                                    <label for="construction_start_date" class="fs-sm-8rem">Construction Start Date </label>
                                    <button type="button" id="construction_start_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="construction_start_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field " id="revised_completionDiv" style="display: none;">
                                    <input id="revised_completion" name="revised_completion" type="text" class="validate datepicker" value="${fob.revised_completion }" <c:if test="${not empty fob.revised_completion}">disabled</c:if>>
                                    <label for="revised_completion" class="fs-sm-8rem">Target completion Date </label>
                                    <button type="button" id="revised_completion_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="revised_completionError" class="error-msg" ></span>
                                </div> 
                                <div class="col m2 hide-on-small-only"></div>                               
                            </div>
                            
							<c:if test="${action eq 'edit'}">
	                            <div class="row">
	                                <h5 class="center-align">FOB Details</h5>
	                                <div class="col s12 m8 offset-m2">
	                                    <table id="fobDetailsTable" class="mdl-data-table">
	                                        <thead>
	                                            <tr>
	                                                <th>FOB Detail </th>
	                                                <th>Value </th>
	                                                <!-- <th>Action</th> -->
	                                            </tr>
	                                        </thead>
	                                        <tbody id="fobDetailsTableBody">
	                                       		<c:forEach var="dObj" items="${fob.fobDetails }" varStatus="index">                                        	
		                                           <tr id="fobDetailsRow${index.count }">                                            	
		                                               <td>
		                                                    <input id="fob_detail_names${index.count }" name="fob_detail_names" type="text" readonly class="validate" value="${dObj.detail_name }"
		                                                        placeholder="Detail name">
		                                                </td>
		                                                <td>
			                                                <c:if test="${dObj.detail_name eq 'Type'}">
			                                                     <select id="fob_detail_values${index.count }" name="fob_detail_values">
			                                                    	<option value="">Select</option>
			                                                    	<c:forEach var="obj" items="${fobDetailsTypes }"> 
			                                                    		<option value="${obj.fob_details_type }" <c:if test="${dObj.value eq obj.fob_details_type }">selected</c:if>>${obj.fob_details_type }</option>
			                                                    	</c:forEach>
			                                                     </select>
			                                                </c:if>
			                                                <c:if test="${dObj.detail_name eq 'Location of FOB'}">
			                                                     <select id="fob_detail_values${index.count }" name="fob_detail_values">
			                                                    	<option value="">Select</option>
			                                                    	<c:forEach var="obj" items="${fobDetailsLocations }"> 
			                                                    		<option value="${obj.fob_details_location }" <c:if test="${dObj.value eq obj.fob_details_location }">selected</c:if>>${obj.fob_details_location }</option>
			                                                    	</c:forEach>
			                                                     </select>
			                                                </c:if>
			                                                <c:if test="${(dObj.detail_name ne 'Type') and (dObj.detail_name ne 'Location of FOB') }">
			                                                    <input id="fob_detail_values${index.count }" name="fob_detail_values" type="text" class="validate" value="${dObj.value }"
			                                                        placeholder="Value">
			                                                </c:if>		                                                 
		                                                </td>
		                                            </tr>
	                                            </c:forEach> 
	                                     </tbody>
	                                   </table>
	                                </div>
	                            </div>
                            </c:if>
                            <br>
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2" id="commissioning_dateDiv" style="display: none;">
                                    <input id="commissioning_date" name="commissioning_date" type="text" class="validate datepicker" value="${fob.commissioning_date }" <c:if test="${not empty fob.commissioning_date}">disabled</c:if>>
                                    <label for="commissioning_date">Commissioning Date </label>
                                    <button type="button" id="commissioning_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="commissioning_dateError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row" id="actual_completion_dateDiv" style="display: none;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 input-field offset-m2"  >
                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker" value="${fob.actual_completion_date }" <c:if test="${not empty fob.actual_completion_date}">disabled</c:if>>
                                    <label for="actual_completion_date">Actual Completion Date </label>
                                    <button type="button" id="actual_completion_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="actual_completion_dateError" class="error-msg" ></span>
                                </div>
                               <%--  <c:if test="${action eq 'edit'}"> --%>
                                <div class="col s9 m3 input-field">
                                	<i class="material-icons prefix cost">₹</i>   
                                    <input id="completion_cost" name="completion_cost" type="number" class="validate" min="0.01" step="0.01" value="${fob.completion_cost }" <c:if test="${not empty fob.completion_cost}">readonly</c:if>>
                                    <label for="completion_cost">Actual Completion Cost</label>
                                    <span id="completion_costError" class="error-msg" ></span>
                                </div>
                                <div class="col s3 m1 input-field">
                                	<p class="searchable_label">Units</p>
                                	<select class="units validate-dropdown" id="completion_cost_units" name="completion_cost_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${fob.completion_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                	<span id="completion_cost_unitsError" class="error-msg" ></span>
                                </div>
                               <%--  </c:if> --%>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row"> 
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m12 s12">
	                            <%-- <c:if test="${action eq 'add'}">
		                            <div id="selectedFilesInput">
	                                   	<div class="file-field input-field" id="fobFilesDiv1" >
	                                        <div class="btn bg-m t-c">
	                                            <span>Attach Photos</span>
	                                            <input type="file" id="fobFiles1" name="fobFiles" onchange="selectFile('1')">
	                                        </div>
	                                        <div class="file-path-wrapper">
	                                            <input class="file-path validate" type="text">
	                                        </div>                                       
	                                    </div>
									</div>
	                                   
	                                   <div id="selectedFiles">
	                                   	
									</div>
								</c:if>		 --%>
								
                             <div class="row">
								<div class="col m8 s12 offset-m2">
									<div class="row fixed-width"
										style="margin-bottom: 20px; margin-top: 20px">
										<div class="table-inside">
											<table class="mdl-data-table update-table mobile_responsible_table" id="gallery_table">
												<thead>
													<tr>
													<!-- 	<th style="width: 52%;text-align: left;">Attach Photo</th>
														<th style="width: 30%;text-align: left;">Photo Date</th>
														<th></th>
														<th style="width: 8%;text-align: left;">Action</th> -->
														
														<th >Attach Photo</th>
														<th>Photo Date</th>
														<th  style="display:none;"></th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="fobFilesBody">
													<c:choose>
														<c:when	test="${not empty fob.fobImages && fn:length(fob.fobImages) gt 0 }">
															<c:forEach var="fObj" items="${fob.fobImages }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<%-- <td style='display:none;'>
																		<div class="input-field">
																			<select  name="fob_file_types"  id="fob_file_types${index.count }"  class="validate-dropdown searchable">
							                                   					 <option value="" >Select</option>
							                                         			  <c:forEach var="obj" items="${fobFileTypesList}">
							                    					  				 <option value="${obj.fob_file_type }" <c:if test="${fObj.fob_file_type_fk eq obj.fob_file_type}">selected</c:if>>${obj.fob_file_type}</option>
							                                          			  </c:forEach>
							                               					  </select>
																		</div>
																	</td> --%>
																	<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach Photo</span>
								                                            <input type="file" id="fobFiles${index.count }" name="fobFiles" accept="image/*">
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="fobFileNames${index.count }" name="fobFileNames" value="${fObj.attachment }">
								                                        </div>                             
			                                                      	</td>
			                                                      	<td data-head="Photo Date" class="input-field">
		                                                      			<span style='display:inline-block;'><input type="text" id="created_dates0" name="created_dates" class="validate datepicker" style="width:150px;" /><button type="button" id="created_dates_0_icon"><i class="fa fa-calendar"></i></button></span>
			                                                      	</td>
			                                                      	<td  style="display:none;">
			                                                      		<input type="hidden" id="fob_file_ids${index.count }" name="fob_file_ids" value="${fObj.fob_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.FOB_GALLERY%>${fObj.fob_id_fk }/${fObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
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
																<%-- <td style='display:none;'>
																	<div class="input-field">
																		<select  name="fob_file_types"  id="fob_file_types0"  class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${fobFileTypesList}">
						                    					  				 <option value="${obj.fob_file_type }">${obj.fob_file_type}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</div>
																</td> --%>
																<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">
							                                        <div class="btn bg-m t-c">
							                                            <span>Attach Photo</span>
							                                            <input type="file" id="fobFiles0" name="fobFiles" accept="image/*">
							                                        </div>
							                                        <div class="file-path-wrapper">
							                                            <input class="file-path validate" type="text" id="fobFileNames0" name="fobFileNames">
							                                        </div>                                       
		                                                      	</td>
		                                                      	<td data-head="Photo Date" class="input-field">
		                                                      		<span style='display:inline-block;'><input type="text" id="created_dates0" name="created_dates" class="validate datepicker" style="width:150px;" /><button type="button" id="created_dates_0_icon"><i class="fa fa-calendar"></i></button></span>

		                                                      	</td>
		                                                      	<td  style="display:none;"><input type="hidden" id="fob_file_ids0" name="fob_file_ids"/></td>
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
													<tr>
														<td colspan="6" style="text-align: right;"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c "
															onclick="addFOBFileRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											
											<c:choose>
												<c:when test="${not empty (fob.fobImages) && fn:length(fob.fobImages) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo" value="${fn:length(fob.fobImages) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>                    
								<%-- <c:if test="${action eq 'edit'}">
										<c:set var="existingFOBFilesLength" value="${fn:length(fob.fobImages )}"></c:set>
										<c:if test="${fn:length(fob.fobImages ) gt 0}">
											<c:set var="existingFOBFilesLength" value="${fn:length(fob.fobImages )+1}"></c:set>
										</c:if>
										<div id="selectedFilesInput">
	                                    	<div class="file-field input-field" id="fobFilesDiv${existingFOBFilesLength }" >
		                                        <div class="btn bg-m t-c">
		                                            <span>Attach Photos</span>
		                                            <input type="file" id="fobFiles${existingFOBFilesLength }" name="fobFiles"  onchange="selectFile('${existingFOBFilesLength }')">
		                                        </div>
		                                        <div class="file-path-wrapper">
		                                            <input class="file-path validate" type="text">
		                                        </div>                                       
		                                    </div>
										</div>
	                                    
	                                    <div id="selectedFiles">
	                                    	<c:forEach var="obj" items="${fob.fobImages }" varStatus="index">
												 <div id="fobFileNames${index.count }">
													<a href="<%=CommonConstants2.FOB_FILES%>${obj.attachment } " class="filevalue" download>${obj.attachment }</a>
													<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
													<input type="hidden" name="fobFileNames" value="${obj.attachment }">
											     </div>
											     <div style="clear:both" ></div>
											</c:forEach>
										</div>
										
	                               
	                             </c:if> --%>
                            	</div>
								<div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateFOB();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addFOB();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/fob" class="btn waves-effect waves-light bg-s" >Cancel</a>
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
	<script type="text/javascript">
    
    
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
    
    let constructionstartdate = document.querySelectorAll('#construction_start_date');
    $.each(constructionstartdate, function(){
    	var dt = this.value.split(/[^0-9]/);
    	this.value = ""; 
    	var options = {format: 'dd-mm-yyyy',autoClose:true,maxDate:new Date()};
    	if(dt.length > 1){
    		options.setDefaultDate = true,
    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
    	}
    	M.Datepicker.init(this, options);
    });
    
    /* window.onload = maxWindow;

    function maxWindow() {
        window.moveTo(0, 0);

        if (document.all) {
            top.window.resizeTo(screen.availWidth, screen.availHeight);
        }

        else if (document.layers || document.getElementById) {
            if (top.window.outerHeight < screen.availHeight || top.window.outerWidth < screen.availWidth) {
                top.window.outerHeight = screen.availHeight;
                top.window.outerWidth = screen.availWidth;
            }
        }
    }*/
    
    
	$(document).ready(function () {
		$('select:not(.searchable):not(.units)').formSelect();
        $('.searchable').select2();
        $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
        $('#remarks').characterCounter();
        
        $('#date_of_approval_icon').click(function () {
            event.stopPropagation();
            $('#date_of_approval').click();
        });
                     
        $('#construction_start_date_icon').click(function () {
            event.stopPropagation();
            $('#construction_start_date').click();
        });
        
              
        $('#actual_completion_date_icon').click(function () {
            event.stopPropagation();
            $('#actual_completion_date').click();
        });
                            
        $('#commissioning_date_icon').click(function () {
            event.stopPropagation();
            $('#commissioning_date').click();
        });
               
        $('#target_date_icon').click(function () {
            event.stopPropagation();
            $('#target_date').click();
        });
        
        $('#revised_completion_icon').click(function () {
            event.stopPropagation();
            $('#revised_completion').click();
        });
        
        
                       
        var project_id_fk = "${fob.project_id_fk}";
        if ($.trim(project_id_fk) != '') {
            getWorksList(project_id_fk);
        }
        
        var work_id_fk = "${fob.work_id_fk}";
        if ($.trim(work_id_fk) != '') {
        	getContractsList(work_id_fk);
        }
        
        var work_status = "${fob.work_status_fk}";
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
    			$("#actual_completion_dateDiv").hide();
    			
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
      			
      			$("#fob_name").attr('readonly', false);
      			$("#estimated_cost").attr('readonly', false);
      			$("#latitude").attr('readonly', false);
      			$("#longitude").attr('readonly', false);
      			$("#completion_cost").attr('readonly', false);
        	}
        	
        }
        
        var size = '${fn:length(fob.fobImages )}';
        
/*         if(size == 0){
        	$("#created_dates0").datepicker({
           	 	 format:'dd-mm-yyyy',
    	       	 maxDate: new Date(),
    	       	 onSelect: function () {
      	    	    $('.confirmation-btns .datepicker-done').click();
      	    	 }
            }).datepicker("setDate", new Date());
        } */
        
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
 			   $('#fob_idError').text(print_value+' alreday exists').css('color', 'red');
 			   //$('#bttn').prop('disabled', true);
 			   flag = false;
 			   return false;
 		   }else{
 			   $('#fob_idError').text('');
 			  // $('#bttn').prop('disabled', false); 
 			   flag = true;
 		   }
 		   
 		   count++;
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
			$("#actual_completion_dateDiv").hide();
			
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
	    files = $("#fobFiles"+no)[0].files;
	    var html = "";
	    for (var i = 0; i < files.length ; i++) {
	    	html =  html + '<div id=fobFileNames'+no+'>'
			 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
			 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
			 + '</div>'
			 + '<div style="clear:both;"></div>';
	    }
	    $("#selectedFiles").append(html);
	    
	    $('#fobFilesDiv'+no).hide();
	    
		var fileIndex = Number(no)+1;
		moreFiles(fileIndex);
	}
	
	function moreFiles(no){
		var html = "";
		html =  html + '<div class="file-field input-field" id="fobFilesDiv'+no+'" >'
		+ '<div class="btn bg-m t-c">'
		+ '<span>Attach Photos</span>'
		+ '<input type="file" id="fobFiles'+no+'" name="fobFiles" onchange="selectFile('+no+')">'
		+ '</div>'
		+ '<div class="file-path-wrapper">'
		+ '<input class="file-path validate" type="text">'
		+ '</div>'                          
		+ '</div>'
		$("#selectedFilesInput").append(html);
	}
	
	function removeFile(no){			
     	$('#fobFilesDiv'+no).remove();
     	$('#fobFileNames'+no).remove();
    } 
	
	function addFOBFileRow(){
		var rowNo = $("#rowNo").val();
        var rNo = Number(rowNo)+1;
        var html = '<tr id="actionRow' + rNo + '">'
          /*  +'<td style="display:none;"> <div class="input-field">'
           +'<select name="fob_file_types" id="fob_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
   		   +'<option value="" >Select</option>'
		     <c:forEach var="obj" items="${fobFileTypesList}">
     	      +'<option value="${obj.fob_file_type }">${obj.fob_file_type}</option>'
		     </c:forEach>
   		   +'</select></div></td>'	  */  		  			
		   
   		html = html +'<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">'	
		   +'<div class="btn bg-m t-c">'	
		   +'<span>Attach Photos</span>'	
		   +'<input type="file" id="fobFiles'+rNo+'" name="fobFiles" accept="image/*">'	
		   +'</div>'	
		   +'<div class="file-path-wrapper">'	
		   +'<input class="file-path validate" type="text" id="fobFileNames'+rNo+'" name="fobFileNames">'	
		   +'</div></td>'
		   +'<td data-head="Photo Date" class="input-field">'
		   +'<span style="display:inline-block;"><input type="text" id="created_dates'+rNo+'" name="created_dates" class="validate datepicker" style="width:150px;" /><button type="button" id="created_dates_'+rNo+'_icon"><i class="fa fa-calendar"></i></button></span>'
		   +'</td>'
		   +'<td style="display:none;"><a ></a><input type="hidden" id="fob_file_ids'+rNo+'" name="fob_file_ids"/></td>'
		   +'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
		   +'</tr>';
	
		$('#fobFilesBody').append(html);
        $("#rowNo").val(rNo);          	
        
        $('.searchable').select2();
        
        $("#created_dates"+rNo).datepicker({
        	 format:'dd-mm-yyyy',
	       	 maxDate: new Date(),
	       	 onSelect: function () {
   	    	    $('.confirmation-btns .datepicker-done').click();
   	    	 }
        });       
    }
	 function removeActions(rowNo){
     	$("#actionRow"+rowNo).remove();
     }

    
    
  //geting works list from database    
    function getWorksList(projectId) {
    	$(".page-loader").show();
        $("#work_id_fk option:not(:first)").remove();
        $("#contract_id_fk option:not(:first)").remove();

        if ($.trim(projectId) != "") {
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorkListForFOBForm",
                data: myParams, cache: false,async : false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
                            var work_id_fk = "${fob.work_id_fk }";
                            if ($.trim(work_id_fk) != '' && val.work_id == $.trim(work_id_fk)) {
                                $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' +  $.trim(workName) + '</option>');
                            } else {
                                $("#work_id_fk").append('<option value="' + val.work_id + '">' +  $.trim(workName) + '</option>');
                            }
                        });
                    }
                    $('.searchable').select2();
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
        $("#contract_id_fk option:not(:first)").remove();
        if($.trim(work_id_fk) != ''){
        	var myParams = { work_id_fk: work_id_fk };
	        $.ajax({
	        	url: "<%=request.getContextPath()%>/ajax/getContractsListForFOBForm",
	            data: myParams, cache: false,async:false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                    	var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                       /*  var contract_id_fk = "${fob.contract_id_fk }";
	                        if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
	                        	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id + '" selected>' +  $.trim(contract_name) + '</option>');
	                        } else {
	                        	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id + '">' +  $.trim(contract_name) + '</option>');
	                        } */
	                        var selectedFlag = ''
	                        <c:forEach var="tempobj" items="${fob.contractsList}">
	                        	
	                        	if('${tempobj.contract_id_fk}' == val.contract_id){
	                        		selectedFlag = 'selected';
	                        	}
		                   	</c:forEach>
		                   	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id + '" '+selectedFlag+'>' +  $.trim(contract_name) + '</option>');
	                       
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
    
    var hitCount = 0;
    function resetWorksAndProjectsDropdowns(){
    	$(".page-loader").show();        	
    	var projectId = '';
    	var workId = ''
   		var contract_id_fk = $("#contract_id_fk").val();
    	//console.log(contract_id_fk);
    	
   		if(!contract_id_fk.includes(",")){  
        	workId = $("#contract_id_fk").find('option:selected').attr("workId");
        	//console.log(workId);
        	if(workId!=undefined)
        		{
	        	projectId = workId.substring(0, 3);    
	   			//workId = workId.substring(3, work_id.length);
	   			$("#project_id_fk").val(projectId);
   			}
   			$("#project_id_fk").select2();
   		}
   		if ($.trim(projectId) != "") {
   			$("#work_id_fk option:not(:first)").remove();
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorkListForFOBForm",
                data: myParams, cache: false,aync:false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) }
                            if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
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
            $('.searchable').select2();
        }
   		
   		/* var size = '${fn:length(fob.contractsList )}'
   		console.log(size); */
   		
   		var work_id_fk = "${fob.work_id_fk}";
   		if($.trim(work_id_fk) == '' && $.trim(workId) != '' && hitCount == 0)
   		{
   			$("#contract_id_fk option:not(:first)").remove();
        	var myParams = { work_id_fk: workId };
        	//console.log(hitCount);
	        $.ajax({
	        	url: "<%=request.getContextPath()%>/ajax/getContractsListForFOBForm",
	            data: myParams, cache: false,aync:false,
	            success: function (data) {
	                if (data.length > 0) {
	                    $.each(data, function (i, val) {
	                    	var contract_name = '';
	                        if ($.trim(val.contract_short_name) != '') { contract_name =  $.trim(val.contract_short_name) }
	                        var selectedFlag = ''
	                        
	                        <c:forEach var="tempobj" items="${fob.contractsList}">
	                        	
	                        	if('${tempobj.contract_id_fk}' == val.contract_id){
	                        		selectedFlag = 'selected';
	                        	}
		                   	</c:forEach>
		                   	
		                   	if(val.contract_id == contract_id_fk){selectedFlag = 'selected';}
		                   	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id + '" '+selectedFlag+'>' +  $.trim(contract_name) + '</option>');
	                    });
	                }
	                $('.searchable').select2();
	                $(".page-loader").hide();
	            }
	        });
	        
	        hitCount = 1;
        }
   	 $(".page-loader").hide();
   		
    }
    
    
    $('form').on('reset', function () {
        $(".searchable").trigger("change");
    });
    
    function addFOB(){
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
			$(".page-loader").show();
  			if(flag){
  				$('form input[name=fob_detail_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  	  			$('form input[name=fob_detail_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  	  			document.getElementById("fobForm").submit();	
 			}
 			$(".page-loader").hide();
 			return false;
	 	}
	}
	
    function updateFOB(){
  		if(validator.form()){ 
  			$(".page-loader").show();
  			var completion_cost = $("#completion_cost").val();
			var estimated_cost = $("#estimated_cost").val();
			if(completion_cost == ""){
  				$('#completion_cost_units').val("");
  			}if(estimated_cost == ""){
  				$('#estimated_cost_units').val("");
  			}
  			$("#project_id_fk").attr('disabled', false);
  			$("#work_id_fk").attr('disabled', false);
  			$("#contract_id_fk").attr('disabled', false);
  			$("#responsible_people_id_fk").attr('disabled', false);
  			$("#target_date").attr('disabled', false);
  			$("#construction_start_date").attr('disabled', false);
  			$("#revised_completion").attr('disabled', false);
  			$("#commissioning_date").attr('disabled', false);
  			$("#actual_completion_date").attr('disabled', false);
  			
  				    		
  			$('form input[name=fob_detail_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			$('form input[name=fob_detail_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
  			document.getElementById("fobForm").submit();			
	 	}
	}
	
	//Wait for the DOM to be ready
	
	// to validate apartment form inputs thruogh jquery.
	   
	var validator = $('#fobForm').validate({
	    	ignore: ":hidden:not(.validate-dropdown)",
			   rules: {
				   	  "fob_id": {
				 		required: true
			 	  	  },"project_id_fk": {
   				 		required: false
   				 	  },"work_id_fk": {
				 		required: false
				 	  },"contract_id_fk": {
				 		required: true
				 	  },"fob_name": {
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
				     "fob_id": {
  			 			required: 'Required'
  			 	  	 },"project_id_fk": {
   			 			required: 'Required'
   			 	  	 },"work_id_fk": {
			 			required: 'Required'
			 	  	 },"contract_id_fk": {
			 			required: 'Required'
			 	  	 },"fob_name": {
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
				    if (element.attr("id") == "fob_id" ){
			 		     document.getElementById("fob_idError").innerHTML="";
			 			 error.appendTo('#fob_idError');
			 	    }else if (element.attr("id") == "project_id_fk" ){
 			 		     document.getElementById("project_id_fkError").innerHTML="";
 			 			 error.appendTo('#project_id_fkError');
 			 	    }else if (element.attr("id") == "work_id_fk" ){
			 		     document.getElementById("work_id_fkError").innerHTML="";
			 			 error.appendTo('#work_id_fkError');
			 	    }else if (element.attr("id") == "contract_id_fk" ){
			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
			 			 error.appendTo('#contract_id_fkError');
			 	    }else if (element.attr("id") == "fob_name" ){
			 		     document.getElementById("fob_nameError").innerHTML="";
			 			 error.appendTo('#fob_nameError');
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
        
        
        
         function addFOBImages(){      		
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
    		   		+'<input type="file" id="fobFile'+rNo+'" name="fobFile" accept="image/*" onchange="readURL(this,'+rNo+');">'
    		   		+'</div>'
    		   		+' <div class="file-path-wrapper">'
    		   		+'<input class="file-path validate" type="text" name="attachment" >'
    		   		+'<img style="height: 20%;width: 20%; id="fobImagePreview'+rNo+'"   />'
    		   		+'</div>'
    		   		+'</div>'
    		   		+'</div>'
    		   		+'<div class="col m2 hide-on-small-only"></div></div>'
		            +'</td>'
    			   	+'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeFOBImages('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
    			 
			 $('#fobImagesTable').append(html);
			 $("#rowNo").val(rNo);
        }
        
		 function removeFOBImages(rowNo){
        	$("#fobImagesRow"+rowNo).remove();
         } 
		 
		 function readURL(input,rowNo) {
	            if (input.files && input.files[0]) {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                    $('#fobImagePreview'+rowNo).attr('src', e.target.result)
	                        //.width(150)
	                        //.height(200);
	                };
	                reader.readAsDataURL(input.files[0]);
	                $('#fobImagePreview'+rowNo).show();
	            }
	     }
		/*  function removeMedia(link,id){
		   	  $('#'+id).val('');
		   	 // $(link).prev().text('');
		   	  $('#fobImagePreview').hide();
		   	  $(link).css('display','none');
		 }   */
    </script>
	
</body>
</html>