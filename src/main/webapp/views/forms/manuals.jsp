<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Manuals</title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/la.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<style>		
		.center-align.p-2.bg-m h5{
			font-size:20px;
		}
		.t-t-i {
			text-transform: inherit;
			font-size:1rem;
			font-weight:500;
		}
		.file-upload-btn{
			width: 100%;
		    border-radius: 40px;
		    height: 45px;
		    line-height: 45px;
		    font-size: 1.1rem;
		    font-weight: 400;
		}
		.modal-header {
		    text-align: center;
		    background-color: #2E58AD;
		    color: #fff;
		    margin: -24px -24px 20px !important;
		    padding: 1rem;
		}
		.preview-modal{
			max-height: 80%;
    		min-height: 60%;
		}
		.main-card>.card-content{
			min-height:85vh;
		}
	
	 /* change icon & bg based on the active state starts */
	  	li.active>.collapsible-header{
			background-color:#DAE8F9;
		}
		li.active>.collapsible-body{
			background-color:#ECF8FD;
		}
        .collapsible-header>.close {
            display: none;
        }

        .collapsible-header>.open {
            display: inline-block;
        }

        .active>.collapsible-header .open {
            display: none;
        }

        .active>.collapsible-header .close {
            display: inline-block;
        }
	/* change icon & bg based on the active state ends  */
		.input-field .searchable_label{
			font-size:0.85rem;
			margin-bottom:6px !important;
		}
		.select2-container.select2-container--default.select2-container--open{
			z-index:1034;
		}
		.select2-container--default .select2-selection--single {
			background-color: transparent;
		}
		.modal .input-field p{
			margin:0;
		}
		.modal .container .row{
			margin-bottom:0;
		}
		file-field .btn span{
   		 font-weight: 400;
		}
		.card-action.flex {
			display: flex;
			justify-content: space-between;
			padding: 8px 24px;
		}
		
		.card-action.flex a {
			margin-right: 0 !important;
			font-size: 2rem;			
			cursor:pointer;
			color:#2E58AD !important;
		}
		.card .card-title {
    		font-size: 1.2rem;
    	}
		
		.collapsible-body .row {
			margin-bottom: 0;
		}
		
		.collapsible-body .card-content img {
			width: 100px;
			height: 100px;
		}
		
		.collapsible-body .card-file {
			width: 230px;
		}
		
		.card-file .card-title, 
		.card-file .card-content .card-title,
		.collapsible-body .card-content .card-title {
			margin-bottom: 0;
		}
		
		.files-collection {
			display: flex;
			/* justify-content: space-between; */
			justify-content: flex-start;
			flex-wrap: wrap;
		}
		.files-collection .card-file{
			margin:6px;
		}
		
		.input-field .prefix.right-side {
			right: 10px;
		}
		
		.input-field .prefix.right-side ~label,
		.input-field .prefix.right-side	~input {
			margin-left: 0;
			width: 100%;
		}
		
		.input-field .prefix.right-side ~input {
			background-color: #fafafa;
			width: calc(100% - 25px);
			border-radius: 20px;
			padding-left: 25px;
		}
		
		@media only screen and (max-width: 600px) {
			.files-collection {
				justify-content: center;
			}
			.collapsible-body .card-file {
				margin-right: auto;
				margin-left: auto;
			}
			.input-field .prefix.right-side ~input {
				padding-left: 15px;
				width: calc(100% - 15px);
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
								<h5>Manuals</h5>
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

							<c:if test="${action eq 'Yes'}">	
							     <div class="col m2 s4 input-field">
								<a class="btn t-t-i bg-m modal-trigger file-upload-btn" href="#upload-modal">Upload</a>							
								</div>
							</c:if>
					</div>
					
						<div class="row">
						   <ul class="collapsible">
							<c:choose>   
	                          <c:when test="${foldersList.size() gt 0}">
		                        <c:forEach var="rows" items="${foldersList}" varStatus="index">     
								  <li class=" files-filter">
									<div class="collapsible-header " id="folder${index.count }">
										<i class="fa fa-folder open"></i> <i class="fa fa-folder-open close"></i> ${rows.manual_folder_fk }
									</div>
									<div class="collapsible-body">
										<div class="files-collection">
										<c:choose>   
										 <c:when test="${not empty rows.manualsList && fn:length(rows.manualsList) gt 0 }">
										  <c:forEach var="data" items="${rows.manualsList}" varStatus="indexx"> 
											<div class="card card-file"  id="row${indexx.count }${index.count }">
												<div class="card-content center-align ">
													<img src="/pmis/resources/images/document.svg"> 
													<span class="card-title">${data.manual_name }</span>
												</div>
												<div class="card-action flex">
													<a href="#modal1${indexx.count }${index.count }" class="modal-trigger"><i class="fa fa-eye"></i></a> 
													<a href="<%=CommonConstants.MANUAL_FILES %>${data.attachment }" download><i class="fa fa-download"></i></a>
												</div>
												 <div id="modal1${indexx.count }${index.count }" class="modal preview-modal">
												    <div class="modal-content">
												      <h5 class="modal-header">File Preview <span class="right modal-action modal-close"><span class="material-icons">close</span></span></h5>
												      <div class="center-align" style="margin-top:50px">
												      	<embed src="<%=CommonConstants.MANUAL_FILES %>${data.attachment }#toolbar=0" width="800px" height="2100px" />
												      </div>
												    </div>
												 </div>
											</div>
										  </c:forEach>
									  </c:when>
	                               	  <c:otherwise>
	                                	<span style="width: 100%;text-align: center">No files available in ${rows.manual_folder_fk }</span>
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
			<h5 class="modal-header">
				File Upload<span class="right modal-action modal-close"><span
					class="material-icons">close</span></span>
			</h5>

			<div class="container">
			 <form action="<%=request.getContextPath() %>/upload-manual" id="manualUploadForm" name="manualUploadForm" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col m6 s12 input-field">
						<input id="manual_name" name="manual_name" type="text" class="validate"> <label
							for="title">Title</label>
					</div>
					<div class="col m6 s12 input-field">
						<p class="searchable_label">Folder</p>
						<select class="searchable" name="manual_folder_fk" id="manual_folder_fk">
							<option disabled selected>Choose your option</option>
							<c:forEach var="obj" items="${foldersList }">
                                   <option value="${obj.manual_folder_fk }">${obj.manual_folder_fk}</option>
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
	 <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="manual_folder_fk" id="manual_folder_fk" />
    </form>
	<script>
		$(document).ready(function() {
			$('.collapsible').collapsible();
			$('.searchable').select2();
			$('.modal').modal({
				dismissible : false,
			});
			$('input.autocomplete').autocomplete({
				data : {
					"MUTP-I" : null,
					"MUTP-II" : null,
					"MUTP-IIA" : null,
					"MUTP-IIB" : null,
					"MUTP-III" : null,
				},
			});
			
			  var searchData = {};
	            
	            <c:forEach var="mObj" items="${foldersList}" varStatus="index">
	            searchData['${rows.manual_folder_fk}'] = null;
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
	        });
		 
		  function manualFileSubmit(){
	        	$(".page-loader").show();
	        	$("#upload_template").modal();
	        	$("#manualUploadForm").submit();
	        }
		 <%--  function folderList(manual_folder_fk){
			  $("#manual_folder_fk").val(manual_folder_fk);
	        	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-list');
	        	$('#getForm').submit();  
		  } --%>
	</script>
</body>
</html>