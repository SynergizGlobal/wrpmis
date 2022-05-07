<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Issue Contract Category</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/referenceformitem.min.css">
    <style>
    	.select2-container--default .select2-results>.select2-results__options {
		    max-height: 300px !important;
		    overflow-y: auto;
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
			padding: 1em;
		}
		.input-field .searchable_label {
		    margin-top: 0px !important;
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
                            <h6> Contract Category</h6>
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
                            <div class="col s12 center-align">
                                <a class="waves-effect waves-light btn bg-s modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Contract Category</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="issue_contract_category_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                          	<th>Category</th>
                                            <th> Contract Category</th>
                                         <%--    <c:forEach var="tObj" items="${issueContractCategory.tablesList}" >
                                            	 <th>${tObj.tName } <br>(count)</th>
                                            </c:forEach> --%>
                                          
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${issueContractCategory}" varStatus="index">
											<tr>
											<td>
											 	${obj.issue_category_fk }
											 	<input type="hidden" id="issue_category_fk${index.count}" value="${obj.issue_category_fk }" class="findLengths2"/>
											</td>
											<td>
											<input type="hidden" id="id${index.count}" name="id" value="${obj.id }" />
												<input type="hidden" id="contract_category_fk${index.count}" value="${obj.contract_category_fk }" class="findLengths"/>
												 <c:forEach var="splt" items="${fn:split(obj.contract_category_fk,',')}">
												   &#9656; ${splt} <br>
												</c:forEach></td>
									
										<td class="last-column"><a href="#onlyUpdateModal" onclick="updateRow(${index.count})" class="btn waves-effect waves-light bg-m t-c "> <i class="fa fa-pencil" ></i></a><a onclick="deleteRow('${ obj.issue_category_fk }');" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>
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
		 <form action="<%=request.getContextPath() %>/add-issue-contract-category" id="addIssueContractCategoryForm" name="addIssueContractCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header">Add Contract Category <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m12 s12">
                        <%-- <div class="row">
                     
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Contract Category</p>
                                <select  id="contract_category_fk_text" name="contract_category_fk" class="searchable validate-dropdown" onchange="doValidate(this.value,null)"> 
                                	<option value="">Select</option>
                                	 <c:forEach var="obj" items="${contractTypeDetails }">
			                                      <option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="contract_category_fkError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6"> 
                            	<p class="searchable_label">Category</p>
                                <select  id="issue_category_fk_text" name="issue_category_fk" class="searchable validate-dropdown" onchange="doValidate(null,this.value)">
                                	<option value="">Select</option>
                                	 <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="issue_category_fkError" class="error-msg" ></span>
                            </div>
                              <div  style="text-align:center">
                        		 <span id="DivError" class="error-msg" ></span> 
                       		   </div>
                        </div> --%>
                        <div id="listOfLocationsAdd"> 
                        <div id="archiveBody"> 
                        <div class="row no-mar mt-c" id="actionRow0">
							<div class="input-field col s11 m5">
								<!-- <input id="rr_location_fk" name="rr_location_fk" type="text" class="validate"> -->
												<p class="searchable_label"> Category</p>
								<select  id="issue_category_fk_text" name="issue_category_fk" class="searchable validate-dropdown">
                                	<option value="">Select</option>
                                	 <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
                                </select>	
                                <span id="issue_category_fkError" class="error-msg" ></span>
                               
							</div>
							<div class="input-field col s11 m5 box-grey" id="subArchiveBody0">
								<div id="subActionRow0">
								<div class="col s10 pd0">
				 
								<p class="searchable_label"> Contract Category</p>
								<select name="contract_category_fk" id="contract_category_fk_text" class="searchable validate-dropdown">
									<option value="">Select </option>
									 <c:forEach var="obj" items="${contractTypeDetails }">
											  <option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>
									  </c:forEach>
								</select>
								<span id="contract_category_fkError" class="error-msg" ></span>
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
                      
                        <div >
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" id="bttn"
                                        class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                        
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/issue-contract-category"
									class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>
     <div id="onlyUpdateModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-issue-contract-category" id=updateIssueContractCategoryForm name="updateIssueContractCategoryForm" method="post" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header bg-m">Update Contract Category <span class="right modal-action modal-close" onclick="removeErrorMsg()"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m12 s12">
                        <%-- <div class="row">
                            <input type="hidden" id="id" name="id"  />
                            <input type="hidden" id="no" name="no"  />
                            
                            <div class="input-field col s12 m6">
                                <input type="hidden" id="contract_caregory_old" name="contract" />
                                <p class="searchable_label">Contract Category</p>
                                <select class="searchable validate-dropdown" id="contract_category_fk_new" name="contract_category_fk_new" onchange="doValidateUpdate(null,null)">
                                	<option value="">Select</option>
                                	<c:forEach var="obj" items="${contractTypeDetails }">
			                                      <option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="contract_category_text_updateError" class="error-msg" ></span>
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="hidden" id="issue_caregory_old" name="issue" />
                            	<p class="searchable_label">Category</p>
                                <select class="searchable validate-dropdown" id="issue_category_text_update" name="issue_category_fk_new" onchange="doValidateUpdate(null,null)">
                                	<option value="">Select</option>
                                	  <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="issue_category_text_updateError" class="error-msg" ></span>
                            </div>
                            <div  style="text-align:center">
                        		 <span id="DivUpdateError" class="error-msg" ></span> 
                       		</div>
                        </div> --%>
                        <div id="listOfLocations">
                         <div id="archiveBodyupdate">
                           <input id="issue_category_fk_old" name="issue_category_fk_old" type="hidden" > 
                          <input id="ids" name="id" type="hidden" > 
                          <input id="value_old" name="value_old" type="hidden" > 
             					<div class="row no-mar mt-c" id="actionRows0"><div class="input-field col s11 m5">
						<p class="searchable_label"> Location</p>
						
						  <select class="searchable validate-dropdown" id="issue_category_fk_new" name="issue_category_fk_new" onchange="doValidateUpdate(null,null)">
                                	<option value="">Select</option>
                                	  <c:forEach var="obj" items="${issueCategoryDetails }">
			                                      <option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>
			                         </c:forEach>
                                </select>
                                <span id="issue_category_text_updateError" class="error-msg" ></span>
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
                                    <button style="width: 100%;" id="bttnUpdate"
                                        class="btn waves-effect waves-light bg-m">Update</button>
                                </div>
                            </div>
                             <div class="col s12 m6">
                                <div class="center-align m-1">
                                  <!--   <button
                                        class="btn waves-effect waves-light bg-s modal-action modal-close black-text"
                                        style="width:100%">Cancel</button> -->
                                        <a href="<%=request.getContextPath()%>/issue-contract-category"
									     class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>

            </div>

        </form>
    </div>

    <!-- footer  -->
<%--     <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
 	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="issue_category_fk" id="idNo" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
        <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
<script src="/pmis/resources/js/dataTables.fixedColumns.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script>
    function addRow() {        	
        var rowNo = $("#rowNo1").val();
        var rNo = Number(rowNo)+1;
        var randNum = Math.floor((Math.random() * 1000));
        var html = '<div class="row no-mar mt-c" id="actionRow' + rNo + '">'
        + '<div class="input-field col s11 m5">'
        

        + '<p class="searchable_label"> Category</p>'
        + '<select name="issue_category_fk" id="issue_category' + rNo +'_fk_text" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${issueCategoryDetails }">'
        + '<option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>'
        + '</c:forEach></select>'
        + '<span id="issue_category' + rNo +'_fkError" class="error-msg" ></span>'
        
        + '</div><div class="input-field col s11 m5 box-grey"  id="subArchiveBody' + rNo +'">'
        + '<div id="subActionRow' + rNo +randNum+'"><div class="col s10 pd0">'
        
        + '<input id="contract_category_fk' + rNo +'" name="contract_category_fk" type="hidden" value="_"> '
        
        + '<p class="searchable_label"> Contract Category</p><select name="contract_category_fk" id="contract_category_fk' + rNo +'_fk_text" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${contractTypeDetails }">'
        + '<option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>'
        + '</c:forEach></select>'
        + '<span id="contract_category_fk' + rNo +'_fkError" class="error-msg" ></span>'
       
        
        + '</div><span class="col s1"><a onclick="subRemoveAction(' + rNo +randNum+ ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div></div>'
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
        + '<p class="searchable_label"> Contract Category</p>'
        + '<select name="contract_category_fk" id="contract_category_fk' + rNo +'_fk_text" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${contractTypeDetails }">'
        + '<option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>'
        + '</c:forEach></select>'
        + '<span id="contract_category_fk' + rNo +'_fkError" class="error-msg" ></span>'
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
        + '<div class="input-field col s11 m5">'
        
        + '<p class="searchable_label"> Category</p>'
        + '<select name="issue_category_fk_new" id="issue_category' + rNo +'text_update" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${issueCategoryDetails }">'
        + '<option value="${obj.issue_category_fk }">${obj.issue_category_fk }</option>'
        + '</c:forEach></select>'
        + '<span id="rr_location' + rNo +'_fkError" class="error-msg" ></span>'
        
        + '</div><div class="input-field col s11 m5 box-grey"  id="subArchiveBodys' + rNo +'">'
        + '<div id="subActionRows' + rNo +randNum+'"><div class="col s10 pd0">'
        + '<input id="contract_category_fk_new' + rNo +'" name="contract_category_fk_new" type="hidden" value="_"> '
        
        +'<p class="searchable_label"> Location</p>'
        + '<select name="contract_category_fk_new" id="contract_category_fk' + rNo +'_fk_new" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${contractTypeDetails }">'
        + '<option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>'
        + '</c:forEach></select>'
        + '<span id="contract_category_fk' + rNo +'_fkError" class="error-msg" ></span>'
      
        + '</div><span class="col s1"><a onclick="subRemoveActions(' + rNo +randNum+ ');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span></div></div>'
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
        + '<p class="searchable_label"> Contract Category</p>'
        + '<select name="contract_category_fk_new" id="contract_category_fk' + rNo +'_fk_new" class="searchable validate-dropdown">'
        + '<option value="">Select </option>'
        + '<c:forEach var="obj" items="${contractTypeDetails }">'
        + '<option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>'
        + '</c:forEach></select>'
        + '<span id="rr_location' + rNo +'_fkError" class="error-msg" ></span>'
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

            var table = $('#issue_contract_category_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                        /* className: "last-column", targets: [1], */
                    },
                    { "width": "20px", "targets": [2] },
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
       
        var flag = false; 
        function doValidate(value,value1){
           var value = $('#contract_category_fk_text').val();
           var value1 = $('#issue_category_fk_text').val();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   if(validate == 0){flag = true;}
     	   var count  = 0;
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	   while(count < validate){
     		 	 var findVal = ek[count];
     			 var findVal2 = ak[count];
     		   if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
     			   $('#DivError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
     			   $('#bttn').prop('disabled', true);
     			   flag = false;
     			   return false;
     		   }else{
     			   $('#DivError').text('');
     			   $('#bttn').prop('disabled', false); 
     			   flag = true;
     		   }
     		   
     		   count++;
     	   }
        }
        var updateFlag = true;
        function doValidateUpdate(value,value1){
           var value = $('#contract_category_fk_new').val();
           var value1 = $('#issue_category_text_update').val();
     	   var print_value = value;	
     	   var print_value2 = value1;	
     	   var validate = $('.findLengths').length;
     	   var count  = 0;
     	   var no = $('#no').val()
     	   var valueOld2 = $('#issue_category_fk'+no).val();
           var valueOld = $('#contract_category_fk'+no).val();
     	   var ek = $('.findLengths').map((_,el) => el.value).get();
     	   var ak = $('.findLengths2').map((_,el) => el.value).get();
     	  /*  var s = Object.keys(ek).find(key => ek[key] === valueOld);
     	   var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
     	   delete ek[s];
     	   delete ak[s1]; */
     	   while(count < validate){
     		  var findVal = ek[count];
  			  var findVal2 = ak[count];
  			  if((findVal == value && value != null) && (findVal2 == value1 && value1 != null)){
  				   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' alreday exists').css('color', 'red');
     			   $('#bttnUpdate').prop('disabled', true);
     			   updateFlag = false;
     			   return false;
     		   }else{
     			   $('#DivUpdateError').text('');
     			   $('#bttnUpdate').prop('disabled', false);
     			   updateFlag = true;
     		   }
     		   
     		   count++; 
     	   }
        }
        
        function removeErrorMsg(){
   		 $('#DivUpdateError').text('');
   		 $('#bttnUpdate').prop('disabled', false);
   		 updateFlag = true;
   		}
        
       
         $("#addIssueContractCategoryForm").submit(function (e) {
          	 if(validator.form()){ 
      			$(".page-loader").show();
      			$("#addUpdateModal").modal();
      			document.getElementById("addIssueContractCategoryForm").submit();	
      			 $(".page-loader").hide();
           }
       })
       
         $("#updateIssueContractCategoryForm").submit(function (e) {
        	 if(validator1.form()){ 
    			$(".page-loader").show();
    			$("#onlyUpdateModal").modal();
      			document.getElementById("updateIssueContractCategoryForm").submit();	
      			 $(".page-loader").hide();
         }
     })
      
      
        var validator = $('#addIssueContractCategoryForm').validate({
	       	 ignore: ":hidden:not(.validate-dropdown)",
	       	 rules: {
	       		 "contract_category_fk": {
	   			 		  required: true
	       		 },
	   		    "issue_category_fk": {
	   				      required: true
	   			}
   			},messages: {
   		 		   "contract_category_fk": {
   			 		  required: 'Required'
   			 	  },
   			 	  "issue_category_fk": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "contract_category_fk_text" ){
   				     document.getElementById("contract_category_fkError").innerHTML="";
   			 	     error.appendTo('#contract_category_fkError');
   				 }else if(element.attr("id") == "issue_category_fk_text" ){
   				     document.getElementById("issue_category_fkError").innerHTML="";
   			 	     error.appendTo('#issue_category_fkError');
   				 }
   	        }
       });
       
       var validator1 =  $('#updateIssueContractCategoryForm').validate({
	       	 ignore: ":hidden:not(.validate-dropdown)",
	      	 rules: {
	      	   "contract_category_fk_new": {
	   	 		  required: true
	   			 },
	   		   "issue_category_fk_new": {
	   				      required: true
	   			}
   			},messages: {
   				   "contract_category_fk_new": {
   			 		  required: 'Required'
   			 	  },
   			 	  "issue_category_fk_new": {
   			 		  required: 'Required'
   			 	  }
   	        },errorPlacement:function(error, element){
   	        	 if(element.attr("id") == "issue_category_text_update" ){
   				     document.getElementById("issue_category_text_updateError").innerHTML="";
   			 	     error.appendTo('#issue_category_text_updateError');
   				 }else if(element.attr("id") == "contract_category_fk_new" ){
   				     document.getElementById("contract_category_text_updateError").innerHTML="";
   			 	     error.appendTo('#contract_category_text_updateError');
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
    	 var issue_category = $('#issue_category_fk'+no).val();
         var contract_category_fk = $('#contract_category_fk'+no).val();
         var id = $('#id'+no).val();
         $('#onlyUpdateModal').modal('open');
         $('#issue_category_text_update').val($.trim(issue_category))
         $('#contract_category_fk_new').val($.trim(contract_category_fk))
       
         $('#issue_category_old').val($.trim(issue_category));
         $('#contract_category_old').val($.trim(contract_category_fk));
         var s  = $('#id').val($.trim(id))
         var s1  = $('#no').val($.trim(no))
         $('#onlyUpdateModal #issue_category_text_update').val($.trim(issue_category)).focus();
         $('#onlyUpdateModal #contract_category_fk_new').val($.trim(contract_category_fk)).focus();
         $('select[name^="issue_category_fk_new"] option[value="'+ $.trim(issue_category) +'"]').attr("selected","selected");
         $('select[name^="contract_category_fk_new"] option[value="'+ $.trim(contract_category_fk) +'"]').attr("selected","selected");
	     $('.searchable').select2();
     } */
    function updateRow(no) {
  	  var html = '';
  	  $("#subArchiveBodys0").empty();
  	  var id = $('#id'+no).val();
  	var issue_category_fk = $('#issue_category_fk'+no).val();
    var contract_category_fk = $('#contract_category_fk'+no).val();
    $('#onlyUpdateModal').modal('open');
        $('#value_old').val($.trim(contract_category_fk));
        $('#issue_category_text_update').val($.trim(issue_category_fk))
        $('#contract_category_fk_new').val($.trim(contract_category_fk))
      
        $('#issue_category_fk_old').val($.trim(issue_category_fk));
        $('#contract_category_old').val($.trim(contract_category_fk));
        $('#ids').val($.trim(id));
        if ($.trim(issue_category_fk) != "") {
            var myParams = { issue_category_fk: issue_category_fk };
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getContarctCategory",
                data: myParams, cache: false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                      html = '<div id="subActionRows'+i+'">'+
							'<div class="col s10 pd0 br20px">'
							  + '<p class="searchable_label"> Contract Category</p>'
							   + '<select name="contract_category_fk_new" id="contract_category_fk' + i +'_fk_new" class="searchable validate-dropdown">'
						        + '<option value="">Select </option>'
						        + '<c:forEach var="obj" items="${contractTypeDetails }">'
						        + '<option value="${obj.contract_category_fk }">${obj.contract_category_fk }</option>'
						        + '</c:forEach></select>'
						        +'<span id="rr_sub_location'+i+'Error" class="error-msg" ></span>	'+
						'</div>'+
						'<span class="col s1"><a onclick="subRemoveActions('+i+');" class="btn red waves-effect waves-light mt1em br20px"><i class="fa fa-close"></i></a></span>'+
						'</div><br>';
	                        	$('#rowNu').val(data.length);
	                        	$('#ids').val(id);
                              $("#subArchiveBodys0").append(html);
                              $('#onlyUpdateModal #issue_category_text_update').val($.trim(issue_category_fk)).focus();
                              $('#onlyUpdateModal #contract_category_fk' + i +'_fk_new').val($.trim(val.contract_category_fk)).focus();
                              $('select[name^="issue_category_fk_new"] option[value="'+ $.trim(issue_category_fk) +'"]').attr("selected","selected");
                              $('#contract_category_fk' + i +'_fk_new option[value="'+ $.trim(val.contract_category_fk) +'"]').attr("selected","selected");
                     	     $('.searchable').select2();
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
	  	$("#idNo").val(val);
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
		            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-issue-contract-category');
		    	    	$('#getForm').submit();
		           }else {
		                swal("Cancelled", "Record is safe :)", "error"); 
		            }
		        });
		    }
	</script>

</body>
</html>
