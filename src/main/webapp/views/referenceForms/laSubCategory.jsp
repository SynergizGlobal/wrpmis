<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <title>Land Acquisition Sub Category</title>
    <%-- <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css"> --%>
    <link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/wrpmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/referenceformitem.min.css">
   <%--  <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rightColumnFixed.css"> --%>
    <style type="text/css">
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
			padding: 1em;
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
                            <h6>  Sub Category</h6>
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
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add  Sub Category</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="la_subcategory_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Category</th>
                                            <th>Sub Category</th>
                                            <%-- <c:forEach var="tObj" items="${landAcquisitionSubCategoryDetails.tablesList}" >
                                            	 <th>${tObj.captiliszedTableName }</th>
                                            </c:forEach> --%>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="obj" items="${landAcquisitionSubCategoryDetails.dList1}" varStatus="indexs">
											<tr><input type="hidden" id="id${indexs.count}" value="${obj.id }" />
											<td style="font-weight: 600;">
												<input type="hidden" id="la_category_fkId${indexs.count}" value="${obj.la_category_fk }" /> 
												${obj.la_category_fk }</td>
											<td>
												<input type="hidden" id="la_sub_categoryId${indexs.count}" value="${obj.la_sub_category }" /> 
												<c:forEach var="splt" items="${fn:split(obj.la_sub_category,',')}">
												   &#9656; ${splt} <br>
												</c:forEach></td>
							<%-- 				<c:forEach var="tObj" items="${landAcquisitionSubCategoryDetails.tablesList}" varStatus="index">
												 
												<td><c:forEach var="cObj" items="${landAcquisitionSubCategoryDetails.countList}" >
												<c:choose> 
													    <c:when test="${tObj.tName eq cObj.tName }"> 
													    
													    		<c:choose>  
																    <c:when test="${cObj.la_category eq obj.id }"> 
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
                                            </c:forEach> --%>
											<td class="last-column "><a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger " href="#"> <i class="fa fa-pencil" ></i></a>
										 	<c:forEach var="oSbj"  items="${landAcquisitionSubCategoryDetails.dList}" varStatus="indexx"> 
												 
												<c:choose>  
												    <c:when test="${oSbj.id eq obj.id }"> 
												      	<a onclick="deleteRow('${ oSbj.la_category_fk }');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i>
												      	  <%-- <input name="bg_type" value="${oSbj.bg_type}"/> --%>
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
		 <form action="<%=request.getContextPath() %>/add-la-sub-category" id="addLaSubCategoryForm" name="addLaSubCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add  Sub Category <span
                        class="right modal-action modal-close"><span class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m12 s12">
                        <%-- <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <!-- <input id="la_category_fk" name="la_category_fk" type="text" class="validate"> -->
                                <p class="searchable_label"> Category</p>
                                <select name="la_category_fk" id="la_category_fk" class="searchable validate-dropdown">
                                    <option value="">Select </option>
                                     <c:forEach var="obj" items="${LACategorysList }">
		                                      <option value="${obj.la_category }">${obj.la_category }</option>
		                              </c:forEach>
                                </select>
                                <span id="la_category_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="la_sub_category" name="la_sub_category" type="text" class="validate">
                                <label for="la_sub_category"> Sub Category </label>
                                <span id="la_sub_categoryError" class="error-msg" ></span>
                            </div>
                        </div> --%>
                        <div id="listOfLocationsAdd"> 
                        <div id="archiveBody">
                        <div class="row no-mar mt-c" id="actionRow0">
							<div class="input-field col s11 m5">
								<!-- <input id="rr_location_fk" name="rr_location_fk" type="text" class="validate"> -->
								<p class="searchable_label"> Category</p>
								<select name="la_category_fk" id="la_category_fk" class="searchable validate-dropdown">
									<option value="">Select </option>
									 <c:forEach var="obj" items="${LACategorysList }">
											  <option value="${obj.la_category }">${obj.la_category }</option>
									  </c:forEach>
								</select>
								<span id="la_category_fkError" class="error-msg" ></span>
							</div>
							<div class="input-field col s11 m5 box-grey" id="subArchiveBody0">
								<div id="subActionRow0">
								<div class="col s10 pd0">
								<input id="la_sub_category" name="la_sub_category" type="text" class="validate" placeholder="Sub Category">
								<!-- <label for="rr_sub_location"> Sub Location </label> -->
								<span id="la_sub_categoryError" class="error-msg" ></span>	
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
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="addLASubCategory()" class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                  
                                         <a href="<%=request.getContextPath()%>/la-sub-category"
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
    
     <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-la-sub-category" id=updateLaSubCategoryForm name="updateLaSubCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update  Status <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m12 s12">
                       <%-- <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="ids" name="id" type="hidden" class="validate"> 
                                <p class="searchable_label"> Category</p>
                                <input id="la_category_fk_old" type="hidden" name="la_category_fk_old"  >
                                <select name="la_category_fk_new" id="la_category_fk_new" class="searchable validate-dropdown">
                                    <option value="">Select </option>
                                     <c:forEach var="obj" items="${LACategorysList }">
		                                      <option value="${obj.la_category }">${obj.la_category }</option>
		                              </c:forEach>
                                </select>
                                <span id="la_category_fk_newError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="la_sub_category_new" name="la_sub_category_new" type="text" class="validate">
                                <input id="la_sub_category_old" type="hidden" name="la_sub_category_old"  >
                                <label for="la_sub_category"> Sub Category </label>
                                <span id="la_sub_category_newError" class="error-msg" ></span>
                            </div>
                        </div> --%>
                        <div id="listOfLocations">
                         <div id="archiveBodyupdate">
                           <input id="la_category_fk_old" name="la_category_fk_old" type="hidden" > 
                          <input id="ids" name="id" type="hidden" > 
                          <input id="value_old" name="value_old" type="hidden" > 
             					<div class="row no-mar mt-c" id="actionRows0"><div class="input-field col s11 m5">
						<p class="searchable_label"> Category</p>
						
						<select name="la_category_fk_new" id="la_category_fk_new" class="searchable validate-dropdown">
							<option value="">Select </option>
							 <c:forEach var="obj" items="${LACategorysList }">
									  <option value="${obj.la_category }">${obj.la_category }</option>
							  </c:forEach>
						</select>
						<span id="la_category_fk0Error" class="error-msg" ></span>
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
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" onclick="updateLASubCategory()"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/la-sub-category"
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
   <!--  <div id="errorModal" class="modal">
        <div class="modal-content">
            <h6 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h6>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="la_category_fk" id="id" />
    </form>
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
<script src="/wrpmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script>
    function addRow() {        	
        var rowNo = $("#rowNo1").val();
        var rNo = Number(rowNo)+1;
        var randNum = Math.floor((Math.random() * 1000));
        var html = '<div class="row no-mar mt-c" id="actionRow' + rNo + '">'
        + '<div class="input-field col s11 m5"><p class="searchable_label"> Category</p>'
        + '<select name="la_category_fk" id="la_category' + rNo +'_fk" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${LACategorysList }">'
        + '<option value="${obj.la_category }">${obj.la_category }</option>'
        + '</c:forEach></select>'
        + '<span id="la_category' + rNo +'_fkError" class="error-msg" ></span></div>'
        + '<div class="input-field col s11 m5 box-grey"  id="subArchiveBody' + rNo +'">'
        + '<div id="subActionRow' + rNo +randNum+'"><div class="col s10 pd0">'
        + '<input id="la_sub_categoryss' + rNo +'" name="la_sub_category" type="hidden" value="_"> '
        + '<input id="la_sub_category' + rNo +'" name="la_sub_category" type="text" class="validate" placeholder="Sub Category"> '
        + '<span id="la_sub_category' + rNo + 'Error" class="error-msg"></span></div>'
        + '<span class="col s1"><a onclick="subRemoveAction(' + rNo +randNum+ ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div></div>'
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
        + '<input id="la_sub_category' + rNo +'" name="la_sub_category" type="text" class="validate" placeholder="Sub Category"> '
        + '<span id="la_sub_category' + rNo + 'Error" class="error-msg"></span>'
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
        var randNum = Math.floor((Math.random() * 1000));
        var html = '<div class="row no-mar mt-c" id="actionRows' + rNo + '">'
        + '<div class="input-field col s11 m5"><p class="searchable_label"> Category</p>'
        + '<select name="la_category_fk_new" id="la_category' + rNo +'_fk" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${LACategorysList }">'
        + '<option value="${obj.la_category }">${obj.la_category }</option>'
        + '</c:forEach></select>'
        + '<span id="la_category' + rNo +'_fkError" class="error-msg" ></span></div>'
        + '<div class="input-field col s11 m5 box-grey"  id="subArchiveBodys' + rNo +'">'
        + '<div id="subActionRows' + rNo +randNum+'"><div class="col s10 pd0">'
        + '<input id="la_sub_categoryss' + rNo +'" name="la_sub_category_new" type="hidden" value="_"> '
        + '<input id="la_sub_category' + rNo +'" name="la_sub_category_new" type="text" class="validate" placeholder="Sub Category"> '
        + '<span id="la_sub_category' + rNo + 'Error" class="error-msg"></span></div>'
        + '<span class="col s1"><a onclick="subRemoveActions(' + rNo +randNum+ ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div></div>'
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
        + '<input id="la_sub_category' + rNo +'" name="la_sub_category_new" type="text" class="validate" placeholder="Sub Category"> '
        + '<span id="la_sub_category' + rNo + 'Error" class="error-msg"></span>'
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
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            $('#remarks').characterCounter();
            // adding table data into table start
          /*   
            // adding table data into table ends */

            var table = $('#la_subcategory_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
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
            });
        });

        function addLASubCategory(){
         	 if(validator.form()){ 
     			$(".page-loader").show();
     			$("#addUpdateModal").modal();
     			document.getElementById("addLaSubCategoryForm").submit();	
          }
       }
        
        function updateLASubCategory(){
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
    			document.getElementById("updateLaSubCategoryForm").submit();	
         }
      }
      
       var validator = $('#addLaSubCategoryForm').validate({
        ignore: ":hidden:not(.validate-dropdown)",
      	 rules: {
      		 	"la_category_fk": {
  			 		  required: true
      			 },"la_sub_category": {
  			 		  required: true
      			 }
  			},messages: {
  		 		 "la_category_fk": {
  			 		  required: 'Required'
  			 	 }, "la_sub_category": {
  			 		  required: 'Required'
  			 	 }
  	        },errorPlacement:function(error, element){
  	        	 if(element.attr("id") == "la_category_fk" ){
  				     document.getElementById("la_category_fkError").innerHTML="";
  			 	     error.appendTo('#la_category_fkError');
  			   }else if(element.attr("id") == "la_sub_category" ){
  				     document.getElementById("la_sub_categoryError").innerHTML="";
  			 	     error.appendTo('#la_sub_categoryError');
  			   }
  	        }
       });
      
       var validator1 = $('#updateLaSubCategoryForm').validate({
           ignore: ":hidden:not(.validate-dropdown)",
         	 rules: {
         		 	"la_category_fk_new": {
     			 		  required: true
         			 },"la_sub_category_new": {
     			 		  required: true
         			 }
     			},messages: {
     		 		 "la_category_fk_new": {
     			 		  required: 'Required'
     			 	 }, "la_sub_category_new": {
     			 		  required: 'Required'
     			 	 }
     	        },errorPlacement:function(error, element){
     	        	 if(element.attr("id") == "la_category_fk_new" ){
     				     document.getElementById("la_category_fk_newError").innerHTML="";
     			 	     error.appendTo('#la_category_fk_newError');
     			   }else if(element.attr("id") == "la_sub_category_new" ){
     				     document.getElementById("la_sub_category_newError").innerHTML="";
     			 	     error.appendTo('#la_sub_category_newError');
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
      /* function updateRow(no) {
    	  var id = $('#id'+no).val();
          var la_sub_category = $('#la_sub_categoryId'+no).val();
          var la_category_fk = $('#la_category_fkId'+no).val();
          $('#la_sub_category_old').val($.trim(la_sub_category));
          $('#la_category_fk_old').val($.trim(la_category_fk));
          $('#ids').val($.trim(id));
          $('#onlyUpdateModal').modal('open');
          $('#onlyUpdateModal #la_sub_category_new').val($.trim(la_sub_category)).focus();
          $('#onlyUpdateModal #la_category_fk_new').val($.trim(la_category_fk)).focus(); 
          $('select[name^="la_category_fk_new"] option[value="'+ la_category_fk +'"]').attr("selected","selected");
          $('.searchable').select2();
      } */
      function updateRow(no) {
    	  var html = '';
    	  $("#subArchiveBodys0").empty();
    	  var id = $('#id'+no).val();
          var la_sub_category = $('#la_sub_categoryId'+no).val();
          var la_category_fk = $('#la_category_fkId'+no).val();
          $('#value_old').val($.trim(id));
          $('#la_category_fk_old').val($.trim(la_category_fk));
          $('#ids').val($.trim(id));
          if ($.trim(la_category_fk) != "") {
              var myParams = { la_category_fk: la_category_fk };
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getLASubCategory",
                  data: myParams, cache: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
                        html = '<div id="subActionRows'+i+'">'+
							'<div class="col s10 pd0 br20px">'+
							'<input id="la_sub_category_new'+i+'" name="la_sub_category_new" type="text" class="validate" placeholder="Sub Category" value="'+val.la_sub_category+'">'+
							'<span id="la_sub_category'+i+'Error" class="error-msg" ></span>	'+
						'</div>'+
						'<span class="col s1"><a onclick="subRemoveActions('+i+');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span>'+
						'</div><br>';
	                        	$('#rowNu').val(data.length);
	                        	$('#ids').val(id);
                                $("#subArchiveBodys0").append(html);
                                $('#onlyUpdateModal #la_sub_category'+i).focus();
                               // $('#onlyUpdateModal #rr_sub_location_new'+i).val($.trim(rr_sub_location)).focus();
                                $('#onlyUpdateModal #la_category_fk_new').val($.trim(la_category_fk)).focus(); 
                               // $('#onlyUpdateModal #rr_location_fk_new').val($.trim(rr_location_fk)); 
                                $('select[name^="la_category_fk_new"] option[value="'+ la_category_fk +'"]').attr("selected","selected");
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
      	$("#id").val(val);
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
    	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-la-sub-category');
    	    	    	$('#getForm').submit();
    	           }else {
    	                swal("Cancelled", "Record is safe :)", "error");
    	            }
    	        });
    	    }
    </script>

    </body>


</html>