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
      <style>
        p a {
            color: blue;
            text-decoration: none;
        }
        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        .row.no-mar{
        	margin-bottom:0;
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
                <div class="col s12 m3">
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
                              	 <a class="btn bg-m waves-effect waves-light t-c" style="min-width:160px" href="<%=request.getContextPath() %>/send-alerts-to-all"> Send Alerts to All </a>
	                          </div>                            
	                      </div>                  
	                    </div>
	                </div>              
          		</div>
          		</div></div>
          		
                <div class="col m9 s12 ">                
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Send Alerts</h6>
                        </div>
                    </span>
                    	<form action="<%=request.getContextPath() %>/generate-alerts" id="alertGenerationForm" name="alertGenerationForm" method="post">
                         <div class="row no-mar">
                         	<div class="col s12 m10 offset-m1">
	                         	<div class="row">
	                         		  <div class="col s12 m4 input-field">
                                 <p class="searchable_label">Alert Type</p>
                                 <select class="searchable validate-dropdown" id="alert_type_fk" name="alert_type_fk" onchange="getContractsListInSafetyReport(this.value);" multiple>
                                     <option value="">Select </option>
                                 </select>
                                 <span id="alert_type_fkError" class="error-msg" ></span>
                             </div>
                             <div class="col s12 m4 input-field">
                                 <p class="searchable_label">Alert Level</p>
                                 <select class="searchable validate-dropdown" id="alert_level_fk" name="alert_level_fk" onchange="getContractsListInSafetyReport(this.value);" multiple>
                                     <option value="">Select </option>
                                 </select>
                                 <span id="alert_level_fkError" class="error-msg" ></span>
                             </div>
                             <div class="col s12 m4 input-field">
                                 <p class="searchable_label">Send To</p>
                                 <select class="searchable validate-dropdown" id="send_to" name="send_to" onchange="getContractsListInSafetyReport(this.value);" multiple>
                                     <option value="">Select </option>
                                 </select>
                                 <span id="send_toError" class="error-msg" ></span>
                             </div>
	                         	</div>
                         	</div>
                                                    
                         </div>
                         
                         <div class="row no-mar">	     
                             <div class="col s12 m3 input-field center-align offset-m3">
                                 <button class="btn bg-m waves-effect waves-light t-c"
                                     style="min-width:160px;" onclick="generateReport()">Generate Alerts</button>
                             </div>
                             <div class="col s12 m3 input-field center-align">
                                 <button class="btn bg-s waves-effect waves-light t-c" type="button"
                                     style="min-width:160px" onclick="clearFilter()">Clear</button>
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
      	function getErrorMessage(jqXHR, exception) {
        	    var msg = '';
        	    if (jqXHR.status === 0) {
        	        msg = 'Not connect.\n Verify Network.';
        	    } else if (jqXHR.status == 404) {
        	        msg = 'Requested page not found. [404]';
        	    } else if (jqXHR.status == 500) {
        	        msg = 'Internal Server Error [500].';
        	    } else if (exception === 'parsererror') {
        	        msg = 'Requested JSON parse failed.';
        	    } else if (exception === 'timeout') {
        	        msg = 'Time out error.';
        	    } else if (exception === 'abort') {
        	        msg = 'Ajax request aborted.';
        	    } else {
        	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        	    }
        	    console.log(msg);
         }
        
        
        $(document).ready(function(){
        	$('.searchable').select2();        	
        });
        
        function clearFilter(){
        	$('#alert_type_fk').val('');
    		$('#alert_level_fk').val('');
    		$('#send_to').val('');
    		$('.searchable').select2();        	
    	}       
          
        function generateReport() {
        	$("#alertGenerationForm").submit();
		}       
        
        var validator =	$('#alertGenerationForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "alert_type_fk": {
	  			 		required: false
	  			 	  },"alert_level_fk": {
	  			 		required: false
	  			 	  },"send_to": {
	  			 		required: false
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "alert_type_fk": {
	  				 	required: 'This field is required',
	  			 	  },"alert_level_fk": {
	  			 		required: ' This field is required'
	  			 	  },"send_to": {
	  			 		required: ' This field is required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "alert_type_fk" ){
						 document.getElementById("alert_type_fkError").innerHTML="";
				 		 error.appendTo('#alert_type_fkError');
					} else if(element.attr("id") == "alert_level_fk" ){
						   document.getElementById("alert_level_fkError").innerHTML="";
					 	   error.appendTo('#alert_level_fkError');
					} else if(element.attr("id") == "send_to" ){
						   document.getElementById("send_toError").innerHTML="";
					 	   error.appendTo('#send_toError');
					} else{
	 					error.insertAfter(element);
			        }
                }
		   		,submitHandler:function(form){
			    	form.submit();
			    }
			}); 
        

    </script>

</body>

</html>