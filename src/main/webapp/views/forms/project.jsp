<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project - Update Forms - PMIS</title>
    <link href="/wrpmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
    <!-- <link rel="stylesheet" href="/wrpmis/resources/css/project.css"> -->
    <link rel="stylesheet" href="/wrpmis/resources/css/rits.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-grid-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/wrpmis/resources/css/mobile-form-template.css" />
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
    
    .page-title-bar {
        background: #f4f4f4;
        padding: 20px 30px;
        border-bottom: 1px solid #ccc;
        margin-bottom: 10px;
        display: flex;
    	justify-content: space-between;
    	    align-items: center;
    }

    .page-title-bar h5 {
        margin: 0;
        font-size: 24px;
        font-weight: bold;
        display: inline-block;
    }

    .action-buttons{
    	display: flex;
    	align-items: center;
    	gap: 10px;
    }

    .action-buttons .btn {
        margin-left: 10px;
    }
	
	.action-buttons .btn i{
		line-height: 100%;
	}
	
    .data-table-wrapper {
        background: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
    }

    .success, .error {
        margin: 10px 0;
        padding: 10px;
        border-radius: 4px;
        text-align: center;
        font-weight: bold;
    }

    .success {
        background-color: #d4edda;
        color: #155724;
    }

    .error {
        background-color: #f8d7da;
        color: #721c24;
    }

    table.mdl-data-table {
        width: 100%;
        border-collapse: collapse;
    }

    table.mdl-data-table th,
    table.mdl-data-table td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table.mdl-data-table th {
        background: #e56717;
        color: white;
    }

    table.mdl-data-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .last-column {
        text-align: center;
    }

    .mob-btn i {
        font-size: 16px;
    }

    .container-padding {
        padding: 30px;
    }
    
.mdl-data-table td:nth-child(2),  /* Project Name */
.mdl-data-table td:nth-child(12) { /* Remarks */
    white-space: pre-wrap;
    word-break: break-word;
    max-width: 200px; /* Adjust as needed */
    overflow-wrap: break-word;
}
    </style>
</head>
<body>

    <!-- header  starts-->
         <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

<!-- Page Content -->
<div class="container-padding">
    <div class="page-title-bar">
        <h5>Project</h5>
        <div class="action-buttons">
            <a href="add-project-form" class="btn waves-effect waves-light bg-s t-c">
                <i class="fa fa-plus-circle"></i> Add
            </a>
            <a href="javascript:void(0);" onclick="exportProject();" class="btn waves-effect waves-light bg-s t-c">
                <i class="fa fa-cloud-download"></i> Export
            </a>
        </div>
    </div>

    <div class="data-table-wrapper">
        <c:if test="${not empty success}">
            <div class="success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

 <table id="project_table" class="mdl-data-table" style="table-layout: fixed; width: 100%;">
    <thead>
        <tr>
            <th>Project ID</th>
			<th style="width: 15%;">Project Name</th>
            <th>Project Status</th>
            <th>Project Type</th>
            <th>Railway Zone</th>
            <th>Plan Head Number</th>
            <th>Sanctioned Year</th>
            <th>Sanctioned Amount</th>
            <th>Sanctioned Commissioning Date</th>
            <th>Division</th>
            <th>Sections</th>
			<th style="width: 20%;">Remarks</th>
            <th class="no-sort">Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="obj" items="${projectList}">
            <tr>
                <td>${obj.project_id}</td>
                <td>${obj.project_name}</td>
                <td>${obj.project_status}</td>
                <td>${obj.project_type_name}</td>
                <td>${obj.railway_zone}</td>
                <td>${obj.plan_head_number}</td>
                <td>${obj.sanctioned_year}</td>
                <td>${obj.sanctioned_amount}</td>
                <td>${obj.sanctioned_commissioning_date}</td>
                <td>${obj.division}</td>
                <td>${obj.sections}</td>
                <td>${obj.remarks}</td>
                <td class="last-column">
                    <a href="javascript:void(0);" onclick="getProject('${obj.project_id}')" class="btn waves-effect waves-light bg-m t-c mob-btn">
                        <i class="fa fa-pencil"></i>
                    </a>
                    <%-- Optional delete button
                    <a onclick="deleteProject('${obj.project_id}');" class="btn waves-effect waves-light bg-s t-c">
                        <i class="fa fa-trash"></i>
                    </a>
                    --%>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

    </div>
</div>

	<!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>


    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    
    <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="project_id" id="project_id" />
    </form>
    
    <form action="<%=request.getContextPath() %>/export-project" name="exportProjectForm" id="exportProjectForm" target="_blank" method="post">	
        <input type="hidden" name="project_id" id="exportProject_id" />
	</form>

    <script>
   	    var pageNo = window.localStorage.getItem("projectPageNo");
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            
          
        	$('.close-message').delay(3000).fadeOut('slow');
        	table = $('#project_table').DataTable();
      		 
    		table.destroy();
    		 
		    table = $('#project_table').DataTable({
		    	    
	        		"bStateSave": true,  
	        		fixedHeader: true,
	              
	            	//Default: Page display length
					"iDisplayLength" : 10,
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
	                    {
	                        targets: [2,3],
	                        className: 'hideCOl'
	                    },
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
	            table = $('#project_table').DataTable();
	            table.fnPageChange( pageNo );
	            table.destroy();
        });
      
 		
	  function getProject(project_id){
	    	$("#project_id").val(project_id);
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-project');
	    	$('#getForm').submit();
	    }
			function deleteProject(project_id){
	       	$("#project_id").val(project_id);
	       	showCancelMessage();
	    }
 	        	
 	        
        function showCancelMessage() {
        	swal({
	            title: "Are you sure?",
	            text: "You will be changing the status of the record!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "Yes, delete it!",
	            cancelButtonText: "No, cancel it!",
	            closeOnConfirm: false,
	            closeOnCancel: false
	        }, function (isConfirm) {
	            if (isConfirm) {
	               // swal("Deleted!", "Record has been deleted", "success");
	            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-project');
	    	    	$('#getForm').submit();
	           }else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
        
        function exportProject() {
            const table = document.getElementById("project_table");
            const rows = table.querySelectorAll("tr");

            let exportTable = "<table border='1'>";

            rows.forEach(row => {
                const cells = row.querySelectorAll("th, td");

                // Exclude the last column
                let rowData = "<tr>";
                for (let i = 0; i < cells.length - 1; i++) {
                    rowData += "<td>" + cells[i].innerText.trim() + "</td>";
                }
                rowData += "</tr>";

                exportTable += rowData;
            });

            exportTable += "</table>";

            // Create downloadable Excel file
            const blob = new Blob([exportTable], {
                type: "application/vnd.ms-excel"
            });

            const link = document.createElement("a");
            link.href = URL.createObjectURL(blob);
            link.download = "project_data.xls";
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }

    </script>

</body>

</html>