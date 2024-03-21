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
  		.error-msg label{color:red!important;}   
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
		.input-field.min4{
			min-height:4rem ;
		}
		.fs11px{font-size: 11px !important;}
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */
			#revisionsTableBody tr .input-field .datepicker~button ,
			 #statusTable tr .input-field .datepicker~button{
			    position: relative;
			    top: 0;
			    right: 26px;
			}			
			#revTable .select2-container{
				width:-webkit-fill-available !important;
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
			.filevalue {
			    width: 200%;
			    white-space: break-spaces;
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
		ul {
		  list-style-type: none;
		}
		 #compensation_unitsError{
	   		float:right;	
	    }
	   .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
		.pdr3em{
			padding-right: 3em !important;
		} 
		.pdr4em{
			padding-right: 4em !important;
		}
		.pdr5em{
			padding-right: 5em !important;
		}
		.w85{
			width: 85% !important;
		}
		.w70{
			width: 70% !important;
		}
    </style>
</head>
<body>
 <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
         
         
         <div class="row">
        <div class="col s12 m12" style="margin-bottom:4rem;">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                               	    <c:if test="${action eq 'edit'}">Update Design & Drawing(${designDetails.design_seq_id })</c:if>
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
						   <input type="hidden" id="design_seq_id" name="design_seq_id" value="${designDetails.design_seq_id }">
						   
						   <c:if test="${action eq 'edit'}"> 
						    <div class="row no-mar p-sticky t-48 z-1">
							    <div class="col s12 m12">
							      <ul class="tabs tab-flex" id="menu-center">
							        <li class="tab" ><a class="t-c" href="#workDetails">Work Details</a></li>
							        <li class="tab" ><a class="t-c" href="#drawingDetails">Drawing Details</a></li>
							        <li class="tab hideTab" ><a class="t-c" href="#statusDetails">Drawing Status</a></li>							        
							        <li class="tab" ><a class="t-c" href="#revisionDetails">Revisions</a></li>
							        <li class="tab"><a class="t-c"><label><input type="checkbox" id="box" value="hidden"><span style="color: darkcyan;">Show Optional Fields</span></label></a></li> 
							      </ul>
							    </div>							    
						   </div>
						  </c:if>
						    <div class="container container-no-margin">
                            <div class="section scrollspy" id="workDetails">
                            <div class="row">
						    	<h5 class="center-align m-b-2">Work Details</h5>
						    <c:if test="${action eq 'add'}">	
                                <div class="col s12 m4 l4 input-field ">
                                    <p class="searchable_label"> Project<span class="required">*</span></p>
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
                                        onchange="getStructureTypes();getContractsList(this.value);resetProjectsDropdowns(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             
                            </c:if>
 							<c:if test="${action eq 'edit'}">		                             
	                                <div class="col s12 m4 l4 input-field ">
	                                    <input type="text" value="${designDetails.project_id_fk}- ${designDetails.project_name}" readonly />
								    	<label for="project_id_fk">Project <span class="required">*</span></label>
								    	<input type="hidden" name="project_id_fk" id="project_id_fk" value="${designDetails.project_id_fk}" readonly />
								    </div> 
	                                <div class="col s6 m4 l4 input-field"> 
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
							</div>
							<div class="row">
<%--                                 <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label"> Department <span class="required">*</span></p>
                                     <select name="department_id_fk" id="department_id_fk" class="searchable validate-dropdown">
                                        <option value="" >Select</option>
                                          <c:forEach var="obj" items="${departmentList }">
                                      	    <option value= "${ obj.department_fk}" <c:if test="${designDetails.department_id_fk eq obj.department_fk}">selected</c:if>>${ obj.department_name}</option>
                                          </c:forEach>
                                    </select>
                                     <span id="department_id_fkError" class="error-msg" ></span>
                                </div> --%>
                           
<!--                                 <div class="col s6 m4 l4 input-field">
                                	<p class="searchable_label">Dy HOD <span class="required">*</span></p>
                                	<select name="dy_hod" id="dy_hod" class="validate-dropdown searchable"> 
                         		  			<option value="">Select</option> 
                                	</select>
                                	<span id="dy_hodError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                	<p class="searchable_label">HOD <span class="required">*</span></p>
                                	<select name="hod" id="hod" class="validate-dropdown searchable" > 
                         		  			<option value="">Select</option> 
                                	</select>
                                	<span id="hodError" class="error-msg" ></span>
                                </div> -->
                                 </div>
                                 <div class="row">                       
                                 <div class="col s6 m4 l4 input-field " id="structureRow">
                                    <p class="searchable_label">Structure Type<span class="required">*</span></p>
                                    <select id="structure_type_fk" name="structure_type_fk" class="searchable validate-dropdown" onChange="getStructureIds();">
                                        <option value="" selected>Select</option>
                                 		<c:forEach var="obj" items="${structureTypeList}">
                  						   <option value="${obj.structure_type_fk }" <c:if test="${designDetails.structure_type_fk eq obj.structure_type_fk }">selected</c:if>>${obj.structure_type_fk}</option>
                                       </c:forEach>
                                    </select>
                                    <span id="structure_type_fkError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l4 input-field " id="structureIdRow">
                                    <p class="searchable_label">Structure<span class="required">*</span></p>
                                    <select id="structure_id_fk" name="structure_id_fk" class="searchable validate-dropdown" >
                                        <option value="" selected>Select</option> 
                                        <c:forEach var="obj" items="${structureId }">
                             				<option value="${obj.structure }" <c:if test="${designDetails.structure_id_fk eq obj.structure }">selected</c:if>>${obj.structure }</option>
                           				 </c:forEach>                                  		
                                    </select>
                                    <span id="structure_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field " >
                                    <p class="searchable_label">Component</p>
                                    <input type="text" name="component" id="component" value="${designDetails.component}">
                                    <span id="componentError" class="error-msg" ></span>
                                </div>
                              </div>
                            
                            <div class="row optionalFileds">      
                            <%--  <c:if test="${action eq 'add'}">   --%>                         
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Contract </p>
                                    <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" 
                                     	<c:if test="${action eq 'add'}"> onchange="resetWorksAndProjectsDropdowns();"</c:if> >
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" value= "${ obj.contract_id}" <c:if test="${designDetails.contract_id eq obj.contract_id}">selected</c:if>>${obj.contract_id} <c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                  <div class="col s12 m4 l4 input-field optionalFileds" >
                                    <p class="searchable_label">Prepared By <span class="required"></span></p>
                                    <select class="searchable validate-dropdown" name="prepared_by_id_fk" id="prepared_by_id_fk">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${preparedBy }">
                                      	    <option value= "${ obj.prepared_by_id_fk}" <c:if test="${designDetails.prepared_by_id_fk eq obj.prepared_by_id_fk}">selected</c:if>>${obj.prepared_by_id_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="prepared_by_id_fkError" class="error-msg" ></span>
                                </div>                                
							</div>
							<div class="row optionalFileds">     
                                <div class="col s12 m4 l4 input-field min4">
                                    <p class="searchable_label">Consultant</p>
                                    <input type="text" name="consultant_contract_id_fk" id="consultant_contract_id_fk" maxlength="100" data-length="100" value="${designDetails.consultant_contract_id_fk}">
                                     <span id="consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field min4">
                                    <p class="searchable_label fs-sm-67rem">Proof Consultant </p>
                                    <input type="text" id="proof_consultant_contract_id_fk" name="proof_consultant_contract_id_fk" maxlength="100" data-length="100" value="${designDetails.proof_consultant_contract_id_fk}">
                                    <span id="proof_consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field min4">
                                    <p class="searchable_label fs-sm-67rem">3PVC </p>
                                    <input type="text" id="threepvc" name="threepvc" maxlength="100" data-length="100" value="${designDetails.threepvc}">
                                    <span id="proof_consultant_contract_id_fkError" class="error-msg" ></span>
                                </div>                                
                             
                                </div>
                                <div class="row">
                                <div class="col s6 m4 l4 input-field" id="drawingType">
                                    <p class="searchable_label mb-8">Drawing Type <span class="required">*</span></p>
                                    <select id="drawing_type_fk" name="drawing_type_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                               			<c:forEach var="obj" items="${drawingTypeList}">
                						    <option value="${obj.drawing_type_fk }" <c:if test="${designDetails.drawing_type_fk eq obj.drawing_type_fk }">selected</c:if>>${obj.drawing_type_fk}</option>
                                      	</c:forEach>
                                    </select>
                                    <span id="drawing_type_fkError" class="error-msg" ></span>
                                </div>
                                 <div class="col s6 m4 l6 input-field hideAuthority">
                                    <p class="searchable_label mb-8" >Approval Authority<span class="required">*</span></p>
                                    <select id="approval_authority_fks" name="approval_authority_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>     
                                         <c:forEach var="obj" items="${approvalAuthority }">
                                  				<option value="${obj.approval_authority_fk }" <c:if test="${designDetails.approval_authority_fk eq obj.approval_authority_fk }">selected</c:if>>${obj.approval_authority_fk }</option>
                                				  </c:forEach>                          			
                                    </select>
                                    <span id="approval_authority_fksError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m8 optionalFileds">
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
		                         		<div class="col s6 m6 l4 input-field ">
		                                    <input id="required_date" name="required_date" type="text" class="validate datepicker" value="${designDetails.required_date }">
		                                    <label for="required_date" class="fs-sm-8rem fs-9">Required Date </label>
		                                    <button type="button" id="required_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
		                                    <span id="required_dateError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m6 l4 input-field ">
		                                    <input id="gfc_release_date" name="gfc_released" type="text" class="validate datepicker" value="${designDetails.gfc_released }">
		                                    <label for="gfc_release_date" class="fs-sm-8rem fs-9">GFC Release Date </label>
		                                    <button type="button" id="gfc_release_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
		                                    <span id="gfc_release_dateError" class="error-msg" ></span>
		                                </div>                                	
                                	</div>
                                </div>
                            </div>
						</div>
                            <div class="row section scrollspy" id="drawingDetails">
                            	<h5 class="center-align">Drawing Details</h5>
                                <div class="col s6 m12 input-field" id="hideResponsive">
                                    <textarea id="drawing_title" maxlength="100" data-length="100" name="drawing_title" class="pmis-textarea num pdr5em">${designDetails.drawing_title }</textarea>
                                    <label for="drawing_title">Drawing Title<span class="required">*</span></label>
                                     <span id="drawing_titleError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field optionalFileds">
                                     <input id="contractor_drawing_no" maxlength="100" data-length="100" name="contractor_drawing_no" type="text" class="validate num w70 pdr4em" value="${designDetails.contractor_drawing_no }">
		                             <label for="contractor_drawing_no" >Agency Drawing No </label>
		                             <span id="contractor_drawing_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field" id="hideResponsive1">
                                     <input id="mrvc_drawing_no" maxlength="100" data-length="100" name="mrvc_drawing_no" type="text" class="validate num w70 pdr4em" value="${designDetails.mrvc_drawing_no }">
                                     <label for="mrvc_drawing_no">MRVC Drawing No </label>
                                     <span id="mrvc_drawing_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field optionalFileds">
                                     <input id="division_drawing_no" maxlength="100" data-length="100" name="division_drawing_no" type="text" class="validate num w70 pdr4em" value="${designDetails.division_drawing_no }">
		                             <label for="division_drawing_no">Divisional Drawing No</label>
		                             <span id="division_drawing_noError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m3 input-field optionalFileds">
                                     <input id="hq_drawing_no" maxlength="100" data-length="100" name="hq_drawing_no" type="text" class="validate num w70 pdr4em" value="${designDetails.hq_drawing_no }">
		                             <label for="hq_drawing_no">HQ Drawing No</label>
                                     <span id="hq_drawing_noError" class="error-msg" ></span>
                                </div>
                               
                            </div> 
                      </div>
                            <!-- insurance show hide div  -->
                           <c:if test="${action eq 'edit'}"> 
                          <div class="row no-mar"> 
                          	<div class="col s10 offset-s1">
                           	<div class="fixed-width section scrollspy" id="statusDetails">
                                <h5 class="center-align pos-rel">Drawing Status
                                	<c:if test="${not empty designDetails.designStatusList && fn:length(designDetails.designStatusList) gt 0 }">
                                	<span class="right mob-center">
                             			<p>
									      <label>
									        <input type="checkbox" class="filled-in" checked id="drawing_show_latest" name="drawing_show_latest"/>
									        <span>Show Latest</span>
									      </label>
							   			</p>
	                               	</span>                                	
                                	</c:if>
                                </h5>
                                <div class="table-inside">
                                    <table id="statusTable" class="mdl-data-table mobile_responsible_table">
                                    	<thead>
										    <tr>
										        <th class="max-200">Stage </th>
										        <th class="max-200">Submitted By</th>
										        <th class="max-200">Submitted To </th>
										        <th class="max-200">Purpose of Submission <br>/ Remarks</th>
										        <th>Submitted Date</th>
										        <th>Latest</th>
										        <th>Action</th>
										    </tr>
										</thead>
										<tbody id="statusTableBody">
                                        
                                        <c:choose>
	                                        <c:when test="${not empty designDetails.designStatusList && fn:length(designDetails.designStatusList) gt 0 }">
	                                        	<c:forEach var="statObj" items="${designDetails.designStatusList }" varStatus="index">  
		                                            <tr id="StatusRow${index.count }" class="drawing-rows">   
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
												        <td data-head="Latest" class="input-field center-align">
			                                                <p>
			                                                 	<label> 
			                                                	   <input type="checkbox"  id="drawing_status_checkbox${index.count }" name="latest"  value="${statObj.latest}" class="drawing_status_checkbox" 
			                                                	   <c:if test="${statObj.latest eq 'Yes'}">checked</c:if>/> 
			                                                			<span></span> 
			                                                	</label>
		                                                	</p>
		                                                	  <input type="hidden" id="drawing_status_checkbox${index.count }s"  name="latests" value="${statObj.latest}" class="drawing_status_checkbox" />		                                                	
		                                                </td>
												        </td>
												        <td class="mobile_btn_close">
		                                                    <a  class="btn waves-effect waves-light red t-c " onclick="removeStatusRow('${index.count }');"> <i
		                                                            class="fa fa-close"></i></a>
		                                                </td>
												    </tr>
											    </c:forEach>
                                         	</c:when>
                                           	<c:otherwise>
                                           		<tr id="StatusRow0" class="drawing-rows">
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
											          <td data-head="Latest" class="input-field center-align">
			                                                <p>
			                                                 	<label> 
			                                                	   <input type="checkbox"  id="drawing_status_checkbox0" name="latest"  value="" class="drawing_status_checkbox" /> 
			                                                			<span></span> 
			                                                	</label>
		                                                	</p>
		                                                	  <input type="hidden" id="drawing_status_checkbox0s"  name="latests" value="" class="drawing_status_checkbox" />		                                                	
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
                                	<span id="drawing_status_error" class="error-msg" ></span>
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
                           </div>
                          </div> 
                          </c:if>  
                          
                           
                            <div style="width:90%;padding-left:10%;"> 
                             <div class="row fixed-width section scrollspy" id="revisionDetails">
                                <h5 class="center-align pos-rel">Revision Details 
                                <c:if test="${not empty designDetails.designRevisions && fn:length(designDetails.designRevisions) gt 0 }">
                                	<span class="right mob-center">
                             			<p>
									      <label>
									        <input type="checkbox" class="filled-in" checked id="rev_show_current" name="rev_show_current"/>
									        <span>Show Current</span>
									      </label>
							   			</p>
	                               	</span>                                
                                </c:if>
	                             </h5>
                                <div class="table-inside">
                                    <table id="revTable" class="mdl-data-table mobile_responsible_table">
                                        <thead>
                                            <tr>
                                                <th>Revision No. </th>
                                                <th>Drawing No.<span class="required">*</span> </th>                                               
                                                <th>Correspondence Letter No.<span class="required">*</span></th>
                                                <th>Revision Date<span class="required">*</span></th>
                                                <th>Revision Status<span class="required">*</span></th>
                                                <th>Remarks</th>
                                                <th>Upload File<span class="required">*</span></th>
                                                <th class="no-sort">Current</th>
                                                 <th class="no-sort">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="revisionsTableBody">
                                        
                                        <c:choose>
	                                        <c:when test="${not empty designDetails.designRevisions && fn:length(designDetails.designRevisions) gt 0 }">
	                                        	<c:forEach var="revObj" items="${designDetails.designRevisions }" varStatus="index">  
		                                            <tr id="revisionRow${index.count }" class="revision-rows">                                                
		                                                <td data-head="Revision" class="input-field">
		                                                    <input id="revisions${index.count }" maxlength="25" data-length="25" name="revisions" type="text" class="validate"
		                                                        placeholder="Revision" value="${revObj.revision }" readonly>                                                        
		                                                </td>
		                                                <td data-head="Revision" class="input-field">
		                                                    <input id="drawing_no${index.count }" maxlength="25" data-length="25" name="drawing_nos" type="text" class="validate"
		                                                        placeholder="drawing no" value="${revObj.drawing_no }" >                                                        
		                                                </td>
		                                                <td data-head="Revision" class="input-field">
		                                                    <input id="correspondence_letter_no${index.count }" maxlength="25" data-length="25" name="correspondence_letter_nos" type="text" class="validate"
		                                                        placeholder="correspondence letter no" value="${revObj.correspondence_letter_no }" >                                                        
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
		                                                    <input id="remarkss${index.count }" maxlength="100" data-length="100" name="remarkss" type="text" class="validate num pdr4em w70"
		                                                        placeholder="Remarks" value="${revObj.remarks }">
		                                                </td>
		                                                
	                                                    <td data-head="Attachment" class="input-field">
	                                                        <span class="normal-btn">
	                                                            <input type="file" id="uploadFiles${index.count }" name="uploadFiles"
	                                                                style="display:none" onchange="getUploadFileName('${index.count }')"/>
	                                                            <label for="uploadFiles${index.count }" class="btn bg-m"><i
	                                                                    class="fa fa-paperclip"></i></label>
	                                                            <input type="hidden" id="uploadFileNames${index.count }" name="uploadFileNames" value="${revObj.upload_file }">
	                                                             <span id="uploadFileName${index.count }" class="filevalue">${revObj.upload_file}</span>
	                                                          </span>
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
	                                        	<tr id="revisionRow0" class="revision-rows">                                                
	                                                <td data-head="Revision" class="input-field">
	                                                    <input id="revisions0" maxlength="25" data-length="25" readonly name="revisions" type="text" class="validate num w70 pdr4em"
	                                                        placeholder="Revision" value="R1">                                                        
	                                                </td>
	                                                <td data-head="Revision Date" class="input-field">
	                                                    <input id="drawing_no0" name="drawing_nos" type="text">
	                                                </td>
	                                               
	                                                <td data-head="Revision Status" class="input-field">
	                                                    <input id="correspondence_letter_no0" name="correspondence_letter_nos" type="text">
	                                                </td>
	                                                <td data-head="Revision Date" class="input-field">
	                                                    <input id="revision_date0" name="revision_dates" type="text" class="validate datepicker"
	                                                        placeholder="Revision Date">
	                                                    <button type="button" id="revision_date0_icon" class="datepicker-button"><i
	                                                            class="fa fa-calendar"></i></button>	                                                
	                                                </td>
	                                                <td data-head="Revision Date" class="input-field">
	                                                    <select class="searchable" id="revision_status_fks0" name="revision_status_fks">
	                                                        <option value="" selected>Select </option>
	                                                          <c:forEach var="obj" items="${revisionStatuses }">
			                                    				<option value="${obj.revision_status }">${obj.revision_status }</option>
			                                  				  </c:forEach>
	                                                    </select>	                                                
	                                                </td>	
                                                                                               
	                                                <td data-head="Remarks" class="input-field">
	                                                    <input id="remarkss0" maxlength="100" data-length="100" name="remarkss" type="text" class="validate num w70 pdr4em"
	                                                        placeholder="Remarks">
	                                                </td>
                                                    <td data-head="Attachment" class="input-field">
                                                        <span class="normal-btn">
                                                            <input type="file" id="uploadFiles0" name="uploadFiles"
                                                                style="display:none" onchange="getUploadFileName('0')"/>
                                                            <label for="uploadFiles0" class="btn bg-m"><i
                                                                    class="fa fa-paperclip"></i></label>
                                                            <input type="hidden" id="uploadFileNames0" name="uploadFileNames" value="">
                                                            <span id="uploadFileName0" class="filevalue"></span>
                                                        </span>
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
                            
                               <div id="errorText" style="color:red;"></div>
                            
                            </div>  
                                  
						</div>	  
 						<div class="container container-no-margin">
 

                            <div class="row optionalFileds">
                                <div class="col s12 m12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea pdr5em num" maxlength="1000" data-length="1000">${designDetails.remarks }</textarea>
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
    $(document).ready(function() {
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
        
        if("${designDetails.structure_type_fk}"!="")
       	{
        	//getStructureIds();
        	$('#structure_id_fk').val("${designDetails.structure_id_fk}");
       	}        
        
        if("${designDetails.structure_type_fk}"!="" && "${designDetails.structure_id_fk}"!="")
       	{
        	//getComponents();
        	$('#component').val("${designDetails.component}");
       	}
    	      
    });
	$(".num").characterCounter();
	
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
	    
	    $(document).on('change', '.drawing_status_checkbox', function () {
	    	var that=this;
	    	var itemId=$( that ).attr('id');
	    	$(".drawing_status_checkbox").prop("checked",false);
	    	$(".drawing_status_checkbox").val("No")
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
            var state = '${action}';
            if(state == 'edit'){
            	  // #############  New chnages #########################
	            $('.optionalFileds').hide();
	            $('.hideTab').hide();
	            $('#structureRow,#structureIdRow').removeClass('l4').addClass('l6'); 
	            $('#hideResponsive').removeClass('m12').addClass('m6');
	            $('#hideResponsive1').removeClass('m3').addClass('m6'); 
	            $('#mrvc_drawing_no').removeClass('w70').addClass('w85');
	            $('#drawingType').removeClass('l4').addClass('l6');
	            $('#hideResponsive1').css("bottom","-1rem");
	    		$('.hideAuthority').show();
	    		$('#approval_authority_fk').prop('disabled', true);
    		// ############# #########################
            }else{
            	$('#approval_authority_fks').prop('disabled', true);
        		$('.hideAuthority').hide();
            }
			
            //revision detail rows show hide based on show current 
            $("#rev_show_current").click(function(){
            	if($('input[name="rev_show_current"]').is(':checked')){
              	  	$(".revision-rows").hide();
              	  	$('.revision_status_checkbox[type="checkbox"]').each(function(){
              	  		if($(this).is(':checked')){
              	  			$('#revisionRow'+$(this).attr('id').split('_checkbox')[1]).show();
              	  		}
              	  	});
              	}else{
              		$(".revision-rows").show();
              	}
            });
            if($('input[name="rev_show_current"]').is(':checked')){
            	if($('.revision_status_checkbox[type="checkbox"]').length >1){
	          	  	$(".revision-rows").hide();            		
            	}
          	  	$('.revision_status_checkbox[type="checkbox"]').each(function(){
        	  		if($(this).is(':checked')){
        	  			$('#revisionRow'+$(this).attr('id').split('_checkbox')[1]).show();
        	  		}
        	  	});
          	}else{
          		$(".revision-rows").show();
          	}
            
            $("#drawing_show_latest").click(function(){
            	if($('input[name="drawing_show_latest"]').is(':checked')){
            		$(".drawing-rows").hide();
              	  	$('.drawing_status_checkbox[type="checkbox"]').each(function(){
              	  		if($(this).is(':checked')){
              	  			$('#StatusRow'+$(this).attr('id').split('_checkbox')[1]).show();
              	  		}
              	  	});
              	}else{
              		$(".drawing-rows").show();
              	}
            });
            if($('input[name="drawing_show_latest"]').is(':checked')){
            	if($('.drawing_status_checkbox[type="checkbox"]').length >1){
	          	  	$(".drawing-rows").hide();            		
            	}
          	  	$('.drawing_status_checkbox[type="checkbox"]').each(function(){
        	  		if($(this).is(':checked')){
        	  			$('#StatusRow'+$(this).attr('id').split('_checkbox')[1]).show();
        	  		}
        	  	});
          	}else{
          		$(".drawing-rows").show();
          	}
            
          /*   $("#drawing_show_latest").click(function(){
            	if($('input[name="drawing_show_latest"]').is(':checked')){
            		if($('[name="submitted_dates"]').length>1){
    	            	$(".drawing-rows").hide();
                	}        	  
              	  	var dates=[]; var plain_dates=[];
            	  	$('[name="submitted_dates"]').each(function(){
            	  		var dt=$(this).val().split('-');
            	  		dates.push(new Date(dt[2],dt[1]-1,dt[0]));
            	  		plain_dates.push($(this).val());
            	  	});
            	  	const maxDate = new Date(Math.max.apply(Math, dates));
					var plain_maxDate=maxDate.getDate()  + "-" + (maxDate.getMonth()+1) + "-" + maxDate.getFullYear();
					if( plain_dates.indexOf(plain_maxDate) >= 0){
						var rNo = plain_dates.indexOf(plain_maxDate)+1;
						$('#StatusRow'+rNo).show();
					}              	  	     	  	
              	}else{
              		$(".drawing-rows").show();
              	}
            });
            
            if($('input[name="drawing_show_latest"]').is(':checked')){
            	if($('[name="submitted_dates"]').length>1){
	            	$(".drawing-rows").hide();
            	}
          	  	var dates=[]; var plain_dates=[];
        	  	$('[name="submitted_dates"]').each(function(){
        	  		var dt=$(this).val().split('-');
        	  		dates.push(new Date(dt[2],dt[1]-1,dt[0]));
        	  		plain_dates.push($(this).val());
        	  	});
        	  	const maxDate = new Date(Math.max.apply(Math, dates));
				var plain_maxDate=maxDate.getDate()  + "-" + (maxDate.getMonth()+1) + "-" + maxDate.getFullYear();
				if( plain_dates.indexOf(plain_maxDate) >= 0){
					var rNo = plain_dates.indexOf(plain_maxDate)+1;
					$('#StatusRow'+rNo).show();
				}      	  	
          	}else{
          		$(".drawing-rows").show();
          	} 
            */
            
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
        /*     
            var projectId = "${designDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
            var work_id_fk = "${designDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getWorksList(work_id_fk);
            } */
          
           getContractsList();
            
           $('#box').prop('checked', true).trigger("change");
   		$('#box').val('show');
   		$('.optionalFileds').show();
   		$('.hideTab').show();
   		$('#structureRow,#structureIdRow').removeClass('l6').addClass('l4');
   		$('#hideResponsive').removeClass('m6').addClass('m12'); 
   		$('#hideResponsive1').removeClass('m6').addClass('m3');
   		$('#mrvc_drawing_no').removeClass('w70').addClass('w85');
   		$('#drawingType').removeClass('l6').addClass('l4');
   		$('#hideResponsive1').css("bottom","");
   		$('#approval_authority_fk').prop('disabled', false);
   		$('#approval_authority_fks').prop('disabled', true);
   		$('.hideAuthority').hide();
   		
        });
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
        }       

      
    
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
            work_id_fk = $("#work_id_fk").val();
            $("#contract_id_fk option:not(:first)").remove();
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
           
        }
        
        function getStructureTypes()
        {
        	$(".page-loader").show();
            var work_id_fk = $("#work_id_fk").val();
            $("#structure_type_fk option:not(:first)").remove();
                var myParams = { work_id_fk: work_id_fk};
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getStructureTypesforDesign",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var structure_type_fk = "${designDetails.structure_type_fk }";
                                if ($.trim(structure_type_fk) != '' && val.structure_type_fk == $.trim(structure_type_fk)) {
                                	$("#structure_type_fk").append('<option value="' + val.structure_type_fk + '" selected>' + val.structure_type_fk + '</option>');
                                } else {
                                	$("#structure_type_fk").append('<option value="' + val.structure_type_fk + '">' + val.structure_type_fk + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });          	
        }       
        
        function getStructureIds()
        {
        	$(".page-loader").show();
            var structure_type_fk = $("#structure_type_fk").val();
            $("#structure_id_fk option:not(:first)").remove();
                var myParams = { structure_type_fk: structure_type_fk};
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getStructureIdsforDesign",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var structure_id_fk = "${designDetails.structure_id_fk }";
                                if ($.trim(structure_id_fk) != '' && val.structure_id_fk == $.trim(structure_id_fk)) {
                                	$("#structure_id_fk").append('<option value="' + val.structure_name + '" selected>' + val.structure_name + '</option>');
                                } else {
                                	$("#structure_id_fk").append('<option value="' + val.structure_name + '">' + val.structure_name + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });          	
        }
        
        
        function getComponents()
        {
        	$(".page-loader").show();
            var structure_type_fk = $("#structure_type_fk").val();
            var structure_id_fk = $("#structure_id_fk").val();
            $("#component option:not(:first)").remove();
                var myParams = { structure_type_fk: structure_type_fk, structure_id_fk:structure_id_fk};
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getComponentsforDesign",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var component = "${designDetails.component }";
                                if ($.trim(component) != '' && val.component == $.trim(component)) {
                                	$("#component").append('<option value="' + val.component + '" selected>' + val.component + '</option>');
                                } else {
                                	$("#component").append('<option value="' + val.component + '">' + val.component + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });        	
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
	  			
	  			$('form input[name=drawing_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=correspondence_letter_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=uploadFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	  			
	  			
	  			
	  			
	  			$('form input[name=stage_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_bys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_tos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submssion_purposes]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$("#errorText").html("");
	  			var rowCount = $('#revTable tbody tr').length;
	  			for(var i = 0; i < rowCount; i++) {
	  			    if ($("#drawing_no" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Drawing No. in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#correspondence_letter_no" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Correspondence Letter No. in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#revision_date" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Revision Date in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#revision_status_fks" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Revision Status in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#uploadFile" + i).val() == "") {
	  			        $("#errorText").html("Please Upload File in row "+(i+1));
	  			        return false;
	  			    }
	  			}    			
	  			
	  			document.getElementById("designForm").submit();	
        	}
    	}
    
    	function updateDesign(){	
    		
  			
    		$("#drawing_status_error").html("");
    		var rowCount = $("#statusTableBody tr").length;
    		if(rowCount>0)
   			{
   					for(var u=0;u<rowCount;u++)
					{
   								u=u+1;
   								if($("#submssion_purpose"+u).val()!=null && $("#submssion_purpose"+u).val()!="" && $("#submssion_purpose"+u).val()!=0)
   								{
   										if($("#stage"+u).val()==null || $("#stage"+u).val()=="" || $("#stage"+u).val()==0)
   										{
   											$("#drawing_status_error").html("Please enter Stage in row "+u);
   											return false;
  										}
   										if($("#submitted_by"+u).val()==null || $("#submitted_by"+u).val()=="" || $("#submitted_by"+u).val()==0)
   										{
   											$("#drawing_status_error").html("Please enter Submitted by in row "+u);
   											return false;
  										}
   										if($("#submitted_to"+u).val()==null || $("#submitted_to"+u).val()=="" || $("#submitted_to"+u).val()==0)
   										{
   											$("#drawing_status_error").html("Please enter Submitted to in row "+u);
   											return false;
  										}
   										if($("#submitted_date"+u).val()==null || $("#submitted_date"+u).val()=="" || $("#submitted_date"+u).val()==0)
   										{
   											$("#drawing_status_error").html("Please enter Submitted Date in row "+u);
   											return false;
  										}   										
   								}
					
					}
   			} 			
        	
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
	  			
	  			$('form input[name=drawing_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=correspondence_letter_nos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=uploadFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			
	  			$('form input[name=stage_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_bys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_tos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submitted_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=submssion_purposes]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			
	  			$("#errorText").html("");
	  			var rowCount = $('#revTable tbody tr').length;
	  			for(var i = 0; i < rowCount; i++) {
	  			    if ($("#drawing_no" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Drawing No. in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#correspondence_letter_no" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Correspondence Letter No. in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#revision_date" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Revision Date in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#revision_status_fks" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Revision Status in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#uploadFile" + i).val() == "") {
	  			        $("#errorText").html("Please Upload File in row "+(i+1));
	  			        return false;
	  			    }
	  			}  	  			
	  			
	  			
	  			
	  			
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
    				 		required: false
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
    				 		required: false
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
    				 	  },"component": {
    				 		required: false
    				 	  },"approving_railway": {
    				 		required: true
    				 	  },"mrvc_drawing_no": {
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
	   			 	  	 },"mrvc_drawing_no": {
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
	   				 	 },"component": {
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
     			 	    }else if (element.attr("id") == "mrvc_drawing_no" ){
   			 		     document.getElementById("mrvc_drawing_noError").innerHTML="";
			 			 error.appendTo('#mrvc_drawing_noError');
			 	    	}else if (element.attr("id") == "work_id_fk" ){
    			 		     document.getElementById("work_id_fkError").innerHTML="";
    			 			 error.appendTo('#work_id_fkError');
    			 	    }else if (element.attr("id") == "approving_railway" ){
   			 		     document.getElementById("approving_railwayError").innerHTML="";
			 			 error.appendTo('#approving_railwayError');
			 	    	}else if (element.attr("id") == "approval_authority_fk" ){
	   			 		     document.getElementById("approval_authority_fkError").innerHTML="";
				 			 error.appendTo('#approval_authority_fkError');
			 	    	}else if (element.attr("id") == "approval_authority_fks" ){
	   			 		     document.getElementById("approval_authority_fksError").innerHTML="";
				 			 error.appendTo('#approval_authority_fksError');
			 	    	}else if (element.attr("id") == "structure_id_fk" ){
	   			 		     document.getElementById("structure_id_fkError").innerHTML="";
				 			 error.appendTo('#structure_id_fkError');
			 	    	}else if (element.attr("id") == "component" ){
	   			 		     document.getElementById("componentError").innerHTML="";
				 			 error.appendTo('#componentError');
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
                     }validateDesign();
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
            
	  		function addRevisionRow()
	  		{
	  			$("#errorText").html("");
	  			var rowCount = $('#revTable tbody tr').length;
	  			for(var i = 0; i < rowCount; i++) {
	  			    if ($("#drawing_no" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Drawing No. in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#correspondence_letter_no" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Correspondence Letter No. in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#revision_date" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Revision Date in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#revision_status_fks" + i).val() == "") {
	  			        $("#errorText").html("Please Enter Revision Status in row "+(i+1));
	  			        return false;
	  			    }
	  			    if ($("#uploadFile" + i).val() == "") {
	  			        $("#errorText").html("Please Upload File in row "+(i+1));
	  			        return false;
	  			    }
	  			}
		
		      var rowNo = $("#rowNo").val();
		      var rNo = Number(rowNo)+1;
		      var rowLr=Number(rNo)+1;
		      
		      var html ='<tr id="revisionRow'+rNo+'" class="revision-rows"> '
				      +'<td data-head="Revision No." class="input-field"> <input id="revisions'+rNo+'" maxlength="25" data-length="25" name="revisions" type="text" value="R'+ rowLr + '" class="validate num w70 pdr4em" placeholder="Revision" readonly></td>'
				      +'<td data-head="Drawing No." class="input-field"><input id="drawing_no'+rNo+'" name="drawing_nos" type="text" </td>'
				      +'<td data-head="Correspondence Letter No." class="input-field"><input id="correspondence_letter_no'+rNo+'" name="correspondence_letter_nos" type="text"></td>'
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
					  +'<td data-head="Remarks" class="input-field"> <input id="remarkss'+rNo+'" maxlength="100" data-length="100" name="remarkss" type="text" class="validate num pdr4em w70" placeholder="Remarks"></td>'
						 +'<td data-head="Attachment" class="input-field">'
						 +'<span class="normal-btn">'
						 +'<input type="file" id="uploadFiles'+rNo+'" name="uploadFiles" style="display:none" onchange="getUploadFileName('+rNo+')" />'
						 +'<label for="uploadFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
						 +'<input type="hidden" id="uploadFileNames'+rNo+'" name="uploadFileNames">'
						 +'<span id="uploadFileName'+rNo+'" class="filevalue"></span>'
						 +'</span>'
						 +'</td>'
					  +'<td data-head="Status" class="input-field center-align"><p>	<label> <input type="checkbox" id="revision_status_checkbox'+rNo+'" class="revision_status_checkbox" name="current"/><span></span> </label> </p>  <input type="hidden" id="revision_status_checkbox'+rNo+'s"  name="currents" value="No" class="revision_status_checkbox" /></td>'
					  +'<td class="mobile_btn_close"><a class="btn waves-effect waves-light red t-c " onclick="removeRevision('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
						
					  $('#revisionsTableBody').append(html);
					  $("#rowNo").val(rNo);
					  $('#remarkss'+rNo).characterCounter();;
					  $('#revisions'+rNo).characterCounter();;
				      $('.searchable').select2();
				      
			  }
			  
			  function removeRevision(rowNo){
			    	$("#revisionRow"+rowNo).remove();
			    	var rm=$("#rowNo").val();
			    	$("#rowNo").val(Number(rm)-1);
			    	
			  }
			  
			  function getUploadFileName(rowNo) {
					var filename = $('#uploadFiles'+rowNo)[0].files[0].name;
				    $('#uploadFileName'+rowNo).html(filename);
				    $('#uploadFileNames'+rowNo).val(filename);
				}
			  
			  function addStatusRow(){
					
			      var rowNo = $("#statusRowNo").val();
			      var rNo = Number(rowNo)+1;
			      var html ='<tr id="StatusRow'+rNo+'" class="drawing-rows"> '
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
			      			+' <td data-head="Latest" class="input-field center-align"> <p><label> <input type="checkbox"  id="drawing_status_checkbox'+rNo+'" name="latest"  value="" class="drawing_status_checkbox" '
                        	+'/> <span></span> </label> </p> <input type="hidden" id="drawing_status_checkbox'+rNo+'s"  name="latests" value="" class="drawing_status_checkbox" />'		                                                	
                    		+'</td>'
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
									+ '</select><span id="design_file_typess'+rNo+'Error" class="error-msg" ></span></td>'
								 +'<td data-head="Name " class="input-field"> <input id="designDocumentNames'+rNo+'" maxlength="25" data-length="25" name="designDocumentNames" type="text" class="validate num w85 pdr4em" placeholder="Name"><span id="designDocumentNames'+rNo+'Error" class="error-msg" ></span> </td>'
								 +'<td data-head="Attachment" class="input-field center-align">'
								 +'<span class="normal-btn">'
								 +'<input type="file" id="designDocumentFiles'+rNo+'" name="designDocumentFiles" style="display:none" onchange="getFileName('+rNo+')" />'
								 +'<label for="designDocumentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
								 +'<input type="hidden" id="designDocumentFileNames'+rNo+'" name="designDocumentFileNames">'
								
								 +'<span id="designDocumentFileName'+rNo+'" class="filevalue"></span>'
								 +'</span> <span id="designDocumentFiles'+rNo+'Error" class="error-msg" ></span>'
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
						 $('#designDocumentNames'+rNo).characterCounter();;
				         $("#design_file_ids0").val('');
				         
				         $('select[name=design_file_typess]').change(function(key, element){
								$("select[name=design_file_typess]").each(function(){
									var idNo = (this.id).replace('design_file_types',''); 
					        		if($.trim(this.value) != ""){ 
					        			$('#design_file_typess'+idNo+'Error').text('');
									}
					            });
								
							});
					        $('[name=designDocumentFiles]').change(function(key, element){
								$("[name=designDocumentFiles]").each(function(){
									var idNo = (this.id).replace('designDocumentFiles',''); 
									var doc = $('#designDocumentFileNames'+idNo).val();
									if($.trim(doc) != ""){  
					        			$('#designDocumentFiles'+idNo+'Error').text('');
									}else if($.trim(doc) == "" && $('#designDocumentFiles'+idNo).val() != ""){
						 				$('#designDocumentFiles'+idNo+'Error').text('');
						 			}
					            });
								
							});
					        $('[name=designDocumentNames]').keyup(function(key, element){
					 			$("[name=designDocumentNames]").each(function(){
					 			var idNo = (this.id).replace('designDocumentNames',''); 
					 			if($.trim(this.value) != "" ){ 
					       			$('#designDocumentNames'+idNo+'Error').text('');
					 			}
					           });
					 		});
				         
				} 
				function removeDesignDocument(rowNo){
					$("#designDocumentRow"+rowNo).remove();
				}
				
			  function removeMedia(link,id){
			   	  $('#'+id).val('');
			   	  $(link).prev().text('');
			   	  $(link).css('display','none');
			  }  
			  

		        


		        function getFileName(rowNo){
		    		var filename = $('#designDocumentFiles'+rowNo)[0].files[0].name;
		    	    $('#designDocumentFileName'+rowNo).html(filename);
		    	}
		        
		        function validateDesign(){
					var flag = true;
					$("select[name=design_file_typess]").each(function(){
						var idNo = (this.id).replace('design_file_types','');
						var design_file_type = $("#design_file_types"+idNo).val();
						var name = $("#designDocumentNames"+idNo).val();
						var file = $("#designDocumentFiles"+idNo).val();
						var doc = $('#designDocumentFileNames'+idNo).val();
						
			       		if($.trim(design_file_type) == "" &&  name == "" &&  file == ""){
			       			flag = true;
						}else{
							if(design_file_type == ""){
								$('#design_file_typess'+idNo+'Error').text('Requried');
								$('#design_file_types'+idNo).slideDown(100,function(){
									$(this).focus();
								});
							}
							if(name == ""){
								$('#designDocumentNames'+idNo+'Error').text('Requried');
								$('#designDocumentNames'+idNo).slideDown(100,function(){
									$(this).focus();
								});
							}
							if(doc == "" && file == ""){
								$('#designDocumentFiles'+idNo+'Error').text('Requried');
								$('#designDocumentFiles'+idNo+'Error').slideDown(100,function(){
									$(this).focus();
								});
							}
							
							flag = false;
							}
			       		a = [];
	     				$('#designDocumentTableBody .error-msg').each(function(){a.push(this.innerHTML)});
	     				console.log(a)
	     				var found = a.includes('Requried');
	     				if (!found){
	     					flag = true;
	     				}else{
	     					flag = false;
	     				}
						});
					return flag;
					} 
		        
		        $('select[name=design_file_typess]').change(function(key, element){
					$("select[name=design_file_typess]").each(function(){
						var idNo = (this.id).replace('design_file_types',''); 
		        		if($.trim(this.value) != ""){ 
		        			$('#design_file_typess'+idNo+'Error').text('');
						}
		            });
					
				});
		       /* $('[name=uploadFiles]').change(function(key, element){
					$("[name=uploadFiles]").each(function(){
						var idNo = (this.id).replace('uploadFiles',''); 
						var doc = $('#uploadFileNames'+idNo).val();
						if($.trim(doc) != ""){  
		        			$('#uploadFiles'+idNo+'Error').text('');
						}else if($.trim(doc) == "" && $('#uploadFiles'+idNo).val() != ""){
			 				$('#uploadFiles'+idNo+'Error').text('');
			 			}
		            });
					
				});	*/	        
		        
		        $('[name=designDocumentFiles]').change(function(key, element){
					$("[name=designDocumentFiles]").each(function(){
						var idNo = (this.id).replace('designDocumentFiles',''); 
						var doc = $('#designDocumentFileNames'+idNo).val();
						if($.trim(doc) != ""){  
		        			$('#designDocumentFiles'+idNo+'Error').text('');
						}else if($.trim(doc) == "" && $('#designDocumentFiles'+idNo).val() != ""){
			 				$('#designDocumentFiles'+idNo+'Error').text('');
			 			}
		            });
					
				});
		        $('[name=designDocumentNames]').keyup(function(key, element){
		 			$("[name=designDocumentNames]").each(function(){
		 			var idNo = (this.id).replace('designDocumentNames',''); 
		 			if($.trim(this.value) != "" ){ 
		       			$('#designDocumentNames'+idNo+'Error').text('');
		 			}
		           });
		 		});
		        $('#box').change(function() { 
		        	var option = $('#box').val();
		        	if(option == "hidden"){
		        		$('#box').val('show');
		        		$('.optionalFileds').show();
		        		$('.hideTab').show();
		        		$('#structureRow,#structureIdRow').removeClass('l6').addClass('l4');
		        		$('#hideResponsive').removeClass('m6').addClass('m12'); 
		        		$('#hideResponsive1').removeClass('m6').addClass('m3');
		        		$('#mrvc_drawing_no').removeClass('w70').addClass('w85');
		        		$('#drawingType').removeClass('l6').addClass('l4');
		        		$('#hideResponsive1').css("bottom","");
		        		$('#approval_authority_fk').prop('disabled', false);
		        		$('#approval_authority_fks').prop('disabled', true);
		        		$('.hideAuthority').hide();
		        	}else{
		        		$('#box').val('hidden');
		        		$('.optionalFileds').hide();
		        		$('.hideTab').hide();
		        		$('#structureRow,#structureIdRow').removeClass('l4').addClass('l6');
		        		$('#hideResponsive').removeClass('m12').addClass('m6'); 
		        		$('#hideResponsive1').removeClass('m3').addClass('m6');
		        		$('#mrvc_drawing_no').removeClass('w85').addClass('w70');
		        		$('#drawingType').removeClass('l4').addClass('l6');
		        		$('#hideResponsive1').css("bottom","-1rem");
		        		$('#approval_authority_fk').prop('disabled', true);
		        		$('#approval_authority_fks').prop('disabled', false);
		        		$('.hideAuthority').show();
		        	
		        	}
		        	
		        });
    </script>

</body>
</html>