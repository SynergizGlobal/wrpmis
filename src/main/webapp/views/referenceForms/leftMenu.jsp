<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Left Menu</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-grid-template.css" />
     <style>
    	 .required{color:red;}
         .dn-l{display:none;}
    	 .m-n1 {
		   		 margin: -2rem auto 0;
			}
		.btn {
		    height: 30px !important;
		    line-height: 30px !important;
		    }
		 .mdl-cell--6-col, .mdl-cell--6-col-desktop.mdl-cell--6-col-desktop {
			    width: calc(50% - 16px) !important;
			}
		div.dataTables_wrapper div.dataTables_filter{
			text-align:right;
		}
		.mdl-data-table{
			font-size: 1.2rem;
		}
		.mdl-data-table thead tr th, .mdl-data-table tfoot tr th{
			font-size: 1.2rem;
		}
		.w-fx{
		 width: 30%;	
		}
		td:last-child, td:last-of-type{
			white-space: initial;
		}
    	 @media(max-width: 820px){
    	 	.hideCOl{
    	 		display:none;
    	 	}
    	 	.dn-l{display: block;}
    	 	.mdl-cell--6-col, .mdl-cell--6-col-phone.mdl-cell--6-col-phone, .mdl-cell--7-col {
			    width: calc(100% - 16px) !important;
			}
			.dataTables_filter label input {
			    width: 90% !important;
			    margin-left: auto !important;
			    margin-right: auto !important;
			}
			div.dataTables_wrapper div.dataTables_filter {
			    margin-top: 0.5rem !important;
			}
			.g-w{
				width: 20% !important;
			}
    	 }
    	 @media(max-width: 575px){
    	 	.s-align-center{
    	 		text-align: center;
    	 	}
    	 	td {
			    word-break: initial;
			    word-wrap: normal;
			    white-space: initial;
			}
			.modal{
				width: 90%;
			}
			.modal-header{
				margin: 0px 0px 20px !important;
			}
			.modal .modal-content{
				padding: 0;
			}
			.mdl-data-table td>.btn+.btn {
			    text-align: center;
			    margin-left: 0.15rem;
			}
			
    	 }
     </style>
</head>

<body>
         <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Overview Dashboard</h6>
                            <div class="col s12 m12 right-align exportButton hideCOl" >
								
								<div class="m-n1">
									<a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Dashboard</a> 
								</div>
							</div>
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
                    	<div class="row">
                     	 	<div class="col s12 m3 offset-m2 input-field">
                                <p class="searchable_label">Parent</p>
                                <select id="parent_id_fk" name="parent_id"  class="searchable" onchange="addInQueParent(this.value);leftMenuList();">
                                    <option value="">Select</option>	                                    
                                </select>
                           	</div>
                     	 	<div class="col s12 m3 input-field">
                                <p class="searchable_label">Status</p>
                                <select id="status_id_fk" name="status"  class="searchable" onchange="addInQueStatus(this.value);leftMenuList();">
                                    <option value="">Select</option>	                                    
                                </select>
                           	</div>
                           	<div class="col s12 m4 s-align-center">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 20px;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                     	</div>
                        <div class="row dn-l">
                            <div class="col s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Dashboard</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="left_menu_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Parent</th>
                                            <th>Order</th>
                                            <th>Status</th>
                                            <%-- <c:forEach var="tObj" items="${moduleDetails.tablesList}" >
                                            	<c:forEach var="TObj" items="${tObj.tName }" >
                                            	 	<c:set var = "mTObj" value = "${fn:replace(TObj, '_', ' ')}" />
                                            	 	<th>${mTObj } </th>
                                            	</c:forEach>
                                            </c:forEach> --%>
                                            <th class="no-sort g-w">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										
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
		<form action="<%=request.getContextPath() %>/add-left-menu" id="addLeftMenuForm" name="addLeftMenuForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Module <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="">
	                <div class="row">
	                    <div class="col s12">
	                    	<div class="row no-mar">
	                            <div class="input-field col s12 m6">
	                                <input id="dashboard_name" name="dashboard_name" type="text" class="validate"  onkeyup="doValidate(this.value)">
	                                <label for="dashboard_name">Name<span class="required">*</span></label>
	                                <span id="dashboard_nameError" class="error-msg" ></span>
	                            </div> 
	                        	<div class="input-field col s12 m6">
	                        		<p class="searchable_label">Parent</p>
	                                <select id="parent_id" name="parent_id" class="searchable validate-dropdown">
	                                	<option value="0">Select</option>	
	                                	 <c:forEach var="obj" items="${parentList }">
		                                      <option value="${obj.dashboard_id }">${obj.dashboard_name }</option>
		                              </c:forEach>                                	
	                                </select>
	                                <span id="parent_idError" class="error-msg" ></span>
	                        	</div>
	                        </div>
	                        <div class="row no-mar">
	                        	<div class="input-field col s12">
	                                <input id="dashboard_url" name="dashboard_url" type="text" class="validate" >
	                                <label for="dashboard_url">Link</label>
	                                <span id="dashboard_urlError" class="error-msg" ></span>
	                        	</div>
	                        </div>  
	                        <div class="row no-mar">
	                        	<div class="input-field col s12 m6">
	                        		<input id="order" name="order" type="number" class="validate" >
	                                <label for="order">Order</label>
	                                <span id="orderError" class="error-msg" ></span>
	                        	</div>  
	                        	<div class="input-field col s12 m6">
	                        		<p class="searchable_label">Status<span class="required">*</span></p>
	                                <select id="status" name="status" class="searchable validate-dropdown">
	                                	   <option value="">Select</option>
								           <option value="Active">Active</option>
								           <option value="Inactive">Inactive</option>
	                                </select>
	                                <span id="statusError" class="error-msg" ></span>
	                        	</div> 
	                         </div>
	                         <div class="row no-mar">	                        	
	                        	<div class="input-field col s12">
	                                <input id="source_field_name" name="source_field_name" type="text" class="validate" >
	                                <label for="source_field_name">Work Filter Name</label>
	                                <span id="source_field_nameError" class="error-msg" ></span>
	                        	</div>
	                        </div> 
	                        <div class="row" style="margin-bottom: 20px;">
                                 
                                <div class="col s12 m12 l12">
                                    <div class="row fixed-width">
                                        <h6 class="center-align"  style="font-weight:600;">Form Details</h6>
                                        <div class="table-inside">
                                            <table id="form_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="w-fx">User Role </th>
                                                        <th class="w-fx">User Type </th>
                                                        <th class="fw-8p w-fx">User</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detailsBody">
	                                                	<tr>	
															<td class="input-field">
																<input type="hidden" id="access_user_roles0" name="access_user_roles" />
	                                                            <select id="user_roles0" name="user_roles" class="searchable" multiple data-placeholder="User Role" onchange="setAccessUserRoles(0)">
	                                                                <option value="">Select</option>
	                                                                <c:forEach var="obj" items="${user_roles }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
	                                                        <td class="input-field">
	                                                        	<input type="hidden" id="access_user_types0" name="access_user_types" />
	                                                            <select id="user_types0" name="user_types" class="searchable" multiple data-placeholder="User Type" onchange="setAccessUserTypes(0)">
	                                                                <option value="">Select</option>
			                                                        <c:forEach var="obj" items="${user_types }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
															<td class="input-field">
																<input type="hidden" id="access_users0" name="access_users" />
	                                                            <select id="users0" name="users" class="searchable" multiple data-placeholder="User"  onchange="setAccessUsers(0)">
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
                            <div class="row" style="margin-bottom: 20px;">
                                 
                                <div class="col s12 m12 l12">
                                    <div class="row fixed-width">
                                        <h6 class="center-align"  style="font-weight:600;">Archive Details</h6>
                                        <div class="table-inside">
                                            <table class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>Date </th>
                                                        <th>URL </th>
                                                        <th class="fw-8p">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="archiveBody">
															<tr id="actionRow0">
																<td class="input-field">
									                                    <input name="archive_date" type="text" class="validate datepicker" id="archive_date0">
									                                    <button type="button" name="archive_date" class="datepicker-button"><i class="fa fa-calendar"></i></button>
									                                
		                                                        </td>
		                                                        <td class="input-field">
										                                <input name="archive_url" type="text" class="validate" id="archive_url0">
										                                <span id="archive_urlError" class="error-msg" ></span>
										                            
		                                                        </td>
																<td>
																	<a onclick="removeActions('0');" class="btn red waves-effect waves-light"><i class="fa fa-close"></i></a>
																</td>
															</tr>													
                                                </tbody>
                                            </table>
                                            
                                            <table class="mdl-data-table">
												<tbody>
													<tr>
														<td colspan="6"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c"
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
                                 
                            </div> 
	                        <div class="row">
	                            <div class="col s12 m6">
	                                <div class="center-align m-1">
	                                    <button style="width: 100%;" id="bttn"  
	                                        class="btn waves-effect waves-light bg-m">Add</button>
	                                </div>
	                            </div>
	                            <div class="col s12 m6">
	                               <div class="center-align m-1">
	                                  <!--   <button
	                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
	                                        style="width:100%">Cancel</button> -->
	                                        <a href="<%=request.getContextPath()%>/left-menu"
										  class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</div>
            </div>

        </form>
    </div>
    
    <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-left-menu" id=updateLeftMenuForm name="updateLeftMenuForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Module <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h5>
                <div class="container">
	                <div class="row">
	                    <div class="col s12">
	                       <div class="row no-mar">
	                       	 <input id="value_old" type="hidden" name="value_old"  >
	                         <input id="update_dashboard_id" type="hidden" name="dashboard_id"  >
                                
	                         <div class="input-field col s6">
	                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(this.value)">
	                                <label for="value_new">Name<span class="required">*</span></label>
	                                <span id="value_newError" class="error-msg" ></span>
	                         </div>
	                         <div class="input-field col s12 m6">
	                        		<p class="searchable_label">Parent</p>
	                                <select id="update_parent_id" name="parent_id" class="searchable validate-dropdown req">
	                                	<option value="0">Select</option>	      
	                                	 <c:forEach var="obj" items="${parentList }">
		                                      <option value="${obj.dashboard_id }">${obj.dashboard_name }</option>
		                                 </c:forEach>                          	
	                                </select>
	                                <span id="update_parent_idError" class="error-msg" ></span>
	                            </div>
	                        </div>
	                        <div class="row no-mar">	                        	
	                        	<div class="input-field col s12">
	                                <input id="update_dashboard_url" name="dashboard_url" type="text" class="validate" >
	                                <label for="update_dashboard_url">Link</label>
	                                <span id="update_dashboard_urlError" class="error-msg" ></span>
	                        	</div>
	                        </div> 
	                        <div class="row no-mar">     
	                        	<div class="input-field col s12 m6">
	                                <input id="update_order" name="order" type="number" class="validate" >
	                                <label for="update_order">Order</label>
	                                <span id="update_orderError" class="error-msg" ></span>
	                        	</div>                     	
	                            <div class="input-field col s6">
	                        		<p class="searchable_label">Status<span class="required">*</span></p>
	                                <select id="update_status" name="status" class="searchable validate-dropdown req">
	                                	   <option value="">Select</option>
								           <option value="Active">Active</option>
								           <option value="Inactive">Inactive</option>
	                                </select>
	                                <span id="update_statusError" class="error-msg" ></span>
	                        	</div>
	                        </div>   
	                        <div class="row no-mar">	                        	
	                        	<div class="input-field col s12">
	                                <input id="update_source_field_name" name="source_field_name" type="text" class="validate" >
	                                <label for="update_source_field_name">Work Filter Name</label>
	                                <span id="source_field_nameError" class="error-msg" ></span>
	                        	</div>
	                        </div>
	                        <div class="row" style="margin-bottom: 20px;">
                                 
                                <div class="col s12 m12 l12">
                                    <div class="row fixed-width">
                                        <h6 class="center-align"  style="font-weight:600;">Left Side Menu Access Details</h6>
                                        <div class="table-inside">
                                            <table class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="w-fx">User Role </th>
                                                        <th class="w-fx">User Type </th>
                                                        <th class="fw-8p w-fx">User</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="detailsBody1">
	                                                	<tr>	
															<td class="input-field">
																<input type="hidden" id="access_user_roles1" name="access_user_roles" />
	                                                            <select id="user_roles1" name="user_roles" class="searchable" multiple data-placeholder="User Role" onchange="setAccessUserRoles(1)">
	                                                                <option value="">Select</option>
	                                                                <c:forEach var="obj" items="${user_roles }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id}</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
	                                                        <td class="input-field">
	                                                        	<input type="hidden" id="access_user_types1" name="access_user_types" />
	                                                            <select id="user_types1" name="user_types" class="searchable" multiple data-placeholder="User Type" onchange="setAccessUserTypes(1)">
	                                                                <option value="">Select</option>
			                                                        <c:forEach var="obj" items="${user_types }">
			                                                        	<option value="${obj.access_value_id }">${obj.access_value_id }</option>
			                                                        </c:forEach>
	                                                            </select>
	                                                        </td>
															<td class="input-field">
																<input type="hidden" id="access_users1" name="access_users" />
	                                                            <select id="users1" name="users" class="searchable" multiple data-placeholder="User"  onchange="setAccessUsers(1)">
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
                            <div class="row" style="margin-bottom: 20px;">
                                 
                                <div class="col s12 m12 l12">
                                    <div class="row fixed-width">
                                        <h6 class="center-align"  style="font-weight:600;">Archive Details</h6>
                                        <div class="table-inside">
                                            <table class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>Date </th>
                                                        <th>URL </th>
                                                        <th class="fw-8p">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="archiveBody1">
															<tr id="actionRows0">
																<td class="input-field">
									                                    <input name="archive_date" type="text" class="validate datepicker" id="archive_date1">
									                                    <button type="button" name="archive_date" class="datepicker-button"><i class="fa fa-calendar"></i></button>
									                                
		                                                        </td>
		                                                        <td class="input-field">
										                                 <input name="archive_url" type="text" class="validate" id="archive_url1">
										                                <span id="archive_urlError" class="error-msg" ></span>
										                            
		                                                        </td>
																<td>
																	<a onclick="removeAction('0');" class="btn red waves-effect waves-light"><i class="fa fa-close"></i></a>
																</td>
															</tr>													
                                                </tbody>
                                            </table>
                                            
                                            <table class="mdl-data-table">
												<tbody>
													<tr>
														<td colspan="6"><a
															type="button"
															class="btn waves-effect waves-light bg-m t-c"
															onclick="addRows()"> <i
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
	                                        <a href="<%=request.getContextPath()%>/left-menu" 
										     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</div>
            </div>

        </form>
    </div>
    <input type="hidden" id="ClickRowNo">
    <input type="hidden" id="rowNu" value="0">
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
	 <jsp:include page="../layout/footer.jsp"></jsp:include>
     <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="dashboard_id" id="dashboard_id" />
    </form>
     <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
<script src="/pmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    
    <script src="/pmis/resources/js/datepickerDepedency.js"></script>
    

    <script>

    
    function addRow() {        	
        var rowNo = $("#rowNo").val();
        var rNo = Number(rowNo)+1;
        var html = '<tr id="actionRow' + rNo + '">'
        + '<td class="input-field">'
        + '<input name="archive_date" type="text" class="validate datepicker" id="archive_date' + rNo + '"> <button type="button" name="archive_date" class="datepicker-button"><i class="fa fa-calendar"></i></button> '
        + '<span id="archive_dateError' + rNo + 'Error" class="error-msg"></span>'
        + '</td>'
        + '<td class="input-field">'
        + '<input name="archive_url" type="text" class="validate" id="archive_url' + rNo + '">'
        + '<span id="archive_urlError' + rNo + 'Error" class="error-msg"></span>' 
        + '</td>'
		+ '<td><a onclick="removeActions(' + rNo + ');" class="btn red waves-effect waves-light"><i class="fa fa-close"></i></a></td></tr>';
	
		$('#archiveBody').append(html);
        $("#rowNo").val(rNo);
        
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
    }
    
    function removeActions(rowNo){
    	$("#actionRow"+rowNo).remove();
    }
    //add-1 end
    function addRows() {        	
        var rowNu = $("#rowNu").val();
        var rNo = Number(rowNu)+1;
        var html = '<tr id="actionRows' + rNo + '">'
        + '<td class="input-field">'
        + '<input name="archive_date" type="text" class="validate datepicker" id="archive_date' + rNo + '"><button type="button" name="archive_date" class="datepicker-button"><i class="fa fa-calendar"></i></button>'
        + '<span id="archive_dateError' + rNo + 'Error" class="error-msg"></span>'
        + '</td>'
        + '<td class="input-field">'
        + '<input name="archive_url" type="text" class="validate" id="archive_url'+ rNo+'">'
        + '<span id="archive_urlError' + rNo + 'Error" class="error-msg"></span>' 
        + '</td>'
		+ '<td><a onclick="removeAction(' + rNo + ');" class="btn red waves-effect waves-light"><i class="fa fa-close"></i></a></td></tr>';
	
		$('#archiveBody1').append(html);
        $("#rowNu").val(rNo);
        
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
  
        
    }
    
    function removeAction(rowNu){
    	$("#actionRows"+rowNu).remove();
    }
    //add-2 end
    function setAccessUserRoles(index){
    	var value = $("#user_roles"+index).val();
    	if($.trim(value)!= '')
    	{ 
    			if(value.toString().indexOf(",")!=-1)
    			{
    				$("#access_user_roles"+index).val(value.toString().split(",").join("~$~")); 
    			}
    			else
   				{
    				$("#access_user_roles"+index).val(value.toString()); 
   				}
    	}
   		else
   		{
   			$("#access_user_roles"+index).val(value.toString()); 
   		}
	}
    
    function setAccessUserTypes(index){
    	var value = $("#user_types"+index).val();
    	
    	if($.trim(value)!= '')
    	{ 
    			if(value.toString().indexOf(",")!=-1)
    			{
    				$("#access_user_types"+index).val(value.toString().split(",").join("~$~")); 
    			}
    			else
   				{
    				$("#access_user_types"+index).val(value.toString()); 
   				}
    	}
   		else
   		{
   			$("#access_user_types"+index).val(value.toString()); 
   		} 	
    	
    }
    function setAccessUsers(index){
    	var value = $("#users"+index).val();
    	if($.trim(value)!= '')
    	{ 
    			if(value.toString().indexOf(",")!=-1)
    			{
    				$("#access_users"+index).val(value.toString().split(",").join("~$~")); 
    			}
    			else
   				{
    				$("#access_users"+index).val(value.toString()); 
   				}
    	}
   		else
   		{
   			$("#access_users"+index).val(value.toString()); 
   		} 	    	
    }
    
    
    	var filtersMap = new Object();
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            
     		var filters = window.localStorage.getItem("leftMenuFilters");
            
            if($.trim(filters) != '' && $.trim(filters) != null){
          	  var temp = filters.split('^'); 
          	  for(var i=0;i< temp.length;i++){
    	        	  if($.trim(temp[i]) != '' ){
    	        		  var temp2 = temp[i].split('=');
    		        	  if($.trim(temp2[0]) == 'parent_id_fk' ){
    		        		  getParentFilter(temp2[1]);
    		        	  }else if($.trim(temp2[0]) == 'status_id_fk' ){
    		        		  getStatusFilter(temp2[1]);
    		        	  }
    	        	  }
    	          }
              }
        
     		 var table = $('#left_menu_table').DataTable({
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
                paging:true,
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
      	leftMenuList();
        });
       
        
        
        function addInQueParent(parent_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('parent_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(parent_id_fk) != ''){
       	    	filtersMap["parent_id_fk"] = parent_id_fk;
        	}
        }  
        function addInQueStatus(status_id_fk){
        	Object.keys(filtersMap).forEach(function (key) {
       			if(key.match('status_id_fk')) delete filtersMap[key];
       		});
        	if($.trim(status_id_fk) != ''){
       	    	filtersMap["status_id_fk"] = status_id_fk;
        	}
        }
        function clearFilters() {
            $('#status_id_fk').val("");     
            $('#parent_id_fk').val("");     
            $('.searchable').select2();
            window.localStorage.setItem("leftMenuFilters",'');
            window.location.href= "<%=request.getContextPath()%>/left-menu";
        }
  	  
        
  	  function deleteRow(id){ 
  	  	$("#id").val(id);
  	  	showCancelMessage(); 
  	  }
  	  	
  	  function showCancelMessage() {
  	    	swal({
  		            title: "Are you sure?",
  		            text: "You will be changing the status of the record!",
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
  		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-left-menu');
  		    	    	$('#getForm').submit();
  		           }else {
  		                swal("Cancelled", "Record is safe :)", "error");
  		            }
  		        });
  	  }
  	  
  	  function getParentFilter(parent) {
        	$(".page-loader").show();
            var parent_id = $("#parent_id_fk").val();
            var status = $("#status_id_fk").val();
    		if ($.trim(parent_id) == "") {
            	$("#parent_id_fk option:not(:first)").remove();
            	var myParams = { parent_id: parent_id, status : status};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getParentFilterList",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                          	var selectedFlag = (parent == val.parent_id)?'selected':'';
    	                        $("#parent_id_fk").append('<option value="' + val.parent_id + '"'+selectedFlag+'>'   + val.parent_id +'</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			      $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);
    	   	     	  }
                });
            }else{
            	  $(".page-loader").hide();
            }
        }
  	  
  	  function getStatusFilter(statusVal) {
        	$(".page-loader").show();
            var parent_id = $("#parent_id_fk").val();
            var status = $("#status_id_fk").val();
    		if ($.trim(status) == "") {
            	$("#status_id_fk option:not(:first)").remove();
            	var myParams = { parent_id: parent_id, status : status};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getStatusFilterList",
                    data: myParams, cache: false,async: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                          	var selectedFlag = (statusVal == val.status)?'selected':'';
    	                        $("#status_id_fk").append('<option value="' + val.status + '"'+selectedFlag+'>'   + val.status +'</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			      $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);
    	   	     	  }
                });
            }else{
            	  $(".page-loader").hide();
            }
        }
  	  var arr = [];
	  function leftMenuList(){
			$(".page-loader-2").show();
			getStatusFilter('');
			getParentFilter('');
			var parent_id = $("#parent_id_fk").val();
	        var status = $("#status_id_fk").val();
			var filters = '';
	    	Object.keys(filtersMap).forEach(function (key) {
	    		//alert(filtersMap[key]);
	    		filters = filters + key +"="+filtersMap[key] + "^";
	    		window.localStorage.setItem("leftMenuFilters", filters);
				});
	      	table = $('#left_menu_table').DataTable();
	  		table.destroy();
	  		$.fn.dataTable.moment('DD-MMM-YYYY');
	  		table = $('#left_menu_table').DataTable({
	  			"order": [],
	      		"bStateSave": false,
	      		fixedHeader: true,
	              "fnStateSave": function (oSettings, oData) {
	                  localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
	              },
	              "fnStateLoad": function (oSettings) {
	                  return JSON.parse(localStorage.getItem('MRVCDataTables'));
	              },
	              columnDefs: [
	                    {
	                        targets: [0],
	                        className: 'mdl-data-table__cell--non-numeric',
	                        targets: 'no-sort', orderable: false,
	                        /* className: "last-column", targets: [1], */
	                    },
	                    { "width": "20px", "targets": 'last-column' },
	                ],
	              // "ScrollX": true,
	              "sScrollX": "100%",
	               "sScrollXInner": "100%",
	               paging:true,
	               "bScrollCollapse": true,
	               fixedColumns:   {
	            	    right: 1
	            	},
	              initComplete: function () {
	                  $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
	                  var input = $('.dataTables_filter input');
	                  self = this.api();
	                  $clearButton = $(	'<i class="fa fa-close" title="Reset">')
	                      .click(function() {		input.val(''); self.search(input.val()).draw(); 	});
	                  $('.dataTables_filter > label').append(	$clearButton); 
	              }
	          }).rows().remove().draw();
	  		
	  		table.state.clear();		
	  		var myParams = {parent_id: parent_id, status :status};
	  		$.ajax({url : "<%=request.getContextPath()%>/ajax/getLeftMenuList",type:"POST",
	  			data:myParams,async: false,
	  			success : function(data){    				
	  				if(data != null && data != '' && data.length > 0){    					
		             		$.each(data,function(key,val){
		             			//alert(val.archive_dates+'----'+val.archive_urls);
		                        //var paras = ''+val.dashboard_id +','+val.dashboard_name+','+val.parent_id +','+val.order+','+val.dashboard_url +','+val.status+'';
		                        var actions = '<input id="hdnArchive_dates'+$.trim(key)+'" type="hidden" name="hdnArchive_dates" value="'+val.archive_dates+'" ><input id="hdnArchive_urls'+$.trim(key)+'" type="hidden" name="hdnArchive_urls" value="'+val.archive_urls+'" ><input id="hdnUserRoles'+$.trim(key)+'" type="hidden" name="hdnUserRoles" value="'+val.user_roles+'" ><input id="hdnUserTypes'+$.trim(key)+'" type="hidden" name="hdnUserTypes" value="'+val.user_types+'" ><input id="hdnUsers'+$.trim(key)+'" type="hidden" name="hdnUsers" value="'+val.users+'"><a onclick="updateRow(\'' + val.dashboard_id + '\',\'' + val.dashboard_name + '\',\'' + val.parent_id + '\',\'' + val.order + '\',\'' + val.dashboard_url + '\',\'' + val.status + '\',\'' + val.source_field_name+ '\','+$.trim(key)+')" class="btn waves-effect waves-light bg-m t-c modal-trigger"> <i class="fa fa-pencil" ></i></a><a href="javascript:void(0);" onclick="deleteRow('+val.dashboard_id +');" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a>'
		                        var rowArray = [];    	                 
		                        arr.push($.trim(val.dashboard_name));
		                       	rowArray.push($.trim(val.dashboard_name));
		                       	rowArray.push($.trim(val.parent_id));
		                       	rowArray.push($.trim(val.order));
		                    	rowArray.push($.trim(val.status));
		                       	rowArray.push($.trim(actions)); 

		                        table.row.add(rowArray).draw( true ); 
		                        		                       
		    				});
		             		
		             		$(".page-loader-2").hide();
		    			}else{
		    				$(".page-loader-2").hide();
		    			}
	  			
	  		},error: function (jqXHR, exception) {
	  			$(".page-loader-2").hide();
	           	getErrorMessage(jqXHR, exception);
	       }});
		  }
        var flag = false; 
        function doValidate(value){
     	   var print_value = value;	
     	   var value = value.trim();
     	   value = value.toLowerCase();
     	   var validate = arr.length;
     	   var count  = 0;
     	  /*  var ek = $('.findLengths').map((_,el) => el.value).get(); */
     	   while(count < validate){
     		   var findVal = arr[count];
     		   findVal = findVal.toLowerCase();
     		   if(findVal == value){
     			   $('#dashboard_nameError').text(print_value+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#dashboard_nameError').text('');
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
     	   var validate = arr.length;
     	   var count  = 0;
     	   var valueOld = $('#value_old').val();
     	   /* var ek = $('.findLengths').map((_,el) => el.value).get(); */
     	   value = value.toLowerCase();
     	  /*  var s = Object.keys(ek).find(key => ek[key] === valueOld); */
     	  if ($.inArray(valueOld, arr) != -1)
     	 { var v = valueOld.toLowerCase();
     		 if(v == value){
     			var index = arr.indexOf(valueOld); // 1
     			arr.splice(arr.indexOf(valueOld),index);
     		 }
     	 }
     	  
     	   while(count < validate){
     		   var findVal = arr[count];
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
    		  var valueOld = $('#value_old').val();
    		  arr.push(valueOld);
    		 $('#value_newError').text('');
    		 $('#bttnUpdate').prop('disabled', false);
    		 updateFlag = true;
    	}
    	
      $("#addLeftMenuForm").submit(function (e) {
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			if (arr.length === 0) {
      				flag = ture;
      			}
      			 if(flag){
       				document.getElementById("addLeftMenuForm").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
             }
         })
      $("#updateLeftMenuForm").submit(function (e) {
         	 if(validator1.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			if (arr.length === 0) {
     				updateFlag = ture;
      			}
     			 if(updateFlag){
       				document.getElementById("updateLeftMenuForm").submit();	
       			 }
       			 $(".page-loader").hide();
       			 return false;
            }
        })
         var validator =  $('#addLeftMenuForm').validate({
        	 ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		 "dashboard_name": {
   			 		  required: true
         		 },"parent_id": {
   			 		  required: false
         		 },"dashboard_url": {
   			 		  required: false
         		 },"order": {
   			 		  required: false
         		 },"status": {
   			 		  required: true
         		 },"archive_url": {
 			 		  required: false
       		 }        		 
   			},messages: {
   		 		   "dashboard_name": {
   			 		  required: 'Required'
   			 	  },"parent_id": {
   			 		  required: 'Required'
   			 	  },"dashboard_url": {
   			 		  required: 'Required'
   			 	  },"order": {
   			 		  required: 'Required'
   			 	  },"status": {
   			 		  required: 'Required'
   			 	  },"archive_url": {
 			 		  required: 'Required'
   	       		 }     
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "dashboard_name" ){
   				     document.getElementById("dashboard_nameError").innerHTML="";
   			 	     error.appendTo('#dashboard_nameError');
   				 }else if(element.attr("id") == "parent_id" ){
   				     document.getElementById("parent_idError").innerHTML="";
   			 	     error.appendTo('#parent_idError');
   				 }else if(element.attr("id") == "dashboard_url" ){
   				     document.getElementById("dashboard_urlError").innerHTML="";
   			 	     error.appendTo('#dashboard_urlError');
   				 }else if(element.attr("id") == "order" ){
   				     document.getElementById("orderError").innerHTML="";
   			 	     error.appendTo('#orderError');
   				 }else if(element.attr("id") == "status" ){
   				     document.getElementById("statusError").innerHTML="";
   			 	     error.appendTo('#statusError');
   				 }else if(element.attr("id") == "archive_url" ){
   				     document.getElementById("archive_urlError").innerHTML="";
   			 	     error.appendTo('#archive_urlError');
   				 }
   	        }
         	
         }); 
         
         var validator1 =  $('#updateLeftMenuForm').validate({
        	 ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 "value_new": {
    			 		  required: true
          		 },"parent_id": {
    			 		  required: false
          		 },"update_dashboard_url": {
    			 		  required: false
          		 },"order": {
    			 		  required: false
          		 },"status": {
    			 		  required: true
          		 }
    			},messages: {
    		 		   "value_new": {
    			 		  required: 'Required'
    			 	  },"parent_id": {
    			 		  required: 'Required'
    			 	  },"update_dashboard_url": {
    			 		  required: 'Required'
    			 	  },"order": {
    			 		  required: 'Required'
    			 	  },"status": {
    			 		  required: 'Required'
    			 	  }
    	        },errorPlacement:function(error, element){
    	        	 if(element.attr("id") == "value_new" ){
    				     document.getElementById("value_newError").innerHTML="";
    			 	     error.appendTo('#value_newError');
    				 }else if(element.attr("id") == "update_parent_id" ){
    				     document.getElementById("update_parent_idError").innerHTML="";
    			 	     error.appendTo('#update_parent_idError');
    				 }else if(element.attr("id") == "update_dashboard_url" ){
    				     document.getElementById("update_dashboard_urlError").innerHTML="";
    			 	     error.appendTo('#update_dashboard_urlError');
    				 }else if(element.attr("id") == "update_order" ){
    				     document.getElementById("update_orderError").innerHTML="";
    			 	     error.appendTo('#update_orderError'); 
    				 }else if(element.attr("id") == "update_status" ){
    				     document.getElementById("update_statusError").innerHTML="";
    			 	     error.appendTo('#update_statusError');
    				 }
    	        }
          	 
          });
           
        $('input').change(function(){
  	           if ($(this).val() != ""){
  	               $(this).valid();
  	           }
  	     });
        $('.validate-dropdown').change(function(){
    	    if ($(this).val() != ""){
    	        $(this).valid();
    	    }
    	});
        function updateRow(id,name,parent,order,link,status,source_field_name,rowid) {
        	
        	$('#archive_date1').val("");
  	 		$('#archive_url1').val("");       	
        	
           	$('#user_roles1').val("");
           	$('#user_types1').val("");
           	$('#users').val("");
           	
	    	  if(link.length == 0 ||link == 'null'){
	    		 link = "";
	    	  }
	    	  if(order.length == 0 ||order == 'null'){
	    		 order = ""; 
	    	  }

   	          $('#onlyUpdateModal').modal('open');
	    	  $('#update_dashboard_id').val($.trim(id))
    	      $('#value_old').val($.trim(name))
    	      $('#onlyUpdateModal #value_new').val($.trim(name)).focus();
    	      $('#onlyUpdateModal #update_parent_id').val(parent);
    	      //$("select option:selected").removeAttr("selected");
    	      $('#onlyUpdateModal #update_order').val(order);
    	      //$('select[id^="update_parent_id"] option[value="'+ parent +'"]').attr("selected","selected");
    	      $('#onlyUpdateModal #update_order').val(order).focus();
    	      $('#onlyUpdateModal #update_dashboard_url').val(link).focus();
    	      $('#onlyUpdateModal #update_status').val(status);
    	      
    	      var uR=$('#hdnUserRoles'+rowid).val().split(',');
    	      var uT=$('#hdnUserTypes'+rowid).val().split(',');
    	      var uS=$('#hdnUsers'+rowid).val().split(',');
    	      
    	      $('#ClickRowNo').val(rowid);
    	      
   	      	  if($('#hdnArchive_dates'+rowid).val()!="" && $('#hdnArchive_urls'+rowid).val()!="")
   	    	  {
   	      			$("#archiveBody1 tr").not(":first").remove();
   	    	  	    var AD=$('#hdnArchive_dates'+rowid).val().split(',');
   	    	  		var AU=$('#hdnArchive_urls'+rowid).val().split(',');
   	    	  		
					var reverseStr=AD[0].split("-").reverse().join("-");
					
					if(AU[0]!="null" && AU[0]!="")
					{
	   	  	 			$('#archive_date1').val(reverseStr);
	    	  	 		$('#archive_url1').val(AU[0]); 
					}
   	  	 			$("#rowNu").val(1);

   	    	  	 	for(var t=2;t<=AD.length;t++)
   	    	  		 {

   	    	  	 				addRows();
   	    	  	 	
   	    	  		 }
   	    	  			for(var q=2;q<=AD.length;q++)
   	    	  			{
   	    	  				var lm=Number(q)-1;
							var reverseStr1=AD[lm].split("-").reverse().join("-");
							if(AU[0]!=null)
							{
	   	    	  	 			$('#archive_date'+q).val(reverseStr1);
	   	     	  	 			$('#archive_url'+q).val(AU[lm]);  	
							}
   	    	  			
   	    	  			}


   	    	     	  	 	
   	    	  	 	
   	    	  }
    	      
           	$('#user_roles1').val(uR).trigger('change');
           	$('#user_types1').val(uT).trigger('change');
           	$('#users1').val(uS).trigger('change');
    	      
    	      //$('select[id^="update_status"] option[value="'+ status +'"]').attr("selected","selected");
    	      $('.req').select2();
    	      
    	      $('#onlyUpdateModal #update_source_field_name').val(source_field_name).focus();
    	  }
    	  
    	  function deleteRow(val){
    	  	$("#dashboard_id").val(val);
    	  	showCancelMessage();
    	  }
    	  	
    	  
    	  function showCancelMessage() {
    	    	swal({
    		            title: "Are you sure?",
    		            text: "You will be changing the status of the record!",
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
    		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-left-menu');
    		    	    	$('#getForm').submit();
    		           }else {
    		                swal("Cancelled", "Record is safe :)", "error");
    		            }
    		        });
    		    }
    	  </script>

   </body>
</html>
