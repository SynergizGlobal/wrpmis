<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
		
		
    </style>
</head>

<body>
 	       <!-- header starts -->
 	  	  <nav>
	        <div class="nav-wrapper bg">
	          <h3 style="margin:0" class="text">Welcome to MRVC PMIS </h3>  
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
			            	<button type="button" onclick="login();" class="btn bgb" style="width:100%">Submit</button>
			               <!--  <input type="submit" class="btn-outline waves-effect waves-light" value="Go">
			                <p class="for-text"><a href="javscript:void(0);" >Forgot Password ?</a></p> -->
			            </div>
			        </div>
		        
		         </form>
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
            	<a href="https://mrvc.indianrailways.gov.in/" target="_blank">mrvc.indianrailways.gov.in</a> | Designed & Developed by
               <a href="https://www.synergizglobal.com" target="_blank"> <img src="/pmis/resources/images/synergiz.png" alt="synergiz logo" class="footer-logo"> </a>
                <a class="help-icon dropdown-trigger"  href='#' data-target='help-dropdown'>
        			<img src="/pmis/resources/images/help_icon_white.svg">        
        		</a>
        	</p>
	          <!-- Dropdown Structure -->
			  <ul id='help-dropdown' class='dropdown-content blue lighten-5'>
			    <li><a href="/pmis/5. User Manual for Risk Module-V2.pdf" target="_blank">User Manual for Risk Module<i class="fa fa-download"></i></a></li>
			    <!-- <ul style="padding-left: 20px;">
			        <li style="min-height: 20px;">
			        	<a style="font-size: 14px;min-height: 20px;padding: 11px 16px;line-height: 0px;font-weight: 100;" href="/pmis/PMIS - Issue Module - User Manual.docx" >PMIS Issue Module<i class="fa fa-download"></i></a>
			        </li>
			    </ul> -->
			    <li><a href="/pmis/5. User Manual for PMIS-V 3.pdf" target="_blank">User Manual for Issue Module <i class="fa fa-download"></i></a></li>
			    <li><a href="5.User Manual -Works Execution & Monitoring Module FOB-Ver-2.pdf"  target="_blank">User Manual for FOB <i class="fa fa-download"></i></a></li>
			    <li><a href="/pmis/Primmavera P6_ppm_usermanual  Ver-19.12.pdf" target="_blank">Primavera Manual <i class="fa fa-download"></i></a></li>
			    <li class="divider" tabindex="-1"></li>
			    <li class="support-link"> Contact us : <a href="mailto:support_pmis@mrvc.gov.in" style="display: inline;">support_pmis@mrvc.gov.in</a></li>
			  </ul>
        </div>
      </footer>
          <!-- footer ends here -->
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js" ></script>  
	<script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
	<script type="text/javascript">
			$(document).ready(function() {	
				
				
		
				
				$.getJSON("https://jsonip.com?callback=?", function (data) {
					$(".page-loader").hide();
			        //$("#systemIPA").html(data.ip);
			        $("#system_ipa").val(data.ip);
			    });
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
		    	
				if(window.matchMedia("(max-width: 769px)").matches)
				{ 
				  var elem = document.documentElement;
				  $('html').click(function () {
				        if (elem.isFullscreen !== true) {
				        	elem.requestFullscreen(); 
				        }
				    });
				}	    	
		    	
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
		    
</script>
</body>

</html>
