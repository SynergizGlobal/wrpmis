<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contractor Specialization</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/contractor.css">
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
		.mdl-data-table td.last-column {
		    text-align: left ;
		}
        .modal-header {
            text-align: center;
            background-color: #05A4CC;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }
        .mdl-data-table thead tr, 
        .mdl-data-table tfoot tr {
		    background-color: #05A4CC !important;
		}

        input[type=number]:not(.browser-default):focus:not([readonly]),
        input[type=text]:not(.browser-default):focus:not([readonly]),
        input[type=search]:not(.browser-default):focus:not([readonly]),
        textarea.materialize-textarea:focus:not([readonly]) {
            border-bottom: 1px solid #05A4CC !important;
            box-shadow: 0 1px 0 0 #05A4CC !important;
        }

        .input-field input[type=text]:not(.browser-default).validate+label::after,
        .input-field input[type=text]:not(.browser-default):focus:not([readonly])+label,
        .input-field input[type=number]:not(.browser-default).validate+label::after,
        .input-field input[type=number]:not(.browser-default):focus:not([readonly])+label,
        .input-field textarea.materialize-textarea:focus:not([readonly])+label {
            color: #05A4CC !important;
        }

        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input {
                width: 85%;
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
                            <h5> Contractor Specialization</h5>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Contractor Specialization</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="contractor_specialization_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contractor Specialization</th>
                                            <c:forEach var="tObj" items="${contractorSpecializationDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="obj" items="${contractorSpecializationDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="contractor_specializationId${indexs.count}" value="${obj.contractor_specialization }" />
												${obj.contractor_specialization }
											</td>
											<c:forEach var="tObj" items="${contractorSpecializationDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${contractorSpecializationDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.contractor_specialization eq obj.contractor_specialization }"> 
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
										 	<c:forEach var="oSbj"  items="${contractorSpecializationDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.contractor_specialization eq obj.contractor_specialization }"> 
												      	<a onclick="deleteRow('${ oSbj.contractor_specialization }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		<form action="<%=request.getContextPath() %>/add-contractor-specialization" id="addContractorSpecializationForm" name="addContractorSpecializationForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h4 class="modal-header">Add Contractor Specialization <span
                        class="right modal-action modal-close"><span class="material-icons">close</span></span></h4>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="contractor_specialization_text" name="contractor_specialization" type="text" class="validate">
                                <label for="contractor_specialization_text">Contractor Specialization</label>
                                <span id="contractor_specializationError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addContractorSpecialization()" class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/contractor-specialization" class="btn waves-effect waves-light bg-s modal-action modal-close"
                                        style="width:100%">Cancel</a>
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
		 <form action="<%=request.getContextPath() %>/update-contractor-specialization" id=updateContractorSpecializationForm name="updateContractorSpecializationForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Contractor Specialization <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                         <div class="input-field col s12 m12">
                                <input id="contractor_specialization_new" type="text" name="contractor_specialization_new" class="validate">
                                <input id="contractor_specialization_old" type="hidden" name="contractor_specialization_old"  >
                                <label for="contractor_specialization_new">Contractor Specialization</label>
                                <span id="contractor_specialization_newError" class="error-msg" ></span>
                         </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateContractorSpecialization()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/contractor-specialization"
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
   <!--  
    <div id="errorModal" class="modal">
        <div class="modal-content">
            <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h5>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color: red;">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="contractor_specialization" id="contractor_specialization" />
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
         
            var table = $('#contractor_specialization_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        /* className: "last-column", targets: [1], */
                    },
                    { "width": "20px", "targets": [1] }, 
                ],
                "paging": false,
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

      function addContractorSpecialization(){
       	 if(validator.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			document.getElementById("addContractorSpecializationForm").submit();	
          }
      }
      function updateContractorSpecialization(){
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#addUpdateModal").modal();
    			document.getElementById("updateContractorSpecializationForm").submit();	
           } 
       }
      var validator1 =  $('#updateContractorSpecializationForm').validate({
      	 rules: {
      			 "contractor_specialization_new": {
			 		  required: true
      		 }
			},messages: {
		 		   "contractor_specialization_new": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "contractor_specialization_new" ){
				     document.getElementById("contractor_specialization_newError").innerHTML="";
			 	     error.appendTo('#contractor_specialization_newError');
				 }
	        }
      	
      });
      var validator =  $('#addContractorSpecializationForm').validate({
       	 rules: {
       			 "contractor_specialization": {
 			 		  required: true
       		 }
 			},messages: {
 		 		   "contractor_specialization": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "contractor_specialization_text" ){
 				     document.getElementById("contractor_specializationError").innerHTML="";
 			 	     error.appendTo('#contractor_specializationError');
 				 }
 	        }
       	
       });
      
      $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	     });
      
      function updateRow(no) {
          var contractor_specialization = $('#contractor_specializationId'+no).val();
          $('#contractor_specialization_old').val($.trim(contractor_specialization))
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #contractor_specialization_new').val($.trim(contractor_specialization)).focus();
      }
      
      function deleteRow(val){
      	$("#contractor_specialization").val(val);
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
 	            	$(".page-loader").show();
 	               // swal("Deleted!", "Record has been deleted", "success");
 	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-contractor-specialization');
 	    	    	$('#getForm').submit();
 	           }else {
 	                swal("Cancelled", "Record is safe :)", "error");
 	            }
 	        });
 	    }
    </script>

</body>

</html>