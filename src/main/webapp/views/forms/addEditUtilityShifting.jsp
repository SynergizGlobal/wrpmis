<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utility Shifting - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
	
    <style>
       
        .mdl-data-table{
        	border:1px solid #ccc;	
        }
  		.error-msg label{color:red!important;}   
		h6{
			margin:1rem 0;
			font-weight:600;
		}
		span.required {
		    font-size: inherit;
		}
       
		.input-field .searchable_label.mb-8 {
		    margin-bottom: 5px !important;
    		margin-top: -11px !important;
    	}
        .fixed-width {
            width: 100%;
			margin: auto !important;            
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td{
        	position:relative
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        .fs-9{
        	font-size:.9rem;
        }
        p.prio{
       	    margin-top: -10px !important;       	    
        }
        p.priokind{
        	font-weight:600;
        	margin-top: 10px !important;     
        }
        p.priokind >i{
        	margin-left:10px;
        	vertical-align:inherit;
        }
             
         
        .my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		td.center-align{
			text-align:center !important;
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
		.input-field p.mt-md-0{
			margin-top:-4px !important;
		}
		.lh{
			line-height:inherit;
		}
		.input-field.min4{
			min-height:4rem ;
		}
		.fs11px{font-size: 11px !important;}
		.filevalue {
            display: block;
            margin-top: 10px;
        }
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			 .filevalue {
			    width: 200%;
			    white-space: break-spaces;
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
			.input-field>textarea ~ label:not(.label-icon).active{		
				-webkit-transform: translateY(-26px) scale(0.95);
			    transform: translateY(-26px) scale(0.95);
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
			.lh{
				line-height:1.1rem;
			}	
			.mt-md-0{
				margin-top: -14px !important;
			} 
		}
		@media only screen and (max-width: 767px){
			.input-field.min4{
				min-height:1px ;
			}
		}	
			.input-field .select2-container--default {
			    margin-bottom: 0.5rem;
			}
			@media only screen and (min-width: 770px){
				.mdl-data-table	tr>td.input-field .select2-container--default {
				    margin-bottom: 0;
	    			margin-top: 8px;
				}
				.normal-btn{
			        display: block;
			        text-align: center;
			    }  
			    td[data-head="Attachment"] {
				    max-width: 30%;
				    width: 30% !important;
				}
				td[data-head="Progress Date"] {
				    max-width: 24%;
				    width: 24% !important;
				}
				.mdl-data-table .datepicker{
					margin-bottom:0 !important;
				}
				.mdl-data-table .datepicker~button{
					bottom:1rem;
				}
			}
			
		@media only screen and (max-width: 769px) and (min-width: 500px){
			  #revTable .select2-container{
		          max-width: inherit !important;
    			  width: 95% !important;
		      }
		}
		@media(max-width: 575px){
			.row .col{margin: 10px auto}
			p.priokind{margin-top: 0 !important;}
			.t-48{
			top:37px;}
		}
		.mobile_btn_close > .waves-effect.btn.red,
		.waves-effect.bg-m.t-c{
			z-index:0;
		}
		.filevalue {
            display: block;
            margin-top: 10px;
			font-size: .9rem;
        }
        .max-200{
        	max-width: 200px;
        	width:200px !important;
        }
        .right.mob-center{
			position:absolute;
			right:3rem;
		}
		@media only screen and (max-width: 769px){
			.right.mob-center{
		    	position:relative;
				right:inherit;
				float: none !important;
				display:block;
				margin-left:auto;
				margin-right:auto;
				margin-top:5px;
			}
			
			td[data-head="Status"]>p{
				display:inline-block;
			}
			.input-field .searchable_label.mb-8 {
				margin-top: -20px !important;
	    		margin-bottom: -4px !important;
    		}
		}
		.pos-rel{
			position:relative;
		}
		h6{
			margin:1rem 0;
			font-weight:600;
		}
		thead th{
			text-transform: capitalize;
		}
		input[type=number]::-webkit-inner-spin-button, 
		input[type=number]::-webkit-outer-spin-button { 
		  -webkit-appearance: none; 
		  margin: 0; 
		}
		#percapita_per_month{
			margin-top:.5rem;
		}
		@media only screen and (min-width: 769px){
			.max-80{
				max-width:80px;
				width:80px;
			}
		}
    </style>
</head>
<body>
 <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
                  
       <div class="row">
        <div class="col s12 m12" style="margin-bottom:3rem;">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                               	    <c:if test="${action eq 'edit'}">Update Utility Shifting</c:if>
									<c:if test="${action eq 'add'}"> Add Utility Shifting</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-randr" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-randr" id="RandRForm" name="RandRForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
						  						  
						    <div class="container container-no-margin">
                            <div class="row" style="margin-top:1rem;">
						    <c:if test="${action eq 'add'}">	
                                <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  >
                                         <option value="" >Select</option>
                                         <%-- <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> --%>	
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label"> Contract </p>
                                    <select class="searchable validate-dropdown" id="contract_fk" name="contract_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="contract_fkError" class="error-msg" ></span>
                                </div>
                            </c:if>
 							<c:if test="${action eq 'edit'}">		                             
	                                <div class="col s6 m4 l4 input-field ">
	                                    <input type="text" value="" readonly />
								    	<label for="project_id_fk">Project </label>
								    	<input type="hidden" name="project_id_fk" id="project_id_fk" value="" readonly />
								    </div> 
	                                <div class="col s6 m4 l4 input-field"> 
	                                    <input type="text" value="" readonly />
	                                	<label for="work_id_fk">Work </label>
	                                	<input type="hidden" name="work_id_fk" id="work_id_fk" value="" readonly />
	                                </div>
	                           		<div class="col s12 m4 l4 input-field"> 
	                                    <input type="text" value="" readonly />
	                                	<label for="contract_fk">Contract </label>
	                                	<input type="hidden" name="contract_fk" id="contract_fk" value="" readonly />
	                                </div>
                              </c:if>
							</div>
							
							<div class="row">
								 <div class="col s12 m4 input-field">
                                     <input id="identification_date" name="identification_date" type="text" class="validate datepicker">                                     
                                     <label for="identification_date">Identification Date</label>
	                                 <button type="button" id="identification_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="identification_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <input id="location_name" name="location_name" type="text" class="validate">                                     
                                     <label for="location_name">Location Name</label>
	                                 <span id="location_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="reference_number" name="reference_number" type="text" class="validate">                                     
                                     <label for="reference_number">Reference Number </label>
	                                 <span id="reference_numberError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
								 <div class="col s12 m4 input-field">
                                     <input id="chainage" name="chainage" type="text" class="validate">                                     
                                     <label for="chainage">Chainage</label>
	                                 <span id="chainageError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <input id="utility_description" name="utility_description" type="text" class="validate">                                     
                                     <label for="utility_description">Utility Description</label>
	                                 <span id="utility_descriptionError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="owner" name="owner" type="text" class="validate">                                     
                                     <label for="owner">Owner </label>
	                                 <span id="ownerError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
								<div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label"> Utility Type </p>
                                    <select class="searchable validate-dropdown" id="utility_type_fk" name="utility_type_fk"  >
                                         <option value="" >Select</option>
                                         <%-- <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> --%>	
                                    </select>
                                    <span id="utility_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Category </p>
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="category_fkError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label"> Execution Agency </p>
                                    <select class="searchable validate-dropdown" id="execution_agency_fk" name="execution_agency_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="execution_agency_fkError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
								<div class="col s6 m4 l3 input-field ">
                                    <p class="searchable_label mb-8"> Impacted Contract </p>
                                    <select class="searchable validate-dropdown" id="impacted_contract_fk" name="impacted_contract_fk"  >
                                         <option value="" >Select</option>
                                         <%-- <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> --%>	
                                    </select>
                                    <span id="impacted_contract_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l3 input-field">
                                    <p class="searchable_label mb-8"> requirement stage </p>
                                    <select class="searchable validate-dropdown" id="requirement_stage_fk" name="requirement_stage_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="requirement_stage_fkError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m4 l3 input-field">
                                     <input id="planned_completion_date" name="planned_completion_date" type="text" class="validate datepicker">                                     
                                     <label for="planned_completion_date">Planned Completion </label>
	                                 <button type="button" id="planned_completion_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="planned_completion_dateError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m4 l3 input-field">
                                     <input id="shifting_completed_date" name="shifting_completed_date" type="text" class="validate datepicker">                                     
                                     <label for="shifting_completed_date">Shifting Completed </label>
	                                 <button type="button" id="shifting_completed_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="shifting_completed_dateError" class="error-msg" ></span>
                                </div>
							</div>
							<div class="row">
                             	<div class="col s12 m4 l3 input-field">
                                     <input id="start_date" name="start_date" type="text" class="validate datepicker">                                     
                                     <label for="start_date">Start Date </label>
	                                 <button type="button" id="start_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
	                                 <span id="start_dateError" class="error-msg" ></span>
                                </div>
								<div class="col s6 m4 l2 input-field">
                                     <input id="scope" name="scope" type="number" class="validate">                                     
                                     <label for="scope">Scope </label>
	                                 <span id="scopeError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                     <input id="completed" name="completed" type="number" class="validate">                                     
                                     <label for="completed">Completed </label>
	                                 <span id="completedError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l2 input-field">
                                    <p class="searchable_label mb-8"> Unit </p>
                                    <select class="searchable validate-dropdown" id="unit_fk" name="unit_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="unit_fkError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m4 l3 input-field">
                                    <p class="searchable_label mb-8"> Status </p>
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk" >
                                        <option value="" >Select</option>
                                        <%-- <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> --%>
                                    </select>
                                      <span id="status_fkError" class="error-msg" ></span>
                                </div>
							</div>
							
							<div class="col s12">
										<div class="row fixed-width">
									       <h6 class="center-align">Progress Details</span></h6>
									        <div class="table-inside">
									            <table id="progressDetailsTable" class="mdl-data-table mobile_responsible_table" >
									                <thead>
									                    <tr>
									                        <th>Progress Date </th>
															<th>Progress of Work </th>
															<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th style="width:8%">Action</th></c:if>
									                    </tr>
									                </thead>
									                <tbody id="progressDetailsTableBody">
									                <c:choose>
				                                        <c:when test="${not empty contractDeatils.departmentList }" >				                                          
				                                		  <c:forEach var="departmentObj" items="${contractDeatils.departmentList }" varStatus="index"> 				                                		      
											                  <tr id="progressDetailsRow${index.count }">
											                        <td data-head="Progress Date" class="input-field">
											                              <input id="progress_date${index.count }" name="progress_dates" type="text" class="validate datepicker" placeholder="Progress Date">
										                                  <button type="button" id="progress_date${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
										                                  <span id="progress_date${index.count}Error" class="error-msg" ></span>
											                        </td>
											                        <td data-head="Progress of Work" class="input-field">
											                            <textarea id="progress_of_work${index.count }" name ="progress_of_works" class="pmis-textarea" placeholder="Progress of Work"></textarea>
									                                    <span id="progress_of_work${index.count }Error" class="error-msg"></span>
											                        </td>
											                        <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
												                        <td class="mobile_btn_close">
												                            <a onclick="removeProgressDetailsRow('${index.count }');"
												                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
												                        </td>
											                        </c:if>
											                    </tr>											                  
									                	</c:forEach>
                                           			</c:when>
                                             		<c:otherwise>
									                    <tr id="progressDetailsRow0">
									                        <td data-head="Progress Date" class="input-field">
									                              <input id="progress_date0" name="progress_dates" type="text" class="validate datepicker" placeholder="Progress Date">
								                                  <button type="button" id="progress_date0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
								                                  <span id="progress_date0Error" class="error-msg" ></span>
									                        </td>									                       
									                        <td data-head="Progress of Work" class="input-field">
									                            <textarea id="progress_of_work0" name ="progress_of_works" class="pmis-textarea" placeholder="Progress of Work"></textarea>
							                                    <span id="progress_of_work0Error" class="error-msg"></span>
									                        </td>
									                        <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
										                        <td class="mobile_btn_close">
										                            <a onclick="removeProgressDetailsRow('0');"
										                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
										                        </td>
									                        </c:if>
									                    </tr>
									              </c:otherwise>
                                            	</c:choose>
									                </tbody>
									            </table>
									            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
									            <table  class="mdl-data-table" style="margin-bottom: 20px">
			                                        <tbody>                                          
			                                            <tr>
			                                   				<td colspan="3"  ><a class="btn waves-effect waves-light bg-m t-c "  onclick="addProgressDetailsRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table> 
			                                    </c:if>
			                                    <c:choose>
				                                    <c:when test="${not empty contractDeatils.departmentList && fn:length(contractDeatils.departmentList) gt 0 }">
				                                		<input type="hidden" id="progressDetailsRowNo"  name="progressDetailsRowNo" value="${fn:length(contractDeatils.departmentList) }" />
				                                	</c:when>
				                                 	<c:otherwise>
				                                 		<input type="hidden" id="progressDetailsRowNo"  name="progressDetailsRowNo" value="0" />
				                                 	</c:otherwise>
				                                 </c:choose>
							                                    
									        </div>
									    </div>
									</div>
							
							
	                            <div class="col s12">
	                                <div class="row fixed-width" >
	                                    <!-- <h5 class="center-align"><span class="div-header">Attachments</span></h5>  -->
	                                    <h6 class="center-align">Attachments</span></h6> 
	                                    <div class="table-inside" style="margin-bottom: 20px">
	                                        <table class="mdl-data-table mobile_responsible_table" >
	                                            <thead>
	                                                <tr>
	                                                	<th>File Type </th>
	                                                    <th>Name </th>
	                                                    <th style="text-align:center;">Attachment</th>
	                                                    <th> </th>
	                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
	                                                    	<th style="width:8%">Action</th>
	                                                    </c:if>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="attachmentsTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty contractDeatils.contractDocuments  && fn:length(contractDeatils.contractDocuments ) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${contractDeatils.contractDocuments }" varStatus="index">  
			                                                <tr id="attachmentRow${index.count }">
			                                                	<td data-head="File Type " class="input-field">
																	<select  name="attachment_file_types"  id="attachment_file_types${index.count }"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${contractFileTypeList}">
					                    					  				 <option value="${obj.contract_file_type }" <c:if test="${docObj.contract_file_type_fk eq obj.contract_file_type}">selected</c:if>>${obj.contract_file_type}</option>
					                                          			  </c:forEach>
					                               					  </select>
															    </td>
			                                                    <td data-head="Name " class="input-field"> <input id="attachmentNames${index.count }" name="attachmentNames" type="text" class="validate"
			                                                            placeholder="Name" value="${docObj.name }">
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="attachmentFiles${index.count }" name="attachmentFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="attachmentFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="attachmentFileNames${index.count }" name="attachmentFileNames" value="${docObj.attachment }">
			                                                             <span id="attachmentFileName${index.count }" class="filevalue"></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
			                                                     		<input type="hidden" id="attach_file_ids${index.count }" name="attach_file_ids" value="${docObj.contract_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.CONTRACT_FILES%>${docObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                        
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
	                                             			<td data-head="File Type " class="input-field">																		
																<select  name="attachment_file_types"  id="attachment_file_types0"  class="validate-dropdown searchable">
				                                   					 <option value="" >Select</option>
				                                         			  <c:forEach var="obj" items="${contractFileTypeList}">
				                    					  				 <option value="${obj.contract_file_type }">${obj.contract_file_type}</option>
				                                          			  </c:forEach>
				                               					  </select>
															    </td>
		                                                    <td data-head="Name " class="input-field"> <input id="attachmentNames0" name="attachmentNames" type="text" class="validate"
		                                                            placeholder="Name">
		                                                    </td>
		                                                    <td data-head="Attachment" class="input-field">
		                                                        <span class="normal-btn">
		                                                            <input type="file" id="attachmentFiles0" name="attachmentFiles"
		                                                                style="display:none" onchange="getFileName('0')"/>
		                                                            <label for="attachmentFiles0" class="btn bg-m"><i
		                                                                    class="fa fa-paperclip"></i></label>
		                                                            <input type="hidden" id="attachmentFileNames0" name="attachmentFileNames" value="">
		                                                            <span id="attachmentFileName0" class="filevalue"></span>
		                                                        </span>
		                                                    </td>
		                                                    <td><input type="hidden" id="attach_file_ids0" name="attach_file_ids" value= ""/>
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
														<td colspan="3" > <a type="button"  class="btn waves-effect waves-light bg-m t-c"  onclick="addAttachmentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty contractDeatils.contractDocuments && fn:length(contractDeatils.contractDocuments) gt 0 }">
		                                    		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="${fn:length(contractDeatils.contractDocuments) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose> 
	                                    </div>
	                                </div>
	                            </div>                         
                                                   
                            <div class="row">
                                <div class="col s12 m12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${designDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom:20px;">
                                <div class="col s6 offset-m2 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/bses" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                            </div>                           
                </div>
                    <!-- form ends  -->
                        </form>
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
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

   <script>
   $(document).ready(function () {
        $('select:not(.searchable)').formSelect();
    	$('.searchable').select2();
    	$(".datepicker").datepicker();
    	
   });

	function addAttachmentRow(){		
		 var rowNo = $("#attachmentRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var total = 0;
		 var html = '<tr id="attachmentRow'+rNo+'">'
					 +'<td data-head="File Type " class="input-field">'
							+'<select  name="attachment_file_types"  id="attachment_file_types'+rNo+'"  class="validate-dropdown searchable">'
		    					+ '<option value="" >Select</option>'
		          			 /*  <c:forEach var="obj" items="${contractFileTypeList}">
					  				+ '<option value="${obj.contract_file_type }">${obj.contract_file_type}</option>'
		           			  </c:forEach> */
						+ '</select></td>'
					 +'<td data-head="Name " class="input-field"> <input id="attachmentNames'+rNo+'" name="attachmentNames" type="text" class="validate" placeholder="Name"> </td>'
					 +'<td data-head="Attachment" class="input-field">'
					 +'<span class="normal-btn">'
					 +'<input type="file" id="attachmentFiles'+rNo+'" name="attachmentFiles" style="display:none" onchange="getFileName('+rNo+')" />'
					 +'<label for="attachmentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
					 +'<input type="hidden" id="attachmentFileNames'+rNo+'" name="attachmentFileNames">'
					 +'<span id="attachmentFileName'+rNo+'" class="filevalue"></span>'
					 +'</span>'
					 +'</td>'
					 +'<td><input type="hidden" id="attach_file_ids'+rNo+'" name="attach_file_ids"/></td>';
					 
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
	         $("#attach_file_ids0").val('');
	} 
	
	function removeAttachmentRow(rowNo){
		$("#attachmentRow"+rowNo).remove();
	}

	function getFileName(rowNo){
		var filename = $('#attachmentFiles'+rowNo)[0].files[0].name;
	    $('#attachmentFileName'+rowNo).html(filename);
	    $('#attachmentFileNames'+rowNo).val(filename);
	}
	
	
	 function addProgressDetailsRow(){
    	 var rowNo = $("#progressDetailsRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var no = 0;
		 var html = '<tr id="progressDetailsRow'+rNo+'"> '
		 			+'<td data-head="Progress Date" class="input-field"> <input id="progress_date'+rNo+'" name="progress_dates" type="text" class="validate datepicker" '
		 			+'placeholder="Progress Date"> <button type="button" id="progress_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> '											                              
        			+'<span id="progress_date'+rNo+'Error" class="error-msg" ></span>   </td>'
   					+'<td data-head="Progress of Work" class="input-field"> <textarea id="progress_of_work'+rNo+'" name ="progress_of_works" class="pmis-textarea" placeholder="Progress of Work"></textarea>'
   					+'<span id="progress_of_work'+rNo+'Error" class="error-msg"></span>  </td>'
   				<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
      				+'<td class="mobile_btn_close"> <a onclick="removeProgressDetailsRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td>'
   				</c:if>
					+'</tr>';
		
			 $('#progressDetailsTableBody').append(html); 
			 $("#progressDetailsRowNo").val(rNo);
			 $('.searchable').select2();
    }
    
    function removeProgressDetailsRow(rowNo){
    	$("#progressDetailsRow"+rowNo).remove();
    }

   </script>

</body>
</html>