<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Training Category</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/training.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }
        p a {
            color: blue;
        }
		/* .mdl-data-table td.last-column {
		    text-align: left ;
		}

        .row.no-mar {
            margin-bottom: 0;
        }

        .modal-header {
            text-align: center;
            background-color: #282130;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .mdl-data-table thead tr,
        .mdl-data-table tfoot tr {
            background-color: #282130 !important;
        } */
        input[type=number]:not(.browser-default):focus:not([readonly]),
		input[type=text]:not(.browser-default):focus:not([readonly]),
		input[type=search]:not(.browser-default):focus:not([readonly]),
		textarea.materialize-textarea:focus:not([readonly])   {
		    border-bottom: 1px solid #282130 !important;
		    box-shadow: 0 1px 0 0 #282130 !important;
		}
		.input-field input[type=text]:not(.browser-default).validate+label::after,
		.input-field input[type=text]:not(.browser-default):focus:not([readonly])+label ,
		.input-field input[type=number]:not(.browser-default).validate+label::after,
		.input-field input[type=number]:not(.browser-default):focus:not([readonly])+label ,
		.input-field textarea.materialize-textarea:focus:not([readonly])+label       {
		    color: #282130  !important;
		}

        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input[type="search"] {
                width: 85% !important;
            }
        }
        
		.error-msg label{color:red!important;}
    </style>
</head>

<body>

    <!-- header  starts-->
<%--   <jsp:include page="../layout/header.jsp"></jsp:include> --%>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Training Category</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Training Category</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="training_category_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Training Category</th>
                                             <c:forEach var="tObj" items="${trainingCategoryDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${trainingCategoryDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="training_categoryId${indexs.count}" value="${obj.training_category }" class="findLengths"/>
												${obj.training_category }</td>
											<c:forEach var="tObj" items="${trainingCategoryDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${trainingCategoryDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.training_category eq obj.training_category }"> 
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
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${trainingCategoryDetails.dList}" varStatus="indexx"> 
												<c:choose>  
												    <c:when test="${oSbj.training_category eq obj.training_category }"> 
												      	<a onclick="deleteRow('${ oSbj.training_category }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		 <form action="<%=request.getContextPath() %>/add-training-category" id="trainingCategoryForm" name="trainingCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Training Category <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="training_category_text" name="training_category" type="text" class="validate" onkeyup="doValidate(this.value)">
                                <label for="training_category_text">Training Category</label>
                                <span id="training_categoryError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn" 
                                        class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/training-category"
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
		 <form action="<%=request.getContextPath() %>/update-training-category" id=updateTrainingCategoryForm  name="updateTrainingCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Training Category <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m12">
                               <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(this.value)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Training Category</label>
                                 <span id="value_newError" class="error-msg" ></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/training-category"
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
<%--   <jsp:include page="../layout/footer.jsp"></jsp:include> --%>

 	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="training_category" id="training_category" />
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

           /*  // adding table data into table start
            var arr = ['Contract Management', 'Finance', 'Fire', 'Induction', 'Leagal', 'Managerial', 'Primavera Training', 'Quality Control', 'Safety', 'Skill Development', 'Technical'];
            var table_text = '';
            $.each(arr, function (i, val) {
                table_text = table_text + ' <tr><td>' + val + '</td>' + '<td class="last-column"> <a href="#errorModal" class="btn waves-effect waves-light modal-trigger bg-m t-c">' +
                    '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
            });
            $('#training_category_table tbody').append(table_text);
            // adding table data into table ends */

            var table = $('#training_category_table').DataTable({
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
                paging: false,
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
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   while(count < validate){
     		   var findVal = ek[count];
     		   findVal = findVal.toLowerCase();
     		   if(findVal == value){
     			   $('#training_categoryError').text(print_value+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#training_categoryError').text('');
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
   
        $("#trainingCategoryForm").submit(function (e) {
           	 if(validator.form()){ 
       			$(".page-loader").show();
       			$("#addUpdateModal").modal();
       			if(flag){
    				document.getElementById("trainingCategoryForm").submit();	
    			 }
    			 $(".page-loader").hide();
    			 return false;
           	}
        })
        $("#updateTrainingCategoryForm").submit(function (e) {
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#onlyUpdateModal").modal();
      			if(updateFlag){
    				document.getElementById("updateTrainingCategoryForm").submit();	
    			 }
    			 $(".page-loader").hide();
    			 return false;
          	}
       })
        var validator =	$('#trainingCategoryForm').validate({
        	 rules: {
        		 "training_category": {
 			 		  required: true
        		 }
 			},messages: {
		 		   "training_category": {
			 		  required: 'Required'
			 	  }
	        },errorPlacement:function(error, element){
	        	 if(element.attr("id") == "training_category_text" ){
				     document.getElementById("training_categoryError").innerHTML="";
			 	     error.appendTo('#training_categoryError');
				 }
	        }
        	
        });
        
        var validator1 =  $('#updateTrainingCategoryForm').validate({
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
       	      var training_category = $('#training_categoryId'+no).val();
       	      $('#value_old').val($.trim(training_category))
       	      $('#onlyUpdateModal').modal('open');
       	      $('#onlyUpdateModal #value_new').val($.trim(training_category)).focus();
       	  }
       	  
       	  function deleteRow(val){
       	  	$("#training_category").val(val);
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
       		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-training-category');
       		    	    	$('#getForm').submit();
       		           }else {
       		                swal("Cancelled", "Record is safe :)", "error"); 
       		            }
       		        });
       		    }
       	</script>

       </body>
       </html>
