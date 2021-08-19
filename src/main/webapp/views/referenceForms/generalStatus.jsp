<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>General Status</title>
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

        .last-column .btn+.btn {
            margin-left: 20px;
        }
		.mdl-data-table td.last-column {
		    text-align: left ;
		}
        .last-column {
            word-break: break-all;
            white-space: inherit;
        }

        .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
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
                            <h6> General Status</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add General Status</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="general_status_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>General Status</th>
                                            <th>Contract Status</th>
                                            <c:forEach var="tObj" items="${generalStatusDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${generalStatusDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="general_statusId${indexs.count}" value="${obj.general_status }" class="findLengths"/>
												${obj.general_status }</td>
												<td>
												<input type="hidden" id="contract_statusId${indexs.count}" value="${obj.contract_status }" class="findLengths1"/>
												${obj.contract_status }</td>
											<c:forEach var="tObj" items="${generalStatusDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${generalStatusDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.general_status eq obj.general_status }"> 
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
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c " > <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${generalStatusDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.general_status eq obj.general_status }"> 
												      	<a onclick="deleteRow('${ oSbj.general_status }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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

    <!-- Modal Training -->
    <div id="addUpdateModal" class="modal">
		<form action="<%=request.getContextPath() %>/add-general-status" id="addGeneralStatusForm"  name="addGeneralStatusForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add General Status <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s6 m6">
                                <input id="general_status_text" type="text" name="general_status" class="validate" onkeyup="doValidate(this.value)">
                                <label for="general_status_text">General Status</label>
                                <span id="general_statusError" class="error-msg" ></span>
                            </div>
                             <div class="input-field col s6 m6">
                                <input id="contract_status_text" type="text" name="contract_status" class="validate" >
                                <label for="contract_status_text">Contract Status</label>
                                <span id="contract_statusError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" 
                                        class="btn waves-effect waves-light bg-m" id="bttn" >Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/general-status" class="btn waves-effect waves-light bg-s modal-action modal-close"
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
		 <form action="<%=request.getContextPath() %>/update-general-status" id=updateGeneralStatusForm name="updateGeneralStatusForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update General Status <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                         <div class="input-field col s6 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(this.value)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">General Status</label>
                                <span id="value_newError" class="error-msg" ></span>
                         </div>
                         <div class="input-field col s6 m6">
                                <input id="contract_status_new" type="text" name="contract_status_new" class="validate" >
                                <label for="contract_status_new">Contract Status</label>
                                <span id="contract_status_newError" class="error-msg" ></span>
                         </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;"
                                        class="btn waves-effect waves-light bg-m" id="bttnUpdate">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/general-status"
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
    	<input type="hidden" name="general_status" id="general_status" />
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

            var table = $('#general_status_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'nosort', orderable: false,
                        className: "last-column", targets: [6],
                    },
                    { "width": "20px", "targets": [6] },
                ],
                "scrollCollapse": true,
               // paging: false,
                fixedHeader: true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });
       var flag = false; 
       function doValidate(value){
    	   var print_value = value;	
    	   var value = value.trim();
    	   value = value.toLowerCase();
    	   if(validate == 0){flag = true;}
    	   var validate = $('.findLengths').length;
    	   var count  = 0;
    	   var ek = $('.findLengths').map((_,el) => el.value).get();
    	   while(count < validate){
    		   var findVal = ek[count];
    		   if(findVal != null){ findVal = findVal.toLowerCase(); }
    		  
    		   if(findVal == value){
    			   $('#general_statusError').text(print_value+' alreday exists').css('color', 'red');
    			   $('#bttn').prop('disabled', true);
    			   flag = false;
    			   return false;
    		   }else{
    			   $('#general_statusError').text('');
    			   $('#bttn').prop('disabled', false); 
    			   flag = true;
    		   }
    		   
    		   count++;
    	   }
       }
       var updateFlag = true;
       function doValidateUpdate(value){
    	   var print_value = value;	
    	   var value = value.trim();
    	   var validate = $('.findLengths').length;
    	   var count  = 0;
    	   var valueOld = $('#value_old').val();
    	   var ek = $('.findLengths').map((_,el) => el.value).get();
    	   value = value.toLowerCase();
    	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
    	   delete ek[s];
    	   while(count < validate){
    		   var findVal = ek[count];
    		   if(findVal != null){ findVal = findVal.toLowerCase();}
    		   if(findVal == value){
    			   $('#value_newError').text(print_value+' alreday exists').css('color', 'red');
    			   $('#bttnUpdate').prop('disabled', true);
    			   updateFlag = false;
    			   return false;
    		   }else{
    			   $('#value_newError').text('');
    			   $('#bttnUpdate').prop('disabled', false);
    			   updateFlag = true;
    		   }
    		   
    		   count++; 
    	   }
       }
       
       function removeErrorMsg(){
  		 $('#value_newError').text('');
  		 $('#bttnUpdate').prop('disabled', false);
  		 updateFlag = true;
  		}
       
       $("#addGeneralStatusForm").submit(function (e) {
    	   if(validator.form()){ 
    			$(".page-loader").show();
    			 if(flag){
    				document.getElementById("addGeneralStatusForm").submit();	
    			 }
    			 $(".page-loader").hide();
    			 return false;
           }
         })
        $("#updateGeneralStatusForm").submit(function (e) {
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			 if(updateFlag){
     				document.getElementById("updateGeneralStatusForm").submit();	
     			 }
     			 $(".page-loader").hide();
     			 return false;
            }
        })
         
         var validator =  $('#addGeneralStatusForm').validate({
         	 rules: {
         		 "general_status": {
   			 		  required: true
         		 },
		         "contract_status": {
			 		  required: true
			 }
   			},messages: {
   		 		   "general_status": {
   			 		  required: 'Required'
   			 	  }, "contract_status": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "general_status_text" ){
   				     document.getElementById("general_statusError").innerHTML="";
   			 	     error.appendTo('#general_statusError');
   				 }else if(element.attr("id") == "contract_status_text" ){
 				     document.getElementById("contract_statusError").innerHTML="";
 			 	     error.appendTo('#contract_statusError');
 				 }
   	        }
         	
         });
         var validator1 =  $('#updateGeneralStatusForm').validate({
         	 rules: {
         		 "value_new": {
   			 		  required: true
         		 },
		         "contract_status_new": {
				 		  required: true
				 }
   			},messages: {
   		 		   "value_new": {
   			 		  required: 'Required'
   			 	  }, "contract_status_new": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "value_new" ){
   				     document.getElementById("value_newError").innerHTML="";
   			 	     error.appendTo('#value_newError');
   				 }else if(element.attr("id") == "contract_status_new" ){
 				     document.getElementById("contract_status_newError").innerHTML="";
 			 	     error.appendTo('#contract_status_newError');
 				 }
   	        }
         	
         });
         
         $('input').change(function(){
   	           if ($(this).val() != ""){
   	               $(this).valid();
   	           }
   	     });


         function updateRow(no) {
     	      var general_status = $('#general_statusId'+no).val();
     	      var contract_status = $('#contract_statusId'+no).val();
     	      $('#value_old').val($.trim(general_status))
     	      $('#onlyUpdateModal').modal('open');
     	      $('#onlyUpdateModal #value_new').val($.trim(general_status)).focus();
     	      $('#onlyUpdateModal #contract_status_new').val($.trim(contract_status)).focus();
     	  }
     	   
     	  function deleteRow(val){
     	  	$("#general_status").val(val);
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
     		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-general-status');
     		    	    	$('#getForm').submit();
     		           }else {
     		                swal("Cancelled", "Record is safe :)", "error");
     		            }
     		        });
     		    }
     	</script>

     	</body>

