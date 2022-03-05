<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
   		 <c:if test="${action eq 'edit'}">Update Contractor - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Contractor - Update Forms - PMIS</c:if>
     </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">  
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/contractor.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    <style>		
    	.error-msg label,#pan_numberError{
    		color:red!important;   
    	}
    	.primary-text-bold {    
		    font-size: 1rem;
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
    			
		/* new css code starts from here */ 		
			
		@media only screen and (max-width: 768px){
			/* table datepicker , select2 dropdown , table column and update , cancel buttons styling for mobile versions */				
			
			.mt-brdr{
				margin-top: auto !important;
    			border: none !important;
			}
			.mt-brdr .btn{
   				width: 100% !important;
			}
			
			/* input fields styles for mobile version  */
			div.input-field {
			    margin-top: 1rem;
			    margin-bottom: 1rem;
			}
			.input-field p.searchable_label {
			    margin-top: -20px !important;
   				margin-bottom: -4px;
			}
			.input-field>.datelike ~ label:not(.label-icon).active, 
			.input-field>input[type='text']:not(.datepicker) ~ label:not(.label-icon).active,
			.input-field>label:not(.label-icon).active {
			    -webkit-transform: translateY(-18px) scale(0.95);
			    transform: translateY(-18px) scale(0.95);
			}			
			.col.input-field>textarea+label:not(.label-icon).active {
			    margin-top: 0;
			}
		}
		@media(max-width: 575px){
			.row .col{margin: 6px auto}
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
                            <div class="center-align p-2 bg-m m-b-2">
                            <h6>
	                             <c:if test="${action eq 'edit'}">
		                              	Update Contractor (${contractorDetails.contractor_id })
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
                               <!-- <div class="col s12 m4 input-field">
                                 <c:if test="${not empty contractorDetails.contractor_id }">
                                    <label class="primary-text-bold" style="margin-top: 10px;">Contractor ID : <input id="contractor_id" name="contractor_id" type="text" value="${contractorDetails.contractor_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
                                  </c:if>
                                </div> -->
                                <c:if test="${action eq 'add'}">	
	                                <div class="col s6 m4 l6 input-field offset-m2">
	                                    <input id="pan_number" name="pan_number" maxlength="10" minlength="10" type="text" class="validate" onblur="panNumberVerify()" style="text-transform:uppercase">
	                                    <label for="pan_number">PAN Number <span class="required">*</span></label>
	                                    <span id="pan_numberError" class="error-msg" ></span>
	                                </div>  
	                                <div class="col s6 m4 l6 input-field">
	                                    <p class="searchable_label">Specialization<span class="required">*</span></p>
	                                    <select id="contractor_specilization_fk" name="contractor_specilization_fk" class="searchable validate-dropdown">
	                                        <option value="" selected>Select</option>
	                                         <c:forEach var="obj" items="${Specialization }">
			                                      <option value="${obj.contractor_specilization_fk }"  <c:if test="${contractorDetails.contractor_specilization_fk eq obj.contractor_specilization_fk}">selected</c:if>>${obj.contractor_specilization_fk }</option>
			                                 </c:forEach>
	                                    </select>
	                                      <span id="specilizationError" class="error-msg" ></span>
	                                </div>
                                </c:if> 
                                 <c:if test="${action eq 'edit'}">	
                                  <input id="contractor_id" type="hidden" class="form-control" name="contractor_id" value="${contractorDetails.contractor_id }" >   
	                                <div class="col s6 m4 l6 input-field offset-m2">
	                                    <input id="pan_number" name="pan_number"  maxlength="10" minlength="10" type="text" class="validate" style="text-transform:uppercase" value="${contractorDetails.pan_number }" readonly="readonly">
	                                    <label for="pan_number">PAN Number <span class="required">*</span></label>
	                                    <span id="pan_numberError" class="error-msg" ></span>
	                                </div>  
	                                <div class="col s6 m4 l6 input-field">
	                                    <input id="contractor_specilization_fk" name="contractor_specilization_fk" type="text" class="validate" value="${contractorDetails.contractor_specilization_fk }" readonly="readonly">
	                                    <label for="contractor_specilization_fk">Specialization <span class="required">*</span></label>
	                                    <span id="contractor_specilization_fkError" class="error-msg" ></span>
	                                </div> 
                                </c:if>                          
                                
                                <div class="col m2 hide-on-small-only"></div>
                            </div>
                            <c:if test="${action eq 'add'}">
                            	<div class="row">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <input id="contractor_name" name="contractor_name" type="text" class="validate" value="${contractorDetails.contractor_name }">
	                                    <label for="contractor_name">Contractor Name <span class="required">*</span></label>
	                                    <span id="contractor_nameError" class="error-msg" ></span>
	                                </div>
	                            </div>
                            </c:if>
							<c:if test="${action eq 'edit'}">	
								<div class="row">
	                               <!-- <c:if test="${not empty contractorDetails.contractor_id }">
	                                	<div class="col s6 m4 offset-m2" style="padding-top: 1rem;">
		                                    <label class="primary-text-bold">Contractor ID : <input id="contractor_id" name="contractor_id" type="text" value="${contractorDetails.contractor_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
		                                </div>
	                                 </c:if> --!>
	
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <input id="contractor_name" name="contractor_name" type="text" class="validate" value="${contractorDetails.contractor_name }" readonly="readonly">
	                                    <label for="contractor_name">Contractor Name <span class="required">*</span></label>
	                                    <span id="contractor_nameError" class="error-msg" ></span>
	                                </div>
	
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
							</c:if>
                            
							<c:if test="${action eq 'edit'}">
                            <div class="row">
                                <div class="col s12 m8 l12 input-field offset-m2">
                                    <textarea id="address" name="address" class="pmis-textarea" data-length="1000">${contractorDetails.address }</textarea>
                                    <label for="address">Address</label>
                                    <span id="addressError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                 <div class="col s6 m4 l4 input-field offset-m2">
                                     <input id="primary_contact_name" name="primary_contact_name" type="text" class="validate" value="${contractorDetails.primary_contact_name }">
                                     <label for="primary_contact_name">Primary Contact<span class="required">*</span></label>
                                     <span id="primary_contact_nameError" class="error-msg" ></span>
                                 </div>
                                 <div class="col s6 m4 l4 input-field">
                                     <input id="phone_number" name="phone_number" type="number" class="validate" value="${contractorDetails.phone_number }">
                                     <label for="phone_number">Phone Number<span class="required">*</span></label>
                                     <span id="phone_numberError" class="error-msg" ></span>
                                 </div>  
                                 <div class="col s6 m4 l4 input-field offset-m2">
                                     <input id="email" name="email_id" type="text" class="validate" value="${contractorDetails.email_id }">
                                     <label for="email">Email Address</label>
                                     <span id="emailError" class="error-msg" ></span>
                                 </div> 
                                 <div class="col s6 m4 l4 input-field">
		                               		<input id="gst_number" name="gst_number" type="text" class="validate" value="${contractorDetails.gst_number }">
		                               		<label for="gst_number">GST Number<span class="required">*</span></label>
		                               		<span id="gst_numberError" class="error-msg" ></span>
		                         		</div>
                                        <div class="col s6 m4 l4 input-field offset-m2">
                                            <input id="bank_name" name="bank_name" type="text" class="validate" value="${contractorDetails.bank_name }">
                                            <label for="bank_name">Bank Name </label>
                                            <span id="bank_nameError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s6 m2 l2 input-field">
                                            <input id="ifsc_code" name="ifsc_code" type="text" class="validate" value="${contractorDetails.ifsc_code }">
                                            <label for="ifsc_code"> IFSC Code </label>
                                            <span id="ifsc_codeError" class="error-msg" ></span>
                                        </div>
                                        <div class="col s12 m2 l2 input-field">
                                            <input id="ac_no" name="account_number" type="number" class="validate" value="${contractorDetails.account_number }">
                                            <label for="ac_no">Account No </label>
                                            <span id="ac_noError" class="error-msg" ></span>
                                        </div>
                            </div>
                            
                            <div class="row">
                               <div class="col m2 hide-on-small-only"></div>
                               <div class="col s12 m8 l12 input-field">
                                   <textarea id="bank_address" name="bank_address" class="pmis-textarea" data-length="1000">${contractorDetails.bank_address }</textarea>
                                   <label for="bank_address">Bank Address</label>
                                   <span id="bank_addressError" class="error-msg" ></span>
                               </div>
                            </div>
                            </c:if>                         
                            <div class="row">
                                <div class="col m2 hide-on-small-only"></div>
                                <div class="col s12 m8 l12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea" data-length="1000">${contractorDetails.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m4 l6 mt-brdr offset-m2">
                                    <div class="center-align m-1">
	                                         <c:if test="${action eq 'edit'}">
	                                           <button type="button" onclick="updateContractor();" class="btn waves-effect waves-light bg-m">Update</button>
	                                         </c:if>
											 <c:if test="${action eq 'add'}"> 
						                       <button type="button" onclick="addContractor();" class="btn waves-effect waves-light bg-m">Add</button>
											 </c:if>
                                    </div>
                                </div>
                                <div class="col s6 m4 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath()%>/contractor" class="btn waves-effect waves-light bg-s">Cancel</a>
                                    </div>
                                </div>
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
	  			 	 },"pan_number": {
		  			 	required: true
		  			 },"contractor_specilization_fk":{
		  				required: true
		  			 },"remarks":{
		  				required: false
		  			 },"primary_contact_name":{
		  				required: true
		  			 },"phone_number":{
		  				required: true
		  			 },"gst_number":{
		  				required: true
		  			 }
	  		 	},
	  		    messages: {
	  		 		  "contractor_name": {
	  			 		required: 'Required'
	  			 	  },"pan_number": {
	  			 		required: 'Required'
	  			 	  },"contractor_specilization_fk":{
	  			 		required: 'Required'
		  			  },"remarks":{
		  				required: 'Required'
		  			  },"primary_contact_name":{
		  				required: 'Required'
		  			  },"phone_number":{
		  				required: 'Required'
		  			  },"gst_number":{
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
					 }else if(element.attr("id") == "contractor_specilization_fk" ){
					     document.getElementById("specilizationError").innerHTML="";
				 	     error.appendTo('#specilizationError');
					 }else if(element.attr("id") == "remarks" ){
					     document.getElementById("remarksError").innerHTML="";
				 	     error.appendTo('#remarksError');
					 }else if(element.attr("id") == "primary_contact_name" ){
					     document.getElementById("primary_contact_nameError").innerHTML="";
				 	     error.appendTo('#primary_contact_nameError');
					 }else if(element.attr("id") == "phone_number" ){
					     document.getElementById("phone_numberError").innerHTML="";
				 	     error.appendTo('#phone_numberError');
					 }else if(element.attr("id") == "gst_number" ){
					     document.getElementById("gst_numberError").innerHTML="";
				 	     error.appendTo('#gst_numberError');
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