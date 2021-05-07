<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Project - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Project - Update Forms - PMIS</c:if>    
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">     
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/project.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<style>
		.my-error-class {
			color: red;
		}
		
		.my-valid-class {
			color: green;
		}
		.images-show{
			display: flex;
		    flex-wrap: wrap;
		    justify-content: flex-start;
		}
		.images-show img,.images-show video{
			width:100px;
			height:100px;
			margin:5px;
		}
		
		.input-field {
		    margin-top: .35rem;
		    margin-bottom: .35rem;
		}
		.input-field>textarea+label:not(.label-icon).active{
			margin-top: 8px;
		}         
		.images-show img:first-of-type{			
			margin-left:0;
		}
		.images-show img:last-of-type{
			margin-right:0;
		}
		.select2-container--default .select2-selection--single{
			background-color:transparent;
		}
		@media only screen and (max-width: 600px) {
		  .images-show{
		    justify-content: space-evenly;
		  }
		}
		.col.input-field>textarea+label:not(.label-icon).active{
		    margin-top:0;
		}
		.input-field p.searchable_label{
			margin-top: -10px !important;
			color :#8585ad;
		}
		.datepicker-table th,
        .datepicker-table td {
            padding: 0 !important;
        }
      
		 .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }
        .datepicker~button {
            top: 26px;
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
                                <c:if test="${action eq 'add'}">	
                               		<h6>Add Project</h6>
                               	 </c:if>
                               	 <c:if test="${action eq 'edit'}">	
                               		<h6>Edit Project</h6>
                               	 </c:if>
                            </div>
                        </span>
                    </div>
		            <div class="row clearfix" style="margin-bottom:0;">
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
				                	<form action="update-project" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                        </c:if>
			              
			                <c:if test="${action eq 'add'}">				                
			                	<form action="add-project" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
							</c:if> 
							
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                
                                <c:if test="${action eq 'edit'}">	
                                	<div class="col s12 m4 input-field">			                
	                                     <input id="project_id" type="text" class="form-control" name="project_id" value="${projectDetails.project_id }" readonly >   
	                               		 <label>Project ID :</label>
                               		 </div>
                               		 <div class="col s12 m4 input-field">
	                                    <input id=project_name type="text" class="validate" value="${projectDetails.project_name }" name="project_name">
	                                    <label for="project_name">Project Name <span class="required">*</span></label>
	                                    <span  id="project_nameError"> </span>
	                                </div>
                                </c:if>
                                <c:if test="${action ne 'edit'}">
	                                <div class="col s12 m8 input-field">
	                                    <input id=project_name type="text" class="validate" value="${projectDetails.project_name }" name="project_name">
	                                    <label for="project_name">Project Name <span class="required">*</span></label>
	                                    <span  id="project_nameError"> </span>
	                                </div>
                                </c:if>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <%-- <div class="col s12 m4 input-field">
                                    <input id="pink_book_item_number" type="text" class="validate" value="${projectDetails.pink_book_item_number }" name="pink_book_item_number">
                                    <label for="pink_book_item_number">PB Item No</label>
                                    <span  id="pink_book_item_numberError"> </span>
                                </div> --%>
                                <div class="col s12 m4 input-field">
									<!-- <p class="searchable_label">Project Status</p> -->
									<select class="validate-dropdown" name="project_status" id="project_status">
										<option value="">Select</option>
										<option value="Open" <c:if test="${projectDetails.project_status == 'Open'}">selected</c:if>>Open</option>
										<option value="Closed" <c:if test="${projectDetails.project_status == 'Closed'}">selected</c:if>>Closed</option>
									</select> 
									<label for="project_staus">Project Status<span class="required">*</span></label>
									<span id="project_statusError"></span>
								</div>
                                <div class="col s12 m4 input-field">
                                    <input id="plan_head_number" type="text" class="validate" value="${projectDetails.plan_head_number }" name="plan_head_number">
                                    <label for="plan_head_number">Plan Head Number<span class="required">*</span></label>
                                    <span  id="plan_head_numberError"> </span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
									<!-- <input id="financial_years" type="text" class="validate" name="financial_years" value="${projectDetails.financial_year_fk }"> -->
                                    <p class="searchable_label">Financial Year</p>
									<select  name="financial_years"  id="financial_years"  class="validate-dropdown searchable">
                   					 	<option value="" >Select</option>
                         			 	<c:forEach var="obj" items="${yearList}">
    					  				 	<option value="${obj.financial_year }"<c:if test="${projectDetails.financial_year_fk eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
                          			  	</c:forEach>
               					  	</select>
                                    <span  id="financial_yearsError"> </span>
								</div>
								 <div class="col s12 m1 input-field">
								 	 <p class="searchable_label">Railway</p>
									<select class="searchable validate-dropdown" id="railways" name="railways" >
										<option value="" >Select</option>	
										<option value="WR" <c:if test="${projectDetails.railway eq 'WR'}">selected</c:if>>WR</option>
						                <option value="CR" <c:if test="${projectDetails.railway eq 'CR'}">selected</c:if>>CR</option>
									</select>
								 </div>
                                <div class="col s12 m3 input-field">
                                    <input id="pink_book_item_numbers" type="number" class="validate" name="pink_book_item_numbers" value="${projectDetails.pb_item_no }" maxlength="4"/>
                                    <label for="pink_book_item_numbers" style="margin-top:5px">PB Item No </label>                                   
                                    <span  id="pink_book_item_numbersError"> </span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            

							<div class="row">
                              <div class="col m2 hide-on-small-only"></div>
                              <div class="col s12 m8 input-field">
                                  <textarea id="benefits"  name="benefits" class="materialize-textarea" data-length="1000" maxlength="1000">${projectDetails.benefits }</textarea>
                                  <label for="benefits">Benefits</label>
                                   <span id="benefitsError"></span>
                              </div>
	                        </div>
							<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" class="materialize-textarea"  maxlength="1000" data-length="1000"  name="remarks">${projectDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                     <span id="remarksError"></span>
                                </div>
                            </div>
							
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="row fixed-width"
										style="margin-bottom: 10px; margin-top: 20px">
										<div class="table-inside">
											<table class="mdl-data-table update-table">
												<thead>
													<tr>
														<th style="width: 52%;text-align: left;">Date</th>
														<th style="width: 52%;text-align: left;">Attach Image</th>
														<th></th>
														<th style="width: 8%;text-align: left;">Action</th>
													</tr>
												</thead>
												<tbody id="projectImageFilesBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectGalleryFilesList && fn:length(projectDetails.projectGalleryFilesList) gt 0 }">
															<c:forEach var="iObj" items="${projectDetails.projectGalleryFilesList }" varStatus="index">
																<tr id="imageRow${index.count }">
																
																	<td>
																		<div class="col m2 hide-on-small-only"></div>
																		<div class="input-field">
																			<input id="created_date${index.count }" name="created_dates" type="text" class="validate datepicker" value="${iObj.created_date }">
										                                    <button type="button" id="created_date${index.count }_icon"><i class="fa fa-calendar"></i></button>
										                                    <span id="dateError" class="error-msg" ></span>
										                                </div>
																	</td>
																	<td>
																		<div class="file-field input-field">
									                                        <div class="btn bg-m t-c">
									                                            <span>Attach Image</span>
									                                            <input type="file" id="projectGalleryFiles${index.count }" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" >
									                                        </div>
									                                        <div class="file-path-wrapper">
									                                            <input class="file-path validate" type="text" id="projectGalleryFileNames${index.count }" name="projectGalleryFileNames" value="${iObj.file_name }">
									                                        </div>                             
									                                    </div>
			                                                      	</td>
			                                                      	<td>
			                                                      		<a href="<%=CommonConstants2.PROJECT_GALLERY%>${iObj.project_id_fk }/${iObj.file_name }" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                      	</td>
																	<td>
																		<a onclick="removeImage('${index.count }');" class="btn red"> 
																			<i class="fa fa-close"></i></a>
																	</td>
																</tr>	
																	<script>
																	var date = '${iObj.created_date }'
																	  
																	
																	</script>														
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="imageRow0">
																<td>
																	<div class="col m2 hide-on-small-only"></div>
																	<div class="input-field">
																		<input id="created_date0" name="created_dates" type="text" class="validate datepicker">
									                                    <button type="button" id="created_date0_icon"><i class="fa fa-calendar"></i></button>
									                                    <span id="dateError" class="error-msg" ></span>
									                                </div>
																</td>
																<td>
																	<div class="file-field input-field">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach Image</span>
								                                            <input type="file" id="projectGalleryFiles0" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" >
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="projectGalleryFileNames0" name="projectGalleryFileNames">
								                                        </div>                                       
								                                    </div>
		                                                      	</td>
		                                                      	<td></td>
																<td>
																	<a onclick="removeImage('0');" class="btn red"> 
																		<i class="fa fa-close"></i></a>
																</td>
																
															</tr>
															<script>
																		var date = ''
															</script>
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
															onclick="addImageFileRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											
											<c:choose>
												<c:when test="${not empty (projectDetails.projectGalleryFilesList) && fn:length(projectDetails.projectGalleryFilesList) gt 0 }">
													<input type="hidden" id="imageRowNo" name="imageRowNo" value="${fn:length(projectDetails.projectGalleryFilesList) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="imageRowNo" name="imageRowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="row fixed-width"
										style="margin-bottom: 10px; margin-top: 20px">
										<div class="table-inside">
											<table class="mdl-data-table update-table">
												<thead>
													<tr>
														<th style="width: 30%;text-align: left;">File Type</th>
														<th style="width: 52%;text-align: left;">Attach File</th>
														<th></th>
														<th style="width: 8%;text-align: left;">Action</th>
													</tr>
												</thead>
												<tbody id="projectFilesBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectFilesList && fn:length(projectDetails.projectFilesList) gt 0 }">
															<c:forEach var="iObj" items="${projectDetails.projectFilesList }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td>
																		<div class="input-field">
																			<select  name="project_file_types"  id="project_file_types${index.count }"  class="validate-dropdown searchable">
							                                   					 <option value="" >Select</option>
							                                         			  <c:forEach var="obj" items="${projectFileTypes}">
							                    					  				 <option value="${obj.project_file_type }" <c:if test="${iObj.project_file_type_fk eq obj.project_file_type}">selected</c:if>>${obj.project_file_type}</option>
							                                          			  </c:forEach>
							                               					  </select>
																		</div>
																	</td>
																	<td>
																		<div class="file-field input-field">
									                                        <div class="btn bg-m t-c">
									                                            <span>Attach File</span>
									                                            <input type="file" id="projectFiles${index.count }" name="projectFiles">
									                                        </div>
									                                        <div class="file-path-wrapper">
									                                            <input class="file-path validate" type="text" id="projectFileNames${index.count }" name="projectFileNames" value="${iObj.attachment }">
									                                        </div>                             
									                                    </div>
			                                                      	</td>
			                                                      	<td>
			                                                      		<input type="hidden" id="project_file_ids${index.count }" name="project_file_ids" value="${iObj.project_file_id }"/>
			                                                      		<a href="<%=CommonConstants.PROJECT_FILES%>${iObj.project_id_fk }/${iObj.attachment }" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                      	</td>
																	<td>
																		<a onclick="removeActions('${index.count }');" class="btn red"> 
																			<i class="fa fa-close"></i></a>
																	</td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow0">
																<td>
																	<div class="input-field">
																		<select  name="project_file_types"  id="project_file_types0"  class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${projectFileTypes}">
						                    					  				 <option value="${obj.project_file_type }">${obj.project_file_type}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</div>
																</td>
																<td>
																	<div class="file-field input-field">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach File</span>
								                                            <input type="file" id="projectFiles0" name="projectFiles">
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="projectFileNames0" name="projectFileNames">
								                                        </div>                                       
								                                    </div>
		                                                      	</td>
		                                                      	<td><input type="hidden" id="project_file_ids0" name="project_file_ids"/></td>
																<td>
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
															onclick="addFileRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											
											<c:choose>
												<c:when test="${not empty (projectDetails.projectFilesList) && fn:length(projectDetails.projectFilesList) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo" value="${fn:length(projectDetails.projectFilesList) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							
						
							<c:if test="${action eq 'edit'}">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="row fixed-width"
										style="margin-bottom: 10px; margin-top: 20px">
										<div class="table-inside">
											<table id="project-table" class="mdl-data-table update-table">
												<thead>
													<tr>
														<th  style=" width: 30%;">Financial Year</th>
														<th style="width:30%">Railway</th> 
														<!-- <th>WR%</th>
														<th>CR</th> -->
														<th style=" width: 130px;">PB Item No</th>
														<th style="width:8%">Action</th>
													</tr>
												</thead>
												<tbody id="pinkBookBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectPinkBooks && fn:length(projectDetails.projectPinkBooks) gt 0 }">
															<c:forEach var="pObj" items="${projectDetails.projectPinkBooks }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td>
																		<div class="input-field">
																			<select  name="financial_years"  id="financial_years${index.count }"  class="validate-dropdown searchable">
							                                   					 <option value="" >Select</option>
							                                         			  <c:forEach var="obj" items="${yearList}">
							                    					  				 <option value="${obj.financial_year }" <c:if test="${pObj.financial_year_fk eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
							                                          			  </c:forEach>
							                               					  </select>
																		</div>
																	</td>
																	<td><div class="input-field">
																		<select  name="railways"  id="railways${index.count }"  class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>							                                         			  
						                    					  			 <option value="WR" <c:if test="${pObj.railway eq 'WR'}">selected</c:if>>WR</option>
						                    					  			 <option value="CR" <c:if test="${pObj.railway eq 'CR'}">selected</c:if>>CR</option>							                                       
							                               				</select>
							                               				</div>
																	</td>
																	<td>
																		<input id="pink_book_item_numbers${index.count }" name="pink_book_item_numbers" type="number" class="validate" maxlength="4"  
	                                                        				placeholder="PB Item No" value="${pObj.pb_item_no }" autocomplete="off">
	                                                        		</td>
																	<td>
																		<a onclick="removeActions('${index.count }');" class="btn red"> 
																			<i class="fa fa-close"></i></a>
																	</td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow0">
																<td>
																	<div class="input-field">
																		<select  name="financial_years" id="financial_years0" class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${yearList}">
						                    					  				 <option value="${obj.financial_year }">${obj.financial_year}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</div>
																</td>
																<td><div class="input-field">
																		<select  name="railways"  id="railways0"  class="validate-dropdown searchable">
							                                   					  <option value="">Select</option>							                                         			 
							                    					  				 <option value="WR">WR</option>
							                    					  				 <option value="CR">CR</option>							                                          		
							                               					  </select>
							                               				</div>
																	</td>																	
																<td>
																	<input id="pink_book_item_numbers0" name="pink_book_item_numbers" type="number" class="validate"  maxlength="4"
	                                                        				placeholder="PB Item No" autocomplete="off">
																</td>
																<td>
																	<a onclick="removeActions('0');" class="btn red"> <i class="fa fa-close"></i></a>
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
															onclick="addPinkBookRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											<c:choose>
												<c:when
													test="${not empty (projectDetails.projectPinkBooks) && fn:length(projectDetails.projectPinkBooks) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo"
														value="${fn:length(projectDetails.projectPinkBooks) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							</c:if>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                     <c:if test="${action eq 'edit'}">
 											<button onclick="updateProject();" class="btn waves-effect waves-light bg-m">Update</button>    
 									 </c:if>
                                         <c:if test="${action eq 'add'}">
 											<button onclick="addProject();" class="btn waves-effect waves-light bg-m">Add</button>    
 									 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/project" class="btn waves-effect waves-light bg-s">Cancel</a>
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


    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>

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
 

    <script type="text/javascript">

	/****************************************************************************************************/
    let date_pickers = document.querySelectorAll('.datepicker');
        $.each(date_pickers, function(){
        	var dt = this.value.split(/[^0-9]/);
        	this.value = ""; 
        	var options = {maxDate: new Date(),format: 'dd-mm-yyyy',autoClose:true};
        	if(dt.length > 1){
        		options.setDefaultDate = true,
        		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
        	}
        	M.Datepicker.init(this, options);
        });
     function addImageFileRow(){
			var rowNo = $("#imageRowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="imageRow' + rNo + '">'	
	        +' <td><div class="col m2 hide-on-small-only"></div>'
				+'<div class="input-field">'
				+'<input id="created_date'+rNo+'" name="created_dates" type="text" class="validate datepicker">'
				+'<button type="button" id="created_date'+rNo+'_icon"><i class="fa fa-calendar"></i></button>'
	            +'<span id="dateError" class="error-msg" ></span>'
	            +' </div></td>'
	   		   +'<td><div class="file-field input-field">'	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Image</span>'	
			   +'<input type="file" id="projectGalleryFiles'+rNo+'" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectGalleryFileNames'+rNo+'" name="projectGalleryFileNames">'	
			   +'</div>'	               
			   +'</div></td>'
			   +'<td></td>'
			   +'<td><a onclick="removeImage(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			  
			$('#projectImageFilesBody').append(html);
	        $("#imageRowNo").val(rNo); 
	       
	    		var id = 'created_date'+rNo
				$('#'+id).datepicker({
					maxDate: new Date(),
		        	format:'dd-mm-yyyy',
		   	    	onSelect: function () {
		   	    	   $('.confirmation-btns .datepicker-done').click();
		   	    	}
		        }).datepicker("setDate", new Date());
				
		        $('#'+id+'_icon').click(function () {
	             event.stopPropagation();
	             $('#'+id).click();
	         });
	    	
	    }
    	
	    function removeImage(rowNo){
	    	$("#imageRow"+rowNo).remove();
	    }
    	
    /**************************************************************************************************************/
    
		function addFileRow(){
			var rowNo = $("#rowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="actionRow' + rNo + '">'
	           +'<td> <div class="input-field">'
	           +'<select name="project_file_types" id="project_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${projectFileTypes}">
	     	      +'<option value="${obj.project_file_type }">${obj.project_file_type}</option>'
			     </c:forEach>
	   		   +'</select></div></td>'	   		  			
			   
	   		   +'<td><div class="file-field input-field">'	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Files</span>'	
			   +'<input type="file" id="projectFiles'+rNo+'" name="projectFiles">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectFileNames'+rNo+'" name="projectFileNames">'	
			   +'</div>'	               
			   +'</div></td>'
			   +'<td><input type="hidden" id="project_file_ids'+rNo+'" name="project_file_ids"/></td>'
			   +'<td><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			$('#projectFilesBody').append(html);
	        $("#rowNo").val(rNo);          	
	        
	        $('select:not(.searchable)').formSelect();
	        $('.searchable').select2();
	    }
	    
	    function removeActions(rowNo){
	    	$("#actionRow"+rowNo).remove();
	    }


	/****************************************************************************************************/

        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks,#benefits').characterCounter(); 
           	var dateVal=  date;
           	if(dateVal == ''){
           	  $(".datepicker").each(function(){
             		var id = $(this).attr('id');
  				$('#'+id).datepicker({
  					maxDate: new Date(),
  		        	format:'dd-mm-yyyy',
  		   	    	onSelect: function () {
  		   	    	   $('.confirmation-btns .datepicker-done').click();
  		   	    	}
  		        }).datepicker("setDate", new Date());
  				
  		        $('#'+id+'_icon').click(function () {
  	                event.stopPropagation();
  	                $('#'+id).click();
  	            });
             	});
           	}
          
        });
        
        function addProject(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	
	  			$('form input[name=financial_years]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=railways]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=pink_book_item_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$("#projectForm").submit();		
    	 	}
    	}
  
        function updateProject(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	   
	  			$('form input[name=financial_years]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=railways]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=pink_book_item_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
    			$("#projectForm").submit();			
    	 	}
    	}
        
        
        function addPinkBookRow() {        	
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="actionRow' + rNo + '">'
            +'<td> <div class="input-field">'
            +'<select name="financial_years" id="financial_years'+rNo+'"  class="validate-dropdown searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${yearList}">
	     	      +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
			     </c:forEach>
	   		   +'</select></div></td>'
	   		   +'<td><div class="input-field">'
	   		   +'<select  name="railways"  id="railways'+rNo+'"  class="validate-dropdown searchable">'
   					+'<option >Select</option>'         			
		  				+' <option value="WR">WR</option>'     
		  				+' <option value="CR">CR</option>' 
					+'  </select>	  </div>		</td>'				
			   +'<td><input  type="number" class="validate" id="pink_book_item_numbers'+rNo+'" name="pink_book_item_numbers" placeholder="PB Item No" maxlength="4""></td>'
			+'<td><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td></tr>';
		
			$('#pinkBookBody').append(html);
            $("#rowNo").val(rNo);          	
            
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        }
        
        function removeActions(rowNo){
        	$("#actionRow"+rowNo).remove();
        }
	            
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
   
        var validator =	$('#projectForm').validate({
				 errorClass: "my-error-class",
				   validClass: "my-valid-class",
				 ignore: ":hidden:not(.validate-dropdown)",
		  		    rules: {
		  		 		  "project_name": {
		  			 		required: true
		  			 	  },"project_status":{
		  			 		required: true
		  			 	  },"plan_head_number": {
		  			 		required: true
		  			 	  },"remarks": {
		  			 		required: false
		  			 	  }		
		  		 	},
		  		    messages: {
		  		 		 "project_name": {
		  				 	required: 'This field is required',
		  			 	  },"project_status":{
		  			 		required: ' This field is required'
		  			 	  },"plan_head_number": {
		  			 		required: ' This field is required'
		  			 	  },"remarks": {
		  		 			required: ' This field is required'
		  		 	  	 }
			   		},
			   		errorPlacement:function(error, element){
			   		 	if (element.attr("id") == "project_name" ){
							 document.getElementById("project_nameError").innerHTML="";
					 		 error.appendTo('#project_nameError');
						}else if(element.attr("id") == "project_status" ){
							   document.getElementById("project_statusError").innerHTML="";
						 	   error.appendTo('#project_statusError');
						}else if(element.attr("id") == "plan_head_number" ){
							   document.getElementById("plan_head_numberError").innerHTML="";
						 	   error.appendTo('#plan_head_numberError');
						}else if(element.attr("id") == "remarks" ){
						 		 document.getElementById("remarksError").innerHTML="";
				 				 error.appendTo('#remarksError');
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

    </script>
</body>

</html>