<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SynTrack - PMIS Login</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/md5.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/style.css">  
    
    <style type="text/css">
    	.help-txt{
    		position: relative;
		    top: -1.1em;
		    right: .5em;
		    font-size: 1rem;
    	}
    	.f-logo{width: 27px;vertical-align: bottom;}
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
		    bottom: 0px;
		}
		#help-dropdown.dropdown-content{
		   /*  width: 250px !important; */
		   width: auto !important;
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
		    /* margin:auto; */
		    margin: auto 10px;
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
		
		.support-link{
			    font-size: 16px;
    			font-weight: 500;
		}
		
		.confetti {
    /*display: flex;
    justify-content: center;
    align-items: center;*/
    position: absolute;
    width: 90%;
    height: 100%;
    overflow: hidden;
    z-index: -1;
  top: 0;
  left: 100px;
}
.confetti-piece {
    position: absolute;
    width: 10px;
    height: 20px;
    background: #ffd300;
    top: 0;
    opacity: 0;
    border: 1px solid #000;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
.confetti-piece:nth-child(1) {
    left: 5%;
    -webkit-transform: rotate(-40deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 182ms;
    -webkit-animation-duration: 1116ms;
}
.confetti-piece:nth-child(2) {
    left: 10%;
    -webkit-transform: rotate(4deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 161ms;
    -webkit-animation-duration: 1076ms;
}
.confetti-piece:nth-child(3) {
    left: 15%;
    -webkit-transform: rotate(-51deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 481ms;
    -webkit-animation-duration: 1103ms;
}
.confetti-piece:nth-child(4) {
    left: 20%;
    -webkit-transform: rotate(61deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 334ms;
    -webkit-animation-duration: 708ms;
}
.confetti-piece:nth-child(5) {
    left: 25%;
    -webkit-transform: rotate(-52deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 302ms;
    -webkit-animation-duration: 776ms;
}
.confetti-piece:nth-child(6) {
    left: 30%;
    -webkit-transform: rotate(38deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 180ms;
    -webkit-animation-duration: 1168ms;
}
.confetti-piece:nth-child(7) {
    left: 35%;
    -webkit-transform: rotate(11deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 395ms;
    -webkit-animation-duration: 1200ms;
}
.confetti-piece:nth-child(8) {
    left: 40%;
    -webkit-transform: rotate(49deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 14ms;
    -webkit-animation-duration: 887ms;
}
.confetti-piece:nth-child(9) {
    left: 45%;
    -webkit-transform: rotate(-72deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 149ms;
    -webkit-animation-duration: 805ms;
}
.confetti-piece:nth-child(10) {
    left: 50%;
    -webkit-transform: rotate(10deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 351ms;
    -webkit-animation-duration: 1059ms;
}
.confetti-piece:nth-child(11) {
    left: 55%;
    -webkit-transform: rotate(4deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 307ms;
    -webkit-animation-duration: 1132ms;
}
.confetti-piece:nth-child(12) {
    left: 60%;
    -webkit-transform: rotate(42deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 464ms;
    -webkit-animation-duration: 776ms;
}
.confetti-piece:nth-child(13) {
    left: 65%;
    -webkit-transform: rotate(-72deg);
    -webkit-animation: makeItRain 1000ms 20s ease-out;
    -webkit-animation-delay: 429ms;
    -webkit-animation-duration: 818ms;
}
.confetti-piece:nth-child(odd) {
    background: #7431e8;
}
.confetti-piece:nth-child(even) {
    z-index: 1;
}
.confetti-piece:nth-child(4n) {
    width: 3px;
    height: 8px;
    -webkit-animation-duration: 2000ms;
}
.confetti-piece:nth-child(3n) {
    width: 3px;
    height: 8px;
    -webkit-animation-duration: 2500ms;
    -webkit-animation-delay: 1000ms;
}
.confetti-piece:nth-child(4n-7) {
  background: red;
}
@-webkit-keyframes makeItRain {
    from {opacity: 0;}
    100% {opacity: 1;}
    to {-webkit-transform: translateY(550px);visibility: hidden;}
}


.blast{
	display: flex;
	justify-content: space-evenly;
	position: absolute;
	width: 48%;
}
.blast-box:nth-child(1){
	top: 50px;
    position: relative;
}
.blast-box:nth-child(3){
	top: 100px;
    position: relative;
}
:root{
    --fc1: #f20;
    --fc2: #ff0;
    --fc3: #04f;
    --fc4: #0f4;
    --fc5: #f0f;
    --fc: var(--fc1);
}

.fireworks:before, .fireworks:after{
    position: absolute;
    content: '';
    width: 10px;
    height: 10px;
    border-radius: 50%;
    box-shadow: -120px 0 var(--fc),
                120px 0 var(--fc),
                0 120px var(--fc),
                0 -120px var(--fc),
                45px 45px var(--fc),
                -45px 45px var(--fc),
                85px 85px var(--fc),
                -85px 85px var(--fc),
                45px -45px var(--fc),
                -45px -45px var(--fc),
                85px -85px var(--fc),
                -85px -85px var(--fc),
                65px 0 var(--fc),
                -65px 0 var(--fc),
                0 65px var(--fc),
                0 -65px var(--fc),
                100px 45px var(--fc),
                -100px 45px var(--fc),
                -50px 100px var(--fc),
                50px 100px var(--fc),
                100px -45px var(--fc),
                -100px -45px var(--fc),
                -50px -100px var(--fc),
                50px -100px var(--fc),
                5px 10px var(--fc);
    animation: 1s bang ease-out infinite backwards, 
                1s gravity ease-in infinite backwards, 
                5s position linear infinite backwards;
}
.fireworks:after{
    animation-delay: 1.25s, 1.25s, 1.25s;
    animation-duration: 1.25s, 1.25s, 6.25s;
}
@keyframes bang {
    from {
        box-shadow: 0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff,
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff,
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff, 
                    0 0 #fff,
                    0 0 #fff; 
    }
}

@keyframes gravity {
    to {
        transform: translate(-10px, 150px);
        height: 6px;
        width: 4px;
        opacity: 0; 
    }
}
@keyframes position {
    0%, 19.9% {
        margin-top: 0%;
        margin-left: -300px;
        --fc: var(--fc1);
    }

    20%, 39.9% {
        margin-top: 0%;
        margin-left: 70%; 
        --fc: var(--fc2);
    }

    40%, 59.9% {
        margin-top: 5%;
        margin-left: 20%; 
        --fc: var(--fc3);
    }

    60%, 79.9% {
        margin-top: 10%;
        margin-left: 70%;
        --fc: var(--fc4);
    }

    80%, 99.9% {
        margin-top: 10%;
        margin-left: 100%; 
        --fc: var(--fc5);
    } 
}
    </style>
</head>

<body>
 	       <!-- header starts -->
 	  	  <nav>
	        <div class="nav-wrapper bg">
	          <!-- <h3 style="margin:0" class="text">Welcome to MRVC PMIS </h3> -->
	          <h3 style="margin:0" class="text">MRVC Celebrates its 25th Anniversary </h3>  
	        </div>
	      </nav>
      <!-- header ends -->
      <!-- side navigation starts here -->
	      <ul id="slide-out" class="sidenav">
		    <li></li>
		    <li><span>Login card Position</span></li>
		    <li>
				    <p>
				      <label>
				        <input class="with-gap" name="group1" type="radio" value="left" />
				        <span>Left</span>
				      </label>
				    </p>
				    <p>
				      <label>
				        <input class="with-gap" name="group1" type="radio" value="middle" />
				        <span>Middle</span>
				      </label>
				    </p>
				    <p>
				      <label>
				        <input class="with-gap" name="group1" type="radio" value="right" />
				        <span>Right</span>
				      </label>
				    </p>
			</li>
		    <li><div class="divider"></div></li>
		    <li><span>Background Image</span></li>
		    <li>
				  <!-- Modal Trigger -->
  				<a class="waves-effect waves-light btn bgb modal-trigger" href="#modal1">Image</a>

			</li>
		</ul>
		      <!-- side navigation ends here -->
		
  	    <a data-target="slide-out" class="sidenav-trigger setting-icon"><i class="material-icons">settings</i></a>

        <div class="container" >
          <!-- Modal Opens to select bg image -->
		  <div id="modal1" class="modal">
		    <div class="modal-content">
		      <h4 class="center-align">Background Image</h4>
		      <div class="divider"></div>
		      <form action="<%=request.getContextPath()%>/changeLoginBackground" id="imageForm" name="imageForm" method="post" enctype="multipart/form-data">
		            <div class="file-field input-field">
		              <div class="btn bgg">
		                <span>Image</span>
		                <input type="file" name="fileName" id="fileName" accept="image/jpeg" required/>
		              </div>
		              <div class="file-path-wrapper">
		                <input class="file-path validate bgb" type="text"/>
		              </div>
		            </div>
		            <input type="submit" class="btn bgb" value="Submit" style="width:100%"/>
		      </form>
		     </div>
		    </div>
        
    		<div class="row mar-top">
    		<div class="blast">
		    			
		    			<div class="fireworks"></div>
		    		</div>
     			<div id="support" class="col"></div>
     			
     			<!-- <h3 style="color: #fff;">System IP Address : <span id="systemIPA">Loading...</span></h3>
                <h3 style="color: #fff;">Public IP Address : <span id="publicIPA">Loading...</span></h3> -->
                
		        <form class="col s12 m3" action="<%=request.getContextPath()%>/login" id="loginForm" name="loginForm" method="post" >
		        	<div class="row homepage">
			            <img src="/pmis/resources/images/mrvclogo.png" alt="mrvc logo" class="card-img">
			            <h4 class="tite">Sign In</h4>
			            <div class="input-field col offset-s1 s10">
			              <input type="text" name="user_id" id="user_id" class="validate form-control" autocomplete="off">
			              <label for="user_id">User Name</label>
			            </div>
			            <div class="input-field col offset-s1 s10">
			              <input type="password" name="password" id="password" class="validate" autocomplete="off">
			              <label for="password">Password</label>
			             <!--  <i class="fa fa-eye toggle-password" style="color:#fff;">.</i> -->
			              <span class="material-icons toggle-password">visibility_off</span>
			            </div>
			            <div class="msg">
			             	<p id="message" class="error">${message}</p>
			               	<c:if test="${not empty success}">
			                   	<p id="logoutMsg" class="success">${success}</p>
			               	</c:if>
			            </div>
			             
			            <input type="hidden" id="system_ipa" name="system_ipa">
			            <input type="hidden" id="public_ipa" name="public_ipa">
			            <div class="input-field col offset-s1 s10 text-center">
			            	<button type="submit" onclick="login();" class="btn bgb" style="width:100%">Submit</button>
			               <!--  <input type="submit" class="btn-outline waves-effect waves-light" value="Go">
			                <p class="for-text"><a href="javscript:void(0);" >Forgot Password ?</a></p> -->
			            </div>
			            
			            <div class="input-field col offset-s1 s10 text-center"><a href="<%=request.getContextPath() %>/forgot-password" style="color:#f7f7f7;">Forgot Password </a></div>
			        </div>
		        
		         </form>
		         <div class="confetti">
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					    <div class="confetti-piece"></div>
					</div>
	      	</div>
    	</div>
    	
    	<!-- Page Loader -->
	<div class="page-loader">
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
	
	<div class="page-loader-2">
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
            	<a href="https://mrvc.indianrailways.gov.in/" target="_blank"><b> MRVC </b> 
      	<img src="/pmis/resources/images/mrvclogo.png" alt="Logo" class="f-logo"></a> | Designed & Developed by
               <a href="https://www.synergizglobal.com" target="_blank"> <img src="/pmis/resources/images/synergiz.png" alt="synergiz logo" class="footer-logo"> </a>
                <a class="help-icon dropdown-trigger"  href='#' data-target='help-dropdown'>
        			<span class="help-txt">Help desk</span><img src="/pmis/resources/images/help_icon_white.svg">        
        		</a>
        	</p>
	          <!-- Dropdown Structure -->
			  <ul id='help-dropdown' class='dropdown-content blue lighten-5'>
			    <!-- <li><a href="/pmis/5. User Manual for Risk Module-V2.pdf" target="_blank">User Manual for Risk Module<i class="fa fa-download"></i></a></li> -->
			    <!-- <ul style="padding-left: 20px;">
			        <li style="min-height: 20px;">
			        	<a style="font-size: 14px;min-height: 20px;padding: 11px 16px;line-height: 0px;font-weight: 100;" href="/pmis/PMIS - Issue Module - User Manual.docx" >PMIS Issue Module<i class="fa fa-download"></i></a>
			        </li>
			    </ul> -->
			    <!-- <li><a href="/pmis/5. User Manual for PMIS-V 3.pdf" target="_blank">User Manual for Issue Module <i class="fa fa-download"></i></a></li>
			    <li><a href="/pmis/5.User Manual -Works Execution & Monitoring Module FOB-Ver-2.pdf"  target="_blank">User Manual for FOB <i class="fa fa-download"></i></a></li>
			    <li><a href="/pmis/Primmavera P6_ppm_usermanual  Ver-19.12.pdf" target="_blank">Primavera Manual <i class="fa fa-download"></i></a></li> -->
			   <%--  <c:forEach var="manualObj" items="${userManuals }">
			    	<li><a href="<%=CommonConstants2.PMIS_MANUALS%>${manualObj.manual_id }/${manualObj.file_name }" target="_blank">${manualObj.title } <i class="fa fa-download"></i></a></li>
			    </c:forEach> --%>
			    <li class="divider" tabindex="-1"></li>
			    <li class="support-link"> Contact us : <a href="mailto:mailto:helpdesk_pmis@mrvc.gov.in" style="display: inline;">helpdesk_pmis@mrvc.gov.in</a></li>
			    <li class="divider" tabindex="-1"></li>
			    <li><a href="#">Unable to login? </a></li>
			  </ul>
        </div>
      </footer>
          <!-- footer ends here -->
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js" ></script>  
	<script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
	<script>  
	    var RTCPeerConnection = /*window.RTCPeerConnection ||*/ window.webkitRTCPeerConnection || window.mozRTCPeerConnection;  
		if (RTCPeerConnection)(function() {  
		    var rtc = new RTCPeerConnection({  
		        iceServers: []  
		    });  
		    if (1 || window.mozRTCPeerConnection) {  
		        rtc.createDataChannel('', {  
		            reliable: false  
		        });  
		    };  
		    rtc.onicecandidate = function(evt) {  
		        if (evt.candidate) grepSDP("a=" + evt.candidate.candidate);  
		    };  
		    rtc.createOffer(function(offerDesc) {  
		        grepSDP(offerDesc.sdp);  
		        rtc.setLocalDescription(offerDesc);  
		    }, function(e) {  
		        console.warn("offer failed", e);  
		    });  
		    var addrs = Object.create(null);  
		    addrs["0.0.0.0"] = false;  
		  
		    function updateDisplay(newAddr) {  
		        if (newAddr in addrs) return;  
		        else addrs[newAddr] = true;  
		        var displayAddrs = Object.keys(addrs).filter(function(k) {  
		            return addrs[k];  
		        });
		        //document.getElementById('system_ipa').textContent = displayAddrs.join(" or perhaps ") || "n/a";  
		        var local_ipa = displayAddrs.join(" or perhaps ") || "n/a";
		        $('#system_ipa').val(local_ipa);
		    }  
		  
		    function grepSDP(sdp) {  
		        var hosts = [];  
		        sdp.split('\r\n').forEach(function(line) {  
		            if (~line.indexOf("a=candidate")) {  
		                var parts = line.split(' '),  
		                    addr = parts[4],  
		                    type = parts[7];  
		                if (type === 'host') updateDisplay(addr);  
		            } else if (~line.indexOf("c=")) {  
		                var parts = line.split(' '),  
		                    addr = parts[2];  
		                updateDisplay(addr);  
		            }  
		        });  
		    }  
		})();  
		else {  
		    //document.getElementById('system_ipa').innerHTML = "<code>ifconfig| grep inet | grep -v inet6 | cut -d\" \" -f2 | tail -n1</code>";  
		    //document.getElementById('system_ipa').nextSibling.textContent = "In Chrome and Firefox your IP should display automatically, by the power of WebRTCskull.";  
		} 
	</script> 

	<script type="text/javascript">
			$(document).ready(function() {	
				
				/* $.getJSON("https://jsonip.com?callback=?", function (data) {
					$(".page-loader").hide();
			        //$("#systemIPA").html(data.ip);
			        $("#system_ipa").val(data.ip);
			    });  */
				 setTimeout(function () {
	    			$(".page-loader").hide();
	    		}, 500);
		    	
		    	$.getJSON("https://api.ipify.org?format=json",  function(data) { 
		    		$(".page-loader-2").hide();
					//$("#publicIPA").html(data.ip); 
		    		$("#public_ipa").val(data.ip); 
			    }); 
				setTimeout(function () {
	    			$(".page-loader-2").hide();
	    		}, 500);
		    	
				
				
		    	 if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) {
			       $('#user_id').on('change focus active click', function (e) {
			            setTimeout(function () {
			                $.each(
			                    document.querySelectorAll('*:-webkit-autofill'),
			                    function () {
			                        var clone = $(this).clone(true, true);
			                        $(this).after(clone).remove();
			                    })
			            }, 100)
			        }).change();
			    }
			});
			

			
			function login() {
				window.localStorage.clear();
				var flag = $('#loginForm').valid();
				if(flag){
					$('#loginForm').submit();
				}
			}
		    
			//form validations and footer related code 
		    $(document).ready(function() {	
		    	
				/*if(window.matchMedia("(max-width: 769px)").matches)
				{ 
				  var elem = document.documentElement;
				  $('html').click(function () {
				        if (elem.isFullscreen !== true) {
				        	elem.requestFullscreen(); 
				        }
				    });
				}*/    	
		    	
		    	$('#logoutMsg').delay(3000).fadeOut('slow');
		    	
				 $("#year").html(new Date().getFullYear());
				 
			        
		        $('input').change(function(){
		    	    if ($(this).val() != ""){
		    	        $(this).valid();
		    	    }
		    	});
						
		        $('.dropdown-trigger').dropdown({
		       	 coverTrigger:false,    	 
		       	 closeOnClick: false,
		       	 aboveOrigin:true,
		        });
			 });
			
			
		    $('#loginForm').validate({
			    rules: {
			     		"user_id":{
			     			required:true
	                	},"password":{
	                		required:true
	                	}
			     	},
			    messages: {
			   			  "user_id":{
			   			  	required:'Please provide Login Id & Password'
			   			  },"password":{
	                		required:'Please provide Login Id & Password'
		                  }
			   	},errorPlacement:function(error, element){
			   		   $("#logoutMsg").html("");
				       if(element.attr("id") == "user_id" ){
						 document.getElementById("message").innerHTML="";
						 error.appendTo('#message');
				        }else if (element.attr("id") == "password" ){
						 document.getElementById("message").innerHTML="";
						 error.appendTo('#message');
			             }else{error.insertAfter(element);} 
				       
			    },invalidHandler: function (form, validator) {
                     var errors = validator.numberOfInvalids();
                     if (errors) {
                         var position = validator.errorList[0].element;
                         jQuery('html, body').animate({
                             scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                         }, 1000);
                     }
                 },submitHandler:function(form){
			    	 
			    	//const public_key="ssdkF$HUy2A#D%kd";
			    	//$('#user_id').val(CryptoJS.AES.encrypt($('#user_id').val(),public_key)); 
			    	//$('#password').val(CryptoJS.AES.encrypt($('#password').val(),public_key));
			    	 
			    	//another method
			    	
			    	//var encdata = $("form").serializeArray();
			    	
			    	//$.each(encdata, function(i, field){
			    		//if(jQuery('input[name="'+field.name+'"]').val()!="")
			    		//jQuery('input[name="'+field.name+'"]').val(CryptoJS.AES.encrypt(jQuery('input[name="'+field.name+'"]').val(),public_key));
			    	    //});
			    	
		             form.submit();
			    }
			});
		    
		    //material components initialization
		    $(document).ready(function(){
		        $('.sidenav').sidenav();
		        $('.modal').modal();
		    });
		    //selecting the login card position 
		    $('input[type=radio][name=group1]').change(function() {
		        if (this.value == 'middle') {
		            $('#support').addClass('m5')
		            $('#support').removeClass('m9')
		        }
		        if (this.value == 'right') {
		        	$('#support').addClass('m9')
		            $('#support').removeClass('m5')
		           
		        }
		        if (this.value == 'left') {
		        	$('#support').removeClass('m9')
		            $('#support').removeClass('m5')
		        }
		    });
		   
		    $(".toggle-password").on('click', function() {
		    	 // $(this).toggleClass("fa-eye fa-eye-slash");
		    	  if($(this).text()=='visibility_off'){
		    		  $(this).text('visibility');
		    	  }
		    	  else{
		    		  $(this).text('visibility_off');
		    	  }
		    	  var input = $("#password");
		    	  if (input.attr("type") === "password") {
		    	    input.attr("type", "text");
		    	  } else {
		    	    input.attr("type", "password");
		    	  }

		    	});
		    
		    
		 // firework scripts
		    setTimeout(function(){
			    document.querySelector('.fireworks').style.display = 'none';
			}, 3200);
		    
</script>

 

</body>

</html>
