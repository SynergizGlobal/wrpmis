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
       	 
    .collapsible-header {
        background-color: #BDD7EE;
        padding:0;
    }
        
    .collapsible-body {
        background-color: #ffffff;
        font-size:1.6rem !important;color:#000000;
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
								<h6 class="mob-mar" style="font-size:1.6rem !important;">Execution Overview Report </h6>	
								<div class="col s12 m12 right-align exportButton">
								<c:if test="${(sessionScope.USER_ROLE_CODE eq 'DA') or (sessionScope.USER_ROLE_CODE eq 'RU') or (sessionScope.USER_ROLE_CODE eq 'IT')}">
    								<div class="m-n1">
    									<a href="javascript:exportExecutionOverviewReport();" class="btn waves-effect waves-light bg-s t-c"> 
    									<strong><i class="fa fa-arrow-circle-down v-align-mid"></i> Download</strong>
    									</a>										
    								</div>
    							</c:if>    								
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
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Work ID</p><br>
										<select id="work_id_fk" name="work_id_fk" class="searchable" onChange="getExecutionOverviewReportList();">
											<option value="">Select</option>										
										</select> 
									</div>		
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Department / HOD</p><br>
										<select id="department_fk" name="department_fk" class="searchable" onChange="getExecutionOverviewReportList();">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l3 input-field">
										<p class="searchable_label" style="font-size:1.6rem !important;color:#000000;">Contract ID</p><br>
										<select id="contract_id_fk" name="contract_id_fk" class="searchable" onChange="getExecutionOverviewReportList();">
											<option value="">Select</option>
										</select>
									</div>
								
								<div class="col s6 m12 l3 center-align">  <br>
									<button class="btn bg-m waves-effect waves-light t-c"
										style="margin-top: 6px;" onclick="clearFilter();">Clear
										Filters</button>
								</div>
							</div>
						</div>
					</div>
					<br><br>
					<div claass="row">
					<table id="datatable-execution-overview-report" class="mdl-data-table" style="background-color:#162D6E;">
								<thead>
									<tr id="topDivCss">
										<th style="width:0.5%; background-color: #162D6E;font-size:1.6rem !important;">S No</th>
										<th style="width:16.5%;padding-left: 0px !important; background-color: #162D6E;font-size:1.6rem !important;">Structure Type</th>
										<th style="width:12%; background-color: #162D6E;font-size:1.6rem !important;">Unit</th>
										<th style="width:15%; background-color: #162D6E;font-size:1.6rem !important;">Scope</th>
										<th style="width:17.4%; background-color: #162D6E;font-size:1.6rem !important;">Completed</th>
										<th style="width:12%; background-color: #162D6E;font-size:1.6rem !important;">Pending</th>
										<th style=" background-color: #162D6E;font-size:1.6rem !important;">Last Updated on</th>
										<th style=" background-color: #162D6E;font-size:1.6rem !important;">Remarks</th>
									</tr>
								</thead>
					</table>
					</div>
					<div class="row">
						
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
	    	$("#word_id_fk").val(cid).trigger('change');
	    }   
    
    var filtersMap = new Object();
    var pageNo = window.localStorage.getItem("executionOverviewReportPageNo");
    $(document).ready(function () {
    	
    	/*$(window).scroll(function () {
         	 if($(document).scrollTop()>270)
       	   {
         		$('#topDivCss').css({"position": "fixed","top": "6%","z-index": "2"});
       	   }
         	 else
         		 {
         			$('#topDivCss').css({"position": "sticky","top": "0","z-index": "2"});
         		 }
       });*/  
       
    	
    	$('.collapsible').collapsible();
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
            
     
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
		var CalStructureTypeValuesArray=new Array();
	 
	 	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/getExecutionOverviewReportList",type:"POST",data:myParams,async: true,success : function(data){    				
				if(data != null && data != '' && data.length > 0){  
					var CheckLp=1;
	         		$.each(data,function(key,val)
	         				{
	         			
		         				var cval1=0;
		         				var pval2=0;
		         				var cnt=0;
		         				
		                   		if(CalStructureTypeValuesArray.indexOf(val.structure_type_fk)==-1)
		                   		{   
		                   				CalStructureTypeValuesArray.push(val.structure_type_fk);
				    	         		$.each(data,function(key1,val1){
				    	         					if(val.structure_type_fk==val1.structure_type_fk)
				    	         						{
				    	         								var a1=0,a2=0;
				    	         								if(val1.pending!="" && val1.pending!=null && val1.pending!=undefined)
				    	         								{a1=val1.pending;}
				    	         								if(val1.completed!="" && val1.completed!=null && val1.completed!=undefined)
				    	         								{a2=val1.completed;}
				    	         								cval1=parseFloat(cval1)+parseFloat(a2);
				    	         								pval2=parseFloat(pval2)+parseFloat(a1);
				    	         								cnt++;
				    	         						}
				    	         				});
				    	         		
				    	         		
		                   		}	

		                   		
		                   		/*if(val.structure_type_fk=="Earthwork")
		                   		{cval1=10.3;}
		                   		else if(val.structure_type_fk=="Major Bridge")
	                   			{
		                   				if($("#contract_id_fk").val()=="P04W01EN04")
		                   				{
		                   					cval1=1.1;
		                   				}
		                   				else
		                   					{
		                   						cval1=0;
		                   					}
		                   		}		                   		
		                   		else if(val.structure_type_fk=="Minor Bridge")
	                   			{cval1=8.5;}
		                   		else
	                   			{
			                   		cval1=cval1/cnt;
			                   		pval2=100-cval1;
			                   		 pval2=pval2/cnt; 
	                   			}*/
		                   		
 		                   		if(cval1>0)
	                   			{
			                   		cval1=cval1/cnt;
			                   		pval2=pval2/cnt;
	                   			} 
         			
		                   		
		                   		if(StructureTypeArray.indexOf(val.structure_type_fk)==-1)
		                   		{   
		                   				StructureTypeArray.push(val.structure_type_fk);
					         			var html="<li>";
					         			var lmVal=parseFloat(100)-cval1;
					                    html=html+'<div class="collapsible-header" style="font-size:1.6rem !important;color:#000000 !important;"><table><thead><tr><th style="width:1%;font-weight:normal;">'+CheckLp+'</th><th style="width:18.3%;font-weight:normal;">'+val.structure_type_fk+'</th><th style="width:11.7%;font-weight:normal;">%</th><th style="width:15.9%;font-weight:normal;">100</th><th style="width:17%;font-weight:normal;">'+cval1.toFixed(2)+'</th><th style="width:11.7%;font-weight:normal;">'+lmVal.toFixed(2)+'</th><th></th><th></th></tr></thead></table></div>';
					                    html=html+'<div class="collapsible-body">';
				
				                    	html=html+'<table id="datatable-execution-overview-report" class="mdl-data-table" style="background-color: #E3F0EF;font-size:1.6rem !important;color:#000000 !important;">'+

										'<tbody>';
				    	         		$.each(data,function(key1,val1)
				    	         				{
				    	         					if(val.structure_type_fk==val1.structure_type_fk)
				    	         						{
						    	         					html=html+'<tr>';
						    	         						html=html+'<td style="width:21%">'+$.trim(val1.strip_chart_structure_id)+'</td>';
						    	         						html=html+'<td style="width:11.9%">'+val1.unit_fk+'</td>';
						    	         						html=html+'<td style="width:16.7%">'+val1.scope.replace('%','')+'</td>';
						    	         						html=html+'<td style="width:17.5%">'+val1.completed+'</td>';
						    	         						html=html+'<td>'+val1.pending+'</td>';
						    	         						html=html+'<td></td>';
						    	         						//html=html+'<td>'+val1.remarks+'</td>';
						    	         						html=html+'<td></td>';
						    	         					html=html+'</tr>';
				    	         						}
				    	         				});
				    	         		html=html+'</tbody></table></div>';
				    	         		$('.collapsible').append(html);
				    	         		CheckLp++;
				    	         		
		                   		}
							});
      		
	         		

	         			
				}
         		$(".page-loader-2").hide();

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
			 	var myParams = {department_fk : department_fk, work_id_fk : cid, contract_id_fk : contract_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInEOR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var workShortName = '';
	                             if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
	                             var selectedFlag = (cid == val.work_id)?'selected':'';
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
			 	var myParams = {department_fk : department_fk, work_id_fk : cid, contract_id_fk : contract_id_fk};
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
			 	var myParams = {department_fk : department_fk, work_id_fk : cid, contract_id_fk : contract_id_fk};
	            $.ajax({
	                url: "<%=request.getContextPath()%>/ajax/getContractIdFilterListInEOR",
	                data: myParams, cache: false,async: false,
	                success: function (data) {
	                    if (data.length > 0) {
	                        $.each(data, function (i, val) {
	                        	 var contractShortName = '';
	                             if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) }
	                             var selectedFlag = (ContractId == val.contract_id)?'selected':'';
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