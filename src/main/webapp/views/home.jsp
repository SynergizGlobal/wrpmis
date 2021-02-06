<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		#bg {
		  position: fixed; 
		  top: 0; 
		  left: 0; 
		  width: 100%; 
		  height: 100%;
		}
		#bg img {
		  position: absolute; 
		  top: 0; 
		  left: 0; 
		  right: 0; 
		  bottom: 0; 
		  margin: auto; 
		  opacity:0.7;
		  min-width: 100%;
		  max-width: 100%;
		  min-height: 100%;
		}
     .card-title .right span{
     	padding: 5px 15px;
	    background-image: linear-gradient(to right, #16D58A, #00BDE7);
	    font-size: 2rem;
	    color: #fff;
	    border-radius: 5px;
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
        .projects-filter .card.sec-clr {        	
        	background-color: transparent;
        	box-shadow:none;
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
            min-height: 270px;
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
        .card-content .button{
        	display:flex;        	
        }
        .button .btn{
       		position: relative;
		    margin: 5px 0;
		    text-transform:capitalize;
		    padding:0 10px;	
		    background-color:#1565C0cc;
       	}
       	.button span{
       		line-height:inherit;
       	}
       	.button .btn-center{
       		position:relative;
       		margin:auto;
       		padding:0 10px;
       	}	
       	.button .btn.btn-left{
       		position:relative;
       		float:left;  
       		border-radius: 10px 0 0 10px;   		
       	}	
       	.button .btn.btn-right{
       		position:relative;
       		margin-left:auto;
       		float:right;  
       		border-radius: 0 10px 10px 0;;   		
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
            margin-left:5vw !important;
        }
		@media only screen and (min-width: 993px){
				.container.no-mar {
				    width: 75%;
				}
		}
		
		i[id^="prev"],i[id^="next"]{
			position: absolute;
		    color: #fff;
		    top:46%;
		    font-size: 4rem;
		    cursor: pointer;
		    -webkit-filter: drop-shadow(3px 6px 3px #444);
    		filter: drop-shadow(3px 6px 3px #444);
		}
		i[id^="next"] {
		    right: 10px;		   
		}
		i[id^="prev"] {
		    left: 20px;		    
		}
        @media only screen and (max-width: 600px) {
            .result {
                width: 90vw;
                margin-left:-0.75rem !important;
            }
            i[id^="next"] {
		   		right: -15px;		   
			}
			i[id^="prev"] {
			    left: -15px;		    
			}
        }
       .projects-filter-work {
		    margin-left: initial !important;
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
        .media-modal{
        	max-height:90%;
        	top:10px !important;
        }
        .media-modal .carousel .carousel-item{
        	text-align:center;
        	background-color:#f2f2f2;
        }
        .media-modal .carousel .carousel-item>img ,
        .media-modal .carousel .carousel-item>video{
		    width: auto;
		    /*height: 80vh;*/
		    height: 51vh;
		    max-width:100%;
		}

        .map-btn-holder {
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
            font-size:18px;
            box-shadow: 2px 3px 7px 2px #999999;
            text-transform: inherit;
        }

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
        .row.no-mar{
        	margin-bottom:0;
        }
        .title-btn{
        	position: absolute;		    
		    right: -94px;
    		top: -70px;
		    margin-bottom: 0;
        }
    </style>
</head>

<body>
<div id="bg">
	<img src="/pmis/resources/images/login-background.jpg" alt="">
</div>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>
  
    <div class="container no-mar" >
            <div class="row">
                <div class="input-field col m11 s9">
                    <i class="material-icons prefix right-side">search</i>
                    <input id="projects_search" type="text" class="validate autocomplete" placeholder="Search ...">
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
                                <img src="/pmis/resources/images/final_map.png" alt="Map Image" width="100%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row no-mar">        
        	<c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
        	<c:if test="${ (index.count-1) % 4 eq 0}"></div><div class="row no-mar"></c:if>
	            <div class="col s12 m3 projects-filter">
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
	                        <div class="button">
		                        <c:if test="${not empty pObj.attachment }">
		                              <a class="btn btn-left" href="<%=CommonConstants.PROJECT_FILES %>${pObj.attachment }" download><i class="fa fa-download" ></i></a> 
		                        </c:if>         
		                        <c:if test="${not empty pObj.projectGallery and fn:length(pObj.projectGallery) gt 0}">
			                        <a class="btn btn-center modal-trigger" href="#mediamodal${index.count }">Gallery</a>   	                 
			                        <div id="mediamodal${index.count }" class="modal media-modal">
			                            <div class="modal-content">
			                                <h5 class="modal-header">${pObj.project_name } Media <span class="right modal-action modal-close">
			                                	<span  class="material-icons" onclick="pauseVideo${index.count }();">close</span></span>
			                                </h5>
		                                	<div class="row">
		                                		<div class="col s12 m12">
		                                			<div class="carousel carousel-slider">
		                                				<c:forEach var="fObj" items="${pObj.projectGallery }">
			                                				<c:choose>
			                                					<c:when test="${fn:endsWith(fObj.file_name, '.jpeg')==true or fn:endsWith(fObj.file_name, '.jpg')==true or fn:endsWith(fObj.file_name, '.png')==true or fn:endsWith(fObj.file_name, '.gif')==true}">
			                                						<a class="carousel-item" href="javascript:void(0);">
			                                							<img src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}">
			                                						</a>
			                                					</c:when>
			                                					<c:otherwise>
			                                						<a class="carousel-item" href="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}">
			                                							<c:if test="${fn:endsWith(fObj.file_name, '.mp4')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/mp4" title="video">
								                                            </video>
							                                            </c:if>
							                                            <c:if test="${fn:endsWith(fObj.file_name, '.avi')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/avi" title="video">
								                                            </video>
							                                            </c:if>
							                                            <c:if test="${fn:endsWith(fObj.file_name, '.mkv')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/mkv" title="video">
								                                            </video>
							                                            </c:if>
							                                            <c:if test="${fn:endsWith(fObj.file_name, '.webm')==true}">
								                                            <video class="" preload="true" controls loop="loop" >
								                                                <source src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}" type="video/webm" title="video">
								                                            </video>
							                                            </c:if>
							                                        </a>
			                                					</c:otherwise>
			                                				</c:choose>
		                                				</c:forEach>
				                                        <div class="row slider-center"><i id="next${index.count }" class="material-icons">chevron_right</i> <i id="prev${index.count }" class="material-icons">chevron_left</i></div>
													</div>
		                                		</div>
		                                	</div>
			                            </div>
			                        </div> 
			                        <script type="text/javascript">
				                        $('i#prev${index.count }').click(function() {
				                        	$(this).parent().parent().carousel('prev');
				                        });
			
				                        $('i#next${index.count }').click(function() {
				                           // $('.carousel').carousel('next');
				                        	$(this).parent().parent().carousel('next');
				                        });
				                        
				                        function pauseVideo${index.count }(){
				                        	$("video").each(function() {
				                        	    $(this).get(0).pause();
				                        	});
				                        }
			                        </script>
		                        </c:if>
		                        <c:if test="${not empty pObj.benefits }">
		                        	<a class="btn btn-center tooltipped" data-position="top" data-tooltip="${pObj.benefits }">Benefits</a>
		                        </c:if>
		                             
			                    <a class="btn btn-right" onclick="closeOther('${index.count }')">More</a>
	                        </div>
	                    </div>
	                </div>
	                <div class="row result hidden" id="result${index.count }">
	                    <div class="col s12 m12">
	                        <div class="card sec-clr">
	                            <div class="card-content">
	                                <div class="card-title title-btn"> &nbsp;
	                                    <span class="right">
	                                        <span class="material-icons" onclick="closeDiv('${index.count }')">keyboard_backspace</span>
	                                    </span>
	                                </div>
	                                <div class="row">
	                                	<c:forEach var="wObj" items="${pObj.worksInfo}" varStatus="index">
	                                	<c:if test="${ (index.count-1) % 3 eq 0}"></div><div class="row"></c:if>
		                                    <div class="col s12 m4 projects-filter-work">
		                                        <div class="card main-clr">
		                                            <div class="card-content" style="cursor: pointer;" >
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
									                    <c:if test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
		                        							<div class="line">
								                                <p class="alignleft">PB Item No</p>
								                                <p class="aligncenter">:</p>
								                                <p class="alignright">${wObj.pink_book_item_number}</p>
										                    </div> 
									                    </c:if>
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
									                    <div class="button">
									                    <c:choose>
								                           <c:when test="${not empty wObj.work_attachment}">
															  <a class="btn btn-left" href="<%=CommonConstants2.WORK_FILES %>${wObj.work_attachment }" download><i class="fa fa-download"></i></a> 
								                           </c:when>
								                           <c:otherwise>
								                           	   <div></div>
								                           </c:otherwise>
								                        </c:choose>	
								                    
								                        <c:forEach var="obj" items="${workDetails }">
	 															<c:if test="${ obj.work_id eq wObj.work_id}">
										                          <a class="btn btn-right" onclick="closeOther('${index.count }'); getTableauDashboard('${obj.work_id}'); ">More</a> 
									                            </c:if>		                                				
								                         </c:forEach>
								                         	                        
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
                onOpenStart: function() {
                    $('<div class="modal-overlay" style="z-index:9998; display: block; opacity: 0.7;"></div>').insertAfter('#dropdown1');
                },
                onCloseStart: function() {
                    $('#dropdown1').next('.modal-overlay').remove();
                }
            });
            $('.sidenav').sidenav();
            $('.collapsible').collapsible();
            $('.modal').modal({
                dismissible: false,
                'onOpenEnd': initCarousel
            });
            $('.tooltipped').tooltip();
            $('.carousel').carousel({
                fullWidth: true
              });
            function initCarousel() {
            	$('.carousel').carousel({
                    fullWidth: true
                  });
            }
           
            var searchData = {};
            
            <c:forEach var="pObj" items="${projectsInfo}" varStatus="index">
            	searchData['${pObj.project_name}'] = null;
            </c:forEach>
            
            $('input.autocomplete').autocomplete({
                data: searchData,
            });
        });
        
        

        $("#projects_search").on("keyup change", function() {
            var txt = $('#projects_search').val();
            $('.projects-filter').each(function(){
            	if($(this).text().toUpperCase().indexOf(txt.toUpperCase()) != -1){
                   $(this).show();
               }else{
            	   $(this).hide();
               }
            });
            
            $('.projects-filter-work').each(function(){
            	if($(this).text().toUpperCase().indexOf(txt.toUpperCase()) != -1){
                   $(this).show();
               }else{
            	   $(this).hide();
               }
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
                $(this).removeClass('active hidden');
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
        	          	   
        	   $("#project_data_"+no).addClass('active hidden');
        	                
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
				if($.trim(work_id) == 'P02W01'){
        			window.location.href = "<%=request.getContextPath()%>/InfoViz/thane-diva/thane-diva-dashboards"
        		}
				if($.trim(work_id) == 'P04W06'){
					window.location.href = "<%=request.getContextPath()%>/InfoViz/ta-studies"
        		}
				if($.trim(work_id) == 'P04W04'){
        			window.location.href = "<%=request.getContextPath()%>/InfoViz/trespass-control" 
        		}
				
        	}
        }

        
    </script>
    
</body>

</html>