<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Training - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/wrpmis/resources/css/mobile-grid-template.css" />
      <style>
        p a {
            color: blue;
        }
        thead th.p-2{
        	padding:inherit !important;
        	vertical-align:inherit;
        }
        .mdl-data-table td{
        	white-space:pre-line;
        	word-break:break-word;
        }    
        .input-field .searchable_label{
            font-size: 0.85rem;
        }
        .fw-300{
        	width:300px !important;
        	max-width:300px;
        }
        .fw-150{
        	width:150px !important;
        	max-width:150px;
        }
        .fw-70{
        	width:80px !important;
        	max-width:80px;
        }     
        .fw-50{
        	width:70px !important;
        	max-width:70px;
        }   
        tbody tr td:last-of-type,thead tr th:last-of-type{
        	white-space:inherit;
        	text-align:center !important;
        }
        tbody tr td:last-of-type a+a{
        	margin-left:20px;
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
			.fw-350{
				width:30vw !important;
        		max-width:30vw;
			 }
			}
			
			.no-sort.hideCOl.sorting_asc:before,
			.no-sort.hideCOl.sorting_asc:after{
				opacity:0;
				content:'';
			}
			
			    .m-n1 {
        margin: -2rem auto 0;
    }

    .template-btn {
        text-shadow: 1px 1px 1px black;
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
    @media(max-width: 575px){
    	.cw-s{width: 150px !important;}
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
							<h6>Training</h6>
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
							<div class="col s12 m4">
								<!-- <div class="m-1 l-align">
                                    <a href="javascript:void(0);" onclick="openUploadTrainingModal();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="/wrpmis/Training_Template.xlsx">here</a> for the template</p>
                                </div>  -->
							</div>

							<div class="col s12 m4">
								<div class="m-1 c-align">
									<a href="<%=request.getContextPath()%>/create-training"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Training</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportTraining();"
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
								<!-- <h6 class="hide-on-med-and-down">Update Training</h6> -->
								<h6 class="mob-mar">Training</h6>	
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="<%=request.getContextPath()%>/create-training"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add</strong></a>
											<a href="javascript:void(0);" onclick="exportTraining();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export</strong></a>
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
							<div class="col m1 hide-on-small-only"></div>
							<div class="col m10 s12 ">
								<div class="row" style="margin-bottom: 0;">
									<div class="col s6 m2 input-field offset-m1">
										<p class="searchable_label">Type</p>
										<select class="searchable" name="training_type_fk"
											id="training_type_fk" onchange="addInQueType(this.value);getTraningList();">
											<option value="">Select Type</option>

										</select>
									</div>
									<div class="col s6 m2 input-field">
										<p class="searchable_label">Category</p>
										<select class="searchable" name="training_category_fk"
											id="training_category_fk" onchange="addInQueCategory(this.value);getTraningList();">
											<option value="">Select Category</option>
										</select>
									</div>
									<div class="col s6 m2 input-field">
										<p class="searchable_label">Title</p>
										<select class="searchable" name="training_title_fk"
											id="training_title_fk" onchange="addInQueTitle(this.value);getTraningList();">
											<option value="">Select Title</option>
										</select>
									</div>
								<!-- 	<div class="col s6 m2 input-field">
										<p class="searchable_label">Status</p>
										<select class="searchable" name="status_fk" id="status_fk"
											onchange="addInQueStatus(this.value);getTraningList();">
											<option value="">Select Status</option>

										</select> 
									</div>  -->
									<div class="col s12 m2 center-align">
										<button
											class="btn bg-s waves-effect waves-light t-c clear-filters"
											onclick="clearFilter();"
											style="margin-top: 6px; width: 100%;">Clear Filters</button>
									</div>
								</div>
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>

						<div class="row">
							<div class="col m12 s12">
								<table id="datatable-training" class="mdl-data-table">
									<thead>
										<tr>
											<th class="no-sort fw-50">ID</th>
											<th>Type &nbsp; &nbsp;</th>
											<th>Category</th>
											<th>Title</th>
											<th>Periodicity</th>
											<th>Contract</th>
											<th class="nosort">Action</th>
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
        <div class="modal-content headbg">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Upload Users</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-training" id="trainingUploadForm" name="trainingUploadForm" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="trainingFile" name="trainingFile" required="required">
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
                                <button type="button" onclick="trainingFileSubmit();" class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">Update</button>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;" onclick="closeUploadTrainingModal();">Cancel</button>
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

    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="training_id" id="training_id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-training" name="exportTrainingForm" id="exportTrainingForm" target="_blank" method="post">	
         <input type="hidden" name="training_type_fk" id="exportTraining_type_fk" />
         <input type="hidden" name="training_category_fk" id="exportTraining_category_fk" />
         <input type="hidden" name="status_fk" id="exportStatus_fk" />
         <input type="hidden" name="training_title_fk" id="exportTraining_title_fk" />
	 </form>

    <script>
    
    	var filtersMap = new Object();
    
	    function  openUploadTrainingModal() {
	  		$("#trainingFile").val('');
	      	$("#upload_template").modal();
	      	$("#upload_template").modal('open');
	  	}
	
	  	function  closeUploadTrainingModal() {
	  		$("#trainingFile").val('');
	      	$("#upload_template").modal('close');
	  	}
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            var filters = window.localStorage.getItem("trainingFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
  	        	  if($.trim(temp[i]) != '' ){
  	        		  var temp2 = temp[i].split('=');
  		        	  if($.trim(temp2[0]) == 'training_type_fk' ){
  		        		getTrainingTypesFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'training_category_fk'){
  		        		getTrainingCategorysFilterList(temp2[1]);
  		        	  }else if($.trim(temp2[0]) == 'status_fk'){
  		        		getStatusFilterList(temp2[1]);
  		        	  }
  		        	else if($.trim(temp2[0]) == 'training_title_fk'){
  		        		getTrainingTitlesFilterList(temp2[1]);
  		        	  }  		        	  
  	        	  }
  	          }
            }
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
     
            getTraningList();
        });
        
        function clearFilter(){
        	$("#training_type_fk").val("");
        	$("#training_category_fk").val("");
        	$("#training_title_fk").val("");
        	$("#status_fk").val("");
        	$('.searchable').select2();
        	window.localStorage.setItem("trainingFilters",'');
        	//getTraningList();
        	window.location.href= "<%=request.getContextPath()%>/training";
        	var table = $('#datatable-training').DataTable();
        	table.draw( true );
        }
        
        function addInQueType(training_type_fk){
        	Object.keys(filtersMap).forEach(function (key) {
	   			if(key.match('training_type_fk')) delete filtersMap[key];
	   		});
        	if($.trim(training_type_fk) != ''){
       	    	filtersMap["training_type_fk"] = training_type_fk;
        	}
        }
        
        function addInQueCategory(training_category_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('training_category_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(training_category_fk) != ''){
            	filtersMap["training_category_fk"] = training_category_fk;
	      	}
        }
        
        function addInQueTitle(training_title_fk){
	      	Object.keys(filtersMap).forEach(function (key) {
		   		if(key.match('training_title_fk')) delete filtersMap[key];
	   	   	});
	      	if($.trim(training_title_fk) != ''){
            	filtersMap["training_title_fk"] = training_title_fk;
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
        
        function trainingFileSubmit(){
        	$(".page-loader").show();
        	$("#upload_template").modal();
        	$("#trainingUploadForm").submit();
        }
        
        var queue = 1;
        <%--  function getTraningList(){
        	$(".page-loader-2").show();
        	getTrainingTypesFilterList('');
         	getTrainingCategorysFilterList('');
         	getTrainingTitlesFilterList('');
         	getStatusFilterList('');
         	
        	var training_type_fk = $("#training_type_fk").val();
        	var training_category_fk = $("#training_category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var title = $("#training_title_fk").val();

        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("trainingFilters", filters);
   			});
         	
        	table = $('#datatable-training').DataTable();

			table.destroy();

			$.fn.dataTable.moment('DD-MMM-YYYY');
			var rowLen = 0;
			var myParams = "training_type_fk=" + training_type_fk + "&training_category_fk="
					+ training_category_fk +  "&status_fk=" + status_fk+  "&title=" + title;

			/***************************************************************************************************/

			$("#datatable-training")
					.DataTable(
							{
								"bProcessing" : true,
								"bServerSide" : true,
								"sort" : "position",
								//bStateSave variable you can use to save state on client cookies: set value "true" 
								"bStateSave" : false,
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
									rowLen = $('#datatable-training tbody tr:visible').length
    								if(rowLen <= 1 &&  queue == 1){									
    									$('#datatable-training').dataTable().api().draw(); 
    									getTraningList();
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
								} ,{targets:[0,1,4,5,6,7,8,9,10],
 			                       className: 'hideCOl'
 			                       },
 			                      {targets:[2],
 	 			                       className: 'cw-s'
 	 			                       }
								],
								"sScrollX" : "100%",
								"sScrollXInner" : "100%",
								"ordering":false,
								"bScrollCollapse" : true,
								"language" : {
									"info" : "_START_ - _END_ of _TOTAL_",
									paginate : {
										next : '<i class="fa fa-angle-right"></i>', // or '→'
										previous : '<i class="fa fa-angle-left"></i>' // or '←' 
									}
								},
								"bDestroy" : true,
								"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-training?"+myParams,
			        "aoColumns": [
			            { "mData": function(data,type,row){
	                     	if($.trim(data.training_id) == ''){ return '-'; }else{ return data.training_id; }
            			} },   				            
			            { "mData": function(data,type,row){
			            	if($.trim(data.training_type_fk) == ''){ return '-'; }else{ return data.training_type_fk; }
			            } },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.training_category_fk) == ''){ return '-'; }else{ return data.training_category_fk; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.description) == ''){ return '-'; }else{ return data.description; }
			            } },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.faculty_name) == ''){ return '-'; }else{ return data.faculty_name; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.start_time) == ''){ return '-'; }else{ return data.start_time; }
			            } },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.end_time) == ''){ return '-'; }else{ return data.end_time; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.hours) == ''){ return '-'; }else{ return data.hours; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.period_fk) == ''){ return '-'; }else{ return data.period_fk; }
			            } },
			            { "mData": function(data,type,row){
			            	if($.trim(data.no_of_Participants) == ''){ return '-'; }else{ return data.no_of_Participants; }
			            } },
			         	{ "mData": function(data,type,row){
			            	if($.trim(data.no_of_Absentees) == ''){ return '-'; }else{ return data.no_of_Absentees; }
			            } },
			         	{ "mData": function(data,type,row){
			         		var training_id = "'"+data.training_id+"'";
		                    var actions = '<a href="javascript:void(0);"  onclick="getTraining('+training_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
			            	return actions;
			            } }
			            
			        ]
			    });
		    
		  $(".page-loader-2").hide();  		     
      	
     }--%>

        function getTraningList(){
        	$(".page-loader-2").show();
        	getTrainingTypesFilterList('');
         	getTrainingCategorysFilterList('');
         	getTrainingTitlesFilterList('');
         	getStatusFilterList('');
         	
        	var training_type_fk = $("#training_type_fk").val();
        	var training_category_fk = $("#training_category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var title = $("#training_title_fk").val();

        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("trainingFilters", filters);
   			});
         	
        	table = $('#datatable-training').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-training').DataTable({
    			"order": [],
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
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'nosort', ordarable:false
                    },
                    { orderable: false, 'aTargets': ['nosort'] },
                    { "width": "20px", "targets": [6] },
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
    	 	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk,title:title};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-training-List",
    	 		type:"POST",
				data:myParams, cache: false,async:false,
				success : function(data){   
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var training_id = "'"+val.training_id+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getTraining('+training_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
                        	var rowArray = [];    	                                       	
                                          
                       	rowArray.push($.trim(val.training_id));
                       	rowArray.push($.trim(val.training_type_fk));
                       	rowArray.push($.trim(val.training_category_fk));
                    	rowArray.push($.trim(val.title));
                       	rowArray.push($.trim(val.period_fk));
                       	rowArray.push($.trim(val.contract_short_name_fk));
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
        
        
        
       
     	 function getTrainingTypesFilterList(type) {
         	$(".page-loader").show();
         	var training_type_fk = $("#training_type_fk").val();
        	var training_category_fk = $("#training_category_fk").val();
        	var status_fk = $("#status_fk").val();
        	var title = $("#training_title_fk").val();
        	
             if ($.trim(training_type_fk) == "") {
             	$("#training_type_fk option:not(:first)").remove();
             	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk,title:title};
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getTrainingTypesFilterListInTraining",
                     data: myParams, cache: false,async: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                            	   var selectedFlag = (type == val.training_type_fk)?'selected':'';
     	                           $("#training_type_fk").append('<option value="' + val.training_type_fk + '"'+selectedFlag+'>' + $.trim(val.training_type_fk) +'</option>');
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
     	 
     	 function getTrainingCategorysFilterList(category) {
          	$(".page-loader").show();
          	var training_type_fk = $("#training_type_fk").val();
         	var training_category_fk = $("#training_category_fk").val();
         	var status_fk = $("#status_fk").val();
         	var title = $("#training_title_fk").val();
         	
              if ($.trim(training_category_fk) == "") {
              	$("#training_category_fk option:not(:first)").remove();
              	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk,title :title};
                  $.ajax({
                      url: "<%=request.getContextPath()%>/ajax/getTrainingCategorysFilterListInTraining",
                      data: myParams, cache: false,async: false,
                      success: function (data) {
                          if (data.length > 0) {
                              $.each(data, function (i, val) {
                            	   var selectedFlag = (category == val.training_category_fk)?'selected':'';
      	                           $("#training_category_fk").append('<option value="' + val.training_category_fk + '"'+selectedFlag+'>' + $.trim(val.training_category_fk)  + '</option>');
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
     	 
     	 
     	 function getTrainingTitlesFilterList(title_fk) {
           	$(".page-loader").show();
           	var training_type_fk = $("#training_type_fk").val();
          	var training_category_fk = $("#training_category_fk").val();
          	var title = $("#training_title_fk").val();
          	var status_fk = $("#status_fk").val();
          	
               if ($.trim(title) == "") {
               	$("#training_title_fk option:not(:first)").remove();
               	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk, title : title};
                   $.ajax({
                       url: "<%=request.getContextPath()%>/ajax/getTrainingTitlesFilterListInTraining",
                       data: myParams, cache: false,async: false,
                       success: function (data) {
                           if (data.length > 0) {
                               $.each(data, function (i, val) {
                             	   var selectedFlag = (title_fk == val.title)?'selected':'';
       	                           $("#training_title_fk").append('<option value="' + val.title + '"'+selectedFlag+'>' + $.trim(val.title)  + '</option>');
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
     	 
     	 function getStatusFilterList(status) {
           	$(".page-loader").show();
           	var training_type_fk = $("#training_type_fk").val();
          	var training_category_fk = $("#training_category_fk").val();
          	var status_fk = $("#status_fk").val();
          	var title = $("#training_title_fk").val();
          	
               if ($.trim(status_fk) == "") {
               	$("#status_fk option:not(:first)").remove();
               	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk,title:title};
                   $.ajax({
                       url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInTraining",
                       data: myParams, cache: false,async: false,
                       success: function (data) {
                           if (data.length > 0) {
                               $.each(data, function (i, val) {
                            	   var selectedFlag = (status == val.status_fk)?'selected':'';
       	                           $("#status_fk").append('<option value="' + val.status_fk + '"'+selectedFlag+'>' + $.trim(val.status_fk)  + '</option>');
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
     	 
         function getTraining(training_id){
         	$("#training_id").val(training_id);
         	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-training');
         	$('#getForm').submit();
         }
         
         function getTrainingDetails(training_id){
          	$("#training_id").val(training_id);
          	$('#getForm').attr('action', '<%=request.getContextPath()%>/export-training-details');
          	$('#getForm').submit();
          }
         function deleteTraining(training_id){
         	$("#training_id").val(training_id);
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
                 	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-training');
         	    	$('#getForm').submit();
                }else {
                     swal("Cancelled", "Record is safe :)", "error");
                 }
             });
         }
         
         function exportTraining(){
        	 var training_type_fk = $("#training_type_fk").val();
             var training_category_fk = $("#training_category_fk").val();
        	 var status_fk = $("#status_fk").val();
        	 $("#exportTraining_type_fk").val(training_type_fk);
        	 $("#exportTraining_category_fk").val(training_category_fk);
        	 $("#exportStatus_fk").val(status_fk);
        	 $("#exportTrainingForm").submit();
     	}
         
    </script>

</body>

</html>