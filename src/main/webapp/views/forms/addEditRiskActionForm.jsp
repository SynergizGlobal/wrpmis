<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	 <title>
     	<%--  <c:if test="${action eq 'edit'}">Update Risk Action</c:if>
		 <c:if test="${action eq 'add'}"> Add Risk Action</c:if> --%>
		 Add/Edit Risks Action
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/risk.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<style>
        textarea::placeholder {
            color: #444;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        td {
            position: relative;
        }

        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }


        .update-table {
            border: 1px solid #ddd;
        }

        table.update-table tbody td .datepicker~button {
            top: 15px !important;
            right: 1px;
        }

        table.update-table tbody td .modal.datepicker-modal.open {
            width: 85% !important;
        }

        table.update-table tbody td .input-field {
            margin-top: 0;
            margin-bottom: 0;
        }

        .fw-60 {
            max-width: 60px;
            width: 60px !important;
        }

        .fw-150 {
            max-width: 150px;
            width: 150px !important;
        }

        .input-field {
            margin-top: 0.75rem;
            margin-bottom: 0.75rem;
        }

        .input-field textarea {
            height: auto;
            border-radius: 0;
            border: none;
            border-bottom: 2px solid #ccc;
            padding-left: 10px;
            padding-bottom: 0;
            margin-bottom: 6px;
        }
    </style>
</head>
<body>
	<!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
     <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Add Risk Action</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m4 s12 input-field">                                   
                                    <p>Project :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m4 s12 input-field">
                                    <p>Work :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p>Risk Id :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p>Owner :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p>Area :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p>Sub Area :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p>Assessment Date :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p>Responsible Person :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <p>Mitigation Plan :</p>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>

                        <div class="row" style="margin-bottom: 0;">
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col s12 m6 input-field">
                                <div class="row fixed-width">
                                    <div class="table-inside">
                                        <table id="atr-table" class="mdl-data-table update-table">
                                            <thead>
                                                <tr>
                                                    <th class="fw-150">ATR Date</th>
                                                    <th>Action Taken</th>
                                                    <th class="fw-60">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <div class="input-field">
                                                            <input id="atr_date0" type="text"
                                                                class="validate datepicker" placeholder="ATR  Date">
                                                            <button type="button" id="atr_date0_icon"><i
                                                                    class="fa fa-calendar"></i></button>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <textarea id="action_taken0" class="materialize-textarea"
                                                            placeholder="Action Taken" style="height: 44px;"></textarea>
                                                    </td>
                                                    <td>
                                                        <a href="#" class="btn waves-effect waves-light red t-c ">
                                                            <i class="fa fa-close"></i></a>
                                                    </td>
                                                </tr>
                                                <tr class="last-row">
                                                    <td></td>
                                                    <td></td>
                                                    <td>
                                                        <a onclick="addATRTableRow()"
                                                            class="btn waves-effect waves-light bg-m t-c ">
                                                            <i class="fa fa-plus"></i></a>
                                                    </td>

                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>

                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="container container-no-margin">

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <input type="submit" value="Create" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;">
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <input type="button" value="Cancel" class="btn waves-effect waves-light bg-s"
                                            style="width: 100%;">
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </form>
                    <!-- form ends  -->
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
           /*  $('select:not(.searchable)').formSelect();
            $('.searchable').select2(); */
            $("#atr_date0").datepicker();
            $('#atr_date0_icon').click(function () {
                event.stopPropagation();
                $('#atr_date0').click();
            });
        });
            var updateNo = 1;
        function addATRTableRow(no) {
            var text = '<tr> <td> <div class="input-field"> <input id="atr_date' + updateNo + '" type="text" class="validate datepicker" placeholder="ATR  Date">' +
                '<button type="button" id="atr_date' + updateNo + '_icon"><i class="fa fa-calendar"></i></button></div> </td> <td>' +
                '<textarea id="action_taken' + updateNo + '" class="materialize-textarea" placeholder="Action Taken" ></textarea> </td>' +
                '<td><a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
            $('#atr-table').find('tr:last').prev().after(text);
            $("#atr_date" + updateNo).datepicker();
            $('#atr_date' + updateNo + '_icon').click(function () {
                event.stopPropagation();
                $('#atr_date' + updateNo).click();
            });
            updateNo++;
        }
    </script>
</body>
</html>