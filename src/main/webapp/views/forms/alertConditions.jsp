<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alert Conditions</title>
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
        
        .input-field p.condition_label {
		    font-size: 0.85rem;
		    margin-bottom: 0;
		    margin-top: 10px !important;
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
        	width:25% !important;
        	max-width:25%;
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
	        	width:25% !important;
	        	max-width:25%;
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
.m-n1 {
        margin: -2rem auto 0;
    }

    @media only screen and (max-width: 767px) {
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
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6>Alert Conditions</h6>
                        </div>
                    </span>
                    <div class="">
                    	<c:if test="${not empty success }">
							<div class="center-align m-1 close-message">${success}</div>
						</c:if>
						<c:if test="${not empty error }">
							<div class="center-align m-1 close-message">${error}</div>
						</c:if>
                        <div class="row no-mar">
                        	<div class="col m3 l4"></div>
                            <div class="col s12 m3 l2 input-field">
                                <p class="searchable_label">Alert Type</p>
                               <select id="alert_type_fk" class="searchable" name="alert_type_fk" onchange="getAlertConditions();">
                                    <option value="" >Select</option>
                                    <c:forEach var="obj" items="${alertTypes }">
                                    	<option value="${obj.alert_type_fk }" >${obj.alert_type_fk }</option>
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
                                <table id="data-table-alert-conditions" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Alert Type</th>
                                            <!-- <th>Alert Level</th> -->
                                            <th>First Condition</th>
                                            <th>First Condition Values</th>
                                            <th>Second Condition</th>
                                            <th>Second Condition Values</th>
                                            <th>Action</th>
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
    
    <div id="editModal" class="modal">
		 <form id="updateForm" name="updateForm" class="form-horizontal" role="form">
            <div class="modal-content">
                <h6 class="modal-header ">Update <span id="alert_type_text"></span> Conditions <span class="right modal-action modal-close"><span
                            class="material-icons">close</span></span></h6>
                <div class="row">
                    <div class="col m12 s12">
                    	<!-- <input id="alert_condition_id" name="alert_condition_id" type="hidden">
                        
                        <div class="row no-mar">
                            <div class="input-field col s12 m6">
                                <p class="condition_label" id="first_condition"></p> 
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="number" id="first_condition_value" name="first_condition_value" class="validate" required="required">
                                <label for="first_condition_value">First Condition Value</label>
                            </div>
                        </div>
                        <div class="row no-mar" id="second_condition_div">
                            <div class="input-field col s12 m6">
                                <p class="condition_label" id="second_condition"></p> 
                            </div>
                            <div class="input-field col s12 m6">
                                <input type="number" id="second_condition_value" name="second_condition_value" class="validate" required="required">
                                <label for="second_condition_value">Second Condition Value</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="button" onclick="updateAlertCondition();" class="btn waves-effect waves-light bg-m">Update </button>
                                </div>
                            </div>
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="button" class="btn waves-effect waves-light bg-s modal-action modal-close " >Cancel</button>
                                </div>
                            </div>
                        </div> -->
                        
                        <table id="data-table-alert-type-conditions" class="mdl-data-table">
                            <thead>
                                <tr>
                                    <th>Alert Level</th>
                                    <th>First Condition</th>
                                    <th>First Condition Value</th>
                                    <th>Second Condition</th>
                                    <th>Second Condition Value</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="center-align m-1">
                                    <button type="button" onclick="updateAlertCondition();" class="btn waves-effect waves-light bg-m">Update </button>
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
            
         	getAlertConditions();
        });

        function clearFilters() {
             $('#alert_type_fk').val("");
             $('.searchable').select2();
             getAlertConditions();
        }
        
        function getAlertConditions(){
        	$(".page-loader-2").show();
        	
        	var alert_type_fk = $("#alert_type_fk").val();

        	table = $('#data-table-alert-conditions').DataTable();
  	   		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#data-table-alert-conditions').DataTable({
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
                        targets: [5],
                        className: 'last-column'
                    },
                    {
                        targets: [2,4],
                        className: 'fw-250'
                    },
                    { orderable: false, 'aTargets': ['no-sort'] }
                ],
                // "ScrollX": true,
                "sScrollX": "100%",
                "ordering":false,
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                "order":[],
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
            }).rows().remove().draw();
    		
    		table.state.clear();	
    	 	var myParams = {alert_type_fk : alert_type_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/getAlertConditions",type:"POST",data:myParams,success : function(data){    
    	 		
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			var alert_condition_id = "'"+val.alert_condition_id+"'";
             			var alert_type_fk = "'"+val.alert_type_fk+"'";
             			/* var alert_level_fk = "'"+val.alert_level_fk+"'";
             			var first_condition = "'"+val.first_condition+"'";
             			var first_condition_value = "'"+val.first_condition_value+"'";
             			var second_condition = "'"+val.second_condition+"'";
             			var second_condition_value = "'"+val.second_condition_value+"'"; */
             			
                        var actions = '<a href="javascript:getAlertCondition('+alert_type_fk+');" class="btn waves-effect waves-light bg-m t-c mobile-btn"><i class="fa fa-pencil"></i></a>';
                        
                        
                        var rowArray = [];    	                 
                        
                        rowArray.push(val.alert_type_fk);
                        /* rowArray.push(val.alert_level_fk);  */
                       	rowArray.push(val.first_condition);
                       	rowArray.push(val.first_condition_value);
                       	rowArray.push(val.second_condition);
                       	rowArray.push(val.second_condition_value);
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
        
        function getAlertCondition(alert_type_fk){         	
        	$("#editModal").modal("open");        
        	
        	$("#alert_type_text").html(alert_type_fk);    
        	
			$(".page-loader-2").show();

        	table = $('#data-table-alert-type-conditions').DataTable();
  	   		 
    		table.destroy();
    		
    		$.fn.dataTable.moment('DD-MMM-YYYY');
    		table = $('#data-table-alert-type-conditions').DataTable({
        		fixedHeader: true,
                columnDefs: [
                    {
                        targets: [1,3],
                        className: 'fw-250'
                    },
                    { orderable: false, 'aTargets': ['no-sort'] }
                ],
                //"ScrollX": true,
                "sScrollX": "100%",
                "ordering":false,
                "sScrollXInner": "100%",
                "bScrollCollapse": true,
                "searching": false,
                "paging": false,
                "info": false,
                "order":[]
            }).rows().remove().draw();
    		
    		table.state.clear();	
    	 	var myParams = {alert_type_fk : alert_type_fk};
    	 	$.ajax({url : "<%=request.getContextPath()%>/ajax/getAlertCondition",type:"POST",data:myParams,
    	 		success : function(data){ 
    			if(data != null && data != '' && data.length > 0){    					
             		$.each(data,function(key,val){
             			//var alert_condition_id = "'"+val.alert_condition_id+"'";
             			var alert_type_fk = "'"+val.alert_type_fk+"'";
             			/* var alert_level_fk = "'"+val.alert_level_fk+"'";
             			var first_condition = "'"+val.first_condition+"'";
             			var first_condition_value = "'"+val.first_condition_value+"'";
             			var second_condition = "'"+val.second_condition+"'";
             			var second_condition_value = "'"+val.second_condition_value+"'"; */
             			var index = key + 1;
             			
             			var alert_condition_id = '<input type="hidden" id="alert_condition_ids'+index+'" name="alert_condition_ids" class="validate" required="required" value="'+val.alert_condition_id+'">'
             			var first_condition_value = '<input type="number" id="first_condition_values'+index+'" name="first_condition_values" class="validate" required="required" value="'+val.first_condition_value+'">'
             			
             			var second_condition_value = '';
             			if($.trim(val.second_condition) != '' && $.trim(val.second_condition_value) != ''){
             				second_condition_value = '<input type="number" id="second_condition_values'+index+'" name="second_condition_values" class="validate" required="required" value="'+val.second_condition_value+'">'
	    	        	}else{
	    	        		second_condition_value = '<input type="hidden" id="second_condition_values'+index+'" name="second_condition_values" class="validate" required="required">'
	    	        	}
             			
                        var rowArray = [];    	                 
                        
                        rowArray.push(alert_condition_id + val.alert_level_fk);
                       	rowArray.push(val.first_condition);
                       	rowArray.push(first_condition_value);
                       	rowArray.push(val.second_condition);
                       	rowArray.push(second_condition_value);
                       	
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
        
        function getAlertCondition2(alert_condition_id){         	
        	$("#editModal").modal("open");        
        	
        	$("#alert_condition_id").val("");
        	$("#first_condition").html("");
        	$("#first_condition_value").val("");
        	$("#second_condition").html("");
        	$("#second_condition_value").val("");	
        	$("#second_condition_div").hide();
        	
        	var myParams = {alert_condition_id : alert_condition_id}
			$.ajax({url : "<%=request.getContextPath()%>/ajax/getAlertCondition",type:"POST",data:myParams,
				success : function(data){ 
	    			if(data != null && data != ''){ 
	    				$("#alert_condition_id").val(data.alert_condition_id);
	    	        	$("#first_condition").html(data.first_condition);
	    	        	$("#first_condition_value").val(data.first_condition_value).focus();
	    	        	
	    	        	if($.trim(data.second_condition) != '' && $.trim(data.second_condition_value) != ''){
	    	        		$("#second_condition_div").show();
	    	        	}
	    	        	
    	        		$("#second_condition").html(data.second_condition);
	    	        	$("#second_condition_value").val(data.second_condition_value).focus();
	    	        	
	             		$(".page-loader-2").hide();
	    			}else{
	    				$(".page-loader-2").hide();
	    			}
	    			
	    		},error: function (jqXHR, exception) {
	    			$(".page-loader-2").hide();
	             	getErrorMessage(jqXHR, exception);
	          	}
    		});
        }
        
        function updateAlertCondition(){  
        	$(".page-loader-2").show();
        	$("#editModal").modal("close");
        	
        	/* var alert_condition_id = $("#alert_condition_id").val();
        	var first_condition_value = $("#first_condition_value").val();
        	var second_condition_value = $("#second_condition_value").val();
        	
        	var myParams = {alert_condition_id : alert_condition_id, first_condition_value : first_condition_value, second_condition_value : second_condition_value} */
			
        	var dataString = $("#updateForm").serialize();
        	
        	$.ajax({url : "<%=request.getContextPath()%>/ajax/updateAlertCondition",
				type:"POST",
				data:dataString,
				success : function(data){ 
	    			if(data == true){
	    				swal({
    					  title: "Success!",
    					  text: "Alert Conditions Updated Succesfully.",
    					  type: "success",
    					  showConfirmButton: true
    					}, function(){
    						getAlertConditions();
    					});
	    				
	             		$(".page-loader-2").hide();
	    			}else{
	    				swal("Failed!", "Updating Alert Conditions failed. Try again.", "error");
	    				$(".page-loader-2").hide();
	    			}
	    		},error: function (jqXHR, exception) {
	    			swal("Failed!", "Updating Alert Conditions failed. Try again.", "error");
	    			$(".page-loader-2").hide();
	             	getErrorMessage(jqXHR, exception);
	          	}
    		});
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