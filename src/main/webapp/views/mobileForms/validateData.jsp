 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@page import="com.synergizglobal.pmis.constants.CommonConstants2" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Approve Activity Progress - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"	rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
<link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style>
    	 .fixed-width {
            width: 100%;
            margin-left :auto !important;
            margin-right :auto !important;
        }
        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }
		.dataTables_filter label {
		    color: transparent;
		}
        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }

        .btn-holder .btn+.btn {
            margin-left: 20px;
        }

        .error-msg label,
        .errMsg label,
        .errMsgCheck label {
            color: red !important;
        }

        .tabs {
            border-bottom: 1px solid #ddd;
            margin-bottom: 2.7rem;
        }

        .tabs .tab a {
            text-transform: capitalize;
            color: #007a7a;
        }

        .tabs .tab a:hover,
        .tabs .tab a.active,
        .tabs .tab a:focus,
        .tabs .tab a:focus.active {
            background-color: #f2fefe;
            color: #007a7a;
        }

        .tabs .indicator {
            background-color: #007a7a;
        }

        a.bg-s.disabled>.fa {
            color: #9F9F9F !important;
        }

        .l-r-brdr {
            border-left: 1px solid #ddd;
            border-right: 1px solid #ddd;
        }

        .last-column .btn {
            padding: 0 5px;
            line-height: 1.5;
            height: auto;
        }

        .last-column .btn+.btn {
            margin-left: 10px;
        }
         .mdl-data-table.dataTable.no-footer {
            width: 100% !important;
        }
        .table-inside .mdl-data-table td {
		    text-align: left !important;
		}
		.fw-120{
        	width:120px !important;
        	max-width:120px;
        }
        .fw-150{
        	width:150px !important;
        	min-width:150px;
        }
        .fw-170{
        	width:170px !important;
        	min-width:170px;
        }
		.fw-200{
        	width:200px;
        	min-width:200px;
        }
        .fw-250{
        	width:250px;
        	min-width:250px;
        } 
        .fw-300{
        	width:300px;
        	min-width:300px;
        }
        .fw-110{
        	width:110px;
        	min-width:110px;
        }
        td:last-of-type .btn+.btn{
        	margin-left:10px;
        }
         .no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
    </style>

</head>

<body>
    <!-- header included -->
    <%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>


    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Approve Activity Progress - Validate data</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <!-- <form action="<%=request.getContextPath() %>/update-pmis-progrss-form" id="progressForm"  name="progressForm" method="post"> -->

                    <div class="row">
                        <div class="col s12">
                            <ul class="tabs">
                                <li class="tab col s4"><a class="active" href="javascript:void(0);" onclick="setActivityProgressStatus('Pending')">Pending</a></li>
                                <li class="tab col s4"><a class="l-r-brdr" href="javascript:void(0);" onclick="setActivityProgressStatus('Approved')">Approved</a></li>
                                <li class="tab col s4"><a href="javascript:void(0);" onclick="setActivityProgressStatus('Rejected')">Rejected</a></li>
                            </ul>
                            <input type="hidden" id="approval_status_fk" name="approval_status_fk" value="Pending"/>
                        </div>
                        <div id="pending_div" class="col s12">
                            <div class="row no-mar">
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col s6 m4 l2 input-field offset-l1">
                                            <p class="searchable_label">Work</p>
                                            <select id="work_id_fk" name="work_id_fk" onchange="getActivities();" class="searchable">
			                                   <option value="">Select</option>                                      
			                                </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Contract</p>
                                            <select id="contract_id_fk" name="contract_id_fk" onchange="getActivities();" class="searchable">
                                     			<option value="" >Select</option>
                                 			</select>     
                                        </div>                                        
                                        <div class="col s6 m4 l2 input-field ">
                                            <p class="searchable_label">Structure</p>
                                            <select id="structure" name="structure" onchange="getActivities();" class="searchable">
                                     			<option value="" >Select</option>
                                 			</select>
                                        </div>
                                        <!-- <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Department</p>
                                            <select name="department_fk" id="department_fk" onchange="getActivities();" class="searchable">
                                                <option value="">Select</option>
                                            </select>
                                        </div> -->
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Updated By</p>
                                            <select name="updated_by_user_id_fk" id="updated_by_user_id_fk" onchange="getActivities();" class="searchable">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s12 m4 l2 input-field center-align">
                                            <button class="btn bg-m waves-effect waves-light t-c clear-filters "
                                                onclick="clearFilter();">Clear Filters</button>
                                        </div>
                                    </div>
                                    <span id="pending_checkBoxError" class="error-msg" style="text-align:center"></span>

                                    <span class="errMsg" id="pending_checkErrMsg">select at least one check box
                                    </span>
                                </div>
                                <span id="actualScopesError" class="error-msg" style="color:red"></span>
                            </div>

                            <div class="row no-mar" id="button_div" style="margin-bottom: 0;display: none;">
                                <div class="col m8 s12 center-align offset-m2 btn-holder">
                                    <a class="btn waves-effect t-c disabled" id="approve-btn" onclick="approveMultipleActivityProgress();"><i class="fa fa-check"></i> Approve
                                    </a>
                                    <a class="btn waves-effect bg-s t-c disabled" id="reject-btn" onclick="rejectMultipleActivityProgress();"> <i class="fa fa-close"></i> Reject
                                    </a>
                                </div>
                            </div>

                            <div class="row fixed-width" style="margin-bottom: 30px;">
                                <div class="table-inside">
                                    <table class="mdl-data-table" id="datatable-table-pending">
                                        <thead>
                                            <tr>
                                                <th class="no-sort" style=" text-align: left; vertical-align: bottom;">
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="pending_select-all"
                                                                id="pending_select-all" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </th>
                                                <!-- <th>Work</th> -->
                                                <th>Contract</th>
                                                <th>Structure</th>
                                                <th>Component</th>
                                                <th>Component Id</th>
                                                <th>Activity</th>
                                                <th>Cumulative <br>Completed</th>
                                                <th>Actual for<br> the day</th>
                                                <th>Updated by</th>
                                                <th>Updated on</th>
                                                <th>Approved on</th>
                                                <th>Rejected on</th>
                                                <th class="nosort">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- <tr>
                                                <td>
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="pending_activity_check"
                                                                class="check" id="pending_check_1" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </td>
                                                <td>work</td>
                                                <td>work</td>
                                                <td>structure</td>
                                                <td>department</td>
                                                <td>activity</td>
                                                <td>reporting date</td>
                                                <td>completed</td>
                                                <td>remaining</td>
                                                <td>actual</td>
                                                <td>updated by</td>
                                                <td>pending on</td>
                                                <td class="last-column">
                                                    <a href="" class="btn"><i class="fa fa-check"></i> </a>
                                                    <a href="" class="btn bg-s" id="pending_reject_1"><i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr> -->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        

                    <!-- </form> -->
                </div>
            </div>
            <!-- form ends  -->
        </div>

        <!-- Page Loader starts-->
        <div class="page-loader" style="display: none;">
            <div class="preloader-wrapper big active">
                <div class="spinner-layer spinner-#007a7a-only">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="gap-patch">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
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
                    </div>
                    <div class="gap-patch">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Page Loader ends-->

        <%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

        <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
        <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
        <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
        <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
        <script src="/pmis/resources/js/dataTables.material.min.js"></script>
        <script src="/pmis/resources/js/select2.min.js"></script>
        <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
        <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
        <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

        <script type="text/javascript">
        	var filtersMap = new Object();
            $(document).ready(function () {
                $(".errMsg").hide();
                $(".errMsgCheck").hide();
                $('.searchable').select2();
                $('.tabs').tabs();
                var filters = window.localStorage.getItem("activitiesApprovalFilters");
  	          
                if($.trim(filters) != '' && $.trim(filters) != null){
            	  var temp = filters.split('^'); 
            	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'structure' ){
    		        		  getStructuresListFilter(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
    		        		  getWorksListFilter(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
    		        		  getContractsListFilter(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'department_fk'){
    		        		  getDepartmentsListFilter(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'updated_by_user_id_fk'){
    		        		  getUpdatedByListFilter(temp2[1]);
    		        	  }
    	        	  }
    	          }
                }
             
            	getActivities();
               
            });
            
            function setActivityProgressStatus(approval_status_fk){
            	$("#approval_status_fk").val(approval_status_fk);
            	$("#work_id_fk").val("");
            	$("#contract_id_fk").val("");
            	//$("#department_fk").val("");
            	$("#structure").val("");
            	$("#updated_by_user_id_fk").val("");  
            	$(".searchable").select2();
            	getActivities();
            }

            // clear filter functionality for all divs
            function clearFilter() {
            	$("#work_id_fk").val("");
            	$("#contract_id_fk").val("");
            	//$("#department_fk").val("");
            	$("#structure").val("");
            	$("#updated_by_user_id_fk").val("");  
            	$(".searchable").select2();
            	
            	//window.localStorage.clear();
            	//window.localStorage.setItem("activitiesApprovalFilters",'');            	
            	getActivities();
            }
            
            function addInQueStructure(structure){
            	Object.keys(filtersMap).forEach(function (key) {
    	   			if(key.match('structure')) delete filtersMap[key];
    	   		});
            	if($.trim(structure) != ''){
           	    	filtersMap["structure"] = structure;
            	}
            }
            
            function addInQueWork(work_id_fk){
    	      	Object.keys(filtersMap).forEach(function (key) {
    		   		if(key.match('work_id_fk')) delete filtersMap[key];
    	   	   	});
    	      	if($.trim(work_id_fk) != ''){
                	filtersMap["work_id_fk"] = work_id_fk;
    	      	}
            }
            
            function addInQueContract(contract_id_fk){
            	Object.keys(filtersMap).forEach(function (key) {
    	   			if(key.match('contract_id_fk')) delete filtersMap[key];
    	   		});
            	if($.trim(contract_id_fk) != ''){
           	    	filtersMap["contract_id_fk"] = contract_id_fk;
            	}
            }
            
            function addInQueDepartment(department_fk){
    	      	Object.keys(filtersMap).forEach(function (key) {
    		   		if(key.match('department_fk')) delete filtersMap[key];
    	   	   	});
    	      	if($.trim(department_fk) != ''){
                	filtersMap["department_fk"] = department_fk;
    	      	}
            }
            
            function addInQueUpdatedBy(updated_by_user_id_fk){
            	Object.keys(filtersMap).forEach(function (key) {
    	   			if(key.match('updated_by_user_id_fk')) delete filtersMap[key];
    	   		});
            	if($.trim(updated_by_user_id_fk) != ''){
           	    	filtersMap["updated_by_user_id_fk"] = updated_by_user_id_fk;
            	}
            }
                
            function getActivities(){
            	$(".page-loader-2").show();

            	getStructuresListFilter('');
            	getWorksListFilter('');
            	getContractsListFilter('');
            	//getDepartmentsListFilter('');
            	getUpdatedByListFilter('');
            	
            	var approval_status_fk = $("#approval_status_fk").val();
            	var work_id_fk = $("#work_id_fk").val();
            	var contract_id_fk = $("#contract_id_fk").val();
            	var structure = $("#structure").val();
            	var department_fk = $("#department_fk").val();
            	var updated_by_user_id_fk = $("#updated_by_user_id_fk").val();
            	
            	var filters = '';
            	Object.keys(filtersMap).forEach(function (key) {
    	    		//alert(filtersMap[key]);
            		filters = filters + key +"="+filtersMap[key] + "^";
            		window.localStorage.setItem("activitiesApprovalFilters", filters);
       			});
            	table = $('#datatable-table-pending').DataTable();

        		table.destroy();
        		$.fn.dataTable.moment('DD-MMM-YYYY');
        		table = $('#datatable-table-pending').DataTable({
        			"sort" : [],
                    columnDefs: [
                        { targets: [10], className: 'btn-holder' },
                        { targets: 'no-sort', orderable: false, },
                        { targets: [1], className: 'fw-150'  },
                        { targets: [2], className: 'fw-150'  },
                        { targets: [5], className: 'fw-200'  },
                    ],
                    //'order': [1, 'asc'],
                    "ScrollX": true,
                    "ordering":false,
                    "scrollCollapse": true,
                    //"sScrollY": 400,
                    //paging: true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                        var input = $('.dataTables_filter input')
    					.unbind(), self = this.api(), $searchButton = $(
    					'<i class="fa fa-search" title="Go">')
    					.click(function() {
    						self.search(input.val()).draw();
    					}), $clearButton = $(
    							'<i class="fa fa-close" title="Reset">')
    					.click(function() {
    						input.val('');
    						$searchButton.click();
    					})
    					$('.dataTables_filter').append( '<div class="right-btns"></div>');
    					$('.dataTables_filter div').append( $searchButton, $clearButton);
                    }
                }).rows().remove().draw();
        		
        		
        		table.state.clear();	
        		
        		 if(approval_status_fk == 'Pending'){
        			$("#button_div").show();
        			table.column( 0 ).visible(true);
        			table.column( 9 ).visible(true);
        			table.column( 10 ).visible(false);
        			table.column( 11 ).visible(false);
        			table.column( 12 ).visible(true);
        		}else if(approval_status_fk == 'Approved'){
        			$("#button_div").hide();
        			table.column( 0 ).visible(false);
        			table.column( 9 ).visible(false);
        			table.column( 10 ).visible(true);
        			table.column( 11 ).visible(false);
        			table.column( 12 ).visible(false);
        		}else if(approval_status_fk == 'Rejected'){
        			$("#button_div").hide();
        			table.column( 0 ).visible(false);
        			table.column( 9 ).visible(false);
        			table.column( 10 ).visible(false);
        			table.column( 11 ).visible(true);
        			table.column( 12 ).visible(false);
        		}
        	 
        	 	var myParams = {work_id_fk :work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk,
        	 			structure : structure, updated_by_user_id_fk : updated_by_user_id_fk,approval_status_fk : approval_status_fk };
        		$.ajax({url : "<%=request.getContextPath()%>/mobileappwebview/ajax/getApprovableActivities",
        				type:"POST",
        				data:myParams, cache: false,async:false,
        				success : function(data){    				
        				if(data != null && data != '' && data.length > 0){    					
        	         		$.each(data,function(key,val){
        	         			var progress_id = "'"+val.progress_id+"'";
        	                    var rowArray = [];   
        	                    var checkbox = '-';
        	                    var actions = '-';
        	                    if(approval_status_fk == 'Pending'){
	        	                   	checkbox = '<p><label><input type="checkbox" name="pending_activity_check" class="check" id="pending_activity_check_'+key+'" value="'+progress_id+'" /><span></span></label></p>';
	        	                   	
	        	                   	actions = '<a href="javascript:void(0);"  onclick="approveActivityProgress('+progress_id+');" class="btn"><i class="fa fa-check"></i> </a>'
        	                   				+'<a href="javascript:void(0);"  onclick="rejectActivityProgress('+progress_id+');" class="btn bg-s" id="pending_reject_1"><i class="fa fa-close"></i></a>';
        	                   	}
        	                    
	        	         		rowArray.push(checkbox);
        	                   	//rowArray.push(val.work_short_name);
        	                   	rowArray.push($.trim(val.contract_short_name));
        	                   	rowArray.push($.trim(val.structure));
        	                   	rowArray.push($.trim(val.component));
        	                   	rowArray.push($.trim(val.component_id));
        	                   	rowArray.push($.trim(val.activity_name));        	                   	
        	                   	rowArray.push($.trim(val.cumulative_completed));
        	                   	rowArray.push($.trim(val.actual_for_the_day));
        	                   	rowArray.push($.trim(val.updated_by));
        	                   	rowArray.push($.trim(val.updated_on));
        	                   	rowArray.push($.trim(val.approved_on));
        	                   	rowArray.push($.trim(val.rejected_on));
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
            
			function approveActivityProgress(progress_id){
				$(".page-loader").show();
                if ($.trim(progress_id) != "") {
                    var myParams = {progress_id : progress_id };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/approveActivityProgress",
                        data: myParams, cache: false,async: false,
                        success: function (data) {
                        	if (data != null) {
	                            if (data.message_flag == true) {
	                            	//swal("Success!",data.message);
	                            	swal({
		                                    title: "Success",
		                                    text: data.message, 
		                                    type: "success",
		                                    icon: "success",
		                                    showCancelButton: false,
		                                    confirmButtonColor: "#26a69a",
		                                    confirmButtonText: "Okay",
		                                    closeOnConfirm: true,
		                    	            closeOnCancel: false
		                                },function (isConfirm) {
		                                    if (isConfirm) {
		                                    	getActivities();
		                    	            }
		                                }
	                                );
	                            }else{
	                            	swal("Failed",data.message, "error");
	                            }
                        	}
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
			
			function rejectActivityProgress(progress_id){
				swal({
                    title: "Are you sure You want to Reject progress of activity?",
                    text: "", 
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, reject it!",
                    closeOnConfirm: true,
    	            closeOnCancel: true
                },function (isConfirm) {   
                    if (isConfirm) {
                    	confirmRejectActivityProgress(progress_id);
    	            }
                }
              );
			}
			
			function confirmRejectActivityProgress(progress_id){
				$(".page-loader").show();
                if ($.trim(progress_id) != "") {
                    var myParams = {progress_id : progress_id };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/rejectActivityProgress",
                        data: myParams, cache: false,async: false,
                        success: function (data) {
                        	
                        	if (data != null) {
	                            if (data.message_flag == true) {
	                            	//getActivities();
	                            	//swal("Success!",data.message);
	                            	setTimeout(function(){
	                            		$(".page-loader").hide();
		                            	swal({
			                                    title: "Success",
			                                    text: data.message, 
			                                    type: "success",
			                                    icon: "success",
			                                    showCancelButton: false,
			                                    confirmButtonColor: "#26a69a",
			                                    confirmButtonText: "Okay",
			                                    closeOnConfirm: true,
			                    	            closeOnCancel: false
			                                },function (isConfirm) {
			                                    if (isConfirm) {
			                                    	getActivities();
			                    	            }
			                                }
		                                );
	                            	},500);
	                            }else{
	                            	swal("Failed",data.message, "error");
	                            	$(".page-loader").hide();
	                            }
                        	}
                            
                        },error: function (jqXHR, exception) {
         	   			  $(".page-loader").hide();
       	   	          	  getErrorMessage(jqXHR, exception);
      	   	     	  }
                    });
                }else{
                	  $(".page-loader").hide();
                }
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
            	    swal("Failed",msg, "error");
             }
          	
            function getWorksListFilter(work) {
            	$(".page-loader").show();
            	
            	var approval_status_fk = $("#approval_status_fk").val();
            	var work_id_fk = $("#work_id_fk").val();
            	var contract_id_fk = $("#contract_id_fk").val();
            	var structure = $("#structure").val();
            	var department_fk = $("#department_fk").val();
            	var updated_by_user_id_fk = $("#updated_by_user_id_fk").val();
             	
                if ($.trim(work_id_fk) == "") {
                    $("#work_id_fk option:not(:first)").remove();
                    var myParams = {work_id_fk :work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk,
            	 			structure : structure, updated_by_user_id_fk : updated_by_user_id_fk, approval_status_fk : approval_status_fk };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getWorksInApprovableActivities",
                        data: myParams, cache: false,async: false,
                        success: function (data) {
                            if (data.length > 0) {
                                $.each(data, function (i, val) {
                                	var selectedFlag = (work == val.work_id_fk)?'selected':'';
     	                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '" '+selectedFlag+'>' + val.work_short_name +'</option>');
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
                    
            function getStructuresListFilter(structure_name) {
            	$(".page-loader").show();
            	var approval_status_fk = $("#approval_status_fk").val();
            	var work_id_fk = $("#work_id_fk").val();
            	var contract_id_fk = $("#contract_id_fk").val();
            	var structure = $("#structure").val();
            	var department_fk = $("#department_fk").val();
            	var updated_by_user_id_fk = $("#updated_by_user_id_fk").val();

                if ($.trim(structure) == "") {
                     $("#structure option:not(:first)").remove();
                     var myParams = {work_id_fk :work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk,
             	 			structure : structure, updated_by_user_id_fk : updated_by_user_id_fk, approval_status_fk : approval_status_fk };
                     $.ajax({
                         url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getStructuresInApprovableActivities",
                         data: myParams, cache: false,async: false,
                         success: function (data) {
                             if (data.length > 0) {
                                 $.each(data, function (i, val) {                             	
                                 	var selectedFlag = (structure_name == val.structure)?'selected':'';
                                	$("#structure").append('<option value="' + val.structure + '" '+selectedFlag+'>' + $.trim(val.structure) +'</option>');
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
            
            function getContractsListFilter(contract) {
            	$(".page-loader").show();
            	var approval_status_fk = $("#approval_status_fk").val();
            	var work_id_fk = $("#work_id_fk").val();
            	var contract_id_fk = $("#contract_id_fk").val();
            	var structure = $("#structure").val();
            	var department_fk = $("#department_fk").val();
            	var updated_by_user_id_fk = $("#updated_by_user_id_fk").val();

                if ($.trim(contract_id_fk) == "") {
                    $("#contract_id_fk option:not(:first)").remove();
                    var myParams = {work_id_fk :work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk,
            	 			structure : structure, updated_by_user_id_fk : updated_by_user_id_fk, approval_status_fk : approval_status_fk };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getContractsInApprovableActivities",
                        data: myParams, cache: false,async: false,
                        success: function (data) {
                            if (data.length > 0) {
                                $.each(data, function (i, val) {
                                	var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
     	                            $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '" '+selectedFlag+'>' + val.contract_short_name +'</option>');
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
          	
            function getDepartmentsListFilter(department) {
             	$(".page-loader").show();
             	var approval_status_fk = $("#approval_status_fk").val();
             	var work_id_fk = $("#work_id_fk").val();
            	var contract_id_fk = $("#contract_id_fk").val();
            	var structure = $("#structure").val();
            	var department_fk = $("#department_fk").val();
            	var updated_by_user_id_fk = $("#updated_by_user_id_fk").val();

                if ($.trim(department_fk) == "") {
                    $("#department_fk option:not(:first)").remove();
                    var myParams = {work_id_fk :work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk,
            	 			structure : structure, updated_by_user_id_fk : updated_by_user_id_fk, approval_status_fk : approval_status_fk };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getDepartmentsInApprovableActivities",
                        data: myParams, cache: false,async: false,
                        success: function (data) {
                            if (data.length > 0) {
                                $.each(data, function (i, val) {
                                	var selectedFlag = (department == val.department_fk)?'selected':'';
                                	$("#department_fk").append('<option value="' + val.department_fk + '" '+selectedFlag+'>' + $.trim(val.department_name) +'</option>');
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
            
            function getUpdatedByListFilter(user_id) {
             	$(".page-loader").show();
             	var approval_status_fk = $("#approval_status_fk").val();
             	var work_id_fk = $("#work_id_fk").val();
            	var contract_id_fk = $("#contract_id_fk").val();
            	var structure = $("#structure").val();
            	var department_fk = $("#department_fk").val();
            	var updated_by_user_id_fk = $("#updated_by_user_id_fk").val();
            	
                if ($.trim(updated_by_user_id_fk) == "") {
                     $("#updated_by_user_id_fk option:not(:first)").remove();
                     var myParams = {work_id_fk :work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk,
             	 			structure : structure, updated_by_user_id_fk : updated_by_user_id_fk, approval_status_fk : approval_status_fk };
                     $.ajax({
                         url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getUpdatedByListInApprovableActivities",
                         data: myParams, cache: false,async: false,
                         success: function (data) {
                             if (data.length > 0) {
                                 $.each(data, function (i, val) {
                                	 var selectedFlag = (user_id == val.user_id)?'selected':'';
                               	 	 $("#updated_by_user_id_fk").append('<option value="' + val.user_id + '" '+selectedFlag+'>' + $.trim(val.user_name) +'</option>');
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

            // select or deselect all checkboxes
            $('#pending_select-all').change(function () {
                var _this = this;
                $('input[name="pending_activity_check"]').each(function () {
                    if ($(_this).is(':checked')) {
                        $(this).prop('checked', true);
                        $('#approve-btn').removeClass('disabled');
                        $('#reject-btn').removeClass('disabled');
                    } else {
                        $(this).prop('checked', false);
                        $('#approve-btn').addClass('disabled');
                        $('#reject-btn').addClass('disabled');
                    }
                });
            });


            /* $("#reject-btn").on("click", function (event) {
                // event.preventDefault();
                swal({
                    title: "Are you sure You want to Reject 'number' of Activities?",
                    text: "", type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false,
    	            closeOnCancel: false
                },function (isConfirm) {
                    if (isConfirm) {
                    	$(".deleteedition").submit();
    	            }else {
    	                swal("You are cancelled the rejection", "", "error");
    	            }
                }
              );
            }); */
            
            function approveMultipleActivityProgress(){
				$(".page-loader").show();				
				var progress_id = $('input[name="pending_activity_check"]:checked').map(function() {
		            return $(this).val();
		        }).get().join(",");
				//alert(progress_id);
                if ($.trim(progress_id) != "") {
                    var myParams = {progress_id : progress_id };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/approveMultipleActivityProgress",
                        data: myParams, cache: false,async: false,
                        success: function (data) {
                        	if (data != null) {
	                            if (data.message_flag == true) {
	                            	//swal("Success!",data.message);
	                            	swal({
		                                    title: "Success",
		                                    text: data.message, 
		                                    type: "success",
		                                    icon: "success",
		                                    showCancelButton: false,
		                                    confirmButtonColor: "#26a69a",
		                                    confirmButtonText: "Okay",
		                                    closeOnConfirm: true,
		                    	            closeOnCancel: false
		                                },function (isConfirm) {
		                                    if (isConfirm) {
		                                    	getActivities();
		                    	            }
		                                }
	                                );
	                            }else{
	                            	swal("Failed",data.message, "error");
	                            }
                        	}
                            $(".page-loader").hide();
                        },error: function (jqXHR, exception) {
         	   			  $(".page-loader").hide();
       	   	          	  getErrorMessage(jqXHR, exception);
      	   	     	  }
                    });
                }else{
                	  $(".page-loader").hide();
                	  swal("Please select atleast one checkbox","", "error");
                }
            }
            
            
            function rejectMultipleActivityProgress(){
				swal({
                    title: "Are you sure You want to Reject selected progress of activities?",
                    text: "", 
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, reject it!",
                    closeOnConfirm: true,
    	            closeOnCancel: true
                },function (isConfirm) {
                    if (isConfirm) {
                    	confirmRejectMultipleActivityProgress();
    	            }
                }
              );
			}
			
			function confirmRejectMultipleActivityProgress(){
				$(".page-loader").show();
				var progress_id = $('input[name="pending_activity_check"]:checked').map(function() {
		            return $(this).val();
		        }).get().join(",");
				
                if ($.trim(progress_id) != "") {
                    var myParams = {progress_id : progress_id };
                    $.ajax({
                        url: "<%=request.getContextPath()%>/mobileappwebview/ajax/rejectMultipleActivityProgress",
                        data: myParams, cache: false,async: false,
                        success: function (data) {                        	
                        	if (data != null) {
	                            if (data.message_flag == true) {
	                            	//getActivities();
	                            	//swal("Success!",data.message);
	                            	setTimeout(function(){
	                            		$(".page-loader").hide();
		                            	swal({
			                                    title: "Success",
			                                    text: data.message, 
			                                    type: "success",
			                                    icon: "success",
			                                    showCancelButton: false,
			                                    confirmButtonColor: "#26a69a",
			                                    confirmButtonText: "Okay",
			                                    closeOnConfirm: true,
			                    	            closeOnCancel: false
			                                },function (isConfirm) {
			                                    if (isConfirm) {
			                                    	getActivities();
			                    	            }
			                                }
		                                );
	                            	},500);
	                            }else{
	                            	swal("Failed",data.message, "error");
	                            	$(".page-loader").hide();
	                            }
                        	}
                            
                        },error: function (jqXHR, exception) {
         	   			  $(".page-loader").hide();
       	   	          	  getErrorMessage(jqXHR, exception);
      	   	     	  }
                    });
                }else{                	
                	 $(".page-loader").hide();
                	 swal("Please select atleast one checkbox","", "error");
                }
            }
            
            
        </script>

</body>

</html>