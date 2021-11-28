<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Structure Form - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
	<!-- <link rel="stylesheet" href="/pmis/resources/css/fob.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
	 <style>
     	.fw-400{
     		max-width:400px;
     		width:400px;
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
         .mt-md-18{
         	margin-top:18px;
         }
          .fw-15vw{
        	width:50px !important;
        	min-width:50px;
	      }
	     .fw-12vw{
	        	width:15vw !important;
	        	min-width:15vw !important;
	      }
	     .fw-10vw{
		       	width:50px !important;
		       	min-width:50px;
	      } 
         @media only screen and (max-width: 769px){ 
		.mdl-data-table thead tr th{
			text-align:left !important;
		}		
		.dataTables_scrollBody tbody tr td:last-of-type,
		.no-sort{
			padding:3px !important;
			max-width: 35px;
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
        .fw-12vw {
	  	     width:15vw !important;
        	min-width:15vw !important;
		}
       .mdl-data-table__cell--non-numeric.mdl-data-table__cell--non-numeric {
		    text-align: center;
		}
     } 
     .no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
	}
   .m-n1 {
        margin: -2rem auto 0;
    }

    .template-btn {
        text-shadow: 1px 1px 1px black;
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
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Structure </h6> -->
								<h6 class="mob-mar">Structure Form</h6>
								<c:if test="${(sessionScope.USER_TYPE eq 'DyHOD') or (sessionScope.USER_TYPE eq 'HOD') or (sessionScope.USER_ROLE_CODE eq 'IT')}">
									<div class="col s12 m12 right-align exportButton">
									    <div class="m-n1">
									        <a href="<%=request.getContextPath()%>/add-structures-form"
												class="btn waves-effect waves-light bg-s t-c"> <strong><i
													class="fa fa-plus-circle"></i> Add</strong></a>
											<!-- <a href="javascript:void(0);" onclick="exportStructure();"
												class="btn waves-effect waves-light bg-s t-c"> <strong><i
													class="fa fa-cloud-download"></i> Export</strong></a> -->
									    </div>
									</div>
								</c:if>
							</div>
						</span>
						
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>

						<div class="row no-mar">
							<div class="col s12 l8 offset-l2 m9 offset-m2">
								<div class="row no-mar">																		
									<div class="col s6 m3 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getStructureList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									
									<div class="col s6 m3 input-field">
										<p class="searchable_label">Contract</p>
										<select id="contract_id_fk" name="contract_id_fk"
											onchange="addInQueContract(this.value);getStructureList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									
									<div class="col s6 m3 input-field">
										<p class="searchable_label">Work Status</p>
										<select id="work_status_fk" name="work_status_fk"
											onchange="addInQueWorkStatus(this.value);getStructureList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<div class="col s12 m3 input-field center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col m12 s12">
								<table id="datatable-structure" class="mdl-data-table">
									<thead>
										<tr>
											<th class="no-sort">Work</th>
											<th>Structure  ID</th>
											<th>Structure  Name</th>
											<th>Contract</th>
											<th>Work Status</th>
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
	
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<form action="<%=request.getContextPath()%>/get-structure-form" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="structure_id" id="structure_id"/>
  		<input type="hidden" name="work_id_fk" id="work"/>
    </form>
  
  
	<form action="<%=request.getContextPath() %>/export-structures" name="exportStructureForm" id="exportStructureForm" target="_blank" method="post">	
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="work_status_fk" id="exportWork_status_fk" />
         <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
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
		//var pageNo = window.localStorage.getItem("strcturePageNo");
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            var filters = window.localStorage.getItem("structureFilters1");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
  	        	  if($.trim(temp[i]) != '' ){
  	        		  var temp2 = temp[i].split('=');
  		        	  if($.trim(temp2[0]) == 'work_status_fk' ){
  		        		getWorkStatusFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
  		        		getWorksFilterList(temp2[1]);
  	        		  }else if($.trim(temp2[0]) == 'contract_id_fk'){
  	        			getContractsFilterList(temp2[1]);
		        	  }
  	        	  }
  	            }
              }
    		
        	
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	 getStructureList();
        	/*  if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
             var oTable = $('#datatable-structure').dataTable();
             oTable.fnPageChange( pageNo ); */
        });

        function clearFilter(){
        	$("#work_id_fk").val('');
        	$("#work_status_fk").val('');
        	$("#contract_id_fk").val('');
        	$(".searchable").select2();
        	window.localStorage.setItem("structureFilters1",'');
        	window.location.href="<%=request.getContextPath()%>/structure-form"
        	//getFOBList();
        	var table = $('#datatable-structure').DataTable();
        	table.draw( true );
        	
        }
        
        function addInQueWork(work_id_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('work_id_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(work_id_fk) != ''){
            	filtersMap["work_id_fk"] = work_id_fk;
	      	}
        }
        
        function addInQueWorkStatus(work_status_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('work_status_fk')) delete filtersMap[key];
	   		});
        	if($.trim(work_status_fk) != ''){
       	    	filtersMap["work_status_fk"] = work_status_fk;
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
        function getStructureList() {
			$(".page-loader-2").show();

			getWorkStatusFilterList('');
        	getWorksFilterList('');
        	getContractsFilterList('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var work_status_fk = $("#work_status_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	
			
			var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("structureFilters1", filters);
   			});
        	 
						table = $('#datatable-structure').DataTable();
			
						table.destroy();
			
						$.fn.dataTable.moment('DD-MMM-YYYY');
						var rowLen = 0;
						var myParams = "work_id_fk=" + work_id_fk 
								+ "&work_status_fk=" + work_status_fk+ "&contract_id_fk=" + contract_id_fk;
			
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
												rowLen = $('#datatable-structure tbody tr:visible').length
			    								if(rowLen <= 1 &&  queue == 1){									
			    									$('#datatable-structure').dataTable().api().draw(); 
			    									getStructureList();
			    									queue++;
			    							    }
											},
											columnDefs : [ {
												"targets" : 'no-sort',
												"orderable" : false,
											},
											// {targets:[1,2,4,5], className: 'hideCOl'},
											{ targets: [0], className: 'no-sort'  },
											{ targets: [1], className: 'last-column'  },
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
											"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/getStructuresList?"+myParams,
													 
						        "aoColumns": [
						        	
						            { "mData": function(data,type,row){
						            	var work_short_name = '';
				                        if ($.trim(data.work_short_name) != '') { work_short_name = $.trim(data.work_short_name) }    	
				                     	if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return work_short_name; }
			            			} },   				            
						            { "mData": function(data,type,row){
						            	if($.trim(data.structure) == ''){ return '-'; }else{ return data.structure; }
						            } },
						            { "mData": function(data,type,row){
						            	if($.trim(data.structure_name) == ''){ return '-'; }else{ return data.structure_name; }
						            } },
						         	{ "mData": function(data,type,row){
						         		var contract_short_name = '';
						         		if ($.trim(data.contract_short_name) != '') { contract_short_name =  $.trim(data.contract_short_name) } 
						            	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return  contract_short_name; }
						            } }, 
						            { "mData": function(data,type,row){
						            	if($.trim(data.work_status_fk) == ''){ return '-'; }else{ return data.work_status_fk; }
						            } },
						         	{ "mData": function(data,type,row){
						         		var structure_id = "'"+data.structure_id+"'";
						         		var work = "'"+data.work_id_fk+"'";
					                    var actions = '<a href="javascript:void(0);"  onclick="getStructure('+structure_id+','+work+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
						            	return actions;
						            } }
						            
						        ]
						    });
		  $(".page-loader-2").hide();  		     
      	
     }

        
        function getStructureList1(){
        	$(".page-loader").show();
        	
        	getWorkStatusFilterList('');
        	getWorksFilterList('');
        	getContractsFilterList('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var work_status_fk = $("#work_status_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("structureFilters1", filters);
   			});
       		table = $('#datatable-structure').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-structure').DataTable({
         		"bStateSave": true,  
         		fixedHeader: true,
               
             	//Default: Page display length
 				"iDisplayLength" : 10,
 				"iData" : {
 					"start" : 52
 				},"iDisplayStart" : 0,
 				"drawCallback" : function() {
 					var info = table.page.info();
 					//window.localStorage.setItem("strcturePageNo", info.page);
 				},
                columnDefs: [ 
                	{targets: [2,3], className: 'hideCOl'},
                    { targets: [ 0,1,2, 3], className: 'fw-12vw'  },
                    { targets: [4], className: 'fw-10vw'  },
                    { targets: [0,1], className: 'fw-111'  },
                    { "width": "20px", "targets": [5] },
                    { orderable: false, 'aTargets': ['no-sort'] } 
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                 "ordering":false,
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
    	 
    	 	var myParams = {work_id_fk : work_id_fk, work_status_fk : work_status_fk, contract_id_fk:contract_id_fk};
    		$.ajax({url : "<%=request.getContextPath()%>/ajax/getStructureList",
	    			type:"POST",
	    			data:myParams,cache: false,async:false,
	    			success : function(data)
	    			{    	
    					if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var structure_id = "'"+val.structure_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getStructure('+structure_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
    	                   	var rowArray = [];    	                  
    	                   	var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) } 
    	                   	
    	                   	rowArray.push( workName);
    	                   	rowArray.push($.trim(val.structure_id));
    	                   	rowArray.push($.trim(val.fob_name));
    	                   	rowArray.push($.trim(val.contract_short_name));
    	                   	rowArray.push($.trim(val.work_status_fk));
    	                   	rowArray.push($.trim(actions));   	                   	
    	                   	
    	                    table.row.add(rowArray).draw( true );
    	                    		                       
    					});
    	         		
    	         		$(".page-loader").hide();
    				}else{
    					$(".page-loader").hide();
    				}
    				
    			},error: function (jqXHR, exception) {
    				$(".page-loader").hide();
    	         	getErrorMessage(jqXHR, exception);
    	     }});
        	
        }
        
        function getWorkStatusFilterList(work_status){
        	$(".page-loader").show();
    	 	var work_id_fk = $("#work_id_fk").val();
    	 	var work_status_fk = $("#work_status_fk").val(); 
    	 	var contract_id_fk = $("#contract_id_fk").val();
    	    if ($.trim(work_status_fk) == "") {
    	    	$("#work_status_fk option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,work_status_fk : work_status_fk,contract_id_fk : contract_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkStatusFilterListInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                       if(data != null && data != '' && data.length > 0){  
                            $.each(data, function (i, val) {
                            		var selectedFlag = (work_status == val.work_status_fk)?'selected':'';
    	                           $("#work_status_fk").append('<option value="' + val.work_status_fk + '"'+selectedFlag+'>' + $.trim(val.work_status_fk) + '</option>');
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
        
        function getWorksFilterList(work_id){
    	 	$(".page-loader").show();
    	 	var work_status_fk = $("#work_status_fk").val();
    	 	var work_id_fk = $("#work_id_fk").val();
    	 	var contract_id_fk = $("#contract_id_fk").val();
    	    if ($.trim(work_id_fk) == "") {
    	    	$("#work_id_fk option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,work_status_fk : work_status_fk,contract_id_fk : contract_id_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                    	if(data != null && data != '' && data.length > 0){  
                            $.each(data, function (i, val) {
                            	 var work_short_name = '';
                                 if ($.trim(val.work_short_name) != '') { work_short_name = ' - ' + $.trim(val.work_short_name) }
                                 var selectedFlag = (work_id == val.work_id_fk)?'selected':'';
    	                         $("#work_id_fk").append('<option value="' + val.work_id_fk + '"'+selectedFlag+'>' + $.trim(val.work_id_fk) + work_short_name + '</option>');
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
        
        
        function getContractsFilterList(contract_id){
    	 	$(".page-loader").show();
    		var work_id_fk = $("#work_id_fk").val();
    	 	var work_status_fk = $("#work_status_fk").val();
    	 	var contract_id_fk = $("#contract_id_fk").val();
    	    if ($.trim(contract_id_fk) == "") {
    	    	$("#contract_id_fk option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,contract_id_fk : contract_id_fk,work_status_fk : work_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInStructure",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                    	if(data != null && data != '' && data.length > 0){  
                            $.each(data, function (i, val) {
                            	 var contract_short_name = '';
                                 if ($.trim(val.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(val.contract_short_name) }
                                 var selectedFlag = (contract_id == val.contract_id_fk)?'selected':'';
    	                         $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '"'+selectedFlag+'>' + $.trim(val.contract_id_fk) + contract_short_name + '</option>');
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
        
        
        function getStructure(structure_id,work) {
    		$("#structure_id").val(structure_id);
    		$("#work").val(work);
    		$("#getForm").submit();
    	}
        
        function exportStructure(){
          	 var work_id_fk = $("#work_id_fk").val();
          	 var work_status_fk = $("#work_status_fk").val();
          	 var contract_id_fk = $("#contract_id_fk").val();
          	 
          	 $("#exportWork_id_fk").val(work_id_fk);
          	 $("#exportWork_status_fk").val(work_status_fk);
          	 $("#exportContract_id_fk").val(contract_id_fk);
          	 $("#exportFOBForm").submit();
       	}
       

    </script>
</body>
</html>