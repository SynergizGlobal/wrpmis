<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
    
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Progress Upload</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4">
                                <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div>
                            </div>
                            <div class="col s12 m4">
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col m10">
                                <div class="row">
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select id="work" class="searchable">
                                            <option value="" disabled selected>Select</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Contract</p>
                                        <select id="contract" class="searchable">
                                            <option value="" disabled selected>Select</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Structure</p>
                                        <select id="structure" class="searchable">
                                            <option value="" disabled selected>Select</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Component ID</p>
                                        <select id="componentid" class="searchable">
                                            <option value="" disabled selected>Select</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <p class="searchable_label">Component</p>
                                        <select id="component" class="searchable">
                                            <option value="" disabled selected>Select</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m2">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                            Filters</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col m1 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract</th>
                                            <th>Structure</th>
                                            <th>Component <br>ID</th>
                                            <th>Component</th>
                                            <th>Activity</th>
                                            <th>Planned <br> Start</th>
                                            <th>Planned <br> Finish</th>
                                            <th>Scope</th>
                                            <th>Completed</th>
                                            <th>Weightage</th>
                                            <!-- <th class="no-sort">Action</th> -->
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
                                            <td></td>
                                            <td></td>
                                            <!-- <td class="last-column"> <a href="progress_upload.html"
                                                    class="btn waves-effect waves-light bg-m t-c"><i
                                                        class="fa fa-pencil"></i></a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c"><i
                                                        class="fa fa-trash"></i></a>
                                            </td> -->
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
            $('#example').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [9] },
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
        function clearFilters() {
            $('#component').val("");
            $('#structure').val("");
            $('#work').val("");
            $('#contract').val("");
            $('#componentid').val("");
            $('.searchable').select2();
        }
    </script>
<body>

</body>
</html>