<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<!DOCTYPE html>
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
     
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <style>
        p a {
            color: blue
        }
    
        td:last-child,
        td:last-of-type {
            white-space: inherit;
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
         .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		.error-msg label{color:red!important;}
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
                    <div class="container container-no-margin">
                         <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-budget" id="budgetForm" name="budgetForm" method="post"   enctype="multipart/form-data">
                         </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-budget" id="budgetForm" name="budgetForm" method="post"  enctype="multipart/form-data">
						  </c:if>
						   <c:if test="${action eq 'add'}">	
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                 	   onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${budgetDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Work</p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" >
                                        <option value="">Select</option>
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
	                                         	 	<input type="text" name="project_id_fk" id="project_id_fk" value="${budgetDetails.project_id_fk}- ${budgetDetails.project_name}" readonly />
								  </div> 
								  <div class="col s12 m4 input-field"> 
									    <p class="searchable_label"> Work</p>
	                                         	 	<input type="text" name="work_id_fk" id="work_id_fk" value="${budgetDetails.work_id_fk}- ${budgetDetails.work_name}" readonly />
	                              </div>
                              </div> 
                             </c:if>
                             </div>
                            <!-- <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Financial Year</label></p>
                                    <select class="searchable validate-dropdown" name="financial_year_fk" id="financial_year_fk">
                                            <option value="" >Select Financial Year </option>
                                            	 <c:forEach var="obj" items="${financialYearList}">
	                       						  <option value="${obj.financial_year }" <c:if test="${budgetDetails.financial_year_fk eq obj.financial_year }">selected</c:if>>${obj.financial_year }</option>
	                                             </c:forEach>
                                        </select>
                                         <span id="financial_year_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div> -->
							<div>
							<input type="hidden" name="budget_id" value="${budgetDetails.budget_id }" />
							</div>

						<div class="row fixed-width">
							<h5 class="center-align">Budget Details</h5>
							<div class="table-inside">
								<table id="budgetTable" class="mdl-data-table">
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
									<tbody>
										<tr>
											<td>
												<div class="input-field">
													<select class="searchable validate-dropdown"
														name="financial_year_fk" id="financial_year0_fk">
														<option value="">Select Financial Year</option>
														<c:forEach var="obj" items="${financialYearList}">
															<option value="${obj.financial_year }"
																<c:if test="${budgetDetails.financial_year_fk eq obj.financial_year }">selected</c:if>>${obj.financial_year }</option>
														</c:forEach>
													</select> <span id="financial_year0_fkError" class="error-msg"></span>
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_estimate0" type="number" name="budget_estimate"
														class="validate" placeholder="Amount" min="0.01" step="0.01"
														value="${budgetDetails.budget_estimate }"> <span
														id="budget_estimate0Error" class="error-msg"></span>
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_estimate" name="revised_estimate0" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${budgetDetails.revised_estimate }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_estimate0" name="final_estimate" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${budgetDetails.final_estimate }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="budget_grant0" name="budget_grant" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${budgetDetails.budget_grant }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="revised_grant0" name="revised_grant" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${budgetDetails.revised_grant }">
												</div>
											</td>
											<td>
												<div class="input-field">
													<i class="material-icons prefix center-align">₹</i> <input
														id="final_grant0" name="final_grant" type="number" min="0.01" step="0.01"
														class="validate" placeholder="Amount"
														value="${budgetDetails.final_grant }">
												</div>
											</td>
											<td>
												<div class="">
													<input type="file" name="myfile" id="myFile0"
														style="display: none" /> <label for="myFile0"
														class="btn bg-m"><i class="fa fa-paperclip"></i></label> <span
														id="fileVal" class="filevalue">fileName</span>
												</div>
											</td>
											<td><a href="#"
												class="btn waves-effect waves-light red t-c "> <i
													class="fa fa-close"></i></a></td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td><a href="#" onclick="addRow()"
												class="btn waves-effect waves-light bg-m t-c "> <i
													class="fa fa-plus"></i></a></td>
										</tr>
	
									</tbody>
								</table>
	
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
                        </form>
                    </div>
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
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
  <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks').characterCounter();
            
            var projectId = "${budgetDetails.project_id_fk}";
            if($.trim(projectId) != ''){
            	getWorksList(projectId);
            }
        });
        
        var budget = 1;
        function addRow(){
        	var text='<tr><td><div class="input-field"><select class="searchable validate-dropdown" name="financial_year_fk" id="financial_year'+budget+'_fk">'+
            '<option value="">Select Financial Year</option></select> <span id="financial_year'+budget+'_fkError" class="error-msg"></span></div></td>'+
            '<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_estimate'+budget+'" type="number" name="budget_estimate"'+
            'class="validate" placeholder="Amount" min="0.01" step="0.01"> <span id="budget_estimate'+budget+'Error" class="error-msg"></span></div></td>'+
            '<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="revised_estimate'+budget+'" name="revised_estimate" type="number" min="0.01" step="0.01"'+
            'class="validate" placeholder="Amount"></div></td><td><div class="input-field"><i class="material-icons prefix center-align">₹</i> '+
            '<input id="final_estimate'+budget+'" name="final_estimate" type="number" min="0.01" step="0.01" class="validate" placeholder="Amount"></div></td>'+
            '<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="budget_grant'+budget+'" name="budget_grant" type="number"'+
            ' min="0.01" step="0.01"class="validate" placeholder="Amount"></div></td><td><div class="input-field"><i class="material-icons prefix center-align">₹</i>'+
            '<input id="revised_grant'+budget+'" name="revised_grant" type="number" min="0.01" step="0.01" class="validate" placeholder="Amount" ></div></td>'+
            '<td><div class="input-field"><i class="material-icons prefix center-align">₹</i> <input id="final_grant'+budget+'" name="final_grant" type="number"'+
            'min="0.01" step="0.01" class="validate" placeholder="Amount"></div></td><td><div class=""><input type="file" name="myfile" id="myFile'+budget+'" '+
            'style="display: none" /> <label for="myFile'+budget+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label> <span id="fileVal'+budget+'" '+
            'class="filevalue">fileName</span></div></td><td><a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';
        	
            $('#budgetTable tbody').find('tr:last').prev().after(text);
            $('.searchable').select2();
            budget++;
        }
        
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
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
        
        function addBudget(){
        	 if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
	   			document.getElementById("budgetForm").submit();			
   	 	 }
        }
        function updateBudget(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	    		
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
	  			 	  },"financial_year_fk": {
	  			 		  required: true
	  			 	  },"budget_estimate": {
	  			 		  required: false
	  			 	  }	
	  		 	},
	  		    messages: {
	  		 		   "project_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"work_id_fk": {
	  			 		  required: 'Required'
	  			 	  },"financial_year_fk": {
	  			 		  required: 'Required'
	  			 	  },"budget_estimate": {
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
					 }else if(element.attr("id") == "financial_year_fk" ){
					     document.getElementById("financial_year_fkError").innerHTML="";
				 	     error.appendTo('#financial_year_fkError');
					 }else if(element.attr("id") == "budget_estimate" ){
					     document.getElementById("budget_estimateError").innerHTML="";
				 	     error.appendTo('#budget_estimateError');
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