<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Gathering</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/la.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <style>
        p a {
            color: blue;
            text-decoration: none;
        }

        td,th{
        	box-sizing: content-box !important;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
        }
          .fw-350{
    	 	width:350px !important;
    	 	max-width:350px;
    	 }
       
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Data Gathering</h6>
                        </div>
                    </span>
                    <div class="">
                    <c:if test="${not empty success }">
					        <div class="center-align m-1 close-message">	
							   ${success}
							</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">
							   ${error}
							</div>
						</c:if>
                        <div class="row plr-1">
                            <div class="col s12 m4">
                                <!-- <div class="m-1 l-align">
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem">Click <a href="#"> here </a>for the template</p>
                                </div> -->
                            </div>
                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-data-gathering-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Data Gathering</strong></a>
                                </div>
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportDataGatherings();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                             <div class="col s12 m2 input-field">
                                <p class="searchable_label">Project</p>
                                <select id="project_id_fk" name="project_id_fk" class="searchable" onchange="getDataGatheringList();">
                                    <option value="">Select</option>                                    
                                </select>
                            </div>
                             <div class="col s12 m2 input-field">
                                <p class="searchable_label">Work</p>
                                <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="getDataGatheringList();">
                                    <option value=""  >Select</option>                                    
                                </select>
                            </div>
                             <div class="col s12 m2 input-field">
                                <p class="searchable_label">Contract</p>
                                <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getDataGatheringList();">
                                    <option value=""  >Select</option>                                    
                                </select>
                            </div>
                            <!-- <div class="col s12 m2 input-field">
                                <p class="searchable_label">Priority</p>
                                <select id="project_priority_fk" name="project_priority_fk" class="searchable" onchange="getDataGatheringList();">
                                    <option value=""  >Select</option>                                    
                                </select>
                            </div> -->
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Status</p>
                                <select id="status_fk" name="status_fk" class="searchable" onchange="getDataGatheringList();">
                                    <option value=""  >Select</option>                                    
                                </select>
                            </div>
                            <div class="col s12 m2">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 20px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                            <div class="col m1 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-data-gathering" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Contract</th>
                                            <th class="fw-350"> Data Gathering Work</th>
                                            <!-- <th> Target Date</th>
                                            <th> Start Date</th> -->
                                            <th> Finish Date</th>
                                            <th> Status</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <!--  <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="#"
                                                    class="btn waves-effect waves-light bg-m t-c"><i
                                                        class="fa fa-pencil"></i></a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c"><i
                                                        class="fa fa-trash"></i></a>
                                            </td>
                                        </tr> -->
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- Page Loader starts-->
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
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 
<!-- Page Loader ends-->


        <!-- footer included -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
    <script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
     <form name="getForm" id="getForm" method="post">
    	<input type="hidden" name="id" id="id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-data-gathering" name="exportDataGatheringForm" id="exportDataGatheringForm" target="_blank" method="post">	
        
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
         <input type="hidden" name="status_fk" id="exportStatus_fk" />
	</form>

     <script>
     $(document).ready(function () {
    	 $('select:not(.searchable)').formSelect();
	     $('.searchable').select2();
	 	var table = $('#datatable-data-gathering').DataTable({
			"bStateSave": true,
			fixedHeader: true,
	      "fnStateSave": function (oSettings, oData) {
	          localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
	      },
	      "fnStateLoad": function (oSettings) {
	          return JSON.parse(localStorage.getItem('MRVCDataTables'));
	      },
	      columnDefs: [
	          {
	              targets: [0, 1, 2],
	              className: 'mdl-data-table__cell--non-numeric'
	          },
	          { orderable: false, 'aTargets': ['nosort'] }
	      ],
	      // "ScrollX": true,
	      "scrollCollapse": true,
	      //"sScrollY": 400,
	      "sScrollX": "100%",
	          "sScrollXInner": "100%",
	          "bScrollCollapse": true,
	      initComplete: function () {
	          $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	      }
	  });
		table.state.clear(); 
		
	
		$('.close-message').delay(3000).fadeOut('slow');
		getDataGatheringList();
        });
        
     function clearFilters() {
         $('#status_fk').val("");
         $('#project_id_fk').val("");
         $('#work_id_fk').val("");
         $('#contract_id_fk').val("");
         $('.searchable').select2();
         getDataGatheringList();
     }
     
     function getDataGatheringList(){
     	$(".page-loader-2").show();
     	var status_fk = $("#status_fk").val();
     	var project_id_fk = $("#project_id_fk").val();
     	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
     	/*var project_priority_fk = $("#project_priority_fk").val();
      	getProjectPriorityFilterList(); */
      	
      	//getProjectFilterList();
      	//getWorkFilterList();
      	//getContractFilterList();
      	getStatusFilterList();
     	table = $('#datatable-data-gathering').DataTable();
 		 
 		table.destroy();
 		
 		$.fn.dataTable.moment('DD-MMM-YYYY');
 		table = $('#datatable-data-gathering').DataTable({
     		"bStateSave": true,
     		fixedHeader: true,
             "fnStateSave": function (oSettings, oData) {
                 localStorage.setItem('MRVCDataTables', JSON.stringify(oData));
             },
             "fnStateLoad": function (oSettings) {
                 return JSON.parse(localStorage.getItem('MRVCDataTables'));
             },
             columnDefs: [
                 {
                     targets: [0, 1, 2],
                     className: 'mdl-data-table__cell--non-numeric'
                 },
                 { orderable: false, 'aTargets': ['nosort'] }
             ],
             // "ScrollX": true,
             "sScrollX": "100%",
              "sScrollXInner": "100%",
              "bScrollCollapse": true,
             initComplete: function () {
                 $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
             }
         }).rows().remove().draw();
 		
 		table.state.clear();		
 	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk:work_id_fk,contract_id_fk:contract_id_fk};
 	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-data-gathering-list",type:"POST",data:myParams,success : function(data){    				
 			if(data != null && data != '' && data.length > 0){    					
          		$.each(data,function(key,val){
          			var id = "'"+val.id+"'";
                     var actions = '<a href="javascript:void(0);"  onclick="getDataGathering('+id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
 /*                     			  +'<a onclick="deleteDataGathering('+id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
  */                   	var rowArray = [];    	                 
	
						//var workName = '';
					 // if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
					 
					    rowArray.push($.trim(val.project_priority_fk));
					 	rowArray.push($.trim(val.work_name));
					 	rowArray.push($.trim(val.target_date));
					 	rowArray.push($.trim(val.start_date));
					 	rowArray.push($.trim(val.finish_date));
					 	rowArray.push($.trim(val.status_fk));
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
     
     function getStatusFilterList() {
     	$(".page-loader").show();
     	var status_fk = $("#status_fk").val();
     	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
     	var project_id_fk = $("#project_id_fk").val();
     
         if ($.trim(status_fk) == "") {
         	$("#status_fk option:not(:first)").remove();
     	 	var myParams = {project_priority_fk : project_priority_fk, status_fk : status_fk};
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInDataGathering",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
 	                           $("#status_fk").append('<option value="' + val.status_fk + '">' + $.trim(val.status_fk)  +'</option>');
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
     function getProjectFilterList() {
      	$(".page-loader").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();     	
      	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(project_id_fk) == "") {
          	$("#project_id_fk option:not(:first)").remove();
      	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk:work_id_fk,contract_id_fk:contract_id_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getProjectsFilterListInDataGathering",
                  data: myParams, cache: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
  	                           $("#project_id_fk").append('<option value="' + val.project_id_fk + '">' + $.trim(val.project_id_fk)  +'</option>');
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
     function getWorkFilterList() {
      	$(".page-loader").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();     	
      	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(status_fk) == "") {
          	$("#work_id_fk option:not(:first)").remove();
      	 	var myParams = {project_id_fk : project_id_fk, status_fk : status_fk,work_id_fk:work_id_fk,contract_id_fk:contract_id_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getWorksFilterListInDataGathering",
                  data: myParams, cache: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
  	                           $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk)  +'</option>');
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
     function getContractFilterList() {
      	$(".page-loader").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();     	
      	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(status_fk) == "") {
          	$("#contract_id_fk option:not(:first)").remove();
      	 	var myParams = {contract_id_fk : contract_id_fk, status_fk : status_fk,project_id_fk:project_id_fk,work_id_fk:work_id_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInDataGathering",
                  data: myParams, cache: false,
                  success: function (data) {
                      if (data.length > 0) {
                          $.each(data, function (i, val) {
  	                           $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk)  +'</option>');
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
     function getProjectPriorityFilterList() {
     	$(".page-loader").show();
     	var status_fk = $("#status_fk").val();
     	var project_priority_fk = $("#project_priority_fk").val();
     
         if ($.trim(project_priority_fk) == "") {
         	$("#project_priority_fk option:not(:first)").remove();
        	var myParams = {project_priority_fk : project_priority_fk, status_fk : status_fk};       
        	$.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getProjectPriorityFilterListInDataGathering",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
 	                           $("#project_priority_fk").append('<option value="' + val.project_priority_fk + '">' + $.trim(val.project_priority_fk)  + '</option>');
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
     
     
     function getDataGathering(id){
     	$("#id").val(id);
     	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-data-gathering');
     	$('#getForm').submit();
     }
     function deleteDataGathering(id){
     	$("#id").val(id);
     	showCancelMessage();
     }
     	
     
   	//This function is used to get error message for all ajax calls
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
                // swal("Deleted!", "Record has been deleted", "success");
             	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-data-gathering');
     	    	$('#getForm').submit();
            }else {
                 swal("Cancelled", "Record is safe :)", "error");
             }
         });
     }
     
     function exportDataGatherings(){
    	 //var project_priority_fk = $("#project_priority_fk").val();
    	 var project_id_fk = $("#project_id_fk").val();
    	 var work_id_fk = $("#work_id_fk").val();
    	 var contract_id_fk = $("#contract_id_fk").val();
    	 var status_fk = $("#status_fk").val();
    	 $("#exportProject_id_fk").val(project_id_fk);
    	$("#exportStatus_fk").val(status_fk);
    	$("#exportWork_id_fk").val(work_id_fk);
    	$("#exportContract_id_fk").val(contract_id_fk);
    	 $("#exportDataGatheringForm").submit();
 	}
     
 </script>


</body>
</html>