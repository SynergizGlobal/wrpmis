<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Land Acquisition</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
  <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/mrvc/resources/css/la.css">
  <link rel="stylesheet" href="/mrvc/resources/css/update_page.css">
  <style type="text/css">
  	.error{color:red;}
  </style>
</head>

<body>
  <!-- header including -->
  <jsp:include page="../../layout/header.jsp"></jsp:include>

  <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Update Land Acquisition</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-land-acquisition-0" id="updateForm" name="updateForm" method="post">
							<input type="hidden" id="landAcquisitionId" name="landAcquisitionId" value="${obj.landAcquisitionId }" />
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">
                                    <label>Work : </label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m8 input-field">
                                    <label> ${obj.workName }</label>
                                    <p>&nbsp;</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col  m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">
                                    <label>Chainage From :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.chainageFrom }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label>Chainage To :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.chainageTo }</label>
                                    <p>&nbsp;</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col  m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">

                                    <label>Category :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.laCategoryName }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">

                                    <label>Sub Category :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.laSubCategoryName }</label>
                                    <p>&nbsp;</p>
                                </div>

                                <div class="col m1 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col  m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">

                                    <label>Location :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.location }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">

                                    <label>Sub Location :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">

                                    <label> ${obj.subLocation }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m1 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col  m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">
                                    <label>Plot Number :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.plotNo }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label>Area :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.area } ${obj.areaUnits }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m1 hide-on-small-only"></div>
                            </div>


                            <div class="row" style="margin-top: 2rem;margin-bottom: 2rem;">
                                <div class="col m2 hide-on-small-only"></div>
                                <!-- <div ></div> -->
                                <table class="col s12 m8 form-table">
                                    <thead>
                                        <tr class="bg-m">
                                            <th>Stage</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Survey Status</td>                                            
                                            <td> <select id="surveyStatusId" name="surveyStatusId" onchange="changeStatusToInprogress(this.value,'valuationStatusId');" <c:if test="${obj.surveyStatusId eq '3' }">disabled</c:if>>
                                                   <c:choose>
                                               			<c:when test="${obj.surveyStatusId eq '2'}">
                                                			<c:forEach var="statusObj" items="${statusList }">
                                                				<c:if test="${statusObj.statusId ne '1' }">
                                                					<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.surveyStatusId }">selected</c:if>>${statusObj.statusName }</option>
                                                				</c:if>
                                                			</c:forEach>     
                                               			</c:when>
                                               			<c:otherwise>
                                                			<c:forEach var="statusObj" items="${statusList }">
                                                				<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.surveyStatusId }">selected</c:if>>${statusObj.statusName }</option>
                                                			</c:forEach>     
                                               			</c:otherwise>
                                               		</c:choose>                             
                                                </select>
                                                <span id="surveyStatusIdError"></span>
                                                <c:if test="${obj.surveyStatusId eq '3' }">
                                                	<input type="hidden" id="surveyStatusId1" name="surveyStatusId" value="${obj.surveyStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Valuation Status</td>
                                            <td> <select id="valuationStatusId" name="valuationStatusId" onchange="changeStatusToInprogress(this.value,'approvalStatusId');" <c:if test="${obj.valuationStatusId eq '3' or obj.valuationStatusId eq '1' }">disabled</c:if>>
                                                   <c:choose>
	                                           			<c:when test="${obj.valuationStatusId eq '2'}">
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<c:if test="${statusObj.statusId ne '1' }">
	                                            					<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.valuationStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            				</c:if>
	                                            			</c:forEach>     
	                                           			</c:when>
	                                           			<c:otherwise>
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.valuationStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            			</c:forEach>     
	                                           			</c:otherwise>
	                                           		</c:choose>
                                                </select>
                                                <span id="valuationStatusIdError"></span>
                                                <c:if test="${obj.valuationStatusId eq '3' or obj.valuationStatusId eq '1' }">
                                           			<input type="hidden" id="valuationStatusId1" name="valuationStatusId" value="${obj.valuationStatusId }" />
                                           		</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Approval Status</td>
                                            <td> <select id="approvalStatusId" name="approvalStatusId" onchange="changeStatusToInprogress(this.value,'paymentStatusId');" <c:if test="${obj.approvalStatusId eq '3' or obj.approvalStatusId eq '1' }">disabled</c:if>>
                                                    <c:choose>
	                                           			<c:when test="${obj.approvalStatusId eq '2'}">
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<c:if test="${statusObj.statusId ne '1' }">
	                                            					<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.approvalStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            				</c:if>
	                                            			</c:forEach>     
	                                           			</c:when>
	                                           			<c:otherwise>
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.approvalStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            			</c:forEach>     
	                                           			</c:otherwise>
	                                           		</c:choose> 
                                                </select>
                                                <span id="approvalStatusIdError"></span>
                                                <c:if test="${obj.approvalStatusId eq '3' or obj.approvalStatusId eq '1' }">
                                                	<input type="hidden" id="approvalStatusId1" name="approvalStatusId" value="${obj.approvalStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Payment Status</td>
                                            <td> <select id="paymentStatusId" name="paymentStatusId" onchange="getPaymentDetails(this.value);changeStatusToInprogress(this.value,'acquisitionStatusId');" <c:if test="${obj.paymentStatusId eq '3' or obj.paymentStatusId eq '1'}">disabled</c:if>>
                                                   <c:choose>
	                                           			<c:when test="${obj.paymentStatusId eq '2'}">
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<c:if test="${statusObj.statusId ne '1' }">
	                                            					<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.paymentStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            				</c:if>
	                                            			</c:forEach>     
	                                           			</c:when>
	                                           			<c:otherwise>
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.paymentStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            			</c:forEach>     
	                                           			</c:otherwise>
	                                           		</c:choose> 
                                                </select>
                                                <span id="paymentStatusIdError"></span>
                                                <c:if test="${obj.paymentStatusId eq '3' or obj.paymentStatusId eq '1' }">
                                                	<input type="hidden" id="paymentStatusId1" name="paymentStatusId" value="${obj.paymentStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Acquisition Status</td>
                                            <td> <select id="acquisitionStatusId" name="acquisitionStatusId" <c:if test="${obj.acquisitionStatusId eq '3' or obj.acquisitionStatusId eq '1' }">disabled</c:if> >
                                                   <c:choose>
	                                           			<c:when test="${obj.acquisitionStatusId eq '2'}">
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<c:if test="${statusObj.statusId ne '1' }">
	                                            					<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.acquisitionStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            				</c:if>
	                                            			</c:forEach>     
	                                           			</c:when>
	                                           			<c:otherwise>
	                                            			<c:forEach var="statusObj" items="${statusList }">
	                                            				<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.acquisitionStatusId }">selected</c:if>>${statusObj.statusName }</option>
	                                            			</c:forEach>     
	                                           			</c:otherwise>
	                                           		</c:choose> 
                                                </select>
                                                <span id="acquisitionStatusIdError"></span>
                                                <c:if test="${obj.acquisitionStatusId eq '3' or obj.acquisitionStatusId eq '1' }">
                                                	<input type="hidden" id="acquisitionStatusId1" name="acquisitionStatusId" value="${obj.acquisitionStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>

                            <div class="row paymentDetails" style="display: none;">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="paymentInCr" name="paymentInCr" type="number" class="validate" value="${obj.paymentInCr }">
                                    <label for="payment">Payment Amount</label>
                                    <span id="paymentInCrError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="paymentDate" name="paymentDate" placeholder="Payment Date" value="${obj.paymentDate }">
                                     <button type="button" id="paymentDateIcon"><i class='fa fa-calendar'></i></button>
                                	<span id="paymentDateError"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${obj.remarks }</textarea>
                                    <label for="textarea1">Remarks</label>
                                    <span id="remarksError"></span>
                                </div>
                            </div>
<!-- update and back buttons starts -->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <!-- <input type="submit" value=" Update" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;"> -->
                                         <button type="submit" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/land-acquisition-0" class="btn waves-effect waves-light bg-s"
                                            style="width: 100%;">Back</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
<!-- update and back buttons ends -->
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>


  <!-- footer including -->
  <jsp:include page="../../layout/footer.jsp"></jsp:include>
  
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
  <script src="/mrvc/resources/js/jquery-validation-1.19.1.min.js"></script>
  <script>
//material components initialization
    $(document).ready(function () {
        $('select').formSelect();
        //$(".datepicker").datepicker();
        $('#remarks').characterCounter();
        $('#paymentDate').datepicker({
    	    //selectMonths: true, // Enable Month Selection
    	    format:'dd-mm-yyyy',
    	    onSelect: function () {
    	     	$('.confirmation-btns .datepicker-done').click();
    	  	}
    	});
        
        $('#paymentDateIcon').click(function() {
  	    	$('#paymentDate').click();
  		});
        
        var paymentStatusId = "${obj.paymentStatusId}";
        if($.trim(paymentStatusId) != '' && paymentStatusId == '3'){
    		$(".paymentDetails").show();
    	}else{
    		$("#paymentInCr").val("");
    		$("#paymentDate").val("");
    	}
    });
    //update status 
    function changeStatusToInprogress(statusId,stageId){
    	$("#"+stageId+" option").remove();
    	if($.trim(statusId) != '' && statusId == '3'){    		
    		<c:forEach var="statusObj" items="${statusList }">
				<c:if test="${statusObj.statusId ne '1' }">
				$("#"+stageId).append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:if>
			</c:forEach>
			$("#"+stageId).removeAttr('disabled');
    		$("#"+stageId).val("2");
    		//$("#"+stageId).prop('disabled', 'disabled');
    		$("#"+stageId).formSelect();
    		$("#"+stageId+"1").removeAttr('disabled');
    		$("#"+stageId+"1").val("2");    
    		$("#"+stageId+"1").prop('disabled', 'disabled');
    	}else{
    		<c:forEach var="statusObj" items="${statusList }">
				$("#"+stageId).append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
			</c:forEach>
			
			var surveyStatusId = "${obj.surveyStatusId}";
			var valuationStatusId = "${obj.valuationStatusId}";
			var approvalStatusId = "${obj.approvalStatusId}";
			var paymentStatusId = "${obj.paymentStatusId}";
			var acquisitionStatusId = "${obj.acquisitionStatusId}";
		
    		var stageStatusValue = "1";
    		if($.trim(stageId) != '' && stageId == 'surveyStatusId'){
    			stageStatusValue = "${obj.surveyStatusId}";        		
        		$("#"+stageId).removeAttr('disabled');
        		$("#"+stageId).val(stageStatusValue);
        		$("#"+stageId).prop('disabled', 'disabled');
        		$("#"+stageId).formSelect();
        		$("#"+stageId+"1").val(stageStatusValue);
        		$("#"+stageId+"1").removeAttr('disabled');
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#valuationStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
        		
        		$("#valuationStatusId").removeAttr('disabled');
        		$("#valuationStatusId").val(valuationStatusId);
        		$("#valuationStatusId").prop('disabled', 'disabled');
        		$("#valuationStatusId").formSelect();
        		$("#valuationStatusId1").val(valuationStatusId);
        		$("#valuationStatusId1").removeAttr('disabled');
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#approvalStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
        		
        		$("#approvalStatusId").removeAttr('disabled');
        		$("#approvalStatusId").val(approvalStatusId);
        		$("#approvalStatusId").prop('disabled', 'disabled');
        		$("#approvalStatusId").formSelect();
        		$("#approvalStatusId1").val(approvalStatusId);
        		$("#approvalStatusId1").removeAttr('disabled');
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#paymentStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
        		
        		$("#paymentStatusId").removeAttr('disabled');
        		$("#paymentStatusId").val(paymentStatusId);
        		$("#paymentStatusId").prop('disabled', 'disabled');
        		$("#paymentStatusId").formSelect();
        		$("#paymentStatusId1").val(paymentStatusId);
        		$("#paymentStatusId1").removeAttr('disabled');
        		
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#acquisitionStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
        		
        		$("#acquisitionStatusId").removeAttr('disabled');
        		$("#acquisitionStatusId").val(acquisitionStatusId);
        		$("#acquisitionStatusId").prop('disabled', 'disabled');
        		$("#acquisitionStatusId").formSelect();
        		$("#acquisitionStatusId1").val(acquisitionStatusId);
        		$("#acquisitionStatusId1").removeAttr('disabled');
        		
        		
    		}else if($.trim(stageId) != '' && stageId == 'valuationStatusId'){
    			stageStatusValue = "${obj.valuationStatusId}";
    			$("#"+stageId).removeAttr('disabled');
        		$("#"+stageId).val(stageStatusValue);
        		$("#"+stageId).prop('disabled', 'disabled');
        		$("#"+stageId).formSelect();
        		$("#"+stageId+"1").val(stageStatusValue);
        		$("#"+stageId+"1").removeAttr('disabled');
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#approvalStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
	    		
	    		$("#approvalStatusId").removeAttr('disabled');
	    		$("#approvalStatusId").val(approvalStatusId);
	    		$("#approvalStatusId").prop('disabled', 'disabled');
	    		$("#approvalStatusId").formSelect();
	    		$("#approvalStatusId1").val(approvalStatusId);
	    		$("#approvalStatusId1").removeAttr('disabled');
	    		
	    		<c:forEach var="statusObj" items="${statusList }">
					$("#paymentStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
	    		
	    		$("#paymentStatusId").removeAttr('disabled');
	    		$("#paymentStatusId").val(paymentStatusId);
	    		$("#paymentStatusId").prop('disabled', 'disabled');
	    		$("#paymentStatusId").formSelect();
	    		$("#paymentStatusId1").val(paymentStatusId);
	    		$("#paymentStatusId1").removeAttr('disabled');
	    		
	    		<c:forEach var="statusObj" items="${statusList }">
					$("#acquisitionStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
	    		
	    		$("#acquisitionStatusId").removeAttr('disabled');
	    		$("#acquisitionStatusId").val(acquisitionStatusId);
	    		$("#acquisitionStatusId").prop('disabled', 'disabled');
	    		$("#acquisitionStatusId").formSelect();
	    		$("#acquisitionStatusId1").val(acquisitionStatusId);
	    		$("#acquisitionStatusId1").removeAttr('disabled');	    		
    		
    		}else if($.trim(stageId) != '' && stageId == 'approvalStatusId'){
    			stageStatusValue = "${obj.approvalStatusId}";
    			$("#"+stageId).removeAttr('disabled');
        		$("#"+stageId).val(stageStatusValue);
        		$("#"+stageId).prop('disabled', 'disabled');
        		$("#"+stageId).formSelect();
        		$("#"+stageId+"1").val(stageStatusValue);
        		$("#"+stageId+"1").removeAttr('disabled');
	    		
	    		<c:forEach var="statusObj" items="${statusList }">
					$("#paymentStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
	    		
	    		$("#paymentStatusId").removeAttr('disabled');
	    		$("#paymentStatusId").val(paymentStatusId);
	    		$("#paymentStatusId").prop('disabled', 'disabled');
	    		$("#paymentStatusId").formSelect();
	    		$("#paymentStatusId1").val(paymentStatusId);
	    		$("#paymentStatusId1").removeAttr('disabled');
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#acquisitionStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
	    		
	    		$("#acquisitionStatusId").removeAttr('disabled');
	    		$("#acquisitionStatusId").val(acquisitionStatusId);
	    		$("#acquisitionStatusId").prop('disabled', 'disabled');
	    		$("#acquisitionStatusId").formSelect();
	    		$("#acquisitionStatusId1").val(acquisitionStatusId);
	    		$("#acquisitionStatusId1").removeAttr('disabled');
    		}else if($.trim(stageId) != '' && stageId == 'paymentStatusId'){
    			stageStatusValue = "${obj.paymentStatusId}";
    			$("#"+stageId).removeAttr('disabled');
        		$("#"+stageId).val(stageStatusValue);
        		$("#"+stageId).prop('disabled', 'disabled');
        		$("#"+stageId).formSelect();
        		$("#"+stageId+"1").val(stageStatusValue);
        		$("#"+stageId+"1").removeAttr('disabled');
        		
        		<c:forEach var="statusObj" items="${statusList }">
					$("#acquisitionStatusId").append('<option value="${statusObj.statusId }">${statusObj.statusName }</option>');
				</c:forEach>
	    		
	    		$("#acquisitionStatusId").removeAttr('disabled');
	    		$("#acquisitionStatusId").val(acquisitionStatusId);
	    		$("#acquisitionStatusId").prop('disabled', 'disabled');
	    		$("#acquisitionStatusId").formSelect();
	    		$("#acquisitionStatusId1").val(acquisitionStatusId);
	    		$("#acquisitionStatusId1").removeAttr('disabled');
	    		
    		}else if($.trim(stageId) != '' && stageId == 'acquisitionStatusId'){
    			stageStatusValue = "${obj.acquisitionStatusId}";
    			$("#"+stageId).removeAttr('disabled');
        		$("#"+stageId).val(stageStatusValue);
        		$("#"+stageId).prop('disabled', 'disabled');
        		$("#"+stageId).formSelect();
        		$("#"+stageId+"1").val(stageStatusValue);
        		$("#"+stageId+"1").removeAttr('disabled');
    		}
		
    		/* $("#"+stageId).removeAttr('disabled');
    		$("#"+stageId).val(stageStatusValue);
    		$("#"+stageId).prop('disabled', 'disabled');
    		$("#"+stageId).formSelect();
    		$("#"+stageId+"1").val(stageStatusValue); */
    		
    		if($.trim(paymentStatusId) == '' || paymentStatusId != '3'){
        		$(".paymentDetails").hide();
        	}
    	}
    }
    
    function getPaymentDetails(statusId){
    	if($.trim(statusId) != '' && statusId == '3'){
    		$(".paymentDetails").show();
    	}else{
    		$(".paymentDetails").hide();
    		$("#paymentInCr").val("");
    		$("#paymentDate").val("");
    	}
    	
    }
    
  //form validations
    $('#updateForm').validate({
    	ignore: ":hidden:not(select)",
		   rules: {
			   	  "surveyStatusId": {
			 		required: true
			 	  },"valuationStatusId": {
			 		required: true
			 	  },"approvalStatusId": {
			 		required: true
			 	  },"acquisitionStatusId": {
			 		required: true
			 	  },"paymentStatusId": {
			 		required: true
			 	  },"paymentInCr": {
			 		required: false
			 	  },"paymentDate": {
		 		    required: false,
		 		    dateFormat: false
		 	   	  },"remarks": {
			 		required: false
			 	  }			 				
		 	},
		   messages: {
			   	"surveyStatusId": {
		 			required: 'Select'
		 	  	 },"valuationStatusId": {
		 			required: 'Select'
		 	  	 },"approvalStatusId": {
		 			required: 'Select'
		 	  	 },"acquisitionStatusId": {
		 			required: 'Select'
		 	  	 },"paymentStatusId": {
		 			required: 'Select'
		 	  	 },"paymentInCr": {
		 			required: 'Amount required'
		 	  	 },"paymentDate": {
		 			required: 'Select'
		 	  	 },"remarks": {
		 			required: 'Enter remarks'
		 	  	 }
		 				      
	    },
		  errorPlacement:
		 	function(error, element){
			  if (element.attr("id") == "surveyStatusId" ){
		 		     document.getElementById("surveyStatusIdError").innerHTML="";
		 			 error.appendTo('#surveyStatusIdError');
		 	    }else if (element.attr("id") == "valuationStatusId" ){
		 	    	 document.getElementById("valuationStatusIdError").innerHTML="";
		 			 error.appendTo('#valuationStatusIdError');
		 	    }else if (element.attr("id") == "approvalStatusId" ){
		 		     document.getElementById("approvalStatusIdError").innerHTML="";
		 			 error.appendTo('#approvalStatusIdError');
		 	    }else if (element.attr("id") == "acquisitionStatusId" ){
		 		     document.getElementById("acquisitionStatusIdError").innerHTML="";
		 			 error.appendTo('#acquisitionStatusIdError');
		 	    }else if (element.attr("id") == "paymentStatusId" ){
		 		     document.getElementById("paymentStatusIdError").innerHTML="";
		 			 error.appendTo('#paymentStatusIdError');
		 	    }else if (element.attr("id") == "paymentInCr" ){
		 		     document.getElementById("paymentInCrError").innerHTML="";
		 			 error.appendTo('#paymentInCrError');
		 	    }else if (element.attr("id") == "paymentDate" ){
		 		     document.getElementById("paymentDateError").innerHTML="";
		 			 error.appendTo('#paymentDateError');
		 	    }else if (element.attr("id") == "remarks" ){
		 		     document.getElementById("remarksError").innerHTML="";
		 			 error.appendTo('#remarksError');
		 	    }
		 },submitHandler: function(form) {
		    form.submit();
		  }
	});

    $.validator.addMethod("dateFormat",
	    function(value, element) {
	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
	    },
	    "Date format (dd-mm-yyyy)!"
	);
  </script>
</body>

</html>