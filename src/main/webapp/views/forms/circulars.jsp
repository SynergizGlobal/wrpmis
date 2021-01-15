<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Circulars</title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" href="/pmis/resources/css/document-pages.css">
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
								<h5>Circulars</h5>
							</div>
						</span>
					</div>
 						<c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
							<div class="center-align m-1 close-message">	
							   ${updateSuccess}
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
									<input id="fileSearch" type="text" class="validate autocomplete"
									placeholder="Search ...">
							</div>

							<%--  <c:if test="${action eq 'Yes'}">	 --%>
							     <div class="col m2 s4 input-field">
								<a class="btn t-t-i bg-m modal-trigger file-upload-btn" href="#upload-modal">Upload</a>							
								</div>
							<%-- </c:if> --%>
					</div>
					
						<div class="row files-filter">
						   <ul class="collapsible">
							<c:choose>   
	                          <c:when test="${foldersList.size() gt 0}">
		                        <c:forEach var="rows" items="${foldersList}" varStatus="index">     
								  <li class="files-filter">
									<div class="collapsible-header  " id="folder${index.count }">
										<i class="fa fa-folder open"></i> <i class="fa fa-folder-open close"></i> ${rows.manual_folder_fk }
									</div>
									<div class="collapsible-body">
										<div class="files-collection">
										<c:choose>   
										 <c:when test="${not empty rows.CircularsList && fn:length(rows.CircularssList) gt 0 }">
										  <c:forEach var="data" items="${rows.CircularsList}" varStatus="indexx"> 
											<div class="card card-file files-filter-data"  id="row${indexx.count }${index.count }">
												<div class="card-content center-align ">
													<img src="/pmis/resources/images/document.svg"> 
													<span class="card-title">${data.Circulars_name }</span>
												</div>
												<div class="card-action flex">
													<a href="#modal1${indexx.count }${index.count }" class="modal-trigger"><i class="fa fa-eye"></i></a> 
													<a href="<%=CommonConstants.MANUAL_FILES%>${data.attachment }" download><i class="fa fa-download"></i></a>
												</div>
												 <div id="modal1${indexx.count }${index.count }" class="modal preview-modal">
												    <div class="modal-content">
												      <h5 class="modal-header">File Preview <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
												      <div class="center-align" style="margin-top:50px">
												      	<embed src="#" width="800px" height="2100px" />
												      </div>
												    </div>
												 </div>
											</div>
										  </c:forEach>
									  </c:when>
	                               	  <c:otherwise>
	                                	<span style="width: 100%;text-align: center">No files available in ${rows.Circulars_folder_fk }</span>
	                                  </c:otherwise>
                                   </c:choose>
								</div>
							  </div>
							 </li>
							</c:forEach>
	                       </c:when>
                           <c:otherwise>
	                        <!-- 	<li style="text-align: center!important;">No folders available</li> -->
								<li class="files-filter">
									<div class="collapsible-header  " id="folder1">
										<i class="fa fa-folder open"></i> <i
											class="fa fa-folder-open close"></i> folder-name
									</div>
									<div class="collapsible-body">
										<div class="files-collection">	
											<div class="card card-file files-filter-data"	id="row1">
												<div class="card-content center-align ">
													<img src="/pmis/resources/images/document.svg"> <span
														class="card-title">title-name</span>
												</div>
												<div class="card-action flex">
													<a href="#modal11"	class="modal-trigger"><i class="fa fa-eye"></i></a> 
													<a	href="#"	download><i class="fa fa-download"></i></a>
												</div>
												<div id="modal1"
													class="modal preview-modal">
													<div class="modal-content">
														<h5 class="modal-header">	File Preview <span	class="right modal-action modal-close">
														<span	class="material-icons">close</span></span>
														</h5>
														<div class="center-align" style="margin-top: 50px">
															<embed src="#" width="800px" height="2100px" />
														</div>
													</div>
												</div>
											</div>
	
										</div>
									</div>
								</li>
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
				Circulars File Upload<span class="right modal-action modal-close"><span
					class="material-icons">close</span></span>
			</h6>

			<div class="container">
			 <form action="<%=request.getContextPath() %>/upload-manual" id="manualUploadForm" name="manualUploadForm" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col m6 s12 input-field">
						<input id="manual_name" name="manual_name" type="text" class="validate"> <label
							for="manual_name">Title</label>
					</div>
					<div class="col m6 s12 input-field">
						<p class="searchable_label">Folder</p>
						<select class="searchable" name="manual_folder_fk" id="manual_folder_fk">
							<option disabled selected>Choose your option</option>
							<c:forEach var="obj" items="${foldersList }">
                                   <option value="${obj.Circulars_folder_fk }">${obj.Circulars_folder_fk}</option>
                            </c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col m12 s12 input-field file-field">
						<div class="btn bg-m t-t-i">
							<span>Upload File</span> <input type="file" id="manualFile" name="manualFile">
						</div>
						<div class="file-path-wrapper">
							<input class="file-path validate" type="text" id="attachment" name="attachment" >
						</div>
					</div>
				</div>
				<div class="row" style="margin-top: 15px;">
					<div class="col s12 m6">
						<div class="center-align m-1">
							<button type="button" onclick="manualFileSubmit();" style="width: 100%;"
								class="btn waves-effect waves-light bg-m">Upload</button>
						</div>
					</div>
					<div class="col s12 m6">
						<div class="center-align m-1">
								<a href="<%=request.getContextPath()%>/manuals"  class="btn waves-effect waves-light bg-s modal-close"
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
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<!--  <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="manual_folder_fk" id="manual_folder_fk" />
    </form> -->
	<script>
		$(document).ready(function() {
			$('.collapsible').collapsible();
			$('.searchable').select2();
			$('.modal').modal({
				dismissible : false,
			});
		
			
			  var searchData = {};
	            
	            <c:forEach var="mObj" items="${foldersList}" varStatus="index">
	            searchData['${mObj.Circulars_name}'] = null;
	            </c:forEach>
	            
	            $('input.autocomplete').autocomplete({
	                data: searchData,
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
		 
		  function manualFileSubmit(){
	        	$(".page-loader").show();
	        	$("#upload_template").modal();
	        	$("#manualUploadForm").submit();
	        }
		
	</script>
</body>
</html>