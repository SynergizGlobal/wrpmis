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
                                <h6><span>Contract</span> Module Permission</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update-module-permission-form" id="modulePermissionForm" name="modulePermissionForm" method="post" class="form-horizontal" role="form" >
                    	<input type="hidden" name ="form_id" value="${formDetails.form_id }" />
                        <div class="container">                            
                            <div class="row">                              
                                <div class="col s12 m9 input-field">
                                    <p class="searchable_label"> Module Name </p>
                                    <select id="module_name" class="searchable" name="module_name">
                                        <option value="">Select</option>                                        
                                    </select>
                                </div>
                                <div class="col s12 m3 input-field pt-1r">
                                     <p>
								      <label>
								        <input type="checkbox" class="filled-in" name="module_active" id="module_active"/>
								        <span>Activate this Module ?</span>
								      </label>
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
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<tr>
                                                	<td class="input-field">
                                                		 <input id="form_name" name="form_name" type="text" class="validate" placeholder="Form Name">
                                                	</td>
                                                	<td class="input-field">
                                                		<input id="form_url" name="form_url" type="text" class="validate" placeholder="Form Url">
                                                	</td>
														<td class="input-field">
                                                            <select id="user_role_access" name="user_role_access" class="searchable" multiple data-placeholder="User Role">
                                                                <option value="">Select</option>
                                                                <c:forEach var="obj" items="${user_roles }">
		                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
		                                                        </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td class="input-field">
                                                            <select id="user_type_access" name="user_type_access" class="searchable" multiple data-placeholder="User Type">
                                                                <option value="">Select</option>
		                                                        <c:forEach var="obj" items="${user_types }">
		                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
		                                                        </c:forEach>
                                                            </select>
                                                        </td>
														<td class="input-field">
                                                            <select id="user_access" name="user_access" class="searchable" multiple data-placeholder="User">
                                                                <option value="" >Select</option>
		                                                        <c:forEach var="obj" items="${users }">
		                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
		                                                        </c:forEach>
                                                            </select>
                                                        </td>
													</tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                 
                            </div>
                            <div class="container">   
                            <div class="row">
                                 
                                <div class="col s6 m6 l6 mt-brdr center-align">
                                    <div class=" m-1">
	                                         <button type="button" onclick="updateForm();" class="btn waves-effect waves-light bg-m">Update</button>
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

            $("#module_active").click(function(){
            	 if($('input[name="module_active"]').is(':checked')){            	
               	  	$('#tableDiv').show();
               	}else{
               		$('#tableDiv').hide();
               	}
            });
            if($('input[name="module_active"]').is(':checked')){            	
          	  	$('#tableDiv').show();
          	}else{
          		$('#tableDiv').hide();
          	}
            
            var user_role_access = '${formDetails.user_role_access}';
            var user_type_access = '${formDetails.user_type_access}';
            var user_access = '${formDetails.user_access}';
            if($.trim(user_role_access) != ''){
            	var user_role_accessArr = user_role_access.split(',');
            	$("#user_role_access").val(user_role_accessArr);
            }
            if($.trim(user_type_access) != ''){
            	var user_type_accessArr = user_type_access.split(',');
            	$("#user_type_access").val(user_type_accessArr);
            }
            if($.trim(user_access) != ''){
            	var user_accessArr = user_access.split(',');
            	$("#user_access").val(user_accessArr);
            }
            
            $('.searchable').select2();
            
        });
        function updateForm(){
	      	   if(validator.form()){ // validation perform
	   	  		   $(".page-loader").show();	  
	       		   document.getElementById("modulePermissionForm").submit();	
	      	   }else{
	           	   $(".page-loader").hide();
	       	   }
        }
         
        var validator = $('#modulePermissionForm').validate({ 			
 			 ignore: ":hidden:not(.validate-dropdown)",
 	  		    rules: {
 	  		 		 "priority": {
 	  			 		required: true
 	  			 	  },
 	  		 	},
 	  		    messages: {
 	  		 		  "priority": {
 	  			 		required: 'Required'
 	  			 	  },
 		   		},
 		   		errorPlacement:function(error, element){
 		   		 	  if(element.attr("id") == "priority" ){
 					     document.getElementById("priorityError").innerHTML="";
 				 	     error.appendTo('#priorityError');
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
    </script>

</body>

</html>