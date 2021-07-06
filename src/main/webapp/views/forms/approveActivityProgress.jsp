 <%@page import="com.synergizglobal.pmis.constants.CommonConstants2" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Approve Activity Progress - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="pmis/resources/css/la.css">
    <link rel="stylesheet" href="pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"        href="pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"        href="pmis/resources/css/mobile-responsive-table.css" />
    <style>
        thead th input[type="checkbox"]+span:not(.lever):before {
            border: 2px solid #fff;
        }

        thead th input[type="checkbox"]:checked+span:not(.lever):before {
            border-right: 2px solid #fff;
            border-bottom: 2px solid #fff;
        }

        .btn-holder .btn+.btn {
            margin-left: 20px;
        }

        .error-msg label,
        .errMsg label,
        .errMsgCheck label {
            color: red !important;
        }

        .tabs {
            border-bottom: 1px solid #ddd;
            margin-bottom: 2.7rem;
        }

        .tabs .tab a {
            text-transform: capitalize;
            color: blue;
        }

        .tabs .tab a:hover,
        .tabs .tab a.active,
        .tabs .tab a:focus,
        .tabs .tab a:focus.active {
            background-color: #e3e3f7;
            color: blue;
        }

        .tabs .indicator {
            background-color: blue;
        }

        a.bg-s.disabled>.fa {
            color: #9F9F9F !important;
        }

        .l-r-brdr {
            border-left: 1px solid #ddd;
            border-right: 1px solid #ddd;
        }

        .last-column .btn {
            padding: 0 5px;
            line-height: 1.5;
            height: auto;
        }

        .last-column .btn+.btn {
            margin-left: 10px;
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
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Approve Activity Progress</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <!-- <form action="<%=request.getContextPath() %>/update-pmis-progrss-form" id="progressForm"  name="progressForm" method="post"> -->

                    <div class="row">
                        <div class="col s12">
                            <ul class="tabs">
                                <li class="tab col s4"><a class="active" href="#pending_div">Pending</a></li>
                                <li class="tab col s4"><a href="#approved_div" class="l-r-brdr">Approved</a></li>
                                <li class="tab col s4"><a href="#rejected_div">Rejected</a></li>
                            </ul>
                        </div>
                        <div id="pending_div" class="col s12">
                            <div class="row no-mar">
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col s6 m4 l2 input-field offset-l1">
                                            <p class="searchable_label">Work</p>
                                            <select name="pending_work_id_fk" id="pending_work_id_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field ">
                                            <p class="searchable_label">Structure</p>
                                            <select name="pending_structure_fk" id="pending_structure_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Department</p>
                                            <select name="pending_department_fk" id="pending_department_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Updated By</p>
                                            <select name="pending_updated_by_fk" id="pending_updated_by_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>

                                            </select>
                                        </div>
                                        <div class="col s12 m4 l3 input-field center-align">
                                            <button class="btn bg-m waves-effect waves-light t-c clear-filters "
                                                onclick="clearFilter('pending');">Clear Filters</button>
                                        </div>
                                    </div>
                                    <span id="pending_checkBoxError" class="error-msg" style="text-align:center"></span>

                                    <span class="errMsg" id="pending_checkErrMsg">select at least one check box
                                    </span>
                                </div>
                                <span id="actualScopesError" class="error-msg" style="color:red"></span>
                            </div>

                            <div class="row no-mar" style="margin-bottom: 0;">
                                <div class="col m8 s12 center-align offset-m2 btn-holder">
                                    <a class="btn waves-effect t-c disabled" id="pending_approve-btn"><i
                                            class="fa fa-check"></i> Approve
                                    </a>
                                    <a class="btn waves-effect bg-s t-c disabled" id="pending_reject-btn"> <i
                                            class="fa fa-close"></i> Reject
                                    </a>
                                </div>
                            </div>

                            <div class="row fixed-width" style="margin-bottom: 30px;">
                                <div class="table-inside">
                                    <table class="mdl-data-table" id="datatable-table-pending">
                                        <thead>
                                            <tr>
                                                <th style=" text-align: center; vertical-align: bottom;">
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="pending_select-all"
                                                                id="pending_select-all" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </th>
                                                <th>work</th>
                                                <th>structure</th>
                                                <th>department</th>
                                                <th>activity</th>
                                                <th>reporting date</th>
                                                <th>completed</th>
                                                <th>remaining</th>
                                                <th>actual</th>
                                                <th>updated by</th>
                                                <th>pending on</th>
                                                <th>action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="pending_activity_check"
                                                                class="check" id="pending_check_1" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </td>
                                                <td>work</td>
                                                <td>structure</td>
                                                <td>department</td>
                                                <td>activity</td>
                                                <td>reporting date</td>
                                                <td>completed</td>
                                                <td>remaining</td>
                                                <td>actual</td>
                                                <td>updated by</td>
                                                <td>pending on</td>
                                                <td class="last-column">
                                                    <a href="" class="btn"><i class="fa fa-check"></i> </a>
                                                    <a href="" class="btn bg-s" id="pending_reject_1"><i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div id="approved_div" class="col s12">
                            <div class="row no-mar">
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col s6 m4 l2 input-field offset-l1">
                                            <p class="searchable_label">Work</p>
                                            <select name="approved_work_id_fk" id="approved_work_id_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field ">
                                            <p class="searchable_label">Structure</p>
                                            <select name="approved_structure_fk" id="approved_structure_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Department</p>
                                            <select name="approved_department_fk" id="approved_department_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label"> Updated By</p>
                                            <select name="approved_updated_by_fk" id="approved_updated_by_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s12 m4 l3 input-field center-align">
                                            <button class="btn bg-m waves-effect waves-light t-c clear-filters "
                                                onclick="clearFilter('approved');">Clear Filters</button>
                                        </div>
                                    </div>
                                    <span id="approved_checkBoxError" class="error-msg"
                                        style="text-align:center"></span>

                                    <span class="errMsg" id="approved_checkErrMsg">select at least one check box
                                    </span>
                                </div>
                                <span id="approved_actualScopesError" class="error-msg" style="color:red"></span>
                            </div>
                            <!-- <div class="row no-mar" style="margin-bottom: 0;">
                                <div class="col m8 s12 center-align offset-m2 btn-holder">
                                    <a class="btn waves-effect t-c disabled" id="approved_approve-btn"> <i
                                            class="fa fa-check"></i> Approve
                                    </a>
                                    <a class="btn waves-effect bg-s t-c disabled" id="approved_reject-btn"> <i
                                            class="fa fa-close"></i> Reject
                                    </a>
                                </div>
                            </div> -->

                            <div class="row fixed-width" style="margin-bottom: 30px;">
                                <div class="table-inside">
                                    <table class="mdl-data-table" id="datatable-table-approved">
                                        <thead>
                                            <tr>
                                                <!-- <th style=" text-align: center; vertical-align: bottom;">
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="approved_select-all"
                                                                id="approved_select-all" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </th> -->
                                                <th>work</th>
                                                <th>structure</th>
                                                <th>department</th>
                                                <th>activity</th>
                                                <th>reporting date</th>
                                                <th>completed</th>
                                                <th>remaining</th>
                                                <th>actual</th>
                                                <th>updated by</th>
                                                <th>approved on</th>
                                                <!-- <th>action</th> -->
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <!-- <td>
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="approved_activity_check"
                                                                class="check" id="approved_check_1" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </td> -->
                                                <td>work</td>
                                                <td>structure</td>
                                                <td>department</td>
                                                <td>activity</td>
                                                <td>reporting date</td>
                                                <td>completed</td>
                                                <td>remaining</td>
                                                <td>actual</td>
                                                <td>updated by</td>
                                                <td>approved on</td>
                                                <!-- <td class="last-column">
                                                    <a href="" class="btn"><i class="fa fa-check"></i> </a>
                                                    <a href="" class="btn bg-s"><i class="fa fa-close"></i></a>
                                                </td> -->
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div id="rejected_div" class="col s12">
                            <div class="row no-mar">
                                <div class="col m10 s12 offset-m1">
                                    <div class="row">
                                        <div class="col s6 m4 l2 input-field offset-l1">
                                            <p class="searchable_label">Work</p>
                                            <select name="rejected_work_id_fk" id="rejected_work_id_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field ">
                                            <p class="searchable_label">Structure</p>
                                            <select name="rejected_structure_fk" id="rejected_structure_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label">Department</p>
                                            <select name="rejected_department_fk" id="rejected_department_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>
                                            </select>
                                        </div>
                                        <div class="col s6 m4 l2 input-field">
                                            <p class="searchable_label"> Updated By</p>
                                            <select name="rejected_updated_by_fk" id="rejected_updated_by_fk"
                                                class="searchable validate-dropdown">
                                                <option value="">Select</option>

                                            </select>
                                        </div>
                                        <div class="col s12 m4 l3 input-field center-align">
                                            <button class="btn bg-m waves-effect waves-light t-c clear-filters "
                                                onclick="clearFilter('rejected');">Clear Filters</button>
                                        </div>
                                    </div>
                                    <span id="rejected_checkBoxError" class="error-msg"
                                        style="text-align:center"></span>

                                    <span class="errMsg" id="checkErrMsg">select at least one check box
                                    </span>
                                </div>
                                <span id="rejected_actualScopesError" class="error-msg" style="color:red"></span>
                            </div>
                            <!-- <div class="row no-mar" style="margin-bottom: 0;">
                                <div class="col m8 s12 center-align offset-m2 btn-holder">
                                    <a class="btn waves-effect t-c disabled" id="rejected_approve-btn"> <i
                                            class="fa fa-check"></i> Approve
                                    </a>
                                    <a class="btn waves-effect bg-s t-c disabled" id="rejected_reject-btn"> <i
                                            class="fa fa-close"></i> Reject
                                    </a>
                                </div>
                            </div> -->

                            <div class="row fixed-width" style="margin-bottom: 30px;">
                                <div class="table-inside">
                                    <table class="mdl-data-table" id="datatable-table-rejected">
                                        <thead>
                                            <tr>
                                                <!-- <th style=" text-align: center; vertical-align: bottom;">
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="rejected_select-all"
                                                                id="rejected_select-all" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </th> -->
                                                <th>work</th>
                                                <th>structure</th>
                                                <th>department</th>
                                                <th>activity</th>
                                                <th>reporting date</th>
                                                <th>completed</th>
                                                <th>remaining</th>
                                                <th>actual</th>
                                                <th>updated by</th>
                                                <th>rejcted on</th>
                                                <!-- <th>action</th> -->
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <!-- <td>
                                                    <p>
                                                        <label>
                                                            <input type="checkbox" name="rejected_activity_check"
                                                                class="check" id="rejected_check_1" />
                                                            <span></span>
                                                        </label>
                                                    </p>
                                                </td> -->
                                                <td>work</td>
                                                <td>structure</td>
                                                <td>department</td>
                                                <td>activity</td>
                                                <td>reporting date</td>
                                                <td>completed</td>
                                                <td>remaining</td>
                                                <td>actual</td>
                                                <td>updated by</td>
                                                <td>rejcted on</td>
                                                <!-- <td class="last-column">
                                                    <a href="" class="btn"><i class="fa fa-check"></i> </a>
                                                    <a href="" class="btn bg-s"><i class="fa fa-close"></i></a>
                                                </td> -->
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- </form> -->
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

        <div class="page-loader-2" style="display: none;">
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

        <script src="pmis/resources/js/jQuery-v.3.5.min.js"></script>
        <script src="pmis/resources/js/materialize-v.1.0.min.js"></script>
        <script src="pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
        <script src="pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
        <script src="pmis/resources/js/dataTables.material.min.js"></script>
        <script src="pmis/resources/js/select2.min.js"></script>
        <script src="pmis/resources/js/moment-v2.8.4.min.js"></script>
        <script src="pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
        <script src="pmis/resources/js/datetime-moment-v1.10.12.js"></script>

        <script>
            $(document).ready(function () {
                $(".errMsg").hide();
                $(".errMsgCheck").hide();
                $('.searchable').select2();
                $('.tabs').tabs();
                $('#datatable-table-pending').DataTable({
                    columnDefs: [
                        { "width": "10px", "targets": [11] },
                    ],
                    'order': [1, 'asc'],
                    "ScrollX": true,
                    "scrollCollapse": true,
                    "sScrollY": 400,
                    //paging: true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    }
                });
                $('#datatable-table-approved').DataTable({
                    columnDefs: [
                        // { "width": "10px", "targets": [9] },
                    ],
                   // 'order': [1, 'asc'],
                    "ScrollX": true,
                    "scrollCollapse": true,
                    "sScrollY": 400,
                    //paging: true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    }
                });
                $('#datatable-table-rejected').DataTable({
                    columnDefs: [
                        // { "width": "10px", "targets": [9] },
                    ],
                   // 'order': [1, 'asc'],
                    "ScrollX": true,
                    "scrollCollapse": true,
                    "sScrollY": 400,
                    //paging: true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    }
                });
            });

            // clear filter functionality for all divs
            function clearFilter(a) {
                $(".page-loader").show();
                $("#" + a + "_work_id_fk").val("");
                $("#" + a + "_structure_fk").val("");
                $("#" + a + "_department_fk").val("");
                $("#" + a + "_updated_by_fk").val("");
                $('.searchable').select2();
                $(".page-loader").hide();
            }

            // select or deselect all checkboxes
            $('#pending_select-all').change(function () {
                var _this = this;
                $('input[name="pending_activity_check"]').each(function () {
                    if ($(_this).is(':checked')) {
                        $(this).prop('checked', true);
                        $('#pending_approve-btn').removeClass('disabled');
                        $('#pending_reject-btn').removeClass('disabled');
                    } else {
                        $(this).prop('checked', false);
                        $('#pending_approve-btn').addClass('disabled');
                        $('#pending_reject-btn').addClass('disabled');
                    }
                });
            });

            $('#approved_select-all').change(function () {
                var _this = this;
                $('input[name="approved_activity_check"]').each(function () {
                    if ($(_this).is(':checked')) {
                        $(this).prop('checked', true);
                    } else {
                        $(this).prop('checked', false);
                    }
                });
            });

            $('#rejected_select-all').change(function () {
                var _this = this;
                $('input[name="rejected_activity_check"]').each(function () {
                    if ($(_this).is(':checked')) {
                        $(this).prop('checked', true);
                    } else {
                        $(this).prop('checked', false);
                    }
                });
            });

            $("#pending_reject-btn").on("click", function (event) {
                // event.preventDefault();
                swal({
                    title: "Are you sure You want to Reject 'number' of Activities?",
                    text: "", type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: true
                },
                    function () {
                        $(".deleteedition").submit();
                    });
            });


            $("#pending_reject_1").on("click", function (event) {
                event.preventDefault();
                swal({
                    title: "Are you sure You want to Reject this Activity?",
                    text: "", type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, Reject it!",
                    closeOnConfirm: true
                },
                    function () {
                        $(".deleteedition").submit();
                    });
            });
        </script>

</body>

</html>