<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reset Password - User Details - PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/pmis/resources/css/activity.css">
  <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
  <style type="text/css">
  	.error{color:red;}
  	.toggle-password{
			position: absolute;
		    color: #777;
		    right: 10px;
		    top: 14px;
		    font-size: 1.35rem;
		    cursor:pointer;
		}
		@media only screen and (max-device-width: 769px){
		    .input-field input[type='password']{
			    box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
			    height: 40px;
			    width: -webkit-fill-available;
			    background-color: transparent;
			    padding-left: 10px;
			}
			.input-field input[type='password']+label{
				color:#333;
				padding-left:10px;
			}
			.toggle-password{
				top:10px;
				right:15px;
			}
		}

  </style>
</head>

<body>
  <!-- header includes -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2">
                            <h6> Reset Password </h6>
                        </div>
                    </span>

                    <!-- form start-->
                    <div class="container container-no-margin">
                    	<c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message error">
							   ${error}
							</div>
						</c:if>
                    	<br>
                    	
                        <form action="<%=request.getContextPath()%>/change-password" id="passwordResetForm" name="passwordResetForm" method="post">
                            <div class="row">
                                <div class="col s12 m6 l4 offset-l4 input-field offset-m3">
                                    <input type="password" id="oldPassword" name="oldPassword" class="validate" autocomplete="off">
                                    <label for="oldPassword">Old Password</label>
                                     <span class="material-icons toggle-password old-pass">visibility_off</span>
                                    <span id="oldPasswordError" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m6 l4 offset-l4 input-field offset-m3">
                                    <input type="password" id="newPassword" name="newPassword"  class="validate" autocomplete="off" step="any" pattern="^\d*(\.\d{0,2})?$">
                                    <label for="newPassword">New Password</label>
                                    <span class="material-icons toggle-password new-pass">visibility_off</span>
                                    <span id="newPasswordError" ></span>
                                </div>
                            </div>
                           
                           <div class="row">
                                <div class="col s12 m6 l4 offset-l4 input-field offset-m3 ">
                                    <input type="password" id="confirmPassword" name="confirmPassword" class="validate" autocomplete="off">
                                    <label for="confirmPassword">Confirm Password</label>
                                    <span class="material-icons toggle-password conf-pass">visibility_off</span>
                                    <span id="confirmPasswordError" ></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m3 l2 offset-m3 offset-l4 ">
                                    <div class="center-align m-1">
                                        <button type="submit" class="btn waves-effect waves-light bg-m" style="width: 100%;">Change Password</button>
                                    </div>
                                </div>
                                <div class="col s12 m3 l2 ">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/reset-password" class="btn waves-effect waves-light bg-s" style="width: 100%;">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->

                    
                </div>
            </div>
        </div>
    </div>


  <!-- footer includes -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
  
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
  
  <script type="text/javascript">
		   //form validations
		    $(document).ready(function() {		    	
				 //$("#year").html(new Date().getFullYear());
				 $('#passwordResetForm').validate({
				    rules: {
				     		"oldPassword":{
				     			required:true
		                	},"newPassword":{
		                		required:true,
		                		minlength: 8,
		                		maxlength: 20,
		                        mypassword: true
		                	},"confirmPassword":{
		                		required:true,
		                		minlength: 8,
		                		maxlength: 20,
		                		equalTo: "#newPassword"
		                	}
				     	},
				    messages: {
				   			  "oldPassword":{
				   			  	required:'Old Password Required'
				   			  },"newPassword":{
		                		required:'New Password Required',
		                		minlength: "Your password must be at least 8 characters long",
		                		maxlength: "Your password must be at most 20 characters long"
			                  },"confirmPassword":{
		                		required:'Confirm Password Required',
		                		minlength: "Your password must be at least 8 characters long",
		                		maxlength: "Your password must be at most 20 characters long",
		                		equalTo: "Confirm password must be same as New Password"
			                  }
				   	},errorPlacement:function(error, element){
					       if(element.attr("id") == "oldPassword" ){
							 document.getElementById("oldPasswordError").innerHTML="";
							 error.appendTo('#oldPasswordError');
					        }else if (element.attr("id") == "newPassword" ){
							 document.getElementById("newPasswordError").innerHTML="";
							 error.appendTo('#newPasswordError');
				            }else if (element.attr("id") == "confirmPassword" ){
								 document.getElementById("confirmPasswordError").innerHTML="";
								 error.appendTo('#confirmPasswordError');
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
				    	form.submit();
				    }
				});  
				 
				 
				 
				jQuery.validator.addMethod("mypassword", function(value, element) {
					  // allow any non-whitespace characters as the host part
					  return this.optional( element ) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/.test( value );
				}, 'Password should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character');
				
			});
		   
		    $('select').change(function(){
	    	    if ($(this).val() != ""){
	    	        $(this).valid();
	    	    }
	    	});
	        
	        $('input').change(function(){
	    	    if ($(this).val() != ""){
	    	        $(this).valid();
	    	    }
	    	});
	        $(".old-pass").on('click', function() {
		    	  if($(this).text()=='visibility_off'){
		    		  $(this).text('visibility');
		    	  } else{
		    		  $(this).text('visibility_off');
		    	  }
		    	  var input = $("#oldPassword");
		    	  if (input.attr("type") === "password") {
		    	     input.attr("type", "text");
		    	  } else {
		    		 input.attr("type", "password");		    	 
		    	  }	
		    });	
	        $(".new-pass").on('click', function() {
		    	  if($(this).text()=='visibility_off'){
		    		  $(this).text('visibility');
		    	  } else{
		    		  $(this).text('visibility_off');
		    	  }
		    	  var input = $("#newPassword");
		    	  if (input.attr("type") === "password") {
		    	     input.attr("type", "text");
		    	  } else {
		    		 input.attr("type", "password");		    	 
		    	  }	
		    });		        
	        $(".conf-pass").on('click', function() {
		    	  if($(this).text()=='visibility_off'){
		    		  $(this).text('visibility');
		    	  } else{
		    		  $(this).text('visibility_off');
		    	  }
		    	  var input = $("#confirmPassword");
		    	  if (input.attr("type") === "password") {
		    	     input.attr("type", "text");
		    	  } else {
		    		 input.attr("type", "password");		    	 
		    	  }	
		    });	
		</script>
</body>

</html>