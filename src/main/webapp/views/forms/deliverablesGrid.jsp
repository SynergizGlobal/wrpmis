<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deliverables</title>
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

        td:last-child {
            white-space: inherit;
        }

        .last-column .btn+.btn {
            margin-left: 15px;
        }

        .input-field .searchable_label {
            font-size: 0.9rem;
        }
          .fw-300{
    	 	width:300px !important;
    	 	max-width:300px;
    	 }
        .page-loader {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}	
		 .page-loader-2 {
		    background: #332e2ec2!important;
		    position: fixed;
		    width: 100%;
		    height: 100%;
		    top: 0;
		    left: 0;
		    z-index: 1000;
		}			
		.preloader-wrapper{top: 45%!important;left:47%!important;}
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
                            <h6>Deliverables</h6>
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
                                    <a href="<%=request.getContextPath() %>/add-deliverables-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Deliverables</strong></a>
                                </div>
                            </div>
                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportDeliverables();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>
                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m1 hide-on-small-only"></div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Project</p>
                                 <select id="project_id_fk" name="project_id_fk" class="searchable" onchange="getDeliverablesList();">
                                    <option value="">Select</option>
                                    
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Work</p>
                                <select id="work_id_fk" name="work_id_fk" class="searchable" onchange="getDeliverablesList();">
                                    <option value="">Select</option>
                                    
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Contract</p>
                                <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getDeliverablesList();">
                                    <option value="">Select</option>
                                    
                                </select>
                            </div>
                            <div class="col s12 m2 input-field">
                                <p class="searchable_label">Status</p>
                                 <select id="status_fk" name="status_fk" class="searchable" onchange="getDeliverablesList();">
                                    <option value="">Select</option>
                                    
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
                                <table id="datatable-deliverables" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th> Project</th>
                                            <th class="fw-300"> Work</th>
                                            <th> Contract</th>
                                            <th> Deliverables Type</th>
                                            <th> Description</th>
                                            <th> Status</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- <tr>
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
    
     <form action="<%=request.getContextPath() %>/export-deliverables" name="exportDeliverablesForm" id="exportDeliverablesForm" target="_blank" method="post">	
         <input type="hidden" name="project_id_fk" id="exportProject_id_fk" />
         <input type="hidden" name="work_id_fk" id="exportWork_id_fk" />
         <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
         <input type="hidden" name="status_fk" id="exportStatus_fk" />
	</form>

     <script>
     $(document).ready(function () {
    	 $('select:not(.searchable)').formSelect();
	     $('.searchable').select2();
	 	var table = $('#datatable-deliverables').DataTable({
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
		getDeliverablesList();
        });
        
     function clearFilters() {
    	 $(".page-loader-2").show();
         $('#project_id_fk').val("");
         $('#work_id_fk').val("");
         $('#contract_id_fk').val("");
         $('#status_fk').val("");
         $('.searchable').select2();
         getDeliverablesList();
     }
     
     
     function getDeliverablesList(){
      	$(".page-loader-2").show();
      	var status_fk = $("#status_fk").val();
      	var project_id_fk = $("#project_id_fk").val();
      	var work_id_fk = $("#work_id_fk").val();
      	var contract_id_fk = $("#contract_id_fk").val();
       	getProjectFilterList();
    	getWorkFilterList();
    	getContarctFilterList();
       	getStatusFilterList();
      	table = $('#datatable-deliverables').DataTable();
  		 
  		table.destroy();
  		
  		$.fn.dataTable.moment('DD-MMM-YYYY');
  		table = $('#datatable-deliverables').DataTable({
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
  	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk};
  	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-deliverables-list",type:"POST",data:myParams,success : function(data){    				
  			if(data != null && data != '' && data.length > 0){    					
           		$.each(data,function(key,val){
           			var id = "'"+val.id+"'";
                      var actions = '<a href="javascript:void(0);"  onclick="getDeliverables('+id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
  /*                     			  +'<a onclick="deleteDeliverables('+id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
   */                   var rowArray = [];    	                 
					    var projectName = '';
					    if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
					    var workName = '';
					    if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
					    var contarctName = '';
					    if ($.trim(val.contract_name) != '') { contarctName = ' - ' + $.trim(val.contract_name) }
 					 
 					    rowArray.push($.trim(val.project_id_fk) + projectName);
 					 	rowArray.push($.trim(val.work_name) + workName);
 					 	rowArray.push($.trim(val.contract_id_fk) + contarctName);
 					 	rowArray.push($.trim(val.deliverable_type_fk));
 					 	rowArray.push($.trim(val.deliverable_description));
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
      	var project_id_fk = $("#project_id_fk").val();
      	var work_id_fk = $("#work_id_fk").val();
      	var contract_id_fk = $("#contract_id_fk").val();
      
          if ($.trim(status_fk) == "") {
          	$("#status_fk option:not(:first)").remove();
      	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk};
              $.ajax({
                  url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInDeliverables",
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
       	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk};
               $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/getProjectFilterListInDeliverables",
                   data: myParams, cache: false,
                   success: function (data) {
                       if (data.length > 0) {
                           $.each(data, function (i, val) {
                        	   var projectName = '';
                        	   if ($.trim(val.project_name) != '') { projectName = ' - ' + $.trim(val.project_name) }
   	                           $("#project_id_fk").append('<option value="' + val.project_id_fk + '">' + $.trim(val.project_id_fk)  + projectName +'</option>');
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
        
            if ($.trim(work_id_fk) == "") {
            	$("#work_id_fk option:not(:first)").remove();
        	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkFilterListInDeliverables",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	var workShortName = '';
                         	   if ($.trim(val.work_short_name) != '') { workShortName = ' - ' + $.trim(val.work_short_name) }
    	                           $("#work_id_fk").append('<option value="' + val.work_id_fk + '">' + $.trim(val.work_id_fk)  + workShortName +'</option>');
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
     
     function getContarctFilterList() {
     	$(".page-loader").show();
     	var status_fk = $("#status_fk").val();
     	var project_id_fk = $("#project_id_fk").val();
     	var work_id_fk = $("#work_id_fk").val();
     	var contract_id_fk = $("#contract_id_fk").val();
     
         if ($.trim(contract_id_fk) == "") {
         	$("#contract_id_fk option:not(:first)").remove();
     	 	var myParams = {project_id_fk : project_id_fk, work_id_fk : work_id_fk, contract_id_fk : contract_id_fk, status_fk : status_fk};
             $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/getContarctFilterListInDeliverables",
                 data: myParams, cache: false,
                 success: function (data) {
                     if (data.length > 0) {
                         $.each(data, function (i, val) {
                        	 var contractShortName = '';
                        	 if ($.trim(val.contract_short_name) != '') { contractShortName = ' - ' + $.trim(val.contract_short_name) } 	           
                        	 $("#contract_id_fk").append('<option value="' + val.contract_id_fk + '">' + $.trim(val.contract_id_fk)  + contractShortName +'</option>');
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
     
     
     function getDeliverables(id){
     	$("#id").val(id);
     	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-deliverables');
     	$('#getForm').submit();
     }
     function deletegetDeliverables(id){
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
             	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-deliverables');
     	    	$('#getForm').submit();
            }else {
                 swal("Cancelled", "Record is safe :)", "error");
             }
         });
     }
     
     function exportDeliverables(){
    	 var project_id_fk = $("#project_id_fk").val();
    	 var work_id_fk = $("#work_id_fk").val();
    	 var contract_id_fk = $("#contract_id_fk").val();
    	 var status_fk = $("#status_fk").val();
    	 $("#exportProject_id_fk").val(project_id_fk);
    	 $("#exportWork_id_fk").val(work_id_fk);
    	 $("#exportContract_id_fk").val(contract_id_fk);
    	 $("#exportStatus_fk").val(status_fk);
    	 $("#exportDeliverablesForm").submit();
 	}
 </script>


</body>
</html>