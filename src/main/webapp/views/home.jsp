<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome to PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
   <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
   
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
       
   <!--  <style>
        .input-field .select2-container--default {
            width: 100% !important;
        }

        /* searchable dropdown styling */
        .select2-container--default .select2-selection--single {
            background-color: lightgrey;
            border: 1px solid #aaa;
            height: 32px;
            /* border-radius: 2px; */
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
        }

        /* datatable styling starts here*/
        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        /* datatable stylings ends here*/
        
        
          h4 {
		    font-size: 2.28rem;
		    line-height: 110%;
		    margin: 0 0 .912rem 0;
		}
		
		.card .card-content {
		    padding: 5px 24px;
		    border-radius: 0 0 2px 2px;
		}
	.no-mar{
		margin-bottom:0;
	}
    </style> -->
    
     <style>
        nav {
            box-shadow: none !important;
        }

        .card-title {
            text-transform: capitalize;
        }

        .main-clr {
            background-color: #fdfdfd;
            border-radius: 10px;
        }

        .no-mar .row {
            margin-bottom: 0;
        }

        .hidden {
            display: none;
        }

        .card.main-clr.active,
        .card.sec-clr {
            background-color: #DAE8F9;
        }

        .card.main-clr.active {
            transition: background-color 0.3s ease-in-out;
        }

        .card.sec-clr .card.main-clr.active,
        .card.sec-clr .card.main-clr,
        .card.sec-clr .card.sec-clr {
            background-color: #f0f7ff;
        }

        .card-title .material-icons {
            font-size: 1.5rem;
            cursor: pointer;
        }

        .active .card-content:after {
            content: "";
            position: absolute;
            top: 100%;
            left: 48%;
            margin-left: -5px;
            border-width: 15px;
            border-style: solid;
            border-color: #DAE8F9 transparent transparent transparent;
            -webkit-filter: drop-shadow(0px 2px 1px rgba(0, 0, 0, 0.35));
            filter: drop-shadow(0px 2px 1px rgba(0, 0, 0, 0.35));
        }

        .card.sec-clr .card.main-clr.active .card-content:after {
            border-color: #f0f7ff transparent transparent transparent;
        }

        .card.main-clr {
            min-height: 240px;
        }

        .card.main-clr .line {
            display: flex;
            padding: 1px;
            justify-content: space-between;
            border-bottom: 1px solid #eee;
        }

        .card.main-clr .line::after {
            clear: both;
        }

        .card.main-clr .line>.alignleft {
            float: left;
            width: 58.33333%;
            text-align: left;
        }

        .card.main-clr .line>.aligncenter {
            float: left;
            width: 3.33333%;
            text-align: center;
        }

        .card.main-clr .line>.alignright {
            float: left;
            width: 38.33333%;
            text-align: right;
        }

        .result {
            width: 70vw;
        }

        @media only screen and (max-width: 600px) {
            .result {
                width: 90vw;
            }
        }
       
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

  
    <div class="container no-mar" style="margin-bottom:40px">
        <div class="row">
        
        	<c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
        	<c:if test="${ (index.count-1) % 3 eq 0}"></div><div class="row"></c:if>
	            <div class="col s12 m4">
	                <div id="project_data_${index.count }" class="card main-clr" onclick="showWorkData('${index.count }')">
	                    <div class="card-content">
	                        <span class="card-title center-align">${pObj.project_name }</span>
	                        <div class="line">
	                            <p class="alignleft">Original Sanctioned Cost</p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">${pObj.sanctioned_estimated_cost }</p>
	                        </div>
	                        <div class="line">
	                            <p class="alignleft">Sanctioned Year</p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">${pObj.sanctioned_year_fk }</p>
	                        </div>
	                        <div class="line">
	                            <p class="alignleft">
	                            <c:if test="${pObj.project_status eq 'Closed' }">Completion Cost</c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">Latest Revised Cost</c:if>
	                            </p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">
	                            <c:if test="${pObj.project_status eq 'Closed' }">${pObj.sanctioned_completion_cost }</c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">${pObj.latest_revised_cost}</c:if>
	                            </p>
	                        </div>
	                        <div class="line">
	                            <p class="alignleft">
	                            <c:if test="${pObj.project_status eq 'Closed' }">Completion Year</c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">Projected Completion Year</c:if>
	                            </p>
	                            <p class="aligncenter">:</p>
	                            <p class="alignright">
	                            <c:if test="${pObj.project_status eq 'Closed' }">${pObj.year_of_completion }</c:if>
	                            <c:if test="${pObj.project_status eq 'Open' }">${pObj.projected_completion_year }</c:if>
	                            </p>
	                        </div>
	                    </div>
	                </div>
	                <div class="row result hidden" id="result${index.count }">
	                    <div class="col s12 m12">
	                        <div class="card sec-clr">
	                            <div class="card-content">
	                                <div class="card-title"> &nbsp;
	                                    <span class="right">
	                                        <span class="material-icons" onclick="closeDiv('${index.count }')">close</span>
	                                    </span>
	                                </div>
	                                <div class="row">
	                                	<c:forEach var="wObj" items="${pObj.worksInfo}" varStatus="index">
	                                	<c:if test="${ (index.count-1) % 3 eq 0}"></div><div class="row"></c:if>
		                                    <div class="col s12 m4">
		                                        <div class="card main-clr">
		                                            <div class="card-content">
		                                                <span class="card-title center-align">${wObj.work_short_name }</span>
		                                                <div class="line">
		                                                    <p class="alignleft">Original Sanctioned Cost</p>
		                                                    <p class="aligncenter">:</p>
		                                                    <p class="alignright">${wObj.sanctioned_estimated_cost }</p>
		                                                </div>
		                                                <div class="line">
		                                                    <p class="alignleft">Sanctioned Year</p>
		                                                    <p class="aligncenter">:</p>
		                                                    <p class="alignright">${wObj.sanctioned_year_fk }</p>
		                                                </div>
		                                                <div class="line">
		                                                    <p class="alignleft">Completion Cost</p>
		                                                    <p class="aligncenter">:</p>
		                                                    <p class="alignright">${wObj.completion_cost}</p>
		                                                </div>
		                                                <div class="line">
		                                                    <p class="alignleft">Completion Year</p>
		                                                    <p class="aligncenter">:</p>
		                                                    <p class="alignright">${wObj.projected_completion_year}</p>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
	                                    </c:forEach>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div> 
	            	           
            </c:forEach>
        </div>
    </div>    


  <!-- footer included -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>

   <script>
        $(document).ready(function () {
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
                onOpenStart() {
                    $('<div class="modal-overlay" style="z-index:9998; display: block; opacity: 0.7;"></div>').insertAfter('#dropdown1');
                },
                onCloseStart() {
                    $('#dropdown1').next('.modal-overlay').remove();
                }
            });
            $('.sidenav').sidenav();
            $('.collapsible').collapsible();
        });

        function showWorkData(indexNo) {
            $('.result').each(function () {
                if (!$(this).hasClass('hidden')) {
                    $(this).addClass('hidden');
                }
            });
            $('.card.main-clr.active').each(function () {
                $(this).removeClass('active');
            });
            $("#project_data_"+indexNo).addClass('active');
            $("#project_data_"+indexNo).parent().find('.result').removeClass('hidden');
            
            var colLeft = Number($("#project_data_"+indexNo).parent().position().left);
            var rowLeft = Number($("#project_data_"+indexNo).parent().parent().position().left);
            $("#project_data_"+indexNo).parent().find('.result').css('margin-left', (rowLeft - colLeft - 10) + 'px');
        }

        function closeDiv(no) {
            $('.card.main-clr.active').each(function () {
                $(this).removeClass('active');
            });
            $('#result' + no).addClass('hidden');
        }

        
    </script>
    
</body>

</html>