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
    <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/md5.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
    <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/mrvc/resources/css/style.css">  
    
    <style type="text/css">
		/* Change the white to any color ;) */
		input:-webkit-autofill,
		input:-webkit-autofill:hover, 
		input:-webkit-autofill:focus, 
		input:-webkit-autofill:active  {
		    -webkit-box-shadow: 0 0 0 30px rgba(2, 36, 202, 0.70) inset !important;
		}		
    </style>
</head>

<body style="background: black url(<%=CommonConstants.LOGIN_BACKGROUND_IMAGE_URL%>) no-repeat center center;background-size: cover;">
 	       <!-- header starts -->
 	  	  <nav>
        <div class="nav-wrapper bg">
          <h3 style="margin:0" class="text">Welcome to MRVC PMIS </h3>  
        </div>
      </nav>
      <!-- header ends -->
      <!-- side navigation starts here -->
	      <ul id="slide-out" class="sidenav">
		    <li><!-- div class="user-view">
		      <div class="background">
		        <img src="images/office.jpg">
		      </div>
		      <a href="#user"><img class="circle" src="images/yuna.jpg"></a>
		      <a href="#name"><span class="white-text name">John Doe</span></a>
		      <a href="#email"><span class="white-text email">jdandturk@gmail.com</span></a>
		    </div--></li>
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

        <div class="container">
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
		        <form class="col s12 m3 " action="<%=request.getContextPath()%>/login" id="loginForm" name="loginForm" method="post" >
		        	<div class="row homepage">
			            <img src="/mrvc/resources/images/mrvclogo.png" alt="mrvc logo" class="card-img">
			            <h4 class="tite">Sign In</h4>
			            <div class="msg">
			             	<p id="message" class="error">${message}</p>
			             	<p id="keyMessage" class="error">${pmis_key}</p>
			               	<c:if test="${not empty success}">
			                   	<p id="logoutMsg" class="success">${success}</p>
			               	</c:if>
			            </div>
			            <div class="input-field col offset-s1 s10">
			              <input type="text" name="userId" id="userId" class="validate form-control" autocomplete="off">
			              <label for="userId">User Name</label>
			            </div>
			            <div class="input-field col offset-s1 s10">
			              <input type="password" name="password" id="password" class="validate" autocomplete="off">
			              <label for="password">Password</label>
			            </div>
			            
			             <input type="hidden" name="taskId" id="taskId" value="${taskId }" />
			             <input type="hidden" name="workId" id="workId" value="${workId }"/>
			             <input type="hidden" name="workName" id="workName" value="${workName }"/> 
			             <input type="hidden" name="notificationId" id="notificationId" value="${notificationId }"/> 
			              
			            <div class="input-field col offset-s1 s10 text-center">
			            	<button type="submit" class="btn bgb" style="width:100%">Submit</button>
			               <!--  <input type="submit" class="btn-outline waves-effect waves-light" value="Go">
			                <p class="for-text"><a href="javscript:void(0);" >Forgot Password ?</a></p> -->
			            </div>
			        </div>
		        
		         </form>
	      	</div>
    	</div>
      <!-- footer starts here -->

      <footer class="page-footer">
        <div class="container">
            <p class="footer-text">Copyright <span id="year"></span> @ mrvc.indianrailways.gov.in | Designed & Developed by
                <img src="/mrvc/resources/images/synergiz.png" alt="synergiz logo" class="footer-logo"></p>
        </div>
    </footer>
          <!-- footer ends here -->
<script src="https://code.jquery.com/jquery-3.5.0.min.js" ></script>
<script src="/mrvc/resources/js/jquery-validation-1.19.1.min.js" ></script>  
<script src="/mrvc/resources/js/materialize-v.1.0.min.js" ></script>
<script type="text/javascript">
			$(document).ready(function() {	
		    	 if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) {
			       $('#userId').on('change focus active click', function (e) {
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
		    
			//form validations and footer related code 
		    $(document).ready(function() {		    	
		    	$('#logoutMsg').delay(3000).fadeOut('slow');
		    	
				 $("#year").html(new Date().getFullYear());
				 $('#loginForm').validate({
				    rules: {
				     		"userId":{
				     			required:true
		                	},"password":{
		                		required:true
		                	}
				     	},
				    messages: {
				   			  "userId":{
				   			  	required:'Please Provide Login Id & Password'
				   			  },"password":{
		                		required:'Please Provide Login Id & Password'
			                  }
				   	},errorPlacement:function(error, element){
				   		   $("#logoutMsg").html("");
					       if(element.attr("id") == "userId" ){
							 document.getElementById("message").innerHTML="";
							 error.appendTo('#message');
					        }else if (element.attr("id") == "password" ){
							 document.getElementById("message").innerHTML="";
							 error.appendTo('#message');
				             }else{error.insertAfter(element);} 
					       
				    },submitHandler:function(form){
				    	 
				    	//const public_key="ssdkF$HUy2A#D%kd";
				    	//$('#userId').val(CryptoJS.AES.encrypt($('#userId').val(),public_key)); 
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
		   
		    
</script>
</body>

</html>
