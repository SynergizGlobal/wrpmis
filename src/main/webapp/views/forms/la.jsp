<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Land Aquisition</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
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
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
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
                            <h6>Land Aquisition</h6>
                        </div>
                    </span>
                    <div class="">
                        <!-- <div class="row plr-1">
                            <div class="col s12 m4">
                                <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div>
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="la.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Land Aquisition</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Select Work</p>
                                <select id="work_fk" class="searchable" name="work_fk">
                                    <option value="" disabled selected>Select Work</option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Select Village</p>
                                <select id="village_fk" class="searchable" name="village_fk">
                                    <option value="" disabled selected>Select Village</option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Select Type of Land</p>
                                <select id="land_type_fk" class="searchable" name="land_type_fk">
                                    <option value="" disabled selected>Select Type of Land</option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Select Sub Category</p>
                                <select id="sub_category_fk" class="searchable" name="sub_category_fk">
                                    <option value="" disabled selected>Select Sub Category</option>
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
                            <div class="col m1 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Survey Number</th>
                                            <th> Work</th>
                                            <th> Village</th>
                                            <th> Type of Land</th>
                                            <th> Sub Category of Land</th>
                                            <th> Area of Plot</th>                                           
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

    <!-- footer  -->
  <jsp:include page="../layout/footer.jsp"></jsp:include>
  
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>

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
                    { "width": "20px", "targets": [6] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });

        function clearFilters() {
            $('#work_fk').val("");
            $('#village_fk').val("");
            $('#land_type_fk').val("");
            $('#sub_category_fk').val("");
            $('.searchable').select2();

        }
    </script>
</body>

</html>