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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.css">
    
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
         .th-fs{
         	font-size: 1.6rem !important;
         }
         .fs-th{
         	font-size: 1.6rem !important;
         }
         .w-half{
         	width: 0.5%;
         }
         .w-16{
         	width: 16.5%;
         }
         .w-12{
         	width: 12%;
         }
         .w-15{
         	width: 15%;
         }
         .w-17{
         	width: 17%;
         }
         .w-1{
         	width: 1%;
         }
         .w-11{
         	width: 11%;
         }
         .w-26{
         	width: 26%;
         }
         .w-18{
         	width: 18%;
         }
         .fs16rem{
         	font-size: 1.6rem !important;
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
        position: sticky;
        top: 4.5em;
        z-index: 1;
    }
        
    .collapsible-body {
        background-color: #ffffff;
        font-size:1.6rem !important;color:#000000;
    } 
    
	    
	 .select2-container--default .select2-selection--single .select2-selection__rendered {
	    text-align: left;
	    font-size: 1.6rem;
	}  
	@media(max-width: 1024px){
		thead tr th{
			padding-right: 11px !important;
		    width: 99px !important;
		    font-size: 1.4rem;
		    text-align: left;
		    padding-left: 1px !important;
   			 white-space: break-spaces;
		}
		.th-fs{
			font-size: 1.4rem !important;
		}
		.collapsible-header{
			top: 5.5em;
		}
	} 
	@media(max-width: 820px){
		thead tr th{
			width: 88px !important;
			font-size: 1rem !important;
		}
		.w-16{
			width: 12.5%;
		}
		.fs16rem{
			font-size: 1rem !important;
		}
		.th-fs{
			font-size: 1.2rem !important;
		}
		 .select2-container--default .select2-selection--single .select2-selection__rendered {
		    font-size: 1.2rem;
		}
	}  
	@media(max-width: 575px){
		td:not(:last-of-type), th:not(:last-of-type){
			    padding: 0 2px !important;
		}
		td{
			word-break: break-all;
		    word-wrap: break-word;
		    white-space: break-spaces;
		    width: 50px;
		}
		.th-fs{
			font-size: .8rem !important;
		}
		.collapsible-body{
			    padding: .5rem;
		}
		.fs16rem{
			font-size: .8rem;
		}
		thead tr th{
			width: 20px !important;
		    font-size: 1rem !important;
		    word-break: break-all;
		    word-wrap: break-word;
		    white-space: break-spaces;
		}
		.select2-container--default .select2-selection--single .select2-selection__rendered {
		    font-size: .8rem;
		}
		.m-n1{
			margin: -3rem auto 0;
		}
	}  
	@media(max-width: 360px){
		.m-n1 {
		    margin: 1rem auto 0;
		    text-align: center;
		}
	}
	table{
	  border-collapse: collapse;
	  position: relative;
	  width: 100%;
	}
	thead{
	  position: relative;
	  top: 0;
	  }
	.thead--is-fixed{
	  display: table;
	  position: fixed;
	  top: 4em;
	  z-index: 2;
	}

.mdl-data-table {
    font-size: 1.6rem !important;

}

table tr td:nth-child(0) {
  width:10px;
}

table tr td:nth-child(3) {
  width:500px;
}

.ClickTd
{
 width:10px;
}
.ClickMdActivityTdChild
{
/* display:none;
 */
}			
	
    </style>
</head>

<body>
    <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->
<form name="exportExecutionOverview" id="exportExecutionOverview" target="_blank" method="post">		
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Contract</h6> -->
								<h6 class="mob-mar fs16rem">Execution Overview Report </h6>	
								<div class="col s12 m12 right-align exportButton">
<%-- 								<c:if test="${(sessionScope.USER_ROLE_CODE eq 'DA') or (sessionScope.USER_ROLE_CODE eq 'RU') or (sessionScope.USER_ROLE_CODE eq 'IT')}">
    								<div class="m-n1">
    									<a href="javascript:exportReport();" class="btn waves-effect waves-light bg-s t-c"> 
    									<strong><i class="fa fa-arrow-circle-down v-align-mid"></i> Download</strong>
    									</a>										
    								</div>
    							</c:if>   --%> 
    							<div class="m-n1">
    									<a href="javascript:exportReport();" class="btn waves-effect waves-light bg-s t-c"> 
    									<strong><i class="fa fa-arrow-circle-down v-align-mid"></i> Export</strong>
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
									<div class="col s6 m4 l4 input-field">
										<p class="searchable_label fs16rem" style="color:#000000;">Work ID</p><br>
										<select id="work_id_fk" name="work_id_fk" class="searchable" onChange="getStructureTypesAutobyWorkId();">
											<option value="">Select</option>										
										</select> 
									</div>		
									<div class="col s6 m4 l4 input-field">
										<p class="searchable_label fs16rem" style="color:#000000;">Department/HOD</p><br>
										<select id="department_fk" name="department_fk" class="searchable" onChange="getStructureTypesAutobyWorkId();">
											<option value="">Select</option>
										</select>
									</div>
									<div class="col s6 m4 l4 input-field">
										<p class="searchable_label fs16rem" style="	color:#000000;">Contract ID</p><br>
										<select id="contract_id_fk" name="contract_id_fk" class="searchable" onChange="getStructureTypesAutobyWorkId();">
											<option value="">Select</option>
										</select>
									</div>
								
								<div class="col s6 m12 l3 center-align offset-l4">  <br>
									<button class="btn bg-m waves-effect waves-light t-c"
										style="margin-top: 6px;" onclick="clearFilter();">Clear
										Filters</button>
								</div>
							</div>
						</div>
					</div>
					<br><br>
					<div claass="row" id="divExport">
<!-- 					<button id="btn-show-all-children" type="button">Expand All</button>
					<button id="btn-hide-all-children" type="button">Collapse All</button>	 -->				
					<table id="datatable-execution-overview-report" class="mdl-data-table" data-module="sticky-table">
								<thead>
									<tr id="topDivCss" style="background-color:#162D6E;">
										<th style="background-color: #162D6E;width:10px;font-size: 1.6rem !important;">S No</th>
										<th style="padding-left: 0px !important; background-color: #162D6E;width:400px;font-size: 1.6rem !important;">Structure Type</th>
										<th  style="background-color: #162D6E;width:50px;font-size: 1.6rem !important;">Unit</th>
										<th  style="background-color: #162D6E;width:50px;font-size: 1.6rem !important;">Scope</th>
										<th  style="background-color: #162D6E;width:50px;font-size: 1.6rem !important;">Completed</th>
										<th  style="background-color: #162D6E;width:50px;font-size: 1.6rem !important;">Target date of Completion</th>
									</tr>
								</thead>
					</table>
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
</form>
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
	
    <script src="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.js"></script>  
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script type="text/javascript" src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>	
	
    <script>
/*     var el = document.querySelector('[data-module="sticky-table"]');

    var scrollPosition = document.documentElement.scrollTop || document.body.scrollTop;

    var thead = el.querySelector('thead');

    var offset = el.getBoundingClientRect();


    // Make sure you throttle/debounce this
    window.addEventListener('scroll', function (event) {
      var rect = el.getBoundingClientRect();
      
      scrollPosition = document.documentElement.scrollTop || document.body.scrollTop;
      
      if(rect.top < thead.offsetHeight) {
        thead.style.width = rect.width + 'px';
        thead.classList.add('thead--is-fixed');
      }
      else {
        thead.classList.remove('thead--is-fixed');
      }
    });
    //end */	
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
    	
    	   getStructureTypesbyWorkId();
    	   
    	   
/*     	    $('#btn-show-all-children').on('click', function(){
    	        // Enumerate all rows
    	        table.rows().every(function(){
    	            // If row has details collapsed
    	            if(!this.child.isShown()){
    	                // Open this row
    	                this.child(format(this.data())).show();
    	                $(this.node()).addClass('shown');
    	            }
    	        });
    	    });

    	    // Handle click on "Collapse All" button
    	    $('#btn-hide-all-children').on('click', function(){
    	        // Enumerate all rows
    	        table.rows().every(function(){
    	            // If row has details expanded
    	            if(this.child.isShown()){
    	                // Collapse row details
    	                this.child.hide();
    	                $(this.node()).removeClass('shown');
    	            }
    	        });
    	    }); */  	   
    	   
    });
    
    
    var iTableCounter = 1;
    var oTable;
    var oInnerTable;
    var TableHtml;

    //Run On HTML Build
    $(document).ready(function () {
        TableHtml = $("#datatable-execution-overview-report").html();


        var nCloneTh = document.createElement('th');
        var nCloneTd = document.createElement('td');
        nCloneTd.innerHTML = '<img src="https://i.imgur.com/SD7Dz.png">';
        nCloneTd.className = "ClickTd";
        

        $('#datatable-execution-overview-report thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });

        $('#datatable-execution-overview-report tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });

        var oTable = $('#datatable-execution-overview-report').dataTable({
            "bPaginate": false,
            "bFilter": false,
            "bInfo": false,
            "targets": 'no-sort',
            "bSort": false,

        });

        $('#datatable-execution-overview-report').on('click', 'img', function () {
            var nTr = $(this).parents('tr')[0];
            var SelCol = $(this).closest('td').parent()[0].sectionRowIndex;
            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                this.src = "https://i.imgur.com/SD7Dz.png";
                oTable.fnClose(nTr);
            }
            else {
                this.src = "https://i.imgur.com/d4ICC.png";

                var rowid = $(this).closest('tr').index();





            }
        });
        
        
        $('.ClickTd').click(function (e) {
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();       	
       	
        	var nTr = $(this).parents('tr')[0];
            var SelCol = $(this).closest('td').parent()[0].sectionRowIndex;
            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                document.getElementById("datatable-execution-overview-report").rows[SelCol + 1].cells[0].innerHTML = "<img src='https://i.imgur.com/SD7Dz.png'>";
                oTable.fnClose(nTr);
            }
            else {
                document.getElementById("datatable-execution-overview-report").rows[SelCol + 1].cells[0].innerHTML = "<img src='https://i.imgur.com/d4ICC.png'>";

                var rowid = $(this).closest('tr').index();
                
                var stcat = document.getElementById("datatable-execution-overview-report").rows[SelCol + 1].cells[2].innerHTML;
                
		    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:stcat};
		        $.ajax({
		            url: "<%=request.getContextPath()%>/ajax/getStructuresByWorkId",
		            data: myParams, cache: false,async: false,
		            success: function (data) {
		                if (data.length > 0) {
		                	var html="";
		                    $.each(data, function (i, val) {
		                    	
		                   	    if(i==0)
		                   		{
		                   			html='<table id="datatable-execution-overview-report_'+rowid+'" class="mdl-data-table fs16rem"><thead style="display:none;"><tr id="topDivCss" style="background-color:#162D6E;"><th class="th-fs w-half" style="background-color: #162D6E;">S No</th><th class="th-fs w-16" style="padding-left: 0px !important; background-color: #162D6E;">Structure Type</th><th class="th-fs w-12" style="background-color: #162D6E;">Unit</th><th class="th-fs w-15" style="background-color: #162D6E;">Scope</th><th class="th-fs w-17" style="background-color: #162D6E;">Completed</th><th class="th-fs w-17" style="background-color: #162D6E;">Target date of Completion</th></tr></thead><tbody>';
		                   		}
		                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;'><td>"+(i+1)+"</td><td>"+val.strip_chart_structure_id+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.structure_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
		                    	if(i==data.length-1)
		                   		{
		                   			html=html+"</tbody></table>";
		                   		}

		                    });
		                    
                            oTable.fnOpen(nTr, html, "info_row");

                            var nCloneTh = document.createElement('th');
                            nCloneTh.style.backgroundColor = '#737171';
                            nCloneTh.style.color = '#ffffff';

                            var nCloneTd = document.createElement('td');
                            nCloneTd.innerHTML = '<span style="font-size:20px;"><b>+</b></span>';
                            nCloneTd.className = "ClickTdChild";

                            $('#datatable-execution-overview-report_' + rowid + ' thead tr').each(function () {
                                this.insertBefore(nCloneTh, this.childNodes[0]);
                            });

                            $('#datatable-execution-overview-report_' + rowid + ' tbody tr').each(function () {
                                this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
                            });
 
                            var oCTable = $('#datatable-execution-overview-report_' + rowid).dataTable({
                                "bPaginate": false,
                                "bFilter": false,
                                "bInfo": false,
                                "targets": 'no-sort',
                                "bSort": false,
                            });
                            
                            $('.ClickTdChild').click(function (e) {
                            	
                            	var cnTr = $(this).parents('tr')[0];
                                var SelColch = $(this).closest('td').parent()[0].sectionRowIndex;

                                if (oCTable.fnIsOpen(cnTr)) {
                                    /* This row is already open - close it */
                                    document.getElementById("datatable-execution-overview-report_" + rowid).rows[SelColch + 1].cells[0].innerHTML = "<span style='font-size:20px;'><b>+</b></span>";
                                    oCTable.fnClose(cnTr);
                                }
                                else {
                                    document.getElementById("datatable-execution-overview-report_" + rowid).rows[SelColch + 1].cells[0].innerHTML = "<span style='font-size:20px;'><b>-</b></span>";

                                    var rowidc = rowid + '' + $(this).closest('tr').index();

                                    var stcat1 = document.getElementById("datatable-execution-overview-report_" + rowid).rows[SelColch + 1].cells[2].innerHTML;
 
                                	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:stcat,strip_chart_structure_id:stcat1};
                                    $.ajax({
                                        url: "<%=request.getContextPath()%>/ajax/getComponentsByWorkId",
                    		            data: myParams, cache: false,async: false,
                    		            success: function (data) {
                    		                if (data.length > 0) {
                    		                	var html="";
                    		                    $.each(data, function (i, val) {
                    		                    	
                    		                   	    if(i==0)
                    		                   		{
                    		                   			html='<table id="datatable-execution-overview-reportC_'+rowidc+'" class="mdl-data-table fs16rem"><thead style="display:none;"><tr id="topDivCss" style="background-color:#162D6E;"><th class="th-fs w-half" style="background-color: #162D6E;">S No</th><th class="th-fs w-16" style="padding-left: 0px !important; background-color: #162D6E;">Structure Type</th><th class="th-fs w-12" style="background-color: #162D6E;">Unit</th><th class="th-fs w-15" style="background-color: #162D6E;">Scope</th><th class="th-fs w-17" style="background-color: #162D6E;">Completed</th><th class="th-fs w-17" style="background-color: #162D6E;">Target date of Completion</th></tr></thead><tbody>';
                    		                   		}
                    		                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;'><td>"+(i+1)+"</td><td>"+val.component+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.component_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
                    		                    	if(i==data.length-1)
                    		                   		{
                    		                   			html=html+"</tbody></table>";
                    		                   		}

                    		                    });
                    		                    oCTable.fnOpen(cnTr, html, "info_row");
                    		                    
                                                var nCloneTh = document.createElement('th');
                                                nCloneTh.style.backgroundColor = '#737171';
                                                nCloneTh.style.color = '#ffffff';

                                                var nCloneTd = document.createElement('td');
                                                nCloneTd.innerHTML = '<span style="font-size:20px;display:none;"><b>+</b></span>';
                                                nCloneTd.className = "ClickMdTdChild";

                                                $('#datatable-execution-overview-reportC_' + rowidc + ' thead tr').each(function () {
                                                    this.insertBefore(nCloneTh, this.childNodes[0]);
                                                });

                                                $('#datatable-execution-overview-reportC_' + rowidc + ' tbody tr').each(function () {
                                                    this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
                                                });                   		                    

 
                                                
                    		                    
                    		                }
                    		            }
                    		            
                    		        });
                                    
                                    
                                }
                            	
                            });
                            
		                }

		            }
		        });               
                
            }
        });
        


    });
    
    
    function clearFilter(){
    	$("#work_id_fk").val("");
    	$("#contract_id_fk").val("");
    	$("#department_fk").val("");    	
    	$('.searchable').select2();
    	
    	window.localStorage.setItem("executionOverviewReprtFilter",'');
    	window.location.href= "<%=request.getContextPath()%>/execution-overview-report?work_id="+cid;

    }
    
    function getData()
    {
    	TableHtml = $("#datatable-execution-overview-report").html();


        var nCloneTh = document.createElement('th');
        var nCloneTd = document.createElement('td');
        nCloneTd.innerHTML = '<img src="https://i.imgur.com/SD7Dz.png">';
        nCloneTd.className = "ClickTd";
        

        $('#datatable-execution-overview-report thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });

        $('#datatable-execution-overview-report tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });

        var oTable = $('#datatable-execution-overview-report').dataTable({
            "bPaginate": false,
            "bFilter": false,
            "bInfo": false,
            "targets": 'no-sort',
            "bSort": false,

        });

        $('#datatable-execution-overview-report').on('click', 'img', function () {
            var nTr = $(this).parents('tr')[0];
            var SelCol = $(this).closest('td').parent()[0].sectionRowIndex;
            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                this.src = "https://i.imgur.com/SD7Dz.png";
                oTable.fnClose(nTr);
            }
            else {
                this.src = "https://i.imgur.com/d4ICC.png";

                var rowid = $(this).closest('tr').index();





            }
        });
        
        
        $('.ClickTd').click(function (e) {
        	
        	var work_id_fk = $("#work_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();       	
       	
        	var nTr = $(this).parents('tr')[0];
            var SelCol = $(this).closest('td').parent()[0].sectionRowIndex;
            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                document.getElementById("datatable-execution-overview-report").rows[SelCol + 1].cells[0].innerHTML = "<img src='https://i.imgur.com/SD7Dz.png'>";
                oTable.fnClose(nTr);
            }
            else {
                document.getElementById("datatable-execution-overview-report").rows[SelCol + 1].cells[0].innerHTML = "<img src='https://i.imgur.com/d4ICC.png'>";

                var rowid = $(this).closest('tr').index();
                
                var stcat = document.getElementById("datatable-execution-overview-report").rows[SelCol + 1].cells[2].innerHTML;
                
		    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:stcat};
		        $.ajax({
		            url: "<%=request.getContextPath()%>/ajax/getStructuresByWorkId",
		            data: myParams, cache: false,async: false,
		            success: function (data) {
		                if (data.length > 0) {
		                	var html="";
		                    $.each(data, function (i, val) {
		                    	
		                   	    if(i==0)
		                   		{
		                   			html='<table id="datatable-execution-overview-report_'+rowid+'" class="mdl-data-table fs16rem"><thead style="display:none;"><tr id="topDivCss" style="background-color:#162D6E;"><th class="th-fs w-half" style="background-color: #162D6E;">S No</th><th class="th-fs w-16" style="padding-left: 0px !important; background-color: #162D6E;">Structure Type</th><th class="th-fs w-12" style="background-color: #162D6E;">Unit</th><th class="th-fs w-15" style="background-color: #162D6E;">Scope</th><th class="th-fs w-17" style="background-color: #162D6E;">Completed</th><th class="th-fs w-17" style="background-color: #162D6E;">Target date of Completion</th></tr></thead><tbody>';
		                   		}
		                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;'><td>"+(i+1)+"</td><td>"+val.strip_chart_structure_id+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.structure_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
		                    	if(i==data.length-1)
		                   		{
		                   			html=html+"</tbody></table>";
		                   		}

		                    });
		                    
                            oTable.fnOpen(nTr, html, "info_row");

                            var nCloneTh = document.createElement('th');
                            nCloneTh.style.backgroundColor = '#737171';
                            nCloneTh.style.color = '#ffffff';

                            var nCloneTd = document.createElement('td');
                            nCloneTd.innerHTML = '<span style="font-size:20px;"><b>+</b></span>';
                            nCloneTd.className = "ClickTdChild";

                            $('#datatable-execution-overview-report_' + rowid + ' thead tr').each(function () {
                                this.insertBefore(nCloneTh, this.childNodes[0]);
                            });

                            $('#datatable-execution-overview-report_' + rowid + ' tbody tr').each(function () {
                                this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
                            });
 
                            var oCTable = $('#datatable-execution-overview-report_' + rowid).dataTable({
                                "bPaginate": false,
                                "bFilter": false,
                                "bInfo": false,
                                "targets": 'no-sort',
                                "bSort": false,
                            });
                            
                            $('.ClickTdChild').click(function (e) {
                            	
                            	var cnTr = $(this).parents('tr')[0];
                                var SelColch = $(this).closest('td').parent()[0].sectionRowIndex;

                                if (oCTable.fnIsOpen(cnTr)) {
                                    /* This row is already open - close it */
                                    document.getElementById("datatable-execution-overview-report_" + rowid).rows[SelColch + 1].cells[0].innerHTML = "<span style='font-size:20px;'><b>+</b></span>";
                                    oCTable.fnClose(cnTr);
                                }
                                else {
                                    document.getElementById("datatable-execution-overview-report_" + rowid).rows[SelColch + 1].cells[0].innerHTML = "<span style='font-size:20px;'><b>-</b></span>";

                                    var rowidc = rowid + '' + $(this).closest('tr').index();

                                    var stcat1 = document.getElementById("datatable-execution-overview-report_" + rowid).rows[SelColch + 1].cells[2].innerHTML;
 
                                	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:stcat,strip_chart_structure_id:stcat1};
                                    $.ajax({
                                        url: "<%=request.getContextPath()%>/ajax/getComponentsByWorkId",
                    		            data: myParams, cache: false,async: false,
                    		            success: function (data) {
                    		                if (data.length > 0) {
                    		                	var html="";
                    		                    $.each(data, function (i, val) {
                    		                    	
                    		                   	    if(i==0)
                    		                   		{
                    		                   			html='<table id="datatable-execution-overview-reportC_'+rowidc+'" class="mdl-data-table fs16rem"><thead style="display:none;"><tr id="topDivCss" style="background-color:#162D6E;"><th class="th-fs w-half" style="background-color: #162D6E;">S No</th><th class="th-fs w-16" style="padding-left: 0px !important; background-color: #162D6E;">Structure Type</th><th class="th-fs w-12" style="background-color: #162D6E;">Unit</th><th class="th-fs w-15" style="background-color: #162D6E;">Scope</th><th class="th-fs w-17" style="background-color: #162D6E;">Completed</th><th class="th-fs w-17" style="background-color: #162D6E;">Target date of Completion</th></tr></thead><tbody>';
                    		                   		}
                    		                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;'><td>"+(i+1)+"</td><td>"+val.component+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.component_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
                    		                    	if(i==data.length-1)
                    		                   		{
                    		                   			html=html+"</tbody></table>";
                    		                   		}

                    		                    });
                    		                    oCTable.fnOpen(cnTr, html, "info_row");
                    		                    
                                                var nCloneTh = document.createElement('th');
                                                nCloneTh.style.backgroundColor = '#737171';
                                                nCloneTh.style.color = '#ffffff';

                                                var nCloneTd = document.createElement('td');
                                                nCloneTd.innerHTML = '<span style="font-size:20px;display:none;"><b>+</b></span>';
                                                nCloneTd.className = "ClickMdTdChild";

                                                $('#datatable-execution-overview-reportC_' + rowidc + ' thead tr').each(function () {
                                                    this.insertBefore(nCloneTh, this.childNodes[0]);
                                                });

                                                $('#datatable-execution-overview-reportC_' + rowidc + ' tbody tr').each(function () {
                                                    this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
                                                });                   		                    
                    		                }
                    		            }
                    		        });
                                }
                            	
                            });
                            
		                }

		            }
		        });               
                
            }
        });
    }
    
    function getStructureTypesAutobyWorkId()
    {
    	$(".page-loader-2").show();
    	getDepartmentFilterList('');
    	getContractIdFilterList('');
    	getWorkFilterList('');
    	
    	
   			if($('#datatable-execution-overview-report tbody tr').length>0)
   			{
   	   			$('#datatable-execution-overview-report').DataTable().clear().destroy();

		   		$('#datatable-execution-overview-report thead tr').find('th:eq(0)').remove();
		   		$('#datatable-execution-overview-report tbody').remove(); 
   			}
    	    	
    	var work_id_fk = $("#work_id_fk").val();
    	var department_fk = $("#department_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();

    	var filters = '';
    	Object.keys(filtersMap).forEach(function (key) {
    		filters = filters + key +"="+filtersMap[key] + "^";
    		window.localStorage.setItem("executionOverviewReprtFilter", filters);
		});
    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk};

        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getStructureTypesbyWorkId",
            data: myParams, cache: false,async: false,
            success: function (data) {
                if (data.length > 0) {
                    $.each(data, function (i, val) {
                    	var trmStr=val.structure_type_fk;
                    	trmStr=trmStr.trim();
                    	var html="";
                    	if(i==0)
                    	{
                    		html="<tbody>";
                    	}
                    	html +='<tr style="background-color: #E3F0EF;color:#000000 !important;"><td>'+(i+1)+'</td><td>'+val.structure_type_fk+'</td><td>'+val.unit+'</td><td>'+val.scope+'</td><td>'+parseFloat(val.structure_type_completed).toFixed(0)+'</td><td>'+val.target_date_of_completion+'</td></tr>';
                    	if(i==data.length-1)
                    	{
                    		html +="</tbody>";
                    	}
                    	$("#datatable-execution-overview-report").append(html);
                   	
                    });
                	
		
                	getData();	
                   
                    
                }
                $(".page-loader").hide();
            }
        });
        $(".page-loader-2").hide();
    	
    }

    
    
    function getStructureTypesbyWorkId()
    {
    	$(".page-loader-2").show();
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
    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk};

        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getStructureTypesbyWorkId",
            data: myParams, cache: false,async: false,
            success: function (data) {
                if (data.length > 0) {
                    $.each(data, function (i, val) {
                    	var trmStr=val.structure_type_fk;
                    	trmStr=trmStr.trim();
                    	var html="";
                    	if(i==0)
                    	{
                    		html="<tbody>";
                    	}
                    	html +='<tr style="background-color: #E3F0EF;color:#000000 !important;"><td>'+(i+1)+'</td><td>'+val.structure_type_fk+'</td><td>'+val.unit+'</td><td>'+val.scope+'</td><td>'+parseFloat(val.structure_type_completed).toFixed(0)+'</td><td>'+val.target_date_of_completion+'</td></tr>';
                    	if(i==data.length-1)
                    	{
                    		html +="</tbody>";
                    	}
                    	$("#datatable-execution-overview-report").append(html);
                    });
                }
                $(".page-loader").hide();
            }
        });
        $(".page-loader-2").hide();
    	
    }
    
    function getStructuresByWorkId(structuretype,row)
    {
    	if($("#"+structuretype+""+row).is(":hidden")==true)
    		{
        	var work_id_fk = $("#work_id_fk").val();
        	var department_fk = $("#department_fk").val();
        	var contract_id_fk = $("#contract_id_fk").val();
        	
		    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:structuretype};
		        $.ajax({
		            url: "<%=request.getContextPath()%>/ajax/getStructuresByWorkId",
		            data: myParams, cache: false,async: false,
		            success: function (data) {
		                if (data.length > 0) {
		                	var html="";
		                    $.each(data, function (i, val) {
		                    	
		                   	    if(i==0)
		                   		{
		                   			html='<table id="datatable-execution-overview-report" class="mdl-data-table fs16rem"><tbody>';
		                   		}
		                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;' onClick=getComponentsByWorkId('"+structuretype+"','"+val.strip_chart_structure_id+"',"+i+");><td>"+(i+1)+"</td><td>"+val.strip_chart_structure_id+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.structure_type_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
		                    	html=html+'<tr id="'+structuretype+''+val.strip_chart_structure_id+''+i+'" style="display:none;"><td colspan="5"><div id="stype'+structuretype+''+val.strip_chart_structure_id+''+i+'"></div></td></tr>';
		                    	if(i==data.length-1)
		                   		{
		                   			html=html+"</tbody></table>";
		                   		}

		                    });
		                    
	                    	$("#"+structuretype+""+row).show();
	                    	$("#stype"+structuretype+''+row).html(html);
		                }
		                else
		                	{
		                		$("#"+structuretype+""+row).hide();
		                	}
		                $('.searchable').select2();
		                $(".page-loader").hide();
		            }
		        }); 
    		}
    	else
    		{
    		$("#"+structuretype+""+row).hide();
    		}
    }

    
    function getComponentsByWorkId(stype,structure,row)
    {
    	var work_id_fk = $("#work_id_fk").val();
    	var department_fk = $("#department_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	
    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:stype,strip_chart_structure_id:structure};
        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getComponentsByWorkId",
            data: myParams, cache: false,async: false,
            success: function (data) {
                if (data.length > 0) {
                	var html="";
                    $.each(data, function (i, val) {
                    	
                   	    if(i==0)
                   		{
                   			html='<table id="datatable-execution-overview-report" class="mdl-data-table fs16rem"><tbody>';
                   		}
                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;' onClick=getActivitiesByWorkId('"+stype+"','"+structure+"','"+val.component+"',"+i+");><td>"+(i+1)+"</td><td>"+val.component+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.structure_type_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
                    	html=html+'<tr id="'+stype+''+structure+''+val.component+''+i+'" style="display:none;"><td colspan="5"><div id="stype'+stype+''+structure+''+val.component+''+i+'"></div></td></tr>';
                    	if(i==data.length-1)
                   		{
                   			html=html+"</tbody></table>";
                   		}

                    });
                    
                	$("#"+stype+""+structure+""+row).show();
                	$("#stype"+stype+""+structure+""+row).html(html);
                }
                else
                	{
                	$("#"+stype+""+structure+""+row).hide();
                	}
                $('.searchable').select2();
                $(".page-loader").hide();
            }
        });
    }
    
    function getActivitiesByWorkId(stype,structure,component,row)
    {
    	var work_id_fk = $("#work_id_fk").val();
    	var department_fk = $("#department_fk").val();
    	var contract_id_fk = $("#contract_id_fk").val();
    	
    	var myParams = { work_id_fk : cid,department_fk : department_fk,contract_id_fk: contract_id_fk,structure_type_fk:stype,strip_chart_structure_id:structure,component:component};
        $.ajax({
            url: "<%=request.getContextPath()%>/ajax/getActivitiesByWorkId",
            data: myParams, cache: false,async: false,
            success: function (data) {
                if (data.length > 0) {
                	var html="";
                    $.each(data, function (i, val) {
                    	
                   	    if(i==0)
                   		{
                   			html='<table id="datatable-execution-overview-report" class="mdl-data-table fs16rem"><tbody>';
                   		}
                    	html=html+"<tr style='background-color: #E3F0EF;color:#000000 !important;'><td>"+(i+1)+"</td><td>"+val.activity_name+"</td><td>"+val.unit+"</td><td>"+val.scope+"</td><td>"+parseFloat(val.structure_type_completed).toFixed(0)+"</td><td>"+val.target_date_of_completion+"</td></tr>";
                    	html=html+'<tr id="'+stype+''+structure+''+component+''+i+'" style="display:none;"><td colspan="5"><div id="stype'+stype+''+structure+''+component+''+i+'"></div></td></tr>';
                    	if(i==data.length-1)
                   		{
                   			html=html+"</tbody></table>";
                   		}

                    });
                    
                	$("#"+stype+""+structure+""+component+""+row).show();
                	$("#stype"+stype+""+structure+""+component+""+row).html(html);
                }
                else
                	{
                		$("#"+stype+""+structure+""+component+""+row).hide();
                	}
                $('.searchable').select2();
                $(".page-loader").hide();
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
    
    function exportReport()
    {
    	 var HTML_Width = $("#divExport").width();
    	    var HTML_Height = $("#divExport").height();
    	    var top_left_margin = 15;
    	    var PDF_Width = HTML_Width + (top_left_margin * 2);
    	    var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
    	    var canvas_image_width = HTML_Width;
    	    var canvas_image_height = HTML_Height;

    	    var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;

    	    html2canvas($("#divExport")[0]).then(function (canvas) {
    	        var imgData = canvas.toDataURL("image/jpeg", 1.0);
    	        var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
    	        pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);
    	        for (var i = 1; i <= totalPDFPages; i++) { 
    	            pdf.addPage(PDF_Width, PDF_Height);
    	            pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
    	        }
    	        pdf.save("ExecutionOverviewReport.pdf");
    	    });  	
    	
 	}    
    
    </script>

</body>

</html>