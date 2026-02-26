<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>R & R</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
  <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/mrvc/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/mrvc/resources/css/datatable-material.css">
  <link rel="stylesheet" href="/mrvc/resources/css/randr.css">
  <link rel="stylesheet" href="/mrvc/resources/css/update_page.css">
   <style type="text/css">
  	.error{color:red;}
  	/*datatable styling starts */
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
  	/*datatable styling ends */
/*color change after status updated*/
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
  <jsp:include page="../layout/header.jsp"></jsp:include>

  
  <div class="row">
        <div class="col s12 m12">
        <!-- card starting -->
            <div class="card">
                <div class="card-content">
                    <span class="card-title headbg">
                        <div class="center-align bg-m p-2 m-b-5">
                            <h6> R & R</h6>
                        </div>
                    </span>
                    <div class="">
                        <%-- <div class="row">
                            <div class="col s12 m12">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath() %>/r-and-r-add-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add R & R</strong></a>
                                </div>
                            </div>
                        </div> --%>
                        
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
                                            <th>Chainage</th>
                                            <th>Location</th>
                                            <th>Sub Location</th>
                                            <th>Affected Structures</th>
                                            <th>Affected People</th>
                                            <!-- <th>Payment Amount</th> -->
                                            <th class="nosort">Identification</th>
                                            <th class="nosort">Approval</th>
                                            <th class="nosort">Resettlement</th>
                                            <th class="nosort">Rehabilitaion</th>
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${randRList }">	
	                                        <tr>
	                                            <td>${obj.workId }</td>
	                                            <td>${obj.chainageFrom } - ${obj.chainageTo }</td>
	                                            <td>${obj.location }</td>
	                                            <td>${obj.subLocation }</td>
	                                            <td>${obj.affectedStructure }</td>
	                                            <td>${obj.affectedPeople }</td>
	                                            <%-- <td>${obj.paymentAmount }</td> --%>
	                                            <%-- <td>${obj.activeStatus }
	                                            	<c:if test="${obj.activeStatus ne 'Completed' }">
	                                            	 <a onclick="updateRRStatus('${obj.randRId}','${obj.activeStatus}')" class="waves-effect waves-light modal-trigger">
													    <i class="material-icons right">send</i>
													 </a>
													</c:if>
	                                            </td> --%>
	                                            
	                                            <td>
	                                            	<c:if test="${obj.identificationStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started" style="cursor: pointer;" onclick="updateRRStatus('${obj.randRId}','Not Started')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.identificationStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateRRStatus('${obj.randRId}','Identification')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.identificationStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${obj.approvalStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.approvalStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateRRStatus('${obj.randRId}','Approval')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.approvalStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${obj.resettlementStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.resettlementStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateRRStatus('${obj.randRId}','Resettlement')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.resettlementStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${obj.rehabilitationStatusId eq '1' }">
	                                            		<span data-value="not_started" class="fa fa-check-circle not_started"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.rehabilitationStatusId eq '2' }">
	                                            		<span data-value="in_progress" class="fa fa-check-circle in_progress" onclick="updateRRStatus('${obj.randRId}','Rehabilitation')"></span>
	                                            	</c:if>
	                                            	<c:if test="${obj.rehabilitationStatusId eq '3' }">
	                                            		<span data-value="completed" class="fa fa-check-circle completed"></span>
	                                            	</c:if>
	                                            </td>
	                                            
	                                           <%--  <td> <a href="<%=request.getContextPath() %>/r-and-r-edit-form" class="btn waves-effect waves-light bg-m t-c">Edit</a>
	                                                <a href="#" class="btn waves-effect waves-light bg-s t-c">Delete</a>
	                                            </td> --%>
	                                            <td> <a href="javascript:void(0);" onclick="getRandR('${obj.randRId}')" class="btn waves-effect waves-light bg-m t-c">Update</a></td>
												<!-- <td> <a href="#rr_update"
	                                                    class="btn waves-effect waves-light bg-m t-c modal-trigger">Update</a>
	                                            </td> -->
	                                        </tr>
	                                   </c:forEach>

                                    </tbody>
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Work ID</th>
                                            <th>Chainage</th>
                                            <th>Location Name</th>
                                            <th>Sub Location Name</th>
                                            <th>Affected Structures</th>
                                            <th>Affected People</th>
                                            <th>Payment Amount</th>
                                            <th>Active Status</th>
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </tfoot> -->
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
    <div id="rr_status_update" class="modal">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6><b id="stageStatusHeader"></b> Status Update Confirmation</h6>
            </div>
            <!-- form start-->
            <div class="container">
               <form action="<%=request.getContextPath() %>/update-randr-status" id="updateForm" name="updateForm" method="post">
			  		<input type="hidden" id="updateRandRId" name="randRId" />
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
                                    style="width: 100%;" onclick="closeUpdateRRStatusModal();">Cancel</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- update popup ends -->
    
    <!-- Modal Structure for rr update -->
    <div id="rr_update" class="modal fs">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Update R & R</h6>
            </div>
            <!-- form start-->
            <div class="container">
                <form action="#">

                    <div class="row">

                        <div class="col s12 m3 input-field">
                            <label>Work :</label>

                        </div>
                        <div class="col s12 m9 input-field">
                            <label> </label>

                        </div>
                    </div>
                    <div class="row">

                        <div class="col s12 m3 input-field">
                            <label>Chainage From :</label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label> </label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label>Chainage To :</label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label> </label>

                        </div>
                    </div>


                    <div class="row">

                        <div class="col s12 m3 input-field">

                            <label>Location :</label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label> </label>

                        </div>
                        <div class="col s12 m3 input-field">

                            <label>Sub Location :</label>

                        </div>
                        <div class="col s12 m3 input-field">

                            <label> </label>

                        </div>

                    </div>
                    <div class="row" style="margin-bottom: 40px;">

                        <div class="col s12 m3 input-field">
                            <label>Affected Structure :</label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label> </label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label>Affected People :</label>

                        </div>
                        <div class="col s12 m3 input-field">
                            <label> </label>

                        </div>

                    </div>


                    <div class="row">
 <!-- status and stage table starts -->
                        <!-- <div ></div> -->
                        <table class="col s12 m12 form-table">
                            <thead>
                                <tr class="bg-m">
                                    <th>Stage</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Identification Status</td>
                                    <td> <select>
                                            <option value="0" selected>Select</option>
                                            <option value="4">Completed</option>
                                            <option value="2">In Progress</option>
                                            <option value="3">Not Started</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Approval Status</td>
                                    <td> <select>
                                            <option value="0" selected>Select</option>
                                            <option value="4">Completed</option>
                                            <option value="2">In Progress</option>
                                            <option value="3">Not Started</option>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Resettlement Status</td>
                                    <td> <select>
                                            <option value="0" selected>Select</option>
                                            <option value="4">Completed</option>
                                            <option value="2">In Progress</option>
                                            <option value="3">Not Started</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Rehabilitaion Status</td>
                                    <td> <select>
                                            <option value="0" selected>Select</option>
                                            <option value="4">Completed</option>
                                            <option value="2">In Progress</option>
                                            <option value="3">Not Started</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
 <!-- status and stage table ends -->
                    <div class="row no-mar">

                        <div class="col s12 m6 input-field">
                            <select>
                                <option value="0" selected>Select</option>
                                <option value="4">Completed</option>
                                <option value="2">In Progress</option>
                                <option value="3">Not Started</option>
                            </select>
                            <label>House Allocation Status</label>
                        </div>

                    </div>

                    <div class="row no-mar">

                        <div class="col s12 m6 input-field">
                            <input id="payment_amt" type="text" class="validate">
                            <label for="payment_amt">Payment Amount</label>
                        </div>
                        <div class="col s12 m6 input-field">
                            <input type="text" class="datepicker" id="paydate" placeholder="Payment Date">
                        </div>

                    </div>

                    <div class="row no-mar">

                        <div class="col s12 m12 input-field">
                            <textarea id="textarea2" class="materialize-textarea" data-length="1000"></textarea>
                            <label for="textarea2">Remarks</label>
                        </div>
                    </div>
<!-- update and cancel buttons starts -->
                    <div class="row no-mar">

                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <input type="submit" value=" Update" class="btn waves-effect waves-light bg-m"
                                    style="width: 100%;">
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <div class="center-align m-1">
                                <input type="button" value="Cancel" class="btn waves-effect waves-light bg-s"
                                    style="width: 100%;">
                            </div>
                        </div>

                    </div>
<!-- update and cancel buttons ends -->
                    
                </form>
            </div>
            <!-- form ends  -->

        </div>
    </div>


  <!-- footer including -->
  <jsp:include page="../layout/footer.jsp"></jsp:include>
  
  <form action="<%=request.getContextPath()%>/get-r-and-r" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="randRId" id="randRId"/>
  </form>
  	<script src="/wrpmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/wrpmis/resources/js/materialize-v.1.0.min.js"></script>	
  <script src="/mrvc/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/mrvc/resources/js/dataTables.material.min.js"></script>
  
  <script>
//material components & dynamic data tables initialization
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
            // "ScrollX": true,
            "scrollCollapse": true,
            "sScrollX": "100%",
            //fixedHeader: true,
            "sScrollY": 400,
            initComplete: function () {
                $('.dataTables_filter input[type="search"]').attr('placeholder', 'Search').css({ 'width': '350px', 'display': 'inline-block' });
            }
        });
    	
    	table.state.clear();
    	
    	$('.close-message').delay(3000).fadeOut('slow');
    });
    
    
    function  getRandR(randRId) {
		$("#randRId").val(randRId);
		$("#getForm").submit();
	}
    
    function  closeUpdateRRStatusModal() {
    	$("#rr_status_update").modal('close');
	}
    //update statuses
    function  updateRRStatus(randRId,activeStatus) {
    	$("#rr_status_update").modal('open');
		$("#updateRandRId").val(randRId);
		var stage = "Not Started";
		if(activeStatus == "Not Started"){
			stage = "Not Started";
		}else if(activeStatus == "Identification"){
			stage = "Identification";
		}else if(activeStatus == "Approval"){
			stage = "Approval";
		}else if(activeStatus == "Resettlement"){
			stage = "Resettlement";
		}else if(activeStatus == "Rehabilitation"){
			stage = "Rehabilitation";
		}
		else if(activeStatus == "Identification Not Started"){
			stage = "Identification";
		}else if(activeStatus == "Approval Not Started"){
			stage = "Approval";
		}else if(activeStatus == "Resettlement Not Started"){
			stage = "Resettlement";
		}else if(activeStatus == "Rehabilitation Not Started"){
			stage = "Rehabilitation";
		}
		$("#stageStatusHeader").html(activeStatus);
		$("#stageStatusBody").html(stage);
		$("#activeStatus").val(stage);
	}
  </script>
</body>

</html>