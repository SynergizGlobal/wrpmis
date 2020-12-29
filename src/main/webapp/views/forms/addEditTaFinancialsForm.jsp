<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>
     	 <c:if test="${action eq 'edit'}">Update TA Financials</c:if>
		 <c:if test="${action eq 'add'}">Add TA Financials</c:if>
    </title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/finance.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        input[type="month"] {
            height: 35px;
            background-color: transparent;
            border: 0;
            outline: 0;
            border-bottom: 1px solid #aaa;
            font-size: 1rem;
        }

        input[type=month]:not(.browser-default):focus:not([readonly]) {
            border-bottom: 1px solid #F99934;
            box-shadow: 0 1px 0 0 #F99934;
        }

        #financialFormTable .input-field .prefix {
            top: 1.55rem;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
          .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		.preloader-wrapper{top: 45%!important;left:47%!important;}
		.error-msg label{color:red!important;}
    </style>
</head>

<body>

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>Add / Edit TA Financials</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Work </p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                    </select>
                                    <span id="workError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Contract </p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                    </select>
                                    <span id="contractError" class="error-msg"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col s12 m8">
                                <div class="row fixed-width">
                                    <h5 class="center-align">TA Financial Details</h5>
                                    <div class="table-inside">
                                        <table id="financialFormTable" class="mdl-data-table">
                                            <thead>
                                                <tr>
                                                    <th>Month </th>
                                                    <th>Planned Invoicing </th>
                                                    <th>Actual Invoicing </th>
                                                    <th>Payment Received </th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td> <input id="month0" name="month" type="month" class="validate"
                                                            placeholder="Select Month">
                                                        <span id="month0sgst_tdsError" class="error-msg"></span>
                                                    </td>
                                                    <td class="input-field">
                                                        <i class="material-icons prefix center-align">₹</i>
                                                        <input id="planned_invoice0" type="number" step="0.01"
                                                            min="0.01" class="validate" name="planned_invoice"
                                                            placeholder="Planned Invoicing">
                                                        <span id="planned_invoice0Error" class="error-msg"></span>
                                                    </td>
                                                    <td class="input-field">
                                                        <i class="material-icons prefix center-align">₹</i>
                                                        <input id="actual_invoice0" type="number" step="0.01" min="0.01"
                                                            class="validate" name="actual_invoice"
                                                            placeholder="Actual Invoicing">
                                                        <span id="actual_invoice0Error" class="error-msg"></span>
                                                    </td>
                                                    <td class="input-field">
                                                        <i class="material-icons prefix center-align">₹</i>
                                                        <input id="payment_received0" type="number" step="0.01"
                                                            min="0.01" class="validate" name="payment_received0"
                                                            placeholder="Payment Received">
                                                        <span id="payment_received0Error" class="error-msg"></span>
                                                    </td>
                                                    <td>
                                                        <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                                class="fa fa-close"></i></a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>
                                                        <a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                            onclick="addRow()">
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
                                        <button style="width: 100%;" class="btn waves-effect waves-light bg-m">Add /
                                            Edit</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</button>
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

 <jsp:include page="../layout/footer.jsp"></jsp:include>
    <!-- footer  -->

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            // $('#textarea1,#textarea2,#textarea3').characterCounter();
            $("#funding_date").datepicker();

            $('#funding_date_icon').click(function () {
                event.stopPropagation();
                $('#funding_date').click();
            });


        });
        No = 1
        function addRow() {
            var text = ' <tr> <td> <input id="month' + No + '" name="month" type="month" class="validate" placeholder="Select Month">' +
                '<span id="month' + No + 'Error" class="error-msg"></span></td > <td class="input-field"> <i class="material-icons prefix center-align">₹</i>' +
                '<input id="planned_invoice' + No + '" type="number" step="0.01" min="0.01" class="validate" name="planned_invoice" placeholder="Planned Invoicing">' +
                '<span id="planned_invoice' + No + 'Error" class="error-msg"></span> </td><td class="input-field">' +
                '<i class="material-icons prefix center-align">₹</i><input id="actual_invoice' + No + '" type="number" step="0.01" min="0.01" class="validate" name="actual_invoice" placeholder="Actual Invoicing">' +
                '<span id="actual_invoice' + No + 'Error" class="error-msg"></span> </td> <td class="input-field"><i class="material-icons prefix center-align">₹</i>' +
                '<input id="payment_received' + No + '" type="number" step="0.01" min="0.01" class="validate" name="payment_received' + No + '" placeholder="Payment Received">' +
                '<span id="payment_received' + No + 'Error" class="error-msg"></span></td> <td> <a href="#" class="btn waves-effect waves-light red t-c "> ' +
                '<i class="fa fa-close"></i></a></td></tr>';
            $('#financialFormTable').find('tr:last').prev().after(text);
            No++;
        }
    </script>

</body>

</html>