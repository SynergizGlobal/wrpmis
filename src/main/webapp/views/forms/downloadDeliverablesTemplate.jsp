<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Download Deliverables Template - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" >
      <style>            		
		.error-msg label{color:red!important;} 
		
		@media only screen and (min-width:787px){
			.pt-md-5{
				padding-top:5px !important;
			}
		}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
 
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Download Deliverables Template </h6>
                        </div>
                    </span>
							
                    <div class="">
                    <div class="center-align m-1 close-message">${error}</div>
			            <form action="<%=request.getContextPath() %>/download-deliverables-template" id="templateForm" name="templateForm" method="post" target="_blank">	                              
                       		 <div class="row no-mar">
                       		     <div class="col s6 m4 l2 input-field offset-l2 pt-md-3">
                                    <p class="searchable_label" style="text-align:left">Project</p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk" >
                                    		 <option value="" >Select</option>
                                      		 <c:forEach var="obj" items="${projectsList }">
                                         			 <option value="${obj.project_id_fk }" <c:if test="${deliverablesDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                      		 </c:forEach>
		                             </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l3 input-field">
                                   <p class="searchable_label" style="margin: 0 0 0px;text-align: left;">Contract</p>
                                   <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                       	<option value="">Select</option>
                                       	 <c:forEach var="obj" items="${contractsList }">
                                     	  	<option value= "${ obj.contract_id_fk}">${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                       	 </c:forEach>
                                  	</select>
                                   	<span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                   	
                            </div>
                            <div class="row">	                                	
                                <div class="col s7 m4 l3 input-field center-align offset-l3 offset-m2">
                                    <button type="button" class="btn bg-m waves-effe ct waves-light t-c clear-filters"
                                        style="margin-top: 6px;min-width:160px%; font-weight: 600;" onclick="downloadTemplate();">Download Template</button>
                                </div>
                                <div class="col s5 m4 l3 input-field left-align ">
                                    <a class="btn bg-s waves-effect waves-light t-c" type="button"
                                        style="margin-top: 6px; font-weight: 600; min-width:120px"
                                        href="<%=request.getContextPath()%>/deliverables-template">Reset</a>
                                </div>                                
                             </div>
                        </form>
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

    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <!-- <script src="/wrpmis/resources/js/datepickerDepedency.js"></script> -->
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/wrpmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    <script>
    var filtersMap = new Object();
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
        

         //geting contracts list    
         function getContractsList(work_id_fk) {
         	$(".page-loader").show();
             $("#contract_id_fk option:not(:first)").remove();
             
             if ($.trim(work_id_fk) != "") {
                 var myParams = { work_id_fk: work_id_fk };
                 $.ajax({
                 	url: "<%=request.getContextPath()%>/ajax/getContractsListForDeliverablesForm",
                     data: myParams, cache: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                             	var contract_short_name = '';
                                 if ($.trim(val.contract_short_name) != '') { contract_short_name = '  - ' + $.trim(val.contract_short_name) }
                                 $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_short_name) + '</option>');
                             });
                         }
                         $('.searchable').select2();
                         $(".page-loader").hide();
                     }
                 });
             }else{
             	$(".page-loader").hide();
             }
         }
         
         function downloadTemplate(){
        	 if(validator.form()){ 	    		
    	  			document.getElementById("templateForm").submit();	
         	}
         }
         
         var validator =	$('#templateForm').validate({	
			 errorClass: "my-error-class",
			 validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
	  			 		required: false
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"contract_id_fk": {
	  		 		    required: false
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"contract_id_fk": {
	  		 			required: ' This field is required'
	  		 	  	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						 document.getElementById("project_id_fkError").innerHTML="";
				 		 error.appendTo('#project_id_fkError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "contract_id_fk" ){
						document.getElementById("contract_id_fkError").innerHTML="";
					 	error.appendTo('#contract_id_fkError');
					}else{
	 					error.insertAfter(element);
			        } 
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
		 
		 $.validator.addMethod("dateFormat",
	        	    function(value, element) {
	        	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
	        	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
	        	    	//return dtRegex.test(value);
       	    },
       	    //"Date format (Aug 02,2020)"
       	    "Date format (DD-MM-YYYY)"
       	);
    </script>

</body>

</html>