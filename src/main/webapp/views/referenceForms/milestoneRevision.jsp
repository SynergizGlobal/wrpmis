<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Milestone Revision</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }
		.mdl-data-table td.last-column {
		    text-align: left ;
		}
        p a {
            color: blue;
        }

        .row.no-mar {
            margin-bottom: 0;
        }

        .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .last-column {
            word-break: break-all;
            white-space: inherit;
        }

        #remarks {
            padding-right: 25px;
        }

        #remarks~.character-counter {
            position: absolute;
            bottom: 22px;
            right: 15px;
        }

        @media only screen and (max-width: 600px) {

            .dataTables_filter input[type="search"],
            div.dataTables_wrapper div.dataTables_filter input[type="search"] {
                width: 85% !important;
            }
        }
    </style>
</head>

<body>

    <!-- header  starts-->
<%--     <jsp:include page="../layout/header.jsp"></jsp:include> --%>

    <!-- header ends  -->


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h5> Milestone Revision</h5>
                        </div>
                    </span>
                    <div class="">
                        <div class="row">
                            <div class="col m4 hide-on-small"></div>
                            <div class="col m4 s12 center-align">
                                <a class="waves-effect waves-light btn bg-m modal-trigger t-c" href="#addUpdateModal">
                                    <i class="fa fa-plus-circle"></i> &nbsp; Add Milestone Revision</a>
                            </div>
                            <div class="col m4 hide-on-small"></div>
                        </div>
                        <div class="row no-mar">
                            <div class="col m12 s12">
                                <table id="milestone_revision_table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Milestone <br> Id</th>
                                            <th>Milestone <br> Revision Id</th>
                                            <th>Revision <br>No</th>
                                            <th>Revised <br> Completion</th>
                                            <th>Remarks</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- Modal Structure -->
    <div id="addUpdateModal" class="modal">
        <form action="#">
            <div class="modal-content">
                <h5 class="modal-header">Add Milestone Revision <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h5>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="milestone_id" type="text" class="validate">
                                <label for="milestone_id">Milestone Id</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="milestone_revision_id" type="text" class="validate">
                                <label for="milestone_revision_id">Milestone Revision Id</label>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <input id="revision_no" type="text" class="validate">
                                <label for="revision_no">Revision No</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="revised_completion" type="text" class="validate">
                                <label for="revised_completion">Revised Completion</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <textarea id="remarks" class="materialize-textarea"></textarea>
                                <label for="remarks">Remarks</label>
                            </div>

                        </div>
                        <div class="row no-mar">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button style="width: 100%;" class="btn waves-effect waves-light bg-m">Add /
                                        Edit</button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button class="btn waves-effect waves-light bg-s modal-action modal-close"
                                        style="width:100%">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>

            </div>

        </form>
    </div>
    <div id="errorModal" class="modal">
        <div class="modal-content">
            <h5 class="modal-header">Error <span class="right modal-action modal-close"><span
                        class="material-icons">close</span></span></h5>
            <div class="row center-align" style="margin-bottom: 0;">
                <p style="color:red">Reference data cannot be edited or deleted when in use by other Data sets</p>
            </div>

        </div>
    </div>
    <!-- footer  -->
<%--     <jsp:include page="../layout/footer.jsp"></jsp:include> --%>


    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.searchable').select2();
            $('.modal').modal({ dismissible: false });
            $('#remarks').characterCounter();
            // adding table data into table start
            var newArr = [
                ["1", "38", "R1", "2020-10-30", "MRVC server not available"],
                ["2", "38", "R2", "2020-11-30", "MRVC server not available"],
                ["3", "39", "R1", "2020-11-30", "MRVC server not available"]
            ]
            function makeTableHTML(myArray) {
                var result = "";
                for (var i = 0; i < myArray.length; i++) {
                    result += "<tr>";
                    for (var j = 0; j < myArray[i].length; j++) {
                        result += "<td>" + myArray[i][j] + "</td>";
                    }
                    result += '<td class="last-column"> <a href="#errorModal" class="btn waves-effect waves-light modal-trigger bg-m t-c">' +
                        '<i class="fa fa-pencil"></i></a><a href="#errorModal" class="btn waves-effect waves-light bg-s t-c modal-trigger"><i class="fa fa-trash"></i></a></td></tr>';
                }
                return result;
            }
            $('#milestone_revision_table tbody').append(makeTableHTML(newArr));
            // adding table data into table ends

            var table = $('#milestone_revision_table').DataTable({
                columnDefs: [
                    {
                        targets: [0],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [2] },
                ],
                "scrollCollapse": true,
                fixedHeader: true,
                paging: false,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '300px', 'display': 'inline-block' });
                }
            });
        });

    </script>

</body>

</html>