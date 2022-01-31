<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utility Shifting - Update Forms - PMIS</title>
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
		#datatable-us_mob td > .btn.t-c{
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
	@media only screen and (max-width: 767px){
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
								<h6 class="left-align mob-mar center-align">Utility Shifting</h6>
								<%-- <div class="col s12 m12 right-align exportButton" >
								
								<div class="m-n1">
									<a href="/pmis/Designs_Drawings.xlsx" download class="template-btn" title="Download Template">
										<i class="material-icons-outlined">download_for_offline</i>
									</a>
									<a href="javascript:void(0);"
										onclick="openUploadUSModal();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-arrow-circle-up"></i> Upload</strong></a>
									<a href="<%=request.getContextPath()%>/add"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
									<a href="javascript:void(0);" "
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>
								</div>
							</div> --%>
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
									    <a href="<%=request.getContextPath()%>/add-utility-shifting"
									        class="btn waves-effect waves-light bg-s t-c"> <strong><i
									            class="fa fa-plus-circle"></i> Add Utility Shifting</strong></a>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Work</p>
										<select id="work_id_fk" name="work_id_fk" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Location</p>
										<select id="location_fk" name="location_fk" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Category</p>
										<select id="category_fk" name="category_fk" class="searchable">
											<option value="">Select</option>											
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Utility Type</p>
										<select id="utility_type_fk" name="utility_type_fk" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l2 input-field">
										<p class="searchable_label">Status</p>
										<select id="status_fk" name="status_fk" class="searchable">
											<option value="">Select</option>											
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
									<table id="datatable-us" class="mdl-data-table">
										<thead>
											<tr>
												<th class="fw-200">ID</th>
												<th>description</th>
												<th>utility type</th>
												<th>category</th>
												<th>owner</th>
												<th>execution agency</th>
												<th>status</th>
												<th class="no-sort">Action</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>												
												<td></td>
												<td></td>
												<td></td>
											</tr>
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
											<span>Attachment</span> <input type="file" id="USFile"
												name="USFile" required="required">
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
									style="width: 100%;" onclick="closeUploadUSModal();">Cancel</button>
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
  		<input type="hidden" name="randr_id" id="randr_id"/>
    </form>
    
  <form action="<%=request.getContextPath()%>/export-randr" name="exportRandRForm" id="exportRandRForm" target="_blank" method="post">	
        <input type="hidden" name="location_fk" id="exportLocation_fk" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
        <input type="hidden" name="phase_fk" id="exportPhase_fk" />
        <input type="hidden" name="status_fk" id="exportStatus_fk" />
        <input type="hidden" name="structure_type_fk" id="exportStructure_type_fk" />
        <input type="hidden" name="type_of_fk" id="exportType_of_fk" />
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
		
		function openUploadUSModal() {
			$("#USFile").val('');
			$("#upload_template").modal('open');
		}

		function closeUploadUSModal() {
			$("#USFile").val('');
			$("#upload_template").modal('close');
		}

		$(document).ready(function() {
			$('.modal').modal();
			$('select:not(.searchable)').formSelect();
			$('.searchable').select2();
			
			var filters = window.localStorage.getItem("USFilters");
	                   
			$('.close-message').delay(3000).fadeOut('slow');

		     $('#datatable-us').DataTable({
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
					},
	            });			
		});

		function clearFilter() {
			$("#work_id_fk").val('');
			$("#location_fk").val('');
			$("#category_fk").val('');
			$("#utility_type_fk").val('');
			$("#status_fk").val('');
			$('.searchable').select2();
		}
            
    </script>
</body>
</html>