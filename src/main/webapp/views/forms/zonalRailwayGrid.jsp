<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zonal Railway</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/zonal.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .fw-370 {
            width: 370px;
            max-width: 370px;
        }

        .fw-80 {
            width: 80px !important;
            max-width: 80px;
        }

        .no-sort.sorting_disabled {
            text-align: center;
        }
    </style>
</head>

<body>
    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Zonal Railway</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                                <!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="zonal_railway_form.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Zonal Railway</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <!-- <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div> -->
                            </div>
                        </div>

                        <div class="row no-mar">
                            <form action="#">
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Project</p>
                                    <select class="searchable" id="project_id" name="project_id">
                                        <option value="">Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select class="searchable" id="work_id" name="work_id">
                                        <option value="">Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Execution Agency</p>
                                    <select class="searchable" id="execution_agency" name="execution_agency">
                                        <option value="">Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Source of Fund</p>
                                    <select class="searchable" id="source_of_fund" name="source_of_fund">
                                        <option value="">Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Status</p>
                                    <select class="searchable" id="status" name="status">
                                        <option value="">Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 6px;width: 100%;" onclick="clearFilters()">Clear
                                        Filters</button>
                                </div>
                            </form>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">

                                <table id="zonal_railway_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="fw-370">Work</th>
                                            <th>Execution <br>Agency</th>
                                            <th>Source of<br> Fund</th>
                                            <th>Status</th>
                                            <th>As on<br> Date</th>
                                            <th>Expected <br>Finish</th>
                                            <th>Actual <br>Finish</th>
                                            <th>Cumilative <br>Expenditure</th>
                                            <th class="no-sort fw-80">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="zonal_railway_form.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>

                                        </tr>

                                    </tbody>

                                </table>

                            </div>
                        </div>
                    </div>
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
            $('.searchable').select2();
            $('#zonal_railway_table').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [8] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                // "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });

        function clearFilters() {
            $('#project_id').val('');
            $('#work_id').val('');
            $('#execution_agency').val('');
            $('#source_of_fund').val('');
            $('#status').val('');
            $('.searchable').select2();
        }
    </script>

</body>

</html>