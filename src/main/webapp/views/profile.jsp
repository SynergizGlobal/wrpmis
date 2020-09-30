<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
   <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
   
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
	<link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">

   
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    
   
   
    <style>

        /* profile related styling  */
        .card-title {
            background-color: #DFE6F6;
        }

        .profile-head.row {
            margin-bottom: 0;
        }

        .profile-head {
            padding: 24px;
            box-shadow: 1px 0 4px rgba(0, 0, 0, 0.2);
            background-color: #DFE6F6;
        }

        .profile-head.row h6 {
            margin-bottom: 0;
            margin-top: 14px;
        }

        .profile-summery {
            border: 1px solid #DFE6F6;
            box-shadow: 1px 3px 4px rgba(0, 0, 0, 0.2);
        }

        .profile-summery .col {
            text-transform: capitalize;
            padding-top: .5rem;
            padding-bottom: .5rem;
        }

        /* data table  styling  */
        .dataTables_scrollBody,
        .dataTables_scrollHead,
        .dataTables_scrollFoot {
            overflow: initial !important;
        }

        .dataTables_scroll {
            overflow: auto;
        }

        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        @media only screen and (max-width: 600px) {
            div.dataTables_wrapper div.dataTables_filter input {
                width: 90% !important;
                margin-left: 0;
            }
        }

        /* min and max height for data table  */
        .p-2 {
            padding: 1.2rem !important;
        }

        #example1_wrapper {
            min-height: 465px;
            min-height: 265px;
        }
    </style>
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

  
    <div class="row">
        <!-- <div class="col m3 hide-on-small-only"></div> -->
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <!-- <span class="card-title headbg">

                        <div class="profile-head row">
                            <div class="col m2 s6">
                                <h6 class="left-align"><b>My Profile</b></h6>
                            </div>
                            <div class="col m3 s6">
                                <div class="profile-img right-align">
                                    <img src="images/user-black.png" alt=""
                                        style="width:50px;height:50px;border-radius:50%;background-color:#fff; ">
                                </div>

                            </div>
                        </div>

                    </span> -->
                    
                    
                    <div class="container">
                        <div class="row">
                            <div class="col m5 s12">
                                <div class="profile-head row">
                                    <div class="col m6 s6">
                                        <h6 class="left-align"><b>My Profile</b></h6>
                                    </div>
                                    <div class="col m6 s6">
                                        <div class="profile-img right-align">
                                            <img src="images/user-black.png" alt=""
                                                style="width:50px;height:50px;border-radius:50%;background-color:#fff; ">
                                        </div>

                                    </div>
                                </div>
                                <div class="row profile-summery">
                                <c:forEach var="obj" items="${userDetails }">
                                     <div class="col m6 s6"> name: </div>
                                    <div class="col m6 s6">&nbsp;${ obj.user_name }  </div>

                                    <div class="col m6 s6"> email: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.email_id } </div>

                                    <div class="col m6 s6"> department: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.department_fk } </div>

                                    <div class="col m6 s6"> designation: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.designation } </div>

                                    <div class="col m6 s6"> reporting to: </div>
                                    <div class="col m6 s6">&nbsp; ${ obj.reporting_to_id_srfk }</div>

                                    <div class="col m6 s6"> user role name: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.user_role_name_fk } </div>

                                    <div class="col m6 s6"> mobile no: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.mobile_number } </div>

                                    <div class="col m6 s6"> landline: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.landline } </div>

                                    <div class="col m6 s6"> extension: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.extension } </div>

                                    <div class="col m6 s6"> pmis key: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.pmis_key_fk } </div>

                                    <div class="col m6 s6"> remarks: </div>
                                    <div class="col m6 s6"> &nbsp;${ obj.remarks } </div>
                                   </c:forEach> 
                                </div>
                            </div>
                            <div class="col m6 s12 offset-m1">
                                <div class="profile-head">
                                    <!-- <div class="col m12 s12"> -->
                                    <h6 class="center-align"><b>User Access</b></h6>
                                    <!-- </div> -->
                                </div>
                                <div class="profile-summery">
                                    <!-- this can be delete if data entered in table  -->
                                    <div style="padding-top:100px"></div>
                                    <table id="example1" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Access Type</th>
                                                <th>Value</th>
                                                <th style="display: none;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td style="display: none;"></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                    <!-- this can be delete if data entered in table  -->
                                    <div style="padding-bottom:100px"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>


  <!-- footer included -->
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>

	 <script>
        $(document).ready(function () {
            $('select').formSelect();
            $('#example1').DataTable({
                "searching": false,
                "ordering": false,
                "language": {
                    "lengthMenu": ""
                },
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric'
                    }
                ],
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
                initComplete: function () {
                    // $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
    </script>	
	
	
</body>

</html>