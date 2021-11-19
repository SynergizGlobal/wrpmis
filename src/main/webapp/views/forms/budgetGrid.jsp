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
    <title>Budget - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/budget.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    
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

    @media only screen and (max-width: 767px) {
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
		<%-- <div class="col s12 m12 hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Budget</h6>
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
							<div class="col s12 m4"></div>

							<div class="col s12 m4">
								<div class="m-1 c-align">
									<a href="<%=request.getContextPath()%>/add-budget-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Budget</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align ">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportBudget();"
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
								<!-- <h6 class="hide-on-med-and-down">Update Budget</h6> -->
								<h6 class="mob-mar">Budget</h6>
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="<%=request.getContextPath()%>/add-budget-form"
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
							<div class="col m8 s12 offset-m2 l6 offset-l3">
								<div class="row no-mar">
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Project</p>
										<select class="searchable" name="project_id_fk"
											id="project_id_fk" onchange="addInQueProject(this.value);getBudgetList();">
											<option value="" selected>Select</option>

										</select>
									</div>
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getBudgetList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<!-- <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Financial Year</p>
                                        <select class="searchable" name="financial_year_fk" id="financial_year_fk" onchange="getBudgetList();">
                                            <option value="" >Select</option>
                                            	 
                                        </select>
                                    </div> -->
									<div class="col s12 m4 center-align">
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
												<th class='fw-300'>Work</th>
												<th>Latest <br>Financial Year
												</th>
												<th>Budget Estimate</th>
												<th>Budget Grant</th>
												<th>Reivised Estimate</th>
												<th>Reivised Grant</th>
												<th>Final Estimate</th>
												<th>Final Grant</th>
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
    </form>
    
     <form action="<%=request.getContextPath() %>/export-budget" name="exportBudgetForm" id="exportBudgetForm" target="_blank" method="post">	
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="financial_year_fk" id="exportFinancial_year_fk" />
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
    	$("#financial_year_fk").val("");
    	$('.searchable').select2();
    	window.localStorage.setItem("budgetFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/budget";
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
    
    var queue = 1;
    function getBudgetList() {
		$(".page-loader-2").show();

		getWorksFilterList('');
     	getProjectsFilterList('');
     	getFinancialYearsFilterList('');
     	
    	var project_id_fk = $("#project_id_fk").val();
    	var work_id_fk = $("#work_id_fk").val();
    	var financial_year_fk = $("#financial_year_fk").val();

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
    				+ work_id_fk + "&project_id_fk="+ project_id_fk+ "&financial_year_fk="+ financial_year_fk;

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
    							},{targets:[2,3,4,5,6,7],
    			                       className: 'hideCOl td-align-right'},{ targets: [0], className: 'no-sort'  }
    			                       ,{ targets: [1], className: 'td-align-center'  }
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
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-budget?"+myParams,
    		        "aoColumns": [
    		        	
      		            { "mData": function(data,type,row){
      		            	var work_short_name = '';
                             if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
                             if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk +work_short_name; }
      		            } },
      		         	{ "mData": function(data,type,row){
                             if($.trim(data.financial_year_fk) == ''){ return '-'; }else{ return data.financial_year_fk ; }
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
    		            } }, 
    		         	{ "mData": function(data,type,row){
    		         		var budget_id = "'"+data.budget_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getBudget('+budget_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
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
    	var financial_year_fk = $("#financial_year_fk").val();

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
	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, financial_year_fk : financial_year_fk};
	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-budget",
			type:"POST",
			data:myParams, cache: false,async:false,
			success : function(data){      				
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
    
    function getWorksFilterList(work) {
    	$(".page-loader").show();
        var project_id_fk = $("#project_id_fk").val();
        var financial_year_fk = $("#financial_year_fk").val();
        var work_id_fk = $("#work_id_fk").val();
        if ($.trim(work_id_fk) == "") {
        	$("#work_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,financial_year_fk : financial_year_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInBudget",
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
        var financial_year_fk = $("#financial_year_fk").val();
		if ($.trim(project_id_fk) == "") {
        	$("#project_id_fk option:not(:first)").remove();
        	var myParams = { project_id_fk: project_id_fk,financial_year_fk : financial_year_fk,work_id_fk: work_id_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInBudget",
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
    
    function getFinancialYearsFilterList(year) {
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