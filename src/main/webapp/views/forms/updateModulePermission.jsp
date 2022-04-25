<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 Update Module Permission
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }
		h6{
			font-weight:600;
			margin-bottom:1rem;
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

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }

        .fw-8p {
            width: 8%;
        }
   
        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }

        .select2-container--default .select2-selection--single {
            background-color: transparent;
        }
        .input-field p.searchable_label {
		    margin-bottom: 10px; 
		}
        .w20em{width: 20em;}

		@media only screen and (max-width: 768px){
			.input-field p.searchable_label {
			    margin-top: -18px !important;
			    margin-bottom: 0; 
			}
			.select2-container .select2-search--inline .select2-search__field{
				width:-webkit-fill-available !important;
			}
		}
		
		td.input-field >input[type="text"]{
			min-height:67px;
		}
		div.pt-1r{
			padding-top:1rem !important;
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6><span>${modulePermissions.module_name}</span> Module Permission</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update-module-permissions" id="modulePermissionForm" name="modulePermissionForm" method="post" class="form-horizontal" role="form" >
                    	<div class="container">                            
                            <div class="row">                          
                                <div class="col s12 m4 input-field offset-m4">
                                    <p class="searchable_label"> Module Name </p>
                                    <select id="module_name" class="searchable" name="module_name" onchange="getModuleUrlsList(this.value);">
                                        <c:forEach var="obj" items="${modulesList }">
                                        	<option value="${obj.module_name }" <c:if test="${obj.module_name eq modulePermissions.module_name }">selected</c:if> >${obj.module_name }</option>   
                                        </c:forEach>    
                                    </select>
                                    <span id="module_nameError" class="error-msg" ></span>
                                </div>
                                <%-- <div class="col s12 m3 input-field pt-1r">
                                     <p>
								      <label>
								        <input type="checkbox" class="filled-in" name="soft_delete_status_fk" id="soft_delete_status_fk" <c:if test="${modulePermissions.soft_delete_status_fk eq 'Active'}">checked</c:if>/>
								        <span>Activate this Module ?</span>
								      </label>
								    </p>	
                                </div>    --%>                              
                            </div>
                            <div class="row">	                                
                                <div class="col s12 m12 l12 input-field">
	                                <p class="priokind pad-top" style="text-align: center;"> <span style="margin-right:.5rem;font-weight:600;">Form Type? <span class="required">*</span></span>
                                       	<span class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                            <c:forEach var="obj" items="${modulePermissions.urlTypes }">
	                                            <label style="padding-right: 10px;">
	                                                <input class="with-gap" name="url_type" type="radio" value="${obj.url_type }" 
	                                                <c:if test="${obj.url_type eq param.url_type}">checked</c:if> 
	                                                onclick="getUrlsList('${obj.url_type }');">
	                                                <span style="padding-left: 23px;">${obj.url_type }</span>
	                                            </label>
                                            </c:forEach>
                                        </span>
                                    </p>	
                                </div>
	                        </div>
						 </div>
                            <div class="row">                                 
                                <div class="col s12" id="tableDiv" style="display:none;">
                                    <div class="row fixed-width">
                                        <h6 class="center-align" >Form Details</h6>
                                        <div class="table-inside">
                                            <table id="module_permission_table" class="mdl-data-table" >
                                                <thead>
                                                    <tr>
                                                    	<th>Form Name</th>
                                                    	<th>Form Url</th>
                                                        <th class="w20em">User Role</th>
                                                        <th class="w20em">User Type</th>
                                                        <th class="fw-8p w20em">User</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach var="mObj" items="${modulePermissions.accessPermissions }" varStatus="index">
	                                                	<tr>	
	                                                		<input type="hidden" id="form_ids${index.count }" name="form_ids" value="${mObj.form_id }" />
		                                                	<td class="input-field">
		                                                		 <%-- <input id="form_names${index.count }" name="form_names" type="text" class="validate" placeholder="Form Name" value="${mObj.form_name }" > --%>
		                                                		 ${mObj.form_name }
		                                                	</td>
		                                                	<td class="input-field">
		                                                		<%-- <input id="form_urls${index.count }" name="form_urls" type="text" class="validate" placeholder="Form Url" value="${mObj.form_url }"> --%>
		                                                		${mObj.form_url }
		                                                	</td>
															<td class="input-field">
																<input type="hidden" id="access_user_roles${index.count }" name="access_user_roles" />
	                                                            <select id="user_roles${index.count }" name="user_roles" class="searchable" multiple data-placeholder="User Role" onchange="setAccessUserRoles('${index.count }')">
	                                                                <option value="">Select</option>
	                                                                <c:forEach var="obj" items="${user_roles }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
	                                                        <td class="input-field">
	                                                        	<input type="hidden" id="access_user_types${index.count }" name="access_user_types" />
	                                                            <select id="user_types${index.count }" name="user_types" class="searchable" multiple data-placeholder="User Type" onchange="setAccessUserTypes('${index.count }')">
	                                                                <option value="">Select</option>
			                                                        <c:forEach var="obj" items="${user_types }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
															<td class="input-field">
																<input type="hidden" id="access_users${index.count }" name="access_users" />
	                                                            <select id="users${index.count }" name="users" class="searchable" multiple data-placeholder="User"  onchange="setAccessUsers('${index.count }')">
	                                                                <option value="" >Select</option>
			                                                        <c:forEach var="obj" items="${users }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
	                                                        <td><a href="javascript:updateUrlPermissions('${index.count }');" class="btn waves-effect waves-light bg-m t-c mob-btn">Update </a> </td>
														</tr>
														<script type="text/javascript">
														 	var user_role_access = '${mObj.user_role_access}';
												            var user_type_access = '${mObj.user_type_access}';
												            var user_access = '${mObj.user_access}';
												            if($.trim(user_role_access) != ''){
												            	var user_role_accessArr = user_role_access.split(',');
												            	$("#user_roles${index.count }").val(user_role_accessArr);
												            	$("#access_user_roles${index.count }").val(user_role_access.toString().split(",").join("~$~"));
												            }
												            if($.trim(user_type_access) != ''){
												            	var user_type_accessArr = user_type_access.split(',');
												            	$("#user_types${index.count }").val(user_type_accessArr);
												            	$("#access_user_types${index.count }").val(user_type_access.toString().split(",").join("~$~"));
												            }
												            if($.trim(user_access) != ''){
												            	var user_accessArr = user_access.split(',');
												            	$("#users${index.count }").val(user_accessArr);
												            	$("#access_users${index.count }").val(user_access.toString().split(",").join("~$~"));
												            }
														</script>
													</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                 
                            </div>
                            
	                        <div class="container" id="buttons" style="display: none;">   
	                           <div class="row">
	                                <div class="col s6 m6 l6 mt-brdr center-align">
	                                    <div class=" m-1">
		                                    <button type="button" onclick="updateForm();" class="btn waves-effect waves-light bg-m">Update All</button>
		                                </div>
	                                </div>
	                                <div class="col s6 m6 l6 mt-brdr center-align">
	                                    <div class=" m-1">
	                                        <a href="<%=request.getContextPath()%>/module-permissions" class="btn waves-effect waves-light bg-s">Cancel</a>
	                                    </div>
	                                </div>
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
    
    <form action="<%=request.getContextPath()%>/get-module-permission" id="getUrlsForm" name="getUrlsForm" method="post">
    	<input type="hidden" id="url_type" name="url_type" />
    	<input type="hidden" id="module_name_fk" name="module_name_fk" value="${modulePermissions.module_name }" />
    </form>
    <form action="<%=request.getContextPath()%>/get-module-permission" id="getModuleUrlsForm" name="getModuleUrlsForm" method="post">
    	<input type="hidden" id="module_name_fk_temp" name="module_name_fk" />
    </form>
    
    <form action="<%=request.getContextPath()%>/ajax//update-url-permissions" id="getUrlpermissionsForm" name="getUrlpermissionsForm" method="post">
    	<input type="hidden" id="form_id" name="form_id" />
    	<input type="hidden" id="form_name" name="form_name" />
    	<input type="hidden" id="form_url" name="form_url" />
    	<input type="hidden" id="user_role_access" name="user_role_access" />
    	<input type="hidden" id="user_type_access" name="user_type_access" />
    	<input type="hidden" id="user_access" name="user_access" />
    </form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            
            /* $("#soft_delete_status_fk").click(function(){
            	 if($('input[name="soft_delete_status_fk"]').is(':checked')){            	
               	  	$('#tableDiv').show();
               	}else{
               		$('#tableDiv').hide();
               	}
            });
            if($('input[name="soft_delete_status_fk"]').is(':checked')){            	
          	  	$('#tableDiv').show();
          	}else{
          		$('#tableDiv').hide();
          	} */
          	
            $('.searchable').select2();
            
            var listCount = '${fn:length(modulePermissions.accessPermissions) }';
            if(listCount > 0){
            	$('#tableDiv').show();
            	$("#buttons").show();
            }else{
            	$('#tableDiv').hide();
            	$("#buttons").hide();
            }
            
            
            $('#module_permission_table').DataTable({
            	"bPaginate": false,
            	"bInfo": false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                "bAutoWidth": true,
                "ordering": false,
                "searching":true,
                "initComplete" : function() {
					$('.dataTables_filter input[type="search"]')
							.attr('placeholder', 'Search')
							.css({
								'width' : '350px ',
								'display' : 'inline-block'
							});
				}
            });
        });
        
        function updateUrlPermissions(index){
        	$(".page-loader").show();
        	var form_id = $("#form_ids"+index).val();
        	var form_name = $("#form_names"+index).val();
        	var form_url = $("#form_urls"+index).val();
        	var user_role_access = $("#access_user_roles"+index).val();
        	var user_type_access = $("#access_user_types"+index).val();
        	var user_access = $("#access_users"+index).val();
        	$("#form_id").val(form_id);
        	$("#form_name").val(form_name);
        	$("#form_url").val(form_url);
        	$("#user_role_access").val(user_role_access);
        	$("#user_type_access").val(user_type_access);
        	$("#user_access").val(user_access);
        	/* alert(form_id)
        	alert(form_name)
        	alert(form_url)
        	alert(user_role_access)
        	alert(user_type_access)
        	alert(user_access) */
        	var form = $("#getUrlpermissionsForm");
            var url = form.attr('action');
            $.ajax({
                type: "POST",
                url: url,
                data: form.serialize(),
                success: function(data) {
                    // Ajax call completed successfully
                    swal("Success!", "URL Permissions Updated Succesfully.");
                    $(".page-loader").hide();
                },
                error: function(data) {
                    // Some error in ajax call
                    swal("Failed!", "Updating URL Permissions failed. Try again.", "error");
                    $(".page-loader").hide();
                }
            });
        } 
        
        function getModuleUrlsList(module_name) {
			$("#module_name_fk_temp").val(module_name);
			$("#getModuleUrlsForm").submit();
		}
        
        function getUrlsList(url_type) {
			$("#url_type").val(url_type);
			$("#getUrlsForm").submit();
		}
        
        function setAccessUserRoles(index){
        	var value = $("#user_roles"+index).val();
        	if($.trim(value) != ''){ $("#access_user_roles"+index).val(value.toString().split(",").join("~$~")); }
  		}
        function setAccessUserTypes(index){
        	var value = $("#user_types"+index).val();
        	if($.trim(value) != ''){ $("#access_user_types"+index).val(value.toString().split(",").join("~$~")); } 
        }
        function setAccessUsers(index){
        	var value = $("#users"+index).val();
        	if($.trim(value) != ''){ $("#access_users"+index).val(value.toString().split(",").join("~$~")); } 
        }
        
        function updateForm(){
      	   if(validator.form()){ // validation perform
   	  		    $(".page-loader").show();
       		    $("#modulePermissionForm").submit();	
      	   }else{
           	    $(".page-loader").hide();
       	   }
        }
         
        var validator = $('#modulePermissionForm').validate({ 			
 			 ignore: ":hidden:not(.validate-dropdown)",
 	  		    rules: {
 	  		 		 "module_name": {
 	  			 		required: true
 	  			 	  },
 	  		 	},
 	  		    messages: {
 	  		 		  "module_name": {
 	  			 		required: 'Required'
 	  			 	  },
 		   		},
 		   		errorPlacement:function(error, element){
 		   		 	  if(element.attr("id") == "module_name" ){
 					     document.getElementById("module_nameError").innerHTML="";
 				 	     error.appendTo('#module_nameError');
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
        
    </script>

</body>

</html>