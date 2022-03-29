<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project Overview Report - PMIS</title>
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
    	.con-center div:not(:first-of-type) {
		    margin-left: auto;
		}
    	.con-center.p-2 {
		    margin-top: 1.5rem;
		    margin-bottom: 1.5rem;
		    float: none;
		}
		.table-like-heading{
			background-color:#007a7a; 
			height:4rem; 			
			box-shadow:0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 12%), 0 1px 5px 0 rgb(0 0 0 / 20%);
		}
		.table-like-heading span{
			display:inline-block;
			vertical-align:middle;
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

        .box.engineering,
        .engineering {
            background-color: #BDD7EE;
        }

        .box.electrical,
        .electrical {
            background-color: #BDD7EE;
        }

        .box.sandt,
        .sandt {
            background-color: #BDD7EE;
        }
        
        .box.nbf,
        .nbf {
            background-color: #7e7579;
        }
        .aw{background-color: #8fcb95;}
        .naw{background color: #fff;}
        .fw-5p{
        	min-width:5.5%;
        	width:5.5%;
        }
        .fw-10p{
        	min-width:10%;
        	width:10%;
        }
        .fw-15p{
        	min-width:15%;
        	width:15%;
        }
        .fw-42p{
        	min-width:15%;
        	width:15%;
        }
        .fw-43p{
        	min-width:43%;
        	width:43%;
        }
        .fw-44p{
        	min-width:44%;
        	width:44%;
        }
		@media(max-width: 1024px){
			.con-center{display: block;}
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
								<h6 class="mob-mar" style="font-size:1.6rem !important;">Project Overview Report</h6>	
								<div class="col s12 m12 right-align exportButton">
    								<div class="m-n1">
    									<a href="javascript:exportProjectOverviewDetails();" class="btn waves-effect waves-light bg-s t-c"> 
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
							<div class="col m12 l12">
								<div class="row no-mar" class="col m12 l10" style="padding-left:7%;">
									<div class="col s12 m5 input-field">
										<p class="searchable_label"  style="font-size:1.6rem !important;color:#000000;">Work</p><br>
										<select id="work_id_fk" name="work_id_fk" class="searchable" onChange="getProjectOverviewDetailList();">
											<option value="">Select</option>										
										</select> 
									</div>										
									<div class="col s12 m5 input-field">
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Department</p><br>
										<select id="department_fk" name="department_fk" class="searchable" onChange="getProjectOverviewDetailList();">
											<option value="">Select</option>
										</select>
									</div>
								
								<div class="col s12 m2 center-align">  
									<button class="btn bg-m waves-effect waves-light t-c"
										style="margin-top: 6px;" onclick="clearFilter();">Clear
										Filters</button>
								</div>
							</div>
						</div>
					</div>

					<div class="row no-mar">
						<div class="col m12 l7 offset-l2 s12">
						 	<fieldset class="p-2 con-center" style="font-size:1.6rem !important;">
                                   <div class="">
                                       <span class="box engineering"></span>
                                       <span class="description">Bank Funds</span>
                                   </div>
                                   <div class="">
                                       <span class="box nbf"></span>
                                       <span class="description">Non Bank Funds</span>
                                   </div> 
                                   <div class="">
                                       <span class="box aw"></span>
                                       <span class="description">Awarded</span>
                                   </div>
                                   <div class="">
                                       <span class="box naw"></span>
                                       <span class="description">Not Awarded</span>
                                   </div>                                  
                              </fieldset>
						</div>
					</div>

					<div class="row">
						<div class="col m12 s12">
						<p style="float:right;margin-right:20px;"><b>Figures in <span style="color:red;">red</span> are Estimated</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Amount Value in:</b> Cr</p>
							<table id="datatable-project-overview-details" class="mdl-data-table" style="background-color:#162D6E;">
								<thead>
									<tr>										
										<th class="fw-5p" style=" background-color: #162D6E;font-size:1.6rem !important;">S. No.</th>
										<th class="fw-15p" style=" background-color: #162D6E;font-size:1.6rem !important;">Description</th>
										<th class="fw-10p" style=" background-color: #162D6E;font-size:1.6rem !important;">Awarded Costs</th>
										<th class="fw-15p" style=" background-color: #162D6E;font-size:1.6rem !important;">Expenditure till Date</th>
										<th class="fw-15p" style=" background-color: #162D6E;font-size:1.6rem !important;">Expenditure this FY</th>
										<th class="fw-10p" style=" background-color: #162D6E;font-size:1.6rem !important;">Pending Amount</th>
									</tr>
								</thead>
								</table>							
						</div>
					  <div class="col m12 s12" id="divCollapase">
						
							<ul class="collapsible">

						    </ul>
							
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
    <form action="<%=request.getContextPath() %>/export-project-overview-report" name="exportProjectOverviewDetailForm" id="exportProjectOverviewDetailForm" target="_blank" method="post">	
        <input type="hidden" name="department_fk" id="exportDepartment_fk" />
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
    
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }
    
    	var cid = getUrlVars()["work_id"];
	    if(cid!="")
	    {
	    	$("#work_id_fk").val(cid);
	    }   
    
    var filtersMap = new Object();
    var pageNo = window.localStorage.getItem("projectOverviewPageNo");
    $(document).ready(function () {
    	$('.collapsible').collapsible();
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();

           var filters = window.localStorage.getItem("projectOverviewDetailsFilters");
         
           if($.trim(filters) != '' && $.trim(filters) != null){
       	   var temp = filters.split('^'); 
       	   for(var i=0;i< temp.length;i++){
	        	  if($.trim(temp[i]) != '' ){
	        		  var temp2 = temp[i].split('=');
		        	  if($.trim(temp2[0]) == 'work_id_fk'){
		        		  getWorkFilterList(temp2[1]);
		        	  }else if($.trim(temp2[0]) == 'department_fk'){
		        		  getDepartmentFilterList(temp2[1]);
		        	  }
	        	  }
	          }
           } 
    	   $('.close-message').delay(3000).fadeOut('slow');
    	
    	   getProjectOverviewDetailList();
    	
    });
    
    
    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#department_fk").val("");    	
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("projectOverviewDetailsFilters",'');
    	window.location.href= "<%=request.getContextPath()%>/project-overview-report";
    	
    	var table = $('#datatable-project-overview-details').DataTable();
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
  
   
    function getProjectOverviewDetailList(){
    	$(".page-loader-2").show();
    	$('#divCollapase ul').html("");
    	getDepartmentFilterList('');
    	getWorkFilterList('');
    	    	
    	var work_id_fk = $("#work_id_fk").val();
    	var department_fk = $("#department_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("projectOverviewDetailsFilters", filters);
		});
     		
		
		var DepartmentArray=new Array(); 

	 
	 	var myParams = { work_id_fk : work_id_fk,department_fk : department_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getProjectOverviewReportList",type:"POST",data:myParams,async: true,success : function(data){ 
			

			
			if(data != null && data != '' && data.length > 0){  
				var CheckLp=1;
				var sumVal1=0,sumVal11=0,sumVal111=0,sumVal1111=0;
				var sumVal2=0,sumVal22=0,sumVal222=0,sumVal2222=0;
				var sumVal3=0,sumVal33=0,sumVal333=0,sumVal3333=0;
				var sumVal4=0,sumVal44=0,sumVal444=0,sumVal4444=0;
				var cumVal=0,cumVal1=0,cumVal2=0,cumVal3=0;
				var conHtm="";

         		$.each(data,function(key,val)
         				{	
         					loop=0;
							var a1=0,a2=0,a3=0,a4=0;
							if(val.awarded_cost!="" && val.awarded_cost!=undefined && val.awarded_cost!=null){a1=val.awarded_cost;}else{a1=0;}
							if(val.cumulative_expenditure!="" && val.cumulative_expenditure!=undefined && val.cumulative_expenditure!=null){a2=val.cumulative_expenditure;}else{a2=0;}
							if(val.actual_financial_progress!="" && val.actual_financial_progress!=undefined && val.actual_financial_progress!=null){a3=val.actual_financial_progress;}else{a3=0;}
							if(val.actual_physical_progress!="" && val.actual_physical_progress!=undefined && val.actual_physical_progress!=null){a4=val.actual_physical_progress;}else{a4=0;}
							
         						if(val.department_name=="Engineering")
         						{
       									sumVal1=parseFloat(sumVal1)+parseFloat(a1);
       									sumVal2=parseFloat(sumVal2)+parseFloat(a2);
         								sumVal3=parseFloat(sumVal3)+parseFloat(a3);
       									sumVal4=parseFloat(sumVal4)+parseFloat(a4);   
         						}
         						else if(val.department_name=="Electrical")
       							{
     									sumVal11=parseFloat(sumVal11)+parseFloat(a1);
     									sumVal22=parseFloat(sumVal22)+parseFloat(a2);
       									sumVal33=parseFloat(sumVal33)+parseFloat(a3);
     									sumVal44=parseFloat(sumVal44)+parseFloat(a4);       									
       							}
         						else if(val.department_name=="Signalling & Telecom")
     							{
     									sumVal111=parseFloat(sumVal111)+parseFloat(a1);
     									sumVal222=parseFloat(sumVal222)+parseFloat(a2);
       									sumVal333=parseFloat(sumVal333)+parseFloat(a3);
     									sumVal444=parseFloat(sumVal444)+parseFloat(a4);  
     							}
         						else if(val.department_name=="Non Bank Funds")
     							{
      									sumVal1111=parseFloat(sumVal1111)+parseFloat(a1);
      									sumVal2222=parseFloat(sumVal2222)+parseFloat(a2);
        								sumVal3333=parseFloat(sumVal3333)+parseFloat(a3);
      									sumVal4444=parseFloat(sumVal4444)+parseFloat(a4);           									
     							}  
         				});
         		
					
         		$.each(data,function(key,val)
         				{

         			
	                   		if(DepartmentArray.indexOf(val.department_name)==-1)
	                   		{  
	                   			
			             		if(loop==0)
		             			{
			             			
			             			conHtm=conHtm+'<table id="datatable-project-overview-details" style="color:#ffffff;">'+
													'<thead>'+
														'<tr>'	+									
															'<th style="font-size:1.6rem !important;background-color:#305496;min-width:20%;width:20%;padding-left: 6px !important;" colspan="2">'+val.work_short_name+'</th>'+
															'<th class="scVal" style="font-size:1.6rem !important;background-color:#305496;min-width:10%;width:10%;text-align:center;padding-left: 0px !important;"></th>'+
															'<th class="scVal1" style="font-size:1.6rem !important;background-color:#305496;min-width:15%;width:15%;text-align:center;padding-right: 50px !important;"></th>'+
															'<th class="scVal2" style="font-size:1.6rem !important;background-color:#305496;min-width:15%;width:15%;text-align:center;padding-right: 80px !important;"></th>'+
															'<th class="scVal3" style="font-size:1.6rem !important;background-color:#305496;min-width:10%;width:10%;text-align:center;padding-right: 80px !important;"></th>'+
														'</tr>'+
													'</thead>'+
												'</table>';
             						loop++;
		             			}
			             		
	                   				DepartmentArray.push(val.department_name);
				         			var html=conHtm+"<li>", class_name='', internal_text='';
				         			if(val.department_name=="Engineering") {
				         				class_name="engineering";
				         				internal_text=internal_text+'<span style="text-align:center;min-width:17.6%;width:17.6%;">'+sumVal1.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:17%;width:17%;">'+sumVal2.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:25%;width:25%;">'+sumVal3.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:11%;width:11%;">'+sumVal4.toFixed(2)+'</span>';

				         			}
									if(val.department_name=="Electrical") {
				         				class_name="electrical";
				         				internal_text=internal_text+'<span style="text-align:center;min-width:17.6%;width:17.6%;">'+sumVal11.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:17%;width:17%;">'+sumVal22.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:25%;width:25%;">'+sumVal33.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:11%;width:11%;">'+sumVal44.toFixed(2)+'</span>';  
			         				}
				         			if(val.department_name=="Signalling & Telecom") {
				         				class_name="sandt";
				         				internal_text=internal_text+'<span style="text-align:center;min-width:17.6%;width:17.6%;">'+sumVal111.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:17%;width:17%;">'+sumVal222.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:25%;width:25%;">'+sumVal333.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:11%;width:11%;">'+sumVal444.toFixed(2)+'</span>'; 
			         				}
				         			if(val.department_name=="Non Bank Funds") {
				         				class_name="nbf";	
				         				internal_text=internal_text+'<span style="text-align:center;min-width:17.6%;width:17.6%;">'+sumVal1111.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:17%;width:17%;">'+sumVal2222.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:25%;width:25%;">'+sumVal3333.toFixed(2)+'</span>';
					                    internal_text=internal_text+'<span style="text-align:center;min-width:11%;width:11%;">'+sumVal4444.toFixed(2)+'</span>'; 
			         				}	
				         			
				         			
				         				cumVal=sumVal1+sumVal11+sumVal111+sumVal1111;
										cumVal1=sumVal2+sumVal22+sumVal222+sumVal2222;
				         				cumVal2=sumVal3+sumVal33+sumVal333+sumVal3333;
				         				cumVal3=sumVal4+sumVal44+sumVal444+sumVal4444;
			         								         			
				         			
				         			
				                    html=html+'<div class="collapsible-header white-text '+class_name+'" style="font-size:1.6rem !important;color:#000000 !important;"><span class="fw-5p">'+CheckLp+'</span><span style="min-width:21.1%;width:21.1%;">'
				                    	 +val.department_name+'</span>'+internal_text+'</div>';
				                    	 
				                    html=html+'<div class="collapsible-body" style="font-size:1.6rem !important;"><span>';	
				                    
			                    	html=html+'<table id="datatable-execution-overview-report'+CheckLp+'" class="mdl-data-table">'+
									'<tbody>';
			    	         		$.each(data,function(key1,val1)
			    	         				{
			    	         					if(val.department_name==val1.department_name)
			    	         						{
			    	         							var contractstatuscolor="style='background-color:#8fcb95;color:#000000;'";
			    	         							if(val1.contract_status_fk=="Not Awarded")
			    	         							{
			    	         								contractstatuscolor="style='background-color:#fff;'";
			    	         							}

								    	         					html=html+'<tr '+contractstatuscolor+'>';
							    	         						html=html+'<td style="min-width:5%;width:5%;font-size:1.6rem !important;">'+$.trim(key1+1-key)+'</td>';
							    	         						html=html+'<td style="min-width:14%;width:14%;font-size:1.6rem !important;">'+val1.contract_short_name+'</td>';
							    	         						html=html+'<td style="min-width:10%;width:10%;text-align:center;font-size:1.6rem !important;">'+val1.awarded_cost+'</td>';
							    	         						html=html+'<td style="min-width:14%;width:14%;text-align:center;font-size:1.6rem !important;">'+val1.cumulative_expenditure+'</td>';
							    	         						html=html+'<td style="min-width:15%;width:15%;text-align:center;font-size:1.6rem !important;">'+val1.actual_financial_progress+'</td>';
							    	         						html=html+'<td style="min-width:10%;width:10%;text-align:center;font-size:1.6rem !important;">'+val1.actual_physical_progress+'</td>';
							    	         					html=html+'</tr>';			    	         								
			    	         								
			    	         						}
			    	         				});
			    	         		html=html+'</tbody></table></span></div>';
			    	         		$('.collapsible').append(html);
			    	         		CheckLp++;
			    	         		conHtm="";
			    	         		
									$(".scVal").html(cumVal.toFixed(2));
									$(".scVal1").html(cumVal1.toFixed(2));
									$(".scVal2").html(cumVal2.toFixed(2));
									$(".scVal3").html(cumVal3.toFixed(2));
									
			    	         		
	                   		}
	                   		
	                   		
						});
         		
         		$(".page-loader-2").hide();

     			
			}
         		
		}
	});
}
        
	function getDepartmentFilterList(department){
	    	$(".page-loader").show();
	    	var work_id_fk = $("#work_id_fk").val();
	    	var department_fk = $("#department_fk").val();
	    	
	        if ($.trim(department_fk) == "") {
	        	$("#department_fk option:not(:first)").remove();
	    	 	var myParams = { department_fk : department_fk, work_id_fk : work_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getDepartmentFilterListInPOR",
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
	 
	
	 function getWorkFilterList(work){
	 	$(".page-loader").show();
	 	
    	var work_id_fk = $("#work_id_fk").val();
    	
    	var department_fk = $("#department_fk").val();
    	
	    if ($.trim(work_id_fk) == "") {
	    	$("#work_id_fk option:not(:first)").remove();
		 	var myParams = {department_fk : department_fk, work_id_fk : cid};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInPOR",
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
    
    function exportProjectOverviewDetails(){
    	 var department_fk = $("#department_fk").val();
     	 var work_id_fk = $("#work_id_fk").val();
     	 
     	 $("#exportWork_id_fk").val(work_id_fk);
     	 $("#exportDepartment_fk").val(department_fk);
     	
     	 $("#exportProjectOverviewDetailForm").submit();
  	}
    
    </script>

</body>

</html>