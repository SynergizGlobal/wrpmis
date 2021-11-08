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
    <title>Update Report - Admin - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
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
        .m0{margin: 0 !important;}
       	.bd-none{border:none !important;background: transparent}
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -4.8em;
   					 margin-left: 11%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:19%;}
    	}
    	@media(max-width: 800px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	}

		@media only screen and (max-width: 768px){
			.input-field p.searchable_label {
			    margin-top: -16px !important;
			    margin-bottom: 0;
			}
		@media(max-width: 575px){
		.m0{margin: 1rem auto !important;}
		}
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
                                <h6>Update Report</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update-report" id="accessReport" name="accessReport" method="post" class="form-horizontal" role="form" >
                    	<input type="hidden" name ="form_id" value="${reportDetails.form_id }" />
                        <div class="container container-no-margin">                            
                            <div class="row">
                                <div class="col s12 m4 l4 input-field">
                                   <input id="form_name" type="text" class="validate" value="${reportDetails.form_name }" readonly="readonly">
                                    <label for="form_name">Report Name</label>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                   <input id="module_name_fk" type="text" class="validate" value="${reportDetails.module_name_fk }" readonly="readonly">
                                    <label for="module_name_fk">Module</label>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                   <input id="parent_form_id_sr_fk" type="text" class="validate" value="${reportDetails.folder_name }" readonly="readonly">
                                    <label for="parent_form_id_sr_fk">Folder</label>
                                </div>
                            </div>
                            
                            <div class="row">     
                            	<div class="col s12 m4 input-field">
                                    <input id="priority" name="priority" type="number" class="validate" value="${reportDetails.priority }">
                                    <label for="priority">priority <span class="required">*</span></label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>                             
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${statusList }">
                                      	    <option value= "${ obj.soft_delete_status_fk}" <c:if test="${reportDetails.soft_delete_status_fk eq obj.soft_delete_status_fk}">selected</c:if>>${obj.soft_delete_status_fk}</option>
                                          </c:forEach>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <div class="row">                                        
                                        <div class="col s5 m5 input-field">
                                            <p style="margin-top: 12px;">Mobile View ?</p>
                                        </div>
                                        <div class="col s5 m7 input-field">
                                            <p class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input class="with-gap" name="display_in_mobile" type="radio"
                                                        value="Yes" <c:if test="${reportDetails.display_in_mobile == 'Yes'}">checked</c:if>/>
                                                    <span>Yes</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="display_in_mobile" type="radio"
                                                        value="No" <c:if test="${reportDetails.display_in_mobile == 'No'}">checked</c:if> <c:if test="${empty reportDetails.display_in_mobile}">checked</c:if>/>
                                                    <span>No</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                 
                            </div>

                            <div class="row" style="margin-bottom: 20px;">
                                 
                                <div class="col s12 m8 l12 offset-m2">
                                    <div class="row fixed-width">
                                        <h6 class="center-align"  style="font-weight:600;">Report Details</h6>
                                        <div class="table-inside">
                                            <table id="form_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>User Role</th>
                                                        <th>User Type</th>
                                                        <th class="fw-8p">User</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
														<td class="input-field">
                                                            <select id="user_role_access" name="user_role_access" class="searchable" multiple>
                                                                <option value="">Select</option>
                                                                <c:forEach var="obj" items="${user_roles }">
		                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
		                                                        </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td class="input-field">
                                                            <select id="user_type_access" name="user_type_access" class="searchable" multiple>
                                                                <option value="">Select</option>
		                                                        <c:forEach var="obj" items="${user_types }">
		                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
		                                                        </c:forEach>
                                                            </select>
                                                        </td>
														<td class="input-field">
                                                            <select id="user_access" name="user_access" class="searchable" multiple>
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
                            <!-- </div> -->
                            <div class="row">
                                 
                                <div class="col s6 m4 l6 mt-brdr offset-m2 center-align">
                                    <div class=" m-1">
	                                         <button type="button" onclick="updateReport();" class="btn waves-effect waves-light bg-m">Update</button>
	                                </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr center-align">
                                    <div class=" m-1">
                                        <a href="<%=request.getContextPath()%>/reports" class="btn waves-effect waves-light bg-s">Cancel</a>
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
            
            var user_role_access = '${reportDetails.user_role_access}';
            var user_type_access = '${reportDetails.user_type_access}';
            var user_access = '${reportDetails.user_access}';
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
        function updateReport(){
      	   if(validator.form()){ // validation perform
   	  		   $(".page-loader").show();	  
       		   document.getElementById("accessReport").submit();	
      	   }else{
           	   $(".page-loader").hide();
       	   }		
     	 	 
         }
         
         var validator = $('#accessReport').validate({ 			
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