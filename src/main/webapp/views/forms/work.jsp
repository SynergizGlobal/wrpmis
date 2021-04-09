<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/work.css">
    <link rel="stylesheet" href="/pmis/resources/css/header-footer.css">
    <style>
        .dataTables_filter label::after{
         	content:'';
         }
         .right-btns .fa{
         	position:relative;
         	top:-35px;
         }
         .right-btns .fa+.fa{
         	right:-10px;
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
							<h6>Work</h6>
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
									<a href="add-work-form"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-plus-circle"></i> Add Work</strong></a>
								</div>
							</div>

							<div class="col s12 m4 r-align">
								<div class="m-1 ">
									<a href="javascript:void(0);" onclick="exportWork();"
										class="btn waves-effect waves-light bg-s t-c"> <strong><i
											class="fa fa-cloud-download"></i> Export Data</strong></a>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<span class="card-title headbg">
							<div class="center-align bg-m p-2 m-b-5">
								<h6>Update Work</h6>
							</div>
						</span>

						<div class="row">
							<div class="col m12 s12">

								<table id="datatable-works" class="mdl-data-table">
									<thead>
										<tr>
											<th>Project</th>
											<th>Work ID</th>
											<th>Work Name</th>
											<th>Sanctioned Year</th>
											<th>Railway Agency</th>
											<th>Executed By</th>
											<th class="no-sort">&nbsp; Remarks &nbsp;</th>
											<th class="no-sort">Action</th>
										</tr>
									</thead>
									<tbody>
										<%-- <c:forEach var="obj" items="${workList }">
											<tr>

												<td>${ obj.project_id_fk}- ${obj.project_name }</td>
												<td>${ obj.work_id }</td>
												<td>${ obj.work_name }</td>
												<td>${ obj.sanctioned_year_fk }</td>
												<td>${ obj.railway }</td>
												<td>${ obj.executed_by }</td>
												<td>&nbsp;${ obj.remarks }</td>
												<td class="last-column"><a href="javascript:void(0);"
													onclick="getWork('${ obj.work_id }');"
													class="btn waves-effect waves-light bg-m t-c "><i
														class="fa fa-pencil"></i> </a>  <a  onclick="deleteWork('${ obj.work_id }');" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
												</td>

											</tr>
										</c:forEach> --%>
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
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    
	<form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="work_id" id="work_id" />
    </form>
    
	<form action="<%=request.getContextPath() %>/export-work" name="exportWorkForm" id="exportWorkForm" target="_blank" method="post">	
        <input type="hidden" name="work_id" id="exportWork_id" />
	</form>
    <script>
        $(document).ready(function () {
            $('.sidenav').sidenav();
            $('select').formSelect();
             $('.modal').modal();
             $('.tooltipped').tooltip();
             $(".datepicker").datepicker();
            // $('#textarea1').characterCounter();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            $('#datatable-works').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [7] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                //"sScrollY": 400,
                "sScrollX": "100%",
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
            getWorksList();
        });
        
    	function getWorksList() {
    		$(".page-loader-2").show();

         	table = $('#datatable-works').DataTable();
    		table.destroy();

    		$.fn.dataTable.moment('DD-MMM-YYYY');

    		var myParams = "";

    		/***************************************************************************************************/

    		$("#datatable-works")
    				.DataTable(
    						{
    							"bProcessing" : true,
    							"bServerSide" : true,
    							"sort" : "position",
    							//bStateSave variable you can use to save state on client cookies: set value "true" 
    							"bStateSave" : false,
    							//Default: Page display length
    							"iDisplayLength" : 10,
    							"iData" : {
    								"start" : 52
    							},
    							//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
    							"iDisplayStart" : 0,
    							"fnDrawCallback" : function() {
    								//Get page numer on client. Please note: number start from 0 So
    								//for the first page you will see 0 second page 1 third page 2...
    								//Un-comment below alert to see page number
    								//alert("Current page number: "+this.fnPagingInfo().iPage);
    							},
    							//"sDom": 'l<"toolbar">frtip',
    							"initComplete" : function() {
    								$('.dataTables_filter input[type="search"]')
    										.attr('placeholder', 'Search')
    										.css({
    											'width' : '350px ',
    											'display' : 'inline-block'
    										});

    								var input = $('.dataTables_filter input')
    										.unbind(), self = this.api(), $searchButton = $(
    										'<i class="fa fa-search" title="Go">')
    								//.text('Go')
    								.click(function() {
    									self.search(input.val()).draw();
    								}), $clearButton = $(
    										'<i class="fa fa-close" title="Reset">')
    								//.text('X')
    								.click(function() {
    									input.val('');
    									$searchButton.click();
    								})
    								$('.dataTables_filter').append(
    										'<div class="right-btns"></div>');
    								$('.dataTables_filter div').append(
    										$searchButton, $clearButton);

    								/* var input = $('.dataTables_filter input').unbind(),
    								self = this.api(),
    								$searchButton = $('<i class="fa fa-search">')
    								           //.text('Go')
    								           .click(function() {			   	                    	 
    								              self.search(input.val()).draw();
    								           })			   	        
    								  $('.dataTables_filter label').append($searchButton); */
    							},
    							columnDefs : [ {
    								"targets" : 'no-sort',
    								"orderable" : false,
    							} ],
    							"sScrollX" : "100%",
    							"sScrollXInner" : "100%",
    							"bScrollCollapse" : true,
    							"language" : {
    								"info" : "_START_ - _END_ of _TOTAL_",
    								paginate : {
    									next : '<i class="fa fa-angle-right"></i>', 
    									previous : '<i class="fa fa-angle-left"></i>'  
    								}
    							},
    							"bDestroy" : true,
    							"sAjaxSource" : "	<%=request.getContextPath()%>/ajax/get-works?"+myParams,
    		        "aoColumns": [
    		            { "mData": function(data,type,row){
    		            	var project_name= "";
    		            	if($.trim(data.project_name) != ''){project_name = '-'+ $.trim(data.project_name)}
    		            	if($.trim(data.project_id_fk) == ''){ return '-'; }else{ return data.project_id_fk + project_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.work_id) == ''){ return '-'; }else{ return data.work_id; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		            	if($.trim(data.work_short_name) == ''){ return '-'; }else{ return data.work_short_name; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.sanctioned_year_fk) == ''){ return '-'; }else{ return data.sanctioned_year_fk; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.railway) == ''){ return '-'; }else{ return data.railway; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.executed_by) == ''){ return '-'; }else{ return data.executed_by; }
    		            } },
    		            { "mData": function(data,type,row){
    		            	if($.trim(data.remarks) == ''){ return '-'; }else{ return data.remarks; }
    		            } },
    		         	{ "mData": function(data,type,row){
    		         		var work_id = "'"+data.work_id+"'";
    	                    var actions = '<a href="javascript:void(0);"  onclick="getWork('+work_id+');" class="btn waves-effect waves-light bg-m t-c" ><i class="fa fa-pencil"></i></a>';
    		            	return actions;
    		            } }
    		            
    		        ]
    		    });
    	    
    	  $(".page-loader-2").hide();  		     
      	
     }
        function getWork(work_id){
	    	$("#work_id").val(work_id);
	    	//$("#executed_by_id_fk").val(executed_by_id_fk);

	    	//$("#railway_id_fk").val(railway_id_fk);
	    	
	    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-work');
	    	$('#getForm').submit();
	    }
        function deleteWork(work_id){       
        	$("#work_id").val(work_id);
        	showCancelMessage();        	
        }
        function showCancelMessage() {
        	
        	swal({
	        	
	            title: "Are you sure?",
	            text: "You will be able to change the status of record!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "Yes, delete it!",
	            cancelButtonText: "No, cancel it!",
	            closeOnConfirm: false,
	            closeOnCancel: false
	        }, function (isConfirm) {
	            if (isConfirm) {
	                //swal("Deleted!", "Record has been deleted", "success");
	                $('#getForm').attr('action', '<%=request.getContextPath()%>/deleteRow');
	    	    	$('#getForm').submit();
	            } else {
	                swal("Cancelled", "Record is safe :)", "error");
	            }
	        });
	    }
        
        function exportWork(){
         	 var work_id = $("#work_id").val();
         	 
         	 $("#exportWork_id").val(work_id);
         	 $("#exportWorkForm").submit();
      	}
    </script>

</body>

</html>