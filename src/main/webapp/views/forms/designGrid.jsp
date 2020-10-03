<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Design & Drawing</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <style>
        p a {
            color: blue;
        }
    </style>
</head>
<body>

   <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
         
         
    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Design & Drawing</h6>
                        </div>
                    </span>
                    <div class="">                      
                        <div class="row plr-1 center-align">
                            <div class="col s12 m4 l-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div>
                            </div>

                            <div class="col s12 m4 c-align">
                                <div class="m-1 ">
                                    <a href="design&drawing.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Design & Drawing</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col s12 m12">
                                <div class="row">
                                    <div class="col s12 m2 input-field">
                                        <select>
                                            <option value="" disabled selected>Select Contract</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                        <label>Select Contract </label>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <select>
                                            <option value="" disabled selected>Select Department</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                        <label>Select Department </label>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <select>
                                            <option value="" disabled selected>Select HOD</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                        <label>Select HOD </label>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <select>
                                            <option value="" disabled selected>Select Structure</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                        <label>Select Structure </label>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <select>
                                            <option value="" disabled selected>Select Drawing Type</option>
                                            <option value="1">Option 1</option>
                                            <option value="2">Option 2</option>
                                            <option value="3">Option 3</option>
                                        </select>
                                        <label>Select Drawing Type </label>
                                    </div>
                                    <div class="col s12 m2 input-field">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                            style="margin-top: 8px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract </th>
                                            <th>Title</th>
                                            <th>Structure </th>
                                            <th>Drawing Type </th>
                                            <th>Contractor <br>Drawing No </th>
                                            <th>MRVC <br>Drawing No </th>
                                            <th>Division <br>Drawing No</th>
                                            <th>HQ Drawing No </th>                                           
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                           
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="design&drawing.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
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



    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
     <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script>
        $(document).ready(function () {
            $('select').formSelect();
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
                    // { "width": "20px", "targets": [7] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });


    </script>
</body>
</html>