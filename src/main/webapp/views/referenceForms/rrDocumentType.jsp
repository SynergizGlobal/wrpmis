<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RR Document Type</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/randr.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css">
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>  Document Type</h6>
                        </div>
                    </span>
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
                    <div class="">
                        <div class="row no-mar">
                            <div class="col s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add  Document Type</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="rr_document_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Document Type</th>
                                             <c:forEach var="tObj" items="${rrDocumentTypeDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${rrDocumentTypeDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="document_typeId${indexs.count}" value="${obj.document_type }" />
												${obj.document_type }</td>
											<c:forEach var="tObj" items="${rrDocumentTypeDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${rrDocumentTypeDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.document_type eq obj.document_type }"> 
																      	 ( ${cObj.count } )   
																    </c:when>  
																    <c:otherwise>  
																    </c:otherwise>   
															</c:choose>
														</c:when>
														<c:otherwise> 
													   </c:otherwise>
												</c:choose>
												</c:forEach></td>
                                            </c:forEach>
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger " href="#"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${rrDocumentTypeDetails.dList}" varStatus="indexx"> 
												<c:choose>  
												    <c:when test="${oSbj.document_type eq obj.document_type }"> 
												      	<a onclick="deleteRow('${ oSbj.document_type }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
												      	  <%-- <input name="bg_type" value="${oSbj.bg_type}"/> --%>
												      	</a>
												    </c:when>  
												    <c:otherwise>  
												    </c:otherwise>   
												</c:choose>  
 											 </c:forEach>
 											</td></tr>													   
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


    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/add-rr-document-type" id="addRRDocumentTypeForm" name="addRRDocumentTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add  Document Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="rr_document_type_text" name="document_type" type="text" class="validate">
                                <label for="rr_document_type_text"> Document Type</label>
                                <span id="document_typeError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addRRDocumentType()"
                                        class="btn waves-effect waves-light bg-m">Add</button>
                                </div>
                            </div>
                           
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/rr-document-type"
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
    <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-rr-document-type" id=updateRRDocumentTypeForm name="id=updateRRDocumentTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update  Document Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                               <input id="value_new" type="text" name="value_new" class="validate">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new"> Document Type</label>
                                 <span id="value_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateRRDocumentType()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/rr-document-type"
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
    <!-- <div id="errorModal" class="modal">
        <div class="modal-content">
            <h6 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h6>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
 	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="document_type" id="document_type" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
<script src="/pmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });

         /*    // adding table data into table start
            var arr = [''];
            var table_text = '';
            $.each(arr, function (i, val) {
                table_text = table_text + ' <tr><td>' + val + '</td>' + '<td class="last-column"> <a href="#errorModal" class="btn waves-effect waves-light modal-trigger bg-m t-c">' +
                    '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
            });
            $('#rr_document_type_table tbody').append(table_text);
            // adding table data into table ends */

            var table = $('#rr_document_type_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        /* className: "last-column", targets: [1], */
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                fixedColumns:   {
                    right: 1
                },
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input');
                    self = this.api();
                    $clearButton = $(	'<i class="fa fa-close" title="Reset">')
                        .click(function() {		input.val(''); self.search(input.val()).draw(); 	});
                    $('.dataTables_filter > label').append(	$clearButton); 
                }
            });
        });
    function addRRDocumentType(){
       	 if(validator.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			document.getElementById("addRRDocumentTypeForm").submit();	
        }
    }
    
    function updateRRDocumentType(){
      	 if(validator1.form()){ 
  			$(".page-loader").show();
  			$("#addUpdateModal").modal();
  			document.getElementById("updateRRDocumentTypeForm").submit();	
       }
   }
    
    var validator =	$('#addRRDocumentTypeForm').validate({
    	 rules: {
    		 "document_type": {
			 		  required: true
    		 }
			},messages: {
		 		   "document_type": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "rr_document_type_text" ){
				     document.getElementById("document_typeError").innerHTML="";
			 	     error.appendTo('#document_typeError');
				 }
	        }
    	
    });
    
    var validator1 =  $('#updateRRDocumentTypeForm').validate({
   	 rules: {
   		 "value_new": {
			 		  required: true
   		 }
			},messages: {
		 		   "value_new": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "value_new" ){
				     document.getElementById("value_newError").innerHTML="";
			 	     error.appendTo('#value_newError');
				 }
	        }
   	
   });
    
    $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	     });

    function updateRow(no) {
	      var document_type = $('#document_typeId'+no).val();
	      $('#value_old').val($.trim(document_type))
	      $('#onlyUpdateModal').modal('open');
	      $('#onlyUpdateModal #value_new').val($.trim(document_type)).focus();
	  }
	  
	  function deleteRow(val){
	  	$("#document_type").val(val);
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
		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-rr-document-type');
		    	    	$('#getForm').submit();
		           }else {
		                swal("Cancelled", "Record is safe :)", "error"); 
		            }
		        });
		    }
	</script>

</body>
</html>
