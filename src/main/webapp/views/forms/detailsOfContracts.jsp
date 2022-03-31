<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Details of Contracts - PMIS</title>
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
    	.bg-red{background-color: red !important;padding: 0px 8px;border-radius: 10px;}
    	.tab-p{
    		position: absolute;
		    text-align: right;
		    right: 3em;
		    margin-top: 5em !important;
    	}
    	.red{color: red;background-color: transparent !important;}
    	tr{
    		border-bottom: 2px solid rgba(255,255,255,0.52);
   		}
   		tr.even {
   			 background-color: transparent !important;
   		}
    	.dataTables_wrapper .mdl-grid .mdl-cell.mdl-cell--6-col:first-of-type{
    		    vertical-align: middle !important;
			    display: flex !important;
			    align-items: center !important;
			    margin-top: -2px !important;
    	}
    	.con-center{
	    	display: flex;
		    vertical-align: middle;
		    align-items: center;
    	}
    	thead tr th{
    		padding-left:15px !important;
    		padding-right: 6px !important;
    	}
    	th.sorting_asc::after, th.sorting_asc::before{ 
    		content:"" !important;
    		padding-left: 7px !important;
    	} 
    	td:last-child, td:last-of-type{
    		white-space: initial;
    	}  
    	.w100{
    		width: 115px !important;
    		padding-left: 25px !important;
    	} 
    	.w300{
    		width: 350px !important;
    	}  
    	.mdl-data-table td:first-of-type, .mdl-data-table th:first-of-type {
    		padding-left: 7px !important;
		}
		thead>tr>th.sorting{
			padding-left: 7px;
			padding-right: 35px !important;
		}
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
         @media(max-width: 1024px){
         	.ms-w280{width: 280px !important;}
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
		@media(max-width: 1366px){
			thead tr th{
				padding-left: 6px !important;
			}
			.w100{
    		padding-left: 20px !important;
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
	   	.center-column, .mdl-data-table tbody tr td:first-of-type{
		    text-align:center !important;
		}
		
		
		.fw-230{
        	width:20% !important;
        	/* min-width:230px !important; */
        }
        
		
		.fw-200{
        	width:10% !important;
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
            background-color: #7e7579;
        }

        .box.in-progress {
            background-color: #BDD7EE;
        }

        .box.completed {
            background-color: #8fcb95;
        }

        .box.delayed {
            background-color: #f00;
        }
		@media(max-width: 1024px){
			.con-center{display: block;}
		}
		@media(max-width: 820px){
			.tab-p{margin-top: 7.5em !important;}
		}
		@media(max-width: 768px){
			div.dataTables_wrapper div.dataTables_filter {
			    text-align: right;
			    width: 50%;
			    margin-top: 20px;
			}
			.tab-p{margin-top: 5.5em !important;}
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
        div.dataTables_wrapper div.dataTables_filter {
			    width: 100%;
			    margin-top: 20px;
			}
		.mdl-grid{padding: 40px 0;}
		fieldset.brdr{margin-top: 0 !important;}
		.tab-p{margin-top: 11em !important;right: 1em;}
        }
       
        fieldset.brdr {
        	/* padding-bottom: 1rem !important;
		    border: 0px solid #ccc; */
		    margin-bottom: -68px;
		    margin-top: 39px;
        }
        fieldset.brdr legend{		    
		    padding: 0 5px;
	    }
	    
 .select2-container--default .select2-selection--single .select2-selection__rendered {
    text-align: left;
    font-size: 1.6rem;
}

.dataTables_length
{
 font-size: 1.6rem !important;
}

.form-control input-sm ms-w280
{
	font-size: 1.6rem !important;
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
    									<a href="javascript:exportContractDetails();" class="btn waves-effect waves-light bg-s t-c"> 
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
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Work</p><br>
										<select id="work_id_fk" name="work_id_fk"
											onchange="addInQueWork(this.value);getContractDetailList();" class="searchable">
											<option value="">Select</option>										
										</select> 
									</div>		
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Contract Status</p><br>
										<select id="contract_status_fk" name="contract_status_fk"
											onchange="addInQueContractStatus(this.value);getContractDetailList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Department</p><br>
										<select id="department_fk" name="department_fk"
											onchange="addInQueDepartment(this.value);getContractDetailList();" class="searchable">
											<option value="">Select</option>
										</select>
									</div>
								
								<div class="col s6 m12 l3 center-align"><br>
									<button class="btn bg-m waves-effect waves-light t-c"
										style="margin-top: 6px;" onclick="clearFilter();">Clear
										Filters</button>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="row" style="font-size:1.6rem !important;">
						<div class="col m12 l5 offset-l3 s12">
						 	<fieldset class="p-2 brdr" >
							 	   <!-- <legend> Legend </legend>  --> 										
                                   <div class="col m4 s4 center-align con-center">
                                       <span class="box not-started"></span>
                                       <span class="description">Not Awarded</span>
                                   </div>
                                   <div class="col m4 s4 center-align con-center">
                                       <span class="box in-progress"></span>
                                       <span class="description">In Progress</span>
                                   </div>
                                   <div class="col m4 s4 center-align con-center">
                                       <span class="box completed"></span>
                                       <span class="description">Completed</span>
                                   </div>
                              </fieldset>
						</div>
						<div class="col m12 s12">
						<p class="right tab-p"><b>Figures in <span class="red">red</span> are Estimated</b></p>
							<table id="datatable-contract-details" class="mdl-data-table" style="background-color:#162D6E;">
								<thead>
									<tr id="topDivCss">
										<th style="background-color: #162D6E;font-size:1.6rem !important;">S.No</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Contract Status</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Contract</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Contract Value(Cr)</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Expenditure(Cr)</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Physical Progress</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;" class="w100">LOA Date</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Completion Date</th>
										<th style="background-color: #162D6E;font-size:1.6rem !important;">Remarks</th>
									</tr>
								</thead>
								<tbody style="background-color: #162D6E;font-size:1.6rem !important;">
									<tr>
										<td></td>
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
    <form action="<%=request.getContextPath() %>/export-details-of-contract" name="exportContractDetailForm" id="exportContractDetailForm" target="_blank" method="post">	
        <input type="hidden" name="department_fk" id="exportDepartment_fk" />
        <input type="hidden" name="contract_status_fk" id="exportContract_status_fk" />
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
    	
/*     	$(window).scroll(function () {
        	 if($(document).scrollTop()>130)
      	   {
        		$('#topDivCss').css({"position": "fixed","top": "4%","z-index": "2"});
      	   }
        	 else
        		 {
        			$('#topDivCss').css({"position": "fixed","top": "29%","z-index": "2"});
        		 }
      		}); */
    	
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
           
           
           var filters = window.localStorage.getItem("contractDetailsFilters");
         
           if($.trim(filters) != '' && $.trim(filters) != null){
       	   var temp = filters.split('^'); 
       	   for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'department_fk'){
		        		  getDepartmentFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'contract_status_fk'){
		        		  getContractStatusFilterList(temp2[1]);
		        	  }
	        	  }
	          }
           } 
    	   $('.close-message').delay(3000).fadeOut('slow');
    	
    	   getContractDetailList();
    	
    });
    
    
    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#contract_status_fk").val("");
    	$("#department_fk").val("");    	
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("contractDetailsFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/details-of-contracts"; 
    	
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
    function addInQueDepartment(department_fk){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('department_fk')) delete filtersMap[key];
   		});
    	if($.trim(department_fk) != ''){
   	    	filtersMap["department_fk"] = department_fk;
    	}
    }
  
    function addInQueContractStatus(contract_status_fk){
    	Object.keys(filtersMap).forEach(function (key) {
   			if(key.match('contract_status_fk')) delete filtersMap[key];
   		});
    	if($.trim(contract_status_fk) != ''){
   	    	filtersMap["contract_status_fk"] = contract_status_fk;
    	}
    }
 
    
    function getContractDetailList(){
    	$(".page-loader-2").show();

    	getWorkFilterList('');
    	getContractStatusFilterList('');
    	getDepartmentFilterList('');
    	    	
    	var work_id_fk = $("#work_id_fk").val();
    	if($.trim(work_id_fk) == '' ){
    		work_id_fk = '${work_id}';
    	}
    	if($.trim(work_id_fk) == '' ){
    		work_id_fk = 'P04W01';
    	}
    	var department_fk = $("#department_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		//alert(filtersMap[key]);
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("contractDetailsFilters", filters);
		});
     	table = $('#datatable-contract-details').DataTable();
		 
		table.destroy();
		
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-contract-details').DataTable({
			"order": [],
			//"paging":   false,
	        "ordering": false,
	        //"info":     false,
			"bStateSave": true,  
     		fixedHeader: true,
			"iDisplayLength" : 10,
			"iData" : {
				"start" : 52
			},"iDisplayStart" : 0,
			"drawCallback" : function() {
				var info = table.page.info();
				window.localStorage.setItem("contractDetailPageNo", info.page);
			},
            columnDefs: [
                {targets: [0, 2],className: 'mdl-data-table__cell--non-numeric'},
                {targets: [1],className: 'hide-column'},
                {targets: [0, 3, 4, 5, 7],className: 'center-column'},
                {targets: [5],className: 'red'},
                {targets: [6],className: 'fw-200'},
                {targets: [2, 8],className: 'fw-230'},
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
							.addClass('ms-w280')
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
				},
			    "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			        if (aData[1] == "Completed") {
			          $('td', nRow).css('background-color', '#8fcb95');
			          $('td', nRow).css('color', 'Black');
			          $('td', nRow).css('font-weight', '400');
			        }else if (aData[1] == "In Progress") {
			          $('td', nRow).css('background-color', '#BDD7EE');
			          $('td', nRow).css('color', 'Black');
			          $('td', nRow).css('font-weight', '400');
			        }else if (aData[1] == "Not Awarded") {
			          $('td', nRow).css('background-color', '#7e7579');
			          $('td', nRow).css('color', 'Black');
			          $('td', nRow).css('font-weight', '400');
			        }else{
			          $('td', nRow).css('background-color', 'White');
			        }
			      }
        }).rows().remove().draw();
		
		
		table.state.clear();		
	 
	 	var myParams = { work_id_fk : work_id_fk,department_fk : department_fk,contract_status_fk: contract_status_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getDetailsOfContracts",type:"POST",data:myParams,async: true,success : function(data){    				
				if(data != null && data != '' && data.length > 0){    					
	         		$.each(data,function(key,val)
	         				{
						if(val.contract_short_name.indexOf("Demo")=="-1")
						{
	                    var rowArray = []; 
                        
                        var conractName = val.contract_id;
                        if ($.trim(val.contract_short_name) != ''){
                        	conractName = $.trim(val.contract_short_name) 
                        }
                        var loa_date = val.loa_date;
                        var awarded_cost = val.awarded_cost;
                        if ($.trim(val.contract_status_fk) == 'Not Awarded'){
                        	loa_date = '<span class="red">'+val.planned_date_of_award+'</span>'
                        	awarded_cost = '<span class="red">'+val.awarded_cost+'</span>'
                        }
	                   	rowArray.push($.trim(key+1));
	                   	rowArray.push($.trim(val.contract_status_fk));
	                   	rowArray.push($.trim(conractName));
	                   	rowArray.push($.trim(awarded_cost));
	                   	rowArray.push($.trim(val.cumulative_expenditure));
	                   	rowArray.push($.trim(val.physical_progress));
	                   	rowArray.push($.trim(loa_date));
	                   	rowArray.push($.trim(val.actual_completion_date));
	                   	rowArray.push($.trim(val.remarks));   	                   	
	                   	
	                    table.row.add(rowArray).draw( true );
						}
	                    
					});
	         		/* if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
	                var oTable = $('#datatable-contract-details').dataTable();
	                oTable.fnPageChange( pageNo ); */
	         		$(".page-loader-2").hide();
				}else{
					$(".page-loader-2").hide();
				}
				
			},error: function (jqXHR, exception) {
				$(".page-loader-2").hide();
	         	getErrorMessage(jqXHR, exception);
	     }});
    } 
        
	function getDepartmentFilterList(department){
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	if($.trim(work_id_fk) == '' ){
	    		work_id_fk = '${work_id}';
	    	}
	    	if($.trim(work_id_fk) == '' ){
	    		work_id_fk = 'P04W01';
	    	}
	    	var department_fk = $("#department_fk").val();
	    	var contract_status_fk = $("#contract_status_fk").val();
	        if ($.trim(department_fk) == "") {
	        	$("#department_fk option:not(:first)").remove();
	    	 	var myParams = { department_fk : department_fk, work_id_fk : work_id_fk, contract_status_fk : contract_status_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getDepartmentsFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	var selectedFlag = (department == val.department_fk)?'selected':'';
		                        $("#department_fk").append('<option value="' + val.department_fk + '" '+selectedFlag+'>' + $.trim(val.department_name)   +'</option>');
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
		 	if($.trim(work_id_fk) == '' ){
	    		work_id_fk = '${work_id}';
	    	}
	    	if($.trim(work_id_fk) == '' ){
	    		work_id_fk = 'P04W01';
	    	}
	    	var department_fk = $("#department_fk").val();
	    	var contract_status_fk = $("#contract_status_fk").val();
		    if ($.trim(contract_status_fk) == "") {
		    	$("#contract_status_fk option:not(:first)").remove();
			 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_status_fk : contract_status_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInContract",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var selectedFlag = (contractStatus == val.contract_status_fk)?'selected':'';
	                        	 $("#contract_status_fk").append('<option value="' + val.contract_status_fk + '"'+selectedFlag+'>' + $.trim(val.contract_status_fk)+'</option>');
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
    	
    	var department_fk = $("#department_fk").val();
    	var contract_status_fk = $("#contract_status_fk").val();
    	
	    if ($.trim(work_id_fk) == "") {
	    	if($.trim(work_id_fk) == '' ){
	    		work_id_fk = '${work_id}';
	    	}
	    	if($.trim(work_id_fk) == '' ){
	    		work_id_fk = 'P04W01';
	    	}
	    	$("#work_id_fk option:not(:first)").remove();
		 	var myParams = {department_fk : department_fk, work_id_fk : work_id_fk, contract_status_fk : contract_status_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInContract",
                data: myParams, cache: false,async: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                        	 var workShortName = '';
                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
                             var selectedFlag = (work == val.work_id_fk)?'selected':'';
                             if(data.length == 1 ){
                            	 selectedFlag = 'selected';
                             }
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
    
    function exportContractDetails(){
    	 var department_fk = $("#department_fk").val();
     	 var work_id_fk = $("#work_id_fk").val();
     	 var contract_status_fk = $("#contract_status_fk").val();
     	 
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportDepartment_fk").val(department_fk);
     	 $("#exportContract_status_fk").val(contract_status_fk);
     	
     	 $("#exportContractDetailForm").submit();
  	}
    
    </script>

</body>

</html>