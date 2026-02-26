<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Land Acquisition</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
  <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/mrvc/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/mrvc/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/mrvc/resources/css/la.css">
  <link rel="stylesheet" href="/mrvc/resources/css/update_page.css">
   <style type="text/css">
  	.error{color:red;}
  	/*data table styling starts*/
  	.dataTables_length {
            margin-top: 15px;
        }

        .no-mar {
            margin-bottom: 0;
        }

        .mdl-grid {
            padding: 3px 8px;
        }

        td:not(:first-child) .btn+.btn {
            margin-left: 5px;
        }

        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            position: relative;
        }

        .dataTables_filter input[type="search"]::placeholder {
            color: #6C587B;
        }

        .dataTables_filter label::after {
            position: relative;
            content: '\f002';
            color: #6C587B;
            right: 15px;
            font: normal normal normal 14px/1 FontAwesome;
        }

        .dataTables_filter label {
            color: #fff;
        }

        .mdl-data-table.dataTable thead {
            position: sticky;
            top: 50px;
        }
        
        td span.fa {
            font-size: 30px;
        }
/*data table styling ends */
/*color change after status updated */
        .not_started {
            /*color: #f81c31;*/
            color: lightgray;
        }

        .in_progress {
            color: #f8ba03;
            cursor: pointer;
        }

        .completed {
            color: #25ce4c;
        }
  </style>
</head>

<body>
  <!-- header including -->
  <jsp:include page="../../layout/header.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
        <!-- card starting -->
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> Land Acquisition Type 3</h6>
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

                        <div class="row">
                            <div class="col m12 s12">
<!-- dynamic data table starts -->
                                <table id="example" class="mdl-data-table">
                                    <thead>
                                        <tr>
                                            <th>Work ID</th>
                                            <th>Plot No.</th>
                                            <th>Location</th>
                                            <!-- <th>Sub Location</th> -->
                                            <th>Chainage</th>
                                            <th>Category</th>
                                            <th>Sub Category</th>
                                            <th>Area</th>
                                            <!-- <th>Payment Amount</th> -->
                                            <th class="nosort">Survey</th>
                                            <th class="nosort">Valuation</th>
                                            <th class="nosort">Approval</th>
                                            <th class="nosort">Payment</th>
                                            <th class="nosort">Acquisition</th>
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${landAcquisitionList }">										
	                                        <tr>
	                                            <td>${obj.workId }</td>
	                                            <td>${obj.plotNo }</td>
	                                            <td>${obj.location }</td>
	                                            <%-- <td>${obj.subLocation }</td> --%>
	                                            <td>${obj.chainageFrom } - ${obj.chainageTo }</td>
	                                            <td>${obj.laCategoryName }</td>
	                                            <td>${obj.laSubCategoryName }</td>
	                                            <td>${obj.area } ${obj.areaUnits }</td>
	                                            <%-- <td>${obj.paymentInCr }</td> --%>
	                                            
	                                            <td>
	                                            	<c:if test="${obj.surveyStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started" style="cursor: pointer;" onclick="updateLAStatus('${obj.landAcquisitionId}','Not Started')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.surveyStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateLAStatus('${obj.landAcquisitionId}','Survey')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.surveyStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            
	                                            <td>
	                                            	<c:if test="${obj.valuationStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.valuationStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateLAStatus('${obj.landAcquisitionId}','Valuation')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.valuationStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            
	                                            <td>
	                                            	<c:if test="${obj.approvalStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.approvalStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateLAStatus('${obj.landAcquisitionId}','Approval')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.approvalStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${obj.paymentStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.paymentStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateLAStatus('${obj.landAcquisitionId}','Payment')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.paymentStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${obj.acquisitionStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.acquisitionStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateLAStatus('${obj.landAcquisitionId}','Acquisition')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.acquisitionStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            
	                                            <%-- <td> <a href="<%=request.getContextPath() %>/land-acquisition-edit-form" class="btn waves-effect waves-light bg-m t-c">Edit</a>
	                                                <a href="#" class="btn waves-effect waves-light bg-s t-c">Delete</a>
	                                            </td> --%>
	                                            <td> <a href="javascript:void(0);" onclick="getLandAcquisition('${obj.landAcquisitionId}')" class="btn waves-effect waves-light bg-m t-c">Update</a></td>
												<%-- <td> <a 
                                                    class="btn waves-effect waves-light bg-m t-c modal-trigger" onclick="getLandAcquisition('${obj.landAcquisitionId}')">Update</a>
                                            	</td> --%>
	                                        </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
<!-- dynamic data table ends -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- card ending -->
        </div>
    </div>
    
    <!-- update popup starts -->
    <div id="la_status_update" class="modal">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6><b id="stageStatusHeader"></b> Status Update Confirmation</h6>
            </div>
            <!-- form start-->
            <div class="container">
                <form action="<%=request.getContextPath() %>/update-land-acquisition-status-2" id="updateForm" name="updateForm" method="post">
			  		<input type="hidden" id="updateLandAcquisitionId" name="landAcquisitionId" />
			  		<input type="hidden" id="activeStatus" name="activeStatus" />
                    <div class="row no-mar">
                        <div class="col s12 m12 input-field center-align">
                            <p style="font-size: 2rem;">Are you sure! </p>
                            <p style="margin: 0;">you want to Update <b id="stageStatusBody"></b> Status?</p>
                        </div>
                    </div>
                    <div class="row no-mar">
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="submit" class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">Update</button>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <button type="button" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;" onclick="closeUpdateLAStatusModal();">Cancel</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- update popup ends -->


  <!-- footer including -->
  <jsp:include page="../../layout/footer.jsp"></jsp:include>
  
  <form action="<%=request.getContextPath()%>/get-land-acquisition-2" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="landAcquisitionId" id="landAcquisitionId"/>
  </form>
  
   <script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>	
  <script src="/mrvc/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/mrvc/resources/js/dataTables.material.min.js"></script>
  
  <script>
//material components & dynamic data table initialization
    $(document).ready(function () {
    	$('select').formSelect();
    	$('.modal').modal();
    	
    	var table = $('#example').DataTable({
    		"bStateSave": true,
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
            //"ScrollX": true,
            "scrollCollapse": true,
            fixedHeader: true,
            "sScrollY": 400,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        });
    	
    	table.state.clear();
    	
    	$('.close-message').delay(3000).fadeOut('slow');
    });
    
    function  getLandAcquisition(landAcquisitionId) {
		$("#landAcquisitionId").val(landAcquisitionId);
		$("#getForm").submit();
	}
    
    function  closeUpdateLAStatusModal() {
    	$("#la_status_update").modal('close');
	}
  //update status from one to other
    function  updateLAStatus(landAcquisitionId,activeStatus) {
    	$("#la_status_update").modal('open');
		$("#updateLandAcquisitionId").val(landAcquisitionId);
		var stage = "Not Started";
		if(activeStatus == "Not Started"){
			stage = "Not Started";
		}else if(activeStatus == "Survey"){
			stage = "Survey";
		}else if(activeStatus == "Valuation"){
			stage = "Valuation";
		}else if(activeStatus == "Approval"){
			stage = "Approval";
		}else if(activeStatus == "Payment"){
			stage = "Payment";
		}else if(activeStatus == "Acquisition"){
			stage = "Acquisition";
		}
		
		else if(activeStatus == "Survey Not Started"){
			stage = "Survey";
		}else if(activeStatus == "Valuation Not Started"){
			stage = "Valuation";
		}else if(activeStatus == "Approval Not Started"){
			stage = "Approval";
		}else if(activeStatus == "Payment Not Started"){
			stage = "Payment";
		}else if(activeStatus == "Acquisition Not Started"){
			stage = "Acquisition";
		}
		$("#stageStatusHeader").html(activeStatus);
		$("#stageStatusBody").html(activeStatus);
		$("#activeStatus").val(stage);
	}
    
  </script>
</body>

</html>