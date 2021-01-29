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
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" href="/pmis/resources/css/document-pages.css">
	<style type="text/css">
		.error-msg label{color:red!important;}
		.bordered th,td{			
			text-align:center !important;
			/*padding:5px;
			border:1px solid #ccc; */
		}
		.bordered td:not(:last-child){
			border-right:1px solid #ccc;
		}
		.bordered th:not(:last-child){
			border-right:1px solid #ccc;
		}
		.bordered tbody td:last-child,
		.bordered tbody td:first-child,
		.bordered thead th:last-child,
		.bordered thead th:first-child{						
			padding:5px 10px;
			text-align:center !important;
		}
		.bordered tr{
			border:1px solid #ccc;
		}
		@media screen and (min-width:1024px){
			.bordered tbody td:last-child a+a{
				margin-left:10px;
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
							<div class="input-field col m10 s8">
								<i class="material-icons prefix right-side">search</i> 
								<input id="fileSearch" type="text" class="validate autocomplete" placeholder="Search ...">
							</div>
							<c:if test="${(sessionScope.USER_ROLE_CODE eq ROLE_CODE_DATA_ADMIN) or (sessionScope.USER_ROLE_CODE eq ROLE_CODE_IT_ADMIN)}">	
							    <div class="col m2 s4 input-field">
									<a class="btn t-t-i bg-m modal-trigger file-upload-btn" href="#upload-modal">Upload</a>							
								</div>
							</c:if>
						</div>
					
						<div class="row files-filter">
						   <ul class="collapsible">
							<c:choose>   
	                          <c:when test="${not empty webDocuments and fn:length(webDocuments) gt 0}">
		                        <c:forEach var="webDocCategory" items="${webDocuments}" varStatus="index">     
								  <li class="files-filter">
									<div class="collapsible-header " id="folder${index.count }">
										<i class="fa fa-folder open"></i> <i class="fa fa-folder-open close"></i> ${webDocCategory.category }
									</div>
									<div class="collapsible-body">
										<!-- <div class="files-collection"> -->
										<div>
										<c:choose>   
										 <c:when test="${not empty webDocCategory.webDocumentsList and fn:length(webDocCategory.webDocumentsList) gt 0}">
										 <div class="row">
											 <div class="col s12 m12">
											    <table class="bordered">
											        <thead>
											            <th style="width:8%;">S.No</th>
											            <th>Subject</th>
											            <th>Date of Issue</th>
											            <th style="width:8%"> </th>
											        </thead>
											        <tbody>
											        <c:forEach var="webDoc" items="${webDocCategory.webDocumentsList}" varStatus="indexx"> 
											            <tr id="row${indexx.count }${index.count }">
											                <td>${index.count }</td>
											                <td>${webDoc.title }</td>
											                <td>${webDoc.title }</td>
											                <td><a href="#modal1${indexx.count }${index.count }" class="modal-trigger"><i class="fa fa-eye"></i></a> 
											                    <a href="<%=CommonConstants2.WEB_DOCUMENTS %>${documentType }/${webDocCategory.category }/${webDoc.file_name }" download><i class="fa fa-download"></i></a>
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
							 </li>
							</c:forEach>
	                       </c:when>
                           <c:otherwise>
                           	<li style="text-align: center!important;">No folders available</li>
                           </c:otherwise>
                         </c:choose>
					    </ul>
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
							<select id="category_id_fk" name="category_id_fk" class="searchable validate-dropdown" onchange="selectCategory();">
								<option value="">Select</option>
								<c:forEach var="obj" items="${webDocCategoriesList }">
	                                   <option value="${obj.category_id }">${obj.category}</option>
	                            </c:forEach>
							</select>
							<span id="category_id_fkError" class="error-msg" ></span>
						</div>
					</div>
					<div class="row">
						<div class="col m12 s12 input-field file-field">
							<div class="btn bg-m t-t-i">
								<span>Upload File</span> <input type="file" id="webDocument" name="webDocument">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text" id="file_name" name="file_name" >
							</div>
							<span id="webDocumentError" class="error-msg" ></span>
						</div>
					</div>
					<input type="hidden" id="category" name="category" />
					<div class="row" style="margin-top: 15px;">
						<div class="col s12 m6">
							<div class="center-align m-1">
								<button type="button" onclick="uploadWebDocument();" style="width: 100%;"
									class="btn waves-effect waves-light bg-m">Upload</button>
							</div>
						</div>
						<div class="col s12 m6">
							<div class="center-align m-1">
									<a href="<%=request.getContextPath()%>/web-documents/${document_type}"  class="btn waves-effect waves-light bg-s modal-close"
	                                            style="width:100%">Cancel</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		
		</div>
	</div>

	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js" ></script>  
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
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
		
		function selectCategory(){
			var category = $("#category_id_fk").find("option:selected").text();
			$("#category").val(category);
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
		         } else {
		        	 error.insertAfter(element);
		         } 
			       
		    },submitHandler:function(form){
	             form.submit();
		    }
		});
	</script>
</body>
</html>