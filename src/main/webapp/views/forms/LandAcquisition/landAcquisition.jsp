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
   .hiddendiv.common{
   		width:100vw !important;
   }
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
/*data table styling ends */
        
  </style>
   <!-- link id="theme" rel="stylesheet" type="text/css" href="/mrvc/resources/css/light-theme.css" /-->
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
                            <h6> Land Acquisition</h6>
                        </div>
                    </span>
                    <div class="">
                        <%-- <div class="row">
                            <div class="col s12 m12">
                                <div class="center-align m-1">
                                    <a href="<%=request.getContextPath() %>/land-acquisition-add-form" class="btn waves-effect waves-light bg-s t-c">
                                        <strong><i class="fa fa-plus-circle"></i> Add Land Acquisition</strong></a>
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
                                            <th>Plot No.</th>
                                            <th>Location</th>
                                            <th>Sub Location</th>
                                            <th>Chainage</th>
                                            <th>Category</th>
                                            <th>Sub Category</th>
                                            <th>Area</th>
                                            <th>Active Status</th>
                                            <!-- <th>Payment Amount</th> -->
                                            <th class="nosort">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:forEach var="obj" items="${landAcquisitionList }">										
	                                        <tr>
	                                            <td>${obj.workId }</td>
	                                            <td>${obj.plotNo }</td>
	                                            <td>${obj.location }</td>
	                                            <td>${obj.subLocation }</td>
	                                            <td>${obj.chainageFrom } - ${obj.chainageTo }</td>
	                                            <td>${obj.laCategoryName }</td>
	                                            <td>${obj.laSubCategoryName }</td>
	                                            <td>${obj.area } ${obj.areaUnits }</td>
	                                            <td>${obj.activeStatus }</td>
	                                            <%-- <td>${obj.paymentInCr }</td> --%>
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
                                    <!-- <tfoot>
                                        <tr>
                                            <th>Work ID</th>
                                            <th>Location Name</th>
                                            <th>Sub Location Name</th>
                                            <th>Chainage</th>
                                            <th>Category</th>
                                            <th>Sub Category</th>
                                            <th>Area</th>
                                            <th>Active Status</th>
                                            <th>Payment Amount</th>
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
    
    
  <!-- Modal Structure for la update -->
    <div id="la_update" class="modal fs">
        <div class="modal-content">
            <div class="center-align p-2 bg-m modal-title">
                <h6>Update Land Acquisition</h6>
            </div>

            <div class="container ">
                <form action="#">
                    <div class="row">
                        <div class="col s12 m3 input-field">
                            <label>Work :</label>
                        </div>
                        <div class="col s12 m9 input-field">
                            <label id="item1"> </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12 m3 input-field">
                            <label>Chainage From :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item2"> </label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label>Chainage To :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item3"> </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12 m3 input-field">
                            <label>Category :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item4"> </label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label>Sub Category :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item5"> </label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s12 m3 input-field">
                            <label>Location :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item6"> </label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label>Sub Location :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item7"> </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12 m3 input-field">
                            <label>Plot Number :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item8"> </label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label>Area :</label>
                        </div>
                        <div class="col s12 m3 input-field">
                            <label id="item9"> </label>
                        </div>
                    </div>

 <!-- status and stage table starts -->
                    <div class="row">
                        <table class="col s12 m12 form-table" style="margin-top:1rem;">
                            <thead>
                                <tr class="bg-m">
                                    <th>Stage</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Survey Status</td>
                                    <td> <select>
                                            <option value="0" selected>Select</option>
                                            <option value="4">Completed</option>
                                            <option value="2">In Progress</option>
                                            <option value="3">Not Started</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Valuation Status</td>
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
                                    <td>Acquisition Status</td>
                                    <td> <select>
                                            <option value="0" selected>Select</option>
                                            <option value="4">Completed</option>
                                            <option value="2">In Progress</option>
                                            <option value="3">Not Started</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Payment Status</td>
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
                           <input id="paymentInCr" name="paymentInCr" type="number" class="validate" value="${obj.paymentInCr }">
                           <label for="payment">Payment Amount</label>
                           <span id="paymentInCrError"></span>
                        </div>
                        <div class="col s12 m6 input-field">
                           <input type="text" class="datepicker" id="paymentDate" name="paymentDate" placeholder="Payment Date">
                           <span id="paymentDateError"></span>
                        </div>

                    </div>

                    <div class="row no-mar">
                        <div class="col s12 m12 input-field">
                            <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000"></textarea>
                            <label for="textarea1">Remarks</label>
                            <span id="remarksError"></span>
                        </div>
                    </div>

                    <div class="row no-mar">
<!-- update and cancel buttons starts -->
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
<!-- update and cancel buttons ends -->

                    </div>
                </form>
            </div>
<!-- card ending -->
        </div>

    </div>


  <!-- footer including -->
  
  <jsp:include page="../../layout/footer.jsp"></jsp:include>
  
  <form action="<%=request.getContextPath()%>/get-land-acquisition-0" id="getForm" name="getForm" method="post">
  		<input type="hidden" name="landAcquisitionId" id="landAcquisitionId"/>
  </form>
  
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
  <script src="/mrvc/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/mrvc/resources/js/dataTables.material.min.js"></script>
    <!--script src="/mrvc/resources/js/dataTables.fixedHeader.min.js"></script-->
  <script src="/mrvc/resources/js/fixedHeader.js"></script>
  <script>
//material components & dynamic data table initialization
    $(document).ready(function () {
    	$('select').formSelect();
    	$('.modal').modal();
    	
    	
    	var table = $('#example').DataTable({
    		"bStateSave": true,
    		//fixedHeader: true,
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
       //new $.fn.dataTable.FixedHeader( table );
    	$('.close-message').delay(3000).fadeOut('slow');
    });
    
    function  getLandAcquisition(landAcquisitionId) {
		$("#landAcquisitionId").val(landAcquisitionId);
		$("#getForm").submit();
	}
    
    
    <%-- function getLandAcquisition(landAcquisitionId){
		if($.trim(landAcquisitionId) != ""){
			var myParams = {landAcquisitionId : landAcquisitionId}; 
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/getLandAcquisition",
				data:myParams,cache: false,
				success:function(data){ 
					//$("#la_update").modal();
					$("#la_update").modal('open');
					if(data != '' && data != null){
						$("#item1").html(data.workName);
						$("#item2").html(data.chainageFrom);
						$("#item3").html(data.chainageTo);
						$("#item4").html(data.laCategoryName);
						$("#item5").html(data.laSubCategoryName);
						$("#item6").html(data.location);
						$("#item7").html(data.subLocation);
						$("#item8").html(data.plotNo);
						$("#item9").html(data.area +" "+ data.areaUnits);
						
						$("#paymentInCr").val(data.paymentInCr).focus();
						$("#paymentDate").val(data.paymentDate).focus();
						$("#remarks").val(data.remarks).focus();
					}
				},error:function(error){
					
				}
			});
		}
	} --%>
  </script>
</body>

</html>