<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forms</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        th:last-child,
        td:last-child {
            text-align: center !important;
        }

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 20px;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
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
                            <h6>Forms</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4 offset-m4">
                                <div class="m-1 c-align">
                                    <a href="forms_form.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Form</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col s12 m2 input-field offset-m3">
                                <p class="searchable_label">Select Module</p>
                                <select id="module_fk" class="searchable">
                                    <option value="">Select</option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Select Status</p>
                                <select id="status_fk" class="searchable">
                                    <option value="">Select </option>
                                    <option value="1">Otion 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>
                            <div class="col s12 m2">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="forms-table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Module</th>
                                            <th>Form</th>
                                            <th>Folder</th>
                                            <th>Mobile Form</th>
                                            <th>Priority</th>
                                            <th>Status</th>
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                            <td class="last-column"> <a href="forms_form.html"
                                                    class="btn waves-effect waves-light bg-m t-c"><i
                                                        class="fa fa-pencil"></i></a>
                                                <a href="#" class="btn waves-effect waves-light bg-m t-c"><i
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
	
	 <div class="page-loader-2" style="display: none;">
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
      <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="forms_id" id="forms_id" />
    </form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#forms-table').DataTable({
                columnDefs: [
                    {
                        targets: [6],
                        className: 'last-column',
                        targets: 'nosort', orderable: false,
                    },
                    { "width": "20px", "targets": [6] },
                ],
                "scrollCollapse": true,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            }); 
            /* var table = $('#forms-table').DataTable({
            	"bStateSave": true,
         		fixedHeader: true,
                 "fnStateSave": function (oSettings, oData) {
                     localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
                 },
                 "fnStateLoad": function (oSettings) {
                     return JSON.parse(localStorage.getItem('MRVCDataTables'));
                 },
                 columnDefs: [
                     {
                         targets: [0],
                         className: 'mdl-data-table__cell--non-numeric'
                     },
                     { orderable: false, 'aTargets': ['nosort'] },
                     { "width": "20px", "targets": [6] },
                 ],
                 // "ScrollX": true,
                 "scrollCollapse": true,
                 "sScrollX": "100%",
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                 initComplete: function () {
                     $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                 }
             });
         	table.state.clear(); 
         	
         	$('.close-message').delay(3000).fadeOut('slow');
         	getFormsList(); */
        });

        function clearFilters() {
            $('#module_fk').val("");
            $('#status_fk').val("");
            $('.searchable').select2();
        }
        
        function getFormsList(){
        	$(".page-loader-2").show();
        	var module_name_fk = $("#module_name_fk").val();
        	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
       
       }
        
    </script>
</body>

</html>