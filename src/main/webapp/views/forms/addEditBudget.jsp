<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Safety Equipment </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/budget.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <style>
        p a {
            color: blue
        }
    
        td:last-child,
        td:last-of-type {
            white-space: inherit;
        }
          #example5 td.input-field .prefix,
        #example4 td.input-field .prefix {
            top: 1.5rem;
        }
        
        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }

        @media only screen and (max-width: 600px) {
            .input-field .prefix~input {
                min-width: 80px;
            }
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
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit Budget</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p> <label> Project ID </label></p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p> <label> Work ID </label></p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>


                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label>Executed By </label></p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="latest_sanction_cost" type="text" class="validate">
                                    <label for="latest_sanction_cost">Latest Sanction Cost</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="cumulative_expenditure" type="text" class="validate">
                                    <label for="cumulative_expenditure">Cumulative Expenditure</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <i class="material-icons prefix center-align">₹</i>
                                    <input id="cumulative_expenditure_fy" type="text" class="validate">
                                    <label for="cumulative_expenditure_fy">Cumulative Expenditure in FY</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m4 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <p><label> Financial Year</label></p>
                                    <select class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                </div>
                                <div class="col m4 hide-on-small-only"></div>
                            </div>

                            <div class="container">
                                <div class="row fixed-width">
                                    <h5 class="center-align">Budget & Grants</h5>
                                    <div class="table-inside">
                                        <table id="example4" class="mdl-data-table">
                                            <thead>
                                                <tr>
                                                    <th>Budget Type </th>
                                                    <th>Amount </th>
                                                    <th>Remarks</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <input id="budget_type1" type="text" class="validate"
                                                            value="BE">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="budget_amount1" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <input id="budget_remakrs1" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input id="budget_type2" type="text" class="validate"
                                                            value="August RE">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="budget_amount2" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <input id="budget_remakrs2" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input id="budget_type3" type="text" class="validate"
                                                            value="RE">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="budget_amount3" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <input id="budget_remakrs3" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input id="budget_type4" type="text" class="validate"
                                                            value="Final Estimate">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="budget_amount4" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <input id="budget_remakrs4" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="container">
                                <div class="row fixed-width" style="margin-top: 40px; margin-bottom:30px;">
                                    <div class="table-inside">
                                        <table id="example5" class="mdl-data-table">
                                            <thead>
                                                <tr>
                                                    <th>Grant Type </th>
                                                    <th>Amount </th>
                                                    <th>Remarks</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <input id="grant_type1" type="text" class="validate" value="BG">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="grant_amount1" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>

                                                    </td>
                                                    <td>
                                                        <input id="grant_remakrs1" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input id="grant_type2" type="text" class="validate" value="RG">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="grant_amount2" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>

                                                    </td>
                                                    <td>
                                                        <input id="grant_remakrs2" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input id="grant_type3" type="text" class="validate" value="FG">
                                                    </td>
                                                    <td>
                                                        <div class="input-field">
                                                            <i class="material-icons prefix center-align">₹</i>
                                                            <input id="grant_amount3" type="text" class="validate"
                                                                placeholder="Amount">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <input id="grant_remakrs3" type="text" class="validate"
                                                            placeholder="Remarks">
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea1" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea1">Remarks</label>
                                </div>
                            </div>


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
                        </form>
                    </div>
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
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#textarea1,#textarea2,#textarea3,#issueDesc').characterCounter();
            $("#joint_survey,#design_finalisation,#payment_date,#date_initiative,#date_approval").datepicker();
            $("#shifting_date,#start_date,#identification").datepicker();

            $('#joint_survey_icon').click(function () {
                event.stopPropagation();
                $('#joint_survey').click();
            });
            $('#design_finalisation_icon').click(function () {
                event.stopPropagation();
                $('#design_finalisation').click();
            });
            $('#payment_date_icon').click(function () {
                event.stopPropagation();
                $('#payment_date').click();
            });
            $('#date_initiative_icon').click(function () {
                event.stopPropagation();
                $('#date_initiative').click();
            });
            $('#date_approval_icon').click(function () {
                event.stopPropagation();
                $('#date_approval').click();
            });
            $('#shifting_date_icon').click(function () {
                event.stopPropagation();
                $('#shifting_date').click();
            });
            $('#start_date_icon').click(function () {
                event.stopPropagation();
                $('#start_date').click();
            });
            $('#identification_icon').click(function () {
                event.stopPropagation();
                $('#identification').click();
            });

            $('input[name=issue]').change(function () {
                var radioval = $('input[name=issue]:checked').val();
                if (radioval == 'yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#issue_yes').css("display", "none");
                }
            });
        });
    </script>


</body>

</html>