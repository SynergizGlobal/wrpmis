<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.wrpmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Module Permissions - Admin - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/wrpmis/resources/images/favicon.png">
    <link href="/wrpmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/wrpmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/material-design-lite-v.1.0.css">          
    <link rel="stylesheet" href="/wrpmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/wrpmis/resources/css/select2.min.css">
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
							
							<div class="col m12 l10 s12">
								<div class="col s6 m4 l3 input-field">
									<p class="searchable_label">Module</p>
									<select id="module_name_filter" name="module_name"
										onchange="getModulesList();" class="searchable">
										<option value="">Select</option>
									</select>
								</div>
								<div class="col s6 m4 l3 input-field">
									<p class="searchable_label">Status</p>
									<select id="soft_delete_status_fk" name="soft_delete_status_fk"
										onchange="getModulesList();" class="searchable">
										<option value="">Select</option>
									</select> 
								</div>
							</div>
							<div class="col s12 m12 l2 center-align">  
								<button class="btn bg-m waves-effect waves-light t-c"
									style="margin-top: 12px;" onclick="clearFilter();">Clear
									Filters</button>
							</div>
						
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
										 <%-- <c:forEach var="obj" items="${modulesList }">
											<tr>
												<td>${obj.module_name}</td>
												<td>${obj.soft_delete_status_fk}</td>												
												<td class="last-column"><a href="javascript:getModule('${obj.module_name}');"
													class="btn waves-effect waves-light bg-m t-c mob-btn"><i
														class="fa fa-pencil"></i> </a> </td>
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
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer  -->
   <jsp:include page="../layout/footer.jsp"></jsp:include>
   
   <form action="<%=request.getContextPath() %>/get-module-permission" id="moduleForm" name="moduleForm" method="post">
   		<input type="hidden" id="module_name_fk" name="module_name_fk" />
   </form>


    <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/wrpmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/wrpmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/wrpmis/resources/js/dataTables.material.min.js"></script>
    <script src="/wrpmis/resources/js/select2.min.js"></script>
    <script src="/wrpmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/wrpmis/resources/js/datetime-moment-v1.10.12.js"></script>
    
    <script src="/wrpmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
    

    <script type="text/javascript">
    	
    	function getModule(module_name){
    		$(".page-loader").show();
			$("#module_name_fk").val(module_name);
			$("#moduleForm").submit();
		}
    
   	    $(document).ready(function () {
   	    	$(".page-loader").hide();
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
           
        	$('.close-message').delay(3000).fadeOut('slow');
        	
        	getModulesList();
        });
   	    
   	 function clearFilter(){
     	$("#module_name_filter").val("");
     	$("#soft_delete_status_fk").val("");
     	$('.searchable').select2();
     	
     	getModulesList();
     }
   	 
   	 function getModulesList(){
     	$(".page-loader-2").show();
     	getModulesForFilter('');
     	getModuleStatusListForFilter('');
     	
     	var module_name = $("#module_name_filter").val();
     	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
      	
     	table = $('#module_permission_table').DataTable();
 		 
		table.destroy();
		 
	    table = $('#module_permission_table').DataTable({
	    	    
    		"bStateSave": true,  
    		fixedHeader: true,
    		"pageLength":25,
        	//Default: Page display length
			"iData" : {
				"start" : 52
			},
			"iDisplayStart" : 0,
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
        }).rows().remove().draw();
 		
 		
 		table.state.clear();		
 	 
 	 	var myParams = {module_name : module_name, soft_delete_status_fk : soft_delete_status_fk};
 		$.ajax({url : "<%=request.getContextPath()%>/ajax/getModulesList",type:"POST",data:myParams,success : function(data){    				
 				if(data != null && data != '' && data.length > 0){    					
 	         		$.each(data,function(key,val){
 	         			var module_name = "'"+val.module_name+"'";
 	                    var actions = '<a href="javascript:getModule('+module_name+');" class="btn waves-effect waves-light bg-m t-c mob-btn"><i class="fa fa-pencil"></i> </a>';    	                   	
 	                   	var rowArray = [];
                         
 	                   	rowArray.push($.trim(val.module_name));
 	                   	rowArray.push($.trim(val.soft_delete_status_fk));
 	                   	rowArray.push($.trim(actions));   	                   	
 	                   	
 	                    table.row.add(rowArray).draw( true );
 					});
 	         		$(".page-loader-2").hide();
 				}else{
 					$(".page-loader-2").hide();
 				}
 				
 			},error: function (jqXHR, exception) {
 				$(".page-loader-2").hide();
 	         	getErrorMessage(jqXHR, exception);
 	     }});
     }
   	 
   	 function getModulesForFilter(){
    	$(".page-loader").show();
    	
    	var module_name = $("#module_name_filter").val();
     	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
     	if($.trim(module_name) == ''){
	       $("#module_name_filter option:not(:first)").remove();
	   	   var myParams = {module_name : module_name,soft_delete_status_fk : soft_delete_status_fk};
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getModulesForFilter",type:"POST",
               data: myParams, cache: false,async: false,
               success: function (data) {
            	   if (data.length > 0) {
                       $.each(data, function (i, val) {
                       		$("#module_name_filter").append('<option value="' + val.module_name +'">' + $.trim(val.module_name) +'</option>');
                       });
                   }
                   $('.searchable').select2();
                   $(".page-loader").hide();
               },error: function (jqXHR, exception) {
	   			      $(".page-loader").hide();
   	          	  getErrorMessage(jqXHR, exception);
   	     	  }
           });
     	}else{
     		$(".page-loader").hide();
     	}
     }
   	
   	 function getModuleStatusListForFilter(){
    	$(".page-loader").show();
    	
    	var module_name = $("#module_name_filter").val();
     	var soft_delete_status_fk = $("#soft_delete_status_fk").val();
     	if($.trim(soft_delete_status_fk) == ''){
	       $("#soft_delete_status_fk option:not(:first)").remove();
	   	   var myParams = {module_name : module_name,soft_delete_status_fk : soft_delete_status_fk};
           $.ajax({
               url: "<%=request.getContextPath()%>/ajax/getModuleStatusListForFilter",type:"POST",
               data: myParams, cache: false,async: false,
               success: function (data) {
                   if (data.length > 0) {
                       $.each(data, function (i, val) {
                       		$("#soft_delete_status_fk").append('<option value="' + val.soft_delete_status_fk +'">' + $.trim(val.soft_delete_status_fk) +'</option>');
                       });
                   }
                   $('.searchable').select2();
                   $(".page-loader").hide();
               },error: function (jqXHR, exception) {
	   			      $(".page-loader").hide();
   	          	  getErrorMessage(jqXHR, exception);
   	     	  }
           });
     	}else{
     		$(".page-loader").hide();
     	}
     }
   	 
   	 function getErrorMessage(jqXHR, exception) {
	    var msg = '';
	    if (jqXHR.status === 0) {
	        msg = 'Not connect.\n Verify Network.';
	    } else if (jqXHR.status == 404) {
	        msg = 'Requested page not found. [404]';
	    } else if (jqXHR.status == 500) {
	        msg = 'Internal Server Error [500].';
	    } else if (exception === 'parsererror') {
	        msg = 'Requested JSON parse failed.';
	    } else if (exception === 'timeout') {
	        msg = 'Time out error.';
	    } else if (exception === 'abort') {
	        msg = 'Ajax request aborted.';
	    } else {
	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
	    }
	    console.log(msg);
 	 }
			 	        	
    </script>

</body>

</html>