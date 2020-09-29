<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FOB</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">	
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/fob.css">
	
	 <style>
        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
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
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit FOB</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">select1</option>
                                        <option value="2">select2</option>
                                    </select>
                                    <label>Project ID</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">select1</option>
                                        <option value="2">select2</option>
                                    </select>
                                    <label>Work ID</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">select1</option>
                                        <option value="2">select2</option>
                                    </select>
                                    <label>Contract ID</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="fob_id" type="text" class="validate">
                                    <label for="fob_id">FOB ID </label>
                                </div>
                                <!-- </div>
                                </div> -->
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="contractor_name" type="text" class="validate">
                                    <label for="contractor_name">FOB Name</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select id="contract_id">
                                        <option value="0" selected>Select</option>
                                        <option value="1">select1</option>
                                        <option value="2">select2</option>
                                    </select>
                                    <label for="contract_id">Work Status</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="target_date" type="text" class="validate datepicker">
                                    <label for="target_date">Target Date </label>
                                    <button type="button" id="target_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="estimated_cost" type="text" class="validate">
                                    <label for="estimated_cost">Estimated Cost </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="construction_start_date" type="text" class="validate datepicker">
                                    <label for="construction_start_date">Construction Start Date </label>
                                    <button type="button" id="construction_start_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="commissioning_date" type="text" class="validate datepicker">
                                    <label for="commissioning_date">Commissioning Date </label>
                                    <button type="button" id="commissioning_date_icon"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="ac_date" type="text" class="validate datepicker">
                                    <label for="ac_date">Actual Completion Date </label>
                                    <button type="button" id="ac_date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="completion_cost" type="text" class="validate">
                                    <label for="completion_cost">Completion Cost </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="latitude" type="text" class="validate">
                                    <label for="latitude">Latitude
                                    </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="longitude" type="text" class="validate">
                                    <label for="longitude">Longitude </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                            <div class="row fixed-width">
                                <h5 class="center-align">FOB Details</h5>
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 ">
                                    <table id="example4" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>FOB Detail </th>
                                                <th>Value </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>                                           
                                            <tr>
                                                <td>
                                                    <input id="fob_detail_1" type="text" class="validate"
                                                        value="FOB Length (m) ">
                                                </td>
                                                <td>
                                                    <input id="fob_value_1" type="text" class="validate"
                                                        placeholder="Value">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input id="fob_detail" type="text" class="validate"
                                                        value="FOB Width (m)">
                                                </td>
                                                <td>
                                                    <input id="fob_value" type="text" class="validate"
                                                        placeholder="Value">
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-m t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

                            </div>

                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea1">Remarks</label>
                                </div>
                            </div>

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
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	    <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('.sidenav').sidenav();
            $("#doa_date,#target_date,#ac_date,#commissioning_date,#construction_start_date").datepicker();
            $('#textarea1').characterCounter();
            $('#doa_date_icon').click(function () {
                event.stopPropagation();
                $('#doa_date').click();
            });
            $('#target_date_icon').click(function () {
                event.stopPropagation();
                $('#target_date').click();
            });
            $('#ac_date_icon').click(function () {
                event.stopPropagation();
                $('#ac_date').click();
            });
            $('#commissioning_date_icon').click(function () {
                event.stopPropagation();
                $('#commissioning_date').click();
            });
            $('#construction_start_date_icon').click(function () {
                event.stopPropagation();
                $('#construction_start_date').click();
            });

        });
    </script>
	
</body>
</html>