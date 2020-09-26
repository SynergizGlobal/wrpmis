<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Project</title>
         <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/project.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    
    <style>
    
    
    .my-error-class {
    color:red;
}
	.my-valid-class {
    color:green;
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
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit Project</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="updateProject" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form">
                                      </c:if>
			              
			                <c:if test="${action eq 'add'}">				                
			                	<form action="addProject" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form">
							</c:if> 
							
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                 
                                <c:if test="${action eq 'edit'}">				                
                                     <input id="project_id" type="text" class="form-control" name="project_id" value="${projectDeatils.project_id }" readonly >   </c:if>
                                     
                                    
                               
                                    <label>Project ID :</label>
                                    <span  id="project_idError"> </span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id=project_name type="text" class="validate" value="${projectDeatils.project_name }" name="project_name">
                                    <label for="p_name">Project Name</label>
                                      <span  id="project_nameError"> </span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="pink_book_item_number" type="text" class="validate" value="${projectDeatils.pink_book_item_number }" name="pink_book_item_number">
                                    <label for="pink_book_item_number">PB Item Number</label>
                                    <span  id="pink_book_item_numberError"> </span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="plan_head_number" type="text" class="validate" value="${projectDeatils.plan_head_number }" name="plan_head_number">
                                    <label for="plan_head_number">Plan Head Number</label>
                                    <span  id="plan_head_numberError"> </span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <!-- 
                            <div class="row">
                                //row 6
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="p_desc" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="p_desc">Project Description</label>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div> -->

                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" class="materialize-textarea" data-length="1000"  name="remarks">${projectDeatils.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                     <span id="remarksError"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                     <c:if test="${action eq 'edit'}">
 											<button style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>    
 									 </c:if>
                                         <c:if test="${action eq 'add'}">
 											<button style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>    
 									 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</button>
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


    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>




    <script src="/pmis/resources/js/jQuery.min.js"></script>
    <script src="/pmis/resources/js/materialize.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script> 

    <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('.sidenav').sidenav();
            $(".datepicker").datepicker();
            $('#textarea1,#p_desc').characterCounter();
        });
        
        $(document).ready(function() {
			 $('#projectForm').validate({
				 errorClass: "my-error-class",
				   validClass: "my-valid-class",
				 ignore: ":hidden:not(.chosen-select)",
		  		    rules: {
		  		 		  "project_name": {
		  			 	required: true
		  			 	  },"plan_head_number": {
		  			 		required: true
		  			 	  },"pink_book_item_number": {
		  		 		    required: true
		  			 	  }	,"remarks": {
		  			 		required: true
		  			 	  }		
		  		 	},
		  		    messages: {
		  		 		 "project_name": {
		  				 	required: 'Project name required',
		  			 	  },"plan_head_number": {
		  			 		required: ' required'
		  			 	  },"pink_book_item_number": {
		  		 			required: ' required'
		  		 	  	 },"remarks": {
		  		 			required: ' Required'
		  		 	  	 }
			   		},
			   		errorPlacement:function(error, element){
			   		 	if (element.attr("id") == "project_name" ){
							  document.getElementById("project_nameError").innerHTML="";
					 			 error.appendTo('#project_nameError');
							 }
			   		 	else if (element.attr("id") == "plan_head_number" ){
								  document.getElementById("plan_head_numberError").innerHTML="";
						 			 error.appendTo('#plan_head_numberError');
								 }
			   		 	else if (element.attr("id") == "pink_book_item_number" ){
									  document.getElementById("pink_book_item_numberError").innerHTML="";
							 			 error.appendTo('#pink_book_item_numberError');
									 }
						 else if (element.attr("id") == "remarks" ){
						  document.getElementById("remarksError").innerHTML="";
				 			 error.appendTo('#remarksError');
						 }else{
			 			error.insertAfter(element);
					       } 
			
			   		},submitHandler:function(form){
				    	form.submit();
				    }
				});   
       });
        
        
    </script>
</body>

</html>