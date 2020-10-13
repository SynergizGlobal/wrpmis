<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Safety Equipment </title>
    <link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/font-awesome-v.4.7.css">
    <link rel="stylesheet" href="/pmis/resources/css/sweetalert-v.1.1.0.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined" rel="stylesheet">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/safety.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <style>
        p a {
            color: blue
        }

        td:last-child,
        td:last-of-type {
            white-space: inherit;
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
                            <h6> Safety Equipment</h6>
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
                                    <a href="#" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-arrow-circle-up"></i> Upload Data</strong></a>
                                    <p style="padding-top:1rem"> Click <a href="#">here</a> for the template</p>
                                </div> -->
                            </div>

                            <div class="col s12 m4">
                                <div class="m-1 c-align">
                                    <a href="<%=request.getContextPath() %>/add-safety-equipment-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Safety Equipment</strong></a>
                                </div>
                            </div>

                            <div class="col s12 m4 r-align">
                                <div class="m-1 ">
                                    <a href="javascript:void(0);" onclick="exportSafetyEquipment()" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-cloud-download"></i> Export Data</strong></a>
                                </div>
                            </div>
                        </div>

                        <div class="row no-mar" style="margin-bottom: 0;">
                            <div class="col m3 hide-on-small-only"></div>
                            <div class="col m6 s12">
                                <div class="row" style="margin-bottom: 0;">
                                    <div class="col m2 hide-on-small-only"></div>
                                    <div class="col s12 m4 input-field">
                                        <p><label>Select Contract</label></p>
                                        <select id="contract_id_fk" name="contract_id_fk" class="searchable" onchange="getSafetyEquiptmentList();">
                                            <option value="" disabled selected>Select Contract</option>
	                                            <c:forEach var="obj" items="${contractList}">
		                       						  <option value="${obj.contract_id }" <c:if test="${param.contract_id eq obj.contract_id }">selected</c:if>>${obj.contract_id }<c:if test="${not empty obj.contract_name}"> - </c:if>${obj.contract_name}</option>
		                                        </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 input-field">
                                        <button class="btn bg-m waves-effect waves-light t-c clear-filters black-text" onclick="clearFilter();"
                                            style="margin-top: 18px;width: 100%;">Clear Filters</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col m3 hide-on-small-only"></div>
                        </div>
                        <div class="row">
                            <div class="col m12 s12">

                                <table id="datatable-safety" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Contract</th>
                                            <th>Equipment No</th>
                                            <th>Equipment Details</th>
                                            <th> Validity of Equipment</th>
                                            <th class="no-sort">Remarks </th>
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
                                            <td class="last-column"> <a href="safety_Equipment.html"
                                                    class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-pencil"></i> </a>
                                                <a href="#" class="btn waves-effect waves-light bg-s t-c "><i
                                                        class="fa fa-trash"></i></a>
                                            </td>
                                        </tr> -->
                                      <!--   <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td class="last-column"> <a href="safety_Equipment.html"
                                                    class="btn waves-effect waves-light bg-s t-c "><i
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
	<script src="/pmis/resources/js/sweetalert-v.1.1.0.min.js"></script>
	
	
		<form name="getForm" id="getForm" method="post">
    		<input type="hidden" name="safety_equipment_id" id="safety_equipment_id" />
   	    </form>
   	    <form action="<%=request.getContextPath() %>/export-safety-equipment" name="exportSafetyEquipmentForm" id="exportSafetyEquipmentForm" target="_blank" method="post">	
       		 <input type="hidden" name="contract_id_fk" id="exportContract_id_fk" />
       		 <input type="hidden" name="safety_equipment_id" id="exportSafety_equipment_id" />
		</form>
	
    <script>
    $(document).ready(function () {
		 $('select:not(.searchable)').formSelect();
        $('.searchable').select2();
	       	var table = $('#datatable-safety').DataTable({
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
	            "sScrollY": 400,
	            initComplete: function () {
	                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
	            }
	        });
	    	table.state.clear(); 
	    	$('.close-message').delay(3000).fadeOut('slow');
	    	getSafetyEquiptmentList();
	    });
    
    function clearFilter(){
    	$("#contract_id_fk").val("");
    	$('.searchable').select2();
    	getSafetyEquiptmentList();
    }

    function getSafetyEquiptmentList(){
    	var contract_id_fk = $("#contract_id_fk").val();
     	table = $('#datatable-safety').DataTable();
		table.destroy();
		$.fn.dataTable.moment('DD-MMM-YYYY');
		table = $('#datatable-safety').DataTable({
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
            "sScrollY": 400,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        }).rows().remove().draw();
		
		
		table.state.clear();		
	 
	 	var myParams = {contract_id_fk : contract_id_fk};
		$.ajax({url : "<%=request.getContextPath()%>/ajax/get-safety-equipment",type:"POST",data:myParams,success : function(data){    				
				if(data != null && data != '' && data.length > 0){    					
	         		$.each(data,function(key,val){
	         			var safety_equipment_id = "'"+val.safety_equipment_id+"'";
	                    var actions = '<a href="javascript:void(0);"  onclick="getSafetyEquipmentId('+safety_equipment_id+');"  class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-pencil"></i> </a>'
/* 	                    			  +'<a onclick="deleteSafetyEquipment('+safety_equipment_id +');" class="btn waves-effect waves-light bg-s t-c "><i class="fa fa-trash"></i></a>';
 */
	                   	var rowArray = [];    	                 
	                   
	                   	rowArray.push($.trim(val.contract_id_fk));
	                   	rowArray.push($.trim(val.safety_equipment_number));
	                   	rowArray.push($.trim(val.safety_equipment_detail));
	                   	rowArray.push($.trim(val.validity_date));
	                   	rowArray.push($.trim(val.remarks));
	                   	rowArray.push($.trim(actions));   	                   	
	                   	
	                    table.row.add(rowArray).draw( true );
	                    		                       
					});
	         		
	         		$(".page-loader").hide();
				}else{
					$(".page-loader").hide();
				}
				
			},error: function (jqXHR, exception) {
				$(".page-loader").hide();
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
    	
    function getSafetyEquipmentId(safety_equipment_id){
    	$("#safety_equipment_id").val(safety_equipment_id);
    	$('#getForm').attr('action', '<%=request.getContextPath()%>/get-safety-equipment');
    	$('#getForm').submit();
    }
    
    function deleteSafetyEquipment(safety_equipment_id){
    	$("#safety_equipment_id").val(safety_equipment_id);
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
               // swal("Deleted!", "Record has been deleted", "success");
            	$('#getForm').attr('action', '<%=request.getContextPath()%>/delete-safety-equipment');
    	    	$('#getForm').submit();
           }else {
                swal("Cancelled", "Record is safe :)", "error");
            }
        });
    }
    

    function exportSafetyEquipment(){
	 var contract_id_fk  = $("#contract_id_fk ").val();
	 var safety_equipment_id  = $("#safety_equipment_id ").val();

	 $("#exportContract_id_fk").val(contract_id_fk);
	 $("#exportSafety_equipment_id").val(contract_id_fk);
	 $("#exportSafetyEquipmentForm ").submit();
	}
    
    </script>

</body>
</html>