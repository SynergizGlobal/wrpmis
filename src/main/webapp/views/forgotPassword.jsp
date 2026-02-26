<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
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
    body{
    	background-color: #FFEFE2;
    }
		/* Change the white to any color ;) */
		/* input:-webkit-autofill,
		input:-webkit-autofill:hover, 
		input:-webkit-autofill:focus, 
		input:-webkit-autofill:active  {
		    -webkit-box-shadow: 0 0 0 30px rgba(2, 36, 202, 0.70) inset !important;
		}	 */	 
		.setting-icon{
			color:inherit;
		}
		.row.homepage{
			-webkit-box-shadow: 0px 0px 25px 10px rgba(0,0,155,.4);
			-moz-box-shadow: 0px 0px 25px 10px rgba(0,0,155,.4);
			box-shadow: 0px 0px 25px 10px rgba(0,0,155,.4);
		}
		.login-heading h1{
			margin: 0.67em 0;
		}
		.toggle-password{
			position: absolute;
		    color: #f0f0f0;
		    right: 10px;
		    top: 14px;
		    font-size: 1.35rem;
		    cursor:pointer;
		}
		
		.toggle-password1{
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
		.input-field.col label{
			color: #fff;
		}
		.input-field.col input{
			color: #fff;
			border-bottom: 1px solid #fff;
			background-color: transparent !important;
		}
		.input-field.col input::focus{
			background-color: transparent;
		}
		.input-field.col label.active{
			color:#fff !important;
		}
		label.error{
			font-size:.9rem;
		}
		#divOTP span{
			color: lightgreen;
    		font-size: .9rem;
		}
		.error {
		    color: #ff8484;
		    text-shadow: 0 0 3px #444;
		}
		.sidenav, .sidenav-trigger{
			display:none;
		}
		.tite{
			font-size: 26px;
			font-weight: 600;
			text-align: center;
			color: #fff;
		}
		#support{
			display: none;
		}
		.row.mar-top{
			display: flex;
			justify-content: center; 
		}
		.row.mar-top form{
			display: flex;
			align-items: center;
			justify-content: center;
		}
		.row.homepage{
			display: flex;
		    flex-direction: column;
		    margin: 3em;
		    align-items: center;
		    position: relative;
		    background-color: #D58D54;
		    border-radius: 30px;
		    width: 63%;
		    padding: 1em;
		}
		.material-icons a{
			color: #fff;
		}
		.footer{
    background-color: #542705;
    /*position: absolute;
    width: 100%;
    bottom: 0;*/
    color: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    padding: 10px 0;
    width: 100%;
    position: relative;
    bottom: 0;
    left: 0;
  }
  .designed-name img{
    width: 140px;
    height: auto;
  }
  .designed-name, .ft-copyright{
    display: flex;
    align-items: center;
  }
  .ft-copyright{
    gap: 10px;
  }
  .server-type .select-wrapper input.select-dropdown{
    background-color: #fff;
    padding: 5px 15px;
    height: auto;
    line-height: 100%;
    width: 100%;
    margin-bottom: 0;
  }
  @media(max-width: 630px){
    
      .footer{  
        margin-bottom: -40px;
        width: 100%;
      }
      .footer, .designed-name{
        flex-direction: column;
      }
  }
    </style>
</head>

<body>
<div class="page-container">
 	       <!-- header starts -->
 	  	  <nav>
	        <div class="nav-wrapper bg login-heading">
	          <h1 class="text">Welcome to WR PMIS </h1>  
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
                
		        <form  action="<%=request.getContextPath()%>/forgot-password" id="forgotPasswordForm" name="forgotPasswordForm" method="post" >
		        	<div class="row homepage" style="margin-bottom:3rem;">
		        	<span class="material-icons" style="position: absolute; left: 20px; top: 20px;"><a href="#" onclick="window.location.href='login'"> keyboard_backspace</a></span> 
			            <img src="/wrpmis/resources/images/wr-logo.png" alt="mrvc logo" width="150" height="150" class="card-img">

				            <h4 class="tite">Forgot Password</h4>
				            <div id="firstDiv">
					            <div class="input-field col offset-s1 s10">
					              <input type="text" name="user_id" id="user_id">
					              <label for="user_id">User Name</label>
					            </div>
					           	<div class="col offset-s1 s10" style="text-align:center;"><span style="color:#ffffff;">OR</span></div>
					            <div class="input-field col offset-s1 s10">
					              <input type="text" name="email_id" id="email_id" >
					              <label for="email_id">Confirm Email</label>
					            </div>
					            <div class="msg">
					             	<p id="message" class="error">${message}</p>
					               	<c:if test="${not empty success}">
					                   	<p id="logoutMsg" class="success">${success}</p>
					               	</c:if>
					            </div>
				            </div>
				             <input type="hidden" id="hdnPass">
				             
				             <div id="divOTP" style="display:none;">
	                            <div class="input-field col offset-s1 s10"  style="text-align:center;">                            
		                             <span>We have sent OTP to your Email Id. Please enter the OTP here to reset your password.</span>                                                     
		 				            <div class="input-field col s12">
						              <input type="text" id="otpvalue" name="otpvalue"  maxlength="6">
						              <label for="otpvalue">OTP</label>
						            </div>
						            
		 				            <div class=" col s12">
										<button type="button" class="btn waves-effect waves-light bg-m" style="width: 100%;border:none;" id="btnCheckotp" onClick="CheckOTP();">Check OTP</button>
						            </div>				            
	                            </div> 
                            </div>
                            <div id="secondDiv" style="display:none;">
					            <div class="input-field col offset-s1 s10">
					              <input type="password" name="newPassword" id="newPassword">
					              <label for="newPassword">New Password</label>
					              <span class="material-icons toggle-password">visibility_off</span>
					            </div>
					            <div class="input-field col offset-s1 s10">
					              <input type="password" name="confirmPassword" id="confirmPassword">
					              <label for="confirmPassword">Confirm New Password</label>
					              <span class="material-icons toggle-password1">visibility_off</span>
					            </div>	                            
                            </div>
                            
 				             <div id="accessMsg" style="text-align:center;color:red;"></div>
                            
                            <div class="error col offset-s1 s10" style="text-align:center;" id="otpmessage">                            
                            </div> 				            
				            
				            
			            <div class="input-field text-center">
			            	<button type="button" onclick="login();" class="btn btn-transparent-white-border" style="width:100%" id="btnSubmit">Submit</button>
			               <!--  <input type="submit" class="btn-outline waves-effect waves-light" value="Go">
			                <p class="for-text"><a href="javscript:void(0);" >Forgot Password ?</a></p> -->
			            </div>				            
			        </div>
		        
		         </form>
	      	</div>
    	</div>
    	
    	<!-- Page Loader -->
	<div class="page-loader" style="display:none; ">
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
	
	<div class="page-loader-2" style="display:none; ">
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

      <div class="footer">
  <div class="ft-copyright">
    © 2025 @ WR <img src="/wrpmis/resources/images/wr-logo.png" alt="logo" width="25" height="25">
  </div> |
  <div class="designed-name">
    Designed & Developed by <img src="/wrpmis/resources/images/syg-logo.png" alt="SYG Logo">
  </div>
  <div class="server-type">
    <select name="server_type" id="server_type">
      <option value="production">Production</option>
      <option value="testing">Testing</option>
    </select>
  </div>
</div>
          <!-- footer ends here -->
          </div>
	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js" ></script>
	<script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js" ></script>  
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js" ></script>
	<script type="text/javascript">
			var glbProcess=false;
	
			$(document).ready(function() {	
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
			
			function GetUserId()
			{
		      	 $.ajax({
		            url: "<%=request.getContextPath()%>/ajax/getUserId",
		            data: {email_id:$("#email_id").val()},
 	    			type:"POST",
 	    			cache: false,async:false,
		            success: function (data) 
		            {
						$("#user_id").val(data[0].user_id);
		            }
		        });  				
			}
			

			
			function login() {
				//window.localStorage.clear();
				//var flag = $('#forgotPasswordForm').valid();
				
							if($("#user_id").val()=="" && $("#email_id").val()=="")
							{
								document.getElementById("message").innerHTML="Please enter User ID or Email ID ";
								return false;
							}
							


							 
							 if($("#user_id").val()!="" && $("#email_id").val()=="")
								 {
									 if(checkUserId()==true)
										 {
			                    	 	$("#user_id").prop("readonly", true);
			                    	 	$("#email_id").prop("readonly", true);	
			                    	 
				                    	 //$("#newPassword").prop("readonly", true);
				                    	 //$("#confirmPassword").prop("readonly", true);	
					                	 if(glbProcess==false)
				                		 {
					                		 $("#accessMsg").html("");
					                    	 $("#divOTP").show();
					                    	 $("#firstDiv").hide();
						                	 $("#btnSubmit").hide();
						                	 
						                	 var OTP=generateOTP();
						                	 $("#hdnPass").val(OTP);
						                	 
						                	 var myParams = { OTP: OTP,Email: $("#email_id").val(),UserId:$("#user_id").val()};
						                	 
						                	 $.ajax({
						                         url: "<%=request.getContextPath()%>/ajax/sendOTPtomailforResetPassword",
						                         data: myParams, cache: false,
						                         success: function (data) 
						                         {
						                             
						                         }
						                     });
				                		 }
					                	 else
				                		 {
				                		     if($("#newPassword").val()=="")
				                			 {
				                		    	 $("#accessMsg").html("New Password Required");
				                		    	 return false;
				                			 }
				                		     if($("#confirmPassword").val()=="")
				                			 {
				                		    	 $("#accessMsg").html("Confirm Password Required");
				                		    	 return false;
				                			 }
					                		 if($("#newPassword").val()!=$("#confirmPassword").val())
				                			 {
					                			 $("#accessMsg").html("Confirm password must be same as New Password");
					                			 return false;
				                			 }
					                		 if($("#newPassword").val().length<8 || $("#newPassword").val().length>20)
				                			 {
					                			 $("#accessMsg").html("Your password must be at least 8 characters long and at most 20 characters long");
					                			 return false;
				                			 }
					                		 if($("#confirmPassword").val().length<8 || $("#confirmPassword").val().length>20)
				                			 {
					                			 $("#accessMsg").html("Your password must be at least 8 characters long and at most 20 characters long");
					                			 return false;
				                			 }
					                	     var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
					                	     if (!regex.test($("#newPassword").val()))
				                			 {
					                			 $("#accessMsg").html("newPassword should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character");
					                			 return false;			                			 
				                			 }
					                	     if (!regex.test($("#confirmPassword").val()))
				                			 {
					                			 $("#accessMsg").html("confirmPassword should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character");
					                			 return false;			                			 
				                			 }				                		 
					                	     $('#forgotPasswordForm').submit();
				                		 }				                	 
				                    	 
			                    	
									 }
									 else
									 {
										 $("#accessMsg").html("User ID wrong ");
									 }								 
								 }
							 	else  if($("#user_id").val()=="" && $("#email_id").val()!="")
							 		{
							 		
									 if(checkLoggedInUserEmail()==true)
									 {
										 
										 
									//GetUserId();	 
										 
		                    	 	$("#user_id").prop("readonly", true);
		                    	 	$("#email_id").prop("readonly", true);	
		                    	 
			                    	 //$("#newPassword").prop("readonly", true);
			                    	 //$("#confirmPassword").prop("readonly", true);	
				                	 if(glbProcess==false)
			                		 {
				                		 $("#accessMsg").html("");
				                    	 $("#divOTP").show();
				                    	 $("#firstDiv").hide();
					                	 $("#btnSubmit").hide();
					                	 
					                	 var OTP=generateOTP();
					                	 $("#hdnPass").val(OTP);
					                	 
					                	 var myParams = { OTP: OTP,Email: $("#email_id").val(),UserId:$("#user_id").val()};
					                	 
					                	 $.ajax({
					                         url: "<%=request.getContextPath()%>/ajax/sendOTPtomailforResetPassword",
					                         data: myParams, cache: false,
					                         success: function (data) 
					                         {
					                             
					                         }
					                     });
			                		 }
				                	 else
			                		 {
			                		     if($("#newPassword").val()=="")
			                			 {
			                		    	 $("#accessMsg").html("New Password Required");
			                		    	 return false;
			                			 }
			                		     if($("#confirmPassword").val()=="")
			                			 {
			                		    	 $("#accessMsg").html("Confirm Password Required");
			                		    	 return false;
			                			 }
				                		 if($("#newPassword").val()!=$("#confirmPassword").val())
			                			 {
				                			 $("#accessMsg").html("Confirm password must be same as New Password");
				                			 return false;
			                			 }
				                		 if($("#newPassword").val().length<8 || $("#newPassword").val().length>20)
			                			 {
				                			 $("#accessMsg").html("Your password must be at least 8 characters long and at most 20 characters long");
				                			 return false;
			                			 }
				                		 if($("#confirmPassword").val().length<8 || $("#confirmPassword").val().length>20)
			                			 {
				                			 $("#accessMsg").html("Your password must be at least 8 characters long and at most 20 characters long");
				                			 return false;
			                			 }
				                	     var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
				                	     if (!regex.test($("#newPassword").val()))
			                			 {
				                			 $("#accessMsg").html("newPassword should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character");
				                			 return false;			                			 
			                			 }
				                	     if (!regex.test($("#confirmPassword").val()))
			                			 {
				                			 $("#accessMsg").html("confirmPassword should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character");
				                			 return false;			                			 
			                			 }				                		 
				                	     $('#forgotPasswordForm').submit();
			                		 }				                	 
			                    	 
		                    	
								 }
								 else
								 {
									 $("#accessMsg").html("Email ID wrong ");
								 }								 		
							 		
							 		}

	    	 		
			    		
/* 			    	 	else
		    	 		{
			    	 		$("#accessMsg").html("User ID or Email wrong ");
		    	 		} */
				
				
				
				
				
				
				
				
				//if(flag){
					//$('#forgotPasswordForm').submit();
				//}
			}
		    
			//form validations and footer related code 
		    $(document).ready(function() {
		    	
		    	$('#logoutMsg').delay(3000).fadeOut('slow');
		    	
				 $("#year").html(new Date().getFullYear());
				 
			        
		        /* $('input').change(function(){
		    	    if ($(this).val() != ""){
		    	        $(this).valid();
		    	    }
		    	}); */
						
		        $('.dropdown-trigger').dropdown({
		       	 coverTrigger:false,    	 
		       	 closeOnClick: false,
		       	 aboveOrigin:true,
		        });
		        
			   			
				
			 });
			
			
<%-- 		    $('#forgotPasswordForm').validate({
			    rules: {
			     		"user_id":{
			     			required:true
	                	},"email_id":{
	                		required:true
	                	}
/* 	                	,"newPassword":{
	                		required:true,
	                		minlength: 8,
	                		maxlength: 20,
	                        mypassword: true
	                	},"confirmPassword":{
	                		required:true,
	                		minlength: 8,
	                		maxlength: 20,
	                		equalTo: "#newPassword"
	                	}    */             	
			     	},
			    messages: {
			   			  "user_id":{
			   			  	required:'User ID Required'
			   			  },"email_id":{
	                		required:'Email id Required'
		                  }
/* 			   			  ,"newPassword":{
	                		required:'New Password Required',
	                		minlength: "Your password must be at least 8 characters long",
	                		maxlength: "Your password must be at most 20 characters long"
		                  },"confirmPassword":{
	                		required:'Confirm Password Required',
	                		minlength: "Your password must be at least 8 characters long",
	                		maxlength: "Your password must be at most 20 characters long",
	                		equalTo: "Confirm password must be same as New Password"
		                  }	  */                 
			   	},errorPlacement:function(error, element){
			   		   $("#logoutMsg").html("");
				       if(element.attr("id") == "user_id" ){
							 document.getElementById("message").innerHTML="";
							 error.appendTo('#message');
				        }else if (element.attr("id") == "email_id" ){
							 document.getElementById("message").innerHTML="";
							 error.appendTo('#message');
			             }
				        else if (element.attr("id") == "newPassword" ){
							 document.getElementById("message").innerHTML="";
							 error.appendTo('#message');
			             }else if (element.attr("id") == "confirmPassword" ){
							 document.getElementById("message").innerHTML="";
							 error.appendTo('#message');
				         }
			             else{error.insertAfter(element);} 
				       
			    },invalidHandler: function (form, validator) {
                     var errors = validator.numberOfInvalids();
                     if (errors) {
                         var position = validator.errorList[0].element;
                         jQuery('html, body').animate({
                             scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                         }, 1000);
                     }
                 },submitHandler:function(form){
                	 
			    	 	if(checkUserId()==true)
			    		 {
			    	 		if(checkLoggedInUserEmail()==true)
			    	 			{
		                    	 	$("#user_id").prop("readonly", true);
		                    	 	$("#email_id").prop("readonly", true);	
		                    	 
			                    	 //$("#newPassword").prop("readonly", true);
			                    	 //$("#confirmPassword").prop("readonly", true);	
				                	 if(glbProcess==false)
			                		 {
				                		 $("#accessMsg").html("");
				                    	 $("#divOTP").show();
				                    	 $("#firstDiv").hide();
					                	 $("#btnSubmit").hide();
					                	 
					                	 var OTP=generateOTP();
					                	 $("#hdnPass").val(OTP);
					                	 
					                	 var myParams = { OTP: OTP,Email: $("#email_id").val()};
					                	 
					                	 $.ajax({
					                         url: "<%=request.getContextPath()%>/ajax/sendOTPtomailforResetPassword",
					                         data: myParams, cache: false,
					                         success: function (data) 
					                         {
					                             
					                         }
					                     });
			                		 }
				                	 else
			                		 {
			                		     if($("#newPassword").val()=="")
			                			 {
			                		    	 $("#accessMsg").html("New Password Required");
			                		    	 return false;
			                			 }
			                		     if($("#confirmPassword").val()=="")
			                			 {
			                		    	 $("#accessMsg").html("Confirm Password Required");
			                		    	 return false;
			                			 }
				                		 if($("#newPassword").val()!=$("#confirmPassword").val())
			                			 {
				                			 $("#accessMsg").html("Confirm password must be same as New Password");
				                			 return false;
			                			 }
				                		 if($("#newPassword").val().length<8 || $("#newPassword").val().length>20)
			                			 {
				                			 $("#accessMsg").html("Your password must be at least 8 characters long and at most 20 characters long");
				                			 return false;
			                			 }
				                		 if($("#confirmPassword").val().length<8 || $("#confirmPassword").val().length>20)
			                			 {
				                			 $("#accessMsg").html("Your password must be at least 8 characters long and at most 20 characters long");
				                			 return false;
			                			 }
				                	     var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
				                	     if (!regex.test($("#newPassword").val()))
			                			 {
				                			 $("#accessMsg").html("newPassword should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character");
				                			 return false;			                			 
			                			 }
				                	     if (!regex.test($("#confirmPassword").val()))
			                			 {
				                			 $("#accessMsg").html("confirmPassword should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character");
				                			 return false;			                			 
			                			 }				                		 
				                		form.submit();
			                		 }				                	 
			                    	 
		                    	 
			    	 			}
					    	 	else
				    	 		{
					    	 		$("#accessMsg").html("Email wrong");
				    	 		}			    	 		
			    		 }
			    	 	else
		    	 		{
			    	 		$("#accessMsg").html("User ID wrong");
		    	 		}
			    	//const public_key="ssdkF$HUy2A#D%kd";
			    	//$('#user_id').val(CryptoJS.AES.encrypt($('#user_id').val(),public_key)); 
			    	//$('#password').val(CryptoJS.AES.encrypt($('#password').val(),public_key));
			    	 
			    	//another method
			    	
			    	//var encdata = $("form").serializeArray();
			    	
			    	//$.each(encdata, function(i, field){
			    		//if(jQuery('input[name="'+field.name+'"]').val()!="")
			    		//jQuery('input[name="'+field.name+'"]').val(CryptoJS.AES.encrypt(jQuery('input[name="'+field.name+'"]').val(),public_key));
			    	    //});
			    	
			    }
			}); --%>
		    
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
		    	  var input = $("#newPassword");
		    	  if (input.attr("type") === "password") {
		    	    input.attr("type", "text");
		    	  } else {
		    	    input.attr("type", "password");
		    	  }
		    	});
		    
		    $(".toggle-password1").on('click', function() {
		    	 // $(this).toggleClass("fa-eye fa-eye-slash");
		    	  if($(this).text()=='visibility_off'){
		    		  $(this).text('visibility');
		    	  }
		    	  else{
		    		  $(this).text('visibility_off');
		    	  }
		    	  var input = $("#confirmPassword");
		    	  if (input.attr("type") === "password") {
		    	    input.attr("type", "text");
		    	  } else {
		    	    input.attr("type", "password");
		    	  }
		    	});		    
		    
		    
	        function CheckOTP()
	        {
        		if($("#hdnPass").val()!=$("#otpvalue").val())
        		{
        		
        			$("#otpmessage").html("Wrong OTP");
            	
	        	}
	            else
	        	{
	            	$("#firstDiv").hide();
            		$("#divOTP").hide();
            		$("#secondDiv").show();
	            	$("#btnSubmit").show();
	            	$("#otpmessage").hide();
	            	$("#otpmessage").html();
	            	glbProcess=true;
	        	}       		
	        }		    
		    
		    
	        function checkLoggedInUserEmail()
	        {
	        
	        	var bool = false;
	        	if($("#email_id").val()!="")
	        		{
	           	 $.ajax({
	                 url: "<%=request.getContextPath()%>/ajax/checkUserEmail",
	                 data: {email_id:$("#email_id").val()},type: 'POST',
	                 async: false,
	                 dataType: 'json',
	                 success: function (data) 
	                 {
	                	 if (data == true) {
	                         bool = true;
	                     }
	                 }
	             });
	        		}
	           	return trueOrFalse(bool);
	        }
	        
	        function checkUserId()
	        {
	        	var bool = false;
	        	if($("#user_id").val()!="")
	        		{
			           	 $.ajax({
			                 url: "<%=request.getContextPath()%>/ajax/checkUserId",
			                 data: {user_id:$("#user_id").val()},type: 'POST',
			                 async: false,
			                 dataType: 'json',
			                 success: function (data) 
			                 {
			                	 if (data == true) {
			                         bool = true;
			                     }
			                 }
			             });
	        		}
	           	return trueOrFalse(bool);
	        }	
	        
	        function trueOrFalse(bool){
	            return bool;
	    	}	        
	        
	        
	        function generateOTP() {
	            var digits = '0123456789';
	            let OTP = '';
	            for (let i = 0; i < 6; i++ ) {
	                OTP += digits[Math.floor(Math.random() * 10)];
	            }
	            return OTP;
	        }	  	        
		    
</script>

 

</body>

</html>
