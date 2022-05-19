<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants2"%>
<%@page import="com.synergizglobal.pmis.constants.CommonConstants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Safety Incident - Update Forms - PMIS</title>
	<link rel="icon" type="image/png" sizes="96x96"	href="/pmis/resources/images/favicon.png">
	<link rel="stylesheet" href="/pmis/resources/css/materialize-v.1.0.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/select2.min.css">	 
	<link rel="stylesheet" href="/pmis/resources/css/rits.css">
	<link rel="stylesheet" href="/pmis/resources/css/searchable-dropdown.css">	
	<link rel="stylesheet" media="screen and (max-device-width: 768px)" href="/pmis/resources/css/mobile-form-template.css" >
	 <style>
        .no-mar .row {
            margin-bottom: 0;
        }
        .hidden{
        }
         .input-field .searchable_label{
        	font-size:0.85rem;
        } 
        .mti-5 p{
        	margin-top:5px;
        }       
		.error-msg label{color:red!important;}
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
		.center-align.m-1 button.bg-m.waves-light, 
		.center-align.m-1 button.bg-s.waves-light{
			width:inherit;
		}
		.col.input-field>textarea+label:not(.label-icon).active{
			margin-top:0;
		}		
		.input-field .searchable_label {
		    margin-top: -12px !important;
		}
		.input-field >textarea.materialize-textarea{
			margin-bottom:2px;
		}
	  
	    #compensation_unitsError{
	   		float:right;	
	    }
	    @media(max-width: 575px){
	     .row .col{margin: 10px auto;}
	     .amount-dropdown input:not(.select-dropdown) {
			  padding-left: 2rem;
			  box-sizing: border-box;
			}
			
			.amount-dropdown input:not(.select-dropdown)+label:not(.active) {
			  padding-left: 2rem;
			}
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
                                <h6>Update Safety Incident</h6>
                            </div>
                        </span>
                    </div>
                    <!-- form start-->
                    <div class="container container-no-margin">
                        <form action="<%=request.getContextPath() %>/update-safety" id="safetyForm" name="safetyForm" method="post" enctype="multipart/form-data">
                        
                         	<input name="existing_status_fk" type="hidden" value="${safety.status_fk }"/>
                        	<input name="existing_responsible_person" type="hidden" value="${safety.responsible_person }"/>
                        	<input name="hod_user_id_fk" type="hidden" value="${safety.hod_user_id_fk }" />
                        	<input name="dy_hod_user_id_fk" type="hidden" value="${safety.dy_hod_user_id_fk }" />        
                        	<input name="existing_committe_members" type="hidden" value="${safety.committe_members }" />                
                        
                        	<div class="row">
                                <div class="col s6 m4 l4 input-field">
                                    <input type="text"  value ="${safety.project_id_fk } - ${safety.project_name}" readonly id="project-text"/>
                                    <label for="project-text">Project <span class="required">*</span></label>
                                    <input type="hidden" name="project_id_fk" id="project_id_fk" value ="${safety.project_id_fk }" />
                                    <span id="project_id_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input type="text"  value ="${safety.work_id_fk } - ${safety.work_short_name}" readonly id="work-text"/>
                                    <label for="work-text">Work <span class="required">*</span></label>
                                    <input type="hidden" name="work_id_fk" id="work_id_fk" value ="${safety.work_id_fk }" />
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <!-- <select id="contract_id_fk" name="contract_id_fk" class="searchable validate-dropdown">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="contract_id_fkError" class="error-msg" ></span> -->
                                    <input type="text"  value ="${safety.contract_id_fk } - ${safety.contract_short_name}" readonly id="contract-text"/>
                                    <label for="contract-text">Contract <span class="required">*</span></label>
                                    <input type="hidden" name="contract_id_fk" id="contract_id_fk" value ="${safety.contract_id_fk }" />
                                </div>
                            </div>

                            <div class="row">
                            <input id="safety_id" name="safety_id" type="hidden" value="${safety.safety_id }" />                                
                            </div>
                            <div class="row">
                            <div class="col s6 m4 l4 input-field">
                                   <p class="searchable_label"> Category <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="category_fk" name="category_fk" onchange="setTitle(this.value);">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyCategoryList }">
                                            <option value="${obj.category }" <c:if test="${safety.category_fk eq obj.category}">selected</c:if>>${obj.category}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="category_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                <p class="searchable_label"> Impact <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="impact_fk" name="impact_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyImpactList }">
                                            <option value="${obj.impact }" <c:if test="${safety.impact_fk eq obj.impact}">selected</c:if>>${obj.impact}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="impact_fkError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">    
                                <p class="searchable_label"> Root Cause <span class="required">*</span></p>                            
                                    <select class="searchable validate-dropdown" id="root_cause_fk" name="root_cause_fk">
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyRootCauseList }">
                                            <option value="${obj.root_cause }" <c:if test="${safety.root_cause_fk eq obj.root_cause}">selected</c:if>>${obj.root_cause}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="root_cause_fkError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">                                   
                                      <textarea id="title" name="title" class="pmis-textarea validate" data-length="100">${safety.title }</textarea>
	                                  <label for="title">Short Description <span class="required">*</span></label>
	                                  <span id="titleError" class="error-msg" ></span>
                                 </div>
                            </div>
                            <div class="row">
                                  <div class="col s12 m12 l12 input-field">
									  <textarea id="description" name="description" class="validate pmis-textarea" data-length="1000">${safety.description }</textarea>                                            
	                                  <label for="description">Full Description<span class="required">*</span></label>
	                                  <span id="descriptionError" class="error-msg" ></span>
                                  </div>                                    
                            </div>

                            <div class="row ">
                                <div class="col s6 m4 l4 input-field">
                                    <input id="date" name="date" type="text" class="validate datepicker" value="${safety.date }">
                                    <label for="date"> Date of Incident <span class="required">*</span></label>
                                    <button type="button" id="date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input id="location" name="location" type="text" class="validate" value="${safety.location }">
                                    <label for="location">Location/Station/KM<span class="required">*</span></label>
                                    <span id="locationError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <%-- <input id="responsible_person" name="responsible_person" type="text" class="validate" value="${safety.responsible_person }">
                                    <label for="responsible_person" class="fs-sm-67rem serchable_label">Person Responsible in MRVC</label> --%>
                                    <p style="color: #aaa;font-size:0.85rem;" >Person Responsible in MRVC</p>
                                    <select id="responsible_person" name="responsible_person" class="searchable">
                                        <option value="">Select</option>
                                    </select>
                                    <span id="responsible_personError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">                                
                                <div class="col s6 m4 l4 input-field">
                                    <input id="latitude" name="latitude" type="text" class="validate" value="${safety.latitude }">
                                    <label for="latitude">Latitude </label>
                                    <span id="latitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m4 l4 input-field">
                                    <input id="longitude" name="longitude" type="text" class="validate" value="${safety.longitude }">
                                    <label for="longitude">Longitude </label>
                                    <span id="longitudeError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m4 l4 input-field">
                                    <input id="reported_by" name="reported_by" type="text" class="validate" value="${safety.reported_by }">
                                    <label for="reported_by">Reported By</label>
                                    <span id="reported_byError" class="error-msg" ></span>
                                </div>
                            </div>

                            
                            <div class="row" style="margin-bottom:10px;">                               
                                <div class="col s6 m4 l4 input-field mti-5">
	                                 <p>
									      <label>
									        <input type="checkbox" id="committee_required" name="committee_required" <c:if test="${safety.committee_required_fk eq 'Yes'}">checked</c:if>/>
									        <span>Committee Required</span>
									      </label>
									      <input type="hidden" id="committee_required_fk" name="committee_required_fk" value="No"/>
								    </p>
							    </div>
							    <div class="col s6 m4 l4 hidden input-field mti-5" id="committee_formed_div" >
	                                 <p>
									      <label>
									        <input type="checkbox" id="committee_formed" name="committee_formed" <c:if test="${safety.committee_formed_fk eq 'Yes'}">checked</c:if>/>
									        <span>Committee Formed</span>
									      </label>
									      
								    </p>
								    <input type="hidden" id="committee_formed_fk" name="committee_formed_fk" value="No"/>
							    </div>
                                <div class="col s12 m4 l4 input-field hidden" id="committee_member_div">                                 	
                                   <%--  <input id="committee_member_name" name="committee_member_name" type="text" class="validate" value="${safety.committee_member_name }">
                                    <label for="committee_member_name">Name of Committee member</label> --%>
                                     <p style="color: #aaa;font-size:0.85rem;" >Name of Committee members</p>
                                    <select id="committee_member_name" name="committee_member_names" class="searchable validate-dropdown" multiple="multiple">
                                   <option value="" disabled="disabled">Select</option>
                                   <c:forEach var="obj" items="${usersList}">
           					  			 <option value="${obj.user_id }"            					  			 
           					  			 		<c:forEach var="tempobj" items="${safety.safetyCommitteeMembersList}">
										 			<c:if test="${tempobj.committee_member_name eq obj.user_id}">selected</c:if>
	                                          	</c:forEach>           					  			 
           					  			 > ${obj.designation} - ${obj.user_name}</option>
                                   </c:forEach>
                                    </select>
                                    <span id="committee_member_nameError" class="error-msg" ></span> 
                                </div>  
                            </div>
                            <div class="row">                             
                                 <div class="col s12 m6 l6 input-field">
                                    <input id="investigation_completed" name="investigation_completed" type="text" class="validate datepicker" value="${safety.investigation_completed }">
                                    <label for="investigation_completed">Investigation Completion Date</label>
                                    <button type="button" id="investigation_completed_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="investigation_completedError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6 l6 input-field">
                                    <input id="lti_hours" name="lti_hours" type="number" class="validate" value="${safety.lti_hours }">
                                    <label for="lti_hours"> LTI  in hours</label>
                                    <span id="lti_hoursError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6 m6 l6 input-field">
                                    <input id="equipment_impact" name="equipment_impact" type="text" class="validate charCount" value="${safety.equipment_impact }" data-length="100">
                                    <label for="equipment_impact"> Equipment Impact </label>
                                    <span id="equipment_impactError" class="error-msg" ></span>
                                </div>
                                <div class="col s6 m6 l6 input-field">
                                    <input id="people_impact" name="people_impact" type="text" class="validate charCount" value="${safety.people_impact }" data-length="100">
                                    <label for="people_impact">People Impact</label>
                                    <span id="people_impactError" class="error-msg" ></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                                    <input id="work_impact" name="work_impact" type="text" class="validate charCount" value="${safety.work_impact }" data-length="100">
                                    <label for="work_impact"> Work Impact </label>
                                    <span id="work_impactError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                                    <textarea id="corrective_measure_short_term" name="corrective_measure_short_term" class="pmis-textarea validate" data-length="1000">${safety.corrective_measure_short_term }</textarea>  
                                    <label for="corrective_measure_short_term">Corrective Measure (Short Term) </label>
                                    <span id="corrective_measure_short_termError" class="error-msg" ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                               		<textarea id="corrective_measure_long_term" name="corrective_measure_long_term" class="pmis-textarea validate" data-length="1000">${safety.corrective_measure_long_term }</textarea>  
                                    <label for="corrective_measure_long_term">Corrective Measure (Long Term) </label>
                                    <span id="corrective_measure_long_termError" class="error-msg" ></span>
                                </div>
                            </div>                          

                            <div class="row">
                            	<div class="col s12 m12 l12 input-field">
									<c:set var="existingsafetyFilesLength" value="${fn:length(safety.safetyFilesList )}"></c:set>
									<c:if test="${fn:length(safety.safetyFilesList ) gt 0}">
										<c:set var="existingSafetyFilesLength" value="${fn:length(safety.safetyFilesList )+1}"></c:set>
									</c:if>
									<div id="selectedFilesInput">
                                    	<div class="file-field input-field" id="safetyFilesDiv${existingSafetyFilesLength }" >
	                                        <div class="btn bg-m t-c">
	                                            <span>Attach Files</span>
	                                            <input type="file" id="safetyFiles${existingSafetyFilesLength }" name="safetyFiles" onchange="selectFile('${existingSafetyFilesLength }')">
	                                        </div>
	                                        <div class="file-path-wrapper">
	                                            <input class="file-path validate" type="text">
	                                        </div>                                       
	                                    </div>
									</div>
                                    
                                    <div id="selectedFiles">
                                    	<c:forEach var="obj" items="${safety.safetyFilesList }" varStatus="index">
											 <div id="safetyFileName${index.count }">
												<a href="<%=CommonConstants2.SAFETY_FILES %>${obj.attachment }" class="filevalue" download>${obj.attachment }</a>
												<span onclick="removeFile(${index.count })" class="attachment-remove-btn">X</span>
												<input type="hidden" name="safetyFileNames" value="${obj.attachment }">
										     </div>
										     <div style="clear:both" ></div>
										</c:forEach>
									</div>
									
                                </div>                              
                            </div>
                            
                            <div class="row">
                                <div class="col s12 m12 l12 input-field">
                                    <textarea id="remarks" name="remarks" class="pmis-textarea validate" data-length="1000">${safety.remarks }</textarea>
                                    <label for="remarks">Remarks</label>
                                    <span id="remarksError" class="error-msg" ></span>
                                </div>
                            </div>
                            
                            <div class="row">                               
                                <div class="col s12 m6 l6 input-field">
                                    <input id="payment_date" name="payment_date" type="text" class="validate datepicker" value="${safety.payment_date }">
                                    <label for="payment_date">Payment Date</label>
                                    <button type="button" id="payment_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="payment_dateError" class="error-msg" ></span>
                                </div>
                                <div class="col s12 m6 l6 input-field amount-dropdown">
                                	<i class="material-icons amount-symbol center-align">₹</i>
                                    <input id="compensation" name="compensation" type="number" min="0.01" step="0.01" class="validate pdl-2em" value="${safety.compensation }">
                                    <label for="compensation" class="pdl-2em"> Compensation </label>
                                    <span id="compensationError" class="error-msg" ></span>
                                	<span id="compensation_unitsError" class="error-msg right" ></span>
                                    <!-- <div class="col s4 m2 l2 input-field pt-10"> -->
                                	<!-- <p class="searchable_label">Unit</p> -->
                                	<select class="units validate-dropdown" id="compensation_units" name="compensation_units">
                                		<!-- <option value="">Units</option> -->
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${safety.compensation_units eq obj.value }">selected</c:if> >${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                </div>
                                <%-- <div class="col s4 m2 l2 input-field pt-10">
                                	<p class="searchable_label">Unit</p>
                                	<select class="units searchable validate-dropdown" id="compensation_units" name="compensation_units">
                                		<option value="">Select</option>
                                		<c:forEach var="obj" items="${unitsList }">
	                                      <option value="${obj.value }" <c:if test="${safety.compensation_units eq obj.value }">selected</c:if> >${obj.unit }</option>
	                                	</c:forEach>
                                	</select>
                                	<span id="compensation_unitsError" class="error-msg" ></span>
                               	</div>  --%>
                            </div>                            

							 <div class="row">
								 <div class="col s6 m6 l6 input-field">
                                  <p class="searchable_label"> Status <span class="required">*</span></p>
                                    <select class="searchable validate-dropdown" id="status_fk" name="status_fk" >
                                        <option value="">Select</option>
                                        <c:forEach var="obj" items="${safetyStatusList }">
                                            <option value="${obj.status }" <c:if test="${safety.status_fk eq obj.status}">selected</c:if>>${obj.status}</option>
                                        </c:forEach>
                                    </select>                                    
                                    <span id="status_fkError" class="error-msg" ></span>
                                </div>
                                  <div class="col s6 m4 l6 input-field hidden" id="hidden_date">
                                    <input id="closure_date" name="closure_date" type="text" class="validate datepicker" value="${safety.closure_date }">
                                    <label for="closure_date">Closure Date</label>
                                    <button type="button" id="closure_date_icon" class="datepicker-button"><i class="fa fa-calendar"></i></button>
                                    <span id="closure_dateError" class="error-msg" ></span>
                                </div>
                                 
							</div> 
                            <div class="row">
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <button type="button" onclick="updateSafety()"
                                            id="btnUpdate" class="btn waves-effect waves-light bg-m">Update </button>
                                    </div>
                                </div>
                                <div class="col s6 m6 l6 mt-brdr">
                                    <div class="center-align m-1">
                                        <a href="<%=request.getContextPath() %>/safety" class="btn waves-effect waves-light bg-s">Cancel</a>
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
	<!-- footer included -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="/pmis/resources/js/jQuery-v.3.5.min.js"></script>
	<script src="/pmis/resources/js/materialize-v.1.0.min.js"></script>	
	<!-- <script src="/pmis/resources/js/datepickerDepedency.js"></script> -->	
	<script src="/pmis/resources/js/jquery-validation-1.19.1.min.js"></script>
	<script src="/pmis/resources/js/select2.min.js"></script>
	<script>

	  $(document).on('focus', '.datepicker', function () {        	 
			var id = $(this).attr('id');
				var dt = this.value.split(/[^0-9]/);
			    this.value = "";
			    var options = {
			    	maxDate: new Date(),
			        format: 'dd-mm-yyyy',
			        autoClose: true,
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
		 $(document).on('focus', '.datepicker-button', function () { 
			 var id = $(this).attr('id').split('_i')[0];
		     $('#'+id+'_icon').click(function () {
		         event.stopPropagation();
		         $('#'+id).focus().click();
		     });
		 });
		 
	
    var today = new Date();
    var dd = today.getDate();
    if (dd < 10) {
        dd = '0' + dd;
    }
    var mm = today.getMonth() + 1;  
    if (mm < 10) {
        mm = '0' + mm;
    }
    var yyyy = today.getFullYear();
    var today =  yyyy+'-'+ mm +'-'+dd ;  

		var user_role = '${sessionScope.USER_ROLE_NAME}';
		var user_type = '${sessionScope.USER_TYPE}';
        $(document).ready(function () {
        	$('select:not(.searchable)').formSelect();
            $('.searchable').select2();
            $('#remarks').characterCounter();
            var reporting_to_id_srfk = "${safety.hod_user_id_fk }";
            getResponsiblePersonsList(reporting_to_id_srfk);
            
            if($('#committee_required').is(":checked")){
           	 $('#committee_member_div').removeClass('hidden');
           }
            if(user_type == 'HOD' || user_role == 'IT Admin'){
            	
            }
            else{
            		if($("#status_fk").val()=="Closed")
            		{
            			$("#status_fk").attr("disabled",true);
            		}
            		else
           			{
           				$("#status_fk option[value='Closed']").remove();
           			}
            }
            
    		if($("#status_fk").val()=="Closed")
    		{
    			$("#btnUpdate").attr("disabled",true);
    		}
    		else
   			{
    			$("#btnUpdate").attr("disabled",false);
   			}           
            dateShowAndHide();
           /*  $('#date_icon').click(function (event) {
                event.stopPropagation();
                $('#date').click();
            }); */
            

           /*  $('#date').datepicker({
            	//maxDate: new Date(today),
            	format: 'dd-mm-yyyy',
            	autoClose:true
            }); */ 
            
        /*     
            $('#closure_date_icon').click(function (event) {
                event.stopPropagation();
                $('#closure_date').click();
            });            
            
            $('#closure_date').datepicker({
  	    	    format:'dd-mm-yyyy',
  	    	    onSelect: function () {
  	    	       $('.confirmation-btns .datepicker-done').click();
  	    	    }
  	        }).datepicker("setDate", new Date());                    
            $('#investigation_completed_icon').click(function (event) {
                event.stopPropagation();
                $('#investigation_completed').click();
            });
            
  	        $('#payment_date_icon').click(function (event) {
                event.stopPropagation();
                $('#payment_date').click();
            });   */           	   
            
  	      $('.pmis-textarea').css('height',function(){
          	this.style.height = (this.scrollHeight < 48) ? '48px' : this.scrollHeight + 'px';
          });
  	        
            var project_id_fk = "${safety.project_id_fk}";
            if ($.trim(project_id_fk) != '') {
                getWorksList(project_id_fk);
            }
            
            var work_id_fk = "${safety.work_id_fk}";
            if ($.trim(work_id_fk) != '') {
            	getContractsList(work_id_fk);
            }
           
            $('#committee_required').change(function(){
                if(this.checked){
                    $('#committee_formed_div').removeClass('hidden');
                }else{
                    $('#committee_formed_div').addClass('hidden');
                    $("#committee_formed_fk").val("No");
                    $('#committee_formed').prop('checked', false);
                }
            });
            $('#committee_formed').change(function(){
                if(this.checked){
                    $('#committee_member_div').removeClass('hidden');
                }else{
                    $('#committee_member_div').addClass('hidden');
                    //$("#committee_formed_fk").val("No");
                    //$('#committee_formed').prop('checked', false);
                }
            });
            $("#status_fk").on("change", dateShowAndHide);
            function dateShowAndHide(){
            	var status = $('#status_fk').val();
            	if(status == 'Closed'){
    	        	$('#hidden_date').removeClass('hidden');
    	        }else{
    	        	$('#hidden_date').addClass('hidden');
    	        }
            };
            
                     
            $("#committee_required").change(function(){
	            if($(this).prop("checked") == true){
	            	$("#committee_required_fk").val("Yes");
	            }else{
	            	$("#committee_required_fk").val("No");
	            }
	        });
	        
	        $("#committee_formed").change(function(){
	            if($(this).prop("checked") == true){
	            	$("#committee_formed_fk").val("Yes");
	            }else{
	            	$("#committee_formed_fk").val("No");
	            }
	        });
	        
	        var rValue = "${safety.committee_required_fk}";
	        var fValue = "${safety.committee_formed_fk}";
	        
	        if(rValue == 'Yes'){
	        	$("#committee_required_fk").val("Yes");
	        	$('#committee_formed_div').removeClass('hidden');
	        }else{
	        	$("#committee_required_fk").val("No");
	        	$('#committee_formed_div').addClass('hidden');
	        }
	        
	        if(fValue == 'Yes'){
	        	$("#committee_formed_fk").val("Yes");
	        }else{
	        	$("#committee_formed_fk").val("No");
	        }
	        
	        $("[data-length]").each(function(i,val){
	        	$('#'+val.id).characterCounter();;
	        });
            
        });
        
        function setTitle(category){
        	$("#title").val(category).focus();
        }
        
      //geting works list from database    
        function getWorksList(projectId) {
        	$(".page-loader").show();
            $("#work_id_fk option:not(:first)").remove();

            if ($.trim(projectId) != "") {
                var myParams = { project_id_fk: projectId };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getWorksList",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var workName = '';
                                if ($.trim(val.work_name) != '') { workName = ' - ' + $.trim(val.work_name) }
                                var work_id_fk = "${safety.work_id_fk }";
                                if ($.trim(work_id_fk) != '' && val.work_id == $.trim(work_id_fk)) {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '" selected>' + $.trim(val.work_id) + $.trim(workName) + '</option>');
                                } else {
                                    $("#work_id_fk").append('<option value="' + val.work_id + '">' + $.trim(val.work_id) + $.trim(workName) + '</option>');
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

        //geting contracts list    
        function getContractsList(work_id_fk) {
        	$(".page-loader").show();
            $("#contract_id_fk option:not(:first)").remove();
            if ($.trim(work_id_fk) != "") {
                var myParams = { work_id_fk: work_id_fk };
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/getContracts",
                    data: myParams, cache: false,
                    success: function (data) {
                        if (data.length > 0) {
                            $.each(data, function (i, val) {
                                var contract_name = '';
                                if ($.trim(val.contract_name) != '') { contract_name = ' - ' + $.trim(val.contract_name) }
                                var contract_id_fk = "${safety.contract_id_fk }";
                                if ($.trim(contract_id_fk) != '' && val.contract_id == $.trim(contract_id_fk)) {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '" selected>' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
                                } else {
                                	$("#contract_id_fk").append('<option value="' + val.contract_id + '">' + $.trim(val.contract_id) + $.trim(contract_name) + '</option>');
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
        
        function updateSafety(){
    		if(validator.form()){ // validation perform
    			$(".page-loader").show();
    			$("#responsible_person").attr("disabled",false);
    			var compensation = $('#compensation').val();
	  			if(compensation == ""){
	  				$('#compensation_units').val("");
	  			}
    			document.getElementById("safetyForm").submit();			
    	 	}
    	}
    	
    	
    	//Wait for the DOM to be ready
    	
    	// to validate apartment form inputs thruogh jquery.
    	   
    	    var validator = $('#safetyForm').validate({
    	    	ignore: ":hidden:not(.validate-dropdown)",
    			   rules: {
	    				  "project_id_fk": {
	   				 		required: true
	   				 	  },"work_id_fk": {
    				 		required: true
    				 	  },"contract_id_fk": {
    				 		required: true
    				 	  },"hod_user_id_fk": {
    				 		required: true
    				 	  },"department_fk": {
    				 		required: true
    				 	  },"category_fk": {
    				 		required: true
    				 	  },"impact_fk": {
    				 		required: true
    				 	  },"status_fk": {
    			 		    required: true,
    			 	   	  },"root_cause_fk": {
    			 		    required: true,
    			 	   	  },"title": {
    				 		required: true
    				 	  },"description": {
    			 		    required: true
    			 	   	  },"date": {
    				 		required: true
    				 	  },"location": {
    				 		required: true
    				 	  },"latitude": {
    				 		required: false
    				 	  },"longitude": {
    				 		required: false
    				 	  },"reported_by": {
    				 		required: false
    				 	  },"responsible_person":{
    				 		 required: false
    				 	  },"closure_date": {
    			 		    required: false,
    			 		   dateBefore1 : "#date",
    				 		statusCheck1: true
    			 	   	  },"committee_formed_fk": {
    				 		required: false
    				 	  },"lti_hours": {
    			 		    required: false
    			 	   	  },"equipment_impact": {
    				 		required: false
    				 	  },"people_impact": {
    				 		required: false
    				 	  },"work_impact":{
    				 		 required: false
    				 	  },"compensation": {
    				 		/*  required: function(element){
   	        		             return $("#compensation_units").val()!="";
   	        		         } */
    			 	   	  },"investigation_completed": {
    				 		required: false,
     			 		    dateBefore3 : "#date",
    				 		//statusCheck2: true
    				 	  },"payment_date": {
    			 		    required: false,
     			 		    dateBefore2 : "#date",
    				 		//statusCheck3: true
    			 	   	  },"corrective_measure_short_term": {
    				 		required: false
    				 	  },"corrective_measure_long_term":{
    				 		 required: false
    				 	  },"remarks":{
    				 		 required: false
    				 	  },"compensation_units":{
   	        		 		 /* required: function(element){
   	        		             return $("#compensation").val()!="";
   	        		         } */
   	        		 	  }
    				 				
    			 	},
    			   messages: {
	    				 "project_id_fk": {
	   			 			required: 'Required'
	   			 	  	 },"work_id_fk": {
    			 			required: 'Required'
    			 	  	 },"contract_id_fk": {
    			 			required: 'Required'
    			 	  	 },"hod_user_id_fk": {
     			 	  		required: 'Required'
  				 	  	  },"department_fk": {
    			 			required: 'Required'
    			 	  	 },"category_fk": {
    			 			required: 'Required'
    			 	  	 },"impact_fk": {
    			 			required: 'Required'
    			 	  	 },"status_fk": {
    			 			required: 'Required'
    			 	  	 },"root_cause_fk": {
    			 	  		required: 'Required'
   			 	   	  	 },"title": {
    			 			required: 'Required'
    			 	  	 },"description": {
    			 			required: 'Required'
    			 	  	 },"date": {
    			 			required: 'Required'
    			 	  	 },"location": {
     				 		required: 'Required'
	   				 	  },"latitude": {
	   				 		required: 'Required'
	   				 	  },"longitude": {
	   				 		required: 'Required'
	   				 	  },"reported_by": {
	   				 		required: 'Required'
	   				 	  },"responsible_person":{
	   				 		 required: 'Required'
	   				 	  },"closure_date": {
	   			 		    required: 'Required',
	   			 	   	  },"committee_formed_fk": {
	   				 		required: 'Required'
	   				 	  },"lti_hours": {
	   			 		    required: 'Required'
	   			 	   	  },"equipment_impact": {
	   				 		required: 'Required'
	   				 	  },"people_impact": {
	   				 		required: 'Required'
    				 	  },"work_impact":{
    				 		 required: 'Required'
    				 	  },"compensation": {
    				 		required: 'Required'
    			 	   	  },"investigation_completed": {
    			 	   		required: 'Required'
    				 	  },"payment_date": {
    				 		 required: 'Required'
    			 	   	  },"corrective_measure_short_term": {
    			 	   		required: 'Required'
    				 	  },"corrective_measure_long_term":{
    				 		 required: 'Required'
    				 	  },"remarks":{
    			 	  		required: 'Required'
    				 	  },"compensation_units":{
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
    			 	    }else if (element.attr("id") == "contract_id_fk" ){
    			 	    	 document.getElementById("contract_id_fkError").innerHTML="";
    			 			 error.appendTo('#contract_id_fkError');
    			 	    }else if (element.attr("id") == "hod_user_id_fk" ){
	   			 	    	 document.getElementById("hod_user_id_fkError").innerHTML="";
				 			 error.appendTo('#hod_user_id_fkError');
				 	    }else if (element.attr("id") == "department_fk" ){
    			 		     document.getElementById("department_fkError").innerHTML="";
    			 			 error.appendTo('#department_fkError');
    			 	    }else if (element.attr("id") == "category_fk" ){
    			 		     document.getElementById("category_fkError").innerHTML="";
    			 			 error.appendTo('#category_fkError');
    			 	    }else if (element.attr("id") == "impact_fk" ){
    			 		     document.getElementById("impact_fkError").innerHTML="";
    			 			 error.appendTo('#impact_fkError');
    			 	    }else if (element.attr("id") == "status_fk" ){
    			 		     document.getElementById("status_fkError").innerHTML="";
    			 			 error.appendTo('#status_fkError');
    			 	    }else if (element.attr("id") == "root_cause_fk" ){
	   			 		     document.getElementById("root_cause_fkError").innerHTML="";
				 			 error.appendTo('#root_cause_fkError');
				 	    }else if (element.attr("id") == "title" ){
    			 		     document.getElementById("titleError").innerHTML="";
    			 			 error.appendTo('#titleError');
    			 	    }else if (element.attr("name") == "description" ){
    			 		     document.getElementById("descriptionError").innerHTML="";
    			 			 error.appendTo('#descriptionError');
    			 	    }else if (element.attr("id") == "date" ){
    			 		     document.getElementById("dateError").innerHTML="";
    			 			 error.appendTo('#dateError');
    			 	    }else if (element.attr("id") == "location" ){
  			 	    	     document.getElementById("locationError").innerHTML="";
  			 			     error.appendTo('#locationError');
	  			 	    }else if (element.attr("id") == "latitude" ){
	  			 		     document.getElementById("latitudeError").innerHTML="";
	  			 			 error.appendTo('#latitudeError');
	  			 	    }else if (element.attr("id") == "longitude" ){
	  			 		     document.getElementById("longitudeError").innerHTML="";
	  			 			 error.appendTo('#longitudeError');
	  			 	    }else if (element.attr("id") == "reported_by" ){
	  			 		     document.getElementById("reported_byError").innerHTML="";
	  			 			 error.appendTo('#reported_byError');
	  			 	    }else if (element.attr("id") == "responsible_person" ){
	  			 		     document.getElementById("responsible_personError").innerHTML="";
	  			 			 error.appendTo('#responsible_personError');
	  			 	    }else if (element.attr("id") == "closure_date" ){
	  			 		     document.getElementById("closure_dateError").innerHTML="";
	  			 			 error.appendTo('#closure_dateError');
	  			 	    }else if (element.attr("id") == "committee_formed_fk" ){
	  			 		     document.getElementById("committee_formed_fkError").innerHTML="";
	  			 			 error.appendTo('#committee_formed_fkError');
	  			 	    }else if (element.attr("name") == "lti_hours" ){
	  			 		     document.getElementById("lti_hoursError").innerHTML="";
	  			 			 error.appendTo('#lti_hoursError');
	  			 	    }else if (element.attr("id") == "equipment_impact" ){
	  			 		     document.getElementById("equipment_impactError").innerHTML="";
	  			 			 error.appendTo('#equipment_impactError');
	  			 	    }else if (element.attr("id") == "people_impact" ){
	  			 		     document.getElementById("people_impactError").innerHTML="";
	  			 			 error.appendTo('#people_impactError');
	  			 	    }else if (element.attr("id") == "work_impact" ){
	  			 		     document.getElementById("work_impactError").innerHTML="";
	  			 			 error.appendTo('#work_impactError');
	  			 	    }else if (element.attr("id") == "compensation" ){
	  			 		     document.getElementById("compensationError").innerHTML="";
	  			 			 error.appendTo('#compensationError');
	  			 	    }else if (element.attr("id") == "investigation_completed" ){
	  			 		     document.getElementById("investigation_completedError").innerHTML="";
	  			 			 error.appendTo('#investigation_completedError');
	  			 	    }else if (element.attr("id") == "payment_date" ){
	  			 		     document.getElementById("payment_dateError").innerHTML="";
	  			 			 error.appendTo('#payment_dateError');
	  			 	    }else if (element.attr("name") == "corrective_measure_short_term" ){
	  			 		     document.getElementById("corrective_measure_short_termError").innerHTML="";
	  			 			 error.appendTo('#corrective_measure_short_termError');
	  			 	    }else if (element.attr("id") == "corrective_measure_long_term" ){
	  			 		     document.getElementById("corrective_measure_long_termError").innerHTML="";
	  			 			 error.appendTo('#corrective_measure_long_termError');
	  			 	    }else if (element.attr("id") == "compensation_units" ){
	  			 		     document.getElementById("compensation_unitsError").innerHTML="";
	  			 			 error.appendTo('#compensation_unitsError');
	  			 	    }else if (element.attr("id") == "remarks" ){
    			 		     document.getElementById("remarksError").innerHTML="";
    			 			 error.appendTo('#remarksError');
    			 	    }
    			 },invalidHandler: function (form, validator) {
                     var errors = validator.numberOfInvalids();
                     if (errors) {
                         var position = validator.errorList[0].element;
                         jQuery('html, body').animate({
                             scrollTop:jQuery(validator.errorList[0].element).offset().top - 100
                         }, 1000);
                     }
                 },submitHandler: function(form) {
    			    // do other things for a valid form
    			    //form.submit();
    			    //return true;
    			  }
    		});
    	
    	    $.validator.addMethod("dateBefore1", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	            
	        }, "Closure date must be after Date");
	    	
	    	$.validator.addMethod("dateBefore2", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Payment Date must be after Date");
	    	
	    	$.validator.addMethod("dateBefore3", function(value, element) {
	            var fromDateString = $('#date').val(); //
	            var fromDateParts = fromDateString.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var fromDate = new Date(+fromDateParts[2], fromDateParts[1] - 1, +fromDateParts[0]); 
	
	            var toDateParts = value.split("-");
	            // month is 0-based, that's why we need dataParts[1] - 1
	            var toDate = new Date(+toDateParts[2], toDateParts[1] - 1, +toDateParts[0]);
	         
	            if($.trim(fromDateString) != '' && $.trim(value) != ''){
	            	//return Date.parse(fromDate) <= Date.parse(toDate);
	            	return Date.parse(fromDate) <= Date.parse(toDate);
	            }else if($.trim(fromDateString) == '' && $.trim(value) != ''){
	            	return false;
	            }else{
	            	return true;
	            }
	        }, "Investigation Completion date must be after Date");
    	
    	    $.validator.addMethod("statusCheck1", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#closure_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    	    $.validator.addMethod("statusCheck2", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#investigation_completed").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    	    $.validator.addMethod("statusCheck3", function(value, element) {
    	    	var status = $("#status_fk").val();
	            if(($.trim(status) == '' || status == 'Open') && $.trim(value) != ''){
	            	$("#payment_date").val('').focus();
	            	return false;
	            }else{
	            	return true;
	            }	            
	        }, "Status is opened or empty, So you cannot select this field");
    	    
    		
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
            
            $('input').change(function(){
        	    if ($(this).val() != ""){
        	        $(this).valid();
        	    }
        	});
            function removeMedia(link,id){
             	  $('#'+id).val('');
             	  $(link).prev().text('');
             	  $(link).css('display','none');
            }
            
            function selectFile(no){
			    files = $("#safetyFiles"+no)[0].files;
			    var html = "";
			    for (var i = 0; i < files.length ; i++) {
			    	html =  html + '<div id=safetyFileName'+no+'>'
					 + '<a href="#" class="filevalue">'+$(this).get(0).files[i].name+'</a>'
					 + '<span onclick="removeFile('+no+')" class="attachment-remove-btn">X</span>'
					 + '</div>'
					 + '<div style="clear:both;"></div>';
			    }
			    $("#selectedFiles").append(html);
			    
			    $('#safetyFilesDiv'+no).hide();
			    
				var fileIndex = Number(no)+1;
				moreFiles(fileIndex);
			}
			
			function moreFiles(no){
				var html = "";
				html =  html + '<div class="file-field input-field" id="safetyFilesDiv'+no+'" >'
				+ '<div class="btn bg-m t-c">'
				+ '<span>Attach Files</span>'
				+ '<input type="file" id="safetyFiles'+no+'" name="safetyFiles" onchange="selectFile('+no+')">'
				+ '</div>'
				+ '<div class="file-path-wrapper">'
				+ '<input class="file-path validate" type="text">'
				+ '</div>'                          
				+ '</div>'
				$("#selectedFilesInput").append(html);
			}
			
			function removeFile(no){			
	         	$('#safetyFilesDiv'+no).remove();
	         	$('#safetyFileName'+no).remove();
	        }
			function getResponsiblePersonsList(reporting_to_id){
	        	$(".page-loader").show();
	            $("#responsible_person option:not(:first)").remove();
	            var reporting_to_id_srfk = reporting_to_id;
	            if ($.trim(reporting_to_id_srfk) != "") {
	                var myParams = { reporting_to_id_srfk: reporting_to_id_srfk };
	                $.ajax({
	                	url: "<%=request.getContextPath()%>/ajax/getResponsiblePersonsListForSafetyForm",
	                    data: myParams, cache: false,
	                    success: function (data) {
	                        if (data.length > 0) {
	                            $.each(data, function (i, val) {
	                            	var userName = '';
	                                if ($.trim(val.user_name) != '') { userName = ' - ' + $.trim(val.user_name) }
	                                if(val.user_id == "${safety.responsible_person}"){
		                                $("#responsible_person").append('<option  value="' + val.user_id + '" selected>' + $.trim(val.designation) + userName + '</option>');
	                                }else{
	                                	$("#responsible_person").append('<option  value="' + val.user_id + '">' + $.trim(val.designation) + userName + '</option>');
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
			
			$(document).ready(function () {
				var hod_user_id = '${safety.hod_user_id_fk}';
				var dy_hod_user_id = '${safety.dy_hod_user_id_fk}';
				var logged_in_user_id = '${sessionScope.USER_ID}';
				if($.trim(hod_user_id) != logged_in_user_id && $.trim(dy_hod_user_id) != logged_in_user_id){
					$("#responsible_person").attr("disabled",true);
				}else if($.trim(hod_user_id) == logged_in_user_id || $.trim(dy_hod_user_id) == logged_in_user_id){
					$("#responsible_person").attr("disabled",false);
				}
			});
    </script>
</body>
</html>