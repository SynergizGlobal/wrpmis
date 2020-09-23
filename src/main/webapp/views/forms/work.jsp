<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    
    <link rel="stylesheet" href="/pmis/resources/css/normalize.css">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/work.css">
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
    <!-- header ends  -->


    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Work</h6>
                        </div>
                    </span>
                    <div class="">
                        <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                                <!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="add-work" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Work</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col m12 s12">

                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Project Name </th>
                                            <th>Work ID</th>
                                            <th>Work Name </th>
                                            <th>Sanctioned Year </th>
                                            <th>Railway Agency </th>
                                            <th>Executed By </th>
                                            <th class="no-sort">Remarks</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
 									 <c:forEach var="obj" items="${workList }">
                                        <tr>
                                       
                                            <td>&nbsp;${ obj.project_name } </td>
                                            <td>&nbsp;${ obj.work_id }</td>
                                            <td>&nbsp;${ obj.work_name }</td>
                                            <td>&nbsp;${ obj.sanctioned_year }</td>
                                            <td>${ obj.railway_name }</td>
                                            <td id="ed">${ obj.executed_by_id_fk }</td>
                                            <td>&nbsp;${ obj.remarks }</td>
                                            <td class="last-column"> <a href="javascript:void(0);" 
                                            				onclick="getId('${ obj.work_id } ', ' ${ obj.executed_by_id_fk }');"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
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





    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery.min.js"></script>
    <script src="/pmis/resources/js/materialize.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="work_id" id="work_id" />
    	<input type="hidden" name="executed_by_id_fk" id="executed_by_id_fk" />
    </form>
    <script>
        $(document).ready(function () {
            $('.sidenav').sidenav();
            $('select').formSelect();
            // $('.modal').modal();
            // $('.tooltipped').tooltip();
            // $(".datepicker").datepicker();
            // $('#textarea1').characterCounter();
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
                    { "width": "20px", "targets": [7] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
        });
        function getId(work_id,executed_by_id_fk){
	    	$("#work_id").val(work_id);
	    	
	    	$("#executed_by_id_fk").val(executed_by_id_fk);
	    	
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/edit-work');
	    	$('#getForm').submit();
	    }

    </script>

</body>

</html>