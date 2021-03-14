<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update Form</c:if>
		 <c:if test="${action eq 'add'}"> Add Form</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }

        .fw-8p {
            width: 8%;
        }

        #form_form_table td>.btn {
            padding: 0 12px;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }

        input[type=url]:not(.browser-default):focus:not([readonly])+label {
            color: #2E58AD !important;
        }

        input[type=url]:not(.browser-default):focus:not([readonly]) {
            border-bottom: 1px solid #2E58AD;
            box-shadow: 0 1px 0 0 #2E58AD;
        }

        .select2-container--default .select2-selection--single {
            background-color: transparent;
        }
    </style>
</head>

<body>

    <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
									<c:if test="${action eq 'edit'}">Update Form</c:if>
		 							<c:if test="${action eq 'add'}"> Add Form</c:if>
								</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-form" id="accessForm" name="accessForm" method="post" class="form-horizontal" role="form" >
                          </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-form" id="accessForm" name="accessForm" method="post" class="form-horizontal" role="form" >
					  </c:if>
					  	<input type="hidden" name ="form_id" value="${formDetails.form_id }" />
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <input id="form_name" name="form_name" type="text" class="validate" value="${formDetails.form_name }">
                                    <label for="form_name">Form Name <span class="required">*</span></label>
                                    <span id="form_nameError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Module </p>
                                    <select class="searchable" id="module_name_fk" name="module_name_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${modulesList }">
                                      	    <option value= "${ obj.module_name_fk}" <c:if test="${formDetails.module_name_fk eq obj.module_name_fk}">selected</c:if>>${obj.module_name_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="moduleError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Folder </p>
                                    <select class="searchable" id="parent_form_id_sr_fk" name="parent_form_id_sr_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${foldersList }">
                                      	    <option value= "${ obj.form_id}" <c:if test="${formDetails.parent_form_id_sr_fk eq obj.form_id}">selected</c:if>>${obj.form_name}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="moduleError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="priority" name="priority" type="number" class="validate" value="${formDetails.priority }">
                                    <label for="priority">priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="web_form_url" name="web_form_url" type="text" class="validate" value="${formDetails.web_form_url }">
                                    <label for="web_form_url">Web Url </label>
                                    <span id="web_form_urlError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="mobile_form_url" name="mobile_form_url" type="text" class="validate" value="${formDetails.mobile_form_url }">
                                    <label for="mobile_form_url">Mobile Url </label>
                                    <span id="mobile_form_urlError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${statusList }">
                                      	    <option value= "${ obj.soft_delete_status_fk}" <c:if test="${formDetails.soft_delete_status_fk eq obj.soft_delete_status_fk}">selected</c:if>>${obj.soft_delete_status_fk}</option>
                                          </c:forEach>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row" style="margin-bottom: 20px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">Form Details</h5>
                                        <div class="table-inside">
                                            <table id="form_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>Access Type </th>
                                                        <th>Access Value </th>
                                                        <th class="fw-8p">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detailsBody">
                                                    <c:choose>
														<c:when	test="${not empty formDetails.accessPermissions && fn:length(formDetails.accessPermissions) gt 0 }">
															<c:forEach var="aObj" items="${formDetails.accessPermissions }"	varStatus="index">
																<tr id="actionRow${index.count }">
																	<td class="input-field">
			                                                            <select id="access_types${index.count }" name="access_types" onchange="getAccessValues(this.value,'${index.count }');" class="searchable">
			                                                                <option value="">Select</option>
			                                                                <option value="user_role" <c:if test="${aObj.access_type eq 'user_role' }">selected</c:if>>User Role</option>
			                                                                <option value="user_type" <c:if test="${aObj.access_type eq 'user_type' }">selected</c:if>>User Type</option>
			                                                                <option value="user" <c:if test="${aObj.access_type eq 'user' }">selected</c:if>>User</option>
			                                                            </select>
			                                                            <span id="access_type${index.count }Error" class="error-msg"></span>
			                                                        </td>
			                                                        <td class="input-field">
			                                                            <select id="access_values${index.count }" class="searchables" name="access_values">
			                                                                <option value="">Select</option>
			                                                                
			                                                                <c:if test="${aObj.access_type eq 'user_role' }">		                                                		  
						                                                        <c:forEach var="obj" items="${user_roles }">
						                                                        	<option value="${obj.access_value_id }" <c:if test="${aObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_id}</option>
						                                                        </c:forEach>
					                                                        </c:if>
					                                                        <c:if test="${aObj.access_type eq 'user_type' }">		                                                		  
						                                                        <c:forEach var="obj" items="${user_types }">
						                                                        	<option value="${obj.access_value_id }" <c:if test="${aObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_id }</option>
						                                                        </c:forEach>
					                                                        </c:if>  
					                                                        <c:if test="${aObj.access_type eq 'user' }">		                                                		  
						                                                        <c:forEach var="obj" items="${users }">
						                                                        	<option value="${obj.access_value_id }" <c:if test="${aObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_id }<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
						                                                        </c:forEach>
					                                                        </c:if> 
					                                                        
			                                                            </select>
			                                                            <span id="access_value${index.count }Error" class="error-msg"></span>
			                                                        </td>
																	<td>
																		<a onclick="removeActions('${index.count }');" style="font-size: 20px;"> 
																		<i class="fa fa-close"></i></a>
																	</td>
																</tr>															
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr id="actionRow0">
																<td class="input-field">
		                                                            <select id="access_types0" name="access_types" onchange="getAccessValues(this.value,'0');" class="searchable">
		                                                                <option value="">Select</option>
		                                                                <option value="user_role">User Role</option>
		                                                                <option value="user_type">User Type</option>
		                                                                <option value="user">User</option>
		                                                            </select>
		                                                            <span id="access_type0Error" class="error-msg"></span>
		                                                        </td>
		                                                        <td class="input-field">
		                                                            <select id="access_values0" class="searchable" name="access_values">
		                                                                <option value="">Select</option>
		                                                            </select>
		                                                            <span id="access_value0Error" class="error-msg"></span>
		                                                        </td>
																<td>
																	<a onclick="removeActions('0');" style="font-size: 20px;"><i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
													
                                                </tbody>
                                            </table>
                                            
                                            <table class="mdl-data-table">
												<tbody>
													<tr>
														<td colspan="6" style="text-align: right;"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c "
															onclick="addRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
											<c:choose>
												<c:when
													test="${not empty (formDetails.accessPermissions) && fn:length(formDetails.accessPermissions) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo"
														value="${fn:length(formDetails.accessPermissions) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <!-- </div> -->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateForm();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addForm();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/forms" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </form>
                    <!-- form ends  -->
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
	
    <!-- footer  -->
     <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });
        function updateForm(){
      	   if(validator.form()){ // validation perform
      	  		   $(".page-loader").show();	  
          		   document.getElementById("accessForm").submit();	
      	   }
          	   else{
              	   $(".page-loader").hide();
          	   }		
     	 	 
         }
         function addForm(){
      	   if(validator.form()){ // validation perform
  		  		   $(".page-loader").show();	  
  	    		   document.getElementById("accessForm").submit();			
      	   }else{
  	        	   $(".page-loader").hide();
  	    	   }
     	 	 
         }
         
         var validator =	$('#accessForm').validate({
 			
 			 ignore: ":hidden:not(.validate-dropdown)",
 	  		    rules: {
 	  		 		 "form_name": {
 	  			 		required: true
 	  			 	  },
 	  		 	},
 	  		    messages: {
 	  		 		  "form_name": {
 	  			 		required: 'Required'
 	  			 	  },
 		   		},
 		   		errorPlacement:function(error, element){
 		   		 	  if(element.attr("id") == "form_name" ){
 					     document.getElementById("form_nameError").innerHTML="";
 				 	     error.appendTo('#form_nameError');
 					 }else{
 	 					 error.insertAfter(element);
 			        } 
 		   		},invalidHandler: function (form, validator) {
                    var errors = validator.numberOfInvalids();
                    if (errors) {
                        var position = validator.errorList[0].element;
                        jQuery('html, body').animate({
                            scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                        }, 1000);
                    }
                },submitHandler:function(form){
 			    	form.submit();
 			    }
 			});   
       
 	       $('select').change(function(){
 	           if ($(this).val() != ""){
 	               $(this).valid();
 	           }
 	       });
 	
 	       $('input').change(function(){
 	           if ($(this).val() != ""){
 	               $(this).valid();
 	           }
 	       });
            
         function addRow() {        	
             var rowNo = $("#rowNo").val();
             var rNo = Number(rowNo)+1;
             var html = '<tr id="actionRow' + rNo + '">'
             + '<td class="input-field">'
             + '<select id="access_types' + rNo + '" class="searchable" name="access_types" onchange="getAccessValues(this.value,'+rNo+');"> ' 
             + '<option value="">Select</option>'
             + '<option value="user_role">User Role</option>'
             + '<option value="user_type">User Type</option>'
             + '<option value="user">User</option>'
             + '</select>'
             + '<span id="access_type' + rNo + 'Error" class="error-msg"></span>'
             + '</td>'
             + '<td class="input-field">'
             + '<select id="access_values' + rNo + '" class="searchable" name="access_values">'
             + '<option value="">Select</option>' 
             + '</select>' 
             + '<span id="access_value' + rNo + 'Error" class="error-msg"></span>' 
             + '</td>'
 			+ '<td><a onclick="removeActions(' + rNo + ');" style="font-size: 20px;"><i class="fa fa-close"></i></a></td></tr>';
 		
 			$('#detailsBody').append(html);
             $("#rowNo").val(rNo);
             
             $('select:not(.searchable)').formSelect();
             $('.searchable').select2();
         }
         
         function removeActions(rowNo){
         	$("#actionRow"+rowNo).remove();
         }
         
         function getAccessValues(access_type,indexNo){
         	$(".page-loader").show();
         	var url = "";
         	if(access_type == 'user_role'){
         		url = "<%=request.getContextPath()%>/ajax/getUserRolesInFormAccess";
         	}else if(access_type == 'user_type'){
         		url = "<%=request.getContextPath()%>/ajax/getUserTypesInFormAccess";
         	}else if(access_type == 'user'){
         		url = "<%=request.getContextPath()%>/ajax/getUsersInFormAccess";
         	}
         	
         	if($.trim(url) != ''){
         		$("#access_values"+indexNo+" option:not(:first)").remove();
                 $.ajax({
                     url: url,
                     cache: false,
                     success: function (data) {
                         if (data.length > 0) {
                         	if(access_type == 'user_role'){
                         		 $.each(data, function (i, val) {
                         			 var access_value_name = '';
                                      if ($.trim(val.access_value_name) != '') { access_value_name = ' - ' + $.trim(val.access_value_name) }
                                      $("#access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_id) + access_value_name+ '</option>');
                                  });
                         	}else if(access_type == 'user_type'){
                         		 $.each(data, function (i, val) {
                         			 var access_value_name = '';
                                      if ($.trim(val.access_value_name) != '') { access_value_name = ' - ' + $.trim(val.access_value_name) }
                                      $("#access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_id) + access_value_name + '</option>');
                                  });
                         	}else if(access_type == 'user'){
                         		 $.each(data, function (i, val) {
                         			 var access_value_name = '';
                                      if ($.trim(val.access_value_name) != '') { access_value_name = ' - ' + $.trim(val.access_value_name) }
                                      $("#access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_id) + access_value_name + '</option>');
                                  });
                         	}                           
                         }
                         $("#access_values"+indexNo).select2();
                         $(".page-loader").hide();
                     }
                 });
         	}
         }
    </script>

</body>

</html>