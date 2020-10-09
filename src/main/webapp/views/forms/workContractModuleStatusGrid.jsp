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
        p a {
            color: blue
        }
  		td {
            word-break: break-word;
            word-wrap: break-word;
            white-space: initial;
        }
        .input-field .searchable_label {
            font-size: 0.8rem;
        }
        td:last-child,
        td:last-of-type {
            white-space: inherit;
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
                            <h6> Work Contract Module Status</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1 center-align">
                            <div class="col m4 hide-on-small-only"> </div>
                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="work_contract_module.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Work Contract Module
                                            Status</strong></a>
                                </div>
                            </div>
                            <div class="col m4 hide-on-small-only"> </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Project</p>
                                        <select class="searchable">
                                            <option value="" disabled selected>Select Project</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Work</p>
                                        <select class="searchable">
                                            <option value="" disabled selected>Select Work </option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Contract</p>
                                        <select class="searchable">
                                            <option value="" disabled selected>Select Contract </option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 20px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Project</th>
                                            <th>Work</th>
                                            <th>Contract</th>
                                            <th>Date</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="work_contract_module.html"
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
            $('#example').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [4] },
                ],
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                fixedHeader: true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
    </script>


</body>

</html>