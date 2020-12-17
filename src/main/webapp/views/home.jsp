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
           
     <style>
     body{
     	background-color:#f2f2f2;
     }
        nav {
            box-shadow: none !important;
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
            min-height: 300px;
            height: auto;
        }
         .card.main-clr .card-content{
         	padding-bottom:0;
         }
         .card.sec-clr .card.main-clr .card-content{
         	padding-bottom:20px;
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
        .button .btn{
       		position: relative;
		    /* bottom: -25px; */
		    left: 87%;
		    margin: 5px 2px;
		    border-radius: 10px 0;
		    text-transform:capitalize;
		    padding:0 10px;	
		    background-color:#1565C0cc;
       	}
	
		 .button .btn:hover, 
		 .button .btn:focus{
		 	background-color:#1565C088;
		 }
		 .button .btn:active{
		 	background-color:#1565C0ff;
		 }
        .result {
            width: 80vw;
            margin-left:-5vw !important;
        }
		@media only screen and (min-width: 993px){
				.container.no-mar {
				    width: 75%;
				}
		}
		
        @media only screen and (max-width: 600px) {
            .result {
                width: 90vw;
                margin-left:-0.75rem !important;
            }
        }
       
    </style>
      <style>
        .modal-header {
            text-align: center;
            background-color: #1565C0cc;
            background-image: linear-gradient(to right, #16D58A, #00BDE7);
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .modal {
            max-height: 100%;
            top: 0 !important;
        }

        .map-btn-holder {
            /* margin-top: 3px; */
            text-align: center;
        }

        .map-btn-holder .btn {
            background-color: #1565C0cc;
            background-image: linear-gradient(to right, #16D58A, #00BDE7);
            border-radius: 20px;
            font-size: 2.25rem;
            line-height: 2.5rem;
            height: 3rem;
            padding: 0 28px;
        }

        .map-btn-holder .fa {
            font-size: 2.3rem;
            vertical-align: middle;
            height: 100%;
            margin-top: 15%;
        }

        .button .btn:hover,
        .button .btn:focus,
        .card.main-clr.active .button .btn {
            background-color: #1565C088;
            background-image: linear-gradient(to right, #16D58A, #00BDE7);
        }

        .container.no-mar .col {
            padding: 0 .37rem;
        }

        .card.main-clr {
            margin-bottom: 0.5rem;
            border-width: 5px;
            border-style: outset;
        }

        .card.main-clr .card-content .card-title {
            margin: -23px -23px 10px -23px;
            background-color: #1565C0cc;
            background-image: linear-gradient(to right, #16D58A, #00BDE7);
            color: #fff;
            border-radius: 4px 4px 0 0;
            padding: 3px;
            box-shadow: 2px 3px 7px 2px #999999;
            text-transform: inherit;
        }

        /* div:not(.sec-clr):not(.card) .card-content .card.main-clr .card-content .card-title {
            margin: inherit;
            background-color: inherit;
            color: inherit;
            box-shadow: inherit;
        } */

        .card.card.sec-clr .row {
            display: flex;
        }

        .card.card.sec-clr .row .col {
            padding: 5px;
        }

        .card.sec-clr .row .col .card.main-clr {
            min-height: 100%;
        }

        .autocomplete~ul.autocomplete-content {
            border-radius: 20px;
        }

        .input-field .prefix.right-side {
            right: 0
        }

        .input-field .prefix.right-side~label,
        .input-field .prefix.right-side~input {
            margin-left: 0;
            width: 100%;
        }

        .input-field .prefix.right-side~input {
            background-color: #fff;
            width: calc(100% - 25px);
            border-radius: 20px;
            padding-left: 25px;
        }

        @media only screen and (max-width: 600px) {
            .input-field .prefix.right-side~input {
                padding-left: 15px;
                width: calc(100% - 15px);
            }
        }
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>
  
    <div class="container no-mar" style="padding-bottom:30px">
     <div style="margin-top:30px">
            <div class="row">
                <div class="input-field col m11 s9">
                    <i class="material-icons prefix right-side">search</i>
                    <input id="icon_telephone" type="text" class="validate autocomplete" placeholder="Search ...">
                </div>
                <div class="col m1 s3 input-field">
                    <div class="map-btn-holder">
                        <a class="waves-effect waves-light btn modal-trigger" href="#mapmodal" title="click to see map">
                            <!-- <span class="material-icons">map</span> -->
                            <i class="fa fa-globe"></i>
                        </a>
                        <!-- Modal Structure -->
                        <div id="mapmodal" class="modal">
                            <div class="modal-content">
                                <h4 class="modal-header">Map with MUTP 3A Corridors <span
                                        class="right modal-action modal-close"><span
                                            class="material-icons">close</span></span></h4>
                                <img src="images/Final Map with MUTP 3A corridors.png" alt="Map Image" width="100%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">        
        	<c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
        	<c:if test="${ (index.count-1) % 3 eq 0}"></div><div class="row"></c:if>
	            <div class="col s12 m4">
	                <div id="project_data_${index.count }" class="card main-clr" >
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
	                            <c:if test="${pObj.project_status eq 'Closed' }">${pObj.completion_cost }</c:if>
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
	                        <div class="line">
                                <p class="alignleft">Project ID</p>
                                <p class="aligncenter">:</p>
                                <p class="alignright">${pObj.project_id }</p>
		                    </div> 
		                     <div class="line">
                                <p class="alignleft">Plan Head No</p>
                                <p class="aligncenter">:</p>
                                <p class="alignright">${pObj.plan_head_number }</p>
		                    </div> 
	                        <div class="button"><a class="btn" onclick="closeOther('${index.count }')">More</a></div>
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
		                                            <div class="card-content" style="cursor: pointer;" onclick="getTableauDashboard('${wObj.work_id}');">
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
								                            <p class="alignleft">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">Completion Cost</c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">Latest Revised Cost</c:if>
								                            </p>
								                            <p class="aligncenter">:</p>
								                            <p class="alignright">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">${wObj.completion_cost }</c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">${wObj.latest_revised_cost}</c:if>
								                            </p>
								                        </div>
								                        <div class="line">
								                            <p class="alignleft">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">Completion Year</c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">Projected Completion Year</c:if>
								                            </p>
								                            <p class="aligncenter">:</p>
								                            <p class="alignright">
								                            <c:if test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">${wObj.year_of_completion }</c:if>
								                            <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">${wObj.projected_completion_year }</c:if>
								                            </p>
								                        </div>
								                         <div class="line">
							                                <p class="alignleft">Work ID</p>
							                                <p class="aligncenter">:</p>
							                                <p class="alignright">${wObj.work_id}</p>
									                    </div> 
	                        							<div class="line">
							                                <p class="alignleft">PB Item No</p>
							                                <p class="aligncenter">:</p>
							                                <p class="alignright">${wObj.pink_book_item_number}</p>
									                    </div> 
									                    <div class="line">
							                                <p class="alignleft">Railway</p>
							                                <p class="aligncenter">:</p>
							                                <p class="alignright">${wObj.railwayAgency}</p>
									                    </div> 
									                    <div class="line">
							                                <p class="alignleft">Execution Agency</p>
							                                <p class="aligncenter">:</p>
							                                <p class="alignright">${wObj.executedBy}</p>
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
            $('.modal').modal({
                dismissible: false,
            });
            
            var searchData = {};
            
            <c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
            	searchData['${pObj.project_name}'] = null;
            </c:forEach>
            
            $('input.autocomplete').autocomplete({
                data: searchData,
            });
        });

    	/*function showWorkData(indexNo) {
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
        } */

        function closeDiv(no) {
            $('.card.main-clr.active').each(function () {
                $(this).removeClass('active');
            });
            $('#result' + no).addClass('hidden');
            $('[id^="project_data"].card.main-clr:not(.active)').each(function () {               
      			$(this).parent().removeClass('hidden');
             });
        }
        
        function closeOther(no){
        	  $('.result').each(function () {
                  if (!$(this).hasClass('hidden')) {
                      $(this).addClass('hidden');
                  }
              });
        	   $('.card.main-clr.active').each(function () {
                   $(this).removeClass('active');
               });
        	          	   
        	   $("#project_data_"+no).addClass('active');
        	                
               $('[id^="project_data"].card.main-clr:not(.active)').each(function () {
                  $(this).parent().addClass('hidden');
               });
              // $("#project_data_"+no).parent().removeClass('hidden');
               $('#result'+no).removeClass('hidden');
        }
        
        
        function getTableauDashboard(work_id){
        	if($.trim(work_id) != ''){
        		if($.trim(work_id) == 'P06W01'){
        			window.location.href = "<%=request.getContextPath()%>/InfoViz/cr-fob"
        		}
				if($.trim(work_id) == 'P07W01'){
					window.location.href = "<%=request.getContextPath()%>/InfoViz/wr-fob"
        		}
        	}
        }

        
    </script>
    
</body>

</html>