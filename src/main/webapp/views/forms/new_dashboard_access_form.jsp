<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add / Edit Dashboard</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .input-field .searchable_label {
            font-size: 0.85rem;
        }

        input::placeholder {
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left: auto !important;
            margin-right: auto !important;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }

        .fw-8p {
            width: 8%;
        }

        #dashboard_form_table td>.btn {
            padding: 0 12px;
        }

        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .required {
            color: red;
            font-size: 1.3rem;
            vertical-align: middle;
        }
        .w20em{width: 20em;}
    </style>
</head>

<body>

    <!-- header  starts-->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h5>Add / Edit Dashboard</h5>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <form action="#">
                        <div class="container container-no-margin">
                            <div class="row">                              
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Dashboard :</p>
                                    <p>DashBoard Name</p>  
                                </div> 
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Module :</p>
                                    <p>Module Name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Dashboard Type :</p>
                                    <p>Dashboard name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                                
                            </div>
                            <div class="row">
                                <div class="col s12 m4 l4 input-field">
                                    <p class="searchable_label">Folder :</p>
                                    <p>Folder Name</p>  
                                </div> 
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Work :</p>
                                    <p>Work Name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                                <div class="col s12 m4 l4 input-field"> 
                                    <p class="searchable_label"> Contract Type :</p>
                                    <p>Contract name</p>
                                    <input type="hidden" id="work_id_fk" name="work_id_fk" value="${risk.work_id_fk}" />
                                </div>
                            </div>
                            <div class="row">
                                
                                <div class="col s12 m4 input-field">
                                    <input id="priority" name="priority" type="number" class="validate">
                                    <label for="priority">priority </label>
                                    <span id="priorityError" class="error-msg"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label"> Status </p>
                                    <select id="status" class="searchable" name="status">
                                        <option value="">Select</option>
                                        <option value="Govt">Govt </option>
                                    </select>
                                </div>
                                <div class="col s12 m4">
                                    <div class="row">
                                        <!-- row 7 -->
                                        <div class="col s5 m5 input-field">
                                            <p style="margin-top: 12px;">Mobile View ?</p>
                                        </div>
                                        <div class="col s5 m7 input-field">
                                            <p class="radiogroup" style="padding-bottom: 10px;padding-top: 10px;">
                                                <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="yes" />
                                                    <span>Yes</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="mobile_view" type="radio"
                                                        value="no" />
                                                    <span>No</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div>

                            <div class="row no-container" style="margin-bottom: 20px;">
                                <div class="col s12 m12">
                                    <div class="row fixed-width">
                                        <h5 class="center-align">User Details</h5>
                                        <div class="table-inside">
                                            <table id="dashboard_form_table" class="mdl-data-table">
                                                <thead>
                                                    <tr>
                                                        <th class="w20em">User Role </th>
                                                        <th class="w20em">user Type </th>
                                                        <th class="w20em">User</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="input-field">

                                                            <select id="user_role" class="searchable"
                                                                name="user_role" multiple placeholder="User Role">
                                                                <option value="select">Select</option>
                                                            </select>
                                                            <span id="access_type0Error" class="error-msg"></span>
                                                        </td>
                                                        <td class="input-field">
                                                            <select id="user_type" class="searchable"
                                                                name="user_type" multiple placeholder="User Type">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                            <span id="access_value0Error" class="error-msg"></span>
                                                        </td>
                                                        <td class="input-field">
                                                            <!-- <a href="#" class="btn waves-effect waves-light red t-c ">
                                                                <i class="fa fa-close"></i></a> -->
                                                                <select id="user" class="searchable"
                                                                name="user" multiple placeholder="User">
                                                                <option value="Select">Select</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <!-- <tr>
                                                        <td></td>
                                                        <td><a href="#" class="btn waves-effect waves-light bg-m t-c"
                                                                onclick="addRow()">
                                                                <i class="fa fa-plus"></i></a></td>
                                                        <td>
                                                            
                                                        </td>

                                                    </tr> -->
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <!-- </div> -->
                            <div class="container container-no-margin">
                            <div class="row">
                                <div class="col s12 m6 l6 mt-brdr ">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-m">Add /
                                            Edit</button>
                                    </div>
                                </div>
                                <div class="col s12 m6 l6 mt-brdr ">
                                    <div class="center-align m-1">
                                        <button class="btn waves-effect waves-light bg-s">Cancel</button>
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


    <!-- footer  -->
<jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $( ".searchable" ).each(function( index,val ) {
   				$( this ).select2({ placeholder: $( this ).attr('placeholder') });
			});
        });
        No = 1
        function addRow() {
            var text = '<tr><td class="input-field"> <select id="access_type' + No + '" class="searchable" name="access_type"> ' +
                '<option value="">Select</option> </select> <span id="access_type' + No + 'Error" class="error-msg"></span> </td>' +
                '<td class="input-field"><select id="access_value' + No + '" class="searchable" name="access_value">' +
                '<option value="">Select</option> </select> <span id="access_value' + No + 'Error" class="error-msg"></span> </td>' +
                '<td><a href="#" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a> </td> </tr>';
            $('#dashboard_form_table').find('tr:last').prev().after(text);
            $('.searchable').select2();
            No++;
        }
    </script>

</body>

</html>