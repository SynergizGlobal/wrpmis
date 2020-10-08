<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Safety Equipment </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <style>
         .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        #example4 {
            border: 1px solid #ddd;
        }

        #example4 .datepicker~button {
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
        }

        .filevalue {
            display: block;
            margin-top: 10px;
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
                                <h6>Add / Edit Safety Equipment</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <p><label> Project ID </label></p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <p><label> Work ID </label></p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <p> <label>Contract ID </label></p>
                                            <select class="searchable">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row fixed-width" style="margin-bottom: 40px;">
                                <h5 class="center-align">Equipment Details</h5>
                                <div class="table-inside">
                                    <table id="example4" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Equipment No</th>
                                                <th>Equipment Details</th>
                                                <th> Validity of Equipment</th>
                                                <th>Remarks </th>
                                                <th> Attachment</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input id="equip_no1" type="text" class="validate"
                                                        placeholder="Equipment No">
                                                </td>
                                                <td>
                                                    <input id="equip_details" type="text" class="validate"
                                                        placeholder="Equipment Details">
                                                </td>
                                                <td>
                                                    <input id="validity_1" type="text" class="validate datepicker"
                                                        placeholder="Validity of Equipment">
                                                    <button type="button" id="validity_1_icon" class="white"><i
                                                            class="fa fa-calendar"></i></button>
                                                </td>
                                                <td>
                                                    <input id="completed_cost" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td>
                                                <td>
                                                    <div class="">
                                                        <input type="file" name="myfile" id="myFile"
                                                            style="display:none" />
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
                                                <td></td>
                                                <td></td>
                                                <td></td>
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
                            </div>
                           
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button style="width: 100%;"
                                            class="btn waves-effect waves-light bg-m black-text">Add / Edit</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s black-text"
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $("#validity_1").datepicker();
            $('#validity_1_icon').click(function () {
                event.stopPropagation();
                $('#validity_1').click();
            });
            $("#myFile").change(function () {
                filename1 = $('#myFile')[0].value;
                $('#fileVal').html(filename1);
                console.log(filename1)
            });
        });
    </script>

</body>

</html>