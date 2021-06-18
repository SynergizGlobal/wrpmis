<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Gathering - Update Forms - PMIS</title>
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
        td,th{
        	box-sizing: content-box !important;
        }
        .input-field .searchable_label {
            font-size: 0.85rem;
        }
        .fw-350{
    	 	width:350px !important;
    	 	max-width:350px;
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
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Data Gathering</h6>
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
							<div class="col s12 m4">
								<!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div> -->
							</div>
							<div class="col s12 m4">
								<div class="m-1 c-align">
									<a href="<%=request.getContextPath()%>/add-data-gathering-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Data Gathering</strong></a>
								</div>
							</div>
							<div class="col s12 m4 r-align hide-on-med-and-down">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportDataGatherings();"
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
								<h6 class="hide-on-med-and-down">Update Data Gathering</h6>
								<h6 class="hide-on-large-only">Data Gathering</h6>	
							</div>
						</span>
						<div class="row no-mar" >
							<div class="col s12 hide-on-large-only mb-md-2 center-align">
    							<a href="<%=request.getContextPath()%>/add-data-gathering-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Data Gathering</strong></a>
							</div>	
							<div class="col s6 m4 l2 input-field offset-l2">
								<p class="searchable_label">Project</p>
								<select id="project_id_fk" name="project_id_fk"
									class="searchable" onchange="addInQueProject(this.value);getDataGatheringList();">
									<option value="">Select</option>
								</select>
							</div>
							<div class="col s6 m4 l2 input-field">
								<p class="searchable_label">Work</p>
								<select id="work_id_fk" name="work_id_fk" class="searchable"
									onchange="addInQueWork(this.value);getDataGatheringList();">
									<option value="">Select</option>
								</select>
							</div>
							<div class="col s6 m4 l2 input-field">
								<p class="searchable_label">Contract</p>
								<select id="contract_id_fk" name="contract_id_fk"
									class="searchable" onchange="addInQueContract(this.value);getDataGatheringList();">
									<option value="">Select</option>
								</select>
							</div>
							<!-- <div class="col s12 m2 input-field">
                                <p class="searchable_label">Priority</p>
                                <select id="project_priority_fk" name="project_priority_fk" class="searchable" onchange="getDataGatheringList();">
                                    <option value=""  >Select</option>                                    
                                </select>
                            </div> -->
							<div class="col s6 m4 l2 input-field">
								<p class="searchable_label">Status</p>
								<select id="status_fk" name="status_fk" class="searchable"
									onchange="addInQueStatus(this.value);getDataGatheringList();">
									<option value="">Select</option>
								</select>
							</div>
							<div class="col s12 m4 l2 center-align">
								<button
									class="btn bg-m waves-effect waves-light t-c clear-filters"
									style="margin-top: 12px; width: 100%;" onclick="clearFilters()">Clear
									Filters</button>
							</div>
							<div class="col m1 hide-on-small-only"></div>
						</div>

						<div class="row">
							<div class="col m12 s12">
							 <div  style= "display:none;" id="webView">
								<table id="datatable-data-gathering" class="mdl-data-table">
									<thead>
										<tr>
											<th>Contract</th>
											<th class="fw-350">Description</th>
											<!-- <th> Target Date</th>
                                            <th> Start Date</th> -->
											<th>Finish Date</th>
											<th>Status</th>
											<th class="no-sort">Action</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>

								</table>
							  </div>
							  <div  style= "display:none;" id="mobView">
							  		<table id="datatable-data-gathering_mobile" class="mdl-data-table">
									<thead>
										<tr>
											<th>Contract</th>
											<th>Status</th>
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
	</div>
	<!-- Page Loader starts-->
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
<!-- Page Loader ends-->


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
     <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-data-gathering" name="exportDataGatheringForm" id="exportDataGatheringForm" target="_blank" method="post">	
        
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
         <input type="hidden" name="status_fk" id="exportStatus_fk" />
	</form>

     <script>
     
     var filtersMap = new Object();
     
     $(document).ready(function () {
    	 $('select:not(.searchable)').formSelect();
	     $('.searchable').select2();
	     
	     var filters = window.localStorage.getItem("dataGatheringFilters");
         
         if($.trim(filters) != '' && $.trim(filters) != null){
     	  var temp = filters.split('^'); 
     	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
		        		  getProjectFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'status_fk'){
		        		  getStatusFilterList(temp2[1]);
		        	  }
	        	  }
	          }
         }
     
	
		$('.close-message').delay(3000).fadeOut('slow');
		getDataGatheringList();
		if(window.matchMedia("(max-width: 769px)").matches){
		        $('#mobView').css({'display':'block'});
		      	
		 } else{
		    	$('#webView').css({'display':'block'});
		 }
        });
        
     function clearFilters() {
         $('#status_fk').val("");
         $('#project_id_fk').val("");
         $('#work_id_fk').val("");
         $('#contract_id_fk').val("");
         $('.searchable').select2();
         window.localStorage.setItem("dataGatheringFilters",'');
         getDataGatheringList();
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
     function addInQueStatus(status_fk){
     	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('status_fk')) delete filtersMap[key];
	   		});
     	if($.trim(status_fk) != ''){
    	    	filtersMap["status_fk"] = status_fk;
     	}
     }
     
     function getDataGatheringList() {
 		$(".page-loader-2").show();

    	getProjectFilterList('');
      	getWorkFilterList('');
      	getContractFilterList('');
      	getStatusFilterList('');
      	
     	var status_fk = $("#status_fk").val();
     	var project_id_fk = $("#project_id_fk").val();
     	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
     	/*var project_priority_fk = $("#project_priority_fk").val();
      	getProjectPriorityFilterList(); */

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("dataGatheringFilters", filters);
			});
    	if(window.matchMedia("(max-width: 769px)").matches){
     	table = $('#datatable-data-gathering_mobile').DataTable();
 		 
 		table.destroy();

 		$.fn.dataTable.moment('DD-MMM-YYYY');

 		var myParams = "status_fk=" + status_fk + "&project_id_fk="
 				+ project_id_fk + "&work_id_fk=" + work_id_fk
 				+ "&contract_id_fk=" + contract_id_fk;

 		/***************************************************************************************************/

 		$("#datatable-data-gathering_mobile")
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
 							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-data-gathering-list?"+myParams,
 		        "aoColumns": [
 		            { "mData": function(data,type,row){
 		            	var contract_short_name = '';
                         if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) }    	
                      	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk +contract_short_name; }
         			} },   		
         			 { "mData": function(data,type,row){
  		            	if($.trim(data.status_fk) == ''){ return '-'; }else{ return data.status_fk; }
  		            } },
 		         	{ "mData": function(data,type,row){
 		         		var id = "'"+data.id+"'";
 	                    var actions = '<a href="javascript:void(0);"  onclick="getDataGathering('+id+');" class="btn mobile-btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
 		            	return actions;
 		            } }
 		            
 		        ]
 		    });
 	    
    }else{
    	table = $('#datatable-data-gathering').DataTable();
		 
 		table.destroy();

 		$.fn.dataTable.moment('DD-MMM-YYYY');

 		var myParams = "status_fk=" + status_fk + "&project_id_fk="
 				+ project_id_fk + "&work_id_fk=" + work_id_fk
 				+ "&contract_id_fk=" + contract_id_fk;

 		/***************************************************************************************************/

 		$("#datatable-data-gathering")
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
 							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-data-gathering-list?"+myParams,
 		        "aoColumns": [
 		            { "mData": function(data,type,row){
 		            	var contract_short_name = '';
                         if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) }    	
                      	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk +contract_short_name; }
         			} },   				            
 		            { "mData": function(data,type,row){
 		            	if($.trim(data.description) == ''){ return '-'; }else{ return data.description; }
 		            } },
 		         	{ "mData": function(data,type,row){
 		            	if($.trim(data.finish_date) == ''){ return '-'; }else{ return data.finish_date; }
 		            } },
 		            { "mData": function(data,type,row){
 		            	if($.trim(data.status_fk) == ''){ return '-'; }else{ return data.status_fk; }
 		            } },
 		         	{ "mData": function(data,type,row){
 		         		var id = "'"+data.id+"'";
 	                    var actions = '<a href="javascript:void(0);"  onclick="getDataGathering('+id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
 		            	return actions;
 		            } }
 		            
 		        ]
 		    });
    	
    }
   	$(".page-loader-2").hide();  		     

  }
     
     
     function getDataGatheringList1(){
     	$(".page-loader-2").show();
     	
    	getProjectFilterList('');
      	getWorkFilterList('');
      	getContractFilterList('');
      	getStatusFilterList('');
      	
     	var status_fk = $("#status_fk").val();
     	var project_id_fk = $("#project_id_fk").val();
     	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
     	/*var project_priority_fk = $("#project_priority_fk").val();
      	getProjectPriorityFilterList(); */

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("dataGatheringFilters", filters);
			});
      
     	table = $('#datatable-data-gathering').DataTable();
 		 
 		table.destroy();
 		
 		$.fn.dataTable.moment('DD-MMM-YYYY');
 		table = $('#datatable-data-gathering').DataTable({
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
                 { orderable: false, 'aTargets': ['nosort'] },
                 { "width": "20px", "targets": [4] },
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
 	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk};
 	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-data-gathering-list",
 	 		type:"POST",
			data:myParams, cache: false,async:false,
			success : function(data){
 			if(data != null && data != '' && data.length > 0){    					
          		$.each(data,function(key,val){
          			var id = "'"+val.id+"'";
                     var actions = '<a href="javascript:void(0);"  onclick="getDataGathering('+id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
                     			  /* +'<a onclick="deleteDataGathering('+id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>' */
                   	 var rowArray = [];    	                 
	
						var contract_short_name = '';
					    if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
					 
					    rowArray.push($.trim(val.contract_id_fk)+contract_short_name);
					    rowArray.push($.trim(val.description));
					 	//rowArray.push($.trim(val.work_short_name));
					 	rowArray.push($.trim(val.finish_date));
					 	rowArray.push($.trim(val.status_fk));
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
     
     function getStatusFilterList(status) {
     	$(".page-loader").show();
     	var status_fk = $("#status_fk").val();
     	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
     	var project_id_fk = $("#project_id_fk").val();
     
         if ($.trim(status_fk) == "") {
         	$("#status_fk option:not(:first)").remove();
     	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk};
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInDataGathering",
                 data: myParams, cache: false,async: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                        	   var selectedFlag = (status == val.status_fk)?'selected':'';
 	                           $("#status_fk").append('<option value="' + val.status_fk + '"'+selectedFlag+'>' + $.trim(val.status_fk)  +'</option>');
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
     
     function getProjectFilterList(project) {
      	$(".page-loader").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();     	
      	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(project_id_fk) == "") {
          	$("#project_id_fk option:not(:first)").remove();
      	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInDataGathering",
                  data: myParams, cache: false,async: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
                        	   var project_name = '';
       					       if ($.trim(val.project_name) != '') { project_name = ' - ' + $.trim(val.project_name) }
       					   	   var selectedFlag = (project == val.project_id_fk)?'selected':'';
  	                           $("#project_id_fk").append('<option value="' + val.project_id_fk + '"'+selectedFlag+'>' + $.trim(val.project_id_fk) +project_name +'</option>');
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
     
     function getWorkFilterList(work) {
      	$(".page-loader").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();     	
      	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(work_id_fk) == "") {
          	$("#work_id_fk option:not(:first)").remove();
      	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk : work_id_fk,contract_id_fk : contract_id_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInDataGathering",
                  data: myParams, cache: false,async: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
                        	   var work_short_name = '';
      					       if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) }
      					       var selectedFlag = (work == val.work_id_fk)?'selected':'';
  	                           $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk)  + work_short_name +'</option>');
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
     function getContractFilterList(contract) {
      	$(".page-loader").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();     	
      	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(contract_id_fk) == "") {
          	$("#contract_id_fk option:not(:first)").remove();
      	 	var myParams = {contract_id_fk : contract_id_fk, status_fk : status_fk,project_id_fk : project_id_fk,work_id_fk : work_id_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInDataGathering",
                  data: myParams, cache: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
                        	  var contract_short_name = '';
     					       if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
     					       var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
  	                           $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '"'+selectedFlag+'>' + $.trim(val.contract_id_fk) +contract_short_name +'</option>');
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
    
     
     function getDataGathering(id){
     	$("#id").val(id);
     	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-data-gathering');
     	$('#getForm').submit();
     }
     function deleteDataGathering(id){
     	$("#id").val(id);
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
             	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-data-gathering');
     	    	$('#getForm').submit();
            }else {
                 swal("Cancelled", "Record is safe :)", "error");
             }
         });
     }
     
     function exportDataGatherings(){
    	 //var project_priority_fk = $("#project_priority_fk").val();
    	 var project_id_fk = $("#project_id_fk").val();
    	 var work_id_fk = $("#work_id_fk").val();
    	 var contract_id_fk = $("#contract_id_fk").val();
    	 var status_fk = $("#status_fk").val();
    	 $("#exportProject_id_fk").val(project_id_fk);
    	$("#exportStatus_fk").val(status_fk);
    	$("#exportWork_id_fk").val(work_id_fk);
    	$("#exportContract_id_fk").val(contract_id_fk);
    	 $("#exportDataGatheringForm").submit();
 	}
     
 </script>


</body>
</html>