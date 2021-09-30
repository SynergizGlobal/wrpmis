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
                                <h6>Update Land Acquisition type 3</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-land-acquisition-2" id="updateForm" name="updateForm" method="post">
							<input type="hidden" id="landAcquisitionId" name="landAcquisitionId" value="${obj.landAcquisitionId }" />
                            <div class="row">
                            <!-- row 1 -->
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
                            <!-- row 2 -->
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
                                <!-- row 3 -->
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
                                <!-- row 4 -->
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
                                <!-- row 5 -->
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
                            

                            <div class="row">
                                <!-- row 6 -->
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                    <input id="paymentInCr" name="paymentInCr" type="number" class="validate" value="${obj.paymentInCr }">
                                    <label for="payment">Payment Amount</label>
                                    <span id="paymentInCrError"></span>
                                </div>
                                <div class="col s12 m4 input-field">
                                    <input type="text" class="datepicker" id="paymentDate" name="paymentDate" placeholder="Payment Date" value="${obj.paymentDate }">
                                	<span id="paymentDateError"></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <!-- row 7 -->
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
                                        <a href="<%=request.getContextPath() %>/land-acquisition-2" class="btn waves-effect waves-light bg-s"
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
    });
    
  //form validations
    
    $('#updateForm').validate({
    	ignore: ":hidden:not(select)",
		   rules: {
			   	  "paymentInCr": {
			 		required: false
			 	  },"paymentDate": {
		 		    required: false,
		 		    dateFormat: false
		 	   	  },"remarks": {
			 		required: false
			 	  }			 				
		 	},
		   messages: {
			   	"paymentInCr": {
		 			required: 'Amount required'
		 	  	 },"paymentDate": {
		 			required: 'Select'
		 	  	 },"remarks": {
		 			required: 'Enter remarks'
		 	  	 }
		 				      
	    },
		  errorPlacement:
		 	function(error, element){
			 	if (element.attr("id") == "paymentInCr" ){
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