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
        
        .dataTables_length {
            margin-top: 15px;
        }

        .mdl-grid {
            padding: 3px 8px;
        }
/*data table styling ends*/
        /* drop down container starts here  */
        .dropdown-container form>ul {
            position: absolute;
            top: 100%;
            left: 0;
            width: max-content;
            padding: 50px 20px;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.12), 0 1px 5px 0 rgba(0, 0, 0, 0.2);
            border-bottom-left-radius: 20px;
            border-bottom-right-radius: 20px;
            background-color: #1565C0;
            display: none;
        }

        .dropdown-container:hover form ul {
            display: block;
        }

        .dropdown-container.open form ul {
            display: block;
        }

        /* drop down container ends here  */

        .fa-star {
            color: lightgray;
            font-size: 1.5rem;
        }

        .fa-star.full {
            color: #17a33c;
        }

        .fa-star.half {
            color: #ff6701;
        }

        /* tooltip css starts here  */
        .tooltip .tooltiptext {
            visibility: hidden;
            width: auto;
            background-color: black;
            color: #fff;
            text-align: center;
            padding: 5px;
            bottom: 60%;
            right: 30%;
            font-size: 1rem;
            border-radius: 6px;
            position: absolute;
            z-index: 1;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
        }

        /* tooltip css ends here  */

        button.btn.bg-m,
        button.btn.bg-s {
            font-weight: 600;
        }

        .searchwork option.selected {
            width: 100px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        /* search css starts  */

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

        /* search css ends  */

        /* legends display css starts  */

        .legends {
            display: flex;
            justify-content: space-evenly;
            flex-wrap: wrap;
        }

        .legends div {
            position: relative;
        }

        .legends div span {
            left: -42px;
            top: -4px;
        }


        .legends .jade-green {
            background-color: #167C70;
            box-shadow:
                inset 0 0 10px #fff,
                2px 0 10px #167C70,
                -2px 0 10px #167C70,
                0 2px 10px #167C70,
                0 -2px 10px #167C70,
                0 0 14px #fff;
        }

        /* legends display css starts  */

        /* bulb effect code starts here  */

        .orb {
            position: absolute;
            top: 15px;
            left: 43%;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            cursor: pointer;
        }

        .simple-green-orb {
            background-color: green;
            box-shadow:
                inset 0 0 15px #fff,
                3px 0 10px green,
                -3px 0 10px green,
                0 3px 10px green,
                0 -3px 10px green,
                0 0 14px #fff;
        }

        .survey-orb {
            background-color: #167C70;
            box-shadow:
                inset 0 0 15px #fff,
                3px 0 10px #167C70,
                -3px 0 10px #167C70,
                0 3px 10px #167C70,
                0 -3px 10px #167C70,
                0 0 14px #fff;
        }


        .approval-orb {
            background-color: #163A6F;
            box-shadow:
                inset 0 0 15px #fff,
                3px 0 10px #163A6F,
                -3px 0 10px #163A6F,
                0 3px 10px #163A6F,
                0 -3px 10px #163A6F,
                0 0 14px #fff;
        }


        .valuation-orb {
            background-color: #EE5C21;
            box-shadow:
                inset 0 0 15px #fff,
                3px 0 10px #EE5C21,
                -3px 0 10px #EE5C21,
                0 3px 10px #EE5C21,
                0 -3px 10px #EE5C21,
                0 0 14px #fff;
        }


        .acquisition-orb {
            background-color: #DE369D;
            box-shadow:
                inset 0 0 15px #fff,
                3px 0 10px #DE369D,
                -3px 0 10px #DE369D,
                0 3px 10px #DE369D,
                0 -3px 10px #DE369D,
                0 0 14px #fff;
        }



        .payment-orb {
            background-color: #F7E311;
            box-shadow:
                inset 0 0 15px #fff,
                3px 0 10px #F7E311,
                -3px 0 10px #F7E311,
                0 3px 10px #F7E311,
                0 -3px 10px #F7E311,
                0 0 14px #fff;
        }


        /* bulb effect code starts here  */
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
                            <h6> Land Acquisition Type 5</h6>
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
                            <div class="col s12 m12 ">
                                <div class="legends">
                                    <div><span class="orb survey-orb"></span> Survey Staus</div>
                                    <div><span class="orb valuation-orb"></span> Valuation Status</div>
                                    <div><span class="orb approval-orb"></span> Approval Status</div>
                                    <div><span class="orb payment-orb"></span> Payment Status</div>
                                    <div><span class="orb acquisition-orb"></span> Acquisition Status</div>
                                </div>
                            </div>
                        </div>
                        
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
                                            <th class="nosort">Active Status</th>
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
	                                            	<c:if test="${obj.activeStatus eq 'Completed' or obj.activeStatus eq 'Not Started'}">
	                                            		${obj.activeStatus}
	                                            	</c:if>
	                                            	<c:if test="${obj.surveyStatusId eq '2' }">
	                                            		<a class="orb survey-orb"  onclick="updateLAStatus('${obj.landAcquisitionId}','Survey')"></a>
	                                            	</c:if>
	                                            	
	                                            	<c:if test="${obj.valuationStatusId eq '2' }">
	                                            		<a class="orb valuation-orb"  onclick="updateLAStatus('${obj.landAcquisitionId}','Valuation')"></a>
	                                            	</c:if>
	                                            	<c:if test="${obj.approvalStatusId eq '2' }">
	                                            		<a class="orb approval-orb"  onclick="updateLAStatus('${obj.landAcquisitionId}','Approval')"></a>
	                                            	</c:if>
	                                            	<c:if test="${obj.paymentStatusId eq '2' }">
	                                            		<a class="orb payment-orb"  onclick="updateLAStatus('${obj.landAcquisitionId}','Payment')"></a>
	                                            	</c:if>
	                                            	<c:if test="${obj.acquisitionStatusId eq '2' }">
	                                            		<a class="orb acquisition-orb"  onclick="updateLAStatus('${obj.landAcquisitionId}','Acquisition')"></a>
	                                            	</c:if>
	                                                
	                                            </td>
	                                            <td> <a href="javascript:void(0);" onclick="getLandAcquisition('${obj.landAcquisitionId}')" class="btn waves-effect waves-light bg-m t-c">Update</a></td>
												
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
    <div id="StatusUpdateModal" class="modal">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6><b id="stageStatusHeader"></b> Status Update Confirmation</h6>
            </div>
            <!-- form start-->
            <div class="container">
                <form action="<%=request.getContextPath() %>/update-land-acquisition-status-4" id="updateForm" name="updateForm" method="post">
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
  
  <form action="<%=request.getContextPath()%>/get-land-acquisition-4" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="landAcquisitionId" id="landAcquisitionId"/>
  </form>
  
   <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
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
            // "ScrollX": true,
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
    	$("#updateLandAcquisitionId").val('');
    	$("#activeStatus").val('');
    	$("#StatusUpdateModal").modal('close');
	}
  //update status from one to other
    function  updateLAStatus(landAcquisitionId,activeStatus) {
    	$("#StatusUpdateModal").modal('open');
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