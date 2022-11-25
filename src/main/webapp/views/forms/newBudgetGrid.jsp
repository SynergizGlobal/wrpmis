<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Budget - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/budget.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 1024px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 1024px)" href="/pmis/resources/css/mobile-grid-template.css" />
    
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
        @media only screen and (max-width: 1024px){ 
			
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
			.fw-300{width:30vw !important;
        	max-width:30vw; }
			
			}
		.no-sort.sorting_asc:before,
		.no-sort.sorting_asc:after{
		    opacity:0 !important;
		    content:'' !important;
		}
	.m-n1 {
        margin: -2rem auto 0;
    }

    @media only screen and (max-width: 1024px) {
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
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Budget</h6> -->
								<h6 class="mob-mar">New Budget</h6>
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="<%=request.getContextPath()%>/add-new-budget-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
										<a href="javascript:void(0);" onclick="exportBudget();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>
    								</div>
    							</div>								
							</div>
						</span>
						<div class="row no-mar" style="margin-bottom: 0;">
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
							<div class="col m8 s12 offset-m2">
								<div class="row no-mar">
									<div class="col s12 m3 input-field">
										<p class="searchable_label">Project</p>
										<select class="searchable" name="project_id_fk"
											id="project_id_fk" onchange="addInQueProject(this.value);getBudgetList();">
											<option value="" selected>Select</option>

										</select>
									</div>
									<div class="col s12 m3 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getBudgetList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<div class="col s12 m3 input-field">
										<p class="searchable_label">Contract</p>
										<select class="searchable" name="contract_id_fk"
											id="contract_id_fk" onchange="addInQueContract(this.value);getBudgetList();">
											<option value="" selected>Select</option>
										</select>
									</div>
									<div class="col s12 m3 center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="margin-top: 10px; width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
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
												<th class='fw-300'>Project</th>
												<th class='fw-300'>Work</th>
												<th class='fw-300'>Contract</th>
<!-- 												<th>Financial Year</th>
												<th>Budget Estimate</th>
												<th>Budget Grant</th>
												<th>Reivised Estimate</th>
												<th>Reivised Grant</th>
												<th>Final Estimate</th>
												<th>Final Grant</th> -->
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
    	<input type="hidden" name="contract_id_fk" id="contract_id_fk" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-new-budget" name="exportBudgetForm" id="exportBudgetForm" target="_blank" method="post">	
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="contract_id_fk" id="exportcontract_id_fk" />
	</form>

    <script>
    
    var filtersMap = new Object();
    
    $(document).ready(function () {
 	   $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
        
        var filters = window.localStorage.getItem("budgetFilters");
        
        if($.trim(filters) != '' && $.trim(filters) != null){
      	  var temp = filters.split('^'); 
      	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
		        		  getProjectsFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorksFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractsFilterList(temp2[1]);
		        	  }
	        	  }
	          }
          }
  
 	$('.close-message').delay(3000).fadeOut('slow');
 	
 	getBudgetList();
  });
 
    function clearFilter(){
    	$("#project_id_fk").val("");
    	$("#work_id_fk").val("");
    	$("#contract_id_fk").val("");
    	$('.searchable').select2();
    	window.localStorage.setItem("budgetFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/new-budget";
    	//getBudgetList(); 
    	var table = $('#datatable-budget').DataTable();
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
    
    function addInQueContract(contract_id_fk){
      	Object.keys(filtersMap).forEach(function (key) {
	   		if(key.match('contract_id_fk')) delete filtersMap[key];
   	   	});
      	if($.trim(contract_id_fk) != ''){
        	filtersMap["contract_id_fk"] = contract_id_fk;
      	}
    }   
    
    var queue = 1;
    function getBudgetList() {
		$(".page-loader-2").show();

		getWorksFilterList('');
     	getProjectsFilterList('');
     	getContractsFilterList('');
     	
    	var project_id_fk = $("#project_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("budgetFilters", filters);
			});
    	   	table = $('#datatable-budget').DataTable();
    		table.destroy();
			var i = 0;
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		var rowLen = 0;
    		var myParams =  "work_id_fk="
    				+ work_id_fk + "&project_id_fk="+ project_id_fk+ "&contract_id="+ contract_id_fk;

    		/***************************************************************************************************/

    		$("#datatable-budget")
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
    								$('.dataTables_filter').append(
    										'<div class="right-btns"></div>');
    								$('.dataTables_filter div').append(
    										$searchButton, $clearButton);
    								rowLen = $('#datatable-budget tbody tr:visible').length
    								if(rowLen <= 1 &&  queue == 1){									
    									$('#datatable-budget').dataTable().api().draw(); 
    									getBudgetList();
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
    							}
    							,
    							columnDefs : [ {
    								"targets" : 'no-sort',
    								"orderable" : false,
    							},{ targets: [0], className: 'no-sort'  }
    			                       ,{ targets: [1], className: 'td-align-left'  }
    			                ],
    							"sScrollX" : "100%",
    							"sScrollXInner" : "100%",
    							"ordering":false,
    							"bScrollCollapse" : true,
    							"language" : {
    								"info" : "_START_ - _END_ of _TOTAL_",
    								paginate : {
    									next : '<i class="fa fa-angle-right"></i>', 
    									previous : '<i class="fa fa-angle-left"></i>'  
    								}
    							},
    							
    							"bDestroy" : true,
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-new-budget?"+myParams,
    		        "aoColumns": [
    		            { "mData": function(data,type,row){ 
    		            	if($.trim(data.project_name) == ''){ return '-'; }else{ return data.project_name; }
    		            } },
    		            { "mData": function(data,type,row){ 
    		            	if($.trim(data.work_name) == ''){ return '-'; }else{ return data.work_name; }
    		            } },      		        	
      		            { "mData": function(data,type,row){
      		            	var contract_name = '';
                             if ($.trim(data.contract_name) != '') { contract_name = ' - ' + $.trim(data.contract_name) }    	
                             if($.trim(data.contract_id) == ''){ return '-'; }else{ return data.contract_id +contract_name; }
      		            } },
 		            
      		            
/*       		         	{ "mData": function(data,type,row){
                             if($.trim(data.financial_year_fk) == ''){ return '-'; }else{  
                            	 var nc=data.financial_year_fk;
                                 var tc=nc.toString();
                                 var SplitStr=tc.split("-");
                                 var nlcSplit=SplitStr[1];
                             return getMonthName(nlcSplit)+" ,"+SplitStr[0]; }
      		            } },
      		       
    		            { "mData": function(data,type,row){ 
    		            	if($.trim(data.budget_estimate) == ''){ return '-'; }else{ return data.budget_estimate; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.budget_grant) == ''){ return '-'; }else{ return data.budget_grant; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.revised_estimate) == ''){ return '-'; }else{ return data.revised_estimate; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.revised_grant) == ''){ return '-'; }else{ return data.revised_grant; }
    		            }},
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.final_estimate) == ''){ return '-'; }else{ return data.final_estimate; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.final_grant) == ''){ return '-'; }else{ return data.final_grant; }
    		            } }, */ 
    		         	{ "mData": function(data,type,row){
    		         		var budget_id = "'"+data.budget_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick=getBudget('+budget_id+',"'+data.contract_id+'"); class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	
    	
	  $(".page-loader-2").hide();  		     
  	
 }

    
    function getBudgetList1(){
    	$(".page-loader-2").show();
    	
    	getWorksFilterList('');
     	getProjectsFilterList('');
     	getFinancialYearsFilterList('');
     	
    	var project_id_fk = $("#project_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("budgetFilters", filters);
			});
    	
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
	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk};
	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-new-budget",
			type:"POST",
			data:myParams, cache: false,async:false,
			success : function(data){      				
			if(data != null && data != '' && data.length > 0){    					
         		$.each(data,function(key,val){
         			var budget_id = "'"+val.budget_id+"'";
                    var actions = '<a href="javascript:void(0);"  onclick=getBudget('+budget_id+',"'+val.contract_id+'"); class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
/*                     			  +'<a onclick="deleteBudget('+budget_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
 */                   	var rowArray = [];    	                 
                   	
                	var workName = '';
                    if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                    
                   	rowArray.push($.trim(val.work_id_fk) + workName);
                   	rowArray.push($.trim(val.contract_id_fk));
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
    
    function getWorksFilterList(work) {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var contract_id_fk = $("#contract_id_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        if ($.trim(work_id_fk) == "") {
        	$("#work_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,contract_id_fk : contract_id_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInNewBudget",
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
    
    function getProjectsFilterList(project) {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        var contract_id_fk = $("#contract_id_fk").val();
		if ($.trim(project_id_fk) == "") {
        	$("#project_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,contract_id_fk : contract_id_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInNewBudget",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	var projectName = '';
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
    
    function getContractsFilterList() {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        var contract_id_fk = $("#contract_id_fk").val();
        if ($.trim(contract_id_fk) == "") {
        	$("#contract_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,contract_id_fk : contract_id_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInNewBudget",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
	                           $("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_name) +'</option>');
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
    
    function getBudget(budget_id,contract_id){
    	
    	$("#budget_id").val(budget_id);
    	$("#contract_id").val(contract_id);
    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-new-budget?contract_id='+contract_id);
    	$('#getForm').submit();
    }
    function deleteBudget(budget_id){
    	$("#budget_id").val(budget_id);
    	showCancelMessage();
    }
    	
    function getMonthName(month){
    	  const d = new Date();
    	  d.setMonth(month-1);
    	  const monthName = d.toLocaleString("default", {month: "long"});
    	  return monthName;
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
            text: "You will be changing the status of the record!",
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
   	 var contract_id_fk = $("#contract_id_fk").val();
   	 $("#exportProject_id_fk").val(project_id_fk);
   	 $("#exportWork_id_fk").val(work_id_fk);
   	 $("#exportcontract_id_fk").val(contract_id_fk);
   	 $("#exportBudgetForm").submit();
	}
    
    
    </script>

</body>

</html>