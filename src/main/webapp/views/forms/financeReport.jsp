<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finance Report - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-grid-template.css" />
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
			background-color:#EA6A2A; 
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
								<h6 class="mob-mar">Finance Report</h6>	
							</div>
						</span>
						<div class="row no-mar">
						<form action="<%=request.getContextPath()%>/generate-finance-report" id="getForm" name="getForm" method="post" >
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
								

							</div>
						</div>
						</form>
						<div class="col m12 l8 offset-l2 s12 offset-s1">
  								<div class="col s6 m6 input-field">
							<button id="gnReport" class="btn btn-primary t-c"
								style="margin-top: 6px;float:right;" onclick="generateReport();">Generate Report</button>						
  								</div>
								<div class="col s6 m6 input-field">
							<button type="button" class="btn waves-effect waves-light red t-c"
								style="margin-top: 6px;" onclick="clearFilters();">Reset</button>
							</div>
						</div>							
					</div>
</div>
</div></div></div>

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

    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
	<script src="/wrpmis/resources/js/select2.min.js"></script>
	<script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script> 
	
	
	<script>
	$(document).ready(function () {
    	$('.collapsible').collapsible();
    	   $('select:not(.searchable)').formSelect();
           $('.searchable').select2();
    	   $('.close-message').delay(3000).fadeOut('slow');
    	   clearFilters();
    });	
	

    function clearFilters()
    {
    	$("#project_id_fk").val("");
    	$("#work_id_fk").val("");
    	getProjects();
    	getWorks();
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
			document.getElementById("getForm").submit();	
		}
		
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
	                            $("#work_id_fk").append('<option value="' + val.work_id_fk + '">'+$.trim(val.work_id_fk)+'--' +  $.trim(val.work_short_name) + '</option>');
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
	
	

    
	</script>

</body>

</html>