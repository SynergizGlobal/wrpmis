<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>
	<c:if test="${action eq 'edit'}">Update User</c:if>
	<c:if test="${action eq 'add'}"> Add User</c:if>
	</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	 
	 
	<link rel="stylesheet" href="/pmis/resources/css/users.css">
	<link rel="stylesheet" href="/mrvc/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<style>
        #example3 .datepicker~button,
        #example4 .datepicker~button {
            top: 26px;
        }

        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }


        .datepicker-table th,
        .datepicker-table td {
            padding: 0;
        }

        .radiogroup {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}		
		.preloader-wrapper{top: 45%!important;left:47%!important;}
        .error-msg label{color:red!important;}
        /*table with fixed header & height start */
		.max-h{
			max-height:400px;
			height:auto;
			overflow:auto;			
		}	
		.max-h tr{
			position:relative;
		}
		.max-h thead th{
			position:sticky;
			top:0;
			z-index:1;
			background-color:#508484;
		}
		/*table with fixed header & height ends */
        #userPermissionsTableBody .select2-container{
        	max-width:290px;
        	text-align:left;
        }
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	
	
	  <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                                	<c:if test="${action eq 'edit'}">Update User</c:if>
									<c:if test="${action eq 'add'}"> Add User</c:if>
								</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                          <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-user" id="userForm" name="userForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                          </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-user" id="userForm" name="userForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						  </c:if>
                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                     <p>User Role</p>
                                      <select id="user_role_name_fk" name="user_role_name_fk" class="searchable validate-dropdown" onchange="setUserRoleCode();">
                                          <option value="">Select</option>
                                          <c:forEach var="obj" items="${roles }">
                                          	<option name="${obj.user_role_code }" value="${obj.user_role_name }" <c:if test="${obj.user_role_name eq usrObj.user_role_name_fk}">selected</c:if>>${obj.user_role_name }</option>
                                          </c:forEach>
                                      </select> 
                                      <span id="user_role_name_fkError" class="error-msg" ></span>
                                </div>
                                <input id="user_role_code" name="user_role_code" type="hidden">
                                <%-- <c:if test="${empty usrObj.user_id }">
                                <div class="col s12 m4 input-field">
                                    <input id="user_id" name="user_id" type="text" class="validate">
                                    <label for="user_id">User ID</label>
                                    <span id="user_idError" class="error-msg" ></span>
                                </div>
                                </c:if> --%>
                                <c:if test="${not empty usrObj.user_id }">
                                <div class="col s12 m4 input-field">
                                    <!-- <input type="text" id="user_id"> -->
                                    <label class="primary-text-bold" style="margin-top:10px">User ID :  <input id="user_id" name="user_id" type="text" value="${usrObj.user_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                    <br><br>
                                </div>
                                </c:if>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <p>Department</p>
                                    <select id="department_fk" name="department_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departments }">
                                        	<option value="${obj.department }" <c:if test="${obj.department eq usrObj.department_fk}">selected</c:if>>${obj.department_name }</option>
                                        </c:forEach>
                                    </select>     
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                   <p>Reporting To</p>
                                   <select id="reporting_to_id_srfk" name="reporting_to_id_srfk" class="searchable validate-dropdown">
                                       <option value="">Select</option>
                                       <c:forEach var="obj" items="${reportingToList }">
                                       	<option value="${obj.user_id }" <c:if test="${obj.user_id eq usrObj.reporting_to_id_srfk}">selected</c:if>><c:if test="${not empty obj.designation}">${obj.designation } - </c:if>${obj.user_name }</option>
                                       </c:forEach>
                                   </select>   
                                   <span id="reporting_to_id_srfkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="user_name" name="user_name" type="text" class="validate" value="${usrObj.user_name }">
                                    <label for="name">Name</label>
                                    <span id="user_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="designation" name="designation" type="text" class="validate" value="${usrObj.designation }">
                                    <label for="designation">Designation </label>
                                    <span id="designationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="email_id" name="email_id" type="email" class="validate" value="${usrObj.email_id }">
                                    <label for="email_id">Email ID </label>
                                    <span id="email_idError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="mobile_number" name="mobile_number" type="text" class="validate" value="${usrObj.mobile_number }">
                                    <label for="mobile_number"> Mobile Number </label>
                                    <span id="mobile_numberError" class="error-msg" ></span>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>                          
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="landline" name="landline" type="number" class="validate" value="${usrObj.landline }">
                                    <label for="landline"> Landline </label>
                                    <span id="landlineError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="extension" name="extension" type="number" class="validate" value="${usrObj.extension }">
                                    <label for="extension">Extension</label>
                                     <span id="extensionError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            
                             <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">                                
                                    <input id="pmis_key_fk" name="pmis_key_fk" type="text" class="validate" value="${usrObj.pmis_key_fk }">
                                    <label for="pmis_key_fk">PMIS KEY</label>
                                    <span id="pmis_key_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div>
                            
                           <%--  <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field "></div>
                                <div class="col s12 m4 input-field">                                
                                    <p>PMIS KEY</p>
                                   	<select id="pmis_key_fk" name="pmis_key_fk" class="searchable validate-dropdown">
                                       <option value="">Select</option>
                                       <c:forEach var="obj" items="${pmisKeys }">
                                       	<option value="${obj.pmis_key_fk }" <c:if test="${obj.pmis_key_fk eq usrObj.pmis_key_fk}">selected</c:if>>${obj.pmis_key_fk }</option>
                                       </c:forEach>
                                   </select>
                                    <span id="pmis_key_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> --%>


                            <!-- insurance show hide div  -->
                            <div class="row fixed-width">
                                <h5 class="center-align">User Permissions</h5>
                                <!-- <div class="table-inside"> -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12 max-h">
                                    <table id="userPermissionsTable" class="mdl-data-table" >
                                        <thead>
                                            <tr>
                                                <th>User Access Type</th>
                                                <th>Value</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="userPermissionsTableBody">
                                            <c:choose>
                                        		<c:when test="${not empty usrObj.userPermissions && fn:length(usrObj.userPermissions) gt 0 }">
                                        			<c:forEach var="dObj" items="${usrObj.userPermissions }" varStatus="index">                                        	
			                                           <tr id="userPermissionsRow${index.count }">
		                                                <td>
		                                                    <select id="user_access_types${index.count }" name="user_access_types" onchange="getUserAccessValues(this.value,'${index.count }');" class="searchable">
		                                                        <option value="">User Access Type</option>
		                                                        <c:forEach var="obj" items="${userAccessTypes }">
		                                                        	<option value="${obj.user_access_type }" <c:if test="${dObj.user_access_type eq obj.user_access_type}">selected</c:if>>${obj.user_access_type }</option>
		                                                        </c:forEach>		                                                        
		                                                    </select>
		                                                </td>                                 
		                                                <td>
		                                                    <select id="user_access_values${index.count }" name="user_access_values" class="searchable">
		                                                        <option value="" selected>Select Value</option>
		                                                         <c:if test="${dObj.user_access_type eq 'Contracts' }">		                                                		  
			                                                        <c:forEach var="obj" items="${contractsForAccess }">
			                                                        	<option value="${obj.access_value_id }" <c:if test="${dObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_id}<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
			                                                        </c:forEach>
		                                                        </c:if>
		                                                        <c:if test="${dObj.user_access_type eq 'Department' }">		                                                		  
			                                                        <c:forEach var="obj" items="${departmentsForAccess }">
			                                                        	<option value="${obj.access_value_id }" <c:if test="${dObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_name }</option>
			                                                        </c:forEach>
		                                                        </c:if>  
		                                                        <c:if test="${dObj.user_access_type eq 'Module' }">		                                                		  
			                                                        <c:forEach var="obj" items="${modulesForAccess }">
			                                                        	<option value="${obj.access_value_id }" <c:if test="${dObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_name }</option>
			                                                        </c:forEach>
		                                                        </c:if>  
		                                                        <c:if test="${dObj.user_access_type eq 'Works' }">		                                                		  
			                                                        <c:forEach var="obj" items="${worksForAccess }">
			                                                        	<option value="${obj.access_value_id }" <c:if test="${dObj.access_value eq obj.access_value_id}">selected</c:if>>${obj.access_value_id}<c:if test="${not empty obj.access_value_name}"> - </c:if> ${obj.access_value_name }</option>
			                                                        </c:forEach>
		                                                        </c:if>    
		                                                    </select>
		                                                </td>
		                                                <td>
		                                                    <a  href="javascript:void(0);" class="btn waves-effect waves-light red t-c " onclick="removeUserPermissions('${index.count }');"> <i
		                                                            class="fa fa-close"></i></a>
		                                                </td>
		                                            </tr>
		                                            </c:forEach> 
                                        		</c:when>
                                        		<c:otherwise>
	                                        		<tr id="userPermissionsRow0">
		                                                <td>
		                                                    <select id="user_access_types0" name="user_access_types" onchange="getUserAccessValues(this.value,'0');" class="searchable">
		                                                        <option value="" selected>User Access Type</option>
		                                                        <c:forEach var="obj" items="${userAccessTypes }">
		                                                        	<option value="${obj.user_access_type }">${obj.user_access_type }</option>
		                                                        </c:forEach>
		                                                    </select>
		                                                </td>                                               
		                                                <td>
		                                                    <select id="user_access_values0" name="user_access_values" class="searchable">
		                                                        <option value="" selected>Select Value</option>
		                                                    </select>
		                                                </td>
		                                                <td>
		                                                    <a  href="javascript:void(0);" class="btn waves-effect waves-light red t-c " onclick="removeUserPermissions('0');"> <i
		                                                            class="fa fa-close"></i></a>
		                                                </td>
		                                            </tr>
                                        		</c:otherwise>
                                        	</c:choose> 
                                        </tbody>
                                    </table>
                                    
                                    <table class="mdl-data-table">
                                        <tbody>                                          
                                            <tr>
                                                <td colspan="3" style="text-align: right;"><a href="javascript:void(0);" onclick="addUserPermissions()"class="btn waves-effect waves-light bg-m t-c "> <i class="fa fa-plus"></i></a> </td>
											</tr>
                                        </tbody>
                                    </table>
                                    <c:choose>
                                        <c:when test="${not empty usrObj.userPermissions && fn:length(usrObj.userPermissions) gt 0 }">
                                            <input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(usrObj.userPermissions)}" />
                                        </c:when>
                                        <c:otherwise>
                                        	<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                        </c:otherwise>
                                    </c:choose> 

                                </div>
                                <!-- </div> -->
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <!-- <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>User Image</span>
                                            <input type="file" id="fileName" name="fileName" accept="image/*">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div> -->
                                
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                        	<c:if test="${not empty usrObj.user_image }">
                                            <span>Change Image</span>
                                            </c:if>
                                            <c:if test="${empty usrObj.user_image }">
                                            <span>User Image</span>
                                            </c:if>
                                            <input type="file" id="fileName" name="fileName" accept="image/*" onchange="readURL(this);">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text" name="user_image" value="${usrObj.user_image }">
                                            <img style="height: 20%;width: 20%;<c:if test="${empty usrObj.user_image }">display:none;</c:if>" id="userImagePreview" src="<%=CommonConstants2.USER_IMAGES %>${usrObj.user_image }" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';" alt="userImage" />
                                        </div>
                                    </div>
                                </div>
                                
                      
                                <div class="col m2 hide-on-small-only"></div>

                            </div>
                            <%-- <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${usrObj.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div> --%>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateUser();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addUser();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/users" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>
	
		
	     <!-- Page Loader -->
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

	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
	
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
    <script>
    	
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks').characterCounter();
        });
        
        function setUserRoleCode(){
        	var user_role_code = $("#user_role_name_fk").find('option:selected').attr("name");
        	
        	$('#user_role_code').val(user_role_code);
        }        
        
        function getUserAccessValues(user_access_type,indexNo){
        	var url = "";
        	if(user_access_type == 'Contracts'){
        		url = "<%=request.getContextPath()%>/ajax/getContractsForUserAccessTypes";
        	}else if(user_access_type == 'Department'){
        		url = "<%=request.getContextPath()%>/ajax/getDepartmentsForUserAccessTypes";
        	}else if(user_access_type == 'Module'){
        		url = "<%=request.getContextPath()%>/ajax/getModulesForUserAccessTypes";
        	}else if(user_access_type == 'Works'){
        		url = "<%=request.getContextPath()%>/ajax/getWorksForUserAccessTypes";
        	}
        	
        	if($.trim(url) != ''){
        		$("#user_access_values"+indexNo+" option:not(:first)").remove();
                $.ajax({
                    url: url,
                    cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                        	if(user_access_type == 'Contracts'){
                        		 $.each(data, function (i, val) {
                        			 var access_value_name = '';
                                     if ($.trim(val.access_value_name) != '') { access_value_name = ' - ' + $.trim(val.access_value_name) }
                                     $("#user_access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_id) + access_value_name+ '</option>');
                                 });
                        	}else if(user_access_type == 'Department'){
                        		 $.each(data, function (i, val) {
                                     $("#user_access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_name) + '</option>');
                                 });
                        	}else if(user_access_type == 'Module'){
                        		 $.each(data, function (i, val) {
                                     $("#user_access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_name) + '</option>');
                                 });
                        	}else if(user_access_type == 'Works'){
                        		 $.each(data, function (i, val) {
                        			 var access_value_name = '';
                                     if ($.trim(val.access_value_name) != '') { access_value_name = ' - ' + $.trim(val.access_value_name) }
                                     $("#user_access_values"+indexNo).append('<option value="' + val.access_value_id + '">' + $.trim(val.access_value_id) + access_value_name + '</option>');
                                 });
                        	}
                           
                        }
                        $("#user_access_values"+indexNo).select2();
                    }
                });
        	}
        }
        
        
        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });
        
        var flag = false;
        
        var pmis_key_fk = $('#pmis_key_fk').val();
        if ($.trim(pmis_key_fk) != '' && pmis_key_fk == '${usrObj.pmis_key_fk }') { 
        	flag = true;
        }
        
        $('#pmis_key_fk').on('blur', function(){
        	$('#pmis_key_fkError').html('');
            var pmis_key_fk = $('#pmis_key_fk').val();
            if ($.trim(pmis_key_fk) != '' && pmis_key_fk != '${usrObj.pmis_key_fk }') {             
	             $.ajax({
	               url: '<%=request.getContextPath()%>/ajax/checkPMISKeyAvailability',
	               type: 'POST',
	               data: { pmis_key_fk : pmis_key_fk},
	               success: function(response){
	                 if (response.keyAvailability == 'Taken' ) {
	                 	 $('#pmis_key_fkError').html('Sorry... Already taken').css('color', 'red');
	                 	 flag = false;
	                 } else if (response.keyAvailability == 'Available') {
	                 	 $('#pmis_key_fkError').html('Available').css('color', 'green');;
	                 	 flag = true;
	                 } else {
	                 	 $('#pmis_key_fkError').html('No Key Available').css('color', 'red');;
	                 	 flag = false;
	                 }
	               }
	             });
            }else if (pmis_key_fk == '${usrObj.pmis_key_fk }') {     
            	flag = true;
            }
        });
        
        function addUser(){
    		if(validator.form()){ // validation perform
    			if(flag){
        			$(".page-loader").show();
        			document.getElementById("userForm").submit();	
            	}		
    	 	}
    	}
    	
        function updateUser(){
      		if(validator.form()){ // validation perform
      			if(flag){
        			$(".page-loader").show();
        			document.getElementById("userForm").submit();	
            	}			
    	 	}
    	}
        
        
        
        
    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	var validator = $('#userForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
    				   	  "user_id":{
    				   		required: true
    				   	  },"user_role_name_fk": {
       				 		required: true
       				 	  },"department_fk": {
    				 		required: true
    				 	  },"reporting_to_id_srfk": {
    				 		required: true
    				 	  },"user_name": {
    				 		required: false
    				 	  },"designation": {
    				 		required: true
    				 	  },"email_id": {
    				 		required: false
    				 	  },"mobile_number": {
    				 		required: true
    				 	  },"landline": {
    			 		    required: false,
    			 	   	  },"extension": {
    				 		required: false
    				 	  },"remarks":{
    				 		 required: false
    				 	  },"pmis_key_fk":{
    				 		 required: false,
    				 		 //checkExists: true
    				 	  }
    				 				
    			 	},
    			   messages: {
	    				 "user_id":{
	    					 required: 'Required'
	   				   	 },"user_role_name_fk": {
       			 			required: 'Required'
       			 	  	 },"department_fk": {
    			 			required: 'Required'
    			 	  	 },"reporting_to_id_srfk": {
    			 			required: 'Required'
    			 	  	 },"user_name": {
    			 			required: 'Required'
    			 	  	 },"designation": {
    			 			required: 'Required'
    			 	  	 },"email_id": {
    			 			required: 'Required'
    			 	  	 },"mobile_number": {
    			 			required: 'Required'
    			 	  	 },"landline": {
    			 			required: 'Required'
    			 	  	 },"extension": {
    			 			required: 'Required'
    			 	  	 },"remarks":{
    			 	  		required: 'Required'
    				 	 },"pmis_key_fk":{
    				 		required: 'Required'
   				 	  	 }
    			 				      
    		    },
    			  errorPlacement:
    			 	function(error, element){
	    				if (element.attr("id") == "user_id" ){
	  			 		     document.getElementById("user_idError").innerHTML="";
	  			 			 error.appendTo('#user_idError');
	  			 	    }else if (element.attr("id") == "user_role_name_fk" ){
     			 		     document.getElementById("user_role_name_fkError").innerHTML="";
     			 			 error.appendTo('#user_role_name_fkError');
     			 	    }else if (element.attr("id") == "department_fk" ){
    			 		     document.getElementById("department_fkError").innerHTML="";
    			 			 error.appendTo('#department_fkError');
    			 	    }else if (element.attr("id") == "reporting_to_id_srfk" ){
    			 	    	 document.getElementById("reporting_to_id_srfkError").innerHTML="";
    			 			 error.appendTo('#reporting_to_id_srfkError');
    			 	    }else if (element.attr("id") == "user_name" ){
    			 		     document.getElementById("user_nameError").innerHTML="";
    			 			 error.appendTo('#user_nameError');
    			 	    }else if (element.attr("id") == "designation" ){
    			 		     document.getElementById("designationError").innerHTML="";
    			 			 error.appendTo('#designationError');
    			 	    }else if (element.attr("id") == "email_id" ){
    			 		     document.getElementById("email_idError").innerHTML="";
    			 			 error.appendTo('#email_idError');
    			 	    }else if (element.attr("id") == "mobile_number" ){
    			 		     document.getElementById("mobile_numberError").innerHTML="";
    			 			 error.appendTo('#mobile_numberError');
    			 	    }else if (element.attr("id") == "landline" ){
    			 		     document.getElementById("landlineError").innerHTML="";
    			 			 error.appendTo('#landlineError');
    			 	    }else if (element.attr("id") == "extension" ){
    			 		     document.getElementById("extensionError").innerHTML="";
    			 			 error.appendTo('#extensionError');
    			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
    			 	    }else if (element.attr("id") == "pmis_key_fk" ){
	   			 		     document.getElementById("pmis_key_fkError").innerHTML="";
				 			 error.appendTo('#pmis_key_fkError');
				 	    }
    			 },invalidHandler: function (form, validator) {
                     var errors = validator.numberOfInvalids();
                     if (errors) {
                         var position = validator.errorList[0].element;
                         jQuery('html, body').animate({
                             scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                         }, 1000);
                     }
                 },submitHandler: function(form) {
    			    // do other things for a valid form
    			    //form.submit();
    			    //return true;
    			  }
    		});
    	
    	    $.validator.addMethod("dateFormat",
        	    function(value, element) {
        	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
        	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
        	    	//return dtRegex.test(value);
        	    },
        	    //"Date format (Aug 02,2020)"
        	    "Date format (DD-MM-YYYY)"
        	);
    	    
    	    
    	    <%-- $.validator.addMethod("checkExists", function(value, element) {
    		    var pmis_key_fk = $('#pmis_key_fk').val();
	            $.ajax({
	               url: '<%=request.getContextPath()%>/ajax/checkPMISKeyAvailability',
	               type: 'POST',
	               data: { pmis_key_fk : pmis_key_fk},
	               success: function(response){
		                 if (response.keyAvailability == "Taken" ) {
		                	 $('#pmis_key_fkError').html("Sorry... Already taken");
		                	 return false;
		                 } else if (response.keyAvailability == "Available") {
		                 	 return true;
		                 } else {
		                	 $('#pmis_key_fkError').html("No Key Available");
		                	 return false ;
		                 }
	               }
		        });

    		}, ""); --%>
            
            
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
                        
            function addUserPermissions(){      		
                var rowNo = $("#rowNo").val();
                var rNo = Number(rowNo)+1;
                var html = '<tr id="userPermissionsRow'+rNo+'">'
        		   		  +'<td>'
        		   		+'<select id="user_access_types'+rNo+'" name="user_access_types" onchange="getUserAccessValues(this.value,'+rNo+');" class="searchable">'
        		   		+'<option value="">User Access Type</option>'
        		   		<c:forEach var="obj" items="${userAccessTypes }">
        		   		+'<option value="${obj.user_access_type }">${obj.user_access_type }</option>'
			            </c:forEach>		                                                        
			            +'</select>'
			            +'</td>'                                   
			            +'<td>'
			            +'<select id="user_access_values'+rNo+'" name="user_access_values" class="searchable">'
			            +'<option value="0" selected>Select Value</option>'
                        +'</select>'
                        +'</td>'
        			   	+'<td><a  class="btn waves-effect waves-light red t-c " onclick="removeUserPermissions('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
        			 
    			 $('#userPermissionsTableBody').append(html);
    			 $("#rowNo").val(rNo);
    			 
    			 //$('select:not(.searchable)').formSelect();
    	         $('.searchable').select2();
            }
            
    		function removeUserPermissions(rowNo){
            	$("#userPermissionsRow"+rowNo).remove();
            }
    		
    		function readURL(input) {
 	            if (input.files && input.files[0]) {
 	                var reader = new FileReader();
 	                reader.onload = function (e) {
 	                    $('#userImagePreview').attr('src', e.target.result)
 	                        //.width(150)
 	                        //.height(200);
 	                };
 	                reader.readAsDataURL(input.files[0]);
 	               $('#userImagePreview').show();
 	            }
 	        }
    		 
    </script>
</body>
</html>