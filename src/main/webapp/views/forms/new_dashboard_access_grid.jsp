<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Access</title>
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
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
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Select Module </p>
                                        <select id="work_id_fk" class="searchable">
                                            <option value="" disabled selected>Select Module Name</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Select Dashboard Type</p>
                                        <select id="dept_fk" class="searchable">
                                            <option value="" disabled selected>Select Dashboard</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">Select Status</p>
                                        <select id="contractor_fk" class="searchable">
                                            <option value="" disabled selected>Select Contractor</option>
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
                                            <th>Name</th>
                                            <th>Module</th>
                                            <th>Folder</th>
                                            <th>Status</th>
                                            <th>User Role Access</th>
                                            <th>User Type Access</th>
                                            <th>User Access</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr>

                                            <td>Name</td>
                                            <td>Module</td>
                                            <td>Folder</td>
                                            <td>Status</td>
                                            <td>User Role Access</td>
                                            <td>User Type Access</td>
                                            <td>User Access</td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-share"></i></a>
                                            </td>

                                        </tr>
                                         <tr>

                                            <td>Name</td>
                                            <td>Module</td>
                                            <td>Folder</td>
                                            <td>Status</td>
                                            <td>User Role Access</td>
                                            <td>User Type Access</td>
                                            <td>User Access</td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-share"></i></a>
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

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
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
                    { "width": "20px", "targets": [7] },
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