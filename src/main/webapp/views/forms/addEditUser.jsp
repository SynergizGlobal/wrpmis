<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>
	<c:if test="${action eq 'edit'}">Update User - Admin - PMIS</c:if>
	<c:if test="${action eq 'add'}"> Add User - Admin - PMIS</c:if>
	</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	
	<link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">	 
	<!-- <link rel="stylesheet" href="/pmis/resources/css/users.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
	<style>
		.con-box, .exe-box, .risk-box, .la-box, .us-box, .rr-box{display: none;}
		.per-box-list{
			margin: 0!important;
		}
		.per-box-list li{
			width: 24.62%;
			margin: 10px 0;
		}
		.marob{
			margin: 0 0 10px;
		}
		.show {
		    display: inline-flex !important;
		}
		.w100{
			width: 100%;
		}
		[type="checkbox"]+span:not(.lever){
			padding: 0 25px;
		}
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
		}
		/*table with fixed header & height ends */
        #userPermissionsTableBody .select2-container{
        	max-width:290px;
        	text-align:left;
        }
        .mdl-data-table {
        	border:1px solid #ccc;
        }
          .input-field .searchable_label{
        	font-size:0.85rem;
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
		.w7em{width: 7em;}
        .bd-none{border:none !important;background: transparent}
		@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.3em;
   					 margin-left: 9%;}
   		.bd-none{border: none;background: transparent}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:17%;}
    	}
    	@media(max-width: 912px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	.per-box-list li {
			    width: 49.5%;
			    margin: 10px 0;
			}
		.mobile_responsible_table>tbody tr td.mobile_btn_close a {
		    float: right;
		    margin-right: -25px;
		    margin-top: 1em;
		}
		.no-bd{
			border: 0 !important;
		}
		.mdl-data-table td, .mdl-data-table th {
		    text-align: center;
		}
		.mobile_responsible_table tbody tr td .select2-container{
			width: 90% !important;
		}
    	}
		 @media only screen and (max-width:820px) {          
			.input-field input[type="email"]{
				box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px #fff !important;
				width: -webkit-fill-available;
			    background-color: transparent;
			    padding-left: 10px;
			}  			
		} 
		 @media only screen and (max-width:601px) {     
			.col.s12.max-h{
				width: 110%;
			}
		}
		
		 .select2-container--default .select2-selection--multiple .select2-selection__choice__display{
			white-space: pre-wrap;
			word-break: break-word;
		}
		.select2-container--default .select2-selection--multiple .select2-selection__choice{
			display: inherit;
		}
		/*.select2-selection__rendered li{
			display: block;
			float: left;
		} */
		@media(max-width: 575px){
			.per-box-list li {
			    width: 100%;
			    margin: 10px 0;
			}
			.mobile_responsible_table tbody tr td .select2-container{
				width: 100% !important;
			}
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                	<c:if test="${action eq 'edit'}">Update User (${usrObj.user_id })</c:if>
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
                            	<div class="col s6 m4 l4 input-field">
                                    <input id="user_name" name="user_name" type="text" class="validate" value="${usrObj.user_name }">
                                    <label for="user_name">Name <span class="required">*</span></label>
                                    <span id="user_nameError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field ">
                                    <input id="designation" name="designation" type="text" class="validate" value="${usrObj.designation }">
                                    <label for="designation">Designation <span class="required">*</span></label>
                                    <span id="designationError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                   <p class="searchable_label">Department <span class="required">*</span></p>
                                    <select id="department_fk" name="department_fk" class="searchable validate-dropdown" onchange="getReportingToPersonsList();">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${departments }">
                                        	<option value="${obj.department }" <c:if test="${obj.department eq usrObj.department_fk}">selected</c:if>>${obj.department_name }</option>
                                        </c:forEach>
                                    </select>     
                                    <span id="department_fkError" class="error-msg" ></span>
                                </div>
                                
                                </div>
                                <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                   <p class="searchable_label">Reporting To <span class="required">*</span></p>
                                   <select id="reporting_to_id_srfk" name="reporting_to_id_srfk" class="searchable validate-dropdown">
                                       <option value="">Select</option>
                                       <c:forEach var="obj" items="${reportingToList }">
                                       	<option value="${obj.user_id }" <c:if test="${obj.user_id eq usrObj.reporting_to_id_srfk}">selected</c:if>><c:if test="${not empty obj.designation}">${obj.designation } - </c:if>${obj.user_name }</option>
                                       </c:forEach>
                                   </select>   
                                   <span id="reporting_to_id_srfkError" class="error-msg" ></span>
                                </div>
                                <input id="user_role_code" name="user_role_code" type="hidden">
                                <%-- <c:if test="${empty usrObj.user_id }">
                                <div class="col s12 m4 input-field">
                                    <input id="user_id" name="user_id" type="text" class="validate">
                                    <label for="user_id">User ID</label>
                                    <span id="user_idError" class="error-msg" ></span>
                                </div>
                                </c:if> --%>
                              <%--   <c:if test="${not empty usrObj.user_id }">
                                <div class="col s12 m4 input-field">
                                    <!-- <input type="text" id="user_id"> -->
                                    <label class="primary-text-bold" style="margin-top:10px">User ID :  <input id="user_id" name="user_id" type="text" value="${usrObj.user_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                    <br><br>
                                </div>
                                </c:if> --%>
                                 <div class="col s6 m4 l4 input-field">
                                     <p class="searchable_label">User Type <span class="required">*</span></p>
                                      <select id="user_type_fk" name="user_type_fk" class="searchable validate-dropdown"  onchange="getReportingToPersonsList();">
                                          <option value="">Select</option>
                                          <c:forEach var="obj" items="${types }">
                                          	<option value="${obj.user_type_fk }" <c:if test="${obj.user_type_fk eq usrObj.user_type_fk}">selected</c:if>>${obj.user_type_fk }</option>
                                          </c:forEach>
                                      </select> 
                                      <span id="user_type_fkError" class="error-msg" ></span>
                                </div>
                                <input id="user_id" name="user_id" type="hidden" value="${usrObj.user_id }">
                                <div class="col s12 m4 l4 input-field">
                                     <p class="searchable_label">User Role <span class="required">*</span></p>
                                      <select id="user_role_name_fk" name="user_role_name_fk" class="searchable validate-dropdown" onchange="setUserRoleCode();">
                                          <option value="">Select</option>
                                          <c:forEach var="obj" items="${roles }">
                                          	<option name="${obj.user_role_code }" value="${obj.user_role_name }" <c:if test="${obj.user_role_name eq usrObj.user_role_name_fk}">selected</c:if>>${obj.user_role_name }</option>
                                          </c:forEach>
                                      </select> 
                                      <span id="user_role_name_fkError" class="error-msg" ></span>
                                </div>
                            
                            	
                            
                                </div>
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="email_id" name="email_id" type="email" class="validate" value="${usrObj.email_id }">
                                    <label for="email_id">Email ID <span class="required">*</span></label>
                                    <span id="email_idError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field ">
                                    <input id="mobile_number" name="mobile_number" type="number" class="validate" value="${usrObj.mobile_number }">
                                    <label for="mobile_number"> Mobile Number</label>
                                    <span id="mobile_numberError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <input id="personal_contact_number" name="personal_contact_number" type="number" class="validate" value="${usrObj.personal_contact_number }">
                                    <label for="personal_contact_number"> Personal Contact Number </label>
                                    <span id="personal_contact_numberError" class="error-msg" ></span>
                                </div>
                            </div>        
                            
                                                           
                                
                          
                                              
                            <div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="landline" name="landline" type="number" class="validate" value="${usrObj.landline }">
                                    <label for="landline"> Landline </label>
                                    <span id="landlineError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input id="extension" name="extension" type="number" class="validate" value="${usrObj.extension }">
                                    <label for="extension">Extension</label>
                                     <span id="extensionError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">                                
                                    <input id="pmis_key_fk" name="pmis_key_fk" type="text" class="validate" value="${usrObj.pmis_key_fk }">
                                    <label for="pmis_key_fk">PMIS KEY</label>
                                    <span id="pmis_key_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                             <div class="row">
                                
                            </div>
                            <div class="row">
                                
                                <div class="col l12 m8 s12 offset-m2">
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
                                
                            
                           </div>
                            </div>
                                <div class="row no-container">
                                	<div class="ch-list center-align">
                                		<p>
									      <label>
									        <input type="checkbox" id="contract" name="contract_permission_checkbox" value="no" class="con-ch" onchange="valueChanged('contract')"/>
									        <span>Contract</span>
									      </label>
									    
								     	 
									    
								<!-- 		      <label>
									        <input type="checkbox" id="risk" name="risk_permission_checkbox" class="risk-ch" value="no" onchange="valueChanged('risk')"/>
									        <span>Risk</span>
									      </label>
									     -->
									      <label>
									        <input type="checkbox" id="land" name="la_permission_checkbox" class="la-ch" value="no" onchange="valueChanged('land')"/>
									        <span>Land Acquisition</span>
									      </label>
									    
									      <label>
									        <input type="checkbox" id="utility" name="us_permission_checkbox" class="us-ch" value="no" onchange="valueChanged('utility')"/>
									        <span>Utility Shifting</span>
									      </label>
									    
									      <label>
									        <input type="checkbox" id="rr" name="rr_permission_checkbox" class="rr-ch" value="no" onchange="valueChanged('rr')"/>
									        <span>R&R</span>
									      </label>
									      
									      <label>
									        <input type="checkbox" id="execution" name="execution_permission_checkbox" value="no" class="exe-ch" onchange="valueChanged('execution')"/>
									        <span>Execution</span>
									      </label>
									    </p>
                                	</div>
                                </div>
                                
                                 <div class="row no-container center-align no-mar">
                                <ul class="per-box-list">
                                	<li class="con-box">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Contract Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="contract_permission" class="searchable"
                                                                name="contract_id" multiple placeholder="Contract">
                                                                <option value="">Select</option>
                                                                 <c:forEach var="obj" items="${contractsList }">
						                                        	<option value="${obj.contract_id }" 
						                                        				<c:forEach var="tempobj" items="${usrObj.contractExecutivesList}">
																		 			<c:if test="${tempobj.contract_id_fk eq obj.contract_id}">selected</c:if>
									                                          	</c:forEach>
						                                        	>${obj.contract_short_name }</option>
						                                        </c:forEach>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </li>
                   <%--              <li class="exe-box">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Structure Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="str_dep" class="searchable"
                                                                name="user_type" multiple placeholder="Structure">
                                                                <option value="Select">Select</option>
                                                                <c:forEach var="obj" items="${structuresList }">
						                                        	<option value="${obj.structure_id }">${obj.structure }</option>
						                                        </c:forEach>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </li>
                                <li class="risk-box">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Risk Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="risk_work" class="searchable"
                                                                name="user_type" multiple placeholder="Work">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </li> --%>
                            
                                    <li class="la-box">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Land Acquisition Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="la_work" class="searchable"
                                                                name="land_work" multiple placeholder="Work">
                                                                <option value="">Select</option>
                                                                <c:forEach var="obj" items="${landList }">
						                                        	<option value="${obj.work_id_fk }" 
						                                        				<c:forEach var="tempobj" items="${usrObj.landExecutivesList}">
																		 			<c:if test="${tempobj.work_id_fk eq obj.work_id_fk}">selected</c:if>
									                                          	</c:forEach>
						                                        	>${obj.work_short_name }</option>
						                                        </c:forEach>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </li>
                                <li class="us-box">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">Utility Shifting Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="us_work" class="searchable"
                                                                name="us_work" multiple placeholder="Work">
                                                                <option value="">Select</option>
                                                                <c:forEach var="obj" items="${utilityList }">
						                                        	<option value="${obj.work_id_fk }" 
						                                        				<c:forEach var="tempobj" items="${usrObj.utilityExecutivesList}">
																		 			<c:if test="${tempobj.work_id_fk eq obj.work_id_fk}">selected</c:if>
									                                          	</c:forEach>
						                                        	>${obj.work_short_name }</option>
						                                        </c:forEach>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </li>
                                <li class=" rr-box">
                                    <div class="row fixed-width">
                                        <!-- <h5 class="center-align">User Details</h5> -->
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="center-align" colspan="3">R&R Permission</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="rr_work" class="searchable"
                                                                name="rr_work" multiple placeholder="Work">
                                                                <option value="">Select</option>
                                                                <c:forEach var="obj" items="${rrList }">
                                                                
						                                        	<option value="${obj.work_id_fk }"  
						                                        				<c:forEach var="tempobj" items="${usrObj.rrExecutivesList}">
																		 			<c:if test="${tempobj.work_id_fk eq obj.work_id_fk}">selected</c:if>
									                                          	</c:forEach>
						                                        	>${obj.work_short_name }</option>
						                                        </c:forEach>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </li>
                                </ul>
                                </div>
                             <div class="container container-no-margin">
                             <div class="row exe-box w100" style="margin-bottom: 20px;">
                             	<div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <h5 class="center-align marob">Structure Permission</h5>
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table mobile_responsible_table">
                                                <thead>
                                                    <tr>
                                                        <th class="w20em">Contract </th>
                                                        <th class="w20em">Structure </th>
                                                        <th class="">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="stBody">
                                                    <tr id="actionStRow0">
                                                        <td data-head="Contract" class="input-field">

                                                            <select id="str_con" class="searchable"
                                                                name="user_role" placeholder="User Role">
                                                                <option value="select">Select</option>
                                                            </select>
                                                            <span id="access_type0Error" class="error-msg"></span>
                                                        </td>
                                                        <td data-head="Structure" class="input-field">
                                                            <select id="str_per" class="searchable"
                                                                name="user_type" multiple placeholder="User Type">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                        <td class="input-field mobile_btn_close">
                                                            <a href="#" onclick="removeStActions('0');" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                                <!-- <select id="user" class="searchable"
                                                                name="user" multiple placeholder="User">
                                                                <option value="Select">Select</option>
                                                            </select> -->
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <table class="mdl-data-table table-add bd-none">
												<tbody class="bd-none">
													<tr class="bd-none">
														<td colspan="3" class="bd-none"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c add-align"
															onclick="addStRow()"> <i
																class="fa fa-plus"></i>
														</a>
													</tr>
												</tbody>
											</table>
                                            
												
													<input type="hidden" id="rowNo" name="rowNo" value="" />
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
												
											

                                        </div>
                                    </div>
                                </div>
                             </div>
                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr offset-m2">
                                    <div class="center-align m-1">
                                        <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateUser();" class="btn waves-effect waves-light bg-m">Update</button>
	                                    </c:if>
	                                    <c:if test="${action eq 'add'}">
	                                        <button type="button" onclick="addUser();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
	                                    </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/users" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
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
	
	
	 <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script>
	    $('.con-ch').change(function () {
	        $('.con-box').toggleClass('show');
	    });
	    $('.exe-ch').change(function () {
	        $('.exe-box').toggleClass('show');
	    });
	    $('.risk-ch').change(function () {
	        $('.risk-box').toggleClass('show');
	    });
	    $('.la-ch').change(function () {
	        $('.la-box').toggleClass('show');
	    });
	    $('.us-ch').change(function () {
	        $('.us-box').toggleClass('show');
	    });
	    $('.rr-ch').change(function () {
	        $('.rr-box').toggleClass('show');
	    });
	    
	    function addStRow(){
			var rowNo = $("#rowNo").val();
	        var rNo = Number(rowNo)+1;
	        var html = '<tr id="actionStRow' + rNo + '">'
	           +'<td data-head="Contract" class="input-field">'
	           +'<select name="user_role" id="str_con'+rNo+'"  class="validate-dropdown searchable">'	   			
	   		   +'<option value="" >Select</option>'
	   		   +'</select><span id="access_type0Error" class="error-msg"></span></td>' 			
	   		   +'<td data-head="Structure" class="input-field">'	
			   +'<select name="user_type" id="str_per'+rNo+'"  class="searchable" multiple placeholder="Structure">'	
			   +'<option value="" >Select</option>'	
			   +'</select><span id="access_value0Error" class="error-msg"></span></td>'
			   +'<td class="input-field mobile_btn_close"><a href="#" onclick="removeStActions(' + rNo + ');" class="btn waves-effect waves-light red t-c "><i class="fa fa-close"></i></a></td>'
			   +'</tr>';
		
			$('#stBody').append(html);
	        $("#rowNo").val(rNo);          	
	        
	        $('.searchable').select2();
	    }
	    
	    function removeStActions(rowNo){
	    	$("#actionStRow"+rowNo).remove();
	    }
	
	/****************************************************************************************************/

	   function valueChanged(idVal){
	        $('#'+idVal).val(idVal);
	    };
	    
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            //$('.searchable').select2();
            $( ".searchable" ).each(function( index,val ) {
            	   $( this ).select2({                placeholder:      $( this ).attr('placeholder')       });
            	});
            $('#remarks').characterCounter();
            if('${action}' == 'edit'){
           	 //$('#pmis_key_fk').rules('remove',  'required');
           	 $('#pmis_key_req').text('');
            }else{
           	 //$('#pmis_key_fk').rules('add',  { required: true });
           	 $('#pmis_key_req').text('*');
            }
        });
        
        function getReportingToPersonsList(){
       	  	 $(".page-loader").show(); 
     		 $("#reporting_to_id_srfk option:not(:first)").remove();
     		 var department_fk = $("#department_fk").val();
     		 var user_type_fk = $("#user_type_fk").val();
             var myParams = { department_fk : department_fk,user_type_fk : user_type_fk };
             $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getUserReportingToList",
                   data: myParams, cache: false,async:true,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	  var name = "";
                        	  if($.trim(val.designation) != ''){
                        		  name = $.trim(val.designation) + " - " + $.trim(val.user_name)
                        	  }
                              $("#reporting_to_id_srfk").append('<option value="' + val.user_id + '">' + $.trim(name)+ '</option>');
                           });
                       }
                       $('.searchable').select2();
                       $(".page-loader").hide();
                   },error:function(){
                	   $(".page-loader").hide();
                   }
             });
        }
        
        function setUserRoleCode(){
        	var user_role_code = $("#user_role_name_fk").find('option:selected').attr("name");
        	
        	$('#user_role_code').val(user_role_code);
        }  
        
        
        $('form').on('reset', function () {
            $(".searchable").trigger("change");
        });
        
        var flag = false;
        if('${action}' == 'edit'){
        	flag = true;
         }
        
        var pmis_key_fk = $('#pmis_key_fk').val();
		/* if ($.trim(pmis_key_fk) != '' && pmis_key_fk == '${usrObj.pmis_key_fk }') { 
        	flag = true;
        } */
        
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
    		if(validator.form()){ 
    			//if(flag){
        			$(".page-loader").show();
        			document.getElementById("userForm").submit();		
            	//}		
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
    				   	  "user_type_fk":{
    				   		required: true
    				   	  },"user_role_name_fk": {
       				 		required: true
       				 	  },"department_fk": {
    				 		required: true
    				 	  },"reporting_to_id_srfk": {
    				 		required: true
    				 	  },"user_name": {
    				 		required: true
    				 	  },"designation": {
    				 		required: true
    				 	  },"email_id": {
    				 		required: true
    				 	  },"mobile_number": {
    				 		required: false
    				 	  },"landline": {
    			 		    required: false,
    			 	   	  },"extension": {
    				 		required: false
    				 	  }/* ,"pmis_key_fk":{
    				 		 required: false
    				 		 //checkExists: true
    				 	  } */
    				 				
    			 	},
    			   messages: {
	    				 "user_type_fk":{
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
    			 	  	 }/* ,"pmis_key_fk":{
    				 		required: 'Required'
   				 	  	 } */
    			 				      
    		    },
    			  errorPlacement:
    			 	function(error, element){
	    				if (element.attr("id") == "user_type_fk" ){
	  			 		     document.getElementById("user_type_fkError").innerHTML="";
	  			 			 error.appendTo('#user_type_fkError');
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
    			 	    } else if (element.attr("id") == "pmis_key_fk" ){
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