<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R&R Agency Grid</title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">     
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">     
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked{position: relative; opacity: 1;pointer-events: auto;}
    </style>
</head>

<body>

    <!-- header  starts-->

    <!-- header ends  -->

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> R&R Agency</h6>
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
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;" id="filters">
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">work </p>
                                        <select id="work_id_fk" class="searchable">
                                            <option value="" disabled selected>Select Module Name</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <p class="searchable_label">MRVC - HOD</p>
                                        <select id="dept_fk" class="searchable">
                                            <option value="" disabled selected>Select Dashboard</option>
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
                                            <th>work</th>
                                            <th>MRVC - HOD</th>
                                            <th>MRVC Responsible Person</th>
                                            <th>BSES Agency Name</th>
                                            <th>Responsible Person From Agency</th>                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <!-- <tr>

                                            <td>Name</td>
                                            <td>Module</td>
                                            <td>Folder</td>
                                            <td>Status</td>
                                            <td>User Role Access</td>
                                            
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>                                            </td>

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
                                                        class="fa fa-pencil"></i> </a>                                            </td>

                                        </tr> -->
                                        

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


    <script src="../nginx-1.9.9/html/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="../nginx-1.9.9/html/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="../nginx-1.9.9/html/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="../nginx-1.9.9/html/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="../nginx-1.9.9/html/pmis/resources/js/select2.min.js"></script>
    
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