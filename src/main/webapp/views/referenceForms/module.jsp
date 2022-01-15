<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Module</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <%--<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">--%>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
<%--    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
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
                            <h6> Module</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Module</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="module_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Module</th>
                                            <th>Incharge</th>
                                            <th>Status</th>
                                            <c:forEach var="tObj" items="${moduleDetails.tablesList}" >
                                            	<c:forEach var="TObj" items="${tObj.tName }" >
                                            	 	<c:set var = "mTObj" value = "${fn:replace(TObj, '_', ' ')}" />
                                            	 	<th>${mTObj } </th>
                                            	</c:forEach>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${moduleDetails.dList1}" varStatus="indexs">
											<tr>
												<td>
												<input type="hidden" id="module_nameId${indexs.count}" value="${obj.module_name }"  class="findLengths"/>
												${obj.module_name }
												</td>
												<td>
												<input type="hidden" id="incharge_user_id_fk${indexs.count}" value="${obj.incharge_user_id_fk }"/>
												${obj.module_incharge }
												</td>
												<td>
												<input type="hidden" id="soft_delete_status_fk${indexs.count}" value="${obj.soft_delete_status_fk }"/>
												${obj.soft_delete_status_fk }
												</td>
												<c:forEach var="tObj" items="${moduleDetails.tablesList}" varStatus="index">
												 
													<td>
														<c:forEach var="cObj" items="${moduleDetails.countList}" >
														<c:choose> 
														    <c:when test="${tObj.tName eq cObj.tName }"> 
														    	<c:choose>  
																    <c:when test="${cObj.module_name eq obj.module_name }"> 
																      	 ( ${cObj.count } )   
																    </c:when>  
																    <c:otherwise>  
																    </c:otherwise>   
																</c:choose>
															</c:when>
															<c:otherwise> 
														   </c:otherwise>
														</c:choose>
														</c:forEach>
													</td>
	                                            </c:forEach>
												<td class="last-column ">
												<c:if test="${obj.module_name ne 'Others' }">
												<a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger"> <i class="fa fa-pencil" ></i></a>
											 	<c:forEach var="oSbj"  items="${moduleDetails.dList}" varStatus="indexx"> 
													<c:choose>  
													    <c:when test="${oSbj.module_name eq obj.module_name}"> 
													      	<a onclick="deleteRow('${ oSbj.module_name }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
													      	  <%-- <input name="bg_type" value="${oSbj.bg_type}"/> --%>
													      	</a>
													    </c:when>  
													    <c:otherwise>  
													    </c:otherwise>   
													</c:choose>  
													
	 											 </c:forEach>
	 											 </c:if>
	 											</td>
	 										</tr>													   
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
		<form action="<%=request.getContextPath() %>/add-module" id="addModuleForm" name="addModuleForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Module <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                            <div class="input-field col s12 ">
                                <input id="module_text" name="module_name" type="text" class="validate"  onkeyup="doValidate(this.value)">
                                <label for="module_text">Module</label>
                                <span id="module_nameError" class="error-msg" ></span>
                            </div> 
                        	<div class="input-field col s12">
                        		<p class="searchable_label">Incharge</p>
                                <select id="incharge_user_id_fk" name="incharge_user_id_fk" class="searchable">
                                	<option value="">--Select--</option>
                                	<c:forEach var="obj" items="${inchargeList}" >
                                		<option value="${obj.user_id }">${obj.designation } - ${obj.user_name }</option>
                                	</c:forEach>
                                </select>
                                <span id="incharge_user_id_fkError" class="error-msg" ></span>
                        	</div>  
                        	<div class="input-field col s12">
                        		<p class="searchable_label">Status</p>
                                <select id="soft_delete_status_fk" name="soft_delete_status_fk">
                                	<c:forEach var="obj" items="${statusList}" >
                                		<option value="${obj.soft_delete_status_fk }">${obj.soft_delete_status_fk }</option>
                                	</c:forEach>
                                </select>
                                <span id="soft_delete_status_fkError" class="error-msg" ></span>
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
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/module"
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
		 <form action="<%=request.getContextPath() %>/update-module" id=updateModuleForm name="updateModuleForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Module <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                       <div class="row no-mar">
                         <div class="input-field col s12 m12">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(this.value)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Module</label>
                                <span id="value_newError" class="error-msg" ></span>
                         </div>
                        </div>
                        <div class="row no-mar">
                        	<div class="input-field col s12 m12">
                        		<p class="searchable_label">Incharge</p>
                                <select id="incharge_user_id_fk_update" name="incharge_user_id_fk" class="">
                                	<option value="">--Select--</option>
                                	<c:forEach var="obj" items="${inchargeList}" >
                                		<option value="${obj.user_id }">${obj.designation } - ${obj.user_name }</option>
                                	</c:forEach>
                                </select>
                                <span id="incharge_user_id_fk_updateError" class="error-msg" ></span>
                            </div>
                        </div>   
                        <div class="input-field col s12">
                        		<p class="searchable_label">Status</p>
                                <select id="soft_delete_status_fk_update" name="soft_delete_status_fk">
                                	<c:forEach var="obj" items="${statusList}" >
                                		<option value="${obj.soft_delete_status_fk }">${obj.soft_delete_status_fk }</option>
                                	</c:forEach>
                                </select>
                                <span id="soft_delete_status_fkError" class="error-msg" ></span>
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
                                        <a href="<%=request.getContextPath()%>/module"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
     <!-- <div id="errorModal" class="modal">
           <div class="modal-content">
               <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                           class="material-icons">close</span></span></h5>
               <div class="row center-align" style="margin-bottom: 0;">
                   <p style="color:red;">Reference data cannot be edited or deleted when in use by other Data sets</p>
               </div>
           </div>
    	</div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="module_name" id="module_name" />
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
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });

           /*  // adding table data into table start
            var arr = ['Contract', 'Design', 'Execution Monitoring', 'Finance', 'Issues', 'Land Aquisition', 'R & R', 'Risk', 'Safety', 'Training', 'Unmanned Aerial Vehicle', 'Utility Shifting', 'Work'];
            var table_text = '';
            $.each(arr, function (i, val) {
            	table_text = table_text + ' <tr><td>' + val + '</td>' + '<td class="last-column"> <a href="#errorModal" class="btn waves-effect waves-light bg-m t-c modal-trigger">' +
                '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
            });
            $('#module_table tbody').append(table_text);
            // adding table data into table ends */

            var table = $('#module_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        /* className: "last-column", targets: [1], */
                    },
                    { "width": "20px", "targets": 'last-column' },
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
     			   $('#module_nameError').text(print_value+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#module_nameError').text('');
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
    	
      $("#addModuleForm").submit(function (e) {
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			 if(flag){
       				document.getElementById("addModuleForm").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
             }
         })
      $("#updateModuleForm").submit(function (e) {
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			 if(updateFlag){
       				document.getElementById("updateModuleForm").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
            }
        })
         var validator =  $('#addModuleForm').validate({
         	 rules: {
         		 "module_name": {
   			 		  required: true
         		 }
   			},messages: {
   		 		   "module_name": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "module_text" ){
   				     document.getElementById("module_nameError").innerHTML="";
   			 	     error.appendTo('#module_nameError');
   				 }
   	        }
         	
         });
         
         var validator1 =  $('#updateModuleForm').validate({
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
    	      var module_name = $('#module_nameId'+no).val();
    	      var incharge_user_id_fk = $('#incharge_user_id_fk'+no).val();
    	      var soft_delete_status_fk = $('#soft_delete_status_fk'+no).val();
    	      if($.trim(soft_delete_status_fk) == ''){
    	    	  soft_delete_status_fk = 'Inactive';
    	      }
    	      $('#value_old').val($.trim(module_name))
    	      $('#onlyUpdateModal').modal('open');
    	      $('#onlyUpdateModal #value_new').val($.trim(module_name)).focus();
    	      $('#onlyUpdateModal #incharge_user_id_fk_update').val(incharge_user_id_fk);
    	      $('#onlyUpdateModal #soft_delete_status_fk_update').val(soft_delete_status_fk);
    	      $('select:not(.searchable)').formSelect();
    	  }
    	  
    	  function deleteRow(val){
    	  	$("#module_name").val(val);
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
    		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-module');
    		    	    	$('#getForm').submit();
    		           }else {
    		                swal("Cancelled", "Record is safe :)", "error");
    		            }
    		        });
    		    }
    	  </script>

   </body>
</html>
