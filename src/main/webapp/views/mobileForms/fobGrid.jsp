<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FOB - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/wrpmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
	<link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
	
	<link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"	rel="stylesheet">
	<!-- <link rel="stylesheet" href="/wrpmis/resources/css/fob.css"> -->
	<link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
	<link rel="stylesheet" href="/wrpmis/resources/css/header-footer.css">
	<link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-grid-template.css" />
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
     
    
    </style>
</head>
<body>
	<!-- header included -->
	<%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
	
		<c:if test="${(sessionScope.USER_TYPE eq 'DyHOD') or (sessionScope.USER_TYPE eq 'HOD') or (sessionScope.USER_ROLE_CODE eq 'IT')}">
			<div class="row no-mar" style="margin-bottom:0;">
			<div class="col s12 m12 hide-on-med-and-down ">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6>FOB</h6>
							</div>
						</span>
						<div class="">
							<div class="row plr-1 center-align">								
									<div class="col s12 m4 offset-m4">
										<div class="m-1 c-align">
											<a href="<%=request.getContextPath()%>/mobileappwebview/add-fob-form"
												class="btn waves-effect waves-light bg-s t-c"> <strong><i
													class="fa fa-plus-circle"></i> Add FOB</strong></a>
										</div>
									</div>
									<div class="col s12 m4 r-align hide-on-med-and-down">
										<div class="m-1 ">
											<a href="javascript:void(0);" onclick="exportFOB();"
												class="btn waves-effect waves-light bg-s t-c"> <strong><i
													class="fa fa-cloud-download"></i> Export Data</strong></a>
										</div>
									</div>
							 	
							</div>
						</div>
					</div>
				</div>
			</div>
		  </div>
		</c:if>
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6 class="hide-on-med-and-down">Update FOB</h6>
								<h6 class="hide-on-large-only">FOB</h6>
							</div>
						</span>
						
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>

						<div class="row no-mar">
							<div class="col s12 hide-on-large-only mb-md-2 center-align">
							    <a href="<%=request.getContextPath()%>/mobileappwebview/add-fob-form" class="btn waves-effect waves-light bg-s t-c"> <strong><i
							                class="fa fa-plus-circle"></i> Add FOB</strong></a>
							</div>
							<div class="col s12 l6 offset-l3 m9 offset-m2">
								<div class="row no-mar">
									<!-- <div class="col s6 m4 input-field">
										<p>
											<label>Contract</label>
										</p>
										<select id="contract_id_fk" name="contract_id_fk"
											onchange="addInQueContract(this.value);getFOBList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div> -->
									
									
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Work Status</p>
										<select id="work_status_fk" name="work_status_fk"
											onchange="addInQueWorkStatus(this.value);getFOBList();" class="searchable">
											<option value="">Select</option>

										</select>
									</div>
									<div class="col s12 m4 input-field center-align">
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
								<table id="datatable-fob" class="mdl-data-table">
									<thead>
										<tr>
											<th class="no-sort">Work</th>
											<!-- <th class="fw-400">Contract</th> -->
											<th>FOB ID</th>
											<th>FOB Name</th>
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
	<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

	<form action="<%=request.getContextPath()%>/mobileappwebview/get-fob" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="fob_id" id="fob_id"/>
    </form>
  
  
	<form action="<%=request.getContextPath()%>/mobileappwebview/export-fobs" name="exportFOBForm" id="exportFOBForm" target="_blank" method="post">	
        <input type="hidden" name="work_status_fk" id="exportWork_status_fk" />
	</form>

	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
	<script src="/wrpmis/resources/js/select2.min.js"></script>
	<script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	<script>
	
		var filtersMap = new Object();
	
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            var filters = window.localStorage.getItem("fobFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
  	        	  if($.trim(temp[i]) != '' ){
  	        		  var temp2 = temp[i].split('=');
  		        	  if($.trim(temp2[0]) == 'work_status_fk' ){
  		        		getWorkStatusFilterList(temp2[1]);
  		        	  }
  	        	  }
  	            }
              }
    		
        	
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	getFOBList();
        
        });
  /*   	if(window.matchMedia("(max-width: 769px)").matches){
        	$('tbody.web').removeAttr('id');
            $('#mobView').css({'display':'block'});
            $('#datatable-fob_mobile').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [2] },
                ],
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                "bAutoWidth": true,
                "ordering": false, //to stop sorting option                
                fixedHeader: true, // to change the language of data table	          
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
          	
        } else{
        	$('#webView').css({'display':'block'});
            $('#datatable-fob').DataTable({
	                columnDefs: [
	                    {
	                        targets: [0],
	                        className: 'mdl-data-table__cell--non-numeric',
	                        targets: 'no-sort', orderable: false,
	                    },
	                    { "width": "10px", "targets": [4] },
	                ],
	                "sScrollX": "100%",
	                "sScrollXInner": "100%",
	                "bScrollCollapse": true,
	                "bAutoWidth": true,
	                "ordering": false, //to stop sorting option                
	                fixedHeader: true, // to change the language of data table	          
	                initComplete: function () {
	                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	                }
	            });
        } */
        function clearFilter(){
        	$("#work_id_fk").val('');
        	$("#work_status_fk").val('');
        	$(".searchable").select2();
        	window.localStorage.setItem("fobFilters",'');
        	window.location.href="<%=request.getContextPath()%>/mobileappwebview/fob"
        	//getFOBList();
        	
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

        
        function getFOBList(){
        	$(".page-loader").show();
        	
        	getWorkStatusFilterList('');
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var work_status_fk = $("#work_status_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("fobFilters", filters);
   			});
       		table = $('#datatable-fob').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-fob').DataTable({
    			"bStateSave": true,
        		fixedHeader: true,
                "fnStateSave": function (oSettings, oData) {
                    localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
                },
                "fnStateLoad": function (oSettings) {
                    return JSON.parse(localStorage.getItem('MRVCDataTables'));
                },
                columnDefs: [ 
                	{targets: [2,3], className: 'hideCOl'},
                    { targets: [ 0,1,2, 3], className: 'fw-12vw'  },
                    { targets: [4], className: 'fw-10vw'  },
                    { targets: [0,1], className: 'fw-111'  },
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
  							.unbind(), self = this.api(), $searchButton = $('<i class="fa fa-search" title="Go">')
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
    	 
    	 	var myParams = {work_id_fk : work_id_fk, work_status_fk : work_status_fk};
    		$.ajax({url : "<%=request.getContextPath()%>/mobileappwebview/ajax/getFOBList",
	    			type:"POST",
	    			data:myParams,cache: false,async:false,
	    			success : function(data)
	    			{    	
    					if(data != null && data != '' && data.length > 0){    					
    	         		$.each(data,function(key,val){
    	         			var fob_id = "'"+val.fob_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getFOB('+fob_id+');" class="btn waves-effect waves-light bg-m t-c mob-btn" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
    	                   	var rowArray = [];    	                  
    	                   	var workName = '';
                            if ($.trim(val.work_short_name) != '') { workName =  $.trim(val.work_short_name) } 
    	                   	
    	                   	rowArray.push( workName);
    	                   	rowArray.push($.trim(val.fob_id));
    	                   	rowArray.push($.trim(val.fob_name)); 
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
    	    if ($.trim(work_status_fk) == "") {
    	    	$("#work_status_fk option:not(:first)").remove();
    	    	var myParams = {work_id_fk : work_id_fk,work_status_fk : work_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getWorkStatusFilterListInFOB",
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
        

        
         <%-- function getContractsFilterList(contract_id){
    	 	$(".page-loader").show();
    	 	var work_status_fk = $("#work_status_fk").val();
    	 	var contract_id_fk = $("#contract_id_fk").val();
    	    if ($.trim(contract_id_fk) == "") {
    	    	$("#contract_id_fk option:not(:first)").remove();
    	    	var myParams = {contract_id_fk : contract_id_fk,work_status_fk : work_status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/mobileappwebview/ajax/getContractsFilterListInFOB",
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
    	 } --%>
        
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
        
        
        function getFOB(fob_id) {
    		$("#fob_id").val(fob_id);
    		$("#getForm").submit();
    	}
        
        function exportFOB(){
          	 var work_id_fk = $("#work_id_fk").val();
          	 var work_status_fk = $("#work_status_fk").val();
          	 
          	 $("#exportWork_id_fk").val(work_id_fk);
          	 $("#exportWork_status_fk").val(work_status_fk);
          	 $("#exportFOBForm").submit();
       	}
        <%--         
        function getFOBList() {
    		$(".page-loader-2").show();
    		getWorkStatusFilterList('');
        	getContractsFilterList('');
        	
        	var contract_id_fk = $("#contract_id_fk").val();
        	var work_status_fk = $("#work_status_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("fobFilters", filters);
   			});
        	
         	table = $('#datatable-fob').DataTable();
    		table.destroy();

    		$.fn.dataTable.moment('DD-MMM-YYYY');

    		var myParams = "contract_id_fk=" + contract_id_fk + "&work_status_fk="
    				+ work_status_fk;

    		/***************************************************************************************************/

    		$("#datatable-fob")
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
    							"sAjaxSource" : "	<%=request.getContextPath()%>/mobileappwebview/ajax/getFOBList?"+myParams,
    		        "aoColumns": [
    		            { "mData": function(data,type,row){
    		            	var work_short_name = '';
                            if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
                         	if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk + work_short_name; }
            			} },   				            
    		            { "mData": function(data,type,row){
    		            	var contract_name = '';
                            if ($.trim(data.contract_short_name) != '') { contract_name = ' - ' + $.trim(data.contract_short_name) }
    		            	if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk + contract_name; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.fob_id) == ''){ return '-'; }else{ return data.fob_id; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.fob_name) == ''){ return '-'; }else{ return data.fob_name; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.work_status_fk) == ''){ return '-'; }else{ return data.work_status_fk; }
    		            } },
    		           
    		         	{ "mData": function(data,type,row){
    		         		var fob_id = "'"+data.fob_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getFOB('+fob_id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	    
    	  $(".page-loader-2").hide();  		     
      	
     }
         --%>

    </script>
</body>
</html>