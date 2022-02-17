<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
 <%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Bank Guarantee Type</title>
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
                            <h6> Bank Guarantee Type </h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Bank Guarantee Type</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="bg_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Bank Guarantee Type</th>
                                           
                                            <c:forEach var="oobj" items="${bankGuaranteeDetails.tablesList}" >
                                            	 <th>${oobj.captiliszedTableName }</th>
                                            </c:forEach>
                                              
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${bankGuaranteeDetails.bankGaurenteeList1}" varStatus="indexs">
											<tr><td>
											 <input type="hidden" id="value${indexs.count}" value="${obj.bg_type }" class="findLengths"/>
											${obj.bg_type }</td>
											
											<c:forEach var="oaobj" items="${bankGuaranteeDetails.tablesList}" varStatus="index">
											 
												<td><c:forEach var="oobj" items="${bankGuaranteeDetails.countList}" >
												<c:choose> 
													    <c:when test="${oaobj.tName eq oobj.tName }">  
													    
													    		<c:choose>  
																    <c:when test="${oobj.bg_type_fk eq obj.bg_type }"> 
																      	 ( ${oobj.count } ) 
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
										 	<c:forEach var="osbj"  items="${bankGuaranteeDetails.bankGaurenteeList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${osbj.bg_type eq obj.bg_type }"> 
												      	<a onclick="deleteRow('${ osbj.bg_type }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
												      	  <%-- <input name="bg_type" value="${osbj.bg_type}"/> --%>
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
		 <form action="<%=request.getContextPath() %>/add-bank-guarantee-type" id="bankGuaranteeTypeForm" name="bankGuaranteeTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Add Bank Guarantee Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="bg_type_text" type="text" name="bg_type" class="validate" onkeyup="doValidate(this.value)">
                                <label for="bg_type_text">Bank Guarantee Type</label>
                                <span id="bg_typeError" class="error-msg" ></span>
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
                                        <a href="<%=request.getContextPath()%>/bank-guarantee-type"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
     <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-bank-guarantee-type" id=bankGuaranteeTypeForm1 name="bankGuaranteeTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Bank Guarantee Type <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="bg_type_text1" type="text" name="bg_type_new" class="validate" onkeyup="doValidateUpdate(this.value)">
                                <input id="bg_type_old_text" type="hidden" name="bg_type_old"  >
                                <label for="bg_type_text1">Bank Guarantee Type</label>
                                <span id="bg_type_text1Error" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s6 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/bank-guarantee-type"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
       <div id="errorModal" class="modal">
           <div class="modal-content">
               <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                           class="material-icons">close</span></span></h5>
               <div class="row center-align" style="margin-bottom: 0;">
                   <p style="color:red;">Reference data cannot be edited or deleted when in use by other Data sets</p>
               </div>
           </div>
    	</div>
    <!-- footer  -->
<%--   <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
 		<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="bg_type" id="bg_type" />
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
         $('.modal').modal( );
      
         var table = $('#bg_type_table').DataTable({
             columnDefs: [
                 {
                     targets: [0],
                     className: 'mdl-data-table__cell--non-numeric',
                     targets: 'no-sort', orderable: false,
                 },
                 { "width": "20px", "targets": [2] },
             ],
             "scrollCollapse": true,
             paging:false,
             fixedHeader: true,
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
  			   $('#bg_typeError').text(print_value+' alreday exists').css('color', 'red');
  			   $('#bttn').prop('disabled', true);
  			   flag = false;
  			   return false;
  		   }else{
  			   $('#bg_typeError').text('');
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
  	   var valueOld = $('#bg_type_old_text').val();
  	   var ek = $('.findLengths').map((_,el) => el.value).get();
  	   value = value.toLowerCase();
  	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
  	   delete ek[s];
  	   while(count < validate){
  		   var findVal = ek[count];
  		   if(findVal != null){ findVal = findVal.toLowerCase();}
  		   if(findVal == value){
  			   $('#bg_type_text1Error').text(print_value+' alreday exists').css('color', 'red');
  			   $('#bttnUpdate').prop('disabled', true);
  			   updateFlag = false;
  			   return false;
  		   }else{
  			   $('#bg_type_text1Error').text('');
  			   $('#bttnUpdate').prop('disabled', false);
  			   updateFlag = true;
  		   }
  		   
  		   count++;
  	   }
     }
     
     function removeErrorMsg(){
		 $('#bg_type_text1Error').text('');
		 $('#bttnUpdate').prop('disabled', false);
		 updateFlag = true;
		}
     
     $("#bankGuaranteeTypeForm").submit(function (e) {
       	 if(validator.form()){ 
   			$(".page-loader").show();
   			$("#addUpdateModal").modal();
   			if(flag){
				document.getElementById("bankGuaranteeTypeForm").submit();	
			 }
			 $(".page-loader").hide();
			 return false;
        }
     })
     
     $("#bankGuaranteeTypeForm1").submit(function (e) {
       	 if(validator1.form()){ 
   			$(".page-loader").show();
   			$("#onlyUpdateModal").modal();
   			if(updateFlag){
 				document.getElementById("bankGuaranteeTypeForm1").submit();	
 			 }
 			 $(".page-loader").hide();
 			 return false;
        }
     })
    
     var validator = $('#bankGuaranteeTypeForm').validate({
    	 rules: {
    		 	"bg_type": {
			 		  required: true 
    			},"bg_type_new": {
			 		  required: true 
    			}
			},messages: {
		 		 "bg_type": {
			 		  required: 'Required'
			 	 },"bg_type_new": {
			 		  required: 'Required'
			 	 }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "bg_type_text" ){
				     document.getElementById("bg_typeError").innerHTML="";
			 	     error.appendTo('#bg_typeError');
			   }else if(element.attr("id") == "bg_type_text1" ){
				     document.getElementById("bg_type_text1Error").innerHTML="";
			 	     error.appendTo('#bg_type_text1Error');
			   }
	        }
     });
     
     var validator1 = $('#bankGuaranteeTypeForm1').validate({
    	 rules: {
    		 	"bg_type_new": {
			 		  required: true 
    			}
			},messages: {
		 		"bg_type_new": {
			 		  required: 'Required'
			 	 }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "bg_type_text1" ){
				     document.getElementById("bg_type_text1Error").innerHTML="";
			 	     error.appendTo('#bg_type_text1Error');
			   }
	        }
     });
    
     $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	  });

     function updateRow(no) {
         var currentVal = $('#value'+no).val();
         $('#bg_type_old_text').val($.trim(currentVal)) 
         $('#onlyUpdateModal').modal('open');
         $('#onlyUpdateModal #bg_type_text1').val($.trim(currentVal)).focus();
     }
     
     function deleteRow(val){
     	$("#bg_type").val(val);
     	showCancelMessage();
	    }
     	
     
     function showCancelMessage() {
     	swal({
	            title: "Are you sure?",
	            text: "You will be changing the status of the record!",
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
	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-bank-guarantee-type');
	    	    	$('#getForm').submit();
	           }else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
     
    </script>

</body>

</html>
