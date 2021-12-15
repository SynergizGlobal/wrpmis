<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Module Permissions - Admin - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-grid-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <style>
        .dataTables_filter label::after{
         	content:'';
         }
         table.dataTable thead .sorting_asc.sorting_disabled:before,
         table.dataTable thead .sorting_asc.sorting_disabled:after{
         	content:'';
         }
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
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
        	width:30vw;
        	min-width:30vw;
        }
       .mdl-data-table__cell--non-numeric.mdl-data-table__cell--non-numeric {
		    text-align: center;
		}
     } 
    .m-n1 {
        margin: -2rem auto 0;
    }
    
    @media only screen and (max-width: 767px) {   
    	.mob-mar {
            text-align: left;
        } 	 
        .exportButton .btn {
            padding-left: 6px;
            padding-right: 6px;
        }
    }
    </style>
</head>
<body>

    <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

	<div class="row">
	
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<!-- <h6 class="hide-on-med-and-down">Update Project</h6>
								<h6 class="hide-on-large-only">Project</h6> -->
								<h6 class="mob-mar">Module Permission</h6>
								
							</div>
						</span>
						<div class="row">
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
								<table id="module_permission_table" class="mdl-data-table">
									<thead>
										<tr>
											<th>Module</th>
											<th>Module Status</th>
											<th class="no-sort">Action</th>
										</tr>
									</thead>
									<tbody>
										 <%-- <c:forEach var="obj" items=""> --%>
											<tr>
												<td>dummy11</td>
												<td>dummy12</td>												
												<td class="last-column"><a href="<%=request.getContextPath() %>/get-module-permission"
													class="btn waves-effect waves-light bg-m t-c mob-btn"><i
														class="fa fa-pencil"></i> </a> 
											</tr>
										<%-- </c:forEach> --%>
									</tbody>
								</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>


    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    

    <script>
   	    var pageNo = window.localStorage.getItem("projectPageNo");
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           
        	$('.close-message').delay(3000).fadeOut('slow');
        	table = $('#module_permission_table').DataTable();
      		 
    		table.destroy();
    		 
		    table = $('#module_permission_table').DataTable({
		    	    
	        		"bStateSave": true,  
	        		fixedHeader: true,
	        		"pageLength":25,
	            	//Default: Page display length
					"iData" : {
						"start" : 52
					},"iDisplayStart" : 0,
                 "drawCallback" : function() {
 					var info = table.page.info();
 					window.localStorage.setItem("projectPageNo", info.page);
 				},
	                columnDefs: [
	                    {
	                        targets: [0],
	                        className: 'mdl-data-table__cell--non-numeric',
	                        targets: 'nosort', orderable: false,
	                    },
	                    { "width": "20px", "targets": [2] }, 
	                    
	                ],
	                "sScrollX": "100%",
	                "sScrollXInner": "100%",
	                "bScrollCollapse": true,
	                "bAutoWidth": true,
	                "ordering": false, //to stop sorting option                
	                fixedHeader: true, // to change the language of data table	          
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
							$('.dataTables_filter').append(
									'<div class="right-btns"></div>');
							$('.dataTables_filter div').append(
									$searchButton, $clearButton);                    
	                }
	            });
		        if(pageNo == null){pageNo = 0;}else{pageNo = Number(pageNo);}
	            table = $('#module_permission_table').DataTable();
	            table.fnPageChange( pageNo );
	            table.destroy();
        });
			 	        	
    </script>

</body>

</html>