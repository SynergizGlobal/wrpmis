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
    <title>User Access Type</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/users.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
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
            background-color: #508484;
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

        .mdl-data-table thead tr,
        .mdl-data-table tfoot tr {
            background-color: #508484 !important;
        } */
		input[type=number]:not(.browser-default):focus:not([readonly]),
		input[type=text]:not(.browser-default):focus:not([readonly]),
		input[type=search]:not(.browser-default):focus:not([readonly]),
		textarea.materialize-textarea:focus:not([readonly])   {
		    border-bottom: 1px solid #508484 !important;
		    box-shadow: 0 1px 0 0 #508484 !important;
		}
		.input-field input[type=text]:not(.browser-default).validate+label::after,
		.input-field input[type=text]:not(.browser-default):focus:not([readonly])+label ,
		.input-field input[type=number]:not(.browser-default).validate+label::after,
		.input-field input[type=number]:not(.browser-default):focus:not([readonly])+label ,
		.input-field textarea.materialize-textarea:focus:not([readonly])+label       {
		    color: #508484  !important;
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
<%-- <jsp:include page="../layout/header.jsp"></jsp:include> --%>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> User Access Type</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add User Access Type</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="user_access_typetable" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>User Access Type</th>
                                            <th>User Access Table</th>
                                            <c:forEach var="tObj" items="${userAccessTypeDetails.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${userAccessTypeDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="user_access_typeId${indexs.count}" value="${obj.user_access_type }" class="findLengths"/>
												${obj.user_access_type }</td>
											<td>
												<input type="hidden" id="user_access_tableId${indexs.count}" value="${obj.user_access_table }" class="findLengths1"/>
												${obj.user_access_table }</td>
											<c:forEach var="tObj" items="${userAccessTypeDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${userAccessTypeDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.user_access_type eq obj.user_access_type }"> 
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
										 	<c:forEach var="oSbj"  items="${userAccessTypeDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.user_access_type eq obj.user_access_type }"> 
												      	<a onclick="deleteRow('${ oSbj.user_access_type }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		<form action="<%=request.getContextPath() %>/add-user-access-type" id="userAccessTypeForm" name="userAccessTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add User Access Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="user_access_type" type="text" name="user_access_type" class="validate" onkeyup="doValidate(this.value,null)">
                                <label for="user_access_type">User Access Type</label>
                                 <span id="user_access_typeError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="user_access_table" type="text" name="user_access_table" class="validate" onkeyup="doValidate(null,this.value)">
                                <label for="user_access_table">User Access Table</label>
                                 <span id="user_access_tableError" class="error-msg" ></span>
                            </div>
                             <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                       		</div>
                        </div>
                        <div class="row">
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;"
                                     class="btn waves-effect waves-light bg-m" id="bttn">Add</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath()%>/user-access-type"
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
		 <form action="<%=request.getContextPath() %>/update-user-access-type" id="updateUserAccessTypeForm" name="updateUserAccessTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update User Access Type <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                         <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">User Access Type</label>
                                <span id="value_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="user_access_table_new" name="user_access_table_new" type="text" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <label for="user_access_table_new">User Access Table</label>
                                <input id="user_access_table_old" type="hidden" name="user_access_table_old"  >
                                <span id="user_access_table_newError" class="error-msg" ></span>
                            </div>
                             <div  style="text-align:center">
                        		 <span id="DivUpdateError" class="error-msg" ></span> 
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
                                        <a href="<%=request.getContextPath()%>/user-access-type"
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
    	<input type="hidden" name="user_access_type_val" id="user_access_type_val" />
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
      
            var table = $('#user_access_typetable').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [3] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });
        var flag = false; 
        function doValidate(value,value1){
           var value = $('#user_access_type').val();
           var value1 = $('#user_access_table').val();
           value = value.trim();
           value1 = value1.trim();
           var print_value = value;	
     	   var print_value2 = value1;
           var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
           var s = Object.keys(ek).find(key => ek[key] === value);
           if(value != null){ value = value.toLowerCase();}
           if(value1 != null){ value1 = value1.toLowerCase();}
         
     	  
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	  
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
     			
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' alreday exists').css('color', 'red');
   				   $('#user_access_typeError').text('');
   				   $('#user_role_code_text').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#user_access_typeError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
     				 if(findVal2 != value1 ){$('#user_access_tableError').text('');}else{ $('#user_access_tableError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else if(findVal2 == value1 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#user_access_tableError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#user_access_typeError').text('');}else{ $('#user_access_typeError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else{
        			   $('#DivError').text('');
        			   $('#user_access_typeError').text('');
        			   $('#user_access_tableError').text('');
        			   $('#bttn').prop('disabled', false); 
        			   flag = true;
     			  }
     		
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1){
           var value = $('#value_new').val();
           var value1 = $('#user_access_table_new').val();
           value = value.trim();
           value1 = value1.trim();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#value_old').val()
           var valueOld2 = $('#user_access_table_old').val()
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
     	   value = value.toLowerCase();
     	   value = value.toLowerCase();
     	   value1 = value1.toLowerCase();
     	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	   delete ek[s];
     	   delete ak[s1];
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			 if(findVal != null){ findVal = findVal.toLowerCase();}
  			 if(findVal2 != null){ findVal2 = findVal2.toLowerCase();}
   		     if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' alreday exists').css('color', 'red');
  				   $('#value_newError').text('');
 			   	   $('#user_access_table_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
      				 $('#bttnUpdate').prop('disabled', true);
      				 $('#DivUpdateError').text('');
      				 $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
      				 if(findVal2 != value1 ){$('#user_access_table_newError').text('');}else{ $('#user_access_table_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 
      				 updateFlag = false; 
      				 $('#bttnUpdate').prop('disabled', true);
      				 return false;
     			 }else if(findVal2 == value1 ){
     				 $('#DivUpdateError').text('');
     				 $('#user_access_table_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#value_newError').text('');}else{ $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 updateFlag = false;
     				 $('#bttnUpdate').prop('disabled', true);
     				 return false;
     			  }else{
       			       $('#DivUpdateError').text('');
       			       $('#department_newError').text('');
       			   	   $('#user_access_table_newError').text('');
        			   $('#bttnUpdate').prop('disabled', false);
        			   updateFlag = true;
        			   }
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#DivUpdateError').text('');
   		 $('#value_newError').text('');
   		 $('#user_access_table_newError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
   
       $("#userAccessTypeForm").submit(function (e) {
	       	 if(validator.form()){ 
	   			$(".page-loader").show();
	   			$("#addUpdateModal").modal();
	   			if(flag){
	  				document.getElementById("userAccessTypeForm").submit();	
	  			 }
	  			 $(".page-loader").hide();
	  			 return false;
	       	}
	    })
	    
       $("#updateUserAccessTypeForm").submit(function (e) {
	       	 if(validator1.form()){ 
	   			$(".page-loader").show();
	   			$("#onlyUpdateModal").modal();
	   			if(updateFlag){
	  				document.getElementById("updateUserAccessTypeForm").submit();	
	  			 }
	  			 $(".page-loader").hide();
	  			 return false;
	       	}
	    })
	    
	    var validator =	$('#userAccessTypeForm').validate({
	   	 rules: {
	   		 "user_access_type": {
				 		  required: true
			},"user_access_table":{
						  required: true
			}
	   	},messages: {
		 		   "user_access_type": {
			 		  required: 'Required'
			 	  }, "user_access_table": {
			 		  required: 'Required'
			 	  }
	    },errorPlacement:function(error, element){
	    	 if(element.attr("id") == "user_access_type" ){
				     document.getElementById("user_access_typeError").innerHTML="";
			 	     error.appendTo('#user_access_typeError');
			  }else if(element.attr("id") == "user_access_table" ){
				     document.getElementById("user_access_tableError").innerHTML="";
			 	     error.appendTo('#user_access_tableError');
			  }
	      }
		  });
	
	    var validator1 = $('#updateUserAccessTypeForm').validate({
        	 rules: {
        		 	"value_new": {
    			 		  required: true
        			 },"user_access_table_new":{
						  required: true
        			}
    			},messages: {
    		 		 "value_new": {
    			 		  required: 'Required'
    			 	 }, "user_access_table_new": {
  			 		  required: 'Required'
  			 	  }
    	        },errorPlacement:function(error, element){
    	        	 if(element.attr("id") == "value_new" ){
    				     document.getElementById("value_newError").innerHTML="";
    			 	     error.appendTo('#value_newError');
    			   }else if(element.attr("id") == "user_access_table_new" ){
				     document.getElementById("user_access_table_newError").innerHTML="";
			 	     error.appendTo('#user_access_table_newError');
			  }
    	        }
        });
      
      $('input').change(function(){
    	           if ($(this).val() != ""){
    	               $(this).valid();
    	           }
    	   });


      function updateRow(no) {
          var user_access_type = $('#user_access_typeId'+no).val();
          var user_access_table = $('#user_access_tableId'+no).val();
          $('#value_old').val($.trim(user_access_type))
          $('#user_access_table_old').val($.trim(user_access_table))
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #value_new').val($.trim(user_access_type)).focus();
          $('#onlyUpdateModal #user_access_table_new').val($.trim(user_access_table)).focus();
      }
      
      function deleteRow(val){
      	$("#user_access_type_val").val(val);
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
    	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-user-access-type');
    	    	    	$('#getForm').submit();
    	           }else {
    	                swal("Cancelled", "Record is safe :)", "error");
    	            }
    	        });
    	    }
    </script>

    </body>


 </html>