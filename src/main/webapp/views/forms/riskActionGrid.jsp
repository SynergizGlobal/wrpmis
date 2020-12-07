<%@ page language="java" contentType="text/html;  pageEncoding=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Risk Action </title>
	<link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        td:last-child,
        td:last-of-type {
            white-space: inherit;
        }

        .last-column a+a {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
 	<!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

    <div class="row" style="margin-bottom: 0;">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Risk Action</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4 l-align"> </div>

                            <div class="col s12 m4 c-align">
                                <div class="m-1 ">
                                    <a href="risks_action.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Risks</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align"> </div>
                        </div>

                        <!--if  model 2 -->
                        <div class="row no-mar">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Work</p>
                                <select class="searchable" id="work_fk">
                                    <option value="" disabled selected>Select </option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Area</p>
                                <select class="searchable" id="area_fk">
                                    <option value="" disabled selected>Select </option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Assessment Date </p>
                                <select class="searchable" id="date_fk">
                                    <option value="" disabled selected>Select </option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Responsible Person </p>
                                <select class="searchable" id="responsible_fk">
                                    <option value="" disabled selected>Select </option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 8px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                            <div class="col m1 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="risks-action" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Risk Id</th>
                                            <th>Area</th>
                                            <th>Sub Area</th>
                                            <th>Assessment Date</th>
                                            <th>Responsible Person</th>
                                            <th>ATR Date</th>
                                            <th>Action Taken</th>
                                            <th class="no-sort">Action</th>
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
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c"><i
                                                        class="fa fa-pencil"></i></a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c"><i
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
   	  <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#risks-action').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [7] },

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
            $('#work_fk').val('');
            $('#area_fk').val('');
            $('#date_fk').val('');
            $('#responsible_fk').val('');
            $('.searchable').select2();
        }

    </script>
</body>
</html>