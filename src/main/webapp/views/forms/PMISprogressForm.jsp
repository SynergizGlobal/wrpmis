<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PMIS Progress Form</title>
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
        .mdl-data-table {
            border: 1px solid #ccc;
        }

        .hiddendiv.common {
            width: 99vw !important;
        }

        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }

        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }

        .input-field .searchable_label {
            font-size: .9rem;
        }

        td,
        .date-holder {
            position: relative;
        }

        .date-holder .datepicker~button {
            top: 30%;
            right: 0;
        }

        td .datepicker~button {
            top: 30px;
        }

        td .datepicker {
            font-size: 0.87rem !important;
        }

        td .datepicker-table thead tr,
        td .datepicker-table thead tr:hover,
        td .datepicker-table tbody tr,
        td .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        td .datepicker-table td:first-of-type,
        td .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        td .datepicker-table th,
        td .datepicker-table td {
            padding: 0;
            border: 0;
        }

        @media only screen and (max-width: 700px) {
            div.dataTables_wrapper div.dataTables_filter input {
                width: 90% !important;
            }
        }
    </style>
</head>
<body>
  <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>PMIS Progress Form</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form id="stripChartForm" name="stripChartForm" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m10 s12">

                                <div class="row ">
                                    <div class="col m3 hide-on-small-only"></div>
                                    <div class="col m6 s12">
                                        <div class="row" style="margin-bottom: 0;">
                                            <div class="col m2 hide-on-small-only"></div>
                                            <div class="col s12 m4 input-field">
                                                <p class="searchable_label">Milestone</p>
                                                <select class="searchable">
                                                    <option value="" disabled selected>Select</option>
                                                    <option value="1">Option 1</option>
                                                    <option value="2">Option 2</option>
                                                    <option value="3">Option 3</option>
                                                </select>
                                            </div>
                                            <div class="col s12 m4 input-field">
                                                <button class="btn bg-m waves-effect waves-light t-c clear-filters "
                                                    style="margin-top: 8px;width: 100%;">Clear Filters</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col m3 hide-on-small-only"></div>
                                </div>
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col m8 s12 center-align" style="margin-top: 10px;">
                                        <a class="btn waves-effect bg-m" onclick="updateActual()">Finish
                                            Activities</a>
                                    </div>
                                    <div class="col m2 hide-on-small-only"></div>
                                </div>

                                <div class="row fixed-width" style="margin-bottom: 30px;">
                                    <div class="table-inside">
                                        <table class="mdl-data-table" id="example">
                                            <thead>
                                                <tr>
                                                    <th>
                                                        <p>
                                                            <label>
                                                                <input type="checkbox" name="select-all"
                                                                    id="select-all" />
                                                                <span></span>
                                                            </label>
                                                        </p>
                                                    </th>
                                                    <th>Milestone</th>
                                                    <th>Activity</th>
                                                    <th>Planned <br> Start Date</th>
                                                    <th>Planned <br> Finish Date</th>
                                                    <th>Actual <br> Start Date </th>
                                                    <th>Actual <br> Finish Date</th>
                                                    <th>Scope</th>
                                                    <th>Completed</th>
                                                    <th class="no-sort">Actual</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <p>
                                                            <label>
                                                                <input type="checkbox" name="activity_check"
                                                                    id="check_1" />
                                                                <span></span>
                                                            </label>
                                                        </p>
                                                    </td>
                                                    <td>Submission of Project Charter </td>
                                                    <td>Software Requirements Specifications (Submission)</td>
                                                    <td>11/12/2019</td>
                                                    <td>13/10/2020</td>
                                                    <td>
                                                        <div class="date-holder">
                                                            <input id="start_date" type="text"
                                                                class="validate datepicker" placeholder="Start Date"
                                                                value="11/12/2019">
                                                            <button type="button" id="start_date_icon" class="white"><i
                                                                    class="fa fa-calendar"></i></button>
                                                    </td>
                                    </div>
                                    <td>

                                        <div class="date-holder">
                                            <input id="finish_date" type="text" class="validate datepicker"
                                                placeholder="Finish Date" value="13/10/2020">
                                            <button type="button" id="finish_date_icon" class="white"><i
                                                    class="fa fa-calendar"></i></button>
                                        </div>
                                    </td>
                                    <td><span id="scope1">10</span></td>
                                    <td><span id="completed1">7</span></td>
                                    <td class="input-field">
                                        <input type="text" id="actual1" readonly>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <label>
                                                    <input type="checkbox" name="activity_check" id="check_2" />
                                                    <span></span>
                                                </label>
                                            </p>
                                        </td>
                                        <td>SRS</td>
                                        <td>Approval of Project Charter </td>
                                        <td>13/10/2018</td>
                                        <td>23/10/2020</td>
                                        <td>
                                            <div class="date-holder">
                                                <input id="start_date" type="text" class="validate datepicker"
                                                    placeholder="Start Date" value="11/12/2019">
                                                <button type="button" id="start_date_icon" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="date-holder">
                                                <input id="finish_date" type="text" class="validate datepicker"
                                                    placeholder="Finish Date" value="13/10/2020">
                                                <button type="button" id="finish_date_icon" class="white"><i
                                                        class="fa fa-calendar"></i></button>

                                            </div>
                                        </td>
                                        <td><span id="scope2">70</span></td>
                                        <td><span id="completed2">27</span></td>
                                        <td class="input-field">
                                            <input type="text" id="actual2" readonly>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <label>
                                                    <input type="checkbox" name="activity_check" id="check_3" />
                                                    <span></span>
                                                </label>
                                            </p>
                                        </td>
                                        <td>Project-1</td>
                                        <td>PMIS Installation in MRVC Server </td>
                                        <td>13/08/2019</td>
                                        <td>12/10/2020</td>
                                        <td>
                                            <div class="date-holder">

                                                <input id="start_date" type="text" class="validate datepicker"
                                                    placeholder="Start Date" value="11/12/2019">
                                                <button type="button" id="start_date_icon" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="date-holder">

                                                <input id="finish_date" type="text" class="validate datepicker"
                                                    placeholder="Finish Date" value="13/10/2020">
                                                <button type="button" id="finish_date_icon" class="white"><i
                                                        class="fa fa-calendar"></i></button>
                                            </div>
                                        </td>
                                        <td><span id="scope3">12</span></td>
                                        <td><span id="completed3">3</span></td>
                                        <td class="input-field">
                                            <input type="text" id="actual3" readonly>
                                        </td>
                                    </tr>

                                    </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m1 hide-on-small-only"></div>
                                <div class="col m10 s12">

                                    <input type="hidden" id="strip_chart_id" name="strip_chart_id" />
                                    <div class="row">
                                        <!-- <div class="col s12 m4">
                                                <div class="center-align m-1">
                                                    <button type="button" class="btn waves-effect waves-light bg-m"
                                                        style="width: 100%;" onclick="updateActual()">Finish
                                                        Activities</button>
                                                </div>
                                            </div> -->
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="button" class="btn waves-effect waves-light bg-m"
                                                    style="width: 100%;">Update</button>
                                            </div>
                                        </div>
                                        <div class="col s12 m6">
                                            <div class="center-align m-1">
                                                <button type="reset" class="btn waves-effect waves-light bg-s"
                                                    style="width: 100%;">Reset</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                </div>
                </form>
            </div>
        </div>
        <!-- form ends  -->
    </div>

    <!-- Page Loader starts-->
    <div class="page-loader" style="display: none;">
        <div class="preloader-wrapper big active">
            <div class="spinner-layer spinner-blue-only">
                <div class="circle-clipper left">
                    <div class="circle"></div>
                </div>
                <div class="gap-patch">
                    <div class="circle"></div>
                </div>
                <div class="circle-clipper right">
                    <div class="circle"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- Page Loader ends-->
    
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
            $('.searchable').select2();
            $('#start_date,#finish_date').datepicker();
            $('#start_date_icon').click(function () {
                event.stopPropagation();
                $('#start_date').click();
            });
            $('#finish_date_icon').click(function () {
                event.stopPropagation();
                $('#finish_date').click();
            });

            $('#example').DataTable({
                columnDefs: [
                    {
                        // targets: [0, 1, 2],
                        // className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [6] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                // paging: false,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
        // update actual function for single value with out ids
        /*  function updateActual(){
             $('input[name="activity_check"]').each(function(){
                 if($(this).prop('checked')){  
                     var scope_val=parseInt($(this).parent().closest('tr').find('td:last-of-type').prev().prev().children().text());
                     var completed_val=parseInt($(this).parent().closest('tr').find('td:last-of-type').prev().children().text());
                     var remaining=scope_val-completed_val;
                     var actual_val=$(this).parent().closest('tr').find('td:last-of-type').children().val(remaining);
                 }
             })           
         } */

        // update actual function for single value with ids
        function updateActual() {
            $('input[name="activity_check"]').each(function () {
                if ($(this).prop('checked')) {
                    let no = $(this).attr('id').split("_")[1];
                    let remaining = parseInt($('#scope' + no).text()) - parseInt($('#completed' + no).text());
                    $('#actual' + no).val(remaining);
                }
            })
        }

        // select or deselect all checkboxes 
        $('#select-all').change(function () {
            var _this = this;
            $('input[name="activity_check"]').each(function () {
                if ($(_this).is(':checked')) {
                    $(this).prop('checked', true);
                } else {
                    $(this).prop('checked', false);
                }
            });
        });

    </script>

</body>
</html>