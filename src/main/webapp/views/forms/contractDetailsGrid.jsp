<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract Details Grid- Update Forms - PMIS</title>
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
    	/*  .fw-250{
    	 	width:250px !important;
    	 	max-width:250px;
    	 }
    	 .fw-150{
    	 	width:150px !important;
    	 	max-width:150px;
    	 }  */
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
         .no-wrap{
         	white-space:nowrap;
         }
         @media only screen and (max-width: 1366px) and (min-width:1023px){ 
         	tbody td{
         		padding:12px 10px !important;
         	}
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
			/* .fw-2{
				width:45vw !important;
        		max-width:45vw;  
        	}
        	fw-1{
				width:30vw !important;
        		max-width:30vw; 
        	}  */
			
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
    .v-align-mid::before{
    	vertical-align:middle;
    }
    </style>
</head>

<body>
    <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->
		
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Contract</h6> -->
								<h6 class="mob-mar">Contract Details</h6>	
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="add-contract-form"
											class="btn waves-effect waves-light bg-s t-c"> <strong><i
												class="fa fa-arrow-circle-down v-align-mid"></i> Download</strong></a>										
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
							<div class="col m12 l8 offset-l2 s12">
								<div class="row no-mar">
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Work ID</p>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getContractDetailList();" class="searchable">
											<option value="">Select</option>										
										</select> 
									</div>		
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Contract Status</p>
										<select id="contract_status" name="contract_status"
											onchange="addInQueContractStatus(this.value);getContractDetailList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Department</p>
										<select id="department_fk" name="department_fk"
											onchange="addInQueStatus(this.value);getContractDetailList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
								
								<div class="col s6 m12 l3 center-align">  
									<button class="btn bg-m waves-effect waves-light t-c"
										style="margin-top: 6px;" onclick="clearFilter();">Clear
										Filters</button>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col m12 s12">

							<table id="datatable-contract-details" class="mdl-data-table">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Contract</th>
										<th>Contract Value</th>
										<th>Expenditure</th>
										<th>Physical Progress</th>
										<th>LOA Date</th>
										<th>Completion Date</th>
										<th>Remarks</th>
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

		<div class="page-loader-2" style="display: none;">
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
 	
	<form action="<%=request.getContextPath()%>/get-contract" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="contract_id" id="contract_id"/>
    </form>
    <form action="<%=request.getContextPath() %>/export-contract" name="exportContractDetailForm" id="exportContractDetailForm" target="_blank" method="post">	
        <input type="hidden" name="department" id="exportDepartment" />
        <input type="hidden" name="contract_status" id="exportContract_status" />
        <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
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
    var pageNo = window.localStorage.getItem("contractPageNo");
    $(document).ready(function () {
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
           
          /*  $('#datatable-contract-details1').DataTable({
        	   columnDefs: [
                   {
                       targets: [0],
                       className: 'mdl-data-table__cell--non-numeric',
                       targets: 'no-sort', orderable: false,
                   },
                  // { "width": "20px", "targets": [7] },
               ],
               "sScrollX": "100%",
               "sScrollXInner": "100%",
               "ordering": false,
               "bScrollCollapse": true,
               fixedHeader: true,               
               initComplete : function() {
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
           }); */
           
           var filters = window.localStorage.getItem("contractDetailFilters");
         
           if($.trim(filters) != '' && $.trim(filters) != null){
       	  var temp = filters.split('^'); 
       	  for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'project_id_fk' ){
		        		  getProjectFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'designation'){
		        		  getDesignationFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'dy_hod_designation'){
		        		  getDyHODDesignationFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contractor_id_fk'){
		        		  getContractorsFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_status'){
		        		  getContractStatusFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_status_fk'){
		        		  getStatusFilterList(temp2[1]);
		        	  }
	        	  }
	          }
           } 
    	$('.close-message').delay(3000).fadeOut('slow');
    	
    	//getContractDetailList();
    	
    });
    
    
    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#contract_status").val("");
    	$("#department").val("");    	
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("contractFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/contract";
    	
    	var table = $('#datatable-contract-details').DataTable();
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
    function addInQueDepartment(department){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('department')) delete filtersMap[key];
   		});
    	if($.trim(designation) != ''){
   	    	filtersMap["department"] = designation;
    	}
    }
  
    function addInQueContractStatus(contract_status){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('contract_status')) delete filtersMap[key];
   		});
    	if($.trim(contract_status) != ''){
   	    	filtersMap["contract_status"] = contract_status;
    	}
    }
 
    
    function getContractDetailList(){
    	$(".page-loader-2").show();
    	/* getDepartmentFilterList('');
    	getContractStatusFilterList('');
    	getWorkFilterList(''); */
    	    	
    	var work_id_fk = $("#work_id_fk").val();
    	var department = $("#designation").val();
    	var contract_status = $("#contract_status").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("contractDetailFilters", filters);
		});
     	table = $('#datatable-contract-details').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-contract-details').DataTable({
			"bStateSave": true,  
     		fixedHeader: true,
           
         	//Default: Page display length
				"iDisplayLength" : 10,
				"iData" : {
					"start" : 52
				},"iDisplayStart" : 0,
				"drawCallback" : function() {
					var info = table.page.info();
					window.localStorage.setItem("contractDetailPageNo", info.page);
				},
            columnDefs: [
                {
                    targets: [0, 1, 2],
                    className: 'mdl-data-table__cell--non-numeric'
                },
                { orderable: false, 'aTargets': ['nosort'] }
            ],
            // "ScrollX": true,
            //"scrollCollapse": true,
            "sScrollX": "100%",
            "sScrollXInner": "100%",
            "bScrollCollapse": true,
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
	 
	 	var myParams = { work_id_fk : work_id_fk,department : department,contract_status: contract_status};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getContractDetail",type:"POST",data:myParams,success : function(data){    				
				if(data != null && data != '' && data.length > 0){    					
	         		$.each(data,function(key,val){
	         			var contract_id = "'"+val.contract_id+"'";
	                   // var actions = '<a href="javascript:void(0);"  onclick="getContractDetail('+contract_id+');" class="btn waves-effect waves-light bg-m t-c" title="Edit"><i class="fa fa-pencil"></i></a>';    	                   	
	                   	var rowArray = [];    	                 
	                   	
	                	//var workName = '';
                       // if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                        
	                   	rowArray.push($.trim(val.work_id_fk));
	                   	rowArray.push($.trim(val.contract_id));
	                   	rowArray.push($.trim(val.contract_short_name));
	                   	rowArray.push($.trim(val.contractor_name));
	                   	rowArray.push($.trim(val.department_name));
	                   	rowArray.push($.trim(val.designation));
	                   	rowArray.push($.trim(val.dy_hod_designation));
	                   	rowArray.push($.trim(val.modified_date));
	                   	//rowArray.push($.trim(actions));   	                   	
	                   	
	                    table.row.add(rowArray).draw( true );
					});
	         		if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
	                var oTable = $('#datatable-contract-details').dataTable();
	                oTable.fnPageChange( pageNo );
	         		$(".page-loader-2").hide();
				}else{
					$(".page-loader-2").hide();
				}
				
			},error: function (jqXHR, exception) {
				$(".page-loader-2").hide();
	         	getErrorMessage(jqXHR, exception);
	     }});
    } 

    
    var queue = 1;
        
	 function getDepartmentFilterList(){
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var department = $("#department").val();
	    	var contract_status = $("#contract_status").val();
	        if ($.trim(department_fk) == "") {
	        	$("#department_fk option:not(:first)").remove();
	    	 	var myParams = { department_fk : department_fk, work_id_fk : work_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getDepartmentsFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
		                           $("#department_fk").append('<option value="' + val.department_fk + '">' + $.trim(val.department_name)   +'</option>');
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
	 
	 function getContractStatusFilterList(contractStatus){
		 	$(".page-loader").show();
		 	var work_id_fk = $("#work_id_fk").val();
	    	var department = $("#department").val();
	    	var contract_status = $("#contract_status").val();
		    if ($.trim(contract_status) == "") {
		    	$("#contract_status option:not(:first)").remove();
			 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getContractStatusFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (contractStatus == val.contract_status)?'selected':'';
	                        	 $("#contract_status").append('<option value="' + val.contract_status + '"'+selectedFlag+'>' + $.trim(val.contract_status)+'</option>');
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
	 
	 function getStatusFilterList(status){
		 	$(".page-loader").show();
		 	var work_id_fk = $("#work_id_fk").val();
	    	var department = $("#department").val();
	    	var contract_status = $("#contract_status").val();
		    if ($.trim(contract_status_fk) == "") {
		    	$("#contract_status_fk option:not(:first)").remove();
			 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_status : contract_status};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (status == val.contract_status)?'selected':'';
	                        	 $("#contract_status").append('<option value="' + val.contract_status + '"'+selectedFlag+'>' + $.trim(val.contract_status)+'</option>');
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
	 
	 function getWorkFilterList(work){
	 	$(".page-loader").show();
	 	
    	var work_id_fk = $("#work_id_fk").val();
    	var department = $("#department").val();
    	var contract_status = $("#contract_status").val();
    	
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
		 	var myParams = {designation : designation,dy_hod_designation : dy_hod_designation,contractor_id_fk : contractor_id_fk, contract_status_fk : contract_status_fk, work_id_fk : work_id_fk, project_id_fk : project_id_fk, contract_status : contract_status};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInContract",
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

    function getContractDetail(contract_id) {
		$("#contract_id").val(contract_id);
		$("#getForm").submit();
	}
    
    function exportContractDetail(){
    	 var department = $("#department").val();
     	 var work_id_fk = $("#work_id_fk").val();
     	 var contract_status = $("#contract_status").val();
     	 
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportDepartment").val(department);
     	 $("#exportContract_status").val(contract_status);
     	
     	 $("#exportContractDetailForm").submit();
  	}
    
    </script>

</body>

</html>