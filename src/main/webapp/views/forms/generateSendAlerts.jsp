<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate & Send Alerts</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
      <style>
        p a {
            color: blue;
            text-decoration: none;
        }
        .input-field .searchable_label {
            font-size: 0.9rem;
        }        
        .input-field .searchable_label {
		    font-size: 0.95rem;
		    color: #555;
		    font-weight: 600;
		}
		.error-msg label{color:red!important;}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
 
    <div class="row">
               
        <div class="col s12 m12">
               <div class="row">               
                <div class="col s12 m5 l3">
                 <div class="card">
	                	<div class="card-content">     
			            	<div class="card">
			                	<div class="card-content">                  
			                      <div class="row no-mar">	     
			                          <div class="col s12 m12 input-field center-align">
			                              <a class="btn bg-m waves-effect waves-light t-c" style="min-width:160px" href="<%=request.getContextPath() %>/generate-alerts-manually"> Generate Alerts</a>
			                          </div>                            
			                      </div>                  
			                    </div>
			                </div>  
			                <div class="card">
			                	<div class="card-content">                  
			                      <div class="row no-mar">	     
			                          <div class="col s12 m12 input-field center-align">
		                              	 <a class="btn bg-m waves-effect waves-light t-c" style="min-width:160px;height: auto;" href="<%=request.getContextPath() %>/send-alerts-to-all"> Send Contract & Issue Alerts to All </a>
			                          </div>                            
			                      </div>   
			                      <div class="row no-mar">	     
			                          <div class="col s12 m12 input-field center-align">
		                              	 <a class="btn bg-m waves-effect waves-light t-c" style="min-width:160px;" href="<%=request.getContextPath() %>/send-risk-alerts-to-all"> Send Risk Alerts to All </a>
			                          </div>                            
			                      </div>    
			                      <div class="row no-mar">	     
			                          <div class="col s12 m12 input-field center-align">
		                              	 <a class="btn bg-m waves-effect waves-light t-c" style="min-width:160px" href="<%=request.getContextPath() %>/send-alerts-rajiv-ravi"> Send All Alerts to IT Admins </a>
			                          </div>                            
			                      </div>                   
			                    </div>
			                </div>              
		          		</div>
          		</div>
          		</div>
          		
                <div class="col m7 l9 s12 ">                
            	<div class="card">
	                <div class="card-content">
	                    <span class="card-title headbg">
	                        <div class="center-align bg-m p-2 m-b-5">
	                            <h6>Send Alerts</h6>
	                        </div>
	                    </span>
	                    <div class="row clearfix">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<c:if test="${not empty success }">
									<div class="center-align m-1 close-message">${success}</div>
								</c:if>
								<c:if test="${not empty error }">
									<div class="center-align m-1 close-message">${error}</div>
								</c:if>
							</div>
						</div>
	                    <form action="<%=request.getContextPath() %>/send-alerts-to-particulars" id="sendAlertsForm" name="sendAlertsForm" method="post">
	                         <div class="row no-mar">
	                         	<div class="col s12 m10 offset-m1">
		                         	<div class="row">
		                         		  <div class="col s12 m6 l4 input-field">
			                                 <p class="searchable_label">Alert Type</p>
			                                 <select class="searchable validate-dropdown" name="alert_type_fk" id="alert_type_fk" multiple="multiple" >
			                                  	 <option value="" disabled="disabled">Select</option>
			                                     <c:forEach var="obj" items="${alertTypes}">
												 	<option value="${obj.alert_type_fk }">${obj.alert_type_fk}</option>
			                                     </c:forEach>
			                                  </select>
			                                 <span id="alert_type_fkError" class="error-msg" ></span>
			                             </div>
			                             <div class="col s12 m6 l4 input-field">
			                                 <p class="searchable_label">Alert Level</p>
			                                 <select  class="searchable validate-dropdown" name="alert_level" id="alert_level" multiple="multiple" >
			                               		 <option value="" disabled="disabled">Select</option>
			                                     <c:forEach var="obj" items="${alertLevels}">
									 				<option value="${obj.alert_level }">${obj.alert_level}</option>
			                                     </c:forEach>
			                                  </select>
			                                 <span id="alert_levelError" class="error-msg" ></span>
			                             </div>
			                             <div class="col s12 m6 l4 input-field">
			                                 <p class="searchable_label">Send To</p>
			                                 <select  class="searchable validate-dropdown" name="email_id" id="email_id" multiple="multiple" >
			                               		 <option value="" disabled="disabled">Select</option>
			                                     <c:forEach var="obj" items="${sendToList}">
									 				<option value="${obj.email_id }"><c:if test="${not empty obj.designation }">${obj.designation} - </c:if>${obj.user_name}</option>
			                                     </c:forEach>
			                                 </select>
			                                 <span id="email_idError" class="error-msg" ></span>
			                             </div>
		                         	</div>
	                         	</div>
	                                                    
	                         </div>
	                         
	                         <div class="row no-mar">	     
	                             <div class="col s6 m4 l3 input-field center-align offset-l3 offset-m1">
	                                 <button class="btn bg-m waves-effect waves-light t-c"
	                                     style="min-width:160px;" type="button" onclick="sendAlerts()">Send Alerts</button>
	                             </div>
	                             <div class="col s6 m4 l3 input-field center-align offset-m1">
	                                 <button class="btn bg-s waves-effect waves-light t-c" type="button"
	                                     style="min-width:160px" type="button" onclick="clearFilter()">Clear</button>
	                             </div>
	                         </div>
	                        
	                        </form>   
                        </div>                    
                    </div>
                </div>
            </div>
        </div>
    </div>
         
    <div class="page-loader" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
    <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script>        
        $(document).ready(function(){
        	$('.searchable').select2();        	
        });
        
        function clearFilter(){
        	//$('#alert_type_fk').val('');
    		//$('#alert_level').val('');
    		//$('#email_id').val('');
    		//$('.searchable').select2(); 
    		window.location.reload();
    	}       
          
        function sendAlerts() {
        	//alert(validator.form());
        	if(validator.form()){
        		$('.page-loader').show();
            	$("#sendAlertsForm").submit();
        	}
        	
		}       
        
        var validator =	$('#sendAlertsForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "alert_type_fk": {
	  			 		required: true
	  			 	  },"alert_level": {
	  			 		required: true
	  			 	  },"email_id": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "alert_type_fk": {
	  				 	required: 'This field is required',
	  			 	  },"alert_level": {
	  			 		required: ' This field is required'
	  			 	  },"email_id": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "alert_type_fk" ){
						 document.getElementById("alert_type_fkError").innerHTML="";
				 		 error.appendTo('#alert_type_fkError');
					} else if(element.attr("id") == "alert_level" ){
						   document.getElementById("alert_levelError").innerHTML="";
					 	   error.appendTo('#alert_levelError');
					} else if(element.attr("id") == "email_id" ){
						   document.getElementById("email_idError").innerHTML="";
					 	   error.appendTo('#email_idError');
					} else{
	 					error.insertAfter(element);
			        }
                }
		   		,submitHandler:function(form){
			    	form.submit();
			    }
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
        

    </script>

</body>

</html>