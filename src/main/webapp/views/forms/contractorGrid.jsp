<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contractor - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png"> 
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contractor.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
   	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
   	<link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
   	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
   <style>
   
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
         .fw-180{
         	width:180px;
        	min-width:180px;
         }
         .fw-250{
         	width:250px;
        	min-width:250px;
         }
          .fw-100{
         	width:100px !important;
        	min-width:100px !important;
         }
      @media only screen and (max-width: 820px){ 
		.dataTables_scrollBody tbody tr td:last-of-type,
		.no-sort{
			padding:3px !important;
			max-width: 45px;
		}
		.fw-100{
         	width:130px !important;
        	min-width:130px !important;
         }
		td:not(.no-sort):not(:last-of-type),
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
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<%-- <div class="col s12 m12 hide-on-med-and-down">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>Contractor</h6>
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
									<a href="<%=request.getContextPath()%>/add-contractor-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Contractor</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align ">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportContractor();"
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
								<!-- <h6 class="hide-on-med-and-down">Update Contractor</h6> -->
								<h6 class="mob-mar">Contractor</h6>
								<div class="col s12 m12 right-align exportButton">
							    	<div class="m-n1">
							    		<a href="<%=request.getContextPath()%>/add-contractor-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
										<a href="javascript:void(0);" onclick="exportContractor();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>
							    	</div>
							    </div>
							</div>
						</span>
						<div class="row">
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
							<div class="col m12 s12">
								<table id="contractorTable" class="mdl-data-table">
									<thead>
										<tr>
											
											<th class="no-sort fw-100">Contractor Name</th>
											<th>PAN Number</th>
											<th>Specialization</th>
											<th class="fw-250"> Address</th>
											<th>Primary Contact</th>
											<th>Phone Number</th>
											<th class="fw-180"> Email </th>
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
    	<input type="hidden" name="contractor_id" id="contractor_id" />
    </form>
     
    <form action="<%=request.getContextPath() %>/export-contractor" name="exportContractorForm" id="exportContractorForm" target="_blank" method="post">	
        <input type="hidden" name="contractor_id" id="exportContractor_id" />
	</form>
	
    <script>
        $(document).ready(function () {
        
        	
        	$('.close-message').delay(3000).fadeOut('slow');
            getContractorsList();
            if(window.matchMedia("(max-width: 820px)").matches){
  		        $('#mobView').css({'display':'block'});
  		      	
  		    } else{
  		    	$('#webView').css({'display':'block'});
  		    }
        });
        
        function getContractorsList() {
    		$(".page-loader-2").show();

   		     	table = $('#contractorTable').DataTable();
   	    		table.destroy();

   	    		$.fn.dataTable.moment('DD-MMM-YYYY');

   	    		var myParams = "";

   	    		/***************************************************************************************************/

   	    		$("#contractorTable")
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
   	    										'<i class="fa fa-search" title="Go" id="save_post">')
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
   	    							},{targets: [1,2,3,5,6], className: 'hideCOl'} ],
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
   	    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-contractor?"+myParams,
   	    		        "aoColumns": [
   	    		            { "mData": function(data,type,row){
   	    		            	if($.trim(data.contractor_name) == ''){ return '-'; }else{ return data.contractor_name; }
   	    		            } },
   	    		            { "mData": function(data,type,row){
   	    		            	if($.trim(data.pan_number) == ''){ return '-'; }else{ return data.pan_number; }
   	    		            } },
   	    		         	{ "mData": function(data,type,row){
   	    		            	if($.trim(data.contractor_specilization_fk) == ''){ return '-'; }else{ return data.contractor_specilization_fk; }
   	    		            } },
   	    		            { "mData": function(data,type,row){
   	    		            	if($.trim(data.address) == ''){ return '-'; }else{ return data.address; }
   	    		            } },
   	    		            { "mData": function(data,type,row){
   	    		            	if($.trim(data.primary_contact_name) == ''){ return '-'; }else{ return data.primary_contact_name; }
   	    		            } },
   	    		            { "mData": function(data,type,row){
   	    		            	if($.trim(data.phone_number) == ''){ return '-'; }else{ return data.phone_number; }
   	    		            } },
   	    		            { "mData": function(data,type,row){
   	    		            	if($.trim(data.email_id) == ''){ return '-'; }else{ return data.email_id; }
   	    		            } },
   	    		         	{ "mData": function(data,type,row){
   	    		         		var contractor_id = "'"+data.contractor_id+"'";
   	    	                    var actions = '<a href="javascript:void(0);"  onclick="getContractor('+contractor_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
   	    		            	return actions;
   	    		            } } 
   	    		            
   	    		        ]
   	    		    });
    	  $(".page-loader-2").hide();  		     
      	
      }
        
      function getContractor(contractor_id){
	    	$("#contractor_id").val(contractor_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-contractor');
	    	$('#getForm').submit();
	    }
        
    	function deleteContractor(contractor_id){
        	$("#contractor_id").val(contractor_id);
        	showCancelMessage();
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
	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-contractor');
	    	    	$('#getForm').submit();
	           }else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
        
        function exportContractor(){
        	 var contractor_id = $("#contractor_id").val();
        	 $("#exportContractor_id").val(contractor_id);
        	 $("#exportContractorForm").submit();
     	}
    </script>
</body>

</html>