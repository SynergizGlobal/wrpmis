<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Document </c:if>
		 <c:if test="${action eq 'add'}"> Add Document </c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" href="/pmis/resources/css/light-theme.css">
     <style>
        .filevalue {
            display: block;
            margin-top: 10px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        #revision_details .datepicker~button {
            top: 28px;
            top: 34px;
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
            border: 0
        }
    </style>
</head>
<body>
  <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>
                                 	 <c:if test="${action eq 'edit'}">Update Document </c:if>
									 <c:if test="${action eq 'add'}"> Add Document </c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                   		    <c:if test="${action eq 'edit'}">				                
				                 	<form action="<%=request.getContextPath() %>/update-document" id="documentForm" name="documentForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	                         </c:if>
				             <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-document" id="documentForm" name="documentForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
							 </c:if>
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Project </p>
                                    <select class="searchable validate-dropdown" id="project_id_fk" name="project_id_fk"  
                               	  		 onchange="getWorksList(this.value);">
                                    		 <option value="" >Select</option>
                                      		 <c:forEach var="obj" items="${projectsList }">
                                         			 <option value="${obj.project_id_fk }" <c:if test="${documentDetails.project_id_fk eq obj.project_id_fk}">selected</c:if>>${obj.project_id_fk}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                      		 </c:forEach>
		                             </select>
                               		 <span id="project_idError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Work </p>
                                     <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk"
                                   		  onchange="getContractsList(this.value);">
                                   		  <option value="">Select</option>
	                                  </select>
                                   		   
                                  <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Contract </p>
                                   <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                       	<option value="">Select</option>
                                  	</select>
                                   	<span id="contract_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Document Type </p>
                                    <select class="searchable">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${documentTypeList }">
		                                     <option value="${obj.document_type_fk }" <c:if test="${documentDetails.document_type_fk eq obj.document_type_fk}">selected</c:if>>${obj.document_type_fk}</option>
		                                </c:forEach>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Priority </p>
                                    <select class="searchable">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${priorityList }">
		                                     <option value="${obj.project_priority_fk }" <c:if test="${documentDetails.project_priority_fk eq obj.project_priority_fk}">selected</c:if>>${obj.project_priority_fk}</option>
		                                </c:forEach>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Responsible for Approval </p>
                                    <select class="searchable">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${userList }">
		                                     <option value="${obj.responsible_for_approval }" <c:if test="${documentDetails.responsible_for_approval eq obj.responsible_for_approval}">selected</c:if>>${obj.responsible_for_approval}</option>
		                                </c:forEach>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="document_name" name="document_name" type="text" class="validate" value="${documentDetails.document_name }">
                                    <label for="doc_name">Document Name</label>
                                </div>
                            </div>
                        </div>
              			 <input type="hidden" name="document_no" id="document_no" value="${documentDetails.document_no}"  />

                        <div class="row fixed-width" style="margin-bottom: 40px;">
                            <h5 class="center-align">Revision Details</h5>
                            <div class="table-inside">
                                <table id="revision_details" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Revision No</th>
                                            <th>Status</th>
                                            <th>Submission Date</th>
                                            <th>Approval Date</th>
                                            <th>Remarks </th>
                                            <th>Attachment</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="documentTableBody">
                                    <c:choose>
                                      	 <c:when test="${not empty documentDetails.documents && fn:length(documentDetails.documents) gt 0 }">
                                       	 <c:forEach var="dObj" items="${documentDetails.documents }" varStatus="index"> 
                                        <tr id="documentRow${index.count }">
                                           <td>
                                                	<input type="hidden" name= "document_no_fk" id="document_no_fks${index.count }" value="${dObj.document_no_fks}" />
                                                    <input id="revision_nos${index.count }" name="revision_nos" type="text" class="validate"  placeholder="Revision No" value="${dObj.revision_no }">
                                                </td>
                                            <td>
                                                <select id="status_fks${index.count }" name="status_fks" class="validate-dropdown searchable">
                                                    <option value="" >Select</option>
                                                    <c:forEach var="obj" items="${statusList }">
		                                           			 <option value="${obj.status_fk }" <c:if test="${dObj.status_fk eq obj.status_fk}">selected</c:if>>${obj.status_fk}</option>
		                                            </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input id="submission_dates${index.count }" name="submission_dates" type="text" class="validate datepicker" value="${dObj.submission_date }"
                                                    placeholder="Submission Date">
                                                <button type="button" id="submission_1_icon${index.count }" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <input id="approval_dates${index.count }" name="approval_dates" type="text" class="validate datepicker" value="${dObj.approval_date }"
                                                    placeholder="Approval Date">
                                                <button type="button" id="approval_1_icon${index.count }" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <textarea id="remarkss${index.count }" name="remarkss"  class="materialize-textarea" data-length="1000"
                                                    placeholder="Remarks">${dObj.remarks }</textarea>
                                            </td>
                                            <td>
                                                <div class="">
                                                    <input type="file" name="documentsFile" id="documentsFile${index.count }" style="display:none" onchange="getFileName('${index.count }')"  />
                                                    <label for="myFile" class="btn bg-m"><i
                                                            class="fa fa-paperclip"></i></label>
                                                    <a id="fileVal${index.count }" class="filevalue" href="<%=CommonConstants.DOCUMENT_FILES %>${dObj.document_attachment }" download>${dObj.document_attachment }</a>
                                                            
                                                    <span id="fileVal" class="filevalue">fileName</span>
                                                </div>
                                                <input type="hidden" id="documentsFileNames${index.count }" name="documentsFileNames" value="${dObj.document_attachment }">
                                            </td>
                                            <td>
                                                <a  onclick="removeDocumentRow('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                       </c:when>
                                       	<c:otherwise>
                                       <tr id="documentRow0">
                                           <td>
                                                	<input type="hidden" name= "document_no_fks" id="document_no_fks0" value="${dObj.document_no_fks}" />
                                                    <input id="revision_nos0" name="revision_nos" type="text" class="validate" value="${dObj.revision_nos }"
                                                        placeholder="Revision No">
                                                </td>
                                            <td>
                                                  <select id="status_fks0" name="status_fks" class="validate-dropdown searchable">
                                                    <option value="" >Select</option>
                                                    <c:forEach var="obj" items="${statusList }">
		                                           			 <option value="${obj.status_fk }">${obj.status_fk}</option>
		                                            </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input id="submission_dates0" name="submission_dates" type="text" class="validate datepicker"
                                                    placeholder="Submission Date">
                                                <button type="button" id="submission_1_icon0" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <input id="approval_dates0" name="approval_dates" type="text" class="validate datepicker"
                                                    placeholder="Approval Date">
                                                <button type="button" id="approval_1_icon0" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                               <textarea id="remarkss0" name="remarkss"  class="materialize-textarea" data-length="1000"
                                                    placeholder="Remarks"></textarea>
                                            </td>
                                            <td>
                                                <div class="">
                                                   <input type="file" name="documentsFile" id="documentsFile0" style="display:none"   />
                                                    <label for="myFile" class="btn bg-m"><i
                                                            class="fa fa-paperclip"></i></label>
                                                            <input name="documentsFiles" id="documentsFiles0" type="hidden"  onchange="getFileName('0')"/>
                                                    <span id="fileVal" class="filevalue">fileName</span>
                                                </div>
                                                 <input type="hidden" id="documentFileNames0" name="documentFileNames">
                                            </td>
                                            <td>
                                                <a  onclick="removeDocumentRow('0');" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
													<script type="text/javascript">
			                                                $("#approval_dates0,#submission_dates0").datepicker({
			                                                 	 format:'dd-mm-yyyy',
			                                                     onSelect: function () {
			                                          	    	     $('.confirmation-btns .datepicker-done').click();
			                                          	    	  }
			                                                 });
			                                               /*  $("#SafetyEquipmentFile0").change(function () {
				                                                filename1 = $('#SafetyEquipmentFile0')[0].files[0].name;
				                                                $('#fileVal0').html(filename1);
				                                                console.log(filename1)
				                                            }); */
			                                                
		                                      </script>
                                    	  </c:otherwise>
                                     	</c:choose>
                                     	 </tbody>
                                    </table>
                                    <table class="mdl-data-table">
                                        <tbody id="documentBody">                                          
			                                    <tr>
			  										 <td colspan="9" style="text-align: right;"> <a type="button" class="btn waves-effect waves-light bg-s t-c " onclick="addDocumentRow()"> <i
			                                                            class="fa fa-plus"></i></a>
			                                    </tr>
                                        </tbody>
                                    </table>
  									<c:choose>
                                        <c:when test="${not empty (documentDetails.documents) && fn:length(documentDetails.documents) gt 0 }">
                                    		<input type="hidden" id="rowNo"  name="rowNo" value="${fn:length(documentDetails.documents) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="rowNo"  name="rowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose> 
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateDocument();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addDocument();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s "
                                            style="width:100%">Cancel</button>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </form>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

  <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    
        <script>
        $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    });
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();

            // commented code placed next script tag from here 
           /*  $('#submission_1,#approval_1').datepicker(); */
            $('#submission_1_icon').click(function () {
                event.stopPropagation();
                $('#submission_1').click();
            });
            $('#approval_1_icon').click(function () {
                event.stopPropagation();
                $('#approval_1').click();
            });

            $("#myFile").change(function () {
                filename1 = $('#myFile')[0].value;

                $('#fileVal').html(filename1);
                console.log(filename1)
                console.log($('#myFile')[0].localName)
            });
            
            var project_id_fk = "${documentDetails.project_id_fk}";
            if($.trim(project_id_fk) != ''){
            	getWorksList(project_id_fk);
            }
            var work_id_fk = "${documentDetails.work_id_fk}";
            if($.trim(work_id_fk) != ''){
            	getContractsList(work_id_fk);
            }
        });
       
        function getWorksList(project_id_fk) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(project_id_fk) != "") {
                var myParams = { project_id_fk: project_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                var workId = "${documentDetails.work_id_fk}";
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContract",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var contract_name = '';
                            	var contract_id_fk = "${documentDetails.contract_id_fk }";
                                if ($.trim(val.contract_name) != '') { contract_name = val.contract_id+' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${documentDetails.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '" selected>' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
                                }else {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id_fk) + $.trim(contract_name) + '</option>');
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
        
function addDocumentRow(){
      		
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
          
             var html = '<tr id="documentRow'+rNo+'">'
    				   +'<td><input type="hidden" name= "document_no_fks" id="document_no_fks'+rNo+'" />'
             		   +'<input id="revision_nos'+rNo+'" name="revision_nos" type="text" class="validate" placeholder="Revision No"></td>'
    				   +'<td><select id="status_fks'+rNo+'" name="status_fks" class="validate-dropdown searchable" >'
                       +'<option value="" >Select</option>'
                         <c:forEach var="obj" items="${statusList}">
                      	   +'<option value="${obj.status_fk }">${obj.status_fk}</option>'
                         </c:forEach>
                  	   +'</select></td>'
    				   +'<td><input id="submission_dates'+rNo+'" name="submission_dates" type="text" class="validate datepicker" placeholder="Submission Date"><button type="button" id="submission_1_icon'+rNo+'" class="white"><i class="fa fa-calendar"></i></button></td>'
    				   +'<td><input id="approval_dates'+rNo+'" name="approval_dates" type="text" class="validate datepicker" placeholder="Approval Date"><button type="button" id="approval_1_icon'+rNo+'" class="white"><i class="fa fa-calendar"></i></button></td>'
    				   +'<td><input id="remarkss'+rNo+'" name="remarkss" type="text" class="validate" placeholder="Remarks"></td>'
    			   	   +'<td><div class=""> <input type="file" name="documentFile" id="documentFile'+rNo+'" style="display:none" onchange="getFileName('+rNo+')" /> '
    			   	   +'<label for="documentFile'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
    			   	   +'<input type="hidden" id="documentFileNames'+rNo+'" name="documentFileNames">'
                       +'<span id="fileVal'+rNo+'" class="filevalue" ></span> </div></td>'
    				   +'<td> <a onclick="removeDocumentRow('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td></tr>';

    				 $('#documentTableBody').append(html);
    				 $("#rowNo").val(rNo);
    				 $('.searchable').select2();
    				  $("#submission_dates"+rNo).datepicker({
                      	 format:'dd-mm-yyyy',
                          onSelect: function () {
               	    	     $('.confirmation-btns .datepicker-done').click();
               	    	  }
                      });
    				  $("#approval_dates"+rNo).datepicker({
                       	 format:'dd-mm-yyyy',
                           onSelect: function () {
                	    	     $('.confirmation-btns .datepicker-done').click();
                	    	  }
                       });
    				  
            } 
         

			function removeDocumentRow(rowNo){
				//alert("#revisionRow"+rowNo);
				$("#documentRow"+rowNo).remove();
			}
			
			function getFileName(rowNo){
				var filename = $('#documentFile'+rowNo)[0].files[0].name;
			    $('#fileVal'+rowNo).html(filename);
			}

    </script>
</body>
</html>