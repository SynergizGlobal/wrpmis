<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
    	 <c:if test="${action eq 'edit'}">Update Land Acquisition - Update Forms - PMIS</c:if>
		 <c:if test="${action eq 'add'}"> Add Land Acquisition - Update Forms - PMIS</c:if>
    </title>
    <link rel="icon" type="image/png" sizes="96x96" href="/pmis/resources/images/favicon.png">
    <link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/material-design-lite-v.1.0.css">
    <link rel="stylesheet" href="/pmis/resources/css/datatable-material.css">
    <!-- <link rel="stylesheet" href="/pmis/resources/css/la.css"> -->
    <link rel="stylesheet" href="/pmis/resources/css/rits.css">
    <link rel="stylesheet" href="/pmis/resources/css/select2.min.css">
    <link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-form-template.css" >
    <link rel="stylesheet" media="screen and (max-device-width: 820px)" href="/pmis/resources/css/mobile-responsive-table.css" >
    <style>
    .m0{margin:0;}
    .w0{width: 0;}
    .area{
    		display:none; 
		    clear:both; 
		    padding: 10px;
		}
        .radiogroup {
            box-shadow: 1px 1px 3px 0px #ccc;
            padding: 5px;
            text-align: center;
        }

        .primary-text-bold {
            color: #007A7A !important;
            font-weight: 600
        }

        .input-field .prefix {
            color: #999;
        }

        .input-field .prefix.active {
            color: #007A7A;
        }
		input[type="number"]~.units {
		    position: absolute;
		    right: 15px;
		    top: 15px;
		    border: 0;
		    opacity: 0.7;
		}
        h6.primary-text-bold.center-align {
            margin: 20px auto;
        }

        .input-field .searchable_label {
            font-size: 0.85rem;
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
        .error-msg label{color:red!important;}
        
        .pt-5 {
		    padding-top: .3rem !important;
		}
		.wd-wrap{white-space: pre-line;
    				overflow: hidden;
    				text-overflow: ellipsis;
    				display: inline-block;
    				font-size: 11px !important;
    				line-height: normal;}
    	.w250px{width: 250px;}
    	@media(max-width: 2200px){
		.table-add{position: absolute;}
		.add-align{position: absolute;
   					 margin-top: -5.5em;
   					 margin-left: 11%;}
   		.bd-none{border: none !important;}
   		 }
    	@media(max-Width: 2000px){
    	.add-align{margin-left:18%;}
    	}
    	@media(max-width: 820px){
    	.add-align{position: relative; margin-top: 0; margin-left:0;}
    	.table-add{position: relative;}
    	}
    	.filevalue {
            display: block;
            margin-top: 10px;
			font-size: .9rem;
        }
        td.center-align{
        	text-align:center !important;
        }
        
        @media only screen and (max-width: 820px){
			.mt-sm-n1rem{
				margin-top:-1rem !important;
			}
			.pt-5 {
			    padding-top: 0 !important;
			}
			.filevalue {
			    width: 200%;
			    white-space: break-spaces;
			}
		}
		
		 @media only screen and (max-width: 568px){
			.container{
				width:100%;
			}
		}
		@media(max-width: 575px){
			.row .col{margin: 10px auto;}
			.mn6tbpx{margin: -6px auto;}
			.area{padding: 10px;}
			.input-field>label{font-size: 11px;line-height: 1;}
			#private_div .radio-lbl{margin-left:0 !important;}
		}
		 #compensation_unitsError{
	   		float:right;	
	    }
	   .character-counter {
		  background-color: smoke;
		  position: absolute;
		  top: 25%;
		  right: 1.5em;
		}
		.pdr3em{
			padding-right: 3em !important;
		} 
		.pdr4em{
			padding-right: 4em !important;
		}
		.pdr5em{
			padding-right: 5em !important;
		}
		.w85{
			width: 85% !important;
		}
		.w80{
			width: 79% !important;
		}
		.w75{
			width: 75% !important;
		}
		.w70{
			width: 70% !important;
		}
         .datepicker-min-today ~button{
			    position: absolute;
			    right: 15px;
			    top: 15px;
			    border: 0;
			    opacity: 0.7;
			    cursor: pointer;
			    background-color: transparent;
		}	
		
		.modal-content label,
		.modal-content [type="checkbox"]+span:not(.lever) {
		    font-size: 1.25rem; 
		    color: #9e9e9e;
		}
		.modal-content [type="radio"]:not(:checked)+span, [type="radio"]:checked+span{
			padding-left:25px;
		}
		
				.modal-content label, .modal-content [type="checkbox"]+span:not(.lever) {
				    font-size: 1rem;
				    color: #9e9e9e;
		}				
    </style>
</head>

<body>


    <!-- header  starts-->
     <jsp:include page="../layout/header.jsp"></jsp:include>
    <!-- header ends  -->

    <!-- card  -->
    <div class="row">
        <div class="col s12 m12">
            <div class="card ">
                <div class="card-content">
                    <div class="center-align">
                        <span class="card-title headbg">
                            <div class="center-align p-2 bg-m m-b-2">
                                <h6>
                                	 <c:if test="${action eq 'edit'}">Update Land Acquisition (${LADetails.la_id })</c:if>
									 <c:if test="${action eq 'add'}"> Add Land Acquisition</c:if>
                                </h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <c:if test="${action eq 'edit'}">		
                        	<c:if test="${sessionScope.USER_ID eq LADetails.executive_user_id_fk or sessionScope.USER_ROLE_NAME eq 'IT Admin'}">
				                 <form action="<%=request.getContextPath() %>/update-land-acquisition" id="landAcquisitionForm" name="landAcquisitionForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                            </c:if>		                
	                 </c:if>
				     <c:if test="${action eq 'add'}">				                
				                	<form action="<%=request.getContextPath() %>/add-land-acquisition" id="landAcquisitionForm" name="landAcquisitionForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
				     </c:if>
				     <c:if test="${action eq 'add'}"> 
                        <div class="row">
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <p class="searchable_label"> Project <span class="required">*</span></p>
                                    <select id="project_id_fk" name="project_id_fk"  class="searchable validate-dropdown"  onchange="getWorksList(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${projectsList }">
                                      	   <option value= "${ obj.project_id}">${obj.project_id}<c:if test="${not empty obj.project_name}"> - </c:if> ${obj.project_name }</option>
                                         </c:forEach>
                                    </select>
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                 <input type="hidden" id= "work_code" name= "work_code"/>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="work_id_fk" name="work_id_fk" onchange="resetProjectsDropdowns(this.value);">
                                        <option value="">Select</option>
                                         <c:forEach var="obj" items="${worksList }">
	                                      	   <option name="${ obj.work_code}" value= "${obj.work_id}">${obj.work_id}<c:if test="${not empty obj.work_short_name}"> - </c:if> ${obj.work_short_name }</option>
	                                     </c:forEach>
                                    </select>
                                    <span id="work_id_fkError" class="error-msg" ></span>
                                </div>
                                  <div class="col s12 m8 l4 input-field offset-m2">
                                	 <p class="searchable_label"> Land Status  <span class="required">*</span></p>
                                 	<select id="la_land_status_fk" class="searchable validate-dropdown" name="la_land_status_fk" onchange="checkLStatus(this);">
                                     	<option value="" >Select</option>
                                     	<c:forEach var="obj" items="${laLandStatus }">
                                    	   	<option value= "${obj.la_land_status}">${obj.la_land_status}</option>
                                  	 </c:forEach>
                                 	</select>
                                		<span id="la_land_status_fkError" class="error-msg" ></span>  
                               </div>
                            </div>
							</c:if>
 							<c:if test="${action eq 'edit'}">	 
                              <div class="row" >
	                       		  <div class="col s6 m4 l4 input-field offset-m2">
										<p class="searchable_label"> Project <span class="required">*</span></p>
	                                    <input type="text" value="${LADetails.project_id_fk} - ${LADetails.project_name}" readonly />
								  </div> 
								  <div class="col s6 m4 l4 input-field"> 
									    <p class="searchable_label"> Work <span class="required">*</span></p>
                                    	<input type="text"  value="${LADetails.work_id_fk} - ${LADetails.work_short_name}" readonly />
                                    	<input type="hidden" name="work_id_fk" id="work_id_fk" value="${LADetails.work_id_fk}"  />
	                              </div>
	                              
	                              <div class="col s12 m8 l4 input-field offset-m2">
	                                 <p class="searchable_label"> Land Status <span class="required">*</span></p>
                                    	<select id="la_land_status_fk" class="searchable validate-dropdown" name="la_land_status_fk" onchange="checkLStatus(this);">
                                        	<option value="" >Select</option>
                                        	<c:forEach var="obj" items="${laLandStatus }">
	                                      	   	<option value= "${obj.la_land_status}" <c:if test="${LADetails.la_land_status_fk eq obj.la_land_status}">selected</c:if>>${obj.la_land_status}</option>
	                                    	 </c:forEach>
                                    	</select>
                                   		<span id="la_land_status_fkError" class="error-msg" ></span> 
	                                </div> 
	                                <input type="hidden" id="la_id" name="la_id" value="${LADetails.la_id }">
                              </div> 
                             </c:if>
                             <br>
                            <div class="row">                                 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="survey_number" maxlength="25" data-length="25" name="survey_number" type="text" class="validate mt-10 w80 pdr4em" value="${LADetails.survey_number }">
                                    <label for="survey_number">Survey Number<!-- <span class="required">*</span> --></label>
									<br><span id="survey_numberError" class="error-msg" ></span>                                    
                                </div>
<%--                                 <div class="col s6 m4 l4 input-field ">
                                    <input id="village_id" maxlength="50" data-length="50" name="village_id" type="text" class="validate mt-10 w80 pdr4em" value="${LADetails.village_id }">
                                    <label for="village_id">Village ID </label>
                                    <span id="village_idError" class="error-msg" ></span>
                                </div>  --%>
                                <div class="col s12 m8 l4 input-field offset-m2">
                                       <input id="special_feature" maxlength="50" data-length="50" name="special_feature" type="text" value="${LADetails.special_feature }"
                                           class="validate mt-10 w80 pdr4em">
                                       <label for="special_feature">Special Feature</label>
                                </div>                                 
                            </div>
							<c:if test="${action eq 'add'}">
                            <div class="row">                                 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <p class="searchable_label"> Type of Land <span class="required">*</span></p>
                                    <select id="type_of_land" class="searchable validate-dropdown" name="category_fk" onchange="getSubCategorysList();">
                                        <option value="" >Select</option>
                                        <c:forEach var="obj" items="${landsList }">
	                                      	   <option value= "${obj.type_of_land}" <c:if test="${LADetails.type_of_land eq obj.type_of_land}">selected</c:if>>${obj.type_of_land}</option>
	                                     </c:forEach>
                                    </select>
                                    <span id="type_of_landError" class="error-msg" ></span>                                    
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <p class="searchable_label fs-sm-67rem"> Sub Category of Land <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="sub_category_of_land" name="id" onchange="getLandsList();">
                                        <option value="" selected>Select</option>
                                       <c:forEach var="obj" items="${subCategorysList }">
	                                      	   <option value= "${obj.id}" <c:if test="${LADetails.sub_category_of_land eq obj.sub_category_of_land}">selected</c:if>>${obj.sub_category_of_land}</option>
	                                     </c:forEach>
                                    </select>
                                    <span id="sub_category_of_landError" class="error-msg" ></span>
                                </div>  
                                <div class="col s12 m8 l4 input-field  offset-m2">
                                       <input id="area_acquired" maxlength="10" data-length="10" name="area_acquired" type="number" step="any" onkeyup="doValidateAquired(this.value)" 
                                           class="validate mt-10 w80 pdr4em num">
                                       <label for="area_acquired"> Acquired Area (Ha)<span class="required" id="acr">*</span></label>
                                       <span id="area_acquiredError" class="error-msg" ></span>  
                                </div>                                
                            </div>
							</c:if>
							<c:if test="${action eq 'edit'}">
								<div class="row">	                                 
	                                <div class="col s6 m4 l4 input-field offset-m2">
	                                    <input type="text" id="type_of_land" name="category_fk"  value="${LADetails.type_of_land }" readonly />
	                                    <label for="type_of_land"> Type of Land <span class="required">*</span></label>	                                    
	                                </div>
	                                <div class="col s6 m4 l4 input-field">
	                                <p class="searchable_label fs-sm-67rem"> Sub Category of Land <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="sub_category_of_land" name="id" onchange="getLandsList();">
                                        <option value="" selected>Select</option>
                                    </select>	                                
	                                </div>
	                                <div class="col s12 m4 l4 offset-m2 input-field">
                                       <input id="area_acquired" maxlength="10" data-length="10" name="area_acquired" type="number" step="any" onkeyup="doValidateAquired(this.value)" value="${LADetails.area_acquired }"
                                           class="validate mt-10 pdr4em w80 num">
                                       <label for="area_acquired"> Acquired Area (Ha)<span class="required" id="acr">*</span></label>
                                       <span id="area_acquiredError" class="error-msg" ></span> 
                                	</div> 	                                 
	                            </div>
							
							</c:if>
						
                            <div class="row">                                 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="village" maxlength="100" data-length="100" name="village" type="text" class="validate w80 pdr4em" value="${LADetails.village }">
                                    <label for="village"> Village <span class="required">*</span></label>
                                    <span id="villageError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input id="taluka" maxlength="100" data-length="100" name="taluka" type="text" class="validate w80 pdr4em" value="${LADetails.taluka }">
                                    <label for="taluka"> Taluka <span class="required">*</span></label>
                                    <span id="talukaError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="dy_slr" maxlength="100" data-length="100" name="dy_slr" type="text" class="validate w80 pdr4em" value="${LADetails.dy_slr }">
                                    <label for="dy_slr">Dy SLR </label>
                                    <span id="dy_slrError" class="error-msg" ></span>
                                </div>                                
                            
                            <div class="col s6 m4 l4 input-field">
                                    <input id="sdo" maxlength="100" data-length="100" name="sdo" type="text" class="validate pdr4em w80" value="${LADetails.sdo }">
                                    <label for="sdo"> SDO</label>
                                    <span id="sdoError" class="error-msg" ></span>
                                </div>                                 
                                <div class="col s8 m8 l4 input-field offset-m2">
                                    <input id="collector" maxlength="100" data-length="100" name="collector" type="text" class="validate w80 pdr4em" value="${LADetails.collector }">
                                    <label for="collector">Collector </label>
                                    <span id="collectorError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="area_to_be_acquired" maxlength="10" data-length="10" name="area_to_be_acquired" type="number" step="any" onkeyup="doValidate(this.value)"  class="validate num w80 pdr4em" value="${LADetails.area_to_be_acquired }">
                                    <label for="area_to_be_acquired">Total Area to be Acquired (Ha)<span class="required" id="atacq">*</span></label><br>
                                    <span id="area_to_be_acquiredError" class="error-msg" ></span>
                                </div>
                                
                                                                                    
                            </div>
	  						<div class="row">                                 
                                
                                 <div class="col s6 m4 l4 input-field ">
                                    <input id="area_of_plot" maxlength="10" data-length="10" name="area_of_plot" type="number" class="validate num w80 pdr4em" value="${LADetails.area_of_plot }">
                                    <label for="area_of_plot">Area of Plot (Ha)</label>
                                </div> 
                                <div class="col s6 m8 l4 input-field offset-m2">
                                    <input id="latitude" maxlength="40" data-length="40" name="latitude" type="number" class="validate w80 pdr4em num" value="${LADetails.latitude }">
                                    <label for="latitude" id="idLatitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div> 
                                <div class="col s6 m8 l4 input-field offset-m2">
                                    <input id="longitude" maxlength="40" data-length="40" name="longitude" type="number" class="validate w80 pdr4em num" value="${LADetails.longitude }">
                                    <label for="longitude" id="idLongitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>                               
                            </div>
                            <div class="row">      
                               <div class="col s12 m4 l4 input-field offset-m2">
                                    <input id="submission_date" name="proposal_submission_date_to_collector" type="text" value="${LADetails.proposal_submission_date_to_collector }"
                                        class="validate datepicker">
                                    <label for="submission_date">Proposal submission Date to collector</label>
                                    <button type="button" id="submission_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                    <span id="submission_dateError" class="error-msg" ></span>
                                </div>        
                                <div class="col s12 m4 l4 input-field amount-dropdown">
                                    <i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="jm_fee_amount" name="jm_fee_amount" type="number" class="validate" value="${LADetails.jm_fee_amount }" min="0" step="0.00001">
                                    <label for="jm_fee_amount"> JM Fee Amount </label>
                                    <span id="jm_fee_amountError" class="error-msg"></span>
                                	<span id="jm_fee_amount_unitsError" class="error-msg right" ></span>
                                    <select class="validate-dropdown" id="jm_fee_amount_units" name="jm_fee_amount_units">
                                		<!-- <option value="">Select</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                </div>   
                            <%--     <div class="col s4 m1 l1 input-field pt-5">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="jm_fee_amount_units" name="jm_fee_amount_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }"<c:if test="${LADetails.jm_fee_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="jm_fee_amount_unitsError" class="error-msg" ></span>
                               	</div> --%>
                               	<!-- <div class="col m2 hide-on-small-only"></div> -->
                               	<div class="col s6 m8 l4 input-field offset-m2">
                                    <input id="chainage_from" maxlength="10" data-length="10" name="chainage_from" type="number" class="validate num w80 pdr4em" value="${LADetails.chainage_from }" onKeyup="getCoordinates();">
                                    <label for="chainage_from">Chainage From <span class="required">*</span></label>
                                    <span id="chainage_fromError" class="error-msg" ></span>
                                </div>                               
                             
                            	<div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="chainage_to" maxlength="10" data-length="10" name="chainage_to" type="number" class="validate num w80 pdr4em" value="${LADetails.chainage_to }" onKeyup="getCoordinates();" onblur="checkValues();">
                                    <label for="chainage_to"> Chainage To<span class="required">*</span> </label>
                                    <span id="chainage_toError" class="error-msg" ></span>
                                </div>                                
                                <div class="col s12 m4 l4 input-field ">
                                    <input id="jm_fee_letter_received_date" name="jm_fee_letter_received_date" type="text" value="${LADetails.jm_fee_letter_received_date }"
                                        class="validate datepicker">
                                    <label for="jm_fee_letter_received_date"> JM Fee Letter received Date </label>
                                    <button type="button" id="jm_fee_letter_received_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="jm_fee_paid_date" name="jm_fee_paid_date" type="text" value="${LADetails.jm_fee_paid_date }"
                                        class="validate datepicker">
                                    <label for="jm_fee_paid_date">JM Fee Paid Date </label>
                                    <button type="button" id="jm_fee_paid_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>                                 
                                                    
                                <div class="col s6 m4 l4 input-field ">
                                    <input id="jm_start_date" type="text" name="jm_start_date" class="validate datepicker" value="${LADetails.jm_start_date }">
                                    <label for="jm_start_date">JM Start Date </label>
                                    <button type="button" id="jm_start_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>
                                <div class="col s6 m4 l4 input-field offset-m2">
                                    <input id="jm_completion_date" name="jm_completion_date" type="text" class="validate datepicker" value="${LADetails.jm_completion_date }">
                                    <label for="jm_completion_date" class="fs-sm-8rem"> JM Completion Date</label>
                                    <button type="button" id="jm_completion_date_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>  
                                 <div class="col s6 m4 l4 input-field ">
                                    <input id="jm_sheet_date_to_sdo" name="jm_sheet_date_to_sdo" type="text" class="validate datepicker" value="${LADetails.jm_sheet_date_to_sdo }">
                                    <label for="jm_sheet_date_to_sdo" class="fs-sm-8rem">JM Sheet Date to SDO </label>
                                    <button type="button" id="jm_sheet_date_to_sdo_icon" class="datepicker-button"><i
                                            class="fa fa-calendar"></i></button>
                                </div>                               
                            </div>
                            
                            <div class="row">             
                                <div class="col s12 m8 l12 center-align offset-m2">
                                    <div class="row">
                                        <div class="col s4 m4 l6 input-field ">
                                            <p>JM Approval</p>
                                        </div>
                                        <div class="col s8 m8 l6 input-field">
                                            <p class="">
                                                <label>
                                                    <input class="with-gap" name="jm_approval" type="radio"
                                                        value="Done" <c:if test="${LADetails.jm_approval eq 'Done'}">checked</c:if> />
                                                    <span>Accept</span>
                                                </label> &nbsp; <label>
                                                    <input class="with-gap" name="jm_approval" type="radio"
                                                        value="Rejected" <c:if test="${LADetails.jm_approval eq 'Rejected'}">checked</c:if>/>
                                                    <span>Reject</span>
                                                </label>
                                            </p>
                                        </div>
                                    </div>
                                </div>                                 
                            </div>

                            <div class="row">                                 
                                <div class="col s12 m8 l12 input-field offset-m2">
                                    <textarea id="jm_remarks" name="jm_remarks" class="pmis-textarea" maxlength="500"
                                        data-length="500">${LADetails.jm_remarks }</textarea>
                                    <label for="jm_remarks"> JM Remarks</label>
                                </div>                                 
                            </div>

                            <!-- if selected govt this div shown  -->
                            <div id="govt_div" style="display: none; ">
                                <h6 class="center-align primary-text-bold">Government Land Details </h6>                               
                                <div class="row">                                     
                                  <%--   <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="govt_area_to_be_acquired" name="area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.area_to_be_acquired }"
                                            class="validate">
                                        <label for="govt_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="govt_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>      
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="govt_area_acquired" name="area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.area_acquired }"
                                            class="validate">
                                        <label for="govt_area_acquired"> Area Acquired (Ha) </label>
                                        <span class="units">units</span>
                                        <span id="govt_area_acquiredError" class="error-msg"></span>
                                    </div> --%>
<%--                                     <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label"> Proposal Submission Status </p>
                                        <select class="searchable" id="proposal_submission_status_fk"
                                            name="proposal_submission_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.proposal_submission_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div> --%>   
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="proposal_submission" type="text" value="${LADetails.proposal_submission }" 
                                            name="proposal_submission" class="validate datepicker">
                                        <label for="proposal_submission">Proposal submission</label>
                                        <button type="button" id="proposal_submission_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                    <div class="col s12 m8 l4 input-field offset-m2 ">
                                        <input id="letter_for_payment" type="text" class="validate datepicker" value="${LADetails.letter_for_payment }"
                                            name="letter_for_payment">
                                        <label for="letter_for_payment">Letter for Payment</label>
                                        <button type="button" id="letter_for_payment_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                
                                <div class="row">
                                
                                    <div class="col s12 m4 l4 input-field amount-dropdown offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="amount_demanded" name="amount_demanded" type="number" value="${LADetails.amount_demanded }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="amount_demanded" class="fs-sm-67rem">Amount Demanded</label>
                                        <span id="amount_demandedError" class="error-msg"></span>
	                                	<span id="amount_demanded_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="amount_demanded_units" name="amount_demanded_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }"<c:if test="${LADetails.amount_demanded_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
<%--                                       <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label"> LFP Status </p>
                                        <select class="searchable" id="lfp_status_fk" name="lfp_status_fk">
                                            <option value="" selected>Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.lfp_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div> --%>
                                    <div class="col s6 m8 l4 input-field offset-m2">
                                        <input id="approval_for_payment" type="text" value="${LADetails.approval_for_payment }"
                                            name="approval_for_payment" class="validate datepicker mt-10">
                                        <label for="approval_for_payment" class="fs-sm-8rem">Approval for Payment </label>
                                        <button type="button" id="approval_for_payment_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="amount_demanded_units" name="amount_demanded_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }"<c:if test="${LADetails.amount_demanded_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="amount_demanded_unitsError" class="error-msg" ></span>
                               		</div> --%>                                      
                                </div>
                                <div class="row">                                     
                                  
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="valuation_date" type="text" value="${LADetails.valuation_date }"
                                            name="valuation_date" class="validate datepicker mt-10">
                                        <label for="valuation_date">Valuation Date</label>
                                        <button type="button" id="valuation_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div> 
                                    <div class="col s12 m4 l4 input-field amount-dropdown">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="govt_amount_paid" name="amount_paid" type="number" value="${LADetails.amount_paid }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="govt_amount_paid"> Amount Paid</label>
                                        <span id="govt_amount_paidError" class="error-msg"></span>
	                                	<span id="amount_paid_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="amount_paid_units" name="amount_paid_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.amount_paid_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div> 
<%--                                     <div class="col s12 m8 l4 input-field offset-m2">
                                        <p class="searchable_label"> Payment Status </p>
                                        <select class="searchable" id="payment_status_fk" name="payment_status_fk">
                                            <option value="" selected>Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.payment_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>   --%>                                  
                                </div>
                                
                                <div class="row">
<%--                                     <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="planned_date_of_possession" name="planned_date_of_possession" type="text" value="${LADetails.planned_date_of_possession }"
                                            class="validate datepicker">
                                        <label for="planned_date_of_possession">Planned Date of Possession </label>
                                        <button type="button" id="planned_date_of_possession_icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>  --%>  
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="possession_date" name="possession_date" type="text" value="${LADetails.possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="possession_date">Possession Date </label>
                                        <button type="button" id="possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s6 m8 l4 input-field offset-m2 ">
                                        <input id="payment_date" name="payment_date" type="text" value="${LADetails.payment_date }"
                                            class="validate datepicker">
                                        <label for="payment_date">Payment date </label>
                                        <button type="button" id="payment_date_icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>                                            
                                    </div>
                                </div>
                            </div>

        <!-- if selected forest this div shown  -->
                            
                            <div id="forest_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Forest Land Details </h6>
                               <div class="row">                                     
                                   <%--  <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_area_to_be_acquired" name="forest_area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.forest_area_to_be_acquired }"
                                            class="validate">
                                        <label for="forest_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="forest_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                     <div class="col s6 m4 l4 input-field">
                                        <input id="forest_area_acquired" name="forest_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.forest_area_acquired }"
                                            class="validate">
                                        <label for="forest_area_acquired"> Area Acquired (Ha) </label>
                                        <span class="units">units</span>
                                        <span id="forest_area_acquiredError" class="error-msg"></span>
                                    </div> --%>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_online_submission" name="forest_online_submission" type="text" value="${LADetails.forest_online_submission }"
                                            class="validate datepicker">
                                        <label for="forest_online_submission"> On line Submission </label>
                                        <button type="button" id="forest_online_submission_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                 
                                <div class="col s6 m4 l4 input-field">
                                        <input id="forest_submission_dycfo" name="forest_submission_date_to_dycfo" type="text" value="${LADetails.forest_submission_date_to_dycfo }"
                                            class="validate datepicker">
                                        <label for="forest_submission_dycfo" class="fs-sm-67rem">Submission Date to DyCFO </label>
                                        <button type="button" id="forest_submission_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="forest_submission_ccf" name="forest_submission_date_to_ccf_thane" type="text" value="${LADetails.forest_submission_date_to_ccf_thane }"
                                            class="validate datepicker">
                                        <label for="forest_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="forest_submission_ccf_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                       <div class="col s12 m4 l4 input-field">
                                        <input id="forest_submission_date_to_nodal_officer" name="forest_submission_date_to_nodal_officer" value="${LADetails.forest_submission_date_to_nodal_officer }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_submission_revenue_sec" class="fs-sm-8rem wd-wrap"> Submission Date to Nodal Officer/CCF Nagpur </label>
                                        <button type="button" id="forest_submission_date_to_nodal_officer_icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>                                    
                                     <div class="col s12 m4 l4 input-field">
                                        <input id="forest_submission_revenue_sec" name="forest_submission_date_to_revenue_secretary_mantralaya" value="${LADetails.forest_submission_date_to_revenue_secretary_mantralaya }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_submission_revenue_sec" class="fs-sm-8rem wd-wrap"> Submission Date to Revenue Secretary
                                            Mantralaya </label>
                                        <button type="button" id="forest_submission_revenue_sec_icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                  
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="forest_submission_regional_office"
                                            name="forest_submission_date_to_regional_office_nagpur" type="text" value="${LADetails.forest_submission_date_to_regional_office_nagpur }"
                                            class="validate datepicker">
                                        <label for="forest_submission_regional_office" class="fs-sm-8rem wd-wrap"> Submission Date to Regional
                                            Office Nagpur
                                        </label>
                                        <button type="button" id="forest_submission_regional_office_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>   
                                       <div class="col s12 m4 l4 input-field ">
                                            <input id="forest_approval_regional_office"
                                            name="forest_date_of_approval_by_regional_office_nagpur" type="text" value="${LADetails.forest_date_of_approval_by_regional_office_nagpur }"
                                            class="validate datepicker">
                                        <label for="forest_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="forest_approval_regional_office_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                         
                                </div> 
                          
                                <div class="row">                                     
                                    
                                                                        
                                </div>
                                <div class="row">
                                	<div class="col s12 m4 l4 offset-m2 input-field">
                                        <input id="forest_valuation_dycfo" name="forest_valuation_by_dycfo" type="text" value="${LADetails.forest_valuation_by_dycfo }"
                                            class="validate datepicker">
                                        <label for="forest_valuation_dycfo">Valuation by DyCFO </label>
                                        <button type="button" id="forest_valuation_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                      
                                    <div class="col s12 m4 l4 input-field amount-dropdown">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="forest_demanded_amount" name="forest_demanded_amount" type="number" value="${LADetails.forest_demanded_amount }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="forest_demanded_amount" class="fs-sm-8rem">Demanded Amount </label>
                                        <span id="forest_demanded_amountError" class="error-msg"></span>
	                                	<span id="forest_demanded_amount_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="demanded_amount_units_forest" name="demanded_amount_units_forest">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <%--  <div class="col s4 m1 l1 input-field pt-5">
	                               	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="demanded_amount_units_forest" name="demanded_amount_units_forest">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_demanded_amount_unitsError" class="error-msg" ></span>
                               		</div>    --%>
                                    <div class="col s12 m8 l4 input-field amount-dropdown offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="forest_payment_amount" name="forest_payment_amount" type="number" value="${LADetails.forest_payment_amount }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="forest_payment_amount" class="fs-sm-8rem"> Payment Amount </label>
                                        <span id="forest_payment_amountError" class="error-msg"></span>
	                                	<span id="forest_payment_amount_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="payment_amount_units_forest" name="payment_amount_units_forest">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>  
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units_forest" name="payment_amount_units_forest">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_forest eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_payment_amount_unitsError" class="error-msg" ></span>
                               		</div>   --%>                                    
                                </div>
                                <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_payment_approval_date" name="forest_approval_for_payment" value="${LADetails.forest_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="forest_payment_approval_date" class="fs-sm-8rem"> Approval for Payment</label>
                                        <button type="button" id="forest_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="forest_payment_date" name="forest_payment_date" type="text" value="${LADetails.forest_payment_date }"
                                            class="validate datepicker">
                                        <label for="forest_payment_date"> Payment Date </label>
                                        <button type="button" id="forest_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m8 l4 input-field offset-m2"> 
                                        <input id="forest_possession_date" name="forest_possession_date" type="text" value="${LADetails.forest_possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="forest_possession_date"> Possession Date</label>
                                        <button type="button" id="forest_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                       
                                <div class="row">  
<%--                                 	<div class="col s6 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="forest_possession_status_fk"
                                            name="forest_possession_status_fk">
                                            <option value="" selected>Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div> --%>                                   
<%--                                     <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label">Payment Status </p>
                                        <select class="searchable" id="forest_payment_status_fk"
                                            name="forest_payment_status_fk">
                                            <option value="" selected>Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_payment_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div> --%>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                     <%--    <input id="forest_special_feature" name="forest_special_feature" type="text" value="${LADetails.forest_special_feature }"
                                            class="validate mt-10">
                                        <label for="forest_special_feature"> Special Feature </label> --%>
                                    </div>
                                     
                                </div>
                          <%--       <div class="row">                                     
                                  	<div class="col s12 m4 l4 input-field">
                                        <input id="forest_attachment_no" name="forest_attachment_No" type="text" value="${LADetails.forest_attachment_No }"
                                            class="validate">
                                        <label for="forest_attachment_no">Attachment Number </label>
                                    </div>
                                     
                                 </div> --%>
                            </div>

        <!-- if selected private this div shown  -->
                            
                            <div id="private_div" style="display: none;">
                                <h6 class="center-align primary-text-bold">Private Land Details </h6> 
                                <div class="row">
                                <center><label style=" margin-bottom: 35px;" class="radio-lbl"><input type="radio" id="private_land_process_ira" name="private_land_process" class="with-gap" value="Private IRA" <c:if test="${LADetails.private_land_process eq 'Private IRA'}">checked</c:if>/><span>Private (Indian Railway Act)</span></label>
    							<label style="margin-left: 58px; margin-bottom: 35px;" class="radio-lbl"><input type="radio" id="private_land_process_dpm" name="private_land_process" class="with-gap" value="Private DPM" <c:if test="${LADetails.private_land_process eq 'Private DPM'}">checked</c:if>/><span>Private - IRA & DPM</span></label></center>
    							
    							<div class='area' id="private_ira"  style="display:none;">
    								<div class="row">
    									<div class="col s12 m6 l6 input-field offset-m2 offset-l3">
	                                        <input id="private_collector" name="private_ira_collector" type="text" value="${LADetails.private_ira_collector }"
	                                            class="validate">
	                                        <label for="private_collector">Collector</label>
                                    	</div>
                                    	<br>
                                    	<br>
                                    	<h6 class="center-align primary-text">Declaration of Special Railway project </h6>
    									<div class="col s6 m4 l4 input-field">
	                                        <input id="private_submission_proposal_gm" name="submission_of_proposal_to_GM" type="text" value="${LADetails.submission_of_proposal_to_GM }"
	                                            class="validate datepicker">
	                                        <label for="private_submission_proposal_gm">Submission of Proposal to GM.(date) </label>
	                                        <button type="button" id="private_submission_proposal_gm_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_approval_gm" name="approval_of_GM" type="text" value="${LADetails.approval_of_GM }"
	                                            class="validate datepicker">
	                                        <label for="private_approval_gm">Approval of GM </label>
	                                        <button type="button" id="private_approval_gm_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_draft_letter_ce_con_approval_dec" name="draft_letter_to_con_for_approval_rp" type="text" value="${LADetails.draft_letter_to_con_for_approval_rp }"
	                                            class="validate datepicker">
	                                        <label for="private_draft_letter_ce_con_approval_dec">Draft Letter to CE/Con for Approval </label>
	                                        <button type="button" id="private_draft_letter_ce_con_approval_dec_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_approval_ce_construction_dec" name="date_of_approval_of_construction_rp" type="text" value="${LADetails.date_of_approval_of_construction_rp }"
	                                            class="validate datepicker">
	                                        <label for="private_date_approval_ce_construction_dec">Date of Approval of CE/Construction </label>
	                                        <button type="button" id="private_date_approval_ce_construction_dec_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_uploading_gazette_notification_dec" name="date_of_uploading_of_gazette_notification_rp" type="text" value="${LADetails.date_of_uploading_of_gazette_notification_rp }"
	                                            class="validate datepicker">
	                                        <label for="private_date_uploading_gazette_notification_dec">Date of Uploading of  Gazette notification </label>
	                                        <button type="button" id="private_date_uploading_gazette_notification_dec_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_publication_gazette_dec" name="publication_in_gazette_rp" type="text" value="${LADetails.publication_in_gazette_rp }"
	                                            class="validate datepicker">
	                                        <label for="private_publication_gazette_dec">Publication in gazette </label>
	                                        <button type="button" id="private_publication_gazette_dec_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<br>
                                    	<br>
                                    	<h6 class="center-align primary-text">Nomination of competent Authority </h6>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_proposal_dc_nomination_nom" name="date_of_proposal_to_DC_for_nomination" type="text" value="${LADetails.date_of_proposal_to_DC_for_nomination }"
	                                            class="validate datepicker">
	                                        <label for="private_date_proposal_dc_nomination_nom">Date of Proposal to DC for nomination </label>
	                                        <button type="button" id="private_date_proposal_dc_nomination_nom_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_nomination_competent_authority_nom" name="date_of_nomination_of_competenta_authority" type="text" value="${LADetails.date_of_nomination_of_competenta_authority }"
	                                            class="validate datepicker">
	                                        <label for="private_date_nomination_competent_authority_nom">Date of Nomination of competent Authority </label>
	                                        <button type="button" id="private_date_nomination_competent_authority_nom_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_draft_letter_ce_con_approval_nom" name="draft_letter_to_con_for_approval_ca" type="text" value="${LADetails.draft_letter_to_con_for_approval_ca }"
	                                            class="validate datepicker">
	                                        <label for="private_draft_letter_ce_con_approval_nom">Draft Letter to CE/Con for Approval </label>
	                                        <button type="button" id="private_draft_letter_ce_con_approval_nom_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_approval_ce_construction_nom" name="date_of_approval_of_construction_ca" type="text" value="${LADetails.date_of_approval_of_construction_ca }"
	                                            class="validate datepicker">
	                                        <label for="private_date_approval_ce_construction_nom">Date of Approval of CE/Construction </label>
	                                        <button type="button" id="private_date_approval_ce_construction_nom_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_uploading_gazette_notification_nom" name="date_of_uploading_of_gazette_notification_ca" type="text" value="${LADetails.date_of_uploading_of_gazette_notification_ca }"
	                                            class="validate datepicker">
	                                        <label for="private_date_uploading_gazette_notification_nom">Date of Uploading of  Gazette notification </label>
	                                        <button type="button" id="private_date_uploading_gazette_notification_nom_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_publication_gazette_nom" name="publication_in_gazette_ca" type="text" value="${LADetails.publication_in_gazette_ca }"
	                                            class="validate datepicker">
	                                        <label for="private_publication_gazette_nom">Publication in gazette </label>
	                                        <button type="button" id="private_publication_gazette_nom_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<br>
                                    	<br>
                                    	<div class="row">
                                    		<h6 class="center-align primary-text no-mar">Publication of notification under 20 A </h6>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_date_submission_draft_notification_cala_20a" name="date_of_submission_of_draft_notification_to_CALA" type="text" value="${LADetails.date_of_submission_of_draft_notification_to_CALA }"
		                                            class="validate datepicker">
		                                        <label for="private_date_submission_draft_notification_cala_20a">Date of Submission of draft notification to CALA </label>
		                                        <button type="button" id="private_date_submission_draft_notification_cala_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_approval_cala_20a" name="approval_of_CALA_20a" type="text" value="${LADetails.approval_of_CALA_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_approval_cala_20a">Approval of CALA </label>
		                                        <button type="button" id="private_approval_cala_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_draft_letter_ce_con_approval_20a" name="draft_letter_to_con_for_approval_20a" type="text" value="${LADetails.draft_letter_to_con_for_approval_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_draft_letter_ce_con_approval_20a">Draft Letter to CE/Con for Approval </label>
		                                        <button type="button" id="private_draft_letter_ce_con_approval_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_date_approval_ce_construction_20a" name="date_of_approval_of_construction_20a" type="text" value="${LADetails.date_of_approval_of_construction_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_date_approval_ce_construction_20a">Date of Approval of CE/Construction </label>
		                                        <button type="button" id="private_date_approval_ce_construction_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_date_uploading_gazette_notification_20a" name="date_of_uploading_of_gazette_notification_20a" type="text" value="${LADetails.date_of_uploading_of_gazette_notification_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_date_uploading_gazette_notification_20a">Date of Uploading of Gazette notification </label>
		                                        <button type="button" id="private_date_uploading_gazette_notification_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_publication_gazette_20a" name="publication_in_gazette_20a" type="text" value="${LADetails.publication_in_gazette_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_publication_gazette_20a">Publication in gazette </label>
		                                        <button type="button" id="private_publication_gazette_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_publication_2_local_newspapers_20a" name="publication_in_2_local_news_papers_20a" type="text" value="${LADetails.publication_in_2_local_news_papers_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_publication_2_local_newspapers_20a">Publication in 2 Local Newspapers </label>
		                                        <button type="button" id="private_publication_2_local_newspapers_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m4 l4 input-field">
		                                        <input id="private_pasting_notification_villages_20a" name="pasting_of_notification_in_villages_20a" type="text" value="${LADetails.pasting_of_notification_in_villages_20a }"
		                                            class="validate datepicker">
		                                        <label for="private_pasting_notification_villages_20a">Pasting of notification in villages </label>
		                                        <button type="button" id="private_pasting_notification_villages_20a_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
                                    	</div>
                                    	<div class="row">
                                    		<h6 class="center-align primary-text m0">Grievances Redressal </h6>
	                                    	<div class="col s6 m6 l6 input-field">
		                                        <input id="private_receipt_grievances" name="receipt_of_grievances" type="text" value="${LADetails.receipt_of_grievances }"
		                                            class="validate datepicker">
		                                        <label for="private_receipt_grievances">Receipt of Grievances </label>
		                                        <button type="button" id="private_receipt_grievances_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    	<div class="col s6 m6 l6 input-field">
		                                        <input id="private_disposal_grievances" name="disposal_of_grievances" type="text" value="${LADetails.disposal_of_grievances }"
		                                            class="validate datepicker">
		                                        <label for="private_disposal_grievances">Disposal of Grievances </label>
		                                        <button type="button" id="private_disposal_grievances_icon"
		                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
	                                    	</div>
	                                    </div>
	                                    <div class="row" style="text-align:center;">
	                                    	<div class="col s12 m12 input-field">
		                                        <button type="button" id="btnIssues" name="btnIssues" class="btn btn-primary" onClick="openAddIssue();">Raise an Issue</button>
	                                    	</div>	                                    	
                                    	</div>
                                    	<div class="row">
                                    	<h6 class="center-align primary-text m0">Acquisition notice under 20E </h6>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_submission_draft_notification_cala_20e" name="date_of_submission_of_draft_notification_to_CALA_20e" type="text" value="${LADetails.date_of_submission_of_draft_notification_to_CALA_20e }"
	                                            class="validate datepicker">
	                                        <label for="private_date_submission_draft_notification_cala_20e">Date of Submission of draft notification to CALA </label>
	                                        <button type="button" id="private_date_submission_draft_notification_cala_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_approval_cala_20e" name="approval_of_CALA_20e" type="text" value="${LADetails.approval_of_CALA_20e }"
	                                            class="validate datepicker">
	                                        <label for="private_approval_cala_20e">Approval of CALA </label>
	                                        <button type="button" id="private_approval_cala_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_owner_consent_20e" name="draft_letter_to_con_for_approval_20e" type="text" value="${LADetails.draft_letter_to_con_for_approval_20e }"
	                                            class="validate datepicker">
	                                        <label for="private_owner_consent_20e">Draft Letter to CE/Con for Approval </label>
	                                        <button type="button" id="private_owner_consent_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_approval_ce_construction_20e" name="date_of_approval_of_construction_20e" type="text" value="${LADetails.date_of_approval_of_construction_20e }"
	                                            class="validate datepicker">
	                                        <label for="private_date_approval_ce_construction_20e">Date of Approval of CE/Construction </label>
	                                        <button type="button" id="private_date_approval_ce_construction_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_uploading_gazette_notification_20e" name="date_of_uploading_of_gazette_notification_20e" type="text" value="${LADetails.date_of_uploading_of_gazette_notification_20e }"
	                                            class="validate datepicker">
	                                        <label for="private__date_uploading_gazette_notification_20e">Date of Uploading of  Gazette notification </label>
	                                        <button type="button" id="private__date_uploading_gazette_notification_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_publication_in_gazette_20e" name="publication_in_gazette_20e" type="text" value="${LADetails.publication_in_gazette_20e }"
	                                            class="validate datepicker">
	                                        <label for="private_publication_in_gazette_20e">Publication in gazette </label>
	                                        <button type="button" id="private_publication_in_gazette_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_publication_notice_2_local_news_papers_20e" name="publication_of_notice_in_2_local_news_papers_20e" type="text" value="${LADetails.publication_of_notice_in_2_local_news_papers_20e }"
	                                            class="validate datepicker">
	                                        <label for="private_publication_notice_2_local_news_papers_20e">Publication of notice in 2 Local News papers  </label>
	                                        <button type="button" id="private_publication_notice_2_local_news_papers_20e_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	</div>
                                    	<div class="row">
                                    		<h6 class="center-align primary-text m0">Acquisition notice under 20F </h6>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_submission_draft_notification_cala_20f" name="date_of_submission_of_draft_notification_to_CALA_20f" type="text" value="${LADetails.date_of_submission_of_draft_notification_to_CALA_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_date_submission_draft_notification_cala_20f">Date of Submission of draft notification to CALA </label>
	                                        <button type="button" id="private_date_submission_draft_notification_cala_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_approval_cala_20f" name="approval_of_CALA_20f" type="text" value="${LADetails.approval_of_CALA_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_approval_cala_20f">Approval of CALA </label>
	                                        <button type="button" id="private_approval_cala_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_draft_letter_ce_con_approval_20f" name="draft_letter_to_con_for_approval_20f" type="text" value="${LADetails.draft_letter_to_con_for_approval_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_draft_letter_ce_con_approval_20f">Draft Letter to CE/Con for Approval </label>
	                                        <button type="button" id="private_draft_letter_ce_con_approval_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_approval_ce_construction_20f" name="date_of_approval_of_construction_20f" type="text" value="${LADetails.date_of_approval_of_construction_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_date_approval_ce_construction_20f">Date of Approval of CE/Construction </label>
	                                        <button type="button" id="private_date_approval_ce_construction_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_date_uploading_gazette_notification_20f" name="date_of_uploading_of_gazette_notification_20f" type="text" value="${LADetails.date_of_uploading_of_gazette_notification_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_date_uploading_gazette_notification_20f">Date of Uploading of  Gazette notification </label>
	                                        <button type="button" id="private_date_uploading_gazette_notification_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_publication_gazette_20f" name="publication_in_gazette_20f" type="text" value="${LADetails.publication_in_gazette_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_publication_gazette_20f">Publication in gazette </label>
	                                        <button type="button" id="private_publication_gazette_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	<div class="col s6 m4 l4 input-field">
	                                        <input id="private_publication_notice_2_local_news_papers_20f" name="publication_of_notice_in_2_local_news_papers_20f" type="text" value="${LADetails.publication_of_notice_in_2_local_news_papers_20f }"
	                                            class="validate datepicker">
	                                        <label for="private_publication_notice_2_local_news_papers_20f">Publication of notice in 2 Local News papers  </label>
	                                        <button type="button" id="private_publication_notice_2_local_news_papers_20f_icon"
	                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    	</div>
                                    	</div>
                                    	
    								</div>
    							</div>
								<div class='area' id="private_dpm" style="display:none;">
									<div class="row">
                                  <%--                                
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_area_to_be_acquired" name="private_area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.private_area_to_be_acquired }"
                                            class="validate">
                                        <label for="private_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="private_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                     <div class="col s12 m4 l4 input-field">
                                        <input id="private_area_acquired" name="private_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.private_area_acquired }"
                                            class="validate">
                                        <label for="private_area_acquired"> Area Acquired (Ha) </label>
                                        <span class="units">units</span>
                                        <span id="private_area_acquiredError" class="error-msg"></span>
                                    </div> --%>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_name_of_owner" name="name_of_the_owner" type="text" value="${LADetails.name_of_the_owner }"
                                            class="validate">
                                        <label for="private_name_of_owner">Name of Owner</label>
                                    </div>                                   
                                     <div class="col s12 m4 l4 amount-dropdown input-field">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_basic_rate" name="basic_rate" type="number" min="0" step="0.00001" value="${LADetails.basic_rate }"
                                            class="validate">
                                        <label for="private_basic_rate">Basic Rate </label>
                                        <span id="private_basic_rateError" class="error-msg"></span>     
	                                	<span id="basic_rate_unitsError" class="error-msg right" ></span>                                 
                                        <select class="validate-dropdown" id="basic_rate_units" name="basic_rate_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.basic_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                         
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="private_agri_trees" name="agriculture_tree_nos" type="text" value="${LADetails.agriculture_tree_nos }"
                                            class="validate">
                                        <label for="private_agri_trees"> Agriculture tree nos</label>
                                       
                                    </div> 
                                </div>
                                <div class="row">                                         
                                 	                                     
                                </div>
                                <div class="row">                                     
                                
                                     <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_agri_tree_rate" name="agriculture_tree_rate" type="number" min="0" step="0.00001" value="${LADetails.agriculture_tree_rate }"
                                            class="validate">
                                        <label for="private_agri_tree_rate" class="fs-sm-8rem"> Agriculture tree rate </label>
										<span id="private_agri_tree_rateError" class="error-msg"></span>
	                                	<span id="agriculture_tree_rate_unitsError" class="error-msg right" ></span>
										<select class="validate-dropdown" id="agriculture_tree_rate_units" name="agriculture_tree_rate_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.agriculture_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>  
                          
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="private_forest_trees" name="forest_tree_nos" type="number" value="${LADetails.forest_tree_nos }"
                                            class="validate">
                                        <label for="private_forest_trees">Forest tree nos </label>
                                    </div> 
                                       
                                    <div class="col s12 m8 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_forest_tree_rate" name="forest_tree_rate" type="number" min="0" step="0.00001" value="${LADetails.forest_tree_rate }"
                                            class="validate">
                                        <label for="private_forest_tree_rate" class="fs-sm-8rem"> Forest tree rate </label>
                                         <span id="private_forest_tree_rateError" class="error-msg"></span>
	                                	<span id="forest_tree_rate_unitsError" class="error-msg right" ></span>
                                         <select class="validate-dropdown" id="forest_tree_rate_units" name="forest_tree_rate_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.forest_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>                                  
                                </div>                              
                                <div class="row">                                     
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    
                                         
                                </div>
                                <div class="row">                                     
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                            
                                    <%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="forest_tree_rate_units" name="forest_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.forest_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_tree_rate_unitsError" class="error-msg" ></span>
                               		</div>  --%> 
                               		<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_owner_consent" name="consent_from_owner" type="text" value="${LADetails.consent_from_owner }"
                                            class="validate datepicker">
                                        <label for="private_owner_consent">Consent from Owner </label>
                                        <button type="button" id="private_owner_consent_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field ">
                                        <input id="private_leagal_search_report" name="legal_search_report" value="${LADetails.legal_search_report }"
                                            type="text" class="validate datepicker">
                                        <label for="private_leagal_search_report"> Legal Search Report</label>
                                        <button type="button" id="private_leagal_search_rport_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                        <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="private_registartion_date" name="date_of_registration" value="${LADetails.date_of_registration }"
                                            type="text" class="validate datepicker">
                                        <label for="private_registartion_date">Date of Registration </label>
                                        <button type="button" id="private_registartion_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>  
                                </div>                               
                                <div class="row">                                     
                          			<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_possession_date" name="date_of_possession" type="text" value="${LADetails.date_of_possession }"
                                            class="validate datepicker">
                                        <label for="private_possession_date">Date of Possession</label>
                                        <button type="button" id="private_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
<%--                                     <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label">Possession Status</p>
                                        <select class="searchable" id="private_possession_status"
                                            name="private_possession_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.private_possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>  --%> 
                                    <div class="col s12 m8 l4 input-field offset-m2"> 
                                        <input id="private_forest_tree_survey" name="forest_tree_survey" value="${LADetails.forest_tree_survey }"
                                            type="text" class="validate datepicker">
                                        <label for="private_forest_tree_survey"> Forest Tree Survey </label>
                                        <button type="button" id="private_forest_tree_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row"> 
                                                                    
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_forest_tree_valuation" name="forest_tree_valuation" value="${LADetails.forest_tree_valuation }"
                                            type="text" class="validate datepicker mt-10">
                                        <label for="private_forest_tree_valuation"> Forest Tree Valuation </label>
                                        <button type="button" id="private_forest_tree_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>   
<%--                                      <div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label fs-sm-67rem"> Forest Tree Valuation Status </p>
                                        <select class="searchable" id="private_forest_tree_valuation_status"
                                            name="forest_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>  --%> 
                                       
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="private_horiculture_tree_survey"
                                            name="horticulture_tree_survey" type="text" value="${LADetails.horticulture_tree_survey }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_survey" class="fs-sm-8rem"> Horticulture Tree Survey </label>
                                        <button type="button" id="private_horiculture_tree_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                               
                                </div>                               
                                <div class="row">                                   
                                                                         
                                </div>
                                <div class="row">
                             
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_horiculture_tree_valuation"
                                            name="horticulture_tree_valuation" type="text" value="${LADetails.horticulture_tree_valuation }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_valuation"> Horticulture Tree Valuation
                                        </label>
                                        <button type="button" id="private_horiculture_tree_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>  
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="private_structure_survey" name="structure_survey" type="text" value="${LADetails.structure_survey }"
                                            class="validate datepicker">
                                        <label for="private_structure_survey"> Structure Survey </label>
                                        <button type="button" id="private_structure_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div> 
                                    <div class="col s6 m8 l4 input-field offset-m2">
                                        <input id="private_structure_valuation" name="structure_valuation" value="${LADetails.structure_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_structure_valuation"> Structure Valuation </label>
                                        <button type="button" id="private_structure_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                   
                                </div>
                                <div class="row">                                     
                                                               
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_borewell_survey" name="borewell_survey" type="text" value="${LADetails.borewell_survey }"
                                            class="validate datepicker">
                                        <label for="private_borewell_survey"> Borewell Survey </label>
                                        <button type="button" id="private_borewell_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>  
                                       
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="private_borewell_valuation" name="borewell_valuation" value="${LADetails.borewell_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_borewell_valuation"> Borewell Valuation </label>
                                        <button type="button" id="private_borewell_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>   
<%--                                     <div class="col s12 m8 l4 input-field offset-m2">
                                        <p class="searchable_label">Horticulture Tree Valuation Status </p>
                                        <select class="searchable" id="private_horticulture_tree_valuation_status"
                                            name="horticulture_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.horticulture_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>   --%>                                
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row"> 
<%--                                     <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label"> Structure Valuation Status </p>
                                        <select class="searchable" id="private_structure_valuation_status"
                                            name="structure_valuation_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.structure_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>  --%>
<%--                                     <div class="col s12 m4 l4 input-field">
                                        <p class="searchable_label">Borewell Valuation Status </p>
                                        <select class="searchable" id="private_borewell_valuation_status"
                                            name="borewell_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.borewell_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div> --%> 
<%--                                     <div class="col s12 m8 l4 input-field offset-m2">
                                        <p class="searchable_label"> RFP to ADTP status </p>
                                        <select class="searchable" id="private_rfp_to_adtp_status"
                                            name="rfp_to_adtp_status_fk">
                                            <option value="" >Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.rfp_to_adtp_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>     --%>                              
                                </div>
                                <div class="row">                                     
                                                                 
                                    <div class="col s12 m4 l4 input-field  offset-m2">
                                        <input id="private_rfp_adtp" name="date_of_rfp_to_adtp" type="text" value="${LADetails.date_of_rfp_to_adtp }"
                                            class="validate datepicker">
                                        <label for="private_rfp_adtp">Date of RFP to ADTP </label>
                                        <button type="button" id="private_rfp_adtp_icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>  
                                     
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_rate_fixation_date" name="date_of_rate_fixation_of_land" value="${LADetails.date_of_rate_fixation_of_land }"
                                            type="text" class="validate datepicker">
                                        <label for="private_rate_fixation_date"> Date of Rate Fixation of Land </label>
                                        <button type="button" id="private_rate_fixation_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div> 
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="private_sdo_payment_demand_date"
                                            name="sdo_demand_for_payment" type="text" value="${LADetails.sdo_demand_for_payment }"
                                            class="validate datepicker">
                                        <label for="private_sdo_payment_demand_date">SDO demand for payment </label>
                                        <button type="button" id="private_sdo_payment_demand_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                  
                                </div>
                              
                                <div class="row"> 
                               
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_payment_approval_date" name="date_of_approval_for_payment" value="${LADetails.date_of_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="private_payment_approval_date"> Date of Approval for Payment
                                        </label>
                                        <button type="button" id="private_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div> 
                                     
                                    <div class="col s12 m4 l4 amount-dropdown input-field">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_payment_amount" name="payment_amount" type="number" value="${LADetails.payment_amount }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="private_payment_amount" class="fs-sm-8rem">Payment Amount </label>
                                        <span id="private_payment_amountError" class="error-msg"></span>
	                                	<span id="payment_amount_unitsError" class="error-msg right" ></span>                                        
                                        <select class="validate-dropdown" id="payment_amount_units" name="payment_amount_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="private_payment_date" name="private_payment_date" type="text" value="${LADetails.private_payment_date }"
                                            class="validate datepicker">
                                        <label for="private_payment_date"> Payment Date </label>
                                        <button type="button" id="private_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                </div>
                                <div class="row">                                     
                                                                 
                                     <div class="col s6 m4 l4 input-field offset-m2 ">
                                        <input id="hundred_percent_Solatium" name="hundred_percent_Solatium" type="number" value="${LADetails.hundred_percent_Solatium }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="hundred_percent_Solatium">100 Percent Solatium </label>
                                    </div>
                                    
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="extra_25_percent" name="extra_25_percent" type="number" value="${LADetails.extra_25_percent }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="extra_25_percent">Extra 25 Percent </label>
                                    </div> 
                                     <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="land_compensation" name="land_compensation" type="number" value="${LADetails.land_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="land_compensation">Land Compensation </label>
                                    </div>
                                    <%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units" name="payment_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="payment_amount_unitsError" class="error-msg" ></span>
                               		</div>  --%>                                    
                                </div>
                                 
                                <div class="row">
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="agriculture_tree_compensation" name="agriculture_tree_compensation" type="number" value="${LADetails.agriculture_tree_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="agriculture_tree_compensation" class="fs-sm-67rem">Agriculture Tree Compensation </label>
                                    </div>   
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="total_rate_divide_m2" name="total_rate_divide_m2" type="number" value="${LADetails.total_rate_divide_m2 }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="total_rate_divide_m2">Total Rate Divide M2 </label>
                                    </div>
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="forest_tree_compensation" name="forest_tree_compensation" type="number" value="${LADetails.forest_tree_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="forest_tree_compensation" class="fs-sm-67rem">Forest Tree Compensation </label>
                                    </div>                                  
                                </div>
                                 <div class="row">                                     
                                                                  
                                     <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="structure_compensation" name="structure_compensation" type="number" value="${LADetails.structure_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="structure_compensation" class="fs-sm-8rem">Structure Compensation </label>
                                    </div>  
                                    
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="borewell_compensation" name="borewell_compensation" type="number" value="${LADetails.borewell_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="borewell_compensation" class="fs-sm-8rem">Borewell Compensation </label>
                                    </div>  
                                           
                                     <div class="col s6 m8 l4 input-field offset-m2">
                                        <input id="total_compensation" name="total_compensation" type="number" value="${LADetails.total_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="total_compensation">Total Compensation </label>
                                    </div>                          
                                </div>
                                   
                                <div class="row"> 
                            
                                     
                                </div>
                                  <div class="row">
                              
                                    <%-- <div class="col s6 m4 l4 input-field">
                                        <input id="extra_area" name="extra_area" type="text" value=""
                                            class="validate">
                                        <label for="extra_area">Extra Area </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="ready_region" name="ready_region" type="text" value=""
                                            class="validate">
                                        <label for="ready_region">Ready Region </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="contact_dpm" name="contact_dpm" type="text" value=""
                                            class="validate">
                                        <label for="contact_dpm">Contact </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="class_dpm" name="class_dpm" type="text" value=""
                                            class="validate">
                                        <label for="class_dpm">Class </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="land_area_ft" name="land_area_ft" type="text" value=""
                                            class="validate">
                                        <label for="land_area_ft">Land Area (ft) </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="rate_land_dpm" name="rate_land_dpm" type="text" value=""
                                            class="validate">
                                        <label for="rate_land_dpm">Rate Land </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 amount-dropdown input-field">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="gross_amount_dpm" name="gross_amount_dpm" type="number" value=""
                                            class="validate">
                                        <label for="gross_amount_dpm" class="fs-sm-8rem">Gross Amount </label>
                                        <span id="gross_amount_dpmError" class="error-msg"></span>
	                                	<span id="gross_amount_dpm_unitsError" class="error-msg right" ></span>                                        
                                        <select class="validate-dropdown" id="gross_amount_dpm_units" name="gross_amount_dpm_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.gross_amount_dpm_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div> 
                                    <div class="col s6 m4 l4 amount-dropdown input-field">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="diff_gross_amount_dpm" name="diff_gross_amount_dpm" type="number" value=""
                                            class="validate">
                                        <label for="diff_gross_amount_dpm" class="fs-sm-8rem">Difference In Gross Amount </label>
                                        <span id="diff_gross_amount_dpmError" class="error-msg"></span>
	                                	<span id="diff_gross_amount_dpm_unitsError" class="error-msg right" ></span>                                        
                                        <select class="validate-dropdown" id="diff_gross_amount_dpm_units" name="diff_gross_amount_dpm_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.diff_gross_amount_dpm_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    </div>
                                    <div class="row">
                                    	<div class="col s6 m4 l4 input-field">
                                        <input id="consent_record_dpm" name="consent_record_dpm" type="text" value=""
                                            class="validate">
                                        <label for="consent_record_dpm">Consent Record </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="reason_delay_registry_dpm" name="reason_for_delay_in_registry_dpm" type="text" value=""
                                            class="validate">
                                        <label for="reason_delay_registry_dpm">Reason For Delay In Registry </label>                                   
                                    </div> 
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="type_land_dpm" name="type_land_dpm" type="text" value=""
                                            class="validate">
                                        <label for="type_land_dpm">Type Land </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="plot_area_dpm" name="plot_area_dpm" type="text" value=""
                                            class="validate">
                                        <label for="plot_area_dpm">Plot Area </label>                                   
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="package_dpm" name="package_dpm" type="text" value=""
                                            class="validate">
                                        <label for="package_dpm">Package </label>                                   
                                    </div> --%>
                                    </div> 
								</div>
                                </div>  
                                 <%-- <div class="row">
                                                                 
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_area_to_be_acquired" name="private_area_to_be_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.private_area_to_be_acquired }"
                                            class="validate">
                                        <label for="private_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="private_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                     <div class="col s12 m4 l4 input-field">
                                        <input id="private_area_acquired" name="private_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.private_area_acquired }"
                                            class="validate">
                                        <label for="private_area_acquired"> Area Acquired (Ha) </label>
                                        <span class="units">units</span>
                                        <span id="private_area_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_name_of_owner" name="name_of_the_owner" type="text" value="${LADetails.name_of_the_owner }"
                                            class="validate">
                                        <label for="private_name_of_owner">Name of Owner</label>
                                    </div>                                   
                                     
                                </div>
                                <div class="row">                                         
                                 	                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_basic_rate" name="basic_rate" type="number" min="0" step="0.00001" value="${LADetails.basic_rate }"
                                            class="validate">
                                        <label for="private_basic_rate">Basic Rate </label>
                                        <span id="private_basic_rateError" class="error-msg"></span>     
	                                	<span id="basic_rate_unitsError" class="error-msg right" ></span>                                 
                                        <select class="validate-dropdown" id="basic_rate_units" name="basic_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.basic_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                 	<div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="basic_rate_units" name="basic_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.basic_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="basic_rate_unitsError" class="error-msg" ></span>
                               		</div> 
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_attachment_no" name="private_attachment_no" type="text" value="${LADetails.private_attachment_no }"
                                            class="validate">
                                        <label for="private_attachment_no" class="fs-sm-8rem">Attachment Number </label>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_agri_trees" name="agriculture_tree_nos" type="text" value="${LADetails.agriculture_tree_nos }"
                                            class="validate">
                                        <label for="private_agri_trees"> Agriculture tree nos</label>
                                       
                                    </div>                                     
                                </div>                              
                                <div class="row">                                     
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    
                                         
                                </div>
                                <div class="row">                                     
                                    <!-- <div class="col s12 m8 input-field">
                                        <div class="row"> -->
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_agri_tree_rate" name="agriculture_tree_rate" type="number" min="0" step="0.00001" value="${LADetails.agriculture_tree_rate }"
                                            class="validate">
                                        <label for="private_agri_tree_rate" class="fs-sm-8rem"> Agriculture tree rate </label>
										<span id="private_agri_tree_rateError" class="error-msg"></span>
	                                	<span id="agriculture_tree_rate_unitsError" class="error-msg right" ></span>
										<select class="validate-dropdown" id="agriculture_tree_rate_units" name="agriculture_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.agriculture_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>  
                                    <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="agriculture_tree_rate_units" name="agriculture_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.agriculture_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="agriculture_tree_rate_unitsError" class="error-msg" ></span>
                               		</div>
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="private_forest_trees" name="forest_tree_nos" type="number" value="${LADetails.forest_tree_nos }"
                                            class="validate">
                                        <label for="private_forest_trees">Forest tree nos </label>
                                    </div>
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_forest_tree_rate" name="forest_tree_rate" type="number" min="0" step="0.00001" value="${LADetails.forest_tree_rate }"
                                            class="validate">
                                        <label for="private_forest_tree_rate" class="fs-sm-8rem"> Forest tree rate </label>
                                         <span id="private_forest_tree_rateError" class="error-msg"></span>
	                                	<span id="forest_tree_rate_unitsError" class="error-msg right" ></span>
                                         <select class="validate-dropdown" id="forest_tree_rate_units" name="forest_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.forest_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="forest_tree_rate_units" name="forest_tree_rate_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.forest_tree_rate_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="forest_tree_rate_unitsError" class="error-msg" ></span>
                               		</div>  
                                </div>                               
                                <div class="row">                                     
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_owner_consent" name="consent_from_owner" type="text" value="${LADetails.consent_from_owner }"
                                            class="validate datepicker">
                                        <label for="private_owner_consent">Consent from Owner </label>
                                        <button type="button" id="private_owner_consent_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field ">
                                        <input id="private_leagal_search_report" name="legal_search_report" value="${LADetails.legal_search_report }"
                                            type="text" class="validate datepicker">
                                        <label for="private_leagal_search_report"> Legal Search Report</label>
                                        <button type="button" id="private_leagal_search_rport_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_registartion_date" name="date_of_registration" value="${LADetails.date_of_registration }"
                                            type="text" class="validate datepicker">
                                        <label for="private_registartion_date">Date of Registration </label>
                                        <button type="button" id="private_registartion_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row"> 
                                	<div class="col s6 m4 l6 input-field offset-m2">
                                        <input id="private_possession_date" name="date_of_possession" type="text" value="${LADetails.date_of_possession }"
                                            class="validate datepicker">
                                        <label for="private_possession_date">Date of Possession</label>
                                        <button type="button" id="private_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s6 m4 l6 input-field">
                                        <p class="searchable_label">Possession Status</p>
                                        <select class="searchable" id="private_possession_status"
                                            name="private_possession_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.possession_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s7 m4 l4 input-field">
                                        <input id="private_special_feature" name="private_special_feature" type="text" value="${LADetails.private_special_feature }"
                                            class="validate mt-10">
                                        <label for="private_special_feature"> Special Feature </label>
                                    </div>                                     
                                </div>                               
                                <div class="row">                                   
                                                                         
                                </div>
                                <div class="row">
                                	<div class="col s12 m4 l4 input-field offset-m2"> 
                                        <input id="private_forest_tree_survey" name="forest_tree_survey" value="${LADetails.forest_tree_survey }"
                                            type="text" class="validate datepicker">
                                        <label for="private_forest_tree_survey"> Forest Tree Survey </label>
                                        <button type="button" id="private_forest_tree_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="private_forest_tree_valuation" name="forest_tree_valuation" value="${LADetails.forest_tree_valuation }"
                                            type="text" class="validate datepicker mt-10">
                                        <label for="private_forest_tree_valuation"> Forest Tree Valuation </label>
                                        <button type="button" id="private_forest_tree_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <p class="searchable_label fs-sm-67rem"> Forest Tree Valuation Status </p>
                                        <select class="searchable" id="private_forest_tree_valuation_status"
                                            name="forest_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.forest_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_horiculture_tree_survey"
                                            name="horticulture_tree_survey" type="text" value="${LADetails.horticulture_tree_survey }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_survey" class="fs-sm-8rem"> Horticulture Tree Survey </label>
                                        <button type="button" id="private_horiculture_tree_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_horiculture_tree_valuation"
                                            name="horticulture_tree_valuation" type="text" value="${LADetails.horticulture_tree_valuation }"
                                            class="validate datepicker">
                                        <label for="private_horiculture_tree_valuation"> Horticulture Tree Valuation
                                        </label>
                                        <button type="button" id="private_horiculture_tree_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_structure_survey" name="structure_survey" type="text" value="${LADetails.structure_survey }"
                                            class="validate datepicker">
                                        <label for="private_structure_survey"> Structure Survey </label>
                                        <button type="button" id="private_structure_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row"> 
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_structure_valuation" name="structure_valuation" value="${LADetails.structure_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_structure_valuation"> Structure Valuation </label>
                                        <button type="button" id="private_structure_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="private_borewell_survey" name="borewell_survey" type="text" value="${LADetails.borewell_survey }"
                                            class="validate datepicker">
                                        <label for="private_borewell_survey"> Borewell Survey </label>
                                        <button type="button" id="private_borewell_survey_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_borewell_valuation" name="borewell_valuation" value="${LADetails.borewell_valuation }"
                                            type="text" class="validate datepicker">
                                        <label for="private_borewell_valuation"> Borewell Valuation </label>
                                        <button type="button" id="private_borewell_valuation_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Horticulture Tree Valuation Status </p>
                                        <select class="searchable" id="private_horticulture_tree_valuation_status"
                                            name="horticulture_tree_valuation_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.horticulture_tree_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <p class="searchable_label"> Structure Valuation Status </p>
                                        <select class="searchable" id="private_structure_valuation_status"
                                            name="structure_valuation_status_fk">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.structure_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label">Borewell Valuation Status </p>
                                        <select class="searchable" id="private_borewell_valuation_status"
                                            name="borewell_valuation_status_fk">
                                            <option value="" >Select</option>
                                           <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.borewell_valuation_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                     
                                </div>
                              
                                <div class="row"> 
                                	<div class="col s12 m4 l4 input-field offset-m2">
                                        <p class="searchable_label"> RFP to ADTP status </p>
                                        <select class="searchable" id="private_rfp_to_adtp_status"
                                            name="rfp_to_adtp_status_fk">
                                            <option value="" >Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.rfp_to_adtp_status_fk eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>                                    
                                    <div class="col s12 m4 l4 input-field ">
                                        <input id="private_rfp_adtp" name="date_of_rfp_to_adtp" type="text" value="${LADetails.date_of_rfp_to_adtp }"
                                            class="validate datepicker">
                                        <label for="private_rfp_adtp">Date of RFP to ADTP </label>
                                        <button type="button" id="private_rfp_adtp_icon" class="datepicker-button"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_rate_fixation_date" name="date_of_rate_fixation_of_land" value="${LADetails.date_of_rate_fixation_of_land }"
                                            type="text" class="validate datepicker">
                                        <label for="private_rate_fixation_date"> Date of Rate Fixation of Land </label>
                                        <button type="button" id="private_rate_fixation_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                </div>
                                <div class="row">                                     
                                    <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="private_sdo_payment_demand_date"
                                            name="sdo_demand_for_payment" type="text" value="${LADetails.sdo_demand_for_payment }"
                                            class="validate datepicker">
                                        <label for="private_sdo_payment_demand_date">SDO demand for payment </label>
                                        <button type="button" id="private_sdo_payment_demand_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="private_payment_approval_date" name="date_of_approval_for_payment" value="${LADetails.date_of_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="private_payment_approval_date"> Date of Approval for Payment
                                        </label>
                                        <button type="button" id="private_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m4 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="private_payment_amount" name="payment_amount" type="number" value="${LADetails.payment_amount }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="private_payment_amount" class="fs-sm-8rem">Payment Amount </label>
                                        <span id="private_payment_amountError" class="error-msg"></span>
	                                	<span id="payment_amount_unitsError" class="error-msg right" ></span>                                        
                                        <select class="validate-dropdown" id="payment_amount_units" name="payment_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units" name="payment_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="payment_amount_unitsError" class="error-msg" ></span>
                               		</div>                                     
                                </div>
                                 
                                <div class="row">
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="private_payment_date" name="private_payment_date" type="text" value="${LADetails.private_payment_date }"
                                            class="validate datepicker">
                                        <label for="private_payment_date"> Payment Date </label>
                                        <button type="button" id="private_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                     <div class="col s6 m4 l4 input-field ">
                                        <input id="hundred_percent_Solatium" name="hundred_percent_Solatium" type="number" value="${LADetails.hundred_percent_Solatium }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="hundred_percent_Solatium">100 Percent Solatium </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="extra_25_percent" name="extra_25_percent" type="number" value="${LADetails.extra_25_percent }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="extra_25_percent">Extra 25 Percent </label>
                                    </div>                                     
                                </div>
                                 <div class="row">                                     
                                     <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="total_rate_divide_m2" name="total_rate_divide_m2" type="number" value="${LADetails.total_rate_divide_m2 }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="total_rate_divide_m2">Total Rate Divide M2 </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="land_compensation" name="land_compensation" type="number" value="${LADetails.land_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="land_compensation">Land Compensation </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="agriculture_tree_compensation" name="agriculture_tree_compensation" type="number" value="${LADetails.agriculture_tree_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="agriculture_tree_compensation" class="fs-sm-67rem">Agriculture Tree Compensation </label>
                                    </div>                                     
                                </div>
                                   
                                <div class="row"> 
                                	<div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="forest_tree_compensation" name="forest_tree_compensation" type="number" value="${LADetails.forest_tree_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="forest_tree_compensation" class="fs-sm-67rem">Forest Tree Compensation </label>
                                    </div>                                    
                                     <div class="col s6 m4 l4 input-field ">
                                        <input id="structure_compensation" name="structure_compensation" type="number" value="${LADetails.structure_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="structure_compensation" class="fs-sm-8rem">Structure Compensation </label>
                                    </div>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="borewell_compensation" name="borewell_compensation" type="number" value="${LADetails.borewell_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="borewell_compensation" class="fs-sm-8rem">Borewell Compensation </label>
                                    </div>
                                     
                                </div>
                                  <div class="row">
                                     
                                     <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="total_compensation" name="total_compensation" type="number" value="${LADetails.total_compensation }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="total_compensation">Total Compensation </label>
                                    </div>                                   
                                     
                                </div> --%>
                                
                            </div>
             <!-- //*********************************************************   -->             
 							<div id="railway_div" style="display: none; ">
                                <h6 class="center-align primary-text-bold">Railway Land Details </h6>
                                <div class="row">                                     
                                <%--     <div class="col s12 m4 l4 input-field offset-m2">
                                        <input id="railway_area_to_be_acquired" name="railway_area_to_be_acquired" value="${LADetails.railway_area_to_be_acquired }"
                                            type="number" min="0.0001" step="0.0001" class="validate">
                                        <label for="railway_area_to_be_acquired"> Area to be Acquired </label>
                                        <span class="units">units</span>
                                        <span id="railway_area_to_be_acquiredError" class="error-msg"></span>
                                    </div>
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="railway_area_acquired" name="railway_area_acquired" type="number" min="0.0001" step="0.0001" value="${LADetails.railway_area_acquired }"
                                            class="validate">
                                        <label for="railway_area_acquired"> Area Acquired (Ha) </label>
                                        <span class="units">units</span>
                                        <span id="railway_area_acquiredError" class="error-msg"></span>
                                    </div> --%>
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="railway_online_submission" type="text" value="${LADetails.railway_online_submission }"
                                            name="railway_online_submission" class="validate datepicker">
                                        <label for="railway_online_submission">Online Submission</label>
                                        <button type="button" id="railway_online_submission_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                
                                	<div class="col s6 m4 l4 input-field ">
                                        <input id="railway_submission_dycfo" type="text" name="railway_submission_date_to_DyCFO" value="${LADetails.railway_submission_date_to_DyCFO }"
                                            class="validate datepicker">
                                        <label for="railway_submission_dycfo" class="fs-sm-8rem">Submission to DyCFO</label>
                                        <button type="button" id="railway_submission_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="railway_submission_ccf" name="railway_submission_date_to_CCF_Thane" type="text" value="${LADetails.railway_submission_date_to_CCF_Thane }"
                                            class="validate datepicker">
                                        <label for="railway_submission_ccf"> Submission Date to CCF Thane </label>
                                        <button type="button" id="railway_submission_ccf_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                                                 
                                </div>
                                <div class="row">  
                                    <div class="col s12 m4 l4 input-field offset-m2 ">
                                        <input id="railway_submission_nodal_officer"
                                            name="railway_submission_date_to_nodal_officer_CCF_Nagpur" type="text" value="${LADetails.railway_submission_date_to_nodal_officer_CCF_Nagpur }"
                                            class="validate datepicker">
                                        <label for="railway_submission_nodal_officer" class="fs-sm-8rem wd-wrap"> Submission Date to Nodal
                                            Officer / CCF Nagpur </label>
                                        <button type="button" id="railway_submission_nodal_officer_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                    
                                    <div class="col s12 m4 l4 input-field">
                                        <input id="railway_submission_revenue_sec" name="railway_submission_date_to_revenue_secretary_mantralaya" value="${LADetails.railway_submission_date_to_revenue_secretary_mantralaya }"
                                            type="text" class="validate datepicker">
                                        <label for="railway_submission_revenue_sec" class="fs-sm-8rem wd-wrap"> Submission Date to Revenue Secretary
                                            Mantralaya </label>
                                        <button type="button" id="railway_submission_revenue_sec_icon"
                                            class="datepicker-button" class="white"><i
                                                class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="railway_submission_regional_office" value="${LADetails.railway_submission_date_to_regional_office_nagpur }"
                                            name="railway_submission_date_to_regional_office_nagpur" type="text"
                                            class="validate datepicker">
                                        <label for="railway_submission_regional_office" class="fs-sm-8rem wd-wrap"> Submission Date to Regional
                                            Office Nagpur
                                        </label>
                                        <button type="button" id="railway_submission_regional_office_icon" 
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                                                
                                </div>
                                <div class="row">                                     
                                    
                                                                         
                                </div>
                                <div class="row">
                                    <div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="railway_approval_regional_office"
                                            name="railway_date_of_approval_by_Rregional_Office_agpur" type="text" value="${LADetails.railway_date_of_approval_by_Rregional_Office_agpur }"
                                            class="validate datepicker">
                                        <label for="railway_approval_regional_office"> Date of Approval by Regional
                                            Office</label>
                                        <button type="button" id="railway_approval_regional_office_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                	<div class="col s12 m8 l4 input-field offset-m2">
                                        <input id="railway_valuation_dycfo" name="railway_valuation_by_DyCFO" type="text" value="${LADetails.railway_valuation_by_DyCFO }"
                                            class="validate datepicker">
                                        <label for="railway_valuation_dycfo">Valuation by DyCFO </label>
                                        <button type="button" id="railway_valuation_dycfo_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>                                     
                                    <div class="col s12 m8 l4 amount-dropdown input-field offset-m2">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="railway_demanded_amount" name="railway_demanded_amount" type="number" value="${LADetails.railway_demanded_amount }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="railway_demanded_amount" class="fs-sm-8rem">Demanded Amount </label>
                                        <span id="railway_demanded_amountError" class="error-msg"></span>
	                                	<span id="railway_demanded_amount_unitsError" class="error-msg right" ></span>
                                        <select class="validate-dropdown" id="demanded_amount_units" name="demanded_amount_units">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>
                                    <%-- <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="demanded_amount_units" name="demanded_amount_units">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.demanded_amount_units eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="railway_demanded_amount_unitsError" class="error-msg" ></span>
                               		</div> --%>
                               
                                   <%--  <div class="col s4 m1 l1 input-field pt-5">
	                                	<p class="searchable_label">Unit</p>
	                                	<select class="units searchable validate-dropdown" id="payment_amount_units_railway" name="payment_amount_units_railway">
	                                		<option value="">Select</option>
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_railway eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
	                                	<span id="railway_payment_amount_unitsError" class="error-msg" ></span>
                               		</div>  --%>                                
                                </div>
                                <div class="row">  
                                   <div class="col s12 m4 l4 amount-dropdown input-field offset-m2 ">
                                        <i class="material-icons amount-symbol center-align">₹</i>
                                        <input id="railway_payment_amount" name="railway_payment_amount" type="number" value="${LADetails.railway_payment_amount }" min="0" step="0.00001"
                                            class="validate">
                                        <label for="railway_payment_amount" class="fs-sm-8rem"> Payment Amount </label>
                                        <span id="railway_payment_amountError" class="error-msg"></span>
	                                	<span id="railway_payment_amount_unitsError" class="error-msg right" ></span>
                                        <select class=" validate-dropdown" id="payment_amount_units_railway" name="payment_amount_units_railway">
	                                		<!-- <option value="">Select</option> -->
	                                		<c:forEach var="obj" items="${unitsList }">
		                                      <option value="${obj.value }" <c:if test="${LADetails.payment_amount_units_railway eq obj.value}">selected</c:if>>${obj.unit }</option>
		                                	</c:forEach>
	                                	</select>
                                    </div>                                   
                                    <div class="col s6 m4 l4 input-field">
                                        <input id="railway_payment_approval_date" name="railway_approval_for_payment" value="${LADetails.railway_approval_for_payment }"
                                            type="text" class="validate datepicker">
                                        <label for="railway_payment_approval_date" class="fs-sm-8rem"> Approval for Payment</label>
                                        <button type="button" id="railway_payment_approval_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                    <div class="col s6 m8 l4 input-field offset-m2">
                                        <input id="railway_payment_date" name="railway_payment_date" type="text" value="${LADetails.railway_payment_date }"
                                            class="validate datepicker">
                                        <label for="railway_payment_date"> Payment Date </label>
                                        <button type="button" id="railway_payment_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div>
                                </div>
                               
                             <div class="row">
                                    <div class="col s6 m4 l4 input-field offset-m2">
                                        <input id="railway_possession_date" name="railway_possession_date" type="text" value="${LADetails.railway_possession_date }"
                                            class="validate datepicker mt-10">
                                        <label for="railway_possession_date"> Possession Date</label>
                                        <button type="button" id="railway_possession_date_icon"
                                            class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    </div> 
<%--                              		<div class="col s6 m4 l4 input-field">
                                        <p class="searchable_label">Possession Status </p>
                                        <select class="searchable" id="railway_possession_status"
                                            name="railway_possession_status">
                                            <option value="" >Select</option>
                                             <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.railway_possession_status eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div>  --%>                                    
<%--                                     <div class="col s6 m8 l4 input-field offset-m2">
                                        <p class="searchable_label">Payment Status </p>
                                        <select class="searchable" id="railway_payment_status"
                                            name="railway_payment_status">
                                            <option value="" >Select</option>
                                            <c:forEach var="obj" items="${statusList}">
												<option value="${obj.status }"
													<c:if test="${LADetails.railway_payment_status eq obj.status }">selected</c:if>>${obj.status }</option>
											</c:forEach>
                                        </select>
                                    </div> --%>
                              <!--   </div>
                                <div class="row">     -->                                 
                                                                   
                                 </div>
                              </div>
                            
                            <div class="row">                                 
                                <div class="col m12 s12  l12">
                                    <div class="row">
                                        <div class="col l12 m12 s12">
                                        	<%-- <c:if test="${action eq 'add'}">
			                             <div id="selectedFilesInput">
			                                    	<div class="file-field input-field" id="laFilesDiv1" >
				                                        <div class="btn bg-m t-c">
				                                            <span>Attach Files</span>
				                                            <input type="file" id="laFiles1" name="laFiles"   onchange="selectFile('1')">
				                                        </div>
				                                        <div class="file-path-wrapper">
				                                            <input class="file-path validate" type="text">
				                                        </div>                                       
				                                    </div>
												</div>
			                                    <div id="selectedFiles">
												</div>
											
									  </c:if>	
									  <c:if test="${action eq 'edit'}">
													<c:set var="existingDeliverableFilesLength" value="${fn:length(LADetails.laFilesList )}"></c:set>
													<c:if test="${fn:length(LADetails.laFilesList ) gt 0}">
														<c:set var="existingDeliverableFilesLength" value="${fn:length(LADetails.laFilesList )+1}"></c:set>
													</c:if>
													<div id="selectedFilesInput">
				                                    	<div class="file-field input-field" id="laFilesDiv${existingDeliverableFilesLength }" >
					                                        <div class="btn bg-m t-c">
					                                            <span>Attach Files</span>
					                                            <input type="file" id="laFiles${existingDeliverableFilesLength }" name="laFiles"  onchange="selectFile('${existingDeliverableFilesLength }')">
					                                        </div>
					                                        <div class="file-path-wrapper">
					                                            <input class="file-path validate" type="text">
					                                        </div>                                       
					                                    </div>
													</div>
				                                    
				                                    <div id="selectedFiles">
				                                    	<c:forEach var="obj" items="${LADetails.laFilesList }" varStatus="index">
															 <div id="laFileNames${index.count }">
																<a href="<%=CommonConstants.LAND_ACQUISITION_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
																<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
																<input type="hidden" name="laFileNames" value="${obj.attachment }">
														     </div>
														     <div style="clear:both" ></div>
														</c:forEach>
													</div>
				                             </c:if>	 --%>
				                             
				                             <div class="row section scrollspy" id="documentDetails">
	                            <div class="col l12 m8 s12 offset-m2"  >
	                                <div class="row fixed-width">
	                                     <h5 class="center-align"><span class="div-header">Attachments</span></h5> 
	                                    <div class="table-inside">
	                                        <table class="mdl-data-table mobile_responsible_table">
	                                            <thead>
	                                                <tr>
	                                                	<th class="w250px">File Type </th>
	                                                    <th class="w250px">Name </th>
	                                                    <th style="text-align:center;" class="w250px">Attachment</th>
	                                                    <th class="w0"> </th>
	                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
	                                                    	<th style="width:8%">Action</th>
	                                                    </c:if>
	                                                </tr>
	                                            </thead>
	                                            <tbody id="laDocumentTableBody" >
	                                             <c:choose>
			                                        <c:when test="${not empty LADetails.laFilesList && fn:length(LADetails.laFilesList) gt 0 }">			                                          
				                                        <c:forEach var="docObj" items="${LADetails.laFilesList }" varStatus="index">  
			                                                <tr id="laDocumentRow${index.count }">
			                                                	<td data-head="File Type" class="input-field">
																	<select  name="la_file_typess"  id="la_file_types${index.count }"  class="validate-dropdown searchable">
					                                   					 <option value="" >Select</option>
					                                   					  <c:forEach var="obj" items="${laFileType }">
						                                    				<option value="${obj.la_file_type }"<c:if test="${docObj.la_file_type_fk eq obj.la_file_type}">selected</c:if>>${obj.la_file_type }</option>
						                                  				  </c:forEach>
					                               					  </select>
					                               					  <span id="la_file_typess${index.count }Error" class="error-msg" ></span>
															    </td>
			                                                    <td data-head="Name" class="input-field"> <input id="laDocumentNames${index.count }" maxlength="50" data-length="50" name="laDocumentNames" type="text" class="validate w75 pdr4em"
			                                                            placeholder="Name" value="${docObj.name }">
			                                                            <span id="laDocumentNames${index.count }Error" class="error-msg" ></span>
			                                                    </td>
			                                                    <td data-head="Attachment" class="input-field center-align">
			                                                        <span class="normal-btn">
			                                                            <input type="file" id="laFiles${index.count }" name="laFiles"
			                                                                style="display:none" onchange="getFileName('${index.count }')"/>
			                                                            <label for="laFiles${index.count }" class="btn bg-m"><i
			                                                                    class="fa fa-paperclip"></i></label>
			                                                            <input type="hidden" id="laDocumentFileNames${index.count }" name="laDocumentFileNames" value="${docObj.attachment }">
			                                                             <span id="laDocumentFileName${index.count }" class="filevalue"></span>
			                                                             <span id="laFiles${index.count }Error" class="error-msg" ></span>
			                                                          </span>
			                                                    </td>
			                                                    <td>
			                                                     		<input type="hidden" id="la_file_ids${index.count }" name="la_file_ids" value="${docObj.la_file_id }"/>
			                                                      		<a href="<%=CommonConstants.LAND_ACQUISITION_FILES%>${docObj.attachment } " class="filevalue" download><i class="fa fa-arrow-down"></i></a>
			                                                        
			                                                    </td>
			                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
				                                                    <td class="mobile_btn_close">
				                                                        <a href="javascript:void(0);" onclick="removeDesignDocument('${index.count }');" class="btn waves-effect waves-light red t-c "> <i
				                                                                class="fa fa-close"></i></a>
				                                                    </td>
			                                                    </c:if>
			                                                </tr> 
	                                                	</c:forEach>
	                                           		</c:when>
	                                             	<c:otherwise>
	                                             		<tr id="laDocumentRow0">
	                                             			<td data-head="File Type " class="input-field">																		
																<select  name="la_file_typess"  id="la_file_types0"  class="validate-dropdown searchable">
				                                   					 <option value="" >Select</option>
				                                         			  <c:forEach var="obj" items="${laFileType }">
						                                    				<option value="${obj.la_file_type }">${obj.la_file_type }</option>
						                                  			  </c:forEach>
				                               					  </select>
				                               					   <span id="la_file_typess0Error" class="error-msg" ></span>
															    </td>
		                                                    <td data-head="Name " class="input-field"> <input id="laDocumentNames0" maxlength="100" data-length="100" name="laDocumentNames" type="text" class="validate w75 pdr4em"
		                                                            placeholder="Name">
		                                                            <span id="laDocumentNames0Error" class="error-msg" ></span>
		                                                    </td>
		                                                    <td data-head="Attachment" class="input-field center-align">
		                                                        <span class="normal-btn">
		                                                            <input type="file" id="laFiles0" name="laFiles"
		                                                                style="display:none" onchange="getFileName('0')"/>
		                                                            <label for="laFiles0" class="btn bg-m"><i
		                                                                    class="fa fa-paperclip"></i></label>
		                                                            <input type="hidden" id="laDocumentFileNames0" name="laDocumentFileNames" value="">
		                                                            <span id="laDocumentFileName0" class="filevalue"></span>
		                                                            <span id="laFiles0Error" class="error-msg" ></span>
		                                                        </span>
		                                                    </td>
		                                                    <td><input type="hidden" id="la_file_ids0" name="la_file_ids" value= ""/>
		                                                    </td>
		                                                    
		                                                    <c:if test="${sessionScope.USER_ROLE_NAME eq 'IT Admin'}"> 
			                                                    <td class="mobile_btn_close">
			                                                        <a href="javascript:void(0);" onclick="removeDesignDocument('0');" class="btn waves-effect waves-light red t-c "> <i
			                                                                class="fa fa-close"></i></a>
			                                                    </td>
		                                                    </c:if>
		                                                </tr>
	                                             	</c:otherwise>
                                            	</c:choose> 
	                                            </tbody>
	                                        </table>
	                                        
	                                        <table class="mdl-data-table table-add bd-none">
		                                        <tbody>                                          
		                                            <tr class="bd-none">
														<td colspan="3" class="bd-none">	<a type="button"  class="btn waves-effect waves-light bg-m t-c add-align"  onclick="addDesignDocumentRow()"> <i
		                                                            class="fa fa-plus"></i></a></td>
		                                              </tr>
		                                        </tbody>
		                                     </table>
		                                   	 <c:choose>
		                                        <c:when test="${not empty LADetails.laFilesList && fn:length(LADetails.laFilesList) gt 0 }">
		                                    		<input type="hidden" id="documentRowNo"  name="documentRowNo" value="${fn:length(LADetails.laFilesList) }" />
		                                    	</c:when>
		                                     	<c:otherwise>
		                                     		<input type="hidden" id="documentRowNo"  name="documentRowNo" value="0" />
		                                     	</c:otherwise>
		                                     </c:choose> 
	                                    </div>
	                                </div>
	                            </div>
							</div>
                                            
                                            </div>
                                    <!--     <div class="col m12 s12">
                                            <div class="row">
                                                row 7
                                                <div class="col s5 m6 l6 input-field center-align">
                                                    <p style="margin-top: 8px;">Any Issue ?</p>
                                                </div>
                                                <div class="col s7 m6 l6 input-field">
                                                    <p class="radiogroup"
                                                        style="padding-bottom: 10px;padding-top: 10px;">
                                                        <label>
                                                            <input class="with-gap" name="is_there_issue" type="radio"
                                                                value="Yes" />
                                                            <span>Yes</span>
                                                        </label> &nbsp; <label>
                                                            <input class="with-gap" name="is_there_issue" type="radio"
                                                                value="No" />
                                                            <span>No</span>
                                                        </label>
                                                    </p>
                                                </div>
                                            </div>
                                        </div> -->
                                    </div>
                                </div>
                            </div>

                            <div id="issue_yes" style="display: none;">
                                <div class="row">
                                    <h6 class="center-align primary-text-bold">Issue
                                        Details </h6>
                                     
                                    <div class="col l12 m8 s12 offset-m2">
                                        <div class="row">
                                            <div class="col s12 m4 l6 input-field" style="margin-top: 32px;">
                                                <p class="searchable_label">Issue Category</p>
                                                <select class="searchable" id="issue_category_id" name="issue_category_id">
                                                    <option value="" selected>Select</option>
                                                    <c:forEach var="obj" items="${issueCatogoriesList}">
													<option value="${obj.category }">${obj.category }</option>
												</c:forEach>
                                                </select>
                                            </div>
                                            <div class="col s12 m8 l6 input-field" style="padding-top: 4px;">
                                                <p class="prio">Priority</p>
                                                <p class="radiogroup">
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="low" />
                                                        <span>Low</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="medium" />
                                                        <span>Medium</span>
                                                    </label>
                                                    &nbsp;
                                                    <label>
                                                        <input class="with-gap" name="issue_priority_id" type="radio"
                                                            value="high" />
                                                        <span>High</span>
                                                    </label>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                     
                                    <div class="col s12 m8 l12 input-field offset-m2">
                                        <textarea  class="pmis-textarea" id="issue_description" name="issue_description"
                                            data-length="500"></textarea>
                                        <label for="issueDesc">Issue Description</label>
                                    </div>
                                </div>
                            </div>
 					 <div class="row">
	                                <!-- row 10 -->
	                                 
		                                <div class="col s12 m8 l12 input-field offset-m2">
		                                    <textarea id="remarks" name="remarks" class="pmis-textarea" maxlength="1000"
		                                        data-length="1000">${LADetails.remarks } </textarea>
		                                    <label for="remarks">Remarks</label>
		                                </div>
	                      </div>	
	 					 <div class="row">
	                                <!-- row 10 -->
	                                 
		                                <div class="col s12 m8 l12 input-field offset-m2">
		                                    <textarea id="issues" name="issues" class="pmis-textarea" maxlength="1000"
		                                        data-length="1000">${LADetails.issues } </textarea>
		                                    <label for="issues">Issues</label>
		                                </div>
	                      </div>				                
				<%--      <c:if test="${action eq 'edit'}">
                            <div class="row">
                                <!-- row 10 -->
                                 
                                 <c:if test="${LADetails.type_of_land == 'Government'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.gov_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                                 <c:if test="${LADetails.type_of_land == 'Forest'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.forest_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                                 <c:if test="${LADetails.type_of_land == 'Private'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.private_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                                 <c:if test="${LADetails.type_of_land == 'Railway'}">
	                                <div class="col s12 m8 l12 input-field offset-m2">
	                                    <textarea id="remarks" name="remarks" class="pmis-textarea" 
	                                        data-length="1000">${LADetails.railway_remarks } </textarea>
	                                    <label for="remarks">Remarks</label>
	                                </div>
                                </c:if>
                            </div>
 							</c:if> --%>
                            <div class="row">                                
                                <div class="col s6 m4 l6 mt-brdr center-align offset-m2">
	                                <div class="m-1">
	                                     <c:if test="${action eq 'edit'}">
	                                       <button type="button" onclick="updateLAFrom();" class="btn waves-effect waves-light bg-m">Update</button>
	                                     </c:if>
										 <c:if test="${action eq 'add'}"> 
					                       <button type="button" onclick="addLAForm();" class="btn waves-effect waves-light bg-m" style="min-width:90px">Add</button>
										 </c:if>                                
	                                </div>
                                </div>
                                 <div class="col s6 m4 l6 mt-brdr center-align">
                                	<div class="m-1">
                                     	<a href="<%=request.getContextPath()%>/land-acquisition" class="btn waves-effect waves-light bg-s">Cancel</a>
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

    <!-- footer  -->
 <jsp:include page="../layout/footer.jsp"></jsp:include>
 
    <script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>
	<script src="/pmis/resources/js/datepickerDepedency.js"></script>
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/jquery.dataTables-v.1.10.min.js"></script>
	<script src="/pmis/resources/js/dataTables.material.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script src="/pmis/resources/js/moment-v2.8.4.min.js"></script>
	<script src="/pmis/resources/js/datetime-moment-v1.10.12.js"></script>
	<script src="/pmis/resources/js/datetimepicker.js"></script>
    <script>
    $(document).ready(function() {
    	$('.modal').modal({ dismissible: false });
        $(".num").keypress(function() {
            if ($(this).val().length == $(this).attr("maxlength")) {
                return false;
            }
        });
       
 
        	if("${LADetails}"!="" && "${LADetails}"!=null)
        	{
        		getSubCategorysList();
            }

        
        
        
        
        $('#payment_amount_units').val('1');
        $('#demanded_amount_units').val('1'); 
        $('#payment_amount_units_railway').val('1'); 
        $('#demanded_amount_units_forest').val('1');
        $('#payment_amount_units_forest').val('1');  
        $('#basic_rate_units').val('1');  
        $('#agriculture_tree_rate_units').val('1');  
        $('#forest_tree_rate_units').val('1');  
        $('#gross_amount_dpm_units').val('1'); 
        $('#jm_fee_amount_units').val('1');  
        $('#amount_demanded_units').val('1');  
        $('#amount_paid_units').val('1');         
        
        $('#payment_amount_units').prop('disabled', true);
        $('#demanded_amount_units').prop('disabled', true);  
        $('#payment_amount_units_railway').prop('disabled', true);  
        $('#demanded_amount_units_forest').prop('disabled', true);  
        $('#payment_amount_units_forest').prop('disabled', true);  
        $('#basic_rate_units').prop('disabled', true);  
        $('#agriculture_tree_rate_units').prop('disabled', true);  
        $('#forest_tree_rate_units').prop('disabled', true);  
        $('#gross_amount_dpm_units').prop('disabled', true);  
        $('#jm_fee_amount_units').prop('disabled', true);  
        $('#amount_demanded_units').prop('disabled', true);  
        $('#amount_paid_units').prop('disabled', true); 
        
        
       	   if("${LADetails.la_land_status_fk}"=='Acquired')
    	   {
    	   	$("#area_acquired").prop("disabled",true);
    	   	$('input[name=jm_approval][value=Done]').prop('checked', true);
    	   }
       	   
       	   
        
        
        
    });
	 $("[data-length]").each(function(i,val){
     	$('#'+val.id).characterCounter();;
     });
      /*   $(document).on('focus', '.datepicker', function () {
            $(this).datepicker({
                format: 'dd-mm-yyyy',
                onSelect: function () {
                    $('.confirmation-btns .datepicker-done').click();
                }
            })
        }); */
     	/* $(document).on('blur', 'input[type="number"]', function(){ 
			if($(this).val() <=0){
				$(this).val(Math.abs($(this).val()));
			}
        }); */
        
 	   $(document).on('focus', '.datepicker-min-today', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split(/[^0-9]/);
			    this.value = "";
			    var options = {
			    	minDate: new Date(),
			        format: 'dd-mm-yyyy',
			        autoClose: true,
			        onOpen: datePickerSelectAddClass,
			        showClearBtn: true,
			        onClose: function () {
			            if (!$(this.el).val()) {
			                $(this.el).siblings('label').removeClass('active');
			            }
			        }
			    };
			    if (dt.length > 1) {
			        options.setDefaultDate = true,
			        options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
			    }
			    M.Datepicker.init(this, options);		       
		 });
		 $(document).on('focus', '.datepicker-min-today-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 });
		 
		 function openAddIssue()
		 {
			 window.open("add-issue-form?la_id="+$("#la_id").val(),
	                    "_blank", "");
		 }
        
        function selectFile(no){
		    files = $("#laFiles"+no)[0].files;
		    var html = "";
		    for (var i = 0; i < files.length ; i++) {
		    	html =  html + '<div id=laFileNames'+no+'>'
				 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
				 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
				 + '</div>'
				 + '<div style="clear:both;"></div>';
		    }
		    $("#selectedFiles").append(html);
		    
		    $('#laFilesDiv'+no).hide();
		    
			var fileIndex = Number(no)+1;
			moreFiles(fileIndex);
		}
        
        function checkValues()
        {
        		if(parseFloat($('#chainage_from').val())>parseFloat($('#chainage_to').val()))
        		{
        			alert("Chainage To should be greater than Chainage From");
        		}
        }
        
        
        function getCoordinates()
        {

            var r1=$('#chainage_from').val();
            var r2=$('#chainage_to').val();
            
            if(r1!="" && r2!="")
            	{
		            var c1=(parseFloat(r1)+parseFloat(r2))/2; 
		            
		        	var myParams = { chainage_from: c1 };
		            $.ajax({
		                url: "<%=request.getContextPath()%>/ajax/getCoordinates",
		                data: myParams, cache: false,
		                success: function (data) {
		                    if (data.length > 0) 
		                    {
		                    	var splitChainage=data[0]["chainage_from"];
		                    	splitChainage=splitChainage.toString();
		                    	splitChainage=splitChainage.split(",");
		
		                    	var splitLatitude=data[0]["latitude"];
		                    	splitLatitude=splitLatitude.toString();
		                    	splitLatitude=splitLatitude.split(",");
		                    	
		                    	var splitLongitude=data[0]["longitude"];
		                    	splitLongitude=splitLongitude.toString();
		                    	splitLongitude=splitLongitude.split(",");                    	
		                    	
		                    	
		                        var a1= splitChainage[0];    var x1=splitLatitude[0]; var y1=splitLongitude[0];
		                        var b1=splitChainage[1];	 var x2=splitLatitude[1]; var y2=splitLongitude[1];
		
		                        var x3=0;   var y3=0;
		
		                        x3=parseFloat(x2)+parseFloat((((c1-b1)/(b1-a1))*(x2-x1)));
		                        y3=parseFloat(y2)+parseFloat((((c1-b1)/(b1-a1))*(y2-y1)));
		                        
		                        $('#idLatitude').html("");
		                        $('#idLongitude').html("");		                        
	                        
		                        $('#latitude').val(x3);
		                        $('#longitude').val(y3);
		                        
		                    }
		                }
		            });
            	}
        }       
        
        
	    function doValidate(value){
	    	
				if($("#la_land_status_fk").val()=="Acquired")
				{	    	
			 	   var valuetrim = value.trim();
			 	  $("#area_acquired").val(valuetrim);
				}

			if(parseFloat($("#area_acquired").val())>parseFloat($("#area_to_be_acquired").val()))
			{
				$("#area_to_be_acquiredError").html("Acquired Area (Ha) should be less than or equal to Area to be Acquired (Ha)");
				return false;
			}  
			else
			{
				$("#area_to_be_acquiredError").html("");
			}
		 	  
	    } 
	    
	    function doValidateAquired(value){

			if(parseFloat($("#area_acquired").val())>parseFloat($("#area_to_be_acquired").val()))
			{
				$("#area_to_be_acquiredError").html("Acquired Area (Ha) should be less than or equal to Area to be Acquired (Ha)");
				return false;
			}  
			else
			{
				$("#area_to_be_acquiredError").html("");
			}
		 	  
	    }  	    
		
		function moreFiles(no){
			var html = "";
			html =  html + '<div class="file-field input-field" id="laFilesDiv'+no+'" >'
			+ '<div class="btn bg-m t-c">'
			+ '<span>Attach Files</span>'
			+ '<input type="file" id="laFiles'+no+'" name="laFiles"  onchange="selectFile('+no+')">'
			+ '</div>'
			+ '<div class="file-path-wrapper">'
			+ '<input class="file-path validate" type="text">'
			+ '</div>'                          
			+ '</div>'
			$("#selectedFilesInput").append(html);
		}
		
		function checkLStatus(t)
		{
				if($(t).val()=="Acquired")
				{

					$('input[name=jm_approval][value=Done]').prop('checked', true);
					$("#area_acquired").val("");
					$("#area_acquired").val($("#area_to_be_acquired").val());
					$("#area_acquired").prop("disabled",true);
					
				}
				else
				{

					$('input[name=jm_approval][value=Done]').prop('checked', false); 
					$("#area_acquired").val(0);
					$("#area_acquired").prop("disabled",false);
				}
		}
		
		function removeFile(no){			
	     	$('#laFilesDiv'+no).remove();
	     	$('#laFileNames'+no).remove();
	    } 
      /*   let date_pickers = document.querySelectorAll('.datepicker');
	    $.each(date_pickers, function(){
	    	var dt = this.value.split(/[^0-9]/);
	    	this.value = ""; 
	    	var options = {format: 'dd-mm-yyyy',autoClose:true};
	    	if(dt.length > 1){
	    		options.setDefaultDate = true,
	    		options.defaultDate = new Date(dt[2], dt[1] - 1, dt[0])
	    	}
	    	M.Datepicker.init(this, options);
	    }); */

      /*   $(document).on('focus', '.datepicker-button', function () {
            var dateId = $(this).attr('id').split("__")[0];
            $('#' + dateId).datepicker({
            	 format: 'dd-mm-yyyy',
                 onSelect: function () {
                     $('.confirmation-btns .datepicker-done').click();
                 }
            });
            $('#' + dateId).datepicker('open');            
        }); */
	    
        $(document).ready(function () {
            $('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#issues,#jm_remarks,#remarks').characterCounter();
            // commented code placed next script tag from here
			forestGovernmentPrivateDivShowOrHide();
            $('input[name=is_there_issue]').change(function () {
                var radioval = $('input[name=is_there_issue]:checked').val();
                if (radioval == 'Yes') {
                    $('#issue_yes').css("display", "block");
                }
                else if (radioval == 'No') {
                    $('#issue_yes').css("display", "none");
                }
            });
            // call show/hide div function on approval change event
            $('input[name=jm_approval]').change(function () {
                forestGovernmentPrivateDivShowOrHide();
                $('select:not(.searchable)').formSelect();
            });
            // call show/hide div function on type of land change event
            $('#type_of_land').change(function () {
                forestGovernmentPrivateDivShowOrHide();
                $('select:not(.searchable)').formSelect();
            });
            // based on type of land show/hide div
            function forestGovernmentPrivateDivShowOrHide() {
                var jmapproval = $('input[name=jm_approval]:checked').val();
                if (jmapproval == 'Done') {
                    var landtype = $('#type_of_land').val();
                    if (landtype == 'Government') {
                        $('#govt_div').css("display", "block");
                        $('#forest_div').css("display", "none");
                        $('#private_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                    else if (landtype == 'Forest') {
                        $('#forest_div').css("display", "block");
                        $('#govt_div').css("display", "none");
                        $('#private_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                    else if (landtype == 'Private') {
                        $('#private_div').css("display", "block");
                        var private_type = '${LADetails.private_land_process}';
                        var action = '${action}';
                        if(action != 'add'){
                        	 if(private_type == 'Private IRA'){
                           	  $('#private_ira').css("display", "block");
                           }else{
                           	  $('#private_dpm').css("display", "block");
                           	  $('#private_land_process_dpm').prop('checked', true);
                           }
                        }
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                    else if (landtype == 'Railway') {
                        $('#private_div').css("display", "none");
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#railway_div').css("display", "block");
                    }
                    else {
                        $('#govt_div').css("display", "none");
                        $('#forest_div').css("display", "none");
                        $('#private_div').css("display", "none");
                        $('#railway_div').css("display", "none");
                    }
                }
                else if (jmapproval == 'Rejected') {
                    $('#govt_div').css("display", "none");
                    $('#forest_div').css("display", "none");
                    $('#private_div').css("display", "none");
                    $('#railway_div').css("display", "none");
                }
            }
          
        });
    
        $('input[type=radio][name=private_land_process]').change(function() {
		    if (this.value == 'Private IRA') {
		          $('#private_ira').show();
		          $('#private_dpm').hide();
		    }
		    else if (this.value == 'Private DPM') {
		          $('#private_ira').hide();
		          $('#private_dpm').show();
		    }
		});
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorkListForLAForm",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_short_name) != '') { workName = ' - ' + $.trim(val.work_short_name) }
                                var workId = "${LADetails.work_id_fk}";
                                if ($.trim(workId) != '' && val.work_id == $.trim(workId)) {
                                    $("#work_id_fk").append('<option name = "' + val.work_code + '" value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option name = "' + val.work_code + '" value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                }
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
        
        function resetProjectsDropdowns(workId){
        	var projectId = '';
        	if($.trim(workId) != ''){  
            	projectId = workId.substring(0, 3); 
       			$("#project_id_fk").val(projectId);
       			$("#project_id_fk").select2();
       			var work_code =   $('#work_id_fk option:selected').attr('name');
      			$("#work_code").val(work_code);
       		}
       		
        }
        function getLandsList() {
        	$(".page-loader").show();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(type_of_land) == "") {
            	$("#type_of_land option:not(:first)").remove();
            	var myParams = { sub_category_of_land: sub_category_of_land, type_of_land: type_of_land };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getLandsList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                              	 var type_of_land = "${LADetails.type_of_land}";
                              	 
      	                           if ($.trim(type_of_land) != '' && val.type_of_land == $.trim(type_of_land)) {
                                         $("#type_of_land").append('<option value="' + val.type_of_land + '" selected>' + $.trim(val.type_of_land) + '</option>');
                                     } else {
                                         $("#type_of_land").append('<option value="' + val.type_of_land + '">' + $.trim(val.type_of_land) + '</option>');
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
            }else{
            	  $(".page-loader").hide();
            }
        }
        
        function getSubCategorysList() {
        	$(".page-loader").show();
        	var type_of_land = $("#type_of_land").val();
        	var sub_category_of_land = $("#sub_category_of_land").val();
            if ($.trim(sub_category_of_land) == "") {
            	$("#sub_category_of_land option:not(:first)").remove();
            	var myParams = { sub_category_of_land: sub_category_of_land, type_of_land: type_of_land };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getSubCategorysList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                            	
                            	 var sub_category_of_land = "${LADetails.sub_category_of_land}";
                            	 
    	                           if ($.trim(sub_category_of_land) != '' && val.sub_category_of_land == $.trim(sub_category_of_land)) {
                                       $("#sub_category_of_land").append('<option value="' + val.id + '" selected>' + $.trim(val.sub_category_of_land) + '</option>');
                                   } else {
                                       $("#sub_category_of_land").append('<option value="' + val.id + '">' + $.trim(val.sub_category_of_land) + '</option>');
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
            }else{
            	  $(".page-loader").hide();
            }
        }
        
      //This function is used to get error message for all ajax calls
        function getErrorMessage(jqXHR, exception) {
        	    var msg = '';
        	    if (jqXHR.status === 0) {
        	        msg = 'Not connect.\n Verify Network.';
        	    } else if (jqXHR.status == 404) {
        	        msg = 'Requested page not found. [404]';
        	    } else if (jqXHR.status == 500) {
        	        msg = 'Internal Server Error [500].';
        	    } else if (exception === 'parsererror') {
        	        msg = 'Requested JSON parse failed.';
        	    } else if (exception === 'timeout') {
        	        msg = 'Time out error.';
        	    } else if (exception === 'abort') {
        	        msg = 'Ajax request aborted.';
        	    } else {
        	        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        	    }
        	    console.log(msg);
         }
        
        function addLAForm(){
        	if(validator.form()){ // validation perform
   	        	$(".page-loader").show();	   
   	        	var amount_demanded = $('#amount_demanded').val();
   	        	var govt_amount_paid = $('#govt_amount_paid').val();
   	        	var forest_demanded_amount = $('#forest_demanded_amount').val();
   	        	var forest_payment_amount = $('#forest_payment_amount').val();
   	        	var private_basic_rate = $('#private_basic_rate').val();
   	        	var private_agri_tree_rate = $('#private_agri_tree_rate').val();
   	        	var private_forest_tree_rate = $('#private_forest_tree_rate').val();
   	        	var private_payment_amount = $('#private_payment_amount').val();
   	        	var gross_amount_dpm = $('#gross_amount_dpm').val();
   	        	var railway_demanded_amount = $('#railway_demanded_amount').val();
   	        	var railway_payment_amount = $('#railway_payment_amount').val();
   	        	var jm_fee_amount = $('#jm_fee_amount').val();
   	        	
	  			if(jm_fee_amount == ""){$('#jm_fee_amount_units').val("");}
	  			if(amount_demanded == ""){$('#amount_demanded_units').val("");}
	  			if(govt_amount_paid == ""){$('#amount_paid_units').val("");}
	  			if(forest_demanded_amount == ""){$('#demanded_amount_units_forest').val("");}
	  			if(forest_payment_amount == ""){$('#payment_amount_units_forest').val("");}
	  			if(private_basic_rate == ""){$('#basic_rate_units').val("");}
	  			if(private_agri_tree_rate == ""){$('#agriculture_tree_rate_units').val("");}
	  			if(private_forest_tree_rate == ""){$('#forest_tree_rate_units').val("");}
	  			if(private_payment_amount == ""){$('#payment_amount_units').val("");}
	  			if(gross_amount_dpm == ""){$('#gross_amount_dpm_units').val("");}
	  			if(railway_demanded_amount == ""){$('#demanded_amount_units').val("");}
	  			if(railway_payment_amount == ""){$('#payment_amount_units_railway').val("");}
	  			var flag = validateLA();
	        	if(flag){
 					if($("#la_land_status_fk").val()=="Acquired")
					{
						/*if($("#required_area").val()=="")
						{
    						$("#required_areaError").html("required");
    						return false;
						}
    					else
    					{
    						$("#required_areaError").html();
    					}
						if($("#area_to_be_acquired").val()=="")
						{
    						$("#area_to_be_acquiredError").html("required");
    						return false;
						}
    					else
    					{
    						$("#area_to_be_acquiredError").html();
    					}*/
						$('input[name=jm_approval][value=Done]').prop('checked', true);
					} 

					if(parseFloat($("#area_acquired").val())>parseFloat($("#area_to_be_acquired").val()))
					{
						$("#area_to_be_acquiredError").html("Acquired Area (Ha) should be less than or equal to Area to be Acquired (Ha)");
						return false;
					}
					else
						{
							$("#area_to_be_acquiredError").html("");
						}
					$("#area_acquired").prop("disabled",false);
					if(checkSurveyNumber()==true)
					{
						
						$("#survey_numberError").html(" In 1 village there cant be duplicate survey numbers");
						return false;						
					}
					else
						{
							document.getElementById("landAcquisitionForm").submit();	
						}
	        			
    	 		}else{
    	        	$(".page-loader").hide();
    	 		}
        	}
        }
        
        function checkSurveyNumber()
        {

        	var bool = false;
           	 $.ajax({
                 url: "<%=request.getContextPath()%>/ajax/checkSurveyNumber",
                 data: {survey_number:$("#survey_number").val(),village_id:$("#village_id").val(),la_id:$("#la_id").val()},type: 'POST',
                 async: false,
                 dataType: 'json',
                 success: function (data) 
                 {
                	 if (data == true) {
                         bool = true;
                     }
                 }
             });
           	return trueOrFalse(bool);
        }	
        
        function trueOrFalse(bool){
            return bool;
    	}        
        
        function updateLAFrom(){
         	if(validator.form()){ // validation perform
    	        	$(".page-loader").show();	
    	        	var amount_demanded = $('#amount_demanded').val();
       	        	var govt_amount_paid = $('#govt_amount_paid').val();
       	        	var forest_demanded_amount = $('#forest_demanded_amount').val();
       	        	var forest_payment_amount = $('#forest_payment_amount').val();
       	        	var private_basic_rate = $('#private_basic_rate').val();
       	        	var private_agri_tree_rate = $('#private_agri_tree_rate').val();
       	        	var private_forest_tree_rate = $('#private_forest_tree_rate').val();
       	        	var private_payment_amount = $('#private_payment_amount').val();
       	        	var gross_amount_dpm = $('#gross_amount_dpm').val();
       	        	var railway_demanded_amount = $('#railway_demanded_amount').val();
       	        	var railway_payment_amount = $('#railway_payment_amount').val();
       	        	var jm_fee_amount = $('#jm_fee_amount').val();
       	        	
    	  			if(jm_fee_amount == ""){$('#jm_fee_amount_units').val("");}
    	  			if(amount_demanded == ""){$('#amount_demanded_units').val("");}
    	  			if(govt_amount_paid == ""){$('#amount_paid_units').val("");}
    	  			if(forest_demanded_amount == ""){$('#demanded_amount_units_forest').val("");}
    	  			if(forest_payment_amount == ""){$('#payment_amount_units_forest').val("");}
    	  			if(private_basic_rate == ""){$('#basic_rate_units').val("");}
    	  			if(private_agri_tree_rate == ""){$('#agriculture_tree_rate_units').val("");}
    	  			if(private_forest_tree_rate == ""){$('#forest_tree_rate_units').val("");}
    	  			if(private_payment_amount == ""){$('#payment_amount_units').val("");}
    	  			if(gross_amount_dpm == ""){$('#gross_amount_dpm_units').val("");}
    	  			if(railway_demanded_amount == ""){$('#demanded_amount_units').val("");}
    	  			if(railway_payment_amount == ""){$('#payment_amount_units_railway').val("");}
    	  			var flag = validateLA();
    	        	if(flag){
    	        		
     					if($("#la_land_status_fk").val()=="Acquired")
    					{
    						/*if($("#required_area").val()=="")
    						{
	    						$("#required_areaError").html("required");
	    						return false;
    						}
	    					else
	    					{
	    						$("#required_areaError").html();
	    					}
    						if($("#area_to_be_acquired").val()=="")
    						{
        						$("#area_to_be_acquiredError").html("required");
        						return false;
    						}
        					else
        					{
        						$("#area_to_be_acquiredError").html();
        					} */
       					
    						$('input[name=jm_approval][value=Done]').prop('checked', true);
    					} 
     					
    					if(parseFloat($("#area_acquired").val())>parseFloat($("#area_to_be_acquired").val()))
    					{
    						$("#area_to_be_acquiredError").html("Acquired Area (Ha) should be less than or equal to Area to be Acquired (Ha)");
    						return false;
    					}  
    					else
						{
							$("#area_to_be_acquiredError").html("");
						}    					
    					$("#area_acquired").prop("disabled",false);
    					if(checkSurveyNumber()==true)
    					{
    						
    						$("#survey_numberError").html(" In 1 village there cant be duplicate survey numbers");
    						return false;						
    					}
    					else
    						{
    							document.getElementById("landAcquisitionForm").submit();	
    						}
        	 		}else{
        	        	$(".page-loader").hide();
        	 		}
         	}
         }
        
        var validator =	$('#landAcquisitionForm').validate({
			 ignore: ":hidden:not(.validate-dropdown)",
	  		    rules: {
	  		 		  "project_id_fk": {
	  			 		required: true
	  			 	  },"work_id_fk": {
	  			 		required: true
	  			 	  },"la_land_status_fk": {
	  			 		required: true
	  			 	  },"survey_number": {
	  			 		required: false
	  			 	  },"la_id": {
	  			 		required: true
	  			 	  },"type_of_land": {
	  			 		required: true
	  			 	  },"id": {
	  			 		required: true
	  			 	  },"jm_fee_amount":{
		  			 	required: false
	  			 	  },"chainage_from":{
		  			 	required: true
	  			 	  },"chainage_to":{
		  			 	required: true
	  			 	  },"area_acquired":{
		  			 	required: true
	  			 	  },"area_to_be_acquired":{
		  			 	required: true
	  			 	  },"village":{
		  			 	required: true
	  			 	  },"taluka":{
		  			 	required: true
	  			 	  },"govt_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"forest_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"forest_area_acquired":{
		  			 	required: false
	  			 	  },"private_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"private_area_acquired":{
		  			 	required: false
	  			 	  },"railway_area_to_be_acquired":{
		  			 	required: false
	  			 	  },"railway_area_acquired":{
		  			 	required: false
	  			 	  },"railway_demanded_amount":{
		  			 	required: false
	  			 	  },"railway_payment_amount":{
		  			 	required: false
	  			 	  },"private_basic_rate":{
		  			 	required: false
	  			 	  },"private_agri_tree_rate":{
		  			 	required: false
	  			 	  },"private_forest_tree_rate":{
		  			 	required: false
	  			 	  },"private_payment_amount":{
		  			 	required: false
	  			 	  },"gross_amount_dpm":{
		  			 	required: false
	  			 	  },"railway_demanded_amount":{
		  			 	required: false
	  			 	  },"railway_payment_amount":{
		  			 	required: false
	  			 	  },"forest_demanded_amount":{
		  			 	required: false
	  			 	  },"forest_payment_amount":{
		  			 	required: false
	  			 	  },"amount_demanded":{
		  			 	required: false
	  			 	  },"govt_amount_paid":{
		  			 	required: false
	  			 	  },"jm_fee_amount_units":{
        		 		 required: function(element){
        		             return $("#jm_fee_amount").val()!="";
        		         }
        		 	  },"amount_demanded_units":{
        		 		 required: function(element){
        		             return $("#amount_demanded").val()!="";
        		         }
        		 	  },"amount_paid_units":{
        		 		 required: function(element){
        		             return $("#govt_amount_paid ").val()!="";
        		         }
        		 	  },"basic_rate_units":{
        		 		 required: function(element){
        		             return $("#private_basic_rate").val()!="";
        		         }
        		 	  },"agriculture_tree_rate_units":{
        		 		 required: function(element){
        		             return $("#private_agri_tree_rate").val()!="";
        		         }
        		 	  },"forest_tree_rate_units":{
        		 		 required: function(element){
        		             return $("#private_forest_tree_rate").val()!="";
        		         }
        		 	  },"demanded_amount_units_forest":{
        		 		 required: function(element){
        		             return $("#forest_demanded_amount").val()!="";
        		         }
        		 	  },"payment_amount_units_forest":{
        		 		 required: function(element){
        		             return $("#forest_payment_amount").val()!="";
        		         }
        		 	  },"payment_amount_units_railway":{
        		 		 required: function(element){
        		             return $("#railway_payment_amount").val()!="";
        		         }
        		 	  },"demanded_amount_units":{
        		 		 required: function(element){
        		             return $("#railway_demanded_amount").val()!="";
        		         }
        		 	  },"payment_amount_units":{
        		 		 required: function(element){
        		             return $("#private_payment_amount").val()!="";
        		         }
        		 	  },"gross_amount_dpm_units":{
        		 		 required: function(element){
        		             return $("#gross_amount_dpm").val()!="";
        		         }
        		 	  }
	  		 	},
	  		    messages: {
	  		 		 "project_id_fk": {
	  				 	required: 'This field is required',
	  			 	  },"work_id_fk": {
	  			 		required: ' This field is required'
	  			 	  },"la_land_status_fk": {
	  			 		required: ' This field is required'
	  			 	  },"survey_number": {
	  			 		required: ' This field is required'
	  			 	  },"la_id": {
	  			 		required: ' This field is required'
	  			 	  },"area_acquired":{
		  			 	required: ' This field is required'
	  			 	  },"area_to_be_acquired":{
		  			 	required: ' This field is required'
	  			 	  },"type_of_land": {
	  			 		required: ' This field is required'
	  			 	  },"id": {
	  			 		required: ' This field is required'
	  			 	  },"village":{
		  			 	required: ' This field is required'
	  			 	  },"taluka":{
		  			 	required: ' This field is required'
	  			 	  },"jm_fee_amount_units": {
	  			 		required: ' Rrequired'
	  			 	  },"chainage_from":{
		  			 	required: 'This field is required'
	  			 	  },"chainage_to":{
		  			 	required: 'This field is required'
	  			 	  },
	  			 	  "demanded_amount_units": {
	  			 		required: ' Rrequired'
	  			 	  },"payment_amount_units": {
	  			 		required: ' Rrequired'
	  			 	  },"amount_demanded_units": {
	  			 		required: ' Rrequired'
	  			 	  },"amount_paid_units": {
	  			 		required: ' Rrequired'
	  			 	  },"payment_amount_units_forest": {
	  			 		required: ' Rrequired'
	  			 	  },"basic_rate_units": {
	  			 		required: ' Rrequired'
	  			 	  },"agriculture_tree_rate_units": {
	  			 		required: ' Rrequired'
	  			 	  },"forest_tree_rate_units": {
	  			 		required: ' Rrequired'
	  			 	  },"demanded_amount_units_forest": {
	  			 		required: ' Rrequired'
	  			 	  },"payment_amount_units_railway": {
	  			 		required: ' Rrequired'
	  			 	  }
		   		},
		   		errorPlacement:function(error, element){
		   		 	if (element.attr("id") == "project_id_fk" ){
						 document.getElementById("project_id_fkError").innerHTML="";
				 		 error.appendTo('#project_id_fkError');
					}else if(element.attr("id") == "work_id_fk" ){
					   document.getElementById("work_id_fkError").innerHTML="";
				 	   error.appendTo('#work_id_fkError');
					}else if(element.attr("id") == "la_land_status_fk" ){
					   document.getElementById("la_land_status_fkError").innerHTML="";
				 	   error.appendTo('#la_land_status_fkError');
					}else if(element.attr("id") == "survey_number" ){
					   document.getElementById("survey_numberError").innerHTML="";
				 	   error.appendTo('#survey_numberError');
					}else if(element.attr("id") == "la_id" ){
						   document.getElementById("la_idError").innerHTML="";
					 	   error.appendTo('#la_idError');
					}else if(element.attr("id") == "area_acquired" ){
						   document.getElementById("area_acquiredError").innerHTML="";
					 	   error.appendTo('#area_acquiredError');
					}else if(element.attr("id") == "area_to_be_acquired" ){
						   document.getElementById("area_to_be_acquiredError").innerHTML="";
					 	   error.appendTo('#area_to_be_acquiredError');
					}else if(element.attr("id") == "type_of_land" ){
						   document.getElementById("type_of_landError").innerHTML="";
					 	   error.appendTo('#type_of_landError');
					}else if(element.attr("id") == "village" ){
						   document.getElementById("villageError").innerHTML="";
					 	   error.appendTo('#villageError');
					}else if(element.attr("id") == "taluka" ){
						   document.getElementById("talukaError").innerHTML="";
					 	   error.appendTo('#talukaError');
					}else if(element.attr("id") == "chainage_from" ){
						   document.getElementById("chainage_fromError").innerHTML="";
					 	   error.appendTo('#chainage_fromError');
					}else if(element.attr("id") == "chainage_to" ){
						   document.getElementById("chainage_toError").innerHTML="";
					 	   error.appendTo('#chainage_toError');
					}else if(element.attr("id") == "sub_category_of_land" ){
						   document.getElementById("sub_category_of_landError").innerHTML="";
					 	   error.appendTo('#sub_category_of_landError');
					}else if(element.attr("id") == "govt_area_acquired" ){
						   document.getElementById("govt_area_acquiredError").innerHTML="";
					 	   error.appendTo('#govt_area_acquiredError');
				    }else if(element.attr("id") == "govt_area_to_be_acquired" ){
						   document.getElementById("govt_area_to_be_acquiredError").innerHTML="";
					 	   error.appendTo('#govt_area_to_be_acquiredError');
				   }else if(element.attr("id") == "forest_area_to_be_acquired" ){
					   document.getElementById("forest_area_to_be_acquiredError").innerHTML="";
				 	   error.appendTo('#forest_area_to_be_acquiredError');
			   	   }else if(element.attr("id") == "forest_area_acquired" ){
					   document.getElementById("forest_area_acquiredError").innerHTML="";
				 	   error.appendTo('#forest_area_acquiredError');
			       }else if(element.attr("id") == "private_area_to_be_acquired" ){
					   document.getElementById("private_area_to_be_acquiredError").innerHTML="";
				 	   error.appendTo('#private_area_to_be_acquiredError');
			   	   }else if(element.attr("id") == "private_area_acquired" ){
					   document.getElementById("private_area_acquiredError").innerHTML="";
				 	   error.appendTo('#private_area_acquiredError');
			       }else if(element.attr("id") == "railway_area_to_be_acquired" ){
					   document.getElementById("railway_area_to_be_acquiredError").innerHTML="";
				 	   error.appendTo('#railway_area_to_be_acquiredError');
			   	   }else if(element.attr("id") == "railway_area_acquired" ){
					   document.getElementById("railway_area_acquiredError").innerHTML="";
				 	   error.appendTo('#railway_area_acquiredError');
			       }else if(element.attr("id") == "railway_demanded_amount" ){
					   document.getElementById("railway_demanded_amountError").innerHTML="";
				 	   error.appendTo('#railway_demanded_amountError');
			   	   }else if(element.attr("id") == "railway_payment_amount" ){
					   document.getElementById("railway_payment_amountError").innerHTML="";
				 	   error.appendTo('#railway_payment_amountError');
			       }else if(element.attr("id") == "private_basic_rate" ){
					   document.getElementById("private_basic_rateError").innerHTML="";
				 	   error.appendTo('#private_basic_rateError');
			       }else if(element.attr("id") == "private_agri_tree_rate" ){
					   document.getElementById("private_agri_tree_rateError").innerHTML="";
				 	   error.appendTo('#private_agri_tree_rateError');
			       }else if(element.attr("id") == "private_forest_tree_rate" ){
					   document.getElementById("private_forest_tree_rateError").innerHTML="";
				 	   error.appendTo('#private_forest_tree_rateError');
			       }else if(element.attr("id") == "private_payment_amount" ){
					   document.getElementById("private_payment_amountError").innerHTML="";
				 	   error.appendTo('#private_payment_amountError');
			       }else if(element.attr("id") == "gross_amount_dpm" ){
					   document.getElementById("gross_amount_dpmError").innerHTML="";
				 	   error.appendTo('#gross_amount_dpmError');
			       }else if(element.attr("id") == "forest_demanded_amount" ){
					   document.getElementById("forest_demanded_amountError").innerHTML="";
				 	   error.appendTo('#forest_demanded_amountError');
			       }else if(element.attr("id") == "forest_payment_amount" ){
					   document.getElementById("forest_payment_amountError").innerHTML="";
				 	   error.appendTo('#forest_payment_amountError');
			       }else if(element.attr("id") == "amount_demanded" ){
					   document.getElementById("amount_demandedError").innerHTML="";
				 	   error.appendTo('#amount_demandedError');
			       }else if(element.attr("id") == "govt_amount_paid" ){
					   document.getElementById("govt_amount_paidError").innerHTML="";
				 	   error.appendTo('#govt_amount_paidError');
			       }else if(element.attr("id") == "jm_fee_amount" ){
					   document.getElementById("jm_fee_amountError").innerHTML="";
				 	   error.appendTo('#jm_fee_amountError');
			       } else if (element.attr("id") == "jm_fee_amount_units") {
			           document.getElementById("jm_fee_amount_unitsError").innerHTML = "";
			           error.appendTo('#jm_fee_amount_unitsError');
			       } else if (element.attr("id") == "basic_rate_units") {
			           document.getElementById("basic_rate_unitsError").innerHTML = "";
			           error.appendTo('#basic_rate_unitsError');
			       }else if (element.attr("id") == "agriculture_tree_rate_units") {
			           document.getElementById("agriculture_tree_rate_unitsError").innerHTML = "";
			           error.appendTo('#agriculture_tree_rate_unitsError');
			       }else if (element.attr("id") == "forest_tree_rate_units") {
			           document.getElementById("forest_tree_rate_unitsError").innerHTML = "";
			           error.appendTo('#forest_tree_rate_unitsError');
			       }else if (element.attr("id") == "payment_amount_units") {
			           document.getElementById("payment_amount_unitsError").innerHTML = "";
			           error.appendTo('#payment_amount_unitsError');
			       }else if (element.attr("id") == "gross_amount_dpm_units") {
			           document.getElementById("gross_amount_dpm_unitsError").innerHTML = "";
			           error.appendTo('#gross_amount_dpm_unitsError');
			       }else if (element.attr("id") == "demanded_amount_units_forest") {
			           document.getElementById("forest_demanded_amount_unitsError").innerHTML = "";
			           error.appendTo('#forest_demanded_amount_unitsError');
			       }else if (element.attr("id") == "payment_amount_units_forest") {
			           document.getElementById("forest_payment_amount_unitsError").innerHTML = "";
			           error.appendTo('#forest_payment_amount_unitsError');
			       }else if (element.attr("id") == "amount_demanded_units") {
			           document.getElementById("amount_demanded_unitsError").innerHTML = "";
			           error.appendTo('#amount_demanded_unitsError');
			       }else if (element.attr("id") == "amount_paid_units") {
			           document.getElementById("amount_paid_unitsError").innerHTML = "";
			           error.appendTo('#amount_paid_unitsError');
			       }else if (element.attr("id") == "demanded_amount_units") {
			           document.getElementById("railway_demanded_amount_unitsError").innerHTML = "";
			           error.appendTo('#railway_demanded_amount_unitsError');
			       }else if (element.attr("id") == "payment_amount_units_railway") {
			           document.getElementById("railway_payment_amount_unitsError").innerHTML = "";
			           error.appendTo('#railway_payment_amount_unitsError');
			       } else{
 					error.insertAfter(element);
			        } 
		   		},invalidHandler: function (form, validator) {
                  var errors = validator.numberOfInvalids();
                  if (errors) {
                      var position = validator.errorList[0].element;
                      jQuery('html, body').animate({
                          scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                      }, 1000);
                  }validateLA();
              },submitHandler:function(form){
			    	form.submit();
			    }
			});   
		 
		 $.validator.addMethod("dateFormat",
	        	    function(value, element) {
	        	        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[-](0?[1-9]|1[0-2])[-](19|20)?\d{2}$/);
	        	        //var dtRegex = new RegExp("^(JAN|FEB|MAR|APR|MAY|JUN|JULY|AUG|SEP|OCT|NOV|DEC) ([0]?[1-9]|[1-2]\\d|3[0-1]), [1-2]\\d{3}$", 'i');
	        	    	//return dtRegex.test(value);
	        	    },
	        	    //"Date format (Aug 02,2020)"
	        	    "Date format (DD-MM-YYYY)"
	        	);
	            
	            
	            $('select').change(function(){
	        	    if ($(this).val() != ""){
	        	        $(this).valid();
	        	    }
	        	});
	            
	            $('select').keyup(function(){
	        	    if ($(this).val() != ""){
	        	        $(this).valid();
	        	    }
	        	});
	            
	            $('input').change(function(){
	        	    if ($(this).val() != ""){
	        	        $(this).valid();
	        	    }
	        	});
	            // radio select tab
	            $('input[type=radio]').click(function(){
	                $('.area').hide();
	                $('#' + $(this).val()).show(); 
	            });
	            
	            //add document
	            
	             function addDesignDocumentRow(){		
					 var rowNo = $("#documentRowNo").val();
					 var rNo = Number(rowNo)+1;
					 var total = 0;
					 var html = '<tr id="laDocumentRow'+rNo+'">'
								 +'<td data-head="File Type " class="input-field">'
										+'<select  name="la_file_typess"  id="la_file_types'+rNo+'"  class="validate-dropdown searchable">'
					    					+ '<option value="" >Select</option>'
					          			  <c:forEach var="obj" items="${laFileType}">
								  				+ '<option value="${obj.la_file_type }">${obj.la_file_type}</option>'
					           			  </c:forEach>
									+ '</select><span id="la_file_typess'+rNo+'Error" class="error-msg" ></span></td>'
								 +'<td data-head="Name " class="input-field"> <input id="laDocumentNames'+rNo+'" maxlength="100" data-length="100" name="laDocumentNames" type="text" class="validate w75 pdr4em" placeholder="Name"> <span id="laDocumentNames'+rNo+'Error" class="error-msg" ></span></td>'
								 +'<td data-head="Attachment" class="input-field center-align">'
								 +'<span class="normal-btn">'
								 +'<input type="file" id="laFiles'+rNo+'" name="laFiles" style="display:none" onchange="getFileName('+rNo+')" />'
								 +'<label for="laFiles'+rNo+'" class="btn bg-m"><i class="fa fa-paperclip"></i></label>'
								 +'<input type="hidden" id="laDocumentFileNames'+rNo+'" name="laDocumentFileNames">'
								 +'<span id="laDocumentFileName'+rNo+'" class="filevalue"></span>'
								 +'</span><span id="laFiles'+rNo+'Error" class="error-msg" ></span>'
								 +'</td>'
								 +'<td><input type="hidden" id="la_file_ids'+rNo+'" name="la_file_ids"/></td>';
								 
								 var user_role_name = '${sessionScope.USER_ROLE_NAME}';
								 if(user_role_name == 'IT Admin'){
									 html = html +'<td class="mobile_btn_close">'
									 +'<a href="javascript:void(0);" onclick="removeDesignDocument('+rNo+');" class="btn waves-effect waves-light red t-c "> <i class="fa fa-close"></i></a>'
									 +'</td>';
								 }
								 html = html +'</tr>';
					
						 $('#laDocumentTableBody').append(html);
						 $("#documentRowNo").val(rNo);
						 $('.searchable').select2();
						 $('#laDocumentNames'+rNo).characterCounter();;
				         $("#la_file_ids0").val('');
				         $('select[name=la_file_typess]').change(function(key, element){
								$("select[name=la_file_typess]").each(function(){
									var idNo = (this.id).replace('la_file_types',''); 
					        		if($.trim(this.value) != ""){ 
					        			$('#la_file_typess'+idNo+'Error').text('');
									}
					            });
								
							});
					        $('[name=laFiles]').change(function(key, element){
								$("[name=laFiles]").each(function(){
									var idNo = (this.id).replace('laFiles',''); 
									var doc = $('#laDocumentFileNames'+idNo).val();
									if($.trim(doc) != ""){  
					        			$('#laFiles'+idNo+'Error').text('');
									}else if($.trim(doc) == "" && $('#laFiles'+idNo).val() != ""){
						 				$('#laFiles'+idNo+'Error').text('');
						 			}
					            });
								
							});
					        $('[name=laDocumentNames]').keyup(function(key, element){
					 			$("[name=laDocumentNames]").each(function(){
					 			var idNo = (this.id).replace('laDocumentNames',''); 
					 			if($.trim(this.value) != "" ){ 
					       			$('#laDocumentNames'+idNo+'Error').text('');
					 			}
					           });
					 		});
				} 
				function removeDesignDocument(rowNo){
					$("#laDocumentRow"+rowNo).remove();
				}
				function getFileName(rowNo){
		    		var filename = $('#laFiles'+rowNo)[0].files[0].name;
		    	    $('#laDocumentFileName'+rowNo).html(filename);
		    	}
				
		        function validateLA(){
					var flag = true;
					$("select[name=la_file_typess]").each(function(){
						var idNo = (this.id).replace('la_file_types','');
						var la_file_type = $("#la_file_types"+idNo).val();
						var name = $("#laDocumentNames"+idNo).val();
						var file = $("#laFiles"+idNo).val();
						var doc = $('#laDocumentFileNames'+idNo).val();
			       		if($.trim(la_file_type) == "" &&  name == "" &&  file == ""){
			       			flag = true;
						}else{
							if(la_file_type == ""){
								$('#la_file_typess'+idNo+'Error').text('Requried');
								$('#la_file_types'+idNo).slideDown(100,function(){
									$(this).focus();
								});
							}
							if(name == ""){
								$('#laDocumentNames'+idNo+'Error').text('Requried');
								$('#laDocumentNames'+idNo).slideDown(100,function(){
									$(this).focus();
								});
							}
							if(doc == "" && file == ""){
								$('#laFiles'+idNo+'Error').text('Requried');
								$('#laFiles'+idNo+'Error').slideDown(100,function(){
									$(this).focus();
								});
							}
							
							flag = false;
							}
			       		a = [];
	     				$('#laDocumentTableBody .error-msg').each(function(){a.push(this.innerHTML)});
	     				console.log(a)
	     				var found = a.includes('Requried');
	     				if (!found){
	     					flag = true;
	     				}else{
	     					flag = false;
	     				}
						});
					return flag;
					} 
		        
		        $('select[name=la_file_typess]').change(function(key, element){
					$("select[name=la_file_typess]").each(function(){
						var idNo = (this.id).replace('la_file_types',''); 
		        		if($.trim(this.value) != ""){ 
		        			$('#la_file_typess'+idNo+'Error').text('');
						}
		            });
					
				});
		        $('[name=laFiles]').change(function(key, element){
					$("[name=laFiles]").each(function(){
						var idNo = (this.id).replace('laFiles',''); 
						var doc = $('#laDocumentFileNames'+idNo).val();
						if($.trim(doc) != ""){  
		        			$('#laFiles'+idNo+'Error').text('');
						}else if($.trim(doc) == "" && $('#laFiles'+idNo).val() != ""){
			 				$('#laFiles'+idNo+'Error').text('');
			 			}
		            });
					
				});
		        $('[name=laDocumentNames]').keyup(function(key, element){
		 			$("[name=laDocumentNames]").each(function(){
		 			var idNo = (this.id).replace('laDocumentNames',''); 
		 			if($.trim(this.value) != "" ){ 
		       			$('#laDocumentNames'+idNo+'Error').text('');
		 			}
		           });
		 		});
    </script>
   
</body>

</html>