<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Structure - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />

    <style>
         p a {
            color: blue;
        }
        .input-field .searchable_label{
        	font-size:0.85rem;
        }     
    	 .fw-400{
    	 	width:400px !important;
    	 	max-width:400px;
    	 }
    	 .fw-300{
    	 	width:300px !important;
    	 	max-width:300px;
    	 }
    	 .fw-200{
    	 	width:200px !important;
    	 	max-width:200px;
    	 }
         .dataTables_filter label::after{
         	content:'';
         }
         .right-btns1 .fa{
         	position:relative;
         	top:-35px;
         }
        
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }
          .right-btns1 .fa+.fa{
         	right:-10px;
         }
         .row.no-mar{
         	margin-bottom:0;
         }

      @media only screen and (max-width: 769px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
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
	        	width:30vw !important;
	        	min-width:30vw;
	        }
		}
		#datatable-structure_mob td > .btn.t-c{
			padding: 0 10px;
		}
		.fw-38w{
			width: 38vw !important;
		}
		.right-btns{ display:none;}

		.right-btns:last-of-type {
		  display:block;
		}
		.no-sort.sorting_asc:before,
.no-sort.sorting_asc:after{
    opacity:0 !important;
    content:'' !important;
}
    </style>
</head>
<body>

   <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="col s12 m12 hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Structure</h6>
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
							<div class="col s12 m4 l-align hide-on-med-and-down">
								<!-- <div class="m-1">
									<a href="javascript:void(0);" onclick="openUploadStructureModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-arrow-circle-up" ></i> Upload Data</strong></a>
									<p style="padding-top: 1rem">
										Click <a href="/pmis/StructureFile.xlsx" download>here</a>
										for the template
									</p>
								</div> -->
							</div>

							<div class="col s12 m4 c-align">
								<div class="m-1 ">
									<a href="<%=request.getContextPath()%>/add-structure-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Structure</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align ">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportStructure();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export Data</strong></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row no-mar">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6 class="hide-on-med-and-down">Update Structure</h6>
								<h6 class="hide-on-large-only">Structure</h6>
							</div>
						</span>
						<div class="row no-mar">
							<div class="col s12 m12">
								<div class="row">
									<div class="col s12 hide-on-large-only mb-md-2 center-align">
									    <a href="<%=request.getContextPath()%>/add-structure-form"
									        class="btn waves-effect waves-light bg-s t-c"> <strong><i
									            class="fa fa-plus-circle"></i> Add Structure</strong></a>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Project</p>
										<select id="project_id_fk" name="project_id_fk"
											  class="searchable" onchange="addInQueProject(this.value);getStructureList();">
											<option value="">Select</option>
											<%--  <c:forEach var="obj" items="${contractList}">
	                       						  <option value="${obj.contract_id }" <c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }</option>
	                                        </c:forEach> --%>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk"
											  class="searchable" onchange="addInQueWork(this.value);getStructureList();">
											<option value="">Select</option>
											<%--  <c:forEach var="obj" items="${contractList}">
	                       						  <option value="${obj.contract_id }" <c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }</option>
	                                        </c:forEach> --%>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Structure</p>
										<select id="structure_fk" name="structure_fk"
											  class="searchable"  onchange="addInQueDepartment(this.value);getStructureList();">
											<option value="">Select</option>
											<%-- <c:forEach var="obj" items="${structureList}">
												<option value="${obj.structureList_fk }"
													<c:if test="${param.department_fk eq obj.department_fk }">selected</c:if>>${obj.department_fk}<c:if
														test="${not empty obj.department_name}"> - </c:if>${obj.department_name}</option>
											</c:forEach> --%>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Contract</p>
										<select id="contract_id_fk" name="contract_id_fk"
										 class="searchable" onchange="addInQueContract(this.value);getStructureList();">
											<option value="">Select</option>
											<c:forEach var="obj" items="${contractList}">
												<option value="${obj.contract_id }"
													<c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }</option>
											</c:forEach>
										</select>
									</div>									
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Work Status</p>
										<select id="work_status_fk" name="work_status_fk"
										 class="searchable" onchange="addInQueContract(this.value);getStructureList();">
											<option value="">Select</option>
											<%-- <c:forEach var="obj" items="${workStatusList}">
												<option value="${obj.work_status_fk }"
													<c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }</option>
											</c:forEach> --%>
										</select>
									</div>									
									<div class="col s12 m4 l2 input-field center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
										<div class="divider hide-on-med-and-up	"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col m12 s12">
									<table id="datatable-structure" class="mdl-data-table">
										<thead>
											<tr>
												<th>Work </th>
												<th>Structures </th>
												<th>Structure Id </th>
												<th>Contract </th>
												<th>Work Status </th>
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
		<%-- <c:if test="${USER_ROLE_CODE eq 'IT' }">
		<div class="row hide-on-med-and-down">
			<div class="col m12 s12">
				 <div class="card">
	                <div class="card-content">
	                    <span class="card-title headbg">
	                        <div class="center-align bg-m p-2 m-b-5">
	                            <h6>Uploaded Structure Data</h6>
	                        </div>
	                    </span>
	                    <div class="">
	                      <!--   <div class="row no-mar" >
	                            <div class="col m5 hide-on-small-only"></div>                            
	                                    <div class="col s12 m2 input-field">
	                                        <p class="searchable_label">Work</p>
	                                        <select id="work_id_fk_filter" name="work_id_fk" class="searchable" onchange="">
	                                            <option value="">Select</option>
	                                        </select>
	                                    </div>                                 
	                                    <div class="col s12 m3">
	                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
	                                            style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
	                                            Filters</button>
	                                    </div>                          
	                            <div class="col m5 hide-on-small-only"></div>
	                        </div> -->
	                        <div class="row">
	                            <div class="col m12 s12">
	                                <table id="structure-table" class="mdl-data-table">
	                                    <thead>
	                                        <tr>                                            
												<th>Uploaded File</th>
												<th>Status</th>
												<th>Remarks</th>
												<th>Uploaded by </th>
												<th>Uploaded On</th>
	                                        </tr>
	                                    </thead>
										<tbody>
											<!-- <tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
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
	</c:if> --%>
	</div>

	<!-- update popup starts -->
	<div id="upload_template" class="modal">
		<div class="modal-content">
			<div class="center-align p-2 bg-m modal-title">
				<h6>Upload Structure</h6>
			</div>
			<!-- form start-->
			<div class="container">
				<form action="<%=request.getContextPath()%>/upload-structures"
					method="post" enctype="multipart/form-data">
					<div class="row no-mar">
						<div class="col s12 m12 input-field center-align">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m">
											<span>Attachment</span> <input type="file" id="structureFile"
												name="structureFile" required="required">
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
									style="width: 100%;" onclick="closeUploadStructureModal();">Cancel</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>



	<div class="page-loader" style="display: none;">
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

	<!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
	 <form action="<%=request.getContextPath()%>/get-structure" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="structure_id" id="structure_id"/>
    </form>
    
  <form action="<%=request.getContextPath()%>/export-structure" name="exportStructureForm" id="exportStructureForm" target="_blank" method="post">	
        <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="department_fk" id="exportDepartment_id_fk" />
        <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
	</form>
	
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>
	
		var filtersMap = new Object();
		
		function openUploadStructureModal() {
			$("#structureFile").val('');
			$("#upload_template").modal('open');
		}

		function closeUploadStructureModal() {
			$("#structureFile").val('');
			$("#upload_template").modal('close');
		}

		$(document).ready(function() {
			$('.modal').modal();
			$('select:not(.searchable)').formSelect();
			$('.searchable').select2();
			
			var filters = window.localStorage.getItem("structureFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
		        		  getProjectListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorksListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractListFilter(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'department_fk'){
		        		  getDepartmentListFilter(temp2[1]);
		        	  }
	        	  }
	          }
            }
            
			$('.close-message').delay(3000).fadeOut('slow');

		     $('#structure-table').DataTable({
	                columnDefs: [
	                    {
	                        targets: [0],
	                        className: 'mdl-data-table__cell--non-numeric',
	                        targets: 'no-sort', orderable: false,
	                    },
	                    //{ "width": "10px", "targets": [2] },
	                ],
	                "sScrollX": "100%",
	                "sScrollXInner": "100%",
	                "bScrollCollapse": true,
	                "bAutoWidth": true,
	                "ordering": false, //to stop sorting option                
	                fixedHeader: true, // to change the language of data table	          
	                initComplete: function () {
	                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	                }
	            });
			getStructureList();
			//getStructureUploadsList();
		});

		function clearFilter() {
			$("#project_id_fk").val('');
			$("#work_id_fk").val('');
			$("#department_fk").val('');
			$("#contract_id_fk").val('');
			$('.searchable').select2();
			window.localStorage.setItem("structureFilters",'');
			window.location.href = "<%=request.getContextPath()%>/structure";
			var table = $('#datatable-structure').DataTable();
    		table.draw( true );
			//getStructureUploadsList();
		}
        
		  function addInQueProject(project_id_fk){
	        	Object.keys(filtersMap).forEach(function (key) {
		   			if(key.match('project_id_fk')) delete filtersMap[key];
		   		});
	        	if($.trim(project_id_fk) != ''){
	       	    	filtersMap["project_id_fk"] = project_id_fk;
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
	        
      <%--   function getStructureUploadsList(){
        	$(".page-loader-2").show();
        	
        	table1 = $('#structure-table').DataTable();
    		table1.destroy();
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table1 = $('#structure-table').DataTable({
    			"order": [],
        		"bStateSave": false,
        		fixedHeader: true,
                "fnStateSave": function (oSettings, oData) {
                    localStorage.setItem('MRVCDataTablesUpload', JSON.stringify(oData));
                },
                "fnStateLoad": function (oSettings) {
                    return JSON.parse(localStorage.getItem('MRVCDataTablesUpload'));
                },
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric'
                    },
                    { orderable: false, 'aTargets': ['no-sort'] }
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "ordering":false,
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            }).rows().remove().draw();
    		
    		table1.state.clear();		
    		var myParams = {};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getStructureUploadsList",
				type:"POST",
    			data:myParams,async: false,
    			success : function(data){    				
    				if(data != null && data != '' && data.length > 0){    					
	             		$.each(data,function(key,val){
	             			var design_data_id = "'"+val.design_data_id+"'"; 
	                        var filePath = "";
	                        
	                        if($.trim(val.uploaded_file) != ''){
	                        	filePath = '<a href="<%=CommonConstants.STRUCTURE_UPLOADED_FILES%>'+ val.uploaded_file +'">'+val.uploaded_file + '</a>';
	                        }
	                        var rowArray = [];    	                 
	                        
	                       	rowArray.push(filePath);
	                       	rowArray.push($.trim(val.status));
	                       	rowArray.push($.trim(val.remarks));
	                       	rowArray.push($.trim(val.uploaded_by_user_id_fk));
	                       	rowArray.push($.trim(val.uploaded_on)); 
	                       	
	                        table1.row.add(rowArray).draw( true );
	                        		                       
	    				});
	             		
	             		$(".page-loader-2").hide();
	    			}else{
	    				$(".page-loader-2").hide();
	    			}
    			
    		},error: function (jqXHR, exception) {
    			$(".page-loader-2").hide();
             	getErrorMessage(jqXHR, exception);
         }});
       } --%>
        
		function getStructureList() {
			$(".page-loader-2").show();

			getWorksListFilter('');
			getProjectListFilter('');
			getDepartmentListFilter('');
			getContractListFilter('');
						
			var work_id_fk = $("#work_id_fk").val();
			var contract_id_fk = $("#contract_id_fk").val();
			var department_fk = $("#department_fk").val();
			var project_id_fk = $("#project_id_fk").val();
			
			var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("structureFilters", filters);
   			});
        	 
						table = $('#datatable-structure').DataTable();
			
						table.destroy();
			
						$.fn.dataTable.moment('DD-MMM-YYYY');
			
						var myParams = "work_id_fk=" + work_id_fk + "&contract_id_fk="
								+ contract_id_fk + "&department_fk="
								+ encodeURIComponent(department_fk) 
								+ "&project_id_fk=" + project_id_fk;
			
						/***************************************************************************************************/
			
						$("#datatable-structure")
								.DataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sort" : "position",
											//bStateSave variable you can use to save state on client cookies: set value "true" 
											"bStateSave" : false,
											 stateSave: true,
											 "fnStateSave": function (oSettings, oData) {
								                    localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
								                },
								                "fnStateLoad": function (oSettings) {
								                    return JSON.parse(localStorage.getItem('MRVCDataTables'));
								                },
											//Default: Page display length
											"iDisplayLength" : 10,
											"iData" : {
												"start" : 52
											},
											//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
											"iDisplayStart" : 0,
											"fnDrawCallback" : function() {
												//Get page numer on client. Please note: number start from 0 So
												//for the first page you will see 0 second page 1 third page 2...
												//Un-comment below alert to see page number
												//alert("Current page number: "+this.fnPagingInfo().iPage);
											},
											//"sDom": 'l<"toolbar">frtip',
											"initComplete" : function() {
												$('.dataTables_filter input[type="search"]')
														.attr('placeholder', 'Search')
														.css({
															'width' : '350px ',
															'display' : 'inline-block'
														});
			
												var input = $('.dataTables_filter input')
														.unbind()
														.bind('keyup',function(e){
														    if (e.which == 13){
														    	self.search(input.val()).draw();
														    }
														}), self = this.api(), $searchButton = $(
														'<i class="fa fa-search" title="Go" >')
												//.text('Go')
												.click(function() {
													self.search(input.val()).draw();
												}), $clearButton = $(
														'<i class="fa fa-close" title="Reset">')
												//.text('X')
												.click(function() {
													input.val('');
													$searchButton.click();
												})
												$("div.right-btns1").remove();
												$('.dataTables_filter').append(
														'<div class="right-btns"></div>');
												$('.dataTables_filter div').append(
														$searchButton, $clearButton);
												
											},
											columnDefs : [ {
												"targets" : 'no-sort',
												"orderable" : false,
											},
											// {targets:[1,2,4,5], className: 'hideCOl'},
											{ targets: [0], className: 'no-sort'  },
											//{ targets: [0,3], className: 'fw-111'  }  
											],
											"sScrollX" : "100%",
											"ordering":false,
											"sScrollXInner" : "100%",
											"bScrollCollapse" : true,
											"language" : {
												"info" : "_START_ - _END_ of _TOTAL_",
												paginate : {
													next : '<i class="fa fa-angle-right"></i>', // or '→'
													previous : '<i class="fa fa-angle-left"></i>' // or '←' 
												}
											},
											"bDestroy" : true,
											"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/getStructureList?"+myParams,
													
						        "aoColumns": [
						        	
						            { "mData": function(data,type,row){
						            	var work_short_name = '';
				                        if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
				                     	if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk + work_short_name; }
			            			} },   				            
						            { "mData": function(data,type,row){
						            	var structureType = data.structure_type_fk;
										if(structureType.indexOf(",") > -1){
											structureType = structureType.replace(/,/g, "<br />");
						            	}
						            	if($.trim(data.structure_type_fk) == ''){ return '-'; }else{ return structureType; }
						            } },
						            { "mData": function(data,type,row){
						            	if($.trim(data.department_fk) == ''){ return '-'; }else{ return data.department_name; }
						            } },
						            { "mData": function(data,type,row){
						            	if($.trim(data.department_fk) == ''){ return '-'; }else{ return data.department_name; }
						            } },
						         	{ "mData": function(data,type,row){
						         		var contract_short_name = '';
						         		if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) } 
						            	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk + contract_short_name; }
						            } },
						         	{ "mData": function(data,type,row){
						         		var structure_id = "'"+data.structure_id+"'";
					                    var actions = '<a href="javascript:void(0);"  onclick="getStructure('+structure_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
						            	return actions;
						            } }
						            
						        ]
						    });
		  $(".page-loader-2").hide();  		     
      	
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
	  	
	    function getWorksListFilter(work) {
	    	var work_id_fk = $("#work_id_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
         	$(".page-loader").show();
            if ($.trim(work_id_fk) == "") {
                $("#work_id_fk option:not(:first)").remove();
     		 	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk, project_id_fk : project_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksListFilterInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) } 
                            	var selectedFlag = (work == val.work_id_fk)?'selected':'';
 	                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + work_short_name +'</option>');
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
	  	
	    function getContractListFilter(contract) {
	    	var work_id_fk = $("#work_id_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
         	$(".page-loader").show();
            if ($.trim(contract_id_fk) == "") {
                $("#contract_id_fk option:not(:first)").remove();
     		 	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk, project_id_fk : project_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsListFilterInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_short_name = '';
                            	if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                            	var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
 	                            $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '"'+selectedFlag+'>' + $.trim(val.contract_id_fk) + contract_short_name +'</option>');
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
      	
        function getProjectListFilter(project) {
        	var work_id_fk = $("#work_id_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
         	$(".page-loader").show();
            if ($.trim(project_id_fk) == "") {
                $("#project_id_fk option:not(:first)").remove();
     		 	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk, project_id_fk : project_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getProjectsListFilterInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var selectedFlag = (project == val.project_id_fk)?'selected':'';
                            	$("#project_id_fk").append('<option value="' + val.project_id_fk + '" '+selectedFlag+'>' + val.project_id_fk + $.trim(val.project_name) +'</option>');
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
        
        function getDepartmentListFilter(department) {
        	var work_id_fk = $("#work_id_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
         	$(".page-loader").show();
            if ($.trim(department_fk) == "") {
                $("#department_fk option:not(:first)").remove();
     		  	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk, department_fk : department_fk, project_id_fk : project_id_fk};
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getDepartmentsListFilterInStructure",
                     data: myParams, cache: false,async: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                            	var selectedFlag = (department == val.department_fk)?'selected':'';
                           	 	$("#department_fk").append('<option value="' + val.department_fk + '"'+selectedFlag+'>' + $.trim(val.department_name) +'</option>');
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
        
	    function getStructure(structure_id) {
			$("#structure_id").val(structure_id);
			$("#getForm").submit();
		}
	
	    function exportStructure(){
	    	var work_id_fk = $("#work_id_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
	     	 
	    	 $("#exportWork_id_fk").val(work_id_fk);
	     	 $("#exportContract_id_fk").val(contract_id_fk);
	     	 $("#exportDepartment_id_fk").val(department_fk);
	     	 $("#exportProject_id_fk").val(project_id_fk);
	     	 $("#exportStructureForm ").submit();
	  	}
    
    </script> 
</body>
</html>