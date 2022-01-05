<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Overview Dashboard - Reports - PMIS</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet"	href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<style>
		.main-menu-collapse{
			padding:0;
		}
		.main-menu-collapse .collapsible{
			margin:0;
		}
		.main-menu-collapse .special-padding{	
			padding:0;		
			padding-left:max(1rem,15px);
			position:relative;
		}
		.collapsible{
			border:none !important;
		}
		.main-menu li .collapsible-header{
			/* background-color:#DAEAFB;
			background-color:#E4EFFC;
			background-color:#A3C9F5; */
			background-color:#deebf7;
			border:none;
		}
		.main-menu li .collapsible-body{
			/* background-color:#FDDAD9; */
			background-color:#EDF4FD;			
			border:none;
		}
		.special-padding .collapsible li:not(.active) .collapsible-header{
			background-color:transparent;
		}
				
		.sub-menu{		    
		    position:relative;		
		}
		.sub-menu:after{
			content:"\f078";
			font: normal normal normal 14px/1 FontAwesome;
			font-size: inherit;
		    text-rendering: auto;
		    -webkit-font-smoothing: antialiased;
		    position:absolute;
		    right:1rem;
		    top:40%;
		}
		.active >.sub-menu:after{
			content:"\f077";
		}
		
		
		
		
		.2ndModel{
			box-shadow :0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2) !important;
		}
		.showHide{
			display:inline-block;			
		}
		.hideText .showHide,
		.hideText .sub-menu:after{
			display:none;
		}
		.hideText{
			display:inline-block;
		}
		.hideText .material-icons{
			margin-right:0;
		}
	</style>
</head>
<body>
	<!-- header included -->
	<%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
	
	<!-- model 1 which closes entire navigation -->
	
	<div class="container">
	    <div class="row">
	        <div class="col s12 m4">
	            <ul class="collapsible">
	                <li class="active">
	                    <div class="collapsible-header"><i class="fa fa-bars"></i></div>
	                    <div class="collapsible-body main-menu-collapse">
	                        <ul class="collapsible main-menu">
	                            <li>
	                                <div class="collapsible-header sub-menu"><i class="material-icons">filter_drama</i>First
	                                </div>
	                                <div class="collapsible-body special-padding">
	                                    <ul class="collapsible">
	                                        <li>
	                                            <div class="collapsible-header"><i class="material-icons">place</i>Second
	                                            </div>
	                                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                                        </li>
	                                        <li>
	                                            <div class="collapsible-header sub-menu"><i
	                                                    class="material-icons">place</i>Second</div>
	                                            <div class="collapsible-body special-padding">
	                                                <ul class="collapsible">
	                                                    <li>
	                                                        <div class="collapsible-header">Second</div>
	                                                        <div class="collapsible-body"><span>Lorem ipsum dolor sit
	                                                                amet.</span></div>
	                                                    </li>
	                                                    <li>
	                                                        <div class="collapsible-header">Second</div>
	                                                        <div class="collapsible-body">
	
	                                                        </div>
	                                                    </li>
	                                                    <li>
	                                                        <div class="collapsible-header">Second</div>
	                                                        <div class="collapsible-body"><span>Lorem ipsum dolor sit
	                                                                amet.</span></div>
	                                                    </li>
	                                                </ul>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="collapsible-header"><i class="material-icons">place</i>Second
	                                            </div>
	                                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </li>
	                            <li>
	                                <div class="collapsible-header"><i class="material-icons">place</i>Second</div>
	                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                            </li>
	                            <li>
	                                <div class="collapsible-header"><i class="material-icons">whatshot</i>Third</div>
	                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                            </li>
	                        </ul>
	                    </div>
	                </li>
	            </ul>
	
	        </div>
	        <div class="col s12 m8">
	            <p> content goes here </p>
	        </div>
	    </div>
	</div>


<!-- model 2 which shows icons on navigation -->
	
	<div class="container">
	    <div class="row">
	        <div class="col s12 m4">
	                <div>
	                    <div class="collapsible-header 2ndModel" onclick="toggleMenu()"><i class="fa fa-bars"></i>Logo</div>
	                    <div class=" main-menu-collapse">
	                        <ul class="collapsible main-menu" id="2ndModel">
	                            <li>
	                                <div class="collapsible-header sub-menu"><i class="material-icons">filter_drama</i> 
	                                	<span class="showHide">First</span>
	                                </div>
	                                <div class="collapsible-body special-padding">
	                                    <ul class="collapsible">
	                                        <li>
	                                            <div class="collapsible-header"><i class="material-icons">place</i>
	                                            	<span class="showHide">Second</span>
	                                            </div>
	                                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                                        </li>
	                                        <li>
	                                            <div class="collapsible-header sub-menu"><i
	                                                    class="material-icons">place</i><span class="showHide">Second</span></div>
	                                            <div class="collapsible-body special-padding">
	                                                <ul class="collapsible">
	                                                    <li>
	                                                        <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Second</span></div>
	                                                        <div class="collapsible-body"><span>Lorem ipsum dolor sit
	                                                                amet.</span></div>
	                                                    </li>
	                                                    <li>
	                                                        <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Second</span></div>
	                                                        <div class="collapsible-body">
	
	                                                        </div>
	                                                    </li>
	                                                    <li>
	                                                        <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Second</span></div>
	                                                        <div class="collapsible-body"><span>Lorem ipsum dolor sit
	                                                                amet.</span></div>
	                                                    </li>
	                                                </ul>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="collapsible-header"><i class="material-icons">place</i><span class="showHide">Second</span>
	                                            </div>
	                                            <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </li>
	                            <li>
	                                <div class="collapsible-header"><i class="material-icons">place</i><span class="showHide">Second</span></div>
	                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                            </li>
	                            <li>
	                                <div class="collapsible-header"><i class="material-icons">whatshot</i><span class="showHide">Third</span></div>
	                                <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
	                            </li>
	                        </ul>
	                    </div>
	                </div>	
	        </div>
	        <div class="col s12 m8">
	            <p> content goes here </p>
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

	<!-- footer included -->
<%-- 	<jsp:include page="../layout/footer.jsp"></jsp:include> --%>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<script>
		$(document).ready(function(){
		    $('.collapsible').collapsible();
		  });
		
		function toggleMenu(){
			$('#2ndModel,.2ndModel').toggleClass('hideText');
		}
	</script>
	</body>
	</html>