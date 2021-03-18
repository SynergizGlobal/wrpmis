<%@ page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update Admin Form</c:if>
		 <c:if test="${action eq 'add'}"> Add Admin Form</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }

        .fw-8p {
            width: 8%;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }

        input[type=url]:not(.browser-default):focus:not([readonly])+label {
            color: #2E58AD !important;
        }

        input[type=url]:not(.browser-default):focus:not([readonly]) {
            border-bottom: 1px solid #2E58AD;
            box-shadow: 0 1px 0 0 #2E58AD;
        }

        .select2-container--default .select2-selection--single {
            background-color: transparent;
        }
        
		.error-msg-class{
		color:red!important; 
		}
		.m-b-2{
			margin-bottom:2rem;
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
	                                <c:if test="${action eq 'edit'}">Update Admin Form</c:if>
			 						<c:if test="${action eq 'add'}"> Add Admin Form</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                      <c:if test="${action eq 'edit'}">				                
		                	<form action="<%=request.getContextPath() %>/update-admin" id="adminForm" name="adminForm" method="post" class="form-horizontal" role="form" >
                      </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-admin" id="adminForm" name="adminForm" method="post" class="form-horizontal" role="form">
					  </c:if>
                        <div class="container container-no-margin">
                        <input type="hidden" name="admin_form_id" value="${adminDetails.admin_form_id }"   />
                            <div class="row">
                                <div class="col s12 m4 input-field offset-m2">
                                    <input id="form_name" name="form_name" type="text" class="validate" value="${adminDetails.form_name }">
                                    <label for="form_name">Form Name</label>
                                    <span id="form_nameError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="url" name="url" type="text" class="validate"  value="${adminDetails.url }">
                                    <label for="url">Url </label>
                                    <span id="urlError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m4 input-field offset-m2">
                                    <input id="priority" name="priority" type="number" class="validate"  value="${adminDetails.priority }">
                                    <label for="priority">Priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="soft_delete_status_fk" class="searchable validate-dropdown" name="soft_delete_status_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${statusList}">
											<option value="${obj.soft_delete_status_fk }"
												<c:if test="${adminDetails.soft_delete_status_fk eq obj.soft_delete_status_fk }">selected</c:if>>${obj.soft_delete_status_fk }</option>
										</c:forEach>
                                    </select>
                                    <span id="soft_delete_status_fkError" class="error-msg"></span>
                                </div>
                            </div>

                            <!-- </div> -->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                   <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateAdmin();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addAdmin();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/admin" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
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

    <!-- footer  -->
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

        function addAdmin(){
       	 if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	   			document.getElementById("adminForm").submit();			
  	 	 }
       }
        function updateAdmin(){
          	 if(validator.form()){ // validation perform
   	        	$(".page-loader").show();	
   	   			document.getElementById("adminForm").submit();			
     	 	 }
          }
           

        var validator =	$('#adminForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		   "form_name": {
	  			 		  required: true
	  			 	  },"url": {
	  			 		  required: true
	  			 	  },"priority": {
	  			 		  required: true
	  			 	  },"soft_delete_status_fk": {
	  			 		  required: true
	  			 	  }		
	  		 	},
	  		    messages: {
	  		 		   "form_name": {
	  			 		  required: 'Required'
	  			 	  },"url": {
	  			 		  required: 'Required'
	  			 	  },"priority": {
	  			 		  required: 'Required'
	  			 	  },"soft_delete_status_fk": {
	  			 		  required: 'Required'
	  			 	  }		
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "form_name" ){
					     document.getElementById("form_nameError").innerHTML="";
				 	     error.appendTo('#form_nameError');
					 }else if(element.attr("id") == "url" ){
					     document.getElementById("urlError").innerHTML="";
				 	     error.appendTo('#urlError');
					 }else if(element.attr("id") == "priority" ){
					     document.getElementById("priorityError").innerHTML="";
				 	     error.appendTo('#priorityError');
					 }else if(element.attr("id") == "soft_delete_status_fk" ){
					     document.getElementById("soft_delete_status_fkError").innerHTML="";
				 	     error.appendTo('#soft_delete_status_fkError');
					 }else{
	 					 error.insertAfter(element);
			        } 
		   		},submitHandler:function(form){
			    	//form.submit();
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