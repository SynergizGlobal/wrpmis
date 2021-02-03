<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile</title>
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
        .error-msg{        color:red		}   
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
    </style>
</head>

<body>
  <!-- header included -->
  <jsp:include page="./layout/header.jsp"></jsp:include>

    <div class="row">
        <div class="col s12 m12">
            <div class="card">
                <div class="card-content"> 
                <form action="#" method="POST" id="profile_form" enctype="multipart/form-data">
                	<span class="card-title headbg main">
                		User Details 
                		<i class="fa fa-pencil editing" onclick="toggleEditing()"></i> 
                		<i class="fa fa-save saving hidden" onclick='profileFormSubmit()'></i>
                		<i class="fa fa-close closing hidden" onclick="toggleEditing()"></i>
                	</span>
                    <div class="row">                   
                        <div class="col m4 s12 center-align">
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
													<span>Change Image</span> <input type="file" name="user_image_file" id="user_image_file"
														accept='image/*'>
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text">
												</div>
												<span id="user_nameError" class="error-msg"></span>
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
                        <div class="col m4 s12">
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
											            <td>Email</td>
											            <td> 
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.email_id }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="email_id" id="email_id" type="email" class="validate">
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
											            		<input name="mobile_number" id="mobile_number" type="number" class="validate">
											            		<span id="mobile_numberError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Personal Contact Number</td>
											            <td>											            
											           		<span class="hideOrShow">: &nbsp; ${ userDetails.personal_contact_number }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="personal_contact_number" id="personal_contact_number" type="number" class="validate">
											            		<span id="personal_contact_numberError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Land line</td>
											            <td>
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.landline }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="landline" id="landline" type="number" class="validate">
											            		<span id="landlineError" class="error-msg"></span>
											            	</span>
											            </td>
											        </tr>
											        <tr>
											            <td>Extension</td>
											            <td>
											            	<span class="hideOrShow">: &nbsp; ${ userDetails.extension }</span>
                                    						<span class="hideOrShow input-field hidden"> 											            	
											            		<input name="extension" id="extension" type="number" class="validate">
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
                        <div class="col m4 s12">
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
	        	$('form input[name=months]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_fy_crs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_planned_expenditure_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_crs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_expenditure_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_planned_physical_progress_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=cum_actual_physical_progress_pers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=progresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=issues]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=assistance_requireds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });

	   			document.getElementById("zonalRailwayForm").submit();	
        	}
        }
        
        var validator =	$('#profile_form').validate({
	  		    rules: {
	  		 		   "project_id_fk": {
	  			 		  required: true
	  			 	  },"work_id_fk": {
	  			 		  required: true
	  			 	  },"execution_agency_railway_fk": {
	  			 		  required: true
	  			 	  },"sanction_cost": {
	  			 		  required: false
	  			 	  }	,"latest_revised_cost": {
	  			 		  required: false
	  			 	  }	,"cumulative_expenditure_upto_last_finacial_year": {
	  			 		  required: false
	  			 	  }	,"completion_cost": {
	  			 		  required: false
	  			 	  }		
	  		 	},
	  		    messages: {
	  		 		   "project_id_fk": {
	  			 		  required: 'Required'								
	  			 	  },"work_id_fk": {
	  			 		  required: 'Required'
	  			 	  }	,"execution_agency_railway_fk": {
	  			 		  required: 'Required'
	  			 	  },"sanction_cost": {
	  			 		  required: 'Required'
	  			 	  },"latest_revised_cost": {
	  			 		  required: 'Required'
	  			 	  },"cumulative_expenditure_upto_last_finacial_year": {
	  			 		  required: 'Required'
	  			 	  },"completion_cost": {
	  			 		  required: 'Required'
	  			 	  }	
		   		},
		   		errorPlacement:function(error, element){
		   		 	  if(element.attr("id") == "project_id_fk" ){
					     document.getElementById("project_id_fkError").innerHTML="";
				 	     error.appendTo('#project_id_fkError');
					 }else if(element.attr("id") == "work_id_fk" ){
					     document.getElementById("work_id_fkError").innerHTML="";
				 	     error.appendTo('#work_id_fkError');
					 }else if(element.attr("id") == "execution_agency_railway_fk" ){
					     document.getElementById("execution_agency_railway_fkError").innerHTML="";
				 	     error.appendTo('#execution_agency_railway_fkError');
					 }else if(element.attr("id") == "sanction_cost" ){
					     document.getElementById("sanction_costError").innerHTML="";
				 	     error.appendTo('#sanction_costError');
					 }else if(element.attr("id") == "latest_revised_cost" ){
					     document.getElementById("latest_revised_costError").innerHTML="";
				 	     error.appendTo('#latest_revised_costError');
					 }else if(element.attr("id") == "cumilative_expenditure" ){
					     document.getElementById("cumulative_expenditure_upto_last_finacial_yearError").innerHTML="";
				 	     error.appendTo('#cumulative_expenditure_upto_last_finacial_yearError');
					 }else if(element.attr("id") == "completion_cost" ){
					     document.getElementById("completion_costError").innerHTML="";
				 	     error.appendTo('#completion_costError');
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