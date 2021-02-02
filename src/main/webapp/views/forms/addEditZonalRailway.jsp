<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Zonal Railway</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/zonal.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .row.fixed-width {
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        #zonal_railway_table thead tr th,
        #zonal_railway_table {
            border: 1px solid #ddd;
            vertical-align: middle;
            text-align: center;
        }

        #zonal_railway_table td input[type="month"] {
            border: 0;
            border-bottom: 1px solid #999;
            width: 130px !important;
            background-color: transparent;
        }

        /* .datepicker-table thead tr,
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
        } */

        .input-field .searchable_label {
            font-size: .85rem;
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
                                <h6>Add / Edit Zonal Railway</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Project </p>
                                    <select class="searchable" id="project_id" name="project_id">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <span id="project_idError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Work </p>
                                    <select class="searchable" id="work_id" name="work_id">
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
                                    <p class="searchable_label">Execution Agency </p>
                                    <select class="searchable" id="execution_agency" name="execution_agency">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Contract :</p>
                                    <p>value goes here</p>
                                    <!-- <select class="searchable">
                                            <option value="0" selected>Select</option>
                                            <option value="1">Agency 1</option>
                                            <option value="2">Agency 2</option>
                                            <option value="3">Agency 3</option>
                                        </select> -->
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <p class="searchable_label">Source of Fund </p>
                                            <select class="searchable" id="source_of_fund" name="source_of_fund">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <p class="searchable_label">Status</p>
                                            <select class="searchable" id="status" name="status">
                                                <option value="0" selected>Select</option>
                                                <option value="1">Agency 1</option>
                                                <option value="2">Agency 2</option>
                                                <option value="3">Agency 3</option>
                                            </select>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="asOnDate" type="text" class="validate datepicker"
                                                name="asOnDate">
                                            <label for="asOnDate">As on Date</label>
                                            <button type="button" id="asOnDate__icon" class="white datepicker-btn"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="sanction_cost" name="sanction_cost" type="text" class="validate">
                                    <label for="sanction_cost">Sanction Cost</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <i class="material-icons prefix center-align">₹</i>
                                    <input id="latest_revised_cost" name="latest_revised_cost" type="text"
                                        class="validate">
                                    <label for="latest_revised_cost">Latest Revised Cost</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                     <i class="material-icons prefix center-align">₹</i>	
                                    <input id="cumilative_expenditure" name="cumilative_expenditure" type="text"
                                        class="validate">
                                    <label for="cumilative_expenditure">Cumilative Expenditure </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="completion_cost" name="completion_cost" type="text"
                                        class="validate">
                                    <label for="completion_cost">Completion Cost</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <input id="actual_start" name="actual_start" type="text"
                                                class="validate datepicker">
                                            <label for="actual_start">Actual Start</label>
                                            <button type="button" id="actual_start__icon"
                                                class="white datepicker-btn"><i class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="expected_finish" name="expected_finish" type="text"
                                                class="validate datepicker">
                                            <label for="expected_finish">Expected Finish</label>
                                            <button type="button" id="expected_finish__icon"
                                                class="white datepicker-btn"><i class="fa fa-calendar"></i></button>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="actual_finish" name="actual_finish" type="text"
                                                class="validate datepicker">
                                            <label for="actual_finish">Actual Finish</label>
                                            <button type="button" id="actual_finish__icon"
                                                class="white datepicker-btn"><i class="fa fa-calendar"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>

                        <div class="row fixed-width" style="margin-bottom: 40px;">
                            <h5 class="center-align">Zonal Railway Details</h5>
                            <div class="table-inside">
                                <table id="zonal_railway_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th rowspan="2" class="fw-125">Month</th>
                                            <th colspan="4">Expenditure </th>
                                            <th colspan="2">Physical Progress</th>
                                            <th rowspan="2">Progress</th>
                                            <th rowspan="2">Issue</th>
                                            <th rowspan="2">Assistance <br>Required</th>
                                            <th rowspan="2">Action</th>
                                        </tr>
                                        <tr>
                                            <th>Cum Actual <br>Current FY (in Cr)</th>
                                            <th>Cum planned %</th>
                                            <th>Cum Actual (in Cr)</th>
                                            <th>Cum Actual %</th>
                                            <th>Cum planned %</th>
                                            <th>Cum Actual %</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <input id="month0" name="month" type="month" class="validate"
                                                    placeholder="Month">
                                            </td>
                                            <td>
                                                <input id="cum_actual_current_fy0" name="cum_actual_current_fy"
                                                    type="number" class="validate" min="0.01" step="0.01"
                                                    placeholder="Amount">
                                            </td>
                                            <td>
                                                <input id="cum_planned_percent0" name="cum_planned_percent"
                                                    type="number" class="validate" min="0.01" step="0.01"
                                                    placeholder="Cum Planned %">
                                            </td>
                                            <td>
                                                <input id="cum_actual0" name="cum_actual" type="number" class="validate"
                                                    min="0.01" step="0.01" placeholder="cum Actual">
                                            </td>
                                            <td>
                                                <input id="cum_actual_percent0" name="cum_actual_percent" type="number"
                                                    class="validate" min="0.01" step="0.01" placeholder="cum Actual %">
                                            </td>
                                            <td>
                                                <input id="physical_cum_planned_percent0"
                                                    name="physical_cum_planned_percent" type="number" class="validate"
                                                    min="0.01" step="0.01" placeholder="Cum Planned %">
                                            </td>
                                            <td>
                                                <input id="physical_cum_actual_percent0"
                                                    name="physical_cum_actual_percent" type="number" class="validate"
                                                    min="0.01" step="0.01" placeholder="cum Actual %">
                                            </td>
                                            <td>
                                                <input id="progress0" name="progress" type="text" class="validate"
                                                    placeholder="Progress">
                                            </td>
                                            <td>
                                                <input id="issue0" name="issue" type="text" class="validate"
                                                    placeholder="Issue">
                                            </td>
                                            <td>
                                                <label><input type="checkbox" checked="checked"
                                                        id="assistance_required0" name="assistance_required" />
                                                    <span></span></label>
                                            </td>
                                            <td>
                                                <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                        class="fa fa-close"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="11">
                                                <a href="#" class="btn waves-effect waves-light bg-m t-c "
                                                    onclick="addZonalRow()"> <i class="fa fa-plus"></i></a>
                                            </td>
                                        </tr>

                                    </tbody>
                                </table>

                            </div>
                        </div>

                        <!-- Modal Structure -->
                        <div id="modal1" class="modal">
                            <div class="modal-content center-align">
                                <h6 class="headbg bg-s p-2">Upload File</h6>
                                <div class="container">
                                    <div class="row">
                                        <div class="col m12 s12">
                                            <p>Select File to Upload</p>
                                            <div class="file-field input-field">
                                                <div class="btn bg-m">
                                                    <span>Attachment</span>
                                                    <input type="file">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col s6 m6">
                                            <div class="center-align m-1">
                                                <button style="width: 100%;"
                                                    class="btn waves-effect waves-light bg-m ">Submit</button>
                                            </div>
                                        </div>
                                        <div class="col s6 m6">
                                            <div class="center-align m-1">
                                                <button style="width: 100%;"
                                                    class="btn waves-effect waves-light bg-s modal-close">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
        $(document).on('focus', '.datepicker', function () {
            $(this).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            })
        });
        $(document).on('focus', '.datepicker-btn', function () {
            var dateId = $(this).attr('id').split("__")[0];
            $('#' + dateId).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            });
            $('#' + dateId).datepicker('open');

        });

        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        });

        var zonalno = 1;
        function addZonalRow() {
            var text = '<tr> <td> <input id="month' + zonalno + '" name="month" type="month" class="validate" placeholder="Month"> </td>' +
                '<td> <input id="cum_actual_current_fy' + zonalno + '" name="cum_actual_current_fy" type="number" class="validate" min="0.01" step="0.01" placeholder="Amount">' +
                '</td> <td> <input id="cum_planned_percent' + zonalno + '" name="cum_planned_percent" type="number" class="validate" min="0.01" step="0.01" placeholder="Cum Planned %">' +
                '</td> <td> <input id="cum_actual' + zonalno + '" name="cum_actual" type="number" class="validate" min="0.01" step="0.01" placeholder="cum Actual"> </td>' +
                '<td> <input id="cum_actual_percent' + zonalno + '" name="cum_actual_percent" type="number" class="validate" min="0.01" step="0.01" placeholder="cum Actual %"> </td>' +
                '<td> <input id="physical_cum_planned_percent0" name="physical_cum_planned_percent" type="number" class="validate" min="0.01" step="0.01" placeholder="Cum Planned %">' +
                '</td> <td> <input id="physical_cum_actual_percent' + zonalno + '" name="physical_cum_actual_percent" type="number" class="validate" min="0.01" step="0.01" placeholder="cum Actual %"> ' +
                '</td> <td> <input id="progress' + zonalno + '" name="progress" type="text" class="validate" placeholder="Progress"> </td> <td>' +
                '<input id="issue' + zonalno + '" name="issue" type="text" class="validate" placeholder="Issue"> </td> <td>' +
                '<label><input type="checkbox" checked="checked" id="assistance_required' + zonalno + '" name="assistance_required" /> <span></span></label> </td>' +
                '<td> <a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
            $('#zonal_railway_table tbody').find('tr:last').prev().after(text);
            trainno++;
        }
    </script>

</body>

</html>