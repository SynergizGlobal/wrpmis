<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants" %>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined"
        rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style type="text/css">
    
    </style>
    <style>
    /* 3rd demo start */
    .w25{width: 25% !important;}
    .w75{width: 75% !important;}
    .line p{font-size: 16px;}
    .modal{max-height: 73.5%;
    		width: max-content;
		    max-width: 90%;
		    min-width: 55%;}
    .modal-header{position: sticky;
				    top: 0;
				    z-index: 1;
				    background-color: #01BAEF;}	
    .w150{width: 150% !important;}
    .btn:hover, .btn-large:hover, .btn-small:hover{background-color: #1565c0;}
    .btn {
				  cursor: pointer;
				  -webkit-transition: all 0.15s ease-in-out;
				  transition: all 0.15s ease-in-out;
				}
				.btn:before,
				.btn:after {
				  -webkit-transition: all 0.15s ease-in-out;
				  transition: all 0.15s ease-in-out;
				}
				.diamond {
				  display: flex;
			    justify-content: center;
			    align-items: center;
				  color: #000;
				  line-height: 40px;
				  padding: 0 20px;
				  background: #fff !important;
				  margin: 40px auto;
				  /* position: relative;*/
 				  height: 85px;
				  /* border-radius: 0 !important; */
				  border-radius: 40px;
				}
				.diamond span {
				  display: inline-block;
				  width: 210px;
				  line-height: initial;
				  font-size: 16px;
				  
				}
				.diamond:before,
				.diamond:after {
				  content: '';
				  position: absolute;
				  width: 0;
				  height: 0;
				  border: 35px solid transparent;
				}
				.diamond:before {
				  right: 100%;
				  border-right-color: #fff;
				}
				.diamond:after {
				  left: 100%;
				  border-left-color: #fff;
				}
				
				.diamond:hover {
				  background: rgba(21, 101, 192, 0.8) !important;
/* 				  filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1);*/
		            transition: all 0.2s;
		            transition-duration: 0.2s; 
		            color: #fff;
				}
				.diamond:hover:before {
				  border-right-color: rgba(21, 101, 192, 0.8);
				  /* filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1); */
		            transition: all 0.2s;
		            transition-duration: 0.2s;
				}
				.diamond:hover:after {
				  border-left-color: rgba(21, 101, 192, 0.8);
				  /* filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1); */
		            transition: all 0.2s;
		            transition-duration: 0.2s;
				}
    			#projectsList ul li{
    			margin: 0 3.1rem;
    			}
    			.sub ul{
    				    display: flex;
					    flex-wrap: wrap;
					    align-items: stretch;
					    align-content: center;
					    justify-content: space-evenly;
    			}
    			.sub ul li{
    				margin: 0 3.1rem;
    			}
    			.pdlr20px{padding: 0 20px;}
    			.card.main-clr{padding: 0 0 2em !important;}
    /* 3rd demo end */
        .btn-menu ul {
            margin-top: 2.35rem;
        }

        .btn-menu ul li {
            display: inline-block;
            background-color: #fff;
            padding: 20px;
           /*  margin: 30px 25px;
            margin: 0.7rem 1.3rem;
            font-size: 18px;
            width: 17em; */
            margin: 0.7rem 2.3rem;
		    font-size: 18px;
		    width: 40%;
            text-align: center;
            border-radius: 50px/20px;
            border-left: 0;
            border-bottom: 3px solid #42a5f5;
            border-top: 3px solid #42a5f5;
            position: relative;           
        }
        .btn-menu:not(.sub) ul li{
        	 display: flex;
		    justify-content: center;
		    align-items: center;
        }
		.btn-menu.sub ul li{
			border-radius: 5px 50px;
	        border-left: 3px solid #42a5f5;
	        border-right: 3px solid #42A5F5;
	        border-bottom: 0;
	        border-top: 0;
		}
        .btn-menu ul li:hover {
            animation: beat .7s ease-in-out infinite alternate;
            transform: scale(1.2);
        }

        @keyframes beat {
            0% {
                box-shadow: 2px 3px 0.3rem #42A5F5, -2px -3px 0.3rem #42A5F5;
            }

            25% {
                box-shadow: 2px 3px 0.6rem #42A5F5, -2px -3px 0.6rem #42A5F5;
            }

            50% {
                box-shadow: 2px 3px 0.9rem #42A5F5, -2px -3px 0.9rem #42A5F5;
            }

            100% {
                box-shadow: 2px 3px 1.2rem #42A5F5, -2px -3px 1.2rem #42A5F5;
            }
        }

        .btn-menu ul li a {
            color: #000;
            font-weight: bold;
        }

        .btn-menu ul li a:after {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }

        /* .btn-menu ul li a::before {
            content: "";
            position: absolute;
            width: 40%;
            height: 50%;
            background-color: inherit;
            border-radius: 50%;
            border-radius: 40px;
            background-color: #42a5f5;
            z-index: -1;
            left: 5em;
            top: 1em;
            animation: ripple 1.5s ease-out infinite;
        }

        @keyframes ripple {
            from {
                opacity: 1;
                transform: scale(0);
            }

            to {
                opacity: 0;
                transform: scale(3);
            }
        } */

        .heading h3 {
            color: #fff;
            font-weight: bold;
            width: 90%;
            vertical-align: middle;
            font-size: 18px;
        }
		/* .heading h3:before,
			.heading h3:after {
			  content: '';
			  position: absolute;
			  width: 0;
			  height: 0;
			  border: 30px solid transparent;
			  top: 0;
			}
			.heading h3:before {
			  right: 100%;
			  border-right-color: #1565C0;
			}
			.heading h3:after {
			  left: 100%;
			  border-left-color: #1565C0;
			} */

        .arrow-img {
            margin: 30px 50px 16px;
            width: 70px;
            height: 26px;
        }

        .btn {
            padding: 0 0.75rem;
            background-color: #01BAEF;
            margin: 24px 0 16px;
            text-transform: capitalize;
            border-radius: 5px;
        }

        .btn.right {
            padding-top: 7px;
        }

        .card.main-clr {
            margin-bottom: 0.5rem;
            border-width: 5px;
            border-style: outset;
            min-height: 270px;
            height: auto;
            margin: 40px;
            padding: 20px 0 7em;
            background-color: #fdfdfd;
            border-radius: 40px;
        }

        .card {
            position: relative;
            margin: 0.5rem 0 1rem 0;
            background-color: #fff;
            -webkit-transition: -webkit-box-shadow .25s;
            transition: -webkit-box-shadow .25s;
            transition: box-shadow .25s;
            transition: box-shadow .25s, -webkit-box-shadow .25s;
            border-radius: 2px;
        }

        .card.main-clr .card-content {
            padding-bottom: 0;
        }

        .card .card-content {
            padding: 24px;
            border-radius: 0 0 2px 2px;
        }

        .hidden {
            display: none;
            opacity:0;
        }

        .card-title .material-icons {
            font-size: 1.5rem;
            cursor: pointer;
        }

        .card.main-clr {
            min-height: 270px;
            height: auto;
        }

        .card.main-clr .card-content {
            padding-bottom: 0;
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
            /* width: 58.33333%; */
            width: 100%;
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

        .card-content .button {
            display: flex;
        }

        .material-tooltip {
            padding: 20px;
            z-index: 2000;
            border-radius: 2px;
            color: #fff;
            font-size: 15px;
            min-height: 36px;
            line-height: 120%;
            border: 1px solid black;
            opacity: 0;
            position: absolute;
            max-width: 40%;
            overflow: hidden;
            left: 0;
            top: 0;
            pointer-events: none;
            visibility: hidden;
            background-color: #01BAEF;
            text-align: justify;
            box-shadow: 0 0 5px 2px #aaa;
            white-space: pre-wrap;
            border-radius: 10px;
        }

        body {
            margin: 0;
            font-family: 'Open Sans', sans-serif;
            /* background: black url("/pmis/resources/images/login-background.jpg") no-repeat center center; */
            background: #6596ff;
            background-size: cover;
            background-repeat-y: repeat;
        }

        body:after {
            content: '';
            background-color: rgba(0, 0, 0, .1);
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: -1;
        }

        .mx-auto {
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        #mediamodal {
            top: 5%;
            width: 90%;
            height: 80%;
            max-height: 80%;
        }

        #mediamodal .carousel-item {
            background-position: center center !important;
            background-repeat: no-repeat !important;
            background-size: contain !important;
            cursor: -webkit-zoom-in;
            cursor: zoom-in;
        }

        :is(#mediamodal, #documentsModal) .modal-header {
            position: sticky;
            top: 0;
            z-index: 1;
        }

        i[id^="prev"],
        i[id^="next"] {
            position: absolute;
            color: #fff;
            top: 46%;
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

        #worksList {
            padding: 35px;
        }

        .right.btn+.right.btn {
            margin-right: 5px;
        }
        #projectsList ul{
        	display: flex;
		    flex-wrap: wrap;
		    align-items: stretch;
		    align-content: center;
		    justify-content: space-evenly;
        }
        .subWorkOverview:not(.hidden) ul li{
	       	 animation: showLi .7s ease-out;
        } 
		.subWorkOverview:not(.hidden) ul li a:before{
	       	 content:none;
        }
        /* @keyframes showLi {
            0% {
            	opacity:0;       
            	position:absolute;   
            }
            10% {
            	bottom:0;  
            }
            20% {
            	opacity:.3;
            }
            40% {
            	bottom:.7rem;  
            	opacity:.5;
            } 
            60% {
            	bottom:1.2rem;
            	opacity:.7;
            }
            70% {
            	bottom:2rem;
            }
            80% {
                bottom:inherit;
                opacity:.8;
            } 
            100% {
            	opacity:1;
            	position:relative;  
            }
        } */
        .pos-abs-down{
        	position:absolute;
            bottom:0;
            right:0;
            opacity:0.2;
        }
        @media(max-width: 1366px) {
            .heading h3 {
                font-size: 18px;
            }
        }
        .modal-header{margin: 0 !important;}
        .subWorkOverview .modal.open{top:20% !important;}
    </style>
</head>

<body>

    <!-- header  starts-->
    <jsp:include page="./layout/header.jsp"></jsp:include>
    <!-- header ends  -->
    <div class="">
        <div class="row">
            <div class="col s12 m10 offset-m1">
                <div class="col s12 m3">
                <div id="projectsBtns">
                    <c:if test="${not empty projectOverview.benefits }">
                        <!-- <a class="btn btn-center">Benefits</a>   -->
                        <a class="btn tooltipped " data-position="bottom"
                            data-tooltip="${projectOverview.benefits }">Benefits</a>
                    </c:if>

                    <c:if
                        test="${not empty projectOverview.projectGallery and fn:length(projectOverview.projectGallery) gt 0}">
                        <a class="btn btn-center modal-trigger" href="#mediamodal">Gallery</a>
                        <div id="mediamodal" class="modal media-modal">
                            <div class="modal-content">
                                <h5 class="modal-header">${projectOverview.project_name } Media
                                    <span class="right modal-action modal-close">
                                        <span class="material-icons"
                                            onclick="pauseVideo();">close</span></span>
                                </h5>
                                <div class="row">
                                    <div class="col s12 m12">
                                        <div class="carousel carousel-slider">
                                            <c:forEach var="fObj"
                                                items="${projectOverview.projectGallery }">
                                                <c:choose>
                                                    <c:when
                                                        test="${fn:endsWith(fObj.file_name, '.jpeg')==true or fn:endsWith(fObj.file_name, '.jpg')==true or fn:endsWith(fObj.file_name, '.png')==true or fn:endsWith(fObj.file_name, '.gif')==true}">
                                                        <a class="carousel-item"
                                                            href="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}"
                                                            target="_blank"
                                                            style='background:url("<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}")'>
                                                            <%-- <img
                                                                src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}">
                                                                --%>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="carousel-item"
                                                            href="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}">
                                                            <c:if
                                                                test="${fn:endsWith(fObj.file_name, '.mp4')==true}">
                                                                <video class="" preload="true"
                                                                    controls loop="loop">
                                                                    <source
                                                                        src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}"
                                                                        type="video/mp4"
                                                                        title="video">
                                                                </video>
                                                            </c:if>
                                                            <c:if
                                                                test="${fn:endsWith(fObj.file_name, '.avi')==true}">
                                                                <video class="" preload="true"
                                                                    controls loop="loop">
                                                                    <source
                                                                        src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}"
                                                                        type="video/avi"
                                                                        title="video">
                                                                </video>
                                                            </c:if>
                                                            <c:if
                                                                test="${fn:endsWith(fObj.file_name, '.mkv')==true}">
                                                                <video class="" preload="true"
                                                                    controls loop="loop">
                                                                    <source
                                                                        src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}"
                                                                        type="video/mkv"
                                                                        title="video">
                                                                </video>
                                                            </c:if>
                                                            <c:if
                                                                test="${fn:endsWith(fObj.file_name, '.webm')==true}">
                                                                <video class="" preload="true"
                                                                    controls loop="loop">
                                                                    <source
                                                                        src="<%=CommonConstants2.PROJECT_GALLERY%>${fObj.project_id_fk}/${fObj.file_name}"
                                                                        type="video/webm"
                                                                        title="video">
                                                                </video>
                                                            </c:if>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <div class="row slider-center"><i id="next"
                                                    class="material-icons">chevron_right</i> <i
                                                    id="prev"
                                                    class="material-icons">chevron_left</i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:if>

                    <c:if test="${not empty projectOverview.projectDocs}">
                        <a class="btn modal-trigger" href="#documentsModal${index.count }">
                            <!-- <i class="fa fa-download" ></i> --> Docs
                        </a>
                        <div id="documentsModal${index.count }" class="modal">
                            <div class="modal-content">
                                <h5 class="modal-header">${projectOverview.project_name } Files<span
                                        class="right modal-action modal-close"><span
                                            class="material-icons">close</span></span></h5>
                                <div class="row">
                                    <div class="col m2 hide-on-small"></div>
                                    <table border="1">
                                        <tr>
                                            <th>Project file Type</th>
                                            <th>File Name</th>
                                            <th>Uploaded On</th>
                                            <th>Download</th>
                                        </tr>
                                        <c:forEach var="pDocs"
                                            items="${projectOverview.projectDocs}">
                                            <tr>
                                                <td>${pDocs.project_file_type_fk }</td>
                                                <td>${pDocs.attachment }</td>
                                                <td>${pDocs.created_date }</td>
                                                <td style="text-align:center;">
                                                    <a href="<%=CommonConstants.PROJECT_FILES%>${pDocs.project_id_fk }/${pDocs.attachment }"
                                                        class="filevalue" download><i
                                                            class="fa fa-arrow-down"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>

                            </div>
                        </div>

                    </c:if>
                </div>
                </div>
                <div class="col s12 m6">
                    <div class="heading">
                        <center>
                            <h3 data-title='${projectOverview.project_name }'>
                                ${projectOverview.project_name }</h3>
                        </center>
                    </div>
                </div>
                <div class="col s12 m2 right">
                    <div class="card-title title-btn" id="projectBackBtn"> &nbsp;
 						<a class="btn " data-position="bottom" href="<%=request.getContextPath()%>/" class="btn tooltipped right btn" style="float:right;">Back</a>  
                                               
                    </div>
                    <div class="card-title title-btn hidden" id="workBackBtn"> &nbsp;
                        <a href="<%=request.getContextPath()%>/" class="right btn">
                            <span class="material-icons-outlined">home</span>
                        </a>
                        <a href="#" onClick="goToProject()" class="right btn">
                            <span class="material-icons">keyboard_backspace</span>
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col s12 m12 l12">
            <div class="row">
                <div class="col s12 m5 l5">
                    <div class="block">
                        <div class="projects-filter-work">
                            <div class="card main-clr" id="projectDetailsDiv">
                                <div class="card-content" style="cursor: pointer;">
                                    <!-- <div class="line">
                                        <p class="alignleft w150">Year of Inclusion</p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px"></p>
                                    </div> -->
                                    <div class="line">
                                        <p class="alignleft w150">Sanctioned Year</p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">${projectOverview.sanctioned_year_fk }
                                        </p>
                                    </div>
                                    <div class="line">
                                        <p class="alignleft w150">Pink Book No</p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">
                                            ${projectOverview.pink_book_item_number }</p>
                                    </div>
                                    <div class="line">
                                        <p class="alignleft w150">Original Sanctioned Cost </p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">
                                            <c:if
                                                test="${not empty projectOverview.sanctioned_estimated_cost }">
                                                ₹ ${projectOverview.sanctioned_estimated_cost } Cr
                                            </c:if>
                                        </p>
                                    </div>
                                    <div class="line">
                                        <p class="alignleft w150">
                                            <c:if
                                                test="${projectOverview.project_status eq 'Closed' }">
                                                Completion Cost </c:if>
                                            <c:if
                                                test="${projectOverview.project_status eq 'Open' }">
                                                Latest Revised Cost </c:if>
                                        </p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">
                                            <c:if
                                                test="${projectOverview.project_status eq 'Closed' }">
                                                <c:if
                                                    test="${not empty projectOverview.completion_cost }">
                                                    ₹ ${projectOverview.completion_cost } Cr </c:if>
                                            </c:if>
                                            <c:if
                                                test="${projectOverview.project_status eq 'Open' }">
                                                <c:if
                                                    test="${not empty projectOverview.latest_revised_cost }">
                                                    ₹ ${projectOverview.latest_revised_cost } Cr
                                                </c:if>
                                            </c:if>
                                        </p>
                                    </div>
                                    <div class="line">
                                        <p class="alignleft w150">
                                            <c:if
                                                test="${projectOverview.project_status eq 'Closed' }">
                                                Completion Year</c:if>
                                            <c:if
                                                test="${projectOverview.project_status eq 'Open' }">
                                                Projected Completion Year</c:if>
                                        </p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">
                                            <c:if
                                                test="${projectOverview.project_status eq 'Closed' }">
                                                ${projectOverview.year_of_completion }</c:if>
                                            <c:if
                                                test="${projectOverview.project_status eq 'Open' }">
                                                ${projectOverview.projected_completion_year }</c:if>
                                        </p>
                                    </div>

                                    <div class="line">
                                        <p class="alignleft w150">Physical Progress (%)</p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">
                                            <c:if
                                                test="${projectOverview.project_status eq 'Closed' }">
                                                Completed </c:if>
                                            <c:if
                                                test="${projectOverview.project_status eq 'Open' }">
                                            </c:if>
                                        </p>
                                    </div>
                                    <div class="line">
                                        <p class="alignleft w150">Financial Progress (%)</p>
                                        <p class="aligncenter">:</p>
                                        <p class="alignleft pdlr20px">
                                            <c:if
                                                test="${projectOverview.project_status eq 'Closed' }">
                                                Completed </c:if>
                                            <c:if
                                                test="${projectOverview.project_status eq 'Open' }">
                                            </c:if>
                                        </p>

                                    </div>

                                </div>
                            </div>
                           
                        </div>
                    </div>
                </div>
                <div class="col s12 m7 l7" id="full-col">
                    <div class="row" id="projectsList">
                        <ul>
                            <c:forEach var="wObj" items="${projectOverview.worksInfo }"
                                varStatus="loop">
                                <li class="col-s12 m6 l6">
                                    <!-- <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="100%" height="130px" viewBox="0 0 1440 130" preserveAspectRatio="none" style="transform-origin: 50% 50%;position: absolute;height: 25px;left: 0; top: -23px;">
                <path id="wave-bottom-white" fill="white" d="M0,56.050526 C121.352261,18.683509 262.172393,0 422.460394,0 C662.892396,0 818.88453,115.474324 1058.94661,115.474324 C1218.988,115.474324 1342.92081,94.808741 1440,56 L1440,130 L0,130 L0,56.050526 Z"/></svg> -->

                                    <c:if test="${empty wObj.work_id_fk}">
                                        <a href="javascript:void(0);"
                                            onClick="movingOnPosition(this,${loop.index})">
                                            <div >
								    <div class="btn diamond">
								         <span>${wObj.work_short_name}</span>
								    </div>
								</div></a>
                                    </c:if>
                                    <c:if test="${not empty wObj.work_id_fk}">
                                        <a
                                            href="javascript:getTableauDashboard('${wObj.work_id_fk }');">
                                            <div >
								    <div class="btn diamond">
								         <span>${wObj.work_short_name}</span>
								    </div>
								</div>
                                            
                                            </a>
                                    </c:if>
                                    <!-- <div class="col s6 m6 l6">
                <p>Silent feature</p>
            </div>
            <div class="col s6 m6 l6">
                <p>Sanction detail</p>
            </div> -->
                                    <!-- <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="100%" height="130px" viewBox="0 0 1440 130" preserveAspectRatio="none" style="transform-origin: 50% 50%; transform: scale(-1, -1);position: absolute;height: 23px; left: 0;bottom: -22px;">
                            <path id="wave-bottom-white" fill="white" d="M0,56.050526 C121.352261,18.683509 262.172393,0 422.460394,0 C662.892396,0 818.88453,115.474324 1058.94661,115.474324 C1218.988,115.474324 1342.92081,94.808741 1440,56 L1440,130 L0,130 L0,56.050526 Z"/></svg> -->
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <c:forEach var="wObj" items="${projectOverview.worksInfo }" varStatus="loop">
                        <div class="hidden subWorkOverview" id="worksListOfProject${loop.index }">
                       <div class="modal" id="workDetails${loop.index }">
                       <div class="modal-header">Sanctions Details <span class="right modal-action modal-close"><span class="material-icons">close</span></span></div>
                                <div class="card main-clr hidden workOverview"
                                    id="workDetailsOfProject${loop.index}">
                                    <div class="card-content" style="cursor: pointer;">
                                        <%-- <span class="card-title center-align">
                                            ${wObj.work_short_name }</span> --%>
                                            <div class="line">
                                                <p class="alignleft">Original Sanctioned Cost </p>
                                                <p class="aligncenter">:</p>
                                                <p class="alignleft pdlr20px">
                                                    <c:if
                                                        test="${not empty wObj.sanctioned_estimated_cost }">
                                                        ₹ ${wObj.sanctioned_estimated_cost } Cr
                                                    </c:if>
                                                </p>
                                            </div>
                                            <div class="line">
                                                <p class="alignleft">Sanctioned Year</p>
                                                <p class="aligncenter">:</p>
                                                <p class="alignleft pdlr20px">${wObj.sanctioned_year_fk }
                                                </p>
                                            </div>

                                            <div class="line">
                                                <p class="alignleft">
                                                    <c:if
                                                        test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">
                                                        Completion Cost </c:if>
                                                    <c:if
                                                        test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
                                                        Latest Revised Cost </c:if>
                                                </p>
                                                <p class="aligncenter">:</p>
                                                <p class="alignleft pdlr20px">
                                                    <c:if
                                                        test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">
                                                        <c:if
                                                            test="${not empty wObj.completion_cost }">
                                                            ₹ ${wObj.completion_cost } Cr </c:if>
                                                    </c:if>
                                                    <c:if
                                                        test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
                                                        <c:if
                                                            test="${not empty wObj.latest_revised_cost }">
                                                            ₹ ${wObj.latest_revised_cost } Cr
                                                        </c:if>
                                                    </c:if>
                                                </p>
                                            </div>
                                            <div class="line">
                                                <p class="alignleft">
                                                    <c:if
                                                        test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">
                                                        Completion Year</c:if>
                                                    <c:if
                                                        test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
                                                        Projected Completion Year</c:if>
                                                </p>
                                                <p class="aligncenter">:</p>
                                                <p class="alignleft pdlr20px">
                                                    <c:if
                                                        test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">
                                                        ${wObj.year_of_completion }</c:if>
                                                    <c:if
                                                        test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
                                                        ${wObj.projected_completion_year }</c:if>
                                                </p>
                                            </div>
                                            <div class="line">
                                                <p class="alignleft">Work ID</p>
                                                <p class="aligncenter">:</p>
                                                <p class="alignleft pdlr20px">${wObj.work_id}</p>
                                            </div>
                                            <%-- <c:if
                                                test="${empty wObj.year_of_completion and empty wObj.completion_cost}">
                                                <div class="line">
                                                    <p class="alignleft">PB Item No</p>
                                                    <p class="aligncenter">:</p>
                                                    <p class="alignright">
                                                        ${wObj.pink_book_item_number}</p>
                                                </div>
                                                </c:if> --%>
                                                <div class="line">
                                                    <p class="alignleft">Railway</p>
                                                    <p class="aligncenter">:</p>
                                                    <p class="alignleft pdlr20px">${wObj.railwayAgency}</p>
                                                </div>
                                                <div class="line">
                                                    <p class="alignleft">Execution Agency</p>
                                                    <p class="aligncenter">:</p>
                                                    <p class="alignleft pdlr20px">${wObj.executedBy}</p>
                                                </div>
                                                <div class="line">

                                                    <p class="alignleft">Physical Progress (%)</p>
                                                    <p class="aligncenter">:</p>
                                                    <p class="alignleft pdlr20px">
                                                        <%-- <c:if
                                                            test="${wObj.project_status eq 'Closed' }">
                                                            </c:if> --%>
                                                            <c:if
                                                                test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">
                                                                100% </c:if>
                                                    </p>

                                                </div>
                                                <div class="line">
                                                    <p class="alignleft">Financial Progress (%)</p>
                                                    <p class="aligncenter">:</p>
                                                    <p class="alignleft pdlr20px">
                                                        <%-- <c:if
                                                            test="${wObj.project_status eq 'Closed' }">
                                                            100% </c:if> --%>
                                                            <c:if
                                                                test="${not empty wObj.year_of_completion or not empty wObj.completion_cost}">
                                                                100% </c:if>
                                                    </p>

                                                </div>
                                    </div>
                                </div>
                         </div>
                        
                        
                        	<!-- ============================================================================================== -->
                        	
                        	<div class="modal" id="workSalientFeatures${loop.index }">
		                        <div class="modal-header">Salient Features <span class="right modal-action modal-close"><span class="material-icons">close</span></span></div>
                                <div class="card main-clr">
                                    <div class="card-content" style="cursor: pointer;">
                                    	  <c:if test="${not empty (wObj.workSalientFeatures) && fn:length(wObj.workSalientFeatures) gt 0}">
	                                    	<c:forEach var="wsfObj" items="${wObj.workSalientFeatures }">
	                                            <div class="line">
	                                                <p class="alignleft w25">${wsfObj.category_fk}</p>
	                                                <p class="aligncenter">:</p>
	                                                <p class="alignleft pdlr20px w75">${wsfObj.description}</p>
	                                            </div>	
                                            </c:forEach>	
                                          </c:if>
                                          <c:if test="${fn:length(wObj.workSalientFeatures) le 0}">
                                           	<div class="line w25">
                                                <p class="alignleft pdlr20px w75">Not available</p>
                                            </div>
                                          </c:if>
                                    </div>
                                </div>
                         	</div>
		                         
                         <!-- ============================================================================================== -->
                        
                            <div class="row sub">
                            	<ul>
                            	<c:forEach var="wdObj" items="${wObj.workDetails }">  
                            		<script>console.log(${wdObj})</script>                          		
                            			<c:if test="${wdObj.title eq 'Sanctions Details'}">
										    <li>
											    <a href="#workDetails${loop.index}" class="modal-trigger">
											    	<div>
											    		<div class="btn diamond">
											         		<span>${wdObj.title }</span>
											    		</div>
													</div>
												</a>	
											</li>
										</c:if>
										<c:if test="${wdObj.title eq 'Salient Features'}">
										    <li>
											    <a href="#workSalientFeatures${loop.index }" class="modal-trigger">
											    	<div>
											    		<div class="btn diamond">
											         		<span>${wdObj.title }</span>
											    		</div>
													</div>
												</a>
											</li>
										</c:if>
                            	</c:forEach>
                            	</ul>
                            	<ul>
                            	<c:forEach var="wdObj" items="${wObj.workDetails }">
	                            		<c:if test="${(wdObj.title ne 'Sanctions Details') and (wdObj.title ne 'Salient Features') }">
	                            			<c:if test="${not empty wdObj.dashboard_url }">
                                					<li>
													    <a href='<%=request.getContextPath() %>/${wdObj.dashboard_url }' class="modal-trigger">
													    	<div>
													    		<div class="btn diamond">
													         		<span>${wdObj.title }</span>
													    		</div>
															</div>
														</a>
													</li>
                                				</c:if>
                                				<c:if test="${empty wdObj.dashboard_url }">
                                					<li>
													    <a href='#' class="modal-trigger">
													    	<div>
													    		<div class="btn diamond">
													         		<span>${wdObj.title }</span>
													    		</div>
															</div>
														</a>
													</li>
                                				</c:if>
	                            		</c:if>
                            	</c:forEach>
                            	</ul>
                                
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- footer starts here  -->
    <jsp:include page="./layout/footer.jsp"></jsp:include>
    <!-- footer ends here  -->
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('.modal').modal();
            $('.media-modal').modal({ dismissible: false });
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
                onCloseStart: function () {
                    $('#dropdown1').next('.modal-overlay').remove();
                }
            });
            $('.sidenav').sidenav();
            $('.collapsible').collapsible();
            $('.modal').modal({
                dismissible: true,
                'onOpenEnd': initCarousel
            });
            $('.tooltipped').tooltip();
            $('.carousel').carousel({
                fullWidth: true,
            });
            function initCarousel() {
                $('.carousel').carousel({
                    fullWidth: true
                });
            }
        });
        $('i#prev').click(function () {
            $(this).parent().parent().carousel('prev');
        });

        $('i#next').click(function () {
            // $('.carousel').carousel('next');
            $(this).parent().parent().carousel('next');
        });

        function pauseVideo() {
            $("video").each(function () {
                $(this).get(0).pause();
            });
        }

        function getTableauDashboard(work_id) {
            if ($.trim(work_id) != '') {
                var myParams = { work_id: work_id };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getDashBoradName",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                if ($.trim(val.dashboard_name) != '') {
                                    var parent_dashboard_id_sr_fk = $.trim(val.parent_dashboard_id_sr_fk);
                                    var name = $.trim(val.dashboard_name).toLowerCase();
                                    var link = name.replaceAll(" ", "-");
                                    if ($.trim(val.dashboard_id) == $.trim(val.parent_dashboard_id_sr_fk)) {
                                        window.location.href = "<%=request.getContextPath()%>/InfoViz/" + link
                                    } else {
                                        var parent_dashboard_id_sr_fk = $.trim(val.parent_dashboard_id_sr_fk);
                                        var myParams2 = { parent_dashboard_id_sr_fk: parent_dashboard_id_sr_fk };
                                        $.ajax({
                                            url: "<%=request.getContextPath()%>/ajax/getSubLink",
                                            data: myParams2, cache: false,
                                            success: function (data) {
                                                if (data.length > 0) {
                                                    $.each(data, function (i, val) {
                                                        if (parent_dashboard_id_sr_fk != '') {
                                                            var sub_link = $.trim(val.subLink).toLowerCase() + "/";
                                                            window.location.href = "<%=request.getContextPath()%>/InfoViz/" + sub_link + link
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }

                    }
                });

            }
        }

      /*   function movingOnPosition(a) {
            var headingItem = $(".heading h3");
            var position = headingItem.position();
            var itemTop = $(a).parent().offset().top;
            var itemLeft = $(a).parent().offset().left;
            $(a).parent().css({ 'left': itemLeft, 'top': itemTop, 'position': 'absolute' }).animate({
                'left': (position.left + (headingItem.width() / 2) - ($(a).width() / 2)),
                'top': (position.top - 10),
                'opacity': 0
            }, 500, function () {
                $(a).parent().css({ 'position': 'relative', 'opacity': 1, 'left': 'inherit', 'top': 'inherit' });
                headingItem.text($(a).text());
                $('#projectDetailsDiv,#workDetailsDiv,#projectsList,#worksList,#projectBackBtn,#workBackBtn').toggleClass('hidden');
            }
            );
            if ($(a).attr('data-href')) {
                getTableauDashboard($(a).attr('data-href'));
            }
        } */
        function movingOnPosition(a, ind) {
            var headingItem = $(".heading h3");
           //var position = headingItem.position();
           //var itemTop = $(a).parent().offset().top;
           //var itemLeft = $(a).parent().offset().left;
           // $(a).parent().css({ 'position': 'relative', 'opacity': 1, 'left': 'inherit', 'top': 'inherit' });
            headingItem.text($(a).text());
            $('.workOverview,.subWorkOverview').each(function (index, value) {
                if ($(value).hasClass('hidden')) {
                } else {
                    $(value).addClass('hidden');
                }
            });
            $('#full-col').toggleClass('m7 l7 m12 l10 offset-l1');
            $('#projectBackBtn,#projectDetailsDiv,#projectsList,#projectsBtns').addClass('hidden');
            $('#workBackBtn,#workDetailsOfProject' + ind+',#worksListOfProject'+ind).removeClass('hidden');  
            
           /*  $(a).parent().css({ 'left': itemLeft, 'top': itemTop, 'position': 'absolute' }).animate({
                'left': (position.left + (headingItem.width() / 2) - ($(a).width() / 2)),
                'top': (position.top - 10),
                'opacity': 0
            }, 500, function () {
                $(a).parent().css({ 'position': 'relative', 'opacity': 1, 'left': 'inherit', 'top': 'inherit' });
                headingItem.text($(a).text());
                $('.workOverview,.subWorkOverview').each(function (index, value) {
                    if ($(value).hasClass('hidden')) {
                    } else {
                        $(value).addClass('hidden');
                    }
                });
                $('#full-col').toggleClass('m7 l7 m12 l10 offset-l1');
                $('#projectBackBtn,#projectDetailsDiv,#projectsList,#projectsBtns').addClass('hidden');
                $('#workBackBtn,#workDetailsOfProject' + ind+',#worksListOfProject'+ind).removeClass('hidden');  
                
               // $('#worksListOfProject'+ind).removeClass('hidden').trigger('classChange');
            }); */
            
           /*  $('#worksListOfProject'+ind).on('classChange', function() {
            	$('#worksListOfProject' + ind+' li').addClass('pos-abs-down').
                animate({
               		'bottom':'inherit',
               		'right':'inherit',
               		'opacity':1,               		
               	},1500,function(){
               		$('#worksListOfProject' + ind+' li').css('position','relative');	
               	});
            });             */
        }

        function goToProject() {
            var headingItem = $(".heading h3");
            headingItem.text(headingItem.attr('data-title'));
            $('#projectDetailsDiv,#workDetailsDiv,#projectsList,#worksList,#projectBackBtn,#workBackBtn,#projectsBtns').toggleClass('hidden');
            $('.workOverview,.subWorkOverview').each(function (index, value) {
                if ($(value).hasClass('hidden')) {
                } else {
                    $(value).addClass('hidden');
                }
            });
            $('#full-col').toggleClass('m7 l7 m12 l10 offset-l1');
        }
    </script>
</body>

</html>