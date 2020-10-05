<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>add Issues</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
<!-- 	<link rel="stylesheet" href="/mrvc/resources/css/material-design-lite-v.1.0.css">	 -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
<!-- 	<link rel="stylesheet" href="/mrvc/resources/css/datatable-material.css"> -->
	<link rel="stylesheet" href="/pmis/resources/css/users.css">
	<style>
        #example3 .datepicker~button,
        #example4 .datepicker~button {
            top: 26px;
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

        .radiogroup {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }

        .fixed-width {
            width: 100%;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
    </style>
</head>
<body>
	<!-- header included -->
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	
	
	  <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Add / Edit Users</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="#">

                            <div class="row">
                                <!-- row 4 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>User Role </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <!-- <input type="text" id="user_id"> -->
                                    <label class="primary-text-bold">User ID :</label>
                                    <br><br>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <!-- <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Employee Type </label>
                                </div> -->
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Department </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <select>
                                        <option value="0" selected>Select</option>
                                        <option value="1">Agency 1</option>
                                        <option value="2">Agency 2</option>
                                        <option value="3">Agency 3</option>
                                    </select>
                                    <label>Reporting To </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="name" type="text" class="validate">
                                    <label for="name">Name</label>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="designation" type="text" class="validate">
                                    <label for="designation">Designation </label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="email" type="email" class="validate">
                                    <label for="email">Email ID </label>
                                </div>
                                <div class="col s12 m4 input-field ">
                                    <input id="mobile" type="number" class="validate">
                                    <label for="mobile"> Mobile Number </label>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>                          
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field ">
                                    <input id="landline" type="number" class="validate">
                                    <label for="landline"> Landline </label>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input id="extension" type="number" class="validate">
                                    <label for="extension">Extension</label>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>


                            <!-- insurance show hide div  -->
                            <div class="row fixed-width">
                                <h5 class="center-align">User Permissions</h5>
                                <!-- <div class="table-inside"> -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <table id="example3" class="mdl-data-table" style="margin-left: 11px;">
                                        <thead>
                                            <tr>
                                                <th>User Access Type</th>
                                                <th>Value</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <select>
                                                        <option value="0" selected>User Access Type</option>
                                                        <option value="1">Agency 1</option>
                                                        <option value="2">Agency 2</option>
                                                        <option value="3">Agency 3</option>
                                                    </select>
                                                </td>                                               
                                                <td>
                                                    <select>
                                                        <option value="0" selected>Select Value</option>
                                                        <option value="1">Agency 1</option>
                                                        <option value="2">Agency 2</option>
                                                        <option value="3">Agency 3</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <a href="#" class="btn waves-effect waves-light bg-m t-c "> <i
                                                            class="fa fa-plus"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                                <!-- </div> -->
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>

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
	
		
	     <!-- Page Loader -->
	<div class="page-loader" style="display: none;">
	  <div class="preloader-wrapper big active">
	    <div class="spinner-layer spinner-blue-only">
	      <div class="circle-clipper left">
	        <div class="circle"></div>
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 

	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
	
	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
<!-- 	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script> -->
<!-- 	<script src="/pmis/resources/js/dataTables.material.min.js"></script> -->

    <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('#textarea1,#textarea2,#textarea3').characterCounter();
            $("#loa_date").datepicker();
            $("#ca_date,#co_date").datepicker();
            $("#bg_upto_valid,#insurance_upto_valid").datepicker();
            $("#milestone_date,#actual_date,#revised_doc").datepicker();

            $('#sactioned_year').datepicker({
                format: 'yyyy',
                selectYears: true,
                selectMonths: true,
                selectDays: false,
                onSelect: function (arg) {
                    var selectedYear = parseInt(arg.getFullYear());
                    var selectedMonth = parseInt(arg.getMonth() + 1);
                    var year = (selectedMonth >= 4) ? selectedYear + '-' + (selectedYear + 1) : (selectedYear - 1) + '-' + selectedYear;
                    console.log('sactioned_year : ' + year);

                }
            });
            $('#loa_date_icon').click(function () {
                event.stopPropagation();
                $('#loa_date').click();
            });
            $('#ca_date_icon').click(function () {
                event.stopPropagation();
                $('#ca_date').click();
            });
            $('#co_date_icon').click(function () {
                event.stopPropagation();
                $('#co_date').click();
            });
            $('#bg_upto_valid_icon').click(function () {
                event.stopPropagation();
                $('#bg_upto_valid').click();
            });
            $('#insurance_upto_valid_icon').click(function () {
                event.stopPropagation();
                $('#insurance_upto_valid').click();
            });
            $('#milestone_date_icon').click(function () {
                event.stopPropagation();
                $('#milestone_date').click();
            });
            $('#actual_date_icon').click(function () {
                event.stopPropagation();
                $('#actual_date').click();
            });
            $('#revised_doc_icon').click(function () {
                event.stopPropagation();
                $('#revised_doc').click();
            });

            $('#example,#example1').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    }
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
            // show or hide based on bg 
            $('input[name="bank_guarantee"]').change(function () {
                var radioval = $('input[name="bank_guarantee"]:checked').val();
                if (radioval == 'yes') {
                    $('#bank_guarantee_div').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#bank_guarantee_div').css("display", "none");
                }
            });
            // show or hide based on insurance 

            $('input[name="insurance"]').change(function () {
                var radioval = $('input[name="insurance"]:checked').val();
                if (radioval == 'yes') {
                    $('#insurance_div').css("display", "block");
                }
                else if (radioval == 'no') {
                    $('#insurance_div').css("display", "none");
                }
            });
        });
    </script>
</body>
</html>