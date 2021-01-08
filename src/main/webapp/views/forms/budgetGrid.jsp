<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Budget </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
     
     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <style>
        p a {
            color: blue
        }
        .input-field .searchable_label{
      		font-size:0.85rem;
        } 
        td,th{
        	box-sizing:content-box !important;
        }
 		
        .fw-300{
        	width:300px !important;
        	max-width:300px;
        }
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Budget</h6>
                        </div>
                    </span>
                    <div class="">
                    <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
                        <div class="row plr-1 center-align">
                            <div class="col s12 m4"> </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-budget-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Budget</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportBudget();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m3 hide-on-small-only"></div>

                            <div class="col m6 s12">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label">Project</p>
                                        <select class="searchable" name="project_id_fk" id="project_id_fk" onchange="getBudgetList();">
                                            <option value="" selected>Select</option>
                                            	
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select id="work_id_fk" name="work_id_fk" onchange="getBudgetList();" class="searchable">
                                            <option value="" >Select</option>
	                                           
                                        </select>
                                    </div>
                                    <!-- <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Financial Year</p>
                                        <select class="searchable" name="financial_year_fk" id="financial_year_fk" onchange="getBudgetList();">
                                            <option value="" >Select</option>
                                            	 
                                        </select>
                                    </div> -->
                                    <div class="col s12 m4">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;" onclick="clearFilter();">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m3 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                         <table id="datatable-budget" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="fw-300">Work </th>
                                            <th>Latest <br>Financial Year</th>
                                            <th>Budget Estimate</th>
                                            <th>Budget Grant </th>
                                            <th>Reivised Estimate</th>
                                            <th>Reivised Grant </th>
                                            <th>Final Estimate</th>
                                            <th>Final Grant </th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <!--  <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="budget.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>
                                        </tr> -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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
 <div class="page-loader-2" style="display: none;">
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
    
    
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="budget_id" id="budget_id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-budget" name="exportBudgetForm" id="exportBudgetForm" target="_blank" method="post">	
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="financial_year_fk" id="exportFinancial_year_fk" />
	</form>
    <script>
    $(document).ready(function () {
 	   $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
    	var table = $('#datatable-budget').DataTable({
 		"bStateSave": true,
 		fixedHeader: true,
         "fnStateSave": function (oSettings, oData) {
             localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
         },
         "fnStateLoad": function (oSettings) {
             return JSON.parse(localStorage.getItem('MRVCDataTables'));
         },
         columnDefs: [
             {
                 targets: [0, 1, 2],
                 className: 'mdl-data-table__cell--non-numeric'
             },
             { orderable: false, 'aTargets': ['nosort'] }
         ],
         // "ScrollX": true,
         "scrollCollapse": true,
         //"sScrollY": 400,
         "sScrollX": "100%",
             "sScrollXInner": "100%",
             "bScrollCollapse": true,
         initComplete: function () {
             $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
         }
     });
 	table.state.clear(); 
		
 	
 	$('.close-message').delay(3000).fadeOut('slow');
 	
 	getBudgetList();
  });
 
    function clearFilter(){
    	$("#project_id_fk").val("");
    	$("#work_id_fk").val("");
    	$("#financial_year_fk").val("");
    	$('.searchable').select2();
    	getBudgetList();
    }
    
    function getBudgetList(){
    	$(".page-loader-2").show();
    	var project_id_fk = $("#project_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var financial_year_fk = $("#financial_year_fk").val();
    	getWorksFilterList();
     	getProjectsFilterList();
     	getFinancialYearsFilterList();
    	table = $('#datatable-budget').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-budget').DataTable({
    		"bStateSave": true,
    		fixedHeader: true,
            "fnStateSave": function (oSettings, oData) {
                localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
            },
            "fnStateLoad": function (oSettings) {
                return JSON.parse(localStorage.getItem('MRVCDataTables'));
            },
            columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric'
                },
                { orderable: false, 'aTargets': ['nosort'] }
            ],
            // "ScrollX": true,
            "sScrollX": "100%",
             "sScrollXInner": "100%",
             "bScrollCollapse": true,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        }).rows().remove().draw();
		
		table.state.clear();		
	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, financial_year_fk : financial_year_fk};
	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-budget",type:"POST",data:myParams,success : function(data){    				
			if(data != null && data != '' && data.length > 0){    					
         		$.each(data,function(key,val){
         			var budget_id = "'"+val.budget_id+"'";
                    var actions = '<a href="javascript:void(0);"  onclick="getBudget('+budget_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
/*                     			  +'<a onclick="deleteBudget('+budget_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
 */                   	var rowArray = [];    	                 
                   	
                	var workName = '';
                    if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                    
                   	rowArray.push($.trim(val.work_id_fk) + workName);
                   	rowArray.push($.trim(val.financial_year_fk));
                   	rowArray.push($.trim(val.budget_estimate));
                   	rowArray.push($.trim(val.budget_grant));
                   	rowArray.push($.trim(val.revised_estimate));
                   	rowArray.push($.trim(val.revised_grant));
                   	rowArray.push($.trim(val.final_estimate));
                   	rowArray.push($.trim(val.final_grant));
                   	rowArray.push($.trim(actions));   	                   	
                   	
                    table.row.add(rowArray).draw( true );
                    		                       
				});
         		
         		$(".page-loader-2").hide();
			}else{
				$(".page-loader-2").hide();
			}
			
		},error: function (jqXHR, exception) {
			$(".page-loader-2").hide();
         	getErrorMessage(jqXHR, exception);
     }});
   }
    
    function getWorksFilterList() {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var financial_year_fk = $("#financial_year_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        if ($.trim(work_id_fk) == "") {
        	$("#work_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,financial_year_fk : financial_year_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInBudget",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
	                           $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
    
    function getProjectsFilterList() {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        var financial_year_fk = $("#financial_year_fk").val();
		if ($.trim(project_id_fk) == "") {
        	$("#project_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,financial_year_fk : financial_year_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInBudget",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var projectName = '';
                            if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
	                           $("#project_id_fk").append('<option value="' + val.project_id_fk + '">' + $.trim(val.project_id_fk)   + projectName +'</option>');
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
    
    function getFinancialYearsFilterList() {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        var financial_year_fk = $("#financial_year_fk").val();
        if ($.trim(financial_year_fk) == "") {
        	$("#financial_year_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,financial_year_fk : financial_year_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getFinancialYearsFilterListInBudget",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
	                           $("#financial_year_fk").append('<option value="' + val.financial_year_fk + '">' + $.trim(val.financial_year_fk) +'</option>');
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
    
    function getBudget(budget_id){
    	$("#budget_id").val(budget_id);
    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-budget');
    	$('#getForm').submit();
    }
    function deleteBudget(budget_id){
    	$("#budget_id").val(budget_id);
    	showCancelMessage();
    }
    	
    
  	//This function is used to get error message for all ajax calls
    function getErrorMessage(jqXHR, exception) {
    	    var msg = '';
    	    if (jqXHR.status === 0) {
    	        msg = 'Not connect.\n Verify Network.';
    	    } else if (jqXHR.status == 404) {
    	        msg = 'Requested page not found. [404]';
    	    } else if (jqXHR.status == 500) {
    	        msg = 'Internal Server Error [500].';
    	    } else if (exception === 'parsererror') {
    	        msg = 'Requested JSON parse failed.';
    	    } else if (exception === 'timeout') {
    	        msg = 'Time out error.';
    	    } else if (exception === 'abort') {
    	        msg = 'Ajax request aborted.';
    	    } else {
    	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
    	    }
    	    console.log(msg);
     }
    
    
  	
    function showCancelMessage() {
    	swal({
            title: "Are you sure?",
            text: "You will be able to change the status of record!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel it!",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function (isConfirm) {
            if (isConfirm) {
               // swal("Deleted!", "Record has been deleted", "success");
            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-budget');
    	    	$('#getForm').submit();
           }else {
                swal("Cancelled", "Record is safe :)", "error");
            }
        });
    }
    
    function exportBudget(){
   	 var project_id_fk = $("#project_id_fk").val();
     var work_id_fk = $("#work_id_fk").val();
   	 var financial_year_fk = $("#financial_year_fk").val();
   	 $("#exportProject_id_fk").val(project_id_fk);
   	 $("#exportWork_id_fk").val(work_id_fk);
   	 $("#exportFinancial_year_fk").val(financial_year_fk);
   	 $("#exportBudgetForm").submit();
	}
    
    
    </script>

</body>

</html>