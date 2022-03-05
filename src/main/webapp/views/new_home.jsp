<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Welcome to PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
	
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/li-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
     <style type="text/css">
		/* 3rd demo start */
        
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
				  background: #fff;
				  margin: 40px auto;
				  /* position: relative; */
				  height: 70px;
				  width: 210px;
				}
				.diamond span {
				  display: inline-block;
				  width: 160px;
				  font-size: 18px;
				  font-weight: bold;
				}
				.diamond:before,
				.diamond:after {
				  /* content: ''; */
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
				  background: rgba(21, 101, 192, 0.8);
				  /* filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
		            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
		            transform: scale(1.1); */
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
				.hexagon {
				  position: relative;
				  width: 150px;
				  height: 81.08108108px;
				  line-height: 75px;
				  background-color: #fff;
				  margin: 50px auto;
				  transform: rotate(90deg)
				}
				.hexagon:before,
				.hexagon:after {
				  content: "";
				  position: absolute;
				  width: 0;
				  left: 0;
				  border-left: 75px solid transparent;
				  border-right: 75px solid transparent;
				}
				.hexagon:before {
				  bottom: 99.40257%;
				  border-bottom: 50px solid #fff;
				}
				.hexagon:after {
				  top: 98.40257%;
				  width: 0;
				  border-top: 50px solid #fff;
				}
				.hexagon span {
				  display: inline-block;
				  vertical-align: middle;
				  line-height: normal;
				  margin: 30px 10px;
				  transform: rotate(-90deg);
				  color: #000;
				  width: 80px;
				}
				.hexagon:hover {
				  background: rgba(255, 255, 255, 0.9);
				}
				.hexagon:hover:before {
				  border-bottom: 50px solid rgba(255, 255, 255, 0.9);
				}
				.hexagon:hover:after {
				  border-top: 50px solid rgba(255, 255, 255, 0.9);
				}
        
        /* 3rd demo end */
        
        /* map styles start */
          .map-btn-holder .btn {
            background-color: #1565C0cc;
           /*  background-image: linear-gradient(to right, #16D58A, #00BDE7); */
            /* background-color:#01BAEF; */
            background-color: transparent;
             border-radius: 50%; 
            box-shadow: none;
            font-size: 2.25rem;
            line-height: 2.5rem;
            height: 5rem;
            padding: 14px 8px;
    		box-shadow: 0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 12%), 0 1px 5px 0 rgb(0 0 0 / 20%);
        }

        .map-btn-holder .fa {
            font-size: 200%;
            vertical-align: middle;
            height: 100%;
            margin-top: 5%;
        }


		.modal {
		            max-height: 100%;
		            top: 0 !important;
		        }
		  .modal-header {
	           text-align: center;
	           background-color: #1565C0cc;
	           / background-image: linear-gradient(to right, #16D58A, #00BDE7); /
	           background-color:#01BAEF;
	           color: #fff;
	           margin: -24px -24px 20px !important;
	           padding: 1rem;
	       }
	       .map-btn-holder{
	       	margin-top: 30px;
	       	text-align: center
	       }
        
        /* map styles end */
        .btn-menu ul {
            text-align: center;
        }

        .btn-menu ul li {
            padding: 20px;
            font-size: 18px;
        }

        .btn-menu ul a li {
            color: #000;
            font-weight: bold;
            z-index: 100;
        }

        .btn-menu ul a li button:hover {
            color: #fff;
            font-weight: bold;
            text-decoration: underline;
        }
		.heading img{width: 110%;margin-top: 30px;}
        .heading h3 {
            color: #fff;
            font-weight: bold;
            padding: 10px;
            /* background: #1565C0; */
            vertical-align: middle;
            width: 100%;
        }
		/* .heading h3:before,
			.heading h3:after {
			  content: '';
			  position: absolute;
			  width: 0;
			  height: 0;
			  /* border: 30px solid transparent; */
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

        li {
            position: relative;
        }

        .row .col {
            padding: 20px 0.75rem !important;
        }
		 
		@media(max-width: 1560px){
			.heading h3 {
                font-size: 32px;
            }
		} 
        @media(max-width: 1366px) {
            .heading h3 {
                font-size: 24px;
            }
            .map-btn-holder .fa {
            font-size: 3.3rem;
            }
        }

        .btn-menu a {
            -webkit-animation: fadeIn 0.5s linear;
            animation: fadeIn 0.5s linear;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }

        .btn-menu a li {
            /* position: relative; */
            display: block;
            margin-bottom: 5px;
            padding: 10px;
            text-align: center;
             border-radius: 50%;
            
  background-color: transparent;
    border-radius: 10%;
   padding: 14px 8px;
    
    
 /*    box-shadow: 0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 12%), 0 1px 5px 0 rgb(0 0 0 / 20%); */
               

        }

        /* .btn-menu a:nth-child(1) {
            -webkit-animation-delay: 0.25s;
            animation-delay: 0.25s;
        }

        .btn-menu a:nth-child(2) {
            -webkit-animation-delay: 0.5s;
            animation-delay: 0.5s;
        }

        .btn-menu a:nth-child(3) {
            -webkit-animation-delay: 0.75s;
            animation-delay: 0.75s;
        }

        .btn-menu a:nth-child(4) {
            -webkit-animation-delay: 1s;
            animation-delay: 1s;
        }

        .btn-menu a:nth-child(5) {
            -webkit-animation-delay: 1.25s;
            animation-delay: 1.25s;
        }

        .btn-menu a:nth-child(6) {
            -webkit-animation-delay: 1.50s;
            animation-delay: 1.50s;
        }

        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0;
                top: 100px;
            }

            75% {
                opacity: 0.5;
                top: 0px;
            }

            100% {
                opacity: 1;
            }
        }

        .btn-menu a {
            position: relative;
            text-align: center;
            text-transform: uppercase;
            -webkit-animation: fadeIn 0.5s linear;
            animation: fadeIn 0.5s linear;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }
        .btn-menu a:hover{
        	text-decoration:none;
        	color:#fff;
        }

        .btn-menu a:nth-child(1) {
            -webkit-animation-delay: 0.25s;
            animation-delay: 0.25s;
        }

        .btn-menu a:nth-child(2) {
            -webkit-animation-delay: 0.5s;
            animation-delay: 0.5s;
        }

        .btn-menu a:nth-child(3) {
            -webkit-animation-delay: 0.75s;
            animation-delay: 0.75s;
        }

        .btn-menu a:nth-child(4) {
            -webkit-animation-delay: 1s;
            animation-delay: 1s;
        }

        .btn-menu a:nth-child(5) {
            -webkit-animation-delay: 1.25s;
            animation-delay: 1.25s;
        }

        .btn-menu a:nth-child(6) {
            -webkit-animation-delay: 1.50s;
            animation-delay: 1.50s;
        }

        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0;
                top: 100px;
            }

            75% {
                opacity: 0.5;
                top: 0px;
            }

            100% {
                opacity: 1;
            }
        } */

        .btn-menu li button:hover {
            /* filter: drop-shadow(2px 4px 6px black); */
            filter: drop-shadow(-2px -2px 2px #42A5F5) drop-shadow(3px 3px 5px #42A5F5);
            filter: drop-shadow(-2px -2px 2px #9ED1FA) drop-shadow(2px 2px 3px #9ED1FA);
            transform: scale(1.1);
            transition: all 0.2s;
            transition-duration: 0.2s;
        }

        @media(max-width: 1366px) {
            .heading h3 {
                font-size: 24px;
            }
            .map-btn-holder .btn{height: 4rem;padding: 8px;}
        }

       /*  .tracking-in-expand-fwd {
            -webkit-animation: tracking-in-expand-fwd 2.8s cubic-bezier(0.215, 0.610, 0.355, 1.000) both;
            animation: tracking-in-expand-fwd 2.8s cubic-bezier(0.215, 0.610, 0.355, 1.000) both;
        }

        @-webkit-keyframes tracking-in-expand-fwd {
            0% {
                letter-spacing: -0.5em;
                -webkit-transform: translateZ(-700px);
                transform: translateZ(-700px);
                opacity: 0;
            }

            40% {
                opacity: 0.6;
            }

            100% {
                -webkit-transform: translateZ(0);
                transform: translateZ(0);
                opacity: 1;
            }
        }

        @keyframes tracking-in-expand-fwd {
            0% {
                letter-spacing: -0.5em;
                -webkit-transform: translateZ(-700px);
                transform: translateZ(-700px);
                opacity: 0;
            }

            40% {
                opacity: 0.6;
            }

            100% {
                -webkit-transform: translateZ(0);
                transform: translateZ(0);
                opacity: 1;
            }
        } */

        body {
            margin: 0;
            font-family: 'Open Sans', sans-serif;
            /* background: black url("/pmis/resources/images/login-background.jpg") no-repeat center center; */
            background: #6596ff;
            background-size: cover;
            background-repeat-y: repeat;
            
        }
         body:after{
         	backdrop-filter: blur(5px);
         }

        body:after {
            content: '';
            /* background-color: rgba(0, 0, 0, .1);  */
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
         @media(max-width: 1024px){
        	.heading h3{font-size: 18px;}
        }
        @media(max-width: 768px){
        	.heading h3{width: 90%;}
        	.diamond span {
			    display: flex;
			    width: 92px;
			    margin-top: 0;
			    line-height: normal;
			    vertical-align: middle;
			    align-content: center;
			    align-items: center;
			    height: 100%;
			}
			.diamond:after{top: 0;}
        }
        @media(max-width: 575px){
        	.heading h3{width: 85%;line-height: 1.2;font-size: 15px;}
        	.map-btn-holder{margin: 0; margin-top: 33px;}
        	/* .heading h3:before, .heading h3:after{
        		border: 28px solid transparent;
        	}
        	.heading h3:before {
			    right: 100%;
			    border-right-color: #1565C0;
			}
			.heading h3:after {
			    left: 100%;
			    border-left-color: #1565C0;
			} */
        	.diamond{height: 50px;margin: 5px auto;}
        	.diamond:before, .diamond:after{
        		border: 25px solid transparent;
        	}
        	.diamond:before {
			    right: 100%;
			    border-right-color: #fff;
			}
			.diamond:after {
			    left: 100%;
			    border-left-color: #fff;
			}
			.diamond span{width: 60px;}
			.map-btn-holder .fa{font-size: 2rem;}
			.map-btn-holder .btn{padding: 0 20px;}
        }

        /* 2nd demo start */

        .btn-menu-1 {
            overflow: auto;
        }

        .slide {
            letter-spacing: 1px;
            background: #42a5f5;
            background:#9ED1FA;
            background-image: linear-gradient(90deg, red, blue);
            color: white;
            position: relative;
            outline: none;
            border: none;
            height: 80px;
            width: 14em;
            font-size: 18px;
            z-index: 2;
            transition: 0.01s 0.23s ease-out all;
            overflow: hidden;
            border-radius: 5px;
        }

        .slide:before {
            content: "";
            position: absolute;
            left: 0;
            top: 0;
            height: 100%;
            width: 55%;
            background: #f56661;
            background:darkgray;
            background-image: linear-gradient(90deg, red, #71008E);
            z-index: -1;
            transition: 0.3s ease-in all;
        }

        .slide:after {
            content: "";
            position: absolute;
            left: -5%;
            top: 5%;
            height: 90%;
            width: 5%;
            background: #084192;
            background:gray;
            background-image: linear-gradient(90deg, red, #71008E);
            z-index: -1;
            transition: 0.4s 0.02s ease-in all;
        }

        .slide:hover {
            cursor: pointer;
            color: transparent;
            box-shadow: 2px 2px 5px #666, -2px -2px 6px #333, 3px 3px 5px #111;
        }

        .slide:hover:before {
            left: 100%;
            width: 25%;
        }

        .slide:hover:after {
            left: 100%;
            width: 70%;
        }

        .icon-right {
            position: absolute;
            top: 5px;
            right: 35px;
        }

        .icon-right:after {
            font-family: "FontAwesome";
            content: "→";
            font-size: 24px;
            display: inline-block;
            position: relative;
            top: 35px;
            transform: translate3D(0, -50%, 0);
        }

        .signature {
            position: fixed;
            font-family: sans-serif;
            font-weight: 100;
            bottom: 10px;
            left: 0;
            letter-spacing: 4px;
            font-size: 10px;
            width: 100vw;
            text-align: center;
            color: white;
            text-transform: uppercase;
            text-decoration: none;
        }

        .text-align-left {
            text-align: left;
        }

        .mt3em {
            margin-top: 11em;
        }
		 /* .btn-menu ul a li #diamond:hover::after {
            content: "";
            position: absolute;
            width: 50%;
            height: 70%;
            background-color: inherit;
            border-radius: 50%;
            background-color: #42a5f5;
            z-index: -1;
            left: 4.3em;
            top: 2rem;
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
            } */
        
        /* 2nd demo end */
        
       
    </style>
</head>

<body>

    <!-- header  starts-->
  <jsp:include page="./layout/header.jsp"></jsp:include>
    <!-- header ends  -->
    
    <!-- li  starts-->

    <!-- li ends  -->

   <!-- <div class="bg" id="train"></div> -->
   	<div class="container">
        <div class="row">
            <div class="col s12 m12 l12">
            	<div class="row">
            	<div class="col s1 m1 l1"></div>
            		<div class="col s1 m1 l1">
            		<div class="heading">
            			<img src="/pmis/resources/images/mrvclogo.png" alt="Logo">
            		</div>
            	</div>
                <div class="col l8 m8 s8">
                	<div class="heading color-change-2x">
                    <center>
                    
                    <h3 class="tracking-in-expand-fwd">Mumbai Railway Vikas Corporation Limited</h3></center>
                </div>
                </div>
                <div class="col l1 m1 s1">
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
                <div class="btn-menu">
                    <%-- <ul>
                    	<c:forEach var="project" items="${projects }">
	                        <a href="javascript:getProjectOverview('${project.project_id }');"><li class="col s6 m4 l4">
	                            
	                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="100%" height="130px" viewBox="0 0 1440 130" preserveAspectRatio="none" style="transform-origin: 50% 50%;position: absolute;height: 25px;left: 0; top: -23px;">
	                               <path id="wave-bottom-white" fill="white" d="M0,56.050526 C121.352261,18.683509 262.172393,0 422.460394,0 C662.892396,0 818.88453,115.474324 1058.94661,115.474324 C1218.988,115.474324 1342.92081,94.808741 1440,56 L1440,130 L0,130 L0,56.050526 Z"/></svg>
	                                    ${project.project_name }
	                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="100%" height="130px" viewBox="0 0 1440 130" preserveAspectRatio="none" style="transform-origin: 50% 50%; transform: scale(-1, -1);position: absolute;height: 23px; left: 0;top: 62px;">
	                                            <path id="wave-bottom-white" fill="white" d="M0,56.050526 C121.352261,18.683509 262.172393,0 422.460394,0 C662.892396,0 818.88453,115.474324 1058.94661,115.474324 C1218.988,115.474324 1342.92081,94.808741 1440,56 L1440,130 L0,130 L0,56.050526 Z"/></svg>
	                        </li></a>
                        </c:forEach>
                    </ul> --%>
                    
                    <%-- <ul class="">
                    	<c:forEach var="project" items="${projects }">
	                        <a href="javascript:getProjectOverview('${project.project_id }');">
	                        	<li class="col s6 m4 l4 text-align-left">
	                            	<button class="explore slide">${project.project_name }<span class="icon-right"></span></button>
	                        </li></a>
                        </c:forEach>
                    </ul> --%>
                    <div class="row">
                    	<ul class="">
                    	<c:forEach var="project" items="${projects }">
	                        <a href="javascript:getProjectOverview('${project.project_id }');">
	                        	<li class="col s6 m4 l4 text-align-left">
	                            <div id="diamond">
								    <div class="btn diamond">
								         <span>${project.project_name }</span>
								    </div>
								</div>
	                        </li></a>
                        </c:forEach>
                    </ul>
                    </div>                    
                </div>
            </div>
        </div>
    </div>
   
    
    <form action="<%=request.getContextPath()%>/project-overview" id="projectOverviewForm" method="post">
    	<input type="hidden" id="project_id_overview" name="project_id">
    </form>

<!-- footer starts here  -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
<!-- footer ends here  -->
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script type="text/javascript">
  	
  	$(document).ready(function () {
  		var procedureResult = '${procedureResult}';
  		if($.trim(procedureResult) != ''){
  			alert(procedureResult);
  		}
  		
  		$('#mapmodal').modal({ dismissible: true });
  	});
  	
	function getProjectOverview(project_id){
  		$("#project_id_overview").val(project_id);
  		$("#projectOverviewForm").submit();
  	}
  </script>
</body>

</html>