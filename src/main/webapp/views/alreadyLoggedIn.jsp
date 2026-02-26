<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SynTrack - PMIS Login</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     <link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/md5.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/style.css">  
    
    <style type="text/css">
		/* Change the white to any color ;) */
		input:-webkit-autofill,
		input:-webkit-autofill:hover, 
		input:-webkit-autofill:focus, 
		input:-webkit-autofill:active  {
		    -webkit-box-shadow: 0 0 0 30px rgba(2, 36, 202, 0.70) inset !important;
		}		
		.setting-icon{
			color:inherit;
		}
		.row.homepage{
			-webkit-box-shadow: 0px 0px 25px 10px rgba(0,0,155,.4);
			-moz-box-shadow: 0px 0px 25px 10px rgba(0,0,155,.4);
			box-shadow: 0px 0px 25px 10px rgba(0,0,155,.4);
		}
		.toggle-password{
			position: absolute;
		    color: #f0f0f0;
		    right: 10px;
		    top: 14px;
		    font-size: 1.35rem;
		    cursor:pointer;
		}
		
		@media screen and (min-width: 480px) {
		  #support {
		    width:38%;
		  }
		}
		.help-icon{
		    position: absolute;
		    display: inline-block;
		    right: .5%;
		}
		.help-icon img{
		    width: 40px;
		    height: 40px;
		    position: relative;
		    right: 5px;
		    bottom: 5px;
		}
		#help-dropdown.dropdown-content{
		    width: 250px !important;
		    bottom: 31px;
		    top: inherit !important;
		}
		#help-dropdown.dropdown-content li>a {
		    color: #444;
		}
		#help-dropdown.dropdown-content li>a:hover {
		    color: #000;
		}
		.dropdown-content li>a>i{
		    float:right;
		    margin:auto;
		}
		#help-dropdown.dropdown-content li>a i {
		    color: #444;
		}
		#help-dropdown.dropdown-content li>a:hover i,
		#help-dropdown.dropdown-content li>a i:hover{
		    color: #26a69a;
		}
		
		.support-link{
		    padding:14px 16px;
		    text-align:center;
		}
		.support-link >a{
		    padding:0 !important;
		    font-size: .9rem !important;
		}
		.support-link >a:hover{
		    color: #26a69a !important;
		}
		
		.page-loader ,.page-loader-2,.page-loader-3{
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}
		.preloader-wrapper{
		    top: 45%!important;
		    left:47%!important;
		}

    </style>
</head>

<body style="background: black url(<%=CommonConstants.LOGIN_BACKGROUND_IMAGE_URL%>) no-repeat center center;background-size: cover;  background-color: rgba(125,0,0,0.32);
    background-blend-mode: overlay;">
 	       <!-- header starts -->
 	  	  <nav>
	        <div class="nav-wrapper bg">
	          <h3 style="margin:0" class="text">Welcome to WR PMIS </h3>  
	        </div>
	      </nav>
      	<!-- header ends -->
        <div class="container">        
    		<div class="row mar-top">
     			<div id="support" class="col"></div>
     			
     			<!-- <h3 style="color: #fff;">System IP Address : <span id="systemIPA">Loading...</span></h3>
                <h3 style="color: #fff;">Public IP Address : <span id="publicIPA">Loading...</span></h3> -->
                
		        <form class="col s12 m3 " action="<%=request.getContextPath()%>/logout-from-all-devices" id="logoutForm" name="logoutForm" method="post" >
		        	<div class="row homepage">
			            <img src="/wrpmis/resources/images/mrvclogo.png" alt="mrvc logo" class="card-img">
			            <div>
			             	<p style="margin: 5px;" class="error">${error}</p>
			            </div>
			            <input type="hidden" id="user_id" name="user_id" value="${sessionScope.USER_ID }">
			            <div class="input-field col offset-s1 s10 text-center">
			            	<button type="submit" class="btn bgb" style="width:100%">Logout</button>
			            </div>
			        </div>
		         </form>
	      	</div>
    	</div>
    	
    	<!-- Page Loader -->
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
	
	
      <!-- footer starts here -->

      <footer class="page-footer">
        <div class="container">
            <p class="footer-text"> &copy; <span id="year"></span> @ 
            	<a href="https://mrvc.indianrailways.gov.in/" target="_blank">mrvc.indianrailways.gov.in</a> | Designed & Developed by
               <a href="https://www.synergizglobal.com" target="_blank"> <img src="/wrpmis/resources/images/synergiz.png" alt="synergiz logo" class="footer-logo"> </a>
                <a class="help-icon dropdown-trigger"  href='#' data-target='help-dropdown'>
        			<img src="/wrpmis/resources/images/help_icon_white.svg">        
        		</a>
        	</p>
	          <!-- Dropdown Structure -->
			  <ul id='help-dropdown' class='dropdown-content blue lighten-5'>
			    <li><a href="/wrpmis/PMIS User Manual Ver-1.pdf" target="_blank">PMIS Manual <i class="fa fa-download"></i></a></li>
			    <li><a href="/wrpmis/Primmavera P6_ppm_usermanual  Ver-19.12.pdf" target="_blank">Primavera Manual <i class="fa fa-download"></i></a></li>
			    <li class="divider" tabindex="-1"></li>
			    <li class="support-link"> Contact us :  <br> <a href="mailto:support_pmis@mrvc.gov.in">support_pmis@mrvc.gov.in</a></li>
			  </ul>
        </div>
      </footer>
          <!-- footer ends here -->
	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js" ></script>
	<script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js" ></script>  
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js" ></script>
</body>

</html>
