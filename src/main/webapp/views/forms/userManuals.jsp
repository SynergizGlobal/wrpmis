<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PMIS Manuals</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
   <!--  <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css" rel="stylesheet" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-form-template.css" />
	<link rel="stylesheet" media="screen and (max-device-width: 768px)"    href="/pmis/resources/css/mobile-grid-template.css" />
    <style>
   
        .input-field p.searchable_label {
            font-size: 0.85rem;
            margin-bottom:0;
            margin-top: -10px !important;
        }
        .select2-container--default .select2-selection--single{
        	background-color:transparent;
        }
        .select2-container.select2-container--default.select2-container--open{
        	z-index:1004;
        }
		.last-column{
			max-width:10rem;
		}		
        .last-column .btn+.btn {
            margin-left: 15px;
        }
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
         /* .fw-all{
         		width:12vw !important;
        		max-width:12vw;
         } */
         
         .fw-100{
        	width:100px !important;
        	max-width:100px;
        }
         .fw-150{
        	width:150px !important;
        	max-width:150px;
        }
		.fw-200{
        	width:200px !important;
        	max-width:200px;
        }
		.fw-250{
        	width:250px !important;
        	max-width:250px;
        } 
           @media only screen and (max-width: 769px){ 
			
			.dataTables_scrollBody tbody tr td:last-of-type,
			.no-sort{
				padding:3px !important;
				max-width: 45px;
			}
			.mob-btn{
				padding:4px 12px;
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
	        .fw-250{
	        	width:35% !important;
	        	max-width:35%;
	        }
	        .break{
	        	text-align:center;
	        	width:26vw;
    			min-width:26vw;
			    margin-left: auto;
			    margin-right: auto;
			    
	        }
	        .break a{
			   word-break: break-word;
			   height: auto;
			   white-space: normal;
			   line-height: inherit;
	        }
	        .mob-center{
	        	text-align:center;
	        }
		}
		
		.my-error-class {
   			 color:red;
		}
		.my-valid-class {
   			 color:green;
		}
		
		/* new code for modal and its contents starts  */
		.row.no-mar{
			margin-bottom:0;
		}
		.radioClass,
		#confirmBox input[type="radio"],
		#cvDocBox input[type="radio"] {
			opacity:2;
			position: inherit;
		}		
		#confirmBox ,#cvDocBox,#noAtrDiv {
			text-align:center;
			font-size: 1.25rem;
		}		
		.modal-content label,
		.modal-content [type="checkbox"]+span:not(.lever) {
		    font-size: 1.25rem; 
		    color: #9e9e9e;
		}
		.modal-content [type="radio"]:not(:checked)+span, [type="radio"]:checked+span{
			padding-left:25px;
		}
		/* new code for modal and its contents ends  */
		.btn-holder .btn+.btn{
			margin-left:20px;
		}
		.btn-holder>.btn{
			margin-top:.7rem;
		}

		.no-sort.sorting_asc:before,
		.no-sort.sorting_asc:after{
		    opacity:0 !important;
		    content:'' !important;
		}
		
		.modal-content label, .modal-content [type="checkbox"]+span:not(.lever) {
		    font-size: 1rem;
		    color: #9e9e9e;
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
                            <h6>PMIS Manuals</h6>
                        </div>
                    </span>
                    <div class="">
                    	<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
                        <div class="row plr-1">
                            <div class="col s12 m4 offset-m4 center-align" style="margin-bottom:20px;">
                                 <a href="javascript:addManual();" class="btn waves-effect waves-light bg-s t-c">
                                     <strong><i class="fa fa-plus-circle"></i> Add Manual</strong></a>
                            </div>
                        </div>
                        <div class="row no-mar">
                        	<div class="col m3 l4"></div>
                            <div class="col s12 m3 l2 input-field">
                                <p class="searchable_label">Status</p>
                               <select id="search_status" class="searchable" name="status" onchange="getManualsList();">
                                    <option value="" >Select</option>
                                    <c:forEach var="obj" items="${statusList }">
                                    	<option value="${obj.status }" >${obj.status }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col s12 m3 l2 mob-center">
                                <button class="btn bg-m waves-effect waves-light t-c clear-filters"
                                    style="margin-top: 8px;width: 100%;" onclick="clearFilters()">Clear
                                    Filters</button>
                            </div>
                            <div class="col m3 l4"></div>
                        </div>

                        <div class="row">
                            <div class="col m12 s12">
                                <table id="data-table-manuals" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th class="no-sort fw-250">Title</th>
                                            <th class="fw-250">Attachment</th>
                                            <th>Status</th>
                                            <th>Created Date</th>
                                            <th>Created By</th>
                                            <th>Priority</th>
                                            <th class="no-sort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="addManualModal" class="modal">
		 <form action="<%=request.getContextPath() %>/add-user-manual" id="addForm" name="addForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
            <div class="modal-content">
                <h6 class="modal-header ">Add Manual <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m2 hide-on-small"></div>
                    <div class="col m8 s12">
                        <div class="row no-mar">
                            <div class="input-field col s12 m12">
                                <input name="title" type="text" class="validate" required="required">
                                <label for="title">Title</label>
                            </div>
                        </div>
                        <div class="row no-mar">
	                        <div class="input-field col s12 m12">
	                            <div class="file-field input-field" >
	                            	<div class="btn bg-m t-c">
			                             <span>Attach File</span>
	                                	<input type="file" id="attachment" name="attachment"  class="validate" accept="*" required="required">
	                               </div> 
	                                <div class="file-path-wrapper">
			                            <input class="file-path validate" type="text">
			                        </div>  
	                            </div>
	                        </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Status</p> 
                                <select class="searchable" name="status" id="add_status" required="required">
                                    <option value="" >Select</option>
                                    <c:forEach var="obj" items="${statusList }">
                                    	<option value="${obj.status }" >${obj.status }</option>
                                    </c:forEach>
                                </select>
                                <!-- <label for="add_status">Status</label> -->
                            </div>
                            <div class="input-field col s12 m6">
                                <input name="priority" type="number" class="validate" required="required">
                                <label for="title">Priority</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="submit" style="min-width:90px;" class="btn waves-effect waves-light bg-m">Add </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="button" class="btn waves-effect waves-light bg-s modal-action modal-close" >Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col m2 hide-on-small"></div>
                </div>
            </div>
        </form>
    </div>
    
    <div id="editManualModal" class="modal">
		 <form action="<%=request.getContextPath() %>/update-user-manual" id="editForm" name="editForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
            <div class="modal-content">
                <h6 class="modal-header ">Update Manual <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m8 s12 offset-m2">
                    	<input id="manual_id" name="manual_id" type="hidden">
                        <div class="row no-mar">
                            <div class="input-field col s12 m12">
                                <input id="update_title" name="title" type="text" class="validate" required="required">
                                <label for="update_title">Title</label>
                            </div>
                        </div>
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <p class="searchable_label">Status</p> 
                                <select id="update_status" class="searchable" name="status" required="required">
                                    <option value="" >Select</option>
                                    <c:forEach var="obj" items="${statusList }">
                                    	<option value="${obj.status }" >${obj.status }</option>
                                    </c:forEach>
                                </select>
                               <!--  <label for="update_status">Status</label> -->
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="update_priority" name="priority" type="number" class="validate" required="required">
                                <label for="update_priority">Priority</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="submit" class="btn waves-effect waves-light bg-m">Update </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="button" class="btn waves-effect waves-light bg-s modal-action modal-close " >Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
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

    <!-- footer  -->
    <jsp:include page="../layout/footer.jsp"></jsp:include>
    
    <form action="<%=request.getContextPath() %>/delete-user-manual" id="deleteForm" name="deleteForm" method="post" >
    	<input type="hidden" name="manual_id" id="delete_manual_id" />
    	<input type="hidden" name="status" value="Inactive" />
    </form>

    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/moment-v2.8.4.min.js"></script> 
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
	
    <script>
    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
        	$('.modal').modal({ dismissible: false });
            $('.close-message').delay(5000).fadeOut('slow');
            
         	getManualsList(); 
        });

        function clearFilters() {
             $('#search_status').val("");
             $('.searchable').select2();
             getManualsList();
        }
        
        function addManual(){
        	$("#addManualModal").modal("open");
        }
        
        function getManual(manual_id,title,attachment,status,priority){         	
        	$("#editManualModal").modal("open");        	
        	$("#manual_id").val(manual_id);
        	$("#update_status").val(status);
        	$('#update_status').select2();

         	$("#update_title").val(title).focus();
         	$("#update_priority").val(priority).focus();
        }
        
        function deleteManual(manual_id){
        	swal({
                title: "Are you sure?",
                text: "You want to delete the manual!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, delete it!",
                cancelButtonText: "No, cancel it!",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                	$("#delete_manual_id").val(manual_id);
                	$("#deleteForm").submit();
               }else {
                    swal("Cancelled", "Record is safe :)", "error");
                }
            });
        }
        
        function getManualsList(){
        	$(".page-loader-2").show();
        	
        	var status = $("#search_status").val();

        	table = $('#data-table-manuals').DataTable();
  	   		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#data-table-manuals').DataTable({
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
                        targets: [6],
                        className: 'last-column'
                    },
                    {
                        targets: [0,1],
                        className: 'fw-250'
                    },
                    {
                        targets: [2,3,4,5],
                        className: 'hideCOl'
                    },
                    { orderable: false, 'aTargets': ['no-sort'] }
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                "ordering":false,
                 "sScrollXInner": "100%",
                 "bScrollCollapse": true,
                initComplete: function () {
                    $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
                    var input = $('.dataTables_filter input')
					.unbind(), self = this.api(), $searchButton = $(
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
            }).rows().remove().draw();
    		
    		table.state.clear();	
    	 	var myParams = {status : status};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/getUserManuals",type:"POST",data:myParams,success : function(data){    
    	 		
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var manual_id = "'"+val.manual_id+"'";
             			var title = "'"+val.title+"'";
             			var file_name = "'"+val.file_name+"'";
             			var status = "'"+val.status+"'";
             			var priority = "'"+val.priority+"'";
             			
                        var actions = '<a href="javascript:getManual('+manual_id+','+title+','+file_name+','+status+','+priority+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-pencil"></i></a>';
                        
                        if(val.status != 'Inactive'){
                        	actions = actions +'<a href="javascript:deleteManual('+manual_id+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-trash"></i></a>';
                        }	  
                        
                        var filePath = "-";
                        
                        if($.trim(val.file_name) != ''){
                        	filePath = '<a href="<%=CommonConstants2.PMIS_MANUALS%>'+ val.manual_id +'/'+val.file_name+'" target="_blank" >'+val.file_name + '</a>';
                        }
                        
                        var rowArray = [];    	                 
                        
                        rowArray.push(val.title);
                        rowArray.push(filePath); 
                       	rowArray.push(val.status);
                       	rowArray.push(val.created_date);
                       	rowArray.push(val.created_by);
                       	rowArray.push(val.priority);
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
        
    </script>
</body>

</html>