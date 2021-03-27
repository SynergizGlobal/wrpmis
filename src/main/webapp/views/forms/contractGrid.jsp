<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">

    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">

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

							<div class="col s12 m4">
								<div class="m-1 c-align">
									<a href="add-contract-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Contract</strong></a>
								</div>
							</div>

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
		</div>

		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6>Update Contract</h6>
							</div>
						</span>
						<div class="row no-mar" style="margin-bottom: 0;">
							<!--   <div class="col m1 hide-on-small-only"></div> -->
							<div class="col m11 s12 ">
								<!--    <div class="row" style="margin-bottom: 0;">
                                <div class="col m1 hide-on-small-only"></div> -->
								<div class="col s12 m2 input-field">
									<p class="searchable_label">Project</p>
									<select id="project_id_fk" name="project_id_fk"
										onchange="addInQueProject(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
										<%--   <c:forEach var="obj" items="${worksList }">
                                                    <option value="${obj.work_id_fk }" >${obj.work_id_fk}<c:if test="${not empty obj.work_name}"> - </c:if> ${obj.work_name }</option>
                                                </c:forEach>  --%>
									</select>
								</div>
								<div class="col s12 m2 input-field">
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
								
								<div class="col s12 m2 input-field">
									<p class="searchable_label">HOD</p>
									<select id="designation" name="designation"
										onchange="addInQueHOD(this.value);getContractList();" class="searchable">
										<option value="">Select</option>

									</select>
								</div>
								<div class="col s12 m2 input-field">
									<p class="searchable_label">Dy HOD</p>
									<select id="dy_hod_designation" name="dy_hod_designation"
										onchange="addInQueDYHodDesignation(this.value);getContractList();" class="searchable">
										<option value="">Select</option>

									</select>
								</div>
								<div class="col s12 m2 input-field">
									<p class="searchable_label">Contractor</p>
									<select id="contractor_id_fk" name="contractor_id_fk"
										onchange="addInQueContractor(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
										<%--  <c:forEach var="obj" items="${contractorsList }">
                                                    <option value="${obj.contractor_id_fk }" >${obj.contractor_id_fk}<c:if test="${not empty obj.contractor_name}"> - </c:if> ${obj.contractor_name }</option>
                                                </c:forEach>  --%>
									</select>
								</div>
								<div class="col s12 m2 input-field">
									<p class="searchable_label">Status</p>
									<select id="contract_status_fk" name="contract_status_fk"
										onchange="addInQueStatus(this.value);getContractList();" class="searchable">
										<option value="">Select</option>
									</select>
								</div>
							</div>
							<div class="col s12 m1">
								<button class="btn bg-m waves-effect waves-light t-c"
									style="margin-top: 12px; width: 100%" onclick="clearFilter();">Clear
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

										<th class="fw-150">Work</th>
										<th>Contract ID</th>
										<th class="fw-250">Contract Name</th>
										<th>Contractor Name</th>
										<th>Department</th>
										<th>HOD</th>
										<th>Dy HOD</th>
										<th class="no-sort">Action</th>
									</tr>
								</thead>
								<tbody>
									<%--  <c:forEach var="obj" items="${contractList }">
                                        <tr>

                                            <td>${ obj.work_name }</td>
                                            <td >${ obj.contract_id }</td>
                                            <td >${ obj.contract_name }</td>
                                            <td>${ obj.contractor_name }</td>
                                            <td>${ obj.department_fk }</td>
                                            <td>${ obj.hod_user_id_fk }</td>
                                            <td>${ obj.dy_hod_user_id_fk }</td>
                                            <td class="last-column"> <a href="contract.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>

                                        </tr>
                                        </c:forEach>  --%>
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
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
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
		        	  }else if($.trim(temp2[0]) == 'contract_status_fk'){
		        		  getStatusFilterList(temp2[1]);
		        	  }
	        	  }
	          }
           }
           
       	var table = $('#datatable-contract').DataTable({
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
    	
    	getContractList();
    });
    
    
    function clearFilter(){
    	$("#contractor_id_fk").val("");
    	$("#work_id_fk").val("");
    	$("#project_id_fk").val("");
    	$("#designation").val("");
    	$("#dy_hod_designation").val("");
    	$("#contract_status_fk").val("");
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("contractFilters",'');
    	getContractList();
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
    function addInQueStatus(contract_status_fk){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('contract_status_fk')) delete filtersMap[key];
   		});
    	if($.trim(contract_status_fk) != ''){
   	    	filtersMap["contract_status_fk"] = contract_status_fk;
    	}
    }
    
    function getContractList() {
		$(".page-loader-2").show();

		getDesignationFilterList('');
    	getDyHODDesignationFilterList('');
    	getContractorsFilterList('');
    	getWorkFilterList('');
    	getProjectFilterList('');
    	getStatusFilterList('');
    	
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
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

		var myParams = "contractor_id_fk=" + contractor_id_fk + "&work_id_fk="
				+ work_id_fk + "&project_id_fk=" + project_id_fk
				+ "&dy_hod_designation=" + dy_hod_designation+ "&contract_status_fk=" + contract_status_fk;

		/***************************************************************************************************/

		$("#datatable-contract")
				.DataTable(
						{
							"bProcessing" : true,
							"bServerSide" : true,
							"sort" : "position",
							//bStateSave variable you can use to save state on client cookies: set value "true" 
							"bStateSave" : false,
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
										.unbind(), self = this.api(), $searchButton = $(
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
							} ],
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
        			} },   				            
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
	                    var actions = '<a href="javascript:void(0);"  onclick="getContract('+contract_id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
		            	return actions;
		            } }
		            
		        ]
		    });
	    
	  $(".page-loader-2").hide();  		     
  	
 }
    
    function getContractList1(){
    	$(".page-loader-2").show();
    	getDesignationFilterList('');
    	getDyHODDesignationFilterList('');
    	getContractorsFilterList('');
    	getWorkFilterList('');
    	getProjectFilterList('');
    	getStatusFilterList('');
    	
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
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
            //"scrollCollapse": true,
            "sScrollX": "100%",
            "sScrollXInner": "100%",
            "bScrollCollapse": true,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        }).rows().remove().draw();
		
		
		table.state.clear();		
	 
	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getContracts",
				type:"POST",
				data:myParams, cache: false,async:false,
				success : function(data){
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

    function getDyHODDesignationFilterList(dy_designation){
    	$(".page-loader").show();
    	var contractor_id_fk = $("#contractor_id_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var project_id_fk = $("#project_id_fk").val();
    	var designation = $("#designation").val();
    	var dy_hod_designation = $("#dy_hod_designation").val();
        if ($.trim(dy_hod_designation) == "") {
        	$("#dy_hod_designation option:not(:first)").remove();
    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getDyHODDesignationsFilterListInContract",
                data: myParams, cache: false,async: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var selectedFlag = (dy_designation == val.dy_hod_designation)?'selected':'';
                        	if($.trim(selectedFlag) != ''){
                        		var designation  = '${sessionScope.USER_DESIGNATION}';
                            	var selectedFlag = (designation == val.dy_hod_designation)?'selected':'';
                         	}
                        	$("#dy_hod_designation").append('<option value="' + val.dy_hod_designation + '" '+selectedFlag+'>' + $.trim(val.dy_hod_designation) +'</option>');
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
        if ($.trim(designation) == "") {
        	$("#designation option:not(:first)").remove();
    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getDesignationsFilterListInContract",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var selectedFlag = (hod_designation == val.designation)?'selected':'';
                        	if($.trim(selectedFlag) != ''){
                        		var designation  = '${sessionScope.USER_DESIGNATION}';
                        		var selectedFlag = (designation == val.designation)?'selected':'';
                         	}
                        	$("#designation").append('<option value="' + val.designation + '" '+selectedFlag+'>' + $.trim(val.designation) +'</option>');
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
        if ($.trim(contractor_id_fk) == "") {
        	$("#contractor_id_fk option:not(:first)").remove();
    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
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
	        if ($.trim(department_fk) == "") {
	        	$("#department_fk option:not(:first)").remove();
	    	 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, department_fk : department_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
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
	 
	 function getStatusFilterList(status){
		 	$(".page-loader").show();
		 	var contractor_id_fk = $("#contractor_id_fk").val();
	    	var contract_status_fk = $("#contract_status_fk").val();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var project_id_fk = $("#project_id_fk").val();
	    	var designation = $("#designation").val();
	    	var dy_hod_designation = $("#dy_hod_designation").val();
		    if ($.trim(contract_status_fk) == "") {
		    	$("#contract_status_fk option:not(:first)").remove();
			 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
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
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
		 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
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
		    if ($.trim(project_id_fk) == "") {
		    	$("#project_id_fk option:not(:first)").remove();
			 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk};
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
     	 
     	 $("#exportProject_id_fk").val(project_id_fk);
     	 $("#exportContractor_id_fk").val(contractor_id_fk);
     	 $("#exportContract_status_fk").val(contract_status_fk);
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportDy_hod_designation").val(dy_hod_designation);
     	 $("#exportDesignation").val(designation);
     	
     	 $("#exportContractForm").submit();
  	}
    
    </script>

</body>

</html>