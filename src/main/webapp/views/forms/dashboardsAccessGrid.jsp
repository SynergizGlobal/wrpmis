<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboards - Admin - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        th:last-child,
        td:last-child {
            text-align: center !important;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
        @media only screen and (max-width:769px) {    		
	        .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	         .last-column .btn+.btn {
	            margin-left: 10px;
	        }
       }
    </style>
</head>
<body>

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>
  <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Dashboards</h6>
                        </div>
                    </span>
                    <div class="">
                    	<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
                        <div class="row plr-1">
                            <div class="col s12 m4 offset-m4 center-align" style="margin-bottom:20px">
                                    <a href="<%=request.getContextPath()%>/add-dashboard-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Dashboard</strong></a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col s6 m3 l2 input-field offset-l2 ">
                                <p class="searchable_label">Select Module</p>
                               <select id="module_name_fk" class="searchable" name="module_name_fk" onchange="getDashboardsList();">
                                    <option value="" >Select Module</option>
                                </select>
                            </div>
                            <div class="col s6 m3 l2 input-field">
                                <p class="searchable_label">Select Dashboard Type</p>
                                 <select id="dashboard_type_fk" class="searchable" name="dashboard_type_fk" onchange="getDashboardsList();">
                                    <option value="" >Select Dashboard Type</option>
                                   
                                </select>
                            </div>
                            <div class="col s6 m3 l2 input-field">
                                <p class="searchable_label">Select Status</p>
                                <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk" onchange="getDashboardsList();">
                                    <option value="" >Select Status</option>
                                </select>
                            </div>
                            <div class="col s12 m3 l2 center-align">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 10px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                               <div style= "display:none;" id="webView">
                                <table id="data-table-dashboard" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Dashboard Name</th>
                                            <th>Work</th>
                                            <th>Contract</th>
                                            <th>Folder</th>
                                            <th>Mobile View</th>
                                            <th>Dashboard Type</th>
                                            <th>Priority</th>
                                            <th>Status</th>
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>

                                </table>
                                </div>
                                
                                <div style= "display:none;" id="mobView">
                                <table id="data-table-dashboard_mob" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Dashboard<br/>Name</th>
                                            <th>&nbsp; Work &nbsp; </th>                                           
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>

                                </table>
                                </div>
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
     <jsp:include page="../layout/footer.jsp"></jsp:include>
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="dashboard_id" id="dashboard_id" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();         	
         	$('.close-message').delay(3000).fadeOut('slow');
         	getDashboardsList();
        });

        function clearFilters() {
            $('#module_name_fk').val("");
            $('#dashboard_type_fk').val("");
            $('#soft_delete_status_fk').val("");
            $('.searchable').select2();
            getDashboardsList();
        }
        
        function getDashboardsList(){
        	$(".page-loader-2").show();
        	var module_name_fk = $("#module_name_fk").val();
        	var dashboard_type_fk = $("#dashboard_type_fk").val();
        	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
        	getModulesFilterList();
         	getDashboardTypesFilterList();
         	getStatusFilterList();
         	
         	if(window.matchMedia("(max-width: 769px)").matches){
        		$('tbody.web').removeAttr('id');
                $('#mobView').css({'display':'block'});
                table = $('#data-table-dashboard_mob').DataTable();
       		 
        		table.destroy();
        		
        		$.fn.dataTable.moment('DD-MMM-YYYY');
        		table = $('#data-table-dashboard_mob').DataTable({
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
                            targets: [2],
                            className: 'last-column'
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
        	 	var myParams = {module_name_fk : module_name_fk, dashboard_type_fk : dashboard_type_fk, soft_delete_status_fk : soft_delete_status_fk};
        	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-dashboards-list",type:"POST",data:myParams,success : function(data){    				
        			if(data != null && data != '' && data.length > 0){    					
                 		$.each(data,function(key,val){
                 			var dashboard_id = "'"+val.dashboard_id+"'";
                 			var dashboardVals = "'" + val.dashboard_name + "','" +val.parent_dashboard_id_sr_fk+ "','" +val.dashboard_id+ "'";
                            var actions = '<a href="javascript:void(0);"  onclick="getDashboard('+dashboard_id+');" class="btn mobile-btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
                            			  +'<a href="javascript:void(0);" onclick="goToDashboardLinks('+dashboardVals+');" class="btn mobile-btn waves-effect waves-light bg-m t-c"><i class="fa fa-share"></i></a>'
                          	var rowArray = [];    	                 
                           	
                        	var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                            
                            var contractName = '';
                            if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
                            
                            var folder_name = '';
                            if($.trim(val.dashboard_id) != $.trim(val.parent_dashboard_id_sr_fk)) { 
                            	folder_name =  $.trim(val.folder); 
                            } else{ 
                            	folder_name =  '-';
                            }
                            
                            if($.trim(val.mobile_view) == ''){ mobile_view =  '-'; }else{ mobile_view =  $.trim(val.mobile_view); }
                            if($.trim(val.dashboard_name) == ''){ dashboard_name =  '-'; }else{ dashboard_name =  $.trim(val.dashboard_name); }
                            if($.trim(val.contract_id_fk) == ''){ contract_id_fk =  '-'; }else{ contract_id_fk =  $.trim(val.contract_id_fk); }
                            if($.trim(val.work_id_fk) == ''){ work_id_fk =  '-'; }else{ work_id_fk =  $.trim(val.work_id_fk); }
                            
                            if($.trim(val.dashboard_type_fk) == ''){ dashboard_type_fk =  '-'; }else{ dashboard_type_fk =  $.trim(val.dashboard_type_fk); }
                            if($.trim(val.priority) == ''){ priority =  '-'; }else{ priority =  $.trim(val.priority); }
                            if($.trim(val.soft_delete_status_fk) == ''){ soft_delete_status_fk =  '-'; }else{ soft_delete_status_fk =  $.trim(val.soft_delete_status_fk); }
                            
                            rowArray.push(dashboard_name);
                           	rowArray.push(work_id_fk + workName);
                           	/* rowArray.push(contract_id_fk + contractName);
                           	rowArray.push(folder_name);
                           	rowArray.push(mobile_view);
                           	rowArray.push(dashboard_type_fk);
                           	rowArray.push(priority);
                           	rowArray.push(soft_delete_status_fk);
                            */
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
                
         	} else {         		
         		$('#webView').css({'display':'block'});
	        	table = $('#data-table-dashboard').DataTable();
	    		 
	    		table.destroy();
	    		
	    		$.fn.dataTable.moment('DD-MMM-YYYY');
	    		table = $('#data-table-dashboard').DataTable({
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
	                        targets: [8],
	                        className: 'last-column'
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
	    	 	var myParams = {module_name_fk : module_name_fk, dashboard_type_fk : dashboard_type_fk, soft_delete_status_fk : soft_delete_status_fk};
	    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-dashboards-list",type:"POST",data:myParams,success : function(data){    				
	    			if(data != null && data != '' && data.length > 0){    					
	             		$.each(data,function(key,val){
	             			var dashboard_id = "'"+val.dashboard_id+"'";
	             			var dashboardVals = "'" + val.dashboard_name + "','" +val.parent_dashboard_id_sr_fk+ "','" +val.dashboard_id+ "'";
	                        var actions = '<a href="javascript:void(0);"  onclick="getDashboard('+dashboard_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
	                        			  +'<a href="javascript:void(0);" onclick="goToDashboardLinks('+dashboardVals+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-share"></i></a>'
	                      	var rowArray = [];    	                 
	                       	
	                    	var workName = '';
	                        if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
	                        
	                        var contractName = '';
	                        if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
	                        
	                        var folder_name = '';
	                        if($.trim(val.dashboard_id) != $.trim(val.parent_dashboard_id_sr_fk)) { 
	                        	folder_name =  $.trim(val.folder); 
	                        } else{ 
	                        	folder_name =  '-';
	                        }
	                        
	                        if($.trim(val.mobile_view) == ''){ mobile_view =  '-'; }else{ mobile_view =  $.trim(val.mobile_view); }
	                        if($.trim(val.dashboard_name) == ''){ dashboard_name =  '-'; }else{ dashboard_name =  $.trim(val.dashboard_name); }
	                        if($.trim(val.contract_id_fk) == ''){ contract_id_fk =  '-'; }else{ contract_id_fk =  $.trim(val.contract_id_fk); }
	                        if($.trim(val.work_id_fk) == ''){ work_id_fk =  '-'; }else{ work_id_fk =  $.trim(val.work_id_fk); }
	                        
	                        if($.trim(val.dashboard_type_fk) == ''){ dashboard_type_fk =  '-'; }else{ dashboard_type_fk =  $.trim(val.dashboard_type_fk); }
	                        if($.trim(val.priority) == ''){ priority =  '-'; }else{ priority =  $.trim(val.priority); }
	                        if($.trim(val.soft_delete_status_fk) == ''){ soft_delete_status_fk =  '-'; }else{ soft_delete_status_fk =  $.trim(val.soft_delete_status_fk); }
	                        
	                        rowArray.push(dashboard_name);
	                       	rowArray.push(work_id_fk + workName);
	                       	rowArray.push(contract_id_fk + contractName);
	                       	rowArray.push(folder_name);
	                       	rowArray.push(mobile_view);
	                       	rowArray.push(dashboard_type_fk);
	                       	rowArray.push(priority);
	                       	rowArray.push(soft_delete_status_fk);
	                       
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
       }
        function getModulesFilterList() {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var dashboard_type_fk = $("#dashboard_type_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(module_name_fk) == "") {
            	$("#module_name_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,dashboard_type_fk : dashboard_type_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getModulesFilterListInDashboard",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#module_name_fk").append('<option value="' + val.module_name_fk + '">' + $.trim(val.module_name_fk) +'</option>');
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
        
        function getDashboardTypesFilterList() {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var dashboard_type_fk = $("#dashboard_type_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(dashboard_type_fk) == "") {
            	$("#dashboard_type_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,dashboard_type_fk : dashboard_type_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getDashboardTypesFilterListInDashboard",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#dashboard_type_fk").append('<option value="' + val.dashboard_type_fk + '">' + $.trim(val.dashboard_type_fk) +'</option>');
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
        
        function getStatusFilterList() {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var dashboard_type_fk = $("#dashboard_type_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(soft_delete_status_fk) == "") {
            	$("#soft_delete_status_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,dashboard_type_fk : dashboard_type_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInDashboard",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
    	                           $("#soft_delete_status_fk").append('<option value="' + val.soft_delete_status_fk + '">' + $.trim(val.soft_delete_status_fk) +'</option>');
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
        
        function getDashboard(dashboard_id){
        	$("#dashboard_id").val(dashboard_id);
        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-dashboard');
        	$('#getForm').submit();
        }
        function goToDashboardLinks(dashboardName,parentId,dshboardId){
        	 if ($.trim(dashboardName) != '') { 
           	  var parentId = $.trim(parentId) ;
           	  var name = $.trim(dashboardName).toLowerCase();
           	  var link = name.replaceAll(" ", "-");
	           	  if($.trim(dshboardId) == $.trim(parentId)){
	           		  console.log(link)
	           		  window.location.href = "<%=request.getContextPath()%>/InfoViz/"+link
	           	  }else{
            		  var parent_dashboard_id_sr_fk = parentId ;
            		  var myParams2 = { parent_dashboard_id_sr_fk: parent_dashboard_id_sr_fk };
            		  $.ajax({
                          url: "<%=request.getContextPath()%>/ajax/getSubLink",
                          data: myParams2, cache: false,
                          success: function (data) {
                              if (data.length > 0) {
                                  $.each(data, function (i, val) {
                                      if (parent_dashboard_id_sr_fk != '') {
                                    	  var sub_link = $.trim(val.subLink).toLowerCase()+"/";
                                    	  window.location.href = "<%=request.getContextPath()%>/InfoViz/"+sub_link+link
                                      } 
                                  });
                              }
                          }
                      });
            	  }
        	
      		  }
        }
      
    </script>
</body>

</html>