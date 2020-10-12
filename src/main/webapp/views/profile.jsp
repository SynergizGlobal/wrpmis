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
      .card-title {
            background-color: #DFE6F6;
        }
	     thead tr {
	        background-color: #2E58AD;
	     }
	     td{
	        word-break: break-word;
	    	word-wrap: break-word;
	   		white-space: initial;
	   		width:50%;  
	     }
	     .mdl-data-table tr td{	     
	   		text-align:center !important;  
	   	}
	     thead th{
	     	text-align: center !important;
   			color: #fff !important;
	     }
        .card-title.headbg {
            padding: 15px;
            margin: -24px;
            text-align: center;
        }

        /* left side code  */
        .profile_photo img {
            width: 250px;
            height: 250px;
            border-radius: 50%;
        }

        .profile_name {
            font-size: 3rem;
            margin: 20px 0;
            font-weight: bold;
            text-transform: uppercase;
        }

        .profile_designation {
            font-size: 1.5rem;
            font-weight: 400;
            margin: 20px 0;
            text-transform: capitalize;
        }

        .profile_role {
            font-size: 1rem;
            font-weight: 300;
            text-transform: capitalize;
        }

        /* right side code  */
        .profile_info {
            text-align: left;
            /* padding: 20px; */
        }
       
        .card .card {
            -webkit-box-shadow: 0 8px 17px 2px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
            box-shadow: 0 8px 17px 2px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
        }

        @media only screen and (max-width: 600px) {

            .dataTables_info,
            .pagination {
                text-align: center;
            }
        }        
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <div class="row">
                      <c:forEach var="obj" items="${userDetails }">
                        <div class="col m4 s12 center-align">
                            <div class="card">
                                <div class="card-content">
                                    <span class="card-title headbg">Basic Details</span>
                                    <div class="profile_photo">
                                        <img src="/pmis/resources/images/mrvc.png">
                                    </div>
                                    <div class="profile_name">${ obj.user_name } </div>
                                    <div class="profile_designation">${ obj.designation }
                                        <span class="profile_role">( ${ obj.user_role_name_fk } )</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col m4 s12">
                            <div class="card">
                                <div class="card-content">
                                    <span class="card-title headbg">Other Details</span>
                                    <div class="profile_info">
                                        <div class="row">                                        
											<table>
											    <tbody>
											        <tr>
											            <td>User ID</td>
											            <td>: &nbsp; ${ obj.user_id }</td>
											        </tr>
											         <tr>
											            <td>Email</td>
											            <td>: &nbsp; ${ obj.email_id }</td>
											        </tr>
											        <tr>
											            <td>Department</td>
											            <td>: &nbsp; ${ obj.department_fk }</td>
											        </tr>
											        <tr>
											            <td>Reporting To</td>
											            <td>: &nbsp; ${ obj.reporting_to_id_srfk }</td>
											        </tr>
											        <tr>
											            <td>Mobile No</td>
											            <td>: &nbsp; ${ obj.mobile_number }</td>
											        </tr>
											        <tr>
											            <td>Landline</td>
											            <td>: &nbsp; ${ obj.landline }</td>
											        </tr>
											        <tr>
											            <td>Extension</td>
											            <td>: &nbsp; ${ obj.extension }</td>
											        </tr>
											         <tr>
											            <td>PMIS Key</td>
											            <td>: &nbsp; ${ obj.pmis_key_fk }</td>
											        </tr>
											         <tr>
											            <td>Remarks</td>
											            <td>: &nbsp; ${ obj.remarks }</td>
											        </tr>
											        
											    </tbody>
											</table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col m4 s12">
                            <div class="card" style="min-height: 445px;">
                                <div class="card-content">
                                    <span class="card-title headbg">User Access</span>
                                    <!-- <div style="padding-top: 100px;"></div> -->
                                    <table id="example2" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Access Type</th>
                                                <th>Value</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                       </c:forEach>
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
            $('#example2').DataTable({
                "searching": false,
                columnDefs: [
                    {
                        targets: ['_all'],
                        className: 'mdc-data-table__cell'
                    }
                ],
                "language": {
                    "info": "Showing _START_ - _END_ in _TOTAL_ ",
                    "lengthMenu": "",
                    "paginate": {
                        "previous": "<",
                        "next": ">",
                    },
                },
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
            });
        })
    </script>
	
</body>

</html>