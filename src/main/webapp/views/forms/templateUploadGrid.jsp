<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Form</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <style>
        p a,
        td a {
            color: blue;
            text-decoration: none;
        }

        .mdl-data-table td:last-child {
            white-space: inherit;
            text-align: center !important;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        th {
            text-transform: capitalize;
        }

        .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 30px !important;
            padding: 1rem;
        }

        .mdl-data-table tbody tr thead tr:hover {
            background-color: #2E58AD;
        }

        .mdl-data-table tbody tr .modal {
            min-height: 50vh;
        }

        svg {
            fill: #fff;
        }
    </style>
</head>

<body>

     <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Template Upload Form</h6>
                        </div>
                    </span>
                    <div class="">

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="upload-form-grid" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>template</th>
                                            <th>uploaded by </th>
                                            <th>uploaded on</th>
                                            <th>status</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>template Name</td>
                                            <td>uploaded by 1</td>
                                            <td>uploaded on 1</td>
                                            <td>status</td>
                                            <td class="last-column">
                                                <a href="#" class="btn waves-effect waves-light bg-m " title="Upload"><i
                                                        class="fa fa-upload"></i></a>
                                                <a href="#" class="btn waves-effect waves-light " title="Download"><i
                                                        class="fa fa-download"></i></a>
                                                <a href="#" class="btn waves-effect waves-light bg-s " title="Delete"><i
                                                        class="fa fa-trash"></i></a>
                                                <a href="#history1" class="btn waves-effect waves-light modal-trigger"
                                                    title="History"><svg xmlns="http://www.w3.org/2000/svg"
                                                        xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                                                        width="24" height="24" viewBox="0 0 24 24">
                                                        <path
                                                            d="M4,2C2.9,2 2,2.9 2,4V20C2,21.1 2.9,22 4,22H12.41C12.41,25.87 12.13,23 16,23C19.87,23 23,19.87 23,16C23,12.13 21.87,9.3 18,9.3V8L12,2H4M4,4H11V9H16C12.13,9 9,12.13 9,16C9,19.87 6.39,20 10.26,20H4V4M16,11C18.76,11 21,13.24 21,16C21,18.76 18.76,21 16,21C13.24,21 11,18.76 11,16C11,13.24 13.24,11 16,11M15,12V17L18.61,19.16L19.36,17.94L16.5,16.25V12H15Z" />
                                                    </svg></a>
                                                <div id="history1" class="modal">
                                                    <div class="modal-content">
                                                        <h6 class="headbg modal-header">Template Name <span
                                                                class="right modal-action modal-close"><span
                                                                    class="material-icons">close</span></span></h6>
                                                        <table class="responsive-table">
                                                            <thead>
                                                                <tr>
                                                                    <th>template </th>
                                                                    <th>uploaaded by</th>
                                                                    <th>uploaded on</th>
                                                                    <th>status</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td><a href="#" download="template">template</a>
                                                                    </td>
                                                                    <td>uploaaded by</td>
                                                                    <td>uploaded on</td>
                                                                    <td>status</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
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
 <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
    <!-- Modal Structure -->

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.modal').modal();
            $('#upload-form-grid').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [4] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });

    </script>
</body>

</html>