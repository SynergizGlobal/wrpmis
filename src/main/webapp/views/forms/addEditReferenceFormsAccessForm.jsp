<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Reference Form - Admin - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Reference Form - Admin - PMIS</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
    <style>
        .select2-container--default .select2-selection--single {
            background-color: transparent;
        }
        .mt18px{margin-top: 18px !important;}
        @media only screen and (max-width: 768px){
			.input-field p.searchable_label {
			    margin-bottom: 0 !important;
			}
		}	
    </style>
</head>

<body>

    <!-- header  starts-->
        <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
											 <c:if test="${action eq 'edit'}">Update Reference Form</c:if>
											 <c:if test="${action eq 'add'}"> Add Reference Form</c:if>
								</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                       <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-reference-form" id="referenceForm" name="referenceForm" method="post" class="form-horizontal" role="form" >
                         </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-referenceForm" id="referenceForm" name="referenceForm" method="post" class="form-horizontal" role="form" >
						  </c:if>
                        <div class="container container-no-margin">
                        <input type="hidden"  name="reference_forms_id" value="${refrenceFormDetails.reference_forms_id }" />
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="name" name="name" type="text" class="validate" value="${refrenceFormDetails.name }">
                                    <label for="name">Name</label>
                                    <span id="nameError" class="error-msg"></span>
                                </div>
                                <div class="col s6 m4 l4 input-field ">
                                    <input id="form_url" name=form_url type="text" class="validate" value="${refrenceFormDetails.form_url }">
                                    <label for="url">Url </label>
                                    <span id="form_urlError" class="error-msg"></span>
                                </div>
                                <div class="col s6 m4 l4 input-field mt18px">
                                    <p class="searchable_label">Module </p>
                                    <select class="searchable validate-dropdown" id="module_fk" name="module_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${modules}">
                						    <option value="${obj.module_name }" <c:if test="${refrenceFormDetails.module_fk eq obj.module_name }">selected</c:if>>${obj.module_name}</option>
                                    	 </c:forEach>
                                    </select>
                                    <span id="module_fkError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 20px;">
                                
                            </div>

                            <!-- </div> -->
                            <div class="row">
                                <div class="col s6 m4 mt-brdr offset-m2 center-align">                                    
                                    <div class=" m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateReferenceForm();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addReferenceForm();" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 mt-brdr center-align">
                                     <div class=" m-1">
                                        <a href="<%=request.getContextPath()%>/reference-form" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

         <!-- Page Loader -->
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
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });
        
        function updateReferenceForm(){
     	   if(validator.form()){ // validation perform
     	  		   $(".page-loader").show();	  
         		   document.getElementById("referenceForm").submit();			
    	 	 }
        }
        function addReferenceForm(){
     	   if(validator.form()){ // validation perform
 		  		   $(".page-loader").show();	  
 	    		   document.getElementById("referenceForm").submit();			
    	 	 }
        }
        
        var validator =	$('#referenceForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		 "name": {
	  			 		required: true
	  			 	  },
	  			 	 "form_url": {
		  			 	required: true
		  			 },
	  			 	 "module_fk": {
			  			 required: true
			  		 }	
	  		 	},
	  		    messages: {
	  		 		  "name": {
	  			 		required: 'Required'
	  			 	  },
	  			 	"form_url": {
	  			 		required: 'Required'
	  			 	  },
	  			 	"module_fk": {
	  			 		required: 'Required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "name" ){
					     document.getElementById("nameError").innerHTML="";
				 	     error.appendTo('#nameError');
					 }else if(element.attr("id") == "form_url" ){
					     document.getElementById("form_urlError").innerHTML="";
				 	     error.appendTo('#form_urlError');
					 }else if(element.attr("id") == "module_fk" ){
					     document.getElementById("module_fkError").innerHTML="";
				 	     error.appendTo('#module_fkError');
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