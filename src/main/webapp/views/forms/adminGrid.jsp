<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants2"%>
<%@ page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <title>Admin Forms - Admin - PMIS</title>
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/searchable-dropdown.css">
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-grid-template.css" /> 

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
        .fw-111{
	        	width:11vw; 
	        	min-width:11vw;
	    }
	    div.dataTables_wrapper div.dataTables_info {
		    white-space: break-spaces;
		} 
          @media only screen and (max-width: 769px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
			}
			.mob-btn{
				padding:0 12px;
			}
			.hideCOl{
				display:none;
			} 
			.r-300{
				width:30vw !important;
        		max-width:30vw;
			}
			 .dataTables_filter label{
	        	position:relative;
	        }
	        .dataTables_filter label::after{
	        	position:absolute;
	        	right:5px;
	        	top:30px;
	        }
	        .fw-111{
	        	width:20vw;
	        	min-width:20vw;
	        }
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
		.no-sort.sorting_asc:before,
	.no-sort.sorting_asc:after{
		opacity:0 !important;
		content:'' !important;
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
	                                <table id="admin-grid" class="mdl-data-table">
	                                    <thead>
	                                        <tr>
	                                            <th class="no-sort">ID</th>
	                                            <th>Form Name</th>
	                                            <th>Priority</th>
	                                            <th>Status</th>
	                                            <th class="no-sort">Action</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                      <c:forEach var="obj" items="${adminList }">
	                                        <tr>
	                                        	<td>&nbsp;${ obj.admin_form_id }</td>
												<td>&nbsp;${ obj.form_name }</td>
												<td>&nbsp;${ obj.priority }</td>
												<td>&nbsp;${ obj.soft_delete_status_fk }</td>
	                                            <td class="last-column"> <a class="btn waves-effect waves-light bg-m t-c mobile-btn" onclick="getAdmin('${ obj.admin_form_id }')">
	                                            <i class="fa fa-pencil"></i></a>
	                                                <a  class="btn waves-effect waves-light bg-m t-c mobile-btn" onclick="gotoLink('${ obj.url }')"><i class="fa fa-share"></i></a>
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
    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>

    <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#admin-grid').DataTable({
                columnDefs: [
                    {
                        targets: 'no-sort', orderable: false,
                    },
                    {targets: [0,2], className: 'hideCOl'},
                    {targets: [0,1,2,3,4], className: 'fw-111'},
                    {targets: [1,3,4], className: 'fw-111'},
                ],
                "scrollCollapse": true,
                "sScrollX": "100%",
                "ordering":false,
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
					.unbind()
					.bind('keyup',function(e){
					    if (e.which == 13){
					    	self.search(input.val()).draw();
					    }
					}), self = this.api(), $searchButton = $(
					'<i class="fa fa-search" title="Go">')
					.click(function() {
						self.search(input.val()).draw();
					}), $clearButton = $(
							'<i class="fa fa-close" title="Reset">')
					.click(function() {
						input.val('');
						$searchButton.click();
					})
					$('.dataTables_filter').append( '<div class="right-btns"></div>');
					$('.dataTables_filter div').append( $searchButton, $clearButton);
                }
            });
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