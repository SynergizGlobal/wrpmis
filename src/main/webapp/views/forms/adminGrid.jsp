<%@ page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <title>Admin Forms - Admin - PMIS</title>
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-grid-template.css" />

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
        @media only screen and (max-width:769px) {
		    .dataTables_filter label{
		        position:relative;
		    }
		    .dataTables_filter label::after{
		        position:absolute;
		        right:5px;
		        top:30px;
		    }
		    .last-column .btn+.btn {
		        margin-left: 10px;
		    }
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
                            <h6>Admin Forms</h6>
                        </div>
                    </span>
                    <div class="row clearfix">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<c:if test="${not empty success }">
								<div class="center-align m-1 close-message">${success}</div>
							</c:if>
							<c:if test="${not empty error }">
								<div class="center-align m-1 close-message">${error}</div>
							</c:if>
						</div>
					</div>
                    
                    <div class="">
                        <div class="row plr-1">
                            <div class="col s12 m4 offset-m4 center-align">
                                <div class="m-1">
                                    <a href="<%=request.getContextPath() %>/add-admin-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Admin Form</strong></a>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
	                            <div style="display:none;" id="webView">
	                                <table id="admin-grid" class="mdl-data-table">
	                                    <thead>
	                                        <tr>
	                                            <th>ID</th>
	                                            <th>Form Name</th>
	                                            <th>Priority</th>
	                                            <th>Status</th>
	                                            <th class="nosort">Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                      <c:forEach var="obj" items="${adminList }">
	                                        <tr>
	                                        	<td>&nbsp;${ obj.admin_form_id }</td>
												<td>&nbsp;${ obj.form_name }</td>
												<td>&nbsp;${ obj.priority }</td>
												<td>&nbsp;${ obj.soft_delete_status_fk }</td>
	                                            <td class="last-column"> <a class="btn waves-effect waves-light bg-m t-c" onclick="getAdmin('${ obj.admin_form_id }')">
	                                            <i class="fa fa-pencil"></i></a>
	                                                <a  class="btn waves-effect waves-light bg-m t-c" onclick="gotoLink('${ obj.url }')"><i class="fa fa-share"></i></a>
	                                            </td>
	                                        </tr>
	                                      </c:forEach>
	                                    </tbody>
	
	                                </table>
	                              </div>
	                              <div style="display:none;" id="mobView">
	                                <table id="admin-grid_mob" class="mdl-data-table">
	                                    <thead>
	                                        <tr>
	                                            <th>Form Name</th>
	                                            <!-- <th>Priority</th> -->
	                                            <th>Status</th>
	                                            <th class="nosort">Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                      <c:forEach var="obj" items="${adminList }">
	                                        <tr>
	                                        	<%-- <td>&nbsp;${ obj.admin_form_id }</td> --%>
												<td>&nbsp;${ obj.form_name }</td>
												<%-- <td>&nbsp;${ obj.priority }</td> --%>
												<td>&nbsp;${ obj.soft_delete_status_fk }</td>
	                                            <td class="last-column"> <a class="btn mobile-btn waves-effect waves-light bg-m t-c" onclick="getAdmin('${ obj.admin_form_id }')">
	                                            <i class="fa fa-pencil"></i></a>
	                                                <a  class="btn mobile-btn waves-effect waves-light bg-m t-c" onclick="gotoLink('${ obj.url }')"><i class="fa fa-share"></i></a>
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
	

    <!-- footer  -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

 	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="admin_form_id" id="admin_form_id" />
    </form>
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
                        
            if(window.matchMedia("(max-width: 769px)").matches){
                $('tbody.web').removeAttr('id');
                $('#mobView').css({'display':'block'});
                $('#admin-grid_mob').DataTable({
                    columnDefs: [
                        {
                            targets: 'nosort', orderable: false,
                        },
                        { "width": "20px", "targets": [2] },
                    ],
                    "scrollCollapse": true,
                    "sScrollX": "100%",
                    "sScrollXInner": "100%",
                    "bScrollCollapse": true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    }
                });
            } else {
                $('#webView').css({'display':'block'});
                $('#admin-grid').DataTable({
                    columnDefs: [
                        {
                            targets: 'nosort', orderable: false,
                        },
                        { "width": "20px", "targets": [4] },
                    ],
                    "scrollCollapse": true,
                    "sScrollX": "100%",
                    "sScrollXInner": "100%",
                    "bScrollCollapse": true,
                    initComplete: function () {
                        $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    }
                });
            }
        });

        function getAdmin(admin_form_id){
	    	$("#admin_form_id").val(admin_form_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-admin');
	    	$('#getForm').submit();
	    }
        
        function gotoLink(path){
       	 if ($.trim(path) != '') { 
       		  console.log(path)
       		  window.location.href = "<%=request.getContextPath()%>/"+path
     		      
      		 }
        }
    </script>
</body>

</html>