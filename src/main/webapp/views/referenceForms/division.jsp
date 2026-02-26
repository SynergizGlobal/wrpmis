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
    <title>Department</title>
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
                        <h6>Division</h6>
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
                                <i class="fa fa-plus-circle"></i> &nbsp; Add Division</a>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col m12 s12">
                            <table id="division_table" class="mdl-data-table">
                                <thead>
                                    <tr>
                                        <th>Division</th>
                                        <th>Division Name</th>
                                        <th>Railway Zone</th>
                                        <c:forEach var="tObj" items="${divisionDetails.tablesList}">
                                            <c:forEach var="TObj" items="${tObj.tName}">
                                                <c:set var="mTObj" value="${fn:replace(TObj, '_', ' ')}" />
                                                <th>${mTObj}</th>
                                            </c:forEach>
                                        </c:forEach>
                                        <th class="no-sort">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="obj" items="${divisionDetails.divList1}" varStatus="indexs">
                                        <tr>
                                            <td>
                                                <input type="hidden" id="divisionId${indexs.count}" value="${obj.division_id}" class="findLengths" />
                                                ${obj.division_id}
                                            </td>
                                            <td>
                                                <input type="hidden" id="divisionName${indexs.count}" value="${obj.division_name}" class="findLengths1" />
                                                ${obj.division_name}
                                            </td>
                                            <td>
                                                <input type="hidden" id="railwayZone${indexs.count}" value="${obj.railway_zone}" class="findLengths2" />
                                                ${obj.railway_zone}
                                            </td>
                                            <c:forEach var="tObj" items="${divisionDetails.tablesList}" varStatus="index">
                                                <td>
                                                    <c:forEach var="cObj" items="${divisionDetails.countList}">
                                                        <c:choose>
                                                            <c:when test="${tObj.tName eq cObj.tName}">
                                                                <c:choose>
                                                                    <c:when test="${cObj.division_id eq obj.division_id}">
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
                                                <a onclick="updateRow(${indexs.count})" class="btn waves-effect waves-light bg-m t-c modal-trigger">
                                                    <i class="fa fa-pencil"></i>
                                                </a>
                                                <c:forEach var="oSbj" items="${divisionDetails.divList}" varStatus="indexx">
                                                    <c:choose>
                                                        <c:when test="${oSbj.division_id eq obj.division_id}">
                                                            <a onclick="deleteRow('${oSbj.division_id}');" id="${indexx.count}" class="btn waves-effect waves-light bg-s t-c modal-trigger">
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
    <form action="<%=request.getContextPath() %>/add-division" id="addDivisionForm" name="addDivisionForm" method="post" class="form-horizontal" role="form">
        <div class="modal-content">
            <h5 class="modal-header">Add Division <span class="right modal-action modal-close">
                <span class="material-icons">close</span></span></h5>
            <div class="row">
                <div class="col m2 hide-on-small"></div>
                <div class="col m8 s12">
                    <div class="row no-mar">
                        <div class="input-field col s12 m6">
                            <input id="division_id" type="text" name="division_id" class="validate" onkeyup="validateDivision(this.value, null, null)">
                            <label for="division_id">Division ID</label>
                            <span id="divisionIdError" class="error-msg"></span>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="division_name" type="text" name="division_name" class="validate" onkeyup="validateDivision(null, this.value, null)">
                            <label for="division_name">Division Name</label>
                            <span id="divisionNameError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="railway_zone" type="text" name="railway_zone" class="validate" onkeyup="validateDivision(null, null, this.value)">
                            <label for="railway_zone">Railway Zone</label>
                            <span id="railwayZoneError" class="error-msg"></span>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="created_date" type="text" name="created_date" class="validate datepicker">
                            <label for="created_date">Created Date</label>
                            <span id="createdDateError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div style="text-align:center">
                            <span id="DivError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s6 m6">
                            <div class="center-align m-1">
                                <button style="width: 100%;" id="bttn" class="btn waves-effect waves-light bg-m">Add</button>
                            </div>
                        </div>
                        <div class="col s6 m6">
                            <div class="center-align m-1">
                                <a href="<%=request.getContextPath()%>/division"
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
    <form action="<%=request.getContextPath() %>/update-division" id="updateDivisionForm" name="updateDivisionForm" method="post" class="form-horizontal" role="form">
        <div class="modal-content">
            <h5 class="modal-header bg-m">
                Update Division 
                <span class="right modal-action modal-close" onclick="removeErrorMsg()">
                    <span class="material-icons">close</span>
                </span>
            </h5>
            <div class="row">
                <div class="col m2 hide-on-small"></div>
                <div class="col m8 s12">
                    <div class="row no-mar">
                        <div class="input-field col s12 m6">
                            <input id="division_id_new" type="text" name="division_id_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <input id="division_id_old" type="hidden" name="division_id_old">
                            <label for="division_id_new">Division ID</label>
                            <span id="division_id_newError" class="error-msg"></span>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="division_name_new" type="text" name="division_name_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <label for="division_name_new">Division Name</label>
                            <input id="division_name_old" type="hidden" name="division_name_old">
                            <span id="division_name_newError" class="error-msg"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="railway_zone_new" type="text" name="railway_zone_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <input id="railway_zone_old" type="hidden" name="railway_zone_old">
                            <label for="railway_zone_new">Railway Zone</label>
                            <span id="railway_zone_newError" class="error-msg"></span>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="created_date_new" type="date" name="created_date_new" class="validate" onkeyup="doValidateUpdate(null,null,null)">
                            <input id="created_date_old" type="hidden" name="created_date_old">
                            <label for="created_date_new">Created Date</label>
                            <span id="created_date_newError" class="error-msg"></span>
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
                                <a href="<%=request.getContextPath()%>/division" class="btn waves-effect waves-light bg-s modal-action modal-close" style="width: 100%">Cancel</a>
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
    	<input type="hidden" name="department" id="department" />
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
          
            var table = $('#department_table').DataTable({
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
            var value = $('#division_id').val();
            var value1 = $('#division_name').val();
            var value2 = $('#railway_zone').val();
            value = value.trim();
            value1 = value1.trim();
            value2 = value2.trim();

            var print_value = value;
            var print_value2 = value1;
            var print_value3 = value2;

            var ek = $('.findDivisionId').map((_, el) => el.value).get();
            var ak = $('.findDivisionName').map((_, el) => el.value).get();
            var bk = $('.findRailwayZone').map((_, el) => el.value).get();

            if (value != null) { value = value.toLowerCase(); }
            if (value1 != null) { value1 = value1.toLowerCase(); }
            if (value2 != null) { value2 = value2.toLowerCase(); }

            var validate = $('.findDivisionId').length;
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
                        
                    $('#DivError').text('" ' + print_value + ' "' + ' & ' + '" ' + print_value2 + ' "' + ' & ' + '" ' + print_value3 + ' "' + ' already exists').css('color', 'red');
                    $('#division_idError').text('');
                    $('#division_nameError').text('');
                    $('#railway_zoneError').text('');
                    $('#bttn').prop('disabled', true);
                    flag = false;
                    return false;
                } else {
                    if (findVal == value) {
                        $('#bttn').prop('disabled', true);
                        $('#DivError').text('');
                        $('#division_idError').text('" ' + print_value + ' "' + ' already exists').css('color', 'red');
                        if (findVal2 != value1) { $('#division_nameError').text(''); } else { $('#division_nameError').text('" ' + print_value2 + ' "' + ' already exists').css('color', 'red'); }
                        if (findVal3 != value2) { $('#railway_zoneError').text(''); } else { $('#railway_zoneError').text('" ' + print_value3 + ' "' + ' already exists').css('color', 'red'); }
                        flag = false;
                        return false;
                    } else if (findVal2 == value1) {
                        $('#bttn').prop('disabled', true);
                        $('#DivError').text('');
                        $('#division_nameError').text('" ' + print_value2 + ' "' + ' already exists').css('color', 'red');
                        if (findVal != value) { $('#division_idError').text(''); } else { $('#division_idError').text('" ' + print_value + ' "' + ' already exists').css('color', 'red'); }
                        if (findVal3 != value2) { $('#railway_zoneError').text(''); } else { $('#railway_zoneError').text('" ' + print_value3 + ' "' + ' already exists').css('color', 'red'); }
                        flag = false;
                        return false;
                    } else if (findVal3 == value2) {
                        $('#bttn').prop('disabled', true);
                        $('#DivError').text('');
                        $('#railway_zoneError').text('" ' + print_value3 + ' "' + ' already exists').css('color', 'red');
                        if (findVal2 != value1) { $('#division_nameError').text(''); } else { $('#division_nameError').text('" ' + print_value2 + ' "' + ' already exists').css('color', 'red'); }
                        if (findVal != value) { $('#division_idError').text(''); } else { $('#division_idError').text('" ' + print_value + ' "' + ' already exists').css('color', 'red'); }
                        flag = false;
                        return false;
                    } else {
                        $('#DivError').text('');
                        $('#division_idError').text('');
                        $('#division_nameError').text('');
                        $('#railway_zoneError').text('');
                        $('#bttn').prop('disabled', false);
                        flag = true;
                    }
                }
                count++;
            }
        }

        var updateFlag = true;
        function doValidateUpdate(value, value1, value2){
           var value = $('#division_new').val();
           var value1 = $('#division_name_new').val();
           var value2 = $('#railway_zone_new').val();
           value = value.trim();
           value1 = value1.trim();
           value2 = value2.trim();
           var print_value = value;	
           var print_value2 = value1;	
           var print_value3 = value2;	
           var validate = $('.findDivisionId').length;
           var count  = 0;
           var no = $('#no').val()
           var valueOld  = $('#division_old').val()
           var valueOld2 = $('#division_name_old').val()
           var valueOld3 = $('#railway_zone_old').val()
           var ek = $('.findDivisionId').map((_,el) => el.value).get();
           var ak = $('.findDivisionName').map((_,el) => el.value).get();
           var bk = $('.findRailwayZone').map((_,el) => el.value).get();
           value = value.toLowerCase();
           value1 = value1.toLowerCase();
           value2 = value2.toLowerCase();
           var s = Object.keys(ek).find(key => ek[key] === valueOld);
           var s1 = Object.keys(ak).find(key => ak[key] === valueOld2);
           var s2 = Object.keys(bk).find(key => bk[key] === valueOld3);
           delete ek[s];
           delete ak[s1];
           delete bk[s2];
           while(count < validate){
              var findVal = ek[count];
              var findVal2 = ak[count];
              var findVal3 = bk[count];
              if(findVal != null){ findVal = findVal.toLowerCase();}
              if(findVal2 != null){ findVal2 = findVal2.toLowerCase();}
              if(findVal3 != null){ findVal3 = findVal3.toLowerCase();}
              if((findVal == value && value != null) && (findVal2 == value1 && value1 != null) && (findVal3 == value2 && value2 != null)){
                   $('#DivUpdateError').text('" '+print_value+' "'+' & '+'" '+print_value2+' "'+' & '+'" '+print_value3+' "'+' already exists').css('color', 'red');
                   $('#division_newError').text('');
                   $('#division_name_newError').text('');
                   $('#railway_zone_newError').text('');
                   $('#bttnUpdate').prop('disabled', true);
                   updateFlag = false;
                   return false;
               }else{
                  if(findVal == value ){
                     $('#bttnUpdate').prop('disabled', true);
                     $('#DivUpdateError').text('');
                     $('#division_newError').text('" '+print_value+' "'+' already exists').css('color', 'red');
                     if(findVal2 != value1 ){$('#division_name_newError').text('');}else{ $('#division_name_newError').text('" '+print_value2+' "'+' already exists').css('color', 'red');}
                     if(findVal3 != value2 ){$('#railway_zone_newError').text('');}else{ $('#railway_zone_newError').text('" '+print_value3+' "'+' already exists').css('color', 'red');}
                     updateFlag = false; 
                     $('#bttnUpdate').prop('disabled', true);
                     return false;
                 }else if(findVal2 == value1 ){
                     $('#DivUpdateError').text('');
                     $('#division_name_newError').text('" '+print_value2+' "'+' already exists').css('color', 'red');
                     if(findVal != value ){$('#division_newError').text('');}else{ $('#division_newError').text('" '+print_value+' "'+' already exists').css('color', 'red');}
                     if(findVal3 != value2 ){$('#railway_zone_newError').text('');}else{ $('#railway_zone_newError').text('" '+print_value3+' "'+' already exists').css('color', 'red');}
                     updateFlag = false;
                     $('#bttnUpdate').prop('disabled', true);
                     return false;
                 }else if(findVal3 == value2 ){
                     $('#DivUpdateError').text('');
                     $('#railway_zone_newError').text('" '+print_value3+' "'+' already exists').css('color', 'red');
                     if(findVal2 != value1 ){$('#division_name_newError').text('');}else{ $('#division_name_newError').text('" '+print_value2+' "'+' already exists').css('color', 'red');}
                     if(findVal != value ){$('#division_newError').text('');}else{ $('#division_newError').text('" '+print_value+' "'+' already exists').css('color', 'red');}
                     updateFlag = false;
                     $('#bttnUpdate').prop('disabled', true);
                     return false;
                 }else{
                       $('#DivUpdateError').text('');
                       $('#division_newError').text('');
                       $('#division_name_newError').text('');
                       $('#railway_zone_newError').text('');
                       $('#bttnUpdate').prop('disabled', false);
                       updateFlag = true;
                  }
               }
               count++; 
           }
        }

        function removeErrorMsg(){
        	$('#DivUpdateError').text('');
        	$('#division_newError').text('');
        	$('#division_name_newError').text('');
        	$('#railway_zone_newError').text('');
        	$('#bttnUpdate').prop('disabled', false);
        	updateFlag = true;
        }

        $("#addDivisionForm").submit(function (e) {
        	if(validator.form()){ 
        		$(".page-loader").show();
        		$("#addUpdateModal").modal();
        		if(flag){
        			document.getElementById("addDivisionForm").submit();	
        		}
        		$(".page-loader").hide();
        		return false;
        	}
        });

        $("#updateDivisionForm").submit(function (e) {
        	if(validator1.form()){ 
        		$(".page-loader").show();
        		$("#addUpdateModal").modal();
        		if(updateFlag){
        			document.getElementById("updateDivisionForm").submit();	
        		}
        		$(".page-loader").hide();
        		return false;
        	}
        });
      
     
        var validator =  $('#addDivisionForm').validate({
            rules: {
                "division": {
                     required: true
                },"division_name": {
                     required: true
                },"railway_zone": {
                     required: true
                }
           },messages: {
                  "division": {
                     required: 'Required'
                 },"division_name": {
                     required: 'Required'
                 },"railway_zone": {
                     required: 'Required'
                 }
           },errorPlacement:function(error, element){
                if(element.attr("id") == "division_id" ){
                    document.getElementById("divisionError").innerHTML="";
                    error.appendTo('#divisionError');
                }else if(element.attr("id") == "division_name" ){
                    document.getElementById("division_nameError").innerHTML="";
                    error.appendTo('#division_nameError');
                }else if(element.attr("id") == "railway_zone" ){
                    document.getElementById("railway_zoneError").innerHTML="";
                    error.appendTo('#railway_zoneError');
                }
           }
       });

       var validator1 =  $('#updateDivisionForm').validate({
            rules: {
                "division_new": {
                     required: true
                },"division_name_new": {
                     required: true
                },"railway_zone_new": {
                     required: true
                }
           },messages: {
                  "division_new": {
                     required: 'Required'
                 },"division_name_new": {
                     required: 'Required'
                 },"railway_zone_new": {
                     required: 'Required'
                 }
           },errorPlacement:function(error, element){
                if(element.attr("id") == "division_new" ){
                    document.getElementById("division_newError").innerHTML="";
                    error.appendTo('#division_newError');
                }else if(element.attr("id") == "division_name_new" ){
                    document.getElementById("division_name_newError").innerHTML="";
                    error.appendTo('#division_name_newError');
                }else if(element.attr("id") == "railway_zone_new" ){
                    document.getElementById("railway_zone_newError").innerHTML="";
                    error.appendTo('#railway_zone_newError');
                }
           }
       });

      
      $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	     });
      
      function updateRow(no) {
    	    var divisionId = $('#divisionId' + no).val();
    	    var divisionName = $('#divisionName' + no).val();
    	    var railwayZone = $('#railwayZone' + no).val();
    	    var createdDate = $('#createdDate' + no).val();

    	    $('#division_id_old').val($.trim(divisionId));
    	    $('#division_name_old').val($.trim(divisionName));
    	    $('#railway_zone_old').val($.trim(railwayZone));
    	    $('#created_date_old').val($.trim(createdDate));

    	    $('#onlyUpdateModal').modal('open');
    	    $('#onlyUpdateModal #division_id_new').val($.trim(divisionId)).focus();
    	    $('#onlyUpdateModal #division_name_new').val($.trim(divisionName)).focus();
    	    $('#onlyUpdateModal #railway_zone_new').val($.trim(railwayZone)).focus();
    	    $('#onlyUpdateModal #created_date_new').val($.trim(createdDate)).focus();
    	}

    	function deleteRow(val) {
    	    $("#division_id").val(val);
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
 	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-department');
 	    	    	$('#getForm').submit();
 	           }else {
 	                swal("Cancelled", "Record is safe :)", "error");
 	            }
 	        });
 	    }
      
    </script>

</body>

</html>