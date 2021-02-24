<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Project</c:if>
		 <c:if test="${action eq 'add'}"> Add Project</c:if>
    
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">    
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">     
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/project.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
	<style>
		.my-error-class {
			color: red;
		}
		
		.my-valid-class {
			color: green;
		}
		.images-show{
			display: flex;
		    flex-wrap: wrap;
		    justify-content: flex-start;
		}
		.images-show img,.images-show video{
			width:100px;
			height:100px;
			margin:5px;
		}
		.images-show img:first-of-type{			
			margin-left:0;
		}
		.images-show img:last-of-type{
			margin-right:0;
		}
		@media only screen and (max-width: 600px) {
		  .images-show{
		    justify-content: space-evenly;
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
                            <div class="center-align p-2 bg-m">
                                <c:if test="${action eq 'add'}">	
                               			 <h6>Add Project</h6>
                               	 </c:if>
                               	 <c:if test="${action eq 'edit'}">	
                               			 <h6>Edit Project</h6>
                               	 </c:if>
                            </div>
                        </span>
                    </div>
             <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                	<c:if test="${not empty success }">
				        <div class="center-align m-1 close-message">	
						   ${success}
						</div>
					</c:if>
					<c:if test="${not empty error }">
						<div class="center-align m-1 close-message">
						   ${error}
						</div>
					</c:if>
				</div>
			</div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">				                
			                	<form action="update-Project" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                                      </c:if>
			              
			                <c:if test="${action eq 'add'}">				                
			                	<form action="add-Project" id="projectForm" name="projectForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
							</c:if> 
							
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                 
                                <c:if test="${action eq 'edit'}">				                
                                     <input id="project_id" type="text" class="form-control" name="project_id" value="${projectDeatils.project_id }" readonly >   
                               		 <label>Project ID :</label>
                                </c:if>
                                   
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id=project_name type="text" class="validate" value="${projectDeatils.project_name }" name="project_name">
                                    <label for="project_name">Project Name <span class="required">*</span></label>
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
							<div class="col m2 hide-on-small-only"></div>
							<div class="col s12 m4 input-field">
								<!-- <p class="searchable_label">Project Status</p> -->
								<select class="validate-dropdown" name="project_status" id="project_status">
									<option value="">Select</option>
									<option value="Open" <c:if test="${projectDeatils.project_status == 'Open'}">selected</c:if>>Open</option>
									<option value="Closed" <c:if test="${projectDeatils.project_status == 'Closed'}">selected</c:if>>Closed</option>
								</select> 
								<label for="project_staus">Project Status</label>
								<span id="project_statusError"></span>
							</div>
							<div class="col m4 s12">
								<div class="file-field input-field">
									<div class="btn bg-m t-c">
										<span>Attachment</span> <input type="file" id="projectFile"
											name="projectFile">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text" id="project_attachment" multiple
											name="attachment" value="${projectDeatils.attachment }">
									</div>
								</div>
								<c:if test="${not empty projectDeatils.attachment }">
									<div><a href="<%=CommonConstants.PROJECT_FILES %>${projectDeatils.attachment }"
										class="filevalue" download>${projectDeatils.attachment }</a>
										<span onclick="removeMedia(this,'project_attachment')" class="attachment-remove-btn">X</span>
									</div>
								</c:if>
							
							</div>
							<div class="col m2 hide-on-small-only"></div>
						</div>

						<div class="row">
                              <div class="col m2 hide-on-small-only"></div>
                              <div class="col s12 m8 input-field">
                                  <textarea id="benefits"  name="benefits" class="materialize-textarea" data-length="1000">${projectDeatils.benefits }</textarea>
                                  <label for="benefits">Benefits</label>
                                   <span id="benefitsError"></span>
                              </div>
                         </div>
						<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" class="materialize-textarea" data-length="1000"  name="remarks">${projectDeatils.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                     <span id="remarksError"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
	                            <div class="col m8 s12">
									<div class="file-field input-field">
										<div class="btn bg-m t-c">
											<span>Images</span> <input type="file" id="projectGalleryFiles"	name="projectGalleryFiles"   multiple accept="image/x-png,image/gif,image/jpeg,video/mp4,video/webm,video/avi,video/mkv" >
										</div>
										<div class="file-path-wrapper">
											<input class="file-path validate" type="text" id="galleryFileNames" name="galleryFileNames" value="${projectDeatils.galleryFileNames}" >
										</div>
									</div>
									<c:forEach var="obj" items="${fileNames }" varStatus="index">
										<div><a href="<%=CommonConstants2.PROJECT_GALLERY%>${obj.project_id_fk }/${obj.file_name } "
											class="filevalue" download>${obj.file_name }</a>
											<span onclick="removeImages(this,'projectGalleryFiles${index.count }','galleryFileNames')" class="attachment-remove-btn">X</span>
										</div>
										 <c:choose>
										 	 <c:when  test="${not empty obj.file_name }">
										 	 	<input type="hidden" id="projectGalleryFiles${index.count }" name="projectGalleryFileNames" value="${obj.file_name }">
										 	 </c:when>											
									     </c:choose> 
									</c:forEach>
									
									<c:if test="${ empty projectDeatils.file_name && empty projectDeatils.galleryFileNames}">
										<input type="hidden"  name="projectGalleryFileNames" value="A" style="display:none" >
									</c:if>
									
									<!-- have to hide this div, if images are 0
									<div class="images-show">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">
										<img src="/pmis/resources/images/mrvc.png">																
									</div> -->
								</div>
								<div class="col m2 hide-on-small-only"></div>
							</div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                     <c:if test="${action eq 'edit'}">
 											<button style="width: 100%;" onclick="updateProject();" class="btn waves-effect waves-light bg-m">Update</button>    
 									 </c:if>
                                         <c:if test="${action eq 'add'}">
 											<button style="width: 100%;" onclick="addProject();" class="btn waves-effect waves-light bg-m">Add</button>    
 									 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/project" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
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
            $(".datepicker").datepicker();
            $('#remarks,#p_desc').characterCounter();
        });
        
        function addProject(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	    		
    			document.getElementById("projectForm").submit();			
    	 	}
    	}
  
        function updateProject(){
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	    		
    			document.getElementById("projectForm").submit();			
    	 	}
    	}
   
        var validator =	$('#projectForm').validate({
				 errorClass: "my-error-class",
				   validClass: "my-valid-class",
				 ignore: ":hidden:not(.chosen-select)",
		  		    rules: {
		  		 		  "project_name": {
		  			 		required: true
		  			 	  },"plan_head_number": {
		  			 		required: false
		  			 	  },"pink_book_item_number": {
		  		 		    required: false
		  			 	  }	,"remarks": {
		  			 		required: false
		  			 	  }		
		  		 	},
		  		    messages: {
		  		 		 "project_name": {
		  				 	required: 'This field is required',
		  			 	  },"plan_head_number": {
		  			 		required: ' This field is required'
		  			 	  },"pink_book_item_number": {
		  		 			required: ' This field is required'
		  		 	  	 },"remarks": {
		  		 			required: ' This field is required'
		  		 	  	 }
			   		},
			   		errorPlacement:function(error, element){
			   		 	if (element.attr("id") == "project_name" ){
							 document.getElementById("project_nameError").innerHTML="";
					 		 error.appendTo('#project_nameError');
							 }
			   		 	else if(element.attr("id") == "plan_head_number" ){
							   document.getElementById("plan_head_numberError").innerHTML="";
						 	   error.appendTo('#plan_head_numberError');
								 }
			   		 	else if(element.attr("id") == "pink_book_item_number" ){
								document.getElementById("pink_book_item_numberError").innerHTML="";
							 	error.appendTo('#pink_book_item_numberError');
									 }
						 else if(element.attr("id") == "remarks" ){
						 		 document.getElementById("remarksError").innerHTML="";
				 				 error.appendTo('#remarksError');
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
       function removeMedia(link,id){
    	  $('#'+id).val('');
    	  $(link).prev().text('');
    	  $(link).css('display','none');
       }        
       function removeImages(link,id,galleryFileNames){
    	
     	//  var text=$('#'+id).val('');
     	 var text1=$('#'+galleryFileNames).val();
     	 //console.log(text1)
     	 // text=text.indexOf(','+$(link).prev().text())?text.replace(','+$(link).prev().text(),''):( text.indexOf($(link).prev().text()+',') ? text.replace($(link).prev().text()+',','') : text.replace($(link).prev().text(),'')) ;
     	  text1= text1.replace($(link).prev().text(),'') ;
     	  text1 = text1.replace(/,\s*$/, "");
     	 console.log(text1)
     	  $('#'+galleryFileNames).val(text1)
     	  $(link).prev().text(''); 
     	  $(link).css('display','none');
     	  
        } 
        
    </script>
</body>

</html>