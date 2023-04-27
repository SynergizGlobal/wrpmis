<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update Budget</c:if>
		 <c:if test="${action eq 'add'}"> Add Budget</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/budget.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" > 
    <style>
        p a {
            color: blue
        }
    	     
        #budgetTable2 td.input-field .prefix,
        #budgetTable1 td.input-field .prefix {
            top: 1.5rem;
        }
          .input-field .searchable_label{
            font-size: .85rem;
            text-align: left;
        }
        .fixed-width {
            width: 100%;
            margin-left:auto !important;
            margin-right:auto !important;
        }
	.mdl-data-table td, .mdl-data-table th{padding: 0 5px 12px;}
	@media(max-width: 820px){
			.mdl-data-table td, .mdl-data-table th{
				padding: 0 35px 12px;
			}
		}
		@media(max-width: 575px){
			.mdl-data-table td, .mdl-data-table th{
				padding: 0 18px 12px;
			}
		}
	@media only screen and (max-width: 820px)
	.mobile_responsible_table>tbody tr td.mobile_btn_close a {
	    float: right;
	    margin-right: -25px;

  		.filevalue {
            display: block;
            margin-top: 10px;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        .select2-container--default .select2-selection--single {
    		background-color: transparent;
    	}
    	.input-field .prefix{
			top:25%;
		}
		@media only screen and (min-width:820px){
			.mdl-data-table td.mobile_btn_close{
				padding-right:inherit;
			}
		}
		
        @media only screen and (max-width: 600px) {
            .input-field .prefix~input {
                min-width: 80px;
            }
        }
        @media only screen and (max-width: 820px) {
             td.cell-disp-inb div.file-path-wrapper {
			    visibility: hidden;
			    width: 2px;
			    margin-bottom: 0;
			}
        }       

        .table-inside td span {
            text-align: center;
            display: block;
        }
           .primary-text {
            color: #118AB2;
        }
            
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
		
		.my-error-class{
		color:red!important; 
		}
				
		.input-field>label:not(.label-icon).active {
		    -webkit-transform: translateY(-24px) scale(0.8);
		    transform: translateY(-24px) scale(0.8);
		}
		
		
		@media only screen and (min-width: 820px)  {
			#budgetTableBody tr td .select2-container{
	        	width:140px !important;
	        	max-width:140px;
	        }          
		}
		
		td.input-field .select2-container--default{
			display:inline-block;
		}
		.mdl-data-table td:not(.mobile_btn_add) {
			vertical-align: baseline;
			padding: 0 1px 12px;
		}
		label.my-error-class{
			transform: none;
		    position: relative;
		    font-size: .85rem;
		    word-break: break-all;
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
	                             <c:if test="${action eq 'edit'}">Update Budget</c:if>
								 <c:if test="${action eq 'add'}"> Add Budget</c:if>
							  </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">				                
		                	<form action="<%=request.getContextPath() %>/update-budget" id="budgetForm" name="budgetForm" method="post"   enctype="multipart/form-data">
                      </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-budget" id="budgetForm" name="budgetForm" method="post"   enctype="multipart/form-data">
					  </c:if>
                        
                    <div class="container container-no-margin">
						   <c:if test="${action eq 'add'}">	
                            <div class="row">
                                <div class="col s6 m4 l6 input-field offset-m2">
                                    <p class="searchable_label"> Project <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                 	   onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l6 input-field">
                                    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" 
                                    		onchange="resetProjectsDropdowns(this.value);">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${worksList }">
	                                      	   <option value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                         </c:forEach>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                             </c:if>
                             <c:if test="${action eq 'edit'}">	
                              <div class="row" id="center" style="text-align:center;">
	                            
	                       		  <div class="col s6 m4 l6 input-field offset-m2">
										<p class="searchable_label"> Project <span class="required">*</span></p>
	                                    <input type="text" value="${budgetDetails.project_id_fk} - ${budgetDetails.project_name}" readonly />
								  </div> 
								  <div class="col s6 m4 l6 input-field"> 
									    <p class="searchable_label"> Work <span class="required">*</span></p>
                                     	<input type="text"  value="${budgetDetails.work_id_fk} - ${budgetDetails.work_short_name}" readonly />
                                     	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${budgetDetails.work_id_fk}" readonly />
	                              </div>
                              </div> 
                             </c:if>
                             </div>
                    
						<div class="row fixed-width">
							<h5 class="center-align">Budget Details</h5>
							<div class="table-inside">
								<table id="budgetFormTable" class="mdl-data-table mobile_responsible_table">
									<thead>
										<tr>
											<th>Financial Year</th>
											<th>Budget Estimate <br>(in Cr)	</th>
											<th>Revised Estimate <br>(in Cr)</th>
											<th>Final Estimate <br>(in Cr)	</th>
											<th>Budget Grant <br>(in Cr)</th>
											<th>Revised Grant <br>(in Cr)</th>
											<th>Final Grant <br>(in Cr)	</th>
											<th>Target <br>(in Cr)</th>
											<th>Attachment</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="budgetTableBody">
									 <c:choose>
                                      	 <c:when test="${not empty budgetDetails.budget && fn:length(budgetDetails.budget) gt 0 }">
                                       	  <c:forEach var="bObj" items="${budgetDetails.budget }" varStatus="index"> 
										   <tr id="budgetRow${index.count }">
											<td data-head="Financial Year" class="input-field">
											   <div style="display:none"> <input type="hidden" name="budget_ids" id="budget_ids${index.count }" value="${bObj.budget_id}" /></div>
												<select class="searchable validate-dropdown"
													name="financial_year_fks" id="financial_year_fks${index.count }">
													<option value="">Select Financial Year</option>
													<c:forEach var="obj" items="${financialYearList}">
														<option value="${obj.financial_year }"
															<c:if test="${bObj.financial_year_fk eq obj.financial_year }">selected</c:if>>${obj.financial_year }</option>
													</c:forEach>
												</select>
											</td>
											<td data-head="Budget Estimate (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_estimates${index.count }" type="number" name="budget_estimates"
														class="validate" placeholder="Amount" min="0.01" step="0.01"
														value="${bObj.budget_estimate }">
											</td>
											<td data-head="Revised Estimate (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_estimates${index.count }" name="revised_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.revised_estimate }">
											</td>
											<td data-head="Final Estimate (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_estimates${index.count }" name="final_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.final_estimate }">
											</td>
											<td data-head="Budget Grant (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_grants${index.count }" name="budget_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.budget_grant }">
											</td>
											<td data-head="Revised Grant (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_grants${index.count }" name="revised_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.revised_grant }">
											</td>
											<td data-head="Final Grant (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_grants${index.count }" name="final_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.final_grant }">
											</td>
											<td data-head="Target (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="target${index.count }" name="target" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.target_value}">
											</td>
											<td data-head="Attachment" class="input-field cell-disp-inb">
												<c:set var="existingBudgetFilesLength" value="${fn:length(bObj.budgetFilesList )}"></c:set>
													<c:if test="${fn:length(bObj.budgetFilesList ) gt 0}">
														<c:set var="existingBudgetFilesLength" value="${fn:length(bObj.budgetFilesList )+1}"></c:set>
													</c:if>
													<div id="selectedFilesInput${index.count }">
				                                    	<div class="file-field input-field" id="budgetFilesDiv${index.count }${fn:length(bObj.budgetFilesList) }" >
					                                        <div class="btn bg-m t-c"> <span>Attach Files</span>
					                                        	<c:if test="${ empty bObj.budgetFilesList }">
					                                        			<input type="hidden" id="budgetFileNames${index.count }" name="budgetFileNames" value="">
												           		 </c:if>
					                                           		<input type="hidden" id="len${index.count }"  value="${fn:length(bObj.budgetFilesList) }">
					                                           
					                                            <input type="file" id="budgetFiles${index.count }${fn:length(bObj.budgetFilesList) }" name="budgetFiles"  onchange="selectFileUpdate('${index.count }${fn:length(bObj.budgetFilesList) }','${index.count }')">
					                                        </div>
					                                        
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    
				                                    <div id="selectedFiles${index.count }" class="disp-in">
				                                    	<c:forEach var="obj" items="${bObj.budgetFilesList }" varStatus="indexx">
															 <div id="budgetFilesNames${index.count }${indexx.count }">
																<a href="<%=CommonConstants.BUDGET_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
																<span onclick="removeFileUpdate('${index.count }${indexx.count }','${index.count }')" class="attachment-remove-btn">X</span>
																<input type="hidden" id="budgetFileNames${index.count }${indexx.count }" name="budgetFileNames" value="${obj.attachment }">
														     </div>
														     <div style="clear:both" class="hide" id="hide${index.count }${indexx.count }"><input type="hidden" id="filecounts${index.count }${indexx.count }" name="filecounts" value="${indexx.count }"></div>
														     <script>
														     var count = ('${index.count }${indexx.count }') - 1;
																var lastNo = $('#selectedFiles${index.count }${indexx.count }  input').last().val('${indexx.count }');
																var s = $('#hide'+count+' input').val();
																if(s != null){
																	$('#hide'+count+' input').removeAttr('name');
																	
																}
															</script>
														</c:forEach>
														<div id="hideVal${index.count }">
														<c:if test="${ empty bObj.budgetFilesList }">
														<input type="hidden" id="filecounts${index.count }" name="filecounts" value="0">
												            </c:if></div>
													</div>
													
												<%-- 
												 <div class="">
                                                   	<input type="file" name="budgetFiles" id="budgetFile${index.count }"  onchange="getFileName('${index.count }')"  style="display:none"  />
                                                   	<label for="budgetFile${index.count }" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
													<a id="fileVal${index.count }" class="filevalue" href="<%=CommonConstants.BUDGET_FILES %>${bObj.attachment }" download>${bObj.attachment }</a> 
												 </div>                                              
										         <input type="hidden" id="budgetFilesNames${index.count }" name="budgetFilesNames" value="${bObj.attachment }"> --%>
											</td>
											<td class="mobile_btn_close "><a onclick="removeBudget('${index.count }');"
												class="btn waves-effect waves-light red t-c "> <i
													class="fa fa-close"></i></a></td>
										</tr>
										 </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       	 <tr id="budgetRow0">
											<td data-head="Financial Year" class="input-field">
													<select class="searchable validate-dropdown"
														name="financial_year_fks" id="financial_year_fks0">
														<option value="">Select Financial Year</option>
														<c:forEach var="obj" items="${financialYearList}">
															<option value="${obj.financial_year }">${obj.financial_year }</option>
														</c:forEach>
													</select>
											</td>
											<td data-head="Budget Estimate (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_estimates0" type="number" name="budget_estimates"
														class="validate" placeholder="Amount" min="0.01" step="0.01"> 
											</td>
											<td data-head="Revised Estimate (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_estimates0" name="revised_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
											</td>
											<td data-head="Final Estimate (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_estimates0" name="final_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
											</td>
											<td data-head="Budget Grant (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_grants0" name="budget_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
											</td>
											<td data-head="Revised Grant (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_grants0" name="revised_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
											</td>
											<td data-head="Final Grant (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_grants0" name="final_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
											</td>
											<td data-head="Target (in Cr)" class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="target0" name="target" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
											</td>											
											<td data-head="Attach" class="cell-disp-inb">
												  <div id="selectedFilesInput">
				                                    	<div class="file-field input-field" id="budgetFilesDiv1" >
				                                    		<div class="btn bg-m t-c" >
				                                            <span>Attach Files</span>
					                                            <input type="file" id="budgetFiles1" name="budgetFiles"   onchange="selectFile('1')">
					                                        </div> 
					                                            <!-- <label for="budgetFiles1" class="btn bg-m"><i class="fa fa-paperclip"></i></label> -->
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    <div id="selectedFiles" class="disp-in">
				                                    	<input type="hidden" id="budgetFileNames0" name="budgetFileNames" value="">
				                                    	<div id="hideVal0">
															<input type="hidden" id="filecounts0" name="filecounts" value="0">
												        </div>
													</div>
											</td>
											<td class="mobile_btn_close "><a onclick="removeBudget('0');"
												class="btn waves-effect waves-light red t-c "> <i
													class="fa fa-close"></i></a></td>
										 </tr>
										 <!-- <script>
											 $("#budgetFile0").change(function () {
	                                             filename1 = $('#budgetFile0')[0].files[0].name;
	                                             $('#fileVal0').html(filename1);
	                                             console.log(filename1)
	                                         }); 
										 </script> -->
										</c:otherwise>
                                      </c:choose>
									</tbody>
								</table>
								 <table class="mdl-data-table">
                                       <tbody>                                          
	                                    <tr>
  										   <td colspan="6" class="mobile_btn_add" > <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addBudgetRow()"> <i
	                                                            class="fa fa-plus"></i></a>
	                                    </tr>
                                       </tbody>
                               </table>
							   <c:choose>
                                    <c:when test="${not empty budgetDetails.budget && fn:length(budgetDetails.budget) gt 0 }">
                                		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(budgetDetails.budget) }" />
                                	</c:when>
                                 	<c:otherwise>
                                 		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                 	</c:otherwise>
                                 </c:choose> 
							</div>
						</div>


					<div class="container container-no-margin">                           

                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                   <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateBudget();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addBudget();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/budget" class="btn waves-effect waves-light bg-s">Cancel</a>
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
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
  <script>
	  function selectFile(no){
		    files = $("#budgetFiles"+no)[0].files;
		    var html = "";
			var count = no - 1;
				
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=budgetFilesNames'+no+'>'
				 + '<a href="#" class="filevalue"> '+$(this).get(0).files[i].name+'<span onclick="removeFile('+no+','+count+')" class="attachment-remove-btn">X</span></a>'
				 + '</div>'
				 + '<div style="clear:both;" id="hide'+no+'"><input id="fileCounts'+no+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles").append(html);
		   
		    $('#budgetFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			var lastfieldsid = $('#hide'+no+' input').last().val(no);
			var s = $('#hide'+count+' input').val();
			 $('#hideVal'+count+' input').removeAttr('name');
			if(s != null){
				$('#hide'+count+' input').removeAttr('name');
				
			}
			
			moreFiles(fileIndex);
		}
	  function selectFileUpdate(no,bNo){
		    files = $("#budgetFiles"+no)[0].files;
		    var html = "";
			var count = no - 1;
		    var s = null;
		    var fileIndex = Number(no)+1;
			var id = (fileIndex/10);
		    var str=id.toString();
		    var splt = str.split('.')[1];
		    var spli1 = str.split('.')[0];
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=budgetFilesNames'+fileIndex+'>'
				 + '<a href="#" class="filevalue"> '+$(this).get(0).files[i].name+' <span onclick="removeFileUpdate('+fileIndex+','+bNo+')" class="attachment-remove-btn">X</span></a>'
				 + '<input type="hidden" id="budgetFileNames'+fileIndex+'" name="budgetFileNames" value="'+$(this).get(0).files[i].name+'" >'
				 + '</div>'
				 + '<div style="clear:both;" class="hide" id="hide'+fileIndex+'"><input id="fileCounts'+fileIndex+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles"+bNo).append(html);
		   
		    $('#budgetFilesDiv'+no).hide();
		    var num = (splt-spli1);
		    var posNum = (num < 0) ? num * -1 : num; // if num is negative multiple by negative one ... 
		    var attr = $('#hide'+no+' input').attr('name');
		    
		    if (typeof attr == 'undefined' || attr == false) {
		      	   s = 1;
				    $('#hide'+fileIndex+' input').last().val(s);

		    }else{
		    	  s = $('#hide'+no+' input').val();
				    if(s == null){
				    	s = 0;
				    }
				    var d = $("#hide"+no+" input").attr("id");
				    if(d != null){
				    	 var v = $("#"+d).val();
						 var splt2 = d.split('s')[1];
				    }else{
				    	v = 0;
				    }
				    var lastfieldsid = $('#hide'+fileIndex+' input').last().val(Number(v)+1);

		    }
		    $('#hide'+splt2+' input').removeAttr('name');
		    $('#hideVal'+bNo+' input').removeAttr('name');
			
			//$('#budgetFileNames'+bNo).removeAttr('name');
			if(s != null){
				$('#hide'+count+' input').removeAttr('name');
				
			}
			
			moreFilesUpdate(no,bNo);
		}
		
		function moreFilesUpdate(no,bNo){
			var html = "";
			var count = no;
			 var fileIndex = Number(no)+1;
			/* if(no >1 ){
				var rNo=(no-1)
				$("#fileCounts"+rNo).removeAttr('value');
			} */
			html =  html + '<div class="file-field input-field" id="budgetFilesDiv'+fileIndex+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="budgetFiles'+fileIndex+'" name="budgetFiles"  onchange="selectFileUpdate('+fileIndex+','+bNo+')">'
			

			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput"+bNo).append(html);
		}
		function moreFiles(no){
			var html = "";
			var count = no;
			/* if(no >1 ){
				var rNo=(no-1)
				$("#fileCounts"+rNo).removeAttr('value');
			} */
			html =  html + '<div class="file-field input-field" id="budgetFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="budgetFiles'+no+'" name="budgetFiles"  onchange="selectFile('+no+')">'
			

			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function removeFileUpdate(no,bNo){			
	   	//$('#budgetFilesDiv'+no).remove();
	   	$('#budgetFilesNames'+no).remove();
	   	var id = Number(no)-1;
	   	$('#hide'+id+' input').attr('name', 'filecounts');
	   	var bId = $('#hide'+id+' input').val();
	   	if(bId == null){
			var html = '<input id="fileCounts'+bNo+'"  name="filecounts" value="0" type="hidden">'
	   		$('#hideVal'+bNo).append(html);
	   	}
	   	$('#hide'+no+' input').removeAttr('name');
	   	
	  } 
		function removeFile(no,bNo){			
		   	$('#budgetFilesDiv'+no).remove();
		   	$('#budgetFilesNames'+no).remove();
		   	var id = Number(no)-1;
		   	$('#hide'+id+' input').attr('name', 'filecounts');
		   	var bId = $('#hide'+id+' input').val();
		   	if(bId == null){
		   		$('#hideVal'+id+' input').attr('name', 'filecounts');
		   	}
		   	$('#hide'+no+' input').removeAttr('name');
		   	
		  } 
		
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           // $('#remarks').characterCounter();
            
            /* var projectId = "${budgetDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            } */
        });
     
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForBudgetForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${budgetDetails.work_id_fk}";
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
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       		}
       		
        }

        function addBudgetRow(){
    	    var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var value = rNo+1;
        	var html ='<tr id="budgetRow'+rNo+'"><td data-head="Financial Year" class="input-field"> <input type="hidden" name="budget_ids" id="budget_ids'+rNo+'" />'
		        		+'<select  name="financial_year_fks" id="financial_year_fks'+rNo+'" class="validate-dropdown searchable" >'
							+'<option value="">Select Financial Year</option>' 
							<c:forEach var="obj" items="${financialYearList}">
								+'<option value="${obj.financial_year }">${obj.financial_year }</option>'
							</c:forEach>
						+'</select></td>'
			            +'<td data-head="Budget Estimate (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_estimates'+rNo+'" type="number" name="budget_estimates"'
						    +'class="validate" placeholder="Amount" min="0.01" step="0.01"></td>'
			            +'<td data-head="Revised Estimate (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_estimates'+rNo+'" name="revised_estimates" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount" ></td>'
			            +'<td data-head="Final Estimate (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_estimates'+rNo+'" name="final_estimates" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Budget Grant (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_grants'+rNo+'" name="budget_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Revised Grant (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_grants'+rNo+'" name="revised_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
			            +'<td data-head="Final Grant (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_grants'+rNo+'" name="final_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'
				            +'<td data-head="Target (in Cr)" class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="target'+rNo+'" name="target" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></td>'							
			            +'<td data-head="Attachment" class="cell-disp-inb">  <div id="selectedFilesInput'+rNo+'"><div class="file-field input-field" id="budgetFilesDivs'+rNo+1+'" >' 
			            + '<div class="btn bg-m t-c"> <span>Attach Files</span>'
                            +'<input type="hidden"  name="budgetFileNames" value=""><input type="file" id="budgetFiles'+rNo+1+'" name="budgetFiles"   onchange="selectFiles('+rNo+1+','+rNo+')"></div>'
                            +' <div class="file-path-wrapper">'
                        +' <input class="file-path validate" type="text">'
                        +' </div></div></div><div id="selectedFiles'+rNo+'" class="disp-in"><div class="hide" id="hideVal'+rNo+'"> <input id="fileCounts'+rNo+'"  name="filecounts"  type="hidden" value="0"></div></div></td>'
			            +'<td class="mobile_btn_close "><a onclick="removeBudget('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
            $('#budgetTableBody').append(html);
			$("#rowNo").val(rNo);
            $('.searchable').select2();
        }
        function selectFiles(no,rNo){
		    files = $("#budgetFiles"+no)[0].files;
		    var id = (no/10);
		    var str=id.toString();
		    var splt = str.split('.')[1];
		    var count = splt;
			var fNo = no - 1
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=budgetFilesNames'+no+'>'
				 + '<a href="#" class="filevalue" >'+$(this).get(0).files[i].name+'<span onclick="removeFiles('+no+','+rNo+')" class="attachment-remove-btn">X</span></a>'
				 + '<input type="hidden" id="budgetFileNames'+no+'" name="budgetFileNames" value="'+$(this).get(0).files[i].name+'" >'
				 + '</div>'
				 + '<div style="clear:both;" class="hide" id="hide'+no+'"><input id="fileCounts'+no+'"  name="filecounts"  type="hidden"></div>';
		    }
		    $("#selectedFiles"+rNo).append(html);
		    
		    $('#budgetFilesDivs'+no).hide();
		    
			var fileIndex = Number(no)+1;
			var lastfieldsid = $('#hide'+no+' input').last().val(count);
			$('#hideVal'+rNo+' input').removeAttr('name');
			//$('#budgetFiles'+fNo).removeAttr('name');
			var s = $('#hide'+fNo+' input').val();
			if(s != null){
				$('#hide'+fNo+' input').removeAttr('name');
				
			}
			moreFiles1(fileIndex,rNo);
		}
		
		function moreFiles1(no,rNo){
			var html = "";
			html =  html + '<div class="file-field input-field" id="budgetFilesDivs'+no+'" >'
			+ '<div class="btn bg-m t-c"> <span>Attach Files</span>'
			+ '<input type="file" id="budgetFiles'+no+'" name="budgetFiles"  onchange="selectFiles('+no+','+rNo+')"></div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput"+rNo).append(html);
		}
		
		function removeFiles(no,rNo){			
	   	$('#budgetFilesDiv'+no).remove();
	   	$('#budgetFilesNames'+no).remove();
		var id = Number(no)-1;
	    $('#hide'+id+' input').attr('name', 'filecounts');
		var bId = $('#hide'+id+' input').val();
	   	if(bId == null){
	   		var html = '<input id="fileCounts'+rNo+'"  name="filecounts" value="0" type="hidden">'
	   		$('#hideVal'+rNo).append(html);
	   	}
		
	   	$('#hide'+no+' input').removeAttr('name');
	   	
	   } 
		
		function removeBudget(rowNo){
			$("#budgetRow"+rowNo).remove();
		}
		
		
        function addBudget(){
        	 if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	        	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=target]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	   			document.getElementById("budgetForm").submit();			
   	 	 }
        }
        function updateBudget(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	        	$('form input[name=financial_year_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_estimates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=budget_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=revised_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=final_grants]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=target]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	   			document.getElementById("budgetForm").submit();	
        	}
        }
        
      
        var validator =	$('#budgetForm').validate({
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
		   		},submitHandler:function(form){
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
	       
    </script>


</body>

</html>