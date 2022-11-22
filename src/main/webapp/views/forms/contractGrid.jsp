<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contract.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>        
        .input-field .searchable_label{
        	font-size:0.85rem;
        }
    	 .fw-250{
    	 	width:250px !important;
    	 	max-width:250px;
    	 }
    	 .fw-150{
    	 	width:150px !important;
    	 	max-width:150px;
    	 } 
    	 .dataTables_filter label::after{
         	content:'';
         }
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
         }   
         .no-wrap{
         	white-space:nowrap;
         }
         @media only screen and (max-width: 1366px) and (min-width:1023px){ 
         	tbody td{
         		padding:12px 10px !important;
         	}
         }
           @media only screen and (max-width: 820px){ 
			
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
			.fw-2{
				width:45vw !important;
        		max-width:45vw;  
        	}
        	.fw-1{
				width:30vw !important;
        		max-width:30vw; 
        	} 
        	.cw-m{width: 8rem;}
			
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
		.page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}		
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		
	.m-n1 {
        margin: -2rem auto 0;
    }


.sorting_asc:after, .sorting_desc:after {
  content: "" !important;
}

.sorting_asc:before, .sorting_desc:before {
  content: "" !important;
}


    @media only screen and (max-width: 820px) {
        .mob-mar {
            text-align: left;
        }

        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    </style>
</head>

<body>
    <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->
	<div class="row">
		<%-- <div class="col s12 m12 hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Contract</h6>
						</div>
					</span>

					<div class="row clearfix">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<c:if test="${not empty success }">
								<div class="center-align m-1 close-message">${success}</div>
							</c:if>
							<c:if test="${not empty error }">
								<div class="center-align m-1 close-message">${error}</div>
							</c:if>
						</div>
					</div>
					<div class="">
						<div class="row plr-1 center-align">
							<div class="col s12 m4">
								<!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
							</div>
							<c:choose>
							    <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
							        <div class="col s12 m4">
									   <div class="m-1 c-align">
										<a href="add-contract-form"
											class="btn waves-effect waves-light bg-s t-c"> <strong><i
												class="fa fa-plus-circle"></i> Add Contract</strong></a>
									   </div>
									</div>
							    </c:when>
							    <c:otherwise>
							        <div class="col s12 m4">
										<div class="m-1 c-align">
										
										</div>
									</div>
							    </c:otherwise>
							</c:choose>
							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportContract();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export Data</strong></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> --%>

		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Contract</h6> -->
								<h6 class="mob-mar">Contract</h6>	
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    								<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD' || sessionScope.USER_ROLE_NAME eq 'Regular User'}">
    									<a href="add-contract-form"
											class="btn waves-effect waves-light bg-s t-c"> <strong><i
												class="fa fa-plus-circle"></i> Add</strong></a>
									</c:if>
										<a href="javascript:void(0);" onclick="exportContract();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export </strong></a>
    								</div>
    							</div>	
							</div>
						</span>
						<div class="row no-mar">
							    
									   <div class="row clearfix">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<c:if test="${not empty success }">
													<div class="center-align m-1 close-message">${success}</div>
												</c:if>
												<c:if test="${not empty error }">
													<div class="center-align m-1 close-message">${error}</div>
												</c:if>
											</div>
										</div>
							<!--   <div class="col m1 hide-on-small-only"></div> -->
							<div class="col m12 l10 s12">
								<!--    <div class="row" style="margin-bottom: 0;">
                                <div class="col m1 hide-on-small-only"></div> -->
								<div class="col s6 m4 l2 input-field" style="display: none;">
									<p class="searchable_label">Project</p>
									<select id="project_id_fk" name="project_id_fk"
										onchange="addInQueProject(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
										<%--   <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id_fk }" >${obj.work_id_fk}<c:if test="${not empty obj.work_name}"> - </c:if> ${obj.work_name }</option>
                                                </c:forEach>  --%>
									</select>
								</div>
								<div class="col s6 m4 l2 input-field">
									<p class="searchable_label">Work</p>
									<select id="work_id_fk" name="work_id_fk"
										onchange="addInQueWork(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
										<%--   <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id_fk }" >${obj.work_id_fk}<c:if test="${not empty obj.work_name}"> - </c:if> ${obj.work_name }</option>
                                                </c:forEach>  --%>
									</select> 
								</div>
								<%-- <div class="col s12 m2 input-field">
									<p class="searchable_label">Department</p>
									<select id="department_fk" name="department_fk"
										onchange="getContractList();" class="searchable">
										<option value="">Select</option>
										 <c:forEach var="obj" items="${departmentList }">
                                                    <option name="${obj.work_id_fk }" value="${obj.department_fk }" >${obj.department_fk}</option>
                                                </c:forEach> 
									</select>
								</div> --%>
								
								<div class="col s6 m4 l2 input-field">
									<p class="searchable_label">HOD</p>
									<select id="designation" name="designation"
										onchange="addInQueHOD(this.value);getContractList();" class="searchable">
										<option value="">Select</option>

									</select>
								</div>
								<div class="col s6 m4 l2 input-field">
									<p class="searchable_label">Dy HOD</p>
									<select id="dy_hod_designation" name="dy_hod_designation"
										onchange="addInQueDYHodDesignation(this.value);getContractList();" class="searchable">
										<option value="">Select</option>

									</select>
								</div>
								<div class="col s6 m4 l2 input-field">
									<p class="searchable_label">Contractor</p>
									<select id="contractor_id_fk" name="contractor_id_fk"
										onchange="addInQueContractor(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
										<%--  <c:forEach var="obj" items="${contractorsList }">
                                                    <option value="${obj.contractor_id_fk }" >${obj.contractor_id_fk}<c:if test="${not empty obj.contractor_name}"> - </c:if> ${obj.contractor_name }</option>
                                                </c:forEach>  --%>
									</select>
								</div>
								<div class="col s6 m4 l2 input-field">
									<p class="searchable_label">Contract Status</p>
									<select id="contract_status" name="contract_status"
										onchange="addInQueContractStatus(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
									</select>
								</div>
								<div class="col s6 m4 l2 input-field">
									<p class="searchable_label">Status of Work</p>
									<select id="contract_status_fk" name="contract_status_fk"
										onchange="addInQueStatus(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
									</select>
								</div>
							</div>
							<div class="col s12 m12 l2 center-align">  
								<button class="btn bg-m waves-effect waves-light t-c"
									style="margin-top: 12px;" onclick="clearFilter();">Clear
									Filters</button>
							</div>
						</div>
						<!--  <div class="col m1 hide-on-small-only"></div> -->
					</div>

					<div class="row">
						<div class="col m12 s12">

							<table id="datatable-contract" class="mdl-data-table">
								<thead>
									<tr>
										<th class="no-sort fw-250">Work &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
										<th class="no-sort">Contract ID</th>
										<th class="no-sort fw-250">Contract Name</th>
										<th class="no-sort">Contractor Name</th>
										<th class="no-sort">Department</th>
										<th class="no-sort fw-150">HOD</th>
										<th class="no-sort fw-150">Dy HOD</th>
										<th>Last Update</th>
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
 	
	<form action="<%=request.getContextPath()%>/get-contract" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="contract_id" id="contract_id"/>
    </form>
    <form action="<%=request.getContextPath() %>/export-contract" name="exportContractForm" id="exportContractForm" target="_blank" method="post">	
    
        <input type="hidden" name="dy_hod_designation" id="exportDy_hod_designation" />
        <input type="hidden" name="designation" id="exportDesignation" />
        <input type="hidden" name="contractor_id_fk" id="exportContractor_id_fk" />
        <input type="hidden" name="contract_status_fk" id="exportContract_status_fk" />
        <input type="hidden" name="contract_status" id="exportContract_status" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
        <input type="hidden" name="searchStr" id="exportsearchStr" />
        
	</form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	
    <script>
    
    var filtersMap = new Object();
    var pageNo = window.localStorage.getItem("contractPageNo");
    $(document).ready(function () {
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
           
           var filters = window.localStorage.getItem("contractFilters");
         
           if($.trim(filters) != '' && $.trim(filters) != null){
       	  var temp = filters.split('^'); 
       	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
		        		  getProjectFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'designation'){
		        		  getDesignationFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'dy_hod_designation'){
		        		  getDyHODDesignationFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contractor_id_fk'){
		        		  getContractorsFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_status'){
		        		  getContractStatusFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_status_fk'){
		        		  getStatusFilterList(temp2[1]);
		        	  }
	        	  }
	          }
           } 
    	$('.close-message').delay(3000).fadeOut('slow');
    	
    	getContractList();
    	
    });
    
    
    function clearFilter(){
    	$("#contractor_id_fk").val("");
    	$("#work_id_fk").val("");
    	$("#project_id_fk").val("");
    	$("#designation").val("");
    	$("#dy_hod_designation").val("");
    	$("#contract_status_fk").val("");
    	$("#contract_status").val("");
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("contractFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/contract";
    	
    	var table = $('#datatable-contract').DataTable();
    	table.draw( true );
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
    function addInQueHOD(designation){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('designation')) delete filtersMap[key];
   		});
    	if($.trim(designation) != ''){
   	    	filtersMap["designation"] = designation;
    	}
    }
    function addInQueDYHodDesignation(dy_hod_designation){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('dy_hod_designation')) delete filtersMap[key];
   		});
    	if($.trim(dy_hod_designation) != ''){
   	    	filtersMap["dy_hod_designation"] = dy_hod_designation;
    	}
    }
    function addInQueContractor(contractor_id_fk){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('contractor_id_fk')) delete filtersMap[key];
   		});
    	if($.trim(contractor_id_fk) != ''){
   	    	filtersMap["contractor_id_fk"] = contractor_id_fk;
    	}
    }
    function addInQueContractStatus(contract_status){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('contract_status')) delete filtersMap[key];
   		});
    	if($.trim(contract_status) != ''){
   	    	filtersMap["contract_status"] = contract_status;
    	}
    }
    function addInQueStatus(contract_status_fk){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('contract_status_fk')) delete filtersMap[key];
   		});
    	if($.trim(contract_status_fk) != ''){
   	    	filtersMap["contract_status_fk"] = contract_status_fk;
    	}
    }
    
    
    function getContractList(){
    	$(".page-loader-2").show();
    	getDesignationFilterList('');
    	getDyHODDesignationFilterList('');
    	getContractorsFilterList('');
    	getWorkFilterList('');
    	getProjectFilterList('');
    	getContractStatusFilterList('');
    	getStatusFilterList('');
    	
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
    	var contract_status = $("#contract_status").val();
    	var contract_status_fk = $("#contract_status_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("contractFilters", filters);
		});
     	table = $('#datatable-contract').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-contract').DataTable({
			"bStateSave": true,  
     		fixedHeader: true,
           
         	//Default: Page display length
				"iDisplayLength" : 10,
				"iData" : {
					"start" : 52
				},"iDisplayStart" : 0,
				"drawCallback" : function() {
					var info = table.page.info();
					window.localStorage.setItem("contractPageNo", info.page);
				},
            columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric'
                },
                { targets:[3,4,5,6,7], className: 'hideCOl'},
                { targets:[1], className: 'cw-m'},
                { orderable: false, targets: [0,1,2,3,4,5,6,7,8] }
            ],
            // "ScrollX": true,
            //"scrollCollapse": true,
            "sScrollX": "100%",
            "sScrollXInner": "100%",
            "bScrollCollapse": true,
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
						}), self = this.api(), $searchButton = $('<i class="fa fa-search" title="Go" >')
					.click(function() {
						self.search(input.val()).draw();
					}), 
					$clearButton = $('<i class="fa fa-close" title="Reset">')
					.click(function() {
						input.val('');
						$searchButton.click();
					})
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton); 					
				}
        }).rows().remove().draw();
		
		
		table.state.clear();		
	 
	 	var myParams = {contractor_id_fk : contractor_id_fk, project_id_fk : project_id_fk, work_id_fk : work_id_fk,designation : designation, dy_hod_designation : dy_hod_designation,contract_status: contract_status,contract_status_fk:contract_status_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getContracts",type:"POST",data:myParams,success : function(data){    				
				if(data != null && data != '' && data.length > 0){    					
	         		$.each(data,function(key,val){
	         			var contract_id = "'"+val.contract_id+"'";
	                    var actions = '<a href="javascript:void(0);"  onclick="getContract('+contract_id+');" class="btn waves-effect waves-light bg-m t-c" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
	                   	var rowArray = [];    	                 
	                   	
	                	var workName = '';
                        if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                        
	                   	rowArray.push($.trim(val.work_id_fk) + workName);
	                   	rowArray.push($.trim(val.contract_id));
	                   	rowArray.push($.trim(val.contract_short_name));
	                   	rowArray.push($.trim(val.contractor_name));
	                   	rowArray.push($.trim(val.department_name));
	                   	rowArray.push($.trim(val.designation));
	                   	rowArray.push($.trim(val.dy_hod_designation));
	                   	rowArray.push($.trim(val.modified_date));
	                   	rowArray.push($.trim(actions));   	                   	
	                   	
	                    table.row.add(rowArray).draw( true );
					});
	         		if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
	                var oTable = $('#datatable-contract').dataTable();
	                oTable.fnPageChange( pageNo );
	         		$(".page-loader-2").hide();
				}else{
					$(".page-loader-2").hide();
				}
				
			},error: function (jqXHR, exception) {
				$(".page-loader-2").hide();
	         	getErrorMessage(jqXHR, exception);
	     }});
    } 

    
    var queue = 1;
    function getContractList1() {
		$(".page-loader-2").show();

		getDesignationFilterList(''); 
    	getDyHODDesignationFilterList('');
    	getContractorsFilterList('');
    	getWorkFilterList('');
    	getProjectFilterList('');
    	getContractStatusFilterList('');
    	getStatusFilterList('');
    	
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
    	var contract_status = $("#contract_status").val();
    	var contract_status_fk = $("#contract_status_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("contractFilters", filters);
		});

    	var rowLen = 0;
		var myParams = "designation=" + designation + "&dy_hod_designation=" + dy_hod_designation+ "&contractor_id_fk=" + contractor_id_fk
				+ "&contract_status_fk="+ contract_status_fk + "&work_id_fk=" + work_id_fk + "&project_id_fk=" + project_id_fk+ "&contract_status="+ contract_status;

		/***************************************************************************************************/

		$("#datatable-contract")
				.DataTable(
						{
							"bSort": false,
							"order": [],
							"bProcessing" : true,
							"bServerSide" : true,
							//"sort" : "position",
							//bStateSave variable you can use to save state on client cookies: set value "true" 
							
					        stateSave: true,
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
											'<i class="fa fa-search" title="Go">')
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
									$('.dataTables_filter').append(
											'<div class="right-btns"></div>');
									$('.dataTables_filter div').append(
											$searchButton, $clearButton);
								rowLen = $('#datatable-contract tbody tr:visible').length
								if(rowLen <= 1 &&  queue == 1){									
									$('#datatable-contract').dataTable().api().draw(); 
									getContractList();
									queue++;
							    } 
								/* var input = $('.dataTables_filter input').unbind(),
								self = this.api(),
								$searchButton = $('<i class="fa fa-search">')
								           //.text('Go')
								           .click(function() {			   	                    	 
								              self.search(input.val()).draw();
								           })			   	        
								  $('.dataTables_filter label').append($searchButton); */
							},
							columnDefs : [ {
								"targets" : 'no-sort',
								"orderable" : false,
							},
							 { targets:[0,3,4,5,6], className: 'hideCOl'},
			                 { targets: [1], className: 'fw-1 no-wrap'},
			                 { targets: [2], className: 'fw-2'  }                
			                ],
							"sScrollX" : "100%",
							"sScrollXInner" : "100%",
							"bScrollCollapse" : true,
							"language" : {
								"info" : "_START_ - _END_ of _TOTAL_",
								paginate : {
									next : '<i class="fa fa-angle-right"></i>', 
									previous : '<i class="fa fa-angle-left"></i>'  
								}
							},
							"bDestroy" : true,
							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-contracts?"+myParams,
		        "aoColumns": [
		            { "mData": function(data,type,row){
		            	var work_short_name = '';
                        if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
                     	if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk +work_short_name; }
        			}},   				            
		            { "mData": function(data,type,row){
		            	if($.trim(data.contract_id) == ''){ return '-'; }else{ return data.contract_id; }
		            } },
		         	{ "mData": function(data,type,row){
		            	if($.trim(data.contract_short_name) == ''){ return '-'; }else{ return data.contract_short_name; }
		            } },
		            { "mData": function(data,type,row){
		            	if($.trim(data.contractor_name) == ''){ return '-'; }else{ return data.contractor_name; }
		            } },
		         	{ "mData": function(data,type,row){
		            	if($.trim(data.department_name) == ''){ return '-'; }else{ return data.department_name; }
		            } },
		            { "mData": function(data,type,row){
		            	if($.trim(data.designation) == ''){ return '-'; }else{ return data.designation; }
		            } },
		            { "mData": function(data,type,row){
		            	if($.trim(data.dy_hod_designation) == ''){ return '-'; }else{ return data.dy_hod_designation; }
		            } },
		         	{ "mData": function(data,type,row){
		         		var contract_id = "'"+data.contract_id+"'";
	                    var actions = '<a href="javascript:void(0);"  onclick="getContract('+contract_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
		            	return actions;
		            } }
		            
		        ]
		    });
	    
	  $(".page-loader-2").hide();  		     
	
 }
    

    function getDyHODDesignationFilterList(dy_designation){
    	$(".page-loader").show();
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
    	var contract_status = $("#contract_status").val();
        if ($.trim(dy_hod_designation) == "") {
        	$("#dy_hod_designation option:not(:first)").remove();
    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getDyHODDesignationsFilterListInContract",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var selectedFlag = (dy_designation == val.dy_hod_user_id)?'selected':'';
                        	/*if($.trim(selectedFlag) != ''){
                            	if(selectedFlag == ''){
                            		var selectedFlag = (dy_designation == val.dy_hod_user_id)?'selected':'';
                        		}
                         	}else{
                         		var user_id  = '${sessionScope.USER_ID}';
                            	var selectedFlag = (user_id == val.dy_hod_user_id)?'selected':'';
                         	}*/
                        	$("#dy_hod_designation").append('<option value="' + val.dy_hod_user_id + '" '+selectedFlag+'>' + $.trim(val.dy_hod_designation) +'</option>');
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

    function getDesignationFilterList(hod_designation){
    	$(".page-loader").show();
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
    	var contract_status = $("#contract_status").val();
        if ($.trim(designation) == "") {
        	$("#designation option:not(:first)").remove();
    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getDesignationsFilterListInContract",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var selectedFlag = (hod_designation == val.hod_user_id)?'selected':'';
                        	/*if($.trim(selectedFlag) != ''){
                        		if(selectedFlag == ''){
                            		var selectedFlag = (hod_designation == val.hod_user_id)?'selected':'';
                        		}
                         	}else{
                         		var user_id  = '${sessionScope.USER_ID}';
                        		var selectedFlag = (user_id == val.hod_user_id)?'selected':'';
                         	}*/
                        	$("#designation").append('<option value="' + val.hod_user_id + '" '+selectedFlag+'>' + $.trim(val.designation) +'</option>');
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
   
    function getContractorsFilterList(contractor){
    	$(".page-loader").show();
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
    	var contract_status = $("#contract_status").val();
        if ($.trim(contractor_id_fk) == "") {
        	$("#contractor_id_fk option:not(:first)").remove();
    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getContractorsFilterListInContract",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
	                           var contractor_name = '';
	                           if ($.trim(val.contractor_name) != '') { contractor_name = ' - ' + $.trim(val.contractor_name) }
	                           var selectedFlag = (contractor == val.contractor_id_fk)?'selected':'';
	                           $("#contractor_id_fk").append('<option value="' + val.contractor_id_fk + '"'+selectedFlag+'>' + $.trim(val.contractor_id_fk)  + contractor_name +'</option>');
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
   
	 function getDeptFilterList(){
	    	$(".page-loader").show();
	    	var contractor_id_fk = $("#contractor_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
	    	var designation = $("#designation").val();
	    	var dy_hod_designation = $("#dy_hod_designation").val();
	    	var contract_status = $("#contract_status").val();
	        if ($.trim(department_fk) == "") {
	        	$("#department_fk option:not(:first)").remove();
	    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, department_fk : department_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getDepartmentsFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
		                           $("#department_fk").append('<option value="' + val.department_fk + '">' + $.trim(val.department_name)   +'</option>');
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
	 
	 function getContractStatusFilterList(contractStatus){
		 	$(".page-loader").show();
		 	var contractor_id_fk = $("#contractor_id_fk").val();
	    	var contract_status_fk = $("#contract_status_fk").val();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
	    	var designation = $("#designation").val();
	    	var dy_hod_designation = $("#dy_hod_designation").val();
	    	var contract_status = $("#contract_status").val();
		    if ($.trim(contract_status) == "") {
		    	$("#contract_status option:not(:first)").remove();
			 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getContractStatusFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (contractStatus == val.contract_status)?'selected':'';
	                        	 $("#contract_status").append('<option value="' + val.contract_status + '"'+selectedFlag+'>' + $.trim(val.contract_status)+'</option>');
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
	 
	 function getStatusFilterList(status){
		 	$(".page-loader").show();
		 	var contractor_id_fk = $("#contractor_id_fk").val();
	    	var contract_status_fk = $("#contract_status_fk").val();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
	    	var designation = $("#designation").val();
	    	var dy_hod_designation = $("#dy_hod_designation").val();
	    	var contract_status = $("#contract_status").val();
		    if ($.trim(contract_status_fk) == "") {
		    	$("#contract_status_fk option:not(:first)").remove();
			 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (status == val.contract_status_fk)?'selected':'';
	                        	 $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '"'+selectedFlag+'>' + $.trim(val.contract_status_fk)+'</option>');
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
	 
	 function getWorkFilterList(work){
	 	$(".page-loader").show();
	 	var contractor_id_fk = $("#contractor_id_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
    	var contract_status = $("#contract_status").val();
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
		 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInContract",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk)   + workShortName +'</option>');
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
	 
	 function getProjectFilterList(project){
		 	$(".page-loader").show();
		 	var contractor_id_fk = $("#contractor_id_fk").val();
	    	var contract_status_fk = $("#contract_status_fk").val();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
	    	var designation = $("#designation").val();
	    	var dy_hod_designation = $("#dy_hod_designation").val();
	    	var contract_status = $("#contract_status").val();
		    if ($.trim(project_id_fk) == "") {
		    	$("#project_id_fk option:not(:first)").remove();
			 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var workShortName = '';
	                             if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
	                             var selectedFlag = (project == val.project_id_fk)?'selected':'';
		                         $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>' + $.trim(val.project_id_fk)   + projectName +'</option>');
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

    function getContract(contract_id) {
		$("#contract_id").val(contract_id);
		$("#getForm").submit();
	}
    
    function exportContract(){
    	 var dy_hod_designation = $("#dy_hod_designation").val();
    	 var designation = $("#designation").val();
     	 var contractor_id_fk = $("#contractor_id_fk").val();
     	 var contract_status_fk = $("#contract_status_fk").val();
     	 var work_id_fk = $("#work_id_fk").val();
     	 var project_id_fk = $("#project_id_fk").val();
     	 var contract_status = $("#contract_status").val();
     	 var searchStrValue = $('[type=search]').val();
     	 
     	
     	 
     	 $("#exportProject_id_fk").val(project_id_fk);
     	 $("#exportContractor_id_fk").val(contractor_id_fk);
     	 $("#exportContract_status_fk").val(contract_status_fk);
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportDy_hod_designation").val(dy_hod_designation);
     	 $("#exportDesignation").val(designation);
     	 $("#exportContract_status").val(contract_status);
     	 $("#exportsearchStr").val(searchStrValue);
     	 $("#exportContractForm").submit();
  	}
    
    </script>

</body>

</html>