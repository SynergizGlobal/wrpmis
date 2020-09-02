<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Activity</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
  <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/mrvc/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/mrvc/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/mrvc/resources/css/activity.css">
  <link rel="stylesheet" href="/mrvc/resources/css/select2.min.css">
  <style type="text/css">
  	.error{color:red;}
  </style>
</head>

<body>
  <!-- header includes -->
  <jsp:include page="./layout/newHeader.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Rest Password </h6>
                        </div>
                    </span>

                    <!-- form start-->
                    <div class="container container-no-margin">
                    	<c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty message }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
                    	<br>
                    	
                        <form action="<%=request.getContextPath()%>/change-password" id="passwordResetForm" name="passwordResetForm" method="post">
                            <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input type="password" id="oldPassword" name="oldPassword" class="validate" autocomplete="off">
                                    <label for="oldPassword">Old Password</label>
                                    <span id="oldPasswordError" ></span>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input type="password" id="newPassword" name="newPassword"  class="validate" autocomplete="off" step="any" pattern="^\d*(\.\d{0,2})?$">
                                    <label for="newPassword">New Password</label>
                                    <span id="newPasswordError" ></span>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div>
                           
                           <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input type="password" id="confirmPassword" name="confirmPassword" class="validate" autocomplete="off">
                                    <label for="confirmPassword">Confirm Password</label>
                                    <span id="confirmPasswordError" ></span>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div>
                            
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="submit" class="btn waves-effect waves-light bg-m" style="width: 100%;">Change Password</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/reset-password" class="btn waves-effect waves-light bg-s" style="width: 100%;">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->

                    
                </div>
            </div>
        </div>
    </div>


  <!-- footer includes -->
  <jsp:include page="./layout/newFooter.jsp"></jsp:include>
  
  <script src="/mrvc/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/mrvc/resources/js/dataTables.material.min.js"></script>
  <script src="/mrvc/resources/js/select2.min.js"></script>
  <script src="/mrvc/resources/js/jquery-validation-1.19.1.min.js"></script>
  
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
					       
				    },submitHandler:function(form){
				    	form.submit();
				    }
				});  
				 
				 
				 
				jQuery.validator.addMethod("mypassword", function(value, element) {
					  // allow any non-whitespace characters as the host part
					  return this.optional( element ) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/.test( value );
					}, 'Password should contain minimum 1 uppercase letter, 1 lowercase letter, 1 number & 1 special character');
						
			 });
		    
		</script>
</body>

</html>