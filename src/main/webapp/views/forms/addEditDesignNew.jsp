<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Design & Drawing - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
	
    <style>
        #revTable .datepicker~button,
        #statusTable .datepicker~button{
            top: 30px;
            right:2px;
        }
        .mdl-data-table{
        	border:1px solid #ccc;	
        }

		.p-sticky{
			position:sticky;
		}
		.z-1{
			z-index:1;
		}
		.t-48{
			top:48px;
		}
		span.required {
		    font-size: inherit;
		}
        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            color: #777;
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

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
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
        #revTable .select2-container{
	        max-width:150px;
	        width: 150px !important;
	        text-align:left;
	        margin-top:10px;
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
		.fs11px{font-size: 11px !important;}
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
			/* .input-field>input[type='text'].datepicker ~ label:not(.label-icon).active{
				-webkit-transform: translateY(-20px) scale(0.95);
			    transform: translateY(-20px) scale(0.95);
			}	 */	
			.input-field .select2-container--default {
			    margin-bottom: 0.5rem;
			}
			@media only screen and (min-width: 770px){
				.mdl-data-table	tr>td.input-field .select2-container--default {
				    margin-bottom: 0;
	    			margin-top: 8px;
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
		.input-field.min4{
			min-height:4rem ;
		}
		.filevalue {
            display: block;
            margin-top: 10px;
			font-size: .9rem;
        }
		@media only screen and (max-width: 769px){
		 .filevalue {
					    width: 200%;
					    white-space: break-spaces;
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
                               	    <c:if test="${action eq 'edit'}">Update Design & Drawing</c:if>
									<c:if test="${action eq 'add'}"> Add Design & Drawing</c:if>
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
						   <c:if test="${action eq 'edit'}"> 
						    <div class="row no-mar p-sticky t-48 z-1">
							    <div class="col s12 m12">
							      <ul class="tabs tab-flex" id="menu-center">
							        <li class="tab" ><a class="t-c" href="#workDetails">Work Details</a></li>
							        <li class="tab" ><a class="t-c" href="#drawingDetails">Drawing Details</a></li>
							        <li class="tab" ><a class="t-c" href="#statusDetails">Drawing Status</a></li>							        
							        <li class="tab" ><a class="t-c" href="#revisionDetails">Revisions</a></li>
							        <li class="tab"><a class="t-c" href="#documentDetails">Attachment</a></li>
							      </ul>
							    </div>							    
						   </div>
						  </c:if> 
						    <div class="container container-no-margin">
                            <div class="row section scrollspy" id="workDetails">
						    <c:if test="${action eq 'add'}">	
						    	<h5 class="center-align m-b-2">Work Details</h5>
                                <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                    onchange="getWorksList(this.value);">
                                         <option value="" >Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${designDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             
                            </c:if>
 							<c:if test="${action eq 'edit'}">		                             
	                                <div class="col s6 m4 l4 input-field ">
	                                    <input type="text" value="${designDetails.project_id_fk}- ${designDetails.project_name}" readonly />
								    	<label for="project_id_fk">Project <span class="required">*</span></label>
								    	<input type="hidden" name="project_id_fk" id="project_id_fk" value="${designDetails.project_id_fk}" readonly />
								    </div> 
	                                <div class="col s6 m4 input-field"> 
	                                    <input type="text" value="${designDetails.work_id_fk}- ${designDetails.work_short_name}" readonly />
	                                	<label for="work_id_fk">Work <span class="required">*</span></label>
	                                	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${designDetails.work_id_fk}" readonly />
	                                </div>
	                           
                              </c:if>
                                <div class="col s6 m4 l4 input-field min4">
                                    <p class="searchable_label">Approving Railway<span class="required">*</span></p>
                                     <select id="approving_railway" name="approving_railway" class="searchable validate-dropdown" >
                                        <option value="">Select</option>  
                                        <c:forEach var="obj" items="${approvingRailway }">
                                      	    <option value= "${ obj.railway_id}" <c:if test="${designDetails.approving_railway eq obj.railway_id}">selected</c:if>>${ obj.railway_id}</option>
                                        </c:forEach>                                     
                                    </select>
                                    <span id="approving_railwayError" class="error-msg" ></span>                                     
                                </div>

                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Department </p>
                                     <select name="department_id_fk" id="department_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                          <c:forEach var="obj" items="${departmentList }">
                                      	    <option value= "${ obj.department_fk}" <c:if test="${designDetails.department_id_fk eq obj.department_fk}">selected</c:if>>${ obj.department_name}</option>
                                          </c:forEach>
                                    </select>
                                     <span id="department_id_fkError" class="error-msg" ></span>
                                </div>
                           
                                <div class="col s6 m4 l4 input-field">
                                	<p class="searchable_label">Dy HOD <span class="required">*</span></p>
                                	<select name="dy_hod" id="dy_hod" class="validate-dropdown searchable"> 
                         		  			<option value="">Select</option> 
                                	</select>
                                	<span id="dy_hodError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                	<p class="searchable_label">HOD <span class="required">*</span></p>
                                	<select name="hod" id="hod" class="validate-dropdown searchable" onchange="getDyHodList();"> 
                         		  			<option value="">Select</option> 
                                	</select>
                                	<span id="hodError" class="error-msg" ></span>
                                </div>
                                                        
                                 <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label">Structure <span class="required">*</span></p>
                                    <select id="structure_type_fk" name="structure_type_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                                 		<c:forEach var="obj" items="${structureTypeList}">
                  						   <option value="${obj.structure_type_fk }" <c:if test="${designDetails.structure_type_fk eq obj.structure_type_fk }">selected</c:if>>${obj.structure_type_fk}</option>
                                       </c:forEach>
                                    </select>
                                    <span id="structure_type_fkError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label">Structure Id<span class="required">*</span></p>
                                    <select id="structure_id_fk" name="structure_id_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option> 
                                        <c:forEach var="obj" items="${structureId }">
                             				<option value="${obj.structure }" <c:if test="${designDetails.structure_id_fk eq obj.structure }">selected</c:if>>${obj.structure }</option>
                           				 </c:forEach>                                  		
                                    </select>
                                    <span id="structure_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Prepared By </p>
                                    <select class="searchable validate-dropdown" name="prepared_by_id_fk" id="prepared_by_id_fk">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="prepared_by_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">       
                             <c:if test="${action eq 'add'}">                           
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label">Contract</p>
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
                                	<div class="col s6 m4 l4 input-field"> 
	                                    <input type="text" value="${designDetails.contract_id_fk}- ${designDetails.contract_short_name}" readonly />
									    <label for="contract_id_fk"> Contract <span class="required">*</span></label>
									    <input type="hidden" name="contract_id_fk" id="contract_id_fk" value="${designDetails.contract_id_fk}" readonly />
	                                </div>
                                </c:if>
                                <div class="col s6 m4 l4 input-field min4">
                                    <p class="searchable_label">Consultant</p>
                                    <select name="consultant_contract_id_fk" id="consultant_contract_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${contractList }">
                                      	   <option value= "${ obj.contract_id_fk}" <c:if test="${designDetails.consultant_contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                         </c:forEach>
                                    </select>
                                     <span id="consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field min4">
                                    <p class="searchable_label fs-sm-67rem">Proof Consultant </p>
                                    <select id="proof_consultant_contract_id_fk" name="proof_consultant_contract_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                          <option value="" selected>Select</option>
                                      	   <c:forEach var="obj" items="${contractList }">
                                      	    <option value= "${ obj.contract_id_fk}" <c:if test="${designDetails.proof_consultant_contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
                                           </c:forEach>
                                    </select>
                                    <span id="proof_consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label mb-8">Drawing Type </p>
                                    <select id="drawing_type_fk" name="drawing_type_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                               			<c:forEach var="obj" items="${drawingTypeList}">
                						    <option value="${obj.drawing_type_fk }" <c:if test="${designDetails.drawing_type_fk eq obj.drawing_type_fk }">selected</c:if>>${obj.drawing_type_fk}</option>
                                      	</c:forEach>
                                    </select>
                                    <span id="drawing_type_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m8">
                                	<div class="row">
		                                <div class="col s6 m4 l4 input-field">
		                                    <p class="searchable_label mb-8" >Approval Authority<span class="required">*</span></p>
		                                    <select id="approval_authority_fk" name="approval_authority_fk" class="searchable validate-dropdown">
		                                        <option value="" selected>Select</option>     
		                                         <c:forEach var="obj" items="${approvalAuthority }">
                                    				<option value="${obj.approval_authority_fk }" <c:if test="${designDetails.approval_authority_fk eq obj.approval_authority_fk }">selected</c:if>>${obj.approval_authority_fk }</option>
                                  				  </c:forEach>                          			
		                                    </select>
		                                    <span id="approval_authority_fkError" class="error-msg" ></span>
		                                </div>
		                         		<div class="col s6 m4 l4 input-field">
		                                    <input id="required_date" name="required_date" type="text" class="validate datepicker" value="${designDetails.required_date }">
		                                    <label for="required_date" class="fs-sm-8rem fs-9">Required Date </label>
		                                    <button type="button" id="required_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
		                                    <span id="required_dateError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m4 l4 input-field">
		                                    <input id="gfc_release_date" name="gfc_released" type="text" class="validate datepicker" value="${designDetails.gfc_released }">
		                                    <label for="gfc_release_date" class="fs-sm-8rem fs-9">GFC Release Date </label>
		                                    <button type="button" id="gfc_release_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
		                                    <span id="gfc_release_dateError" class="error-msg" ></span>
		                                </div>                                	
                                	</div>
                                </div>
                            </div>

                            <div class="row section scrollspy" id="drawingDetails">
                            	<h5 class="center-align">Drawing Details</h5>
                                <div class="col s12 m8 l12 input-field">
                                    <textarea id="drawing_title" name="drawing_title" class="pmis-textarea" data-length="1000">${designDetails.drawing_title }</textarea>
                                    <label for="drawing_title">Drawing Title</label>
                                     <span id="drawing_titleError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field">
                                     <input id="contractor_drawing_no" name="contractor_drawing_no" type="text" class="validate" value="${designDetails.contractor_drawing_no }">
		                             <label for="contractor_drawing_no" >Agency Drawing No </label>
		                             <span id="contractor_drawing_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field">
                                     <input id="mrvc_drawing_no" name="mrvc_drawing_no" type="text" class="validate" value="${designDetails.mrvc_drawing_no }">
                                     <label for="mrvc_drawing_no">MRVC Drawing No </label>
                                     <span id="mrvc_drawing_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field">
                                     <input id="division_drawing_no" name="division_drawing_no" type="text" class="validate" value="${designDetails.division_drawing_no }">
		                             <label for="division_drawing_no">Divisional Drawing No</label>
		                             <span id="division_drawing_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field">
                                     <input id="hq_drawing_no" name="hq_drawing_no" type="text" class="validate" value="${designDetails.hq_drawing_no }">
		                             <label for="hq_drawing_no">HQ Drawing No</label>
                                     <span id="hq_drawing_noError" class="error-msg" ></span>
                                </div>
                               
                            </div> 
                      </div>
                            <!-- insurance show hide div  -->
                           <c:if test="${action eq 'edit'}"> 
                           <div class="container container-no-margin">
                           	<div class="row fixed-width section scrollspy" id="statusDetails">
                                <h5 class="center-align">Drawing Status</h5>
                                <div class="table-inside">
                                    <table id="statusTable" class="mdl-data-table mobile_responsible_table">
                                    	<thead>
										    <tr>
										        <th>Stage </th>
										        <th>Submitted By</th>
										        <th>Submitted To </th>
										        <th>Purpose of Submission <br>/ Remarks</th>
										        <th>Submitted Date</th>
										        <th>Action</th>
										    </tr>
										</thead>
										<tbody id="statusTableBody">
                                        
                                        <c:choose>
	                                        <c:when test="${not empty designDetails.designStatusList && fn:length(designDetails.designStatusList) gt 0 }">
	                                        	<c:forEach var="statObj" items="${designDetails.designStatusList }" varStatus="index">  
		                                            <tr id="StatusRow${index.count }">   
												        <td data-head="Stage" class="input-field">
													        <select id="stage${index.count }" name="stage_fks" class="searchable validate-dropdown">
						                                        <option value="" >Select</option>
						                                         <c:forEach var="obj" items="${stage }">
				                                    				<option value="${obj.stage_fk }" <c:if test="${statObj.stage_fk eq obj.stage_fk }">selected</c:if>>${obj.stage_fk }</option>
				                                  				  </c:forEach>			                                         
						                                    </select>
												        </td>
												        <td data-head="Submitted By" class="input-field">
												        	<select id="submitted_by${index.count }" name="submitted_bys" class="searchable validate-dropdown">
					                                        	<option value="" >Select</option>	
					                                        	 <c:forEach var="obj" items="${submitted }">
				                                    				<option value="${obj.design_status_submit }" <c:if test="${statObj.submitted_by eq obj.design_status_submit }">selected</c:if>>${obj.design_status_submit }</option>
				                                  				  </c:forEach>			                                         
					                                    	</select>
												        </td>
												        <td data-head="Submitted To" class="input-field">
												        	<select id="submitted_to${index.count }" name="submitted_tos" class="searchable validate-dropdown">
					                                        	<option value="" >Select</option>	
					                                        	 <c:forEach var="obj" items="${submitted }">
				                                    				<option value="${obj.design_status_submit }" <c:if test="${statObj.submitted_to eq obj.design_status_submit }">selected</c:if>>${obj.design_status_submit }</option>
				                                  				  </c:forEach>			                                         
					                                    	</select>
												        </td>
												        <td data-head="Purpose of Submission / Remarks" class="input-field">
												        	<select id="submssion_purpose${index.count }" name="submssion_purposes" class="searchable validate-dropdown">
					                                        	<option value="" >Select</option>	
					                                        	 <c:forEach var="obj" items="${submssionpurpose }">
				                                    				<option value="${obj.submission_purpose }" <c:if test="${statObj.submssion_purpose eq obj.submission_purpose }">selected</c:if>>${obj.submission_purpose }</option>
				                                  				  </c:forEach>			                                         
					                                    	</select>
												        </td>
												        <td data-head="Submitted Date" class="input-field">
												        	<input id="submitted_date${index.count }" name="submitted_dates" type="text" class="validate datepicker" value="${statObj.submitted_date}" placeholder="Submitted Date">
				                                    		<button type="button" id="submitted_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
												        </td>
												        <td class="mobile_btn_close">
		                                                    <a  class="btn waves-effect waves-light red t-c " onclick="removeStatusRow('${index.count }');"> <i
		                                                            class="fa fa-close"></i></a>
		                                                </td>
												    </tr>
											    </c:forEach>
                                         	</c:when>
                                           	<c:otherwise>
                                           		<tr id="StatusRow0">
											        <td data-head="Stage" class="input-field">
												        <select id="stage0" name="stage_fks" class="searchable validate-dropdown">
					                                        <option value="" >Select</option>	
					                                         <c:forEach var="obj" items="${stage }">
				                                    				<option value="${obj.stage_fk }" >${obj.stage_fk }</option>
				                                  			 </c:forEach>		                                         
					                                    </select>
											        </td>
											        <td data-head="Submitted By" class="input-field">
											        	<select id="submitted_by0" name="submitted_bys" class="searchable validate-dropdown">
				                                        	<option value="" >Select</option>	
				                                        	 <c:forEach var="obj" items="${submitted }">
				                                    				<option value="${obj.design_status_submit }">${obj.design_status_submit }</option>
				                                  			 </c:forEach>			                                         
				                                    	</select>
											        </td>
											        <td data-head="Submitted To" class="input-field">
											        	<select id="submitted_to0" name="submitted_tos" class="searchable validate-dropdown">
				                                        	<option value="" >Select</option>
				                                        	<c:forEach var="obj" items="${submitted }">
				                                    				<option value="${obj.design_status_submit }">${obj.design_status_submit }</option>
				                                  			</c:forEach>				                                         
				                                    	</select>
											        </td>
											        <td data-head="Purpose of Submission / Remarks" class="input-field">
											        	<select id="submssion_purpose0" name="submssion_purposes" class="searchable validate-dropdown">
				                                        	<option value="" >Select</option>
				                                        	 <c:forEach var="obj" items="${submssionpurpose }">
				                                    				<option value="${obj.submission_purpose }">${obj.submission_purpose }</option>
				                                  			 </c:forEach>				                                         
				                                    	</select>
											        </td>
											        <td data-head="Submitted Date" class="input-field">
											        	<input id="submitted_date0" name="submitted_dates" type="text" class="validate datepicker" value="" placeholder="Submitted Date">
			                                    		<button type="button" id="submitted_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											        </td>
											        <td class="mobile_btn_close">
	                                                    <a  class="btn waves-effect waves-light red t-c " onclick="removeStatusRow('${index.count }');"> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
											    </tr>
										     </c:otherwise>
                                        </c:choose>   
										</tbody>
                                	</table>
                                	
                                	<table class="mdl-data-table">
                                        <tbody id="statTableBody">                                          
		                                    <tr>
		  										 <td colspan="9"> 
		  										 	<a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addStatusRow()"> 
		  										 		<i class="fa fa-plus"></i>
		  										 	</a>
		  										 </td>
		                                    </tr>
                                        </tbody>
                                    </table>
                                    
                                    <c:choose>
                                        <c:when test="${not empty designDetails.designStatusList && fn:length(designDetails.designStatusList) gt 0 }">
                                            <input type="hidden" id="statusRowNo"  name="statusRowNo" value="${fn:length(designDetails.designStatusList)}" />
                                        </c:when>
                                        <c:otherwise>
                                        	<input type="hidden" id="statusRowNo"  name="statusRowNo" value="0" />
                                        </c:otherwise>
                                    </c:choose> 
                                </div>
                            </div>
                           
                             <div class="row fixed-width section scrollspy" id="revisionDetails">
                                <h5 class="center-align">Revision Details</h5>
                                <div class="table-inside">
                                    <table id="revTable" class="mdl-data-table mobile_responsible_table">
                                        <thead>
                                            <tr>
                                                <th>Revision </th>
                                                <th>Revision Date </th>                                               
                                                <th>Revision Status</th>
                                                <th>Remarks</th>
                                                <th>Current</th>
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
		                                                <td data-head="Revision Date" class="input-field">
		                                                    <input id="revision_date${index.count }" name="revision_dates" type="text" class="validate datepicker"
		                                                        placeholder="Revision Date" value="${revObj.revision_date }">
		                                                    <button type="button" id="revision_date${index.count }_icon" class="datepicker-button"><i
		                                                            class="fa fa-calendar"></i></button>
		                                                </td>
		                                                
		                                                <td data-head="Revision Status" class="input-field">
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
		                                                <td data-head="Status" class="input-field center-align">
			                                                <p>
			                                                 	<label> 
			                                                	   <input type="checkbox"  id="revision_status_checkbox${index.count }" name="current"  value="${revObj.current}" class="revision_status_checkbox" 
			                                                	   <c:if test="${revObj.current eq 'Yes'}">checked</c:if>/> 
			                                                			<span></span> 
			                                                	</label>
		                                                	</p>
		                                                	  <input type="hidden" id="revision_status_checkbox${index.count }s"  name="currents" value="${revObj.current}" class="revision_status_checkbox" />
		                                                	
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
	                                                <td data-head="Revision Date" class="input-field">
	                                                    <input id="revision_date0" name="revision_dates" type="text" class="validate datepicker"
	                                                        placeholder="Revision Date">
	                                                    <button type="button" id="revision_date0_icon" class="datepicker-button"><i
	                                                            class="fa fa-calendar"></i></button>
	                                                </td>
	                                               
	                                                <td data-head="Revision Status" class="input-field">
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
	                                                <td data-head="Status" class="input-field center-align">
		                                                <p>
		                                                 	<label> 
		                                                	   <input type="checkbox" id="revision_status_checkbox0"  name="current"  class="revision_status_checkbox" /> 
		                                                	   
		                                                	   <span></span> 
		                                                	</label>
	                                                	</p>
	                                                	<input type="hidden" id="revision_status_checkbox0s"  name="currents" value="No" class="revision_status_checkbox" />
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
		  										 <td colspan="9"> 
		  										 	<a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addRevisionRow()"> 
		  										 		<i class="fa fa-plus"></i>
		  										 	</a>
		  										 </td>
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
                                  
							<div class="row section scrollspy" id="documentDetails">
	                            <div class="col l12 m8 s12 offset-m2"  >
	                                <div class="row fixed-width">
	                                     <h5 class="center-align"><span class="div-header">Attachments</span></h5> 
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table mobile_responsible_table">
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
	                                            <tbody id="designDocumentTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty designDetails.designFilesList && fn:length(designDetails.designFilesList) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${designDetails.designFilesList }" varStatus="index">  
			                                                <tr id="designDocumentRow${index.count }">
			                                                	<td data-head="File Type" class="input-field">
																	<select  name="design_file_typess"  id="design_file_types${index.count }"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                   					  <c:forEach var="obj" items="${designFileType }">
						                                    				<option value="${obj.design_file_type }"<c:if test="${docObj.design_file_type_fk eq obj.design_file_type}">selected</c:if>>${obj.design_file_type }</option>
						                                  				  </c:forEach>
					                               					  </select>
															    </td>
			                                                    <td data-head="Name" class="input-field"> <input id="designDocumentNames${index.count }" name="designDocumentNames" type="text" class="validate"
			                                                            placeholder="Name" value="${docObj.name }">
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field center-align">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="designDocumentFiles${index.count }" name="designDocumentFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="designDocumentFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="designDocumentFileNames${index.count }" name="designDocumentFileNames" value="${docObj.attachment }">
			                                                             <span id="designDocumentFileName${index.count }" class="filevalue"></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
			                                                     		<input type="hidden" id="design_file_ids${index.count }" name="design_file_ids" value="${docObj.design_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.DESIGN_FILES%>${docObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                        
			                                                    </td>
			                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
				                                                    <td class="mobile_btn_close">
				                                                        <a href="javascript:void(0);" onclick="removeDesignDocument('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
				                                                                class="fa fa-close"></i></a>
				                                                    </td>
			                                                    </c:if>
			                                                </tr> 
	                                                	</c:forEach>
	                                           		</c:when>
	                                             	<c:otherwise>
	                                             		<tr id="designDocumentRow0">
	                                             			<td data-head="File Type " class="input-field">																		
																<select  name="design_file_typess"  id="design_file_types0"  class="validate-dropdown searchable">
				                                   					 <option value="" >Select</option>
				                                         			  <c:forEach var="obj" items="${designFileType }">
						                                    				<option value="${obj.design_file_type }">${obj.design_file_type }</option>
						                                  			  </c:forEach>
				                               					  </select>
															    </td>
		                                                    <td data-head="Name " class="input-field"> <input id="designDocumentNames0" name="designDocumentNames" type="text" class="validate"
		                                                            placeholder="Name">
		                                                    </td>
		                                                    <td data-head="Attachment" class="input-field center-align">
		                                                        <span class="normal-btn">
		                                                            <input type="file" id="designDocumentFiles0" name="designDocumentFiles"
		                                                                style="display:none" onchange="getFileName('0')"/>
		                                                            <label for="designDocumentFiles0" class="btn bg-m"><i
		                                                                    class="fa fa-paperclip"></i></label>
		                                                            <input type="hidden" id="designDocumentFileNames0" name="designDocumentFileNames" value="">
		                                                            <span id="designDocumentFileName0" class="filevalue"></span>
		                                                        </span>
		                                                    </td>
		                                                    <td><input type="hidden" id="design_file_ids0" name="design_file_ids" value= ""/>
		                                                    </td>
		                                                    
		                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
			                                                    <td class="mobile_btn_close">
			                                                        <a href="javascript:void(0);" onclick="removeDesignDocument('0');" class="btn waves-effect waves-light red t-c "> <i
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
														<td colspan="3" >	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addDesignDocumentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty designDetails.designFilesList && fn:length(designDetails.designFilesList) gt 0 }">
		                                    		<input type="hidden" id="documentRowNo"  name="documentRowNo" value="${fn:length(designDetails.designFilesList) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="documentRowNo"  name="documentRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose> 
	                                    </div>
	                                </div>
	                            </div>
							</div>
						</div>	  
						 </c:if>        
 						<div class="container container-no-margin">
                           <%--  <div class="row" style="margin-top: 20px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col l12 m8 s12">
                                    <div class="row">
                                    
		                           <div class="col s12 m6">
									  <c:if test="${action eq 'add'}">
			                            <div id="selectedFilesInput">
	                                    	<div class="file-field input-field" id="designFilesDiv1" >
		                                        <div class="btn bg-m t-c">
		                                            <span>Attach Files</span>
		                                            <input type="file" id="designFiles1" name="designFiles"  onchange="selectFile('1')">
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
													<c:set var="existingDesignFilesLength" value="${fn:length(designDetails.designFilesList )}"></c:set>
													<c:if test="${fn:length(designDetails.designFilesList ) gt 0}">
														<c:set var="existingDesignFilesLength" value="${fn:length(designDetails.designFilesList )+1}"></c:set>
													</c:if>
													<div id="selectedFilesInput">
				                                    	<div class="file-field input-field" id="designFilesDiv${existingDesignFilesLength }" >
					                                        <div class="btn bg-m t-c">
					                                            <span>Attach Files</span>
					                                            <input type="file" id="designFiles${existingDesignFilesLength }" name="designFiles"  onchange="selectFile('${existingDesignFilesLength }')">
					                                        </div>
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    
				                                    <div id="selectedFiles">
				                                    	<c:forEach var="obj" items="${designDetails.designFilesList }" varStatus="index">
															 <div id="designFileNames${index.count }">
																<a href="<%=CommonConstants2.DESIGN_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
																<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
																<input type="hidden" name="designFileNames" value="${obj.attachment }">
														     </div>
														     <div style="clear:both" ></div>
														</c:forEach>
													</div>
				                             </c:if>	
									</div>
									
                                    </div>
                                </div>
                            </div>
 --%>

                            <div class="row">
                                <div class="col s12 m8 l12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${designDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 offset-m2 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateDesign();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addDesign();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/design" class="btn waves-effect waves-light bg-s">Cancel</a>
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

	  /* commented because html responsible for this comented  
	  function selectFile(no){
		    files = $("#designFiles"+no)[0].files;
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=designFileNames'+no+'>'
				 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
				 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
				 + '</div>'
				 + '<div style="clear:both;"></div>';
		    }
		    $("#selectedFiles").append(html);		    
		    $('#designFilesDiv'+no).hide();		    
			var fileIndex = Number(no)+1;
			moreFiles(fileIndex);
		}
		
		function moreFiles(no){
			var html = "";
			html =  html + '<div class="file-field input-field" id="designFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c">'
			+ '<span>Attach Files</span>'
			+ '<input type="file" id="designFiles'+no+'" name="designFiles"  onchange="selectFile('+no+')">'
			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFile(no){			
	     	$('#designFilesDiv'+no).remove();
	     	$('#designFileNames'+no).remove();
	    }  */
	    
	    $(document).on('change', '.revision_status_checkbox', function () {
	    	var that=this;
	    	var itemId=$( that ).attr('id');
	    	$(".revision_status_checkbox").prop("checked",false);
	    	$(".revision_status_checkbox").val("No")
	    	$("#"+itemId).prop("checked",true);
	    	if (document.getElementById(itemId).checked) {
	    		$("#"+itemId+"s").val("Yes")
	    	}
	    });
	    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
         	$('.searchable').select2();
            $('#remarks,#issueDesc').characterCounter();
            $('.tooltipped').tooltip();
            $('.scrollspy').scrollSpy();
            
            getHodList();
            getDyHodList();
			
           /*  $( ".revision_status_checkbox" ).each(function( index,val ) {
            	var that=this;
            	  console.log( index + ": " + $( that ).prop('checked') );
            	  $(that).change(function() {
                      if(that.checked) {
                      }
                  });
            	}); */
          
            
            $('input[name=is_there_issue]').change(function () {
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
            
            $('input[name=crs_sanction_fk]').change(function () {
                var radioval = $('input[name=crs_sanction_fk]:checked').val();
                if (radioval == 'Yes') {
                    $('.crs_sanction_fk').css("display", "block");
                }
                else if (radioval == 'No') {
                    $('.crs_sanction_fk').css("display", "none");
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
            
            var crs_sanction_fk = "${designDetails.crs_sanction_fk}";
            if($.trim(crs_sanction_fk) == 'Yes' ){
            	$('.crs_sanction_fk').css("display", "block");
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
	  			$('form input[name=revision_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  		/* 	$('form input[name=mrvc_revieweds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=divisional_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=hq_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } }); */
	  			$('form input[name=currents]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revision_status_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=stage_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_bys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_tos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submssion_purposes]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			document.getElementById("designForm").submit();	
        	}
    	}
    
    	function updateDesign(){		
    		if(validator.form()){ // validation perform
				$(".page-loader").show();
				$('form input[name=revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revision_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			/* $('form input[name=mrvc_revieweds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=divisional_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=hq_approvals]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } }); */
	  			$('form input[name=currents]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revision_status_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=stage_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_bys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_tos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submssion_purposes]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
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
    				 	  }/*"consultant_submission": {
       				 		required: false
       				 	  } ,"mrvc_reviewed": {
    				 		required: false,
       				 		dateBefore1:"#consultant_submission"
    				 	  } */,"divisional_approval": {
    				 		required: false,
       				 		dateBefore2:"#mrvc_reviewed"
    				 	  }/* ,"hq_approval": {
    				 		required: false,
       				 		dateBefore3:"#divisional_approval"
    				 	  } */,"gfc_released": {
    				 		required: false,
       				 		//dateBefore4:"#hq_approval"
    				 	  }/* ,"as_built_date": {
    				 		required: false,
       				 		dateBefore5:"#gfc_released"
    				 	  } */,"hod": {
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
    				 	  },"approval_authority_fk": {
    				 		required: true
    				 	  },"structure_id_fk": {
    				 		required: true
    				 	  },"approving_railway": {
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
	   				 	 },"approval_authority_fk": {
	   				 		required: 'Required'
	   				 	 },"structure_id_fk": {
	   				 		required: 'Required'
	   				 	 },"approving_railway": {
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
    			 	    }else if (element.attr("id") == "approving_railway" ){
   			 		     document.getElementById("approving_railwayError").innerHTML="";
			 			 error.appendTo('#approving_railwayError');
			 	    	}else if (element.attr("id") == "approval_authority_fk" ){
	   			 		     document.getElementById("approval_authority_fkError").innerHTML="";
				 			 error.appendTo('#approval_authority_fkError');
			 	    	}else if (element.attr("id") == "structure_id_fk" ){
	   			 		     document.getElementById("structure_id_fkError").innerHTML="";
				 			 error.appendTo('#structure_id_fkError');
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
	            
	        }, "MRVC reviewed date must be after Revision Date date");
	    	
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
        	        $(this).valid();
        	    }
        	});
            
	  		function addRevisionRow(){
		
		      var rowNo = $("#rowNo").val();
		      var rNo = Number(rowNo)+1;
		      var html ='<tr id="revisionRow'+rNo+'"> '
				      +'<td data-head="Revision" class="input-field"> <input id="revisions'+rNo+'" name="revisions" type="text" class="validate" placeholder="Revision"></td>'
				      +'<td data-head="Revision Date" class="input-field"><input id="revision_date'+rNo+'" name="revision_dates" type="text" class="validate datepicker" placeholder="Revision Date"><button type="button" id="revision_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> </td>'
				     // +'<td data-head="MRVC Reviewed" class="input-field"><input id="mrvc_revieweds'+rNo+'" name="mrvc_revieweds" type="text" class="validate datepicker" placeholder="MRVC Reviewed"><button type="button" id="mrvc_revieweds'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>'
				     // +'<td data-head="Divisional Approval" class="input-field"><input id="divisional_approvals'+rNo+'" name="divisional_approvals" type="text" class="validate datepicker" placeholder="Divisional Approval"> <button type="button" id="divisional_approvals'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></td>'
					 // +'<td data-head="HQ approval" class="input-field"><input id="hq_approvals'+rNo+'" name="hq_approvals" type="text" class="validate datepicker" placeholder="HQ approval"> <button type="button" id="hq_approvals'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> </td>'
					  +'<td data-head="Revision Status" class="input-field">'
					  +'<select class="searchable" id="revision_status_fks'+rNo+'" name="revision_status_fks">'
				      +'<option value="" selected>Select </option>'
				        <c:forEach var="obj" items="${revisionStatuses }">
					  	  +'<option value="${obj.revision_status }">${obj.revision_status }</option>'
						</c:forEach>
					  +'</select></td>'
					  +'<td data-head="Remarks" class="input-field"> <input id="remarkss'+rNo+'" name="remarkss" type="text" class="validate" placeholder="Remarks"></td>'
					  +'<td data-head="Status" class="input-field center-align"><p>	<label> <input type="checkbox" id="revision_status_checkbox'+rNo+'" class="revision_status_checkbox" name="current"/><span></span> </label> </p>  <input type="hidden" id="revision_status_checkbox'+rNo+'s"  name="currents" value="No" class="revision_status_checkbox" /></td>'
					  +'<td class="mobile_btn_close"><a class="btn waves-effect waves-light red t-c " onclick="removeRevision('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
						
					  $('#revisionsTableBody').append(html);
					  $("#rowNo").val(rNo);
				      $('.searchable').select2();
				      
			  }
			  
			  function removeRevision(rowNo){
			    	$("#revisionRow"+rowNo).remove();
			  }
			  
			  function addStatusRow(){
					
			      var rowNo = $("#statusRowNo").val();
			      var rNo = Number(rowNo)+1;
			      var html ='<tr id="StatusRow'+rNo+'"> '
			      			+'<td data-head="Stage" class="input-field">	 <select id="stage'+rNo+'" name="stage_fks" class="searchable validate-dropdown">'
			      			+'<option value="" >Select</option>'
			      			 <c:forEach var="obj" items="${stage }">
			      				+'<option value="${obj.stage_fk }" >${obj.stage_fk }</option>'
          			 		 </c:forEach>	
			      			+' </select> </td> <td data-head="Submitted By" class="input-field">'
			      			+'<select id="submitted_by'+rNo+'" name="submitted_bys" class="searchable validate-dropdown"> <option value="" >Select</option>'
			      			 <c:forEach var="obj" items="${submitted }">
		      					+'<option value="${obj.design_status_submit }" >${obj.design_status_submit }</option>'
      			 		 	 </c:forEach>	
			      			+'</select></td> <td data-head="Submitted To" class="input-field"> <select id="submitted_to'+rNo+'" name="submitted_tos" class="searchable validate-dropdown">'
			      			+'<option value="" >Select</option>'
			      			 <c:forEach var="obj" items="${submitted }">
	      						+'<option value="${obj.design_status_submit }" >${obj.design_status_submit }</option>'
  			 		 	 	 </c:forEach>	
			      			+'</select> </td> <td data-head="Purpose of Submission / Remarks" class="input-field">'
			      			+'<select id="submssion_purpose'+rNo+'" name="submssion_purposes" class="searchable validate-dropdown"> <option value="" >Select</option>'
			      			 <c:forEach var="obj" items="${submssionpurpose }">
      							+'<option value="${obj.submission_purpose }" >${obj.submission_purpose }</option>'
			 		 	 	 </c:forEach>
			      			+' </select> </td>'
			      			+'<td data-head="Submitted Date" class="input-field"> <input id="submitted_date'+rNo+'" name="submitted_dates" type="text" class="validate datepicker" value="" placeholder="Submitted Date">'
			      			+'<button type="button" id="submitted_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button></td> '
			      			+'<td class="mobile_btn_close">	 <a  class="btn waves-effect waves-light red t-c " onclick="removeStatusRow('+rNo+');"> <i class="fa fa-close"></i></a>	</td>';
							
						  $('#statusTableBody').append(html);
						  $("#statusRowNo").val(rNo);
					      $('.searchable').select2();
					      
				  }
				  
				  function removeStatusRow(rowNo){
				    	$("#StatusRow"+rowNo).remove();
				  }
			  
			  function addDesignDocumentRow(){		
					 var rowNo = $("#documentRowNo").val();
					 var rNo = Number(rowNo)+1;
					 var total = 0;
					 var html = '<tr id="designDocumentRow'+rNo+'">'
								 +'<td data-head="File Type " class="input-field">'
										+'<select  name="design_file_typess"  id="design_file_types'+rNo+'"  class="validate-dropdown searchable">'
					    					+ '<option value="" >Select</option>'
					          			  <c:forEach var="obj" items="${designFileType}">
								  				+ '<option value="${obj.design_file_type }">${obj.design_file_type}</option>'
					           			  </c:forEach>
									+ '</select></td>'
								 +'<td data-head="Name " class="input-field"> <input id="designDocumentNames'+rNo+'" name="designDocumentNames" type="text" class="validate" placeholder="Name"> </td>'
								 +'<td data-head="Attachment" class="input-field center-align">'
								 +'<span class="normal-btn">'
								 +'<input type="file" id="designDocumentFiles'+rNo+'" name="designDocumentFiles" style="display:none" onchange="getFileName('+rNo+')" />'
								 +'<label for="designDocumentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
								 +'<input type="hidden" id="designDocumentFileNames'+rNo+'" name="designDocumentFileNames">'
								 +'<span id="designDocumentFileName'+rNo+'" class="filevalue"></span>'
								 +'</span>'
								 +'</td>'
								 +'<td><input type="hidden" id="design_file_ids'+rNo+'" name="design_file_ids"/></td>';
								 
								 var user_role_name = '${sessionScope.USER_ROLE_NAME}';
								 if(user_role_name == 'IT Admin'){
									 html = html +'<td class="mobile_btn_close">'
									 +'<a href="javascript:void(0);" onclick="removeDesignDocument('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
									 +'</td>';
								 }
								 html = html +'</tr>';
					
						 $('#designDocumentTableBody').append(html);
						 $("#documentRowNo").val(rNo);
						 $('.searchable').select2();
				         $("#design_file_ids0").val('');
				} 
				function removeDesignDocument(rowNo){
					$("#designDocumentRow"+rowNo).remove();
				}
				
			  function removeMedia(link,id){
			   	  $('#'+id).val('');
			   	  $(link).prev().text('');
			   	  $(link).css('display','none');
			  }  
			  
			  function getHodList() {
		        	$(".page-loader").show();
		        	var myParams = {};
	                $.ajax({
	                    url: "<%=request.getContextPath()%>/ajax/getHodListForDesignForm",
	                    data: myParams, cache: false,async:false,
	                    success: function (data) {
	                        if (data.length > 0) {
	                            $.each(data, function (i, val) {
	                            	var userName = '';
	 	                        	if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
	 	                        	var hod = "${designDetails.hod }";
	 	                        	if(val.hod == hod ){
	                            		$("#hod").append('<option value="' + val.hod + '" selected>' + $.trim(val.designation) + userName + '</option>');
	 	                        	}else{
	 	                        		$("#hod").append('<option value="' + val.hod + '">' + $.trim(val.designation) + userName + '</option>');
	 	                        	}
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
		        
		        function getDyHodList() {
		        	$(".page-loader").show();
		        	$("#dy_hod option:not(:first)").remove();
		        	var hod = $("#hod").val();
		            var myParams = { hod: hod};
		            $.ajax({
		                url: "<%=request.getContextPath()%>/ajax/getDyHodListForDesignForm",
		                data: myParams, cache: false,async:false,
		                success: function (data) {
		                    if (data.length > 0) {
		                        $.each(data, function (i, val) {
		                        	var userName = '';
	 	                        	if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
	 	                        	var hy_hod = "${designDetails.dy_hod }";
	 	                        	if(val.dy_hod == hy_hod ){
	 	                        		$("#dy_hod").append('<option value="' + val.dy_hod + '" selected>' + $.trim(val.designation) + userName + '</option>');
	 	                        	}else{
			                        	$("#dy_hod").append('<option value="' + val.dy_hod + '">' + $.trim(val.designation) + userName + '</option>');
	 	                        	}
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

		        function getFileName(rowNo){
		    		var filename = $('#designDocumentFiles'+rowNo)[0].files[0].name;
		    	    $('#designDocumentFileName'+rowNo).html(filename);
		    	}
    </script>

</body>
</html>