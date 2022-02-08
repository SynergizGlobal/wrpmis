<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R & R BSES- Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />

    <style>
        .input-field .searchable_label{
        	font-size:0.85rem;
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
         
         .row.no-mar{
         	margin-bottom:0;
         }
			
		.m-n1 {
		    margin: -2rem auto 0;
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
			
			 .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }	       
	      
		}
		#datatable-bses_mob td > .btn.t-c{
			padding: 0 10px;
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

	@media only screen and (max-width: 767px){
		.mob-mar{
			text-align: left;
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
							<div class="center-align bg-m p-2 m-b-2">								
								<h6 class="mob-mar ">R & R BSES</h6>
								 <div class="col s12 m12 right-align exportButton" >
								
								<div class="m-n1">
								<%--	<a href="/pmis/#.xlsx" download class="template-btn" title="Download Template">
										<i class="material-icons-outlined">download_for_offline</i>
									</a>
									<a href="javascript:void(0);"
										onclick="openUploadBsesModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-arrow-circle-up"></i> Upload</strong></a>--%>
									<a href="<%=request.getContextPath()%>/bses-add-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
									<!-- <a href="javascript:void(0);" onclick="exportDesign();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a> -->
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
							<div class="col s12 m12 l10 offset-l2">
								<div class="row">
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk" class="searchable" onchange="addInQueWork(this.value);getBSESList();">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">BSES Agency</p>
										<select id="agency_name" name="agency_name" class="searchable" onchange="addInQueAgency(this.value);getBSESList();">
											<option value="">Select</option>
										</select>
									</div>
									<!-- <div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Status</p>
										<select id="status_fk" name="status_fk" class="searchable" onchange="addInQueStatus(this.value);getBSESList();">
											<option value="">Select</option>											
										</select>
									</div> -->
									<div class="col s8 m4 l2 input-field center-align"> 
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
									<table id="datatable-bses" class="mdl-data-table">
										<thead>
											<tr>
												<th>ID</th>
												<th>work</th>
												<th>bses Agency</th>
												<th>contract</th>
												<!-- <th>status</th> -->
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
				<h6>Upload Designs</h6>
			</div>
			<!-- form start-->
			<div class="container">
				<form action="<%=request.getContextPath()%>/upload-designs"
					method="post" enctype="multipart/form-data">
					<div class="row no-mar">
						<div class="col s12 m12 input-field center-align">
							<div class="row">
								<div class="col m2 hide-on-small-only"></div>
								<div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m">
											<span>Attachment</span> <input type="file" id="BsesFile"
												name="BsesFile" required="required">
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
									style="width: 100%;" onclick="closeUploadBsesModal();">Cancel</button>
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
 
	 <form action="<%=request.getContextPath()%>/get-rr-bses" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="bses_id" id="bses_id"/>
    </form>
    
  <form action="<%=request.getContextPath()%>/export-rr-bses" name="exportRandRForm" id="exportRandRForm" target="_blank" method="post">	
        <input type="hidden" name="agency_name" id="exportAgency_name" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        
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
		
		function openUploadBsesModal() {
			$("#BsesFile").val('');
			$("#upload_template").modal('open');
		}

		function closeUploadBsesModal() {
			$("#BsesFile").val('');
			$("#upload_template").modal('close');
		}

		$(document).ready(function() {
			$('.modal').modal();
			$('select:not(.searchable)').formSelect();
			$('.searchable').select2();
			
			var filters = window.localStorage.getItem("BsesFilters");
		    if($.trim(filters) != '' && $.trim(filters) != null){
		      	  var temp = filters.split('^'); 
		      	  for(var i=0;i< temp.length;i++){
			        	  if($.trim(temp[i]) != '' ){
			        		  var temp2 = temp[i].split('=');
				        	  if($.trim(temp2[0]) == 'work_id_fk' ){
				        		  getWorksFilterList(temp2[1]);
				        	  }else if($.trim(temp2[0]) == 'agency_name'){
				        		  getAgencyNameFilterList(temp2[1]);
				        	  }
			          }
		          }     
		    }
			$('.close-message').delay(3000).fadeOut('slow');
		   
		     getBSESList();
		});
		  function addInQueAgency(agency_name){
		      	Object.keys(filtersMap).forEach(function (key) {
			   		if(key.match('agency_name')) delete filtersMap[key];
		   	   	});
		      	if($.trim(agency_name) != ''){
		        	filtersMap["agency_name"] = agency_name;
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
		function getBSESList(){
			$(".page-loader-2").show();

			getWorksFilterList('');
			getAgencyNameFilterList('');
	    	var work_id_fk = $("#work_id_fk").val();
	    	var agency_name = $("#agency_name").val();
	    	
	    	var filters = '';
	    	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
	    		filters = filters + key +"="+filtersMap[key] + "^";
	    		window.localStorage.setItem("BsesFilters", filters);
				});
	    	   	table = $('#datatable-bses').DataTable();
	    		table.destroy();
				var i = 0;
	    		$.fn.dataTable.moment('DD-MMM-YYYY');
	    		var rowLen = 0;
	    		var myParams =  "work_id_fk="
	    				+ work_id_fk + "&agency_name="+ agency_name;

	    		/***************************************************************************************************/

	    		$("#datatable-bses").DataTable({
	    			
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
	    								rowLen = $('#datatable-bses tbody tr:visible').length
	    								if(rowLen <= 1 &&  queue == 1){									
	    									$('#datatable-bses').dataTable().api().draw(); 
	    									getBSESList();
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
	    								 /* "width": "20px", "targets": [4] ,  */
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
	    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-rr-bses?"+myParams,
	    		        "aoColumns": [
	    		        	
	      		            { "mData": function(data,type,row){
	                             if($.trim(data.bses_id) == ''){ return '-'; }else{ return data.bses_id; }
	      		            } },
	      		         	{ "mData": function(data,type,row){
	      		         		var work_short_name = '';
	                             if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
	                             if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk +work_short_name; }
	      		            } },
	      		       
	    		            { "mData": function(data,type,row){ 
	    		            	if($.trim(data.agency_name) == ''){ return '-'; }else{ return data.agency_name; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		         		var contract_short_name = '';
	                             if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) }    	
	                             if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk +contract_short_name; }
	    		            } },
	    		         	{ "mData": function(data,type,row){
	    		         		var bses_id = "'"+data.bses_id+"'";
	    	                    var actions = '<a href="javascript:void(0);"  onclick="getRandR('+bses_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
	    		            	return actions;
	    		            } }
	    		            
	    		        ]
	    		    });
	    	
	    	
		  $(".page-loader-2").hide();  	
			
		}
		function getRandR(bses_id){
	    	$("#bses_id").val(bses_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-rr-bses');
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
	    
	    function getWorksFilterList(work) {
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var agency_name = $("#agency_name").val();
	    	
	        if ($.trim(work_id_fk) == "") {
	        	$("#work_id_fk option:not(:first)").remove();
	        	var myParams = {agency_name : agency_name,work_id_fk: work_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInRRBses",
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
	        
	        function getAgencyNameFilterList(agency) {
		    	$(".page-loader").show();
		    	var work_id_fk = $("#work_id_fk").val();
		    	var agency_name = $("#agency_name").val();
		        if ($.trim(agency_name) == "") {
		        	$("#agency_name option:not(:first)").remove();
		        	var myParams = {agency_name : agency_name,work_id_fk: work_id_fk};
		            $.ajax({
		                url: "<%=request.getContextPath()%>/ajax/getAgencyNameFilterListInRRBses",
		                data: myParams, cache: false,async: false,
		                success: function (data) {
		                    if (data.length > 0) {
		                        $.each(data, function (i, val) {
		                             var selectedFlag = (agency == val.agency_name)?'selected':'';
			                         $("#agency_name").append('<option value="' + val.agency_name + '"'+selectedFlag+'>' + $.trim(val.agency_name) +'</option>');
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
		        
		function clearFilter() {
			$("#work_id_fk").val('');
			$("#agency_name").val('');
			//$("#status_fk").val('');
			$('.searchable').select2();
			window.localStorage.setItem("BsesFilters",'');
	    	window.location.href= "<%=request.getContextPath()%>/rr-bses";
	    	var table = $('#datatable-bses').DataTable();
	    	table.draw( true );
		}
            
    </script>
</body>
</html>