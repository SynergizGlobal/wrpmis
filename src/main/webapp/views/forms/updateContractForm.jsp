<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Edit Contract - Update Forms - PMIS</title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">    
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <link rel="stylesheet" href="/pmis/resources/css/contract.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" />
    <link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-responsive-table.css" />
    <style>
 
        #ravTable .datepicker~button,
        #insurenceTable .datepicker~button,
        #bankTable .datepicker~button,
        #mileTable .datepicker~button {
            top: 26px;
        }

        .datepicker-table thead tr,
        .datepicker-table thead tr:hover,
        .datepicker-table tbody tr,
        .datepicker-table tbody tr:hover {
            background-color: transparent;
            border-radius: 0;
            border-bottom-width: 0;
        }

        .datepicker-table td:first-of-type,
        .datepicker-table td:last-of-type {
            padding: 0 !important;
        }

        .datepicker-table th,
        .datepicker-table td {
            padding: 0 !important;
        }

        .radiogroup {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        #example3 input[type="text"]::-webkit-input-placeholder,
        #example3 input[type="text"]:-ms-input-placeholder,
        #example3 input[type="text"]::placeholder {
            /* Edge */
            color: #777;
        }

        .fixed-width {
            width: 100%;
            margin-left:auto !important;
            margin-right:auto !important;
            margin-bottom:0;
        }

        .fixed-width .table-inside {
            width: 100%;
            overflow: auto;
        }
        .input-field p.searchable_label{
        	color: #7BC2B9;
        }
       .row.no-mar{ margin-bottom:0;}
	    @media only screen and (max-width: 600px) {
          .no-float-small {
              float: none !important;
          }
     	 }
     	 .my-error-class {
   			 color:red;
		}
		.my-error {
   			 color:red;
   			 font-size: .8rem;
		}
		.my-valid-class {
   			 color:green;
		}
		.mt-10{
			margin-top:10px;
		}
					
		@media only screen and (min-width: 769px){
			#insurenceTableBody .select2-container{
				max-width:134px
			}
			#bankTableBody .select2-container{
				max-width:140px;
				width:130px;
			}
			#bankTableBody th.fs-100,
			#bankTableBody td.fs-100{
				max-width:100px;
				width:90px;
			}
		}
		#insurenceTableBody td.input-field .prefix,
		#revTableBody td.input-field .prefix,
		#bankTableBody td.input-field .prefix {
    		top: 1.5rem;
		}

		.filevalue {
            display: block;
            margin-top: 10px;
        }
        #insurance_div,#bank_guarantee_div{
        	margin:0
        }
        .table-inside td .btn{
        	padding:6px 12px;
        	line-height:20px;
        	height:auto;
        }
         .table-inside td .btn .fa{
         	font-size:1rem;
         }
         
         .table-inside .normal-btn .btn{
         	padding:0 16px;
        	line-height:36px;
        	height:36px;
         }
         .table-inside .normal-btn .btn .fa{
         	font-size:1.3rem;
         }
         .input-field .searchable_label{
         	font-size: 0.85rem;
         }
         .select2-container--default .select2-selection--single {
	    	background-color: transparent;
	     }
	     #bankTable thead th, 
	     #bankTable tbody td,	      
	     #insurenceTable thead th, 
	     #insurenceTable tbody td{
	     	padding:12px;
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
		.input-field>textarea+label:not(.label-icon).active{
			margin-top: 8px;
		}
		/* cost unit dropdown , lable and input styling starts here  */
		.pt-5{
			padding-top:5px !important;
		}
		.pt-14{
			padding-top:14px !important;
		}
		.cost_dropdown{
			min-width:95px !important;
		}
		.input-field .prefix.cost ~ input,
		.input-field .prefix.cost ~ label,
		.input-field .prefix.cost ~ .validate ~ label {
		    margin-left: 2rem;
		    width: 92%;
		    width: calc(100% - 2rem);
		}
		.input-field.col .prefix.cost ~ label,
		.input-field.col .prefix.cost ~ .validate ~ label {
		    width: calc(100% - 2rem - 1.5rem);
		}
		.input-field .prefix.cost {
			width:2rem;
		}	
		.input-field.no-box-shadow input[type="text"]{
			box-shadow:none !important;
			border:none;
		}
		/* responsive_units styling starts */
		.responsive_units {
			min-width:50px;			
		}
		.responsive_units > .select2-container{
			max-width:50px !important;
		}
		/* responsive_units styling ends */
		@media only screen and (max-width: 769px){
			.mobile_responsible_table>tbody> tr:not(.datepicker-row):not(.mobile_hide_row) {
			    border-bottom: 3px solid #2A9D8F;
			}
			.h-auto{
				height:auto !important;
			}
			.responsive_units > .select2-container{
				max-width:inherit !important;
			}
			.input-field p.searchable_label {
			    margin-top: -24px !important;
			}
			 .filevalue {
			    width: 200%;
			    white-space: break-spaces;
			}
			#ravTable .datepicker~button, 
			#insurenceTable .datepicker~button, 
			#bankTable .datepicker~button, 
			#mileTable .datepicker~button {
			     top: 0;
			}
			.mobile_responsible_table>tbody tr td.mobile_btn_close a{
				margin-right:-22px;
			}
			td.input-field > p{
				display:inline-block;
			}
			.responsive_units > .select2-container{
				max-width:100%;
			}
			.mobile_responsible_table tbody tr td .select2-container{
				width:93% !important;
			}
			.input-field .prefix.cost ~ input{
				width: calc(90% - 2rem);
			}
			.mobile_responsible_table.another>tbody > tr:not(.datepicker-row) >td {
			    width: 53%;
			}
		}
		.validate:focus{ scroll-behavior:smooth;}
		/* cost unit dropdown , lable and input styling ends here  */
		
		/* special columns of revision table css starts here  */
		/* .light-green_column {
			background-color:#CFDBF2
		}
		.light-green_column {
			background-color:#DFF7F4
		}	 */		
		th.light-blue_column {
			color:#444 !important;
			background-color:#7DBCC1;
		}	
		th.light-green_column {
			color:#444 !important;
			background-color:#85CAC2;
		}
	/* 	tr:hover td. {
			background-color:hsl(219, 57%, 92%)
		}
		tr:hover td. {
			background-color:hsl(173, 60%, 96%)
		} */		
		/* .  input[type="number"]::placeholder ,
		.  input[type="text"].datepicker::placeholder{
            color: #bbb;
        } */
        .p-h-0{
        	padding-left:0 !important;
        	padding-right:10px !important;
        }
		/* special columns of revision table css ends here  */	
		h5{margin-top:0;}
		/* .input-field input[type="text"],
		.input-field .pmis-textarea{
        	margin-bottom:0;
        } */
        
        [type="radio"]+span:before, [type="radio"]+span:after {
        	left:12px!important;
        }
    </style>
</head>

<body>

    <!-- header  starts-->
             <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>Update Contract</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
<!--                     <div class="container container-no-margin"> -->
                        <form action="<%=request.getContextPath() %>/update-contract" id="contractForm" name="contractForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                        	
                        	 <div class="row">
							    <div class="col s12 m12">
							      <ul class="tabs tab-flex">
							        <li class="tab"><a class="active t-c" href="#basicDetails">Basic</a></li>
							        <li class="tab"><a class="t-c" href="#departmentDetails">Departments</a></li>
							        <li class="tab"><a class="t-c" href="#contractDetails">Contracts Details</a></li>
							        <li class="tab"><a class="t-c" href="#bgDetails">Bank Guarantee</a></li>
							        <li class="tab"><a class="t-c" href="#insuranceDetails">Insurance</a></li>
							        <li class="tab"><a class="t-c" href="#milestoneDetails">Milestones</a></li>
							        <li class="tab"><a class="t-c" href="#documentDetails">Documents</a></li>
							        <li class="tab"><a class="t-c" href="#revisionDetails">Revisions</a></li>
							        <li class="tab"><a class="t-c" href="#keyPersonDetails">Key Persons</a></li>
							        <li class="tab"><a class="t-c" href="#closureDetails" id="closureTab">Closures</a></li>
							      </ul>
							    </div>							    
						   </div>
                        	
							<div class="container container-no-margin" id="basicDetails">
								<div class="row" style="margin-bottom: 50px;">
	                                <div class="col s12 m4 input-field no-box-shadow offset-m2">
	                                    <label class="primary-text-bold ">Contract ID : <input id="contract_id" name="contract_id" type="text" value="${contractDeatils.contract_id }"  style="background-color: none;border: none; border-bottom: 0px solid #4CAF50;webkit-box-shadow: 0 0px 0 0 #4CAF50;box-shadow: 0 0px 0 0 #4CAF50;height: 20px;width:60%;"></label>
	                                </div>
	                            </div>
							
	                            <div class="row">
	                                <div class="col s6 m4 input-field offset-m2">
	                                	<p class="searchable_label">Project <span class="required">*</span></p>
                               			 <input type="text"  value="${contractDeatils.project_id_fk} - ${contractDeatils.project_name}" readonly />
                                      	 <input type="hidden" name="project_id_fk" id="project_id_fk" value="${contractDeatils.project_id_fk}" readonly />
	                                </div>
	                                <div class="col s6 m4 input-field">
	                                    <p class="searchable_label">Work <span class="required">*</span></p>
                                    	<input type="text"  value="${contractDeatils.work_id_fk} - ${contractDeatils.work_name}" readonly />
                                        <input type="hidden" name="work_id_fk" id="work_id_fk" value="${contractDeatils.work_id_fk}" readonly />
                                    	<input type="hidden" name="work_name" value="${contractDeatils.work_name}" />
                                    	<input type="hidden" name="work_short_name" value="${contractDeatils.work_short_name}" />
	                                </div>	
	                            </div>
	                             <div class="row">
	                                <div class="col s12 m8 offset-m2">
	                                    <div class="row">
	                                    <c:choose>
								         <c:when test = "${sessionScope.USER_ROLE_NAME eq 'IT Admin'}">
								         <div class="col s6 m6 input-field">
								            <p class="searchable_label">HOD<span class="required">*</span></p>
	                                            <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown searchable"> 
	                                     		  <option value="">Select</option> 
	                                     		  	<c:forEach var="obj" items="${hodList }"> 
			                                    	  <option value="${obj.user_id }" <c:if test="${contractDeatils.hod_user_id_fk eq obj.user_id}">selected</c:if> > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                        </c:forEach> 
	                                     	     </select> 
	                                     	<span id="hod_user_id_fkError" class="error-msg" ></span>
	                                     	</div>
	                                     	 <div class="col s6 m6 input-field">
	                                        	<p class="searchable_label">Dy HOD<span class="required">*</span></p>
	                                            <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown searchable">
	                                                <option value="">Select</option>
	                                                 <c:forEach var="obj" items="${dyHodList }"> 
			                                    	  <option value="${obj.user_id }" <c:if test="${contractDeatils.dy_hod_user_id_fk eq obj.user_id}">selected</c:if> > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
			                                         </c:forEach>   
	                                            </select>
												<!-- <input name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" type="text" class="validate" style="margin-top:10px">
	                               		     	<label for="dy_hod_user_id_fk">Dy HOD</label> -->
	                                            <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
	                                        </div>
								         </c:when>
								         <c:otherwise>
	                                        <div class="col s6 m6 input-field">
			                                	<p class="searchable_label">HOD <span class="required">*</span></p>
		                               			 <c:choose>
				                                    <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }">
				                                		 <select name="hod_user_id_fk" id="hod_user_id_fk" class="validate-dropdown searchable" onchange="getDyHodList();"> 
	                                     		  			<option value="">Select</option> 
			                                                 <%-- <c:forEach var="obj" items="${hodList }"> 
					                                    	  <option value="${obj.user_id }" <c:if test="${sessionScope.USER_ID eq obj.user_id}">selected</c:if> > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
					                                        </c:forEach>  --%>  
	                                            		</select>
	                                            		<span id="hod_user_id_fkError" class="error-msg" ></span>
				                                	</c:when>
				                                 	<c:otherwise>
				                                 		 <input type="text"  value="${contractDeatils.hod_designation }<c:if test="${not empty contractDeatils.hod_name}"> - </c:if>${contractDeatils.hod_name}"  readonly />
		                                      	 		 <input type="hidden" name="hod_user_id_fk" id="hod_user_id_fk" value="${contractDeatils.hod_user_id_fk}" readonly />
				                                 	</c:otherwise>
				                                 </c:choose>
		                               			
			                                </div>
			                                <div class="col s6 m6 input-field">
			                                    <p class="searchable_label">Dy HOD <span class="required">*</span></p>
			                                    <c:choose>
				                                    <c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' }">
				                                		  <select name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" class="validate-dropdown searchable" onchange="getHodList();">
			                                                <option value="">Select</option>
			                                                 <%--  <c:forEach var="obj" items="${dyHodList }"> 
					                                    	  <option value="${obj.user_id }" > ${obj.designation }<c:if test="${not empty obj.user_name}"> - </c:if>${obj.user_name}</option> 
					                                        </c:forEach>   --%> 
			                                              </select>
			                                              <span id="dy_hod_user_id_fkError" class="error-msg" ></span>
				                                	</c:when>
				                                 	<c:otherwise>
				                                    	<input type="text"  value="${contractDeatils.dy_hod_designation }<c:if test="${not empty contractDeatils.dy_hod_name}"> - </c:if>${contractDeatils.dy_hod_name}" readonly />
				                                        <input type="hidden" name="dy_hod_user_id_fk" id="dy_hod_user_id_fk" value="${contractDeatils.dy_hod_user_id_fk}" readonly />
				                                 	</c:otherwise>
				                                 </c:choose>
			                                </div>
			                               </c:otherwise>
								        </c:choose>	
	                                    </div>
	                                </div>	
	                            </div>
							
								<c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' }"><br> </c:if>
							<div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <textarea id="contract_name" name ="contract_name" class="pmis-textarea validate-dropdown" data-length="1000"
	                                    <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}"> readonly </c:if>> ${contractDeatils.contract_name }</textarea>
	                                    <label for="contract_name">Contract Name <span class="required">*</span></label>
	                                    <span id="contract_nameError" class="error-msg" ></span>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <input name="contract_short_name" id="contract_short_name" type="text" class="validate validate-dropdown" 
	                                    <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}"> readonly </c:if>
	                                     value="${contractDeatils.contract_short_name }">
	                                    <label for="contract_short_name">Contract Short Name <span class="required">*</span></label>
	                                      <span id="contract_short_nameError" class="error-msg" ></span>
	                                </div>
	                            </div>
							<div class="row">
								  <c:choose>
						         	<c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">
							         
		                                <div class="col s6 m4 input-field offset-m2">
		                                 <p class="searchable_label">Contract Type <span class="required">*</span></p>
		                                    <select name="contract_type_fk" id="contract_type_fk" class="validate-dropdown searchable">
		                                        <option value="" selected>Select</option>
		                                       	   <c:forEach var="obj" items="${contract_type }">
				                                     <option value="${obj.contract_type_fk }" <c:if test="${contractDeatils.contract_type_fk eq obj.contract_type_fk}">selected</c:if>>${obj.contract_type_fk }</option>
				                                   </c:forEach>
		                                    </select>                                   
		                                     <span id="contract_type_fkError" class="error-msg" ></span>
		                                </div>
		                                <div class="col s6 m4 input-field">
		                                    <p class="searchable_label">Contractor Name <span class="required">*</span></p>
		                                    <select name="contractor_id_fk" id="contractor_id_fk" class="validate-dropdown searchable">
		                                        <option value="" selected>Select</option>
		                                       	    <c:forEach var="obj" items="${contractor }">
				                                      <option value="${obj.contractor_id_fk }" <c:if test="${contractDeatils.contractor_id_fk eq obj.contractor_id_fk}">selected</c:if>>${obj.contractor_name }</option>
				                                    </c:forEach>
		                                    </select>
		                            		<span id="contractor_id_fkError" class="error-msg" ></span>                                    
		                                </div>  
		                              </c:when>
						           <c:otherwise>
						         	 <div class="col s6 m4 input-field offset-m2">
		                                <input name="contract_type_fk" id="contract_type_fk" type="text" 
	                                    <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}"> readonly </c:if>
	                                     value="${contractDeatils.contract_type_fk }">
	                                    <label for="contract_type_fk">Contract Type</label>
		                             </div>
						         	<div class="col s6 m4 input-field">
						         	 	<input name="contractor_id_fk" id="contractor_id_fk" type="hidden" value="${contractDeatils.contractor_id_fk }"/>
		                                <input type="text" <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}"> readonly </c:if>
	                                     value="${contractDeatils.contractor_name }">
	                                    <label for="contractor_id_fk">Contractor Name </label>                         
		                             </div>  
						         </c:otherwise>
						      </c:choose>
	                                                 
		                     </div>
						        
	                            <div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <textarea id="scope_of_contract" name="scope_of_contract" class="pmis-textarea validate" data-length="1000" 
	                                    <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD' &&  sessionScope.USER_TYPE ne 'DyHOD'}"> readonly </c:if>
	                                    >${contractDeatils.scope_of_contract }</textarea>
	                                    <label for="scope_of_contract">Scope of Contract</label>
	                                    <span id="scope_of_contractError" class="error-msg" ></span>
	                                </div>
	                             </div>
	                        </div>
	                        <div class="container container-no-margin" id="contractDetails">
	                             <div class="row">
	                                <div class="col s6 m4 input-field offset-m2">
	                                    <input id="loa_letter_number" name="loa_letter_number" type="text" class="validate" value="${contractDeatils.loa_letter_number }">
	                                    <label for="loa_letter_number">LOA Letter No</label>
	                                    <span id="loa_letter_numberError" class="error-msg" ></span>
	                                   
	                                </div>
	                                <div class="col s6 m4 input-field">
	                                    <input id="loa_date" name="loa_date" type="text" class="validate datepicker" value="${contractDeatils.loa_date }">
	                                    <label for="loa_date">LOA Date</label>
	                                    <span id="loa_dateError" class="error-msg" ></span>
	                                    <button type="button" id="loa_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                            </div>
	                            <div class="row">
	                                <div class="col s6 m4 input-field offset-m2">
	                                    <input id="ca_no" name="ca_no" type="text" class="validate" value="${contractDeatils.ca_no }">
	                                    <label for="ca_no">CA No</label>
	                                     <span id="ca_noError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s6 m4 input-field">
	                                    <input id="ca_date" name="ca_date" type="text" class="validate datepicker" value="${contractDeatils.ca_date }">
	                                    <label for="ca_date">CA Date</label>
	                                     <span id="ca_dateError" class="error-msg" ></span>
	                                    <button type="button" id="ca_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                            </div>
	                            <div class="row">	                                
	                                <div class="col s12 m4 input-field offset-m2">
	                                    <input id="date_of_start" name="date_of_start" type="text" class="validate validate-dropdown datepicker" value="${contractDeatils.date_of_start }">
	                                    <label for="date_of_start">Date of Start <span class="required">*</span></label>
	                                     <span id="date_of_startError" class="error-msg" ></span>
	                                    <button type="button" id="date_of_start_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s9 m3 input-field">
	                                    <i class="material-icons prefix cost left-align">₹</i>
	                                    <input id="estimated_cost" name="estimated_cost" min="0.01" step="0.01" type="number" class="validate" value="${contractDeatils.estimated_cost }">
	                                    <label for="estimated_cost"> Detailed Estimated cost</label>
	                                    <span id="estimated_costError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s3 m1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units validate-dropdown" id="estimated_cost_units" name="estimated_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${contractDeatils.estimated_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		    </c:forEach>
	                                	</select>
	                                	<span id="estimated_cost_unitsError" class="error-msg" ></span>
                                	</div>	                         
                                </div>      
	                           	<div class="row">
	                                <div class="col s12 m4 input-field offset-m2">
	                                    <input name="doc" id="doc" type="text" class="validate datepicker" value="${contractDeatils.doc }" >
	                                    <label for="doc">Original DOC</label>
	                                     <button type="button" id="doc_icon"><i class="fa fa-calendar"></i></button>
	                                     <span id="docError" class="error-msg" ></span>
	                                </div>
	                                   <div class="col s9 m3 input-field ">
	                                	<i class="material-icons prefix cost left-align">₹</i>
	                                    <input id="awarded_cost" name="awarded_cost" min="0.01" step="0.01" type="number" class="validate" value="${contractDeatils.awarded_cost }" />
	                                    <label for="awarded_cost">Awarded cost</label>
	                                    <span id="awarded_costError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s3 m1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units validate-dropdown" id="awarded_cost_units" name="awarded_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
                                  			   <option value="${obj.value }" <c:if test="${contractDeatils.awarded_cost_units eq obj.value}">selected</c:if>>${obj.unit }</option>
                                   		    </c:forEach>
	                                	</select>
	                                	<span id="awarded_cost_unitsError" class="error-msg" ></span>
                                	</div>
	                                </div>
	                                <div class="row">
	                                <div class="col s6 m4 input-field offset-m2">
	                                    <input name="target_doc" id="target_doc" type="text" class="validate datepicker" value="${contractDeatils.target_doc }" >
	                                    <label for="target_doc">Target DOC</label>
	                                     <button type="button" id="target_doc_icon"><i class="fa fa-calendar"></i></button>
	                                     <span id="target_docError" class="error-msg" ></span>
	                                </div>
	                                                             
	                                <div class="col s6 m2 input-field">
	                                   <p class="searchable_label">Contract Status</p>
	                                    <select name = "contract_status" id="contract_status" class="validate-dropdown searchable" onchange="getContractClosureDetails('');getStatusLIst();">
	                                        <option value="" >Select</option>
	                                          <c:forEach var="obj" items="${contract_Status }">
		                                    	 <option value="${obj.contract_status }"<c:if test="${contractDeatils.status eq obj.contract_status}">selected</c:if>>${obj.contract_status }</option>
		                                     </c:forEach>     
	                                    </select>
	                                     <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div>
	                                 <div class="col s6 m2 input-field">
	                                   <p class="searchable_label">Status of Work</p>
	                                    <select name = "contract_status_fk" id="contract_status_fk" class="validate-dropdown searchable" onchange="getContractClosureDetails(this.value);setContractStatus();">
	                                        <option value="" selected>Select</option>
	                                           <c:forEach var="obj" items="${contract_Statustype }">
			                                    	<option status="${obj.contract_status }" value="${obj.contract_status_fk }" <c:if test="${contractDeatils.contract_status_fk eq obj.contract_status_fk}">selected</c:if>>${obj.contract_status_fk }</option>
			                                    </c:forEach>
	                                    </select>
	                                     <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div>
	                            </div>
	                            <div class="row" id="contractClosureRadioBtn" style="display: none;">
	                            	<div class="col s12 m6 input-field offset-m3">
			                   			<div class="col l8 m12">
			                   				<p style="text-align: right;">Contract Closed/Closure initiated? </p>
			                   			</div>
			                   			<div class="col l4 m12 right-align mob-center">                   				
									      <label>
									        <input id="contractClosureRadioYes" name="contractClosureRadio" class="with-gap" type="radio" value="yes"/>
									        <span>Yes</span>
									      </label>					    
									      <label>
									        <input id="contractClosureRadioNo" name="contractClosureRadio" class="with-gap" type="radio" value="no"/>
									        <span>No</span>
									      </label>							    
			                   			</div>
		                   			</div>
		                   		</div> 
	                            <div class="row">
	                                <div class="col s12 m8 input-field offset-m2">
	                                    <textarea id="remarks" name ="remarks" class="pmis-textarea" data-length="1000">${contractDeatils.remarks }</textarea>
	                                    <label for="remarks">Remarks</label>
	                                    <span id="remarksError" class="error-msg"></span>
	                                </div>
	                            </div>
	                          </div>
	                          
	                           <div class="container container-no-margin" id="departmentDetails">
	                            <div class="row"> 
	                            	<div class="col m8 offset-m2 s12">
										<div class="row fixed-width">
									      <!--   <h5 class="center-align">Department Details</h5> -->
									        <div class="table-inside">
									            <table id="departmentTable" class="mdl-data-table mobile_responsible_table" >
									                <thead>
									                    <tr>
									                        <th style="width:22%">Department <span class="required">*</span></th>
															<th style="text-align : center;">Select Executives <span class="required">*</span></th>
															<c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}"><th style="width:8%">Action</th></c:if>
									                    </tr>
									                </thead>
									                <tbody id="departmentTableBody">
									                <c:choose>
				                                        <c:when test="${not empty contractDeatils.departmentList }" >
				                                          
				                                		  <c:forEach var="departmentObj" items="${contractDeatils.departmentList }" varStatus="index"> 
				                                		    <c:choose>
								         					<c:when test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD' ||  sessionScope.USER_TYPE eq 'DyHOD'}">  
											                  <tr id="departmentRow${index.count }">
											                        <td data-head="Department" class="input-field">
											                             <select class="searchable validate-dropdown" name="department_fks" id="department_fks${index.count }"
											                                id="department_fk${index.count }" onchange="getExecutivesList('${index.count }');">
											                                	<option value="" >Select</option>  
																		          <c:forEach var="obj" items="${departmentList }">
										                                      	    <option value= "${ obj.department_fk}" <c:if test="${departmentObj.department_id_fk eq obj.department_fk}">selected</c:if>>${ obj.department_name}</option>
										                                          </c:forEach>
											                              </select> 
											                              <span id="deptError${index.count }" class="my-error"></span>
											                        </td>
											                        <td data-head="Select Executives" class="input-field h-auto">
											                            <select class="searchable validate-dropdown dept" name="responsible_people_id_fks" id="responsible_people_id_fks${index.count }" onchange="fileCount('${index.count }');"
											                             multiple="multiple">
											                             <option value="" >Select</option>
											                             <c:forEach var="obj" items="${departmentObj.responsiblePersonsList}">
											                             <option value="${obj.hod_user_id_fk }" 
																		 		<c:forEach var="tempobj" items="${departmentObj.executivesList}">
																		 			<c:if test="${tempobj.executive_user_id_fk eq obj.hod_user_id_fk}">selected</c:if>
									                                          	</c:forEach>
																	 		>${obj.designation} - ${obj.user_name}</option>
									                                         </c:forEach>
											                            </select>
											                             <span id="personError${index.count }" class="my-error" ></span>
											                            <input type="hidden" id="filecounts${index.count }" name="filecounts">
											                            <script>
											                            	var count = $("#responsible_people_id_fks${index.count } :selected").length;
											                            	var s = $('#filecounts${index.count}').val(count);
											                            </script>
											                        </td>
											                        <td class="mobile_btn_close">
											                            <a onclick="removeDepartment('${index.count }');"
											                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
											                        </td>
											                    </tr>
											                    </c:when>
				                                 			<c:otherwise>
				                                 			 <tr id="departmentRow${index.count }">
					                                 			 <td>
					                                 				<input type="text"  id="department_fk${index.count }" value="${departmentObj.department_name }"  readonly/>
					                                 				<input type="hidden" name="department_fks" id="department_fks${index.count }"  value="${departmentObj.department_fk }"  /></td>
					                                 			<td>
					                                 				<div id="container${index.count }">
															 		<c:forEach var="tempobj" items="${departmentObj.executivesList}" varStatus="indexx" >
															 				<input type="hidden" name="responsible_people_id_fks"  value="${tempobj.executive_user_id_fk }"  />
															 				<p >${tempobj.designation} - ${tempobj.user_name}</p><br>
						                                          	</c:forEach>
						                                          	</div>
						                                          	<input type="hidden" id="filecounts${index.count }" name="filecounts">
														 				 <script>
																 				var inputs = $("#container${index.count}").find($("input") );
											                            		var len = inputs.length;
											                            		$('#filecounts${index.count}').val(len/2);
											                            </script>
											                     </td>
							                                 </tr>
				                                 			</c:otherwise>
				                                		 </c:choose>
									                	</c:forEach>
                                           			</c:when>
                                             		<c:otherwise>
									                    <tr id="departmentRow0">
									                        <td data-head="Department" class="input-field">
									                             <select class="searchable validate-dropdown dept" name="department_fks" id="department_fks0"  onchange="getExecutivesList('0');"
									                             	<c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD'  && sessionScope.USER_TYPE ne 'DyHOD'}">disabled </c:if>> 
									                                	<option value="" >Select</option>  
																          <c:forEach var="obj" items="${departmentList }">
								                                      	    <option value= "${ obj.department_fk}" >${ obj.department_name}</option>
								                                          </c:forEach>
									                              </select>
									                              <span id="deptError0" class="my-error"></span>
									                              <input type="hidden" id="filecounts0" name="filecounts" value="0">
									                        </td>
									                        <td data-head="Select Executives" class="input-field h-auto">
									                            <select class="searchable validate-dropdown" name="responsible_people_id_fks"  onchange="fileCount('0');"
									                               <c:if test="${sessionScope.USER_ROLE_NAME ne 'IT Admin' && sessionScope.USER_TYPE ne 'HOD'   && sessionScope.USER_TYPE ne 'DyHOD'}">disabled </c:if>
									                                id="responsible_people_id_fks0" multiple="multiple">
									                                <option value="" >Select</option>
									                             	
									                            </select>
									                            <span id="personError0" class="my-error"></span>
									                        </td>
									                        <td class="mobile_btn_close">
									                            <a onclick="removeDepartment('0');"
									                                class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
									                        </td>
									                    </tr>
									              </c:otherwise>
                                            	</c:choose>
									                </tbody>
									            </table>
									            <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin' || sessionScope.USER_TYPE eq 'HOD'   || sessionScope.USER_TYPE eq 'DyHOD'}">
									            <table  class="mdl-data-table" style="margin-bottom: 30px">
			                                        <tbody>                                          
			                                            <tr>
			                                   				<td colspan="3" style="text-align: right;" ><a class="btn waves-effect waves-light bg-m t-c "  onclick="addDepartmentRow()"> <i class="fa fa-plus"></i></a></td>
			                                             </tr>
			                                        </tbody>
			                                    </table> 
			                                    </c:if>
			                                    <c:choose>
				                                    <c:when test="${not empty contractDeatils.departmentList && fn:length(contractDeatils.departmentList) gt 0 }">
				                                		<input type="hidden" id="deptRowNo"  name="deptRowNo" value="${fn:length(contractDeatils.departmentList) }" />
				                                	</c:when>
				                                 	<c:otherwise>
				                                 		<input type="hidden" id="deptRowNo"  name="deptRowNo" value="0" />
				                                 	</c:otherwise>
				                                 </c:choose>
							                                    
									        </div>
									    </div>
									</div>
								</div>
							</div>
	                            <%-- <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                   <p>   <label>Status of Contract</p>
	                                    <select name = "contract_status_fk" id="contract_status_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                           <c:forEach var="obj" items="${contract_Statustype }">
			                                    	<option value="${obj.contract_status_fk }" <c:if test="${contractDeatils.contract_status_fk eq obj.contract_status_fk}">selected</c:if>>${obj.contract_status_fk }</option>
			                                    </c:forEach>
	                                    </select>
	                                     <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div> --%>
	                          <div class="" id="bgDetails">
	                           <%--  <div class="row">
                                <div class="col m8 input-field center-align no-float-small offset-m2">
                                    <p>Bank Guarantee Required</p>							
                                    <p>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio"  value="Yes" <c:if test="${contractDeatils.bg_required eq 'Yes'}">checked
                                            </c:if> />
                                            <span>Yes</span>
                                        </label>
                                        <label>
                                            <input class="with-gap" name="bg_required" type="radio" value="No" <c:if test="${contractDeatils.bg_required eq 'No'}">checked
                                            </c:if> />
                                            <span>No</span>
                                        </label>
                                    </p>
                                </div>
                            	</div> --%>
							
                            <!-- bg show hide div  -->
                            <div class="row fixed-width" id="bank_guarantee_div" style="/* display: none; */">
                               <!--  <h5 class="center-align">Bank Guarantee Details</h5> -->
                                <div class="table-inside">
                                    <table id="bankTable" class="mdl-data-table mobile_responsible_table another">
                                        <thead>
                                            <tr>
                                               <th class="fs-100">Code </th>
                                                <th>BG Type </th>
                                                <th>Issuing Bank </th>
                                               <!--  <th>Bank Address </th> -->
                                                <th>BG / FDR <br>Number </th>
                                                <th >Amount </th>
                                                <th>Unit</th>
                                                <th>BG / FDR <br> Date </th>
                                                <th>Expiry Date </th>
                                              <!--   <th>Remarks </th> -->
                                                <th>Release Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="bankTableBody">
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.bankGauranree && fn:length(contractDeatils.bankGauranree) gt 0 }">
                                          
                                		  <c:forEach var="bankObj" items="${contractDeatils.bankGauranree }" varStatus="index">                                        	
                                        
                                            <tr id="bankRow${index.count }">
                                            <td data-head="Code " class="input-field fs-100"> <input id="codes${index.count }" type="text" class="validate" name="codes" value="${bankObj.code }"
                                                        placeholder="Code">
                                                </td>
                                                <td data-head="BG Type " class="input-field"> <select id="bg_type_fks${index.count }" name="bg_type_fks" class="searchable">
                                                        <option value="">Select</option>
                                                         <c:forEach var="obj" items="${bankGuaranteeTYpe }">
		                                    			   <option value="${obj.bg_type_fk }" <c:if test="${bankObj.bg_type_fk eq obj.bg_type_fk}">selected</c:if>>${obj.bg_type_fk }</option>
		                                     			  </c:forEach>
                                                    </select>
                                                </td> 
                                                <!-- <td> <input id="bg_type" type="text" class="validate"
                                                        placeholder="BG Type">
                                                </td> -->
                                                <td data-head="Issuing Bank " class="input-field">
                                                    <input id="issuing_banks${index.count }" name="issuing_banks"  type="text" class="validate" value="${bankObj.issuing_bank }"
                                                        placeholder="Issuing Bank">
                                                </td>
                                                <!-- <td>
                                                    <input id="bank_addresss${index.count }" name ="bank_addresss" type="text" class="validate" value="${bankObj.bank_address }"
                                                        placeholder="Bank Address">
                                                </td> -->
                                                <td data-head="BG / FDR Number " class="input-field">
                                                    <input id="bg_numbers${index.count }" name="bg_numbers" type="text" class="validate" value="${bankObj.bg_number}"
                                                        placeholder="BG / FDR Number">
                                                </td>
                                                <td data-head="Amount" class="input-field">
                                                		<i class="material-icons prefix cost left-align">₹</i>
                                                    	<input id="bg_values${index.count }" name="bg_values" min="0.01" step="0.01" type="number" class="validate" value="${bankObj.bg_value }"
                                                        placeholder="Amount">
                                                 </td>
                                                 <td class="responsive_units">
					                                	<select class="units validate-dropdown" id="bg_values_units${index.count }" name="bg_value_unitss">
					                                		<option value="">Select</option>
					                                      <c:forEach var="obj" items="${unitsList }">
		                                    			    <option value="${obj.value }" <c:if test="${bankObj.bg_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                     			  </c:forEach>
					                                	</select>
					                                	<span id="bg_units${index.count }Error" class="my-error"></span>
                                                </td>
                                               <td data-head="BG / FDR Date " class="input-field">
                                                    <input id="bg_dates${index.count }" name="bg_dates" type="text" class="validate datepicker" value="${bankObj.bg_date }"
                                                        placeholder="BG /FDR Date">
                                                         <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td data-head="Expiry Date " class="input-field">
                                                     <input id="bg_valid_uptos${index.count }" name="bg_valid_uptos" type="text" class="validate datepicker" value="${bankObj.bg_valid_upto }"
                                                        placeholder="Expiry Date">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <!-- <td>
                                                    <input id="remarkss${index.count }" name ="remarkss" type="text" class="validate" value="${bankObj.remarks }"
                                                        placeholder="Remarks">
                                                </td> -->
                                                <td data-head="Release Date" class="input-field">	<input id="release_dates${index.count }" name="release_dates" type="text" class="validate datepicker" value="${bankObj.release_date }"
                                                        placeholder="Release Date">
                                                    <button type="button"><i class="fa fa-calendar"></i></button></td>       
                                                <td class="mobile_btn_close">
                                                    <a onclick="removeBank('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>                                               
                                               
                                            </tr>
                                           <script type="text/javascript">
                                             	$('#bg_values_units${index.count }').change(function(){
									            	if($.trim($('#bg_values_units${index.count }').val()) == ""){
														$('#bg_units${index.count }Error').text('Requried');
													}else{
														$('#bg_units${index.count }Error').text('');
													}
										        });
                                             </script>
                                          </c:forEach>
                                           </c:when>
                                             <c:otherwise>
                                             <tr id="bankRow0">
                                             <td data-head="Code " class="input-field fs-100"> <input id="codes0" type="text" class="validate" name="codes"
                                                        placeholder="Code">
                                                </td>
                                                <td data-head="BG Type " class="input-field"> <select id="bg_type_fks0" name="bg_type_fks" class="searchable">
                                                        <option value="" selected>Select </option>
                                                         <c:forEach var="obj" items="${bankGuaranteeTYpe }">
		                                    			   <option value="${obj.bg_type_fk }" >${obj.bg_type_fk }</option>
		                                     			  </c:forEach>
                                                    </select>
                                                </td> 
                                                <!-- <td> <input id="bg_type" type="text" class="validate"
                                                        placeholder="BG Type">
                                                </td> -->
                                                <td data-head="Issuing Bank " class="input-field">
                                                    <input id="issuing_banks0" name="issuing_banks"  type="text" class="validate"
                                                        placeholder="Issuing Bank">
                                                </td>
                                               <!--  <td>
                                                    <input id="bank_addresss0" name ="bank_addresss" type="text" class="validate"
                                                        placeholder="Bank Address">
                                                </td> -->
                                                <td data-head="BG / FDR Number " class="input-field">
                                                    <input id="bg_numbers0" name="bg_numbers" type="text" class="validate"
                                                        placeholder="BG / FDR Number">
                                                </td>
                                                <td data-head="Amount " class="input-field">
                                                	
                                                		<i class="material-icons prefix cost left-align">₹</i>
                                                    	<input id="bg_values0" name="bg_values" min="0.01" step="0.01" type="number" class="validate"     placeholder="Amount">
                                                  </td>
                                                  <td class="responsive_units">
					                                	<select class="units validate-dropdown" id="bg_values_units0" name="bg_value_unitss">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
		                                    			     <option value="${obj.value }">${obj.unit }</option>
		                                     			    </c:forEach>
					                                	</select>
					                                	<span id="bg_units0Error" class="my-error"></span>
                                                    </div>
                                                </td>
                                                <td data-head="BG / FDR Date " class="input-field">
                                                    <input id="bg_dates0" name="bg_dates" type="text" class="validate datepicker"
                                                        placeholder="BG /FDR Date">
                                                         <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td data-head="Expiry Date " class="input-field">
                                                    <input id="bg_valid_uptos0" name="bg_valid_uptos" type="text" class="validate datepicker"
                                                        placeholder="Expiry Date">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                               <!--  <td>
                                                    <input id="remarkss0" name ="remarkss" type="text" class="validate"
                                                        placeholder="Remarks">
                                                </td> -->
                                                <td data-head="Release Date" class="input-field">	<input id="release_dates0" name="release_dates" type="text" class="validate datepicker"
                                                        placeholder="Release Date">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>	</td>       
                                                <td class="mobile_btn_close">
                                                    <a onclick="removeBank('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                                
                                                
                                            </tr> 
                                            	<script type="text/javascript">
	                                                $("#bg_valid_uptos0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
	                                                $('#bg_values_units0').change(function(){
										            	if($.trim($('#bg_values_units0').val()) == ""){
															$('#bg_units0Error').text('Requried');
														}else{
															$('#bg_units0Error').text('');
														}
											        });
	                                             
                                                </script>
                                              </c:otherwise>
                                            </c:choose>
                                        </tbody>
                                    </table>
                                     <table class="mdl-data-table">
                                        <tbody id="bankTableBody">                                          
                                            <tr>
									 			<td colspan="9" style="text-align: right;"> 
									 				<a class="btn waves-effect waves-light bg-m t-c "  onclick="addBankRow()"> 
									 					<i class="fa fa-plus"></i>
									 				</a>
									 			</td>
                                         </tr>
                                        </tbody>
                                    </table>
                                    <c:choose>
                                        <c:when test="${not empty contractDeatils.bankGauranree && fn:length(contractDeatils.bankGauranree) gt 0 }">
                                    		<input type="hidden" id="bankRowNo"  name="bankRowNo" value="${fn:length(contractDeatils.bankGauranree) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="bankRowNo"  name="bankRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>   
                                </div>
                            </div>
						</div>
						<div class="" id="insuranceDetails">
	                           <%--  <div class="row">
	                                <div class="col m8 input-field center-align no-float-small offset-m2">
	                                    <p>Insurance Required</p>
	                                    <p>
	                                        <label>
	                                            <input class="with-gap" name="insurance_required" type="radio"  value="Yes" <c:if test="${contractDeatils.insurance_required eq 'Yes'}">checked
	                                            </c:if> />
	                                            <span>Yes</span>
	                                        </label>
	                                        <label>
	                                            <input class="with-gap" name="insurance_required" type="radio"  value="No" <c:if test="${contractDeatils.insurance_required eq 'No'}">checked
	                                            </c:if> />
	                                            <span>No</span>
	                                        </label>
	                                    </p>
	                                </div>
	                            </div> --%>
                            <!-- insurance show hide div  -->
                            <div class="row fixed-width" id="insurance_div" style="/* display: none; */">
                               <!--  <h5 class="center-align">Insurance Details</h5> -->
                                <div class="table-inside">
                                    <table id="insurenceTable" class="mdl-data-table mobile_responsible_table another">
                                        <thead>
                                            <tr>
                                                <th>Insurance Type </th>
                                                <th>Issuing Agency </th>
                                                <th>Agency Address </th>
                                                <th>Insurance Number </th>
                                                <th >Insurance Value </th>
                                                <th>Unit</th>
                                                <th>Revision </th>
                                                <th>Valid Upto </th>
                                                <th>Remarks </th>
                                                <th>Release</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="insurenceTableBody">
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                          <c:forEach var="insurenceObj" items="${contractDeatils.insurence }" varStatus="index">  
                                            <tr id="insurenceRow${index.count }">
                                                <td data-head="Insurance Type " class="input-field">
                                                    <select id="insurance_type_fks${index.count }" name="insurance_type_fks" class="searchable">
                                                        <option value="" selected>Select</option>
                                                          <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" <c:if test="${insurenceObj.insurance_type_fk eq obj.insurance_type}">selected</c:if>>${ obj.insurance_type}</option>
                                        				  </c:forEach>
                                                    </select>
                                                </td>
                                                <td data-head="Issuing Agency " class="input-field">
                                                    <input id="issuing_agencys${index.count }" name="issuing_agencys" type="text" class="validate"  value="${insurenceObj.issuing_agency }"
                                                        placeholder="Issuing Agency">
                                                </td>
                                                <td data-head="Agency Address " class="input-field">
                                                    <input id="agency_addresss${index.count }" name="agency_addresss" type="text" class="validate" value="${insurenceObj.agency_address }"
                                                        placeholder="Agency Address">
                                                </td>

                                                <td data-head="Insurance Number " class="input-field">
                                                    <input id="insurance_numbers${index.count }" name="insurance_numbers" type="text" class="validate" value="${insurenceObj.insurance_number }"
                                                        placeholder="Insurance Number">
                                                </td>                                                
                                                <td data-head="Insurance Value " class="input-field">
                                                		<i class="material-icons prefix cost left-align">₹</i>
                                                    	<input id="insurance_values${index.count }" name="insurance_values" min="0.01" step="0.01" type="number" class="validate" value="${insurenceObj.insurance_value }"
                                                        placeholder="Insurance Value">
                                                    </td> 
                                                    <td class="responsive_units">
					                                	<select class="units validate-dropdown" id="insurance_values_units${index.count }" name="insurance_value_unitss">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
		                                    			    <option value="${obj.value }" <c:if test="${insurenceObj.insurance_value_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                     			  </c:forEach>
					                                	</select>
					                                	<span id="insurence_units${index.count }Error" class="my-error"></span>
                                                 </td>
                                                 <td data-head="Revision " class="input-field">
                                                    <input id="insurance_revisions${index.count }" name="insurance_revisions" type="text" class="validate" value="${insurenceObj.revision }"  
                                                        placeholder="Revision">
                                                </td>
                                                <td data-head="Valid Upto " class="input-field">
                                                    <input id="insurence_valid_uptos${index.count }" name="insurence_valid_uptos" type="text" value="${insurenceObj.insurence_valid_upto }"
                                                        class="validate datepicker" placeholder="Valid Upto">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td data-head="Remarks " class="input-field">
                                                    <input id="insurence_remarks${index.count }" name="insurence_remarks"  type="text" class="validate" value="${insurenceObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td data-head="Release" class="input-field"> <p><label> <input type="hidden" id="insuranceStatuss${index.count }" name="insuranceStatus" value="${insurenceObj.insurance_status}" />
                                                <input type="checkbox" id="insuranceStatus${index.count }" <c:if test="${insurenceObj.insurance_status == 'Yes'}">checked
                                            </c:if>/> <span></span> </label>	</p></td>       
                                                <td class="mobile_btn_close">
                                                    <a onclick="removeInsurence('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                             <script type="text/javascript">
	                                               /*  $("#insurence_valid_uptos${index.count }").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 }); */
	                                                 $('#insuranceStatus${index.count }').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#insuranceStatuss${index.count }').val('Yes');
							                             } else{
							                              	 
							                            	  $("#insuranceStatuss${index.count }").prop('checked',false).removeAttr('checked');
							                            	  $("#insuranceStatuss${index.count }").val('No')
							                              }
							                   	    });
	                                                 $('#insurance_values_units${index.count }').change(function(){
											            	if($.trim($('#insurance_values_units${index.count }').val()) == ""){
																$('#insurence_units${index.count }Error').text('Requried');
															}else{
																$('#insurence_units${index.count }Error').text('');
															}
												    });
                                                </script>
                                             </c:forEach>
                                             </c:when>
                                             <c:otherwise>
                                             
                                              <tr id="insurenceRow0">
                                                <td data-head="Insurance Type " class="input-field">
                                                    <select id="insurance_type_fks0" name="insurance_type_fks" class="searchable">
                                                        <option value="" selected>Select</option>
                                                          <c:forEach var="obj" items="${insurance_type }">
                                      					   <option value= "${ obj.insurance_type}" >${ obj.insurance_type}</option>
                                        				  </c:forEach>
                                                    </select>
                                                </td>
                                                <td data-head="Issuing Agency " class="input-field">
                                                    <input id="issuing_agencys0" name="issuing_agencys" type="text" class="validate" 
                                                        placeholder="Issuing Agency">
                                                </td>
                                                <td data-head="Agency Address " class="input-field">
                                                    <input id="agency_addresss0" name="agency_addresss" type="text" class="validate" 
                                                        placeholder="Agency Address">
                                                </td>

                                                <td data-head="Insurance Number " class="input-field">
                                                    <input id="insurance_numbers0" name="insurance_numbers" type="text" class="validate" 
                                                        placeholder="Insurance Number">
                                                </td>
                                                <td data-head="Insurance Value " class="input-field ">
                                                		<i class="material-icons prefix cost left-align">₹</i>
                                                    	<input id="insurance_values0" name="insurance_values" min="0.01" step="0.01" type="number" class="validate" value="${insurenceObj.insurance_value }"
                                                        placeholder="Insurance Value">
                                                    </td> 
                                                    <td class="responsive_units">
					                                	<select class="units validate-dropdown" id="insurance_values_units0" name="insurance_value_unitss">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
		                                    			    <option value="${obj.value }">${obj.unit }</option>
		                                     			  </c:forEach>
					                                	</select>
					                                	<span id="insurence_units0Error" class="my-error"></span>
                                                 </td>
                                                 <td data-head="Revision " class="input-field">
                                                    <input id="insurance_revisions0" name="insurance_revisions" type="text" class="validate" 
                                                        placeholder="Revision">
                                                </td>
                                                <td data-head="Valid Upto " class="input-field">
                                                    <input id="insurence_valid_uptos0" name="insurence_valid_uptos" type="text" 
                                                        class="validate datepicker" placeholder="Valid Upto">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                <td data-head="Remarks " class="input-field">
                                                    <input id="insurence_remarks0" name="insurence_remarks"  type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td data-head="Release" class="input-field"><p><label><input type="hidden" id="insuranceStatuss0" name="insuranceStatus" value="No" />
                                                 <input type="checkbox" id="insuranceStatus0" /> <span></span> </label>	</p></td>       
                                                <td class="mobile_btn_close">
                                                    <a onclick="removeInsurence('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                            <script type="text/javascript">
	                                                $("#insurence_valid_uptos0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
	                                                $('#insuranceStatus0').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#insuranceStatuss0').val('Yes');
							                             } else{
							                              	 
							                            	  $("#insuranceStatuss0").prop('checked',false).removeAttr('checked');
							                            	  $("#insuranceStatuss0").val('No')
							                              }
							                   	    });
	                                                $('#insurance_values_units0').change(function(){
										            	if($.trim($('#insurance_values_units0').val()) == ""){
															$('#insurence_units0Error').text('Requried');
														}else{
															$('#insurence_units0Error').text('');
														}
											    	});
                                                </script>
                                           
                                             </c:otherwise>
                                            </c:choose>  
                                        </tbody>
                                    </table>
                                     <table  class="mdl-data-table">
                                        <tbody id="insurenceTableBody">                                          
                                            <tr>
                                   <td colspan="8" style="text-align: right;" ><a   class="btn waves-effect waves-light bg-m t-c "  onclick="addInsurenceRow()"> <i class="fa fa-plus"></i></a></td>
                                             </tr>
                                        </tbody>
                                    </table>
									<c:choose>
                                        <c:when test="${not empty contractDeatils.insurence && fn:length(contractDeatils.insurence) gt 0 }">
                                    		<input type="hidden" id="insurenceRowNo"  name="insurenceRowNo" value="${fn:length(contractDeatils.insurence) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="insurenceRowNo"  name="insurenceRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
                                </div>
                            </div>
						</div>
						<div class="row" id="milestoneDetails">
							<div class="col m8 offset-m2 s12" >
	                            <div class="row fixed-width">
	                               <!--  <h5 class="center-align">Milestone Details</h5> -->
	                                <div class="table-inside">
	                                    <table id="mileTable" class="mdl-data-table mobile_responsible_table">
	                                        <thead>
	                                            <tr>
	                                            	<th>Milestone ID </th>
	                                                <th>Milestone Name </th>
	                                                <th>Milestone Date </th>
	                                                <th>Actual Date </th>
	                                                <th>Revision</th>
	                                                <th>Remarks </th>
	                                                <th>Action</th>
	                                            </tr>
	                                        </thead >
	                                        <tbody id="milestoneTableBody" >
	                                        <c:choose>
	                                        <c:when test="${not empty contractDeatils.milestones  && fn:length(contractDeatils.milestones ) gt 0 }">
	                                          
	                                          <c:forEach var="milestonesObj" items="${contractDeatils.milestones }" varStatus="index">  
	                                        
	                                            <tr id="mileRow${index.count }">   
	                                           		  <td data-head="Milestone ID " class="input-field">
	                                           		    <input type ="hidden" name="contract_milestones_ids" id="contract_milestones_ids${index.count }" value="${milestonesObj.contract_milestones_id }" />
	                                                    <input id="milestone_ids${index.count }" name="milestone_ids" type="text" class="validate" value="${milestonesObj.milestone_id }"
	                                                        placeholder="Milestone ID ">
	                                                </td>                                 
	                                                <td data-head="Milestone Name " class="input-field">
	                                                    <input id="milestone_names${index.count }" name="milestone_names" type="text" class="validate" value="${milestonesObj.milestone_name }"
	                                                        placeholder="Milestone Name ">
	                                                </td>
	                                                <td data-head="Milestone Date " class="input-field">
	                                                    <input id="milestone_dates${index.count }" name="milestone_dates" type="text" class="validate datepicker" value="${milestonesObj.milestone_date }"
	                                                        placeholder="Milestone Date">
	                                                    <button type="button"><i class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="Actual Date " class="input-field">
	                                                    <input id="actual_dates${index.count }" name="actual_dates" type="text" class="validate datepicker" value="${milestonesObj.actual_date }"
	                                                        placeholder="Actual Date">
	                                                    <button type="button"><i class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="Revision" class="input-field">
	                                                    <input id="revisions${index.count }" name="revisions" type="text" class="validate" value="${milestonesObj.revision }"
	                                                        placeholder="Revision">
	                                                </td>
	                                                <td data-head="Remarks " class="input-field">
	                                                    <input id="mile_remarks${index.count }" name="mile_remarks" type="text" class="validate" value="${milestonesObj.remarks }"
	                                                        placeholder="Remarks">
	                                                </td>
	                                                <td class="mobile_btn_close"><a onclick="removeMilestone('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
	                                            </tr>
	                                            
	                                             </c:forEach>
	                                              </c:when>
	                                             <c:otherwise>
	                                              <tr id="mileRow0"> 
	                                              <td data-head="Milestone ID " class="input-field"><input type="hidden" name= "contract_milestones_ids" id="contract_milestones_ids0" value=" "/>
	                                                    <input id="milestone_ids0" name="milestone_ids" type="text" class="validate" 
	                                                        placeholder="Milestone ID" value="K-1">
	                                                </td>                                        
	                                                <td data-head="Milestone Name " class="input-field">
	                                                    <input id="milestone_names0" name="milestone_names" type="text" class="validate" 
	                                                        placeholder="Milestone Name ">
	                                                </td>
	                                                <td data-head="Milestone Date " class="input-field">
	                                                    <input id="milestone_dates0" name="milestone_dates" type="text" class="validate datepicker" 
	                                                        placeholder="Milestone Date">
	                                                    <button type="button"><i class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="Actual Date " class="input-field">
	                                                    <input id="actual_dates0" name="actual_dates" type="text" class="validate datepicker" 
	                                                        placeholder="Actual Date">
	                                                    <button type="button"><i class="fa fa-calendar"></i></button>
	                                                </td>
	                                                <td data-head="Revision" class="input-field">
	                                                    <input id="revisions0" name="revisions" type="text" class="validate " 
	                                                        placeholder="Revision">
	                                                </td>
	                                                <td data-head="Remarks " class="input-field">
	                                                    <input id="mile_remarks0" name="mile_remarks" type="text" class="validate" 
	                                                        placeholder="Remarks">
	                                                </td>
	                                                <td class="mobile_btn_close"><a onclick="removeMilestone('0');" class="btn waves-effect waves-light red t-c "> <i
	                                                            class="fa fa-close"></i></a>
	                                                </td>
	                                            </tr>
	                                              <script type="text/javascript">
		                                                $("#milestone_dates0,#actual_dates0").datepicker({
		                                                 	 format:'dd-mm-yyyy',
		                                                     onSelect: function () {
		                                          	    	     $('.confirmation-btns .datepicker-done').click();
		                                          	    	  }
		                                                 });
	                                                </script>
	                                            </c:otherwise>
	                                            </c:choose>  
	                                        </tbody>
	                                    </table>
	                                    <table class="mdl-data-table">
	                                        <tbody>                                          
	                                            <tr>
	  												<td colspan="6" style="text-align: right;" ><a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addMilestoneRow()"> <i
	                                                            class="fa fa-plus"></i></a></td> 
	                                             </tr>
	                                        </tbody>
	                                    </table>
	                                    
	                                      <c:choose>
	                                        <c:when test="${not empty contractDeatils.milestones && fn:length(contractDeatils.milestones) gt 0 }">
	                                    		<input type="hidden" id="mileRowNo"  name="mileRowNo" value="${fn:length(contractDeatils.milestones) }" />
	                                    	</c:when>
	                                     	<c:otherwise>
	                                     		<input type="hidden" id="mileRowNo"  name="mileRowNo" value="0" />
	                                     	</c:otherwise>
	                                     </c:choose>  
	                                </div>
	                            </div>
	                         </div>
	                         </div>
	                         <div class="container container-no-margin" id="revisionDetails"> 
	                            <div class="row fixed-width">
                                <!-- <h5 class="center-align">Revision Details</h5> -->
                                <div class="table-inside">

                                    <table id="ravTable" class="mdl-data-table mobile_responsible_table">
                                        <thead>
                                            <tr>
                                                <th>Revision Number <span class="required">*</span></th>
                                                <th class="light-blue_column">Revised Contract Value </th>
                                                <th class="light-blue_column">Unit</th>
                                                <th class="light-blue_column p-h-0">Current</th>
                                                <th class="light-green_column">Revised DOC </th>
                                                <th class="light-green_column  p-h-0">Current</th>
                                                <th>Remarks </th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody  id="revTableBody" >
                                        <c:choose>
                                        <c:when test="${not empty contractDeatils.contract_revision  && fn:length(contractDeatils.contract_revision ) gt 0 }">
                                          
                                         <c:forEach var="revObj" items="${contractDeatils.contract_revision }" varStatus="index">  
                                            <tr id="revRow${index.count }">
                                                <td data-head="Revision Number " class="input-field"> <input id="revision_numbers${index.count }" name="revision_numbers" type="text" class="validate" value="${revObj.revision_number }"
                                                        placeholder="Revision Number">
                                                </td>
                                                <td data-head="Revised Amount " class="input-field ">
                                                		<i class="material-icons prefix cost left-align">₹</i>
                                                    	<input id="revised_amounts${index.count }" name="revised_amounts" min="0.01" step="0.01" type="number" onkeyup="toggleRevision('amounts', ${index.count })" class="validate" value="${revObj.revised_amount }"
                                                        placeholder="Revised Amount">
                                                 </td>
                                                 <td class="responsive_units  ">
                                                   <!--  <div class="col s3 pt-14"> -->
					                                	<select class="units validate-dropdown" id="revised_amounts_units${index.count }" name="revised_amount_unitss">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
		                                    			      <option value="${obj.value }" <c:if test="${revObj.revised_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                     			    </c:forEach>
					                                	</select>
					                                	<span id="units${index.count }Error" class="my-error"></span>
                                                   <!--  </div> -->
                                                </td>
                                                 <td data-head="Current" class="input-field  p-h-0">	
                                                 	<p>
                                                 	<label> 
                                                  	   <input type="hidden" class="hidden_check hidden_amount" id="revision_amounts_statuss${index.count }" name="revision_amounts_statuss" value="${revObj.revision_amounts_status}" />
                                                	   <input type="checkbox"  id="revision_amounts_status${index.count }" 
                                                          onchange="revisionChecks('amounts',${index.count })"<c:if test="${revObj.revision_amounts_status == 'Yes'}">checked
                                                          </c:if> class="revision_amount_status" /> 
                                                			<span></span> 
                                                	</label></p>
                                                </td>
                                                <td data-head="Revised DOC " class="input-field ">
                                                    <input id="revised_docs${index.count }" name="revised_docs" type="text" class="validate datepicker" value="${revObj.revised_doc }"
                                                       onchange="toggleRevision('docs', ${index.count })"  placeholder="Revised DOC">
                                                    <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td>
                                                 <td data-head="Current" class="input-field  p-h-0">	
                                                 	<p>
                                                 	<label> 
                                                 		<input type="hidden" class="hidden_check hidden_doc" id="revision_statuss${index.count }" name="revision_statuss" value="${revObj.revision_status}" />
                                                		<input type="checkbox" id="revision_status${index.count }"  onchange="revisionChecks('docs','${index.count }')" class="revision_doc_status" 
                                                			<c:if test="${revObj.revision_status == 'Yes'}">checked</c:if>/> 
                                                			<span></span> 
                                                	</label></p>
                                                </td> 
                                                <td data-head="Remarks " class="input-field "> 
                                                    <input id="revision_remarks${index.count }" name="revision_remarks" type="text" class="validate" value="${revObj.remarks }"
                                                        placeholder="Remarks">
                                                </td>
                                                <td class="mobile_btn_close"><a onclick="removeRev('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                           		  <script type="text/javascript">
	                                                $("#revised_docs${index.count }").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
	                                                
                                                	var amount = $('#revised_amounts${index.count }').val();
                                                	if(amount == ""){
                                                		$('#revision_amounts_status${index.count }').attr('disabled', true);
                                                	}
                                                	var date = $('#revised_docs${index.count }').val();
                                                	if(date == ""){
                                                		$('#revision_status${index.count }').attr('disabled', true);
                                                	}
  	                                                	
	                                                $('#revision_status${index.count }').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#revision_statuss${index.count }').val('Yes');
							                             } else{
							                              	 
							                            	  $("#revision_statuss${index.count }").prop('checked',false).removeAttr('checked');
							                            	  $("#revision_statuss${index.count }").val('No')
							                              }
							                   	    });
	                                                $('#revision_amounts_status${index.count }').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#revision_amounts_statuss${index.count }').val('Yes');
							                             } else{
							                              	 
							                            	  $("#revision_amounts_statuss${index.count }").prop('checked',false).removeAttr('checked');
							                            	  $("#revision_amounts_statuss${index.count }").val('No')
							                              }
							                   	    });
	                                                $('#revised_amounts_units${index.count }').change(function(){
										            	if($.trim($('#revised_amounts_units${index.count }').val()) == ""){
															$('#units${index.count }Error').text('Requried');
														}else{
															$('#units${index.count }Error').text('');
														}
											    	});	                                             	                                                
	                                             	       
	                                                 $(document).ready(function () {                                               
                                                	 if( $('#revision_amounts_status'+${index.count}).prop('checked',true) ){    
                                                		if(!$('#revision_amounts_status'+${index.count}).attr('disabled')){
                                                	 		revisionChecks('amounts',${index.count});	                                                			
                                                		} else {
                                                			$('#revision_amounts_status'+${index.count}).prop('checked',false)
                                                		}
                                                	}
                                                	if(	$('#revision_status'+${index.count}).prop('checked',true) ){    
                                                		if (!$('#revision_status'+${index.count}).attr('disabled')){
                                                	 		revisionChecks('docs',${index.count})	                                                			
                                                		} else {
                                                			$('#revision_status'+${index.count}).prop('checked',false)
                                                		}
                                                	} 	                                                	
	                                                }); 
                                                </script>
                                          </c:forEach>
                                           </c:when>
                                             <c:otherwise>
                                             <tr id="revRow0">
                                                <td data-head="Revision Number " class="input-field"> <input id="revision_numbers0" name="revision_numbers" type="text" class="validate" 
                                                        placeholder="Revision Number" value="R-1">
                                                </td>
                                                <td data-head="Revised Amount " class="input-field  ">
                                                		<i class="material-icons prefix cost left-align">₹</i>
                                                    	<input id="revised_amounts0" name="revised_amounts" min="0.01" step="0.01" type="number" class="validate" onkeyup="toggleRevision('amounts', '0')"  placeholder="Revised Amount">
                                                </td>
                                                <td class="responsive_units  "> 
                                                    <!-- div class="col s3 pt-14"> -->
					                                	<select class="units validate-dropdown" id="revised_amounts_units0" name="revised_amount_unitss">
					                                		<option value="">Select</option>
					                                		<c:forEach var="obj" items="${unitsList }">
		                                    			      <option value="${obj.value }">${obj.unit }</option>
		                                     			    </c:forEach>
					                                	</select>
					                                	<span id="units0Error" class="my-error"></span>
                                                    <!-- </div> -->
                                                </td>
                                                <td data-head="Current" class="input-field   p-h-0">	
                                                 	<p>
                                                 	<label> 
                                                 		<input type="hidden" id="revision_amounts_statuss0" name="revision_amounts_statuss" class="hidden_check hidden_amount" value="No" />
	                                                	<input type="checkbox"  id="revision_amounts_status0" 
	                                                       onchange="revisionChecks('amounts',0)" class="revision_amount_status" disabled  />
	                                                	<span></span> 
                                                	</label></p>
                                                </td>
                                                <td data-head="Revised DOC " class="input-field  ">
                                                    <input id="revised_docs0" name="revised_docs" type="text" class="validate datepicker" 
                                                       onchange="toggleRevision('docs', '0' )"  placeholder="Revised DOC">
                                                   <button type="button"><i class="fa fa-calendar"></i></button>
                                                </td> 
                                                <td data-head="Current" class="input-field   p-h-0"><p><label><input type="hidden" id="revision_statuss0" name="revision_statuss" class="hidden_check hidden_doc" value="No" />
                                                 <input type="checkbox" id="revision_status0"  onchange="revisionChecks('docs','0')" class="revision_doc_status"  disabled />  <span></span> </label></p>
                                                 	</td>      
                                               
                                                <td data-head="Remarks " class="input-field"> 
                                                    <input id="revision_remarks0" name="revision_remarks" type="text" class="validate" 
                                                        placeholder="Remarks">
                                                </td>
                                                <td class="mobile_btn_close"><a onclick="removeRev('0');" class="btn waves-effect waves-light red t-c "> <i
                                                            class="fa fa-close"></i></a>
                                                </td>
                                            </tr>
                                              <script type="text/javascript">
	                                                $("#revised_docs0").datepicker({
	                                                	
	                                                 	 format:'dd-mm-yyyy',
	                                                     onSelect: function () {
	                                          	    	     $('.confirmation-btns .datepicker-done').click();
	                                          	    	  }
	                                                 });
	                                                $('#revision_status0').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                            	// $(".part").prop('disabled', true);
							                                 $('#revision_statuss0').val('Yes');
							                             } else{
							                              	 
							                            	  $("#revision_statuss0").prop('checked',false).removeAttr('checked');
							                            	  $("#revision_statuss0").val('No')
							                              }
							                   	    });
	                                                $('#revision_amounts_status0').on('change', function(e){
							                             if($(this).prop('checked'))
							                             {
							                                 $('#revision_amounts_statuss0').val('Yes');
							                             } else{
							                              	 
							                            	  $("#revision_amounts_statuss0").prop('checked',false).removeAttr('checked');
							                            	  $("#revision_amounts_statuss0").val('No')
							                              }
							                   	    });
										            
										            $('#revised_amounts_units0').change(function(){
										            	if($.trim($('#revised_amounts_units0').val()) == ""){
															$('#units0Error').text('Requried');
														}else{
															$('#units0Error').text('');
														}
											    	});
                                                </script> 
                                             </c:otherwise>
                                            </c:choose> 
                                        </tbody>
                                    </table>
                                     <table class="mdl-data-table">
                                        <tbody>                                          
                                            <tr>
												<td colspan="6" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addRevRow()"> <i
                                                            class="fa fa-plus"></i></a></td>
                                              </tr>
                                        </tbody>
                                    </table>
                                   	 <c:choose>
                                        <c:when test="${not empty contractDeatils.contract_revision && fn:length(contractDeatils.contract_revision) gt 0 }">
                                    		<input type="hidden" id="revRowNo"  name="revRowNo" value="${fn:length(contractDeatils.contract_revision) }" />
                                    	</c:when>
                                     	<c:otherwise>
                                     		<input type="hidden" id="revRowNo"  name="revRowNo" value="0" />
                                     	</c:otherwise>
                                     </c:choose>  
                                </div>
                            </div>
                          </div>
                            
                         <div class="" id="keyPersonDetails">
                         	<div class="row no-mar">
                             <!-- new code  starts-->
                            <div class="col m8 offset-m2 s12">
	                            <div class="container">
	                                <div class="row fixed-width">
	                                  <!--   <h5 class="center-align">Contractor's Key Personnel</h5> -->
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table mobile_responsible_table">
	                                            <thead>
	                                                <tr>
	                                                    <th>Name </th>
	                                                    <th>Designation </th>
	                                                    <th>Mobile No</th>
	                                                    <th>Email ID </th>
	                                                    <th>Action</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="keyPersonnelTableBody">
	                                            <c:choose>
			                                        <c:when test="${not empty contractDeatils.contractKeyPersonnels  && fn:length(contractDeatils.contractKeyPersonnels ) gt 0 }">			                                          
				                                        <c:forEach var="keyObj" items="${contractDeatils.contractKeyPersonnels }" varStatus="index">  
			                                                <tr id="keyPersonnelRow${index.count }">
			                                                    <td data-head="Name " class="input-field"> 
			                                                    	<input id="contractKeyPersonnelNames${index.count }" name="contractKeyPersonnelNames" type="text" value="${keyObj.name }" class="validate" placeholder="Name">
			                                                    </td>
			                                                    <td data-head="Designation " class="input-field"> 
			                                                    	<input id="contractKeyPersonnelDesignations${index.count }" name="contractKeyPersonnelDesignations" type="text" value="${keyObj.designation }" class="validate" placeholder="Designation">
			                                                    </td>
			                                                    <td data-head="Mobile No" class="input-field">
			                                                        <input id="contractKeyPersonnelMobileNos${index.count }" name="contractKeyPersonnelMobileNos" type="number" value="${keyObj.mobile_no }" class="validate" placeholder="Mobile No">
			                                                    </td>
			                                                    <td data-head="Email ID " class="input-field">
			                                                        <input id="contractKeyPersonnelEmailIds${index.count }" name="contractKeyPersonnelEmailIds" type="text" value="${keyObj.email_id }" class="validate" placeholder="Email">
			                                                    </td>
			                                                    <td class="mobile_btn_close">
			                                                        <a href="javascript:void(0);" onclick="removeKeyPersonnel('${index.count }');"  class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
			                                                    </td>
			                                                </tr>
		                                                </c:forEach>
	                                           		</c:when>
	                                             	<c:otherwise>
	                                             		<tr id="keyPersonnelRow0">
		                                                    <td data-head="Name " class="input-field"> 
		                                                    	<input id="contractKeyPersonnelNames0" name="contractKeyPersonnelNames" type="text" class="validate" placeholder="Name">
		                                                    </td>
		                                                    <td data-head="Designation " class="input-field"> 
		                                                    	<input id="contractKeyPersonnelDesignations0" name="contractKeyPersonnelDesignations" type="text" class="validate" placeholder="Designation">
		                                                    </td>
		                                                    <td data-head="Mobile No" class="input-field">
		                                                        <input id="contractKeyPersonnelMobileNos0" name="contractKeyPersonnelMobileNos" type="number" class="validate" placeholder="Mobile No">
		                                                    </td>
		                                                    <td data-head="Email ID " class="input-field">
		                                                        <input id="contractKeyPersonnelEmailIds0" name="contractKeyPersonnelEmailIds" type="text" class="validate" placeholder="Email">
		                                                    </td>
		                                                    <td class="mobile_btn_close">
		                                                        <a href="javascript:void(0);" onclick="removeKeyPersonnel('0');"  class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>
		                                                    </td>
		                                                </tr>
	                                             	</c:otherwise>
                                            	</c:choose> 
	                                            </tbody>
	                                        </table>
	                                        
	                                        <table class="mdl-data-table">
		                                        <tbody id="revTableBody">                                          
		                                            <tr>
														<td colspan="4" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addKeyPersonnelRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty contractDeatils.contract_revision && fn:length(contractDeatils.contractKeyPersonnels) gt 0 }">
		                                    		<input type="hidden" id="keyRowNo"  name="keyRowNo" value="${fn:length(contractDeatils.contractKeyPersonnels) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="keyRowNo"  name="keyRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose>  
                                   	 
	                                    </div>
	                                </div>
	                            </div>
							</div>
					  </div>
				</div>
							<div class="row" id="documentDetails">
	                            <div class="col m8 s12 offset-m2"  >
	                                <div class="row fixed-width">
	                                   <!--  <h5 class="center-align">Documents</h5> -->
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table mobile_responsible_table">
	                                            <thead>
	                                                <tr>
	                                                	<th>File Type </th>
	                                                    <th>Name </th>
	                                                    <th style="text-align:center">Attachment</th>
	                                                     <th> </th>
	                                                    <th style="width:8%">Action</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="contractDocumentTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty contractDeatils.contractDocuments  && fn:length(contractDeatils.contractDocuments ) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${contractDeatils.contractDocuments }" varStatus="index">  
			                                                <tr id="contractDocumentRow${index.count }">
			                                                	<td data-head="File Type " class="input-field">
																	<select  name="contract_file_types"  id="contract_file_types${index.count }"  class="validate-dropdown searchable">
					                                   					 <option value="" >--Select--</option>
					                                         			  <c:forEach var="obj" items="${contractFileTypeList}">
					                    					  				 <option value="${obj.contract_file_type }" <c:if test="${docObj.contract_file_type_fk eq obj.contract_file_type}">selected</c:if>>${obj.contract_file_type}</option>
					                                          			  </c:forEach>
					                               					  </select>
															    </td>
			                                                    <td data-head="Name " class="input-field"> <input id="contractDocumentNames${index.count }" name="contractDocumentNames" type="text" class="validate"
			                                                            placeholder="Name" value="${docObj.name }">
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="contractDocumentFiles${index.count }" name="contractDocumentFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="contractDocumentFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="contractDocumentFileNames${index.count }" name="contractDocumentFileNames" value="${docObj.attachment }">
			                                                             <span id="contractDocumentFileName${index.count }" class="filevalue"></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
			                                                     		<input type="hidden" id="contract_file_ids${index.count }" name="contract_file_ids" value="${docObj.contract_file_id }"/>
			                                                      		<a href="<%=CommonConstants2.CONTRACT_FILES%>${docObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                        
			                                                    </td>
			                                                    <td class="mobile_btn_close">
			                                                        <a href="javascript:void(0);" onclick="removeContractDocument('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
			                                                                class="fa fa-close"></i></a>
			                                                    </td>
			                                                </tr> 
	                                                	</c:forEach>
	                                           		</c:when>
	                                             	<c:otherwise>
	                                             		<tr id="contractDocumentRow0">
	                                             			<td data-head="File Type " class="input-field">																		
																<select  name="contract_file_types"  id="contract_file_types0"  class="validate-dropdown searchable">
				                                   					 <option value="" >--Select--</option>
				                                         			  <c:forEach var="obj" items="${contractFileTypeList}">
				                    					  				 <option value="${obj.contract_file_type }">${obj.contract_file_type}</option>
				                                          			  </c:forEach>
				                               					  </select>
															    </td>
		                                                    <td data-head="Name " class="input-field"> <input id="contractDocumentNames0" name="contractDocumentNames" type="text" class="validate"
		                                                            placeholder="Name">
		                                                    </td>
		                                                    <td data-head="Attachment" class="input-field">
		                                                        <span class="normal-btn">
		                                                            <input type="file" id="contractDocumentFiles0" name="contractDocumentFiles"
		                                                                style="display:none" onchange="getFileName('0')"/>
		                                                            <label for="contractDocumentFiles0" class="btn bg-m"><i
		                                                                    class="fa fa-paperclip"></i></label>
		                                                            <input type="hidden" id="contractDocumentFileNames0" name="contractDocumentFileNames" value=" ">
		                                                            <span id="contractDocumentFileName0" class="filevalue"></span>
		                                                        </span>
		                                                    </td>
		                                                    <td><input type="hidden" id="contract_file_ids0" name="contract_file_ids" value= " "/>
		                                                    </td>
		                                                    <td class="mobile_btn_close">
		                                                        <a href="javascript:void(0);" onclick="removeContractDocument('0');" class="btn waves-effect waves-light red t-c "> <i
		                                                                class="fa fa-close"></i></a>
		                                                    </td>
		                                                </tr>
	                                             	</c:otherwise>
                                            	</c:choose> 
	                                            </tbody>
	                                        </table>
	                                        
	                                        <table class="mdl-data-table">
		                                        <tbody id="revTableBody">                                          
		                                            <tr>
														<td colspan="3" style="text-align: right;">	<a type="button"  class="btn waves-effect waves-light bg-m t-c "  onclick="addContractDocumentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty contractDeatils.contractDocuments && fn:length(contractDeatils.contractDocuments) gt 0 }">
		                                    		<input type="hidden" id="documentRowNo"  name="documentRowNo" value="${fn:length(contractDeatils.contractDocuments) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="documentRowNo"  name="documentRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose> 
	                                    </div>
	                                </div>
	                            </div>
							</div>
							
 							<div class="container container-no-margin" id="closureDetails"> 
 								<div id="contractClosureDetails" style="display: none;">
 								<!-- 	<h5 class="center-align">Contract Closure Details</h5> 
 								</div>-->
 							    <div class="row">
 							 		<div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="actual_completion_date" name="actual_completion_date" type="text" class="validate datepicker" value="${contractDeatils.actual_completion_date }">
	                                    <label for="actual_completion_date">Actual Completion Date</label>
	                                    <span id="actual_completion_dateError" class="error-msg" ></span>
	                                    <button type="button" id="actual_completion_date_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s9 m3 input-field">
	                                	<i class="material-icons prefix cost left-align">₹</i>
	                                    <input id="completed_cost" name="completed_cost" min="0.01" step="0.01" type="number" class="validate" value="${contractDeatils.completed_cost }">
	                                    <label for="completed_cost">Completion Cost</label>
	                                     <span id="completed_costError" class="error-msg" ></span>
	                                </div>    
	                                <div class="col s3 m1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units validate-dropdown" id="completed_cost_units" name="completed_cost_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${contractDeatils.completed_cost_units eq obj.value }">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="completed_cost_unitsError" class="error-msg" ></span>
                                	</div>                          
	                            </div> 
	      						<div class="row">
	                                <div class="col m2 hide-on-small-only"></div>	                                
	                                <div class="col s12 m4 input-field">
	                                    <input id="final_takeover" name="final_takeover" type="text" class="validate datepicker" value="${contractDeatils.final_takeover }">
	                                    <label for="final_takeover">Final Taking over by Client</label>
	                                    <span id="final_takeoverError" class="error-msg" ></span>
	                                    <button type="button" id="final_takeover_icon"><i class="fa fa-calendar"></i></button>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="completion_certificate_release" name="completion_certificate_release" type="text" class="validate datepicker" value="${contractDeatils.completion_certificate_release }">
	                                    <label for="completion_certificate_release">Date of issue of Completion Certificate</label>
	                                    <button type="button" id="completion_certificate_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="completion_certificate_releaseError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="contract_closure_date" name="contract_closure_date" type="text" class="validate datepicker" value="${contractDeatils.contract_closure_date }">
	                                    <label for="contract_closure_date">Date of Contract Closure</label>
	                                    <button type="button" id="contract_closure_date_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="contract_closure_dateError" class="error-msg" ></span>	                                    
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="final_bill_release" name="final_bill_release" type="text" class="validate datepicker" value="${contractDeatils.final_bill_release }">
	                                    <label for="final_bill_release">Date of Payment of Final bill</label>
	                                    <button type="button" id="final_bill_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="final_bill_releaseError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="defect_liability_period" name="defect_liability_period" type="text" class="validate datepicker" value="${contractDeatils.defect_liability_period }">
	                                    <label for="defect_liability_period">End date of Defect Liability Period</label>
	                                    <button type="button" id="defect_liability_period_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="defect_liability_periodError" class="error-msg" ></span>
	                                </div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="final_retention_release" name="retention_money_release" type="text" class="validate datepicker" value="${contractDeatils.retention_money_release }">
	                                    <label for="final_retention_release">Date of release of Final Retention BG</label>
	                                    <button type="button" id="retention_money_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="final_retention_releaseError" class="error-msg" ></span>
	                                </div>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div>
	                            <div class="row">
	                                <div class="col m2 hide-on-small-only"></div>
	                                <div class="col s12 m4 input-field">
	                                    <input id="pbg_release" name="pbg_release" type="text" class="validate datepicker" value="${contractDeatils.pbg_release }">
	                                    <label for="pbg_release">Date of release of PBG</label>
	                                    <button type="button" id="pbg_release_icon"><i class="fa fa-calendar"></i></button>
	                                    <span id="pbg_releaseError" class="error-msg" ></span>
	                                </div>
	                                <%-- <div class="col s12 m4 input-field">
	                                    <input id="contract_closure" name="contract_closure" type="text" class="validate" value="${contractDeatils.contract_closure }">
	                                    <label for="contract_closure"> Contract Closure Comment</label>
	                                     <span id="contract_closureError" class="error-msg" ></span>
	                                </div> --%>
	                                <%-- <div class="col s12 m4 input-field">
	                                   <p class="searchable_label">   <label>Status of Contract</p>
	                                    <select name = "contract_status_fk" id="contract_status_fk" class="validate-dropdown searchable">
	                                        <option value="" selected>Select</option>
	                                           <c:forEach var="obj" items="${contract_Statustype }">
			                                    	<option value="${obj.contract_status_fk }" <c:if test="${contractDeatils.contract_status_fk eq obj.contract_status_fk}">selected</c:if>>${obj.contract_status_fk }</option>
			                                    </c:forEach>
	                                    </select>
	                                     <span id="contract_status_fkError" class="error-msg" ></span>
	                                </div> --%>
	                                <div class="col m2 hide-on-small-only"></div>
	                            </div> 
	                            </div>
                     		 </div>                     		 
                     		
                        </form>
                    </div>
                    <!-- form ends  -->
                </div>

 				
           <!--  </div> -->
            <div class="card ">
                <div class="card-content">
                <div class="container container-no-margin"> 
           		 	<div class="row">
                       <div class="col s6 m4 mt-brdr offset-m2 center-align">
                           <div class=" m-1">
                               <button type="button" onclick="updateContract();" class="btn waves-effect waves-light bg-m">Update</button>
                           </div>
                       </div>
                       <div class="col s6 m4 mt-brdr center-align">
                           <div class=" m-1">
                               <a href="<%=request.getContextPath()%>/contract"class="btn waves-effect waves-light bg-s">Cancel</a>
                           </div>
                       </div>
                   </div>
           		 </div>
           	</div>
        </div>
	</div>
</div>
    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
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
 

<!--     <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script> -->
<!--     <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script> -->
    <script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
    <script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
    <script src="/pmis/resources/js/dataTables.material.min.js"></script>
    <script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>


    <script>
	   /*  $(document).on('focus', '.datepicker',function(){
	        $(this).datepicker({
	        	format:'dd-mm-yyyy',
	   	    	onSelect: function () {
	   	    	   $('.confirmation-btns .datepicker-done').click();
	   	    	}
	        })
	    }); */
	    let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy',autoClose:true};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    });
    
        $(document).ready(function () {												
        	$('select:not(.searchable):not(.units)').formSelect();
            $('.searchable').select2();
            $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
            $('.tabs').tabs();
            $('#remarks').characterCounter();
            //getDyHodList();
            if('${contractTypeBGInsurance}'=='Bank Guarantee'){
            	$('.tabs a').removeClass('active');
            	$('.tabs [href="#bgDetails"]').addClass('active');
            	$('.tabs').tabs();
            }else if('${contractTypeBGInsurance}'=='Insurance'){
            	$('.tabs a').removeClass('active');
            	$('.tabs [href="#insuranceDetails"]').addClass('active');
            	$('.tabs').tabs();
            }
            var contract_status_fk = '${contractDeatils.contract_status_fk}';
            getContractClosureDetails(contract_status_fk);
            setContractStatus();
           /*  var bg_required = '${contractDeatils.bg_required}';
            if(bg_required == 'Yes'){
	       		$("#bank_guarantee_div").show();
	       	}else{
	       		$("#bank_guarantee_div").hide();
	       	}
            
            var insurance_required = '${contractDeatils.insurance_required}';
            if(insurance_required == 'Yes'){
	       		$("#insurance_div").show();
	       	}else{
	       		$("#insurance_div").hide();
	       	} */
	      /*  	$("#myForm").submit(function () {
	            $("#tabs").tabs("select", $("#contractForm .my-error-class").closest(".ui-tabs-panel").get(0).id);
	        }); */
            getHodList();
            getDyHodList();
        });
        
        function getContractClosureDetails(contract_status_fk) {
			/* if(contract_status_fk == 'Completed' || contract_status_fk == 'Closed'){
				$("#contractClosureDetails").show();
				$('#closureTab').show();
			}else{
				$("#contractClosureDetails").hide();
				$('#closureTab').hide();
			} */
			var actual_completion_date = '${contractDeatils.actual_completion_date}';
			var completed_cost = '${contractDeatils.completed_cost}';
			var final_takeover = '${contractDeatils.final_takeover}';
			var completion_certificate_release = '${contractDeatils.completion_certificate_release}';
			var contract_closure_date = '${contractDeatils.contract_closure_date}';
			var final_bill_release = '${contractDeatils.final_bill_release}';
			var defect_liability_period = '${contractDeatils.defect_liability_period}';
			var retention_money_release = '${contractDeatils.retention_money_release}';
			var pbg_release = '${contractDeatils.pbg_release}';
			
			$("#contractClosureDetails").hide();
			$('#closureTab').hide();
			if(contract_status_fk == 'Completed' || contract_status_fk == 'Closed'){
				$("#contractClosureRadioBtn").show();
				if($.trim(actual_completion_date) != '' || $.trim(completed_cost) != '' ||$.trim(final_takeover) != '' ||$.trim(completion_certificate_release) != '' || 
						$.trim(contract_closure_date) != '' || $.trim(final_bill_release) != '' || $.trim(defect_liability_period) != '' || $.trim(retention_money_release) != '' || 
						$.trim(pbg_release) != ''){
					$("#contractClosureRadioYes").prop("checked", true);
					$("#contractClosureDetails").show();
	 				$('#closureTab').show();
				}else{
					$("#contractClosureRadioNo").prop("checked", true);
				}
			}else{
				$("#contractClosureRadioBtn").hide();
			}
		} 
        $('input[name="contractClosureRadio"]').click(function(){
           if ($(this).is(':checked')){
             //alert($(this).val());
             var selectedVal = $(this).val();
             if(selectedVal == 'yes'){
            	 $("#contractClosureDetails").show();
 				 $('#closureTab').show();
             }else{
            	 $("#contractClosureDetails").hide();
 				 $('#closureTab').hide();
             }
           }
         });
        
     /*    $('input[name=bg_required]').change(function(){
	       	var value = $( 'input[name=bg_required]:checked' ).val();
	       	if(value == 'Yes'){
	       		$("#bank_guarantee_div").show();
	       	}else{
	       		$("#bank_guarantee_div").hide();
	       	}
       	}); 
        
        $('input[name=insurance_required]').change(function(){
	       	var value = $( 'input[name=insurance_required]:checked' ).val();
	       	if(value == 'Yes'){
	       		$("#insurance_div").show();
	       	}else{
	       		$("#insurance_div").hide();
	       	}
       	});
        */
        function getExecutivesList(num) {
        	$(".page-loader").show();
        	var count = Number(num);
        	$('#deptError'+count).hide();
        	var department_fk = $('#department_fks'+count).val();
        	/* var id =  $("#departmentTable tbody tr:first-of-type >td:first-of-type").find('.searchable').attr("id");  
        	var deptFirst = $('#'+id).val();
        	$('#department_fk').val(deptFirst); */
        	$("#responsible_people_id_fks"+count+" option:not(:first)").attr("selected",false);
            if ($.trim(department_fk) != "") {
            	$("#responsible_people_id_fks"+count+" option:not(:first)").remove();
                var myParams = { department_fk: department_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getExecutivesListForContractForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	 var userName = '';
	                        	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
                                 var designation = '';
                                 if ($.trim(val.designation) != '') { designation = $.trim(val.designation) }
                                
                                if ($.trim(hod_user_id_fk) != '') {
                                     $("#responsible_people_id_fks"+count).append('<option  value="' + val.hod_user_id_fk + '" >'  +  $.trim(designation) + $.trim(userName) + '</option>');
                                 } else {
                                     $("#responsible_people_id_fks"+count).append('<option  value="' + val.hod_user_id_fk + '" >'  +  $.trim(designation) + $.trim(userName) +'</option>');
                                 }
                            });
                        }
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
        }
        
        function fileCount(Rno){
        	var count = $('#responsible_people_id_fks'+Rno+' option:selected').length;
        	$('#filecounts'+Rno).val(count)
        }
  
        function addDepartmentRow(){
        	 var rowNo = $("#deptRowNo").val();
    		 var rNo = Number(rowNo)+1;
    		 var no = 0;
    		 var html = '<tr id="departmentRow'+rNo+'">'
    			   +'<td data-head="Department" class="input-field">'
    			   +'<select class="searchable validate-dropdown" name="department_fks" id="department_fks'+rNo+'" onchange="getExecutivesList('+rNo+');">'
    			   			+'<option value="" >Select</option> '
    			   			<c:forEach var="obj" items="${departmentList }">
    			   				+'<option value= "${ obj.department_fk}" >${ obj.department_name}</option>'
    			   			</c:forEach>
    			   +' </select><span id="deptError'+rNo+'" class="my-error"></span><input id="filecounts'+rNo+'"  name="filecounts"  type="hidden"></td>'
    			   +'<td data-head="Select Executives" class="input-field h-auto">'
    			   		+'<select class="searchable validate-dropdown dept" name="responsible_people_id_fks" id="responsible_people_id_fks'+rNo+'" onchange="fileCount(' +rNo+ ');"  MULTIPLE>'
    			   			+'<option value="" >Select</option>'
    			   
    			   +'</select><span id="personError'+rNo+'" class="my-error"></span></td>'
    			   +'<td class="mobile_btn_close"> <a onclick="removeDepartment('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a></td>'
    			   +'</tr>';
    		
    			 $('#departmentTableBody').append(html); 
    			 $("#deptRowNo").val(rNo);
    			 $('.searchable').select2();
        }
        
        function removeDepartment(rowNo){
        	$("#departmentRow"+rowNo).remove();
        }

        function getHodList() {
        	$(".page-loader").show();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();;
        	var dy_hod_user_id_fk = $("#dy_hod_user_id_fk").val();
        	var reporting_to_id_srfk = $("#dy_hod_user_id_fk").find('option:selected').attr("name");
            if ($.trim(hod_user_id_fk) == "") {
            	$("#hod_user_id_fk option:not(:first)").attr("selected",false);
            	//var myParams = { hod_user_id_fk: hod_user_id_fk, dy_hod_user_id_fk: reporting_to_id_srfk };
            	var myParams = {};
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getHodList",
                    data: myParams, cache: false,async:false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	   var userName = '';
 	                        	   if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
 	                        	   var deptCode =  val.contract_id_code;
	                        	   $("#contract_id_code").val(deptCode);
	                        	   $('#department_fk').val(val.department_fk);
	                        	   var user = '${contractDeatils.hod_user_id_fk}';
	                        	   if(val.hod_user_id_fk == user){
                             	   		$("#hod_user_id_fk").append('<option value="' + val.hod_user_id_fk + '" selected>' + $.trim(val.designation) + userName + '</option>');
                             	   }else{
                             	   		$("#hod_user_id_fk").append('<option value="' + val.hod_user_id_fk + '">' + $.trim(val.designation) + userName + '</option>');
                             	   }
                            });
                            if($.trim(reporting_to_id_srfk) != ''){
                            	$("#hod_user_id_fk").val(reporting_to_id_srfk);      
                            }
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    },error: function (jqXHR, exception) {
     	   			      $(".page-loader").hide();
    	   	          	  getErrorMessage(jqXHR, exception);
    	   	     	  }
                });
            }else{
            	  $(".page-loader").hide();
            	  if($.trim(reporting_to_id_srfk) != ''){
                      $("#hod_user_id_fk").val(reporting_to_id_srfk);      
                  }
            }
        }
        
        function getDyHodList() {
        	$(".page-loader").show();
        	$("#dy_hod_user_id_fk option:not(:first)").remove();
        	var hod_user_id_fk = $("#hod_user_id_fk").val();
            //var myParams = { hod_user_id_fk: hod_user_id_fk, dy_hod_user_id_fk: dy_hod_user_id_fk };
            var myParams = { hod_user_id_fk: hod_user_id_fk};
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/getDyHodList",
                data: myParams, cache: false,async:false,
                success: function (data) {
                    if (data.length > 0) {
                        $.each(data, function (i, val) {
                           	  var userName = '';
                     	      if($.trim(val.user_name) != ''){userName = " - "+ $.trim(val.user_name)}
                     	      var user = '${contractDeatils.dy_hod_user_id_fk}';
  	                          if(val.dy_hod_user_id_fk == user){
                                  $("#dy_hod_user_id_fk").append('<option name="'+val.reporting_to_id_srfk+'" value="' + val.dy_hod_user_id_fk + '" selected>' + $.trim(val.designation) + userName + '</option>');
                     	      }else{
                                  $("#dy_hod_user_id_fk").append('<option name="'+val.reporting_to_id_srfk+'" value="' + val.dy_hod_user_id_fk + '">' + $.trim(val.designation) + userName + '</option>');
                              }
                        });
                    }
                    $('.searchable').select2();
                    $(".page-loader").hide();
                },error: function (jqXHR, exception) {
 	   			      $(".page-loader").hide();
	   	          	  getErrorMessage(jqXHR, exception);
	   	     	  }
            });
        }
     
        function updateContract(){
        	var rowCount = $('#departmentTableBody tr').length;
        	var c = $('[name=responsible_people_id_fks]').length;
  			for(var i=1; i<= (rowCount); i++){ 
  				var resp_Person = $("#responsible_people_id_fks"+i).val();
  				if(resp_Person == ""){
  					$("#responsible_people_id_fks"+i).val("");
  					var v = $("#responsible_people_id_fks"+i).val(); 
  					$("#filecounts"+i).val(1);
  					$('#responsible_people_id_fks' + i + ' option[value=""]').hide();
  				}
  			}
        	
	  		if(validator.form()){ // validation perform
	  			$(".page-loader").show();	
	  		
	  			var estimated_cost = $('#estimated_cost').val();
	  			var awarded_cost = $('#awarded_cost').val();
	  			var completed_cost = $('#completed_cost').val();
	  			if(estimated_cost == ""){
	  				$('#estimated_cost_units').val("");
	  			}
	  			if(awarded_cost == ""){
	  				$('#awarded_cost_units').val("");
	  			}
	  			if(completed_cost == ""){
	  				$('#completed_cost_units').val("");
	  			}
	  			var bg_required = $("input[name=bg_required]:checked").val();
	  			var insurance_required = $("input[name=insurance_required]:checked").val();
	  			var contract_status_fk = $("#contract_status_fk").val();
	  			
	  			if(bg_required != 'Yes'){
	        		$("#bank_guarantee_div").remove();
	        	}
	  			if(insurance_required != 'Yes'){
	        		$("#insurance_div").remove();
	        	}
	        	if(contract_status_fk != 'Completed' && contract_status_fk != 'Closed'){
	        		$("#contractClosureDetails").remove(); 
	        	} 
	        	$('form input[name=department_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });		
	  			$('form input[name=responsible_people_id_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=bg_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });		
	  			$('form input[name=issuing_banks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bank_addresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bg_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=bg_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=release_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=bg_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=codes]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=insuranceStatus]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			
	  			$('form input[name=bank_revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=bg_valid_uptos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=remarkss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_type_fks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=issuing_agencys]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=agency_addresss]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_values]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurance_revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurence_valid_uptos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=insurence_remarks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contract_milestones_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=milestone_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=milestone_names]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=milestone_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=actual_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revisions]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=mile_remarks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			
	  			$('form input[name=contract_revision_ids]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revision_numbers]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revision_dates]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revised_amounts]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revised_docs]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=revision_remarks]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractDocumentNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractDocumentFileNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractKeyPersonnelNames]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });	
	  			$('form input[name=contractKeyPersonnelDesignations]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=contractKeyPersonnelMobileNos]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			$('form input[name=contractKeyPersonnelEmailIds]').each(function(){ if($.trim(this.value) != ''){ $(this).val(this.value.split(",").join("~$~")); } });
	  			var flag = validateContract();
	        	if(flag){
    				document.getElementById("contractForm").submit();			
    	 		}else{
    	        	$(".page-loader").hide();
    	 		}
        	}else{
        		validator;
        	}
    	}

        var validator = $('#contractForm').validate({
        	
        		 errorClass: "my-error-class",
        		   validClass: "my-valid-class", 
        		  
        	ignore: ":hidden:not(.validate-dropdown)",
        	   rules: {
        			  "project_id_fk": {
        			 		required: true
        			   },"work_id_fk": {
        		 		required: true
        		 	  },"department_fk": {
        		 		required: true
        		 	  },"contract_name": {
        		 		required: true
        		 	  },"contract_short_name":{
        		 		 required: true,
        		 		 maxlength: 100
        		 	  },"contract_type_fk": {
        		 		required: true
        		 	  },"contractor_id_fk": {
        	 		    required: true,
        	 	   	  },"scope_of_contract": {
        	 		    required: false,
        	 	   	  },"hod_user_id_fk": {
        		 		required: true
        		 	  },"dy_hod_user_id_fk": {
        	 		    required: true
        	 	   	  },"doc": {
        		 		required: false,
   				 		dateBefore1:"#date_of_start"
        		 	  },"target_doc":{
        		 		 required: false,
    				 	 dateAfterDoc:"#doc"
         	 	  	  },"awarded_cost": {
        		 		required: false
        		 	  },"date_of_start": { 
        		 		 required: true,
    				 	 dateBeforeDOS:"#loa_date"
        		 	  },"estimated_cost": {
        		 		required: false
        		 	  },"loa_letter_number": {
        		 		required: false
        		 	  },"loa_date":{
        		 		 required: false
        		 	  },"ca_no": {
        	 		    required: false
        	 	   	  },"ca_date": {
        		 		required: false
        		 	  },"actual_completion_date": {
        	 		    required: false
        	 	   	  },"completed_cost": {
        		 		required: false
        		 	  },"contract_closure_date": {
        		 		required: false,
	   				 	dateBefore3:"#completion_certificate_release"
        		 	  },"completion_certificate_release":{
        		 		 required: false,
	   				 	 dateBefore2:"#final_takeover"
        		 	  },"final_takeover": {
        	 		    required: false
        	 	   	  },"final_bill_release": {
        		 		required: false
        		 	  },"defect_liability_period": {
        	 		    required: false
        	 	   	  },"retention_money_release": {
        		 		required: false
        		 	  },"pbg_release":{
        		 		 required: false
        		 	  },"contract_closure":{
        		 		 required: false
        		 	  },"contract_status_fk":{
        		 		 required: false
        		 	  },"remarks":{
        		 		 required: false
        		 	  },"estimated_cost_units":{
        		 		 required: function(element){
        		             return $("#estimated_cost").val()!="";
        		         }
        		 	  },"awarded_cost_units":{
        		 		 required: function(element){
        		             return $("#awarded_cost").val()!="";
        		         }
        		 	  },"completed_cost_units":{
        		 		 required: function(element){
        		             return $("#completed_cost").val()!="";
        		         }
        		 	  }
        		 				
        	 	},
        	   messages: {
        			   "project_id_fk": {
        		 		required: 'Required'
        		 	  },"work_id_fk": {
        	 			required: 'Required'
        	 	  	 },"department_fk": {
        	 			required: 'Required'
        	 	  	 },"contract_name": {
        	 			required: 'Required'
        	 	  	 },"contract_short_name":{
        	 	  		required: 'Required',
        	 	  		maxlength : 'Contract short name must be less than or equal to 100 characters'
       		 	  	 },"contract_type_fk": {
        	 			required: 'Required'
        	 	  	 },"contractor_id_fk": {
        	 			required: 'Required'
        	 	  	 },"scope_of_contract": {
        	 	  		required: 'Required'
        	 	   	  },"hod_user_id_fk": {
        	 			required: 'Required'
        	 	  	 },"dy_hod_user_id_fk": {
        	 			required: 'Required'
        	 	  	 },"doc": {
        	 			required: 'Required'
        	 	  	 },"target_doc":{
        	 	  		required: 'Required'
        	 	  	 },"awarded_cost": {
       			 		required: 'Required'
       			 	  },"date_of_start": {
       			 		required: 'Required'
       			 	  },"estimated_cost": {
       			 		required: 'Required'
       			 	  },"loa_letter_number": {
       			 		required: 'Required'
       			 	  },"loa_date":{
       			 		 required: 'Required'
       			 	  },"ca_no": {
       		 		    required: 'Required',
       		 	   	  },"ca_date": {
       			 		required: 'Required'
       			 	  },"actual_completion_date": {
       		 		    required: 'Required'
       		 	   	  },"completed_cost": {
       			 		required: 'Required'
       			 	  },"contract_closure_date": {
       			 		required: 'Required'
        		 	  },"completion_certificate_release":{
        		 		 required: 'Required'
        		 	  },"final_takeover": {
        		 		 required: 'Required'
        	 	   	  },"final_bill_release": {
        	 	   		required: 'Required'
        		 	  },"defect_liability_period": {
        		 		 required: 'Required'
        	 	   	  },"retention_money_release": {
        	 	   		required: 'Required'
        		 	  },"pbg_release":{
        		 		 required: 'Required'
        		 	  },"contract_closure":{
        	 	  		required: 'Required'
        		 	  },"contract_status_fk":{
        	 	  		required: 'Required'
        		 	  },"remarks":{
        	 	  		required: 'Required'
        		 	  },"estimated_cost_units":{
        	 	  		required: 'Required'
        		 	  },"awarded_cost_units":{
        	 	  		required: 'Required'
        		 	  },"completed_cost_units":{
        	 	  		required: 'Required'
        		 	  }
        	 				      
            },
        	  errorPlacement:
        	 	function(error, element){
        			if (element.attr("id") == "project_id_fk" ){
        		 		     document.getElementById("project_id_fkError").innerHTML="";
        		 			 error.appendTo('#project_id_fkError');
        		 	    }else if (element.attr("id") == "work_id_fk" ){
	        	 		     document.getElementById("work_id_fkError").innerHTML="";
	        	 			 error.appendTo('#work_id_fkError');  	
	        	 	    }else if (element.attr("id") == "department_fk" ){
	        	 		     document.getElementById("department_fkError").innerHTML="";
	        	 			 error.appendTo('#department_fkError');
	        	 	    }else if (element.attr("id") == "contract_name" ){
	        	 		     document.getElementById("contract_nameError").innerHTML="";
	        	 			 error.appendTo('#contract_nameError');
	        	 	    }else if (element.attr("id") == "contract_short_name" ){
	        	 		     document.getElementById("contract_short_nameError").innerHTML="";
	        	 			 error.appendTo('#contract_short_nameError');
	        	 	    }else if (element.attr("id") == "contract_type_fk" ){
	        	 		     document.getElementById("contract_type_fkError").innerHTML="";
	        	 			 error.appendTo('#contract_type_fkError');
	        	 	    }else if (element.attr("id") == "contractor_id_fk" ){
	          	 		     document.getElementById("contractor_id_fkError").innerHTML="";
	           	 			 error.appendTo('#contractor_id_fkError');
	           	 	    }else if (element.attr("id") == "scope_of_contract" ){
	        	 		     document.getElementById("scope_of_contractError").innerHTML="";
	        	 			 error.appendTo('#scope_of_contractError');
	        	 	    }else if (element.attr("id") == "hod_user_id_fk" ){
	          	 		     document.getElementById("hod_user_id_fkError").innerHTML="";
	           	 			 error.appendTo('#hod_user_id_fkError');
	           	 	    }else if (element.attr("id") == "dy_hod_user_id_fk" ){
	           	 		     document.getElementById("dy_hod_user_id_fkError").innerHTML="";
	           	 			 error.appendTo('#dy_hod_user_id_fkError');
	           	 	    }else if (element.attr("id") == "doc" ){
	        	 		     document.getElementById("docError").innerHTML="";
	        	 			 error.appendTo("#docError");
	        	 	    }else if (element.attr("id") == "target_doc" ){
	        	 		     document.getElementById("target_docError").innerHTML="";
	        	 			 error.appendTo("#target_docError");
	        	 	    }else if (element.attr("id") == "awarded_cost" ){
	        	 		     document.getElementById("awarded_costError").innerHTML="";
	        	 			 error.appendTo('#awarded_costError');
	        	 	    }else if (element.attr("id") == "date_of_start" ){
        	 	    	     document.getElementById("date_of_startError").innerHTML="";
        	 			     error.appendTo('#date_of_startError');
        		 	    }else if (element.attr("id") == "estimated_cost" ){
        		 		     document.getElementById("estimated_costError").innerHTML="";
        		 			 error.appendTo('#estimated_costError');
        		 	    }else if (element.attr("id") == "loa_letter_number" ){
        		 		     document.getElementById("loa_letter_numberError").innerHTML="";
        		 			 error.appendTo('#loa_letter_numberError');
        		 	    }else if (element.attr("id") == "loa_date" ){
        		 		     document.getElementById("loa_dateError").innerHTML="";
        		 			 error.appendTo('#loa_dateError');
        		 	    }else if (element.attr("id") == "ca_no" ){
        		 		     document.getElementById("ca_noError").innerHTML="";
        		 			 error.appendTo('#ca_noError');
        		 	    }else if (element.attr("id") == "ca_date" ){
        		 		     document.getElementById("ca_dateError").innerHTML="";
        		 			 error.appendTo('#ca_dateError');
        		 	    }else if (element.attr("id") == "actual_completion_date" ){
        		 		     document.getElementById("actual_completion_dateError").innerHTML="";
        		 			 error.appendTo('#actual_completion_dateError');
        		 	    }else if (element.attr("id") == "completed_cost" ){
        		 		     document.getElementById("completed_costError").innerHTML="";
        		 			 error.appendTo('#completed_costError');
        		 	    }else if (element.attr("id") == "contract_closure_date" ){
        		 		     document.getElementById("contract_closure_dateError").innerHTML="";
        		 			 error.appendTo('#contract_closure_dateError');
        		 	    }else if (element.attr("id") == "completion_certificate_release" ){
        		 		     document.getElementById("completion_certificate_releaseError").innerHTML="";
        		 			 error.appendTo('#completion_certificate_releaseError');
        		 	    }else if (element.attr("id") == "final_takeover" ){
        		 		     document.getElementById("final_takeoverError").innerHTML="";
        		 			 error.appendTo('#final_takeoverError');
        		 	    }else if (element.attr("id") == "final_bill_release" ){
        		 		     document.getElementById("final_bill_releaseError").innerHTML="";
        		 			 error.appendTo('#final_bill_releaseError');
        		 	    }else if (element.attr("id") == "defect_liability_period" ){
        		 		     document.getElementById("defect_liability_periodError").innerHTML="";
        		 			 error.appendTo('#defect_liability_periodError');
        		 	    }else if (element.attr("id") == "final_retention_release" ){
        		 		     document.getElementById("final_retention_releaseError").innerHTML="";
        		 			 error.appendTo('#final_retention_releaseError');
        		 	    }else if (element.attr("name") == "pbg_release" ){
        		 		     document.getElementById("pbg_releaseError").innerHTML="";
        		 			 error.appendTo('#pbg_releaseError');
        		 	    }else if (element.attr("id") == "contract_closure" ){
        		 		     document.getElementById("ontract_closureError").innerHTML="";
        		 			 error.appendTo('#ontract_closureError');
        		 	    }else if (element.attr("id") == "contract_status_fk" ){
        	 		     document.getElementById("contract_status_fkError").innerHTML="";
        	 			 error.appendTo('#contract_status_fkError');
	        	 	    }else if (element.attr("id") == "estimated_cost_units" ){
        	 		     document.getElementById("estimated_cost_unitsError").innerHTML="";
        	 			 error.appendTo('#estimated_cost_unitsError');
	        	 	    }else if (element.attr("id") == "awarded_cost_units" ){
        	 		     document.getElementById("awarded_cost_unitsError").innerHTML="";
        	 			 error.appendTo('#awarded_cost_unitsError');
	        	 	    }else if (element.attr("id") == "completed_cost_units" ){
        	 		     document.getElementById("completed_cost_unitsError").innerHTML="";
        	 			 error.appendTo('#completed_cost_unitsError');
	        	 	    }else if (element.attr("id") == "remarks" ){
	       	 		     document.getElementById("remarksError").innerHTML="";
	    	 			 error.appendTo('#remarksError');}
      
        	 },invalidHandler: function (form, validator) {
                 var errors = validator.numberOfInvalids();                 
                 if (errors) {
                     var position = validator.errorList[0].element;
                     var eleDivId=$(position).parentsUntil('form');
                     var l=eleDivId.push();
                     $('.tab-flex .tab > a').removeClass('active');
                     $('.tab-flex .tab > a').each(function(){
		   			 		$($(this).attr('href')).css('display','none');
		   			 	});
		    		 $('[href="#'+eleDivId[l-1].id+'"]').addClass('active');
		    		 $('.tabs').tabs();
                     jQuery('html, body').animate({
                         scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                     }, 1000); 
                 }validateContract();
             },submitHandler: function(form) {
            	// alert($('#awarded_cost').val());
        	   
        	  }
        });
        $.validator.addMethod("dateBeforeDOS", function(value, element) {
            var fromDateString = $('#loa_date').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return true;
            }else{
            	return true;
            }
        }, "Date of Start must be after LOA Date");
       /*  
        $('#loa_date').change(function(){
                var fromDateString = $('#loa_date').val();
                var fromDateParts = fromDateString.split("-");
                var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

                var toDateParts = $('#date_of_start').val().split("-");
                var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
                if($.trim(fromDateString) != '' && $.trim(toDateParts) != ''){
                	if( Date.parse(fromDate) <= Date.parse(toDate)){
                    	$('#date_of_startError').text("")
                	}else{
                		$('#date_of_startError').text("Date of Start must be after LOA Date").addClass("my-error");
                	}
                }
        });
         */
        $.validator.addMethod("dateBefore1", function(value, element) {
            var fromDateString = $('#date_of_start').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
            
        }, "Planned Doc date must be after Date of start");
        
        $.validator.addMethod("dateAfterDoc", function(value, element) {
            var fromDateString = $('#loa_date').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
            
            var today = new Date();
        	var dd = today.getDate();

        	var mm = today.getMonth()+1; 
        	var yyyy = today.getFullYear();
        	if(dd<10) {
        	    dd='0'+dd;
        	} 
        	if(mm<10){
        	    mm='0'+mm;
        	} 
            var today_date = new Date(yyyy,mm-1,dd);
            
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return (Date.parse(toDate) >= Date.parse(fromDate) && Date.parse(toDate) >= Date.parse(today_date));
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
                return Date.parse(toDate) >= Date.parse(today_date);
            }else{
            	return true;
            }
            
        }, "Target DOC must be after LOA Date or Today");
        
    	
    	$.validator.addMethod("dateBefore2", function(value, element) {
            var fromDateString = $('#final_takeover').val();
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
         
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
        }, "Release of Completion Certificate date must be after Final takeover by client date");
    	
    	$.validator.addMethod("dateBefore3", function(value, element) {
            var fromDateString = $('#completion_certificate_release').val(); 
            var fromDateParts = fromDateString.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 

            var toDateParts = value.split("-");
            // month is 0-based, that's why we need dataParts[1] - 1
            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
         
            if($.trim(fromDateString) != '' && $.trim(value) != ''){
            	return Date.parse(fromDate) <= Date.parse(toDate);
            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
            	return false;
            }else{
            	return true;
            }
        }, "Contract closure date must be after Release of Completion Certificate date"); 

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
    	
        function addBankRow(){
    		
		          var rowNo = $("#bankRowNo").val();
		          var rNo = Number(rowNo)+1;
		          var total = 0;
		          var html = '<tr id="bankRow'+rNo+'"> <td data-head="Code " class="input-field"> <input id="codes'+rNo+'" type="text" class="validate" name="codes" placeholder="Code">       </td><td data-head="BG Type " class="input-field"> '
		  		   +'<select  name="bg_type_fks" id="bg_type_fks'+rNo+'" class="searchable">'	   			
		  		   +'<option value="" >select</option>'
		  		 	<c:forEach var="obj" items="${bankGuaranteeTYpe }">
				  +'<option value="${obj.bg_type_fk }">${obj.bg_type_fk }</option>'
					</c:forEach>
		  		   +'</select></td>'
				   +'<td data-head="Issuing Bank " class="input-field"> <input id="issuing_banks'+rNo+'" name="issuing_banks"  type="text" class="validate"  placeholder="Issuing Bank"></td>'
				  // +'<td><input id="bank_addresss'+rNo+'" name ="bank_addresss" type="text" class="validate"  placeholder="Bank Address"></td>'
				   +'<td data-head="BG / FDR Number " class="input-field"><input id="bg_numbers'+rNo+'" name="bg_numbers" type="text" class="validate"  placeholder="BG / FDR Number"></td>'
				   //+'<td class="input-field"><i class="material-icons prefix cost left-align">₹</i><input id="bg_values'+rNo+'" name="bg_values" type="number" min="0.01" step="0.01" class="validate"  placeholder="Amount"></td>'
				   +'<td data-head="Amount " class="input-field"> <i class="material-icons prefix cost left-align">₹</i> <input id="bg_values'+rNo+'" name="bg_values" min="0.01" step="0.01" type="number" class="validate" placeholder="Amount">'
				   +'</td><td class="responsive_units">		<select class="units validate-dropdown" id="bg_values_units'+rNo+'" name="bg_value_unitss">'
				   +'<option value="">Select</option>'
				    <c:forEach var="obj" items="${unitsList }">
  			     	 +'<option value="${obj.value }">${obj.unit }</option>'
   			    	</c:forEach>	
				   +'</select><span id="bg_units'+rNo+'Error" class="my-error"></span></div> </td>'
				   +'<td data-head="BG / FDR Date " class="input-field"><input id="bg_dates'+rNo+'" name="bg_dates" type="text" class="validate datepicker" placeholder="BG /FDR Date"> <button type="button"><i class="fa fa-calendar"></i></button>'
				   //+'<td><input id="bank_revisions'+rNo+'" name="bank_revisions" type="text" class="validate"  placeholder="Revision"></td>'
				   +'<td data-head="Expiry Date " class="input-field"><input id="bg_valid_uptos'+rNo+'" name="bg_valid_uptos" type="text" class="validate datepicker"  placeholder="Expiry Date"><button type="button"><i class="fa fa-calendar"></i></button></td>'
				   //+'<td><input id="remarkss'+rNo+'" name ="remarkss" type="text" class="validate" value="${bankObj.remarks }" placeholder="Remarks"></td>'
				   //+'<td><label> <input type="checkbox" name="bankStatus" id="bankStatus'+rNo+'" value="Inactive"/> <span></span> </label></td>'
				   +'<td data-head="Release Date" class="input-field"><input id="release_dates'+rNo+'" name="release_dates" type="text" class="validate datepicker" placeholder="Release Date"> <button type="button"><i class="fa fa-calendar"></i></button></td>'
				   +'<td class="mobile_btn_close"><a  class="btn waves-effect waves-light red t-c " onclick="removeBank('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
			 
				 $('#bankTableBody').append(html);
				 $("#bankRowNo").val(rNo);
				 $('.searchable').select2();
				 $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
				 $("#bg_valid_uptos"+rNo).datepicker({
		         	 format:'dd-mm-yyyy',
		             onSelect: function () {
		  	    	     $('.confirmation-btns .datepicker-done').click();
		  	    	  }
		         });
				 
		             $('#bg_values_units'+rNo).on('change', function(e){
		            	 if($.trim($('#bg_values_units'+rNo).val()) != ""){
		            		 $('#bg_units'+rNo+'Error').text('');
		            	 }
		             });
				
		} 
		
		
		function removeBank(rowNo){
			$("#bankRow"+rowNo).remove();
		}
		
		function addInsurenceRow(){
			
		    var rowNo = $("#insurenceRowNo").val();
		    var rNo = Number(rowNo)+1;
		    console.log(rNo)
		    var html = '<tr id="insurenceRow'+rNo+'"><td data-head="Insurance Type " class="input-field">'
			   +'<select  name="insurance_type_fks" id="insurance_type_fks'+rNo+'"  class="searchable">'	   			
			   +'<option value="" >select</option>'
			   <c:forEach var="obj" items="${insurance_type }">
				  +' <option value= "${ obj.insurance_type}">${ obj.insurance_type}</option>'
			  </c:forEach>
			   +'</select></td>'
			   +'<td data-head="Issuing Agency " class="input-field"> <input id="issuing_agencys'+rNo+'" name="issuing_agencys" type="text" class="validate"  placeholder="Issuing Agency"></td>'
			   +'<td data-head="Agency Address " class="input-field"><input id="agency_addresss'+rNo+'" name="agency_addresss" type="text" class="validate" placeholder="Agency Address"></td>'
			   +'<td data-head="Insurance Number " class="input-field"><input id="insurance_numbers'+rNo+'" name="insurance_numbers" type="text" class="validate"  placeholder="Insurance Number"></td>'
			  // +'<td class="input-field"><i class="material-icons prefix cost left-align">₹</i><input id="insurance_values'+rNo+'" name="insurance_values" type="number" min="0.01" step="0.01" class="validate" placeholder="Insurance Value"></td>'
			   +'<td data-head="Insurance Value " class="input-field responsive_units"> <i class="material-icons prefix cost left-align">₹</i> <input id="insurance_values'+rNo+'" name="insurance_values" '
			   +'min="0.01" step="0.01" type="number" class="validate" placeholder="Insurance Value"> </td><td class="responsive_units"> <select class="units validate-dropdown" id="insurance_values_units'+rNo+'" name="insurance_value_unitss">'
			   +'<option value="">Select</option>'
			   <c:forEach var="obj" items="${unitsList }">
		     	 +'<option value="${obj.value }">${obj.unit }</option>'
			    </c:forEach>
			   +'</select> <span id="insurence_units'+rNo+'Error" class="my-error"></span></div> </td>' 
			   +'<td data-head="Revision " class="input-field"><input id="insurance_revisions'+rNo+'" name="insurance_revisions" type="text" class="validate" placeholder="Revision"></td>'
			   +'<td data-head="Valid Upto " class="input-field"><input id="insurence_valid_uptos'+rNo+'" name="insurence_valid_uptos" type="text" class="validate datepicker" placeholder="Valid Upto"> <button type="button"><i class="fa fa-calendar"></i></button></td>'
			   +'<td data-head="Remarks " class="input-field"><input id="insurence_remarks'+rNo+'" name="insurence_remarks"  type="text" class="validate"  placeholder="Remarks"></td>'
			   +'<td data-head="Release" class="input-field"><p><label> <input type="hidden" id="insuranceStatus'+rNo+'" name="insuranceStatus" value="No" /><input type="checkbox" id="insuranceStatuss'+rNo+'" /> <span></span> </label></p></td>'
			   +'<td class="mobile_btn_close"><a  class="btn waves-effect waves-light red t-c " onclick="removeInsurence('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
		 		  
			 $('#insurenceTableBody').append(html);
			 $("#insurenceRowNo").val(rNo);
			 $('.searchable').select2();
			 $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
			 $("#insurence_valid_uptos"+rNo).datepicker({
			      	 format:'dd-mm-yyyy',
			          onSelect: function () {
				    	     $('.confirmation-btns .datepicker-done').click();
				    	  }
			 });
			 $("#insuranceStatuss"+rNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                	// $(".part").prop('disabled', true);
                     $("#insuranceStatus"+rNo).val('Yes');
                 } else{
                  	 
                	  $("#insuranceStatus"+rNo).prop('checked',false).removeAttr('checked');
                	  $("#insuranceStatus"+rNo).val('No')
                  }
       	    });
			  
	             $('#insurance_values_units'+rNo).on('change', function(e){
	            	 if($.trim($('#insurance_values_units'+rNo).val()) != ""){
	            		 $('#insurence_units'+rNo+'Error').text('');
	            	 }
	             });
		} 
		
		
		function removeInsurence(rowNo){
			$("#insurenceRow"+rowNo).remove();
		}
		
		function addMilestoneRow(){
			//var rowCount = $('#milestoneTableBody tr').length;
		    var rowNo = $("#mileRowNo").val();
		    var rNo = Number(rowNo)+1;
		    var total = 0;
		    var html = '<tr id="mileRow'+rNo+'">'
		 	   +'<td data-head="Milestone ID " class="input-field"><input type="hidden" name= "contract_milestones_ids" id="contract_milestones_ids'+rNo+'" /><input id="milestone_ids'+rNo+'" name="milestone_ids" type="text" class="validate" placeholder="Milestone ID" value="K-'+(rNo+1)+'"></td>'
			   +'<td data-head="Milestone Name " class="input-field"><input id="milestone_names'+rNo+'" name="milestone_names" type="text" class="validate"  placeholder="Milestone Name "></td>'
			   +'<td data-head="Milestone Date " class="input-field"><input id="milestone_dates'+rNo+'" name="milestone_dates" type="text" class="validate datepicker"  placeholder="Milestone Date"><button type="button"><i class="fa fa-calendar"></i></button></td>'
			   +'<td data-head="Actual Date " class="input-field"><input id="actual_dates'+rNo+'" name="actual_dates" type="text" class="validate datepicker"   placeholder="Actual Date">  <button type="button"><i  class="fa fa-calendar"></i></button></td>'
			   +'<td data-head="Revision" class="input-field" ><input id="revisions'+rNo+'" name="revisions" type="text" class="validate" placeholder="Revision"></td>'
			   +'<td data-head="Remarks " class="input-field">  <input id="mile_remarks'+rNo+'" name="mile_remarks" type="text" class="validate" placeholder="Remarks"></td>'
		 	   +'<td class="mobile_btn_close"><a  class="btn waves-effect waves-light red t-c " onclick="removeMilestone('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
			   +'</tr>';
		
			 $('#milestoneTableBody').append(html);
			 $("#mileRowNo").val(rNo);
			 $('.searchable').select2();
			 
			 $("#milestone_dates"+rNo).datepicker({
			      	 format:'dd-mm-yyyy',
			          onSelect: function () {
				    	     $('.confirmation-btns .datepicker-done').click();
				    	  }
			      });
			 $("#actual_dates"+rNo).datepicker({
			      	 format:'dd-mm-yyyy',
			          onSelect: function () {
				    	     $('.confirmation-btns .datepicker-done').click();
				    	  }
			      });
		
		} 
		
		
		function removeMilestone(rowNo){
			$("#mileRow"+rowNo).remove();
		}
		function addRevRow(){
			
		    var rowNo = $("#revRowNo").val();
		    var rNo = Number(rowNo)+1;
		    var total = 0;
		    var html = '<tr id="revRow'+rNo+'">'
			   +'<td data-head="Revision Number " class="input-field"><input id="revision_numbers'+rNo+'" name="revision_numbers" type="text" class="validate"  placeholder="Revision Number"  value="R-'+(rNo+1)+'" /></td>'
			   +'<td data-head="Revised Amount " class="input-field  "> <i class="material-icons prefix cost left-align">₹</i>  <input id="revised_amounts'+rNo+'" '
			   +'name="revised_amounts" min="0.01" step="0.01" type="number" class="validate" onkeyup="toggleRevision(' + '\'amounts\'' + ',' + rNo + ')"  placeholder="Revised Amount"> </td><td class="responsive_units  "> <select class="units validate-dropdown " id="revised_amounts_units'+rNo+'" name="revised_amount_unitss">'
			   +'<option value="">Select</option>'
			   <c:forEach var="obj" items="${unitsList }">
		     	 +'<option value="${obj.value }">${obj.unit }</option>'
			   </c:forEach>			  
		       +'</select> <span id="units'+rNo+'Error" class="my-error"></span></div> </td>'
		       +'<td data-head="Current" class="input-field   p-h-0"> <p> <label><input type="hidden" id="revision_amounts_statuss'+rNo+'"  name="revision_amounts_statuss" class="hidden_check hidden_amount" value="No" /> '
		       +'<input type="checkbox"  id="revision_amounts_status'+rNo+'" disabled onchange="revisionChecks(' + '\'amounts\'' + ',' + rNo + ')" class="revision_amount_status"/> <span></span> </label> </p> </td> '
			   +'<td data-head="Revised DOC" class="input-field  "><input id="revised_docs'+rNo+'" name="revised_docs" type="text" class="validate datepicker" onchange="toggleRevision(' + '\'docs\'' + ',' + rNo + ')"  placeholder="Revised DOC">'
			   +'<button type="button"><i class="fa fa-calendar"></i></button></td>'
			   +'<td data-head="Current" class="input-field   p-h-0"><p><label> <input type="hidden" id="revision_statuss'+rNo+'" name="revision_statuss" class="hidden_check hidden_doc" value="No" /><input type="checkbox" class="revision_doc_status" disabled onchange="revisionChecks(' + '\'docs\'' + ',' + rNo + ')"  id="revision_status'+rNo+'" /> <span></span> </label></p></td>'
			   +'<td data-head="Remarks" class="input-field"> <input id="revision_remarks'+rNo+'" name="revision_remarks" type="text" class="validate"  placeholder="Remarks"></td>'
		 	   +'<td class="mobile_btn_close"><a  class="btn waves-effect waves-light red t-c " onclick="removeRev('+rNo+');"> <i class="fa fa-close"></i></a></td></tr>';
			   +'</tr>';
		
			 $('#revTableBody').append(html);
			 $("#revRowNo").val(rNo);
			 $('searchable').select2();
			 $('.units').select2({        	dropdownCssClass : 'cost_dropdown'        });
			 $("#revised_docs"+rNo).datepicker({
			      	 format:'dd-mm-yyyy',
			          onSelect: function () {
				    	     $('.confirmation-btns .datepicker-done').click();
				    	  }
			      });
            
             $('#revision_status'+rNo).on('change', function(e){
                  if($(this).prop('checked'))
                  {
                      $('#revision_statuss'+rNo).val('Yes');
                  } else{
                   	 
                 	  $("#revision_statuss"+rNo).prop('checked',false).removeAttr('checked');
                 	  $("#revision_statuss"+rNo).val('No')
                   }
        	    });
             $('#revision_amounts_status'+rNo).on('change', function(e){
                 if($(this).prop('checked'))
                 {
                     $('#revision_amounts_statuss'+rNo).val('Yes');
                 } else{
                  	 
                	  $("#revision_amounts_statuss"+rNo).prop('checked',false).removeAttr('checked');
                	  $("#revision_amounts_statuss"+rNo).val('No')
                  }
       	    });
           
             $('#revised_amounts_units'+rNo).on('change', function(e){
            	 if($.trim($('#revised_amounts_units'+rNo).val()) != ""){
            		 $('#units'+rNo+'Error').text('');
            	 }
             });
		} 
		
		function removeRev(rowNo){
			$("#revRow"+rowNo).remove();
		}
		
		
		function addKeyPersonnelRow(){		
			 var rowNo = $("#keyRowNo").val();
			 var rNo = Number(rowNo)+1;
			 var total = 0;
			 var html = '<tr id="keyPersonnelRow'+rNo+'">'
						 +'<td data-head="Name " class="input-field"> <input id="contractKeyPersonnelNames'+rNo+'" name="contractKeyPersonnelNames" type="text" class="validate" placeholder="Name">'
						 +'</td>'
						 +'<td data-head="Designation " class="input-field"> <input id="contractKeyPersonnelDesignations'+rNo+'" name="contractKeyPersonnelDesignations" type="text" class="validate" placeholder="Name">'
						 +'</td>'
						 +'<td data-head="Mobile No " class="input-field">'
						 +'<input id="contractKeyPersonnelMobileNos'+rNo+'" name="contractKeyPersonnelMobileNos" type="number" class="validate" placeholder="Mobile No">'
						 +'</td>'
						 +'<td data-head="Email ID " class="input-field">'
						 +'<input id="contractKeyPersonnelEmailIds'+rNo+'" name="contractKeyPersonnelEmailIds" type="text" class="validate" placeholder="Email">'
						 +'</td>'
						 +'<td class="mobile_btn_close">'
						 +'<a href="javascript:void(0);" onclick="removeKeyPersonnel('+rNo+');"  class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
						 +'</td>'
				   		 +'</tr>';
			
				 $('#keyPersonnelTableBody').append(html);
				 $("#keyRowNo").val(rNo);
		}
		
		function removeKeyPersonnel(rowNo){
			$("#keyPersonnelRow"+rowNo).remove();
		}

		function addContractDocumentRow(){		
			 var rowNo = $("#documentRowNo").val();
			 var rNo = Number(rowNo)+1;
			 var total = 0;
			 var html = '<tr id="contractDocumentRow'+rNo+'">'
						 +'<td data-head="File Type " class="input-field">'
								+'<select  name="contract_file_types"  id="contract_file_types'+rNo+'"  class="validate-dropdown searchable">'
			    					+ '<option value="" >--Select--</option>'
			          			  <c:forEach var="obj" items="${contractFileTypeList}">
						  				+ '<option value="${obj.contract_file_type }">${obj.contract_file_type}</option>'
			           			  </c:forEach>
							+ '</select></td>'
						 +'<td data-head="Name " class="input-field"> <input id="contractDocumentNames'+rNo+'" name="contractDocumentNames" type="text" class="validate" placeholder="Name"> </td>'
						 +'<td data-head="Attachment" class="input-field">'
						 +'<span class="normal-btn">'
						 +'<input type="file" id="contractDocumentFiles'+rNo+'" name="contractDocumentFiles" style="display:none" onchange="getFileName('+rNo+')" />'
						 +'<label for="contractDocumentFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
						 +'<input type="hidden" id="contractDocumentFileNames'+rNo+'" name="contractDocumentFileNames">'
						 +'<span id="contractDocumentFileName'+rNo+'" class="filevalue"></span>'
						 +'</span>'
						 +'</td>'
						 +'<td><input type="hidden" id="contract_file_ids'+rNo+'" name="contract_file_ids"/></td>'
						 +'<td class="mobile_btn_close">'
						 +'<a href="javascript:void(0);" onclick="removeContractDocument('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
						 +'</td>'
				   		 +'</tr>';
			
				 $('#contractDocumentTableBody').append(html);
				 $("#documentRowNo").val(rNo);
				 $('.searchable').select2();
		         $("#contract_file_ids0").val('');
		} 
		function removeContractDocument(rowNo){
			$("#contractDocumentRow"+rowNo).remove();
		}
		
		
		function getFileName(rowNo){
			var filename = $('#contractDocumentFiles'+rowNo)[0].files[0].name;
		    $('#contractDocumentFileName'+rowNo).html(filename);
		    $('#contractDocumentFileNames'+rowNo).val(filename);
		}

		function revisionChecks(s, no) {
            var type = (s == 'amounts') ? 'amount' : (s == 'docs') ? 'doc' : '';
            var clsType = (s == 'amounts') ? '_amounts_' : (s == 'docs') ? '_' : '';
            
            //list all the elements
            var listing=$('.revision_'+type+'_status');    
            
            $.each(listing,function(no,val){
            	if(!$(val).attr('disabled')){      
            		console.log($(val))
            		$('.hidden_'+type).val('No');
            		$(val).prop('checked', false);            		
            	}            	
            })
           	$("#revision"+clsType+"status"+no).prop('checked', true);
           	$("#revision"+clsType+"statuss"+no).val('Yes');           
        }

        function toggleRevision(s, no) {
            var type = (s == 'docs') ? '' : (s == 'amounts') ? '_amounts' : '';
            var idType = (s == 'docs') ? '_docs' : (s == 'amounts') ? '_amounts' : '';

            if ($('#revised' + idType + no).val() != '') {
                $('#revision' + type + "_status" + no).removeAttr('disabled');
            } else {
            	$('#revision' + type + "_status" + no).prop('checked', false);
                $('#revision' + type + "_status" + no).attr('disabled', true);
            }
        }

		function validateContract(){
			var flag = true;
			$("input[name=bg_values]").each(function(){
				var idNo = (this.id).replace('bg_values','');
				var bg_value_units = $("#bg_values_units"+idNo).val();
				var bg_values = $("#bg_values"+idNo).val();
				if($.trim(bg_values) == ""){$("#bg_values_units"+idNo).val("");}
				if(idNo === ""){
	       				idNo = 0;
	       		}
	       		if($.trim(bg_value_units) == "" && bg_values != ""){
					$('#bg_units'+idNo+'Error').text('Requried');
					$('#bg_values_units'+idNo).slideDown(100,function(){
						$(this).focus();
					});
					flag = false;
				}
			});
			$("input[name=insurance_values]").each(function(){
	       		var idNo = (this.id).replace('insurance_values','');
	       		var insurance_value_units = $("#insurance_values_units"+idNo).val();
	       		var insurance_values = $("#insurance_values"+idNo).val();
				if($.trim(insurance_values) == ""){$("#insurance_values_units"+idNo).val("");}
	       		if(idNo === ""){
	       				idNo = 0;
	       		}
	       		if($.trim(insurance_value_units) == "" && $('#insurance_values'+idNo).val() != ""){
					$('#insurence_units'+idNo+'Error').text('Requried');
					$('#insurance_values_units'+idNo).slideDown(100,function(){
						$(this).focus();
					});
					flag = false;
				}
			});
			$("input[name=revised_amounts]").each(function(){
	       		var idNo = (this.id).replace('revised_amounts','');
	       		var revised_amount_units = $("#revised_amounts_units"+idNo).val();
	       		var revised_amounts = $("#revised_amounts"+idNo).val();
				if($.trim(revised_amounts) == ""){$("#revised_amounts_units"+idNo).val("");}
	       		if(idNo === ""){
	       				idNo = 0;
	       		}
	       		if( $.trim(revised_amount_units) == "" && $('#revised_amounts'+idNo).val() != ""){
					$('#units'+idNo+'Error').text('Requried');
					$('#revised_amounts_units'+idNo).slideDown(100,function(){
						$(this).focus();
					});
					flag = false;
				} 
			});
			return flag;
		}
		
		$('select[name=bg_value_unitss]').change(function(key, element){
			$("input[name=bg_values]").each(function(){
				var idNo = (this.id).replace('bg_values',''); 
        		if($.trim(this.value) != "" && $('#bg_values_units'+idNo).val() == ""){ 
        			$('#bg_units'+idNo+'Error').text('Requried');
				}else{
					$('#bg_units'+idNo+'Error').text('');
				}
            });
			
		});
		$('select[name=insurance_value_unitss]').change(function(key, element){
			
			$("input[name=insurance_values]").each(function(){
				var idNo = (this.id).replace('insurance_values','');
        		if( ($.trim(this.value)) != "" && $('#insurance_values_units'+idNo).val() == ""){
        			$('#insurence_units'+idNo+'Error').text('Requried');
				}else{
					$('#insurence_units'+idNo+'Error').text('');
				}
            });
			
		});
		$('select[name=revised_amount_unitss]').change(function(key, element){
			
			$("input[name=revised_amounts]").each(function(){
				var idNo = (this.id).replace('revised_amounts','');
        		if($.trim(this.value) != "" && $('#revised_amounts_units'+idNo).val() == ""){
        			$('#units'+idNo+'Error').text('Requried');
        			
				}else{
					$('#units'+idNo+'Error').text('');
				}
            });
			
		});
		
		function getStatusLIst(){
		  	$(".page-loader").show();
		  	var contract_status = $('#contract_status').val();
            $("#contract_status_fk option:not(:first)").remove();
            if ($.trim(contract_status) != "") {
                var myParams = { contract_status: contract_status };
                $.ajax({
                	url: "<%=request.getContextPath()%>/ajax/getContractStatusLIstFormContractFom",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                $("#contract_status_fk").append('<option status="'+val.contract_status +'" value="' + val.contract_status_fk + '">' + $.trim(val.contract_status_fk) +  '</option>');
                            });
                        }
                        $('.searchable').select2();
                        $(".page-loader").hide();
                    }
                });
            }else{
            	$(".page-loader").hide();
            }
		}
		
		function setContractStatus(){
			$(".page-loader").show();        	
			var contract_status_fk = $('#contract_status_fk').val();
       		if($.trim(contract_status_fk) != ''){  
       			var status = $("#contract_status_fk").find('option:selected').attr("status");
       			$("#contract_status").val(status);
       			$("#contract_status").select2();
       		}
       		$(".page-loader").hide();
		}
	  </script>

</body>

</html>