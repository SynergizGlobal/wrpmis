<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Structure - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Structure - Update Forms - PMIS</c:if>
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
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" > 	
    <style>

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
.input-field p.searchable_label{
	text-align:left;
}
.mdl-data-table .amount-dropdown .select-wrapper, .mdl-data-table .amount-dropdown .amount-symbol{
	top:0.25rem;margin-left:0;
}
/* Chrome, Safari, Edge, Opera */
input[type=number]::-webkit-outer-spin-button,
input[type=number]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
td label.btn.bg-m{
	height: 2.5rem;
    line-height: 2.5rem;
    margin-top: 0.5rem;
    padding: 0 12px;
}
.modal{
	max-height:90%;
	top:5% !important;
}
        #structure_details td>.select2-container{
        	min-width:300px;
        	max-width:300px;
        }
        #structure_details .select2-container--default .select2-selection--single {
        	background-color:transparent;
        }
         .fixed-width {
            width: 100%;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
         .bd-none{border:none !important;background: transparent}
		@media(max-width: 2200px){
		.table-add{position: absolute; width:52%;}
		.add-align{position: absolute;
   					 margin-top: -5.8em;
   					 margin-left: 26%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    		td >a.add-align{margin-left:57% !important;}
    	}
    	@media(max-width: 800px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;width:100%;}
    	}
        @media only screen and (max-width: 769px) {
            #structure_details .select2-container{
                min-width: inherit;
                max-width: inherit;
            }
            .disp-in-table{
            	display:inline-table !important;
            }
            .disp-in-table >.btn{
            	float:left;
            }   
            #structure_details .datepicker~button {
	            top: 0;
	            bottom:0;
	            right:26px;
	        }                    
        }
       
		.my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		.mob-btn{
			height: 1.7rem;
		    line-height: 1.7rem;
		    padding: 0 0.5rem;
		    font-size: .97rem;
		    border-radius:8px;
		}
		.mob-btn i{
		    vertical-align: bottom;
		    font-size: .9rem;
		}
		td.no-pad{
			padding:0 !important;
		}
		.mob-add-btn{
			position:absolute;			
		}
		.internal-table{
			border-left:1px solid rgba(0,0,0,0.12);
			border-right:1px solid rgba(0,0,0,0.12);
			/* background-color:#EBFFFF; */
		}
		.internal-table tbody >tr, .internal-table tr >td{
			border:none;
			padding-top:0 !important;
			padding-bottom:0 !important;
		}
		.internal-table .mob-add-btn{
			height:0;
		}
		.internal-table .mob-add-btn >td{
			position: absolute;
		    right: 0;
		    padding: 0;
		    padding-left: 100%;
		    height: 0;
		}
		.internal-table .mob-add-btn >td>a.mob-btn{
			position: absolute;
		    top: -2.5rem;
		    right: -2.2rem;
		}
		.max-w-350{
			max-width:350px;
			width:340px;
		}
		.mobile_responsible_table > tbody >tr >td{
			border-bottom: 1px solid rgba(0,0,0,.35);
		}
		@media only screen and (min-width: 769px) {
			td.mobile_btn_close.line{
				text-align:right;
			}
			td.mobile_btn_close.line:before{
				content: '';
			    border: 1px solid #ececec;
			    position: absolute;
			    left: 3rem;
			    top: 0;
			    height: 100%;
			}
			#structure_details .text-right{
				text-align:right;
			}
		}
		.select2-container{
			z-index:1003;
		}
		.no-z+.select2-container{
			z-index:1;
		}
		.plus_btn{
			position:relative;
			height:0;
		}
		.plus_btn >.btn{
			position:absolute;
			right:-4rem;
			bottom:1rem;
		}
		.modal-content .mdl-data-table thead tr:hover{
			background-color:#007a7a;
		}
    </style>
</head>
<body>
  <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                 	 <c:if test="${action eq 'edit'}">Update Structure </c:if>
									 <c:if test="${action eq 'add'}"> Add Structure </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   		    <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-structure" id="structureForm" name="structureForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                         </c:if>
				             <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-structure" id="structureForm" name="structureForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
							 </c:if>
                        <div class="container container-no-margin">
                        <input type="hidden" name= "structure_id" id="structure_id" value="${structuresListDetails.structure_id}" />
                        <c:if test="${action eq 'add'}">
                            <div class="row"> 
                                <div class="col s6 offset-m2 m4 input-field">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown no-z" id="project_id_fk" name="project_id_fk"  
                               	  		 onchange="getWorksList(this.value);">
                                    		 <option value="" >Select</option>
                                      		 <c:forEach var="obj" items="${projectsList }">
                                         			 <option value="${obj.project_id_fk }">${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                      		 </c:forEach>
		                             </select>
                               		 <span id="project_idError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Work </p>
                                     <select class="searchable validate-dropdown no-z" id="work_id_fk" name="work_id_fk"
                                   		  onchange="resetProject();">  <!-- getContractsList(this.value); -->
                                   		  <option value="" >Select</option>
                                   		  	 <c:forEach var="obj" items="${worksList }">
	                                      	   		<option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                      	 </c:forEach>
	                                  </select>
                                   		   
                                  <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             </div>
                             </c:if>
                             <c:if test="${action eq 'edit'}">	
                             <div class="row">                              
                                <div class="col s6 m4 input-field offset-m2">
                                    <input type="text"  id="project_id_fk" value="${structuresListDetails.project_id_fk}- ${structuresListDetails.project_name}" readonly />
                                    <input type="hidden" name="project_id_fk"  value="${structuresListDetails.project_id_fk}" />
									<label for="project_id_fk">Project <span class="required">*</span></label>     
							    </div> 
                                <div class="col s6 m4 input-field"> 
                                    <input type="text" id="work_id_fk"  value="${structuresListDetails.work_id_fk}- ${structuresListDetails.work_short_name}" readonly />
								    <input type="hidden" name="work_id_fk"  value="${structuresListDetails.work_id_fk}" />
								    <label for="work_id_fk">Work <span class="required">*</span></label>     
                                </div>
                            </div>
                             </c:if>
                       <%--      <div class="row">                            	 
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label">Department</span></p>
                                    <select class="searchable validate-dropdown" name="department_fk" id="department_fk">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${departmentsList }">
		                                     <option value="${obj.department_fk }" <c:if test="${structuresListDetails.department_fk eq obj.department_fk}">selected</c:if>>${obj.department_name}</option>
		                                </c:forEach>
                                    </select>
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                <c:if test="${action eq 'edit'}">	
                                 <div class="col s6 m4 input-field "> 
                              	    <input type="text" id="contract_id_fk" value="${structuresListDetails.contract_id_fk} - ${structuresListDetails.contract_short_name}" readonly />
                                 	<input type="hidden" name="contract_id_fk"  value="${structuresListDetails.contract_id_fk}" />
                                 	<label for="contract_id_fk">Contract <span class="required">*</span></label>           
                              	    </div>
                                 </c:if>
                                <c:if test="${action eq 'add'}">	
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Contract</p>
                                   <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" onchange="resetWorksAndProjectsDropdowns();">
                                       	<option value="">Select</option>
                                       	     <c:forEach var="obj" items="${contractsList }">
                                      	    	<option workId="${obj.work_id_fk }" value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                        	</c:forEach>
                                  	</select>
                                   	<span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                 </c:if>                                
                            </div> --%>
                          
                        <div class="row fixed-width" style="margin-bottom: 10px;margin-top:20px;">
                            <!-- <h5 class="center-align">Revision Details</h5> -->
                            <div class=" col s12 m12 table-inside">
                              <!-- <div class=""> -->
                                <table id="structure_details" class="mdl-data-table mobile_responsible_table">
                                    <thead>
                                        <tr>
                                            <th class="max-w-350">Structure Type</th>
                                            <th>Structure Id</th>
                                            <th class="text-right">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="structureTableBody">
                                    <c:choose>
                                      	 <c:when test="${not empty structuresListDetails.structureList && fn:length(structuresListDetails.structureList) gt 0 }">
                                       	 <c:forEach var="dObj" items="${structuresListDetails.structureList }" varStatus="index"> 
                                        <tr id="structureRow${index.count }">
                                       
                                            <td data-head="Structure Type" class="input-field" >
                                                <select id="structure_type_fks${index.count }" name="structure_type_fks" class="validate-dropdown searchable no-z" 
                                                onchange="setStructureTypes(${index.count });">
                                                    <option value="" >Select</option>
                                                    <c:forEach var="obj" items="${structuresList }">
		                                           			 <option value="${obj.structure_type }" <c:if test="${dObj.structure_type_fk eq obj.structure_type}">selected</c:if>>${obj.structure_type}</option>
		                                            </c:forEach>
                                                </select>
                                            </td>
                                           <td data-head="Structure Id" class="input-field no-pad">                                         
                                                 <input type="hidden" name= "ids" id="ids" value="${dObj.structure_id}" />                                                    
                                                 <table class="internal-table" id="structureRow${index.count }-internalTable">
                                                    <tbody id="internalTable${indexx.count }${index.count }">
                                                     <c:forEach var="sObj" items="${dObj.structureSubList }" varStatus="indexx"> 
	                                                     <tr id="internalTableRow${indexx.count }${index.count }">
		                                                    <td>
			                                                  <input type="hidden" id="structure_type_fks${indexx.count }${index.count }"   <c:if test="${indexx.count != 1}"> name="structure_type_fks"</c:if> value="${dObj.structure_type_fk }"/>
			                                                 
			                                                    <input id="structure_id${index.count }" name="structures" type="text" class="validate"  placeholder="Structure Id" value="${sObj.structure }"></td>
			                                                     <td style="text-align:center;"><div id="modal${indexx.count }${index.count }" class="modal">
															    
															    
															    <div class="modal-content">
																	<h5 class="modal-header">Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
																	<div class="container">
																		<div class="row">
											                                 <div class="col s6 input-field">
											                                    <p class="searchable_label">Work Status </p>
											                                    <select id="work_status_fk${indexx.count }${index.count }" name="work_status_fks"  class="searchable validate-dropdown">
											                                        <option value="">Select</option>
											                                         <c:forEach var="obj" items="${workStatusList}">
																						<option value="${obj.work_status_fk }" >${obj.work_status_fk }</option>
																					</c:forEach>
											                                     </select>
											                                    <span id="work_status_fk${indexx.count }${index.count }Error" class="error-msg" ></span>
											                                </div>
											                                <div class="col s6 input-field">
											                                    <input id="target_date${indexx.count }${index.count }" name="target_dates" type="text" class="validate datepicker" value="" >
											                                    <label for="target_date${indexx.count }${index.count }">Original Target Date </label>
											                                    <button type="button" id="target_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											                                    <span id="target_date${indexx.count }${index.count }Error" class="error-msg" ></span>
											                                </div>
										                                </div>
										                                <div class="row">
										                                	<div class="col s6 input-field amount-dropdown">
											                                	<i class="material-icons amount-symbol cost">₹</i>   
											                                    <input id="estimated_cost${indexx.count }${index.count }" name="estimated_costs" type="number" class="validate" value="" min="0.01" step="0.01">
											                                    <label for="estimated_cost${indexx.count }${index.count }">Estimated Cost</label>
											                                    <span id="estimated_cost${indexx.count }${index.count }Error" class="error-msg" ></span> 
											                                	<span id="estimated_cost_units${indexx.count }${index.count }Error" class="error-msg right" ></span>
											                                    <select class="validate-dropdown" id="estimated_cost_units${indexx.count }${index.count }" name="estimated_cost_unitss">
											                                		<option value="">Select</option>    
											                                		<c:forEach var="obj" items="${unitsList}">
																						<option value="${obj.value }" >${obj.unit }</option>
																					</c:forEach>                              		
											                                	</select>
											                                </div>
										                                	 <div class="col s6 input-field">
											                                    <input id="construction_start_date${indexx.count }${index.count }" name="construction_start_dates" type="text" class="validate datepicker" value="" >
											                                    <label for="construction_start_date${indexx.count }${index.count }">Construction Start Date </label>
											                                    <button type="button" id="construction_start_date${indexx.count }${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											                                    <span id="construction_start_date${indexx.count }${index.count }Error" class="error-msg" ></span>
											                                </div>
											                                <div class="col s6 input-field " >
											                                    <input id="revised_completion${indexx.count }${index.count }" name="revised_completions" type="text" class="validate datepicker" value="${fob.revised_completion }" <c:if test="${not empty fob.revised_completion}">disabled</c:if>>
											                                    <label for="revised_completion${indexx.count }${index.count }" >Target completion Date </label>
											                                    <button type="button" id="revised_completion${indexx.count }${index.count }_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											                                    <span id="revised_completion${indexx.count }${index.count }Error" class="error-msg" ></span>
											                                </div> 
										                                </div>
										                                <div class="row">
										                                	<div class="col s12 input-field ">
											                                    <textarea id="remarks${indexx.count }${index.count }" name="remarkss" class="pmis-textarea" data-length="1000" maxlength="1000">${fob.remarks }</textarea>
											                                    <label for="remarks${indexx.count }${index.count }">Remarks</label>
											                                    <span id="remarks${indexx.count }${index.count }Error" class="error-msg" ></span>
											                                </div>
										                                </div>
																	</div>
																	
																	
																	<div class="row">
																		<div class="col m12">
																			<table id="structureResponsibleTable${indexx.count }${index.count }${index.count }" class="mdl-data-table mobile_responsible_table">
																			    <thead>
																			        <tr>
																			            <th style="width:50%">Contract </th>
																			            <th style="text-align : left;">Responsible Executives </th>
																			            <th style="width:8%">Action</th>
																			        </tr>
																			    </thead>
																			    <tbody id="structureResponsibleBody${indexx.count }${index.count }${index.count }">
																			    <input type="hidden"  id="contracts_id_fk${indexx.count }${index.count }${index.count }" name="contracts_id_fk" />
																			        <tr id="structureResponsibleRow${indexx.count }${index.count }${index.count }">
																			            <td data-head="Department" class="input-field">
																			                <select class="searchable validate-dropdown contracts_id_fk"
																			                id="contract_id_fk${indexx.count }${index.count }${index.count }" name="contracts"  onchange="getRowsCount('${indexx.count }${index.count }${index.count }','${indexx.count }${index.count }');">
																			                    <option value="">Select</option>
																			                    <c:forEach var="obj" items="${contractsList}">
																									<option value="${obj.contract_id_fk }" >${obj.contract_short_name }</option>
																								</c:forEach>
																			                </select>
																			            </td>
																			            <td data-head="Select Executives" class="input-field h-auto">
																			            	<input type="hidden"  id="responsible_people_id_fk${indexx.count }${index.count }${index.count }" name="responsible_people_id_fks" />
																			                <select class="searchable validate-dropdown"  id="responsible_people_id_fks${indexx.count }${index.count }${index.count }" multiple onchange="executivesToStringMethod('${indexx.count }${index.count }${index.count }')">
																			                    <option value="">Select</option>
																			                     <c:forEach var="obj" items="${responsiblePeopleList}">
																									<option value="${obj.user_id }" >${obj.designation }</option>
																								</c:forEach>
																			                </select>
																			            </td>
																			            <td class="mobile_btn_close">
																			                <a onclick="removeStructureResponsible('${indexx.count }${index.count }${index.count }','${index.count }');deleteRowsCount('${indexx.count }${index.count }${index.count }','${indexx.count }${index.count }');" class="btn waves-effect waves-light red t-c "> <i
																			                        class="fa fa-close"></i></a>
																			            </td>
																			        </tr>
																			    </tbody>
																			</table>
																			<input type="hidden" value="0" id="structureResponsibleLength${indexx.count }${index.count }${index.count }">
																			<table class="mdl-data-table bd-none">
																			    <tbody>                                          
																			        <tr class="bd-none">
																			               <td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c " onclick="addstructureResponsibleRow('${indexx.count }${index.count }${index.count }');"> <i class="fa fa-plus"></i></a></td>
																			         </tr>
																			    </tbody>
																			</table>
																		</div>
																	</div>	
																	
																	
																	<div class="row">
																        <h5 class="center-align">Structure Details</h5>
																        <div class="col s12 m10 offset-m1">
																            <table id="structureDetailsTable${indexx.count }${index.count }${index.count }" class="mdl-data-table">
																                <thead>
																                    <tr>
																                        <th>Structure Detail </th>
																                        <th>Value </th>
																                        <th>Action</th>
																                    </tr>
																                </thead>
																                <tbody id="structureDetailsTableBody${indexx.count }${index.count }${index.count }">
																                	<input type="hidden" id="structure_details${indexx.count }${index.count }${index.count }" name="structure_detailss">
																                    <tr id="structureDetailsRow${indexx.count }${index.count }${index.count }">
																                        <td>
																                            <input id="structure_detail_names${indexx.count }${index.count }${index.count }" name="structure_details" type="text" onkeyup="detailsMethod('${indexx.count }${index.count }${index.count }')"
																                                class="validate" value="" placeholder="Detail name">
																                        </td>
																                        <td>
																                            <input id="structure_detail_values${indexx.count }${index.count }${index.count }" name="structure_values" type="text"
																                                class="validate" value="" placeholder="Value">
																                        </td>
																                        <td class="mobile_btn_close">
																                            <a onclick="removeStructureDetail('${indexx.count }${index.count }${index.count }','${index.count }');detailsMethod('${indexx.count }${index.count }${index.count }');"
																                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
																                        </td>
																                    </tr>
																                </tbody>
																            </table>
																            <input type="hidden" value="0" id="structureDetailsLength${indexx.count }${index.count }${index.count }">
																            <table class="mdl-data-table bd-none">
																                <tbody>
																                    <tr class="bd-none">
																                        <td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c "
																                                onclick="addstructureDetailRow('${indexx.count }${index.count }${index.count }')"> <i class="fa fa-plus"></i></a></td>
																                    </tr>
																                </tbody>
																            </table>
																        </div>
																    </div>
																    
																    
																    <div class="row">
																	    <div class="col s12">
																	        <div class="row fixed-width">
																	            <h5 class="center-align"><span class="div-header">Documents</span></h5>
																	            <table class="mdl-data-table update-table mobile_responsible_table" id="structureFilesTable${indexx.count }${index.count }${index.count }">
																	                <thead>
																	                    <tr>
																	                        <th class="min-180">File Type</th>
																	                        <th>Name</th>
																	                        <th>Attachment</th>
																	                        <th style="display:none;"></th>
																	                        <th>Action</th>
																	                    </tr>
																	                </thead>
																	                <tbody id="structureFilesBody${indexx.count }${index.count }${index.count }">
																	                 <input type="hidden"  id="structureFiless${indexx.count }${index.count }${index.count }" name="structureFiless" />
																	                    <tr id="structureFilesRow${indexx.count }${index.count }${index.count }">
																	                        <td data-head="File Type" class="input-field">
																	                           <select name="structure_file_types" id="structure_file_types${indexx.count }${index.count }${index.count }" class="validate-dropdown searchable">
																	                                <option value="">Select</option>
																	                                 <c:forEach var="obj" items="${fileType}">
																										<option value="${obj.structure_file_type }" >${obj.structure_file_type }</option>
																									 </c:forEach>
																	                      	 </select></td> 
																	                        <td data-head="Name" class="input-field">
																	                            <input id="structureDocumentNames${indexx.count }${index.count }${index.count }" name="structureDocumentNames" type="text" class="validate"
																	                                placeholder="Name">
																	                        </td>
																	                        <td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">
																	                            <div class="t-c">
																	                                <input type="file" id="structureFiles${indexx.count }${index.count }${index.count }" name="structureFiles" accept="image/*" onchange="documentMethod('${indexx.count }${index.count }${index.count }');">
																	                                <label for="structureFiles${indexx.count }${index.count }${index.count }" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
																	                            </div>
																	                            <div class="file-path-wrapper">
																	                                <input class="file-path validate" type="text" id="structureFileNames${indexx.count }${index.count }${index.count }" name="structureFileNames">
																	                            </div>
																	                        </td>
																	                        <td style="display:none;"><input type="hidden" id="structure_file_ids${indexx.count }${index.count }${index.count }" name="structure_file_ids" /></td>
																	                        <td class="mobile_btn_close">
																	                            <a onclick="removeStructureFileRow('${indexx.count }${index.count }${index.count }','${index.count }');documentMethod('${indexx.count }${index.count }${index.count }');" class="btn red">
																	                                <i class="fa fa-close"></i></a>
																	                        </td>
																	                    </tr>
																	                </tbody>
																	            </table>
																	            <input type="hidden" id="structureFilesLength${indexx.count }${index.count }${index.count }" name="structureFilesLength" value="0" />
																	
																	            <table class="mdl-data-table bd-none">
																	                <tbody>
																	                    <tr class="bd-none">
																	                        <td colspan="5" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																	                                onclick="addStructureFileRow('${indexx.count }${index.count }${index.count }')"> <i class="fa fa-plus"></i>
																	                            </a>
																	                    </tr>
																	                </tbody>
																	            </table>
																	        </div>
																	    </div>
																	</div>
																    
															    </div>												
															 </div>
			                                           		<a class="modal-trigger btn bg-m t-c" href="#modal${indexx.count }${index.count }">Update</a>
                                           					</td>
		                                                    <td class="no-pad"><a class="btn mob-btn waves-effect waves-light red t-c" onclick="removeStructureInternalRow('${indexx.count }${index.count }','${index.count }')">
		                                                     <i class="fa fa-close"></i></a></td></tr> 
		                                      
		                                         </c:forEach>
		                                          <tr class="mob-add-btn"><td> 
			  												<a type="button" class="btn mob-btn waves-effect waves-light bg-m t-c" onClick="addInternalTableRow('${indexx.count }${index.count }','${index.count }')"> <i
			                                                            class="fa fa-plus" ></i></a>
			                                        </td></tr>
		                                        </tbody>
		                                        </table>
                                                    <%-- <input id="structure_id${index.count }" name="structures" type="text" class="validate"  placeholder="Structure Id" value="${dObj.structure }"> --%>
                                           </td>  
                                         
                                            <td class="mobile_btn_close line">
                                                <a onclick="removeStructureRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                       <c:choose>
                                        <c:when test="${not empty (dObj.structureSubList) && fn:length(dObj.structureSubList) gt 0 }">
                                    		<input type="hidden" id="internalRow${indexx.count }${index.count }"  name="internalRowNo" value="${fn:length(dObj.structureSubList) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="internalRow${indexx.count }${index.count }"  name="internalRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>
                                        </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       <tr id="structureRow0">                                        
                                            <td data-head="Structure Type" class="input-field">
                                            	 	
                                                  <select id="structure_type_fks0" name="structure_type_fks" class="validate-dropdown searchable no-z" 
                                                   onchange="setStructureTypes(0);">
                                                    <option value="" >Select</option>
                                                    <c:forEach var="obj" items="${structuresList }">
		                                           			 <option value="${obj.structure_type }">${obj.structure_type}</option>
		                                            </c:forEach>
                                                </select>
                                            </td>                                            
                                           <td data-head="Structure Id" class="input-field no-pad"> 
                                               <c:choose>
			                                        <c:when test="${not empty (dObj.structureSubList) && fn:length(dObj.structureSubList) gt 0 }">
			                                    		<input type="hidden" id="internalRow00"  name="internalRowNo" value="${fn:length(dObj.structureSubList) }" />
			                                    	</c:when>
			                                     	<c:otherwise>
			                                     		<input type="hidden" id="internalRow00"  name="internalRowNo" value="0" />
			                                     	</c:otherwise>
			                                     </c:choose> 
												<table class="internal-table" id="structureRow0-internalTable">
												<tbody id="internalTable00">
													
													<tr id="internalTableRow00"><td>
													<input id="structure_id" name="structuress" type="hidden">
													<input id="structure_id0_0" name="structures" type="text" class="validate" placeholder="Structure Id">
	                                                         </td>
	                                                        <td style="text-align:center;">
	                                                        
	                                                        <div id="modal00" class="modal">
															    <div class="modal-content">
																	<h5 class="modal-header">Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
															    	<div class="container">
																		<div class="row">
											                                 <div class="col s6 input-field">
											                                    <p class="searchable_label">Work Status </p>
											                                    <select id="work_status_fk00" name="work_status_fks"  class="searchable validate-dropdown">
											                                        <option value="">Select</option>
											                                        <c:forEach var="obj" items="${workStatusList}">
																						<option value="${obj.work_status_fk }" >${obj.work_status_fk }</option>
																					</c:forEach>
											                                     </select>
											                                    <span id="work_status_fk00Error" class="error-msg" ></span>
											                                </div>
											                                <div class="col s6 input-field">
											                                    <input id="target_date00" name="target_dates" type="text" class="validate datepicker" value="" >
											                                    <label for="target_date00">Original Target Date </label>
											                                    <button type="button" id="target_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											                                    <span id="target_date00Error" class="error-msg" ></span>
											                                </div>
										                                </div>
										                                <div class="row">
										                                	<div class="col s6 input-field amount-dropdown">
											                                	<i class="material-icons amount-symbol cost">₹</i>   
											                                    <input id="estimated_cost00" name="estimated_costs" type="number" class="validate" value="" min="0.01" step="0.01">
											                                    <label for="estimated_cost00">Estimated Cost</label>
											                                    <span id="estimated_cost00Error" class="error-msg" ></span> 
											                                	<span id="estimated_cost_units00Error" class="error-msg right" ></span>
											                                    <select class="validate-dropdown" id="estimated_cost_units00" name="estimated_cost_unitss">
											                                		<option value="">Select</option>             
											                                		<c:forEach var="obj" items="${unitsList}">
																						<option value="${obj.value }" >${obj.unit }</option>
																					</c:forEach>                   		
											                                	</select>
											                                </div>
										                                	 <div class="col s6 input-field">
											                                    <input id="construction_start_date00" name="construction_start_dates" type="text" class="validate datepicker" value="" >
											                                    <label for="construction_start_date00">Construction Start Date </label>
											                                    <button type="button" id="construction_start_date00_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											                                    <span id="construction_start_date00Error" class="error-msg" ></span>
											                                </div>
											                                <div class="col s6 input-field " >
											                                    <input id="revised_completion00" name="revised_completions" type="text" class="validate datepicker" value="${fob.revised_completion }" <c:if test="${not empty fob.revised_completion}">disabled</c:if>>
											                                    <label for="revised_completion00" >Target completion Date </label>
											                                    <button type="button" id="revised_completion00_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
											                                    <span id="revised_completion00Error" class="error-msg" ></span>
											                                </div> 
										                                </div>
										                                <div class="row">
										                                	<div class="col s12  input-field ">
											                                    <textarea id="remarks00" name="remarkss" class="pmis-textarea" data-length="1000" maxlength="1000">${fob.remarks }</textarea>
											                                    <label for="remarks00">Remarks</label>
											                                    <span id="remarks00Error" class="error-msg" ></span>
											                                </div>
										                                </div>
																	</div>
																	
															    
																	<div class="row">
																		<div class="col m12">
																			<table id="structureResponsibleTable1000" class="mdl-data-table mobile_responsible_table">
																			    <thead>
																			        <tr>
																			            <th style="width:50%">Contract </th>
																			            <th style="text-align : left;">Responsible Executives </th>
																			            <th style="width:8%">Action</th>
																			        </tr>
																			    </thead>
																			    <tbody id="structureResponsibleBody1000">
																			    <input type="hidden"  id="contracts_id_fk1000" name="contracts_id_fk" />
																			        <tr id="structureResponsibleRow1000">
																			            <td data-head="Department" class="input-field">
																			           	    
																			                <select class="searchable validate-dropdown contracts_id_fk" id="contract_id_fk1000" name="contracts"  onchange="getRowsCount('1000','00');">
																			                    <option value="">Select</option>
																			                    <c:forEach var="obj" items="${contractsList}">
																									<option value="${obj.contract_id_fk }" >${obj.contract_short_name }</option>
																								</c:forEach>
																			                </select>
																			            </td>
																			            <td data-head="Select Executives" class="input-field h-auto">
																			            	<input type="hidden" id="responsible_people_id_fk1000" name="responsible_people_id_fks" />
																			            	
																			                <select class="searchable validate-dropdown"
																			                    id="responsible_people_id_fks1000" multiple onchange="executivesToStringMethod('1000')">
																			                    <option value="">Select</option>
																			                    <c:forEach var="obj" items="${responsiblePeopleList}">
																									<option value="${obj.user_id }" >${obj.designation }</option>
																								</c:forEach>
																			                </select>
																			            </td>
																			            <td class="mobile_btn_close">
																			                <a onclick="removeStructureResponsible('1000','0');deleteRowsCount('1000','00');" class="btn waves-effect waves-light red t-c "> <i
																			                        class="fa fa-close"></i></a>
																			            </td>
																			        </tr>
																			    </tbody>
																			</table>
																			<input type="hidden" value="0" id="structureResponsibleLength1000">
																			<table class="mdl-data-table bd-none">
																			    <tbody>                                          
																			        <tr class="bd-none">
																			               <td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c " onclick="addstructureResponsibleRow('1000');"> <i class="fa fa-plus"></i></a></td>
																			         </tr>
																			    </tbody>
																			</table>
																		</div>
																	</div>																	
        
        
        														<div class="row">
															        <h5 class="center-align">Structure Details</h5>
															        <div class="col s12 m10 offset-m1">
															            <table id="structureDetailsTable1000" class="mdl-data-table">
															                <thead>
															                    <tr>
															                        <th>Structure Detail </th>
															                        <th>Value </th>
															                        <th>Action</th>
															                    </tr>
															                </thead>
															                <tbody id="structureDetailsTableBody1000">
															                <input type="hidden" id="structure_details1000" name="structure_detailss">
															                    <tr id="structureDetailsRow1000">
															                        <td>
															                        	
															                            <input id="structure_detail_names1000" name="structure_details" type="text"
															                                class="validate" value="" placeholder="Detail name" onkeyup="detailsMethod('1000')">
															                        </td>
															                        <td>
															                            <input id="structure_detail_values1000" name="structure_values" type="text"
															                                class="validate" value="" placeholder="Value">
															                        </td>
															                        <td class="mobile_btn_close">
															                            <a onclick="removeStructureDetail('1000','0');detailsMethod('1000');"
															                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
															                        </td>
															                    </tr>
															                </tbody>
															            </table>
															            <input type="hidden" value="0" id="structureDetailsLength1000">
															            <table class="mdl-data-table bd-none">
															                <tbody>
															                    <tr class="bd-none">
															                        <td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c "
															                                onclick="addstructureDetailRow('1000')"> <i class="fa fa-plus"></i></a></td>
															                    </tr>
															                </tbody>
															            </table>
															        </div>
															    </div>
															    
															    
															    <div class="row">
																    <div class="col  s12">
																        <div class="row fixed-width">
																            <h5 class="center-align"><span class="div-header">Documents</span></h5>
																            <table class="mdl-data-table update-table mobile_responsible_table" id="structureFilesTable1000">
																                <thead>
																                    <tr>
																                        <th class="min-180">File Type</th>
																                        <th>Name</th>
																                        <th>Attachment</th>
																                        <th style="display:none;"></th>
																                        <th>Action</th>
																                    </tr>
																                </thead>
																                <tbody id="structureFilesBody1000">
																                	<input type="hidden"  id="structureFiless1000" name="structureFiless" />
																                    <tr id="structureFilesRow1000">
																                        <td data-head="File Type" class="input-field">
																                            <select name="structure_file_types" id="structure_file_types1000" class="validate-dropdown searchable">
																                                <option value="">Select</option>
																                                <c:forEach var="obj" items="${fileType}">
																									<option value="${obj.structure_file_type }" >${obj.structure_file_type }</option>
																								</c:forEach>
																                        </select></td>
																                        <td data-head="Name" class="input-field">
																                            <input id="structureDocumentNames1000" name="structureDocumentNames" type="text" class="validate"
																                                placeholder="Name">
																                        </td>
																                        <td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto">
																                            <div class="t-c">
																                                <input type="file" id="structureFiles1000" name="structureFiles" accept="image/*" onchange="documentMethod('1000');">
																                                <label for="structureFiles1000" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
																                            </div>
																                            <div class="file-path-wrapper">
																                                <input class="file-path validate" type="text" id="structureFileNames1000" name="structureFileNames">
																                            </div>
																                        </td>
																                        <td style="display:none;"><input type="hidden" id="structure_file_ids1000" name="structure_file_ids" /></td>
																                        <td class="mobile_btn_close">
																                            <a onclick="removeStructureFileRow('1000','0');documentMethod('1000');" class="btn red">
																                                <i class="fa fa-close"></i></a>
																                        </td>
																                    </tr>
																                </tbody>
																            </table>
																            <input type="hidden" id="structureFilesLength1000" name="structureFilesLength" value="0" />
																
																            <table class="mdl-data-table bd-none">
																                <tbody>
																                    <tr class="bd-none">
																                        <td colspan="5" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c "
																                                onclick="addStructureFileRow('1000')"> <i class="fa fa-plus"></i>
																                            </a>
																                    </tr>
																                </tbody>
																            </table>
																        </div>
																    </div>
																</div>
															    
															    </div>												
															 </div>
			                                            		<a class="modal-trigger btn bg-m t-c" href="#modal00">Update</a>
                                           					</td>			                                                    	
			                                                    
	                                                        <td class="no-pad"><a class="btn mob-btn waves-effect waves-light red t-c" onclick="removeStructureInternalRow('00','0')"> <i
	                                                        class="fa fa-close"></i></a></td>
	                                               </tr><tr class="mob-add-btn"><td> 
			  											<a type="button" class="btn mob-btn waves-effect waves-light bg-m t-c" onClick="addInternalTableRow('00','0')"> <i
			                                                            class="fa fa-plus" ></i></a>
			                                        </td></tr>
				                                    <!--  <script>
														 var len = $("#internalTable0 tr").length-1;
															 $("#subRowsLengths0").val(len);
				                                     </script> -->
			                                    </tbody>
		                                        </table>                                                        
                                            </td>
                                            
                                            <td class="mobile_btn_close line">
                                                <a  onclick="removeStructureRow('0');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
										
                                    	  </c:otherwise>
                                     	</c:choose>
                                     	 </tbody>
                                    </table>
                                     
                                    <!-- <table class="mdl-data-table table-add bd-none">
                                        <tbody id="documentBody">                                          
		                                    <tr class="bd-none">
		  										<td colspan="3" class="bd-none"> 
		  											<a type="button" class="btn waves-effect waves-light bg-m t-c add-align" onclick="addStructureRow()"> <i
		                                                            class="fa fa-plus"></i></a>
		                                        </td>
		                                    </tr>
                                        </tbody>
                                    </table> -->
  									<c:choose>
                                        <c:when test="${not empty (structuresListDetails.structureList) && fn:length(structuresListDetails.structureList) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(structuresListDetails.structureList) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose> 
                                <!--  </div> -->
                            </div>
                        </div>
                        <div class="plus_btn">
										<a type="button" class="btn waves-effect waves-light bg-m t-c" onclick="addStructureRow()"> <i
                                                         class="fa fa-plus"></i></a>
                                     </div>
					<c:if test="${action eq 'edit'}">	
						<div class="row">                            
                             <div class="col s6 m4 offset-m2  input-field" id="loa_date_div">
                                 <input id="commissioning_date" name="commissioning_date" type="text" class="validate datepicker">
                                 <label for="commissioning_date">Actual date of Commissioning</label>
                                  <span id="commissioning_dateError" class="error-msg" ></span>
                                 <button type="button" id="commissioning_date_icon"><i class="fa fa-calendar"></i></button>
                             </div>
                             <div class="col s6 m4 input-field" id="loa_date_div">
                                 <input id="structure_value" name="structure_value" type="text" class="validate">
                                 <label for="structure_value">Structure Value</label>
                                 <span id="structure_valueError" class="error-msg" ></span>
                             </div>
                         </div>
	                 </c:if>   
	                            
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m6 mt-brdr center-align">
                                    <div class="m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateDocument();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addDocument();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
											 </c:if>
                                    </div>
                                </div>
                               <div class="col s6 m6 mt-brdr center-align">
                                    <div class=" m-1">
                                          <a href="<%=request.getContextPath()%>/structure" class="btn waves-effect waves-light bg-s w-text">Cancel</a>
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    
        <script>
 
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal();

            // commented code placed next script tag from here 


            $("#myFile").change(function () {
                filename1 = $('#myFile')[0].value;

                $('#fileVal').html(filename1);
                console.log(filename1)
                console.log($('#myFile')[0].localName)
            });
            
            var project_id_fk = "${structuresListDetails.project_id_fk}";
            if($.trim(project_id_fk) != ''){
            	getWorksList(project_id_fk);
            }
            var work_id_fk = "${structuresListDetails.work_id_fk}";
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
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForStructureForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var work_id_fk = "${structuresListDetails.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id_fk == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                        getContractsList("") 
                    }
                });
            }else{
            	
            	$(".page-loader").hide();
            }
        }
        function getContractsList(work_id_fk) {
            $(".contracts_id_fk option:not(:first)").remove();
            var project_id_fk = $("#project_id_fk").val();
            if ($.trim(work_id_fk) != "" || $.trim(project_id_fk) != "") {
                var myParams = {work_id_fk:work_id_fk,project_id_fk:project_id_fk};
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractListForStructureFrom",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                var contract_id_fk = "${structuresListDetails.contract_id_fk }";
                                var count = val.quantity;
                                if ($.trim(contract_id_fk) != '' && val.contract_id_fk == $.trim(contract_id_fk)) {
                                	$(".contracts_id_fk").slice(0, count).append('<option  value="' + val.contract_id_fk + '" selected>' + $.trim(contract_name) + '</option>');
                                } else {
                                	$(".contracts_id_fk").slice(0, count).append('<option  value="' + val.contract_id_fk + '">' + $.trim(contract_name) + '</option>');
                                }
                            });
                        }
                        $('.searchable').select2(); 
                    }
                });
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
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		}
       		$(".page-loader").hide();
        }
        
        function resetProject(){
        	$(".page-loader").show();        	
        	var projectId = '';
        	var workId = $("#work_id_fk").val();
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3);    
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		    document.getElementById("project_idError").innerHTML="";
       		}
       		$(".page-loader").hide();
        }
        function addDocument(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	  			$('form input[name=structure_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=structures]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=structures]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=work_status_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=target_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=estimated_costs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=estimated_cost_unitss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=construction_start_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_completions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			document.getElementById("structureForm").submit();	
        	}
        }
        function updateDocument(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	        	$('form input[name=structure_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=structures]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=structures]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=work_status_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=target_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=estimated_costs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=estimated_cost_unitss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=construction_start_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_completions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			document.getElementById("structureForm").submit();	
        	}
        }
     
    function addStructureRow(){
    	 	/*  var work_id_fk = $("#work_id_fk").val();
    	 	 getContractsList(work_id_fk); */
             var rowNo = $("#rowNo").val();
             var rNo = Number(rowNo)+1;
             var x = Math.floor(Math.random() * (5 - 99 + 1) + 99)
             var html = '<tr id="structureRow'+rNo+'">'
             			+'<td data-head="Structure Type" class="input-field"><select id="structure_type_fks'+rNo+'" name="structure_type_fks" class="validate-dropdown searchable no-z" onchange="setStructureTypes('+rNo+');">'
             			+'<option value="" >Select</option>'
			               <c:forEach var="obj" items="${structuresList }">
			              	+'<option value="${obj.structure_type }">${obj.structure_type}</option>'
			               </c:forEach>
           				+'</select></td>'
           				+'<td data-head="Structure Id" class="input-field no-pad"><input type="hidden" id="internalRow'+rNo+rNo+x+'"  name="internalRowNo" value="0" /> <table class="internal-table" id="structureRow'+rNo+'-internalTable"><tbody id="internalTable'+rNo+'">'
           				+'<tr id="internalTableRow'+rNo+rNo+x+'"><td><input type = "hidden" name="structure_type_fks" id="structure_type_fks'+rNo+rNo+x+'"/> <input id="structure_id'+rNo+'" name="structures" type="text" class="validate" placeholder="Structure Id"></td>'
           				+'<td style="text-align:center;"><div id="modal'+rNo+rNo+x+'" class="modal">'
           			    +'<div class="modal-content"><h5 class="modal-header">Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>'
           			    +'<div class="container"><div class="row"><div class="col s6 input-field"><p class="searchable_label">Work Status </p><select id="work_status_fk'+rNo+rNo+x+'" '
           			    +'name="work_status_fks"  class="searchable validate-dropdown">'+
           			    '<option value="">Select</option>'
		           			 <c:forEach var="obj" items="${workStatusList }">
			              	 	+'<option value="${obj.work_status_fk }">${obj.work_status_fk}</option>'
			                 </c:forEach>
           			    +'</select><span id="work_status_fk'+rNo+rNo+x+'Error" class="error-msg" ></span>'
           			    +'</div><div class="col s6 input-field"><input id="target_date'+rNo+rNo+x+'" name="target_dates" type="text" class="validate datepicker" value="" >'
           			    +'<label for="target_date'+rNo+rNo+x+'">Original Target Date </label><button type="button" id="target_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
           			    +'<span id="target_date'+rNo+rNo+x+'Error" class="error-msg" ></span></div></div>'
           			    +'<div class="row"><div class="col s6 input-field amount-dropdown"><i class="material-icons amount-symbol cost">₹</i>'
           			    +'<input id="estimated_cost'+rNo+rNo+x+'" name="estimated_costs" type="number" class="validate" value="" min="0.01" step="0.01"><label for="estimated_cost'+rNo+rNo+x+'">Estimated Cost</label>'
           			    +'<span id="estimated_cost'+rNo+rNo+x+'Error" class="error-msg" ></span><span id="estimated_cost_units'+rNo+rNo+x+'Error" class="error-msg right" ></span>' 
           			    +'<select class="validate-dropdown" id="estimated_cost_units'+rNo+rNo+x+'" name="estimated_cost_unitss">'+
           			    '<option value="">Select</option>'
		           			 <c:forEach var="obj" items="${unitsList }">
			              	 	+'<option value="${obj.value }">${obj.unit}</option>'
			                 </c:forEach>
           			    +'</select></div>'
           			    +'<div class="col s6 input-field"><input id="construction_start_date'+rNo+rNo+x+'" name="construction_start_dates" type="text" class="validate datepicker" value="" >'
           			    +'<label for="construction_start_date'+rNo+rNo+x+'">Construction Start Date </label><button type="button" id="construction_start_date'+rNo+rNo+x+'_icon" class="datepicker-button">'
           			    +'<i class="fa fa-calendar"></i></button><span id="construction_start_date'+rNo+rNo+x+'Error" class="error-msg" ></span></div>'
           			    +'<div class="col s6 input-field " ><input id="revised_completion'+rNo+rNo+x+'" name="revised_completions" type="text" class="validate datepicker" >'
           			    +'<label for="revised_completion'+rNo+rNo+x+'" >Target completion Date </label><button type="button" id="revised_completion'+rNo+rNo+x+'_icon" class="datepicker-button"><i class="fa fa-calendar">'
           			    +'</i></button><span id="revised_completion'+rNo+rNo+x+'Error" class="error-msg" ></span></div></div>'
           			    +'<div class="row"><div class="col s12  input-field "><textarea id="remarks'+rNo+rNo+x+'" name="remarkss" class="pmis-textarea" data-length="1000" maxlength="1000"></textarea>'
           			    +'<label for="remarks'+rNo+rNo+x+'">Remarks</label><span id="remarks'+rNo+rNo+x+'Error" class="error-msg" ></span></div></div></div>'
           			    +'<div class="row"><div class="col m12"><table id="structureResponsibleTable'+1+rNo+rNo+rNo+x+'" class="mdl-data-table mobile_responsible_table"><thead><tr>'
           			    +'<th style="width:50%">Contract </th><th style="text-align : left;">Responsible Executives </th><th style="width:8%">Action</th></tr></thead>'
           			    +'<tbody id="structureResponsibleBody'+1+rNo+rNo+rNo+x+'"><input type="hidden"  id="contracts_id_fk'+1+rNo+rNo+rNo+x+'" name="contracts_id_fk" /><tr id="structureResponsibleRow'+1+rNo+rNo+rNo+x+'"><td data-head="Departments" class="input-field">'
           			    +'<select class="searchable validate-dropdown contracts_id_fk"  id="contract_id_fk'+1+rNo+rNo+rNo+x+'" name="contracts"  onchange="getRowsCount('+1+rNo+rNo+rNo+x+','+rNo+');">'+
           			    '<option value="">Select</option>'
           			    	<c:forEach var="obj" items="${contractsList }">
				              	 +'<option value="${obj.contract_id_fk }">${obj.contract_short_name}</option>'
				            </c:forEach> 
           			    +'</select></td>'
           			    +'<td data-head="Select Executives" class="input-field h-auto"><input type="hidden" id="responsible_people_id_fk'+1+rNo+rNo+rNo+x+'" name="responsible_people_id_fks" /><select class="searchable validate-dropdown"  id="responsible_people_id_fks'+1+rNo+rNo+rNo+x+'" multiple onchange="executivesToStringMethod('+1+rNo+rNo+rNo+x+')">'
           			    +'<option value="">Select</option>'
		           			 <c:forEach var="obj" items="${responsiblePeopleList }">
				              	 +'<option value="${obj.user_id }">${obj.designation}</option>'
				             </c:forEach>
           			    +'</select></td><td class="mobile_btn_close"><a href="javascript:void(0);" onClick="removeStructureResponsible('+1+rNo+rNo+rNo+x+','+rNo+');deleteRowsCount('+1+rNo+rNo+rNo+x+','+rNo+');" class="btn waves-effect waves-light red t-c ">'
           			    +'<i class="fa fa-close"></i></a></td></tr></tbody></table>'
           			    +'<input type="hidden" value="0" id="structureResponsibleLength'+1+rNo+rNo+rNo+x+'"><table class="mdl-data-table bd-none"><tbody>  <tr class="bd-none">'
           			    +'<td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c " onclick="addstructureResponsibleRow('+1+rNo+rNo+rNo+x+');"> <i class="fa fa-plus"></i></a></td>'
           			    +'</tr></tbody></table></div></div>'
           			    
           			 	+ '<div class="row"><h5 class="center-align">Structure Details</h5><div class="col s12 m10 offset-m1"><table id="structureDetailsTable'+1+rNo+rNo+rNo+x+'" class="mdl-data-table">'
			            +'<thead><tr><th>Structure Detail </th><th>Value </th><th>Action</th></tr></thead><tbody id="structureDetailsTableBody'+1+rNo+rNo+rNo+x+'"><input type="hidden" id="structure_details'+1+rNo+rNo+rNo+x+'" name="structure_detailss"><tr id="structureDetailsRow'+1+rNo+rNo+rNo+x+'">'
			            +'<td><input id="structure_detail_names'+1+rNo+rNo+rNo+x+'" name="structure_details" onkeyup="detailsMethod('+1+rNo+rNo+rNo+x+');" type="text" class="validate" value="" placeholder="Detail name"></td> <td>'
			            +'<input id="structure_detail_values'+1+rNo+rNo+rNo+x+'" name="structure_values" type="text" class="validate" value="" placeholder="Value"></td> <td class="mobile_btn_close">'
			            +'<a onclick="removeStructureDetail('+1+rNo+rNo+rNo+x+','+rNo+');detailsMethod('+1+rNo+rNo+rNo+x+');"class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr></tbody></table>'
			            +'<input type="hidden" value="0" id="structureDetailsLength'+1+rNo+rNo+rNo+x+'"><table class="mdl-data-table bd-none"><tbody><tr class="bd-none">'
			            +'<td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c " onclick="addstructureDetailRow('+1+rNo+rNo+rNo+x+')"> <i class="fa fa-plus"></i></a></td></tr>'
			            +'</tbody></table></div></div>'
			            
			            + '<div class="row"><div class="col s12"><div class="row fixed-width"><h5 class="center-align"><span class="div-header">Documents</span></h5>'
			            +'<table class="mdl-data-table update-table mobile_responsible_table" id="structureFilesTable'+1+rNo+rNo+rNo+x+'"><thead><tr><th class="min-180">File Type</th>'
			            +'<th>Name</th><th>Attachment</th><th style="display:none;"></th><th>Action</th></tr></thead><tbody id="structureFilesBody'+1+rNo+rNo+rNo+x+'">'
			            +'<input type="hidden"  id="structureFiless'+1+rNo+rNo+rNo+x+'" name="structureFiless" /><tr id="structureFilesRow'+1+rNo+rNo+rNo+x+'"><td data-head="File Type" class="input-field"><select name="structure_file_types" id="structure_file_types'+1+rNo+rNo+rNo+x+'" '
			            +'class="validate-dropdown searchable"><option value="">Select</option>'
				            <c:forEach var="obj" items="${fileType }">
		              		 +'<option value="${obj.structure_file_type }">${obj.structure_file_type}</option>'
		             	 	</c:forEach>
			            +'</select></td><td data-head="Name" class="input-field">'
			            +'<input id="structureDocumentNames'+1+rNo+rNo+rNo+x+'" name="structureDocumentNames" type="text" class="validate" placeholder="Name"></td>'
			            +'<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto"><div class="t-c"><input type="file" id="structureFiles'+1+rNo+rNo+rNo+x+'" '
			            +'name="structureFiles" accept="image/*" onchange="documentMethod('+1+rNo+rNo+rNo+x+');"><label for="structureFiles'+1+rNo+rNo+rNo+x+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label></div>'
			            +'<div class="file-path-wrapper"><input class="file-path validate" type="text" id="structureFileNames'+1+rNo+rNo+rNo+x+'" name="structureFileNames"></div></td>'
			            +'<td style="display:none;"><input type="hidden" id="structure_file_ids'+1+rNo+rNo+rNo+x+'" name="structure_file_ids" /></td><td class="mobile_btn_close">'
			            +'<a onclick="removeStructureFileRow('+1+rNo+rNo+rNo+x+','+rNo+');documentMethod('+1+rNo+rNo+rNo+x+');" class="btn red"><i class="fa fa-close"></i></a></td></tr></tbody></table>'
			            +'<input type="hidden" id="structureFilesLength'+1+rNo+rNo+rNo+x+'" name="structureFilesLength" value="0" /> <table class="mdl-data-table bd-none"><tbody><tr class="bd-none"> '
			            +'<td colspan="5" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addStructureFileRow('+1+rNo+rNo+rNo+x+')"> <i class="fa fa-plus"></i>'
			            +'</a></tr></tbody></table> </div></div></div>'
           			    
           				//+'<span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5></div></div>'
           				+'</div></div><a class="modal-trigger btn bg-m t-c" href="#modal'+rNo+rNo+x+'">Update</a>	</td>'
           				+'<td class="no-pad"><a class="btn mob-btn waves-effect waves-light red t-c " onclick="removeStructureInternalRow('+rNo+rNo+x+','+rNo+')"> <i class="fa fa-close"></i></a></td></tr>'
           				+'<tr class="mob-add-btn"><td> <a type="button" class="btn mob-btn waves-effect waves-light bg-m t-c" onclick="addInternalTableRow('+rNo+rNo+x+','+rNo+')"> <i class="fa fa-plus"></i></a>'
           				+'</td></tr></tbody></table></td>'
           				//+'<td data-head="Structure Id" class="input-field">'
               			//+'<input id="structure_id'+rNo+'" name="structures" type="text" class="validate" placeholder="Structure Id"> </td>'
               			+'<td class="mobile_btn_close line"><a  onclick="removeStructureRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
       					+'</td></tr>';

    				 $('#structureTableBody').append(html);
    				 $("#rowNo").val(rNo);
    				 $('.searchable').select2();  
    				 $('select:not(.searchable)').formSelect();
    				 $('.modal').modal(); 
            } 
         
			function removeStructureRow(rowNo){
				$("#structureRow"+rowNo).remove();
			}
			
			function addInternalTableRow(ind,tableNo){
				/* var work_id_fk = $("#work_id_fk").val();
	    	 	getContractsList(work_id_fk); */
				var rowNo=$('#internalRow'+ind).val();
				var rNo = Number(rowNo)+1;
				/* var len = $("#internalTable"+rNo+" tr").length-1;
				$("#subRowsLengths"+rNo).val(len); */
				var structureType = $("#structure_type_fks"+tableNo).val();
				var x = Math.floor(Math.random() * (100 - 500 + 1) + 500)
				var y = Math.floor(Math.random() * (10 - 50 + 100) + 5)
				var html = '<tr id="internalTableRow'+rNo+tableNo+'"><td><input type = "hidden" name="structure_type_fks" id="structure_type_fks'+rNo+tableNo+rNo+'"/> <input id="structure_id'+rNo+'_'+tableNo+'" name="structures" type="text" class="validate"'
						   +'placeholder="Structure Id"></td>'
						   +'<td style="text-align:center;"><div id="modal'+rNo+tableNo+'" class="modal"> '
						    +'<div class="modal-content"><h5 class="modal-header">Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>'
						    +'<div class="container"><div class="row"><div class="col s6 input-field"><p class="searchable_label">Work Status </p>'+
						    '<select id="work_status_fk'+rNo+tableNo+x+'" name="work_status_fks"  class="searchable validate-dropdown"> '+
						    	'<option value="">Select</option>'
						    	 <c:forEach var="obj" items="${workStatusList }">
				              	 	+'<option value="${obj.work_status_fk }">${obj.work_status_fk}</option>'
				                 </c:forEach>
				            +'</select><span id="work_status_fk'+rNo+rNo+'Error" class="error-msg" ></span>'
						    +'</div><div class="col s6 input-field"><input id="target_date'+rNo+rNo+'" name="target_dates" type="text" class="validate datepicker" value="" >'
						    +'<label for="target_date'+rNo+rNo+'">Original Target Date </label><button type="button" id="target_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
						    +'<span id="target_date'+rNo+rNo+'Error" class="error-msg" ></span></div></div>'
						    
						    +'<div class="row"><div class="col s6 input-field amount-dropdown"><i class="material-icons amount-symbol cost">₹</i>'
						    +'<input id="estimated_cost'+rNo+rNo+'" name="estimated_costs" type="number" class="validate" value="" min="0.01" step="0.01"><label for="estimated_cost'+rNo+rNo+'">Estimated Cost</label>'
						    +'<span id="estimated_cost'+rNo+rNo+'Error" class="error-msg" ></span><span id="estimated_cost_units'+rNo+rNo+x+'Error" class="error-msg right" ></span>'
						    +'<select class="validate-dropdown " id="estimated_cost_units'+rNo+rNo+x+'" name="estimated_cost_unitss">'+
						    '<option value="">Select</option>'
							   <c:forEach var="obj" items="${unitsList }">
				              	+'<option value="${obj.value }">${obj.unit}</option>'
				               </c:forEach>
						    +'</select></div>'
						    
						    +'<div class="col s6 input-field"><input id="construction_start_date'+rNo+rNo+'" name="construction_start_dates" type="text" class="validate datepicker" value="" >'
						    +'<label for="construction_start_date'+rNo+rNo+'">Construction Start Date </label><button type="button" id="construction_start_date'+rNo+rNo+'_icon" class="datepicker-button">'
						    +'<i class="fa fa-calendar"></i></button><span id="construction_start_date'+rNo+rNo+'Error" class="error-msg" ></span></div>'
						    +'<div class="col s6 input-field " ><input id="revised_completion'+rNo+rNo+'" name="revised_completions" type="text" class="validate datepicker" >'
						    +'<label for="revised_completion'+rNo+rNo+'" >Target completion Date </label><button type="button" id="revised_completion'+rNo+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar">'
						    +'</i></button><span id="revised_completion'+rNo+rNo+'Error" class="error-msg" ></span></div></div>'
						    +'<div class="row"><div class="col s12  input-field "><textarea id="remarks'+rNo+rNo+'" name="remarkss" class="pmis-textarea" data-length="1000" maxlength="1000"></textarea>'
						    +'<label for="remarks'+rNo+rNo+'">Remarks</label><span id="remarks'+rNo+rNo+'Error" class="error-msg" ></span></div></div></div>'
						    +'<div class="row"><div class="col m12"><table id="structureResponsibleTable'+y+rNo+rNo+rNo+x+'" class="mdl-data-table mobile_responsible_table"><thead><tr>'
						    +'<th style="width:50%">Contract </th><th style="text-align : left;">Responsible Executives </th><th style="width:8%">Action</th></tr></thead>'
						    +'<tbody id="structureResponsibleBody'+y+rNo+rNo+rNo+x+'"><input type="hidden"  id="contracts_id_fk'+y+rNo+rNo+rNo+x+'" name="contracts_id_fk" /><tr id="structureResponsibleRow'+y+rNo+rNo+rNo+x+'"><td data-head="Department" class="input-field">'
						    +'<select class="searchable validate-dropdown contracts_id_fk" id="contract_id_fk'+y+rNo+rNo+rNo+x+'" name="contracts"  onchange="getRowsCount('+y+rNo+rNo+rNo+x+','+rNo+rNo+tableNo+');">'+
						    '<option value="">Select</option>'
						     	<c:forEach var="obj" items="${contractsList }">
				              	   +'<option value="${obj.contract_id_fk }">${obj.contract_short_name}</option>'
				                </c:forEach>
						    +'</select></td>'
						    +'<td data-head="Select Executives" class="input-field h-auto"><input type="hidden" id="responsible_people_id_fk'+y+rNo+rNo+rNo+x+'" name="responsible_people_id_fks" /><select class="searchable validate-dropdown" id="responsible_people_id_fks'+y+rNo+rNo+rNo+x+'" multiple onchange="executivesToStringMethod('+y+rNo+rNo+rNo+x+')">'
						    +'<option value="">Select</option>'
							    <c:forEach var="obj" items="${responsiblePeopleList }">
				              	 +'<option value="${obj.user_id }">${obj.designation}</option>'
				             	</c:forEach>
						    +'</select></td><td class="mobile_btn_close"><a href="javascript:void(0);" onClick="removeStructureResponsible('+y+rNo+rNo+rNo+x+','+rNo+');deleteRowsCount('+y+rNo+rNo+rNo+x+','+rNo+rNo+tableNo+');" class="btn waves-effect waves-light red t-c ">'
						    +'<i class="fa fa-close"></i></a></td></tr></tbody></table>'
						    +'<input type="hidden" value="0" id="structureResponsibleLength'+y+rNo+rNo+rNo+x+'"><table class="mdl-data-table bd-none"><tbody>  <tr class="bd-none">'
						    +'<td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c " onclick="addstructureResponsibleRow('+y+rNo+rNo+rNo+x+');"> <i class="fa fa-plus"></i></a></td>'
						    +'</tr></tbody></table></div></div>'
						    
						    + '<div class="row"><h5 class="center-align">Structure Details</h5><div class="col s12 m10 offset-m1"><table id="structureDetailsTable'+y+rNo+rNo+rNo+x+'" class="mdl-data-table">'
				            +'<thead><tr><th>Structure Detail </th><th>Value </th><th>Action</th></tr></thead><tbody id="structureDetailsTableBody'+y+rNo+rNo+rNo+x+'"><input type="hidden" id="structure_details'+y+rNo+rNo+rNo+x+'" name="structure_detailss"><tr id="structureDetailsRow'+y+rNo+rNo+rNo+x+'">'
				            +'<td><input id="structure_detail_names'+y+rNo+rNo+rNo+x+'" name="structure_details" onkeyup="detailsMethod('+y+rNo+rNo+rNo+x+');" type="text" class="validate" value="" placeholder="Detail name"></td> <td>'
				            +'<input id="structure_detail_values'+y+rNo+rNo+rNo+x+'" name="structure_values" type="text" class="validate" value="" placeholder="Value"></td> <td class="mobile_btn_close">'
				            +'<a onclick="removeStructureDetail('+y+rNo+rNo+rNo+x+','+rNo+');detailsMethod('+y+rNo+rNo+rNo+x+');"class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr></tbody></table>'
				            +'<input type="hidden" value="0" id="structureDetailsLength'+y+rNo+rNo+rNo+x+'"><table class="mdl-data-table bd-none"><tbody><tr class="bd-none">'
				            +'<td colspan="3" class="bd-none"><a class="btn waves-effect waves-light bg-m t-c " onclick="addstructureDetailRow('+y+rNo+rNo+rNo+x+')"> <i class="fa fa-plus"></i></a></td></tr>'
				            +'</tbody></table></div></div>'
				            
				            + '<div class="row"><div class="col s12"><div class="row fixed-width"><h5 class="center-align"><span class="div-header">Documents</span></h5>'
				            +'<table class="mdl-data-table update-table mobile_responsible_table" id="structureFilesTable'+1+ind+rNo+rNo+'"><thead><tr><th class="min-180">File Type</th>'
				            +'<th>Name</th><th>Attachment</th><th style="display:none;"></th><th>Action</th></tr></thead><tbody id="structureFilesBody'+1+ind+rNo+rNo+'">'
				            +'<input type="hidden"  id="structureFiless'+1+ind+rNo+rNo+'" name="structureFiless" /><tr id="structureFilesRow'+1+ind+rNo+rNo+'"><td data-head="File Type" class="input-field"><select name="structure_file_types" id="structure_file_types'+1+ind+rNo+rNo+'" '
				            +'class="validate-dropdown searchable"><option value="">Select</option>'
					            <c:forEach var="obj" items="${fileType }">
			              		 +'<option value="${obj.structure_file_type }">${obj.structure_file_type}</option>'
			             	 	</c:forEach>
				            +'</select></td><td data-head="Name" class="input-field">'
				            +'<input id="structureDocumentNames'+1+ind+rNo+rNo+'" name="structureDocumentNames" type="text" class="validate" placeholder="Name"></td>'
				            +'<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto"><div class="t-c"><input type="file" id="structureFiles'+1+ind+rNo+rNo+'" '
				            +'name="structureFiles" accept="image/*" onchange="documentMethod('+1+ind+rNo+rNo+');"><label for="structureFiles'+1+ind+rNo+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label></div>'
				            +'<div class="file-path-wrapper"><input class="file-path validate" type="text" id="structureFileNames'+1+ind+rNo+rNo+'" name="structureFileNames"></div></td>'
				            +'<td style="display:none;"><input type="hidden" id="structure_file_ids'+1+ind+rNo+rNo+'" name="structure_file_ids" /></td><td class="mobile_btn_close">'
				            +'<a onclick="removeStructureFileRow('+1+ind+rNo+rNo+','+rNo+');documentMethod('+1+ind+rNo+rNo+');" class="btn red"><i class="fa fa-close"></i></a></td></tr></tbody></table>'
				            +'<input type="hidden" id="structureFilesLength'+1+ind+rNo+rNo+'" name="structureFilesLength" value="0" /> <table class="mdl-data-table bd-none"><tbody><tr class="bd-none"> '
				            +'<td colspan="5" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addStructureFileRow('+1+ind+rNo+rNo+')"> <i class="fa fa-plus"></i>'
				            +'</a></tr></tbody></table> </div></div></div>'
				            
						   //+'<h5 class="modal-header">Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5></div></div>'
	           				+'</div></div><a class="modal-trigger btn bg-m t-c" href="#modal'+rNo+tableNo+'">Update</a>	</td>'
						   +'<td class="no-pad"><a class="btn mob-btn waves-effect waves-light red t-c" '
						   +'onclick="removeStructureInternalRow('+rNo+tableNo+','+tableNo+')"> <i class="fa fa-close"></i></a></td></tr>';

			   $('#structureRow'+tableNo+'-internalTable tbody tr.mob-add-btn').prev().after(html);
			   $("#internalRow"+ind).val(rNo);
			   $("#structure_type_fks"+rNo+tableNo+rNo).val(structureType);
			   $('.modal').modal(); 
			   $('.searchable').select2(); 
			   $('select:not(.searchable)').formSelect();
			  // $("#subRowsLengths"+ind).val(rNo+1);
			}
			
			function removeStructureInternalRow(rowNo,ind){
				$("#internalTableRow"+rowNo).remove();
				var row = $('#structureRow'+ind+'-internalTable tbody tr td input')[0]; //get the first row of the tbody
				var structureId = $(row).attr('id');
				$("#"+structureId).removeAttr('name');
				var len = $("#internalTable"+ind+" tr").length-1;
				var rNo = Number(len) - 1;
				$("#internalRow"+ind).val(rNo); 
				//$("#subRowsLengths"+ind).val(len);
			}
			
			function addstructureDetailRow(ind){
				 var rowNo = $("#structureDetailsLength"+ind).val();
				 var rNo = Number(rowNo)+1;
				 var x = Math.floor(Math.random() * (500 - 900 + 1) + 900)
				 var index = rNo+ind+x;
				 var html = '<tr id="structureDetailsRow'+index+'">'
			            +'<td><input id="structure_detail_names'+index+'" name="structure_details" type="text" class="validate" onkeyup="detailsMethod('+ind+');" placeholder="Detail name"></td> <td>'
			            +'<input id="structure_detail_values'+index+'" name="structure_values" type="text" class="validate" value="" placeholder="Value"></td> <td class="mobile_btn_close">'
			            +'<a onclick="removeStructureDetail('+index+','+rNo+');detailsMethod('+ind+');"class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
			            
				 $('#structureDetailsTableBody'+ind).append(html); 
				 $("#structureDetailsLength"+ind).val(rNo);
				 $('.searchable').select2(); 
				 $('select:not(.searchable)').formSelect();
			}
			
			function detailsMethod(index){
			   var vals = [];
			   $('#structureDetailsTableBody'+index+' input[name="structure_details"]').each(function(i,val){vals.push($(this).val());   });
        	   vals = vals.join(',_,');
        	   vals = vals.replace(/,_,\s*$/, '');
			   $('#structure_details'+index ).val(vals);
			}
			
			 function addstructureResponsibleRow(ind){
					/*  var work_id_fk = $("#work_id_fk").val();
		    	 	 getContractsList(work_id_fk); */
				   	 var rowNo = $("#structureResponsibleLength"+ind).val();
					 var rNo = Number(rowNo)+1;
					 var no = 0;
					 var x = Math.floor(Math.random() * (500 - 900 + 1) + 900)
					 var index = rNo+ind+x;
					 var html = '<tr id="structureResponsibleRow'+index+'" class=""> <td data-head="Department" class="input-field">'
					 			+'<select class="searchable validate-dropdown contracts_id_fk" name="contracts" id="contract_id_fk'+index+'" onchange="getRowsCount('+ind+','+index+');">'+
					 			'<option value="">Select</option>'
					 			 <c:forEach var="obj" items="${contractsList }">
				              	    +'<option value="${obj.contract_id_fk }">${obj.contract_short_name}</option>'
				                 </c:forEach>
					 			+'</select></td> <td data-head="Select Executives" class="input-field h-auto"><input type="hidden" id="responsible_people_id_fk'+index+'" name="responsible_people_id_fks" /> <select class="searchable validate-dropdown" '
					 			+' id="responsible_people_id_fks'+index+'" multiple onchange="executivesToStringMethod('+index+')"> '+
					 			'<option value="">Select</option>'
					 			 <c:forEach var="obj" items="${responsiblePeopleList }">
				              		 +'<option value="${obj.user_id }">${obj.designation}</option>'
				             	 </c:forEach>
					 			+'</select> </td>'
					 			+'<td class="mobile_btn_close">	<a onclick="removeStructureResponsible('+index+');deleteRowsCount('+index+','+ind+');" class="btn waves-effect waves-light red t-c "> '
					 			+'<i class="fa fa-close"></i></a> </td> </tr>';
						 $('#structureResponsibleBody'+ind).append(html); 
						 $("#structureResponsibleLength"+ind).val(rNo);
						 $('.searchable').select2();
			   }  
			 
			 function addStructureFileRow(ind){
			   	 var rowNo = $("#structureFilesLength"+ind).val();
					 var rNo = Number(rowNo)+1;
					 var no = 0;
					 var x = Math.floor(Math.random() * (500 - 900 + 1) + 900)
					 var index = rNo+ind+x;
					 var html = '<tr id="structureFilesRow'+index+rNo+'"><td data-head="File Type" class="input-field"><select name="structure_file_types" id="structure_file_types'+index+rNo+'" '
				        +'class="validate-dropdown searchable"><option value="">Select</option>'
					        <c:forEach var="obj" items="${fileType }">
		              		 +'<option value="${obj.structure_file_type }">${obj.structure_file_type}</option>'
		             	 	</c:forEach>
				        +'</select></td><td data-head="Name" class="input-field">'
				        +'<input id="structureDocumentNames'+index+rNo+'" name="structureDocumentNames" type="text" class="validate" placeholder="Name"></td>'
				        +'<td data-head="Attach Photo" class="input-field cell-disp-inb file-field h-auto"><div class="t-c"><input type="file" id="structureFiles'+index+rNo+'" '
				        +'name="structureFiles" accept="image/*" onchange="documentMethod('+ind+');"><label for="structureFiles'+index+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label></div>'
				        +'<div class="file-path-wrapper"><input class="file-path validate" type="text" id="structureFileNames'+index+rNo+'" name="structureFileNames"></div></td>'
				        +'<td style="display:none;"><input type="hidden" id="structure_file_ids'+index+rNo+'" name="structure_file_ids" /></td><td class="mobile_btn_close">'
				        +'<a onclick="removeStructureFileRow('+index+rNo+','+rNo+');documentMethod('+ind+');" class="btn red"><i class="fa fa-close"></i></a></td></tr>';
				        
						 $('#structureFilesBody'+ind).append(html); 
						 $("#structureFilesLength"+ind).val(rNo);
						 $('.searchable').select2();
			   }  
			 function documentMethod(index){
				   var vals = [];
				   $('#structureFilesBody'+index+' input[name="structureFiles"]').each(function(i,val){vals.push($(this).val());   });
	        	   vals = vals.join(',_,');
	        	   vals = vals.replace(/,_,\s*$/, '');
				   $('#structureFiless'+index ).val(vals);
				}
			 function removeStructureResponsible(indexs,rowNo){
				 $('#structureResponsibleRow'+indexs).remove();
			 }
			 function removeStructureDetail(indexs,rowNo){
				 $('#structureDetailsRow'+indexs).remove();
			 }
			 function removeStructureFileRow(indexs,rowNo){
				 $('#structureFilesRow'+indexs).remove();
			 }
			
			function setStructureTypes(rNo){
				 var structureType = $("#structure_type_fks"+rNo).val();
				$('#structureRow'+rNo+'-internalTable input[name="structure_type_fks"]').each(function(index,val){ $(val).val(structureType);});
				var row = $('#structureRow'+rNo+'-internalTable tbody tr td input')[0]; //get the first row of the tbody
				var structureId = $(row).attr('id');
				if (structureId.indexOf("structure_type_fks") >= 0){
					$("#"+structureId).removeAttr('name');
				}
				
			}
			
			
		    var validator =	$('#structureForm').validate({
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
		  			 	  },"department_fk": {
		  		 		    required: true
		  			 	  }
		  		 	},
		  		    messages: {
		  		 		 "project_id_fk": {
		  				 	required: 'Required',
		  			 	  },"work_id_fk": {
		  			 		required: 'Required'
		  			 	  },"contract_id_fk": {
		  		 			required: 'Required'
		  		 	  	  },"department_fk": {
		  		 			required: 'Required'
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
						}else if(element.attr("id") == "department_fk" ){
							document.getElementById("department_fkError").innerHTML="";
						 	error.appendTo('#department_fkError');
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
	            $('[name="structure_type_fks"]').on('change', function() {
	    			$("select[name=structure_type_fks]").each(function(){
	    				var idNo = (this.id).replace('structure_type_fks',''); 
	    				if($.trim(this.value) == "" && $('#structure_id'+idNo).val() != ""){ 
	            			$('#structure_id'+idNo+'Error').text('Requried');
	    				}else{
	    					$('#structure_id'+idNo+'Error').text('');
	    				} 
	                });
	    			
	    		});
	             
	           function executivesToStringMethod(index){
	        	     var val = $('#responsible_people_id_fks'+index).val();
	        	     val = val.join(',');
	            	 var val3 = $('#responsible_people_id_fk'+index).val(val);
	           }
	           
	           function getRowsCount(count,index){
	        	   var len = $("#structureResponsibleBody"+count+" tr").length
	        	   var vals = [];
	        	   $('#structureResponsibleBody'+count+' select[name="contracts"]').each(function(i,val){vals.push($(this).val());   });
	        	   vals = vals.join(',');
	        	   vals = vals.replace(/,\s*$/, '');
				   $('#contracts_id_fk'+count).val(vals);
	           }
	           function deleteRowsCount(count,index){
	        	   var len = $("#structureResponsibleBody"+count+" tr").length
	        	   var vals = [];
	        	   $('#structureResponsibleBody'+count+' select[name="contracts"]').each(function(i,val){vals.push($(this).val());   });
	        	   vals = vals.join(',');
	        	   vals = vals.replace(/,\s*$/, '');
				   $('#contracts_id_fk'+count).val(vals);
	        	   
	           }
    </script>
</body>
</html>