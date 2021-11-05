<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
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
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/project.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    
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
		
		.w7em{width: 7em;}
		
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.5em;
   					 margin-left: 11%;}
   		.bd-none{border: none;}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:18%;}
    	}
    	@media(max-width: 800px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
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
            border-bottom: none !important;
        }
        .mobile_responsible_table .datepicker~button {
            top: 26px;
        }        
        
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
		@media  only screen and (min-width: 769px)  {
			/*  #created_date0_icon{
			 	top:30px;
			 	right:20px;
			 } */
			 .md-pt-2px{
			 	padding-top:2px !important;
			 }
			 .md-mt-0{
			 	margin-top:0 !important;
			 }
		}
		
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			.mobile_responsible_table tbody tr .input-field .datepicker~button {
			    position: relative;
			    top: 0;
			    right: 26px;
			}			
			.mobile_responsible_table tbody tr td .select2-container {
			    width: 100% !important;
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row) >td::before {
			    vertical-align: middle;
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
			.mb-md-20	{
				margin-bottom:0 !important;
			}				
			.fs-sm-8rem{
				font-size:.8rem !important;
			}	
			.input-field>.datepicker~button{
				top:10px;
				right:6px;
			}			
			.mt-md-0{
				margin-top: -14px !important;
			} 
			.mobile_responsible_table>tbody tr:not(.datepicker-row):not(.mobile_hide_row) {
			}
			.mobile_responsible_table>tbody >tr:not(.datepicker-row)> td> div.btn{
				float:none;
				position:relative;
			}
			td.cell-disp-inb .file-path-wrapper {
			    visibility: visible;
			    width: 200%;
			    display: block !important;
			}
		}
			.input-field>input[type='text'].datepicker ~ label:not(.label-icon).active{
				-webkit-transform: translateY(-20px) scale(0.95);
			    transform: translateY(-20px) scale(0.95);
			}	
		@media(max-width: 575px){
		.row .col{margin: 6px auto !important;}
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
                               		<h6>Edit Project (${projectDetails.project_id })</h6>
                               	 </c:if>
                            </div>
                        </span>
                    </div>
		            <div class="row clearfix" style="margin-bottom:0;">
		                <div class="col s12 m12">
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
                               
                               
                            </div>

                            <div class="row">
                                <%-- <div class="col s12 m4 input-field">
                                    <input id="pink_book_item_number" type="text" class="validate" value="${projectDetails.pink_book_item_number }" name="pink_book_item_number">
                                    <label for="pink_book_item_number">PB Item No</label>
                                    <span  id="pink_book_item_numberError"> </span>
                                </div> --%>
                                 <c:if test="${action eq 'edit'}">	
                                  <input id="project_id" type="hidden" class="form-control" name="project_id" value="${projectDetails.project_id }" >   
                                <!--	<div class="col s6 m4 l4 input-field offset-m2">			                
	                                     <input id="project_id" type="text" class="form-control" name="project_id" value="${projectDetails.project_id }" readonly >   
	                               		 <label>Project ID :</label>
                               		 </div> --!>
                               		 <div class="col s6 m4 l4 input-field">
	                                    <input id=project_name type="text" class="validate" value="${projectDetails.project_name }" name="project_name">
	                                    <label for="project_name">Project Name <span class="required">*</span></label>
	                                    <span  id="project_nameError"> </span>
	                                </div>
                                </c:if>
                                <c:if test="${action ne 'edit'}">
	                                <div class="col s6 m4 l4 input-field">
	                                    <input id=project_name type="text" class="validate" value="${projectDetails.project_name }" name="project_name">
	                                    <label for="project_name">Project Name <span class="required">*</span></label>
	                                    <span  id="project_nameError"> </span>
	                                </div>
                                </c:if>
                                <div class="col s6 m4 l4 input-field">
<!-- 									<label for="project_staus">Project Status<span class="required">*</span></label> -->
									 <p class="searchable_label">Project Status <span class="required">*</span></p>
									<select class="validate-dropdown searchable" name="project_status" id="project_status">
										<option value="">Select</option>
										<option value="Open" <c:if test="${projectDetails.project_status == 'Open'}">selected</c:if>>Open</option>
										<option value="Closed" <c:if test="${projectDetails.project_status == 'Closed'}">selected</c:if>>Closed</option>
									</select> 
									<span id="project_statusError"></span>
								</div>
                                <div class="col s12 m4 l4 input-field md-pt-2px">
                                    <input id="plan_head_number" type="text" class="validate" value="${projectDetails.plan_head_number }" name="plan_head_number">
                                    <label for="plan_head_number" class='fs-sm-8rem'>Plan Head Number<span class="required">*</span></label>
                                    <span  id="plan_head_numberError"> </span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s6 m3 l4 input-field offset-m2">
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
								 <div class="col s6 m2 l4 input-field">
								 	 <p class="searchable_label">Railway</p>
									<select class="searchable validate-dropdown" id="railways" name="railways" >
										<option value="" >Select</option>	
										<option value="WR" <c:if test="${projectDetails.railway eq 'WR'}">selected</c:if>>WR</option>
						                <option value="CR" <c:if test="${projectDetails.railway eq 'CR'}">selected</c:if>>CR</option>
									</select>
								 </div>
                                <div class="col s6 m3 l4 input-field md-mt-0">
                                    <input id="pink_book_item_numbers" class="validate" type="text" name="pink_book_item_numbers" value="${projectDetails.pb_item_no }" maxlength="15"/>
                                    <label for="pink_book_item_numbers">PB Item No </label>                                   
                                    <span  id="pink_book_item_numbersError"> </span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            

							<div class="row">
                              <div class="col s12 m6 l6 input-field">
                                  <textarea id="benefits"  name="benefits" class="pmis-textarea" data-length="1000" maxlength="1000">${projectDetails.benefits }</textarea>
                                  <label for="benefits">Benefits</label>
                                   <span id="benefitsError"></span>
                              </div>
                              <div class="col s12 m6 l6 input-field">
                                    <textarea id="remarks" class="pmis-textarea"  maxlength="1000" data-length="1000"  name="remarks">${projectDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                     <span id="remarksError"></span>
                                </div>
	                        </div>
							<div class="row">
                                
                                
                            </div>
							
							<div class="row">
								<div class="col l12 m8 s12 offset-m2">
									<div class="row fixed-width">
										<!-- <div class="table-inside"> -->
											<table class="mdl-data-table mobile_responsible_table">
												<thead>
													<tr>
														<!-- <th style="width: 52%;text-align: left;">Date</th>
														<th style="width: 52%;text-align: left;">Attach Image</th>
														<th></th>
														<th style="width: 8%;text-align: left;">Action</th>
														 -->
														<th>Date</th>
														<th>Attach Image</th>
														<th></th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="projectImageFilesBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectGalleryFilesList && fn:length(projectDetails.projectGalleryFilesList) gt 0 }">
															<c:forEach var="iObj" items="${projectDetails.projectGalleryFilesList }" varStatus="index">
																<tr id="imageRow${index.count }">
																
																	<td data-head="Data" class="input-field">
																			<input id="created_date${index.count }" name="created_dates" type="text" class="validate datepicker" value="${iObj.created_date }">
										                                    <button type="button" id="created_date${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
										                                    <span id="dateError" class="error-msg" ></span>
																	</td>
																	<td data-head="Attach Image" class="cell-disp-inb input-field file-field">
									                                        <div class="btn bg-m t-c">
									                                            <span>Attach Image</span>
									                                            <input type="file" id="projectGalleryFiles${index.count }" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" onchange="validateImage('${index.count }')">
									                                        </div>
									                                        <div class="file-path-wrapper">
									                                            <input class="file-path validate" type="text" id="projectGalleryFileNames${index.count }" name="projectGalleryFileNames" value="${iObj.file_name }">
									                                        </div>          
			                                                      	</td>
			                                                      	<td>
			                                                      		<a href="<%=CommonConstants2.PROJECT_GALLERY%>${iObj.project_id_fk }/${iObj.file_name }" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                      	</td>
																	<td class="mobile_btn_close">
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
																<td data-head="Data" class="input-field">
																		<input id="created_date0" name="created_dates" type="text" class="validate datepicker" placeholder="date">
									                                    <button type="button" id="created_date0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
									                                    <span id="dateError" class="error-msg" ></span>
																</td>
																<td data-head="Attach Image" class="cell-disp-inb input-field file-field">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach Image</span>
								                                            <input type="file" id="projectGalleryFiles0" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" >
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="projectGalleryFileNames0" name="projectGalleryFileNames">
								                                        </div>
		                                                      	</td>
		                                                      	<td></td>
																<td class="mobile_btn_close">
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
											<table class="mdl-data-table table-add">
												<tbody>
													<tr class="bd-none">
														<td colspan="6"  ><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c add-align"
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
										<!-- </div> -->
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col l12 m8 s12 offset-m2">
									<div class="row fixed-width">
										<div class="table-inside">
											<table class="mdl-data-table update-table mobile_responsible_table">
												<thead>
													<tr>
													<!-- 	<th style="width: 30%;text-align: left;">File Type</th>
														<th style="width: 52%;text-align: left;">Attach File</th>
														<th></th>
														<th style="width: 8%;text-align: left;">Action</th> -->
														
														<th>File Type</th>
														<th>Attach File</th>
														<th></th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="projectFilesBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectFilesList && fn:length(projectDetails.projectFilesList) gt 0 }">
															<c:forEach var="iObj" items="${projectDetails.projectFilesList }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td data-head="File Type" class="input-field">
																		<select  name="project_file_types"  id="project_file_types${index.count }"  class="validate-dropdown searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${projectFileTypes}">
						                    					  				 <option value="${obj.project_file_type }" <c:if test="${iObj.project_file_type_fk eq obj.project_file_type}">selected</c:if>>${obj.project_file_type}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</td>
																	<td data-head="Attach File" class="file-field input-field cell-disp-inb">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach File</span>
								                                            <input type="file" id="projectFiles${index.count }" name="projectFiles">
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="projectFileNames${index.count }" name="projectFileNames" value="${iObj.attachment }">
								                                        </div>                             
			                                                      	</td>
			                                                      	<td>
			                                                      		<input type="hidden" id="project_file_ids${index.count }" name="project_file_ids" value="${iObj.project_file_id }"/>
			                                                      		<a href="<%=CommonConstants.PROJECT_FILES%>${iObj.project_id_fk }/${iObj.attachment }" class="filevalue" download><i class="fa fa-arrow-down"></i></a>
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
																	<select  name="project_file_types"  id="project_file_types0"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${projectFileTypes}">
					                    					  				 <option value="${obj.project_file_type }">${obj.project_file_type}</option>
					                                          			  </c:forEach>
					                               					  </select>
																</td>
																<td data-head="Attach File" class="file-field input-field cell-disp-inb">
								                                        <div class="btn bg-m t-c">
								                                            <span>Attach File</span>
								                                            <input type="file" id="projectFiles0" name="projectFiles">
								                                        </div>
								                                        <div class="file-path-wrapper">
								                                            <input class="file-path validate" type="text" id="projectFileNames0" name="projectFileNames">
								                                        </div>                                       
		                                                      	</td>
		                                                      	<td><input type="hidden" id="project_file_ids0" name="project_file_ids"/></td>
																<td class="mobile_btn_close">
																	<a onclick="removeActions('0');" class="btn red"> 
																		<i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
													
												</tbody>
											</table>
											<table class="mdl-data-table table-add">
												<tbody>
													<tr  class="bd-none">
														<td colspan="6"  ><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c  add-align"
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
								<div class="col l12 m8 s12 offset-m2">
									<div class="row fixed-width">
										<div class="table-inside">
											<table id="project-table" class="mdl-data-table update-table mobile_responsible_table">
												<thead>
													<tr>
													<!-- 	<th  style=" width: 30%;">Financial Year</th>
														<th style="width:30%">Railway</th> 
														<th>WR%</th>
														<th>CR</th>
														<th style=" width: 130px;">PB Item No</th>
														<th style="width:8%">Action</th>
														 -->
														<th>Financial Year</th>
														<th>Railway</th> 												
														<th >PB Item No</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="pinkBookBody">
													<c:choose>
														<c:when	test="${not empty projectDetails.projectPinkBooks && fn:length(projectDetails.projectPinkBooks) gt 0 }">
															<c:forEach var="pObj" items="${projectDetails.projectPinkBooks }" varStatus="index">
																<tr id="actionRow${index.count }">
																	<td data-head="Financial Year" class="input-field">
																		<select  name="financial_years"  id="financial_years${index.count }"  class="select searchable">
						                                   					 <option value="" >Select</option>
						                                         			  <c:forEach var="obj" items="${yearList}">
						                    					  				 <option value="${obj.financial_year }" <c:if test="${pObj.financial_year_fk eq obj.financial_year}">selected</c:if>>${obj.financial_year}</option>
						                                          			  </c:forEach>
						                               					  </select>
																	</td>
																	<td data-head="Railway" class="input-field">
																		<select  name="railways"  id="railways${index.count }"  class="select searchable">
						                                   					 <option value="" >Select</option>							                                         			  
						                    					  			 <option value="WR" <c:if test="${pObj.railway eq 'WR'}">selected</c:if>>WR</option>
						                    					  			 <option value="CR" <c:if test="${pObj.railway eq 'CR'}">selected</c:if>>CR</option>							                                       
							                               				</select>
																	</td>
																	<td data-head="PB Item No" class="input-field">
																		<input id="pink_book_item_numbers${index.count }" name="pink_book_item_numbers" type="text" class="validate" maxlength="15"  
	                                                        				placeholder="PB Item No" value="${pObj.pb_item_no }" autocomplete="off">
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
																<td data-head="Financial Year" class="input-field">
																	<select  name="financial_years" id="financial_years0" class="select searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${yearList}">
					                    					  				 <option value="${obj.financial_year }">${obj.financial_year}</option>
					                                          			  </c:forEach>
					                               					  </select>
																</td>
																<td data-head="Railway" class="input-field">
																	<select  name="railways"  id="railways0"  class="select searchable">
				                                   					    <option value="">Select</option>							                                         			 
				                    					  				<option value="WR">WR</option>
				                    					  				<option value="CR">CR</option>							                                          		
						                               				</select>
																</td>																	
																<td data-head="PB Item No" class="input-field">
																	<input id="pink_book_item_numbers0" name="pink_book_item_numbers" type="text" class="validate"  maxlength="15"
	                                                        				placeholder="PB Item No" autocomplete="off">
																</td>
																<td class="mobile_btn_close">
																	<a onclick="removeActions('0');" class="btn red"> <i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
												</tbody>
											</table>
											<table class="mdl-data-table table-add">
												<tbody>
													<tr>
														<td colspan="6"  ><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c add-align"
															onclick="addPinkBookRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											<c:choose>
												<c:when
													test="${not empty (projectDetails.projectPinkBooks) && fn:length(projectDetails.projectPinkBooks) gt 0 }">
													<input type="hidden" id="PBRowNo" name="rowNo"
														value="${fn:length(projectDetails.projectPinkBooks) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="PBRowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							</c:if>

                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr offset-m2 center-align">
                                    <div class=" m-1">
                                     <c:if test="${action eq 'edit'}">
 											<button onclick="updateProject();" class="btn waves-effect waves-light bg-m w7em">Update</button>    
 									 </c:if>
                                         <c:if test="${action eq 'add'}">
 											<button onclick="addProject();" class="btn waves-effect waves-light bg-m w7em">Add</button>    
 									 </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr center-align">
                                    <div class=" m-1">
                                        <a href="<%=request.getContextPath()%>/project" class="btn waves-effect waves-light bg-s w7em">Cancel</a>
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
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
 

    <script type="text/javascript">

	/****************************************************************************************************/
/*     let date_pickers = document.querySelectorAll('.datepicker');
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
         */
        function validateImage(RowNum) 
        {
            var formData = new FormData();
            var file = document.getElementById("projectGalleryFiles"+RowNum).files[0];
            formData.append("Filedata", file);
            var t = file.type.split('/').pop().toLowerCase();
            
            if (t != "jpeg" && t != "jpg" && t != "png" && t != "bmp" && t != "gif") {
                alert('Please select a valid image file');
                document.getElementById("projectGalleryFiles"+RowNum).value = '';
                return false;
            }
            return true;
        }
        
        
     function addImageFileRow(){
			var rowNo = $("#imageRowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="imageRow' + rNo + '">'	
	        +' <td data-head="Data" class="input-field">'
				+'<input id="created_date'+rNo+'" name="created_dates" type="text" class="validate datepicker" placeholder="date">'
				+'<button type="button" id="created_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
	            +'<span id="dateError" class="error-msg" ></span></td>'
	   		   +'<td data-head="Attach Image" class="cell-disp-inb input-field file-field"> '	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Image</span>'	
			   +'<input type="file" id="projectGalleryFiles'+rNo+'" name="projectGalleryFiles" accept="image/x-png,image/gif,image/jpeg" onchange="validateImage('+rNo+')">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectGalleryFileNames'+rNo+'" name="projectGalleryFileNames">'	
			   +'</div></td>'
			   +'<td class=" input-field"></td>'
			   +'<td class="mobile_btn_close"><a onclick="removeImage(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			  
			$('#projectImageFilesBody').append(html);
	        $("#imageRowNo").val(rNo); 
	       
	    	 	var id = 'created_date'+rNo
				$('#'+id).datepicker({
					maxDate: new Date(),
		        	format:'dd-mm-yyyy',
		        	onOpen: datePickerSelectAddClass,
		        	autoClose: true		   	    	
		        }).datepicker("setDate", new Date());
				
	    	
	    }
    	
	    function removeImage(rowNo){
	    	$("#imageRow"+rowNo).remove();
	    }
    	
    /**************************************************************************************************************/
    
		function addFileRow(){
			var rowNo = $("#rowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="actionRow' + rNo + '">'
	           +'<td data-head="File Type" class="input-field" > '
	           +'<select name="project_file_types" id="project_file_types'+rNo+'"  class="validate-dropdown searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${projectFileTypes}">
	     	      +'<option value="${obj.project_file_type }">${obj.project_file_type}</option>'
			     </c:forEach>
	   		   +'</select></td>'	
	   		   +'<td data-head="Attach File" class="file-field input-field cell-disp-inb">'	
			   +'<div class="btn bg-m t-c">'	
			   +'<span>Attach Files</span>'	
			   +'<input type="file" id="projectFiles'+rNo+'" name="projectFiles">'	
			   +'</div>'	
			   +'<div class="file-path-wrapper">'	
			   +'<input class="file-path validate" type="text" id="projectFileNames'+rNo+'" name="projectFileNames">'	
			   +'</div></td>'
			   +'<td><input type="hidden" id="project_file_ids'+rNo+'" name="project_file_ids"/></td>'
			   +'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td>'
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
           	  /* $(".datepicker").each(function(){
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
             	}); */
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
            var rowNo = $("#PBRowNo").val();
            var rNo = Number(rowNo)+1;
            var html = '<tr id="actionRow' + rNo + '">'
            +'<td data-head="Financial Year" class="input-field">'
            +'<select name="financial_years" id="financial_years'+rNo+'"  class="select searchable" >'	   			
	   		   +'<option value="" >Select</option>'
			     <c:forEach var="obj" items="${yearList}">
	     	      +'<option value="${obj.financial_year }">${obj.financial_year}</option>'
			     </c:forEach>
	   		   +'</select></div></td>'
	   		   +'<td data-head="Railway" class="input-field">'
	   		   +'<select name="railways"  id="railways'+rNo+'"  class="select searchable">'
   					+'<option >Select</option>'         			
		  				+' <option value="WR">WR</option>'     
		  				+' <option value="CR">CR</option>' 
					+'  </select>	</td>'				
			   +'<td data-head="PB Item No" class="input-field"><input  class="validate" id="pink_book_item_numbers'+rNo+'" name="pink_book_item_numbers" type="text" placeholder="PB Item No" maxlength="15""></td>'
			+'<td class="mobile_btn_close"><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;" class="btn red"><i class="fa fa-close"></i></a></td></tr>';
		
			$('#pinkBookBody').append(html);
            $("#PBRowNo").val(rNo);          	
            
            //$('select:not(.searchable)').formSelect();
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