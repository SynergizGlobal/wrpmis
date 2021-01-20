<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
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
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
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

        @media only screen and (max-width: 600px) {
            .input-field .prefix~input {
                min-width: 80px;
            }
        }

        .table-inside td span {
            text-align: center;
            display: block;
        }
           .primary-text {
            color: #118AB2;
        }
        #budgetTableBody tr td .select2-container{
        	width:140px !important;
        	max-width:140px;
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
                            <div class="center-align p-2 bg-m">
                                 <h6>
	                             <c:if test="${action eq 'edit'}">Update Budget</c:if>
								 <c:if test="${action eq 'add'}"> Add Budget</c:if>
							  </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">				                
		                	<form action="<%=request.getContextPath() %>/update-budget" id="budgetForm" name="budgetForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                      </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-budget" id="budgetForm" name="budgetForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
					  </c:if>
                        
                    <div class="container container-no-margin">
						   <c:if test="${action eq 'add'}">	
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                 	   onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" 
                                    		onchange="resetProjectsDropdowns(this.value);">
	                                        <option value="">Select</option>
	                                        <c:forEach var="obj" items="${worksList }">
	                                      	   <option value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                         </c:forEach>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                             </c:if>
                             <c:if test="${action eq 'edit'}">	
                              <div class="row" id="center" style="text-align:center;">
	                              <div class="col m2 hide-on-small-only">
	                              </div>
	                       		  <div class="col s12 m4 input-field">
										<p class="searchable_label"> Project</p>
	                                         	 	<input type="text" value="${budgetDetails.project_id_fk} - ${budgetDetails.project_name}" readonly />
								  </div> 
								  <div class="col s12 m4 input-field"> 
									    <p class="searchable_label"> Work</p>
	                                         	 	<input type="text"  value="${budgetDetails.work_id_fk} - ${budgetDetails.work_short_name}" readonly />
	                                         	 	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${budgetDetails.work_id_fk}" readonly />
	                              </div>
                              </div> 
                             </c:if>
                             </div>
                    
						<div class="row fixed-width">
							<h5 class="center-align">Budget Details</h5>
							<div class="table-inside">
								<table id="budgetFormTable" class="mdl-data-table">
									<thead>
										<tr>
											<th>Financial Year</th>
											<th>Budget Estimate <br>(in Cr)	</th>
											<th>Revised Estimate <br>(in Cr)</th>
											<th>Final Estimate <br>(in Cr)	</th>
											<th>Budget Grant <br>(in Cr)</th>
											<th>Revised Grant <br>(in Cr)</th>
											<th>Final Grant <br>(in Cr)	</th>
											<th>Attachment</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="budgetTableBody">
									 <c:choose>
                                      	 <c:when test="${not empty budgetDetails.budget && fn:length(budgetDetails.budget) gt 0 }">
                                       	  <c:forEach var="bObj" items="${budgetDetails.budget }" varStatus="index"> 
										   <tr id="budgetRow${index.count }">
											<td>
												<div class="input-field">
												    <input type="hidden" name="budget_ids" id="budget_ids${index.count }" value="${bObj.budget_id}" />
													<select class="searchable validate-dropdown"
														name="financial_year_fks" id="financial_year_fks${index.count }">
														<option value="">Select Financial Year</option>
														<c:forEach var="obj" items="${financialYearList}">
															<option value="${obj.financial_year }"
																<c:if test="${bObj.financial_year_fk eq obj.financial_year }">selected</c:if>>${obj.financial_year }</option>
														</c:forEach>
													</select>
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_estimates${index.count }" type="number" name="budget_estimates"
														class="validate" placeholder="Amount" min="0.01" step="0.01"
														value="${bObj.budget_estimate }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_estimates${index.count }" name="revised_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.revised_estimate }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_estimates${index.count }" name="final_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.final_estimate }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_grants${index.count }" name="budget_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.budget_grant }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_grants${index.count }" name="revised_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.revised_grant }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_grants${index.count }" name="final_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${bObj.final_grant }">
												</div>
											</td>
											<td>
												
												 <div class="">
                                                   	<input type="file" name="budgetFile" id="budgetFile${index.count }"  onchange="getFileName('${index.count }')"  style="display:none"  />
                                                   	<label for="budgetFile${index.count }" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
													<a id="fileVal${index.count }" class="filevalue" href="<%=CommonConstants.BUDGET_FILES %>${bObj.attachment }" download>${bObj.attachment }</a> 
												 </div>                                              
										         <input type="hidden" id="budgetFileNames${index.count }" name="budgetFileNames" value="${bObj.attachment }">
											</td>
											<td><a onclick="removeBudget('${index.count }');"
												class="btn waves-effect waves-light red t-c "> <i
													class="fa fa-close"></i></a></td>
										</tr>
										 </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       	 <tr id="budgetRow0">
											<td>
												<div class="input-field">
													<select class="searchable validate-dropdown"
														name="financial_year_fks" id="financial_year_fks0">
														<option value="">Select Financial Year</option>
														<c:forEach var="obj" items="${financialYearList}">
															<option value="${obj.financial_year }">${obj.financial_year }</option>
														</c:forEach>
													</select>
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_estimates0" type="number" name="budget_estimates"
														class="validate" placeholder="Amount" min="0.01" step="0.01"> 
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_estimates0" name="revised_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_estimates0" name="final_estimates" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_grants0" name="budget_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_grants0" name="revised_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_grants0" name="final_grants" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount">
												</div>
											</td>
											<td>
											  <div class="">
                                                   <input type="file" name="budgetFile" id="budgetFile0"  onchange="getFileName('0')" 
                                                            style="display:none" />
                                                   <label for="budgetFile0" class="btn bg-m"><i class="fa fa-paperclip"></i></label>
                                                   <span id="fileVal0" class="filevalue" ></span>
                                              </div>
											</td>
											<td><a onclick="removeBudget('0');"
												class="btn waves-effect waves-light red t-c "> <i
													class="fa fa-close"></i></a></td>
										 </tr>
										 <script>
											 $("#budgetFile0").change(function () {
	                                             filename1 = $('#budgetFile0')[0].files[0].name;
	                                             $('#fileVal0').html(filename1);
	                                             console.log(filename1)
	                                         }); 
										 </script>
										</c:otherwise>
                                      </c:choose>
									</tbody>
								</table>
								 <table class="mdl-data-table">
                                        <tbody>                                          
			                                    <tr>
			  										 <td colspan="6" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-m t-c " onclick="addBudgetRow()"> <i
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
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                   <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateBudget();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addBudget();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/budget" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
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
             
        	var html ='<tr id="budgetRow'+rNo+'"><td> <input type="hidden" name="budget_ids" id="budget_ids'+rNo+'" />'
		        		+'<select  name="financial_year_fks" id="financial_year_fks'+rNo+'" class="validate-dropdown searchable" >'
							+'<option value="">Select Financial Year</option>' 
							<c:forEach var="obj" items="${financialYearList}">
								+'<option value="${obj.financial_year }">${obj.financial_year }</option>'
							</c:forEach>
						+'</select></td>'
			            +'<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_estimates'+rNo+'" type="number" name="budget_estimates"'
						    +'class="validate" placeholder="Amount" min="0.01" step="0.01"</div></td>'
			            +'<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_estimates'+rNo+'" name="revised_estimates" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount" ></div></td>'
			            +'<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_estimates'+rNo+'" name="final_estimates" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></div></td>'
			            +'<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_grants'+rNo+'" name="budget_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></div></td>'
			            +'<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_grants'+rNo+'" name="revised_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></div></td>'
			            +'<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_grants'+rNo+'" name="final_grants" type="number" min="0.01" step="0.01"'
							+'class="validate" placeholder="Amount"></div></td>'
			            +'<td> <div class=""><input type="file" name="budgetFile" id="budgetFile'+rNo+'"  onchange="getFileName('+rNo+')" style="display:none" /> <label for="budgetFile'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
		          		+'<span id="fileVal'+rNo+'" class="filevalue" ></span></div></td>'
			            +'<td><a onclick="removeBudget('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
            $('#budgetTableBody').append(html);
			$("#rowNo").val(rNo);
            $('.searchable').select2();
        }
        
        function getFileName(rowNo){
			var filename = $('#budgetFile'+rowNo)[0].files[0].name;
		    $('#fileVal'+rowNo).html(filename);
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