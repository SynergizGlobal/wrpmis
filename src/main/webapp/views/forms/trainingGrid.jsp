<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Training</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
          
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/training.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
      <style>
        p a {
            color: blue;
        }
        .mdl-data-table td{
        	white-space:pre-line;
        	word-break:break-word;
        }
    
         .input-field .searchable_label{
            font-size: 0.85rem;
        }
        .fw-370{
        	width:370px !important;
        	max-width:370px;
        }
         .fw-90{
        	width:90px !important;
        	max-width:90px;
        }
       
        tbody tr td:last-of-type,thead tr th:last-of-type{
        	white-space:inherit;
        	text-align:center !important;
        }
        tbody tr td:last-of-type a+a{
        	margin-left:20px;
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
                            <h6> Training</h6>
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
                        <div class="row plr-1 center-align">
                            <div class="col s12 m4">
                                 <!-- <div class="m-1 l-align">
                                    <a href="javascript:void(0);" onclick="openUploadTrainingModal();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="/pmis/Training_Template.xlsx">here</a> for the template</p>
                                </div>  -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-training-form"  class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Training</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                 <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportTraining();" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div> 
                            </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m2 hide-on-small-only"></div>
                            <div class="col m8 s12 ">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col s12 m3 input-field">
                                       <p class="searchable_label">Type</p>
                                        <select class="searchable" name="training_type_fk" id="training_type_fk" onchange="getTraningList();" >
                                            <option value="" >Select Type</option>
                                           
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                       <p class="searchable_label">Category</p>
                                        <select class="searchable" name="training_category_fk" id="training_category_fk" onchange="getTraningList();">
                                            <option value="" >Select Category</option>
                                           
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                       <p class="searchable_label">Status</p>
                                        <select class="searchable" name="status_fk" id="status_fk" onchange="getTraningList();">
                                            <option value="" >Select Status</option>
                                           
                                        </select>
                                    </div>
                                    <div class="col s12 m3 input-field">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters" onclick="clearFilter();"
                                            style="margin-top: 6px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m2 hide-on-small-only"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="datatable-training" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Type &nbsp;</th>
                                            <th>Category</th>
                                            <th class="fw-370">Title </th>
                                            <th>Faculty</th>
                                            <th>Start Date</th>
                                            <th>End Date</th>
                                            <th>Hours</th>
                                            <th>Status  &nbsp; &nbsp;</th>
                                            <th class="fw-90">Nominated </th>
                                            <th class="fw-90">Attended </th>
                                            <th class="nosort">Action</th>
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
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="training_new.html"
                                                    class="btn waves-effect waves-light bg-m t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
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
      <!-- update popup starts -->
    <div id="upload_template" class="modal">
        <div class="modal-content headbg">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Upload Users</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/upload-training" id="trainingUploadForm" name="trainingUploadForm" method="post" enctype="multipart/form-data">
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="file-field input-field">
                                        <div class="btn bg-m">
                                            <span>Attachment</span>
                                            <input type="file" id="trainingFile" name="trainingFile" required="required">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" onclick="trainingFileSubmit();" class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">Update</button>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;" onclick="closeUploadTrainingModal();">Cancel</button>
                            </div>
                        </div>
                    </div>
                </form>
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
	      </div><div class="gap-patch">
	        <div class="circle"></div>
	      </div><div class="circle-clipper right">
	        <div class="circle"></div>
	      </div>
	    </div>
	  </div>
	</div> 


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
    	<input type="hidden" name="training_id" id="training_id" />
    </form>
    
     <form action="<%=request.getContextPath() %>/export-training" name="exportTrainingForm" id="exportTrainingForm" target="_blank" method="post">	
         <input type="hidden" name="training_type_fk" id="exportTraining_type_fk" />
         <input type="hidden" name="training_category_fk" id="exportTraining_category_fk" />
         <input type="hidden" name="status_fk" id="exportStatus_fk" />
	 </form>

    <script>
	    function  openUploadTrainingModal() {
	  		$("#trainingFile").val('');
	      	$("#upload_template").modal();
	      	$("#upload_template").modal('open');
	  	}
	
	  	function  closeUploadTrainingModal() {
	  		$("#trainingFile").val('');
	      	$("#upload_template").modal('close');
	  	}
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('.notification.dropdown-trigger').dropdown({
                coverTrigger: false,
                closeOnClick: false,
            });
            $('#datatable-training').DataTable({
            	"order": [],
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric',
                        targets: 'no-sort', orderable: false,
                    },
                    { "width": "20px", "targets": [11] },
                ], "scrollCollapse": true,
                fixedHeader: true,
                "sScrollY": 400,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                }
            });
            getTraningList();
        });
        
        function clearFilter(){
        	$("#training_type_fk").val("");
        	$("#training_category_fk").val("");
        	$("#status_fk").val("");
        	$('.searchable').select2();
        	getTraningList();
        }
        
        function trainingFileSubmit(){
        	$(".page-loader").show();
        	$("#upload_template").modal();
        	$("#trainingUploadForm").submit();
        }
        function getTraningList(){
        	$(".page-loader-2").show();
        	var training_type_fk = $("#training_type_fk").val();
        	var training_category_fk = $("#training_category_fk").val();
        	var status_fk = $("#status_fk").val();
        	getTrainingTypesFilterList();
         	getTrainingCategorysFilterList();
         	getStatusFilterList();
        	table = $('#datatable-training').DataTable();
    		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#datatable-training').DataTable({
    			"order": [],
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
                    { orderable: false, 'aTargets': ['nosort'] },
                    { "width": "20px", "targets": [11] },
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
    	 	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/get-training",type:"POST",data:myParams,success : function(data){    				
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var training_id = "'"+val.training_id+"'";
                        var actions = '<a href="javascript:void(0);"  onclick="getTraining('+training_id+');" class="btn waves-effect waves-light bg-m t-c"><i class="fa fa-pencil"></i></a>'
                        			 // +'<a href="javascript:void(0);" onclick="getTrainingDetails('+training_id+');" class="btn waves-effect waves-light bg-s t-c"><i class="fa fa-download"></i></a>'
    /*                     			  +'<a onclick="deleteBudget('+budget_id+');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>'
     */                   	var rowArray = [];    	                                       	
                                          
                       	rowArray.push($.trim(val.training_id));
                       	rowArray.push($.trim(val.training_type_fk));
                       	rowArray.push($.trim(val.training_category_fk));
                       	rowArray.push($.trim(val.title));
                       	rowArray.push($.trim(val.faculty_name));
                       	rowArray.push($.trim(val.start_time));
                       	rowArray.push($.trim(val.end_time));
                       	rowArray.push($.trim(val.hours));
                       	rowArray.push($.trim(val.status_fk));
                       	rowArray.push($.trim(val.nominated));
                       	rowArray.push($.trim(val.attended));
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
        
        
        
       
     	 function getTrainingTypesFilterList() {
         	$(".page-loader").show();
         	var training_type_fk = $("#training_type_fk").val();
        	var training_category_fk = $("#training_category_fk").val();
        	var status_fk = $("#status_fk").val();
             if ($.trim(training_type_fk) == "") {
             	$("#training_type_fk option:not(:first)").remove();
             	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk};
                 $.ajax({
                     url: "<%=request.getContextPath()%>/ajax/getTrainingTypesFilterListInTraining",
                     data: myParams, cache: false,
                     success: function (data) {
                         if (data.length > 0) {
                             $.each(data, function (i, val) {
                             	 
     	                           $("#training_type_fk").append('<option value="' + val.training_type_fk + '">' + $.trim(val.training_type_fk) +'</option>');
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
     	 
     	 function getTrainingCategorysFilterList() {
          	$(".page-loader").show();
          	var training_type_fk = $("#training_type_fk").val();
         	var training_category_fk = $("#training_category_fk").val();
         	var status_fk = $("#status_fk").val();
              if ($.trim(training_category_fk) == "") {
              	$("#training_category_fk option:not(:first)").remove();
              	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk};
                  $.ajax({
                      url: "<%=request.getContextPath()%>/ajax/getTrainingCategorysFilterListInTraining",
                      data: myParams, cache: false,
                      success: function (data) {
                          if (data.length > 0) {
                              $.each(data, function (i, val) {
                              	
      	                           $("#training_category_fk").append('<option value="' + val.training_category_fk + '">' + $.trim(val.training_category_fk)  + '</option>');
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
     	 
     	 function getStatusFilterList() {
           	$(".page-loader").show();
           	var training_type_fk = $("#training_type_fk").val();
          	var training_category_fk = $("#training_category_fk").val();
          	var status_fk = $("#status_fk").val();
               if ($.trim(status_fk) == "") {
               	$("#status_fk option:not(:first)").remove();
               	var myParams = {training_type_fk : training_type_fk, training_category_fk : training_category_fk, status_fk : status_fk};
                   $.ajax({
                       url: "<%=request.getContextPath()%>/ajax/getStatusFilterListInTraining",
                       data: myParams, cache: false,
                       success: function (data) {
                           if (data.length > 0) {
                               $.each(data, function (i, val) {
                               	
       	                           $("#status_fk").append('<option value="' + val.status_fk + '">' + $.trim(val.status_fk)  + '</option>');
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
     	 
         function getTraining(training_id){
         	$("#training_id").val(training_id);
         	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-training');
         	$('#getForm').submit();
         }
         
         function getTrainingDetails(training_id){
          	$("#training_id").val(training_id);
          	$('#getForm').attr('action', '<%=request.getContextPath()%>/export-training-details');
          	$('#getForm').submit();
          }
         function deleteTraining(training_id){
         	$("#training_id").val(training_id);
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
                 	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-training');
         	    	$('#getForm').submit();
                }else {
                     swal("Cancelled", "Record is safe :)", "error");
                 }
             });
         }
         
         function exportTraining(){
        	 var training_type_fk = $("#training_type_fk").val();
             var training_category_fk = $("#training_category_fk").val();
        	 var status_fk = $("#status_fk").val();
        	 $("#exportTraining_type_fk").val(training_type_fk);
        	 $("#exportTraining_category_fk").val(training_category_fk);
        	 $("#exportStatus_fk").val(status_fk);
        	 $("#exportTrainingForm").submit();
     	}
         
    </script>

</body>

</html>