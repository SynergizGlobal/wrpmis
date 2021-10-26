<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Form</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
   <!--  <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
        p a,
        td a {
            color: blue;
            text-decoration: none;
        }

        .mdl-data-table td:last-child {
            white-space: inherit;
            text-align: center !important;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        th {
            text-transform: capitalize;
        }
		.mdl-data-table td > .btn~.btn{
			margin-left: .5rem;
		}
        .mdl-data-table tbody tr thead tr:hover {
            background-color: #007a7a;
        } 

        .mdl-data-table tbody tr .modal {
            min-height: 50vh;
        }

        svg {
            fill: #fff;
        }
        .my-error-class{
			color:red!important; 
		}
    </style>
</head>

<body>

     <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Template Upload Form</h6>
                        </div>
                    </span>
                    <div class="">
						<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
                        <div class="row">
                            <div class="col m12 s12">
                                <table id="upload-form-grid" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>template</th>
                                            <th>uploaded by </th>
                                            <th>uploaded on</th>
                                            <th>status</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tabletBody">
                                    <c:forEach var="obj" items="${templatesList}" varStatus="index">
                                        <tr  id="${index.count }">
                                        
                                            <td>${obj.template_name }</td>
                                            <td>${obj.user_name }</td>
                                            <td>${obj.uploaded_on }</td>
                                            <td>${obj.status }</td>
                                            
                                            <td class="last-column">
                                                <a href="#addUpdateModal" class="btn bg-m waves-effect waves-light modal-trigger" id="${index.count }" onclick="uploadFunction('${obj.template_name }');" title="Upload"><i
                                                        class="fa fa-upload"></i></a>
                                                <a  class="btn waves-effect waves-light " href="/pmis/${obj.template_name }.xlsx" download  title="Download"><i
                                                        class="fa fa-download"></i></a><input type="hidden" id="attachmentFile${index.count }"  value="${obj.attachment}" />
                                                <a href="#" class="btn waves-effect waves-light bg-s " title="Delete"  onclick="deleteTemplate('${obj.id }');" ><i
                                                        class="fa fa-trash"></i></a>
                                                <a href="#history${index.count }" class="btn bg-m waves-effect waves-light modal-trigger"  
                                                    title="History"><svg xmlns="http://www.w3.org/2000/svg"
                                                        xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                                                        width="24" height="24" viewBox="0 0 24 24">
                                                        <path
                                                            d="M4,2C2.9,2 2,2.9 2,4V20C2,21.1 2.9,22 4,22H12.41C12.41,25.87 12.13,23 16,23C19.87,23 23,19.87 23,16C23,12.13 21.87,9.3 18,9.3V8L12,2H4M4,4H11V9H16C12.13,9 9,12.13 9,16C9,19.87 6.39,20 10.26,20H4V4M16,11C18.76,11 21,13.24 21,16C21,18.76 18.76,21 16,21C13.24,21 11,18.76 11,16C11,13.24 13.24,11 16,11M15,12V17L18.61,19.16L19.36,17.94L16.5,16.25V12H15Z" />
                                                    </svg></a>
                                                <div id="history${index.count }" class="modal"> 
                                                    <div class="modal-content">
                                                        <h6 class="headbg modal-header">Template Upload History <span 
                                                                class="right modal-action modal-close"><span
                                                                    class="material-icons">close</span></span></h6>
                                                        <table class="responsive-table" id="history_table${index.count }">
                                                            <thead>
                                                                <tr>
                                                                    <th>template </th>
                                                                    <th>uploaded by</th>
                                                                    <th>uploaded on</th>
                                                                    <th>status</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody> 
                                                            <c:choose> 
	                                                            <c:when test = "${not empty obj.tableHistoryList}">
	                                                              <c:forEach var="hObj" items="${obj.tableHistoryList}" varStatus="indexx">
	                                                               <tr id="historyTableRow${index.count }${indexx.count }">
	                                                               		<td><a  id="attachment${index.count }${indexx.count }" href="/pmis/TEMPLATES_OLD/${hObj.attachment }" download title="Download">  ${hObj.template_name }</a></td>
	                                                               		<td>${hObj.user_name }</td>
	                                                               		<td>${hObj.uploaded_on }</td>
	                                                               		<td>${hObj.status }</td>
	                                                               </tr>
	                                                             </c:forEach> 
	                                                             </c:when>
	                                                             <c:otherwise>
														           <tr> <td colspan="4">No Rows to Display</td></tr>
														         </c:otherwise>
														         </c:choose>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   <div id="addUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/upload-template" id="templateForm" name="templateForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
            <div class="modal-content">
                <h5 class="headbg modal-header">Upload Template<span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                    <input type="hidden" id="template_name" name="template_name"  >
                        <div class="row">
                            <div class="input-field col s12 m12">
                            <div class="file-field input-field" >
                            <div class="btn bg-m t-c">
		                             <span>Attach File</span>
                                <input type="file" id="templateFile" name="templateFile"  class="validate" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">  <!-- onchange="selectFile('1')" -->
                               </div> 
                                <div class="file-path-wrapper">
		                            <input class="file-path validate" type="text">
		                        </div>   
                                <span id="template_nameError" class="error-msg" ></span>
                                <input type="hidden" name="commonAttachment" id="attachment1" />
                            </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                               <div class="center-align m-1">
										<button id="bttn" style="width: 100%;" onclick="uploadTemplate();" class="btn waves-effect waves-light bg-m">Upload</button>
								</div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/template-upload"
									class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>

            </div>

        </form>
    </div>
    <!-- footer  -->
 <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
    <!-- Modal Structure -->

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="id" />
    	
    </form>
    <script>
	$(document).keypress(function(e){
    if (e.which == 13){
        $("#save_post").click();
    }
	});
	</script>
    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal();
            
            $('#upload-form-grid').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [4] },
                ],
                "sScrollX": "100%",
                "ordering":false,
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
					.unbind(), self = this.api(), $searchButton = $(
					'<i class="fa fa-search" title="Go" id="save_post">')
					.click(function() {
						self.search(input.val()).draw();
					}), $clearButton = $(
							'<i class="fa fa-close" title="Reset">')
					.click(function() {
						input.val('');
						$searchButton.click();
					})
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton);
                }
            });
        });
    	var trid = null;
    	$('#tabletBody tr').click(function (event) {
    		trid = this.id; //trying to alert id of the clicked row          
    	  });
        function uploadTemplate() {
		    var attachmentFile = $('#attachmentFile'+trid).val()
        	if(validator.form()){ 
     			$(".page-loader").show();
     			$('#attachment1').val(attachmentFile)
     			$("#addUpdateModal").modal();
     			document.getElementById("templateForm").submit();	
          }
        }

        function uploadFunction(templateName){
        	$('#template_name').val(templateName)        	
        }
        
        function deleteTemplate(id){
        	$("#id").val(id);
    	  	showCancelMessage();
        }
        
        function showCancelMessage() {
	    	swal({
		            title: "Are you sure?",
		            text: "You will be able to change the status of record!",
		            type: "warning",
		            showCancelButton: true, 
		            confirmButtonColor: "#DD6B55",
		            confirmButtonText: "Yes, delete it!",
		            cancelButtonText: "No, cancel it!",
		            closeOnConfirm: false,
		            closeOnCancel: false
		        }, function (isConfirm) {
		            if (isConfirm) {
		               // swal("Deleted!", "Record has been deleted", "success");
		                $(".page-loader").show();
		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-template');
		    	    	$('#getForm').submit();
		           }else {
		                swal("Cancelled", "Record is safe :)", "error");
		            }
		        });
	  }
        var validator =	$('#templateForm').validate({
			 errorClass: "my-error-class",
			   validClass: "my-valid-class",
	  		    rules: {
	  		 		   "templateFile": {
	  			 		  required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		   "templateFile": {
	  			 		  required: 'Required'
	  			 	  }	
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "templateFile" ){
					     document.getElementById("template_nameError").innerHTML="";
				 	     error.appendTo('#template_nameError');
					 }else{
	 					 error.insertAfter(element);
			        } 
		   		},submitHandler:function(form){
			    	//form.submit();
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