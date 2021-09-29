<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update R & R</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/mrvc/resources/images/favicon.png">
  <link rel="stylesheet" href="/mrvc/resources/css/materialize-v.1.0.min.css">
  <link rel="stylesheet" href="/mrvc/resources/css/randr.css">
  <link rel="stylesheet" href="/mrvc/resources/css/update_page.css">
  <style type="text/css">
  	.error{color:red;}
  </style>
</head>

<body>
  <!-- header including -->
  <jsp:include page="../layout/header.jsp"></jsp:include>

  
  <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                                <h6>Update R & R</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-r-and-r" id="updateForm" name="updateForm" method="post">
							<input type="hidden" id="randRId" name="randRId" value="${obj.randRId }" />

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">
                                    <label>Work :</label>
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
                                <!-- row 8 -->
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
                                <!-- row 7 -->
                                <div class="col  m2 hide-on-small-only"></div>
                                <div class="col s6 m2 input-field">
                                    <label>Affected Structure :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.affectedStructure }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label>Affected People :</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col s6 m2 input-field">
                                    <label> ${obj.affectedPeople }</label>
                                    <p>&nbsp;</p>
                                </div>
                                <div class="col m1 hide-on-small-only"></div>
                            </div>


                            <%-- <div class="row" style="margin-top: 2rem;">
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
                                            <td>Identification Status</td>
                                            <td> <select id="identificationStatusId" name="identificationStatusId" onchange="changeStatusToInprogress(this.value,'approvalStatusId');" <c:if test="${obj.identificationStatusId eq '3' }">disabled</c:if>>
                                                    <option value="" selected>Select</option>
                                                    <c:forEach var="statusObj" items="${statusList }">
                                                   		<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.identificationStatusId }">selected</c:if>>${statusObj.statusName }</option>
                                                   </c:forEach>
                                                </select>
                                                <span id="identificationStatusIdError"></span>
                                                <c:if test="${obj.identificationStatusId eq '3' }">
                                                	<input type="hidden" id="identificationStatusId " name="identificationStatusId" value="${obj.identificationStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Approval Status</td>
                                            <td> <select id="approvalStatusId" name="approvalStatusId" onchange="changeStatusToInprogress(this.value,'resettlementStatusId');" <c:if test="${obj.approvalStatusId eq '3' }">disabled</c:if>>
                                                    <option value="" selected>Select</option>
                                                    <c:forEach var="statusObj" items="${statusList }">
                                                   		<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.approvalStatusId }">selected</c:if>>${statusObj.statusName }</option>
                                                   </c:forEach>
                                                </select>
                                                <span id="approvalStatusIdError"></span>
                                                <c:if test="${obj.approvalStatusId eq '3' }">
                                                	<input type="hidden" id="approvalStatusId " name="approvalStatusId" value="${obj.approvalStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Resettlement Status</td>
                                            <td> <select id="resettlementStatusId" name="resettlementStatusId" onchange="changeStatusToInprogress(this.value,'rehabilitationStatusId');" <c:if test="${obj.resettlementStatusId eq '3' }">disabled</c:if>>
                                                    <option value="" selected>Select</option>
                                                    <c:forEach var="statusObj" items="${statusList }">
                                                   		<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.resettlementStatusId }">selected</c:if>>${statusObj.statusName }</option>
                                                   </c:forEach>
                                                </select>
                                                <span id="resettlementStatusIdError"></span>
                                                <c:if test="${obj.resettlementStatusId eq '3' }">
                                                	<input type="hidden" id="resettlementStatusId " name="resettlementStatusId" value="${obj.resettlementStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Rehabilitaion Status</td>
                                            <td> <select id="rehabilitationStatusId" name="rehabilitationStatusId" <c:if test="${obj.rehabilitationStatusId eq '3' }">disabled</c:if>>
                                                    <option value="" selected>Select</option>
                                                    <c:forEach var="statusObj" items="${statusList }">
                                                   		<option value="${statusObj.statusId }" <c:if test="${statusObj.statusId eq obj.rehabilitationStatusId }">selected</c:if>>${statusObj.statusName }</option>
                                                   </c:forEach>
                                                </select>
                                                <span id="rehabilitationStatusIdError"></span>
                                                <c:if test="${obj.rehabilitationStatusId eq '3' }">
                                                	<input type="hidden" id="rehabilitationStatusId " name="rehabilitationStatusId" value="${obj.rehabilitationStatusId }" />
                                                </c:if>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div> --%>
                            
                            <div class="row" style="margin-top: 20px;">
				                <!-- row  -->
				                <div class="col  m2 hide-on-small-only"></div>
				                <div class="col s12 m4 input-field">
				                  <select id="houseAllocated" name="houseAllocated">
                                      <option value="" selected>Select</option>
                                      <option value="Yes" <c:if test="${'Yes' eq obj.houseAllocated }">selected</c:if>>Yes</option>
                                      <option value="No" <c:if test="${'No' eq obj.houseAllocated }">selected</c:if>>No</option>
                                  </select>
				                  <label>House Allocation (Y/N)</label>
				                  <span id="houseAllocatedError"></span>
				                </div>
				              </div>

                            <div class="row">
                                <!-- row 9 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                   <input id="paymentAmount" name="paymentAmount" type="number" class="validate" value="${obj.paymentAmount }">
                                    <label for="paymentAmount">Payment Amount</label>
                                    <span id="paymentAmountError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                     <input type="text" class="datepicker" id="paymentDate" name="paymentDate" value="${obj.paymentDate }">
                                      <button type="button" id="paymentDateIcon"><i class='fa fa-calendar'></i></button>
                                      <label for="paymentDate">Payment Date</label>
                                	<span id="paymentDateError"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 10 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                     <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${obj.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError"></span>
                                </div>
                            </div>
<!-- update and back buttons starts -->
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <button type="submit" class="btn waves-effect waves-light bg-m"
                                            style="width: 100%;">Update</button>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/r-and-r" class="btn waves-effect waves-light bg-s"
                                            style="width: 100%;">Back</a>
                                    </div>
                                </div>
<!-- update and back buttons ends -->
                                
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

  <jsp:include page="../layout/footer.jsp"></jsp:include>

  <!-- footer including -->
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
    });
    //update status 
    function changeStatusToInprogress(statusId,stageId){
    	if($.trim(statusId) != '' && statusId == '3'){
    		$("#"+stageId).val("2");
    		$("#"+stageId).formSelect();
    	}else{
    		var stageStatusValue = "1";
    		if($.trim(stageId) != '' && stageId == 'identificationStatusId'){
    			stageStatusValue = "${obj.identificationStatusId}";
    		}else if($.trim(stageId) != '' && stageId == 'approvalStatusId'){
    			stageStatusValue = "${obj.approvalStatusId}";
    		}else if($.trim(stageId) != '' && stageId == 'resettlementStatusId'){
    			stageStatusValue = "${obj.resettlementStatusId}";
    		}else if($.trim(stageId) != '' && stageId == 'rehabilitationStatusId'){
    			stageStatusValue = "${obj.rehabilitationStatusId}";
    		}
    		$("#"+stageId).val(stageStatusValue);
    		$("#"+stageId).formSelect();
    	}
    }
  //form validations
    $('#updateForm').validate({
    	ignore: ":hidden:not(select)",
		   rules: {
			   	  "identificationStatusId": {
			 		required: true
			 	  },"approvalStatusId": {
			 		required: true
			 	  },"resettlementStatusId": {
			 		required: true
			 	  },"rehabilitationStatusId": {
			 		required: true
			 	  },"houseAllocated": {
			 		required: false
			 	  },"paymentAmount": {
			 		required: false
			 	  },"paymentDate": {
		 		    required: false,
		 		    dateFormat:false
		 	   	  },"remarks": {
			 		required: false
			 	  }			 				
		 	},
		   messages: {
			   	"identificationStatusId": {
		 			required: 'Select'
		 	  	 },"approvalStatusId": {
		 			required: 'Select'
		 	  	 },"resettlementStatusId": {
		 			required: 'Select'
		 	  	 },"rehabilitationStatusId": {
		 			required: 'Select'
		 	  	 },"houseAllocated": {
		 			required: 'Select'
		 	  	 },"paymentAmount": {
		 			required: 'Amount required'
		 	  	 },"paymentDate": {
		 			required: 'Select'
		 	  	 },"remarks": {
		 			required: 'Enter remarks'
		 	  	 }
		 				      
	    },
		  errorPlacement:
		 	function(error, element){
			  if (element.attr("id") == "identificationStatusId" ){
		 		     document.getElementById("identificationStatusIdError").innerHTML="";
		 			 error.appendTo('#identificationStatusIdError');
		 	    }else if (element.attr("id") == "approvalStatusId" ){
		 	    	 document.getElementById("approvalStatusIdError").innerHTML="";
		 			 error.appendTo('#approvalStatusIdError');
		 	    }else if (element.attr("id") == "resettlementStatusId" ){
		 		     document.getElementById("resettlementStatusIdError").innerHTML="";
		 			 error.appendTo('#resettlementStatusIdError');
		 	    }else if (element.attr("id") == "rehabilitationStatusId" ){
		 		     document.getElementById("rehabilitationStatusIdError").innerHTML="";
		 			 error.appendTo('#rehabilitationStatusIdError');
		 	    }else if (element.attr("id") == "houseAllocated" ){
		 		     document.getElementById("houseAllocatedError").innerHTML="";
		 			 error.appendTo('#houseAllocatedError');
		 	    }else if (element.attr("id") == "paymentAmount" ){
		 		     document.getElementById("paymentAmountError").innerHTML="";
		 			 error.appendTo('#paymentAmountError');
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