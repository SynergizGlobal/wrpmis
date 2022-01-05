<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deliverable Type</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <%--<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">--%>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
    <%--<link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
   <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css">--%>
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Deliverable Type</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Deliverable Type</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="deliverable_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Deliverable Type</th>
                                            <c:forEach var="tObj" items="${deliverableTypeDetails.tablesList}" >
                                            	<c:forEach var="TObj" items="${tObj.tName }" >
                                            	 	<c:set var = "mTObj" value = "${fn:replace(TObj, '_', ' ')}" />
                                            	 	<th>${mTObj } </th>
                                            	</c:forEach>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${deliverableTypeDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="deliverable_typeId${indexs.count}" value="${obj.deliverable_type }"  class="findLengths" />
												${obj.deliverable_type }</td>
											<c:forEach var="tObj" items="${deliverableTypeDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="cObj" items="${deliverableTypeDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.deliverable_type eq obj.deliverable_type }"> 
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
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c "> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${deliverableTypeDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.deliverable_type eq obj.deliverable_type }"> 
												      	<a onclick="deleteRow('${ oSbj.deliverable_type }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		<form action="<%=request.getContextPath() %>/add-deliverable-type" id="addDeliverableTypeForm" name="addDeliverableTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Deliverable Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="deliverable_type_text" type="text" name="deliverable_type" class="validate" onkeyup="doValidate(this.value)">
                                <label for="deliverable_type_text">Deliverable Type</label>
                                <span id="deliverable_typeError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn"
                                        class="btn waves-effect waves-light bg-m">Add</button>
                                </div>
                            </div>
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/deliverable-type"
									  class="btn waves-effect waves-light bg-s modal-action modal-close black-text" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
    
    <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-deliverable-type" id=updateDeliverableTypeForm name="updateDeliverableTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Deliverable Type <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                         <div class="input-field col s12 m12">
                                <input id="deliverable_type_new" type="text" name="deliverable_type_new" class="validate" onkeyup="doValidateUpdate(this.value)">
                                <input id="deliverable_type_old" type="hidden" name="deliverable_type_old"  >
                                <label for="deliverable_type_new">Deliverable Type</label>
                                <span id="deliverable_type_newError" class="error-msg" ></span>
                         </div>
                        </div>
                        <div class="row">
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;"  id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s6 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/deliverable-type"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
        <!--  <div id="errorModal" class="modal">
           <div class="modal-content">
               <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                           class="material-icons">close</span></span></h5>
               <div class="row center-align" style="margin-bottom: 0;">
                   <p style="color:red;">Reference data cannot be edited or deleted when in use by other Data sets</p>
               </div>
           </div>
    	</div> -->
    <!-- footer  -->
<%--  <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="deliverable_type" id="deliverable_type" />
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

            var table = $('#deliverable_type_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        className: "last-column", targets: [1],
                    },
                    { "width": "20px", "targets": [1] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging:false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                fixedColumns:   {
                    right: 1
                },
                "bScrollCollapse": true,
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
        var flag = false; 
        function doValidate(value){
     	   var print_value = value;	
     	   var value = value.trim();
     	   value = value.toLowerCase();
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   while(count < validate){
     		   var findVal = ek[count];
     		   findVal = findVal.toLowerCase();
     		   if(findVal == value){
     			   $('#deliverable_typeError').text(print_value+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#deliverable_typeError').text('');
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
     	   var valueOld = $('#deliverable_type_old').val();
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   value = value.toLowerCase();
     	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   delete ek[s];
     	   while(count < validate){
     		   var findVal = ek[count];
     		   if(findVal != null){ findVal = findVal.toLowerCase();}
     		   if(findVal == value){
     			   $('#deliverable_type_newError').text(print_value+' alreday exists').css('color', 'red');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			   $('#deliverable_type_newError').text('');
     			   $('#bttnUpdate').prop('disabled', false);
     			   updateFlag = true;
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#deliverable_type_newError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
     
         $("#addDeliverableTypeForm").submit(function (e) {
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			document.getElementById("addDeliverableTypeForm").submit();	
      			 if(flag){
      				document.getElementById("addDeliverableTypeForm").submit();	
      			 }
      			 $(".page-loader").hide();
      			 return false;
             }
         })
         
         $("#updateDeliverableTypeForm").submit(function (e) {
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			 if(updateFlag){
       				document.getElementById("updateDeliverableTypeForm").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
            }
        })
        
         var validator =  $('#addDeliverableTypeForm').validate({
         	 rules: {
         		 "deliverable_type": {
   			 		  required: true
         		 }
   			},messages: {
   		 		   "deliverable_type": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "deliverable_type_text" ){
   				     document.getElementById("deliverable_typeError").innerHTML="";
   			 	     error.appendTo('#deliverable_typeError');
   				 }
   	        }
         	
         });
         
         var validator1 =  $('#updateDeliverableTypeForm').validate({
         	 rules: {
         		 "deliverable_type_new": {
   			 		  required: true
         		 }
   			},messages: {
   		 		   "deliverable_type_new": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "deliverable_type_new" ){
   				     document.getElementById("deliverable_type_newError").innerHTML="";
   			 	     error.appendTo('#deliverable_type_newError');
   				 }
   	        }
         	
         });
         
         $('input').change(function(){
   	           if ($(this).val() != ""){
   	               $(this).valid();
   	           }
   	     });
         function updateRow(no) {
             var deliverable_type = $('#deliverable_typeId'+no).val();
             $('#deliverable_type_old').val($.trim(deliverable_type))
             $('#onlyUpdateModal').modal('open');
             $('#onlyUpdateModal #deliverable_type_new').val($.trim(deliverable_type)).focus();
         }
         
         function deleteRow(val){
         	$("#deliverable_type").val(val);
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
       	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-deliverable-type');
       	    	    	$('#getForm').submit();
       	           }else {
       	                swal("Cancelled", "Record is safe :)", "error");
       	            }
       	        });
       	    }
       </script>

       </body>

       </html>