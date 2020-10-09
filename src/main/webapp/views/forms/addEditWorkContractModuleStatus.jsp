<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Safety Equipment </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        td:last-child,
        td:last-of-type {
            white-space: inherit;
        }
        .input-field .searchable_label {
            font-size: 0.8rem;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        .table-inside td span {
            text-align: left;
            display: block;
        }

        .primary-text {
            color: #2E58AD;
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
                                <h6> Work Contract Module Status</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Project</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select Project</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select Work</option>
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
                                    <p class="searchable_label">Contract</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select Contract</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="date" type="text" class="validate datepicker">
                                    <label for="date"> Date </label>
                                    <button id="date_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="container" style="margin-bottom: 30px;">
                                <div class="row">
                                    <div class="col m1 hide-on-small-only"></div>
                                    <div class="col m10 s12">
                                        <div class="row fixed-width">
                                            <h5 class="center-align">Module Status</h5>
                                            <div class="table-inside">
                                                <table id="example4" class="mdl-data-table">
                                                    <thead>
                                                        <tr>
                                                            <th>Module </th>
                                                            <th>Status </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_contract">Contract</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status1" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_design">Design</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status2" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_execution_monitoring">Execution
                                                                    Monitoring</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status3" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_finance">Finance</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status4" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_issues">Issues</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status5" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_land_acquisition">Land Aquisition</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status6" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_randr">R & R</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status7" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_risk">Risk</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status8" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_safety">Safety</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status9" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text"
                                                                    id="table_training">Training</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status10" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_training">Unmanned
                                                                    Aerial Vehicle</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status11" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_us">Utility
                                                                    Shifting</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status12" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <span class="primary-text" id="table_work">Work</span>
                                                            </td>
                                                            <td>
                                                                <textarea id="status13" class="materialize-textarea"
                                                                    data-length="1000"></textarea>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
  <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#textarea1').characterCounter();
            $('#date').datepicker();

            $('#date_icon').click(function () {
                event.stopPropagation();
                $('#date').click();
            });
        });
    </script>

</body>

</html>