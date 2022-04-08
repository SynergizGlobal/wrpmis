<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R & R - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />

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
		#datatable-randr_mob td > .btn.t-c{
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
	.m-n1 {
   		 margin: -2rem auto 0;
	}
	.template-btn{
		text-shadow:1px 1px 1px black;
	}
	@media only screen and (max-width: 820px){
		.mob-mar{
			text-align: center;
		    margin-top: -1rem;
		    margin-bottom: 2.2rem;
		}
		.exportButton .btn{
			padding-left: 6px;
	   		padding-right: 6px;
		}
	}
	
	thead th{
		text-transform: capitalize;
	}
    </style>
</head>
<body>

   <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		
		<div class="row no-mar">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">								
								<h6 class="left-align center-align">R & R</h6>
								<div class="col s12 m12 right-align exportButton hideCOl" >
								
								<div class="m-n1">
									 <!-- <a href="/pmis/RR_Drawings.xlsx" download class="template-btn" title="Download Template">
										<i class="material-icons-outlined">download_for_offline</i>
									</a> -->
									<a href="/pmis/R&R_Template.xlsx" download class="template-btn" title="Click to Download R&R Template">
										<i class="material-icons-outlined">download_for_offline</i>
									</a>
									<a href="javascript:void(0);"
										onclick="openUploadRRModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-arrow-circle-up"></i> Upload</strong></a> 
									<a href="<%=request.getContextPath()%>/add-randr-main"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
									<a href="javascript:void(0);" onclick="exportDesign();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a> 
								</div>
							</div>
							</div>
						</span>
						<c:if test="${not empty success }">
									<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
						<div class="row no-mar">
							<div class="col s12 m12">
								<div class="row">
									<div class="col s12 hide-on-large-only mb-md-2 center-align">
									    <a href="<%=request.getContextPath()%>/add-randr-form"
									        class="btn waves-effect waves-light bg-s t-c"> <strong><i
									            class="fa fa-plus-circle"></i> Add R & R</strong></a>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk" class="searchable" onchange="addInQueWork(this.value);getRRList();">
											<option value="">Select</option>											
										</select>
									</div>
									
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Location</p>
										<select id="location_name" name="location_name" class="searchable" onchange="addInQueLocation(this.value);getRRList();">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Phase</p>
										<select id="phase" name="phase" class="searchable" onchange="addInQuePhase(this.value);getRRList();">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Structure</p>
										<select id="structure_id" name="structure_id" class="searchable" onchange="addInQueStructure(this.value);getRRList();">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l1 input-field">
										<p class="searchable_label">Type of Use</p>
										<select id="type_of_use" name="type_of_use" class="searchable" onchange="addInQueTypeofUse(this.value);getRRList();">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Status</p>
										<select id="boundary_wall_status" name="boundary_wall_status" class="searchable" onchange="addInQueStatus(this.value);getRRList();">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s12 m4 offset-m4 l1 input-field center-align">
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
									<table id="datatable-randr" class="mdl-data-table">
										<thead>
											<tr>
												<th class="fw-200">ID</th>
												<th>occupier name</th>
												<th>location</th>
												<th>sub location</th>
												<th>phase</th>
 												<th>structure </th>
												<th>type of use</th>
												<!-- <th>stage</th> -->
												<th>status</th>
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
		</div>
	
	</div>

	<!-- update popup starts -->
	<div id="upload_template" class="modal">
		<div class="modal-content">
			<div class="center-align p-2 bg-m modal-title headbg">
				<h6>Upload R & R</h6>
			</div>
			<!-- form start-->
			<div class="container">
				<form action="<%=request.getContextPath()%>/upload-rr"
					method="post" enctype="multipart/form-data">
					<div class="row no-mar">
						<div class="col s12 m12 input-field center-align">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m">
											<span>Attachment</span> <input type="file" id="RandRFile"
												name="RandRFile" required="required">
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
									style="width: 100%;" onclick="closeUploadRRModal();">Cancel</button>
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

	
	<!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
	 <form action="<%=request.getContextPath()%>/get-randr" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="rr_id" id="rr_id"/>
  		<input type="hidden" name="work_id_fk" id="work_id"/>
    </form>
    
  <form action="<%=request.getContextPath()%>/export-randr-main" name="exportRandRForm" id="exportRandRForm" target="_blank" method="post">	
        <input type="hidden" name="location_name" id="exportLocation_fk" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="phase" id="exportPhase_fk" />
        <input type="hidden" name="boundary_wall_status" id="exportStatus_fk" />
        <input type="hidden" name="structure_id" id="exportStructure_type_fk" />
        <input type="hidden" name="type_of_use" id="exportType_of_use" />
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
	
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

		var filtersMap = new Object();
		
		
		function openUploadRRModal() {
			$("#RandRFile").val('');
			$("#upload_template").modal('open');
		}

		function closeUploadRRModal() {
			$("#RandRFile").val('');
			$("#upload_template").modal('close');
		}

		$(document).ready(function() {
	    	var cid2 = getUrlVars()["work_id"];
		    if(cid2!="")
		    {
		    	$("#work_id_fk").val(cid2).trigger('change');
		    	addInQueWork(cid2);getRRList();
		    } 
	    	var cid1 = getUrlVars()["type_of_use"];
		    if(cid1!="")
		    {
		    	$("#type_of_use").val(cid1);
		    }  		    
	    	var cid = getUrlVars()["location"];
		    if(cid!="")
		    {
		    	$("#location_name").val(cid);
		    } 		    
			
			$('.modal').modal();
			$('select:not(.searchable)').formSelect();
			$('.searchable').select2();
			$('.close-message').delay(20000).fadeOut('slow');
			var filters = window.localStorage.getItem("RandRFilters");
		    if($.trim(filters) != '' && $.trim(filters) != null){
		      	  var temp = filters.split('^'); 
		      	  for(var i=0;i< temp.length;i++){
			        	  if($.trim(temp[i]) != '' ){
			        		  var temp2 = temp[i].split('=');
				        	  if($.trim(temp2[0]) == 'work_id_fk' ){
				        		  getWorksFilterList(temp2[1]);
				        	  }else if($.trim(temp2[0]) == 'location_name'){
				        		  getLocationsFilterList(temp2[1]);
				        	  }else if($.trim(temp2[0]) == 'phase'){
				        		  getPhasesFilterList(temp2[1]);
				        	  }else if($.trim(temp2[0]) == 'structure_id'){
				        		  getStructuresFilterList(temp2[1]);
				        	  }else if($.trim(temp2[0]) == 'type_of_use'){
				        		  getTypeofUseFilterList(temp2[1]);
				        	  }else if($.trim(temp2[0]) == 'boundary_wall_status'){
				        		  getStatussFilterList(temp2[1]);
				        	  }
			        	  }
			          }
		          }
			

		     getRRList();
		});
		 function addInQueLocation(location_name){
		    	Object.keys(filtersMap).forEach(function (key) {
		   			if(key.match('location_name')) delete filtersMap[key];
		   		});
		    	if($.trim(location_name) != ''){
		   	    	filtersMap["location_name"] = location_name;
		    	}
		    }
		    
		    function addInQuePhase(phase){
		      	Object.keys(filtersMap).forEach(function (key) {
			   		if(key.match('phase')) delete filtersMap[key];
		   	   	});
		      	if($.trim(phase) != ''){
		        	filtersMap["phase"] = phase;
		      	}
		    }
		    function addInQueStructure(structure_id){
		      	Object.keys(filtersMap).forEach(function (key) {
			   		if(key.match('structure_id')) delete filtersMap[key];
		   	   	});
		      	if($.trim(structure_id) != ''){
		        	filtersMap["structure_id"] = structure_id;
		      	}
		    }
		    function addInQueTypeofUse(type_of_use){
		      	Object.keys(filtersMap).forEach(function (key) {
			   		if(key.match('type_of_use')) delete filtersMap[key];
		   	   	});
		      	if($.trim(type_of_use) != ''){
		        	filtersMap["type_of_use"] = type_of_use;
		      	}
		    }
		    function addInQueStatus(boundary_wall_status){
		      	Object.keys(filtersMap).forEach(function (key) {
			   		if(key.match('boundary_wall_status')) delete filtersMap[key];
		   	   	});
		      	if($.trim(boundary_wall_status) != ''){
		        	filtersMap["boundary_wall_status"] = boundary_wall_status;
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
	    function getRRList() {
			$(".page-loader-2").show();

			getWorksFilterList('');
	     	getLocationsFilterList('');
	     	getPhasesFilterList('');
	     	getStructuresFilterList('');
	     	getTypeofUseFilterList('');
	     	getStatussFilterList('');
	     	
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	    	var filters = '';
	    	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
	    		filters = filters + key +"="+filtersMap[key] + "^";
	    		window.localStorage.setItem("RandRFilters", filters);
				});
	    	   	table = $('#datatable-randr').DataTable();
	    		table.destroy();
				var i = 0;
	    		$.fn.dataTable.moment('DD-MMM-YYYY');
	    		var rowLen = 0;
	    		var myParams =  "work_id_fk="
	    				+ work_id_fk + "&location_name="+ location_name+ "&phase="+ phase+ "&structure_id="+ structure_id+ "&type_of_use="+ type_of_use+ "&boundary_wall_status="+ boundary_wall_status;

	    		/***************************************************************************************************/

	    		$("#datatable-randr").DataTable({
	    			
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
	    								rowLen = $('#datatable-randr tbody tr:visible').length
	    								if(rowLen <= 1 &&  queue == 1){									
	    									$('#datatable-randr').dataTable().api().draw(); 
	    									getRRList();
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
	    							},
	    							{
	    	                            targets: [0,1,2,3,4,7,8],
	    	                            className: 'hideCOl'
	    	                        }
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
	    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-rAndr?"+myParams,
	    		        "aoColumns": [
	    		        	
	      		            { "mData": function(data,type,row){
	                             if($.trim(data.rr_id) == ''){ return '-'; }else{ return data.rr_id; }
	      		            } },
	      		         	{ "mData": function(data,type,row){
	                             if($.trim(data.occupier_name_during_verification) == ''){ return '-'; }else{ return data.occupier_name_during_verification ; }
	      		            } },
	      		       
	    		            { "mData": function(data,type,row){ 
	    		            	if($.trim(data.location_name) == ''){ return '-'; }else{ return data.location_name; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		            	if($.trim(data.sub_location_name) == ''){ return '-'; }else{ return data.sub_location_name; }
	    		            } },
	    		            { "mData": function(data,type,row){
	    		            	if($.trim(data.phase) == ''){ return '-'; }else{ return data.phase; }
	    		            } },
	    		            { "mData": function(data,type,row){
	    		            	if($.trim(data.structure_id) == ''){ return '-'; }else{ return data.structure_id; } 
	    		            }},
	    		         	{ "mData": function(data,type,row){
	    		            	if($.trim(data.type_of_use) == ''){ return '-'; }else{ return data.type_of_use; }
	    		            } },
	    		           /*  { "mData": function(data,type,row){
	    		            	if($.trim(data.stage) == ''){ return '-'; }else{ return data.stage; }
	    		            } }, */
	    		            { "mData": function(data,type,row){
	    		            	if($.trim(data.boundary_wall_status) == ''){ return '-'; }else{ return data.boundary_wall_status; }
	    		            } },
	    		            { "mData": function(data,type,row){
	    		            	if($.trim(data.modified_date) == ''){ return '-'; }else{ return data.modified_date; }
	    		            } },	    		            
	    		         	{ "mData": function(data,type,row){
	    		         		var rr_id = "'"+data.rr_id+"'";
	    		         		var work_id = "'"+data.work_id+"'";
	    	                    var actions = '<a href="javascript:void(0);"  onclick="getRandR('+rr_id+','+work_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
	    		            	return actions;
	    		            } }
	    		            
	    		        ]
	    		    });
	    	
	    	
		  $(".page-loader-2").hide();  		     
	  	
	 }
	    function getStatussFilterList(status) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	        if ($.trim(boundary_wall_status) == "") {
	        	$("#boundary_wall_status option:not(:first)").remove();
	        	var myParams = { location_name: location_name,phase : phase,work_id_fk: work_id_fk,structure_id: structure_id,type_of_use : type_of_use,boundary_wall_status: boundary_wall_status };
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInRR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (status == val.boundary_wall_status)?'selected':'';
		                         $("#boundary_wall_status").append('<option value="' + val.boundary_wall_status + '"'+selectedFlag+'>' + $.trim(val.boundary_wall_status)  +'</option>');
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
	    function getLocationsFilterList(location) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	        if ($.trim(location_name) == "") {
	        	$("#location_name option:not(:first)").remove();
	        	var myParams = { location_name: location_name,phase : phase,work_id_fk: work_id_fk,structure_id: structure_id,type_of_use : type_of_use,boundary_wall_status: boundary_wall_status };
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getLocationsFilterListInRR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (location == val.location_name)?'selected':'';
		                         $("#location_name").append('<option value="' + val.location_name + '"'+selectedFlag+'>' + $.trim(val.location_name)  +'</option>');
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
	    function getTypeofUseFilterList(type) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	        if ($.trim(type_of_use) == "") {
	        	$("#type_of_use option:not(:first)").remove();
	        	var myParams = { location_name: location_name,phase : phase,work_id_fk: work_id_fk,structure_id: structure_id,type_of_use : type_of_use,boundary_wall_status: boundary_wall_status };
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getTypeofUseFilterListInRR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (type == val.type_of_use)?'selected':'';
		                         $("#type_of_use").append('<option value="' + val.type_of_use + '"'+selectedFlag+'>' + $.trim(val.type_of_use)  +'</option>');
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
	    function getStructuresFilterList(structure) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	        if ($.trim(structure_id) == "") {
	        	$("#structure_id option:not(:first)").remove();
	        	var myParams = { location_name: location_name,phase : phase,work_id_fk: work_id_fk,structure_id: structure_id,type_of_use : type_of_use,boundary_wall_status: boundary_wall_status };
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getStructuresFilterListInRR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (structure == val.structure_id)?'selected':'';
		                         $("#structure_id").append('<option value="' + val.structure_id + '"'+selectedFlag+'>' + $.trim(val.structure_id)  +'</option>');
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
	    function getPhasesFilterList(phaseVal) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	        if ($.trim(phase) == "") {
	        	$("#phase option:not(:first)").remove();
	        	var myParams = { location_name: location_name,phase : phase,work_id_fk: work_id_fk,structure_id: structure_id,type_of_use : type_of_use,boundary_wall_status: boundary_wall_status };
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getPhasesFilterListInRR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                             var selectedFlag = (phaseVal == val.phase)?'selected':'';
		                         $("#phase").append('<option value="' + val.phase + '"'+selectedFlag+'>' + $.trim(val.phase)  +'</option>');
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
	    function getWorksFilterList(work) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var location_name = $("#location_name").val();
	    	var phase = $("#phase").val();
	    	var structure_id = $("#structure_id").val();
	    	var type_of_use = $("#type_of_use").val();
	    	var boundary_wall_status = $("#boundary_wall_status").val();

	        if ($.trim(work_id_fk) == "") {
	        	$("#work_id_fk option:not(:first)").remove();
	        	var myParams = { location_name: location_name,phase : phase,work_id_fk: work_id_fk,structure_id: structure_id,type_of_use : type_of_use,boundary_wall_status: boundary_wall_status };
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInRR",
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
	    function getRandR(rr_id,work_id){
	    	$("#rr_id").val(rr_id);
	    	$("#work_id").val(work_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-rr');
	    	$('#getForm').submit();
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
	    
	    
		function clearFilter() {
			$("#work_id_fk").val('');
			$("#location_name").val('');
			$("#phase").val('');
			$("#structure_id").val('');
			$("#type_of_use").val('');
			$("#boundary_wall_status").val('');
			$('.searchable').select2();
			window.localStorage.setItem("RandRFilters",'');
	    	window.location.href= "<%=request.getContextPath()%>/randr-main";
	    	//getBudgetList(); 
	    	var table = $('#datatable-randr').DataTable();
	    	table.draw( true );
		}

		function exportDesign(){
				var work_id_fk = $("#work_id_fk").val();
		    	var location_name = $("#location_name").val();
		    	var phase = $("#phase").val();
		    	var structure_id = $("#structure_id").val();
		    	var type_of_use = $("#type_of_use").val();
		    	var boundary_wall_status = $("#boundary_wall_status").val();
		    	
		    	 $("#exportWork_id_fk").val(work_id_fk);
		    	 $("#exportLocation_fk").val(location_name);
		    	 $("#exportPhase_fk").val(phase);
		    	 $("#exportType_of_use").val(type_of_use);
		    	 $("#exportStructure_type_fk").val(structure_id);
		    	 $("#exportStatus_fk").val(boundary_wall_status);
		    	     
	        	 $("#exportRandRForm").submit();
		}
    </script>
</body>
</html>