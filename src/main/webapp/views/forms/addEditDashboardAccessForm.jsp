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
     	 <c:if test="${action eq 'edit'}">Update Dashboard - Admin - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Dashboard - Admin - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
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
        .select2-container--default .select2-selection--single {
		    background-color: transparent;
		}
		@media only screen and (max-width: 768px){
			.input-field p.searchable_label {
			    margin-top: -18px !important;
			    margin-bottom: 0;
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
                                <h6>
                                 <c:if test="${action eq 'edit'}">Update Dashboard</c:if>
								 <c:if test="${action eq 'add'}"> Add Dashboard</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                     <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-dashboard" id="dashboardForm" name="dashboardForm" method="post" class="form-horizontal" role="form" >
                          </c:if>
		              <c:if test="${action eq 'add'}">				                
		                	<form action="<%=request.getContextPath() %>/add-dashboard" id="dashboardForm" name="dashboardForm" method="post" class="form-horizontal" role="form" >
					  </c:if>
                        <div class="container container-no-margin">
                        	<input type="hidden" name ="dashboard_id" value="${dashboardDetails.dashboard_id }" />
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                    <input id="dashboard_name" name="dashboard_name" type="text" class="validate" value="${dashboardDetails.dashboard_name }">
                                    <label for="dashboard_name">Dashboard <span class="required">*</span></label>
                                    <span id="dashboard_nameError" class="error-msg"></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Module </p>
                                    <select class="searchable" id="module_name_fk" name="module_name_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${modulesList }">
                                      	    <option value= "${ obj.module_name_fk}" <c:if test="${dashboardDetails.module_name_fk eq obj.module_name_fk}">selected</c:if>>${obj.module_name_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="moduleError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label">Dashboard Type </p>
                                    <select class="searchable" id="dashboard_type_fk" name="dashboard_type_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${dashboardTypeList }">
                                      	    <option value= "${ obj.dashboard_type_fk}" <c:if test="${dashboardDetails.dashboard_type_fk eq obj.dashboard_type_fk}">selected</c:if>>${obj.dashboard_type_fk}</option>
                                          </c:forEach>
                                    </select>
                                    <span id="dashboard_typeError" class="error-msg"></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <input list="folder_list"  id="folder" type="text" value="${dashboardDetails.folder }" >
                                    <input type="hidden" name="folder" id="folder_id" value="${dashboardDetails.parent_dashboard_id_sr_fk }"/>
                                    <label for="folder">Folder</label>
                                    <datalist id="folder_list">
                                     	  <c:forEach var="obj" items="${foldersList }">
                                      	    <option data-value="${obj.dashboard_id}" value="${obj.folder}" ></option>
                                          </c:forEach>
                                    </datalist>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                    <p class="searchable_label"> Work </p>
                                   <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                        onchange="getContractsList(this.value);">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${worksList }">
                                      	   <option value= "${ obj.work_id_fk}" <c:if test="${dashboardDetails.work_id_fk eq obj.work_id_fk}">selected</c:if>>${obj.work_id_fk}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
                                         </c:forEach>
                                    </select>
                                      <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label">Contract</p>
                                   <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown" 
                                     	onchange="resetWorksDropdowns();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${contractsList }">
                                      	   <option workId="${obj.work_id_fk }" value= "${ obj.contract_id_fk}" <c:if test="${dashboardDetails.contract_id_fk eq obj.contract_id_fk}">selected</c:if>>${obj.contract_id_fk}<c:if test="${not empty obj.contract_short_name}"> - </c:if> ${obj.contract_short_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                           <%--  <c:if test="${action eq 'edit'}">	
	                             <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field"> 
									    <p class="searchable_label"> Work <span class="required">*</span></p>
	                                    <input type="text" name="work_id_fk" id="work_id_fk" value="${dashboardDetails.work_id_fk}- ${dashboardDetails.work_short_name}" readonly />
	                                </div>
	                                 <div class="col s12 m4 input-field">
										<p class="searchable_label">Project <span class="required">*</span></p>
	                                    <input type="text" name="project_id_fk" id="project_id_fk" value="${dashboardDetails.contract_id_fk}- ${dashboardDetails.contract_short_name}" readonly />
								    </div> 
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
                            </c:if> --%>
                            
                            <div class="row">
                                <div class="col s12 m8 l8 offset-l2 offset-m2">
                                    <div class="row">
                                        <!-- row 7 -->
                                        <div class="col s5 m5 l6 input-field">
                                            <p style="margin-top: 12px;" class="center-align">Mobile View ?</p>
                                        </div>
                                        <div class="col s7 m7 l6 input-field">
                                            <p class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="Yes" <c:if test="${dashboardDetails.mobile_view == 'Yes'}">checked</c:if> />
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
                                <div class="col s12 m3 input-field offset-m2">
                                    <input id="priority" name="priority" type="number" class="validate" value="${dashboardDetails.priority }">
                                    <label for="priority">priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 input-field offset-m2">
                                    <input id="icon_path" name="icon_path" type="text" class="validate" value="${dashboardDetails.icon_path }">
                                    <label for="icon_path">Icon </label>
                                    <span id="icon_pathError" class="error-msg"></span>
                                </div>
                                <div class="col s6 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="soft_delete_status_fk" class="searchable" name="soft_delete_status_fk">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${statusList }">
                                      	    <option value= "${ obj.soft_delete_status_fk}" <c:if test="${dashboardDetails.soft_delete_status_fk eq obj.soft_delete_status_fk}">selected</c:if>>${obj.soft_delete_status_fk}</option>
                                          </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s12 m4 input-field offset-m2">
                                    <input id="dashboard_url" name="dashboard_url" type="text" class="validate" value="${dashboardDetails.dashboard_url }">
                                    <label for="url">Url </label>
                                    <span id="urlError" class="error-msg"></span>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 20px;">
                                <div class="col s12 m8 offset-m2">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">Dashboard Details</h5>
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>Access Type </th>
                                                        <th>Access Value </th>
                                                        <th class="fw-8p">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detailsBody">
                                                    <c:choose>
														<c:when	test="${not empty dashboardDetails.accessPermissions && fn:length(dashboardDetails.accessPermissions) gt 0 }">
															<c:forEach var="aObj" items="${dashboardDetails.accessPermissions }"	varStatus="index">
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
			                                                            <select id="access_values${index.count }" class="searchable" name="access_values">
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
																		<a onclick="removeActions('${index.count }');" class="btn red waves-effect waves-light"> 
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
																	<a onclick="removeActions('0');" class="btn red waves-effect waves-light"><i class="fa fa-close"></i></a>
																</td>
															</tr>
														</c:otherwise>
													</c:choose>
													
                                                </tbody>
                                            </table>
                                            
                                            <table class="mdl-data-table">
												<tbody>
													<tr>
														<td colspan="6" ><a
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
													test="${not empty (dashboardDetails.accessPermissions) && fn:length(dashboardDetails.accessPermissions) gt 0 }">
													<input type="hidden" id="rowNo" name="rowNo"
														value="${fn:length(dashboardDetails.accessPermissions) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- </div> -->
                            <div class="row">
                                <div class="col s6 m4 mt-brdr offset-m2 center-align">
                                   <div class="m-1">
	                                 <c:if test="${action eq 'edit'}">
	                                    <button type="button" onclick="updateDashboard();" class="btn waves-effect waves-light bg-m">Update</button>
	                                 </c:if>
									 <c:if test="${action eq 'add'}"> 
				                       <button type="button" onclick="addDashboard();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
									 </c:if>
								   </div>
                                </div>
                                <div class="col s6 m4 mt-brdr center-align">
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
    <script>
  
    
    $('#folder').on('input', function() {
        const value = $(this).val();
        const data_value = $('#folder_list [value="' + value + '"]').data('value');
        //document.getElementById("folder").value = data_value;
        var v = $('#folder_id').val(data_value)
      });
    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });
        
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractsListForDashboardForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                                if ($.trim(val.contract_short_name) != '') { contract_name = ' - ' + $.trim(val.contract_short_name) }
                                var contract_id_fk = "${dashboardDetails.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id_fk == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '" selected>' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option workId="'+val.work_id_fk +'" value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                                }
                            });
                        } 
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function resetWorksDropdowns(){
        	$(".page-loader").show();        	
        	var workId = ''
       		var contract_id_fk = $("#contract_id_fk").val();
       		if($.trim(contract_id_fk) != ''){  
            	var workId = $("#contract_id_fk").find('option:selected').attr("workId");
       			$("#work_id_fk").val(workId);
       			$("#work_id_fk").select2();
       		}
       		$(".page-loader").hide();
        }
        
        function updateDashboard(){
     	   if(validator.form()){ // validation perform
     	  		   $(".page-loader").show();	  
         		   document.getElementById("dashboardForm").submit();	
     	   }
         	   else{
             	   $(".page-loader").hide();
         	   }		
    	 	 
        }
        function addDashboard(){
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
	  		 		 "dashboard_name": {
	  			 		required: true
	  			 	  },
	  		 	},
	  		    messages: {
	  		 		  "dashboard_name": {
	  			 		required: 'Required'
	  			 	  },
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "dashboard_name" ){
					     document.getElementById("dashboard_nameError").innerHTML="";
				 	     error.appendTo('#dashboard_nameError');
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
			+ '<td><a onclick="removeActions(' + rNo + ');"  class="btn red waves-effect waves-light"><i class="fa fa-close"></i></a></td></tr>';
		
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
        		url = "<%=request.getContextPath()%>/ajax/getUserRolesInDashboardAccess";
        	}else if(access_type == 'user_type'){
        		url = "<%=request.getContextPath()%>/ajax/getUserTypesInDashboardAccess";
        	}else if(access_type == 'user'){
        		url = "<%=request.getContextPath()%>/ajax/getUsersInDashboardAccess";
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