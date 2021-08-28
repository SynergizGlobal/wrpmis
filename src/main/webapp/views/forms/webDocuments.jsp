<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ROLE_CODE_DATA_ADMIN" value="<%=CommonConstants.ROLE_CODE_DATA_ADMIN %>"></c:set>
<c:set var="ROLE_CODE_IT_ADMIN" value="<%=CommonConstants.ROLE_CODE_IT_ADMIN %>"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>
		<c:choose>
		 	<c:when test="${not empty title }">
		 		${title }
		 	</c:when>
		 	<c:otherwise>
		 		Web Documents - PMIS - Syntrack.
		 	</c:otherwise>
		</c:choose>
	</title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" href="/pmis/resources/css/document-pages.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css">
	
	<!-- if need less code try to replace document-template css with mobile-document-template --> 
	<style>
		.w-12p{
			width:12%;
		}
		@media screen and (min-width: 1024px){
			.w-12p.last-column {
			    width: 30%;
			}
		}
	</style>

</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="col s12 m12">
			<div class="card main-card">
				<div class="card-content">
					<div class="center-align">
						<span class="card-title headbg">
							<div class="center-align p-2 bg-m">
								<h5><strong style="text-transform: capitalize;">${documentType}</strong></h5>
							</div>
						</span>
					</div>
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
					<div class="container">
						<div class="row">
							<div class="input-field col 
							<c:choose>
    							<c:when test='${(sessionScope.USER_ROLE_CODE eq ROLE_CODE_DATA_ADMIN) or (sessionScope.USER_ROLE_CODE eq ROLE_CODE_IT_ADMIN)}'>
									m10 s8  
								</c:when>    
    							<c:otherwise>
    								m12 s12
								</c:otherwise>
							</c:choose>
							">
								<i class="material-icons prefix right-side">search</i> 
								<input id="fileSearch" type="text" class="validate autocomplete" placeholder="Search ...">
							</div>
							<c:if test="${(sessionScope.USER_ROLE_CODE eq ROLE_CODE_DATA_ADMIN) or (sessionScope.USER_ROLE_CODE eq ROLE_CODE_IT_ADMIN)}">	
							    <div class="col m2 s4 input-field ">
									<a class="btn t-t-i bg-m modal-trigger file-upload-btn mob-pad-reduce" href="#upload-modal">Upload</a>							
								</div>
							</c:if>
						</div>
					
						<div class="row folder-group">
							<c:choose>   
	                          <c:when test="${not empty webDocuments and fn:length(webDocuments) gt 0}">
		                        <c:forEach var="webDocCategory" items="${webDocuments}" varStatus="index">     
								  <div class="files-filter">
									<div class="folder-header" id="folder${index.count }">
										<i class="fa fa-folder open"></i> ${webDocCategory.category }
									</div>
									<div class=" folder-body">
										<div>
										<c:choose>   
										 <c:when test="${not empty webDocCategory.webDocumentsList and fn:length(webDocCategory.webDocumentsList) gt 0}">
										 <div class="row">
											 <div class="col s12 m12">
											    <table class="bordered">
											        <thead>
											            <th class="w-8p">S.No</th>
											            <th class="title-text">Subject</th>
											            <c:if test="${fn:containsIgnoreCase(documentType, 'policies')}">
											            <th>Date of Issue</th>
											            </c:if>
											            <th class="last-column <c:choose>
								    									<c:when test='${(sessionScope.USER_ROLE_CODE eq ROLE_CODE_DATA_ADMIN) or (sessionScope.USER_ROLE_CODE eq ROLE_CODE_IT_ADMIN)}'> w-12p </c:when>    
										    							<c:otherwise> w-8p </c:otherwise>
																	</c:choose> "> </th>
											        </thead>
											        <tbody>
											        <c:forEach var="webDoc" items="${webDocCategory.webDocumentsList}" varStatus="indexx"> 
											            <tr id="row${indexx.count }${index.count }">
											                <td>${indexx.count }</td>
											                <td class="title-text">${webDoc.title }</td>
											                <c:if test="${fn:containsIgnoreCase(documentType, 'policies')}">
											                <td class="date">${webDoc.date_of_issue }</td>
											                </c:if>
											                <td><a href="#modal1${indexx.count }${index.count }" class="modal-trigger"><i class="fa fa-eye"></i></a> 
											                    <a class="file-name" href="<%=CommonConstants2.WEB_DOCUMENTS %>${documentType }/${webDocCategory.category }/${webDoc.file_name }" download><i class="fa fa-download"></i></a>
											                    <c:if test='${(sessionScope.USER_ROLE_CODE eq ROLE_CODE_DATA_ADMIN) or (sessionScope.USER_ROLE_CODE eq ROLE_CODE_IT_ADMIN)}'>
											                    	<a href="#" onclick="editWebDocument('${webDoc.title}','${webDoc.category_id_fk}','${webDoc.file_name}','${webDoc.date_of_issue}','${webDoc.web_document_id}')"><i class="fa fa-pencil"></i></a>
											                    	<a href="#" ><i class="fa fa-trash" onclick="deleteRow('${webDoc.web_document_id}')"></i></a>
											                    </c:if> 
											                </td>
											                <div id="modal1${indexx.count }${index.count }" class="modal preview-modal">
															    <div class="modal-content">
															      <h5 class="modal-header">File Preview <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
															      <div class="center-align" style="margin-top:50px">
															      	<embed src="<%=CommonConstants2.WEB_DOCUMENTS %>${documentType }/${webDocCategory.category }/${webDoc.file_name }#toolbar=0" width="800px" height="2100px" />
															      </div>
															    </div>
															</div>
											            </tr>
											         </c:forEach>
											        </tbody>
											    </table>
											 </div>
										</div>
										<!--   <c:forEach var="webDoc" items="${webDocCategory.webDocumentsList}" varStatus="indexx"> 
											<div class="card card-file files-filter-data"  id="row${indexx.count }${index.count }">
												<div class="card-content center-align ">
													<img src="/pmis/resources/images/document.svg"> 
													<span class="card-title">${webDoc.title }</span>
												</div>
												<div class="card-action flex">
													<a href="#modal1${indexx.count }${index.count }" class="modal-trigger"><i class="fa fa-eye"></i></a> 
													<a href="<%=CommonConstants2.WEB_DOCUMENTS %>${documentType }/${webDocCategory.category }/${webDoc.file_name }" download><i class="fa fa-download"></i></a>
												</div> 
												
												 <div id="modal1${indexx.count }${index.count }" class="modal preview-modal">
												    <div class="modal-content">
												      <h5 class="modal-header">File Preview <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
												      <div class="center-align" style="margin-top:50px">
												      	<embed src="<%=CommonConstants2.WEB_DOCUMENTS %>${documentType }/${webDocCategory.category }/${webDoc.file_name }#toolbar=0" width="800px" height="2100px" />
												      </div>
												    </div>
												 </div>
											</div> 
										  </c:forEach>-->
									  </c:when>
	                               	  <c:otherwise>
	                                	<span style="width: 100%;text-align: center">No files available in ${webDocCategory.category }</span>
	                                  </c:otherwise>
                                   </c:choose>
								</div>
							  </div>
							 <!-- </li> -->
							 </div>
							<!--  </div> -->
							</c:forEach>
	                       </c:when>
                           <c:otherwise>
                           	<!-- <li style="text-align: center!important;">No folders available</li> -->
                           	<div style="text-align: center!important;">No folders available</div>
                           </c:otherwise>
                         </c:choose>
					   <!--  </ul> 
					    </div>-->
					  </div>
					</div>
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
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>
  

  <!-- Modal Structure -->
	<div id="upload-modal" class="modal preview-modal"> 
		<div class="modal-content">
			<h6 class="modal-header">
				<strong style="text-transform: capitalize;">${documentType}</strong> File Upload<span class="right modal-action modal-close"><span class="material-icons">close</span></span>
			</h6>

			<div class="container">
			 	<form action="<%=request.getContextPath() %>/upload-web-document/${document_type}" id="uploadWebDocumentForm" name="uploadWebDocumentForm" method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="col m6 s12 input-field">
							<input type="text" id="title" name="title" class="validate"> 
							<label for="title">Title</label>
							<span id="titleError" class="error-msg" ></span>
						</div>
						<div class="col m6 s12 input-field">
							<p class="searchable_label">Category</p>
							<select id="category_id_fk" name="category_id_fk" class="searchable validate-dropdown" onchange="selectCategory('category_id_fk','category');">
								<option value="">Select</option>
								<c:forEach var="obj" items="${webDocCategoriesList }">
	                                   <option value="${obj.category_id }">${obj.category}</option>
	                            </c:forEach>
							</select>
							<span id="category_id_fkError" class="error-msg" ></span>
						</div>
					</div>
					<div class="row">
						<div class="col m6 s12 input-field file-field">
							<div class="btn bg-m t-t-i">
								<span>Upload File</span> <input type="file" id="webDocument" name="webDocument">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text" id="file_name" name="file_name" >
							</div>
							<span id="webDocumentError" class="error-msg" ></span>
						</div>
						<c:if test="${fn:containsIgnoreCase(documentType, 'policies')}">
						<div class="col m6 s12 input-field">
							<input type="text" id="date_of_issue" name="date_of_issue" class="validate datepicker"> 
							<label for="date_of_issue">Date of Issue</label>
							<span id="date_of_issueError" class="error-msg" ></span>
							<button type="button" id="date_of_issue_icon"><i class="fa fa-calendar"></i></button>
						</div>
						</c:if>
					</div>
					<input type="hidden" id="category" name="category" />
					<div class="row" style="margin-top: 15px;">
						<div class="col s6 m6 center-align">
							<button type="button" onclick="uploadWebDocument();" style="width: 100%;"
								class="btn waves-effect waves-light bg-m">Upload</button>
						</div>
						<div class="col s6 m6 center-align">
							<a href="<%=request.getContextPath()%>/web-documents/${document_type}"  class="btn waves-effect waves-light bg-s modal-close"
                                           style="width:100%">Cancel</a>
						</div>
					</div>
				</form>
			</div>
		
		</div>
	</div>
	
		<div id="update-modal" class="modal preview-modal"> 
		<div class="modal-content">
			<h6 class="modal-header">
				<strong style="text-transform: capitalize;">${documentType}</strong> Update Form<span class="right modal-action modal-close"><span class="material-icons">close</span></span>
			</h6>

			<div class="container">
			 	<form action="<%=request.getContextPath() %>/update-web-document/${document_type}" id="updateWebDocumentForm" name="updateWebDocumentForm" method="post" enctype="multipart/form-data">
					<div class="row">
						<input type="hidden" id="web_document_id" name="web_document_id" />
						<div class="col m6 s12 input-field">
							<input type="text" id="update_title" name="title" class="validate"> 
							<label for="update_title">Title</label>
							<span id="update_titleError" class="error-msg" ></span>
						</div>
						<div class="col m6 s12 input-field">
							<p class="searchable_label">Category</p>
							<select id="update_category_id_fk" name="category_id_fk" class="searchable validate-dropdown" onchange="selectCategory('update_category_id_fk','update_category');">
								<option value="">Select</option>
								<c:forEach var="obj" items="${webDocCategoriesList }">
	                                   <option value="${obj.category_id }">${obj.category}</option>
	                            </c:forEach>
							</select>
							<span id="update_category_id_fkError" class="error-msg" ></span>
						</div>
					</div>
					<div class="row">
						<div class="col m6 s12 input-field">
							<div class="file-field">
								<div class="btn bg-m t-t-i">
									<span>Upload File</span> <input type="file" id="update_webDocument" name="webDocument">
								</div>
								<div class="file-path-wrapper">
									<input class="file-path validate" type="text" id="update_file_name" name="file_name" >
								</div>							
							</div>
							<a id="update_file" href="" download></a>
							<span id="update_webDocumentError" class="error-msg" ></span>
						</div>
						<c:if test="${fn:containsIgnoreCase(documentType, 'policies')}">
						<div class="col m6 s12 input-field">
							<input type="text" id="update_date_of_issue" name="date_of_issue" class="validate datepicker"> 
							<label for="update_date">Date of Issue</label>
							<span id="update_date_of_issueError" class="error-msg" ></span>
							<button type="button" id="update_date_icon"><i class="fa fa-calendar"></i></button>
						</div>
						</c:if>
					</div>
					<input type="hidden" id="update_category" name="category" />
					<div class="row" style="margin-top: 15px;">
						<div class="col s6 m6 center-align">
							<button type="button" onclick="updateWebDocument();" style="width: 100%;"
								class="btn waves-effect waves-light bg-m">Update</button>
						</div>
						<div class="col s6 m6 center-align">
							<a href="<%=request.getContextPath()%>/web-documents/${document_type}"  class="btn waves-effect waves-light bg-s modal-close"
                                           style="width:100%">Cancel</a>
						</div>
					</div>
				</form>
			</div>
		
		</div>
	</div>

	<form action="<%=request.getContextPath() %>/delete-web-document/${document_type}" id="deleteWebDocumentForm" name="deleteWebDocumentForm" method="post">
		<input id="delete_web_document_id" name="web_document_id" />
	</form>
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js" ></script>  
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
	<script>
	 
		$(document).ready(function() {
			$('.collapsible').collapsible();
			$('.searchable').select2();
			$('.modal').modal({
				dismissible : false,
			});
		
			var searchData = {};
	            
            <c:forEach var="mObj" items="${foldersList}" varStatus="index">
            	searchData['${mObj.manual_name}'] = null;
            </c:forEach>
            
            $('input.autocomplete').autocomplete({
                data: searchData,
            });
            
            $('.close-message').delay(5000).fadeOut('slow');            
            
            $('#date_of_issue_icon').click(function () {
                event.stopPropagation();
                $('#date_of_issue').click();
            });
            
            $('#date_of_issue').datepicker({                   
  	    	  maxDate: new Date(),
  	    	  format:'dd-mm-yyyy',
  	    	  //perform click event on done button
  	    	  onSelect: function () {
  	    	     $('.confirmation-btns .datepicker-done').click();
  	    	  }
  	        });
		});
		
		$("#fileSearch").on("keyup change", function() {
            var txt = $('#fileSearch').val();
            $('.files-filter').each(function(){
            	if($(this).text().toUpperCase().indexOf(txt.toUpperCase()) != -1){
                   $(this).show();
               }else{
            	   $(this).hide();
               }
            });
            
            $('.files-filter-data').each(function(){
            	if($(this).text().toUpperCase().indexOf(txt.toUpperCase()) != -1){
                   $(this).show();
               }else{
            	   $(this).hide();
               }
            });
        });
		
		function selectCategory(id1,id2){
			var category = $("#"+id1).find("option:selected").text();
			$("#"+id2).val(category);
		}
		
		function uploadWebDocument(){
			var flag = $("#uploadWebDocumentForm").valid();
			if(flag){
	        	$(".page-loader").show();
	        	$("#uploadWebDocumentForm").submit();
			}
        }
		
		var validator = $('#uploadWebDocumentForm').validate({
			ignore: ":hidden:not(.validate-dropdown)",
		    rules: {
		     		"title":{
		     			required:true
                	},"category_id_fk":{
                		required:true
                	},"webDocument":{
                		required:true
                	}
		    },messages: {
		   			"title":{
		   			  	required:'Required'
		   			 },"category_id_fk":{
                		required:'Required'
	                 },"webDocument":{
                		required:'Required'
	                 }
		   	},errorPlacement:function(error, element){
			     if(element.attr("id") == "title" ){
					 document.getElementById("titleError").innerHTML="";
					 error.appendTo('#titleError');
			     } else if (element.attr("id") == "category_id_fk" ){
					 document.getElementById("category_id_fkError").innerHTML="";
					 error.appendTo('#category_id_fkError');
		         } else if (element.attr("id") == "webDocument" ){
					 document.getElementById("webDocumentError").innerHTML="";
					 error.appendTo('#webDocumentError');
		         }else {
		        	 error.insertAfter(element);
		         } 
			       
		    },submitHandler:function(form){
	             form.submit();
		    }
		});
		
		function updateWebDocument(){
			var flag = $("#updateWebDocumentForm").valid();
			if(flag){
	        	$(".page-loader").show();
	        	$("#updateWebDocumentForm").submit();
			}
        }
		
		var validator = $('#updateWebDocumentForm').validate({
			ignore: ":hidden:not(.validate-dropdown)",
		    rules: {
		     		"title":{
		     			required:true
                	},"category_id_fk":{
                		required:true
                	},"file_name":{
                		required:true
                	}
		    },messages: {
		   			"title":{
		   			  	required:'Required'
		   			 },"category_id_fk":{
                		required:'Required'
	                 },"file_name":{
                		required:'Required'
	                 }
		   	},errorPlacement:function(error, element){
			     if(element.attr("id") == "update_title" ){
					 document.getElementById("update_titleError").innerHTML="";
					 error.appendTo('#update_titleError');
			     } else if (element.attr("id") == "update_category_id_fk" ){
					 document.getElementById("update_category_id_fkError").innerHTML="";
					 error.appendTo('#update_category_id_fkError');
		         } else if (element.attr("id") == "update_file_name" ){
					 document.getElementById("update_webDocumentError").innerHTML="";
					 error.appendTo('#update_webDocumentError');
		         }else {
		        	 error.insertAfter(element);
		         } 
			       
		    },submitHandler:function(form){
	             form.submit();
		    }
		});
		
		function editWebDocument(title,category_id_fk,file_name,date_of_issue,web_document_id){
			//setting values to modal items
			$('#web_document_id').val(web_document_id);
			$('#update_title').val(title);	
			$('#update_category_id_fk').val(category_id_fk)
			$('#update_file_name').val(file_name);
			$('#update_date_of_issue').val(date_of_issue);			
			$('#update-modal').modal('open');
			$('#update_title').focus();  	
			$('#update_date_of_issue').focus();
			$('#update_category_id_fk').select2();
			var category = $('#update_category_id_fk option:selected').text();
			if($.trim(file_name) != ''){
				$("#update_file").attr("href",'<%=CommonConstants2.WEB_DOCUMENTS %>${documentType }/'+category+'/'+file_name);
				$("#update_file").text(file_name);
			}
		}
		
		function deleteRow(web_document_id){
			swal({
				  title: "Are you sure?",
				  text: "You want to delete this record!",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "Yes, delete it!",
				  closeOnConfirm: false,
				  html: false
				}, function(){		
					$('#delete_web_document_id').val(web_document_id);
					$('#deleteWebDocumentForm').submit();
				    //swal("Deleted!","Your imaginary file has been deleted.","success");
				});
		}
	</script>
</body>
</html>