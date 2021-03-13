<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
     	 <c:if test="${action eq 'edit'}">Update Form</c:if>
		 <c:if test="${action eq 'add'}"> Add Form</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
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

        .fw-8p {
            width: 8%;
        }

        #form_form_table td>.btn {
            padding: 0 12px;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }

        input[type=url]:not(.browser-default):focus:not([readonly])+label {
            color: #2E58AD !important;
        }

        input[type=url]:not(.browser-default):focus:not([readonly]) {
            border-bottom: 1px solid #2E58AD;
            box-shadow: 0 1px 0 0 #2E58AD;
        }

        .select2-container--default .select2-selection--single {
            background-color: transparent;
        }
    </style>
</head>

<body>

    <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="form_name" name="form" type="text" class="validate">
                                    <label for="form_name">Form Name</label>
                                    <span id="form_nameError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Module </p>
                                    <select class="searchable" id="module" name="module">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="moduleError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input list="folder_list" name="folder" id="folder" type="text">
                                    <label for="folder">Folder</label>
                                    <datalist id="folder_list">
                                        <option value="Edge">
                                        <option value="Firefox">
                                        <option value="Chrome">
                                    </datalist>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="priority" name="priority" type="number" class="validate">
                                    <label for="priority">Priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="web_url" name="web_url" type="url" class="validate">
                                    <label for="web_url">Web Url </label>
                                    <span id="web_urlError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="mobile_url" name="mobile_url" type="url" class="validate">
                                    <label for="mobile_url">Mobile Url </label>
                                    <span id="mobile_urlError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="status" class="searchable" name="status">
                                        <option value="">Select</option>
                                        <option value="Govt">Govt </option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row" style="margin-bottom: 20px;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">Form Details</h5>
                                        <div class="table-inside">
                                            <table id="form_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th>Access Type </th>
                                                        <th>Access Value </th>
                                                        <th class="fw-8p">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">
                                                            <select id="access_type" class="searchable"
                                                                name="access_type">
                                                                <option value="">Select</option>
                                                            </select>
                                                            <span id="access_type0Error" class="error-msg"></span>
                                                        </td>
                                                        <td class="input-field">
                                                            <select id="access_value" class="searchable"
                                                                name="access_value">
                                                                <option value="">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                        <td>
                                                            <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="3">
                                                            <a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a>
                                                        </td>

                                                    </tr>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <!-- </div> -->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button style="width: 100%;" class="btn waves-effect waves-light bg-m">Add /
                                            Edit</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
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
	
    <!-- footer  -->
     <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });
        No = 1
        function addRow() {
            var text = '<tr><td class="input-field"> <select id="access_type' + No + '" class="searchable" name="access_type"> ' +
                '<option value="">Select</option> </select> <span id="access_type' + No + 'Error" class="error-msg"></span> </td>' +
                '<td class="input-field"><select id="access_value' + No + '" class="searchable" name="access_value">' +
                '<option value="">Select</option> </select> <span id="access_value' + No + 'Error" class="error-msg"></span> </td>' +
                '<td><a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
            $('#form_form_table').find('tr:last').prev().after(text);
            $('.searchable').select2();
            No++;
        }
    </script>

</body>

</html>