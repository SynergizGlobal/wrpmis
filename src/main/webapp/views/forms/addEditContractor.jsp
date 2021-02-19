<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Contractor</c:if>
		 <c:if test="${action eq 'add'}"> Add Contractor</c:if>
     </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">     
     
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contractor.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <style>		
    	.error-msg label,#pan_numberError{
    		color:red!important;   
    	}
    	/* Chrome, Safari, Edge, Opera */
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		/* Firefox */
		input[type=number] {
		  -moz-appearance: textfield;
		}
    	.input-field .searchable_label {
    		font-size:0.85rem;
    		color:#33BFDB;
    	}
    </style>
</head>

<body>
    <!-- header included -->
    <jsp:include page="../layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m">
                            <h6>
	                             <c:if test="${action eq 'edit'}">
		                              <c:if test="${not empty contractorDetails.contractor_id }">
		                              	Update Contractor ( ${contractorDetails.contractor_id})
	                                  </c:if>	                             
	                             </c:if>
								 <c:if test="${action eq 'add'}"> Add Contractor</c:if>
							 </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                         <c:if test="${action eq 'edit'}">				                
			                	<form action="<%=request.getContextPath() %>/update-contractor" id="contractorForm" name="contractorForm" method="post" class="form-horizontal" role="form" >
                         </c:if>
			              <c:if test="${action eq 'add'}">				                
			                	<form action="<%=request.getContextPath() %>/add-contractor" id="contractorForm" name="contractorForm" method="post" class="form-horizontal" role="form" >
						  </c:if>
                            <div class="row">
                            
                                <div class="col m2 hide-on-small-only"></div>
                                <!-- <div class="col s12 m4 input-field">
                                 <c:if test="${not empty contractorDetails.contractor_id }">
                                    <label class="primary-text-bold" style="margin-top: 10px;">Contractor ID : <input id="contractor_id" name="contractor_id" type="text" value="${contractorDetails.contractor_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                  </c:if>
                                </div> -->
                                <input id="contractor_id" name="contractor_id" type="hidden" value="${contractorDetails.contractor_id }" />
                                <c:if test="${action eq 'add'}">	
	                                <div class="col s12 m4 input-field">
	                                    <input id="pan_number" name="pan_number" maxlength="10" minlength="10" type="text" class="validate" onblur="panNumberVerify()" style="text-transform:uppercase">
	                                    <label for="pan_number">PAN Number <span class="required">*</span></label>
	                                    <span id="pan_numberError" class="error-msg" ></span>
	                                </div>  
                                </c:if> 
                                 <c:if test="${action eq 'edit'}">	
	                                <div class="col s12 m4 input-field">
	                                    <input id="pan_number" name="pan_number"  maxlength="10" minlength="10" type="text" class="validate" style="text-transform:uppercase" value="${contractorDetails.pan_number }">
	                                    <label for="pan_number">PAN Number <span class="required">*</span></label>
	                                    <span id="pan_numberError" class="error-msg" ></span>
	                                </div>  
                                </c:if>                          
                                <div class="col s12 m4 input-field">
                                    <p class="searchable_label">Specialization</p>
                                    <select id="specialization" name="contractor_specilization_fk" class="searchable validate-dropdown">
                                        <option value="" selected>Select</option>
                                         <c:forEach var="obj" items="${Specialization }">
		                                      <option value="${obj.contractor_specilization_fk }"  <c:if test="${contractorDetails.contractor_specilization_fk eq obj.contractor_specilization_fk}">selected</c:if>>${obj.contractor_specilization_fk }</option>
		                                 </c:forEach>
                                    </select>
                                      <span id="specilizationError" class="error-msg" ></span>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>

                                <div class="col s12 m8 input-field">
                                    <input id="contractor_name" name="contractor_name" type="text" class="validate" value="${contractorDetails.contractor_name }">
                                    <label for="contractor_name">Contractor Name <span class="required">*</span></label>
                                    <span id="contractor_nameError" class="error-msg" ></span>
                                </div>

                                <div class="col m2 hide-on-small-only"></div>
                            </div>

                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="address" name="address" class="materialize-textarea" data-length="1000">${contractorDetails.address }</textarea>
                                    <label for="address">Address</label>
                                    <span id="addressError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <!-- <div class="col s12 m8">
                                    <div class="row"> -->
                                        <div class="col s12 m4 input-field">
                                            <input id="primary_contract" name="primary_contact_name" type="text" class="validate" value="${contractorDetails.primary_contact_name }">
                                            <label for="primary_contract">Primary Contact</label>
                                            <span id="primary_contractError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="phone_number" name="phone_number" type="number" class="validate" value="${contractorDetails.phone_number }">
                                            <label for="phone_number">Phone Number</label>
                                            <span id="phone_numberError" class="error-msg" ></span>
                                        </div>                                        
                                    <!-- </div>
                                </div> -->
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
							<div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4 input-field">
                                     <input id="email" name="email_id" type="text" class="validate" value="${contractorDetails.email_id }">
                                     <label for="email">Email Id</label>
                                     <span id="emailError" class="error-msg" ></span>
                                 </div>
                                 <div class="col s12 m4 input-field">
		                               <input id="gst_number" name="gst_number" type="text" class="validate" value="${contractorDetails.gst_number }">
		                               <label for="gst_number">GST Number</label>
		                               <span id="gst_numberError" class="error-msg" ></span>
		                         </div>                                
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col m8 s12">
                                    <div class="row">
                                        <div class="col s12 m4 input-field">
                                            <input id="bank_name" name="bank_name" type="text" class="validate" value="${contractorDetails.bank_name }">
                                            <label for="bank_name">Bank Name </label>
                                            <span id="bank_nameError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="ac_no" name="account_number" type="number" class="validate" value="${contractorDetails.account_number }">
                                            <label for="ac_no">Account No </label>
                                            <span id="ac_noError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m4 input-field">
                                            <input id="ifsc_code" name="ifsc_code" type="text" class="validate" value="${contractorDetails.ifsc_code }">
                                            <label for="ifsc_code"> IFSC Code </label>
                                            <span id="ifsc_codeError" class="error-msg" ></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <div class="row">
                               <div class="col m2 hide-on-small-only"></div>
                               <div class="col s12 m8 input-field">
                                   <textarea id="bank_address" name="bank_address" class="materialize-textarea" data-length="1000">${contractorDetails.bank_address }</textarea>
                                   <label for="bank_address">Bank Address</label>
                                   <span id="bank_addressError" class="error-msg" ></span>
                               </div>
                            </div>
                                                      
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 input-field">
                                    <textarea id="remarks" name="remarks" class="materialize-textarea" data-length="1000">${contractorDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateContractor();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addContractor();" style="width: 100%;" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s12 m4">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/contractor" class="btn waves-effect waves-light bg-s"
                                            style="width:100%">Cancel</a>
                                    </div>
                                </div>
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

            </div>
        </div>
    </div>

 <!-- Page Loader -->
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
   <script>
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $(".datepicker").datepicker();
            $('#remarks').characterCounter();
            $('#address').characterCounter();
        });
        
        //$("#pan_number").rules("add", { pattern: "/^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/" })
 
        
        function panNumberVerify(){
        	var pan_number = $("#pan_number").val();
            if ($.trim(pan_number) != "") {
                var myParams = { pan_number: pan_number };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getPanNumberListFormContactor",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                if ($.trim(val.pan_number) != '') {
                                	$('#pan_numberError').show();
                                	document.getElementById('pan_numberError').innerHTML='PAN number already exist';
                                } 
                            });
                        }
                        else{
                        	$('#pan_numberError').empty();
                        }
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
          }
        
        $("#pan_number").blur(function(){
        	var panNumber = $("#pan_number").val();
        	var pan_number = "${contractorDetails.pan_number}";
        	if ($.trim(panNumber) != '' && pan_number == $.trim(panNumber)) {
        		$('#pan_numberError').empty();
            }else{
            	panNumberVerify();
            }
        });
        
       function updateContractor(){
    	   if(validator.form()){ // validation perform
    		   if($('#pan_numberError').html()==""){
    	  		   $(".page-loader").show();	  
        		   document.getElementById("contractorForm").submit();			
        	   }
        	   else{
        		   $("#pan_number").focus();
            	   $(".page-loader").hide();
        	   }		
   	 	 }
       }
       function addContractor(){
    	   if(validator.form()){ // validation perform
	    	   if($('#pan_numberError').html()==""){
		  		   $(".page-loader").show();	  
	    		   document.getElementById("contractorForm").submit();			
	    	   }
	    	   else{
	    		   $("#pan_number").focus();
	        	   $(".page-loader").hide();
	    	   }
   	 	 }
       }
       
       var validator =	$('#contractorForm').validate({
			 errorClass: "my-error-class",
			   validClass: "my-valid-class",
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		 "contractor_name": {
	  			 		required: true
	  			 	  },
	  			 	 "pan_number": {
		  			 	required: true
		  			 }	
	  		 	},
	  		    messages: {
	  		 		  "contractor_name": {
	  			 		required: 'Required'
	  			 	  },
	  			 	"pan_number": {
	  			 		required: 'Required'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "contractor_name" ){
					     document.getElementById("contractor_nameError").innerHTML="";
				 	     error.appendTo('#contractor_nameError');
					 }else if(element.attr("id") == "pan_number" ){
					     document.getElementById("pan_numberError").innerHTML="";
				 	     error.appendTo('#pan_numberError');
					 }else{
	 					 error.insertAfter(element);
			        } 
		   		},invalidHandler: function (form, validator) {
                    var errors = validator.numberOfInvalids();
                    if (errors) {
                        var position = validator.errorList[0].element;
                        jQuery('html, body').animate({
                            scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                        }, 1000);
                    }
                },submitHandler:function(form){
			    	form.submit();
			    }
			});   
       
	       $('select').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	       });
	
	       $('input').change(function(){
	           if ($(this).val() != ""){
	               $(this).valid();
	           }
	       });
    </script>

</body>

</html>