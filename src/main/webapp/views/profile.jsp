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
  <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
  <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
  <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
       
     <style>
     .theme-change {
		  position: fixed;
		  z-index: 2;
		  right: -20px;
		  top: 60px;
		  border: 0;
		  cursor: pointer;
		  font-size: 1.5rem;
		  transition: all 1s ease-in-out;
		}
		.theme-change .fa-moon-o {
		    color: white;
		}
		@media (hover: hover) and (pointer: fine){
			.theme-change:hover {
			    transform: translateX(-20px);
			}		
		}
      .card-title {
            background-color: #DFE6F6;
        }
       .text-capitalize{
       		text-transform:capitalize;
       }
       .table-inside{
       	    overflow-x: auto;
       }
       .table-plus-btn{
       	    text-align: center;
    		margin-top: .5rem;
       }
        .custom-margin{
        	margin: 1.35rem 0 2.5rem 0;
        }
	    table:not(.datepicker-table) thead tr {
	        background-color: #2E58AD;
	        background-color: #007a7a;
	     }
	     .profile_info table tbody tr td:first-of-type{
	     	width:30%;
	     }
	     table:not(.datepicker-table) td{
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
		.m-1 {
		    margin: .5rem auto;
		}
		.mt-20px{
			margin-top:20px;
		}
		.row.no-mar{margin-bottom:0;}
		div.center-align.m-1 button.bg-m, div.center-align.m-1 button.bg-s{width:auto;}
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
		
        .main .left i{
        	margin:0 5px;
        	transition:color .3s linear;
        }
        .main i:hover{
        	color:#007A7A;
        }
        .main .hidden{
        	display:none;
        }
        .bg-m{
	    	background-color:#007a7a;
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
		.no-sort.sorting_asc:before,
		.no-sort.sorting_asc:after{
			opacity:0 !important;
			content:'' !important;
		}
		.select2-container--default .select2-selection--single{
			background-color:transparent;
		}
	</style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card custom-margin">
                <div class="card-content"> 
                <form action="<%=request.getContextPath() %>/update-profile" method="POST" id="profileForm" name="profileForm" class="form-horizontal" role="form" enctype="multipart/form-data">
                	<span class="card-title headbg main">
                		User Details 
                		<span class="left">
                		<a class="btn bg-m editing" onclick="toggleEditing()">Edit</a> 
                		<i class="fa fa-save saving hidden" onclick='profileFormSubmit()'></i>
                		<i class="fa fa-close closing hidden" onclick="toggleEditing()"></i>
                		</span>
                		
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
                      <%--   asked to remove so this was commented
                      	<div class="col m12 l4 s12">
                            <div class="card" style="min-height: 445px;">
                                <div class="card-content">
                                    <span class="card-title headbg">User Access</span>
                                    <table id="example2" class="mdl-data-table">
                                        <thead>
                                            <tr>
                                                <th class="no-sort">Access Type</th>
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
                        </div> --%>
                        
                         <div class="col m12 l4 s12" style="display:none;">
                            <div class="card" style="min-height: 445px;">
                                <div class="card-content">
                                    <span class="card-title headbg">Leave Responsibility</span>
                                    <div class="row">
									    <div class="col s12">
									        <form action="">
									            <p class="center-align text-capitalize">are you going on leave 
									                <label style="margin-left:2rem;">
									                    <input type="checkbox" name="leaveYes" onchange="datesShowHide()"/>
									                    <span>Yes</span>
									                </label>
									            </p>
									            <div id="datesDiv" style="display:none;">
									            	<div class="input-field col s6">
										                <input type="text" class="datepicker" id="from_date" placeholder="From Date">
										                <button type="button" id="from_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
										            </div>
										            <div class="input-field col s6">
										                <input type="text" class="datepicker" id="to_date" placeholder="To Date">
										                <button type="button" id="to_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
										            </div>
									            </div>
									            <div class="row fixed-width no-mar" id="responsibleDiv" style="display:none;">
												    <h6 class="center-align">Assign Responsibility</h6>
												    <div class="table-inside">   
													    <table class="mdl-data-table mobile_responsible_table" id="responsibility_table">
													        <thead>
													            <tr>
													                <th>Module</th>
													                <th>Responsible Person</th> 
													                <th>Action</th>
													            </tr>
													        </thead>
													        <tbody id="responsibilityBody">
													            <tr id="tableRow0">
													                <td data-head="Module" class="input-field">
													                    <select name="modules" id="module0" onChange="removeRest()" class="validate-dropdown searchable">
													                        <option value="all">All</option>
													                        <option value="contract" selected>Contract</option>
													                    </select>
													                </td>
													                <td data-head="Responsible Person" class="input-field">
													                    <select name="responsible_person" id="responsible_person0" class="validate-dropdown searchable">
													                        <option value="">User</option>
													                    </select>
													                </td>
													                <td class="mobile_btn_close">
													                    <a onclick="removeRow('0');" class="btn red">
													                        <i class="fa fa-close"></i></a>
													                </td>
													            </tr>
													        </tbody>
													    </table>
													    
													    <div class="table-plus-btn">
													    	<input type="hidden" id="rowNo" name="rowNo" value="0" />
													        <a type="button" class="btn waves-effect waves-light bg-m t-c add-align" onclick="addNewRow()"> <i
													                class="fa fa-plus"></i>
													        </a>
													    </div>
													  </div>
												</div>
												<div class="row no-mar" id="btnDiv" style="display:none;">
					                                <div class="col s6 mt-brdr mt-20px">
					                                    <div class="center-align m-1">
						                                       <button type="button" class="btn waves-effect waves-light bg-m">Update</button>
					                                    </div>
					                                </div>
					                                <div class="col s6 mt-brdr mt-20px">
					                                    <div class="center-align m-1">
					                                        <a href="#!" class="btn waves-effect waves-light bg-s" >Cancel</a>
					                                    </div>
					                                </div>
					                            </div>
									        </form>
									    </div>
									</div>
                                  
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
  <script src="/pmis/resources/js/datepickerDepedency.js"></script>
  <script src="/pmis/resources/js/select2.min.js"></script>
  <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
  <script src="/pmis/resources/js/dataTables.material.min.js"></script>
  <script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	  <script>
       /*  $(document).ready(function () {
            $('#example2').DataTable({
                "searching": false,
                columnDefs: [
                    {
                        targets: [],
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
                "ordering":false,
                "sScrollY": 400,
            });
        }); */
    	$(document).ready(function () {
        	$('.searchable').select2();
    	});
        function datesShowHide(){
        	 if($('input[name="leaveYes"]:checked').val()){
        		 $('#datesDiv,#btnDiv').show();
        		 $('#responsibleDiv').show();        		 
        	 }else{
        		 $('#datesDiv,#btnDiv').hide();
        		 $('#responsibleDiv').hide();
        	 }
        }
        function toggleEditing(){
        	$('.main .fa,.main .editing').toggleClass('hidden');
        	$('.hideOrShow').toggleClass('hidden');      	       	
        }
        
        function addNewRow(){
            var rowNo = $("#rowNo").val();
            var rNo = Number(rowNo)+1;
            var html='<tr id="tableRow' + rNo + '"> <td data-head="Module" class="input-field">'
                     +'<select name="modules" id="module' + rNo + '" class="validate-dropdown searchable"> <option value="all">All</option><option value="1">one</option>'
                     +'</select></td> <td data-head="Responsible Person" class="input-field">'
                     +'<select name="responsible_person" id="responsible_person' + rNo + '" class="validate-dropdown searchable"> <option value="">User</option>'
                     +'</select> </td> <td class="mobile_btn_close"> <a onclick="removeRow(' + rNo + ');" class="btn red"> <i class="fa fa-close"></i></a>'
                     +'</td></tr>';	
    		$('#responsibilityBody').append(html);
            $("#rowNo").val(rNo);          	
            
            $('.searchable').select2();
        }
        
        function removeRow(rowNo){
         	$("#tableRow"+rowNo).remove();
        } 
        
        function removeRest(){
        	if($('#module0').val()=='all'){
        		$('#responsibilityBody > tr:not("#tableRow0")').hide();
        		$('.table-plus-btn > .btn').attr('disabled',true);
           	}else{
           		$('#responsibilityBody > tr:not("#tableRow0")').show();
        		$('.table-plus-btn > .btn').attr('disabled',false);
        	}
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