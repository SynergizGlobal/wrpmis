<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update WORK - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add WORK - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">    
	<link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
	<link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
	
	<link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"	rel="stylesheet">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/work.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
	<link rel="stylesheet" href="/wrpmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
 	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-responsive-table.css" />
    <style>
        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            color: #777;
        }
        .fixed-width {
            width: 100%;
            margin-left:auto !important;
            margin-right:auto !important;
        }
        @media(max-width: 1380px){
			.fs7rem{font-size: .7rem !important;}
		}
        @media only screen and (min-width: 768px){
	        .fs-md-9r{
	        	font-size:.9rem;
	        }
        }
        .my-error {
   			 color:red;
   			 font-size: .8rem;
		}
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        .input-field p.searchable_label {
        	font-size:0.85rem;        	
		    margin-top: -4px !important;
        }
        
        #revisionsTableBody .select2-container--default .select2-selection--single {
        	background-color:transparent;
        }
        .my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}		
		#revisionsTableBody .select2-container{
			text-align:left;
			max-width:127px;
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
		.col.input-field>textarea+label:not(.label-icon).active{
			margin-top:0;
		}
		.mt-brdr .center-align.m-1 button.bg-m, 
		.mt-brdr .center-align.m-1 button.bg-s{
			width:inherit;
		}
		/* cost unit dropdown , lable and input styling starts here  */
		.pt-14{
			padding-top:14px !important;
		}
		.cost_dropdown{
			min-width:90px !important;
		}
		.input-field .prefix.cost ~ input,
		.input-field .prefix.cost ~ label,
		.input-field .prefix.cost ~ .validate ~ label {
		    margin-left: 2rem;
		    width: 92%;
		    width: calc(100% - 2rem);
		}
		.input-field.col .prefix.cost ~ label, 
		.input-field.col .prefix.cost ~ .validate ~ label {
		    width: calc(100% - 2rem - 1.5rem);
		}
		/* cost unit dropdown , lable and input styling ends here  */
	
	
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
		.mt-md-0{
			margin-top:0 !important;
		}
		.lh{
			line-height:inherit;
		}
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			#revisionsTableBody tr .input-field .datepicker~button {
			    position: relative;
			    top: 0;
			    right: 26px;
			}			
			/* .mobile_responsible_table #revisionsTableBody tr td .select2-container {
			    width: 100% !important;
			} */
			#revisionsTableBody .select2-container {
			    max-width: inherit;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td:not(.verti-top)::before {
			    vertical-align: middle;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td.verti-top::before {
			    vertical-align: top;
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
			.mb-md-20 {
				margin-bottom:0 !important;
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
			/* .mobile_responsible_table>tbody tr:not(.datepicker-row):not(.mobile_hide_row) {
			    border-bottom: 3px solid #662E9B;
			} */
			.sm-w100 input{
				width:100% !important;
			}		
		}		
			.input-field>input[type='text'].datepicker ~ label:not(.label-icon).active{
				-webkit-transform: translateY(-20px) scale(0.95);
			    transform: translateY(-20px) scale(0.95);
			}	
			
		@media only screen and (max-width: 769px) and (min-width: 500px){
			#revisionsTableBody .select2-container--default,
			.mobile_responsible_table .select2-container--default {
			    width: 93% !important;
			}
		}
		@media(max-width: 360px){
			.fs7rem{font-size: .7rem !important;}
		}
    </style>
</head>
<body>
    <!-- header  starts-->
    <%--  <jsp:include page="../layout/header.jsp"></jsp:include> --%>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                            	 <c:if test="${action eq 'add'}">	
                               			 <h6>Add Work</h6>
                               	 </c:if>
                               	 <c:if test="${action eq 'edit'}">	
                               			 <h6>Edit Work</h6>
                               	 </c:if>
                            </div>
                        </span>
                    </div>
            <div class="row clearfix" style="margin-bottom:0">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                	<c:if test="${not empty success }">
				        <div class="center-align m-1 close-message">	
						   ${success}
						</div>
					</c:if>
					<c:if test="${not empty error }">
						<div class="center-align m-1 close-message">
						   ${error}
						</div>
					</c:if>
				</div>
			</div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                    	 <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath()%>/mobileappwebview/update-work" id="workForm" name="workForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                         </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath()%>/mobileappwebview/add-work" id="workForm" name="workForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
                      
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col s12 m8 offset-m2">
	                                 <div class="row">
	                                 	<c:if test="${action eq 'add'}">
	                                 	<div class="col s6 m6 input-field">
		                                   <p class="searchable_label">Project : <span class="required">*</span></p>
		                                    <select class="searchable validate-dropdown"  name ="project_id_fk" id="project_id_fk"  >
		                                   		  <option value="">select</option>
		                                          <c:forEach var="obj" items="${projectsList}">
		                       						  <option value="${obj.project_id }"<c:if test="${workDetails.project_id_fk eq obj.project_id }">selected</c:if>><%-- ${obj.project_id} - --%> ${obj.project_name}</option>
		                                          </c:forEach>
		                                    </select>
		                                    <span id="project_id_fkError"></span>
		                               </div> 
		                               <input type="hidden" name ="project_name" id="project_name"/>
		                               </c:if>
		                               <c:if test="${action eq 'edit'}">
			                               <div class="col s6 m6 input-field">
												<input type="text" class="form-control" value="${workDetails.project_name}" readonly >  
												<label >Project<span class="required">*</span>:</label>			                                    
			                                    <input type="hidden" name ="project_id_fk" id="project_id_fk" value="${workDetails.project_id_fk}"/>
			                               </div>
		                               </c:if>
		                                                                    
		                               <%-- <div class="col s12 m4 input-field">
			                               <input type="text" class="validate" id="pink_book_item_number" name="pink_book_item_number" value="${workDetails.pink_book_item_number }">
		                                   <label for="pb_item_no">PB Item No</label>
		                                   <span id="pb_item_noError"></span>
		                               </div> --%>
		                               
	                                 </div>
                                </div>
                             </div>

                            <div class="row">
                                <div class="col s12 m8 input-field offset-m2">
                                    <textarea id="work_name" class="pmis-textarea" data-length="1000" name="work_name">${workDetails.work_name }</textarea>
                                    <label for="work_name">Work Name <span class="required">*</span></label>
                                     <span id="work_nameError"></span>
                                </div>
                                <div class="col s6 m8 input-field offset-m2">
                                    <input id="work_short_name" type="text" class="validate" name="work_short_name" value="${workDetails.work_short_name }">
                                    <label for="work_short_name">Work Short Name<span class="required">*</span></label>
                                     <span id="work_short_nameError"></span>
                                </div>
                          
                                <div class="col s6 m4 input-field offset-m2">
                       				<p class="searchable_label">Sanctioned Year</p>
                                    <select class="searchable" id="sanctioned_year_fk" name="sanctioned_year_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${yearList}">
 					  				  <option  value="${obj.financial_year }"<c:if test="${workDetails.sanctioned_year_fk eq obj.financial_year}">selected</c:if>> ${obj.financial_year}</option>
                       			    </c:forEach>
                                    </select>
                                    <span id="sanctioned_yearError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                   <input id="completeion_period_months" type="number" class="validate" name="completeion_period_months" value="${workDetails.completeion_period_months }">
                                   <label for="completeion_period_months">Completion Period (in Months)</label>
                                   <span id="completeion_period_monthsError"></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s8 m5 l3 input-field offset-m2 offset-l2">
                       				<i class="material-icons prefix cost">₹</i>
                                    <input id="sanctioned_estimated_cost" type="number" class="validate" name="sanctioned_estimated_cost" value="${workDetails.sanctioned_estimated_cost }" min="0.01" step="0.01">
                                    <label for="sanctioned_estimated_cost" class="active fs-sm-8rem fs-md-9r fs7rem">Sanctioned Estimated Cost</label>
                                    <span id="sanctioned_estimated_costError"></span>
                                </div>
                                <div class="col s4 m3 l1 input-field">
                                	<p class="searchable_label">Units</p>
                                	<select class="units validate-dropdown" id="sanctioned_estimated_cost_unit" name="sanctioned_estimated_cost_unit">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${workDetails.sanctioned_estimated_cost_unit eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                	<span id="sanctioned_estimated_cost_unitError" class="error-msg" ></span>
                                	<!-- <label for="sanctioned_estimated_cost_unit">Units</label> -->
                                </div>
                              
                                <div class="col s8 m5 l3 input-field offset-m2 ">
                                  	<i class="material-icons prefix cost">₹</i>
                                    <input id="sanctioned_completion_cost" type="number" class="validate" name="sanctioned_completion_cost" value="${workDetails.sanctioned_completion_cost }" min="0.01" step="0.01">
                                    <label for="sanctioned_completion_cost" class="fs-sm-67rem fs-md-9r fs7rem"> Sanctioned Completion Cost</label>
                                    <span id="sanctioned_completion_costError"></span>
                                </div>
                                <div class="col s4 m3 l1 input-field">
                                	<p class="searchable_label">Units</p>
                                	<select class="units validate-dropdown" id="sanctioned_completion_cost_unit" name="sanctioned_completion_cost_unit">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${workDetails.sanctioned_completion_cost_unit eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                	<span id="sanctioned_completion_cost_unitError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                    <input id="projected_completion" name="projected_completion" type="text" class="validate datepicker" value="${workDetails.projected_completion }">
                                    <label for="projected_completion">Target date</label>
                                    <button type="button" id="projected_completion_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="projected_completionError" class="error-msg" ></span>
                                </div>
                               
                                <div class="col s6 m4 input-field">
                                    <input id="projected_completion_date" type="text" class="validate datepicker" name="projected_completion_date" value="${workDetails.projected_completion_date }">
                                    <label for="projected_completion_date" class="active fs-sm-8rem">Revised completion date</label>
                                    <button type="button" id="projected_completion_date_icon"><i class="fa fa-calendar"></i></button>
                                    <span id="projected_completion_dateError"></span>
                                </div>
                            </div>
                            
                            <div class="row">                              
                                <div class="col s8 m3 input-field offset-m2">
                                  	<i class="material-icons prefix cost">₹</i>
                                    <input id="anticipated_cost" type="number" class="validate" name="anticipated_cost" value="${workDetails.anticipated_cost }" min="0.01" step="0.01">
                                    <label for="anticipated_cost">Anticipated cost</label>
                                    <span id="anticipated_costError"></span>
                                </div>
                                <div class="col s4 m1 input-field">
                                	<p class="searchable_label">Units</p>
                                	<select class="units validate-dropdown" id="anticipated_cost_unit" name="anticipated_cost_unit">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${workDetails.anticipated_cost_unit eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                	<span id="anticipated_cost_unitError" class="error-msg" ></span>
                                </div>
                                <c:if test="${action eq 'edit'}">
	                                <div class="col s12 m4 input-field">
	                                   <p class="searchable_label">Work Status <span class="required">*</span></p>
	                                    <select id="work_status_fk" name="work_status_fk"  class="select searchable validate-dropdown">
	                                        <option value="">Select</option>
	                                    </select>
	                                    <span id="work_status_fkError" class="error-msg" ></span>
	                                </div> 
	                                <input type="hidden" id="existing_work_status_fk" name="existing_work_status_fk" value="${workDetails.work_status_fk }"/> 
                                </c:if>                              
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m4 input-field offset-m2">
                                <p class="searchable_label">Railway Agency</p>
                                 <select  class="searchable validate-dropdown" name="railway_id_fk" id="railway_id_fk" multiple="multiple" >
                                  		 <option value="" disabled="disabled">Select</option>
                                          <c:forEach var="obj" items="${railwaysList}">
                                          	<c:if test="${obj.railway_id ne 'Con' and obj.railway_id ne 'Others'}">
										 		<option value="${obj.railway_id }" 
											 		<c:forEach var="tempobj" items="${workDetails.railwayAgencyList}">
											 			<c:if test="${tempobj.railway_id_fk eq obj.railway_id}">selected</c:if>
		                                          	</c:forEach>
										 		>${obj.railway_name}</option>
									 		</c:if>
                                          </c:forEach>
                                  </select>
                                  <span id="railway_id_fkError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                <p class="searchable_label">Executed By</p>
                                  <select  class="searchable validate-dropdown" name="executed_by_id_fk" id="executed_by_id_fk" multiple="multiple" >
                                   <option value="" disabled="disabled">Select</option>
                                   <c:forEach var="obj" items="${railwaysList}">
                                   		<c:if test="${obj.railway_id ne 'Con' and obj.railway_id ne 'Others'}">
	           					  			 <option value="${obj.railway_id }"            					  			 
	           					  			 		<c:forEach var="tempobj" items="${workDetails.executedByList}">
											 			<c:if test="${tempobj.executed_by_id_fk eq obj.railway_id}">selected</c:if>
		                                          	</c:forEach>           					  			 
	           					  			 > ${obj.railway_name}</option>
           					  			</c:if>
                                   </c:forEach>
                                  </select>
                                     <span id="executed_by_id_fkError"></span>
                                </div>
                            </div> 
                            
                            <%-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
								  <c:if test="${action eq 'add'}">
			                            <div id="selectedFilesInput">
		                                    	<div class="file-field input-field" id="workFilesDiv1" >
			                                        <div class="btn bg-m t-c">
			                                            <span>Attach Files</span>
			                                            <input type="file" id="workFiles1" name="workFile"  onchange="selectFile('1')">
			                                        </div>
			                                        <div class="file-path-wrapper">
			                                            <input class="file-path validate" type="text">
			                                        </div>                                       
			                                    </div>
										</div>
	                                    <div id="selectedFiles">
										</div>
								  </c:if>	
								  <c:if test="${action eq 'edit'}">
										<c:set var="existingWorkFilesLength" value="${fn:length(workDetails.workFilesList )}"></c:set>
										<c:if test="${fn:length(workDetails.workFilesList ) gt 0}">
											<c:set var="existingWorkFilesLength" value="${fn:length(workDetails.workFilesList )+1}"></c:set>
										</c:if>
										<div id="selectedFilesInput">
	                                    	<div class="file-field input-field" id="workFilesDiv${existingWorkFilesLength }" >
		                                        <div class="btn bg-m t-c">
		                                            <span>Attach Files</span>
		                                            <input type="file" id="workFiles${existingWorkFilesLength }" name="workFile"  onchange="selectFile('${existingWorkFilesLength }')">
		                                        </div>
		                                        <div class="file-path-wrapper">
		                                            <input class="file-path validate" type="text">
		                                        </div>                                       
		                                    </div>
										</div>
	                                    
	                                    <div id="selectedFiles">
	                                    	<c:forEach var="obj" items="${workDetails.workFilesList }" varStatus="index">
												 <div id="workFileNames${index.count }">
													<a href="<%=CommonConstants2.WORK_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
													<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
													<input type="hidden" name="workFileNames" value="${obj.attachment }">
											     </div>
											     <div style="clear:both" ></div>
											</c:forEach>
										</div>
	                             	</c:if>	
								</div>
								<div class="col m2 hide-on-small-only"></div>
                            </div> --%>
                            
                            <div class="row">
								<div class="col m8 s12 offset-m2">
									<div class="row fixed-width"
										style="margin-bottom: 20px; margin-top: 20px">
										<div class="table-inside">
											<table class="mdl-data-table update-table mobile_responsible_table">
												<thead>
													<tr>
														<!-- <th style="width: 30%;text-align: left;">File Type</th>
														<th style="width: 52%;text-align: left;">Attach File</th>
														<th></th>
														<th style="width: 8%;text-align: left;">Action</th> -->
														
														<th>File Type</th>
														<th>Attach File</th>
														<th style="display:none;"></th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="workFilesBody">
													<c:choose>
														<c:when	test="${not empty workDetails.workFilesList && fn:length(workDetails.workFilesList) gt 0 }">
															<c:forEach var="iObj" items="${workDetails.workFilesList }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td data-head="File Type" class="input-field">
																		<select  name="work_file_types"  id="work_file_types${index.count }"  class="validate-dropdown searchable">
						                                   					  <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${workFileTypes}">
						                    					  				 <option value="${obj.work_file_type }" <c:if test="${iObj.work_file_type_fk eq obj.work_file_type}">selected</c:if>>${obj.work_file_type}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</td>
																	<td data-head="Attach File" class="file-field input-field cell-disp-inb">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach File</span>
								                                            <input type="file" id="workFiles${index.count }" name="workFiles">
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="workFileNames${index.count }" name="workFileNames" value="${iObj.attachment }">
								                                        </div>                             
			                                                      	</td>
			                                                      	<td style="display:none;">
			                                                      		<input type="hidden" id="work_file_ids${index.count }" name="work_file_ids" value="${iObj.work_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.WORK_FILES%>${iObj.work_id_fk }/${iObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
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
																	<select  name="work_file_types"  id="work_file_types0"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${workFileTypes}">
					                    					  				 <option value="${obj.work_file_type }">${obj.work_file_type}</option>
					                                          			  </c:forEach>
					                               					  </select>
																</td>
																<td data-head="Attach File" class="file-field input-field cell-disp-inb">
							                                        <div class="btn bg-m t-c">
							                                            <span>Attach File</span>
							                                            <input type="file" id="workFiles0" name="workFiles">
							                                        </div>
							                                        <div class="file-path-wrapper">
							                                            <input class="file-path validate" type="text" id="workFileNames0" name="workFileNames">
							                                        </div>                                       
		                                                      	</td>
		                                                      	<td style="display:none;"><input type="hidden" id="work_file_ids0" name="work_file_ids"/></td>
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
														<td colspan="5" ><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c "
															onclick="addWorkFileRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											
											<c:choose>
												<c:when test="${not empty (workDetails.workFilesList) && fn:length(workDetails.workFilesList) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo" value="${fn:length(workDetails.workFilesList) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
                            
                            <div class="row">
                                <div class="col s12 m8 input-field offset-m2">
                                    <textarea id="remarks" class="pmis-textarea" data-length="1000" maxlength="1000" name="remarks">${workDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError"></span>
                                </div>
                            </div>
                            
                            <c:if test="${action eq 'edit'}">
                            <div class="row fixed-width" style="margin-bottom:20px">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">

                                    <table id="revisionsTable" class="mdl-data-table mobile_responsible_table">
                                        <thead>
                                            <tr>
                                                <th>Financial Year </th>
                                                <!-- <th>PB Item No </th> -->
                                                <th colspan="2">Latest Revised Cost </th>
                                                <th>Year of Revision </th>
                                                <th>Revision No </th>
									<!--        <th>Remarks </th> -->
                                                <th>Action</th>
                                            </tr>
                                        </thead> 
                                        <tbody id="revisionsTableBody">
											<c:choose>
                                      		  <c:when test="${not empty workDetails.workRevisions && fn:length(workDetails.workRevisions) gt 0 }">
                                        			
                                        		<c:forEach var="revObj" items="${workDetails.workRevisions }" varStatus="index">                                        	
	                                           <tr id="revisionRow${index.count }">                                            	
	                                                <td data-head="Financial Year" class="input-field"> 
	                              					 <select  name="financial_years"  id="financial_years${index.count }"  class="validate-dropdown searchable">
	                                   					 <option value="" >select</option>
	                                         			  <c:forEach var="obj" items="${yearList}">
	                    					  				 <option value="${obj.financial_year }"<c:if test="${revObj.financial_year eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
	                                          			  </c:forEach>
	                               					  </select>
	                                                </td>
	                                                <%-- <td>
	                                                    <input id="pink_book_item_numbers${index.count }" name="pink_book_item_numbers" type="text" class="validate" value="${revObj.pink_book_item_number }" 
	                                                        placeholder="PB Item Number">
	                                                </td> --%>
	                                                <!-- <td data-head="Latest Revised Cost" class="input-field verti-top" style="height:auto;"> -->
	                                                	<%-- <div class="col s9">
	                                                    	<input id="latest_revised_costs${index.count }" name="latest_revised_costs" type="number" class="validate" value="${revObj.latest_revised_cost }"
	                                                        placeholder="Latest Revised Cost">
	                                                    </div> 
	                                                    <div class="col s3 pt-14">
	                                                    	<select class="units validate-dropdown" id="latest_revised_cost_units${index.count}" name="latest_revised_cost_units">
						                                		<option value="">Select</option>
						                                		<option value="rs">Rs</option>
						                                		<option value="thousands">Thousands</option>
						                                		<option value="lacs">Lacs</option>
						                                		<option value="crores">Crores</option>
						                                	</select>
	                                                    </div> --%>

														<%-- <span style="display:inline-block;">
															<div class="sm-w100">
		                                                    	<input id="latest_revised_costs${index.count }" name="latest_revised_costs" type="number" class="validate" value="${revObj.latest_revised_cost }"
		                                                        placeholder="Latest Revised Cost">
		                                                    </div> 
		                                                    <div class=" pt-14" >
		                                                    	<select class="units validate-dropdown" id="latest_revised_cost_units${index.count}" name="latest_revised_cost_units">
							                                		<option value="">Select</option>
							                                		<option value="rs">Rs</option>
							                                		<option value="thousands">Thousands</option>
							                                		<option value="lacs">Lacs</option>
							                                		<option value="crores">Crores</option>
							                                	</select>
		                                                    </div> 
	                                                    </span> --%>
	                                                <!-- </td> -->
	                                                <td data-head="Latest Revised Cost" class="input-field">
	                                                	<input id="latest_revised_costs${index.count }" name="latest_revised_costs" type="number" class="validate" value="${revObj.latest_revised_cost }"
	                                                        placeholder="Latest Revised Cost">
	                                                </td>
	                                                <td>
	                                                	<select class="units validate-dropdown" id="latest_revised_cost_units${index.count}" name="latest_revised_cost_units">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
				                                  			   <option value="${obj.value }" <c:if test="${revObj.latest_revised_cost_unit eq obj.value}">selected</c:if>>${obj.unit }</option>
				                                   			 </c:forEach>
					                                	</select>
					                                	<p id="units${index.count}Error" class="my-error" ></p>
	                                                </td>
	                                                
	                                                <td data-head="Year of Revision" class="input-field">
	                                                   <select id="year_of_revisions${index.count }"  name="year_of_revisions" class="searchable">
	                                                           <option value="" >select</option>
	                                                        <c:forEach var="obj" items="${yearList}">
	                    					  				  <option  value="${obj.financial_year }"<c:if test="${revObj.year_of_revision eq obj.financial_year}">selected</c:if>> ${obj.financial_year}</option>
	                                          			    </c:forEach>
	                                                    </select>
	                                                </td>
	                                                <td data-head="Revision No" class="input-field">
	                                                    <input id="revision_numbers${index.count }" name="revision_numbers" type="text" class="validate" value="${revObj.revision_number }"
	                                                        placeholder="Revision Number">
	                                                </td>
	                                                <td class="mobile_btn_close">
	                                                    <a class="btn waves-effect waves-light red t-c " onclick="removeRevision('${index.count }');"> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
	                                            </tr>
                                            </c:forEach>
                                            </c:when>
                                        	<c:otherwise>
                                        		<tr id="revisionRow0">                                            	
	                                                <td data-head="Financial Year" class="input-field"> 
	                              					 <select  name="financial_years"  id="financial_years0"  class="validate-dropdown searchable">
	                                   					 <option value="" >Select</option>
	                                         			  <c:forEach var="obj" items="${yearList}">
	                    					  				 <option value="${obj.financial_year }">${obj.financial_year}</option>
	                                          			  </c:forEach>
	                               					  </select>
	                                                </td>
	                                                <!-- <td>
	                                                    <input id="pink_book_item_numbers0" name="pink_book_item_numbers" type="text" class="validate" 
	                                                        placeholder="PB Item Number">
	                                                </td> -->	                                                
	                                               <!--  <td data-head="Latest Revised Cost" class="input-field verti-top" style="height:auto;"> -->
	                                              <%-- <div class="col s9">
	                                                    	<input id="latest_revised_costs0" name="latest_revised_costs" type="number" class="validate" 
	                                                        placeholder="Latest Revised Cost">
	                                                    </div> 
	                                                    <div class="col s3 pt-14">
	                                                    	<select class="units" id="latest_revised_cost_units${index.count}" name="latest_revised_cost_units">
						                                		<option value="">Select</option>
						                                		<option value="rs">Rs</option>
						                                		<option value="thousands">Thousands</option>
						                                		<option value="lacs">Lacs</option>
						                                		<option value="crores">Crores</option>
						                                	</select>
	                                                    </div > --%>
													<%-- <span style="display:inline-block">
														<div class="sm-w100">
	                                                    	<input id="latest_revised_costs${index.count }" name="latest_revised_costs" type="number" class="validate" value="${revObj.latest_revised_cost }"
	                                                        placeholder="Latest Revised Cost">
	                                                    </div> 
	                                                    <div class=" pt-14" >
	                                                    	<select class="units" id="latest_revised_cost_units${index.count}" name="latest_revised_cost_units">
						                                		<option value="">Select</option>
						                                		<option value="rs">Rs</option>
						                                		<option value="thousands">Thousands</option>
						                                		<option value="lacs">Lacs</option>
						                                		<option value="crores">Crores</option>
						                                	</select>
	                                                    </div>
	                                                    </span> --%>
	                                               <!--  </td> -->
	                                               <td>
	                                               		<input id="latest_revised_costs0" name="latest_revised_costs" type="number" class="validate" value="${revObj.latest_revised_cost }"
	                                                        placeholder="Latest Revised Cost">
	                                               </td>
	                                               <td>
	                                               		<select class="units validate-dropdown" id="latest_revised_cost_units0" name="latest_revised_cost_units">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
					                                  			   <option value="${obj.value }">${obj.unit }</option>
					                                   		 </c:forEach>
					                                	</select>
					                                	<p id="units0Error" class="my-error" ></p>
	                                               </td>
	                                               
	                                                <td  data-head="Year of Revision" class="input-field ">
	                                                   <select id="year_of_revisions0" name ="year_of_revisions" class="searchable" >
	                                                           <option value="" selected>Select</option>
	                                                        <c:forEach var="obj" items="${yearList}">
	                    					  				  <option  value="${obj.financial_year }"> ${obj.financial_year}</option>
	                                          			    </c:forEach>
	                                                    </select>
	                                                </td>
	                                                <td data-head="Revision No" class="input-field">
	                                                    <input id="revision_numbers0" name="revision_numbers" type="text" class="validate" 
	                                                        placeholder="Revision Number">
	                                                </td>
	                                                <td class="mobile_btn_close">
	                                                    <a class="btn waves-effect waves-light red t-c " onclick="removeRevision('0');"> <i
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
			  										 <td colspan="9" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addRevisionRow()"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
                                   
                                    <c:choose>
                                        <c:when test="${not empty workDetails.workRevisions && fn:length(workDetails.workRevisions) gt 0 }">
                                    		<input type="hidden" id="RevrowNo"  name="rowNo" value="${fn:length(workDetails.workRevisions) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="RevrowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m8 l4 input-field offset-m2 offset-l2">
                       				<p class="searchable_label">Actual Year of Completion</p>
                                    <select id="year_of_completion" name="year_of_completion" class="searchable">
                                          <option value="">Select</option>
                                           <c:forEach var="obj" items="${yearList}">
        					  				  <option  value="${obj.financial_year }"<c:if test="${workDetails.year_of_completion eq obj.financial_year}">selected</c:if>> ${obj.financial_year}</option>
                              			   </c:forEach>
                                    </select>
                                    <span id="year_of_completionError"></span>
                                </div>
                                <div class="col s8 m5 l3 input-field offset-m2">
                                	<i class="material-icons prefix cost">₹</i>
                                    <input id="completion_cost" type="number" class="validate" name="completion_cost" value="${workDetails.completion_cost }" min="0.01" step="0.01">
                                    <label for="completion_cost" class="fs-sm-8rem">Actual Completion cost</label>
                                    <span id="completion_costError"></span>
                                </div>
                                <div class="col s4 m3 l1 input-field">
                                	<p class="searchable_label">Units</p>
                                	<select class="units validate-dropdown" id="completion_cost_unit" name="completion_cost_unit">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${workDetails.completion_cost_unit eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		 </c:forEach>
                                	</select>
                                	<span id="completion_cost_unitError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> 
                            </c:if>

                            <div class="row">
                                <div class="col s6 m4 mt-brdr offset-m2 center-align">
                                    <div class=" m-1">
                                      <c:if test="${action eq 'edit'}">
                                       <button type="button" onclick="updateWork();" class="btn waves-effect waves-light bg-m">Update</button>
                                      </c:if>
                                    <c:if test="${action eq 'add'}">
                                        <button type="button" onclick="addWork();" class="btn waves-effect waves-light bg-m">Add</button>
                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 mt-brdr center-align">
                                    <div class=" m-1">
                                        <a  href="<%=request.getContextPath()%>/mobileappwebview/work"  class="btn waves-effect waves-light bg-s">Cancel</a>
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
<%--    <jsp:include page="../layout/footer.jsp"></jsp:include> --%> 


	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
	<script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>


    <script type="text/javascript">
    
    /**************************************************************************************************************/
    
    
    
        function getWorkStatusList(work_status_fk){
	       	$(".page-loader").show();
   	    	$("#work_status_fk option:not(:first)").remove();
               $.ajax({
                   url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getWorkStatusList",
                   cache: false,async: false,
                   success: function (data) {
                      if(data != null && data != '' && data.length > 0){  
                           $.each(data, function (i, val) {
                           		var selectedFlag = (work_status_fk == val.work_status_fk)?'selected':'';
   	                           $("#work_status_fk").append('<option value="' + val.work_status_fk + '"'+selectedFlag+'>' + $.trim(val.work_status_fk) + '</option>');
                           });
                       }
                       $('.searchable').select2();
                       $(".page-loader").hide();
                   },error: function (jqXHR, exception) {
    	   			      $(".page-loader").hide();
   	   	          	  getErrorMessage(jqXHR, exception);
   	   	     	  }
               });
    	 }    
    
    
		function addWorkFileRow(){
			var rowNo = $("#rowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="actionRow' + rNo + '">'
	           +'<td data-head="File Type" class="input-field">'
	           +'<select name="work_file_types" id="work_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${workFileTypes}">
	     	      +'<option value="${obj.work_file_type }">${obj.work_file_type}</option>'
			     </c:forEach>
	   		   +'</select></div></td>' 			
	   		   +'<td data-head="Attach File" class="file-field input-field cell-disp-inb">'	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach File</span>'	
			   +'<input type="file" id="workFiles'+rNo+'" name="workFiles">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="workFileNames'+rNo+'" name="workFileNames">'	
			   +'</div></td>'
			   +'<td style="display:none;"><input type="hidden" id="work_file_ids'+rNo+'" name="work_file_ids"/></td>'
			   +'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			$('#workFilesBody').append(html);
	        $("#rowNo").val(rNo);          	
	        
	        $('.searchable').select2();
	    }
	    
	    function removeActions(rowNo){
	    	$("#actionRow"+rowNo).remove();
	    }
	
	/****************************************************************************************************/
	    function selectFile(no){
		    files = $("#workFiles"+no)[0].files;
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=workFileNames'+no+'>'
				 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
				 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
				 + '</div>'
				 + '<div style="clear:both;"></div>';
		    }
		    $("#selectedFiles").append(html);
		    
		    $('#workFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			moreFiles(fileIndex);
		}
		
		function moreFiles(no){
			var html = "";
			html =  html + '<div class="file-field input-field" id="workFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c">'
			+ '<span>Attach Files</span>'
			+ '<input type="file" id="workFiles'+no+'" name="workFile"  onchange="selectFile('+no+')">'
			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFile(no){			
	     	$('#workFilesDiv'+no).remove();
	     	$('#workFileNames'+no).remove();
	    } 
	
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

        $(document).ready(function () {
        	//$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.units').select2({
            	dropdownCssClass : 'cost_dropdown'
            });
            $('#work_name').characterCounter();
            $('#remarks').characterCounter();
            // $(".datepicker").datepicker();          
            $('#sanctioned_year_icon').click(function () {
                event.stopPropagation();
                $('#sanctioned_year').click();
            });
            $('#year_completed_icon').click(function () {
                event.stopPropagation();
                $('#year_completed').click();
            });
            $('#projected_completion_icon').click(function () {
                event.stopPropagation();
                $('#projected_completion').click();
            });
            
            $('#projected_completion').datepicker({
	        	 format: 'dd-mm-yyyy',
	        	 autoClose:true,
	        	 minDate: new Date(),
	        });
            
            $('#projected_completion_date_icon').click(function () {
                event.stopPropagation();
                $('#projected_completion_date').click();
            });
            
            $('#projected_completion_date').datepicker({
	        	 format: 'dd-mm-yyyy',
	        	 autoClose:true
	        });
            var work_status_fk = '${workDetails.work_status_fk}';

      		getWorkStatusList(work_status_fk);
          
            
        });
        
  //*********************VALIDATION FOR WORK ADD/EDIT FORMS*************************************      
        
        function addWork(){
        	var flag = validateContract();
        	if(flag){
		  		if(validator.form()){ // validation perform
		  			$(".page-loader").show();	    
		  			var sanctioned_estimated_cost = $('#sanctioned_estimated_cost').val();
		  			var sanctioned_completion_cost = $('#sanctioned_completion_cost').val();
		  			var anticipated_cost = $('#anticipated_cost').val();
		  			var completion_cost = $('#completion_cost').val();
		  			if(sanctioned_estimated_cost == ""){$('#sanctioned_estimated_cost_unit').val("");}
		  			if(sanctioned_completion_cost == ""){$('#sanctioned_completion_cost_unit').val("");}
		  			if(anticipated_cost == ""){$('#anticipated_cost_unit').val("");}
		  			if(completion_cost == ""){$('#completion_cost_unit').val("");}
		  			
		  			var project_name = $( "#project_id_fk option:selected" ).text();
		  			$("#project_name").val(project_name);
		  			$('form input[name=financial_years]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			//$('form input[name=pink_book_item_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=latest_revised_costs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=latest_revised_cost_unit]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=year_of_revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=revision_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			document.getElementById("workForm").submit();			
	    	 	}
        	}
    	}
  
        function updateWork(){
        	var flag = validateWork();
        	if(flag){
		  		if(validator.form()){ // validation perform
		  			$(".page-loader").show();	   
		  			var sanctioned_estimated_cost = $('#sanctioned_estimated_cost').val();
		  			var sanctioned_completion_cost = $('#sanctioned_completion_cost').val();
		  			var anticipated_cost = $('#anticipated_cost').val();
		  			var completion_cost = $('#completion_cost').val();
		  			if(sanctioned_estimated_cost == ""){$('#sanctioned_estimated_cost_unit').val("");}
		  			if(sanctioned_completion_cost == ""){$('#sanctioned_completion_cost_unit').val("");}
		  			if(anticipated_cost == ""){$('#anticipated_cost_unit').val("");}
		  			if(completion_cost == ""){$('#completion_cost_unit').val("");}
		  			
		  			$('form input[name=financial_years]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			//$('form input[name=pink_book_item_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=latest_revised_costs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=year_of_revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=latest_revised_cost_unit]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			$('form input[name=revision_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
		  			document.getElementById("workForm").submit();			
	    	 	}
        	}
    	}
        
        var validator = $('#workForm').validate({
		           errorClass: "my-error-class",
				   validClass: "my-valid-class",
				   ignore: ":hidden:not(.validate-dropdown)",
		  		    rules: {
		  		 		  "project_id_fk": {
		  		 			required: true
		  			 	  },"work_name": {
		  			 		required: true
		  			 	  },"work_short_name":{
		  			 		required: true
		  			 	  },"sanctioned_estimated_cost": {
		  			 		required: false
		  			 	  },"completeion_period_months": {
		  		 		    required: false
		  		 	   	  },"sanctioned_year": {
		  			 		required: false
		  			 	  },"sanctioned_completion_cost": {
		  			 		required: false
		  			 	  }	,"anticipated_cost": {
		  			 		required: false
		  			 	  }	,"year_of_completion": {
		  			 		required: false
		  			 	  }	,"projected_completion": {
		  			 		required: false
		  			 	  },"completion_cost": {
		  			 		required: false
		  			 	  }	,"railway_id_fk": {
		  			 		required: false
		  			 	  }	,"executed_by_id_fk": {
		  			 		required: false
		  			 	  }	,"remarks": {
		  			 		required: false
		  			 	  },"sanctioned_estimated_cost_unit":{
	        		 		 required: function(element){
	        		             return $("#sanctioned_estimated_cost").val()!="";
	        		         }
	        		 	  },"sanctioned_completion_cost_unit":{
	        		 		 required: function(element){
	        		             return $("#sanctioned_completion_cost").val()!="";
	        		         }
	        		 	  },"anticipated_cost_unit":{
	        		 		 required: function(element){
	        		             return $("#anticipated_cost").val()!="";
	        		         }
	        		 	  },"completion_cost_unit":{
	        		 		 required: function(element){
	        		             return $("#completion_cost").val()!="";
	        		         }
	        		 	  }		
		  		 	},
		  		    messages: {
		  		 		 "project_id_fk": {
		  		 			required: 'This Field Required'
		  		 	  	 },"work_name": {
		  				 	required: 'This Field Required',
		  			 	 },"work_short_name":{
		  			 		required: 'This Field Required',
		  			 	  },"sanctioned_estimated_cost": {
		  			 		required: ' This Field Required'
		  			 	 },"completeion_period_months": {
		  		 			required: ' This Field Required'
		  		 	  	 },"sanctioned_year": {
		  		 			required: ' This Field Required'
		  		 	  	 },"sanctioned_completion_cost": {
		  		 			required: ' This Field Required' 
		  		 	  	 },"anticipated_cost": {
		  		 			required: '  This Field Required'
		  		 	  	 },"year_of_completion": {
		  		 			required: ' This Field Required  '
		  		 	  	 },"projected_completion": {
		  		 	  		required: ' This Field Required  '
		  			 	 },"completion_cost": {
		  		 			required: ' This Field Required'
		  		 	  	 },"railway_id_fk": {
		  		 			required: ' This Field Required'
		  		 	  	 },"executed_by_id_fk": {
		  		 			required: 'This Field Required'
		  		 		 },"remarks": {
		  		 			required: 'This Field Required'
		  		 	  	 },"sanctioned_estimated_cost_unit": {
		  		 			required: 'Required'
		  		 	  	 },"sanctioned_completion_cost_unit": {
		  		 			required: 'Required'
		  		 	  	 },"anticipated_cost_unit": {
		  		 			required: 'Required'
		  		 		 },"completion_cost_unit": {
		  		 			required: 'Required'
		  		 	  	 }
			   		},
			   		errorPlacement:function(error, element){
			   		 	 if (element.attr("id") == "work_name" ){
		  		 		      document.getElementById("work_nameError").innerHTML="";
		  		 		  	  error.appendTo('#work_nameError');
			   			 }else if (element.attr("id") == "work_short_name" ){
		  		 		      document.getElementById("work_short_nameError").innerHTML="";
			  		 		  error.appendTo('#work_short_nameError');
				   		 }else if (element.attr("id") == "sanctioned_estimated_cost" ){
		  		 		      document.getElementById("sanctioned_estimated_costError").innerHTML="";
		  		 			  error.appendTo('#sanctioned_estimated_costError');
	  		 	   		 }else if (element.attr("id") == "project_id_fk" ){
	  						  document.getElementById("project_id_fkError").innerHTML="";
			  		 		  error.appendTo('#project_id_fkError');
		  		 	   	 }else if (element.attr("id") == "completeion_period_months" ){
			  		 		  document.getElementById("completeion_period_monthsError").innerHTML="";
			  		 		  error.appendTo('#completeion_period_monthsError');
		  		 	     }else if (element.attr("id") == "sanctioned_year" ){
				  		 	  document.getElementById("sanctioned_yearError").innerHTML="";
				  		 	  error.appendTo('#sanctioned_yearError');
			  		     }else if (element.attr("id") == "sanctioned_completion_cost" ){
					  		  document.getElementById("sanctioned_completion_costError").innerHTML="";
					  		  error.appendTo('#sanctioned_completion_costError');
				  		 }else if (element.attr("id") == "anticipated_cost" ){
						  	  document.getElementById("anticipated_costError").innerHTML="";
						  	  error.appendTo('#anticipated_costError');
					  	 }else if (element.attr("id") == "year_of_completion" ){
							  document.getElementById("year_of_completionError").innerHTML="";
							  error.appendTo('#year_of_completionError');
						 }else if (element.attr("id") == "projected_completion" ){
							  document.getElementById("projected_completionError").innerHTML="";
							  error.appendTo('#projected_completionError');
						 }else if (element.attr("id") == "completion_cost" ){
							  document.getElementById("completion_costError").innerHTML="";
							  error.appendTo('#completion_costError');
						 }else if (element.attr("id") == "railway_id_fk" ){
							  document.getElementById("railway_id_fkError").innerHTML="";
							  error.appendTo('#railway_id_fkError');
						 }else if (element.attr("id") == "executed_by_id_fk" ){
							  document.getElementById("executed_by_id_fkError").innerHTML="";
							  error.appendTo('#executed_by_id_fkError');
						 }else if (element.attr("id") == "remarks" ){
							  document.getElementById("remarksError").innerHTML="";
							  error.appendTo('#remarksError');
						 }else if (element.attr("id") == "sanctioned_estimated_cost_unit" ){
							  document.getElementById("sanctioned_estimated_cost_unitError").innerHTML="";
							  error.appendTo('#sanctioned_estimated_cost_unitError');
						 }else if (element.attr("id") == "sanctioned_completion_cost_unit" ){
							  document.getElementById("sanctioned_completion_cost_unitError").innerHTML="";
							  error.appendTo('#sanctioned_completion_cost_unitError');
						 }else if (element.attr("id") == "anticipated_cost_unit" ){
							  document.getElementById("anticipated_cost_unitError").innerHTML="";
							  error.appendTo('#anticipated_cost_unitError');
						 }else if (element.attr("id") == "completion_cost_unit" ){
							  document.getElementById("completion_cost_unitError").innerHTML="";
							  error.appendTo('#completion_cost_unitError');
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
        //**********************ADD NEW ROW TO REVISION TABLE *************************************************************************************
       


  	  function addRevisionRow(){
  		
        var rowNo = $("#RevrowNo").val();
        var rNo = Number(rowNo)+1;
        var total = 0;
       /*   $('select[name="financial_years"]').each(function(){
        	if($(this).val() != ''){
                $('.validate-dropdown').not(this).find('option[value="'+$(this).val()+'"]').remove();
        	} 
        });
         */
         var html = '<tr id="revisionRow'+rNo+'"><td data-head="Financial Year " class="input-field"> '
		   		   +'<select  name="financial_years" id="financial_years'+rNo+'"  class="validate-dropdown searchable" >'	   			
		   		   +'<option value="" >select</option>'
				     <c:forEach var="obj" items="${yearList}">
		     	      +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
				     </c:forEach>
		   		   +'</select></td>'
				   //+'<td><input  type="text" class="validate" id="pink_book_item_numbers'+rNo+'" name="pink_book_item_numbers" placeholder="PB Item Number"></td>'
				   //+'<td><input  type="number" class="validate" id="latest_revised_costs'+rNo+'" name="latest_revised_costs" placeholder="Latest Revised Cost" min="0.01" step="0.01"></td>'
				   +'<td data-head="Latest Revised Cost " class="input-field"> <input id="latest_revised_costs'+rNo+'" name="latest_revised_costs" type="number" class="validate" value=""'
				   +'placeholder="Latest Revised Cost" min="0.01" step="0.01"> </td> ' 
				   +'<td><select class="units validate-dropdown" id="latest_revised_cost_units'+rNo+'" name="latest_revised_cost_units">'
               	   +'<option value="">Select</option>'
		             <c:forEach var="obj" items="${unitsList }">
		      			   +'<option value="${obj.value }">${obj.unit }</option>'
		       		 </c:forEach>	              
		      	   +'</select><p id="units'+rNo+'Error" class="my-error" ></p></td>'
				   +'<td data-head="Year of Revision " class="input-field">'
				   +'<select id="year_of_revisions'+rNo+'" name="year_of_revisions" class="validate-dropdown searchable" >'
				   +'<option value="" selected>select</option>'
				     <c:forEach var="obj" items="${yearList}">
					   +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
				     </c:forEach>
				   +'</select></td>'
				   +'<td data-head="Revision No " class="input-field"><input  type="text" class="validate" id="revision_numbers'+rNo+'" name="revision_numbers" placeholder="Revision Number"></td>'
				   
			   	   +'<td class="mobile_btn_close"><a  class="btn waves-effect waves-light red t-c " onclick="removeRevision('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
			 
				 $('#revisionsTableBody').append(html);
				 $("#RevrowNo").val(rNo);
				// $('select').formSelect();
				 $('.searchable').select2();
				 $('.units').select2({dropdownCssClass : 'cost_dropdown'});
				 
				 $('#latest_revised_cost_units'+rNo).on('change', function(e){
	            	 if($.trim($('#latest_revised_costs'+rNo).val()) != ""){
	            		 $('#units'+rNo+'Error').text('');
	            	 }
	             });
		 //******************* Revision table Validation***************************************

		
		/*  var className = '.validate-dropdown';
		  $(className).focus(function () {
		                  oldValue = this.value;
		                  oldText = $(this).find('option:selected').text();
		              })
		                $(className).change(function () {
		                  var newSelectedValue = $(this).val();
		                  if (newSelectedValue != "") {
		                      $('.validate-dropdown').not(this).find('option[value="'+newSelectedValue+'"]').remove();
		                  }
		                  if ($(className).not(this).find('option[value="'+this.value+'"]').length == 0) { // NOT EXIST
		                      $(className).not(this).append('<option value='+this.value+'>' + $(this).find('option:selected').text() + '</option>');
		                  }
		                  $(this).blur();
		              }); */
     } 
        
     function removeRevision(rowNo){
    	//alert("#revisionRow"+rowNo);
    	$("#revisionRow"+rowNo).remove();
     }
     function removeMedia(link,id){
	   	  $('#'+id).val('');
	   	  $(link).prev().text('');
	   	  $(link).css('display','none');
     }       
     
     function validateWork(){
    	 var flag = true;
	     $("input[name=latest_revised_costs]").each(function(){
	    		var idNo = (this.id).replace('latest_revised_costs','');
	    		var latest_revised_cost_unit = $("#latest_revised_cost_units"+idNo).val();
	    		var latest_revised_costs = $("#latest_revised_costs"+idNo).val();
				if($.trim(latest_revised_costs) == ""){$("#latest_revised_cost_units"+idNo).val("");}
	    		if(idNo === ""){
	    				idNo = 0;
	    		}
	    		if( $.trim(latest_revised_cost_unit) == "" && $('#latest_revised_costs'+idNo).val() != ""){
					$('#units'+idNo+'Error').text('Requried');
					$('#latest_revised_cost_units'+idNo).slideDown(100,function(){
						$(this).focus();
					});
					flag = false;
				} 
			});
			return flag;
	}
     $('select[name=latest_revised_cost_units]').change(function(key, element){
			$("select[name=latest_revised_cost_units]").each(function(){
				var idNo = (this.id).replace('latest_revised_cost_units','');
	     		if($.trim(this.value) == "" && $('#latest_revised_costs'+idNo).val() != ""){
	     			$('#units'+idNo+'Error').text('Requried');
	     			
				}else{
					$('#units'+idNo+'Error').text('');
				}
            });
		});
	
    </script>
</body>

</html>