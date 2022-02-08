<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R & R BSES - Update Forms - PMIS</title>
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
		.internal-table .btn,
		.internal-table-add-btn .btn{
			padding:0 8px;
			line-height:2rem;
			height:2rem;
			cursor:pointer;
			z-index:1 !important;
		}
		.internal-table .btn >.fa,
		.internal-table-add-btn .btn >.fa{
			font-size:1rem;
		}
		.internal-table tr{
			border:none;
		}
		.internal-table td{
			padding-top:0;
			padding-bottom:0;
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
				td[data-head="Date of Nomination"] {
				    max-width: 200px;
				    width: 20% !important;
				}
				td[data-head="Date of Nomination"] .datepicker~button{
				    bottom:1.5rem;
				}
				.internal-table-add-btn{
					position:absolute;
					right:.65rem;
					bottom:2.5rem;
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
                               	    <c:if test="${action eq 'edit'}">Update R & R BSES</c:if>
									<c:if test="${action eq 'add'}"> Add R & R BSES</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-rr-bses" id="RRbsesForm" name="RRbsesForm" method="post"   enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-rr-bses" id="RRbsesForm" name="RRbsesForm" method="post"  enctype="multipart/form-data" >
						  </c:if>
						  <input type="hidden" name="bses_id" id="bses_id" value="${rrBsesDeatils.bses_id }"  />
						  
						    <div class="container container-no-margin">
                            <div class="row" style="margin-top:1rem;">
						    <c:if test="${action eq 'add'}">	
                                <div class="col s6 m4 l4 input-field ">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"   onchange="getWorksList(this.value);">
                                         <option value="" >Select</option>
                                          <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach> 
                                         </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="getContractsList(this.value);resetProjectsDropdowns(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${workList }">
                                      	   <option value= "${ obj.work_id_fk}">${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach> 
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                             	<div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label"> Contract </p>
                                    <select class="searchable validate-dropdown" id="contract_id_fk" name="contract_id_fk" onchange="resetWorksAndProjectsDropdowns();">
                                        <option value="" >Select</option>
                                       <c:forEach var="obj" items="${contractList }">
                                      	   <option workId="${obj.work_id_fk }" value= "${ obj.contract_id}">${obj.contract_id} <c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                      <span id="contract_fkError" class="error-msg" ></span>
                                </div>
                            </c:if>
 							<c:if test="${action eq 'edit'}">		                             
	                                <div class="col s6 m4 l4 input-field ">
								    	<label for="project_id_fk">Project </label>
								    	<input type="hidden" name="project_id_fk" id="project_id_fk" value="${rrBsesDeatils.project_id_fk }"  />
								    	<input type="text"  value="${rrBsesDeatils.project_id_fk } -  ${rrBsesDeatils.project_name }" readonly />
								    </div> 
	                                <div class="col s6 m4 l4 input-field"> 
	                                	<label for="work_id_fk">Work </label>
	                                	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${rrBsesDeatils.work_id_fk }"  />
	                                	<input type="text" value="${rrBsesDeatils.work_id_fk } - ${rrBsesDeatils.work_short_name }" readonly />
	                                </div>
	                           		<div class="col s12 m4 l4 input-field"> 
	                                	<label for="contract_id_fk">Contract </label>
	                                	<input type="hidden" name="contract_id_fk" id="contract_id_fk" value="${rrBsesDeatils.contract_id_fk }"  />
	                                	<input type="text" value="${rrBsesDeatils.contract_id_fk } - ${rrBsesDeatils.contract_short_name }" readonly />
	                                </div>
                              </c:if>
							</div>
							<div class="row">
								 <div class="col s12 m12 input-field">
                                    <textarea id="agency_name" name="agency_name" class="pmis-textarea" data-length="1000">${rrBsesDeatils.agency_name }</textarea>
                                    <label for="agency_name">Agency Name</label>
                                </div>
							</div>  
							<div class="row">
								 <div class="col s12 m4 input-field">
                                     <p class="center-align" style="margin-top:1rem;">
									      <label>
									        <input type="hidden"   id="report_submission_to_mrvcs" name="report_submission_to_mrvc" 
									        value ="${rrBsesDeatils.report_submission_to_mrvc}"/>
									         <input type="checkbox" class="filled-in"  <c:if test="${rrBsesDeatils.report_submission_to_mrvc eq 'Yes'}">  checked</c:if> id="report_submission_to_mrvc" />
									        <span>Report Submission to MRVC</span>
									      </label>
								    </p>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <p class="center-align" style="margin-top:1rem;">
									      <label>
									        <input type="hidden"   id="submission_to_mmrdas" name="report_submission_to_mmrda" 
									        value ="${rrBsesDeatils.report_submission_to_mmrda}"/>
									         <input type="checkbox" class="filled-in"  <c:if test="${rrBsesDeatils.report_submission_to_mmrda eq 'Yes'}">  checked</c:if> id="report_submission_to_mmrda" />
									        <span>Report Submission to MMRDA </span>
									      </label>
								    </p>   
                                </div>
							</div>
						</div>
					
													
								<div class="col s12">
										<div class="row fixed-width">
									       <h6 class="center-align">Committee Details</span></h6>
									        <div class="table-inside">
									            <table id="committeeDetailsTable" class="mdl-data-table mobile_responsible_table" >
									                <thead>
									                    <tr>
									                        <th>Date of Nomination </th>
															<th>Details of Committee </th>
															<th>Remarks </th>
															<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th style="width:8%">Action</th></c:if>
									                    </tr>
									                </thead>
									                <tbody id="committeeDetailsTableBody">
									                <c:choose>
				                                        <c:when test="${not empty rrBsesDeatils.detailsList }" >				                                          
				                                		  <c:forEach var="dObj" items="${rrBsesDeatils.detailsList }" varStatus="index"> 	
				                                		 		                                		      
											                  <tr id="committeeDetailsRow${index.count }">
											                        <td data-head="Date of Nomination" class="input-field">
											                              <input id="nomination_date${index.count }" name="date_of_nominations" type="text" value="${dObj.date_of_nomination }" class="validate datepicker" placeholder="Date of Nomination">
										                                  <button type="button" id="nomination_date${index.count}_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
										                                  <span id="nomination_date${index.count}Error" class="error-msg" ></span>
											                        </td>
											                         <td>
											                        	<div class="internal-table-add-btn">
											                        		<a class="btn waves-effect waves-light bg-m t-c" onclick="addInternalBodyRow('${index.count}')"> <i class="fa fa-plus"></i></a>
											                        		
											                        	</div>
											                        	<table>
											                        	<tbody id="committeeDetailsInternalBody${index.count}" class="internal-table">
											                        	<input type="hidden" id="details${index.count}" name ="detailss" />	 
											                        	 <c:choose>
									                                        <c:when test="${not empty dObj.comiteDetailsList }" >				                                          
									                                		  <c:forEach var="fObj" items="${dObj.comiteDetailsList }" varStatus="indexx"> 
									                                		   
												                        		<tr id="committeeDetailsInternalRow${index.count}">
												                        			<td>
												                        				
												                        				<input type="hidden" id="internalRow${index.count}" value="${fn:length(dObj.comiteDetailsList) }">
												                        				<textarea id="committee_details${indexx.count}${index.count}"  onkeyup="getRowsCount('${index.count}');" name ="details" class="pmis-textarea" placeholder="Details">${fObj.details }</textarea>
												                        			</td>
												                        			<td>
												                        				<a class="btn waves-effect waves-light red t-c" onclick="removeInternalBodyRow('${indexx.count}');deleteRowsCount('${index.count}');"> <i class="fa fa-close"></i></a>
												                        			</td>
												                        		</tr>
												                        		<script>
												                        			   var len = $("#committeeDetailsInternalBody${indexx.count} tr").length
												                        			   var vals = [];
												                        			   $('#committeeDetailsInternalBody${indexx.count} textarea[name="details"]').each(function(i,val){vals.push($(this).val());   });
												                        			   vals = vals.join(',_,');
												                        			   vals = vals.replace(/,_,\s*$/, '');
												                        			   $("#details${indexx.count}").val(vals);
												                        		  
												                        		</script>
												                        		</c:forEach>
							                                           		   </c:when>
							                                             	 <c:otherwise>
							                                             	 	<tr id="committeeDetailsInternalRow${index.count}">
												                        			<td>
												                        				<input type="hidden" id="internalRow${index.count}" value="0">
												                        				
												                        				<textarea id="committee_details${index.count}" onkeyup="getRowsCount('${index.count}');" name ="details" class="pmis-textarea" placeholder="Details"></textarea>
												                        			</td>
												                        			<td>
												                        				<a class="btn waves-effect waves-light red t-c" onclick="removeInternalBodyRow('${index.count}');deleteRowsCount('${index.count}');"> <i class="fa fa-close"></i></a>
												                        			</td>
												                        		</tr>
							                                             	</c:otherwise>
                                            							  </c:choose>
											                        	</tbody>
											                        	</table>
											                        	
										
	           
									                        	</td>
											                        <td data-head="Remarks" class="input-field">
											                            <textarea id="committee_remarks${index.count }" name ="committee_details_remarkss" class="pmis-textarea" placeholder="Remarks">${dObj.committee_details_remarks }</textarea>
									                                    <span id="committee_remarks${index.count }Error" class="error-msg"></span>
											                        </td>
											                        <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
												                        <td class="mobile_btn_close">
												                            <a onclick="removeCommitteeDetailsRow('${index.count }');"
												                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
												                        </td>
											                        </c:if>
											                    </tr>											                  
									                	</c:forEach>
                                           			</c:when>
                                             		<c:otherwise>
									                    <tr id="committeeDetailsRow0">
									                        <td data-head="Date of Nomination" class="input-field">
									                              <input id="nomination_date0" name="date_of_nominations" type="text" class="validate datepicker" placeholder="Date of Nomination">
								                                  <button type="button" id="nomination_date0_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> 											                              
								                                  <span id="nomination_date0Error" class="error-msg" ></span>
									                        </td>
									                        <td>
									                        	<div class="internal-table-add-btn">
									                        		<a class="btn waves-effect waves-light bg-m t-c" onclick="addInternalBodyRow('0')"> <i class="fa fa-plus"></i></a>
									                        		<input type="hidden" id="internalRow0" value="0">
									                        	</div>
									                        	<table>
									                        	<tbody id="committeeDetailsInternalBody0" class="internal-table">
									                        	<input type="hidden" id="details0" name ="detailss" />
									                        		<tr id="committeeDetailsInternalRow0">
									                        			<td>
									                        				
									                        				<textarea id="committee_details00" name ="details" onkeyup="getRowsCount('0');" class="pmis-textarea" placeholder="Details"></textarea>
									                        			</td>
									                        			<td>
									                        				<a class="btn waves-effect waves-light red t-c" onclick="removeInternalBodyRow('0');deleteRowsCount('0');"> <i class="fa fa-close"></i></a>
									                        			</td>
									                        		</tr>
									                        	</tbody>
									                        	</table>
									                        </td>
									                        <td data-head="Remarks" class="input-field">
									                            <textarea id="committee_remarks0" name ="committee_details_remarkss" class="pmis-textarea" placeholder="Remarks"></textarea>
							                                    <span id="committee_remarks0Error" class="error-msg"></span>
									                        </td>
									                        <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
										                        <td class="mobile_btn_close">
										                            <a onclick="removeCommitteeDetailsRow('0');"
										                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
										                        </td>
									                        </c:if>
									                    </tr>
									              </c:otherwise>
                                            	</c:choose>
									                </tbody>
									            </table>
									            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
									            <table  class="mdl-data-table" style="margin-bottom: 30px">
			                                        <tbody>                                          
			                                            <tr>
			                                   				<td colspan="3"  ><a class="btn waves-effect waves-light bg-m t-c "  onclick="addCommitteeDetailsRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table> 
			                                    </c:if>
			                                    <c:choose>
				                                    <c:when test="${not empty rrBsesDeatils.detailsList && fn:length(rrBsesDeatils.detailsList) gt 0 }">
				                                		<input type="hidden" id="committeeDetailsRowNo"  name="committeeDetailsRowNo" value="${fn:length(rrBsesDeatils.detailsList) }" />
				                                	</c:when>
				                                 	<c:otherwise>
				                                 		<input type="hidden" id="committeeDetailsRowNo"  name="committeeDetailsRowNo" value="0" />
				                                 	</c:otherwise>
				                                 </c:choose>
							                                    
									        </div>
									    </div>
									</div>
							
							<div class="container container-no-margin">
	                            <div class="col s12">
	                                <div class="row fixed-width">
	                                    <!-- <h5 class="center-align"><span class="div-header">Attachments</span></h5>  -->
	                                    <h6 class="center-align">Attachments</span></h6> 
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
	                                            <tbody id="attachmentsTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty rrBsesDeatils.filesList  && fn:length(rrBsesDeatils.filesList ) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${rrBsesDeatils.filesList }" varStatus="index">  
			                                                <tr id="attachmentRow${index.count }">
			                                                	<td data-head="File Type " class="input-field">
																	<select  name="bses_file_type_fks"  id="bses_file_type_fks${index.count }"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                         			  <c:forEach var="obj" items="${fileTypeList}">
					                    					  				 <option value="${obj.bses_file_type }" <c:if test="${docObj.bses_file_type_fk eq obj.bses_file_type}">selected</c:if>>${obj.bses_file_type}</option>
					                                          			  </c:forEach>
					                               					  </select>
															    </td>
			                                                    <td data-head="Name " class="input-field"> <input id="names${index.count }" name="names" type="text" class="validate"
			                                                            placeholder="Name" value="${docObj.name }">
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="rrFiles${index.count }" name="rrFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="rrFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="attachmentFileNames${index.count }" name="attachmentFileNames" value="${docObj.attachment }">
			                                                             <span id="attachmentFileName${index.count }" class="filevalue"></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
			                                                     		<%-- <input type="hidden" id="attach_file_ids${index.count }" name="attach_file_ids" value="${docObj.id }"/> --%>
			                                                      		<a href="<%=CommonConstants2.RRBSES_FILE_SAVING_PATH%>${docObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                        
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
																<select  name="bses_file_type_fks"  id="bses_file_types0"  class="validate-dropdown searchable">
				                                   					 <option value="" >Select</option>
				                                         			  <c:forEach var="obj" items="${fileTypeList}">
				                    					  				 <option value="${obj.bses_file_type }">${obj.bses_file_type}</option>
				                                          			  </c:forEach>
				                               					  </select>
															    </td>
		                                                    <td data-head="Name " class="input-field"> <input id="attachmentNames0" name="names" type="text" class="validate"
		                                                            placeholder="Name">
		                                                    </td>
		                                                    <td data-head="Attachment" class="input-field">
		                                                        <span class="normal-btn">
		                                                            <input type="file" id="rrFiles0" name="rrFiles"
		                                                                style="display:none" onchange="getFileName('0')"/>
		                                                            <label for="rrFiles0" class="btn bg-m"><i
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
		                                        <c:when test="${not empty rrBsesDeatils.filesList && fn:length(rrBsesDeatils.filesList) gt 0 }">
		                                    		<input type="hidden" id="attachmentRowNo"  name="attachmentRowNo" value="${fn:length(rrBsesDeatils.filesList) }" />
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
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${rrBsesDeatils.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom:20px;">
                                <div class="col s6 offset-m2 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                       <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateRRBsesForm();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addRRBsesForm();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
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
    	var submission_to_mmrdas =  $('#submission_to_mmrdas').val();
    	var report_submission_to_mrvcs =  $('#report_submission_to_mrvcs').val();
    	if(submission_to_mmrdas == ''){ $("#submission_to_mmrdas").val('No')}
    	if(report_submission_to_mrvcs == ''){$("#report_submission_to_mrvcs").val('No')}
   });
	 $('#report_submission_to_mmrda').on('change', function(e){
         if($(this).prop('checked'))
         { 
             $('#submission_to_mmrdas').val('Yes');
         }else{
          	  $("#submission_to_mmrdas").val('No')
        	  $("#report_submission_to_mmrda").prop('checked',false).removeAttr('checked');
          }
   });
   $('#report_submission_to_mrvc').on('change', function(e){
         if($(this).prop('checked'))
         { 
             $('#report_submission_to_mrvcs').val('Yes');
         }else{
          	  $("#report_submission_to_mrvcs").val('No')
        	  $("#report_submission_to_mrvc").prop('checked',false).removeAttr('checked');
          }
   });
	function addAttachmentRow(){		
		 var rowNo = $("#attachmentRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var total = 0;
		 var html = '<tr id="attachmentRow'+rNo+'">'
					 +'<td data-head="File Type " class="input-field">'
							+'<select  name="bses_file_type_fks"  id="bses_file_type_fks'+rNo+'"  class="validate-dropdown searchable">'
		    					+ '<option value="" >Select</option>'
		          			<c:forEach var="obj" items="${fileTypeList}">
					  				+ '<option value="${obj.bses_file_type }">${obj.bses_file_type}</option>'
		           			  </c:forEach> 
						+ '</select></td>'
					 +'<td data-head="Name " class="input-field"> <input id="names'+rNo+'" name="names" type="text" class="validate" placeholder="Name"> </td>'
					 +'<td data-head="Attachment" class="input-field">'
					 +'<span class="normal-btn">'
					 +'<input type="file" id="rrFiles'+rNo+'" name="rrFiles" style="display:none" onchange="getFileName('+rNo+')" />'
					 +'<label for="rrFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
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
		var filename = $('#rrFiles'+rowNo)[0].files[0].name;
	    $('#attachmentFileName'+rowNo).html(filename);
	    $('#attachmentFileNames'+rowNo).val(filename);
	}
	
	
	function addCommitteeDetailsRow(){
    	 var rowNo = $("#committeeDetailsRowNo").val();
		 var rNo = Number(rowNo)+1;
		 var no = 0;
		 var html = '<tr id="committeeDetailsRow'+rNo+'"> '
		 			+'<td data-head="Date of Nomination" class="input-field"> <input id="nomination_date'+rNo+'" name="date_of_nominations" type="text" class="validate datepicker" '
		 			+'placeholder="Date of Nomination"> <button type="button" id="nomination_date'+rNo+'_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button> '											                              
        			+'<span id="nomination_date'+rNo+'Error" class="error-msg" ></span>   </td>'
   					+'<td><div class="internal-table-add-btn"> <a class="btn waves-effect waves-light bg-m t-c" onclick="addInternalBodyRow('+rNo+')"> <i class="fa fa-plus"></i></a>'
            		+'<input type="hidden" id="internalRow'+rNo+'" value="0"></div> <table> <tbody id="committeeDetailsInternalBody'+rNo+'" class="internal-table">'
            		+'<input type="hidden" id="details'+rNo+'" name ="detailss" /><tr id="committeeDetailsInternalRow'+rNo+'"> <td>	<textarea id="committee_details'+rNo+'" name ="details" onkeyup="getRowsCount('+rNo+');"class="pmis-textarea" placeholder="Details"></textarea> </td>'
            		+'<td> <a class="btn waves-effect waves-light red t-c" onclick="removeInternalBodyRow('+rNo+');deleteRowsCount('+rNo+');"> <i class="fa fa-close"></i></a> </td> </tr> </tbody> </table> </td>'
   					+'<td data-head="Remarks" class="input-field"> <textarea id="committee_remarks'+rNo+'" name ="committee_details_remarkss" class="pmis-textarea" placeholder="Remarks"></textarea>'
   					+'<span id="committee_remarks'+rNo+'Error" class="error-msg"></span>  </td>'
   				<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
      				+'<td class="mobile_btn_close"> <a onclick="removeCommitteeDetailsRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td>'
   				</c:if>
					+'</tr>';
		
			 $('#committeeDetailsTableBody').append(html); 
			 $("#committeeDetailsRowNo").val(rNo);
			 $('.searchable').select2();
    }
    
    function removeCommitteeDetailsRow(rowNo){
    	$("#committeeDetailsRow"+rowNo).remove();
    }
    
	function addInternalBodyRow(No){
    	 var rowNo = $("#internalRow"+No).val();
		 var rNo = Number(rowNo)+1;
		 var no = 0;
		 var html = '<tr id="committeeDetailsInternalRow'+rNo+'"> '
		 			+'<td> <textarea id="committee_details'+rNo+'" name ="details" onkeyup="getRowsCount('+No+');" class="pmis-textarea" placeholder="Details"></textarea> </td>'
		 			+'<td><a class="btn waves-effect waves-light red t-c" onclick="removeInternalBodyRow('+rNo+');deleteRowsCount('+No+');"> <i class="fa fa-close"></i></a></td>'
					+'</tr>';
		
			 $('#committeeDetailsInternalBody'+No).append(html); 
			 $("#internalRow"+No).val(rNo);
			 $('.searchable').select2();
    }
    
    function removeInternalBodyRow(rowNo){ 
    	$("#committeeDetailsInternalRow"+rowNo).remove();
    }
    function getWorksList(projectId) {
    	$(".page-loader").show();
        $("#work_id_fk option:not(:first)").remove();
        $("#contract_id_fk option:not(:first)").remove();
        if ($.trim(projectId) != "") {
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorkListForRRBsesForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
            	url: "<%=request.getContextPath()%>/ajax/getContractsListForRRBsesForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var contract_name = '';
                            if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                            var contract_id_fk = "${rrBsesDeatils.contract_id_fk }";
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
    function resetProjectsDropdowns(workId){
       	var projectId = '';
       	if($.trim(workId) != ''){  
           	projectId = workId.substring(0, 3); 
      			$("#project_id_fk").val(projectId);
      			$("#project_id_fk").select2();
      			$("#project_id_fkError").text("");
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
   			//workId = workId.substring(3, work_id_fk.length);
   			$("#project_id_fk").val(projectId);
   			$("#project_id_fk").select2();
   		}
   		
   		if ($.trim(projectId) != "") {
   			$("#work_id_fk option:not(:first)").remove();
            var myParams = { project_id_fk: projectId };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorkListForRRBsesForm",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                            var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                            if ($.trim(workId) != '' && val.work_id_fk == $.trim(workId)) {
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '" selected>' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
                            } else {
                                $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk) + $.trim(workName) + '</option>');
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
    
    function updateRRBsesForm(){
    	 if(validator.form()){ // validation perform
    	       	$(".page-loader").show();	
    	       	$('form input[name=date_of_nominations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
    	 			$('form input[name=committee_details_remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
    	 			$('form input[name=bses_file_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
    	  			document.getElementById("RRbsesForm").submit();			
    		 	 }
    }
    
  function addRRBsesForm(){
	   	 if(validator.form()){ // validation perform
 	       	$(".page-loader").show();	
 	       	$('form input[name=date_of_nominations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 	 			$('form input[name=committee_details_remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 	 			$('form input[name=bses_file_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
 	  			document.getElementById("RRbsesForm").submit();			
 		 	 }
 }
    
  var validator =	$('#RRbsesForm').validate({
		 errorClass: "my-error-class",
		 validClass: "my-valid-class",
		 ignore: ":hidden:not(.validate-dropdown)",
		    rules: {
		 		   "project_id_fk": {
			 		  required: true
			 	  },"work_id_fk": {
			 		  required: true
			 	  }	
		 	},
		    messages: {
		 		   "project_id_fk": {
			 		  required: 'Required'
			 	  },"work_id_fk": {
			 		  required: 'Required'
			 	  }	
	   		},
	   		errorPlacement:function(error, element){
	   		 	  if(element.attr("id") == "project_id_fk" ){
				     document.getElementById("project_id_fkError").innerHTML="";
			 	     error.appendTo('#project_id_fkError');
				 }else if(element.attr("id") == "work_id_fk" ){
				     document.getElementById("work_id_fkError").innerHTML="";
			 	     error.appendTo('#work_id_fkError');
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
         },
	   		submitHandler:function(form){
		    	//form.submit();
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
   function getRowsCount(count){
	   var len = $("#committeeDetailsInternalBody"+count+" tr").length
	   var vals = [];
	   $('#committeeDetailsInternalBody'+count+' textarea[name="details"]').each(function(i,val){vals.push($(this).val());   });
	   vals = vals.join(',_,');
	   vals = vals.replace(/,_,\s*$/, '');
	   $('#details'+count).val(vals);
   }
   function deleteRowsCount(count){
	   var len = $("#committeeDetailsInternalBody"+count+" tr").length
	   var vals = [];
	   $('#committeeDetailsInternalBody'+count+' textarea[name="details"]').each(function(i,val){vals.push($(this).val());   });
	   vals = vals.join(',_,');
	   vals = vals.replace(/,_,\s*$/, '');
	   $('#details'+count).val(vals);
	   
   }
   </script>

</body>
</html>