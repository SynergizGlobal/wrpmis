<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.PageConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Access</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
<!--     <link rel="stylesheet" href="/wrpmis/resources/css/normalize.css">
 -->    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked{position: relative; opacity: 1;pointer-events: auto;}
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
                            <h6> Dashboard Access</h6>
                        </div>
                    </span>
                    <div class="">

                        <!-- <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Contract</strong></a>
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
                            <div class="col m4 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Module</p>
                                        <select id="dept_fk" class="searchable">
                                            <option value="" disabled selected>Select Module</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <!-- <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Contractor</p>
                                        <select id="contractor_fk" class="searchable">
                                            <option value="" disabled selected>Select Contractor</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div> -->
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
                                            <th></th>
                                            <th>User</th>
                                            <th><input type="checkbox" name="dashboard1" class="selectcolumn1">&nbsp; Dashboard 1 </th>
                                            <th><input type="checkbox" name="dashboard2" class="selectcolumn2">&nbsp; Dashboard 2</th>
                                            <th>Sample fields </th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr>

                                            <td><input type="checkbox" name="row1" class="selectrow1"></td>
                                            <td>Designation - Name</td>
                                            <td><input type="checkbox" name="row_list" class="row1 column1"></td>
                                            <td><input type="checkbox" name="row_list" class="row1 column2"></td>
                                            <td></td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <!-- <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a> -->
                                            </td>

                                        </tr>
                                        <tr>

                                            <td><input type="checkbox" name="row2" class="selectrow2"></td>
                                            <td>Designation - Name</td>
                                            <td><input type="checkbox" name="row_list" class="row2 column1"></td>
                                            <td><input type="checkbox" name="row_list" class="row2 column2"></td>
                                            <td></td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <!-- <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a> -->
                                            </td>

                                        </tr>
                                        <tr>

                                            <td><input type="checkbox" name="row3" class="selectrow3"></td>
                                            <td>Designation - Name</td>
                                            <td><input type="checkbox" name="row_list" class="row3 column1"></td>
                                            <td><input type="checkbox" name="row_list" class="row3 column2"></td>
                                            <td></td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <!-- <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a> -->
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

    <!-- footer starts -->
 	<jsp:include page="../layout/footer.jsp"></jsp:include>
    <!-- footer ends  -->

    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    
    <script type="text/javascript">
        $(".selectrow1").click(function(){
        $(".row1").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow2").click(function(){
        $(".row2").prop("checked",$(this).prop("checked"));
        });
        $(".selectrow3").click(function(){
        $(".row3").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn1").click(function(){
        $(".column1").prop("checked",$(this).prop("checked"));
        });
        $(".selectcolumn2").click(function(){
        $(".column2").prop("checked",$(this).prop("checked"));
        });
    </script>
    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            $('#example').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [5] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                ordering: false,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
                        .unbind(),
                        self = this.api(),
                        $searchButton = $('<i class="fa fa-search" title="Go" id="save_post">')
                            .click(function () { self.search(input.val()).draw(); }),
                        $clearButton = $('<i class="fa fa-close" title="Reset">')
                            .click(function () {
                                input.val('');
                                $searchButton.click();
                            })
                    $('.dataTables_filter').append(
                        '<div class="right-btns"></div>');
                    $('.dataTables_filter div').append(
                        $searchButton, $clearButton);
                }
            });

            $('.clear-filters').click(function () {
                $('#hod_fk').val("");
                $('#module_fk').val("");
            });
        });
        

    </script>

</body>
</html>
