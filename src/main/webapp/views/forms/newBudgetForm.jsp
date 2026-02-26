<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update New Budget</c:if>
		 <c:if test="${action eq 'add'}"> Add New Budget</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/budget.css"> -->
	<link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-responsive-table.css" > 
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
		    color: red !important;
		}
		
		.my-error-class
		{
		color: red !important;
		}

		
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

         <div class="container-padding">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                 <h6>
	                             <c:if test="${action eq 'edit'}">Update New Budget</c:if>
								 <c:if test="${action eq 'add'}"> Add New Budget</c:if>
							  </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">				                
		                	<form action="<%=request.getContextPath() %>/update-new-budget" id="budgetForm" name="budgetForm" method="post"   enctype="multipart/form-data">
                      </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-new-budget" id="budgetForm" name="budgetForm" method="post"   enctype="multipart/form-data">
					  </c:if>
                        
                    <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Project </p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" >
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${budgetDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Contract <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="contract_id" name="contract_id" 
                                    		onchange="resetProjectsDropdowns(this.value);">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${contractsList }">
	                                      	   <option value= "${obj.contract_id}" <c:if test="${budgetDetails.contract_id eq obj.contract_id}">selected</c:if>>${obj.contract_id}<c:if test="${not empty obj.contract_name}"> - </c:if> ${obj.contract_name }</option>
	                                         </c:forEach>
                                    </select>
                                      <span id="contract_idError" class="error-msg" ></span>
                                </div>
                            </div>

                             </div>
                    
						<div class="row fixed-width">
							<h5 class="center-align">Budget Details</h5>
							<div class="table-inside">
							 <c:if test="${action eq 'add'}">
							 <a type="button" class="btn waves-effect waves-light bg-m t-c right" onclick="addBudget();">Add</a>
							 </c:if>
                               <c:if test="${action eq 'edit'}">
                                  <a type="button" class="btn waves-effect waves-light bg-m t-c right" onclick="updateBudget();">Update</a>
                               </c:if>							 
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
											<!-- <th>Attachment</th> -->
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="budgetTableBody">
									 <c:choose>
                                      	 <c:when test="${not empty budgetDetails.budget && fn:length(budgetDetails.budget) gt 0 }">
                                       	  <c:forEach var="bObj" items="${budgetDetails.budget }" varStatus="index"> 
										   <tr id="budgetRow${index.count }">
											<td data-head="Financial Year" class="input-field">
											    <input type="month" class="validate" onChange="checkFY(this.value);" name="financial_year_fks" id="financial_year_fks${index.count }" value="${bObj.financial_year_fk}" />
												
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
											<td class="mobile_btn_close ">
												<c:if test="${index.count gt 1}">
												<a onclick="removeBudget('${index.count }');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
												</c:if>
											</td>
										</tr>
										 </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       	 <tr id="budgetRow0">
											<td data-head="Financial Year" class="input-field">
													<input type="month" name="financial_year_fks" onChange="checkFY(this.value);" class="validate" id="financial_year_fks0" value="${bObj.financial_year_fk}" />
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
											<td class="mobile_btn_close "><!-- <a onclick="removeBudget('0');"
												class="btn waves-effect waves-light red t-c "> <i
													class="fa fa-close"></i></a> --></td>
										 </tr>
										</c:otherwise>
                                      </c:choose>
									</tbody>
								</table>
								<p id="fyError" style="color:red;"></p>
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
                                        <a href="<%=request.getContextPath()%>/new-budget" class="btn waves-effect waves-light bg-s">Cancel</a>
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

 
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
  <script>
	
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           // $('#remarks').characterCounter();
            
            /* var projectId = "${budgetDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            } */
            
                if("${budgetDetails.contract_id}"!="")
            	{
                	$("#project_id_fk").attr("disabled",true);
                	$("#work_id_fk").attr("disabled",true);
                	$("#contract_id").attr("disabled",true);
            	}
        });
     

        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	var work_Id = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
            	work_Id=workId.substring(0, 6);
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
  
       			$("#work_id_fk").val(work_Id);
       			$("#work_id_fk").select2();      			
       			
       		}
       		
        }
        
        function resetProjectsContractsDropdowns(workId){
        	var projectId = '';
        	var work_Id = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
            	work_Id=workId.substring(0, 6);
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       			getContractsFilterList(projectId,work_Id);
       		}
       		
        }    
        
        
        function getContractsFilterList(projectId,work_Id) {
        
            if ($.trim(work_Id) != "") {
            	$("#contract_id option:not(:first)").remove();
            	var myParams = { project_id_fk: projectId,work_id_fk: work_Id };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsListForNewBudgetForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        
                    	if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#contract_id").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) +' - ' + $.trim(val.contract_name) +'</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			      $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);
    	   	     	  }
                });
            }else{
            	  $(".page-loader").hide();
            }
        }         
        
        function addBudgetRowUp(){
    	    var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var value = rNo+1;
        	var html ='<tr id="budgetRow'+rNo+'"><td data-head="Financial Year" class="input-field"> <input type="month" name="financial_year_fks" onChange="checkFY(this.value);" class="validate" id="financial_year_fks'+rNo+'" />'
		        		+'</td>'
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
			            +'<td class="mobile_btn_close "><a onclick="removeBudget('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
            $('#budgetTableBody').prepend(html);
			$("#rowNo").val(rNo);
            $('.searchable').select2();
        }
        function addBudgetRow(){
    	    var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var value = rNo+1;
        	var html ='<tr id="budgetRow'+rNo+'"><td data-head="Financial Year" class="input-field"> <input type="hidden" name="budget_ids" id="budget_ids'+rNo+'" />'+
        				'<input type="month" name="financial_year_fks" onChange="checkFY(this.value);" class="validate" id="financial_year_fks'+rNo+'" /></td>'
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
			            +'<td class="mobile_btn_close "><a onclick="removeBudget('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
            $('#budgetTableBody').append(html);
			$("#rowNo").val(rNo);
            $('.searchable').select2();
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

	  				var rowCount = $('#budgetFormTable tbody tr').length;
	  					if(rowCount>0)
	  					{
	  					
	                		for(var m=0;m<rowCount;m++)
	          				{
	                			if($('#financial_year_fks'+m).val()=="")
             		    	   		 {
	                					$(".page-loader").hide();   	 
	                					$('#fyError').html("Financial year required");
             				    	   	 return false;            		       		  		
             		    	   		 }
             			       	   else
             			    	   {
             				    	   	 $('#fyError').html("");

             			    	   }	               		    	 
	                		       
	          				}
	  					
	  					}
	  					
	  				document.getElementById("budgetForm").submit();	

   	 	 }
        }
        var checkFYArray=new Array();
        function checkFY(t)
        {
        	checkFYArray=[];
        	var rowCount = $('#budgetFormTable tbody tr').length;
    		$('#fyError').html("");
      	
    		if("${budgetDetails.contract_id}"=="")
    			{

	        		for(var m=0;m<rowCount;m++)
	  				{
	       		       	  if(checkFYArray.indexOf($('#financial_year_fks'+m).val())==-1)
	       		    	   {
	       		    	   	 	if($('#financial_year_fks'+m).val()!="")
	       		    	   		 {
	       		       		  		checkFYArray.push($('#financial_year_fks'+m).val());
	       		       		 		$('#fyError').html("");
	       		    	   		 }
	       		    	   }
	       		       	  else
       		       		  {
	     		    	   	 $('#fyError').html("Financial year alredy selected");
	    		    	   	 return false;
       		       		  }
	
	        		       
	  				}
    			
    			}
    		else
    		{
	        		for(var m=1;m<=rowCount;m++)
	  				{
	       		       	  if(checkFYArray.indexOf($('#financial_year_fks'+m).val())==-1)
	       		    	   {
	       		    	   	 	if($('#financial_year_fks'+m).val()!="")
	       		    	   		 {
	       		       		  		checkFYArray.push($('#financial_year_fks'+m).val());
	       		       		 		$('#fyError').html("");
	       		    	   		 }
	       		    	   }
	       		       	  else
	       		       		  {
		     		    	   	 $('#fyError').html("Financial year alredy selected");
		    		    	   	 return false;
	       		       		  }
	
	        		       
	  				}
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

                	
	  				var rowCount = $('#budgetFormTable tbody tr').length;
  					if(rowCount>0)
  					{
  					
                		for(var m=0;m<rowCount;m++)
          				{
                			if($('#financial_year_fks'+m).val()=="")
         		    	   		 {
                					$(".page-loader").hide();   	 
                					$('#fyError').html("Financial year required");
         				    	   	 return false;            		       		  		
         		    	   		 }
         			       	   else
         			    	   {
         				    	   	 $('#fyError').html("");

         			    	   }	               		    	 
                		       
          				}
  					
  					}	  				
	  				
	  				$("#project_id_fk").attr("disabled",false);
                	$("#work_id_fk").attr("disabled",false);
                	$("#contract_id").attr("disabled",false);
	  				document.getElementById("budgetForm").submit();	
	  			
        	}
        }
        
      
        var validator =	$('#budgetForm').validate({
			 errorClass: "error-msg",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "contract_id": {
	  			 		  required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		   "contract_id": {
	  			 		  required: 'Required'
	  			 	  }		
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "contract_id" ){
					     document.getElementById("contract_idError").innerHTML="";
				 	     error.appendTo('#contract_idError');
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