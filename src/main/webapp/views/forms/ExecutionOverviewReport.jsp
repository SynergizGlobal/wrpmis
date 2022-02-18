<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Execution Overview Report - PMIS</title>
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
    	thead tr th{
    		padding-left:15px !important;
    		padding-right: 40px !important;
    	}
    	th.sorting_asc::after, th.sorting_asc::before{ 
    		content:"" !important;
    	} 
    	td:last-child, td:last-of-type{
    		white-space: initial;
    	}   
    	.w300{
    		width: 350px !important;
    	}  
    	.mdl-data-table td:first-of-type, .mdl-data-table th:first-of-type {
    		padding-left: 10px;
		}
		thead>tr>th.sorting{
			padding-left: 7px;
			padding-right: 35px !important;
		}
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
			
		} 
				
		.m-n1 {
	        margin: -2rem auto 0;
	    }
		@media(max-width: 1366px){
			thead tr th{
				padding-left: 6px !important;
			}
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
	    
	    .hide-column {
		    display : none;
		}
	    .center-column {
		    text-align:center;
		}
		
		.fw-230{
        	width:20% !important;
        	/* min-width:230px !important; */
        }
        
        .fw-250{
        	width:250px !important;
        	min-width:250px !important;
        }
                

		.legends {
            padding: 2px;
        }

        .box,
        .description {
            display: inline-block;
            margin-left: 3px;
            margin-right: 3px;
            vertical-align: middle;
        }

        .box {
            width: 20px;
            height: 20px;
            border-radius:50%;
            background-color: #fff;
            border: 1px solid #ccc;
        }

        .box.not-started {
            background-color: #808080;
        }

        .box.in-progress {
            background-color: #FFFF00;
        }

        .box.completed {
            background-color: #05a705;
        }

        .box.delayed {
            background-color: #f00;
        }

        @media only screen and (max-width: 768px) {
           .fixed-width .table-inside {
	    		overflow: hidden;
			}
        }
        @media only screen and (max-width: 700px) {
            .legends .col {
                text-align: left;
            }
        }
        @media(max-width: 575px){
        .row .col{margin: 10px auto}
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
								<h6 class="mob-mar">Execution Overview Report </h6>	
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="javascript:exportExecutionOverviewReport();" class="btn waves-effect waves-light bg-s t-c"> 
    									<strong><i class="fa fa-arrow-circle-down v-align-mid"></i> Download</strong>
    									</a>										
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
										<select id="work_id_fk" name="work_id_fk" class="searchable" onChange="getExecutionOverviewReportList();">
											<option value="">Select</option>										
										</select> 
									</div>		
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Department / HOD</p>
										<select id="department_fk" name="department_fk" class="searchable" onChange="getExecutionOverviewReportList();">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label">Contract ID</p>
										<select id="contract_id_fk" name="contract_id_fk" class="searchable" onChange="getExecutionOverviewReportList();">
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
						
						<div class="col m12 s12" id="divCollapase">
						
	
	
							<ul class="collapsible">

						    </ul>					
						
							<!-- <table id="datatable-execution-overview-report" class="mdl-data-table">
								<thead>
									<tr>
										<th>S No</th>
										<th>Structure Type</th>
										<th>Description</th>
										<th>Unit</th>
										<th>Scope</th>
										<th>Completed</th>
										<th>Pending</th>
										<th>Last Updated on</th>
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
							</table> -->
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
    <form action="<%=request.getContextPath() %>/export-execution-overview-report" name="exportExecutionOverviewReport" id="exportExecutionOverviewReport" target="_blank" method="post">	
        <input type="hidden" name="department_fk" id="exportDepartment_fk" />
        <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
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
    var pageNo = window.localStorage.getItem("executionOverviewReportPageNo");
    $(document).ready(function () {
    	$('.collapsible').collapsible();
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
            
     /*--------    can remove from here after linked to backend   -----------*/
        /*   $('#datatable-execution-overview-report').DataTable({
               columnDefs: [
                   {
                       targets: [0],
                       className: 'mdl-data-table__cell--non-numeric',
                       targets: 'no-sort', orderable: false,
                   },
                   { "width": "20px", "targets": [7] },
               ],
               "sScrollX": "100%",
               "sScrollXInner": "100%",
               "ordering": false,
               "bScrollCollapse": true,
               fixedHeader: true,
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
           }); */
           
           /*--------   can remove upto here after linked to backend   -----------*/      
     
           var filters = window.localStorage.getItem("executionOverviewReprtFilter");
         
           if($.trim(filters) != '' && $.trim(filters) != null){
       	   var temp = filters.split('^'); 
       	   for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'department_fk'){
		        		  getDepartmentFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_id_fk'){
		        		  getContractIdFilterList(temp2[1]);
		        	  }
	        	  }
	          }
           } 
    	   $('.close-message').delay(3000).fadeOut('slow');
    	
    	   getExecutionOverviewReportList();
    	
    });
    
    
    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#contract_id_fk").val("");
    	$("#department_fk").val("");    	
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("executionOverviewReprtFilter",'');
    	window.location.href= "<%=request.getContextPath()%>/execution-overview-report";

    }
        
    function getExecutionOverviewReportList(){
    	$(".page-loader-2").show();
    	$('#divCollapase ul').html("");
    	getDepartmentFilterList('');
    	getContractIdFilterList('');
    	getWorkFilterList('');
    	    	
    	var work_id_fk = $("#work_id_fk").val();
    	var department_fk = $("#department_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("executionOverviewReprtFilter", filters);
		});

		var StructureTypeArray=new Array(); 
	 
	 	var myParams = { work_id_fk : work_id_fk,department_fk : department_fk,contract_id_fk: contract_id_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getExecutionOverviewReportList",type:"POST",data:myParams,async: true,success : function(data){    				
				if(data != null && data != '' && data.length > 0){  
					var CheckLp=1;
	         		$.each(data,function(key,val)
	         				{
	         			
		                   		if(StructureTypeArray.indexOf(val.structure_type_fk)==-1)
		                   		{   
		                   				StructureTypeArray.push(val.structure_type_fk);
					         			var html="<li>";
					                    html=html+'<div class="collapsible-header"  style="background-color:#007A7A;color:#ffffff;"><span>'+CheckLp+'</span><span style="margin-right:70px;"></span><span>'+val.structure_type_fk+'</span></div>';
					                    html=html+'<div class="collapsible-body"><span>';
				
				                    	html=html+'<table id="datatable-execution-overview-report" class="mdl-data-table">'+
										'<thead>'+
											'<tr>'+
												'<th>Description</th>'+
												'<th>Unit</th>'+
												'<th>Scope</th>'+
												'<th>Completed</th>'+
												'<th>Pending</th>'+
												'<th>Last Updated on</th>'+
											'</tr>'+
										'</thead>'+
										'<tbody>';
				    	         		$.each(data,function(key1,val1)
				    	         				{
				    	         					if(val.structure_type_fk==val1.structure_type_fk)
				    	         						{
						    	         					html=html+'<tr>';
						    	         						html=html+'<td>'+$.trim(val1.strip_chart_structure_id)+'</td>';
						    	         						html=html+'<td>'+val1.unit_fk+'</td>';
						    	         						html=html+'<td>'+val1.scope+'</td>';
						    	         						html=html+'<td>'+val1.completed+'%</td>';
						    	         						html=html+'<td>'+val1.pending+'%</td>';
						    	         						html=html+'<td>'+val1.modified_date+'</td>';
						    	         					html=html+'</tr>';
				    	         						}
				    	         				});
				    	         		html=html+'</tbody></table></span></div>';
				    	         		$('.collapsible').append(html);
				    	         		CheckLp++;
				    	         		
		                   		}
		                   		
		                   		
							});
	         		
	         		$(".page-loader-2").hide();

	         			
				}
	         		
			}
		});
    }

       
	 function getWorkFilterList(work){
		 	$(".page-loader").show();
		 	
	    	var work_id_fk = $("#work_id_fk").val();
	    	
	    	var department_fk = $("#department_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	
		    if ($.trim(work_id_fk) == "") {
		    	$("#work_id_fk option:not(:first)").remove();
			 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInEOR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var workShortName = '';
	                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
	                             var selectedFlag = (work == val.work_id)?'selected':'';
	                             if(data.length == 1 ){
	                            	 selectedFlag = 'selected';
	                             }
		                         $("#work_id_fk").append('<option value="' + val.work_id + '"'+selectedFlag+'>' + $.trim(val.work_id)   + workShortName +'</option>');
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
    
	 function getDepartmentFilterList(department){
		 	$(".page-loader").show();
		 	
	    	var work_id_fk = $("#work_id_fk").val();
	    	
	    	var department_fk = $("#department_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	
		    if ($.trim(department_fk) == "") {
		    	$("#department_fk option:not(:first)").remove();
			 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getDepartmentFilterListInEOR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var departmentHOD = '';
	                             if ($.trim(val.department_name) != '') { departmentHOD =  $.trim(val.department_name) }
	                             var selectedFlag = (department == val.department_name)?'selected':'';
	                             if(data.length == 1 ){
	                            	 selectedFlag = 'selected';
	                             }
		                         $("#department_fk").append('<option value="' + val.department_name + '"'+selectedFlag+'>' + $.trim(val.department)   + departmentHOD +'</option>');
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
 
	 
	 function getContractIdFilterList(ContractId){
		 	$(".page-loader").show();
		 	
	    	var work_id_fk = $("#work_id_fk").val();
	    	
	    	var department_fk = $("#department_fk").val();
	    	var contract_id_fk = $("#contract_id_fk").val();
	    	
		    if ($.trim(contract_id_fk) == "") {
		    	$("#contract_id_fk option:not(:first)").remove();
			 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getContractIdFilterListInEOR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var contractShortName = '';
	                             if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
	                             var selectedFlag = (contractShortName == val.contract_id)?'selected':'';
	                             if(data.length == 1 ){
	                            	 selectedFlag = 'selected';
	                             }
		                         $("#contract_id_fk").append('<option value="' + val.contract_id + '"'+selectedFlag+'>' + $.trim(val.contract_id)   + contractShortName +'</option>');
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
    
    function exportExecutionOverviewReport(){
    	 var department_fk = $("#department_fk").val();
     	 var work_id_fk = $("#work_id_fk").val();
     	 var contract_id_fk = $("#contract_id_fk").val();
     	 
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportDepartment_fk").val(department_fk);
     	 $("#exportContract_id_fk").val(contract_id_fk);
     	
     	 $("#exportExecutionOverviewReport").submit();
  	}
    
    </script>

</body>

</html>