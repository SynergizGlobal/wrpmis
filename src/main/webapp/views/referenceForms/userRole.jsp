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
    <title>User Role</title>
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
                            <h6> User Role</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add User Role</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="user_role_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>User Role Name</th>
                                            <th>User Role Code</th>
                                            <c:forEach var="tObj" items="${userRoleDetails.tablesList}" >
                                            	 <c:forEach var="TObj" items="${tObj.tName }" >
                                            	 	<c:set var = "mTObj" value = "${fn:replace(TObj, '_', ' ')}" />
                                            	 	<th>${mTObj } </th>
                                            	</c:forEach>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${userRoleDetails.dList1}" varStatus="indexs">
											<tr><td>
												<input type="hidden" id="user_role_nameId${indexs.count}" value="${obj.user_role_name }" class="findLengths" />
												${obj.user_role_name }</td>
											<td>
												<input type="hidden" id="user_role_codeId${indexs.count}" value="${obj.user_role_code }" class="findLengths1"/>
												${obj.user_role_code }</td>
											<c:forEach var="tObj" items="${userRoleDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${userRoleDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.user_role_name eq obj.user_role_name }"> 
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
										 	<c:forEach var="oSbj"  items="${userRoleDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.user_role_name eq obj.user_role_name }"> 
												      	<a onclick="deleteRow('${ oSbj.user_role_name }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
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
		<form action="<%=request.getContextPath() %>/add-user-role" id="userRoleForm" name="userRoleForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add User Role <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="user_role_name_text" type="text" name="user_role_name" class="validate" onkeyup="doValidate(this.value,null)">
                                <label for="user_role_name_text">User Role Name</label>
                                <span id="user_role_nameError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="user_role_code_text" name="user_role_code" type="text" class="validate" onkeyup="doValidate(null,this.value)">
                                <label for="user_role_code_text">User Role Code</label>
                                <span id="user_role_codeError" class="error-msg" ></span>
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
                                    <a href="<%=request.getContextPath()%>/user-role"
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
		 <form action="<%=request.getContextPath() %>/update-user-role" id="updateUserRoleForm" name="updateUserRoleForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update User Role <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                         <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">User Role Name</label>
                                <span id="value_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="user_role_code_new" name="user_role_code_new" type="text" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <label for="user_role_code_new">User Role Code</label>
                                <input id="user_role_code_old" type="hidden" name="user_role_code_old"  >
                                <span id="user_role_code_newError" class="error-msg" ></span>
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
                                        <a href="<%=request.getContextPath()%>/user-role"
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
    	<input type="hidden" name="user_role_name" id="user_role_name" />
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
          /*   // adding table data into table start
           
            // adding table data into table ends */

            var table = $('#user_role_table').DataTable({
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
        function doValidate(value,value1){
           var value = $('#user_role_name_text').val();
           var value1 = $('#user_role_code_text').val();
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
   				   $('#user_role_nameError').text('');
   				   $('#user_role_codeError').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#user_role_nameError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
     				 if(findVal2 != value1 ){$('#user_role_codeError').text('');}else{ $('#user_role_codeError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else if(findVal2 == value1 ){
     				 $('#bttn').prop('disabled', true);
     				 $('#DivError').text('');
     				 $('#user_role_codeError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#user_role_nameError').text('');}else{ $('#user_role_nameError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 flag = false;
      			     return false;
     			  }else{
        			   $('#DivError').text('');
        			   $('#user_role_nameError').text('');
        			   $('#user_role_codeError').text('');
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
           var value1 = $('#user_role_code_new').val();
           value = value.trim();
           value1 = value1.trim();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#value_old').val()
           var valueOld2 = $('#user_role_code_old').val()
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
 			   	   $('#user_role_code_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			  if(findVal == value ){
      				 $('#bttnUpdate').prop('disabled', true);
      				 $('#DivUpdateError').text('');
      				 $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');
      				 if(findVal2 != value1 ){$('#user_role_code_newError').text('');}else{ $('#user_role_code_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');}
     				 
      				 updateFlag = false; 
      				 $('#bttnUpdate').prop('disabled', true);
      				 return false;
     			 }else if(findVal2 == value1 ){
     				 $('#DivUpdateError').text('');
     				 $('#user_role_code_newError').text('" '+print_value2+' "'+' alreday exists').css('color', 'red');
     				 if(findVal != value ){$('#value_newError').text('');}else{ $('#value_newError').text('" '+print_value+' "'+' alreday exists').css('color', 'red');}
     				 updateFlag = false;
     				 $('#bttnUpdate').prop('disabled', true);
     				 return false;
     			  }else{
       			       $('#DivUpdateError').text('');
       			       $('#department_newError').text('');
       			   	   $('#user_role_code_newError').text('');
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
   		 $('#user_role_code_newError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
   
        $("#userRoleForm").submit(function (e) {
	       	 if(validator.form()){ 
	   			$(".page-loader").show();
	   			$("#addUpdateModal").modal();
	   			if(flag){
	  				document.getElementById("userRoleForm").submit();	
	  			 }
	  			 $(".page-loader").hide();
	  			 return false;
	       	}
	    })
        
        $("#updateUserRoleForm").submit(function (e) {
	       	 if(validator1.form()){ 
	   			$(".page-loader").show();
	   			$("#onlyUpdateModal").modal();
	   			if(updateFlag){
	  				document.getElementById("updateUserRoleForm").submit();	
	  			 }
	  			 $(".page-loader").hide();
	  			 return false;
	       	}
	    })
	    
	    var validator =	$('#userRoleForm').validate({
	   	 rules: {
	   		 "user_role_name": {
				 		  required: true
			},"user_role_code":{
						  required: true
			}
	   	},messages: {
		 		   "user_role_name": {
			 		  required: 'Required'
			 	  }, "user_role_code": {
			 		  required: 'Required'
			 	  }
	    },errorPlacement:function(error, element){
	    	 if(element.attr("id") == "user_role_name_text" ){
				     document.getElementById("user_role_nameError").innerHTML="";
			 	     error.appendTo('#user_role_nameError');
			  }else if(element.attr("id") == "user_role_code_text" ){
				     document.getElementById("user_role_codeError").innerHTML="";
			 	     error.appendTo('#user_role_codeError');
			  }
	      }
		  });
	    
	    var validator1 = $('#updateUserRoleForm').validate({
         	 rules: {
         		 	"value_new": {
     			 		  required: true
         			 },"user_role_code_new":{
						  required: true
         			}
     			},messages: {
     		 		 "value_new": {
     			 		  required: 'Required'
     			 	 }, "user_role_code_new": {
   			 		  required: 'Required'
   			 	  }
     	        },errorPlacement:function(error, element){
     	        	 if(element.attr("id") == "value_new" ){
     				     document.getElementById("value_newError").innerHTML="";
     			 	     error.appendTo('#value_newError');
     			   }else if(element.attr("id") == "user_role_code_new" ){
				     document.getElementById("user_role_code_newError").innerHTML="";
			 	     error.appendTo('#user_role_code_newError');
			  }
     	        }
         });
       
       $('input').change(function(){
     	           if ($(this).val() != ""){
     	               $(this).valid();
     	           }
     	   });


       function updateRow(no) {
           var user_role_name = $('#user_role_nameId'+no).val();
           var user_role_code = $('#user_role_codeId'+no).val();
           $('#value_old').val($.trim(user_role_name))
           $('#user_role_code_old').val($.trim(user_role_code))
           $('#onlyUpdateModal').modal('open');
           $('#onlyUpdateModal #value_new').val($.trim(user_role_name)).focus();
           $('#onlyUpdateModal #user_role_code_new').val($.trim(user_role_code)).focus();
       }
       
       function deleteRow(val){
       	$("#user_role_name").val(val);
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
     	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-user-role');
     	    	    	$('#getForm').submit();
     	           }else {
     	                swal("Cancelled", "Record is safe :)", "error");
     	            }
     	        });
     	    }
     </script>

     </body>


  </html>