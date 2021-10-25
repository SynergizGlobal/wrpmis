<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Users - Admin - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<!-- <link rel="stylesheet" href="/pmis/resources/css/users.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
	<style>
      	
      	p a{
      		color:blue;
      	}
      	  .input-field .searchable_label{
        	font-size:0.85rem;
        } 
		.row.no-mar {
            margin-bottom: 0;
        }
        .fw-150{
        	width:150px !important;
        	max-width:150px;
        }
        .fw-200{
        	width:200px !important;
        	max-width:200px;
        }
        @media only screen and (max-width: 769px){ 
		
		.dataTables_scrollBody tbody tr td:last-of-type,
		.no-sort{
			padding:3px !important;
			max-width: 45px;
		}
		td:not(:last-of-type),
		th:not(:last-of-type){
			width:30vw !important;
		}
		.mob-btn{
			padding:0 12px; 
		}
		.hideCOl{
			display:none;
		} 
		.r-300{
			width:30vw !important;
       		max-width:30vw;
		}
		 .dataTables_filter label{
        	position:relative;
        }
        .dataTables_filter label::after{
        	position:absolute;
        	right:5px;
        	top:30px;
        }
        .fw-111{
        	width:30vw;
        	min-width:30vw;
        }
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
	<jsp:include page="../layout/header.jsp"></jsp:include>


	<div class="row">
		<div class="col s12 m12 hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-2">
							<h6>Users</h6>
						</div>
					</span>
					<div class="">
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
						<div class="row plr-1 center-align">
							<div class="col s12 m4">
								<div class="m-1 l-align">
									<a href="javascript:void(0);" onclick="openUploadUsersModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
									<p style="padding-top: 1rem">
										Click <a href="/pmis/Users.xlsx" download>here</a> for the
										template
									</p>
								</div>
							</div>

							<div class="col s12 m4">
								<div class="m-1 c-align">
									<a href="<%=request.getContextPath()%>/add-user-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Users</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportUser();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export Data</strong></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6 class="hide-on-med-and-down">Update User</h6>
								<h6 class="hide-on-large-only">Users</h6>
							</div>
						</span>
						<div class="row no-mar" >
							<div class="col s12 hide-on-large-only mb-md-2 center-align">
							    <a href="<%=request.getContextPath()%>/add-user-form"
							        class="btn waves-effect waves-light bg-s t-c"> <strong><i
							            class="fa fa-plus-circle"></i> Add Users</strong></a>
							</div>
							<div class="col m12 s12 l10 offset-l1">
								<div class="row no-mar">
									<div class="col s6 m4 l2 input-field offset-l1">
										<p class="searchable_label">User Type</p>
										<select id="user_type_fk" name="user_type_fk"
											class="searchable" onchange="addInQueUserType(this.value);getUsersList();">
											<option value="">Select</option>
											<%-- <c:forEach var="obj" items="${roles }">
                                            	<option value="${obj.user_role_name }">${obj.user_role_name }</option>
                                            </c:forEach> --%>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">User Role</p>
										<select id="user_role_name_fk" name="user_role_name_fk"
											class="searchable" onchange="addInQueUserRole(this.value);getUsersList();">
											<option value="">Select</option>
											<%-- <c:forEach var="obj" items="${roles }">
                                            	<option value="${obj.user_role_name }">${obj.user_role_name }</option>
                                            </c:forEach> --%>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Department</p>
										<select id="department_fk" name="department_fk"
											class="searchable" onchange="addInQueDepartment(this.value);getUsersList();">
											<option value="">Select</option>
											<%-- <c:forEach var="obj" items="${departments }">
                                            	<option value="${obj.department }">${obj.department_name }</option>
                                            </c:forEach> --%>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Reporting To</p>
										<select id="reporting_to_id_srfk" name="reporting_to_id_srfk"
											class="searchable" onchange="addInQueReportingTo(this.value);getUsersList();">
											<option value="">Select</option>
											<%-- <c:forEach var="obj" items="${reportingToList }">
                                            	<option value="${obj.user_id }"><c:if test="${not empty obj.designation}">${obj.designation } - </c:if>${obj.user_name }</option>
                                            </c:forEach> --%>
										</select>
									</div>
									<div class="col s12 m4 l2 center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="margin-top: 10px; width: 100%"
											onclick="clearFilter();">Clear Filters</button>
									</div>
								</div>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>
 
						<div class="row">
							<div class="col m12 s12">
								<table id="datatable-users" class="mdl-data-table">
									<thead>
										<tr>
											<th class="no-sort">ID</th>
											<th>Name</th>
											<th>Designation</th>
											<th>Department</th>
											<th>Reporting To</th>
											<th>User Type &nbsp; </th>
											<th>User Role</th>
											<th>Last Login</th>
											<th>Last <br> 7 days</th>
											<th>Last <br> 30 days</th>
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


	<!-- update popup starts -->
    <div id="upload_template" class="modal">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Upload Users</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-users" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="fileName" name="fileName" required="required">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="submit" class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">Update</button>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;" onclick="closeUploadUsersModal();">Cancel</button>
                            </div>
                        </div>
                    </div>
                </form>
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
	
	<form action="<%=request.getContextPath()%>/get-user" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="user_id" id="user_id"/>
    </form>
  
  
	<form action="<%=request.getContextPath() %>/export-users" name="exportUserForm" id="exportUserForm" target="_blank" method="post">	
        <input type="hidden" name="user_role_name_fk" id="exportUser_role_name_fk" />
         <input type="hidden" name="department_fk" id="exportDepartment_fk" />
        <input type="hidden" name="reporting_to_id_srfk" id="exportReporting_to_id_srfk" />
	</form>
	
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	
	<script>
	$(document).keypress(function(e){
    if (e.which == 13){
        $("#save_post").click();
    }
	});
	</script>
	<script>
		
		var filtersMap = new Object();
	
		function  openUploadUsersModal() {
			$("#fileName").val('');
	    	$("#upload_template").modal('open');
		}
	
		function  closeUploadUsersModal() {
			$("#fileName").val('');
	    	$("#upload_template").modal('close');
		}
	
        $(document).ready(function () {
        	$('.modal').modal();
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        	
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	var filters = window.localStorage.getItem("usersFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'user_type_fk' ){
		        		  getUserTypesFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'user_role_name_fk'){
		        		  getUserRolesFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'department_fk'){
		        		  getUserDepartmentsFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'reporting_to_id_srfk'){
		        		  getUserReportingToListFilter(temp2[1]);
		        	  }
	        	  }
	          }
            }
        	
        	getUsersList();
        });
        
        function addInQueUserType(user_type_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('user_type_fk')) delete filtersMap[key];
	   		});
        	if($.trim(user_type_fk) != ''){
       	    	filtersMap["user_type_fk"] = user_type_fk;
        	}
        }
        
        function addInQueUserRole(user_role_name_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('user_role_name_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(user_role_name_fk) != ''){
            	filtersMap["user_role_name_fk"] = user_role_name_fk;
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
        
        function addInQueReportingTo(reporting_to_id_srfk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('reporting_to_id_srfk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(reporting_to_id_srfk) != ''){
            	filtersMap["reporting_to_id_srfk"] = reporting_to_id_srfk;
	      	}
        }
        
        function clearFilter(){
        	$("#user_type_fk").val('');
        	$("#user_role_name_fk").val('');
        	$("#department_fk").val('');
        	$("#reporting_to_id_srfk").val('');
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            
            window.localStorage.setItem("usersFilters",'');
            window.location.href="<%=request.getContextPath()%>/users"
        	//getUsersList();
        }
        
        function getUsersList(){
        	$(".page-loader-2").show();
          	
          	getUserTypesFilter('');
          	getUserRolesFilter('');
          	getUserDepartmentsFilter('');
          	getUserReportingToListFilter('');
          	

        	var user_type_fk = $("#user_type_fk").val();
        	var user_role_name_fk = $("#user_role_name_fk").val();
          	var department_fk = $("#department_fk").val();
          	var reporting_to_id_srfk = $("#reporting_to_id_srfk").val();
          	
          	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("usersFilters", filters);
   			});
        		table = $('#datatable-users').DataTable();
       		 
        		table.destroy();
        		
        		$.fn.dataTable.moment('DD-MMM-YYYY');
        		table = $('#datatable-users').DataTable({
        			"bSort": false,
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
                            targets: [2,3,4,5,6,7,8,9],
                            className: 'hideCOl'
                        }
                        ,{ targets: [0], 
                        	className: 'fw-150'  
                        }
                        ,{ targets: [1], 
                        	className: 'fw-200'  
                        }
                        ,{ orderable: false, 'aTargets': ['nosort'] },
                        { "width": "20px", "targets": [6] },
                    ],
                    // "ScrollX": true,
                    //"scrollCollapse": true,
                    //"sScrollY": 400,
                    "sScrollX": "100%",
                    "sScrollXInner": "100%",
                    "bScrollCollapse": true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                        var input = $('.dataTables_filter input')
    					.unbind(), self = this.api(), $searchButton = $(
    					'<i class="fa fa-search" title="Go" id="save_post">')
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
        	 
        	 	var myParams = {user_type_fk : user_type_fk,user_role_name_fk : user_role_name_fk,department_fk :department_fk, reporting_to_id_srfk : reporting_to_id_srfk};
        		$.ajax({url : "<%=request.getContextPath()%>/ajax/getUsersList",type:"POST",data:myParams,success : function(data){    				
        				if(data != null && data != '' && data.length > 0){    					
        	         		$.each(data,function(key,val){
        	         			var user_id = "'"+val.user_id+"'";
        	                    var actions = '<a href="javascript:void(0);"  onclick="getUser('+user_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
        	                   	var rowArray = [];    	                  
        	                   	
        	                   	
        	                   	rowArray.push($.trim(val.user_id));
        	                   	rowArray.push($.trim(val.user_name));
        	                   	rowArray.push($.trim(val.designation));
        	                   	rowArray.push($.trim(val.department_name));
        	                   	rowArray.push($.trim(val.reporting_to_name));
        	                   	rowArray.push($.trim(val.user_type_fk));
        	                   	rowArray.push($.trim(val.user_role_name_fk));
        	                   	
        	                   	rowArray.push($.trim(val.last_login));
        	                   	rowArray.push($.trim(val.last7DaysLogins));
        	                   	rowArray.push($.trim(val.last30DaysLogins));
        	                   	
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
      	
        function getUserTypesFilter(user_type) {
        	var user_type_fk = $("#user_type_fk").val();
        	var user_role_name_fk = $("#user_role_name_fk").val();
          	var department_fk = $("#department_fk").val();
          	var reporting_to_id_srfk = $("#reporting_to_id_srfk").val();
 	      
        	$(".page-loader").show();

            if ($.trim(user_type_fk) == "") {
                $("#user_type_fk option:not(:first)").remove();
                var myParams = {user_type_fk : user_type_fk,user_role_name_fk : user_role_name_fk,department_fk :department_fk, reporting_to_id_srfk : reporting_to_id_srfk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getUserTypesFilterInUser",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFlag = (user_type == val.user_type_fk)?'selected':'';
 	                            $("#user_type_fk").append('<option value="' + val.user_type_fk + '" '+selectedFlag+'>' + $.trim(val.user_type_fk) +'</option>');
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
      	
        function getUserRolesFilter(user_role) {
        	var user_type_fk = $("#user_type_fk").val();
        	var user_role_name_fk = $("#user_role_name_fk").val();
          	var department_fk = $("#department_fk").val();
          	var reporting_to_id_srfk = $("#reporting_to_id_srfk").val();
 	      
        	$(".page-loader").show();

            if ($.trim(user_role_name_fk) == "") {
                $("#user_role_name_fk option:not(:first)").remove();
                var myParams = {user_type_fk : user_type_fk,user_role_name_fk : user_role_name_fk,department_fk :department_fk, reporting_to_id_srfk : reporting_to_id_srfk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getUserRolesFilterInUser",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var selectedFlag = (user_role == val.user_role_name_fk)?'selected':'';
 	                            $("#user_role_name_fk").append('<option value="' + val.user_role_name_fk + '" '+selectedFlag+'>' + $.trim(val.user_role_name_fk) +'</option>');
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
       
        
        function getUserDepartmentsFilter(department) {
        	var user_type_fk = $("#user_type_fk").val();
        	var user_role_name_fk = $("#user_role_name_fk").val();
          	var department_fk = $("#department_fk").val();
          	var reporting_to_id_srfk = $("#reporting_to_id_srfk").val();
            $(".page-loader").show();

            if ($.trim(department_fk) == "") {
                $("#department_fk option:not(:first)").remove();
                var myParams = {user_type_fk : user_type_fk,user_role_name_fk : user_role_name_fk,department_fk :department_fk, reporting_to_id_srfk : reporting_to_id_srfk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getUserDepartmentsFilterInUser",
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
        
        function getUserReportingToListFilter(reporting_to) {
        	var user_type_fk = $("#user_type_fk").val();
        	var user_role_name_fk = $("#user_role_name_fk").val();
          	var department_fk = $("#department_fk").val();
          	var reporting_to_id_srfk = $("#reporting_to_id_srfk").val();
  	       
         	$(".page-loader").show();

            if ($.trim(reporting_to_id_srfk) == "") {
                 $("#reporting_to_id_srfk option:not(:first)").remove();
                 var myParams = {user_type_fk : user_type_fk,user_role_name_fk : user_role_name_fk,department_fk :department_fk, reporting_to_id_srfk : reporting_to_id_srfk};
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getUserReportingToListFilterInUser",
                     data: myParams, cache: false,async: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                           	 	var designation = '';
                            	if ($.trim(val.designation) != '') { designation =  $.trim(val.designation) }  	    
                            	var selectedFlag = (reporting_to == val.user_id)?'selected':'';
                               	$("#reporting_to_id_srfk").append('<option value="' + val.user_id + '" '+selectedFlag+'>' + /* $.trim(val.reporting_to_name)  + */ designation +'</option>');
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
        
        function getUser(user_id) {
    		$("#user_id").val(user_id);
    		$("#getForm").submit();
    	}
        
        function exportUser(){
          	 var user_role_name_fk = $("#user_role_name_fk").val();
          	 var department_fk = $("#department_fk").val();
          	 var reporting_to_id_srfk = $("#reporting_to_id_srfk").val();
          	 
          	 $("#exportUser_role_name_fk").val(user_role_name_fk);
          	 $("#exportDepartment_fk").val(department_fk);
          	 $("#exportReporting_to_id_srfk").val(reporting_to_id_srfk);
          	 $("#exportUserForm").submit();
       	}


    </script>
</body>
</html>