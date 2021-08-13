<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile - User Details - PMIS</title>
  <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
  <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">   
  <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">    
  <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
       
     <style>
      .card-title {
            background-color: #DFE6F6;
        }
	     thead tr {
	        background-color: #2E58AD;
	     }
	     td{
	        word-break: break-word;
	    	word-wrap: break-word;
	   		white-space: initial;
	   		width:50%;  
	     }
	     .mdl-data-table tr td{	     
	   		text-align:center !important;  
	   	}
	     thead th{
	     	text-align: center !important;
   			color: #fff !important;
	     }
        .card-title.headbg {
            padding: 15px;
            margin: -24px;
            text-align: center;
        }

        /* left side code  */
        .profile_photo img {
            width: 250px;
            height: 250px;
            border-radius: 50%;
        }
        
        .profile_name {
		    font-size: 2rem;
		    margin: 20px 0;
		    font-weight: bold;
		    text-transform: uppercase;
		    line-height: 1;
		}

        .profile_designation {
            font-size: 1.5rem;
            font-weight: 400;
            margin: 20px 0;
            text-transform: capitalize;
        }

        .profile_role {
            font-size: 1rem;
            font-weight: 300;
            text-transform: capitalize;
        }

        /* right side code  */
        .profile_info {
            text-align: left;
            /* padding: 20px; */
        }
       
        .card .card {
            -webkit-box-shadow: 0 8px 17px 2px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
            box-shadow: 0 8px 17px 2px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12), 0 5px 5px -3px rgba(0, 0, 0, 0.2);
        }

        @media only screen and (max-width: 600px) {

            .dataTables_info,
            .pagination {
                text-align: center;
            }
        }  
        .error-msg label{color:red!important;}   
		.profile_name .error-msg {
		    text-transform: capitalize;
		    color: red;
		    text-align: left;
		    font-size: 13px;
		    font-weight: 400;
		}
		
        .main .fa{
        	float:right;
        	color:#aaa;
        	margin:0 5px;
        	transition:color .3s linear;
        }
        .main .fa:hover{
        	color:#555;
        }
        .main .hidden{
        	display:none;
        }
        .bg-m{
	    	background-color:#2E58AD;
	    	text-transform:Capitalize;    
	    }
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		  margin: 0;
		}
		input[type=number] {
		  -moz-appearance: textfield;
		}
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content"> 
                <form action="<%=request.getContextPath() %>/update-profile" method="POST" id="profileForm" name="profileForm" class="form-horizontal" role="form" enctype="multipart/form-data">
                	<span class="card-title headbg main">
                		User Details 
                		<i class="fa fa-pencil editing" onclick="toggleEditing()"></i> 
                		<i class="fa fa-save saving hidden" onclick='profileFormSubmit()'></i>
                		<i class="fa fa-close closing hidden" onclick="toggleEditing()"></i>
                	</span>
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
                        <div class="col m6 l4 s12 center-align">
                            <div class="card">
                                <div class="card-content">
                                   <!--  <span class="card-title headbg">Basic</span> -->
                                    <div class="profile_photo">
                                        <span class="hideOrShow">
                                        	<img src="<%=CommonConstants2.USER_IMAGES %>${userDetails.user_image}" onerror="this.onerror=null;this.src='/pmis/resources/images/mrvc.png';" >
                                        </span> 
                                        <span class="hideOrShow hidden"> <!--  <form> -->
											<div class="file-field input-field">
												<div class="btn bg-m">
													<span>Change Image</span> <input type="file" name="userImageFile" id="userImageFile" 
														accept='image/*'>
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" name="user_image" type="text" value="${ userDetails.user_image }">
												</div>
											</div> <!-- </form> -->
										</span>
									</div>
                                    <div class="profile_name">
                                    	 <span class="hideOrShow">${ userDetails.user_name }</span>
                                    	 <span class="hideOrShow input-field hidden">
                                    	 	<input name="user_name" id="user_name" type="text" class="validate"  value="${ userDetails.user_name }"/>
                                    	 	<span id="user_nameError" class="error-msg"></span>
                                    	 </span>
                                    </div>
                                    <div class="profile_designation">${ userDetails.designation }
                                        <span class="profile_role">( ${ userDetails.user_role_name_fk } )</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden"  name="user_id" id="user_id" value="${ userDetails.user_id }" />
                        <div class="col m6 l4 s12">
                            <div class="card">
                                <div class="card-content">
                                    <span class="card-title headbg">Basic</span>
                                    <div class="profile_info">
                                        <div class="row">                                        
											<table>
											    <tbody>
											        <tr>
											            <td>User ID</td>
											            <td>: &nbsp; ${ userDetails.user_id }</td>
											        </tr>
											        <tr>
											            <td>User Type</td>
											            <td>: &nbsp; ${ userDetails.user_type_fk }</td>
											        </tr>
											         <tr>
											            <td>Email</td>
											            <td> 
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.email_id }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="email_id" id="email_id" type="email" class="validate" value="${ userDetails.email_id }">
											            		<span id="email_idError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Department</td>
											            <td>: &nbsp; ${ userDetails.department_name }</td>
											        </tr>
											        <tr>
											            <td>Reporting To</td>
											            <td>: &nbsp; ${ userDetails.reporting_to_designation }</td>
											            <%-- <td>: &nbsp; ${ userDetails.reporting_to_name } <c:if test="${not empty userDetails.designation }">(${ userDetails.reporting_to_designation }) </c:if> </td> --%>
											        </tr>
											        <tr>
											            <td>Mobile No</td>
											            <td>											            	
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.mobile_number }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="mobile_number" id="mobile_number" type="number" class="validate" value="${ userDetails.mobile_number }">
											            		<span id="mobile_numberError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Personal Contact Number</td>
											            <td>											            
											           		<span class="hideOrShow">: &nbsp; ${ userDetails.personal_contact_number }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="personal_contact_number" id="personal_contact_number" type="number" class="validate" value="${ userDetails.personal_contact_number }">
											            		<span id="personal_contact_numberError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Land line</td>
											            <td>
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.landline }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="landline" id="landline" type="number" class="validate" value="${ userDetails.landline }">
											            		<span id="landlineError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Extension</td>
											            <td>
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.extension }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="extension" id="extension" type="number" class="validate" value="${ userDetails.extension }">
											            		<span id="extensionError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											         <tr>
											            <td>PMIS Key</td>
											            <td>: &nbsp; ${ userDetails.pmis_key_fk }</td>
											        </tr>
											     											        
											    </tbody>
											</table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col m12 l4 s12">
                            <div class="card" style="min-height: 445px;">
                                <div class="card-content">
                                    <span class="card-title headbg">User Access</span>
                                    <!-- <div style="padding-top: 100px;"></div> -->
                                    <table id="example2" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th>Access Type</th>
                                                <th>Value</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="obj" items="${userDetails.userPermissions }">
                                        		<tr>
	                                                <td>${obj.user_access_type }</td>
	                                                <td>${obj.access_value }</td>
	                                            </tr>
                                        	</c:forEach>
                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                  </form>
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
  <jsp:include page="./layout/footer.jsp"></jsp:include>
    		
  <script src="/pmis/resources/js/jQuery-v.3.5.min.js" ></script>
  <script src="/pmis/resources/js/materialize-v.1.0.min.js" ></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	  <script>
        $(document).ready(function () {
            $('#example2').DataTable({
                "searching": false,
                columnDefs: [
                    {
                        targets: ['_all'],
                        className: 'mdc-data-table__cell'
                    }
                ],
                "language": {
                    "info": "Showing _START_ - _END_ in _TOTAL_ ",
                    "lengthMenu": "",
                    "paginate": {
                        "previous": "<",
                        "next": ">",
                    },
                },
                "ScrollX": true,
                "scrollCollapse": true,
                "sScrollY": 400,
            });
        });
        
        function toggleEditing(){
        	$('.main .fa').toggleClass('hidden');
        	$('.hideOrShow').toggleClass('hidden');      	       	
        }
        
        function profileFormSubmit(){
        	if(validator.form()){ // validation perform
	        	$(".page-loader").show();	
	   			document.getElementById("profileForm").submit();	
        	}
        }
        
        var validator =	$('#profileForm').validate({
	  		    rules: {
	  		 		   "user_name": {
	  			 		  required: true
	  			 	  },"email_id": {
	  			 		  required: false
	  			 	  },"mobile_number": {
	  			 		  required: false,
		  			 	  minlength:10,
		  			 	  maxlength:10,
		  			 	  number: true
	  			 	  },"personal_contact_number": {
	  			 		  required: false,
		  			 	  minlength:10,
		  			 	  maxlength:10,
		  			 	  number: true
	  			 	  }	,"landline": {
	  			 		  required: false,
	  			 		  number: true
	  			 	  }	,"extension": {
	  			 		  required: false
	  			 	  }		
	  		 	},
	  		    messages: {
	  		 		   "user_name": {
	  			 		  required: 'Required'								
	  			 	  },"email_id": {
	  			 		  required: 'Required'
	  			 	  }	,"mobile_number": {
	  			 		  required: "Enter your mobile no"
	  			 	  },"personal_contact_number": {
	  			 		  required: "Enter your mobile no"
	  			 	  },"landline": {
	  			 		  required: "Enter your landline no"
	  			 	  },"extension": {
	  			 		  required: 'Required'
	  			 	  }	
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "user_name" ){
					     document.getElementById("user_nameError").innerHTML="";
				 	     error.appendTo('#user_nameError');
					 }else if(element.attr("id") == "email_id" ){
					     document.getElementById("email_idError").innerHTML="";
				 	     error.appendTo('#email_idError');
					 }else if(element.attr("id") == "mobile_number" ){
					     document.getElementById("mobile_numberError").innerHTML="";
				 	     error.appendTo('#mobile_numberError');
					 }else if(element.attr("id") == "personal_contact_number" ){
					     document.getElementById("personal_contact_numberError").innerHTML="";
				 	     error.appendTo('#personal_contact_numberError');
					 }else if(element.attr("id") == "landline" ){
					     document.getElementById("landlineError").innerHTML="";
				 	     error.appendTo('#landlineError');
					 }else if(element.attr("id") == "extension" ){
					     document.getElementById("extensionError").innerHTML="";
				 	     error.appendTo('#extensionError');
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
    </script>
	
</body>

</html>