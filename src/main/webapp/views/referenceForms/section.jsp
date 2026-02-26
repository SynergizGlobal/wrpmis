<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>section</title>
    <%--<link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">--%>
    <link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/wrpmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/referenceformitem.min.css">
    <%--<link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/reference-item.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rightColumnFixed.css"> --%>
</head>

<body>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Section</h6>
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
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Section</a>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                               <table id="section_table" class="mdl-data-table">
    <thead>
        <tr>
            <th>Section</th>
            <th>Section Name</th>
            <th>Division ID</th> <!-- Changed from Section Code -->
            <c:forEach var="tObj" items="${sectionDetails.tablesList}">
                <c:forEach var="TObj" items="${tObj.tName}">
                    <c:set var="mTObj" value="${fn:replace(TObj, '_', ' ')}" />
                    <th>${mTObj}</th>
                </c:forEach>
            </c:forEach>
            <th class="no-sort">Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="obj" items="${sectionDetails.sList1}" varStatus="indexs">
            <tr>
                <td>
                    <input type="hidden" id="sectionId${indexs.count}" value="${obj.section}" class="findLengths" />
                    ${obj.section}
                </td>
                <td>
                    <input type="hidden" id="sectionName${indexs.count}" value="${obj.section_name}" class="findLengths1" />
                    ${obj.section_name}
                </td>
                <td>
                    <input type="hidden" id="divisionId${indexs.count}" value="${obj.division_id}" class="findLengths2" />
                    ${obj.division_id}
                </td>
                <c:forEach var="tObj" items="${sectionDetails.tablesList}" varStatus="index">
                    <td>
                        <c:forEach var="cObj" items="${sectionDetails.countList}">
                            <c:choose>
                                <c:when test="${tObj.tName eq cObj.tName}">
                                    <c:choose>
                                        <c:when test="${cObj.section eq obj.section}">
                                            (${cObj.count})
                                        </c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </td>
                </c:forEach>
                <td class="last-column">
                    <a onclick="updateSectionRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger">
                        <i class="fa fa-pencil"></i>
                    </a>
                    <c:forEach var="oSbj" items="${sectionDetails.sList}" varStatus="indexx">
                        <c:choose>
                            <c:when test="${oSbj.section eq obj.section}">
                                <a onclick="deleteSectionRow('${oSbj.section}');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger">
                                    <i class="fa fa-trash"></i>
                                </a>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
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
    <form action="<%=request.getContextPath() %>/add-section" id="addSectionForm" name="addSectionForm" method="post" class="form-horizontal" role="form">
        <div class="modal-content">
            <h5 class="modal-header">Add Section 
                <span class="right modal-action modal-close">
                    <span class="material-icons">close</span>
                </span>
            </h5>
            <div class="row">
                <div class="col m2 hide-on-small"></div>
                <div class="col m8 s12">
                    <div class="row no-mar">
                        <div class="input-field col s12 m6">
                            <input id="section_id" type="text" name="section_id" class="validate" onkeyup="validateSection(this.value, null)">
                            <label for="section_id">Section ID</label>
                            <span id="sectionIdError" class="error-msg"></span>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="section_name" type="text" name="section_name" class="validate" onkeyup="validateSection(null, this.value)">
                            <label for="section_name">Section Name</label>
                            <span id="sectionNameError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
							<select id="division_id" name="division_id" class="browser-default">
							    <option value="" disabled selected>Choose Division</option>
							    <c:forEach var="division" items="${divisionList}">
							        <option value="${division.division_id}">${division.division_name}</option>
							    </c:forEach>
							</select>
                            <span id="divisionError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div style="text-align:center">
                            <span id="formError" class="error-msg"></span> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6 m6">
                            <div class="center-align m-1">
                                <button style="width: 100%;" id="submitBtn" class="btn waves-effect waves-light bg-m">Add</button>
                            </div>
                        </div>
                        <div class="col s6 m6">
                            <div class="center-align m-1">
                                <a href="<%=request.getContextPath()%>/sections"
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
    <form action="<%=request.getContextPath() %>/update-section" id="updateSectionForm" name="updateSectionForm" method="post" class="form-horizontal" role="form">
        <div class="modal-content">
            <h5 class="modal-header bg-m">Update Section
                <span class="right modal-action modal-close" onclick="removeErrorMsg()">
                    <span class="material-icons">close</span>
                </span>
            </h5>
            <div class="row">
                <div class="col m2 hide-on-small"></div>
                <div class="col m8 s12">
                    <div class="row no-mar">
                        <div class="input-field col s12 m6">
                            <input id="section_id_new" type="text" name="section_id_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <input id="section_id_old" type="hidden" name="section_id_old">
                            <label for="section_id_new">Section ID</label>
                            <span id="section_id_newError" class="error-msg"></span>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="section_name_new" type="text" name="section_name_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <input id="section_name_old" type="hidden" name="section_name_old">
                            <label for="section_name_new">Section Name</label>
                            <span id="section_name_newError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="division_id_new" type="text" name="division_id_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <input id="division_id_old" type="hidden" name="division_id_old">
                            <label for="division_id_new">Division ID</label>
                            <span id="division_id_newError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div style="text-align:center">
                            <span id="DivUpdateError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6 m6">
                            <div class="center-align m-1">
                                <button style="width: 100%;" id="bttnUpdate" class="btn waves-effect waves-light bg-m">Update</button>
                            </div>
                        </div>
                        <div class="col s6 m6">
                            <div class="center-align m-1">
                                <a href="<%=request.getContextPath()%>/section" class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>


    
   <!--  <div id="errorModal" class="modal">
        <div class="modal-content">
            <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h5>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div> -->
    <!-- footer  -->
<%-- <jsp:include page="../layout/footer.jsp"></jsp:include> --%>
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="section" id="section" />
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
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
          
            var table = $('#section_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [8] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging:false,
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
        });
        var flag = false;

        function doValidate(value, value1, value2) {
            var value = $('#section_id').val();
            var value1 = $('#section_name').val();
            var value2 = $('#division_id').val();

            value = value.trim();
            value1 = value1.trim();
            value2 = value2.trim();

            var print_value = value;
            var print_value2 = value1;
            var print_value3 = value2;

            var ek = $('.findLengths').map((_, el) => el.value).get();     // section_id
            var ak = $('.findLengths1').map((_, el) => el.value).get();    // section_name
            var bk = $('.findLengths2').map((_, el) => el.value).get();    // division_id

            var s = Object.keys(ek).find(key => ek[key] === value);

            if (value != null) { value = value.toLowerCase(); }
            if (value1 != null) { value1 = value1.toLowerCase(); }
            if (value2 != null) { value2 = value2.toLowerCase(); }

            var validate = $('.findLengths').length;
            if (validate == 0) { flag = true; }
            var count = 0;

            while (count < validate) {
                var findVal = ek[count];
                var findVal2 = ak[count];
                var findVal3 = bk[count];

                if (findVal != null) { findVal = findVal.toLowerCase(); }
                if (findVal2 != null) { findVal2 = findVal2.toLowerCase(); }
                if (findVal3 != null) { findVal3 = findVal3.toLowerCase(); }

                if ((findVal == value && value != null) &&
                    (findVal2 == value1 && value1 != null) &&
                    (findVal3 == value2 && value2 != null)) {

                    $('#DivError').text(`" ${print_value} " & " ${print_value2} " & " ${print_value3} " already exists`).css('color', 'red');
                    $('#sectionError').text('');
                    $('#section_nameError').text('');
                    $('#division_idError').text('');
                    $('#bttn').prop('disabled', true);
                    flag = false;
                    return false;

                } else {
                    if (findVal == value) {
                        $('#bttn').prop('disabled', true);
                        $('#DivError').text('');
                        $('#sectionError').text(`" ${print_value} " already exists`).css('color', 'red');
                        if (findVal2 != value1) { $('#section_nameError').text(''); } else { $('#section_nameError').text(`" ${print_value2} " already exists`).css('color', 'red'); }
                        if (findVal3 != value2) { $('#division_idError').text(''); } else { $('#division_idError').text(`" ${print_value3} " already exists`).css('color', 'red'); }
                        flag = false;
                        return false;
                    } else if (findVal2 == value1) {
                        $('#bttn').prop('disabled', true);
                        $('#DivError').text('');
                        $('#section_nameError').text(`" ${print_value2} " already exists`).css('color', 'red');
                        if (findVal != value) { $('#sectionError').text(''); } else { $('#sectionError').text(`" ${print_value} " already exists`).css('color', 'red'); }
                        if (findVal3 != value2) { $('#division_idError').text(''); } else { $('#division_idError').text(`" ${print_value3} " already exists`).css('color', 'red'); }
                        flag = false;
                        return false;
                    } else if (findVal3 == value2) {
                        $('#bttn').prop('disabled', true);
                        $('#DivError').text('');
                        $('#division_idError').text(`" ${print_value3} " already exists`).css('color', 'red');
                        if (findVal2 != value1) { $('#section_nameError').text(''); } else { $('#section_nameError').text(`" ${print_value2} " already exists`).css('color', 'red'); }
                        if (findVal != value) { $('#sectionError').text(''); } else { $('#sectionError').text(`" ${print_value} " already exists`).css('color', 'red'); }
                        flag = false;
                        return false;
                    } else {
                        $('#DivError').text('');
                        $('#sectionError').text('');
                        $('#section_nameError').text('');
                        $('#division_idError').text('');
                        $('#bttn').prop('disabled', false);
                        flag = true;
                    }
                }
                count++;
            }
        }
        var updateFlag = true;

        function doValidateUpdate(value, value1, value2) {
            var value = $('#section_new').val();
            var value1 = $('#section_name_new').val();
            var value2 = $('#division_id_new').val();

            value = value.trim();
            value1 = value1.trim();
            value2 = value2.trim();

            var print_value = value;
            var print_value2 = value1;
            var print_value3 = value2;

            var validate = $('.findLengths').length;
            var count = 0;
            var no = $('#no').val();
            var valueOld = $('#section_old').val();
            var valueOld2 = $('#section_name_old').val();
            var valueOld3 = $('#division_id_old').val();

            var ek = $('.findLengths').map((_, el) => el.value).get();     // section
            var ak = $('.findLengths1').map((_, el) => el.value).get();    // section_name
            var bk = $('.findLengths2').map((_, el) => el.value).get();    // division_id

            value = value.toLowerCase();
            value1 = value1.toLowerCase();
            value2 = value2.toLowerCase();

            var s = Object.keys(ek).find(key => ek[key] === valueOld);
            var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
            var s2 = Object.keys(bk).find(key => bk[key] === valueOld3);

            delete ek[s];
            delete ak[s1];
            delete bk[s2];

            while (count < validate) {
                var findVal = ek[count];
                var findVal2 = ak[count];
                var findVal3 = bk[count];

                if (findVal != null) { findVal = findVal.toLowerCase(); }
                if (findVal2 != null) { findVal2 = findVal2.toLowerCase(); }
                if (findVal3 != null) { findVal3 = findVal3.toLowerCase(); }

                if ((findVal == value && value != null) &&
                    (findVal2 == value1 && value1 != null) &&
                    (findVal3 == value2 && value2 != null)) {

                    $('#DivUpdateError').text(`" ${print_value} " & " ${print_value2} " & " ${print_value3} " already exists`).css('color', 'red');
                    $('#section_newError').text('');
                    $('#section_name_newError').text('');
                    $('#division_id_newError').text('');
                    $('#bttnUpdate').prop('disabled', true);
                    updateFlag = false;
                    return false;

                } else {
                    if (findVal == value) {
                        $('#bttnUpdate').prop('disabled', true);
                        $('#DivUpdateError').text('');
                        $('#section_newError').text(`" ${print_value} " already exists`).css('color', 'red');

                        if (findVal2 != value1) {
                            $('#section_name_newError').text('');
                        } else {
                            $('#section_name_newError').text(`" ${print_value2} " already exists`).css('color', 'red');
                        }

                        if (findVal3 != value2) {
                            $('#division_id_newError').text('');
                        } else {
                            $('#division_id_newError').text(`" ${print_value3} " already exists`).css('color', 'red');
                        }

                        updateFlag = false;
                        return false;

                    } else if (findVal2 == value1) {
                        $('#DivUpdateError').text('');
                        $('#section_name_newError').text(`" ${print_value2} " already exists`).css('color', 'red');

                        if (findVal != value) {
                            $('#section_newError').text('');
                        } else {
                            $('#section_newError').text(`" ${print_value} " already exists`).css('color', 'red');
                        }

                        if (findVal3 != value2) {
                            $('#division_id_newError').text('');
                        } else {
                            $('#division_id_newError').text(`" ${print_value3} " already exists`).css('color', 'red');
                        }

                        updateFlag = false;
                        return false;

                    } else if (findVal3 == value2) {
                        $('#DivUpdateError').text('');
                        $('#division_id_newError').text(`" ${print_value3} " already exists`).css('color', 'red');

                        if (findVal2 != value1) {
                            $('#section_name_newError').text('');
                        } else {
                            $('#section_name_newError').text(`" ${print_value2} " already exists`).css('color', 'red');
                        }

                        if (findVal != value) {
                            $('#section_newError').text('');
                        } else {
                            $('#section_newError').text(`" ${print_value} " already exists`).css('color', 'red');
                        }

                        updateFlag = false;
                        return false;

                    } else {
                        $('#DivUpdateError').text('');
                        $('#section_newError').text('');
                        $('#section_name_newError').text('');
                        $('#division_id_newError').text('');
                        $('#bttnUpdate').prop('disabled', false);
                        updateFlag = true;
                    }
                }

                count++;
            }
        }

        
        function removeErrorMsg() { 
            $('#DivUpdateError').text('');
            $('#section_newError').text('');
            $('#section_name_newError').text('');
            $('#division_id_newError').text('');
            $('#bttnUpdate').prop('disabled', false);
            updateFlag = true;
        }

        $("#addSectionForm").submit(function (e) {
            if (validator.form()) { 
                $(".page-loader").show();
                $("#addUpdateModal").modal();
                if (flag) {
                    document.getElementById("addSectionForm").submit();    
                }
                $(".page-loader").hide();
                return false;
            }
        });

        $("#updateSectionForm").submit(function (e) {
            if (validator1.form()) { 
                $(".page-loader").show();
                $("#addUpdateModal").modal();
                if (updateFlag) {
                    document.getElementById("updateSectionForm").submit();    
                }
                $(".page-loader").hide();
                return false;
            }
        });

        var validator = $('#addSectionForm').validate({
            rules: {
                "section": {
                    required: true
                },
                "section_name": {
                    required: true
                },
                "division_id": {
                    required: true
                }
            },
            messages: {
                "section": {
                    required: 'Required'
                },
                "section_name": {
                    required: 'Required'
                },
                "division_id": {
                    required: 'Required'
                }
            },
            errorPlacement: function (error, element) {
                if (element.attr("id") == "section") {
                    document.getElementById("sectionError").innerHTML = "";
                    error.appendTo('#sectionError');
                } else if (element.attr("id") == "section_name") {
                    document.getElementById("section_nameError").innerHTML = "";
                    error.appendTo('#section_nameError');
                } else if (element.attr("id") == "division_id") {
                    document.getElementById("division_idError").innerHTML = "";
                    error.appendTo('#division_idError');
                }
            }
        });

      
        var validator1 =  $('#updateSectionForm').validate({
            rules: {
                "section_new": {
                    required: true
                },
                "section_name_new": {
                    required: true
                },
                "division_id_new": {
                    required: true
                }
            },
            messages: {
                "section_new": {
                    required: 'Required'
                },
                "section_name_new": {
                    required: 'Required'
                },
                "division_id_new": {
                    required: 'Required'
                }
            },
            errorPlacement: function(error, element) {
                if(element.attr("id") == "section_new") {
                    document.getElementById("section_newError").innerHTML = "";
                    error.appendTo('#section_newError');
                } else if(element.attr("id") == "section_name_new") {
                    document.getElementById("section_name_newError").innerHTML = "";
                    error.appendTo('#section_name_newError');
                } else if(element.attr("id") == "division_id_new") {
                    document.getElementById("division_id_newError").innerHTML = "";
                    error.appendTo('#division_id_newError');
                }
            }
        });

        $('input, select').change(function(){
            if ($(this).val() != "") {
                $(this).valid();
            }
        });

        function updateRow(no) {
            var sectionName = $('#sectionName' + no).val();
            var section = $('#sectionId' + no).val();
            var divisionId = $('#divisionId' + no).val();

            $('#section_name_old').val($.trim(sectionName));
            $('#section_old').val($.trim(section));
            $('#division_id_old').val($.trim(divisionId));

            $('#onlyUpdateModal').modal('open');
            $('#onlyUpdateModal #section_new').val($.trim(section)).focus();
            $('#onlyUpdateModal #section_name_new').val($.trim(sectionName)).focus();
            $('#onlyUpdateModal #division_id_new').val($.trim(divisionId)).focus();
        }

        function deleteRow(val){
            $("#section").val(val);
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
                    $(".page-loader").show();
                    $('#getForm').attr('action', '<%=request.getContextPath()%>/delete-section');
                    $('#getForm').submit();
                } else {
                    swal("Cancelled", "Record is safe :)", "error");
                }
            });
        }

      
    </script>

</body>

</html>