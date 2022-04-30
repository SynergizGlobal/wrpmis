<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Sub Resource Type</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
     <%-- <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css"> --%>
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
   <%--  <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/pmis/resources/css/rightColumnFixed.css"> --%>
    <style>
    	.w-br{
    		white-space: pre-line;
    		line-height: 13px;
    	}
		.r-flex{
		    position: relative;
		        right: -15em;
    			top: -1.5em;
		    }	
		.input-field {
		    position: relative;
		    margin-top: 0rem;
		    margin-bottom: 0rem;
		}	
		.align-center{
			    display: flex;
    			align-items: center;
		}
		.mt1em{
			margin-top: 1em;
		}
		.w50{
			width: 50% !important;
		}
		.mov {
    display: flex;
    align-items: center;
    justify-content: center;
}
		.pd0{padding: 0 !important;}
		.mt-c{
			    display: flex;
			    flex-wrap: wrap;
			    align-items: center;
			    padding-bottom: 15px;
		}
		
		 .box-grey{
			background-color: #eee;
		}
		.bg-none{
		 background: transparent !important;
		}
		.bd-none{
			border: 0 !important;
		}
		.br20px{
			border-radius: 20px;
		}
		.mdl-data-table tbody tr:hover {
		    background-color: transparent;
		}
		.mdl-data-table tbody tr, .mdl-data-table tbody tr td{
			padding: 1.5em;
		}
		@media(max-width: 575px){
			.align-center{
				display: block;
			}
			.w50{
			width: 100% !important;
			}
		}
    </style>
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Sub Resource Type</h6>
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
                            <div class="col m12 s12 center-align">
                                <a style="height: auto;" class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Sub Resource Type</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="sub_resource_type_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Resource Type</th>
                                            <th>Sub Resource Type</th>
                                            <c:forEach var="tObj" items="${subResourceTypeDetails.tablesList}" >
                                            	 <th>${tObj.captiliszedTableName }</th>
                                            </c:forEach>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${subResourceTypeDetails.dList1}" varStatus="indexs">
											<tr><td style="font-weight: 600;">
												${obj.resource_type_fk } 
											</td>
											<input type="hidden" id="resource_type_fk${indexs.count}" value="${obj.resource_type_fk }" name="resource_type_fk"   class="findLengths"/> 
											<input type="hidden" id="sub_resource_type${indexs.count}" value="${obj.sub_resource_type }" class="findLengths1"/>
											<input type="hidden" id="id${indexs.count}" value="${obj.id }" />
											<td class="w-br"> 
											    <c:forEach var="splt" items="${fn:split(obj.sub_resource_type,',')}">
												   &#9656; ${splt} <br>
												</c:forEach></td>
									
											<c:forEach var="tObj" items="${subResourceTypeDetails.tablesList}" varStatus="index">
												<td><c:forEach var="cObj" items="${subResourceTypeDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    		<c:choose>  
																    <c:when test="${cObj.sub_resource_type eq obj.sub_resource_type }"> 
																      	 ( ${cObj.count } )   
																    </c:when>  
																    <c:otherwise>  
																    </c:otherwise>    
															</c:choose>
														</c:when>
														<c:otherwise> 
													   </c:otherwise>
												</c:choose>
												</c:forEach></td>
                                            </c:forEach>
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger " href="#"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${subResourceTypeDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.sub_resource_type eq obj.sub_resource_type }"> 
												      	<a onclick="deleteRow('${indexs.count}');"  class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
												      	</a>
												    </c:when>  
												    <c:otherwise>  
												    </c:otherwise>   
												</c:choose>  
 											 </c:forEach>
 											</td></tr>											   
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
		 <form action="<%=request.getContextPath() %>/add-sub-resource-type" id="subResourceType" name="subResourceType" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header">Add Sub Resource Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m1 hide-on-small"></div>
                    <div class="col m12 s12">
                        <%-- <div class="row no-mar align-center">
                           <!--  <div class="input-field col s12 m6">
                                <input id="risk_area" type="text" class="validate">
                                <label for="risk_area">Risk Area</label>
                            </div> -->
                              <div class="col s12 m6 input-field">
                                 <p class="searchable_label">Resource Type</p>
                                 <select class="searchable validate-dropdown" name="resource_type_fk" id="area" onchange="doValidate(this.value,null)">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${resourceTypeList }">
		                                      <option value="${obj.resource_type }">${obj.resource_type }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="resource_type_fkError" class="error-msg" ></span>
                              </div>                            
                        <!-- </div>
                        <div class="row"> -->
                        <div class="input-field col s12 m6">
	                                	<input id="sub_resource_type" type="text" name="sub_resource_type" class="validate" placeholder="Sub Resource Type">
		                               <!--  <label for="sub_resource_type">Sub Resource Type</label> -->
		                                <span id="sub_resource_typeError" class="error-msg" ></span>                           
                            </div>
                              
                            <div  style="text-align:center;" id="DivError"></div>
                        </div> --%>
                      
							<div id="listOfLocationsAdd"> 
		                        <div id="archiveBody"> 
		                        <div class="row no-mar mt-c" id="actionRow0">
									<div class="input-field col s11 m5">
										<!-- <input id="rr_location_fk" name="rr_location_fk" type="text" class="validate"> -->
										<p class="searchable_label"> Resource Type</p>
										<select name="resource_type_fk" id="area" class="searchable validate-dropdown">
											<option value="">Select </option>
											 <c:forEach var="obj" items="${resourceTypeList }">
													  <option value="${obj.resource_type }">${obj.resource_type }</option>
											  </c:forEach>
										</select>
										<span id="resource_type_fkError" class="error-msg" ></span>
									</div>
									<div class="input-field col s11 m5 box-grey" id="subArchiveBody0">
										<div id="subActionRow0">
										<div class="col s10 pd0">
										<input id="sub_resource_type" name="sub_resource_type" type="text" class="validate" placeholder="Sub Resource Type">
										<!-- <label for="sub_resource_type"> Sub Location </label> -->
										<span id="sub_resource_typeError" class="error-msg" ></span>	
									</div>
									<span class="col s1"><a onclick="subRemoveAction('0');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span>
									</div>
								</div>
								<span class="col s1 m1 input-field"><a onclick="removeAction('0');" class="btn red waves-effect waves-light mt1em"><i class="fa fa-close"></i></a></span>
								<table class="mdl-data-table col offset-m5 w50 s12 bg-none bd-none">
														<tbody>
															<tr class="bd-none">
																<td colspan="6" class="bd-none"><a
																	type="button"
																	class="btn waves-effect waves-light bg-m t-c br20px"
																	onclick="addSubRow('0')"> <i
																		class="fa fa-plus"></i>
																</a>
															</tr>
														</tbody>
													</table>
													<input type="hidden" id="rowNo" name="rowNo" value="0" />
											</div>
					                        </div> </div>
					                        <table class="mdl-data-table col s12">
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
												<input type="hidden" id="rowNo1" name="rowNo1" value="0" />
                        <div class="row no-mar">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addSubResourceType()" class="btn waves-effect waves-light bg-m " id="bttn">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/sub-resource-type"
									     class="btn waves-effect waves-light bg-s modal-action modal-close " style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>

            </div>

        </form>
    </div>

   <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-sub-resource-type" id="updateSubResourceTypeForm" name="updateSubResourceTypeForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h5 class="modal-header bg-m">Update Sub Resource Type <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m1 hide-on-small"></div>
                    <div class="col m12 s12">
                        
                       <%-- <div class="row no-mar">
                           <!--  <div class="input-field col s12 m6">
                                <input id="risk_area" type="text" class="validate">
                                <label for="risk_area">Risk Area</label>
                            </div> -->
                              <div class="col s12 m6 input-field">
                                 <p class="searchable_label">Resource Type</p>
                                 <select class="searchable validate-dropdown" name="resource_type_fk_new" id="resource_type_fk_new" onchange="doValidateUpdate(null,null)">
                                     <option value="">Select</option>
                                      <c:forEach var="obj" items="${resourceTypeList }">
		                                      <option value="${obj.resource_type }" id="${obj.resource_type }" >${obj.resource_type }</option>
		                              </c:forEach>
                                 </select>
                                 <span id="resource_type_fkError" class="error-msg" ></span>
                              </div>                            
                        <!-- </div>
                        <div class="row"> -->
                        <!-- <div class="input-field col s12 m6">
                                <input id="value_new" type="text" name="value_new" class="validate" onkeyup="doValidateUpdate(null,null)">
                                <input id="value_old" type="hidden" name="value_old"  >
                                <label for="value_new">Sub Resource Type</label>
                                <span id="sub_resource_typeError" class="error-msg" ></span>
                            </div> -->
                            <div class="col s12 m6" id="archiveBody1">
                                <div id="actionRows0">
                                	<div class="input-field col s10 pd0">
	                                	<input id="sub_resource_type" type="text" name="sub_resource_type" class="validate" placeholder="Sub Resource Type">
		                               <!--  <label for="sub_resource_type">Sub Resource Type</label> -->
		                                <span id="sub_resource_typeError" class="error-msg" ></span>
		                                
                                	</div>
                                	<span class="col s1"><a onclick="removeActions('0');" class="btn red waves-effect waves-light mt1em"><i class="fa fa-close"></i></a></span>
                                </div>
                              
                            </div>
                              
                            <div  style="text-align:center;" id="DivError"></div>
                        </div>
                        <table class="mdl-data-table col offset-m6 w50 s12">
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
													<input type="hidden" id="rowNu" name="rowNo"
														value="${fn:length(formDetails.accessPermissions) }" />
												</c:when>
												<c:otherwise>
													<input type="hidden" id="rowNu" name="rowNo" value="0" />
												</c:otherwise>
											</c:choose>
							start --%>
							<div id="listOfLocations">
                         <div id="archiveBodyupdate">
                           <input id="resource_type_fk_old" name="resource_type_fk_old" type="hidden" > 
                          <input id="ids" name="id" type="hidden" > 
                          <input id="value_old" name="value_old" type="hidden" > 
             					<div class="row no-mar mt-c" id="actionRows0"><div class="input-field col s11 m5">
						<p class="searchable_label"> Resource Type</p>
						
						<select name="resource_type_fk_new" id="resource_type_fk_new" class="searchable validate-dropdown">
							<option value="">Select </option>
							 <c:forEach var="obj" items="${resourceTypeList }">
									  <option value="${obj.resource_type }" id="${obj.resource_type }" >${obj.resource_type }</option>
							  </c:forEach>
						</select>
						<span id="resource_type_fk0Error" class="error-msg" ></span>
					</div>
					<div class="input-field col s11 m5 box-grey" id="subArchiveBodys0">
						<div id="subActionRows0">
				
						</div>
				</div>
				<span class="col s1 m1 input-field"><a onclick="removeActions(0);" class="btn red waves-effect waves-light mt1em"><i class="fa fa-close"></i></a></span>
				<table class="mdl-data-table col offset-m5 w50 s12 bg-none bd-none">
								<tbody>
									<tr class="bd-none">
										<td colspan="6" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c br20px" onclick="addSubRows(0)"> <i class="fa fa-plus"></i>
										</a>
									</tr>
								</tbody>
							</table>
							   <input type="hidden" id="rowNu" name="rowNu" value="1" />
							</div>
	                        </div>
                         </div>
                        <table class="mdl-data-table col s12">
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
							<input type="hidden" id="rowNu1" name="rowNu1" value="0" />
                            <div  style="text-align:center;" id="DivUpdateError"></div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateubResourceType()" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close "
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/sub-resource-type"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>

            </div>

        </form>
    </div>
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="sub_resource_types" />
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
    function addRow() {        	
        var rowNo = $("#rowNo1").val();
        var rNo = Number(rowNo)+1;
        var randNum = Math.floor((Math.random() * 9999));
        var html = '<div class="row no-mar mt-c" id="actionRow' + rNo + '">'
        + '<div class="input-field col s11 m5"><p class="searchable_label"> Resource Type</p>'
        + '<select name="resource_type_fk" id="area' + rNo +'" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${resourceTypeList }">'
        + '<option value="${obj.resource_type }">${obj.resource_type }</option>'
        + '</c:forEach></select>'
        + '<span id="resource_type' + rNo +'_fkError" class="error-msg" ></span></div>'
        + '<div class="input-field col s11 m5 box-grey"  id="subArchiveBody' + rNo +'">'
        + '<div id="subActionRow' + rNo +randNum +'"><div class="col s10 pd0">'
        + '<input id="sub_resource_typess' + rNo +'" name="sub_resource_type" type="hidden" value="_"> '
        + '<input id="sub_resource_type' + rNo +'" name="sub_resource_type" type="text" class="validate" placeholder="Sub Resource Type"> '
        + '<span id="sub_resource_type' + rNo + 'Error" class="error-msg"></span></div>'
        + '<span class="col s1"><a onclick="subRemoveAction(' + rNo +randNum + ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div></div>'
        + '<span class="col s1 m1 input-field"><a onclick="removeAction(' + rNo + ');" class="btn red waves-effect waves-light mt1em"><i class="fa fa-close"></i></a></span>'
        + '<table class="mdl-data-table col offset-m5 w50 s12 bg-none bd-none"><tbody><tr class="bd-none"><td colspan="6" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c br20px" onclick="addSubRow(' + rNo + ')"> <i class="fa fa-plus"></i></a></tr></tbody></table></div>';
 		$('#listOfLocationsAdd').append('<div id="archiveBody' + rNo + '" > </div>');
        
        $('#archiveBody' + rNo).append(html);
        $("#rowNo1").val(rNo);
        
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
    }

    function removeAction(rowNo){
        $("#actionRow"+rowNo).remove();
    }
    function addSubRow(idNo) {        	
        var rowNo = $("#rowNo").val();
        var rNo = Number(rowNo)+1;
        var html = '<div id="subActionRow' + rNo + '">'
        + '<div class="input-field col s10 pd0">'
        + '<input id="sub_resource_type' + rNo +'" name="sub_resource_type" type="text" class="validate" placeholder="Sub Resource Type"> '
        + '<span id="sub_resource_type' + rNo + 'Error" class="error-msg"></span>'
        + '</div>'
        + '<span class="col s1"><a onclick="subRemoveAction(' + rNo + ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div>';
      
        $('#subArchiveBodyAdd').append('<div id="subArchiveBody' + rNo + '" > </div>');
        
        $('#subArchiveBody' + idNo).append(html);
        $("#rowNo").val(rNo);
        
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
    }

  
    function subRemoveAction(rowNo){
        $("#subActionRow"+rowNo).remove();
    }
    //add-1 end
     function addRows() {        	
        var rowNo = $("#rowNu1").val();
        var rNo = Number(rowNo)+1;
        var randNum = Math.floor((Math.random() * 9999));
        var html = '<div class="row no-mar mt-c" id="actionRows' + rNo + '">'
        + '<div class="input-field col s11 m5"><p class="searchable_label"> Resource Type</p>'
        + '<select name="resource_type_fk_new" id="area' + rNo +'" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${resourceTypeList }">'
        + '<option value="${obj.resource_type }">${obj.resource_type }</option>'
        + '</c:forEach></select>'
        + '<span id="resource_type' + rNo +'_fkError" class="error-msg" ></span></div>'
        + '<div class="input-field col s11 m5 box-grey"  id="subArchiveBodys' + rNo +'">'
        + '<div id="subActionRows' + rNo +randNum +'"><div class="col s10 pd0">'
        + '<input id="sub_resource_typess' + rNo +'" name="sub_resource_type_new" type="hidden" value="_"> '
        + '<input id="sub_resource_type' + rNo +'" name="sub_resource_type_new" type="text" class="validate" placeholder="Sub Resource Type"> '
        + '<span id="sub_resource_type' + rNo + 'Error" class="error-msg"></span></div>'
        + '<span class="col s1"><a onclick="subRemoveActions(' + rNo +randNum + ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div></div>'
        + '<span class="col s1 m1 input-field"><a onclick="removeActions(' + rNo + ');" class="btn red waves-effect waves-light mt1em"><i class="fa fa-close"></i></a></span>'
        + '<table class="mdl-data-table col offset-m5 w50 s12 bg-none bd-none"><tbody><tr class="bd-none"><td colspan="6" class="bd-none"><a type="button" class="btn waves-effect waves-light bg-m t-c br20px" onclick="addSubRows(' + rNo + ')"> <i class="fa fa-plus"></i></a></tr></tbody></table></div>';

        $('#listOfLocations').append('<div id="archiveBodyupdate' + rNo + '" > </div>');
        
        $('#archiveBodyupdate' + rNo).append(html);
        $("#rowNu1").val(rNo);
        
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
    }

    function removeActions(rowNo){
        $("#actionRows"+rowNo).remove();
    }
    function addSubRows(idNo) {        	
        var rowNo = $("#rowNu").val();
        var rNo = Number(rowNo)+1;
        var html = '<div id="subActionRows' + rNo + '">'
        + '<div class="input-field col s10 pd0">'
        + '<input id="sub_resource_type' + rNo +'" name="sub_resource_type_new" type="text" class="validate" placeholder="Sub Resource Type"> '
        + '<span id="sub_resource_type' + rNo + 'Error" class="error-msg"></span>'
        + '</div>'
        + '<span class="col s1"><a onclick="subRemoveActions(' + rNo + ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div>';
        $('#subArchiveBodyUpdate').append('<div id="subArchiveBody' + rNo + '" > </div>');
        $('#subArchiveBodys' + idNo).append(html);
        $("#rowNu").val(rNo);
        
        $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
    }

  
    function subRemoveActions(rowNo){
        $("#subActionRows"+rowNo).remove();
    }
    //add-2 end
        $(document).ready(function () {
        	 $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });

            var table = $('#sub_resource_type_table').DataTable({
            	"order": [],
                columnDefs: [
                    {
                        targets: [0],
                       /*  className: 'mdl-data-table__cell--non-numeric', */
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                "sScrollX": "100%",
                paging:false,
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
        function doValidate(value,value1){
           var value = $('#area').val();
           var value1 = $('#sub_resource_type').val();
           value = value.trim();
           value1 = value1.trim();
           var print_value = value;	
     	   var print_value2 = value1;
           var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
           var s = Object.keys(ek).find(key => ek[key] === value);
           if(value != null){ value = value.toLowerCase();}
           if(value1 != null){ value1 = value1.toLowerCase();}
         
     	  
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	  
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     			if(findVal != null){ findVal = findVal.toLowerCase(); }
     			if(findVal2 != null){ findVal2 = findVal2.toLowerCase(); }
     			
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
   				   $('#resource_type_fkError').text('');
   				   $('#sub_resource_typeError').text('');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
        			   $('#DivError').text('');
        			   $('#resource_type_fkError').text('');
        			   $('#sub_resource_typeError').text('');
        			   $('#bttn').prop('disabled', false); 
        			   flag = true;
     			  
     		   }
     		   count++;
     	   }
        }
        var updateFlag = true;
         function doValidateUpdate(value,value1){
           var value = $('#resource_type_fk_new').val();
           var value1 = $('#value_new').val();
           value = value.trim();
           value1 = value1.trim(); 
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld  = $('#department_old').val()
           var valueOld2 = $('#department_name_old').val()
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths1').map((_,el) => el.value).get();
     	   value = value.toLowerCase();
     	   value1 = value1.toLowerCase();
     	   var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	   delete ek[s];
     	   delete ak[s1];
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			 if(findVal != null){ findVal = findVal.toLowerCase();}
  			 if(findVal2 != null){ findVal2 = findVal2.toLowerCase();}
   		     if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
  				   $('#resource_type_fkError').text('');
 			   	   $('#value_newError').text('');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			 
       			       $('#DivUpdateError').text('');
       			       $('#resource_type_fkError').text('');
       			   	   $('#value_newError').text('');
        			   $('#bttnUpdate').prop('disabled', false);
        			   updateFlag = true;
        			   
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function addSubResourceType(){
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			document.getElementById("subResourceType").submit();	
           }
        }
       
        function updateubResourceType(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#onlyUpdateModal").modal();
     			document.getElementById("updateSubResourceTypeForm").submit();	
          }
       }
        
        var validator = $('#subResourceType').validate({
         ignore: ":hidden:not(.validate-dropdown)",
       	 rules: {
       		 	"sub_resource_type": {
   			 		  required: true
       			 },"resource_type_fk": {
   			 		  required: true
       			 }
   			},messages: {
   		 		 "sub_resource_type": {
   			 		  required: 'Required'
   			 	 }, "resource_type_fk": {
   			 		  required: 'Required'
   			 	 }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "sub_resource_type" ){
   				     document.getElementById("sub_resource_typeError").innerHTML="";
   			 	     error.appendTo('#sub_resource_typeError');
   			   }else if(element.attr("id") == "area" ){
   				     document.getElementById("resource_type_fkError").innerHTML="";
   			 	     error.appendTo('#resource_type_fkError');
   			   }
   	        }
        });
       
        var validator1 = $('#updateSubResourceTypeForm').validate({
            ignore: ":hidden:not(.validate-dropdown)",
          	 rules: {
          		 	"value_new": {
      			 		  required: true
          			 }, "resource_type_fk_new": {
    			 		  required: 'true'
     			 	 }
      			},messages: {
      		 		 "value_new": {
      			 		  required: 'Required'
      			 	 }, "resource_type_fk_new": {
      			 		  required: 'Required'
       			 	 }
      	        },errorPlacement:function(error, element){
      	        	 if(element.attr("id") == "value_new" ){
      				     document.getElementById("value_newError").innerHTML="";
      			 	     error.appendTo('#value_newError');
      			   }else if(element.attr("id") == "resource_type_fk_new" ){
      			     document.getElementById("resource_type_fkError").innerHTML="";
      		 	     error.appendTo('#resource_type_fkError');
      		  }
      	        }
          });


        $('input').change(function(){
   	           if ($(this).val() != ""){
   	               $(this).valid();
   	           }
   	  });

       $('select').change(function(){
           if ($(this).val() != ""){
               $(this).valid();
           }
       });
      
       $("#sub_resource_type_table td:not(:last-child)").each(function(){ 
    	    $(this).text($.trim($(this).text()));
    	})
       
   function updateRow(no) {
    	  var html = '';
    	  $("#subArchiveBodys0").empty();
    	  var id = $('#id'+no).val();
         // var sub_resource_type = $('#sub_resource_typeId'+no).val();
          var resource_type_fk = $('#resource_type_fk'+no).val();
          $('#value_old').val($.trim(sub_resource_type));
          $('#resource_type_fk_old').val($.trim(resource_type_fk));
          $('#ids').val($.trim(id));
          if ($.trim(resource_type_fk) != "") {
              var myParams = { resource_type_fk: resource_type_fk };
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getResourceType",
                  data: myParams, cache: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
                        html = '<div id="subActionRows'+i+'">'+
							'<div class="col s10 pd0 br20px">'+
							'<input id="sub_resource_type_new'+i+'" name="sub_resource_type_new" type="text" class="validate" placeholder="Sub Resource Type" value="'+val.sub_resource_type+'">'+
							'<span id="sub_resource_type'+i+'Error" class="error-msg" ></span>	'+
						'</div>'+
						'<span class="col s1"><a onclick="subRemoveActions('+i+');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span>'+
						'</div><br>';
	                        	$('#rowNu').val(data.length);
	                        	$('#ids').val(id);
                                $("#subArchiveBodys0").append(html);
                                $('#onlyUpdateModal #sub_resource_type_new'+i).focus();
                               // $('#onlyUpdateModal #sub_resource_type_new'+i).val($.trim(sub_resource_type)).focus();
                                $('#onlyUpdateModal #resource_type_fk_new').val($.trim(resource_type_fk)).focus(); 
                               // $('#onlyUpdateModal #rr_location_fk_new').val($.trim(rr_location_fk)); 
                                $('select[name^="resource_type_fk_new"] option[value="'+ resource_type_fk +'"]').attr("selected","selected");
                          });
                      }
                      $('.searchable').select2();
                      $(".page-loader").hide();
                  }
              });
          }else{
          	$(".page-loader").hide();
          }
          $('#onlyUpdateModal').modal('open');
       
          $('.searchable').select2();
      }
      
      function deleteRow(val){
      	$("#resource_type_fks").val(val);
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
      		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-sub-resource-type');
      		    	    	$('#getForm').submit();
      		           }else {
      		                swal("Cancelled", "Record is safe :)", "error");
      		            }
      		        });
      		    }
    </script>

</body>

</html>