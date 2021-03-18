<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Department</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        p a {
            color: blue;
        }

        .row.no-mar {
            margin-bottom: 0;
        }

        .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .last-column {
            word-break: break-all;
            white-space: inherit;
        }
        .mdl-data-table td.last-column {
		    text-align: left;
		}

        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input[type="search"] {
                width: 85% !important;
            }
        }
         .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		.error-msg label{color:red!important;}
    </style>
</head>

<body>

    <!-- header  starts-->
<%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
    <!-- header ends  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Department</h6>
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
                        <div class="row">
                            <div class="col m4 hide-on-small"></div>
                            <div class="col m4 s12 center-align">
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Department</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="department_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Department</th>
                                            <th>Department Name</th>
                                            <th>Department code</th>
                                            <c:forEach var="tObj" items="${departmentDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${departmentDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="departmentId${indexs.count}" value="${obj.department }" />
												${obj.department }
											</td>
											<td>
												<input type="hidden" id="departmentName${indexs.count}" value="${obj.department_name }" />
												${obj.department_name }
											</td>
											<td>
												<input type="hidden" id="departmentCode${indexs.count}" value="${obj.contract_id_code }" />
												${obj.contract_id_code }
											</td>
											<c:forEach var="tObj" items="${departmentDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${departmentDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.department eq obj.department }"> 
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
										 	<c:forEach var="oSbj"  items="${departmentDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.department eq obj.department }"> 
												      	<a onclick="deleteRow('${ oSbj.department }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		<form action="<%=request.getContextPath() %>/add-department" id="addDepartmentForm" name="addDepartmentForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Department <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="department_id" type="text" name="department" class="validate">
                                <label for="department_id">Department id</label>
                                <span id="departmentError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="department_name" type="text" name="department_name" class="validate">
                                <label for="department_name">Department Name</label>
                                <span id="department_nameError" class="error-msg" ></span>
                            </div>
                            </div><div class="row">
                            <div class="input-field col s12 m6">
                                <input id="department_code" type="text" name="contract_id_code" class="validate">
                                <label for="department_code">Department code</label>
                                <span id="contract_id_codeError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addDepartment();" class="btn waves-effect waves-light bg-m">Add</button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/department"
									  class="btn waves-effect waves-light bg-s modal-action modal-close " style="width: 100%">Cancel</a>
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
		 <form action="<%=request.getContextPath() %>/update-department" id=updateDepartmentForm name="updateDepartmentForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Department <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="department_new" type="text" name="department_new" class="validate">
                                <input id="department_old" type="hidden" name="department_old"  >
                                <label for="department_id">Department id</label>
                                <span id="department_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="department_name_new" type="text" name="department_name_new" class="validate">
                                <label for="department_name_new">Department Name</label>
                                <input id="department_name_old" type="hidden" name="department_name_old"  >
                                <span id=department_name_newError class="error-msg" ></span>
                            </div>
                            </div>
                            <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="department_code_new" type="text" name="department_code_new" class="validate">
                                <input id="department_code_old" type="hidden" name="department_code_old"  >
                                <label for="contract_id_code_new">Department code</label>
                                <span id="department_code_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateDepartment()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/department"
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
    
   <!--  <div id="errorModal" class="modal">
        <div class="modal-content">
            <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h5>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="department" id="department" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
          
            var table = $('#department_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [9] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });

     function addDepartment(){
       	 if(validator.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			document.getElementById("addDepartmentForm").submit();	
          }
      }
      
     function updateDepartment(){
       	 if(validator1.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			document.getElementById("updateDepartmentForm").submit();	
          }
      }
      
     
      var validator =  $('#addDepartmentForm').validate({
      	 rules: {
      		 "department": {
			 		  required: true
      		 },"department_name": {
			 		  required: true
      		 },"contract_id_code": {
			 		  required: true
      		 }
			},messages: {
		 		   "department": {
			 		  required: 'Required'
			 	  },"department_name": {
			 		  required: 'Required'
			 	  },"contract_id_code": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "department_id" ){
				     document.getElementById("departmentError").innerHTML="";
			 	     error.appendTo('#departmentError');
				 }else if(element.attr("id") == "department_name" ){
				     document.getElementById("department_nameError").innerHTML="";
			 	     error.appendTo('#department_nameError');
				 }else if(element.attr("id") == "department_code" ){
				     document.getElementById("contract_id_codeError").innerHTML="";
			 	     error.appendTo('#contract_id_codeError');
				 }
	        }
      	
      });
      
      var validator1 =  $('#updateDepartmentForm').validate({
       	 rules: {
       		 "department_new": {
 			 		  required: true
       		 },"department_name_new": {
 			 		  required: true
       		 },"department_code_new": {
 			 		  required: true
       		 }
 			},messages: {
 		 		   "department_new": {
 			 		  required: 'Required'
 			 	  },"department_name_new": {
 			 		  required: 'Required'
 			 	  },"department_code_new": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "department_new" ){
 				     document.getElementById("department_newError").innerHTML="";
 			 	     error.appendTo('#department_newError');
 				 }else if(element.attr("id") == "department_name_new" ){
 				     document.getElementById("department_name_newError").innerHTML="";
 			 	     error.appendTo('#department_name_newError');
 				 }else if(element.attr("id") == "department_code_new" ){
 				     document.getElementById("department_code_newError").innerHTML="";
 			 	     error.appendTo('#department_code_newError');
 				 }
 	        }
       	
       });
      
      $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	     });
      
      function updateRow(no) {
          var departmentName = $('#departmentName'+no).val();
          var department = $('#departmentId'+no).val();
          var departmentCode = $('#departmentCode'+no).val();
          $('#department_name_old').val($.trim(departmentName))
          $('#department_old').val($.trim(department))
          $('#department_code_old').val($.trim(departmentCode))
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #department_new').val($.trim(department)).focus();
          $('#onlyUpdateModal #department_name_new').val($.trim(departmentName)).focus();
          $('#onlyUpdateModal #department_code_new').val($.trim(departmentCode)).focus();
      }
      
      function deleteRow(val){
      	$("#department").val(val);
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
 	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-department');
 	    	    	$('#getForm').submit();
 	           }else {
 	                swal("Cancelled", "Record is safe :)", "error");
 	            }
 	        });
 	    }
      
    </script>

</body>

</html>