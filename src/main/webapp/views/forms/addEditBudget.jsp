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
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
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
        
        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        @media only screen and (max-width: 600px) {
            .input-field .prefix~input {
                min-width: 80px;
            }
        }

        .table-inside td span {
            text-align: left;
            display: block;
        }
           .primary-text {
            color: #118AB2;
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
                    <div class="container container-no-margin">
                         <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-budget" id="budgetForm" name="budgetForm" method="post"   enctype="multipart/form-data">
                         </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-budget" id="budgetForm" name="budgetForm" method="post"  enctype="multipart/form-data">
						  </c:if>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Project ID </label></p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                                 	   onchange="getWorksList(this.value);">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}" <c:if test="${budgetDetails.project_id_fk eq obj.project_id}">selected</c:if>>${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Work ID </label></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" >
                                        <option value="" selected>Select</option>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                           
                            <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Financial Year</label></p>
                                    <select class="searchable" name="financial_year_fk" id="financial_year_fk">
                                            <option value="" >Select Financial Year </option>
                                            	 <c:forEach var="obj" items="${financialYearList}">
	                       						  <option value="${obj.financial_year }" <c:if test="${budgetDetails.financial_year_fk eq obj.financial_year }">selected</c:if>>${obj.financial_year }</option>
	                                             </c:forEach>
                                        </select>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div>
							<div>
							<input type="hidden" name="budget_id" value="${budgetDetails.budget_id }" />
							</div>
                            <div class="container" style="margin-bottom:20px;">
                                <div class="row">
                                    <div class="col m6 s12">
                                        <div class="row fixed-width">
                                            <h5 class="center-align">Budget</h5>
                                            <div class="table-inside">
                                                <table id="budgetTable1" class="mdl-data-table">
                                                    <thead>
                                                        <tr>
                                                            <th>Budget Type </th>
                                                            <th>Amount </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">Budget Estimate</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="budget_estimate" type="text" name="budget_estimate"
                                                                        class="validate"  placeholder="Amount" value="${budgetDetails.budget_estimate }">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">August Review Estimate</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="august_review_estimate" name="august_review_estimate" type="text" 
                                                                        class="validate" placeholder="Amount" value="${budgetDetails.august_review_estimate }">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">Revised Estimate</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="revised_estimate" name="revised_estimate" type="text"
                                                                        class="validate" placeholder="Amount" value="${budgetDetails.revised_estimate }">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">Final Estimate</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="final_estimate" name="final_estimate" type="text"
                                                                        class="validate" placeholder="Amount" value="${budgetDetails.final_estimate }">
                                                                </div>
                                                            </td>
                                                        </tr>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col m6 s12">
                                        <div class="row fixed-width">
                                            <h5 class="center-align">Grants</h5>
                                            <div class="table-inside">
                                                <table id="budgetTable2" class="mdl-data-table">
                                                    <thead>
                                                        <tr>
                                                            <th>Grant Type </th>
                                                            <th>Amount </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">Budget Grant</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="budget_grant" name="budget_grant" type="text"
                                                                        class="validate" placeholder="Amount" value="${budgetDetails.budget_grant }">
                                                                </div>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">Revised Grant</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="revised_grant" name="revised_grant" type="text"
                                                                        class="validate" placeholder="Amount" value="${budgetDetails.revised_grant }">
                                                                </div>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text">Final Grant</span>
                                                            </td>
                                                            <td>
                                                                <div class="input-field">
                                                                    <i class="material-icons prefix center-align">₹</i>
                                                                    <input id="final_grant" name="final_grant" type="text"
                                                                        class="validate" placeholder="Amount" value="${budgetDetails.final_grant }">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" accept="image/x-png,image/jpeg" name=budgetFile id="budgetFile" >
                                              <input name="attachment" id="attachment" type="hidden"  />
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text" name="attachment"  value="${budgetDetails.attachment }">
                                          
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${budgetDetails.remarks}</textarea>
                                    <label for="remarks">Remarks</label>
                                </div>
                            </div>

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
            $('#budgetFile').change(function() {
                $('#attachment').val($(this).val());
          });
        });
        
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
        	$(".page-loader").show();	    		
   			document.getElementById("budgetForm").submit();			
   	 	 }
        function updateBudget(){
        	$(".page-loader").show();	    		
   			document.getElementById("budgetForm").submit();	
        }
        
    </script>


</body>

</html>