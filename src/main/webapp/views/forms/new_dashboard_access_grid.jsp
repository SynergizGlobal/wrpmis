<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboards - Admin - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked{position: relative; opacity: 1;pointer-events: auto;}
    </style>
</head>

<body>

    <!-- header  starts-->
  <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Dashboard Access</h6>
                        </div>
                    </span>
                    <div class="">
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if> 
						
                        <!-- <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Module </p>
                                        <select id="module_name_fk" class="searchable" name="module_name_fk" onchange="addInQueModule(this.value);getDashboardsList();">
		                                    <option value="" >Select</option>
		                                </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label"> Dashboard Type</p>
                                        <select id="dashboard_type_fk" class="searchable" name="dashboard_type_fk" onchange="addInQueDashboardType(this.value);getDashboardsList();">
		                                    <option value="" >Select</option>
		                                </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Status</p>
                                        <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk" onchange="addInQueStatus(this.value);getDashboardsList();">
		                                    <option value="" >Select</option>
		                                </select>
                                    </div>
                                    <div class="col s12 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="data-table-dashboard" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Module</th>
                                            <th>Folder</th>
                                            <th>Status</th>
                                            <th>User Role Access</th>
                                            <th>User Type Access</th>
                                            <th>User Access</th>
                                            <th class="no-sort">Action</th>
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
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="dashboard_id" id="dashboard_id" />
    </form>
    <!-- footer  -->
  	<!-- footer included -->
  	<jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script type="text/javascript">
   		 var filtersMap = new Object();
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();         	
         	$('.close-message').delay(3000).fadeOut('slow');
         	
            var filters = window.localStorage.getItem("dashboardFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^');  
        	  for(var i=0;i< temp.length;i++){
 	        	  if($.trim(temp[i]) != '' ){
 	        		  var temp2 = temp[i].split('=');
 		        	  if($.trim(temp2[0]) == 'module_name_fk' ){
 		        		 getModulesFilterList(temp2[1]);
 		        	  }else if($.trim(temp2[0]) == 'dashboard_type_fk'){
 		        		 getDashboardTypesFilterList(temp2[1]);
 		        	  }else if($.trim(temp2[0]) == 'soft_delete_status_fk'){
 		        		 getStatusFilterList(temp2[1]);
 		        	  }
 	        	  }
 	          }
            }
            
         	getDashboardsList();
        });

        function clearFilters() {
            $('#module_name_fk').val("");
            $('#dashboard_type_fk').val("");
            $('#soft_delete_status_fk').val("");
            $('.searchable').select2();
            getDashboardsList();

        	window.localStorage.setItem("dashboardFilters",'');
        	window.location.href= "<%=request.getContextPath()%>/dashboards";
        }
        
        function addInQueModule(module_name_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('module_name_fk')) delete filtersMap[key];
       		});
        	if($.trim(module_name_fk) != ''){
       	    	filtersMap["module_name_fk"] = module_name_fk;
        	}
        }
        
        function addInQueDashboardType(dashboard_type_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('dashboard_type_fk')) delete filtersMap[key];
       		});
        	if($.trim(dashboard_type_fk) != ''){
       	    	filtersMap["dashboard_type_fk"] = dashboard_type_fk;
        	}
        }
        function addInQueStatus(soft_delete_status_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('soft_delete_status_fk')) delete filtersMap[key];
       		});
        	if($.trim(soft_delete_status_fk) != ''){
       	    	filtersMap["soft_delete_status_fk"] = soft_delete_status_fk;
        	}
        }
        
        function getDashboardsList(){
        	$(".page-loader-2").show();
        	var module_name_fk = $("#module_name_fk").val();
        	var dashboard_type_fk = $("#dashboard_type_fk").val();
        	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
        	getModulesFilterList('');
         	getDashboardTypesFilterList('');
         	getStatusFilterList('');
         	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
        		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("dashboardFilters", filters);
    		});

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
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [7] },
                ], 
                "scrollCollapse": true,
                "sScrollY": 400,
                ordering: false,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "ordering":false,
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
					.unbind()
					.bind('keyup',function(e){
					    if (e.which == 13){
					    	self.search(input.val()).draw();
					    }
					}), self = this.api(), $searchButton = $(
					'<i class="fa fa-search" title="Go" >')
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
    	 	var myParams = {module_name_fk : module_name_fk, dashboard_type_fk : dashboard_type_fk, soft_delete_status_fk : soft_delete_status_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-dashboards-list",type:"POST",data:myParams,success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var dashboard_id = "'"+val.dashboard_id+"'";
             			var dashboardVals = "'" + val.dashboard_name + "','" +val.parent_dashboard_id_sr_fk+ "','" +val.dashboard_id+ "'";
                        var actions = '<a href="javascript:void(0);"  onclick="getDashboard('+dashboard_id+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-pencil"></i></a>'
                        			  +'<a href="javascript:void(0);" onclick="goToDashboardLinks('+dashboardVals+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-share"></i></a>'
                      	var rowArray = [];
                        
                        var folder_name = '';
                        if($.trim(val.dashboard_id) != $.trim(val.parent_dashboard_id_sr_fk)) { 
                        	folder_name =  $.trim(val.folder); 
                        } else{ 
                        	folder_name =  '-';
                        }
                        
                        if($.trim(val.dashboard_name) == ''){ dashboard_name =  '-'; }else{ dashboard_name =  $.trim(val.dashboard_name); }
                        if($.trim(val.module_name_fk) == ''){ module_name_fk =  '-'; }else{ module_name_fk =  $.trim(val.module_name_fk); }
                        if($.trim(val.soft_delete_status_fk) == ''){ soft_delete_status_fk =  '-'; }else{ soft_delete_status_fk =  $.trim(val.soft_delete_status_fk); }
                        
                        if($.trim(val.user_role_access) == ''){ user_role_access =  '-'; }else{ user_role_access =  $.trim(val.user_role_access); }
                        if($.trim(val.user_type_access) == ''){ user_type_access =  '-'; }else{ user_type_access =  $.trim(val.user_type_access); }
                        if($.trim(val.user_access) == ''){ user_access =  '-'; }else{ user_access =  $.trim(val.user_access); }
                        
                        
                        rowArray.push(dashboard_name);
                       	rowArray.push(module_name_fk);
                       	rowArray.push(folder_name);
                       	rowArray.push(soft_delete_status_fk);                       	
                       	rowArray.push(user_role_access);
                       	rowArray.push(user_type_access);
                       	rowArray.push(user_access);
                       	
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
        function getModulesFilterList(module) {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var dashboard_type_fk = $("#dashboard_type_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(module_name_fk) == "") {
            	$("#module_name_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,dashboard_type_fk : dashboard_type_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getModulesFilterListInDashboard",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (module == val.module_name_fk)?'selected':'';
    	                           $("#module_name_fk").append('<option value="' + val.module_name_fk + '"'+selectedFlag+'>' + $.trim(val.module_name_fk) +'</option>');
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
        
        function getDashboardTypesFilterList(dashboard_type) {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var dashboard_type_fk = $("#dashboard_type_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(dashboard_type_fk) == "") {
            	$("#dashboard_type_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,dashboard_type_fk : dashboard_type_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getDashboardTypesFilterListInDashboard",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (dashboard_type == val.dashboard_type_fk)?'selected':'';
    	                           $("#dashboard_type_fk").append('<option value="' + val.dashboard_type_fk + '"'+selectedFlag+'>' + $.trim(val.dashboard_type_fk) +'</option>');
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
        
        function getStatusFilterList(status) {
        	$(".page-loader").show();
            var module_name_fk = $("#module_name_fk").val();
            var dashboard_type_fk = $("#dashboard_type_fk").val();
            var soft_delete_status_fk = $("#soft_delete_status_fk").val();
            if ($.trim(soft_delete_status_fk) == "") {
            	$("#soft_delete_status_fk option:not(:first)").remove();
            	var myParams = { module_name_fk: module_name_fk,dashboard_type_fk : dashboard_type_fk,soft_delete_status_fk: soft_delete_status_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInDashboard",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var selectedFlag = (status == val.soft_delete_status_fk)?'selected':'';
    	                           $("#soft_delete_status_fk").append('<option value="' + val.soft_delete_status_fk + '"'+selectedFlag+'>' + $.trim(val.soft_delete_status_fk) +'</option>');
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
        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-tableau-dashboard');
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