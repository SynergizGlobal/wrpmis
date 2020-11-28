<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Risk</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
        <style>
        textarea::placeholder {
            color: #444;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td {
            position: relative;
        }

        #riskReview {
            border: 1px solid #ddd;
        }

        #riskReview .datepicker~button {
            top: 30px;
        }

        #riskReview td .select2-container {
            width: 120px;
            max-width: 120px;
            /* margin-top: 8px; */
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

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
        
       tbody .select2-container--default .select2-selection--single {
        	background-color:transparent;
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
                                <h6>Add Risks</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m4 s12 input-field">
                                    <p class="searchable_label">Project</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">impact 1</option>
                                        <option value="2">impact 2</option>
                                        <option value="3">impact 3</option>
                                    </select>
                                </div>
                                <div class="col m4 s12 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">impact 1</option>
                                        <option value="2">impact 2</option>
                                        <option value="3">impact 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <!-- row 1  -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="riskid" type="text" class="validate">
                                    <label for="riskid">Risk ID </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="date_of_identification">
                                    <label for="date_of_identification">Date of Identification</label>
                                    <button id="date_of_identification_icon"><i class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 2 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Area</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">impact 1</option>
                                        <option value="2">impact 2</option>
                                        <option value="3">impact 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Sub Area</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">owner 1</option>
                                        <option value="2">owner 2</option>
                                        <option value="3">owner 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                        <div class="row fixed-width" style="margin-bottom: 40px;">
                            <h5 class="center-align">Risk Review</h5>
                            <div class="table-inside">
                                <table id="riskReview" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Date Of Review</th>
                                            <th>Owner</th>
                                            <th>Responsible <br> Person</th>
                                            <th>Probability</th>
                                            <th>Impact</th>
                                            <th>Mitigation <br> Plan</th>
                                            <th>Action Taken</th>
                                            <th>ATR Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <input id="reveiw_date0" type="text" class="validate datepicker"
                                                    placeholder="Date">
                                                <button type="button" id="reveiw_date0_icon"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <input id="owner0" type="text" class="validate" placeholder="Owner">
                                            </td>
                                            <td>
                                                <input id="responsible0" type="text" class="validate"
                                                    placeholder="Responsible Person">
                                            </td>
                                            <td>
                                                <select class="searchable" id="probability0">
                                                    <option value="0" selected>Probability</option>
                                                    <option value="1">owner 1</option>
                                                    <option value="2">owner 2</option>
                                                    <option value="3">owner 3</option>
                                                </select>
                                            </td>

                                            <td>
                                                <select class="searchable" id="impact0">
                                                    <option value="0" selected>Impact</option>
                                                    <option value="1">owner 1</option>
                                                    <option value="2">owner 2</option>
                                                    <option value="3">owner 3</option>
                                                </select>
                                            </td>
                                            <td>
                                                <textarea id="mitigation_plan0" class="materialize-textarea"
                                                    placeholder="Mitigation Plan"></textarea>
                                            </td>
                                            <td>
                                                <textarea id="action_taken0" class="materialize-textarea"
                                                    placeholder="Action Taken"></textarea>
                                            </td>
                                            <td>
                                                <input id="atr_date0" type="text" class="validate datepicker"
                                                    placeholder="ATR  Date">
                                                <button type="button" id="atr_date0_icon"><i
                                                        class="fa fa-calendar"></i></button>
                                            </td>
                                            <td>
                                                <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                        <tr class="last-row">
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>
                                                <a onclick="addRow()" class="btn waves-effect waves-light bg-m t-c "> <i
                                                        class="fa fa-plus"></i></a>
                                            </td>
                                        </tr>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="container container-no-margin">
                            <!-- <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <p class="searchable_label">Category</p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">probability 1</option>
                                        <option value="2">probability 2</option>
                                        <option value="3">probability 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">

                                    <input id="priority" type="number" class="validate" style="margin-top: 5px;">
                                    <label for="priority">Priority</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m4 input-field">
                                   <p class="searchable_label">Responsible Person </p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Status 1</option>
                                        <option value="2">Status 2</option>
                                        <option value="3">Status 3</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">

                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea3" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea3">Action Taken / Remarks</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="target_date"
                                        placeholder="Target Date of Mitigation">
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="mitigated_on" placeholder="Mitigated On">
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field file-field">

                                    <div class="btn bg-m">
                                        <span>Attach Risk Analysis Report</span>
                                        <input type="file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text">
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div> -->


                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <input type="submit" value="Create" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;">
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <input type="button" value="Cancel" class="btn waves-effect waves-light bg-s"
                                            style="width: 100%;">
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
            $("#reveiw_date0,#date_of_identification").datepicker();
            $('#reveiw_date0_icon').click(function () {
                event.stopPropagation();
                $('#reveiw_date0').click();
            });
            $('#date_of_identification_icon').click(function () {
                event.stopPropagation();
                $('#date_of_identification').click();
            });
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
            $('#textarea3').characterCounter();
        });
        riskNo = 1
        function addRow() {
            var text = '<tr>' + '<td><input id="reveiw_date' + riskNo + '" type="text" class="validate datepicker" placeholder="Date">' +
                '<button type="button" id="reveiw_date' + riskNo + '_icon"><i class="fa fa-calendar"></i></button> </td>' +
                '<td> <input id="owner' + riskNo + '" type="text" class="validate" placeholder="Owner"></td>' +
                '<td><input id="responsible' + riskNo + '" type="text" class="validate" placeholder="Responsible Person"></td>' +
                '<td><select class="searchable" id="probability' + riskNo + '"> <option value="0" selected>Probability</option>' +
                '<option value="1">owner 1</option>option value="2">owner 2</option><option value="3">owner 3</option> </select> </td>' +
                '<td><select class="searchable" id="impact' + riskNo + '"><option value="0" selected>Impact</option><option value="1">owner 1</option>' +
                '<option value="2">owner 2</option><option value="3">owner 3</option></select></td>' +
                '<td><textarea id="mitigation_plan' + riskNo + '" class="materialize-textarea" placeholder="Mitigation Plan"></textarea></td>' +
                '<td><textarea id="action_taken' + riskNo + '" class="materialize-textarea" placeholder="Action Taken"></textarea></td>' +
                '<td> <input id="atr_date' + riskNo + '" type="text" class="validate datepicker" placeholder="ATR  Date"> <button type="button" id="atr_date0_icon">' +
                '<i class="fa fa-calendar"></i></button></td>' +
                '<td> <a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>  </tr>';
            $('#riskReview').find('tr:last').prev().after(text);

            $("#reveiw_date" + riskNo).datepicker();
            $('#reveiw_date' + riskNo + '_icon').click(function () {
                event.stopPropagation();
                $('#reveiw_date' + riskNo).click();
            });
            $("#atr_date" + riskNo).datepicker();
            $('#atr_date' + riskNo + '_icon').click(function () {
                event.stopPropagation();
                $('#atr_date' + riskNo).click();
            });
            $('.searchable').select2();
            riskNo++;

        }
    </script>

</body>

</html>