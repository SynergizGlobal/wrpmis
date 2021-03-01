<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alerts</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        .modal-header {
            text-align: center;
            background-color: #2E58AD;
            color: #fff;
            margin: -24px -24px 20px !important;
            padding: 1rem;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
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
                            <h5>Alerts</h5>
                        </div>
                    </span>
                    <div class="">
                        <!-- <div class="row plr-1">
                            <div class="col s12 m4">
                                 <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div>
                            </div>
                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="Notifications.html" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Notifications</strong></a>
                                </div>
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div> -->
                        <div class="row no-mar">
                            <form action="">
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">HOD</p>
                                    <select id="hod" class="searchable" name="hod">
                                        <option value="" disabled>Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Work</p>
                                    <select id="work" class="searchable" name="work">
                                        <option value="" disabled>Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Contract</p>
                                    <select id="contract" class="searchable" name="contract">
                                        <option value="" disabled>Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Contractor</p>
                                    <select id="contractor" class="searchable" name="contractor">
                                        <option value="" disabled>Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Alert Type</p>
                                    <select id="alert_type" class="searchable" name="alert_type">
                                        <option value="" disabled>Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div>
                                <!-- <div class="col s12 m2 input-field">
                                    <p class="searchable_label">Activity Level</p>
                                    <select id="activity_level" class="searchable" name="activity_level">
                                        <option value="" disabled>Select</option>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                    </select>
                                </div> -->
                                <div class="col s12 m2">
                                    <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                        style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                        Filters</button>
                                </div>
                            </form>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="notifications-table" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>HOD </th>
                                            <th>Work </th>
                                            <th>Contract </th>
                                            <th>Contractor </th>
                                            <th>Alert Type </th>
                                            <th>Alert Level</th>
                                            <th>Reason</th>
                                            <th>Remarks</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="obj" items="${alerts }">
	                                        <tr>
	                                            <td>${obj.hod }</td>
	                                            <td>${obj.work_short_name }</td>
	                                            <td>${obj.contract_short_name }</td>
	                                            <td>${obj.contractor_name }</td>
	                                            <td>${obj.alert_type_fk }</td>
	                                            <td>${obj.alert_level }</td>
	                                            <td>${obj.alert_value }</td>
	                                            <td>${obj.remarks }</td>
	                                            <td class="last-column">
	                                                <a href="#modal1" class="btn waves-effect waves-light bg-m t-c modal-trigger">Remarks</a>
	                                            </td>
	                                        </tr>
                                        </c:forEach>
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
   <div id="modal1" class="modal">
       <div class="modal-content">
           <h5 class="modal-header"> Update Remark <span
                   class="right modal-action modal-close"><span
                       class="material-icons">close</span></span></h5>
           <form>
               <div class="row no-mar">
                   <div class="col m1 hide-on-small-only"></div>
                   <div class="input-field col s12 m10">
                       <textarea id="textarea1"
                           class="materialize-textarea"
                           data-length="1000"></textarea>
                       <label for="textarea1">Textarea</label>
                   </div>
                   <div class="col m1 hide-on-small-only"></div>
               </div>
               <div class="row no-mar">
                   <div class="col m1 hide-on-small-only"></div>
                   <div class="col s12 m5">
                       <div class="center-align m-1">
                           <button type="button" style="width: 100%;"
                               class="btn waves-effect waves-light bg-m">Update</button>
                       </div>
                   </div>
                   <div class="col s12 m5">
                       <div class="center-align m-1">
                           <button type="button" style="width: 100%;"
                               class="btn waves-effect waves-light bg-s modal-close">Cancel</button>
                       </div>
                   </div>
                   <div class="col m1 hide-on-small-only"></div>
               </div>
           </form>
       </div>
   </div>

    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
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
            $('.modal').modal();
            $('.materialize-textarea').characterCounter();
            $('#notifications-table').DataTable({
                columnDefs: [
                    {
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "10px", "targets": [8] },
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                // paging: false,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
        function clearFilters() {
            $('#hod').val("");
            $('#work').val("");
            $('#contract').val("");
            $('#contractor').val("");
            $('#alert_type').val("");
            //$('#activity_level').val("");
            $('.searchable').select2();
        }
    </script>

</body>

</html>