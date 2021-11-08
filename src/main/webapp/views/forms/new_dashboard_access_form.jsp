<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 Update Dashboard - Admin - PMIS
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
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

        #dashboard_form_table td>.btn {
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
        .w20em{width: 20em;}
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
                                <h5>Update Dashboard</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="<%=request.getContextPath() %>/update-dashboard" id="dashboardForm" name="dashboardForm" method="post" class="form-horizontal" role="form" >
                    
                        <div class="container container-no-margin">
                        	<input type="hidden" name ="dashboard_id" value="${dashboardDetails.dashboard_id }" />
                            <div class="row">                              
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Dashboard :</p>
                                    <p>${dashboardDetails.dashboard_name }</p>  
                                </div> 
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Module :</p>
                                    <p>${dashboardDetails.module_name_fk}</p>
                                </div>
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Dashboard Type :</p>
                                    <p>${dashboardDetails.dashboard_type_fk}</p>
                                </div>
                                
                            </div>
                            <div class="row">
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Folder :</p>
                                    <p>${dashboardDetails.folder }</p>  
                                </div> 
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Work :</p>
                                    <p>${dashboardDetails.work_id_fk}<c:if test="${not empty dashboardDetails.work_short_name}"> - </c:if> ${dashboardDetails.work_short_name }</p>
                                </div>
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Contract :</p>
                                    <p>${dashboardDetails.contract_id_fk}<c:if test="${not empty dashboardDetails.contract_short_name}"> - </c:if> ${dashboardDetails.contract_short_name }</p>
                                </div>
                            </div>
                            <div class="row">
                                
                                <div class="col s12 m4 input-field">
                                    <input id="priority" name="priority" type="number" class="validate" value="${dashboardDetails.priority }">
                                    <label for="priority">priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${statusList }">
                                      	    <option value= "${ obj.soft_delete_status_fk}" <c:if test="${dashboardDetails.soft_delete_status_fk eq obj.soft_delete_status_fk}">selected</c:if>>${obj.soft_delete_status_fk}</option>
                                          </c:forEach>
                                    </select>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row">
                                        <!-- row 7 -->
                                        <div class="col s5 m5 input-field">
                                            <p style="margin-top: 12px;">Mobile View ?</p>
                                        </div>
                                        <div class="col s5 m7 input-field">
                                            <p class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="Yes" <c:if test="${dashboardDetails.mobile_view == 'Yes'}">checked</c:if>/>
                                                    <span>Yes</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="No" <c:if test="${dashboardDetails.mobile_view == 'No'}">checked</c:if>/>
                                                    <span>No</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div>

                            <div class="row no-container" style="margin-bottom: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">User Details</h5>
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="w20em">User Role </th>
                                                        <th class="w20em">user Type </th>
                                                        <th class="w20em">User</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr id="actionRow${index.count }">
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
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <!-- </div> -->
                            <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr offset-m2 center-align">
                                   <div class="m-1">
	                                 <button type="button" onclick="updateDashboard();" class="btn waves-effect waves-light bg-m">Update</button>
	                               </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr center-align">
                                	<div class="m-1">
                                      <a href="<%=request.getContextPath()%>/dashboards" class="btn waves-effect waves-light bg-s">Cancel</a>
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
	
    <script type="text/javascript">
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $( ".searchable" ).each(function( index,val ) {
   				$( this ).select2({ placeholder: $( this ).attr('placeholder') });
			});
            
            var user_role_access = '${dashboardDetails.user_role_access}';
            var user_type_access = '${dashboardDetails.user_type_access}';
            var user_access = '${dashboardDetails.user_access}';
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
            
            $( ".searchable" ).each(function( index,val ) {
   				$( this ).select2({ placeholder: $( this ).attr('placeholder') });
			});
            
        });
        
        function updateDashboard(){
	      	   if(validator.form()){ // validation perform
      	  		   $(".page-loader").show();	  
          		   document.getElementById("dashboardForm").submit();	
	      	   }else{
	            $(".page-loader").hide();
	           }	
         }
        
        var validator =	$('#dashboardForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		 "priority": {
	  			 		required: true
	  			 	  }
	  		 	},
	  		    messages: {
	  		 		  "priority": {
	  			 		required: 'Required'
	  			 	  }
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