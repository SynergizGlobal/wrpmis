<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Safety Equipment </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contractor.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <style>
        p a {
            color: blue
        }

        td:last-child,
        td:last-of-type {
            white-space: inherit;
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
                                <h6>Add / Edit Contractor</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <label class="primary-text-bold" style="margin-top: 10px;">Contractor ID :</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <p><label>Specialization</label></p>
                                    <select id="specialization" class="searchable">
                                        <option value="0" selected>Select</option>
                                        <option value="1">select1</option>
                                        <option value="2">select2</option>
                                    </select>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m8 input-field">
                                    <input id="contractor_name" type="text" class="validate">
                                    <label for="contractor_name">Contractor Name</label>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="textarea2" class="materialize-textarea" data-length="1000"></textarea>
                                    <label for="textarea2">Address</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <input id="primary_contract" type="text" class="validate">
                                            <label for="primary_contract">Primary Contact</label>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="phone_number" type="number" class="validate">
                                            <label for="phone_number">Phone Number</label>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="email" type="text" class="validate">
                                            <label for="email">Email Id</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <input id="bank_name" type="text" class="validate">
                                            <label for="bank_name">Bank Name </label>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="ac_no" type="text" class="validate">
                                            <label for="ac_no">Account No </label>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="ifsc_code" type="text" class="validate">
                                            <label for="ifsc_code"> IFSC Code </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="pan_number" type="text" class="validate">
                                    <label for="pan_number">PAN Number</label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="gst_number" type="text" class="validate">
                                    <label for="gst_number">GST Number</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
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
            $(".datepicker").datepicker();
            $('#textarea1').characterCounter();
            $('#textarea2').characterCounter();
        });
    </script>

</body>

</html>