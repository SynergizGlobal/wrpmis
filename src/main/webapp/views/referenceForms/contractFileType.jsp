<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contract File Type</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contract.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
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
                            <h6> Contract File Type</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Contract File Type</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="contract_file_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract File Type</th>                                           
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                   <tbody>
										<c:forEach var="obj" items="${contractFileType}" varStatus="index">
											<tr>
											<td>
												<input type="hidden" id="contract_file_type${index.count}" value="${obj.contract_file_type }"  class="findLengths"/>
												${obj.contract_file_type }</td>
										<td class="last-column"><a href="#onlyUpdateModal" onclick="updateRow(${index.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger "> <i class="fa fa-pencil" ></i></a><a onclick="deleteRow('${ obj.contract_file_type }');" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>
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
		 <form action="<%=request.getContextPath() %>/add-contract-file-type" id="addContractFileTypeForm" name="addContractFileTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header ">Add Contract File Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="contract_file_type" name="contract_file_type" type="text" class="validate" onkeyup="doValidate(this.value)">
                                <label for="contract_file_type">Contract File Type</label>
                                 <span id="contract_file_typeError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn" 
                                        class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s6 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/contract-file-type"
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
		 <form action="<%=request.getContextPath() %>/update-contract-file-type" id=updateContractFileTypeForm name="id=updateContractFileTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Contract File Type <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                 <input id="id" type="hidden" name="id"  >
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                       <div class="row no-mar">
                         <div class="input-field col s12 m12">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(this.value)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Contract File Type</label>
                                <span id="value_newError" class="error-msg" ></span>
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
                                        <a href="<%=request.getContextPath()%>/contract-file-type"
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
 	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="contract_file_type" id="idNo" />
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

           /*  // adding table data into table start
            // adding table data into table ends */

            var table = $('#contract_file_type_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        /* className: "last-column", targets: [1], */
                    }, 
                    { "width": "20px", "targets": [1] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "sScrollX": "100%",
                paging: false,
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
        var flag = false; 
        function doValidate(value){
     	   var print_value = value;	
     	   var value = value.trim();
     	   value = value.toLowerCase();
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   while(count < validate){
     		   var findVal = ek[count];
     		   findVal = findVal.toLowerCase();
     		   if(findVal == value){
     			   $('#contract_file_typeError').text(print_value+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#contract_file_typeError').text('');
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
   
        $("#addContractFileTypeForm").submit(function (e) {
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			 if(flag){
        				document.getElementById("addContractFileTypeForm").submit();	
        			 }
        			 $(".page-loader").hide();
        			 return false;
            }
        })
        $("#updateContractFileTypeForm").submit(function (e) {
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			 if(updateFlag){
        				document.getElementById("updateContractFileTypeForm").submit();	
        			 }
        			 $(".page-loader").hide();
        			 return false;
            }
        })
        var validator =  $('#addContractFileTypeForm').validate({
        	 rules: {
        		 "contract_file_type": {
 			 		  required: true 
        		 }
 			},messages: {
 		 		   "contract_file_type": {
 			 		  required: 'Required'
 			 	  }
 	        },errorPlacement:function(error, element){
 	        	 if(element.attr("id") == "contract_file_type" ){
 				     document.getElementById("contract_file_typeError").innerHTML="";
 			 	     error.appendTo('#contract_file_typeError');
 				 }
 	        }
        	
        });
        
        var validator1 =  $('#updateContractFileTypeForm').validate({
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
       	      var contract_file_type = $('#contract_file_type'+no).val();
       	      $('#value_old').val($.trim(contract_file_type))
       	      $('#onlyUpdateModal').modal('open');
       	      $('#onlyUpdateModal #value_new').val($.trim(contract_file_type)).focus();
       	  }
       	  
       	  function deleteRow(val){
       	  	$("#idNo").val(val);
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
       		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-contract-file-type');
       		    	    	$('#getForm').submit();
       		           }else {
       		                swal("Cancelled", "Record is safe :)", "error");
       		            }
       		        });
       		    }
       	</script>

   </body>
</html>
