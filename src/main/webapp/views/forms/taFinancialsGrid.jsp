<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TA Financials - Update Forms - PMIS</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/finance.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">	
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        .row.no-mar {
            margin-bottom: 0;
        }
        .input-field .searchable_label {
            font-size: 0.85rem;
        }       
       .fw-300{
	       	width:300px;
	       	min-width:300px;
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

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<%-- <div class="col s12 m12 hide-on-med-and-down ">
			<div class="card">
				<div class="card-content">
					<span class="card-title headbg">
						<div class="center-align bg-m p-2 m-b-5">
							<h6>TA Financials</h6>
						</div>
					</span>
					<c:if test="${not empty success }">
						<div class="center-align m-1 close-message">${success}</div>
					</c:if>
					<c:if test="${not empty error }">
						<div class="center-align m-1 close-message">${error}</div>
					</c:if>
					<div class="">

						<div class="row center-align">
							<div class="col s12 m4 "></div>

							<div class="col s12 m4 c-align">
								<a href="<%=request.getContextPath()%>/add-ta-financials-form"
									class="btn waves-effect waves-light bg-s t-c"> <strong><i
										class="fa fa-plus-circle"></i> Add TA Financials</strong></a>
							</div>

							<div class="col s12 m4"></div>
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
								<!-- <h6 class="hide-on-med-and-down">Update TA Financial</h6> -->
								<h6 class="mob-mar">TA Financial</h6>
								<div class="col s12 m12 right-align exportButton">
								    <div class="m-n1">
								    	<a href="<%=request.getContextPath()%>/add-ta-financials-form"
									class="btn waves-effect waves-light bg-s t-c"> <strong><i
										class="fa fa-plus-circle"></i> Add </strong></a>
								    </div>
							    </div>
							</div>
						</span>
						<div class="row no-mar" >
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
							<div class="col s12 m10 offset-m1 l6 offset-l3">
								<div class="row" style="margin-bottom: 0;">
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Work</p>
										<select class="searchable" name="work_id_fk" id="work_id_fk"
											onchange="addInQueWork(this.value);getTAFinancialList();">
											<option value="" >Select</option>
										</select>
									</div>
									<div class="col s6 m4 input-field">
										<p class="searchable_label">Contract</p>
										<select class="searchable" name="contract_id_fk"
											id="contract_id_fk" onchange="addInQueContract(this.value);getTAFinancialList();">
											<option value="" >Select</option>

										</select>
									</div>
									<div class="col s12 m4 input-field center-align">
										<button
											class="btn bg-m waves-effect waves-light t-c clear-filters"
											style="margin-top: 6px; width: 100%;"
											onclick="clearFilter();">Clear Filters</button>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col m12 s12">
								<table id="tafinancials" class="mdl-data-table">
									<thead>
										<tr>
											<th class="fw-300 no-sort">Work</th>
											<th class="fw-300">Contract</th>
											<th>Planned Invoicing <br> Till Date
											</th>
											<th>Actual Invoicing <br>Till Date
											</th>
											<th>Payment Received <br>Till Date
											</th>
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

    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <!-- footer  -->


    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="ID" id="ID" />
    </form>

    <script>
    
    	var filtersMap = new Object();
    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            var filters = window.localStorage.getItem("taFinancialsFilters");
	          
            if($.trim(filters) != '' && $.trim(filters) != null){
        	  var temp = filters.split('^'); 
        	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'work_id_fk' ){
		        		  getWorksFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractsFilterList(temp2[1]);
		        	  }
	        	  }
	          }
            }
            getTAFinancialList();
        });

        function clearFilter(){
        	$("#work_id_fk").val("");
        	$("#contract_id_fk").val("");
        	$('.searchable').select2();
        	window.localStorage.setItem("taFinancialsFilters",'');
        	window.location.href="<%=request.getContextPath()%>/ta-financials";
        	var table = $('#tafinancialst').DataTable();
        	table.draw( true );

        	//getTAFinancialList();
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
        function getTAFinancialList() {
			$(".page-loader-2").show();

			getWorksFilterList('');
         	getContractsFilterList('');
         	
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("taFinancialsFilters", filters);
   			});
       	 	table = $('#tafinancials').DataTable();
   			table.destroy();

   			$.fn.dataTable.moment('DD-MMM-YYYY');
   			var rowLen = 0;
   			var myParams =  "work_id_fk="
   					+ work_id_fk + "&contract_id_fk="
   					+ contract_id_fk;

   			/***************************************************************************************************/

   			$("#tafinancials")
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
   									rowLen = $('#tafinancials tbody tr:visible').length
    								if(rowLen <= 1 &&  queue == 1){									
    									$('#tafinancials').dataTable().api().draw(); 
    									getTAFinancialList();
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
   								},{targets: [2,3,4], className: 'hideCOl'},{ targets: [0,1], className: 'fw-111'  } ],
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
   								"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-ta-financials?"+myParams,
   			        "aoColumns": [
   	  		            { "mData": function(data,type,row){
   	  		            	var work_short_name = '';
   	                         if ($.trim(data.work_short_name) != '') { work_short_name = ' - ' + $.trim(data.work_short_name) }    	
   	                         if($.trim(data.work_id_fk) == ''){ return '-'; }else{ return data.work_id_fk +work_short_name; }
   	  		            } },
   	  		         	{ "mData": function(data,type,row){
   	  		         		var contract_short_name = '';
   	                         if ($.trim(data.contract_short_name) != '') { contract_short_name = ' - ' + $.trim(data.contract_short_name) }    	
   	                         if($.trim(data.contract_id_fk) == ''){ return '-'; }else{ return data.contract_id_fk +contract_short_name; }
   	  		            } },
   			            { "mData": function(data,type,row){
   			            	if($.trim(data.planned) == ''){ return '-'; }else{ return data.planned; }
   			            } },
   			         	{ "mData": function(data,type,row){
   			            	if($.trim(data.actual) == ''){ return '-'; }else{ return data.actual; }
   			            } },
   			            { "mData": function(data,type,row){
   			            	if($.trim(data.payment_received) == ''){ return '-'; }else{ return data.payment_received; }
   			            } },
   			         	{ "mData": function(data,type,row){ 
   			         		var ID = "'"+data.financial_id+"'";
   		                    var actions = '<a href="javascript:void(0);"  onclick="getTAFinancials('+ID+');" class="btn waves-effect waves-light bg-m t-c mob-btn" ><i class="fa fa-pencil"></i></a>';
   			            	return actions;
   			            } }
   			            
   			        ]
   			    });
		  $(".page-loader-2").hide();  		     
      	
     }

        
        function getTAFinancialList1(){
        	$(".page-loader-2").show();
        	
        	getWorksFilterList('');
         	getContractsFilterList('');
         	
        	var work_id_fk = $("#work_id_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	
        	var filters = '';
        	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
        		filters = filters + key +"="+filtersMap[key] + "^";
        		window.localStorage.setItem("taFinancialsFilters", filters);
   			});
        	table = $('#tafinancials').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#tafinancials').DataTable({
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
    	 	var myParams = {work_id_fk : work_id_fk, contract_id_fk : contract_id_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-ta-financials",
    	 		type:"POST",
				data:myParams, cache: false,async:false,
				success : function(data){    
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var ID = "'"+val.financial_id+"'" ;
                        var actions = '<a href="javascript:void(0);"  onclick="getTAFinancials('+ID+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
    /*                     			  +'<a onclick="deleteTAFinancials('+ID+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
     */                   	var rowArray = [];    	                 
                       	
                    	var workName = '';
                        if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                        var contractName = '';
                        if ($.trim(val.contract_short_name) != '') { contractName = ' - ' + $.trim(val.contract_short_name) }
                        
                       	rowArray.push($.trim(val.work_id_fk) + workName);
                       	rowArray.push($.trim(val.contract_id_fk) + contractName);
                       	rowArray.push($.trim(val.planned));
                       	rowArray.push($.trim(val.actual));
                       	rowArray.push($.trim(val.payment_received));
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
            var contract_id_fk = $("#contract_id_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
            	var myParams = {contract_id_fk : contract_id_fk,work_id_fk: work_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInTAFinancials",
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
        
        function getContractsFilterList(contract) {
        	$(".page-loader").show();
            var contract_id_fk = $("#contract_id_fk").val();
            var work_id_fk = $("#work_id_fk").val();
            if ($.trim(contract_id_fk) == "") {
            	$("#contract_id_fk option:not(:first)").remove();
            	var myParams = {contract_id_fk : contract_id_fk,work_id_fk: work_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContractsFilterListInTAFinancials",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var contractShortName = '';
                                 if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
                                 var selectedFlag = (contract == val.contract_id_fk)?'selected':'';
    	                         $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '" '+selectedFlag+'>' + $.trim(val.contract_id_fk)   + contractShortName +'</option>');
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
        
        function getTAFinancials(ID){
        	$("#ID").val(ID);
        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-ta-financials');
        	$('#getForm').submit();
        }
        function deleteTAFinancials(ID){
        	$("#ID").val(ID);
        	showCancelMessage();
        }
    </script>

</body>

</html>