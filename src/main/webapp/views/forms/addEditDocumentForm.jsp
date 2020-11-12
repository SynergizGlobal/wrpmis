<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Priority </p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Responsible for Approval </p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <input id="doc_name" type="text" class="validate">
                                    <label for="doc_name">Document Name</label>
                                </div>
                            </div>
                        </div>

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
                                    <tbody>
                                        <tr>
                                            <td>
                                                <input id="revision_no1" type="text" class="validate"
                                                    placeholder="Revision No">
                                            </td>
                                            <td>
                                                <select id="status1">
                                                    <option value="" selected>Select</option>
                                                    <option value="1">Revised</option>
                                                    <option value="2">Approved</option>
                                                    <option value="3">Submitted</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input id="submission_1" type="text" class="validate datepicker"
                                                    placeholder="Submission Date">
                                                <button type="button" id="submission_1_icon" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <input id="approval_1" type="text" class="validate datepicker"
                                                    placeholder="Approval Date">
                                                <button type="button" id="approval_1_icon" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <textarea id="remarks1" class="materialize-textarea" data-length="1000"
                                                    placeholder="Remarks"></textarea>
                                            </td>
                                            <td>
                                                <div class="">
                                                    <input type="file" name="myfile" id="myFile" style="display:none" />
                                                    <label for="myFile" class="btn bg-m"><i
                                                            class="fa fa-paperclip"></i></label>
                                                    <span id="fileVal" class="filevalue">fileName</span>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="7">
                                                <a href="#" class="btn waves-effect waves-light bg-m t-c right"
                                                    onclick="addRow()"> <i class="fa fa-plus"></i></a>
                                            </td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button style="width: 100%;" class="btn waves-effect waves-light bg-m ">Add /
                                            Edit</button>
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
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();

            // commented code placed next script tag from here 
            $('#submission_1,#approval_1').datepicker();
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
        });
       
    </script>
</body>
</html>