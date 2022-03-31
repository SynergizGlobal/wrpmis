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
    		text-align:center;
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
            background-color: #ffc000;
        }

        .box.electrical,
        .electrical {
            background-color: #2F75B5;
        }

        .box.sandt,
        .sandt {
            background-color: #548235;
        }
        
        .box.nbf,
        .nbf {
            background-color: #7e7579;
        }
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
        	min-width:42%;
        	width:42%;
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
	    h4{
	    font-size:14px!important;
	    }
	    
	    table thead tr td{text-align:center;}
	
    </style>
    
<style>
    #googoose-footer {
        margin-right: 1in;
        position: absolute;
        width: 100%;
        text-align: right;
    }
    #googoose-header {
        width: 100%;
        text-align: center;
    }
#hello-canvas
{
display:none;
}  

table,body {
  font-family: Verdana;
  font-size:14px;
  border-collapse: collapse;
  width: 100%;
  word-spacing: 1.5px;
  letter-spacing: 0px;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 2px;
    font-size:14px;
  
}  

.cmHed{
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
								<h6 class="mob-mar">Project Overview Report</h6>	
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
									<div class="col s6 m6 input-field">
										<p class="searchable_label">Project</p>
										<select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" onchange="getWorks();">
											<option value="">Select</option>										
	                                         <c:forEach var="obj" items="${projectsList }">
	                                             <option value="${obj.project_id_fk }">${obj.project_name }</option>
	                                         </c:forEach>
                                         </select>
									</div>										
									<div class="col s6 m6 input-field">
										<p class="searchable_label">Work<span class="required">*</span></p>
										<select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="getProjects();">
											<option value="">Select</option>
											<c:forEach var="obj" items="${worksList }">
		                                      	   <option  value= "${obj.work_id_fk}" >${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
		                                    </c:forEach>
										</select>
										<span id="work_idError" class="error-msg"></span>
									</div>
								

							</div>
						</div>
						<div class="col m12 l8 offset-l2 s12 offset-s1">
  								<div class="col s6 m6 input-field">
							<button id="gnReport" class="btn waves-effect waves-light bg-m t-c"
								style="margin-top: 6px;float:right;" onclick="generateReport();">Generate Report</button>									
  								</div>
								<div class="col s6 m6 input-field">
							<button type="button" class="btn waves-effect waves-light red t-c"
								style="margin-top: 6px;" onclick="clearFilters();">Reset</button>
							</div>
						</div>							
					</div>
<div class='googoose-wrapper' style="display:none;">
    <div class='googoose' style="padding-bottom:0px;text-align:center;">
       <img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55">
    </div>
    <br><br>					
 					<div style="text-align:center;text-transform: uppercase;" id="divWorkShortName"></div><br><br>
					<div class="WordSection1">
						<table style='width:auto;' id="prjOverviewTbl" class="bordered" >
						        <tbody>
						        </tbody>
						    </table>
						<div style="text-align:center;" id="divProject"><span style="font-size:14px;">PROCUREMENT STATUS</span></div><br><br>
						<table style='width:auto;' id="procurementStatusTbl" class="table table-striped table-bordered" >
								<thead>
									<tr><td style="width:5%">S. No.</td><td style="width:10%">Description</td><td style="width:10%">Total Contracts</td><td style="width:10%">Estimated Cost (Cr)</td><td style="width:10%">Awarded Contracts</td><td style="width:10%">Awarded Value (Cr)</td></tr>
								</thead>
						        <tbody>
					
						        </tbody>
						    </table>
						 <div><span style="font-size:16px;">Refer <a href="#firstDestination">Annexure-1</a> for detailed list of contracts.</span></div>
						    <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>						    
						<div style="text-align:center;" id="divProject"><span style="font-size:14px;">LAND ACQUISITION STATUS</span></div>  
						<table style='width:auto;' id="landAcquisitionStatusTbl" class="table table-striped table-bordered" >
								<thead>
									<tr>
										<td>Land Category</td>
										<td>Area to be Acquired (Ha.)</td>
										<td>Area Acquired (Ha.)</td>
										<td>Balance (Ha.)</td>
										<td>Cost as per Estimate (Cr.)</td>
										<td>Payment Made (Cr.)</td>
										<td>Payment as per Accounts (Cr.)</td>
									</tr>
								</thead>
						        <tbody>
						        </tbody>
						    </table>
						      <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>
						    <div style="text-align:center;" id="divProject"><span style="font-size:14px;"> UTILITY SHIFTING STATUS</span></div>	
						   
							<table style='width:40%' id="utilityShiftingStatusSummaryTbl" class="table table-striped table-bordered" >
						        <tbody>
							        <tr>
							        	<td>Total No. of Utilities</td><td id="usTot">:</td>
							        </tr>
							        <tr>
							        	<td>Above Ground Utilities</td><td id="agUtl">:</td>
							        </tr>
							        <tr>
							        	<td>Below Ground Utilities</td><td id="bgUtl">:</td>
							        </tr>
							        <tr>
							        	<td>Shifting Progress</td><td id="shiftingProgress">:</td>
							        </tr>						        
						        </tbody>
						    </table>
						    <br><br>	
						<table style='width:auto;' id="utilityShiftingStatusTbl" class="table table-striped table-bordered" >
						        <thead>
						            <tr>
						                <td>Utility Agency</td>
						                <td>Total</td>
						                <td>Completed</td>
						                <td>In Progress</td>
						                <td>Pending</td>
						            </tr>
						        </thead>
						        <tbody>
						        </tbody>
						    </table>
						   <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>	
						 <div style="text-align:center;" id="divProject"><span style="font-size:14px;">STRUCTURAL PROGRESS (%)</span></div>	   
						<table style='width:100%;' id="structuralProgressTbl" class="table table-striped table-bordered" >
						        <thead>
						            <tr>
						                <td>Description</td>
						                <td>Progress (%)</td>
						                <td>Completion Date</td>
						                <td>Remarks</td>
						            </tr>
						        </thead>
						        <tbody>
					
						        </tbody>
						    </table>
						       <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>
						<div style="text-align:center;" id="divProject"><span style="font-size:14px;">ISSUE STATUS</span></div>		
						
						<table style='width:40%' id="issueSummaryTbl" class="table table-striped table-bordered" >
					        <tbody>
						        <tr>
						        	<td>Total No. of Issues till Date</td><td id="totIssueSITillDate">:</td>
						        </tr>
						        <tr>
						        	<td>Pending Issues</td><td id="pendingIssues">:</td>
						        </tr>
						        						        						        						        
					        </tbody>
						</table>
						<br> <br>											    	
						 <div><span style="font-size:14px;">List of Pending Issues are mentioned in table below.</span></div>		 						
						 
						<table style='width:auto;' id="issueStatusTbl" class="table table-striped table-bordered" >
						        <thead>
						            <tr>
						                <td>S. No.</td>
						                <td>Category</td>
						                <td>Description</td>
						                <td>Contract</td>
						                <td>Action Taken</td>
						                <td>Reported By</td>
						                <td>Pending With</td>
						            </tr>
						        </thead>
						        <tbody>
						        </tbody>
						    </table>	
						        <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>
						<div style="text-align:center;" id="divProject"><span style="font-size:14px;">RISK STATUS</span></div>  
						
						<table style='width:60%;' id="riskSummaryTbl" class="table table-striped table-bordered" >
					        <tbody>
						        <tr>
						        	<td style='width:50%' class="cmHed">Latest Risk Assessment Date</td><td id="totSITillDate" class="cmHed">:</td>
						        </tr>
						        <tr>
						        	<td style='width:50%' class="cmHed">Total Risks</td><td id="totCloseInc" class="cmHed">:</td>
						        </tr>
						        <tr>
						        	<td style='width:50%' class="cmHed">Priority Risks</td><td id="totOpenInc" class="cmHed">:</td>
						        </tr>
						        <tr>
						        	<td colspan="2">
						        		<table style='width:100%'>
						        			<tr>
						        				<td style='width:50%' class="cmHed">High Risks</td><td id="hRisks" class="cmHed">:</td>
						        			</tr>
						        			<tr>
						        				<td style='width:50%' class="cmHed">Substantial Risks</td><td id="substantialRisks" class="cmHed">:</td>
						        			</tr>
						        			<tr>
						        				<td style='width:50%' class="cmHed">Moderate Risks</td><td id="moderateRisks" class="cmHed">:</td>
						        			</tr>
						        		</table>
						        	</td>
						        </tr>
						        <tr>
						        	<td style='width:50%' class="cmHed">Accepted/Low Risks</td><td id="CompPaidTlDate">:</td>
						        </tr>
					        </tbody>
						</table>
						<br><br>	
						 <div><span style="font-size:14px;">List of Priority Risks are mentioned in table below.</span></div>   						  
						<table style='width:auto;' id="riskStatusTbl" class="table table-striped table-bordered" >
						        <thead>
						            <tr>
						                <td>S. No.</td>
						                <td>Area</td>
						                <td>Sub Area</td>
						                <td>Classification</td>
						                <td>Mitigation Plan</td>
						                <td>Risk Owner</td>
						                <td>Action Taken</td>
						            </tr>
						        </thead>
						        <tbody>
						        </tbody>
						    </table>
						   <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>	
						<div style="text-align:center;" id="divProject"><span style="font-size:14px;">SAFETY INCIDENTS</span></div> 
						<table style='width:40%' id="safetySummaryTbl" class="table table-striped table-bordered" border="0">
					        <tbody>
						        <tr>
						        	<td style='width:50%' class="cmHed">Total Safety Incidents till Date  </td><td id="totSSITillDate" class="cmHed">:</td>
						        </tr>
						        <tr>
						        	<td style='width:50%' class="cmHed">Closed Incidents  </td><td id="totSCloseInc" class="cmHed">:</td>
						        </tr>
						        <tr>
						        	<td style='width:50%' class="cmHed">Open Incidents  </td><td id="totSOpenInc" class="cmHed">:</td>
						        </tr>
						        <tr>
						        	<td style='width:50%' class="cmHed">Compensation Paid till Date  </td><td id="CompPaidSTlDate" class="cmHed">:</td>
						        </tr>
					        </tbody>
						</table>
						<br><br>
						<div><span style="font-size:14px;">List of Open Incidents are mentioned in table below</span></div> 						    
						<table style='width:auto;' id="safetyIncidentsOpenTbl" class="table table-striped table-bordered" >
						        <thead>
						            <tr>
						                <td style="width:9%">S. No.</td>
						                <td style="width:30%">Contract</td>
						                <td style="width:10%">Department</td>
						                <td style="width:15%">Date/ Location</td>
						                <td style="width:15%">Description</td>
						                <td style="width:10%">Category</td>
						                <td style="width:10%">Compensation</td>
						                <td style="width:10%">Corrective Action</td>
						            </tr>
						        </thead>
						        <tbody>
				
						        </tbody>
						    </table>
						    
						<br><br><div><span style="font-size:14px;">List of Closed Incidents are mentioned in table below</span></div> 						    
						    
						<table style='width:auto;' id="safetyIncidentsClosedTbl" class="table table-striped table-bordered" >
						        <thead>
						            <tr>
						                <td style="width:9%">S. No.</td>
						                <td style="width:30%">Contract</td>
						                <td style="width:10%">Department</td>
						                <td style="width:15%">Date/ Location</td>
						                <td style="width:15%">Description</td>
						                <td style="width:10%">Category</td>
						                <td style="width:10%">Compensation</td>
						                <td style="width:10%">Corrective Action</td>
						            </tr>
						        </thead>
						        <tbody>
				
						        </tbody>
						    </table>
						   <div class='googoose break'></div><div style="text-align:center;"><img src="/pmis/resources/images/mrvclogo.png" alt="Logo" width="70" height="55"></div><br><br>						    	
						<div style="text-align:center;" id="divProject"><span style="font-size:14px;">PROJECT PHOTOS</span></div>   
							<table style='width:auto;' id="projectPhotosTbl" class="table table-striped table-bordered" >
							        <tbody>
						
							        </tbody>
							</table>
						<br><br>
						<div id="firstDestination" >
							<div style="text-align:center;" id="divProject"><span style="font-size:14px;">ANNEXURE-1: LIST OF CONTRACTS</span></div>   
							<br>
							<table style='width:auto;' id="listofContractsTbl" class="table table-striped table-bordered" >
									<thead>
										<tr>
											<td style="width:9%">S. No.</td>
											<td style="width:30%">Contract Name</td>
											<td style="width:13%">LOA Date</td>
											<td style="width:10%">Completion Date</td>
											<td style="width:8%">% Progress</td>
											<td style="width:10%">Estimated Cost (Cr)</td>
											<td style="width:10%">Awarded Cost (Cr)</td>
											<td style="width:10%">Remarks</td>
										</tr>
									</thead>
							        <tbody>
						
							        </tbody>
							</table>							
						</div>

 <canvas id='hello-canvas'></canvas>

    <div class='googoose footer'>
        <span class='googoose currentdate'></span>
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

		<!-- footer  -->
 	<jsp:include page="../layout/footer.jsp"></jsp:include>
 	
	<form action="<%=request.getContextPath()%>/get-contract" id="getForm" name="getForm" method="post" >
  		<input type="hidden" name="contract_id" id="contract_id"/>
    </form>
    <form action="<%=request.getContextPath() %>/export-project-work-overview-report" name="exportProjectWorkOverviewDetailForm" id="exportProjectWorkOverviewDetailForm" target="_blank" method="post">	
        <input type="hidden" name="Project_fk" id="exportProject_fk" />
        <input type="hidden" name="work_id" id="exportWork_id" />
	</form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	<script type="text/javascript" src="https://cdn.jsdelivr.net/gh/aadel112/googoose@master/jquery.googoose.js"></script>
	
	
	<script>
	$(document).ready(function () {
    	$('.collapsible').collapsible();
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
    	   $('.close-message').delay(3000).fadeOut('slow');
    	   
    	   var today = new Date();
    	   var dd = today.getDate();

    	   var mm = today.getMonth()+1; 
    	   var yyyy = today.getFullYear();
    	   if(dd<10) 
    	   {
    	       dd='0'+dd;
    	   } 

    	   if(mm<10) 
    	   {
    	       mm='0'+mm;
    	   } 
    	   today = dd+'/'+mm+'/'+yyyy;
    	   
    	   $('.currentdate').html(today);
    	   
    });	
	

    function clearFilters()
    {
    	$("#project_id_fk").val("");
    	$("#work_id_fk").val("");
    	getProjects();
    	getWorks();
    }	
 

	
	function getWorks()
	{
		$("#work_id_fk option:not(:first)").remove();
	        var myParams = { work_id_fk: $("#work_id_fk").val(),project_id_fk: $("#project_id_fk").val() };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getPORWorksFilterList",
	            data: myParams, cache: false,async: false,
	            success: function (data) 
	            {
	                if (data.length > 0) 
	                {
	                    $.each(data, function (i, val) {
	                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' +  $.trim(val.work_name) + '</option>');
	                    });
	                }
	                $('.searchable').select2();
	            }
	        });
	        $('.searchable').select2();
	}
	function getProjects()
	{
			if($("#work_id_fk").val()!="")
			{
				$("#work_idError").html("");
			}
			$("#project_id_fk option:not(:first)").remove();
			 var myParams = { work_id_fk: $("#work_id_fk").val(),project_id_fk: $("#project_id_fk").val() };
	        $.ajax({
	            url: "<%=request.getContextPath()%>/ajax/getPORProjectsFilterList",
	            data: myParams, cache: false,async: false,
	            success: function (data) 
	            {
	                if (data.length > 0) 
	                {
	                	var selectedFlag="";
                		if(data.length==1)
                		{
                			selectedFlag="selected";
                		}
	                    $.each(data, function (i, val) {
	                            $("#project_id_fk").append('<option value="' + val.project_id_fk + '" '+selectedFlag+'>' +  $.trim(val.project_name) + '</option>');
	                    });
	                }
	                $('.searchable').select2();
	            }
	        });
	        $('.searchable').select2();
	        
	 }
	
	
    function getCurrentFinancialYear() {
        var financial_year = "";
        var today = new Date();
        if ((today.getMonth() + 1) <= 3) {
       		var date=today.getFullYear();
       		var mcDate=(today.getFullYear() + 1)
            financial_year = (today.getFullYear() - 1) + "-" + date.toString().slice(-2);
        } else {
            financial_year = today.getFullYear() + "-" + mcDate.toString().slice(-2);
        }
        return financial_year;
    }		
    
    

    function getProjectWorkOverviewReport()
    {
    	$("#prjOverviewTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getProjectWorkOverviewReportList",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var today = new Date();
						var LastYear=today.getFullYear();
						
						$.each(data,function(key,val){
		        			$("#prjOverviewTbl tbody").append("<tr><td>Year of Sanction</td><td>"+val.sanctioned_year_fk+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Sanctioned Cost (Cr)</td><td>"+val.sanctioned_estimated_cost+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Budget Grant FY "+getCurrentFinancialYear()+" (Cr)</td><td>"+val.budget_grant_current_fy+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Expenditure at the end of 03/"+getCurrentFiscalYear()+" (Cr)</td><td>"+val.expenditure_end_of_fy+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Expenditure in FY "+getCurrentFinancialYear()+" (Cr)</td><td>"+val.expenditure_current_fy+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Cumulative Total Expenditure (Cr)</td><td>"+val.cumulative_total_expenditure+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Financial Progress (%)</td><td>"+val.financial_progress+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Physical Progress (%)</td><td>"+val.physical_progress+"</td></tr>");
		        			$("#prjOverviewTbl tbody").append("<tr><td>Target Completion Date</td><td>"+val.target_completion_date+"</td></tr>");
		        			
	        				if(key==0)
	        				{
								$("#divWorkShortName").html("<span style='font-size:16px;'>"+val.work_short_name+" ("+val.project_name+")<br><br>OVERVIEW</span>");
	        				}
						});
						
					}
				
				}
	   	 });   	 
    }
    
    function getCurrentFiscalYear() {
        //get current date
        var today = new Date();

        //get current month
        var curMonth = today.getMonth();

        var fiscalYr = "";
        if (curMonth > 3) { //
            var nextYr1 = (today.getFullYear() + 1).toString();
            fiscalYr = today.getFullYear().toString();
        } else {
            var nextYr2 = today.getFullYear().toString();
            fiscalYr = (today.getFullYear() - 1).toString();
        }
        return fiscalYr;
     }
    
    function getLandAcquisitionStatus()
    {
    	$("#landAcquisitionStatusTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getLandAcquisitionStatus",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var OI=0;
						$.each(data,function(key,val){
							if(key==0)
								{
									$("#landAcquisitionStatusTbl tbody").append("<tr><td>"+val.type_of_land+"</td><td style='text-align:center;'>"+val.area_to_be_acquired+"</td><td style='text-align:center;'>"+val.area_acquired+"</td><td style='text-align:center;'>"+val.balance+"</td><td style='text-align:right;'>35.28</td><td style='text-align:right;'>"+val.payment_amount_units_railway+"</td><td style='text-align:right;' rowspan='5'>233.19</td></tr>");
								}
							else
								{
									var amc=0;
									if(key==1)
									{amc=322;}
									var tyc=val.payment_amount_units_railway;
									if(key==4){amc=357.28;tyc=237.11;}
									$("#landAcquisitionStatusTbl tbody").append("<tr><td>"+val.type_of_land+"</td><td style='text-align:center;'>"+val.area_to_be_acquired+"</td><td style='text-align:center;'>"+val.area_acquired+"</td><td style='text-align:center;'>"+val.balance+"</td><td style='text-align:right;'>"+amc+"</td><td style='text-align:right;'>"+val.payment_amount_units_railway+"</td></tr>");
								}
							OI++;
						});	
						
					}
	 			}
	   	 });
    }
    
    
    function getStructuralProgress()
    {
    	$("#structuralProgressTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getStructuralProgress",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
					
						$.each(data,function(key,val){
							$("#structuralProgressTbl tbody").append("<tr><td>"+val.strip_chart_type_fk+"</td><td style='text-align:center;'>"+val.structure_type_per+"</td><td style='text-align:center;'>"+val.actual_completion_date+"</td><td></td></tr>");
						});						
					}
	 			}
	   	 });
    }    
    
 
    function getProcurementStatus()
    {
    	$("#procurementStatusTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getProcurementStatus",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var OI=1;
						var EstTot=0;
						
						$.each(data,function(key,val){
							$("#procurementStatusTbl tbody").append("<tr><td style='text-align:center;'>"+OI+"</td><td>"+val.work_name+"</td><td style='text-align:center;'>"+val.total+"</td><td style='text-align:right;'>"+val.estimated_cost+"</td><td style='text-align:center;'>"+val.contract_details_types+"</td><td style='text-align:right;'>"+val.awarded_cost+"</td></tr>");
							if(key>0)
								{
									EstTot=parseFloat(EstTot)+parseFloat(val.estimated_cost);
								}
		        			OI++;
						});						
					}
	 			}
	   	 });
    }
    
    var hCount=0;
    var sCount=0;
    var mCount=0;
    
    function getRiskStatus()
    {
    	$("#riskStatusTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getRisks",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var OI=1;
						
						$.each(data,function(key,val){
		        			$("#riskStatusTbl tbody").append("<tr><td>"+OI+"</td><td>"+val.area+"</td><td>"+val.sub_area+"</td><td>"+val.classification+"</td><td>"+val.mitigation_plan+"</td><td>"+val.owner+"</td><td></td></tr>");
		        			OI++;
		        				if(key==0)
		        				{
		        					$("#totSITillDate").html(val.assessment_date);
		        					$("#totCloseInc").html(val.total);
		        					$("#totOpenInc").html(val.priority_risks);
		        					$("#CompPaidTlDate").html(val.low_risks);
		        				}
		        				
	        					if(val.classification=="High")
	        					{
	        						hCount++;
	        					}
	        					if(val.classification=="Substantial")
	        					{
	        						sCount++;
	        					}
	        					if(val.classification=="Moderate")
	        					{
	        						mCount++;
	        					}	        					
						});	
    					$("#hRisks").html(hCount);
    					$("#substantialRisks").html(sCount);
    					$("#moderateRisks").html(mCount);
					}
	 			}
	   	 });
    } 
    
    
    function getIssueStatus()
    {
    	$("#issueStatusTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getIssueStatus",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var OI=1;
						var PIssues=0;
						var remarks="";
						$.each(data,function(key,val){
							if(val.remarks!=null)
							{
								remarks=val.remarks;
							}							
							if(val.status_fk!="Closed")
								{
			        				$("#issueStatusTbl tbody").append("<tr><td>"+OI+"</td><td>"+val.category_fk+"</td><td>"+val.description+"</td><td>"+val.contract_short_name+"</td><td>"+remarks+"</td><td>"+val.reported_by+"</td><td>"+val.other_organization+"</td></tr>");
			        				OI++;
			        				PIssues++;
								}
								if(key==0)
								{
									$("#totIssueSITillDate").html(val.total_issues);
								}
						});	
						$("#pendingIssues").html(PIssues);
					}
	 			}
	   	 });
    }   
    
    
    function getUtilityShifting()
    {
    	$("#utilityShiftingStatusTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getUtilityShifting",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var OI=1;
						var Tot=0;
						var UTot=0;
						
						var InpTot=0;
						var InpUTot=0;
						var html="";
						$.each(data,function(key,val){
							html=html+"<tr>";
							html=html+"<td>"+val.execution_agency_fk+"</td>";
		        				if(val.execution_agency_fk=="Above Ground Utilities")
		        				{
		        					$("#agUtl").html(val.total);
		        					Tot=val.total;
		        					InpTot=val.inprogress;
		        				}
		        				if(val.execution_agency_fk=="Under Ground Utilities")
		        				{
		        					$("#bgUtl").html(val.total);
		        					UTot=val.total;
		        					InpUTot=val.inprogress;
		        				}
		        				var cTotal=Number(Tot)+Number(UTot);
	        					$("#usTot").html(cTotal);
	        					
		        				var InTotal=Number(InpTot)+Number(InpUTot);
	        					$("#shiftingProgress").html(InTotal+'%');
	        					
	        					html=html+"<td style='text-align:center;'>"+val.total+"</td>";
								html=html+"<td style='text-align:center;'>"+val.completed+"</td>";
	   							html=html+"<td style='text-align:center;'>"+val.inprogress+"</td>";
	   							html=html+"<td style='text-align:center;'>"+val.pending+"</td>";
		        			OI++;
						});	
        				$("#utilityShiftingStatusTbl tbody").append(html);
					}
	 			}
	   	 });
    }      
    
    
    
    
    function getProjectPhotos()
    {
    	$("#projectPhotosTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getProjectPhotos",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var html="";
						$.each(data,function(key,val){
							html=html+"<td><img src='/pmis/STRUCTURE_FILES/"+val.attachment+"' width='200' height='200'><br>"+val.strip_chart_type_fk+"-"+val.name+"-"+val.date+"</td>";
						});
						$("#projectPhotosTbl tbody").append("<tr>"+html+"</tr>");

					}
	 			}
	   	 });
    }
    
    function getSafetyIncidents()
    {
		$("#safetyIncidentsOpenTbl tbody").html("");
		$("#safetyIncidentsClosedTbl tbody").html("");
		
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getSafetyIncidents",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						
						$("#totSITillDate").html(data.length);
						
						var sumCompensation=0;
						var sumCompensation1=0;
						
						var OI=0,CI=0;

						var ohtml="";
						var chtml="";
						$.each(data,function(key,val){
								if(val.status_fk=="Open")
								{
									if(data>0 && key==0)
									{
										OI=1;
									}									
										ohtml=ohtml+"<tr>";
										ohtml=ohtml+"<td>"+OI+"</td>";
										ohtml=ohtml+"<td>"+val.contract_short_name+"</td>";
									   	ohtml=ohtml+"<td>"+val.department_fk+"</td>";
									   	ohtml=ohtml+"<td>"+val.date+"/"+val.location+"</td>";
									   	ohtml=ohtml+"<td>"+val.description+"</td>";
									   	ohtml=ohtml+"<td>"+val.category_fk+"</td>";
									   	var valD="";
									   	var valDC="";
									   	if(val.compensation!=null){valD=val.compensation;}
									   	if(val.compensation_units!=null)
									   	{
									   		valDC=val.compensation_units;
									   		if(valDC=="L"){valDC="100000";}
									   		if(valDC=="Th"){valDC="1000";}
						        			sumCompensation=parseFloat(sumCompensation)+parseFloat(valD)*parseFloat(valDC);
									   	}
					        			ohtml=ohtml+"<td style='text-align:right;'>"+sumCompensation.toFixed(2)+"</td>";
					        			ohtml=ohtml+"<td>"+val.corrective_measure_short_term+"</td>";
					        			ohtml=ohtml+"</tr>";
				        			OI++;
								}
								else if(val.status_fk=="Closed")
									{
									if(data>0 && key==0)
									{
										CI=1;
									}										
											chtml=chtml+"<tr>";
												chtml=chtml+"<td>"+CI+"</td>";
											   	chtml=chtml+"<td>"+val.contract_short_name+"</td>";
											   	chtml=chtml+"<td>"+val.department_fk+"</td>";
											   	chtml=chtml+"<td>"+val.date+"/"+val.location+"</td>";
											   	chtml=chtml+"<td>"+val.description+"</td>";
											   	chtml=chtml+"<td>"+val.category_fk+"</td>";
											   	var valD="";
											   	var valDC="";
											   	if(val.compensation!=null){valD=val.compensation;}
											   	if(val.compensation_units!=null)
											   	{
											   		valDC=val.compensation_units;
											   		if(valDC=="L"){valDC="100000";}
											   		if(valDC=="Th"){valDC="1000";}	
								        			sumCompensation1=parseFloat(sumCompensation1)+parseFloat(valD)*parseFloat(valDC);
											   	}	
							        			chtml=chtml+"<td style='text-align:right;'>"+sumCompensation1.toFixed(2)+"</td>";
							        			chtml=chtml+"<td>"+val.corrective_measure_short_term+"</td>";	
						        			chtml=chtml+"</tr>";
				        				
				        				CI++;
									}
						});	
	        			$("#safetyIncidentsOpenTbl tbody").append(ohtml);
	        			$("#safetyIncidentsClosedTbl tbody").append(chtml);
	        			
	        			
	        			$("#totSSITillDate").html(CI+OI);
						$("#totSCloseInc").html(CI);
						$("#totSOpenInc").html(OI);
						$("#CompPaidSTlDate").html(sumCompensation+sumCompensation1);
					}
	 			}
	   	 });
    }   
    var deptArray=new Array();
    function getListOfContracts()
    {
    	$("#listofContractsTbl tbody").html("");
	   	 var myParams = {work_id_fk:$("#work_id_fk").val()};
	   	 $.ajax({url : "<%=request.getContextPath()%>/ajax/getListOfContracts",
	 			type:"POST",
	 			data:myParams,cache: false,async:false,
	 			success : function(data)
	 			{   
					if(data != null && data != '' && data.length > 0)
					{ 
						var OI=1;
						var html="";
						$.each(data,function(key,val){
							if(deptArray.indexOf(val.department_id_fk)=="-1")
							{
								html=html+"<tr><td style='text-align:center;'>"+OI+"</td><td colspan='7'>"+val.department_id_fk+"</td></tr>";
								deptArray.push(val.department_id_fk);
							
								var kt=0;
								$.each(data,function(key1,val1){
	
									if(val1.department_id_fk==val.department_id_fk)
									{
										var m=Number(kt)+1;
										var concVal=OI+'.'+m;
										
										html=html+"<tr><td style='text-align:center;'>"+concVal+"</td><td>"+val1.contract_short_name+"</td><td style='text-align:center;'>"+val1.loa_date+"</td><td style='text-align:center;'>"+val1.actual_completion_date+"</td><td style='text-align:center;'>"+val1.actual_physical_progress+"</td><td style='text-align:right;'>"+val1.estimated_cost+"</td><td style='text-align:right;'>"+val1.awarded_cost+"</td><td>"+val1.remarks+"</td></tr>";
										kt++;
									}
								});
								OI++;
							}
							
						});
						$("#listofContractsTbl tbody").append(html);

					}
	 			}
	   	 });
    } 
	
    function generateReport()
    {
		if($("#work_id_fk").val()=="")
		{
			$("#work_idError").html("Please select work");
			return false;
		}
		else
		{
			$("#work_idError").html("");
		}
		$(".googoose-wrapper").show();
		$(".page-loader-2").show();
    	getProjectWorkOverviewReport();
    	getProcurementStatus();
		getLandAcquisitionStatus();
		getStructuralProgress();    	
    	getProjectPhotos();
    	getSafetyIncidents();
    	getRiskStatus();
    	getIssueStatus();
    	getUtilityShifting();
    	getListOfContracts();
    	
    	
    	 var canvas = document.getElementById("hello-canvas");
    	    var ctx = canvas.getContext("2d");
    	    function r(ctx, x, y, w, h, c) {
    	      ctx.beginPath();
    	      ctx.rect(x, y, w, h);
    	      ctx.strokeStyle = c;
    	      ctx.stroke();
    	    }
    	    r(ctx, 0, 0, 32, 32, "black");
    	    r(ctx, 4, 4, 16, 16, "red");
    	    r(ctx, 8, 8, 16, 16, "green");
    	    r(ctx, 12, 12, 16, 16, "blue");


    	    var o = {
    	        download: 0,
    	        filename: 'Project Overview Report.doc',
    	    	  	  margins: '0.25in',
    	    	  	  size: '9in 11.0in',
    	    	  	headermargin: '.4in',
    	    };
    	    $(document).googoose(o);    
    	    $(".page-loader-2").hide();
    	    $(".googoose-wrapper").hide();
    	
        /* var header = "<html xmlns:o='urn:schemas-microsoft-com:office:office' "+
             "xmlns:w='urn:schemas-microsoft-com:office:word' "+
             "xmlns='http://www.w3.org/TR/REC-html40'>"+
             "<head><meta charset='utf-8'><title>Export HTML to Word Document with JavaScript</title></head><body>";
        var footer = "</body></html>";
        
        var sourceHTML = header+document.getElementById("docx").innerHTML+footer;
        var html, link, blob, url, css;

        // EU A4 use: size: 841.95pt 595.35pt;
        // US Letter use: size:11.0in 8.5in;

        css = (
          '<style>' +
          '@page WordSection1{size: 700.95pt 595.35pt;mso-page-orientation: portrait;}' +
          'div.WordSection1 {page: WordSection1;Calibri; font-size: 16px; font-style: normal; font-variant: normal; font-weight: 400; line-height: 20px;}' +
          'table{border-collapse:collapse;width:100%}td{border:1px gray solid;width:5em;padding:2px;}'+
          'img{width: 200px;height:200px;}'+	
          '</style>'
        );

        html = window.docx.innerHTML;
        blob = new Blob(['\ufeff', css + html], {
          type: 'application/msword'
        });
        url = URL.createObjectURL(blob);
        link = document.createElement('A');
        link.href = url;
        // Set default file name.
        // Word will append file extension - do not add an extension here.
        link.download = 'Project Overview Report';
        document.body.appendChild(link);
        if (navigator.msSaveOrOpenBlob ) navigator.msSaveOrOpenBlob( blob, 'Document.doc'); // IE10-11
        		else link.click();  // other browsers
        document.body.removeChild(link);*/
     }
    
	</script>

</body>

</html>